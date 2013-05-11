<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--银行账户开户审批表 --%>
<%@ include file="template-header.jsp"%>
<div align="center" class="billContent">
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="110"/>
			<col/>
			<col width="125"/>
			<col/>
			<tr>
				<td class="td_title">申请公司全称</td>
				<td colspan="3"> 
				<input class="inputBorder" validate="required" type="text" name="templateBean.approveCompany" id="approveCompany" value="${templateBean.approveCompany}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">开户银行全称</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.estaAcctName" value="${templateBean.estaAcctName}" />	
				</td>
			</tr>
			<tr>
				<td class="td_title">账户类型</td>
				<td colspan="3"  class="chkGroup">
				 <table border="0" cellpadding="0" cellspacing="0" class="tb_checkbox" validate="required">
					<col width="100"/>
					<col width="100"/>
					<col width="100"/>
					<col width="100"/>
					<col width="100"/>
					<col/>
					<tr>
						<td><s:checkbox name="templateBean.basicAccount"  cssClass="group" />基本户</td>
						<td><s:checkbox name="templateBean.commonAccount"  cssClass="group" />一般户</td>
						<td><s:checkbox name="templateBean.loanAccount"  cssClass="group" />贷款户</td>
						<td><s:checkbox name="templateBean.earnestMoneyAccount"  cssClass="group" />保证金户</td>
						<td><s:checkbox name="templateBean.fixedDepositAccount"  cssClass="group" />定期存款户</td>
						<td><s:checkbox name="templateBean.verificationAccount"  cssClass="group" />验资户</td>
					</tr>
				  </table>
				</td>
			</tr>
			<tr>
				<td class="td_title">币别</td>
				<td>
				<input class="inputBorder" type="text" validate="required" name="templateBean.currencyType" value="${templateBean.currencyType}" />	
				</td>
				<td class="td_title">银行地址</td>
				<td>
				<input class="inputBorder" type="text" validate="required" name="templateBean.bankAddr" value="${templateBean.bankAddr}" />	
				</td>
			</tr>
			<tr>
				<td class="td_title">银行联系人</td>
				<td>
				<input class="inputBorder" type="text" name="templateBean.bankContactor" value="${templateBean.bankContactor}" />	
				</td>
				<td class="td_title">联系方式</td>
				<td>
				<input class="inputBorder" type="text" name="templateBean.contactorType" value="${templateBean.contactorType}" />	
				</td>
			</tr>
			<tr>
				<td class="td_title">申请事由</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.approveReason">${templateBean.approveReason}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
