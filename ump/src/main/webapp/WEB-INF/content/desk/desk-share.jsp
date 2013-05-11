<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>
	<meta http-equiv="Content-Type" content="text/html" />
	<script language="javascript" src="${ctx}/js/jquery.js"></script>
	<title>文件共享</title>
</head>
<body>
<div id="divJre">
<applet width='0' id='OpenShareApplet' height='0'
	code='OpenShareApplet.class' codebase='.' archive='../winApplet.jar'>
	<param name='url' value='192.168.180.252' />
</applet>
</div>
<script language="javascript">
openShare();
function openShare(){
	$("#OpenShareApplet").remove();
	$("#divJre").append("<applet width='0' id='OpenShareApplet' height='0' code='OpenShareApplet.class' codebase='.' archive='../winApplet.jar'><param name='url' value='192.168.180.252'/></applet>");
}
</script>
</body>
</html>