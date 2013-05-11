<#setting number_format="#">
<div id="plansWindowDiv2" style="display:none;">
	<div style="margin:5px;">
		<#list execPlanLayouts as plan>
			<div style="cursor:pointer;	height:26px; line-height:26px; width:100%; font-size:14px; font-weight:bolder; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchPlan_${plan.execPlanLayoutId}" myid="${plan.execPlanLayoutId}" class="js_searchPlan" if_active="1">&nbsp;&nbsp;${plan.executionPlanName}</div>
		</#list>
		<div style="height:26px; width:100%; padding-left:30px;">
			<button class="btn_green" onClick="ifProjectChange=false;switchProject(null,getCurProjectCd());$('#plansWindowDiv').hide();">搜索</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="btn_green" onClick="$('#plansWindowDiv').hide();">关闭</button>
		</div>
	</div>
</div>
<div>目前主力店签约数&nbsp;<span class="color_green" style="font-size:16px;font-weight:bolder;">${nMainSigned}</span>，未签约数&nbsp;<span style="color:red; font-size:16px;font-weight:bolder;">${nMainUnsign}</span>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目前次主力店签约数&nbsp;<span class="color_green" style="font-size:16px;font-weight:bolder;">${nSubSigned}</span>，未签约数&nbsp;<span style="color:red; font-size:16px;font-weight:bolder;">${nSubUnsign}</span></div>
<div id="" style="padding-left:0px; float:left; width:100%;">
	<table id="" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top" id="topLeftTd">
				<div style="overflow: hidden;padding-bottom: 1px solid #aaabb0;background-color: #E4E7EC; width:<#if aShowStandard==true>${searchedPlans.size()*200+620}<#else>${searchedPlans.size()*200+458}</#if>px; height:48px;">
					<table cellpadding="0" cellspacing="0" style="background-color: #E4E7EC;" class="top_left_thead">
						<tr>
							<td style="width:30px;" rowspan="2">序号</td>
							<td style="width:200px" rowspan="2"><#if planTypeCd==10>商业汇总计划</#if><#if planTypeCd==11>商业四级计划</#if></td>
							<#list searchedPlans as searchedPlan>
								<td style="width:80px" rowspan="2">开始时间</td>
								<td style="width:80px" rowspan="2">完成时间</td>
								<td style="width:40px" rowspan="2">状态</td>
							</#list>
							<td style="width:100px" rowspan="2"><div style="text-decoration:underline; cursor:pointer;" class="color_blue" onclick="$('#resOrgNameWindowDiv').toggle();">主责方</div></td>
							<td style="width:120px" colspan="2">地产现状况</td>
							<#if aShowStandard==true><td style="width:160px" colspan="2">标准</td></#if>
						</tr>
						<tr>
							<td style="width:60px">开始时间</td>
							<td style="width:60px">完成时间</td>
							<#if aShowStandard==true><td style="width:80px">开始时间</td>
							<td style="width:80px">完成时间</td></#if>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td valign="top" id="">
				<div id="" style="overflow: hidden;">
					<table id="" cellpadding="0" cellspacing="0" style="border-top: 0 none;" class="top_left_thead bottom_content">
						<#list execPlanAllVoList as planNode>
							<tr style="height:38px;background-color:#eee;">
								<#list searchedPlans as plan>
									<#assign key2 = (planNode.execPlanNodeId+"_"+plan.execPlanLayoutId)>
									<#assign planDetail = planDetailMap[key2]>
									<#assign printHtml = mapPrintHtml[key2]>
									<#assign printStartDate = mapPrintStartDate[key2]>
									<#assign printEndDate = mapPrintEndDate[key2]>
									<#assign key = planNode.execPlanNodeId>
									<#assign parentNodeVo = parentNodeVoMap[key]>
									<#if planDetail!=""><#assign tip = mapTips[planDetail.execPlanDetailId]><#else><#assign tip=""></#if>
									<#if parentNodeVo!=""><#assign tip2 = mapTips2[parentNodeVo.execPlanDetailId]><#else><#assign tip2=""></#if>
								<td style="width:30px;" align="center"><#if (planNode.sequenceNo>0)>${planNode.sequenceNo}<#else>&nbsp;</#if></td>
								<td style="width:200px; overflow:hidden; text-align:left;" title="${planNode.nodeName14}">
									<div class="nodeNameDiv" style="<#if parentNodeVo!="">font-weight:bolder; </#if>overflow:hidden; text-overflow:ellipsis; max-width:80%; max-height:36px;">${planNode.nodeName14}</div>
								</td>
									<td width="80px" planInfo="${key2}"
										<#if (planDetail!="" || aAddPoint)>
											onclick="showPlanDetail($(this),'${planNode.execPlanNodeId}','${plan.execPlanLayoutId}');" 
											id="${key2}_data1_td" <#if planDetail != "">label="${planDetail.execPlanDetailId}";<#else>label="";</#if>
										</#if>
										style="<#if (planDetail!="" || aAddPoint)>cursor:pointer;</#if>" <#if planDetail!="">class="pd-chill-tip" title="${tip}"</#if>
									><#if printStartDate!="">${printStartDate}</#if></td>
									<td width="80px" planInfo="${key2}"
										<#if (planDetail!="" || aAddPoint)>
											onclick="showPlanDetail($(this),'${planNode.execPlanNodeId}','${plan.execPlanLayoutId}');" 
											id="${key2}_data2_td" <#if planDetail != "">label="${planDetail.execPlanDetailId}";<#else>label="";</#if>
										</#if>
										style="<#if (planDetail!="" || aAddPoint)>cursor:pointer;</#if>" <#if planDetail!="">class="pd-chill-tip" title="${tip}"</#if>
									><#if printEndDate!="">${printEndDate}</#if></td>
									<td width="40px" planInfo="${key2}"
										<#if (planDetail!="" || aAddPoint)>
											onclick="showPlanDetail($(this),'${planNode.execPlanNodeId}','${plan.execPlanLayoutId}');" 
											id="${key2}_data3_td" <#if planDetail != "">label="${planDetail.execPlanDetailId}";<#else>label="";</#if>
										</#if>
										style="<#if (planDetail!="" || aAddPoint)>cursor:pointer;</#if>" <#if planDetail!="">class="pd-chill-tip" title="${tip}"</#if>
									><#if printHtml!="">${printHtml}</#if></td>
								</#list>
								<td style="width:100px;">${planNode.resOrgName}</td>
								<td width="60px" planInfo="${key}" 
									<#if (parentNodeVo!=""&&parentNodeVo.execPlanDetailId!="")>onclick="showPlanDetail($(this),null,null,'${parentNodeVo.execPlanDetailId}');" 
											id="${key}_data_td" style="cursor:pointer" <#if parentNodeVo != "">label="${parentNodeVo.execPlanDetailId}" class="pd-chill-tip" title="${tip2}"<#else>label=""</#if></#if>><#if (parentNodeVo!=""&&parentNodeVo.startDate!=null)>${parentNodeVo.startDate}</#if></td>
								<td width="60px" planInfo="${key}" 
									<#if (parentNodeVo!=""&&parentNodeVo.execPlanDetailId!="")>onclick="showPlanDetail($(this),null,null,'${parentNodeVo.execPlanDetailId}');" 
											id="${key}_data_td" style="cursor:pointer" <#if parentNodeVo != "">label="${parentNodeVo.execPlanDetailId}" class="pd-chill-tip" title="${tip2}"<#else>label=""</#if></#if>><#if (parentNodeVo!=""&&parentNodeVo.endDate!=null)>${parentNodeVo.endDate}</#if></td>
								<#if aShowStandard==true><td title="${planNode.standardStartDate}" style="width:80px;"><div style="height:38px; overflow:hidden;">${planNode.standardStartDate}</div></td>
								<td title="${planNode.standardEndDate}" style="width:80px;"><div style="height:38px; overflow:hidden;">${planNode.standardEndDate}</div></td></#if>
							</tr>
						</#list>
					</table>
				</div>
			</td>
		</tr>
	</table>
</div>

<script type="text/javascript">
	$(function(){
		isLoading = false;
		searchPlans = "${searchPlans}";
		$("#plansWindowDiv").html($("#plansWindowDiv2").html());
		$("#plansWindowDiv2").html("");
		ifProjectChange = "${ifProjectChange}";
		if("true"==ifProjectChange){
			selectAllPlans();
		}else{
			initSearchPlans();
		}
	});
</script>