<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<%--计划管理员一直有新建计划权限，其它的角色，只有当月未被锁的时候可以操作 --%>
<div class="addTab">
       <c:if test="${currentCenterCd=='153'||currentCenterCd=='155' ||currentCenterCd =='156' ||currentCenterCd =='154' ||currentCenterCd =='157' ||currentCenterCd =='869' ||currentCenterCd =='439' ||currentCenterCd =='453'}">
		<div style="padding-top:8px;color:#F46614;font-weight: bold;float:left;">
		共<span id="totCount">${totCount}</span>条记录，其中完成<span id="compCount">${compCount}</span>条，
		完成率<span id="compRate">${compRate}</span>%&nbsp;&nbsp;
		</div>
       </c:if>
   <div style="float: left;">
        <input type="button" style="height:26px;background-color: #FFFFFF;border:none;"/>
		<security:authorize ifAnyGranted="A_PLAN_WORK2_CENTER,A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_CEO,A_PLAN_WORK2_VICE,A_PLAN_WORK_STAFF">
		<s:if test="lockFlg!=1&&haveOperAuth=='true'">
		<input type="button" id="addPlan" class="btn_Blue newBtn" onclick="hideOrShow_New();" value="&nbsp;新建计划"/>
		</s:if>
		<s:else>
		<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_CEO">
		<input type="button" class="btn_Blue newBtn" onclick="hideOrShow_New();" value="&nbsp;新建计划"/>
		</security:authorize>
		</s:else>
        </security:authorize>
        <security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_CEO">
		<s:if test="lockFlg!=1">
		<input type="button" class="btn_Blue inputBtn" onclick="onlock();" value="锁定"/>
		</s:if>
		<s:else>
		<input type="button" class="btn_Blue inputBtn" onclick="unlock();" value="解锁"/>
		</s:else>
		</security:authorize>
	</div>
