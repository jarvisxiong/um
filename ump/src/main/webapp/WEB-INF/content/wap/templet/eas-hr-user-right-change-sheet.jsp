<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--EAS用户权限变动申请表--%>

<div class="billContent">
<%@ include file="template-var.jsp"%>
  <div class="div_row">
		<span class="wap_title">申请人:</span>
		<span class="wap_value">${templateBean.applyUserName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">申请人帐号:</span>
		<span class="wap_value">${templateBean.applyUserAccounts}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">申请日期:</span>
		<span class="wap_value">${templateBean.applyUserDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">所在单位/部门/职位:</span>
		<span class="wap_value">${templateBean.deptName}</span>
	</div>
	<div class="div_label">
	  <span class="wap_title">变更类型:</span>
	 <div><s:checkbox name="templateBean.ctFinancePeople" cssClass="group"></s:checkbox><span class="wap_value">财务人员</span></div>
	 <div><s:checkbox  name="templateBean.ctAddRight" cssClass="group"></s:checkbox><span class="wap_value">增加权限</span></div>
	 <div><s:checkbox  name="templateBean.ctDelRight" cssClass="group"></s:checkbox><span class="wap_value">删除权限</span></div>
	</div>
    <div class="div_label">
	  <span class="wap_title">变动原因:</span>
	 <div><s:checkbox name="templateBean.personnelChange" cssClass="group"></s:checkbox><span class="wap_value">人事异动</span></div>
	 <div> <span class="wap_value">异动前公司/部门/职位：${templateBean.frontPersonnelChange}</span></div>
	 <div> <span class="wap_value">异动后公司/部门/职位：${templateBean.afterPersonnelChange}</span></div>
	 <div><span class="wap_value">增加单位/部门：${templateBean.addUnitValue}</span></div>
	 <div><span class="wap_value">其它（请作其它原因说明）：${templateBean.otherValue }</span></div>
	</div>
	
	<div class="div_row">
		<span class="wap_title">申请人岗位职责描述:</span>
		<span class="wap_value">${templateBean.postResponsibilityDes}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">组织范围:</span>
		<span class="wap_value">${templateBean.groupScope}</span>
	</div>
	<div class="div_label">
	  <span class="wap_title">权限模块:</span>
	  <div><s:checkbox name="templateBean.abilityQualityModel" cssClass="group"></s:checkbox><span class="wap_value">能力素质模型</span></div>
	  <div><s:checkbox name="templateBean.performanceManage" cssClass="group"></s:checkbox><span class="wap_value">绩效管理</span></div>
	  <div><s:checkbox name="templateBean.groupPlanning" cssClass="group"></s:checkbox><span class="wap_value">组织规划</span></div>
	  <div><s:checkbox name="templateBean.personnelManage" cssClass="group"></s:checkbox><span class="wap_value">职员管理</span></div>
	  <div><s:checkbox name="templateBean.workManage" cssClass="group"></s:checkbox><span class="wap_value">考勤管理</span></div>
	  <div><s:checkbox name="templateBean.jobSelect" cssClass="group"></s:checkbox><span class="wap_value">招聘选拔</span></div>
	  
	  <div><s:checkbox name="templateBean.trainDevelop" cssClass="group"></s:checkbox><span class="wap_value">培训发展</span></div>
	  <div><s:checkbox name="templateBean.salaryDesign" cssClass="group"></s:checkbox><span class="wap_value">薪酬设计</span></div>
	  <div><s:checkbox name="templateBean.salaryCheck" cssClass="group"></s:checkbox><span class="wap_value">薪酬核算</span></div>
	  <div><s:checkbox name="templateBean.socialSecurity" cssClass="group"></s:checkbox><span class="wap_value">社保副利</span></div>
	  
	  <div><s:checkbox name="templateBean.seachReport" cssClass="group"></s:checkbox><span class="wap_value">搜索报表</span></div>
	  <div><s:checkbox name="templateBean.mobileHRGuide" cssClass="group"></s:checkbox><span class="wap_value">移动HR向导</span></div>
	  <div><s:checkbox name="templateBean.myWorkbench" cssClass="group"></s:checkbox><span class="wap_value">我的工作台</span></div>
	</div>
	<div class="div_row">
	 <span class="wap_title">工资项目权限:</span>
		<span class="wap_value">${templateBean.salaryProjectRight}</span>
	</div>
	<div class="div_row">
	 <span class="wap_title">申请权限内容描述:</span>
		<span class="wap_value">${templateBean.applyRightContentDes}</span>
	</div>

</div>
