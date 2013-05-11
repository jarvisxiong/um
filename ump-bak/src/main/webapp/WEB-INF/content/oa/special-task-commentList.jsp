<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<ul class="commentList">
	<s:iterator value="commentList">
		<li>
			<strong>
			<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>
			(<s:date name="createdDate" format="MM-dd"/>):
			</strong>
			<s:property value="content" escape="true"/>
		</li>
	</s:iterator>
</ul>