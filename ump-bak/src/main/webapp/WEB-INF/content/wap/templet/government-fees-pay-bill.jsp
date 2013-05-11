<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--政府规费付款审批表-->
<div class="billContent">
	
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
		<span class="wap_title">公司名称:</span>
		<span class="wap_value">${templateBean.companyName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">项目名称:</span>
		<span class="wap_value">${templateBean.projectName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">规费内容:</span>
		<span class="wap_value">${templateBean.feeContent}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">办理何种工程手续:</span>
		<span class="wap_value">${templateBean.projectProcedure}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">本次支付金额(元):</span>
		<span class="wap_value">${templateBean.curPaymentAmt}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">收费部门/收款单位:</span>
		<span class="wap_value">${templateBean.acceptOrgName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">收费文件号:</span>
		<span class="wap_value">${templateBean.chargeDocNo}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">收费期限:</span>
		<span class="wap_value">${templateBean.chargeDeadlineDesc}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">是否有政策性减免:</span>
		<span class="wap_value">${templateBean.hasPolicyDerate}</span>
	</div>
	<div class="div_label">
		<span class="wap_label">【收款人信息】</span>
		<div class="div_row">
			<span class="wap_title">收款人名称:</span>
			<span class="wap_value">${templateBean.payer}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">收款人开户银行:</span>
			<span class="wap_value">${templateBean.payerAccount}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">收款人账户号:</span>
			<span class="wap_value">${templateBean.payerBank}</span>
		</div>
	</div>
	<div class="div_row">
		<span class="wap_title">需说明事项:</span>
		<span class="wap_value">${templateBean.contentDesc}</span>
	</div>

</div>
