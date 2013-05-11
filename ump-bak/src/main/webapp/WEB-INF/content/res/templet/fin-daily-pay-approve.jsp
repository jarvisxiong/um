<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 下属公司日常业务支出审批表 --%>
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
				<td></td>
				<td colspan="3" validate="required" class="chkGroup">
					<s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox>预算内
					<s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox>预算外
				</td>
			</tr>
			<tr>
				<td class="td_title">公司名称</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.name" value="${templateBean.name}" />
				</td>
				<td class="td_title">合同编号</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.contCd" value="${templateBean.contCd}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">本次支付金额(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.currPay" value="${templateBean.currPay}" onblur="formatVal($(this));"/>
				</td>
				<td class="td_title">本次支付时间</td>
				<td>
				<input onfocus="WdatePicker()" class="Wdate inputBorder" validate="required" type="text" name="templateBean.currDate" value="${templateBean.currDate}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">合同总金额(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.totalAmount" value="${templateBean.totalAmount}" onblur="formatVal($(this));"/>
				</td>
				<td class="td_title">已付合同款(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.paidAmount" value="${templateBean.paidAmount}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td class="td_title" >付款原因</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.reason">${templateBean.reason}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
