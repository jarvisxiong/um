package org.apache.jsp.WEB_002dINF.content.desk2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.hhz.core.utils.PowerUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import com.hhz.ump.cache.PlasCache;

public final class desk_002dnew_002dmain_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(11);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/common/meta.jsp");
    _jspx_dependants.add("/common/global.jsp");
    _jspx_dependants.add("/WEB-INF/content/desk2/desk-new-left.jsp");
    _jspx_dependants.add("/WEB-INF/content/desk2/desk-new-notepad.jsp");
    _jspx_dependants.add("/WEB-INF/content/desk2/desk-new-common.jsp");
    _jspx_dependants.add("/WEB-INF/content/desk2/desk-new-plan.jsp");
    _jspx_dependants.add("/WEB-INF/content/desk2/desk-new-cost.jsp");
    _jspx_dependants.add("/WEB-INF/content/desk2/desk-new-business.jsp");
    _jspx_dependants.add("/WEB-INF/content/desk2/desk-new-other.jsp");
    _jspx_dependants.add("/WEB-INF/PowerDesk-tags.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus_005fend_005fbegin;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005felse;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus_005fend_005fbegin = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005felse = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus_005fend_005fbegin.release();
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005felse.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<title>宝龙管理平台</title>  \r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"/>\r\n");
      out.write("<meta http-equiv=\"Cache-Control\" content=\"no-store\"/>\r\n");
      out.write("<meta http-equiv=\"Pragma\" content=\"no-cache\"/>\r\n");
      out.write("<meta http-equiv=\"Expires\" content=\"0\"/>\r\n");
      out.write("<META http-equiv=Page-Enter content=blendTrans(Duration=0.5)>\r\n");
      out.write("<META http-equiv=Page-Exit content=blendTrans(Duration=0.5)>\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=8\" />");
      out.write('\r');
      out.write('\n');
      out.write("<script language=\"javascript\">\r\n");
      out.write("var _ctx = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\r\n");
      out.write("function autoHeight(specialName){\r\n");
      out.write("\tvar endHeight = 0;\r\n");
      out.write("\ttry{\r\n");
      out.write("\t\tvar oheight = $(window.top.document).find(\"#bodyLoad\").height();\r\n");
      out.write("\t\tvar menuId = top.nowMenuId;\r\n");
      out.write("\t\tif(null!=menuId && undefined !=menuId){\r\n");
      out.write("\t\t\tvar ch = 0;\r\n");
      out.write("\t\t\ttry{\r\n");
      out.write("\t\t\t\tch=$(window.top.document).find('#' + menuId+'_frame').get(0).document.body.scrollHeight;\r\n");
      out.write("\t\t\t\tvar ch2= $(document).height();\r\n");
      out.write("\t\t\t\tif(ch2>ch || ch2==ch-51){ch=ch2;}\r\n");
      out.write("\t\t\t}catch(e){\r\n");
      out.write("\t\t\t\tch=$(window.top.document).find('#' + menuId+'_frame').get(0).contentDocument.body.scrollHeight;\r\n");
      out.write("\t\t\t\tvar ch2= $(document).height();\r\n");
      out.write("\t\t\t\tif(ch2>ch){ch=ch2;}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(endHeight == 0){\r\n");
      out.write("\t\t\t\tendHeight = ch;\r\n");
      out.write("\t\t\t}else if(ch > endHeight){\r\n");
      out.write("\t\t\t\tendHeight = ch;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(endHeight>604){\r\n");
      out.write("\t\t\t\t$(window.top.document).find(\"#bodyLoad\").height(endHeight+51);\r\n");
      out.write("\t\t\t\t$(window.top.document).find(\"#div_left_b\").height(endHeight+51);\r\n");
      out.write("\t\t\t\t$(window.top.document).find('#' + menuId+'_frame').contents().find(\"body\").height(endHeight);\r\n");
      out.write("\t\t\t\t$(window.top.document).find('#' + menuId+'_frame').height(endHeight);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}catch(e){}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function rePosition(obj){\r\n");
      out.write("\tobj.focus();\r\n");
      out.write("}\r\n");
      out.write("</script>");
      out.write("\r\n");
      out.write("<meta http-equiv=\"imagetoolbar\" content=\"no\" />\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=EmulateIE7\" />\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0\" />\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/desk2/pdhome.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/desk2/pdarea.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/desk2/tab.css\" />\r\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/desk2/right.css\" />\r\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/desk/left.css\" />\r\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/dtree/dtree.css\" />\r\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/prompt/skin/custom_1/ymPrompt.css\" id='skin' />\r\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/desk/wab/visitingCard.css\" />\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/common.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/cookie.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.messager.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/desk2/main.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/desk2/tab.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/prompt/ymPrompt_source.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/datePicker/WdatePicker.js\" ></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.resizable.js\" ></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/desk2/left.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/desk2/right.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/desk2/wabTreePanel.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/desk2/visitingCard.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("//密码过期\r\n");
      if (_jspx_meth_s_005fif_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("//注册登录\r\n");
PlasCache.addOnlineCount();
      out.write("\r\n");
      out.write("\r\n");
      out.write("$(function(){\r\n");
      out.write("\tvar ggxw_interval;\r\n");
      out.write("\ttry{\r\n");
      out.write("\t\t//默认初始化桌面标签\r\n");
      out.write("\t\tTabUtils.initTab(\"homepage\");\r\n");
      out.write("\t}catch(e){}\r\n");
      out.write("\t//初始化外网邮箱  main.js\r\n");
      out.write("\ttry{\r\n");
      out.write("\t\trefreshMails('1');\r\n");
      out.write("\t}catch(e){}\r\n");
      out.write("\t\r\n");
      out.write("\t//初始化待办事项  main.js\r\n");
      out.write("\ttry{\r\n");
      out.write("\t\trefreshJbpm(\"\",\"1\");\r\n");
      out.write("\t}catch(e){}\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t//更新会议提醒\r\n");
      out.write("    try{\r\n");
      out.write("\t\trefreshHomeAttention();\r\n");
      out.write("\t}catch(e){}\r\n");
      out.write("\t\r\n");
      out.write("\t//公告  main.js\r\n");
      out.write("\ttry{\r\n");
      out.write("\t\trefreshHomeNotifyList();\r\n");
      out.write("\t}catch(e){}\r\n");
      out.write("\r\n");
      out.write("\ttry{\r\n");
      out.write("\t\t//设置首页每5分钟自动刷新一次\r\n");
      out.write("\t\tsetInterval(refreshHome, 10*60*1000);\r\n");
      out.write("\t}catch(e){}\r\n");
      out.write("\r\n");
      out.write("\ttry{\r\n");
      out.write("\t\t//自动切换公告和新闻\r\n");
      out.write("\t\t//ggxw_interval = setInterval(autoChangeGgxw, 10*1000);\r\n");
      out.write("\t\t$(\"#pn_content\").bind(\"mouseenter\",function(){\r\n");
      out.write("\t\t\tCAN_GGXW_CHANGE = false;\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$(\"#pn_content\").bind(\"mouseleave\",function(){\r\n");
      out.write("\t\t\tCAN_GGXW_CHANGE = true;\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}catch(e){}\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body id=\"bodyLoad\" onbeforeunload=\"runOnBeforeUnload();\" onblur=\"closeBook();\"  onfucosout=\"closeBook();\"    >\r\n");
      out.write("\r\n");
      out.write("<a name=\"top\" id=\"top\"></a>\r\n");
      out.write("<div id=\"div_home_b\" class=\"div_home\" style=\"min-width: 1008px;background-color: #e4e7ec\">\r\n");
      out.write("\t");
      if (_jspx_meth_c_005fset_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("    <!--左边框架-->\r\n");
      out.write("\t<div class=\"div_left\" id=\"div_left_b\" style=\"width:88px;\" >\r\n");
      out.write("\t\t<div class=\"div_left_top\">\r\n");
      out.write("\t\t\t&nbsp;\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\t<div id=\"divXialaMenu\" >\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\t\t<div class=\"div_left_title green_hover\" style=\"height:40px;line-height: 40px;margin-bottom: 1px;\" oi=\"nohide\"  id=\"book\" onclick=\"doClickBook(this,'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/app/app-authority!loadMenuByNewModule.action?moduleCd=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${moduleCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("');\">\r\n");
      out.write("\t\t\t<div style=\"margin-left:0px;float:center;font-size: 12px;\" class=\"font_left\">通讯录</div>\r\n");
      out.write("\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div oi=\"nohide\" class=\"div_left_bottom\" style=\"margin-bottom:1px;\">&nbsp;</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t");
      out.write("\t\r\n");
      out.write("\t</div>\t\r\n");
      out.write("\t<div class=\"div_left_bottom\" style=\"margin-top:1px;\">&nbsp;</div>\r\n");
      out.write("\t\t<div align=\"center\" class=\"person_online\" id=\"login_user_pic\">\r\n");
      out.write("\t\t\t&nbsp;\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div align=\"center\" style=\"margin-top: 2px;\" class=\"font_left_user\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</div>\r\n");
      out.write("\t<div class=\"font_left_config red_hover\" onclick=\"logout(this);\" style=\"margin-top:8px;\">退出</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\t<div id=\"addressBookList\" oi=\"nohide\" addressShow=\"false\" style=\"z-index: 100;height:590px;\" >");
      out.write("\r\n");
      out.write("\t\t");
      out.write("\r\n");
      out.write("\t\t<div id=\"child_book\" class=\"div_address\">\r\n");
      out.write("\t\t<div style=\"margin-top:1px;margin-left:12px;\">\r\n");
      out.write("\t\t\t<div style=\"height:28px; line-height:24px;font-size:18px;margin-top: 8px;width:225px;\">\r\n");
      out.write("\t\t\t\t<div id=\"wab\" onclick=\"resetWabTreeHeight();\" title=\"查看通讯录\">\r\n");
      out.write("\t\t\t\t\t通讯录 \r\n");
      out.write("\t\t\t\t\t<a  href=\"javascript:showAddressTree()\" >回到架构表</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div> \r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<!-- 搜索条件 -->\r\n");
      out.write("\t\t\t<input  type=\"text\" \r\n");
      out.write("\t\t\t\t\tid=\"searched_user\" \r\n");
      out.write("\t\t\t\t\tvalue=\"\" \r\n");
      out.write("\t\t\t\t\tclass=\"search_input\" \r\n");
      out.write("\t\t\t\t\tonclick=\"clearSearchInput(this);searchUserResult(this);\"\r\n");
      out.write("\t\t\t\t\tonkeyup =\"searchUserResult(this);\" \r\n");
      out.write("\t\t\t\t\tonblur=\"this.focus();\"/>\r\n");
      out.write("\t\t\t\t\t<!-- onblur=\"resetSearchInput(this,'wabTree','searchResult');\" style=\"width:225px;\"/> -->\r\n");
      out.write("\t\t\t<div style=\"height:8px;\">&nbsp;</div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<!-- 搜索列表 -->\r\n");
      out.write("\t\t\t<div id=\"searchResult\" class=\"search_user_result\" ></div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<!-- 通讯录树-->\r\n");
      out.write("\t\t\t<div id=\"wabTree\" style=\"float:left;width:230px; height:501px; overflow-x:hidden;overflow-y:auto;font-size:12px;line-height:26px;\" onclick=\"resetWabTreeHeight();\">\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<!-- 名片区域-->\r\n");
      out.write("\t\t\t<div id=\"showContent\" style=\"float:left;max-width:200px;padding-left:5px;\">\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"div_show_address\" style=\"background-color:#fff;margin-left:249px;display:none;width:100px;z-index: 100;position:absolute;height:501px;\">\r\n");
      out.write("\t\t&nbsp;\t\r\n");
      out.write("\t</div>\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\t<!--中间框架-->\r\n");
      out.write("\t<div class=\"div_center\" id=\"div_center_b\">&nbsp;</div>\r\n");
      out.write("\t<!--右边框架-->\r\n");
      out.write("\t<div class=\"div_right\" style=\"padding-top:8px;\" id=\"divFrame\" onclick=\"closeBook();\">\r\n");
      out.write("  \t\t<div id=\"divTab\" class=\"div_right_title\" style=\"margin-right:8px;\">\r\n");
      out.write("\t\t\t<div id=\"homepage_tab\">\r\n");
      out.write("\t\t\t\t<div type=\"pageDiv\" class=\"div_right_tab_selected\" onclick=\"TabUtils.showTab({data:{tabId:'homepage',src:''}});\">桌面</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\t\r\n");
      out.write("\t\t<div id=\"div_right_fixed\" class=\"div_right_content\" style=\"margin-top:10px;min-width: 1024px;\">\r\n");
      out.write("\t\t\t<!-- 固定区域 -->\r\n");
      out.write("\t\t\t<div style=\"float:left;width:863px;height:522px;margin-right: 9px;margin-bottom:4px;\">\r\n");
      out.write("\t\t\t\t<div style=\"width:281px;float:left;margin-right: 10px;\">\r\n");
      out.write("\t\t\t\t\t<div style=\"border-bottom-width:1px;\r\n");
      out.write("\t\t\t\t\t\t\t\tborder-bottom-color:#CCCCCC;border-bottom-style:solid;\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"div_index\" >\r\n");
      out.write("\t\t\t\t\t\t\t");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${num }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_c_005fset_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div style=\"width:100%;margin-left:0px;padding-bottom: 0px;padding-top:0px;height:30px;\" class=\"font_title_16\">\r\n");
      out.write("\t\t\t\t\t\t<div style=\"float:left;margin-right:8px; cursor:pointer;font-weight: bold;line-height:30px;height:30px;text-align:center;\" id=\"email\"  onclick=\"refreshMails('1');\">邮件</div>\r\n");
      out.write("\t\t\t\t\t\t<div id=\"attention\">\r\n");
      out.write("\t\t\t\t\t\t<div style=\"float:left;margin-right:5px;font-size:14px;margin-top:6px;\" isline=\"true\">|</div>\r\n");
      out.write("\t\t\t\t\t\t<div id=\"attenDiv\" style=\"float:left;margin-right:8px; cursor:pointer;font-weight: bold;line-height:30px;height:30px;text-align:center;\">关注\r\n");
      out.write("\t\t\t\t\t\t<span id=\"attentionCount\"></span>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div id=\"emailPageTest\" style=\"float:right;margin-right: 0px;\">\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t </div>\r\n");
      out.write("\t\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write("\t\t\t\t\t<div id=\"desk_new_email\" style=\"width:100%;height:422px;margin-left:0px;\">\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write("\t\t\t\t\t<div id=\"desk_new_meeting\" style=\"width:100%;margin-left:0px;background-color:#54b147;height:40px;border-bottom:0px;margin-top:4px;\"></div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t<div style=\"float:left;width:399px;margin-right:10px;\" id=\"waitArea\">\r\n");
      out.write("\t\t\t\t\t <div class=\"div_index\">\r\n");
      out.write("\t\t\t\t\t\t");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${num }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\r\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_c_005fset_005f3(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t \t<div id=\"desk-new-schedule\" style=\"height:370px;width:100%;margin-left:0px;\"></div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div style=\"float:left;width:163px;\" id=\"main_right\">\r\n");
      out.write("\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"div_index\" >\r\n");
      out.write("\t\t\t\t\t\t\t");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${num }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_c_005fset_005f4(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div style=\"width:100%;padding-bottom: 0px;height:30px;border-bottom-width:1px;\r\n");
      out.write("\t\t\t\t\t\t\tborder-bottom-color:#CCCCCC;border-bottom-style:solid;\" class=\"font_title_16\">\r\n");
      out.write("\t\t\t \t\t\t\t\t<div style=\"float:left;font-weight: bold;line-height:30px;height:30px;text-align:center;\" >便签</div>\r\n");
      out.write("\t\t\t \t\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write("\t\t \t\t\t\t\t</div>\r\n");
      out.write("\t \t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t \t\t\t\t<div style=\"margin-top:4px;\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"content_left_page_selected\" id=\"note_page_btn_1\"  onclick=\"showFirstPage();\" title=\"第一页\">1</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"content_left_page\" id=\"note_page_btn_2\" onclick=\"showSecondPage();\">2</div>\r\n");
      out.write("\t\t\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write(" \t\t\t\t\t\t\r\n");
      out.write(" \t\t\t\t\t\t\r\n");
      out.write(" \t\t\t\t<div class=\"note_container\">\r\n");
      out.write("\t\t\t\t\t<div id=\"note_container\">\r\n");
      out.write("\t\t\t\t\t\t<div id=\"note_page_1\">\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_s_005fiterator_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div id=\"note_page_2\" style=\"display:none\">\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_s_005fiterator_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write("\t\t\t\t<div  class=\"block_deepblue_fast\" id=\"pd_notice\"  style=\"width:100%;height:260px;margin-top: 4px;\">\r\n");
      out.write(" \t\t\t\t\t\t<!-- <div style=\"line-height: 16px;text-align: left;padding:12px 0px 0px 5px;font-size: 18px;font-weight:bold;\"></div> -->\r\n");
      out.write(" \t\t\t\t\t\t<div class=\"font_notice\" style=\"margin-top: 8px;\" >\r\n");
      out.write(" \t\t\t\t\t\t\t<div style=\"padding:18px 5px 6px 5px; font-weight:bolder;\" onclick=\"TabUtils.newTab('pptdownload2','下载', '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/common/pptdownload2.jsp', true);\">下载半年会工作报告&nbsp;></div>\r\n");
      out.write(" \t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t<div class=\"font_notice\">\r\n");
      out.write(" \t\t\t\t\t\t\t<div style=\"padding:6px 5px 6px 5px; font-weight:bolder;\" onclick=\"TabUtils.newTab('pptdownload','下载', '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/common/pptdownload.jsp', true);\">下载商业地产项目管理专项培训课件&nbsp;></div>\r\n");
      out.write(" \t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t<div class=\"font_notice\">\r\n");
      out.write(" \t\t\t\t\t\t\t<!-- <div style=\"padding:6px 5px 6px 5px;\" onclick=\"window.open('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/common/viewVideo.jsp');\">观看网批培训视频&nbsp;></div> -->\r\n");
      out.write(" \t\t\t\t\t\t\t<div style=\"padding:6px 5px 6px 5px; font-weight:bolder;\" onclick=\"window.open('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/app/download.action?fileName=PD3.0.ppsx&amp;realFileName=PD3.0_intro.ppsx&amp;bizModuleCd=public');\">下载PD3.0新特性说明&nbsp;></div>\r\n");
      out.write(" \t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"font_notice\">\r\n");
      out.write("\t\t\t\t\t\t\t<div style=\"padding:6px 5px 6px 5px;\" onclick=\"window.open('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/app/download.action?fileName=seat_and_tel.xlsx&amp;realFileName=seat_and_tel.xlsx&amp;bizModuleCd=public');\">总部人员座位图&nbsp;></div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"font_notice\">\r\n");
      out.write("\t\t\t\t\t\t\t<div style=\"padding:6px 5px 6px 5px;\" onclick=\"window.open('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/app/download.action?fileName=IT_duty.xlsx&realFileName=IT_duty.xlsx&bizModuleCd=public');\">IT值班表&nbsp;></div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"font_notice\">\r\n");
      out.write("\t\t\t\t\t\t\t<div style=\"padding:6px 5px 6px 5px;\" onclick=\"window.open('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/app/download.action?fileName=adobe.rar&realFileName=adobe.rar&bizModuleCd=public');\">人事系统和加密系统插件&nbsp;></div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"font_notice\">\r\n");
      out.write("\t\t\t\t\t\t\t<div style=\"padding:6px 5px 6px 5px;\" onclick=\"window.open('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/app/download.action?fileName=print_set.docx&amp;realFileName=print_set.docx&amp;bizModuleCd=public');\">下载打印机配置说明&nbsp;></div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write(" \t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t<div id=\"note_show\" class=\"note_show\" style=\"margin-top: 4px;\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"note_remind_date\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"hidden\" id=\"dlyNoteId\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" id=\"noteRemindDate\" style=\"width:80px;margin-left: 3px;\" onfocus=\"WdatePicker()\"/>\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" id=\"noteRemindTime\" style=\"width:50px;\" onfocus=\"WdatePicker({dateFmt:'HH:mm'})\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div style=\"border-top: 1px solid #dddbdc;\">\r\n");
      out.write("\t\t\t\t\t\t\t<textarea id=\"noteContentId\"  onpaste=\"IS_NOTE_DIRTY = true;\" class=\"note_content_div\" style=\"width:158px;\"></textarea>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"pd_remind\" style=\"border-top: 1px solid #dddbdc;\">\r\n");
      out.write("\t\t\t\t\t\t\t<div onclick=\"saveNote()\" style=\"width:100px;float:left;font-weight: bold;cursor: pointer; background-color:#54b147; color:#fff;\">保存</div> \r\n");
      out.write("\t\t\t\t\t\t\t<div onclick=\"delNote()\"  style=\"width:53px;float:right;font-weight: bold;cursor: pointer; background-color:#AC2727; color:#fff;\">删除</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"note_update_div\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<span id=\"noteUpdatedDate\"></span>更新\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div style=\"background-color:#8c8f94; height:1px;\"></div>\r\n");
      out.write("\t\t\t\t\t\t\t<div id=\"noteResizeHandler\" class=\"note_resize\"></div>\r\n");
      out.write("\t\t\t\t\t\t\t<div style=\"background-color:#8c8f94; height:1px;\"></div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<div id=\"attention_ajax_div\" style=\"display:none;\"></div>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t<div style=\"float:left;width:281px;height:261px;margin-bottom: 2px;margin-right: 10px;max-height: 262px;min-height: 224px;\" id=\"pn_content\">\r\n");
      out.write("\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t<div class=\"div_index\" >\r\n");
      out.write("\t\t\t\t\t\t");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${num }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\r\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_c_005fset_005f5(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div style=\"width:100%;margin-left:0px;padding-bottom: 0px;border-bottom-width:1px;\r\n");
      out.write("\t\t\t\t\t\tborder-bottom-color:#CCCCCC;border-bottom-style:solid;height:30px;\" class=\"font_title_16\">\r\n");
      out.write("\t\t\t\t\t\t<div style=\"float:left;margin-right:8px;font-weight: bold;line-height:30px;height:30px;text-align:center;\" id=\"div_notice\" class=\"notice_link_selected\" onclick=\"refreshHomeNotifyList();\" >公告</div>\r\n");
      out.write("\t\t\t\t\t\t<div style=\"float:left;margin-right:8px;font-size:14px;margin-top:6px;\" isline=\"true\">|</div>\r\n");
      out.write("\t\t\t\t\t\t<div style=\"float:left;font-weight: bold;line-height:30px;height:30px;text-align:center;\" class=\"notice_link\" id=\"div_news\" onclick=\"refreshHomeNewsList();\">新闻</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write("\t\t        <div id=\"desk-new-news\" style=\"max-height: 261px;min-height: 224px;\"></div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t<div style=\"float:left;width:281px;height:261px;margin-right: 10px;margin-bottom: 2px;\" >\r\n");
      out.write("\t\t\t\t");
      out.write("\r\n");
      out.write(" \t\t\t\t\t<div>\r\n");
      out.write(" \t\t\t\t\t<div class=\"div_index\" >\r\n");
      out.write(" \t\t\t\t\t\t");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${num }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\r\n");
      out.write(" \t\t\t\t\t\t");
      if (_jspx_meth_c_005fset_005f6(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write(" \t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t<div style=\"width:100%;margin-left:0px;padding-bottom: 0px;border-bottom-width:1px;\r\n");
      out.write(" \t\t\t\t\tborder-bottom-color:#CCCCCC;border-bottom-style:solid;height:30px;\" class=\"font_title_16\">\r\n");
      out.write(" \t\t\t\t\t<div style=\"float:left;margin-right:8px;font-weight: bold;line-height:30px;height:30px;text-align:center;\">常用</div>\r\n");
      out.write(" \t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write(" \t\t\t\t\t<div style=\"padding:4px 0px 4px 0px;\">\r\n");
      out.write(" \t\t\t\t\t\t<div style=\"float:left;margin-right: 4px;color:#fff;width:186px;height:134px;\r\n");
      out.write(" \t\t\t\t\t\ttext-align: center;line-height: 134px;font-size: 18px;\" class=area_other_blue isStatic=\"110||156\" title=\"网上审批\">\r\n");
      out.write(" \t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/desk_res.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />网上审批\r\n");
      out.write(" \t\t\t\t\t\t</div>\t\r\n");
      out.write(" \t\t\t\t\t\t<div style=\"float:left;color:#fff;width:91px;height:65px;text-align:center;line-height: 65px;\" class=\"area_other_deep_blue\" isStatic=\"109||175\" title=\"预约总裁\">\r\n");
      out.write(" \t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/appointment.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />预约总裁\r\n");
      out.write(" \t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t<div style=\"margin-top:4px;float:left;color:#fff;width:91px;height:65px;text-align:center;line-height: 65px;\" class=\"area_other_deep_blue\" isStatic=\"109||144\" title=\"预定会议室\">\r\n");
      out.write(" \t\t\t\t\t\t\t <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/meeting_gb.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />预定会议室\r\n");
      out.write(" \t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t<div style=\"margin-top:4px;float:left;width:91px;height:65px;text-align: center;margin-right: 4px;color:#fff;\" class=\"area_other_deep_blue\" isStatic=\"108||151\" title=\"制度体系(地产控股)\">\r\n");
      out.write(" \t\t\t\t\t\t\t<div class=\"pic_common\" style=\"background-image:url('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/system.png');background-position:11px 5px;background-repeat:no-repeat;padding-left: 8px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t\t<div style=\"height:12px;line-height: 12px;text-align: left;margin-left: 26px;margin-top: 18px;\">地产控股</div>\r\n");
      out.write("\t \t\t\t\t\t\t\t<div style=\"height:12px;line-height: 12px;text-align: left;margin-left: 26px;margin-top:4px;\">工具书</div>\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t<div style=\"margin-top:4px;float:left;width:91px;height:65px;text-align: center;line-height: 65px;margin-right: 4px;color:#fff;\" class=\"area_other_deep_blue\" isStatic=\"108||205\" title=\"制度体系(集团)\">\r\n");
      out.write(" \t\t\t\t\t\t\t<div class=\"pic_common\" style=\"background-image:url('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/standard.png');background-position:11px 5px;background-repeat:no-repeat;padding-left: 8px;\">\r\n");
      out.write("\r\n");
      out.write(" \t\t\t\t\t\t\t\t<div style=\"height:12px;line-height: 12px;text-align: left;margin-left: 26px;margin-top: 18px;\">宝龙集团</div>\r\n");
      out.write("\t \t\t\t\t\t\t\t<div style=\"height:12px;line-height: 12px;text-align: left;margin-left: 26px;margin-top:4px;\">工具书</div>\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t<div style=\"margin-top:4px;float:left;color:#fff;width:91px;height:65px;text-align: center;line-height: 65px;\" class=\"area_other_deep_blue\" isStatic=\"109||165\" title=\"共享文件\">\r\n");
      out.write(" \t\t\t\t\t\t\t <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/file_share.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />共享文件\r\n");
      out.write(" \t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t<div style=\"float:left;width:281px;height:261px;margin-right: 10px;margin-bottom: 2px;display:none;\" isShow=\"107||\" isParent=\"true\">\r\n");
      out.write("\t\t\t\t");
      out.write("\r\n");
      out.write(" \t\t\t\t\t\r\n");
      out.write(" \t\t\t\t\t<div>\r\n");
      out.write(" \t\t\t\t\t<div class=\"div_index\" type=\"num\" >\r\n");
      out.write(" \t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t<div style=\"width:100%;margin-left:0px;padding-bottom: 0px;border-bottom-width:1px;\r\n");
      out.write(" \t\t\t\t\tborder-bottom-color:#CCCCCC;border-bottom-style:solid;height:30px;\" class=\"font_title_16\">\r\n");
      out.write(" \t\t\t\t\t<div style=\"float:left;margin-right:8px;font-weight: bold;line-height:30px;height:30px;text-align:center;\">计划系统</div>\r\n");
      out.write(" \t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write(" \t\t\t\t\t\r\n");
      out.write(" \t\t\t\t\t<div style=\"padding:4px 0px 4px 0px;\">\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<div class=\"area_light_blue_disabled\" isControl=\"true\"  style=\"float:left;color:#fff;width:186px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t<div  style=\"display:none;\" align=\"center\" isShow=\"107||154\">\r\n");
      out.write(" \t\t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/project_develop.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />项目开发计划\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t<div class=\"area_deep_blue_disabled\" isControl=\"true\"  style=\"margin-left:4px;float:left;color:#fff;width:91px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t<div style=\"display:none;\" isShow=\"107||172\">\r\n");
      out.write(" \t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/work_command.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />工作指令\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t<div class=\"area_light_blue_disabled\" isControl=\"true\"  style=\"margin-top:4px;float:left;color:#fff;width:186px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t<div style=\"display:none\" isShow=\"107||201\">\r\n");
      out.write(" \t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/business_execute.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />商业执行计划\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t<div class=\"area_deep_blue_disabled\" isControl=\"true\"  style=\"margin-left:4px;margin-top:4px;float:left;color:#fff;width:91px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t");
      out.write("\r\n");
      out.write(" \t\t\t\t\t\t\t<div style=\"display:none;\" isShow=\"107||213\">\r\n");
      out.write(" \t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/summary_resolution.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />纪要决议\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t<div class=\"area_light_blue_disabled\" isControl=\"true\"  style=\"margin-top:4px;float:left;color:#fff;width:186px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t<div style=\"display:none\" isShow=\"107||153\">\r\n");
      out.write(" \t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/work_plan.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />总部工作计划\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t<div class=\"area_deep_blue_disabled\" isControl=\"true\"  style=\"margin-left:4px;margin-top:4px;float:left;color:#fff;width:91px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t<div style=\"display:none;\" isShow=\"107||138\">\r\n");
      out.write(" \t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/inside_task.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />内部任务\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t<div style=\"float:left;width:281px;height:261px;margin-right: 10px;margin-bottom: 2px;display:none;\" isShow=\"115||\" isParent=\"true\">\r\n");
      out.write("\t\t\t\t");
      out.write("\r\n");
      out.write(" \t\t\t\t\t\r\n");
      out.write(" \t\t\t\t\t<div>\r\n");
      out.write(" \t\t\t\t\t<div class=\"div_index\" type=\"num\">\r\n");
      out.write(" \t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t<div style=\"width:100%;margin-left:0px;padding-bottom: 0px;border-bottom-width:1px;\r\n");
      out.write(" \t\t\t\t\tborder-bottom-color:#CCCCCC;border-bottom-style:solid;height:30px;\" class=\"font_title_16\">\r\n");
      out.write(" \t\t\t\t\t<div style=\"float:left;margin-right:8px;font-weight: bold;line-height:30px;height:30px;text-align:center;\">成本系统</div>\r\n");
      out.write(" \t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write(" \t\t\t\t\t\r\n");
      out.write(" \t\t\t\t\t<div style=\"padding:4px 0px 4px 0px;\">\r\n");
      out.write("\t\t\t\t\t\t<div style=\"height:65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t<div class=\"area_light_blue_disabled\" isControl=\"true\" style=\"float:left;margin-right:4px;color:#fff;width:186px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t\t<div style=\"display:none\" isShow=\"115||185\">\r\n");
      out.write(" \t\t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/plan_platform.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />计划平台\r\n");
      out.write(" \t\t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t<div class=\"area_deep_blue_disabled\" isControl=\"true\" style=\"float:left;color:#fff;width:91px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t\t<div style=\"display:none\" isShow=\"115||157\">\r\n");
      out.write(" \t\t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/supply_platform.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />供方平台\r\n");
      out.write(" \t\t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div style=\"height:65px;margin-top: 4px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t <div class=\"area_light_blue_disabled\" isControl=\"true\" style=\"float:left;margin-right:4px;color:#fff;width:186px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t\t<div style=\"display:none\" isShow=\"115||202\">\r\n");
      out.write(" \t\t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/invite_platform.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />招标平台\r\n");
      out.write(" \t\t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t<div class=\"area_deep_blue_disabled\" isControl=\"true\" style=\"float:left;color:#fff;width:91px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t\t<div style=\"display:none\" isShow=\"115||171\">\r\n");
      out.write(" \t\t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/cont_platform.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />合同平台\r\n");
      out.write(" \t\t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div style=\"height:65px;margin-top: 4px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t <div class=\"area_deep_blue_disabled\" isControl=\"true\" style=\"float:left;margin-right:4px;color:#fff;width:91px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t\t<div style=\"display:none;\" isShow=\"115||152\">\r\n");
      out.write(" \t\t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/cost_tool_book.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />成本工具书\r\n");
      out.write(" \t\t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t<div class=\"area_deep_blue_disabled\" isControl=\"true\" style=\"float:left;margin-right:4px;color:#fff;width:91px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t\t<div style=\"display:none\" isShow=\"115||207\">\r\n");
      out.write(" \t\t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/cost_data.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />成本数据库\r\n");
      out.write(" \t\t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t<div class=\"area_deep_blue_disabled\" isControl=\"true\" style=\"float:left;color:#fff;width:91px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t\t<div style=\"display:none\" class=\"glzs_common\" isShow=\"115||209\">\r\n");
      out.write(" \t\t\t\t\t\t\t\t\t<div style=\"height:12px;line-height: 12px;text-align: left;margin-left: 26px;margin-top: 16px;\">工料</div>\r\n");
      out.write("\t \t\t\t\t\t\t\t\t<div style=\"height:12px;line-height: 12px;text-align: left;margin-left: 26px;margin-top:4px;\">价格指数</div>\r\n");
      out.write("\t \t\t\t\t\t\t\t\t \r\n");
      out.write("\t \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t<div style=\"float:left;width:281px;height:261px;margin-right: 10px;margin-bottom: 2px;display:none;\" isShow=\"102||\" isParent=\"true\">\r\n");
      out.write("\t\t\t\t");
      out.write("\r\n");
      out.write(" \t\t\t\t\t\r\n");
      out.write(" \t\t\t\t\t<div>\r\n");
      out.write(" \t\t\t\t\t<div class=\"div_index\"  type=\"num\" >\r\n");
      out.write(" \t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t<div style=\"width:100%;margin-left:0px;padding-bottom: 0px;padding-top:5px;border-bottom-width:1px;\r\n");
      out.write(" \t\t\t\t\tborder-bottom-color:#CCCCCC;border-bottom-style:solid;height:24px;\" class=\"font_title_16\">\r\n");
      out.write(" \t\t\t\t\t<div style=\"float:left;margin-right:8px;font-weight: bold;\">商业系统</div>\r\n");
      out.write(" \t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write(" \t\t\t\t\t\r\n");
      out.write(" \t\t\t\t\t<div style=\"padding:4px 0px 4px 0px;\">\r\n");
      out.write(" \t\t\t\t\t\t<div class=\"area_light_blue_disabled\" isControl=\"true\" style=\"float:left;margin-right: 4px;color:#fff;width:186px;height:134px;\r\n");
      out.write(" \t\t\t\t\t\ttext-align: center;line-height: 134px;font-size: 16px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t<div style=\"display:none;\" isShow=\"102||195\">\r\n");
      out.write(" \t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/sale_manage.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />营运平台\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t</div>\t\r\n");
      out.write(" \t\t\t\t\t\t<div class=\"area_deep_blue_disabled\" isControl=\"true\" style=\"float:left;color:#fff;width:91px;height:65px;text-align:center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t<div style=\"display:none;\" isShow=\"102||196\">\r\n");
      out.write(" \t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/business_lib.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />商家库\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t<div class=\"area_deep_blue_disabled\" isControl=\"true\" style=\"margin-top:4px;float:left;color:#fff;width:91px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t<div style=\"display:none\" isShow=\"102||200\">\r\n");
      out.write(" \t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/cont_book.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />合同台帐\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t<div class=\"area_light_blue_disabled\" isControl=\"true\" style=\"margin-top:4px;float:left;color:#fff;width:186px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t<div style=\"display:none\" isShow=\"102||152\" title=\"计划平台\">\r\n");
      out.write(" \t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/business_plan.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />计划平台\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t<div class=\"area_deep_blue_disabled\" isControl=\"true\" style=\"margin-left: 4px;margin-top:4px;float:left;color:#fff;width:91px;height:65px;text-align:center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t<div style=\"display:none;\\\" isShow=\"102||221\" title=\"资产平台\">\r\n");
      out.write(" \t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/base_data.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />资产平台\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\r\n");
      out.write(" \t\t\t\t\t\t\r\n");
      out.write(" \t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t<div style=\"float:left;width:281px;height:261px;margin-right: 9px;margin-bottom: 2px;display:none;\" isShow=\"112||\" isParent=\"true\">\r\n");
      out.write("\t\t\t\t");
      out.write("\r\n");
      out.write(" \t\t\t\t\t\r\n");
      out.write(" \t\t\t\t\t<div>\r\n");
      out.write(" \t\t\t\t\t<div class=\"div_index\" type=\"num\" >\r\n");
      out.write(" \t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t<div style=\"width:100%;margin-left:0px;padding-bottom: 0px;border-bottom-width:1px;\r\n");
      out.write(" \t\t\t\t\tborder-bottom-color:#CCCCCC;border-bottom-style:solid;height:30px;\" class=\"font_title_16\">\r\n");
      out.write(" \t\t\t\t\t<div style=\"float:left;margin-right:8px;font-weight: bold;line-height:30px;height:30px;text-align:center;\">其他</div>\r\n");
      out.write(" \t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write(" \t\t\t\t\t\r\n");
      out.write(" \t\t\t\t\t<div style=\"padding:4px 0px 4px 0px;\">\r\n");
      out.write(" \t\t\t\t\t\t<div style=\"height:65px;\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"area_light_blue_disabled\" isControl=\"true\" style=\"float:left;margin-right:4px;color:#fff;width:186px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t <div style=\"display:none\" isShow=\"112||192\">\r\n");
      out.write(" \t\t\t\t\t\t\t <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/sale_system.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />销售系统\r\n");
      out.write(" \t\t\t\t\t\t\t </div>\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t<div class=\"area_deep_blue_disabled\" isControl=\"true\" style=\"float:left;color:#fff;width:91px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t<div style=\"display:none\" class=\"jdtz_common\" isShow=\"112||203\">\r\n");
      out.write(" \t\t\t\t\t\t\t\t<div style=\"height:12px;line-height: 12px;text-align: left;margin-left: 26px;margin-top: 18px;\">酒店</div>\r\n");
      out.write("\t \t\t\t\t\t\t\t<div style=\"height:12px;line-height: 12px;text-align: left;margin-left: 26px;margin-top:4px;\">合同台账</div>\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div style=\"height:65px;margin-top: 4px;\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"area_light_blue_disabled\" isControl=\"true\" sameWinFlg=\"1\" style=\"float:left;margin-right:4px;color:#fff;width:186px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t<div style=\"display:none\" isShow=\"101||149\">\r\n");
      out.write(" \t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/hr_system.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />人事系统\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t<div class=\"area_deep_blue_disabled\" isControl=\"true\" style=\"float:left;color:#fff;width:91px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t<div style=\"display:none\" class=\"jdhoe_common\"  isShow=\"112||201\">\r\n");
      out.write(" \t\t\t\t\t\t\t\t<div style=\"height:12px;line-height: 12px;text-align: left;margin-left: 26px;margin-top: 18px;\">酒店</div>\r\n");
      out.write("\t \t\t\t\t\t\t\t<div style=\"height:12px;line-height: 12px;text-align: left;margin-left: 26px;margin-top:4px;\">HOE库</div>\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div style=\"height:65px;margin-top: 4px;\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"area_light_blue_disabled\" isControl=\"true\" style=\"float:left;margin-right:4px;color:#fff;width:186px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t<div style=\"display:none;\" isShow=\"101||204\">\r\n");
      out.write(" \t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/desk2/home/hr_system.png\" style=\"vertical-align: middle;margin-right: 4px;margin-bottom:2px;\" />编制人员中枢\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t<div class=\"area_deep_blue_disabled\" isControl=\"true\" style=\"float:left;color:#fff;width:91px;height:65px;text-align: center;line-height: 65px;\">\r\n");
      out.write(" \t\t\t\t\t\t\t<div style=\"display:none\" class=\"jd_common\" isShow=\"112||205\">\r\n");
      out.write(" \t\t\t\t\t\t\t\t<div style=\"height:12px;line-height: 12px;text-align: left;margin-left: 26px;margin-top: 18px;\">酒店</div>\r\n");
      out.write("\t \t\t\t\t\t\t\t<div style=\"height:12px;line-height: 12px;text-align: left;margin-left: 26px;margin-top:4px;\">采购数据</div>\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t</div>\r\n");
      out.write(" \t\t\t\t\t\t\t<div class=\"div_clear\"></div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t  \t\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"noteClickMask\" onclick=\"saveNote();\" class=\"full_screen_mask\"></div>\r\n");
      out.write("<div id=\"bookClickMask\" class=\"full_screen_mask_book\"></div>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var speed = 500;   \r\n");
      out.write("var bookShowMgr;\r\n");
      out.write("var bookHideMgr;\r\n");
      out.write("var addressShowMgr;\r\n");
      out.write("var addressHideMrg;\r\n");
      out.write("var selectedObj;\r\n");
      out.write("var allMenu;\r\n");
      out.write("var n = 4;\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("$(\"#div_home_b\").blur(function(){closeBook();});\r\n");
      out.write("\r\n");
      out.write("$(\"#bookClickMask\").click(function(){\r\n");
      out.write("\tcloseBook();\r\n");
      out.write("\t$('#bookClickMask').hide();\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("//初始化\r\n");
      out.write("$(function(){\r\n");
      out.write("\tif(navigator.userAgent.indexOf('iPad') > -1){\r\n");
      out.write("\t}else if(navigator.userAgent.indexOf('8') > -1 && navigator.userAgent.indexOf('Trident/4.0') > -1){\r\n");
      out.write("\t\t$(\"#div_home_b\").find(\".div_index\").removeClass().addClass(\"div_index_ie8\");\r\n");
      out.write("\t\t$(\"#pn_content\").find(\"div[isline]\").css(\"margin-top\",\"4\");\r\n");
      out.write("\t}\r\n");
      out.write("\ttry{\r\n");
      out.write("\t\t//加载有权限使用菜单\r\n");
      out.write("\t\tvar data={roleCd:'");
      out.print(PowerUtils.array2String(SpringSecurityUtils.getCurrentRoleCds()));
      out.write("' };\r\n");
      out.write("\t\t$(\"#divXialaMenu\").append('<div id=\"loading\" style=\"padding-left:8px;\"><image src=\"'+_ctx+'/images/loading.gif\"></div>');\r\n");
      out.write("\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/app/app-authority!loadNewMenuByRoleCd.action\",data, function(result) {\r\n");
      out.write("\t\t\t$(\"#divXialaMenu\").find(\"#loading\").remove();\r\n");
      out.write("\t\t\t$(\"#divXialaMenu\").append(result);\r\n");
      out.write("\t\t\t$(\"#divXialaMenu\").find(\"div[type='moduleChild']\").each(function(){\r\n");
      out.write("\t\t\t\tif($(this).attr(\"id\") != \"child_book\"){\r\n");
      out.write("\t\t\t\t\t$(\"#addressBookList\").append($(this));\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t//加载有权限所有menu\r\n");
      out.write("\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/app/app-authority!loadAllRoleModuleMenu.action\",data, function(result) {\r\n");
      out.write("\t\t\tallMenu = eval('('+ result +')');\r\n");
      out.write("\t\t\tn++;\r\n");
      out.write("\t\t\t$(\"#div_right_fixed\").find(\"[isShow]\").each(function(){\r\n");
      out.write("\t\t\t\tvar v = allMenu[$(this).attr(\"isShow\")];\r\n");
      out.write("\t\t\t\t$(this).find(\"[type='num']\").each(function(){\r\n");
      out.write("\t\t\t\t\tn++;\r\n");
      out.write("\t\t\t\t\t$(this).html(n);\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\tif(typeof v != 'undefined'){\r\n");
      out.write("\t\t\t\t\t$(this).show();\r\n");
      out.write("\t\t\t\t\tif($(this).parent().attr(\"isControl\") == 'true'){\r\n");
      out.write("\t\t\t\t\t\tv = v.split(\"||\");\r\n");
      out.write("\t\t\t\t\t\t$(this).parent().attr(\"title\",v[2]);\r\n");
      out.write("\t\t\t\t\t\t$(this).parent().toggleClass($(this).parent().attr(\"class\").replace(\"_disabled\",\"\"));\r\n");
      out.write("\t\t\t\t\t\t//加入点击事件\r\n");
      out.write("\t\t\t\t\t\t$(this).parent().click(function(){\r\n");
      out.write("\t\t\t\t\t\t\twindow.scroll(0,0);\r\n");
      out.write("\t\t\t\t\t\t\tif(\"1\"!=$(this).attr(\"sameWinFlg\")){\r\n");
      out.write("\t\t\t\t\t\t\t\tTabUtils.newTab(v[0],v[2],\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"+v[1]);\r\n");
      out.write("\t\t\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t\t\twindow.open(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"+v[1]);\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t//将没权限菜单名字灰掉\r\n");
      out.write("\t\t\t\t\tif($(this).parent().width() == \"91\"){\r\n");
      out.write("\t\t\t\t\t\t$(this).parent().removeClass(\"area_deep_blue_disabled\").removeClass(\"area_light_blue_disabled\").addClass(\"area_light_blue_other_noright\");\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t$(this).parent().removeClass(\"area_deep_blue_disabled\").removeClass(\"area_light_blue_disabled\").addClass(\"area_light_blue_noright\");\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t$(this).css(\"color\",\"#666666\");\r\n");
      out.write("\t\t\t\t\t$(this).attr(\"title\",\"[\"+$(this).text().trim()+\"]您没有权限\");\r\n");
      out.write("\t\t\t\t\t//$(this).html($(this).text().trim());\r\n");
      out.write("\t\t\t\t\t$(this).click(function(){\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t//如果一级菜单没权限整个隐藏\r\n");
      out.write("\t\t\t\t\tif($(this).attr(\"isParent\") == \"true\"){\r\n");
      out.write("\t\t\t\t\t\t$(this).show();\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t$(this).show();\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t//加载常用模块\r\n");
      out.write("\t\t\t$(\"#div_right_fixed\").find(\"[isStatic]\").each(function(){\r\n");
      out.write("\t\t\t\t$(this).click(function(){\r\n");
      out.write("\t\t\t\t\twindow.scroll(0,0);\r\n");
      out.write("\t\t\t\t\tif(typeof allMenu[$(this).attr(\"isStatic\")] != 'undefined'){\r\n");
      out.write("\t\t\t\t\t\tvar tmp = allMenu[$(this).attr(\"isStatic\")]\r\n");
      out.write("\t\t\t\t\t\ttmp = tmp.split(\"||\");\r\n");
      out.write("\t\t\t\t\t\tTabUtils.newTab(tmp[0],tmp[2],\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"+tmp[1]);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t//遍历判断各区块的子区块，将区块下没有权限的父区块隐藏。（5月13日draco要求取消）\r\n");
      out.write("\t\t\t/*\r\n");
      out.write("\t\t\t$(\"#div_right_fixed\").find(\"[isParent='true'][display!='none']\").each(function(){\r\n");
      out.write("\t\t\t\tvar success = true;\r\n");
      out.write("\t\t\t\t$(this).find(\"[isShow]\").each(function(){\r\n");
      out.write("\t\t\t\t\tvar v = allMenu[$(this).attr(\"isShow\")]\r\n");
      out.write("\t\t\t\t\tif(typeof v != 'undefined'){\r\n");
      out.write("\t\t\t\t\t\tsuccess = false;\r\n");
      out.write("\t\t\t\t\t\treturn true;\r\n");
      out.write("\t\t\t\t\t}\t\t\t\t\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\tif(success){\r\n");
      out.write("\t\t\t\t\t$(this).hide();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t*/\r\n");
      out.write("\t\t\trefreshWin();\r\n");
      out.write("\r\n");
      out.write("\t\t\t//加载个人照片\r\n");
      out.write("\t\t\tloadUserPic();\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}catch(e){}\r\n");
      out.write("\t\r\n");
      out.write("\trefreshWin();\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("//加载个人照片\r\n");
      out.write("function loadUserPic(){\r\n");
      out.write("\tvar url = _ctx + '/desk/desk-wab!loadUserPic.action';\r\n");
      out.write("\t$.post(url, {}, function(path){\r\n");
      out.write("\t\tif('' != path){\r\n");
      out.write("\t\t\t$('#login_user_pic').html('<img style=\"width:60px; padding-top:5px; padding-bottom:5px;\" src=\"'+path+'\"/>');\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\t$('#login_user_pic').html('<img style=\"width:60px; padding-top:5px; padding-bottom:5px;\" src=\"'+_ctx+'/resources/images/desk2/div_left_03.gif\"  />' )\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//\r\n");
      out.write("function openMenu(){\r\n");
      out.write("\tvar url = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/app/app-authority!loadMenuByNewModule.action?moduleCd=105';\r\n");
      out.write("\t$.post(url,{},function(){\r\n");
      out.write("\t\tdoClickBook(this,url);\r\n");
      out.write("\t\t\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("function refreshWin(){\r\n");
      out.write("\t//自适应高度\r\n");
      out.write("\t$(\"#div_left_b,#div_center_b\").css(\"height\",$(document).height());\r\n");
      out.write("\t//$(\"#addressBookList\").css(\"height\",$(document).height());\r\n");
      out.write("\t//$(\"#div_show_address\").css(\"height\",$(document).height());\r\n");
      out.write("\t$(\"#divFrame\").css(\"height\",$(document).height()-51);\r\n");
      out.write("\t\r\n");
      out.write("\t/*\r\n");
      out.write("\t$(window).resize(function() {\r\n");
      out.write("\t\t$(\".div_left,div_center\").css(\"height\",$(document).height());\r\n");
      out.write("\t\t//$(\"#addressBookList\").css(\"height\",$(document).height());\r\n");
      out.write("\t\t$(\"#divFrame\").css(\"height\",$(document).height()-51);\r\n");
      out.write("\t\t$(\"iframe\").each(function(){\r\n");
      out.write("\t\t\t$(this).css(\"width\",$(\".div_right_title\").width());\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\t*/\r\n");
      out.write("\t$(\"#float_news\").css(\"width\",\"88px\");\r\n");
      out.write("\t$(\"#newsMarquee\").css(\"width\",\"88px\");\r\n");
      out.write("}\r\n");
      out.write("//显示通讯录\r\n");
      out.write("function showAddressTree(){\r\n");
      out.write("\t$('#wabTree').show();\r\n");
      out.write("\t$('#searchResult').hide();\r\n");
      out.write("}\r\n");
      out.write("//绑定\r\n");
      out.write("/*\r\n");
      out.write("$(\"#addressBookList\").bind(\"mouseover\",function(){\r\n");
      out.write("\t//清掉隐藏事件\r\n");
      out.write("\tif(addressHideMrg){\r\n");
      out.write("\t\tclearTimeout(addressHideMrg);\r\n");
      out.write("\t}\r\n");
      out.write("\tif(bookHideMgr){\r\n");
      out.write("\t\tclearTimeout(bookHideMgr);\r\n");
      out.write("\t}\r\n");
      out.write("\tselectedObj.css(\"background-color\",\"#1BA0E1\");\r\n");
      out.write("\tselectedObj.css(\"color\",\"#FFFFFF\");\r\n");
      out.write("\t$(this).mouseleave(function(){\r\n");
      out.write("\t\tif(addressHideMrg){\r\n");
      out.write("\t\t\tclearTimeout(addressHideMrg);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\taddressHideMrg = setTimeout(function(){\r\n");
      out.write("\t\t\t$(\"#addressBookList\").hide();\r\n");
      out.write("\t\t\t},300);\r\n");
      out.write("\t\tselectedObj.css(\"background-color\",\"\");\r\n");
      out.write("\t\t});\r\n");
      out.write("\t$(this).mouseout(function(){\r\n");
      out.write("\t});\r\n");
      out.write("});*/\r\n");
      out.write("//检查过期密码\r\n");
      out.write("function showModPasswordDlg(){\r\n");
      out.write("\tymPrompt.win( {\r\n");
      out.write("\t\tmessage : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/plaspd/plas-user-change!password.action?pwdExpiredFlag=1\",\r\n");
      out.write("\t\twidth : 450,\r\n");
      out.write("\t\theight : 240,\r\n");
      out.write("\t\tdragOut: false,\r\n");
      out.write("\t\ttitle : '密码修改提示',\r\n");
      out.write("\t\tiframe : true,\r\n");
      out.write("\t\tafterShow : function(){},\r\n");
      out.write("\t\thandler : handlerCall\r\n");
      out.write("\t});\r\n");
      out.write("} \r\n");
      out.write("function handlerCall(){\r\n");
      out.write("\tvar url = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/desk/desk!isPwdExpired.action';\r\n");
      out.write("\t$.post(url,{},function(result){\r\n");
      out.write("\t\tif('1' == result){\r\n");
      out.write("\t\t\tshowModPasswordDlg();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fset_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent(null);
    // /common/taglibs.jsp(8,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("ctx");
    // /common/taglibs.jsp(8,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f0 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f0.setParent(null);
    // /WEB-INF/content/desk2/desk-new-main.jsp(39,0) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f0.setTest("isModLastDateFlag == 1");
    int _jspx_eval_s_005fif_005f0 = _jspx_th_s_005fif_005f0.doStartTag();
    if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("showModPasswordDlg();\r\n");
        int evalDoAfterBody = _jspx_th_s_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f1 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f1.setParent(null);
    // /WEB-INF/content/desk2/desk-new-main.jsp(94,1) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setVar("num");
    // /WEB-INF/content/desk2/desk-new-main.jsp(94,1) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setValue(new String("1"));
    int _jspx_eval_c_005fset_005f1 = _jspx_th_c_005fset_005f1.doStartTag();
    if (_jspx_th_c_005fset_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f2 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f2.setParent(null);
    // /WEB-INF/content/desk2/desk-new-main.jsp(169,7) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setVar("num");
    // /WEB-INF/content/desk2/desk-new-main.jsp(169,7) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${num+1 }", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fset_005f2 = _jspx_th_c_005fset_005f2.doStartTag();
    if (_jspx_th_c_005fset_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f3 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f3.setParent(null);
    // /WEB-INF/content/desk2/desk-new-main.jsp(193,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f3.setVar("num");
    // /WEB-INF/content/desk2/desk-new-main.jsp(193,6) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f3.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${num+1 }", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fset_005f3 = _jspx_th_c_005fset_005f3.doStartTag();
    if (_jspx_th_c_005fset_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f4 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f4.setParent(null);
    // /WEB-INF/content/desk2/desk-new-main.jsp(201,7) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f4.setVar("num");
    // /WEB-INF/content/desk2/desk-new-main.jsp(201,7) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f4.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${num+1 }", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fset_005f4 = _jspx_th_c_005fset_005f4.doStartTag();
    if (_jspx_th_c_005fset_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f4);
    return false;
  }

  private boolean _jspx_meth_s_005fiterator_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_005fiterator_005f0 = (org.apache.struts2.views.jsp.IteratorTag) _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus_005fend_005fbegin.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_005fiterator_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fiterator_005f0.setParent(null);
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(16,7) name = begin type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f0.setBegin("0");
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(16,7) name = end type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f0.setEnd("7");
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(16,7) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f0.setValue("pageNote.result");
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(16,7) name = status type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f0.setStatus("st");
    int _jspx_eval_s_005fiterator_005f0 = _jspx_th_s_005fiterator_005f0.doStartTag();
    if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fiterator_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fiterator_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_s_005fif_005f1(_jspx_th_s_005fiterator_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_s_005felseif_005f0(_jspx_th_s_005fiterator_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_s_005felse_005f0(_jspx_th_s_005fiterator_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_s_005fif_005f2(_jspx_th_s_005fiterator_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_005fiterator_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fiterator_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus_005fend_005fbegin.reuse(_jspx_th_s_005fiterator_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus_005fend_005fbegin.reuse(_jspx_th_s_005fiterator_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f1 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f0);
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(17,8) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f1.setTest("noteContent == null||noteContent == ''");
    int _jspx_eval_s_005fif_005f1 = _jspx_th_s_005fif_005f1.doStartTag();
    if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<div class=\"pd_notepad note_empty\" id=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dlyNoteId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${noteContent}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"noteDisplay\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${noteContent}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</div>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005felseif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f0 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f0);
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(22,8) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f0.setTest("tipStartDate != null");
    int _jspx_eval_s_005felseif_005f0 = _jspx_th_s_005felseif_005f0.doStartTag();
    if (_jspx_eval_s_005felseif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<div class=\"pd_notepad remind\" id=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dlyNoteId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${noteContent}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"noteDisplay\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${noteContent}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</div>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t<script type=\"text/javascript\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\tvar tipDate = \"");
        if (_jspx_meth_s_005fdate_005f0(_jspx_th_s_005felseif_005f0, _jspx_page_context))
          return true;
        out.write("\";\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\tnoteTipObj[tipDate]='");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dlyNoteId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("';\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\tif(EVERY_DAY_TIP_UIIDS.contains('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${uiid}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("')){\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\tvar tipTime = \"");
        if (_jspx_meth_s_005fdate_005f1(_jspx_th_s_005felseif_005f0, _jspx_page_context))
          return true;
        out.write("\";\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\tnoteTipObj[tipTime]='");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dlyNoteId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("';\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t}\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t</script>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_005felseif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005felseif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005felseif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fdate_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felseif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:date
    org.apache.struts2.views.jsp.DateTag _jspx_th_s_005fdate_005f0 = (org.apache.struts2.views.jsp.DateTag) _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.get(org.apache.struts2.views.jsp.DateTag.class);
    _jspx_th_s_005fdate_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fdate_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felseif_005f0);
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(26,26) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f0.setName("tipStartDate");
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(26,26) name = format type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f0.setFormat("yyyyMMddHHmm");
    int _jspx_eval_s_005fdate_005f0 = _jspx_th_s_005fdate_005f0.doStartTag();
    if (_jspx_th_s_005fdate_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fdate_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felseif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:date
    org.apache.struts2.views.jsp.DateTag _jspx_th_s_005fdate_005f1 = (org.apache.struts2.views.jsp.DateTag) _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.get(org.apache.struts2.views.jsp.DateTag.class);
    _jspx_th_s_005fdate_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fdate_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felseif_005f0);
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(29,27) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f1.setName("tipStartDate");
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(29,27) name = format type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f1.setFormat("HHmm");
    int _jspx_eval_s_005fdate_005f1 = _jspx_th_s_005fdate_005f1.doStartTag();
    if (_jspx_th_s_005fdate_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005felse_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:else
    org.apache.struts2.views.jsp.ElseTag _jspx_th_s_005felse_005f0 = (org.apache.struts2.views.jsp.ElseTag) _005fjspx_005ftagPool_005fs_005felse.get(org.apache.struts2.views.jsp.ElseTag.class);
    _jspx_th_s_005felse_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005felse_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f0);
    int _jspx_eval_s_005felse_005f0 = _jspx_th_s_005felse_005f0.doStartTag();
    if (_jspx_eval_s_005felse_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felse_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felse_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felse_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<div class=\"pd_notepad note_noremind\" id=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dlyNoteId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${noteContent}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"noteDisplay\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${noteContent}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</div>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_005felse_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005felse_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005felse_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f2 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f0);
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(40,8) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f2.setTest("#st.Odd");
    int _jspx_eval_s_005fif_005f2 = _jspx_th_s_005fif_005f2.doStartTag();
    if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<div style=\"float:left;width:4px;\">&nbsp;</div>\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_005fif_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f2);
    return false;
  }

  private boolean _jspx_meth_s_005fiterator_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_005fiterator_005f1 = (org.apache.struts2.views.jsp.IteratorTag) _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus_005fend_005fbegin.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_005fiterator_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fiterator_005f1.setParent(null);
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(47,7) name = begin type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f1.setBegin("8");
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(47,7) name = end type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f1.setEnd("15");
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(47,7) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f1.setValue("pageNote.result");
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(47,7) name = status type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f1.setStatus("st");
    int _jspx_eval_s_005fiterator_005f1 = _jspx_th_s_005fiterator_005f1.doStartTag();
    if (_jspx_eval_s_005fiterator_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fiterator_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fiterator_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fiterator_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_s_005fif_005f3(_jspx_th_s_005fiterator_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_s_005felseif_005f1(_jspx_th_s_005fiterator_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_s_005felse_005f1(_jspx_th_s_005fiterator_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_s_005fif_005f4(_jspx_th_s_005fiterator_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_005fiterator_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fiterator_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fiterator_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus_005fend_005fbegin.reuse(_jspx_th_s_005fiterator_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus_005fend_005fbegin.reuse(_jspx_th_s_005fiterator_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f3 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f1);
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(48,8) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f3.setTest("noteContent == null||noteContent == ''");
    int _jspx_eval_s_005fif_005f3 = _jspx_th_s_005fif_005f3.doStartTag();
    if (_jspx_eval_s_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f3.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<div class=\"pd_notepad note_empty\" id=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dlyNoteId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${noteContent}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"noteDisplay\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${noteContent}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</div>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_005fif_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f3);
    return false;
  }

  private boolean _jspx_meth_s_005felseif_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f1 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f1);
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(53,8) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f1.setTest("tipStartDate != null");
    int _jspx_eval_s_005felseif_005f1 = _jspx_th_s_005felseif_005f1.doStartTag();
    if (_jspx_eval_s_005felseif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<div class=\"pd_notepad remind\" id=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dlyNoteId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${noteContent}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"noteDisplay\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${noteContent}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</div>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t<script type=\"text/javascript\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\tvar tipDate = \"");
        if (_jspx_meth_s_005fdate_005f2(_jspx_th_s_005felseif_005f1, _jspx_page_context))
          return true;
        out.write("\";\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\tnoteTipObj[tipDate]='");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dlyNoteId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("';\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\tif(EVERY_DAY_TIP_UIIDS.contains('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${uiid}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("')){\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\tvar tipTime = \"");
        if (_jspx_meth_s_005fdate_005f3(_jspx_th_s_005felseif_005f1, _jspx_page_context))
          return true;
        out.write("\";\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\tnoteTipObj[tipTime]='");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dlyNoteId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("';\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t}\r\n");
        out.write("\t\t\t\t\t\t\t\t\t</script>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_005felseif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005felseif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005felseif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005fdate_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felseif_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:date
    org.apache.struts2.views.jsp.DateTag _jspx_th_s_005fdate_005f2 = (org.apache.struts2.views.jsp.DateTag) _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.get(org.apache.struts2.views.jsp.DateTag.class);
    _jspx_th_s_005fdate_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fdate_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felseif_005f1);
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(57,26) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f2.setName("tipStartDate");
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(57,26) name = format type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f2.setFormat("yyyyMMddHHmm");
    int _jspx_eval_s_005fdate_005f2 = _jspx_th_s_005fdate_005f2.doStartTag();
    if (_jspx_th_s_005fdate_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f2);
    return false;
  }

  private boolean _jspx_meth_s_005fdate_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felseif_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:date
    org.apache.struts2.views.jsp.DateTag _jspx_th_s_005fdate_005f3 = (org.apache.struts2.views.jsp.DateTag) _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.get(org.apache.struts2.views.jsp.DateTag.class);
    _jspx_th_s_005fdate_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005fdate_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felseif_005f1);
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(60,27) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f3.setName("tipStartDate");
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(60,27) name = format type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f3.setFormat("HHmm");
    int _jspx_eval_s_005fdate_005f3 = _jspx_th_s_005fdate_005f3.doStartTag();
    if (_jspx_th_s_005fdate_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f3);
    return false;
  }

  private boolean _jspx_meth_s_005felse_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:else
    org.apache.struts2.views.jsp.ElseTag _jspx_th_s_005felse_005f1 = (org.apache.struts2.views.jsp.ElseTag) _005fjspx_005ftagPool_005fs_005felse.get(org.apache.struts2.views.jsp.ElseTag.class);
    _jspx_th_s_005felse_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005felse_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f1);
    int _jspx_eval_s_005felse_005f1 = _jspx_th_s_005felse_005f1.doStartTag();
    if (_jspx_eval_s_005felse_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felse_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felse_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felse_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<div class=\"pd_notepad note_noremind\" id=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dlyNoteId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${noteContent}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"noteDisplay\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${noteContent}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</div>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_005felse_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005felse_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005felse_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f4 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f1);
    // /WEB-INF/content/desk2/desk-new-notepad.jsp(71,8) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f4.setTest("#st.Odd");
    int _jspx_eval_s_005fif_005f4 = _jspx_th_s_005fif_005f4.doStartTag();
    if (_jspx_eval_s_005fif_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f4.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<div style=\"float:left;width:4px;\">&nbsp;</div>\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_005fif_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f4);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f5 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f5.setParent(null);
    // /WEB-INF/content/desk2/desk-new-main.jsp(218,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f5.setVar("num");
    // /WEB-INF/content/desk2/desk-new-main.jsp(218,6) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f5.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${num+1 }", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fset_005f5 = _jspx_th_c_005fset_005f5.doStartTag();
    if (_jspx_th_c_005fset_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f5);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f6(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f6 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f6.setParent(null);
    // /WEB-INF/content/desk2/desk-new-common.jsp(5,7) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f6.setVar("num");
    // /WEB-INF/content/desk2/desk-new-common.jsp(5,7) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f6.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${num+1 }", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fset_005f6 = _jspx_th_c_005fset_005f6.doStartTag();
    if (_jspx_th_c_005fset_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f6);
    return false;
  }
}
