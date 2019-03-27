package org.jeecgframework.web.cgform.enhance;

import org.jeecgframework.core.common.exception.BusinessException;

import java.util.Map;

/**
 * JAVA增强
 *
 * @author luobaoli
 */
public interface CgformEnhanceJavaInter {
    /**
     * @param tableName 数据库表名
     * @param map 表单数据
     */

    public void execute(String tableName, Map map) throws BusinessException;

}
