<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<script type="text/javascript">
//打开要编辑的工作计划--与商业同用，商业有自己特殊需求
function openEditPlanTarget(id) {
	if(lastPlanTargetId) {
		lastDisplay = $("#edit_monthPlan_" + lastPlanTargetId).css("display");
		if(lastDisplay == "block" && lastPlanTargetId != id){
			$("#edit_monthPlan_" + lastPlanTargetId).hide();
		}
	}
	var display = $("#edit_monthPlan_" + id).css("display");
	if(display == "none") {
		// 计划类型ID编号转换为文本
		$("#td_edit_pt_" + id + " input[class=editPlanType]").val($.trim($("#td_pt_" + id).html()));
		// 获取附件列表
		fileList(id);
		$("#edit_monthPlan_" + id).show();
		editPlanNumber = $("#editPlanNumber_" + id).val();
		editPlanType = $("#td_edit_pt_" + id + " input[class=editPlanType]").val();
		editContent = $("#td_edit_ct_" + id + " textarea").val();
		editTargetDate = $("#td_edit_td_" + id + " input").val();
		$("#tr_" + id).css({"background-color": "#D9DEE6"});
	} else {
		$("#edit_monthPlan_" + id).hide();
		$("#tr_" + id).css("background-color", "#FFF");
	}
	lastPlanTargetId = id;
	//autoHeight();
}
</script>

