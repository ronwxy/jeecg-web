package org.jeecgframework.web.cgform.engine.tag;

import freemarker.core.Environment;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * 自定义Online自定义按钮
 * 使用方法 ：<@exp exp='${x['exp']}' data='name' >
 *
 * @author gj_shaojc
 */
@Component("expTag")
public class ExpTag implements TemplateDirectiveModel {

    private static final Logger LOG = LoggerFactory.getLogger(ExpTag.class);

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
                        TemplateDirectiveBody body) throws TemplateException, IOException {

        String exp = getAttribute(params, "exp");
        String data = getAttribute(params, "data");
        if (exp == null || data == null) {
            throw new TemplateException(
                    "Can not find attribute 'name' in data tag", env);
        }
        StringBuilder resExp = new StringBuilder();
        String[] showByFields = exp.split("&&");
        for (String showByField : showByFields) {
            int beginIndex = showByField.indexOf("#");
            int endIndex = showByField.lastIndexOf("#");
            String exptype = showByField.substring(beginIndex + 1, endIndex);// 表达式类型
            String field = showByField.substring(0, beginIndex);// 判断显示依据字段
            String[] values = showByField.substring(endIndex + 1, showByField.length()).split(",");// 传入字段值
            String value = "";
            for (int i = 0; i < values.length; i++) {
                value += "'" + "" + values[i] + "" + "'";
                if (i < values.length - 1) {
                    value += ",";
                }
            }
            if ("eq".equals(exptype)) {
                resExp.append("$.inArray(" + data + "." + field + ",[" + value + "])>=0");
            }
            if ("ne".equals(exptype)) {
                resExp.append("$.inArray(" + data + "." + field + ",[" + value + "])<0");
            }
            if ("empty".equals(exptype) && "'true'".equals(value)) {
                resExp.append("" + data + "." + field + "==''");
            }
            if ("empty".equals(exptype) && "'false'".equals(value)) {
                resExp.append("" + data + "." + field + "!=''");
            }
        }
        Writer out = env.getOut();
        out.append(resExp);
    }

    /**
     * 取得标签参数
     *
     * @param params
     * @param name
     * @return
     */
    @SuppressWarnings("unchecked")
    private String getAttribute(Map params, String name) {
        String value = null;
        if (params.containsKey(name)) {
            TemplateModel paramValue = (TemplateModel) params.get(name);
            try {
                value = ((TemplateScalarModel) paramValue).getAsString();
            } catch (TemplateModelException e) {
                LOG.error("get attribute '{}' error", name, e);
            }
        }
        return value;
    }
}
