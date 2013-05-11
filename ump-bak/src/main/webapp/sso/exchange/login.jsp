<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
<%@ Page Language="C#" AutoEventWireup="true" CodeFile="RedirectURLPage.aspx.cs"
    Inherits="RedirectURLPage" %>
 --%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.Constants"%>
<%@page import="com.hhz.ump.util.LoginUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>登录邮箱中...</title>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
    <%--
   		response.sendRedirect("https://mail.powerlong.com/owa/auth/owaauth.dll?destination=https://mail.powerlong.com/owa&flags=1&username="+username+"&password="+password);
    --%>
</head>
<body>
    <form id="login_form" action="https://mail.powerlong.com/owa/auth/owaauth.dll" method="post" name="logonForm" autocomplete="off" runat="server">
	    <input type="hidden" id="destination" name="destination" value="https://mail.powerlong.com/owa" />
	    <input type="hidden" id="username" name="username" value="<%=SpringSecurityUtils.getCurrentUiid() %>"/>
	    <input type="hidden" id="password" name="password" value="<%=SpringSecurityUtils.getCurPassword() %>"/>
	    <input type="hidden" value="1" name="flags" />
    </form>
	<script language="javascript">
		$(function(){
			$('#login_form').submit();
		});
	</script>
</body>
</html>
