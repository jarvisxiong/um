<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--商业公司奖金申报表--%>

<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<r:wapRow value="${templateBean.titleName}" title="标题"></r:wapRow>
	<r:wapRow value="${templateBean.projectName}" title="项目名称"></r:wapRow>
	
	<r:wapCheckGroup title="类别">
	<r:wapCheck isCheck="${templateBean.feeType1}" title="招商奖金"></r:wapCheck>
	<r:wapCheck isCheck="${templateBean.feeType2}" title="开业奖"></r:wapCheck>
	<r:wapCheck isCheck="${templateBean.feeType3}" title="目标责任书完成奖"></r:wapCheck>
	<r:wapCheck isCheck="${templateBean.feeType4}" title="其他"></r:wapCheck>
	</r:wapCheckGroup>
	<r:wapRow value="${templateBean.other}" title="其他"></r:wapRow>
	<r:wapRow value="${templateBean.totalFee}" title="奖金总额(元)"></r:wapRow>
	<r:wapRow value="${templateBean.remark}" title="发放理由及说明(附件)"></r:wapRow>
</div>
