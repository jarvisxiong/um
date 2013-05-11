package org.apache.jsp.WEB_002dINF.content.bis;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import com.hhz.ump.util.DictMapUtil;
import com.hhz.ump.util.JspUtil;

public final class bis_002dfact_002dlist_002dinput_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(3);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/common/nocache.jsp");
    _jspx_dependants.add("/WEB-INF/PowerDesk-tags.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fp_005fcode2name_0026_005fvalue_005fmapCodeName_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fset_0026_005fvar;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fp_005fcode2name_0026_005fvalue_005fmapCodeName_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fset_0026_005fvar = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus.release();
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.release();
    _005fjspx_005ftagPool_005fp_005fcode2name_0026_005fvalue_005fmapCodeName_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fset_0026_005fvar.release();
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
      out.write('\r');
      out.write('\n');

	response.setHeader("Expires","0");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragrma", "no-cache");
	response.setDateHeader("Expires", 0);

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<style>\n");
      out.write("<!--\n");
      out.write("table.detail{margin:0 auto;width:98%;border: 1px solid #DBDBDB;}\r\n");
      out.write("table.must{margin:0 auto;width:98%;}\r\n");
      out.write("table.must td{height:26px;line-height: 26px;}\r\n");
      out.write("table.must th{height:26px;line-height: 26px;}\r\n");
      out.write("table.detail th{text-align:left;padding:4px;border: 1px solid #DBDBDB;background: #dbdbdb;font-weight:lighter;}\n");
      out.write("table.detail td{text-align:left;padding:4px;border: 1px solid #DBDBDB;}\n");
      out.write("-->\n");
      out.write("</style>\r\n");
      out.write("<form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-fact!save.action\" id=\"inputForm\" method=\"post\">\r\n");
      out.write("\t<input type=\"hidden\" id=\"id\" name=\"id\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bisFactId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t<input type=\"hidden\" id=\"currDetail\" name=\"currDetail\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currDetail}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t<input type=\"hidden\" id=\"currDetailName\" name=\"currDetailName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currDetailName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t<input type=\"hidden\" id=\"bisContIdInput\" name=\"bisContIdInput\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${voFact.bisContId }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/> \r\n");
      out.write("\t<table class=\"must\" border=\"0\">\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td style=\"text-align:right;padding-right:4px;\">名称：</td>\r\n");
      out.write("\t\t\t<td >");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currDetailName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("\t\t\t<td style=\"text-align:right;padding-right:4px;\">合同类别：</td>\r\n");
      out.write("\t\t\t<td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${voFact.contBigTypeCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t");
      if (_jspx_meth_s_005fif_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td style=\"text-align:right;padding-right:4px;\">权责年月：</td>\r\n");
      out.write("\t\t\t<td><strong>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${voFact.factYear}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write('年');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${voFact.factMonth}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("月</strong></td>\r\n");
      out.write("\t\t\t<td style=\"text-align:right;padding-right:4px;\">最晚应收日期：</td>\r\n");
      out.write("\t\t\t<td><strong>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${voFact.factDate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</strong></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td style=\"text-align:right;padding-right:4px;\">应收金额：</td>\r\n");
      out.write("\t\t\t<td><strong>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${voFact.mustMoney}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</strong></td>\r\n");
      out.write("\t\t\t<td style=\"text-align:right;padding-right:4px;\">费用类别：</td>\r\n");
      out.write("\t\t\t<td><strong>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${voFact.chargeTypeCdName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</strong></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<table class=\"detail content_table\" style=\"border:0px;\" id=\"detail\">\r\n");
      out.write("\t\t<col width=\"18%\">\r\n");
      out.write("\t\t<col width=\"18%\">\r\n");
      out.write("\t\t<col width=\"34%\">\r\n");
      out.write("\t\t<col width=\"20%\">\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th style=\"border-right:1px solid #fff;\">实收日期</th>\r\n");
      out.write("\t\t\t<th style=\"border-right:1px solid #fff;\">实收金额</th>\r\n");
      out.write("\t\t\t<th >备注</th>\r\n");
      out.write("\t\t\t<th style=\"border-right:1px solid #fff;\">\r\n");
      out.write("\t\t\t\t<div style=\"float:left  ;  line-height: 26px;\">状态</div>\r\n");
      out.write("\t\t\t\t<input type=\"button\" onclick=\"addItem();\" style=\"float:left;  margin-top: 2px; margin-left: 10px;\" class=\"btn_blue\" id=\"newFact\" value='新增'/>\r\n");
      out.write("\t\t\t</th>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t");
      if (_jspx_meth_s_005fiterator_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t<tr id=\"factTr\" style=\"display:none;\">\r\n");
      out.write("\t\t\t<td>\r\n");
      out.write("\t\t\t\t<input id=\"factListDate\" style=\"width: 95%\" type=\"text\" name=\"money[0]\" \r\n");
      out.write("\t\t\t\tonfocus=\"WdatePicker()\" class=\"inputBorder Wdate required select_115_20\" />\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t<td>\r\n");
      out.write("\t\t\t\t<input id=\"newMoney\" style=\"width: 95%\" type=\"text\" name=\"money[0]\" \r\n");
      out.write("\t\t\t\tonblur=\"formatNumber1($(this));\"/>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t<td>\r\n");
      out.write("\t\t\t\t<textarea rows=\"2\" name=\"remark\" id=\"remark\" style=\"font-size:12px;\"></textarea>\r\n");
      out.write("\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"totalMustMoney\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${voFact.mustMoney}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t<td align=\"right\" style=\"text-align: center;\">\r\n");
      out.write("\t\t\t\t新增&nbsp;\r\n");
      out.write("\t\t\t\t<font style=\"color: red; font-weight: bold; cursor: pointer;\" onclick=\"delItem(this);\">×</font>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<div id='tip' style=\"margin-left:20px;display:none;color:red\">建议单条录入</div>\r\n");
      out.write("</form>\r\n");
      if (_jspx_meth_s_005fset_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var layOutCd = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${layOutCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("/*\r\n");
      out.write("switch(layOutCd){\r\n");
      out.write("\tcase '1':$('#detailTitle').html('租户：');break;\r\n");
      out.write("\tcase '2':$('#detailTitle').html('公寓：');break;\r\n");
      out.write("\tcase '3':$('#detailTitle').html('多经：');break;\r\n");
      out.write("}\r\n");
      out.write("*/\r\n");
      out.write("var factListData = {\r\n");
      out.write("\tbisProjectId:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${voFact.bisProjectId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("',\r\n");
      out.write("\tlayOutCd:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${layOutCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("',\r\n");
      out.write("\tchargeTypeCd:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${voFact.chargeTypeCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("',\r\n");
      out.write("\tfactYear:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${voFact.factYear}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("',\r\n");
      out.write("\tfactMonth:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${voFact.factMonth}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("',\r\n");
      out.write("\tcurrDetail:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currDetail}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("',\r\n");
      out.write("\tstoreId:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${voFact.bisStoreId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("',\r\n");
      out.write("\tremark:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${remark}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("',\r\n");
      out.write("\tbisContId:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${voFact.bisContId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("',\r\n");
      out.write("\ttotalMustMoney:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${voFact.mustMoney}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("'\r\n");
      out.write("};\r\n");
      out.write("function newFact(){\r\n");
      out.write("\tymPrompt.close();\r\n");
      out.write("\tappendBisFact(\r\n");
      out.write("\t\tfactListData.bisProjectId,\r\n");
      out.write("\t\tfactListData.layOutCd,\r\n");
      out.write("\t\tfactListData.chargeTypeCd,\r\n");
      out.write("\t\tfactListData.factYear,\r\n");
      out.write("\t\tfactListData.factMonth,\r\n");
      out.write("\t\tfactListData.remark);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("var trApprover=$(\"#factTr\").clone();\r\n");
      out.write("$(\"#factTr\").remove();\r\n");
      out.write("var cloneCount = 0;\r\n");
      out.write("var index=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${conItemCount}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write(";\r\n");
      out.write("\r\n");
      out.write("function addItem(){\r\n");
      out.write("\tif(cloneCount>0){\r\n");
      out.write("\t\t$('#tip').show().fadeOut(2000);\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\tvar trApprover_new=trApprover.clone();\r\n");
      out.write("\tcloneCount++;\r\n");
      out.write("\t$(trApprover_new).show();\r\n");
      out.write("\t$(trApprover_new).find(\":input\").each(function(i){\r\n");
      out.write("\t\tthis.name=this.name.replace('0',index);\r\n");
      out.write("\t\tthis.id=this.id.replace('0',index);\r\n");
      out.write("\t});\r\n");
      out.write("\t$(\"#detail\").append(trApprover_new);\r\n");
      out.write("\tindex++;\r\n");
      out.write("}\r\n");
      out.write("function delItem(dom){\r\n");
      out.write("\t$(dom).parent().parent().remove();\r\n");
      out.write("\tcloneCount--;\r\n");
      out.write("}\r\n");
      out.write("function addItemVal(jObj,date){\r\n");
      out.write("\tvar trApprover_new=trApprover.clone();\r\n");
      out.write("\tcloneCount++;\r\n");
      out.write("\t$(trApprover_new).show();\r\n");
      out.write("\t$(trApprover_new).find(\":input\").each(function(i){\r\n");
      out.write("\t\tif(this.name.indexOf('describe')!=-1) {\r\n");
      out.write("\t\t\t$(this).val(jObj.describe);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\t$(trApprover_new).find(\":input[type=checkbox]\").attr(\"date\", date);\r\n");
      out.write("\t$(\"#tbConItem\").append(trApprover_new);\r\n");
      out.write("\tindex++;\r\n");
      out.write("}\r\n");
      out.write("function openCont(contId){\r\n");
      out.write("\tvar url = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-cont!input.action?id=\"+contId;\r\n");
      out.write("\tif(parent.TabUtils==null){\r\n");
      out.write("\t\twindow.open(url);\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tparent.TabUtils.newTab(\"bisContMenu\",\"合同明细\",url,true);\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("</script>");
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
    // /WEB-INF/content/bis/bis-fact-list-input.jsp(31,2) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f0.setTest("voFact.bisContId !=null");
    int _jspx_eval_s_005fif_005f0 = _jspx_th_s_005fif_005f0.doStartTag();
    if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t<tr>\r\n");
        out.write("\t\t\t<td style=\"text-align:right;padding-right:4px;\">合同编号：</td>\r\n");
        out.write("\t\t\t<td colspan=\"3\"><a href=\"javascript:openCont('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${voFact.bisContId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("');\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${voFact.contNo}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</a></td>\r\n");
        out.write("\t\t</tr>\r\n");
        out.write("\t\t");
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

  private boolean _jspx_meth_s_005fiterator_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_005fiterator_005f0 = (org.apache.struts2.views.jsp.IteratorTag) _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_005fiterator_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fiterator_005f0.setParent(null);
    // /WEB-INF/content/bis/bis-fact-list-input.jsp(64,2) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f0.setValue("factList");
    // /WEB-INF/content/bis/bis-fact-list-input.jsp(64,2) name = status type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f0.setStatus("status");
    int _jspx_eval_s_005fiterator_005f0 = _jspx_th_s_005fiterator_005f0.doStartTag();
    if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fiterator_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fiterator_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t<tr>\r\n");
        out.write("\t\t\t<td style=\"text-align:center;\">");
        if (_jspx_meth_s_005fdate_005f0(_jspx_th_s_005fiterator_005f0, _jspx_page_context))
          return true;
        out.write("</td>\r\n");
        out.write("\t\t\t<td style=\"text-align:right;\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${money }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</td>\r\n");
        out.write("\t\t\t<td  title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${remark }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" ><div class=\"ellipsisDiv_full\" style=\"width:50px;\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${remark }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</div></td>\r\n");
        out.write("\t\t\t<td>\r\n");
        out.write("\t\t\t\t<font  ");
        if (_jspx_meth_s_005fif_005f1(_jspx_th_s_005fiterator_005f0, _jspx_page_context))
          return true;
        out.write(">\r\n");
        out.write("\t\t\t\t\t");
        if (_jspx_meth_p_005fcode2name_005f0(_jspx_th_s_005fiterator_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t</font>\r\n");
        out.write("\t\t\t</td>\r\n");
        out.write("\t\t</tr>\r\n");
        out.write("\t\t");
        int evalDoAfterBody = _jspx_th_s_005fiterator_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fiterator_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus.reuse(_jspx_th_s_005fiterator_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvalue_005fstatus.reuse(_jspx_th_s_005fiterator_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fdate_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:date
    org.apache.struts2.views.jsp.DateTag _jspx_th_s_005fdate_005f0 = (org.apache.struts2.views.jsp.DateTag) _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.get(org.apache.struts2.views.jsp.DateTag.class);
    _jspx_th_s_005fdate_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fdate_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f0);
    // /WEB-INF/content/bis/bis-fact-list-input.jsp(66,34) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f0.setName("factDate");
    // /WEB-INF/content/bis/bis-fact-list-input.jsp(66,34) name = format type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f0.setFormat("yyyy-MM-dd");
    int _jspx_eval_s_005fdate_005f0 = _jspx_th_s_005fdate_005f0.doStartTag();
    if (_jspx_th_s_005fdate_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f1 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f0);
    // /WEB-INF/content/bis/bis-fact-list-input.jsp(70,11) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f1.setTest("statusCd==0");
    int _jspx_eval_s_005fif_005f1 = _jspx_th_s_005fif_005f1.doStartTag();
    if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f1.doInitBody();
      }
      do {
        out.write("style=\"color:red\"\t\t");
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

  private boolean _jspx_meth_p_005fcode2name_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  p:code2name
    com.hhz.core.tags.Code2NameTag _jspx_th_p_005fcode2name_005f0 = (com.hhz.core.tags.Code2NameTag) _005fjspx_005ftagPool_005fp_005fcode2name_0026_005fvalue_005fmapCodeName_005fnobody.get(com.hhz.core.tags.Code2NameTag.class);
    _jspx_th_p_005fcode2name_005f0.setPageContext(_jspx_page_context);
    _jspx_th_p_005fcode2name_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f0);
    // /WEB-INF/content/bis/bis-fact-list-input.jsp(71,5) name = mapCodeName type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_p_005fcode2name_005f0.setMapCodeName("@com.hhz.ump.util.DictMapUtil@getMapBisFactStatus()");
    // /WEB-INF/content/bis/bis-fact-list-input.jsp(71,5) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_p_005fcode2name_005f0.setValue("statusCd");
    int _jspx_eval_p_005fcode2name_005f0 = _jspx_th_p_005fcode2name_005f0.doStartTag();
    if (_jspx_th_p_005fcode2name_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fp_005fcode2name_0026_005fvalue_005fmapCodeName_005fnobody.reuse(_jspx_th_p_005fcode2name_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fp_005fcode2name_0026_005fvalue_005fmapCodeName_005fnobody.reuse(_jspx_th_p_005fcode2name_005f0);
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
    // /WEB-INF/content/bis/bis-fact-list-input.jsp(99,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fset_005f0.setVar("conItemCount");
    int _jspx_eval_s_005fset_005f0 = _jspx_th_s_005fset_005f0.doStartTag();
    if (_jspx_eval_s_005fset_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fset_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fset_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fset_005f0.doInitBody();
      }
      do {
        if (_jspx_meth_s_005fproperty_005f0(_jspx_th_s_005fset_005f0, _jspx_page_context))
          return true;
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

  private boolean _jspx_meth_s_005fproperty_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fset_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_005fproperty_005f0 = (org.apache.struts2.views.jsp.PropertyTag) _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_005fproperty_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fproperty_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fset_005f0);
    // /WEB-INF/content/bis/bis-fact-list-input.jsp(99,26) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fproperty_005f0.setValue("factList.size()");
    int _jspx_eval_s_005fproperty_005f0 = _jspx_th_s_005fproperty_005f0.doStartTag();
    if (_jspx_th_s_005fproperty_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody.reuse(_jspx_th_s_005fproperty_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fproperty_0026_005fvalue_005fnobody.reuse(_jspx_th_s_005fproperty_005f0);
    return false;
  }
}
