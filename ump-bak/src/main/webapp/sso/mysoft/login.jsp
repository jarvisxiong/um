<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>登录明源中...</title>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
    <%  
    	String[] arr = SpringSecurityUtils.getMysoftFields();
    	String DyPWD = arr[0];
    %>
    <%--
		response.sendRedirect("http://192.168.180.100:8000/default.aspx?&usercode=<%=userencode%>"); 
     --%>
</head>
<body>
    <form id="login_form" action="http://my.powerlong.com/default.aspx" method="get" name="logonForm" autocomplete="off" runat="server">
	    <input type="hidden" name="usercode" id="usercode" value="<%= SpringSecurityUtils.getCurrentUiid() %>"/>
	    <input type="hidden" name="DyPWD" id="DyPWD" value="<%= DyPWD %>"/>
    </form>
	<script language="javascript">
		$(function(){
			$('#login_form').submit();
		});
	</script>
</body>
</html>
