<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
			<td id="chk_${planWorkId}" nowrap="nowrap" colspan="2" onclick='scheClick("${planWorkId}");' align="center">
				<div style="display:none;" id="attention_recordVersion_${planWorkId}">${recordVersion}</div>
				<div style="display:none;" id="attention_status_${planWorkId}">${status}</div>
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
				<div id="content_show_${planWorkId}" class="span_show ellipsisDiv_full" style="word-break:break-all; word-wrap:break-word; white-space:normal;">${content}</div>
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
				<a href="javascript: openAttachment('附件管理','${planWorkId==null?entityTmpId_New:planWorkId}');" >
					<s:if test="attachFlg == 1"><img src="${ctx}/resources/images/common/atta_y.gif" /></s:if>
					<s:else><img src="${ctx}/resources/images/common/atta.gif" /></s:else>
				</a>
			</td>
		<script language="javascript">
			<s:if test="isAttentioned == 'attention'">
				$("#attention_${planWorkId}").show();
			</s:if>
			<s:if test="!(isAttentioned == 'attention')">
				$("#attention_cancel_${planWorkId}").show();
			</s:if>
		</script>