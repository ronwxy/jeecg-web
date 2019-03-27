package org.jeecgframework.tag.core.easyui;

import jodd.util.StringUtil;
import org.apache.log4j.Logger;
import org.jeecgframework.core.enums.SysThemesEnum;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.SysThemesUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.AbstractJeecgTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

/**
 * @author 张代浩
 */
public class BaseTag extends AbstractJeecgTag {
    private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(BaseTag.class);
    /**
     * 加载类型
     */
    protected String type = "default";
    protected String cssTheme;

    private final String jqueryWebos = "jquery-webos";
    private final String jquery = "jquery";
    private final String ckeditor = "ckeditor";
    private final String easyui = "easyui";
    private final String datePicker = "DatePicker";
    private final String jqueryui = "jqueryui";
    private final String jqueryuiSortable = "jqueryui-sortable";
    private final String prohibit = "prohibit";
    private final String tools = "tools";
    private final String toptip = "toptip";
    private final String autocomplete = "autocomplete";
    private final String jeasyuiextensions = "jeasyuiextensions";
    private final String ztree = "ztree";
    private final String bootstrap = "bootstrap";
    private final String bootstrapTable = "bootstrap-table";
    private final String layer = "layer";
    private final String aceform = "aceform";
    private final String webuploader = "webuploader";
    private final String ueditor = "ueditor";
    private final String uploadify = "uploadify";
    private final String bootstrapForm = "bootstrap-form";
    private final String validform = "validform";
    private final String echarts = "echarts";

    public String getCssTheme() {
        return cssTheme;
    }


