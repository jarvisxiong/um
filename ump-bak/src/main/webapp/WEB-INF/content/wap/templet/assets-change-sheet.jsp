<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page import="com.hhz.ump.util.JspUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page import="com.hhz.ump.util.LoginUtil"%>

<%-- 办公资产维修/更换申请单 --%>
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	

	<div class="div_row">
		<span class="wap_title">申请日期:</span>
		<span class="wap_value">${templateBean.applDate}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">单号:</span>
		<span class="wap_value">${templateBean.applNo}</span>
	</div>
	
	
	<div class="div_row">
		<span class="wap_title">申请人姓名:</span>
		<span class="wap_value">${templateBean.userName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">所在部门:</span>
		<span class="wap_value">${templateBean.dept}</span>
	</div>
	
	
	<div class="div_row">
		<span class="wap_title">金额(元):</span>
		<span class="wap_value">${templateBean.totalMoney}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">资产编号:</span>
		<span class="wap_value">${templateBean.assetNo}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">购置时间:</span>
		<span class="wap_value">${templateBean.buyDate}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">是否在预算内:</span>
		<div><s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox><span>预算内</span></div>
		<div><s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox><span>预算外</span></div>
	</div>
	
	<div class="div_row">
		<span class="wap_title">申请原因:</span>
		<span class="wap_value">${templateBean.applCause}</span>
	</div>
</div>