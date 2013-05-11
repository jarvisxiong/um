<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--费用报销审批表(宝龙商业)--%>
<div class="billContent">
	
	<%@ include file="template-var.jsp"%>
	
	<div class="div_row">
		<div><s:checkbox name="templateBean.positionLevel1"  cssClass="group"></s:checkbox><span>部门负责人以下</span></div>
		<div><s:checkbox name="templateBean.positionLevel2"  cssClass="group"></s:checkbox><span>部门负责人</span></div>
		<div><s:checkbox name="templateBean.positionLevel3"  cssClass="group"></s:checkbox><span>中心总经理</span></div>
		<div><s:checkbox name="templateBean.positionLevel4"  cssClass="group"></s:checkbox><span>集团副总经理</span></div>
	</div>
	
	<%@ include file="fee-reim-bill.jsp"%>
</div>
