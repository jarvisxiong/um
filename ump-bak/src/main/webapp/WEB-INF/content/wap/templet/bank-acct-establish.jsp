<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page import="com.hhz.ump.util.JspUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page import="com.hhz.ump.util.LoginUtil"%>


<%--银行账户开户审批表 --%>
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	
	
	<div class="div_row">
		<span class="wap_title">申请公司全称:</span>
		<span class="wap_value">${templateBean.approveCompany}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">开户银行全称:</span>
		<span class="wap_value">${templateBean.estaAcctName}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">账户类型:</span>
	</div>
	
	<div class="div_row">
		<div><s:checkbox name="templateBean.basicAccount" cssClass="group"></s:checkbox><span>基本户</span></div>
		<div><s:checkbox name="templateBean.commonAccount" cssClass="group"></s:checkbox><span>一般户</span></div>
		<div><s:checkbox name="templateBean.loanAccount" cssClass="group"></s:checkbox><span>贷款户</span></div>
		<div><s:checkbox name="templateBean.earnestMoneyAccount" cssClass="group"></s:checkbox><span>保证金户</span></div>
		<div><s:checkbox name="templateBean.fixedDepositAccount" cssClass="group"></s:checkbox><span>定期存款户</span></div>
		<div><s:checkbox name="templateBean.verificationAccount" cssClass="group"></s:checkbox><span>验资户</span></div>
	</div>
	
	
	<div class="div_row">
		<span class="wap_title">币别:</span>
		<span class="wap_value">${templateBean.currencyType}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">银行地址:</span>
		<span class="wap_value">${templateBean.bankAddr}</span>
	</div>
	
	
	<div class="div_row">
		<span class="wap_title">银行联系人:</span>
		<span class="wap_value">${templateBean.bankContactor}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">联系方式:</span>
		<span class="wap_value">${templateBean.contactorType}</span>
	</div>
	
	
	<div class="div_row">
		<span class="wap_title">申请事:</span>
		<span class="wap_value">${templateBean.approveReason}</span>
	</div>
</div>