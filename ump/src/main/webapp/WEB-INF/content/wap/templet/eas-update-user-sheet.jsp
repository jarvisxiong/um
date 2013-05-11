<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--EAS用户变更申请表 --%>

<div class="billContent">
	
	<%@ include file="template-var.jsp"%>
	
	<div class="div_row">
		<span class="wap_title">申请人:</span>
		<span class="wap_value">${templateBean.applicant}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">申请人帐号:</span>
		<span class="wap_value">${templateBean.applAccount}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">申请日期:</span>
		<span class="wap_value">${templateBean.applDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">所在单位/部门/职位:</span>
		<span class="wap_value">${templateBean.dept}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">帐套范围:</span>
		<span class="wap_value">${templateBean.accountScope}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">职位级别:</span>
		<span>是否为公司财务负责人、中心经理级以上人员</span>
		<div><s:checkbox  name="templateBean.postionLevel1" cssClass="group"></s:checkbox><span>是</span></div>
		<div><s:checkbox  name="templateBean.postionLevel2" cssClass="group"></s:checkbox><span>否</span></div>
	</div>
	<div class="div_label">
		<span class="wap_label">【变更原因】</span>
		<div class="div_row">
			<div>
			<s:checkbox name="templateBean.reason1" cssClass="group"></s:checkbox><span>岗位变动</span>
			</div>
			<div>
			<s:checkbox name="templateBean.reason2" cssClass="group"></s:checkbox><span>离职</span>
			</div>
			<div>
			<s:checkbox name="templateBean.reason3" cssClass="group"></s:checkbox><span>密码遗失</span>
			</div>
			<div>
			<s:checkbox name="templateBean.reason4" cssClass="group"></s:checkbox><span>其他（说明）</span>
			<span class="wap_value">${templateBean.reasonOther}</span>
			</div>
		</div>
	</div>
	<div class="div_row">
		<span class="wap_title">变更说明:</span>
		<span class="wap_value">${templateBean.changeDesc}</span>
	</div>
		
</div>
