<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 材料设备结算款付款审批表 --%>
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
	  <span class="wap_value">${templateBean.projectName}(${templateBean.projectPeriod})期</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">付款单位:</span>
	  <span class="wap_value">${templateBean.paymentUnit}</span>
    </div>
	<div class="div_label">
	  <span class="wap_title">收款人(乙方)信息:</span>
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
	  <span class="wap_title">保修期起止时间:</span>
	  <span class="wap_value">${templateBean.guarBeginDate}至${templateBean.guarEndDate}</span>
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
	  <span class="wap_title">本次付款申请金额(元):</span>
	  <span class="wap_value">${templateBean.applyAmount}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">结算总价:</span>
	  <span class="wap_value">${templateBean.clearPrice}</span>
    </div>
    <div class="div_label">
	    <span class="wap_label">计算公式</span>
	    <div class="div_row">
		  <span class="wap_title">结算总价:</span>
		  <span class="wap_value">${templateBean.fuClearPrice}</span>
	    </div>
	    <div class="div_row">
	      <span class="wap_title">预留：质保金:</span>
	      <span class="wap_value">${templateBean.fuGuaranteeMoney}</span>
	    </div>
	     <div class="div_row">
	      <span class="wap_title">扣：已付款:</span>
	      <span class="wap_value">${templateBean.fuCurrentPay}</span>
	    </div>
	     <div class="div_row">
	      <span class="wap_title">扣：其他应扣款 	:</span>
	      <span class="wap_value">${templateBean.fuCurrentAdd}</span>
	    </div>
	     <div class="div_row">
	      <span class="wap_title">本次付款申请:</span>
	      <span class="wap_value">${templateBean.fuPayMoney}</span>
	    </div>
	    <div class="div_row">
	      <span class="wap_title">需说明事项:</span>
	      <span class="wap_value">${templateBean.description}</span>
	    </div>
	    <div class="div_label">
		    <div class="div_row">
		      <span class="wap_title">材料设备结算审批表:</span>
		     <div id="show_materialSettlementApproveId"></div>
					<script type="text/javascript">
					showUploadedFile('${templateBean.materialSettlementApproveId}','materialSettlementApproveId','${canEdit}');
					</script>
		    </div>
	    </div>
	    
	</div>
</div>
