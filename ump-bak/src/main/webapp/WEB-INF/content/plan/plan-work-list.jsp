<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.springside.modules.web.struts2.Struts2Utils"%>
<%@page import="com.hhz.core.utils.DateOperator"%>
<%@page import="java.util.Date"%>
<%@page import="com.hhz.core.utils.CollectionHelper"%>
<%@page import="java.util.List"%>
<%@page import="com.hhz.core.utils.RandomUtils"%>
<%
	Date nowDate = DateOperator.truncDate(new Date());
	Date nextDate = DateOperator.addDays(nowDate, 1);
%>
<s:set var="nextDate" ><%=DateOperator.formatDate(nextDate,"yyyy-MM-dd")%></s:set>
<script type="text/javascript">
	$("#orderStr1").val("${orderStr1}");
	$("#orderStr2").val("${orderStr2}");
	$("#orderDir1").val("${orderDir1}");
	$("#orderDir2").val("${orderDir2}");
	if_in_attention = "${if_in_attention}";
	isEditOrg = "${isEditOrg}";
	$("#countAll").html("${countAll}");
	$("#countComplete").html("${countComplete}");
	$("#countFromPrev").html("${countFromPrev}");
	$("#countFromPrevCompleted").html("${countFromPrevCompleted}");
	$("#completedRate").html("${completedRate}");
</script>
<s:set var="entityTmpId_New" ><%=RandomUtils.generateTmpEntityId() %></s:set>
<form id="newScheForm" method="post">
	<input type="hidden" name="page.pageNo" value="1"/>
	<input type ="hidden" name="statusCd" value="0"/>
	<input type ="hidden" name="orgCd"/>
	<input type ="hidden" name="serialNumber" value="${newSerialNumber}"/>
	<input type ="hidden" name="serialOrder" value="${newSerialOrder}"/>
	<input type ="hidden" name="targetPointCd"/>
	<input type ="hidden" name="area"/>
	<input type ="hidden" name="content"/>
	<input type ="hidden" name="principal"/>
	<input type ="hidden" name="targetDate"/>
	<input type ="hidden" name="newMessage"/>
	<input type="hidden" name="entityTmpId" value="${entityTmpId_New}" />
	<input type="hidden" name="isEditOrg" value="${isEditOrg}"/>
