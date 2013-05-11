<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
			<td id="chk_${planWorkCenterId}" nowrap="nowrap" onclick='scheClick("${planWorkCenterId}");' align="center">
				<div style="display:none;" id="attention_recordVersion_${planWorkCenterId}">${recordVersion}</div>
				<div style="display:none;" id="attention_status_${planWorkCenterId}">${status}</div>
				<div style="display:inline;"><img id="attention_${planWorkCenterId}" title="点击取消关注" attentionFlg="1" src="${ctx}/pics/email/attention.gif" onclick='CAN_scheClick=false;doDeleteAttention("planWorkCenterId","${planWorkCenterId}");' style="display:none;"/><img id="attention_cancel_${planWorkCenterId}" title="点击关注,该条将在您的首页自动提示是否有更新" attentionFlg="0" src="${ctx}/pics/email/attention_cancel.gif" onclick='CAN_scheClick=false;doAddAttention("planWorkCenter","${planWorkCenterId}","${recordVersion}");' style="display:none;"/></div>
				<div style="display:none;" id="attention_unread_img_${planWorkCenterId}"><img class="new_img" src="${ctx}/resources/images/common/new.gif" /></div>
				<div style="display:inline;"><input type="checkbox" id="chk_all" value="${planWorkCenterId}" onClick="CAN_scheClick=false;" recordVersion="${recordVersion}"></input></div>
				<div style="display:inline;"><img id="down_arrow_${planWorkCenterId}" src="${ctx}/resources/images/common/right_grey.gif"/><img id="up_arrow_${planWorkCenterId}" src="${ctx}/resources/images/common/down_grey.gif" style="display:none;"/></div>
			</td>
			<td id="td_serialOrder_${planWorkCenterId}" nowrap="nowrap"
			<security:authorize ifAnyGranted="A_PLAN_CENTER_USER">
				<c:choose>
					<c:when test="${1==isEditOrg}">
						onclick='scheClick("${planWorkCenterId}");toggleDetail(this,"${planWorkCenterId}");'
					</c:when>
					<c:otherwise> onclick='scheClick("${planWorkCenterId}");'</c:otherwise>
				</c:choose>
			</security:authorize>
			 <security:authorize ifAnyGranted="A_PLAN_CENTER_ADMIN"> onclick='scheClick("${planWorkCenterId}");toggleDetail(this,"${planWorkCenterId}");'</security:authorize>
			  >
				<div id="serialOrder_show_${planWorkCenterId}" class="span_show">${serialOrder}</div>
				<div id="serialOrder_hide_${planWorkCenterId}" class="span_hide" style="display:none;">
					<input type='text' id="serialOrder_input_${planWorkCenterId}" onblur="updateRecord('${planWorkCenterId}','serialOrder',$(this).val());" value="${serialOrder}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;"/>
				</div>
			</td>
			<s:if test="centerCd=='11'">
			<td id="td_targetPointCd_${planWorkCenterId}" onclick="scheClick('${planWorkCenterId}');toggleDetail(this);">
				<div id="targetPointCd_show_${planWorkCenterId}" class="span_show ellipsisDiv_full">
					<p:code2name mapCodeName="mapTargetPointCd" value="targetPointCd"/>
				</div>
				<div id="targetPointCd_hide_${planWorkCenterId}" class="span_hide" style="display:none;">
					<s:select list="mapTargetPointCd" listKey="key" listValue="value" onclick="CAN_toggleDetail=false;CAN_scheClick=false;"/>
				</div>
			</td>
			<td id="td_area_${planWorkCenterId}"
			<security:authorize ifAnyGranted="A_PLAN_CENTER_USER">
				<c:choose>
					<c:when test="${1==isEditOrg}">
						onclick='scheClick("${planWorkCenterId}");toggleDetail(this,"${planWorkCenterId}");'
					</c:when>
					<c:otherwise> onclick='scheClick("${planWorkCenterId}");'</c:otherwise>
				</c:choose>
			</security:authorize>
			 <security:authorize ifAnyGranted="A_PLAN_CENTER_ADMIN"> onclick='scheClick("${planWorkCenterId}");toggleDetail(this,"${planWorkCenterId}");'</security:authorize>
			 >
				<div id="area_show_${planWorkCenterId}" class="span_show">
					${area}
				</div>
				<div id="area_hide_${planWorkCenterId}" class="span_hide" style="display:none;">
					<input style="width:180px;" id="area_input_${planWorkCenterId}" onclick="CAN_toggleDetail=false;CAN_scheClick=false;" onblur="updateRecord('${planWorkCenterId}','area',$(this).val());"/>
				</div>
			</td>
			</s:if>
			<c:if test="${if_chengben=='true'}">
			<td id="td_targetPointCd_${planWorkCenterId}" onclick="scheClick('${planWorkCenterId}');toggleDetail(this);">
				<div id="targetPointCd_show_${planWorkCenterId}" class="span_show ellipsisDiv_full">
					<p:code2name mapCodeName="mapXM" value="targetPointCd"/>
				</div>
				<div id="targetPointCd_hide_${planWorkCenterId}" class="span_hide" style="display:none;">
					<s:select list="mapXM" listKey="key" listValue="value" value="targetPointCd" onclick="CAN_toggleDetail=false;CAN_scheClick=false;"/>
				</div>
			</td>
			</c:if>
			<td id="td_content_${planWorkCenterId}"
			<security:authorize ifAnyGranted="A_PLAN_CENTER_USER">
				<c:choose>
					<c:when test="${1==isEditOrg}">
						onclick='scheClick("${planWorkCenterId}");toggleDetail(this,"${planWorkCenterId}");'
					</c:when>
					<c:otherwise> onclick='scheClick("${planWorkCenterId}");'</c:otherwise>
				</c:choose>
			</security:authorize>
			 <security:authorize ifAnyGranted="A_PLAN_CENTER_ADMIN"> onclick='scheClick("${planWorkCenterId}");toggleDetail(this,"${planWorkCenterId}");'</security:authorize>
			 <security:authorize ifNotGranted="A_PLAN_CENTER_ADMIN,A_PLAN_CENTER_USER"> onclick='scheClick("${planWorkCenterId}");'</security:authorize>
			  >
				<div id="content_show_${planWorkCenterId}" class="span_show ellipsisDiv_full pd-chill-tip" style="word-break:break-all; word-wrap:break-word; white-space:normal;" title="<p:code2name mapCodeName="mapContentTips" value="planWorkCenterId" />">${content}</div>
				<div id="content_hide_${planWorkCenterId}" class="span_hide" style="display:none;">
					<textarea id="content_input_${planWorkCenterId}" rows="5" style="width:100%;" onblur="updateRecord('${planWorkCenterId}','content',$(this).val());" onclick="CAN_toggleDetail=false;CAN_scheClick=false;">${content}</textarea>
				</div>
			</td>
			<td onclick='scheClick("${planWorkCenterId}");' nowrap="nowrap"><%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("orgCd"))%></td>
			<td id="td_principal_${planWorkCenterId}" onclick='scheClick("${planWorkCenterId}");' class="ellipsisDiv_full" title="<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("principal"),";") %>" >
				<div class="span_show" id="principalNames_show_${planWorkCenterId}"><%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("principal"),";") %></div>
				<div class="span_hide" style="display:none;">
					<input type="text" id="${planWorkCenterId}_principalNames" style="width:90px;" value="<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("principal"),";") %>" onclick="CAN_toggleDetail=false;CAN_scheClick=false;"/>
					<input type="hidden" id="${planWorkCenterId}_principalCds" value="${principal}">
				</div>
				<script language="javascript">
					<security:authorize ifAnyGranted="A_PLAN_CENTER_USER">
						<c:if test="${1==isEditOrg}">
							$('#${planWorkCenterId}_principalNames').ouSelect({showGroupFlg : true});
						</c:if>
					</security:authorize>
					<security:authorize ifAnyGranted="A_PLAN_CENTER_ADMIN">$('#${planWorkCenterId}_principalNames').ouSelect({showGroupFlg : true});</security:authorize>
				</script>
			</td>
			<s:if test="'planWorkCenterBis'==centerCd">
				<td id="td_workType_${planWorkCenterId}" onclick='scheClick("${planWorkCenterId}");' class="ellipsisDiv_full">
					<div class="span_show" id="corUserNames_show_${planWorkCenterId}">
						<s:if test="1==workType">工作联系单</s:if>
						<s:if test="2==workType">工作指令单</s:if>
						<s:if test="3==workType">会议纪要</s:if>
					</div>
					<div class="span_hide" style="display:none;" onclick="CAN_toggleDetail=false;CAN_scheClick=false;">
		    			<select id="${planWorkCenterId}_workType" <security:authorize ifNotGranted="A_PLAN_CENTER_ADMIN">disabled="disabled"</security:authorize>>
		    				<option value="1" <s:if test="1==workType">selected</s:if>>工作联系单</option>
		    				<option value="2" <s:if test="2==workType">selected</s:if>>工作指令单</option>
		    				<option value="3" <s:if test="3==workType">selected</s:if>>会议纪要</option>
		    			</select>
		    		</div>
				</td>
				<td id="td_corUser_${planWorkCenterId}" onclick='scheClick("${planWorkCenterId}");' class="ellipsisDiv_full" title="<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("corUser"),";") %>" >
					<div class="span_show" id="corUserNames_show_${planWorkCenterId}"><%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("corUser"),";") %></div>
					<div class="span_hide" style="display:none;">
						<input type="text" id="${planWorkCenterId}_corUserNames" style="width:90px;" value="<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("corUser"),";") %>" onclick="CAN_toggleDetail=false;CAN_scheClick=false;"/>
						<input type="hidden" id="${planWorkCenterId}_corUserCds" value="${corUser}">
					</div>
					<script language="javascript">
						<security:authorize ifAnyGranted="A_PLAN_CENTER_USER">
							<c:if test="${1==isEditOrg}">
								$('#${planWorkCenterId}_corUserNames').ouSelect({showGroupFlg : true});
							</c:if>
						</security:authorize>
						<security:authorize ifAnyGranted="A_PLAN_CENTER_ADMIN">$('#${planWorkCenterId}_corUserNames').ouSelect({showGroupFlg : true});</security:authorize>
					</script>
				</td>
				<td nowrap="nowrap" onclick='scheClick("${planWorkCenterId}");'>
					<a href="javascript:openAttachment('任务依据管理','${planWorkCenterId==null?entityTmpId_New:planWorkCenterId}','ifAttaAccording'<security:authorize ifNotGranted="A_PLAN_CENTER_ADMIN">,'true'</security:authorize>);" >
						<s:if test="ifAttaAccording == 1"><img src="${ctx}/resources/images/common/atta_y.gif" /></s:if>
						<s:else><img src="${ctx}/resources/images/common/atta.gif" /></s:else>
					</a>
				</td>
			</td>
			</s:if>
			<td id="td_targetDate_${planWorkCenterId}" nowrap="nowrap" title="<s:date name='targetDate' format='yyyy-MM-dd'/>"
			<security:authorize ifAnyGranted="A_PLAN_CENTER_USER">
				<c:choose>
					<c:when test="${1==isEditOrg}">
						onclick='scheClick("${planWorkCenterId}");toggleDetail(this,"${planWorkCenterId}");'
					</c:when>
					<c:otherwise> onclick='scheClick("${planWorkCenterId}");'</c:otherwise>
				</c:choose>
			</security:authorize>
			 <security:authorize ifAnyGranted="A_PLAN_CENTER_ADMIN"> onclick='scheClick("${planWorkCenterId}");toggleDetail(this,"${planWorkCenterId}");'</security:authorize>
			 >
				<div id="targetDate_show_${planWorkCenterId}" class="span_show <c:if test="${nextDate>targetDate && statusCd<3}"> color_red</c:if>">
					<s:date name="targetDate" format="MM-dd"></s:date>
				</div>
				<div id="targetDate_hide_${planWorkCenterId}" class="span_hide" style="display:none;">
					<input type='text' id="targetDate_input_${planWorkCenterId}" class="Wdate" onblur="updateRecord('${planWorkCenterId}','targetDate',$(this).val());" onclick="CAN_toggleDetail=false;CAN_scheClick=false;WdatePicker();"/>
				</div>
			</td>
			<td id="td_statusCd_${planWorkCenterId}" nowrap="nowrap" onclick='scheClick("${planWorkCenterId}");'>
				<s:if test="statusCd==-1">其他月份</s:if>
				<s:if test="statusCd==0"><img src="${ctx}/resources/images/common/status_unconfirm.gif" title="未确认"></s:if>
				<s:elseif test="statusCd==1"><img src="${ctx}/resources/images/common/status_confirm.gif" title="进行中"></s:elseif>
				<s:elseif test="statusCd==2"><img src="${ctx}/resources/images/common/status_prefinish.gif" title="预完成"></s:elseif>
				<s:elseif test="statusCd==3"><img src="${ctx}/resources/images/common/status_finish.gif" title="已完成"></s:elseif>
				<s:elseif test="statusCd==4"><img src="${ctx}/resources/images/common/status_del.gif" title="已删除"></s:elseif>
				<s:elseif test="statusCd==5"><img src="${ctx}/resources/images/common/status_hidden.gif" title="已隐藏"></s:elseif>
				<s:elseif test="statusCd==7">非本月</s:elseif>
				<s:elseif test="statusCd==8"><img src="${ctx}/resources/images/common/status_suspend.gif" title="已过期"></s:elseif>
				<s:elseif test="statusCd==9">非考核性过期</s:elseif>
				<s:elseif test="statusCd==10">完成但曾过期</s:elseif>
			</td>
			<td nowrap="nowrap" onclick='scheClick("${planWorkCenterId}");' id="td_createdDate_${planWorkCenterId}" nowrap="nowrap" title="<s:date name='createdDate'/>&#13创建人：<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"),";") %>" onclick='scheClick("${planWorkCenterId}");'><s:date name="createdDate" format="MM-dd"></s:date></td>
			<td nowrap="nowrap" onclick='scheClick("${planWorkCenterId}");' id="td_updateDate_${planWorkCenterId}" nowrap="nowrap" title="<s:date name='updatedDate'/>&#13更新人：<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("updator"),";") %>" onclick='scheClick("${planWorkCenterId}");'><s:date name="updatedDate" format="MM-dd"></s:date></td>
			<td nowrap="nowrap">
				<a href="javascript: openAttachment('附件管理','${planWorkCenterId==null?entityTmpId_New:planWorkCenterId}');" >
					<s:if test="attachFlg == 1"><img src="${ctx}/resources/images/common/atta_y.gif" /></s:if>
					<s:else><img src="${ctx}/resources/images/common/atta.gif" /></s:else>
				</a>
			</td>
		<script language="javascript">
			<s:if test="isAttentioned == 'attention'">
				//$("#attention_${planWorkCenterId}").show();
			</s:if>
			<s:if test="!(isAttentioned == 'attention')">
				//$("#attention_cancel_${planWorkCenterId}").show();
			</s:if>
		</script>