<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<!--商业公司返租合同费用付款审批表-->
<div id="billContent">
			<c:set var="isSy">false</c:set>
			<c:if test='${fn:startsWith(authTypeCd, "BLSY_")||fn:startsWith(authTypeCd, "SYGS_")}'>
			<c:set var="isSy">true</c:set>
			</c:if>
			<%@ include file="template-var.jsp"%>
			<c:if test="${isSy}">
			<div class="div_row">
				<div><s:checkbox name="templateBean.businessCompany" cssClass="group"></s:checkbox><span>商业公司发起</span></div>
				<div><s:checkbox name="templateBean.businessGroup" cssClass="group"></s:checkbox><span>商业集团发起</span></div>
			</div>				
			</c:if>
			<div class="div_row">
				<span class="wap_title">预算:</span>
				<div><s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox><span>预算外</span></div>
				<div><s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox><span>预算内</span></div>
			</div>
			<div class="div_row">
				<span class="wap_title">项目:</span>
				<span class="wap_value">${templateBean.projectName}</span>
			</div>				
			<div class="div_row">
				<span class="wap_title">本次支付总额:</span>
				<span class="wap_value">${templateBean.paymentTotalAmt}</span>
			</div>		
			<div class="div_row">
				<span class="wap_title">备注:</span>
				<span class="wap_value">${templateBean.remark}</span>
			</div>
			<div class=div_table_row>
			<span>合同编号/账户名(业主)/商铺位置/租金所属期 /实付租金</span>	
			<s:iterator value="templateBean.businessBackRentCons" var="item" status="s">
			<div class="orgDiv">
				<s:property value="#s.index+1"/>./${item.contNo}/${item.owner}/${item.shopPosition}/${item.blongPeriod}/${item.downPayment}
			</div>
			</s:iterator>
			</div>
</div>
	