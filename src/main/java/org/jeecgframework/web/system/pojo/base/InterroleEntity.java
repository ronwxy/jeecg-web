package org.jeecgframework.web.system.pojo.base;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: 角色表
 * @Description: t_s_interrole
 * @date 2017-11-30 11:38:39
 */
@Entity
@Table(name = "t_s_interrole", schema = "")
@SuppressWarnings("serial")
public class InterroleEntity implements java.io.Serializable {
    /**
     * id
     */
    private String id;
    /**
     * 接口角色编码
     */
    @Excel(name = "接口角色编码", width = 15)
    private String roleCode;
    /**
     * 接口角色名称
     */
    @Excel(name = "接口角色名称", width = 15)
    private String roleName;
    /**
     * 修改时间
     */
    @Excel(name = "修改时间", width = 15)
    private String updateName;
    /**
     * 修改时间
     */
    @Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
    private java.util.Date updateDate;
    /**
     * 创建人id
     */
    @Excel(name = "创建人id", width = 15)
    private String updateBy;
    /**
     * 创建人
     */
    @Excel(name = "创建人", width = 15)
    private String createName;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
    private java.util.Date createDate;
    /**
     * 创建人id
     */
    @Excel(name = "创建人id", width = 15)
    private String createBy;

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  id
     */
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

    @Column(name = "ID", nullable = false, length = 32)
    public String getId() {
        return this.id;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  接口角色编码
     */

    @Column(name = "ROLE_CODE", nullable = true, length = 10)
    public String getRoleCode() {
        return this.roleCode;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  接口角色编码
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  接口角色名称
     */

    @Column(name = "ROLE_NAME", nullable = true, length = 100)
    public String getRoleName() {
        return this.roleName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  接口角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  修改时间
     */

    @Column(name = "UPDATE_NAME", nullable = true, length = 32)
    public String getUpdateName() {
        return this.updateName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  修改时间
     */
    public void setUpdateName(String updateName) {
        this.updateName = updateName;
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
     * @return: java.lang.String  创建人id
     */

    @Column(name = "UPDATE_BY", nullable = true, length = 32)
    public String getUpdateBy() {
        return this.updateBy;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  创建人id
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  创建人
     */

    @Column(name = "CREATE_NAME", nullable = true, length = 32)
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
     * @return: java.lang.String  创建人id
     */

    @Column(name = "CREATE_BY", nullable = true, length = 32)
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
}
