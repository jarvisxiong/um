<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 营销费用限额审批表-->
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
		<span class="wap_title">费用限额(元):</span>
		<span class="wap_value">${templateBean.costLimit}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">费用用途:</span>
		<div><s:checkbox name="templateBean.marketResearch" cssClass="group"></s:checkbox><span>推广</span></div>
		<div><s:checkbox name="templateBean.market" cssClass="group"></s:checkbox><span>销售</span></div>
		<div><s:checkbox name="templateBean.other" cssClass="group"></s:checkbox><span>其它</span></div>
	</div>
	<div class="div_row">
		<span class="wap_title">营销内容及限额说明:</span>
		<span class="wap_value">${templateBean.marketContent}</span>
	</div>
</div>
