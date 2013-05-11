<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>


<%@page import="java.util.Date"%>
<%@page import="com.hhz.core.utils.DateOperator"%><div>
	<table style="table-layout: fixed; width: 900px;" cellpadding="0" cellspacing="0">
		<tr>
			<td <s:if test="plans.size == 0">width="300"</s:if><s:else>width="283"</s:else>>
				<div style="height: 60px; width: 283px; overflow: hidden; border-bottom: 2px solid #000;">
					<table cellpadding="0" cellspacing="0" style="background-color: #d6d6d6;" class="top_left_thead">
						<tr>
							<td rowspan="2" width="30" height="60">序号</td>
							<td colspan="2" width="250" height="20" align="center">任务分解节点</td>
						</tr>
						<tr>
							<td height="40" width="150">节点名称</td>
							<td height="40" width="100">责任中心 </td>
						</tr>
					</table>
				</div>
			</td>
			<td id="topRightTd" <s:if test="plans.size == 0">width="0"</s:if><s:else>width="617"</s:else>>
				<c:choose>
					<c:when test="${planMatrixWidth < 617}">
						<div style="height: 60px; width: ${planMatrixWidth.toString()}px; border-bottom: 2px solid #000; overflow: hidden; background-color: #d6d6d6;">
					</c:when>
					<c:otherwise>
						<div style="height: 60px; width: 583px; border-bottom: 2px solid #000; border-right: 1px solid #000; overflow: hidden; background-color: #d6d6d6;">
					</c:otherwise>
				</c:choose>
				
					<table id="topRightTitle" width="${planMatrixWidth.toString()}" cellpadding="0" cellspacing="0" class="top_left_thead" style="border-left: 0 none;">
						<tr>
							<td colspan="<s:property value="plans.size * 3"/>" height="20">控制计划配置</td>
						</tr>
						<tr>
							<s:iterator value="plans">
								<td colspan="3" width="230" height="20">${executionPlanName}</td>
							</s:iterator>
						</tr>
						<tr>
							<s:iterator value="plans">
								<td width="80" height="20">开始时间</td>
								<td width="80" height="20">完成时间 </td>
								<td width="70" height="20">状态</td>
							</s:iterator>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		
		<tr>
			<td valign="top">
				<div id="bottomLeftDiv" <s:if test="plans.size == 0">style="height: 350px; width: 300px; overflow: auto;"</s:if><s:else>style="height: 350px; width: 283px; overflow: hidden;"</s:else>>
					<table id="bottomLeftTitle" cellpadding="0" cellspacing="0" style="border-top: 0 none;" class="top_left_thead bottom_content">
						<s:iterator value="planNodes" status="stat">
							<tr <s:if test="#stat.even">style="background-color: #eeeeee;"</s:if>>
								<td width="30"><s:property value="#stat.index + 1" /></td>
								<td width="150">${nodeName}</td>
								<td width="100">${resCenterOrg}</td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</td>
			
			<td valign="top">
				<c:choose>
					<c:when test="${planMatrixWidth < 617}">
						<div id="bottomRightDiv" style="height: 350px; width: ${(planMatrixWidth + 17).toString()}px; overflow: auto;" onscroll="resetLayout($(this));">
					</c:when>
					<c:otherwise>
						<div id="bottomRightDiv" style="height: 350px; width: 600px; overflow: auto;" onscroll="resetLayout($(this));">
					</c:otherwise>
				</c:choose>
				<c:set var="curDate"><%=DateOperator.formatDate(new Date(), "yyyy-MM-dd")%></c:set>
					<table cellpadding="0" cellspacing="0" class="top_left_thead bottom_content" width="${planMatrixWidth.toString()}" style="border-left: 0 none; border-top: 0 none;">
						<s:iterator var="node" value="planNodes" status="nodeStatus">
						<tr>
							<s:iterator var="plan" value="plans" status="planStatus">
								<s:set name="key" value="planProjectNodeRelId + '_' + executionPlanCd"></s:set>
								<s:set name="planDetail" value="planDetailMap[#key]"></s:set>
								<s:set name="planDetailId" value="#planDetail.planExecPlanDetailId"></s:set>
								<s:set name="planDetailStatus" value="#planDetail.status"></s:set>
								
								<td width="80" 
									onmousemove="hoverPlan($(this));" 
									onmouseout="leavePlan($(this));" 
									planInfo="<s:property value="#key"/>"
									<s:if test="#nodeStatus.even && #planStatus.odd">bgcolor="#dbebee"</s:if>
									<s:elseif test="#nodeStatus.even && #planStatus.even">bgcolor="#eeeeee"</s:elseif>
									
									<s:if test="#planDetailId != null || superAdmin">
										style="cursor: pointer;" 
										onclick="showPlanDetail($(this), '${node.planProjectNodeRelId}', '${plan.executionPlanCd}');" 
										id="<s:property value="#key" />StDateTd"
										label="<s:property value="#planDetailId" />";
									</s:if>
								>
									<s:if test="#planDetail.scheduleStartDate != null">
										<c:set var="scheduleStDate"><s:property value="#planDetail.scheduleStartDate" /></c:set>
										<p id="<s:property value="#key" />ScheStDateSpan"
											<c:if test="${scheduleStDate < curDate}">style="color: red;"</c:if>
											<c:if test="${scheduleStDate >= curDate}">style="color: green;"</c:if>
										>${scheduleStDate}</p>
									</s:if>
									<s:else>
										<p id="<s:property value="#key" />ScheStDateSpan">&nbsp;</p>
									</s:else>
									
									<p id="<s:property value="#key" />RealStDateSpan"><s:property value="#planDetail.realStartDate" /></p>
								</td>
								<td width="80" 
									onmousemove="hoverPlan($(this));" 
									onmouseout="leavePlan($(this));" 
									planInfo="<s:property value="#key"/>"
									<s:if test="#nodeStatus.even && #planStatus.odd">bgcolor="#dbebee"</s:if>
									<s:elseif test="#nodeStatus.even && #planStatus.even">bgcolor="#eeeeee"</s:elseif>
									
									<s:if test="#planDetailId != null || superAdmin">
										style="cursor: pointer;" 
										onclick="showPlanDetail($(this), '${node.planProjectNodeRelId}', '${plan.executionPlanCd}');" 
										id="<s:property value="#key" />EdDateTd"
										label="<s:property value="#planDetailId" />";
									</s:if>
								>
									
									<s:if test="#planDetail.scheduleEndDate != null">
										<c:set var="scheduleEdDate"><s:property value="#planDetail.scheduleEndDate" /></c:set>
										
										<p id="<s:property value="#key" />ScheEdDateSpan"
											<c:if test="${scheduleEdDate < curDate}">style="color: red;"</c:if>
											<c:if test="${scheduleEdDate >= curDate}">style="color: green;"</c:if>
										>${scheduleEdDate}</p>
									</s:if>
									<s:else>
										<p id="<s:property value="#key" />ScheEdDateSpan"></p>
									</s:else>
									
									<p id="<s:property value="#key" />RealEdDateSpan"><s:property value="#planDetail.realEndDate" /></p>
								</td>
								<td width="70" 
									onmousemove="hoverPlan($(this));" 
									onmouseout="leavePlan($(this));" 
									planInfo="<s:property value="#key"/>"
									<s:if test="#nodeStatus.even && #planStatus.odd">bgcolor="#dbebee"</s:if>
									<s:elseif test="#nodeStatus.even && #planStatus.even">bgcolor="#eeeeee"</s:elseif>
									
									<s:if test="#planDetailId != null || superAdmin"> 
										style="cursor: pointer;"
										onclick="showPlanDetail($(this), '${node.planProjectNodeRelId}', '${plan.executionPlanCd}');" 
										id="<s:property value="#key" />StatusTd"
										label="<s:property value="#planDetailId" />";
									</s:if>
								>
									<s:if test="#planStatus == null">&nbsp;</s:if>
									<s:elseif test="#planDetailStatus == 0"><img src="${ctx}/images/planold/pic_noConfirm.gif" title="新建" /></s:elseif>
									<s:elseif test="#planDetailStatus == 1"><img src="${ctx}/images/planold/pic_confirm.gif" title="进行中" /></s:elseif>
									<s:elseif test="#planDetailStatus == 2"><img src="${ctx}/images/planold/pic_preFinish.gif" title="预完成" /></s:elseif>
									<s:elseif test="#planDetailStatus == 3"><img src="${ctx}/images/planold/pic_finish.gif" title="已完成" /></s:elseif>
								</td>
							</s:iterator>
						</tr>
						</s:iterator>
					</table>
				</div>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
	setExecPlanMainId('${planExecutionPlanMain.planExecutionPlanMainId}');
	isLoading = false;
</script> 