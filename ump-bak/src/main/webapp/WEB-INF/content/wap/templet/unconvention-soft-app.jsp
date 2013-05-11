<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--非常规软件使用清单-->
<div class="billContent">
	<div class="div_row">
	<span class="wap_title">申请人:</span>
	<span class="wap_value">${templateBean.applicant}</span>
	</div>
	<div class="div_row">
	<span class="wap_title">所属部门:</span>
	<span class="wap_value">${templateBean.appDept}</span>
	</div>
	<div class="div_row">
	<span class="wap_title">申请事宜:</span>
	<span class="wap_value">${templateBean.applicant}</span>
	</div>
	<div class="div_label">
		<span class="wap_label">【申请事宜】</span>
		<div class="div_row">
			<span class="wap_title">开通软件/端口:</span>
			<span class="wap_value">${templateBean.appSoft}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">说明:</span>
			<span class="wap_value">${templateBean.appDescribe}</span>
		</div>
	</div>
	<div class="div_row">
	<span class="wap_title">申请理由:</span>
	<span class="wap_value">${templateBean.appReason}</span>
	</div>
</div>