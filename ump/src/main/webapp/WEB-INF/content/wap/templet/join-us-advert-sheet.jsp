<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!--招聘广告审批表(总部/项目公司)-->

<div id="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">名称:</span>
			<span class="wap_value">${templateBean.adverName}</span>
		</div>	
		<div class="div_row">
			<span class="wap_title">发布方式及范围:</span>
			<span class="wap_value">${templateBean.postScopDesc}</span>
		</div>	
		<div class="div_row">
			<span class="wap_title">内容简述:</span>
			<span class="wap_value">${templateBean.adverDesc}</span>
		</div>	
</div>
