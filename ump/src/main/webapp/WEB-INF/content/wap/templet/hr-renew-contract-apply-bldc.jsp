<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--续签合同申请表(地产公司)-->
<div id="billContent">
	<div class="div_row">
		<span class="wap_title">选择:</span>
		<div><s:checkbox name="templateBean.positionGrade1" cssClass="group"></s:checkbox>总经理级以上</div>
		<div><s:checkbox name="templateBean.positionGrade3" cssClass="group"></s:checkbox>总经理级(双线管理人员部门第一负责人除外)</div>
		<div><s:checkbox name="templateBean.positionGrade2" cssClass="group"></s:checkbox>副总经理级</div>
		<div><s:checkbox name="templateBean.positionGrade4" cssClass="group"></s:checkbox>双线管理人员部门第一负责人(财务、成本)</div>
		<div><s:checkbox name="templateBean.positionGrade5" cssClass="group"></s:checkbox>双线管理人员部门第一负责人(人资)</div>
		<div><s:checkbox name="templateBean.positionGrade6" cssClass="group"></s:checkbox>其它人员</div>
	</div>
		<%@ include file="hr-renew-contract-apply-base.jsp"%>
</div>
