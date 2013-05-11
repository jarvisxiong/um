<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--车辆补贴报销申请单-->
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
	
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">中心:</span>
			<span class="wap_value">${templateBean.centerName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">职级:</span>
			<span class="wap_value">${templateBean.positionName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">申请人姓名:</span>
			<span class="wap_value">${templateBean.userName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">申请额度:</span>
			<span class="wap_value">${templateBean.applyLimit}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">开始报销日期:</span>
			<span class="wap_value">${templateBean.startDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">申请理由:</span>
			<span class="wap_value">${templateBean.applyReason}</span>
		</div>
