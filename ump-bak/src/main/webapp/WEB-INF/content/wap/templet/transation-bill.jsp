<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--行政事务申请表-->
		<div class="billContent">
		<div class="div_row">
		<span class="wap_title">申请中心:</span>
		<span class="wap_value">${templateBean.appCenterName}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">资金用途:</span>
		<span class="wap_value">${templateBean.finUseWay}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">列支科目:</span>
		<span class="wap_value">${templateBean.subjects}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">资金数额(元):</span>
		<span class="wap_value">${templateBean.finAmount}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">用款时间:</span>
		<span class="wap_value">${templateBean.useTime}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">申请事由:</span>
		<span class="wap_value">${templateBean.appReason}</span>
		</div>
		</div>
		