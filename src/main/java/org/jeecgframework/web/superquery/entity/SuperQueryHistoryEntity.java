package org.jeecgframework.web.superquery.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Entity
 * @Description: 高级查询历史记录
 * @date
 */
@Entity
@Table(name = "super_query_history", schema = "")
@SuppressWarnings("serial")
public class SuperQueryHistoryEntity implements java.io.Serializable {
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
     * 用户id
     */
    @Excel(name = "用户id", width = 15)
    private String userId;
    /**
     * 查询规则编码
     */
    @Excel(name = "查询规则编码", width = 15)
    private String queryCode;
    /**
     * 查询类型
     */
    @Excel(name = "查询类型", width = 15, dicCode = "sel_type")
    private String queryType;
    /**
     * 说明
     */
    @Excel(name = "记录", width = 15)
    private String record;

    @Excel(name = "名称", width = 15)
    private String historyName;

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

    @Column(name = "UPDATE_DATE", nullable = true, length = 50)
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
     * @return: java.lang.String  查询规则编码
     */

    @Column(name = "QUERY_CODE", nullable = true, length = 50)
    public String getQueryCode() {
        return this.queryCode;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  查询规则编码
     */
    public void setQueryCode(String queryCode) {
        this.queryCode = queryCode;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  查询类型
     */

    @Column(name = "QUERY_TYPE", nullable = true, length = 50)
    public String getQueryType() {
        return this.queryType;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  查询类型
     */
    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    @Column(name = "user_id", nullable = true, length = 50)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "record", nullable = true, length = 255)
    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    @Column(name = "history_name", nullable = true, length = 255)
    public String getHistoryName() {
        return historyName;
    }

    public void setHistoryName(String historyName) {
        this.historyName = historyName;
    }

}
