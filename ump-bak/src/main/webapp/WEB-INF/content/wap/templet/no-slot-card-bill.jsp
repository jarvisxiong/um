<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!--未刷卡原因确认单(新)-->
<div id="center">
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
	  <span class="wap_title">中心/区域:</span>
	  <span class="wap_value">${templateBean.centerOrgName}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">部门:</span>
	  <span class="wap_value">${templateBean.deptOrgName}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">姓名:</span>
	  <span class="wap_value">${templateBean.userName}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">职位:</span>
	  <span class="wap_value">${templateBean.positionName}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">刷卡日期:</span>
	  <span class="wap_value">${templateBean.slotDate}</span>
    </div>
    <div class="div_row">
		<span class="wap_title">班次:</span>
		<div><s:checkbox name="templateBean.slotTime1" cssClass="group"></s:checkbox><span>上午上班</span></div>
		<div><s:checkbox name="templateBean.slotTime2" cssClass="group"></s:checkbox><span>中午</span></div>
		<div><s:checkbox name="templateBean.slotTime4" cssClass="group"></s:checkbox><span>下午下班</span></div>
	</div>
	<div class="div_row">
		<span class="wap_title">原因:</span>
		<div><s:checkbox name="templateBean.rsGoOut" cssClass="group"></s:checkbox><span>公事外出</span></div>
		<div><s:checkbox name="templateBean.rsForget" cssClass="group"></s:checkbox><span>忘记刷卡</span></div>
		<div><s:checkbox name="templateBean.rsBug" cssClass="group"></s:checkbox><span>机器故障</span></div>
		<div><s:checkbox name="templateBean.selfOut" cssClass="group"></s:checkbox><span>私事外出</span></div>
	</div>
	<div class="div_row">
	  <span class="wap_title">备注:</span>
	  <span class="wap_value">${templateBean.remark}</span>
    </div>
</div>
