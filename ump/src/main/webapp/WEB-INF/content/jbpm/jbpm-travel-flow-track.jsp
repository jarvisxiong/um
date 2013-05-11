<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" href="${ctx}/css/content.css" type="text/css" />
<script language="javascript" src="${ctx}/js/jquery.js"></script>
<title>出差流程跟踪</title>
<script language="javascript">
</script>
</head>

<body>
<div>
		<s:url id="pImage" action="jbpm-travel-flow!loadImage.action" >
		</s:url>
		<img style="position:absolute;left:0px;top:0px;" src="${pImage}"/>
		<c:forEach var="coor" items="${activityCoordinates}">
			<div style="position:absolute;left:${coor.x }px;top:${coor.y}px;width:${coor.width}px;height:${coor.height}px;border:2px red solid">
			</div>
		</c:forEach>
		<c:forEach var="coor" items="${historyCoordinates}">
			<div style="position:absolute;left:${coor.x }px;top:${coor.y}px;width:${coor.width}px;height:${coor.height}px;border:2px blue solid">
			</div>
		</c:forEach>
</div>
</body>
</html>
