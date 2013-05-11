<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 设计费付款审批表  --%>

<div id="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<div><s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox><span>预算内</span></div>
			<div><s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox><span>预算外</span></div>
		</div>
		<s:if test="authTypeCd=='FKGL_SJF_35'">
		<div class="div_row">
			<span class="wap_title">发起中心:</span>
			<div><s:checkbox name="templateBean.sendCenter1" cssClass="group"></s:checkbox><span>设计管理中心</span></div>
			<div><s:checkbox name="templateBean.sendCenter2" cssClass="group"></s:checkbox><span>技术研发中心</span></div>
		</div>
		</s:if>
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
			<div><s:checkbox name="templateBean.payDependence1" cssClass="group"></s:checkbox><span>设计成果审批表</span></div>
			<div><s:checkbox name="templateBean.payDependence2" cssClass="group"></s:checkbox><span>图纸签收表</span></div>
			<div><s:checkbox name="templateBean.payDependence3" cssClass="group"></s:checkbox><span>白皮书</span></div>
			<div><s:checkbox name="templateBean.payDependence4" cssClass="group"></s:checkbox><span>材料封样</span></div>
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