    public void setCssTheme(String cssTheme) {
        this.cssTheme = cssTheme;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public int doStartTag() {
        return EVAL_PAGE;
    }


    @Override
    public int doEndTag() {
        JspWriter out = null;
        try {
            out = this.pageContext.getOut();
            out.print(end().toString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.clearBuffer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return EVAL_PAGE;
    }

    public StringBuffer end() {
        StringBuffer sb = this.getTagCache();
        if (sb != null) {
            return sb;
        }
        sb = new StringBuffer();
        String[] types = type.split(",");
        for (int i = 0; i < types.length; i++) {
            String type = types[i];
            types[i] = type.trim();
        }

        SysThemesEnum sysThemesEnum;
        String nullStr = "null";
        if (StringUtil.isEmpty(cssTheme) || nullStr.equals(cssTheme)) {
            sysThemesEnum = SysThemesUtil.getSysTheme((HttpServletRequest) super.pageContext.getRequest());
        } else {
            sysThemesEnum = SysThemesEnum.toEnum(cssTheme);
        }

        //插入多语言脚本
        HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
        String lang = (String) request.getSession().getAttribute("lang");
        String basePath = ResourceUtil.getBasePath();
        if (lang == null) {
            lang = "zh-cn";
        }
        String langjs = StringUtil.replace("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/mutiLang/{0}.js\"></script>", "{0}", lang);
        sb.append(langjs);
        appendPageLab(sb, types, sysThemesEnum, lang, basePath);

        types = null;
        this.putTagCache(sb);
        String curdtools = "plug-in/tools/curdtools.js";
        String i18nProperties = "/plug-in/jquery-plugs/i18n/jquery.i18n.properties.js";
        if (sb.indexOf(curdtools) != -1 && sb.indexOf(i18nProperties) == -1) {
            sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/jquery-plugs/i18n/jquery.i18n.properties.js\"></script>");
        }

        return sb;
    }

    private void appendPageLab(StringBuffer sb, String[] types, SysThemesEnum sysThemesEnum, String lang, String basePath) {
        if (oConvertUtils.isIn(jqueryWebos, types)) {
            appendJqueryWebos(sb, basePath);
        } else if (oConvertUtils.isIn(jquery, types)) {
            appendJquery(sb, basePath);
        }
        if (oConvertUtils.isIn(ckeditor, types)) {
            appendCkeditor(sb, basePath);
        }
        if (oConvertUtils.isIn(easyui, types)) {
            appendEasyui(sb, sysThemesEnum, lang, basePath);
        }
        if (oConvertUtils.isIn(datePicker, types)) {
            appendDatePicker(sb, basePath);
        }
        if (oConvertUtils.isIn(jqueryui, types)) {
            appendJqueryui(sb, basePath);
        }
        if (oConvertUtils.isIn(jqueryuiSortable, types)) {
            appendJqueryuiSortable(sb, basePath);
        }
        if (oConvertUtils.isIn(prohibit, types)) {
            appendProhibit(sb, basePath);
        }
        if (oConvertUtils.isIn(tools, types)) {
            appendTools(sb, sysThemesEnum, basePath);
        }
        if (oConvertUtils.isIn(toptip, types)) {
            appendToptip(sb, basePath);
        }
        if (oConvertUtils.isIn(autocomplete, types)) {
            appendAutocomplete(sb, basePath);
        }
        if (oConvertUtils.isIn(jeasyuiextensions, types)) {
            appendJeasyuiextensions(sb, basePath);
        }
        if (oConvertUtils.isIn(ztree, types)) {
            appendZtree(sb, basePath);
        }
        if (oConvertUtils.isIn(bootstrap, types)) {
            appendBootstrap(sb, basePath);
        }
        if (oConvertUtils.isIn(bootstrapTable, types)) {
            appendBootstrapTable(sb, basePath);
        }
        if (oConvertUtils.isIn(layer, types)) {
            appendLayer(sb, basePath);
        }
        if (oConvertUtils.isIn(aceform, types)) {
            appendAceform(sb, lang, basePath);
        }
        if (oConvertUtils.isIn(validform, types)) {
            appendValidform(sb, lang, basePath);
        }
        if (oConvertUtils.isIn(webuploader, types)) {
            appendWebuploader(sb, basePath);
        }
        if (oConvertUtils.isIn(bootstrapForm, types)) {
            appendBootstrapForm(sb, basePath);
        }
        if (oConvertUtils.isIn(ueditor, types)) {
            appendUeditor(sb, basePath);
        }
        if (oConvertUtils.isIn(uploadify, types)) {
            appendUploadify(sb, basePath);
        }
        if (oConvertUtils.isIn(echarts, types)) {
            appendEcharts(sb, basePath);
        }
    }

    private void appendEcharts(StringBuffer sb, String basePath) {
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/echart/echarts.js\"></script>");
    }

    private void appendUploadify(StringBuffer sb, String basePath) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/uploadify/css/uploadify.css\" type=\"text/css\"></link>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/uploadify/jquery.uploadify-3.1.js\"></script>");
    }

    private void appendUeditor(StringBuffer sb, String basePath) {
        sb.append("<script type=\"text/javascript\" charset=\"utf-8\" src=\"" + basePath + "/plug-in/ueditor/ueditor.config.js\"></script>");
        sb.append("<script type=\"text/javascript\" charset=\"utf-8\" src=\"" + basePath + "/plug-in/ueditor/ueditor.all.min.js\"></script>");
    }

    private void appendBootstrapForm(StringBuffer sb, String basePath) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/themes/bootstrap-ext/css/validform-ext.css\" type=\"text/css\"></link>");
        //icheck组件引用

        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/hplus/css/plugins/iCheck/custom.css\" type=\"text/css\"></link>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/hplus/js/plugins/iCheck/icheck.min.js\"></script>");

        //通用组件引用
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/bootstrap3.3.5/css/default.css\" type=\"text/css\"></link>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/themes/bootstrap-ext/js/common.js\"></script>");
        //自定义form样式-一定要放在最后引入
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/bootstrap3.3.5/css/bootstrap-form.css\" type=\"text/css\"></link>");
    }

    private void appendWebuploader(StringBuffer sb, String basePath) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/webuploader/custom.css\" type=\"text/css\"></link>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/webuploader/webuploader.min.js\"></script>");
    }

    private void appendValidform(StringBuffer sb, String lang, String basePath) {
        sb.append(StringUtil.replace("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/Validform/js/Validform_v5.3.1_min_{0}.js\"></script>", "{0}", lang));
        sb.append(StringUtil.replace("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/Validform/js/Validform_Datatype_{0}.js\"></script>", "{0}", lang));
        sb.append(StringUtil.replace("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/Validform/js/datatype_{0}.js\"></script>", "{0}", lang));
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js\"></script>");
    }

    private void appendAceform(StringBuffer sb, String lang, String basePath) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/online/template/ledefault/css/vendor.css\" type=\"text/css\"></link>");
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/online/template/ledefault/css/bootstrap-theme.css\" type=\"text/css\"></link>");
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/online/template/ledefault/css/bootstrap.css\" type=\"text/css\"></link>");
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/online/template/ledefault/css/app.css\" type=\"text/css\"></link>");
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/Validform/css/metrole/style.css\" type=\"text/css\"></link>");
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/Validform/css/metrole/tablefrom.css\" type=\"text/css\"></link>");
        //easyui的使用经快速测试唯一用到就只有单表为树形列表时，需要用到combotree插件
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/easyui/jquery.easyui.min.1.3.2.js\"></script>");
        sb.append(StringUtil.replace("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/easyui/locale/{0}.js\"></script>", "{0}", lang));
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/tools/syUtil.js\"></script>");
        //一对多子表的选择文件弹框
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/lhgDialog/lhgdialog.min.js\"></script>");
        //大量js工具函数
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/tools/curdtools.js\"></script>");
    }

    private void appendLayer(StringBuffer sb, String basePath) {
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/layer/layer.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/laydate/laydate.js\"></script>");
    }

    private void appendBootstrapTable(StringBuffer sb, String basePath) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/bootstrap-table/bootstrap-table.min.css\" type=\"text/css\"></link>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/bootstrap-table/bootstrap-table.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/bootstrap-table/locale/bootstrap-table-zh-CN.min.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/themes/bootstrap-ext/js/common.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/tools/dataformat.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/lhgDialog/lhgdialog.min.js?skin=metrole\"></script>");

        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/themes/bootstrap-ext/js/bootstrap-lhgdialog-curdtools.js\"></script>");

        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/ace/css/common.css\" type=\"text/css\"></link>");
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/ace/css/font-awesome.css\" type=\"text/css\"></link>");
    }

    private void appendBootstrap(StringBuffer sb, String basePath) {
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/jquery/jquery-1.9.1.js\"></script>");
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/bootstrap3.3.5/css/bootstrap.min.css\" type=\"text/css\"></link>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/bootstrap3.3.5/js/bootstrap.min.js\"></script>");
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/bootstrap3.3.5/css/default.css\" type=\"text/css\"></link>");
    }

    private void appendZtree(StringBuffer sb, String basePath) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/ztree/css/metroStyle.css\" type=\"text/css\"></link>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/ztree/js/jquery.ztree.core-3.5.min.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js\"></script>");
    }

    private void appendJeasyuiextensions(StringBuffer sb, String basePath) {
        sb.append("<script src=\"" + basePath + "/plug-in/jquery-extensions/release/jquery.jdirk.min.js\" type=\"text/javascript\"></script>");
        sb.append("<link href=\"" + basePath + "/plug-in/jquery-extensions/icons/icon-all.css\" rel=\"stylesheet\" type=\"text/css\" />");
        sb.append("<link href=\"" + basePath + "/plug-in/jquery-extensions/jeasyui-extensions/jeasyui.extensions.css\" rel=\"stylesheet\" type=\"text/css\" />");
        sb.append("<script src=\"" + basePath + "/plug-in/jquery-extensions/jeasyui-extensions/jeasyui.extensions.js\" type=\"text/javascript\"></script>");
        sb.append("<script src=\"" + basePath + "/plug-in/jquery-extensions/jeasyui-extensions/jeasyui.extensions.linkbutton.js\" type=\"text/javascript\"></script>");
        sb.append("<script src=\"" + basePath + "/plug-in/jquery-extensions/jeasyui-extensions/jeasyui.extensions.menu.js\" type=\"text/javascript\"></script>");
        sb.append("<script src=\"" + basePath + "/plug-in/jquery-extensions/jeasyui-extensions/jeasyui.extensions.panel.js\" type=\"text/javascript\"></script>");
        sb.append("<script src=\"" + basePath + "/plug-in/jquery-extensions/jeasyui-extensions/jeasyui.extensions.window.js\" type=\"text/javascript\"></script>");
        sb.append("<script src=\"" + basePath + "/plug-in/jquery-extensions/jeasyui-extensions/jeasyui.extensions.dialog.js\" type=\"text/javascript\"></script>");
        sb.append("<script src=\"" + basePath + "/plug-in/jquery-extensions/jeasyui-extensions/jeasyui.extensions.datagrid.js\" type=\"text/javascript\"></script>");
    }

    private void appendAutocomplete(StringBuffer sb, String basePath) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/jquery/jquery-autocomplete/jquery.autocomplete.css\" type=\"text/css\"></link>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/jquery/jquery-autocomplete/jquery.autocomplete.min.js\"></script>");
    }

    private void appendToptip(StringBuffer sb, String basePath) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/toptip/css/css.css\" type=\"text/css\"></link>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/toptip/manhua_msgTips.js\"></script>");
    }

    private void appendTools(StringBuffer sb, SysThemesEnum sysThemesEnum, String basePath) {
        sb.append(SysThemesUtil.getCommonTheme(sysThemesEnum));
        sb.append(SysThemesUtil.getLhgdialogTheme(sysThemesEnum));
        sb.append(SysThemesUtil.getBootstrapTabTheme(sysThemesEnum));
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/layer/layer.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/tools/curdtools.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/tools/easyuiextend.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/jquery-plugs/hftable/jquery-hftable.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/tools/json2.js\" ></script>");
    }

    private void appendProhibit(StringBuffer sb, String basePath) {
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/tools/prohibitutil.js\"></script>");
    }

    private void appendJqueryuiSortable(StringBuffer sb, String basePath) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/jquery-ui/css/ui-lightness/jquery-ui-1.9.2.custom.min.css\" type=\"text/css\"></link>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/jquery-ui/js/ui/jquery.ui.core.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/jquery-ui/js/ui/jquery.ui.widget.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/jquery-ui/js/ui/jquery.ui.mouse.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/jquery-ui/js/ui/jquery.ui.sortable.js\"></script>");
    }

    private void appendJqueryui(StringBuffer sb, String basePath) {
        sb.append("<link rel=\"stylesheet\" href=\"" + basePath + "/plug-in/jquery-ui/css/ui-lightness/jquery-ui-1.9.2.custom.min.css\" type=\"text/css\"></link>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/jquery-ui/js/jquery-ui-1.9.2.custom.min.js\"></script>");
    }

    private void appendDatePicker(StringBuffer sb, String basePath) {
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/My97DatePicker/WdatePicker.js\"></script>");
    }

    private void appendEasyui(StringBuffer sb, SysThemesEnum sysThemesEnum, String lang, String basePath) {
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/tools/dataformat.js\"></script>");
        sb.append(SysThemesUtil.getEasyUiTheme(sysThemesEnum));
        sb.append(SysThemesUtil.getEasyUiMainTheme(sysThemesEnum));
        sb.append(SysThemesUtil.getEasyUiIconTheme(sysThemesEnum));
        sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + basePath + "/plug-in/accordion/css/accordion.css\">");
        sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + basePath + "/plug-in/accordion/css/icons.css\">");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/easyui/jquery.easyui.min.1.3.2.js\"></script>");
        sb.append(StringUtil.replace("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/easyui/locale/{0}.js\"></script>", "{0}", lang));
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/tools/syUtil.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/easyui/extends/datagrid-scrollview.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/mutitables/datagrid-filter.js\"></script>");
    }

    private void appendCkeditor(StringBuffer sb, String basePath) {
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/ckeditor/ckeditor.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/tools/ckeditorTool.js\"></script>");
    }

    private void appendJquery(StringBuffer sb, String basePath) {
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/jquery/jquery-1.8.3.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/jquery/jquery.cookie.js\" ></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/jquery-plugs/storage/jquery.storageapi.min.js\" ></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/jquery-plugs/i18n/jquery.i18n.properties.js\"></script>");
    }

    private void appendJqueryWebos(StringBuffer sb, String basePath) {
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/sliding/js/jquery-1.7.1.min.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"" + basePath + "/plug-in/jquery-plugs/i18n/jquery.i18n.properties.js\"></script>");
    }


    @Override
    public String toString() {
        String basePath = ResourceUtil.getBasePath();
        return new StringBuffer().append("BaseTag [type=").append(type)
                .append(",sysTheme=").append(SysThemesUtil.getSysTheme(ContextHolderUtils.getRequest()).getStyle())
                .append(",brower_type=").append(ContextHolderUtils.getSession().getAttribute("brower_type"))
                .append(",cssTheme=").append(cssTheme)
                .append(",basePath=").append(basePath)
                .append("]").toString();
    }

}
