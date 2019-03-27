package org.jeecgframework.web.cgform.service.button;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.button.CgformButtonEntity;

import java.util.List;

/**
 * @author 张代浩
 */
public interface CgformButtonServiceI extends CommonService {

    /**
     * 查询按钮list
     *
     * @param formId
     * @return
     */
    public List<CgformButtonEntity> getCgformButtonListByFormId(String formId);

    /**
     * 校验按钮唯一性
     *
     * @param cgformButtonEntity
     * @return
     */
    public List<CgformButtonEntity> checkCgformButton(CgformButtonEntity cgformButtonEntity);


}
