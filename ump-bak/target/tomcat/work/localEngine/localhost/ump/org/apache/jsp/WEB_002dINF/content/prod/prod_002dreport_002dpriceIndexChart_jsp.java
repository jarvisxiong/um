package org.apache.jsp.WEB_002dINF.content.prod;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class prod_002dreport_002dpriceIndexChart_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(3);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/common/meta.jsp");
    _jspx_dependants.add("/WEB-INF/PowerDesk-tags.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
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
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"/>\r\n");
      out.write("<meta http-equiv=\"Cache-Control\" content=\"no-store\"/>\r\n");
      out.write("<meta http-equiv=\"Pragma\" content=\"no-cache\"/>\r\n");
      out.write("<meta http-equiv=\"Expires\" content=\"0\"/>\r\n");
      out.write("<META http-equiv=Page-Enter content=blendTrans(Duration=0.5)>\r\n");
      out.write("<META http-equiv=Page-Exit content=blendTrans(Duration=0.5)>\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=8\" />");
      out.write("\r\n");
      out.write("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/prod/js/highcharts.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/prod/js/modules/exporting.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\t<div style=\"display: none;margin-left: 10px;\" id=\"error\">没有搜索到符合条件的的数据！</div>\r\n");
      out.write("\t\r\n");
      out.write(" \t<div id=\"container\" style=\"width: 800px; height: 350px; margin-top:30px;margin-bottom: 30px;clear: left;\"></div>\r\n");
      out.write("\t<center>\r\n");
      out.write("\t\t<div style=\"clear: left;text-align: center;\">\r\n");
      out.write("\t\t\t<font style=\"color: red;\">友情提示：可点击相应的\"地区\"，以隐藏或显示图表中对应的曲线！<div id=\"remark\"> </div></font>\t\t\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</center>\r\n");
      out.write("\t\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\tvar chart;\r\n");
      out.write("\t//构建图表\r\n");
      out.write("\tfunction viewChart(mateZoneCdFlg,dateTo,bussinessCd,mateZoneCd,title){\r\n");
      out.write("\t\tvar yearMonth;\r\n");
      out.write("\t\t//var dateFrom=$(\"#input_dateFrom_3\").val();\r\n");
      out.write("\t\t//var dateTo=$(\"#input_dateTo_3\").val();\r\n");
      out.write("\t\t//var areaCd=$(\"select#input_areaCd :selected\").val();\r\n");
      out.write("\t\t//var bussinessCd=$(\"select#input_bussiness :selected\").val();\r\n");
      out.write("\t\t//var mateZoneCd=\"1\";//$(\"select#input_mateZoneCd :selected\").val();\r\n");
      out.write("\t\tvar chartName=\"\";\t\t\r\n");
      out.write("\t\t//数据参数\r\n");
      out.write("\t\tvar data={dateTo:dateTo,bussinessCd:bussinessCd};\r\n");
      out.write("\t\tif(mateZoneCdFlg=='1'){\r\n");
      out.write("\t\t\tdata['mateZoneCd']=mateZoneCd;\r\n");
      out.write("\t\t\tchartName=\"工料价格曲线\";\r\n");
      out.write("\t\t\t$(\"#remark\").html(\"人工基准价：60元/工日;砼基准价：410元/m3 ;钢筋基准价：4.800元/kg ;砌块基准价：240元/m3\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\telse if(mateZoneCdFlg=='2'){\r\n");
      out.write("\t\t\tchartName=\"建安单价造价曲线\";\r\n");
      out.write("\t\t\tdata['constructType']='constructType';\r\n");
      out.write("\t\t\t$(\"#remark\").html(\"备注:建安单价造价基准价：1530元/m2 \");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\telse{\r\n");
      out.write("\t\t\tchartName=\"工料指数曲线\";\r\n");
      out.write("\t\t\t$(\"#remark\").html(\"备注:工料价格指数基准价：733元/m2\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\tvar dateurl=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/prod/prod-report!jsonChartYearMonth.action\";\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//时间\r\n");
      out.write("\t\t$.post(dateurl,data,function(result){\r\n");
      out.write("\t\t\tyearMonth=eval(result);\r\n");
      out.write("\t\t\t});\t\r\n");
      out.write("\t\t//曲线数据\r\n");
      out.write("\t\tvar url=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/prod/prod-report!jsonChart.action\";\r\n");
      out.write("\t\t$.post(url,data,function(result){\r\n");
      out.write("\t\t\tif(result==''){$(\"#error\").show();return ;}else{$(\"#error\").hide();}\t\t\r\n");
      out.write("\t\t\tchart = new Highcharts.Chart({\r\n");
      out.write("\t\t\t\tchart: {\r\n");
      out.write("\t\t\t\t\trenderTo: 'container',\r\n");
      out.write("\t\t\t\t\tdefaultSeriesType: 'line',\r\n");
      out.write("\t\t\t\t\tmarginRight: 60,\r\n");
      out.write("\t\t\t\t\tmarginBottom: 25,\r\n");
      out.write("\t\t\t\t\tmarginTop :100,\r\n");
      out.write("\t\t\t\t\theight:350\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\ttitle: {\r\n");
      out.write("\t\t\t\t\ttext: title,//标题\r\n");
      out.write("\t\t\t\t\tx: -20, //center\r\n");
      out.write("\t\t\t\t\ty:70\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tsubtitle: {\r\n");
      out.write("\t\t\t\t\ttext: '',\r\n");
      out.write("\t\t\t\t\tx: -20,\r\n");
      out.write("\t\t\t\t\ty:440\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\texporting:{enabled :false},\r\n");
      out.write("\t\t\t\txAxis: {\r\n");
      out.write("\t\t\t\t\tcategories: yearMonth\t\t\t\t\t\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tyAxis: {\r\n");
      out.write("\t\t\t\t\ttitle: {\r\n");
      out.write("\t\t\t\t\t\ttext: chartName\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\tplotLines: [{\r\n");
      out.write("\t\t\t\t\t\tvalue: 0,\r\n");
      out.write("\t\t\t\t\t\twidth: 1,\r\n");
      out.write("\t\t\t\t\t\tcolor: '#808080'\r\n");
      out.write("\t\t\t\t\t}]\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\ttooltip: {\r\n");
      out.write("\t\t\t\t\tformatter: function() {\r\n");
      out.write("\t\t\t\t\t\t\tvar ds=this.x;\r\n");
      out.write("\t\t\t\t\t\t\tvar y=ds.substring(0,2);\r\n");
      out.write("\t\t\t\t\t\t\tvar m=ds.substring(2,4);\r\n");
      out.write("\t\t\t                return '<b>'+ this.series.name +'</b><br/>'+\r\n");
      out.write("\t\t\t                y +'年'+m+\"月:\"+ this.y ;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tlegend: {\r\n");
      out.write("\t\t\t\t\tlayout: 'horizontal',\r\n");
      out.write("\t\t\t\t\talign: 'top',\r\n");
      out.write("\t\t\t\t\tverticalAlign: 'center',\r\n");
      out.write("\t\t\t\t\tx: 20,\r\n");
      out.write("\t\t\t\t\ty: 0,\r\n");
      out.write("\t\t\t\t\twidth:700,\r\n");
      out.write("\t\t\t\t\titemWidth:100, \r\n");
      out.write("\t\t\t\t\tborderWidth: 0\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tseries:eval(result)\r\n");
      out.write("\t\t\t});\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\r\n");
      out.write("\t</script>\r\n");
      out.write("</body>\r\n");
      out.write("\t\r\n");
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
}
