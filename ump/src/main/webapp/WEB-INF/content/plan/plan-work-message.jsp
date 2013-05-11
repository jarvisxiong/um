<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
	<s:iterator value="messageList">
		<div>
			<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>
			(<s:date name="createdDate" format="MM-dd"/>):
			<s:property value="content" escape="true"/>
		</div>
	</s:iterator>