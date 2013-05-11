<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--设计变更审批表 --%>

<div id="billContent">
	
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">工程名称:</span>
			<span class="wap_value">${templateBean.engineeringName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">原图纸号:</span>
			<span class="wap_value">${templateBean.paperNumber}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">子项名称:</span>
			<span class="wap_value">${templateBean.subName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">增加或减少的费用(元):</span>
			<span class="wap_value">${templateBean.changeMoney}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">累计变更比例:</span>
			<span class="wap_value">${templateBean.addUpRate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">累计变更额(元):</span>
			<span class="wap_value">${templateBean.addUpMoney}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">变更内容及原因:</span>
			<span class="wap_value">${templateBean.contentAndReason}</span>
		</div>
</div>
