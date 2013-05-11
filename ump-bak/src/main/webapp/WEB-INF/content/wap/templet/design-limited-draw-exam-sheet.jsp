<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!--限额设计图纸复核审批表-->

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
			<span class="wap_title">实际设计指标<s:if test="authTypeCd == 'SJGL_XESJZB_30'">(酒店开发中心填写)</s:if><s:else>(区域公司技术管理部填写)</s:else>:</span>
			<span class="wap_value">${templateBean.realDesignIndexDesc}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">对比分析(成本控制中心填写):</span>
			<span class="wap_value">${templateBean.compareDesc}</span>
		</div>
</div>
