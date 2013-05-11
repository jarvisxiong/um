var CENTER_NAMES = {
		147: "监审部",
		868: "公共关系部",
		7: "行政管理中心",
		9: "人力资源管理中心",
		8: "财务管理中心",
		10: "资本管理中心",
		11: "投资发展中心",
		12: "技术研发中心",
		725: "总师室",
		712: "设计管理中心",
		710: "营销管理中心",
		720: "项目管理中心",
		17: "成本控制中心",
		132: "营运管理中心",
		134: "酒店开发中心",
		153: "商业集团",
		155: "商业行政人事部",
		156: "商业财务部",
		154: "商业招商中心",
		157: "商业营运中心",
		869: "商业总工办",
		439: "商业企划部",
		453: "百货管理中心",
		1087: "企业发展中心"
};
var ORDER_BY = {
		"lx": "planType",
		"mb": "targetDate",
		"zt": "status",
		"gx": "updatedDate",
		"sh": "sequenceNumber"
};
var PLAN_TYPES = {
		1: "年计划",
		2: "月会工作",
		3: "项目管理",
		4: "指令单",
		5: "综合",
		6: "季度KPI",
		7: "四级计划",
		8: "中心工作"
};

function init() {
	// 初始化中心选择div
	if("1"==bisFlg){
		var centers = $("#selectCenterBis >:input");
		initSelect("selectCenterBis", "centerName", centers, $("#currentCenterCd").val());
	}else{
		var centers = $("#selectCenter >:input");
		initSelect("selectCenter", "centerName", centers, $("#currentCenterCd").val());
	}
	// 选中当前用户所在中心
	for(var i=0; i<centers.size(); i++) {
		if((i+1) % 5 == 0) {
			centers.eq(i).css("border-right", "none");
		}
	}

	var date = new Date();
	var year = 0;
	try{
		year = currentPlanYear;
	}catch(e){year = date.getFullYear();}
	if($("#currentPlanYear").val()) {
		year = $("#currentPlanYear").val();
	}
	$("#year").val(year);
	$("#targetYear").val(year);
	
	// 初始化年份选择div
	var years = $("#selectYear >:input");
	initSelect("selectYear", "year", years, year);
	
	// 当前月份
	var month = 0;
	try{
		month = currentPlanMonth;
	}catch(e){month = date.getMonth() + 1;}
	if($("#currentPlanMonth").val()) {
		month = $("#currentPlanMonth").val();
	}
	$("#month").val(month);
	$("#targetMonth").val(month);
	if(parseInt(month) < 10) {
		month = "0" + month;
	}
	
	// 初始化月份选择div
	var months = $("#selectMonth >:input");
	initSelect("selectMonth", "month", months, month);

}
/***
 * initSelect 初始化选项
 * @param containerId 选项容器
 * @param bindId 被绑定ID
 * @param options 选项内容(input[button])
 * @param current 当前的值
 */
