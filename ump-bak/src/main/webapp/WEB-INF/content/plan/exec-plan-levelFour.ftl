<br/>
<br/>
<br/>
<table class="content_table " >
<thead>
	<tr>
		<th width="30px" style="background:none;" >序号</th>
		<th width="100px">项目名称</th>
		<th width="200px">商业四级计划节点名称</th>
		<th width="150px">主责方</th>
		<th width="80px">开始时间</th>
		<th width="80px">完成时间</th>
		<th width="80px">状态</th>
	</tr>
</thead>
<tbody>
<#if levelFourMap.keySet().size()==0>
<tr style='height:120px; background-color: #ffffff;' >
<td colspan='7' align="center">没有搜索到的记录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<tr>
<#else>
	<#list levelFourMap.keySet() as key>
		<#assign name = levelFourMap.get(key).get("name")>
		<#list levelFourMap.get(key).get("details") as detail>
			<tr class="showPlanDetail pd-chill-tip mainTr" style="cursor:pointer;" title="${mapTips.get(detail.execPlanDetailId)}"
				planId="${detail.execPlanNode.execPlanNodeId}"
				layoutId="${detail.execPlanLayout.execPlanLayoutId}"
				detailId="${detail.execPlanDetailId}"
				projectId="${key}">
				<td align="center" width="30px"><div class="sequenceNoLevel1">${detail.execPlanNode.sequenceNo}</div></td>
				<td title="${name}">${name}</td>
				<td title="${detail.execPlanNode.nodeName}">${detail.execPlanNode.nodeName}</td>
				<td title="${detail.execPlanNode.resOrgName}">${detail.execPlanNode.resOrgName}</td>
				<td id="${detail.execPlanDetailId}_startDate" align="center">${mapPrintStartDate.get(detail.execPlanDetailId)}</td>
				<td id="${detail.execPlanDetailId}_endDate" align="center">${mapPrintEndDate.get(detail.execPlanDetailId)}</td>
				<td id="${detail.execPlanDetailId}_status" align="center">${mapPrintHtml.get(detail.execPlanDetailId)}</td>
			</tr>
		</#list>
	</#list>
</#if>
</tbody>
</table>
<script type="text/javascript">
var projectId="";
jQuery(function($) {
	$(".showPlanDetail").click(function(){
		projectId=$(this).attr("projectId");
		var url=_PATH + "/plan/exec-plan!planDetailInput.action";
		var paramData={
			projectCd: $(this).attr("projectId"),
			planDetailId :$(this).attr("detailId"),
			nodeId	 : $(this).attr("planId"),
			layoutId : $(this).attr("layoutId"),
			planTypeCd : 24
		};
		ymPrompt.win({
			icoCls:"",
			title:"节点详情",
			message:"<div id='planDetailDiv'><img align='absMiddle' src='" + _PATH + "/resources/images/common/loading.gif'></div>",
			useSlide:false,
			width:800,
			autoClose: false,
			height:500,
			maxBtn: false,
			allowRightMenu:true,
			allowSelect:true,
			handler:savePlanNodeDetail,
			closeBtn: false,
			btn:[['确定','ok'], ['退出', 'cancel']],
			afterShow:function(){
				$.post(url,paramData,function(result){
					$("#planDetailDiv").html(result);
				});
			}
		});
	});
	
	
});

function savePlanNodeDetail(tp) {
	if (tp == 'cancel') {
		ymPrompt.close();
		return;
	}
	var curRole = $("#curRoleHid").val();
	if (curRole == "viewer") {
		ymPrompt.close();
		return;
	}

	try{
		var checkedValue = "";
		for (var i = 0; i < document.planDetailForm.activeBl1.length;i++){
			if (document.planDetailForm.activeBl1[i].checked == true)
				checkedValue = document.planDetailForm.activeBl1[i].value;
		}
		$("#activeBl").val(checkedValue);
	}catch(e){}
	
	$("#form_newMessage").val($("#newMessage").val());
	
	var saveParam={
			planDetailId	:	$("#planDetailIdHid").val(),
			projectCd		:	projectId,
			nodeId			:	$("#nodeId").val(),
			layoutId		:	$("#layoutId").val()
	}
	$("#planDetailForm").ajaxSubmit(function(result) {
		$.post(_PATH + "/plan/exec-plan!fetchPlanDetailInfo.action", saveParam,function (result) {
			if ("failure"!=result && saveParam.planDetailId===result.split("*")[1]) {
				$("td[id='" + saveParam.planDetailId + "_startDate']").html(result.split("*")[2]);
				$("td[id='" + saveParam.planDetailId + "_endDate']").html(result.split("*")[3]);
				$("td[id='" + saveParam.planDetailId + "_status']").html(result.split("*")[0]);
				ymPrompt.close();
			}
		});
	});
}

</script>