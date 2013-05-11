<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--地产、行业、商业车辆购置审批表--%>
<%@page import="com.hhz.ump.util.DictContants"%>
<div id="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<div><s:checkbox name="templateBean.carStandard1" cssClass="group"></s:checkbox><span>标准车型</span></div>
			<div><s:checkbox name="templateBean.carStandard2" cssClass="group"></s:checkbox><span>非标准车型</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">选购车型:</span>
			<span class="wap_value">${templateBean.selectCarType}</span>
		</div>	
		<div class="div_row">
			<span class="wap_title">申购日期:</span>
			<span class="wap_value">${templateBean.applyDateTime}</span>
		</div>	
		<div class="div_row">
			<span class="wap_title">当地报价:</span>
			<span class="wap_value">${templateBean.localPrice}</span>
		</div>	
		<div class="div_row">
			<span class="wap_title">己购车辆:</span>
			<span class="wap_value">${templateBean.yetBuyCars}</span>
		</div>	
		<div class="div_row">
			<span class="wap_title">购车理由:</span>
			<span class="wap_value">${templateBean.buyCarReason}</span>
		</div>	
</div>
