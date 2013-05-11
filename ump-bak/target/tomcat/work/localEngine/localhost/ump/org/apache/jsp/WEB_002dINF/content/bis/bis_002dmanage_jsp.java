package org.apache.jsp.WEB_002dINF.content.bis;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class bis_002dmanage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(5);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/common/nocache.jsp");
    _jspx_dependants.add("/common/global.jsp");
    _jspx_dependants.add("/WEB-INF/content/bis/bis-manage-version.jsp");
    _jspx_dependants.add("/WEB-INF/PowerDesk-tags.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.release();
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted.release();
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
      out.write("/resources/css/bis/bis-manage.css\"/>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/bis/bis.project.select.js\"></script>\r\n");
      out.write("    <title>商业管理系统</title>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<div id=\"warp\">\r\n");
      out.write("    <div id=\"header\">\r\n");
      out.write("        <div class=\"title1 clearfix\">\r\n");
      out.write("\r\n");
      out.write("                <h2 style=\"width: auto;margin-right: 10px; float: left;\">商业管理系统</h2>\r\n");
      out.write("\r\n");
      out.write("                <div style=\"float:left; padding-top: 13px;\">\r\n");
      out.write("                    ");
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
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("                <div class=\"btns\">\r\n");
      out.write("                    <button class=\"green\" type=\"button\" onclick=\"openReports()\">报表首页</button>\r\n");
      out.write("                    <!--button class=\"blue\" type=\"button\">支出明细</button>\r\n");
      out.write("                    <button class=\"blue\" type=\"button\">收入明细</button>\r\n");
      out.write("                    <button class=\"blue\" type=\"button\">合同台帐</button>\r\n");
      out.write("                    <button class=\"blue\" type=\"button\">商家库</button-->\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div id=\"body\" style=\"margin:10px;\">\r\n");
      out.write("        <div id=\"projectNorthDiv\" style=\"float: left; padding-right:10px; width: 30%;\">\r\n");
      out.write("            <ul id=\"projectNorth\" class=\"project-nav\">\r\n");
      out.write("                <li class=\"project-nav-title\">北方区域公司</li>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fiterator_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            </ul>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div id=\"projectSouthDiv\" style=\"float: left;  width: 30%;\">\r\n");
      out.write("            <ul id=\"projectSouth\" class=\"project-nav\">\r\n");
      out.write("                <li class=\"project-nav-title\">南方区域公司</li>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fiterator_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            </ul>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\t");
      if (_jspx_meth_security_005fauthorize_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("    function viewProject(id) {\r\n");
      out.write("        var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-manage!toProjectOverview.action?bisProjectId=\" + id;\r\n");
      out.write("        if (parent.TabUtils == null)\r\n");
      out.write("            window.open(url);\r\n");
      out.write("        else\r\n");
      out.write("            parent.TabUtils.newTab('projectOverview', '项目总况', url, true);\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    function viewReport(module) {\r\n");
      out.write("\r\n");
      out.write("        var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-manage!layout.action?module=\" + module;\r\n");
      out.write("        var tabname;\r\n");
      out.write("        var title;\r\n");
      out.write("        if (module == 1) {\r\n");
      out.write("            tabname = \"bisGroupOperate\";\r\n");
      out.write("            title = \"集团报表\";\r\n");
      out.write("        } else if (module == 2) {\r\n");
      out.write("            tabname = \"bisGroupCash\";\r\n");
      out.write("            title = \"集团资金流量表\";\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        if (parent.TabUtils == null)\r\n");
      out.write("            window.open(url);\r\n");
      out.write("        else\r\n");
      out.write("            parent.TabUtils.newTab(tabname, title, url, true);\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    function toBisShop() {\r\n");
      out.write("        var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-shop!main.action\";\r\n");
      out.write("        if (parent.TabUtils == null)\r\n");
      out.write("            window.open(url);\r\n");
      out.write("        else\r\n");
      out.write("            parent.TabUtils.newTab(\"bisShop\", \"商家库\", url, true);\r\n");
      out.write("    }\r\n");
      out.write("    function toBisManageDay() {\r\n");
      out.write("        var tab = parent.TabUtils;\r\n");
      out.write("        var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-manage-day!report.action\";\r\n");
      out.write("        if (parent.TabUtils == null)\r\n");
      out.write("            window.open(url);\r\n");
      out.write("        else {\r\n");
      out.write("            tab.closeTab({data:{tabId:\"195\"}});\r\n");
      out.write("            tab.newTab(\"195\", \"经营日报表\", url, true);\r\n");
      out.write("        }\r\n");
      out.write("    }\r\n");
      out.write("    function toBisMainShopWeekly() {\r\n");
      out.write("        var tab = parent.TabUtils;\r\n");
      out.write("        var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-main-shop-report.action\";\r\n");
      out.write("        if (parent.TabUtils == null)\r\n");
      out.write("            window.open(url);\r\n");
      out.write("        else {\r\n");
      out.write("            tab.closeTab({data:{tabId:\"195\"}});\r\n");
      out.write("            tab.newTab(\"195\", \"主力店招商周报\", url, true);\r\n");
      out.write("        }\r\n");
      out.write("    }\r\n");
      out.write("    function toRentalCollectionRate(){\r\n");
      out.write("    \tvar tab = parent.TabUtils;\r\n");
      out.write("    \tvar url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-rental-collection-rate.action\";\r\n");
      out.write("    \tif(parent.TabUtils==null)\r\n");
      out.write("    \t\twindow.open(url);\r\n");
      out.write("    \telse{\r\n");
      out.write("            tab.closeTab({data:{tabId:\"195\"}});\r\n");
      out.write("            tab.newTab(\"195\",\"租费收缴率\",url,true);\r\n");
      out.write("        }\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    /**\r\n");
      out.write("     * 选择项目业态图，跳转mall楼层展示页面\r\n");
      out.write("     */\r\n");
      out.write("    function goFloor(bisProjectId) {\r\n");
      out.write("        var url = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-project!projectOperate.action?bisProjectId=' + bisProjectId;\r\n");
      out.write("        $('#layoutPanel').hide();\r\n");
      out.write("        if (parent.TabUtils == null)\r\n");
      out.write("            window.open(url);\r\n");
      out.write("        else\r\n");
      out.write("            parent.TabUtils.newTab(\"bisProjectMenu\", \"项目业态图\", url, true);\r\n");
      out.write("    }\r\n");
      out.write("     function openReports(){\r\n");
      out.write("         var tab = parent.TabUtils;\r\n");
      out.write("         var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-manage!reports.action\";\r\n");
      out.write("         if (parent.TabUtils == null)\r\n");
      out.write("             window.open(url);\r\n");
      out.write("         else {\r\n");
      out.write("             //parent.TabUtils.newTab(\"19870422\", \"报表首页\", url, true);\r\n");
      out.write("             //tab.closeTab({data:{tabId:\"195\"}});\r\n");
      out.write("             tab.newTab(\"195\", \"报表首页\", url, true);\r\n");
      out.write("         }\r\n");
      out.write("     }\r\n");
      out.write("    $(function () {\r\n");
      out.write("        $('#projectDiv').height($(window).height() - 103 + \"px\");\r\n");
      out.write("        $('#btnLayOut').onSelect({\r\n");
      out.write("            muti:false,\r\n");
      out.write("            callback:function (project) {\r\n");
      out.write("                goFloor(project.bisProjectId);\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
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

  private boolean _jspx_meth_s_005fiterator_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_005fiterator_005f0 = (org.apache.struts2.views.jsp.IteratorTag) _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_005fiterator_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fiterator_005f0.setParent(null);
    // /WEB-INF/content/bis/bis-manage.jsp(42,16) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f0.setValue("projectNorth");
    int _jspx_eval_s_005fiterator_005f0 = _jspx_th_s_005fiterator_005f0.doStartTag();
    if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fiterator_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fiterator_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    <li id=\"btli_");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" class=\"project-nav-li\"\r\n");
        out.write("                        onclick=\"viewProject('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("');\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${projectName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</li>\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fiterator_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fiterator_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.reuse(_jspx_th_s_005fiterator_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.reuse(_jspx_th_s_005fiterator_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fiterator_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_005fiterator_005f1 = (org.apache.struts2.views.jsp.IteratorTag) _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_005fiterator_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fiterator_005f1.setParent(null);
    // /WEB-INF/content/bis/bis-manage.jsp(51,16) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f1.setValue("projectSouth");
    int _jspx_eval_s_005fiterator_005f1 = _jspx_th_s_005fiterator_005f1.doStartTag();
    if (_jspx_eval_s_005fiterator_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fiterator_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fiterator_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fiterator_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                    <li id=\"btli_");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" class=\"project-nav-li\"\r\n");
        out.write("                        onclick=\"viewProject('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("');\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${projectName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</li>\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fiterator_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fiterator_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fiterator_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.reuse(_jspx_th_s_005fiterator_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.reuse(_jspx_th_s_005fiterator_005f1);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f0 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f0.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f0.setParent(null);
    // /WEB-INF/content/bis/bis-manage.jsp(58,1) name = ifAllGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f0.setIfAllGranted("A_RENTAL_C_RATE_VIEW");
    int _jspx_eval_security_005fauthorize_005f0 = _jspx_th_security_005fauthorize_005f0.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t<div style=\"float:left; padding-left:10px;display:none;\">\r\n");
        out.write("\t\t\t<input type=\"button\" class=\"btn_blue\" style=\"height:26px;width:100px;\" onclick=\"toRentalCollectionRate()\" value=\"租费收缴率\"/>\r\n");
        out.write("\t\t</div>\r\n");
        out.write("\t");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted.reuse(_jspx_th_security_005fauthorize_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted.reuse(_jspx_th_security_005fauthorize_005f0);
    return false;
  }
}
