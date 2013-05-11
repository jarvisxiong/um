<%--
手机审批，双标题，和wapRow.tag配合使用
 --%>
<%@ taglib prefix="r" tagdir="/WEB-INF/tags/res" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" description="标题" required="true"%>
<div class="div_label">
	<span class="wap_label">【${title}】</span>
	<jsp:doBody/>
</div>