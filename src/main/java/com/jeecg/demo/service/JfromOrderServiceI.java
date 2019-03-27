package com.jeecg.demo.service;

import com.jeecg.demo.entity.JfromOrderEntity;
import com.jeecg.demo.entity.JfromOrderLineEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.util.List;

public interface JfromOrderServiceI extends CommonService {

    @Override
    public <T> void delete(T entity);

    /**
     * 添加一对多
     */
    public void addMain(JfromOrderEntity jfromOrder,
                        List<JfromOrderLineEntity> jfromOrderLineList);

    /**
     * 修改一对多
     */
    public void updateMain(JfromOrderEntity jfromOrder, List<JfromOrderLineEntity> jfromOrderLineList);

    public void delMain(JfromOrderEntity jfromOrder);

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(JfromOrderEntity t);

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(JfromOrderEntity t);

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(JfromOrderEntity t);
}
