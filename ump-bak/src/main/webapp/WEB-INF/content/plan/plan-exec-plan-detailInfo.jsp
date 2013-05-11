<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<div class="plan_detail_div">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<caption class="plandetail_caption" align="top">${planDetailCaption}</caption>
	<tr>
		<td valign="top" width="50%">
			<s:form name="planDetailForm" id="planDetailForm" action="/plan/plan-exec-plan!savePlanDetail.action" method="post">
				<input type="hidden" name="planDetailId" id="planDetailIdHid" value="${planDetailEntity.planExecPlanDetailId}" />
				<input type="hidden" name="planDetailTempId" value="${planDetailTempId}" />
				<input type="hidden" name="activeBl" id="activeBl" value="true" />
				<input type="hidden" name="newMessage" id="form_newMessage"/>
				<input type="hidden" id="projNodeIdHid" name="projNodeId" value="${projNodeId}" />
				<input type="hidden" id="projPlanCdHid" name="projPlanCd" value="${projPlanCd}" />
				<input type="hidden" id="planDetailOriStatusHid" name="planDetailOriStatus" value="${planDetailEntity.status}" />
				<input type="hidden" id="planDetailStatusHid" name="planDetailStatus" value="${planDetailEntity.status}" />
				<input type="hidden" id="recordVersionHid" name="recordVersionHid" value="${planDetailEntity.recordVersion}" />
				<s:if test="aExecSuperAdmin">
					<input type="hidden" id="curRoleHid" value="sp" />
				</s:if>
				<s:elseif test="aExecAdmin">
					<input type="hidden" id="curRoleHid" value="sp" />
				</s:elseif>
				<s:elseif test="aWritePoint">
					<input type="hidden" id="curRoleHid" value="pj" />
				</s:elseif>
				<s:elseif test="aAddPoint">
					<input type="hidden" id="curRoleHid" value="inptor" />
				</s:elseif>
				<s:elseif test="aCompletePoint">
					<input type="hidden" id="curRoleHid" value="pj" />
				</s:elseif>
				<s:elseif test="1==viewer">
					<input type="hidden" id="curRoleHid" value="viewer" />
				</s:elseif>
				<s:else>
					<input type="hidden" id="curRoleHid" value="" />
				</s:else>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="right" width="90px" style="border-right:1px solid #DBDBDB;">计划开始时间：</td>
					<td align="left" style="padding-left: 6px;">
						<s:if test="aAddPoint||aConfirmPoint||aChangeSchedule">
							<input readonly="readonly" style="width: 150px;" id="scheduleStartDateInput" name="scheduleStartDate" value="<s:date name="planDetailEntity.scheduleStartDate" format="yyyy-MM-dd" />" 
								type="text" class="dateInput" onclick="WdatePicker();" />
						</s:if>
						<s:else>
							<input type="hidden" name="scheduleStartDate" value="<s:date name="planDetailEntity.scheduleStartDate" format="yyyy-MM-dd" />" />
							<s:date name="planDetailEntity.scheduleStartDate" format="yyyy-MM-dd" />
						</s:else>
					</td>
				</tr>
				<tr>
					<td align="right" style="border-right:1px solid #DBDBDB;">计划完成时间：</td>
					<td align="left" style="padding-left: 6px;">
						<s:if test="aAddPoint||aConfirmPoint||aChangeSchedule">
							<input readonly="readonly" style="width: 150px;" id="scheduleEndDateInput" name="scheduleEndDate" value="<s:date name="planDetailEntity.scheduleEndDate" format="yyyy-MM-dd" />" 
								type="text" class="dateInput" onclick="WdatePicker();"
							/>
						</s:if>
						<s:else>
							<input type="hidden" name="scheduleEndDate" value="<s:date name="planDetailEntity.scheduleEndDate" format="yyyy-MM-dd" />" />
							<s:date name="planDetailEntity.scheduleEndDate" format="yyyy-MM-dd" />
						</s:else>
					</td>
				</tr>
				<tr>
					<td align="right" style="border-right:1px solid #DBDBDB;">实际完成时间：</td>
					<td align="left" style="padding-left: 6px;">
						<s:if test="aCompletePoint">
							<input readonly="readonly" style="width: 150px;" id="realEndDateInput" name="realEndDate" value="<s:date name="planDetailEntity.realEndDate" format="yyyy-MM-dd" />" type="text" class="dateInput" onclick="WdatePicker();"/>
						</s:if>
						<s:elseif test="aExecSuperAdmin">
							<input readonly="readonly" style="width: 150px;" id="realEndDateInput" name="realEndDate" value="<s:date name="planDetailEntity.realEndDate" format="yyyy-MM-dd" />" type="text" class="dateInput" onclick="WdatePicker();"/>
						</s:elseif>
						<s:else>
							<input type="hidden" name="realEndDate" id="realEndDateInput" value="<s:date name="planDetailEntity.realEndDate" format="yyyy-MM-dd" />" />
							<s:date name="planDetailEntity.realEndDate" format="yyyy-MM-dd" />
						</s:else>
					</td>
				</tr>
				<tr>
					<td align="right" style="border-right:1px solid #DBDBDB;">审核确认信息：</td>
					<td style="padding-left: 6px;" id="infoConfirmTd">
						<s:if test="planDetailEntity.planExecPlanDetailId != null">
							<s:if test="aConfirmPoint && viewModel != 1">
								<div class="btn_blue_55_20" onclick="if(confirm('确认过的计划信息将不能再被信息录入员编辑，请问继续吗？')){confirmInfo('${planDetailEntity.planExecPlanDetailId}');}">确认</div>
							</s:if>
							<s:elseif test="planDetailEntity.infoConfirmedFlg == 0">
								<span style="color: red;">待确认</span>
							</s:elseif>
							<s:elseif test="planDetailEntity.infoConfirmedFlg == 1">
								<span style="color: green;">已确认</span>
							</s:elseif>
						</s:if>
					</td>
				</tr>
				<tr>
					<td align="right" style="border-right:1px solid #DBDBDB;">当前状态：</td>
					<td style="padding-left: 6px;">
						<s:if test="planDetailEntity.planExecPlanDetailId == null">
							新建
						</s:if>
						<s:else>
							<p:code2name mapCodeName="planDetailStatusMap" value="planDetailEntity.status" />
						</s:else>
					</td>
				</tr>
				<tr>
					<td align="right" style="border-right:1px solid #DBDBDB;">是否可用：</td>
					<td style="padding-left: 6px;">
						<div style="float:left; margin-top:-2px;"><input name="activeBl1" type="radio" value="true" <s:if test="!(aExecSuperAdmin || (aExecAdmin && null==planDetailEntity.planExecPlanDetailId) || (aExecAdmin && 0==planDetailEntity.status && 0==planDetailEntity.infoConfirmedFlg))">disabled</s:if> <s:if test="null==planDetailEntity.planExecPlanDetailId || planDetailEntity.activeBl">checked="checked"</s:if>/></div><div style="float:left;">可用</div>
						<div style="float:left; margin-top:-2px;"><input name="activeBl1" type="radio" value="false" <s:if test="!(aExecSuperAdmin || (aExecAdmin && null==planDetailEntity.planExecPlanDetailId) || (aExecAdmin && 0==planDetailEntity.status && 0==planDetailEntity.infoConfirmedFlg))">disabled</s:if> <s:if test="null!=planDetailEntity.planExecPlanDetailId && !planDetailEntity.activeBl">checked="checked"</s:if>/></div><div style="float:left;">不可用</div>
					</td>
				</tr>
				<tr>
					<td align="right" style="border-right:1px solid #DBDBDB;">操作：</td>
					<td style="padding-left: 6px;">
						<s:if test="planDetailEntity.planExecPlanDetailId != null && planDetailEntity.infoConfirmedFlg == 1 && viewModel != 1">
							<s:if test="aCompletePoint && (planDetailEntity.status == 0 || planDetailEntity.status == 0) && planDetailEntity.infoConfirmedFlg == 1">
								<div class="btn_blue_55_20"  style="float:left; margin-left:5px;"
									onclick="if(confirm('确认将当前计划置为完成状态吗？')){changePlanDetailStatus($(this), '${planDetailId}', '2');}">
									完成
								</div>
							</s:if>
							<s:if test="aExecSuperAdmin">
								<s:if test="planDetailEntity.status == 1 || planDetailEntity.status == 0">
								</s:if>
									<div class="btn_blue_55_20"  style="float:left; margin-left:5px;"
										onclick="if(confirm('确认将当前计划置为完成状态吗？')){changePlanDetailStatus($(this), '${planDetailId}', '2');}">
										完成
									</div>
									<div class="btn_green_65_20" style="float:left; margin-left:5px;"
										onclick="if(confirm('确认将当前计划恢复为未完成状态吗？')){changePlanDetailStatus($(this), '${planDetailId}', '0');}">
										回进行中
									</div>
									<div class="btn_green_65_20" style="float:left; margin-left:5px;"
										onclick="if(confirm('确认将当前计划恢复为未完成状态吗？')){changePlanDetailStatus($(this), '${planDetailId}', '-1');}">
										取消确认
									</div>
							</s:if>
						</s:if>
					</td>
				</tr>
			</table>
			</s:form>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed; width: 99%;">
				<tr>
					<td align="right" width="90px" style="border-right:1px solid #DBDBDB;">输出成果：</td>
					<td style="padding-left: 6px;" valign="top">
						<div id="planDetailOutputList">
							<s:if test="planDetailOutput.size == 0&&outFileTile!=null">
								<div style="color: #6BAD42; font-weight: bold; height: 120px; text-align: center;">
									提示：请提交"${outFileTile}"
								</div>
							</s:if>
							<s:if test="planDetailOutput.size == 0&&outFileTile==null">
							   <div style="color: #6BAD42; font-weight: bold; height: 120px; text-align: center;">
									无输出成果
								</div>
							</s:if>
							<s:else>
								<table cellpadding="0" cellspacing="0" class="planDetailOutput" border="0">
									<s:iterator value="planDetailOutput">
										<tr>
											<td style="padding:0;margin:0;">
												<s:url id="downUrl" action="show" namespace="/app">
													<s:param name="fileName">${fileName}</s:param>
													<s:param name="realFileName">${fileName}</s:param>
													<s:param name="bizModuleCd">execPlan</s:param>
													<s:param name="operator">inline;</s:param>
													<s:param name="id">${appAttachFileId}</s:param>
												</s:url>
												
												<a style="text-decoration: underline; color: #5A5A5A;" 
													 onclick="window.open('${downUrl}'); return false;" 
													href="#" title="上传人：<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"),";")%>">${realFileName}</a> &nbsp;&nbsp;
											</td>
											<s:if test="aExecSuperAdmin || aExecAdmin">
												<td style="padding:0;margin:0;text-align: center;width:25px">
													<img style="cursor: pointer;" title="删除附件"  src="${ctx}/pics/note/note_del.gif"  
														onclick="deleteAttachment('${appAttachFileId}');" />
												</td>
											</s:if>
										</tr>
									</s:iterator>
								</table>
							</s:else>
						</div>
					</td>
				</tr>
				<s:if test="aWritePoint && viewModel != 1">
					<tr>
						<td align="right" style="border-right:1px solid #DBDBDB;">上传附件：</td>
						<td style="padding-left: 6px;">
							<s:form id="outputUploadForm" action="/app/app-attachment!upload.action" enctype="multipart/form-data">
								<input type="hidden" name="bizModuleCd" value="execPlan" />
								<input type="hidden" id="bizEntityIdHid" name="bizEntityId" value="${outputBizEntityId}" />
								<div style="float:left; display:inline;"><input type="file" id="uploadInput" name="upload" style="height: 25px; "/></div>
								<div style="float:left; display:inline;"><input type="button" onclick="uploadAttachment(); return false;" style="height: 25px;" value="上传" /></div>
							</s:form>
						</td>
					</tr>
				</s:if>
			</table>
		</td>
		<td valign="top" width="50%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top">
						<div id="messageDiv" style="height:300px; overflow-y:scroll;">
						<s:iterator value="planDetailEntity.planExecDetailMesses">
							<div class="<s:if test="1==sysType"> color_black</s:if>">
								<pre style="padding-left:2px;"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>(<s:date name="createdDate" format="MM-dd HH:mm"/>):<s:property value="content" escape="true"/></pre>
							</div>
						</s:iterator>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding-bottom:4px;padding-top:4px;">
						<div style="float:left;"><textarea id="newMessage" name="newMessage" rows="3" style="width:290px; height:50px;"></textarea></div>
						<div style="float:left;"><button type="button" style="margin-left:2px;" class="btn_blue_55_55" onclick="saveMessage('${planDetailEntity.planExecPlanDetailId}');">发表</button></div>
					</td>
				</tr>
				<s:if test="planWorkCenterMaps.size>0">
				<tr>
					<td>相关中心<br/>内部任务:</td>
					<td>
						<table cellpadding="0" cellspacing="0" style="width: 100%;">
							<s:iterator value="planWorkCenterMaps">
								<tr>
									<td>
										<span style="cursor:pointer;" onClick="parent.TabUtils.newTab('planWorkCenter','中心内部任务','${ctx}/plan/plan-work-center!getAllPlan.action?opened_entityid=${planWorkCenterId}',true);">${content}</span>
									</td>
									<td width="40px">
										<s:if test="statusCd==0"><img src="${ctx}/images/plan/pic_noConfirm.gif" title="未确认"></s:if>
										<s:elseif test="statusCd==1"><img src="${ctx}/images/plan/pic_confirm.gif" title="进行中"></s:elseif>
										<s:elseif test="statusCd==2"><img src="${ctx}/images/plan/pic_preFinish.gif" title="预完成"></s:elseif>
										<s:elseif test="statusCd==3"><img src="${ctx}/images/plan/pic_finish.gif" title="已完成"></s:elseif>
										<s:elseif test="statusCd==4">已删除</s:elseif>
										<s:elseif test="statusCd==5"><img src="${ctx}/images/plan/pic_hide.gif" title="已隐藏"></s:elseif>
									</td>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
				</s:if>
			</table>
		</td>
	</tr>
</table>
</div>
<script type="text/javascript">
	$(function(){
		isLoading = false;
	});
</script>
