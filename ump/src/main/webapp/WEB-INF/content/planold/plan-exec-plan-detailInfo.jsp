<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<div class="plan_detail_div">
	<s:form id="planDetailForm" action="/planold/plan-exec-plan!savePlanDetail.action" method="post">
		<input type="hidden" name="planDetailId" id="planDetailIdHid" value="${planDetailEntity.planExecPlanDetailId}" />
		<input type="hidden" name="planDetailTempId" value="${planDetailTempId}" />
		<input type="hidden" id="projNodeIdHid" name="projNodeId" value="${projNodeId}" />
		<input type="hidden" id="projPlanCdHid" name="projPlanCd" value="${projPlanCd}" />
		<input type="hidden" id="planDetailOriStatusHid" name="planDetailOriStatus" value="${planDetailEntity.status}" />
		<input type="hidden" id="planDetailStatusHid" name="planDetailStatus" value="${planDetailEntity.status}" />
		<s:if test="superAdmin">
			<input type="hidden" id="curRoleHid" value="sp" />
		</s:if>
		<s:elseif test="projAdmin">
			<input type="hidden" id="curRoleHid" value="pj" />
		</s:elseif>
		<s:elseif test="infoInputor">
			<input type="hidden" id="curRoleHid" value="inptor" />
		</s:elseif>
		<s:else>
			<input type="hidden" id="curRoleHid" value="viewer" />
		</s:else>
	
		<table cellpadding="0" cellspacing="0">
			<caption class="plandetail_caption" align="top">${planDetailCaption}</caption>
			<tr>
				<td width="100">计划开始时间：</td>
				<td align="left">
					<s:if test="(infoInputor && (planDetailEntity.planExecPlanDetailId == null || planDetailEntity.infoConfirmedFlg == 0))
					|| (superAdmin && (planDetailEntity.planExecPlanDetailId != null && planDetailEntity.infoConfirmedFlg == 1))">
						<input readonly="readonly" style="width: 150px;" id="scheduleStartDateInput" name="scheduleStartDate" value="<s:date name="planDetailEntity.scheduleStartDate" format="yyyy-MM-dd" />" 
							type="text" class="dateInput" onclick="WdatePicker();"
						/>
					</s:if>
					<s:else>
						<input type="hidden" name="scheduleStartDate" value="<s:date name="planDetailEntity.scheduleStartDate" format="yyyy-MM-dd" />" />
						<s:date name="planDetailEntity.scheduleStartDate" format="yyyy-MM-dd" />
					</s:else>
				</td>
			</tr>
			<tr>
				<td width="100">计划完成时间：</td>
				<td align="left">
					<s:if test="(infoInputor && (planDetailEntity.planExecPlanDetailId == null || planDetailEntity.infoConfirmedFlg == 0))
					|| (superAdmin && (planDetailEntity.planExecPlanDetailId != null && planDetailEntity.infoConfirmedFlg == 1))">
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
				<td>实际完成时间：</td>
				<td align="left">
					<s:if test="projAdmin && planDetailEntity.status == 0">
						<span id="realEndDateSpan"><s:date name="planDetailEntity.realEndDate" format="yyyy-MM-dd" /></span>
						<input readonly="readonly" style="width: 150px; display: none;" id="realEndDateInput" name="realEndDate" value="<s:date name="planDetailEntity.realEndDate" format="yyyy-MM-dd" />" 
							type="text" class="dateInput" onclick="WdatePicker();"
						/>
					</s:if>
					<s:elseif test="superAdmin && planDetailEntity.status == 1">
						<input readonly="readonly" style="width: 150px;" id="realEndDateInput" name="realEndDate" value="<s:date name="planDetailEntity.realEndDate" format="yyyy-MM-dd" />" 
								type="text" class="dateInput" onclick="WdatePicker();"
							/>
					</s:elseif>
					<s:else>
						<input type="hidden" name="realEndDate" value="<s:date name="planDetailEntity.realEndDate" format="yyyy-MM-dd" />" />
						<s:date name="planDetailEntity.realEndDate" format="yyyy-MM-dd" />
					</s:else>
				</td>
			</tr>
			<tr>
				<td>审核确认信息：</td>
				<td style="padding-left: 15px;" id="infoConfirmTd">
					<s:if test="planDetailEntity.planExecPlanDetailId != null">
						<s:if test="superAdmin && planDetailEntity.infoConfirmedFlg != 1 && viewModel != 1">
							<div class="func_icon" onclick="if (confirm('确认过的计划信息将不能再被信息录入员编辑，请问继续吗？')) {confirmInfo('${planDetailEntity.planExecPlanDetailId}');}">确认</div>
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
				<td>当前状态：</td>
				<td style="padding-left: 15px;">
					<s:if test="planDetailEntity.planExecPlanDetailId == null">
						新建
					</s:if>
					<s:else>
						<p:code2name mapCodeName="planDetailStatusMap" value="planDetailEntity.status" />
					</s:else>
				</td>
			</tr>
			<tr>
				<td>操作：</td>
				<td>
					<s:if test="planDetailEntity.planExecPlanDetailId != null && planDetailEntity.infoConfirmedFlg == 1 && viewModel != 1">
						<s:if test="projAdmin&& planDetailEntity.status == 0">
							<div class="func_icon" style="margin-left: 10px; float: left;" 
								onclick="completePlan($(this), '${planDetailId}');">完成</div>
						</s:if>
						<s:if test="superAdmin">
							<%-- 20100826 "项目管理员"点击"完成"还需要"管理员"审核
							 --%>
							<s:if test="planDetailEntity.status == 1">
								<div class="func_icon" style="margin-left: 10px; float: left;" 
									onclick="if (confirm('确认将当前计划置为完成状态吗？')) {changePlanDetailStatus($(this), '${planDetailId}', '2');}">
									完成
								</div>
							</s:if>
							<s:if test="planDetailEntity.status == 1 || planDetailEntity.status == 2">
								<div class="func_icon" style="margin-left: 10px; float: left;" 
									onclick="if (confirm('确认将当前计划恢复为未完成状态吗？')) {changePlanDetailStatus($(this), '${planDetailId}', '0');}">
									恢复
								</div>
							</s:if>
						</s:if>
					</s:if>
					<%-- 发起任务 --%>
					<security:authorize ifAnyGranted="A_PLAN_CENTER_USER,A_PLAN_CENTER_ADMIN">
					</security:authorize>
					<s:if test="linkSource == 'cost'" >
					<input class="btn_green_85_20" type="button" style="cursor:pointer;" value="发起中心任务" 
						   onclick="gotoAddPlanWorkCenter(2,'${planDetailEntity.planExecPlanDetailId}','${projectCd}','${projectName}','${planName}','${nodeName}','<s:date name="planDetailEntity.realStartDate" format="yyyy-MM-dd"/>','<s:date name="planDetailEntity.realEndDate" format="yyyy-MM-dd"/>','<s:date name="planDetailEntity.scheduleStartDate" format="yyyy-MM-dd"/>','<s:date name="planDetailEntity.scheduleEndDate" format="yyyy-MM-dd"/>','${planDetailEntity.status}','${costResOrgCd}');"/>
				   	</s:if>
					<%--
					<div class="func_icon"style=" margin-left: 10px; float: left;width:120px;" 
						onclick="gotoAddPlanWorkCenter(1,'${planDetailEntity.planExecPlanDetailId}','${projectCd}','${projectName}','${planName}','${nodeName}','<s:date name="planDetailEntity.realStartDate" format="yyyy-MM-dd"/>','<s:date name="planDetailEntity.realEndDate" format="yyyy-MM-dd"/>','<s:date name="planDetailEntity.scheduleStartDate" format="yyyy-MM-dd"/>','<s:date name="planDetailEntity.scheduleEndDate" format="yyyy-MM-dd"/>','${planDetailEntity.status}');">
						发起中心内部任务
					</div>
					 --%>
				</td>
			</tr>
		</table>
	</s:form>
	<table cellpadding="0" cellspacing="0" style="table-layout: fixed; width: 99%;">
		<tr>
			<td <s:if test="(superAdmin || projAdmin) && viewModel != 1">rowspan="2"</s:if> width="100">输出成果：</td>
			<td>
				<div id="planDetailOutputList">
					<s:if test="planDetailOutput.size == 0&&outFileTile!=null">
						<div style="color: #6BAD42; font-weight: bold; font-size: 16px; height: 50px; line-height: 50px; text-align: center;">
							提示：请提交"${outFileTile}"
						</div>
					</s:if>
					<s:if test="planDetailOutput.size == 0&&outFileTile==null">
					   <div style="color: #6BAD42; font-weight: bold; font-size: 16px; height: 50px; line-height: 50px; text-align: center;">
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
											title="点击下载附件" onclick="window.open('${downUrl}'); return false;" 
											href="#">${realFileName}</a> &nbsp;&nbsp;
									</td>
									<s:if test="superAdmin">
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
		<s:if test="(superAdmin || projAdmin) && viewModel != 1">
			<tr>
				<td>
					<s:form id="outputUploadForm" action="/app/app-attachment!upload.action" enctype="multipart/form-data">
						<input type="hidden" name="bizModuleCd" value="execPlan" />
						<input type="hidden" id="bizEntityIdHid" name="bizEntityId" value="${outputBizEntityId}" />
					
						<input type="file" id="uploadInput" name="upload" style="float: left; height: 25px; width: 270px;"/>
						<input type="button" onclick="uploadAttachment(); return false;" style="padding: 0 10px; height: 25px; margin-left: 5px;" value="上传" />
					</s:form>
				</td>
			</tr>
		</s:if>
		<s:if test="planWorkCenterMaps.size>0">
		<tr>
			<td>相关中心<br/>内部任务:</td>
			<td>
				<table cellpadding="0" cellspacing="0" style="width: 100%;">
					<s:iterator value="planWorkCenterMaps">
						<tr>
							<td>
								<span style="cursor:pointer;" onClick="parent.TabUtils.newTab('planWorkCenter','中心内部任务','${ctx}/planold/plan-work-center!getAllPlan.action?opened_entityid=${planWorkCenterId}',true);">${content}</span>
							</td>
							<td width="40px">
								<s:if test="statusCd==0"><img src="${ctx}/images/planold/pic_noConfirm.gif" title="未确认"></s:if>
								<s:elseif test="statusCd==1"><img src="${ctx}/images/planold/pic_confirm.gif" title="进行中"></s:elseif>
								<s:elseif test="statusCd==2"><img src="${ctx}/images/planold/pic_preFinish.gif" title="预完成"></s:elseif>
								<s:elseif test="statusCd==3"><img src="${ctx}/images/planold/pic_finish.gif" title="已完成"></s:elseif>
								<s:elseif test="statusCd==4">已删除</s:elseif>
								<s:elseif test="statusCd==5"><img src="${ctx}/images/planold/pic_hide.gif" title="已隐藏"></s:elseif>
							</td>
						</tr>
					</s:iterator>
				</table>
			</td>
		</tr>
		</s:if>
	</table>
</div>

