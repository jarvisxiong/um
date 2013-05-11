<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page import="com.hhz.ump.util.JspUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page import="com.hhz.ump.util.LoginUtil"%>


<%-- 机票退票确认单 --%>
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
		<span class="wap_title">出行人员:</span>
		<span class="wap_value">${templateBean.userName}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">出行时间:</span>
		<span class="wap_value">${templateBean.tripDate}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">电子客票号:</span>
		<span class="wap_value">${templateBean.eticketNo}</span>
	</div>
	
	
	<div class="div_row">
		<span class="wap_title">应退航程:</span>
		<span class="wap_value">${templateBean.backVovage}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">退票费用:</span>
		<span class="wap_value">${templateBean.backAmount}</span>
	</div>
	
	
	<div class="div_row">
		<span class="wap_title">退票原因:</span>
		<span class="wap_value">${templateBean.backCause}</span>
	</div>
</div>
