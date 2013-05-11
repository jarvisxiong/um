<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--融资类合同审批表-->
<div class="billContent">
	
	<%@ include file="template-var.jsp"%>
	
	<div class="div_label">
		<span class="wap_label">【合作双方】</span>
		<div class="div_row">
			<span class="wap_title">甲方:</span>
			<span class="wap_value">${templateBean.partA}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">签约人:</span>
			<span class="wap_value">${templateBean.partASign}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">乙方:</span>
			<span class="wap_value">${templateBean.partB}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">签约人:</span>
			<span class="wap_value">${templateBean.partBSign}</span>
		</div>
		<!-- Start Add for part C by zhuxj on 2012.3.31 -->
		<div class="div_row">
			<span class="wap_title">丙方:</span>
			<span class="wap_value">${templateBean.partC}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">签约人:</span>
			<span class="wap_value">${templateBean.partCSign}</span>
		</div>
		<!-- End   Add for part C by zhuxj on 2012.3.31 -->
	</div>
	<div class="div_label">
		<span class="wap_label">【合同主要内容】</span>
		<div class="div_row">
			<span class="wap_title">主要内容:</span>
			<span class="wap_value">${templateBean.mainContent}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">合同款价(元):</span>
			<span class="wap_value">${templateBean.contractPrice}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">付款方式:</span>
			<span class="wap_value">${templateBean.payWay}</span>
		</div>
	</div>
	
</div>
