package org.apache.jsp.WEB_002dINF.content.oa;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import com.hhz.ump.web.oa.CompanyViFileVO;
import com.hhz.ump.entity.oa.OaFilingFile;
import com.hhz.ump.entity.app.AppAttachFile;

public final class company_002dfile_002dfileList_jsp extends org.apache.jasper.runtime.HttpJspBase
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
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005felse;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fform_0026_005fmethod_005fid_005faction;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005furl_0026_005fnamespace_005fid_005faction;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fparam_0026_005fname;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005felse = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fform_0026_005fmethod_005fid_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005furl_0026_005fnamespace_005fid_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fparam_0026_005fname = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.release();
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fs_005felse.release();
    _005fjspx_005ftagPool_005fs_005fform_0026_005fmethod_005fid_005faction.release();
    _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005furl_0026_005fnamespace_005fid_005faction.release();
    _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.release();
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
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function viewDetails(fileName,path,title){\r\n");
      out.write("\tvar url = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("'+path;\r\n");
      out.write("\twindow.parent.TabUtils.newTab(\"viFile\",title,url,true);\r\n");
      out.write("\t\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<!-- 分页信息 -->\r\n");
      out.write("<div class=\"right_page_bar\">\r\n");
      out.write("\t");
      if (_jspx_meth_security_005fauthorize_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t <div class=\"fRight\">\r\n");
      out.write("\t\t<input type=\"text\" id=\"pageNo\" style=\"height: 15px; width: 20px; text-align: center;\"/> \r\n");
      out.write("\t \t<a href=\"javascript: jumpTo('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${folderId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("', $('#pageNo').val());\">GO</a>\r\n");
      out.write("\t </div>\r\n");
      out.write("\t");
      if (_jspx_meth_s_005fif_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      out.write('	');
      if (_jspx_meth_s_005felse_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      out.write('	');
      if (_jspx_meth_s_005fif_005f1(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      out.write('	');
      if (_jspx_meth_s_005felse_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t \r\n");
      out.write("\t<div style=\"float: right;\">\r\n");
      out.write("\t共有&nbsp;");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${filePage.totalCount}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("&nbsp;条记录&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t 当前第 ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${filePage.pageNo}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write('/');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${filePage.totalPages}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("&nbsp;页\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<!-- 列表信息 -->\r\n");
      out.write("<div style=\" overflow-y: auto; overflow-x: hidden;\">\r\n");
      out.write("\t");
      if (_jspx_meth_s_005fif_005f2(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      out.write('	');
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
          out.write("\t\t");
          //  s:form
          org.apache.struts2.views.jsp.ui.FormTag _jspx_th_s_005fform_005f0 = (org.apache.struts2.views.jsp.ui.FormTag) _005fjspx_005ftagPool_005fs_005fform_0026_005fmethod_005fid_005faction.get(org.apache.struts2.views.jsp.ui.FormTag.class);
          _jspx_th_s_005fform_005f0.setPageContext(_jspx_page_context);
          _jspx_th_s_005fform_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005felse_005f2);
          // /WEB-INF/content/oa/company-file-fileList.jsp(52,2) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_s_005fform_005f0.setId("fileListForm");
          // /WEB-INF/content/oa/company-file-fileList.jsp(52,2) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_s_005fform_005f0.setAction("/oa/company-file!fileList.action");
          // /WEB-INF/content/oa/company-file-fileList.jsp(52,2) name = method type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_s_005fform_005f0.setMethod("post");
          int _jspx_eval_s_005fform_005f0 = _jspx_th_s_005fform_005f0.doStartTag();
          if (_jspx_eval_s_005fform_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_s_005fform_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_s_005fform_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_s_005fform_005f0.doInitBody();
            }
            do {
              out.write("\r\n");
              out.write("\t\t\t");
              if (_jspx_meth_s_005fhidden_005f0(_jspx_th_s_005fform_005f0, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t\t\t<input type=\"hidden\" name=\"filePage.pageNo\" id=\"filePageNo\" value=\"");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${filePage.pageNo}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
              out.write("\" />\r\n");
              out.write("\t\t\t<input type=\"hidden\" name=\"totalPageSize\" id=\"totalPageSize\" value=\"");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${filePage.totalPages}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
              out.write("\" />\r\n");
              out.write("\t\t\r\n");
              out.write("\t\t\t<div>\r\n");
              out.write("\t\t\t\t<table class=\"file_list_table\" cellpadding=\"0\" cellspacing=\"0\">\r\n");
              out.write("\t\t\t\t\t<thead>\r\n");
              out.write("\t\t\t\t\t\t<tr class=\"header\">\r\n");
              out.write("\t\t\t\t\t\t\t<td style=\"background-image: none;\">文件名称</td>\r\n");
              out.write("\t\t\t\t\t\t\t<td>附件</td>\r\n");
              out.write("\t\t\t\t\t\t\t");
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t");
              if (_jspx_meth_security_005fauthorize_005f1(_jspx_th_s_005fform_005f0, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t\t\t\t\t\t</tr>\r\n");
              out.write("\t\t\t\t\t</thead>\r\n");
              out.write("\t\t\t\t\t\r\n");
              out.write("\t\t\t\t\t<tbody>\r\n");
              out.write("\t\t\t\t\t\t\r\n");
              out.write("\t\t\t\t\t\t\t");

							 List<CompanyViFileVO> fileVoLists = (List)request.getAttribute("fileVoList");
							 if (fileVoLists.size()!=0){
								 for(CompanyViFileVO vo :fileVoLists){
									 String tem  = vo.getFile().getRemark();
									 
								 
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t\t <tr class=\"data\">\r\n");
              out.write("\t\t\t\t\t\t\t\t\t ");

									 if(tem!=null){
										 String[] ids = tem.split(",");
										 String tempId = null;
										 String aHref = "";
										 if (ids.length==1){
											 aHref = ids[0];
											 tempId = "";
										 }
										 else if (ids.length>1){
											 aHref = ids[0];
											 tempId = ids[1];
										 }
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t <td title=\"");
              out.print(vo.getFile().getFileName() );
              out.write("\">\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t<div style=\"color:#5A5A5A;\">\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t<a style=\"color:blue;font-weight: bold;text-decoration: underline;\" href=\"javascript:viewDetails('");
              out.print(vo.getFile().getFileName() );
              out.write('\'');
              out.write(',');
              out.write('\'');
              out.print(aHref );
              out.write('\'');
              out.write(',');
              out.write('\'');
              out.print(tempId );
              out.write("');\">");
              out.print(vo.getFile().getFileName() );
              out.write("</a>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t</td>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t ");
}else{
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t\t <td title=\"");
              out.print(vo.getFile().getFileName() );
              out.write("\">\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t<div style=\"color:#5A5A5A;\">\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t");
              out.print(vo.getFile().getFileName() );
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t </td>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t ");
}
									 
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t<td title=\"附件列表\" class=\"attach_file_list\">\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t");

											 if (vo.getAttachList().size() > 0){
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t <ul style =\"list-style: none outside none;\">\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t");

													for(AppAttachFile af : vo.getAttachList()){
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<li>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
              //  s:url
              org.apache.struts2.views.jsp.URLTag _jspx_th_s_005furl_005f0 = (org.apache.struts2.views.jsp.URLTag) _005fjspx_005ftagPool_005fs_005furl_0026_005fnamespace_005fid_005faction.get(org.apache.struts2.views.jsp.URLTag.class);
              _jspx_th_s_005furl_005f0.setPageContext(_jspx_page_context);
              _jspx_th_s_005furl_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fform_005f0);
              // /WEB-INF/content/oa/company-file-fileList.jsp(116,15) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
              _jspx_th_s_005furl_005f0.setId("attUrl");
              // /WEB-INF/content/oa/company-file-fileList.jsp(116,15) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
              _jspx_th_s_005furl_005f0.setAction("show");
              // /WEB-INF/content/oa/company-file-fileList.jsp(116,15) name = namespace type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
              _jspx_th_s_005furl_005f0.setNamespace("/app");
              int _jspx_eval_s_005furl_005f0 = _jspx_th_s_005furl_005f0.doStartTag();
              if (_jspx_eval_s_005furl_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                if (_jspx_eval_s_005furl_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_s_005furl_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_s_005furl_005f0.doInitBody();
                }
                do {
                  out.write("\r\n");
                  out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                  //  s:param
                  org.apache.struts2.views.jsp.ParamTag _jspx_th_s_005fparam_005f0 = (org.apache.struts2.views.jsp.ParamTag) _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.get(org.apache.struts2.views.jsp.ParamTag.class);
                  _jspx_th_s_005fparam_005f0.setPageContext(_jspx_page_context);
                  _jspx_th_s_005fparam_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005furl_005f0);
                  // /WEB-INF/content/oa/company-file-fileList.jsp(117,16) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_s_005fparam_005f0.setName("realFileName");
                  int _jspx_eval_s_005fparam_005f0 = _jspx_th_s_005fparam_005f0.doStartTag();
                  if (_jspx_eval_s_005fparam_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_s_005fparam_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_s_005fparam_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_s_005fparam_005f0.doInitBody();
                    }
                    do {
                      out.print(af.getRealFileName() );
                      int evalDoAfterBody = _jspx_th_s_005fparam_005f0.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_s_005fparam_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.popBody();
                    }
                  }
                  if (_jspx_th_s_005fparam_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.reuse(_jspx_th_s_005fparam_005f0);
                    return;
                  }
                  _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.reuse(_jspx_th_s_005fparam_005f0);
                  out.write("\r\n");
                  out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                  //  s:param
                  org.apache.struts2.views.jsp.ParamTag _jspx_th_s_005fparam_005f1 = (org.apache.struts2.views.jsp.ParamTag) _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.get(org.apache.struts2.views.jsp.ParamTag.class);
                  _jspx_th_s_005fparam_005f1.setPageContext(_jspx_page_context);
                  _jspx_th_s_005fparam_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005furl_005f0);
                  // /WEB-INF/content/oa/company-file-fileList.jsp(118,16) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_s_005fparam_005f1.setName("fileName");
                  int _jspx_eval_s_005fparam_005f1 = _jspx_th_s_005fparam_005f1.doStartTag();
                  if (_jspx_eval_s_005fparam_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_s_005fparam_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_s_005fparam_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_s_005fparam_005f1.doInitBody();
                    }
                    do {
                      out.print(af.getFileName() );
                      int evalDoAfterBody = _jspx_th_s_005fparam_005f1.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_s_005fparam_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.popBody();
                    }
                  }
                  if (_jspx_th_s_005fparam_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.reuse(_jspx_th_s_005fparam_005f1);
                    return;
                  }
                  _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.reuse(_jspx_th_s_005fparam_005f1);
                  out.write("\r\n");
                  out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                  //  s:param
                  org.apache.struts2.views.jsp.ParamTag _jspx_th_s_005fparam_005f2 = (org.apache.struts2.views.jsp.ParamTag) _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.get(org.apache.struts2.views.jsp.ParamTag.class);
                  _jspx_th_s_005fparam_005f2.setPageContext(_jspx_page_context);
                  _jspx_th_s_005fparam_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005furl_005f0);
                  // /WEB-INF/content/oa/company-file-fileList.jsp(119,16) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_s_005fparam_005f2.setName("id");
                  int _jspx_eval_s_005fparam_005f2 = _jspx_th_s_005fparam_005f2.doStartTag();
                  if (_jspx_eval_s_005fparam_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    if (_jspx_eval_s_005fparam_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.pushBody();
                      _jspx_th_s_005fparam_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                      _jspx_th_s_005fparam_005f2.doInitBody();
                    }
                    do {
                      out.print(af.getAppAttachFileId() );
                      int evalDoAfterBody = _jspx_th_s_005fparam_005f2.doAfterBody();
                      if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                        break;
                    } while (true);
                    if (_jspx_eval_s_005fparam_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                      out = _jspx_page_context.popBody();
                    }
                  }
                  if (_jspx_th_s_005fparam_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.reuse(_jspx_th_s_005fparam_005f2);
                    return;
                  }
                  _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.reuse(_jspx_th_s_005fparam_005f2);
                  out.write("\r\n");
                  out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                  if (_jspx_meth_s_005fparam_005f3(_jspx_th_s_005furl_005f0, _jspx_page_context))
                    return;
                  out.write("\r\n");
                  out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                  int evalDoAfterBody = _jspx_th_s_005furl_005f0.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_s_005furl_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.popBody();
                }
              }
              if (_jspx_th_s_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _005fjspx_005ftagPool_005fs_005furl_0026_005fnamespace_005fid_005faction.reuse(_jspx_th_s_005furl_005f0);
                return;
              }
              _005fjspx_005ftagPool_005fs_005furl_0026_005fnamespace_005fid_005faction.reuse(_jspx_th_s_005furl_005f0);
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"float: left;max-width: 87%;\"  class=\"ellipsisDiv\" >\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0)\" title=\"");
              out.print(af.getRealFileName() );
              out.write(" \" onclick=\"openAtta('");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${attUrl}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
              out.write('\'');
              out.write(',');
              out.write('\'');
              out.print(af.getRealFileName() );
              out.write("')\">");
              out.print(af.getRealFileName() );
              out.write("</a>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div style=\"float: left; padding-right: 10px;\">\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0)\" title=\"");
              out.print(af.getRealFileName() );
              out.write("\" onclick=\"openAtta('");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${attUrl}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
              out.write('\'');
              out.write(',');
              out.write('\'');
              out.print(af.getRealFileName() );
              out.write("')\"></a>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<br/>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t</li>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t");
}
													
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t</ul>\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t");
}else{
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t\t &nbsp;\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\t");
 }	
											
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t</td>\r\n");
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t\t\t");
              //  security:authorize
              org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f2 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
              _jspx_th_security_005fauthorize_005f2.setPageContext(_jspx_page_context);
              _jspx_th_security_005fauthorize_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fform_005f0);
              // /WEB-INF/content/oa/company-file-fileList.jsp(140,10) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
              _jspx_th_security_005fauthorize_005f2.setIfAnyGranted("A_COMPANYFILE_ADMIN");
              int _jspx_eval_security_005fauthorize_005f2 = _jspx_th_security_005fauthorize_005f2.doStartTag();
              if (_jspx_eval_security_005fauthorize_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                do {
                  out.write("\r\n");
                  out.write("\t\t\t\t\t\t\t\t\t\t<td style=\"text-align: center;margin: 0; padding:0;\">\r\n");
                  out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"btn_edt_file_func\" style=\"float:left;\" title=\"编辑文件\" onclick=\"editFile('");
                  out.print(vo.getFile().getOaFilingFileId() );
                  out.write("');\" >编辑</div>\r\n");
                  out.write("\t\t\t\t\t\t\t\t\t\t\t&nbsp;\r\n");
                  out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"btn_del_file_func\" style=\"float:left;\" title=\"删除文件\" onclick=\"delFile('");
                  out.print(vo.getFile().getOaFilingFileId() );
                  out.write("', '");
                  out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${folderId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                  out.write("');\" >删除</div>\r\n");
                  out.write("\t\t\t\t\t\t\t\t\t\t</td>\r\n");
                  out.write("\t\t\t\t\t\t\t\t\t\t");
                  int evalDoAfterBody = _jspx_th_security_005fauthorize_005f2.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
              }
              if (_jspx_th_security_005fauthorize_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f2);
                return;
              }
              _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.reuse(_jspx_th_security_005fauthorize_005f2);
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\t\t</tr>\r\n");
              out.write("\t\t\t\t\t\t\t\t ");
}
							 }
							
              out.write("\r\n");
              out.write("\t\t\t\t\t\t\t\r\n");
              out.write("\t\t\r\n");
              out.write("\t\t\t\t\t</tbody>\r\n");
              out.write("\t\t\t\t</table>\r\n");
              out.write("\t\t\t</div>\r\n");
              out.write("\t\t");
              int evalDoAfterBody = _jspx_th_s_005fform_005f0.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_s_005fform_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.popBody();
            }
          }
          if (_jspx_th_s_005fform_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fs_005fform_0026_005fmethod_005fid_005faction.reuse(_jspx_th_s_005fform_005f0);
            return;
          }
          _005fjspx_005ftagPool_005fs_005fform_0026_005fmethod_005fid_005faction.reuse(_jspx_th_s_005fform_005f0);
          out.write('\r');
          out.write('\n');
          out.write('	');
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
        return;
      }
      _005fjspx_005ftagPool_005fs_005felse.reuse(_jspx_th_s_005felse_005f2);
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
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
    // /WEB-INF/content/oa/company-file-fileList.jsp(17,1) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f0.setIfAnyGranted("A_COMPANYFILE_ADMIN");
    int _jspx_eval_security_005fauthorize_005f0 = _jspx_th_security_005fauthorize_005f0.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t<input type=\"button\" class=\"fLeft btn_new btn_add_new\" onclick=\"addFile('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${folderId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("');\" value=\"增加文件\" style=\"width:80px;\"/>\r\n");
        out.write("\t");
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
    // /WEB-INF/content/oa/company-file-fileList.jsp(25,1) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f0.setTest("filePage.hasNext");
    int _jspx_eval_s_005fif_005f0 = _jspx_th_s_005fif_005f0.doStartTag();
    if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t<div style=\"float:right;\" class=\"jump_down\" onclick=\"jumpTo('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${folderId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("', '");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${filePage.nextPage}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("');\"></div>\r\n");
        out.write("\t");
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
        out.write("\t \t<div style=\"float:right;\" class=\"jump_down_none\" ></div>\r\n");
        out.write("\t");
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
    // /WEB-INF/content/oa/company-file-fileList.jsp(31,1) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f1.setTest("filePage.hasPre");
    int _jspx_eval_s_005fif_005f1 = _jspx_th_s_005fif_005f1.doStartTag();
    if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t<div style=\"float:right;\" class=\"jump_up\" onclick=\"jumpTo('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${folderId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("', '");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${filePage.prePage}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("');\"></div>\r\n");
        out.write("\t");
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
        out.write("\t\t<div style=\"float:right;\" class=\"jump_up_none\" ></div>\r\n");
        out.write("\t");
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

  private boolean _jspx_meth_s_005fif_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f2 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f2.setParent(null);
    // /WEB-INF/content/oa/company-file-fileList.jsp(46,1) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f2.setTest("filePage.result.size == 0");
    int _jspx_eval_s_005fif_005f2 = _jspx_th_s_005fif_005f2.doStartTag();
    if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t<div class=\"inform_div\">\r\n");
        out.write("\t\t\t请选择子目录 \r\n");
        out.write("\t\t</div>\r\n");
        out.write("\t");
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

  private boolean _jspx_meth_s_005fhidden_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:hidden
    org.apache.struts2.views.jsp.ui.HiddenTag _jspx_th_s_005fhidden_005f0 = (org.apache.struts2.views.jsp.ui.HiddenTag) _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.get(org.apache.struts2.views.jsp.ui.HiddenTag.class);
    _jspx_th_s_005fhidden_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fhidden_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fform_005f0);
    // /WEB-INF/content/oa/company-file-fileList.jsp(53,3) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fhidden_005f0.setName("folderId");
    // /WEB-INF/content/oa/company-file-fileList.jsp(53,3) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fhidden_005f0.setId("curFolderId");
    int _jspx_eval_s_005fhidden_005f0 = _jspx_th_s_005fhidden_005f0.doStartTag();
    if (_jspx_th_s_005fhidden_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.reuse(_jspx_th_s_005fhidden_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fhidden_0026_005fname_005fid_005fnobody.reuse(_jspx_th_s_005fhidden_005f0);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.AuthorizeTag _jspx_th_security_005fauthorize_005f1 = (org.springframework.security.taglibs.authz.AuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005fifAnyGranted.get(org.springframework.security.taglibs.authz.AuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f1.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005fform_005f0);
    // /WEB-INF/content/oa/company-file-fileList.jsp(67,7) name = ifAnyGranted type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f1.setIfAnyGranted("A_COMPANYFILE_ADMIN");
    int _jspx_eval_security_005fauthorize_005f1 = _jspx_th_security_005fauthorize_005f1.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t<td style=\"width:100px;margin: 0; padding-left:5px;\">操作</td>\r\n");
        out.write("\t\t\t\t\t\t\t");
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

  private boolean _jspx_meth_s_005fparam_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005furl_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:param
    org.apache.struts2.views.jsp.ParamTag _jspx_th_s_005fparam_005f3 = (org.apache.struts2.views.jsp.ParamTag) _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.get(org.apache.struts2.views.jsp.ParamTag.class);
    _jspx_th_s_005fparam_005f3.setPageContext(_jspx_page_context);
    _jspx_th_s_005fparam_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005furl_005f0);
    // /WEB-INF/content/oa/company-file-fileList.jsp(120,16) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fparam_005f3.setName("bizModuleCd");
    int _jspx_eval_s_005fparam_005f3 = _jspx_th_s_005fparam_005f3.doStartTag();
    if (_jspx_eval_s_005fparam_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fparam_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fparam_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fparam_005f3.doInitBody();
      }
      do {
        out.write("companyFile");
        int evalDoAfterBody = _jspx_th_s_005fparam_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fparam_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fparam_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.reuse(_jspx_th_s_005fparam_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.reuse(_jspx_th_s_005fparam_005f3);
    return false;
  }
}
