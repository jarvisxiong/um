<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
		<%-- 车辆补贴报销申请单(宝龙拍卖) --%>
<div id="billContent">
	
		<div class="div_row">
			<span class="wap_title">选择:</span>
			<div><s:checkbox name="templateBean.positionLevel1" cssClass="group"></s:checkbox><span>总经理级</span></div>
			<div><s:checkbox name="templateBean.positionLevel2" cssClass="group"></s:checkbox><span>副总经理级</span></div>
		</div>
		<%@ include file="car-subsidy-base.jsp"%>
</div>
