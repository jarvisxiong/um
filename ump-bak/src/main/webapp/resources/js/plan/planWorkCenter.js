var can_edit_record = false;	//能否对记录进行编辑。如果是管理员，则能编辑；如果是普通用户，则在自己中心内的才能编辑.
var opened_td_id = "";	//当前打开的记录的ID，如果点了其他行，那么关闭当前打开的行
var now_year = 0;	//当前的年份
var now_month = 0;	//当先的月份
var now_month_of_year = 0;	//当先的周数
var centerCd = "";	//当先的中心cd
var search_orgCd = "";	//搜索的中心的cds
var IF_IN_LOADING = false;	//是否正在部门的切换中，如果是，屏蔽切换部门的动作
var CAN_toggleDetail = true;	//是否能toggleDetail,用来屏蔽输入框的父对象的点击事件
var oldId="";	//上次打开的详细信息的Id
var CAN_scheClick = true;	//是否能scheClick,用来屏蔽输入框的父对象的点击事件
var isEditOrg = 0;	//是否是当前用户所在部门，用于更新记录判断是否有权限
var myTask = "";	//是否从邮件报告中进入，如果是搜索我的记录
var uiid = "";	//如果是西西，新建记录后保留负责人
var new_content = "";	//来自执行计划的新增内容
var new_targetDate = "";	//来自执行计划的新增内容
var new_targetPointCd = "";	//来自执行计划的新增内容
var new_addFromType = "0";	//来自执行计划的新增内容
var new_planExecId = "";	//来自执行计划的新增内容
function newSchedule(){
	$("#newScheTemplate1").show();
	$("#newScheTemplate2").show();
	$("#monthScheSave").show();
}
function cancelSchedule(){
	$("#new_content").val("");
	$("#new_targetDate").val("");
	$("#new_targetPointCd").val("");
	$("#newScheForm input[name='addFromType']").val("0");
	$("#newScheForm input[name='planExecId']").val("");
	try{
		$("#newScheForm input[name='principal']").val("");
		$("#new_principalCds").val("");
		$("#new_principalNames").val("");
	}catch(e){}
	try{
		$("#newScheForm input[name='workType']").val("");
	}catch(e){}
	try{
		$("#newScheForm input[name='corUser']").val("");
		$("#new_corUserCds").val("");
		$("#new_corUserNames").val("");
	}catch(e){}
	$("#new_content").attr("disabled","");
	$("#new_targetDate").attr("disabled","");
	$("#new_targetPointCd").attr("disabled","");
	$("#monthScheSave").hide();
	$("#newScheTemplate1").hide();
	$("#newScheTemplate2").hide();
}
//确认事件
function doConfirm(){
	var data={centerCd:centerCd};
	$.post(_ctx+"/plan/plan-work-center!confirm.action",data, function(result) {
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
	$.post(_ctx+"/plan/plan-work-center!unConfirm.action",data, function(result) {
		if (result=="success"){
			ymPrompt.succeedInfo({message:"解锁成功",title:"提示",width:220,height:180});
		}else{
			ymPrompt.alert({message:result,slideShowHide:false,title:"提示",width:220,height:180});
		}
	});
}
//切换中心事件
function doChangeCenter(domValue){
	centerCd = domValue;
	search_orgCd = "";
	var mytype = $("#search_centerCd option:selected").attr("mytype");
	if("center"==mytype){
		//如果是中心，搜索下属的部门
		search_orgCd = centerCd+",";
		$("select[@id=search_centerCd] option").each(function(){
			if(domValue+"_dept"==$(this).attr("mytype")){
				search_orgCd += $(this).val()+",";
			}
		});
		search_orgCd = search_orgCd.substring(0, search_orgCd.length-1);
	}else{
		search_orgCd = centerCd;
	}
	$("#pageNo").val(0);
	if (!isEmpty(search_orgCd)){
		centerClick();
	}
}
//页面跳转函数的覆盖
/**
function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	centerClick();
}
**/
//新增的保存
function addSaveSchedule(dataId){
	if($("#new_content").val()==""){
		alert("请输入内容 ");
		return false;
	}
	try{
		if($("#new_targetDate").val()==""){
			alert("请输入目标时间  ");
			return false;
		}else{
			var targetDate = $("#new_targetDate").val();
			var targetYear = Number(targetDate.substring(0,4));
			var targetMonth = Number(targetDate.substring(5,7));
			if(targetYear < now_year || targetMonth < now_month){
				alert("目标时间不可小于当前月份!");
				return false;
			}
		}
	}catch(e){}
	try{
		$("#newScheForm input[name='targetPointCd']").val($("#new_targetPointCd").val());
		$("#newScheForm input[name='area']").val($("#new_area").val());
	}catch(e){}
	$("#newScheForm input[name='content']").val($("#new_content").val());
	try{
		$("#newScheForm input[name='principal']").val($("#new_principalCds").val());
	}catch(e){}
	try{
		$("#newScheForm input[name='workType']").val($("#new_workType").val());
	}catch(e){}
	try{
		$("#newScheForm input[name='corUser']").val($("#new_corUserCds").val());
	}catch(e){}
	$("#newScheForm input[name='targetDate']").val($("#new_targetDate").val());
	$("#newScheForm input[name='newMessage']").val($("#new_newMessage").val());
	var now_serialOrder = $("#newScheForm input[name='serialOrder']").val();	//现在的序列号
	$("#newScheForm input[name='orgCd']").val(centerCd);
	
	var param = {deptCd:dataId,respDeptCds:dataId};
	$("#newScheForm").attr("action",_ctx+"/plan/plan-work-center!save.action");
	$("#newScheForm").ajaxSubmit(function(result) {
		try{
			while("success"==result.substring(0,7)||"failure"==result.substring(0,7)){
				result = result.substring(7,result.length);
			}
			while("success"==result.substring(result.length-7,result.length)
					||"failure"==result.substring(result.length-7,result.length)){
				result = result.substring(0,result.length-7);
			}
		}catch(e){}
		if (result) {
			now_serialOrder++;	//新增成功，序列号加1
			var new_record_html = '<tr id="main_'+result+'" class="mainTr" style="cursor:pointer;"><td colspan="8"></td></tr>'
				+'<tr id="detail_'+result+'" class="detailTr" style="display:none;"><td colspan="8"></td></tr>';
			$("#newScheTemplate2").after(new_record_html);
			refreshMain(result);
			refreshDetail(result);
			$("#newScheForm input[name='serialOrder']").val(now_serialOrder);
			$("#newScheForm input[name='content']").val("");
			$("#newScheForm input[name='targetDate']").val("");
			$("#newScheForm input[name='addFromType']").val("0");
			$("#newScheForm input[name='planExecId']").val("");
			$("#newScheForm input[name='newMessage']").val("");
			$("#newScheForm input[name='principal']").val("");
			$("#new_principalNames").val("");
			$("#new_principalCds").val("");
			$("#new_content").val("");
			$("#new_targetDate").val("");
			$("#new_newMessage").val("");
			$("#new_img_atta").attr("src",_ctx+"/resources/images/common/atta.gif");
			$("#new_content").attr("disabled","");
			$("#new_targetDate").attr("disabled","");
			$("#new_targetPointCd").attr("disabled","");
			cancelSchedule();
			try{
				$("#noResult_td").html("");
			}catch(e){}
		}
	});
	$("#pop_bg").show();
}
//修改记录
function savePlanWorkCenter(planWorkCenterId,statusCd){
	try{
		$("#scheForm_"+planWorkCenterId+" input[name='targetPointCd']").val($("#targetPointCd_hide_"+planWorkCenterId).children(0).val());
	}catch(e){}
	try{
		$("#scheForm_"+planWorkCenterId+" input[name='area']").val($("#area_input_"+planWorkCenterId).val());
	}catch(e){}
	try{
		$("#scheForm_"+planWorkCenterId+" input[name='principal']").val($("#"+planWorkCenterId+"_principalCds").val());
	}catch(e){}
	try{
		$("#scheForm_"+planWorkCenterId+" input[name='workType']").val($("#"+planWorkCenterId+"_workType").val());
	}catch(e){}
	try{
		$("#scheForm_"+planWorkCenterId+" input[name='corUser']").val($("#"+planWorkCenterId+"_corUserCds").val());
	}catch(e){}
	$("#scheForm_"+planWorkCenterId+" input[name='newMessage']").val($("#"+planWorkCenterId+"_message").val());
	$("#scheForm_"+planWorkCenterId+" input[name='serialOrder']").val($("#serialOrder_input_"+planWorkCenterId).val());
	$("#scheForm_"+planWorkCenterId+" input[name='content']").val($("#content_input_"+planWorkCenterId).val());
	if(null!=$("#targetDate_input_"+planWorkCenterId).val() && ""!=$("#targetDate_input_"+planWorkCenterId).val()){
		$("#scheForm_"+planWorkCenterId+" input[name='targetDate']").val($("#targetDate_input_"+planWorkCenterId).val());
	}
	if(null!=statusCd && ""!=statusCd){
		$("#scheForm_"+planWorkCenterId+" input[name='statusCd']").val(statusCd);
	}
	$("#scheForm_"+planWorkCenterId).ajaxSubmit(function(result) {
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
					succMsg = "隐藏成功";
					break;
				case 5:
					succMsg = "删除成功";
					break;
			}
			//$("#scheduleTaskDiv").html(result);
			$("#succInfoMsg").html(succMsg).show().fadeOut(2000);
			updateTdStatusHtml(planWorkCenterId,statusCd);
			//updateTdrecordVersions(planWorkCenterId,result);
			refreshMain(planWorkCenterId);
			refreshDetail(planWorkCenterId);
		}
	});
	$("#pop_bg").show();
}
//刷新某条记录的主体部分
function refreshMain(planId){
	var isAttentioned = $("#scheForm_"+planId+" input[name='isAttentioned']").val();
	var target=$("#main_"+planId);
	$.get(_ctx+"/plan/plan-work-center!fetchMain.action?id="+planId+"&centerCd="+centerCd+"&now_orgCd="+centerCd+"&isEditOrg="+isEditOrg+"&isAttentioned="+isAttentioned+"&if_in_attention="+if_in_attention+"&if_in_center="+if_in_center+"&if_chengben="+if_chengben+"&now_year="+now_year+"&now_month="+now_month, function(result) {
		if (result) {
			target.html(result);
		}
	}); 
}
//刷新某条记录的展开部分
function refreshDetail(planId){
	var isAttentioned = $("#scheForm_"+planId+" input[name='isAttentioned']").val();
	var target=$("#detail_"+planId);
	$.get(_ctx+"/plan/plan-work-center!fetchDetail.action?id=" + planId+"&centerCd="+centerCd+"&now_orgCd="+centerCd+"&isEditOrg="+isEditOrg+"&isAttentioned="+isAttentioned+"&now_year="+now_year+"&now_month="+now_month, function(result) {
		if (result) {
			target.html(result);
			$("#pop_bg").hide();
		}
	}); 
}
//异步保存记录信息
function updateRecord(planWorkCenterId, type, newVal) {
	var record_planYear = now_year;
	var record_planMonth = now_month;
	var param = null;
	var msg = "";
	var oldVal = "";
	try{
		oldVal = $("#scheForm_"+planWorkCenterId+" input[name='"+type+"']").val();
	}catch(e){}
	if(oldVal==newVal && !(type=="statusCd" && newVal==8)){
		return;
	}
	if("targetDate"==type && (""==newVal||null==newVal)){
		return;
	}
	switch (type) {
		case "serialOrder" :
			param = {"id" : planWorkCenterId, "serialOrder" : newVal};
			msg = "顺序修改成功";
			break;
		case "content" :
			param = {"id" : planWorkCenterId, "content" : newVal};
			msg = "内容修改成功";
			break;
		case "principal" :
			param = {"id" : planWorkCenterId, "principal" : newVal};
			msg = "负责人修改成功";
			break;
		case "targetDate" :
			param = {"id" : planWorkCenterId, "targetDate" : newVal};
			msg = "目标日期修改成功";
			break;
		case "area" :
			param = {"id" : planWorkCenterId, "area" : newVal};
			msg = "地区修改成功";
			break;
		case "statusCd" :
			var tempMessage = "";
			switch(newVal){
				case 0:
					msg = "未确认成功";
					tempMessage = "未确认";
					break;
				case 1:
					msg = "确认成功";
					tempMessage = "确认";
					break;
				case 2:
					msg = "预完成成功";
					tempMessage = "预完成";
					break;
				case 3:
					msg = "完成成功";
					tempMessage = "完成";
					break;
				case 4:
					msg = "删除成功";
					tempMessage = "删除";
					break;
				case 5:
					msg = "隐藏成功";
					tempMessage = "隐藏";
					break;
				case 7:
					msg = "非本月";
					tempMessage = "非本月";
					break;
				case 8:
					msg = "过期成功";
					record_planMonth++;
					if(13==record_planMonth){
						record_planMonth=1;
						record_planYear+=1;
					}
					tempMessage = "过期到"+record_planMonth+"月";
					break;
				case 9:
					msg = "非考核性过期成功";
					record_planMonth++;
					if(13==record_planMonth){
						record_planMonth=1;
						record_planYear+=1;
					}
					tempMessage = "非考核性过期到"+record_planMonth+"月";
					break;
				case 10:
					msg = "完成成功但曾过期";
					record_planMonth++;
					if(13==record_planMonth){
						record_planMonth=1;
						record_planYear+=1;
					}
					tempMessage = "完成到"+record_planMonth+"月但曾过期";
					break;
			}
			param = {"id" : planWorkCenterId,"planYear":now_year,"planMonth":now_month, "statusCd" : newVal, "newMessage":"【修改了状态为"+tempMessage+"】"};
			break;
		case "delete" :
			if (confirm("确认删除？")) {
				param = {"id" : planWorkCenterId, "isDeleted" : newVal};
				msg = "已删除";
				$("#" + planWorkCenterId + "item").next().remove();
				$("#" + planWorkCenterId + "item").remove();
			}
			break;
		case "hide" :
			param = {"id" : planWorkCenterId, "hiddenFlg" : newVal};
			msg = "已设置为隐藏";
			$("#" + planWorkCenterId + "item").next().remove();
			$("#" + planWorkCenterId + "item").remove();
			break;
		case "show" :
			param = {"id" : planWorkCenterId, "hiddenFlg" : newVal};
			msg = "已设置为被显示";
			break;
	}
	if (param) {
		$.post(_ctx+"/plan/plan-work-center!save.action?isEditOrg="+isEditOrg+"&if_in_attention="+if_in_attention+"&if_in_center="+if_in_center, param, function(result) {
			refreshMain(planWorkCenterId);
			$("#succInfoMsg").html(msg).show().fadeOut(2000);
		});
	}
}

