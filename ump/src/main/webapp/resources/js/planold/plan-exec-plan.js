﻿﻿var _PATH = _ctx;
var scrollWidth = 100;
var isScrolling = false;
var curOpenedNodeCompDateId = null;
var projReminders = [];
var nodeReminders = [];
var curNodeRelId = null;
var isLoading = false;

//设置宽度/高度
function setCurDocWidth(docWidth){
	$("#curDocWidth").val($(document).width());
}
function getCurDocWidth(){
	return $("#curDocWidth").val();
}
function setCurDocHeight(docHeight){
	$("#curDocHeight").val($(document).height());
}
function getCurDocHeight(){
	return $("#curDocHeight").val();
}
// 获取/设置当前项目CD
function getCurProjectCd(){
	return $("#curProjectCd").val();
}
function setCurProjectCd(projectCd){
	$("#curProjectCd").val(projectCd);
}
//执行计划ID
function getExecPlanMainId(){
	return $("#curExecPlanMainId").val();
}
function setExecPlanMainId(curExecPlanMainId){
	$("#curExecPlanMainId").val(curExecPlanMainId);
}
//计划类型:1-执行计划 0-前期计划
function getPlanTypeCd(){
	return $("#curPlanTypeCd").val();
}
function setPlanTypeCd(planTypeCd){
	$("#curPlanTypeCd").val(planTypeCd);
	if("0" == planTypeCd){
		$("#resOrgListCd_select_pre").show();
		$("#resOrgListCd_select_exec").hide();
		//$("#resOrgListCd_select_cost").hide();
	}
	else if("1" == planTypeCd){
		$("#resOrgListCd_select_pre").hide();
		$("#resOrgListCd_select_exec").show();
		//$("#resOrgListCd_select_cost").hide();
	}
	/*
	else if("2" == planTypeCd){
		$("#resOrgListCd_select_pre").hide();
		$("#resOrgListCd_select_exec").hide();
		$("#resOrgListCd_select_cost").show();
	}
	*/
}
//初始化高度
function initPlanContainerHeight(){
	$("#planContainerDiv").height(getCurDocHeight()-$("#portalTopDiv").height()-5);
}
//获取选择的中心
function getResOrgName(){
	var planTypeCd= getPlanTypeCd();
	//执行计划
	if("1" == planTypeCd){
		return $("#resOrgListCd_select_exec").find("option:selected").text();
	}else{
		return $("#resOrgListCd_select_pre").find("option:selected").text();
	}
}
//切换"计划类型"(前期-执行),触发刷新
function changePlanTypeCd(){
	if (isLoading) {
		return;
	}
	
	var planTypeCd = getPlanTypeCd();
	//执行计划
	if("1" == planTypeCd){
		setPlanTypeCd("0");
		$("#curPlanTypeDescPre").show();
		$("#curPlanTypeDescExec").hide();
		//$("#curPlanTypeDescCost").hide();
		$("#planTypeDescChg").html("执行计划");
	}else{
		setPlanTypeCd("1");
		$("#curPlanTypeDescPre").hide();
		$("#curPlanTypeDescExec").show();
		//$("#curPlanTypeDescCost").hide();
		$("#planTypeDescChg").html("前期计划");
	}
	switchProject(null, getCurProjectCd());
}
// 切换地产公司
function switchProject(srcEle, curProjCd) {
	var planTypeCd = getPlanTypeCd();
	if (isLoading) {
		return;
	}
	curOpenedNodeCompDateId = null;

	$("#planContainerDiv").html('<div class="waiting" style="width:200px;height:100px;"></div>');
	$("#projs_nav_btns li div.active").removeClass("active");
	
	if(srcEle){
		srcEle.addClass("active");
	}
	isLoading = true;
	
	var url  = _PATH + "/planold/plan-exec-plan!planMatrix.action";
	var data = {projectCd : curProjCd,planTypeCd: planTypeCd};
	$.post( url, data, function(result) {
		$("#planContainerDiv").html(result).hide().fadeIn();
		setCurProjectCd(curProjCd);
		isLoading = false;
		resetMatrixLayout();
	});
}
//切换地产公司
function projectChange(){
	getDisplayProject();
}
//切换主责方
function resOrgChange(){
	getDisplayProject();
}
//private
function getDisplayProject(){
	var curProjCd=$("#typecd_select").val();
	var curResOrgCd="";
	var curResOrgName=getResOrgName();
	//alert(curResOrgName);
	if( curProjCd){
		if (isLoading) {
			return;
		}
		curOpenedNodeCompDateId = null;
		
		$("#planContainerDiv").html('<div class="waiting" style="width:200px;height:100px;"></div>');
		$("#projs_nav_btns li div.active").removeClass("active");
		$("projs_nav_btns_container").addClass("active");
		isLoading = true;
		
		var planTypeCd = getPlanTypeCd(); 
		var url  = _PATH + "/planold/plan-exec-plan!planMatrix.action";
		var data = {projectCd : curProjCd,planTypeCd: planTypeCd,resOrgCd: curResOrgCd,resOrgName:curResOrgName};
		$.post( url, data, function(result) {
			$("#planContainerDiv").html(result).hide().fadeIn();
			setCurProjectCd(curProjCd);
			isLoading = false;
			resetMatrixLayout();
		});
	}
}
// 滚动菜单
function scrollNavBtn(direction) {
	if (isScrolling) {
		return;
	}
	var mgLeft = parseInt($("#projs_nav_btns").css("margin-left"));
	if (direction == "left") {
		mgLeft = mgLeft - scrollWidth;
	} else if (direction == "right") {
		if (mgLeft < 0) {
			mgLeft = mgLeft + scrollWidth;
		}
	}
	isScrolling = true;
	$("#projs_nav_btns").animate({marginLeft: mgLeft}, 300, function() {
		isScrolling = false;
	});
}
//滚动时,自动保持左下/右上/右下布局对齐.
function resetLayout(obj) {
	var t = parseInt(obj.scrollTop());
	var l = parseInt(obj.scrollLeft());
	
	$("#bottomLeftTitle").css("margin-top", -t + "px");
	$("#topRightTitle").css("margin-left", -l + "px");
}
// 激活节点完成时间输入框
function enableNodeCompDateInput(nodeId, event) {
	jQuery.Event(event).stopPropagation();
	
	if (curOpenedNodeCompDateId) {
		if (nodeId != curOpenedNodeCompDateId) {
			$("#" + curOpenedNodeCompDateId + "compDateInput").hide();
			$("#" + curOpenedNodeCompDateId + "compDateDiv").show();
			svNodeCompDate(curOpenedNodeCompDateId);
		} else {
			var dateDiv = $("#" + nodeId + "compDateDiv");
			var dateInpt = $("#" + nodeId + "compDateInput");
			
			var oldVal = $.trim(dateDiv.html());
			var newVal = $.trim(dateInpt.val());
			
			if (newVal != oldVal) {
				svNodeCompDate(curOpenedNodeCompDateId);
				return;
			}
		}
	}
	curOpenedNodeCompDateId = nodeId;
	
	var dateDiv = $("#" + nodeId + "compDateDiv");
	var dateInpt = $("#" + nodeId + "compDateInput");
	dateDiv.hide();
	dateInpt.val($.trim(dateDiv.html())).show();
}
function showDatePicker(event) {
	jQuery.Event(event).stopPropagation();
	WdatePicker();
}
// 保存节点的完成时间
function svNodeCompDate(nodeId) {
	var dateDiv = $("#" + nodeId + "compDateDiv");
	var dateInpt = $("#" + nodeId + "compDateInput");
	
	var oldVal = $.trim(dateDiv.html());
	var newVal = $.trim(dateInpt.val());

	dateInpt.val("").hide();
	dateDiv.html(newVal).show();
	
	if (newVal != oldVal) {
		$.post(_PATH + "/planold/plan-exec-plan!saveCompDate.action",
				{
					projNodeId 			: nodeId,
					nodeCompleteDate 	: newVal
				},
				function(result) {
					if (result == "succ") {
						displaySuccInfo("节点完成日期更新成功");
					} else {
						alert("节点完成日期更新失败");
					}
		});
	}
	
	curOpenedNodeCompDateId = null;
}
// 展示操作成功信息
function displaySuccInfo(msg) {
	$("#succInfoMsg").html(msg).show().fadeOut(2000);
}
// 配置业态
function configPlan() {
	if (isLoading) {
		return;
	}
	var curProjCd  = getCurProjectCd();
	var planTypeCd = getPlanTypeCd();
	$("#planContainerDiv").html('<div class="waiting" style="width:200px;height:100px;"></div>');
	
	var url  = _PATH + "/planold/plan-exec-plan!configPlan.action";
	var data = {projectCd: curProjCd,planTypeCd: planTypeCd};
	$.post(url, data,function(result) {
		$("#planContainerDiv").html(result).hide().fadeIn();
		$("#configPlanContentDiv").height($("#planContainerDiv").height()-$("#configPlanHeadDiv").height());
		$("#configPlanContentDiv").css({"overflow-y":"auto"});
	});
}
function backToProjPlan() {
	var curProjCd = getCurProjectCd();
	//alert(curProjCd);
	//$("#proj_" + curProjCd).trigger("click");
	switchProject(null, curProjCd);
}
// 增加计划模块
function addPlanModule(projPlanId) {
	var curProjCd = getCurProjectCd();
	var planTypeCd = getPlanTypeCd();
	//alert(planTypeCd);
	var projPlanName = $("#projPlanNameInput").val();
	if($.trim(projPlanName) == ''){
		alert("请输入业态名称!");
		return;
	}
	
	var url  = _PATH + "/planold/plan-exec-plan!saveProjPlan.action";
	var data = {
			projectCd	:	curProjCd,
			planTypeCd  :   planTypeCd,
			projPlanId 	: 	"",
			projPlanName:	projPlanName
		};
	$.post(url, data, function(result) {
		if (result == "succ") {
			configPlan();
		}
	});
}
// 激活计划名称输入框
function enablePlanNameEdit(planId) {
	$("#" + planId + "PlanNameInput").show().focus();
	$("#" + planId + "PlanNameDiv").hide();
}
function savePlanName(planId) {
	var planNameInput = $("#" + planId + "PlanNameInput");
	var planNameDiv = $("#" + planId + "PlanNameDiv");
	
	var oldPlanName = $.trim(planNameDiv.html());
	var newPlanName = $.trim(planNameInput.val());
	var curProjCd = getCurProjectCd();
	
	if (oldPlanName == newPlanName) {
		planNameInput.hide();
		planNameDiv.show();
		return;
	}
	
	var url  = _PATH + "/planold/plan-exec-plan!saveProjPlan.action";
	var data = {
			projectCd	:	curProjCd,
			projPlanId 	: 	planId,
			projPlanName:	newPlanName
		};
	$.post( url, data, function(result) {
		if (result == "succ") {
			planNameInput.hide();
			planNameDiv.html(planNameInput.val()).fadeIn();
			displaySuccInfo("计划名称更新成功");
		} else {
			alert("计划名称更新失败");
		}
	});
}
// 更改状态（激活/禁用）
function changePlanState(planId, curState) {
	if (!curState) {
		return;
	}
	
	var newState = curState == "1" ? "0" : "1"; 
	var data = {projPlanId : planId,projPlanState : newState };
	$.post(_PATH + "/planold/plan-exec-plan!changePlanState.action", data, function(result) {
		if (result == "succ") {
			configPlan();
		}
	});
}
// 激活控制计划的开始时间/完成时间的输入框
function enableDetailStDateEdit(eleKey) {
	$("#" +eleKey + "Input").val($.trim($("#" + eleKey + "Div").text())).show();
	$("#" + eleKey + "Div").hide();
}
//激活控制计划的开始时间/完成时间的输入框
function enableDetailEdDateEdit(eleKey) {
	$("#" +eleKey + "Input").val($.trim($("#" + eleKey + "Div").text())).show();
	$("#" + eleKey + "Div").hide();
}

