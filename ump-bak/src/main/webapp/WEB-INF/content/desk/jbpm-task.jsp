<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="com.hhz.core.utils.DateOperator"%>
<%@page import="java.util.Date"%>

<div style="height:27px; width:100%; background-color:#e4e7ec; line-height:27px;">
	<div style="float:left; margin-left:5px;">
		<span id="span_title_daiban" style="cursor: pointer;" onclick="doChangeDaibanAttention('daiban')" title="您有 <s:property value= "deskHomePager.getTotalCount()" />条未处理事宜，点击查看">
			<span id="span_title_daiban_wenzi" style="font-size:16px; font-weight:bolder;">待办</span><span style="font-size:9px;" class="color_red">(<s:property value= "deskHomePager.getTotalCount()" />)</span>
		</span>
		|
		<span id="span_title_attention" style="cursor: pointer;" onclick="doChangeDaibanAttention('attention')" title="关注事项会自动提醒您有人更新了内容。您有 ${oaAllAttentionCount}条事项有更新，点击查看">
			<span id="span_title_attention_wenzi" style="font-size:14px; font-weight:normal;">关注</span><span style="font-size:9px;" class="color_red">(${oaAllAttentionCount})</span>
		</span>
		
		<s:if test="hrApproveCount != -1">
		|
		<span style="cursor: pointer;" onclick="doShowHrApprove()" title="您有 ${hrApproveCount}条人员审批事项，点击查看">
			<a href="javascript:void(0)">
				<span>人员模块</span>
				<span id="hr_approve_count" style="font-size:9px;" class="color_red">(${hrApproveCount})</span>
			</a>
		</span>
		</s:if>
	</div>
	<div style="float:right; margin-right:8px;">
		<span id="span_page_daiban" style="display:none;">
			<s:if test="deskHomePager.totalCount != 0">
				第  <s:property value="deskHomePager.pageNo" />/<s:property value="deskHomePager.totalPages" /> 页
			</s:if>
			<s:if test="%{deskHomePager.hasPre}">
				<img align="absmiddle" style="cursor:pointer;" src="${ctx}/resources/images/desk/page_up.gif" onmouseover="$(this).attr('src', '${ctx}/resources/images/desk/page_up_hover.gif');" onmouseout="$(this).attr('src', '${ctx}/resources/images/desk/page_up.gif');" onclick="jumpPageTo('${ctx}/desk/jbpm-task.action', '${deskHomePager.prePage}', 'desk_front_jbpm_div'); return false;"/>
			</s:if>
			<s:else>
				<img align="absmiddle" src="${ctx}/resources/images/desk/page_up_inactive.gif" />
			</s:else>
			<s:if test="%{deskHomePager.hasNext}">
				<img align="absmiddle" style="cursor:pointer;" src="${ctx}/resources/images/desk/page_down.gif" onmouseover="$(this).attr('src', '${ctx}/resources/images/desk/page_down_hover.gif');" onmouseout="$(this).attr('src', '${ctx}/resources/images/desk/page_down.gif');" onclick="jumpPageTo('${ctx}/desk/jbpm-task.action', '${deskHomePager.nextPage}', 'desk_front_jbpm_div'); return false;"/>
			</s:if>
			<s:else>
				<img align="absmiddle" src="${ctx}/resources/images/desk/page_down_inactive.gif" />
			</s:else>
		</span>
		<span id="span_page_attention" style="display:none;">
			第 <span id="page_no_attention">1</span>/${pageAllAttention} 页
			<img id="attention_up_active" align="absmiddle" style="display:none; cursor:pointer;" src="${ctx}/resources/images/desk/page_up.gif" onmouseover="$(this).attr('src', '${ctx}/resources/images/desk/page_up_hover.gif');" onmouseout="$(this).attr('src', '${ctx}/resources/images/desk/page_up.gif');" onclick="pageUpAttention(); return false;"/>
			<img id="attention_up_inactive" align="absmiddle" style="display:none;" src="${ctx}/resources/images/desk/page_up_inactive.gif" />
			<img id="attention_down_active" align="absmiddle" style="display:none; cursor:pointer;" src="${ctx}/resources/images/desk/page_down.gif" onmouseover="$(this).attr('src', '${ctx}/resources/images/desk/page_down_hover.gif');" onmouseout="$(this).attr('src', '${ctx}/resources/images/desk/page_down.gif');" onclick="pageDownAttention(); return false;"/>
			<img id="attention_down_inactive" align="absmiddle" style="display:none;" src="${ctx}/resources/images/desk/page_down_inactive.gif" />
			<script language="javascript">
				page_no_attention=1;
				page_all_attention = Number("${pageAllAttention}");
				activePageAttention();
			</script>
		</span>
	</div>
