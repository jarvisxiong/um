<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 年假延期申请单 --%>
<%@ include file="template-var.jsp"%>
<div class="div_row">
<s:set var="sendCenterName">${templateBean.centerOrgName}</s:set>
<span class="wap_title">中心/部门:</span>
<span class="wap_value">${sendCenterName}</span>
</div>
<div class="div_row">
<span class="wap_title">申请人:</span>
<span class="wap_value">${templateBean.userName}</span>
</div>
<div class="div_row">
<span class="wap_title">职位:</span>
<span class="wap_value">${templateBean.position}</span>
</div>
<div class="div_row">
<span class="wap_title">入职日期:</span>
<span class="wap_value">${templateBean.entryDate}</span>
</div>
<div class="div_row">
<span class="wap_title">事由:</span>
<span class="wap_value">${templateBean.reason}</span>
</div>
<div class="div_row">
<span class="wap_title">剩余年假:</span>
<span class="wap_value">${templateBean.remaindDays}</span>
</div>
<div class="div_row">
	<span class="wap_title">延期时间:</span>
	<div><s:checkbox name="templateBean.delayMonth1" cssClass="group"></s:checkbox><span>一个月</span></div>
	<div><s:checkbox name="templateBean.delayMonth2" cssClass="group"></s:checkbox><span>两个月</span></div>
	<div><s:checkbox name="templateBean.delayMonth3" cssClass="group"></s:checkbox><span>三个月</span></div>
	<div><s:checkbox name="templateBean.delayMonth4" cssClass="group"></s:checkbox><span>四个月</span></div>
	<div><s:checkbox name="templateBean.delayMonth5" cssClass="group"></s:checkbox><span>五个月</span></div>
	<div><s:checkbox name="templateBean.delayMonth6" cssClass="group"></s:checkbox><span>六个月</span></div>
	</div>
