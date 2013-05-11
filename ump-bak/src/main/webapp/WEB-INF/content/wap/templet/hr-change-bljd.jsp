<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--人事变动表(宝龙酒店)--%>
<div  class="billContent">
	<div class="div_row">
		<div><s:checkbox name="templateBean.positionLevel1" cssClass="group"></s:checkbox><span>总经理级及以上</div></div>
		<div><s:checkbox name="templateBean.positionLevel2" cssClass="group"></s:checkbox><span>副总经理级及以下人员</span></div>
	</div>
	<%@ include file="hr-change-base.jsp"%>
</div>
