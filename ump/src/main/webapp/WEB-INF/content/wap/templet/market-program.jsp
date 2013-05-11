<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--营销方案审批表-->
<div id="billContent">
<%@ include file="template-var.jsp"%>
	<div class="div_row">
		<span class="wap_title">标题:</span>
		<span class="wap_value">${templateBean.titleName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">项目名称:</span>
		<span class="wap_value">${templateBean.projectName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">方案类别:</span>
		<div><s:checkbox name="templateBean.totalMarketProgram" cssClass="group"></s:checkbox><span>整体营销方案</span></div>
		<div><s:checkbox name="templateBean.stageMarketProgram" cssClass="group"></s:checkbox><span>阶段营销方案</span></div>
	</div>
	<div class="div_row">
	    <div><s:checkbox name="templateBean.subscriptionProgram" cssClass="group"></s:checkbox><span>认购方案</span></div>
		<div><s:checkbox name="templateBean.openProgram" cssClass="group"></s:checkbox><span>开盘方案</span></div>
	    <div><s:checkbox name="templateBean.additionPush" cssClass="group"></s:checkbox><span>房源加推</span></div>
	    <div><s:checkbox name="templateBean.extendProgram" cssClass="group"></s:checkbox><span>推广方案</span></div>
		<div><s:checkbox name="templateBean.sellPlan" cssClass="group"></s:checkbox><span>销售方案</span></div>
	    <div><s:checkbox name="templateBean.planProgram" cssClass="group"></s:checkbox><span>策划方案</span></div>
	</div>
	<div class="div_row">
		<span class="wap_title">营销内容及限额说明:</span>
		<span class="wap_value">${templateBean.programContent}</span>
	</div>
</div>
