<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 
	租金减免审批表(eg:租金减免审批表) 
--%>
<div class="billContent">
	<%@ include file="template-var.jsp"%>
	<r:wapRow title="项目名称" value="${templateBean.projectCd}"></r:wapRow>
	<r:wapRow title="商户名称" value="${templateBean.storeName}"></r:wapRow>
	<r:wapRow title="店面号" value="${templateBean.storeNo}"></r:wapRow>
	<r:wapRow title="面积" value="${templateBean.area}"></r:wapRow>
	<r:wapRow title="合同期" value="${templateBean.contractPeriod}"></r:wapRow>
	<r:wapRow title="面积" value="${templateBean.area}"></r:wapRow>
	<r:wapCheckGroup title="减免类型">
		<r:wapCheck isCheck="${templateBean.feeTypeCd1}" title="物业管理费"></r:wapCheck>
		<r:wapCheck isCheck="${templateBean.feeTypeCd2}" title="公摊费用"></r:wapCheck>
		<r:wapCheck isCheck="${templateBean.feeTypeCd3}" title="违约金"></r:wapCheck>
		<r:wapCheck isCheck="${templateBean.feeTypeCd4}" title="对商户补偿"></r:wapCheck>
	</r:wapCheckGroup>
	<r:wapCheckGroup title="物业性质">
		<r:wapCheck isCheck="${templateBean.charactCd1}" title="自持"></r:wapCheck>
		<r:wapCheck isCheck="${templateBean.charactCd2}" title="销售"></r:wapCheck>
	</r:wapCheckGroup>
	<r:wapRow title="物业管理费减免金额(元)" value="${templateBean.strataFeeAmt}"></r:wapRow>
	<r:wapRow title="物业管理费减免起止期" value="${templateBean.strataStartDate}"></r:wapRow>
	<r:wapRow title="至" value="${templateBean.strataEndDate}"></r:wapRow>
	<r:wapRow title="公摊费用减免金额(元)" value="${templateBean.publicFeeAmt}"></r:wapRow>
	<r:wapRow title="至" value="${templateBean.publicEndDate}"></r:wapRow>
	<r:wapRow title="违约金减免金额(元)" value="${templateBean.reduceAmt}"></r:wapRow>
	<r:wapRow title="对商户补偿金额(元)" value="${templateBean.offsetAmt}"></r:wapRow>
	<r:wapRow title="物业管理费/公摊费用减免内容" value="${templateBean.contentDesc}"></r:wapRow>
</div>