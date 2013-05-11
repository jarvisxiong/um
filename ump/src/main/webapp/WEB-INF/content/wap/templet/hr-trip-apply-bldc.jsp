<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--出差申请单(宝龙地产)-->
<div id="billContent" >
	<div class="div_row">
		<div><s:checkbox name="templateBean.positionLevel1" cssClass="group"></s:checkbox><span>副总裁级</span></div>
		<div><s:checkbox name="templateBean.positionLevel2" cssClass="group"></s:checkbox><span>中心第一负责人</span></div>
		<div><s:checkbox name="templateBean.positionLevel3" cssClass="group"></s:checkbox><span>其他人员</span></div>
	</div>
	<div class="div_row">
		<div><s:checkbox name="templateBean.submitDay" cssClass="group"></s:checkbox><span>提单日</span></div>
		<div><s:checkbox name="templateBean.unSubmitDay" cssClass="group"></s:checkbox><span>非提单日</span></div>
	</div>
		<%@ include file="hr-trip-apply-base.jsp"%>
</div>