// 管理项目提醒人
function mngProjReminder() {
	if (isLoading) {
		return;
	}
	var execPlanMainId = getExecPlanMainId();
	var planTypeCd = getPlanTypeCd();
	var murl  = _PATH + "/planold/plan-exec-plan!fetchProjReminders.action";
	var mdata = {executionPlanMainId : execPlanMainId,planTypeCd : planTypeCd};
	$.post(murl,mdata,
			function(result) {
				if (result) {
					var strAr = result.split("|");
					for (var i = 0; i < strAr.length; i ++) {
						var j = eval("(" + strAr[i] + ")");
						projReminders.push(j);
					}
				}
				
					
				var url = _PATH + "/oa/oa-user-selection!selPerson.action";
				temPersons = projReminders.slice(0);
				
				ymPrompt.confirmInfo({
					icoCls:"",
					title: "请选择项目联系人",
					message:"<div id='personDiv'><img align='absMiddle' src='" + _PATH + "/resources/images/common/loading.gif'></div>",
					useSlide:true,
					width:400,
					height:400,
					maxBtn: false,
					allowRightMenu:true,
					handler:saveProjReminders,
					afterShow:function(){
						$.post(url, function(result){
							$("#personDiv").html(result);
							if (temPersons.length > 0) {
								$("#deptDisplay").html("已选择人员");
								$("#userDisplay").empty();
								for (var i = 0; i < temPersons.length; i ++) {
									bindPersonEvents(temPersons[i], "userDisplay", temPersons, true);
								}
							} else {
								$("#userDisplay li").each(function(){
									var obj = {"uiid" : $(this).attr("id"), "userName" : $(this).text()};
									bindPersonEvents(obj, "userDisplay", temPersons, false);
								});
							}
						});
					}
				});
	});
}
// 保存项目提醒人
function saveProjReminders(tp){
	if (tp=="ok") {
		var curProjCd = getCurProjectCd();
		
		projReminders = temPersons.slice(0);
		var result = getSelectedResult(projReminders);
		var reminders = "";
		if (result.length > 0) {
			reminders = result[0].userIds;
		}
		var execPlanMainId = getExecPlanMainId();
		var planTypeCd = getPlanTypeCd();
		
		var url = _PATH + "/planold/plan-exec-plan!saveProjReminders.action";
		var data = {projReminder:reminders, executionPlanMainId:execPlanMainId,planTypeCd:planTypeCd };
		$.post( url, data, function(result) {
			if (result == "done") {
				displaySuccInfo("项目提醒人配置完成");
			}
		});
	}
	projReminders = [];
	temPersons = [];
}
// 管理节点提醒人
function mngNodeReminders(nodeRelId) {
	curNodeRelId = nodeRelId;
	
	var myUrl  = _PATH + "/planold/plan-exec-plan!fetchNodeReminders.action";
	var myData = {projNodeId : nodeRelId};
	$.post(myUrl, myData, function(result) {
		if (result) {
			var strAr = result.split("|");
			for (var i = 0; i < strAr.length; i ++) {
				var j = eval("(" + strAr[i] + ")");
				nodeReminders.push(j);
			}
		}
		
			
		var url = _PATH + "/oa/oa-user-selection!selPerson.action";
		temPersons = nodeReminders.slice(0);
		
		ymPrompt.confirmInfo({
			icoCls:"",
			title: "请选择节点联系人",
			message:"<div id='personDiv'><img align='absMiddle' src='" + _PATH + "/resources/images/common/loading.gif'></div>",
			useSlide:true,
			width:400,
			height:400,
			maxBtn: false,
			allowRightMenu:true,
			handler:saveNodeReminders,
			afterShow:function(){
				$.post(url, function(result){
					$("#personDiv").html(result);
					if (temPersons.length > 0) {
						$("#deptDisplay").html("已选择人员");
						$("#userDisplay").empty();
						for (var i = 0; i < temPersons.length; i ++) {
							bindPersonEvents(temPersons[i], "userDisplay", temPersons, true);
						}
					} else {
						$("#userDisplay li").each(function(){
							var obj = {"uiid" : $(this).attr("id"), "userName" : $(this).text()};
							bindPersonEvents(obj, "userDisplay", temPersons, false);
						});
					}
				});
			}
		});
	});
}
// 保存节点提醒人(TODO)
function saveNodeReminders(tp){
	if (tp=="ok") {
		nodeReminders = temPersons.slice(0);
		var result = getSelectedResult(nodeReminders);
		var reminders = "";
		var rimindersName = "";
		if (result.length > 0) {
			reminders = result[0].userIds;
			rimindersName = result[0].userNames;
		}
		$.post(_PATH + "/planold/plan-exec-plan!saveNodeReminders.action", 
				{
					nodeReminder 		: 	reminders,
					projNodeId			:	curNodeRelId
				},
				function(result) {
					if (result == "done") {
						displaySuccInfo("节点提醒人配置完成");
						$("#" + curNodeRelId + "ReminderDiv").attr("title", rimindersName).html(rimindersName);
					}
					curNodeRelId = null;
				}
		);
	}
	
	nodeReminders = [];
	temPersons = [];
}
// 展示计划详情
function showPlanDetail(srcEle, nodeRelId, planCd,detaId) {
	var url = _PATH + "/planold/plan-exec-plan!planDetailInput.action";
	var title = "";
	var detailId;
	if(detaId!=null&&detaId!=""){
		detailId =detaId;
	}else{
	    detailId = srcEle.attr("label");
	}
	ymPrompt.win({
		icoCls:"",
		title:"计划详情",
		message:"<div id='planDetailDiv'><img align='absMiddle' src='" + _PATH + "/resources/images/common/loading.gif'></div>",
		useSlide:true,
		width:500,
		autoClose: false,
		height:350,
		maxBtn: false,
		allowRightMenu:true,
		handler:savePlanDetail,
		closeBtn: false,
		btn:[['确定','ok'], ['退出', 'cancel']],
		afterShow:function(){
			$.post(url,
			{
				planDetailId : detailId,
				projNodeId	 : nodeRelId,
				projPlanCd	 : planCd
			},
			function(result){
				$("#planDetailDiv").html(result);
			});
		}
	});
}
function savePlanDetail(tp) {
	if (tp == 'cancel') {
		ymPrompt.close();
		return;
	}
	
	var curRole = $("#curRoleHid").val();
	if (curRole == "viewer") {
		ymPrompt.close();
		return;
	}
	
	var oriStatus = $("#planDetailOriStatusHid").val();
	var newStatus = $("#planDetailStatusHid").val();
	var oriPlanDetailid = $("#planDetailIdHid").val();
	
	if (curRole == "pj") {
		if (oriStatus == "0" && newStatus == "1") {
			var realEndDate = $("#realEndDateInput").val();
			if ($.trim(realEndDate).length == 0) {
				alert("请输入实际完成时间");
				return;
			}
		}
	}
	
	var projNodeId = $("#projNodeIdHid").val();
	var projPlanCd = $("#projPlanCdHid").val();
	
	$("#planDetailForm").ajaxSubmit(function(result) {
		/*
		if(result=="clear"){
			var key = projNodeId + "_" + projPlanCd;
			$("#" + key + "ScheEdDateTd").attr("label", "");
			$("#" + key + "RealEdDateTd").attr("label", "");
			$("#" + key + "StatusTd").attr("label", "");
			$("#" + key + "StatusTd").html("");
			$("#" + key + "ScheEdDateSpan").html("");
			//$("#" + key + "RealEdDateSpan").html("");
		}else{
		*/
			$.post(_PATH + "/planold/plan-exec-plan!fetchPlanDetailInfo.action", 
			{
				planDetailId	:	oriPlanDetailid,
				projNodeId		:	projNodeId,
				projPlanCd		:	projPlanCd
			},
			function (result) {
				if (result) {
					var j = eval("(" + result + ")");
					var key = projNodeId + "_" + projPlanCd;
					
					$("#" + key + "ScheEdDateTd").attr("label", j.id);
					$("#" + key + "RealEdDateTd").attr("label", j.id);
					$("#" + key + "StatusTd").attr("label", j.id);
					$("#" + key + "ScheEdDateSpan").html(j.scheduleEdDate);
					//$("#" + key + "RealEdDateSpan").html(j.realEdDate);
					
					var html = "";
					var status = j.status;
					var infoConfirmedFlg = j.infoConfirmedFlg;
					if (status == "0") {
						if(infoConfirmedFlg == "0"){
							html = "<img src='" + _PATH + "/resources/images/plan/pic_noConfirm.gif' title='" + "待确认'/>";
						}
						else if (infoConfirmedFlg == "1") {
							html = "<img src='" + _PATH + "/resources/images/plan/pic_confirm.gif' title='" + "确认'/>";
						}
					} else if (status == "1") {
						html = "<img src='" + _PATH + "/resources/images/plan/pic_preFinish.gif' title='" + "预完成' />";
					} else if (status == "2") {
						html = j.realEdDate + " &nbsp <img src='" + _PATH + "/resources/images/plan/pic_finish.gif' title='" + "完成' />";
					}
					$("#" + key + "StatusTd").html(html);

					/* 20100826 "项目管理员"点击"完成"后自动提醒"管理员"审核
					if (curRole == "pj" && oriStatus == "0" && newStatus == "1") {						
						$.post(_PATH + "/planold/plan-exec-plan!remindVerify.action", { planDetailId : oriPlanDetailid });
					}
					*/
				}
			});
		/*
		}
		*/
	});
	
	ymPrompt.close();
}
// 上传附件，成功后刷新附件列表
function uploadAttachment() {
	var fileName = $("#uploadInput").val();
	if (!fileName) {
		alert("请选择待上传的文件!");
		return;
	}
	$("#outputUploadForm").ajaxSubmit(function(result) {
		refreshOutputList($("#bizEntityIdHid").val());
		var planDetailId = $.trim($("#planDetailIdHid").val());
		if (planDetailId && planDetailId.length > 0) {
			logOutputUpdateHistory(planDetailId, '3', '');
		}
	});
}
//删除附件，删除完毕后更新附件列表
function deleteAttachment(attachId) {
	var url = _PATH + "/app/app-attachment!delete.action";
	$.post(url,
		{
			id:	attachId
		},
		function(result) {
			refreshOutputList($("#bizEntityIdHid").val());
			var planDetailId = $.trim($("#planDetailIdHid").val());
			if (planDetailId && planDetailId.length > 0) {
				logOutputUpdateHistory(planDetailId, '4', attachId);
			}
		}
	);
}
function logOutputUpdateHistory(planDetailId, updateType, fileId) {
	$.post(_PATH + "/planold/plan-exec-plan!logOutputUpdateHistory.action",
			{
				planDetailId		: planDetailId,
				outputUpdateType	: updateType,
				deletedOutputFileId	: fileId,
				planTypeCd			: getPlanTypeCd()//很重要
			});
}
// 刷新附件
function refreshOutputList(bizEntityId) {
	$("#planDetailOutputList").html("").addClass("waiting");
	var projCd = getCurProjectCd();
	$.post(_PATH + "/planold/plan-exec-plan!fetchOutputList.action",
			{
				outputBizEntityId : bizEntityId,
				projectCd : projCd
			},
			function(result) {
				$("#planDetailOutputList").removeClass("waiting").html(result);
	});
}
// 查看操作日志
function reviewOpLogs() {
	if (isLoading) {
		return;
	}
	var projCd = getCurProjectCd();
	$("#planContainerDiv").html('<div class="waiting" style="width:200px;height:100px;"></div>');
	
	var url  = _PATH + "/planold/plan-operation-log!list.action";
	var data = {projectCd: projCd};
	$.post( url, data, function(result) {
		$("#planContainerDiv").html(result).hide().fadeIn();
	});
}
// 搜索操作日志
function searchOpLogs() {
	$("#operationLogPageNo").val("");
	$("#operationLogSearchForm").ajaxSubmit(function(result) {
		$("#planContainerDiv").html(result).hide().fadeIn();
	});
}
function jumpToPlanOpLogPage(pageNo) {
	var p = parseInt(pageNo);
	var t = parseInt($("#totalPageNoHid").val());
	
	if (p && t) {
		if (p <= 0 || p > t) {
			alert("请输入1至" + t + "的数字");
			return;
		}
		$("#operationLogPageNo").val(pageNo);
		$("#operationLogSearchForm").ajaxSubmit(function(result) {
			$("#planContainerDiv").html(result).hide().fadeIn();
		});
	}
}
// 发送提醒邮件
function remindVerify(srcEle, planDetailId) {
	
}
//清空已选择的节点
function deletePlanDetail(srcEle, planDetailId){
 if(planDetailId){
	 if(confirm("确定要清空这条记录吗？")){
		$.post(_PATH + "/planold/plan-exec-plan!deletePlanExecPlanDetail.action",
		{
			 planDetailId 	: planDetailId
		},	
		function (result) {
			
		});
	 }
 }
}
// 完成计划(即预完成操作)
function completePlan(srcEle, planDetailId) {
	srcEle.attr("disabled", "disabled");
	$.post(_PATH + "/planold/plan-exec-plan!checkIfCanComplete.action",
	{
		planDetailId 	: planDetailId
	},
	function (result) {
		
		//srcEle.hide();
		//$("#realEndDateSpan").hide();
		//$("#realEndDateInput").show();
		//$("#planDetailStatusHid").val("2");
		
		//先把result是否有附件再点击完成的判断去掉
		if (result == "ok") {
			srcEle.hide();
			$("#realEndDateSpan").hide();
			$("#realEndDateInput").show();
			$("#planDetailStatusHid").val("1");
		} else if (result == "no") {
			alert("请先上传成果附件后再点完成");
			srcEle.attr("disabled", "");
		}
		//是否有附件判断结束。
	});
}
// 更改计划详情状态
function changePlanDetailStatus(srcEle, planDetailId, status) {
	srcEle.attr("disabled", "disabled");
	$.post(_PATH + "/planold/plan-exec-plan!changePlanDetailStatus.action",
	{
		planDetailId 	 : planDetailId,
		planDetailStatus : status
	},
	function (result) {
		if (result == "succ") {
			srcEle.hide();
			if (status == "0") {
				alert("该计划已恢复为未完成状态");
			}
			if (status == "1") {
				alert("该计划已置为预完成状态");
			}
			if (status == "2") {
				alert("该计划已置为完成状态");
			}
			$("#planDetailStatusHid").val(status);
		}
	});
}
$(function() {
	$("body").click(function() {
		if (curOpenedNodeCompDateId) {
			svNodeCompDate(curOpenedNodeCompDateId);
		}
	});
});
function resetMatrixLayout() {
	var leftWidth = $("#topLeftTd").width();
	var leftHeight = $("#topLeftTd").height();

	//功能区域
	var portalTopHeight = $("#portalTopDiv").height();

	//默认宽度/最大宽度
	var oldRightWidth = $("#topRightTdContainer").width();
	var dataPanelWidth = getCurDocWidth() - leftWidth-17;

	if( oldRightWidth <= dataPanelWidth){
		$("#alignWidthSpanDiv").hide();
	}
	
	//若宽度超出屏幕,则出现滚动条
	if( oldRightWidth > dataPanelWidth){
		$("#topRightTdContainer").css({"width":dataPanelWidth+"px"});
		$("#bottomRightDiv").css({"width":(dataPanelWidth+17)+"px"});
	}else{
		//$("#noDataWidthDiv").hide();
	}
	
	var dataPanelHeight = getCurDocHeight() - leftHeight - portalTopHeight-5;
	$("#dataLeftPanelTd").css({"height":dataPanelHeight+"px"});
	$("#dataRightPanelTd").css({"height":dataPanelHeight+"px"});
	$("#bottomLeftDiv").css({"height":dataPanelHeight+"px"});
	$("#bottomRightDiv").css({"height":dataPanelHeight+"px"});
	
	//左边节点行总高度超过数据区,出现y滚动条
	if($("#noOperationDiv").height() > $("#bottomRightDiv").height()){
		$("#bottomRightDiv").css({"overflow-y":"scroll"});
	}
	if($("#bottomRightTd").height() > $("#bottomRightDiv").height()){
		$("#bottomRightDiv").css({"overflow-y":"scroll"});
	}
}
function hoverPlan(srcEle) {
	var k = srcEle.attr("planInfo");
	$("td[planInfo='" + k + "']").css("background-color", "#e1e1e1");
}
function leavePlan(srcEle) {
	var k = srcEle.attr("planInfo");
	$("td[planInfo='" + k + "']").css("background-color", "");
}
function confirmInfo(planDetailId) {
	if (planDetailId) {
		$.post(_PATH + "/planold/plan-exec-plan-detail!confirmPlanInfo.action", { planDetailId : planDetailId },
			function (result) {
				if (result == "succ") {
					alert("信息审核确认成功");
					$("#infoConfirmTd").html("<span style='color: green'>已确认</span>");
				}
		});
	}
}
//批量确认
function batchConfirm() {
	if (isLoading) {
		return;
	}
	
	var desc = "";
	switch (getPlanTypeCd()) {
		case "1":
			desc = " 执行计划 ";
			break;
		case "0":
			desc = " 前期计划 ";
			break;
		default:
			desc = " 位置类型 ";
			alert(desc);
			break;
	}
	if (confirm("确定所有的"+desc+"都是正确吗？")) {
		var projCd = getCurProjectCd();
		var planTypeCd = getPlanTypeCd();
		var url  = _PATH + "/planold/plan-exec-plan-detail!bachConfirmPlanInfo.action";
		var data = { projectCd : projCd ,planTypeCd: planTypeCd};
		$.post(url, data, function (result) {
			if (result == "succ") {
				alert("本项目的所有"+desc+"信息已审核确认成功");
				switchProject(null, getCurProjectCd());
			}
		});
	}
}

