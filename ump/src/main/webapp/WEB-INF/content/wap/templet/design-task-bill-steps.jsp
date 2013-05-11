<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--	标准审批表-项目名称-自由步骤	-->
<div id="billContent">
	
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">设计单位:</span>
			<span class="wap_value">${templateBean.designUnit}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">设计任务提要(设计任务书附后):</span>
			<span class="wap_value">${templateBean.remark}</span>
		</div>
</div>
