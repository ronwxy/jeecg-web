package org.jeecgframework.web.cgform.service.upload;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.upload.CgUploadEntity;

/**
 * @author 赵俊夫
 * @version V1.0
 * @Title:CgUploadServiceI
 * @description:智能表单文件上传服务
 * @date Jul 24, 2013 9:55:07 PM
 */
public interface CgUploadServiceI extends CommonService {
    /**
     * 删除文件
     *
     * @param file
     */
    public void deleteFile(CgUploadEntity file);

    /**
     * 将文件信息回填到智能表单的表中
     *
     * @param cgFormId
     * @param cgFormName
     * @param cgFormField
     */
    public void writeBack(String cgFormId, String cgFormName, String cgFormField, String fileId, String fileUrl);
}
