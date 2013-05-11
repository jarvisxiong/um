<%--
手机审批单行脚本,复选框
 --%>
<%@ taglib prefix="r" tagdir="/WEB-INF/tags/res" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" description="标题" required="true"%>
<div class="div_row">
	<span class="wap_title">${title}:</span>
	<jsp:doBody/>
</div>