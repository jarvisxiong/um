<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 地产公司办公室租凭审批表 --%>
<div class="billContent">
	
	<%@ include file="template-var.jsp"%>
		
	<div class="div_row">
		<span class="wap_title">申请公司:</span>
		<span class="wap_value">${templateBean.approveCompany}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">租凭地点:</span>
		<span class="wap_value">${templateBean.rentAddr}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">面积大小:</span>
		<span class="wap_value">${templateBean.rentArea}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">租凭期:</span>
		<span class="wap_value">${templateBean.rentDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">资金数额(元):</span>
		<span class="wap_value">${templateBean.fundNo}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">费用说明(租凭合同附后):</span>
		<span class="wap_value">${templateBean.costDeclare}</span>
	</div>
	
</div>
