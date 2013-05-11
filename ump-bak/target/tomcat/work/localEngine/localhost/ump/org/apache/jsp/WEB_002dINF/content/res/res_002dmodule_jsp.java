package org.apache.jsp.WEB_002dINF.content.res;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class res_002dmodule_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(2);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/WEB-INF/PowerDesk-tags.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssStyle_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005femptyOption_005fcssStyle_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssStyle_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005femptyOption_005fcssStyle_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssStyle_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005femptyOption_005fcssStyle_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fnobody.release();
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
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html\" />\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/desk/res-common.css\" type=\"text/css\" />\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/resApprove.css\" type=\"text/css\" />\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/TreePanel.css\" type=\"text/css\" />\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/prompt/skin/custom_1/ymPrompt.css\" /> \r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/table.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/common.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/TreePanel.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/prompt/ymPrompt.js\"></script>\t\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.form.pack.js\"></script>\r\n");
      out.write("\t\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/gt-grid/gt_grid.css\" />\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/gt-grid/skin/vista/skinstyle.css\" />\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/gt-grid/skin/china/skinstyle.css\" />\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/gt-grid/skin/mac/skinstyle.css\" />\r\n");
      out.write("\t\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/gt-grid/gt_msg_cn.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/gt-grid/gt_grid_all.js\"></script>\r\n");
      out.write("\t\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.quickSearch.js\"></script>\r\n");
      out.write("\t\r\n");
      out.write("\t<title>权责信息配置</title>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\t<table border=\"0\" cellpadding=\"0\" style=\"width:100%;\">\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t<td style=\"width:300px;\" valign=\"top\">\r\n");
      out.write("\t\t<div style=\"margin:10px;\">\r\n");
      out.write("\t\t<input type=\"button\" value=\"网上审批\" onclick=\"changeModuleType('0');\"/>\r\n");
      out.write("\t\t<input type=\"button\" value=\"合理化建议\" onclick=\"changeModuleType('1');\"/>\r\n");
      out.write("\t\t<input type=\"button\" value=\"特别审批\" onclick=\"changeModuleType('2');\"/>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div id=\"searchApproveFix\" style=\"border-style:solid;border-width:1px; border-color:#BFBFBF;margin:10px;\">\r\n");
      out.write("\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%;\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input  value=\"搜索表单...\" \r\n");
      out.write("\t\t\t\t\t\t\t\ttype=\"text\" \r\n");
      out.write("\t\t\t\t\t\t\t\tstyle=\"padding:2px;border:0;font-size: 12px;color: #CCCCCC;width:100%;\"\r\n");
      out.write("\t\t\t\t\t\t\t\tonkeyup=\"searchTreeNode(this)\"\r\n");
      out.write("\t\t\t\t\t\t\t\tonblur=\"resetSearchApproveInput(this);\"\r\n");
      out.write("\t\t\t\t\t\t\t\tonclick=\"clearSearchApproveInput(this);\"\r\n");
      out.write("\t\t\t\t\t\t/>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td style=\"width:56px;\">\r\n");
      out.write("\t\t\t\t\t\t<div id=\"inputSearchOperate\" class=\"searchNextNoActive\">&nbsp;</div>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"divTree\"  id =\"tree-div\" style=\"overflow-y:auto;margin-left:10px;border:1px solid #BFBFBF;\"></div>\r\n");
      out.write("\t</td>\r\n");
      out.write("\t<td style=\"padding:10px\" valign=\"top\">\r\n");
      out.write("\t\t<fieldset>\r\n");
      out.write("\t\t\t<div style=\"margin: 10px 0px;width:100%;\">\r\n");
      out.write("\t\t\t\t<button class=\"buttom\" style=\"height: 25px; width: 80px;\" onclick=\"refreshResModuleTree()\" class=\"buttom\">刷新表单</button>\r\n");
      out.write("\t\t\t\t<button class=\"buttom\" style=\"height: 25px; width: 80px;\" onclick=\"showNew('newModuleDiv',300,300);\">新增模块</button>\r\n");
      out.write("\t\t\t\t<button class=\"buttom\" id=\"newTypeBtnId\" style=\"height: 25px; width: 120px;\" onclick=\"showNew('newAuthTypeDiv',300,300);\" disabled=\"disabled\">新增分类(表单)</button>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<span style=\"padding-left:20px;font-weight: bold; color:red;\" id=\"succesedInfo\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</fieldset>\r\n");
      out.write("\t\t<div >\r\n");
      out.write("\t\t<div id=\"content\" style=\"line-height: 25px;height: 25px;\">友情提示: 双击模块可以直接对模块进行编辑</div>\r\n");
      out.write("\t\t<div>\r\n");
      out.write("\t\t\t<div id=\"newModuleDiv\" style=\"display: none\">\r\n");
      out.write("\t\t\t\t<form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/res/res-module!saveModule.action\" id=\"newModuleForm\" method=\"post\">\r\n");
      out.write("\t\t\t\t\t<table style=\"width:95%;\">\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td align=\"right\" style=\"width:80px;\" valign=\"top\">上级模块：</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td valign=\"top\" style=\"line-height: 18px;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div id=\"parentModuleNameId\"></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"hidden\" id=\"parentModuleCdId\" name=\"resModule.parentModuleCd\" value=\"0\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td align=\"right\">序号：</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td><input type=\"text\" name=\"resModule.sequenceNo\" /></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td align=\"right\">模块代码：</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" name=\"resModule.moduleCd\"  onkeyup=\"validateCd('',this,'module')\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td align=\"right\">模块名称：</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td><input type=\"text\" name=\"resModule.moduleName\" /></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td align=\"right\">模块类别：</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fselect_005f0(_jspx_page_context))
        return;
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td align=\"right\">是否有效：</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td align=\"left\" >");
      if (_jspx_meth_s_005fcheckbox_005f0(_jspx_page_context))
        return;
      out.write(" </td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"2\" align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<button class=\"buttom\" type=\"button\" onclick=\"save(this)\">保存</button>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div id=\"newAuthTypeDiv\" style=\"display: none\">\r\n");
      out.write("\t\t\t\t<form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/res/res-module!save.action\" id=\"newAuthTypeForm\" method=\"post\">\r\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_s_005fhidden_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t<table style=\"width:95%;\">\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td align=\"right\" style=\"width:80px\">序号：</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td><input style=\"width:100%;\" type=\"text\" name=\"sequenceNo\" /></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td align=\"right\">分类代码：</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<input style=\"width:100%;\" type=\"text\" name=\"authTypeCd\"  onblur=\"validateCd('',this,'type')\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td align=\"right\">分类名称：</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td><input style=\"width:100%;\" type=\"text\" name=\"authTypeName\" /></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td align=\"right\">显示名称：</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td><input style=\"width:100%;\" type=\"text\" name=\"displayName\" /></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td align=\"right\">模板：</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fselect_005f1(_jspx_page_context))
        return;
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td align=\"right\">是否发布：</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f1(_jspx_page_context))
        return;
      out.write(" </td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"2\" align=\"center\"><button class=\"buttom\" type=\"button\" onclick=\"save(this)\">保存</button></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t//加载页面功能树\r\n");
      out.write("\tvar treePanel;\r\n");
      out.write("\tvar curNode;\r\n");
      out.write("\tfunction changeModuleType(moduleTypeCd){\r\n");
      out.write("\t\t//0-网上审批，1-合理化建议，2-特别申请\r\n");
      out.write("\t\trefreshResModuleTree(moduleTypeCd);\r\n");
      out.write("\t}\r\n");
      out.write("\tchangeModuleType('0');\r\n");
      out.write("\t//刷新树\r\n");
      out.write("\tfunction refreshResModuleTree(moduleTypeCd){\r\n");
      out.write("\t\t$(\"#tree-div\").html('<div><image src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/loading.gif\"/>加载数据...</div>');\r\n");
      out.write("\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/res/res-approve-info!buildTree.action\", {moduleTypeCd:moduleTypeCd},function(result) {\r\n");
      out.write("\t\t\t$(\"#tree-div\").empty();\r\n");
      out.write("\t\t\tif (result) {\r\n");
      out.write("\t\t\t\tvar arr=eval('('+result+')');\r\n");
      out.write("\t\t\t\troot=arr;\r\n");
      out.write("\t\t\t\ttreePanel = new TreePanel({\r\n");
      out.write("\t\t\t\t\trenderTo:'tree-div',\r\n");
      out.write("\t\t\t\t\t'root' : root,\r\n");
      out.write("\t\t\t\t\t'ctx':'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("'\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t\t\ttreePanel.delegateOnDblClick = function(node){\r\n");
      out.write("\t\t\t\t\tif(node.attributes.nodeType == \"module\"){\r\n");
      out.write("\t\t\t\t\t\t//curNode = node;\r\n");
      out.write("\t\t\t\t\t\tvar id = node.attributes.id;\r\n");
      out.write("\t\t\t\t\t\tymPrompt.win({\r\n");
      out.write("\t\t\t\t\t\t\ttitle:'编辑模块',\r\n");
      out.write("\t\t\t\t\t\t\tfixPosition:true,\r\n");
      out.write("\t\t\t\t\t\t\twidth:250,\r\n");
      out.write("\t\t\t\t\t\t\theight:280,\r\n");
      out.write("\t\t\t\t\t\t\tallowRightMenu:true,\r\n");
      out.write("\t\t\t\t\t\t\tshowMask : false,\r\n");
      out.write("\t\t\t\t\t\t\tmessage:\"<div id='popEditDiv'><img align='absMiddle' src='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/loading.gif'></div>\",\r\n");
      out.write("\t\t\t\t\t\t\tafterShow:function(){\r\n");
      out.write("\t\t\t\t\t\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/res/res-module!edit.action\",{id:id},function(result){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$(\"#popEditDiv\").html(result);\r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t};\r\n");
      out.write("\t\t\t\ttreePanel.render();\r\n");
      out.write("\t\t\t\ttreePanel.on(function(node){\r\n");
      out.write("\t\t\t\t\t//模块\r\n");
      out.write("\t\t\t\t\tif(node.attributes.nodeType == \"module\"){\r\n");
      out.write("\t\t\t\t\t\tif(node.isExpand){\r\n");
      out.write("\t\t\t\t\t\t\tnode.collapse();\r\n");
      out.write("\t\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t\tnode.expand();\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t$(\"#parentModuleNameId\").text(node.attributes.text);\r\n");
      out.write("\t\t\t\t\t\t$(\"#parentModuleCdId\").val(node.attributes.entityCd);\r\n");
      out.write("\t\t\t\t\t\t$(\"#newTypeBtnId\").attr(\"disabled\",false);\r\n");
      out.write("\t\t\t\t\t\t$(\"#resModuleIdHidden\").val(node.attributes.id);\r\n");
      out.write("\t\t\t\t\t\t$(\"#content\").html(\"<div style='color:red;'>友情提示:双击模块可以直接对模块进行编辑<div>\");\r\n");
      out.write("\t\t\t\t\t}else if(node.attributes.nodeType == \"authType\"){ //分类\r\n");
      out.write("\t\t\t\t\t\tvar itemId=node.attributes.id;\r\n");
      out.write("\t\t\t\t\t\t$(\"#newTypeBtnId\").attr(\"disabled\",false);\r\n");
      out.write("\t\t\t\t\t\t$(\"#resModuleIdHidden\").val(node.parentNode.attributes.id);\r\n");
      out.write("\t\t\t\t\t\t$(\"#content\").addClass(\"waiting\");\r\n");
      out.write("\t\t\t\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/res/res-module!input.action\",{id:itemId},function(result) {\r\n");
      out.write("\t\t\t\t\t\t\t $(\"#content\").empty().html(result).removeClass(\"waiting\");\r\n");
      out.write("\t\t\t\t\t\t\t msgGrid.render();\r\n");
      out.write("\t\t\t\t\t\t\t conditionGrid.render();\r\n");
      out.write("\t\t\t\t\t\t\t stepGrid.render();\r\n");
      out.write("\t\t\t\t\t\t\t centerGrid.render();\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tcurNode = node;\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t/**\r\n");
      out.write("\t* 弹出新增框\r\n");
      out.write("\t**/\r\n");
      out.write("\tfunction showNew(divId,height,width){\r\n");
      out.write("\t\tymPrompt.win({\r\n");
      out.write("\t\t\ttitle:'新增',\r\n");
      out.write("\t\t\tfixPosition:true,\r\n");
      out.write("\t\t\tallowRightMenu:true,\r\n");
      out.write("\t\t\twidth:width||200,\r\n");
      out.write("\t\t\theight:height,\r\n");
      out.write("\t\t\tshowMask : false,\r\n");
      out.write("\t\t\tmessage:$(\"#\"+divId).html()\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t/**\r\n");
      out.write("\t* 新增保存 - 模块|分类 |步骤|权限\r\n");
      out.write("\t**/\r\n");
      out.write("\tfunction save(dom){\r\n");
      out.write("\t\tvar $form = $(dom).parents(\"form\");\r\n");
      out.write("\t\tif($(\".error\",$form).length>0){\r\n");
      out.write("\t\t\t$(\".error\",$form).focus();\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tvar formId = $form.attr(\"id\");\r\n");
      out.write("\t\t$form.ajaxSubmit(function(result){\r\n");
      out.write("\t\t\tif(formId == \"newContionForm\"){\r\n");
      out.write("\t\t\t\tvar idValue = $('#resAuthTypeIdHidden').val();\r\n");
      out.write("\t\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/res/res-module!input.action\",{id:idValue},function(result) {\r\n");
      out.write("\t\t\t\t\tymPrompt.close();\r\n");
      out.write("\t\t\t\t\t$(\"#content\").html(result);\r\n");
      out.write("\t\t\t\t\t $(\"#conditionTable tbody tr:first td:first\").trigger(\"click\");\r\n");
      out.write("\t\t\t\t\tshowSuccesedInfo(\"保存成功!\");\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}else if(formId == \"newApproveStepForm\"){\r\n");
      out.write("\t\t\t\tvar idValue = $('#resConditionIdHidden').val();\r\n");
      out.write("\t\t\t\tymPrompt.close();\r\n");
      out.write("\t\t\t\tshowSteps(idValue);\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\tlocation.href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/res/res-module.action\";\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t/**\r\n");
      out.write("\t* 编辑模块-保存\r\n");
      out.write("\t**/\r\n");
      out.write("\tfunction saveModulePop(){\r\n");
      out.write("\t\tif($(\".error\",\"#popModuleForm\").length>0)return;\r\n");
      out.write("\t\t$(\"#popModuleForm\").ajaxSubmit(function(){\r\n");
      out.write("\t\t\t//curNode.attributes.text = $(\"#popModuleName\").val();\r\n");
      out.write("\t\t\t//curNode.paintText();\r\n");
      out.write("\t\t\t//ymPrompt.close();\r\n");
      out.write("\t\t\twindow.location.href=window.location.href;\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t/**\r\n");
      out.write("\t* 编辑分类-保存\r\n");
      out.write("\t**/\r\n");
      out.write("\tfunction saveType(dom){\r\n");
      out.write("\t\tvar $form = $(dom).parents(\"form\");\r\n");
      out.write("\t\tif($(\".error\",$form).length>0){\r\n");
      out.write("\t\t\t$(\".error\",$form).focus();\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$form.ajaxSubmit(function(result){\r\n");
      out.write("\t\t\t//curNode.attributes.text = $(\"#authTypeEditId\").val();\r\n");
      out.write("\t\t\t//curNode.paintText();\r\n");
      out.write("\t\t\tshowSuccesedInfo(\"保存成功!\");\r\n");
      out.write("\t\t\t//window.location.href=window.location.href;\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction editRow(id){\r\n");
      out.write("\t\t$(\"#main_\"+id).hide();\r\n");
      out.write("\t\t$(\"#edit_\"+id).show();\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction cancelRow(id){\r\n");
      out.write("\t\t$(\"#main_\"+id).show();\r\n");
      out.write("\t\t$(\"#edit_\"+id).hide();\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t/**\r\n");
      out.write("\t*  编辑权限-保存\r\n");
      out.write("\t**/\r\n");
      out.write("\tfunction saveCondition(id){\r\n");
      out.write("\t\tvar conditionCd = $(\"#edit_\"+id+\" input[name='conditionCd']\").val();\r\n");
      out.write("\t\tvar conditionName = $(\"#edit_\"+id+\" input[name='conditionName']\").val();\r\n");
      out.write("\t\tvar conditionValue = $(\"#edit_\"+id+\" input[name='conditionValue']\").val();\r\n");
      out.write("\t\tvar param = {\r\n");
      out.write("\t\t\t'id':id,\r\n");
      out.write("\t\t\t'resConditonType.conditionCd':conditionCd,\r\n");
      out.write("\t\t\t'resConditonType.conditionName':conditionName,\r\n");
      out.write("\t\t\t'resConditonType.conditionValue':conditionValue\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t};\r\n");
      out.write("\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/res/res-module!saveCondition.action\",param,function(result){\r\n");
      out.write("\t\t\t$(\"#main_\"+id+\" td[name='conditionCd']\").text(conditionCd);\r\n");
      out.write("\t\t\t$(\"#main_\"+id+\" td[name='conditionName']\").text(conditionName);\r\n");
      out.write("\t\t\t$(\"#main_\"+id+\" td[name='conditionValue']\").text(conditionValue);\r\n");
      out.write("\t\t\t$(\"#main_\"+id).show();\r\n");
      out.write("\t\t\t$(\"#edit_\"+id).hide();\r\n");
      out.write("\t\t\tshowSuccesedInfo(\"保存成功\");\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t/**\r\n");
      out.write("\t*  编辑步骤-保存\r\n");
      out.write("\t**/\r\n");
      out.write("\tfunction saveStep(id){\r\n");
      out.write("\t\tvar $nodeCd = $(\"#edit_\"+id+\" select[name='nodeCd']\");\r\n");
      out.write("\t\tvar $approveLevel = $(\"#edit_\"+id+\" input[name='approveLevel']\");\r\n");
      out.write("\t\tvar nodeCd = $nodeCd.val();\r\n");
      out.write("\t\tvar approveLevel = $approveLevel.val();\r\n");
      out.write("\t\tvar nodeText = $nodeCd.find(\"option:selected\").text();\r\n");
      out.write("\t\tvar levelText = $approveLevel.val();\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar param = {\r\n");
      out.write("\t\t\t'id':id,\r\n");
      out.write("\t\t\t'resApproveStep.nodeCd':nodeCd,\r\n");
      out.write("\t\t\t'resApproveStep.approveLevel':approveLevel\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t};\r\n");
      out.write("\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/res/res-module!saveStep.action\",param,function(result){\r\n");
      out.write("\t\t\t$(\"#main_\"+id+\" td[name='nodeCd']\").text(nodeText);\r\n");
      out.write("\t\t\t$(\"#main_\"+id+\" td[name='approveLevel']\").text(levelText);\r\n");
      out.write("\t\t\t$(\"#main_\"+id).show();\r\n");
      out.write("\t\t\t$(\"#edit_\"+id).hide();\r\n");
      out.write("\t\t\tshowSuccesedInfo(\"保存成功\");\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t/**\r\n");
      out.write("\t* 删除模块\r\n");
      out.write("\t**/\r\n");
      out.write("\tfunction delModule(moduleId){\r\n");
      out.write("\t\tif(!confirm(\"确认要删除该模块吗？该操作同时会删除当前模块下所有的分类？\"))return;\r\n");
      out.write("\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/res/res-module!delModule.action\",{id:moduleId},function(result){\r\n");
      out.write("\t\t\tlocation.href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/res/res-module.action\";\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t/**\r\n");
      out.write("\t* 删除分类\r\n");
      out.write("\t**/\r\n");
      out.write("\tfunction delType(typeId){\r\n");
      out.write("\t\tif(!confirm(\"确认要删除该分类吗？该操作同时会删除当前分类下所有的权限\"))return;\r\n");
      out.write("\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/res/res-module!delete.action\",{id:typeId},function(result){\r\n");
      out.write("\t\t\tlocation.href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/res/res-module.action\";\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t/**\r\n");
      out.write("\t* 删除权限\r\n");
      out.write("\t**/\r\n");
      out.write("\tfunction delCondition(conditionId){\r\n");
      out.write("\t\tif(!confirm(\"确认要删除该权限吗？该操作同时会删除当前权限下所有的步骤\"))return;\r\n");
      out.write("\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/res/res-module!delCondition.action\",{id:conditionId},function(result){\r\n");
      out.write("\t\t\t$(\"#main_\"+conditionId).remove();\r\n");
      out.write("\t\t\t$(\"#edit_\"+conditionId).remove();\r\n");
      out.write("\t\t\t$(\"#resStepGridId\").html(\"请点击一条权限记录\");\r\n");
      out.write("\t\t\tshowSuccesedInfo(\"删除成功!\");\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t/**\r\n");
      out.write("\t* 删除步骤\r\n");
      out.write("\t**/\r\n");
      out.write("\tfunction delStep(stepId){\r\n");
      out.write("\t\tif(!confirm(\"确认要删除该步骤吗？\"))return;\r\n");
      out.write("\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/res/res-module!delStep.action\",{id:stepId},function(result){\r\n");
      out.write("\t\t\t$(\"#main_\"+stepId).remove();\r\n");
      out.write("\t\t\t$(\"#edit_\"+stepId).remove();\r\n");
      out.write("\t\t\tshowSuccesedInfo(\"删除成功!\");\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction showSteps(id){\r\n");
      out.write("\t\tvar $grid = $(\"#resStepGridId\");\r\n");
      out.write("\t\t$grid.addClass(\"waiting\");\r\n");
      out.write("\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/res/res-module!showSteps.action\",{id:id},function(result){\r\n");
      out.write("\t\t\t$(\"#main_\"+id).addClass(\"trHilight\").siblings().removeClass(\"trHilight\");\r\n");
      out.write("\t\t\t$grid.html(result).removeClass(\"waiting\");\r\n");
      out.write("\t\t\tshowSuccesedInfo(\"加载成功!\");\r\n");
      out.write("\t\t\t$(\"#newStepBtnId\").attr(\"disabled\",false);\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t/**\r\n");
      out.write("\t* 显示成功信息\r\n");
      out.write("\t**/\r\n");
      out.write("\tfunction showSuccesedInfo(text){\r\n");
      out.write("\t\t$(\"#succesedInfo\").text(text);\r\n");
      out.write("\t\tsetTimeout(function(){\r\n");
      out.write("\t\t\t$(\"#succesedInfo\").text(\"\");\r\n");
      out.write("\t\t}, 3000);\r\n");
      out.write("\t}\r\n");
      out.write("\t/**\r\n");
      out.write("\t* 验证模块代码或者分类代码是否已经存在\r\n");
      out.write("\t**/\r\n");
      out.write("\tvar timeOut;\r\n");
      out.write("\tfunction validateCd(oldCd,dom,type){\r\n");
      out.write("\t\tif(timeOut)clearTimeout(timeOut);\r\n");
      out.write("\t\ttimeOut = setTimeout(function(){\r\n");
      out.write("\t\t\tvar newCd = $(dom).val();\r\n");
      out.write("\t\t\tvar $form = $(dom).parents(\"form\"); \r\n");
      out.write("\t\t\tvar action = $form.attr(\"action\");\r\n");
      out.write("\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/res/res-module!checkCd.action\",{oldCd:oldCd,newCd:newCd,type:type},function(result){\r\n");
      out.write("\t\t\t\tif(eval(result)){\r\n");
      out.write("\t\t\t\t\t$(dom).removeClass(\"error\").removeAttr(\"title\");\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$(dom).addClass(\"error\").attr(\"title\",\"已经存在\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}, 500);\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t//\r\n");
      out.write("\t\t//$(\"#item_3_8_frame\",window.parent.document).css(\"height\",\"90%\");\r\n");
      out.write("\t});\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t//搜索定位表单\r\n");
      out.write("\tvar curVal = null;\r\n");
      out.write("\tvar curNode = null;\r\n");
      out.write("\r\n");
      out.write("\tvar searchTreeManager;\r\n");
      out.write("\tfunction searchTreeNode(dom){\r\n");
      out.write("\t\tif(searchTreeManager)clearTimeout(searchTreeManager);\r\n");
      out.write("\t\tsearchTreeManager = setTimeout(function(){\r\n");
      out.write("\t\t\tprocessSearch(dom);\r\n");
      out.write("\t\t}, 300);\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction processSearch(dom){\r\n");
      out.write("\t\tif($(dom).val().trim() == ''){\r\n");
      out.write("\t\t\t$('#inputSearchOperate').removeClass('searchNextActive').addClass('searchNextNoActive');\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\t$('#inputSearchOperate').removeClass('searchNextNoActive').addClass('searchNextActive');\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(dom).css({color:\"#5A5A5A\"});\r\n");
      out.write(" \t\tif($(dom).val().trim() == curVal){\r\n");
      out.write("\t\t\t//NONE\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tcurVal = $(dom).val().trim();\r\n");
      out.write("\t\t\tcurNode = null;\r\n");
      out.write("\t\t}\r\n");
      out.write(" \t\tcurNode = treePanel.searchNode(curVal, curNode);\r\n");
      out.write(" \t\tif(curNode){\r\n");
      out.write("\t\t\tvar nodes = curNode.getPathNodes();\r\n");
      out.write("\t\t\tfor(var i= 0; i < nodes.length; i++){\r\n");
      out.write("\t\t\t\tnodes[i].expand();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\ttreePanel.setFocusNode(curNode);\r\n");
      out.write("\t\t\tvar nodeDom = curNode['html-element']['text'];\r\n");
      out.write("\t\t\tvar toh = $('#tree-div').height();\r\n");
      out.write("\t\t\tvar top = $('#tree-div')[0].scrollTop;\r\n");
      out.write("\t\t\tvar noh = $(nodeDom).offset().top;\r\n");
      out.write("\t\t\t$('#tree-div').animate({ scrollTop : top+noh-toh }, \"normal\");\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\t$('#inputSearchOperate').removeClass('searchNextActive').addClass('searchNextNoActive');\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction resetSearchApproveInput(dom){\r\n");
      out.write("\t\tif($(dom).val().trim() == ''){\r\n");
      out.write("\t\t\t$(dom).val('搜索表单...');\r\n");
      out.write("\t\t\t$(dom).css({color:\"#E6E6E6\"});\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\t$(dom).css({color:\"#5A5A5A\"});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction clearSearchApproveInput(dom){\r\n");
      out.write("\t\tif( $(dom).val() == '搜索表单...'){\r\n");
      out.write("\t\t\t$(dom).val('');\r\n");
      out.write("\t\t\t$(dom).css({color:\"#5A5A5A\"});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
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

  private boolean _jspx_meth_s_005fselect_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f0 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f0.setParent(null);
    // /WEB-INF/content/res/res-module.jsp(100,11) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setList("mapModuleType");
    // /WEB-INF/content/res/res-module.jsp(100,11) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setListKey("key");
    // /WEB-INF/content/res/res-module.jsp(100,11) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setListValue("value");
    // /WEB-INF/content/res/res-module.jsp(100,11) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setName("resModule.moduleTypeCd");
    int _jspx_eval_s_005fselect_005f0 = _jspx_th_s_005fselect_005f0.doStartTag();
    if (_jspx_th_s_005fselect_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fnobody.reuse(_jspx_th_s_005fselect_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fnobody.reuse(_jspx_th_s_005fselect_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f0 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f0.setParent(null);
    // /WEB-INF/content/res/res-module.jsp(104,25) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f0.setCssStyle("width:20px;");
    // /WEB-INF/content/res/res-module.jsp(104,25) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f0.setName("resModule.active");
    int _jspx_eval_s_005fcheckbox_005f0 = _jspx_th_s_005fcheckbox_005f0.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fhidden_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:hidden
    org.apache.struts2.views.jsp.ui.HiddenTag _jspx_th_s_005fhidden_005f0 = (org.apache.struts2.views.jsp.ui.HiddenTag) _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.get(org.apache.struts2.views.jsp.ui.HiddenTag.class);
    _jspx_th_s_005fhidden_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fhidden_005f0.setParent(null);
    // /WEB-INF/content/res/res-module.jsp(116,5) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fhidden_005f0.setName("resModule.resModuleId");
    // /WEB-INF/content/res/res-module.jsp(116,5) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fhidden_005f0.setId("resModuleIdHidden");
    int _jspx_eval_s_005fhidden_005f0 = _jspx_th_s_005fhidden_005f0.doStartTag();
    if (_jspx_th_s_005fhidden_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.reuse(_jspx_th_s_005fhidden_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.reuse(_jspx_th_s_005fhidden_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fselect_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f1 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005femptyOption_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f1.setParent(null);
    // /WEB-INF/content/res/res-module.jsp(138,11) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setCssStyle("width:100%;");
    // /WEB-INF/content/res/res-module.jsp(138,11) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setList("templetMap");
    // /WEB-INF/content/res/res-module.jsp(138,11) name = emptyOption type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setEmptyOption("true");
    // /WEB-INF/content/res/res-module.jsp(138,11) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setName("templetCd");
    // /WEB-INF/content/res/res-module.jsp(138,11) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setListKey("key");
    // /WEB-INF/content/res/res-module.jsp(138,11) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setListValue("value");
    int _jspx_eval_s_005fselect_005f1 = _jspx_th_s_005fselect_005f1.doStartTag();
    if (_jspx_th_s_005fselect_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005femptyOption_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005femptyOption_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005fcheckbox_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f1 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f1.setParent(null);
    // /WEB-INF/content/res/res-module.jsp(142,11) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f1.setName("publish");
    int _jspx_eval_s_005fcheckbox_005f1 = _jspx_th_s_005fcheckbox_005f1.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f1);
    return false;
  }
}
