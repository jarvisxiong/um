var can_edit_record = true;	//能否对记录进行编辑。如果是管理员，则能编辑；如果是普通用户，则在自己中心内的才能编辑.
var can_edit_point = true;	//能否对记录进行打分。如果是普通用户，则在自己中心内的才能编辑
var opened_td_id = "";	//当前打开的记录的ID，如果点了其他行，那么关闭当前打开的行
var now_year = 0;	//当前的年份
var now_month = 0;	//当先的月份
var centerCd = "";	//当先的中心cd
var IF_IN_LOADING = false;	//是否正在部门的切换中，如果是，屏蔽切换部门的动作
var CAN_toggleDetail = true;	//是否能toggleDetail,用来屏蔽输入框的父对象的点击事件
var oldId="";	//上次打开的详细信息的Id
var CAN_scheClick = true;	//是否能scheClick,用来屏蔽输入框的父对象的点击事件
var isEditOrg = 0;	//是否是当前用户所在部门，用于更新记录判断是否有权限
var IF_HISTORY = false;	//是否正在查看历史
var myTask = "";	//是否从邮件报告中进入，如果是搜索我的记录
var center_status = "";	//此中心的月计划操作状态，从planWork2Status表中读取
var quarter_status = "";	//此中心的季度计划操作状态，从planWork2Status表中读取
var myRoles = new Array();	//用户在此模块中的角色数组
var arr_ids = new Array();	//储存所有记录的id，用来做储值的判断
var temp_if_arr_ids_hasId = false;	//临时变量，用于arr_ids的初始化
var if_in_weight = "0";		//是否在评分的状态,0表示否
var if_goto_cost = "0";		//是否在成本工作管理中,0表示否
var now_quarter = 0;		//现在所在的季度
var myUiid = "";
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
			now_month = search_planMonth;
		}
		if(null!=search_centerCd && ""!=search_centerCd){
			centerCd = search_centerCd;
		}
		var data = {
				myTask : myTask,
				opened_entityid : opened_entityid,
				if_in_attention : if_in_attention,
				centerCd : centerCd,
				now_year : now_year,
				now_month : now_month,
				now_quarter : now_quarter,
				if_in_weight : if_in_weight,
				if_goto_cost : if_goto_cost,
				"page.pageNo" : 1,
				"page.pageSize" : 999,
				filter_LIKES_content : $("#filter_LIKES_content").val(),
				search_statusCd : $("#search_statusCd").val(),
				if_search_all : $("#if_search_all").val(),
				orderStr1 : $("#orderStr1").val(),
				orderStr2 : $("#orderStr2").val(),
				orderDir1 : $("#orderDir1").val(),
				orderDir2 : $("#orderDir2").val()
		};
		$("#scheduleDiv").html("<div style='height:100px'></div><table width='100%'><tr><td align='center'><img src='"+_ctx+"/images/loading.gif'/></td></tr></table>");
		$.post(_ctx+"/plan/plan-work2!list.action", data, function(result) {
			$("#scheduleDiv").html(result);
			autoHeight();
			IF_IN_LOADING = false;
			IF_HISTORY = false;
		});
	}
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
	/*
	if($("#new_targetDate").val()==""){
		//alert("请输入目标时间  ");
		//return false;
	}
	*/
	try{
		//$("#newScheForm input[name='targetPointCd']").val($("#new_targetPointCd").val());
		//$("#newScheForm input[name='area']").val($("#new_area").val());
	}catch(e){}
	$("#newScheForm input[name='planYear']").val(now_year);
	$("#newScheForm input[name='planMonth']").val(now_month);
	$("#newScheForm input[name='planType']").val($("#new_planType").val());
	$("#newScheForm input[name='content']").val($("#new_content").val());
	$("#newScheForm input[name='targetDate']").val($("#new_targetDate").val());
	$("#newScheForm input[name='newMessage']").val($("#new_newMessage").val());
	var now_serialOrder = $("#newScheForm input[name='serialOrder']").val();	//现在的序列号
	$("#newScheForm input[name='centerCd']").val(centerCd);
	
	var param = {deptCd:dataId,respDeptCds:dataId};
	$("#newScheForm").attr("action",_ctx+"/plan/plan-work2!save.action");
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
		if (null!=result && ""!=result) {
			$("#pop_bg").hide();
			now_serialOrder++;	//新增成功，序列号加1
			var new_record_html = '<tr id="main_'+result+'" class="mainTr" style="cursor:pointer;"><td colspan="8"></td></tr>'
				+'<tr id="detail_'+result+'" class="detailTr" style="display:none;" ondblclick="scheClick(\''+result+'\');"><td colspan="8"></td></tr>';
			$("#newScheTemplate2").after(new_record_html);
			refreshMain(result);
			refreshDetail(result);
			$("#newScheForm input[name='serialOrder']").val(now_serialOrder);
			$("#newScheForm input[name='content']").val("");
			$("#newScheForm input[name='planType']").val("2");
			$("#newScheForm input[name='targetDate']").val("");
			$("#newScheForm input[name='newMessage']").val("");
			$("#new_content").val("");
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
function savePlanWork(planWork2Id,statusCd){
	try{
		//$("#scheForm_"+planWork2Id+" input[name='targetPointCd']").val($("#targetPointCd_hide_"+planWork2Id).children(0).val());
		//$("#scheForm_"+planWork2Id+" input[name='area']").val($("#area_input_"+planWork2Id).val());
	}catch(e){}
	$("#scheForm_"+planWork2Id+" input[name='newMessage']").val($("#"+planWork2Id+"_message").val());
	$("#scheForm_"+planWork2Id+" input[name='serialOrder']").val($("#serialOrder_input_"+planWork2Id).val());
	$("#scheForm_"+planWork2Id+" input[name='content']").val($("#content_input_"+planWork2Id).val());
	if(null!=$("#targetDate_input_"+planWork2Id).val() && ""!=$("#targetDate_input_"+planWork2Id).val()){
		$("#scheForm_"+planWork2Id+" input[name='targetDate']").val($("#targetDate_input_"+planWork2Id).val());
	}
	if(null!=statusCd && ""!=statusCd){
		$("#scheForm_"+planWork2Id+" input[name='statusCd']").val(statusCd);
	}
	$("#scheForm_"+planWork2Id+" input[name='if_in_weight']").val(if_in_weight);
	$("#scheForm_"+planWork2Id).ajaxSubmit(function(result) {
		if (null!=result && ""!=result) {
			$("#pop_bg").hide();
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
					succMsg = "申请删除成功";
					break;
				case 4:
					succMsg = "完成成功";
					break;
				case 5:
					succMsg = "删除成功";
					break;
				case 6:
					succMsg = "隐藏成功";
					break;
				case 8:
					succMsg = "延迟成功";
					break;
			}
			$("#succInfoMsg").html(succMsg).show().fadeOut(2000);
			//updateTdStatusHtml(planWork2Id,statusCd);
			refreshMain(planWork2Id);
			refreshDetail(planWork2Id);
		}
	});
	$("#pop_bg").show();
}
//刷新某条记录的主体部分
function refreshMain(planId){
	var isAttentioned = $("#scheForm_"+planId+" input[name='isAttentioned']").val();
	var target=$("#main_"+planId);
	$.get(_ctx+"/plan/plan-work2!fetchMain.action?id="+planId+"&centerCd="+centerCd+"&now_centerCd="+centerCd+"&isEditOrg="+isEditOrg+"&isAttentioned="+isAttentioned+"&if_in_attention="+if_in_attention+"&center_status="+center_status+"&quarter_status="+quarter_status+"&if_in_weight="+if_in_weight+"&if_goto_cost="+if_goto_cost+"&now_year="+now_year+"&now_month="+now_month, function(result) {
		if (result) {
			target.html(result);
			$("#pop_bg").hide();
			refreshMyOper();
		}
	}); 
}
//刷新某条记录的展开部分
function refreshDetail(planId){
	var isAttentioned = $("#scheForm_"+planId+" input[name='isAttentioned']").val();
	var target=$("#detail_"+planId);
	$.get(_ctx+"/plan/plan-work2!fetchDetail.action?id=" + planId+"&centerCd="+centerCd+"&now_centerCd="+centerCd+"&isEditOrg="+isEditOrg+"&isAttentioned="+isAttentioned+"&center_status="+center_status+"&quarter_status="+quarter_status+"&if_in_weight="+if_in_weight+"&if_goto_cost="+if_goto_cost+"&now_year="+now_year+"&now_month="+now_month, function(result) {
		if (result) {
			target.html(result);
			$("#pop_bg").hide();
			refreshMyOper();
		}
	}); 
}
//异步保存记录信息
function updateRecord(planWork2Id, type, newVal) {
	var record_planYear = $("#scheForm_"+planWork2Id+" input[name='planYear']").val();
	var record_planMonth = $("#scheForm_"+planWork2Id+" input[name='planMonth']").val();
	if(1!=if_in_weight && (record_planYear!=now_year || record_planMonth!=now_month)){
		alert("当前记录已推至"+record_planYear+"年"+record_planMonth+"月");
		return;
	}
	if("selfPoint"==type || "selfCheckPoint"==type || "evaluatePoint"==type || "finalPoint"==type){
		var my_weightPoint = $("#weightPoint_input_"+planWork2Id).val();
		if(Number(my_weightPoint) < Number(newVal)){
			//alert("输入数值不得大于权重分！");
			//return;
		}
	}
	var param = null;
	var msg = "";
	var oldVal = "";
	try{
		oldVal = $("#scheForm_"+planWork2Id+" input[name='"+type+"']").val();
	}catch(e){}
	if(oldVal==newVal && !(type=="statusCd" && newVal==8)){
		return;
	}
	if("targetDate"==type && (""==newVal||null==newVal)){
		return;
	}
	var myWeightPoint = new Number(0);

	switch (type) {
		case "planType" :
			param = {"id" : planWork2Id, "planType" : newVal};
			msg = "类型修改成功";
			break;
		case "serialOrder" :
			param = {"id" : planWork2Id, "serialOrder" : newVal};
			msg = "顺序修改成功";
			break;
		case "content" :
			param = {"id" : planWork2Id, "content" : newVal, "newMessage":"【修改了内容】"};
			msg = "内容修改成功";
			break;
		case "principal" :
			param = {"id" : planWork2Id, "principal" : newVal, "newMessage":"【修改了负责人】"};
			msg = "负责人修改成功";
			break;
		case "targetDate" :
			param = {"id" : planWork2Id, "targetDate" : newVal, "newMessage":"【修改了目标日期】"};
			msg = "目标日期修改成功";
			break;
		case "remark" :
			param = {"id" : planWork2Id, "remark" : newVal, "newMessage":"【修改了备注信息】"};
			msg = "备注修改成功";
			break;	
		case "area" :
			param = {"id" : planWork2Id, "area" : newVal};
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
					msg = "申请删除成功";
					tempMessage = "申请删除";
					break;
				case 4:
					msg = "完成成功";
					tempMessage = "完成";
					break;
				case 5:
					msg = "删除成功";
					tempMessage = "删除";
					break;
				case 6:
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
					tempMessage = "过期到"+record_planMonth+"月";
					break;
				case 9:
					msg = "非考核性过期成功";
					record_planMonth++;
					if(13==record_planMonth){
						record_planMonth=1;
					}
					tempMessage = "非考核性过期到"+record_planMonth+"月";
					break;
				case 10:
					msg = "完成成功但曾过期";
					record_planMonth++;
					tempMessage = "完成到"+record_planMonth+"月但曾过期";
					break;
			}
			param = {"id" : planWork2Id,"planYear":now_year,"planMonth":now_month, "statusCd" : newVal, "newMessage":"【修改了状态为"+tempMessage+"】"};
			break;
		case "delete" :
			if (confirm("确认删除？")) {
				param = {"id" : planWork2Id, "isDeleted" : newVal};
				msg = "已删除";
				$("#" + planWork2Id + "item").next().remove();
				$("#" + planWork2Id + "item").remove();
			}
			break;
		case "hide" :
			param = {"id" : planWork2Id, "hiddenFlg" : newVal};
			msg = "已设置为隐藏";
			$("#" + planWork2Id + "item").next().remove();
			$("#" + planWork2Id + "item").remove();
			break;
		case "show" :
			param = {"id" : planWork2Id, "hiddenFlg" : newVal};
			msg = "已设置为被显示";
			break;
		case "bogusWeightPoint":
			//如果是更新权重分bogusWeightPoint，先根据规则计算实际权重分，再提交
			var myPlanType = $("#scheForm_"+planWork2Id+" input[name='planType']").val();
			myWeightPoint = convertWeightPoint(Number(newVal),myPlanType);
			param = {"id" : planWork2Id, "bogusWeightPoint" : newVal, "weightPoint" : myWeightPoint};
			msg = "权重分已修改";
			break;
		case "selfPoint":
			param = {"id" : planWork2Id, "selfPoint" : newVal};
			msg = "自评分已修改";
			break;
		case "selfCheckPoint":
			param = {"id" : planWork2Id, "selfCheckPoint" : newVal};
			msg = "考评分已修改";
			break;
		case "evaluatePoint":
			param = {"id" : planWork2Id, "evaluatePoint" : newVal};
			msg = "考评分已修改";
			break;
		case "finalPoint":
			param = {"id" : planWork2Id, "finalPoint" : newVal};
			msg = "最终得分已修改";
			break;
	}
	if (param) {
		$.post(_ctx+"/plan/plan-work2!save.action?isEditOrg="+isEditOrg+"&if_in_weight="+if_in_weight+"&now_year="+now_year+"&now_month="+now_month+"&now_quarter="+now_quarter, param, function(result) {
			if("failure"==result){
				$("#succInfoMsg").html("操作失败,请刷新").show().fadeOut(2000);
				return;
			}
			$("#succInfoMsg").html(msg).show().fadeOut(2000);
			try{$("#td_updateDate_"+planWork2Id).html(nowDate.substring(5,nowDate.length));}catch(e){}
			var beforeRecordVersion = Number($("#scheForm_"+planWork2Id+" input[name='recordVersion']").val());
			beforeRecordVersion++;
			$("#scheForm_"+planWork2Id+" input[name='recordVersion']").val(beforeRecordVersion);
			switch (type) {
				case "content" :
					$("#content_show_"+planWork2Id).html(newVal);
					refreshDetail(planWork2Id);
					break;
				case "targetDate" :
					refreshMain(planWork2Id);
					break;
				case "principal" :
					refreshMain(planWork2Id);
					refreshDetail(planWork2Id);
					break;
				case "planType" :
					refreshMain(planWork2Id);
					refreshDetail(planWork2Id);
					break;
				case "serialOrder" :
					$("#serialOrder_show_"+planWork2Id).html(newVal);
					break;
				case "remark" :
					$("#remark_show_"+planWork2Id).html(newVal);
					break;	
				case "statusCd" :
					refreshDetail(planWork2Id);
					break;
				case "bogusWeightPoint" :
					$("#weightPoint_show_"+planWork2Id).html(newVal);
					$("#weightPoint_hide_"+planWork2Id).hide();
					$("#weightPoint_show_"+planWork2Id).show();
					var topPlanType = $("#scheForm_"+planWork2Id+" input[name='planType']").val();
					if(4!=topPlanType){
						var titleStr = "剩余可给分数:"+getRestWeightPoint(planWork2Id,topPlanType);
						for(var i=0;i<arr_ids.length;i++){
							var myId = arr_ids[i];
							var myPoint = Number(0);
							var myPlanType = $("#scheForm_"+myId+" input[name='planType']").val();
							if(topPlanType==myPlanType){
								$("#td_weightPoint_"+myId).attr("title",titleStr);
							}
						}
					}
					break;
				case "selfPoint" :
					$("#selfPoint_show_"+planWork2Id).html(newVal);
					$("#selfPoint_hide_"+planWork2Id).hide();
					$("#selfPoint_show_"+planWork2Id).show();
					break;
				case "selfCheckPoint" :
					$("#selfCheckPoint_show_"+planWork2Id).html(newVal);
					$("#selfCheckPoint_hide_"+planWork2Id).hide();
					$("#selfCheckPoint_show_"+planWork2Id).show();
					break;
				case "evaluatePoint" :
					$("#evaluatePoint_show_"+planWork2Id).html(newVal);
					$("#evaluatePoint_hide_"+planWork2Id).hide();
					$("#evaluatePoint_show_"+planWork2Id).show();
					break;
				case "finalPoint" :
					$("#finalPoint_show_"+planWork2Id).html(newVal);
					$("#finalPoint_hide_"+planWork2Id).hide();
					$("#finalPoint_show_"+planWork2Id).show();
					break;
			}
		});
	}
}

