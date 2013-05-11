<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/oa/oa-file.css" />

<div id="rightContainer" class="right_container">
	<div class="inform_div">
		<ul>
			<s:set name="msgs" value="#request.error_msg"/>
			<s:iterator value="msgs" status="st">
				<li><s:property/></li>
			</s:iterator>
		</ul>
	</div>
</div>