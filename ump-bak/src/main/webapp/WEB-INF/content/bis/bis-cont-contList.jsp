<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

	<s:iterator value="tenantConts">
	<li id="conli_${bisContId}" val="${bisContId}" title="${contNo}" class="ctslt-nav-li" onclick="clkCont(this);">${contNo}</li>
	</s:iterator>
