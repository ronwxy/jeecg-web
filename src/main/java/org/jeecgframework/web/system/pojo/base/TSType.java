package org.jeecgframework.web.system.pojo.base;
// default package

import org.jeecgframework.core.common.entity.AbstractIdEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 通用类型字典表
 *
 * @author 张代浩
 */
@Entity
@Table(name = "t_s_type")
public class TSType extends AbstractIdEntity implements java.io.Serializable {

    private TSTypegroup tsTypegroup;//类型分组
    private TSType tsType;//父类型
    private String typename;//类型名称
    private String typecode;//类型编码

    private Date createDate;//创建时间
    private String createName;//创建用户

    //	private List<TPProcess> TSProcesses = new ArrayList();
    private List<TSType> tsTypes = new ArrayList();

    private Integer orderNum;//序号

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typegroupid")
    public TSTypegroup getTSTypegroup() {
        return this.tsTypegroup;
    }

    public void setTSTypegroup(TSTypegroup tsTypeGroup) {
        this.tsTypegroup = tsTypeGroup;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typepid")
    public TSType getTSType() {
        return this.tsType;
    }

    public void setTSType(TSType tsType) {
        this.tsType = tsType;
    }

    @Column(name = "typename", length = 50)
    public String getTypename() {
        return this.typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    @Column(name = "typecode", length = 50)
    public String getTypecode() {
        return this.typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "create_name", length = 36)
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }


//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSType")
//	public List<TPProcess> getTSProcesses() {
//		return this.TSProcesses;
//	}
//
//	public void setTSProcesses(List<TPProcess> TSProcesses) {
//		this.TSProcesses = TSProcesses;
//	}

    @Column(name = "order_num", length = 3)
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSType")
    public List<TSType> getTSTypes() {
        return this.tsTypes;
    }

    public void setTSTypes(List<TSType> tsTypes) {
        this.tsTypes = tsTypes;
    }

}