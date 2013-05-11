<jsp:directive.page contentType="text/html;charset=UTF-8"/>
<jsp:directive.include file="/common/taglibs.jsp"/>
<jsp:directive.page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"/>
<!--计划内多经点位审批表  -->
<div id="billContent">
		
	<jsp:directive.include file="template-var.jsp"/>	
  	<%--合同文本库引用 start --%>
	<%@ include file="bid-contract-base.jsp"%>
	<%--合同文本库引用 end --%>
	<div class="div_row">
		<span class="wap_title">预算:</span>
		<div><s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox><span>预算外</span></div>
		<div><s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox><span>预算内</span></div>
	</div>		
	<div class="div_row">
		<span class="wap_title">项目:</span>
		<span class="wap_value">${templateBean.projectName}</span>
	</div>	
		<div class="div_row">
		<span class="wap_title">审请商户:</span>
		
		<div><s:checkbox name="templateBean.inCustomer" cssClass="group"/><span>场内商户</span></div>
		<div><s:checkbox name="templateBean.outCustomer" cssClass="group"/><span>场外</span></div>
        <span class="wap_title">名称:</span><span class="wap_value">${templateBean.customerName}</span>
	</div>	
		<div class="div_row">
		<span class="wap_title">区域:</span>
		<span class="wap_value">${templateBean.area}</span>
	</div>		
	<div class="div_row">
		<span class="wap_title">点位类型:</span>
		<span class="wap_value">${templateBean.locationType}</span>
	</div>		
	<div class="div_row">
		<span class="wap_title">点位编号:</span>
		<span class="wap_value">${templateBean.locationNum}</span>
	</div>
	<div class="div_table_row">
	<span>序号/科目/标准	/审批条件/批注</span>	
	
	<div class="orgDiv">
		1./经营业态/${templateBean.manageType1}/${templateBean.manageType2}/${templateBean.manageType3}
	</div>
	 <div class="orgDiv">
		2./租赁面积/${templateBean.rentType1}/${templateBean.rentType2}/${templateBean.rentType3}
 	</div>
	<div class="orgDiv">
		3./装修标准/${templateBean.fillerType1}/${templateBean.fillerType2}/${templateBean.fillerType3}
	</div>
	<div class="orgDiv">
		4./合约期限/${templateBean.contDateType1}/${templateBean.contDateType2}/${templateBean.contDateType3}
	</div>
	<div class="orgDiv">
		5./免租期/${templateBean.rentFreePeriodType1}/${templateBean.rentFreePeriodType2}/${templateBean.rentFreePeriodType3}
	</div>
	<div class="orgDiv">
		6./租金标准（元/月）/${templateBean.rentStandrdType1}/${templateBean.rentStandrdType2}/${templateBean.rentStandrdType3}
	</div>
	<div class="orgDiv">
		7./租赁期总收益（元）/${templateBean.rentIncomeType1}/${templateBean.rentIncomeType2}/${templateBean.rentIncomeType3}	
	</div>
	<div class="orgDiv">
		8./付款方式/${templateBean.methodPaymentType1}/${templateBean.methodPaymentType2}/${templateBean.methodPaymentType3}	
	</div>
	<div class="orgDiv">
		9./经营保证金/${templateBean.operatingMarginType2}/${templateBean.operatingMarginType2}/${templateBean.operatingMarginType3}
		</div>
	<div class="orgDiv">
		10./交付时间	/${templateBean.handOverDateType1}/${templateBean.handOverDateType2}/${templateBean.handOverDateType3}
	</div>
	<div class="orgDiv">
		11./装修期/${templateBean.fillerPeriodType1}/${templateBean.fillerPeriodType2}/${templateBean.fillerPeriodType3}
		</div>
	<div class="orgDiv">
	12./其它条件/${templateBean.otherCondType}
	</div>

	</div>	
	
</div>
	