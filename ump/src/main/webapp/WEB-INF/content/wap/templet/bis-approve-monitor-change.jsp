<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--战略采购订单审批表-->
<s:set var="canEdit">false</s:set>
<div class="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">标题:</span>
			<span class="wap_value">${templateBean.titleName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">发起公司:</span>
			<span class="wap_value">${templateBean.companyName}</span>
			<span><s:checkbox name="templateBean.haierFlg" cssClass="group"></s:checkbox>急</span>
		</div>
		<div class="div_label">
			<span class="wap_title">本次报销</span>
			<div class="div_row">
				<span class="wap_title">费用名称:</span>
				<span class="wap_value">${templateBean.changeName}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">金额:</span>
				<span class="wap_value">${templateBean.changeCrash}</span>
			</div>
		</div>
		<div class="div_row">
			<span class="wap_title">文件内容详述:</span>
			<span class="wap_value">${templateBean.fileDetail}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">详见扫描见:</span>
			<span class="wap_value">${templateBean.detailFileId}</span>
		</div>
</div>