//保存留言
function saveMessage(planWork2Id){
	var isAttentioned = $("#scheForm_"+planWork2Id+" input[name='isAttentioned']").val();
	var content =$("#"+planWork2Id+"_message").val();
	if($.trim(content).length>0){
		$.post(_ctx+"/plan/plan-work2!saveMessage.action",
				{
			     id:planWork2Id,
			     content:content,
			     isAttentioned:isAttentioned
				},
				 function(result) {
					 if (result == "ok") {
						 refreshMain(planWork2Id);
						 refreshDetail(planWork2Id);
					 }else if(result =="no"){
						 alert("留言失败");
					 }
				 });
	}
}

//显示/隐藏细项
function toggleDetail(detail_obj,planWork2Id,toggleName){

//	if("1"==$("#scheForm_"+planWork2Id+" input[name='planType']").val()){
//		//如果是年计划，不可编辑细项
//		return;
//	}

	if(can_edit_record){
		if(CAN_toggleDetail){
			if($("#scheForm_"+planWork2Id+" input[name='planType']").val()==="1"){
				if(judgeIfHasSomeRole("A_PLAN_WORK2_ADMIN")){
					$(detail_obj).find('.span_show').toggle();
					$(detail_obj).find('.span_hide').toggle();
				}
			}else{
				$(detail_obj).find('.span_show').toggle();
				$(detail_obj).find('.span_hide').toggle();
			}
		}else{
			CAN_toggleDetail = true;
		}
		if(null!=planWork2Id){
			closePrevDetail(planWork2Id);
		}
	}
	/*
	else{
		if(judgeIfHasSomeRole("A_PLAN_WORK2_CENTER") &&  $("#scheForm_"+planWork2Id+" input[name='planType']").val()==="2"){
			can_edit_record=true;
			if(CAN_toggleDetail){
				//if($(detail_obj).children().attr("id")==='content_show_'+planWork2Id){
				//$(detail_obj).children().toggle();
					$(detail_obj).find('.span_show').toggle();
					$(detail_obj).find('.span_hide').toggle();
				//}
			}else{
				CAN_toggleDetail = true;
			}
			if(null!=planWork2Id){
				closePrevDetail(planWork2Id);
			}
			btnControl(new Array("None"));
			if(1!=if_in_weight){
				$("#NewBtn").show();
			}
			can_edit_record=false;
		}else{
			can_edit_record=true;
			if(null!=planWork2Id){
				closePrevDetail(planWork2Id);
			}
			btnControl(new Array("None"));
			if(1!=if_in_weight){
				$("#NewBtn").show();
			}
			can_edit_record=false;
			btnControl(new Array(""));
			if(1!=if_in_weight){
				$("#NewBtn").show();
			}
		}
		
	}
	*/
}

