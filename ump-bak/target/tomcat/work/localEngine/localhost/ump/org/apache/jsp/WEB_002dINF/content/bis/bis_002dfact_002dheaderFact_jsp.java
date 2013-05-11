package org.apache.jsp.WEB_002dINF.content.bis;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.hhz.ump.util.DictContants;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

public final class bis_002dfact_002dheaderFact_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(4);
    _jspx_dependants.add("/common/global.jsp");
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/common/nocache.jsp");
    _jspx_dependants.add("/WEB-INF/PowerDesk-tags.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005frequired_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005felse;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fif_0026_005ftest_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005frequired_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005felse = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005frequired_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle.release();
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fs_005felse.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest_005fnobody.release();
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.release();
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
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
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
      out.write("    \r\n");
      out.write("    <title>实收明细</title>\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("        var _ctx = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("        var isProjectBusinessCompany = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isProjectBusinessCompany}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("    </script>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/bis/bis-project.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/cont/cont.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/customInput/customInput.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/bis/ymPrompt.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/bis/bis.fact.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/loadMask/jquery.loadmask.css\"/>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/common.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/prompt/ymPrompt.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/ConvertUtil.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jqueryplugin/chilltip.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/datePicker/WdatePicker.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.form.pack.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/formValidator/formValidator.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.select.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/validate/formatUtil.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.quickSearch.js\"></script>\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/mes/jquery-powerdesk.js\" type=\"text/javascript\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" charset=\"UTF-8\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/loadMask/jquery.loadmask.min.js\"></script>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/bis/bis-manage.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/base.css\"/>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/bis/bis.project.select.js\"></script>\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/MaskLayer.js\" type=\"text/javascript\"></script>\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/mes/thickbox.css\" media=\"screen\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("    <style type=\"text/css\">\r\n");
      out.write("    \t#finance_report{\r\n");
      out.write("\t\tborder-width:0px;\r\n");
      out.write("\t\tcolor:#000;\r\n");
      out.write("\t\tdisplay:block;\r\n");
      out.write("\t\tfont-size:12px;\r\n");
      out.write("\t\tmargin:auto;\r\n");
      out.write("\t\tmargin-bottom:5px;\r\n");
      out.write("\t\tpadding:0px;\r\n");
      out.write("\t\tpadding-left:10px;\r\n");
      out.write("\t\tline-height:25px;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t.linkmenu_A{\r\n");
      out.write("\t\tbackground-attachment:scroll;\r\n");
      out.write("\t\tbackground-colr:rgb(255,255,255);\r\n");
      out.write("\t\tbackground-image:none;\r\n");
      out.write("\t\tborder-color:#026eb6;\r\n");
      out.write("\t\tcursor:pointer;\r\n");
      out.write("\t\tfloat:left;\r\n");
      out.write("\t\tfont-size:12px;\r\n");
      out.write("\t\tmargin:-5px 10px 5px 10px;\r\n");
      out.write("\t\ttext-decoration: none;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t.linkmenu_S{\r\n");
      out.write("\t\tborder-style:none;\r\n");
      out.write("\t\tborder-width:0px;\r\n");
      out.write("\t\tcolor:#cecece;\r\n");
      out.write("\t\tfloat:left;\r\n");
      out.write("\t\tfont-size:12px;\r\n");
      out.write("\t\tmargin:0px;\r\n");
      out.write("\t\tpadding:0px;\r\n");
      out.write("\t\t}\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-fact!list.action\" id=\"searchForm\" method=\"post\">\r\n");
      out.write("\t<input type=\"hidden\" id=\"mustclickId\" name=\"mustclick\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mustclick }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("    <div id=\"header\">\r\n");
      out.write("        <div class=\"title_bar\">\r\n");
      out.write("            <h2>商业历史明细</h2>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"left\">\r\n");
      out.write("                <input type=\"button\" class=\"btn_gray\" style=\"width: 80px;\" id=\"btnSeniorSearch\" value=\"高级搜索\"\r\n");
      out.write("                       onclick=\"showSeniorSearch();\"/>\r\n");
      out.write("                &nbsp;&nbsp;&nbsp;&nbsp;项目：\r\n");
      out.write("                <input type=\"text\" id=\"bisProjectName\"  name=\"bisProjectName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" readonly=\"true\"\r\n");
      out.write("                       style=\"cursor: pointer; color: #ff0000;border:1px solid #333;height:16px;\"/>\r\n");
      out.write("                <input class=\"search\" type=\"hidden\" id=\"bisProjectIdFact\" name=\"bisProjectId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("                &nbsp;&nbsp;&nbsp;&nbsp;业态：\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fselect_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("            <div style=\"float:right; height:26px; margin-right:5px; margin-top:6px;\">\r\n");
      out.write("                <div class=\"btn_green\" onclick=\"clkFullScreen('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("');\">全屏</div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"bis_search\" id=\"main_search_div\">\r\n");
      out.write("            <!-- 3实收-->\r\n");
      out.write("            <table class=\"tb_search\" style=\"margin-left: 5px;\">\r\n");
      out.write("                <col/>\r\n");
      out.write("                <tr class=\"no_advances_dime\">\r\n");
      out.write("                    <td align=\"right\" class=\"flat_layout\" style=\"display:none;\" layout=\"flat\" width=\"40\">楼号：</td>\r\n");
      out.write("                    <td layout=\"flat\" class=\"flat_layout\" style=\"display:none;\" width=\"70\">\r\n");
      out.write("                        <input style=\"width:100px;height:16px;\" id=\"flatBuding\"/>\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td align=\"right\" id=\"detailLabel\" width=\"70\">租户/商铺：</td>\r\n");
      out.write("                    <td width=\"100\">\r\n");
      out.write("                        <input type=\"text\"\r\n");
      out.write("                               style=\"width:100px;height:16px;");
      if (_jspx_meth_s_005fif_005f0(_jspx_page_context))
        return;
      if (_jspx_meth_s_005felse_005f0(_jspx_page_context))
        return;
      out.write("\"\r\n");
      out.write("                               title=\"\" name=\"shopStoreName\" id=\"layOutCdList_v0\" required=\"true\"\r\n");
      out.write("                               onfocus=\"clearInput(this);\"\r\n");
      out.write("                               value=\"");
      if (_jspx_meth_s_005fif_005f1(_jspx_page_context))
        return;
      if (_jspx_meth_s_005felse_005f1(_jspx_page_context))
        return;
      out.write("\"/>\r\n");
      out.write("                        <select style=\"display:none;\" title=\"\" id=\"layOutCdList_v1\" required=\"true\"\r\n");
      out.write("                                onClick=\"selectDetail1();\"></select>\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td align=\"right\" width=\"40\">类别：</td>\r\n");
      out.write("                    <td width=\"100\">\r\n");
      out.write("                        ");
      if (_jspx_meth_s_005fselect_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                    </td>\r\n");
      out.write("                   <td align=\"right\" width=\"40\">年月：</td>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"60\" >\r\n");
      out.write("                    \t\t<input style=\"width:60px;height:16px;\" type=\"text\" name=\"reportDateStart\"\r\n");
      out.write("                           id=\"reportDateStart\"  value=\"");
      if (_jspx_meth_s_005fif_005f2(_jspx_page_context))
        return;
      if (_jspx_meth_s_005felse_005f2(_jspx_page_context))
        return;
      out.write("\"\r\n");
      out.write("                            onfocus=\"WdatePicker({dateFmt:'yyyy-MM'})\"/>\r\n");
      out.write("               \t\t\t </td>\r\n");
      out.write("               \t     \t<td align=\"center\" width=\"40\">至</td>\r\n");
      out.write("                \t\t<td width=\"60\" >\r\n");
      out.write("                    \t\t<input validate=\"required\" style=\"width:60px;height:16px;\" type=\"text\" name=\"reportDateEnd\"\r\n");
      out.write("                           id=\"reportDateEnd\" value=\"");
      if (_jspx_meth_s_005fif_005f3(_jspx_page_context))
        return;
      if (_jspx_meth_s_005felse_005f3(_jspx_page_context))
        return;
      out.write("\"\r\n");
      out.write("                            onfocus=\"WdatePicker({dateFmt:'yyyy-MM'})\"/>\r\n");
      out.write("                \t\t</td>\r\n");
      out.write("                   \r\n");
      out.write("                    <td style=\"padding-left: 5px;\">\r\n");
      out.write("                        <input id=\"btn_search\" type=\"button\" style=\"width:65px\" class=\"btn_blue\"\r\n");
      out.write("                               onclick=\"chargeDetailQuery();\" value=\"搜索\"/>\r\n");
      out.write("                        ");
      if (_jspx_meth_security_005fauthorize_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                        ");
      if (_jspx_meth_security_005fauthorize_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                    </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("            </table>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"bis_search\" id=\"main_search_div1\"\r\n");
      out.write("             style=\"height:30px;background:white;border:0px;    margin-bottom: 9px;\">\r\n");
      out.write("            <table style=\"width:100%\">\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td colspan=\"12\">\r\n");
      out.write("                        <ul id=\"bis_rpt\" style=\"margin-left: 4px;list-style-type:none;\">\r\n");
      out.write("                            <li value=\"2\" class=\"bis_fact_unclick\" id=\"must\">应收明细</li>\r\n");
      out.write("                            <li value=\"3\" class=\"bis_fact_unclick\" id=\"overdue\">欠费明细</li>\r\n");
      out.write("                            <li value=\"1\" class=\"bis_fact_unclick\" id=\"fact\">收费历史记录</li>\r\n");
      out.write("                            <li value=\"4\" class=\"bis_fact_unclick\" id=\"advances\">预收明细</li>\r\n");
      out.write("                            <!--<li   value=\"5\" id=\"payNoti\" >缴费通知</li>-->\r\n");
      out.write("                            <li value=\"6\" class=\"bis_fact_unclick\" id=\"payIncome\">营业外收入</li>\r\n");
      out.write("                            <li value=\"7\" class=\"bis_fact_unclick\" id=\"budget\">经营预算明细</li>\r\n");
      out.write("                            <li value=\"8\" class=\"bis_fact_unclick\" id=\"actInCome\" style=\"display: none;\">实收明细</li>\r\n");
      out.write("                            <li value=\"9\" class=\"bis_fact_unclick\" id=\"gysfRecord\">公寓收费记录</li>\r\n");
      out.write("                            <input type=\"hidden\" id=\"dimension\" name=\"dimension\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dimension }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("                        </ul>\r\n");
      out.write("                        <ul class=\"fact_dime\" style=\"list-style-type: none;\" id=\"search_fact\">\r\n");
      out.write("                            <li fact=\"true\" id=\"\" valu='1'>审核通过</li>\r\n");
      out.write("                            <li fact=\"true\" id=\"\" valu='0'>等待审核</li>\r\n");
      out.write("                            <li fact=\"true\" id=\"\" valu='2'>审核驳回</li>\r\n");
      out.write("                            <li fact=\"true\" id=\"factAll\" valu='' style=\"color:red;\">全部</li>\r\n");
      out.write("                            <li fact=\"true\" id=\"\" valu='n' style=\"color:#464646\">快速搜索:</li>\r\n");
      out.write("                        </ul>\r\n");
      out.write("                        <span id=\"pageHtml\" style=\"margin-left:10px;\"></span>\r\n");
      out.write("                        <span style=\"margin-top:8px;margin-left:20px;float:left ;color:red;display:none;line-height:20px;height:20px;\"\r\n");
      out.write("                              id=\"factoptip\"></span>\r\n");
      out.write("                    </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("            </table>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div id=\"finance_report\">\r\n");
      out.write("        \t<a class=\"linkmenu_A \" style=\"color:#ff6500;text-decoration: none;\" onclick=\"actIncomeJumper(1);\">实收明细</a><span class=\"linkmenu_S \">|</span>\r\n");
      out.write("        \t<a class=\"linkmenu_A \" style=\"text-decoration: none;\" onclick=\"actIncomeJumper(2);\">实收汇总</a>\r\n");
      out.write("        </div>\r\n");
      out.write("        <!-- 页面高级检索隐藏域 -->\r\n");
      out.write("        <div id=\"seniorSearchDiv\"\r\n");
      out.write("             style=\"position: absolute; width: 650px; height: 95px; top: 31px; left: 8px; background-color: rgb(255, 255, 255); border: 1px solid rgb(0, 0, 0); padding: 10px 20px 10px 0px; z-index: 8; display: block; display:none; \">\r\n");
      out.write("            <table cellspacing=\"0\" cellpadding=\"0\" class=\"tb_search\">\r\n");
      out.write("                <colgroup>\r\n");
      out.write("                    <col width=\"80\">\r\n");
      out.write("                    <col width=\"130\">\r\n");
      out.write("                    <col width=\"100\">\r\n");
      out.write("                    <col width=\"130\">\r\n");
      out.write("                    <col width=\"75\">\r\n");
      out.write("                    <col width=\"130\">\r\n");
      out.write("                </colgroup>\r\n");
      out.write("                <tbody>\r\n");
      out.write("                <tr id=\"must_fact_senior\" class=\"fact_dime\" style=\"display:none;\">\r\n");
      out.write("                    <td style=\"text-align: right;\">预收日期：</td>\r\n");
      out.write("                    <td>\r\n");
      out.write("                        <input class=input\" type=\"text\"\r\n");
      out.write("                        onfocus=\"WdatePicker()\"\r\n");
      out.write("                        name=\"factInBegin\" id=\"factInBegin\" style=\"cursor: pointer;\"/>\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td style=\"text-align: right;\">到：</td>\r\n");
      out.write("                    <td>\r\n");
      out.write("                        <input class=input\" type=\"text\"\r\n");
      out.write("                        onfocus=\"WdatePicker()\"\r\n");
      out.write("                        name=\"factInEnd\" id=\"factInEnd\" style=\"cursor: pointer;\"/>\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td style=\"text-align: right;\">状态：</td>\r\n");
      out.write("                    <td>\r\n");
      out.write("                        ");
      if (_jspx_meth_s_005fselect_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                    </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td style=\"text-align: right;\">创建人：</td>\r\n");
      out.write("                    <td>\r\n");
      out.write("                        <input name=\"creatorQ\" id=\"creatorQ\" type=\"text\"/>\r\n");
      out.write("                        <input name=\"creator\" id=\"creator\" type=\"hidden\"/>\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td style=\"text-align: right;\">实收日从：</td>\r\n");
      out.write("                    <td>\r\n");
      out.write("                        <input class=\"Wdate\" type=\"text\" onfocus=\"WdatePicker()\" name=\"minMonth\" id=\"minMonth\"/>\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td style=\"text-align: right;\">到：</td>\r\n");
      out.write("                    <td>\r\n");
      out.write("                        <input class=\"Wdate\" type=\"text\" onfocus=\"WdatePicker()\" name=\"maxMonth\" id=\"maxMonth\"/>\r\n");
      out.write("                    </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td style=\"text-align: right;\">审核人：</td>\r\n");
      out.write("                    <td>\r\n");
      out.write("                        <input name=\"checkUserCdQ\" id=\"checkUserCdQ\" type=\"text\"/>\r\n");
      out.write("                        <input name=\"checkUserCd\" id=\"checkUserCd\" type=\"hidden\"/>\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td style=\"text-align: right;\">实收金额：</td>\r\n");
      out.write("                    <td>\r\n");
      out.write("                        <input class=enterQuery\" type=\"text\" name=\"minMoney\" id=\"minMoney\" />\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td style=\"text-align: right;\">到：</td>\r\n");
      out.write("                    <td>\r\n");
      out.write("                        <input class=enterQuery\" type=\"text\" name=\"maxMoney\" id=\"maxMoney\" />\r\n");
      out.write("                    </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td colspan=\"6\" style=\"padding-top:10px; text-align:center;\">\r\n");
      out.write("                        <input type=\"button\" class=\"btn_blue\" onClick=\"chargeDetailQuery();closeSeniorSearch();\"\r\n");
      out.write("                               value=\"搜索\"/>\r\n");
      out.write("                        <input type=\"button\" class=\"btn_red\" onClick=\"clearSearch();\" value=\"清空\"/>\r\n");
      out.write("                        <input type=\"button\" class=\"btn_green\" onClick=\"closeSeniorSearch();\" value=\"关闭\"/>\r\n");
      out.write("                    </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                </tbody>\r\n");
      out.write("            </table>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("<div id=\"addContent\"\r\n");
      out.write("     style=\"display:none;clear:both;margin:0px;padding:5px;border-bottom:1px solid #dddbdc;background:#f7f7f7;\">\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id=\"welcom\" style=\"clear:both;height:30px;width:80%\">\r\n");
      out.write("    <div style=\"color:#6BAD42;font-size:16px;font-weight:bold;width:auto;margin-top:200px;text-align:center;\">\r\n");
      out.write("        请选择搜索条件\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"detailPanel\" style=\"clear:both;width:100%\">\r\n");
      out.write("    <div id=\"detailFor\" style=\"clear:both;\">\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/bis/bis.fact.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("    var uiid = '");
      out.print(SpringSecurityUtils.getCurrentUiid());
      out.write("';\r\n");
      out.write("    layOutCdStore = '");
      out.print(DictContants.BIS_CONT_TYPE_STORE);
      out.write("';\r\n");
      out.write("    layOutCdFlat = '");
      out.print(DictContants.BIS_CONT_TYPE_FLAT);
      out.write("';\r\n");
      out.write("    layOutCdMulti = '");
      out.print(DictContants.BIS_CONT_TYPE_MULTI);
      out.write("';\r\n");
      out.write("    chargeType02 = '14';\r\n");
      out.write("    chargeType03 = '12';\r\n");
      out.write("    chargeType38 = '13';\r\n");
      out.write("    var currProjectId = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("    var currProjectName = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("    var bisTenantId = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisTenantId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("    var bisTenantName = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currDetailName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("    var dimension = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dimension}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("    var year = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${factYear}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("    //存储租户、公寓、多径等id的控件\r\n");
      out.write("    var currLayoutLabel = 'layOutCdList_v0';\r\n");
      out.write("\r\n");
      out.write("    $(function () {\r\n");
      out.write("        //注册维度切换事件以及渲染样式，注册快速搜索事件\r\n");
      out.write("        init();\r\n");
      out.write("        $('#bisProjectId').val(currProjectId);\r\n");
      out.write("        $('#welcom').show();\r\n");
      out.write("        changlayOutDetail1();\r\n");
      out.write("        loadDefault();\r\n");
      out.write("        initClick();\r\n");
      out.write("    });\r\n");
      out.write("    /**跳到实收*/\r\n");
      out.write("    function actIncomeJumper(demo){\r\n");
      out.write("        if(demo==2){\r\n");
      out.write("\t\t\t$(\"#actInCome\").click();\r\n");
      out.write("        }else{\r\n");
      out.write("\t\t\t$(\"#fact\").click();\r\n");
      out.write("        }\r\n");
      out.write("    }\r\n");
      out.write("    //批量导入导出应收实收数据\r\n");
      out.write("    function batchOper() {\r\n");
      out.write("        parent.TabUtils.newTab(\"supInput\", \"批量操作\", '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-fact!batchOper.action?bisProjectId=' + currProjectId, true);\r\n");
      out.write("    }\r\n");
      out.write("    /**初始化页面(应收)传过来的点击初始化事件*/\r\n");
      out.write("    function initClick(){\r\n");
      out.write("\t\tif(\"1\" == $(\"#mustclickId\").val()){\r\n");
      out.write("\t\t\t$(\"#btn_new_fact\").click();\r\n");
      out.write("\t\t}\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    function clearSearch(){\r\n");
      out.write("        $(\"#creatorQ\").val(\"\");\r\n");
      out.write("        $(\"#creator\").val(\"\");\r\n");
      out.write("        $(\"#minMonth\").val(\"\");\r\n");
      out.write("        $(\"#maxMonth\").val(\"\");\r\n");
      out.write("        $(\"#checkUserCdQ\").val(\"\");\r\n");
      out.write("        $(\"#checkUserCd\").val(\"\");\r\n");
      out.write("        $(\"#minMoney\").val(\"\");\r\n");
      out.write("        $(\"#maxMoney\").val(\"\");\r\n");
      out.write("    }\r\n");
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

  private boolean _jspx_meth_s_005fselect_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f0 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005frequired_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f0.setParent(null);
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(89,16) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setCssStyle("height:24px;line-height:20px;");
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(89,16) null
    _jspx_th_s_005fselect_005f0.setDynamicAttribute(null, "validate", new String("required"));
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(89,16) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setList("@com.hhz.ump.util.DictMapUtil@getMapContBigTypeNew()");
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(89,16) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setListKey("key");
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(89,16) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setListValue("value");
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(89,16) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setName("layOutCd");
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(89,16) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setId("layOutCd");
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(89,16) name = onchange type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setOnchange("changlayOutDetail1();");
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(89,16) name = required type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setRequired("true");
    int _jspx_eval_s_005fselect_005f0 = _jspx_th_s_005fselect_005f0.doStartTag();
    if (_jspx_eval_s_005fselect_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fselect_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fselect_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fselect_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_s_005fselect_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fselect_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fselect_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005frequired_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle.reuse(_jspx_th_s_005fselect_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005frequired_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle.reuse(_jspx_th_s_005fselect_005f0);
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
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(112,62) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f0.setTest("''==shopStoreName||null==shopStoreName");
    int _jspx_eval_s_005fif_005f0 = _jspx_th_s_005fif_005f0.doStartTag();
    if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f0.doInitBody();
      }
      do {
        out.write("color:#ccc;");
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

  private boolean _jspx_meth_s_005felse_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:else
    org.apache.struts2.views.jsp.ElseTag _jspx_th_s_005felse_005f0 = (org.apache.struts2.views.jsp.ElseTag) _005fjspx_005ftagPool_005fs_005felse.get(org.apache.struts2.views.jsp.ElseTag.class);
    _jspx_th_s_005felse_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005felse_005f0.setParent(null);
    int _jspx_eval_s_005felse_005f0 = _jspx_th_s_005felse_005f0.doStartTag();
    if (_jspx_eval_s_005felse_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felse_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felse_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felse_005f0.doInitBody();
      }
      do {
        out.write("color:#333;");
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

  private boolean _jspx_meth_s_005fif_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f1 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f1.setParent(null);
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(115,38) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f1.setTest("''==shopStoreName||null==shopStoreName");
    int _jspx_eval_s_005fif_005f1 = _jspx_th_s_005fif_005f1.doStartTag();
    if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f1.doInitBody();
      }
      do {
        out.write("搜索商家/商铺");
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

  private boolean _jspx_meth_s_005felse_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:else
    org.apache.struts2.views.jsp.ElseTag _jspx_th_s_005felse_005f1 = (org.apache.struts2.views.jsp.ElseTag) _005fjspx_005ftagPool_005fs_005felse.get(org.apache.struts2.views.jsp.ElseTag.class);
    _jspx_th_s_005felse_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005felse_005f1.setParent(null);
    int _jspx_eval_s_005felse_005f1 = _jspx_th_s_005felse_005f1.doStartTag();
    if (_jspx_eval_s_005felse_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felse_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felse_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felse_005f1.doInitBody();
      }
      do {
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${shopStoreName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
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

  private boolean _jspx_meth_s_005fselect_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f1 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f1.setParent(null);
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(121,24) name = onchange type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setOnchange("setThisTitle('chargeTypeCd');");
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(121,24) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setList("@com.hhz.ump.util.DictMapUtil@getMapChargeType()");
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(121,24) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setListKey("key");
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(121,24) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setListValue("value");
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(121,24) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setName("chargeTypeCd");
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(121,24) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setId("chargeTypeCd");
    int _jspx_eval_s_005fselect_005f1 = _jspx_th_s_005fselect_005f1.doStartTag();
    if (_jspx_th_s_005fselect_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.reuse(_jspx_th_s_005fselect_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.reuse(_jspx_th_s_005fselect_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f2 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest_005fnobody.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f2.setParent(null);
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(128,56) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f2.setTest("''==reportDateStart||null==reportDateStart");
    int _jspx_eval_s_005fif_005f2 = _jspx_th_s_005fif_005f2.doStartTag();
    if (_jspx_th_s_005fif_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest_005fnobody.reuse(_jspx_th_s_005fif_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest_005fnobody.reuse(_jspx_th_s_005fif_005f2);
    return false;
  }

  private boolean _jspx_meth_s_005felse_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:else
    org.apache.struts2.views.jsp.ElseTag _jspx_th_s_005felse_005f2 = (org.apache.struts2.views.jsp.ElseTag) _005fjspx_005ftagPool_005fs_005felse.get(org.apache.struts2.views.jsp.ElseTag.class);
    _jspx_th_s_005felse_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005felse_005f2.setParent(null);
    int _jspx_eval_s_005felse_005f2 = _jspx_th_s_005felse_005f2.doStartTag();
    if (_jspx_eval_s_005felse_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felse_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felse_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felse_005f2.doInitBody();
      }
      do {
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${reportDateStart}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        int evalDoAfterBody = _jspx_th_s_005felse_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005felse_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005felse_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f2);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f3 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest_005fnobody.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f3.setParent(null);
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(134,53) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f3.setTest("''==reportDateEnd||null==reportDateEnd");
    int _jspx_eval_s_005fif_005f3 = _jspx_th_s_005fif_005f3.doStartTag();
    if (_jspx_th_s_005fif_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest_005fnobody.reuse(_jspx_th_s_005fif_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest_005fnobody.reuse(_jspx_th_s_005fif_005f3);
    return false;
  }

  private boolean _jspx_meth_s_005felse_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:else
    org.apache.struts2.views.jsp.ElseTag _jspx_th_s_005felse_005f3 = (org.apache.struts2.views.jsp.ElseTag) _005fjspx_005ftagPool_005fs_005felse.get(org.apache.struts2.views.jsp.ElseTag.class);
    _jspx_th_s_005felse_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005felse_005f3.setParent(null);
    int _jspx_eval_s_005felse_005f3 = _jspx_th_s_005felse_005f3.doStartTag();
    if (_jspx_eval_s_005felse_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felse_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felse_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felse_005f3.doInitBody();
      }
      do {
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${reportDateEnd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        int evalDoAfterBody = _jspx_th_s_005felse_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005felse_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005felse_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f3);
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
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(141,24) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f0.setIfAnyGranted("A_FACT_INSERT");
    int _jspx_eval_security_005fauthorize_005f0 = _jspx_th_security_005fauthorize_005f0.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                            <input id=\"btn_new_fact\" class=\"btn_blue fact_dime\" type=\"button\" style=\"width:65px; \"\r\n");
        out.write("                                   onclick=\"appendFact();\" value=\"新增实收\"/>\r\n");
        out.write("\r\n");
        out.write("                            <input id=\"btn_imp_fact\" class=\"btn_green\" type=\"button\" style=\"width:65px;\"\r\n");
        out.write("                                   onclick=\"batchOper();\" value=\"批量操作\"/>\r\n");
        out.write("                            <input type=\"button\" class=\"btn_green\" onclick=\"doExport4Search('fact');\" value=\"导出\"/>\r\n");
        out.write("                        ");
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
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(149,24) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f1.setIfAnyGranted("A_FACT_CHECK");
    int _jspx_eval_security_005fauthorize_005f1 = _jspx_th_security_005fauthorize_005f1.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                            <input type=\"button\" class=\"btn_green\" onclick=\"doUpdateAll(1);\" value=\"批量审核\"/>\r\n");
        out.write("                        ");
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

  private boolean _jspx_meth_s_005fselect_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f2 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f2.setParent(null);
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(219,24) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setList("@com.hhz.ump.util.DictMapUtil@getMapBisFactStatus()");
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(219,24) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setListKey("key");
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(219,24) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setListValue("value");
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(219,24) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setName("statusCd");
    // /WEB-INF/content/bis/bis-fact-headerFact.jsp(219,24) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setId("statusCd");
    int _jspx_eval_s_005fselect_005f2 = _jspx_th_s_005fselect_005f2.doStartTag();
    if (_jspx_th_s_005fselect_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.reuse(_jspx_th_s_005fselect_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.reuse(_jspx_th_s_005fselect_005f2);
    return false;
  }
}
