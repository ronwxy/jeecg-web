package org.jeecgframework.core.util;

import org.jeecgframework.web.system.pojo.base.TSFunction;

import java.util.Comparator;


/**
 * @author 张代浩
 * @ClassName: SetListSort
 * @Description: TODO(int比较器)
 * @date 2013-1-31 下午06:19:03
 */
public class SetListSort implements Comparator {
    /**
     * 菜单排序比较器
     */
    @Override
    public int compare(Object o1, Object o2) {
        TSFunction c1 = (TSFunction) o1;
        TSFunction c2 = (TSFunction) o2;
        if (c1.getFunctionOrder() != null && c2.getFunctionOrder() != null) {
            int c1order = oConvertUtils.getInt(c1.getFunctionOrder().substring(c1.getFunctionOrder().indexOf("fun") + 3));
            int c2order = oConvertUtils.getInt(c2.getFunctionOrder().substring(c2.getFunctionOrder().indexOf("fun")) + 3);
            if (c1order > c2order) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return 1;
        }

    }
}