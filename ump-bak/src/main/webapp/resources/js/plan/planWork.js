var can_edit_record = true;	//能否对记录进行编辑。如果是管理员，则能编辑；如果是普通用户，则在自己中心内的才能编辑.
var opened_td_id = "";	//当前打开的记录的ID，如果点了其他行，那么关闭当前打开的行
var now_year = 0;	//当前的年份
var now_month_of_year = 0;	//当先的周数
var centerCd = "";	//当先的中心cd
var IF_IN_LOADING = false;	//是否正在部门的切换中，如果是，屏蔽切换部门的动作
var CAN_toggleDetail = true;	//是否能toggleDetail,用来屏蔽输入框的父对象的点击事件
var oldId="";	//上次打开的详细信息的Id
var CAN_scheClick = true;	//是否能scheClick,用来屏蔽输入框的父对象的点击事件
var isEditOrg = 0;	//是否是当前用户所在部门，用于更新记录判断是否有权限
var IF_HISTORY = false;	//是否正在查看历史
var myTask = "";	//是否从邮件报告中进入，如果是搜索我的记录
function newSchedule(){
	$("#newScheTemplate1").show();
	$("#newScheTemplate2").show();
	$("#monthScheSave").show();
}
function cancelSchedule(){
	$("#monthScheSave").hide();
	$("#newScheTemplate1").hide();
	$("#newScheTemplate2").hide();
}
//切换部门的动作
function centerClick(search_year,search_planMonth,search_centerCd){
	if(!IF_IN_LOADING){
		IF_IN_LOADING = true;
		if(null!=search_year && ""!=search_year){
			now_year = search_year;
		}
		if(null!=search_planMonth && ""!=search_planMonth){
			now_month_of_year = search_planMonth;
		}
		if(null!=search_centerCd && ""!=search_centerCd){
			centerCd = search_centerCd;
		}
		var page_no = 1;
		try{
			if($("#pageNo") && undefined!=$("#pageNo").val()){
				page_no = Number($("#pageNo").val());
			}
			if(0==page_no){
				page_no = 1;
			}
		}catch(e){page_no=1;}
		if(null==page_size || ""==page_size){
			page_size = 15;
		}
		var data = {
				myTask : myTask,
				opened_entityid : opened_entityid,
				if_in_attention : if_in_attention,
				centerCd : centerCd,
				now_year : now_year,
				now_month_of_year : now_month_of_year,
				"page.pageNo" : page_no,
				"page.pageSize" : page_size,
				filter_LIKES_content : $("#filter_LIKES_content").val(),
				search_statusCd : $("#search_statusCd").val(),
				if_search_all : $("#if_search_all").val(),
				orderStr1 : $("#orderStr1").val(),
				orderStr2 : $("#orderStr2").val(),
				orderDir1 : $("#orderDir1").val(),
				orderDir2 : $("#orderDir2").val()
		};
		$("#scheduleDiv").html("<div style='height:100px'></div><table width='100%'><tr><td align='center'><img src='"+_ctx+"/images/loading.gif'/></td></tr></table>");
		$.post(_ctx+"/plan/plan-work!list.action", data, function(result) {
			$("#scheduleDiv").html(result);
			IF_IN_LOADING = false;
			IF_HISTORY = false;
		});
	}
}
//搜索历史记录
function searchHistory(){
	if(!IF_IN_LOADING){
		IF_IN_LOADING = true;
		var search_year = $("#search_year").val();
		var search_planMonth = $("#search_planMonth").val();
		var search_centerCd = $("#search_centerCd").val();
		var page_no = 1;
		try{
			if($("#pageNo") && undefined!=$("#pageNo").val()){
				page_no = Number($("#pageNo").val());
			}
			if(0==page_no){
				page_no = 1;
			}
		}catch(e){page_no=1;}
		var data = {
				centerCd : search_centerCd,
				planYear : search_year,
				planMonth : search_planMonth,
				"pageHistory.pageNo" : page_no
		};
		$("#scheduleDiv").html("<div style='height:100px'></div><table width='100%'><tr><td align='center'><img src='"+_ctx+"/images/loading.gif'/></td></tr></table>");
		$.post(_ctx+"/plan/plan-work!listHistory.action", data, function(result) {
			$("#scheduleDiv").html(result);
			IF_IN_LOADING = false;
			IF_HISTORY = true;
		});
	}
}
//确认事件
function doConfirm(){
	var data={centerCd:centerCd};
	$.post(_ctx+"/plan/plan-work!confirm.action",data, function(result) {
		if (result=="success"){
			ymPrompt.succeedInfo({message:"归档成功",title:"提示",width:220,height:180});
		}else{
			ymPrompt.alert({message:result,slideShowHide:false,title:"提示",width:220,height:180});
		}
	});
}
//解锁事件
function doUnConfirm(){
	var data={centerCd:centerCd};
	$.post(_ctx+"/plan/plan-work!unConfirm.action",data, function(result) {
		if (result=="success"){
			ymPrompt.succeedInfo({message:"解锁成功",title:"提示",width:220,height:180});
		}else{
			ymPrompt.alert({message:result,slideShowHide:false,title:"提示",width:220,height:180});
		}
	});
}
//切换中心事件
function doChangeCenter(dom){
	centerCd=$(dom).val();
	$("#pageNo").val(0);
	if (!isEmpty(centerCd)){
		if(IF_HISTORY){
			searchHistory();
		}else{
			centerClick();
		}
	}
}
//页面跳转函数的覆盖
function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	centerClick();
}
//新增的保存
function addSaveSchedule(dataId){
	if($("#new_content").val()==""){
		alert("请输入内容 ");
		return false;
	}
	if($("#new_targetDate").val()==""){
		//alert("请输入目标时间  ");
		//return false;
	}
	try{
		$("#newScheForm input[name='targetPointCd']").val($("#new_targetPointCd").val());
		$("#newScheForm input[name='area']").val($("#new_area").val());
	}catch(e){}
	$("#newScheForm input[name='content']").val($("#new_content").val());
	//$("#newScheForm input[name='principal']").val($("#new_principal").val());
	$("#newScheForm input[name='targetDate']").val($("#new_targetDate").val());
	$("#newScheForm input[name='newMessage']").val($("#new_newMessage").val());
	//$("#newScheForm input[name='levelCd']").val($("#new_levelCd").val());
	var now_serialOrder = $("#newScheForm input[name='serialOrder']").val();	//现在的序列号
	$("#newScheForm input[name='orgCd']").val(centerCd);
	
	var param = {deptCd:dataId,respDeptCds:dataId};
	$("#newScheForm").attr("action",_ctx+"/plan/plan-work!save.action");
	$("#newScheForm").ajaxSubmit(function(result) {
		if (result) {
			now_serialOrder++;	//新增成功，序列号加1
			var new_record_html = '<tr id="main_'+result+'" class="mainTr" style="cursor:pointer;"><td colspan="8"></td></tr>'
				+'<tr id="detail_'+result+'" class="detailTr" style="display:none;"><td colspan="8"></td></tr>';
			$("#newScheTemplate2").after(new_record_html);
			refreshMain(result);
			refreshDetail(result);
			$("#newScheForm input[name='serialOrder']").val(now_serialOrder);
			$("#newScheForm input[name='content']").val("");
			//$("#newScheForm input[name='principal']").val("");
			$("#newScheForm input[name='targetDate']").val("");
			$("#newScheForm input[name='newMessage']").val("");
			$("#new_content").val("");
			//$("#new_principal").val("1");
			$("#new_targetDate").val("");
			$("#new_newMessage").val("");
			$("#new_img_atta").attr("src",_ctx+"/resources/images/common/atta.gif");
			cancelSchedule();
			try{
				$("#noResult_td").html("");
			}catch(e){}
		}
	});
	$("#pop_bg").show();
}
//修改记录
function savePlanWork(planWorkId,statusCd){
	try{
		$("#scheForm_"+planWorkId+" input[name='targetPointCd']").val($("#targetPointCd_hide_"+planWorkId).children(0).val());
		$("#scheForm_"+planWorkId+" input[name='area']").val($("#area_input_"+planWorkId).val());
	}catch(e){}
	$("#scheForm_"+planWorkId+" input[name='newMessage']").val($("#"+planWorkId+"_message").val());
	$("#scheForm_"+planWorkId+" input[name='serialOrder']").val($("#serialOrder_input_"+planWorkId).val());
	$("#scheForm_"+planWorkId+" input[name='content']").val($("#content_input_"+planWorkId).val());
	if(null!=$("#targetDate_input_"+planWorkId).val() && ""!=$("#targetDate_input_"+planWorkId).val()){
		$("#scheForm_"+planWorkId+" input[name='targetDate']").val($("#targetDate_input_"+planWorkId).val());
	}
	if(null!=statusCd && ""!=statusCd){
		$("#scheForm_"+planWorkId+" input[name='statusCd']").val(statusCd);
	}
	$("#scheForm_"+planWorkId).ajaxSubmit(function(result) {
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
					succMsg = "申请删除成功";
					break;
				case 3:
					succMsg = "完成成功";
					break;
				case 4:
					succMsg = "隐藏成功";
					break;
				case 5:
					succMsg = "删除成功";
					break;
			}
			//$("#scheduleTaskDiv").html(result);
			$("#succInfoMsg").html(succMsg).show().fadeOut(2000);
			updateTdStatusHtml(planWorkId,statusCd);
			//updateTdrecordVersions(planWorkId,result);
			//centerClick();
			refreshMain(planWorkId);
			refreshDetail(planWorkId);
		}
	});
	$("#pop_bg").show();
}
//刷新某条记录的主体部分
function refreshMain(planId){
	var isAttentioned = $("#scheForm_"+planId+" input[name='isAttentioned']").val();
	var target=$("#main_"+planId);
	$.get(_ctx+"/plan/plan-work!fetchMain.action?id=" + planId+"&centerCd="+centerCd+"&now_orgCd="+centerCd+"&isEditOrg="+isEditOrg+"&isAttentioned="+isAttentioned+"&if_in_attention="+if_in_attention, function(result) {
		if (result) {
			target.html(result);
		}
	}); 
}
//刷新某条记录的展开部分
function refreshDetail(planId){
	var isAttentioned = $("#scheForm_"+planId+" input[name='isAttentioned']").val();
	var target=$("#detail_"+planId);
	$.get(_ctx+"/plan/plan-work!fetchDetail.action?id=" + planId+"&centerCd="+centerCd+"&now_orgCd="+centerCd+"&isEditOrg="+isEditOrg+"&isAttentioned="+isAttentioned, function(result) {
		if (result) {
			target.html(result);
			$("#pop_bg").hide();
		}
	}); 
}
//异步保存记录信息
function updateRecord(planWorkId, type, newVal) {
	var param = null;
	var msg = "";
	var oldVal = "";
	try{
		oldVal = $("#scheForm_"+planWorkId+" input[name='"+type+"']").val();
	}catch(e){}
	if(oldVal==newVal){
		return;
	}
	if("targetDate"==type && (""==newVal||null==newVal)){
		return;
	}
	
	switch (type) {
		case "serialOrder" :
			param = {"id" : planWorkId, "serialOrder" : newVal};
			msg = "顺序修改成功";
			break;
		case "content" :
			param = {"id" : planWorkId, "content" : newVal};
			msg = "内容修改成功";
			break;
		case "principal" :
			param = {"id" : planWorkId, "principal" : newVal};
			msg = "负责人修改成功";
			break;
		case "targetDate" :
			param = {"id" : planWorkId, "targetDate" : newVal};
			msg = "目标日期修改成功";
			break;
		case "area" :
			param = {"id" : planWorkId, "area" : newVal};
			msg = "地区修改成功";
			break;
		case "statusCd" :
			param = {"id" : planWorkId, "statusCd" : newVal};
			switch(newVal){
				case 0:
					msg = "未确认成功";
					break;
				case 1:
					msg = "确认成功";
					break;
				case 2:
					msg = "申请删除成功";
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
				case 6:
					msg = "预完成成功";
					break;
			}
			break;
		case "delete" :
			if (confirm("确认删除？")) {
				param = {"id" : planWorkId, "isDeleted" : newVal};
				msg = "已删除";
				$("#" + planWorkId + "item").next().remove();
				$("#" + planWorkId + "item").remove();
			}
			break;
		case "hide" :
			param = {"id" : planWorkId, "hiddenFlg" : newVal};
			msg = "已设置为隐藏";
			$("#" + planWorkId + "item").next().remove();
			$("#" + planWorkId + "item").remove();
			break;
		case "show" :
			param = {"id" : planWorkId, "hiddenFlg" : newVal};
			msg = "已设置为被显示";
			break;
	}
	if (param) {
		$.post(_ctx+"/plan/plan-work!save.action?isEditOrg="+isEditOrg, param, function(result) {
			$("#succInfoMsg").html(msg).show().fadeOut(2000);
			$("#td_updateDate_"+planWorkId).html(nowDate.substring(5,nowDate.length));
			var beforeRecordVersion = Number($("#scheForm_"+planWorkId+" input[name='recordVersion']").val());
			beforeRecordVersion++;
			$("#scheForm_"+planWorkId+" input[name='recordVersion']").val(beforeRecordVersion);
			switch (type) {
				case "content" :
					$("#content_show_"+planWorkId).html(newVal);
					break;
				case "principal" :
					refreshMain(planWorkId);
					break;
				case "targetDate" :
					$("#targetDate_show_"+planWorkId).html(newVal);
					break;
				case "area" :
					$("#area_show_"+planWorkId).html(newVal);
					break;
				case "serialOrder" :
					$("#serialOrder_show_"+planWorkId).html(newVal);
					break;
			}
		});
	}
}

