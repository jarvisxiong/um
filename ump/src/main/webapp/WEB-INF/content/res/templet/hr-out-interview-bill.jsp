<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

		<%@ include file="template-header.jsp"%>
	<!--外地人才面试审批表-->
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
					<col width="80"/>
					<col width="60"/>
					<col/>
					<col width="100"/>
					<col/>
					<tr>
						<td  class="td_title">姓名</td>
						<td colspan="2">
						<input class="inputBorder" validate="required" type="text" name="templateBean.userName" value="${templateBean.userName}"  />
						</td>
						<td  class="td_title">应聘岗位</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.positionName" value="${templateBean.positionName}"  />
						</td>
					</tr>
					<tr>
						<td  class="td_title">面试时间</td>
						<td colspan="2">
						<input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="Wdate" type="text" validate="required" name="templateBean.interviewDate" value="${templateBean.interviewDate}"/>
						</td>
						<td  class="td_title">面试地点</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.address" value="${templateBean.address}"  />
						</td>
					</tr>
					<tr>
						<td  class="td_title" rowspan="6">费用计划</td>
						<td colspan="3">费用项目</td>
						<td>合计</td>
					</tr>
					<tr>
						<td  class="td_title"><s:checkbox name="templateBean.isTicket"></s:checkbox>机票</td>
						<td colspan="2">
						<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<tr>
						<td><input class="inputBorder" validate="required"   type="text" name="templateBean.ticketBefore" value="${templateBean.ticketBefore}"/>
						</td>
						<td width="30"><span style="font-size: 28px;">↔</span>
						</td>
						<td><input class="inputBorder" validate="required"  type="text" name="templateBean.ticketAfter" value="${templateBean.ticketAfter}"/>
						</td>
						</tr>
						</table>
						</td>
						<td rowspan="5" class="chkGroup">
							<div><s:checkbox name="templateBean.in2000" cssClass="group"></s:checkbox>2000元以内</div>
							<div><s:checkbox name="templateBean.out2000" cssClass="group"></s:checkbox>2000元以上</div>
						</td>
					</tr>
					<tr>
						<td rowspan="2"  class="td_title"><s:checkbox name="templateBean.isLodging"></s:checkbox>住宿</td>
						<td colspan="2">
						<input class="inputBorder"  type="text"   name="templateBean.lodgingBefore" value="${templateBean.lodgingBefore}"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
						<input class="inputBorder"  type="text"   name="templateBean.lodgingAfter" value="${templateBean.lodgingAfter}"/>
						</td>
					</tr>
					<tr>
						<td  class="td_title"><s:checkbox name="templateBean.isRepast"></s:checkbox>餐饮</td>
						<td colspan="2">
						<input class="inputBorder" type="text" name="templateBean.repastContent"   value="${templateBean.repastContent}"/>
						</td>
					</tr>
					<tr>
						<td  class="td_title"><s:checkbox name="templateBean.isOther"></s:checkbox>其他</td>
						<td colspan="2">
						<input class="inputBorder"  type="text" name="templateBean.otherContent"  value="${templateBean.otherContent}"/>
						</td>
					</tr>
					<tr>
						<td  class="td_title">说明</td>
						<td colspan="4">
						<textarea class="inputBorder contentTextArea"  name="templateBean.remark">${templateBean.remark}</textarea>
						</td>
					</tr>
					<tr>
						<td class="td_title">附件上传</td>
						<td align="left"  style="height:40px;" validate="required" value="${templateBean.attachmentId}">人员简历</td>
						<td align="center">
						<s:if test="#canEdit=='true'">
						<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('面试人员简历','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','attachmentId',event);"/>
						<input type="hidden" name="templateBean.attachmentId" id="attachmentId" value="${templateBean.attachmentId}"/>
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
