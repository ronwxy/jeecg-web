package org.jeecgframework.web.system.pojo.base;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Entity
 * @Description: t_s_interrole_interface
 * @date 2017-11-30 16:12:31
 */
@Entity
@Table(name = "t_s_interrole_interface", schema = "")
@SuppressWarnings("serial")
public class InterroleInterfaceEntity implements java.io.Serializable {
    //接口权限
    private TSInterfaceEntity interfaceEntity;
    //角色
    private InterroleEntity interroleEntity;
    //接口数据
    private String dataRule;
    /**
     * ID
     */
    private String id;


    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

    @Column(name = "ID", nullable = false, length = 32)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "DATA_RULE", length = 1000)
    public String getDataRule() {
        return this.dataRule;
    }

    public void setDataRule(String dataRule) {
        this.dataRule = dataRule;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interface_id")

    @NotFound(action = NotFoundAction.IGNORE)

    public TSInterfaceEntity getInterfaceEntity() {
        return this.interfaceEntity;
    }

    public void setInterfaceEntity(TSInterfaceEntity tSInterfaceEntity) {
        this.interfaceEntity = tSInterfaceEntity;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interrole_id")
    public InterroleEntity getInterroleEntity() {
        return this.interroleEntity;
    }

    public void setInterroleEntity(InterroleEntity interroleEntity) {
        this.interroleEntity = interroleEntity;
    }

    // TODO 以下是两个表的关联关系
	
	/*public TSInterfaceEntity getTSInterface() {
		return InterroleEntity;
	}

	public void setTSInterface(TSInterfaceEntity InterroleEntity) {
		InterroleEntity = InterroleEntity;
	}*/

	
	
	/*public InterroleEntity getInterRole() {
		return InterRole;
	}

	public void setInterRole(InterroleEntity interRole) {
		InterRole = interRole;
	}*/


}
