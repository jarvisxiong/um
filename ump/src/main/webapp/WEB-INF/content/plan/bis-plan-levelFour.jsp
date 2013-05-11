<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="java.util.Date"%>
<%@page import="com.hhz.core.utils.DateOperator"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/global.jsp"%> 
<title>商业计划平台-->四级计划汇总</title>
<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/plan.css"/>
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/exec-plan-stat.css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/exec-plan.css"/>
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/userSelection.css" />
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js" ></script>
		<script type="text/javascript" src="${ctx}/js/oa/userSelection.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/plan/exec-plan.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript">
jQuery(function($) {
	var loading="<div style='height:100px'></div><table width='100%'><tr><td align='center'><img src='"+_ctx+"/images/loading.gif'/></td></tr></table>";
	$("#list_leveFour").html(loading).load("${ctx}/plan/exec-plan!levelFour.action",parent._queryParam,function(){
		autoHeight();	
	});
});
</script>
</head>
<body>
<div id="list_leveFour"></div>
</body>
</html>

