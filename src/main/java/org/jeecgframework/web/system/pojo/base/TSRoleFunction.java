package org.jeecgframework.web.system.pojo.base;

import org.jeecgframework.core.common.entity.AbstractIdEntity;

import javax.persistence.*;

/**
 * TRoleFunction entity.
 *
 * @author 张代浩
 */
@Entity
@Table(name = "t_s_role_function")
public class TSRoleFunction extends AbstractIdEntity implements java.io.Serializable {
    private TSFunction tsFunction;
    private TSRole tsRole;
    private String operation;
    private String dataRule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "functionid")
    public TSFunction getTSFunction() {
        return this.tsFunction;
    }

    public void setTSFunction(TSFunction tsFunction) {
        this.tsFunction = tsFunction;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleid")
    public TSRole getTSRole() {
        return this.tsRole;
    }

    public void setTSRole(TSRole tsRole) {
        this.tsRole = tsRole;
    }

    @Column(name = "operation", length = 100)
    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Column(name = "datarule", length = 100)
    public String getDataRule() {
        return dataRule;
    }

    public void setDataRule(String dataRule) {
        this.dataRule = dataRule;
    }

}