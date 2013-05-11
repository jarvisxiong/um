<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	<div class="toolBar">
	<input type="button" value="返回" class="btn_green" onclick="openDetail();" />
	</div>
	<div class="div_row">
		<span class="wap_title">延期历史</span>
	</div>
	<s:iterator value="historyList" status="sta">
	<div class="div_label">
		
		<span class="wap_label">【申请人:<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("applyUserCd")) %>】</span>
		<div class="div_row">
			<span class="wap_title">申请延期:</span>
			<span class="wap_value">${delayTime}小时</span>
		</div>
		<div class="div_row">
			<span class="wap_title">发起时间:</span>
			<span class="wap_value"><s:date name="createdDate" format="yyyy/MM/dd"/></span>
		</div>
		<div class="div_row">
			<span class="wap_title">审批人:</span>
			<span class="wap_value"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("approveUserCd")) %></span>
		</div>
		<div class="div_row">
			<span class="wap_title">审批意见:</span>
			<span class="wap_value"><s:if test="approveOptionCd == 0">不同意</s:if><s:elseif test="approveOptionCd == 1">同意</s:elseif><s:else>-</s:else></span>
		</div>
		<div class="div_row">
			<span class="wap_title">审批时间:</span>
			<span class="wap_value"><s:date name="approveDate" format="yyyy/MM/dd"/></span>
		</div>
		<div class="div_row">
			<span class="wap_title">申请事由:</span>
			<span class="wap_value">${applyReason}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">审核意见:</span>
			<span class="wap_value">${approveRemark}</span>
		</div>
		<s:if test="confirmRemark != null && confirmRemark != ''">
		<div class="div_row">
			<span class="wap_title">确认意见:</span>
			<span class="wap_value">${confirmRemark}</span>
		</div>
		</s:if>
	</div>
	</s:iterator>
