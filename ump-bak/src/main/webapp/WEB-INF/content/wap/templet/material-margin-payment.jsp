<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 工程、材料设备质保金付款审批表 --%>

<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
	    <span class="wap_title">审批权限:</span>
		<div><s:checkbox name="templateBean.hotel" cssClass="group"></s:checkbox><span>与酒店有关</span></div>
		<div><s:checkbox name="templateBean.hasEstate" cssClass="group"></s:checkbox><span>有商业公司</span></div>
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
		<span class="wap_value">${templateBean.projectName}(${templateBean.projectPeriod})期</span>
	</div>
	<div class="div_row">
		<span class="wap_title">付款单位:</span>
		<span class="wap_value">${templateBean.paymentUnit}</span>
	</div>
	<div class="div_label">
	    <span class="wap_label">收款人(乙方)信息</span>
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
		<span class="wap_title">保修期起止时间:</span>
		<span class="wap_value">${templateBean.guarBeginDate}至${templateBean.guarEndDate }</span>
	</div>
	<div class="div_row">
		<span class="wap_title">合同总价:</span>
		<span class="wap_value">${templateBean.totalPrice}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">结算总价:</span>
		<span class="wap_value">${templateBean.clearPrice}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">本次应付质保金(元):</span>
		<span class="wap_value">${templateBean.marginPay}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">合同总价:</span>
		<span class="wap_value">${templateBean.totalPrice}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">合同总价:</span>
		<span class="wap_value">${templateBean.totalPrice}</span>
	</div>
	<div class="div_label">
	    <span class="wap_label">计算公式</span>
	    <div class="div_row">
	      <span class="wap_title">实际预留质保金:</span>
	      <span class="wap_value">${templateBean.fuRealGuarantee}</span>
	    </div>
	    <div class="div_row">
	      <span class="wap_title">扣：保修应扣款:</span>
	      <span class="wap_value">${templateBean.fuCutPayment}</span>
	    </div>
	    <div class="div_row">
	      <span class="wap_title">扣：预留保修金:</span>
	      <span class="wap_value">${templateBean.fuLeaveGuarantee}</span>
	    </div>
	    <div class="div_row">
	      <span class="wap_title">本次应付质保金:</span>
	      <span class="wap_value">${templateBean.fuPayMoeny}</span>
	    </div>
	</div>
	<div class="div_row">
		<span class="wap_title">需说明事项:</span>
		<span class="wap_value">${templateBean.description}</span>
	</div>
	<div class="div_label">
	    <span class="wap_label">【附件】</span>
	    <span class="wap_title">工程或材料设备结算款付款审批表:</span>
	    <div id="show_mateSettleId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.mateSettleId}','mateSettleId','${canEdit}');
				</script>
		<span class="wap_title">结算审批表:</span>
	    <div id="show_settleApproveId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.settleApproveId}','settleApproveId','${canEdit}');
				</script>
	</div>
</div>
