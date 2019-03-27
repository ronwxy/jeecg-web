package org.jeecgframework.web.cgform.entity.fillrule;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Entity
 * @Description: 填值规则表
 * @date 2018-01-04 19:01:44
 */
@Entity
@Table(name = "t_s_fill_rule", schema = "")
@SuppressWarnings("serial")
public class TSFillRuleEntity implements java.io.Serializable {
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
     * 所属部门
     */

    private String sysOrgCode;
    /**
     * 所属公司
     */

    private String sysCompanyCode;
    /**
     * 流程状态
     */

    private String bpmStatus;
    /**
     * 规则code
     */
    @Excel(name = "规则code", width = 15)

    private String ruleCode;
    /**
     * 规则名称
     */
    @Excel(name = "规则名称", width = 15)

    private String ruleName;
    /**
     * 规则实现类
     */
    @Excel(name = "规则实现类", width = 15)

    private String ruleClass;
    /**
     * 规则参数
     */
    @Excel(name = "规则参数", width = 15)

    private String ruleParam;

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
     * @return: java.lang.String  所属部门
     */
    @Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
    public String getSysOrgCode() {
        return this.sysOrgCode;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  所属部门
     */
    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  所属公司
     */
    @Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
    public String getSysCompanyCode() {
        return this.sysCompanyCode;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  所属公司
     */
    public void setSysCompanyCode(String sysCompanyCode) {
        this.sysCompanyCode = sysCompanyCode;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  流程状态
     */
    @Column(name = "BPM_STATUS", nullable = true, length = 32)
    public String getBpmStatus() {
        return this.bpmStatus;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  流程状态
     */
    public void setBpmStatus(String bpmStatus) {
        this.bpmStatus = bpmStatus;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  规则code
     */
    @Column(name = "RULE_CODE", nullable = true, length = 255)
    public String getRuleCode() {
        return this.ruleCode;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  规则code
     */
    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  规则名称
     */
    @Column(name = "RULE_NAME", nullable = true, length = 255)
    public String getRuleName() {
        return this.ruleName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  规则名称
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  规则实现类
     */
    @Column(name = "RULE_CLASS", nullable = true, length = 500)
    public String getRuleClass() {
        return this.ruleClass;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  规则实现类
     */
    public void setRuleClass(String ruleClass) {
        this.ruleClass = ruleClass;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  规则参数
     */
    @Column(name = "RULE_PARAM", nullable = true, length = 500)
    public String getRuleParam() {
        return this.ruleParam;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  规则参数
     */
    public void setRuleParam(String ruleParam) {
        this.ruleParam = ruleParam;
    }
}
