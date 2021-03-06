package org.jeecgframework.web.system.pojo.base;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Entity
 * @Description: 部门管理员设置
 * @date 2017-12-01 18:20:32
 */
@Entity
@Table(name = "t_s_depart_auth_group", schema = "")
@SuppressWarnings("serial")
public class TSDepartAuthGroupEntity implements java.io.Serializable {
    /**
     * ID
     */
    private String id;
    /**
     * 权限组名称
     */
    @Excel(name = "权限组名称", width = 15)
    private String groupName;
    /**
     * 部门ID
     */
    @Excel(name = "部门ID", width = 15)
    private String deptId;
    /**
     * 部门编码
     */
    @Excel(name = "部门编码", width = 15)
    private String deptCode;
    /**
     * 部门名称
     */
    @Excel(name = "部门名称", width = 15)
    private String deptName;
    /**
     * 类型1/公司2/部门4/供应商
     */
    @Excel(name = "类型", width = 15)
    private String departType;
    /**
     * 级别
     */
    @Excel(name = "级别", width = 15)
    private Integer level;
    /**
     * 创建人
     */
    @Excel(name = "创建人", width = 15)
    private String createName;
    /**
     * 创建人id
     */
    @Excel(name = "创建人id", width = 15)
    private String createBy;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
    private java.util.Date createDate;
    /**
     * 修改人
     */
    @Excel(name = "修改人", width = 15)
    private String updateName;
    /**
     * 修改人id
     */
    @Excel(name = "修改人id", width = 15)
    private String updateBy;
    /**
     * 修改时间
     */
    @Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
    private java.util.Date updateDate;
    /**
     * 所属部门
     */
    @Excel(name = "所属部门", width = 15)
    private String sysOrgCode;
    /**
     * 所属公司
     */
    @Excel(name = "所属公司", width = 15)
    private String sysCompanyCode;

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  ID
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
     * @param: java.lang.String  ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  权限组名称
     */

    @Column(name = "GROUP_NAME", nullable = true, length = 100)
    public String getGroupName() {
        return this.groupName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  权限组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  部门ID
     */

    @Column(name = "DEPT_ID", nullable = true, length = 36)
    public String getDeptId() {
        return this.deptId;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  部门ID
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  部门编码
     */

    @Column(name = "DEPT_CODE", nullable = true, length = 50)
    public String getDeptCode() {
        return this.deptCode;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  部门编码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  部门名称
     */

    @Column(name = "DEPT_NAME", nullable = true, length = 100)
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  类型
     */

    @Column(name = "DEPART_TYPE", nullable = true, length = 2)
    public String getDepartType() {
        return this.departType;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  类型
     */
    public void setDepartType(String departType) {
        this.departType = departType;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  级别
     */

    @Column(name = "LEVEL", nullable = true, length = 10)
    public Integer getLevel() {
        return this.level;
    }

    /**
     * 方法: 设置java.lang.Integer
     *
     * @param: java.lang.Integer  级别
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  创建人
     */

    @Column(name = "CREATE_NAME", nullable = true, length = 50)
    public String getCreateName() {
        return this.createName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  创建人
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  创建人id
     */

    @Column(name = "CREATE_BY", nullable = true, length = 50)
    public String getCreateBy() {
        return this.createBy;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  创建人id
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  创建时间
     */

    @Column(name = "CREATE_DATE", nullable = true)
    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 方法: 设置java.util.Date
     *
     * @param: java.util.Date  创建时间
     */
    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  修改人
     */

    @Column(name = "UPDATE_NAME", nullable = true, length = 50)
    public String getUpdateName() {
        return this.updateName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  修改人
     */
    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  修改人id
     */

    @Column(name = "UPDATE_BY", nullable = true, length = 50)
    public String getUpdateBy() {
        return this.updateBy;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  修改人id
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  修改时间
     */

    @Column(name = "UPDATE_DATE", nullable = true)
    public java.util.Date getUpdateDate() {
        return this.updateDate;
    }

    /**
     * 方法: 设置java.util.Date
     *
     * @param: java.util.Date  修改时间
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
}
