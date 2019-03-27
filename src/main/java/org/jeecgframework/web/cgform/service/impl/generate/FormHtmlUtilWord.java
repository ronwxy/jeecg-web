package org.jeecgframework.web.cgform.service.impl.generate;

import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;

/**
 * @author 张代浩
 * @version V1.0
 * @date 2013-08-11 09:47:30
 */
public class FormHtmlUtilWord {

    /**
     * 根据CgFormFieldEntity表属性配置，返回表单HTML代码
     *
     * @throws Exception
     */
    public static String getFormHTML(CgFormFieldEntity cgFormFieldEntityOrig, String tableName) throws Exception {
        String html = "";
        CgFormFieldEntity cgFormFieldEntity = new CgFormFieldEntity();
        MyBeanUtils.copyBean2Bean(cgFormFieldEntity, cgFormFieldEntityOrig);
        String fieldName = oConvertUtils.camelName(cgFormFieldEntity.getFieldName());
        cgFormFieldEntity.setFieldName(fieldName);
        if ("text".equals(cgFormFieldEntity.getShowType())) {
            if ("only".equalsIgnoreCase(cgFormFieldEntity.getFieldValidType())) {
                html = getTextOnlyFormHtml(cgFormFieldEntityOrig, tableName);
            } else {
                html = getTextFormHtml(cgFormFieldEntity);
            }
        } else if ("password".equals(cgFormFieldEntity.getShowType())) {
            html = getPwdFormHtml(cgFormFieldEntity);
        } else if ("radio".equals(cgFormFieldEntity.getShowType())) {
            html = getRadioFormHtml(cgFormFieldEntity);
        } else if ("checkbox".equals(cgFormFieldEntity.getShowType())) {
            html = getCheckboxFormHtml(cgFormFieldEntity);
        } else if ("list".equals(cgFormFieldEntity.getShowType())) {
            html = getListFormHtml(cgFormFieldEntity);
        } else if ("date".equals(cgFormFieldEntity.getShowType())) {
            html = getDateFormHtml(cgFormFieldEntity);
        } else if ("datetime".equals(cgFormFieldEntity.getShowType())) {
            html = getDatetimeFormHtml(cgFormFieldEntity);
        } else if ("file".equals(cgFormFieldEntity.getShowType())) {
            html = getFileFormHtml(cgFormFieldEntity);
        } else if ("textarea".equals(cgFormFieldEntity.getShowType())) {
            html = getTextAreaFormHtml(cgFormFieldEntity);
        } else if ("popup".equals(cgFormFieldEntity.getShowType())) {
            html = getPopupFormHtml(cgFormFieldEntity);
        } else {
            html = getTextFormHtml(cgFormFieldEntity);
        }
        return html;
    }

    /**
     * 返回textarea的表单html
     *
     * @param cgFormFieldEntity
     * @return style="width: 300px" class="inputxt" rows="6"
     */
    private static String getTextAreaFormHtml(
            CgFormFieldEntity cgFormFieldEntity) {
        StringBuilder html = new StringBuilder("");
        html.append("<textarea  style=\"width: 300px\" rows=\"6\" ");
        html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
        html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
        if ("Y".equals(cgFormFieldEntity.getIsNull())) {
            html.append("ignore=\"ignore\" ");
        }
        if (cgFormFieldEntity.getFieldValidType() != null && cgFormFieldEntity.getFieldValidType().length() > 0) {
            html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
        } else {
            html.append("datatype=\"*\" ");
        }
        html.append("\\>");
        html.append("\\${").append(cgFormFieldEntity.getFieldName()).append("?if_exists?html}</textarea> ");
        return html.toString();
    }

