package org.jeecgframework.web.cgform.dao.config;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.springframework.stereotype.Repository;

/**
 * @author 赵俊夫
 * @version V1.0
 * @Title:CgFormFieldDao
 * @description:
 * @date Aug 24, 2013 11:33:33 AM
 */
@Repository("cgFormVersionDao")
public interface CgFormVersionDao {
    @Arguments("tableName")
    public String getCgFormVersionByTableName(String tableName);

    @Arguments("id")
    public String getCgFormVersionById(String id);

    @Arguments({"newVersion", "formId"})
    public void updateVersion(String newVersion, String formId);

    @Arguments({"id"})
    public CgFormHeadEntity getCgFormById(String id);
}
