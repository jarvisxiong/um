<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<div style="padding: 3px;">

<table align="left" width="100%">
	<s:iterator value="appAttachFileList">
		<tr id="tr${appAttachFileId}">
			<td width="60%"><s:url id="downUrl" action="download" namespace="/sc">
				<s:param name="fileName">${fileName}</s:param>
				<s:param name="realFileName">${realFileName}</s:param>
				<s:param name="bizModuleCd">${bizModuleCd}</s:param>
				<s:param name="operator">inline;</s:param>
				<s:param name="id">${appAttachFileId}</s:param>
			</s:url> <a href="${downUrl}">${realFileName}</a></td>
			<td width="40%"><%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"), ";")%> <s:property value="createdDate" /></td>
		</tr>
	</s:iterator>
</table>
</div>

