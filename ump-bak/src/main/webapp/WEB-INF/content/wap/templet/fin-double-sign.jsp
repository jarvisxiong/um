<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--有偿服务收费标准审批表--%>

<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<r:wapRow value="${templateBean.companyName}" title="公司名称"></r:wapRow>
	<r:wapRowLabel title="变动原因说明">
		<r:wapRow value="${templateBean.reason1}" title="1、第一双签人（法定双签人）变动原因（附任命通知）"></r:wapRow>
		<r:wapRow value="${templateBean.reason2}" title="2、第二双签人（指定双签人）变动理由（简明扼要）"></r:wapRow>
	</r:wapRowLabel>
	<r:wapRowLabel title="变动前">
		<r:wapRow value="${templateBean.oriSigner1}" title="第一双签人"></r:wapRow>
		<r:wapRow value="${templateBean.oriSigner2}" title="第二双签人"></r:wapRow>
	</r:wapRowLabel>
	<r:wapRowLabel title="变动后">
		<r:wapRow value="${templateBean.newSigner1}" title="第一双签人"></r:wapRow>
		<r:wapRow value="${templateBean.newSigner2}" title="第二双签人"></r:wapRow>
	</r:wapRowLabel>
</div>
