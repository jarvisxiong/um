<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!--限额设计指标审批表-->

<div id="billContent">
	
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">工程名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">专项名称:</span>
			<span class="wap_value">${templateBean.specItemName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">概算指标:</span>
			<span class="wap_value">${templateBean.budgetIndexName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">设计单位:</span>
			<span class="wap_value">${templateBean.designOrgName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">限额设计指标:</span>
			<span class="wap_value">${templateBean.designLimitedIndexDesc}</span>
		</div>
</div>
