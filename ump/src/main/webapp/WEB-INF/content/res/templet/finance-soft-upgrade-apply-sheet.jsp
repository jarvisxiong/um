<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<%@ include file="template-header.jsp"%>

<!--财务软件升级申请表 -->

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="15%">
			<col width="35%">
			<col width="15%">
			<col width="35%">
			 <tr>
				<td class="td_title">申请单位</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.applyOrgName" value="${templateBean.applyOrgName}"/>
					<input type="hidden" id="centerCd"  name="templateBean.applyOrgCd" value="${templateBean.applyOrgCd}"  />
				</td>
				<td class="td_title">申请人</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.applyUserName" value="${templateBean.applyUserName}"/>
					<input type="hidden" id="centerCd"  name="templateBean.applyUserCd" value="${templateBean.applyUserCd}"  />
				</td>
			</tr> 
			<tr>
				<td class="td_title">申请日期</td>
				<td>
					<input validate="required" class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.applyDate" value="${templateBean.applyDate}"/>
				</td>
				<td class="td_title">预计升级日期</td>
				<td>
					<input validate="required" class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.planUpGradeDate" value="${templateBean.planUpGradeDate}"/>
				</td>
			</tr> 
			<tr>
				<td class="td_title">升级原因</td>
				<td colspan="3">
					<textarea validate="required" class="inputBorder contentTextArea" name="templateBean.upGradedReasonDesc">${templateBean.upGradedReasonDesc}</textarea>
				</td>
			</tr> 
			<tr>
				<td>费用预算</td>
				<td colspan="3">
					<textarea validate="required" class="inputBorder contentTextArea" name="templateBean.expensesBudgetDesc" style="width:100%;height:200px;">${templateBean.expensesBudgetDesc}</textarea>
                 </td>
			</tr> 
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
