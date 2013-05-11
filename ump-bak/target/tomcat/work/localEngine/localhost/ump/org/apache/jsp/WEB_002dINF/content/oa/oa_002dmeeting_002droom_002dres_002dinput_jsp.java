package org.apache.jsp.WEB_002dINF.content.oa;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.JspUtil;

public final class oa_002dmeeting_002droom_002dres_002dinput_jsp extends org.apache.jasper.runtime.HttpJspBase
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
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fhidden_0026_005fvalue_005fname_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fname_005fid_005fcssStyle_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fonfocus_005fname_005fid_005fcssStyle_005fcssClass_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fname_005fcssStyle_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fradio_0026_005fvalue_005fname_005flist_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005ftextarea_0026_005frows_005fname_005fcssStyle_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fhidden_0026_005fvalue_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fname_005fid_005fcssStyle_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fonfocus_005fname_005fid_005fcssStyle_005fcssClass_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fname_005fcssStyle_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fradio_0026_005fvalue_005fname_005flist_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005ftextarea_0026_005frows_005fname_005fcssStyle_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fhidden_0026_005fvalue_005fname_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fname_005fid_005fcssStyle_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fonfocus_005fname_005fid_005fcssStyle_005fcssClass_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fname_005fcssStyle_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fradio_0026_005fvalue_005fname_005flist_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005ftextarea_0026_005frows_005fname_005fcssStyle_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody.release();
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t");
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
      out.write("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("\t\t<title>会议室预定</title>\r\n");
      out.write("\t\t<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/common.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("\t\t<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/desk/desk-oa.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/datePicker/WdatePicker.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.form.pack.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.select.js\"></script>\r\n");
      out.write("\t</head>\r\n");
      out.write("\t\r\n");
      out.write("\t<body>\r\n");
      out.write("\t\t<div class=\"title_bar\">\r\n");
      out.write("\t\t\t<div style=\"float:left; margin-left:8px; font-size:18px; font-weight:bolder;\">会议室预定</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div style=\"background-color: #5a5a5a;height:1px;margin-bottom:8px;\"></div>\r\n");
      out.write("\t\t<div>\r\n");
      out.write("\t\t\t<fieldset style=\"margin:0 5px;padding: 5px;\">\r\n");
      out.write("\t\t\t<div style=\"float:left;padding-left: 10px;width:490px;border-right: 1px solid #BFC4C8;\">\r\n");
      out.write("\t\t\t\t<form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-meeting-room-res!save.action\" method=\"post\" id=\"roomForm\">\r\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_s_005fhidden_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_s_005fhidden_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t<div style=\"font-weight:bold;margin-bottom: 10px;\"><span style=\"margin-right:5px;\">预定会议室</span><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/meetingRoom/pic_down_blue.gif\"/></div>\r\n");
      out.write("\t\t\t\t<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" class=\"editTable\">\r\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_s_005fif_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_s_005fif_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td width=\"80px\">会议主题<font color=\"red\">*</font></td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      if (_jspx_meth_s_005ftextfield_005f0(_jspx_page_context))
        return;
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>会议日期 <font color=\"red\">*</font></td>\r\n");
      out.write("\t\t\t\t\t\t<td >\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_s_005ftextfield_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\"  value=\"");
      if (_jspx_meth_s_005fdate_005f0(_jspx_page_context))
        return;
      out.write("\" name=\"meetingBegin\" id=\"meetingDateBeginId\" class=\"time\" style=\"width:80px;color:red;\" onfocus=\"WdatePicker({dateFmt:'HH:mm'})\"/>\r\n");
      out.write("\t\t\t\t\t\t\t到\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" value=\"");
      if (_jspx_meth_s_005fdate_005f1(_jspx_page_context))
        return;
      out.write("\" name=\"meetingEnd\" id=\"meetingDateEndId\" class=\"time\" style=\"width:80px;color:red;\" onfocus=\"WdatePicker({dateFmt:'HH:mm'})\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>主持人 <font color=\"red\">*</font></td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"compereName\" value=\"");
      out.print(CodeNameUtil.getUserNameByCd(JspUtil.findString("compere")) );
      out.write("\" onkeyup=\"showSearchUser(this)\"/>\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_s_005fhidden_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>召集人 <font color=\"red\">*</font></td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"applicantName\" value=\"");
      out.print(CodeNameUtil.getUserNameByCd(JspUtil.findString("applicant")) );
      out.write("\" onkeyup=\"showSearchUser(this)\"/>\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_s_005fhidden_005f3(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>记录人<font color=\"red\">*</font></td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"recorderName\" value=\"");
      out.print(CodeNameUtil.getUserNameByCd(JspUtil.findString("recorder")) );
      out.write("\" onkeyup=\"showSearchUser(this)\"/>\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_s_005fhidden_005f4(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>参会人员<font color=\"red\">*</font></td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<textarea id=\"otherUserNames\" name=\"participatorNames\" style=\"width:350px;\" rows=\"2\" readonly=\"readonly\">");
      out.print(CodeNameUtil.getUserNamesByUiids(JspUtil.findString("participators"),";") );
      out.write("</textarea>\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_s_005fhidden_005f5(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<span onclick=\"$('#otherUserNames').trigger('click')\" style=\"font-weight: bolder;cursor: pointer;text-decoration: underline\">选择</span>\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>参会人数<font color=\"red\">*</font></td>\r\n");
      out.write("\t\t\t\t\t\t<td>约&nbsp;&nbsp;");
      if (_jspx_meth_s_005ftextfield_005f2(_jspx_page_context))
        return;
      out.write("&nbsp;&nbsp;人</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>涉及部门</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      if (_jspx_meth_s_005ftextfield_005f3(_jspx_page_context))
        return;
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>隐藏主题</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"encryptFlg\" value=\"1\" />\r\n");
      out.write("\t\t\t\t\t\t\t<span class=\"red\">注:隐藏主题后,只有参会人员才能看到主题</span>\r\n");
      out.write("\t\t\t\t\t\t\t<!--");
      if (_jspx_meth_s_005fradio_005f0(_jspx_page_context))
        return;
      out.write(" -->\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr >\r\n");
      out.write("\t\t\t\t\t\t<td>注意事项</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      if (_jspx_meth_s_005ftextarea_005f0(_jspx_page_context))
        return;
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>备注</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      if (_jspx_meth_s_005ftextarea_005f1(_jspx_page_context))
        return;
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr style=\"height:10px;\">\r\n");
      out.write("\t\t\t\t\t<td colspan=\"2\">&nbsp;</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr style=\"height:50px;\">\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"2\" align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"button\" class=\"btn_blue\" onclick=\"saveRes();\" value=\"预定\" />\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"button\" class=\"btn_red\" onclick=\"cancel();\" value=\"取消\" />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</table>\t\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div style=\"margin-left:510px;max-width: 300px;\">\r\n");
      out.write("\t\t\t\t\t<div style=\"font-weight:bold;\">\r\n");
      out.write("\t\t\t\t\t<span style=\"margin-right:5px;\">查看会议室预定情况</span><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/meetingRoom/pic_down_blue.gif\"/>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div style=\"margin-top: 10px;margin-bottom: 10px;\"><span style=\"margin-right:5px;\">选择日期</span>\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currDay}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" style=\"width:70px;\" onfocus=\"WdatePicker({minDate:'%y-%M-%d',onpicked:function(dp){changeMeetingDate(dp);}})\"/>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div id=\"meetingRoomResCon\"></div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t</fieldset>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div id=\"searchUserDiv\" class=\"searchUserDiv\"></div>\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\tvar cfg = {};\r\n");
      out.write("\t\t\tcfg.data = {tabId:'resMeeting'};\r\n");
      out.write("\t\t\tfunction saveRes(){\r\n");
      out.write("\t\t\t\tif(!checkForm())return;\r\n");
      out.write("\t\t\t\tvar confStr = \"请确认会议室信息：\\r\\r\";\r\n");
      out.write("\t\t\t\tvar roomName= [];\r\n");
      out.write("\t\t\t\t$(\"#roomForm div input[name='roomIds']:checked\").each(function(){\r\n");
      out.write("\t\t\t\t\troomName.push($(this).next().text());\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\tconfStr += roomName.join(\",\")+\"。   \\r\";\r\n");
      out.write("\t\t\t\tvar dateStr = $(\"#meetingDateId\").val()+\"   \"+$(\"#meetingDateBeginId\").val()+\" ~ \"+$(\"#meetingDateEndId\").val();\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tif(!confirm(confStr+dateStr))return;\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t$(\"#roomForm\").ajaxSubmit(function(result){\r\n");
      out.write("\t\t\t\t\tif(result == \"success\"){\r\n");
      out.write("\t\t\t\t\t\talert(\"申请已提交成功,等待管理员审批！\");\r\n");
      out.write("\t\t\t\t\t\twindow.parent.TabUtils.closeTab(cfg);\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\twindow.parent.TabUtils.closeTab(cfg);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tfunction checkForm(){\r\n");
      out.write("\t\t\t\tvar str = [];\r\n");
      out.write("\t\t\t\tif($(\"#roomForm div input[name='roomIds']:checked\").length == 0){\r\n");
      out.write("\t\t\t\t\tstr.push(\"请选择会议室\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif($.trim($(\"#subject\").val()) == \"\"){\r\n");
      out.write("\t\t\t\t\tstr.push(\"主题不能为空\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif($(\"#meetingDateId\").val() == \"\"||$(\"#meetingDateBeginId\").val() == \"\"||$(\"#meetingDateEndId\").val() == \"\"){\r\n");
      out.write("\t\t\t\t\tstr.push(\"会议日期不能为空\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif($.trim($(\"#compere\").val()) == \"\"){\r\n");
      out.write("\t\t\t\t\tstr.push(\"请选择主持人\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif($.trim($(\"#applicant\").val()) == \"\"){\r\n");
      out.write("\t\t\t\t\tstr.push(\"请选择召集人\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif($.trim($(\"#recorder\").val()) == \"\"){\r\n");
      out.write("\t\t\t\t\tstr.push(\"请选择记录人\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif($.trim($(\"#otherUserCds\").val()) == \"\"){\r\n");
      out.write("\t\t\t\t\tstr.push(\"请选择参会人员\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif($.trim($(\"#otherNum\").val()) == \"\"){\r\n");
      out.write("\t\t\t\t\tstr.push(\"请填写参会人数\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif(str.length>0){\r\n");
      out.write("\t\t\t\t\talert(str.join(\"\\r\"));\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\treturn true;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tfunction cancel(){\r\n");
      out.write("\t\t\t\twindow.parent.TabUtils.closeTab(cfg);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\tvar searchTime;\r\n");
      out.write("\t\t\tfunction showSearchUser(dom){\r\n");
      out.write("\t\t\t\tvar $currentDom = $(dom);\r\n");
      out.write("\t\t\t\tvar $next = $(dom).next();\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tif(searchTime)clearTimeout(searchTime);\r\n");
      out.write("\t\t\t\tsearchTime = setTimeout(function(){\r\n");
      out.write("\t\t\t\t\tvar val = $.trim($currentDom.val());\r\n");
      out.write("\t\t\t\t\t$next.val(\"\");\r\n");
      out.write("\t\t\t\t\tif(val == \"\"){\r\n");
      out.write("\t\t\t\t\t\t$(\"#searchUserDiv\").slideUp(200);\r\n");
      out.write("\t\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-email!getUsersByFilter.action\",{value:val,maxNum:10},function(result){\r\n");
      out.write("\t\t\t\t\t\tresult = eval(result);\r\n");
      out.write("\t\t\t\t\t\tvar $offset = $currentDom.offset();\r\n");
      out.write("\t\t\t\t\t\t$(\"#searchUserDiv\").css({left:$offset.left,top:$offset.top+$currentDom.height()}).empty().show();\r\n");
      out.write("\t\t\t\t\t\t$.each(result,function(i,n){\r\n");
      out.write("\t\t\t\t\t\t\tvar html = \"<div uiid='\"+n.uiid+\"'><span>\"+n.userName +\"</span>|<span>\"+ n.orgName+\"</span></div>\";\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#searchUserDiv\").append(html);\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t$(\"#searchUserDiv div\").click(function(){\r\n");
      out.write("\t\t\t\t\t\t\tvar userName = $(this).children(\"span:first\").text();\r\n");
      out.write("\t\t\t\t\t\t\tvar uiid = $(this).attr(\"uiid\");\r\n");
      out.write("\t\t\t\t\t\t\t$currentDom.val(userName);\r\n");
      out.write("\t\t\t\t\t\t\t$next.val(uiid);\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#searchUserDiv\").slideUp(200);\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t//$(\"#searchUserDiv\").focus();\r\n");
      out.write("\t\t\t\t\t\t//$(dom).focus();\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}, 300);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t$(document).bind('click',function(event){\r\n");
      out.write("\t\t\t\t\tvar event  = window.event || event;\r\n");
      out.write("\t\t\t\t    var obj = event.srcElement ? event.srcElement : event.target;\r\n");
      out.write("\t\t\t\t    if(obj != dom){\r\n");
      out.write("\t\t\t\t    \t$(\"#searchUserDiv\").slideUp(200);\r\n");
      out.write("\t\t\t\t    \tif($next.val() == ''){\r\n");
      out.write("\t\t\t\t    \t\t$currentDom.val('');\r\n");
      out.write("\t\t\t\t    \t}\r\n");
      out.write("\t\t\t\t    }\r\n");
      out.write("\t\t\t\t    $(document).unbind('click');\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\tfunction changeMeetingDate(dp){\r\n");
      out.write("\t\t\t\trefreshResInfo(dp.cal.getDateStr());\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tfunction refreshResInfo(meetingDate){\r\n");
      out.write("\t\t\t\t$(\"#meetingRoomResCon\").addClass(\"waiting\");\r\n");
      out.write("\t\t\t\tvar param = {\"simple\":\"1\"};\r\n");
      out.write("\t\t\t\tparam.addrType = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${addrType}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\t\t\t\tif(meetingDate){\r\n");
      out.write("\t\t\t\t\tparam.currDay = meetingDate;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-meeting-room-res!res.action\",param,function(result){\r\n");
      out.write("\t\t\t\t\t$(\"#meetingRoomResCon\").html(result).removeClass(\"waiting\");\r\n");
      out.write("\t\t\t\t\tautoHeight();\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t/*\r\n");
      out.write("\t\t\t*旧的人员选择框\r\n");
      out.write("\t\t\tfunction chooseUser(){\r\n");
      out.write("\t\t\t\tgetMember('other','','0');\r\n");
      out.write("\t\t\t}*/\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t$(function(){\r\n");
      out.write("\t\t\t\t//人员选择框初始化\r\n");
      out.write("\t\t\t\t$('#otherUserNames').ouSelect({\r\n");
      out.write("\t\t\t\t\tshowGroupFlg : true\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t$(\"#roomForm div input[name='roomIds']\").click(function(){\r\n");
      out.write("\t\t\t\t\tif($(this).attr(\"checked\")==true){\r\n");
      out.write("\t\t\t\t\t\t$(this).next().addClass(\"red\");\r\n");
      out.write("\t\t\t\t\t\tif($(this).attr('id') == 'roomIds-5'){\r\n");
      out.write("\t\t\t\t\t\t\talert('提示：\\r该会议室使用投影仪请将电脑屏幕分辨率调整至1024*768或以下\\r如有问题,请联系万记方(13761460105/2338)!');\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t$(this).next().removeClass(\"red\");\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\tvar roomIds = \"");
      if (_jspx_meth_s_005fproperty_005f0(_jspx_page_context))
        return;
      out.write("\";\r\n");
      out.write("\t\t\t\troomIds = roomIds.split(\",\");\r\n");
      out.write("\t\t\t\t$.each(roomIds,function(i,v){\r\n");
      out.write("\t\t\t\t\tv = $.trim(v);\r\n");
      out.write("\t\t\t\t\t$(\"#roomIds-\"+v).attr(\"checked\",true).next().addClass(\"red\");\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\trefreshResInfo();\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
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

  private boolean _jspx_meth_s_005fhidden_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:hidden
    org.apache.struts2.views.jsp.ui.HiddenTag _jspx_th_s_005fhidden_005f0 = (org.apache.struts2.views.jsp.ui.HiddenTag) _005fjspx_005ftagPool_005fs_005fhidden_0026_005fvalue_005fname_005fnobody.get(org.apache.struts2.views.jsp.ui.HiddenTag.class);
    _jspx_th_s_005fhidden_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fhidden_005f0.setParent(null);
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(30,5) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fhidden_005f0.setName("status");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(30,5) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fhidden_005f0.setValue("0");
    int _jspx_eval_s_005fhidden_005f0 = _jspx_th_s_005fhidden_005f0.doStartTag();
    if (_jspx_th_s_005fhidden_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fhidden_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_s_005fhidden_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fhidden_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_s_005fhidden_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fhidden_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:hidden
    org.apache.struts2.views.jsp.ui.HiddenTag _jspx_th_s_005fhidden_005f1 = (org.apache.struts2.views.jsp.ui.HiddenTag) _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.get(org.apache.struts2.views.jsp.ui.HiddenTag.class);
    _jspx_th_s_005fhidden_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fhidden_005f1.setParent(null);
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(31,5) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fhidden_005f1.setName("addrType");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(31,5) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fhidden_005f1.setId("addrType");
    int _jspx_eval_s_005fhidden_005f1 = _jspx_th_s_005fhidden_005f1.doStartTag();
    if (_jspx_th_s_005fhidden_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.reuse(_jspx_th_s_005fhidden_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.reuse(_jspx_th_s_005fhidden_005f1);
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
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(34,5) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f0.setTest("addrType == 'GB'");
    int _jspx_eval_s_005fif_005f0 = _jspx_th_s_005fif_005f0.doStartTag();
    if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t<tr>\r\n");
        out.write("\t\t\t\t\t\t<td width=\"80px\">会议室<font color=\"red\">*</font></td>\r\n");
        out.write("\t\t\t\t\t\t<td>\r\n");
        out.write("\t\t\t\t\t\t\t<div>\r\n");
        out.write("\t\t\t\t\t\t\t\t<span style=\"font-weight: bolder;\">15F:</span>\r\n");
        out.write("\t\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"roomIds\" id=\"roomIds-1\" value=\"1\"/>\r\n");
        out.write("\t\t\t\t\t\t\t\t<label class=\"checkboxLabel\" for=\"roomIds-1\">会议室1</label>\r\n");
        out.write("\t\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"roomIds\" id=\"roomIds-2\" value=\"2\"/>\r\n");
        out.write("\t\t\t\t\t\t\t\t<label class=\"checkboxLabel\" for=\"roomIds-2\">洽谈室1</label>\r\n");
        out.write("\t\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"roomIds\" id=\"roomIds-3\" value=\"3\"/>\r\n");
        out.write("\t\t\t\t\t\t\t\t<label class=\"checkboxLabel\" for=\"roomIds-3\">洽谈室2</label>\r\n");
        out.write("\t\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"roomIds\" id=\"roomIds-4\" value=\"4\"/>\r\n");
        out.write("\t\t\t\t\t\t\t\t<label class=\"checkboxLabel\" for=\"roomIds-4\">洽谈室3</label>\r\n");
        out.write("\t\t\t\t\t\t\t</div>\r\n");
        out.write("\t\t\t\t\t\t\t<div>\r\n");
        out.write("\t\t\t\t\t\t\t\t<span style=\"font-weight: bolder;\">12F:</span>\r\n");
        out.write("\t\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"roomIds\" id=\"roomIds-5\" value=\"5\"/>\r\n");
        out.write("\t\t\t\t\t\t\t\t<label class=\"checkboxLabel\" for=\"roomIds-5\">会议室2</label>\r\n");
        out.write("\t\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"roomIds\" id=\"roomIds-6\" value=\"6\"/>\r\n");
        out.write("\t\t\t\t\t\t\t\t<label class=\"checkboxLabel\" for=\"roomIds-6\">会议室3</label>\r\n");
        out.write("\t\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"roomIds\" id=\"roomIds-7\" value=\"7\"/>\r\n");
        out.write("\t\t\t\t\t\t\t\t<label class=\"checkboxLabel\" for=\"roomIds-7\">洽谈室4</label>\r\n");
        out.write("\t\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"roomIds\" id=\"roomIds-8\" value=\"8\"/>\r\n");
        out.write("\t\t\t\t\t\t\t\t<label class=\"checkboxLabel\" for=\"roomIds-8\">洽谈室5</label>\r\n");
        out.write("\t\t\t\t\t\t\t</div>\r\n");
        out.write("\t\t\t\t\t\t\t<div>\r\n");
        out.write("\t\t\t\t\t\t\t\t<span style=\"font-weight: bolder;\">&nbsp;6F:</span>\r\n");
        out.write("\t\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"roomIds\" id=\"roomIds-9\" value=\"9\"/>\r\n");
        out.write("\t\t\t\t\t\t\t\t<label class=\"checkboxLabel\" for=\"roomIds-9\">会议室4</label>\r\n");
        out.write("\t\t\t\t\t\t\t</div>\r\n");
        out.write("\t\t\t\t\t\t</td>\r\n");
        out.write("\t\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t\t");
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

  private boolean _jspx_meth_s_005fif_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f1 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f1.setParent(null);
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(68,5) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f1.setTest("addrType == 'SHC'");
    int _jspx_eval_s_005fif_005f1 = _jspx_th_s_005fif_005f1.doStartTag();
    if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t<tr>\r\n");
        out.write("\t\t\t\t\t\t<td width=\"80px\">上海城会议室<font color=\"red\">*</font></td>\r\n");
        out.write("\t\t\t\t\t\t<td>\r\n");
        out.write("\t\t\t\t\t\t\t<div>\r\n");
        out.write("\t\t\t\t\t\t\t\t<span style=\"font-weight: bolder;\">会议室</span>\r\n");
        out.write("\t\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"roomIds\" id=\"roomIds-S1\" value=\"S1\"/>\r\n");
        out.write("\t\t\t\t\t\t\t\t<label class=\"checkboxLabel\" for=\"roomIds-S1\">会议室1</label>\r\n");
        out.write("\t\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"roomIds\" id=\"roomIds-S2\" value=\"S2\"/>\r\n");
        out.write("\t\t\t\t\t\t\t\t<label class=\"checkboxLabel\" for=\"roomIds-S2\">会议室2</label>\r\n");
        out.write("\t\t\t\t\t\t\t</div>\r\n");
        out.write("\t\t\t\t\t\t\t<div>\r\n");
        out.write("\t\t\t\t\t\t\t\t<span style=\"font-weight: bolder;\">洽谈室</span>\r\n");
        out.write("\t\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"roomIds\" id=\"roomIds-S3\" value=\"S3\"/>\r\n");
        out.write("\t\t\t\t\t\t\t\t<label class=\"checkboxLabel\" for=\"roomIds-S3\">洽谈室1</label>\r\n");
        out.write("\t\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"roomIds\" id=\"roomIds-S4\" value=\"S4\"/>\r\n");
        out.write("\t\t\t\t\t\t\t\t<label class=\"checkboxLabel\" for=\"roomIds-S4\">洽谈室2</label>\r\n");
        out.write("\t\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"roomIds\" id=\"roomIds-S5\" value=\"S5\"/>\r\n");
        out.write("\t\t\t\t\t\t\t\t<label class=\"checkboxLabel\" for=\"roomIds-S5\">洽谈室3</label>\r\n");
        out.write("\t\t\t\t\t\t\t</div>\r\n");
        out.write("\t\t\t\t\t\t</td>\r\n");
        out.write("\t\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t\t");
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

  private boolean _jspx_meth_s_005ftextfield_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:textfield
    org.apache.struts2.views.jsp.ui.TextFieldTag _jspx_th_s_005ftextfield_005f0 = (org.apache.struts2.views.jsp.ui.TextFieldTag) _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fname_005fid_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.TextFieldTag.class);
    _jspx_th_s_005ftextfield_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005ftextfield_005f0.setParent(null);
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(93,10) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f0.setName("subject");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(93,10) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f0.setId("subject");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(93,10) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f0.setCssStyle("width:350px");
    int _jspx_eval_s_005ftextfield_005f0 = _jspx_th_s_005ftextfield_005f0.doStartTag();
    if (_jspx_th_s_005ftextfield_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fname_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005ftextfield_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fname_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005ftextfield_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005ftextfield_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:textfield
    org.apache.struts2.views.jsp.ui.TextFieldTag _jspx_th_s_005ftextfield_005f1 = (org.apache.struts2.views.jsp.ui.TextFieldTag) _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fonfocus_005fname_005fid_005fcssStyle_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.TextFieldTag.class);
    _jspx_th_s_005ftextfield_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005ftextfield_005f1.setParent(null);
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(98,7) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f1.setCssStyle("color:red");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(98,7) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f1.setCssClass("date");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(98,7) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f1.setId("meetingDateId");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(98,7) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f1.setName("currDay");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(98,7) name = onfocus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f1.setOnfocus("WdatePicker({minDate:'%y-%M-%d'})");
    int _jspx_eval_s_005ftextfield_005f1 = _jspx_th_s_005ftextfield_005f1.doStartTag();
    if (_jspx_th_s_005ftextfield_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fonfocus_005fname_005fid_005fcssStyle_005fcssClass_005fnobody.reuse(_jspx_th_s_005ftextfield_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fonfocus_005fname_005fid_005fcssStyle_005fcssClass_005fnobody.reuse(_jspx_th_s_005ftextfield_005f1);
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
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(99,34) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f0.setName("beginTime");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(99,34) name = format type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f0.setFormat("HH:mm");
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
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(101,33) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f1.setName("endTime");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(101,33) name = format type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f1.setFormat("HH:mm");
    int _jspx_eval_s_005fdate_005f1 = _jspx_th_s_005fdate_005f1.doStartTag();
    if (_jspx_th_s_005fdate_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005fhidden_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:hidden
    org.apache.struts2.views.jsp.ui.HiddenTag _jspx_th_s_005fhidden_005f2 = (org.apache.struts2.views.jsp.ui.HiddenTag) _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.get(org.apache.struts2.views.jsp.ui.HiddenTag.class);
    _jspx_th_s_005fhidden_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fhidden_005f2.setParent(null);
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(107,7) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fhidden_005f2.setName("compere");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(107,7) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fhidden_005f2.setId("compere");
    int _jspx_eval_s_005fhidden_005f2 = _jspx_th_s_005fhidden_005f2.doStartTag();
    if (_jspx_th_s_005fhidden_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.reuse(_jspx_th_s_005fhidden_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.reuse(_jspx_th_s_005fhidden_005f2);
    return false;
  }

  private boolean _jspx_meth_s_005fhidden_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:hidden
    org.apache.struts2.views.jsp.ui.HiddenTag _jspx_th_s_005fhidden_005f3 = (org.apache.struts2.views.jsp.ui.HiddenTag) _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.get(org.apache.struts2.views.jsp.ui.HiddenTag.class);
    _jspx_th_s_005fhidden_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005fhidden_005f3.setParent(null);
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(114,7) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fhidden_005f3.setName("applicant");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(114,7) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fhidden_005f3.setId("applicant");
    int _jspx_eval_s_005fhidden_005f3 = _jspx_th_s_005fhidden_005f3.doStartTag();
    if (_jspx_th_s_005fhidden_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.reuse(_jspx_th_s_005fhidden_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.reuse(_jspx_th_s_005fhidden_005f3);
    return false;
  }

  private boolean _jspx_meth_s_005fhidden_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:hidden
    org.apache.struts2.views.jsp.ui.HiddenTag _jspx_th_s_005fhidden_005f4 = (org.apache.struts2.views.jsp.ui.HiddenTag) _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.get(org.apache.struts2.views.jsp.ui.HiddenTag.class);
    _jspx_th_s_005fhidden_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005fhidden_005f4.setParent(null);
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(121,7) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fhidden_005f4.setName("recorder");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(121,7) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fhidden_005f4.setId("recorder");
    int _jspx_eval_s_005fhidden_005f4 = _jspx_th_s_005fhidden_005f4.doStartTag();
    if (_jspx_th_s_005fhidden_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.reuse(_jspx_th_s_005fhidden_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.reuse(_jspx_th_s_005fhidden_005f4);
    return false;
  }

  private boolean _jspx_meth_s_005fhidden_005f5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:hidden
    org.apache.struts2.views.jsp.ui.HiddenTag _jspx_th_s_005fhidden_005f5 = (org.apache.struts2.views.jsp.ui.HiddenTag) _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.get(org.apache.struts2.views.jsp.ui.HiddenTag.class);
    _jspx_th_s_005fhidden_005f5.setPageContext(_jspx_page_context);
    _jspx_th_s_005fhidden_005f5.setParent(null);
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(128,7) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fhidden_005f5.setId("otherUserCds");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(128,7) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fhidden_005f5.setName("participators");
    int _jspx_eval_s_005fhidden_005f5 = _jspx_th_s_005fhidden_005f5.doStartTag();
    if (_jspx_th_s_005fhidden_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.reuse(_jspx_th_s_005fhidden_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.reuse(_jspx_th_s_005fhidden_005f5);
    return false;
  }

  private boolean _jspx_meth_s_005ftextfield_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:textfield
    org.apache.struts2.views.jsp.ui.TextFieldTag _jspx_th_s_005ftextfield_005f2 = (org.apache.struts2.views.jsp.ui.TextFieldTag) _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fname_005fid_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.TextFieldTag.class);
    _jspx_th_s_005ftextfield_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005ftextfield_005f2.setParent(null);
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(134,23) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f2.setName("partNum");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(134,23) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f2.setCssStyle("width:30px");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(134,23) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f2.setId("otherNum");
    int _jspx_eval_s_005ftextfield_005f2 = _jspx_th_s_005ftextfield_005f2.doStartTag();
    if (_jspx_th_s_005ftextfield_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fname_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005ftextfield_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fname_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005ftextfield_005f2);
    return false;
  }

  private boolean _jspx_meth_s_005ftextfield_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:textfield
    org.apache.struts2.views.jsp.ui.TextFieldTag _jspx_th_s_005ftextfield_005f3 = (org.apache.struts2.views.jsp.ui.TextFieldTag) _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fname_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.TextFieldTag.class);
    _jspx_th_s_005ftextfield_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005ftextfield_005f3.setParent(null);
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(138,10) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f3.setName("relatedDept");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(138,10) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextfield_005f3.setCssStyle("width:350px");
    int _jspx_eval_s_005ftextfield_005f3 = _jspx_th_s_005ftextfield_005f3.doStartTag();
    if (_jspx_th_s_005ftextfield_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fname_005fcssStyle_005fnobody.reuse(_jspx_th_s_005ftextfield_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005ftextfield_0026_005fname_005fcssStyle_005fnobody.reuse(_jspx_th_s_005ftextfield_005f3);
    return false;
  }

  private boolean _jspx_meth_s_005fradio_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:radio
    org.apache.struts2.views.jsp.ui.RadioTag _jspx_th_s_005fradio_005f0 = (org.apache.struts2.views.jsp.ui.RadioTag) _005fjspx_005ftagPool_005fs_005fradio_0026_005fvalue_005fname_005flist_005fnobody.get(org.apache.struts2.views.jsp.ui.RadioTag.class);
    _jspx_th_s_005fradio_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fradio_005f0.setParent(null);
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(145,11) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fradio_005f0.setList("#{'0':'普通会议','1':'重要会议'}");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(145,11) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fradio_005f0.setName("encryptFlg");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(145,11) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fradio_005f0.setValue("0");
    int _jspx_eval_s_005fradio_005f0 = _jspx_th_s_005fradio_005f0.doStartTag();
    if (_jspx_th_s_005fradio_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fradio_0026_005fvalue_005fname_005flist_005fnobody.reuse(_jspx_th_s_005fradio_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fradio_0026_005fvalue_005fname_005flist_005fnobody.reuse(_jspx_th_s_005fradio_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005ftextarea_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:textarea
    org.apache.struts2.views.jsp.ui.TextareaTag _jspx_th_s_005ftextarea_005f0 = (org.apache.struts2.views.jsp.ui.TextareaTag) _005fjspx_005ftagPool_005fs_005ftextarea_0026_005frows_005fname_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.TextareaTag.class);
    _jspx_th_s_005ftextarea_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005ftextarea_005f0.setParent(null);
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(150,10) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextarea_005f0.setName("attention");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(150,10) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextarea_005f0.setCssStyle("width:350px");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(150,10) name = rows type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextarea_005f0.setRows("3");
    int _jspx_eval_s_005ftextarea_005f0 = _jspx_th_s_005ftextarea_005f0.doStartTag();
    if (_jspx_th_s_005ftextarea_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005ftextarea_0026_005frows_005fname_005fcssStyle_005fnobody.reuse(_jspx_th_s_005ftextarea_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005ftextarea_0026_005frows_005fname_005fcssStyle_005fnobody.reuse(_jspx_th_s_005ftextarea_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005ftextarea_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:textarea
    org.apache.struts2.views.jsp.ui.TextareaTag _jspx_th_s_005ftextarea_005f1 = (org.apache.struts2.views.jsp.ui.TextareaTag) _005fjspx_005ftagPool_005fs_005ftextarea_0026_005frows_005fname_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.TextareaTag.class);
    _jspx_th_s_005ftextarea_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005ftextarea_005f1.setParent(null);
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(154,10) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextarea_005f1.setName("remark");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(154,10) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextarea_005f1.setCssStyle("width:350px");
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(154,10) name = rows type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005ftextarea_005f1.setRows("3");
    int _jspx_eval_s_005ftextarea_005f1 = _jspx_th_s_005ftextarea_005f1.doStartTag();
    if (_jspx_th_s_005ftextarea_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005ftextarea_0026_005frows_005fname_005fcssStyle_005fnobody.reuse(_jspx_th_s_005ftextarea_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005ftextarea_0026_005frows_005fname_005fcssStyle_005fnobody.reuse(_jspx_th_s_005ftextarea_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005fproperty_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_005fproperty_005f0 = (org.apache.struts2.views.jsp.PropertyTag) _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_005fproperty_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fproperty_005f0.setParent(null);
    // /WEB-INF/content/oa/oa-meeting-room-res-input.jsp(329,19) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fproperty_005f0.setValue("roomIds");
    int _jspx_eval_s_005fproperty_005f0 = _jspx_th_s_005fproperty_005f0.doStartTag();
    if (_jspx_th_s_005fproperty_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody.reuse(_jspx_th_s_005fproperty_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody.reuse(_jspx_th_s_005fproperty_005f0);
    return false;
  }
}
