<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--外地人员面试费用确认单-->

<div class="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">应聘人员:</span>
			<span class="wap_value">${templateBean.userName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">起止地:</span>
			<span class="wap_value">${templateBean.starStopPlace}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">应聘岗位:</span>
			<span class="wap_value">${templateBean.positionName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">来沪日期:</span>
			<span class="wap_value">${templateBean.shangHaiDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">抵达时间:</span>
			<span class="wap_value">${templateBean.arriveDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">备注:</span>
			<span class="wap_value">${templateBean.remark}</span>
		</div>
</div>
