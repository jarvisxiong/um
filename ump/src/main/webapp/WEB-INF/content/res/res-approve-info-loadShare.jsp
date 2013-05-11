<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<s:if test="shareUserNames!=''">
	<div class="list_header"><span style="font-size:14px;">已共享给</span></div>
	<div id="resShared" style="margin-left: 20px;line-height:24px;padding-bottom:8px;color:#0167a2;">
		<s:property value="shareUserNames"/>
	</div>
</s:if>