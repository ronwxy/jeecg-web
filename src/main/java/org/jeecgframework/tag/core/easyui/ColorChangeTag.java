package org.jeecgframework.tag.core.easyui;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * 类描述：改变HTML控件颜色
 *
 * @version 1.0
 * @author: 张代浩
 * @date： 日期：2012-12-7 时间：上午10:17:45
 */
public class ColorChangeTag extends TagSupport {
    @Override
    public int doStartTag() throws JspTagException {
        return EVAL_PAGE;
    }

    @Override
    public int doEndTag() throws JspTagException {
        JspWriter out = null;
        try {
            out = this.pageContext.getOut();
            out.print(end().toString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.clear();
                out.close();
            } catch (Exception e2) {
            }
        }
        return EVAL_PAGE;
    }

    public StringBuffer end() {
        StringBuffer sb = new StringBuffer();
        return sb;
    }

}
