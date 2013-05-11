<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
	<!--概念规划设计任务书审批表-->
<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>
		<div align="center" class="billContent">
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="100"/>
					<col/>
					<col width="100"/>
					<col/>
					<tr>
					<td class="td_title">审批权限</td>
					<td align="left" colspan="3">
						<table class="tb_checkbox" style="width:100%;">
						    <col width="100">
						    <col width="100">
							<col width="100">
							<col width="100">
							<tr>
							<td><s:checkbox name="templateBean.estate"  cssClass="group"></s:checkbox>与商业有关</td>
							<td><s:checkbox name="templateBean.hotel"  cssClass="group"></s:checkbox>与酒店有关</td>
							</tr>
						</table>
					</td>
					</tr>
					<tr>
						<td class="td_title">项目名称</td>
						<td>
						<s:if test="'SJGL_GNGH_10'==authTypeCd||'SJGL_JZFA_10'==authTypeCd">
							<input class="inputBorder" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" />
						</s:if>
						<s:else>
							<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
							<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
						</s:else>
						</td>
						<td class="td_title">设计单位</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.designUnit" value="${templateBean.designUnit}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">设计任务提要（设计任务书附后）</td>
						<td colspan="3">
						<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.remark">${templateBean.remark}</textarea>
						</td>
					</tr>
					<tr>
						<td class="td_title" >附件上传</td>
						<td align="center" style="height:40px;" validate="required" value="${templateBean.bidBillId}">定标审批表</td>
						<td align="center">
						<s:if test="#canEdit=='true'">
						<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('定标审批表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','bidBillId',event);"/>
						<input type="hidden" name="templateBean.bidBillId" id="bidBillId" value="${templateBean.bidBillId}"/>
						</s:if>
						</td>
						<td>
						<div id="show_bidBillId"></div>
						<script type="text/javascript">
						showUploadedFile('${templateBean.bidBillId}','bidBillId','${canEdit}');
						</script>
						</td>
					</tr>
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
