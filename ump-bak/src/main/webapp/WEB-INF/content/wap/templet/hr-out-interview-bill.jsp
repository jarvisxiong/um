<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	<!--外地人才面试审批表-->
<div  class="billContent">
	
	<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
	</s:set>
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
		<span class="wap_title">姓名:</span>
		<span class="wap_value">${templateBean.userName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">应聘岗位:</span>
		<span class="wap_value">${templateBean.positionName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">面试时间:</span>
		<span class="wap_value">${templateBean.interviewDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">面试地点:</span>
		<span class="wap_value">${templateBean.address}</span>
	</div>
	<div class="div_label">
		<span class="wap_label">【费用计划】</span>
		<div class="div_row">
			<span class="wap_title">费用项目:</span>
			<div>
				<s:checkbox name="templateBean.isTicket" cssClass="group"></s:checkbox>
				<span class="wap_title">机票:</span>
				<span class="wap_value">${templateBean.ticketBefore}</span>
				<span class="wap_value" style="font-size: 28px;">↔</span>
				<span class="wap_value">${templateBean.ticketAfter}</span>
			</div>
			<div>
				<s:checkbox name="templateBean.isLodging" cssClass="group"></s:checkbox>
				<span class="wap_title">住宿:</span>
				<span class="wap_value">${templateBean.lodgingBefore}</span>
				<span class="wap_value" style="font-size: 28px;">↔</span>
				<span class="wap_value">${templateBean.lodgingAfter}</span>
			</div>
			<div>
				<s:checkbox name="templateBean.repastContent" cssClass="group"></s:checkbox>
				<span class="wap_title">餐饮:</span>
				<span class="wap_value">${templateBean.repastContent}</span>
			</div>
			<div>
				<s:checkbox name="templateBean.otherContent" cssClass="group"></s:checkbox>
				<span class="wap_title">其他:</span>
				<span class="wap_value">${templateBean.otherContent}</span>
			</div>
		</div>
		<div class="div_row">
			<span class="wap_title">合计:</span>
			<div><s:checkbox name="templateBean.in2000" cssClass="group"></s:checkbox><span>2000元以内</span></div>
			<div><s:checkbox name="templateBean.out2000" cssClass="group"></s:checkbox><span>2000元以上</span></div>
		</div>
	</div>
	<div class="div_row">
		<span class="wap_title">说明:</span>
		<span class="wap_value">${templateBean.remark}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">附件:</span>
		<div id="show_attachmentId"></div>
		<script type="text/javascript">
		showUploadedFile('${templateBean.attachmentId}','attachmentId','${canEdit}');
		</script>
	</div>
</div>
