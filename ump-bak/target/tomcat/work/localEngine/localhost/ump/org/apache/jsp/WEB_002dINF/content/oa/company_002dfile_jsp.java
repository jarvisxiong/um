package org.apache.jsp.WEB_002dINF.content.oa;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class company_002dfile_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(5);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/common/nocache.jsp");
    _jspx_dependants.add("/common/meta.jsp");
    _jspx_dependants.add("/common/global.jsp");
    _jspx_dependants.add("/WEB-INF/PowerDesk-tags.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
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
      out.write('\r');
      out.write('\n');

	response.setHeader("Expires","0");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragrma", "no-cache");
	response.setDateHeader("Expires", 0);

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"/>\r\n");
      out.write("<meta http-equiv=\"Cache-Control\" content=\"no-store\"/>\r\n");
      out.write("<meta http-equiv=\"Pragma\" content=\"no-cache\"/>\r\n");
      out.write("<meta http-equiv=\"Expires\" content=\"0\"/>\r\n");
      out.write("<META http-equiv=Page-Enter content=blendTrans(Duration=0.5)>\r\n");
      out.write("<META http-equiv=Page-Exit content=blendTrans(Duration=0.5)>\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=8\" />");
      out.write("\r\n");
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
      out.write("\t\t<title>地产控股</title>\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/common.css\" />\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/prompt/skin/custom_1/ymPrompt.css\" />\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/TreePanel.css\" />\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/userSelection.css\" />\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/oa/oa-file.css\" />\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.form.pack.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/prompt/ymPrompt.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/common.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/TreePanel.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/datePicker/WdatePicker.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/jumpTable.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/oa/companyfile.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/oa/userSelection.js\"></script>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/loadMask/jquery.loadmask.css\" />\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/loadMask/jquery.loadmask.min.js\"></script>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.resizable.js\" ></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jqueryplugin/chilltip.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.select.js\"></script>\r\n");
      out.write("\t</head>\r\n");
      out.write("\t<body>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t<div class=\"title_bar\" id=\"title_bar_banner\">\r\n");
      out.write("\t\t\t<!-- 很重要 -->\r\n");
      out.write("\t\t\t<input type=\"hidden\" id=\"editedFolderId\" value=\"\" />\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<table style=\"width:100%;\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<div class=\"fLeft\" style=\"margin-top:5px;\">\r\n");
      out.write("\t\t\t\t\t\t<h2>地产控股</h2>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_security_005fauthorize_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t<div id=\"fLeft folderTitle\" class=\"current_operate_file_file\" style=\"margin-top:5px;\"></div>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<div id=\"loadFirstTip\" class=\"tip_success ellipsisDiv\" style=\"margin-top:5px;\">\r\n");
      out.write("\t\t\t\t\t\t<div style=\"color: red;\" id=\"succInfoMsg\">所有制度文件查看前须安装“安全插件”，如有疑问请联系营运管理中心。</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_security_005fauthorize_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<table style=\"width:100%;\">\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t<td id=\"main_left\" style=\"overflow:hidden; padding:0;\" valign=\"top\">\r\n");
      out.write("\t\t\t<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:100%;\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td id=\"leftPanel\" style=\"width:182px;background-color: #E4E7EC;\" valign=\"top\">\r\n");
      out.write("\t\t\t\t\t\t<div id=\"searchCompanyFileFix\" style=\"border-bottom:1px solid #BFBFBF;\">\r\n");
      out.write("\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td style=\"padding:2px;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input  value=\"搜索目录...\" \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\ttype=\"text\" \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tstyle=\"padding-left:5px;border:0;font-size: 12px;color: #CCCCCC;width:100%;\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tonkeyup=\"searchTreeNode(this)\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tonblur=\"resetSearchApproveInput(this);\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tonclick=\"clearSearchApproveInput(this);\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tid=\"inputSearchVal\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t/>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td style=\"width:56px;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div id=\"inputSearchOperate\" class=\"searchNextNoActive\" onclick=\"searchTreeNode(document.getElementById('inputSearchVal'))\">&nbsp;</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td valign=\"top\">\r\n");
      out.write("\t\t\t\t\t\t<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:100%;\">\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td valign=\"top\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div id=\"divTreeP\" style=\"border:none;background-color:#e4e7ec;overflow-y:auto;overflow-x:hidden;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div id=\"foldersTree\" style=\"padding-top:5px;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"loading\">加载目录...</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div id=\"noteResizeHandler\" class=\"pd-chill-tip\" title=\"您可以拖动,调整宽度\" style=\"width:5px;height:100%;background: #eee url('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/common/width_resize.gif') left center no-repeat;cursor: w-resize;\">&nbsp;</div>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t\t<td id=\"main_right\" valign=\"top\">\r\n");
      out.write("\t\t\t<div id=\"rightContainer\" class=\"right_container\">\r\n");
      out.write("\t\t\t\t<div class=\"inform_div\">\r\n");
      out.write("\t\t\t\t\t请选择目录浏览文件\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\t$(function() {\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\trefreshFolderTree();\r\n");
      out.write("\t\t\t\tresizeMainLeft();\r\n");
      out.write("\t\t\t\tresizeMainRight();\r\n");
      out.write("\t        \t\r\n");
      out.write("\t\t\t\t//左右拖拉\r\n");
      out.write("\t\t\t    $('#main_left').resizable({\r\n");
      out.write("\t\t\t        handler: '#noteResizeHandler',\r\n");
      out.write("\t\t\t        min: { width: 182, height: ($('#main_left').height()) },\r\n");
      out.write("\t\t\t        max: { width: 300, height: ($('#main_left').height()) },\r\n");
      out.write("\t\t\t        onResize: function(e) {\r\n");
      out.write("\t\t\t        \t//$('#divTreeP').width($('#main_left').width()-5);\r\n");
      out.write("\t\t\t        }\r\n");
      out.write("\t\t\t    });\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t//************************* 搜索定位目录 ************************* \r\n");
      out.write("\t\t\tvar curVal = null;\r\n");
      out.write("\t\t\tvar curNode = null;\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tvar searchTreeManager;\r\n");
      out.write("\t\t\tfunction searchTreeNode(dom){\r\n");
      out.write("\t\t\t\tif(searchTreeManager)clearTimeout(searchTreeManager);\r\n");
      out.write("\t\t\t\tsearchTreeManager = setTimeout(function(){\r\n");
      out.write("\t\t\t\t\tprocessSearch(dom);\r\n");
      out.write("\t\t\t\t}, 300);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tfunction processSearch(dom){\r\n");
      out.write("\t\t\t\tif($(dom).val().trim() == ''){\r\n");
      out.write("\t\t\t\t\t$('#inputSearchOperate').removeClass('searchNextActive').addClass('searchNextNoActive');\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$('#inputSearchOperate').removeClass('searchNextNoActive').addClass('searchNextActive');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t$(dom).css({color:\"#5A5A5A\"});\r\n");
      out.write("\t\t \t\tif($(dom).val().trim() == curVal){\r\n");
      out.write("\t\t\t\t\t//NONE\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\tcurVal = $(dom).val().trim();\r\n");
      out.write("\t\t\t\t\tcurNode = null;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t \t\tcurNode = folderTree.searchNode(curVal, curNode);\r\n");
      out.write("\t\t\t\tif(curNode){\r\n");
      out.write("\t\t\t\t\tvar nodes = curNode.getPathNodes();\r\n");
      out.write("\t\t\t\t\tfor(var i= 0; i < nodes.length; i++){\r\n");
      out.write("\t\t\t\t\t\tnodes[i].expand();\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tfolderTree.setFocusNode(curNode);\r\n");
      out.write("\t\t\t\t\tvar nodeDom = curNode['html-element']['text'];\r\n");
      out.write("\t\t\t\t\tvar toh = $('#divTreeP').height();\r\n");
      out.write("\t\t\t\t\tvar top = $('#divTreeP')[0].scrollTop;\r\n");
      out.write("\t\t\t\t\tvar noh = $(nodeDom).offset().top;\r\n");
      out.write("\t\t\t\t\t$('#divTreeP').animate({ scrollTop : top+noh-toh }, \"normal\");\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t//$('#inputSearchOperate').removeClass('searchNextActive').addClass('searchNextNoActive');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tfunction resetSearchApproveInput(dom){\r\n");
      out.write("\t\t\t\tif($(dom).val().trim() == ''){\r\n");
      out.write("\t\t\t\t\t$(dom).val('搜索目录...');\r\n");
      out.write("\t\t\t\t\t$(dom).css({color:\"#cccccc\"});\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$(dom).css({color:\"#5A5A5A\"});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tfunction clearSearchApproveInput(dom){\r\n");
      out.write("\t\t\t\tif( $(dom).val() == '搜索目录...'){\r\n");
      out.write("\t\t\t\t\t$(dom).val('');\r\n");
      out.write("\t\t\t\t\t$(dom).css({color:\"#5A5A5A\"});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
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

  private boolean _jspx_meth_security_005fauthorize_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f0 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f0.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f0.setParent(null);
    // /WEB-INF/content/oa/company-file.jsp(49,5) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f0.setIfAnyGranted("A_COMPANYFILE_ADMIN");
    int _jspx_eval_security_005fauthorize_005f0 = _jspx_th_security_005fauthorize_005f0.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t<div class=\"fLeft\" style=\"margin-top:5px;\">\r\n");
        out.write("\t\t\t\t\t\t<input type=\"checkbox\" onchange=\"refreshFolderTree()\" style=\"margin-right:5px;\" id=\"load_auto_flag\" />自动刷新\r\n");
        out.write("\t\t\t\t\t</div>\r\n");
        out.write("\t\t\t\t\t");
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

  private boolean _jspx_meth_security_005fauthorize_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f1 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f1.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f1.setParent(null);
    // /WEB-INF/content/oa/company-file.jsp(62,5) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f1.setIfAnyGranted("A_COMPANYFILE_ADMIN");
    int _jspx_eval_security_005fauthorize_005f1 = _jspx_th_security_005fauthorize_005f1.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t<div class=\"fRight\" style=\"margin-top:5px;\">\r\n");
        out.write("\t\t\t\t\t\t<input type=\"button\" class=\"btn_blue\" onclick=\"editFolder();\" value=\"编辑目录\" style=\"width:80px;\"/>\r\n");
        out.write("\t\t\t\t\t\t<input type=\"button\" class=\"btn_blue\"  onclick=\"addNewFolder();\" value=\"新增目录\" style=\"width:80px;\"/>\r\n");
        out.write("\t\t\t\t\t</div>\r\n");
        out.write("\t\t\t\t\t");
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
}
