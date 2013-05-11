<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--会议签到情况审批表-->
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
	  <span class="wap_title">会议主题:</span>
	  <span class="wap_value">${templateBean.topic}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">会议时间:</span>
	  <span class="wap_value">${templateBean.meetingDate}</span>
    </div>
    <div class="div_row">
		<span class="wap_title">类型:</span>
		<div><s:checkbox name="templateBean.isBefore" cssClass="group"></s:checkbox><span>会前请假</span></div>
		<div><s:checkbox name="templateBean.isAfter" cssClass="group"></s:checkbox><span>会后说明</span></div>
	</div>
	<div class="div_row">
	  <span class="wap_title">姓名:</span>
	  <span class="wap_value">${templateBean.userName}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">无法签到原因说明:</span>
	  <span class="wap_value">${templateBean.reason}</span>
    </div>
    <div class="div_row">
           说明：未签到原因必须为公务原因。
    </div>
</div>
