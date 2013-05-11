<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

	<s:iterator value="queryIdNoMap">
	<li id="stli_${key}" onclick="selectStore('${key}');" title="${value}">
		<div class="ellipsisDiv_full" >
		${value}
		</div>
	</li>
	</s:iterator>

