package org.jeecgframework.web.system.pojo.base;
// default package

import org.jeecgframework.core.common.entity.AbstractIdEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TTypegroup entity.
 */
@Entity
@Table(name = "t_s_typegroup")
public class TSTypegroup extends AbstractIdEntity implements java.io.Serializable {
    private String typegroupname;
    private String typegroupcode;

    private Date createDate;//创建时间
    private String createName;//创建用户

    private List<TSType> tsTypes = new ArrayList<TSType>();

    @Column(name = "typegroupname", length = 50)
    public String getTypegroupname() {
        return this.typegroupname;
    }

    public void setTypegroupname(String typegroupname) {
        this.typegroupname = typegroupname;
    }

    @Column(name = "typegroupcode", length = 50)
    public String getTypegroupcode() {
        return this.typegroupcode;
    }

    public void setTypegroupcode(String typegroupcode) {
        this.typegroupcode = typegroupcode;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSTypegroup")
    public List<TSType> getTSTypes() {
        return this.tsTypes;
    }

    public void setTSTypes(List<TSType> tsTypes) {
        this.tsTypes = tsTypes;
    }

}