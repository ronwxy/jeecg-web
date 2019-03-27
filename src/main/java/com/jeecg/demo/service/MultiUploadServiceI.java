package com.jeecg.demo.service;

import com.jeecg.demo.entity.MultiUploadEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface MultiUploadServiceI extends CommonService {

    public void delete(MultiUploadEntity entity) throws Exception;

    public Serializable save(MultiUploadEntity entity) throws Exception;

    public void saveOrUpdate(MultiUploadEntity entity) throws Exception;

}
