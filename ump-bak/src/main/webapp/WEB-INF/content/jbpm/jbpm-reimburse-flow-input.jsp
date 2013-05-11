<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" href="${ctx}/css/content.css" type="text/css" />
<script language="javascript" src="${ctx}/js/jquery.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/PdValidate.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/datePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctx}/js/jbpm/reimburse.js" type="text/javascript"></script>
<script src="${ctx}/js/jbpm/style.js" type="text/javascript"></script>
<script src="${ctx}/js/prompt/ymPrompt.js" type="text/javascript"></script>
<link rel="stylesheet" id='skin' type="text/css" href="${ctx}/js/prompt/skin/qq/ymPrompt.css" />
<title>报销申请</title>
<script language="javascript">
$(function(){
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
	    calcOfficePay();
		loadComment();
		loadDetail('1');
		$(".jbpmInputTitle").toggle(function() {
			$(this).next().next().hide();
			$(this).children(".arrow_down").attr("class","arrow_right");
		}, function(dom) {
			$(this).next().next().show();
			$(this).children(".arrow_right").attr("class","arrow_down");
		});
		$("#sendTypeCd").bind("change",function(){
			if (this.value=="1"){
			$("#bankName").removeAttr("readonly");
			$("#bankNo").removeAttr("readonly");
			$("#bankName").removeClass("readonly");
			$("#bankNo").removeClass("readonly");
			$("#bankName").attr("validate","required");
			$("#bankNo").attr("validate","required");
			}else{
			$("#bankName").attr("readonly","readonly");
			$("#bankNo").attr("readonly","readonly");
			$("#bankName").addClass("readonly");
			$("#bankNo").addClass("readonly");
			$("#bankName").removeClass("error");
			$("#bankNo").removeClass("error");
			$("#bankName").removeAttr("validate");
			$("#bankNo").removeAttr("validate");
			}
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
<div class="arrow_down"></div><span>报销申请表</span>
</div>
<div style="background-color: #5a5a5a ;height:1px;margin-top: 0;padding-top: 0px;margin-bottom:5px; " ></div>
<div>
<fieldset>
<s:form id="inputFromReimburse" action="jbpm-reimburse-flow!save.action" method="post" cssClass="inputFrom" >
<div>
		<input type="hidden" name="isDesk" value="${isDesk}" />
		<input type="hidden" name="id" value="${jbpmReimburseId}" />
		<input type="hidden" name="taskId" value="${curTaskId}" />
		<s:hidden name="recordVersion" id="recordVersionReimburse" />
		<s:hidden name="entityTmpId"/>
	<table class="editTable" cellpadding="10" >
		<tr>
		<td width="15%" align="right"><s:text name="jbpmJbpmReimburse.userName"/>:</td>
		<td width="20%">
		<input type="hidden"  id="userCd" name="userCd" value="${userCd}" />
		<input type="text" class="readonly" readonly="readonly" name="userName" id="userName"  value="${userName}" />
		</td>
		<td width="12.5%" align="right"><s:text name="jbpmJbpmReimburse.reiDate" />:</td>
		<td width="20%"><s:textfield validate="required" id="reiDate" name="reiDate" onfocus="WdatePicker()" cssClass="Wdate" /></td>
		<td width="12.5%" align="right"><s:text name="jbpmJbpmReimburse.amountFee"/>:</td>
		<td width="20%"><s:textfield  cssClass="readonly"  readonly="true" name="amountFee" id="amountFee"/></td>
		<td width="5px" rowspan="5"></td>
		</tr>
		<tr>
		<td align="right"><s:text name="jbpmJbpmReimburse.travelPlace"/>:</td>
		<td><s:textfield id="travelPlace" name="travelPlace"/></td>
		<td align="right">应付个人合计:</td>
		<td><input type="text" onblur="calcOfficePay(this);" validate="num[0,6,1]required" name="selfPayAmount" id="selfPayAmount"  value="<s:property value="selfPayAmount" />" /></td>
		<td align="right">公司代付合计:</td>
		<td><input type="text" class="readonly" readonly="readonly" id="officePayAmount" /></td>
		</tr>
		<tr>
		<td align="right"><s:text name="jbpmJbpmReimburse.sendTypeCd"/>:</td>
		<td><s:select list="mapSendType" listKey="key" listValue="value" id="sendTypeCd" name="sendTypeCd" validate="required" ></s:select> </td>
		<td align="right"><s:text name="jbpmJbpmReimburse.bankName"/>:</td>
		<td><input type="text" class="readonly" readonly="readonly" name="bankName" id="bankName"  value="${bankName}" /></td>
		<td align="right"><s:text name="jbpmJbpmReimburse.bankNo"/>:</td>
		<td><input type="text" class="readonly" readonly="readonly" name="bankNo" id="bankNo"  value="${bankNo}" /></td>
		</tr>
		<tr>
		<td align="right"><s:text name="jbpmJbpmReimburse.travelReason"/>:</td>
		<td colspan="5"><s:textarea id="travelReason" name="travelReason"/>
		</td>
		</tr>
		<tr>
		<td align="right">其他费用说明:</td>
		<td colspan="5"><s:textarea id="remark" name="remark"  validate="len[0,200]" />
		</td>
		</tr>
	</table>
</div>
</s:form>
</fieldset>
<div id="divDetail">
</div>
<div class="toolBar" align="center">
	<input type="button" class="btn btnSave" value="<s:text name="common.save" />" onclick="save();"/>
	<input type="button" class="btn btnApply" value="<s:text name="common.apply" />" onclick="apply();"/>
	<s:if test="statusCd!=3">
	<input type="button" class="btn btnCancel" value="<s:text name="common.cancel" />" onclick="cancel('${taskId}','${jbpmReimburseId}');"/>
	</s:if>
	<input type="button" class="btn btnAttach" value="<s:text name="common.attachment" />" onclick="openAttachment('<s:text name="appAttachment.title"/>','${jbpmReimburseId==null?entityTmpId:jbpmReimburseId}'); return false;"/>
	<input type="button" class="btn btnReturn" value="<s:text name="common.return" />" onclick="doReturn('${pageApprove.pageNo}','${searchScop}');"/>
	<security:authorize ifAnyGranted="A_ADMIN">
	<input type="button" class="btn btnExport" value="导出" onclick="doExport();"/>
	</security:authorize>
</div>
<div style="height: 10px;"></div>
<s:if test="executionId!=null">
<div id="divComment">
</div>
</s:if>
<div style="height: 10px;"></div>
</div>
<div class="clear jbpmInputTitle">
<div class="arrow_down"></div><span>报销审批流程图</span>
</div>
<div style="background-color: #5a5a5a ;height:1px;margin-top: 0;padding-top: 0px;margin-bottom:5px; " ></div>
<div align="center">
<fieldset>
<iframe width="550" height="630" frameborder="0" src="jbpm-reimburse-flow!track.action?executionId=${executionId}"></iframe>
</fieldset>
</div>
<s:if test="isDesk!=1">
<%@ include file="/common/gridFooter.jsp" %>
</s:if>
</body>
</html>
