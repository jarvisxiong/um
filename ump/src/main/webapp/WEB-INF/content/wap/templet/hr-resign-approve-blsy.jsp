<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--辞职审批表(商业集团)-->
<div id="billContent">
	<div class="div_row">
		<span class="wap_title">选择:</span>
		<div><s:checkbox name="templateBean.positionGrade1" cssClass="group"></s:checkbox>中心总经理级以上人员</div>
		<div><s:checkbox name="templateBean.positionGrade2" cssClass="group"></s:checkbox>中心副总经理</div>
		<div><s:checkbox name="templateBean.positionGrade4" cssClass="group"></s:checkbox>双线管理人员(财务)</div>
		<div><s:checkbox name="templateBean.positionGrade3" cssClass="group"></s:checkbox>其它人员</div>
	</div>
		<%@ include file="hr-resign-approve-base.jsp"%>
</div>
