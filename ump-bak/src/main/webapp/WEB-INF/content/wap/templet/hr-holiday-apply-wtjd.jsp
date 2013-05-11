<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--请假单(新)委托管理酒店--%>
<div class="billContent">
	<div class="div_row">
		<div><s:checkbox name="templateBean.dayType1" cssClass="group"></s:checkbox><span>短期(5天及以内)</span></div>
		<div><s:checkbox name="templateBean.dayType2" cssClass="group"></s:checkbox><span>长期(5天以上)</span></div>
	</div>
	<div class="div_row">
		<div><s:checkbox name="templateBean.positionLevel1" cssClass="group"></s:checkbox><span>业主代表</span></div>
		<div><s:checkbox name="templateBean.positionLevel2" cssClass="group"></s:checkbox><span>业主财务代表</span></div>
	</div>
	<%@ include file="hr-holiday-apply-base.jsp"%>
</div>
