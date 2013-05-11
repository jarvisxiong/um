<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--行业人事审批表(下属公司)--%>
<div id="billContent">
	<div class="div_row">
		<span class="wap_title">选择:</span>
		<div><s:checkbox name="templateBean.positionLevel1" cssClass="group"></s:checkbox><span>总经理级</span></div>
		<div><s:checkbox name="templateBean.positionLevel2" cssClass="group"></s:checkbox><span>副总经理级(财务人员除外)</span></div>
		<div><s:checkbox name="templateBean.positionLevel3" cssClass="group"></s:checkbox><span>财务人员：副总经理级或财务负责人</span></div>
		<div><s:checkbox name="templateBean.positionLevel4" cssClass="group"></s:checkbox><span>财务人员：经理级</span></div>
		<div><s:checkbox name="templateBean.positionLevel6" cssClass="group"></s:checkbox><span>财务人员：经理级以下</span></div>
		<div><s:checkbox name="templateBean.positionLevel5" cssClass="group"></s:checkbox><span>其他人员</span></div>
	</div>	
		<%@ include file="hy-hr-approve-base.jsp"%>
</div>
