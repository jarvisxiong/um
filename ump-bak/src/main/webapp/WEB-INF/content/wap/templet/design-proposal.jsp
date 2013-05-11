<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--总包单位施工组织设计方案审批表-->

<div id="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
 			<span class="wap_title">审批权限:</span>
 			<div><s:checkbox name="templateBean.hotel" cssClass="group"></s:checkbox><span>与酒店有关</span></div>
 		</div>
 		<div class="div_row">
 			<span class="wap_title">项目名称:</span>
 			<span class="wap_value">${templateBean.projectName}</span>
 		</div>
 		<div class="div_row">
 			<span class="wap_title">总包单位:</span>
 			<span class="wap_value">${templateBean.totalUnit}</span>
 		</div>
 		<div class="div_row">
 			<span class="wap_title">内容简述(详细内容附后):</span>
 			<span class="wap_value">${templateBean.contentDesc}</span>
 		</div>
</div>
