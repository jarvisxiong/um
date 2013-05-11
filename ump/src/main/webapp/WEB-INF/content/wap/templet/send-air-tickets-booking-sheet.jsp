<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 外派人员机票订购申请单 --%>
<%@ include file="template-var.jsp"%>
<div id="billContent">
	<div class="div_row">
			<span class="wap_title">出行人员:</span>
			<span class="wap_value">${templateBean.userName}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">起迄地:</span>
			<span class="wap_value">${templateBean.beginEndPlace}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">身份证号:</span>
			<span class="wap_value">${templateBean.idCardNo}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">出行事由 	:</span>
			<span class="wap_value">${templateBean.tripCause}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">出行日期:</span>
			<span class="wap_value">${templateBean.tripDate}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">费用承担部门:</span>
			<span class="wap_value">${templateBean.costDept}</span>
	</div>
</div>
		