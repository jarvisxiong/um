<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
	<table class="table_ggxw" width="100%">
		<s:iterator value="deskHomePager.result">
		<c:set var="userId"><%=SpringSecurityUtils.getCurrentUiid()+","%></c:set>
		<c:set var="cReaders"><s:property value="readers" /></c:set>
		<c:set var="isReaded">${fn:indexOf(cReaders, userId)}</c:set>
		<tr onclick="$(this).find('img.new_img').hide(); window.open('${ctx}/oa/oa-news!detail.action?id=${oaNewsId}');">
			<td title="${subject}">
				<div><s:property value="newsTime"/><c:if test="${isReaded==-1}">&nbsp;<img class="new_img" src="${ctx}/resources/images/common/new.gif" /></c:if></div>
				<div class="ellipsisDiv_full">
					<span <c:if test="${topFlg == '1'}"></c:if>>
						<c:out value="${subject}" />
					</span>
				</div>
			</td>
		</tr>
		</s:iterator>
	</table>