package org.jeecgframework.web.system.pojo.base;
// default package

import org.jeecgframework.core.common.entity.AbstractIdEntity;

import javax.persistence.*;

/**
 * 通知公告角色授权表
 *
 * @author alax
 */
@Entity
@Table(name = "t_s_notice_authority_role")
@SuppressWarnings("serial")
public class TSNoticeAuthorityRole extends AbstractIdEntity implements java.io.Serializable {

    private String noticeId;// 通告ID
    private TSRole role;//

    @Column(name = "notice_id", nullable = true)
    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    @ManyToOne
    @JoinColumn(name = "role_id")
    public TSRole getRole() {
        return role;
    }

    public void setRole(TSRole role) {
        this.role = role;
    }


}