<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page import="com.hhz.ump.util.JspUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page import="com.hhz.ump.util.LoginUtil"%>



<%-- 商业付款审批表(eg: 商业公司工程改造付款审批表) --%>
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	
	<div class="div_row">
		<div><s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox><span>月度预算内</span></div>
		<div><s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox><span>月度预算外</span></div>
	</div>
	 
	<s:if test="authTypeCd=='BLSY_CWGL_FKGL_60'">
	<div class="div_row">
		<div><s:checkbox name="templateBean.hasLandCompany" cssClass="group"></s:checkbox><span>有地产公司</span></div>
	</div>
	</s:if>
	
	
	<div class="div_row">
		<span class="wap_title">合同编号:</span>
		<span class="wap_value">${templateBean.contactNo}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">合同名称:</span>
		<span class="wap_value">${templateBean.contactName}</span>
	</div>
	
	
	<div class="div_row">
		<span class="wap_title">项目名称:</span>
		<span class="wap_value">${templateBean.projectName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">付款单位:</span>
		<span class="wap_value">${templateBean.payUnitName}</span>
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
		<span class="wap_title">已付合同款（元）:</span>
		<span class="wap_value">${templateBean.paidTotalAmt}</span>
	</div>　
	<div class="div_row">
		<span class="wap_title">本次付款申请金额（元）:</span>
		<span class="wap_value">${templateBean.applyAmt}</span>
	</div>　

</div>