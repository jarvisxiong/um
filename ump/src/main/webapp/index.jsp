<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<% session.removeAttribute("loginMobile"); %>
<script language="javascript">
	self.location="${ctx}/index/";
</script>
</head>
</html>