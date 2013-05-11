<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<ul class="commentList">
	<s:iterator value="commentList">
		<li>
			<pre><strong><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>(<s:date name="createdDate" format="MM-dd HH:mm"/>):</strong><s:property value="content" escape="true"/><s:if test="oaMeetingAdmin == true">            <input type="button" id="${oaMeetingCommentId}comment" onclick="deleteComment('${oaMeetingCommentId}','${oaMeetingId}');" value="删除" /></s:if></pre>
		</li>
	</s:iterator>
</ul>
