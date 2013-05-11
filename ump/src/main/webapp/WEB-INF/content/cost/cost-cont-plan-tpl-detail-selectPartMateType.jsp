<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<div id="costPartMateType">
	<select id="selectPartMateType" multiple="multiple" style="width:250px;height:153px;">
		<c:forEach items="${costPartMateTypeCdsMap}" var="myMap">
			<option value="${myMap.key }">${myMap.value }</option>
		</c:forEach>
	</select>
	
</div>