function initSelect(containerId, bindId, options, current) {
	// 给选项绑定click事件
	options.bind("click", function() {
		for(var i=0; i<options.size(); i++) {
			if(options.eq(i).attr("class") == "btnSelect") {
				options.eq(i).attr("class", "btnCancel");
				options.eq(i).css("color", "#000000");
				break;
			}
		}
		if($(this).attr("class") == "btnSelect") {
			$(this).attr("class", "btnCancel");
			$(this).css("color", "#000000");
		} else {
			$(this).attr("class", "btnSelect");
			$(this).css("color", "#FFFFFF");
		}
	});
	// 默认当前选项
	for(var i=0; i<options.size(); i++) {
		if(options.eq(i).attr("key") == current) {
			options.eq(i).attr("class", "btnSelect");
			options.eq(i).css("color", "#FFFFFF");
		} else {
			options.eq(i).attr("class", "btnCancel");
			options.eq(i).css("color", "#000000");
		}
	}

	if("centerName" != bindId) { // 当选择中心的时候有特殊处理
		$("#" + bindId).bind("mouseenter", function() {$("#" + containerId).show();});
	}
	$("#" + containerId).bind("mouseenter", function() {$("#" + containerId).show();});
	$("#" + containerId).bind("mouseleave", function() {$("#" + containerId).hide();});
	$("#" + bindId).bind("mouseleave", function() {$("#" + containerId).hide();});
	// 绑定事件：当鼠标在选择器控件上时，取消目标控件的mouseout事件取消
	$("#" + containerId).bind("mouseenter", function() {
		$("#" + bindId).unbind("mouseleave");
	});
	// 绑定事件：当鼠标在选择器控件上离开时，绑定目标控件的mouseout事件取消
	$("#" + containerId).bind("mouseleave", function() {
		$("#" + bindId).bind("mouseleave", function() {$("#" + containerId).hide();});
	});
	
}
function centerSelect(centerCd) {
	$("#centerCd").val(centerCd);
	$("#centerName").val(CENTER_NAMES[centerCd]);
	$("#currentCenterCd").val(centerCd);
	jumpPage('1');
}
function selectMonth(month) {
	monthHide();
	$("#month").val(month);
	$("#targetMonth").val(month);
	$("#currentPlanMonth").val(month);
	jumpPage('1');
}
function selectYear(year) {
	yearHide();
	$("#year").val(year);
	$("#targetYear").val(year);
	$("#currentPlanYear").val(year);
	jumpPage('1');
}
function selectYearByYear(year){
	yearHide();
	$("#year").val(year);
	$("#targetYear").val(year);
	$("#currentPlanYear").val(year);
	jumpPageYear('1');
}
function centerShow() {$("#selectCenter").show();}
function centerHide() {$("#selectCenter").hide();}
function monthShow() {$("#selectMonth").show();}
function monthHide() {$("#selectMonth").hide();}
function yearShow() {$("#selectYear").show();}
function yearHide() {$("#selectYear").hide();}
//计划的商业部门
function centerByBisShow() {$("#selectCenterBis").show();}
function centerByBisHide() {$("#selectCenterBis").hide();}
/**商业**/
function yearHideByBis() {$("#selectYearByBis").hide();}
function yearShowByBis() {$("#selectYearByBis").show();}
function monthShowByBis() {$("#selectMonthByBis").show();}
function monthHideByBis() {$("#selectMonthByBis").hide();}
function hideOrShow_Search() {
	var display = $("#searchPanel").css("display");
	if(display == "none") {
		$("#searchPanel").show();
	} else {
		$("#searchPanel").hide();
	}
}
function hideOrShow_New() {
	var display = $("#newMonthPlan").css("display");
	if(display == "none") {
		var month =$("#month").val();
		if(!(""==month||"0"==month)){
			$("#newTime").val(month);
			
		}
		$("#newMonthPlan").show();
		$("#edit_plan_type").val("月会工作");
		$("#editPlanNewType").val("2");
	} else {
		$("#newMonthPlan").hide();
	}
}
function doVal_targetDate(){
	if(!$("#targetDate").val()){
		$("#targetDateVal").show();
		return false;
	} else {
		$("#targetDateVal").hide();
	}
	return true;
}
function doVal_content(){
	if(!$("#content").val()){
		$("#contentVal").show();
		return false;
	} else {
		$("#contentVal").hide();
	}
	return true;
}
function clickSort(attr) {
	var orderBy = "";
	var order = "asc";
	var tds = $(".mainTable >thead >tr >td");
	for(var i=0; i<tds.size(); i++) {
		if(tds.eq(i).attr("sortCol") != "" && tds.eq(i).attr("sortCol") == attr) { // 排序的列
			orderBy = ORDER_BY[attr];
			if(tds.eq(i).children().attr("class") == "asc") {
				tds.eq(i).children().attr("class", "desc");
				order = "desc";
			} else {
				tds.eq(i).children().attr("class", "asc");
				order = "asc";
			}
			$("#orderColMark").val(attr);
			$("#orderMark").val(order);
		}
	}
	//alert("orderBy: " + orderBy + "\norder: " + order);
	$("#orderBy").val(orderBy);
	$("#order").val(order);
	jumpPage("1");
}
function jumpPage(pageNo){
	if(""!=pageNo){
		$("#queryPageNo").val(pageNo);
	}
	centerHide();
	var url = "" + _ctx + "/plan/plan-target!month.action";
	TB_showMaskLayer("正在搜索...",5000);
	$("#advanceSearch").attr("action", url).ajaxSubmit(function(result){
		$("#contentPanel").html(result);
		TB_removeMaskLayer();
		initMonthList();
		if(null!=opened_entityid && ""!=opened_entityid){
			openEditPlanTarget(opened_entityid);
		}
		autoHeightTarget("contentTable");
	});
}
function autoHeightTarget(contentTable){
	var addHeight=$("#"+contentTable).height()-540;//多出来的高度
    var endHeight = 0;
	try{
		var menuId = top.nowMenuId;
		if(null!=menuId && undefined !=menuId){
			var	ch =0;
			if(addHeight>0){
				ch=$("#"+contentTable).height()+170+addHeight;
			}else{
				ch=$("#"+contentTable).height()+170;
			}
			if(endHeight == 0){
				endHeight = ch;
			}else if(ch > endHeight){
				endHeight = ch;
			}
			if(endHeight>604){
				$(window.top.document).find("#bodyLoad").height(endHeight+51);
				$(window.top.document).find("#div_left_b").height(endHeight+51);
				$(window.top.document).find('#' + menuId+'_frame').contents().find("body").height(endHeight-50);
				$(window.top.document).find('#' + menuId+'_frame').height(endHeight);
			}else{
				$("#mainBody").height(540);
			}
		}else{
			$("#mainContent").height(addHeight+630);
			$("#mainBody").height(addHeight+540);
		}
	}catch(e){}
} 
// 初始化月计划数据列表
function initMonthList() {
	// 排序处理
	var tds = $(".mainTable >thead >tr >td");
	var col = $("#orderColMark").val(); // 记录排序的列
	var order = $("#orderMark").val();
	for(var i=0; i<tds.size(); i++) {
		if(tds.eq(i).attr("sortCol") == col) {
			tds.eq(i).children().attr("class", order);
		}
	}
	// 中心计划类型选择
	$(".selectType").hide();
	$(".editPlanType").bind("click", typeShow);
	$(".editPlanType").bind("blur", typeHide);
	$(".selectType").bind("mouseover", function() {$(".editPlanType").unbind("blur");});
	$(".selectType").bind("mouseout", function() {$(".editPlanType").bind("blur", typeHide);});
	var lis = $(".selectType >ul >li");
	//lis.bind("click", function() {});
	lis.bind("mouseover", function() {$(this).css("background-color", "#CBCBCB");$(this).css("color", "#FFFFFF");});
	lis.bind("mouseout", function() {$(this).css("background-color", "#FFFFFF");$(this).css("color", "#000000");});

}
function clickType(id, value) {
	$("#editPlanTypeVal_" + id).val(value);
	$(".editPlanType").val(PLAN_TYPES[value]);
	typeHide();
}
function clickNewType(dom,value,key){
	$(dom).parent().parent().prev().val(value);
	$("#editPlanNewType").val(key);
	typeHide();
}
function typeShow() {$(".selectType").show();}
function typeHide() {$(".selectType").hide();}
function doSubmit() {
	$("#newCenter").val($("#centerCd").val());
	var month=$("#newTime").val();
	var year =$("#year").val();
	if(""==month){
		alert("请选择目标月份");
	}
	var lastDay =getLastDay(year,month);
	if(month.length==1){
		$("#targetDate").val(year+"-0"+month+"-"+lastDay);
	}else{
		$("#targetDate").val(year+"-"+month+"-"+lastDay);
	}
	if(doVal_targetDate() && doVal_content()) {
		$("#newPlanTarget").submit();
	}
}