//保存留言
function saveMessage(planWorkId){
	var isAttentioned = $("#scheForm_"+planWorkId+" input[name='isAttentioned']").val();
	var content =$("#"+planWorkId+"_message").val();
	if($.trim(content).length>0){
		$.post(_ctx+"/plan/plan-work!saveMessage.action",
				{
			     id:planWorkId,
			     content:content,
			     isAttentioned:isAttentioned
				},
				 function(result) {
					 if (result == "ok") {
						 //refreshContent(planWorkId);
						 refreshMain(planWorkId);
						 refreshDetail(planWorkId);
						 //$("#"+planWorkId+"_message").val("");
					 }else if(result =="no"){
						 alert("留言失败");
					 }
				 });
	}
}
//刷新留言,不用
function refreshContent(planWorkId){
	var target=$("#"+planWorkId+"_messageDiv");
	$.get(_ctx+"/plan/plan-work!fetchContent.action?id=" + planWorkId+"&isEditOrg="+isEditOrg, function(result) {
		if (result) {
			target.html(result);
			$("#pop_bg").hide();
		}
	}); 
}
//显示/隐藏细项
function toggleDetail(detail_obj,planWorkId){
	if(can_edit_record){
		if(CAN_toggleDetail){
			$(detail_obj).find('.span_show').toggle();
			$(detail_obj).find('.span_hide').toggle();
		}else{
			CAN_toggleDetail = true;
		}
		if(null!=planWorkId){
			closePrevDetail(planWorkId);
		}
	}
}
function toggleDetail2(toggleName,planWorkId){
	if(can_edit_record){
		if(CAN_toggleDetail){
			$("#"+toggleName).find('.span_show').toggle();
			$("#"+toggleName).find('.span_hide').toggle();
		}else{
			CAN_toggleDetail = true;
		}
		if(null!=planWorkId){
			closePrevDetail(planWorkId);
		}
	}
}
function hideDetail(toggleName){
	if(can_edit_record){
		if(CAN_toggleDetail){
			$("#"+toggleName).find('.span_show').show();
			$("#"+toggleName).find('.span_hide').hide();
		}else{
			CAN_toggleDetail = true;
		}
	}
}
//关闭正在打开的内容的详细条目
function closePrevDetail(scheId){
	try{
		if(""!=scheId && opened_td_id!=scheId){
			hideDetail("td_serialOrder_"+opened_td_id);
			hideDetail("td_targetPointCd_"+opened_td_id);
			hideDetail("td_area_"+opened_td_id);
			hideDetail("td_content_"+opened_td_id);
			hideDetail("td_targetDate_"+opened_td_id);
			//hideDetail("td_principal_"+opened_td_id);
			$("#chk_"+opened_td_id).css("backgroundColor","");
			$("#img_"+opened_td_id).css("backgroundColor","");
			$("#img_"+opened_td_id).css("borderRight","");
			opened_td_id = scheId;
		}
	}catch(e){}
}
//关闭上一个打开的内容的详细条目
function closePrevDetail2(scheId){
	try{
		if(""!=scheId && oldId!=scheId){
			hideDetail("td_serialOrder_"+oldId);
			hideDetail("td_targetPointCd_"+oldId);
			hideDetail("td_area_"+oldId);
			hideDetail("td_content_"+oldId);
			hideDetail("td_targetDate_"+oldId);
			//hideDetail("td_principal_"+oldId);
			$("#down_arrow_"+oldId).show();
			$("#up_arrow_"+oldId).hide();
			$("#chk_"+oldId).css("backgroundColor","");
			$("#img_"+oldId).css("backgroundColor","");
			$("#img_"+oldId).css("borderRight","");
			oldId = scheId;
		}
	}catch(e){}
}
//打开详细内容
function scheClick(scheId){
	if(CAN_scheClick){
		try{
			setAttentionRead("planWork",scheId);
		}catch(e){}
		var detailObj = document.getElementById("detail_"+scheId);
		if(detailObj.style.display == "none"){
			if(oldId!="" && oldId!=scheId){
				try{
					$("#detail_"+oldId).hide();
					$("#main_"+oldId).css("backgroundColor","#fff");
					$("#main_"+oldId+" td").css("color","#161616");
					closePrevDetail2(scheId);
				}catch(e){}
			}
			$(detailObj).show();
			$("#down_arrow_"+scheId).hide();
			$("#up_arrow_"+scheId).show();
			$("#main_"+scheId).css("backgroundColor","#eee");
			//$("#main_"+scheId+" td").css("color","#40a3de");
			$("#content_show_"+scheId).removeClass("ellipsisDiv_full");
			//$("#content_show_"+scheId).css("height","auto");
			$("#chk_"+scheId).css("backgroundColor","#e4e7ec");
			$("#img_"+scheId).css("backgroundColor","#e4e7ec");
			$("#img_"+scheId).css("borderRight","1px solid #dcdcde");
			autoHeight();
		}else{
			$(detailObj).hide();
			$("#down_arrow_"+scheId).show();
			$("#up_arrow_"+scheId).hide();
			try{
				hideDetail("td_serialOrder_"+scheId);
			}catch(e){}
			try{
				hideDetail("td_targetPointCd_"+scheId);
			}catch(e){}
			try{
				hideDetail("td_area_"+scheId);
			}catch(e){}
			try{
				hideDetail("td_content_"+scheId);
			}catch(e){}
			try{
				hideDetail("td_targetDate_"+scheId);
			}catch(e){}
			try{
				//hideDetail("td_principal_"+scheId);
			}catch(e){}
			$("#main_"+scheId).css("backgroundColor","#fff");
			//$("#main_"+scheId+" td").css("color","#161616");
			$("#content_show_"+scheId).addClass("ellipsisDiv_full");
			$("#chk_"+scheId).css("backgroundColor","");
			$("#img_"+scheId).css("backgroundColor","");
			$("#img_"+scheId).css("borderRight","");
		}
		oldId =scheId;
	}else{
		CAN_scheClick = true;
	}
}

