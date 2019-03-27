package org.jeecgframework.web.cgform.service.impl.button;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.cgform.entity.button.CgformButtonEntity;
import org.jeecgframework.web.cgform.service.button.CgformButtonServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("cgformButtonService")
@Transactional
public class CgformButtonServiceImpl extends CommonServiceImpl implements CgformButtonServiceI {

    /**
     * 查询按钮list
     *
     * @param formId
     * @return
     */

    @Override
    public List<CgformButtonEntity> getCgformButtonListByFormId(String formId) {
        StringBuilder hql = new StringBuilder("");
        hql.append(" from CgformButtonEntity t");
        hql.append(" where t.formId=?0 order by t.orderNum asc");
        List<CgformButtonEntity> list = this.findHql(hql.toString(), formId);
        return list;
    }

    /**
     * 校验按钮唯一性
     *
     * @param cgformButtonEntity
     * @return
     */

    @Override
    public List<CgformButtonEntity> checkCgformButton(CgformButtonEntity cgformButtonEntity) {
        StringBuilder hql = new StringBuilder("");
        hql.append(" from CgformButtonEntity t");

        hql.append(" where t.formId=?0");
        hql.append(" and  t.buttonCode =?1");
        List<CgformButtonEntity> list = null;
        if (cgformButtonEntity.getId() != null) {
            hql.append(" and t.id !=?2");
            list = this.findHql(hql.toString(), cgformButtonEntity.getFormId(), cgformButtonEntity.getButtonCode(), cgformButtonEntity.getId());
        } else {
            list = this.findHql(hql.toString(), cgformButtonEntity.getFormId(), cgformButtonEntity.getButtonCode());
        }

        return list;
    }

}