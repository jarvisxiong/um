<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--年假延期申请单(地产公司)-->
<div id="billContent" >
	<div class="div_row">
		<div><s:checkbox name="templateBean.positionLevel1" cssClass="group"></s:checkbox><span>总经理</span></div>
		<div><s:checkbox name="templateBean.positionLevel2" cssClass="group"></s:checkbox><span>其他人员</span></div>
	</div>
	<%@ include file="hr-vocation-delay-base.jsp"%>
</div>
