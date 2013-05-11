<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page import="com.hhz.ump.util.JspUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page import="com.hhz.ump.util.LoginUtil"%>



<%-- 商业招待费付款(eg: 宝龙商业招待费付款审批表) --%>
<div id="billContent">
	<%@ include file="template-var.jsp"%>

	<div class="div_row">
		<div><s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox><span>月度预算内</span></div>
		<div><s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox><span>月度预算外</span></div>
	</div>
	
	<div class="div_row">
		<span class="wap_title">商业公司:</span>
		<span class="wap_value">${templateBean.companyName}</span>
	</div>
 
	<div class="div_row">
		<span class="wap_title">申请人:</span>
		<span class="wap_value">${templateBean.applyName}</span>
	</div> 
	
	
	<div class="div_row">
		<span class="wap_title">招待对象:</span>
		<span class="wap_value">${templateBean.entertainObjectDesc}</span>
	</div> 
	
	<div class="div_row">
		<span class="wap_title">招待地点:</span>
		<span class="wap_value">${templateBean.entertainPlaceDesc}</span>
	</div> 
	
	
	
	<div class="div_row">
		<span class="wap_title">本次支付金额（元）:</span>
		<span class="wap_value">${templateBean.payAmt}</span>
	</div> 
	<div class="div_row">
		<span class="wap_title">本次付款时间:</span>
		<span class="wap_value">${templateBean.payDate}</span>
	</div>  
	
	
	<div class="div_row">
		<span class="wap_title">付款原由:</span>
		<span class="wap_value">${templateBean.payReasonDesc}</span>
	</div>    
</div>