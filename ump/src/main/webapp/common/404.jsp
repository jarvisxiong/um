<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="refresh" content="5;URL=${ctx}"/>
<title>404 - 资源不存在</title>
</head>

<body>
<div>
<div style="margin:0 auto;width:500px;">
<h2>欢迎来到 404 页面</h2>
<img src="${ctx}/resources/images/common/404.png"/>
</div>
</div>
<div><a href="${ctx}">返回首页</a></div>
5秒钟之后自动跳往首页。
</body>
</html>
