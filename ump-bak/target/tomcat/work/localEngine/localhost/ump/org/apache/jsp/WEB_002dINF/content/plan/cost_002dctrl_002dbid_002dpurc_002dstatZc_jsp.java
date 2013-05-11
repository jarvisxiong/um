package org.apache.jsp.WEB_002dINF.content.plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Date;
import com.hhz.ump.util.JspUtil;
import com.hhz.ump.util.CodeNameUtil;

public final class cost_002dctrl_002dbid_002dpurc_002dstatZc_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(3);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/common/nocache.jsp");
    _jspx_dependants.add("/WEB-INF/PowerDesk-tags.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005felse;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005felse = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.release();
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.release();
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
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
      out.write('\r');
      out.write('\n');

	response.setHeader("Expires","0");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragrma", "no-cache");
	response.setDateHeader("Expires", 0);

      out.write("\r\n");
      out.write(" \r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("<!--\r\n");
      out.write("\t.stat_table{\r\n");
      out.write("\t\tcolor:#464646;\r\n");
      out.write("\t\ttable-layout:fixed;\r\n");
      out.write("\t\twidth:100%;\r\n");
      out.write("\t}\r\n");
      out.write("\t.stat_table th{\r\n");
      out.write("\t\tborder:1px solid #aaabb0;\r\n");
      out.write("\t\tbackground-color: #E4E7EC;\r\n");
      out.write("\t\tline-height:20px;\r\n");
      out.write("\t}\r\n");
      out.write("\t.stat_table td{\r\n");
      out.write("\t\ttext-align:right;\r\n");
      out.write("\t\tborder:1px solid #aaabb0;\r\n");
      out.write("\t\tpadding-right:5px;\r\n");
      out.write("\t\tline-height:26px;\r\n");
      out.write("\t}\r\n");
      out.write("\t.stat_table .total td{\r\n");
      out.write("\t\tfont-weight:bold;\r\n");
      out.write("\t}\r\n");
      out.write("\t.stat_title{\r\n");
      out.write("\t\ttext-align:center;\r\n");
      out.write("\t\tfont-size:16px;\r\n");
      out.write("\t\tfont-weight:bold;\r\n");
      out.write("\t\t/*margin:0 100px;*/\r\n");
      out.write("\t\tcursor: pointer;\r\n");
      out.write("\t}\r\n");
      out.write("\t.stat_bar{\r\n");
      out.write("\t\twidth:100px;\r\n");
      out.write("\t\tfont-size:12px;\r\n");
      out.write("\t\ttext-decoration: underline;\r\n");
      out.write("\t\tcursor: pointer;\r\n");
      out.write("\t}\r\n");
      out.write("\t.change_month{\r\n");
      out.write("\t\tfont-size:12px;\r\n");
      out.write("\t\tfont-weight:normal;\r\n");
      out.write("\t\tcolor:#0693e3;\r\n");
      out.write("\t\ttext-decoration: underline;\r\n");
      out.write("\t\tcursor: pointer;\r\n");
      out.write("\t}\r\n");
      out.write("-->\r\n");
      out.write("</style>\r\n");
      out.write(" \r\n");
      out.write("<!-- 招采统计页面 -->\r\n");
      out.write("<div style=\"margin:50px 20px;\">\r\n");
      out.write("<!-- 第一个 -->\r\n");
      out.write("<table class=\"stat_table\" id=\"statTable0\">\r\n");
      out.write("\t<col width=\"100px\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<thead>\r\n");
      out.write("\t\t<tr id=\"statTitle0\">\r\n");
      out.write("\t\t\t<th style=\"line-height:30px;\" colspan=\"10\">\r\n");
      out.write("\t\t\t\t<div style=\"float:left;\" class=\"stat_bar\" onclick=\"changeMonth('");
      if (_jspx_meth_s_005fdate_005f0(_jspx_page_context))
        return;
      out.write("')\" title=\"");
      if (_jspx_meth_s_005fdate_005f1(_jspx_page_context))
        return;
      out.write("\">上个月</div>\r\n");
      out.write("\t\t\t\t<div style=\"float:right;\" class=\"stat_bar\" onclick=\"changeMonth('");
      if (_jspx_meth_s_005fdate_005f2(_jspx_page_context))
        return;
      out.write("')\" title=\"");
      if (_jspx_meth_s_005fdate_005f3(_jspx_page_context))
        return;
      out.write("\">下个月</div>\r\n");
      out.write("\t\t\t\t<div class=\"stat_title\">\r\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_s_005fdate_005f4(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t招采报表统计\r\n");
      out.write("\t\t\t\t\t<span class=\"change_month\" onclick=\"WdatePicker({dateFmt:'yyyy-MM',onpicked:function(dp){changeStatDate(dp);}})\">切换月份</span>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</th>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">部门</th>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">负责人</th>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">未完成</th>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">按期完成</th>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">逾期完成</th>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">总数</th>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">未完成率</th>\r\n");
      out.write("\t\t\t<th colspan=\"3\">完成率</th>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th >按期完成率</th>\r\n");
      out.write("\t\t\t<th >逾期完成率</th>\r\n");
      out.write("\t\t\t<th >总完成率</th>\r\n");
      out.write("\t\t</tr> \r\n");
      out.write("\t</thead>\r\n");
      out.write("\t<tbody>\r\n");
      out.write("\t\t");
      //  s:iterator
      org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_005fiterator_005f0 = (org.apache.struts2.views.jsp.IteratorTag) _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.get(org.apache.struts2.views.jsp.IteratorTag.class);
      _jspx_th_s_005fiterator_005f0.setPageContext(_jspx_page_context);
      _jspx_th_s_005fiterator_005f0.setParent(null);
      // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(95,2) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_s_005fiterator_005f0.setValue("costStatisticsArea0");
      int _jspx_eval_s_005fiterator_005f0 = _jspx_th_s_005fiterator_005f0.doStartTag();
      if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_s_005fiterator_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_s_005fiterator_005f0.doInitBody();
        }
        do {
          out.write("   \r\n");
          out.write("\t\t\t<tr class=\"data\" relArea=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${qy}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\">\t\r\n");
          out.write("\t\t\t\t");
          //  s:if
          org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f0 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
          _jspx_th_s_005fif_005f0.setPageContext(_jspx_page_context);
          _jspx_th_s_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f0);
          // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(97,4) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_s_005fif_005f0.setTest("qy == 'zb' || qy == 'cg' || qy == 'zhb' ");
          int _jspx_eval_s_005fif_005f0 = _jspx_th_s_005fif_005f0.doStartTag();
          if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_s_005fif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_s_005fif_005f0.doInitBody();
            }
            do {
              out.write("\r\n");
              out.write("\t\t\t\t\t<td style=\"text-align: center;\">\r\n");
              out.write("\t\t\t\t\t\t");
              if (_jspx_meth_s_005fif_005f1(_jspx_th_s_005fif_005f0, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t\t\t\t\t\t");
              if (_jspx_meth_s_005felseif_005f0(_jspx_th_s_005fif_005f0, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t\t\t\t\t\t");
              if (_jspx_meth_s_005felseif_005f1(_jspx_th_s_005fif_005f0, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t\t\t\t\t\t");
              if (_jspx_meth_s_005felse_005f0(_jspx_th_s_005fif_005f0, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t\t\t\t\t</td>\r\n");
              out.write("\t\t\t\t\t<td style=\"font-weight:bold;text-align: center;\" title=\"");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${uiid}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
              out.write('"');
              out.write('>');
              out.print(CodeNameUtil.getUserNameByCd(JspUtil.findString("uiid")) );
              out.write("</td>\r\n");
              out.write("\t\t\t\t");
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
            return;
          }
          _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f0);
          out.write("\r\n");
          out.write("\t\t\t\t");
          //  s:else
          org.apache.struts2.views.jsp.ElseTag _jspx_th_s_005felse_005f1 = (org.apache.struts2.views.jsp.ElseTag) _005fjspx_005ftagPool_005fs_005felse.get(org.apache.struts2.views.jsp.ElseTag.class);
          _jspx_th_s_005felse_005f1.setPageContext(_jspx_page_context);
          _jspx_th_s_005felse_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f0);
          int _jspx_eval_s_005felse_005f1 = _jspx_th_s_005felse_005f1.doStartTag();
          if (_jspx_eval_s_005felse_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_s_005felse_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_s_005felse_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_s_005felse_005f1.doInitBody();
            }
            do {
              out.write("\r\n");
              out.write("\t\t\t\t\t<td style=\"font-weight:bold;text-align: center;\" title=\"");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${uiid}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
              out.write("\" colspan=\"2\">");
              out.print(CodeNameUtil.getUserNameByCd(JspUtil.findString("uiid")) );
              out.write("</td>\r\n");
              out.write("\t\t\t\t");
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
            return;
          }
          _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f1);
          out.write("\r\n");
          out.write("\t\t\t\t\r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wwc}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${aqwc}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${yqwc}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td> \r\n");
          out.write("\t\t\t\t\r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${total}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td> \r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wwcRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("%</td> \r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${aqwcRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("%</td> \r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${yqwcRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("%</td> \r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${zwcRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("%</td> \r\n");
          out.write("\t\t\t</tr>\t\r\n");
          out.write("\t\t");
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
        return;
      }
      _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.reuse(_jspx_th_s_005fiterator_005f0);
      out.write(" \r\n");
      out.write("\t\t");
      if (_jspx_meth_s_005fif_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t</tbody>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("<br/>\r\n");
      out.write("<br/>\r\n");
      out.write("<!-- 第二个 -->\r\n");
      out.write("<table class=\"stat_table\" id=\"statTable1\">\r\n");
      out.write("\t<col width=\"100px\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<thead>\r\n");
      out.write("\t\t<tr id=\"statTitle1\">\r\n");
      out.write("\t\t\t<th style=\"line-height:30px;\" colspan=\"10\">\r\n");
      out.write("\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"stat_title\">\r\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_s_005fdate_005f5(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t招采报表统计\r\n");
      out.write("\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</th>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">部门</th>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">负责人</th>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">未完成</th>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">按期完成</th>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">逾期完成</th>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">总数</th>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">未完成率</th>\r\n");
      out.write("\t\t\t<th colspan=\"3\">完成率</th>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th >按期完成率</th>\r\n");
      out.write("\t\t\t<th >逾期完成率</th>\r\n");
      out.write("\t\t\t<th >总完成率</th>\r\n");
      out.write("\t\t</tr> \r\n");
      out.write("\t</thead>\r\n");
      out.write("\t<tbody>\r\n");
      out.write("\t\t");
      //  s:iterator
      org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_005fiterator_005f1 = (org.apache.struts2.views.jsp.IteratorTag) _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.get(org.apache.struts2.views.jsp.IteratorTag.class);
      _jspx_th_s_005fiterator_005f1.setPageContext(_jspx_page_context);
      _jspx_th_s_005fiterator_005f1.setParent(null);
      // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(176,2) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_s_005fiterator_005f1.setValue("costStatisticsArea");
      int _jspx_eval_s_005fiterator_005f1 = _jspx_th_s_005fiterator_005f1.doStartTag();
      if (_jspx_eval_s_005fiterator_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_s_005fiterator_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_s_005fiterator_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_s_005fiterator_005f1.doInitBody();
        }
        do {
          out.write("   \r\n");
          out.write("\t\t\t<tr class=\"data\" relArea=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${qy}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\">\t\r\n");
          out.write("\t\t\t\t");
          //  s:if
          org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f3 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
          _jspx_th_s_005fif_005f3.setPageContext(_jspx_page_context);
          _jspx_th_s_005fif_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f1);
          // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(178,4) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_s_005fif_005f3.setTest("qy == 'zb' || qy == 'cg' || qy == 'zhb' ");
          int _jspx_eval_s_005fif_005f3 = _jspx_th_s_005fif_005f3.doStartTag();
          if (_jspx_eval_s_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_s_005fif_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_s_005fif_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_s_005fif_005f3.doInitBody();
            }
            do {
              out.write("\r\n");
              out.write("\t\t\t\t\t<td style=\"text-align: center;\">\r\n");
              out.write("\t\t\t\t\t\t");
              if (_jspx_meth_s_005fif_005f4(_jspx_th_s_005fif_005f3, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t\t\t\t\t\t");
              if (_jspx_meth_s_005felseif_005f2(_jspx_th_s_005fif_005f3, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t\t\t\t\t\t");
              if (_jspx_meth_s_005felseif_005f3(_jspx_th_s_005fif_005f3, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t\t\t\t\t\t");
              if (_jspx_meth_s_005felse_005f2(_jspx_th_s_005fif_005f3, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t\t\t\t\t</td>\r\n");
              out.write("\t\t\t\t\t<td style=\"font-weight:bold;text-align: center;\" title=\"");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${uiid}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
              out.write('"');
              out.write('>');
              out.print(CodeNameUtil.getUserNameByCd(JspUtil.findString("uiid")) );
              out.write("</td>\r\n");
              out.write("\t\t\t\t");
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
            return;
          }
          _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f3);
          out.write("\r\n");
          out.write("\t\t\t\t");
          //  s:else
          org.apache.struts2.views.jsp.ElseTag _jspx_th_s_005felse_005f3 = (org.apache.struts2.views.jsp.ElseTag) _005fjspx_005ftagPool_005fs_005felse.get(org.apache.struts2.views.jsp.ElseTag.class);
          _jspx_th_s_005felse_005f3.setPageContext(_jspx_page_context);
          _jspx_th_s_005felse_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f1);
          int _jspx_eval_s_005felse_005f3 = _jspx_th_s_005felse_005f3.doStartTag();
          if (_jspx_eval_s_005felse_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_s_005felse_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_s_005felse_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_s_005felse_005f3.doInitBody();
            }
            do {
              out.write("\r\n");
              out.write("\t\t\t\t\t<td style=\"font-weight:bold;text-align: center;\" title=\"");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${uiid}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
              out.write("\" colspan=\"2\">");
              out.print(CodeNameUtil.getUserNameByCd(JspUtil.findString("uiid")) );
              out.write("</td>\r\n");
              out.write("\t\t\t\t");
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
            return;
          }
          _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f3);
          out.write("\r\n");
          out.write("\t\t\t\t\r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wwc}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${aqwc}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${yqwc}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td> \r\n");
          out.write("\t\t\t\t\r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${total}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td> \r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wwcRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("%</td> \r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${aqwcRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("%</td> \r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${yqwcRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("%</td> \r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${zwcRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("%</td> \r\n");
          out.write("\t\t\t</tr>\t\r\n");
          out.write("\t\t");
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
        return;
      }
      _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.reuse(_jspx_th_s_005fiterator_005f1);
      out.write(" \r\n");
      out.write("\t\t");
      if (_jspx_meth_s_005fif_005f5(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t</tbody>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("<br/>\r\n");
      out.write("<br/>\r\n");
      out.write("\r\n");
      out.write("<!-- 第三个 -->\r\n");
      out.write("<table class=\"stat_table\" id=\"statTable2\">\r\n");
      out.write("\t<col width=\"100px\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<col width=\"10%\"/>\r\n");
      out.write("\t<thead>\r\n");
      out.write("\t\t<tr id=\"statTitle2\">\r\n");
      out.write("\t\t\t<th style=\"line-height:30px;\" colspan=\"10\">\r\n");
      out.write("\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"stat_title\">\r\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_s_005fdate_005f6(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t招采报表统计\r\n");
      out.write("\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</th>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">部门</th>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">负责人</th>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">未完成</th>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">按期完成</th>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">逾期完成</th>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">总数</th>\r\n");
      out.write("\t\t\t<th rowspan=\"2\">未完成率</th>\r\n");
      out.write("\t\t\t<th colspan=\"3\">完成率</th>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th >按期完成率</th>\r\n");
      out.write("\t\t\t<th >逾期完成率</th>\r\n");
      out.write("\t\t\t<th >总完成率</th>\r\n");
      out.write("\t\t</tr> \r\n");
      out.write("\t</thead>\r\n");
      out.write("\t<tbody>\r\n");
      out.write("\t\t");
      //  s:iterator
      org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_005fiterator_005f2 = (org.apache.struts2.views.jsp.IteratorTag) _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.get(org.apache.struts2.views.jsp.IteratorTag.class);
      _jspx_th_s_005fiterator_005f2.setPageContext(_jspx_page_context);
      _jspx_th_s_005fiterator_005f2.setParent(null);
      // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(258,2) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_s_005fiterator_005f2.setValue("costStatisticsArea2");
      int _jspx_eval_s_005fiterator_005f2 = _jspx_th_s_005fiterator_005f2.doStartTag();
      if (_jspx_eval_s_005fiterator_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_s_005fiterator_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_s_005fiterator_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_s_005fiterator_005f2.doInitBody();
        }
        do {
          out.write("   \r\n");
          out.write("\t\t\t<tr class=\"data\" relArea=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${qy}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\">\t\r\n");
          out.write("\t\t\t\t");
          //  s:if
          org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f6 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
          _jspx_th_s_005fif_005f6.setPageContext(_jspx_page_context);
          _jspx_th_s_005fif_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f2);
          // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(260,4) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_s_005fif_005f6.setTest("qy == 'zb' || qy == 'cg' || qy == 'zhb' ");
          int _jspx_eval_s_005fif_005f6 = _jspx_th_s_005fif_005f6.doStartTag();
          if (_jspx_eval_s_005fif_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_s_005fif_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_s_005fif_005f6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_s_005fif_005f6.doInitBody();
            }
            do {
              out.write("\r\n");
              out.write("\t\t\t\t\t<td style=\"text-align: center;\">\r\n");
              out.write("\t\t\t\t\t\t");
              if (_jspx_meth_s_005fif_005f7(_jspx_th_s_005fif_005f6, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t\t\t\t\t\t");
              if (_jspx_meth_s_005felseif_005f4(_jspx_th_s_005fif_005f6, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t\t\t\t\t\t");
              if (_jspx_meth_s_005felseif_005f5(_jspx_th_s_005fif_005f6, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t\t\t\t\t\t");
              if (_jspx_meth_s_005felse_005f4(_jspx_th_s_005fif_005f6, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t\t\t\t\t</td>\r\n");
              out.write("\t\t\t\t\t<td style=\"font-weight:bold;text-align: center;\" title=\"");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${uiid}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
              out.write('"');
              out.write('>');
              out.print(CodeNameUtil.getUserNameByCd(JspUtil.findString("uiid")) );
              out.write("</td>\r\n");
              out.write("\t\t\t\t");
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
            return;
          }
          _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f6);
          out.write("\r\n");
          out.write("\t\t\t\t");
          //  s:else
          org.apache.struts2.views.jsp.ElseTag _jspx_th_s_005felse_005f5 = (org.apache.struts2.views.jsp.ElseTag) _005fjspx_005ftagPool_005fs_005felse.get(org.apache.struts2.views.jsp.ElseTag.class);
          _jspx_th_s_005felse_005f5.setPageContext(_jspx_page_context);
          _jspx_th_s_005felse_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f2);
          int _jspx_eval_s_005felse_005f5 = _jspx_th_s_005felse_005f5.doStartTag();
          if (_jspx_eval_s_005felse_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_s_005felse_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_s_005felse_005f5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_s_005felse_005f5.doInitBody();
            }
            do {
              out.write("\r\n");
              out.write("\t\t\t\t\t<td style=\"font-weight:bold;text-align: center;\" title=\"");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${uiid}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
              out.write("\" colspan=\"2\">");
              out.print(CodeNameUtil.getUserNameByCd(JspUtil.findString("uiid")) );
              out.write("</td>\r\n");
              out.write("\t\t\t\t");
              int evalDoAfterBody = _jspx_th_s_005felse_005f5.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_s_005felse_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.popBody();
            }
          }
          if (_jspx_th_s_005felse_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f5);
            return;
          }
          _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f5);
          out.write("\r\n");
          out.write("\t\t\t\t\r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wwc}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${aqwc}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${yqwc}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td> \r\n");
          out.write("\t\t\t\t\r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${total}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td> \r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wwcRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("%</td> \r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${aqwcRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("%</td> \r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${yqwcRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("%</td> \r\n");
          out.write("\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${zwcRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("%</td> \r\n");
          out.write("\t\t\t</tr>\t\r\n");
          out.write("\t\t");
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
        return;
      }
      _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.reuse(_jspx_th_s_005fiterator_005f2);
      out.write(" \r\n");
      out.write("\t\t");
      if (_jspx_meth_s_005fif_005f8(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t</tbody>\r\n");
      out.write("</table>\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t//第一月\r\n");
      out.write("\t\t$.each( ['zb','cg','zhb'], function(i, n){\r\n");
      out.write("\t\t\tvar size = $('#statTable0 tr[relArea=\"'+n+'\"]').length;\r\n");
      out.write("\t\t\t$('#statTable0 tr[relArea=\"'+n+'\"]').each(function(i,n){\r\n");
      out.write("\t\t\t\tif(i == 0){\r\n");
      out.write("\t\t\t\t\t$(this).find(\"td:eq(0)\").attr(\"rowspan\",size);\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$(this).find(\"td:eq(0)\").remove();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t//第二月\r\n");
      out.write("\t\t$.each( ['zb','cg','zhb'], function(i, n){\r\n");
      out.write("\t\t\tvar size = $('#statTable1 tr[relArea=\"'+n+'\"]').length;\r\n");
      out.write("\t\t\t$('#statTable1 tr[relArea=\"'+n+'\"]').each(function(i,n){\r\n");
      out.write("\t\t\t\tif(i == 0){\r\n");
      out.write("\t\t\t\t\t$(this).find(\"td:eq(0)\").attr(\"rowspan\",size);\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$(this).find(\"td:eq(0)\").remove();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t//第三月\r\n");
      out.write("\t\t$.each( ['zb','cg','zhb'], function(i, n){\r\n");
      out.write("\t\t\tvar size = $('#statTable2 tr[relArea=\"'+n+'\"]').length;\r\n");
      out.write("\t\t\t$('#statTable2 tr[relArea=\"'+n+'\"]').each(function(i,n){\r\n");
      out.write("\t\t\t\tif(i == 0){\r\n");
      out.write("\t\t\t\t\t$(this).find(\"td:eq(0)\").attr(\"rowspan\",size);\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$(this).find(\"td:eq(0)\").remove();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}); \r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$.each( ['0','1','2'], function(i, n){\r\n");
      out.write("\t\t\t$('#statTitle'+n).toggle(\r\n");
      out.write("\t\t\t\tfunction(){\r\n");
      out.write("\t\t\t\t\t$('#statTitle'+n).attr('title','点击展开明细');\r\n");
      out.write("\t\t\t\t\t$('#statTitle'+n).nextAll().hide();\r\n");
      out.write("\t\t\t\t\t$('#statTable'+n+' tbody').hide();\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tfunction(){\r\n");
      out.write("\t\t\t\t\t$('#statTitle'+n).attr('title','点击收起明细');\r\n");
      out.write("\t\t\t\t\t$('#statTitle'+n).nextAll().show();\r\n");
      out.write("\t\t\t\t\t$('#statTable'+n+' tbody').show();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t);\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$('#statTitle1').click();\r\n");
      out.write("\t\t$('#statTitle2').click();\r\n");
      out.write("\t});\r\n");
      out.write("</script>");
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

  private boolean _jspx_meth_s_005fdate_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:date
    org.apache.struts2.views.jsp.DateTag _jspx_th_s_005fdate_005f0 = (org.apache.struts2.views.jsp.DateTag) _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.get(org.apache.struts2.views.jsp.DateTag.class);
    _jspx_th_s_005fdate_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fdate_005f0.setParent(null);
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(69,68) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f0.setName("prevMonth");
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(69,68) name = format type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f0.setFormat("yyyy-MM-dd");
    int _jspx_eval_s_005fdate_005f0 = _jspx_th_s_005fdate_005f0.doStartTag();
    if (_jspx_th_s_005fdate_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fdate_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:date
    org.apache.struts2.views.jsp.DateTag _jspx_th_s_005fdate_005f1 = (org.apache.struts2.views.jsp.DateTag) _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.get(org.apache.struts2.views.jsp.DateTag.class);
    _jspx_th_s_005fdate_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fdate_005f1.setParent(null);
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(69,125) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f1.setName("prevMonth");
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(69,125) name = format type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f1.setFormat("yyyy年MM月");
    int _jspx_eval_s_005fdate_005f1 = _jspx_th_s_005fdate_005f1.doStartTag();
    if (_jspx_th_s_005fdate_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005fdate_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:date
    org.apache.struts2.views.jsp.DateTag _jspx_th_s_005fdate_005f2 = (org.apache.struts2.views.jsp.DateTag) _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.get(org.apache.struts2.views.jsp.DateTag.class);
    _jspx_th_s_005fdate_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fdate_005f2.setParent(null);
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(70,69) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f2.setName("nextMonth");
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(70,69) name = format type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f2.setFormat("yyyy-MM-dd");
    int _jspx_eval_s_005fdate_005f2 = _jspx_th_s_005fdate_005f2.doStartTag();
    if (_jspx_th_s_005fdate_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f2);
    return false;
  }

  private boolean _jspx_meth_s_005fdate_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:date
    org.apache.struts2.views.jsp.DateTag _jspx_th_s_005fdate_005f3 = (org.apache.struts2.views.jsp.DateTag) _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.get(org.apache.struts2.views.jsp.DateTag.class);
    _jspx_th_s_005fdate_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005fdate_005f3.setParent(null);
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(70,126) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f3.setName("nextMonth");
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(70,126) name = format type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f3.setFormat("yyyy年MM月");
    int _jspx_eval_s_005fdate_005f3 = _jspx_th_s_005fdate_005f3.doStartTag();
    if (_jspx_th_s_005fdate_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f3);
    return false;
  }

  private boolean _jspx_meth_s_005fdate_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:date
    org.apache.struts2.views.jsp.DateTag _jspx_th_s_005fdate_005f4 = (org.apache.struts2.views.jsp.DateTag) _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.get(org.apache.struts2.views.jsp.DateTag.class);
    _jspx_th_s_005fdate_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005fdate_005f4.setParent(null);
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(72,5) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f4.setName("month1");
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(72,5) name = format type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f4.setFormat("yyyy年MM月");
    int _jspx_eval_s_005fdate_005f4 = _jspx_th_s_005fdate_005f4.doStartTag();
    if (_jspx_th_s_005fdate_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f4);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f1 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f0);
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(99,6) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f1.setTest("qy == 'zb'");
    int _jspx_eval_s_005fif_005f1 = _jspx_th_s_005fif_005f1.doStartTag();
    if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f1.doInitBody();
      }
      do {
        out.write('招');
        out.write('标');
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

  private boolean _jspx_meth_s_005felseif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f0 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f0);
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(100,6) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f0.setTest("qy == 'cg'");
    int _jspx_eval_s_005felseif_005f0 = _jspx_th_s_005felseif_005f0.doStartTag();
    if (_jspx_eval_s_005felseif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f0.doInitBody();
      }
      do {
        out.write('采');
        out.write('购');
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

  private boolean _jspx_meth_s_005felseif_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f1 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f0);
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(101,6) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f1.setTest("qy == 'zhb'");
    int _jspx_eval_s_005felseif_005f1 = _jspx_th_s_005felseif_005f1.doStartTag();
    if (_jspx_eval_s_005felseif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f1.doInitBody();
      }
      do {
        out.write("综合部<br/>中心内部任务");
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

  private boolean _jspx_meth_s_005felse_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:else
    org.apache.struts2.views.jsp.ElseTag _jspx_th_s_005felse_005f0 = (org.apache.struts2.views.jsp.ElseTag) _005fjspx_005ftagPool_005fs_005felse.get(org.apache.struts2.views.jsp.ElseTag.class);
    _jspx_th_s_005felse_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005felse_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f0);
    int _jspx_eval_s_005felse_005f0 = _jspx_th_s_005felse_005f0.doStartTag();
    if (_jspx_eval_s_005felse_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felse_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felse_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felse_005f0.doInitBody();
      }
      do {
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${qy}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
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

  private boolean _jspx_meth_s_005fif_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f2 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f2.setParent(null);
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(121,2) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f2.setTest("costStatisticsArea0.size==0");
    int _jspx_eval_s_005fif_005f2 = _jspx_th_s_005fif_005f2.doStartTag();
    if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t<tr style=\"height:80px;\">\r\n");
        out.write("\t\t\t\t<td colspan=\"18\"><div align=\"center\" style=\"font-weight:bold;\">没有相关记录！</div></td>\r\n");
        out.write("\t\t\t</tr>\r\n");
        out.write("\t\t");
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

  private boolean _jspx_meth_s_005fdate_005f5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:date
    org.apache.struts2.views.jsp.DateTag _jspx_th_s_005fdate_005f5 = (org.apache.struts2.views.jsp.DateTag) _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.get(org.apache.struts2.views.jsp.DateTag.class);
    _jspx_th_s_005fdate_005f5.setPageContext(_jspx_page_context);
    _jspx_th_s_005fdate_005f5.setParent(null);
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(151,5) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f5.setName("month2");
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(151,5) name = format type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f5.setFormat("yyyy年MM月");
    int _jspx_eval_s_005fdate_005f5 = _jspx_th_s_005fdate_005f5.doStartTag();
    if (_jspx_th_s_005fdate_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f5);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f4 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f3);
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(180,6) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f4.setTest("qy == 'zb'");
    int _jspx_eval_s_005fif_005f4 = _jspx_th_s_005fif_005f4.doStartTag();
    if (_jspx_eval_s_005fif_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f4.doInitBody();
      }
      do {
        out.write('招');
        out.write('标');
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

  private boolean _jspx_meth_s_005felseif_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f2 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f3);
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(181,6) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f2.setTest("qy == 'cg'");
    int _jspx_eval_s_005felseif_005f2 = _jspx_th_s_005felseif_005f2.doStartTag();
    if (_jspx_eval_s_005felseif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f2.doInitBody();
      }
      do {
        out.write('采');
        out.write('购');
        int evalDoAfterBody = _jspx_th_s_005felseif_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005felseif_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005felseif_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f2);
    return false;
  }

  private boolean _jspx_meth_s_005felseif_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f3 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f3);
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(182,6) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f3.setTest("qy == 'zhb'");
    int _jspx_eval_s_005felseif_005f3 = _jspx_th_s_005felseif_005f3.doStartTag();
    if (_jspx_eval_s_005felseif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f3.doInitBody();
      }
      do {
        out.write("综合部<br/>中心内部任务");
        int evalDoAfterBody = _jspx_th_s_005felseif_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005felseif_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005felseif_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f3);
    return false;
  }

  private boolean _jspx_meth_s_005felse_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:else
    org.apache.struts2.views.jsp.ElseTag _jspx_th_s_005felse_005f2 = (org.apache.struts2.views.jsp.ElseTag) _005fjspx_005ftagPool_005fs_005felse.get(org.apache.struts2.views.jsp.ElseTag.class);
    _jspx_th_s_005felse_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005felse_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f3);
    int _jspx_eval_s_005felse_005f2 = _jspx_th_s_005felse_005f2.doStartTag();
    if (_jspx_eval_s_005felse_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felse_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felse_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felse_005f2.doInitBody();
      }
      do {
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${qy}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
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

  private boolean _jspx_meth_s_005fif_005f5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f5 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f5.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f5.setParent(null);
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(202,2) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f5.setTest("costStatisticsArea.size==0");
    int _jspx_eval_s_005fif_005f5 = _jspx_th_s_005fif_005f5.doStartTag();
    if (_jspx_eval_s_005fif_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f5.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t<tr style=\"height:80px;\">\r\n");
        out.write("\t\t\t\t<td colspan=\"18\"><div align=\"center\" style=\"font-weight:bold;\">没有相关记录！</div></td>\r\n");
        out.write("\t\t\t</tr>\r\n");
        out.write("\t\t");
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

  private boolean _jspx_meth_s_005fdate_005f6(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:date
    org.apache.struts2.views.jsp.DateTag _jspx_th_s_005fdate_005f6 = (org.apache.struts2.views.jsp.DateTag) _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.get(org.apache.struts2.views.jsp.DateTag.class);
    _jspx_th_s_005fdate_005f6.setPageContext(_jspx_page_context);
    _jspx_th_s_005fdate_005f6.setParent(null);
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(233,5) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f6.setName("month3");
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(233,5) name = format type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f6.setFormat("yyyy年MM月");
    int _jspx_eval_s_005fdate_005f6 = _jspx_th_s_005fdate_005f6.doStartTag();
    if (_jspx_th_s_005fdate_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f6);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f7 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f7.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f6);
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(262,6) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f7.setTest("qy == 'zb'");
    int _jspx_eval_s_005fif_005f7 = _jspx_th_s_005fif_005f7.doStartTag();
    if (_jspx_eval_s_005fif_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f7.doInitBody();
      }
      do {
        out.write('招');
        out.write('标');
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

  private boolean _jspx_meth_s_005felseif_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f4 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f6);
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(263,6) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f4.setTest("qy == 'cg'");
    int _jspx_eval_s_005felseif_005f4 = _jspx_th_s_005felseif_005f4.doStartTag();
    if (_jspx_eval_s_005felseif_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f4.doInitBody();
      }
      do {
        out.write('采');
        out.write('购');
        int evalDoAfterBody = _jspx_th_s_005felseif_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005felseif_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005felseif_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f4);
    return false;
  }

  private boolean _jspx_meth_s_005felseif_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f5 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f5.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f6);
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(264,6) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f5.setTest("qy == 'zhb'");
    int _jspx_eval_s_005felseif_005f5 = _jspx_th_s_005felseif_005f5.doStartTag();
    if (_jspx_eval_s_005felseif_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f5.doInitBody();
      }
      do {
        out.write("综合部<br/>中心内部任务");
        int evalDoAfterBody = _jspx_th_s_005felseif_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005felseif_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005felseif_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f5);
    return false;
  }

  private boolean _jspx_meth_s_005felse_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:else
    org.apache.struts2.views.jsp.ElseTag _jspx_th_s_005felse_005f4 = (org.apache.struts2.views.jsp.ElseTag) _005fjspx_005ftagPool_005fs_005felse.get(org.apache.struts2.views.jsp.ElseTag.class);
    _jspx_th_s_005felse_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005felse_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f6);
    int _jspx_eval_s_005felse_005f4 = _jspx_th_s_005felse_005f4.doStartTag();
    if (_jspx_eval_s_005felse_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felse_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felse_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felse_005f4.doInitBody();
      }
      do {
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${qy}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        int evalDoAfterBody = _jspx_th_s_005felse_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005felse_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005felse_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f4);
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
    // /WEB-INF/content/plan/cost-ctrl-bid-purc-statZc.jsp(284,2) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f8.setTest("costStatisticsArea2.size==0");
    int _jspx_eval_s_005fif_005f8 = _jspx_th_s_005fif_005f8.doStartTag();
    if (_jspx_eval_s_005fif_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f8.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f8.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t<tr style=\"height:80px;\">\r\n");
        out.write("\t\t\t\t<td colspan=\"18\"><div align=\"center\" style=\"font-weight:bold;\">没有相关记录！</div></td>\r\n");
        out.write("\t\t\t</tr>\r\n");
        out.write("\t\t");
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
}
