<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.core.utils.DateOperator"%>
<%@page import="java.util.Date"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<script type="text/javascript" src="${ctx}/resources/js/oa/allAttention.js"></script>
<div class="plan_detail_div" style="overflow-y:hidden;">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<caption class="plandetail_caption" align="top">
		${planDetailCaption}
	</caption>
	<tr>
		<td valign="top" width="50%">
			<s:form name="planDetailForm" id="planDetailForm" action="/plan/exec-plan!savePlanDetail.action" method="post">
				<input type="hidden" name="planDetailId" id="planDetailIdHid" value="${planDetailEntity.execPlanDetailId}" />
				<input type="hidden" name="planDetailTempId" value="${planDetailTempId}" />
				<input type="hidden" name="projectCd" value="${planDetailEntity.projectCd}" />
				<input type="hidden" name="nodeId" id="nodeId" value="${nodeId}" />
				<input type="hidden" name="layoutId" id="layoutId" value="${layoutId}" />
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
					  <input style="width: 150px;" id="scheduleStartDateInput" name="scheduleStartDate" value="<s:date name="planDetailEntity.scheduleStartDate" format="yyyy-MM-dd" />" 
								type="text" class="dateInput" onclick="WdatePicker();" />
						<%-- <s:if test="aAddPoint||aConfirmPoint||aChangeSchedule">
							<input style="width: 150px;" id="scheduleStartDateInput" name="scheduleStartDate" value="<s:date name="planDetailEntity.scheduleStartDate" format="yyyy-MM-dd" />" 
								type="text" class="dateInput" onclick="WdatePicker();" />
						</s:if>
						<s:else>
							<input type="hidden" name="scheduleStartDate" value="<s:date name="planDetailEntity.scheduleStartDate" format="yyyy-MM-dd" />" />
							<s:date name="planDetailEntity.scheduleStartDate" format="yyyy-MM-dd" />
						</s:else> --%>
					</td>
				</tr>
				<tr>
					<td align="right" style="border-right:1px solid #DBDBDB;">计划完成时间：</td>
					<td align="left" style="padding-left: 6px;">
					<input style="width: 150px;" id="scheduleEndDateInput" name="scheduleEndDate" value="<s:date name="planDetailEntity.scheduleEndDate" format="yyyy-MM-dd" />" 
								type="text" class="dateInput" onclick="WdatePicker();"/>
						<%-- <s:if test="aAddPoint||aConfirmPoint||aChangeSchedule">
							<input style="width: 150px;" id="scheduleEndDateInput" name="scheduleEndDate" value="<s:date name="planDetailEntity.scheduleEndDate" format="yyyy-MM-dd" />" 
								type="text" class="dateInput" onclick="WdatePicker();"/>
						</s:if>
						<s:else>
							<input type="hidden" name="scheduleEndDate" value="<s:date name="planDetailEntity.scheduleEndDate" format="yyyy-MM-dd" />" />
							<s:date name="planDetailEntity.scheduleEndDate" format="yyyy-MM-dd" />
						</s:else> --%>
					</td>
				</tr>
				<tr>
					<td align="right" style="border-right:1px solid #DBDBDB;">实际完成时间：</td>
					<td align="left" style="padding-left: 6px;">
					 <input style="width: 150px;" id="realEndDateInput" name="realEndDate" value="<s:date name="planDetailEntity.realEndDate" format="yyyy-MM-dd" />" type="text" class="dateInput" onclick="WdatePicker();"/>
						
						<%-- <s:if test="aAddPoint || aCompletePoint || aWritePoint || aExecSuperAdmin">
							<input style="width: 150px;" id="realEndDateInput" name="realEndDate" value="<s:date name="planDetailEntity.realEndDate" format="yyyy-MM-dd" />" type="text" class="dateInput" onclick="WdatePicker();"/>
						</s:if>
						<s:else>
							<input type="hidden" name="realEndDate" id="realEndDateInput" value="<s:date name="planDetailEntity.realEndDate" format="yyyy-MM-dd" />" />
							<s:date name="planDetailEntity.realEndDate" format="yyyy-MM-dd" />
						</s:else> --%>
					</td>
				</tr>
				<tr>
					<td align="right" style="border-right:1px solid #DBDBDB;">当前状态：</td>
					<td style="padding-left: 6px;">
						<s:if test="aExecSuperAdmin || aExecAdmin">
							<div style="float:left; margin-top:-2px;"><input name="activeBl1" type="radio" value="true" <s:if test="!(aExecSuperAdmin || (aExecAdmin && null==planDetailEntity.execPlanDetailId) || (aExecAdmin && 0==planDetailEntity.status && 0==planDetailEntity.infoConfirmedFlg))">disabled</s:if> <s:if test="null==planDetailEntity.execPlanDetailId || planDetailEntity.activeBl">checked="checked"</s:if>/></div><div style="float:left;">可用</div>
							<div style="float:left; margin-top:-2px;"><input name="activeBl1" type="radio" value="false" <s:if test="!(aExecSuperAdmin || (aExecAdmin && null==planDetailEntity.execPlanDetailId) || (aExecAdmin && 0==planDetailEntity.status && 0==planDetailEntity.infoConfirmedFlg))">disabled</s:if> <s:if test="null!=planDetailEntity.execPlanDetailId && !planDetailEntity.activeBl">checked="checked"</s:if>/></div><div style="float:left;">不可用</div>
						</s:if>
						<s:elseif test="null!=planDetailEntity && !planDetailEntity.activeBl">
							未启用
						</s:elseif>
						<s:else>
							<s:if test="planDetailEntity.execPlanDetailId != null">
								<s:if test="planDetailEntity.infoConfirmedFlg == 0">
									<span style="color: red;">未确认</span>
								</s:if>
							</s:if>
							<s:if test="planDetailEntity.execPlanDetailId == null">
								新建
							</s:if>
							<s:else>
								<p:code2name mapCodeName="planDetailStatusMap" value="planDetailEntity.status" />
							</s:else>
						</s:else>
					</td>
				</tr>
				<tr>
					<td align="right" style="border-right:1px solid #DBDBDB;">操作：</td>
					<td style="padding-left: 6px;">
					    <div class="btn_blue" style="float:left;" onclick="confirmInfo('${planDetailEntity.execPlanDetailId}','1');">确认</div>
						<%-- <s:if test="planDetailEntity.execPlanDetailId != null">
							<s:if test="aConfirmPoint && viewModel != 1 && planDetailEntity.infoConfirmedFlg == 0">
								<div class="btn_blue_55_20" style="float:left;" onclick="if(confirm('确认过的计划信息将不能再被信息录入员编辑，请问继续吗？')){confirmInfo('${planDetailEntity.execPlanDetailId}','1');}">确认</div>
							</s:if>
							<s:elseif test="planDetailEntity.infoConfirmedFlg == 1">
								<s:if test="aExecSuperAdmin">
									<input type="button" class="button_green" onclick="confirmInfo('${planDetailEntity.execPlanDetailId}','0');" value="取消确认"/>
								</s:if>
							</s:elseif>
						</s:if> --%>
						
						<s:if test="planDetailEntity.execPlanDetailId != null && planDetailEntity.infoConfirmedFlg == 1 && viewModel != 1">
							<s:if test="aExecSuperAdmin">
									<s:if test="aCompletePoint">
									<input type="button" class="button_blue" 
										onclick="if(confirm('确认将当前计划置为完成状态吗？')){changePlanDetailStatus($(this), '${planDetailId}', '2');}" value="完成"/>
										
									</s:if>
									<input type="button" class="button_blue" 
										onclick="if(confirm('确认将当前计划置为预完成状态吗？')){changePlanDetailStatus($(this), '${planDetailId}', '3');}"value="预完成"/>
									
									<input type="button" class="button_blue" 
										onclick="if(confirm('确认将当前计划恢复为进行中状态吗？')){changePlanDetailStatus($(this), '${planDetailId}', '0');}" value="回进行中"/>
									
							</s:if>
							<s:elseif test="aCompletePoint">
								<input type="button" class="button_blue"
									onclick="if(confirm('确认将当前计划置为预完成状态吗？')){changePlanDetailStatus($(this), '${planDetailId}', '3');}" value="预完成"/>
								
								<s:if test="planDetailEntity.status == 0 || planDetailEntity.status == 3">
									<input type="button" class="button_blue"
										onclick="if(confirm('确认将当前计划置为完成状态吗？')){changePlanDetailStatus($(this), '${planDetailId}', '2');}" value="完成"/>
							   </s:if>
								<s:if test="planDetailEntity.status == 1 || planDetailEntity.status == 2">
									<input type="button" class="button_blue"
										onclick="if(confirm('确认将当前计划恢复为进行中状态吗？')){changePlanDetailStatus($(this), '${planDetailId}', '0');}" value="取消<s:if test='if_bis'>预</s:if>完成"/>
								</s:if>
							</s:elseif>
							<s:elseif test="aWritePoint">
								<s:if test="planDetailEntity.status == 0">
									<input type="button" class="button_blue"
										onclick="if(confirm('确认将当前计划置为预完成状态吗？')){changePlanDetailStatus($(this), '${planDetailId}', '3');}" value="预完成"/>
								</s:if>
								<s:if test="planDetailEntity.status == 1 || planDetailEntity.status == 3">
									<input type="button" class="button_blue"
										onclick="if(confirm('确认将当前计划恢复为进行中状态吗？')){changePlanDetailStatus($(this), '${planDetailId}', '0');}" value="取消<s:if test='if_bis'>预</s:if>完成"/>
										
								</s:if>
							</s:elseif>
						</s:if>
					</td>
				</tr>
			</table>
			</s:form>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed; width: 100%;">
				<tr>
					<td align="right" width="90px" style="border-right:1px solid #DBDBDB;">输出成果：</td>
					<td style="padding-left: 6px; height:200px; overflow-x:hidden;" valign="top">
						<div id="planDetailOutputList">
							<div style="color: #6BAD42; font-weight: bold; text-align: center;">
								提示：请提交"${outFileTile}"
							</div>
							<s:if test="planDetailOutput.size == 0&&outFileTile==null">
							   <div style="color: #6BAD42; font-weight: bold; text-align: center;">
									无输出成果
								</div>
							</s:if>
							<s:else>
								<table cellpadding="0" cellspacing="0" class="planDetailOutput" style="overflow-x:hidden;" border="0">
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
												<span <s:if test="aOnlyCreator && creator==myUiid || !aOnlyCreator"> style="text-decoration:underline; color:#5A5A5A; cursor:pointer;" title="点击下载附件" onclick="window.open('${downUrl}'); return false;"</s:if> 
													<s:else> title="您不是上传者，无法下载附件"</s:else>
													>${realFileName}</span> &nbsp;&nbsp;
											</td>
											<td title="上传人">
												<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"),";")%>
											</td>
											<s:if test="aExecSuperAdmin || aExecAdmin || (aOnlyCreator && creator==myUiid || !aOnlyCreator)">
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
						<td align="right" style="border-right:1px solid #DBDBDB;">上传附件： </td>
						<td style="padding-left: 6px;">
							<s:form id="outputUploadForm" action="/app/app-attachment!uploadForAttention.action" enctype="multipart/form-data">
								<input type="hidden" name="bizModuleCd" value="execPlan" />
								<input type="hidden" name="bizEntityName" value="ExecPlanDetail" />
								<input type="hidden" id="bizEntityIdHid" name="bizEntityId" value="${outputBizEntityId}" />
								<div style="float:left; display:inline;padding-top:6px;"><input type="file" id="uploadInput" name="upload" style="height: 25px; "/></div>
								<input type="button" onclick="uploadAttachment(); return false;" style="height: 25px;width:50px;margin-top:5px;" class="btn_green" value="上传"/>
								<%-- <div style="float:left; display:inline;padding-top:5px;"></div>--%>
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
						<div id="messageDiv" style="<s:if test="planDetailEntity.execPlanNode.execPlanNodeId=='2416' || planDetailEntity.execPlanNode.execPlanNodeId=='2421' || planDetailEntity.execPlanNode.execPlanNodeId=='2461' || planDetailEntity.execPlanNode.execPlanNodeId=='2462'">height:100px;</s:if><s:else>height:300px;</s:else> overflow-y:scroll;">
						<s:iterator value="planDetailEntity.execPlanDetailMesses">
							<div class="divMessContent${sysType} <s:if test="1==sysType"> color_black</s:if>">
								<pre style="padding-left:2px; "><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>(<s:date name="createdDate" format="MM-dd HH:mm"/>):<s:property value="messContent" escape="true"/></pre>
							</div>
						</s:iterator>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding-bottom:4px;padding-top:4px;">
						<div style="float:left;">
							<textarea id="newMessage" name="newMessage" rows="3" style="width:230px; height:50px;"></textarea>
						</div>
						<div style="float:left;padding-top:5px;">
							<input type="button" class="button_green" style="height:50px;"  onclick="saveMessage('${planDetailEntity.execPlanDetailId}');" value="留言"/>
							<input type="button" class="button_green" style="height:50px;"  id="btn_msg_switch" value="用户留言"/>
						</div>
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
										<s:elseif test="statusCd==2"><img src="${ctx}/images/plan/pic_finish.gif" title="已完成"></s:elseif>
										<s:elseif test="statusCd==3"><img src="${ctx}/images/plan/pic_preFinish.gif" title="预完成"></s:elseif>
										<s:elseif test="statusCd==4">已删除</s:elseif>
										<s:elseif test="statusCd==5"><img src="${ctx}/images/plan/pic_hide.gif" title="已隐藏"></s:elseif>
									</td>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
				</s:if>
				<s:if test="planDetailEntity.execPlanNode.execPlanNodeId=='2416' || planDetailEntity.execPlanNode.execPlanNodeId=='2421' || planDetailEntity.execPlanNode.execPlanNodeId=='2461' || planDetailEntity.execPlanNode.execPlanNodeId=='2462'">
				<tr>
					<td colspan="2">
						<s:if test="planDetailEntity.execPlanNode.execPlanNodeId==2462">次</s:if>主力店<s:if test="planDetailEntity.execPlanNode.execPlanNodeId=='2416' || planDetailEntity.execPlanNode.execPlanNodeId=='2421'">签约</s:if><s:if test="planDetailEntity.execPlanNode.execPlanNodeId=='2461' || planDetailEntity.execPlanNode.execPlanNodeId=='2462'">进场</s:if>情况:
						<button class="btn_green" onclick="doAddPlus()" <s:if test="!aAddFlg">disabled</s:if>>新增</button>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div style="height:200px; overflow:auto;">
							<table width="100%" border="1" cellpadding="0" cellspacing="0">
								<tr>
									<th style="background-color:#AAABB0;">商家名称</th>
									<th style="background-color:#AAABB0;">计划签约时间</th>
									<th style="background-color:#AAABB0;">状态</th>
									<th style="background-color:#AAABB0;">实际签约时间</th>
									<th style="background-color:#AAABB0;">操作</th>
								</tr>
								<tr id="trAddPlus" <s:if test="listExecPlanDetailPlus.size>0">style="display:none;"</s:if>>
									<td><input id="shopName_add" type="text" style="width:110px;"/></td>
									<td><input id="scheduleEndDate_add" type="text" style="width:70px;" onclick="WdatePicker();"/></td>
									<td>新增中</td>
									<td><input id="realEndDate_add" type="text" style="width:70px;" onclick="WdatePicker();"/></td>
									<td><button class="btn_green" style="width:34px;" onclick="doSavePlus(null,'${planDetailEntity.execPlanDetailId}')">保存</button></td>
								</tr>
								<s:iterator value="listExecPlanDetailPlus" var="plusEntity">
								<security:authorize ifAnyGranted="A_EXEC_BIS_ADMIN">
								<tr>
									<td><input id="shopName_${plusEntity.execPlanDetailPlusId}" type="text" style="width:110px;" value="${plusEntity.shopName}" /></td>
									<td><input id="scheduleEndDate_${plusEntity.execPlanDetailPlusId}" type="text" style="width:70px;" value="<s:date name="scheduleEndDate" format="yyyy-MM-dd" />" onclick="WdatePicker();"/></td>
									<td nowrap="nowrap">
										<s:if test="status==0"><span class="color_yellow">未确认</span></s:if>
										<s:if test="status==1">
											<s:if test="'going'==remark"><span class="color_blue">进行中</span></s:if>
											<s:else><span class="color_red">过期</span></s:else>
										</s:if>
										<s:if test="status==2"><span class="color_green">完成</span></s:if>
										<s:if test="status==3"><span class="color_yellow">预完成</span></s:if>
									</td>
									<td><input id="realEndDate_${plusEntity.execPlanDetailPlusId}" type="text" style="width:70px;" value="<s:date name="realEndDate" format="yyyy-MM-dd" />" onclick="WdatePicker();"/></td>
									<td>
										<button class="btn_green" style="width:34px;" onclick="doSavePlus('${plusEntity.execPlanDetailPlusId}','${planDetailEntity.execPlanDetailId}')">保存</button>
										<s:if test="status==0"><button class="btn_green" style="width:34px;" onclick="doSavePlus('${plusEntity.execPlanDetailPlusId}','${planDetailEntity.execPlanDetailId}','1')">确认 </button></s:if>
										<s:if test="status==1 or status==3"><button class="btn_green" style="width:34px;" onclick="doSavePlus('${plusEntity.execPlanDetailPlusId}','${planDetailEntity.execPlanDetailId}','2')">完成</button></s:if>
										<s:if test="status==1"><button class="btn_green" style="width:34px;" onclick="doSavePlus('${plusEntity.execPlanDetailPlusId}','${planDetailEntity.execPlanDetailId}','3')">预完 </button></s:if>
										<s:if test="status==2"><button class="btn_green" style="width:34px;" onclick="doSavePlus('${plusEntity.execPlanDetailPlusId}','${planDetailEntity.execPlanDetailId}','0')">退回 </button></s:if>
										<s:if test="status==3"><button class="btn_green" style="width:34px;" onclick="doSavePlus('${plusEntity.execPlanDetailPlusId}','${planDetailEntity.execPlanDetailId}','0')">退回 </button></s:if>
										<s:if test="status==1"><button class="btn_green" style="width:34px;" onclick="doSavePlus('${plusEntity.execPlanDetailPlusId}','${planDetailEntity.execPlanDetailId}','5')">删除 </button></s:if>
									</td>
								</tr>
								</security:authorize>
								<security:authorize ifNotGranted="A_EXEC_BIS_ADMIN">
								<tr>
									<td><input id="shopName_${plusEntity.execPlanDetailPlusId}" type="text" style="width:110px;" value="${plusEntity.shopName}" disabled/></td>
									<td><input id="scheduleEndDate_${plusEntity.execPlanDetailPlusId}" type="text" style="width:70px;" value="<s:date name="scheduleEndDate" format="yyyy-MM-dd" />" onclick="WdatePicker();" <s:if test="status!=1">disabled</s:if>/></td>
									<td nowrap="nowrap">
										<s:if test="status==0"><span class="color_yellow">未确认</span></s:if>
										<s:if test="status==1">
											<s:if test="'going'==remark"><span class="color_blue">进行中</span></s:if>
											<s:else><span class="color_red">过期</span></s:else>
										</s:if>
										<s:if test="status==2"><span class="color_green">完成</span></s:if>
										<s:if test="status==3"><span class="color_yellow">预完成</span></s:if>
									</td>
									<td><input id="realEndDate_${plusEntity.execPlanDetailPlusId}" type="text" style="width:70px;" value="<s:date name="realEndDate" format="yyyy-MM-dd" />" onclick="WdatePicker();" <s:if test="status!=1">disabled</s:if>/></td>
									<td>
										<button class="btn_green" style="width:34px;" onclick="doSavePlus('${plusEntity.execPlanDetailPlusId}','${planDetailEntity.execPlanDetailId}')">保存</button>
										<s:if test="status==1"><button class="btn_green" style="width:34px;" onclick="doSavePlus('${plusEntity.execPlanDetailPlusId}','${planDetailEntity.execPlanDetailId}','3')">预完</button></s:if>
										<s:if test="status==3"><button class="btn_green" style="width:34px;" onclick="doSavePlus('${plusEntity.execPlanDetailPlusId}','${planDetailEntity.execPlanDetailId}','1')">退回 </button></s:if>
										<s:if test="status==0"><button class="btn_green" style="width:34px;" onclick="doSavePlus('${plusEntity.execPlanDetailPlusId}','${planDetailEntity.execPlanDetailId}','5')">删除 </button></s:if>
									</td>
								</tr>
								</security:authorize>
								</s:iterator>
							</table>
						</div>
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
		
		$("#btn_msg_switch").click(function(){
			switch($(this).val()){
			    case "0":{
			    	$(this).text("所有留言");
			    	$(this).val("1");
			    	$(".divMessContent0").show();
			    	$(".divMessContent1").hide();
			        break;
			    }
			    case "1":{
			    	$(this).text("系统留言");
			    	$(this).val("2");
			    	$(".divMessContent1").show();
			    	$(".divMessContent0").hide();
			        break;
			    }
			    default:{
			    	$(this).text("用户留言");
			    	$(this).val("0");
			    	$(".divMessContent1").show();
			    	$(".divMessContent0").show();
			        break;
			    }
			 }
		});
	});
</script>
