<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 营销活动方案审批表 --%>
<%-- 月度营销活动方案审批表 --%>
<%-- 媒体广告审批表 --%>
<%@ include file="template-var.jsp"%>
<div id="billContent">
	<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.name}</span>
	</div>
	<s:if test="authTypeCd=='HY_QHGL_10'">	
		<div class="div_row">
			<span class="wap_title">方案类别:</span>
					<div><s:checkbox name="templateBean.yearSalePlan" cssClass="group"></s:checkbox><span>年度营销活动方案</span></div>
					<div><s:checkbox name="templateBean.yearSalePlanOther" cssClass="group"></s:checkbox><span>年度营销活动方案外营销活动方案</span></div>
					<div><s:checkbox name="templateBean.openShopPlan" cssClass="group"></s:checkbox><span>开店活动方案</span></div>
					<div><s:checkbox name="templateBean.majorEventPlan" cssClass="group"></s:checkbox><span>重大活动方案</span></div>
		</div>
	</s:if>	
	<s:if test="authTypeCd=='HY_QHGL_30'">
		<div class="div_row">
			<span class="wap_title">广告类别:</span>
			<span class="wap_value">${templateBean.type}</span>
		</div>
	</s:if>	
		<div class="div_row">
			<span class="wap_title">方案简要说明(具体内容附后):</span>
			<span class="wap_value">${templateBean.desc}</span>
		</div>
</div>
