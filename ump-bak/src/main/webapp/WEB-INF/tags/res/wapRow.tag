<%--
手机审批单行脚本
 --%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" description="标题" required="true"%>
<%@ attribute name="value" description="字段值" required="true"%>
<div class="div_row">
	<span class="wap_title">${title}:</span>
	<span class="wap_value">${value}</span>
</div>