//清空所有搜索选项
function clearAllSearch(){
	try{$("#filter_LIKES_content").val("");}catch(e){}
	try{$("#search_statusCd").val("");}catch(e){}
	try{$("#filter_GED_createdDate").val("");}catch(e){}
	try{$("#filter_LTD_createdDate").val("");}catch(e){}
	try{$("#filter_GED_targetDate").val("");}catch(e){}
	try{$("#filter_LTD_targetDate").val("");}catch(e){}
	try{$("#filter_GED_endDate").val("");}catch(e){}
	try{$("#filter_LTD_endDate").val("");}catch(e){}
	try{$("#search_year").val(now_year);}catch(e){}
	try{$("#search_planMonth").val(now_month_of_year);}catch(e){}
}
function doClickOrder1(){
	var order_dir1 = $("#orderDir1").val();
	switch (order_dir1) {
		case "" :
			$("#orderStr1").val("serialOrder");
			$("#orderDir1").val("ASC");
			$("#order_serialOrder").html("<img src='"+_ctx+"/images/plan/btn_up_10_10.gif'/>");
			break;
		case "ASC" :
			$("#orderStr1").val("serialOrder");
			$("#orderDir1").val("DESC");
			$("#order_serialOrder").html("<img src='"+_ctx+"/images/plan/btn_down_10_10.gif'/>");
			break;
		case "DESC" :
			$("#orderStr1").val("");
			$("#orderDir1").val("");
			$("#order_serialOrder").html("");
			break;
	}
	if(!IF_IN_LOADING){
		setTimeout("IF_IN_LOADING=false;centerClick();",1000);
		IF_IN_LOADING = true;
	}
}
function doClickOrder2(orderStr2_id){
	var order_str2 = $("#orderStr2").val();
	if(order_str2!=orderStr2_id){
		$("#orderStr2").val(orderStr2_id);
		$("#orderDir2").val("ASC");
		$("#order_"+orderStr2_id).html("<img src='"+_ctx+"/images/plan/btn_up_10_10.gif'/>");
		switch (orderStr2_id) {
			case "createdDate" :
				$("#order_createdDate").html("");
			break;
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
		setTimeout("IF_IN_LOADING=false;centerClick();",1000);
		IF_IN_LOADING = true;
	}
}
//批量操作
function doUpdateStatusAll(statusCd){
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
	$.post(_ctx+"/plan/plan-work!doUpdateStatusAll.action", param, function(result) {
		if(result == "error"){
			alert("操作失败！");
			return;
		}
		if (result == "done") {
			$("#succInfoMsg").html("操作成功").show().fadeOut(2000);
			for(var i=0;null!=checkbox_ids && i<checkbox_ids.length;i++){
				updateTdStatusHtml(checkbox_ids[i],statusCd);
			}
			centerClick();
		}
	});
}
function updateStatusCd(itemId,statusCd) {
	var sourceStatusCd = $("#scheForm_"+itemId+" input[name='statusCd']").val();
	var targetStatusCd = 0;
	if(null!=statusCd){
		targetStatusCd = statusCd;
		updateRecord(itemId, "statusCd", targetStatusCd);
		updateTdStatusHtml(itemId, targetStatusCd);
		$("#scheForm_"+itemId+" input[name='statusCd']").val(targetStatusCd);
	}else{
		//记录列里直接点击图标进行确定/未确定
		if (0==sourceStatusCd || 1==sourceStatusCd) {
			if(0==sourceStatusCd){
				targetStatusCd = 1;
			}else if(1==sourceStatusCd){
				targetStatusCd = 0;
			}
			updateRecord(itemId, "statusCd", targetStatusCd);
			updateTdStatusHtml(itemId, targetStatusCd);
			$("#scheForm_"+itemId+" input[name='statusCd']").val(targetStatusCd);
		} else {
			scheClick(itemId);
		}
	}
}
//更新记录的statusCd和显示出来的图标
function updateTdStatusHtml(itemId,statusCd){
	var html_str = "";
	if(null!=statusCd){
		switch(statusCd){
			case 0:
				html_str = "<img src='"+_ctx+"/images/plan/pic_noConfirm.gif' title='未确认'>";
				break;
			case 1:
				html_str = "<img src='"+_ctx+"/images/plan/pic_confirm.gif' title='已确认'>";
				break;
			case 2:
				html_str = "<img src='"+_ctx+"/images/plan/pic_preFinish.gif' title='申请删除'>";
				break;
			case 3:
				html_str = "<img src='"+_ctx+"/images/plan/pic_finish.gif' title='已完成'>";
				break;
			case 4:
				html_str = "已删除";
				break;
			case 5:
				html_str = "<img src='"+_ctx+"/images/plan/pic_hide.gif' title='已隐藏'>";
				break;
			case 6:
				html_str = "<img src='"+_ctx+"/images/plan/pic_preFinish.gif' title='预完成'>";
				break;
		}
		$("#scheForm_"+itemId+" input[name='statusCd']").val(statusCd);
		$("#td_statusCd_"+itemId).html(html_str);
	}
}
//交换顺序
function doExchangeOrder(){
	var checkbox_ids = new Array();
	var checkbox_chkIds_ids = new Array();
	var checkbox_orders = new Array();
	var checkbox_chkIds_orders = new Array();
	$("input[type=checkbox][id='chk_all']:checked").each(function(i, dom) {
		checkbox_ids.push($(dom).val());
		checkbox_chkIds_ids.push("chkIds=" + $(dom).val());
		checkbox_orders.push($("#serialOrder_show_"+$(dom).val()).html());
		checkbox_chkIds_orders.push("chkOrders=" + $("#serialOrder_show_"+$(dom).val()).html());
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
	$.post(_ctx+"/plan/plan-work!doExchangeOrder.action", param+"&"+param2, function(result) {
		if (result == "done") {
			$("#succInfoMsg").html("交换顺序成功").show().fadeOut(2000);
			$("#serialOrder_show_"+checkbox_ids[0]).html(checkbox_orders[0]);
			$("#serialOrder_show_"+checkbox_ids[1]).html(checkbox_orders[1]);
		}else{
			alert("交换顺序失败！");
			return;
		}
	});
}
//点击全选按钮
function checkedAll(flag){
	$("input[id='chk_all']").attr("checked",flag);
}
function openUserChooseEdit(nameId,cdId,id,fieldName){
	var config={'nameId':nameId,'cdId':cdId,callBack:function(){
		updateRecord(id,fieldName,$("#"+cdId).val());
		$("#span_principalName_"+id).html($("#principalName_"+id).val());
	}};
	getMemberJson(config);
}
var cur_entityId = null;	//附件的临时存储变量
function openAttachment(title,entityId){
	if("新增附件管理"==title){
		cur_entityId = null;
	}else{
		cur_entityId = entityId;
	}
	ymPrompt.win({
		message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=planWork&filterType=image|office&bizEntityName=PlanWork",
		width:500,
		height:300,
		title:title,
		iframe:true,
		handler : attachRefresh
		});
}
function attachRefresh(){
	if(null!=cur_entityId){
		refreshMain(cur_entityId);
	}else{
		$("#new_img_atta").attr("src",_ctx+"/resources/images/common/atta_y.gif");
	}
}
//确认事件
function doConfirm(){
	var data={centerCd:centerCd};
	$.post(_ctx+"/plan/plan-work!confirm.action",data, function(result) {
		if (result=="success"){
			ymPrompt.succeedInfo({message:"归档成功",title:"提示",width:220,height:180,handler:function(){window.location.reload();}});
		}else{
			ymPrompt.alert({message:result,slideShowHide:false,title:"提示",width:220,height:180});
		}
	});
}
//解锁事件
function doUnConfirm(){
	var data={centerCd:centerCd,planYear:now_year,planMonth:now_month_of_year};
	$.post(_ctx+"/plan/plan-work!unConfirm.action",data, function(result) {
		if (result=="success"){
			ymPrompt.succeedInfo({message:"解锁成功",title:"提示",width:220,height:180,handler:function(){window.location.reload();}});
		}else{
			ymPrompt.alert({message:result,slideShowHide:false,title:"提示",width:220,height:180});
		}
	});
}
// 提醒功能, 给负责人发信
function remind(itemId) {
	$.post(_ctx + "/plan/plan-work!remind.action?id=" + itemId, function(result) {
		if (result == "done") {
			$("#succInfoMsg").html("提醒邮件已发送").show().fadeOut(2000);
		}
	});
}