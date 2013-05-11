<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--办公资产借用单-->
<div id="billContent">
	
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">借用期间:</span>
			<span class="wap_value">${templateBean.borrowPeriod}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">经办人:</span>
			<span class="wap_value">${templateBean.operator}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">借用公司/中心:</span>
			<span class="wap_value">${templateBean.centerName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">借用责任人:</span>
			<span class="wap_value">${templateBean.borrowPerson}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">金额(元):</span>
			<span class="wap_value">${templateBean.totalMoney}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">申请借用原因:</span>
			<span class="wap_value">${templateBean.reason}</span>
		</div>
		<div class=div_table_row>
		<span>序号/分类编号 /设备名称 /规格型号 /数量/备注 </span>	
		<s:iterator value="templateBean.otherProperties" var="item" status="s">
		<div class="orgDiv">
			<span class="wap_value"><s:property value="#s.index+1"/>./${item.sortNo}/${item.equipName}/${item.standardNo}/${item.quantity}/${item.remark}</span>
		</div>
		</s:iterator>
		</div>
</div>
