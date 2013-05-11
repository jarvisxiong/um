<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!--财务软件升级申请表 -->
<div class="billContent" >
	
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
		<span class="wap_title">申请单位:</span>
		<span class="wap_value">${templateBean.applyOrgName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">申请人:</span>
		<span class="wap_value">${templateBean.applyUserName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">申请日期:</span>
		<span class="wap_value">${templateBean.applyDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">预计升级日期:</span>
		<span class="wap_value">${templateBean.planUpGradeDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">升级原因:</span>
		<span class="wap_value">${templateBean.upGradedReasonDesc}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">费用预算:</span>
		<span class="wap_value">${templateBean.expensesBudgetDesc}</span>
	</div>
	
</div>
