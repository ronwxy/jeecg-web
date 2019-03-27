package org.jeecgframework.core.util;

import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.enums.StoreUploadFilePathEnum;
import org.jeecgframework.core.extend.swftools.SwfToolsUtil;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 文件操作工具类
 *
 * @author 张代浩
 */
public class FileUtils {
    /**
     * 获取文件扩展名
     *
     * @param filename
     * @return
     */
    public static String getExtend(String filename) {
        return getExtend(filename, "");
    }

    /**
     * 获取文件扩展名
     *
     * @param filename
     * @return
     */
    public static String getExtend(String filename, String defExt) {
        if ((filename != null) && (filename.length() > 0)) {
            int i = filename.lastIndexOf('.');

            if ((i > 0) && (i < (filename.length() - 1))) {
                return (filename.substring(i + 1)).toLowerCase();
            }
        }
        return defExt.toLowerCase();
    }

    /**
     * 获取文件名称[不含后缀名]
     *
     * @param
     * @return String
     */
    public static String getFilePrefix(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, splitIndex).replaceAll("\\s*", "");
    }

    /**
     * 获取文件名称[不含后缀名]
     * 不去掉文件目录的空格
     *
     * @param
     * @return String
     */
    public static String getFilePrefix2(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, splitIndex);
    }

    /**
     * 文件复制
     * 方法摘要：这里一句话描述方法的用途
     *
     * @param
     * @return void
     */
    public static void copyFile(String inputFile, String outputFile) throws FileNotFoundException {
        File sFile = new File(inputFile);
        File tFile = new File(outputFile);
        FileInputStream fis = new FileInputStream(sFile);
        FileOutputStream fos = new FileOutputStream(tFile);
        int temp = 0;
        byte[] buf = new byte[10240];
        try {
            while ((temp = fis.read(buf)) != -1) {
                fos.write(buf, 0, temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断文件是否为图片<br>
     * <br>
     *
     * @param filename 文件名<br>
     * 判断具体文件类型<br>
     * @return 检查后的结果<br>
     * @throws Exception
     */
    public static boolean isPicture(String filename) {
        // 文件名称为空的场合
        if (oConvertUtils.isEmpty(filename)) {
            // 返回不和合法
            return false;
        }
        // 获得文件后缀名
        String tmpName = getExtend(filename);
//        String tmpName = filename;
        // 声明图片后缀名数组
        String[][] imgeArray = {{"bmp", "0"}, {"dib", "1"},
                {"gif", "2"}, {"jfif", "3"}, {"jpe", "4"},
                {"jpeg", "5"}, {"jpg", "6"}, {"png", "7"},
                {"tif", "8"}, {"tiff", "9"}, {"ico", "10"}};
        // 遍历名称数组
        for (int i = 0; i < imgeArray.length; i++) {
            // 判断单个类型文件的场合
            if (imgeArray[i][0].equals(tmpName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断文件是否为DWG<br>
     * <br>
     *
     * @param filename 文件名<br>
     * 判断具体文件类型<br>
     * @return 检查后的结果<br>
     * @throws Exception
     */
    public static boolean isDwg(String filename) {
        // 文件名称为空的场合
        if (oConvertUtils.isEmpty(filename)) {
            // 返回不和合法
            return false;
        }
        // 获得文件后缀名
        String tmpName = getExtend(filename);
        // 声明图片后缀名数组
        return "dwg".equals(tmpName);
    }

    /**
     * 删除指定的文件
     *
     * @param strFileName 指定绝对路径的文件名
     * @return 如果删除成功true否则false
     */
    public static boolean delete(String strFileName) {
        File fileDelete = new File(strFileName);

        if (!fileDelete.exists() || !fileDelete.isFile()) {
            LogUtil.info("错误: " + strFileName + "不存在!");
            return false;
        }

        LogUtil.info("--------成功删除文件---------" + strFileName);
        return fileDelete.delete();
    }

    /**
     * @param @param fileName
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: encodingFileName 2015-11-26 huangzq add
     * @Description: 防止文件名中文乱码含有空格时%20
     */
    public static String encodingFileName(String fileName) {
        String returnFileName = "";
        try {
            returnFileName = URLEncoder.encode(fileName, "UTF-8");
            returnFileName = StringUtils.replace(returnFileName, "+", "%20");
            if (returnFileName.length() > 150) {
                returnFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
                returnFileName = StringUtils.replace(returnFileName, " ", "%20");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            LogUtil.info("Don't support this encoding ...");
        }
        return returnFileName;
    }

    /**
     * 根据现有路径获取SWF文件名称
     *
     * @author taoYan
     * @since 2018年7月26日
     */
    public static String getSwfPath(String path) {
        String leftSlash = "/";
        if (!File.separator.equals(leftSlash)) {
            path = path.replace(File.separator, leftSlash);
        }
        String fileDir = path.substring(0, path.lastIndexOf(leftSlash) + 1);//文件目录带/
        int pointPosition = path.lastIndexOf(".");
        String fileName = path.substring(path.lastIndexOf(leftSlash) + 1, pointPosition);//文件名不带后缀
        String swfName = PinyinUtil.getPinYinHeadChar(fileName);// 取文件名首字母作为SWF文件名
        return fileDir + swfName + ".swf";
    }

    /**
     * 上传txt文件，防止乱码
     *
     * @author taoYan
     * @since 2018年7月26日
     */
    public static void uploadTxtFile(MultipartFile mf, String savePath) throws IOException {
        //利用utf-8字符集的固定首行隐藏编码原理
        //Unicode:FF FE   UTF-8:EF BB
        byte[] allbytes = mf.getBytes();
        try {
            String head1 = toHexString(allbytes[0]);
            //System.out.println(head1);
            String head2 = toHexString(allbytes[1]);
            //System.out.println(head2);
            if ("ef".equals(head1) && "bb".equals(head2)) {
                //UTF-8
                String contents = new String(mf.getBytes(), StandardCharsets.UTF_8);
                if (StringUtils.isNotBlank(contents)) {
                    OutputStream out = new FileOutputStream(savePath);
                    out.write(contents.getBytes());
                    out.close();
                }
            } else {

                //GBK
                String contents = new String(mf.getBytes(), "GBK");
                OutputStream out = new FileOutputStream(savePath);
                out.write(contents.getBytes());
                out.close();

            }
        } catch (Exception e) {
            String contents = new String(mf.getBytes(), StandardCharsets.UTF_8);
            if (StringUtils.isNotBlank(contents)) {
                OutputStream out = new FileOutputStream(savePath);
                out.write(contents.getBytes());
                out.close();
            }
        }
    }

    public static String toHexString(int index) {
        String hexString = Integer.toHexString(index);
        // 1个byte变成16进制的，只需要2位就可以表示了，取后面两位，去掉前面的符号填充   
        hexString = hexString.substring(hexString.length() - 2);
        return hexString;
    }

    /**
     * 统一上传文件保存
     * @param bizType  文件类型
     * @param mf 上传文件
     * @param filePrefixName 文件名 为空系统给默认规则文件名_时间戳
     * @param swfTransform 是否转swf 一般情况为null 即可
     * @return
     * @throws IOException
     */
    public static String fileSave(String bizType, MultipartFile mf, String filePrefixName,String swfTransform) throws IOException {
        LogUtil.info("--------文件正在保存---------" );
        //demo中设置为D://upFiles,实际项目应因事制宜
        String ctxPath = ResourceUtil.getConfigByName("webUploadpath");
        //默认上传文件是否转换为swf，实现在线预览功能开关
        String globalSwfTransformFlag = ResourceUtil.getConfigByName("swf.transform.flag");
        String bizPath = StoreUploadFilePathEnum.getPath(bizType);//根据业务名称判断上传路径
        File file = new File(ctxPath + File.separator + bizPath);
        if (!file.exists()) {
            file.mkdirs();// 创建文件根目录
        }
        String orgName = mf.getOriginalFilename();// 获取文件名
        if(StringUtil.isEmpty(filePrefixName)) {
            filePrefixName = orgName.substring(0, orgName.lastIndexOf(".")) + "_" + System.currentTimeMillis();
        }
        String fileName =  filePrefixName + orgName.substring(orgName.indexOf("."));
        String savePath = file.getPath() + File.separator + fileName;
        String fileExt = getExtend(fileName);
        if ("txt".equals(fileExt)) {
            uploadTxtFile(mf, savePath);
        } else {
            File savefile = new File(savePath);
            FileCopyUtils.copy(mf.getBytes(), savefile);
        }
        String dbpath = bizPath + File.separator + fileName;
        if (dbpath.contains("\\")) {
            dbpath = dbpath.replace("\\", "/");
        }
        if ("true".equals(globalSwfTransformFlag) && "true".equals(swfTransform)) {
            //转换swf
            SwfToolsUtil.convert2SWF(savePath);
        }
        LogUtil.info("--------文件成功保存至" + dbpath);
        return dbpath;
    }

    /**
     * 上传文件保存
     * @param bizType
     * @param mf
     * @return
     * @throws IOException
     */
    public static String fileSave(String bizType, MultipartFile mf) throws IOException {
        return fileSave(bizType,mf,null,null);
    }

    /**
     * 获取保存目录
     *
     * @param bizType 业务类型
     * @return
     */
    public static String getBizPath(String bizType) {
        String ctxPath = ResourceUtil.getConfigByName("webUploadpath");
        String bizPath = StoreUploadFilePathEnum.getPath(bizType);//根据业务名称判断上传路径
        String dirPath = ctxPath + File.separator + bizPath;
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();// 创建文件根目录
        }
        return bizPath;
    }

}
