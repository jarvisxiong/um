<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>管理区域</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
	<script language="javascript"  src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script language="javascript" src="${ctx}/resources/js/common/common.js"></script>
	
	<!-- 自定义 -->
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/work-plan-main.css" />
	<script language="javascript" src="${ctx}/resources/js/plan/work-plan-main.js" ></script>
	
</head>

<body>
	<div id="main_panel"></div>
		
	<%-- 用于ajax搜索后显示结果1 --%>
	<div id="popDiv" class="popDiv"></div>
	 
	<script language="javascript">
		loadMainPanel();
	</script>
</body>
</html>