</form>
<table class="content_table" id="scheTable">
	<thead>
		<tr>
			<th align="left" width="71px;" colspan="2" style="background:none;"></th>
			<th align="left" width="34px" nowrap="nowrap" onClick="doClickOrder1('serialOrder')" style="cursor:pointer; word-break: break-all;">编号<span id="order_serialOrder"><c:if test="${orderStr1 eq 'serialOrder'}"><c:if test="${orderDir1 eq 'ASC'}"><img src='${ctx}/images/plan/btn_up_10_10.gif'/></c:if><c:if test="${orderDir1 eq 'DESC'}"><img src='${ctx}/images/plan/btn_down_10_10.gif'/></c:if></c:if></span></th>
			<s:if test="centerCd=='11'">
			<th align="left" nowrap="nowrap">目标节点</th>
			<th align="left" nowrap="nowrap">地区(或项目名称)</th>
			</s:if>
			<th align="left" nowrap="nowrap">工作内容</th>
			<th align="left" width="80px" nowrap="nowrap">所属中心</th>
			<th align="left" width="50px" nowrap="nowrap" onClick="doClickOrder2('targetDate')" style="cursor:pointer; word-break: break-all;">目标<span id="order_targetDate"><c:if test="${orderStr2 eq 'targetDate'}"><c:if test="${orderDir2 eq 'ASC'}"><img src='${ctx}/images/plan/btn_up_10_10.gif'/></c:if><c:if test="${orderDir2 eq 'DESC'}"><img src='${ctx}/images/plan/btn_down_10_10.gif'/></c:if></c:if></span></th>
			<th align="left" width="50px" nowrap="nowrap" onClick="doClickOrder2('statusCd')" style="cursor:pointer; word-break: break-all;">状态<span id="order_statusCd"><c:if test="${orderStr2 eq 'statusCd'}"><c:if test="${orderDir2 eq 'ASC'}"><img src='${ctx}/images/plan/btn_up_10_10.gif'/></c:if><c:if test="${orderDir2 eq 'DESC'}"><img src='${ctx}/images/plan/btn_down_10_10.gif'/></c:if></c:if></span></th>
			<th align="left" width="50px" nowrap="nowrap" onClick="doClickOrder2('createdDate')" style="cursor:pointer; word-break: break-all;">创建<span id="order_createdDate"><c:if test="${orderStr2 eq 'createdDate'}"><c:if test="${orderDir2 eq 'ASC'}"><img src='${ctx}/images/plan/btn_up_10_10.gif'/></c:if><c:if test="${orderDir2 eq 'DESC'}"><img src='${ctx}/images/plan/btn_down_10_10.gif'/></c:if></c:if></span></th>
			<th align="left" width="50px" nowrap="nowrap" onClick="doClickOrder2('updatedDate')" style="cursor:pointer; word-break: break-all;">更新<span id="order_updatedDate"><c:if test="${orderStr2 eq 'updatedDate'}"><c:if test="${orderDir2 eq 'ASC'}"><img src='${ctx}/images/plan/btn_up_10_10.gif'/></c:if><c:if test="${orderDir2 eq 'DESC'}"><img src='${ctx}/images/plan/btn_down_10_10.gif'/></c:if></c:if></span></th>
			<th align="left" width="50px" nowrap="nowrap">附件</th>
		</tr>
	</thead>
	<tbody>
		<tr id="newScheTemplate1" class="detailTr"  style="display:none; height:50px;">
			<td colspan="3" align="right">${newSerialNumber}${newSerialOrder}</td>
			<s:if test="centerCd=='11'">
			<td><s:select list="mapTargetPointCd" listKey="key" listValue="value" id="new_targetPointCd"/></td>
			<td><input type="text" style="width:60px;" id="new_area"/></td>
			</s:if>
			<td colspan="2"><textarea id="new_content" rows="3" cols="24"></textarea></td>
			<td colspan="4">
				<s:textfield id="new_targetDate" onfocus="WdatePicker()" cssClass="Wdate" cssStyle="width:80px"/>
			</td>
		    <td><a href="javascript: attachManage('', event);" onclick="openAttachment('附件管理','${planWorkId==null?entityTmpId_New:planWorkId}'); return false;" ><img id="new_img_atta" src="${ctx}/resources/images/common/atta.gif" /></a></td>
		</tr>
		<tr id="newScheTemplate2" class="detailTr"  style="display:none; height:60px;">
		    <td colspan="3" align="right">
		    	留言：
		    </td>
		    <td <s:if test="centerCd=='11'">colspan="4"</s:if> colspan="2">
		    	<div style="float:left;"><textarea style="display:inline" id="new_newMessage" rows="3" cols="24"></textarea></div>
		    </td>
		    <td colspan="5" valign="middle">
				<div style="float:left; padding-left:12px;" id="monthScheSave">
					<button type="button" class="btn_green_55_20" onclick="addSaveSchedule('${centerCd}');">保存</button>&nbsp;
					<button type="button" class="btn_red_35_20" onclick="cancelSchedule();">取消</button>
				</div>
		    </td>
		</tr>
		<s:if test="page.result.size == 0">
		<tr>
			<td id="noResult_td" <s:if test="centerCd=='11'">colspan="12"</s:if> colspan="10" align="center" style="height:200px;">没有搜索到的记录</td>
		</tr>
		</s:if>
		<s:iterator value="page.result" var="pagePlanWork">
		<tr id="main_${planWorkId}" class="mainTr" style="cursor:pointer;">
			<td id="chk_${planWorkId}" nowrap="nowrap" colspan="2" onclick='scheClick("${planWorkId}");' align="center">
				<div style="display:none;" id="attention_recordVersion_${planWorkId}"></div>
				<div style="display:none;" id="attention_status_${planWorkId}">${statusCd}</div>
				<div style="display:inline;"><img id="attention_${planWorkId}" title="点击取消关注" attentionFlg="1" src="${ctx}/pics/email/attention.gif" onclick='CAN_scheClick=false;doDeleteAttention("planWork","${planWorkId}");' style="display:none;"/><img id="attention_cancel_${planWorkId}" title="点击关注,该条将在您的首页自动提示是否有更新" attentionFlg="0" src="${ctx}/pics/email/attention_cancel.gif" onclick='CAN_scheClick=false;doAddAttention("planWork","${planWorkId}","${recordVersion}");' style="display:none;"/></div>
				<div style="display:none;" id="attention_unread_img_${planWorkId}"><img class="new_img" src="${ctx}/resources/images/common/new.gif" /></div>
				<div style="display:inline;"><input type="checkbox" id="chk_all" value="${planWorkId}" onClick="CAN_scheClick=false;" recordVersion="${recordVersion}"></input></div>
				<div style="display:inline;"><img id="down_arrow_${planWorkId}" src="${ctx}/resources/images/common/right_grey.gif"/><img id="up_arrow_${planWorkId}" src="${ctx}/resources/images/common/down_grey.gif" style="display:none;"/></div>
			</td>
			<td id="td_serialOrder_${planWorkId}" nowrap="nowrap" onclick='scheClick("${planWorkId}");toggleDetail(this);'>
				<div id="serialOrder_show_${planWorkId}" class="span_show">${serialOrder}</div>
				<div id="serialOrder_hide_${planWorkId}" class="span_hide" style="display:none;">
					<input type='text' id="serialOrder_input_${planWorkId}" onblur="updateRecord('${planWorkId}','serialOrder',$(this).val());" value="${serialOrder}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;"/>
				</div>
			</td>
			<s:if test="centerCd=='11'">
			<td id="td_targetPointCd_${planWorkId}" onclick="scheClick('${planWorkId}');toggleDetail(this);">
				<div id="targetPointCd_show_${planWorkId}" class="span_show ellipsisDiv_full">
					<p:code2name mapCodeName="mapTargetPointCd" value="targetPointCd"/>
				</div>
				<div id="targetPointCd_hide_${planWorkId}" class="span_hide" style="display:none;">
					<s:select list="mapTargetPointCd" listKey="key" listValue="value" onclick="CAN_toggleDetail=false;CAN_scheClick=false;"/>
				</div>
			</td>
			<td id="td_area_${planWorkId}"
			<security:authorize ifAnyGranted="A_PLAN_WORK_MANAGER,A_PLAN_WORK_USER">
				<c:choose>
					<c:when test="${1==isEditOrg}">
						onclick='scheClick("${planWorkId}");toggleDetail(this,"${planWorkId}");'
					</c:when>
					<c:otherwise> onclick='scheClick("${planWorkId}");'</c:otherwise>
				</c:choose>
			</security:authorize>
			 <security:authorize ifAnyGranted="A_PLAN_WORK_ADMIN"> onclick='scheClick("${planWorkId}");toggleDetail(this,"${planWorkId}");'</security:authorize>
			 >
				<div id="area_show_${planWorkId}" class="span_show">
					${area}
				</div>
				<div id="area_hide_${planWorkId}" class="span_hide" style="display:none;">
					<input style="width:180px;" id="area_input_${planWorkId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onblur="updateRecord('${planWorkId}','area',$(this).val());"/>
				</div>
			</td>
			</s:if>
			<td id="td_content_${planWorkId}"
			<security:authorize ifAnyGranted="A_PLAN_WORK_MANAGER,A_PLAN_WORK_USER">
				<c:choose>
					<c:when test="${1==isEditOrg}">
						onclick='scheClick("${planWorkId}");toggleDetail(this,"${planWorkId}");'
					</c:when>
					<c:otherwise> onclick='scheClick("${planWorkId}");'</c:otherwise>
				</c:choose>
			</security:authorize>
			 <security:authorize ifAnyGranted="A_PLAN_WORK_ADMIN"> onclick='scheClick("${planWorkId}");toggleDetail(this,"${planWorkId}");'</security:authorize>
			  >
				<div id="content_show_${planWorkId}" class="span_show ellipsisDiv_full pd-chill-tip" title="<p:code2name mapCodeName="mapContentTips" value="planWorkId" />" style="word-break:break-all; word-wrap:break-word; white-space:normal;">${content}</div>
				<div id="content_hide_${planWorkId}" class="span_hide" style="display:none;">
					<textarea id="content_input_${planWorkId}" rows="5" style="width:100%;" onblur="updateRecord('${planWorkId}','content',$(this).val());" onclick="CAN_toggleDetail=false;CAN_scheClick=false;">${content}</textarea>
				</div>
			</td>
			<td onclick='scheClick("${planWorkId}");' nowrap="nowrap"><%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("orgCd"))%></td>
			<td id="td_targetDate_${planWorkId}" nowrap="nowrap" title="<s:date name='targetDate' format='yyyy-MM-dd'/>"
			<security:authorize ifAnyGranted="A_PLAN_WORK_MANAGER,A_PLAN_WORK_USER">
				<c:choose>
					<c:when test="${1==isEditOrg}">
						onclick='scheClick("${planWorkId}");toggleDetail(this,"${planWorkId}");'
					</c:when>
					<c:otherwise> onclick='scheClick("${planWorkId}");'</c:otherwise>
				</c:choose>
			</security:authorize>
			 <security:authorize ifAnyGranted="A_PLAN_WORK_ADMIN"> onclick='scheClick("${planWorkId}");toggleDetail(this,"${planWorkId}");'</security:authorize>
			 >
				<div id="targetDate_show_${planWorkId}" class="span_show <c:if test="${nextDate>targetDate && statusCd<3}"> color_red</c:if>">
					<s:date name="targetDate" format="MM-dd"></s:date>
				</div>
				<div id="targetDate_hide_${planWorkId}" class="span_hide" style="display:none;">
					<input type='text' id="targetDate_input_${planWorkId}" class="Wdate" onblur="updateRecord('${planWorkId}','targetDate',$(this).val());" onclick="CAN_toggleDetail=false;CAN_scheClick=false;WdatePicker();"/>
				</div>
			</td>
			<td id="td_statusCd_${planWorkId}" nowrap="nowrap" onclick='scheClick("${planWorkId}");'>
				<s:if test="statusCd==0"><img src="${ctx}/images/plan/pic_noConfirm.gif" title="未确认"></s:if>
				<s:elseif test="statusCd==1"><img src="${ctx}/images/plan/pic_confirm.gif" title="进行中"></s:elseif>
				<s:elseif test="statusCd==2"><img src="${ctx}/images/plan/pic_preFinish.gif" title="申请删除"></s:elseif>
				<s:elseif test="statusCd==3"><img src="${ctx}/images/plan/pic_finish.gif" title="已完成"></s:elseif>
				<s:elseif test="statusCd==4">已删除</s:elseif>
				<s:elseif test="statusCd==5"><img src="${ctx}/images/plan/pic_hide.gif" title="已隐藏"></s:elseif>
				<s:elseif test="statusCd==6"><img src="${ctx}/images/plan/pic_preFinish.gif" title="预完成"></s:elseif>
			</td>
			<td onclick='scheClick("${planWorkId}");' id="td_createdDate_${planWorkId}" nowrap="nowrap" title="<s:date name='createdDate'/>&#13创建人：<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"),";") %>" onclick='scheClick("${planWorkId}");'><s:date name="createdDate" format="MM-dd"></s:date></td>
			<td onclick='scheClick("${planWorkId}");' id="td_updateDate_${planWorkId}" nowrap="nowrap" title="<s:date name='updatedDate'/>&#13更新人：<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("updator"),";") %>" onclick='scheClick("${planWorkId}");'><s:date name="updatedDate" format="MM-dd"></s:date></td>
			<td onclick='scheClick("${planWorkId}");'>
				<a href="javascript: attachManage('', event);" onclick="openAttachment('附件管理','${planWorkId==null?entityTmpId_New:planWorkId}'); return false;" >
					<s:if test="attachFlg == 1"><img src="${ctx}/resources/images/common/atta_y.gif" /></s:if>
					<s:else><img src="${ctx}/resources/images/common/atta.gif" /></s:else>
				</a>
			</td>
		</tr>
		<tr id="detail_${planWorkId}" class="detailTr" style="display:none;">
			<td <s:if test="centerCd=='11'">colspan="12"</s:if> colspan="10">
				<form action="${ctx}/plan/plan-work!save.action" id="scheForm_${planWorkId}" method="post">
				<input type="hidden" name="id" value="${planWorkId}"/>
				<input type="hidden" name="planWorkId" value="${planWorkId}"/>
				<input type="hidden" name="userDeptCd" value ="${userDeptCd}" />
				<input type="hidden" name="recordVersion" value ="${recordVersion}"/>
				<input type="hidden" name="serialOrder" value ="${serialOrder}"/>
				<input type="hidden" name="targetPointCd" value ="${targetPointCd}"/>
				<input type="hidden" name="area" value ="${area}"/>
				<input type="hidden" name="content" value ="${content}"/>
				<input type="hidden" name="levelCd" value ="${levelCd}"/>
				<input type="hidden" name="targetDate" value ="${targetDate}"/>
				<input type="hidden" name="page.pageNo" value="1"/>
				<input type="hidden" name="statusCd" value="${statusCd}"/>
				<input type="hidden" name="isEditOrg" value="${isEditOrg}"/>
				<input type="hidden" name="isAttentioned" value="${isAttentioned}"/>
				<input type="hidden" name="newMessage"/>
				</form>
				<table id="table_${planWorkId}" width="100%">
					<tr><td colspan="3" style="width:100%; height:1px; background-color:#8c8f94;"></td></tr>
					<tr><td colspan="3" style="width:100%; height:1px; background-color:#dcdcde;"></td></tr>
					<tr>
						<td align="left" nowrap="nowrap" style="width:70px; height:30px; background-color:#e4e7ec;">
							<div style="padding-left:4px;">开始时间:</div>
						</td>
						<td align="left" nowrap="nowrap" style="width:5px; height:30px; background-color:#e4e7ec; border-right:1px solid #dcdcde;"></td>
						<td align="left" nowrap="nowrap" style="width:1200px; padding-left:14px;">
							<s:date name="createdDate" format="MM-dd HH:mm"></s:date>
							&nbsp;&nbsp;创建人：<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"),";") %>&nbsp;
							<s:if test="(statusCd==3 || statusCd==4 || statusCd==5) && null!=endDate">
								&nbsp;&nbsp;完成时间:<s:date name="endDate" format="MM-dd"></s:date>&nbsp;
							</s:if>
							<div id="saveDelete_btn_${planWorkId}" style="display:none;">
							<security:authorize ifAnyGranted="A_PLAN_WORK_ADMIN,A_PLAN_WORK_MANAGER">
								<button type="button" style="margin-left:2px;" class="btn_green_55_20" onclick="savePlanWork('${planWorkId}');">保存</button>
								<button type="button" style="margin-left:2px;" class="btn_green_55_20" onclick="updateStatusCd('${planWorkId}',1);">退回</button>
								<button type="button" style="margin-left:2px;" class="btn_green_55_20" onclick="updateStatusCd('${planWorkId}',3);">完成</button>
								<button type="button" style="margin-left:2px;" class="btn_red_35_20" onclick="updateStatusCd('${planWorkId}',4);">删除</button>
								<button type="button" style="margin-left:2px;" class="btn_red_35_20"  onclick="updateStatusCd('${planWorkId}',5);">隐藏</button>
								<button type="button" style="margin-left:2px;" class="btn_green_55_20" onclick="remind('${planWorkId}');">提醒</button>
							</security:authorize>
							<security:authorize ifAnyGranted="A_PLAN_WORK_USER">
								<button type="button" style="margin-left:2px;" class="btn_green_55_20" onclick="savePlanWork('${planWorkId}');">保存</button>
								<button type="button" style="margin-left:2px;" class="btn_green_55_20" onclick="updateStatusCd('${planWorkId}',1);">退回</button>
								<button type="button" style="margin-left:2px;" class="btn_green_65_20" onclick="updateStatusCd('${planWorkId}',2);">申请删除</button>
								<button type="button" style="margin-left:2px;" class="btn_green_65_20" onclick="updateStatusCd('${planWorkId}',6);">预完成</button>
								<button type="button" style="margin-left:2px;" class="btn_green_55_20" onclick="remind('${planWorkId}');">提醒</button>
							</security:authorize>
							</div>
							<security:authorize ifAnyGranted="A_PLAN_WORK_ADMIN">
									<script type="text/javascript">document.getElementById("saveDelete_btn_${planWorkId}").style.display = "inline";</script>
							</security:authorize>
							<security:authorize ifAnyGranted="A_PLAN_WORK_MANAGER,A_PLAN_WORK_USER">
								<c:if test="${1==isEditOrg}">
									<script type="text/javascript">document.getElementById("saveDelete_btn_${planWorkId}").style.display = "inline";</script>
								</c:if>
							</security:authorize>
						</td>
					</tr>
					<tr><td colspan="3" style="width:100%; height:1px; background-color:#dcdcde;"></td></tr>
					<tr>
						<td colspan="2" style="background-color:#e4e7ec; border-right:1px solid #dcdcde;">
							<div style="padding-left:4px;">留言:</div>
						</td>
						<td>
							<table>
								<tr>
									<td align="left" style="padding-left: 14px; padding-bottom:4px; padding-top:4px;">
										<table>
											<tr>
												<td colspan="2" id="${planWorkId}_messageDiv">
													<s:iterator value="planWorkMessages">
														<div class="detail_message_div">
															<pre><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>(<s:date name="createdDate" format="MM-dd HH:mm"/>):<s:property value="content" escape="true"/></pre>
														</div>
													</s:iterator>
												</td>
											</tr>
											<tr><td colspan="2" style="height:4px;"></td></tr>
											<tr>
												<td width="20%" style="padding-bottom:8px;">
													<textarea id="${planWorkId}_message" name="newMessage" rows="3" cols="36" style="height:53px;"></textarea>
												</td>
												<td width="80%" style="padding-bottom:8px;" align="left">
													<button type="button" style="margin-left:2px;" class="btn_blue_55_55" onclick="saveMessage('${planWorkId}');">发表</button>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr><td colspan="3" style="width:100%; height:1px; background-color:#dcdcde;"></td></tr>
					<tr><td colspan="3" style="width:100%; height:1px; background-color:#8c8f94;"></td></tr>
				</table>
			</td>
		</tr>
		<script language="javascript">
			<s:if test="attentionMap[planWorkId] == 'attention'">
				$("#attention_${planWorkId}").show();
				$("#scheForm_${planWorkId} input[name='isAttentioned']").val("attention");
			</s:if>
			<s:if test="!(attentionMap[planWorkId] == 'attention')">
				$("#attention_cancel_${planWorkId}").show();
			</s:if>
			<s:if test="attentionMapUnread[planWorkId] == 'unread'">
				$("#attention_recordVersion_${planWorkId}").html("${recordVersion}");
				$("#attention_unread_img_${planWorkId}").css("display","inline");
			</s:if>
		</script>
		</s:iterator>
	</tbody>
