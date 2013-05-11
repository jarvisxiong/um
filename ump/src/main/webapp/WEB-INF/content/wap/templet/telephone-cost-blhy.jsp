<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--电话费用报销单(宝龙行业)--%>

<div class="billContent">
	<div class="div_row">
		<div><s:checkbox name="templateBean.positionLevel1" cssClass="group"></s:checkbox><span>总经理级</span></div>
		<div><s:checkbox name="templateBean.positionLevel2" cssClass="group"></s:checkbox><span>副总级及以下</span></div>
	</div>
	<%@ include file="telephone-cost-base.jsp"%>
</div>