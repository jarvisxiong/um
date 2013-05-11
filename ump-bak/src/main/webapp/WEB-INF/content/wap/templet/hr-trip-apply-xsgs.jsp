<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--出差申请单(下属公司)--%>
<div id="billContent">
		<div class="div_row">
			<span class="wap_title">选择:</span>
			<div><s:checkbox name="templateBean.positionLevel1" cssClass="group"></s:checkbox><span>总经理</span></div>
			<div><s:checkbox name="templateBean.positionLevel2" cssClass="group"></s:checkbox><span>其他人员</span></div>
		</div>	
		<%@ include file="hr-trip-apply-base.jsp"%>
</div>
