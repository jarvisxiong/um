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
	<s:if test="showDivName == 'zero'">
	<div style="margin-left:8px;width:85%;background-color:#eaeaea;height:453px;line-height: 426px;font-size:18px;text-align:center;font-weight: bold;color:#909090; ">
	没有关注事项
	</div>
	</s:if>
	<s:else>
		<div style="width:85%;float:left;margin-left: 10px;">
	    <div style="margin-left:12px;margin-top:8px;cursor:pointer;">
	    <s:iterator value="homeWaitList" status="s">
	    	<s:if test="moduleCd == 'resApprove'">
	    	<span onclick="showSchedule('${moduleCd}','1');" id="${moduleCd}" isNum="${waitNum}" style="color:#16a0e1;" class="schetitle"> 网批<span class="titleCount">(${waitNum})</span></span>
	    	</s:if>
	    	<s:elseif test="moduleCd == 'ceomeeting'">
	    	<span onclick="showSchedule('${moduleCd}','1');" id="${moduleCd}" isNum="${waitNum}" class="schetitle">指令单<span style="color:#f46614" class="titleCount">(${waitNum})</span></span>
	    	</s:elseif>
	    	<s:elseif test="moduleCd == 'planWorkCenter'">
	    	<span onclick="showSchedule('${moduleCd}','1');" id="${moduleCd}" isNum="${waitNum}" class="schetitle">内部任务<span style="color:#f46614" class="titleCount">(${waitNum})</span></span>
	    	</s:elseif>
	    	<s:elseif test="moduleCd == 'oaFileFollowed'">
	    	<span onclick="showSchedule('${moduleCd}','1');" id="${moduleCd}" isNum="${waitNum}" class="schetitle">文件跟踪<span style="color:#f46614" class="titleCount">(${waitNum})</span></span>
	    	</s:elseif>
	    	<s:elseif test="moduleCd == 'costCtrlPurBid'">
	    	<span onclick="showSchedule('${moduleCd}','1');" id="${moduleCd}" isNum="${waitNum}" class="schetitle">成本招采<span style="color:#f46614" class="titleCount">(${waitNum})</span></span>
	    	</s:elseif>
	    	
	    </s:iterator>
	    <input type="hidden"  id="selectedWaitModule" value="${selectedModuleCd }"/>
	     <input type="hidden"  id="pageNo" value="${pageNo }"/>
		</div>
		<%--网批 --%>
		<div id="div_res_show" class="sche" style="height:455px;">
			<div>
			<s:iterator value="resResult">
					<s:set var="subStatusCd"><p:code2name mapCodeName="mapSubStatusCd" value="jbpmTaskId"/></s:set>
					<s:set var="isMyRec"><p:code2name mapCodeName="mapMyRec" value="jbpmTaskId"/></s:set>
				 	<%-- 网批 --%>
				 	<s:if test="selectedModuleCd == 'resApprove'">
				 	 <div class="content_left"  onclick="openTask('','${jbpmId}','${taskId}','${moduleCd}');">
						 <div style="float:left;margin-left:12px;margin-top:8px;max-width: 80%;" class="ellipsisDiv font_title">
				 		 <s:if test="delayStatusCd!=null && delayStatusCd!=0">
							<span style="color: red;">[<%=CodeNameUtil.getDictNameByCd(DictContants.DELAY_STATUS_CD,JspUtil.findString("delayStatusCd")) %>]</span>
						</s:if>
						<span <s:if test="#isMyRec==0">style="color: #f46614;"</s:if>>
						<%=CodeNameUtil.getResAuthTypeNameByCd(JspUtil.findString("taskId"))%>
						</span>
						<%=CodeNameUtil.getDictNameByCd(DictContants.SP_STATUS_QZ,JspUtil.findString("statusCd"))%>
						<s:if test="#subStatusCd==1"><span class="shareFont" title="共享给我"><span class="shareFont"><img src="${ctx}/resources/images/res/res_share_me.png"/></span></span></s:if>
						<s:else>
						<s:if test="otherStatusCd==0"><span class="shareFont" title="已共享"><span class="shareFont"><img src="${ctx}/resources/images/res/res_share_to.png"/></span></span></s:if>
						<s:if test="otherStatusCd==1"><span class="replyFont" title="已回复"><span class="replyFont"><img src="${ctx}/resources/images/res/res_reply.png"/></span></span></s:if>
						</s:else>
				 	</s:if>
				 	<%--指令单 --%>
				 	<s:elseif test="selectedModuleCd == 'ceomeeting'">
				 	<div class="content_left"  onclick="openTask('','${jbpmId}','','${moduleCd}');">
						 <div style="float:left;margin-left:12px;margin-top:8px;max-width: 80%;" class="ellipsisDiv font_title">
				 			<p:code2name mapCodeName="jbpmTaskSubjMap" value="jbpmTaskId"/>
				 	</s:elseif>
				 	<%--内部任务 --%>
				 	<s:elseif test="selectedModuleCd == 'planWorkCenter'">
				 	<div class="content_left"  onclick="openTask('${statusCd}','${jbpmId}','','${moduleCd}','${deptCd}');">
						 <div style="float:left;margin-left:12px;margin-top:8px;max-width: 80%;" class="ellipsisDiv font_title">
				 		${remark}
				 	</s:elseif>
				 	<%--文件跟踪 --%>
				 	<s:elseif test="moduleCd == 'oaFileFollowed'">
				 	<div class="content_left"  onclick="openTask('${statusCd}','${jbpmId}','','${moduleCd}');">
						 <div style="float:left;margin-left:12px;margin-top:8px;max-width: 80%;" class="ellipsisDiv font_title">
				 		${remark}
				 	</s:elseif>
				 	<%--成本招采 --%>
				 	<s:elseif test="moduleCd == 'costCtrlPurBid'">
				 	<div class="content_left"  onclick="openTask('${otherStatusCd}','','${jbpmId}','${moduleCd}','${deptCd}');">
						 <div style="float:left;margin-left:12px;margin-top:8px;max-width: 80%;" class="ellipsisDiv font_title">
				 		${remark}
				 	</s:elseif>
				 	<s:else>
				 	<div class="content_left"  onclick="openTask('${statusCd}','${jbpmId}','${taskId}','${moduleCd}');">
						 <div style="float:left;margin-left:12px;margin-top:8px;max-width: 80%;" class="ellipsisDiv font_title">
				 		${remark}
				 	</s:else>
					</div>
					<div class="font_time" style="float:left;margin-top:10px;">&nbsp;<s:date name="applyDate" format="MM-dd" /></div>
					<div class="div_clear"></div>
					<div style="margin-left:12px;float:left;" class="font_dept"><%=CodeNameUtil.getCenterOrgNameByOrgCd(JspUtil.findString("createdCenterCd"))%>-</div>
					<div style="float:left;" class="font_name"><s:property value="userName"/>&nbsp;</div>
					<div style="float:left;" class="font_dept">-【${moduleName}】</div>
					<div class="div_clear"></div>
			</div>
			</s:iterator>
			</div>
			<div style="padding-top: 8px;margin-left: 10px;" id="page">
			</div>
		</div>
	</div>	
	</s:else>
	<script>
	var moduleCd = $("#selectedWaitModule").val();
	var moduleAllNum = $("#"+moduleCd).attr("isNum");
	var pageContent = "<div id=\"Page_1\" class=\"content_left_page_selected\" onclick=\"showSchedule('${selectedModuleCd }','1');\">1</div>";
	if(moduleCd != ""){
		$("#"+moduleCd).children().css("color","#16a0e1");//schetitle
		$("#"+moduleCd).css("color","#16a0e1");
		
	}
	//显示页数
	if(moduleAllNum >8){
		pageContent  = pageContent + "<div id=\"Page_2\" class=\"content_left_page\" onclick=\"showSchedule('${selectedModuleCd }','2');\">2</div>";
	}
	if(moduleAllNum > 16){
		pageContent  = pageContent + "<div id=\"Page_3\" class=\"content_left_page\" onclick=\"showSchedule('${selectedModuleCd }','3');\">3</div>";
	}
	//网批
	if(moduleCd == "resApprove"){
		pageContent  = pageContent + "<div id=\"Page_3\" class=\"content_left_page\" onclick=\"parent.TabUtils.newTab('156','网上审批','/PowerDesk/res/res-approve-info.action');\">...</div>";
	}
	//指令单
	else if(moduleCd == "ceomeeting"){
	}
	//内部任务
	else if(moduleCd == "planWorkCenter"){
		pageContent  = pageContent + "<div id=\"Page_3\" class=\"content_left_page\" onclick=\"parent.TabUtils.newTab('138','中心内部任务','/PowerDesk/plan/plan-work-center!getAllPlan.action');\">...</div>";
	}
	//文件跟踪
	else if(moduleCd == "oaFileFollowed"){
		pageContent  = pageContent + "<div id=\"Page_3\" class=\"content_left_page\" onclick=\"parent.TabUtils.newTab('132','文件跟踪','/PowerDesk/oa/oa-file-followed.action');\">...</div>";
	}
	//成本招采
	else if(moduleCd == "costCtrlPurBid"){
		
		pageContent  = pageContent + "<div id=\"Page_3\" class=\"content_left_page\" onclick=\"parent.TabUtils.newTab('185','项目招采计划','/PowerDesk/plan/cost-ctrl-bid-purc.action');\">...</div>";
	}
	$("#page").html(pageContent);
	</script>

								
					
