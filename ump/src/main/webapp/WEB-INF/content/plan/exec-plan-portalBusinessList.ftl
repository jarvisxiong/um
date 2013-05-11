
<#list viewPlanNodes as planNode>
	<#assign key = planNode.execPlanNodeId>
	<tr class="item" style="height:38px;background-color:#eee;">
		<td align="center">
			<div class="sequenceNoLevel1">${planNode.sequenceNo}</div>
		</td>
		<td style="overflow:hidden; text-align:left;" title="${projectName}">
			<div class="nodeNameDiv" style="overflow:hidden; text-overflow:ellipsis; max-width:80%; max-height:36px;">${projectName}</div>
		</td>		
		<td style="width:200px; overflow:hidden; text-align:left;" title="${planNode.nodeName}">
			<div class="nodeNameDiv" style="overflow:hidden; text-overflow:ellipsis; max-width:80%; max-height:36px;">${planNode.nodeName}</div>
		</td>
		<td style="width:100px;">${planNode.resOrgName}</td>

		<#list searchedPlans as plan>
			<#assign key2 = (planNode.execPlanNodeId+"_"+plan.execPlanLayoutId)>
			<#assign planDetail = planDetailMap[key2]>
			<#assign printStartDate = mapPrintStartDate[key2]>
			<#assign printEndDate = mapPrintEndDate[key2]>
			<#assign printHtml = mapPrintHtml[key2]>
			<#if planDetail!=""><#assign tip = mapTips[planDetail.execPlanDetailId]><#else><#assign tip=""></#if>
			<#if parentNodeVo != ""><#assign tip2 = mapTips2[parentNodeVo.execPlanDetailId]><#else><#assign tip2=""></#if>

		<td width="80px" planInfo="${key2}"
			<#if (planDetail!="" || aAddPoint)>
				onclick="showPlanDetail($(this),'${planNode.execPlanNodeId}','${plan.execPlanLayoutId}');" 
				id="${key2}_data1_td"
				<#if planDetail != "">label="${planDetail.execPlanDetailId}";<#else>label="";</#if>
			</#if>
				style="<#if (planDetail!="" || aAddPoint)>cursor:pointer;</#if>" 
			<#if planDetail!="">class="pd-chill-tip" title="${tip}"</#if>>
				<#if planDetail!=""&&planDetail.scheduleStartDate!="">${printStartDate}</#if>
		</td>
	
		<td width="80px" planInfo="${key2}"
			<#if (planDetail!="" || aAddPoint)>
				onclick="showPlanDetail($(this),'${planNode.execPlanNodeId}','${plan.execPlanLayoutId}');" 
				id="${key2}_data2_td" 
				<#if planDetail != "">label="${planDetail.execPlanDetailId}";<#else>label="";</#if>
			</#if>
				style="<#if (planDetail!="" || aAddPoint)>cursor:pointer;</#if>" 
			<#if planDetail!="">class="pd-chill-tip" title="${tip}"</#if>>
					<#if planDetail!=""&&planDetail.scheduleEndDate!="">${printEndDate}</#if>
		</td>

		<td width="40px" planInfo="${key2}"
			<#if (planDetail!="" || aAddPoint)>
				onclick="showPlanDetail($(this),'${planNode.execPlanNodeId}','${plan.execPlanLayoutId}');" 
				id="${key2}_data3_td" 
				<#if planDetail != "">label="${planDetail.execPlanDetailId}";<#else>label="";</#if>
			</#if>
			style="<#if (planDetail!="" || aAddPoint)>cursor:pointer;</#if>" 
			<#if planDetail!="">class="pd-chill-tip" title="${tip}"</#if>>
				<#if printHtml!="">${printHtml}</#if>
		</td>		
	</#list>

	<#assign parentNodeVo = parentNodeVoMap[key]>
	<td width="60px" planInfo="${key}" 
		<#if (parentNodeVo!=""&&parentNodeVo.execPlanDetailId!="")>
			onclick="showPlanDetail($(this),null,null,'${parentNodeVo.execPlanDetailId}');" 
			id="${key}_data_td" 
			style="cursor:pointer" 
			<#if parentNodeVo != "">
				label="${parentNodeVo.execPlanDetailId}" 
				class="pd-chill-tip" title="${tip2}"
			<#else>
				label=""
			</#if>
		</#if>>
		<#if (parentNodeVo!=""&&parentNodeVo.endDate!=null)>${parentNodeVo.endDate}</#if>
	</td>
</tr>
</#list>

	
	