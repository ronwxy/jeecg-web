package org.jeecgframework.tag.core.easyui;

import com.google.gson.Gson;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.UUIDGenerator;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Map;


/**
 * 类描述：选择器标签
 *
 * @version 1.0
 * @author: 张代浩
 * @date： 日期：2012-12-7 时间：上午10:17:45
 */
public class ChooseTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    protected String hiddenName;
    /**
     * 显示文本框字段
     */
    protected String textname;
    protected String icon;
    protected String title;
    protected String url;
    protected String top;
    protected String left;
    protected String width;
    protected String height;
    protected String name;
    /**
     * 隐藏框取值ID
     */
    protected String hiddenid;
    protected Boolean isclear = false;
    /**
     * 自定义函数
     */
    protected String fun;
    protected String inputTextname;
    protected String langArg;
    /**
     * 是否初始化
     */
    protected Boolean isInit = false;
    /**
     * 扩展参数
     */
    private String extendButtonJson;


    @Override
    public int doStartTag() throws JspTagException {
        return EVAL_PAGE;
    }

    @Override
    public int doEndTag() throws JspTagException {
        JspWriter out = null;
        try {
            title = MutiLangUtil.doMutiLang(title, langArg);
            out = this.pageContext.getOut();
            out.print(end().toString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    public StringBuffer end() {
        String confirm = MutiLangUtil.getLang("common.confirm");
        String cancel = MutiLangUtil.getLang("common.cancel");
        String methodname = UUIDGenerator.generate().replaceAll("-", "");
        StringBuffer sb = new StringBuffer();

        //增加扩展属性
        StringBuffer extendButtonJsonStrBuf = this.getExtendButtonJsonCommon(extendButtonJson);

        sb.append("<a href=\"#\" " + extendButtonJsonStrBuf + " plain=\"true\" icon=\"" + icon + "\" onClick=\"choose_" +
                methodname + StringUtil.replace("()\">{0}</a>", "{0}",
                MutiLangUtil.getLang("common.select", langArg)));
        if (isclear && StringUtil.isNotEmpty(textname)) {
            sb.append("<a href=\"#\" " + extendButtonJsonStrBuf + " plain=\"true\" icon=\"icon-redo\" onClick=\"clearAll_" +
                    methodname + StringUtil.replace("();\">{0}</a>", "{0}",
                    MutiLangUtil.getLang("common.clear", langArg)));
        }
        sb.append("<script type=\"text/javascript\">");
        //--author：scott-----start----date:20170407--------for: 异常捕获避免js报错-------------
        sb.append("var windowapi;");
        sb.append("try{").append("windowapi = frameElement.api, W = windowapi.opener;").append("}catch(e){}")
                .append("function choose_" + methodname + "(){");
        //--author：zhoujf-----start----date:20150531--------for: 编辑用户，选择角色,弹出的角色列表页面，默认没选中 标签扩展
        sb.append("var url = ").append("'").append(url).append("';");
        if (isInit) {
            sb.append("var initValue = ").append("$(\'#" + hiddenName + "\').val();").append("url += ").append("'&ids='+initValue;");
        }
        sb.append("if(typeof(windowapi) == 'undefined'){").append("$.dialog({").append("content: \'url:\'+url,").append("zIndex: getzIndex(),");
        if (title != null) {
            sb.append("title: \'" + title + "\',");
        }
        sb.append("lock : true,").append(width != null ? "width :\'" + width + "\'," : "width :400,")
                .append(height != null ? "height :\'" + height + "\'," : "height :350,")
                .append(left != null ? "left :\'" + left + "\'," : "left :'85%',")
                .append(top != null ? "top :\'" + top + "\'," : "top :'65%',").append("opacity : 0.4,").append("button : [ {")
                .append(StringUtil.replace("name : \'{0}\',", "{0}", confirm))
                .append("callback : clickcallback_" + methodname + ",").append("focus : true").append("}, {")
                .append(StringUtil.replace("name : \'{0}\',", "{0}", cancel)).append("callback : function() {")
                .append("}").append("} ]").append("});").append("}else{").append("$.dialog({").append("content: \'url:\'+url,")
                .append("zIndex: getzIndex(),");
        if (title != null) {
            sb.append("title: \'" + title + "\',");
        }
        sb.append("lock : true,").append("parent:windowapi,").append(width != null ? "width :\'" + width + "\'," : "width :400,")
                .append(height != null ? "height :\'" + height + "\'," : "height :350,")
                .append(left != null ? "left :\'" + left + "\'," : "left :'85%',")
                .append(top != null ? "top :\'" + top + "\'," : "top :'65%',").append("opacity : 0.4,")
                .append("button : [ {").append(StringUtil.replace("name : \'{0}\',", "{0}", confirm))
                .append("callback : clickcallback_" + methodname + ",").append("focus : true").append("}, {")
                .append(StringUtil.replace("name : \'{0}\',", "{0}", cancel))
                .append("callback : function() {").append("}").append("} ]").append("});").append("}").append("}");
        clearAll(sb, methodname);
        callback(sb, methodname);
        sb.append("</script>");
        return sb;
    }

    /**
     * 清除
     *
     * @param sb
     */
    private void clearAll(StringBuffer sb, String methodname) {
        String[] textnames = null;
        String[] inputTextnames = null;

        if (!StringUtil.isEmpty(this.textname)) {
            textnames = textname.split(",");
        }

        if (StringUtil.isNotEmpty(inputTextname)) {
            inputTextnames = inputTextname.split(",");
        } else {
            inputTextnames = textnames;
        }
        if (isclear && StringUtil.isNotEmpty(textname)) {
            sb.append("function clearAll_" + methodname + "(){");
            for (int i = 0; i < textnames.length; i++) {
                inputTextnames[i] = inputTextnames[i].replaceAll("\\[", "\\\\\\\\[").replaceAll("\\]", "\\\\\\\\]").replaceAll("\\.", "\\\\\\\\.");
                sb.append("if($(\'#" + inputTextnames[i] + "\').length>=1){");
                sb.append("$(\'#" + inputTextnames[i] + "\').val('');");
                sb.append("$(\'#" + inputTextnames[i] + "\').blur();");
                sb.append("}");
                sb.append("if($(\"input[name='" + inputTextnames[i] + "']\").length>=1){");
                sb.append("$(\"input[name='" + inputTextnames[i] + "']\").val('');");
                sb.append("$(\"input[name='" + inputTextnames[i] + "']\").blur();");
                sb.append("}");
            }
            sb.append("$(\'#" + hiddenName + "\').val(\"\");");
            sb.append("}");
        }
    }

    /**
     * 点击确定回填
     *
     * @param sb
     */
    private void callback(StringBuffer sb, String methodname) {
        sb.append("function clickcallback_" + methodname + "(){");
        sb.append("iframe = this.iframe.contentWindow;");
        String[] textnames = null;
        String[] inputTextnames = null;
        if (StringUtil.isNotEmpty(textname)) {
            textnames = textname.split(",");
            if (StringUtil.isNotEmpty(inputTextname)) {
                inputTextnames = inputTextname.split(",");
            } else {
                inputTextnames = textnames;
            }
            for (int i = 0; i < textnames.length; i++) {
                inputTextnames[i] = inputTextnames[i].replaceAll("\\[", "\\\\\\\\[").replaceAll("\\]", "\\\\\\\\]").replaceAll("\\.", "\\\\\\\\.");
                sb.append("var " + textnames[i] + "=iframe.get" + name + "Selections(\'" + textnames[i] + "\');	");
                sb.append("if($(\'#" + inputTextnames[i] + "\').length>=1){");
                sb.append("$(\'#" + inputTextnames[i] + "\').val(" + textnames[i] + ");");
                sb.append("$(\'#" + inputTextnames[i] + "\').blur();");
                sb.append("}");
                sb.append("if($(\"input[name='" + inputTextnames[i] + "']\").length>=1){");
                sb.append("$(\"input[name='" + inputTextnames[i] + "']\").val(" + textnames[i] + ");");
                sb.append("$(\"input[name='" + inputTextnames[i] + "']\").blur();");
                sb.append("}");
            }
        }
        if (StringUtil.isNotEmpty(hiddenName)) {
            sb.append("var id =iframe.get" + name + "Selections(\'" + hiddenid + "\');");
            sb.append("if (id!== undefined &&id!=\"\"){");
            sb.append("$(\'#" + hiddenName + "\').val(id);");
            sb.append("}");
        }
        if (StringUtil.isNotEmpty(fun)) {
            //执行自定义函数
            sb.append("" + fun + "();");
        }
        sb.append("}");
    }

    private StringBuffer getExtendButtonJsonCommon(String extendJson) {
        Gson gson = new Gson();
        Map<String, String> mp = gson.fromJson(extendJson, Map.class);
        StringBuffer sb = new StringBuffer();
        String className = "class=\"easyui-linkbutton\"";
        sb.append(" ");
        boolean hasClass = false;
        if (mp != null) {
            for (Map.Entry<String, String> entry : mp.entrySet()) {
                //判断choose标签中是否含有class属性
                if ("class".equals(entry.getKey())) {
                    hasClass = true;
                }
                sb.append(entry.getKey() + "=\"" + entry.getValue() + "\"");
            }
        }
        //为了兼容以前的样式
        if (!hasClass) {
            sb.append(className);
        }
        return sb;
    }


    public void setHiddenName(String hiddenName) {
        this.hiddenName = hiddenName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setTextname(String textname) {
        this.textname = textname;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setIsclear(Boolean isclear) {
        this.isclear = isclear;
    }

    public void setHiddenid(String hiddenid) {
        this.hiddenid = hiddenid;
    }

    public void setFun(String fun) {
        this.fun = fun;
    }

    public String getInputTextname() {
        return inputTextname;
    }

    public void setInputTextname(String inputTextname) {
        this.inputTextname = inputTextname;
    }

    public String getLangArg() {
        return langArg;
    }

    public void setLangArg(String langArg) {
        this.langArg = langArg;
    }

    public void setIsInit(Boolean isInit) {
        this.isInit = isInit;
    }

    public String getExtendButtonJson() {
        return extendButtonJson;
    }

    public void setExtendButtonJson(String extendJson) {
        this.extendButtonJson = extendJson;
    }

}
