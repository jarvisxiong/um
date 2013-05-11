<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--有偿服务收费标准审批表--%>

<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<r:wapRow value="${templateBean.titleName}" title="标题"></r:wapRow>
	<r:wapRow value="${templateBean.companyName}" title="公司名称"></r:wapRow>
	<r:wapCheckGroup title="类别">
	<r:wapCheck isCheck="${templateBean.feeType1}" title="物业服务"></r:wapCheck>
	<r:wapCheck isCheck="${templateBean.feeType2}" title="商户服务"></r:wapCheck>
	<r:wapCheck isCheck="${templateBean.feeType3}" title="其他"></r:wapCheck>
	</r:wapCheckGroup>
	<r:wapRow value="${templateBean.other}" title="其他"></r:wapRow>
	<r:wapRow value="${templateBean.serviceItem}" title="服务项目"></r:wapRow>
	<r:wapRow value="${templateBean.feeStandard}" title="收费标准"></r:wapRow>
	<r:wapRow value="${templateBean.remark}" title="相关说明"></r:wapRow>
</div>