//控制计划
function viewControlPlans(srcEle) {
	if (isLoading) {
		return;
	}
	curOpenedNodeCompDateId = null;
	
	setPlanTypeCd("1");
	
	$("#curPlanTypeDescPre").hide();
	$("#curPlanTypeDescExec").show();
	//$("#curPlanTypeDescCost").hide();
	
	$("#planTypeDescChg").html("前期计划");
	
	$("#planContainerDiv").html('<div class="waiting" style="width:200px;height:100px;"></div>');
	var projCd = getCurProjectCd();
	
	isLoading = true;
	var url  = _PATH + "/planold/plan-exec-plan!planMatrix.action";
	var data = {projectCd : projCd, planType : "control", planTypeCd: "1"};
	$.post( url, data, function(result) {
		$("#planContainerDiv").html(result).hide().fadeIn();
		setCurProjectCd(projCd);
		isLoading = false;
		resetMatrixLayout();
	});
}
//管理节点
function configNodes() {
	if (isLoading) {
		return;
	}
	var projCd = getCurProjectCd();
	var planTypeCd= getPlanTypeCd();
	$("#planContainerDiv").html('<div class="waiting" style="width:200px;height:100px;"></div>');
	var url  = _PATH + "/planold/plan-exec-plan-node!list.action";
	var data = {projectCd:projCd,planTypeCd:planTypeCd};
	$.post(url, data,function(result) {
		$("#planContainerDiv").html(result).hide().fadeIn();
		$("#configNodeContentDiv").height($("#planContainerDiv").height()-$("#configNodeHeadDiv").height());
		$("#configNodeContentDiv").css({"overflow-y":"auto"});
	});
}
function exportExcel() {
	var url = _PATH + "/planold/plan-exec-plan-detail!exportExcel.action";
	this.location.href=url;
}
function fetchCenterDetails(){
	var url =_PATH + "/planold/plan-exec-plan!getCenterPlanRel.action?centerCd="+"11";
	this.location.href=url;
}
function returnMain(){
	var url =_PATH +"/planold/plan-exec-plan!portal.action";
	this.location.href=url;
}
function gotoExecStat(centerCd,projectCd){
	var url =_PATH +"/planold/plan-exec-plan-stat!portal.action?centerCd="+centerCd+"&projectCd="+projectCd;
	showExecStat(url);
}
function getFullSrc(projectCd,centerCd){
	if(projectCd!=""){
		var url = _PATH + "/planold/plan-exec-plan!getCenterPlanRel.action?projectCd=" + projectCd;
		window.open(url);
	}
	if(centerCd!=""){
		var url = _PATH + "/planold/plan-exec-plan!getCenterPlanRel.action?centerCd="+centerCd;
		window.open(url);
	}
}

