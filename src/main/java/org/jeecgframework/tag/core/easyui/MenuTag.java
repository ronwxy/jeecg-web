package org.jeecgframework.tag.core.easyui;

import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ListtoMenu;
import org.jeecgframework.core.util.SysThemesUtil;
import org.jeecgframework.tag.core.AbstractJeecgTag;
import org.jeecgframework.web.system.pojo.base.TSFunction;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * 类描述：菜单标签
 * <p>
 * 张代浩
 *
 * @version 1.0
 * @date： 日期：2012-12-7 时间：上午10:17:45
 */
public class MenuTag extends AbstractJeecgTag {
    private static final long serialVersionUID = 1L;
    protected String style = "easyui";//菜单样式
    protected List<TSFunction> parentFun;//一级菜单
    protected List<TSFunction> childFun;//二级菜单
    protected Map<Integer, List<TSFunction>> menuFun;//菜单Map


    public void setParentFun(List<TSFunction> parentFun) {
        this.parentFun = parentFun;
    }

    public void setChildFun(List<TSFunction> childFun) {
        this.childFun = childFun;
    }

    @Override
    public int doStartTag() throws JspTagException {
        return EVAL_PAGE;
    }

    @Override
    public int doEndTag() throws JspTagException {
        JspWriter out = null;
        StringBuffer endString = null;
        try {

            out = this.pageContext.getOut();
            endString = end();
            out.print(endString.toString());
            out.flush();
//			String menu = (String) this.pageContext.getSession().getAttribute("leftMenuCache"+style);
//			if(menu!=null){
//				out.print(menu);
//				out.flush();
//			}else{
//				menu=end().toString();
//				this.pageContext.getSession().setAttribute("leftMenuCache"+style,menu);
//				out.print(menu);
//				out.flush();
//			}

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.clearBuffer();
                //添加缓存后，不需要清空，并且清空后会对缓存造成影响
//				if(endString != null){
//					endString.setLength(0);
//				}
//				endString = null;
                //end().setLength(0);
            } catch (Exception e) {
                e.printStackTrace();
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

        if ("easyui".equals(style)) {
            sb.append("<ul id=\"nav\" class=\"easyui-tree tree-lines\" fit=\"true\" border=\"false\">");
            sb.append(ListtoMenu.getEasyuiMultistageTree(menuFun, style));
            sb.append("</ul>");
        }
        if ("shortcut".equals(style))

//		{	sb.append("<div id=\"nav\" style=\"display:none;\" class=\"easyui-accordion\" fit=\"true\" border=\"false\">");
        {
            sb.append("<div id=\"nav\" style=\"display:block;\" class=\"easyui-accordion\" fit=\"true\" border=\"false\">");

            sb.append(ListtoMenu.getEasyuiMultistageTree(menuFun, style));
            sb.append("</div>");
        }

        if ("bootstrap".equals(style)) {
            sb.append(ListtoMenu.getBootMenu(parentFun, childFun));
        }
        if ("json".equals(style)) {
            sb.append("<script type=\"text/javascript\">");
            sb.append("var _menus=" + ListtoMenu.getMenu(parentFun, childFun));
            sb.append("</script>");
        }
        if ("june_bootstrap".equals(style)) {
            sb.append(ListtoMenu.getBootstrapMenu(menuFun));
        }
        if ("ace".equals(style)) {
            sb.append(ListtoMenu.getAceMultistageTree(menuFun));
        }
        if ("diy".equals(style)) {
            sb.append(ListtoMenu.getDIYMultistageTree(menuFun));
        }
        if ("hplus".equals(style)) {
            sb.append(ListtoMenu.getHplusMultistageTree(menuFun));
        } else if ("fineui".equals(style)) {
            sb.append(ListtoMenu.getFineuiMultistageTree(menuFun));
        }

        this.putTagCache(sb);

        return sb;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setMenuFun(Map<Integer, List<TSFunction>> menuFun) {
        this.menuFun = menuFun;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MenuTag [style=").append(style);
        builder.append(",sessionid=").append(ContextHolderUtils.getSession().getId());
        builder.append(",sysTheme=").append(SysThemesUtil.getSysTheme(ContextHolderUtils.getRequest()).getStyle())
                .append(",brower_type=").append(ContextHolderUtils.getSession().getAttribute("brower_type"))
                .append("]");
        return builder.toString();
    }


}
