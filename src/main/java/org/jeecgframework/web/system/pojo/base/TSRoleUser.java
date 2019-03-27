package org.jeecgframework.web.system.pojo.base;

import org.jeecgframework.core.common.entity.AbstractIdEntity;

import javax.persistence.*;

/**
 * TSRoleUser entity.
 *
 * @author 张代浩
 */
@Entity
@Table(name = "t_s_role_user")
public class TSRoleUser extends AbstractIdEntity implements java.io.Serializable {
    private TSUser tsUser;
    private TSRole tsRole;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    public TSUser getTSUser() {
        return this.tsUser;
    }

    public void setTSUser(TSUser tsUser) {
        this.tsUser = tsUser;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleid")
    public TSRole getTSRole() {
        return this.tsRole;
    }

    public void setTSRole(TSRole tsRole) {
        this.tsRole = tsRole;
    }

}