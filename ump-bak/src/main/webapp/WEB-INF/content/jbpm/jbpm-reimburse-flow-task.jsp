<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page
	import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" href="${ctx}/css/content.css" type="text/css" />
<script language="javascript" src="${ctx}/js/jquery.js"
	type="text/javascript"></script>
<script src="${ctx}/js/jbpm/reimburse.js" type="text/javascript"></script>
<script src="${ctx}/js/jbpm/style.js" type="text/javascript"></script>
<script src="${ctx}/js/prompt/ymPrompt.js" type="text/javascript"></script>
<link rel="stylesheet" id='skin' type="text/css"
	href="${ctx}/js/prompt/skin/qq/ymPrompt.css" />
<title>出差流程</title>
<script language="javascript">
	config = {
			ctx : '${ctx}',
			isDesk : '${isDesk}',
			taskId : '${curTaskId}',
			statusCd : '${statusCd}',
			jbpmReimburseId : '${jbpmReimburseId}',
			executionId : '${executionId}',
			isMyTask : '${userCd}'=='<%=SpringSecurityUtils.getCurrentUiid()%>',
			isMyApprove : ('${approver}'+',').indexOf ('<%=SpringSecurityUtils.getCurrentUiid()%>'+',') != -1,
			styleCall : Style.replace
	};
	$(function() {
		calcOfficePay("task");
		loadComment();
		loadDetail('0');
		loadAttachment();
		$(".jbpmInputTitle").toggle(function() {
			$(this).next().next().hide();
			$(this).children(".arrow_down").attr("class", "arrow_right");
		}, function(dom) {
			$(this).next().next().show();
			$(this).children(".arrow_right").attr("class", "arrow_down");
		});
		if (config['styleCall'])
			config['styleCall']();

		$("#reimburse_frame",window.parent.document).css("height","90%");
	});
</script>
</head>

<body>

<div class="jbpmInputTitle">
<div class="arrow_down"></div>
<span>报销申请表</span>
</div>
<div style="background-color: #5a5a5a ;height:1px;margin-top: 0;padding-top: 0px;margin-bottom:5px; " ></div>
<div>
<div>
<fieldset>
<input type="hidden" name="id" value="${jbpmReimburseId}" />
<table class="editTable" cellspacing="10">
	<tr>
		<td width="15%" align="right"><s:text name="jbpmJbpmReimburse.userName"/>:</td>
		<td width="20%"><s:property value="userName" /></td>
		<td width="12.5%" align="right"><s:text name="jbpmJbpmReimburse.reiDate" />:</td>
		<td width="20%"><s:property value="reiDate" /></td>
		<td width="12.5%" align="right"><s:text name="jbpmJbpmReimburse.amountFee"/>:</td>
		<td width="20%" id="amountFee"><s:property value="amountFee" /></td>
		<td width="5px" rowspan="5"></td>
		</tr>
		<tr>
		<td align="right"><s:text name="jbpmJbpmReimburse.travelPlace"/>:</td>
		<td><s:property value="travelPlace" /></td>
		<td align="right">应付个人合计:</td>
		<td id="selfPayAmount"><s:property value="selfPayAmount" /></td>
		<td align="right">公司代付合计:</td>
		<td id="officePayAmount"></td>
		</tr>
		<tr>
		<td align="right"><s:text name="jbpmJbpmReimburse.sendTypeCd"/>:</td>
		<td><p:code2name mapCodeName="mapSendType" value="sendTypeCd"/></td>
		<td align="right"><s:text name="jbpmJbpmReimburse.bankName"/>:</td>
		<td><s:property value="bankName" /></td>
		<td align="right"><s:text name="jbpmJbpmReimburse.bankNo"/>:</td>
		<td><s:property value="bankNo" /></td>
		</tr>
		<tr>
		<td align="right"><s:text name="jbpmJbpmReimburse.travelReason"/>:</td>
		<td colspan="5"><s:property value="travelReason" /></td>
		</tr>
		<tr>
		<td align="right">其他费用说明:</td>
		<td colspan="5"><s:property value="remark" /></td>
		</tr>
</table>
</fieldset>
</div>
<div id="divDetail"></div>
<div style="height: 5px;"></div>
<s:if test="executionId!=null">
	<div id="divToolbar" class="toolBar" align="center">
	<table>
		<tr>
			<td>
			<input type="button" class="btn btnReturn" value="<s:text name="common.return" />" onclick="doReturn('${pageApprove.pageNo}','${searchScop}');"/>
			<s:if test="curTaskName=='accountant'||curTaskName=='end'">
			<input type="button" class="btn btnExport" value="导出" onclick="doExport();"/>
			</s:if>
			<security:authorize ifAnyGranted="A_ADMIN">
			<input type="button" class="btn btnExport" value="导出" onclick="doExport();"/>
			</security:authorize>
			</td>
		</tr>
	</table>
	</div>
	<div style="height: 5px;"></div>
	<s:url id="showAttachment" action="app-attachment!show.action"
		namespace="/app">
		<s:param name="bizEntityId">${jbpmReimburseId}</s:param>
	</s:url>
	<div id="attach_files_div" href="${showAttachment}"></div>
	<div id="divComment"></div>
</s:if>
<div style="height: 10px;"></div>
</div>
<div class="clear jbpmInputTitle">
<div class="arrow_down"></div>
<span>报销审批流程图</span>
</div>
<div style="background-color: #5a5a5a ;height:1px;margin-top: 0;padding-top: 0px;margin-bottom:5px; " ></div>
<div align="center">
<fieldset>
<iframe width="550" height="630" frameborder="0" src="jbpm-reimburse-flow!track.action?executionId=${executionId}"></iframe>
</fieldset>
</div>

</body>
</html>
