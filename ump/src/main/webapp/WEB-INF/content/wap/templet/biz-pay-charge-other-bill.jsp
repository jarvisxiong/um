<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--商业公司其它费用审批表 -->

<div id="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<div><s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox><span>预算内</span></div>
			<div><s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox><span>预算外</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">申请中心:</span>
			<span class="wap_value">${templateBean.sendOrgName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">合同编号:</span>
			<span class="wap_value">${templateBean.contractNo}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">合同名称:</span>
			<span class="wap_value">${templateBean.contactName}</span>
		</div>
		
		<div class="div_label">
			<span class="wap_label">【收款人（乙方）信息】</span>
			
			<div class="div_row">
				<span class="wap_title">收款人名称:</span>
				<span class="wap_value">${templateBean.receName}</span>
			</div>
			
			<div class="div_row">
				<span class="wap_title">收款人开户行:</span>
				<span class="wap_value">${templateBean.receOpenBankName}</span>
			</div>
			
			<div class="div_row">
				<span class="wap_title">收款人账号:</span>
				<span class="wap_value">${templateBean.receAcctNo}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">付款方式:</span>
				<span class="wap_value">${templateBean.payTypeDesc}</span>
			</div>
		</div>　
		<div class="div_row">
				<span class="wap_title">合同总价（元）:</span>
				<span class="wap_value">${templateBean.contractTotalAmt}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">已确认合同总价（元）:</span>
				<span class="wap_value">${templateBean.confirmTotalAmt}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">已付合同款(元):</span>
				<span class="wap_value">${templateBean.contractPaiedAmt}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">本次支付金额(元):</span>
				<span class="wap_value">${templateBean.curPaymentAmt}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">本次付款时间:</span>
				<span class="wap_value">${templateBean.currentPayDate}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">收款单位:</span>
				<span class="wap_value">${templateBean.orgName}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">需说明事项:</span>
				<span class="wap_value">${templateBean.contentDesc}</span>
		</div>
</div>
