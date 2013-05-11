<%--
手机审批单行脚本,复选框
 --%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" description="附件标题" required="true"%>
<%@ attribute name="isCheck" description="是否选中,默认false" required="true"%>
<div>
<input type="checkbox" class="group" value="true" <%if (isCheck.equals("true")){ %> checked="checked" <%} %> />
<input type="hidden" value="true">
<span>${title}</span>
</div>