/***
 * 隐藏或显示按钮(非本月和驳回)
 */
function s_h_other(id, displany) {
	var statusBtn = $("#statusBtn_" + id + " >thead >tr td input[type=button]");
	for(var i=0; i<statusBtn.size(); i++) {
		if(statusBtn.eq(i).attr("title") != "退回") {
			statusBtn.eq(i).css("display", displany);
		}
	}
}
/***
 * 预处理状态
 * @param id planTargetId
 * @param statusFlg 状态
 * @returns {Boolean}
 */
function preStatus(id, statusFlg) {
	if("10" == statusFlg) { // 删除
		var result = confirm("你要删除这个工作计划吗？");
		if(result) {
			statusFlg = $("#editStatusVal_" + id).val();
		} else {
			return false;
		}
	}
	/*if("2" == statusFlg) { // 2 预完成
		var result = confirm("你要预完成这个工作计划吗？");
		if(result == false)
			return false;
	}*/
	if("8" == statusFlg) { // 8 隐藏状态
		var result = confirm("你要隐藏这个工作计划吗？");
		if(result == false)
			return false;
	}
	/*if("9" == statusFlg) { // 9 申请删除
		var result = confirm("你要申请删除这个工作计划吗？");
		if(result == false)
			return false;
	}*/
	if("7" == statusFlg) { // 7非本月
		s_h_other(id, "none");
		$("#tr_" + id).css("color", "#999999");
	}
	if("-1" == statusFlg) { // -1驳回
		s_h_other(id, "block");
		$("#tr_" + id).css("color", "#000000");
	}
	return true;
}
/***
 * doSubmitEdit
 * 
 * @param id planTargetId
 * @param statusFlg 修改状态时传值
 */
