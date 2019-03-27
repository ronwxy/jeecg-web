package org.jeecgframework.web.cgform.service.impl.enhance;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJsEntity;
import org.jeecgframework.web.cgform.service.enhance.CgformEnhanceJsServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("cgformenhanceJsService")
@Transactional
public class CgformEnhanceJsServiceImpl extends CommonServiceImpl implements CgformEnhanceJsServiceI {

    /**
     * 根据cgJsType和formId查找数据
     *
     * @param cgJsType
     * @param formId
     * @return
     */

    @Override
    public CgformEnhanceJsEntity getCgformEnhanceJsByTypeFormId(String cgJsType, String formId) {
        StringBuilder hql = new StringBuilder("");
        hql.append(" from CgformEnhanceJsEntity t");

        hql.append(" where t.formId=?0");
        hql.append(" and  t.cgJsType =?1");
        List<CgformEnhanceJsEntity> list = this.findHql(hql.toString(), formId, cgJsType);

        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}