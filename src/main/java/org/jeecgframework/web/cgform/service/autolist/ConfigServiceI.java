package org.jeecgframework.web.cgform.service.autolist;

import java.util.Map;

/**
 * @author 赵俊夫
 * @version V1.0
 * @Title:ConfigServiceI
 * @description:动态配置服务接口
 * @date Jul 5, 2013 3:02:11 PM
 */
public interface ConfigServiceI {
    /**
     * 读取动态表配置
     *
     * @param configId 标示配置的id
     * @return
     */
    public Map<String, Object> queryConfigs(String configId, String jversion);
}
