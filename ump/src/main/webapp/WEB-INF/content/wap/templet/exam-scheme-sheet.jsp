<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 样板房方案审批表(eg:定价) -->
<div class="billContent">
	
	<%@ include file="template-var.jsp"%>
	
	<div class="div_row">
		<span class="wap_title">项目名称:</span>
		<span class="wap_value">${templateBean.projectName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">样板房位置:</span>
		<span class="wap_value">${templateBean.buildName}栋${templateBean.floorName}层${templateBean.unitName}单元${templateBean.storeName}住宅/店面</span>
	</div>
	<div class="div_row">
		<span class="wap_title">内容简述(详细内容附后):</span>
		<span class="wap_value">${templateBean.contentDesc}</span>
	</div>
	
</div>
