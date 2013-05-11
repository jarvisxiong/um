package org.apache.jsp.WEB_002dINF.content.bis;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

public final class bis_002dproject_002dmain_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(4);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/common/global.jsp");
    _jspx_dependants.add("/WEB-INF/content/bis/bis-manage-header.jsp");
    _jspx_dependants.add("/WEB-INF/PowerDesk-tags.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fset_0026_005fvar;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005felse;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fstyle_005frequired_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fcssStyle_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fclass_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fclass_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssClass_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fonfocus_005fonblur_005fname_005fid_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fset_0026_005fvar = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005felse = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fstyle_005frequired_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fcssStyle_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fclass_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fclass_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssClass_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fonfocus_005fonblur_005fname_005fid_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.release();
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fs_005felse.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fstyle_005frequired_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.release();
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fcssStyle_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fclass_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fclass_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssClass_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fonfocus_005fonblur_005fname_005fid_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.release();
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
      out.write("/resources/css/cont/cont.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/bis/bis-project.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/plan/cost-ctrl.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/TreePanel.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/bis/ymPrompt.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/desk/thickbox.css\"/>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.form.pack.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jqueryplugin/chilltip.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.form.pack.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/prompt/ymPrompt.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/desk/MaskLayer.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/common.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/datePicker/WdatePicker.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/validate/formatUtil.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/bis/bis-project.js\"></script>\r\n");
      out.write("    <title>项目基础信息</title>\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("        var isProjectBusinessCompany = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isProjectBusinessCompany}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("    </script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-project!save.action\" method=\"post\" id=\"seaFloorForm\">\r\n");
      out.write("<input type=\"hidden\" name=\"recordVersion\" id=\"recordVersion\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${recordVersion}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("<input type=\"hidden\" name=\"floorType\"/>\r\n");
      out.write("<input type=\"hidden\" id=\"projAdmin\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${projAdmin}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("<input type=\"hidden\" name=\"bisProjectId\"/>\r\n");
      out.write("<input type=\"hidden\" id=\"floorTypeSplit\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${floorType}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("<input type=\"hidden\" id=\"floorNumSplit\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisStore.bisFloor.bisFloorId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write('`');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisStore.bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("<input type=\"hidden\" name=\"bisFloorId\"/>\r\n");
      if (_jspx_meth_s_005fselect_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_s_005fselect_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_s_005fset_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
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
      out.write("\r\n");
      if (_jspx_meth_s_005fset_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"title_bar\">\r\n");
      out.write("    <div style=\"font-weight:bold;padding-left:8px;padding-right:8px; font-size:14px;float:left;\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${titleName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</div>\r\n");
      out.write("    ");
      if (_jspx_meth_s_005fif_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("    ");
      if (_jspx_meth_s_005fif_005f3(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("    ");
      //  s:if
      org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f4 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
      _jspx_th_s_005fif_005f4.setPageContext(_jspx_page_context);
      _jspx_th_s_005fif_005f4.setParent(null);
      // /WEB-INF/content/bis/bis-manage-header.jsp(49,4) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_s_005fif_005f4.setTest("#module==7");
      int _jspx_eval_s_005fif_005f4 = _jspx_th_s_005fif_005f4.doStartTag();
      if (_jspx_eval_s_005fif_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_s_005fif_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_s_005fif_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_s_005fif_005f4.doInitBody();
        }
        do {
          out.write("\r\n");
          out.write("        ");
          if (_jspx_meth_security_005fauthorize_005f0(_jspx_th_s_005fif_005f4, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("        ");
          if (_jspx_meth_security_005fauthorize_005f1(_jspx_th_s_005fif_005f4, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("        ");
          //  s:set
          org.apache.struts2.views.jsp.SetTag _jspx_th_s_005fset_005f2 = (org.apache.struts2.views.jsp.SetTag) _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.get(org.apache.struts2.views.jsp.SetTag.class);
          _jspx_th_s_005fset_005f2.setPageContext(_jspx_page_context);
          _jspx_th_s_005fset_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f4);
          // /WEB-INF/content/bis/bis-manage-header.jsp(57,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_s_005fset_005f2.setVar("curUserCd");
          int _jspx_eval_s_005fset_005f2 = _jspx_th_s_005fset_005f2.doStartTag();
          if (_jspx_eval_s_005fset_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_s_005fset_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_s_005fset_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_s_005fset_005f2.doInitBody();
            }
            do {
              out.print(SpringSecurityUtils.getCurrentUiid());
              out.write("\r\n");
              out.write("        ");
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
          out.write("        ");
          if (_jspx_meth_s_005fif_005f5(_jspx_th_s_005fif_005f4, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("        ");
          if (_jspx_meth_s_005fif_005f6(_jspx_th_s_005fif_005f4, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("    ");
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
        return;
      }
      _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f4);
      out.write("\r\n");
      out.write("    ");
      if (_jspx_meth_s_005fif_005f7(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            <div style=\"float:right; height:26px; margin-right:5px; margin-top:6px;\">\r\n");
      out.write("                <div class=\"btn_green\" onclick=\"clkFullScreen('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("');\">全屏</div>\r\n");
      out.write("            </div>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"div_title\" style=\"height: 30px;\">\r\n");
      out.write("<div style=\"margin:0 0px;\">\r\n");
      if (_jspx_meth_s_005fif_005f8(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_s_005felseif_005f6(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_s_005felseif_005f7(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_s_005felseif_005f8(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_s_005felseif_005f9(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_s_005felseif_005f10(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"seniorSearchDiv\"\r\n");
      out.write("     style=\"position:absolute; width:650px; height:130px; top:31px; left:8px; display:none; background-color:#fff; border:1px solid #000; padding:10px 20px 10px 0px; z-index:8;\">\r\n");
      out.write("    <table class=\"tb_search\" cellpadding=\"0\" cellspacing=\"0\">\r\n");
      out.write("        <col width=\"80\"/>\r\n");
      out.write("        <col width=\"130\"/>\r\n");
      out.write("        <col width=\"80\"/>\r\n");
      out.write("        <col width=\"130\"/>\r\n");
      out.write("        <col width=\"80\"/>\r\n");
      out.write("        <col width=\"130\"/>\r\n");
      out.write("        ");
      if (_jspx_meth_s_005fif_005f17(_jspx_page_context))
        return;
      out.write("\r\n");
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
      out.write("        if (_MODULE == 5) {\r\n");
      out.write("            $('#bisProjectName').onSelect({\r\n");
      out.write("                muti:false,\r\n");
      out.write("                callback:function (project) {\r\n");
      out.write("                    $(\"#bisProjectName\").val(project.projectName);\r\n");
      out.write("                    $(\"#bisProjectId\").val(project.bisProjectId);\r\n");
      out.write("                    filterFloor(project.bisProjectId);\r\n");
      out.write("                }\r\n");
      out.write("            });\r\n");
      out.write("        } else if (_MODULE == 7) {\r\n");
      out.write("            var projAdmin = $('#projAdmin').val();\r\n");
      out.write("            if (projAdmin == 'true') {\r\n");
      out.write("                if (isProjectBusinessCompany == \"false\") {\r\n");
      out.write("                    $('#bisProjectName').onSelect({\r\n");
      out.write("                        muti:false,\r\n");
      out.write("                        callback:function (project) {\r\n");
      out.write("                            $(\"#bisProjectName\").val(project.projectName);\r\n");
      out.write("                            //if($(\"#bisProjectNames\").val()=='') {\r\n");
      out.write("                            //\t$(\"#bisProjectNames\").val(project.bisProjectName);\r\n");
      out.write("                            //}\r\n");
      out.write("                            $(\"#bisProjectId\").val(project.bisProjectId);\r\n");
      out.write("                            filterFloor(project.bisProjectId);\r\n");
      out.write("                        }\r\n");
      out.write("                    });\r\n");
      out.write("                }\r\n");
      out.write("                $('#bisProjectNames').onSelect({\r\n");
      out.write("                    muti:false,\r\n");
      out.write("                    callback:function (project) {\r\n");
      out.write("                        $(\"#bisProjectNames\").val(project.projectName);\r\n");
      out.write("                        $(\"#bisProjectIds\").val(project.bisProjectId);\r\n");
      out.write("                        filterFloors(project.bisProjectId);\r\n");
      out.write("                        if (project.bisProjectId == '40282b8927a42dff0127a435d5c30127') {\r\n");
      out.write("                            $('#luoyanginfo').show();\r\n");
      out.write("                        } else {\r\n");
      out.write("                            $('#luoyanginfo').hide();\r\n");
      out.write("\r\n");
      out.write("                        }\r\n");
      out.write("                    }\r\n");
      out.write("                });\r\n");
      out.write("            }\r\n");
      out.write("        } else if (_MODULE == 9) {\r\n");
      out.write("            //租户\r\n");
      out.write("            $('#filter_EQ_bisProjectName').onSelect({muti:false});\r\n");
      out.write("        } else {\r\n");
      out.write("            $('#bisProjectName').onSelect({muti:false});\r\n");
      out.write("        }\r\n");
      out.write("        $('#btnLayOut').onSelect({\r\n");
      out.write("            muti:false,\r\n");
      out.write("            callback:function (project) {\r\n");
      out.write("                goFloor(project.bisProjectId);\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      if (_jspx_meth_s_005fif_005f18(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div style=\"height:100%;padding-top:10px;border-bottom:1px solid #DDDBDC;background-color:#F7F7F7;display:none;\"\r\n");
      out.write("     id=\"titleAdd\">\r\n");
      out.write("<fieldset style=\"border:0;\">\r\n");
      out.write("<legend style=\"padding-left:10px;background-color: #F7F7F7;\"><font\r\n");
      out.write("        style=\"font-weight:bolder; font-size:14px; color:#fb9032;\">新增</font>\r\n");
      out.write("    <font id=\"luoyanginfo\" style=\"display:none;font-weight: lighter; color: rgb(0, 0, 0);\"></font>\r\n");
      out.write("</legend>\r\n");
      out.write("<div>\r\n");
      out.write("    <table class=\"main_content\" border=\"0\">\r\n");
      out.write("        <col width=\"160px\">\r\n");
      out.write("        <col width=\"150px\">\r\n");
      out.write("        <col width=\"160px\">\r\n");
      out.write("        <col width=\"150px\">\r\n");
      out.write("        <col width=\"160px\">\r\n");
      out.write("        <col width=\"150px\">\r\n");
      out.write("        <col width=\"30px\">\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td align=\"right\">楼宇类型：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <select class=\"required\" id=\"floorTypes\" onchange=\"floTypes(this.value);\">\r\n");
      out.write("                    <option value=\"\">--选择--</option>\r\n");
      out.write("                    <option value=\"1\">商铺</option>\r\n");
      out.write("                    <option value=\"2\">公寓</option>\r\n");
      out.write("                    <option value=\"3\">多经</option>\r\n");
      out.write("                </select>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\">项目：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input class=\"required\" type=\"text\" readonly=\"true\" id=\"bisProjectNames\" name=\"bisProjectName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"\r\n");
      out.write("                       style=\"cursor: pointer; font-size: 12px; color: #ff0000;\"/>\r\n");
      out.write("                <input type=\"hidden\" id=\"bisProjectIds\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\">\r\n");
      out.write("                <span id=\"storeBuildings\">楼层号：</span>\r\n");
      out.write("                <span id=\"flatBuildings\" style=\"display:none;\">楼号：</span>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <span id=\"bisFloorTds\"><select class=\"required\" id=\"bisFloorIds\">\r\n");
      out.write("                    <option>--选择--</option>\r\n");
      out.write("                </select></span>\r\n");
      out.write("\r\n");
      out.write("            </td>\r\n");
      out.write("            <td></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"new_Store\" style=\"display: none;\">\r\n");
      out.write("    <table class=\"main_content\" border=\"0\">\r\n");
      out.write("        <col width=\"160px\">\r\n");
      out.write("        <col width=\"150px\">\r\n");
      out.write("        <col width=\"160px\">\r\n");
      out.write("        <col width=\"150px\">\r\n");
      out.write("        <col width=\"160px\">\r\n");
      out.write("        <col width=\"150px\">\r\n");
      out.write("        <col width=\"30px\">\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td align=\"right\">编号：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type=\"text\" id=\"storeNo\" class=\"required\" name=\"bisStore.storeNo\"\r\n");
      out.write("                       onkeyup=\"storeValidate(this);\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\">建筑面积㎡(图纸)：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type=\"text\" id=\"square\" class=\"required\" alt=\"amount\" name=\"bisStore.square\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\">套内面积㎡(图纸)：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type=\"text\" id=\"innerSquare\" class=\"required\" alt=\"amount\" name=\"bisStore.innerSquare\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td align=\"right\">建筑面积㎡(实测)：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type=\"text\" id=\"square\" alt=\"amount\" name=\"bisStore.squareReal\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\">套内面积㎡(实测)：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type=\"text\" id=\"innerSquare\" alt=\"amount\" name=\"bisStore.innerSquareReal\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\">计租面积㎡：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type=\"text\" id=\"rentSquare\" class=\"required\" alt=\"amount\" name=\"bisStore.rentSquare\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td align=\"right\">销售单价(元/㎡)：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type=\"text\" id=\"sellPrice\" alt=\"amount\" name=\"bisStore.sellPrice\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\">开业日期：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      out.write("\r\n");
      out.write("                <input type=\"text\" name=\"bisStore.openDate\" id=\"openDate\" onfocus=\"WdatePicker()\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\">规划业态：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fselect_005f8(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            </td>\r\n");
      out.write("            <td></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td align=\"right\">租金标准(元/㎡)：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type=\"text\" id=\"rentStandard\" class=\"required\" alt=\"amount\" name=\"bisStore.rentStandard\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\">物业标准(元/㎡)：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type=\"text\" id=\"tenemStandard\" class=\"required\" alt=\"amount\" name=\"bisStore.tenemStandard\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\">产权性质：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fselect_005f9(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            </td>\r\n");
      out.write("            <td></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td align=\"right\">\r\n");
      out.write("                <span class=\"propRight\" style=\"display:none\">业主：</span>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type=\"text\" id=\"owner\" class=\"propRight\" style=\"display:none\" name=\"bisStore.owner\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\">\r\n");
      out.write("                <span class=\"propRight\" style=\"display:none\"> 经营现状：</span>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fselect_005f10(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\">\r\n");
      out.write("                <span class=\"propRight\" style=\"display:none\">是否开业：</span>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <select id=\"ifPractice\" name=\"bisStore.ifPractice\" class=\"propRight\" style=\"display:none\">\r\n");
      out.write("                    <option value=\"\">--选择--</option>\r\n");
      out.write("                    <option value=\"1\">已开业</option>\r\n");
      out.write("                    <option value=\"2\">未开业</option>\r\n");
      out.write("                </select>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td align=\"right\">商铺定位：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fselect_005f11(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td align=\"right\">备注：</td>\r\n");
      out.write("            <td colspan=\"5\"><textarea name=\"bisStore.storeRemark\" id=\"remark\"></textarea></td>\r\n");
      out.write("            <td></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td align=\"right\"><font color=\"red\">注意：</font></td>\r\n");
      out.write("            <td colspan=\"5\"><font color=\"red\">请填写商铺单元如：1001，保存后再填写1002 ；禁止填写商铺集合 如：1001~1005 。</font></td>\r\n");
      out.write("            <td></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"new_Flat\" style=\"display:none;\">\r\n");
      out.write("    <table class=\"main_content\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%;height:100%;\">\r\n");
      out.write("        <col width=\"160px\"/>\r\n");
      out.write("        <col width=\"150px\"/>\r\n");
      out.write("        <col width=\"160px\"/>\r\n");
      out.write("        <col width=\"150px\"/>\r\n");
      out.write("        <col width=\"160px\"/>\r\n");
      out.write("        <col width=\"150px\"/>\r\n");
      out.write("        <col width=\"30px\"/>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td align=\"right\">编号：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input class=\"required\" type=\"text\" id=\"flatNo\" name=\"bisFlat.flatNo\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\">客户名称：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input class=\"required\" type=\"text\" id=\"flat_customerName\" name=\"bisFlat.customerName\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\">住宅底商：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("            \t");
      if (_jspx_meth_s_005fselect_005f12(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            </td>\r\n");
      out.write("            <td></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("        \t<td align=\"right\">建筑面积㎡：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input class=\"required\" type=\"text\" id=\"flat_square\" alt=\"amount\" name=\"bisFlat.square\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\">套内面积㎡：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input class=\"required\" type=\"text\" id=\"flat_innerSquare\" alt=\"amount\" name=\"bisFlat.innerSquare\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("             <td align=\"right\">实测建筑面积㎡：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input class=\"required\" type=\"text\" id=\"flat_actualSquare\" alt=\"amount\" name=\"bisFlat.actualSquare\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("        \t<td align=\"right\">实测套内面积㎡：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input class=\"required\" type=\"text\" id=\"flat_actualInnerSquare\" alt=\"amount\" name=\"bisFlat.actualInnerSquare\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\">月物管费单价(元/月)：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input class=\"required\" type=\"text\" id=\"flat_monPromanfeePrice\" alt=\"amount\" name=\"bisFlat.monPromanfeePrice\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("           \t<td align=\"right\">出售日期：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005ftextfield_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            </td>\r\n");
      out.write("            <td></td>\r\n");
      out.write("            ");
      out.write("\r\n");
      out.write("        </tr>\r\n");
      out.write("        ");
      out.write("\r\n");
      out.write("        <tr>\r\n");
      out.write("            <!--<td align=\"right\">公摊面积㎡：</td>\r\n");
      out.write("                   <td width=\"150\">\r\n");
      out.write("                       <input type=\"text\" id=\"publicSquare\" alt=\"amount\" name=\"bisFlat.publicSquare\" />\r\n");
      out.write("                   </td>\r\n");
      out.write("                   -->\r\n");
      out.write("            <td align=\"right\">房屋结构：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fselect_005f13(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\">规划业态：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                ");
      if (_jspx_meth_s_005fselect_005f14(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            </td>\r\n");
      out.write("            <td colspan=\"2\"></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td align=\"right\">备注：</td>\r\n");
      out.write("            <td colspan=\"3\"><textarea name=\"bisFlat.remark\" id=\"flat_remark\"></textarea></td>\r\n");
      out.write("            <td colspan=\"3\"></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"new_Multi\" style=\"display:none;\">\r\n");
      out.write("    <table class=\"main_content\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%;height:100%;\">\r\n");
      out.write("        <col width=\"160px\">\r\n");
      out.write("        <col width=\"150px\">\r\n");
      out.write("        <col width=\"160px\">\r\n");
      out.write("        <col width=\"150px\">\r\n");
      out.write("        <col width=\"160px\">\r\n");
      out.write("        <col width=\"150px\">\r\n");
      out.write("        <col width=\"30px\">\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td align=\"right\">多经编号：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type=\"text\" id=\"multiName\" name=\"bisMulti.multiName\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\">经营项目：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type=\"text\" id=\"operationProjectCd\" name=\"bisMulti.operationProjectCd\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            ");
      out.write("\r\n");
      out.write("            <td colspan=\"3\"></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td align=\"right\">执行价格：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type=\"text\" id=\"multiPrice\" name=\"bisMulti.multiPrice\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\">面积㎡：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type=\"text\" id=\"multi_square\" alt=\"amount\" name=\"bisMulti.square\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td colspan=\"3\"></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td align=\"right\">多经类型：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <select id=\"multi_type\" name=\"bisMulti.multiType\" onChange=\"changeMulti(this);\">\r\n");
      out.write("                    <option value=\"\"></option>\r\n");
      out.write("                    <option value=\"1\">广告位</option>\r\n");
      out.write("                    <option value=\"2\">多经点位</option>\r\n");
      out.write("                </select>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\" class=\"multFloor\" style=\"display:none\">楼层：</td>\r\n");
      out.write("            <td class=\"multFloor\" style=\"display:none\">\r\n");
      out.write("                <input type=\"text\" id=\"multiFloor\" name=\"bisMulti.multiFloor\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td align=\"right\" class=\"multGrade\" style=\"display:none\">等级：</td>\r\n");
      out.write("            <td class=\"multGrade\" style=\"display:none\">\r\n");
      out.write("                <input type=\"text\" id=\"multiGrade\" name=\"bisMulti.multiGrade\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td colspan=\"3\"></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td align=\"right\">位置区域：</td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type=\"text\" id=\"operationArea\" name=\"bisMulti.operationArea\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td colspan=\"3\"></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td align=\"right\">执行政策：</td>\r\n");
      out.write("            <td colspan=\"3\">\r\n");
      out.write("                <textarea name=\"bisMulti.multiPolicy\" id=\"multiPolicy\"></textarea>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td colspan=\"3\"></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td align=\"right\">备注：</td>\r\n");
      out.write("            <td colspan=\"3\"><textarea name=\"bisMulti.remark\" id=\"multi_remark\"></textarea></td>\r\n");
      out.write("            <td colspan=\"3\"></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("</div>\r\n");
      out.write("<div style=\"padding-top:10px;\">\r\n");
      out.write("    <table class=\"main_content\" border=\"0\">\r\n");
      out.write("        <col width=\"120\"/>\r\n");
      out.write("        <col/>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td></td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input class=\"btn_green\" type=\"button\" onclick=\"saveNewFloor();\" value=\"保存\"/>\r\n");
      out.write("                <input class=\"btn_red\" type=\"button\" onclick=\"cancelAdd();\" value=\"取消\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("</div>\r\n");
      out.write("</fieldset>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"bisProjectFloor\" style=\"padding: 10px 8px 0px;\"></div>\r\n");
      out.write("<div id=\"massageTab\" align=\"center\" style=\"color:#6BAD42;font-size:16px;font-weight:bold;padding-top:100px;\">\r\n");
      out.write("    请选择要搜索的内容！\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</form>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("    $(function () {\r\n");
      out.write("        var floorTypeSplit = $('#floorTypeSplit').val();\r\n");
      out.write("\r\n");
      out.write("        var floorNumSplit = $('#floorNumSplit').val();\r\n");
      out.write("        var bisProjectId = $('#bisProjectId').val();\r\n");
      out.write("\r\n");
      out.write("        if (floorTypeSplit != \"\" && floorNumSplit != \"\") {\r\n");
      out.write("            $('#floorType').val(floorTypeSplit);\r\n");
      out.write("            filterFloor(bisProjectId);\r\n");
      out.write("            $('#bisFloorId').val(floorNumSplit);\r\n");
      out.write("            bisFloorSearch();\r\n");
      out.write("        }\r\n");
      out.write("        //bisFloorSearch();\r\n");
      out.write("        $('input[alt=\"amount\"]').live('keyup', function () {\r\n");
      out.write("            clearNoNum(this);\r\n");
      out.write("            if ($('.percent').val() > 100) {\r\n");
      out.write("                this.value = 0;\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("    });\r\n");
      out.write("    function jumpPage(pageNo) {\r\n");
      out.write("        var floorType = $(\"#floorType\").val();\r\n");
      out.write("        if (floorType == \"3\") {\r\n");
      out.write("            $(\"#pageNo\").val(pageNo);\r\n");
      out.write("            $(\"#seaFloorForm\").attr(\"action\", \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-project!multiList.action\");\r\n");
      out.write("            $(\"#seaFloorForm\").ajaxSubmit(function (result) {\r\n");
      out.write("                $(\"#bisProjectFloor\").html(result);\r\n");
      out.write("            });\r\n");
      out.write("            $(\"#seaFloorForm\").attr(\"action\", \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-project!save.action\");\r\n");
      out.write("        } else {\r\n");
      out.write("            $(\"#pageNo\").val(pageNo);\r\n");
      out.write("            $(\"#seaFloorForm\").attr(\"action\", \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-project!list.action\");\r\n");
      out.write("            $(\"#seaFloorForm\").ajaxSubmit(function (result) {\r\n");
      out.write("                $(\"#bisProjectFloor\").html(result);\r\n");
      out.write("            });\r\n");
      out.write("            $(\"#seaFloorForm\").attr(\"action\", \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-project!save.action\");\r\n");
      out.write("        }\r\n");
      out.write("    }\r\n");
      out.write("    function jumpPageTo() {\r\n");
      out.write("        var index = $(\"#pageTo\").val();\r\n");
      out.write("        index = parseInt(index);\r\n");
      out.write("        if (index > 0) {\r\n");
      out.write("            jumpPage(index);\r\n");
      out.write("        }\r\n");
      out.write("    }\r\n");
      out.write("    function storeValidate(obj) {\r\n");
      out.write("        obj.value = obj.value.replace(/[^\\a-zA-Z\\d\\_\\-]/g, \"\");\r\n");
      out.write("    }\r\n");
      out.write("    function showStatus(dom) {\r\n");
      out.write("        if (\"2\" == $(dom).val()) {\r\n");
      out.write("            $(\".propRight\").show();\r\n");
      out.write("        } else {\r\n");
      out.write("            $(\".propRight\").val(\"\").hide();\r\n");
      out.write("        }\r\n");
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

  private boolean _jspx_meth_s_005fselect_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f0 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f0.setParent(null);
    // /WEB-INF/content/bis/bis-project-main.jsp(37,0) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setCssStyle("display:none;");
    // /WEB-INF/content/bis/bis-project-main.jsp(37,0) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setList("mapBuilding");
    // /WEB-INF/content/bis/bis-project-main.jsp(37,0) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setListKey("key");
    // /WEB-INF/content/bis/bis-project-main.jsp(37,0) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setListValue("value");
    // /WEB-INF/content/bis/bis-project-main.jsp(37,0) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setId("bisBuildingHid");
    int _jspx_eval_s_005fselect_005f0 = _jspx_th_s_005fselect_005f0.doStartTag();
    if (_jspx_th_s_005fselect_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fselect_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f1 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f1.setParent(null);
    // /WEB-INF/content/bis/bis-project-main.jsp(38,0) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setCssStyle("display:none;");
    // /WEB-INF/content/bis/bis-project-main.jsp(38,0) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setList("mapFloor");
    // /WEB-INF/content/bis/bis-project-main.jsp(38,0) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setListKey("key");
    // /WEB-INF/content/bis/bis-project-main.jsp(38,0) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setListValue("value");
    // /WEB-INF/content/bis/bis-project-main.jsp(38,0) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setId("bisFloorHid");
    int _jspx_eval_s_005fselect_005f1 = _jspx_th_s_005fselect_005f1.doStartTag();
    if (_jspx_th_s_005fselect_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005fset_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:set
    org.apache.struts2.views.jsp.SetTag _jspx_th_s_005fset_005f0 = (org.apache.struts2.views.jsp.SetTag) _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.get(org.apache.struts2.views.jsp.SetTag.class);
    _jspx_th_s_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fset_005f0.setParent(null);
    // /WEB-INF/content/bis/bis-project-main.jsp(40,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fset_005f0.setVar("module");
    int _jspx_eval_s_005fset_005f0 = _jspx_th_s_005fset_005f0.doStartTag();
    if (_jspx_eval_s_005fset_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fset_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fset_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fset_005f0.doInitBody();
      }
      do {
        out.write('7');
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
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.reuse(_jspx_th_s_005fset_005f0);
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
    // /WEB-INF/content/bis/bis-manage-header.jsp(10,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fset_005f1.setVar("titleName");
    int _jspx_eval_s_005fset_005f1 = _jspx_th_s_005fset_005f1.doStartTag();
    if (_jspx_eval_s_005fset_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fset_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fset_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fset_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("    ");
        if (_jspx_meth_s_005fif_005f0(_jspx_th_s_005fset_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("    ");
        if (_jspx_meth_s_005felseif_005f0(_jspx_th_s_005fset_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("    ");
        if (_jspx_meth_s_005felseif_005f1(_jspx_th_s_005fset_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("    ");
        if (_jspx_meth_s_005felseif_005f2(_jspx_th_s_005fset_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("    ");
        if (_jspx_meth_s_005felseif_005f3(_jspx_th_s_005fset_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("    ");
        if (_jspx_meth_s_005felseif_005f4(_jspx_th_s_005fset_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("    ");
        if (_jspx_meth_s_005felseif_005f5(_jspx_th_s_005fset_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("    ");
        if (_jspx_meth_s_005felse_005f0(_jspx_th_s_005fset_005f1, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
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
    // /WEB-INF/content/bis/bis-manage-header.jsp(11,4) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f0.setTest("#module==1");
    int _jspx_eval_s_005fif_005f0 = _jspx_th_s_005fif_005f0.doStartTag();
    if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f0.doInitBody();
      }
      do {
        out.write("集团报表");
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
    // /WEB-INF/content/bis/bis-manage-header.jsp(12,4) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f0.setTest("#module==2");
    int _jspx_eval_s_005felseif_005f0 = _jspx_th_s_005felseif_005f0.doStartTag();
    if (_jspx_eval_s_005felseif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f0.doInitBody();
      }
      do {
        out.write("项目汇总表");
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
    // /WEB-INF/content/bis/bis-manage-header.jsp(13,4) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f1.setTest("#module==3");
    int _jspx_eval_s_005felseif_005f1 = _jspx_th_s_005felseif_005f1.doStartTag();
    if (_jspx_eval_s_005felseif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f1.doInitBody();
      }
      do {
        out.write("商业收费明细");
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

  private boolean _jspx_meth_s_005felseif_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fset_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f2 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fset_005f1);
    // /WEB-INF/content/bis/bis-manage-header.jsp(14,4) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f2.setTest("#module==4");
    int _jspx_eval_s_005felseif_005f2 = _jspx_th_s_005felseif_005f2.doStartTag();
    if (_jspx_eval_s_005felseif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f2.doInitBody();
      }
      do {
        out.write("商业合同管理");
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

  private boolean _jspx_meth_s_005felseif_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fset_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f3 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fset_005f1);
    // /WEB-INF/content/bis/bis-manage-header.jsp(15,4) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f3.setTest("#module==5");
    int _jspx_eval_s_005felseif_005f3 = _jspx_th_s_005felseif_005f3.doStartTag();
    if (_jspx_eval_s_005felseif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f3.doInitBody();
      }
      do {
        out.write("租户详细信息");
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

  private boolean _jspx_meth_s_005felseif_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fset_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f4 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fset_005f1);
    // /WEB-INF/content/bis/bis-manage-header.jsp(16,4) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f4.setTest("#module==7");
    int _jspx_eval_s_005felseif_005f4 = _jspx_th_s_005felseif_005f4.doStartTag();
    if (_jspx_eval_s_005felseif_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f4.doInitBody();
      }
      do {
        out.write("基础数据维护");
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

  private boolean _jspx_meth_s_005felseif_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fset_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f5 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f5.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fset_005f1);
    // /WEB-INF/content/bis/bis-manage-header.jsp(17,4) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f5.setTest("#module==8");
    int _jspx_eval_s_005felseif_005f5 = _jspx_th_s_005felseif_005f5.doStartTag();
    if (_jspx_eval_s_005felseif_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f5.doInitBody();
      }
      do {
        out.write("项目资料管理");
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

  private boolean _jspx_meth_s_005felse_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fset_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:else
    org.apache.struts2.views.jsp.ElseTag _jspx_th_s_005felse_005f0 = (org.apache.struts2.views.jsp.ElseTag) _005fjspx_005ftagPool_005fs_005felse.get(org.apache.struts2.views.jsp.ElseTag.class);
    _jspx_th_s_005felse_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005felse_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fset_005f1);
    int _jspx_eval_s_005felse_005f0 = _jspx_th_s_005felse_005f0.doStartTag();
    if (_jspx_eval_s_005felse_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felse_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felse_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felse_005f0.doInitBody();
      }
      do {
        out.write("商业管理系统");
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
    // /WEB-INF/content/bis/bis-manage-header.jsp(23,4) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f1.setTest("#module==2 ");
    int _jspx_eval_s_005fif_005f1 = _jspx_th_s_005fif_005f1.doStartTag();
    if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("        ");
        if (_jspx_meth_s_005fif_005f2(_jspx_th_s_005fif_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("    ");
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

  private boolean _jspx_meth_s_005fif_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f2 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f1);
    // /WEB-INF/content/bis/bis-manage-header.jsp(24,8) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f2.setTest("#curUserCd=='baolm' || #curUserCd=='lujy' || #curUserCd=='shenjian' || #curUserCd=='jiaoxf' || #curUserCd=='dengyh'");
    int _jspx_eval_s_005fif_005f2 = _jspx_th_s_005fif_005f2.doStartTag();
    if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("            &nbsp;&nbsp;<input type=\"button\" class=\"btn_blue\" style=\"width:80px\" value=\"更新数据\" onclick=\"refreshData();\"/>\r\n");
        out.write("            <input type=\"button\" class=\"btn_green\" style=\"width:80px;margin-left: 5px;\" value=\"合同导出\" onclick=\"exportExcel();\"/>\r\n");
        out.write("        ");
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
    // /WEB-INF/content/bis/bis-manage-header.jsp(29,4) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f3.setTest("#module==3 ");
    int _jspx_eval_s_005fif_005f3 = _jspx_th_s_005fif_005f3.doStartTag();
    if (_jspx_eval_s_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f3.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("        <div style=\"float:left;padding:3px;\">\r\n");
        out.write("            <input type=\"button\" id=\"btnSeniorSearch\" value=\"高级搜索\" class=\"btn_gray senior_search\" onclick=\"showSeniorSearch();\"/>\r\n");
        out.write("        </div>\r\n");
        out.write("        <div style=\"font-size:12px;width: 37px; height:16px;margin-left: 8px; padding: 0pt 0pt 0pt 0px; float: left;\">\r\n");
        out.write("            项目：\r\n");
        out.write("        </div>\r\n");
        out.write("        <div style=\"width: 100px; margin-top: 4px; padding: 0pt 0pt 0pt 0px; float: left; \t\">\r\n");
        out.write("            <input type=\"text\" id=\"bisProjectName\" name=\"bisProjectName\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"\r\n");
        out.write("                   style=\"  height: 16px;width:100%; cursor: pointer; font-size: 12px; color: #ff0000;\"/>\r\n");
        out.write("            <input type=\"hidden\" id=\"bisProjectIdFact\" name=\"bisProjectIdFact\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"/>\r\n");
        out.write("        </div>\r\n");
        out.write("        <div style=\"font-size:12px;width: 37px; margin-left: 28px; padding: 0pt 0pt 0pt 0px; float: left;\">业态：</div>\r\n");
        out.write("        <div style=\"width: 70px; margin-top: 4px;height:16px; padding: 0pt 0pt 0pt 0px; float: left; \t\">\r\n");
        out.write("            ");
        if (_jspx_meth_s_005fselect_005f2(_jspx_th_s_005fif_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("        </div>\r\n");
        out.write("    ");
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

  private boolean _jspx_meth_s_005fselect_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f2 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fstyle_005frequired_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f3);
    // /WEB-INF/content/bis/bis-manage-header.jsp(43,12) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setCssStyle("width:100%;");
    // /WEB-INF/content/bis/bis-manage-header.jsp(43,12) null
    _jspx_th_s_005fselect_005f2.setDynamicAttribute(null, "style", new String("height:19px;"));
    // /WEB-INF/content/bis/bis-manage-header.jsp(43,12) null
    _jspx_th_s_005fselect_005f2.setDynamicAttribute(null, "validate", new String("required"));
    // /WEB-INF/content/bis/bis-manage-header.jsp(43,12) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setList("@com.hhz.ump.util.DictMapUtil@getMapContBigTypeNew()");
    // /WEB-INF/content/bis/bis-manage-header.jsp(43,12) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setListKey("key");
    // /WEB-INF/content/bis/bis-manage-header.jsp(43,12) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setListValue("value");
    // /WEB-INF/content/bis/bis-manage-header.jsp(43,12) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setName("layOutCd");
    // /WEB-INF/content/bis/bis-manage-header.jsp(43,12) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setId("layOutCd");
    // /WEB-INF/content/bis/bis-manage-header.jsp(43,12) name = onchange type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setOnchange("changlayOutDetail1();");
    // /WEB-INF/content/bis/bis-manage-header.jsp(43,12) name = required type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setRequired("true");
    int _jspx_eval_s_005fselect_005f2 = _jspx_th_s_005fselect_005f2.doStartTag();
    if (_jspx_th_s_005fselect_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fstyle_005frequired_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fstyle_005frequired_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f2);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f0 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f0.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f4);
    // /WEB-INF/content/bis/bis-manage-header.jsp(50,8) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f0.setIfAnyGranted("A_PROJ_PROP_MODI");
    int _jspx_eval_security_005fauthorize_005f0 = _jspx_th_security_005fauthorize_005f0.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("            <input type=\"button\" class=\"btn_blue\" style=\"width:95px; \" onclick=\"editProjectInfo();\"\r\n");
        out.write("                   value=\"项目信息维护\"/>\r\n");
        out.write("        ");
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

  private boolean _jspx_meth_security_005fauthorize_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f1 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f1.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f4);
    // /WEB-INF/content/bis/bis-manage-header.jsp(54,8) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f1.setIfAnyGranted("A_PROJ_DATA_MODI");
    int _jspx_eval_security_005fauthorize_005f1 = _jspx_th_security_005fauthorize_005f1.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("            <input type=\"button\" class=\"btn_blue\" style=\"width:65px;\" onclick=\"doAddLayoutImg();\" value=\"资料管理\"/>\r\n");
        out.write("        ");
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

  private boolean _jspx_meth_s_005fif_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f5 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f5.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f4);
    // /WEB-INF/content/bis/bis-manage-header.jsp(59,8) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f5.setTest("#curUserCd=='baolm' || #curUserCd=='lujy' || #curUserCd=='shenjian' || #curUserCd=='jiaoxf' || #curUserCd=='dengyh' || #curUserCd=='zhengym'");
    int _jspx_eval_s_005fif_005f5 = _jspx_th_s_005fif_005f5.doStartTag();
    if (_jspx_eval_s_005fif_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f5.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("            <input type=\"button\" class=\"btn_blue\" style=\"width:65px;\" onclick=\"openFloorTab();\" value=\"楼层管理\"/>\r\n");
        out.write("        ");
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

  private boolean _jspx_meth_s_005fif_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f6 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f6.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f4);
    // /WEB-INF/content/bis/bis-manage-header.jsp(62,8) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f6.setTest("#curUserCd == 'jiaoxf'||#curUserCd == 'zhengym'");
    int _jspx_eval_s_005fif_005f6 = _jspx_th_s_005fif_005f6.doStartTag();
    if (_jspx_eval_s_005fif_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f6.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("            <input type=\"button\" class=\"btn_blue\" style=\"width:100px; \" onclick=\"exportAlertInfo();\" value=\"商铺空坐标提醒\"/>\r\n");
        out.write("        ");
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
    // /WEB-INF/content/bis/bis-manage-header.jsp(66,4) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f7.setTest("#module==1");
    int _jspx_eval_s_005fif_005f7 = _jspx_th_s_005fif_005f7.doStartTag();
    if (_jspx_eval_s_005fif_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f7.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("        <div style=\"float:left;\">\r\n");
        out.write("            <input id=\"topBtn_1\" type=\"button\" class=\"btn_grey\" style=\"width:125px; height:24px; line-height:24px;\"\r\n");
        out.write("                   onclick=\"onClickTop(1)\" value=\"集团资金流量汇总表\"/>\r\n");
        out.write("            <input id=\"topBtn_2\" type=\"button\" class=\"btn_grey\" style=\"width:125px; height:24px; line-height:24px;\"\r\n");
        out.write("                   onclick=\"onClickTop(2)\" value=\"集团经营情况汇总表\"/>\r\n");
        out.write("            <script language=\"javascript\">\r\n");
        out.write("                function onClickTop(clickNo) {\r\n");
        out.write("                    if (1 == clickNo) {\r\n");
        out.write("                        self.location = \"bis-manage-report.action?reportTypeStr=\";\r\n");
        out.write("                    } else if (2 == clickNo) {\r\n");
        out.write("                        self.location = \"bis-manage-report.action?reportTypeStr=manage\";\r\n");
        out.write("                    }\r\n");
        out.write("                }\r\n");
        out.write("                if (\"manage\" == \"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${reportTypeStr}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\") {\r\n");
        out.write("                    $(\"#topBtn_1\").removeClass();\r\n");
        out.write("                    $(\"#topBtn_1\").addClass(\"btn_grey\");\r\n");
        out.write("                    $(\"#topBtn_2\").removeClass();\r\n");
        out.write("                    $(\"#topBtn_2\").addClass(\"btn_orange\");\r\n");
        out.write("                } else {\r\n");
        out.write("                    $(\"#topBtn_1\").removeClass();\r\n");
        out.write("                    $(\"#topBtn_1\").addClass(\"btn_orange\");\r\n");
        out.write("                    $(\"#topBtn_2\").removeClass();\r\n");
        out.write("                    $(\"#topBtn_2\").addClass(\"btn_grey\");\r\n");
        out.write("                }\r\n");
        out.write("            </script>\r\n");
        out.write("        </div>\r\n");
        out.write("    ");
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
    // /WEB-INF/content/bis/bis-manage-header.jsp(100,0) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f8.setTest("#module==1");
    int _jspx_eval_s_005fif_005f8 = _jspx_th_s_005fif_005f8.doStartTag();
    if (_jspx_eval_s_005fif_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f8.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f8.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("    <input type=\"hidden\" id=\"bisProjectId\" name=\"bisProjectId\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"/>\r\n");
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

  private boolean _jspx_meth_s_005felseif_005f6(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f6 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f6.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f6.setParent(null);
    // /WEB-INF/content/bis/bis-manage-header.jsp(103,0) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f6.setTest("#module==2");
    int _jspx_eval_s_005felseif_005f6 = _jspx_th_s_005felseif_005f6.doStartTag();
    if (_jspx_eval_s_005felseif_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f6.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("    <div class=\"bis_search\" id=\"main_search_div\">\r\n");
        out.write("        <table class=\"tb_search\">\r\n");
        out.write("            <col width=\"45\"/>\r\n");
        out.write("            <col width=\"100\"/>\r\n");
        out.write("            <col width=\"45\"/>\r\n");
        out.write("            <col width=\"50\"/>\r\n");
        out.write("            <col width=\"4\"/>\r\n");
        out.write("            <col width=\"75\"/>\r\n");
        out.write("            <col width=\"4\"/>\r\n");
        out.write("            <col width=\"420\"/>\r\n");
        out.write("            <col/>\r\n");
        out.write("            <tr>\r\n");
        out.write("                <td style=\"padding-left: 8px;\" align=\"right\">项目：</td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    <input type=\"text\" validate=\"required\" id=\"bisProjectName\" name=\"bisProjectName\"\r\n");
        out.write("                           value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"\r\n");
        out.write("                           style=\"width:100%; cursor: pointer; font-size: 12px; color: #ff0000;\"/>\r\n");
        out.write("                    <input type=\"hidden\" id=\"bisProjectId\" name=\"bisProjectId\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"/>\r\n");
        out.write("                        ");
        out.write("\r\n");
        out.write("                </td>\r\n");
        out.write("                <td align=\"right\">月份：</td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    <input validate=\"required\" class=\"Wdate\" style=\"width:50px;\" type=\"text\" name=\"reportDate\"\r\n");
        out.write("                           id=\"reportDate\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${reportDate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" onfocus=\"WdatePicker({dateFmt:'yyyy-MM'})\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("                <td>&nbsp;</td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    ");
        if (_jspx_meth_s_005fselect_005f3(_jspx_th_s_005felseif_005f6, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                </td>\r\n");
        out.write("                <td>&nbsp;</td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    <ul class=\"bis_rpt\" id=\"bis_rpt\" style=\"float:left; width: 100%;\">\r\n");
        out.write("                        ");
        if (_jspx_meth_security_005fauthorize_005f2(_jspx_th_s_005felseif_005f6, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                        ");
        if (_jspx_meth_security_005fauthorize_005f3(_jspx_th_s_005felseif_005f6, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                    </ul>\r\n");
        out.write("                </td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    <input type=\"button\" value=\"搜索\" class=\"btn_blue\" onclick=\"ajaxSearch();\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("            </tr>\r\n");
        out.write("            <tr id=\"trChargeType\" style=\"display: none;\">\r\n");
        out.write("                <td colspan=\"8\" style=\"padding-left: 10px;\">\r\n");
        out.write("                    <ul class=\"bis_cgt\" id=\"bis_cgt_store\" style=\"float:left; width:100%; display: none;\">\r\n");
        out.write("                        ");
        if (_jspx_meth_s_005fiterator_005f0(_jspx_th_s_005felseif_005f6, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                    </ul>\r\n");
        out.write("                    <ul class=\"bis_cgt\" id=\"bis_cgt_flat\" style=\"float:left; width:100%; display: none;\">\r\n");
        out.write("                        ");
        if (_jspx_meth_s_005fiterator_005f1(_jspx_th_s_005felseif_005f6, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                    </ul>\r\n");
        out.write("                    <ul class=\"bis_cgt\" id=\"bis_cgt_multi\" style=\"float:left; width:100%; display: none;\">\r\n");
        out.write("                        ");
        if (_jspx_meth_s_005fiterator_005f2(_jspx_th_s_005felseif_005f6, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                    </ul>\r\n");
        out.write("                </td>\r\n");
        out.write("            </tr>\r\n");
        out.write("        </table>\r\n");
        out.write("    </div>\r\n");
        int evalDoAfterBody = _jspx_th_s_005felseif_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005felseif_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005felseif_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f6);
    return false;
  }

  private boolean _jspx_meth_s_005fselect_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felseif_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f3 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felseif_005f6);
    // /WEB-INF/content/bis/bis-manage-header.jsp(133,20) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f3.setCssStyle("width:100%;");
    // /WEB-INF/content/bis/bis-manage-header.jsp(133,20) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f3.setList("@com.hhz.ump.util.DictMapUtil@getMapShopManageType()");
    // /WEB-INF/content/bis/bis-manage-header.jsp(133,20) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f3.setListKey("key");
    // /WEB-INF/content/bis/bis-manage-header.jsp(133,20) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f3.setListValue("value");
    // /WEB-INF/content/bis/bis-manage-header.jsp(133,20) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f3.setName("manageCd");
    int _jspx_eval_s_005fselect_005f3 = _jspx_th_s_005fselect_005f3.doStartTag();
    if (_jspx_th_s_005fselect_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f3);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felseif_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f2 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f2.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felseif_005f6);
    // /WEB-INF/content/bis/bis-manage-header.jsp(140,24) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f2.setIfAnyGranted("A_REPO_PROJ_QUERY,A_REPO_ARRE_QUERY");
    int _jspx_eval_security_005fauthorize_005f2 = _jspx_th_security_005fauthorize_005f2.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                            <li id=\"li_rpt_1\" onclick=\"changeReportType('li_rpt_1');\"\r\n");
        out.write("                                ");
        if (_jspx_meth_s_005fif_005f9(_jspx_th_security_005fauthorize_005f2, _jspx_page_context))
          return true;
        out.write(" >项目资金流量表\r\n");
        out.write("                            </li>\r\n");
        out.write("                            <li id=\"li_rpt_5\" onclick=\"changeReportType('li_rpt_5');\"\r\n");
        out.write("                                ");
        if (_jspx_meth_s_005fif_005f10(_jspx_th_security_005fauthorize_005f2, _jspx_page_context))
          return true;
        out.write(" >项目经营情况表\r\n");
        out.write("                            </li>\r\n");
        out.write("                            <!--<li id=\"li_rpt_7\" onclick=\"changeReportType('li_rpt_7');\"\r\n");
        out.write("                            ");
        if (_jspx_meth_s_005fif_005f11(_jspx_th_security_005fauthorize_005f2, _jspx_page_context))
          return true;
        out.write(" >经营情况表</li> -->\r\n");
        out.write("                        ");
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

  private boolean _jspx_meth_s_005fif_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_security_005fauthorize_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f9 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f9.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_security_005fauthorize_005f2);
    // /WEB-INF/content/bis/bis-manage-header.jsp(142,32) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f9.setTest("reportType=='total'");
    int _jspx_eval_s_005fif_005f9 = _jspx_th_s_005fif_005f9.doStartTag();
    if (_jspx_eval_s_005fif_005f9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f9.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f9.doInitBody();
      }
      do {
        out.write("class=\"bis_rpt_click\"");
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

  private boolean _jspx_meth_s_005fif_005f10(javax.servlet.jsp.tagext.JspTag _jspx_th_security_005fauthorize_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f10 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f10.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_security_005fauthorize_005f2);
    // /WEB-INF/content/bis/bis-manage-header.jsp(145,32) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f10.setTest("reportType=='manage'");
    int _jspx_eval_s_005fif_005f10 = _jspx_th_s_005fif_005f10.doStartTag();
    if (_jspx_eval_s_005fif_005f10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f10.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f10.doInitBody();
      }
      do {
        out.write("class=\"bis_rpt_click\"");
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

  private boolean _jspx_meth_s_005fif_005f11(javax.servlet.jsp.tagext.JspTag _jspx_th_security_005fauthorize_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f11 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f11.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_security_005fauthorize_005f2);
    // /WEB-INF/content/bis/bis-manage-header.jsp(148,28) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f11.setTest("reportType=='manaRept'");
    int _jspx_eval_s_005fif_005f11 = _jspx_th_s_005fif_005f11.doStartTag();
    if (_jspx_eval_s_005fif_005f11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f11 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f11.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f11.doInitBody();
      }
      do {
        out.write("class=\"bis_rpt_click\"");
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

  private boolean _jspx_meth_security_005fauthorize_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felseif_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f3 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f3.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felseif_005f6);
    // /WEB-INF/content/bis/bis-manage-header.jsp(150,24) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f3.setIfAnyGranted("A_REPO_DETA_QUERY");
    int _jspx_eval_security_005fauthorize_005f3 = _jspx_th_security_005fauthorize_005f3.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                            <li id=\"li_rpt_2\" onclick=\"changeReportType('li_rpt_2');\"\r\n");
        out.write("                                ");
        if (_jspx_meth_s_005fif_005f12(_jspx_th_security_005fauthorize_005f3, _jspx_page_context))
          return true;
        out.write(">商铺\r\n");
        out.write("                            </li>\r\n");
        out.write("                            <li id=\"li_rpt_6\" onclick=\"changeReportType('li_rpt_6');\"\r\n");
        out.write("                                ");
        if (_jspx_meth_s_005fif_005f13(_jspx_th_security_005fauthorize_005f3, _jspx_page_context))
          return true;
        out.write(">步行街\r\n");
        out.write("                            </li>\r\n");
        out.write("                            <li id=\"li_rpt_3\" onclick=\"changeReportType('li_rpt_3');\"\r\n");
        out.write("                                ");
        if (_jspx_meth_s_005fif_005f14(_jspx_th_security_005fauthorize_005f3, _jspx_page_context))
          return true;
        out.write(">公寓\r\n");
        out.write("                            </li>\r\n");
        out.write("                            <li id=\"li_rpt_4\" onclick=\"changeReportType('li_rpt_4');\"\r\n");
        out.write("                                ");
        if (_jspx_meth_s_005fif_005f15(_jspx_th_security_005fauthorize_005f3, _jspx_page_context))
          return true;
        out.write(">多经\r\n");
        out.write("                            </li>\r\n");
        out.write("                        ");
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

  private boolean _jspx_meth_s_005fif_005f12(javax.servlet.jsp.tagext.JspTag _jspx_th_security_005fauthorize_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f12 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f12.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_security_005fauthorize_005f3);
    // /WEB-INF/content/bis/bis-manage-header.jsp(152,32) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f12.setTest("reportType=='store'");
    int _jspx_eval_s_005fif_005f12 = _jspx_th_s_005fif_005f12.doStartTag();
    if (_jspx_eval_s_005fif_005f12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f12 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f12.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f12.doInitBody();
      }
      do {
        out.write("class=\"bis_rpt_click\"");
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

  private boolean _jspx_meth_s_005fif_005f13(javax.servlet.jsp.tagext.JspTag _jspx_th_security_005fauthorize_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f13 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f13.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_security_005fauthorize_005f3);
    // /WEB-INF/content/bis/bis-manage-header.jsp(155,32) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f13.setTest("reportType=='walkStreet'");
    int _jspx_eval_s_005fif_005f13 = _jspx_th_s_005fif_005f13.doStartTag();
    if (_jspx_eval_s_005fif_005f13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f13 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f13.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f13.doInitBody();
      }
      do {
        out.write("class=\"bis_rpt_click\"");
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

  private boolean _jspx_meth_s_005fif_005f14(javax.servlet.jsp.tagext.JspTag _jspx_th_security_005fauthorize_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f14 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f14.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_security_005fauthorize_005f3);
    // /WEB-INF/content/bis/bis-manage-header.jsp(158,32) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f14.setTest("reportType=='flat'");
    int _jspx_eval_s_005fif_005f14 = _jspx_th_s_005fif_005f14.doStartTag();
    if (_jspx_eval_s_005fif_005f14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f14 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f14.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f14.doInitBody();
      }
      do {
        out.write("class=\"bis_rpt_click\"");
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

  private boolean _jspx_meth_s_005fif_005f15(javax.servlet.jsp.tagext.JspTag _jspx_th_security_005fauthorize_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f15 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f15.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_security_005fauthorize_005f3);
    // /WEB-INF/content/bis/bis-manage-header.jsp(161,32) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f15.setTest("reportType=='multi'");
    int _jspx_eval_s_005fif_005f15 = _jspx_th_s_005fif_005f15.doStartTag();
    if (_jspx_eval_s_005fif_005f15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f15 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f15.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f15.doInitBody();
      }
      do {
        out.write("class=\"bis_rpt_click\"");
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

  private boolean _jspx_meth_s_005fiterator_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felseif_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_005fiterator_005f0 = (org.apache.struts2.views.jsp.IteratorTag) _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_005fiterator_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fiterator_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felseif_005f6);
    // /WEB-INF/content/bis/bis-manage-header.jsp(173,24) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
        out.write("                            <li id=\"li_cgt_");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${key}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${key}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" class=\"bis_cgt_li\"\r\n");
        out.write("                                onclick=\"sltReportCharge(this);\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${value}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</li>\r\n");
        out.write("                        ");
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

  private boolean _jspx_meth_s_005fiterator_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felseif_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_005fiterator_005f1 = (org.apache.struts2.views.jsp.IteratorTag) _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_005fiterator_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fiterator_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felseif_005f6);
    // /WEB-INF/content/bis/bis-manage-header.jsp(179,24) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
        out.write("                            <li id=\"li_cgt_");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${key}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${key}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" class=\"bis_cgt_li\"\r\n");
        out.write("                                onclick=\"sltReportCharge(this);\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${value}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</li>\r\n");
        out.write("                        ");
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

  private boolean _jspx_meth_s_005fiterator_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felseif_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_005fiterator_005f2 = (org.apache.struts2.views.jsp.IteratorTag) _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_005fiterator_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fiterator_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felseif_005f6);
    // /WEB-INF/content/bis/bis-manage-header.jsp(185,24) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
        out.write("                            <li id=\"li_cgt_");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${key}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${key}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" class=\"bis_cgt_li\"\r\n");
        out.write("                                onclick=\"sltReportCharge(this);\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${value}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</li>\r\n");
        out.write("                        ");
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

  private boolean _jspx_meth_s_005felseif_005f7(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f7 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f7.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f7.setParent(null);
    // /WEB-INF/content/bis/bis-manage-header.jsp(196,0) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f7.setTest("#module==3");
    int _jspx_eval_s_005felseif_005f7 = _jspx_th_s_005felseif_005f7.doStartTag();
    if (_jspx_eval_s_005felseif_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f7.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("    <div class=\"bis_search\" id=\"main_search_div\" style=\"height:30px\">\r\n");
        out.write("        <table class=\"tb_search\" style=\"    margin-left: 5px;\">\r\n");
        out.write("            <col/>\r\n");
        out.write("            <tr>\r\n");
        out.write("\r\n");
        out.write("                <td align=\"right\" class=\"flat_layout\" style=\"display:none;\" layout=\"flat\" width=\"40\">楼号：</td>\r\n");
        out.write("                <td layout=\"flat\" class=\"flat_layout\" style=\"display:none;\" width=\"70\">\r\n");
        out.write("                    <input style=\"width:100%;height:16px;\" id=\"flatBuding\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("                <td align=\"right\" id=\"detailLabel\" width=\"40\">租户：</td>\r\n");
        out.write("                <td width=\"100\">\r\n");
        out.write("                    <input style=\"width:100px;height:16px;color:#ccc\" title=\"\" id=\"layOutCdList_v0\" required=\"true\"\r\n");
        out.write("                           onfocus=\"clearInput(this);\" value=\"搜索商家/商铺\"/>\r\n");
        out.write("                    <select style=\"width:150px;height:20px;display:none;\" title=\"\" id=\"layOutCdList_v1\" required=\"true\"\r\n");
        out.write("                            onClick=\"selectDetail1();\"></select>\r\n");
        out.write("                </td>\r\n");
        out.write("                <td align=\"right\" width=\"40\">类别：</td>\r\n");
        out.write("                <td width=\"100\">\r\n");
        out.write("                    ");
        if (_jspx_meth_s_005fselect_005f4(_jspx_th_s_005felseif_005f7, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                </td>\r\n");
        out.write("                <td align=\"right\" width=\"30\">年：</td>\r\n");
        out.write("                <td width=\"50\">\r\n");
        out.write("                    ");
        if (_jspx_meth_s_005fselect_005f5(_jspx_th_s_005felseif_005f7, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                </td>\r\n");
        out.write("                <td align=\"right\" width=\"30\">月：</td>\r\n");
        out.write("                <td width=\"50\">\r\n");
        out.write("                    ");
        if (_jspx_meth_s_005fselect_005f6(_jspx_th_s_005felseif_005f7, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                </td>\r\n");
        out.write("                <td align=\"right\" class=\"must_dime\" must=\"true\" style=\"display:none\" width=\"70\">应收日期：</td>\r\n");
        out.write("\r\n");
        out.write("                <td align=\"right\" class=\"must_dime\" id=\"must_1\" must=\"true\" style=\"display:none\" width=\"80px\">\r\n");
        out.write("                    <input class=input\" type=\"text\"\r\n");
        out.write("                    onfocus=\"WdatePicker()\"\r\n");
        out.write("                    name=\"mustInBegin\" id=\"mustInBegin\" style=\"cursor: pointer;width:70px;height:16px; margin-top:\r\n");
        out.write("                    2px;\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("                <td align=\"right\" class=\"must_dime\" must=\"true\" style=\"display:none\" width=\"30\">至 &nbsp;  </td>\r\n");
        out.write("                <td align=\"right\" class=\"must_dime\" id=\"must_1\" must=\"true\" style=\"display:none\" width=\"80px\">\r\n");
        out.write("\r\n");
        out.write("                    <input class=input\" type=\"text\"\r\n");
        out.write("                    onfocus=\"WdatePicker()\"\r\n");
        out.write("                    name=\"mustInEnd\" id=\"mustInEnd\" style=\"cursor: pointer;width:70px;height:16px; margin-top: 2px;\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("                <td style=\"padding-left: 5px;\">\r\n");
        out.write("                    <input id=\"btn_search\" type=\"button\" style=\"width:65px;margin-right: 5px;\" class=\"btn_blue\" onclick=\"submitSearch1();\"\r\n");
        out.write("                           value=\"搜索\"/>\r\n");
        out.write("                    <input id=\"btn_blue\" class=\"btn_blue fact_dime\" type=\"button\" style=\"width:65px; \"\r\n");
        out.write("                           onclick=\"appendFact();\" value=\"新增\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("            </tr>\r\n");
        out.write("        </table>\r\n");
        out.write("    </div>\r\n");
        out.write("    <div class=\"bis_search\" id=\"main_search_div1\"\r\n");
        out.write("         style=\"height:30px;background:white;border:0px;    margin-bottom: 9px;\">\r\n");
        out.write("        <table style=\"width:100%\">\r\n");
        out.write("            <tr>\r\n");
        out.write("                <td colspan=\"12\">\r\n");
        out.write("                    <ul id=\"bis_rpt\" style=\"margin-left: 4px;list-style-type:none;\">\r\n");
        out.write("                        <li class=\"\" id=\"must\" value=\"2\" style=\"margin-top:8px;height:24px;line-height:24px;\">应收明细</li>\r\n");
        out.write("                        <li class=\"\" id=\"overdue\" value=\"3\" style=\"margin-top:8px;height:24px;line-height:24px; \">欠费明细\r\n");
        out.write("                        </li>\r\n");
        out.write("                        <li class=\"\" id=\"fact\" value=\"1\" style=\"margin-top:8px;height:24px;line-height:24px;\">收费历史记录\r\n");
        out.write("                        </li>\r\n");
        out.write("                    </ul>\r\n");
        out.write("                    <ul class=\"fact_dime\" style=\"    list-style-type: none;\" id=\"search_fact\">\r\n");
        out.write("                        <li class=\"\" fact=\"true\" id=\"\" valu='1'\r\n");
        out.write("                            style=\"color:#006FB6;float:right;margin:8px 14px 0 4px;height:18px;line-height:18px;\">审核通过\r\n");
        out.write("                        </li>\r\n");
        out.write("                        <li class=\"\" fact=\"true\" id=\"\" valu='0'\r\n");
        out.write("                            style=\"color:#006FB6;float:right;margin:8px 4px 0;height:18px;line-height:18px;\">等待审核\r\n");
        out.write("                        </li>\r\n");
        out.write("                        <li class=\"\" fact=\"true\" id=\"\" valu='2'\r\n");
        out.write("                            style=\"color:#006FB6;float:right;margin:8px 4px 0;height:18px;line-height:18px;\">审核驳回\r\n");
        out.write("                        </li>\r\n");
        out.write("                        <li class=\"\" fact=\"true\" id=\"factAll\" valu=''\r\n");
        out.write("                            style=\"color:red;float:right;margin:8px 4px 0;height:18px;line-height:18px;\">全部\r\n");
        out.write("                        </li>\r\n");
        out.write("                        <li class=\"\" fact=\"true\" id=\"\" valu='n'\r\n");
        out.write("                            style=\"float:right;margin:8px 4px 0;height:18px;line-height:18px;\">快速搜索:\r\n");
        out.write("                        </li>\r\n");
        out.write("                    </ul>\r\n");
        out.write("                    <ul class=\"must_dime\" style=\"    list-style-type: none;\" id=\"search_due\">\r\n");
        out.write("                        <li class=\"\" id=\"dueli\" valu='1' style=\"color:red\">未收齐</li>\r\n");
        out.write("                        <li class=\"\" id=\"nodueli\" valu='0'>已收齐</li>\r\n");
        out.write("                        <li class=\"\" id=\"allnodueli\" valu='2'>全部</li>\r\n");
        out.write("                        <li class=\"\" id=\"\" valu='n' style=\"color:#000\">快速搜索:</li>\r\n");
        out.write("                    </ul>\r\n");
        out.write("                    <!-- <div id=\"divoverdue\" style=\"line-height:18px;text-align:center;float:left; \" >\r\n");
        out.write("                                     <input id=\"inputoverdue\" checked=\"checked\" style=\"float:left;    margin:8px 1px 8px 4px \" type=\"checkbox\"/>\r\n");
        out.write("                                     </div>\r\n");
        out.write("                                      -->\r\n");
        out.write("                    <span style=\"margin-top:8px;margin-left:20px;float:left ;color:red;display:none;line-height:20px;height:20px;\"\r\n");
        out.write("                          id=\"factoptip\"></span>\r\n");
        out.write("                </td>\r\n");
        out.write("            </tr>\r\n");
        out.write("        </table>\r\n");
        out.write("    </div>\r\n");
        out.write("    <div class=\"bis_search\" id=\"notPublic\"\r\n");
        out.write("         style=\"height:30px;background:white;border:0px;    margin-bottom: 9px;display:none\">\r\n");
        out.write("        <table style=\"width:100%\">\r\n");
        out.write("            <tr>\r\n");
        out.write("                <td>\r\n");
        out.write("                        ");
        out.write("\r\n");
        out.write("                    <form id=\"importFactForm\" enctype=\"multipart/form-data\" method=\"post\"\r\n");
        out.write("                          action=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("/bis/bis-fact!importFact.action\">\r\n");
        out.write("                        <input id=\"importBisProjectId\" name=\"bisProjectId\" type=\"hidden\"/>\r\n");
        out.write("                        <table>\r\n");
        out.write("                            <tbody>\r\n");
        out.write("                            <tr>\r\n");
        out.write("                            <tr>\r\n");
        out.write("                                <td style=\"padding-left: 8px; padding-top: 5px;\" colspan=\"3\">\r\n");
        out.write("                                    <input type=\"file\" style=\"line-height: 22px; height: 22px; margin-bottom: 3px;\"\r\n");
        out.write("                                           name=\"importFact\" id=\"importFact\">\r\n");
        out.write("                                </td>\r\n");
        out.write("                                <td style=\"padding-left: 10px;\" colspan=\"6\">\r\n");
        out.write("                                    <input type=\"button\" value=\"导入数据\" onclick=\"importFactFile();\" style=\"width: 65px; margin-right: 5px;\"\r\n");
        out.write("                                           class=\"btn_blue\">\r\n");
        out.write("                                    <input id=\"btn_blue\" type=\"button\" value=\"批量新增\" onclick=\"appendFacts();\"\r\n");
        out.write("                                           style=\"width: 65px;\" class=\"btn_blue\">\r\n");
        out.write("                                </td>\r\n");
        out.write("                            </tr>\r\n");
        out.write("                            </tbody>\r\n");
        out.write("                        </table>\r\n");
        out.write("\r\n");
        out.write("                    </form>\r\n");
        out.write("                </td>\r\n");
        out.write("            </tr>\r\n");
        out.write("        </table>\r\n");
        out.write("    </div>\r\n");
        int evalDoAfterBody = _jspx_th_s_005felseif_005f7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005felseif_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005felseif_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f7);
    return false;
  }

  private boolean _jspx_meth_s_005fselect_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felseif_005f7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f4 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felseif_005f7);
    // /WEB-INF/content/bis/bis-manage-header.jsp(215,20) name = onchange type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f4.setOnchange("setThisTitle('chargeTypeCd');");
    // /WEB-INF/content/bis/bis-manage-header.jsp(215,20) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f4.setCssStyle("width:100%");
    // /WEB-INF/content/bis/bis-manage-header.jsp(215,20) null
    _jspx_th_s_005fselect_005f4.setDynamicAttribute(null, "validate", new String("required"));
    // /WEB-INF/content/bis/bis-manage-header.jsp(215,20) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f4.setList("@com.hhz.ump.util.DictMapUtil@getMapChargeType()");
    // /WEB-INF/content/bis/bis-manage-header.jsp(215,20) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f4.setListKey("key");
    // /WEB-INF/content/bis/bis-manage-header.jsp(215,20) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f4.setListValue("value");
    // /WEB-INF/content/bis/bis-manage-header.jsp(215,20) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f4.setName("chargeTypeCd");
    // /WEB-INF/content/bis/bis-manage-header.jsp(215,20) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f4.setId("chargeTypeCd");
    int _jspx_eval_s_005fselect_005f4 = _jspx_th_s_005fselect_005f4.doStartTag();
    if (_jspx_th_s_005fselect_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f4);
    return false;
  }

  private boolean _jspx_meth_s_005fselect_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felseif_005f7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f5 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f5.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felseif_005f7);
    // /WEB-INF/content/bis/bis-manage-header.jsp(221,20) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f5.setCssStyle("width:100%;");
    // /WEB-INF/content/bis/bis-manage-header.jsp(221,20) null
    _jspx_th_s_005fselect_005f5.setDynamicAttribute(null, "validate", new String("required"));
    // /WEB-INF/content/bis/bis-manage-header.jsp(221,20) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f5.setList("@com.hhz.ump.util.DictMapUtil@getMapYear()");
    // /WEB-INF/content/bis/bis-manage-header.jsp(221,20) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f5.setListKey("key");
    // /WEB-INF/content/bis/bis-manage-header.jsp(221,20) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f5.setListValue("value");
    // /WEB-INF/content/bis/bis-manage-header.jsp(221,20) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f5.setName("year");
    // /WEB-INF/content/bis/bis-manage-header.jsp(221,20) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f5.setId("year");
    int _jspx_eval_s_005fselect_005f5 = _jspx_th_s_005fselect_005f5.doStartTag();
    if (_jspx_th_s_005fselect_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f5);
    return false;
  }

  private boolean _jspx_meth_s_005fselect_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felseif_005f7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f6 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f6.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felseif_005f7);
    // /WEB-INF/content/bis/bis-manage-header.jsp(227,20) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f6.setCssStyle("width:100%;");
    // /WEB-INF/content/bis/bis-manage-header.jsp(227,20) null
    _jspx_th_s_005fselect_005f6.setDynamicAttribute(null, "validate", new String("required"));
    // /WEB-INF/content/bis/bis-manage-header.jsp(227,20) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f6.setList("@com.hhz.ump.util.DictMapUtil@getMapMonth()");
    // /WEB-INF/content/bis/bis-manage-header.jsp(227,20) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f6.setListKey("key");
    // /WEB-INF/content/bis/bis-manage-header.jsp(227,20) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f6.setListValue("value");
    // /WEB-INF/content/bis/bis-manage-header.jsp(227,20) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f6.setName("month");
    // /WEB-INF/content/bis/bis-manage-header.jsp(227,20) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f6.setId("month");
    int _jspx_eval_s_005fselect_005f6 = _jspx_th_s_005fselect_005f6.doStartTag();
    if (_jspx_th_s_005fselect_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fvalidate_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f6);
    return false;
  }

  private boolean _jspx_meth_s_005felseif_005f8(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f8 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f8.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f8.setParent(null);
    // /WEB-INF/content/bis/bis-manage-header.jsp(333,0) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f8.setTest("#module==7");
    int _jspx_eval_s_005felseif_005f8 = _jspx_th_s_005felseif_005f8.doStartTag();
    if (_jspx_eval_s_005felseif_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f8.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f8.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("    <div class=\"bis_search\" id=\"main_search_div\">\r\n");
        out.write("        <table class=\"tb_search\">\r\n");
        out.write("            <col width=\"45\"/>\r\n");
        out.write("            <col width=\"120\"/>\r\n");
        out.write("            <col width=\"80\"/>\r\n");
        out.write("            <col width=\"70\"/>\r\n");
        out.write("            <col width=\"60\"/>\r\n");
        out.write("            <col width=\"90\"/>\r\n");
        out.write("            <col/>\r\n");
        out.write("            <tr>\r\n");
        out.write("                <td style=\"padding-left: 8px;\" align=\"right\">项目：</td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    <input type=\"text\" readonly=\"true\" class=\"projectSelect\" id=\"bisProjectName\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"\r\n");
        out.write("                           style=\"width:100%; cursor: pointer; font-size: 12px; color: #ff0000;\"/>\r\n");
        out.write("                    <input type=\"hidden\" id=\"bisProjectId\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("                <td align=\"right\">楼宇类型：</td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    <select style=\"width: 100%\" id=\"floorType\" onchange=\"changeFloorType(this.value);\">\r\n");
        out.write("                        <option value=\"\">--选择--</option>\r\n");
        out.write("                        <option value=\"1\">商铺</option>\r\n");
        out.write("                        <option value=\"2\">公寓</option>\r\n");
        out.write("                        <option value=\"3\">多经</option>\r\n");
        out.write("                    </select>\r\n");
        out.write("                </td>\r\n");
        out.write("                <td id=\"building\" align=\"right\">楼号：</td>\r\n");
        out.write("                <td id=\"bisFloorTd\">\r\n");
        out.write("                    <select style=\"width: 100%\" id=\"bisFloorId\" onchange=\"bisFloorSearch(1)\">\r\n");
        out.write("                        <option>--选择--</option>\r\n");
        out.write("                    </select>\r\n");
        out.write("                </td>\r\n");
        out.write("                <td id=\"numName\" align=\"right\">编号：</td>\r\n");
        out.write("                <td style=\"width: 90px;\"><input type=\"text\" id=\"num\" style=\"width: 80px;\"></input></td>\r\n");
        out.write("                <td style=\"padding-left: 5px;\">\r\n");
        out.write("                    <input type=\"button\" class=\"btn_blue\" onclick=\"bisFloorSearch(1);\" value=\"搜索\"/>\r\n");
        out.write("                    ");
        if (_jspx_meth_security_005fauthorize_005f4(_jspx_th_s_005felseif_005f8, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                    <input type=\"button\" class=\"btn_blue\" style=\"width:65px; display:none;\" onclick=\"doImport();\"\r\n");
        out.write("                           value=\"导入\"/>\r\n");
        out.write("                    <input type=\"button\" class=\"btn_green\" style=\"width:65px;\" onclick=\"doExport();\" value=\"导出\"/>\r\n");
        out.write("\r\n");
        out.write("                </td>\r\n");
        out.write("            </tr>\r\n");
        out.write("        </table>\r\n");
        out.write("    </div>\r\n");
        int evalDoAfterBody = _jspx_th_s_005felseif_005f8.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005felseif_005f8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005felseif_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f8);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felseif_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f4 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f4.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felseif_005f8);
    // /WEB-INF/content/bis/bis-manage-header.jsp(369,20) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f4.setIfAnyGranted("A_PROJ_PROP_MODI");
    int _jspx_eval_security_005fauthorize_005f4 = _jspx_th_security_005fauthorize_005f4.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("                        <input type=\"button\" class=\"btn_blue\" style=\"width:65px;\" onclick=\"bisFloorAdd();\" value=\"新增\"/>\r\n");
        out.write("                    ");
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

  private boolean _jspx_meth_s_005felseif_005f9(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f9 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f9.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f9.setParent(null);
    // /WEB-INF/content/bis/bis-manage-header.jsp(381,0) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f9.setTest("#module==8");
    int _jspx_eval_s_005felseif_005f9 = _jspx_th_s_005felseif_005f9.doStartTag();
    if (_jspx_eval_s_005felseif_005f9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f9.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f9.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("    <div class=\"bis_search\" id=\"main_search_div\">\r\n");
        out.write("        <table class=\"tb_search\">\r\n");
        out.write("            <col width=\"45\"/>\r\n");
        out.write("            <col width=\"120\"/>\r\n");
        out.write("            <col/>\r\n");
        out.write("            <tr>\r\n");
        out.write("                <td style=\"padding-left: 8px;\" align=\"right\">项目：</td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    <input type=\"text\" id=\"bisProjectName\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"\r\n");
        out.write("                           style=\"width:100%; cursor: pointer; font-size: 12px; color: #ff0000;\"/>\r\n");
        out.write("                    <input type=\"hidden\" id=\"bisProjectId\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("                <td style=\"padding-left: 20px;\">\r\n");
        out.write("                    <input type=\"button\" class=\"btn_blue\" style=\"width: 65px;\" onclick=\"submitSearch();\" value=\"搜索\"/>\r\n");
        out.write("                    <input type=\"button\" class=\"btn_blue\" style=\"width:65px;\" onclick=\"doAddLayout();\" value=\"新增\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("            </tr>\r\n");
        out.write("        </table>\r\n");
        out.write("    </div>\r\n");
        int evalDoAfterBody = _jspx_th_s_005felseif_005f9.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005felseif_005f9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005felseif_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f9);
    return false;
  }

  private boolean _jspx_meth_s_005felseif_005f10(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f10 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f10.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f10.setParent(null);
    // /WEB-INF/content/bis/bis-manage-header.jsp(402,0) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f10.setTest("#module==9");
    int _jspx_eval_s_005felseif_005f10 = _jspx_th_s_005felseif_005f10.doStartTag();
    if (_jspx_eval_s_005felseif_005f10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f10.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f10.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("    <div class=\"bis_search\" id=\"main_search_div\">\r\n");
        out.write("        <table class=\"tb_search\">\r\n");
        out.write("            <col width=\"45\"/>\r\n");
        out.write("            <col width=\"100\"/>\r\n");
        out.write("            <col width=\"50\"/>\r\n");
        out.write("            <col width=\"80\"/>\r\n");
        out.write("            <col width=\"50\"/>\r\n");
        out.write("            <col width=\"100\"/>\r\n");
        out.write("            <col/>\r\n");
        out.write("            <tr>\r\n");
        out.write("                <td style=\"padding-left: 8px;\" align=\"right\">项目：</td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    <input type=\"text\" id=\"filter_EQ_bisProjectName\" name=\"filter_EQ_bisProjectName\"\r\n");
        out.write("                           value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${filter_EQ_bisProjectName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"\r\n");
        out.write("                           style=\"width:100%; cursor: pointer; font-size: 12px; color: #ff0000;\"/>\r\n");
        out.write("                    <input type=\"hidden\" id=\"filter_EQ_bisProjectId\" name=\"filter_EQ_bisProjectId\"\r\n");
        out.write("                           value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${filter_EQ_bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("                <td align=\"right\">商铺：</td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    <input class=\"enterQuery\" type=\"text\" id=\"filter_LIKES_storeNo\" name=\"filter_LIKES_storeNo\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("                <td align=\"right\">商家：</td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    <input class=\"enterQuery\" type=\"text\" id=\"filter_LIKES_shopName\" name=\"filter_LIKES_shopName\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("                <td style=\"padding-left:10px; line-height:20px;\">\r\n");
        out.write("                    <input type=\"button\" value=\"搜索\" class=\"btn_blue\" onclick=\"submitSearch();\"/>\r\n");
        out.write("                        ");
        out.write("\r\n");
        out.write("                    ");
        if (_jspx_meth_s_005fif_005f16(_jspx_th_s_005felseif_005f10, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                </td>\r\n");
        out.write("            </tr>\r\n");
        out.write("        </table>\r\n");
        out.write("    </div>\r\n");
        int evalDoAfterBody = _jspx_th_s_005felseif_005f10.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005felseif_005f10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005felseif_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.reuse(_jspx_th_s_005felseif_005f10);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f16(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felseif_005f10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f16 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f16.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felseif_005f10);
    // /WEB-INF/content/bis/bis-manage-header.jsp(434,20) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f16.setTest("#curUserCd == 'baolm'");
    int _jspx_eval_s_005fif_005f16 = _jspx_th_s_005fif_005f16.doStartTag();
    if (_jspx_eval_s_005fif_005f16 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f16 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f16.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f16.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("                        <input type=\"button\" value=\"合并\" class=\"btn_blue\" onclick=\"toMergeTenant();\"/>\r\n");
        out.write("                    ");
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

  private boolean _jspx_meth_s_005fif_005f17(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f17 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f17.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f17.setParent(null);
    // /WEB-INF/content/bis/bis-manage-header.jsp(453,8) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f17.setTest("#module==3");
    int _jspx_eval_s_005fif_005f17 = _jspx_th_s_005fif_005f17.doStartTag();
    if (_jspx_eval_s_005fif_005f17 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f17 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f17.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f17.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("            <tr id=\"must_fact_senior\" class=\"fact_dime\" style=\"display:none;\">\r\n");
        out.write("                <td align=\"right\">实收日期：</td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    <input class=input\" type=\"text\"\r\n");
        out.write("                    onfocus=\"WdatePicker()\"\r\n");
        out.write("                    name=\"factInBegin\" id=\"factInBegin\" style=\"cursor: pointer;\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("                <td align=\"right\">到：</td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    <input class=input\" type=\"text\"\r\n");
        out.write("                    onfocus=\"WdatePicker()\"\r\n");
        out.write("                    name=\"factInEnd\" id=\"factInEnd\" style=\"cursor: pointer;\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("                <td align=\"right\">状态：</td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    ");
        if (_jspx_meth_s_005fselect_005f7(_jspx_th_s_005fif_005f17, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("                </td>\r\n");
        out.write("            </tr>\r\n");
        out.write("            <tr>\r\n");
        out.write("                <td align=\"right\">创建人：</td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    <input name=\"creatorQ\" id=\"creatorQ\"/>\r\n");
        out.write("                    <input name=\"creator\" id=\"creator\" type=\"hidden\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("                <td align=\"right\">年月从：</td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    <input onfocus=\"WdatePicker({dateFmt:'yyyy-MM'})\"\r\n");
        out.write("                           name=\"minMonth\" id=\"minMonth\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("                <td align=\"right\">到：</td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    <input onfocus=\"WdatePicker({dateFmt:'yyyy-MM'})\" name=\"maxMonth\" id=\"maxMonth\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("            </tr>\r\n");
        out.write("            <tr>\r\n");
        out.write("                <td align=\"right\">审核人：</td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    <input name=\"checkUserCdQ\" id=\"checkUserCdQ\"/>\r\n");
        out.write("                    <input name=\"checkUserCd\" id=\"checkUserCd\" type=\"hidden\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("                <td align=\"right\">实收金额：</td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    <input class=input\" type=\"text\" name=\"minMoney\" id=\"minMoney\" style=\"cursor: pointer;\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("                <td align=\"right\">到：</td>\r\n");
        out.write("                <td>\r\n");
        out.write("                    <input class=input\" type=\"text\" name=\"maxMoney\" id=\"maxMoney\" />\r\n");
        out.write("                </td>\r\n");
        out.write("            </tr>\r\n");
        out.write("            <tr>\r\n");
        out.write("                <td colspan=\"6\" style=\"padding-top:10px; text-align:center;\">\r\n");
        out.write("                    <input type=\"button\" class=\"btn_blue\" onClick=\"submitSearch2();closeSeniorSearch();\" value=\"搜索\"/>\r\n");
        out.write("                    <input type=\"button\" class=\"btn_green\" onClick=\"closeSeniorSearch();\" value=\"关闭\"/>\r\n");
        out.write("                </td>\r\n");
        out.write("            </tr>\r\n");
        out.write("        ");
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

  private boolean _jspx_meth_s_005fselect_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f17, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f7 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f7.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f17);
    // /WEB-INF/content/bis/bis-manage-header.jsp(469,20) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f7.setList("@com.hhz.ump.util.DictMapUtil@getMapBisFactStatus()");
    // /WEB-INF/content/bis/bis-manage-header.jsp(469,20) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f7.setListKey("key");
    // /WEB-INF/content/bis/bis-manage-header.jsp(469,20) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f7.setListValue("value");
    // /WEB-INF/content/bis/bis-manage-header.jsp(469,20) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f7.setName("statusCd");
    // /WEB-INF/content/bis/bis-manage-header.jsp(469,20) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f7.setId("statusCd");
    int _jspx_eval_s_005fselect_005f7 = _jspx_th_s_005fselect_005f7.doStartTag();
    if (_jspx_th_s_005fselect_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.reuse(_jspx_th_s_005fselect_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.reuse(_jspx_th_s_005fselect_005f7);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f18(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f18 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f18.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f18.setParent(null);
    // /WEB-INF/content/bis/bis-manage-header.jsp(583,0) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f18.setTest("#module==0 || #module==1 || #module==5");
    int _jspx_eval_s_005fif_005f18 = _jspx_th_s_005fif_005f18.doStartTag();
    if (_jspx_eval_s_005fif_005f18 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f18 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f18.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f18.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("    <script type=\"text/javascript\">\r\n");
        out.write("        //$(\"#div_title\").css(\"height\",\"43px\");\r\n");
        out.write("        $(\"#div_title\").css(\"height\", \"0px\");\r\n");
        out.write("\r\n");
        out.write("    </script>\r\n");
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

  private boolean _jspx_meth_s_005fselect_005f8(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f8 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fclass_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f8.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f8.setParent(null);
    // /WEB-INF/content/bis/bis-project-main.jsp(180,16) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f8.setCssStyle("width:100%;");
    // /WEB-INF/content/bis/bis-project-main.jsp(180,16) null
    _jspx_th_s_005fselect_005f8.setDynamicAttribute(null, "class", new String("required"));
    // /WEB-INF/content/bis/bis-project-main.jsp(180,16) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f8.setList("@com.hhz.ump.util.DictMapUtil@getMapStoreLayout()");
    // /WEB-INF/content/bis/bis-project-main.jsp(180,16) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f8.setListKey("key");
    // /WEB-INF/content/bis/bis-project-main.jsp(180,16) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f8.setListValue("value");
    // /WEB-INF/content/bis/bis-project-main.jsp(180,16) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f8.setName("bisStore.layoutCd");
    // /WEB-INF/content/bis/bis-project-main.jsp(180,16) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f8.setId("layoutCd");
    int _jspx_eval_s_005fselect_005f8 = _jspx_th_s_005fselect_005f8.doStartTag();
    if (_jspx_th_s_005fselect_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fclass_005fnobody.reuse(_jspx_th_s_005fselect_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fclass_005fnobody.reuse(_jspx_th_s_005fselect_005f8);
    return false;
  }

  private boolean _jspx_meth_s_005fselect_005f9(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f9 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fclass_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f9.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f9.setParent(null);
    // /WEB-INF/content/bis/bis-project-main.jsp(197,16) null
    _jspx_th_s_005fselect_005f9.setDynamicAttribute(null, "class", new String("required"));
    // /WEB-INF/content/bis/bis-project-main.jsp(197,16) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f9.setList("@com.hhz.ump.util.DictMapUtil@getMapPropertyRight()");
    // /WEB-INF/content/bis/bis-project-main.jsp(197,16) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f9.setListKey("key");
    // /WEB-INF/content/bis/bis-project-main.jsp(197,16) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f9.setListValue("value");
    // /WEB-INF/content/bis/bis-project-main.jsp(197,16) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f9.setName("bisStore.equityNature");
    // /WEB-INF/content/bis/bis-project-main.jsp(197,16) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f9.setId("equityNature");
    // /WEB-INF/content/bis/bis-project-main.jsp(197,16) name = onchange type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f9.setOnchange("showStatus(this);");
    int _jspx_eval_s_005fselect_005f9 = _jspx_th_s_005fselect_005f9.doStartTag();
    if (_jspx_th_s_005fselect_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fclass_005fnobody.reuse(_jspx_th_s_005fselect_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fclass_005fnobody.reuse(_jspx_th_s_005fselect_005f9);
    return false;
  }

  private boolean _jspx_meth_s_005fselect_005f10(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f10 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f10.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f10.setParent(null);
    // /WEB-INF/content/bis/bis-project-main.jsp(214,16) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f10.setId("manageStatus");
    // /WEB-INF/content/bis/bis-project-main.jsp(214,16) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f10.setCssClass("propRight");
    // /WEB-INF/content/bis/bis-project-main.jsp(214,16) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f10.setCssStyle("display:none");
    // /WEB-INF/content/bis/bis-project-main.jsp(214,16) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f10.setList("@com.hhz.ump.util.DictMapUtil@getBisManagementStatus()");
    // /WEB-INF/content/bis/bis-project-main.jsp(214,16) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f10.setListKey("key");
    // /WEB-INF/content/bis/bis-project-main.jsp(214,16) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f10.setListValue("value");
    // /WEB-INF/content/bis/bis-project-main.jsp(214,16) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f10.setName("bisStore.managementStatus");
    int _jspx_eval_s_005fselect_005f10 = _jspx_th_s_005fselect_005f10.doStartTag();
    if (_jspx_th_s_005fselect_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody.reuse(_jspx_th_s_005fselect_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody.reuse(_jspx_th_s_005fselect_005f10);
    return false;
  }

  private boolean _jspx_meth_s_005fselect_005f11(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f11 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f11.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f11.setParent(null);
    // /WEB-INF/content/bis/bis-project-main.jsp(234,16) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f11.setList("@com.hhz.ump.util.DictMapUtil@getMapShopManageType()");
    // /WEB-INF/content/bis/bis-project-main.jsp(234,16) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f11.setListKey("key");
    // /WEB-INF/content/bis/bis-project-main.jsp(234,16) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f11.setListValue("value");
    // /WEB-INF/content/bis/bis-project-main.jsp(234,16) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f11.setName("bisStore.shopPosition");
    int _jspx_eval_s_005fselect_005f11 = _jspx_th_s_005fselect_005f11.doStartTag();
    if (_jspx_th_s_005fselect_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fnobody.reuse(_jspx_th_s_005fselect_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fnobody.reuse(_jspx_th_s_005fselect_005f11);
    return false;
  }

  private boolean _jspx_meth_s_005fselect_005f12(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f12 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f12.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f12.setParent(null);
    // /WEB-INF/content/bis/bis-project-main.jsp(270,13) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f12.setCssClass("required");
    // /WEB-INF/content/bis/bis-project-main.jsp(270,13) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f12.setId("flat_houseShopFlag");
    // /WEB-INF/content/bis/bis-project-main.jsp(270,13) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f12.setName("bisFlat.houseShopFlag");
    // /WEB-INF/content/bis/bis-project-main.jsp(270,13) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f12.setList("@com.hhz.ump.util.DictMapUtil@getMapEnableFlgNum()");
    // /WEB-INF/content/bis/bis-project-main.jsp(270,13) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f12.setListKey("key");
    // /WEB-INF/content/bis/bis-project-main.jsp(270,13) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f12.setListValue("value");
    int _jspx_eval_s_005fselect_005f12 = _jspx_th_s_005fselect_005f12.doStartTag();
    if (_jspx_th_s_005fselect_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssClass_005fnobody.reuse(_jspx_th_s_005fselect_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssClass_005fnobody.reuse(_jspx_th_s_005fselect_005f12);
    return false;
  }

  private boolean _jspx_meth_s_005ftextfield_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:textfield
    org.apache.struts2.views.jsp.ui.TextFieldTag _jspx_th_s_005ftextfield_005f0 = (org.apache.struts2.views.jsp.ui.TextFieldTag) _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fonfocus_005fonblur_005fname_005fid_005fnobody.get(org.apache.struts2.views.jsp.ui.TextFieldTag.class);
    _jspx_th_s_005ftextfield_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005ftextfield_005f0.setParent(null);
    // /WEB-INF/content/bis/bis-project-main.jsp(300,16) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f0.setName("bisFlat.openDate");
    // /WEB-INF/content/bis/bis-project-main.jsp(300,16) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f0.setId("flat_openDate");
    // /WEB-INF/content/bis/bis-project-main.jsp(300,16) name = onfocus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f0.setOnfocus("WdatePicker()");
    // /WEB-INF/content/bis/bis-project-main.jsp(300,16) name = onblur type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f0.setOnblur("updateContStatus('plan');");
    int _jspx_eval_s_005ftextfield_005f0 = _jspx_th_s_005ftextfield_005f0.doStartTag();
    if (_jspx_th_s_005ftextfield_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fonfocus_005fonblur_005fname_005fid_005fnobody.reuse(_jspx_th_s_005ftextfield_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fonfocus_005fonblur_005fname_005fid_005fnobody.reuse(_jspx_th_s_005ftextfield_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fselect_005f13(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f13 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f13.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f13.setParent(null);
    // /WEB-INF/content/bis/bis-project-main.jsp(381,16) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f13.setList("@com.hhz.ump.util.DictMapUtil@getMapHouseStru()");
    // /WEB-INF/content/bis/bis-project-main.jsp(381,16) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f13.setListKey("key");
    // /WEB-INF/content/bis/bis-project-main.jsp(381,16) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f13.setListValue("value");
    // /WEB-INF/content/bis/bis-project-main.jsp(381,16) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f13.setName("bisFlat.houseStruCd");
    // /WEB-INF/content/bis/bis-project-main.jsp(381,16) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f13.setId("flat_houseStruCd");
    // /WEB-INF/content/bis/bis-project-main.jsp(381,16) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f13.setCssStyle("width:100%;");
    int _jspx_eval_s_005fselect_005f13 = _jspx_th_s_005fselect_005f13.doStartTag();
    if (_jspx_th_s_005fselect_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f13);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f13);
    return false;
  }

  private boolean _jspx_meth_s_005fselect_005f14(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f14 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f14.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f14.setParent(null);
    // /WEB-INF/content/bis/bis-project-main.jsp(387,16) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f14.setList("@com.hhz.ump.util.DictMapUtil@getMapFlatLayout()");
    // /WEB-INF/content/bis/bis-project-main.jsp(387,16) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f14.setListKey("key");
    // /WEB-INF/content/bis/bis-project-main.jsp(387,16) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f14.setListValue("value");
    // /WEB-INF/content/bis/bis-project-main.jsp(387,16) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f14.setName("bisFlat.layoutCd");
    // /WEB-INF/content/bis/bis-project-main.jsp(387,16) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f14.setId("flat_layoutCd");
    // /WEB-INF/content/bis/bis-project-main.jsp(387,16) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f14.setCssStyle("width:100%;");
    int _jspx_eval_s_005fselect_005f14 = _jspx_th_s_005fselect_005f14.doStartTag();
    if (_jspx_th_s_005fselect_005f14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f14);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f14);
    return false;
  }
}
