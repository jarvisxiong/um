<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 百货业态规划审批表 --%>

<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
		<span class="wap_title">名称:</span>
		<span class="wap_value">${templateBean.name}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">类别:</span>
		<div><s:checkbox name="templateBean.marketResearch" cssClass="group"></s:checkbox><span>市场调查报告及分析</span></div>
		<div><s:checkbox name="templateBean.categoryPlan" cssClass="group"></s:checkbox><span>品类规划方案</span></div>
		
	</div>
	<div class="div_row">
		<span class="wap_title">内容简述(详细内容附后):</span>
		<span class="wap_value">${templateBean.desc}</span>
	</div>
</div>