function projectExecStat(){
	var projectCd  = getCurProjectCd();
	var planTypeCd = getPlanTypeCd();
	var url = _PATH +"/planold/plan-exec-plan-stat!portal.action?projectCd="+projectCd +"&planTypeCd="+planTypeCd;
	showExecStat(url);
}

//private 
function showExecStat(url){
	//alert(projectCd);
	try{
		window.parent.TabUtils.newTab("work_plan_exec_stat","业态进度",url,true);
	}catch(e){
		window.open(url);
	}
}
function projectWorkPlan(){
	var url = _ctx + "/plan/cost-exec-plan!getCenterPlanRel.action?projectCd=" + getCurProjectCd();
	window.parent.TabUtils.newTab("work_plan_exec_rel_list","项目进度",url,true);
}
//导出执行计划统计
function exportExecPlanStat(){
	ymPrompt.confirmInfo({
		icoCls:"",
		title: "请选择导出年月",
		message:'<div id="exportYearMonthDiv" style="padding:10px;"><input id="exportYearMonth" type="text" onfocus=WdatePicker({dateFmt:"yyyy-MM"}) class="Wdate" style="text-align:left;padding:5px 30px 5px 10px;"/></div>',
		useSlide:true,
		width:220,
		height:120,
		maxBtn: false,
		allowRightMenu:true,
		handler:exportYearMonth,
		afterShow:function(){
			var myDate = new Date();
			var year = myDate.getFullYear();   //获取完整的年份(4位,1970-????)
			var month = ((myDate.getMonth() < 10) ? "0" : "") + myDate.getMonth()+"";
			$('#exportYearMonth').val(year+ '-' + month);
		}
	});
}
function exportYearMonth(tp){
	if (tp=="ok") {
		window.open(_ctx + "/planold/plan-exec-export!export.action?yearMonth="+$("#exportYearMonth").val());
	}
}
//导出项目执行明细
function exportExecPlanDetail(){
	window.open(_ctx + "/planold/plan-exec-export!export.action?yearMonth="+$("#exportYearMonth").val());
}