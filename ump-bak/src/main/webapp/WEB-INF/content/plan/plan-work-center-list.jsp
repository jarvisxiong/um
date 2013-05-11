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
	isEditOrg = "${isEditOrg}";
	if_in_attention = "${if_in_attention}";
	if_in_center = "${if_in_center}";
	if_chengben = "${if_chengben}";
	centerCd = "${centerCd}";
	now_year = "${now_year}";
</script>
<s:set var="entityTmpId_New" ><%=RandomUtils.generateTmpEntityId() %></s:set>
<form id="newScheForm" method="post">
	<input type="hidden" name="page.pageNo" value="1"/>
	<input type="hidden" name="statusCd" value="0"/>
	<input type="hidden" name="planYear" value="${now_year}"/>
	<input type="hidden" name="planMonth" value="${now_month}"/>
	<input type="hidden" name="orgCd"/>
	<input type="hidden" name="serialNumber" value="${newSerialNumber}"/>
	<input type="hidden" name="serialOrder" value="${newSerialOrder}"/>
	<input type="hidden" name="targetPointCd"/>
	<input type="hidden" name="area"/>
	<input type="hidden" name="content"/>
	<input type="hidden" name="principal"/>
	<input type="hidden" name="workType"/>
	<input type="hidden" name="corUser"/>
	<input type="hidden" name="targetDate"/>
	<input type="hidden" name="addFromType" value="0"/>
	<input type="hidden" name="planExecId"/>
	<input type="hidden" name="newMessage"/>
	<input type="hidden" name="entityTmpId" value="${entityTmpId_New}" />
	<input type="hidden" name="isEditOrg" value="${isEditOrg}"/>
