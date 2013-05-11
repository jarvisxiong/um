<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--培训请假单-->

<div class="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
		<span class="wap_title">姓名:</span>
		<span class="wap_value">${templateBean.userName}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">部门:</span>
		<span class="wap_value">${templateBean.deptName}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">职务:</span>
		<span class="wap_value">${templateBean.positionName}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">培训项目:</span>
		<span class="wap_value">${templateBean.trainName}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">培训日期:</span>
		<span class="wap_value">${templateBean.trainDate}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">请假事由:</span>
		<span class="wap_value">${templateBean.reasonDesc}</span>
		</div>
</div>
