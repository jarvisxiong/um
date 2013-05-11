<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page import="com.hhz.ump.util.JspUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page import="com.hhz.ump.util.LoginUtil"%>

<%-- 询标问卷审批表 --%>

<div id="billContent">
	<%@ include file="template-var.jsp"%>
	
	<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
	</s:set>
 
	<div class="div_row">
		<span class="wap_title">项目名称:</span>
		<span class="wap_value">${templateBean.projectName}</span>
	</div>
	 
	 
	<div class="div_row">
		<span class="wap_title">工程名称:</span>
		<span class="wap_value">${templateBean.engineeringName}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">招标文件编号:</span>
		<span class="wap_value">${templateBean.inviteBidFileCd}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">参加答疑单位:</span>
		<span class="wap_value">${templateBean.accessUnit}</span>
	</div>
	
	
	<div class="div_row">
		<span class="wap_title">询标回复时间:</span>
		<span class="wap_value">${templateBean.bidAnswerDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">询标附件页数:</span>
		<span class="wap_value">${templateBean.bidAttachmentPage}</span>
	</div> 
	
	
	<div class="div_row">
		<span class="wap_title">【附件】</span>
		<span class="wap_value" style="white-space: normal;">
			<s:url id="downBidQuestionnaire" action="download" namespace="/app" includeParams="none" >
				<s:param name="fileName">BidQuestionnaire.doc</s:param>
				<s:param name="realFileName">询标问卷.doc</s:param>
				<s:param name="bizModuleCd">resDownload</s:param>
			</s:url>
			<a style="text-decoration: underline;" href="${downBidQuestionnaire}">询标问卷</a>
		</span>
	</div>  
</div>