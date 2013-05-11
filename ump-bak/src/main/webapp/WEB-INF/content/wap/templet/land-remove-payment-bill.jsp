<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--土地、拆迁付款审批单-->

<div id="billContent">
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
			<span class="wap_title">合同编号:</span>
			<span class="wap_value">${templateBean.contractNo}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">合同名称:</span>
			<span class="wap_value">${templateBean.contractName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">合同总金额(元):</span>
			<span class="wap_value">${templateBean.contractTotalAmt}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">已付合同款(元):</span>
			<span class="wap_value">${templateBean.contractPaidAmt}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">本次支付金额(元):</span>
			<span class="wap_value">${templateBean.curPaymentAmt}</span>
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
			<span class="wap_value">${templateBean.payerBank}</span>
		</div>
</div>
