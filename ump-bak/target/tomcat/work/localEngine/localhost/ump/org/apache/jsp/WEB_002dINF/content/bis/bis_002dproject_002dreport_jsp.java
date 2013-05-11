package org.apache.jsp.WEB_002dINF.content.bis;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

public final class bis_002dproject_002dreport_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(4);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/common/nocache.jsp");
    _jspx_dependants.add("/common/global.jsp");
    _jspx_dependants.add("/WEB-INF/PowerDesk-tags.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fset_0026_005fvar;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fset_0026_005fvar = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.release();
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.release();
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.release();
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
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" style=\"overflow: hidden;\">\r\n");
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
      out.write("\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/common.css\"/>\r\n");
      out.write("    <link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/desk/thickbox.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/prompt/skin/custom_1/ymPrompt.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/bis/bisCont.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/plan/cost-ctrl.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/bis/bisReport.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/bis/bis-shop.css\"/>\r\n");
      out.write("\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/common.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jqueryplugin/chilltip.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.form.pack.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/prompt/ymPrompt.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/desk/MaskLayer.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/datePicker/WdatePicker.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/validate/PdValidate.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/bis/bisCont.js\"></script>\r\n");
      out.write("    <title>项目经营报表</title>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body style=\"min-height: 450px;\">\r\n");
      //  s:set
      org.apache.struts2.views.jsp.SetTag _jspx_th_s_005fset_005f0 = (org.apache.struts2.views.jsp.SetTag) _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.get(org.apache.struts2.views.jsp.SetTag.class);
      _jspx_th_s_005fset_005f0.setPageContext(_jspx_page_context);
      _jspx_th_s_005fset_005f0.setParent(null);
      // /WEB-INF/content/bis/bis-project-report.jsp(31,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
      out.write("<form id=\"mainFormSearch\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-project-report!load.action\">\r\n");
      out.write("<input type=\"hidden\" id=\"showmanageCdId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${manageCd }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("<input type=\"hidden\" name=\"reportType\" id=\"reportType\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${reportType}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("<input type=\"hidden\" name=\"chargeTypeCd\" id=\"chargeTypeCd\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${chargeTypeCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\r\n");
      if (_jspx_meth_s_005fset_005f1(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/bis/bis-manage.css\"/>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/bis/ymPrompt.css\"/>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/bis/bis.project.select.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/bis/bisManageHeader.js\"></script>\r\n");
      out.write("<div class=\"title_bar none\" style=\"display:none;\">\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("    ");
      if (_jspx_meth_s_005fif_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id=\"div_title\" style=\"height: 30px;\">\r\n");
      out.write("    <div class=\"bis_search\" id=\"main_search_div\" style=\" background: #FFF;border-bottom:0px\">\r\n");
      out.write("        <table class=\"tb_search\">\r\n");
      out.write("            <col width=\"37\"/>\r\n");
      out.write("            <col width=\"100\"/>\r\n");
      out.write("            <col width=\"50\"/>\r\n");
      out.write("            <col width=\"50\"/>\r\n");
      out.write("            <col width=\"4\"/>\r\n");
      out.write("            <col width=\"75\"/>\r\n");
      out.write("            <col width=\"4\"/>\r\n");
      out.write("            ");
      out.write("\r\n");
      out.write("            <col/>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td>项目：</td>\r\n");
      out.write("                <td>\r\n");
      out.write("                    <input type=\"text\" validate=\"required\" id=\"bisProjectName\" name=\"bisProjectName\"\r\n");
      out.write("                           value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" readonly=\"true\"\r\n");
      out.write("                           style=\"width:100%; cursor: pointer; font-size: 12px; color: #ff0000;height: 16px;line-height: 16px;\"/>\r\n");
      out.write("                    <input type=\"hidden\" id=\"bisProjectId\" name=\"bisProjectId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("                    ");
      out.write("\r\n");
      out.write("                </td>\r\n");
      out.write("                <td align=\"right\">月份：</td>\r\n");
      out.write("                <td>\r\n");
      out.write("                    <input validate=\"required\" class=\"Wdate text\" style=\"width:75px;height: 16px;line-height: 16px;\"\r\n");
      out.write("                           type=\"text\"\r\n");
      out.write("                           name=\"reportDate\"\r\n");
      out.write("                           id=\"reportDate\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${reportDate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" onfocus=\"WdatePicker({dateFmt:'yyyy-MM'})\"/>\r\n");
      out.write("                </td>\r\n");
      out.write("                <td>&nbsp;</td>\r\n");
      out.write("                <td>\r\n");
      out.write("                 ");
      out.write("\r\n");
      out.write("                </td>\r\n");
      out.write("                <td>&nbsp;</td>\r\n");
      out.write("                <td>\r\n");
      out.write("                    <style type=\"text/css\">\r\n");
      out.write("                        div.bts_container {\r\n");
      out.write("                            float: left;\r\n");
      out.write("                            padding-right: 10px;\r\n");
      out.write("                            position: relative;\r\n");
      out.write("                            width: 100px;\r\n");
      out.write("                            height: 20px;\r\n");
      out.write("                        }\r\n");
      out.write("\r\n");
      out.write("                        div.bts_container ul {\r\n");
      out.write("                            overflow: hidden;\r\n");
      out.write("                            height: 19px;\r\n");
      out.write("                            padding-top: 1px;\r\n");
      out.write("                            position: absolute;\r\n");
      out.write("                            border: 1px solid #000;\r\n");
      out.write("                            background: #FFF;\r\n");
      out.write("                            margin-top: 0px;\r\n");
      out.write("                        }\r\n");
      out.write("\r\n");
      out.write("                        div.bts_container:hover  ul {\r\n");
      out.write("                            height: auto;\r\n");
      out.write("                        }\r\n");
      out.write("\r\n");
      out.write("                        ul.bis_cgt li {\r\n");
      out.write("                            width: 98px;\r\n");
      out.write("                            margin: 0px;\r\n");
      out.write("                            padding: 0px 5px 0px 5px;\r\n");
      out.write("                            margin: 1px;\r\n");
      out.write("                            margin-top: 0px;\r\n");
      out.write("                            float: none;\r\n");
      out.write("                        }\r\n");
      out.write("                    </style>\r\n");
      out.write("                    <div class=\"bts_container\" >\r\n");
      out.write("                        <ul class=\"bis_cgt\" id=\"bis_cgt_store\" style=\"float:left; display: none;\">\r\n");
      out.write("                            <li style=\"background:#0072bb;color: #FFF\">费用类型</li>\r\n");
      out.write("                            ");
      if (_jspx_meth_s_005fiterator_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                        </ul>\r\n");
      out.write("                        <ul class=\"bis_cgt\" id=\"bis_cgt_flat\" style=\"float:left; width:100%; display: none;\">\r\n");
      out.write("                            <li style=\"background:#0072bb;color: #FFF\">费用类型</li>\r\n");
      out.write("                            ");
      if (_jspx_meth_s_005fiterator_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                        </ul>\r\n");
      out.write("                        <ul class=\"bis_cgt\" id=\"bis_cgt_multi\" style=\"float:left; width:100%; display: none;\">\r\n");
      out.write("                            <li style=\"background:#0072bb;color: #FFF\">费用类型</li>\r\n");
      out.write("                            ");
      if (_jspx_meth_s_005fiterator_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                        </ul>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    ");
      if (_jspx_meth_s_005fif_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("                    <input type=\"button\" value=\"搜索\" class=\"btn_blue\" onclick=\"ajaxSearch();\"\r\n");
      out.write("                           style=\"float: left;margin-top:0px;height:22px;line-height: 22px;margin-left: 6px;\"/>\r\n");
      out.write("\r\n");
      out.write("                </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr id=\"trChargeType\" style=\"display: none;\">\r\n");
      out.write("                <td colspan=\"8\" style=\"padding-left: 10px;\">\r\n");
      out.write("                    <ul class=\"bis_cgt\" id=\"bis_cgt_store\" style=\"float:left; width:100%; display: none;\">\r\n");
      out.write("                        ");
      if (_jspx_meth_s_005fiterator_005f3(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                    </ul>\r\n");
      out.write("                    <ul class=\"bis_cgt\" id=\"bis_cgt_flat\" style=\"float:left; width:100%; display: none;\">\r\n");
      out.write("                        ");
      if (_jspx_meth_s_005fiterator_005f4(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                    </ul>\r\n");
      out.write("                    <ul class=\"bis_cgt\" id=\"bis_cgt_multi\" style=\"float:left; width:100%; display: none;\">\r\n");
      out.write("                        ");
      if (_jspx_meth_s_005fiterator_005f5(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id=\"seniorSearchDiv\"\r\n");
      out.write("     style=\"position:absolute; width:650px; height:130px; top:31px; left:8px; display:none; background-color:#fff; border:1px solid #000; padding:10px 20px 10px 0px; z-index:8;\">\r\n");
      out.write("    <table class=\"tb_search\" cellpadding=\"0\" cellspacing=\"0\">\r\n");
      out.write("        <col width=\"80\"/>\r\n");
      out.write("        <col width=\"130\"/>\r\n");
      out.write("        <col width=\"80\"/>\r\n");
      out.write("        <col width=\"130\"/>\r\n");
      out.write("        <col width=\"80\"/>\r\n");
      out.write("        <col width=\"130\"/>\r\n");
      out.write("    </table>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("    isEmpty = function (str) {\r\n");
      out.write("        return (typeof (str) === \"undefined\" || str === null || (str.length === 0));\r\n");
      out.write("    };\r\n");
      out.write("    var _MODULE = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${module}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\r\n");
      out.write("    $(function () {\r\n");
      out.write("        $(\"#bis-nav li\").removeClass(\"bis-nav-click\");\r\n");
      out.write("        $(\"#li_\" + _MODULE).removeClass(\"bis-nav-border\");\r\n");
      out.write("        $(\"#li_\" + _MODULE).addClass(\"bis-nav-click\");\r\n");
      out.write("        if ($(\"#bisProjectName\").val() == '') {\r\n");
      out.write("            $(\"#bisProjectName\").val(\"--选择项目--\");\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        if (\"true\" != \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isBusinessCompanyUser}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\") {\r\n");
      out.write("            $('#bisProjectName').onSelect({\r\n");
      out.write("                top:10,\r\n");
      out.write("                muti:false, callback:function (obj) {\r\n");
      out.write("                    if (obj.bisProjectId != \"1\") {\r\n");
      out.write("                        parent.bisProjectId = obj.bisProjectId;\r\n");
      out.write("                        $(\"#bisProjectName\").val(obj.projectName);\r\n");
      out.write("                        $(\"#bisProjectName\").next().val(obj.bisProjectId);\r\n");
      out.write("                        ajaxSearch();\r\n");
      out.write("                    }\r\n");
      out.write("                }});\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        $('#btnLayOut').onSelect({\r\n");
      out.write("            muti:false,\r\n");
      out.write("            callback:function (project) {\r\n");
      out.write("                goFloor(project.bisProjectId);\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        if (location.href.indexOf(\"bisProjectId\") == -1) {\r\n");
      out.write("            $('#bisProjectName').trigger(\"click\");\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("<div id=\"reportContent\" style=\"clear:both; word-break:break-all;  text-align:center; overflow: hidden;\">\r\n");
      out.write("    <div style=\"color: #6BAD42; font-size: 14px; font-weight: bold; text-align: center; line-height: 300px;\">请选择搜索条件！\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("/**\r\n");
      out.write(" * 切换报表类别\r\n");
      out.write(" */\r\n");
      out.write("function changeReportType(id) {\r\n");
      out.write("    if (id == 'li_rpt_1') {\r\n");
      out.write("        $(\"#bis_rpt li\").removeClass(\"bis_rpt_click\");\r\n");
      out.write("        $(\"#li_rpt_1\").addClass(\"bis_rpt_click\");\r\n");
      out.write("        $(\"#reportType\").val(\"total\");\r\n");
      out.write("        showChargeType(false);\r\n");
      out.write("    } else if (id == 'li_rpt_2') {\r\n");
      out.write("        $(\"#bis_rpt li\").removeClass(\"bis_rpt_click\");\r\n");
      out.write("        $(\"#li_rpt_2\").addClass(\"bis_rpt_click\");\r\n");
      out.write("        $(\"#reportType\").val(\"store\");\r\n");
      out.write("        showChargeType(true, 'store');\r\n");
      out.write("    } else if (id == 'li_rpt_6') {\r\n");
      out.write("        $(\"#bis_rpt li\").removeClass(\"bis_rpt_click\");\r\n");
      out.write("        $(\"#li_rpt_6\").addClass(\"bis_rpt_click\");\r\n");
      out.write("        $(\"#reportType\").val(\"walkStreet\");\r\n");
      out.write("        showChargeType(true, 'store');\r\n");
      out.write("    } else if (id == 'li_rpt_3') {\r\n");
      out.write("        $(\"#bis_rpt li\").removeClass(\"bis_rpt_click\");\r\n");
      out.write("        $(\"#li_rpt_3\").addClass(\"bis_rpt_click\");\r\n");
      out.write("        $(\"#reportType\").val(\"flat\");\r\n");
      out.write("        showChargeType(true, 'flat');\r\n");
      out.write("    } else if (id == 'li_rpt_4') {\r\n");
      out.write("        //alert(\"此功能暂未开放\");\r\n");
      out.write("        //return;\r\n");
      out.write("        $(\"#bis_rpt li\").removeClass(\"bis_rpt_click\");\r\n");
      out.write("        $(\"#li_rpt_4\").addClass(\"bis_rpt_click\");\r\n");
      out.write("        $(\"#reportType\").val(\"multi\");\r\n");
      out.write("        // multi...\r\n");
      out.write("        showChargeType(true, 'multi');\r\n");
      out.write("    } else if (id == 'li_rpt_5') {\r\n");
      out.write("        $(\"#bis_rpt li\").removeClass(\"bis_rpt_click\");\r\n");
      out.write("        $(\"#li_rpt_5\").addClass(\"bis_rpt_click\");\r\n");
      out.write("        $(\"#reportType\").val(\"manage\");\r\n");
      out.write("    }\r\n");
      out.write("    if (validateSearch()) {\r\n");
      out.write("        ajaxSearch();\r\n");
      out.write("    }\r\n");
      out.write("}\r\n");
      out.write("/**\r\n");
      out.write(" * 选择费用类别\r\n");
      out.write(" */\r\n");
      out.write("function sltReportCharge(dom) {\r\n");
      out.write("\r\n");
      out.write("    if ($(dom).hasClass(\"bis_cgt_click\")) {\r\n");
      out.write("        $(dom).addClass('bis_cgt_li');\r\n");
      out.write("        $(dom).removeClass('bis_cgt_click');\r\n");
      out.write("    } else {\r\n");
      out.write("        $(dom).removeClass('bis_cgt_li');\r\n");
      out.write("        $(dom).addClass('bis_cgt_click');\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("/**\r\n");
      out.write(" * 显示费用类别\r\n");
      out.write(" */\r\n");
      out.write("function showChargeType(flag, type) {\r\n");
      out.write("    if (flag) {\r\n");
      out.write("        $(\"#main_search_div\").css({height:\"60px\"});\r\n");
      out.write("        //$(\"#reportContent\").css(\"height\", $(window).height() - 27 - 30 - 30 - 12 - 2 + \"px\");\r\n");
      out.write("        $(\"#div_title\").css(\"height\", \"60px\");\r\n");
      out.write("        $(\"#trChargeType\").show();\r\n");
      out.write("        if (type == 'store') {\r\n");
      out.write("            $(\"#bis_cgt_store\").show();\r\n");
      out.write("            $(\"#bis_cgt_flat\").hide();\r\n");
      out.write("            $(\"#bis_cgt_multi\").hide();\r\n");
      out.write("        } else if (type == 'flat') {\r\n");
      out.write("            $(\"#bis_cgt_flat\").show();\r\n");
      out.write("            $(\"#bis_cgt_store\").hide();\r\n");
      out.write("            $(\"#bis_cgt_multi\").hide();\r\n");
      out.write("        } else if (type == 'multi') {\r\n");
      out.write("            $(\"#bis_cgt_multi\").show();\r\n");
      out.write("            $(\"#bis_cgt_store\").hide();\r\n");
      out.write("            $(\"#bis_cgt_flat\").hide();\r\n");
      out.write("        }\r\n");
      out.write("    } else {\r\n");
      out.write("        $(\"#main_search_div\").css({height:\"30px\"});\r\n");
      out.write("        //$(\"#reportContent\").css(\"height\", $(window).height() - 27 - 30 - 12 + \"px\");\r\n");
      out.write("        $(\"#div_title\").css(\"height\", \"30px\");\r\n");
      out.write("        $(\"#trChargeType\").hide();\r\n");
      out.write("    }\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function validateSearch() {\r\n");
      out.write("    if ($(\"#bisProjectName\").val() == '--选择项目--') {\r\n");
      out.write("        $(\"#bisProjectName\").val('');\r\n");
      out.write("    }\r\n");
      out.write("    var pdv = new Validate(\"mainFormSearch\");\r\n");
      out.write("    if (!pdv.validate()) {\r\n");
      out.write("        //alert(\"请填写完整\");\r\n");
      out.write("        $(\"#mainFormSearch :input,textarea\").filter(\"[validate=required]\").filter(\"[value='']\").eq(0).focus();\r\n");
      out.write("        if ($(\"#bisProjectName\").val() == '') {\r\n");
      out.write("            $(\"#bisProjectName\").val('--选择项目--');\r\n");
      out.write("        }\r\n");
      out.write("        return false;\r\n");
      out.write("    }\r\n");
      out.write("    return true;\r\n");
      out.write("}\r\n");
      out.write("/**\r\n");
      out.write(" * 搜索\r\n");
      out.write(" */\r\n");
      out.write("function ajaxSearch() {\r\n");
      out.write("\r\n");
      out.write("    if (!validateSearch()) {\r\n");
      out.write("        return false;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    var reportType = $(\"#reportType\").val();\r\n");
      out.write("\r\n");
      out.write("    if (reportType == 'total' || reportType == 'manage') {\r\n");
      out.write("        $(\"#mainFormSearch\").attr(\"action\", \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-project-report!load.action\");\r\n");
      out.write("    } else if (reportType == 'store' || reportType == 'walkStreet') {\r\n");
      out.write("        var chargeTypeCd = \"\";\r\n");
      out.write("        $(\"#bis_cgt_store\").find(\".bis_cgt_click\").each(function (i, dom) {\r\n");
      out.write("            chargeTypeCd += dom.value + \",\";\r\n");
      out.write("        });\r\n");
      out.write("        $(\"#chargeTypeCd\").val(chargeTypeCd.substring(0, chargeTypeCd.length - 1));\r\n");
      out.write("    } else if (reportType == 'flat') {\r\n");
      out.write("        var chargeTypeCd = \"\";\r\n");
      out.write("        $(\"#bis_cgt_flat\").find(\".bis_cgt_click\").each(function (i, dom) {\r\n");
      out.write("            chargeTypeCd += dom.value + \",\";\r\n");
      out.write("        });\r\n");
      out.write("        $(\"#chargeTypeCd\").val(chargeTypeCd.substring(0, chargeTypeCd.length - 1));\r\n");
      out.write("    } else if (reportType == 'multi') {\r\n");
      out.write("        var chargeTypeCd = \"\";\r\n");
      out.write("        $(\"#bis_cgt_multi\").find(\".bis_cgt_click\").each(function (i, dom) {\r\n");
      out.write("            chargeTypeCd += dom.value + \",\";\r\n");
      out.write("        });\r\n");
      out.write("        $(\"#chargeTypeCd\").val(chargeTypeCd.substring(0, chargeTypeCd.length - 1));\r\n");
      out.write("    } else {\r\n");
      out.write("        return;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    TB_showMaskLayer(\"正在搜索...\");\r\n");
      out.write("    $(\"#mainFormSearch\").ajaxSubmit(function (result) {\r\n");
      out.write("        $(\"#reportContent\").html(result);\r\n");
      out.write("        if (reportType == 'store' || reportType == 'flat' || reportType == 'multi') {\r\n");
      out.write("            var typeStr = (chargeTypeCd.split(\",\")).length - 1;\r\n");
      out.write("            if (chargeTypeCd == \"\")\r\n");
      out.write("                typeStr = 2;\r\n");
      out.write("            $(\"#rightTable\").width(460 * typeStr);\r\n");
      out.write("            $(\"#rightTopTable\").width(460 * typeStr);\r\n");
      out.write("            var contentWidth = $(\"#reportContent\").width();\r\n");
      out.write("            $(\"#rightTopDiv\").width(contentWidth - 250);\r\n");
      out.write("            $(\"#rightDiv\").width(contentWidth - 250);\r\n");
      out.write("            //取高度\r\n");
      out.write("            var contentHeight = $(\"#reportContent\").height();\r\n");
      out.write("            $(\"#rightDiv\").height(contentHeight - 90);\r\n");
      out.write("            $(\"#leftDiv\").height(contentHeight - 90);\r\n");
      out.write("\r\n");
      out.write("            if ($(\"#reportContent\").hasClass(\"scroll_y_show\")) {\r\n");
      out.write("                $(\"#reportContent\").removeClass(\"scroll_y_show\");\r\n");
      out.write("            }\r\n");
      out.write("        } else {\r\n");
      out.write("            if (!$(\"#reportContent\").hasClass(\"scroll_y_show\")) {\r\n");
      out.write("                $(\"#reportContent\").addClass(\"scroll_y_show\");\r\n");
      out.write("            }\r\n");
      out.write("        }\r\n");
      out.write("        TB_removeMaskLayer();\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/**\r\n");
      out.write(" * 查看费用\r\n");
      out.write(" */\r\n");
      out.write("function viewFeeManage() {\r\n");
      out.write("    /*window.location = \"\r\n");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis - manage!layout.action ? bisProjectId = \"+$(\"#bisProjectId\").val()+\" & module = 3\";*/\r\n");
      out.write("    var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-manage!layout.action?module=3&bisProjectId=\" + $(\"#bisProjectId\").val();\r\n");
      out.write("    if (parent.parent.TabUtils == null)\r\n");
      out.write("        window.open(url);\r\n");
      out.write("    else\r\n");
      out.write("        parent.parent.TabUtils.newTab(\"bisFee\", \"收费明细\", url, true);\r\n");
      out.write("}\r\n");
      out.write("/**\r\n");
      out.write(" * 查看合同\r\n");
      out.write(" */\r\n");
      out.write("function viewContManage() {\r\n");
      out.write("    /*window.location = \"\r\n");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis - manage!layout.action ? bisProjectId = \"+$(\"#bisProjectId\").val()+\" & module = 4\";*/\r\n");
      out.write("    var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-manage!layout.action?module=4&bisProjectId=\" + $(\"#bisProjectId\").val();\r\n");
      out.write("    if (parent.parent.TabUtils == null)\r\n");
      out.write("        window.open(url);\r\n");
      out.write("    else\r\n");
      out.write("        parent.parent.TabUtils.newTab(\"bisCont\", \"商业合同管理\", url, true);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/**\r\n");
      out.write(" * 更新报表数据\r\n");
      out.write(" */\r\n");
      out.write("function refreshData() {\r\n");
      out.write("    TB_showMaskLayer(\"正在更新...\");\r\n");
      out.write("    var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-project-report!refreshData.action\";\r\n");
      out.write("    $.post(url, {reportDate:$(\"#reportDate\").val()}, function (result) {\r\n");
      out.write("        TB_removeMaskLayer();\r\n");
      out.write("        TB_showMaskLayer(\"更新完成\", 1000);\r\n");
      out.write("    });\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("    //$(\"#reportContent\").height($(window).height() - 27 - 30 - 12 + \"px\");\r\n");
      out.write("    var reportType = $(\"#reportType\").val();\r\n");
      out.write("    if (reportType == 'store') {\r\n");
      out.write("        changeReportType('li_rpt_2');\r\n");
      out.write("    }\r\n");
      out.write("    ");
      if (_jspx_meth_security_005fauthorize_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    $(function () {\r\n");
      out.write("        if (location.href.indexOf(\"cash\") != -1) {\r\n");
      out.write("            changeReportType('li_rpt_1');\r\n");
      out.write("        } else if (location.href.indexOf(\"operate\") != -1) {\r\n");
      out.write("            changeReportType('li_rpt_5');\r\n");
      out.write("        } else if (location.href.indexOf(\"store\") != -1) {\r\n");
      out.write("            changeReportType('li_rpt_2');\r\n");
      out.write("        } else if (location.href.indexOf(\"walkStreet\") != -1) {\r\n");
      out.write("            changeReportType('li_rpt_6');\r\n");
      out.write("        } else if (location.href.indexOf(\"flat\") != -1) {\r\n");
      out.write("            changeReportType('li_rpt_3');\r\n");
      out.write("        } else if (location.href.indexOf(\"multi\") != -1) {\r\n");
      out.write("            changeReportType('li_rpt_4');\r\n");
      out.write("        }\r\n");
      out.write("        dynamicChange($(\"#showmanageCdId\").val());\r\n");
      out.write("    });\r\n");
      out.write("    /**动态选中*/\r\n");
      out.write("    function dynamicChange(demo){\r\n");
      out.write("        if(demo!=undefined&&demo!=\"\"||demo!=null){\r\n");
      out.write("            demo = parseInt(demo)+1;\r\n");
      out.write("    \t\t$(\"#manageCd option:nth-child(\"+demo+\")\").attr(\"selected\" , \"selected\");\r\n");
      out.write("        } \r\n");
      out.write("    }\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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

  private boolean _jspx_meth_s_005fset_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:set
    org.apache.struts2.views.jsp.SetTag _jspx_th_s_005fset_005f1 = (org.apache.struts2.views.jsp.SetTag) _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.get(org.apache.struts2.views.jsp.SetTag.class);
    _jspx_th_s_005fset_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fset_005f1.setParent(null);
    // /WEB-INF/content/bis/bis-project-report.jsp(38,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fset_005f1.setVar("module");
    int _jspx_eval_s_005fset_005f1 = _jspx_th_s_005fset_005f1.doStartTag();
    if (_jspx_eval_s_005fset_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fset_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fset_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fset_005f1.doInitBody();
      }
      do {
        out.write('2');
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

  private boolean _jspx_meth_s_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f0 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f0.setParent(null);
    // /WEB-INF/content/bis/bis-project-report.jsp(48,4) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f0.setTest("#curUserCd=='baolm' || #curUserCd=='lujy' || #curUserCd=='shenjian' || #curUserCd=='jiaoxf' || #curUserCd=='dengyh'");
    int _jspx_eval_s_005fif_005f0 = _jspx_th_s_005fif_005f0.doStartTag();
    if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("        &nbsp;&nbsp;<input type=\"button\" class=\"btn_blue\" style=\"width:75px;height:22px;line-height: 22px;\" value=\"更新数据\" onclick=\"refreshData();\"/>\r\n");
        out.write("        <!--input type=\"button\" class=\"btn_blue\" style=\"width:75px;height:22px;line-height: 22px;\" value=\"合同导出\"\r\n");
        out.write("        onclick=\"exportExcel();\"/-->\r\n");
        out.write("    ");
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

  private boolean _jspx_meth_s_005fiterator_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_005fiterator_005f0 = (org.apache.struts2.views.jsp.IteratorTag) _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_005fiterator_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fiterator_005f0.setParent(null);
    // /WEB-INF/content/bis/bis-project-report.jsp(130,28) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f0.setValue("@com.hhz.ump.util.DictMapUtil@getMapChargeTypeSrpt()");
    int _jspx_eval_s_005fiterator_005f0 = _jspx_th_s_005fiterator_005f0.doStartTag();
    if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fiterator_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fiterator_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                                <li id=\"li_cgt_");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${key}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${key}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" class=\"bis_cgt_li\"\r\n");
        out.write("                                    onclick=\"sltReportCharge(this);\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${value}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</li>\r\n");
        out.write("                            ");
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
    // /WEB-INF/content/bis/bis-project-report.jsp(137,28) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f1.setValue("@com.hhz.ump.util.DictMapUtil@getMapChargeTypeFrpt()");
    int _jspx_eval_s_005fiterator_005f1 = _jspx_th_s_005fiterator_005f1.doStartTag();
    if (_jspx_eval_s_005fiterator_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fiterator_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fiterator_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fiterator_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                                <li id=\"li_cgt_");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${key}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${key}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" class=\"bis_cgt_li\"\r\n");
        out.write("                                    onclick=\"sltReportCharge(this);\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${value}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</li>\r\n");
        out.write("                            ");
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

  private boolean _jspx_meth_s_005fiterator_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_005fiterator_005f2 = (org.apache.struts2.views.jsp.IteratorTag) _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_005fiterator_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fiterator_005f2.setParent(null);
    // /WEB-INF/content/bis/bis-project-report.jsp(144,28) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f2.setValue("@com.hhz.ump.util.DictMapUtil@getMapChargeTypeMrpt()");
    int _jspx_eval_s_005fiterator_005f2 = _jspx_th_s_005fiterator_005f2.doStartTag();
    if (_jspx_eval_s_005fiterator_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fiterator_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fiterator_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fiterator_005f2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                                <li id=\"li_cgt_");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${key}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${key}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" class=\"bis_cgt_li\"\r\n");
        out.write("                                    onclick=\"sltReportCharge(this);\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${value}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</li>\r\n");
        out.write("                            ");
        int evalDoAfterBody = _jspx_th_s_005fiterator_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fiterator_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fiterator_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.reuse(_jspx_th_s_005fiterator_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.reuse(_jspx_th_s_005fiterator_005f2);
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
    // /WEB-INF/content/bis/bis-project-report.jsp(151,20) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f1.setTest("#curUserCd=='baolm' || #curUserCd=='lujy' || #curUserCd=='shenjian' || #curUserCd=='jiaoxf' || #curUserCd=='dengyh'");
    int _jspx_eval_s_005fif_005f1 = _jspx_th_s_005fif_005f1.doStartTag();
    if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                        <input type=\"button\" class=\"btn_blue\"\r\n");
        out.write("                               style=\"width:75px;height:22px;line-height: 22px; float: right;\" value=\"更新数据\"\r\n");
        out.write("                               onclick=\"refreshData();\"/>\r\n");
        out.write("                    ");
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

  private boolean _jspx_meth_s_005fiterator_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_005fiterator_005f3 = (org.apache.struts2.views.jsp.IteratorTag) _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_005fiterator_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005fiterator_005f3.setParent(null);
    // /WEB-INF/content/bis/bis-project-report.jsp(165,24) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f3.setValue("@com.hhz.ump.util.DictMapUtil@getMapChargeTypeSrpt()");
    int _jspx_eval_s_005fiterator_005f3 = _jspx_th_s_005fiterator_005f3.doStartTag();
    if (_jspx_eval_s_005fiterator_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fiterator_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fiterator_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fiterator_005f3.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                            <li id=\"li_cgt_");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${key}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${key}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" class=\"bis_cgt_li\"\r\n");
        out.write("                                onclick=\"sltReportCharge(this);\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${value}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</li>\r\n");
        out.write("                        ");
        int evalDoAfterBody = _jspx_th_s_005fiterator_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fiterator_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fiterator_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.reuse(_jspx_th_s_005fiterator_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.reuse(_jspx_th_s_005fiterator_005f3);
    return false;
  }

  private boolean _jspx_meth_s_005fiterator_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_005fiterator_005f4 = (org.apache.struts2.views.jsp.IteratorTag) _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_005fiterator_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005fiterator_005f4.setParent(null);
    // /WEB-INF/content/bis/bis-project-report.jsp(171,24) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f4.setValue("@com.hhz.ump.util.DictMapUtil@getMapChargeTypeFrpt()");
    int _jspx_eval_s_005fiterator_005f4 = _jspx_th_s_005fiterator_005f4.doStartTag();
    if (_jspx_eval_s_005fiterator_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fiterator_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fiterator_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fiterator_005f4.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                            <li id=\"li_cgt_");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${key}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${key}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" class=\"bis_cgt_li\"\r\n");
        out.write("                                onclick=\"sltReportCharge(this);\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${value}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</li>\r\n");
        out.write("                        ");
        int evalDoAfterBody = _jspx_th_s_005fiterator_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fiterator_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fiterator_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.reuse(_jspx_th_s_005fiterator_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.reuse(_jspx_th_s_005fiterator_005f4);
    return false;
  }

  private boolean _jspx_meth_s_005fiterator_005f5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_005fiterator_005f5 = (org.apache.struts2.views.jsp.IteratorTag) _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_005fiterator_005f5.setPageContext(_jspx_page_context);
    _jspx_th_s_005fiterator_005f5.setParent(null);
    // /WEB-INF/content/bis/bis-project-report.jsp(177,24) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f5.setValue("@com.hhz.ump.util.DictMapUtil@getMapChargeTypeMrpt()");
    int _jspx_eval_s_005fiterator_005f5 = _jspx_th_s_005fiterator_005f5.doStartTag();
    if (_jspx_eval_s_005fiterator_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fiterator_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fiterator_005f5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fiterator_005f5.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                            <li id=\"li_cgt_");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${key}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${key}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" class=\"bis_cgt_li\"\r\n");
        out.write("                                onclick=\"sltReportCharge(this);\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${value}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</li>\r\n");
        out.write("                        ");
        int evalDoAfterBody = _jspx_th_s_005fiterator_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fiterator_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fiterator_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.reuse(_jspx_th_s_005fiterator_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.reuse(_jspx_th_s_005fiterator_005f5);
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
    // /WEB-INF/content/bis/bis-project-report.jsp(463,4) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f0.setIfAnyGranted("A_REPO_PROJ_QUERY,A_REPO_ARRE_QUERY");
    int _jspx_eval_security_005fauthorize_005f0 = _jspx_th_security_005fauthorize_005f0.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("    if ($(\"#bisProjectId\").val() && $(\"#reportDate\").val() && $(\"#reportType\").val()&&(\r\n");
        out.write("            location.href.indexOf(\"cash\")!=-1||location.href.indexOf(\"operate\")!=-1\r\n");
        out.write("            )) {\r\n");
        out.write("        if (reportType == 'total' || reportType == 'manage') {\r\n");
        out.write("            ajaxSearch();\r\n");
        out.write("        }\r\n");
        out.write("    }\r\n");
        out.write("    ");
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
}
