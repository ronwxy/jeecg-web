package org.jeecgframework.core.extend.swftools;

import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ResourceUtil;

import javax.servlet.http.HttpServletRequest;


/**
 * 常量类
 */
public class ConStant {
    /**
     * OpenOffice安装根目录
     */
    public static final String OFFICE_HOME;
    /**
     * 文件上传保存根目录
     */
    public static final String UPLOAD_BASE_DIR = "upload";
    /**
     * 文件转换工具根目录
     */
    public static final String SWFTOOLS_BASE_DIR = "swftools";
    /**
     * PDF-SWF
     */
    public static final String SWFTOOLS_PDF2SWF_PATH = "pdf2swf.exe";
    public static final String SWFTOOLS_PDF2SWF = "pdf2swf";
    /**
     * GIF-SWF
     */
    public static final String SWFTOOLS_GIF2SWF_PATH = "gif2swf.exe";
    /**
     * PNG-SWF
     */
    public static final String SWFTOOLS_PNG2SWF_PATH = "png2swf.exe";
    /**
     * JPEG-SWF
     */
    public static final String SWFTOOLS_JPEG2SWF_PATH = "jpeg2swf.exe";
    /**
     * WAV-SWF
     */
    public static final String SWFTOOLS_WAV2SWF_PATH = "wav2swf.exe";
    /**
     * PDF合并
     */
    public static final String SWFTOOLS_PDFCOMBINE_PATH = "swfcombine.exe";
    /**
     * SWF文件后缀
     */
    public static final String SWF_STUFFIX = "swf";
    public static String SWFTOOLS_HOME = "";

    static {
        OFFICE_HOME = ResourceUtil.getSessionattachmenttitle("office_home");
    }

    /**
     * 根据扩展名获取转换工具
     *
     * @param extend
     * @return
     */
    public static String getSWFToolsPath(String extend) {
        HttpServletRequest request = ContextHolderUtils.getRequest();
        SWFTOOLS_HOME = request.getSession().getServletContext().getRealPath("/") + SWFTOOLS_BASE_DIR + "/";
        if ("pdf".equals(extend)) {
            SWFTOOLS_HOME += SWFTOOLS_PDF2SWF_PATH;
        }
        if ("gif".equals(extend)) {
            SWFTOOLS_HOME += SWFTOOLS_GIF2SWF_PATH;
        }
        if ("png".equals(extend)) {
            SWFTOOLS_HOME += SWFTOOLS_PNG2SWF_PATH;
        }
        if ("jpeg".equals(extend)) {
            SWFTOOLS_HOME += SWFTOOLS_JPEG2SWF_PATH;
        }
        if ("wav".equals(extend)) {
            SWFTOOLS_HOME += SWFTOOLS_WAV2SWF_PATH;
        }
        return SWFTOOLS_HOME;
    }

    public static String getSWFToolsForLinux(String extend) {

        SWFTOOLS_HOME = "";
        if ("pdf".equals(extend)) {
            SWFTOOLS_HOME += SWFTOOLS_PDF2SWF;
        }
        if ("gif".equals(extend)) {
            SWFTOOLS_HOME += SWFTOOLS_GIF2SWF_PATH;
        }
        if ("png".equals(extend)) {
            SWFTOOLS_HOME += SWFTOOLS_PNG2SWF_PATH;
        }
        if ("jpeg".equals(extend)) {
            SWFTOOLS_HOME += SWFTOOLS_JPEG2SWF_PATH;
        }
        if ("wav".equals(extend)) {
            SWFTOOLS_HOME += SWFTOOLS_WAV2SWF_PATH;
        }
        return SWFTOOLS_HOME;
    }
}
