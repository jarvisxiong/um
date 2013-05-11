<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<!--文件提取申请单-->
<div class="billContent" >
	
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
		<span class="wap_title">申请中心:</span>
		<span class="wap_value">${templateBean.applyCenterName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">申请日期:</span>
		<span class="wap_value">${templateBean.applyDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">归还日期:</span>
		<span class="wap_value">${templateBean.returnDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">提取文档明细:</span>
		<span class="wap_value">${templateBean.fileDetail}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">事由说明:</span>
		<span class="wap_value">${templateBean.reason}</span>
	</div>

</div>