</div>
<form id="delPlanTarget" action="" method="post"></form>
<!-- 显示内容模块 -->
<div style="margin-top:30px;">
			<table id="contentTable" class="mainTable" cellpadding="0" cellspacing="0" width="100%">
			   <col width="30"/>
				    <col width="50"/>
				    <col width="40"/>
				    <col width="80"/>
				    <col/>
				    <col width="60"/>
				    <col width="60"/>
				    <col width="60"/>
				    <col width="45"/>
				<thead style="background-color:#DDDBDC;">
					<tr>
						<td class="first" nowrap="nowrap"><input type="checkbox" id="cbxAll" onclick="selectAll();" style="height:auto;border:none;" title="全选"/></td>
					    <td class="headTd sort" nowrap="nowrap" onclick="clickSort('sh');" sortCol="sh" title="点击排序">序号<label class="">&nbsp;&nbsp;</label></td>
						<td class="headTd" nowrap="nowrap">关注&nbsp;&nbsp;</td>
						<td class="headTd sort" nowrap="nowrap" onclick="clickSort('lx');" sortCol="lx" title="点击排序">类型<label class="">&nbsp;&nbsp;</label></td>
						<td class="headTd"  nowrap="nowrap">工作内容</td>
						<td class="headTd sort" nowrap="nowrap" onclick="clickSort('mb');" sortCol="mb" title="点击排序">目标<label class="">&nbsp;&nbsp;</label></td>
						<td class="headTd sort" nowrap="nowrap" onclick="clickSort('zt');" sortCol="zt" title="点击排序">状态<label class="">&nbsp;&nbsp;</label></td>
						<td class="headTd sort" nowrap="nowrap" onclick="clickSort('gx');" sortCol="gx" title="点击排序">更新<label class="">&nbsp;&nbsp;</label></td>
						<td class="headTd" nowrap="nowrap">附件</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="9" style="border-bottom: none;padding-left: 0px;">
							<!-- 新建中心月计划 -->
							<div id="newMonthPlan" style="display: none;">
								<form action="${ctx}/plan/plan-target!save.action" id="newPlanTarget" method="post">
								<input type="hidden" id="newCenter" name="center" value="${currentCenterCd}">
								<input type="hidden" id="targetDate" name="targetDate" />
								<input type="hidden" name="lockFlg" id="lockFlg" value="${lockFlg}"/>
								<table>
									<thead>
									<tr><td colspan="4">&nbsp;</td></tr>
									<tr>
										<td colspan="4"><label style="font-weight: bold;padding-left: 14px;">新建中心计划</label>&nbsp;&nbsp;创建人：<%=SpringSecurityUtils.getCurrentUserName() %></td>
									</tr>
									<tr><td colspan="4">&nbsp;</td></tr>
									<tr>
										<td align="right"><%--编号： --%></td>
										<td><%--<s:property value="PlanNumberNew"/> --%>
										<input type="hidden" id="planNumber" name="planNumber" value="<s:property value="PlanNumberNew"/>"/></td>
										<td>类型：
										  <input class="editPlanType" id="edit_plan_type" style="width:100px;"/>
											<div id="selectNewType" class="selectType" style="margin-left:40px;">
												<ul>
												<s:iterator value="mapPlanType" id="" status="st">
													<s:if test="key!=''">
														<li value="${key}" onclick="clickNewType(this,'${value}','${key}');" title="${value}">${value}</li>
													</s:if>
												</s:iterator>
												</ul>
											</div>
											<input type="hidden" id="editPlanNewType" name="planType"/>
										</td>
										<td>目标月份：<input type="text" id="newTime" onclick="selectNewTime();"/>
										<div id="selectNewMonth" style="display: none;">
												<div class="monthNewDiv">请选择月份</div>
												<input type="button" class="btnCancel" value="01" key="01" onclick="selectNewMonth(01);"/>
												<input type="button" class="btnCancel" value="02" key="02" onclick="selectNewMonth(02);"/>
												<input type="button" class="btnCancel" value="03" key="03" onclick="selectNewMonth(03);"/>
												<div style="clear: both;"></div>
												<input type="button" class="btnCancel" value="04" key="04" onclick="selectNewMonth(04);"/>
												<input type="button" class="btnCancel" value="05" key="05" onclick="selectNewMonth(05);"/>
												<input type="button" class="btnCancel" value="06" key="06" onclick="selectNewMonth(06);"/>
												<div style="clear: both;"></div>
												<input type="button" class="btnCancel" value="07" key="07" onclick="selectNewMonth(07);"/>
												<input type="button" class="btnCancel" value="08" key="08" onclick="selectNewMonth(08);"/>
												<input type="button" class="btnCancel" value="09" key="09" onclick="selectNewMonth(09);"/>
												<div style="clear: both;"></div>
												<input type="button" class="btnCancel" value="10" key="10" onclick="selectNewMonth(10);"/>
												<input type="button" class="btnCancel" value="11" key="11" onclick="selectNewMonth(11);"/>
												<input type="button" class="btnCancel" value="12" key="12" onclick="selectNewMonth(12);"/>
												<div style="clear: both;"></div>
											</div>
										<%--
										<input id="targetDate" name="targetDate" value='<s:date name="targetDate" format="yyyy-MM-dd"/>'  class="Wdate" onfocus="WdatePicker();" onblur="doVal_targetDate();"/><label id="targetDateVal" style="color:red;display: none;">&nbsp;*</label>
										 --%>
										</td>
									</tr>
									<tr><td colspan="4">&nbsp;</td></tr>
									<tr>
										<td align="right">工作内容：</td>
										<td colspan="3"><s:textarea id="content" name="content" cssStyle="width:600px;height:60px;" onblur="doVal_content();"/><label id="contentVal" style="color:red;display: none;">&nbsp;*</label></td>
									</tr>
									<tr><td colspan="4">&nbsp;</td></tr>
									<tr>
										<td align="right">留言：</td>
										<td colspan="3"><s:textarea id="planMessages" name="planMessages[0].content" cssStyle="width:400px;height:60px;"/>
										</td>
									</tr>
									<tr><td colspan="4">&nbsp;</td></tr>
									<tr>
										<td></td>
										<td colspan="3"><input type="button" class="btn btn_Green" onclick="doSubmit();" value=" 提交 "/>&nbsp;<input type="button" class="btn btn_Green" onclick="hideOrShow_New();" value=" 返回 "/></td>
									</tr>
									<tr><td colspan="4">&nbsp;</td></tr>
									</thead>
								</table>
								</form>
							</div>
						</td>
					</tr>
					<s:iterator value="page.result" status="st" id="">
					<tr id="tr_${planTargetId}" onmouseover="mouseOver('${planTargetId}');" onmouseout="mouseOut('${planTargetId}');" 
					  <s:if test="(status==7)||((status==5||status==6)&&!(''==parentTargetId||null==parentTargetId))">
					  style="color:#999999;" 
					  </s:if>>
						<td class="bodyTd"><input type="checkbox" style="height:auto;border:none;" name="cbx" onclick="selectOne();" value="${planTargetId}"/></td>
						<td id="td_sn_${planTargetId}" class="bodyTd" nowrap="nowrap" onclick="openEditPlanTarget('${planTargetId}');"><s:property value="sequenceNumber"/></td>
						<td id="td_attention_${planTargetId}" class="bodyTd" nowrap="nowrap">
							<div style="display:none;" id="attention_recordVersion_${planTargetId}"></div>
							<div style="display:none;" id="attention_status_${planTargetId}">${statusCd}</div>
							<div style="display:inline;"><img id="attention_${planTargetId}" title="点击取消关注" attentionFlg="1" src="${ctx}/resources/images/common/attention_cancel.png" onclick='doDeleteAttention("planTarget","${planTargetId}");' style="display:none;"/><img id="attention_cancel_${planTargetId}" title="点击关注,该条将在您的首页自动提示是否有更新" attentionFlg="0" src="${ctx}/resources/images/common/attention.png" onclick='doAddAttention("planTarget","${planTargetId}","${recordVersion}");' style="display:none;"/></div>
							<div style="display:none;" id="attention_unread_img_${planTargetId}"><img class="new_img" src="${ctx}/resources/images/common/new.gif" /></div>
						</td>
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
						<td class="bodyTd" nowrap="nowrap"><span id="edit_atta_imgSpan_${planTargetId}" style="cursor:pointer;" 
						<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN">
						onclick='openAttachment("附件管理","${planTargetId==null?entityTmpId_New:planTargetId}");'
						</security:authorize>
						>
							<s:if test="attachFlg == 1"><div class="attach" title="附件">&nbsp;</div></s:if></span>
						</td>
					</tr>
					<tr><td colspan="9" style="border-bottom:none;padding-left:0px;">
						<!-- 编辑中心月计划 -->
						<div id="edit_monthPlan_${planTargetId}" class="editMonthPlan" style="display: none;padding:5px 0;">
						<form action="${ctx}/plan/plan-target!save.action" name="" id="editPlanTarget_${planTargetId}" method="post">
						<input type="hidden" name="id" value="${planTargetId}"/>
						<input type="hidden" name="isAttentioned" value="${isAttentioned}"/>
                         <input type="hidden" name="recordVersion" value="${recordVersion}"/>
							<table class="editTable" >
							     <col width='8%'/>
								<col width='7%'/>
								<col width='35%'/>
								<col width='5%'/>
								<col width='7%'/>
								<col width='48%'/>
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
													<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_VICE">
													<input type="button" class="btn btn_Blue" onclick="doSubmitEdit('${planTargetId}', '1');" value="&nbsp;执行&nbsp;" title="执行"/>
													</security:authorize>
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
													<input type="button" class="btn btn_Red" style="float:left;" onclick="doSubmitEdit('${planTargetId}', '-1');" value="&nbsp;退回&nbsp;" title="退回"/>
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
										<div style="width:90%;word-wrap:break-word;"><span style="color:#18478d;"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %></span>(<s:date name="createdDate" format="MM-dd HH:MM"/>):${content}</div>
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
							</table>
							</form>
						</div>
						</td>
						</tr>
						<script language="javascript">
							<s:if test="attentionMap[planTargetId] == 'attention'">
								$("#attention_${planTargetId}").show();
								$("#scheForm_${planTargetId} input[name='isAttentioned']").val("attention");
							</s:if>
							<s:if test="!(attentionMap[planTargetId] == 'attention')">
								$("#attention_cancel_${planTargetId}").show();
							</s:if>
							<s:if test="attentionMapUnread[planTargetId] == 'unread'">
								$("#attention_recordVersion_${planTargetId}").html("${recordVersion}");
								$("#attention_unread_img_${planTargetId}").css("display","inline");
							</s:if>
						</script>
					</td></tr>
					</s:iterator>
				</tbody>
			</table>