function doSubmitEdit(id, statusFlg, msgPub) {
	if(preStatus(id, statusFlg) == false) {
		return;
	}
	var url = ""+_ctx+"/plan/plan-target!save.action";
	if(msgPub == "byMsgPub" && $("#editMessage_" + id).val() == "") { // 如果没有留言内容
		return;
	}
	if(statusFlg && statusFlg != "") {
		if("-1"==statusFlg&&"1" == $("#editStatusVal_" + id).val()) {
			alert("执行状态无法退回！");
			return;
		}
		if($("#editStatusVal_" + id).val() == statusFlg) {
			// 过期(5)或者非考核性过期(6)可以重复点多次,其它状态屏蔽多次点击
			if("5" != statusFlg && "6" != statusFlg ) {
				return;
			}
		}
		url += "?statusFlg=" + statusFlg;
		if("10" == statusFlg) { // 删除状态
			url += "&sysStatusFlg=1";
		}
	} else {
		var edited = isValChanged(id);
		if(edited != "") {
			url += "?edited=" + edited;
		}
	}
	var year = $("#targetDate_" + id).val().split("-")[0];
	$("#targetDate_" + id).val(year + "-" + $("#editTargetDate_" + id).val());
	$("#editPlanTarget_" + id).attr("action", url).ajaxSubmit(function(result){
		var date = eval("(" + result + ")");
		if(statusFlg && statusFlg != "") {
			var status = date["status"];
			$("#editStatusVal_" + id).val(status);
			if(status==0) {$("#td_st_" + id).html('<div class="unexec" title="未执行">&nbsp;</div>');$("#edit_st_" + id).val("未执行");}
			else if(status==1) {$("#td_st_" + id).html('<div class="process" title="进行中" >&nbsp;</div>');$("#edit_st_" + id).val("进行中");}
			else if(status==2) {$("#td_st_" + id).html('<div class="prefinish" title="预完成" >&nbsp;</div>');$("#edit_st_" + id).val("预完成");}
			else if(status==3) {$("#td_st_" + id).html('<div class="finish" title="完成" >&nbsp;</div>');$("#edit_st_" + id).val("完成");}
			else if(status==4) {$("#td_st_" + id).html('<div class="completedelay" title="完成但曾经过期" >&nbsp;</div>');$("#edit_st_" + id).val("完成但曾经过期");}
			else if(status==5) {$("#td_st_" + id).html('<div class="suspend" title="过期" >&nbsp;</div>');$("#edit_st_" + id).val("过期");}
			else if(status==6) {$("#td_st_" + id).html('<div class="delay" title="非考核性过期">&nbsp;</div>');$("#edit_st_" + id).val("非考核性过期");}
			else if(status==7) {$("#td_st_" + id).html('<div class="unmonth" title="非本月任务">&nbsp;</div>');$("#edit_st_" + id).val("非本月任务");}
			else if(status==8) {$("#td_st_" + id).html('<div class="hidden" title="隐藏" >&nbsp;</div>');$("#edit_st_" + id).val("隐藏");}
			else if(status==9) {$("#td_st_" + id).html('<div class="predel" title="申请删除">&nbsp;</div>');$("#edit_st_" + id).val("申请删除");}
			else if(status==10) {$("#td_st_" + id).html('<div class="delete" title="删除">&nbsp;</div>');$("#edit_st_" + id).val("删除");}
		   if(status==3||status==4){
			   var oldCompCount =parseFloat($("#compCount").text());
			   oldCompCount++;
			   $("#compCount").text(oldCompCount);
			   var oldTotCount =parseFloat($("#totCount").text());
			   $("#compRate").text((((oldCompCount)/oldTotCount) * 100).toFixed(1));
		   }
		} else {
			editSequenceNumber = $("#td_edit_sn_" + id + " input").val();
			editPlanType = $("#td_edit_pt_" + id + " input[class=editPlanType]").val();
			editContent = $("#td_edit_ct_" + id + " textarea").val();
			editTargetDate = $("#td_edit_td_" + id + " input").val();
			
			$("#td_sn_" + id).html(date["sequenceNumber"]);
			// 类型ID编号转换为文本
			var planType = date["planType"];
			$("#td_pt_" + id).html(PLAN_TYPES[planType]);
			$("#td_ct_" + id).html(date["content"]);
			$("#td_td_" + id).html(date["targetDate"]);
			$("#td_up_" + id).html(date["updatedDate"]);
		}

		$("#editMessage_" + id).val("");
		var name = $("#editMessage_" + id).attr("name");
		var size = parseInt(name.split("[")[1].split("]")[0]) + 1;
		$("#editMessage_" + id).attr("name", name.split("" + size -1 + "")[0] + size + name.split("" + size -1 + "")[1]);
		var html = date["msgContent"];
		$("#msgList_" + id).html(html);
	});
}
function doSubmitMessage(id){
	var url = ""+_ctx+"/plan/plan-target!message.action";
	var message =$("#editMessage_"+id).val();
	$.post(url,{message:message,id:id},function(result){
		var date = eval("(" + result + ")");
		var html = date["msgContent"];
		$("#msgList_" + id).html(html);
		$("#editMessage_"+id).val("");
		var name = $("#editMessage_" + id).attr("name");
		var size = parseInt(name.split("[")[1].split("]")[0]) + 1;
		$("#editMessage_" + id).attr("name", name.split("" + size -1 + "")[0] + size + name.split("" + size -1 + "")[1]);
	});
}
/***
 * 批量修改状态
 * @param statusFlg 修改后的状态值
 * @param id 此处 id 赋值为了避免后台判断为新增
 */
