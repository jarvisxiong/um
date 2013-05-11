<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 履约保证金付款审批表  --%>

<div id="billContent">
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
				<span class="wap_title">实际开工日期:</span>
				<span class="wap_value">${templateBean.realBeginDate}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">实际竣工日期:</span>
				<span class="wap_value">${templateBean.realEndDate}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">合同工期(天):</span>
				<span class="wap_value">${templateBean.period}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">实际工期(天):</span>
				<span class="wap_value">${templateBean.realPeriod}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">保修期起止时间:</span>
				<span class="wap_value">${templateBean.guarBeginDate} 至 ${templateBean.guarEndDate} </span>
		</div>
		<div class="div_row">
				<span class="wap_title">工期延误说明:</span>
				<span class="wap_value">${templateBean.durationDelaysDesc}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">应扣工期违约金:</span>
				<span class="wap_value">${templateBean.durationPenalty}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">质量验收说明:</span>
				<span class="wap_value">${templateBean.qualityAcceptDesc}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">应扣质量违约金:</span>
				<span class="wap_value">${templateBean.qualityPenalty}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">其他违约说明:</span>
				<span class="wap_value">${templateBean.otherRenegeDesc}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">应扣其他违约金:</span>
				<span class="wap_value">${templateBean.otherRenegePenalty}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">实际缴纳履约保证金(元):</span>
				<span class="wap_value">${templateBean.realPerformBond}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">应付履约保证金金额:</span>
				<span class="wap_value">${templateBean.payPerformBond}</span>
		</div>
		<div class="div_label">
				<span class="wap_label">【附件】</span>
				<div class="div_row">
					<span class="wap_title">工程竣工遗留问题确认单:</span>
					<div id="show_leaveProblemsConformId"></div>
					<script type="text/javascript">
					showUploadedFile('${templateBean.leaveProblemsConformId}','leaveProblemsConformId','${canEdit}');
					</script>
				</div>
				<div class="div_row">
					<span class="wap_title">收据凭证:</span>
					<div id="show_receiptVerifyId"></div>
					<script type="text/javascript">
					showUploadedFile('${templateBean.receiptVerifyId}','receiptVerifyId','${canEdit}');
					</script>
				</div>
				<div class="div_row">
					<span class="wap_title">保修协议书:</span>
					<div id="show_warrantyAgreementId"></div>
					<script type="text/javascript">
					showUploadedFile('${templateBean.warrantyAgreementId}','warrantyAgreementId','${canEdit}');
					</script>
				</div>
		</div>
</div>