//显示/隐藏评分细项
function toggleDetailPoint(detail_obj,planWork2Id,toggleName,planType){
	if("1"!=if_in_weight){
		return;
	}
	if("weightPoint"==toggleName){
		if(4!=quarter_status || (!judgeIfHasSomeRole("A_PLAN_WORK2_CEO")&&!judgeIfHasSomeRole("A_PLAN_WORK2_OFFICE"))){
			//如果不是在给权重分状态或者不是总裁用户或者总裁办用户，不可打开权重分
			return false;
		}
	}
	if("selfPoint"==toggleName || "selfCheckPoint"==toggleName || "evaluatePoint"==toggleName || "finalPoint"==toggleName){
		if("selfPoint"==toggleName && (5!=quarter_status || !judgeIfHasSomeRole("A_PLAN_WORK2_CENTER"))){
			//如果不是在给自评分状态或者不是普通用户，不可打开自评分
			return;
		}
		if("selfCheckPoint"==toggleName && (6!=quarter_status || !judgeIfHasSomeRole("A_PLAN_WORK2_VICE"))){
			//如果不是在给副总裁审核分状态或者不是副总裁用户，不可打开副总裁审核分
			return;
		}
		if("evaluatePoint"==toggleName && ((5!=quarter_status&&6!=quarter_status&&7!=quarter_status) || (!judgeIfHasSomeRole("A_PLAN_WORK2_OFFICE")&&!judgeIfHasSomeRole("A_PLAN_WORK2_PROJECT")))){
			//如果不是在给企管部审核分状态或者不是企管部用户或者项目管理中心用户，不可打开企管部审核分
			return;
		}
		if("evaluatePoint"==toggleName && (5==quarter_status||6==quarter_status||7==quarter_status) && judgeIfHasSomeRole("A_PLAN_WORK2_PROJECT")){
			if(3!=planType){
				//如果是项目管理中心，只能给 plantype为 项目管理 的记录给分
				return;
			}
		}
		if("finalPoint"==toggleName && (8!=quarter_status || !judgeIfHasSomeRole("A_PLAN_WORK2_CEO"))){
			//如果不是在给最终分状态或者不是总裁，不可打开最终分
			return;
		}
	}
	if(can_edit_point){
		if(CAN_toggleDetail){
			$("#"+toggleName+"_show_"+planWork2Id).toggle();
			$("#"+toggleName+"_hide_"+planWork2Id).toggle();
		}else{
			CAN_toggleDetail = true;
		}
		if(null!=planWork2Id){
			closePrevDetail(planWork2Id);
		}
	}
}
function toggleDetail2(toggleName,planWork2Id){
	if(can_edit_record){
		if(CAN_toggleDetail){
			$("#"+toggleName).find('.span_show').toggle();
			$("#"+toggleName).find('.span_hide').toggle();
		}else{
			CAN_toggleDetail = true;
		}
		if(null!=planWork2Id){
			closePrevDetail(planWork2Id);
		}
	}
}
function hideDetail(toggleName){
	if(can_edit_record){
		if(CAN_toggleDetail){
			try{
				$("#"+toggleName).find('.span_show').show();
				$("#"+toggleName).find('.span_hide').hide();
				
			}catch(e){}
		}else{
			CAN_toggleDetail = true;
		}
	}
}
//关闭正在打开的内容的详细条目
function closePrevDetail(scheId){
	try{
		if(""!=scheId && opened_td_id!=scheId){
			try{
				hideDetail("td_serialOrder_"+opened_td_id);
			}catch(e){}
			try{
				hideDetail("td_planType_"+opened_td_id);
			}catch(e){}
			//hideDetail("td_targetPointCd_"+opened_td_id);
			//hideDetail("td_area_"+opened_td_id);
			try{
				hideDetail("td_content_"+opened_td_id);
			}catch(e){}
			try{
				hideDetail("td_targetDate_"+opened_td_id);
			}catch(e){}
			try{
				hideDetail("td_remark_"+opened_td_id);
			}catch(e){}
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
			hideDetail("td_planType_"+oldId);
			hideDetail("td_targetPointCd_"+oldId);
			//hideDetail("td_area_"+oldId);
			//hideDetail("td_content_"+oldId);
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
	try{
	if(CAN_scheClick){
		try{
			if("inline"==$("#attention_unread_img_"+scheId).css("display")){
				setAttentionRead("planWork2",scheId);
			}
		}catch(e){}
		var detailObj = document.getElementById("detail_"+scheId);
		
		if(detailObj.style.display == "none"){
			if(oldId!="" && oldId!=scheId){
				try{
					$("#detail_"+oldId).hide();
					$("#main_"+oldId).css("backgroundColor","#fff");
					$("#main_"+oldId+" td").css("color","#161616");
					closePrevDetail2(scheId);
				}catch(e){
				}
			}
			$(detailObj).show();
			$("#down_arrow_"+scheId).hide();
			$("#up_arrow_"+scheId).show();
			$("#main_"+scheId).css("backgroundColor","#eee");
			$("#content_show_"+scheId).removeClass("ellipsisDiv_full");
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
				hideDetail("td_newType_"+scheId);
			}catch(e){}
			try{
				hideDetail("td_targetPointCd_"+scheId);
			}catch(e){}
			try{
				//hideDetail("td_area_"+scheId);
			}catch(e){}
			try{
				hideDetail("td_content_"+scheId);
			}catch(e){}
			try{
				hideDetail("td_targetDate_"+scheId);
			}catch(e){}
			try{
				hideDetail("td_remark_"+scheId);
			}catch(e){}
			$("#main_"+scheId).css("backgroundColor","#fff");
			$("#content_show_"+scheId).addClass("ellipsisDiv_full");
			$("#chk_"+scheId).css("backgroundColor","");
			$("#img_"+scheId).css("backgroundColor","");
			$("#img_"+scheId).css("borderRight","");
		}
		oldId =scheId;
	}else{
		CAN_scheClick = true;
	}
	}catch(e){}
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
	try{$("#search_planMonth").val(now_month);}catch(e){}
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
			case "serialOrder" :
				$("#order_updatedDate").html("");
				$("#order_targetDate").html("");
				break;
			case "targetDate" :
				$("#order_serialOrder").html("");
				$("#order_updatedDate").html("");
				break;
			case "updatedDate" :
				$("#order_serialOrder").html("");
				$("#order_targetDate").html("");
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
	$.post(_ctx+"/plan/plan-work2!doUpdateStatusAll.action", param, function(result) {
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
function updateStatusCd(planWork2Id,statusCd) {
	var sourcePlanType = $("#scheForm_"+planWork2Id+" input[name='planType']").val();
	var sourceStatusCd = $("#scheForm_"+planWork2Id+" input[name='statusCd']").val();
//	if(statusCd==sourceStatusCd && 8!=statusCd && 9!=statusCd){
//		//如果状态没改变，不修改记录
//		return;
//	}
	/*
	if(7==sourceStatusCd){
		alert("非本月任务不能修改状态");
		return;
	}
	if(1==sourcePlanType && (0==statusCd || 3==statusCd || 5==statusCd || 6==statusCd)){
		alert("年计划无法删除");
		return;
	}
	*/
	var targetStatusCd = 0;
	if(null!=statusCd){
		targetStatusCd = statusCd;
		updateRecord(planWork2Id, "statusCd", targetStatusCd);
		updateTdStatusHtml(planWork2Id, targetStatusCd);
		$("#scheForm_"+planWork2Id+" input[name='statusCd']").val(targetStatusCd);
	}else{
		//记录列里直接点击图标进行确定/未确定
		if (0==sourceStatusCd || 1==sourceStatusCd) {
			if(0==sourceStatusCd){
				targetStatusCd = 1;
			}else if(1==sourceStatusCd){
				targetStatusCd = 0;
			}
			updateRecord(planWork2Id, "statusCd", targetStatusCd);
			updateTdStatusHtml(planWork2Id, targetStatusCd);
			$("#scheForm_"+planWork2Id+" input[name='statusCd']").val(targetStatusCd);
		} else {
			scheClick(planWork2Id);
		}
	}
}
//退回
function doCallback(planWork2Id) {
	var record_planYear = $("#scheForm_"+planWork2Id+" input[name='planYear']").val();
	var record_planMonth = $("#scheForm_"+planWork2Id+" input[name='planMonth']").val();
//	var record_ifSuspend = $("#scheForm_"+planWork2Id+" input[name='ifSuspend']").val();
	if(1!=if_in_weight && (record_planYear!=now_year || record_planMonth!=now_month)){
		alert("当前记录已推至"+record_planYear+"年"+record_planMonth+"月");
		return;
	}
//	if(1!=record_ifSuspend){
//		alert("非过期任务不能退回");
//		return;
//	}
	record_planMonth--;
	var data = {
			id:planWork2Id,
			now_year:now_year,
			now_month:now_month,
			newMessage:"【驳回到"+record_planMonth+"月】"
			};
	$.post(_ctx+"/plan/plan-work2!doCallback.action", data, function(result) {
		if("success"==result){
			$("#succInfoMsg").html("已成功驳回").show().fadeOut(2000);
			refreshMain(planWork2Id);
			refreshDetail(planWork2Id);
		}else{
			$("#succInfoMsg").html("驳回失败,请刷新").show().fadeOut(2000);
		}
	});
}
//更新记录的statusCd和显示出来的图标
function updateTdStatusHtml(planWork2Id,statusCd){
	var html_str = "";
	if(null!=statusCd){
		switch(statusCd){
			case 0:
				html_str = "<img src='"+_ctx+"/resources/images/common/status_unconfirm.gif' title='未确认'>";
				break;
			case 1:
				html_str = "<img src='"+_ctx+"/resources/images/common/status_confirm.gif' title='进行中'>";
				break;
			case 2:
				html_str = "<img src='"+_ctx+"/resources/images/common/status_prefinish.gif' title='预完成'>";
				break;
			case 3:
				html_str = "<img src='"+_ctx+"/resources/images/common/status_predel.gif' title='申请删除'>";
				break;
			case 4:
				html_str = "<img src='"+_ctx+"/resources/images/common/status_finish.gif' title='已完成'>";
				break;
			case 5:
				html_str = "<img src='"+_ctx+"/resources/images/common/status_del.gif' title='已删除'>";
				break;
			case 6:
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
				html_str = "<img src='"+_ctx+"/resources/images/common/status_completeDely.gif' title='完成但曾过期'>";
				break;
		}
		$("#scheForm_"+planWork2Id+" input[name='statusCd']").val(statusCd);
		$("#td_statusCd_"+planWork2Id).html(html_str);
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
	$.post(_ctx+"/plan/plan-work2!doExchangeOrder.action", param+"&"+param2, function(result) {
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
function openAttachment(title,entityId,onlyCreator){
	if("新增附件管理"==title){
		cur_entityId = null;
	}else{
		cur_entityId = entityId;
	}
	//var rr=new getClientBounds();
	//var rrtop=(rr.height-300)/2 + $("body",top.document).scrollTop();
	//alert($("#div_home_b",top.document).scrollTop());
	ymPrompt.win({
		message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=planWork2&filterType=image|office&bizEntityName=PlanWork2&onlyCreator="+onlyCreator,
		width:500,
		height:300,
		title:title,
		iframe:true,
		winPos: 'c',
		handler : attachRefresh
		});
}
function getClientBounds(){  
    var clientWidth;  
    var clientHeight;  
      
    clientWidth = document.compatMode == "CSS1Compat" ?  
        document.documentElement.clientWidth : document.body.clientWidth;  
    clientHeight = document.compatMode == "CSS1Compat" ?  
        document.documentElement.clientHeight : document.body.clientHeight;  
          
    return {width: clientWidth, height: clientHeight};  
}
function attachRefresh(){
	if(null!=cur_entityId){
		refreshMain(cur_entityId);
	}else{
		$("#new_img_atta").attr("src",_ctx+"/resources/images/common/atta_y.gif");
	}
}
function onkeypressPoint(e,planWork2Id,operType,operValue){
	var keyCode;
	if($.browser.msie){
		keyCode = e.keyCode;
	}else{
		keyCode = e.which;
	}
	if(keyCode==13){
		updateRecord(planWork2Id,operType,operValue);
	}
}
// 提醒功能, 给负责人发信
function remind(planWork2Id) {
	$.post(_ctx + "/plan/plan-work2!remind.action?id=" + planWork2Id, function(result) {
		if (result == "done") {
			$("#succInfoMsg").html("提醒邮件已发送").show().fadeOut(2000);
		}
	});
}
//改变centerStatus状态
function changeCenterStatus(){
	var toStatus = $("#centerStatusBtn").attr("myValue");
	if(judgeIfHasSomeRole("A_PLAN_WORK2_ADMIN")){
		if(null!=$('#select_center_status').val()
				&&""!=$('#select_center_status').val()){
			toStatus = $('#select_center_status').val();
		}
	}
	if(null==toStatus || ""==toStatus){
		alert("操作失效，请先刷新页面。");
		return;
	}
	if(!judgeIfHasSomeRole("A_PLAN_WORK2_ADMIN")){
		if(5==toStatus || 6==toStatus || 7==toStatus || 8==toStatus || 9==toStatus){
			//判断是否都给分了
			if(!checkPoint(toStatus)){
				return false;
			}
		}
		if(5==toStatus){
			//判断权重分合理性
			if(!checkWeightPoint()){
				return false;
			}
		}
	}
	if(""==centerCd){
		alert("请先选择中心");
		return;
	}
	var if_admin_change = 0;
	if(judgeIfHasSomeRole("A_PLAN_WORK2_ADMIN")){
		//如果是中心月计划管理员，修改状态不发邮件
		if_admin_change = 1;
	}
	var data = {
			center_status : toStatus,
			centerCd : centerCd,
			now_year : now_year,
			now_month : now_month,
			now_quarter : now_quarter,
			if_in_weight : if_in_weight,
			if_goto_cost : if_goto_cost,
			if_admin_change : if_admin_change
	};
	$.post(_ctx + "/plan/plan-work2!savePlanWork2Status.action", data, function(result) {
		if ("success"==result) {
			$("#succInfoMsg").html("状态已修改").show().fadeOut(2000);
			centerClick();
		}else{
			$("#succInfoMsg").html("修改失败！！！请重新刷新页面！！！").show().fadeOut(2000);
		}
	});
}

//按钮的控制，只列出显示的部分，其余的隐藏
function btnControl(btnList){
	try{
		if(null==btnList){
			btnList = new Array();
		}
	}catch(e){}
	try{
		if(btnList.contains("None")){
			$(".saveNoneBtn").show();
		}else{
			$(".saveNoneBtn").hide();
		}
	}catch(e){}
	try{
		if(btnList.contains("0")){
			$(".save0Btn").show();
		}else{
			$(".save0Btn").hide();
		}
	}catch(e){}
	try{
		if(btnList.contains("1")){
			$(".save1Btn").show();
		}else{
			$(".save1Btn").hide();
		}
	}catch(e){}
	try{
		if(btnList.contains("2")){
			$(".save2Btn").show();
		}else{
			$(".save2Btn").hide();
		}
	}catch(e){}
	try{
		if(btnList.contains("3")){
			$(".save3Btn").show();
		}else{
			$(".save3Btn").hide();
		}
	}catch(e){}
	try{
		if(btnList.contains("4")){
			$(".save4Btn").show();
		}else{
			$(".save4Btn").hide();
		}
	}catch(e){}
	try{
		if(btnList.contains("5")){
			$(".save5Btn").show();
		}else{
			$(".save5Btn").hide();
		}
	}catch(e){}
	try{
		if(btnList.contains("6")){
			$(".save6Btn").show();
		}else{
			$(".save6Btn").hide();
		}
	}catch(e){}
	try{
		if(btnList.contains("7")){
			$(".save7Btn").show();
		}else{
			$(".save7Btn").hide();
		}
	}catch(e){}
	try{
		if(btnList.contains("8")){
			$(".save8Btn").show();
		}else{
			$(".save8Btn").hide();
		}
	}catch(e){}
	try{
		if(btnList.contains("9")){
			$(".save9Btn").show();
		}else{
			$(".save9Btn").hide();
		}
	}catch(e){}
	try{
		if(btnList.contains("10")){
			$(".save10Btn").show();
		}else{
			$(".save10Btn").hide();
		}
	}catch(e){}
	try{
		if(btnList.contains("Callback")){
			$(".saveCallbackBtn").show();
		}else{
			$(".saveCallbackBtn").hide();
		}
	}catch(e){}
}

//操作中心状态按钮
function controlCenterStatusBtn(myValue,myText){
	if(null!=myValue && ""!=myValue){
		if(null==myText || ""==myText){
			myText = "确认完毕";
		}
		$("#centerStatusBtn").attr("myValue",myValue);
		$("#centerStatusBtn").val(myText);
		$("#centerStatusBtn").show();
	}else{
		$("#centerStatusBtn").attr("myValue","");
		$("#centerStatusBtn").val("");
		$("#centerStatusBtn").hide();
	}
}

//判断用户是否具有某角色
function judgeIfHasSomeRole(judgeRole){
	for(var i=0;null!=myRoles && i<myRoles.length;i++){
		var testRole = myRoles[i];
		if(""!=testRole && ""!=judgeRole && testRole==judgeRole){
			return true;
		}
	}
	return false;
}

//根据部门和类型，获得权重分的和的最大值
function getTopPoint(myPlanType){
	var top_weightPoint1 = Number(0);
	var top_weightPoint2 = Number(0);
	var top_weightPoint3 = Number(0);
	if("147"==centerCd){
		//监审部
		top_weightPoint1 = 50;
		top_weightPoint2 = 40;
		top_weightPoint3 = 0;
	}else if("7"==centerCd){
		//总裁办
		top_weightPoint1 = 60;
		top_weightPoint2 = 25;
		top_weightPoint3 = 5;
	}else if("9"==centerCd){
		//人力资源管理中心
		top_weightPoint1 = 50;
		top_weightPoint2 = 30;
		top_weightPoint3 = 10;
	}else if("8"==centerCd){
		//财务管理中心
		top_weightPoint1 = 50;
		top_weightPoint2 = 30;
		top_weightPoint3 = 10;
	}else if("125"==centerCd){
		//投资关系部
		top_weightPoint1 = 50;
		top_weightPoint2 = 40;
		top_weightPoint3 = 0;
	}else if("10"==centerCd){
		//资本管理中心
		top_weightPoint1 = 50;
		top_weightPoint2 = 40;
		top_weightPoint3 = 0;
	}else if("11"==centerCd){
		//投资发展中心
		top_weightPoint1 = 50;
		top_weightPoint2 = 30;
		top_weightPoint3 = 10;
	}else if("126"==centerCd){
		//商业地产研究院
		top_weightPoint1 = 40;
		top_weightPoint2 = 20;
		top_weightPoint3 = 30;
	}else if("12"==centerCd){
		//技术研发中心
		top_weightPoint1 = 20;
		top_weightPoint2 = 20;
		top_weightPoint3 = 50;
	}else if("132"==centerCd){
		//项目管理中心
		top_weightPoint1 = 70;
		top_weightPoint2 = 10;
		top_weightPoint3 = 10;
	}else if("133"==centerCd){
		//营销管理中心
		top_weightPoint1 = 30;
		top_weightPoint2 = 10;
		top_weightPoint3 = 50;
	}else if("17"==centerCd){
		//成本控制中心
		top_weightPoint1 = 40;
		top_weightPoint2 = 10;
		top_weightPoint3 = 40;
	}
	if(1==myPlanType){
		return top_weightPoint1;
	}else if(2==myPlanType){
		return top_weightPoint2;
	}else if(3==myPlanType){
		return top_weightPoint3;
	}else if(5==myPlanType){
		return 10;
	}else{
		return 0;
	}
}
//给权重分完后，判断剩余分数
function getRestWeightPoint(planWork2Id,topPlanType){
	var topPoint = getTopPoint(topPlanType);
	var totalPoint = Number(0);
	for(var i=0;i<arr_ids.length;i++){
		var myId = arr_ids[i];
		var myPoint = Number(0);
		var myPlanType = $("#scheForm_"+myId+" input[name='planType']").val();
		if(topPlanType==myPlanType){
			myPoint = Number($("#weightPoint_show_"+myId).html());
			if(null!=myPoint && NaN!=myPoint && ""!=myPoint){
				totalPoint += myPoint;
			}
		}
	}
	return topPoint-totalPoint;
}
//判断权重分合理性
function checkWeightPoint(){
	var sum_weightPoint1 = new Number(0);
	var sum_weightPoint2 = new Number(0);
	var sum_weightPoint3 = new Number(0);
	var sum_weightPoint5 = new Number(0);
	for(var i=0;i<arr_ids.length;i++){
		var myId = arr_ids[i];
		var myWeightPoint = Number($("#weightPoint_show_"+myId).html());
		var myPlanType = $("#scheForm_"+myId+" input[name='planType']").val();
		if(1==myPlanType){
			sum_weightPoint1 += myWeightPoint;
		}else if(2==myPlanType){
			sum_weightPoint2 += myWeightPoint;
		}else if(3==myPlanType){
			sum_weightPoint3 += myWeightPoint;
		}else if(5==myPlanType){
			sum_weightPoint5 += myWeightPoint;
		}
	}
	
	var top_weightPoint1 = Number(getTopPoint(1));
	var top_weightPoint2 = Number(getTopPoint(2));
	var top_weightPoint3 = Number(getTopPoint(3));
	var top_weightPoint5 = Number(getTopPoint(5));
	if(top_weightPoint1!=sum_weightPoint1){
		//alert("年计划权重分相加之和必须是"+top_weightPoint1+"，现在是"+sum_weightPoint1);
		//return false;
	}
	if(top_weightPoint2!=sum_weightPoint2){
		//alert("月会工作权重分相加之和必须是"+top_weightPoint2+"，现在是"+sum_weightPoint2);
		//return false;
	}
	if(top_weightPoint3!=sum_weightPoint3){
		//alert("项目管理权重分相加之和必须是"+top_weightPoint3+"，现在是"+sum_weightPoint3);
		//return false;
	}
	if(top_weightPoint5!=sum_weightPoint5){
		//alert("综合权重分相加之和必须是"+top_weightPoint5+"，现在是"+sum_weightPoint5);
		//return false;
	}
	return true;
}

//判断各评分的合理性，必须都填
function checkPoint(toQuarterStatus){
	var ifNotPass = false;
	for(var i=0;i<arr_ids.length;i++){
		var myId = arr_ids[i];
		var myPoint = Number(0);
		if(5==toQuarterStatus){
			myPoint = Number($("#weightPoint_show_"+myId).html());
		}else if(6==toQuarterStatus){
			myPoint = Number($("#selfPoint_show_"+myId).html());
		}else if(7==toQuarterStatus){
			myPoint = Number($("#selfCheckPoint_show_"+myId).html());
		}else if(8==toQuarterStatus){
			myPoint = Number($("#evaluatePoint_show_"+myId).html());
		}else if(9==toQuarterStatus){
			myPoint = Number($("#finalPoint_show_"+myId).html());
		}
		if(null==myPoint || isNaN(myPoint) || ""==myPoint){
			/*201201删除
			alert("还有任务未给分,请给所有任务都赋予一个分数");
			ifNotPass = true;
			break;
			*/
		}
	}
	if(ifNotPass){
		return false;
	}else{
		return true;
	}
}

//根据中心把假权重分换算成真权重分(暂时不用了，直接返回原分数)
function convertWeightPoint(bogusWeightPoint,planType){
	return bogusWeightPoint;
	var returnValue = Number(0);
	if("147"==centerCd){
		//监审部
		if(1==planType){
			returnValue = Number(bogusWeightPoint*50/180).toFixed(2);
		}else if(2==planType){
			returnValue = Number(bogusWeightPoint*40/100).toFixed(2);
		}
	}else if("7"==centerCd){
		//总裁办
		if(1==planType){
			returnValue = Number(bogusWeightPoint*30/180).toFixed(2);
		}else if(2==planType){
			returnValue = Number(bogusWeightPoint*25/100).toFixed(2);
		}
	}else if("9"==centerCd){
		//人力资源管理中心
		if(1==planType){
			returnValue = Number(bogusWeightPoint*50/180).toFixed(2);
		}else if(2==planType){
			returnValue = Number(bogusWeightPoint*30/100).toFixed(2);
		}
	}else if("8"==centerCd){
		//财务管理中心
		if(1==planType){
			returnValue = Number(bogusWeightPoint*50/180).toFixed(2);
		}else if(2==planType){
			returnValue = Number(bogusWeightPoint*30/100).toFixed(2);
		}
	}else if("125"==centerCd){
		//投资关系部
		if(1==planType){
			returnValue = Number(bogusWeightPoint*50/180).toFixed(2);
		}else if(2==planType){
			returnValue = Number(bogusWeightPoint*40/100).toFixed(2);
		}
	}else if("10"==centerCd){
		//资本管理中心
		if(1==planType){
			returnValue = Number(bogusWeightPoint*50/180).toFixed(2);
		}else if(2==planType){
			returnValue = Number(bogusWeightPoint*40/100).toFixed(2);
		}
	}else if("11"==centerCd){
		//投资发展中心
		if(1==planType){
			returnValue = Number(bogusWeightPoint*50/180).toFixed(2);
		}else if(2==planType){
			returnValue = Number(bogusWeightPoint*30/100).toFixed(2);
		}
	}else if("126"==centerCd){
		//商业地产研究院
		if(1==planType){
			returnValue = Number(bogusWeightPoint*40/180).toFixed(2);
		}else if(2==planType){
			returnValue = Number(bogusWeightPoint*20/100).toFixed(2);
		}
	}else if("12"==centerCd){
		//技术研发中心
		if(1==planType){
			returnValue = Number(bogusWeightPoint*20/180).toFixed(2);
		}else if(2==planType){
			returnValue = Number(bogusWeightPoint*20/100).toFixed(2);
		}
	}else if("132"==centerCd){
		//项目管理中心
		if(1==planType){
			returnValue = Number(bogusWeightPoint*30/180).toFixed(2);
		}else if(2==planType){
			returnValue = Number(bogusWeightPoint*10/100).toFixed(2);
		}
	}else if("133"==centerCd){
		//营销管理中心
		if(1==planType){
			returnValue = Number(bogusWeightPoint*30/180).toFixed(2);
		}else if(2==planType){
			returnValue = Number(bogusWeightPoint*10/100).toFixed(2);
		}
	}else if("17"==centerCd){
		//成本控制中心
		if(1==planType){
			returnValue = Number(bogusWeightPoint*40/180).toFixed(2);
		}else if(2==planType){
			returnValue = Number(bogusWeightPoint*10/100).toFixed(2);
		}
	}
	if(0==returnValue){
		returnValue = bogusWeightPoint;
	}
	return returnValue;
}

//更新用户的操作权限和状态
function refreshMyOper(){
	if(1==if_in_weight){
		if(""==quarter_status || "0"==quarter_status || "1"==quarter_status || "2"==quarter_status || "3"==quarter_status){
			$("#centerStatusMsg").html("未开始评分");
		}
	}else{
		if(""==center_status || "0"==center_status){
			$("#centerStatusMsg").html("各中心新增中");
		}else if("1"==center_status){
			$("#centerStatusMsg").html("各中心新增完毕");
		}else if("2"==center_status){
			$("#centerStatusMsg").html("副总裁确认新增完毕");
		}else if("3"==center_status){
			$("#centerStatusMsg").html("计划已确认，开始执行");
		}
	}
	if("4"==quarter_status){
		$("#centerStatusMsg").html("开始给权重分");
	}else if("5"==quarter_status){
		$("#centerStatusMsg").html("总裁给权重分完毕");
	}else if("6"==quarter_status){
		$("#centerStatusMsg").html("各中心自评完毕");
	}else if("7"==quarter_status){
		$("#centerStatusMsg").html("副总裁评分完毕");
	}else if("8"==quarter_status){
		$("#centerStatusMsg").html("营运管理中心或者项目管理中心考评完毕");
	}else if("9"==quarter_status){
		$("#centerStatusMsg").html("总裁给最终分数完毕");
	}

	can_edit_record = false;
	can_edit_point = false;
	$(".planType_class").attr("disabled","disabled");
	controlCenterStatusBtn();
	if(judgeIfHasSomeRole("A_PLAN_WORK2_CENTER")){
		if(isEditOrg){
			can_edit_point = true;
		}
		if(1!=if_in_weight){
			if("0"==center_status){
				if(isEditOrg){
					can_edit_record = true;
					controlCenterStatusBtn(1);
					if(1!=if_in_weight){
						$("#NewBtn").show();
					}
					$("#td_all_operate_btn").show();
					$("#operate_all_div").show();
					btnControl(new Array("None","2","3"));
				}else{
					controlCenterStatusBtn();
					$("#NewBtn").hide();
				}
			}else{
				controlCenterStatusBtn();
				$("#NewBtn").hide();
				$("#td_all_operate_btn").hide();
				$("#operate_all_div").hide();
				
				if("3"==center_status){
					if(isEditOrg){
						btnControl(new Array("2","3"));
					}
				}
			}
		}
		if(1==if_in_weight && 5==quarter_status){
			controlCenterStatusBtn(6,"自评完毕");
		}
	}
	if(judgeIfHasSomeRole("A_PLAN_WORK2_VICE")){
		if(isEditOrg){
			can_edit_point = true;
		}
		if(1!=if_in_weight){
			if("0"==center_status){
				if(isEditOrg){
					can_edit_record = true;
					if(1!=if_in_weight){
						$("#NewBtn").show();
					}
					btnControl(new Array("None","2","3"));
					$("#td_all_operate_btn").show();
					$("#operate_all_div").show();
				}else{
					$("#NewBtn").hide();
				}
			}else if("1"==center_status){
				if(isEditOrg){
					can_edit_record = true;
					controlCenterStatusBtn(2);
					if(1!=if_in_weight){
						$("#NewBtn").show();
					}
					$("#td_all_operate_btn").show();
					$("#operate_all_div").show();
					btnControl(new Array("None","2","3"));
				}else{
					$("#NewBtn").hide();
				}
			}else{ 
				controlCenterStatusBtn();
				$("#NewBtn").hide();
				$("#td_all_operate_btn").hide();
				$("#operate_all_div").hide();
				btnControl();
				if("3"==center_status){
					if(isEditOrg){
						btnControl(new Array("1","2","3"));
						$(".save1Btn").html("退回");
					}
				}
			}
		}
		if(1==if_in_weight && 6==quarter_status){
			controlCenterStatusBtn(7,"复评完毕");
		}
	}
	if(judgeIfHasSomeRole("A_PLAN_WORK2_PROJECT")){
		if(1==if_in_weight && (5==quarter_status||6==quarter_status||7==quarter_status)){
			controlCenterStatusBtn(8,"考评完毕");
		}
	}
	if(judgeIfHasSomeRole("A_PLAN_WORK2_OFFICE")){
		$(".planType_class").attr("disabled","");
		can_edit_point = true;
		if(1!=if_in_weight){
			if("0"==center_status || "1"==center_status){
				can_edit_record = true;
				if(1!=if_in_weight){
					$("#NewBtn").show();
				}
				$("#td_all_operate_btn").show();
				$("#operate_all_div").show();
				btnControl(new Array("None","0","1","5","8","9","Callback"));
			}else if("2"==center_status){
				can_edit_record = true;
				controlCenterStatusBtn(3);
				if(1!=if_in_weight){
					$("#NewBtn").show();
				}
				$("#td_all_operate_btn").show();
				$("#operate_all_div").show();
				btnControl(new Array("None","0","1","5","8","9","Callback"));
			}else if("3"==center_status){
				can_edit_record = true;
				controlCenterStatusBtn();
				if(1!=if_in_weight){
					$("#NewBtn").show();
				}
				$(".save1Btn").html("退回");
				btnControl(new Array("None","0","1","4","10","8","9","Callback"));
			}else{
				controlCenterStatusBtn();
				$("#NewBtn").hide();
				$("#operate_all_div").hide();
				$("#td_all_operate_btn").hide();
				can_edit_record = false;
				btnControl();
			}
		}
		if(1==if_in_weight && (0==quarter_status || 1==quarter_status || 2==quarter_status || 3==quarter_status)){
			controlCenterStatusBtn(4,"开始评分");
		}
		if(1==if_in_weight && 4==quarter_status){
			controlCenterStatusBtn(5,"提交权重分");
		}
		if(1==if_in_weight && 7==quarter_status){
			controlCenterStatusBtn(8,"考评完毕");
		}
	}
	if(judgeIfHasSomeRole("A_PLAN_WORK2_CEO")){
		$(".planType_class").attr("disabled","");
		can_edit_record = true;
		can_edit_point = true;
		if(1==if_in_weight && 4==quarter_status){
			controlCenterStatusBtn(5,"提交权重分");
		}else if(1==if_in_weight && 8==quarter_status){
			controlCenterStatusBtn(9,"考评完毕");
		}
	}
	if(judgeIfHasSomeRole("A_PLAN_WORK2_ADMIN")){
		$(".planType_class").attr("disabled","");
		can_edit_record = true;
		can_edit_point = true;
		$("#span_select_center_status").show();
		$("#centerStatusBtn").val("改变状态");
		$("#centerStatusBtn").show();
		btnControl(new Array("None","0","1","2","3","4","5","6","7","8","9","10","Callback"));
		if(1!=if_in_weight){
			$("#NewBtn").show();
		}
		if("1"==center_status){
			controlCenterStatusBtn(3,"开始执行");
		}
	}
	
	//如果在评分状态，无法编辑内容和按按钮
	if(Number(quarter_status)>3){
		$("#NewBtn").hide();
		$("#operate_all_div").hide();
		$("#td_all_operate_btn").hide();
		can_edit_record = false;
		btnControl();
	}
	if("shisn"==myUiid){
		btnControl(new Array("None","0","1","2","3","4","5","6","7","8","9","10","Callback"));
		$("#centerStatusBtn").hide();
		$("#NewBtn").show();
	}
}