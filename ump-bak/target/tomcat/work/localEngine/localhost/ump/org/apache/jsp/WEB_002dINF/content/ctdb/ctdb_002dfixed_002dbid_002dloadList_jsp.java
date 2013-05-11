package org.apache.jsp.WEB_002dINF.content.ctdb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ctdb_002dfixed_002dbid_002dloadList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(2);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/WEB-INF/PowerDesk-tags.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvar_005fvalue;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fp_005fpage_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvar_005fvalue = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fp_005fpage_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvar_005fvalue.release();
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.release();
    _005fjspx_005ftagPool_005fp_005fpage_005fnobody.release();
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
      out.write("<table class=\"ctdbTable\" cellpadding=\"0\" cellspacing=\"0\" style=\"margin-top: 5px;\">\r\n");
      out.write("\t<colgroup>\r\n");
      out.write("\t\t<col width=\"100px;\"/>\r\n");
      out.write("\t\t<col/>\r\n");
      out.write("\t\t<col/>\r\n");
      out.write("\t\t<col width=\"80px;\"/>\r\n");
      out.write("\t\t<col width=\"80px;\"/>\r\n");
      out.write("\t\t<col width=\"80px;\"/>\r\n");
      out.write("\t\t<col width=\"80px;\"/>\t\t\r\n");
      out.write("\t\t<col width=\"80px;\"/>\t\t\r\n");
      out.write("\t\t<col width=\"80px;\"/>\t\t\r\n");
      out.write("\t\t<col width=\"80px;\"/>\t\t\t\t\r\n");
      out.write("\t</colgroup>\r\n");
      out.write("\t<thead>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th title=\"项目名称\" nowrap=\"nowrap\"  style=\"background-image: url('');\">项目</th>\r\n");
      out.write("\t\t\t<th title=\"工程名称(设计阶段)\" nowrap=\"nowrap\">工程</th>\r\n");
      out.write("\t\t\t<th title=\"中标单位\" nowrap=\"nowrap\">中标单位</th>\r\n");
      out.write("\t\t\t<th title=\"招标范围\" nowrap=\"nowrap\">招标范围</th>\r\n");
      out.write("\t\t\t<th title=\"计价模式\" nowrap=\"nowrap\">计价模式</th>\r\n");
      out.write("\t\t\t<th title=\"施工面积(m2)\" nowrap=\"nowrap\">施工面积</th>\r\n");
      out.write("\t\t\t<th title=\"中标价\" nowrap=\"nowrap\">中标价</th>\r\n");
      out.write("\t\t\t<th title=\"单位造价(单价)\" nowrap=\"nowrap\">单位造价</th>\r\n");
      out.write("\t\t\t<th title=\"定标日期\" nowrap=\"nowrap\">定标日期</th>\r\n");
      out.write("\t\t\t<th title=\"网批编号\" nowrap=\"nowrap\">网批编号</th>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</thead>\r\n");
      out.write("\t<tbody>\r\n");
      out.write("\t");
      if (_jspx_meth_s_005fiterator_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t</tbody>\r\n");
      out.write("</table>\r\n");
      out.write("<div class=\"pageFooter\">\t\t\r\n");
      out.write("\t<input type=\"hidden\" name=\"pageNums\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPages}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></input>\t\t\r\n");
      out.write("\t<input type=\"hidden\" name=\"orgName\" value=\"");
      out.print(request.getParameter("orgName")==null?"":request.getParameter("orgName"));
      out.write("\"></input>\r\n");
      out.write("\t<input type=\"hidden\" name=\"projectName\"  value=\"");
      out.print(request.getParameter("projectName")==null?"":request.getParameter("projectName"));
      out.write("\"></input>\r\n");
      out.write("\t<input type=\"hidden\" name=\"bidSupName\"  value=\"");
      out.print(request.getParameter("bidSupName")==null?"":request.getParameter("bidSupName"));
      out.write("\"></input>\r\n");
      out.write("\t<input type=\"hidden\" name=\"bidDate\"  value=\"");
      out.print(request.getParameter("bidDate")==null?"":request.getParameter("bidDate"));
      out.write("\"></input>\r\n");
      out.write("\t");
      if (_jspx_meth_p_005fpage_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
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
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_005fiterator_005f0 = (org.apache.struts2.views.jsp.IteratorTag) _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvar_005fvalue.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_005fiterator_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fiterator_005f0.setParent(null);
    // /WEB-INF/content/ctdb/ctdb-fixed-bid-loadList.jsp(31,1) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f0.setValue("page.result");
    // /WEB-INF/content/ctdb/ctdb-fixed-bid-loadList.jsp(31,1) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f0.setVar("bided");
    int _jspx_eval_s_005fiterator_005f0 = _jspx_th_s_005fiterator_005f0.doStartTag();
    if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fiterator_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fiterator_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t<tr>\r\n");
        out.write("\t\t<td class=\"pd-chill-tip\" title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${orgName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"><div class=\"partHide\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${orgName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</div></td>\r\n");
        out.write("\t\t<td class=\"pd-chill-tip\" title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${projectName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"><div class=\"partHide\"  >");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${projectName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</div></td>\r\n");
        out.write("\t\t<td class=\"pd-chill-tip\" title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bidSupCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"><div class=\"partHide\" >");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bidSupCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</div></td>\r\n");
        out.write("\t\t<td class=\"pd-chill-tip\" title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bidArea}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"><div class=\"partHide\" >");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bidArea}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</div></td>\r\n");
        out.write("\t\t<td class=\"pd-chill-tip\" title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${calcuMode}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"><div class=\"partHide\" >");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${calcuMode}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</div></td>\r\n");
        out.write("\t\t<td class=\"pd-chill-tip\" title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${constructArea}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"><div class=\"partHide\" >");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ constructArea}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</div></td>\r\n");
        out.write("\t\t<td class=\"pd-chill-tip\" title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bidedPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"><div class=\"partHide\" >");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ bidedPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</div></td>\r\n");
        out.write("\t\t<td class=\"pd-chill-tip\" title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${unitPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"><div class=\"partHide\" >");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${unitPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</div></td>\r\n");
        out.write("\t\t<td class=\"pd-chill-tip\" title=\"");
        if (_jspx_meth_s_005fdate_005f0(_jspx_th_s_005fiterator_005f0, _jspx_page_context))
          return true;
        out.write("\"><div>");
        if (_jspx_meth_s_005fdate_005f1(_jspx_th_s_005fiterator_005f0, _jspx_page_context))
          return true;
        out.write("</div></td>\r\n");
        out.write("\t\t<td title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${approveCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" >\r\n");
        out.write("\t\t\t<div class=\"partHide\">\r\n");
        out.write("\t\t\t<a  style=\"color:red;\" href=\"javascript:void(0)\" \r\n");
        out.write("\t\t\t\ttitle=\"点击查看网批号：");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${approveCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"  class=\"pd-chill-tip\" \r\n");
        out.write("\t\t\t\tonclick=\"openResTask('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${resCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write('\'');
        out.write(',');
        out.write('\'');
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${approveCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write('\'');
        out.write(',');
        out.write('\'');
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${importType}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("');\">\r\n");
        out.write("\t\t\t\t");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${approveCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\r\n");
        out.write("\t\t\t</a>\r\n");
        out.write("\t\t\t</div>\r\n");
        out.write("\t\t</td>");
        out.write("\r\n");
        out.write("\t</tr>\r\n");
        out.write("\t");
        int evalDoAfterBody = _jspx_th_s_005fiterator_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fiterator_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvar_005fvalue.reuse(_jspx_th_s_005fiterator_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvar_005fvalue.reuse(_jspx_th_s_005fiterator_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fdate_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:date
    org.apache.struts2.views.jsp.DateTag _jspx_th_s_005fdate_005f0 = (org.apache.struts2.views.jsp.DateTag) _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.get(org.apache.struts2.views.jsp.DateTag.class);
    _jspx_th_s_005fdate_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fdate_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f0);
    // /WEB-INF/content/ctdb/ctdb-fixed-bid-loadList.jsp(41,34) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f0.setName("#bided.bidDate");
    // /WEB-INF/content/ctdb/ctdb-fixed-bid-loadList.jsp(41,34) name = format type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f0.setFormat("yy-MM-dd");
    int _jspx_eval_s_005fdate_005f0 = _jspx_th_s_005fdate_005f0.doStartTag();
    if (_jspx_th_s_005fdate_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fdate_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:date
    org.apache.struts2.views.jsp.DateTag _jspx_th_s_005fdate_005f1 = (org.apache.struts2.views.jsp.DateTag) _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.get(org.apache.struts2.views.jsp.DateTag.class);
    _jspx_th_s_005fdate_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fdate_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f0);
    // /WEB-INF/content/ctdb/ctdb-fixed-bid-loadList.jsp(41,90) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f1.setName("#bided.bidDate");
    // /WEB-INF/content/ctdb/ctdb-fixed-bid-loadList.jsp(41,90) name = format type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f1.setFormat("yy-MM-dd");
    int _jspx_eval_s_005fdate_005f1 = _jspx_th_s_005fdate_005f1.doStartTag();
    if (_jspx_th_s_005fdate_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f1);
    return false;
  }

  private boolean _jspx_meth_p_005fpage_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  p:page
    com.hhz.core.tags.PageTag _jspx_th_p_005fpage_005f0 = (com.hhz.core.tags.PageTag) _005fjspx_005ftagPool_005fp_005fpage_005fnobody.get(com.hhz.core.tags.PageTag.class);
    _jspx_th_p_005fpage_005f0.setPageContext(_jspx_page_context);
    _jspx_th_p_005fpage_005f0.setParent(null);
    int _jspx_eval_p_005fpage_005f0 = _jspx_th_p_005fpage_005f0.doStartTag();
    if (_jspx_th_p_005fpage_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fp_005fpage_005fnobody.reuse(_jspx_th_p_005fpage_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fp_005fpage_005fnobody.reuse(_jspx_th_p_005fpage_005f0);
    return false;
  }
}