</div>
		<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_CEO">	
 			<div class="batchAction"><label class="batchText">批量操作：</label>
 			<input type="button" class="btn btn_Blue" onclick="batchEdit('1');" value="&nbsp;&nbsp;执行&nbsp;&nbsp;"/> 
			<input type="button" class="btn btn_Blue" onclick="batchEdit('2');" value="&nbsp;&nbsp;预完成&nbsp;&nbsp;"/> 
			
			<input type="button" class="btn btn_Blue" onclick="batchEdit('3');" value="&nbsp;&nbsp;完成&nbsp;&nbsp;"/> 
			
			<input type="button" class="btn btn_Green" onclick="batchEdit('7');" value="&nbsp;&nbsp;非本月&nbsp;&nbsp;"/>
			
 			<input type="button" class="btn btn_Red" onclick="batchEdit('5');" value="&nbsp;过期&nbsp;"/> 
 			<input type="button" class="btn btn_Red" onclick="batchEdit('6');" value="&nbsp;非考核性过期&nbsp;"/> 
			<input type="button" class="btn btn_Red" onclick="batchEdit('8');" value="&nbsp;&nbsp;隐藏&nbsp;&nbsp;"/> 
		
			<input type="button" class="btn btn_Red" onclick="batchEdit('9');" value="&nbsp;申请删除&nbsp;"/>
			<input type="button" class="btn btn_Red" onclick="batchDelete();" value="&nbsp;删除&nbsp;"/>
		
	    	</div> 
			</security:authorize>