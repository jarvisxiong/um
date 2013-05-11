<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!-- 加班补休申请表-->

<div class="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">中心/区域:</span>
			<span class="wap_value">${templateBean.centerOrgName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">部门:</span>
			<span class="wap_value">${templateBean.deptOrgName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">姓名:</span>
			<span class="wap_value">${templateBean.userName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">职位:</span>
			<span class="wap_value">${templateBean.positionName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">职级:</span>
			<span class="wap_value">${templateBean.positionLevelName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">补休日期:</span>
			<span class="wap_value">${templateBean.resetDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">从(时):</span>
			<span class="wap_value">${templateBean.resetStartHour}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">至(时):</span>
			<span class="wap_value">${templateBean.resetEndHour}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">时数:</span>
			<span class="wap_value">${templateBean.restHours}</span>
		</div>
		<div class="div_label">
			<span class="wap_label">【补偿替代】<red>(可左右拖动查看)</red></span>
			<div class="div_scroll">
			<table>
				<tr>
					<td class="wap_title" rowspan="4">补偿替代</td>
					<td class="wap_title">加班日期</td>
					<td class="wap_title" colspan="3">加班时数</td>
					<td class="wap_title" colspan="3">上次补休后剩余时数</td>
				</tr>
				<tr>
					<td class="wap_title">${templateBean.overTimeDate1}</td>
					<td class="wap_title" colspan="4">${templateBean.overTimeHours1}</td>
					<td class="wap_title" colspan="2">${templateBean.lastLeaveHours1}</td>
				</tr>
				<tr>
					<td class="wap_title">${templateBean.overTimeDate2}</td>
					<td colspan="4">${templateBean.overTimeHours2}</td>
					<td class="wap_title" colspan="2">${templateBean.lastLeaveHours2}</td>
				</tr>
				<tr>
					<td class="wap_title">${templateBean.overTimeDate3}</td>
					<td colspan="4">${templateBean.overTimeHours3}</td>
					<td class="wap_title" colspan="2">${templateBean.lastLeaveHours3}</td>
				</tr>
				<tr>
					<td class="wap_title" colspan="2" class="wap_title">累计时数</td>
					<td class="wap_title" colspan="4">${templateBean.totalOverTimeHours}</td>
					<td class="wap_title" colspan="2">${templateBean.totalLeaveHours}</td>
				</tr>
			</table>
			</div>
		</div>
		<div class="div_row">
				<span class="wap_title">补休原因: </span>
				<span class="wap_value">${templateBean.restReasonDesc }</span>
		</div>
</div>

