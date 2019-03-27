package org.jeecgframework.tag.core.easyui;

import org.jeecgframework.core.util.MutiLangUtil;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 类描述：列表自定义函数操作项处理标签
 *
 * @author 张代浩
 * @version 1.0
 * @date： 日期：2012-12-7 时间：上午10:17:45
 */
public class DataGridFunOptTag extends TagSupport {

    protected String title;
    private String exp;//判断链接是否显示的表达式
    private String funname;//自定义函数名称
    private String operationCode;//按钮的操作Code
    private String langArg;//按钮的操作Code
    private String urlStyle;//样式

    private String urlclass;//按钮样式
    private String urlfont;//按钮图标

    private boolean inGroup;//操作列菜单一开始是否隐藏【船舶专用】

    @Override
    public int doStartTag() throws JspTagException {
        return EVAL_PAGE;
    }

    @Override
    public int doEndTag() throws JspTagException {
        title = MutiLangUtil.doMutiLang(title, langArg);

        Tag t = findAncestorWithClass(this, DataGridTag.class);
        DataGridTag parent = (DataGridTag) t;
        parent.setFunUrl(title, exp, funname, operationCode, urlStyle, urlclass, urlfont, inGroup);
        return EVAL_PAGE;
    }

    public void setFunname(String funname) {
        this.funname = funname;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public void setLangArg(String langArg) {
        this.langArg = langArg;
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
