<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 资金申请表 --%>
<div class="billContent">
	
	<%@ include file="template-var.jsp"%>
	<s:if test="authTypeCd=='FSS_CW_FK_60'">
	<div class="div_row">
		<div><s:checkbox name="templateBean.type1" cssClass="group"></s:checkbox><span>全资公司</span></div>
		<div><s:checkbox name="templateBean.type2" cssClass="group"></s:checkbox><span>合资公司(宝龙拍卖、聚龙轩)</span></div>
	</div>
	</s:if>
	<s:if test="authTypeCd=='FKGL_250'">
	<div class="div_row">
		<span class="wap_title">宝龙集团资金申请表:</span>
		<span class="wap_value">${templateBean.resApproveCd}</span>
	</div>
	</s:if>
	<div class="div_row">
		<span class="wap_title">申请单位:</span>
		<span class="wap_value">${templateBean.unit}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">开户行账户信息:</span>
		<span class="wap_value">${templateBean.openAccount}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">资金用途:</span>
		<span class="wap_value">${templateBean.usage}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">列支科目:</span>
		<span class="wap_value">${templateBean.subject}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">资金数额(元):</span>
		<span class="wap_value">${templateBean.amount}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">用款时间:</span>
		<span class="wap_value">${templateBean.useDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">申请事由:</span>
		<span class="wap_value">${templateBean.reason}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">调拨路径描述:</span>
		<span class="wap_value">${templateBean.remark}</span>
	</div>
	
</div>