//退回
function doCallback(planWorkCenterId) {
	var record_planYear = now_year;
	var record_planMonth = now_month;
	record_planMonth--;
	var data = {
			id:planWorkCenterId,
			now_year:now_year,
			now_month:now_month,
			newMessage:"【驳回到"+record_planMonth+"月】"
			};
	$.post(_ctx+"/plan/plan-work-center!doCallback.action", data, function(result) {
		if("success"==result){
			$("#succInfoMsg").html("已成功驳回").show().fadeOut(2000);
			refreshMain(planWorkCenterId);
			refreshDetail(planWorkCenterId);
		}else{
			$("#succInfoMsg").html("驳回失败,请刷新").show().fadeOut(2000);
		}
	});
}

//保存留言
function saveMessage(planWorkCenterId){
	var isAttentioned = $("#scheForm_"+planWorkCenterId+" input[name='isAttentioned']").val();
	var content =$("#"+planWorkCenterId+"_message").val();
	if($.trim(content).length>0){
		$.post(_ctx+"/plan/plan-work-center!saveMessage.action",
				{
			     id:planWorkCenterId,
			     content:content,
			     isAttentioned:isAttentioned
				},
				 function(result) {
					 if (result == "ok") {
						 //refreshContent(planWorkCenterId);
						 refreshMain(planWorkCenterId);
						 refreshDetail(planWorkCenterId);
						 //$("#"+planWorkCenterId+"_message").val("");
					 }else if(result =="no"){
						 alert("留言失败");
					 }
				 });
	}
}
//刷新留言,不用
function refreshContent(planWorkCenterId){
	var target=$("#"+planWorkCenterId+"_messageDiv");
	$.get(_ctx+"/plan/plan-work-center!fetchContent.action?id=" + planWorkCenterId+"&isEditOrg="+isEditOrg, function(result) {
		if (result) {
			target.html(result);
			$("#pop_bg").hide();
		}
	}); 
}
//显示/隐藏细项
function toggleDetail(detail_obj,planWorkCenterId){
	if(can_edit_record){
		if(CAN_toggleDetail){
			$(detail_obj).find('.span_show').toggle();
			$(detail_obj).find('.span_hide').toggle();
		}else{
			CAN_toggleDetail = true;
		}
		if(null!=planWorkCenterId){
			closePrevDetail(planWorkCenterId);
		}
	}
}
function toggleDetail2(toggleName,planWorkCenterId){
	if(can_edit_record){
		if(CAN_toggleDetail){
			$("#"+toggleName).find('.span_show').toggle();
			$("#"+toggleName).find('.span_hide').toggle();
		}else{
			CAN_toggleDetail = true;
		}
		if(null!=planWorkCenterId){
			closePrevDetail(planWorkCenterId);
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
function showDetail(toggleName){
	if(can_edit_record){
		if(CAN_toggleDetail){
			$("#"+toggleName).find('.span_show').hide();
			$("#"+toggleName).find('.span_hide').show();
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
			hideDetail("td_principal_"+opened_td_id);
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
			hideDetail("td_principal_"+oldId);
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
			setAttentionRead("planWorkCenter",scheId);
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
			try{
				showDetail("td_serialOrder_"+scheId);
			}catch(e){}
			try{
				showDetail("td_targetPointCd_"+scheId);
			}catch(e){}
			try{
				showDetail("td_area_"+scheId);
			}catch(e){}
			try{
				showDetail("td_content_"+scheId);
			}catch(e){}
			try{
				showDetail("td_targetDate_"+scheId);
			}catch(e){}
			try{
				showDetail("td_principal_"+scheId);
			}catch(e){}
			try{
				showDetail("td_workType_"+scheId);
			}catch(e){}
			try{
				showDetail("td_corUser_"+scheId);
			}catch(e){}
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
				hideDetail("td_principal_"+scheId);
			}catch(e){}
			try{
				hideDetail("td_workType_"+scheId);
			}catch(e){}
			try{
				hideDetail("td_corUser_"+scheId);
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
	try{$("#search_principalName").val("");}catch(e){}
	try{$("#search_principal").val("");}catch(e){}
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
	$.post(_ctx+"/plan/plan-work-center!doUpdateStatusAll.action", param, function(result) {
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
	try{
		if(2==statusCd){
			//如果是预完成，取消申请删除按钮
			//$("#saveDelete_btn_"+itemId).css("display","none");
		}
	}catch(e){}
}
//更新记录的statusCd和显示出来的图标
function updateTdStatusHtml(itemId,statusCd){
	var html_str = "";
	if(null!=statusCd){
		switch(statusCd){
			case 0:
				html_str = "<img src='"+_ctx+"/resources/images/common/status_unconfirm.gif' title='未确认'>";
				break;
			case 1:
				html_str = "<img src='"+_ctx+"/resources/images/common/status_confirm.gif' title='已确认'>";
				break;
			case 2:
				html_str = "<img src='"+_ctx+"/resources/images/common/status_prefinish.gif' title='预完成'>";
				break;
			case 3:
				html_str = "<img src='"+_ctx+"/resources/images/common/status_finish.gif' title='已完成'>";
				break;
			case 4:
				html_str = "已删除";
				break;
			case 5:
				html_str = "<img src='"+_ctx+"/resources/images/common/status_hidden.gif' title='已隐藏'>";
				break;
			case 7:
				html_str = "非本月任务";
				break;
			case 8:
				html_str = "<img src='"+_ctx+"/resources/images/common/status_suspend.gif' title='已过期'>";
				break;
			case 9:
				html_str = "非考核性过期";
				break;
			case 10:
				html_str = "完成但曾过期";
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
	$.post(_ctx+"/plan/plan-work-center!doExchangeOrder.action", param+"&"+param2, function(result) {
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
function openSearchPrincipal(nameId,cdId){
	var config={'nameId':nameId,'cdId':cdId,callBack:function(){
	}};
	getMemberJson(config);
}
var cur_entityId = null;	//附件的临时存储变量
var if_atta_according = false;	//新增时是否是任务依据
function openAttachment(title,entityId,bizFieldName,onlyShow){
	var bizFieldNameStr = "";
	if(null==bizFieldName || undefined==bizFieldName){
		bizFieldNameStr = "";
		if_atta_according = false;
	}else{
		bizFieldNameStr = "&bizFieldName="+bizFieldName;
		if_atta_according = true;
	}
	var onlyShowStr = "";
	if(null==onlyShow || undefined==onlyShow){
		onlyShowStr = "";
	}else{
		onlyShowStr = "&onlyShow="+onlyShow;
	}
	if("新增附件管理"==title || "新增任务依据"==title){
		cur_entityId = null;
	}else{
		cur_entityId = entityId;
	}
	ymPrompt.win({
		message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=planWorkCenter&filterType=image|office&bizEntityName=PlanWorkCenter"+bizFieldNameStr+onlyShowStr,
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
		if(if_atta_according){
			$("#new_img_atta_according").attr("src",_ctx+"/resources/images/common/atta_y.gif");
		}else{
			$("#new_img_atta").attr("src",_ctx+"/resources/images/common/atta_y.gif");
		}
	}
}
// 提醒功能, 给负责人发信
function remind(itemId) {
	$.post(_ctx + "/plan/plan-work-center!remind.action?id=" + itemId, function(result) {
		if (result == "done") {
			$("#succInfoMsg").html("提醒邮件已发送").show().fadeOut(2000);
		}
	});
}

//从成本执行计划中新增
function addFromPlanExec(param_j){
	var from_centerCd = param_j["resOrgCd"];
	if(""!=from_centerCd && centerCd!=from_centerCd){
		//如果传入的部门cd不为空并且不是当前的部门，刷新页面
		param_str = "?planExecId="+param_j["planExecId"]+"&addFromType="+param_j["addFromType"]+"&resOrgCd="+from_centerCd;
		self.location = _ctx+"/plan/plan-work-center!addFromPlanExec.action"+param_str;
	}else{
		//如果传入的部门cd是空或者是当前部门，直接新增
		newSchedule();
		$("#new_content").val(param_j["content"]);
		$("#new_targetDate").val(param_j["targetDate"]);
		$("#new_targetPointCd").val(param_j["projectCd"]);
		$("#newScheForm input[name='addFromType']").val(param_j["addFromType"]);
		$("#newScheForm input[name='planExecId']").val(param_j["planExecId"]);
		$("#new_content").attr("disabled","true");
		$("#new_targetDate").attr("disabled","true");
		$("#new_targetPointCd").attr("disabled","true");
	}
}

//月份的点击
function monthClick(toMonth){
	if(toMonth==now_month){
		return;
	}
	for(var i=1;i<13;i++){
		$("#month"+i).removeClass("month_big");
		$("#month"+i).removeClass("color_red");
		$("#month"+i).addClass("month_small");
	}
	$("#month"+toMonth).removeClass("month_small");
	$("#month"+toMonth).addClass("month_big");
	$("#month"+toMonth).addClass("color_red");
	now_month = toMonth;
	centerClick();
	opened_entityid = "";
}