function doSubmitBatchEdit(statusFlg, ids) {
	var url = ""+_ctx+"/plan/plan-target!save.action?statusFlg=" + statusFlg + "&id=" + ids[0];
	$("#delPlanTarget").attr("action", url).ajaxSubmit(function(result){
		var date = eval("(" + result + ")");
		var batchStatus = date["batchStatus"];
		var batchMsgContent = date["batchMsgContent"];
		for(var i in ids) {
			var status = batchStatus["status_" + ids[i]];
			var id = ids[i];
			if(status==0) {$("#td_st_" + id).html('<div class="unexec" title="未执行">&nbsp;</div>');$("#edit_st_" + id).val("未执行");}
			else if(status==1) {$("#td_st_" + id).html('<div class="process" title="进行中" >&nbsp;</div>');$("#edit_st_" + id).val("进行中");}
			else if(status==2) {$("#td_st_" + id).html('<div class="prefinish" title="预完成" >&nbsp;</div>');$("#edit_st_" + id).val("预完成");}
			else if(status==3) {$("#td_st_" + id).html('<div class="finish" title="完成" >&nbsp;</div>');$("#edit_st_" + id).val("完成");}
			else if(status==4) {$("#td_st_" + id).html('<div class="completedelay" title="完成但曾经过期" >&nbsp;</div>');$("#edit_st_" + id).val("完成但曾经过期");}
			else if(status==5) {$("#td_st_" + id).html('<div class="suspend" title="过期" >&nbsp;</div>');$("#edit_st_" + id).val("过期");}
			else if(status==6) {$("#td_st_" + id).html('非考核性过期');$("#edit_st_" + id).val("非考核性过期");}
			else if(status==7) {$("#td_st_" + id).html('非本月任务');$("#edit_st_" + id).val("非本月任务");}
			else if(status==8) {$("#td_st_" + id).html('<div class="hidden" title="隐藏" >&nbsp;</div>');$("#edit_st_" + id).val("隐藏");}
			else if(status==9) {$("#td_st_" + id).html('<div class="predel" title="申请删除">&nbsp;</div>');$("#edit_st_" + id).val("申请删除");}

			$("#editMessage_" + id).val("");
			var name = $("#editMessage_" + id).attr("name");
			var size = parseInt(name.split("[")[1].split("]")[0]) + 1;
			$("#editMessage_" + id).attr("name", name.split("" + size -1 + "")[0] + size + name.split("" + size -1 + "")[1]);
			var html = batchMsgContent["msgContent_" + ids[i]];
			$("#msgList_" + id).html(html);
		}
	});
}
var editSequenceNumber, editPlanType, editContent, editTargetDate;
// 编辑模块内容是否改变
function isValChanged(id) {
	//alert("planType : " + editPlanType + " === content : " + editContent + " ==== targetDate : " + editTargetDate);
	var edited = "";
	var current_sn = $("#td_edit_sn_" + id + " input").val();
	var current_pt = $("#td_edit_pt_" + id + " input[class=editPlanType]").val();
	var current_ct = $("#td_edit_ct_" + id + " textarea").val();
	var current_td = $("#td_edit_td_" + id + " input").val();
	//alert("currentPlanType : " + current_pt + " === currentContent : " + current_ct + " ==== currentTargetDate : " + current_td);
	if(editSequenceNumber && editSequenceNumber != current_sn) {
		edited += "sequenceNumber,";
	}
	if(editPlanType && editPlanType != current_pt) {
		edited += "planType,";
	}
	if(editContent && editContent != current_ct) {
		edited += "content,";
	}
	if(editTargetDate && editTargetDate != current_td) {
		edited += "targetDate,";
	}
	edited = edited.substr(0, edited.lastIndexOf(","));
	return edited;
}

