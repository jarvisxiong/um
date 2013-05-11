<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page import="com.hhz.ump.util.JspUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page import="com.hhz.ump.util.LoginUtil"%>
		
<!--PD通讯录架构变动确认表--> 
<div id="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="td_title">填表人:</span>
			<span class="wap_value">${templateBean.applicant}</span>
			
			<span class="td_title">填表日期:</span>
			<span class="wap_value">${templateBean.applyDate}</span>
		</div>
		<div class="div_row">
			<span class="td_title">所在中心总经理:</span>
			<span class="wap_value">${templateBean.centerManager}</span>
		</div>
		<div class="div_row">
			<span class="td_title">变动说明(可带附件):</span>
			<span class="wap_value">${templateBean.changeDesc}</span>
		</div>
</div>
