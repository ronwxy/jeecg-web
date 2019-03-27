package org.jeecgframework.web.system.pojo.base;
// default package

import org.jeecgframework.core.common.entity.AbstractIdEntity;

import javax.persistence.*;

/**
 * 通知公告用户授权表
 *
 * @author alax
 */
@Entity
@Table(name = "t_s_notice_authority_user")
@SuppressWarnings("serial")
public class TSNoticeAuthorityUser extends AbstractIdEntity implements java.io.Serializable {

    private String noticeId;// 通告ID
    private TSUser user;//用户

    @Column(name = "notice_id", nullable = true)
    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public TSUser getUser() {
        return user;
    }

    public void setUser(TSUser user) {
        this.user = user;
    }

}