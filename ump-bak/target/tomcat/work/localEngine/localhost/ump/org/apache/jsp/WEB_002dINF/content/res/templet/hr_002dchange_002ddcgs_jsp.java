package org.apache.jsp.WEB_002dINF.content.res.templet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import com.hhz.ump.util.CodeNameUtil;
import java.util.Map;
import com.hhz.ump.util.JspUtil;
import com.hhz.ump.util.CodeNameUtil;

public final class hr_002dchange_002ddcgs_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(7);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/WEB-INF/content/res/templet/template-header.jsp");
    _jspx_dependants.add("/WEB-INF/content/res/templet/hr-change-base.jsp");
    _jspx_dependants.add("/WEB-INF/content/res/templet/template-var.jsp");
    _jspx_dependants.add("/WEB-INF/content/res/templet/template-approver.jsp");
    _jspx_dependants.add("/WEB-INF/content/res/templet/template-footer.jsp");
    _jspx_dependants.add("/WEB-INF/PowerDesk-tags.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fset_0026_005fvar;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005felse;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fset_0026_005fvar = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005felse = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.release();
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fs_005felse.release();
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.release();
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
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div align=\"center\" class=\"billContent\">\r\n");
      out.write("\t<form action=\"res-approve-info!save.action\" method=\"post\" id=\"templetForm\">\r\n");
      out.write("\t\t<table  class=\"mainTable\" style=\"margin-bottom: 5px;\">\r\n");
      out.write("\t\t\t<col width=\"80\"/>\r\n");
      out.write("\t\t\t<col />\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t<td></td>\r\n");
      out.write("\t\t\t<td class=\"chkGroup\" align=\"left\" validate=\"required\">\r\n");
      out.write("\t\t\t<table class=\"tb_checkbox\">\r\n");
      out.write("\t\t\t\t<col width=\"250\">\r\n");
      out.write("\t\t\t\t<col width=\"250\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f0(_jspx_page_context))
        return;
      out.write("总经理级及以上</td>\r\n");
      out.write("\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f1(_jspx_page_context))
        return;
      out.write("副总经理级</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f2(_jspx_page_context))
        return;
      out.write("经理级(双线管理人员部门第一负责人除外)</td>\r\n");
      out.write("\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f3(_jspx_page_context))
        return;
      out.write("双线管理人员部门第一负责人(财务、成本)</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f4(_jspx_page_context))
        return;
      out.write("双线管理人员部门第一负责人(人资)</td>\r\n");
      out.write("\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f5(_jspx_page_context))
        return;
      out.write("其他人员</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t<td></td>\r\n");
      out.write("\t\t\t<td class=\"chkGroup\" align=\"left\">\r\n");
      out.write("\t\t\t<table class=\"tb_checkbox\">\r\n");
      out.write("\t\t\t\t<col width=\"250\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f6(_jspx_page_context))
        return;
      out.write("是否为双线管理部门</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t\t");
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div align=\"center\" class=\"billContent\">\r\n");
      out.write("\t\t\t\t");
      //  s:set
      org.apache.struts2.views.jsp.SetTag _jspx_th_s_005fset_005f0 = (org.apache.struts2.views.jsp.SetTag) _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.get(org.apache.struts2.views.jsp.SetTag.class);
      _jspx_th_s_005fset_005f0.setPageContext(_jspx_page_context);
      _jspx_th_s_005fset_005f0.setParent(null);
      // /WEB-INF/content/res/templet/template-var.jsp(1,1) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_s_005fset_005f0.setVar("isMy");
      int _jspx_eval_s_005fset_005f0 = _jspx_th_s_005fset_005f0.doStartTag();
      if (_jspx_eval_s_005fset_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_s_005fset_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_s_005fset_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_s_005fset_005f0.doInitBody();
        }
        do {
          out.print(request.getParameter("isMy")  );
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
      out.write(" \r\n");
      out.write("<input type=\"hidden\" name=\"resAuthTypeCd\" id=\"resAuthTypeCd\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${resAuthTypeCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("<input type=\"hidden\" name=\"id\" id=\"resApproveInfoId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${resApproveInfoId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("<input type=\"hidden\" name=\"recordVersion\" id=\"recordVersion\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${recordVersion}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("<input type=\"hidden\" name=\"entityTmpId\" id=\"entityTmpId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entityTmpId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />");
      out.write("\r\n");
      out.write("\t\t\t\t<table  class=\"mainTable\">\r\n");
      out.write("\t\t\t\t\t<col width=\"80\"/>\r\n");
      out.write("\t\t\t\t\t<col/>\r\n");
      out.write("\t\t\t\t\t<col width=\"80\"/>\r\n");
      out.write("\t\t\t\t\t<col/>\r\n");
      out.write("\t\t\t\t\t<col width=\"80\"/>\r\n");
      out.write("\t\t\t\t\t<col/>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td class=\"td_title\">中心</td>\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"5\">\r\n");
      out.write("\t\t\t\t\t\t");
      //  s:if
      org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f0 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
      _jspx_th_s_005fif_005f0.setPageContext(_jspx_page_context);
      _jspx_th_s_005fif_005f0.setParent(null);
      // /WEB-INF/content/res/templet/hr-change-base.jsp(18,6) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_s_005fif_005f0.setTest("resApproveInfoId==null");
      int _jspx_eval_s_005fif_005f0 = _jspx_th_s_005fif_005f0.doStartTag();
      if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_s_005fif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_s_005fif_005f0.doInitBody();
        }
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t");
          //  s:set
          org.apache.struts2.views.jsp.SetTag _jspx_th_s_005fset_005f1 = (org.apache.struts2.views.jsp.SetTag) _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.get(org.apache.struts2.views.jsp.SetTag.class);
          _jspx_th_s_005fset_005f1.setPageContext(_jspx_page_context);
          _jspx_th_s_005fset_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f0);
          // /WEB-INF/content/res/templet/hr-change-base.jsp(19,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_s_005fset_005f1.setVar("centerName");
          int _jspx_eval_s_005fset_005f1 = _jspx_th_s_005fset_005f1.doStartTag();
          if (_jspx_eval_s_005fset_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_s_005fset_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_s_005fset_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_s_005fset_005f1.doInitBody();
            }
            do {
              out.print(CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) );
              out.write(' ');
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
            return;
          }
          _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.reuse(_jspx_th_s_005fset_005f1);
          out.write("\r\n");
          out.write("\t\t\t\t\t\t");
          //  s:set
          org.apache.struts2.views.jsp.SetTag _jspx_th_s_005fset_005f2 = (org.apache.struts2.views.jsp.SetTag) _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.get(org.apache.struts2.views.jsp.SetTag.class);
          _jspx_th_s_005fset_005f2.setPageContext(_jspx_page_context);
          _jspx_th_s_005fset_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f0);
          // /WEB-INF/content/res/templet/hr-change-base.jsp(20,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_s_005fset_005f2.setVar("centerCd");
          int _jspx_eval_s_005fset_005f2 = _jspx_th_s_005fset_005f2.doStartTag();
          if (_jspx_eval_s_005fset_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_s_005fset_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_s_005fset_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_s_005fset_005f2.doInitBody();
            }
            do {
              out.print(SpringSecurityUtils.getCurrentCenterCd() );
              int evalDoAfterBody = _jspx_th_s_005fset_005f2.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_s_005fset_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.popBody();
            }
          }
          if (_jspx_th_s_005fset_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.reuse(_jspx_th_s_005fset_005f2);
            return;
          }
          _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.reuse(_jspx_th_s_005fset_005f2);
          out.write("\r\n");
          out.write("\t\t\t\t\t\t");
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
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_s_005felse_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<input validate=\"required\" type=\"text\" name=\"templateBean.centerName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${centerName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" id=\"centerName\" readonly=\"readonly\" ");
      if (_jspx_meth_s_005fif_005f1(_jspx_page_context))
        return;
      if (_jspx_meth_s_005felse_005f1(_jspx_page_context))
        return;
      out.write("/>\r\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" id=\"centerCd\" name=\"templateBean.centerCd\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${centerCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"  />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td class=\"td_title\">姓名</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.userName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.userName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"  />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t<td class=\"td_title\">部门</td>\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"3\">\r\n");
      out.write("\t\t\t\t\t\t<input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.deptName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.deptName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"  />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td class=\"td_title\">职位</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.positionName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.positionName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"  />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t<td class=\"td_title\">入职日期</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input onfocus=\"WdatePicker()\" class=\"inputBorder Wdate\" type=\"text\" validate=\"required\" name=\"templateBean.enterDate\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.enterDate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"  />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t<td class=\"td_title\">生效日期</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input onfocus=\"WdatePicker()\" class=\"inputBorder Wdate\" type=\"text\" validate=\"required\" name=\"templateBean.effectDate\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.effectDate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"  />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td class=\"td_title\">用工类别</td>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"5\" class=\"chkGroup\" align=\"left\" validate=\"required\">\r\n");
      out.write("\t\t\t\t\t<table class=\"tb_checkbox\">\r\n");
      out.write("\t\t\t\t\t<col width=\"100\">\r\n");
      out.write("\t\t\t\t\t<col width=\"100\">\r\n");
      out.write("\t\t\t\t\t<col width=\"100\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f7(_jspx_page_context))
        return;
      out.write("正式工</td>\r\n");
      out.write("\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f8(_jspx_page_context))
        return;
      out.write("临时工</td>\r\n");
      out.write("\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f9(_jspx_page_context))
        return;
      out.write("实习生</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f10(_jspx_page_context))
        return;
      out.write("外聘</td>\r\n");
      out.write("\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f11(_jspx_page_context))
        return;
      out.write("外派</td>\r\n");
      out.write("\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f12(_jspx_page_context))
        return;
      out.write("外籍</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td class=\"td_title\">调整项目</td>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"5\" class=\"chkGroup\"align=\"left\">\r\n");
      out.write("\t\t\t\t\t<div id=\"alertShow\">\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<table class=\"tb_checkbox\">\r\n");
      out.write("\t\t\t\t\t<col width=\"100\">\r\n");
      out.write("\t\t\t\t\t<col width=\"100\">\r\n");
      out.write("\t\t\t\t\t<col width=\"100\">\r\n");
      out.write("\t\t\t\t\t<col width=\"200\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f13(_jspx_page_context))
        return;
      out.write("降职</td>\r\n");
      out.write("\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f14(_jspx_page_context))
        return;
      out.write("通过试用期</td>\r\n");
      out.write("\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f15(_jspx_page_context))
        return;
      out.write("升职</td>\r\n");
      out.write("\t\t\t\t\t<td></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f16(_jspx_page_context))
        return;
      out.write("内部调动</td>\r\n");
      out.write("\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f17(_jspx_page_context))
        return;
      out.write("工资变动</td>\r\n");
      out.write("\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f18(_jspx_page_context))
        return;
      out.write("临时借调</td>\r\n");
      out.write("\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f19(_jspx_page_context))
        return;
      out.write("离职</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f20(_jspx_page_context))
        return;
      out.write("新聘</td>\r\n");
      out.write("\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f21(_jspx_page_context))
        return;
      out.write("奖励</td>\r\n");
      out.write("\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f22(_jspx_page_context))
        return;
      out.write("惩罚</td>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"2\">");
      if (_jspx_meth_s_005fcheckbox_005f23(_jspx_page_context))
        return;
      out.write("其他\r\n");
      out.write("\t\t\t\t\t<input class=\"inputBorder\" type=\"text\" name=\"templateBean.adjustOther\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.adjustOther}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" style=\"width:100px;\"/>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t<table  class=\"mainTable\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-top: none; \">\r\n");
      out.write("\t\t\t\t\t<col width=\"100px\" />\t\r\n");
      out.write("\t\t\t\t\t<col/>\t\r\n");
      out.write("\t\t\t\t\t<col/>\t\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td style=\"border-top: none;text-align: center;font-weight: bold;\">调整内容</td>\r\n");
      out.write("\t\t\t\t\t\t<td style=\"border-top: none;text-align: center;font-weight: bold;\">调整前</td>\r\n");
      out.write("\t\t\t\t\t\t<td style=\"border-top: none;text-align: center;font-weight: bold;\">调整后</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td class=\"td_title\">中心/公司</td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.centerBefore\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.centerBefore}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.centerAfter\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.centerAfter}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td class=\"td_title\">部门</td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.deptBefore\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.deptBefore}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.deptAfter\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.deptAfter}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td class=\"td_title\">职位</td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.positionBefore\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.positionBefore}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.positionAfter\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.positionAfter}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td class=\"td_title\">职级</td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.levelBefore\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.levelBefore}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.levelAfter\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.levelAfter}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td class=\"td_title\">工资</td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.salaryBefore\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.salaryBefore}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.salaryAfter\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.salaryAfter}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td class=\"td_title\">外派补贴(元)</td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.foreSubsidyBefore\" onblur=\"formatVal($(this));\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.foreSubsidyBefore}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.foreSubsidyAfter\" onblur=\"formatVal($(this));\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.foreSubsidyAfter}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td class=\"td_title\">住房补贴(元)</td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.houseSubsidyBefore\" onblur=\"formatVal($(this));\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.houseSubsidyBefore}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.houseSubsidyAfter\" onblur=\"formatVal($(this));\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.houseSubsidyAfter}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td class=\"td_title\">交通补贴(元)</td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.trafficSubsidyBefore\" onblur=\"formatVal($(this));\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.trafficSubsidyBefore}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.trafficSubsidyAfter\" onblur=\"formatVal($(this));\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.trafficSubsidyAfter}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td class=\"td_title\">通讯补贴(元)</td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.commuSubsidyBefore\" onblur=\"formatVal($(this));\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.commuSubsidyBefore}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.commuSubsidyAfter\" onblur=\"formatVal($(this));\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.commuSubsidyAfter}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td class=\"td_title\">特殊补贴(元)</td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.specialSubsidyBefore\" onblur=\"formatVal($(this));\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.specialSubsidyBefore}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" validate=\"required\" type=\"text\" name=\"templateBean.specialSubsidyAfter\" onblur=\"formatVal($(this));\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.specialSubsidyAfter}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td class=\"td_title\">其他</td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" type=\"text\" name=\"templateBean.otherBefore\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.otherBefore}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t<td><input class=\"inputBorder\" type=\"text\" name=\"templateBean.otherAfter\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.otherAfter}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td class=\"td_title\">备注</td>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t<textarea class=\"inputBorder contentTextArea\" name=\"templateBean.remark\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.remark}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</textarea>\r\n");
      out.write("\t\t\t\t\t</td></tr>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<script>\r\n");
      out.write("\t\t//alert(\"主动离职，请上传《辞职申请报告》；\\n被动离职，请上传《员工调岗培训或辞退建议表》、《考核表》\");\r\n");
      out.write("\t\t//对有popupValue属性的checkbox加上点击事件\r\n");
      out.write("\t\t$(\"[popupValue]\").each(function(){\r\n");
      out.write("\t\t\tvar obj = $(this);\r\n");
      out.write("\t\t\t\tobj.click(function(){\r\n");
      out.write("\t\t\t\t\tif(obj.attr(\"checked\") == true){\r\n");
      out.write("\t\t\t\t\t\tif($.trim(obj.attr(\"popupValue\")) != \"\"){\r\n");
      out.write("\t\t\t\t\t\t\talert(obj.attr(\"popupValue\").replace(\"s\",\"\\n\"));\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t$(\"#alertShow\").html(obj.attr(\"popupValue\").replace(\"s\",\"<br/>\")).css(\"color\",\"red\");\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t$(\"#alertShow\").html(\"\");\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\r\n");
      out.write("\t\t");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t</form>\r\n");
      out.write("</div>\r\n");
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_005fset_005f2(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("<div id=\"searchUserDiv\" class=\"searchUserDiv\"></div>\r\n");
      if (_jspx_meth_s_005fif_005f2(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_s_005felse_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_s_005fif_005f3(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_s_005felse_005f3(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t$(\".inputBorder\").filter(\"input\").each(function(i){\r\n");
      out.write("\t  \t$(this).wrap(\"<span class='pd-chill-tip' title=\"+$(this).val()+\"></span>\");\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      if (_jspx_meth_s_005fif_005f4(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_s_005felse_005f4(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\ttry{\r\n");
      out.write("\t\t$('.singleSelect').userSelect({\r\n");
      out.write("\t\t\tmuti:false\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$('.mutiSelect').userSelect({\r\n");
      out.write("\t\t\tmuti:true\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$('.orgSelect').orgSelect({\r\n");
      out.write("\t\t\torgMuti:false\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$('.projSelect').orgSelect({\r\n");
      out.write("// \t\t\tshowProjectOrg:true\r\n");
      out.write("\t\t\torgMuti:false\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}catch(e){\r\n");
      out.write("\r\n");
      out.write("\t}\r\n");
      out.write("</script>");
      out.write('\r');
      out.write('\n');
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

  private boolean _jspx_meth_s_005fcheckbox_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f0 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f0.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-dcgs.jsp(19,8) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f0.setName("templateBean.positionLevel1");
    // /WEB-INF/content/res/templet/hr-change-dcgs.jsp(19,8) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f0.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f0 = _jspx_th_s_005fcheckbox_005f0.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f1 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f1.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-dcgs.jsp(20,8) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f1.setName("templateBean.positionLevel2");
    // /WEB-INF/content/res/templet/hr-change-dcgs.jsp(20,8) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f1.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f1 = _jspx_th_s_005fcheckbox_005f1.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f2 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f2.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-dcgs.jsp(23,8) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f2.setName("templateBean.positionLevel3");
    // /WEB-INF/content/res/templet/hr-change-dcgs.jsp(23,8) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f2.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f2 = _jspx_th_s_005fcheckbox_005f2.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f2);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f3 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f3.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-dcgs.jsp(24,8) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f3.setName("templateBean.positionLevel4");
    // /WEB-INF/content/res/templet/hr-change-dcgs.jsp(24,8) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f3.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f3 = _jspx_th_s_005fcheckbox_005f3.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f3);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f4 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f4.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-dcgs.jsp(27,8) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f4.setName("templateBean.positionLevel6");
    // /WEB-INF/content/res/templet/hr-change-dcgs.jsp(27,8) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f4.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f4 = _jspx_th_s_005fcheckbox_005f4.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f4);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f5 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f5.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f5.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-dcgs.jsp(28,8) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f5.setName("templateBean.positionLevel5");
    // /WEB-INF/content/res/templet/hr-change-dcgs.jsp(28,8) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f5.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f5 = _jspx_th_s_005fcheckbox_005f5.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f5);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f6(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f6 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f6.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f6.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-dcgs.jsp(39,8) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f6.setName("templateBean.positionType1");
    // /WEB-INF/content/res/templet/hr-change-dcgs.jsp(39,8) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f6.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f6 = _jspx_th_s_005fcheckbox_005f6.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f6);
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
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_s_005fset_005f3(_jspx_th_s_005felse_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_s_005fset_005f4(_jspx_th_s_005felse_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
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

  private boolean _jspx_meth_s_005fset_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felse_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:set
    org.apache.struts2.views.jsp.SetTag _jspx_th_s_005fset_005f3 = (org.apache.struts2.views.jsp.SetTag) _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.get(org.apache.struts2.views.jsp.SetTag.class);
    _jspx_th_s_005fset_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005fset_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felse_005f0);
    // /WEB-INF/content/res/templet/hr-change-base.jsp(23,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fset_005f3.setVar("centerName");
    int _jspx_eval_s_005fset_005f3 = _jspx_th_s_005fset_005f3.doStartTag();
    if (_jspx_eval_s_005fset_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fset_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fset_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fset_005f3.doInitBody();
      }
      do {
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.centerName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        int evalDoAfterBody = _jspx_th_s_005fset_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fset_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fset_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.reuse(_jspx_th_s_005fset_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.reuse(_jspx_th_s_005fset_005f3);
    return false;
  }

  private boolean _jspx_meth_s_005fset_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felse_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:set
    org.apache.struts2.views.jsp.SetTag _jspx_th_s_005fset_005f4 = (org.apache.struts2.views.jsp.SetTag) _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.get(org.apache.struts2.views.jsp.SetTag.class);
    _jspx_th_s_005fset_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005fset_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felse_005f0);
    // /WEB-INF/content/res/templet/hr-change-base.jsp(24,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fset_005f4.setVar("centerCd");
    int _jspx_eval_s_005fset_005f4 = _jspx_th_s_005fset_005f4.doStartTag();
    if (_jspx_eval_s_005fset_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fset_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fset_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fset_005f4.doInitBody();
      }
      do {
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${templateBean.centerCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        int evalDoAfterBody = _jspx_th_s_005fset_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fset_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fset_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.reuse(_jspx_th_s_005fset_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.reuse(_jspx_th_s_005fset_005f4);
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
    // /WEB-INF/content/res/templet/hr-change-base.jsp(26,134) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f1.setTest("#isMy==1");
    int _jspx_eval_s_005fif_005f1 = _jspx_th_s_005fif_005f1.doStartTag();
    if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f1.doInitBody();
      }
      do {
        out.write(" class=\"inputBorderNoTip orgSelect\" style=\"cursor: pointer;\" ");
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
        out.write(" class=\"inputBorderNoTip\" ");
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

  private boolean _jspx_meth_s_005fcheckbox_005f7(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f7 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f7.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f7.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-base.jsp(62,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f7.setName("templateBean.userKind1");
    // /WEB-INF/content/res/templet/hr-change-base.jsp(62,9) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f7.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f7 = _jspx_th_s_005fcheckbox_005f7.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f7);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f8(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f8 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f8.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f8.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-base.jsp(63,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f8.setName("templateBean.userKind2");
    // /WEB-INF/content/res/templet/hr-change-base.jsp(63,9) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f8.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f8 = _jspx_th_s_005fcheckbox_005f8.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f8);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f9(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f9 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f9.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f9.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-base.jsp(64,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f9.setName("templateBean.userKind3");
    // /WEB-INF/content/res/templet/hr-change-base.jsp(64,9) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f9.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f9 = _jspx_th_s_005fcheckbox_005f9.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f9);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f10(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f10 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f10.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f10.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-base.jsp(67,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f10.setName("templateBean.userKind4");
    // /WEB-INF/content/res/templet/hr-change-base.jsp(67,9) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f10.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f10 = _jspx_th_s_005fcheckbox_005f10.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f10);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f11(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f11 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f11.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f11.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-base.jsp(68,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f11.setName("templateBean.userKind5");
    // /WEB-INF/content/res/templet/hr-change-base.jsp(68,9) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f11.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f11 = _jspx_th_s_005fcheckbox_005f11.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f11);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f12(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f12 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f12.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f12.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-base.jsp(69,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f12.setName("templateBean.userKind6");
    // /WEB-INF/content/res/templet/hr-change-base.jsp(69,9) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f12.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f12 = _jspx_th_s_005fcheckbox_005f12.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f12);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f13(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f13 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f13.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f13.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-base.jsp(85,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f13.setName("templateBean.adjustItem4");
    // /WEB-INF/content/res/templet/hr-change-base.jsp(85,9) null
    _jspx_th_s_005fcheckbox_005f13.setDynamicAttribute(null, "popupValue", new String("请上传\"人员简历\""));
    // /WEB-INF/content/res/templet/hr-change-base.jsp(85,9) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f13.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f13 = _jspx_th_s_005fcheckbox_005f13.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f13);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f13);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f14(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f14 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f14.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f14.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-base.jsp(86,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f14.setName("templateBean.adjustItem2");
    // /WEB-INF/content/res/templet/hr-change-base.jsp(86,9) null
    _jspx_th_s_005fcheckbox_005f14.setDynamicAttribute(null, "popupValue", new String("请上传各月\"试用期工作总结\"、\"面谈记录表\"和\"月度考核表\""));
    // /WEB-INF/content/res/templet/hr-change-base.jsp(86,9) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f14.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f14 = _jspx_th_s_005fcheckbox_005f14.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f14);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f14);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f15(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f15 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f15.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f15.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-base.jsp(87,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f15.setName("templateBean.adjustItem3");
    // /WEB-INF/content/res/templet/hr-change-base.jsp(87,9) null
    _jspx_th_s_005fcheckbox_005f15.setDynamicAttribute(null, "popupValue", new String("请上传\"人员简历\""));
    // /WEB-INF/content/res/templet/hr-change-base.jsp(87,9) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f15.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f15 = _jspx_th_s_005fcheckbox_005f15.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f15);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f15);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f16(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f16 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f16.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f16.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-base.jsp(91,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f16.setName("templateBean.adjustItem5");
    // /WEB-INF/content/res/templet/hr-change-base.jsp(91,9) null
    _jspx_th_s_005fcheckbox_005f16.setDynamicAttribute(null, "popupValue", new String(""));
    // /WEB-INF/content/res/templet/hr-change-base.jsp(91,9) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f16.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f16 = _jspx_th_s_005fcheckbox_005f16.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f16);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f16);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f17(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f17 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f17.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f17.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-base.jsp(92,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f17.setName("templateBean.adjustItem6");
    // /WEB-INF/content/res/templet/hr-change-base.jsp(92,9) null
    _jspx_th_s_005fcheckbox_005f17.setDynamicAttribute(null, "popupValue", new String(""));
    // /WEB-INF/content/res/templet/hr-change-base.jsp(92,9) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f17.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f17 = _jspx_th_s_005fcheckbox_005f17.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f17);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f17);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f18(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f18 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f18.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f18.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-base.jsp(93,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f18.setName("templateBean.adjustItem8");
    // /WEB-INF/content/res/templet/hr-change-base.jsp(93,9) null
    _jspx_th_s_005fcheckbox_005f18.setDynamicAttribute(null, "popupValue", new String(""));
    // /WEB-INF/content/res/templet/hr-change-base.jsp(93,9) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f18.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f18 = _jspx_th_s_005fcheckbox_005f18.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f18);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f18);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f19(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f19 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f19.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f19.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-base.jsp(94,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f19.setName("templateBean.adjustItem12");
    // /WEB-INF/content/res/templet/hr-change-base.jsp(94,9) null
    _jspx_th_s_005fcheckbox_005f19.setDynamicAttribute(null, "popupValue", new String("主动离职，请上传《辞职申请报告》；s被动离职，请上传《员工调岗培训或辞退建议表》、《考核表》"));
    // /WEB-INF/content/res/templet/hr-change-base.jsp(94,9) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f19.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f19 = _jspx_th_s_005fcheckbox_005f19.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f19);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f19);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f20(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f20 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f20.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f20.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-base.jsp(97,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f20.setName("templateBean.adjustItem11");
    // /WEB-INF/content/res/templet/hr-change-base.jsp(97,9) null
    _jspx_th_s_005fcheckbox_005f20.setDynamicAttribute(null, "popupValue", new String("请上传\"人员简历\"、\"职位申请表\""));
    // /WEB-INF/content/res/templet/hr-change-base.jsp(97,9) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f20.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f20 = _jspx_th_s_005fcheckbox_005f20.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f20);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f20);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f21(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f21 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f21.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f21.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-base.jsp(98,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f21.setName("templateBean.adjustItem9");
    // /WEB-INF/content/res/templet/hr-change-base.jsp(98,9) null
    _jspx_th_s_005fcheckbox_005f21.setDynamicAttribute(null, "popupValue", new String(""));
    // /WEB-INF/content/res/templet/hr-change-base.jsp(98,9) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f21.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f21 = _jspx_th_s_005fcheckbox_005f21.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f21);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f21);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f22(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f22 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f22.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f22.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-base.jsp(99,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f22.setName("templateBean.adjustItem10");
    // /WEB-INF/content/res/templet/hr-change-base.jsp(99,9) null
    _jspx_th_s_005fcheckbox_005f22.setDynamicAttribute(null, "popupValue", new String(""));
    // /WEB-INF/content/res/templet/hr-change-base.jsp(99,9) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f22.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f22 = _jspx_th_s_005fcheckbox_005f22.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f22);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f22);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f23(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f23 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f23.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f23.setParent(null);
    // /WEB-INF/content/res/templet/hr-change-base.jsp(100,21) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f23.setName("templateBean.adjustItem7");
    // /WEB-INF/content/res/templet/hr-change-base.jsp(100,21) null
    _jspx_th_s_005fcheckbox_005f23.setDynamicAttribute(null, "popupValue", new String(""));
    // /WEB-INF/content/res/templet/hr-change-base.jsp(100,21) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f23.setCssClass("group");
    int _jspx_eval_s_005fcheckbox_005f23 = _jspx_th_s_005fcheckbox_005f23.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f23);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fpopupValue_005fname_005fcssClass_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f23);
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
    // /common/taglibs.jsp(8,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setVar("ctx");
    // /common/taglibs.jsp(8,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fset_005f2 = _jspx_th_c_005fset_005f2.doStartTag();
    if (_jspx_th_c_005fset_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f2);
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
    // /WEB-INF/content/res/templet/template-footer.jsp(4,0) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f2.setTest("#isMy!=1");
    int _jspx_eval_s_005fif_005f2 = _jspx_th_s_005fif_005f2.doStartTag();
    if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t<script type=\"text/javascript\">\r\n");
        out.write("\t\tdisapledAll();\r\n");
        out.write("\t\taddClickAction('',false);\r\n");
        out.write("\t\taddRequireCss(false);\r\n");
        out.write("\t</script>\r\n");
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
        out.write("\r\n");
        out.write("\t<script type=\"text/javascript\">\r\n");
        out.write("\t\taddClickAction('',true);\r\n");
        out.write("\t\taddRequireCss(true);\r\n");
        out.write("\t</script>\r\n");
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
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f3 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f3.setParent(null);
    // /WEB-INF/content/res/templet/template-footer.jsp(18,0) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f3.setTest("(statusCd!=0) && (statusCd!=3)");
    int _jspx_eval_s_005fif_005f3 = _jspx_th_s_005fif_005f3.doStartTag();
    if (_jspx_eval_s_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f3.doInitBody();
      }
      do {
        out.write('\r');
        out.write('\n');
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
        out.write("\r\n");
        out.write("\r\n");
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

  private boolean _jspx_meth_s_005fif_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f4 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f4.setParent(null);
    // /WEB-INF/content/res/templet/template-footer.jsp(29,0) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f4.setTest("(statusCd!=0) && (statusCd!=3)");
    int _jspx_eval_s_005fif_005f4 = _jspx_th_s_005fif_005f4.doStartTag();
    if (_jspx_eval_s_005fif_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f4.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t<script type=\"text/javascript\">\r\n");
        out.write("\t\t// for printing , replace textarea with div\t\r\n");
        out.write("\t\t$('.contentTextArea').filter(\"[edit!=true]\").each(function(i){\r\n");
        out.write("\t\t\tvar str = $(this)[0].value;\r\n");
        out.write("\t\t\tstr = str.replace(/\\r\\n/g,'<br/>');\r\n");
        out.write("\t\t\t$(this).replaceWith('<div class='+'\"inputBorder_readOnly\"'+'><pre style=\"white-space:pre-wrap;\">'+str+'</pre></div>');\t \t\r\n");
        out.write("\t\t});\r\n");
        out.write("\t\t$('.xheditor-simple').filter(\"[edit!=true]\").each(function(i){\r\n");
        out.write("\t\t\tvar str = $(this)[0].value;\r\n");
        out.write("\t\t\t$(this).replaceWith('<div class=\"orgDiv\"><pre style=\"white-space:pre-wrap;\">'+str+'</pre></div>');\t \t\r\n");
        out.write("\t\t});\r\n");
        out.write("\t\t");
        out.write("\r\n");
        out.write("\t</script>\r\n");
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

  private boolean _jspx_meth_s_005felse_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:else
    org.apache.struts2.views.jsp.ElseTag _jspx_th_s_005felse_005f4 = (org.apache.struts2.views.jsp.ElseTag) _005fjspx_005ftagPool_005fs_005felse.get(org.apache.struts2.views.jsp.ElseTag.class);
    _jspx_th_s_005felse_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005felse_005f4.setParent(null);
    int _jspx_eval_s_005felse_005f4 = _jspx_th_s_005felse_005f4.doStartTag();
    if (_jspx_eval_s_005felse_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felse_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felse_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felse_005f4.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("<script type=\"text/javascript\">\r\n");
        out.write("parseTextArea();\r\n");
        out.write("</script>\r\n");
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
}
