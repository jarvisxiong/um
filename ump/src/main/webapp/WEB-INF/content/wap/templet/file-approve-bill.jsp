<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="java.util.Map"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<!--文件审批单-->
		
<%@page import="com.hhz.ump.util.LoginUtil"%>
<div id="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
		<span class="wap_title">文件标题:</span>
		<span class="wap_value">${templateBean.fileTitle}</span>
		</div>
		<div class="div_row">
		<div><s:checkbox name="templateBean.secretLevel1" cssClass="group"></s:checkbox><span>绝密</span></div>
		<div><s:checkbox name="templateBean.secretLevel2" cssClass="group"></s:checkbox><span>机密</span></div>
		<div><s:checkbox name="templateBean.secretLevel3" cssClass="group"></s:checkbox><span>保密</span></div>
		<div><s:checkbox name="templateBean.secretLevel4" cssClass="group"></s:checkbox><span>内部公开</span></div>
		</div>
		<div class="div_row">
		<span class="wap_title">发起中心/公司:</span>
		<span class="wap_value">${templateBean.sendOrgName}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">文件内容简述(详细内容附后):</span>
		<div class="orgDiv"><pre style="white-space:pre-wrap;">${templateBean.fileDesc}</pre></div>
		</div>
</div>
