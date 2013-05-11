<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--员工行政、福利支出申请表--%>

<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<r:wapRow value="${templateBean.titleName}" title="标题"></r:wapRow>
	<r:wapRow value="${templateBean.companyName}" title="公司名称"></r:wapRow>
	
	<r:wapCheck isCheck="${templateBean.outFlag}" title="预算外"></r:wapCheck>
	<r:wapCheck isCheck="${templateBean.inFlag}" title="预算内"></r:wapCheck>
	
	<r:wapCheckGroup title="类别">
	<r:wapCheck isCheck="${templateBean.feeType1}" title="员工活动"></r:wapCheck>
	<r:wapCheck isCheck="${templateBean.feeType2}" title="工装"></r:wapCheck>
	<r:wapCheck isCheck="${templateBean.feeType3}" title="非法定类保险"></r:wapCheck>
	<r:wapCheck isCheck="${templateBean.feeType4}" title="体检"></r:wapCheck>
	<r:wapCheck isCheck="${templateBean.feeType5}" title="其他"></r:wapCheck>
	</r:wapCheckGroup>
	<r:wapRow value="${templateBean.other}" title="其他"></r:wapRow>
	<r:wapRow value="${templateBean.bringItem}" title="提审事项"></r:wapRow>
	<r:wapRow value="${templateBean.estimatedCost}" title="预计费用(元)"></r:wapRow>
	<r:wapRow value="${templateBean.remark}" title="相关说明"></r:wapRow>
</div>
