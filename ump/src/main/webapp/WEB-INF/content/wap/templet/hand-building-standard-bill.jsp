<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--交楼标准审批表-->

<div class="billContent">
	
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
		<span class="wap_title">项目名称:</span>
		<span class="wap_value">${templateBean.projectName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">物业范围:</span>
		<span class="wap_value">${templateBean.propertyScope}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">内容简述（详细内容附后）:</span>
		<span class="wap_value">${templateBean.contentDesc}</span>
	</div>
	
</div>
