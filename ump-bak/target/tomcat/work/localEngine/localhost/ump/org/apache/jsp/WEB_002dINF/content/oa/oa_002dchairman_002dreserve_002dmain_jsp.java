package org.apache.jsp.WEB_002dINF.content.oa;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

public final class oa_002dchairman_002dreserve_002dmain_jsp extends org.apache.jasper.runtime.HttpJspBase
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
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fhidden_0026_005fid_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fhidden_0026_005fid_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.release();
    _005fjspx_005ftagPool_005fs_005fhidden_0026_005fid_005fnobody.release();
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
      out.write("\t\t<title>预约总裁</title>\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/desk/desk-oa.css\" />\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/userChoose.css\" />\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/prompt/skin/custom_1/ymPrompt.css\" /> \r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/desk/thickbox.css\" />\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/TreePanel.css\" />\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/common.css\" />\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/prompt/ymPrompt.js\"></script>\t\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/common.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/datePicker/WdatePicker.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.form.pack.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/userChoose.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/TreePanel.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/common.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/desk/MaskLayer.js\"></script>\r\n");
      out.write("\t</head>\r\n");
      out.write("\t\r\n");
      out.write("\t<body style=\"line-height: 25px;\" ");
      if (_jspx_meth_s_005fif_005f0(_jspx_page_context))
        return;
      out.write(">\r\n");
      out.write("\t\t<div class=\"title_bar\">\r\n");
      out.write("\t\t\t<div style=\"float:left; margin-left:8px; font-size:18px; font-weight:bolder;\">预约总裁</div>\r\n");
      out.write("\t\t\t<div style=\"float:right; margin-right: 5px; margin-top:6px;\">\r\n");
      out.write("\t\t\t\t<div class=\"btn_blue\" style=\"float:left; margin-right:5px;\" onclick=\"resMeetingRoom();\" class=\"title_bar_chairman_res\">预约总裁</div>\r\n");
      out.write("\t\t\t\t<div class=\"btn_green\" style=\"float:left; margin-right:5px;\" onclick=\"showMyApp();\">我的申请</div>\r\n");
      out.write("\t\t\t\t<div class=\"btn_green\" style=\"float:left; margin-right:5px;\" onclick=\"window.location.href='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-chairman-reserve!main.action'\">刷新</div>\r\n");
      out.write("\t\t\t\t<div class=\"btn_green\" style=\"float:left; margin-right:5px;\" onclick=\"window.open('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-chairman-reserve!main.action')\">全屏</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div style=\"height:2px;background-color: #5a5a5a;height:1px;margin-bottom:2px;margin-bottom: 8px;\"></div>\r\n");
      out.write("\t\t<div style=\"margin:2px\">\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div id=\"myMeetingCon\" style=\"display:none\">\r\n");
      out.write("\t\t\t<fieldset>\r\n");
      out.write("\t\t\t\t<form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-chairman-reserve!myres.action\" method=\"post\" id=\"myMeetingForm\">\r\n");
      out.write("\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t<div style=\"height: 35px;line-height:35px;background-color: #eeeeee\">\r\n");
      out.write("\t\t\t\t\t\t<div style=\"float:left;font-weight: bold;margin-right: 10px;font-size: 14px;color:#316685;padding-left:10px;\">我的申请</div>\r\n");
      out.write("\t\t\t\t\t\t<div style=\"float: left; display:inline;\">\r\n");
      out.write("\t\t\t\t\t\t\t<span style=\"margin-right: 5px;\">申请日期</span>\r\n");
      out.write("\t\t\t\t\t\t\t从<input type=\"text\" name=\"filter_GED_createdDate\" class=\"date\" style=\"width:100px;\" onfocus=\"WdatePicker()\"/>\r\n");
      out.write("\t\t\t\t\t\t\t到<input type=\"text\" name=\"filter_LTD_createdDate\" class=\"date\" style=\"width:100px;\" onfocus=\"WdatePicker()\"/>\r\n");
      out.write("\t\t\t\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"button\" style=\"width:60px;height:24px;line-height: 20px;\" onclick=\"showMyApp();\" value=\"搜索\" />\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"meetingClose\" onclick=\"closeMyMeetingCon();\"></div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div id=\"myMeetingRoomRes\"></div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t</fieldset>\r\n");
      out.write("\t\t\t<div style=\"height:5px;\"></div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t");
      if (_jspx_meth_security_005fauthorize_005f0(_jspx_page_context))
        return;
      out.write("\t\r\n");
      out.write("\t\t<div id=\"meetingRoomResInfoCon\">\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\tfunction changeApplyDate(dp){\r\n");
      out.write("\t\t\t\trefreshRes(dp.cal.getDateStr());\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tfunction changeMeetingDate(dp){\r\n");
      out.write("\t\t\t\trefreshResInfo(dp.cal.getDateStr());\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tfunction refreshRes(applyDate){\r\n");
      out.write("\t\t\t\t$(\"#meetingRoomResCon\").addClass(\"waiting\");\r\n");
      out.write("\t\t\t\tvar param = {};\r\n");
      out.write("\t\t\t\tif(applyDate){\r\n");
      out.write("\t\t\t\t\tparam = {applyDate:applyDate};\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-chairman-reserve!list.action\",param,function(result){\r\n");
      out.write("\t\t\t\t\t$(\"#meetingRoomResCon\").html(result).removeClass(\"waiting\");\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tfunction refreshResInfo(meetingDate){\r\n");
      out.write("\t\t\t\t$(\"#meetingRoomResInfoCon\").addClass(\"waiting\");\r\n");
      out.write("\t\t\t\tvar param = {};\r\n");
      out.write("\t\t\t\tif(meetingDate){\r\n");
      out.write("\t\t\t\t\tparam = {currDay:meetingDate};\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-chairman-reserve!res.action\",param,function(result){\r\n");
      out.write("\t\t\t\t\t$(\"#meetingRoomResInfoCon\").html(result).removeClass(\"waiting\");\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tfunction resMeetingRoom(){\r\n");
      out.write("\t\t\t\tvar url = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-chairman-reserve!input.action';\r\n");
      out.write("\t\t\t\twindow.parent.TabUtils.newTab(\"chairmanRes\",\"预约总裁\",url,true);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\tfunction reResMeetingRoom(id){\r\n");
      out.write("\t\t\t\tvar url = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-chairman-reserve!input.action?id='+id;\r\n");
      out.write("\t\t\t\twindow.parent.TabUtils.newTab(\"chairmanRes\",\"预约总裁\",url,true);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tfunction assign(id){\r\n");
      out.write("\t\t\t\t$.post('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-chairman-reserve!status.action?id='+id,function(result){\r\n");
      out.write("\t\t\t\t\tif(result == '0'){\r\n");
      out.write("\t\t\t\t\t\tymPrompt.win({\r\n");
      out.write("\t\t\t\t\t\t\ttitle:'审核预约信息',\r\n");
      out.write("\t\t\t\t\t\t\tfixPosition:true,\r\n");
      out.write("\t\t\t\t\t\t\twidth:550,\r\n");
      out.write("\t\t\t\t\t\t\theight:370,\r\n");
      out.write("\t\t\t\t\t\t\tcloseBtn:false,\r\n");
      out.write("\t\t\t\t\t\t\tiframe:{id:'assignChairmanResIframe',name:'assignChairmanResIframe',src:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-chairman-reserve!assign.action?id='+id}\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\talert('该预约已经审核通过！');\r\n");
      out.write("\t\t\t\t\t\twindow.location.href='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-chairman-reserve!main.action';\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tfunction delMeeting(id){\r\n");
      out.write("\t\t\t\tif(confirm(\"确定要删除该条预约申请吗？\")){\r\n");
      out.write("\t\t\t\t\t$.post('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-chairman-reserve!delete.action?id='+id,function(){\r\n");
      out.write("\t\t\t\t\t\trefreshRes();\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tfunction assignMeetingRoom(id){\r\n");
      out.write("\t\t\t\t$.post('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-meeting-room-res!status.action?id='+id,function(result){\r\n");
      out.write("\t\t\t\t\tif(result != \"1\"){\r\n");
      out.write("\t\t\t\t\t\tymPrompt.win({\r\n");
      out.write("\t\t\t\t\t\t\ttitle:'会议预约审核',\r\n");
      out.write("\t\t\t\t\t\t\tfixPosition:true,\r\n");
      out.write("\t\t\t\t\t\t\twidth:550,\r\n");
      out.write("\t\t\t\t\t\t\theight:450,\r\n");
      out.write("\t\t\t\t\t\t\tcloseBtn:false,\r\n");
      out.write("\t\t\t\t\t\t\tiframe:{id:'assignMeetingRoomIframe',name:'assignMeetingRoomIframe',src:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-meeting-room-res!assign.action?id='+id}\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tfunction delMeetingRoomRes(id){\r\n");
      out.write("\t\t\t\tif(confirm(\"确定要删除该会议室申请记录吗？\")){\r\n");
      out.write("\t\t\t\t\t$.post('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-meeting-room-res!delete.action?id='+id,function(){\r\n");
      out.write("\t\t\t\t\t\trefreshRes();\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tfunction showMeetingInfo(id){\r\n");
      out.write("\t\t\t\tymPrompt.win({\r\n");
      out.write("\t\t\t\t\ttitle:'预约信息',\r\n");
      out.write("\t\t\t\t\tfixPosition:true,\r\n");
      out.write("\t\t\t\t\twidth:550,\r\n");
      out.write("\t\t\t\t\theight:385,\r\n");
      out.write("\t\t\t\t\tshowMask:false,\r\n");
      out.write("\t\t\t\t\tiframe:{id:'showMeetingRoomIframe',name:'showMeetingRoomIframe',src:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-chairman-reserve!look.action?id='+id}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\tfunction showMeetingRoomInfo(id){\r\n");
      out.write("\t\t\t\tymPrompt.win({\r\n");
      out.write("\t\t\t\t\ttitle:'会议室预定信息',\r\n");
      out.write("\t\t\t\t\tfixPosition:true,\r\n");
      out.write("\t\t\t\t\twidth:550,\r\n");
      out.write("\t\t\t\t\theight:450,\r\n");
      out.write("\t\t\t\t\tshowMask:false,\r\n");
      out.write("\t\t\t\t\tiframe:{id:'showMeetingRoomIframe',name:'showMeetingRoomIframe',src:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-meeting-room-res!look.action?id='+id}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\tfunction showMyApp(){\r\n");
      out.write("\t\t\t\t$(\"#pageNo\").val(1);\r\n");
      out.write("\t\t\t\t$(\"#myMeetingCon\").slideDown();\r\n");
      out.write("\t\t\t\t$(\"#myMeetingForm\").ajaxSubmit(function(result){\r\n");
      out.write("\t\t\t\t\t$(\"#myMeetingRoomRes\").html(result);\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tfunction closeMyMeetingCon(){\r\n");
      out.write("\t\t\t\t$('#myMeetingCon').slideUp();\r\n");
      out.write("\t\t\t\t$(\"#myMeetingForm input\").val(\"\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\tfunction jumpPage(pageNo) {\r\n");
      out.write("\t\t\t\t$(\"#myMeetingRoomRes\").addClass(\"waiting\");\r\n");
      out.write("\t\t\t\t$(\"#pageNo\").val(pageNo);\r\n");
      out.write("\t\t\t\t$(\"#myMeetingForm\").ajaxSubmit(function(result){\r\n");
      out.write("\t\t\t\t\t$(\"#myMeetingRoomRes\").html(result).removeClass(\"waiting\");\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tfunction jumpPageTo() {\r\n");
      out.write("\t\t\t\tvar index = $(\"#pageTo\").val();\r\n");
      out.write("\t\t\t\tindex = parseInt(index);\r\n");
      out.write("\t\t\t\tif (index > 0) {\r\n");
      out.write("\t\t\t\t\tjumpPage(index);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tfunction sendMsg(){\r\n");
      out.write("\t\t\t\tvar userCds = $.trim($('#innerUserCds').val());\r\n");
      out.write("\t\t\t\tvar telNos = $.trim($('#telNoId').val());\r\n");
      out.write("\t\t\t\tvar content = $.trim($('#msgContent').val());\r\n");
      out.write("\t\t\t\tif(userCds == \"\" && telNos == \"\"){\r\n");
      out.write("\t\t\t\t\talert(\"请选择接收人或者填写手机号\");\r\n");
      out.write("\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif(content == \"\"){\r\n");
      out.write("\t\t\t\t\talert(\"请填写短信内容\");\r\n");
      out.write("\t\t\t\t\t$('#msgContent').focus();\r\n");
      out.write("\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tvar param = {\r\n");
      out.write("\t\t\t\t\tuserCds : userCds,\r\n");
      out.write("\t\t\t\t\ttelNos : telNos,\r\n");
      out.write("\t\t\t\t\tmsgContent : content \r\n");
      out.write("\t\t\t\t};\r\n");
      out.write("\t\t\t\tTB_showMaskLayer(\"正在发送短信...\");\r\n");
      out.write("\t\t\t\tvar url = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/oa/oa-chairman-reserve!send.action';\r\n");
      out.write("\t\t\t\t$.post(url,param,function(){\r\n");
      out.write("\t\t\t\t\tTB_removeMaskLayer();\r\n");
      out.write("\t\t\t\t\talert('发送成功！');\r\n");
      out.write("\t\t\t\t\t$('#innerUserCds').val(\"\");\r\n");
      out.write("\t\t\t\t\t$('#innerUserNames').val(\"\");\r\n");
      out.write("\t\t\t\t\t$('#telNoId').val(\"\");\r\n");
      out.write("\t\t\t\t\t$('#msgContent').val(\"\");\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t$(function(){\r\n");
      out.write("\t\t\t\trefreshRes();\r\n");
      out.write("\t\t\t\trefreshResInfo();\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\r\n");
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

  private boolean _jspx_meth_s_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f0 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f0.setParent(null);
    // /WEB-INF/content/oa/oa-chairman-reserve-main.jsp(28,34) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f0.setTest("flag == 'true'");
    int _jspx_eval_s_005fif_005f0 = _jspx_th_s_005fif_005f0.doStartTag();
    if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f0.doInitBody();
      }
      do {
        out.write("onLoad=\"showMeetingInfo('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${objId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write('\'');
        out.write(')');
        out.write('"');
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

  private boolean _jspx_meth_security_005fauthorize_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f0 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f0.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f0.setParent(null);
    // /WEB-INF/content/oa/oa-chairman-reserve-main.jsp(63,2) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f0.setIfAnyGranted("A_CHAIRMAN_RES_ADMIN,A_CEO_RES_ADMIN");
    int _jspx_eval_security_005fauthorize_005f0 = _jspx_th_security_005fauthorize_005f0.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\t\r\n");
        out.write("\t\t\t<div id=\"sendMsgContainer\" style=\"display: none;\">\r\n");
        out.write("\t\t\t<fieldset>\r\n");
        out.write("\t\t\t\t<div>\r\n");
        out.write("\t\t\t\t\t<div style=\"height: 35px;line-height:35px;background-color: #eeeeee\">\r\n");
        out.write("\t\t\t\t\t\t<div style=\"float:left;font-weight: bold;margin-right: 10px;font-size: 14px;color:#316685;padding-left:10px;\">人工短信提醒</div>\r\n");
        out.write("\t\t\t\t\t\t<div class=\"meetingClose\" onclick=\"$('#sendMsgContainer').slideUp();\"></div>\r\n");
        out.write("\t\t\t\t\t</div>\r\n");
        out.write("\t\t\t\t\t<div>\r\n");
        out.write("\t\t\t\t\t\t<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" class=\"editTable\">\r\n");
        out.write("\t\t\t\t\t\t\t<tr>\r\n");
        out.write("\t\t\t\t\t\t\t\t<td width=\"80px\">内部接收人:</td>\r\n");
        out.write("\t\t\t\t\t\t\t\t<td>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<textarea id=\"innerUserNames\" style=\"width:450px;height:40px;\" onclick=\"getMember('inner','','0');\"  readonly=\"readonly\"></textarea>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<span onclick=\"getMember('inner','','0');\" style=\"font-weight: bolder;cursor: pointer;text-decoration: underline\">选择</span>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_s_005fhidden_005f0(_jspx_th_security_005fauthorize_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t</td>\r\n");
        out.write("\t\t\t\t\t\t\t\t<td>无需填写手机号,直接选择人员</td>\r\n");
        out.write("\t\t\t\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t\t\t\t<tr>\r\n");
        out.write("\t\t\t\t\t\t\t\t<td width=\"80px\">手机号：</td>\r\n");
        out.write("\t\t\t\t\t\t\t\t<td>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<textarea id=\"telNoId\"  style=\"width:450px;height:40px;\"></textarea>\r\n");
        out.write("\t\t\t\t\t\t\t\t</td>\r\n");
        out.write("\t\t\t\t\t\t\t\t<td>填写11位手机号，多个手机号请以,(英文逗号)分隔</td>\r\n");
        out.write("\t\t\t\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t\t\t\t<tr>\r\n");
        out.write("\t\t\t\t\t\t\t\t<td width=\"80px\">短信内容：</td>\r\n");
        out.write("\t\t\t\t\t\t\t\t<td>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<textarea id=\"msgContent\"  style=\"width:450px;height:60px;\"></textarea>\r\n");
        out.write("\t\t\t\t\t\t\t\t</td>\r\n");
        out.write("\t\t\t\t\t\t\t\t<td valign=\"bottom\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<input onclick=\"sendMsg();\" style=\"width:60px;height:25px;line-height: 20px;\" value=\"发送\" />\r\n");
        out.write("\t\t\t\t\t\t\t\t</td>\r\n");
        out.write("\t\t\t\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t\t\t</table>\r\n");
        out.write("\t\t\t\t\t</div>\r\n");
        out.write("\t\t\t\t</div>\r\n");
        out.write("\t\t\t</fieldset>\r\n");
        out.write("\t\t\t<div style=\"height:5px;\"></div>\r\n");
        out.write("\t\t\t</div>\r\n");
        out.write("\t\t\t\r\n");
        out.write("\t\t\t<fieldset>\r\n");
        out.write("\t\t\t<div>\r\n");
        out.write("\t\t\t\t<div style=\"height: 35px;line-height:35px;background-color: #eeeeee\">\r\n");
        out.write("\t\t\t\t\t<div style=\"float:left;font-weight: bold;margin-right: 10px;font-size: 14px;color:#316685;padding-left:10px;\">预约申请列表</div>\r\n");
        out.write("\t\t\t\t\t<div style=\"float: left; display:inline;\">\r\n");
        out.write("\t\t\t\t\t\t<span style=\"margin-right: 5px;\">申请日期</span><input type=\"text\" class=\"date\" style=\"width:120px;\" onfocus=\"WdatePicker({onpicked:function(dp){changeApplyDate(dp);}})\"/>\r\n");
        out.write("\t\t\t\t\t</div>\r\n");
        out.write("\t\t\t\t\t<div onclick=\"$('#sendMsgContainer').slideDown();\" style=\"float:right;font-weight: bold;color:#316685;padding-right: 10px;text-decoration: underline;cursor: pointer;\">手动短信提醒</div>\r\n");
        out.write("\t\t\t\t</div>\r\n");
        out.write("\t\t\t\t<div id=\"meetingRoomResCon\"></div>\r\n");
        out.write("\t\t\t</div>\r\n");
        out.write("\t\t\t<div style=\"height:15px;\"></div>\r\n");
        out.write("\t\t\t</fieldset>\r\n");
        out.write("\t\t\t<div style=\"height:15px;\"></div>\r\n");
        out.write("\t\t");
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

  private boolean _jspx_meth_s_005fhidden_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_security_005fauthorize_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:hidden
    org.apache.struts2.views.jsp.ui.HiddenTag _jspx_th_s_005fhidden_005f0 = (org.apache.struts2.views.jsp.ui.HiddenTag) _005fjspx_005ftagPool_005fs_005fhidden_0026_005fid_005fnobody.get(org.apache.struts2.views.jsp.ui.HiddenTag.class);
    _jspx_th_s_005fhidden_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fhidden_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_security_005fauthorize_005f0);
    // /WEB-INF/content/oa/oa-chairman-reserve-main.jsp(78,9) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fhidden_005f0.setId("innerUserCds");
    int _jspx_eval_s_005fhidden_005f0 = _jspx_th_s_005fhidden_005f0.doStartTag();
    if (_jspx_th_s_005fhidden_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fhidden_0026_005fid_005fnobody.reuse(_jspx_th_s_005fhidden_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fhidden_0026_005fid_005fnobody.reuse(_jspx_th_s_005fhidden_005f0);
    return false;
  }
}
