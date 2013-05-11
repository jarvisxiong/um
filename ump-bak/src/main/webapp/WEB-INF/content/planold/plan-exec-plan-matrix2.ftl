<#setting number_format="#">
<div id="mainPlanMatrixDiv" style="padding-left:0px; float:left;">
	<table id="mainMatrix" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top" id="topLeftTd">
				<div style="overflow: hidden;padding-bottom: 1px solid #aaabb0;background-color: #E4E7EC; width:218px;">
					<table cellpadding="0" cellspacing="0" style="background-color: #E4E7EC;" class="top_left_thead">
						<tr>
							<td rowspan="2" style="width:35px;">
								<#if (planTypeCd == 0 && planType == 'control')>
									<a href="javascript: switchProject(null, getCurProjectCd())">所有</a>
								</#if>
								<#if (planTypeCd == 1 && planType == 'control')>
									<a href="javascript: switchProject(null, getCurProjectCd())">所有</a>
								</#if>
								序号
							</td>
							<td colspan="2" style="width:180px;height:24px;text-align:center;">
								项目
								<#if (planTypeCd == 0)>
									前期计划
								</#if>
								<#if (planTypeCd == 1)>
									<#if (planType == 'control')>
										控制计划
									<#else>
										执行计划
									</#if>
								</#if>
								<#if (planTypeCd == 2)>
									成本计划
								</#if>
								节点
							</td>
						</tr>
						<tr style="height:50px;">
							<td style="width:100px">节点名称</td>
							<td style="width:80px">主责方 </td>
						</tr>
					</table>
				</div>
			</td>
			<td valign="top" id="topRightTd" style="background-color: #E4E7EC;">
				<div id="topRightTdContainer" style="float:left;border-bottom: 0px solid #aaabb0; overflow: hidden;">
					<#if (plans.size()>0)>
					<table id="topRightTitle" cellpadding="0" cellspacing="0" class="top_left_thead" style="border-left: 0 none;width:${(plans.size() * 202)}px;">
						<tr>
							<td style="height:24px;" colspan="${plans.size() * 2}">项目业态</td>
						</tr>
						<tr>
							<#list plans as plan>
								<td style="height:24px;" colspan="2">${plan.executionPlanName}</td>
							</#list>
						</tr>
						<tr>
							<#list plans as plan>
								<td style="width:80px;height:24px">计划时间</td>
								<td style="width:120px;height:24px">当前状态</td>
							</#list>
						</tr>
					</table>
					<#else>
						<!--没有业态-->
					</#if>
				</div>
				<div id="alignWidthSpanDiv" style="width:17px;float:right;">&nbsp</div>
			</td>
		</tr>
		
		<tr>
			<td valign="top" id="dataLeftPanelTd">
				<div id="bottomLeftDiv" style="overflow: hidden;">
					<table id="bottomLeftTitle" cellpadding="0" cellspacing="0" style="border-top: 0 none;" class="top_left_thead bottom_content">
						<#list planNodes as planNode>
							<tr>
								<td style="width:35px;">${planNode_index + 1}</td>
								<td style="width:100px;">
									<div class="pd-chill-tip" style="width: 95%; height: 18px;padding-left:3px; line-height: 18px; overflow: hidden; text-align: left;" title="${planNode.nodeName}">
										${planNode.nodeName}
									</div>
								</td>
								<td style="width:80px;">
									<div class="pd-chill-tip" style="width: 95%; height: 18px;padding-left:3px; line-height: 18px; overflow: hidden; text-align: left;" title="${planNode.resOrgName}">
										${planNode.resOrgName}
									</div>
								</td>
							</tr>
						</#list>
					</table>
				</div>
			</td>
			
			<td valign="top" id="dataRightPanelTd">
				<div id="bottomRightDiv"  onscroll="resetLayout($(this));" style="overflow: auto;">
					<#if (plans.size()>0)>
					<table id="bottomRightTd" cellpadding="0" cellspacing="0" class="top_left_thead bottom_content"  style="border-left: 0 none; border-top: 0 none;width:${plans.size() * 202}px;">
						<#if (planNodes.size() ==0)>
						<!--
						<tr id="noDataWidthDiv">
							<td></td>
						</tr>
						-->
						</#if>
						
						<#list planNodes as planNode>
						<tr>
							<#list plans as plan>
								<#assign key = (planNode.planProjectNodeRelId + "_" + plan.executionPlanCd)>
								<#assign planDetail = planDetailMap[key]>
								<td width="80px"
									onmousemove="hoverPlan($(this));"
									onmouseout="leavePlan($(this));"
									planInfo="${key}"
									<#if (planDetail != "" || infoInputor)>
										style="cursor: pointer;" 
										onclick="showPlanDetail($(this), '${planNode.planProjectNodeRelId}', '${plan.executionPlanCd}');" 
										id="${key}ScheEdDateTd"
										<#if planDetail != "">
											label="${planDetail.planExecPlanDetailId}";
										<#else>
											label="";
										</#if>
									</#if>
								>
									<p id="${key}ScheEdDateSpan">
										<#if (planDetail != "" && planDetail.scheduleEndDate!=null)>
											${planDetail.scheduleEndDate?string("yyyy-MM-dd")}
										<#else>
											&nbsp;
										</#if>
									</p>
								</td>
								<td width="120px"
									onmousemove="hoverPlan($(this));"
									onmouseout="leavePlan($(this));"
									planInfo="${key}"
									<#if (planDetail != "" || infoInputor)>
										style="cursor: pointer;" 
										onclick="showPlanDetail($(this), '${planNode.planProjectNodeRelId}', '${plan.executionPlanCd}');" 
										id="${key}StatusTd"
										<#if planDetail != "">
											label="${planDetail.planExecPlanDetailId}";
										<#else>
											label="";
										</#if>
									</#if>>
									
									<#if planDetail != "">
										<#if (planDetail.status == "0" && planDetail.infoConfirmedFlg=="1")>
											<#if (planDetail.scheduleEndDate!=null && planDetail.realEndDate == null)&&((planDetail.scheduleEndDate?date) < (currentDate?date))>
												<img src="${request.contextPath}/resources/images/plan/pic_delay.gif" title="延期" />
											<#else>
												<img src="${request.contextPath}/resources/images/plan/pic_confirm.gif" title="确认" />
											</#if>
										<#else>
											<#if (planDetail.status == "0" &&(planDetail.infoConfirmedFlg!="" )&&(planDetail.infoConfirmedFlg=="0"))><img src="${request.contextPath}/resources/images/plan/pic_noConfirm.gif" title="未确认" />
											<#elseif (planDetail.status == "0" &&(planDetail.infoConfirmedFlg!="" )&&(planDetail.infoConfirmedFlg=="1"))><img src="${request.contextPath}/resources/images/plan/pic_confirm.gif" title="确认" />
											<#elseif (planDetail.status == "1")>
												<#if (planDetail.realEndDate!="" && planDetail.realEndDate != null)>
													${planDetail.realEndDate?string("yyyy-MM-dd")}
												</#if>
												<img src="${request.contextPath}/resources/images/plan/pic_preFinish.gif" title="预完成" />
											<#elseif (planDetail.status == "2")>
												<#if (planDetail.realEndDate!="" && planDetail.realEndDate != null)>
													${planDetail.realEndDate?string("yyyy-MM-dd")}
												</#if>
												<img src="${request.contextPath}/resources/images/plan/pic_finish.gif" title="完成" />
											</#if>
										</#if>
									</#if>
								</td>
							</#list>
						</tr>
						</#list>
					</table>
					<#else>
						<div style="width:18px;" id="noOperationDiv">&nbsp;</div>
						<script type="text/javascript">
							$("#noOperationDiv").height($("#bottomLeftTitle").height());
						</script>
					</#if>
				</div>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
	setExecPlanMainId('${planExecutionPlanMain.planExecutionPlanMainId}');
	isLoading = false;	
</script>