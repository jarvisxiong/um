<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <title>校验结果</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
</head>
<body> 
	
	<div>
	校验类型: <br/>
		1.elem-返回页面元素(默认)<br/>
		2.url-返回sucess.jsp/failure.jsp/exception.jsp<br/>
		3.返回response参数(eg:head/session/request)<br/>
	</div>

	<!-- 返回结果类型:1-成功 0-失败 2-异常-->
	<input type="text" id="result" name="result" value="${validateResult}"/> 
	<!-- 返回结果描述:成功/失败/异常描述-->
	<input type="text" id="resultDesc" name="resultDesc" value="${validateResultDesc}"/> 
	<%--
	<a href="{ctx}/uaap/ssl/ssl.action">重试测试</a>
	 --%>
</body>
</html>
