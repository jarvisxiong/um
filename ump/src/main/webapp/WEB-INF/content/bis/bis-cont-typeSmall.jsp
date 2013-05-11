<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

	<s:iterator value="mapContSmallType">
	<li id="stli_${key}" value="${key}" class="ctslt-nav-li" onclick="clkSmallType(this);">${value}</li>
	</s:iterator>
