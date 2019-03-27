package org.jeecgframework.core.common.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.Collection;

/**
 * @author 赵俊夫
 * @version V1.0
 * @Title:CgReportExcelServiceI
 * @description:动态报表excel导出
 * @date Aug 1, 2013 8:53:54 AM
 */
public interface CommonExcelServiceI extends CommonService {
    /**
     * @param title 标题
     * @param titleSet 报表头
     * @param dataSet 报表内容
     * @return
     */
    public HSSFWorkbook exportExcel(String title, Collection<?> titleSet, Collection<?> dataSet);
}