</form>
<table class="content_table" id="scheTable">
	<thead>
		<tr>
			<th align="left" width="71px;" style="background:none;"<%-- <button id="NewBtn" style="display:none; float:left;" class="btn_green_55_20" onclick="newSchedule();">新建</button>--%></th>
			<th align="left" width="34px" nowrap="nowrap" onClick="doClickOrder1('serialOrder')" style="cursor:pointer; word-break: break-all;">编号<span id="order_serialOrder"><c:if test="${orderStr1 eq 'serialOrder'}"><c:if test="${orderDir1 eq 'ASC'}"><img src='${ctx}/images/plan/btn_up_10_10.gif'/></c:if><c:if test="${orderDir1 eq 'DESC'}"><img src='${ctx}/images/plan/btn_down_10_10.gif'/></c:if></c:if></span></th>
			<s:if test="centerCd=='11'">
			<th align="left" nowrap="nowrap">目标节点</th>
			<th align="left" nowrap="nowrap">地区(或项目名称)</th>
			</s:if>
			<c:if test="${if_chengben=='true'}">
			<th align="left" width="50px" nowrap="nowrap">项目</th>
			</c:if>
			<th align="left" nowrap="nowrap">工作内容</th>
			<th align="left" width="80px" nowrap="nowrap">部门</th>
			<th align="left" width="100px" nowrap="nowrap">负责人</th>
			<s:if test="'planWorkCenterBis'==centerCd">
				<th align="left" width="100px" nowrap="nowrap">类型</th>
				<th align="left" width="100px" nowrap="nowrap">配合人</th>
				<th align="left" width="50px" nowrap="nowrap">任务依据</th>
			</s:if>
			<th align="left" width="50px" nowrap="nowrap" onClick="doClickOrder2('targetDate')" style="cursor:pointer; word-break: break-all;">目标<span id="order_targetDate"><c:if test="${orderStr2 eq 'targetDate'}"><c:if test="${orderDir2 eq 'ASC'}"><img src='${ctx}/images/plan/btn_up_10_10.gif'/></c:if><c:if test="${orderDir2 eq 'DESC'}"><img src='${ctx}/images/plan/btn_down_10_10.gif'/></c:if></c:if></span></th>
			<th align="left" width="50px" nowrap="nowrap" onClick="doClickOrder2('statusCd')" style="cursor:pointer; word-break: break-all;">状态<span id="order_statusCd"><c:if test="${orderStr2 eq 'statusCd'}"><c:if test="${orderDir2 eq 'ASC'}"><img src='${ctx}/images/plan/btn_up_10_10.gif'/></c:if><c:if test="${orderDir2 eq 'DESC'}"><img src='${ctx}/images/plan/btn_down_10_10.gif'/></c:if></c:if></span></th>
			<th align="left" width="50px" nowrap="nowrap" onClick="doClickOrder2('createdDate')" style="cursor:pointer; word-break: break-all;">创建<span id="order_createdDate"><c:if test="${orderStr2 eq 'createdDate'}"><c:if test="${orderDir2 eq 'ASC'}"><img src='${ctx}/images/plan/btn_up_10_10.gif'/></c:if><c:if test="${orderDir2 eq 'DESC'}"><img src='${ctx}/images/plan/btn_down_10_10.gif'/></c:if></c:if></span></th>
			<th align="left" width="50px" nowrap="nowrap" onClick="doClickOrder2('updatedDate')" style="cursor:pointer; word-break: break-all;">更新<span id="order_updatedDate"><c:if test="${orderStr2 eq 'updatedDate'}"><c:if test="${orderDir2 eq 'ASC'}"><img src='${ctx}/images/plan/btn_up_10_10.gif'/></c:if><c:if test="${orderDir2 eq 'DESC'}"><img src='${ctx}/images/plan/btn_down_10_10.gif'/></c:if></c:if></span></th>
			<th align="left" width="50px" nowrap="nowrap">附件</th>
		</tr>
	</thead>
	<tbody>
		<tr id="newScheTemplate1" class="detailTr"  style="display:none; height:50px;">
			<td colspan="2" align="right">${newSerialNumber}${newSerialOrder}</td>
			<s:if test="centerCd=='11'">
				<td><s:select list="mapTargetPointCd" listKey="key" listValue="value" id="new_targetPointCd"/></td>
				<td><input type="text" style="width:60px;" id="new_area"/></td>
			</s:if>
			<c:if test="${if_chengben=='true'}">
				<td><s:select list="mapXM" listKey="key" listValue="value" id="new_targetPointCd"/></td>
				<script language="javascript">$("#search_span_targetPointCd").show();</script>
			</c:if>
			<c:if test="${if_chengben!='true'}">
				<script language="javascript">$("#search_span_targetPointCd").hide();</script>
			</c:if>
			<td><textarea id="new_content" rows="3" cols="24"></textarea></td>
			<td id="td_new_deptName"><%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("centerCd"))%></td>
			<td nowrap="nowrap">
				<textarea rows="3" cols="10" id="new_principalNames" readonly="readonly" ></textarea>
				<input type="hidden" id="new_principalCds"/>
				<script language="javascript">$('#new_principalNames').ouSelect({showGroupFlg : true});</script>
		    </td>
		    <s:if test="'planWorkCenterBis'==centerCd">
		    	<td nowrap="nowrap">
		    		<select id="new_workType">
		    			<option value="1">工作联系单</option>
		    			<option value="2">工作指令单</option>
		    			<option value="3">会议纪要</option>
		    		</select>
		    	</td>
		    	<td nowrap="nowrap">
					<textarea rows="3" cols="10" id="new_corUserNames" readonly="readonly" ></textarea>
					<input type="hidden" id="new_corUserCds"/>
					<script language="javascript">$('#new_corUserNames').ouSelect({showGroupFlg : true});</script>
		    	</td>
		    	<td nowrap="nowrap">
		    		<a href="javascript:openAttachment('新增任务依据','${planWorkCenterId==null?entityTmpId_New:planWorkCenterId}','ifAttaAccording');" ><img id="new_img_atta_according" src="${ctx}/resources/images/common/atta.gif" /></a>
		    	</td>
		    </s:if>
		    <td colspan="4">
				<s:textfield id="new_targetDate" onfocus="WdatePicker()" cssClass="Wdate" cssStyle="width:80px"/>
			</td>
			<td><a href="javascript:openAttachment('新增附件管理','${planWorkCenterId==null?entityTmpId_New:planWorkCenterId}');" ><img id="new_img_atta" src="${ctx}/resources/images/common/atta.gif" /></a></td>
		</tr>
		<tr id="newScheTemplate2" class="detailTr"  style="display:none; height:60px;">
		    <td colspan="2" align="right">
		    	留言：
		    </td>
		    <td <s:if test="centerCd=='11'">colspan="4"</s:if><c:if test="${if_chengben=='true'}">colspan="3"</c:if> colspan="2">
		    	<div style="float:left;"><textarea style="display:inline" id="new_newMessage" rows="3" cols="24"></textarea></div>
		    </td>
		    <td <s:if test="'planWorkCenterBis'==centerCd">colspan="9"</s:if><s:else>colspan="6"</s:else> valign="middle">
				<div style="float:left; padding-left:12px;" id="monthScheSave">
					<input type="button" class="button_blue" onclick="addSaveSchedule('${centerCd}');" value="保存"/>
					<input type="button" class="button_green" onclick="cancelSchedule();" value="取消"/>
				</div>
		    </td>
		</tr>
		<s:if test="page.result.size == 0">
		<tr>
			<td id="noResult_td" <s:if test="centerCd=='11'">colspan="12"</s:if><c:if test="${if_chengben=='true'}">colspan="11"</c:if> colspan="10" align="center" style="height:200px;">没有搜索到的记录</td>
		</tr>
		</s:if>
		<s:iterator value="page.result" var="pagePlanWorkCenter">
		<tr id="main_${planWorkCenterId}" class="mainTr" style="cursor:pointer;">
			<td id="chk_${planWorkCenterId}" nowrap="nowrap" onclick='scheClick("${planWorkCenterId}");' align="center">
				<div style="display:none;" id="attention_recordVersion_${planWorkCenterId}"></div>
				<div style="display:none;" id="attention_status_${planWorkCenterId}">${statusCd}</div>
				<div style="display:inline;"><img id="attention_${planWorkCenterId}" title="点击取消关注" attentionFlg="1" src="${ctx}/pics/email/attention.gif" onclick='CAN_scheClick=false;doDeleteAttention("planWorkCenter","${planWorkCenterId}");' style="display:none;"/><img id="attention_cancel_${planWorkCenterId}" title="点击关注,该条将在您的首页自动提示是否有更新" attentionFlg="0" src="${ctx}/pics/email/attention_cancel.gif" onclick='CAN_scheClick=false;doAddAttention("planWorkCenter","${planWorkCenterId}","${recordVersion}");' style="display:none;"/></div>
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
					<s:select list="mapXM" listKey="key" value="targetPointCd" listValue="value" onclick="CAN_toggleDetail=false;CAN_scheClick=false;"/>
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
				<s:if test="statusCd==0"><img src="${ctx}/resources/images/common/status_unconfirm.gif" title="未确认"></s:if>
				<s:elseif test="statusCd==1"><img src="${ctx}/resources/images/common/status_confirm.gif" title="进行中"></s:elseif>
				<s:elseif test="statusCd==2"><img src="${ctx}/resources/images/common/status_prefinish.gif" title="预完成"></s:elseif>
				<s:elseif test="statusCd==3"><img src="${ctx}/resources/images/common/status_finish.gif" title="已完成"></s:elseif>
				<s:elseif test="statusCd==4">已删除</s:elseif>
				<s:elseif test="statusCd==5"><img src="${ctx}/resources/images/common/status_hidden.gif" title="已隐藏"></s:elseif>
				<s:elseif test="statusCd==7">非本月</s:elseif>
				<s:elseif test="statusCd==8"><img src="${ctx}/resources/images/common/status_suspend.gif" title="已过期"></s:elseif>
				<s:elseif test="statusCd==9">非考核性过期</s:elseif>
				<s:elseif test="statusCd==10"><img src="${ctx}/resources/images/common/status_completeDely.gif" title="完成但曾过期"></s:elseif>
			</td>
			<td nowrap="nowrap" onclick='scheClick("${planWorkCenterId}");' id="td_createdDate_${planWorkCenterId}" nowrap="nowrap" title="<s:date name='createdDate'/>&#13创建人：<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"),";") %>" onclick='scheClick("${planWorkCenterId}");'><s:date name="createdDate" format="MM-dd"></s:date></td>
			<td nowrap="nowrap" onclick='scheClick("${planWorkCenterId}");' id="td_updateDate_${planWorkCenterId}" nowrap="nowrap" title="<s:date name='updatedDate'/>&#13更新人：<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("updator"),";") %>" onclick='scheClick("${planWorkCenterId}");'><s:date name="updatedDate" format="MM-dd"></s:date></td>
			<td nowrap="nowrap">
				<a href="javascript:openAttachment('附件管理','${planWorkCenterId==null?entityTmpId_New:planWorkCenterId}');" >
					<s:if test="attachFlg == 1"><img src="${ctx}/resources/images/common/atta_y.gif" /></s:if>
					<s:else><img src="${ctx}/resources/images/common/atta.gif" /></s:else>
				</a>
			</td>
			<s:if test="1==addFromType || 2==addFromType">
				<script language="javascript">
					//如果是来自执行计划的记录，不能编辑项目，内容，目标时间
					try{
						$("#targetPointCd_hide_${planWorkCenterId} select").attr("disabled","true");
					}catch(e){}
					$("#content_hide_${planWorkCenterId} textarea").attr("disabled","true");
					$("#targetDate_hide_${planWorkCenterId} input").attr("disabled","true");
				</script>
			</s:if>
		</tr>
		<tr id="detail_${planWorkCenterId}" class="detailTr" style="display:none;">
			<td <s:if test="centerCd=='11'">colspan="12"</s:if><c:if test="${if_chengben=='true'}">colspan="11"</c:if><s:if test="'planWorkCenterBis'==centerCd">colspan="13"</s:if> colspan="10">
				<form action="${ctx}/plan/plan-work-center!save.action" id="scheForm_${planWorkCenterId}" method="post">
				<input type="hidden" name="id" value="${planWorkCenterId}"/>
				<input type="hidden" name="planWorkCenterId" value="${planWorkCenterId}"/>
				<input type="hidden" name="planYear" value="${planYear}"/>
				<input type="hidden" name="planMonth" value="${planMonth}"/>
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
				<input type="hidden" name="principal" value="${principal}"/>
				<input type="hidden" name="workType" value="${workType}"/>
				<input type="hidden" name="corUser" value="${corUser}"/>
				<input type="hidden" name="isAttentioned" value="${isAttentioned}"/>
				<input type="hidden" name="newMessage"/>
				</form>
				<table id="table_${planWorkCenterId}" width="100%">
					<tr><td colspan="3" style="width:100%; height:1px; background-color:#8c8f94;"></td></tr>
					<tr><td colspan="3" style="width:100%; height:1px; background-color:#dcdcde;"></td></tr>
					<tr>
						<td align="left" nowrap="nowrap" style="width:70px; height:30px; background-color:#e4e7ec;">
							<div style="padding-left:4px;">开始时间:</div>
						</td>
						<td align="left" nowrap="nowrap" style="width:5px; height:30px; background-color:#e4e7ec; border-right:1px solid #dcdcde;"></td>
						<td align="left" nowrap="nowrap" style="width:1200px; padding-left:14px;">
							<div style="float:left; margin-top:6px;">
								<s:date name="createdDate" format="MM-dd hh:mm"></s:date>
								&nbsp;&nbsp;创建人：<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"),";") %>&nbsp;
								<s:if test="(statusCd==3 || statusCd==4 || statusCd==5) && null!=endDate">
									完成时间:<s:date name="endDate" format="MM-dd"></s:date>&nbsp;
								</s:if>
							</div>
							<s:if test="statusCd!=2">
								<input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_blue" onclick="updateStatusCd('${planWorkCenterId}',2);" value="预完成"/>
							</s:if>
							<span id="saveDelete_btn_${planWorkCenterId}">
							<security:authorize ifAnyGranted="A_PLAN_CENTER_ADMIN,A_PLAN_CENTER_USER">
								<input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_blue" onclick="updateStatusCd('${planWorkCenterId}',1);" value="进行中"/>
								<input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_blue" onclick="updateStatusCd('${planWorkCenterId}',3);" value="完成"/>
								<input type="button" style="float:left; margin-left:2px; margin-top:1px; width:85px" class="btn_green" onclick="updateStatusCd('${planWorkCenterId}',10);" value="完成但曾过期"/>
								<input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_green" onclick="savePlanWorkCenter('${planWorkCenterId}');"value="保存"/>
								<input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_green" onclick="updateStatusCd('${planWorkCenterId}',7);" value="非本月"/>
								<input type="button" style="float:left; margin-left:2px; margin-top:1px; width:85px" class="btn_green" onclick="updateStatusCd('${planWorkCenterId}',9);" value="非考核性过期"/>
								<input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_red" onclick="updateStatusCd('${planWorkCenterId}',8);" value="过期"/>
								<input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_red" onclick="updateStatusCd('${planWorkCenterId}',4);" value="删除"/>
								<!-- <input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_green" onclick="remind('${planWorkCenterId}');" value="提醒"/> -->
								<!-- <input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_green"  onclick="updateStatusCd('${planWorkCenterId}',5);" value="隐藏"/> -->
								<input type="button" style="margin-left:2px;" class="btn_red" onclick="doCallback('${planWorkCenterId}');" value="驳回"/>
							</security:authorize>
							</span>
							<s:if test="statusCd==2">
								<input type="button" style="float:left; margin-left:2px; margin-top:1px;" class="btn_red" onclick="updateStatusCd('${planWorkCenterId}',1);" value="取消预完成" />
							</s:if>
							<security:authorize ifAnyGranted="A_PLAN_CENTER_PRE,A_PLAN_CENTER_USER">
								<c:if test="${1==isEditOrg}">
									<script type="text/javascript">document.getElementById("saveDelete_btn_${planWorkCenterId}").style.display = "";</script>
								</c:if>
								<c:if test="${1!=isEditOrg}">
									<script type="text/javascript">document.getElementById("saveDelete_btn_${planWorkCenterId}").style.display = "none";</script>
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
												<td colspan="2" id="${planWorkCenterId}_messageDiv">
													<s:iterator value="planWorkCenterMessages">
														<div class="detail_message_div">
															<pre><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>(<s:date name="createdDate" format="MM-dd HH:mm"/>):<s:property value="content" escape="true"/></pre>
														</div>
													</s:iterator>
												</td>
											</tr>
											<tr><td colspan="2" style="height:4px;"></td></tr>
											<tr>
												<td width="20%" style="padding-bottom:8px;">
													<textarea id="${planWorkCenterId}_message" name="newMessage" rows="3" cols="36" style="height:53px;"></textarea>
												</td>
												<td width="80%" style="padding-bottom:8px;" align="left">
													<input type="button" style="margin-left:2px;height:53px;" class="button_green" onclick="saveMessage('${planWorkCenterId}');" value="留言"/>
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
			<s:if test="attentionMap[planWorkCenterId] == 'attention'">
				$("#attention_${planWorkCenterId}").show();
				$("#scheForm_${planWorkCenterId} input[name='isAttentioned']").val("attention");
			</s:if>
			<s:if test="!(attentionMap[planWorkCenterId] == 'attention')">
				$("#attention_cancel_${planWorkCenterId}").show();
			</s:if>
			<s:if test="attentionMapUnread[planWorkCenterId] == 'unread'">
				$("#attention_recordVersion_${planWorkCenterId}").html("${recordVersion}");
				$("#attention_unread_img_${planWorkCenterId}").css("display","inline");
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
<form action="${ctx}/plan/plan-work-center!updateStatus.action" id="statusForm" method="post">
	<input type="hidden" id="status_id" name="id"/>
	<input type="hidden" name="userDeptCd" value="${userDeptCd}"/>
	<input type="hidden" id="status_cd" name="statusCd"/>
	<input type="hidden" id="status_version" name="recordVersion"/>
