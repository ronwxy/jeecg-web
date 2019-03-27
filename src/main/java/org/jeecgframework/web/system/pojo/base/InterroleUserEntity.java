package org.jeecgframework.web.system.pojo.base;

import org.jeecgframework.core.common.entity.AbstractIdEntity;

import javax.persistence.*;

/**
 * 接口角色用户
 *
 * @author
 */
@Entity
@Table(name = "t_s_interrole_user")
public class InterroleUserEntity extends AbstractIdEntity implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private TSUser tsUser;
    private InterroleEntity interroleEntity;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public TSUser getTSUser() {
        return this.tsUser;
    }

    public void setTSUser(TSUser tsUser) {
        this.tsUser = tsUser;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "interrole_id")
    public InterroleEntity getInterroleEntity() {
        return interroleEntity;
    }

    public void setInterroleEntity(InterroleEntity interroleEntity) {
        this.interroleEntity = interroleEntity;
    }
}