</div>
<div style="height:1px; width:100%; background-color:#8c8f94;"></div>
<div id="div_content_daiban" style="width:100%; display:none; float:left;">
	<s:if test="deskHomePager.result.size == 0">
		<div class="noResult">没有待办事项</div>
	</s:if>
	<s:else>
		<table class="home_table" width="100%" cellpadding="0" cellspacing="0" border="0">
			<col align="left" width="8px"/>
			<col align="left" width="110px"/>
			<col align="left" width="130px"/>
			<col align="left" width="40px"/>
			<col align="left"/>
			<col align="left"/>
			<col align="left" width="90px"/>
			<col align="right" width="50px" style="margin-right:10px;"/>
			<%	String moduleCd = (String)request.getAttribute("moduleCd");
				if ("execPlan".equals(moduleCd)){%>
					<tr class="editTr ellipsisDiv_full" onclick="openTask('','','','execPlan','');">
						<td></td>
						<td style="white-space: nowrap;">【项目计划】</td>
						<td colspan="6">待回复进度节点信息<span class="color_red">(${execPlanTotalCount})</span>条</td>
					</tr>
			<%	}
			%>
			<s:iterator value="taskResult">
				<s:set var="subStatusCd"><p:code2name mapCodeName="mapSubStatusCd" value="jbpmTaskId"/></s:set>
				<s:set var="isMyRec"><p:code2name mapCodeName="mapMyRec" value="jbpmTaskId"/></s:set>
				<c:set var="j_moduleName">【<%=CodeNameUtil.getDictNameByCd(DictContants.JBPM_MODULE_CD,JspUtil.findString("moduleCd") ) %>】</c:set>
				<s:if test="moduleCd=='ceomeeting' or moduleCd=='specialtask'">
					<tr class="editTr ellipsisDiv_full" onclick="openTask('','${jbpmId}','','${moduleCd}');">
						<td></td>
						<td >
						<s:if test="moduleCd=='ceomeeting' ">
						<span class='color_red'>${j_moduleName}</span>
						</s:if>
						<s:else>
						${j_moduleName}
						</s:else>
						</td>
						<td style="overflow: hidden; white-space: nowrap;">${JbpmCd}</td>
						<td colspan="4" title="<p:code2name mapCodeName="jbpmTaskSubjMap" value="jbpmTaskId"/>">
							<div  class="ellipsisDiv_full"><p:code2name mapCodeName="jbpmTaskSubjMap" value="jbpmTaskId"/></div>
						</td>
						<td title="<s:date name='applyDate' format='yyyy-MM-dd'/>" >
							<c:set var="curDate"><%=DateOperator.formatDate(new Date(), "MM-dd") %></c:set>
							<c:set var="tarDate"><s:date name="applyDate" format="MM-dd" /></c:set>
							<span
								<c:if test="${tarDate < curDate}">style="color: red;"</c:if>
								<c:if test="${tarDate >= curDate}">style="color: blue;"</c:if>>
								<s:date name="applyDate" format="MM-dd" />
							</span>
						</td>
					</tr>
				</s:if>
				<s:elseif test="moduleCd == 'resApprove'">
				<tr class="editTr ellipsisDiv_full" onclick="openTask('','${jbpmId}','${taskId}','${moduleCd}');">
					<td></td>
					<td>${j_moduleName}</td>
					<td class="ellipsisDiv_full" title="<s:property value="userName"/>"><s:property value="userName"/></td>
					<td class="ellipsisDiv_full" colspan="2">
						<s:if test="delayStatusCd!=null && delayStatusCd!=0">
							<span style="color: red;">[<%=CodeNameUtil.getDictNameByCd(DictContants.DELAY_STATUS_CD,JspUtil.findString("delayStatusCd")) %>]</span>
						</s:if>
						<span <s:if test="#isMyRec==0">style="color: #f46614;"</s:if>>
						<%=CodeNameUtil.getResAuthTypeNameByCd(JspUtil.findString("taskId"))%>
						</span>
					</td>
					<td title="${executionId}&nbsp;${remark}"><div class="ellipsisDiv_full">${executionId}&nbsp;${remark}</div> </td>
					<td>
						<%=CodeNameUtil.getDictNameByCd(DictContants.SP_STATUS_QZ,JspUtil.findString("statusCd"))%>
						<s:if test="#subStatusCd==1"><span class="shareFont" title="共享给我"><span class="shareFont"><img src="${ctx}/resources/images/res/res_share_me.png"/></span></span></s:if>
						<s:else>
						<s:if test="otherStatusCd==0"><span class="shareFont" title="已共享"><span class="shareFont"><img src="${ctx}/resources/images/res/res_share_to.png"/></span></span></s:if>
						<s:if test="otherStatusCd==1"><span class="replyFont" title="已回复"><span class="replyFont"><img src="${ctx}/resources/images/res/res_reply.png"/></span></span></s:if>
						</s:else>
					</td>
					<td><div title="<s:date name='applyDate' format='yyyy-MM-dd'/>"><div  class="ellipsisDiv_full"><s:date name="applyDate" format="MM-dd" /></div> </div></td>
				</tr>
				</s:elseif>
				<s:elseif test="moduleCd == 'mesMeetingInfo'">
				<tr class="editTr ellipsisDiv_full" onclick="openTask('','${jbpmId}','${taskId}','${moduleCd}');">
					<td></td>
					<td>${j_moduleName}</td>
					<td class="ellipsisDiv_full" title="<s:property value="userName"/>"><s:property value="userName"/></td>
					<td title="${remark}" colspan="2"><div class="ellipsisDiv_full">${remark}</div></td>
					<td class="ellipsisDiv_full" >
						<s:if test="delayStatusCd!=null && delayStatusCd!=0">
							<span style="color: red;">[<%=CodeNameUtil.getDictNameByCd(DictContants.MES_MEETING_STATUS,JspUtil.findString("delayStatusCd")) %>]</span>
						</s:if>
						<span>
						${jbpmCd}
						</span>
					</td>
					<td>
						<%=CodeNameUtil.getDictNameByCd(DictContants.MES_MEETING_STATUS,JspUtil.findString("statusCd"))%>
					</td>
					<td><div title="<s:date name='applyDate' format='yyyy-MM-dd'/>"><div  class="ellipsisDiv_full"><s:date name="applyDate" format="MM-dd" /></div> </div></td>
				</tr>
				</s:elseif>
				<s:elseif test="moduleCd == 'oaManInfo'">
				<tr class="editTr ellipsisDiv_full" onclick="openTask('','${jbpmId}','${taskId}','${moduleCd}');">
					<td></td>
					<td>${j_moduleName}</td>
					<td style="overflow: hidden; white-space: nowrap;">${JbpmCd}</td>
					<td class="ellipsisDiv_full" title="<s:property value="userName"/>"><s:property value="userName"/></td>
					<td class="ellipsisDiv_full"><%=CodeNameUtil.getResModuleNameByCd(JspUtil.findString("taskId"))%></td>
					<td title="${remark}"><div class="ellipsisDiv_full">${remark}</div> </td>
					<td><%=CodeNameUtil.getDictNameByCd(DictContants.SP_STATUS_QZ,JspUtil.findString("statusCd"))%></td>
					<td><div style="float:left;" title="<s:date name='applyDate' format='yyyy-MM-dd'/>"><div class="ellipsisDiv_full"><s:date name="applyDate" format="MM-dd" /></div></div></td>
				</tr>
				</s:elseif>
				<s:elseif test="moduleCd == 'planWork2'">
				<tr class="editTr ellipsisDiv_full" onclick="openTask('${statusCd}','${jbpmId}','','${moduleCd}','${deptCd}');">
					<td></td>
					<td>${j_moduleName}</td>
					<td style="overflow: hidden; white-space: nowrap;">${JbpmCd}</td>
					<td class="ellipsisDiv_full" title="<s:property value="userName"/>"><s:property value="userName"/></td>
					<td colspan="2" title="${remark}"><div  class="ellipsisDiv_full">${remark}</div> </td>
					<td>进行中</td>
					<td class="ellipsisDiv_full" title="<s:date name='applyDate' format='yyyy-MM-dd'/>"><s:date name="applyDate" format="MM-dd" /></td>
				</tr>
				</s:elseif>
				<s:elseif test="moduleCd == 'planWorkCenter'">
				<tr class="editTr ellipsisDiv_full" onclick="openTask('${statusCd}','${jbpmId}','','${moduleCd}','${deptCd}');">
					<td></td>
					<td style="white-space: nowrap;">${j_moduleName}</td>
					<td style="overflow: hidden; white-space: nowrap;">${JbpmCd}</td>
					<td class="ellipsisDiv_full" title="<s:property value="userName"/>"><s:property value="userName"/></td>
					<td colspan="2" title="${remark}"><div  class="ellipsisDiv_full">${remark}</div> </td>
					<td>进行中</td>
					<td class="ellipsisDiv_full" title="<s:date name='applyDate' format='yyyy-MM-dd'/>"><s:date name="applyDate" format="MM-dd" /></td>
				</tr>
				</s:elseif>
				<s:elseif test="moduleCd == 'costCtrlPurBid'">
				<tr class="editTr" onclick="openTask('${otherStatusCd}','','${jbpmId}','${moduleCd}');">
					<td></td>
					<td>${j_moduleName}</td>
					<td style="overflow: hidden; white-space: nowrap;">${JbpmCd}</td>
					<td colspan="2" class="ellipsisDiv_full" title="<%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("deptCd")) %>"><%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("deptCd")) %></td>
					<td title="${remark}"><div class="ellipsisDiv_full">${remark}</div></td>
					<td>进行中</td>
					<td title="<s:date name='applyDate' format='yyyy-MM-dd'/>"><s:date name="applyDate" format="MM-dd" /></td>
				</tr>
				</s:elseif>
				<s:else>
				<tr class="editTr ellipsisDiv_full">
					<td></td>
					<td onclick="diplayAll('${statusCd}','${moduleCd}');" >${j_moduleName}</td>
					<td onclick="openTask('${statusCd}','${jbpmId}','${taskId}','${moduleCd}');" style="overflow: hidden; white-space: nowrap;">${JbpmCd}</td>
					<td onclick="openTask('${statusCd}','${jbpmId}','${taskId}','${moduleCd}');" ><s:property value="userName"/></td>
					<td onclick="openTask('${statusCd}','${jbpmId}','${taskId}','${moduleCd}');" ><%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("deptCd"))%></td>
					<td onclick="openTask('${statusCd}','${jbpmId}','${taskId}','${moduleCd}');" ><%=CodeNameUtil.getPositionNameByCd(JspUtil.findString("positionCd"))%></td>
					<td onclick="openTask('${statusCd}','${jbpmId}','${taskId}','${moduleCd}');" ><%=CodeNameUtil.getDictNameByCd(JspUtil.findString("moduleCd").equals("reimburse")?DictContants.JBPM_STATUS_BX:DictContants.JBPM_STATUS_CC,JspUtil.findString("statusCd"))%></td>
					<td title="<s:date name='applyDate' format='yyyy-MM-dd'/>" onclick="openTask('${statusCd}','${jbpmId}','${taskId}','${moduleCd}');" ><s:date name="applyDate" format="MM-dd" /></td>
				</tr>
				</s:else>
			</s:iterator>
		</table>
	</s:else>
