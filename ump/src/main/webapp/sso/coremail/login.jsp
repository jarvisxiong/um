<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>登录coremail中...</title>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
    
    <%--
    <% String outUrl = "http://mail1.powerlong.com/coremail/XJS/mbox/list_oa.jsp?"+ SpringSecurityUtils.getCoreMailSID()[0] +"&fid=0"; %>
    <% String outUrlExt = "http://localhost/coremail/XJS/index.jsp?"+ SpringSecurityUtils.getCoreMailSID()[0]; %>
     --%>
    <%--
		response.sendRedirect("http://192.168.180.237:9080/easportal/custom/eas.jsp?usernumber="+usermember+"&userencode="+userencode); 
     --%>
     
     <%
	     String[] fields = SpringSecurityUtils.getCoreMailFields();
	     String sid = fields[1];
	     //String outUrl = "http://mail.powerlong.com/coremail/XJS/mbox/list_oa.jsp?"+ sid +"&fid=0";
	     String outUrlExt = fields[0]+"/coremail/XJS/index.jsp?"+ sid; 
     %>
</head>
<body>
	<script language="javascript">
		$(function(){
			window.location.href = '<%= outUrlExt %>';
		});
	</script>
</body>
</html>
