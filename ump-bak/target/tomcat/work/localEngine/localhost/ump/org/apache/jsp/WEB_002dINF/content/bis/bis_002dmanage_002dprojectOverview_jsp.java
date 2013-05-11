package org.apache.jsp.WEB_002dINF.content.bis;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

public final class bis_002dmanage_002dprojectOverview_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(6);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/common/nocache.jsp");
    _jspx_dependants.add("/common/global.jsp");
    _jspx_dependants.add("/WEB-INF/content/bis/bis-manage-version.jsp");
    _jspx_dependants.add("/WEB-INF/content/bis/bis-manage-overviewMall.jsp");
    _jspx_dependants.add("/WEB-INF/PowerDesk-tags.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fset_0026_005fvar;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifNotGranted;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fset_0026_005fvar = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifNotGranted = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.release();
    _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.release();
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus.release();
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifNotGranted.release();
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("    ");
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
      out.write("    ");

	response.setHeader("Expires","0");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragrma", "no-cache");
	response.setDateHeader("Expires", 0);

      out.write("\r\n");
      out.write("    ");
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
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/base.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/common.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/desk/thickbox.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/bis/bis-manage.css\"/>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/desk/MaskLayer.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/datePicker/WdatePicker.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/bis/bis.project.select.js\"></script>\r\n");
      out.write("    <title>项目总况</title>\r\n");
      out.write("    <style type=\"text/css\">\r\n");
      out.write("        html div.btns{padding-right:5px;}\r\n");
      out.write("        html div.btns button{margin: 0px;padding:0px;}\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body onload=\"adjustHeight();\">\r\n");
      out.write("<div id=\"warp\">\r\n");
      out.write("    <div id=\"header\">\r\n");
      out.write("        <div class=\"title1 clearfix\">\r\n");
      out.write("\r\n");
      out.write("            <h2 style=\"width: auto;margin-right: 10px; float: left;\">项目总况</h2>\r\n");
      out.write("\r\n");
      out.write("            <div style=\"float:left; padding-top: 13px;\">\r\n");
      out.write("                ");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");

	response.setHeader("Expires","0");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragrma", "no-cache");
	response.setDateHeader("Expires", 0);

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
      out.write("\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>版本更新</title>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/prompt/skin/pd/ymPrompt.css\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/prompt/ymPrompt.js\"></script>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("#btn_version{cursor: pointer;float:left;    text-decoration:underline;;color:#0167A2; font-size:12px;}\r\n");
      out.write(".div_span{width:1px;height:4px;color:#0167a2;display:list-item;float:left;margin-left:25px;}\r\n");
      out.write(".div_info{display:none;}\r\n");
      out.write(".div_date{float:left;width:70px;}\r\n");
      out.write(".div_title{height:23px;line-height:23px;width:70px;margin-right:10px;}\r\n");
      out.write(".div_content{height:23x;line-height:23px;}\r\n");
      out.write(".div_title font{ margin-right:5px;}\r\n");
      out.write("\r\n");
      out.write(".tab{margin:15px;}\r\n");
      out.write(".tab tr{ border-bottom: 1px solid #DDDBDC;}\r\n");
      out.write(".tab tr td:hover{border-bottom: 1px solid #DDDBDC;background:#DDDBDC;color:#40a4be; }\r\n");
      out.write("\r\n");
      out.write(".div_ul{list-style:outside none;margin-bottom:15px;}\r\n");
      out.write(".div_ul li{list-style:outside none;}\r\n");
      out.write(".div_li{height:20px;line-height:20px;}\r\n");
      out.write("</style>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function ver(){\r\n");
      out.write("\tymPrompt.confirmInfo( {\r\n");
      out.write("\t\ticoCls : \"\",\r\n");
      out.write("\t\tautoClose:false,\r\n");
      out.write("\t\tmessage : \"<div id='selectTypeDiv'><img align='absMiddle' src='\"+ _ctx + \"/images/loading.gif'></div>\",\r\n");
      out.write("\t\twidth : 500,\r\n");
      out.write("\t\theight :400,\r\n");
      out.write("\t\ttitle : \"功能更新提醒\",\r\n");
      out.write("\t\tcloseBtn:true,\r\n");
      out.write("\t\tafterShow : function() {\r\n");
      out.write("\t\t\t$(\"#selectTypeDiv\").html($('.div_info').html());\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\thandler : function(btn){\r\n");
      out.write("\t\t\tif(btn=='ok'){\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\tymPrompt.close();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tbtn:[[\"关闭\",'cancel']]\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div id=\"btn_version\" onclick=\"ver();\" style=\"display:none;\"><span class=\"div_span\"></span>1月10日系统更新提示:租赁合同自动生成应收。</div>\r\n");
      out.write("<div class=\"div_info\">\r\n");
      out.write("\t<table class=\"tab\">\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<ul class=\"div_ul\">\r\n");
      out.write("\t\t\t\t<li class=\"div_li\"><span class=\"div_title\"><font>1月10日</font><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/common/new.gif\" class=\"new_img\"></span></li>\r\n");
      out.write("\t\t\t\t<li><span class=\"div_content\">租赁合同增加自动生成应收功能。</span></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<ul class=\"div_ul\">\r\n");
      out.write("\t\t\t\t<li class=\"div_li\"><span class=\"div_title\"><font>1月8日</font><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/common/new.gif\" class=\"new_img\"></span></li>\r\n");
      out.write("\t\t\t\t<li><span class=\"div_content\">项目总况增加累计搜索功能。</span></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<ul class=\"div_ul\">\r\n");
      out.write("\t\t\t\t<li class=\"div_li\"><span class=\"div_title\"><font>1月8日</font><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/common/new.gif\" class=\"new_img\"></span></li>\r\n");
      out.write("\t\t\t\t<li><span class=\"div_content\">平面图增加欠费列表功能。</span></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<ul class=\"div_ul\">\r\n");
      out.write("\t\t\t\t<li class=\"div_li\"><span class=\"div_title\"><font>12月16日</font><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/common/new.gif\" class=\"new_img\"></span></li>\r\n");
      out.write("\t\t\t\t<li><span class=\"div_content\">新增租户退铺功能。</span></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<ul class=\"div_ul\">\r\n");
      out.write("\t\t\t\t<li class=\"div_li\"><span class=\"div_title\"><font>12月8日</font><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/common/new.gif\" class=\"new_img\"></span></li>\r\n");
      out.write("\t\t\t\t<li><span class=\"div_content\">合同模块增加有无应收的搜索。</span></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<ul class=\"div_ul\">\r\n");
      out.write("\t\t\t\t<li class=\"div_li\"><span class=\"div_title\"><font>12月5日</font><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/common/new.gif\" class=\"new_img\"></span></li>\r\n");
      out.write("\t\t\t\t<li><span class=\"div_content\">平面图模块商家商铺历史记录搜索功能。</span></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<ul class=\"div_ul\">\r\n");
      out.write("\t\t\t\t<li class=\"div_li\"><span class=\"div_title\"><font>12月2日</font><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/common/new.gif\" class=\"new_img\"></span></li>\r\n");
      out.write("\t\t\t\t<li><span class=\"div_content\">项目模块增加广告图功能。</span></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<ul class=\"div_ul\">\r\n");
      out.write("\t\t\t\t<li class=\"div_li\"><span class=\"div_title\"><font>12月2日</font><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/common/new.gif\" class=\"new_img\"></span></li>\r\n");
      out.write("\t\t\t\t<li><span class=\"div_content\">合同增加内部批文。</span></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<ul class=\"div_ul\">\r\n");
      out.write("\t\t\t\t<li class=\"div_li\"><span class=\"div_title\"><font>12月2日</font><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/common/new.gif\" class=\"new_img\"></span></li>\r\n");
      out.write("\t\t\t\t<li><span class=\"div_content\">费用模块增加导入导出功能。</span></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<ul class=\"div_ul\">\r\n");
      out.write("\t\t\t\t<li class=\"div_li\"><span class=\"div_title\"><font>11月27日</font><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/common/new.gif\" class=\"new_img\"></span></li>\r\n");
      out.write("\t\t\t\t<li><span class=\"div_content\">收费明细新增预收功能。</span></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<ul class=\"div_ul\">\r\n");
      out.write("\t\t\t\t<li class=\"div_li\"><span class=\"div_title\"><font>11月14日</font><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/common/new.gif\" class=\"new_img\"></span></li>\r\n");
      out.write("\t\t\t\t<li><span class=\"div_content\">商家库新增商家资源时，商家类别只允许对‘末级节点’进行勾选。</span></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<ul class=\"div_ul\">\r\n");
      out.write("\t\t\t\t\t<li class=\"div_li\"><span class=\"div_title\"><font>11月7日</font><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/common/new.gif\" class=\"new_img\"></span></li>\r\n");
      out.write("\t\t\t\t\t<li><span class=\"div_content\">增加商家库评级功能。</span></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<ul class=\"div_ul\">\r\n");
      out.write("\t\t\t\t\t<li class=\"div_li\"><span class=\"div_title\"><font>11月7日</font><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/common/new.gif\" class=\"new_img\"></span></li>\r\n");
      out.write("\t\t\t\t\t<li><span class=\"div_content\">增加商家库品牌名重复判断功能。</span></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<ul class=\"div_ul\">\r\n");
      out.write("\t\t\t\t\t<li class=\"div_li\"><span class=\"div_title\"><font>10月31日</font><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/common/new.gif\" class=\"new_img\"></span></li>\r\n");
      out.write("\t\t\t\t\t<li><span class=\"div_content\">增加商家合并功能，总部可进行商家合并。</span></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<ul class=\"div_ul\">\r\n");
      out.write("\t\t\t\t<li class=\"div_li\"><span class=\"div_title\"><font>10月22日</font><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/common/new.gif\" class=\"new_img\"></span></li>\r\n");
      out.write("\t\t\t\t<li><span class=\"div_content\">增加业主物业合同。已售商铺的业主物业合同可录入系统。</span></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<ul class=\"div_ul\">\r\n");
      out.write("\t\t\t\t<li class=\"div_li\"><span class=\"div_title\"><font>10月18日</font><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/common/new.gif\" class=\"new_img\"></span></li>\r\n");
      out.write("\t\t\t\t<li><span class=\"div_content\">增加租户台账功能。在平面图可切换至租户台账。</span></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<ul class=\"div_ul\">\r\n");
      out.write("\t\t\t\t<li class=\"div_li\"><span class=\"div_title\"><font>10月12日</font><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/common/new.gif\" class=\"new_img\"></span></li>\r\n");
      out.write("\t\t\t\t<li><span class=\"div_content\">实收款项批量自动生成功能。在“收费明细”-->“应收明细”列表下方，新增“自动生成实收”按钮，可批量自动生成实收</span></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<ul class=\"div_ul\">\r\n");
      out.write("\t\t\t\t<li class=\"div_li\"><span class=\"div_title\"><font>10月9日</font></span></li>\r\n");
      out.write("\t\t\t\t<li><span class=\"div_content\">能源费应收导入功能完善。能源费应收款项每月批量导入系统，实收在“收费明细”中实际录入</span></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<ul class=\"div_ul\">\r\n");
      out.write("\t\t\t\t<li class=\"div_li\"><span class=\"div_title\"><font>9月29日</font></span></li>\r\n");
      out.write("\t\t\t\t<li><span class=\"div_content\">增加实收批量审核功能</span></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("            <div class=\"btns\">\r\n");
      out.write("\r\n");
      out.write("                ");
      out.write("\r\n");
      out.write("                \r\n");
      out.write("                ");
      out.write("\r\n");
      out.write("\t\t\t\t");
      out.write("\r\n");
      out.write("                ");
      if (_jspx_meth_security_005fauthorize_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                ");
      out.write("\r\n");
      out.write("                ");
      if (_jspx_meth_security_005fauthorize_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                ");
      if (_jspx_meth_security_005fauthorize_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                ");
      if (_jspx_meth_security_005fauthorize_005f3(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                ");
      if (_jspx_meth_security_005fauthorize_005f4(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                ");
      if (_jspx_meth_security_005fauthorize_005f5(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                ");
      if (_jspx_meth_security_005fauthorize_005f6(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                <!--button class=\"blue\" type=\"button\">支出明细</button>\r\n");
      out.write("              <button class=\"blue\" type=\"button\">收入明细</button>\r\n");
      out.write("              <button class=\"blue\" type=\"button\">合同台帐</button>\r\n");
      out.write("              <button class=\"blue\" type=\"button\">商家库</button-->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <style text=\"text/css\">\r\n");
      out.write("        #bis_tbs {\r\n");
      out.write("            margin: 10px;\r\n");
      out.write("            margin-bottom: 0px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        #bis_tbs a {\r\n");
      out.write("            float: left;\r\n");
      out.write("            border: none;\r\n");
      out.write("            background: #cbcbcb;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        #bis_tbs a.selected {\r\n");
      out.write("            background: #2D8BEF;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        #bis_tbs a.selected:hover {\r\n");
      out.write("            color: #fff;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        #bis_tbs a:hover {\r\n");
      out.write("            color: #000;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("    <div id=\"bis_tbs\" class=\"clearfix\">\r\n");
      out.write("        <a href=\"javascript://\" onclick=\"toggleBottomType(this,1);\" class=\"btn selected\" style=\"margin-right:5px;\">商业</a>\r\n");
      out.write("        <a href=\"javascript://\" onclick=\"toggleBottomType(this,2);\" class=\"btn\" >广告</a>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      //  s:set
      org.apache.struts2.views.jsp.SetTag _jspx_th_s_005fset_005f0 = (org.apache.struts2.views.jsp.SetTag) _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.get(org.apache.struts2.views.jsp.SetTag.class);
      _jspx_th_s_005fset_005f0.setPageContext(_jspx_page_context);
      _jspx_th_s_005fset_005f0.setParent(null);
      // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(125,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_s_005fset_005f0.setVar("curUserCd");
      int _jspx_eval_s_005fset_005f0 = _jspx_th_s_005fset_005f0.doStartTag();
      if (_jspx_eval_s_005fset_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_s_005fset_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_s_005fset_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_s_005fset_005f0.doInitBody();
        }
        do {
          out.print(SpringSecurityUtils.getCurrentUiid());
          out.write('\r');
          out.write('\n');
          int evalDoAfterBody = _jspx_th_s_005fset_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_s_005fset_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.popBody();
        }
      }
      if (_jspx_th_s_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.reuse(_jspx_th_s_005fset_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.reuse(_jspx_th_s_005fset_005f0);
      out.write("\r\n");
      out.write("<div id=\"mainDiv\" style=\"overflow-x:hidden;\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div id=\"overviewInfoDiv\" style=\"height:160px;background-color:#e4e7ec;margin:10px 8px;\">\r\n");
      out.write("    <input type=\"hidden\" id=\"bisProjectId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("    <table class=\"tb_content\">\r\n");
      out.write("        <col width=\"90\"/>\r\n");
      out.write("        <col width=\"120\"/>\r\n");
      out.write("        <col width=\"100\"/>\r\n");
      out.write("        <col width=\"130\"/>\r\n");
      out.write("        <col width=\"130\"/>\r\n");
      out.write("        <col/>\r\n");
      out.write("        <tr>\r\n");
      out.write("            ");
      if (_jspx_meth_s_005fset_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            <th colspan=\"8\" style=\"text-align:left;padding-left:8px;\">\r\n");
      out.write("                正在查看： ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${area}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("&nbsp;>&nbsp;<span\r\n");
      out.write("                    ");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write(" style=\"font-size: 14px; color: red;\"\r\n");
      out.write("                    id=\"btn_changeProject\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</span></font>\r\n");
      out.write("                <input id=\"conditionType\" type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${conditionType}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<span id=\"byMonth\"\r\n");
      out.write("                      ");
      if (_jspx_meth_s_005fif_005f1(_jspx_page_context))
        return;
      out.write(" >\r\n");
      out.write("\t\t\t\t&nbsp;&nbsp; <input class=\"Wdate\" style=\"width: 70px;\" type=\"text\" id=\"reportDate\" value='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${reportDate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("'\r\n");
      out.write("                                    onfocus=\"WdatePicker({dateFmt:'yyyy-MM'});\"/>\r\n");
      out.write("\t\t\t\t&nbsp;&nbsp;<input type=\"button\" class=\"btn_blue\" style=\"width:75px; margin-left: 5px\" value=\"按时间段查\"\r\n");
      out.write("                                   onclick=\"changeCondition(1);\"/>\r\n");
      out.write("\t\t\t\t</span>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<span id=\"byTimePeriod\"\r\n");
      out.write("                      ");
      if (_jspx_meth_s_005fif_005f2(_jspx_page_context))
        return;
      out.write(" >\r\n");
      out.write("\t\t\t\t&nbsp;&nbsp;<input class=\"Wdate\" style=\"width: 75px;\" type=\"text\" id=\"startDate\" value='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${startDate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("'\r\n");
      out.write("                                   onfocus=\"WdatePicker({dateFmt:'yyyy-MM'});\"/>\r\n");
      out.write("\t\t\t\t&nbsp;-&nbsp;<input class=\"Wdate\" style=\"width: 75px;\" type=\"text\" id=\"endDate\" value='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${endDate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("'\r\n");
      out.write("                                    onfocus=\"WdatePicker({dateFmt:'yyyy-MM'});\"/>\r\n");
      out.write("\t\t\t\t&nbsp;&nbsp;<input type=\"button\" class=\"btn_blue\" style=\"width:75px;\" value=\"按月查\"\r\n");
      out.write("                                   onclick=\"changeCondition(0);\"/>\r\n");
      out.write("\t\t\t\t</span>\r\n");
      out.write("\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f3(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            </th>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td class=\"td_title_border\">MALL面积：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f4(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            </td>\r\n");
      out.write("            <td class=\"td_title_border\">租约率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f5(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("            <td class=\"td_title_border\">平均租金单价：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f6(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td class=\"td_title_border\">自留物业：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f7(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("            <td class=\"td_title_border\">出售物业：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f8(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("            <td class=\"td_title_border\">平均物业单价：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f9(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td class=\"td_title_border\">返租率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f10(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("            <td class=\"td_title_border\">返租租金单价：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f11(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("            <td class=\"td_title_border\">返租收支平均差额：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f12(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td class=\"td_title_border\">租金收款率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f13(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("            <td class=\"td_title_border\">能耗收款率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f14(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("            <td class=\"td_title_border\">物业收款率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f15(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td class=\"td_title\" style=\"font-weight:bolder;\">项目业态：</td>\r\n");
      out.write("            <td colspan=\"5\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${totalInfo.projectLayout}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("&nbsp; </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div>\r\n");
      out.write("<div id=\"floorDiv\" style=\"float:left; width:360px; padding-left:8px;\">\r\n");
      out.write("    ");
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_005fset_005f1(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');

	response.setHeader("Expires","0");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragrma", "no-cache");
	response.setDateHeader("Expires", 0);

      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_s_005fiterator_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"baseInfoDiv\" style=\"float:left; margin-left:8px; padding-right:8px;\">\r\n");
      out.write("<div style=\"border-bottom: 1px solid #cccccc; padding:10px 0 5px;\">\r\n");
      out.write("    <table class=\"tb_content\">\r\n");
      out.write("        <col width=\"10\"/>\r\n");
      out.write("        <col width=\"70\"/>\r\n");
      out.write("        <col width=\"120\"/>\r\n");
      out.write("        <col width=\"80\"/>\r\n");
      out.write("        <col/>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td colspan=\"5\" class=\"td_header link\"\r\n");
      out.write("                ");
      if (_jspx_meth_security_005fauthorize_005f9(_jspx_page_context))
        return;
      out.write(" >\r\n");
      out.write("                <font style=\"font-weight:bolder;\">主力店</font>&nbsp;\r\n");
      out.write("                (面积：");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mainStoreInfo.square}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("㎡&nbsp;&nbsp; 商家总数：");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mainStoreInfo.storeCount}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("&nbsp;&nbsp;)\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td></td>\r\n");
      out.write("            <td class=\"td_title\">租约率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f20(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("            <td class=\"td_title\"> 租金收款率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f21(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td></td>\r\n");
      out.write("            <td class=\"td_title\">返租率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f22(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("            <td class=\"td_title\">能耗收款率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f23(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td></td>\r\n");
      out.write("            <td class=\"td_title\">租金单价：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f24(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("            <td class=\"td_title\">物业收款率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f25(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("</div>\r\n");
      out.write("<div style=\"border-bottom: 1px solid #cccccc; padding:10px 0 5px;\">\r\n");
      out.write("    <table class=\"tb_content\">\r\n");
      out.write("        <col width=\"10\"/>\r\n");
      out.write("        <col width=\"70\"/>\r\n");
      out.write("        <col width=\"120\"/>\r\n");
      out.write("        <col width=\"80\"/>\r\n");
      out.write("        <col/>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td colspan=\"5\" class=\"td_header link\"\r\n");
      out.write("                ");
      if (_jspx_meth_security_005fauthorize_005f10(_jspx_page_context))
        return;
      out.write(" >\r\n");
      out.write("                <font style=\"font-weight:bolder;\">次主力店</font>&nbsp;\r\n");
      out.write("                (面积：");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${minorStoreInfo.square}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("㎡&nbsp;&nbsp; 商家总数：");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${minorStoreInfo.storeCount}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("&nbsp;&nbsp;)\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td></td>\r\n");
      out.write("            <td class=\"td_title\">租约率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f26(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("            <td class=\"td_title\"> 租金收款率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f27(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td></td>\r\n");
      out.write("            <td class=\"td_title\">返租率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f28(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("            <td class=\"td_title\">能耗收款率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f29(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td></td>\r\n");
      out.write("            <td class=\"td_title\">租金单价：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f30(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("            <td class=\"td_title\">物业收款率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f31(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("</div>\r\n");
      out.write("<div style=\"border-bottom: 1px solid #cccccc; padding:10px 0 5px;\">\r\n");
      out.write("    <table class=\"tb_content\">\r\n");
      out.write("        <col width=\"10\"/>\r\n");
      out.write("        <col width=\"70\"/>\r\n");
      out.write("        <col width=\"120\"/>\r\n");
      out.write("        <col width=\"80\"/>\r\n");
      out.write("        <col/>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td colspan=\"5\" class=\"td_header link\"\r\n");
      out.write("                ");
      if (_jspx_meth_security_005fauthorize_005f11(_jspx_page_context))
        return;
      out.write(" >\r\n");
      out.write("                <font style=\"font-weight:bolder;\">小商铺</font>&nbsp;\r\n");
      out.write("                (面积：");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${smallStoreInfo.square}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("㎡&nbsp;&nbsp; 商家总数：");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${smallStoreInfo.storeCount}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("&nbsp;&nbsp;)\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td></td>\r\n");
      out.write("            <td class=\"td_title\">租约率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f32(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("            <td class=\"td_title\"> 租金收款率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f33(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td></td>\r\n");
      out.write("            <td class=\"td_title\">返租率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f34(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("            <td class=\"td_title\">能耗收款率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f35(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td></td>\r\n");
      out.write("            <td class=\"td_title\">租金单价：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f36(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("            <td class=\"td_title\">物业收款率：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fif_005f37(_jspx_page_context))
        return;
      out.write("&nbsp;\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"layoutDiv\" style=\"padding:10px 0 5px;\">\r\n");
      out.write("    ");
      if (_jspx_meth_security_005fauthorize_005f12(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("    isEmpty = function (str) {\r\n");
      out.write("        return (typeof (str) === \"undefined\" || str === null || (str.length === 0));\r\n");
      out.write("    };\r\n");
      out.write("\r\n");
      out.write("    function toggleBottomType(dom, index) {\r\n");
      out.write("        $(dom).parent().find(\"a\").removeClass(\"selected\");\r\n");
      out.write("        $(dom).addClass(\"selected\");\r\n");
      out.write("        var url;\r\n");
      out.write("        var data = {\r\n");
      out.write("            bisProjectId:$(\"#bisProjectId\").val(),\r\n");
      out.write("            reportDate:$(\"#reportDate\").val()\r\n");
      out.write("        };\r\n");
      out.write("        if (index == 1) { //MALL\r\n");
      out.write("            url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-manage!overviewMall.action\";\r\n");
      out.write("        } else if (index == 2) { //广告\r\n");
      out.write("            url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-manage!overviewMulti.action\";\r\n");
      out.write("        }\r\n");
      out.write("        $.post(url, data, function (result) {\r\n");
      out.write("            $(\"#floorDiv\").html(result);\r\n");
      out.write("            autoHeight();\r\n");
      out.write("        });\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    function toBisTenant(bisFloorId, bisFloorVirId) {\r\n");
      out.write("        var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-tenant!main.action?bisFloorId=\" + bisFloorId + \"&bisProjectId=\" + $(\"#bisProjectId\").val();\r\n");
      out.write("        if (bisFloorVirId) {\r\n");
      out.write("            url += \"&bisFloorVirId=\" + bisFloorVirId;\r\n");
      out.write("        }\r\n");
      out.write("        if (parent.TabUtils == null)\r\n");
      out.write("            window.open(url);\r\n");
      out.write("        else\r\n");
      out.write("            parent.TabUtils.newTab(\"bisTenant\", \"商业租户\", url, true);\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    function toBisAD(bisFloorId) {\r\n");
      out.write("        var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-tenant!loadAdMain.action?bisFloorId=\" + bisFloorId + \"&bisProjectId=\" + $(\"#bisProjectId\").val();\r\n");
      out.write("        if (parent.TabUtils == null)\r\n");
      out.write("            window.open(url);\r\n");
      out.write("        else\r\n");
      out.write("            parent.TabUtils.newTab(\"bisTenant\", \"商业租户\", url, true);\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    function adjustHeight() {\r\n");
      out.write("    /*\r\n");
      out.write("        var height = $(window).height() - 225;\r\n");
      out.write("        //alert($(\"#floorDiv\").height());\r\n");
      out.write("        //alert($(\"#baseInfoDiv\").height());\r\n");
      out.write("        $(\"#mainDiv\").height($(window).height() - 38);\r\n");
      out.write("        if ($(\"#floorDiv\").height() > height || $(\"#baseInfoDiv\").height() > height) {\r\n");
      out.write("            //if($(\"#footerDiv\").hasClass(\"bottom_div\")) {\r\n");
      out.write("            //\t$(\"#footerDiv\").removeClass(\"bottom_div\");\r\n");
      out.write("            //}\r\n");
      out.write("            $(\"#footerDiv\").addClass(\"followed_div\");\r\n");
      out.write("        } else {\r\n");
      out.write("            //$(\"#footerDiv\").removeClass(\"followed_div\");\r\n");
      out.write("            $(\"#footerDiv\").addClass(\"bottom_div\");\r\n");
      out.write("        }\r\n");
      out.write("        */\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    function clkFullScreen() {\r\n");
      out.write("        var bisProjectId = $(\"#bisProjectId\").val();\r\n");
      out.write("        var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-manage!toProjectOverview.action?bisProjectId=\" + $(\"#bisProjectId\").val() + \"&reportDate=\" + $(\"#reportDate\").val();\r\n");
      out.write("        window.open(url);\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    function showLayoutFile(id, fieldId) {\r\n");
      out.write("        if (isEmpty(id)) {\r\n");
      out.write("            return;\r\n");
      out.write("        }\r\n");
      out.write("        var url = _ctx + \"/app/app-attachment!bisShow.action\";\r\n");
      out.write("        var data = {\r\n");
      out.write("            bizEntityId:id,\r\n");
      out.write("            bizModuleCd:\"bisProjectLayout\"\r\n");
      out.write("        };\r\n");
      out.write("        $.post(url, data, function (result) {\r\n");
      out.write("            $(\"#show_\" + fieldId).html(result);\r\n");
      out.write("            autoHeight();\r\n");
      out.write("        });\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    function toReport(manageCd) {\r\n");
      out.write("\r\n");
      out.write("        /*\r\n");
      out.write("        var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-manage!layout.action?module=2&bisProjectId=\" + $(\"#bisProjectId\").val() + \"&reportType=store&manageCd=\" + manageCd;\r\n");
      out.write("        if (parent.TabUtils == null)\r\n");
      out.write("            window.open(url);\r\n");
      out.write("        else\r\n");
      out.write("            parent.TabUtils.newTab(\"bisProjectOverview\", \"项目报表\", url, true);\r\n");
      out.write("         */\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        var tab = parent.TabUtils;\r\n");
      out.write("        var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-manage!reports.action?store=true&bisProjectId=\"+$(\"#bisProjectId\").val()+\"&manageCd=\" + manageCd;;\r\n");
      out.write("        if (parent.TabUtils == null)\r\n");
      out.write("            window.open(url);\r\n");
      out.write("        else {\r\n");
      out.write("            //parent.TabUtils.newTab(\"19870422\", \"报表首页\", url, true);\r\n");
      out.write("            //tab.closeTab({data:{tabId:\"195\"}});\r\n");
      out.write("            tab.newTab(\"195\", \"报表首页\", url, true);\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    /**\r\n");
      out.write("     * 更新总况数据\r\n");
      out.write("     */\r\n");
      out.write("    function refreshData() {\r\n");
      out.write("        TB_showMaskLayer(\"正在更新...\");\r\n");
      out.write("        var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-manage!refreshData.action\";\r\n");
      out.write("        $.post(url, {bisProjectId:$(\"#bisProjectId\").val(), reportDate:$(\"#reportDate\").val()}, function (result) {\r\n");
      out.write("            TB_removeMaskLayer();\r\n");
      out.write("            TB_showMaskLayer(\"更新完成\", 1000);\r\n");
      out.write("        });\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    function reloadOverview() {\r\n");
      out.write("\r\n");
      out.write("        var conditionType = $(\"#conditionType\").val();\r\n");
      out.write("        if (!isEmpty(conditionType) && conditionType == 1 && (isEmpty($(\"#startDate\").val()) || isEmpty($(\"#endDate\").val()))) {\r\n");
      out.write("            alert(\"请选择搜索时间段\");\r\n");
      out.write("            return;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-manage!toProjectOverview.action?bisProjectId=\" + $(\"#bisProjectId\").val()\r\n");
      out.write("                + \"&conditionType=\" + $(\"#conditionType\").val()\r\n");
      out.write("                + \"&reportDate=\" + $(\"#reportDate\").val()\r\n");
      out.write("                + \"&startDate=\" + $(\"#startDate\").val() + \"&endDate=\" + $(\"#endDate\").val();\r\n");
      out.write("        window.location = url;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    function changeCondition(conditionType) {\r\n");
      out.write("        $(\"#conditionType\").val(conditionType);\r\n");
      out.write("        if (conditionType == 0) {\r\n");
      out.write("            $(\"#byMonth\").show();\r\n");
      out.write("            $(\"#byTimePeriod\").hide();\r\n");
      out.write("        } else {\r\n");
      out.write("            $(\"#byMonth\").hide();\r\n");
      out.write("            $(\"#byTimePeriod\").show();\r\n");
      out.write("        }\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    $(function () {\r\n");
      out.write("        if (");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isProjectLevel}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write(") {\r\n");
      out.write("            $('#btn_changeProject').onSelect({\r\n");
      out.write("                muti:false,\r\n");
      out.write("                callback:function (project) {\r\n");
      out.write("                    if (project.projectName == '宝龙商业') {\r\n");
      out.write("                        alert(\"请选择具体商业公司\");\r\n");
      out.write("                        return;\r\n");
      out.write("                    }\r\n");
      out.write("                    $(\"#bisProjectId\").val(project.bisProjectId);\r\n");
      out.write("                    reloadOverview();\r\n");
      out.write("                }\r\n");
      out.write("            });\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        window.openProjects = function () {\r\n");
      out.write("            var tab = parent.TabUtils;\r\n");
      out.write("            var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-manage.action\";\r\n");
      out.write("            if (tab == null) {\r\n");
      out.write("                window.open(url);\r\n");
      out.write("            } else {\r\n");
      out.write("                /*if ($(\"#195_tab\", parent.document).size() != 0) {\r\n");
      out.write("                 tab.closeTab({data:{tabId:\"195\"}});\r\n");
      out.write("                 }\r\n");
      out.write("                 tab.newTab(\"195\", \"项目列表\", url, true);*/\r\n");
      out.write("                parent.TabUtils.newTab(\"19870422\", \"项目列表\", url, true);\r\n");
      out.write("            }\r\n");
      out.write("        }\r\n");
      out.write("        autoHeight();\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("    function changeLayout(module) {\r\n");
      out.write("        var bisProjectId = $(\"#bisProjectId\").val();\r\n");
      out.write("        var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-manage!layout.action?module=\" + module + \"&bisProjectId=\" + $(\"#bisProjectId\").val();\r\n");
      out.write("        var tabname = \"bis\";\r\n");
      out.write("        var title = \"商业管理系统\";\r\n");
      out.write("\r\n");
      out.write("        if (module == 2) {\r\n");
      out.write("            var tabname = \"bisProjectOverview\";\r\n");
      out.write("            var title = \"项目报表\";\r\n");
      out.write("        } else if (module == 3) {\r\n");
      out.write("            var tabname = \"bisFee\";\r\n");
      out.write("            var title = \"收费明细\";\r\n");
      out.write("        } else if (module == 4) {\r\n");
      out.write("            tabname = 'bisCont';\r\n");
      out.write("            title = '商业合同管理';\r\n");
      out.write("        } else if (module == 6) {\r\n");
      out.write("            tabname = 'bisShop';\r\n");
      out.write("            title = '商家库';\r\n");
      out.write("        } else if (module == 7) {\r\n");
      out.write("            tabname = 'bisProjectMain';\r\n");
      out.write("            title = '基础数据维护';\r\n");
      out.write("        } else if (module == 9) {\r\n");
      out.write("            tabname = 'bisManageDay';\r\n");
      out.write("            title = '日经营数据管理';\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        if (parent.TabUtils == null)\r\n");
      out.write("            window.open(url);\r\n");
      out.write("        else\r\n");
      out.write("            parent.TabUtils.newTab(tabname, title, url, true);\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    function go2Pay() {\r\n");
      out.write("        var bisProjectId = $(\"#bisProjectId\").val();\r\n");
      out.write("        var url = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-pay.action?bisProjectId=' + bisProjectId;\r\n");
      out.write("        parent.TabUtils.newTab('payOut', '支出明细', url, true);\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    function goIncomeOther() {\r\n");
      out.write("        var bisProjectId = $(\"#bisProjectId\").val();\r\n");
      out.write("        var url = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-income-other.action?bisProjectId=' + bisProjectId;\r\n");
      out.write("        parent.TabUtils.newTab('bisIncomeOther', '能源费', url, true);\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      if (_jspx_meth_security_005fauthorize_005f13(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_security_005fauthorize_005f14(_jspx_page_context))
        return;
      out.write("\r\n");
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

  private boolean _jspx_meth_security_005fauthorize_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f0 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f0.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f0.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(44,16) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f0.setIfAnyGranted("A_SHOP_LOOK_HB,A_SHOP_UPDATE_SALE,A_SHOP_REJECT,A_SHOP_SECOND_VIEW,A_SHOP_ALL_VIEW,\r\n                        A_SHOP_FIRST_VIEW,A_SHOP_MERGE,A_SHOP_CONF_SORT,A_SHOP_RATE,A_SHOP_DEL,A_SHOP_AUDIT_ALL,A_SHOP_COMMIT,\r\n                        A_SHOP_NEW_PRO,A_SHOP_NEW_HQ,A_SHOP_REJECTION,A_SHOP_AUDITION");
    int _jspx_eval_security_005fauthorize_005f0 = _jspx_th_security_005fauthorize_005f0.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                    <button class=\"blue\" type=\"button\"  style=\"margin-left: 5px;\"\r\n");
        out.write("                            onclick=\"changeLayout(6);\">商家库\r\n");
        out.write("                    </button>\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f0);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f1 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f1.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f1.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(53,16) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f1.setIfAnyGranted("A_ENER_QUERY,A_ENER_IMPORT");
    int _jspx_eval_security_005fauthorize_005f1 = _jspx_th_security_005fauthorize_005f1.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                    <button class=\"blue\" type=\"button\"   style=\"margin-left: 5px;display: none;\"\r\n");
        out.write("                            onclick=\"goIncomeOther();\">能源费\r\n");
        out.write("                    </button>\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f1);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f2 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f2.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f2.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(58,16) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f2.setIfAnyGranted("A_PROJ_PROP_QUERY,A_PROJ_PROP_MODI,A_PROJ_DATA_MODI");
    int _jspx_eval_security_005fauthorize_005f2 = _jspx_th_security_005fauthorize_005f2.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                    <button class=\"blue\" type=\"button\"   style=\"margin-left: 5px;\"\r\n");
        out.write("                            onclick=\"changeLayout(7);\">基础资料\r\n");
        out.write("                    </button>\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f2);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f3 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f3.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f3.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(63,16) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f3.setIfAnyGranted("A_CONT_QUERY_PERS,A_CONT_QUERY_ALL");
    int _jspx_eval_security_005fauthorize_005f3 = _jspx_th_security_005fauthorize_005f3.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                    <button class=\"blue\" type=\"button\" style=\"margin-left: 5px;\"\r\n");
        out.write("                            onclick=\"changeLayout(4);\">合同台账\r\n");
        out.write("                    </button>\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f3);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f4 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f4.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f4.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(68,16) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f4.setIfAnyGranted("A_EXPE_QUERY");
    int _jspx_eval_security_005fauthorize_005f4 = _jspx_th_security_005fauthorize_005f4.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                    <button class=\"blue\" type=\"button\" style=\"margin-left: 5px;\"\r\n");
        out.write("                            onclick=\"go2Pay();\">支出明细\r\n");
        out.write("                    </button>\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f4);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f5 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f5.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f5.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(73,16) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f5.setIfAnyGranted("A_FACT_QUERY");
    int _jspx_eval_security_005fauthorize_005f5 = _jspx_th_security_005fauthorize_005f5.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                    <button class=\"blue\" type=\"button\" style=\"margin-left: 5px;\"\r\n");
        out.write("                            onclick=\"changeLayout(3);\">收费明细\r\n");
        out.write("                    </button>\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f5);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f6(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f6 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f6.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f6.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(78,16) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f6.setIfAnyGranted("A_BIS_DAY_WRITE,A_BIS_DAY_SELECT");
    int _jspx_eval_security_005fauthorize_005f6 = _jspx_th_security_005fauthorize_005f6.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                    <button class=\"blue\" type=\"button\" style=\"margin-left: 5px;\"\r\n");
        out.write("                            onclick=\"changeLayout(9);\">经营数据\r\n");
        out.write("                    </button>\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f6);
    return false;
  }

  private boolean _jspx_meth_s_005fset_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:set
    org.apache.struts2.views.jsp.SetTag _jspx_th_s_005fset_005f1 = (org.apache.struts2.views.jsp.SetTag) _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.get(org.apache.struts2.views.jsp.SetTag.class);
    _jspx_th_s_005fset_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fset_005f1.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(140,12) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fset_005f1.setVar("area");
    int _jspx_eval_s_005fset_005f1 = _jspx_th_s_005fset_005f1.doStartTag();
    if (_jspx_eval_s_005fset_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fset_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fset_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fset_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                ");
        if (_jspx_meth_s_005fif_005f0(_jspx_th_s_005fset_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                ");
        if (_jspx_meth_s_005felseif_005f0(_jspx_th_s_005fset_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                ");
        if (_jspx_meth_s_005felseif_005f1(_jspx_th_s_005fset_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("            ");
        int evalDoAfterBody = _jspx_th_s_005fset_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fset_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fset_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.reuse(_jspx_th_s_005fset_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.reuse(_jspx_th_s_005fset_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fset_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f0 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fset_005f1);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(141,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f0.setTest("bisProjectAreaCd=='SOUTH'");
    int _jspx_eval_s_005fif_005f0 = _jspx_th_s_005fif_005f0.doStartTag();
    if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f0.doInitBody();
      }
      do {
        out.write('南');
        out.write('区');
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

  private boolean _jspx_meth_s_005felseif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fset_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f0 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fset_005f1);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(142,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f0.setTest("bisProjectAreaCd=='NORTH'");
    int _jspx_eval_s_005felseif_005f0 = _jspx_th_s_005felseif_005f0.doStartTag();
    if (_jspx_eval_s_005felseif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f0.doInitBody();
      }
      do {
        out.write('北');
        out.write('区');
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

  private boolean _jspx_meth_s_005felseif_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fset_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f1 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fset_005f1);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(143,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f1.setTest("bisProjectAreaCd=='SH'");
    int _jspx_eval_s_005felseif_005f1 = _jspx_th_s_005felseif_005f1.doStartTag();
    if (_jspx_eval_s_005felseif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f1.doInitBody();
      }
      do {
        out.write('上');
        out.write('海');
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

  private boolean _jspx_meth_c_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(147,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isProjectLevel}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("class=\"link_bis\"");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f1 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f1.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(152,22) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f1.setTest("conditionType==1");
    int _jspx_eval_s_005fif_005f1 = _jspx_th_s_005fif_005f1.doStartTag();
    if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f1.doInitBody();
      }
      do {
        out.write("style=\"display: none;\"");
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

  private boolean _jspx_meth_s_005fif_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f2 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f2.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(160,22) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f2.setTest("conditionType!=1");
    int _jspx_eval_s_005fif_005f2 = _jspx_th_s_005fif_005f2.doStartTag();
    if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f2.doInitBody();
      }
      do {
        out.write("style=\"display: none;\"");
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

  private boolean _jspx_meth_s_005fif_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f3 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f3.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(169,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f3.setTest("#curUserCd=='baolm' || #curUserCd=='lujy' || #curUserCd=='shenjian' || #curUserCd=='jiaoxf' || #curUserCd=='dengyh'");
    int _jspx_eval_s_005fif_005f3 = _jspx_th_s_005fif_005f3.doStartTag();
    if (_jspx_eval_s_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f3.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    &nbsp;&nbsp;<input type=\"button\" class=\"btn_blue\" value=\"搜索\" style=\"width:75px;margin-left: -8px;\" onclick=\"reloadOverview();\"/>\r\n");
        out.write("                    &nbsp;&nbsp;<input type=\"button\" class=\"btn_blue\" style=\"width:75px;margin-left: -8px;\" value=\"更新数据\" onclick=\"refreshData();\"/>\r\n");
        out.write("                ");
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

  private boolean _jspx_meth_s_005fif_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f4 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f4.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(178,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f4.setTest("totalInfo.square != null");
    int _jspx_eval_s_005fif_005f4 = _jspx_th_s_005fif_005f4.doStartTag();
    if (_jspx_eval_s_005fif_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f4.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${totalInfo.square}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("㎡\r\n");
        out.write("                ");
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

  private boolean _jspx_meth_s_005fif_005f5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f5 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f5.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f5.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(184,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f5.setTest("totalInfo.rentRate != null");
    int _jspx_eval_s_005fif_005f5 = _jspx_th_s_005fif_005f5.doStartTag();
    if (_jspx_eval_s_005fif_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f5.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${totalInfo.rentRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f5);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f6(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f6 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f6.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f6.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(190,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f6.setTest("totalInfo.rentUnitPrice != null");
    int _jspx_eval_s_005fif_005f6 = _jspx_th_s_005fif_005f6.doStartTag();
    if (_jspx_eval_s_005fif_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f6.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${totalInfo.rentUnitPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("元/平方.月\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f6);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f7(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f7 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f7.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f7.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(198,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f7.setTest("totalInfo.propLeave != null");
    int _jspx_eval_s_005fif_005f7 = _jspx_th_s_005fif_005f7.doStartTag();
    if (_jspx_eval_s_005fif_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f7.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${totalInfo.propLeave}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("㎡\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f7);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f8(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f8 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f8.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f8.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(204,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f8.setTest("totalInfo.propSell != null");
    int _jspx_eval_s_005fif_005f8 = _jspx_th_s_005fif_005f8.doStartTag();
    if (_jspx_eval_s_005fif_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f8.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f8.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${totalInfo.propSell}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("㎡\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f8.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f8);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f9(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f9 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f9.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f9.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(210,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f9.setTest("totalInfo.propUnitPrice != null");
    int _jspx_eval_s_005fif_005f9 = _jspx_th_s_005fif_005f9.doStartTag();
    if (_jspx_eval_s_005fif_005f9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f9.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f9.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${totalInfo.propUnitPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("元/平方.月\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f9.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f9);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f10(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f10 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f10.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f10.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(218,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f10.setTest("totalInfo.backRate != null");
    int _jspx_eval_s_005fif_005f10 = _jspx_th_s_005fif_005f10.doStartTag();
    if (_jspx_eval_s_005fif_005f10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f10.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f10.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${totalInfo.backRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f10.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f10);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f11(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f11 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f11.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f11.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(224,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f11.setTest("totalInfo.backUnitPrice != null");
    int _jspx_eval_s_005fif_005f11 = _jspx_th_s_005fif_005f11.doStartTag();
    if (_jspx_eval_s_005fif_005f11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f11 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f11.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f11.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${totalInfo.backUnitPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("元/平方.月\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f11.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f11 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f11);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f12(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f12 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f12.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f12.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(230,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f12.setTest("totalInfo.backPayBalance != null");
    int _jspx_eval_s_005fif_005f12 = _jspx_th_s_005fif_005f12.doStartTag();
    if (_jspx_eval_s_005fif_005f12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f12 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f12.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f12.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${totalInfo.backPayBalance}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f12.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f12 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f12);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f13(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f13 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f13.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f13.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(238,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f13.setTest("totalInfo.rentCollectRate != null");
    int _jspx_eval_s_005fif_005f13 = _jspx_th_s_005fif_005f13.doStartTag();
    if (_jspx_eval_s_005fif_005f13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f13 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f13.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f13.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${totalInfo.rentCollectRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f13.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f13 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f13);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f13);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f14(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f14 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f14.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f14.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(244,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f14.setTest("totalInfo.energyCollectRate != null");
    int _jspx_eval_s_005fif_005f14 = _jspx_th_s_005fif_005f14.doStartTag();
    if (_jspx_eval_s_005fif_005f14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f14 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f14.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f14.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${totalInfo.energyCollectRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f14.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f14 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f14);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f14);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f15(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f15 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f15.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f15.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(250,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f15.setTest("totalInfo.propCollectRate != null");
    int _jspx_eval_s_005fif_005f15 = _jspx_th_s_005fif_005f15.doStartTag();
    if (_jspx_eval_s_005fif_005f15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f15 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f15.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f15.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${totalInfo.propCollectRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f15.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f15 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f15);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f15);
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
    // /common/taglibs.jsp(8,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setVar("ctx");
    // /common/taglibs.jsp(8,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fset_005f1 = _jspx_th_c_005fset_005f1.doStartTag();
    if (_jspx_th_c_005fset_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005fiterator_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_005fiterator_005f0 = (org.apache.struts2.views.jsp.IteratorTag) _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_005fiterator_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fiterator_005f0.setParent(null);
    // /WEB-INF/content/bis/bis-manage-overviewMall.jsp(5,0) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f0.setValue("floorInfoList");
    // /WEB-INF/content/bis/bis-manage-overviewMall.jsp(5,0) name = status type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f0.setStatus("s");
    int _jspx_eval_s_005fiterator_005f0 = _jspx_th_s_005fiterator_005f0.doStartTag();
    if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fiterator_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fiterator_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("<div style=\"border:1px solid #aaabb0; height:130px;padding:10px 8px;margin-bottom:10px;\">\r\n");
        out.write("<table>\r\n");
        out.write("\t<col width=\"150\"/>\r\n");
        out.write("\t<col width=\"10\"/>\r\n");
        out.write("\t<col />\r\n");
        out.write("\t<tr>\r\n");
        out.write("\t\t<td>\r\n");
        out.write("\t\t\t<div id=\"floor1\" value=\"1\" style=\"float:left; width:150px; height:110px; cursor:pointer; background-color: #464646;background-image: url('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("/resources/images/bis/");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${floorUrl}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("');\" \r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_security_005fauthorize_005f7(_jspx_th_s_005fiterator_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_security_005fauthorize_005f8(_jspx_th_s_005fiterator_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t>\r\n");
        out.write("\t\t\t</div>\r\n");
        out.write("\t\t</td>\r\n");
        out.write("\t\t<td rowspan=\"2\"></td>\r\n");
        out.write("\t\t<td rowspan=\"2\">\r\n");
        out.write("\t\t<table class=\"tb_content\" style=\"color:#5a5a5a;\">\r\n");
        out.write("\t\t\t<col width=\"75\"/>\r\n");
        out.write("\t\t\t<col />\r\n");
        out.write("\t\t\t<tr>\r\n");
        out.write("\t\t\t\t<td class=\"td_title_border\">面积：</td>\r\n");
        out.write("\t\t\t\t<td >\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_s_005fif_005f16(_jspx_th_s_005fiterator_005f0, _jspx_page_context))
          return true;
        out.write("&nbsp;\r\n");
        out.write("\t\t\t\t</td>\r\n");
        out.write("\t\t\t</tr>\r\n");
        out.write("\t\t\t<tr>\r\n");
        out.write("\t\t\t\t<td class=\"td_title_border\">租约率：</td>\r\n");
        out.write("\t\t\t\t<td >\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_s_005fif_005f17(_jspx_th_s_005fiterator_005f0, _jspx_page_context))
          return true;
        out.write("&nbsp;\r\n");
        out.write("\t\t\t\t</td>\r\n");
        out.write("\t\t\t</tr>\r\n");
        out.write("\t\t\t<tr>\r\n");
        out.write("\t\t\t\t<td class=\"td_title_border\">返租率：</td>\r\n");
        out.write("\t\t\t\t<td >\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_s_005fif_005f18(_jspx_th_s_005fiterator_005f0, _jspx_page_context))
          return true;
        out.write("&nbsp;\r\n");
        out.write("\t\t\t\t</td>\r\n");
        out.write("\t\t\t</tr>\r\n");
        out.write("\t\t\t<tr>\r\n");
        out.write("\t\t\t\t<td class=\"td_title_border\">租金单价：</td>\r\n");
        out.write("\t\t\t\t<td >\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_s_005fif_005f19(_jspx_th_s_005fiterator_005f0, _jspx_page_context))
          return true;
        out.write("&nbsp;\r\n");
        out.write("\t\t\t\t</td>\r\n");
        out.write("\t\t\t</tr>\r\n");
        out.write("\t\t</table>\r\n");
        out.write("\t\t</td>\r\n");
        out.write("\t</tr>\r\n");
        out.write("\t<tr>\r\n");
        out.write("\t\t<td style=\"text-align:center;font-size:14px;font-weight:bolder;color:#0167A2;\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${buildingNum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(' ');
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${floorNum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("楼</td>\r\n");
        out.write("\t</tr>\r\n");
        out.write("</table>\r\n");
        out.write("</div>\r\n");
        int evalDoAfterBody = _jspx_th_s_005fiterator_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fiterator_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus.reuse(_jspx_th_s_005fiterator_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus.reuse(_jspx_th_s_005fiterator_005f0);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f7 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f7.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f0);
    // /WEB-INF/content/bis/bis-manage-overviewMall.jsp(14,4) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f7.setIfAnyGranted("A_PLAN_QUERY");
    int _jspx_eval_security_005fauthorize_005f7 = _jspx_th_security_005fauthorize_005f7.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("onclick=\"toBisTenant('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisFloorId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("');\"");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f7);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f8 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifNotGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f8.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f0);
    // /WEB-INF/content/bis/bis-manage-overviewMall.jsp(15,4) name = ifNotGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f8.setIfNotGranted("A_PLAN_QUERY");
    int _jspx_eval_security_005fauthorize_005f8 = _jspx_th_security_005fauthorize_005f8.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("title=\"未分配权限\"");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f8.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifNotGranted.reuse(_jspx_th_security_005fauthorize_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifNotGranted.reuse(_jspx_th_security_005fauthorize_005f8);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f16(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f16 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f16.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f0);
    // /WEB-INF/content/bis/bis-manage-overviewMall.jsp(27,4) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f16.setTest("square != null");
    int _jspx_eval_s_005fif_005f16 = _jspx_th_s_005fif_005f16.doStartTag();
    if (_jspx_eval_s_005fif_005f16 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f16 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f16.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f16.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${square}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("㎡\r\n");
        out.write("\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_005fif_005f16.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f16 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f16);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f16);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f17(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f17 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f17.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f0);
    // /WEB-INF/content/bis/bis-manage-overviewMall.jsp(35,4) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f17.setTest("rentRate != null");
    int _jspx_eval_s_005fif_005f17 = _jspx_th_s_005fif_005f17.doStartTag();
    if (_jspx_eval_s_005fif_005f17 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f17 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f17.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f17.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${rentRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_005fif_005f17.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f17 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f17);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f17);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f18(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f18 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f18.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f0);
    // /WEB-INF/content/bis/bis-manage-overviewMall.jsp(43,4) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f18.setTest("backRate != null");
    int _jspx_eval_s_005fif_005f18 = _jspx_th_s_005fif_005f18.doStartTag();
    if (_jspx_eval_s_005fif_005f18 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f18 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f18.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f18.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${backRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_005fif_005f18.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f18 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f18);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f18);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f19(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f19 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f19.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f0);
    // /WEB-INF/content/bis/bis-manage-overviewMall.jsp(51,4) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f19.setTest("rentUnitPrice != null");
    int _jspx_eval_s_005fif_005f19 = _jspx_th_s_005fif_005f19.doStartTag();
    if (_jspx_eval_s_005fif_005f19 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f19 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f19.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f19.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${rentUnitPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("元/平方.月\r\n");
        out.write("\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_005fif_005f19.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f19 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f19);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f19);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f9(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f9 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f9.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f9.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(276,16) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f9.setIfAnyGranted("A_REPO_DETA_QUERY");
    int _jspx_eval_security_005fauthorize_005f9 = _jspx_th_security_005fauthorize_005f9.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("onclick=\"toReport('1');\"\r\n");
        out.write("                title=\"查看主力店报表\"");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f9.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f9);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f20(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f20 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f20.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f20.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(286,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f20.setTest("mainStoreInfo.rentRate != null");
    int _jspx_eval_s_005fif_005f20 = _jspx_th_s_005fif_005f20.doStartTag();
    if (_jspx_eval_s_005fif_005f20 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f20 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f20.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f20.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mainStoreInfo.rentRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f20.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f20 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f20);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f20);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f21(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f21 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f21.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f21.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(292,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f21.setTest("mainStoreInfo.rentCollectRate != null");
    int _jspx_eval_s_005fif_005f21 = _jspx_th_s_005fif_005f21.doStartTag();
    if (_jspx_eval_s_005fif_005f21 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f21 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f21.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f21.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mainStoreInfo.rentCollectRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f21.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f21 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f21);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f21);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f22(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f22 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f22.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f22.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(301,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f22.setTest("mainStoreInfo.backRate != null");
    int _jspx_eval_s_005fif_005f22 = _jspx_th_s_005fif_005f22.doStartTag();
    if (_jspx_eval_s_005fif_005f22 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f22 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f22.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f22.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mainStoreInfo.backRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f22.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f22 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f22);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f22);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f23(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f23 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f23.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f23.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(307,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f23.setTest("mainStoreInfo.energyCollectRate != null");
    int _jspx_eval_s_005fif_005f23 = _jspx_th_s_005fif_005f23.doStartTag();
    if (_jspx_eval_s_005fif_005f23 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f23 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f23.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f23.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mainStoreInfo.energyCollectRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f23.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f23 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f23);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f23);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f24(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f24 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f24.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f24.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(316,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f24.setTest("mainStoreInfo.rentUnitPrice != null");
    int _jspx_eval_s_005fif_005f24 = _jspx_th_s_005fif_005f24.doStartTag();
    if (_jspx_eval_s_005fif_005f24 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f24 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f24.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f24.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mainStoreInfo.rentUnitPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("元/平方.月\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f24.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f24 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f24);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f24);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f25(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f25 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f25.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f25.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(322,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f25.setTest("mainStoreInfo.propCollectRate != null");
    int _jspx_eval_s_005fif_005f25 = _jspx_th_s_005fif_005f25.doStartTag();
    if (_jspx_eval_s_005fif_005f25 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f25 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f25.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f25.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mainStoreInfo.propCollectRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f25.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f25 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f25);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f25);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f10(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f10 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f10.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f10.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(338,16) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f10.setIfAnyGranted("A_REPO_DETA_QUERY");
    int _jspx_eval_security_005fauthorize_005f10 = _jspx_th_security_005fauthorize_005f10.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("onclick=\"toReport('2');\"\r\n");
        out.write("                title=\"查看次主力店报表\"");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f10.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f10);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f26(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f26 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f26.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f26.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(348,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f26.setTest("minorStoreInfo.rentRate != null");
    int _jspx_eval_s_005fif_005f26 = _jspx_th_s_005fif_005f26.doStartTag();
    if (_jspx_eval_s_005fif_005f26 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f26 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f26.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f26.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${minorStoreInfo.rentRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f26.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f26 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f26);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f26);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f27(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f27 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f27.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f27.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(354,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f27.setTest("minorStoreInfo.rentCollectRate != null");
    int _jspx_eval_s_005fif_005f27 = _jspx_th_s_005fif_005f27.doStartTag();
    if (_jspx_eval_s_005fif_005f27 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f27 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f27.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f27.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${minorStoreInfo.rentCollectRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f27.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f27 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f27);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f27);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f28(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f28 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f28.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f28.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(363,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f28.setTest("minorStoreInfo.backRate != null");
    int _jspx_eval_s_005fif_005f28 = _jspx_th_s_005fif_005f28.doStartTag();
    if (_jspx_eval_s_005fif_005f28 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f28 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f28.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f28.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${minorStoreInfo.backRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f28.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f28 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f28);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f28);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f29(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f29 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f29.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f29.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(369,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f29.setTest("minorStoreInfo.energyCollectRate != null");
    int _jspx_eval_s_005fif_005f29 = _jspx_th_s_005fif_005f29.doStartTag();
    if (_jspx_eval_s_005fif_005f29 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f29 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f29.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f29.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${minorStoreInfo.energyCollectRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f29.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f29 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f29);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f29);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f30(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f30 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f30.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f30.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(378,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f30.setTest("minorStoreInfo.rentUnitPrice != null");
    int _jspx_eval_s_005fif_005f30 = _jspx_th_s_005fif_005f30.doStartTag();
    if (_jspx_eval_s_005fif_005f30 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f30 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f30.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f30.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${minorStoreInfo.rentUnitPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("元/平方.月\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f30.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f30 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f30);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f30);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f31(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f31 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f31.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f31.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(384,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f31.setTest("minorStoreInfo.propCollectRate != null");
    int _jspx_eval_s_005fif_005f31 = _jspx_th_s_005fif_005f31.doStartTag();
    if (_jspx_eval_s_005fif_005f31 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f31 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f31.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f31.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${minorStoreInfo.propCollectRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f31.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f31 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f31);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f31);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f11(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f11 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f11.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f11.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(400,16) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f11.setIfAnyGranted("A_REPO_DETA_QUERY");
    int _jspx_eval_security_005fauthorize_005f11 = _jspx_th_security_005fauthorize_005f11.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("onclick=\"toReport('3');\"\r\n");
        out.write("                title=\"查看小商铺报表\"");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f11.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f11);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f32(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f32 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f32.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f32.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(410,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f32.setTest("smallStoreInfo.rentRate != null");
    int _jspx_eval_s_005fif_005f32 = _jspx_th_s_005fif_005f32.doStartTag();
    if (_jspx_eval_s_005fif_005f32 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f32 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f32.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f32.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${smallStoreInfo.rentRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f32.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f32 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f32.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f32);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f32);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f33(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f33 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f33.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f33.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(416,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f33.setTest("smallStoreInfo.rentCollectRate != null");
    int _jspx_eval_s_005fif_005f33 = _jspx_th_s_005fif_005f33.doStartTag();
    if (_jspx_eval_s_005fif_005f33 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f33 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f33.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f33.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${smallStoreInfo.rentCollectRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f33.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f33 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f33.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f33);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f33);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f34(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f34 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f34.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f34.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(425,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f34.setTest("smallStoreInfo.backRate != null");
    int _jspx_eval_s_005fif_005f34 = _jspx_th_s_005fif_005f34.doStartTag();
    if (_jspx_eval_s_005fif_005f34 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f34 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f34.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f34.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${smallStoreInfo.backRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f34.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f34 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f34.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f34);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f34);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f35(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f35 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f35.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f35.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(431,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f35.setTest("smallStoreInfo.energyCollectRate != null");
    int _jspx_eval_s_005fif_005f35 = _jspx_th_s_005fif_005f35.doStartTag();
    if (_jspx_eval_s_005fif_005f35 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f35 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f35.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f35.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${smallStoreInfo.energyCollectRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f35.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f35 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f35.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f35);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f35);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f36(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f36 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f36.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f36.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(440,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f36.setTest("smallStoreInfo.rentUnitPrice != null");
    int _jspx_eval_s_005fif_005f36 = _jspx_th_s_005fif_005f36.doStartTag();
    if (_jspx_eval_s_005fif_005f36 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f36 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f36.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f36.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${smallStoreInfo.rentUnitPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("元/平方.月\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f36.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f36 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f36.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f36);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f36);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f37(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f37 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f37.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f37.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(446,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f37.setTest("smallStoreInfo.propCollectRate != null");
    int _jspx_eval_s_005fif_005f37 = _jspx_th_s_005fif_005f37.doStartTag();
    if (_jspx_eval_s_005fif_005f37 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f37 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f37.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f37.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${smallStoreInfo.propCollectRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("%\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fif_005f37.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fif_005f37 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fif_005f37.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f37);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f37);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f12(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f12 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f12.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f12.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(454,4) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f12.setIfAnyGranted("A_TOTAL_DATA_QUERY");
    int _jspx_eval_security_005fauthorize_005f12 = _jspx_th_security_005fauthorize_005f12.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("        <table class=\"tb_content\">\r\n");
        out.write("            <col width=\"10\"/>\r\n");
        out.write("            <col/>\r\n");
        out.write("            <tr>\r\n");
        out.write("                <td colspan=\"5\" class=\"td_header\"><font style=\"font-weight:bolder;\">资料下载</font></td>\r\n");
        out.write("            </tr>\r\n");
        out.write("            <tr>\r\n");
        out.write("                <td></td>\r\n");
        out.write("                <td class=\"td_title\" colspan=\"2\">业态图</td>\r\n");
        out.write("            </tr>\r\n");
        out.write("            <tr>\r\n");
        out.write("                <td></td>\r\n");
        out.write("                <td colspan=\"2\">\r\n");
        out.write("                    <div id=\"show_layoutId\"></div>\r\n");
        out.write("                </td>\r\n");
        out.write("            </tr>\r\n");
        out.write("            <tr>\r\n");
        out.write("                <td></td>\r\n");
        out.write("                <td class=\"td_title\" colspan=\"2\">品牌落位图</td>\r\n");
        out.write("            </tr>\r\n");
        out.write("            <tr>\r\n");
        out.write("                <td></td>\r\n");
        out.write("                <td colspan=\"2\">\r\n");
        out.write("                    <div id=\"show_brandLoadId\"></div>\r\n");
        out.write("                </td>\r\n");
        out.write("            </tr>\r\n");
        out.write("            <!--<tr>\r\n");
        out.write("               <td></td>\r\n");
        out.write("               <td class=\"td_title\" colspan=\"2\">价格审批表</td>\r\n");
        out.write("           </tr>\r\n");
        out.write("           <tr>\r\n");
        out.write("               <td></td>\r\n");
        out.write("               <td colspan=\"2\">\r\n");
        out.write("                   <div id=\"show_priceApproveId\"></div>\r\n");
        out.write("               </td>\r\n");
        out.write("           </tr>\r\n");
        out.write("           -->\r\n");
        out.write("        </table>\r\n");
        out.write("    ");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f12.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f12);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f13(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f13 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f13.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f13.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(723,0) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f13.setIfAnyGranted("A_REPO_DETA_QUERY");
    int _jspx_eval_security_005fauthorize_005f13 = _jspx_th_security_005fauthorize_005f13.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("    <script type=\"text/javascript\">\r\n");
        out.write("        $(\".link\").removeClass(\"link\").addClass(\"link_bis\");\r\n");
        out.write("    </script>\r\n");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f13.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f13);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f13);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f14(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f14 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f14.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f14.setParent(null);
    // /WEB-INF/content/bis/bis-manage-projectOverview.jsp(728,0) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f14.setIfAnyGranted("A_TOTAL_DATA_QUERY");
    int _jspx_eval_security_005fauthorize_005f14 = _jspx_th_security_005fauthorize_005f14.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("    <script type=\"text/javascript\">\r\n");
        out.write("        showLayoutFile('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${layoutId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("', 'layoutId');\r\n");
        out.write("        showLayoutFile('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${brandLoadId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("', 'brandLoadId');\r\n");
        out.write("        //showLayoutFile('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${priceApproveId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("','priceApproveId');\r\n");
        out.write("    </script>\r\n");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f14.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f14);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f14);
    return false;
  }
}
