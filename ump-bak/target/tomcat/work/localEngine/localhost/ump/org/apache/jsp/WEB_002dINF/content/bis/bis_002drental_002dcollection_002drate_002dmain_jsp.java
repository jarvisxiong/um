package org.apache.jsp.WEB_002dINF.content.bis;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.hhz.ump.util.DateUtil;
import java.util.Calendar;

public final class bis_002drental_002dcollection_002drate_002dmain_jsp extends org.apache.jasper.runtime.HttpJspBase
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
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fstep_005fend_005fbegin;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fstep_005fend_005fbegin = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fstep_005fend_005fbegin.release();
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" style=\"overflow: auto\">\r\n");
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
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/bis/bis-main-shop-report.css\"\r\n");
      out.write("          media=\"screen\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/loadMask/jquery.loadmask.css\"\r\n");
      out.write("          media=\"screen\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/bis/ymPrompt.css\" media=\"screen\"\r\n");
      out.write("          rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/mes/thickbox.css\" media=\"screen\"\r\n");
      out.write("          rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.js\" type=\"text/javascript\"></script>\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.select.js\"\r\n");
      out.write("            type=\"text/javascript\"></script>\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/datePicker/WdatePicker.js\"\r\n");
      out.write("            type=\"text/javascript\"></script>\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/xheditor/xheditor-zh-cn.min.js\"\r\n");
      out.write("            type=\"text/javascript\"></script>\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/MaskLayer.js\"\r\n");
      out.write("            type=\"text/javascript\"></script>\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/loadMask/jquery.loadmask.min.js\"\r\n");
      out.write("            type=\"text/javascript\"></script>\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/MaskLayer.js\"\r\n");
      out.write("            type=\"text/javascript\"></script>\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/mes/jquery-powerdesk.js\"\r\n");
      out.write("            type=\"text/javascript\"></script>\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/prompt/ymPrompt.js\" type=\"text/javascript\"></script>\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/jqueryplugin/chilltip.js\" type=\"text/javascript\"></script>\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/bis/bis-rental-collection-rate.css\"\r\n");
      out.write("        media=\"screen\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("    <title>宝龙商业 租费收缴率</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");

    Calendar calendar = DateUtil.getCalendar(-7);
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1;
    int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
    int curDate = calendar.getTime().getDate();
    weekOfMonth = calendar.get(Calendar.DAY_OF_WEEK) == 1 ? weekOfMonth - 1 : weekOfMonth;
    if (curDate <= 15) {
        weekOfMonth = 1;
    } else {
        weekOfMonth = 2;
    }

      out.write("\r\n");
      out.write("<div id=\"warp\">\r\n");
      out.write("    <div id=\"header\" class=\"none\">\r\n");
      out.write("        <div class=\"title1 clearfix\">\r\n");
      out.write("            <h2>租费收缴率周报</h2>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"btns\">\r\n");
      out.write("                <button class=\"green qiehuan\" type=\"button\"\r\n");
      out.write("                        style=\"float: right; margin-top: 7px; margin-left: 10px; width: 120px;\">主力店招商周报\r\n");
      out.write("                </button>\r\n");
      out.write("                <button class=\"green qiehuan\" type=\"button\"\r\n");
      out.write("                        style=\"float: right; margin-top: 7px; margin-left: 10px; width: 100px;\">经营日报表\r\n");
      out.write("                </button>\r\n");
      out.write("                <button class=\"green qiehuan\" style=\"width: 100px;\" type=\"button\">项目列表</button>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"form_body condition_panel clearfix\"\r\n");
      out.write("         style=\"background: #FFF;border:none; height: 29px; padding: 0px;\">\r\n");
      out.write("        <form id=\"queryFrom\" method=\"post\"><select id=\"year\"\r\n");
      out.write("                                                   class=\"box date\" style=\"width: 65px;\">\r\n");
      out.write("            ");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fstep_005fend_005fbegin.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f0.setParent(null);
      // /WEB-INF/content/bis/bis-rental-collection-rate-main.jsp(76,12) name = begin type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setBegin(2011);
      // /WEB-INF/content/bis/bis-rental-collection-rate-main.jsp(76,12) name = end type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setEnd(year+3);
      // /WEB-INF/content/bis/bis-rental-collection-rate-main.jsp(76,12) name = step type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setStep(1);
      // /WEB-INF/content/bis/bis-rental-collection-rate-main.jsp(76,12) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setVar("b");
      int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
        if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("                <option value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${b}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('"');
            out.write('>');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${b}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</option>\r\n");
            out.write("            ");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return;
        }
      } catch (Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f0.doFinally();
        _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fstep_005fend_005fbegin.reuse(_jspx_th_c_005fforEach_005f0);
      }
      out.write("\r\n");
      out.write("        </select> <label>年</label> <select id=\"month\" class=\"box date\"\r\n");
      out.write("                                           style=\"width: 50px; margin-left: 10px;\">\r\n");
      out.write("            ");
      if (_jspx_meth_c_005fforEach_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("        </select> <label>月</label> <label style=\"margin-left: 10px;\">第</label> <select\r\n");
      out.write("                id=\"weekOfMonth\" class=\"box date\" style=\"width: 40px; OVERFLOW: yes\">\r\n");
      out.write("            ");
      if (_jspx_meth_c_005fforEach_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("        </select> <label>个双周</label> <label id=\"dataText\" class=\"max\"\r\n");
      out.write("                                            style=\"margin-left: 10px;display:none\"></label> <input type=\"hidden\"\r\n");
      out.write("                                                                                                   id=\"startDay\"\r\n");
      out.write("                                                                                                   name=\"startDay\"/>\r\n");
      out.write("            <input type=\"hidden\" id=\"endDay\" name=\"endDay\"/>\r\n");
      out.write("\r\n");
      out.write("            <div\r\n");
      out.write("                    style=\"text-align: right; margin-top: 0px; border: 0px solid #cccccc; float: right;\">\r\n");
      out.write("<span id=\"status\"\r\n");
      out.write("      style=\"color: red; margin-right: 10px; font-size: 14px; height: 26px; padding-left: 5px; line-height: 24px; font-style: normal;\"></span>\r\n");
      out.write("                ");
      if (_jspx_meth_security_005fauthorize_005f0(_jspx_page_context))
        return;
      out.write(' ');
      if (_jspx_meth_security_005fauthorize_005f1(_jspx_page_context))
        return;
      out.write(' ');
      if (_jspx_meth_security_005fauthorize_005f2(_jspx_page_context))
        return;
      out.write(' ');
      if (_jspx_meth_security_005fauthorize_005f3(_jspx_page_context))
        return;
      out.write("</div>\r\n");
      out.write("        </form>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div id=\"body\" style=\"margin: 0px;padding-right:10px;\"></div>\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("var A_BIS_MAIN_NEW = false;\r\n");
      if (_jspx_meth_security_005fauthorize_005f4(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("var default_height = 100;\r\n");
      out.write("var _ctx = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("var affirmBut = true;\r\n");
      out.write("var statusTxt = \"已提交\";\r\n");
      out.write("var status = -1;\r\n");
      out.write("function setStatus(owner) {\r\n");
      out.write("    if (status === -1) {\r\n");
      out.write("        affirmBut = true;\r\n");
      out.write("        $(\"#status\").text(\"当前状态：待新增\");\r\n");
      out.write("        $(\"#addReportBut\").show();\r\n");
      out.write("        $(\"#affirmReportBut\").hide();\r\n");
      out.write("        $(\"#rejectReportBut\").hide();\r\n");
      out.write("        $(\"#submitReportBut\").hide();\r\n");
      out.write("    }\r\n");
      out.write("    if (status === 0) {\r\n");
      out.write("        affirmBut = true;\r\n");
      out.write("        $(\"#status\").text(\"当前状态：新增中\");\r\n");
      out.write("        $(\"#submitReportBut\").show();\r\n");
      out.write("        $(\"#addReportBut\").hide();\r\n");
      out.write("        $(\"#affirmReportBut\").hide();\r\n");
      out.write("        $(\"#rejectReportBut\").hide();\r\n");
      out.write("    }\r\n");
      out.write("    if (status === 1) {\r\n");
      out.write("        affirmBut = false;\r\n");
      out.write("        statusTxt = \"已提交\";\r\n");
      out.write("        $(\"#status\").text(\"当前状态：已提交\");\r\n");
      out.write("        $(\"#affirmReportBut\").show();\r\n");
      out.write("        $(\"#rejectReportBut\").show();\r\n");
      out.write("        $(\"#submitReportBut\").hide();\r\n");
      out.write("        $(\"#addReportBut\").hide();\r\n");
      out.write("    }\r\n");
      out.write("    if (status === 2) {\r\n");
      out.write("        affirmBut = false;\r\n");
      out.write("        statusTxt = \"已确认\";\r\n");
      out.write("        $(\"#status\").text(\"当前状态：已确认\");\r\n");
      out.write("        $(\"#addReportBut\").hide();\r\n");
      out.write("        $(\"#submitReportBut\").hide();\r\n");
      out.write("        $(\"#affirmReportBut\").hide();\r\n");
      out.write("        $(\"#rejectReportBut\").show();\r\n");
      out.write("        updateUpdator();\r\n");
      out.write("    }\r\n");
      out.write("    if (status === 3) {\r\n");
      out.write("        affirmBut = true;\r\n");
      out.write("        $(\"#status\").text(\"当前状态：被驳回\");\r\n");
      out.write("        $(\"#submitReportBut\").show();\r\n");
      out.write("        $(\"#addReportBut\").hide();\r\n");
      out.write("        $(\"#affirmReportBut\").hide();\r\n");
      out.write("        $(\"#rejectReportBut\").hide();\r\n");
      out.write("    }\r\n");
      out.write("    /**每次计算*/\r\n");
      out.write("    calcModiList();\r\n");
      out.write("}\r\n");
      out.write("$(document).ready(function () {\r\n");
      out.write("    $.ajaxSetup({cache:false});\r\n");
      out.write("    init();\r\n");
      out.write("    $(\"#queryFrom select[class=box date]\").change(function () {\r\n");
      out.write("        setDateText($(\"#year\").val(), $(\"#month\").val(), $(\"#weekOfMonth\").val(), true);\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("    $(\"#queryBut\").click(function () {\r\n");
      out.write("        loadBody();\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("    $(\"#addReportBut\").click(function () {\r\n");
      out.write("        setReportStatusFlgNew(\"新增时会将上双周数据作为本双周的初始数据，是否继续？\", \"生成周报\", \"add\");\r\n");
      out.write("\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("    $(\"#submitReportBut\").click(function () {\r\n");
      out.write("        setReportStatusFlg(\"您确定要提交本期周报吗？\", \"提交周报\", \"submit\");\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("    $(\"#affirmReportBut\").click(function () {\r\n");
      out.write("        setReportStatusFlg(\"您确定固化本期周报吗？<br/>固化后本期周报不能再修改！\", \"固化周报\", \"affirm\");\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("    $(\"#rejectReportBut\").click(function () {\r\n");
      out.write("        setReportStatusFlg(\"您确定要驳回本期周报吗？<br/>驳回后本期周报可以再修改！\", \"驳回周报\", \"reject\");\r\n");
      out.write("    });\r\n");
      out.write("});\r\n");
      out.write("/**填充背景颜色*/\r\n");
      out.write("function fillBackGroundColor() {\r\n");
      out.write("    $.each($(\".tred\"), function (i, j) {\r\n");
      out.write("        $(j).prev().addClass(\"divred\");\r\n");
      out.write("    })\r\n");
      out.write("    $.each($(\".tyellow\"), function (i, j) {\r\n");
      out.write("        $(j).prev().addClass(\"divyellow\");\r\n");
      out.write("    })\r\n");
      out.write("    $.each($(\".tgreen\"), function (i, j) {\r\n");
      out.write("        $(j).prev().addClass(\"divgreen\");\r\n");
      out.write("    }) \r\n");
      out.write("    $.each($(\".text\"), function (i, j) {\r\n");
      out.write("        $(j).prev().addClass(\"divtext\");\r\n");
      out.write("    })\r\n");
      out.write("}\r\n");
      out.write("function setReportStatusFlg(message1, message2, func) {\r\n");
      out.write("\r\n");
      out.write("    ymPrompt.confirmInfo({\r\n");
      out.write("        message:\"<p style='margin-top:-25px;display:block;line-height: 20px;'>\" + message1 + \"</p>\",\r\n");
      out.write("        width:350,\r\n");
      out.write("        height:160,\r\n");
      out.write("\t\twinPos:[($(\"body\").width()-350)/2,50],\r\n");
      out.write("        title:\"消息提示！\",\r\n");
      out.write("        closeBtn:true,\r\n");
      out.write("        handler:function (btn) {\r\n");
      out.write("            if (btn == 'ok') {\r\n");
      out.write("                TB_showMaskLayer(\"正在\" + message2 + \"请稍后...\");\r\n");
      out.write("                var saveParam = {\r\n");
      out.write("                    'startDay':$(\"#queryFrom input[name=startDay]\").val(),\r\n");
      out.write("                    'endDay':$(\"#queryFrom input[name=endDay]\").val()\r\n");
      out.write("                };\r\n");
      out.write("                $.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-rental-collection-rate!\" + func + \".action\", saveParam, function (result) {\r\n");
      out.write("\r\n");
      out.write("                    $(\"#body\").html(result);\r\n");
      out.write("                    TB_removeMaskLayer();\r\n");
      out.write("                    setStatus();\r\n");
      out.write("                });\r\n");
      out.write("            }\r\n");
      out.write("            ymPrompt.close();\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("}\r\n");
      out.write("/**新增周报专用*/\r\n");
      out.write("function setReportStatusFlgNew(message1, message2, func) {\r\n");
      out.write("    var cationMSG = \"本次周报：\" + $(\"#year\").val() + \"年\" + $(\"#month\").val() + \"月,第\" + $(\"#weekOfMonth\").val() + \"个双周\";\r\n");
      out.write("    ymPrompt.confirmInfo({\r\n");
      out.write("        message:\"<p style='margin-top:-25px;display:block;line-height: 20px;'>\" + message1 + \"</p><br/>\" + cationMSG,\r\n");
      out.write("        width:350,\r\n");
      out.write("        height:200,\r\n");
      out.write("\t\twinPos:[($(\"body\").width()-350)/2,50],\r\n");
      out.write("        title:\"消息提示！\",\r\n");
      out.write("        closeBtn:true,\r\n");
      out.write("        handler:function (btn) {\r\n");
      out.write("            if (btn == 'ok') {\r\n");
      out.write("                TB_showMaskLayer(\"正在\" + message2 + \"请稍后...\");\r\n");
      out.write("                var saveParam = {\r\n");
      out.write("                    'startDay':$(\"#queryFrom input[name=startDay]\").val(),\r\n");
      out.write("                    'endDay':$(\"#queryFrom input[name=endDay]\").val()\r\n");
      out.write("                };\r\n");
      out.write("                $.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-rental-collection-rate!\" + func + \".action\", saveParam, function (result) {\r\n");
      out.write("\r\n");
      out.write("                    $(\"#body\").html(result);\r\n");
      out.write("                    TB_removeMaskLayer();\r\n");
      out.write("                    setStatus();\r\n");
      out.write("                });\r\n");
      out.write("            }\r\n");
      out.write("            ymPrompt.close();\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("}\r\n");
      out.write("/**初始化相关信息*/\r\n");
      out.write("function init() {\r\n");
      out.write("\t");

	if(curDate>15){
		weekOfMonth = 2;
	}else{
		weekOfMonth = 1;
		if(month<1){
			month = 12+month;
			year = year -1;
		}
	}
	
      out.write("\r\n");
      out.write("    $(\"#year\").val(\"");
      out.print(year);
      out.write("\");\r\n");
      out.write("    $(\"#month\").val(\"");
      out.print(month);
      out.write("\");\r\n");
      out.write("    $(\"#weekOfMonth\").val(\"");
      out.print(weekOfMonth);
      out.write("\");\r\n");
      out.write("    setDateText(\"");
      out.print(year);
      out.write("\", \"");
      out.print(month);
      out.write("\", \"");
      out.print(weekOfMonth);
      out.write("\", true);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function loadBody() {\r\n");
      out.write("\r\n");
      out.write("    TB_showMaskLayer(\"正在搜索请稍后...\");\r\n");
      out.write("    var queryParam = {\r\n");
      out.write("        'startDay':$(\"#queryFrom input[name=startDay]\").val(),\r\n");
      out.write("        'endDay':$(\"#queryFrom input[name=endDay]\").val()\r\n");
      out.write("    };\r\n");
      out.write("    $.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-rental-collection-rate!list.action\", queryParam, function (result) {\r\n");
      out.write("        $(\"#body\").html(result);\r\n");
      out.write("        TB_removeMaskLayer();\r\n");
      out.write("        setStatus();\r\n");
      out.write("\r\n");
      out.write("        /**填充背景颜色*/\r\n");
      out.write("        fillBackGroundColor();\r\n");
      out.write("        calcModiList();\r\n");
      out.write("    });\r\n");
      out.write("}\r\n");
      out.write("/**更新审核人 单独方法*/\r\n");
      out.write("function updateUpdator() {\r\n");
      out.write("    var params = {\r\n");
      out.write("        'startDay':$(\"#queryFrom input[name=startDay]\").val(),\r\n");
      out.write("        'endDay':$(\"#queryFrom input[name=endDay]\").val()\r\n");
      out.write("    };\r\n");
      out.write("    $.get(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-rental-collection-rate!reGetUpdator.action\", params, function (data) {\r\n");
      out.write("        if (data == null || data == \"\" || data.length < 1) {\r\n");
      out.write("            return;\r\n");
      out.write("        } else {\r\n");
      out.write("            $(\"#status\").text($(\"#status\").text() + \"（\" + data + \"）\");\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("}\r\n");
      out.write("function isEdit() {\r\n");
      out.write("    return \"");
      out.print(year);
      out.write("\" === $(\"#year\").val() && \"");
      out.print(month);
      out.write("\" === $(\"#month\").val() && \"");
      out.print(weekOfMonth);
      out.write("\" == $(\"#weekOfMonth\").val();\r\n");
      out.write("}\r\n");
      out.write("/**显示时间*/\r\n");
      out.write("function setDateText(year, month, weekOfMonth, isload) {\r\n");
      out.write("    var queryParam = {\r\n");
      out.write("        'year':year,\r\n");
      out.write("        'month':month,\r\n");
      out.write("        'weekOfMonth':weekOfMonth\r\n");
      out.write("    };\r\n");
      out.write("    $.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-rental-collection-rate!getStartEndDay.action\", queryParam, function (result) {\r\n");
      out.write("\r\n");
      out.write("        if (result) {\r\n");
      out.write("            var dates = result.split(\"~\");\r\n");
      out.write("            if (dates.lenght = 2) {\r\n");
      out.write("                var html = '<font color=\"red\">' + dates[0] + '</font>&nbsp;&nbsp;至&nbsp;&nbsp;<font color=\"red\">' + dates[1] + '</font>';\r\n");
      out.write("                $(\"#queryFrom input[name=startDay]\").val(dates[0]);\r\n");
      out.write("                $(\"#queryFrom input[name=endDay]\").val(dates[1]);\r\n");
      out.write("                $(\"#dataText\").html(html);\r\n");
      out.write("                if (isload) {\r\n");
      out.write("                    loadBody();\r\n");
      out.write("                }\r\n");
      out.write("            }\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("    });\r\n");
      out.write("}\r\n");
      out.write("/**验证文本长度50，并且只能输入数字和小数点**/\r\n");
      out.write("function valiKeyUp(demo) {\r\n");
      out.write("    if (!demo.value.match(/^(?:[\\+\\-]?\\d+(?:\\.\\d+)?|\\.\\d*?)?$/)) {\r\n");
      out.write("        demo.value = demo.o_value;\r\n");
      out.write("    } else {\r\n");
      out.write("        if (demo.value.match(/^\\.\\d+$/))\r\n");
      out.write("            demo.value = 0 + demo.value;\r\n");
      out.write("        if (demo.value.match(/^\\.$/))\r\n");
      out.write("            demo.value = 0;\r\n");
      out.write("        demo.o_value = demo.value;\r\n");
      out.write("    }\r\n");
      out.write("    ;\r\n");
      out.write("    $(demo).val($(demo).val().substr(0, 50));\r\n");
      out.write("}\r\n");
      out.write("/**保留2位小数*/\r\n");
      out.write("function changeTwoDecimal_f(x) {\r\n");
      out.write("    var f_x = parseFloat(x);\r\n");
      out.write("    if (isNaN(f_x)) {\r\n");
      out.write("        return 0;\r\n");
      out.write("    }\r\n");
      out.write("    var f_x = Math.round(x * 100) / 100;\r\n");
      out.write("    var s_x = f_x.toString();\r\n");
      out.write("    var pos_decimal = s_x.indexOf('.');\r\n");
      out.write("    if (pos_decimal < 0) {\r\n");
      out.write("        pos_decimal = s_x.length;\r\n");
      out.write("        s_x += '.';\r\n");
      out.write("    }\r\n");
      out.write("    while (s_x.length <= pos_decimal + 2) {\r\n");
      out.write("        s_x += '0';\r\n");
      out.write("    }\r\n");
      out.write("    return s_x;\r\n");
      out.write("}\r\n");
      out.write("//项目切换\r\n");
      out.write("var qiehuans = $(\"button.qiehuan\").click(function () {\r\n");
      out.write("    var index = qiehuans.index(this);\r\n");
      out.write("    var tab = parent.TabUtils;\r\n");
      out.write("    if (index == 2) {\r\n");
      out.write("        var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-manage.action?report=false\";\r\n");
      out.write("        if (tab == null) {\r\n");
      out.write("            window.open(url);\r\n");
      out.write("        } else {\r\n");
      out.write("            if ($(\"#195_tab\", parent.document).size() != 0) {\r\n");
      out.write("                tab.closeTab({data:{tabId:\"195\"}});\r\n");
      out.write("            }\r\n");
      out.write("            tab.newTab(\"195\", \"项目列表\", url, true);\r\n");
      out.write("        }\r\n");
      out.write("    } else if (index == 1) {\r\n");
      out.write("        var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-manage.action\";\r\n");
      out.write("        if (tab == null)\r\n");
      out.write("            window.open(url);\r\n");
      out.write("        else {\r\n");
      out.write("            if ($(\"#195_tab\", parent.document).size() != 0) {\r\n");
      out.write("                tab.closeTab({data:{tabId:\"195\"}});\r\n");
      out.write("            }\r\n");
      out.write("            tab.newTab(\"195\", \"经营报表\", url, true);\r\n");
      out.write("        }\r\n");
      out.write("    } else {\r\n");
      out.write("        var url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-main-shop-report.action\";\r\n");
      out.write("        if (tab == null)\r\n");
      out.write("            window.open(url);\r\n");
      out.write("        else {\r\n");
      out.write("            if ($(\"#195_tab\", parent.document).size() != 0) {\r\n");
      out.write("                tab.closeTab({data:{tabId:\"195\"}});\r\n");
      out.write("            }\r\n");
      out.write("            tab.newTab(\"195\", \"主力店招商周报\", url, true);\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("    }\r\n");
      out.write("});\r\n");
      out.write("/**计算值*/\r\n");
      out.write("function calcModiList() {\r\n");
      out.write("    var sums = [0, 0, 0, 0, 0, 0, 0];\r\n");
      out.write("    var trs = $(\".editReport\").each(function () {\r\n");
      out.write("        var tds = $(\"td\", this).each(function (index) {\r\n");
      out.write("            if ($(this).text() !== \"\" && $(this).text() !== \"/\" && $(this).text() !== \"0\" && $(this).text() !== \"%\") {\r\n");
      out.write("                //sums[index]=sums[index]+filterNaN($(this).attr(\"sum\").trim());\r\n");
      out.write("                sums[index] = sums[index] + filterNaN($(this).text().trim());\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("    });\r\n");
      out.write("    $(\"#body .report tr:last td:eq(0)\").text(changeTwoDecimal_f(sums[1]));\r\n");
      out.write("    $(\"#body .report tr:last td:eq(1)\").text(changeTwoDecimal_f(getColRateRentForCalc(1) * 100 / (sums[1]==0?1:sums[1])) + \"%\");\r\n");
      out.write("    $(\"#body .report tr:last td:eq(2)\").text(changeTwoDecimal_f(sums[3]));\r\n");
      out.write("    $(\"#body .report tr:last td:eq(3)\").text(changeTwoDecimal_f(getColRateRentForCalc(2) * 100 / (sums[3]==0?1:sums[3])) + \"%\");\r\n");
      out.write("    setGoodBadPoint();\r\n");
      out.write("    fillBackGroundColor();\r\n");
      out.write("}\r\n");
      out.write("/**累计实收额*/\r\n");
      out.write("function getColRateRentForCalc(demo) {\r\n");
      out.write("    var allColRateRentForCalc = 0;\r\n");
      out.write("    if (demo == 1) {//租金累计实收额\r\n");
      out.write("        $.each($(\".ColRateRentForCalc1\"), function (i, j) {\r\n");
      out.write("            if ($(j).val() != \"\") {\r\n");
      out.write("                allColRateRentForCalc += parseFloat($(j).val());\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("    } else {    //物管累计实收额\r\n");
      out.write("        $.each($(\".ColRateRentForCalc2\"), function (i, j) {\r\n");
      out.write("            if ($(j).val() != \"\") {\r\n");
      out.write("                allColRateRentForCalc += parseFloat($(j).val());\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("    }\r\n");
      out.write("    return allColRateRentForCalc;\r\n");
      out.write("}\r\n");
      out.write("/**获取最好和最差的收缴率并赋予相应样式*/\r\n");
      out.write("function setGoodBadPoint() {\r\n");
      out.write("    var arrayObj1 = new Array();\r\n");
      out.write("    var arrayObj2 = new Array();\r\n");
      out.write("    $.each($(\".CollPointRent\"), function (i, j) {\r\n");
      out.write("        if ($(j).val() != \"\" && $(j).val() != \"/\") {\r\n");
      out.write("            arrayObj1[arrayObj1.length] = parseFloat($(j).val());\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("    $.each($(\".CollPointProperty\"), function (i, j) {\r\n");
      out.write("        if ($(j).val() != \"\" && $(j).val() != \"/\") {\r\n");
      out.write("            arrayObj2[arrayObj2.length] = parseFloat($(j).val());\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("    var t1 = Math.max.apply(Math, arrayObj1);\r\n");
      out.write("    var t2 = Math.min.apply(Math, arrayObj1);\r\n");
      out.write("    var t3 = Math.max.apply(Math, arrayObj2);\r\n");
      out.write("    var t4 = Math.min.apply(Math, arrayObj2);\r\n");
      out.write("    $(\"input:hidden[class='CollPointRent'][value=\" + changeTwoDecimal_f(t1) + \"]\").parent(\"td\").css({\"background-image\":\"url(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/bis/good.png)\", \"background-repeat\":\"no-repeat\", \"background-position\":\"center\"});\r\n");
      out.write("    $(\"input:hidden[class='CollPointRent'][value=\" + changeTwoDecimal_f(t2) + \"]\").parent(\"td\").css({\"background-image\":\"url(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/bis/bad.png)\", \"background-repeat\":\"no-repeat\", \"background-position\":\"center\"});\r\n");
      out.write("    $(\"input:hidden[class='CollPointProperty'][value=\" + changeTwoDecimal_f(t3) + \"]\").parent(\"td\").css({\"background-image\":\"url(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/bis/good.png)\", \"background-repeat\":\"no-repeat\", \"background-position\":\"center\"});\r\n");
      out.write("    $(\"input:hidden[class='CollPointProperty'][value=\" + changeTwoDecimal_f(t4) + \"]\").parent(\"td\").css({\"background-image\":\"url(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/bis/bad.png)\", \"background-repeat\":\"no-repeat\", \"background-position\":\"center\"});\r\n");
      out.write("}\r\n");
      out.write("/**处理空字符特殊情况*/\r\n");
      out.write("function filterNaN(demo, type) {\r\n");
      out.write("    if (type == 1) {\r\n");
      out.write("        if (isNaN(demo)) {\r\n");
      out.write("            return \"\";\r\n");
      out.write("        } else {\r\n");
      out.write("            return demo;\r\n");
      out.write("        }\r\n");
      out.write("    }\r\n");
      out.write("    if (demo == null || demo == undefined || demo == null || demo == \"\" || isNaN(demo)) {\r\n");
      out.write("        return 0;\r\n");
      out.write("    } else {\r\n");
      out.write("        return parseFloat(demo);\r\n");
      out.write("    }\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/**租费收缴率按钮单击*/\r\n");
      out.write("$(\"button.zufei\").bind(\"click\", function () {\r\n");
      out.write("    return;\r\n");
      out.write("});\r\n");
      out.write("function resize() {\r\n");
      out.write("    var container = $.browser.msie ? $(\"html\") : $(window);\r\n");
      out.write("    $(\"#body\").height(container.height() - 100);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//高度设置\r\n");
      out.write("//setInterval(resize, 1000);\r\n");
      out.write("//resize();\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
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

  private boolean _jspx_meth_c_005fforEach_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fstep_005fend_005fbegin.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f1.setParent(null);
    // /WEB-INF/content/bis/bis-rental-collection-rate-main.jsp(81,12) name = begin type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setBegin(1);
    // /WEB-INF/content/bis/bis-rental-collection-rate-main.jsp(81,12) name = end type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setEnd(12);
    // /WEB-INF/content/bis/bis-rental-collection-rate-main.jsp(81,12) name = step type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setStep(1);
    // /WEB-INF/content/bis/bis-rental-collection-rate-main.jsp(81,12) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setVar("b");
    int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
      if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("                <option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${b}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write('"');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${b}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</option>\r\n");
          out.write("            ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f1.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fstep_005fend_005fbegin.reuse(_jspx_th_c_005fforEach_005f1);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f2 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fstep_005fend_005fbegin.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f2.setParent(null);
    // /WEB-INF/content/bis/bis-rental-collection-rate-main.jsp(86,12) name = begin type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setBegin(1);
    // /WEB-INF/content/bis/bis-rental-collection-rate-main.jsp(86,12) name = end type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setEnd(2);
    // /WEB-INF/content/bis/bis-rental-collection-rate-main.jsp(86,12) name = step type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setStep(1);
    // /WEB-INF/content/bis/bis-rental-collection-rate-main.jsp(86,12) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setVar("b");
    int[] _jspx_push_body_count_c_005fforEach_005f2 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f2 = _jspx_th_c_005fforEach_005f2.doStartTag();
      if (_jspx_eval_c_005fforEach_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("                <option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${b}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write('"');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${b}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</option>\r\n");
          out.write("            ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f2.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f2[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f2.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f2.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fstep_005fend_005fbegin.reuse(_jspx_th_c_005fforEach_005f2);
    }
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
    // /WEB-INF/content/bis/bis-rental-collection-rate-main.jsp(99,16) name = ifAllGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f0.setIfAllGranted("A_RENTAL_C_RATE_REF");
    int _jspx_eval_security_005fauthorize_005f0 = _jspx_th_security_005fauthorize_005f0.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                    <button id=\"rejectReportBut\" class=\"red\"\r\n");
        out.write("                            style=\"width: 76px; display: none; float: right; margin-right: 5px;\"\r\n");
        out.write("                            type=\"button\">驳回周报\r\n");
        out.write("                    </button>\r\n");
        out.write("                ");
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

  private boolean _jspx_meth_security_005fauthorize_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f1 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f1.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f1.setParent(null);
    // /WEB-INF/content/bis/bis-rental-collection-rate-main.jsp(104,38) name = ifAllGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f1.setIfAllGranted("A_RENTAL_C_RATE_CFM");
    int _jspx_eval_security_005fauthorize_005f1 = _jspx_th_security_005fauthorize_005f1.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                <button id=\"affirmReportBut\" class=\"green\"\r\n");
        out.write("                        style=\"width: 76px; display: none; float: right; margin-right: 5px;\"\r\n");
        out.write("                        type=\"button\">确认周报\r\n");
        out.write("                </button>\r\n");
        out.write("            ");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted.reuse(_jspx_th_security_005fauthorize_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted.reuse(_jspx_th_security_005fauthorize_005f1);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f2 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f2.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f2.setParent(null);
    // /WEB-INF/content/bis/bis-rental-collection-rate-main.jsp(109,34) name = ifAllGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f2.setIfAllGranted("A_RENTAL_C_RATE_SUB");
    int _jspx_eval_security_005fauthorize_005f2 = _jspx_th_security_005fauthorize_005f2.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                <button id=\"submitReportBut\" class=\"blue\"\r\n");
        out.write("                        style=\"width: 76px; display: none; float: right; margin-right: 5px;\"\r\n");
        out.write("                        type=\"button\">提交周报\r\n");
        out.write("                </button>\r\n");
        out.write("            ");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted.reuse(_jspx_th_security_005fauthorize_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted.reuse(_jspx_th_security_005fauthorize_005f2);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f3 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f3.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f3.setParent(null);
    // /WEB-INF/content/bis/bis-rental-collection-rate-main.jsp(114,34) name = ifAllGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f3.setIfAllGranted("A_RENTAL_C_RATE_NEW");
    int _jspx_eval_security_005fauthorize_005f3 = _jspx_th_security_005fauthorize_005f3.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                <button id=\"addReportBut\" class=\"blue\"\r\n");
        out.write("                        style=\"width: 76px; display: none; float: right; margin-right: 5px;\"\r\n");
        out.write("                        type=\"button\">新增周报\r\n");
        out.write("                </button>\r\n");
        out.write("            ");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted.reuse(_jspx_th_security_005fauthorize_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted.reuse(_jspx_th_security_005fauthorize_005f3);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f4 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f4.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f4.setParent(null);
    // /WEB-INF/content/bis/bis-rental-collection-rate-main.jsp(127,0) name = ifAllGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f4.setIfAllGranted("A_BIS_MAIN_NEW");
    int _jspx_eval_security_005fauthorize_005f4 = _jspx_th_security_005fauthorize_005f4.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("A_BIS_MAIN_NEW = true;\r\n");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted.reuse(_jspx_th_security_005fauthorize_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAllGranted.reuse(_jspx_th_security_005fauthorize_005f4);
    return false;
  }
}
