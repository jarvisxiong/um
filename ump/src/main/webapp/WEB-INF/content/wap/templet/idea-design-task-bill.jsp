<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	<!--概念规划设计任务书审批表-->
		<div id="billContent">
			<%@ include file="template-var.jsp"%>
			<div class="div_row">
				<span class="wap_title">审批权限:</span>
				<div><s:checkbox name="templateBean.estate" cssClass="group"></s:checkbox><span>与商业有关</span></div>
				<div><s:checkbox name="templateBean.hotel" cssClass="group"></s:checkbox><span>与酒店有关</span></div>
			</div>
			<div class="div_row">
				<span class="wap_title">项目名称:</span>
				<span class="wap_value">${templateBean.projectName}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">设计单位:</span>
				<span class="wap_value">${templateBean.designUnit}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">设计任务提要（设计任务书附后）:</span>
				<span class="wap_value">${templateBean.remark}</span>
			</div>
			<div class="div_label">
				<span class="wap_label">【附件】</span>
				<div class="div_row">
					<span class="wap_title">定标审批表:</span>
					<div id="show_bidBillId"></div>
					<script type="text/javascript">
					showUploadedFile('${templateBean.bidBillId}','bidBillId','${canEdit}');
					</script>
				</div>
			</div>
		</div>