</div>
<script language="javascript">
daiban_sort_num = 0;
<s:iterator value="mapCount" var="i">
	if("ceomeeting"=="${key}" || "specialtask"=="${key}"){
		var value_temp = 0;
		try{
			value_temp = $("#${key}_num").html();
		}catch(e){}
		if(0==value_temp || null==value_temp){
			var new_record_html = '<tr onClick=\'jbpmModuleClick(\"ceomeeting\")\'><td nowrap="nowrap" style="padding-left:0px;">&bull;&nbsp;指令单跟踪</td><td align="right" nowrap="nowrap"><span id="${key}_num">${value}</span>项</td></tr>';
			$("#daiban_start_tr").after(new_record_html);
		}
		daiban_sort_num++;
	}else if("resApprove"=="${key}"){
		var value_temp = 0;
		try{
			value_temp = $("#${key}_num").html();
		}catch(e){}
		if(0==value_temp || null==value_temp){
			var new_record_html = '<tr onClick=\'jbpmModuleClick(\"resApprove\")\'><td nowrap="nowrap" style="padding-left:0px;">&bull;&nbsp;网上审批</td><td align="right" nowrap="nowrap"><span id="${key}_num">${value}</span>项</td></tr>';
			$("#daiban_start_tr").after(new_record_html);
		}
		daiban_sort_num++;
	}else if("planWork2"=="${key}"){
		var value_temp = 0;
		try{
			value_temp = $("#${key}_num").html();
		}catch(e){}
		if(0==value_temp || null==value_temp){
			var new_record_html = '<tr onClick=\'jbpmModuleClick(\"planWork2\")\'><td nowrap="nowrap" style="padding-left:0px;">&bull;&nbsp;中心月计划</td><td align="right" nowrap="nowrap"><span id="${key}_num">${value}</span>项</td></tr>';
			$("#daiban_start_tr").after(new_record_html);
		}
		daiban_sort_num++;
	}else if("planWorkCenter"=="${key}"){
		var value_temp = 0;
		try{
			value_temp = $("#${key}_num").html();
		}catch(e){}
		if(0==value_temp || null==value_temp){
			var new_record_html = '<tr onClick=\'jbpmModuleClick(\"planWorkCenter\")\'><td nowrap="nowrap" style="padding-left:0px;">&bull;&nbsp;中心内部任务</td><td align="right" nowrap="nowrap"><span id="${key}_num">${value}</span>项</td></tr>';
			$("#daiban_start_tr").after(new_record_html);
		}
		daiban_sort_num++;
	}else if("oaFileFollowed"=="${key}"){
		var value_temp = 0;
		try{
			value_temp = $("#${key}_num").html();
		}catch(e){}
		if(0==value_temp || null==value_temp){
			var new_record_html = '<tr onClick=\'jbpmModuleClick("oaFileFollowed")\'><td nowrap="nowrap" style="padding-left:0px;">&bull;&nbsp;文件跟踪</td><td align="right" nowrap="nowrap"><span id="${key}_num">${value}</span>项</td></tr>';
			$("#daiban_start_tr").after(new_record_html);
		}
		daiban_sort_num++;
	}else if("execPlan"=="${key}"){
		<s:if test="aWritePoint">
		var value_temp = 0;
		try{
			value_temp = $("#${key}_num").html();
		}catch(e){}
		if(0==value_temp || null==value_temp){
			var new_record_html = '<tr onClick=\'jbpmModuleClick("execPlan")\'><td nowrap="nowrap" style="padding-left:0px;">&bull;&nbsp;项目开发</td><td align="right" nowrap="nowrap"><span id="${key}_num">${value}</span>项</td></tr>';
			$("#daiban_start_tr").after(new_record_html);
		}
		daiban_sort_num++;
		</s:if>
	}else{
		var value_temp = 0;
		try{
			value_temp = $("#${key}_num").html();
		}catch(e){}
		if(0==value_temp || null==value_temp){
			var new_record_html = '<tr onClick=\'jbpmModuleClick(\"${key}\")\'><td nowrap="nowrap" style="padding-left:0px;">&bull;&nbsp;成本招采</td><td align="right" nowrap="nowrap"><span id="${key}_num">${value}</span>项</td></tr>';
			$("#daiban_start_tr").after(new_record_html);
		}
		daiban_sort_num++;
	}
