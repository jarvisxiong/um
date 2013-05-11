package org.apache.jsp.WEB_002dINF.content.cont;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class cont_002dledger_002dmain_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(4);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/common/nocache.jsp");
    _jspx_dependants.add("/common/meta.jsp");
    _jspx_dependants.add("/WEB-INF/PowerDesk-tags.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fstyle_005fname_005flistValue_005flistKey_005flist_005fcssStyle_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005felse;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fstyle_005fname_005flistValue_005flistKey_005flist_005fcssStyle_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005felse = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.release();
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fstyle_005fname_005flistValue_005flistKey_005flist_005fcssStyle_005fnobody.release();
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
      out.write("\t<title></title>\r\n");
      out.write("\t");

	response.setHeader("Expires","0");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragrma", "no-cache");
	response.setDateHeader("Expires", 0);

      out.write('\r');
      out.write('\n');
      out.write('	');
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"/>\r\n");
      out.write("<meta http-equiv=\"Cache-Control\" content=\"no-store\"/>\r\n");
      out.write("<meta http-equiv=\"Pragma\" content=\"no-cache\"/>\r\n");
      out.write("<meta http-equiv=\"Expires\" content=\"0\"/>\r\n");
      out.write("<META http-equiv=Page-Enter content=blendTrans(Duration=0.5)>\r\n");
      out.write("<META http-equiv=Page-Exit content=blendTrans(Duration=0.5)>\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=8\" />");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/common.css\" />\r\n");
      out.write("\t<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/thickbox.css\"  />\r\n");
      out.write("\t<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/cont/cont.css\" />\r\n");
      out.write("\t<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/TreePanel.css\"/>\r\n");
      out.write("\t<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/prompt/skin/custom_1/ymPrompt.css\" />\r\n");
      out.write("\t\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.form.pack.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/common.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/TreePanel.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.resizable.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jqueryplugin/chilltip.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/loadMask/jquery.loadmask.css\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/loadMask/jquery.loadmask.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/prompt/ymPrompt.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/MaskLayer.js\"></script>\r\n");
      out.write("\t\r\n");
      out.write("\t<style type=\"text/css\">\r\n");
      out.write("\t\t#search_condtion{\r\n");
      out.write("\t\t\tpadding:5px 10px;\r\n");
      out.write("\t\t\tfont-weight: normal;\r\n");
      out.write("\t\t\tbackground-color:#D7DAD9;\r\n");
      out.write("\t\t\twidth:100%;\r\n");
      out.write("\t\t\tpadding:5px;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t#search_condtion th{\r\n");
      out.write("\t\t\tpadding:2px 5px;\r\n");
      out.write("\t\t\ttext-align: right;\r\n");
      out.write("\t\t\tfont-weight: normal;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body> \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div id=\"mailMianContainer\">\r\n");
      out.write("\t<div class=\"title_bar\">\r\n");
      out.write("\t\t<div class=\"fLeft\">\r\n");
      out.write("\t\t\t<div class=\"fLeft banTitle\">合同台账</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div class=\"fRight\">\r\n");
      out.write("\t\t\t<input type=\"button\" class=\"btn_new btn_senior_new\" style=\"width:80px;\" onclick=\"doQuery();\" value=\"高级搜索\" />\r\n");
      out.write("\t\t\t<input type=\"button\"  class=\"btn_new btn_query_new\" onclick=\"doQueryCont();\" value=\"搜索\" style=\"margin-top:8px;\"/>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_security_005fauthorize_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t \t\r\n");
      out.write("\t\t \t\r\n");
      out.write("\t\t \t");
      if (_jspx_meth_security_005fauthorize_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t<input type=\"button\" class=\"btn_new btn_stat_new\" style=\"width:60px;\" onclick=\"doCaptain();\" value=\"统计\" />\r\n");
      out.write("\t\t\t \r\n");
      out.write("\t\t\t");
      if (_jspx_meth_security_005fauthorize_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t \t");
      if (_jspx_meth_security_005fauthorize_005f3(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t \t\r\n");
      out.write("\t\t\t<input type=\"button\" class=\"btn_new btn_full_new\"  onclick=\"window.open(location.href);\" title=\"全屏\" value=\"全屏\"/>\r\n");
      out.write("\t\t\t<input type=\"button\" class=\"btn_new btn_refresh_new\"  onclick=\"window.location.href= location.href;\"  title=\"刷新\" value=\"刷新\"/>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- mailInfo end -->\r\n");
      out.write("\t<div id=\"maiMainBottom\" style=\"width:100%; height:100%;\">\r\n");
      out.write("\t\t<table style=\"width:100%;\">\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t<td id=\"leftPanel\" style=\"width:80px;border-bottom:1px solid #8C8F94; border-right:1px solid #8c8f94;background-color: #white;\" valign=\"top\">\r\n");
      out.write("\t\t\t<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:100%;\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t  \t\t\t\t\t<div id=\"itemDiv\" style=\"padding-top:5px;min-height:450px; min-width:100px; overflow-x: hidden; overflow-y:auto;\">加载中...</div>\r\n");
      out.write("\t  \t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td style=\"width:5px;\">\r\n");
      out.write("\t\t\t\t\t\t<div id=\"noteResizeHandler\" class=\"pd-chill-tip\" title=\"您可以拖动,调整宽度\" style=\"float:right; width:5px;height:100%;background: #eee url('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/common/width_resize.gif') left center no-repeat;cursor: w-resize;\">&nbsp;</div>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t\t<td id=\"rightPanel\" valign=\"top\">\r\n");
      out.write("\t\t\r\n");
      out.write("\t   \t \t<div style=\"height: 100%;background-color: white;line-height: 22px;border-bottom:1px solid #cbcbcb;\" id=\"search_condtion\">\r\n");
      out.write("\t\t\t\t<form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/cont/cont-ledger!list.action\" method=\"post\" id=\"searchForm\">\r\n");
      out.write("\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" id=\"ledgerType\" name=\"ledgerType\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ledgerType}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" name=\"pageNo\" id=\"currentPageNo\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageNo}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" name=\"contTypes\" id=\"contTypes\"/>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t \t\t<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>科目类型</th>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fif_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_s_005felseif_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_s_005felse_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t<th>合同编号</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input style=\"width:120px;\" type=\"text\" name=\"contNo\"/></td>\r\n");
      out.write("\t\t\t\t\t\t<th>合同名称</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input style=\"width:120px;\" type=\"text\" name=\"contName\"/></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>合同状态</th>\r\n");
      out.write("\t\t\t\t\t\t<td><select style=\"width:120px;\" name=\"contStatus\">\r\n");
      out.write("\t\t\t\t\t\t       <option value=\"\"></option>\r\n");
      out.write("\t\t                       <option value=\"0\">未完未结</option>\r\n");
      out.write("\t\t                       <option value=\"1\">已完未结</option>\r\n");
      out.write("\t\t                       <option value=\"2\">已完已结</option>\r\n");
      out.write("\t\t\t                 </select>\r\n");
      out.write("\t\t\t            </td>\r\n");
      out.write("\t\t\t            <th>进度状态</th>\r\n");
      out.write("\t\t\t\t\t\t<td><select style=\"width:120px;\" name=\"contProcess\">\r\n");
      out.write("\t\t                       <option value=\"\"></option>\r\n");
      out.write("\t\t                       <option value=\"0\">未开工</option>\r\n");
      out.write("\t\t                       <option value=\"1\">按期</option>\r\n");
      out.write("\t\t                       <option value=\"2\">滞后</option>\r\n");
      out.write("\t\t                       <option value=\"3\">延期竣工</option>\r\n");
      out.write("\t\t                       <option value=\"4\">已竣工</option>\r\n");
      out.write("\t\t\t                </select>\r\n");
      out.write("\t\t\t            </td>\r\n");
      out.write("\t\t\t            <th>当前状态</th>\r\n");
      out.write("\t\t\t           \t<td><select style=\"width:120px;\" name=\"auditStatus\">\r\n");
      out.write("\t\t                       <option value=\"\"></option>\r\n");
      out.write("\t\t                       <option value=\"0\">未提交</option>\r\n");
      out.write("\t\t                       <option value=\"1\">待审核</option>\r\n");
      out.write("\t\t                       <option value=\"2\">已审核</option>\r\n");
      out.write("\t\t                 \t</select>\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>乙方</th>\r\n");
      out.write("\t\t\t\t\t\t<td><input style=\"width:120px\" type=\"text\" name=\"partB\"/></td>\r\n");
      out.write("\t\t\t\t \t\t<th>实际供方</th>\r\n");
      out.write("\t\t\t\t \t\t<td><input style=\"width:120px\" type=\"text\" name=\"realProvName\"/></td>\r\n");
      out.write("\t\t\t\t \t\t<th>是否战略</th>\r\n");
      out.write("\t\t\t\t \t\t<td>\r\n");
      out.write("\t\t\t\t \t\t\t<select style=\"width:120px;\" name=\"strageFlg\">\r\n");
      out.write("\t\t\t\t \t\t\t\t<option value=\"\"></option>\r\n");
      out.write("\t\t\t\t \t\t\t\t<option value=\"1\">是</option>\r\n");
      out.write("\t\t\t\t \t\t\t\t<option value=\"0\">否</option>\r\n");
      out.write("\t\t\t\t \t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>&nbsp;</td>\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"4\" style=\"padding-top: 5px;line-height: 40px;\" >\r\n");
      out.write("\t\t\t\t\t   \t\t<input type=\"button\" class=\"btn_new btn_query_new\" onclick=\"doQueryCont();\" value=\"搜索\"/>\r\n");
      out.write("\t\t\t\t\t\t \t");
      if (_jspx_meth_security_005fauthorize_005f4(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t \t");
      if (_jspx_meth_security_005fauthorize_005f5(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"onlyCompanyFlg\" id=\"onlyCompanyFlg\" />查看挂在项目公司下的合同\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t  \t</div>\r\n");
      out.write("\t\t  \t\r\n");
      out.write("\t\t  \t\r\n");
      out.write("\t\t  \t<div id=\"mailRight\">\r\n");
      out.write("\t\t\t  ");
      out.write("\r\n");
      out.write("\t  \t\t</div>\r\n");
      out.write("\t  \t\t\r\n");
      out.write("\t  \t\t\r\n");
      out.write("\t\t\t<div id=\"searchUserDiv\" class=\"searchUserDiv\">\r\n");
      out.write("\t\t\t \t<div class=\"inform_div\" >请选择左侧目录搜索</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t    </div>\r\n");
      out.write("\t    </td>\r\n");
      out.write("\t    </tr>\r\n");
      out.write("\t    ");
      out.write("\r\n");
      out.write("\t    </table>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\t    \r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("var arrCheck =\"\";//存储树形结构节点值\r\n");
      out.write("\r\n");
      out.write("$(function(){\r\n");
      out.write("\tgetContTree(\"itemDiv\");//初始化树形结构\r\n");
      out.write("\t//左右拖拉\r\n");
      out.write("    $('#leftPanel').resizable({\r\n");
      out.write("        handler: '#noteResizeHandler',\r\n");
      out.write("        min: { width: 100, height: ($('#leftPanel').height()) },\r\n");
      out.write("        max: { width: 400, height: ($('#leftPanel').height()) },\r\n");
      out.write("        onResize: function(e) {\r\n");
      out.write("        \t//$('#divTreeP').width($('#leftPanel').width()-5);\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("\t\r\n");
      out.write("\t//默认查询\r\n");
      out.write("    jumpPage(1);\r\n");
      out.write("})\r\n");
      out.write("\r\n");
      out.write("function doContType(){\r\n");
      out.write("\tvar url=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/cont/cont-contract-type!input.action\";\r\n");
      out.write("\tif(parent.TabUtils==null)\r\n");
      out.write("\t\twindow.open(url);\r\n");
      out.write("\telse\r\n");
      out.write("\t  parent.TabUtils.newTab(\"contType\",\"合同类型\",url,true);\r\n");
      out.write("}\r\n");
      out.write("function doProjAuthority(){\r\n");
      out.write("\tvar url =\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/cont/cont-project-code!main.action\";\r\n");
      out.write("\tif(parent.TabUtils==null)\r\n");
      out.write("\t\twindow.open(url);\r\n");
      out.write("\telse\r\n");
      out.write("\t  parent.TabUtils.newTab(\"contProjectType\",\"合同权限\",url,true);\r\n");
      out.write("}\r\n");
      out.write("function getContTree(itemDiv){\r\n");
      out.write("\t\r\n");
      out.write("\tvar data = {ledgerType: $(\"#ledgerType\").val()};\r\n");
      out.write("\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/cont/cont-contract-type!getContTypeTree.action\", data, function(result){\r\n");
      out.write("\r\n");
      out.write("\t\t$('#'+itemDiv).empty();\r\n");
      out.write("\t\tvar tree = new TreePanel({\r\n");
      out.write("\t\t\trenderTo:itemDiv,\r\n");
      out.write("\t\t\t'root' : eval('('+result+')'),\r\n");
      out.write("\t\t\t'ctx':'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("'\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\ttree.haveClickText =true;\r\n");
      out.write("\t\ttree.on(function(node){\r\n");
      out.write("\t\t\t$(\"#id\").val(node.attributes.trueId);\r\n");
      out.write("\t\t\t$(\"#itemName\").val(node.attributes.text);\r\n");
      out.write("\t\t\t$(\"#parentCd\").val(node.attributes.parentId);\r\n");
      out.write("\t\t\t$(\"#parentName\").val(node.attributes.parentName);\r\n");
      out.write("\t\t\t$(\"#orderNo\").val(node.attributes.orderNo);\r\n");
      out.write("\t\t\t$(\"#itemType\").val(node.attributes.finType);\r\n");
      out.write("\t\t\t$(\"#itemCd\").val(node.attributes.finItemCd);\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\ttree.addListener(\"check\",function(node){\r\n");
      out.write("\t\t\tarrCheck =tree.getAllCheckedId();\r\n");
      out.write("\t\t\tfinType=tree.getCheckedType();\r\n");
      out.write("\t\t\t//搜索台账\r\n");
      out.write("\t\t\tdoQueryCont();\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\ttree.render();\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("//查看明细\r\n");
      out.write("function getDetail(id){\r\n");
      out.write("\t\r\n");
      out.write("\tvar ledgerType = $(\"#ledgerType\").val();\r\n");
      out.write("\tvar url=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/cont/cont-ledger!input.action?id=\"+id+\"&ledgerType=\"+ledgerType;\r\n");
      out.write("\tvar showName;\r\n");
      out.write("\tif(\"1\"==ledgerType){\r\n");
      out.write("\t\tshowName=\"合同台账查看/新增\";\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tshowName=\"商业台账查看/新增\";\r\n");
      out.write("\t}\r\n");
      out.write("\tif(parent.TabUtils==null)\r\n");
      out.write("\t\twindow.open(url);\r\n");
      out.write("\telse\r\n");
      out.write("\t  parent.TabUtils.newTab(\"cont-ledger-input\",showName,url,true);\r\n");
      out.write("}\r\n");
      out.write("//新增合同\r\n");
      out.write("function doAddContType(){\r\n");
      out.write("\tvar ledgerType=$(\"#ledgerType\").val();\r\n");
      out.write("\tvar url=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/cont/cont-ledger!input.action?ledgerType=\"+ledgerType;\r\n");
      out.write("\tif(parent.TabUtils==null)\r\n");
      out.write("\t\twindow.open(url);\r\n");
      out.write("\telse\r\n");
      out.write("\t  parent.TabUtils.newTab(\"cont-ledger-input\",\"新增\",url,true);\r\n");
      out.write("}\r\n");
      out.write("//删除合同\r\n");
      out.write("function doDeleteCont(id){\r\n");
      out.write("\tif(id!=null){\r\n");
      out.write("\t\tif(confirm(\"确定要删除该条记录吗？\")){\r\n");
      out.write("\t\t\tvar url =\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/cont/cont-ledger!delete.action\";\r\n");
      out.write("\t\t\t$.post(url,{id:id}, function(result) {\r\n");
      out.write("\t\t\t\tdoQueryCont();\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/**\r\n");
      out.write(" * 驾驶舱\r\n");
      out.write(" */\r\n");
      out.write("function doCaptain(){\r\n");
      out.write("\tvar ledgerType=$(\"#ledgerType\").val();\r\n");
      out.write("\tvar url=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/cont/cont-ledger!captain.action?ledgerType=\"+ledgerType;\r\n");
      out.write("\tif(parent.TabUtils==null)\r\n");
      out.write("\t   window.open(url);\r\n");
      out.write("\telse\r\n");
      out.write("\t   parent.TabUtils.newTab(\"cont-ledger-captain\",\"统计\",url,true); \r\n");
      out.write("}\r\n");
      out.write("//暂不使用\r\n");
      out.write("function doUpdateData(){\r\n");
      out.write("\tvar url =\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/cont/cont-ledger!updateData.action\";\r\n");
      out.write("\t$.post(url,function(result) {\r\n");
      out.write("\t\t\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("//工具栏搜索\r\n");
      out.write("function doQuery(){\r\n");
      out.write("\tif($(\"#search_condtion\").css(\"display\")==\"none\"){\r\n");
      out.write("\t\t$(\"#search_condtion\").show();\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\t$(\"#search_condtion\").hide();\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("//翻页\r\n");
      out.write("function jumpPage(pageNo) {\r\n");
      out.write("\r\n");
      out.write("\tif(!pageNo){\r\n");
      out.write("\t\tpageNo = 1;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#currentPageNo\").val(pageNo);\r\n");
      out.write("\t\r\n");
      out.write("\tif(arrCheck == null || arrCheck == \"\"){\r\n");
      out.write("\t\tarrCheck = \"\";\r\n");
      out.write("\t}\r\n");
      out.write("\t$(\"#contTypes\").val(arrCheck);\r\n");
      out.write("\t$(\"#searchForm\").attr(\"action\", \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/cont/cont-ledger!list.action\");\r\n");
      out.write("\t\r\n");
      out.write("\tTB_showMaskLayer(\"正在搜索...\");\r\n");
      out.write("\t$(\"#searchForm\").ajaxSubmit(function(result){\r\n");
      out.write("\t\tTB_removeMaskLayer();\r\n");
      out.write("\t\t$('#searchUserDiv').hide();\r\n");
      out.write("\t\t$(\"#mailRight\").html(result);\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("//点击搜索\r\n");
      out.write("function doQueryCont(){  \r\n");
      out.write("\tjumpPage();\r\n");
      out.write("}\r\n");
      out.write("//导出合同\r\n");
      out.write("function doExportExcel(){\r\n");
      out.write("\tif(\"\" == arrCheck){\r\n");
      out.write("\t\talert(\"请选择合同类别(项目公司)\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\tTB_showMaskLayer(\"正在导出\");\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#searchForm\").attr(\"action\", \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/cont/cont-ledger!exportExcel.action\");\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#searchForm\").submit();\r\n");
      out.write("\tsetTimeout(function(){\r\n");
      out.write("\t\t$('#TB_overlay,#TB_HideSelect,#TB_load').remove();\r\n");
      out.write("\t},2000);\r\n");
      out.write("}\r\n");
      out.write("//财务导出\r\n");
      out.write("function doExportByFin(){\r\n");
      out.write("\tif(\"\"== arrCheck){\r\n");
      out.write("\t\talert(\"请选择合同类别树(项目公司)\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\tTB_showMaskLayer(\"正在导出\");\r\n");
      out.write("\t$(\"#searchForm\").attr(\"action\", \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/cont/cont-ledger!exportExcel.action?excelByFin=true\");\r\n");
      out.write("\t$(\"#searchForm\").submit();\r\n");
      out.write("\tsetTimeout(function(){\r\n");
      out.write("\t\t$('#TB_overlay,#TB_HideSelect,#TB_load').remove();\r\n");
      out.write("\t},2000);\r\n");
      out.write("}\r\n");
      out.write("//财务导入\r\n");
      out.write("function doImportByFin(){\r\n");
      out.write("\tvar html='<form id=\"importContForm\" enctype=\"multipart/form-data\" method=\"post\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/cont/cont-ledger!importxcelByFin.action\"><input id=\"importBisProjectId\" name=\"bisProjectId\" type=\"hidden\" /><table><tbody><tr> <td style=\"padding-left: 8px; padding-top: 5px;\" colspan=\"3\"> <input type=\"file\" style=\"line-height: 22px; height: 22px; margin-bottom: 3px;\" name=\"importCont\" id=\"importCont\"> </td> </tr></tbody></table></form>';\r\n");
      out.write("\tymPrompt.confirmInfo({\r\n");
      out.write("\t\ticoCls : \"\",\r\n");
      out.write("\t\tautoClose:false,\r\n");
      out.write("\t\tmessage : \"<div id='selectTypeDiv'><img align='absMiddle' src='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/loading.gif'></div>\",\r\n");
      out.write("\t\twidth : 300,\r\n");
      out.write("\t\theight :140,\r\n");
      out.write("\t\ttitle : \"导入数据\",\r\n");
      out.write("\t\tafterShow : function() {\r\n");
      out.write("\t\t  $(\"#selectTypeDiv\").html(html);\r\n");
      out.write("\t\t // $('#importBisProjectId').val($('#bisProjectIdFact').val()); \r\n");
      out.write("\t\t},\r\n");
      out.write("\t\thandler : function(btn){\r\n");
      out.write("\t\t\tif(btn=='ok'){\r\n");
      out.write("\t\t\t\tif(isEmpty($(\"#importCont\").val())) {\r\n");
      out.write("\t\t\t\t\t alert(\"请先选择要导入的文件\"); \r\n");
      out.write("\t\t\t\t\t $(\"#importCont\").focus();\r\n");
      out.write("\t\t             return false;\r\n");
      out.write("\t\t         }\r\n");
      out.write("\t\t\t\tymPrompt.close();\r\n");
      out.write("\t\t\t\tTB_showMaskLayer(\"正在导入\");\r\n");
      out.write("\t\t\t\t$(\"#importContForm\").ajaxSubmit(function(result){\r\n");
      out.write("\t\t\t\tTB_removeMaskLayer();\r\n");
      out.write("\t\t\t\tresult ='<div style=\"margin:8px\">'+result+'</div';\r\n");
      out.write("\t\t\t\tymPrompt.alert({title:\"操作结果\",icoCls:\"\",message:result});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\tymPrompt.close();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tbtn:[[\"保存\",'ok'],[\"取消\",'cancel']],\r\n");
      out.write("\t\tcloseBtn:true\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("isEmpty = function (str) {\r\n");
      out.write("\treturn (typeof (str) === \"undefined\" || str === null || (str.length === 0));\r\n");
      out.write("}; \r\n");
      out.write("\r\n");
      out.write("//刷新\r\n");
      out.write("function refreshMain(){\r\n");
      out.write("\twindow.location.href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/cont/cont-ledger!main.action\";\r\n");
      out.write("} \r\n");
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

  private boolean _jspx_meth_security_005fauthorize_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f0 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f0.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f0.setParent(null);
    // /WEB-INF/content/cont/cont-ledger-main.jsp(56,3) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f0.setIfAnyGranted("A_CONTRACT_INPUT,A_CONT_MANA,A_CONTRACT_ADMIN,A_CONT_BIS_INPUT,A_CONT_HOTEL_INPUT");
    int _jspx_eval_security_005fauthorize_005f0 = _jspx_th_security_005fauthorize_005f0.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t");
        if (_jspx_meth_s_005fif_005f0(_jspx_th_security_005fauthorize_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t \t");
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

  private boolean _jspx_meth_s_005fif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_security_005fauthorize_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f0 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_security_005fauthorize_005f0);
    // /WEB-INF/content/cont/cont-ledger-main.jsp(57,3) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f0.setTest("ledgerType!=1");
    int _jspx_eval_s_005fif_005f0 = _jspx_th_s_005fif_005f0.doStartTag();
    if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t<input type=\"button\" class=\"btn_new btn_add_new\" style=\"width:60px;\" onclick=\"doAddContType();\" value=\"新增\"/>\r\n");
        out.write("\t\t \t");
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

  private boolean _jspx_meth_security_005fauthorize_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f1 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f1.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f1.setParent(null);
    // /WEB-INF/content/cont/cont-ledger-main.jsp(63,4) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f1.setIfAnyGranted("A_CONT_MANA");
    int _jspx_eval_security_005fauthorize_005f1 = _jspx_th_security_005fauthorize_005f1.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t");
        if (_jspx_meth_s_005fif_005f1(_jspx_th_security_005fauthorize_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t");
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

  private boolean _jspx_meth_s_005fif_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_security_005fauthorize_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f1 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_security_005fauthorize_005f1);
    // /WEB-INF/content/cont/cont-ledger-main.jsp(64,3) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f1.setTest("ledgerType==1");
    int _jspx_eval_s_005fif_005f1 = _jspx_th_s_005fif_005f1.doStartTag();
    if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t<input type=\"button\" class=\"btn_new btn_add_new\" style=\"width:60px;\" onclick=\"doAddContType();\" value=\"新增\"/>\r\n");
        out.write("\t\t\t");
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

  private boolean _jspx_meth_security_005fauthorize_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f2 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f2.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f2.setParent(null);
    // /WEB-INF/content/cont/cont-ledger-main.jsp(72,3) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f2.setIfAnyGranted("A_CONTRACT_ADMIN");
    int _jspx_eval_security_005fauthorize_005f2 = _jspx_th_security_005fauthorize_005f2.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t \t<input type=\"button\" class=\"btn_new btn_blue_new\" style=\"width:80px;\" onclick=\"doProjAuthority();\" value=\"分配权限\" />\r\n");
        out.write("\t\t\t");
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

  private boolean _jspx_meth_security_005fauthorize_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f3 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f3.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f3.setParent(null);
    // /WEB-INF/content/cont/cont-ledger-main.jsp(76,4) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f3.setIfAnyGranted("A_CONTRACT_ADMIN");
    int _jspx_eval_security_005fauthorize_005f3 = _jspx_th_security_005fauthorize_005f3.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t \t<input type=\"button\" class=\"btn_new btn_set_new\" style=\"width:70px;\" onclick=\"doContType();\" value=\"合同类型\"/>\r\n");
        out.write("\t\t \t");
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

  private boolean _jspx_meth_s_005fif_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f2 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f2.setParent(null);
    // /WEB-INF/content/cont/cont-ledger-main.jsp(115,10) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f2.setTest("ledgerType==1");
    int _jspx_eval_s_005fif_005f2 = _jspx_th_s_005fif_005f2.doStartTag();
    if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_s_005fselect_005f0(_jspx_th_s_005fif_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
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

  private boolean _jspx_meth_s_005fselect_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f0 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fstyle_005fname_005flistValue_005flistKey_005flist_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f2);
    // /WEB-INF/content/cont/cont-ledger-main.jsp(116,8) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setCssStyle("width:120px");
    // /WEB-INF/content/cont/cont-ledger-main.jsp(116,8) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setList("mapTypeByEstate");
    // /WEB-INF/content/cont/cont-ledger-main.jsp(116,8) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setListKey("key");
    // /WEB-INF/content/cont/cont-ledger-main.jsp(116,8) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setListValue("value");
    // /WEB-INF/content/cont/cont-ledger-main.jsp(116,8) null
    _jspx_th_s_005fselect_005f0.setDynamicAttribute(null, "style", new String("width:120px"));
    // /WEB-INF/content/cont/cont-ledger-main.jsp(116,8) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setName("typeByCd");
    int _jspx_eval_s_005fselect_005f0 = _jspx_th_s_005fselect_005f0.doStartTag();
    if (_jspx_th_s_005fselect_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fstyle_005fname_005flistValue_005flistKey_005flist_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fstyle_005fname_005flistValue_005flistKey_005flist_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005felseif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:elseif
    org.apache.struts2.views.jsp.ElseIfTag _jspx_th_s_005felseif_005f0 = (org.apache.struts2.views.jsp.ElseIfTag) _005fjspx_005ftagPool_005fs_005felseif_0026_005ftest.get(org.apache.struts2.views.jsp.ElseIfTag.class);
    _jspx_th_s_005felseif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005felseif_005f0.setParent(null);
    // /WEB-INF/content/cont/cont-ledger-main.jsp(118,7) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005felseif_005f0.setTest("ledgerType==2");
    int _jspx_eval_s_005felseif_005f0 = _jspx_th_s_005felseif_005f0.doStartTag();
    if (_jspx_eval_s_005felseif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felseif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felseif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felseif_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t \t");
        if (_jspx_meth_s_005fselect_005f1(_jspx_th_s_005felseif_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
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

  private boolean _jspx_meth_s_005fselect_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felseif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f1 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fstyle_005fname_005flistValue_005flistKey_005flist_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felseif_005f0);
    // /WEB-INF/content/cont/cont-ledger-main.jsp(119,9) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setCssStyle("width:120px");
    // /WEB-INF/content/cont/cont-ledger-main.jsp(119,9) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setList("mapTypeByBis");
    // /WEB-INF/content/cont/cont-ledger-main.jsp(119,9) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setListKey("key");
    // /WEB-INF/content/cont/cont-ledger-main.jsp(119,9) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setListValue("value");
    // /WEB-INF/content/cont/cont-ledger-main.jsp(119,9) null
    _jspx_th_s_005fselect_005f1.setDynamicAttribute(null, "style", new String("width:120px"));
    // /WEB-INF/content/cont/cont-ledger-main.jsp(119,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setName("typeByCd");
    int _jspx_eval_s_005fselect_005f1 = _jspx_th_s_005fselect_005f1.doStartTag();
    if (_jspx_th_s_005fselect_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fstyle_005fname_005flistValue_005flistKey_005flist_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fstyle_005fname_005flistValue_005flistKey_005flist_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f1);
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
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_s_005fselect_005f2(_jspx_th_s_005felse_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
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

  private boolean _jspx_meth_s_005fselect_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005felse_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f2 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fstyle_005fname_005flistValue_005flistKey_005flist_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felse_005f0);
    // /WEB-INF/content/cont/cont-ledger-main.jsp(122,8) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setCssStyle("width:120px");
    // /WEB-INF/content/cont/cont-ledger-main.jsp(122,8) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setList("mapTypeByHotel");
    // /WEB-INF/content/cont/cont-ledger-main.jsp(122,8) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setListKey("key");
    // /WEB-INF/content/cont/cont-ledger-main.jsp(122,8) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setListValue("value");
    // /WEB-INF/content/cont/cont-ledger-main.jsp(122,8) null
    _jspx_th_s_005fselect_005f2.setDynamicAttribute(null, "style", new String("width:120px"));
    // /WEB-INF/content/cont/cont-ledger-main.jsp(122,8) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setName("typeByCd");
    int _jspx_eval_s_005fselect_005f2 = _jspx_th_s_005fselect_005f2.doStartTag();
    if (_jspx_th_s_005fselect_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fstyle_005fname_005flistValue_005flistKey_005flist_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fstyle_005fname_005flistValue_005flistKey_005flist_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fselect_005f2);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f4 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f4.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f4.setParent(null);
    // /WEB-INF/content/cont/cont-ledger-main.jsp(176,8) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f4.setIfAnyGranted("A_CONTRACT_QUERYEXP,A_CONTRACT_INPUT,A_CONTRACT_AUDIT,A_CONTRACT_ADMIN");
    int _jspx_eval_security_005fauthorize_005f4 = _jspx_th_security_005fauthorize_005f4.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t   \t\t<input type=\"button\" class=\"btn_new btn_export_new\" onclick=\"doExportExcel();\" value=\"导出\" />\r\n");
        out.write("\t\t\t\t\t\t \t");
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

  private boolean _jspx_meth_security_005fauthorize_005f5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f5 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f5.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f5.setParent(null);
    // /WEB-INF/content/cont/cont-ledger-main.jsp(179,8) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f5.setIfAnyGranted("A_CONTRACT_FIN,A_CONTRACT_ADMIN");
    int _jspx_eval_security_005fauthorize_005f5 = _jspx_th_security_005fauthorize_005f5.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t   \t<input type=\"button\" class=\"btn_new btn_export_new\" onclick=\"doExportByFin();\" value=\"财务导出\" style=\"width:70px;\"/>\r\n");
        out.write("\t\t\t\t\t\t   \t<input type=\"button\" class=\"btn_new btn_export_new\" onclick=\"doImportByFin();\" value=\"财务导入\" style=\"width:70px;\"/>\r\n");
        out.write("\t\t\t\t\t\t \t");
        int evalDoAfterBody = _jspx_th_security_005fauthorize_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_security_005fauthorize_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f5);
    return false;
  }
}
