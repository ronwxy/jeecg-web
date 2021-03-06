package org.jeecgframework.tag.core.easyui;

import org.jeecgframework.core.util.MutiLangUtil;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 类描述：列表操作项处理标签
 * <p>
 * 张代浩
 *
 * @version 1.0
 * @date： 日期：2012-12-7 时间：上午10:17:45
 */
public class DataGridConfOptTag extends TagSupport {
    protected String url;
    protected String title;
    private String message;//询问链接的提示语
    private String exp;//判断链接是否显示的表达式
    private String operationCode;//按钮的操作Code
    private String urlStyle;//样式

    private String urlclass;//自定义按钮样式
    private String urlfont;//自定义按钮图标样式

    private boolean inGroup;

    @Override
    public int doStartTag() throws JspTagException {
        return EVAL_PAGE;
    }

    @Override
    public int doEndTag() throws JspTagException {
        Tag t = findAncestorWithClass(this, DataGridTag.class);
        DataGridTag parent = (DataGridTag) t;

        parent.setConfUrl(url, MutiLangUtil.getLang(title), MutiLangUtil.getLang(message), exp, operationCode, urlStyle, urlclass, urlfont, inGroup);

        return EVAL_PAGE;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public String getUrlStyle() {
        return urlStyle;
    }

    public void setUrlStyle(String urlStyle) {
        this.urlStyle = urlStyle;
    }

    public String getUrlclass() {
        return urlclass;
    }

    public void setUrlclass(String urlclass) {
        this.urlclass = urlclass;
    }

    public String getUrlfont() {
        return urlfont;
    }

    public void setUrlfont(String urlfont) {
        this.urlfont = urlfont;
    }

    public boolean isInGroup() {
        return inGroup;
    }

    public void setInGroup(boolean inGroup) {
        this.inGroup = inGroup;
    }

}
