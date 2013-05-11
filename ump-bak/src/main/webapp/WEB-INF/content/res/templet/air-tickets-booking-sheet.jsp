<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

		<%@ include file="template-header.jsp"%>
	<%-- 机票订购申请单 --%>
		<div align="center" class="billContent">
			<s:set var="canEdit">
			<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
			true
			</s:if>
			<s:else>
			false
			</s:else>
			</s:set>
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="90"/>
					<col/>
					<col width="90"/>
					<col/>
					<tr>
						<td class="td_title"></td>
						<td colspan="3">
						<s:checkbox name="templateBean.isRecord" id="isRecord" cssClass="group"></s:checkbox>有审批记录(出差申请表，人员面试申请表)
						</td>
					</tr>
					<tr>
						<td class="td_title">出行人员</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.userName" value="${templateBean.userName}" />
							<input type="hidden" name="templateBean.userCd" value="${templateBean.userCd}"  />
						</td>
						<td class="td_title">起迄地</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.beginEndPlace" value="${templateBean.beginEndPlace}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">身份证号</td>
						<td colspan="3">
							<input class="inputBorder" validate="required" type="text" name="templateBean.idCardNo" value="${templateBean.idCardNo}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">出行事由</td>
						<td colspan="3">
							<input class="inputBorder" validate="required" type="text" name="templateBean.tripCause" value="${templateBean.tripCause}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">建议航班时间</td>
						<td>
							<input class="Wdate inputBorder" validate="required" type="text" name="templateBean.tripDate" value="${templateBean.tripDate}" onfocus="WdatePicker()" />
						</td>
						<td class="td_title">费用承担部门</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.costDept" value="${templateBean.costDept}"  />
						</td>
					</tr>
					<tr id="showRecord" style="display:none;">
						<td class="td_title"  validate="" id="validateAttachmentId" value="${templateBean.attachmentId}">审批记录</td>
						<td>
						<s:if test="#canEdit=='true'">
						<input value="上传附件" class="btn_green_65_20" style="border: medium none;" onclick="showUploadSingleAttachDialog('审批记录','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','attachmentId',event);" type="button">
						<input name="templateBean.attachmentId" id="attachmentId" value="${templateBean.attachmentId}" type="hidden">
						</s:if>
						</td>
						<td colspan="2">
						<div id="show_attachmentId"></div>
						<script type="text/javascript">
						showUploadedFile('${templateBean.attachmentId}','attachmentId','${canEdit}');
						</script>
						</td>
					</tr>
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
		<script>
		$(function(){
			//判断审批记录是否选中
			if($('#isRecord').attr("checked")){
				$('#showRecord').show();
			}
		});
		$('#isRecord').click(function(){
			if(this.checked){
			$('#showRecord').show();
			$('#validateAttachmentId').attr("validate","required");
			}else{
			$('#showRecord').hide();
			$('#validateAttachmentId').attr("validate","");
			}
			
		});
		</script>