    /**
     * 返回text类型的表单html
     */
    private static String getTextFormHtml(CgFormFieldEntity cgFormFieldEntity) {
        StringBuilder html = new StringBuilder("");
        html.append("<input type=\"text\" ");
        html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
        html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
        if (cgFormFieldEntity.getFieldLength() != null && cgFormFieldEntity.getFieldLength() > 0) {
            html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
        }
        html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(cgFormFieldEntity.getFieldName()).append("}\" ");
        if ("Y".equals(cgFormFieldEntity.getIsNull())) {
            html.append("ignore=\"ignore\" ");
        }
        if (cgFormFieldEntity.getFieldValidType() != null && cgFormFieldEntity.getFieldValidType().length() > 0) {
            html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
        } else {
            if ("int".equals(cgFormFieldEntity.getType())) {
                html.append("datatype=\"n\" ");
            } else if ("double".equals(cgFormFieldEntity.getType())) {
                html.append("datatype=\"\\/^(-?\\\\d+)(\\\\.\\\\d+)?\\$\\/\" ");
            } else {
                html.append("datatype=\"*\" ");
            }
        }
        html.append("\\/>");
        return html.toString();
    }

    /**
     * 返回text类型的表单html(唯一值校验)
     */
    private static String getTextOnlyFormHtml(CgFormFieldEntity cgFormFieldEntity, String tableName) {
        String fieldName = oConvertUtils.camelName(cgFormFieldEntity.getFieldName());
        StringBuilder html = new StringBuilder("");
        html.append("<input type=\"text\" ");
        html.append("id=\"").append(fieldName).append("\" ");
        html.append("name=\"").append(fieldName).append("\" ");
        if (cgFormFieldEntity.getFieldLength() != null && cgFormFieldEntity.getFieldLength() > 0) {
            html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
        }
        html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(fieldName).append("}\" ");
        if ("Y".equals(cgFormFieldEntity.getIsNull())) {
            html.append("ignore=\"ignore\" ");
        } else {
            html.append("ignore=\"checked\" ");
        }
        html.append("validtype=\"").append(tableName).append(",").append(cgFormFieldEntity.getFieldName()).append(",id\" ");
        html.append("datatype=\"*\" ");
        html.append("\\/>");
        return html.toString();
    }


    /**
     * 返回password类型的表单html
     */
    private static String getPwdFormHtml(CgFormFieldEntity cgFormFieldEntity) {
        StringBuilder html = new StringBuilder("");
        html.append("<input type=\"password\" ");
        html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
        html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
        if (cgFormFieldEntity.getFieldLength() != null && cgFormFieldEntity.getFieldLength() > 0) {
            html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
        }
        html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(cgFormFieldEntity.getFieldName()).append("}\" ");
        if ("Y".equals(cgFormFieldEntity.getIsNull())) {
            html.append("ignore=\"ignore\" ");
        }
        if (cgFormFieldEntity.getFieldValidType() != null && cgFormFieldEntity.getFieldValidType().length() > 0) {
            html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
        } else {
            html.append("datatype=\"*\" ");
        }
        html.append("\\/>");
        return html.toString();
    }


    /**
     * 返回radio类型的表单html
     */
    private static String getRadioFormHtml(CgFormFieldEntity cgFormFieldEntity) {

        if (StringUtil.isEmpty(cgFormFieldEntity.getDictField())) {
            return getTextFormHtml(cgFormFieldEntity);
        } else {
            StringBuilder html = new StringBuilder("");
            html.append("<@DictData name=\"" + cgFormFieldEntity.getDictField() + "\"");
            if (!StringUtil.isEmpty(cgFormFieldEntity.getDictTable())) {
                html.append(" tablename=\"" + cgFormFieldEntity.getDictTable() + "\"");
            }
            html.append(" var=\"dictDataList\">");
            html.append("<#list dictDataList as dictdata>");
            html.append(" <input type=\"radio\" value=\"\\${dictdata.typecode?if_exists?html}\" name=\"" + cgFormFieldEntity.getFieldName() + "\" ");

            html.append("<c:if test=\"@@@{onlineCodeGenereateEntityKey." + cgFormFieldEntity.getFieldName() + "=='\\${dictdata.typecode?if_exists?html}'}\" >");
            html.append(" checked=\"true\" ");
            html.append("</c:if>");

            html.append(">");
            html.append("\\${dictdata.typename?if_exists?html}");
            html.append("</#list> ");
            html.append("</@DictData> ");
            return html.toString();
        }
    }


