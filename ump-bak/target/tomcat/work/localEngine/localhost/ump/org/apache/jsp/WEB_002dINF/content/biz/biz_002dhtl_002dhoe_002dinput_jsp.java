package org.apache.jsp.WEB_002dINF.content.biz;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.JspUtil;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

public final class biz_002dhtl_002dhoe_002dinput_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(3);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/common/global.jsp");
    _jspx_dependants.add("/WEB-INF/PowerDesk-tags.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fstyle_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005felse;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifNotGranted;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fstyle_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005felse = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifNotGranted = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fstyle_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.release();
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.release();
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fs_005felse.release();
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
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
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/common.css\" type=\"text/css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/TreePanel.css\" type=\"text/css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/prompt/skin/pd/ymPrompt.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/prompt/skin/custom_1/ymPrompt.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/desk/thickbox.css\"  />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/biz/biz-rela.css\" type=\"text/css\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/common.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.select.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.form.pack.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.quickSearch.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/desk/MaskLayer.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/customInput/customInput.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/prompt/ymPrompt.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/datePicker/WdatePicker.js\" ></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/validate/formatUtil.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/validate/PdValidate.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/biz/biz-htl-hoe!save.action\" method=\"post\" id=\"bizHtlHoeForm\">\r\n");
      out.write("<input type=\"hidden\" id=\"recordVersion\" name=\"recordVersion\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entity.recordVersion}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("<input type=\"hidden\" id=\"hotelCd\" name=\"hotelCd\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entity.hotelCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("<input type=\"hidden\" id=\"bizHtlHoeId\" name=\"bizHtlHoeId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entity.bizHtlHoeId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("<input type=\"hidden\" id=\"hoeTypeCd\" name=\"hoeTypeCd\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entity.hoeTypeCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("<input type=\"hidden\" id=\"bizHoeAttaId\" name=\"bizHoeAttaId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entity.bizHoeAttaId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("<input type=\"hidden\" id=\"noSearch\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${noSearch}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("<!--<input type=\"hidden\" id=\"h_hoeTypeCd\" name=\"h_hoeTypeCd\" />\r\n");
      out.write("<input type=\"hidden\" id=\"h_hotelCd\" name=\"h_hotelCd\" />-->\r\n");
      out.write("<div id=\"div_main_cont\" style=\"margin-left: 0px;margin-top: 10px;\">\r\n");
      out.write("\t <fieldset style=\"padding-left: 10px;padding-right: 10px;\">\r\n");
      out.write("\t <legend>&nbsp;&nbsp;\r\n");
      out.write("\t 酒店名称：");
      if (_jspx_meth_s_005fselect_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t &nbsp;&nbsp;品名类别：<input style=\"width: 80px;\" type=\"text\"    name=\"h_hoeTypeName\" id=\"h_hoeTypeName\" class=\"query\" />\r\n");
      out.write("\t &nbsp;&nbsp;HOE名称：<input style=\"width: 80px;\" type=\"text\"    name=\"h_hoeName\" id=\"h_hoeName\" class=\"query\"/>\r\n");
      out.write("\t <input  class=\"btn_green\" type=\"button\" onclick=\"bizHtlSearch();\" value=\"搜索\" />\r\n");
      out.write("\t ");
      if (_jspx_meth_security_005fauthorize_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t </legend>\r\n");
      out.write("\t </fieldset>\r\n");
      out.write("\t<div  id=\"bizHtlAdd\" >\r\n");
      out.write("\t<fieldset style=\"padding-left: 10px;padding-right: 10px;border:#DBE5F1 \">\r\n");
      out.write("\t\t <legend >&nbsp;&nbsp;&nbsp;HOE库&nbsp;<font color=\"red\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${savesuccess}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</font></legend>\r\n");
      out.write("\t\t <table class=\"shop-table\">\r\n");
      out.write("\t          <col width=\"100\"/>\r\n");
      out.write("\t\t      <col />\r\n");
      out.write("\t\t\t  <col width=\"100\"/>\r\n");
      out.write("\t\t\t  <col />\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t     <td><font style=\"color:red;\">*</font>酒店名称</td>\r\n");
      out.write("\t\t\t     <td><input type=\"text\" name=\"hotelName\" id=\"hotelName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entity.hotelName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" validate=\"required\" /></td>\t\t\t\r\n");
      out.write("\t\t\t  \t<td><font style=\"color:red;\">*</font>品名类别</td>\r\n");
      out.write("\t\t      \t<td><input type=\"text\"    name=\"hoeTypeName\" id=\"hoeTypeName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entity.hoeTypeName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" validate=\"required\" /></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t   <td><font style=\"color:red;\">*</font>供应商名称</td>\r\n");
      out.write("\t\t\t   <td colspan=\"3\"><input type=\"text\"    name=\"supName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entity.supName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" validate=\"required\" /></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t   <td><font style=\"color:red;\">*</font>HOE名称</td>\r\n");
      out.write("\t\t       <td><input type=\"text\" name=\"hoeName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entity.hoeName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" validate=\"required\" /> </td>\r\n");
      out.write("\t\t\t   <td>规格型号</td>\r\n");
      out.write("\t\t       <td><input type=\"text\" name=\"hoeModel\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entity.hoeModel}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /> </td>\r\n");
      out.write("\t\t\t </tr>\r\n");
      out.write("\t\t\t <tr>\r\n");
      out.write("\t\t\t   <td>数量</td>\r\n");
      out.write("\t\t       <td><input type=\"text\"   name=\"hoeAmount\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entity.hoeAmount}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" alt=\"amount\"  maxlength=\"9\"/> </td>\r\n");
      out.write("\t\t  \t   <td>序号</td>\r\n");
      out.write("\t\t \t   <td><input type=\"text\" name=\"sequenceNo\"  value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entity.sequenceNo}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" id=\"sequenceNo\" alt=\"amount\" maxlength=\"9\"/></td>\r\n");
      out.write("\t\t\t </tr>\r\n");
      out.write("\t\t\t <tr>\r\n");
      out.write("\t\t\t   <td>单价</td>\r\n");
      out.write("\t\t\t   <td><input type=\"text\"  name=\"singelPrice\" alt=\"amount\"  value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entity.singelPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" maxlength=\"9\"/></td>\r\n");
      out.write("\t\t\t   <td>总价</td>\r\n");
      out.write("\t\t\t   <td><input type=\"text\" name=\"totalPrice\" id=\"totalPrice\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entity.totalPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" alt=\"amount\"  maxlength=\"9\"/></td>\r\n");
      out.write("\t\t\t </tr>\r\n");
      out.write("\t\t\t  <tr>\r\n");
      out.write("\t\t\t   <td rowspan=\"3\">说明</td>\r\n");
      out.write("\t\t       <td colspan=\"3\" rowspan=\"3\"><textarea id=\"remark\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entity.remark}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" style=\"width:100%;height:100px;background-color: #DBE5F1;border: none;\" name=\"remark\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entity.remark}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</textarea></td>\r\n");
      out.write("\t\t\t </tr>\r\n");
      out.write("\t\t\t <tr></tr>\r\n");
      out.write("\t\t\t <tr></tr>\r\n");
      out.write("\t\t\t <tr>\r\n");
      out.write("\t \t\t\t<td align=\"right\">酒店Hoe附件:</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"3\">\r\n");
      out.write("\t\t\t\t  <div class=\"atta_readOnly\" style=\"float:left;cursor: pointer; display: none;\"  onclick=\"showAttachment('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entity.bizHoeAttaId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("')\">\r\n");
      out.write("\t\t\t\t\t   ");
      if (_jspx_meth_s_005fif_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_s_005felse_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t查看附件\r\n");
      out.write("\t\t\t\t </div>\r\n");
      out.write("\t\t\t\t <div class=\"atta_edit\" style=\"float:left;cursor: pointer;\"  onclick=\"openAttachment(this,'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${entity.bizHoeAttaId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("','bizHoeAttaId')\">\r\n");
      out.write("\t\t\t\t       ");
      if (_jspx_meth_s_005fif_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_s_005felse_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t添加附件\r\n");
      out.write("\t\t\t\t  </div>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t </tr>\r\n");
      out.write("\t\t </table>\r\n");
      out.write("\t\t <div style=\"float:left;font-size:12px;padding-right:20px;line-height:30px;width:100%;\">\r\n");
      out.write("\t\t\t<table  class=\"main_content\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"  style=\"width:100%;height:100%;table-layout:fixed\">\r\n");
      out.write("\t\t\t\t<col width=\"750\"/>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td   style=\"padding-right: 0px\">\r\n");
      out.write("\t\t\t\t\t<input  class=\"btn_green\"  type=\"button\" onclick=\"saveBizHtl();\" value=\"保存\"/>\r\n");
      out.write("\t\t\t\t\t<input  class=\"btn_green\"  type=\"button\" onclick=\"outBizHtl();\" value=\"取消\"/>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t </fieldset>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\t <div id=\"bizHtlList\" style=\"padding: 10px 10px 0px;\"></div>\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var curr_user_cont = '");
      out.print( SpringSecurityUtils.getCurrentUiid());
      out.write("';\r\n");
      out.write("$(function(){\r\n");
      out.write("\t");
      if (_jspx_meth_security_005fauthorize_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t$('input[alt=\"amount\"]').live('keyup',function(){\r\n");
      out.write("\t\tclearNoNum_1(this);\r\n");
      out.write("\t\tif($('.percent').val()>100){\r\n");
      out.write("\t\t\tthis.value=0;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\tvar noSearch=$('#noSearch').val();//阻止搜索事件\r\n");
      out.write("\tif(noSearch!=\"noSearch\"){\r\n");
      out.write("\t\tbizHtlSearch();\r\n");
      out.write("\t}\r\n");
      out.write("});\r\n");
      out.write("$(\"#hotelName\").quickSearch(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/biz/biz-htl-hoe!quickSearch.action\",['dictName'],{dictCd:'hotelCd',dictName:\"hotelName\"});\r\n");
      out.write("$(\"#hoeTypeName\").quickSearch(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/biz/biz-htl-hoe!quickSearchHoe.action\",['dictName'],{dictCd:'hoeTypeCd',dictName:\"hoeTypeName\"});\r\n");
      out.write("function bizHtlAdd(){\r\n");
      out.write("\t$(\"input[name='hotelCd']\").val('');\r\n");
      out.write("\t$(\"input[name='hoeTypeName']\").val('');\r\n");
      out.write("\t$(\"input[name='hoeName']\").val('');\r\n");
      out.write("\t$('#bizHtlAdd').show();\r\n");
      out.write("\t$('#bizHtlList').hide();\r\n");
      out.write("}\r\n");
      out.write("function outBizHtl(){\r\n");
      out.write("\t$('#bizHtlList').show();\r\n");
      out.write("\t$('#bizHtlAdd').hide();\r\n");
      out.write("}\r\n");
      out.write("function saveBizHtl(){\r\n");
      out.write("\tvar pdv = new Validate(\"bizHtlHoeForm\");\r\n");
      out.write("\tif (pdv.validate()) {\r\n");
      out.write("\t\t$('#bizHtlHoeForm').submit();\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function convertNameSea(){\r\n");
      out.write("\t$(\"input[name='hotelCd']\").val($('#h_hotelName').val());\r\n");
      out.write("\t$(\"input[name='hoeTypeName']\").val($('#h_hoeTypeName').val());\r\n");
      out.write("\t$(\"input[name='hoeName']\").val($('#h_hoeName').val());\r\n");
      out.write("}\r\n");
      out.write("function bizHtlSearch(pageNo){\r\n");
      out.write("\t\t$('#bizHtlList').show();\r\n");
      out.write("\t\t$('#bizHtlAdd').hide();\r\n");
      out.write("\t\tconvertNameSea();\r\n");
      out.write("\t\tvar hotelCd=$('#h_hotelName').val();\r\n");
      out.write("\t\tvar hoeTypeName=$('#h_hoeTypeName').val();\r\n");
      out.write("\t\tvar hoeName=$('#h_hoeName').val();\r\n");
      out.write("\t\tTB_showMaskLayer(\"正在搜索请稍后...\");\r\n");
      out.write("\t\tvar data={\r\n");
      out.write("\t\t\t\thotelCd:hotelCd,\r\n");
      out.write("\t\t\t\thoeTypeName:hoeTypeName,\r\n");
      out.write("\t\t\t\thoeName:hoeName\r\n");
      out.write("\t\t    };\r\n");
      out.write("\t\tif(pageNo!=\"\"&&null!=pageNo){\r\n");
      out.write("\t\t\tvar data={\r\n");
      out.write("\t\t\t\t\thotelCd:hotelCd,\r\n");
      out.write("\t\t\t\t\thoeTypeName:hoeTypeName,\r\n");
      out.write("\t\t\t\t\thoeName:hoeName,\r\n");
      out.write("\t\t\t\t\t\"page.pageNo\":pageNo\r\n");
      out.write("\t\t\t    };\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$.post(_ctx+\"/biz/biz-htl-hoe!list.action\",\r\n");
      out.write("\t\t\t\tdata,\r\n");
      out.write("\t\t\t\tfunction(result) {\r\n");
      out.write("\t\t\t\t\tif (result) {\r\n");
      out.write("\t\t\t\t\t\t$(\"#bizHtlList\").html(result);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tTB_removeMaskLayer();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t);\r\n");
      out.write("}\r\n");
      out.write("$(\".query\").keydown(function(event){\r\n");
      out.write("    if(event.keyCode==13){ \r\n");
      out.write("       //doSth\r\n");
      out.write("  \t  bizHtlSearch();\r\n");
      out.write("    }\r\n");
      out.write(" }); \r\n");
      out.write("function bizHitImport(){\r\n");
      out.write("\tymPrompt.confirmInfo( {\r\n");
      out.write("\t\ticoCls : \"\",\r\n");
      out.write("\t\tautoClose:false,\r\n");
      out.write("\t\tmessage : \"<div id='bizHtlImport'><img align='absMiddle' src='\"\r\n");
      out.write("\t\t\t+ _ctx + \"/images/loading.gif'></div>\",\r\n");
      out.write("\t\twidth : 330,\r\n");
      out.write("\t\theight : 140,\r\n");
      out.write("\t\ttitle : \"导入公共关系库\",\r\n");
      out.write("\t\tcloseBtn:true,\r\n");
      out.write("\t\tafterShow : function() {\r\n");
      out.write("\t\t\tvar url = _ctx+\"/biz/biz-htl-hoe!bizHtlImport.action\";\r\n");
      out.write("\t\t\t$.post(url,{}, function(result) {\r\n");
      out.write("\t\t\t\t$(\"#bizHtlImport\").html(result);\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\thandler : function(btn){\r\n");
      out.write("\t\t\tif(btn=='ok'){\r\n");
      out.write("\t\t\t\t\t//$('#bizRelaDetaForm').ajaxSubmit(function(result) { \r\n");
      out.write("\t\t\t\t\t//\tbizRelaSearch();\r\n");
      out.write("\t\t\t\t\t//});\r\n");
      out.write("\t\t\t\t$('#htlUplodForm').submit();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("//\t\t\t\tdoInput(bigTypeId, smallTypeId);\r\n");
      out.write("//\t\t\t}\r\n");
      out.write("\t\t\tymPrompt.close();\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tbtn:[[\"确定\",'ok'],[\"取消\",'cancel']]\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("function jumpPage(pageNo) {\r\n");
      out.write("\t$(\"#pageNo\").val(pageNo);\r\n");
      out.write("\t$(\"#bizHtlHoeForm\").attr(\"action\", \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/biz/biz-htl-hoe!list.action\");\r\n");
      out.write("\t$(\"#bizHtlHoeForm\").ajaxSubmit(function(result) {\r\n");
      out.write("\t\t$(\"#bizHtlList\").html(result);\r\n");
      out.write("\t});\r\n");
      out.write("\t$(\"#bizHtlHoeForm\").attr(\"action\", \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/biz/biz-htl-hoe!save.action\");\r\n");
      out.write("}\r\n");
      out.write("function jumpPageTo() {\r\n");
      out.write("\tvar index = $(\"#pageTo\").val();\r\n");
      out.write("\tindex = parseInt(index);\r\n");
      out.write("\tif (index > 0) {\r\n");
      out.write("\t\tjumpPage(index);\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function showAttachment(entityId){\r\n");
      out.write("\tymPrompt.win({\r\n");
      out.write("\t\tmessage:_ctx+\"/app/app-attachment!list.action?bizEntityId=\"+entityId+\"&bizModuleCd=bizHtlHoe&filterType=image|office&onlyShow=true\",\r\n");
      out.write("\t\twidth:500,\r\n");
      out.write("\t\theight:300,\r\n");
      out.write("\t\ttitle: '附件查看',\r\n");
      out.write("\t\tafterShow:function(){},\r\n");
      out.write("\t\tiframe:true\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("function openAttachment(dom,entityId,hiddenId,title){\r\n");
      out.write("\tvar _title = title||'上传附件';\r\n");
      out.write("\tif(!entityId||entityId == ''){\r\n");
      out.write("\t\t//var hiddenId = $(dom).children(\":hidden\");\r\n");
      out.write("\t\tif($.trim($('#'+hiddenId).val()) == ''){\r\n");
      out.write("\t\t\tentityId = 'agree_'+curr_user_cont+'_'+String((new Date().getTime()) ^ Math.random());\r\n");
      out.write("\t\t\t$('#'+hiddenId).val(entityId);\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tentityId = $('#'+hiddenId).val();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tymPrompt.win({\r\n");
      out.write("\t\tmessage:_ctx+\"/app/app-attachment!list.action?bizEntityId=\"+entityId+\"&bizModuleCd=bizHtlHoe&filterType=image|office\",\r\n");
      out.write("\t\twidth:500,\r\n");
      out.write("\t\theight:300,\r\n");
      out.write("\t\ttitle: _title,\r\n");
      out.write("\t\tafterShow:function(){},\r\n");
      out.write("\t\tiframe:true\r\n");
      out.write("\t\t});\r\n");
      out.write("}\r\n");
      out.write("function bizHtlEdit(id){\r\n");
      out.write("\t$('#id').val(id);\r\n");
      out.write("\t$('#bizHtlListForm').submit();\r\n");
      out.write("\t\t\t/**$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/biz/biz-htl-hoe!bizHtlDetail.action\",\r\n");
      out.write("\t\t\t\t\tdata,\r\n");
      out.write("\t\t\t\t\tfunction(data) {\r\n");
      out.write("\t\t\t\t\t\tvar data = eval('('+data+')');\r\n");
      out.write("\t\t\t\t\t\t$.each(data,function(i,n){\r\n");
      out.write("\t\t\t\t\t\t     $('#'+i).val(n);\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t);**/\r\n");
      out.write("}\r\n");
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
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f0 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fstyle_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f0.setParent(null);
    // /WEB-INF/content/biz/biz-htl-hoe-input.jsp(41,7) null
    _jspx_th_s_005fselect_005f0.setDynamicAttribute(null, "style", new String("width: 120px;"));
    // /WEB-INF/content/biz/biz-htl-hoe-input.jsp(41,7) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setName("h_hotelName");
    // /WEB-INF/content/biz/biz-htl-hoe-input.jsp(41,7) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setId("h_hotelName");
    // /WEB-INF/content/biz/biz-htl-hoe-input.jsp(41,7) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setList("@com.hhz.ump.util.DictMapUtil@getMapHotelName()");
    // /WEB-INF/content/biz/biz-htl-hoe-input.jsp(41,7) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setListKey("key");
    // /WEB-INF/content/biz/biz-htl-hoe-input.jsp(41,7) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setListValue("value");
    // /WEB-INF/content/biz/biz-htl-hoe-input.jsp(41,7) name = onchange type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setOnchange("bizHtlSearch();");
    int _jspx_eval_s_005fselect_005f0 = _jspx_th_s_005fselect_005f0.doStartTag();
    if (_jspx_th_s_005fselect_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fstyle_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.reuse(_jspx_th_s_005fselect_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fstyle_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.reuse(_jspx_th_s_005fselect_005f0);
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
    // /WEB-INF/content/biz/biz-htl-hoe-input.jsp(45,2) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f0.setIfAnyGranted("A_BIZ_HOTEL_ADMIN");
    int _jspx_eval_security_005fauthorize_005f0 = _jspx_th_security_005fauthorize_005f0.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t <input   class=\"btn_green\" type=\"button\" onclick=\"bizHitImport();\" value=\"导入\" />\r\n");
        out.write("\t <input   class=\"btn_orange\" type=\"button\" onclick=\"bizHtlAdd();\" value=\"新增\" />\r\n");
        out.write("\t ");
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

  private boolean _jspx_meth_s_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f0 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f0.setParent(null);
    // /WEB-INF/content/biz/biz-htl-hoe-input.jsp(97,8) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f0.setTest("bizHoeAttaList.get(0)==0||bizHoeAttaList.get(0)==null");
    int _jspx_eval_s_005fif_005f0 = _jspx_th_s_005fif_005f0.doStartTag();
    if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t<img align=\"top\"  id=\"agree_atta_id\" style=\"margin-top:5px;\" src=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("/resources/images/common/atta.gif\" />\r\n");
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
        out.write("\r\n");
        out.write("\t\t\t\t\t\t<img align=\"top\"  id=\"agree_atta_id\" style=\"margin-top:5px;\" src=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("/resources/images/common/atta_y.gif\" />\r\n");
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

  private boolean _jspx_meth_s_005fif_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f1 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f1.setParent(null);
    // /WEB-INF/content/biz/biz-htl-hoe-input.jsp(106,11) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f1.setTest("bizHoeAttaList.get(0)==0||bizHoeAttaList.get(0)==null");
    int _jspx_eval_s_005fif_005f1 = _jspx_th_s_005fif_005f1.doStartTag();
    if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t<img align=\"top\"  id=\"agree_atta_id\" style=\"margin-top:5px;\" src=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("/resources/images/common/atta.gif\" />\r\n");
        out.write("\t\t\t\t\t\t");
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
        out.write("\r\n");
        out.write("\t\t\t\t\t\t<img align=\"top\"  id=\"agree_atta_id\" style=\"margin-top:5px;\" src=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("/resources/images/common/atta_y.gif\" />\r\n");
        out.write("\t\t\t\t\t\t");
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

  private boolean _jspx_meth_security_005fauthorize_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f1 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifNotGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f1.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f1.setParent(null);
    // /WEB-INF/content/biz/biz-htl-hoe-input.jsp(137,1) name = ifNotGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f1.setIfNotGranted("A_BIZ_HOTEL_ADMIN");
    int _jspx_eval_security_005fauthorize_005f1 = _jspx_th_security_005fauthorize_005f1.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t$(\".atta_edit\").hide();\r\n");
        out.write("\t$(\".atta_readOnly\").show();\r\n");
        out.write("\t$(\"#bizHtlAdd\").find(\":text,textarea\").css('backgroundColor','#dddbdc');\r\n");
        out.write("\t$(\"#bizHtlAdd\").find(\":text,textarea\").attr('readOnly',\"readOnly\");\r\n");
        out.write("\t");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifNotGranted.reuse(_jspx_th_security_005fauthorize_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifNotGranted.reuse(_jspx_th_security_005fauthorize_005f1);
    return false;
  }
}
