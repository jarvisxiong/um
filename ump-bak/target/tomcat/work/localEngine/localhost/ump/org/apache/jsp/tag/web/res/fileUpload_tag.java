package org.apache.jsp.tag.web.res;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class fileUpload_tag
    extends javax.servlet.jsp.tagext.SimpleTagSupport
    implements org.apache.jasper.runtime.JspSourceDependent {


  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private JspContext jspContext;
  private java.io.Writer _jspx_sout;
  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public void setJspContext(JspContext ctx) {
    super.setJspContext(ctx);
    java.util.ArrayList _jspx_nested = null;
    java.util.ArrayList _jspx_at_begin = null;
    java.util.ArrayList _jspx_at_end = null;
    this.jspContext = new org.apache.jasper.runtime.JspContextWrapper(ctx, _jspx_nested, _jspx_at_begin, _jspx_at_end, null);
  }

  public JspContext getJspContext() {
    return this.jspContext;
  }
  private java.lang.String title;
  private java.lang.String fileId;
  private java.lang.String index;
  private java.lang.String fileValue;
  private java.lang.String bizEntityId;
  private java.lang.String canEdit;
  private java.lang.String subProp;
  private java.lang.String isTemplate;
  private java.lang.String isRequired;
  private java.lang.String isRele2Contract;
  private java.lang.String custAttr;

  public java.lang.String getTitle() {
    return this.title;
  }

  public void setTitle(java.lang.String title) {
    this.title = title;
    jspContext.setAttribute("title", title);
  }

  public java.lang.String getFileId() {
    return this.fileId;
  }

  public void setFileId(java.lang.String fileId) {
    this.fileId = fileId;
    jspContext.setAttribute("fileId", fileId);
  }

  public java.lang.String getIndex() {
    return this.index;
  }

  public void setIndex(java.lang.String index) {
    this.index = index;
    jspContext.setAttribute("index", index);
  }

  public java.lang.String getFileValue() {
    return this.fileValue;
  }

  public void setFileValue(java.lang.String fileValue) {
    this.fileValue = fileValue;
    jspContext.setAttribute("fileValue", fileValue);
  }

  public java.lang.String getBizEntityId() {
    return this.bizEntityId;
  }

  public void setBizEntityId(java.lang.String bizEntityId) {
    this.bizEntityId = bizEntityId;
    jspContext.setAttribute("bizEntityId", bizEntityId);
  }

  public java.lang.String getCanEdit() {
    return this.canEdit;
  }

  public void setCanEdit(java.lang.String canEdit) {
    this.canEdit = canEdit;
    jspContext.setAttribute("canEdit", canEdit);
  }

  public java.lang.String getSubProp() {
    return this.subProp;
  }

  public void setSubProp(java.lang.String subProp) {
    this.subProp = subProp;
    jspContext.setAttribute("subProp", subProp);
  }

  public java.lang.String getIsTemplate() {
    return this.isTemplate;
  }

  public void setIsTemplate(java.lang.String isTemplate) {
    this.isTemplate = isTemplate;
    jspContext.setAttribute("isTemplate", isTemplate);
  }

  public java.lang.String getIsRequired() {
    return this.isRequired;
  }

  public void setIsRequired(java.lang.String isRequired) {
    this.isRequired = isRequired;
    jspContext.setAttribute("isRequired", isRequired);
  }

  public java.lang.String getIsRele2Contract() {
    return this.isRele2Contract;
  }

  public void setIsRele2Contract(java.lang.String isRele2Contract) {
    this.isRele2Contract = isRele2Contract;
    jspContext.setAttribute("isRele2Contract", isRele2Contract);
  }

  public java.lang.String getCustAttr() {
    return this.custAttr;
  }

  public void setCustAttr(java.lang.String custAttr) {
    this.custAttr = custAttr;
    jspContext.setAttribute("custAttr", custAttr);
  }

  public Object getDependants() {
    return _jspx_dependants;
  }

  private void _jspInit(ServletConfig config) {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(config.getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) config.getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void doTag() throws JspException, java.io.IOException {
    PageContext _jspx_page_context = (PageContext)jspContext;
    HttpServletRequest request = (HttpServletRequest) _jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse) _jspx_page_context.getResponse();
    HttpSession session = _jspx_page_context.getSession();
    ServletContext application = _jspx_page_context.getServletContext();
    ServletConfig config = _jspx_page_context.getServletConfig();
    JspWriter out = jspContext.getOut();
    _jspInit(config);
    jspContext.getELContext().putContext(JspContext.class,jspContext);
    if( getTitle() != null ) 
      _jspx_page_context.setAttribute("title", getTitle());
    if( getFileId() != null ) 
      _jspx_page_context.setAttribute("fileId", getFileId());
    if( getIndex() != null ) 
      _jspx_page_context.setAttribute("index", getIndex());
    if( getFileValue() != null ) 
      _jspx_page_context.setAttribute("fileValue", getFileValue());
    if( getBizEntityId() != null ) 
      _jspx_page_context.setAttribute("bizEntityId", getBizEntityId());
    if( getCanEdit() != null ) 
      _jspx_page_context.setAttribute("canEdit", getCanEdit());
    if( getSubProp() != null ) 
      _jspx_page_context.setAttribute("subProp", getSubProp());
    if( getIsTemplate() != null ) 
      _jspx_page_context.setAttribute("isTemplate", getIsTemplate());
    if( getIsRequired() != null ) 
      _jspx_page_context.setAttribute("isRequired", getIsRequired());
    if( getIsRele2Contract() != null ) 
      _jspx_page_context.setAttribute("isRele2Contract", getIsRele2Contract());
    if( getCustAttr() != null ) 
      _jspx_page_context.setAttribute("custAttr", getCustAttr());

    try {
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div id=\"div_");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileId}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write("\" style=\"float: left;width:100%;padding: 5px 0;border-bottom:#BFC4C8 1px solid; \">\r\n");
      out.write("<div id=\"div_vali_");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileId}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write('"');
      out.write(' ');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${custAttr}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write(" class=\"td_title\" style=\"float:left;width:160px; margin: 0 10px;\"  ");
 if (isRequired==null || isRequired.equals("true")){ 
      out.write("validate=\"required\" ");
} 
      out.write(" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileValue}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write('"');
      out.write('>');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${title}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write("</div>\r\n");
 if (canEdit.equals("true")){ 
      out.write("\r\n");
      out.write("<div style=\"float:left; margin: 0 10px;\">\r\n");
      out.write("<input type=\"button\" id=\"btn");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileId}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write("\" value=\"上传附件\" class=\"btn_green_65_20\" style=\"border:none;\" ");
 if (isTemplate==null || isTemplate.equals("false")){ 
      out.write(" onclick=\"showUploadSingleAttachDialog('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${title}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write('\'');
      out.write(',');
      out.write('\'');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bizEntityId}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write("','resCustomFile','");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${index}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileId}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write("',event);\" ");
} 
      out.write(" />\r\n");
 if (index!=null){ 
      out.write("\r\n");
      out.write("<input type=\"hidden\" name=\"templateBean.");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${subProp}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write('[');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${index}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write(']');
      out.write('.');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileId}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write("\" id=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${index}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileId}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write("\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileValue}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write("\"/>\r\n");
}else{ 
      out.write("\r\n");
      out.write("<input type=\"hidden\" name=\"templateBean.");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileId}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write("\" id=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileId}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write("\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileValue}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write("\"/>\r\n");
} 
      out.write("\r\n");
      out.write("</div>\r\n");
} 
      out.write("\r\n");
      out.write("<div id=\"show_");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${index}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileId}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write("\" style=\"float:left;\"  ");
 if (isRele2Contract!=null && isRele2Contract.equals("true")){ 
      out.write(" resattachname=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${title}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write('"');
      out.write(' ');
}
      out.write("></div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("showUploadedFile('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileValue}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write('\'');
      out.write(',');
      out.write('\'');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${index}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileId}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write('\'');
      out.write(',');
      out.write('\'');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${canEdit}", java.lang.String.class, (PageContext)this.getJspContext(), null, false));
      out.write("');\r\n");
      out.write("</script>\r\n");
      out.write("</div>");
    } catch( Throwable t ) {
      if( t instanceof SkipPageException )
          throw (SkipPageException) t;
      if( t instanceof java.io.IOException )
          throw (java.io.IOException) t;
      if( t instanceof IllegalStateException )
          throw (IllegalStateException) t;
      if( t instanceof JspException )
          throw (JspException) t;
      throw new JspException(t);
    } finally {
      jspContext.getELContext().putContext(JspContext.class,super.getJspContext());
      ((org.apache.jasper.runtime.JspContextWrapper) jspContext).syncEndTagFile();
    }
  }
}
