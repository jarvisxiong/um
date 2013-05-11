<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--续签合同申请表(总部)-->
<div id="billContent">
	<div class="div_row">
		<span class="wap_title">选择:</span>
		<div><s:checkbox name="templateBean.positionGrade1" cssClass="group"></s:checkbox>副总裁级</div>
		<div><s:checkbox name="templateBean.positionGrade2" cssClass="group"></s:checkbox>总经理级</div>
		<div><s:checkbox name="templateBean.positionGrade3" cssClass="group"></s:checkbox>副总经理级</div>
		<div><s:checkbox name="templateBean.positionGrade4" cssClass="group"></s:checkbox>高级经理级</td>
		<div><s:checkbox name="templateBean.positionGrade5" cssClass="group"></s:checkbox>经理级</div>
		<div><s:checkbox name="templateBean.positionGrade6" cssClass="group"></s:checkbox>主管级</div>
		<div><s:checkbox name="templateBean.positionGrade7" cssClass="group"></s:checkbox>专员级</div>
	</div>
		<%@ include file="hr-renew-contract-apply-base.jsp"%>
</div>
