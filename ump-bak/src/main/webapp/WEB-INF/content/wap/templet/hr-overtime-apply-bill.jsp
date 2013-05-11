<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--加班申请表-->

<div class="billContent">
	
		<%@ include file="template-var.jsp"%>
		<s:set var="conItemCount"><s:property value="templateBean.otherProperties.size()"/></s:set>
		<div class="div_row">
			<span class="wap_title">中心/区域:</span>
			<span class="wap_value">${templateBean.centerName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">部门:</span>
			<span class="wap_value">${templateBean.deptName}</span>
		</div>
		<s:iterator value="templateBean.otherProperties" var="item" status="s">
		<div class="div_label">
		<div class="div_row">
			<span class="wap_title">姓名:</span>
			<span class="wap_value">${item.applyUserName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">职位:</span>
			<span class="wap_value">${item.positionName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">职级:</span>
			<span class="wap_value">${item.positionLevel}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">加班日期:</span>
			<span class="wap_value">${item.overtimeDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">从(时):</span>
			<span class="wap_value">${item.fromTime}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">至(时):</span>
			<span class="wap_value">${item.toTime}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">时数:</span>
			<span class="wap_value">${item.totalHour}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">加班类型:</span>
			<div><s:checkbox name="templateBean.otherProperties[%{#s.index}].sendType1"  cssClass="group"></s:checkbox><span>休息日加班</span></div>
			<div><s:checkbox name="templateBean.otherProperties[%{#s.index}].sendType2"  cssClass="group"></s:checkbox><span>法定假日加班</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">补贴方式:</span>
			<div><s:checkbox name="templateBean.otherProperties[%{#s.index}].subsidy1"  cssClass="group"></s:checkbox><span>安排调休</span></div>
			<div><s:checkbox name="templateBean.otherProperties[%{#s.index}].subsidy2"  cssClass="group"></s:checkbox><span>支付工资</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">加班原因:</span>
			<span class="wap_value">${item.reason}</span>
		</div>
		</div>
	</s:iterator>
	
</div>
