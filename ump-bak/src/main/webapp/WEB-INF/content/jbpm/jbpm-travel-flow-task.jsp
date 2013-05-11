<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" href="${ctx}/css/content.css" type="text/css" />
<script language="javascript" src="${ctx}/js/jquery.js" type="text/javascript"></script>
<script src="${ctx}/js/prompt/ymPrompt.js" type="text/javascript"></script>
<link rel="stylesheet" id='skin' type="text/css" href="${ctx}/js/prompt/skin/qq/ymPrompt.css" />
<script src="${ctx}/js/jbpm/travel.js" type="text/javascript"></script>
<script src="${ctx}/js/jbpm/style.js" type="text/javascript"></script>
<title>出差流程</title>
<script language="javascript">

$(function(){
	config = {
		ctx : '${ctx}',
		isDesk : '${isDesk}',
		taskId : '${curTaskId}',
		statusCd : '${statusCd}',
		id : '${jbpmTravelApplyId}',
		executionId : '${executionId}',
		isMyTask : '${userCd}'=='<%=SpringSecurityUtils.getCurrentUiid()%>',
		isMyApprove : ('${approver}'+',').indexOf ('<%=SpringSecurityUtils.getCurrentUiid()%>'+',') != -1,
		styleCall : Style.replace
	};
	
	loadComment();
	loadAttachment();
	$(".jbpmInputTitle").toggle(function() {
		$(this).next().next().hide();
		$(this).children(".arrow_down").attr("class","arrow_right");
	}, function(dom) {
		$(this).next().next().show();
		$(this).children(".arrow_right").attr("class","arrow_down");
	});
	if(config['styleCall'])config['styleCall']();
});
</script>
</head>

<body>
<s:if test="isDesk!=1">
<%@ include file="/common/gridHeader.jsp" %>
</s:if>
<div class="jbpmInputTitle">
<div class="arrow_down"></div><span>出差申请表</span>
</div>
<div style="background-color: #5a5a5a ;height:1px;margin-top: 0;padding-top: 0px;margin-bottom:5px; " ></div>
<div>
	<fieldset>
		<input type="hidden" name="id" value="${jbpmTravelApplyId}" />
	<table class="editTable" cellspacing="5" >
		<tr align="left">
		<td width="15%" align="right"><s:text name="jbpmJbpmTravelApply.userName"/>:</td>
		<td width="20%" ><s:property value="userName" /></td>
		<td width="15%" align="right"><s:text name="jbpmJbpmTravelApply.deptCd"/>:</td>
		<td width="20%" ><%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("deptCd"))%></td>
		<td width="10%" align="right"><s:text name="jbpmJbpmTravelApply.positionCd"/>:</td>
		<td width="20%" ><%=CodeNameUtil.getPositionNameByCd(JspUtil.findString("positionCd"))%></td>
		<td width="5px" rowspan="5"></td>
		</tr>
		<tr>
		<td align="right"><s:text name="jbpmJbpmTravelApply.travelPlace"/>:</td><td><s:property value="travelPlace" /></td>
		<td align="right"><s:text name="jbpmJbpmTravelApply.startDate"/>:</td><td><s:property value="startDate" /></td>
		<td align="right"><s:text name="jbpmJbpmTravelApply.endDate"/>:</td><td><s:property value="endDate" /></td>
		</tr>
		<tr>
		<td align="right"><s:text name="jbpmJbpmTravelApply.otherUserNames"/>:</td><td colspan="3"><s:property value="otherUserNames" /></td>
		<td align="right">交通方式:</td><td><p:code2name mapCodeName="mapTravelWay" value="travelWay"/></td>
		</tr>
		<tr>
		<td align="right"><s:text name="jbpmJbpmTravelApply.travelReason"/>:</td>
		<td colspan="5" ><div><s:property value="travelReason" /></div>
		</td>
		</tr>
		<tr>
		<td align="right"><s:text name="jbpmJbpmTravelApply.travelPlan"/>:</td>
		<td colspan="5"><s:property value="travelPlan" />
		</td>
		</tr>
	</table>
<div style="height: 5px;" ></div>
<div class="toolBar" align="center" >
<input type="button" class="btn btnReturn" value="<s:text name="common.return" />" onclick="doReturn('${pageApprove.pageNo}','${searchScop}');"/>
</div>
</fieldset>
<div style="height: 5px;"></div>
<s:url id="showAttachment" action="app-attachment!show.action" namespace="/app">
	<s:param name="bizEntityId">${jbpmTravelApplyId}</s:param>
</s:url>
<div id="attach_files_div" href="${showAttachment}">
</div>
<div id="divComment">
</div>
<div style="height: 10px;"></div>
</div>
<div class="clear jbpmInputTitle">
<div class="arrow_down"></div><span>出差申请流程图</span>
</div>
<div style="background-color: #5a5a5a ;height:1px;margin-top: 0;padding-top: 0px;margin-bottom:5px; " ></div>
<fieldset>
<div align="center" >
<iframe align="middle" width="530" height="530" frameborder="0" src="jbpm-travel-flow!track.action?executionId=${executionId}"></iframe>
</div>
</fieldset>
<s:if test="isDesk!=1">
<%@ include file="/common/gridFooter.jsp" %>
</s:if>
</body>
</html>
