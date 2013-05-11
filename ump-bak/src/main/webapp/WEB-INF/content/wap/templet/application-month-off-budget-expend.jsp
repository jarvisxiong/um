<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page import="com.hhz.ump.util.JspUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page import="com.hhz.ump.util.LoginUtil"%>

<%-- 地产公司月预算外支出申请表（集团拨款支付） --%>
<div id="billContent">
	<%@ include file="template-var.jsp"%>

	<div class="div_row">
		<span class="wap_title">收款单位:</span>
		<span class="wap_value">${templateBean.payee}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">公司名称:</span>
		<span class="wap_value">${templateBean.companyName}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">预算支付日期:</span>
		<span class="wap_value">${templateBean.budgetPayDate}</span>
	</div>
	 
	 
	<div class="div_row">
		<span class="wap_title">合同总金额(元):</span>
		<span class="wap_value">${templateBean.contractTotalAmount}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">合同已付金额(元):</span>
		<span class="wap_value">${templateBean.contractPayedAmount}</span>
	</div>
	
	
	<div class="div_row">
		<span class="wap_title">申请支付金额(元):</span>
		<span class="wap_value">${templateBean.applicationPayedAmount}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">批准支付金额(元):</span>
		<span class="wap_value">${templateBean.approvedPayedAmount}</span>
	</div>



	<div class="div_row">
		<span class="wap_title">付款办理审批情况:</span>
		<div><s:checkbox name="templateBean.paymentApproveState1" cssClass="group"></s:checkbox><span>付款审批手续已完成（需后附）</span></div>
		<div><s:checkbox name="templateBean.paymentApproveState2" cssClass="group"></s:checkbox><span>付款审批手续未完成（需同时报））</span></div>
	</div>
	
	<div class="div_row">
		<span class="wap_title">支出用途及预算外原因:</span>
		<span class="wap_value">${templateBean.offBudgetExpendUseAndReason}</span>
	</div>
</div>
