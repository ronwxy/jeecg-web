package com.jeecg.demo.service.impl;

import com.jeecg.demo.entity.JeecgDemoExcelEntity;
import com.jeecg.demo.service.JeecgDemoExcelServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service("jeecgDemoExcelService")
@Transactional
public class JeecgDemoExcelServiceImpl extends CommonServiceImpl implements JeecgDemoExcelServiceI {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void delete(JeecgDemoExcelEntity entity) throws Exception {
        super.delete(entity);
    }

    @Override
    public Serializable save(JeecgDemoExcelEntity entity) throws Exception {
        Serializable t = super.save(entity);
        return t;
    }

    @Override
    public void saveOrUpdate(JeecgDemoExcelEntity entity) throws Exception {
        super.saveOrUpdate(entity);
    }

}