<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--总包单位施工组织设计方案审批表-->
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="100"/>
			<col/>
			<tr>
			<td class="td_title">审批权限</td>
			<td align="left">
				<table class="tb_checkbox" style="width:100%;">
				    <col width="100">
				    <col width="100">
					<col width="100">
					<col width="100">
					<tr>
					<td><s:checkbox name="templateBean.hotel"  cssClass="group"></s:checkbox>与酒店有关</td>
					</tr>
				</table>
			</td>
			</tr>
			<tr>
				<td class="td_title">项目名称</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}"  /></td>
			</tr>
			<tr>
				<td class="td_title">总包单位</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.totalUnit" value="${templateBean.totalUnit}"  /></td>
			</tr>
			<tr>
				<td class="td_title">内容简述<br>（详细内容附后）</td>
				<td><textarea class="inputBorder contentTextArea" validate="required" type="text" name="templateBean.contentDesc">${templateBean.contentDesc}</textarea></td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
