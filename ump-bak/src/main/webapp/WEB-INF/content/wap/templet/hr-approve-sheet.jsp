<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--人事审批表 --%>

<div class="billContent">
	
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
		<span class="wap_title">姓    名:</span>
		<span class="wap_value">${templateBean.userName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">岗位名称:</span>
		<span class="wap_value">${templateBean.position}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">所在部门:</span>
		<span class="wap_value">${templateBean.dept}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">职    级:</span>
		<span class="wap_value">${templateBean.positionLevel}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">入职日期:</span>
		<span class="wap_value">${templateBean.entryDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">员工编号:</span>
		<span class="wap_value">${templateBean.jobNumber}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">审核种类:</span>
		<div><s:checkbox name="templateBean.auditType1"  cssClass="group"></s:checkbox><span>试用</span></div>
		<div><s:checkbox name="templateBean.auditType2"  cssClass="group"></s:checkbox><span>转正</span></div>
		<div><s:checkbox name="templateBean.auditType3"  cssClass="group"></s:checkbox><span>任免</span></div>
		<div><s:checkbox name="templateBean.auditType4"  cssClass="group"></s:checkbox><span>调薪</span></div>
		<div><s:checkbox name="templateBean.auditType5"  cssClass="group"></s:checkbox><span>奖励</span></div>
		<div><s:checkbox name="templateBean.auditType6"  cssClass="group"></s:checkbox><span>处罚</span></div>
		<div><s:checkbox name="templateBean.auditType7"  cssClass="group"></s:checkbox><span>辞退</span></div>
		<div><s:checkbox name="templateBean.auditType8"  cssClass="group"></s:checkbox><span>离职</span></div>
	</div>
	<div class="div_row">
		<span class="wap_title">简要说明:</span>
		<span class="wap_value">${templateBean.simpleDesc}</span>
	</div>
</div>
