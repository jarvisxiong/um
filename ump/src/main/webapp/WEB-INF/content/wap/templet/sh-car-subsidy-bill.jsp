<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--上海总部车辆补贴报销申请单-->
<%@ include file="template-var.jsp"%>
<div id="billContent">
		<div class="div_row">
			<span class="wap_title">中心:</span>
			<s:if test="resApproveInfoId==null">
				<s:set var="centerName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %> </s:set>
				<s:set var="centerCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></s:set>
				</s:if>
				<s:else>
				<s:set var="centerName">${templateBean.centerName}</s:set>
				<s:set var="centerCd">${templateBean.centerCd}</s:set>
				</s:else>
			<span class="wap_value">${centerName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">职级:</span>
			<span class="wap_value">${templateBean.positionName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">申请人姓名:</span>
			<span class="wap_value">${templateBean.userName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">申请额度:</span>
			<span class="wap_value">${templateBean.applyLimit}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">开始报销日期:</span>
			<span class="wap_value">${templateBean.startDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">申请理由:</span>
			<span class="wap_value">${templateBean.applyReason}</span>
		</div>
</div>

