<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page
	import="org.springframework.security.ui.AbstractProcessingFilter"%>
<%@ page
	import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter"%>
<%@ page import="org.springframework.security.AuthenticationException"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>powerlong <s:text name="login" /></title>
<%@ include file="/common/meta.jsp"%>
<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/css/pl/login.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/jquery.validate.js"
	type="text/javascript"></script>
<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
<script>
	$(document).ready(function() {
		$("#loginForm").validate();
	});
</script>
</head>

<body>
<%@ include file="/common/header.jsp"%>
<s:div id="content">
	<%
		if (session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null) {
	%>
	<span style="color: red"> 登录失败，请重试.</span>
	<%
		}
	%>
	
	<form id="loginForm" action="${ctx}/j_spring_security_check"
		method="post">
	<table class="noborder" align="center">
		<tr>
			<td><label><s:text name="user.userName" />:</label></td>
			<td><input type="text" name='j_username' id='j_username' class="required"
				<s:if test="not empty param.error">
						value='<%=session.getAttribute(AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY)%>'</s:if> />
			</td>
		</tr>
		<tr>
			<td><label><s:text name="user.password" />:</label></td>
			<td><input type="password" id='j_password' name='j_password'
				class="required" /></td>
		</tr>
		<tr>
			<td colspan='2' align="right"><input type="checkbox"
				name="_spring_security_remember_me" />两周内记住我 
		<s:submit key="login"
				cssClass="button" /></td>
		</tr>
	</table>
	</form>
</s:div>
<%@ include file="/common/footer.jsp"%>
</body>
</html>