// 上一个计划的ID
var lastPlanTargetId = "";
// 上一个计划的display (block或none)
var lastDisplay = "";

function deletePlanTarget(id) {
	var result = confirm("您要删除这条记录吗？");
	if(result) {
		var url = "" +_ctx+ "/plan/plan-target!save.action?sysStatusFlg=1&id=" + id;
		$("#delPlanTarget").attr("action", url).submit();
	}
}
/***
 * 批量删除(逻辑删除)
 * @param id 此处 id 赋值为了避免后台判断为新增
 */
function batchDeletePlanTarget(id) {
	var result = confirm("您要批量删除这些记录吗？");
	if(result) {
		var url = "" +_ctx+ "/plan/plan-target!save.action?sysStatusFlg=1&id=" + id;
		$("#delPlanTarget").attr("action", url).ajaxSubmit(function(r){
			jumpPage("1");
		});
	}
}
function preIds() {
	var cbxs = $(".mainTable >tbody >:tr >td input[name=cbx][checked=true]");
	if(cbxs.size() == 0) {
		alert("您没有选择要操作的计划！");
		return "";
	}
	var ids = new Array(cbxs.size());
	var html = "";
	for(var i=0; i<cbxs.size(); i++) {
		ids[i] = cbxs.eq(i).val();
		html += '<input type="hidden" name="ids" value="' + ids[i] + '"/>';
	}
	$("#delPlanTarget").html(html);
	return ids;
}
function batchEdit(statusFlg) {
	if(preIds() != "") {
		var result = confirm("您要批量操作工作计划吗？");
		if(result) {
			doSubmitBatchEdit(statusFlg, preIds());
		}
	}
}
function batchDelete() {
	var result = confirm("您要批量删除工作计划吗？");
	if(result) {
		batchDeletePlanTarget(preIds()[0]);
	}
}
function mouseOver(trId) {
	$("#tr_" + trId).css({"background-color": "#D9DEE6", "cursor": "pointer"});
}
function mouseOut(trId) {
	$("#tr_" + trId).css("background-color", "#FFF");
}
function fileList(entityId,owner) {
	var url = "" +_ctx+ "/plan/plan-target!uploadFile.action?bizEntityId="+entityId+"&bizModuleCd=planTarget&filterType=image|office&bizEntityName=PlanTarget";
	if("1"==owner){
		url=url+"&owner=1";
	}
	$("#editPlanTarget_" + entityId).attr("action", url).ajaxSubmit(function(result){
		var date = eval("(" + result + ")");
		var tbody = "";
		var size = 0;
		$.each(date, function(i, v) {
			size++;
			tbody += "<tr valign='top'>";
			tbody += "<td></td>";
			var fileUrl = downloadFile(v["fileName"], v["realFileName"], v["bizMouduleCd"], v["fileType"], v["id"]);
			var realFileName = v["realFileName"];
			var suffix = realFileName.substr(realFileName.lastIndexOf("."));
			var realName = realFileName.substr(0, realFileName.lastIndexOf("."));
			if(realName.length >20) {
				realFileName = realName.substr(0, 20) + "···" + suffix;
			}
			tbody += '<td><a href="' + fileUrl + '" title="' + v["realFileName"] + '" class="link">' + realFileName + '</a></td>';
			tbody += '<td width="80px"><span>' + v["creator"] + '</span></td>';
			//tbody += '<td width="10px"><span onclick="delFile(\'' + v["id"] + '\', \'' + entityId + '\');"><img src="' + _ctx + '/resources/images/common/status_del.gif" class="del" title="删除"/></span>';
			tbody += "</td></tr>";
		});
		if(size == 1) {
			tbody += "<tr><td colspan=\"4\">&nbsp;</td></tr>";
		}
		if(size == 0) {
			tbody += "<tr><td colspan=\"4\">&nbsp;</td></tr>";
			tbody += "<tr><td colspan=\"4\">&nbsp;</td></tr>";
		}
		$("#editAttach table tbody").html(tbody);
	});
}
function downloadFile(fileName, realFileName, bizMouduleCd, fileType, attachId) {
	var url = "" +_ctx+ "/app/download.action"
			+ "?fileName=" + fileName
			+ "&realFileName=" + realFileName
			+ "&bizModuleCd=" + bizMouduleCd
			+ "&filterType=" + fileType
			+ "&id=" + attachId;
	return url;
}
function delFile(attachId, planTargetId) {
	var url = "" +_ctx+ "/plan/plan-target!deleteFile.action?attachId=" + attachId + "&bizEntityId=" + planTargetId;
	//$.post(url);
	$("#delPlanTarget").attr("action", url).ajaxSubmit(function(result) {
		if(result == "success") {
			cur_entityId = planTargetId;
			attachRefresh();
		}
	});
}
function selectOne() {
	var sign = false; // 是否有未选中
	var cbxs = $(".mainTable >tbody >:tr >td input[name=cbx]");
	for(var i=0; i<cbxs.size(); i++) {
		if(cbxs.eq(i).attr("checked") == false) {
			sign = true;
			break;
		}
	}
	if(sign) {
		$("#cbxAll").attr("checked", false);
	} else {
		$("#cbxAll").attr("checked", true);
	}
}
function selectAll() {
	var cbxs = $(".mainTable >tbody >:tr >td input[name=cbx]");
	if($("#cbxAll").attr("checked") == true) {
		for(var i=0; i<cbxs.size(); i++) {
			cbxs.eq(i).attr("checked", true);
		}
	} else {
		for(var i=0; i<cbxs.size(); i++) {
			cbxs.eq(i).attr("checked", false);
		}
	}
}
/***
 * 锁定计划
 */
