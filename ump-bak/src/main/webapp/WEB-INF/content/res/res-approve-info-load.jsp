<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<s:form id="mainFormSearch" action="res-approve-info!load.action" method="post">
	<jsp:include page="res-approve-info-search.jsp"></jsp:include>
	<!-- 同res-approve-info-load.jsp一样,结束! -->
	
	<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
	<!-- 搜素结果 -->
	<div id="tableDiv">
		<jsp:include page="res-approve-info-result.jsp"></jsp:include>
	</div>
</s:form>