﻿<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script language="javascript"  src="${ctx}/resources/js/jquery/jquery.js"></script>
</head>
<body>
	<form id="login_form" name="login_form" class="login_form" action="http://plas.powerlong.com/j_spring_security_check" method="post" >
		<input  name="j_username" id="j_username" value="<%= SpringSecurityUtils.getCurrentUiid() %>" type="hidden"/>
		<input  name="j_password" id="j_password" value="<%= SpringSecurityUtils.getCurPassword() %>" type="hidden"/>
	</form>
	<script language="javascript">
		$(function(){
			$('#login_form').submit();
		});
	</script>
</body>
</html>