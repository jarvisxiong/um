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
	
<%@page import="org.apache.commons.lang.StringUtils"%>
	<s:if test="showDivName == 'zero'">
	<div style="width:100%;margin-left:0px;padding-bottom: 0px;border-bottom-width:1px;
						border-bottom-color:#CCCCCC;border-bottom-style:solid;height:30px;" class="font_title_16">
						<div style="float:left;margin-right:8px;" >
							<div style="float:left;font-weight: bold;line-height:30px;height:30px;text-align:center;" iswait="mesMeetingInfo"  title="纪要">纪要</div>
							<div style="float:left;margin-left:8px;margin-right:8px;font-size:14px;margin-top:6px;" isline="true">|</div>
							<div style="float:left;font-weight: bold;line-height:30px;height:30px;text-align:center;" iswait="resApprove"  title="网批">网批</div>
							<div style="float:left;margin-left:8px;margin-right:8px;font-size:14px;margin-top:6px;" isline="true">|</div>
							<%--
							<div style="float:left;font-weight: bold;line-height:30px;height:30px;text-align:center;" iswait="planWorkCenter"  title="任务">任务</div>
							<div style="float:left;margin-left:8px;margin-right:8px;font-size:14px;margin-top:6px;" isline="true">|</div>
							 --%>
							<div style="float:left;font-weight: bold;line-height:30px;height:30px;text-align:center;" iswait="ceomeeting"   title="指令">指令</div>
							<div style="float:left;margin-left:8px;margin-right:8px;font-size:14px;margin-top:6px;" isline="true">|</div>
							<div style="float:left;font-weight: bold;line-height:30px;height:30px;text-align:center;" iswait="planTarget"  title="计划">计划</div>
							<s:if test="hrApproveCount != -1">
							<div style="float:left;margin-left:8px;margin-right:8px;font-size:14px;margin-top:6px;" isline="true">|</div>
							<div style="float:left;cursor: pointer;font-weight: bold;line-height:30px;height:30px;text-align:center;"  class="notice_link" onclick="doShowHrApprove();"  title="人员模块">
						    	人员<span style="font-size:12px;color:#f46614;">(${hrApproveCount})</span>
						    </div>
						    </s:if>
						</div>
						<div id="waitPage" style="float:right;margin-right: 0px;">
						
						</div>
						<div class="div_clear"></div>
	</div>
	<div style="margin-left:0px;width:100%;background-color:#cbcbcb;height:470px;line-height: 425px;font-size:18px;text-align:center;font-weight: bold;color:#909090; ">
	没有待办事项
	</div>
	</s:if>
	<s:else>
		<div style="width:100%;margin-left:0px;padding-bottom: 0px;border-bottom-width:1px;
						border-bottom-color:#CCCCCC;border-bottom-style:solid;height:30px;" class="font_title_16">
						<div style="float:left;margin-right:8px;height:30px;" >
							<s:set var="resApprove">false</s:set>
							<s:set var="resApproveNum">0</s:set>
							<s:set var="planTarget">false</s:set>
							<s:set var="planTargetNum">0</s:set>
							<s:set var="planWorkCenter">false</s:set>
							<s:set var="planWorkCenterNum">0</s:set>
							<s:set var="ceomeeting">false</s:set>
							<s:set var="ceomeetingNum">0</s:set>
							<s:set var="mesMeetingInfo">false</s:set>
							<s:set var="mesMeetingInfoNum">0</s:set>
							<s:set var="targetModuleCd">planTarget</s:set>
							<s:iterator value="homeWaitList" status="s">
						    	<s:if test="moduleCd == 'resApprove'">
						    	<s:set var="resApprove">true</s:set>
						    	<s:set var="resApproveNum">${waitNum}</s:set>
						    	</s:if>
						    	<s:elseif test="moduleCd == 'planTarget'">
						    	<s:set var="planTarget">true</s:set>
						    	<s:set var="planTargetNum">${waitNum}</s:set>
						    	<s:set var="targetModuleCd">${targetCd}</s:set>
						    	</s:elseif>
						    	<s:elseif test="moduleCd == 'ceomeeting'">
						    	<s:set var="ceomeeting">true</s:set>
						    	<s:set var="ceomeetingNum">${waitNum}</s:set>
						    	</s:elseif>
						    	<s:elseif test="moduleCd == 'planWorkCenter'">
						    	<s:set var="planWorkCenter">true</s:set>
								<s:set var="planWorkCenterNum">${waitNum}</s:set>
						    	</s:elseif>
						    	<s:elseif test="moduleCd == 'mesMeetingInfo'">
						    	<s:set var="mesMeetingInfo">true</s:set>
						    	<s:set var="mesMeetingInfoNum">${waitNum}</s:set>
						    	</s:elseif>
						    	<s:elseif test="moduleCd == 'costCtrlPurBid'">
						    	</s:elseif>
						    </s:iterator>
							<s:if test="#mesMeetingInfo == 'true'">
								<div style="float:left;cursor: pointer;font-weight: bold;line-height:30px;height:30px;text-align:center;" isNum="${mesMeetingInfoNum}" iswait="mesMeetingInfo" class="notice_link" onclick="refreshJbpm('mesMeetingInfo','1');" id="mesMeetingInfo" title="纪要">
						    	纪要<span style="font-size:12px;color:#f46614;">(${mesMeetingInfoNum})</span>
						    	</div>
							</s:if>
							<s:else>
								<div style="float:left;font-weight: bold;line-height:30px;height:30px;text-align:center;" iswait="mesMeetingInfo"  title="纪要">纪要</div>
							</s:else>
							<div style="float:left;margin-left:8px;margin-right:8px;font-size:14px;margin-top:6px;" isline="true">|</div>
							
							<s:if test="#resApprove == 'true'">
								<div style="float:left;cursor: pointer;font-weight: bold;height:30px;text-align:center;" isNum="${resApproveNum}" iswait="resApprove" class="notice_link" onclick="refreshJbpm('resApprove','1');" id="resApprove" title="网批">
						    	网批<span style="font-size:12px;color:#f46614;">(${resApproveNum})</span>
						    	</div>
							</s:if>
							<s:else>
								<div style="float:left;font-weight: bold;" iswait="resApprove"  title="网批">网批</div>
							</s:else>
							<div style="float:left;margin-left:8px;margin-right:8px;font-size:14px;margin-top:6px;" isline="true">|</div>
							<%--
							<s:if test="#planWorkCenter == 'true'">
								<div style="float:left;cursor: pointer;font-weight: bold;line-height:30px;height:30px;text-align:center;" isNum="${planWorkCenterNum}" iswait="planWorkCenter" class="notice_link" onclick="refreshJbpm('planWorkCenter','1');" id="planWorkCenter" title="任务">
						    	任务<span style="font-size:12px;color:#f46614;font-weight: bold;">(${planWorkCenterNum})</span>
						    	</div>
							</s:if>
							<s:else>
								<div style="float:left;font-weight: bold;line-height:30px;height:30px;text-align:center;" iswait="planWorkCenter"  title="任务">任务</div>
							</s:else>
							<div style="float:left;margin-left:8px;margin-right:8px;font-size:14px;margin-top:6px;" isline="true">|</div>
							 --%>
							
							<s:if test="#ceomeeting == 'true'">
								<div style="float:left;cursor: pointer;font-weight: bold;line-height:30px;height:30px;text-align:center;" isNum="${ceomeetingNum}" iswait="ceomeeting" class="notice_link" onclick="refreshJbpm('ceomeeting','1');" id="ceomeeting"  title="指令">
						    	指令<span style="font-size:12px;color:#f46614;">(${ceomeetingNum})</span>
						    	</div>
							</s:if>
							<s:else>
								<div style="float:left;font-weight: bold;line-height:30px;height:30px;text-align:center;" iswait="ceomeeting"   title="指令">指令</div>
							</s:else>
							<div style="float:left;margin-left:8px;margin-right:8px;font-size:14px;margin-top:6px;" isline="true">|</div>
							
							<s:if test="#planTarget == 'true'">
								<div style="float:left;cursor: pointer;font-weight: bold;line-height:30px;height:30px;text-align:center;" isNum="${planTargetNum}" iswait="planTarget" class="notice_link" onclick="refreshJbpm('planTarget','1');" id="planTarget" title="计划">
						    	计划<span style="font-size:12px;color:#f46614;">(${planTargetNum})</span>
						    	</div>
							</s:if>
							<s:else>
								<div style="float:left;font-weight: bold;line-height:30px;height:30px;text-align:center;" iswait="planTarget"  title="计划">计划</div>
							</s:else>
							
							<s:if test="hrApproveCount != -1">
							<div style="float:left;margin-left:8px;margin-right:8px;font-size:14px;margin-top:6px;" isline="true">|</div>
							<div style="float:left;cursor: pointer;font-weight: bold;line-height:30px;height:30px;text-align:center;"  class="notice_link" onclick="doShowHrApprove();"  title="人员模块">
						    	人员<span style="font-size:12px;color:#f46614;">(${hrApproveCount})</span>
						    </div>
						    </s:if>
							
							
						</div>
						<div id="waitPage" style="float:right;margin-right: 0px;"></div>
						<div class="div_clear"></div>
		</div>
		<div style="padding-top: 4px;" id="page">
		</div>
		<div style="width:100%;float:left;margin-left: 0px;">
	    <div style="margin-left:0px;margin-top:0px;cursor:pointer;">
	    <input type="hidden"  id="selectedWaitModule" value="${selectedModuleCd }"/>
	     <input type="hidden"  id="pageNo" value="${pageNo }"/>
		</div>
		<%--网批 --%>
		<div id="div_res_show" class="sche" style="height:455px;">
			<div>
				<s:iterator value="resResult">
					<s:set var="myStatusCd" value="1"/>
					<s:set var="myUiid"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
					<s:iterator value="jbpmTaskCandidates">
						<s:if test="userCd==#myUiid"><s:set var="myStatusCd" value="statusCd"/></s:if>
					</s:iterator>
					<s:set var="subStatusCd"><p:code2name mapCodeName="mapSubStatusCd" value="jbpmTaskId"/></s:set>
					<s:set var="isMyRec"><p:code2name mapCodeName="mapMyRec" value="jbpmTaskId"/></s:set>
					<c:set var="j_moduleName"><%=CodeNameUtil.getDictNameByCd(DictContants.JBPM_MODULE_CD,JspUtil.findString("moduleCd") ) %></c:set>
				 	<%-- 网批 --%>
				 	<s:if test="selectedModuleCd == 'resApprove'">
				 	 <div class="content_left" style="padding:2px 0px 8px 5px;height:30px;margin-top:4px;</div>" onclick="openTask('','${jbpmId}','${taskId}','${moduleCd}','${deptCd}');" title="<%=CodeNameUtil.getResAuthTypeNameByCd(JspUtil.findString("taskId"))%>&nbsp;${remark}">
						 <div style="float:left;margin-left:0px;margin-top:0px;max-width: 80%;" class="ellipsisDiv font_title">
						 <s:if test="#subStatusCd==1"><span style="color:#02592b;font-weight: bold;" title="共享给我">【共享】</span></s:if>
				 		 <s:if test="delayStatusCd!=null && delayStatusCd!=0">
							<span style="color: red;">[<%=CodeNameUtil.getDictNameByCd(DictContants.DELAY_STATUS_CD,JspUtil.findString("delayStatusCd")) %>]</span>
						</s:if>
						<span style="<s:if test='#myStatusCd==2'>color:#cbcbcb;</s:if><s:else><s:if test="#isMyRec==0">color: #f46614;</s:if></s:else>">
						<span>${remark}</span>
						<span>【<%=CodeNameUtil.getResAuthTypeNameByCd(JspUtil.findString("taskId"))%>】</span>
						</span>
						&nbsp;
				 	</s:if>
				 	<%--指令单 --%>
				 	<s:elseif test="selectedModuleCd == 'ceomeeting'">
				 	<div class="content_left" style="padding:2px 0px 8px 5px;height:30px;margin-top:4px;"  onclick="openTask('','${jbpmId}','','${moduleCd}');" title="<p:code2name mapCodeName="jbpmTaskSubjMap" value="jbpmTaskId"/>">
						 <div style="float:left;margin-left:0px;margin-top:0px;max-width: 80%;" class="ellipsisDiv font_title">
				 			<p:code2name mapCodeName="jbpmTaskSubjMap" value="jbpmTaskId"/>
				 	</s:elseif>
				 	<%--纪要系统 --%>
				 	<s:elseif test="moduleCd == 'mesMeetingInfo'">
				 	<div class="content_left" style="padding:2px 0px 8px 5px;height:30px;margin-top:4px;" onclick="openTask('','${jbpmId}','${taskId}','${moduleCd}');" title="${remark}">
						 <div style="float:left;margin-left:0px;margin-top:0px;max-width: 80%;" class="ellipsisDiv font_title">
				 		${remark}
				 	</s:elseif>
				 	<%--内部任务 --%>
				 	<s:elseif test="selectedModuleCd == 'planWorkCenter'">
				 	<div class="content_left" style="padding:2px 0px 8px 5px;height:30px;margin-top:4px;"  onclick="openTask('${statusCd}','${jbpmId}','','${moduleCd}','${deptCd}');" title="${remark}">
						 <div style="float:left;margin-left:0px;margin-top:0px;max-width: 80%;" class="ellipsisDiv font_title">
				 		${remark}
				 	</s:elseif>
				 	<%--成本招采 --%>
				 	<s:elseif test="moduleCd == 'costCtrlPurBid'">
				 	<div class="content_left" style="padding:2px 0px 8px 5px;height:30px;margin-top:4px;" onclick="openTask('${otherStatusCd}','','${jbpmId}','${moduleCd}','${deptCd}');" title="${remark}">
						 <div style="float:left;margin-left:0px;margin-top:0px;max-width: 80%;" class="ellipsisDiv font_title">
				 		${remark}
				 	</s:elseif>
				 	<s:else>
				 	<div class="content_left" style="padding:2px 0px 8px 5px;height:30px;margin-top:4px;" onclick="openTask('${statusCd}','${jbpmId}','${taskId}','${moduleCd}','${deptCd}');" title="${remark}">
						 <div style="float:left;margin-left:0px;margin-top:0px;max-width: 80%;" class="ellipsisDiv font_title">
				 		${remark}
				 	</s:else>
					</div>
					
					<s:if test="selectedModuleCd == 'resApprove'">
						&nbsp;
						<s:if test="otherStatusCd==0"><span class="shareFont" title="已共享"><span class="shareFont"><img src="${ctx}/resources/images/res/res_share_to.png"/></span></span></s:if>
						<s:if test="otherStatusCd==1"><span class="replyFont" title="已回复"><span class="replyFont"><img src="${ctx}/resources/images/res/res_reply.png"/></span></span></s:if>
						&nbsp;
						<%--<%=CodeNameUtil.getDictNameByCd(DictContants.SP_STATUS_QZ,JspUtil.findString("statusCd"))--%>
						&nbsp;
						<div class="div_clear"></div>
					
					<div class="font_name" style="float:left;margin-top:2px;<s:if test='#myStatusCd==2'>color:#cbcbcb;</s:if>"><s:property value="jbpmCd"/>&nbsp;</div>
					<div class="font_dept" style="float:left;margin-top:2px;<s:if test='#myStatusCd==2'>color:#cbcbcb;</s:if>"><s:property value="executionId"/>&nbsp;</div>
					<div class="font_name" style="float:left;margin-top:2px;<s:if test='#myStatusCd==2'>color:#cbcbcb;</s:if>"><s:property value="userName"/>&nbsp;</div>
					<div class="font_time" style="float:left;margin-top:2px;">&nbsp;<s:date name="applyDate" format="MM-dd" /></div>
					</s:if>
					<s:elseif test="selectedModuleCd == 'mesMeetingInfo'">
					<div class="div_clear"></div>
					<div style="margin-left:0px;float:left;margin-top:2px;" class="font_dept">
					<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator"))%>
					-<s:date name="applyDate" format="MM-dd" /></div>
					</s:elseif>
					<s:else>
					<div class="div_clear"></div>
					<div style="margin-left:0px;float:left;margin-top:2px;" class="font_dept">
					<%
					if(StringUtils.isNotBlank(JspUtil.findString("deptCd"))){
						out.print(CodeNameUtil.getCenterOrgNameByOrgCd(JspUtil.findString("deptCd")));
					}
					%>
					-<s:date name="applyDate" format="MM-dd" /></div>
					</s:else>
					<div class="div_clear"></div>
			</div>
			</s:iterator>
			</div>
			
		</div>
	</div>	
	</s:else>
	<script>
	var targetCd = "<s:property value='#targetModuleCd'/>";
	var moduleCd = $("#selectedWaitModule").val();
	var moduleAllNum = $("#"+moduleCd).attr("isNum");
	var pageContent = "<div id=\"Page_1\" class=\"content_left_page_selected\" onclick=\"refreshJbpm('${selectedModuleCd }','1');\">1</div>";
	var waitPageContent = "";
	if(moduleCd != ""){
		$("#"+moduleCd).removeClass().addClass("notice_link_selected");
		
	}
	//显示页数
	if(moduleAllNum >10){
		pageContent  = pageContent + "<div id=\"Page_2\" class=\"content_left_page\" onclick=\"refreshJbpm('${selectedModuleCd }','2');\">2</div>";
	}
	if(moduleAllNum > 20){
		pageContent  = pageContent + "<div id=\"Page_3\" class=\"content_left_page\" onclick=\"refreshJbpm('${selectedModuleCd }','3');\">3</div>";
	}
	//网批
	if(moduleCd == "resApprove"){
		pageContent  = pageContent + "<div id=\"more\" class=\"content_left_page\" style='margin-right: 0px;' onclick=\"parent.TabUtils.newTab('156','网上审批','${ctx}/res/res-approve-info.action');\" ispop=\"parent.TabUtils.newTab('156','网上审批','${ctx}/res/res-approve-info.action');\">...</div>";
	}
	//指令单
	else if(moduleCd == "ceomeeting"){
		pageContent  = pageContent + "<div id=\"more\" class=\"content_left_page\" style='margin-right: 0px;' onclick=\"parent.TabUtils.newTab('172','指令单跟踪','${ctx}/oa/oa-meeting.action?moduleCd=zc');\" ispop=\"parent.TabUtils.newTab('172','指令单跟踪','${ctx}/oa/oa-meeting.action?moduleCd=zc');\">...</div>";
	}
	//内部任务
	else if(moduleCd == "planWorkCenter"){
		pageContent  = pageContent + "<div id=\"more\" class=\"content_left_page\" style='margin-right: 0px;' onclick=\"parent.TabUtils.newTab('138','中心内部任务','${ctx}/plan/plan-work-center!getAllPlan.action');\" ispop=\"parent.TabUtils.newTab('138','中心内部任务','${ctx}/plan/plan-work-center!getAllPlan.action');\">...</div>";
	}
	//中心月计划
	else if(moduleCd == "planTarget"){
		if(targetCd == "planTarget"){
			pageContent  = pageContent + "<div id=\"more\" class=\"content_left_page\" style='margin-right: 0px;' onclick=\"parent.TabUtils.newTab('132','中心月计划','${ctx}/plan/plan-target!monEnter.action');\" ispop=\"parent.TabUtils.newTab('132','中心月计划','${ctx}/plan/plan-target!monEnter.action');\">...</div>";
		}else if(targetCd == "planWorkCenter"){
			pageContent  = pageContent + "<div id=\"more\" class=\"content_left_page\" style='margin-right: 0px;' onclick=\"parent.TabUtils.newTab('138','中心内部任务','${ctx}/plan/plan-work-center!getAllPlan.action');\" ispop=\"parent.TabUtils.newTab('138','中心内部任务','${ctx}/plan/plan-work-center!getAllPlan.action');\">...</div>";
		}
		
	}
	//成本招采
	else if(moduleCd == "costCtrlPurBid"){
		pageContent  = pageContent + "<div id=\"more\" class=\"content_left_page\" style='margin-right: 0px;' onclick=\"parent.TabUtils.newTab('185','项目招采计划','${ctx}/plan/cost-ctrl-bid-purc.action');\" ispop=\"parent.TabUtils.newTab('185','项目招采计划','${ctx}/plan/cost-ctrl-bid-purc.action');\">...</div>";
	}
	//纪要管理
	else if(moduleCd == "mesMeetingInfo"){
		pageContent  = pageContent + "<div id=\"more\" class=\"content_left_page\" style='margin-right: 0px;' onclick=\"parent.TabUtils.newTab('213','纪要决议管理','${ctx}/mes/mes-meeting-info!index.action');\" ispop=\"parent.TabUtils.newTab('213','纪要决议管理','${ctx}/mes/mes-meeting-info!index.action');\">...</div>";
	}
	$("#page").html(pageContent);
	//$("#waitPage").html(waitPageContent);
	</script>
