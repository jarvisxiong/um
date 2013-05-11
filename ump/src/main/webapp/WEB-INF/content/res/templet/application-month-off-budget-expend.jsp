<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 地产公司月预算外支出申请表（集团拨款支付） --%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">

	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="120"/>
			<col/>
			<col width="120"/>
			<col/>
			<tr>
				<td class="td_title">收款单位</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.payee" value="${templateBean.payee}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">公司名称</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.companyName" value="${templateBean.companyName}" />
				</td>
				<td class="td_title">预算支付日期</td>
				<td>
				<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.budgetPayDate" value="${templateBean.budgetPayDate}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">合同总金额(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.contractTotalAmount" value="${templateBean.contractTotalAmount}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">合同已付金额(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.contractPayedAmount" value="${templateBean.contractPayedAmount}" onblur="formatVal($(this));" />
				</td>
			</tr>
			<tr>
				<td class="td_title">申请支付金额(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.applicationPayedAmount" value="${templateBean.applicationPayedAmount}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">批准支付金额(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.approvedPayedAmount" value="${templateBean.approvedPayedAmount}" onblur="formatVal($(this));" />
				</td>
			</tr>
			<tr>
				<td class="td_title">付款办理审批情况</td>
				<td colspan="3" class="chkGroup" align="center"  validate="required">
					<s:checkbox name="templateBean.paymentApproveState1"  cssClass="group"></s:checkbox>付款审批手续已完成（需后附）
					<s:checkbox name="templateBean.paymentApproveState2"  cssClass="group"></s:checkbox>付款审批手续未完成（需同时报）
				</td>
			</tr>
			<tr>
				<td class="td_title">支出用途及预算外原因</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.offBudgetExpendUseAndReason">${templateBean.offBudgetExpendUseAndReason}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
