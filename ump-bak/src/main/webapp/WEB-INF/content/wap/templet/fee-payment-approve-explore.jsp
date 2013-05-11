<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 勘察、测绘费、监理费付款审批表  --%>

<div id="billContent">
	<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
	</s:set>
	
	
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">审批权限:</span>
			<div><s:checkbox name="templateBean.hotel" cssClass="group"></s:checkbox><span>与酒店有关</span></div>
		</div>
		<div class="div_row">
			<div><s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox><span>预算内</span></div>
			<div><s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox><span>预算外</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">合同编号:</span>
			<span class="wap_value">${templateBean.contNo}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">合同名称:</span>
			<span class="wap_value">${templateBean.contName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName} (${templateBean.projectPeriod})期</span>
		</div>
		<div class="div_row">
			<span class="wap_title">付款单位:</span>
			<span class="wap_value">${templateBean.paymentUnit}</span>
		</div>
		<div class="div_label">
			<span class="wap_label">【收款人(乙方)信息】</span>
			<div class="div_row">
				<span class="wap_title">收款人名称:</span>
				<span class="wap_value">${templateBean.partB}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">收款人开户行:</span>
				<span class="wap_value">${templateBean.payeeOpenBankNo}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">收款人账号:</span>
				<span class="wap_value">${templateBean.payeeAccountNo}</span>
			</div>
		</div>
		<div class="div_row">
				<span class="wap_title">付款方式:</span>
				<span class="wap_value">${templateBean.payWay}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">合同总价:</span>
				<span class="wap_value">${templateBean.totalPrice}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">已确认合同总价:</span>
				<span class="wap_value">${templateBean.updateTotal}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">本次付款前已经支付(元):</span>
				<span class="wap_value">${templateBean.currentPayBefore}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">本次付款前支付比例%:</span>
				<span class="wap_value">${templateBean.payRateBefore}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">本次付款理由:</span>
				<span class="wap_value">${templateBean.currentPayReason}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">本次付款期数:</span>
				<span class="wap_value">${templateBean.currentPayPeriod}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">付款依据:</span>
				<span class="wap_value">${templateBean.payDependence}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">本次付款金额(元):</span>
				<span class="wap_value">${templateBean.currentPayThis}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">本次付款比例%:</span>
				<span class="wap_value">${templateBean.payRateThis}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">本次付款后付款总额(元):</span>
				<span class="wap_value">${templateBean.currentPayTotal}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">本次付款后支付比例%:</span>
				<span class="wap_value">${templateBean.payRateTotal}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">备注:</span>
				<span class="wap_value">${templateBean.remark}</span>
		</div>
		<div class="div_label">
			<span class="wap_label">【附件】</span>
			<div class="div_row">
				<span class="wap_title">付款依据附件:</span>
				<div id="show_payDependenceId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.payDependenceId}','payDependenceId','${canEdit}');
				</script>
			</div>
		</div>
</div>

