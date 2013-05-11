<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>


<%-- 办公资产出售/报废/遗失申请单(商业公司/总部) --%>
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<s:if test="templateBean.subProperties.size()>0">
	<s:iterator value="templateBean.subProperties" var="item" status="s">
	<r:wapRowLabel title="${item.assetCode}"></r:wapRowLabel>
	<r:wapRow value="${item.assetCode}" title="资产编码"></r:wapRow>
	<r:wapRow value="${item.assetName}" title="资产名称"></r:wapRow>
	<r:wapRow value="${item.model}" title="设备型号"></r:wapRow>
	<r:wapRow value="${item.assmCode}" title="型号编码"></r:wapRow>
	<r:wapRow value="${item.useDept}" title="使用部门"></r:wapRow>
	<r:wapRow value="${item.usePersonName}" title="使用责任人"></r:wapRow>
	<r:wapRow value="${item.useDate}" title="购置日期"></r:wapRow>
	<r:wapRow value="${item.useYear}" title="使用年限"></r:wapRow>
	<r:wapRow value="${item.srcValue}" title="原值(元)"></r:wapRow>
	<r:wapRow value="${item.remainVal}" title="净值(元)"></r:wapRow>
	<r:wapRow value="${item.currNum}" title="当前配置"></r:wapRow>
	<r:wapRow value="${item.stanNum}" title="标准配置"></r:wapRow>
	<r:wapCheckGroup title="类型">
		<r:wapCheck isCheck="${item.saleType1}" title="出售"></r:wapCheck>
		<r:wapCheck isCheck="${item.saleType2}" title="报废"></r:wapCheck>
		<r:wapCheck isCheck="${item.saleType3}" title="遗失"></r:wapCheck>
	</r:wapCheckGroup>
	<r:wapRow value="${item.remark}" title="情况说明"></r:wapRow>
	</s:iterator>
	</s:if>
</div>