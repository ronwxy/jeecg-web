package com.jeecg.demo.service;

import com.jeecg.demo.entity.JeecgDemoExcelEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface JeecgDemoExcelServiceI extends CommonService {

    public void delete(JeecgDemoExcelEntity entity) throws Exception;

    public Serializable save(JeecgDemoExcelEntity entity) throws Exception;

    public void saveOrUpdate(JeecgDemoExcelEntity entity) throws Exception;

}
