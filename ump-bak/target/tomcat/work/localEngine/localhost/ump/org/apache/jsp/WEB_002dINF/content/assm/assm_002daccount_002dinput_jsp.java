package org.apache.jsp.WEB_002dINF.content.assm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.JspUtil;

public final class assm_002daccount_002dinput_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(2);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/WEB-INF/PowerDesk-tags.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005felse;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fp_005fcode2name_0026_005fvalue_005fmapCodeName_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvar_005fvalue;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005felse = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fp_005fcode2name_0026_005fvalue_005fmapCodeName_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvar_005fvalue = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fs_005felse.release();
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.release();
    _005fjspx_005ftagPool_005fp_005fcode2name_0026_005fvalue_005fmapCodeName_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvar_005fvalue.release();
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
      out.write("<div id=\"body\" class=\"publish\" style=\"margin-left: 10px;\">\r\n");
      out.write("\t<div class=\"title1 none\">\r\n");
      out.write("\t    <h2>新增资产</h2>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"informations none\"></div>\r\n");
      out.write("\t<div class=\"title\" style=\"margin-bottom: 5px;margin-left: 3px;\">\r\n");
      out.write("\t\t");
      if (_jspx_meth_s_005fif_005f0(_jspx_page_context))
        return;
      if (_jspx_meth_s_005felse_005f0(_jspx_page_context))
        return;
      out.write("固定资产\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"res_tip\" style=\"height: 45px;\">\r\n");
      out.write("\t\t<span style=\"margin-left: 10px;\">注意：1、“原值” 须大于或者等于500元；</span><br/>\r\n");
      out.write("\t\t<span style=\"margin-left: 46px;\">2、同一 “保管人员” 不能拥有两个或两个以上的电脑设备；</span><br/>\r\n");
      out.write("\t\t<span style=\"margin-left: 46px;\">3、“保管人员(PD)”和“保管人员(非PD)” 只能填写一个。</span>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"btns clearfix\" style=\"margin-top: 10px;\">\r\n");
      out.write("\t    ");
      if (_jspx_meth_s_005fif_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t");
      if (_jspx_meth_security_005fauthorize_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t");
      if (_jspx_meth_s_005fif_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t");
      if (_jspx_meth_s_005felse_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"body_warp\">\r\n");
      out.write("\t    <div class=\"form_body\" style=\"margin-top: 10px;margin-bottom: 10px;\">\r\n");
      out.write("           <form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/assm/assm-account!save.action\" id=\"accountFrom\" method=\"post\">\r\n");
      out.write("           \t\t<input type=\"hidden\" name=\"recordVersion\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${recordVersion}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"assmAccountId\" id=\"assmAccountId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${assmAccountId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t        \t<ul class=\"clearfix\">\r\n");
      out.write("\t                <li>\r\n");
      out.write("\t                    <label style=\"width: 90px;\">商业公司：</label>\r\n");
      out.write("\t                    ");
      if (_jspx_meth_s_005fif_005f3(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_s_005felse_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" name=\"projectCd\" id=\"projectCd\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${projectCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t                </li>\r\n");
      out.write("\t                <li>\r\n");
      out.write("\t                    <label style=\"width: 90px;\">设备型号：</label>\r\n");
      out.write("\t                    <input type=\"text\" \r\n");
      out.write("\t                    \t   class=\"text required\"\r\n");
      out.write("\t\t\t\t\t           name=\"assmModelName\" \r\n");
      out.write("\t\t\t\t\t           id=\"assmModelName\" \r\n");
      out.write("\t\t\t\t\t           readonly=\"readonly\"\r\n");
      out.write("\t\t\t\t\t\t       ");
      if (_jspx_meth_s_005fif_005f4(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t       ");
      if (_jspx_meth_s_005felse_005f3(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t   \t   />\r\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" id=\"assmModelId2\" name=\"assmModelId2\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${assmModel.assmModelId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" id=\"assmModelStandardId\" name=\"assmModelStandardId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${assmModelStandard.assmModelStandardId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<label style=\"width: 100px;\">型号编码：</label>\r\n");
      out.write("\t              \t\t<input type=\"text\" class=\"text required\" name=\"assmCode\" id=\"assmCode\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${assmCode}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" readonly=\"readonly\" style=\"width: 170px;background-color:#EDEFF3;\"/>\r\n");
      out.write("\t                </li>\r\n");
      out.write("\t                <li>\r\n");
      out.write("\t                \t<label style=\"width: 90px;\">资产名称：</label>\r\n");
      out.write("\t              \t\t<input type=\"text\" class=\"text max required\"  name=\"assmName\" id=\"assmName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${assmName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" style=\"width: 170px;\" maxlength=\"50\"/>\r\n");
      out.write("\t              \t\t<label style=\"width: 100px;\">资产编码：</label>\r\n");
      out.write("\t              \t\t<input type=\"text\" class=\"text max required\" name=\"code\" id=\"code\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${code}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" onblur=\"checkCode(this.value);\" style=\"width: 170px;\" maxlength=\"25\"/>\r\n");
      out.write("\t                </li>\r\n");
      out.write("\t                <li>\r\n");
      out.write("\t              \t\t<label style=\"width: 90px;\">使用部门：</label>\r\n");
      out.write("\t              \t\t<input type=\"text\" class=\"text max required\" name=\"useDepartmentName\" id=\"useDepartmentName\" value=\"");
      out.print( CodeNameUtil.getDeptNameByCd(JspUtil.findString("useDepartment")));
      out.write("\" readonly=\"readonly\" style=\"width: 451px;cursor: pointer;\" title=\"点击选择部门\"/>\r\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" id=\"useDepartment\" name=\"useDepartment\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${useDepartment}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t                </li>\r\n");
      out.write("\t                <li>\r\n");
      out.write("\t              \t\t<label style=\"width: 90px;\">保管人员(PD)：</label>\r\n");
      out.write("\t              \t\t<input type=\"text\" class=\"text max required\" name=\"keeperName\" id=\"keeperName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${keeperName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" readonly=\"readonly\" style=\"width: 170px;cursor: pointer;\" title=\"点击选择用户\"/> \r\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" id=\"keeperCd\" name=\"keeperCd\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${keeperCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t\t\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" id=\"computerType\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${computerModelName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" id=\"selectThridModelId\"/>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<label style=\"width: 100px;\">保管人员(非PD)：</label>\r\n");
      out.write("\t              \t\t<input type=\"text\" class=\"text max required\" name=\"keeperName2\" id=\"keeperName2\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${keeperName2}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" onblur=\"clearKeeperName(this);\" style=\"width: 170px;\" title=\"请输入无PD账号的保管人员\"/> \r\n");
      out.write("\t                </li>\r\n");
      out.write("\t                <li>\r\n");
      out.write("\t                \t<label style=\"width: 90px;\">使用情况：</label>\r\n");
      out.write("\t              \t\t");
      if (_jspx_meth_s_005fselect_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t              \t\t<label style=\"width: 100px;\">购入时间：</label>\r\n");
      out.write("\t              \t\t<input type=\"text\" \r\n");
      out.write("\t              \t\t\t   class=\"text max required\"\r\n");
      out.write("\t\t\t\t\t\t       name=\"useDate\" \r\n");
      out.write("\t\t\t\t\t\t       id=\"useDate\" \r\n");
      out.write("\t\t\t\t\t\t       readonly=\"readonly\" \r\n");
      out.write("\t\t\t\t\t\t       value=\"");
      if (_jspx_meth_s_005fdate_005f0(_jspx_page_context))
        return;
      out.write("\" \r\n");
      out.write("\t\t\t\t\t       \t   style=\"width: 170px;\"\r\n");
      out.write("\t\t\t\t\t       \t   onfocus=\"WdatePicker()\"\r\n");
      out.write("\t\t\t\t\t\t/>\r\n");
      out.write("\t                </li>\r\n");
      out.write("\t                <li>\r\n");
      out.write("\t              \t\t<label style=\"width: 90px;\">存放地点：</label>\r\n");
      out.write("\t              \t\t");
      if (_jspx_meth_s_005fselect_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t                \t<label style=\"width: 100px;\">增加方式：</label>\r\n");
      out.write("\t              \t\t");
      if (_jspx_meth_s_005fselect_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t                </li>\r\n");
      out.write("\t                <li>\r\n");
      out.write("\t              \t\t<label style=\"width: 90px;\">原值&nbsp;(元)：</label>\r\n");
      out.write("\t              \t\t<input type=\"text\" \r\n");
      out.write("\t              \t\t\t   class=\"text max required\"\r\n");
      out.write("\t\t\t\t\t           name=\"srcValue\" \r\n");
      out.write("\t\t\t\t\t           id=\"srcValue\" \r\n");
      out.write("\t\t\t\t\t       \t   value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${srcValue}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" \r\n");
      out.write("\t\t\t\t\t       \t   style=\"width: 170px;\"\r\n");
      out.write("\t\t\t\t\t       \t   ");
      if (_jspx_meth_s_005fif_005f5(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t       ");
      if (_jspx_meth_s_005felse_005f4(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t       maxlength=\"10\"\r\n");
      out.write("\t\t\t\t\t           title=\"原值须大于等于500\"\r\n");
      out.write("\t\t\t\t\t    />\r\n");
      out.write("\t\t\t\t\t    <label style=\"width: 100px;\">净值&nbsp;(元)：</label>\r\n");
      out.write("\t              \t\t<input type=\"text\" class=\"text max required\" name=\"remainVal\" id=\"remainVal\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${remainVal}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" readonly=\"readonly\" style=\"width: 170px;background-color:#EDEFF3;\"/>\r\n");
      out.write("\t                </li>\r\n");
      out.write("\t                <li style=\"display: none;\" id=\"netValueLi\">\r\n");
      out.write("\t              \t\t<label style=\"width: 90px;\">残值&nbsp;(元)：</label>\r\n");
      out.write("\t              \t\t<input type=\"text\" id=\"netValue\" name=\"netValue\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${netValue}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" readonly=\"readonly\" onkeyup=\"clearNoNum_1(this);\" maxlength=\"10\" class=\"text max\" style=\"width: 451px;background-color:#EDEFF3;\" title=\"只有当净值为0或报废状态才能填写\"/>\r\n");
      out.write("\t                </li>\r\n");
      out.write("\t                <li>\r\n");
      out.write("\t              \t\t<label style=\"width: 90px;\">折旧年限：</label>\r\n");
      out.write("\t              \t\t<input type=\"text\" class=\"text max required\" name=\"depreYeadNum\" id=\"depreYeadNum\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${depreYeadNum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" readonly=\"readonly\" style=\"width: 170px;background-color:#EDEFF3;\"/>\r\n");
      out.write("\t                \t<label style=\"width: 100px;\">折旧方式：</label>\r\n");
      out.write("\t              \t\t<input type=\"text\" class=\"text max required\" name=\"depreWay\" id=\"depreWay\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${depreWay}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" readonly=\"readonly\" style=\"width: 170px;background-color:#EDEFF3;\"/>\r\n");
      out.write("\t                </li>\r\n");
      out.write("\t                <li>\r\n");
      out.write("\t              \t\t<label style=\"width: 90px;\">当前配置：</label>\r\n");
      out.write("\t              \t\t<input type=\"text\" class=\"text max required\" id=\"currHasNum\" readonly=\"readonly\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currNum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" style=\"width: 170px;background-color:#EDEFF3;\"/>\r\n");
      out.write("\t                \t<label style=\"width: 100px;\">标准配置：</label>\r\n");
      out.write("\t              \t\t<input type=\"text\" class=\"text max required\" id=\"currStanNum\" readonly=\"readonly\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${stanNum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" style=\"width: 170px;background-color:#EDEFF3;\"/>\r\n");
      out.write("\t                \r\n");
      out.write("\t                </li>\r\n");
      out.write("\t        \t</ul>\r\n");
      out.write("           </form>\r\n");
      out.write("\t    </div>\r\n");
      out.write("\t    <div class=\"form_body\" style=\"margin-top: 10px;margin-bottom: 10px;\">\r\n");
      out.write("\t\t");
      if (_jspx_meth_s_005fiterator_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t    </div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("$(function(){\r\n");
      out.write("\tnetValueChange(null);\r\n");
      out.write("\t//选择保管人员\r\n");
      out.write("\t$(\"#keeperName\").userSelect({\r\n");
      out.write("        muti:false,\r\n");
      out.write("        nameField:'keeperName',\r\n");
      out.write("        cdField:'keeperCd',\r\n");
      out.write("        callfun:\"clearKeeperName2()\"\r\n");
      out.write("\t});\r\n");
      out.write("\t//选择使用部门\r\n");
      out.write("\t$('#useDepartmentName').orgSelect({});\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("//选择设备型号\r\n");
      out.write("function doAssmModel(){\r\n");
      out.write("\tymPrompt.confirmInfo( {\r\n");
      out.write("\t\ticoCls : \"\",\r\n");
      out.write("\t\tautoClose:false,\r\n");
      out.write("\t\tmessage : \"<div id='selectTypeDiv'><img align='absMiddle' src='\"\r\n");
      out.write("\t\t\t+ _ctx + \"/images/loading.gif'></div>\",\r\n");
      out.write("\t\twidth : 580,\r\n");
      out.write("\t\theight : 500,\r\n");
      out.write("\t\ttitle : \"设备型号选择\",\r\n");
      out.write("\t\tafterShow : function() {\r\n");
      out.write("\t\t\tvar url = _ctx+\"/assm/assm-account!getAssmModel.action\";\r\n");
      out.write("\t\t\t$.post(url, {}, function(result) {\r\n");
      out.write("\t\t\t\t$(\"#selectTypeDiv\").html(result);\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\thandler : function(btn){\r\n");
      out.write("\t\t\tif(btn=='ok'){\r\n");
      out.write("\t\t\t\t//选中的三级设备名称(用于判断‘电脑’类的设备保管人只能有一个)\r\n");
      out.write("\t\t\t\tvar selectTwoAssmName = $('#selectThridAssmName').val();\r\n");
      out.write("\t\t\t\t$(\"#computerType\").val(selectTwoAssmName);\r\n");
      out.write("\t\t\t\t$(\"#selectThridModelId\").val($('#selectThridAssmId').val());\r\n");
      out.write("\t\t\t\t//选中的第四级设备的ID\r\n");
      out.write("\t\t\t\tvar selectAssmModelId = $('#selectAssmModelId').val();\r\n");
      out.write("\t\t\t\t//选中的第四级设备的名称\r\n");
      out.write("\t\t\t\tvar selectAssmModelName = $('#selectAssmModelName').val();\r\n");
      out.write("\t\t\t\tvar projectCd = $('#projectCd').val();\r\n");
      out.write("\t\t\t\tif(selectAssmModelId == null || selectAssmModelId ==''){\r\n");
      out.write("\t\t\t\t\talert('请选择四级设备');\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tvar data ={\r\n");
      out.write("\t\t\t\t\tassmModelId:selectAssmModelId,\r\n");
      out.write("\t\t\t\t\tprojectCd:projectCd\r\n");
      out.write("\t\t\t\t};\r\n");
      out.write("\t\t\t\t//根据设备型号自动带出型号编码、残值、当前配置、标准配置等\r\n");
      out.write("\t\t\t\tvar url1 = _ctx+\"/assm/assm-account!getValues.action\";\r\n");
      out.write("\t\t\t\t$.post(url1,data, function(result) {\r\n");
      out.write("\t\t\t\t\t$(\"#assmModelId2\").val(selectAssmModelId);\r\n");
      out.write("\t\t\t\t\t$(\"#assmModelName\").val(selectAssmModelName);\r\n");
      out.write("\t\t\t\t\tif(result != null && \"\" != result){\r\n");
      out.write("\t\t\t\t\t\tif(\"error\"==result){\r\n");
      out.write("\t\t\t\t\t\t\talert(\"该商业公司未在资产台账中维护\");\r\n");
      out.write("\t\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t\tvar rs=result.split(\",\");\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#assmCode\").val(rs[0]);\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#assmModelStandardId\").val(rs[1]);\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#currStanNum\").val(rs[2]);\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#currHasNum\").val(rs[3]);\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#depreYeadNum\").val(rs[4]);\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#depreWay\").val(rs[5]);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("//\t\t\t\t\t$(\"#selectTypeDiv\").html(result);\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tymPrompt.close();\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tbtn:[[\"确定\",'ok'],[\"退出\",'cancel']]\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//校验资产编码唯一性\r\n");
      out.write("function checkCode(value){\r\n");
      out.write("\tvar id = $(\"#assmAccountId\").val();\r\n");
      out.write("\tvar url = _ctx+\"/assm/assm-account!checkCode.action\";\r\n");
      out.write("\t$.post(url,{code:$.trim(value),id:id}, function(result) {\r\n");
      out.write("\t\tif(result=='true'){\r\n");
      out.write("\t\t\talert(\"资产编码已经存在\");\r\n");
      out.write("\t\t\t//$(\"#code\").focus();\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//校验保管人员(同一“保管人员”不能拥有两个或两个以上的设备)\r\n");
      out.write("function checkKeeper(){\r\n");
      out.write("\tvar keeperCd = $(\"#keeperCd\").val();\r\n");
      out.write("\tvar id = $(\"#assmAccountId\").val();\r\n");
      out.write("\tvar url = _ctx+\"/assm/assm-account!checkKeeper.action\";\r\n");
      out.write("\t$.post(url,{keeperCd:$.trim(keeperCd),id:id}, function(result) {\r\n");
      out.write("\t\tif(result=='true'){\r\n");
      out.write("\t\t\talert(\"该保管人员已经保管有设备\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\treturn true;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//当净值为0活使用情况为报废时残值可编辑\r\n");
      out.write("function netValueChange(useState){\r\n");
      out.write("\tvar assmAccountId = $(\"#assmAccountId\").val();\r\n");
      out.write("\tvar remainVal = $(\"#remainVal\").val();\r\n");
      out.write("\tvar value;\r\n");
      out.write("\tif(useState != null && useState != ''){\r\n");
      out.write("\t\tvalue = useState;\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tvalue = $(\"#useStatus\").val();\r\n");
      out.write("\t}\r\n");
      out.write("\tif(value=='3'){\r\n");
      out.write("\t\t$(\"#netValueLi\").show();\r\n");
      out.write("\t\t$(\"#netValue\").attr(\"readonly\",\"\");\r\n");
      out.write("\t\t$(\"#netValue\").attr(\"style\",\"width: 451px;background-color:#FFFFFF\");\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tif(assmAccountId != null && assmAccountId != ''){\r\n");
      out.write("\t\t\tif(remainVal != null && remainVal == '0'){\r\n");
      out.write("\t\t\t\t$(\"#netValueLi\").show();\r\n");
      out.write("\t\t\t\t$(\"#netValue\").attr(\"readonly\",\"\");\r\n");
      out.write("\t\t\t\t$(\"#netValue\").attr(\"style\",\"width: 451px;background-color:#FFFFFF\");\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\t$(\"#netValueLi\").hide();\r\n");
      out.write("\t\t\t\t$(\"#netValue\").val('');\r\n");
      out.write("\t\t\t\t$(\"#netValue\").attr(\"readonly\",\"readonly\");\r\n");
      out.write("\t\t\t\t$(\"#netValue\").attr(\"style\",\"width: 451px;background-color:#EDEFF3\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\t$(\"#netValueLi\").hide();\r\n");
      out.write("\t\t\t$(\"#netValue\").val('');\r\n");
      out.write("\t\t\t$(\"#netValue\").attr(\"readonly\",\"readonly\");\r\n");
      out.write("\t\t\t$(\"#netValue\").attr(\"style\",\"width: 451px;background-color:#EDEFF3\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function getRemain(value){\r\n");
      out.write("\t$(\"#remainVal\").val(value);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function clearKeeperName(dom){\r\n");
      out.write("\tif($.trim(dom.value) != ''){\r\n");
      out.write("\t\t$(\"#keeperName\").val('');\r\n");
      out.write("\t\t$(\"#keeperCd\").val('');\r\n");
      out.write("\t\tvar\t_userMap = {};\r\n");
      out.write("\t\tvar o = {userName:'',uiid:''};\r\n");
      out.write("\t\t_userMap[$(\"#keeperName\").val()] = o;\r\n");
      out.write("\t\tvar data = $.extend(true,{},null);\r\n");
      out.write("\t\t$(\"#keeperName\").data('userMap',data);\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function clearKeeperName2(){\r\n");
      out.write("\tvar keepName = $(\"#keeperName\").val();\r\n");
      out.write("\tif($.trim(keepName) != ''){\r\n");
      out.write("\t\t$(\"#keeperName2\").val('');\r\n");
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
    // /WEB-INF/content/assm/assm-account-input.jsp(12,2) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f0.setTest("assmAccountId!=null && assmAccountId!=''");
    int _jspx_eval_s_005fif_005f0 = _jspx_th_s_005fif_005f0.doStartTag();
    if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f0.doInitBody();
      }
      do {
        out.write('修');
        out.write('改');
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
        out.write('新');
        out.write('增');
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
    // /WEB-INF/content/assm/assm-account-input.jsp(21,5) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f1.setTest("assmAccountId!=null && assmAccountId!=''");
    int _jspx_eval_s_005fif_005f1 = _jspx_th_s_005fif_005f1.doStartTag();
    if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t <button type=\"button\" class=\"green min\" onclick=\"loadAccount();\">返回</button>\r\n");
        out.write("\t\t");
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

  private boolean _jspx_meth_security_005fauthorize_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f0 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f0.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f0.setParent(null);
    // /WEB-INF/content/assm/assm-account-input.jsp(24,2) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f0.setIfAnyGranted("A_ASSM_ACC_EDIT");
    int _jspx_eval_security_005fauthorize_005f0 = _jspx_th_security_005fauthorize_005f0.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t <button type=\"button\" class=\"green min\" onclick=\"saveAccount();\">保存</button>\r\n");
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

  private boolean _jspx_meth_s_005fif_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f2 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f2.setParent(null);
    // /WEB-INF/content/assm/assm-account-input.jsp(27,2) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f2.setTest("assmAccountId!=null && assmAccountId!=''");
    int _jspx_eval_s_005fif_005f2 = _jspx_th_s_005fif_005f2.doStartTag();
    if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t");
        if (_jspx_meth_security_005fauthorize_005f1(_jspx_th_s_005fif_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t");
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

  private boolean _jspx_meth_security_005fauthorize_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f1 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f1.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f2);
    // /WEB-INF/content/assm/assm-account-input.jsp(28,3) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f1.setIfAnyGranted("A_ASSM_ACC_DEL");
    int _jspx_eval_security_005fauthorize_005f1 = _jspx_th_security_005fauthorize_005f1.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t<button type=\"button\" class=\"red min\" onclick=\"deleteAccount('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${assmAccountId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("');\">删除</button>\r\n");
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
        out.write("\t\t\t <button type=\"button\" class=\"red min\" onclick=\"canleAccount();\">取消</button>\r\n");
        out.write("\t\t");
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

  private boolean _jspx_meth_s_005fif_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f3 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f3.setParent(null);
    // /WEB-INF/content/assm/assm-account-input.jsp(45,21) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f3.setTest("assmAccountId!=null && assmAccountId!=''");
    int _jspx_eval_s_005fif_005f3 = _jspx_th_s_005fif_005f3.doStartTag();
    if (_jspx_eval_s_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f3.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t<input type=\"text\" id=\"projectName\" value=\"");
        if (_jspx_meth_p_005fcode2name_005f0(_jspx_th_s_005fif_005f3, _jspx_page_context))
          return true;
        out.write("\" class=\"text max required\" style=\"width: 451px;background-color:#EDEFF3;\"/>\r\n");
        out.write("\t\t\t\t\t\t");
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

  private boolean _jspx_meth_p_005fcode2name_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  p:code2name
    com.hhz.core.tags.Code2NameTag _jspx_th_p_005fcode2name_005f0 = (com.hhz.core.tags.Code2NameTag) _005fjspx_005ftagPool_005fp_005fcode2name_0026_005fvalue_005fmapCodeName_005fnobody.get(com.hhz.core.tags.Code2NameTag.class);
    _jspx_th_p_005fcode2name_005f0.setPageContext(_jspx_page_context);
    _jspx_th_p_005fcode2name_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f3);
    // /WEB-INF/content/assm/assm-account-input.jsp(46,50) name = mapCodeName type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_p_005fcode2name_005f0.setMapCodeName("mapBisProject");
    // /WEB-INF/content/assm/assm-account-input.jsp(46,50) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_p_005fcode2name_005f0.setValue("projectCd");
    int _jspx_eval_p_005fcode2name_005f0 = _jspx_th_p_005fcode2name_005f0.doStartTag();
    if (_jspx_th_p_005fcode2name_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fp_005fcode2name_0026_005fvalue_005fmapCodeName_005fnobody.reuse(_jspx_th_p_005fcode2name_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fp_005fcode2name_0026_005fvalue_005fmapCodeName_005fnobody.reuse(_jspx_th_p_005fcode2name_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005felse_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:else
    org.apache.struts2.views.jsp.ElseTag _jspx_th_s_005felse_005f2 = (org.apache.struts2.views.jsp.ElseTag) _005fjspx_005ftagPool_005fs_005felse.get(org.apache.struts2.views.jsp.ElseTag.class);
    _jspx_th_s_005felse_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005felse_005f2.setParent(null);
    int _jspx_eval_s_005felse_005f2 = _jspx_th_s_005felse_005f2.doStartTag();
    if (_jspx_eval_s_005felse_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felse_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felse_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felse_005f2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t<input type=\"text\" id=\"projectName\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${deptName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" readonly=\"readonly\" class=\"text max required\" style=\"width: 451px;background-color:#EDEFF3;\"/>\r\n");
        out.write("\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_005felse_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005felse_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005felse_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f2);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f4 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f4.setParent(null);
    // /WEB-INF/content/assm/assm-account-input.jsp(60,13) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f4.setTest("assmAccountId!=null && assmAccountId!=''");
    int _jspx_eval_s_005fif_005f4 = _jspx_th_s_005fif_005f4.doStartTag();
    if (_jspx_eval_s_005fif_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f4.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t       value=\"");
        if (_jspx_meth_p_005fcode2name_005f1(_jspx_th_s_005fif_005f4, _jspx_page_context))
          return true;
        out.write("\" \r\n");
        out.write("\t\t\t\t\t\t\t       style=\"width: 170px;background-color:#EDEFF3;\"\r\n");
        out.write("\t\t\t\t\t\t       ");
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
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.reuse(_jspx_th_s_005fif_005f4);
    return false;
  }

  private boolean _jspx_meth_p_005fcode2name_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fif_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  p:code2name
    com.hhz.core.tags.Code2NameTag _jspx_th_p_005fcode2name_005f1 = (com.hhz.core.tags.Code2NameTag) _005fjspx_005ftagPool_005fp_005fcode2name_0026_005fvalue_005fmapCodeName_005fnobody.get(com.hhz.core.tags.Code2NameTag.class);
    _jspx_th_p_005fcode2name_005f1.setPageContext(_jspx_page_context);
    _jspx_th_p_005fcode2name_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fif_005f4);
    // /WEB-INF/content/assm/assm-account-input.jsp(61,21) name = mapCodeName type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_p_005fcode2name_005f1.setMapCodeName("mapAssmModelAll");
    // /WEB-INF/content/assm/assm-account-input.jsp(61,21) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_p_005fcode2name_005f1.setValue("assmModel.assmModelId");
    int _jspx_eval_p_005fcode2name_005f1 = _jspx_th_p_005fcode2name_005f1.doStartTag();
    if (_jspx_th_p_005fcode2name_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fp_005fcode2name_0026_005fvalue_005fmapCodeName_005fnobody.reuse(_jspx_th_p_005fcode2name_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fp_005fcode2name_0026_005fvalue_005fmapCodeName_005fnobody.reuse(_jspx_th_p_005fcode2name_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005felse_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:else
    org.apache.struts2.views.jsp.ElseTag _jspx_th_s_005felse_005f3 = (org.apache.struts2.views.jsp.ElseTag) _005fjspx_005ftagPool_005fs_005felse.get(org.apache.struts2.views.jsp.ElseTag.class);
    _jspx_th_s_005felse_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005felse_005f3.setParent(null);
    int _jspx_eval_s_005felse_005f3 = _jspx_th_s_005felse_005f3.doStartTag();
    if (_jspx_eval_s_005felse_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felse_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felse_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felse_005f3.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t       \t   style=\"width: 170px;cursor:pointer;\"\r\n");
        out.write("\t\t\t\t\t\t\t       onclick=\"doAssmModel();\" \r\n");
        out.write("\t\t\t\t\t\t\t       title=\"点击选择设备型号\" \r\n");
        out.write("\t\t\t\t\t\t       ");
        int evalDoAfterBody = _jspx_th_s_005felse_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005felse_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005felse_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f3);
    return false;
  }

  private boolean _jspx_meth_s_005fselect_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f0 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f0.setParent(null);
    // /WEB-INF/content/assm/assm-account-input.jsp(103,17) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setList("mapUseStatus");
    // /WEB-INF/content/assm/assm-account-input.jsp(103,17) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setListKey("key");
    // /WEB-INF/content/assm/assm-account-input.jsp(103,17) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setListValue("value");
    // /WEB-INF/content/assm/assm-account-input.jsp(103,17) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setName("useStatus");
    // /WEB-INF/content/assm/assm-account-input.jsp(103,17) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setId("useStatus");
    // /WEB-INF/content/assm/assm-account-input.jsp(103,17) name = onchange type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setOnchange("netValueChange(this.value);");
    // /WEB-INF/content/assm/assm-account-input.jsp(103,17) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setCssClass("box required");
    // /WEB-INF/content/assm/assm-account-input.jsp(103,17) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setCssStyle("width:180px;");
    int _jspx_eval_s_005fselect_005f0 = _jspx_th_s_005fselect_005f0.doStartTag();
    if (_jspx_th_s_005fselect_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody.reuse(_jspx_th_s_005fselect_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fonchange_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody.reuse(_jspx_th_s_005fselect_005f0);
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
    // /WEB-INF/content/assm/assm-account-input.jsp(110,20) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f0.setName("useDate");
    // /WEB-INF/content/assm/assm-account-input.jsp(110,20) name = format type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f0.setFormat("yyyy-MM-dd");
    int _jspx_eval_s_005fdate_005f0 = _jspx_th_s_005fdate_005f0.doStartTag();
    if (_jspx_th_s_005fdate_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fselect_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f1 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f1.setParent(null);
    // /WEB-INF/content/assm/assm-account-input.jsp(117,17) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setList("mapStorageSites");
    // /WEB-INF/content/assm/assm-account-input.jsp(117,17) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setListKey("key");
    // /WEB-INF/content/assm/assm-account-input.jsp(117,17) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setListValue("value");
    // /WEB-INF/content/assm/assm-account-input.jsp(117,17) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setName("storageSites");
    // /WEB-INF/content/assm/assm-account-input.jsp(117,17) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setId("storageSites");
    // /WEB-INF/content/assm/assm-account-input.jsp(117,17) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setCssClass("box");
    // /WEB-INF/content/assm/assm-account-input.jsp(117,17) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f1.setCssStyle("width:180px;");
    int _jspx_eval_s_005fselect_005f1 = _jspx_th_s_005fselect_005f1.doStartTag();
    if (_jspx_th_s_005fselect_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody.reuse(_jspx_th_s_005fselect_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody.reuse(_jspx_th_s_005fselect_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005fselect_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f2 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f2.setParent(null);
    // /WEB-INF/content/assm/assm-account-input.jsp(119,17) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setList("mapAddWays");
    // /WEB-INF/content/assm/assm-account-input.jsp(119,17) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setListKey("key");
    // /WEB-INF/content/assm/assm-account-input.jsp(119,17) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setListValue("value");
    // /WEB-INF/content/assm/assm-account-input.jsp(119,17) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setName("addWay");
    // /WEB-INF/content/assm/assm-account-input.jsp(119,17) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setId("addWay");
    // /WEB-INF/content/assm/assm-account-input.jsp(119,17) name = cssClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setCssClass("box");
    // /WEB-INF/content/assm/assm-account-input.jsp(119,17) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f2.setCssStyle("width:180px;");
    int _jspx_eval_s_005fselect_005f2 = _jspx_th_s_005fselect_005f2.doStartTag();
    if (_jspx_th_s_005fselect_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody.reuse(_jspx_th_s_005fselect_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fcssStyle_005fcssClass_005fnobody.reuse(_jspx_th_s_005fselect_005f2);
    return false;
  }

  private boolean _jspx_meth_s_005fif_005f5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f5 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f5.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f5.setParent(null);
    // /WEB-INF/content/assm/assm-account-input.jsp(129,16) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f5.setTest("assmAccountId!=null && assmAccountId!=''");
    int _jspx_eval_s_005fif_005f5 = _jspx_th_s_005fif_005f5.doStartTag();
    if (_jspx_eval_s_005fif_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f5.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t      \t \tonkeyup=\"clearNoNum_1(this);\" \r\n");
        out.write("\t\t\t\t\t\t       ");
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

  private boolean _jspx_meth_s_005felse_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:else
    org.apache.struts2.views.jsp.ElseTag _jspx_th_s_005felse_005f4 = (org.apache.struts2.views.jsp.ElseTag) _005fjspx_005ftagPool_005fs_005felse.get(org.apache.struts2.views.jsp.ElseTag.class);
    _jspx_th_s_005felse_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005felse_005f4.setParent(null);
    int _jspx_eval_s_005felse_005f4 = _jspx_th_s_005felse_005f4.doStartTag();
    if (_jspx_eval_s_005felse_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005felse_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005felse_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005felse_005f4.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t       \t\tonkeyup=\"clearNoNum_1(this);getRemain(this.value);\" \r\n");
        out.write("\t\t\t\t\t\t       ");
        int evalDoAfterBody = _jspx_th_s_005felse_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005felse_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005felse_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f4);
    return false;
  }

  private boolean _jspx_meth_s_005fiterator_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_005fiterator_005f0 = (org.apache.struts2.views.jsp.IteratorTag) _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvar_005fvalue.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_005fiterator_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fiterator_005f0.setParent(null);
    // /WEB-INF/content/assm/assm-account-input.jsp(162,2) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f0.setValue("res");
    // /WEB-INF/content/assm/assm-account-input.jsp(162,2) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fiterator_005f0.setVar("r");
    int _jspx_eval_s_005fiterator_005f0 = _jspx_th_s_005fiterator_005f0.doStartTag();
    if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fiterator_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fiterator_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fiterator_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t<ul>\r\n");
        out.write("\t\t<li>\r\n");
        out.write("\t\t<span>");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${r.authTypeName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</span>\r\n");
        out.write("\t\t<span>");
        if (_jspx_meth_s_005fdate_005f1(_jspx_th_s_005fiterator_005f0, _jspx_page_context))
          return true;
        out.write("</span>\r\n");
        out.write("\t\t<span>");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${r.displayNo}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</span>\r\n");
        out.write("\t\t<span style=\"cursor: pointer; text-decoration: underline;\" onclick=\"parent.showAll('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("/res/res-approve-info.action?id=");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${r.resApproveInfoId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("','resApprove');\">审批内容</span>\r\n");
        out.write("\t\t</li>\r\n");
        out.write("\t\t</ul>\r\n");
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
      _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvar_005fvalue.reuse(_jspx_th_s_005fiterator_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fiterator_0026_005fvar_005fvalue.reuse(_jspx_th_s_005fiterator_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fdate_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fiterator_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:date
    org.apache.struts2.views.jsp.DateTag _jspx_th_s_005fdate_005f1 = (org.apache.struts2.views.jsp.DateTag) _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.get(org.apache.struts2.views.jsp.DateTag.class);
    _jspx_th_s_005fdate_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fdate_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fiterator_005f0);
    // /WEB-INF/content/assm/assm-account-input.jsp(166,8) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f1.setName("#r.completeDate");
    // /WEB-INF/content/assm/assm-account-input.jsp(166,8) name = format type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fdate_005f1.setFormat("yyyy-MM-dd");
    int _jspx_eval_s_005fdate_005f1 = _jspx_th_s_005fdate_005f1.doStartTag();
    if (_jspx_th_s_005fdate_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fdate_0026_005fname_005fformat_005fnobody.reuse(_jspx_th_s_005fdate_005f1);
    return false;
  }
}
