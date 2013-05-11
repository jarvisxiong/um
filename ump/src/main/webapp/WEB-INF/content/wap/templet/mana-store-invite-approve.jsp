<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 百货商家租金、扣点审批表 HY_JYGL_40 --%>
<%-- 百货商家补贴、借款审批表 HY_JYGL_50 --%>

<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
		<span class="wap_title">商家名称:</span>
		<span class="wap_value">${templateBean.name}</span>
	</div>
	<div class="div_row">
	  <span class="wap_title">内容简述<br/>(详细内容附后):</span>
		<span class="wap_value">${templateBean.desc}</span>
	</div>
	<s:if test="authTypeCd=='HY_JYGL_40'||authTypeCd=='JD_YYGL_50'">
	<div class="div_row">
		<div><s:checkbox name="templateBean.inPolicy" cssClass="group"></s:checkbox><span>政策内</span></div>
		<div><s:checkbox name="templateBean.outBelow10" cssClass="group"></s:checkbox><span>超政策≤10%</span></div>
	    <div><s:checkbox name="templateBean.outAbove10" cssClass="group"></s:checkbox><span>超政策>10%</span></div>
	</div>
	</s:if>
	<s:elseif test="authTypeCd=='HY_JYGL_50'">
	<div class="div_row">
		<div><s:checkbox name="templateBean.inPlan" cssClass="group"></s:checkbox><span>计划总额内</span></div>
		<div><s:checkbox name="templateBean.outPlan" cssClass="group"></s:checkbox><span>超计划总额</span></div>
	</div>
	</s:elseif>
</div>
