package org.apache.jsp.WEB_002dINF.content.prod;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class prod_002dmate_002dlist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(4);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/common/global.jsp");
    _jspx_dependants.add("/common/meta.jsp");
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
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("\t<title>工料价格指数管理</title>\r\n");
      out.write("\t\r\n");
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html\" />\r\n");
      out.write("\t");
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
      out.write("/resources/css/common/common.css\"/>\r\n");
      out.write("\t<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/TreePanel.css\"/>\r\n");
      out.write("\t<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/thickbox.css\"  />\r\n");
      out.write("\t<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/prompt/skin/custom_1/ymPrompt.css\" /> \t\r\n");
      out.write("\t<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/prod/prod.css\"  />\r\n");
      out.write("\t\r\n");
      out.write("\t<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.form.pack.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/common.js\"></script>\r\n");
      out.write("\t\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/MaskLayer.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/prompt/ymPrompt.js\"></script>\t\t\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.select.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/datePicker/WdatePicker.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/prod/prod.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div id=\"prodcontainer\">\r\n");
      out.write("\t\t<div class=\"title_bar\">\r\n");
      out.write("\t\t\t<div class=\"fLeft banTitle\">供料价格指数管理</div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<div class=\"fRight\">\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\t");
      if (_jspx_meth_security_005fauthorize_005f0(_jspx_page_context))
        return;
      out.write("\t\r\n");
      out.write("\t\t\t\t");
      if (_jspx_meth_security_005fauthorize_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t<input type=\"button\" class=\"btn_new btn_full_new\" onclick=\"window.open(location.href);\" value=\"全屏\" />\r\n");
      out.write("\t\t\t\t<input type=\"button\" class=\"btn_new btn_green_new\" onclick=\"window.location.href=location.href\" value=\"刷新\"/>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div id=\"nav\">\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_security_005fauthorize_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t    \r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div id=\"divBody\" style=\"clear: both;margin-top: 30px;\">\r\n");
      out.write("\t\t\t<div id=\"reportRs\" style=\"margin-left: 10px;margin-right: 10px;\"></div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t  \r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t$(function(){\t\t\r\n");
      out.write("\t\t//价格指数表单控制\r\n");
      out.write("\t\t//menuHover();\t\r\n");
      out.write("\t\t//控制菜单颜色\r\n");
      out.write("\t\t$(\"li\").click(function(){\r\n");
      out.write("\t\t\t\t$(\"#pisearchBtn\").show();\r\n");
      out.write("\t\t\t\t$(this).addClass('prodMenu-click').siblings().removeClass('prodMenu-click');\r\n");
      out.write("\t\t\t\t//工料指数曲线\t\t\t\t\t\r\n");
      out.write("\t\t\t\tif('priceIndexChart'==$(this).attr('id')){\r\n");
      out.write("\t\t\t\t\t$(\"#btn_search\").attr('onclick','priceIndexChartForm(\"priceIndexChart\");');\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t//工料价格指数\r\n");
      out.write("\t\t\t\tif('bntShowPriceIndex'==$(this).attr('id')){\r\n");
      out.write("\t\t\t\t\t$(\"#btn_search\").attr('onclick','priceIndexForm();');\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t//工料价格曲线\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\tif('matePriceChart'==$(this).attr('id')){\r\n");
      out.write("\t\t\t\t\t$(\"#btn_search\").attr('onclick','matePriceChartForm();');\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t//建安单价造价（与工料指数曲线共用同一个方法）\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\tif('constructPrice'==$(this).attr('id')){\r\n");
      out.write("\t\t\t\t\t$(\"#btn_search\").attr('onclick','priceIndexChartForm(\"constructPrice\");');\r\n");
      out.write("\t\t\t\t\t}\t\t\t\t\t\t\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t});\r\n");
      out.write("\t//搜索价格指数\r\n");
      out.write("\tfunction priceIndex(){\t\t\t\t\r\n");
      out.write("\t\tvar url=_ctx+\"/prod/prod-report!priceIndexTwo.action\";\r\n");
      out.write("\t\tvar data={};\r\n");
      out.write("\t\t$.post(url,data,function(result){\t\t\r\n");
      out.write("\t\t\t$(\"#reportRs\").html(result);\r\n");
      out.write("\t\t\tautoHeight();\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t//价格指数表格搜索弹出窗\r\n");
      out.write("\tfunction priceIndexForm(){\t\t\r\n");
      out.write("\t\tymPrompt.confirmInfo({\r\n");
      out.write("\t\t\ticoCls : \"\",\r\n");
      out.write("\t\t\tautoClose:false,\r\n");
      out.write("\t\t\tmessage : \"<div id='searchFormDiv'><img align='absMiddle' src='\"\r\n");
      out.write("\t\t\t\t+ _ctx + \"/images/loading.gif' style='border-radius: 5px 5px 5px 5px;'></div>\",\r\n");
      out.write("\t\t\twidth : 300,\r\n");
      out.write("\t\t\theight : 200,\r\n");
      out.write("\t\t\ttitle : \"工料价格指数搜索\",\r\n");
      out.write("\t\t\tcloseBtn:true,\r\n");
      out.write("\t\t\tafterShow : function() {\r\n");
      out.write("\t\t\t\t\tvar url=_ctx+\"/prod/prod-report!searchForm.action\";\r\n");
      out.write("\t\t\t\t\tvar data={formType:'priceIndex'};\r\n");
      out.write("\t\t\t\t\t$.post(url,data,function(result){\t\t\r\n");
      out.write("\t\t\t\t\t\t$(\"#searchFormDiv\").html(result);\t\r\n");
      out.write("\t\t\t\t\t\tautoHeight();\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\thandler : function(btn){\r\n");
      out.write("\t\t\t\tif(btn=='ok'){\r\n");
      out.write("\t\t\t\t\t$.getScript('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.form.pack.js',function(){\r\n");
      out.write("\t\t\t\t\t$('#pisearchForm').ajaxSubmit(function(result){\r\n");
      out.write("\t\t\t\t\t\t$('#reportRs').html(result);\t\r\n");
      out.write("\t\t\t\t\t\tautoHeight();\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tymPrompt.close();\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tbtn:[[\"搜索\",'ok'],[\"取消\",'cancel']]\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t//价格指数表格搜索弹出窗\r\n");
      out.write("\t//建安单价造价曲线搜索弹出窗共用\r\n");
      out.write("\tfunction priceIndexChartForm(formType){\r\n");
      out.write("\t\tvar title='';\r\n");
      out.write("\t\tif(\"priceIndexChart\"==formType){\r\n");
      out.write("\t\t\ttitle='工料指数曲线搜索';\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\telse{\r\n");
      out.write("\t\t\ttitle='建安单价造价曲线搜索';\r\n");
      out.write("\t\t\t}\t\t\r\n");
      out.write("\t\tymPrompt.confirmInfo({\r\n");
      out.write("\t\t\ticoCls : \"\",\r\n");
      out.write("\t\t\tautoClose:false,\r\n");
      out.write("\t\t\tmessage : \"<div id='searchFormDiv'><img align='absMiddle' src='\"\r\n");
      out.write("\t\t\t\t+ _ctx + \"/images/loading.gif' style='border-radius: 5px 5px 5px 5px;'></div>\",\r\n");
      out.write("\t\t\twidth : 300,\r\n");
      out.write("\t\t\theight : 200,\r\n");
      out.write("\t\t\ttitle : \"工料指数曲线搜索\",\r\n");
      out.write("\t\t\tcloseBtn:true,\r\n");
      out.write("\t\t\tafterShow : function() {\r\n");
      out.write("\t\t\t\t\tvar url=_ctx+\"/prod/prod-report!searchForm.action\";\r\n");
      out.write("\t\t\t\t\tvar data={formType:'priceIndex'};\r\n");
      out.write("\t\t\t\t\t$.post(url,data,function(result){\t\t\r\n");
      out.write("\t\t\t\t\t\t$(\"#searchFormDiv\").html(result);\r\n");
      out.write("\t\t\t\t\t\tautoHeight();\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\thandler : function(btn){\r\n");
      out.write("\t\t\t\tif(btn=='ok'){\r\n");
      out.write("\t\t\t\t\t$.getScript('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.form.pack.js',function(){ \r\n");
      out.write("\t\t\t\t\t\tvar dt=$(\"#inp_ym\").val();\r\n");
      out.write("\t\t\t\t\t\tvar bussinessCd=$(\"select#sele_bussinessCd :selected\").val();\r\n");
      out.write("\t\t\t\t\t\tvar bussinessCdTxt='19-32层住宅';\r\n");
      out.write("\t\t\t\t\t\tif(bussinessCd)\r\n");
      out.write("\t\t\t\t\t\t\tbussinessCdTxt=$(\"select#sele_bussinessCd :selected\").html();\r\n");
      out.write("\t\t\t\t\t\t//工料指数曲线搜索\r\n");
      out.write("\t\t\t\t\t\tif(\"priceIndexChart\"==formType){\r\n");
      out.write("\t\t\t\t\t\t\tviewChart('0',dt,bussinessCd,'',bussinessCdTxt);\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t//建安单价造价曲线搜索\r\n");
      out.write("\t\t\t\t\t\telse{\r\n");
      out.write("\t\t\t\t\t\t\tviewChart('2',dt,bussinessCd,'',bussinessCdTxt);\r\n");
      out.write("\t\t\t\t\t\t\t}\t\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t} );\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tymPrompt.close();\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tbtn:[[\"搜索\",'ok'],[\"取消\",'cancel']]\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t//工料价格曲线搜索弹出窗\r\n");
      out.write("\tfunction matePriceChartForm(){\t\t\r\n");
      out.write("\t\tymPrompt.confirmInfo({\r\n");
      out.write("\t\t\ticoCls : \"\",\r\n");
      out.write("\t\t\tautoClose:false,\r\n");
      out.write("\t\t\tmessage : \"<div id='searchFormDiv'><img align='absMiddle' src='\"\r\n");
      out.write("\t\t\t\t+ _ctx + \"/images/loading.gif' style='border-radius: 5px 5px 5px 5px;'></div>\",\r\n");
      out.write("\t\t\twidth : 300,\r\n");
      out.write("\t\t\theight : 200,\r\n");
      out.write("\t\t\ttitle : \"工料价格曲线搜索\",\r\n");
      out.write("\t\t\tcloseBtn:true,\r\n");
      out.write("\t\t\tafterShow : function() {\r\n");
      out.write("\t\t\t\t\tvar url=_ctx+\"/prod/prod-report!searchForm.action\";\r\n");
      out.write("\t\t\t\t\tvar data={formType:'matePriceChart'};\r\n");
      out.write("\t\t\t\t\t$.post(url,data,function(result){\t\t\r\n");
      out.write("\t\t\t\t\t\t$(\"#searchFormDiv\").html(result);\t\r\n");
      out.write("\t\t\t\t\t\tautoHeight();\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\thandler : function(btn){\r\n");
      out.write("\t\t\t\tif(btn=='ok'){\r\n");
      out.write("\t\t\t\t\t$.getScript('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.form.pack.js',function(){ \r\n");
      out.write("\t\t\t\t\t\tvar dt=$(\"#inp_ym\").val();\r\n");
      out.write("\t\t\t\t\t\tvar bussinessCd=$(\"select#sele_bussinessCd :selected\").val();\r\n");
      out.write("\t\t\t\t\t\tvar mateZoneCd=$(\"select#sele_mateZoneCd :selected\").val();\r\n");
      out.write("\t\t\t\t\t\t//工料范围\r\n");
      out.write("\t\t\t\t\t\tvar mateZoneCdTxt='(人工)';\r\n");
      out.write("\t\t\t\t\t\tif(mateZoneCd)\r\n");
      out.write("\t\t\t\t\t\t\tmateZoneCdTxt='('+$(\"select#sele_mateZoneCd :selected\").html()+')';\r\n");
      out.write("\t\t\t\t\t\t//业态\r\n");
      out.write("\t\t\t\t\t\tvar bussinessCdTxt='19-32层住宅';\r\n");
      out.write("\t\t\t\t\t\tif(bussinessCd)\r\n");
      out.write("\t\t\t\t\t\t\tbussinessCdTxt=$(\"select#sele_bussinessCd :selected\").html();\r\n");
      out.write("\t\t\t\t\t\tbussinessCdTxt=bussinessCdTxt+mateZoneCdTxt\r\n");
      out.write("\t\t\t\t\t\tviewChart('1',dt,bussinessCd,mateZoneCd,bussinessCdTxt);\r\n");
      out.write("\t\t\t\t\t} );\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tymPrompt.close();\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tbtn:[[\"搜索\",'ok'],[\"取消\",'cancel']]\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t//价格指数曲线图\r\n");
      out.write("\tfunction priceIndexChart2(mateZoneCdFlg){\r\n");
      out.write("\t\tvar url=_ctx+\"/prod/prod-report!priceIndexChart.action\";\r\n");
      out.write("\t\t//var data={year:2011,month:02,areaCd:1};\r\n");
      out.write("\t\tvar data;\r\n");
      out.write("\t\t$.post(url,data,function(result){\t\t\r\n");
      out.write("\t\t\t\t$('#reportRs').html(result);\r\n");
      out.write("\t\t\t\tpriceIndexChart2Data(mateZoneCdFlg);\r\n");
      out.write("\t\t\t\tautoHeight();\t\t\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t//建安单价造价曲线图\r\n");
      out.write("\tfunction constructPrice(mateZoneCdFlg){\r\n");
      out.write("\t\tvar url=_ctx+\"/prod/prod-report!priceIndexChart.action\";\r\n");
      out.write("\t\tvar data;\r\n");
      out.write("\t\t$.post(url,data,function(result){\t\t\r\n");
      out.write("\t\t\t$('#reportRs').html(result);\r\n");
      out.write("\t\t\tpriceIndexChart2Data(mateZoneCdFlg);\t\r\n");
      out.write("\t\t\tautoHeight();\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t//获取工料价格指数数据\r\n");
      out.write("\tfunction priceIndexChart2Data(mateZoneCdFlg){\r\n");
      out.write("\t\t//默认业态为\"1\"-19-32层住宅,工料为\"1\"-人工\r\n");
      out.write("\t\tif('0'==mateZoneCdFlg)\r\n");
      out.write("\t\t\tviewChart(mateZoneCdFlg,\"\",\"1\",\"1\",'19-32层住宅');\r\n");
      out.write("\t\telse if('2'==mateZoneCdFlg)\r\n");
      out.write("\t\t\tviewChart(mateZoneCdFlg,\"\",\"1\",\"1\",'19-32层住宅');\r\n");
      out.write("\t\telse\r\n");
      out.write("\t\t\tviewChart(mateZoneCdFlg,\"\",\"1\",\"1\",'19-32层住宅(人工)');\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//暂时不启用\r\n");
      out.write("\tfunction priceIndexChart(){\r\n");
      out.write("\t\tymPrompt.confirmInfo({\r\n");
      out.write("\t\t\ticoCls : \"\",\r\n");
      out.write("\t\t\tautoClose:false,\r\n");
      out.write("\t\t\tmessage : \"<div id='searchFormDiv'><img align='absMiddle' src='\"\r\n");
      out.write("\t\t\t\t+ _ctx + \"/images/loading.gif'></div>\",\r\n");
      out.write("\t\t\twidth : 300,\r\n");
      out.write("\t\t\theight : 220,\r\n");
      out.write("\t\t\ttitle : \"价格指数曲线图搜索\",\r\n");
      out.write("\t\t\tcloseBtn:true,\r\n");
      out.write("\t\t\tafterShow : function() {\r\n");
      out.write("\t\t\t\t\tvar url=_ctx+\"/prod/prod-report!searchForm.action\";\r\n");
      out.write("\t\t\t\t\tvar data={formType:'priceIndexChart'};\r\n");
      out.write("\t\t\t\t\t$.post(url,data,function(result){\t\t\r\n");
      out.write("\t\t\t\t\t\t$(\"#searchFormDiv\").html(result);\t\r\n");
      out.write("\t\t\t\t\t\tautoHeight();\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\thandler : function(btn){\r\n");
      out.write("\t\t\t\t//叉叉关闭事件\r\n");
      out.write("\t\t\t\t$(\"div.ymPrompt_close\").click(function(){\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\tymPrompt.close();\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\tif(btn=='ok'){\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t//验证搜索表单\r\n");
      out.write("\t\t\t\t\tif(validateSearchForm2()){\r\n");
      out.write("\t\t\t\t\t\t//验证是否存在激活版本号\r\n");
      out.write("\t\t\t\t\t\tfindNewVersion();\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\tviewChart();\r\n");
      out.write("\t\t\t\t\t\tymPrompt.close();\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif(btn=='cancel'){\r\n");
      out.write("\t\t\t\t\tymPrompt.close();\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tbtn:[[\"搜索\",'ok'],[\"取消\",'cancel']]\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//收缩表单\r\n");
      out.write("\tfunction toggleForm(){\r\n");
      out.write("\t\t$(\"#searchForm\").slideToggle();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//点击表单显示控制\r\n");
      out.write("\tfunction menuHover(){\r\n");
      out.write("\t\t$(\"#priceIndexChart\").toggle(function(){\r\n");
      out.write("\t\t\t$(\"#searchForm\").slideDown();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t,function(){\r\n");
      out.write("\t\t\t$(\"#searchForm\").slideToggle();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t);\t\t\t\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t//验证空\r\n");
      out.write("\tfunction  isEmpty(str) {\r\n");
      out.write("\t\treturn (typeof (str) === \"undefined\" || str === null || (str.length === 0));\r\n");
      out.write("\t}\r\n");
      out.write("\t//寻找最新版本\r\n");
      out.write("\tfunction findNewVersion(){\r\n");
      out.write("\t\t//验证是否存在激活的当前比对版本\r\n");
      out.write("\t\tvar url=_ctx+\"/prod/prod-basic-version!findNewVersion.action\";\r\n");
      out.write("\t\t$.post(url,{},function(result){\r\n");
      out.write("\t\t\tvar rs=result.split(\",\");\t\t\r\n");
      out.write("\t\t\tif('success'==rs[0]){\r\n");
      out.write("\t\t\t\treturn true;\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\telse{\r\n");
      out.write("\t\t\t\talert('无激活版本号,请激活一个版本号,才能搜索相应数据和图表!');\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t}\t\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t//权重表单验证\r\n");
      out.write("\tfunction validateSearchForm2(){\r\n");
      out.write("\t\t//判空\t\t\r\n");
      out.write("\t\tif(($(\"#input_areaCd\").val().trim().length<1)&&($(\"#input_bussiness\").val().trim().length<1)){\r\n");
      out.write("\t\t\talert('地区与产品业态必选其一！');\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t//验证时间\r\n");
      out.write("\t\tvar fromDate = $(\"#input_dateFrom_3\").val();\r\n");
      out.write("\t\tvar toDate = $(\"#input_dateTo_3\").val();\r\n");
      out.write("\t\t//判断时间是否为空\r\n");
      out.write("\t\tif(!isEmpty(fromDate) && !isEmpty(toDate)) {\r\n");
      out.write("\t\t\tvar fArray = fromDate.split(\"-\");\r\n");
      out.write("\t\t\tvar tArray = toDate.split(\"-\");\r\n");
      out.write("\t\t\tvar fDate = new Date(fArray[0],fArray[1]-1,'01');\r\n");
      out.write("\t\t\tvar tDate = new Date(tArray[0],tArray[1]-1,'01');\r\n");
      out.write("\t\t\tif(tDate.getTime()<fDate.getTime()) {\r\n");
      out.write("\t\t\t\talert(\"结束时间不能小于开始时间\");\r\n");
      out.write("\t\t\t\t$(\"#input_dateTo_3\").val(\"\");\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//屏操作\r\n");
      out.write("\tfunction openFullWindow(){\r\n");
      out.write("\t\twindow.open(_ctx+'/prod/prod-mate.action');\r\n");
      out.write("\t}\t\r\n");
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

  private boolean _jspx_meth_security_005fauthorize_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f0 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f0.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f0.setParent(null);
    // /WEB-INF/content/prod/prod-mate-list.jsp(35,4) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f0.setIfAnyGranted("A_PROD_ADMIN,A_PROD_BASIC");
    int _jspx_eval_security_005fauthorize_005f0 = _jspx_th_security_005fauthorize_005f0.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t");
        out.write("\r\n");
        out.write("\t\t\t\t\t");
        out.write("\t\t\t\t\t\r\n");
        out.write("\t\t\t\t\t<input type=\"button\" style=\"width:90px;\" value=\"工料基准版本\" onclick=\"versionDetail();\" class=\"btn_new btn_blue_new\" />\r\n");
        out.write("\t\t\t\t");
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
    // /WEB-INF/content/prod/prod-mate-list.jsp(40,4) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f1.setIfAnyGranted("A_PROD_ADMIN,A_PROD_PRICE");
    int _jspx_eval_security_005fauthorize_005f1 = _jspx_th_security_005fauthorize_005f1.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\t\t\r\n");
        out.write("\t\t\t\t\t<input type=\"button\" style=\"width:90px;\" value=\"工料价格维护\" onclick=\"matePrice();\" class=\"btn_new btn_blue_new\" />\r\n");
        out.write("\t\t\t\t");
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

  private boolean _jspx_meth_security_005fauthorize_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f2 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f2.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f2.setParent(null);
    // /WEB-INF/content/prod/prod-mate-list.jsp(49,3) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f2.setIfAnyGranted("A_PROD_ADMIN,A_PROD_VIEW");
    int _jspx_eval_security_005fauthorize_005f2 = _jspx_th_security_005fauthorize_005f2.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t<div id=\"menuDiv\" class=\"menuDiv\">\r\n");
        out.write("\t\t\t\t\t<ul class=\"prodMenu\">\r\n");
        out.write("\t\t\t\t\t\t<li  onclick=\"priceIndex();\" id=\"bntShowPriceIndex\" title=\"点击以显示工料价格指数\">工料价格指数</li>\t\t\t\t\r\n");
        out.write("\t\t\t\t\t\t<li  onclick=\"priceIndexChart2('0');\" id=\"priceIndexChart\" style=\"width: 100px;\" title=\"点击以显示工料指数曲线\">工料指数曲线</li>\r\n");
        out.write("\t\t\t\t\t\t<li  onclick=\"constructPrice('2');\" id=\"constructPrice\" style=\"width: 100px;\" title=\"点击以显示建安单价造价\">建安单价造价</li>\r\n");
        out.write("\t\t\t\t\t\t<li  onclick=\"priceIndexChart2('1');\" id=\"matePriceChart\" style=\"width: 100px;\" title=\"点击以显示工料价格曲线\">工料价格曲线</li>\r\n");
        out.write("\t\t\t\t\t</ul>\r\n");
        out.write("\t\t\t\t\t<div style=\"float: right;display: none;margin-right: 10px;\" id=\"pisearchBtn\">\r\n");
        out.write("\t\t\t\t\t\t<input type=\"button\" value=\"搜索\" id=\"btn_search\" onclick=\"priceIndexForm();\" class=\"btn_new btn_query_new\"/>\r\n");
        out.write("\t\t\t\t\t</div>\t\t\r\n");
        out.write("\t\t\t\t</div>\r\n");
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
}
