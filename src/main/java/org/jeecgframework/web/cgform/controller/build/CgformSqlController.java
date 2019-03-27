package org.jeecgframework.web.cgform.controller.build;

import com.thoughtworks.xstream.XStream;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.common.DBTable;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.*;
import org.jeecgframework.web.cgform.dao.config.CgFormVersionDao;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.pojo.config.CgFormHeadPojo;
import org.jeecgframework.web.cgform.service.migrate.MigrateForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author duanqilu
 * @version V1.0
 * @Title: Controller
 * @Description: Online表单导入导出（采用XML方式）
 * @date 2013-09-04
 */
//@Scope("prototype")
@Controller
@RequestMapping("/cgformSqlController")
public class CgformSqlController extends BaseController {
    /**
     * Logger for this class
     */
    @SuppressWarnings("unused")
    private static final Logger logger = Logger
            .getLogger(CgformSqlController.class);
    @Autowired
    private CgFormVersionDao cgFormVersionDao;

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("namedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    /**
     * 导出 Form(采用XML方式)
     *
     * @return
     * @throws SQLException
     */
    @RequestMapping(params = "doMigrateOut")
    public void doMigrateOut(HttpServletRequest request, HttpServletResponse response) {
        String ids = request.getParameter("ids"); // 获得选择表单ID
        //List<String> listSQL = new ArrayList<String>();
        try {
            //listSQL = MigrateForm.createSQL(ids, jdbcTemplate);// 创建查询语句
            //MigrateForm.executeSQL(listSQL, jdbcTemplate);// 生成sql并拼装
            List<DBTable> dbTables = MigrateForm.buildExportDbTableList(ids, jdbcTemplate);  //创建导出的数据对象
            //MigrateForm.generateXmlDataOutFlieContent(ids, jdbcDao);
            //update by duanqilu 2013-12-05 增加多表单导出功能
            String lsId = "";
            if (ids.indexOf(",") > 0) {
                lsId = ids.substring(0, ids.indexOf(","));
            } else {
                lsId = ids;
            }
            CgFormHeadEntity cgFormHeadEntity = cgFormVersionDao.getCgFormById(lsId);
            //update by duanqilu 2013-12-05 增加多表单导出功能
            //MigrateForm.createFile(request,cgFormHeadEntity.getTableName())
            // 创建文件
            String lsFileName = cgFormHeadEntity.getTableName();
            String destFileDir = ResourceUtil.getSystempPath() + File.separator + lsFileName;
            MigrateForm.generateXmlDataOutFlieContent(dbTables, destFileDir);
            // 压缩文件
            lsFileName = MigrateForm.zip(null, "", destFileDir);
            // 文件下载
            File file = new File(lsFileName);
            String filename = file.getName();
            InputStream fis = new BufferedInputStream(new FileInputStream(lsFileName));
            // 输出生成的zip文件
            // 清空response
            response.reset();
            // 设置response的Header
            response.setContentType("text/html;charset=utf-8");
            request.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename="
                    + new String(filename.getBytes("utf-8"), "ISO8859-1"));
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
                toClient.write(buffer, 0, bytesRead);
            }
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            fis.close();
            logger.info("[" + IpUtil.getIpAddr(request) + "][online表单配置导出]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入sql文件跳转
     *
     * @param icon
     * @param req
     * @return
     */
    @RequestMapping(params = "toCgformMigrate")
    public ModelAndView toCgformMigrate(HttpServletRequest req) {
        return new ModelAndView("jeecg/cgform/config/cgformMigrateSqlInclude");
    }

    /**
     * 导入Form(采用XML方式)
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "doMigrateIn", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson doMigrateIn(HttpServletRequest request,
                                HttpServletResponse response) {
        String message = null;
        AjaxJson j = new AjaxJson();
        String lsFile = "";
        UploadFile uploadFile = new UploadFile(request, lsFile);
        uploadFile.setCusPath("");
        uploadFile.setSwfpath("");
        String uploadbasepath = uploadFile.getBasePath();// 文件上传根目录
        if (uploadbasepath == null) {
            uploadbasepath = ResourceUtil.getConfigByName("uploadpath");
        }
        String path = uploadbasepath + File.separator;// 文件保存在硬盘的相对路径
        String realPath = uploadFile.getMultipartRequest().getSession()
                .getServletContext().getRealPath(File.separator)
                + path;// 文件的硬盘真实路径
        message = null;
        try {
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdir();// 若目录不存在，创建根目录
            }
            uploadFile.getMultipartRequest().setCharacterEncoding("UTF-8");
            MultipartHttpServletRequest multipartRequest = uploadFile
                    .getMultipartRequest();
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            String fileName = "";
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                MultipartFile mf = entity.getValue();// 获取上传文件对象
                fileName = mf.getOriginalFilename();// 获取文件名
                String savePath = realPath + fileName;
                File savefile = new File(savePath);
                String lsTmp = savefile.getName();
				/*String sqlfilename = realPath
						+ ls_tmp.substring(0, ls_tmp.lastIndexOf(".")) + "\\"
						+ ls_tmp.substring(0, ls_tmp.lastIndexOf(".")) + ".sql";*/
                FileCopyUtils.copy(mf.getBytes(), savefile);
                MigrateForm.unzip(savePath, "");
                String sqlFileDir = realPath + lsTmp.substring(0, lsTmp.lastIndexOf("."));
                File sqlDirFile = new File(sqlFileDir);
                String sqlFileName = sqlDirFile.getPath() + File.separator;
                if (sqlDirFile.isDirectory()) {
                    sqlFileName += sqlDirFile.list()[0];
                }
				/*OfficeHtmlUtil officeHtml = new OfficeHtmlUtil();
				String sqlStr = officeHtml.getInfo(sqlfilename);
				String[] sqlStrs = sqlStr.split(";");
				for (String exesql : sqlStrs) {
					if (!StringUtil.isEmpty(exesql) && !"\n".equals(exesql)) {
						jdbcTemplate.execute(exesql);
					}
				}*/
                XStream xStream = new XStream();
                xStream.processAnnotations(DBTable.class);
                @SuppressWarnings("unchecked")
                List<DBTable> dbTables = (List<DBTable>) xStream.fromXML(new File(sqlFileName));
                if (!dbTables.isEmpty() && dbTables.size() > 0) {
                    for (DBTable dbTable : dbTables) {
                        mergeMigrateInComponent(dbTable);
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            LogUtil.error(e1.toString());
            message = e1.toString();
        }
        logger.info("[" + IpUtil.getIpAddr(request) + "][online表单配置导入]" + message);
        if (StringUtil.isNotEmpty(message)) {
            j.setMsg("SQL文件导入失败," + message);
        } else {
            j.setMsg("SQL文件导入成功");
        }

        return j;
    }

    private <T> void mergeMigrateInComponent(DBTable<T> dbTable) {
        Class<T> clazz = dbTable.getClass1();
        if (null != clazz) {
            List<T> dataList = dbTable.getTableData();
            if (null == dataList || dataList.size() < 1) {
                return;
            }
            Map<String, String> idMap = new HashMap<String, String>();
            String id = "";
            String countSql = "";
            SqlParameterSource sqlParameterSource;
            List<Map<String, Object>> idList = new ArrayList<Map<String, Object>>();
            for (T t : dataList) {
                ReflectHelper reflectHelper = new ReflectHelper(t);
                List<String> ignores = new ArrayList<String>();
                ignores.add("class");
                PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(clazz);
                for (PropertyDescriptor pd : pds) {
                    if (null == reflectHelper.getMethodValue(pd.getName())) {
                        ignores.add(pd.getName());

                    } else if (reflectHelper.isIgore(pd.getName())) {
                        ignores.add(pd.getName());
                    }

                }
                if (t instanceof CgFormHeadPojo) {
                    reflectHelper.setMethodValue("isDbsynch", "N");
                }

                SqlInjectionUtil.filterContent(dbTable.getTableName());

                id = (String) reflectHelper.getMethodValue("id");
                countSql = "select id from " + dbTable.getTableName() + " where id=?0";
                if (t instanceof CgFormHeadPojo) {
                    countSql = "select id from " + dbTable.getTableName() + " where table_name=?0";
                    id = oConvertUtils.getString(reflectHelper.getMethodValue("tableName"));
                }
                idList = jdbcTemplate.queryForList(countSql, id);
                sqlParameterSource = MigrateForm.generateParameterMap(t, ignores);
                if (idList.size() > 0 && t instanceof CgFormHeadPojo) {
                    idMap.put("id", oConvertUtils.getString(idList.get(0).get("id")));
                    namedJdbcTemplate.update("delete from cgform_field where table_id=:id", idMap);
                    namedJdbcTemplate.update("delete from cgform_head where id=:id", idMap);
                    namedJdbcTemplate.update(MigrateForm.generateInsertSql(dbTable.getTableName(), clazz, ignores), sqlParameterSource);
                } else if (idList.size() > 0) {
                    namedJdbcTemplate.update(MigrateForm.generateUpdateSql(dbTable.getTableName(), clazz, ignores), sqlParameterSource);
                } else {
                    namedJdbcTemplate.update(MigrateForm.generateInsertSql(dbTable.getTableName(), clazz, ignores), sqlParameterSource);
                }
            }
        }
    }
}
