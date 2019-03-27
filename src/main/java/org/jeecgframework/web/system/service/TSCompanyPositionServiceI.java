package org.jeecgframework.web.system.service;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSCompanyPositionEntity;

import java.io.Serializable;

public interface TSCompanyPositionServiceI extends CommonService {

    public void delete(TSCompanyPositionEntity entity) throws Exception;

    public Serializable save(TSCompanyPositionEntity entity) throws Exception;

    public void saveOrUpdate(TSCompanyPositionEntity entity) throws Exception;

}
