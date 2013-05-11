<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--PD通讯录架构变动确认表-->
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="25%"/>
			<col width="25%"/>
			<col width="20%"/>
			<col width="30%"/>
			<tr>
				<td class="td_title">填表人</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.applicant" value="${templateBean.applicant}"  /></td>
				<td class="td_title">填表日期</td>
				<td><input class="inputBorder Wdate" onfocus="WdatePicker()" validate="required" type="text" name="templateBean.applyDate" value="${templateBean.applyDate}"  /></td>
			</tr>
			<tr>
				<td class="td_title">所在中心总经理</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.centerManager" value="${templateBean.centerManager}"/></td>
			</tr>
			<tr>
			  <td class="td_title">变动说明(可带附件)</td>
			  <td colspan="3"><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.changeDesc">${templateBean.changeDesc}</textarea></td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
