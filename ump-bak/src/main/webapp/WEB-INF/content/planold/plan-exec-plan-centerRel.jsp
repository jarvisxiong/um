<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/global.jsp"%>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/planold/plan-exec-plan.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/userSelection.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/userChoose.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/planold/plan.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/planold/plan-exec-plan-centerRel.css" />
	
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/planold/plan-exec-plan.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/planold/plan-exec-plan-centerRel.js"></script>
	
	<script type="text/javascript">
		function trOnclick(projectCd){
			if(projectCd){
				if($(".tr_"+projectCd).css("display") == "none")
				    $(".tr_"+projectCd).show();
				else{
					
					$(".tr_"+projectCd).hide();
				}	
			}
		}
		$(function(){
			var bodyHeight = Number(parent._bodyHeight);
			bodyHeight = bodyHeight -63-24-7-22 -30-33-8;
			$("#main_list_div").css("height",bodyHeight+"px");
			return;
		});
	</script>
</head>
<body>
	<div class="title_bar">
		<div class="title_bar_h">地产计划</div>
		<div style="float: right;" class="full_screen" onclick="getFullSrc('${projectCd}','${centerCd}');">全屏</div>
		<div style="float: right;" class="split_vertial">&nbsp;</div>
		<div style="float: right;" class="exec_plan" onclick=returnMain();>执行计划总表</div>
		<div style="float: right;" class="split_vertial">&nbsp;</div>
		<div style="float: right;" class="project_process" onclick="gotoExecStat('${centerCd}','${projectCd}');">业态进度</div>
		<div style="float: right;" class="split_vertial">&nbsp;</div>
	</div>
	
	<table class="plan_list" cellpadding="0" cellspacing="0" width="100%">
		<tr class="header">
			<th style="width:10%;" class="first_col">序号</th>
			<th style="width:20%;">节点名称</th>
			<th style="width:20%;">主责方</th>
			<th style="width:20%;">项目业态</th>
			<th style="width:20%;">计划完成时间</th>
			<th style="width:10%;background: none;">进度</th>
		</tr>
		<s:iterator value="detailsList" var="details">
		<tr onclick="trOnclick('${projectCd}');">
			<td colspan="6" class="big_drop_down project_row_item">
			  <s:if test="!isExecSum&&execDelaySum==0&&execRecentSum==0"><!--只有前期计划 -->
			  <span>${projectName}</span>前期计划：<span>延期(${preDelaySum})</span><span>近期(${preRecentSum})</span>&nbsp;&nbsp;执行计划未开始
			  </s:if>
			  <s:elseif test="preDelaySum==0&&preRecentSum==0"><!-- 只有项目执行计划 -->
			  <span>${projectName}</span>执行计划：<span>延期(${execDelaySum})</span><span>近期(${execRecentSum })</span>
			  </s:elseif>
			  <s:else><!-- 既有前期计划又有项目执行计划 -->
			   <span>${projectName}</span>前期计划：<span>延期(${preDelaySum})</span><span>近期(${preRecentSum})</span>
			  </s:else>
			</td>
		</tr>

		<%
			int orderNo = 1;
		    boolean haveExecTitleTr=false;
		%>

		<s:iterator value="#details.planDetailVO" var="detail">
		 <s:if test="!isExecSum&&planTypeCd==1&&!(preDelaySum==0&&preRecentSum==0)"><!-- isExecSum:有执行计划，但是在近一个月内没有项目 -->
		    <%if(!haveExecTitleTr){
		    %>
		     <tr class="tr_${projectCd}">
		      <td colspan="6" class="project_row_item">
		             &nbsp;&nbsp;&nbsp;执行计划：<span>延期(${execDelaySum})</span><span>近期(${execRecentSum })</span>
		      </td>
		     </tr>
		    <%
		    }
		    haveExecTitleTr=true;
		    %>
		 </s:if>
			<tr class="tr_${projectCd}" onclick="showPlanDetail('','${projNodeId}','${projPlanCd}','${detailId}');" 
				<s:if test="nowStatus==0">
					style="color:#FF0000;display:'';"
				</s:if>
				<s:else>
					style="color:#161616;display:'';"
				</s:else> 
				style="width: 100%">
				<td class="first_col"><%=orderNo%></td>
				<td>${nodeName}</td>
				<td>${resOrgName}</td>
				<td>${executionPlanName}</td>
				<td><s:date name="scheduleEndDate" format="yyyy-MM-dd" /></td>
				<td><s:if test="nowStatus==0">延期</s:if> <s:elseif test="nowStatus==1">近期</s:elseif></td>
			</tr>
			<%
				orderNo++;
			%>
		</s:iterator>
		</s:iterator>
	</table>
</body>
</html>