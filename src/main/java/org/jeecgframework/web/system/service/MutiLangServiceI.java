package org.jeecgframework.web.system.service;

import org.jeecgframework.web.system.pojo.base.MutiLangEntity;

public interface MutiLangServiceI {

    void initAllMutiLang();

    String getLang(String langKey);

    String getLang(String langKey, String args);

    void refleshMutiLangCach();

    /**
     * 更新缓存，插入缓存
     */
    void putMutiLang(MutiLangEntity mutiLangEntity);

    /**
     * 更新缓存，插入缓存
     */
    void putMutiLang(String langKey, String langCode, String langContext);

}
