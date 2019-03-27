<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.cnbot.common.utils.AliOssService" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="org.jeecgframework.core.util.ApplicationContextUtil" %>
<%@ page import="org.jeecgframework.core.util.FileUtils" %>
<%@ page import="org.jeecgframework.core.util.ResourceUtil" %>
<%@ page import="javax.servlet.ServletContext" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.util.*" %>
<%
	String currentPath = request.getRequestURI().replace(request.getContextPath(), "");
	File currentFile = new File(currentPath);
	currentPath = currentFile.getParent() + File.separator;
	String propertiesPath = request.getSession().getServletContext().getRealPath(currentPath + "config.properties");
	Properties properties = new Properties();

	try {
		properties.load(new FileInputStream(propertiesPath));
	} catch (Exception e) {
		//加载失败的处理
		e.printStackTrace();
	}

	List<String> savePath = Arrays.asList(properties.getProperty("savePath").split(","));
	//如果是oss的情况
	String ossFlag = ResourceUtil.getCnbotsConfingByName("upload.oss.flag");
	if (StringUtils.equals(ossFlag, "true")) {
		StringBuffer imgStr = new StringBuffer();
		AliOssService aliOssService = ApplicationContextUtil.getContext().getBean("aliOssService", AliOssService.class);
		String bizPath = FileUtils.getBizPath(null);
		bizPath = StringUtils.replace(bizPath, "\\", "/");
		for (int i = 0; i < savePath.size(); i++) {
			List<String> list = aliOssService.listObjectsKey(bizPath + "/" + savePath.get(i) + "/");
			//jsp 不支持
//			list.forEach(o->imgStr.append(o).append("ue_separate_ue"));
			for (String o : list) {
				imgStr.append(o).append("ue_separate_ue");
			}
		}
		out.print(imgStr);
		return;
	}

    //仅做示例用，请自行修改
	String imgStr ="";
	String path = "";
	String ctxPath = ResourceUtil.getConfigByName("webUploadpath");
	String bizPath = FileUtils.getBizPath(null);
    String basePath = ctxPath + File.separator + bizPath + File.separator;
//	String realpath = getRealPath(request,path)+"/"+path;
    String realpath = basePath + path;
	List<File> files = new ArrayList();
	for (int i = 0; i < savePath.size(); i++) {
		files = getFiles(realpath + File.separator + savePath.get(i), files);
	}
	for(File file :files ){
		imgStr += file.getPath().replace(basePath, "") + "ue_separate_ue";
	}
	if(imgStr!=""){
        imgStr = imgStr.substring(0,imgStr.lastIndexOf("ue_separate_ue")).replace(File.separator, "/").trim();
    }
	out.print(imgStr);
%>
<%!
public List getFiles(String realpath, List files) {
	
	File realFile = new File(realpath);
	if (realFile.isDirectory()) {
		File[] subfiles = realFile.listFiles();
		for(File file :subfiles ){
			if(file.isDirectory()){
				getFiles(file.getAbsolutePath(),files);
			}else{
				if(!getFileType(file.getName()).equals("")) {
					files.add(file);
				}
			}
		}
	}
	return files;
}

public String getRealPath(HttpServletRequest request,String path){
	ServletContext application = request.getSession().getServletContext();
	String str = application.getRealPath(request.getServletPath());
	return new File(str).getParent();
}

public String getFileType(String fileName){
	String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
	Iterator<String> type = Arrays.asList(fileType).iterator();
	while(type.hasNext()){
		String t = type.next();
		if(fileName.toLowerCase().endsWith(t)){
			return t;
		}
	}
	return "";
}
%>