<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page import="com.hhz.ump.util.JspUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page import="com.hhz.ump.util.LoginUtil"%>


<%-- 办公资产出售/报废/遗失申请单 --%>
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	
	<div class="div_row">
		<span class="wap_title">设备名称:</span>
		<span class="wap_value">${templateBean.assetName}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">分类编号:</span>
		<span class="wap_value">${templateBean.typeNo}</span>
	</div>
	
	
	<div class="div_row">
		<span class="wap_title">使用部门:</span>
		<span class="wap_value">${templateBean.useDept}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">使用责任人:</span>
		<span class="wap_value">${templateBean.useUser}</span>
	</div>
	
	
	<div class="div_row">
		<span class="wap_title">规格型号:</span>
		<span class="wap_value">${templateBean.modelNo}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">数量:</span>
		<span class="wap_value">${templateBean.assetNum}</span>
	</div>
	
	
	<div class="div_row">
		<span class="wap_title">购置时间:</span>
		<span class="wap_value">${templateBean.buyDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">使用年限:</span>
		<span class="wap_value">${templateBean.userYear}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">金额(元):</span>
		<span class="wap_value">${templateBean.totalMoney}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">出售/报废/遗失原因:</span>
		<span class="wap_value">${templateBean.saleCause}</span>
	</div>
</div>