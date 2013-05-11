<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--出差申请单(宝龙酒店)-->
<div id="billContent">
		<div class="div_row">
			<span class="wap_title">选择:</span>
			<div><s:checkbox name="templateBean.positionLevel1" cssClass="group"></s:checkbox><span>酒店开发中心-第一负责人</span></div>
			<div><s:checkbox name="templateBean.positionLevel2" cssClass="group"></s:checkbox><span>酒店开发中心-其他人员</span></div>
			<div><s:checkbox name="templateBean.positionLevel3" cssClass="group"></s:checkbox><span>委托管理酒店、自营酒店-总经理</span></div>
			<div><s:checkbox name="templateBean.positionLevel4" cssClass="group"></s:checkbox><span>自营酒店-其他人员</span></div>
		</div>
		<%@ include file="hr-trip-apply-base.jsp"%>
</div>
