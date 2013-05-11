<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--项目开业/开街/交房时间审批表--%>

<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<r:wapRow value="${templateBean.titleName}" title="标题"></r:wapRow>
	<r:wapRow value="${templateBean.projectName}" title="项目名称"></r:wapRow>
	<r:wapRow value="${templateBean.openArea}" title="开业/开街区域"></r:wapRow>
	<r:wapRow value="${templateBean.roomArea}" title="交房区域"></r:wapRow>
	<r:wapRow value="${templateBean.oriDate}" title="原定时间"></r:wapRow>
	<r:wapRow value="${templateBean.tarDate}" title="调整时间"></r:wapRow>
	<r:wapRow value="${templateBean.remark}" title="相关说明"></r:wapRow>
</div>