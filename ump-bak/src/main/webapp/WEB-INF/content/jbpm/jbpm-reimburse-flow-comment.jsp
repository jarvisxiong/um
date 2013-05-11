<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<script src="${ctx}/js/table.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery.js"></script>
<script src="${ctx}/js/jquery.form.pack.js"  type="text/javascript"></script>
<title>管理区域</title>
</head>

<body>
<div id="tableDiv">
<fieldset>
	<legend><s:text name="jbpm.approveIdeas"/></legend>
<table id="resultTable" width="99%">
	<tbody>
	<s:iterator value="jbpmTaskComments">
	<tr id="main_${id}">
		<td>
			<table class="showTable"  cellpadding="0" cellspacing="0" >
			<tr>
			<td id="creator" width="80">${creator}&nbsp;</td>
			<td id="createdDate" width="180"><s:property value="createdDate" />&nbsp;</td>
			<td id="positionName" width="80"><s:property value="positionName" />&nbsp;</td>
			<td id="opinion" width="60"><s:property value="opinion" />&nbsp;</td>
			<td id="message"><s:property value="message" />&nbsp;</td>
			</tr>
			</table>
		</td>
	</tr>
	</s:iterator>
	
	<s:if test="(isMyApprove=='true' || isMyTask=='true') && statusCd != 4 && statusCd != 1">
	<tr>
		<td  width="10000">
		<s:form id="inputFromComment" action="jbpm-reimburse-flow!addComments.action" method="post">
		<input type="hidden" name="executionId" value="${executionId}" />
		<input type="hidden" name="taskId" value="${taskId}" />
		<input type="hidden" name="isMyTask" value="${isMyTask}" />
		<table class="editTable"><tr>
			<td><s:textarea name="comment" id="comment"/></td>
			</tr>
		</table>
		</s:form>
			<div class="toolBar" align="center">
			<s:if test="isMyTask=='true'">
			<input type="button" class="btn btnSay" value="<s:text name="common.say" />" onclick="saveComment();"/>
			</s:if>
			<s:if test="isMyApprove=='true'&& statusCd!=2 ">
			<input type="button" class="btn btnAgree" value="<s:text name="common.approve" />" onclick="approve('${taskId}');"/>
			<input type="button" class="btn btnBack" value="<s:text name="jbpm.back" />" onclick="doBack('${taskId}');"/>
			</s:if>
			</div>
			</td>
	</tr>
	</s:if>
	<security:authorize ifAnyGranted="A_ADMIN">
			<input type="button" class="btn btnAgree" value="<s:text name="common.approve" />" onclick="approve('${taskId}');"/>
			<input type="button" class="btn btnBack" value="<s:text name="jbpm.back" />" onclick="doBack('${taskId}');"/>
	</security:authorize>
	</tbody>
</table>
</fieldset>
</div>
</body>
</html>
