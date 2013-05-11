<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 下属公司日常业务支出审批表 --%>
<div class="billContent">

	<%@ include file="template-var.jsp"%>
	<div class="div_row">
		<div><s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox><span>预算内</span></div>
		<div><s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox><span>预算外</span></div>
	</div>
	<div class="div_row">
		<span class="wap_title">公司名称:</span>
		<span class="wap_value">${templateBean.name}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">合同编号:</span>
		<span class="wap_value">${templateBean.contCd}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">本次支付金额(元):</span>
		<span class="wap_value">${templateBean.currPay}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">本次支付时间:</span>
		<span class="wap_value">${templateBean.currDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">合同总金额(元):</span>
		<span class="wap_value">${templateBean.totalAmount}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">已付合同款(元):</span>
		<span class="wap_value">${templateBean.paidAmount}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">付款原因:</span>
		<span class="wap_value">${templateBean.reason}</span>
	</div>
	
</div>