    /**
     * 返回checkbox类型的表单html ${data['${po.field_name}']?if_exists?html}
     */
    private static String getCheckboxFormHtml(CgFormFieldEntity cgFormFieldEntity) {
        if (StringUtil.isEmpty(cgFormFieldEntity.getDictField())) {
            return getTextFormHtml(cgFormFieldEntity);
        } else {
            StringBuilder html = new StringBuilder("");
            html.append("<#assign checkboxstr>\\${data['").append(cgFormFieldEntity.getFieldName()).append("']?if_exists?html}</#assign>");
            html.append("<#assign checkboxlist=checkboxstr?split(\",\")> ");
            html.append("<@DictData name=\"" + cgFormFieldEntity.getDictField() + "\"");
            if (!StringUtil.isEmpty(cgFormFieldEntity.getDictTable())) {
                html.append(" tablename=\"" + cgFormFieldEntity.getDictTable() + "\"");
            }
            html.append(" var=\"dictDataList\">");
            html.append("<#list dictDataList as dictdata>");
            html.append(" <input type=\"checkbox\" value=\"\\${dictdata.typecode?if_exists?html}\" name=\"" + cgFormFieldEntity.getFieldName() + "\" ");

            html.append("<c:if test=\"@@@{onlineCodeGenereateEntityKey." + cgFormFieldEntity.getFieldName() + "=='\\${dictdata.typecode?if_exists?html}'}\" >");
            html.append(" checked=\"true\" ");
            html.append("</c:if>");

            html.append("<#if dictdata.typecode=='\\${").append(cgFormFieldEntity.getFieldName()).append("?if_exists?html}'>");
            html.append(" checked=\"true\" ");
            html.append("</#if> ");

            html.append(">");
            html.append("\\${dictdata.typename?if_exists?html}");
            html.append("</#list> ");
            html.append("</@DictData> ");
            return html.toString();
        }
    }


    /**
     * 返回Select类型的表单html
     */
    private static String getListFormHtml(CgFormFieldEntity cgFormFieldEntity) {
        if (StringUtil.isEmpty(cgFormFieldEntity.getDictField())) {
            return getTextFormHtml(cgFormFieldEntity);
        } else {
            StringBuilder html = new StringBuilder("");
            html.append("<@DictData name=\"" + cgFormFieldEntity.getDictField() + "\"");
            if (!StringUtil.isEmpty(cgFormFieldEntity.getDictText())) {
                html.append(" text=\"" + cgFormFieldEntity.getDictText() + "\"");
            }
            if (!StringUtil.isEmpty(cgFormFieldEntity.getDictTable())) {
                html.append(" tablename=\"" + cgFormFieldEntity.getDictTable() + "\"");
            }
            html.append(" var=\"dictDataList\">");
            html.append("<select name=\"" + cgFormFieldEntity.getFieldName() + "\" id=\"" + cgFormFieldEntity.getFieldName() + "\"> ");
            html.append("<#list dictDataList as dictdata>");
            html.append(" <option value=\"\\${dictdata.typecode?if_exists?html}\" ");

            html.append("<c:if test=\"@@@{onlineCodeGenereateEntityKey." + cgFormFieldEntity.getFieldName() + "=='\\${dictdata.typecode?if_exists?html}'}\" >");
            html.append(" selected=\"selected\" ");
            html.append("</c:if>");

            html.append(">");
            html.append("\\${dictdata.typename?if_exists?html}");
            html.append("</option> ");
            html.append("</#list> ");
            html.append("</select>");
            html.append("</@DictData> ");
            return html.toString();
        }
    }


