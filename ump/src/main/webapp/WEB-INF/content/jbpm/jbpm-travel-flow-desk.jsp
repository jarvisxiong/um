<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" href="${ctx}/css/content.css" type="text/css" />
<script language="javascript" src="${ctx}/js/jquery.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery.form.pack.js" type="text/javascript"></script>
<script src="${ctx}/js/jbpm/jbpm.js" type="text/javascript"></script>
<script src="${ctx}/js/jbpm/style.js" type="text/javascript"></script>
<title>管理区域</title>
<script language="javascript">
	$(function() {
		config = {
			ctx : '${ctx}',
			isDesk : '${isDesk}',
			module : 'travel',
			pageSize : '${page.pageSize}',
			styleCall : Style.replace,
			filter_LIKES_travelCd : '${filter_LIKES_travelCd}'
		};
		loadApply();
		loadApprove('${pageApprove.pageNo}','${searchScop}');
		$("#travel_frame",window.parent.document).css("height","90%");
	});
</script>
</head>

<body>

<div class="moduleTtile"><div class="titleImgTravel"></div><span class="text">出差审批</span></div>
<div style="background-color: #5a5a5a ;height:1px;margin-top: 0;padding-top: 0px;margin-bottom:5px; " ></div>
<div id="divApply"></div>
<div id="divApprove"></div>

</body>
</html>
