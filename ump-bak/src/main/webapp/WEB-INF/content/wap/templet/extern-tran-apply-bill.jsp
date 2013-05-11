<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!--赴外培训申请表-->
<div class="billContent" >
	
	<%@ include file="template-var.jsp"%>
	
	<div class="div_row">
		<span class="wap_title">申请中心:</span>
		<span class="wap_value">${templateBean.centerOrgName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">培训对象:</span>
		<span class="wap_value">${templateBean.tranObjectName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">培训课程:</span>
		<span class="wap_value">${templateBean.tranClassName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">培训日期:</span>
		<span class="wap_value">${templateBean.tranDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">培训地点:</span>
		<span class="wap_value">${templateBean.tranAddr}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">培训机构:</span>
		<span class="wap_value">${templateBean.tranOrgName}</span>
	</div>
	<div class="div_label">
		<span class="wap_label">【培训预算】</span>
		<div class="div_row">
			<span class="wap_title">1.培训费:</span>
			<span class="wap_value">${templateBean.tranCostAmt}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">2.交通费:</span>
			<span class="wap_value">${templateBean.vehicleCostAmt}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">3.住宿费:</span>
			<span class="wap_value">${templateBean.settleCostAmt}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">4.其他费:</span>
			<span class="wap_value">${templateBean.otherCostAmt}</span>
		</div>
		<div class="div_row">
			<span class="wap_title" style="font-weight: bold;">总计费用:</span>
			<span class="wap_value">${templateBean.totoalCostAmt}</span>
		</div>
	</div>
	<div class="div_row">
		<span class="wap_title">申请培训原因:</span>
		<span class="wap_value">${templateBean.reasonDesc}</span>
	</div>
	<div class="div_label">
		<span class="wap_label">【资料附件】</span>
		<div class="div_row">
			<span class="wap_title">“邀请函”、“课程内容介绍”等资料</span>
			<div id="show_attachmentId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.attachmentId}','attachmentId','false');
			</script>
		</div>
	</div>
	
</div>
