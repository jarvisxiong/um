package org.apache.jsp.WEB_002dINF.content.bis;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

public final class bis_002dstore_002dcoords_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(4);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/common/nocache.jsp");
    _jspx_dependants.add("/common/global.jsp");
    _jspx_dependants.add("/WEB-INF/PowerDesk-tags.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.release();
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
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
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/common/common.css\"  />\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery/jquery.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/bis/bis.project.select.js\"></script>\r\n");
      out.write("<title>商业管理</title>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\t<fieldset>\r\n");
      out.write("\t\t<legend>导入商铺坐标</legend>\r\n");
      out.write("\t\t<div style=\"margin:10px 20px;line-height: 20px;border:1px solid #ccc\">\r\n");
      out.write("\t\t\t<div style=\"height:20px;line-height:20px;background-color: #8DB2E3;color:#fff;padding-left:10px;font-weight:bold;\">项目单选</div>\r\n");
      out.write("\t\t\t<div style=\"padding-left:10px;height:30px;line-height:30px;\">\r\n");
      out.write("\t\t\t\t选择项目\r\n");
      out.write("\t\t\t\t<input type=\"text\" id=\"select_single\"/>\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" id=\"bisProjectId\"/>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div style=\"padding-left:10px;height:30px;line-height:30px;\">\r\n");
      out.write("\t\t\t\t楼层\r\n");
      out.write("\t\t\t\t<input type=\"text\" id=\"floorNum\"/>\r\n");
      out.write("\t\t\t\t<input type=\"hidden\"/>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div style=\"padding-left:10px;height:30px;line-height:30px;\">\r\n");
      out.write("\t\t\t\t逻辑分区\r\n");
      out.write("\t\t\t\t<input type=\"text\" id=\"area\"/>\r\n");
      out.write("\t\t\t\t<input type=\"hidden\"/>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div style=\"padding-left:10px;height:30px;line-height:30px;\">\r\n");
      out.write("\t\t\t\t文件路径\r\n");
      out.write("\t\t\t\t<input type=\"text\" id=\"fileName\"/>\r\n");
      out.write("\t\t\t\t<input type=\"hidden\"/>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div style=\"padding-left:10px;height:30px;line-height:30px;\">\r\n");
      out.write("\t\t\t\t<input type=\"button\" onclick=\"submit()\" value=\"提交\" />\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div id=\"result\" style=\"display:none;padding-left:10px;height:30px;line-height:30px;\">\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</fieldset>\r\n");
      out.write("\t<fieldset>\r\n");
      out.write("\t\t<legend>导入广告坐标</legend>\r\n");
      out.write("\t\t<div style=\"margin:10px 20px;line-height: 20px;border:1px solid #ccc\">\r\n");
      out.write("\t\t\t<div style=\"height:20px;line-height:20px;background-color: #8DB2E3;color:#fff;padding-left:10px;font-weight:bold;\">初始化广告位平面图</div>\r\n");
      out.write("\t\t\t<div style=\"padding-left:10px;height:30px;line-height:30px;\">\r\n");
      out.write("\t\t\t\t选择项目\r\n");
      out.write("\t\t\t\t<input type=\"text\" id=\"gselect_single\" />\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" id=\"gbisProjectId\"/>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<table>\r\n");
      out.write("\t\t\t<col></col>\r\n");
      out.write("\t\t\t<col></col>\r\n");
      out.write("\t\t\t<col></col>\r\n");
      out.write("\t\t\t<col></col>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>平面图说明</td>\r\n");
      out.write("\t\t\t\t<td>小图</td>\r\n");
      out.write("\t\t\t\t<td>大图</td>\r\n");
      out.write("\t\t\t\t<td>类别编号</td>\r\n");
      out.write("\t\t\t\t<td>类别名称</td>\r\n");
      out.write("\t\t\t\t<td>文件路径</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" id=\"remark\"/></td>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" id=\"floorUrl\"/></td>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" id=\"bigPicUrl\"/></td>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" name=\"subFloorType\" id=\"subFloorType\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" name=\"subFloorTypeName\" id=\"subFloorTypeName\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" id=\"gfileName\"/>\r\n");
      out.write("\t\t\t\t<input type=\"hidden\"/></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t<div style=\"padding-left:10px;height:30px;line-height:30px;\">\r\n");
      out.write("\t\t\t\t<input type=\"button\" style=\"height:30px\"  onclick=\"gSaveFloorsubmit()\" value=\"创建楼层以及更新多径广告坐标\" />\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<br></br><br/>\r\n");
      out.write("\t\t\t<div style=\"padding-left:10px;height:30px;line-height:30px;\">\r\n");
      out.write("\t\t\t\t楼层\r\n");
      out.write("\t\t\t\t");
      if (_jspx_meth_s_005fselect_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div style=\"padding-left:10px;height:30px;line-height:30px;\">\r\n");
      out.write("\t\t\t\t<input type=\"button\" style=\"height:30px\" onclick=\"gsubmit()\" value=\"更新多径广告坐标\" />\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div id=\"gresult\" style=\"display:none;padding-left:10px;height:30px;line-height:30px;\">\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<div style=\"height:20px;line-height:20px;background-color: #8DB2E3;color:#fff;padding-left:10px;font-weight:bold;\">初始化广告位平面图\r\n");
      out.write("\t\t\t选择项目\r\n");
      out.write("\t\t\t\t<input type=\"text\" id=\"gselect_single_g\" />\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div id=\"bisFloorPanel\"></div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</fieldset>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\t\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("$(function(){\r\n");
      out.write("\t$('#select_single').onSelect({\r\n");
      out.write("\t\tmuti:false,\r\n");
      out.write("\t});\r\n");
      out.write("\t$('#gselect_single').onSelect({\r\n");
      out.write("\t\tmuti:false\r\n");
      out.write("\t\t\r\n");
      out.write("\t});\r\n");
      out.write("\t$('#gselect_single_g').onSelect({\r\n");
      out.write("\t\tmuti:false,\r\n");
      out.write("\t\tcallback:function(result){\r\n");
      out.write("\t\t\tvar url = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-store-coords!loadFloors.action';\r\n");
      out.write("\t\t\tvar bisProjectId= result.bisProjectId;\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t$.post(url,{bisProjectId:bisProjectId},function(result){$('#bisFloorPanel').html(result);\t$('#ggBisProjectId').val(bisProjectId);});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("function updateFloor(bisFloorId){\r\n");
      out.write("\tvar url = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-store-coords!updateFloor.action';\r\n");
      out.write("\tvar remark = $('#remark').val();\r\n");
      out.write("\tvar parentId = $('#parentId').val();\r\n");
      out.write("\tvar subFloorType = $('#subFloorType').val();\r\n");
      out.write("\t$.post(url,{bisFloorId:bisFloorId,remark:remark,subFloorType:subFloorType,parentId:parentId},function(result){\r\n");
      out.write("\t\talert(result);\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("function toGGPic(bisFloorId){\r\n");
      out.write("\tvar bisProjectId = $('#ggBisProjectId').val();\r\n");
      out.write("\tvar url = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-tenant!loadAdMain.action?bisFloorId='+bisFloorId+'&bisProjectId='+bisProjectId;\r\n");
      out.write("\tif(parent.TabUtils==null)\r\n");
      out.write("\t\t window.open(url);\r\n");
      out.write("\t\t else\r\n");
      out.write("\t\t parent.TabUtils.newTab(\"bisTenant\",\"商业租户\",url,true); \r\n");
      out.write("}\r\n");
      out.write("function submit(){\r\n");
      out.write("\tvar bisProjectId = $('#bisProjectId').val();\r\n");
      out.write("\tvar floorNum = $('#floorNum').val();\r\n");
      out.write("\tvar fileName = $('#fileName').val();\r\n");
      out.write("\tvar area = $('#area').val();\r\n");
      out.write("\tvar data = {bisProjectId:bisProjectId,floorNum:floorNum,fileName:fileName,area:area};\r\n");
      out.write("\t$.post('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-store-coords!importExl.action',data,function(result){\r\n");
      out.write("\t\t$('#result').html(result).show();\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("function gSaveFloorsubmit(){\r\n");
      out.write("\tvar bisProjectId = $('#gbisProjectId').val();\r\n");
      out.write("\tvar fileName = $('#gfileName').val();\r\n");
      out.write("\tvar remark = $('#remark').val();\r\n");
      out.write("\tvar floorUrl = $('#floorUrl').val();\r\n");
      out.write("\tvar bigPicUrl = $('#bigPicUrl').val();\r\n");
      out.write("\tvar subFloorType = $('#subFloorType').val();\r\n");
      out.write("\tvar subFloorTypeName = $('#subFloorTypeName').val();\r\n");
      out.write("\tvar area = $('#area').val();\r\n");
      out.write("\tvar data = {bisProjectId:bisProjectId,remark:remark,fileName:fileName,floorUrl:floorUrl,bigPicUrl:bigPicUrl,subFloorType:subFloorType,subFloorTypeName:subFloorTypeName};\r\n");
      out.write("\t$.post('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-store-coords!importGuangGaoFloor.action',data,function(result){\r\n");
      out.write("\t\t$('#gresult').html(result).show();\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("function gsubmit(){\r\n");
      out.write("\tvar bisProjectId = $('#gbisProjectId').val();\r\n");
      out.write("\tvar fileName = $('#gfileName').val();\r\n");
      out.write("\tvar bisFloorId = $('#bisFloorId').val();\r\n");
      out.write("\tvar area = $('#area').val();\r\n");
      out.write("\tvar data = {bisProjectId:bisProjectId,fileName:fileName,bisFloorId:bisFloorId};\r\n");
      out.write("\t$.post('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/bis/bis-store-coords!importGuangGaoExl.action',data,function(result){\r\n");
      out.write("\t\t$('#gresult').html(result).show();\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
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

  private boolean _jspx_meth_s_005fselect_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:select
    org.apache.struts2.views.jsp.ui.SelectTag _jspx_th_s_005fselect_005f0 = (org.apache.struts2.views.jsp.ui.SelectTag) _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.get(org.apache.struts2.views.jsp.ui.SelectTag.class);
    _jspx_th_s_005fselect_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fselect_005f0.setParent(null);
    // /WEB-INF/content/bis/bis-store-coords.jsp(89,4) name = list type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setList("mapFloor");
    // /WEB-INF/content/bis/bis-store-coords.jsp(89,4) name = listKey type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setListKey("key");
    // /WEB-INF/content/bis/bis-store-coords.jsp(89,4) name = listValue type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setListValue("value");
    // /WEB-INF/content/bis/bis-store-coords.jsp(89,4) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setName("mapFloor");
    // /WEB-INF/content/bis/bis-store-coords.jsp(89,4) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fselect_005f0.setId("bisFloorId");
    int _jspx_eval_s_005fselect_005f0 = _jspx_th_s_005fselect_005f0.doStartTag();
    if (_jspx_th_s_005fselect_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.reuse(_jspx_th_s_005fselect_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fselect_0026_005fname_005flistValue_005flistKey_005flist_005fid_005fnobody.reuse(_jspx_th_s_005fselect_005f0);
    return false;
  }
}
