package org.apache.jsp.WEB_002dINF.content.plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

public final class bis_002dplan_002dportal_jsp extends org.apache.jasper.runtime.HttpJspBase
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
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
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

	response.setHeader("Expires","0");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragrma", "no-cache");
	response.setDateHeader("Expires", 0);

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"/>\r\n");
      out.write("<meta http-equiv=\"Cache-Control\" content=\"no-store\"/>\r\n");
      out.write("<meta http-equiv=\"Pragma\" content=\"no-cache\"/>\r\n");
      out.write("<meta http-equiv=\"Expires\" content=\"0\"/>\r\n");
      out.write("<META http-equiv=Page-Enter content=blendTrans(Duration=0.5)>\r\n");
      out.write("<META http-equiv=Page-Exit content=blendTrans(Duration=0.5)>\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=8\" />");
      out.write('\r');
      out.write('\n');
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
      out.write("<title>商业计划平台</title>\r\n");
      out.write("\r\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/plan/cost-ctrl.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/plan/planWork.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/common.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/plan/plan-target.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/prompt/skin/custom_1/ymPrompt.css\" />\r\n");
      out.write("\t\t<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/plan/exec-plan.css\"/>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.form.pack.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/datePicker/WdatePicker.js\" ></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/plan/plan-target.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/common/MaskLayer.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jqueryplugin/chilltip.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/prompt/ymPrompt.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/plan/exec-plan.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("    var orgID=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${projectCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\tvar str=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/plan/bis-target!bis.action\";\r\n");
      out.write("\tvar now_year = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${now_year}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\tvar now_month = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${now_month}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\tvar orderBy = \"\";\r\n");
      out.write("\tvar order = \"\";\r\n");
      out.write("\tvar _PATH=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\r\n");
      out.write("\tfunction isLoad(action){\r\n");
      out.write("\t\tvar orgText=$(\"#\"+orgID).text();\r\n");
      out.write("\t\tif(orgText!=null && orgText!=\"\"){\r\n");
      out.write("\t\t\t$(\"#iframe_body\").attr(\"orgName\",orgText);\r\n");
      out.write("\t\t\t$(\"#iframe_body\").attr('src',action);\r\n");
      out.write("\t\t\t$(\"#iframe_body\").attr(\"orgID\",orgID);\r\n");
      out.write("\t\t\t$(\"#\"+orgID).css(\"background-color\",\"#0072bb\");\r\n");
      out.write("\t\t\t$(\"#hint\").hide();\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\torgText = \"153\";\r\n");
      out.write("\t\t\t$(\"#iframe_body\").attr(\"orgName\",orgText);\r\n");
      out.write("\t\t\t$(\"#iframe_body\").attr('src',action);\r\n");
      out.write("\t\t\t$(\"#153\").css(\"background-color\",\"#0072bb\");\r\n");
      out.write("\t\t\t$(\"#hint\").show();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t/* jQuery(function($) {\r\n");
      out.write("\t\t$(\".cost-nav li\").click(function(){\r\n");
      out.write("\t\t\tif($(this).hasClass(\"cost-nav-click\")===true){\r\n");
      out.write("\t\t\t\treturn true;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t$(this).parent().children().removeClass(\"cost-nav-click\").css(\"background-color\",\"#cbcbcb\");\r\n");
      out.write("\t\t\t$(this).addClass(\"cost-nav-click\").css(\"background-color\",\"#0072bb\");\r\n");
      out.write("\t\t\tif($(this).parent().attr(\"id\")===\"meumTable\"){\r\n");
      out.write("\t\t\t\tif($(this).text()===\"商业四级计划\"){\r\n");
      out.write("\t\t\t\t\twindow.location.href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/plan/exec-plan!portalforward.action?planTypeCd=24&if_bis=true\";\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$(\"#divOrgTab\").show();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tstr=$(this).attr(\"href\")+\"?now_year=\"+now_year+\"&now_month=\"+now_month;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif($(this).parent().attr(\"id\")===\"orgTab\"){\r\n");
      out.write("\t\t\t\torgID=$(this).attr(\"value\");\r\n");
      out.write("\t\t\t\t$(\"#iframe_body\").attr('orgID',$(this).attr(\"value\"));\r\n");
      out.write("\t\t\t\t$(\"#iframe_body\").attr('orgName',$(this).text());\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tisLoad(str);\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$(\"#\"+orgID).addClass(\"cost-nav-click\");\r\n");
      out.write("\t\tif(getURLType('type')==\"2\"){\r\n");
      out.write("\t\t\tisLoad(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/plan/bis-plan!center.action\");\r\n");
      out.write("\t\t\t$(\"#centerTask\").css(\"background-color\",\"#0072bb\");\r\n");
      out.write("\t\t\t$(\"#meumTable\").children().removeClass(\"cost-nav-click\");\r\n");
      out.write("\t\t\t$(\"#meumTable\").children()[1].className=\"cost-nav-click\";\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\t$(\"#centerWork\").css(\"background-color\",\"#0072bb\");\r\n");
      out.write("\t\t\tisLoad(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/plan/bis-plan!work.action\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}); */\r\n");
      out.write("\t$(function() {\r\n");
      out.write("\t\t$(\".cost-nav li\").click(function(){\r\n");
      out.write("\t\t\tif($(this).hasClass(\"cost-nav-click\")===true){\r\n");
      out.write("\t\t\t\treturn true;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t$(this).parent().children().removeClass(\"cost-nav-click\").css(\"background-color\",\"#cbcbcb\");\r\n");
      out.write("\t\t\t$(this).addClass(\"cost-nav-click\").css(\"background-color\",\"#0072bb\");\r\n");
      out.write("\t\t\tif($(this).parent().attr(\"id\")===\"meumTable\"){\r\n");
      out.write("\t\t\t\tif($(this).text()===\"商业四级计划\"){\r\n");
      out.write("\t\t\t\t\twindow.location.href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/plan/exec-plan!portalforward.action?planTypeCd=24&if_bis=true\";\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$(\"#divOrgTab\").show();\r\n");
      out.write("\t\t\t\t\tif(\"centerWork\"==$(this).attr(\"id\")){\r\n");
      out.write("\t\t\t\t\t\t//如果中心工作计划\r\n");
      out.write("\t\t\t\t\t\tshowPlanTarget();\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t//中心内部任务\r\n");
      out.write("\t\t\t\t\t\tshowCenterTarget();\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\torgID=$(this).attr(\"id\");\r\n");
      out.write("\t\t\t\tif(\"cost-nav-click\"==$(\"#centerWork\").attr(\"class\")){\r\n");
      out.write("\t\t\t\t\tshowPlanTarget();\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\tshowCenterTarget();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$(\"#\"+orgID).click();\r\n");
      out.write("\t});\r\n");
      out.write("\t//展示商业月计划功能\r\n");
      out.write("    function showPlanTarget(){\r\n");
      out.write("    \tif(orgID){\r\n");
      out.write("    \t\tparam={currentCenterCd:orgID,currentPlanYear:now_year,currentPlanMonth:now_month,orderBy:orderBy,order:order};\r\n");
      out.write("\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/plan/plan-target!bis.action\",param,function(r){\r\n");
      out.write("\t\t\t\t$(\"#work_body\").html(r);\r\n");
      out.write("\t\t\t\tautoHeight();\r\n");
      out.write("\t\t\t\t//初始化initMonthListByBis();\r\n");
      out.write("\t\t\t\t// 排序处理\r\n");
      out.write("\t\t\t\tvar tds = $(\".mainTable >thead >tr >td\");\r\n");
      out.write("\t\t\t\t// 中心计划类型选择\r\n");
      out.write("\t\t\t\t$(\".selectType\").hide();\r\n");
      out.write("\t\t\t\t$(\".editPlanType\").bind(\"click\", typeShow);\r\n");
      out.write("\t\t\t\t$(\".editPlanType\").bind(\"blur\", typeHide);\r\n");
      out.write("\t\t\t\t$(\".selectType\").bind(\"mouseover\", function() {$(\".editPlanType\").unbind(\"blur\");});\r\n");
      out.write("\t\t\t\t$(\".selectType\").bind(\"mouseout\", function() {$(\".editPlanType\").bind(\"blur\", typeHide);});\r\n");
      out.write("\t\t\t\tvar lis = $(\".selectType >ul >li\");\r\n");
      out.write("\t\t\t\t//lis.bind(\"click\", function() {});\r\n");
      out.write("\t\t\t\tlis.bind(\"mouseover\", function() {$(this).css(\"background-color\", \"#CBCBCB\");$(this).css(\"color\", \"#FFFFFF\");});\r\n");
      out.write("\t\t\t\tlis.bind(\"mouseout\", function() {$(this).css(\"background-color\", \"#FFFFFF\");$(this).css(\"color\", \"#000000\");});\r\n");
      out.write("\t\t\t\t//初始化结束\r\n");
      out.write("\t\t\t\tvar subPlanNumber = $(\".mainTable >tbody >tr td span[class=subPlanNumber]\");\r\n");
      out.write("\t\t\t\tfor(var i=0; i<subPlanNumber.size(); i++) {\r\n");
      out.write("\t\t\t\t\tvar sub = subPlanNumber.eq(i).html();\r\n");
      out.write("\t\t\t\t\tsubPlanNumber.eq(i).html(sub.split(\"-\")[sub.split(\"-\").length - 1]);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t//中心工作计划的年月\r\n");
      out.write("\t\t\t\t$(\"#year\").val(now_year);\r\n");
      out.write("\t\t\t\t$(\"#month\").val(now_month);\r\n");
      out.write("\t\t\t\tTB_removeMaskLayer();\r\n");
      out.write("\t\t\t\tvar queryParam = {\r\n");
      out.write("\t\t\t\t\t\tcenterCd :orgID,\r\n");
      out.write("\t\t\t\t\t\tnow_year : now_year,\r\n");
      out.write("\t\t\t\t\t\tnow_month : now_month,\r\n");
      out.write("\t\t\t\t\t\torg_name:\"\",\r\n");
      out.write("\t\t\t\t\t\tif_in_weight : -1,\r\n");
      out.write("\t\t\t\t\t\tif_goto_cost : 0,\r\n");
      out.write("\t\t\t\t\t\tnowResOrgNames:$(\"#\"+orgID).text(),\r\n");
      out.write("\t\t\t\t\t\t\"page.pageNo\" : 1,\r\n");
      out.write("\t\t\t\t\t\t\"page.pageSize\" : 999\r\n");
      out.write("\t\t\t\t};\r\n");
      out.write("\t\t\t\tvar url =\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/plan/exec-plan!levelFour.action\";\r\n");
      out.write("\t\t\t\t$.post(url,queryParam,function(result){\r\n");
      out.write("\t\t\t\t\t$(\"#levelFour_body\").html(result);\r\n");
      out.write("\t\t\t\t\t$(\"#levelFour_body\").show();\r\n");
      out.write("\t\t\t\t\tautoHeight();\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("    \t}\r\n");
      out.write("    \t\r\n");
      out.write("    }\r\n");
      out.write("\tfunction showCenterTarget(){\r\n");
      out.write("\t\tvar param={projectCd:orgID};\r\n");
      out.write("\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/plan/bis-plan!center.action\",param,function(r){\r\n");
      out.write("\t\t\t$(\"#work_body\").html(r);\r\n");
      out.write("\t\t\t$(\"#levelFour_body\").html(\"\");\r\n");
      out.write("\t\t\t$(\"#levelFour_body\").hide();\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction setIframeHeigh(iframeID){\r\n");
      out.write("\t\tvar iframe = document.getElementById(iframeID) ;\r\n");
      out.write("\t\tif(iframe)\r\n");
      out.write("\t\ttry{\r\n");
      out.write("\t\t\tvar iframebody=iframe.contentWindow.document.body;\r\n");
      out.write("\t\t\tvar bHeight = 0;\r\n");
      out.write("\t\t\tif(iframebody) bHeight = iframe.contentWindow.document.body.scrollHeight;\r\n");
      out.write("\t\t\tvar dHeight = iframe.contentWindow.document.documentElement.scrollHeight;\r\n");
      out.write("\t\t\tvar height = Math.min(bHeight, dHeight);\r\n");
      out.write("\t\t\tiframe.height =  Math.max(height,500);\r\n");
      out.write("\t\t}catch (e){}\r\n");
      out.write("\t}\r\n");
      out.write("\t//window.setInterval(\"setIframeHeigh('iframe_body')\", 500);\r\n");
      out.write("\t\r\n");
      out.write("\tvar getURLType=function(type){\r\n");
      out.write("\t\tvar reg=new RegExp(\"(^|&)\"+type+\"=([^&]*)(&|$)\",\"i\");\r\n");
      out.write("\t\tvar r=window.location.search.substr(1).match(reg);\r\n");
      out.write("\t\tif(r!=null) return unescape(r[2]);\r\n");
      out.write("\t\treturn null;\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"title_bar\" style=\"font-weight: bold; padding-left: 5px; font-size: 14px;\" >\r\n");
      out.write("\t商业计划平台\r\n");
      out.write("\t<div style=\"float: right;padding-top:5px;\">\r\n");
      out.write("\t  <input type=\"button\" class=\"btn_green\" onclick=\"window.open('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/plan/bis-plan!portal.action')\" value=\"全屏\" />\r\n");
      out.write("\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div style=\"clear: left;\">\r\n");
      out.write("\t\t<ul id=\"meumTable\" class=\"cost-nav\" style=\"float: left; margin-left: 5px;margin-top:5px;\">\r\n");
      out.write("\t\t\t<li class=\"cost-nav-click\" id=\"centerWork\"  href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/plan/bis-target!bis.action\" style=\"background-color:\">中心工作计划</li>\r\n");
      out.write("\t\t\t<li id=\"centerTask\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/plan/bis-plan!center.action\" style=\"background-color:#cbcbcb;\">中心内部任务</li>\r\n");
      out.write("\t\t\t<li href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/plan/exec-plan!portal.action?planTypeCd=24&if_bis=true\" style=\"background-color:#cbcbcb;\">商业四级计划</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"divOrgTab\" style=\"clear: left;\">\r\n");
      out.write("\t\t<ul id=\"orgTab\" class=\"cost-nav\" style=\"float: left; margin-left: 5px; font-size: 12px;\">\r\n");
      out.write("\t\t<li id=\"153\" value=\"153\" style=\"background-color:#cbcbcb;\">商业集团</li>\r\n");
      out.write("\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"hint\" style=\"padding: 10px; color: red;\">&nbsp;请选择中心</div>\r\n");
      out.write("\r\n");
      out.write(" <div id=\"work_body\"> </div>\r\n");
      out.write(" <div style=\"padding-top:20px;\">&nbsp;&nbsp;</div>\r\n");
      out.write(" <div id=\"levelFour_body\"></div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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

  private boolean _jspx_meth_c_005fforEach_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /WEB-INF/content/plan/bis-plan-portal.jsp(218,3) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${orgMap}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/content/plan/bis-plan-portal.jsp(218,3) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("map");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t");
          if (_jspx_meth_c_005fforEach_005f1(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("\r\n");
          out.write("\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /WEB-INF/content/plan/bis-plan-portal.jsp(219,4) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setVar("org");
    // /WEB-INF/content/plan/bis-plan-portal.jsp(219,4) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${map.value}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
      if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t");
          if (_jspx_meth_c_005fif_005f0(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
            return true;
          out.write("\r\n");
          out.write("\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f1.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f1);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /WEB-INF/content/plan/bis-plan-portal.jsp(220,5) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${org.orgCd!=512}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t<li id=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${org.orgCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${org.orgCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" style=\"background-color:#cbcbcb;\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${org.orgName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</li>\r\n");
        out.write("\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }
}
