<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="title_bar" >
	<div class="title_bar_h big_drop_down project_row_item" id="div_task_project" onclick="divOnClick('project');">项目执行计划</div>
</div>
<div id="div_task_detail_project">
<table class="content_table" cellpadding="0" cellspacing="0" width="100%">
	<tr class="header">
		<th style="width:10%;background: none;" class="first_col" align="left">序号</th>
		<th style="width:20%;" align="left">节点名称</th>
		<th style="width:20%;" align="left">主责方</th>
		<th style="width:20%;" align="left">项目业态</th>
		<th style="width:20%;" align="left">计划完成时间</th>
		<th style="width:10%;" align="left">进度</th>
	</tr>
	<s:iterator value="detailsList" var="details">
	<tr onclick="trOnclickByProj('${projectCd}');">
		<td colspan="6" id="detail_tr_${projectCd}" class="proj_drop_down proj_down_item">
		  <s:if test="!isExecSum&&execDelaySum==0&&execRecentSum==0"><!--只有前期计划 -->
		  <span>&nbsp;&nbsp;${projectName}</span>前期计划：<span>过期(${preDelaySum})</span><span>近期(${preRecentSum})</span>&nbsp;&nbsp;执行计划未开始
		  </s:if>
		  <s:elseif test="preDelaySum==0&&preRecentSum==0"><!-- 只有项目执行计划 -->
		  <span>&nbsp;&nbsp;${projectName}</span>执行计划：<span>过期(${execDelaySum})</span><span>近期(${execRecentSum })</span>
		  </s:elseif>
		  <s:else><!-- 既有前期计划又有项目执行计划 -->
		   <span>&nbsp;&nbsp;${projectName}</span>前期计划：<span>过期(${preDelaySum})</span><span>近期(${preRecentSum})</span>
		  </s:else>
		</td>
	</tr>

	<%
		int orderNo = 1;
	    boolean haveExecTitleTr=false;
	%>

	<s:iterator value="#details.planDetailVO" var="detail">
	 <input type="hidden" id="tr_${detailId}"  value="${detailId}"/>
	 <s:if test="!isExecSum&&planTypeCd==1&&!(preDelaySum==0&&preRecentSum==0)"><!-- isExecSum:有执行计划，但是在近一个月内没有项目 -->
	    <%if(!haveExecTitleTr){
	    %>
	     <tr class="tr_${projectCd}">
	      <td colspan="6" class="project_row_item">
	             &nbsp;&nbsp;&nbsp;执行计划：<span>过期(${execDelaySum})</span><span>近期(${execRecentSum })</span>
	      </td>
	     </tr>
	    <%
	    }
	    haveExecTitleTr=true;
	    %>
	 </s:if>
		<tr class="tr_${projectCd} mainTr" 
			<s:if test="nowStatus==0">
				style="color:#FF0000;display:'';"
			</s:if>
			<s:else>
				style="color:#161616;display:'';"
			</s:else> 
			style="width: 100%">
			<td class="first_col" style="padding-left:15px;"><span><%=orderNo%></span></td>
			<td>${nodeName}</td>
			<td>${resOrgName}</td>
			<td>${executionPlanName}</td>
			<td><s:date name="scheduleEndDate" format="yyyy-MM-dd" /></td>
			<td><s:if test="nowStatus==0">过期</s:if> <s:elseif test="nowStatus==1">近期</s:elseif>
			<s:if test="costCtrlId==null">
			<button onclick="doTranByCostCtrl('${detailId}','${projectCd}','${projectName}','${nodeName}','${executionPlanName}');" class="btn_blue_35_20" id="transmitCostCtrl" type="button">推送</button>
			</s:if>
			</td>
		</tr>
		<%
			orderNo++;
		%>
	</s:iterator>
	</s:iterator>
</table>
</div>