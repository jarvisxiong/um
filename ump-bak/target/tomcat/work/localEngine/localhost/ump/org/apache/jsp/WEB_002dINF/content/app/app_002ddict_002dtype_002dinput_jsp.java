package org.apache.jsp.WEB_002dINF.content.app;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class app_002ddict_002dtype_002dinput_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(2);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/WEB-INF/PowerDesk-tags.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fif_0026_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.release();
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
      response.setContentType("text/html; charset=UTF-8");
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
      out.write("<div class=\"easyui-layout\" fit=\"true\">\t\r\n");
      out.write("\t<div region=\"center\" style=\"padding:5px 20px 5px 5px;+position: relative;overflow-x:hidden;\" border=\"false\">\r\n");
      out.write("\t\t<form id=\"inputForm\" method=\"post\">\r\n");
      out.write("\t\t\t<input type=\"hidden\" name=\"id\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${appDictTypeId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /> \r\n");
      out.write("\t\t\t<table class=\"edit_table\">\r\n");
      out.write("\t\t\t\t<col style=\"width:80px;\"/>\r\n");
      out.write("\t\t\t\t<col />\r\n");
      out.write("\t\t\t\t<col style=\"width:80px;\"/>\r\n");
      out.write("\t\t\t\t<col />\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<!--modify by chenyk 2011-4-15 取消 属性 isExists 否则保存时执行更新数据出现数据not defined问题 -->\r\n");
      out.write("\t\t\t\t\t<td style=\"text-align: right;\">字典代码:</td>\r\n");
      out.write("\t\t\t\t\t<td><input style=\"width:100%;\" name=\"dictTypeCd\" id=\"dictTypeCd\" class=\"easyui-validatebox\" required=\"true\"  type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dictTypeCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></input></td>\r\n");
      out.write("\t\t\t\t\t<td style=\"text-align: right;\">字典名称:</td>\r\n");
      out.write("\t\t\t\t\t<td><input style=\"width:100%;\" name=\"dictTypeName\" class=\"easyui-validatebox\" required=\"true\" validType=\"length[0,50]\" type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dictTypeName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></input></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td style=\"text-align: right;\">显示序号:</td>\r\n");
      out.write("\t\t\t\t\t<td><input style=\"width:100%;\" name=\"dispOrderNo\" type=\"text\"  class=\"easyui-numberbox\" max=\"100000\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dispOrderNo}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></input></td>\r\n");
      out.write("\t\t\t\t\t<td style=\"text-align: right;\">是否默认:</td>\r\n");
      out.write("\t\t\t\t\t<td>");
      if (_jspx_meth_s_005fcheckbox_005f0(_jspx_page_context))
        return;
      out.write("  </td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td style=\"text-align: right;\" valign=\"top\">备注:</td>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"3\"><textarea style=\"width:100%;\" name=\"remark\" class=\"easyui-validatebox\" validType=\"length[0,200]\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${remark}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</textarea> </td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t\t<div>\r\n");
      out.write("\t\t\t<table id=\"tt2\" \r\n");
      out.write("\t\t\t\ttitle=\"字典子表\" singleSelect=\"true\" rownumbers=\"true\"\r\n");
      out.write("\t\t\t\tidField=\"appDictDataId\" url=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/app/app-dict-type!listSub.action?id=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${appDictTypeId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div region=\"south\" border=\"false\" style=\"height:30px;line-height:30px;\">\r\n");
      out.write("\t\t<div class=\"toolbar\">\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_s_005fif_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-save\" onclick=\"saveDict();\">保存</a>\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_s_005fif_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t<a id=\"add\" href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" onclick=\"editrow('');\">新增</a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tvar lastIndex2;\r\n");
      out.write("\tvar isExists=false;\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\tinitAppDictInput();\r\n");
      out.write("\t});\r\n");
      out.write("\tfunction initAppDictInput(){\r\n");
      out.write("\t\t$('#tt2').datagrid({\r\n");
      out.write("\t\t\tcolumns:[[\r\n");
      out.write("\t\t\t\t{field:'dictCd',title:'代码',sortable:true,width:100,editor:{type:'validatebox',options:{required:'true',validType:'length[1,50]'}}},\r\n");
      out.write("\t\t\t\t{field:'dictName',title:'名称',sortable:true,width:100,editor:{type:'validatebox',options:{required:'true',validType:'length[1,50]'}}},\r\n");
      out.write("\t\t\t\t{field:'dispOrderNo',title:'显示序号',align:'right',width:60,editor:{type:'numberbox',options:{max:'10'}}},\r\n");
      out.write("\t\t\t\t{field:'remark',title:'备注',width:100,editor:{type:'validatebox',options:{validType:'length[0,200]'}},\r\n");
      out.write("\t\t\t\t\tformatter:function(value,row,index){\r\n");
      out.write("\t\t\t\t\t\treturn '<div style=\"overflow: hidden; white-space: nowrap; text-overflow:ellipsis;\" title=\"'+value+'\">'+value+'</div>';\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field:'updatedDate',title:'最后更新时间',sortable:true,width:80,\r\n");
      out.write("\t\t\t\t\tformatter:function(value,row,index){\r\n");
      out.write("\t\t\t\t\t\tif(value){\r\n");
      out.write("\t\t\t\t\t\t\treturn '<div style=\"overflow: hidden; white-space: nowrap; text-overflow:ellipsis;\" title=\"'+value+'\">'+value+'</div>';\r\n");
      out.write("\t\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t\treturn '';\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field:'action',title:'操作',width:70,align:'center',\r\n");
      out.write("\t\t\t\t\tformatter:function(value,row,index){\r\n");
      out.write("\t\t\t\t\t\tvar id =row.appDictDataId;\r\n");
      out.write("\t\t\t\t\t\tvar d = '<a href=\"#\" class=\"easyui-linkbutton\" onclick=deleterow2('+index+',\"'+id+'\") >删除</a>';\r\n");
      out.write("\t\t\t\t\t\treturn d;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t]],\r\n");
      out.write("\t\t\ttoolbar:[{\r\n");
      out.write("\t\t\t\ttext:'新增',\r\n");
      out.write("\t\t\t\ticonCls:'icon-add',\r\n");
      out.write("\t\t\t\thandler:function(){\r\n");
      out.write("\t\t\t\t\tvar rowSel=$('#tt2').datagrid('getSelected', lastIndex2);\r\n");
      out.write("\t\t\t\t\t$('#tt2').datagrid('endEdit', lastIndex2);\r\n");
      out.write("\t\t\t\t\tvar index = $('#tt2').datagrid('getData').total;\r\n");
      out.write("\t\t\t\t\tif(!index)index = $('#tt2').datagrid('getRows').length;\r\n");
      out.write("\t\t\t\t\t$('#tt2').datagrid('appendRow',{\r\n");
      out.write("\t\t\t\t\t\tappDictDataId:'',\r\n");
      out.write("\t\t\t\t\t\tdictCd:'',\r\n");
      out.write("\t\t\t\t\t\tdictName:'',\r\n");
      out.write("\t\t\t\t\t\tremark:'',\r\n");
      out.write("\t\t\t\t\t\tsequenceNo:index\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\tlastIndex2 = $('#tt2').datagrid('getRows').length-1;\r\n");
      out.write("\t\t\t\t\t$('#tt2').datagrid('selectRow', lastIndex2);\r\n");
      out.write("\t\t\t\t\t$('#tt2').datagrid('beginEdit', lastIndex2);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}],\r\n");
      out.write("\t\t\tonBeforeLoad:function(){\r\n");
      out.write("\t\t\t\t$(this).datagrid('rejectChanges');\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tonClickRow:function(rowIndex){\r\n");
      out.write("\t\t\t\tif (lastIndex2 != rowIndex){\r\n");
      out.write("\t\t\t\t\t$('#tt2').datagrid('endEdit', lastIndex2);\r\n");
      out.write("\t\t\t\t\t$('#tt2').datagrid('beginEdit', rowIndex);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tlastIndex2 = rowIndex;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$(\"#ff\").validate({\r\n");
      out.write("\t\t\trules: {\r\n");
      out.write("\t\t\t\tdictTypeCd: {\r\n");
      out.write("\t\t\t\t\tremote: \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/app/app-dict-type!isTypeExists.action?oldDictTypeCd=\" + encodeURIComponent('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dictTypeCd}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("')\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tmessages: {\r\n");
      out.write("\t\t\t\tdictTypeCd: {\r\n");
      out.write("\t\t\t\t\tremote: \"已经存在！\"\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\toptions={\r\n");
      out.write("        url:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/app/app-dict-type!save.action',\r\n");
      out.write("        onSubmit: function(){\r\n");
      out.write("        \tvar flag=$('#inputForm').form('validate');\r\n");
      out.write("        \tif (flag){\r\n");
      out.write("        \t\tvar changeRows=$('#tt2').datagrid('getChanges','inserted','updated');\r\n");
      out.write("\t        \tfor(var i=0;i<changeRows.length;i++){\r\n");
      out.write("\t        \t\tvar rowIndex=$('#tt2').datagrid('getRowIndex',changeRows[i]);\r\n");
      out.write("\t        \t\tflag=$('#tt2').datagrid('validateRow',rowIndex);\r\n");
      out.write("\t        \t\tif (!flag){\r\n");
      out.write("\t        \t\t\t$('#tt2').datagrid('selectRow',rowIndex);\r\n");
      out.write("\t        \t\t\t$('#tt2').datagrid('beginEdit', rowIndex);\r\n");
      out.write("\t\t\t\t\t\tbreak;\r\n");
      out.write("\t        \t\t}else{\r\n");
      out.write("\t        \t\t\t$('#tt2').datagrid('endEdit', rowIndex);\r\n");
      out.write("\t        \t\t}\r\n");
      out.write("\t        \t}\r\n");
      out.write("        \t}\r\n");
      out.write("        \tif(flag){\r\n");
      out.write("        \t\t$('#tt2').datagrid('endEdit', lastIndex2);\r\n");
      out.write("\t\t        Convert.setChildren2Form(\"inputForm\",\"tt2\");\r\n");
      out.write("        \t}\r\n");
      out.write("        \treturn flag;\r\n");
      out.write("        },\r\n");
      out.write("        success:function(data){\r\n");
      out.write("        \tif(data!='failure'){\r\n");
      out.write("\t        \t$.messager.alert('Info','保存成功');\r\n");
      out.write("\t        \t\r\n");
      out.write("        \t}else{\r\n");
      out.write("        \t\t$.messager.alert('Info','保存失败');\r\n");
      out.write("        \t}\r\n");
      out.write("        }\r\n");
      out.write("\t};\r\n");
      out.write("\t\r\n");
      out.write("\tfunction deleterow2(index,id){\r\n");
      out.write("\t\t$.messager.confirm('Confirm','Are you sure?',function(r){\r\n");
      out.write("\t\t\tif (r){\r\n");
      out.write("\t\t\t\t$('#tt2').datagrid('deleteRow', index);\r\n");
      out.write("\t\t\t\tvar nextSelect=index>0?index-1:0;\r\n");
      out.write("\t\t\t\t$('#tt2').datagrid('selectRow', nextSelect);\r\n");
      out.write("\t\t\t\tif(id){\r\n");
      out.write("\t\t\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/app/app-dict-type!deleteSub.action\",{subId:id} , function(result) {\r\n");
      out.write("\t\t\t\t\t\t$('#tt2').datagrid('acceptChanges');\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t//$('#tt2').datagrid('acceptChanges');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction deleteType(){\r\n");
      out.write("\t\t$.messager.confirm('Confirm','Are you sure?',function(r){\r\n");
      out.write("\t\t\tif (r){\r\n");
      out.write("\t\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/app/app-dict-type!delete.action\",{id:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${appDictTypeId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("'} , function(result) {\r\n");
      out.write("\t\t\t\t\t$('#w').window(\"close\");\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
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

  private boolean _jspx_meth_s_005fcheckbox_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:checkbox
    org.apache.struts2.views.jsp.ui.CheckboxTag _jspx_th_s_005fcheckbox_005f0 = (org.apache.struts2.views.jsp.ui.CheckboxTag) _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fnobody.get(org.apache.struts2.views.jsp.ui.CheckboxTag.class);
    _jspx_th_s_005fcheckbox_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fcheckbox_005f0.setParent(null);
    // /WEB-INF/content/app/app-dict-type-input.jsp(24,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fcheckbox_005f0.setName("defaultFlg");
    int _jspx_eval_s_005fcheckbox_005f0 = _jspx_th_s_005fcheckbox_005f0.doStartTag();
    if (_jspx_th_s_005fcheckbox_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fcheckbox_0026_005fname_005fnobody.reuse(_jspx_th_s_005fcheckbox_005f0);
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
    // /WEB-INF/content/app/app-dict-type-input.jsp(42,3) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f0.setTest("appDictTypeId != null");
    int _jspx_eval_s_005fif_005f0 = _jspx_th_s_005fif_005f0.doStartTag();
    if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-reload\" onclick=\"editrow('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${appDictTypeId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("');\">刷新</a>\r\n");
        out.write("\t\t\t");
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

  private boolean _jspx_meth_s_005fif_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_005fif_005f1 = (org.apache.struts2.views.jsp.IfTag) _005fjspx_005ftagPool_005fs_005fif_0026_005ftest.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fif_005f1.setParent(null);
    // /WEB-INF/content/app/app-dict-type-input.jsp(46,3) name = test type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fif_005f1.setTest("appDictTypeId != null");
    int _jspx_eval_s_005fif_005f1 = _jspx_th_s_005fif_005f1.doStartTag();
    if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fif_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fif_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fif_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-remove\" onclick=\"deleteType();\">删除</a>\r\n");
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
}
