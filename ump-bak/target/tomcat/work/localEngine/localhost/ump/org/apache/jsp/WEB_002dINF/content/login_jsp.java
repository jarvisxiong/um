package org.apache.jsp.WEB_002dINF.content;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.LoginUtil;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fpassword_0026_005fvalue_005fsize_005fonkeypress_005fname_005fid_005fcssStyle_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005furl_0026_005fnamespace_005fincludeParams_005fid_005fescapeAmp_005faction;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fparam_0026_005fname;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fpassword_0026_005fvalue_005fsize_005fonkeypress_005fname_005fid_005fcssStyle_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005furl_0026_005fnamespace_005fincludeParams_005fid_005fescapeAmp_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fparam_0026_005fname = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fpassword_0026_005fvalue_005fsize_005fonkeypress_005fname_005fid_005fcssStyle_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005furl_0026_005fnamespace_005fincludeParams_005fid_005fescapeAmp_005faction.release();
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
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<title>宝龙管理平台</title>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"/>\r\n");
      out.write("<meta http-equiv=\"Cache-Control\" content=\"no-store\"/>\r\n");
      out.write("<meta http-equiv=\"Pragma\" content=\"no-cache\"/>\r\n");
      out.write("<meta http-equiv=\"Expires\" content=\"0\"/>\r\n");
      out.write("<META http-equiv=Page-Enter content=blendTrans(Duration=0.5)>\r\n");
      out.write("<META http-equiv=Page-Exit content=blendTrans(Duration=0.5)>\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=8\" />");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/desk/thickbox.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/prompt/skin/dmm-green/ymPrompt.css\" id='skin' />\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/cookie.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/desk/MaskLayer.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/prompt/ymPrompt.js\"></script>\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("function getJREVersions() {\r\n");
      out.write("\tfunction _64(_65) {\r\n");
      out.write("\t\tvar _66 = \"JavaWebStart.isInstalled.\" + _65 + \".0\";\r\n");
      out.write("\t\tif (!ActiveXObject) {\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\ttry {\r\n");
      out.write("\t\t\treturn (new ActiveXObject(_66) != null);\r\n");
      out.write("\t\t} catch (exception) {\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction _67(v1, v2) {\r\n");
      out.write("\t\tvar v1a = v1.split(\".\");\r\n");
      out.write("\t\tvar v2a = v2.split(\".\");\r\n");
      out.write("\t\tvar _68 = v1a.length;\r\n");
      out.write("\t\tif (v2a.length > _68) {\r\n");
      out.write("\t\t\t_68 = v2a.length;\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\tfor ( var i = 0; i < _68; i++) {\r\n");
      out.write("\t\t\tif (v1a.length == i) {\r\n");
      out.write("\t\t\t\treturn v2;\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\tif (v2a.length == i) {\r\n");
      out.write("\t\t\t\t\treturn v1;\r\n");
      out.write("\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\tif (v1a[i] < v2a[i]) {\r\n");
      out.write("\t\t\t\t\t\treturn v2;\r\n");
      out.write("\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\tif (v1a[i] > v2a[i]) {\r\n");
      out.write("\t\t\t\t\t\t\treturn v1;\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn v1;\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tif (!navigator.javaEnabled()) {\r\n");
      out.write("\t\treturn \"none\";\r\n");
      out.write("\t}\r\n");
      out.write("\tvar _69 = \"undefined\";\r\n");
      out.write("\tif (navigator.userAgent.toLowerCase().indexOf(\"msie\") != -1) {\r\n");
      out.write("\t\tif (_64(\"1.8.0\")) {\r\n");
      out.write("\t\t\t_69 = \"1.8.0\";\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\tif (_64(\"1.7.0\")) {\r\n");
      out.write("\t\t\t\t_69 = \"1.7.0\";\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\tif (_64(\"1.6.0\")) {\r\n");
      out.write("\t\t\t\t\t_69 = \"1.6.0\";\r\n");
      out.write("\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\tif (_64(\"1.5.0\")) {\r\n");
      out.write("\t\t\t\t\t\t_69 = \"1.5.0\";\r\n");
      out.write("\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\tif (_64(\"1.4.2\")) {\r\n");
      out.write("\t\t\t\t\t\t\t_69 = \"1.4.2\";\r\n");
      out.write("\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\t_69 = \"none\";\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t} else {\r\n");
      out.write("\t\tif (navigator.mimeTypes) {\r\n");
      out.write("\t\t\tfor ( var i = 0; i < navigator.mimeTypes.length; i++) {\r\n");
      out.write("\t\t\t\ts = navigator.mimeTypes[i].type;\r\n");
      out.write("\t\t\t\tvar p = /^application\\/x-java-applet\\x3Bversion=([0-9\\.]+)/;\r\n");
      out.write("\t\t\t\tvar m = s.match(p);\r\n");
      out.write("\t\t\t\tif (m != null) {\r\n");
      out.write("\t\t\t\t\tif (_69 != \"undefined\") {\r\n");
      out.write("\t\t\t\t\t\t_69 = _67(_69, RegExp.$1);\r\n");
      out.write("\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t_69 = RegExp.$1;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\treturn _69;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("var rand = Math.random()*10000;\r\n");
      out.write("// 系统登录\r\n");
      out.write("function user_login(){\r\n");
      out.write("\t$(\"#login_name\").val($.trim($(\"#login_name\").val()));\r\n");
      out.write("\t$(\"#password\").val($.trim($(\"#password\").val()));\r\n");
      out.write("\tvar str = rand.toString();\r\n");
      out.write("\tvar len = str.length;\r\n");
      out.write("\tvar random;\r\n");
      out.write("\t//判断网段\r\n");
      out.write("\tvar network = $(':input[name=\"network\"]:checked').val();\r\n");
      out.write("\trandom=str.substring(0,str.indexOf(\".\"));\r\n");
      out.write("\tswitch(random.length)\r\n");
      out.write("\t{\r\n");
      out.write("\t  case 1:random=\"000\"+random;break;\r\n");
      out.write("\t  case 2:random=\"00\"+random;break;\r\n");
      out.write("\t  case 3:random=\"0\"+random;break;\r\n");
      out.write("\t  default:random=random.substring(0,4);break;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tif(checkData()){\r\n");
      out.write("\t\t$(\"#loginLoadingDiv\").show();\r\n");
      out.write("\t\t$(\"#loadingMask\").show();\r\n");
      out.write("\t\tvar login_nameValue = $(\"#login_name\").val();   \r\n");
      out.write("        var passwordValue = $(\"#password\").val();   \r\n");
      out.write("        //服务器验证（模拟）       \r\n");
      out.write("        if( $(\"#saveCookie\")[0].checked){     \r\n");
      out.write("            setCookie(\"login_name\",$(\"#login_name\").val(),24,\"/\");   \r\n");
      out.write("        }\r\n");
      out.write("        //验证是否选择网段\r\n");
      out.write("        setCookie(\"network\",network,24,\"/\");   \r\n");
      out.write("\t\tswitch(network){\r\n");
      out.write("\t\tcase \"telecom\":$(\"#frm\").attr(\"action\",\"http://pd.powerlong.com/j_spring_security_check\");break;\r\n");
      out.write("\t\tcase \"netcom\":$(\"#frm\").attr(\"action\",\"http://wt.powerlong.com/j_spring_security_check\");break;\r\n");
      out.write("\t\tcase \"backup\":$(\"#frm\").attr(\"action\",\"http://180.169.33.85/j_spring_security_check\");break;\r\n");
      out.write("\t\tdefault:break;\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("        setCookie(\"password\",\"\",24,\"/\"); \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        \r\n");
      out.write("\t\t$(\"#frm\").submit();\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("// 取消登录\r\n");
      out.write("function user_cancel(){\r\n");
      out.write("\tdocument.location = \"login.jsp\";\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//获得验证码\r\n");
      out.write("function checkcode(){\r\n");
      out.write("\tdocument.write(\"<img style='margin-left: -5px;margin-top: -1px;' border=0 src='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/image.jsp?Rand=\" + rand + \"' align='absmiddle'>\");\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//验证数据合法性\r\n");
      out.write("function checkData(){\r\n");
      out.write("\tif($.trim($(\"#login_name\").val()) == \"\"){\r\n");
      out.write("\t\talert(\"登录名不能为空或者全部为空格，请重新填写!\");\r\n");
      out.write("\t\t$(\"#login_name\").focus();\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\tif($.trim($(\"#password\").val())  == \"\"){\r\n");
      out.write("\t\talert(\"密码不能为空或者全部为空格，请重新填写!\");\r\n");
      out.write("\t\t$(\"#password\").focus();\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function onkeypress_login(id,e){\r\n");
      out.write("\tvar keyCode;\r\n");
      out.write("\tif($.browser.msie){\r\n");
      out.write("\t\tkeyCode = event.keyCode;\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tkeyCode = e.which;\r\n");
      out.write("\t}\r\n");
      out.write("\tif(keyCode==13){\r\n");
      out.write("\t\tif(id==\"login_name\"){\r\n");
      out.write("\t\t\t$(\"#password\").focus();\r\n");
      out.write("\t\t}else if(id==\"password\"){\r\n");
      out.write("\t\t\t//$(\"#checkcode\").focus();\r\n");
      out.write("\t\t\tuser_login();\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tuser_login();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function onCancel(){\r\n");
      out.write("\tdocument.getElementById(\"login_name\").value=\"\";\r\n");
      out.write("\tdocument.getElementById(\"password\").value=\"\";\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\tfunction verifyCorrectBrowser() {\r\n");
      out.write("\t\tif (navigator.appName == \"Microsoft Internet Explorer\")\r\n");
      out.write("\t\t\tif (navigator.appVersion.indexOf(\"MSIE 6.\") >= 0)\r\n");
      out.write("\t\t\t\treturn (true);\r\n");
      out.write("\t\t\telse\r\n");
      out.write("\t\t\t\treturn (false);\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction doOnload() {\r\n");
      out.write("\t\ttry{\r\n");
      out.write("\t\t\tdocument.getElementById(\"login_name\").value = getCookieValue(\"login_name\");\r\n");
      out.write("\r\n");
      out.write("\t\t\t//选中cookie中默认网段\r\n");
      out.write("\t\t\tvar n = getCookieValue(\"network\");\r\n");
      out.write("\t\t\t$(\"#\"+n).attr(\"checked\",\"true\");\r\n");
      out.write("\t\t\t$(\"#\"+n).siblings().attr('checked',false);\t\t\t\r\n");
      out.write("\t\t\tvar curName=$(\"#\"+n).attr(\"id\");\r\n");
      out.write("\t\t\t$(\"#\"+n).parents(\".chkGroup\").find(\".group\").each(function(i){\r\n");
      out.write("\t\t\t\tvar tmpName=this.id;\r\n");
      out.write("\t\t\t\tif (tmpName!=curName){\r\n");
      out.write("\t\t\t\t\t$(this).attr('checked',false);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}catch(e){}\r\n");
      out.write("\t\t_PAGEWIDTH = document.documentElement.clientWidth;\r\n");
      out.write("\t\t_PAGEHEIGHT = document.documentElement.clientHeight;\r\n");
      out.write("\t\tvar login_div_width = 856;\r\n");
      out.write("\t\tvar login_div_height = 628;//http://localhost:8080/PowerDesk/login.action\r\n");
      out.write("\t\tvar login_div_left = 0;\r\n");
      out.write("\t\tvar login_div_top = 0;\r\n");
      out.write("\t\tlogin_div_left = (_PAGEWIDTH - login_div_width) / 2;\r\n");
      out.write("\t\tdocument.getElementById(\"login_div\").style.left = login_div_left + \"px\";\r\n");
      out.write("\t\tdocument.getElementById(\"login_div\").style.visibility = \"visible\";\r\n");
      out.write("\t\t/*\r\n");
      out.write("\t\ttry{\r\n");
      out.write("\t\t\tvar ua = navigator.userAgent.toLowerCase();  \r\n");
      out.write("\t\t\tvar is360se = ua.indexOf(\"360se\") > -1 ;\t//判断是否是360浏览器\r\n");
      out.write("\t\t\tif(is360se){\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"login_name_div\").style.left = \"117px\";\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"password_div\").style.left = \"117px\";\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"saveCookie_div\").style.left = \"112px\";\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"saveCookie_div\").style.top = \"292px\";\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"saveCookie_div\").style.left = \"111px\";\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"saveCookie_div\").style.top = \"293px\";\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}catch(e){}\r\n");
      out.write("\t\t*/\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction ieDownload(){\r\n");
      out.write("\t\tymPrompt.win({message:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/common/IEdownload.jsp',width:300,height:220,title:'支持浏览器下载',iframe:true});\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction submit(){\r\n");
      out.write("\t\t$(\"#idNo\").val($(\"#idNoValid\").val());\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction idNoValidate() {\r\n");
      out.write("\t\t");
if(LoginUtil.isFirstlogin(request)){ 
      out.write("\r\n");
      out.write("\t\tymPrompt.win( {\r\n");
      out.write("\t\t\tmessage :\"<div><br/><form action='j_spring_security_check' method='post'>请输入您的身份证号码：<br/><br/><input type='hidden' value='");
      out.print(LoginUtil.getUiid(request));
      out.write("' name='j_username'/><input type='hidden' value='");
      out.print(LoginUtil.getPwd(request));
      out.write("' name='j_password'/><input type='text' id='idNoValid' name='idNoValid'/><input type='submit' value='提交' /></form></div>\",\r\n");
      out.write("\t\t\twidth : 200,\r\n");
      out.write("\t\t\theight : 120,\r\n");
      out.write("\t\t\twinPos:[550,250],\r\n");
      out.write("\t\t\ttitle : '身份证校验',\r\n");
      out.write("\t\t\tcloseBtn: false\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t");
}
      out.write("\r\n");
      out.write("\t}\r\n");
      out.write("\t  function getApplet(){\r\n");
      out.write("          if (window.navigator.userAgent.indexOf(\"MSIE\")>=1) {\r\n");
      out.write("              _6666.method();\r\n");
      out.write("          }\r\n");
      out.write("          else\r\n");
      out.write("              document._5555.method();\r\n");
      out.write("      }\r\n");
      out.write("      function foucusInput(){\r\n");
      out.write("\t\tvar $name =$('#login_name'); \r\n");
      out.write("\t\tvar $password =$('#password'); \r\n");
      out.write("\t\tif($.trim($name.val()) == ''){\r\n");
      out.write("\t\t\t$name.focus();\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\t$password.focus();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("      /*\r\n");
      out.write("\t$(function (){\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tif (getJREVersions()!='none'){\r\n");
      out.write("\t\t\tTB_showMaskLayer('正在获取MAC地址');\r\n");
      out.write("\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/loadApplet.action\",function(result) {\r\n");
      out.write("\t\t\t\t$(\"#divJre\").html(result);\r\n");
      out.write("\t\t\t\tTB_removeMaskLayer();\r\n");
      out.write("\t\t\t});\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\t*/\r\n");
      out.write("\tfunction doSaveCookieDiv(){\r\n");
      out.write("\t\tvar myHtml = $(\"#saveCookieDiv\").html();\r\n");
      out.write("\t\tif(null==myHtml || \"\"==myHtml || \"undefined\"==myHtml){\r\n");
      out.write("\t\t\t$(\"#saveCookieDiv\").html('√');\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\t$(\"#saveCookieDiv\").html('');\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$(\"#saveCookie\").click();\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction addClickAction(){\r\n");
      out.write("\t\tvar chks;\r\n");
      out.write("\t\tchks=$(\".group\");\r\n");
      out.write("\t\tchks.click(function(){\r\n");
      out.write("\t\t\tif($(this).attr('checked')){\r\n");
      out.write("\t\t\t\t$(this).siblings().attr('checked',false);\r\n");
      out.write("\t\t\t\tvar curName=$(this).attr(\"id\");\r\n");
      out.write("\t\t\t\t$(this).parents(\".chkGroup\").find(\".group\").each(function(i){\r\n");
      out.write("\t\t\t\t\tvar tmpName=this.id;\r\n");
      out.write("\t\t\t\t\tif (tmpName!=curName){\r\n");
      out.write("\t\t\t\t\t\t$(this).attr('checked',false);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t$(function() {\r\n");
      out.write("\t\t//if (!verifyCorrectBrowser()){\r\n");
      out.write("\t\t//\t$(\"#ieDownlond\").show();\r\n");
      out.write("\t\t//}\r\n");
      out.write("\t\tdoOnload();\r\n");
      out.write("\t\tidNoValidate();\r\n");
      out.write("\t\tfoucusInput();\r\n");
      out.write("\t\taddClickAction();\r\n");
      out.write("\t\t$(\"#bigPicImg\").attr(\"src\",\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/login3/pic5.png\");\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("<!--\r\n");
      out.write("html{\r\n");
      out.write("\tfont: 12px Tahoma, Helvetica, Arial, \"\\5b8b\\4f53\", sans-serif;\r\n");
      out.write("\tscrollbar-face-color:#C0C0C0;\r\n");
      out.write("\tscrollbar-highlight-color:#FFFFFF;\r\n");
      out.write("\tscrollbar-3dlight-color:#C0C0C0;\r\n");
      out.write("\tscrollbar-darkshadow-color:#737373;\r\n");
      out.write("\tscrollbar-shadow-color:#a6a6a6;\r\n");
      out.write("\tscrollbar-arrow-color:#696969;\r\n");
      out.write("\tscrollbar-track-color:#e0e0e0;\r\n");
      out.write("}\r\n");
      out.write("body{\r\n");
      out.write("\tbackground-color:#e0e0e0;\r\n");
      out.write("\tcolor:#0167a2;\r\n");
      out.write("\tmargin:0px;\r\n");
      out.write("\tpadding:0px;\r\n");
      out.write("\tfont-size:12px;\r\n");
      out.write("\toverflow-x:hidden;\r\n");
      out.write("\toverflow-y:hidden;\r\n");
      out.write("}\r\n");
      out.write(".loginDiv{ \r\n");
      out.write("\tbackground-color:#2c8bef;\r\n");
      out.write("}\r\n");
      out.write(".loginDiv:hover{\r\n");
      out.write("\tbackground-color:#277cd7;\r\n");
      out.write("}\r\n");
      out.write("-->\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<!--收集mac地址-->\r\n");
      out.write("<div id=\"divJre\" style=\"width:0;height:0;\">\r\n");
      out.write("</div>\r\n");
      out.write("<!--按钮预载入-->\r\n");
      out.write("<div style=\"display:none;\">\r\n");
      out.write("<button id=\"btn_login1\" class=\"login_in_normal\"></button>\r\n");
      out.write("<button id=\"btn_login2\" class=\"login_in_press\"></button>\r\n");
      out.write("<button id=\"login_button_ie8_normal\" class=\"login_button_ie8_normal\"></button>\r\n");
      out.write("<button id=\"login_button_ie8_hover\" class=\"login_button_ie8_hover\"></button>\r\n");
      out.write("<button id=\"login_button_help_normal\" class=\"login_button_help_normal\"></button>\r\n");
      out.write("<button id=\"login_button_help_hover\" class=\"login_button_help_hover\"></button>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- <div style=\"background-color:#2d8bef; width:100%; height:1px; position:absolute; top:91px; z-index:100;\"></div> -->\r\n");
      out.write("<div id=\"login_div\" style=\"visibility:hidden;  width:855px; height:628px; position:absolute; left:0px; top:0px;\">\r\n");
      out.write("\t<div style=\"height:111px;\">\r\n");
      out.write("\t\t<div style=\"float:left; margin-left:58px; margin-top:43px; width:151px; height:56px;\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/login3/logo.png\" width=\"151px\" height=\"56px\"/></div>\r\n");
      out.write("\t\t<div style=\"float:right; margin-right:36px; margin-top:75px;\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/resources/images/login3/powerdesk3.png\"/></div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div style=\"float:left; width:323px; height:400px;\">\r\n");
      out.write("\t\t<div style=\"padding-left:58px; font-size:14px;\">\r\n");
      out.write("\t\t");
if(!LoginUtil.isFirstlogin(request)){ 
      out.write("\r\n");
      out.write("\t\t\t<form id=\"frm\" action=\"");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write("\" method=\"post\">\r\n");
      out.write("\t\t\t\t<div id=\"login_name_div\" style=\"height:26px; line-height:26px;\">\r\n");
      out.write("\t\t\t\t\t用户名：&nbsp;\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"login_name\" name=\"j_username\" style=\"width:189px; border: 1px solid #909090;\" value='");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("' onkeypress=\"onkeypress_login('login_name',event)\" onfocus=\"this.select();\"/>\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" id=\"idNo\" />\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div id=\"password_div\" style=\"height:26px; line-height:26px; margin-top:8px;\">\r\n");
      out.write("\t\t\t\t\t密<span style=\"visibility:hidden;\">一</span>码：&nbsp;\r\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_s_005fpassword_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div id=\"saveCookie_div\" style=\"height:26px; line-height:26px; margin-top:8px;\">\r\n");
      out.write("\t\t\t\t\t<div style=\"float:left;\"><span style=\"visibility:hidden;\">密一码：&nbsp;</span></div>\r\n");
      out.write("\t\t\t\t\t<input id=\"saveCookie\" type=\"checkbox\" style=\"display:none;\"/>\r\n");
      out.write("\t\t\t\t\t<div id=\"saveCookieDiv\" style=\"float:left; text-align:center; display:block; background-color:#f56518; width:26px; height:26px; margin-left:3px; border:none; color:#fff; cursor:pointer; font-weight:bolder; font-size:18px;\" onclick=\"doSaveCookieDiv();\"></div>\r\n");
      out.write("\t\t\t\t\t<div style=\"float:left; text-align:center; cursor:pointer; font-size:12px;\"><label for=\"saveCookie\">&nbsp;&nbsp;记住用户名&nbsp;&nbsp;</label></div>\r\n");
      out.write("\t\t\t\t\t<div class=\"loginDiv\" style=\"float:left; text-align:center; display:block; width:94px; height:26px; border:none; color:#fff; cursor:pointer;\" onclick=\"user_login();\">登&nbsp;&nbsp;&nbsp;&nbsp;录</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"chkGroup\" style=\"margin-top:24px; font-size:12px;\" title=\"如果您的网络速度不理想，可以试着换一个网络链路\">\r\n");
      out.write("\t    \t\t\t选择网段：\r\n");
      out.write("\t\t    \t\t<span><input type=\"checkbox\" class=\"group\" id=\"telecom\" name=\"network\" value=\"telecom\" /></span>\r\n");
      out.write("\t\t    \t\t<span>&nbsp;<label for=\"telecom\">电信</label>&nbsp;&nbsp;</span>\r\n");
      out.write("\t\t    \t\t<span><input type=\"checkbox\" class=\"group\" id=\"netcom\" name=\"network\" value=\"netcom\"/></span>\r\n");
      out.write("\t\t    \t\t<span>&nbsp;<label for=\"netcom\">网通</label>&nbsp;&nbsp;</span>\r\n");
      out.write("\t\t    \t\t<span><input type=\"checkbox\" class=\"group\" id=\"backup\" name=\"network\" value=\"backup\"/></span>\r\n");
      out.write("\t\t    \t\t<span>&nbsp;<label for=\"backup\">备用</label></span>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div style=\"clear:both; margin-top:8px; line-height:18px; font-size:12px;\">本系统需要运行在IE8以上浏览器，如果您是首次登录系统请确认您的浏览器版本。<a href=\"#\" onclick=\"ieDownload();\">浏览器下载</a>。</div>\r\n");
      out.write("\t\t\t\t");
      if (_jspx_meth_s_005furl_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t<div style=\"height:26px; text-align:center; line-height:26px; margin-top:24px; display:none;\" id=\"loginLoadingDiv\">\r\n");
      out.write("\t\t\t\t\t<span style=\"font-size:14px;font-weight:bold;\">正在登录,请稍候...</span>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div style=\"height:26px; text-align:center; line-height:26px; margin-top:8px; color:red;\">\r\n");
      out.write("\t\t\t      \t");
if(LoginUtil.getErrorInfo(request)!=null){ 
      out.write("\r\n");
      out.write("\t\t\t      \t\t");
      out.print(LoginUtil.getErrorInfo(request));
      out.write("\r\n");
      out.write("\t\t\t      \t");
}
      out.write("\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t");
}
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div style=\"float:left; width:480px; height:350px; margin-left:16px;\">\r\n");
      out.write("\t\t<div style=\"clear:both; width:100%; position:relative;\">\r\n");
      out.write("\t\t\t<img id=\"bigPicImg\" width=\"480px\" height=\"294px\"/>\r\n");
      out.write("\t\t\t<div style=\"position:absolute; display:block; width:480px; height:180px; left:0px; top:0px; cursor:pointer;\" onclick=\"javascript:window.open('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/app/download.action?fileName=PD3.0.ppsx&amp;realFileName=PD3.0_intro.ppsx&amp;bizModuleCd=public');\" title=\"点击下载PD3.0新特性说明\">\r\n");
      out.write("\t\t\t\t\t<font style=\"font-size:48px;\">\r\n");
      out.write("\t\t\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t</font>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div style=\"clear:both; width:100%; margin-top:16px;\">\r\n");
      out.write("\t\t\t<div style=\"margin-top:5px;\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/app/download.action?fileName=PD3.0.ppsx&amp;realFileName=PD3.0_intro.ppsx&amp;bizModuleCd=public\" target=\"_blank\">点击下载PowerDesk3.0版新特性说明</a></div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"loadingMask\" style=\"position: absolute;top:0;left:0;z-index: 99;height:100%;width:100%;cursor: wait;display: none;\"></div>\r\n");
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

  private boolean _jspx_meth_c_005furl_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f0.setParent(null);
    // /WEB-INF/content/login.jsp(386,26) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setValue("j_spring_security_check");
    int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
    if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /WEB-INF/content/login.jsp(389,113) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty param.error}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fout_005f0(_jspx_th_c_005fif_005f0, _jspx_page_context))
          return true;
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

  private boolean _jspx_meth_c_005fout_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /WEB-INF/content/login.jsp(389,151) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${SPRING_SECURITY_LAST_USERNAME}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fpassword_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:password
    org.apache.struts2.views.jsp.ui.PasswordTag _jspx_th_s_005fpassword_005f0 = (org.apache.struts2.views.jsp.ui.PasswordTag) _005fjspx_005ftagPool_005fs_005fpassword_0026_005fvalue_005fsize_005fonkeypress_005fname_005fid_005fcssStyle_005fnobody.get(org.apache.struts2.views.jsp.ui.PasswordTag.class);
    _jspx_th_s_005fpassword_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fpassword_005f0.setParent(null);
    // /WEB-INF/content/login.jsp(394,5) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fpassword_005f0.setId("password");
    // /WEB-INF/content/login.jsp(394,5) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fpassword_005f0.setName("j_password");
    // /WEB-INF/content/login.jsp(394,5) name = cssStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fpassword_005f0.setCssStyle("width:189px; border: 1px solid #909090;");
    // /WEB-INF/content/login.jsp(394,5) name = size type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fpassword_005f0.setSize("13");
    // /WEB-INF/content/login.jsp(394,5) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fpassword_005f0.setValue("");
    // /WEB-INF/content/login.jsp(394,5) name = onkeypress type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fpassword_005f0.setOnkeypress("onkeypress_login('password',event)");
    int _jspx_eval_s_005fpassword_005f0 = _jspx_th_s_005fpassword_005f0.doStartTag();
    if (_jspx_th_s_005fpassword_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fpassword_0026_005fvalue_005fsize_005fonkeypress_005fname_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fpassword_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fpassword_0026_005fvalue_005fsize_005fonkeypress_005fname_005fid_005fcssStyle_005fnobody.reuse(_jspx_th_s_005fpassword_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005furl_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:url
    org.apache.struts2.views.jsp.URLTag _jspx_th_s_005furl_005f0 = (org.apache.struts2.views.jsp.URLTag) _005fjspx_005ftagPool_005fs_005furl_0026_005fnamespace_005fincludeParams_005fid_005fescapeAmp_005faction.get(org.apache.struts2.views.jsp.URLTag.class);
    _jspx_th_s_005furl_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005furl_005f0.setParent(null);
    // /WEB-INF/content/login.jsp(413,4) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005furl_005f0.setId("down");
    // /WEB-INF/content/login.jsp(413,4) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005furl_005f0.setAction("show");
    // /WEB-INF/content/login.jsp(413,4) name = namespace type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005furl_005f0.setNamespace("/app");
    // /WEB-INF/content/login.jsp(413,4) name = includeParams type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005furl_005f0.setIncludeParams("none");
    // /WEB-INF/content/login.jsp(413,4) name = escapeAmp type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005furl_005f0.setEscapeAmp("true");
    int _jspx_eval_s_005furl_005f0 = _jspx_th_s_005furl_005f0.doStartTag();
    if (_jspx_eval_s_005furl_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005furl_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005furl_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005furl_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t  \t  ");
        if (_jspx_meth_s_005fparam_005f0(_jspx_th_s_005furl_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t  \t  ");
        if (_jspx_meth_s_005fparam_005f1(_jspx_th_s_005furl_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t  \t  ");
        if (_jspx_meth_s_005fparam_005f2(_jspx_th_s_005furl_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t  \t  ");
        if (_jspx_meth_s_005fparam_005f3(_jspx_th_s_005furl_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t  \t  ");
        if (_jspx_meth_s_005fparam_005f4(_jspx_th_s_005furl_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_005furl_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005furl_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005furl_0026_005fnamespace_005fincludeParams_005fid_005fescapeAmp_005faction.reuse(_jspx_th_s_005furl_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005furl_0026_005fnamespace_005fincludeParams_005fid_005fescapeAmp_005faction.reuse(_jspx_th_s_005furl_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fparam_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005furl_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:param
    org.apache.struts2.views.jsp.ParamTag _jspx_th_s_005fparam_005f0 = (org.apache.struts2.views.jsp.ParamTag) _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.get(org.apache.struts2.views.jsp.ParamTag.class);
    _jspx_th_s_005fparam_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fparam_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005furl_005f0);
    // /WEB-INF/content/login.jsp(414,11) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fparam_005f0.setName("fileName");
    int _jspx_eval_s_005fparam_005f0 = _jspx_th_s_005fparam_005f0.doStartTag();
    if (_jspx_eval_s_005fparam_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fparam_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fparam_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fparam_005f0.doInitBody();
      }
      do {
        out.write("PdHelp.pdf");
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
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.reuse(_jspx_th_s_005fparam_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fparam_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005furl_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:param
    org.apache.struts2.views.jsp.ParamTag _jspx_th_s_005fparam_005f1 = (org.apache.struts2.views.jsp.ParamTag) _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.get(org.apache.struts2.views.jsp.ParamTag.class);
    _jspx_th_s_005fparam_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005fparam_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005furl_005f0);
    // /WEB-INF/content/login.jsp(415,11) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fparam_005f1.setName("realFileName");
    int _jspx_eval_s_005fparam_005f1 = _jspx_th_s_005fparam_005f1.doStartTag();
    if (_jspx_eval_s_005fparam_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fparam_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fparam_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fparam_005f1.doInitBody();
      }
      do {
        out.write("PdHelp.pdf");
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
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.reuse(_jspx_th_s_005fparam_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005fparam_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005furl_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:param
    org.apache.struts2.views.jsp.ParamTag _jspx_th_s_005fparam_005f2 = (org.apache.struts2.views.jsp.ParamTag) _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.get(org.apache.struts2.views.jsp.ParamTag.class);
    _jspx_th_s_005fparam_005f2.setPageContext(_jspx_page_context);
    _jspx_th_s_005fparam_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005furl_005f0);
    // /WEB-INF/content/login.jsp(416,11) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fparam_005f2.setName("bizModuleCd");
    int _jspx_eval_s_005fparam_005f2 = _jspx_th_s_005fparam_005f2.doStartTag();
    if (_jspx_eval_s_005fparam_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fparam_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fparam_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fparam_005f2.doInitBody();
      }
      do {
        out.write("public");
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
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.reuse(_jspx_th_s_005fparam_005f2);
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
    // /WEB-INF/content/login.jsp(417,11) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fparam_005f3.setName("filterType");
    int _jspx_eval_s_005fparam_005f3 = _jspx_th_s_005fparam_005f3.doStartTag();
    if (_jspx_eval_s_005fparam_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fparam_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fparam_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fparam_005f3.doInitBody();
      }
      do {
        out.write('p');
        out.write('d');
        out.write('f');
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

  private boolean _jspx_meth_s_005fparam_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005furl_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:param
    org.apache.struts2.views.jsp.ParamTag _jspx_th_s_005fparam_005f4 = (org.apache.struts2.views.jsp.ParamTag) _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.get(org.apache.struts2.views.jsp.ParamTag.class);
    _jspx_th_s_005fparam_005f4.setPageContext(_jspx_page_context);
    _jspx_th_s_005fparam_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005furl_005f0);
    // /WEB-INF/content/login.jsp(418,11) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fparam_005f4.setName("operator");
    int _jspx_eval_s_005fparam_005f4 = _jspx_th_s_005fparam_005f4.doStartTag();
    if (_jspx_eval_s_005fparam_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_005fparam_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_005fparam_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_005fparam_005f4.doInitBody();
      }
      do {
        out.write("inline;");
        int evalDoAfterBody = _jspx_th_s_005fparam_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_005fparam_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_s_005fparam_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.reuse(_jspx_th_s_005fparam_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fparam_0026_005fname.reuse(_jspx_th_s_005fparam_005f4);
    return false;
  }
}
