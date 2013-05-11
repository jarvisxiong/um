<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--PD通讯录架构变动确认表-->
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="25%"/>
			<col width="75%"/>
			<tr>
				<td class="td_title">项目名称</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}"  /></td>
			</tr>
			<tr>
				<td class="td_title">物业范围</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.propertyScope" value="${templateBean.propertyScope}"/></td>
			</tr>
			<tr>
			  <td class="td_title">内容简述(详细内容附后)</td>
			  <td><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentBriefly">${templateBean.contentBriefly}</textarea></td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
