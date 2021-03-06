package org.jeecgframework.web.superquery.page;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.jeecgframework.web.superquery.entity.SuperQueryFieldEntity;
import org.jeecgframework.web.superquery.entity.SuperQueryTableEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Entity
 * @Description: 高级查询
 * @date 2017-12-04 18:10:18
 */
public class SuperQueryMainPage implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
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
     * 查询规则名称
     */
    @Excel(name = "查询规则名称")
    private String queryName;
    /**
     * 查询规则编码
     */
    @Excel(name = "查询规则编码")
    private String queryCode;
    /**
     * 查询类型
     */
    @Excel(name = "查询类型")
    private String queryType;
    /**
     * 说明
     */
    @Excel(name = "说明")
    private String content;
    /**
     * 保存-表集合
     */
    @ExcelCollection(name = "表集合")
    private List<SuperQueryTableEntity> superQueryTableList = new ArrayList<SuperQueryTableEntity>();
    /**
     * 保存-字段配置
     */
    @ExcelCollection(name = "字段配置")
    private List<SuperQueryFieldEntity> superQueryFieldList = new ArrayList<SuperQueryFieldEntity>();

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  主键
     */
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
     * @return: java.lang.String  查询规则名称
     */
    public String getQueryName() {
        return this.queryName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  查询规则名称
     */
    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  查询规则编码
     */
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

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  说明
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  说明
     */
    public void setContent(String content) {
        this.content = content;
    }

    public List<SuperQueryTableEntity> getSuperQueryTableList() {
        return superQueryTableList;
    }

    public void setSuperQueryTableList(List<SuperQueryTableEntity> superQueryTableList) {
        this.superQueryTableList = superQueryTableList;
    }

    public List<SuperQueryFieldEntity> getSuperQueryFieldList() {
        return superQueryFieldList;
    }

    public void setSuperQueryFieldList(List<SuperQueryFieldEntity> superQueryFieldList) {
        this.superQueryFieldList = superQueryFieldList;
    }
}
