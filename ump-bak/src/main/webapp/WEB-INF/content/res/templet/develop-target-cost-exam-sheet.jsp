<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!--开发项目目标成本审批表-->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="100">
			<col width="100">
			<col width="100">
			<col width="140">
			<col>
			<col>
			 <tr>
				<td class="td_title">工程名称</td>
				<td colspan="5">
					<input class="inputBorder" validate="required" type="text" name="templateBean.engineeringName" value="${templateBean.engineeringName}" />
				</td>
			</tr>  
			 <tr>
				<td class="td_title">专项名称</td>
				<td colspan="5">
					<input validate="required" class="inputBorder" type="text" name="templateBean.specItemName" value="${templateBean.specItemName}"/>
				</td>
			</tr>  
			 <tr>
				<td class="td_title">开竣工时间</td>
				<td>
					<input validate="required" class="Wdate inputBorder" type="text" name="templateBean.startDate" value="${templateBean.startDate}" onfocus="WdatePicker()"/>
				</td>
				<td>
					<input validate="required" class="Wdate inputBorder" type="text" name="templateBean.endDate" value="${templateBean.endDate}" onfocus="WdatePicker()"/>
				</td>
				<td class="td_title">目标成本总额(元)</td>
				<td colspan="2">
					<input validate="required" class="inputBorder" type="text" name="templateBean.targetCostAmt" value="${templateBean.targetCostAmt}" onblur="formatVal($(this));"/>
				</td>
			</tr>  
			<tr>
				<td class="td_title">主要内容及说明<br/>(目标成本明细附后)</td>
				<td colspan="5">
					<textarea class="inputBorder contentTextArea" name="templateBean.contentDesc" style="height:200px;">${templateBean.contentDesc}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
