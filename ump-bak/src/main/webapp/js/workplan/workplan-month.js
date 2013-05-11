//新增的保存
function addSaveScheduleMonth(dataId){
	if($("#new_content").val()==""){
		alert("请输入内容 ");
		return false;
	}
	$("#newScheForm input[name='planYear']").val(NOW_YEAR);
	$("#newScheForm input[name='planMonth']").val(NOW_MONTH);
	$("#newScheForm input[name='serialNumber']").val($("#new_serialNumber").val());
	$("#newScheForm input[name='serialOrder']").val($("#new_serialOrder").val());
	$("#newScheForm input[name='content']").val($("#new_content").val());
	//$("#newScheForm input[name='levelCd']").val($("#new_levelCd").val());
	$("#newScheForm input[name='weightPoint']").val($("#new_weightPoint").val());
	$("#newScheForm input[name='newMessage']").val($("#new_newMessage").val());
	
	//var param = {deptCd:dataId,respDeptCds:dataId};
	$("#newScheForm").attr("action",_ctx+"/plan/workplan-month!save.action");
	$("#newScheForm").ajaxSubmit(function(result) {
		if (result) {
			centerClickMonth();
		}
	});
}
//修改记录
function saveWorkplanMonth(workplanId,statusCd){
	$("#scheForm_"+workplanId+" input[name='serialOrder']").val($("#serialOrder_input_"+workplanId).val());
	$("#scheForm_"+workplanId+" input[name='content']").val($("#content_input_"+workplanId).val());
	$("#scheForm_"+workplanId+" input[name='weightPoint']").val($("#weightPoint_input_"+workplanId).val());
	$("#scheForm_"+workplanId+" input[name='selfPoint']").val($("#selfPoint_input_"+workplanId).val());
	$("#scheForm_"+workplanId+" input[name='resultPoint']").val($("#resultPoint_input_"+workplanId).val());
	//$("#scheForm_"+workplanId+" input[name='levelCd']").val($("#levelCd_input_"+workplanId).val());
//	if(null!=$("#targetDate_input_"+workplanId).val() && ""!=$("#targetDate_input_"+workplanId).val()){
//		$("#scheForm_"+workplanId+" input[name='targetDate']").val($("#targetDate_input_"+workplanId).val());
//	}
	if(null!=statusCd && ""!=statusCd){
		$("#scheForm_"+workplanId+" input[name='statusCd']").val(statusCd);
	}
	$("#scheForm_"+workplanId).ajaxSubmit(function(result) {
		if (null!=result && ""!=result) {
			var succMsg = "保存成功";
			switch(statusCd){
				case 0:
					succMsg = "取消成功";
					break;
				case 1:
					succMsg = "确认成功";
					break;
				case 2:
					succMsg = "预完成成功";
					break;
				case 3:
					succMsg = "完成成功";
					break;
				case 4:
					succMsg = "删除成功";
					break;
				case 5:
					succMsg = "隐藏成功";
					break;
			}
			$("#succInfoMsg").html(succMsg).show().fadeOut(2000);
			updateTdStatusHtml(workplanId,statusCd);
			refreshDetailMonth(workplanId);
		}
	});
	$("#pop_bg").show();
}
function refreshDetailMonth(planId){
	var target=$("#detailMonth_"+planId);
	$.get(_ctx+"/plan/workplan-month!fetchDetail.action?id=" + planId, function(result) {
		if (result) {
			target.html(result);
			$("#pop_bg").hide();
		}
	}); 
}
function updateSerialOrderMonth(itemId) {
	var inputEle = $("#serialOrder_show_" + itemId);
	var inputHide = $("#serialOrder_hide_" + itemId);
	var inputInp = $("#serialOrder_input_" + itemId);
	var inputSpan = $("#serialOrder_span_" + itemId);
	
	var oldVal = $.trim(inputSpan.text());
	var newVal = $.trim(inputInp.val());
	if (oldVal != newVal) {
		updateRecordMonth(itemId, "serialOrder", newVal, 'workplan-month!saveSerialOrder.action');
		inputSpan.html(newVal);
		inputEle.show();
		inputHide.hide();
	} else {
		inputEle.show();
		inputHide.hide();
	}
}
function updateContentMonth(itemId) {
	var inputEle = $("#content_show_" + itemId);
	var inputHide = $("#content_hide_" + itemId);
	var inputInp = $("#content_input_" + itemId);
	
	var oldVal = $.trim(inputEle.text());
	var newVal = $.trim(inputInp.val());
	if (oldVal != newVal) {
		updateRecordMonth(itemId, "content", newVal, 'workplan-month!saveContent.action');
		inputEle.html(newVal).show();
		inputHide.hide();
	} else {
		inputEle.show();
		inputHide.hide();
	}
}
function updateWeightPoint(itemId) {
	var inputEle = $("#weightPoint_show_" + itemId);
	var inputHide = $("#weightPoint_hide_" + itemId);
	var inputInp = $("#weightPoint_input_" + itemId);
	
	var oldVal = $.trim(inputEle.text());
	var newVal = $.trim(inputInp.val());

	if (newVal != "") {
		inputInp.val("");
		inputHide.hide();
		inputEle.html(newVal.substr(newVal.indexOf("-") + 1)).show();
		updateRecordMonth(itemId, "weightPoint", newVal, 'workplan-month!saveWeightPoint.action');
	}
}
function updateSelfPoint(itemId) {
	if(float($("#selfPoint_input_" + itemId).val()) > float($("#weightPoint_input_" + itemId).val())){
		alert("自评分不能大于权重分!");
		return;
	}
	
	var inputEle = $("#selfPoint_show_" + itemId);
	var inputHide = $("#selfPoint_hide_" + itemId);
	var inputInp = $("#selfPoint_input_" + itemId);
	
	var oldVal = $.trim(inputEle.text());
	var newVal = $.trim(inputInp.val());

	if (newVal != "") {
		inputInp.val("");
		inputHide.hide();
		inputEle.html(newVal.substr(newVal.indexOf("-") + 1)).show();
		updateRecordMonth(itemId, "selfPoint", newVal, 'workplan-month!saveSelfPoint.action');
	}
}
function updateResultPoint(itemId) {
	if(float($("#selfPoint_input_" + itemId).val()) > float($("#weightPoint_input_" + itemId).val())){
		alert("考评分不能大于权重分!");
		return;
	}
	
	var inputEle = $("#resultPoint_show_" + itemId);
	var inputHide = $("#resultPoint_hide_" + itemId);
	var inputInp = $("#resultPoint_input_" + itemId);
	
	var oldVal = $.trim(inputEle.text());
	var newVal = $.trim(inputInp.val());

	if (newVal != "") {
		inputInp.val("");
		inputHide.hide();
		inputEle.html(newVal.substr(newVal.indexOf("-") + 1)).show();
		updateRecordMonth(itemId, "resultPoint", newVal, 'workplan-month!saveResultPoint.action');
	}
}
function updateStatusCdMonth(itemId,statusCd) {
	var sourceStatusCd = $("#scheForm_"+itemId+" input[name='statusCd']").val();
	var targetStatusCd = 0;
	if(null!=statusCd){
		targetStatusCd = statusCd;
		updateRecordMonth(itemId, "statusCd", targetStatusCd, 'workplan-month!saveStatusCd.action');
		updateTdStatusHtml(itemId, targetStatusCd);
	}else{
		//记录列里直接点击图标进行确定/未确定
		if (0==sourceStatusCd || 1==sourceStatusCd) {
			if(0==sourceStatusCd){
				targetStatusCd = 1;
			}else if(1==sourceStatusCd){
				targetStatusCd = 0;
			}
			updateRecordMonth(itemId, "statusCd", targetStatusCd, 'workplan-month!saveStatusCd.action');
			updateTdStatusHtml(itemId, targetStatusCd);
		} else {
			scheClick(itemId);
		}
	}
}
function saveMessageMonth(planId){
	var content =$("#"+planId+"_message").val();
	if($.trim(content).length>0){
		$.post(_ctx+"/plan/workplan-month!saveMessage.action",
				{
			     id:planId,
			     content:content
				},
				function(result) {
					if (result == "ok") {
						refreshContentMonth(planId);
						$("#"+planId+"_message").val("");
					}else if(result =="no"){
					}
				});
	}
}
function refreshContentMonth(planId){
	var target=$("#"+planId+"_messageDiv");
	$.get(_ctx+"/plan/workplan-month!fetchContent.action?id=" + planId, function(result) {
		if (result) {
			target.html(result);
			$("#pop_bg").hide();
		}
	}); 
}
//根据点击的部门，刷新数据列表
var NOW_ORGNAME = "";
var NOW_ORGCD = "";
var NOW_SHORTORGNAME = "";
var NOW_PARENT_ORGCD = "";
var NOW_PARENT_SHORTORGNAME = "";
var IF_IN_LOADING = false;	//是否正在部门的切换中，如果是，屏蔽切换部门的动作
function centerClickMonth(orgCd,parentOrgCd,shortOrgName,parentShortOrgName){
	if(!IF_IN_LOADING){
		IF_IN_LOADING = true;
		if(null!=orgCd && ""!=orgCd){
			NOW_ORGCD = orgCd;
		}
		if(null!=parentOrgCd && ""!=parentOrgCd){
			NOW_PARENT_ORGCD = parentOrgCd;
		}
		if(null!=shortOrgName){
			NOW_SHORTORGNAME = shortOrgName;
		}
		if(null!=parentShortOrgName){
			NOW_PARENT_SHORTORGNAME = parentShortOrgName;
		}
		var page_no = 1;
		try{
			if(1==$("#if_search_all").val()){
				if($("#pageNo")){
					page_no = Number($("#pageNo").val());
				}
			}
			if(0==page_no){
				page_no = 1;
			}
		}catch(e){}
		var data = {
				now_orgCd : NOW_ORGCD,
				now_parent_orgCd : NOW_PARENT_ORGCD,
				now_shortOrgName : NOW_SHORTORGNAME,
				now_parent_shortOrgName : NOW_PARENT_SHORTORGNAME,
				"page.pageNo" : page_no,
				filter_EQs_planYear : $("#filter_EQs_planYear").val(),
				filter_EQs_planMonth : $("#filter_EQs_planMonth").val(),
				filter_LIKES_content : $("#filter_LIKES_content").val(),
				search_statusCd : $("#search_statusCd").val(),
				filter_GED_endDate : $("#filter_GED_endDate").val(),
				filter_LTD_endDate : $("#filter_LTD_endDate").val(),
				if_search_all : $("#if_search_all").val(),
				orderStr1 : $("#orderStr1").val(),
				orderStr2 : $("#orderStr2").val(),
				orderDir1 : $("#orderDir1").val(),
				orderDir2 : $("#orderDir2").val()
		};
		$("#scheduleTaskDiv").html("<div style='height:100px'></div><table width='100%'><tr><td align='center'><img src='"+_ctx+"/images/loading.gif'/></td></tr></table>");
		$.post(_ctx+"/plan/workplan-month!list.action", data, function(result) {
			$("#scheduleTaskDiv").html(result);
			IF_IN_LOADING = false;
		});
	}
}
//清空所有搜索选项
function clearAllSearchMonth(){
	$("#filter_LIKES_content").val("");
	$("#search_statusCd").val("");
	$("#filter_GED_endDate").val("");
	$("#filter_LTD_endDate").val("");
}
// 异步保存记录信息
function updateRecordMonth(itemId, type, newVal, url, dom) {
	var param = null;
	var msg = "";
	
	switch (type) {
		case "serialOrder" :
			param = {"id" : itemId, "serialOrder" : newVal};
			msg = "顺序修改成功";
			break;
		case "content" :
			param = {"id" : itemId, "content" : newVal};
			msg = "内容修改成功";
			break;
		case "weightPoint" :
			param = {"id" : itemId, "targetDate" : newVal};
			msg = "权重分修改成功";
			break;
		case "selfPoint" :
			param = {"id" : itemId, "targetDate" : newVal};
			msg = "自评分修改成功";
			break;
		case "resultPoint" :
			param = {"id" : itemId, "targetDate" : newVal};
			msg = "考评分修改成功";
			break;
		case "levelCd" :
			param = {"id" : itemId, "levelCd" : newVal};
			msg = "重要性修改成功";
			break;
		case "targetDate" :
			param = {"id" : itemId, "targetDate" : newVal};
			msg = "目标日期修改成功";
			break;
		case "statusCd" :
			param = {"id" : itemId, "statusCd" : newVal};
			switch(newVal){
				case 0:
					msg = "未确认成功";
					break;
				case 1:
					msg = "确认成功";
					break;
				case 2:
					msg = "预完成成功";
					break;
				case 3:
					msg = "完成成功";
					break;
				case 4:
					msg = "删除成功";
					break;
				case 5:
					msg = "隐藏成功";
					break;
			}
			break;
		case "preComplete" :
			param = {"id" : itemId, "status" : newVal};
			msg = "已设置为预完成状态";
			break;
		case "complete" :
			param = {"id" : itemId, "status" : newVal};
			msg = "已设置为完成状态";
			break;
		case "delete" :
			if (confirm("确认删除？")) {
				param = {"id" : itemId, "isDeleted" : newVal};
				msg = "已删除";
				$("#" + itemId + "item").next().remove();
				$("#" + itemId + "item").remove();
			}
			break;
		case "hide" :
			param = {"id" : itemId, "hiddenFlg" : newVal};
			msg = "已设置为隐藏";
			$("#" + itemId + "item").next().remove();
			$("#" + itemId + "item").remove();
			break;
		case "show" :
			param = {"id" : itemId, "hiddenFlg" : newVal};
			msg = "已设置为被显示";
			break;
	}
	if (param) {
		$.post(url, param, function(result) {
			$("#succInfoMsg").html(msg).show().fadeOut(2000);
			$("#scheForm_"+itemId+" input[name='recordVersion']").val(result);
		});
	}
}
function doClickOrderMonth2(orderStr2_id){
	var order_str2 = $("#orderStr2").val();
	if(order_str2!=orderStr2_id){
		$("#orderStr2").val(orderStr2_id);
		$("#orderDir2").val("ASC");
		$("#order_"+orderStr2_id).html("<img src='"+_ctx+"/images/plan/btn_up_10_10.gif'/>");
		switch (orderStr2_id) {
			case "targetDate" :
				$("#order_updatedDate").html("");
				break;
			case "updatedDate" :
				$("#order_targetDate").html("");
				break;
			case "statusCd" :
				$("#order_statusCd").html("");
				break;
		}
	}else{
		var order_dir2 = $("#orderDir2").val();
		switch (order_dir2) {
			case "" :
				$("#orderStr2").val(orderStr2_id);
				$("#orderDir2").val("ASC");
				$("#order_"+orderStr2_id).html("<img src='"+_ctx+"/images/plan/btn_up_10_10.gif'/>");
				break;
			case "ASC" :
				$("#orderStr2").val(orderStr2_id);
				$("#orderDir2").val("DESC");
				$("#order_"+orderStr2_id).html("<img src='"+_ctx+"/images/plan/btn_down_10_10.gif'/>");
				break;
			case "DESC" :
				$("#orderStr2").val("");
				$("#orderDir2").val("");
				$("#order_"+orderStr2_id).html("");
				break;
		}
	}
	if(!IF_IN_LOADING){
		setTimeout("IF_IN_LOADING=false;centerClickMonth();",1000);
		IF_IN_LOADING = true;
	}
}
//批量操作
function doUpdateStatusAllMonth(statusCd){
	var checkbox_ids = new Array();
	var checkbox_chkIds = new Array();
	$("input[type=checkbox][id='chk_all']:checked").each(function(i, dom) {
		checkbox_ids.push($(dom).val());
		checkbox_chkIds.push("chkIds=" + $(dom).val());
	});
	if(checkbox_ids.length == 0){
		alert("请勾选需要操作的记录");
		return false;
	}
	var param = checkbox_chkIds.join("&");
	param = param +"&statusCd="+statusCd;
	$.post(_ctx+"/plan/workplan-month!doUpdateStatusAll.action", param, function(result) {
		if(result == "error"){
			alert("操作失败！");
			return;
		}
		if (result == "done") {
			$("#succInfoMsg").html("操作成功").show().fadeOut(2000);
			for(var i=0;null!=checkbox_ids && i<checkbox_ids.length;i++){
				updateTdStatusHtml(checkbox_ids[i],statusCd);
			}
			centerClickMonth();
		}
	});
}
//交换顺序
function doExchangeOrderMonth(){
	var checkbox_ids = new Array();
	var checkbox_chkIds_ids = new Array();
	var checkbox_orders = new Array();
	var checkbox_chkIds_orders = new Array();
	$("input[type=checkbox][id='chk_all']:checked").each(function(i, dom) {
		checkbox_ids.push($(dom).val());
		checkbox_chkIds_ids.push("chkIds=" + $(dom).val());
		checkbox_orders.push($("#serialOrder_input_"+$(dom).val()).val());
		checkbox_chkIds_orders.push("chkOrders=" + $("#serialOrder_input_"+$(dom).val()).val());
	});
	if(checkbox_ids.length != 2){
		alert("请选择2条记录进行交换顺序");
		return false;
	}
	//交换顺序
	var changeTemp = checkbox_orders[0];
	checkbox_orders[0] = checkbox_orders[1];
	checkbox_orders[1] = changeTemp;
	changeTemp = checkbox_chkIds_orders[0];
	checkbox_chkIds_orders[0] = checkbox_chkIds_orders[1];
	checkbox_chkIds_orders[1] = changeTemp;
	
	var param = checkbox_chkIds_ids.join("&");
	var param2 = checkbox_chkIds_orders.join("&");
	$.post(_ctx+"/plan/workplan-month!doExchangeOrder.action", param+"&"+param2, function(result) {
		if(result == "error"){
			alert("交换顺序失败！");
			return;
		}
		if (result == "done") {
			$("#succInfoMsg").html("交换顺序成功").show().fadeOut(2000);
			$("#serialOrder_input_"+checkbox_ids[0]).val(checkbox_orders[0]);
			$("#serialOrder_input_"+checkbox_ids[1]).val(checkbox_orders[1]);
			$("#serialOrder_show_"+checkbox_ids[0]).html($("#scheForm_"+checkbox_ids[0]+" input[name='serialNumber']").val()+"-"+checkbox_orders[0]);
			$("#serialOrder_show_"+checkbox_ids[1]).html($("#scheForm_"+checkbox_ids[1]+" input[name='serialNumber']").val()+"-"+checkbox_orders[1]);
		}
	});
}
//点击全选按钮
function checkedAllMonth(flag){
	$("input[id='chk_all']").attr("checked",flag);
}
var oldMonthId = "";
function scheClickMonth(scheId){
	if(CAN_scheClick){
		var detailObj = document.getElementById("detailMonth_"+scheId);
		if(detailObj.style.display == "none"){
			if(oldMonthId!="" && oldMonthId!=scheId){
				try{
					$("#detailMonth_"+oldMonthId).hide();
					$("#mainMonth_"+oldMonthId).css("backgroundColor","#fff");
					$("#mainMonth_"+oldMonthId+" td").css("color","#161616");
				}catch(e){}
			}
			$(detailObj).show();
			$("#down_arrow_"+scheId).hide();
			$("#up_arrow_"+scheId).show();
			$("#mainMonth_"+scheId).css("backgroundColor","#eee");
			$("#mainMonth_"+scheId+" td").css("color","#40a3de");
			$("#content_show_"+scheId).removeClass("ellipsisDiv_full");
			$("#content_show_"+scheId).css("height","auto");
		}else{
			$(detailObj).hide();
			$("#down_arrow_"+scheId).show();
			$("#up_arrow_"+scheId).hide();
			if("none"!=document.getElementById("content_hide_"+scheId).style.display){
				document.getElementById("content_hide_"+scheId).style.display = "none";
				document.getElementById("content_show_"+scheId).style.display = "block";
			}
			$("#mainMonth_"+scheId).css("backgroundColor","#fff");
			$("#mainMonth_"+scheId+" td").css("color","#161616");
			$("#content_show_"+scheId).addClass("ellipsisDiv_full");
			$("#content_show_"+scheId).css("height","35px");
		}
		oldMonthId =scheId;
	}else{
		CAN_scheClick = true;
	}
}
//操作当前的年月，addMonthNumber为前进或者后退的月份数
function operateYearMonth(addMonthNumber){
	var nowYear = Number($("#filter_EQs_planYear").val());
	var nowMonth = Number($("#filter_EQs_planMonth").val());
	var toYear = nowYear;
	var toMonth = nowMonth + addMonthNumber;
	if(toMonth<1){
		toYear--;
		toMonth=12;
	}else if(toMonth>12){
		toYear++;
		toMonth=1;
	}
	$("#filter_EQs_planYear").val(toYear);
	$("#filter_EQs_planYear").val(toMonth);
}