</s:iterator>
try{
	//运行首页关注事项提示，在这里运行时为了计算待办事项提示和关注事项提示的高度关系，在关注事项提示中计算
	refreshHomeAttention();
}catch(e){}
</script>
<div id="div_content_attention" style="width:100%; display:none; float:left;">
	<s:if test="oaAllAttentionCount == 0">
		<div class="noResult">没有更新的关注事项</div>
	</s:if>
	<s:else>
		<table class="home_table" width="100%" cellpadding="0" cellspacing="0" border="0">
			<col width="8px"/>
			<col/>
			<col width="60px"/>
			<col width="80px" align="right"/>
			
			<s:set var="oaAttentionListCount1" value="0"/>
			<s:set var="oaAttentionListCount2" value="1"/>
			<s:iterator value="oaAllAttentionList" status="t">
				<s:set var="oaAttentionListCount1">${oaAttentionListCount1+1}</s:set>
				<tr class="js_attention_${oaAttentionListCount2}" style="display:none;">
					<td>
						<c:if test="${oaAttentionListCount1==pageSizeAttention}">
							<s:set var="oaAttentionListCount1">0</s:set>
							<s:set var="oaAttentionListCount2">${oaAttentionListCount2+1}</s:set>
						</c:if>
					</td>
					<td class="ellipsisDiv_full" align="left" onclick="openHomeAttention('${moduleCd}','${entityId}');"><s:property value="content"/></td>
					<td align="left" onclick="openHomeAttention('${moduleCd}','${entityId}');">${statusName}</td>
					<td align="right" onclick="openHomeAttention('${moduleCd}','${entityId}');" style="padding-right:10px;"><s:date name="updatedDate" format="MM-dd" /></td>
				</tr>
			</s:iterator>
		</table>
		<script language="javascript">$(".js_attention_1").show();</script>
	</s:else>
</div>
<script language="javascript">
	now_in_daiban_or_attention = "";
	if(""==before_in_daiban_or_attention){
		doChangeDaibanAttention("daiban");
	}else{
		doChangeDaibanAttention(before_in_daiban_or_attention);
	}
	activePageAttention();
</script>
