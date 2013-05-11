<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<%--设计变更审批表 --%>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="100px">
			<col>
			<col width="120px">
			<col>
			<tr>
				<td class="td_title">工程名称</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.engineeringName" value="${templateBean.engineeringName}"/>
				</td>
				<td class="td_title">原图纸号</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.paperNumber" value="${templateBean.paperNumber}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">子项名称</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.subName" value="${templateBean.subName}"/>
				</td>
				<td class="td_title">增加或减少的费用(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.changeMoney" value="${templateBean.changeMoney}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">累计变更比例</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.addUpRate" value="${templateBean.addUpRate}"/>
				</td>
				<td class="td_title">累计变更额(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.addUpMoney" value="${templateBean.addUpMoney}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">变更内容及原因</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentAndReason" rows="6" cols="30">${templateBean.contentAndReason}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>