<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--人事变动表(区域公司)--%>
<div class="billContent">
	<div class="div_row">
		<div><s:checkbox name="templateBean.positionLevel1" cssClass="group"></s:checkbox><span>总经理级及以上</span></div>
		<div><s:checkbox name="templateBean.positionLevel2" cssClass="group"></s:checkbox><span>副总经理级</span></div>
	</div>
	<div class="div_row">
		<div><s:checkbox name="templateBean.positionLevel3" cssClass="group"></s:checkbox><span>双线管理人员部门第一负责人</span></div>
		<div><s:checkbox name="templateBean.positionLevel4" cssClass="group"></s:checkbox><span>其他人员</span></div>
	</div>
	<%@ include file="hr-change-base.jsp"%>
</div>
