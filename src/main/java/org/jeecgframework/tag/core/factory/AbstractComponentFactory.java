package org.jeecgframework.tag.core.factory;

import org.jeecgframework.core.online.util.FreemarkerHelper;
import org.jeecgframework.tag.core.easyui.DataGridTag;

import java.util.Map;

/**
 * @author zhoujf
 * @version V1.0
 */
public abstract class AbstractComponentFactory {


    public String invoke(String templatePath, DataGridTag dataGridTag) throws Exception {
        FreemarkerHelper free = new FreemarkerHelper();
        Map<String, Object> data = getData(dataGridTag);
        String content = free.parseTemplate(templatePath, data);
        return content;
    }


    protected abstract Map<String, Object> getData(DataGridTag dataGridTag);

}
