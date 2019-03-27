package org.jeecgframework.tag.core;

import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.web.cgform.common.CgAutoListConstant;
import org.jeecgframework.web.cgform.engine.TempletContext;
import org.jeecgframework.web.system.controller.core.LoginController;
import org.jeecgframework.web.system.service.CacheServiceI;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * 【优化系统】父Tag标签，主要为做缓存使用
 *
 * @author yugw
 */
public abstract class AbstractJeecgTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private Logger log = Logger.getLogger(LoginController.class);

    /**
     * 获取缓存
     *
     * @return
     */
    public StringBuffer getTagCache() {
        CacheServiceI cacheService = ApplicationContextUtil.getContext().getBean(CacheServiceI.class);
        if (CgAutoListConstant.SYS_MODE_DEV.equalsIgnoreCase(TempletContext.sysMode)) {
            return null;
        }
        log.debug("-----TagCache-----toString()-----" + toString());
        return (StringBuffer) cacheService.get(CacheServiceI.TAG_CACHE, toString());
    }

    /**
     * 存放缓存
     *
     * @param tagCache
     */
    public void putTagCache(StringBuffer tagCache) {
        CacheServiceI cacheService = ApplicationContextUtil.getContext().getBean(CacheServiceI.class);
        cacheService.put(CacheServiceI.TAG_CACHE, toString(), tagCache);
    }
}