<!-- 显示内容模块 -->
			<table class="mainTable" cellpadding="0" cellspacing="0" width="100%">
				<thead style="background-color:#DDDBDC;">
					<tr>
						<td class="first" nowrap="nowrap" style="width:45px;">序号</td>
						<td class="headTd sort" nowrap="nowrap" onclick="clickSortCost('lx');" sortCol="lx" title="" style="width:60px;">类型<label class="">&nbsp;</label></td>
						<td class="headTd" width="70%">工作内容</td>
						<td class="headTd sort" nowrap="nowrap" onclick="clickSortCost('mb');" sortCol="mb" title="" style="width:60px;">目标<label class="">&nbsp;</label></td>
						<td class="headTd sort" nowrap="nowrap" onclick="clickSortCost('zt');" sortCol="zt" title="" style="width:60px;">状态<label class="">&nbsp;</label></td>
						<td class="headTd sort" nowrap="nowrap" onclick="clickSortCost('gx');" sortCol="gx" title="" style="width:60px;">更新<label class="">&nbsp;</label></td>
						<td class="headTd" nowrap="nowrap" style="width:45px;">附件</td>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="page.result" status="st" id="">
					<tr id="tr_${planTargetId}" onmouseover="mouseOver('${planTargetId}');" onmouseout="mouseOut('${planTargetId}');" <s:if test="status==7">style="color:#999999;"</s:if>>
						<td id="td_pn_${planTargetId}" class="bodyTd" nowrap="nowrap" onclick="openEditPlanTarget('${planTargetId}');"><span class="subPlanNumber"><s:property value="planNumber"/></span></td>
						<td id="td_pt_${planTargetId}" class="bodyTd" nowrap="nowrap" onclick="openEditPlanTarget('${planTargetId}');">
							<s:if test="planType==1">年计划</s:if>
							<s:if test="planType==2">月会工作</s:if>
							<s:if test="planType==3">项目管理</s:if>
							<s:if test="planType==4">指令单</s:if>
							<s:if test="planType==5">综合</s:if>
							<s:if test="planType==6">季度KPI</s:if>
							<s:if test="planType==7">四级计划</s:if>
							<s:if test="planType==8">中心工作</s:if>
						</td>
						<td id="td_ct_${planTargetId}" class="bodyTd pd-chill-tip" onclick="openEditPlanTarget('${planTargetId}');" title="<p:code2name mapCodeName="mapContentTips" value="planTargetId" />">
						<div class="ellipsis_Div"><s:property value="content"/></div>
						</td>
						<td id="td_td_${planTargetId}" class="bodyTd" nowrap="nowrap" onclick="openEditPlanTarget('${planTargetId}');"><s:date name="targetDate" format="MM-dd"/></td>
						<td id="td_st_${planTargetId}" class="bodyTd" nowrap="nowrap" onclick="openEditPlanTarget('${planTargetId}');">
							<s:if test="status==0"><div class="unexec" title="未执行">&nbsp;</div></s:if>
							<s:elseif test="status==1"><div class="process" title="进行中" >&nbsp;</div></s:elseif>
							<s:elseif test="status==2"><div class="prefinish" title="预完成" >&nbsp;</div></s:elseif>
							<s:elseif test="status==3"><div class="finish" title="完成" >&nbsp;</div></s:elseif>
							<s:elseif test="status==4"><div class="completedelay" title="完成但曾经过期" >&nbsp;</div></s:elseif>
							<s:elseif test="status==5"><div class="suspend" title="过期" >&nbsp;</div></s:elseif>
							<s:elseif test="status==6"><div class="delay" title="非考核性过期">&nbsp;</div></s:elseif>
							<s:elseif test="status==7"><div class="unmonth" title="非本月任务">&nbsp;</div></s:elseif>
							<s:elseif test="status==8"><div class="hidden" title="隐藏" >&nbsp;</div></s:elseif> 
							<s:elseif test="status==9"><div class="predel" title="申请删除">&nbsp;</div></s:elseif>
							<s:elseif test="status==10"><div class="delete" title="删除">&nbsp;</div></s:elseif>
						</td>
						<td id="td_ud_${planTargetId}" class="bodyTd" nowrap="nowrap" onclick="openEditPlanTarget('${planTargetId}');"><s:date name="updatedDate" format="MM-dd"></s:date></td>
						<td class="bodyTd" nowrap="nowrap"><span id="edit_atta_imgSpan_${planTargetId}" style="cursor:pointer;" onclick='openAttachment("附件管理","${planTargetId==null?entityTmpId_New:planTargetId}");' title="上传附件">
							<s:if test="attachFlg == 1"><div class="attach" title="附件">&nbsp;</div></s:if></span>
						</td>
					</tr>
					<tr><td colspan="8" style="border-bottom:none;padding-left:0px;">
						<!-- 编辑中心月计划 -->
						<div id="edit_monthPlan_${planTargetId}" class="editMonthPlan" style="display: none;">
						<form action="${ctx}/plan/plan-target!save.action" name="" id="editPlanTarget_${planTargetId}" method="post">
						<input type="hidden" name="id" value="${planTargetId}"/>
                         <input type="hidden" name="recordVersion" value="${recordVersion}"/>
							<table class="editTable"><thead>
								<tr>
									<col width='8%'/>
								<col width='7%'/>
								<col width='35%'/>
								<col width='5%'/>
								<col width='7%'/>
								<col/>
								<tr valign="top">
									<td id="td_edit_sn_${planTargetId}" style="padding-right:5px;padding-left:26px;"><input id="editSequenceNumber_${planTargetId}" name="sequenceNumber" value="${sequenceNumber}" class="editSequenceNumber"/></td>
									<td id="td_edit_pt_${planTargetId}" style="padding-right:5px;">
									<input class="editPlanType" value="${planType}"/>
									<div id="selectType_${planTargetId}" class="selectType">
										<ul>
										<s:iterator value="mapPlanType" id="" status="st">
											<s:if test="key!=''">
												<li value="${key}" onclick="clickType('${planTargetId}', this.value);" title="${value}">${value}</li>
											</s:if>
										</s:iterator>
										</ul>
									</div>
									<input type="hidden" id="editPlanTypeVal_${planTargetId}" name="planType" value="${planType}"/>
									</td>
									<td id="td_edit_ct_${planTargetId}" style="padding-right:5px;"><s:textarea name="content" cssClass="editContent"/></td>
									<td id="td_edit_td_${planTargetId}" style="padding-right:5px;"><input id="editTargetDate_${planTargetId}" value='<s:date name="targetDate" format="MM-dd"/>' class="Wdate editTargetDate" onfocus="WdatePicker({ dateFmt: 'MM-dd' });"/><input type="hidden" id="targetDate_${planTargetId}" name="targetDate" value="<s:date name="targetDate" format="yyyy-MM-dd"/>"/></td>
									<td style="padding-right:5px;"><input class="editStatus" id="edit_st_${planTargetId}" 
										<s:if test="status==0">value="未执行"</s:if>
										<s:elseif test="status==1">value="进行中"</s:elseif>
										<s:elseif test="status==2">value="预完成"</s:elseif>
										<s:elseif test="status==3">value="完成"</s:elseif>
										<s:elseif test="status==4">value="完成但曾经过期"</s:elseif>
										<s:elseif test="status==5">value="过期"</s:elseif>
										<s:elseif test="status==6">value="非考核性过期"</s:elseif>
										<s:elseif test="status==7">value="非本月任务"</s:elseif>
										<s:elseif test="status==8">value="隐藏"</s:elseif>
										<s:elseif test="status==9">value="申请删除"</s:elseif>
										<s:elseif test="status==10">value="删除"</s:elseif>
									 readonly="readonly"/>
									 <input type="hidden" id="editStatusVal_${planTargetId}" name="status" value="${status}"/>
									 </td>
									<td style="width: 38%;padding-top:3px;padding-left:5px;">
									<label style="height:24px;">编号：</label>${planNumber}&nbsp;&nbsp;
									<label>创建时间：</label><s:date name="createdDate" format="MM-dd"/>
									<s:if test="creator!=null&&creator!=''"><label>创建人：</label><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator"))%></s:if>
								    </td>
								</tr>
								<tr>
									<td style="padding-left:21px;" colspan="5" valign="top" class="td">
									<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_CENTER,A_PLAN_WORK2_CEO,A_PLAN_WORK2_VICE">
									<s:if test="!(planType==1||lockFlg==1)&&haveOperAuth=='true'">
									<input type="button" onclick="doSubmitEdit('${planTargetId}');" class="btn btn_Green"  value="&nbsp;&nbsp;保存&nbsp;&nbsp;"/>
									</s:if>
									<s:else>
									<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_CEO">
									 <input type="button" onclick="doSubmitEdit('${planTargetId}');" class="btn btn_Green"  value="&nbsp;&nbsp;保存&nbsp;&nbsp;"/>
									</security:authorize>
									</s:else>
									</security:authorize>
									<input type="button" onclick="openEditPlanTarget('${planTargetId}');" class="btn btn_Green"  value="&nbsp;&nbsp;返回&nbsp;&nbsp;"/>
									</td>
									<td class="td">
									<table id="statusBtn_${planTargetId}">
										<thead>
											<tr><td>
											  <s:if test="haveOperAuth=='true'">
												<s:if test="!(status==3||status==4)">
												<security:authorize ifAnyGranted="A_PLAN_WORK2_CENTER,A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_VICE,A_PLAN_WORK2_CEO">
												<input type="button" class="btn btn_Blue" onclick="doSubmitEdit('${planTargetId}', '2');" value="&nbsp;预完成&nbsp;" title="预完成"/>
												</security:authorize>
												</s:if>
												<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_CEO">
												<s:if test="!(status==3||status==4)">
												<input type="button" class="btn btn_Blue" onclick="doSubmitEdit('${planTargetId}', '3');" value="&nbsp;完成&nbsp;" title="完成"/>
												</s:if>
												</security:authorize>
												<%-- 
												<input type="button" class="btn btn_Green" onclick="doSubmitEdit('${planTargetId}', '0');" value="&nbsp;未执行&nbsp;" title="未执行"/>
												 --%>
												<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_CEO">
												<input type="button" class="btn btn_Green" onclick="doSubmitEdit('${planTargetId}', '7');" value="&nbsp;非本月&nbsp;" title="非本月"/>
											    </security:authorize>
											    </s:if>
											</td></tr>
											<tr><td class="td">
											   <s:if test="haveOperAuth=='true'">
												<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_CEO,A_PLAN_WORK2_VICE">
												<s:if test="planType!=1&&lockFlg==0">
												<input type="button" class="btn btn_Red" onclick="doSubmitEdit('${planTargetId}', '10');" value="&nbsp;删除&nbsp;" title="删除"/>
												</s:if>
												<s:elseif test="planType!=1">
												 <security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN">
												  <input type="button" class="btn btn_Red" onclick="doSubmitEdit('${planTargetId}', '10');" value="&nbsp;删除&nbsp;" title="删除"/>
												 </security:authorize>
												</s:elseif>
												</security:authorize>
												<security:authorize ifAnyGranted="A_PLAN_WORK2_CENTER,A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_VICE,A_PLAN_WORK2_CEO">
												<s:if test="!(status==3||status==4)">
												<s:if test="planType!=1">
												<input type="button" class="btn btn_Red" onclick="doSubmitEdit('${planTargetId}', '9');" value="&nbsp;申请删除&nbsp;" title="申请删除"/>
												</s:if>
												</s:if>
												</security:authorize>
												<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_CEO">
												<s:if test="(!(status==5||status==6))||(''==parentTargetId||null==parentTargetId)">
												<input type="button" class="btn btn_Red" onclick="doSubmitEdit('${planTargetId}', '5');" value="&nbsp;过期&nbsp;" title="过期"/>
												<input type="button" class="btn btn_Red" onclick="doSubmitEdit('${planTargetId}', '6');" value="&nbsp;非考核性过期&nbsp;" title="非考核性过期"/>
												</s:if>
												<input type="button" class="btn btn_Red" onclick="doSubmitEdit('${planTargetId}', '8');" value="&nbsp;隐藏&nbsp;" title="隐藏"/>
												</security:authorize>
												<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_CEO">
												<s:if test="(!(status==5||status==6))||(''==parentTargetId||null==parentTargetId)">
												<input type="button" class="btn btn_Red" style="float:left;" onclick="doSubmitEdit('${planTargetId}', '-1');" value="&nbsp;驳回&nbsp;" title="驳回"/>
											    </s:if>
											    </security:authorize>
											    </s:if>
											</td></tr>
										</thead>
									</table>
									</td>
								</tr>
								<tr>
									<td valign="top" class="td2" style="padding-left:26px;" colspan="5">留言：
									<div id="msgList_${planTargetId}">
									<s:iterator value="planMessages" status="st">
										<div style="width:90%;"><span style="color:#18478d;"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %></span>(<s:date name="createdDate" format="MM-dd HH:MM"/>):${content}</div>
									</s:iterator>
									</div>
									<textarea id="editMessage_${planTargetId}" name="planMessages[${fn:length(planMessages)}].content" 
										style="width: 300px;height: 50px;float: left;border: 1px solid #7F9DB9;"></textarea><input type="button" onclick="doSubmitMessage('${planTargetId}');" class="msgBtn pub btn_Green" value="留言"/>
									</td>
									<td valign="top">
									 <div id="editAttach">
												<table cellpadding="0px" cellspacing="0px;">
													<thead>
													<tr>
														<td width="50px;"><label style="font-weight: bold;padding-left: 5px;">附件：</label></td>
														<td colspan="3">
														<s:if test="haveOperAuth=='true'&&lockFlg!=1">
														<security:authorize ifAnyGranted="A_PLAN_WORK2_CENTER,A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_CEO,A_PLAN_WORK2_VICE,A_PLAN_WORK_STAFF">
														<label onclick='openAttachment("附件管理","${planTargetId==null?entityTmpId_New:planTargetId}");' class="link">添加附件</label>&nbsp;|&nbsp;可以上传小于40M的附件
														</security:authorize>
														</s:if>
														<s:elseif test="haveOperAuth=='true'">
														<security:authorize ifAnyGranted="A_PLAN_WORK2_CENTER,A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_CEO,A_PLAN_WORK2_VICE">
														<label onclick='openAttachment("附件管理","${planTargetId==null?entityTmpId_New:planTargetId}");' class="link">添加附件</label>&nbsp;|&nbsp;可以上传小于40M的附件
														</security:authorize>
														</s:elseif>
														</td>
													</tr>
													</thead>
													<tbody>
													<tr>
														<td></td>
														<td><span onclick="" class="link">&nbsp;</span></td>
														<td>&nbsp;</td>
														<td><span onclick="" class="del">&nbsp;</span></td>
													</tr>
													</tbody>
												</table>
												</div>
													<input type="hidden" name="attach"/>
									</td>
								</tr>
							</thead></table>
							</form>
						</div>
					</td></tr>
					</s:iterator>
				</tbody>
			</table>
