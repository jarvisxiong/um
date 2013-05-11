<%@page import="java.net.InetAddress"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>登录图纸管理...</title>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
    <%  
    	String[] arr = SpringSecurityUtils.getMysoftFields();
    	String DyPWD = arr[0];
    	
    	String ipAdd = InetAddress.getLocalHost().getHostAddress();
    	String net = "";
    	if(ipAdd.startsWith("192.168.180")||ipAdd.startsWith("192.168.170")||ipAdd.startsWith("192.168.169")
    			||ipAdd.startsWith("192.168.150") || ipAdd.startsWith("10.8.0")) {
    		net = "in"; // 内网
    	} else {
    		net = "out"; // 外网
    	}
    %>
</head>
<body>
    <form id="login_form" action="" method="get" name="logonForm" autocomplete="off" runat="server">
	    <input type="hidden" name="usercode" id="usercode" value="<%= SpringSecurityUtils.getCurrentUiid() %>"/>
	    <input type="hidden" name="DyPWD" id="DyPWD" value="<%= DyPWD %>"/>
	    <input type="hidden" name="net" id="net" value="<%= net %>" />
    </form>
	<script language="javascript">
		$(function(){
			var netType = $("#net").val();
			var url = "";
			if(netType == "in") {
				url = "http://192.168.180.110";
			} else {
				url = "http://180.169.33.87";
			}
 			$('#login_form').attr({"action" : url});
			$('#login_form').submit();
		});
	</script>
</body>
</html>
