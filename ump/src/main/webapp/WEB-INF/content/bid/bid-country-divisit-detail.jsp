<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

	<s:iterator value="bidCountryDivisitList">
	<li id="stli_${itemId}" itemId="${itemId}" onclick="selectSmallItem('${itemId}');" title="${itemName}">
		<div class="ellipsisDiv_full" >
		${itemName}
		</div>
	</li>
	</s:iterator>
