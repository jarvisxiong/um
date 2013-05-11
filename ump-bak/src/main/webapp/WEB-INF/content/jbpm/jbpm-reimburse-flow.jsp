<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" href="${ctx}/css/content.css" type="text/css" />
<script language="javascript" src="${ctx}/js/jquery.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery.form.pack.js"  type="text/javascript"></script>
<script src="${ctx}/js/jbpm/jbpm.js" type="text/javascript"></script>
<script src="${ctx}/js/jbpm/style.js" type="text/javascript"></script>
<title>管理区域</title>
<script language="javascript">

	$(function() {

		config = {
			ctx : '${ctx}',
			isDesk : '${isDesk}',
			module : 'reimburse',
			pageSize : '${page.pageSize}',
			styleCall : Style.replace
		};
		loadApply();
		loadApprove('${pageApprove.pageNo}','${searchScop}');
	});
</script>
</head>

<body>
<%@ include file="/common/gridHeader.jsp" %>
<div id="divApply">
</div>
<div id="divApprove">
</div>
<%@ include file="/common/gridFooter.jsp" %>
</body>
</html>
