package org.jeecgframework.web.cgform.entity.upload;

import org.jeecgframework.web.system.pojo.base.TSAttachment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author 赵俊夫
 * @version V1.0
 * @Title:CgUploadEntity
 * @description: 智能表单文件上传
 * @date Jul 24, 2013 9:01:55 PM
 */
@Entity
@Table(name = "cgform_uploadfiles", schema = "")
@PrimaryKeyJoinColumn(name = "id")
@SuppressWarnings("serial")
public class CgUploadEntity extends TSAttachment implements java.io.Serializable {

    /**
     * 自定义表名
     */
    private String cgformName;
    /**
     * 自定义表的主键
     */
    private String cgformId;
    /**
     * 自定义表上传控件绑定字段
     */
    private String cgformField;

    @Column(name = "CGFORM_ID", nullable = false, length = 36)
    public String getCgformId() {
        return cgformId;
    }

    public void setCgformId(String cgformId) {
        this.cgformId = cgformId;
    }

    @Column(name = "CGFORM_NAME", nullable = false, length = 100)
    public String getCgformName() {
        return cgformName;
    }

    public void setCgformName(String cgformName) {
        this.cgformName = cgformName;
    }

    @Column(name = "CGFORM_FIELD", nullable = false, length = 100)
    public String getCgformField() {
        return cgformField;
    }

    public void setCgformField(String cgformField) {
        this.cgformField = cgformField;
    }


}
