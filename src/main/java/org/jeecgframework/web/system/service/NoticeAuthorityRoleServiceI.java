package org.jeecgframework.web.system.service;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSNoticeAuthorityRole;

import java.io.Serializable;

public interface NoticeAuthorityRoleServiceI extends CommonService {

    @Override
    public <T> void delete(T entity);

    @Override
    public <T> Serializable save(T entity);

    @Override
    public <T> void saveOrUpdate(T entity);

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(TSNoticeAuthorityRole t);

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(TSNoticeAuthorityRole t);

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(TSNoticeAuthorityRole t);

    public boolean checkAuthorityRole(String noticeId, String roleid);

    public void saveTSNoticeAuthorityRole(TSNoticeAuthorityRole noticeAuthorityRole);

    public void doDelTSNoticeAuthorityRole(TSNoticeAuthorityRole noticeAuthorityRole);
}
