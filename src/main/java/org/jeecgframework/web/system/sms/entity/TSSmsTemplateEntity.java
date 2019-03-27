package org.jeecgframework.web.system.sms.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Entity
 * @Description: 消息模本表
 * @date 2014-09-17 23:52:46
 */
@Entity
@Table(name = "t_s_sms_template", schema = "")
@SuppressWarnings("serial")
public class TSSmsTemplateEntity implements java.io.Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 创建人名称
     */
    private String createName;
    /**
     * 创建人登录名称
     */
    private String createBy;
    /**
     * 创建日期
     */
    private java.util.Date createDate;
    /**
     * 更新人名称
     */
    private String updateName;
    /**
     * 更新人登录名称
     */
    private String updateBy;
    /**
     * 更新日期
     */
    private java.util.Date updateDate;
    /**
     * 模板类型
     */
    @Excel(name = "模板类型")
    private String templateType;
    /**
     * 模板CODE
     */
    @Excel(name = "模板CODE")
    private String templateCode;
    /**
     * 模板名称
     */
    @Excel(name = "模板名称")
    private String templateName;
    /**
     * 模板内容
     */
    @Excel(name = "模板内容")
    private String templateContent;

    private String templateTestJson;

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  主键
     */
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return this.id;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  创建人名称
     */
    @Column(name = "CREATE_NAME", nullable = true, length = 50)
    public String getCreateName() {
        return this.createName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  创建人名称
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  创建人登录名称
     */
    @Column(name = "CREATE_BY", nullable = true, length = 50)
    public String getCreateBy() {
        return this.createBy;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  创建人登录名称
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  创建日期
     */
    @Column(name = "CREATE_DATE", nullable = true, length = 20)
    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 方法: 设置java.util.Date
     *
     * @param: java.util.Date  创建日期
     */
    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  更新人名称
     */
    @Column(name = "UPDATE_NAME", nullable = true, length = 50)
    public String getUpdateName() {
        return this.updateName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  更新人名称
     */
    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  更新人登录名称
     */
    @Column(name = "UPDATE_BY", nullable = true, length = 50)
    public String getUpdateBy() {
        return this.updateBy;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  更新人登录名称
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  更新日期
     */
    @Column(name = "UPDATE_DATE", nullable = true, length = 20)
    public java.util.Date getUpdateDate() {
        return this.updateDate;
    }

    /**
     * 方法: 设置java.util.Date
     *
     * @param: java.util.Date  更新日期
     */
    public void setUpdateDate(java.util.Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  模板类型
     */
    @Column(name = "TEMPLATE_TYPE", nullable = true, length = 1)
    public String getTemplateType() {
        return this.templateType;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  模板类型
     */
    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  模板名称
     */
    @Column(name = "TEMPLATE_NAME", nullable = true, length = 50)
    public String getTemplateName() {
        return this.templateName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  模板名称
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  模板内容
     */
    @Column(name = "TEMPLATE_CONTENT", nullable = true, length = 1000)
    public String getTemplateContent() {
        return this.templateContent;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  模板内容
     */
    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    @Column(name = "TEMPLATE_CODE", nullable = true)
    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    @Column(name = "TEMPLATE_TEST_JSON", nullable = true)
    public String getTemplateTestJson() {
        return templateTestJson;
    }

    public void setTemplateTestJson(String templateTestJson) {
        this.templateTestJson = templateTestJson;
    }


}
