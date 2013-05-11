<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<table class="table_ggxw" width="100%">
	<s:iterator value="deskHomePager.result">
	<s:url id="down" action="show" namespace="/app" includeParams="none" escapeAmp="true" >
		<s:param name="bizModuleCd">oaNotify</s:param>
		<s:param name="fileName"><p:code2name mapCodeName="mapAttachFileNames" value="oaNotifyId" /></s:param>
		<s:param name="realFileName"><p:code2name mapCodeName="mapAttachFileNames" value="oaNotifyId" /></s:param>
		<s:param name="filterType">pdf</s:param>
	</s:url>
	<c:set var="userName"><%=SpringSecurityUtils.getCurrentUiid()+","%></c:set>
	<c:set var="cReaders"><s:property value="readers" /></c:set>
	<c:set var="isReaded">${fn:indexOf(cReaders, userName)}</c:set>
	
	<tr onclick="openNotify('${down}', '${oaNotifyId}', '${isReaded}'); $(this).find('img.new_img').hide();">
		<td title="${subject}">
			<div class="time"><s:property value="sendTime"/><c:if test="${isReaded==-1}">&nbsp;<img class="new_img" src="${ctx}/resources/images/common/new.gif" /></c:if></div>
			<div class="ellipsisDiv_full">
				<span <c:if test="${topFlg == '1'}"></c:if>>
					<c:out value="${subject}" />
				</span>
			</div>
		</td>
	</tr>
</s:iterator>
</table>