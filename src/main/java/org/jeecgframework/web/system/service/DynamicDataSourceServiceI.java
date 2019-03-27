package org.jeecgframework.web.system.service;

import org.jeecgframework.web.system.pojo.base.DynamicDataSourceEntity;

import java.util.List;

public interface DynamicDataSourceServiceI {

    public List<DynamicDataSourceEntity> initDynamicDataSource();

    public void refleshCache();


    public DynamicDataSourceEntity getDynamicDataSourceEntityForDbKey(String dbKey);


}
