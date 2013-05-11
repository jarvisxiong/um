<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>


<%-- 办公资产维修/更换审批单(商业公司/总部) --%>
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<r:wapRow value="${templateBean.applyDate}" title="申请日期"></r:wapRow>
	<r:wapRow value="${templateBean.applyNo}" title="单号"></r:wapRow>
	<r:wapRow value="${templateBean.applyUserName}" title="申请人姓名"></r:wapRow>
	<r:wapRow value="${templateBean.orgName}" title="所在部门"></r:wapRow>
	<s:if test="templateBean.subProperties.size()>0">
	<s:iterator value="templateBean.subProperties" var="item" status="s">
	<r:wapRowLabel title="${item.assetCode}"></r:wapRowLabel>
	<r:wapRow value="${item.assetCode}" title="资产编码"></r:wapRow>
	<r:wapRow value="${item.assetName}" title="资产名称"></r:wapRow>
	<r:wapRow value="${item.model}" title="设备型号"></r:wapRow>
	<r:wapRow value="${item.useDate}" title="购置日期"></r:wapRow>
	<r:wapRow value="${item.srcValue}" title="原值(元)"></r:wapRow>
	<r:wapRow value="${item.remainVal}" title="净值(元)"></r:wapRow>
	<r:wapCheckGroup title="是否在预算内">
		<r:wapCheck isCheck="${item.inFlag}" title="预算内"></r:wapCheck>
		<r:wapCheck isCheck="${item.outFlag}" title="预算外"></r:wapCheck>
	</r:wapCheckGroup>
	<r:wapRow value="${item.saleReason}" title="申请原因"></r:wapRow>
	</s:iterator>
	</s:if>
</div>