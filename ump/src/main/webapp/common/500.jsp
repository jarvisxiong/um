<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory"%>
<!DOCTYPE>
<html>
<head>
<title>500 - 系统内部错误</title>
</head>

<body>
<!--<div id="systemFailed"
	style="text-align: left; padding-left: 80px; padding-right: 80px;">
<s:property value="exception" /></div>
<br />
<div style="text-align: left; padding-left: 80px; padding-right: 80px;">
<s:property value="exceptionStack" /></div>

--><div>
<div style="margin:0 auto;width:500px;">
<h2> 系统内部错误,请联系系统管理员</h2>
<img src="${ctx}/resources/images/common/500.png"/>
</div>
</div>
<div><a href="${ctx}">返回首页</a></div>
</body>
</html>