</table>
<table cellpadding="0" cellspacing="0" border="0" width="100%" style="display:none;">
	<tr>
		<td align="center" id="td_page_source"><p:page/></td>
	</tr>
</table>
<script type="text/javascript">
//if(1==$("#if_search_all").val()){
	$("#td_page").html($("#td_page_source").html());
	$("#td_page_source").html("");
//}
</script>
<form action="${ctx}/plan/plan-work!updateStatus.action" id="statusForm" method="post">
	<input type="hidden" id="status_id" name="id"/>
	<input type="hidden" name="userDeptCd" value="${userDeptCd}"/>
	<input type="hidden" id="status_cd" name="statusCd"/>
	<input type="hidden" id="status_version" name="recordVersion"/>
</form>
<div align="center">
	<security:authorize ifAnyGranted="A_PLAN_WORK_ADMIN">
		<script type="text/javascript">
			if(IF_DEPT_IN_SELECT){
				$("#NewBtn").show();
				$("#NewBtn_cutline").show();
			}
			$("#ConfirmBtn").show();
			$("#ConfirmBtn_cutline").show();
			$("#UnConfirmBtn").show();
			$("#UnConfirmBtn_cutline").show();
			$("#td_all_operate_btn").show();
			$("#operate_all_div").show();
			can_edit_record=true;
		</script>
   	</security:authorize>
	<security:authorize ifAnyGranted="A_PLAN_WORK_USER,A_PLAN_WORK_MANAGER">
		<c:choose>
			<c:when test="${1==isEditOrg}">
				<script type="text/javascript">
					if(IF_DEPT_IN_SELECT){
						$("#NewBtn").show();
						$("#NewBtn_cutline").show();
					}
					$("#ConfirmBtn").show();
					$("#ConfirmBtn_cutline").show();
					$("#UnConfirmBtn").show();
					$("#UnConfirmBtn_cutline").show();
					$("#td_all_operate_btn").show();
					$("#operate_all_div").show();
					can_edit_record=true;
				</script>
			</c:when>
			<c:otherwise>
				<script type="text/javascript">
					if(IF_DEPT_IN_SELECT){
						$("#NewBtn").show();
						$("#NewBtn_cutline").show();
					}
					$("#ConfirmBtn").hide();
					$("#ConfirmBtn_cutline").hide();
					$("#UnConfirmBtn").hide();
					$("#UnConfirmBtn_cutline").hide();
					$("#td_all_operate_btn").hide();
					$("#operate_all_div").hide();
					can_edit_record=false;
				</script>
			</c:otherwise>
		</c:choose>
   	</security:authorize>
</div>
<div id="pop_bg" class="pop_bg" style="display:none;">
	<div style='height:200px'></div>
	<table width='100%'>
		<tr>
			<td align='center'>
				<img src='${ctx}/resources/images/common/loading.gif'/>
			</td>
		</tr>
	</table>
</div>
<script language="javascript">
	$("#pop_bg").css("height",Number($(document).height()-20)+"px");
	if(""!=opened_entityid){
		scheClick(opened_entityid);
	}
</script>
