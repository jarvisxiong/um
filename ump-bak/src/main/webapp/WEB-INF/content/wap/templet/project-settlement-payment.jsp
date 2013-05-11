<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 工程结算款付款审批表 --%>

<div id="billContent">
	<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
	</s:set>
	<div class="div_row">
			<span class="wap_title">审批权限:<s:checkbox name="templateBean.hotel"  cssClass="group"></s:checkbox>与酒店有关</span>			
	</div>   
	<div class="div_row">
		<span class="wap_title">预算范围:<s:checkbox name="templateBean.inFlag"  cssClass="group"></s:checkbox>预算内
		&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="templateBean.outFlag"  cssClass="group"></s:checkbox>预算外
		</span>
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
			<span class="wap_title">保修期起止时间:</span>
			<span class="wap_value">${templateBean.guarBeginDate}  至 ${templateBean.guarEndDate} </span>
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
	
	<div class="div_label">
		 <span class="wap_label">【计算公式】</span>
		   	<div class="div_row">
				<span class="wap_title">结算总价(含甲供料):</span>
				<span class="wap_value">${templateBean.fuClearPrice}</span>
			</div>
		   	<div class="div_row">
				<span class="wap_title">预留：质保金:</span>
				<span class="wap_value">${templateBean.fuGuaranteeMoney}</span>
			</div>
		   	<div class="div_row">
				<span class="wap_title">扣：甲供料款(按暂定价):</span>
				<span class="wap_value">${templateBean.fuMatieralPrice}</span>
			</div>
		   	<div class="div_row">
				<span class="wap_title">扣：已直接付款累计:</span>
				<span class="wap_value">${templateBean.fuCurrentPay}</span>
			</div>
		   	<div class="div_row">
				<span class="wap_title">扣：其他扣款或代付款累计 	:</span>
				<span class="wap_value">${templateBean.fuCurrentAdd}</span>
			</div>
		   	<div class="div_row">
				<span class="wap_title">本次付款金额:</span>
				<span class="wap_value">${templateBean.fuPayMoney}</span>
			</div>
		   	<div class="div_row">
				<span class="wap_title">备注:</span>
				<span class="wap_value">${templateBean.description}</span>
			</div>
				<div class="div_row">
				<span class="wap_title">工程结算审批表:</span>
				<div id="show_proSettleId"></div>				
					<script type="text/javascript">
						showUploadedFile('${templateBean.proSettleId}','proSettleId','${canEdit}');
					</script>
				</div>
	</div> 
</div>


