package org.jeecgframework.web.system.pojo.base;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.jeecgframework.core.common.controller.CustomJsonDateDeserializer;
import org.jeecgframework.core.common.entity.AbstractIdEntity;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 部门机构表
 *
 * @author 张代浩
 */
@Entity
@Table(name = "t_s_depart")
public class TSDepart extends AbstractIdEntity implements java.io.Serializable {
    //上级部门
    private TSDepart tsDepart;
    @Excel(name = "部门名称", width = 20)
    private String departname;
    @Excel(name = "部门描述", width = 20)
    private String description;
    @Excel(name = "机构编码", width = 20)
    private String orgCode;
    @Excel(name = "机构类型编码", width = 25)
    private String orgType;
    @Excel(name = "电话", width = 20)
    private String mobile;
    @Excel(name = "传真", width = 20)
    private String fax;
    @Excel(name = "地址", width = 20)
    private String address;
    //排序
    private String departOrder;
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

    private List<TSDepart> tsDeparts = new ArrayList<TSDepart>();//下属部门

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentdepartid")
    @JsonIgnore
    public TSDepart getTSPDepart() {
        return this.tsDepart;
    }

    public void setTSPDepart(TSDepart tsDepart) {
        this.tsDepart = tsDepart;
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
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
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
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
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


    @Column(name = "departname", nullable = false, length = 100)
    public String getDepartname() {
        return this.departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }

    @Column(name = "description", length = 500)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSPDepart")
    @JsonIgnore
    public List<TSDepart> getTSDeparts() {
        return tsDeparts;
    }

    public void setTSDeparts(List<TSDepart> tSDeparts) {
        tsDeparts = tSDeparts;
    }

    @Column(name = "org_code", length = 64)
    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Column(name = "org_type", length = 1)
    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    @Column(name = "mobile", length = 32)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "fax", length = 32)
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Column(name = "address", length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "depart_order")
    public String getDepartOrder() {
        return departOrder;
    }

    public void setDepartOrder(String departOrder) {
        this.departOrder = departOrder;
    }


}