</form>
<div align="center">
	<security:authorize ifAnyGranted="A_PLAN_CENTER_ADMIN">
		<script type="text/javascript">
			if(IF_DEPT_IN_SELECT){
				try{$("#NewBtn").show();}catch(e){}
				try{$("#NewBtn_cutline").show();}catch(e){}
			}else{
				try{$("#NewBtn").hide();}catch(e){}
				try{$("#NewBtn_cutline").hide();}catch(e){}
			}
			try{$("#td_all_operate_btn").show();}catch(e){}
			try{$("#operate_all_div").show();}catch(e){}
			can_edit_record=true;
		</script>
   	</security:authorize>
	<security:authorize ifAnyGranted="A_PLAN_CENTER_USER">
		<script type="text/javascript">
			if(IF_DEPT_IN_SELECT){
				<c:if test="${1==isEditOrg}">
					try{$("#NewBtn").show();}catch(e){}
					try{$("#NewBtn_cutline").show();}catch(e){}
					can_edit_record=true;
				</c:if>
				<c:if test="${1!=isEditOrg}">
					try{$("#NewBtn").hide();}catch(e){}
					try{$("#NewBtn_cutline").hide();}catch(e){}
					can_edit_record=false;
				</c:if>
			}else{
				try{$("#NewBtn").hide();}catch(e){}
				try{$("#NewBtn_cutline").hide();}catch(e){}
			}
			try{$("#td_all_operate_btn").show();}catch(e){}
			try{$("#operate_all_div").show();}catch(e){}
		</script>
   	</security:authorize>
	<security:authorize ifAnyGranted="A_PLAN_CENTER_PRE">
		<script type="text/javascript">
			if(IF_DEPT_IN_SELECT){
				can_edit_record=true;
			}
		</script>
   	</security:authorize>
</div>
<div id="pop_bg" class="pop_bg" style="display:none;">
	<div style='height:200px'></div>
	<table width='90%'>
		<tr>
			<td align='center'>
				<img src='${ctx}/resources/images/common/loading.gif'/>
			</td>
		</tr>
	</table>
</div>
<script language="javascript">
	if(new_addFromType>0){
		new_addFromType=0;
		newSchedule();
		$("#new_content").val(new_content);
		$("#new_targetDate").val(new_targetDate);
		$("#new_targetPointCd").val(new_targetPointCd);
		$("#newScheForm input[name='addFromType']").val(new_addFromType);
		$("#newScheForm input[name='planExecId']").val(new_planExecId);
		$("#new_content").attr("disabled","true");
		$("#new_targetDate").attr("disabled","true");
		$("#new_targetPointCd").attr("disabled","true");
	}
	if(""!=opened_entityid){
		scheClick(opened_entityid);
	}
	
	$("#pop_bg").css("height",Number($(document).height()-20)+"px");
</script>
