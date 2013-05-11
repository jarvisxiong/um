<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--EAS用户增加申请表 --%>

<div id="billContent">
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
			<span class="wap_title">工作职责描述:</span>
			<span class="wap_value">${templateBean.jobDuties}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">帐套范围:</span>
			<span class="wap_value">${templateBean.accountScope}</span>
		</div>
		
		<div class="div_label">
			<span class="wap_label">【职位级别】</span>
			<div class="div_row">
	 		<span class="wap_title">是否为公司财务负责人、中心经理级以上人员:</span>
			<div><s:checkbox name="templateBean.postionLevel1" cssClass="group"></s:checkbox><span>是</span></div>
			<div><s:checkbox name="templateBean.postionLevel2" cssClass="group"></s:checkbox><span>否</span></div>
			</div>
		</div>
		<div class="div_label">
	 		<span class="wap_label">【权限模块】</span>
	 		<div class="div_row">
			<div><s:checkbox name="templateBean.module1" cssClass="group"></s:checkbox><span>总账</span></div>
			<div><s:checkbox name="templateBean.module2" cssClass="group"></s:checkbox><span>报表</span></div>
			<div><s:checkbox name="templateBean.module3" cssClass="group"></s:checkbox><span>现金管理</span></div>
			<div><s:checkbox name="templateBean.module4" cssClass="group"></s:checkbox><span>固定资产管理</span></div>
			<div><s:checkbox name="templateBean.module5" cssClass="group"></s:checkbox><span>现金流量表</span></div>
			<div><s:checkbox name="templateBean.module6" cssClass="group"></s:checkbox><span>合并报表</span></div>
			</div>
			<div class="div_row">
			<div><s:checkbox name="templateBean.module7" cssClass="group"></s:checkbox><span>基础资料</span></div>
			<div><s:checkbox name="templateBean.module8" cssClass="group"></s:checkbox><span>初始化</span></div>
			<div><s:checkbox name="templateBean.module9" cssClass="group"></s:checkbox><span>系统设置</span></div>
			<div><s:checkbox name="templateBean.module10" cssClass="group"></s:checkbox><span>预算管理</span></div>
			<div><s:checkbox name="templateBean.module11" cssClass="group"></s:checkbox><span>其他</span><span class="wap_value">${templateBean.moduleOther}</span></div>
			</div>
		</div>
		<div class="div_row">
			<span class="wap_title">权限内容:</span>
			<span class="wap_value">${templateBean.permissionContent}</span>
		</div>
</div>