    /**
     * 返回date类型的表单html
     */
    private static String getDateFormHtml(CgFormFieldEntity cgFormFieldEntity) {
        StringBuilder html = new StringBuilder("");
        html.append("<input type=\"text\" ");
        html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
        html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
        html.append("class=\"Wdate\" ");
        html.append("onClick=\"WdatePicker()\" ");
        if (cgFormFieldEntity.getFieldLength() != null && cgFormFieldEntity.getFieldLength() > 0) {
            html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
        }
        html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(cgFormFieldEntity.getFieldName()).append("}\" ");
        if ("Y".equals(cgFormFieldEntity.getIsNull())) {
            html.append("ignore=\"ignore\" ");
        }
        if (cgFormFieldEntity.getFieldValidType() != null && cgFormFieldEntity.getFieldValidType().length() > 0) {
            html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
        } else {
            html.append("datatype=\"*\" ");
        }
        html.append("\\/>");
        return html.toString();
    }

    /**
     * 返回datetime类型的表单html
     */
    private static String getDatetimeFormHtml(CgFormFieldEntity cgFormFieldEntity) {
        StringBuilder html = new StringBuilder("");
        html.append("<input type=\"text\" ");
        html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
        html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
        html.append("class=\"Wdate\" ");
        html.append("onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\" ");
        if (cgFormFieldEntity.getFieldLength() != null && cgFormFieldEntity.getFieldLength() > 0) {
            html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
        }
        html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(cgFormFieldEntity.getFieldName()).append("}\" ");
        if ("Y".equals(cgFormFieldEntity.getIsNull())) {
            html.append("ignore=\"ignore\" ");
        }
        if (cgFormFieldEntity.getFieldValidType() != null && cgFormFieldEntity.getFieldValidType().length() > 0) {
            html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
        } else {
            html.append("datatype=\"*\" ");
        }
        html.append("\\/>");
        return html.toString();
    }

    /**
     * 返回file类型的表单html
     */
    private static String getFileFormHtml(CgFormFieldEntity cgFormFieldEntity) {
        StringBuilder html = new StringBuilder("");
        html.append("<input type=\"text\" ");
        html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
        html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
        if (cgFormFieldEntity.getFieldLength() != null && cgFormFieldEntity.getFieldLength() > 0) {
            html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
        }
        html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(cgFormFieldEntity.getFieldName()).append("}\" ");
        html.append("\\/>");
        return html.toString();
    }


    /**
     * 返回popup类型的表单html
     */
    private static String getPopupFormHtml(CgFormFieldEntity cgFormFieldEntity) {
        StringBuilder html = new StringBuilder("");
        html.append("<input type=\"text\" readonly=\"readonly\" class=\"searchbox-inputtext\" ");
        html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
        html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
        if (cgFormFieldEntity.getFieldLength() != null && cgFormFieldEntity.getFieldLength() > 0) {
            html.append("style=\"width:").append(cgFormFieldEntity.getFieldLength()).append("px\" ");
        }
        html.append("value=\"\\@{onlineCodeGenereateEntityKey@.").append(cgFormFieldEntity.getFieldName()).append("}\" ");

        html.append("onclick=\"popupClick(this,'" + cgFormFieldEntity.getDictText() + "','" + cgFormFieldEntity.getDictField() + "','" + cgFormFieldEntity.getDictTable() + "');\" ");

        if ("Y".equals(cgFormFieldEntity.getIsNull())) {
            html.append("ignore=\"ignore\" ");
        }
        if (cgFormFieldEntity.getFieldValidType() != null && cgFormFieldEntity.getFieldValidType().length() > 0) {
            html.append("datatype=\"").append(cgFormFieldEntity.getFieldValidType()).append("\" ");
        } else {
            html.append("datatype=\"*\" ");
        }
        html.append("\\/>");
        return html.toString();
    }
}
