package org.jeecgframework.web.system.pojo.base;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: 字典表授权配置
 * @Description: 字典表授权配置
 * @date 2018-07-10 15:30:22
 */
@Entity
@Table(name = "t_s_dict_table_config", schema = "")
@SuppressWarnings("serial")
public class TSDictTableConfigEntity implements java.io.Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 表名
     */
    @Excel(name = "表名", width = 15)
    private String tableName;
    /**
     * 值字段名
     */
    @Excel(name = "值字段名", width = 15)
    private String valueCol;
    /**
     * 文本字段名
     */
    @Excel(name = "文本字段名", width = 15)
    private String textCol;
    /**
     * 字典表查询条件
     */
    @Excel(name = "字典表查询条件", width = 15)
    private String dictCondition;
    /**
     * 是否启用
     */
    @Excel(name = "是否启用", width = 15, dicCode = "sf_yn")
    private String isvalid;
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
     * @return: java.lang.String  表名
     */

    @Column(name = "TABLE_NAME", nullable = true, length = 100)
    public String getTableName() {
        return this.tableName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  表名
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  值字段名
     */

    @Column(name = "VALUE_COL", nullable = true, length = 50)
    public String getValueCol() {
        return this.valueCol;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  值字段名
     */
    public void setValueCol(String valueCol) {
        this.valueCol = valueCol;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  文本字段名
     */

    @Column(name = "TEXT_COL", nullable = true, length = 50)
    public String getTextCol() {
        return this.textCol;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  文本字段名
     */
    public void setTextCol(String textCol) {
        this.textCol = textCol;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  是否启用
     */

    @Column(name = "ISVALID", nullable = true, length = 32)
    public String getIsvalid() {
        return this.isvalid;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  是否启用
     */
    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
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

    @Column(name = "DICT_CONDITION")
    public String getDictCondition() {
        return dictCondition;
    }

    public void setDictCondition(String dictCondition) {
        this.dictCondition = dictCondition;
    }


}