function onlock(){
	//alert("currentCenter: " + $("#currentCenterCd").val() + "\ncurrentPlanMonth : " + $("#currentPlanMonth").val());
	var flg=confirm("您要锁定计划吗？");
		if(flg){
			 $("#lockFlg").val("1"); // 锁定计划
		var url = "" +_ctx+ "/plan/plan-target!lockOperation.action";
		$("#advanceSearch").attr("action", url).submit();
	}
}

function openPlanWork2Year(){
	var centerCd="0";
	try{
		centerCd=$("#currentCenterCd").val();
		parent.TabUtils.newTab("154","年度工作计划",_ctx+"/plan/plan-work2-year!getAllPlan.action?centerCd="+centerCd,true);
	}catch(e){
		self.location = _ctx+"/plan/plan-work2-year!getAllPlan.action?centerCd="+centerCd;
	}
} 
function openPlanTargetYear(){
	var centerCd=$("#currentCenterCd").val();
	try{
		parent.TabUtils.newTab("154","年度工作计划",_ctx+"/plan/plan-target!yearEnter.action?currentCenterCd="+centerCd,true);
	}catch(e){
		self.location = _ctx+"/plan/plan-target!yearEnter.action?currentCenterCd="+centerCd;
	}
}
function openMonth(){
	var centerCd=$("#currentCenterCd").val();
	try{
		parent.TabUtils.newTab("153","年度工作计划",_ctx+"/plan/plan-target!monEnter.action?currentCenterCd="+centerCd,true);
	}catch(e){
		self.location = _ctx+"/plan/plan-target!yearEnter.action?currentCenterCd="+centerCd;
	}
}
function updateFile(){
	$.post(_ctx + '/app/app-attachment!updateAttach.action', function(result) {
		
	});
}
function fullScreen() {
	var centerCd = $("#currentCenterCd").val();
	var targetYear = $("#currentPlanYear").val();
	var targetMonth = $("#currentPlanMonth").val();
	
	var url = ""+ _ctx + "/plan/plan-target!monEnter.action?currentCenterCd=" + centerCd + "&currentPlanYear=" + targetYear + "&currentPlanMonth=" + targetMonth;
	window.open(url);
}
function fullScreenYear(){
	var url = ""+ _ctx + "/plan/plan-target!yearEnter.action";
	window.open(url);
}
function selectNewTime(){
	if($("#selectNewMonth").css("display")=="none"){
	  $("#selectNewMonth").show();
	// 默认当前选项
   	var options = $("#selectNewMonth >:input");
   	var current=$("#newTime").val();
   	if(current.length==1){
   		current="0"+current;
   	}
		for(var i=0; i<options.size(); i++) {
			if(options.eq(i).attr("key") == current) {
				options.eq(i).attr("class", "btnSelect");
				options.eq(i).css("color", "#FFFFFF");
			} else {
				options.eq(i).attr("class", "btnCancel");
				options.eq(i).css("color", "#000000");
			}
		 }
	}
	else
		$("#selectNewMonth").hide();
}
function selectNewMonth(selMonth){
	if(selMonth){
		$("#newTime").val(selMonth);
		$("#selectNewMonth").hide();
	}
}
//取当月的最后一天
function getLastDay(year,month)  {  
	 var new_year = year;    //取当前的年份  
	 var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）  
	 if(month>12)            //如果当前大于12月，则年份转到下一年  
	 {  
		  new_month -=12;        //月份减  
		  new_year++;            //年份增  
	 }  
	 var new_date = new Date(new_year,new_month,1);                //取当年当月中的第一天  
	 return (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月最后一天日期  
} 
function exportExcel(){
	var year =$("#year").val();
	var month =$("#month").val();
	location.href=_ctx+"/plan/plan-target!exportExcel.action?deptFlg=estate&currentPlanMonth="+month+"&currentPlanYear="+year;
}
function centerYearSelect(centerCd){
	$("#centerCd").val(centerCd);
	$("#centerName").val(CENTER_NAMES[centerCd]);
	$("#currentCenterCd").val(centerCd);
	jumpPageYear('1');
}
//年计划LIST
function jumpPageYear(pageNo){
	if(""!=pageNo){
		$("#queryPageNo").val(pageNo);
	}
	centerHide();
	var url = "" + _ctx + "/plan/plan-target!year.action";
	TB_showMaskLayer("正在搜索...",5000);
	$("#advanceSearch").attr("action", url).ajaxSubmit(function(result){
		$("#contentPanel").html(result);
		TB_removeMaskLayer();
		
		autoHeightTarget("mainContent");
	});
}
//年计划里的高级搜索
function seniorSearch(){
	if(""!=$("#workTarget").val()){
		$("#yearTarget_qua").val($("#workTarget").val());
	}else{
		$("#yearTarget_qua").val("");
	}
	if(""!=$("#workContent").val()){
		$("#yearContent").val($("#workContent").val());
	}else{
		$("#yearContent").val("");
	}
	if(""!=$("#search_statusCd").val()){
		$("#status").val($("#search_statusCd").val());
	}else{
		$("#status").val("");
	}
	jumpPageYear("1");
}
//更新月计划里的年计划数据
function updateYearForMonth(){
	$.post(_ctx+"/plan/plan-target!updateYearForMonth.action",function(result){
		if(result){
			alert("ok");
		}
	});
}
//新增年计划
function newYearPlan(){
	var display = $(".newYearPlan").css("display");
	if(display == "none") {
		$(".newYearPlan").show();
	} else {
		$(".newYearPlan").hide();
	} 
}
//新增年计划
function doYearSubmit(){
	var ids=preyearIds('point');
	if(""!=ids){
		var url =_ctx+"/plan/plan-target!saveyear.action?targetId=" + ids;
		$("#newSequenceNumber").val($("#sequenceNumber").val());
		$("#newYearTarget").val($("#yearTarget").val());
		$("#newContent").val($("#content").val());
		$("#newTargetYear").val($("#year").val());
		$("#newYearPlan").attr("action", url).submit();
	}
	
}
//取得年计划选择的ID值
function preyearIds(nameId) {
	var cbxs = $('input[type="checkbox"][name="'+nameId+'"]:checked');
	if(cbxs.size() == 0) {
		alert("您没有选择要操作的月份！");
		return "";
	}
	var ids="";
	for(var i=0;i<cbxs.size();i++){
		if(i==(cbxs.size()-1))
		    ids+=cbxs[i].value;
		else
			ids+=cbxs[i].value+",";
	}
	return ids;
}
function doYearDetail(planTargetId){
	if(lastPlanTargetId!=planTargetId){
		$("#"+planTargetId).next().show();
		if(""!=lastPlanTargetId){
			$("#"+lastPlanTargetId).next().hide();
		}
		lastPlanTargetId=planTargetId;
	}else{
		$("#"+planTargetId).next().hide();
		lastPlanTargetId="";
	}
}
//修改年计划
function doSaveYearTarget(planTargetId){
	var ids=preyearIds('month_'+planTargetId);
	if(""!=ids){
		var url =_ctx+"/plan/plan-target!saveyear.action?targetId=" + ids;
		$("#id").val(planTargetId);
		$("#newSequenceNumber").val($("#sequenceNumber_"+planTargetId).val());
		$("#newYearTarget").val($("#yearTarget_"+planTargetId).val());
		$("#newContent").val($("#content_"+planTargetId).val());
		$("#newTargetYear").val($("#year").val());
		$("#newYearPlan").attr("action", url).submit();
	}
}
//删除年计划
function doDelYearTarget(planTargetId){
	if(confirm("你要删除这个工作计划吗？")){
		var url =_ctx+"/plan/plan-target!delyear.action?id=" + planTargetId;
		$.post(url,function(result){
			if(result){
				jumpPageYear("1");
			}
		});
	}
}