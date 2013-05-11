<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page import="com.hhz.ump.util.JspUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page import="com.hhz.ump.util.LoginUtil"%>

<%-- 机票订购申请单 --%>
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
		<div><s:checkbox name="templateBean.isRecord" cssClass="group"></s:checkbox><span>有审批记录(出差申请表，人员面试申请表)</span></div>
	</div>
		
	<div class="div_row">
		<span class="wap_title">出行人员:</span>
		<span class="wap_value">${templateBean.userName}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">起迄地:</span>
		<span class="wap_value">${templateBean.beginEndPlace}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">身份证号:</span>
		<span class="wap_value">${templateBean.idCardNo}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">出行事由:</span>
		<span class="wap_value">${templateBean.tripCause}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">建议航班时间:</span>
		<span class="wap_value">${templateBean.tripDate}</span>
	</div>
	
	<div class="div_row">
		<span class="wap_title">费用承担部:</span>
		<span class="wap_value">${templateBean.costDept}</span>
	</div>

	
	<div class="div_row">
		<span class="wap_title">审批记录</span>
		<div id="show_attachmentId"></div>
		<script type="text/javascript">
		showUploadedFile('${templateBean.attachmentId}','attachmentId','${canEdit}');
		</script>
	</div>
	<%-- 不上传附件 --%>
</div>
