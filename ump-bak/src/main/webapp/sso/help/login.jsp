<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.LoginUtil"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>登录help中...</title>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
</head>
<body>
  	<form id="login_form" name="frm" action="http://pd.powerlong.com:9090/help/j_spring_security_check" method="post">
	    <input type="hidden" id="login_name" name="j_username" value="<%=SpringSecurityUtils.getCurrentUiid() %>"/>
	    <input type="hidden" id="password" name="j_password" value="<%=SpringSecurityUtils.getCurPassword() %>"/>
    </form>
	<script language="javascript">
		$(function(){
			$('#login_form').submit();
		});
	</script>
</body>
</html> 