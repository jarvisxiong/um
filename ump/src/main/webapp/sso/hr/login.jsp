<% response.sendRedirect("http://192.168.180.237:9080/easportal/index_sso.jsp"); %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>登录HR中...</title>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
    <%
    	String[] fields = SpringSecurityUtils.getEasSsoFields();
    	String preURL 	  = fields[0];
		String usermember = fields[1];    
		String userencode = fields[2];    
    %>
    <%--
		response.sendRedirect("http://192.168.180.237:9080/easportal/custom/autologin.jsp?usernumber="+usermember+"&password="+userencode);
     --%>
</head>
<body>
    <form id="login_form" action="http://192.168.180.237:9080/easportal/custom/autologin.jsp" method="post" name="logonForm" autocomplete="off" runat="server">
	    <input type="hidden" id="usernumber" name="usernumber" value="<%= SpringSecurityUtils.getCurrentUiid()%>"/>
	    <input type="hidden" id="password" name="password" value="<%= userencode%>"/>
    </form>
	<script language="javascript">
		$(function(){
			$('#login_form').submit();
		});
	</script>
</body>
</html>
