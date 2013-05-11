<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--工作联系单-->

<div id="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">发单中心:</span>
			<span class="wap_value">${templateBean.signCenterOrgName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">接单中心:</span>
			<span class="wap_value">${templateBean.receCenterOrgName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">签发日期:</span>
			<span class="wap_value">${templateBean.signDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">接单日期:</span>
			<span class="wap_value">${templateBean.receDate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">工作联系内容:</span>
			<span class="wap_value">${templateBean.workContactDesc}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">完成情况:</span>
			<span class="wap_value">${templateBean.completeDesc}</span>
		</div>
</div>
