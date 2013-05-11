<#setting number_format="#">
<div id="plansWindowDiv2" style="display:none;">
	<div style="margin:5px;">
		<#list execPlanLayouts as plan>
			<div style="cursor:pointer;	height:26px; overflow:hidden; line-height:26px; width:100%; font-size:14px; font-weight:bolder; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchPlan_${plan.execPlanLayoutId}" myid="${plan.execPlanLayoutId}" class="js_searchPlan" if_active="1" title="${plan.executionPlanName}">&nbsp;&nbsp;${plan.executionPlanName}</div>
		</#list>
		<div style="height:26px; width:100%; padding-left:30px;">
			<input type="button" class="button_blue" onClick="ifProjectChange=false;switchProject(null,getCurProjectCd());$('#plansWindowDiv').hide();" value="搜索"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" class="button_red" onClick="$('#plansWindowDiv').hide();" value="取消"/>
		</div>
	</div>
</div>
<div id="mainPlanMatrixDiv" style="padding-left:0px; float:left; margin-left:0px;">
	<#if execPlanLayouts.size()!=0>
	<table id="mainMatrix" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top" id="topLeftTd">
				<div style="overflow: hidden;padding-bottom: 1px solid #aaabb0;background-color: #E4E7EC; width:333px;">
					<table cellpadding="0" cellspacing="0" style="background-color: #E4E7EC;" class="top_left_thead">
						<tr>
							<td style="width:30px;">序号</td>
							<td style="width:200px">项目开发计划节点</td>
							<td style="width:100px"><div style="text-decoration:underline; cursor:pointer;" class="color_blue" onclick="$('#resOrgNameWindowDiv').toggle();">主责方</div></td>
						</tr>
					</table>
				</div>
			</td>
			<td valign="top" id="topRightTd" style="background-color: #E4E7EC;">
				<div id="topRightTdContainer" style="float:left;border-bottom: 0px solid #aaabb0; overflow: hidden;">
					<table id="topRightTitle" cellpadding="0" cellspacing="0" class="table_list top_left_thead" style="border-left: 0 none;width:${(searchedPlans.size() * 160)}px;">
						<tr>
							<#list searchedPlans as searchedPlan>
								<td style="height:24px; width:160px;" title="${searchedPlan.executionPlanName}"><div style="height:24px; width:158px; overflow:hidden;">${searchedPlan.executionPlanName}</div></td>
							</#list>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td valign="top" id="dataLeftPanelTd">
				<div id="bottomLeftDiv" style="overflow: hidden;">
					<table id="bottomLeftTitle" cellpadding="0" cellspacing="0" style="border-top: 0 none;" class="top_left_thead bottom_content">
						<#list viewPlanNodes as planNode>
						<#assign key = planNode.planProjectNodeRelId>
						<#assign parentRel = parentNodeMap[key]>
							<#if parentRel!=null>
							<tr style="height:38px;background-color:#eee;">
								<td style="width:30px;" align="center"><div <#if parentRel.pointLevel==1>class="sequenceNoLevel1"</#if>>${parentRel.sequenceNo}</div></td>
								<td style="width:200px; overflow:hidden; text-align:left;" title="${parentRel.nodeName}">
									<#if parentRel.pointLevel==1||parentRel.pointLevel==2><div class="pic_guide" title="点击下载计划指引" onClick="getGuideFile(${parentRel.sequenceNo},'${parentRel.nodeName}')"></div></#if>
									<div class="pic_standard" title="点击下载完成标准" style="display:none;"></div>
									<div class="nodeNameDiv" style="overflow:hidden; text-overflow:ellipsis; max-width:80%; max-height:36px;">${parentRel.nodeName}</div>
								</td>
								<td style="width:100px;">${parentRel.resOrgName}</td>
							</tr>
							</#if>
							<#if parentRel==null>
							<tr style="height:38px;background-color:#eee;">
								<td style="width:30px;" align="center"><div <#if planNode.pointLevel==1>class="sequenceNoLevel1"</#if>>${planNode.sequenceNo}</div></td>
								<td style="width:200px; overflow:hidden; text-align:left;" title="${planNode.nodeName}">
									<#if planNode.pointLevel==1||planNode.pointLevel==2><div class="pic_guide" title="点击下载计划指引" onClick="getGuideFile(${planNode.sequenceNo},'${planNode.nodeName}')"></div></#if>
									<div class="pic_standard" title="点击下载完成标准" style="display:none;"></div>
									<div class="nodeNameDiv" style="overflow:hidden; text-overflow:ellipsis; max-width:80%; max-height:36px;">${planNode.nodeName}</div>
								</td>
								<td style="width:100px;">${planNode.resOrgName}</td>
							</tr>
						</#if>
						</#list>
					</table>
				</div>
			</td>
			
			<td valign="top" id="dataRightPanelTd" style="font-size:14px;">
				<div id="bottomRightDiv"  onscroll="resetLayout($(this));" style="overflow: auto;">
					<#if (searchedPlans.size()>0)>
					<table id="bottomRightTd" cellpadding="0" cellspacing="0" class="table_list bottom_content"  style="border-left: 0 none; border-top: 0 none;width:${searchedPlans.size() * 160}px;">
						<#list viewPlanNodes as planNode>
						<tr class="item" style="height:38px;">
							<#list searchedPlans as plan>
								<#assign key = (planNode.execPlanNodeId+"_"+plan.execPlanLayoutId)>
								<#assign planDetail = planDetailMap[key]>
								<#assign printHtml = mapPrintHtml[key]>
								<#if planDetail!=""><#assign tip = mapTips[planDetail.execPlanDetailId]></#if>
								<td width="160px" planInfo="${key}"
									<#if (planDetail!="" || aAddPoint)>
										onclick="showPlanDetail($(this),'${planNode.execPlanNodeId}','${plan.execPlanLayoutId}');" 
										id="${key}_data_td" <#if planDetail != "">label="${planDetail.execPlanDetailId}";<#else>label="";</#if>
									</#if>
									style="<#if (planDetail!="" || aAddPoint)>cursor:pointer;</#if>" <#if planDetail!="">class="pd-chill-tip" title="${tip}" </#if>
								>${printHtml}</td>
							</#list>
						</tr>
						</#list>
					</table>
					<#else>
						<div style="width:300px; font-size:28; font-weight:bolder; padding-left:50px; padding-top:100px;" id="noOperationDiv">没有搜索到的业态</div>
						<script type="text/javascript">
							$("#noOperationDiv").height($("#bottomLeftTitle").height());
						</script>
					</#if>
				</div>
			</td>
		</tr>
	</table>
	<#else>
	<table id="" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top" id="">
				&nbsp;请在下拉框中选择要查看的项目！
			</td>
		</tr>
	</table>
	</#if>
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