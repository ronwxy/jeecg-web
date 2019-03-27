package org.jeecgframework.web.cgform.service.button;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.button.CgformButtonSqlEntity;

import java.util.List;

public interface CgformButtonSqlServiceI extends CommonService {

    /**
     * 校验按钮sql增强唯一性
     *
     * @param cgformButtonEntity
     * @return
     */
    public List<CgformButtonSqlEntity> checkCgformButtonSql(CgformButtonSqlEntity cgformButtonSqlEntity);

    /**
     * 根据buttonCode和formId初始化数据
     *
     * @param buttonCode
     * @param formId
     * @return
     */
    public CgformButtonSqlEntity getCgformButtonSqlByCodeFormId(String buttonCode, String formId);
}
