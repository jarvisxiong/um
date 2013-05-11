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
function centerClick(search_year,search_centerCd){
	if(!IF_IN_LOADING){
		IF_IN_LOADING = true;
		if(null!=search_year && ""!=search_year){
			now_year = search_year;
		}
		if(null!=search_centerCd && ""!=search_centerCd){
			centerCd = search_centerCd;
		}
		var page_no = 1;
		var page_size = 999;
		var data = {
				search_statusCd : $("#search_statusCd").val(),
				centerCd : centerCd,
				now_year : now_year,
				"page.pageNo" : page_no,
				"page.pageSize" : page_size,
				filter_LIKES_workTarget : $("#filter_LIKES_workTarget").val(),
				filter_LIKES_detailStep : $("#filter_LIKES_detailStep").val()
		};
		$("#scheduleDiv").html("<div style='height:100px'></div><table width='100%'><tr><td align='center'><img src='"+_ctx+"/resources/images/common/loading.gif'/></td></tr></table>");
		$.post(_ctx+"/plan/plan-work2-year!list.action", data, function(result) {
			$("#scheduleDiv").html(result);
			IF_IN_LOADING = false;
			autoHeight();
		});
	}
}
//切换中心事件
function doChangeCenter(dom){
	centerCd=$(dom).val();
	$("#pageNo").val(0);
	if (!isEmpty(centerCd)){
		centerClick($("#filter_EQs_planYear").val());
	}
}
//页面跳转函数的覆盖
function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	centerClick();
}
//新增的保存
function addSavePlanWork2Year(dataId){
	if($("#new_workTarget").val()==""){
		alert("请输入“年度重点工作目标 ”");
		return false;
	}
	/*
	if($("#new_accordingType1").attr('checked')==false){
		$("#newScheForm input[name='accordingType1']").val(0);
	}else{
		$("#newScheForm input[name='accordingType1']").val(1);
	}
	if($("#new_accordingType2").attr('checked')==false){
		$("#newScheForm input[name='accordingType2']").val(0);
	}else{
		$("#newScheForm input[name='accordingType2']").val(1);
	}
	if($("#new_accordingType3").attr('checked')==false){
		$("#newScheForm input[name='accordingType3']").val(0);
	}else{
		$("#newScheForm input[name='accordingType3']").val(1);
	}
	if($("#new_accordingType4").attr('checked')==false){
		$("#newScheForm input[name='accordingType4']").val(0);
	}else{
		$("#newScheForm input[name='accordingType4']").val(1);
	}
	*/
	$("#newScheForm input[name='planYear']").val(NOW_YEAR);
	$("#newScheForm input[name='workTarget']").val($("#new_workTarget").val());
	$("#newScheForm input[name='detailStep']").val($("#new_detailStep").val());
	if(""==$("#newScheForm input[name='detailStep']").val() || null==$("#newScheForm input[name='detailStep']").val()){
		$("#newScheForm input[name='detailStep']").val(" ");
	}
	//$("#newScheForm input[name='coordinateCenterNames']").val($("#new_coordinateCenterNames").val());
	if($("#new_weightPoint1").attr('checked')==false){
		$("#newScheForm input[name='weightPoint1']").val(0);
	}else{
		$("#newScheForm input[name='weightPoint1']").val(1);
	}
	if($("#new_weightPoint2").attr('checked')==false){
		$("#newScheForm input[name='weightPoint2']").val(0);
	}else{
		$("#newScheForm input[name='weightPoint2']").val(1);
	}
	if($("#new_weightPoint3").attr('checked')==false){
		$("#newScheForm input[name='weightPoint3']").val(0);
	}else{
		$("#newScheForm input[name='weightPoint3']").val(1);
	}
	if($("#new_weightPoint4").attr('checked')==false){
		$("#newScheForm input[name='weightPoint4']").val(0);
	}else{
		$("#newScheForm input[name='weightPoint4']").val(1);
	}
	if($("#new_weightPoint5").attr('checked')==false){
		$("#newScheForm input[name='weightPoint5']").val(0);
	}else{
		$("#newScheForm input[name='weightPoint5']").val(1);
	}
	if($("#new_weightPoint6").attr('checked')==false){
		$("#newScheForm input[name='weightPoint6']").val(0);
	}else{
		$("#newScheForm input[name='weightPoint6']").val(1);
	}
	if($("#new_weightPoint7").attr('checked')==false){
		$("#newScheForm input[name='weightPoint7']").val(0);
	}else{
		$("#newScheForm input[name='weightPoint7']").val(1);
	}
	if($("#new_weightPoint8").attr('checked')==false){
		$("#newScheForm input[name='weightPoint8']").val(0);
	}else{
		$("#newScheForm input[name='weightPoint8']").val(1);
	}
	if($("#new_weightPoint9").attr('checked')==false){
		$("#newScheForm input[name='weightPoint9']").val(0);
	}else{
		$("#newScheForm input[name='weightPoint9']").val(1);
	}
	if($("#new_weightPoint10").attr('checked')==false){
		$("#newScheForm input[name='weightPoint10']").val(0);
	}else{
		$("#newScheForm input[name='weightPoint10']").val(1);
	}
	if($("#new_weightPoint11").attr('checked')==false){
		$("#newScheForm input[name='weightPoint11']").val(0);
	}else{
		$("#newScheForm input[name='weightPoint11']").val(1);
	}
	if($("#new_weightPoint12").attr('checked')==false){
		$("#newScheForm input[name='weightPoint12']").val(0);
	}else{
		$("#newScheForm input[name='weightPoint12']").val(1);
	}
	var now_serialOrder = $("#newScheForm input[name='serialOrder']").val();	//现在的序列号
	$("#newScheForm input[name='centerCd']").val(centerCd);
	
	var param = {centerCd:dataId,respDeptCds:dataId};
	$("#newScheForm").attr("action",_ctx+"/plan/plan-work2-year!save.action");
	$("#newScheForm").ajaxSubmit(function(result) {
		if (result) {
			now_serialOrder++;	//新增成功，序列号加1
			var new_record_html = '<tr id="main_'+result+'" class="mainTr" style="cursor:pointer;"><td colspan="17"></td></tr>';
			$("#newScheTemplate2").after(new_record_html);
			refreshMain(result);
			$("#newScheForm input[name='serialOrder']").val(now_serialOrder);
			$("#new_workTarget").val("");
			$("#new_detailStep").val("");
			//$("#new_coordinateCenterNames").val("");
			$("#new_accordingType1").attr('checked',false);
			$("#new_accordingType2").attr('checked',false);
			$("#new_accordingType3").attr('checked',false);
			$("#new_accordingType4").attr('checked',false);
			$("#new_weightPoint1").attr('checked',false);
			$("#new_weightPoint2").attr('checked',false);
			$("#new_weightPoint3").attr('checked',false);
			$("#new_weightPoint4").attr('checked',false);
			$("#new_weightPoint5").attr('checked',false);
			$("#new_weightPoint6").attr('checked',false);
			$("#new_weightPoint7").attr('checked',false);
			$("#new_weightPoint8").attr('checked',false);
			$("#new_weightPoint9").attr('checked',false);
			$("#new_weightPoint10").attr('checked',false);
			$("#new_weightPoint11").attr('checked',false);
			$("#new_weightPoint12").attr('checked',false);
			cancelSchedule();
			try{
				$("#noResult_td").html("");
			}catch(e){}
			$("#pop_bg").hide();
		}
	});
	$("#pop_bg").show();
}
//修改记录
function savePlanWork2Year(planWork2YearId,type){
	$("#scheForm_"+planWork2YearId+" input[name='serialOrder']").val($("#serialOrder_input_"+planWork2YearId).val());
	/*
	if($("#accordingType1_input_"+planWork2YearId).attr('checked')==false){
		$("#scheForm_"+planWork2YearId+" input[name='accordingType1']").val(0);
	}else{
		$("#scheForm_"+planWork2YearId+" input[name='accordingType1']").val(1);
	}
	if($("#accordingType2_input_"+planWork2YearId).attr('checked')==false){
		$("#scheForm_"+planWork2YearId+" input[name='accordingType2']").val(0);
	}else{
		$("#scheForm_"+planWork2YearId+" input[name='accordingType2']").val(1);
	}
	if($("#accordingType3_input_"+planWork2YearId).attr('checked')==false){
		$("#scheForm_"+planWork2YearId+" input[name='accordingType3']").val(0);
	}else{
		$("#scheForm_"+planWork2YearId+" input[name='accordingType3']").val(1);
	}
	if($("#accordingType4_input_"+planWork2YearId).attr('checked')==false){
		$("#scheForm_"+planWork2YearId+" input[name='accordingType4']").val(0);
	}else{
		$("#scheForm_"+planWork2YearId+" input[name='accordingType4']").val(1);
	}
	*/
	$("#scheForm_"+planWork2YearId+" input[name='workTarget']").val($("#workTarget_input_"+planWork2YearId).val());
	$("#scheForm_"+planWork2YearId+" input[name='detailStep']").val($("#detailStep_input_"+planWork2YearId).val());
	$("#scheForm_"+planWork2YearId+" input[name='coordinateCenterCds']").val($("#coordinateCenterCds_input_"+planWork2YearId).val());
//	$("#scheForm_"+planWork2YearId+" input[name='weightPoint1']").val($("#weightPoint1_input_"+planWork2YearId).val());
//	$("#scheForm_"+planWork2YearId+" input[name='weightPoint2']").val($("#weightPoint2_input_"+planWork2YearId).val());
//	$("#scheForm_"+planWork2YearId+" input[name='weightPoint3']").val($("#weightPoint3_input_"+planWork2YearId).val());
//	$("#scheForm_"+planWork2YearId+" input[name='weightPoint4']").val($("#weightPoint4_input_"+planWork2YearId).val());
//	$("#scheForm_"+planWork2YearId+" input[name='weightPoint5']").val($("#weightPoint5_input_"+planWork2YearId).val());
//	$("#scheForm_"+planWork2YearId+" input[name='weightPoint6']").val($("#weightPoint6_input_"+planWork2YearId).val());
//	$("#scheForm_"+planWork2YearId+" input[name='weightPoint7']").val($("#weightPoint7_input_"+planWork2YearId).val());
//	$("#scheForm_"+planWork2YearId+" input[name='weightPoint8']").val($("#weightPoint8_input_"+planWork2YearId).val());
//	$("#scheForm_"+planWork2YearId+" input[name='weightPoint9']").val($("#weightPoint9_input_"+planWork2YearId).val());
//	$("#scheForm_"+planWork2YearId+" input[name='weightPoint10']").val($("#weightPoint10_input_"+planWork2YearId).val());
//	$("#scheForm_"+planWork2YearId+" input[name='weightPoint11']").val($("#weightPoint11_input_"+planWork2YearId).val());
//	$("#scheForm_"+planWork2YearId+" input[name='weightPoint12']").val($("#weightPoint12_input_"+planWork2YearId).val());
	if($("#weightPoint1_input_"+planWork2YearId).attr('checked')==false){
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint1']").val(0);
	}else{
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint1']").val(1);
	}
	if($("#weightPoint2_input_"+planWork2YearId).attr('checked')==false){
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint2']").val(0);
	}else{
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint2']").val(1);
	}
	if($("#weightPoint3_input_"+planWork2YearId).attr('checked')==false){
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint3']").val(0);
	}else{
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint3']").val(1);
	}
	if($("#weightPoint4_input_"+planWork2YearId).attr('checked')==false){
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint4']").val(0);
	}else{
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint4']").val(1);
	}
	if($("#weightPoint5_input_"+planWork2YearId).attr('checked')==false){
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint5']").val(0);
	}else{
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint5']").val(1);
	}
	if($("#weightPoint6_input_"+planWork2YearId).attr('checked')==false){
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint6']").val(0);
	}else{
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint6']").val(1);
	}
	if($("#weightPoint7_input_"+planWork2YearId).attr('checked')==false){
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint7']").val(0);
	}else{
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint7']").val(1);
	}
	if($("#weightPoint8_input_"+planWork2YearId).attr('checked')==false){
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint8']").val(0);
	}else{
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint8']").val(1);
	}
	if($("#weightPoint9_input_"+planWork2YearId).attr('checked')==false){
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint9']").val(0);
	}else{
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint9']").val(1);
	}
	if($("#weightPoint10_input_"+planWork2YearId).attr('checked')==false){
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint10']").val(0);
	}else{
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint10']").val(1);
	}
	if($("#weightPoint11_input_"+planWork2YearId).attr('checked')==false){
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint11']").val(0);
	}else{
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint11']").val(1);
	}
	if($("#weightPoint12_input_"+planWork2YearId).attr('checked')==false){
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint12']").val(0);
	}else{
		$("#scheForm_"+planWork2YearId+" input[name='weightPoint12']").val(1);
	}
	$("#scheForm_"+planWork2YearId).ajaxSubmit(function(result) {
		if (null!=result && ""!=result) {
			var succMsg = "保存成功";
			$("#succInfoMsg").html(succMsg).show().fadeOut(2000);
			refreshMain(planWork2YearId);
			$("#pop_bg").hide();
		}
	});
	$("#pop_bg").show();
}
//刷新某条记录的主体部分
function refreshMain(planId){
	var target=$("#main_"+planId);
	$.get(_ctx+"/plan/plan-work2-year!fetchMain.action?id="+planId, function(result) {
		if (result) {
			target.html(result);
		}
	}); 
}
//刷新某条记录的展开部分
function refreshDetail(planId){
	var isAttentioned = $("#scheForm_"+planId+" input[name='isAttentioned']").val();
	var target=$("#detail_"+planId);
	$.get(_ctx+"/plan/plan-work2-year!fetchDetail.action?id=" + planId+"&centerCd="+centerCd+"&now_orgCd="+centerCd+"&isEditOrg="+isEditOrg+"&isAttentioned="+isAttentioned, function(result) {
		if (result) {
			target.html(result);
			$("#pop_bg").hide();
		}
	}); 
}
//异步保存记录信息
function updateRecord(planWork2YearId, type, newVal) {
	var param = null;
	var msg = "";
	var oldVal = "";
	try{
		oldVal = $("#scheForm_"+planWork2YearId+" input[name='"+type+"']").val();
	}catch(e){}
	if(oldVal==newVal){
		return;
	}
	if("targetDate"==type && (""==newVal||null==newVal)){
		return;
	}
	
	switch (type) {
		case "serialOrder" :
			param = {"id" : planWork2YearId, "serialOrder" : newVal};
			msg = "顺序修改成功";
			break;
		case "content" :
			param = {"id" : planWork2YearId, "content" : newVal};
			msg = "内容修改成功";
			break;
		case "principal" :
			param = {"id" : planWork2YearId, "principal" : newVal};
			msg = "负责人修改成功";
			break;
		case "targetDate" :
			param = {"id" : planWork2YearId, "targetDate" : newVal};
			msg = "目标日期修改成功";
			break;
		case "area" :
			param = {"id" : planWork2YearId, "area" : newVal};
			msg = "地区修改成功";
			break;
		case "statusCd" :
			param = {"id" : planWork2YearId, "statusCd" : newVal};
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
					msg = "删除成功";
					break;
			}
			break;
		case "delete" :
			if (confirm("确认删除？")) {
				param = {"id" : planWork2YearId, "isDeleted" : newVal};
				msg = "已删除";
				$("#" + planWork2YearId + "item").next().remove();
				$("#" + planWork2YearId + "item").remove();
			}
			break;
		case "hide" :
			param = {"id" : planWork2YearId, "hiddenFlg" : newVal};
			msg = "已设置为隐藏";
			$("#" + planWork2YearId + "item").next().remove();
			$("#" + planWork2YearId + "item").remove();
			break;
		case "show" :
			param = {"id" : planWork2YearId, "hiddenFlg" : newVal};
			msg = "已设置为被显示";
			break;
	}
	if (param) {
		$.post(_ctx+"/plan/plan-work2-year!save.action?isEditOrg="+isEditOrg, param, function(result) {
			$("#succInfoMsg").html(msg).show().fadeOut(2000);
			$("#td_updateDate_"+planWork2YearId).html(nowDate.substring(5,nowDate.length));
			var beforeRecordVersion = Number($("#scheForm_"+planWork2YearId+" input[name='recordVersion']").val());
			beforeRecordVersion++;
			$("#scheForm_"+planWork2YearId+" input[name='recordVersion']").val(beforeRecordVersion);
			switch (type) {
				case "content" :
					$("#content_show_"+planWork2YearId).html(newVal);
					break;
				case "principal" :
					refreshMain(planWork2YearId);
					break;
				case "targetDate" :
					$("#targetDate_show_"+planWork2YearId).html(newVal);
					break;
				case "area" :
					$("#area_show_"+planWork2YearId).html(newVal);
					break;
				case "serialOrder" :
					$("#serialOrder_show_"+planWork2YearId).html(newVal);
					break;
			}
		});
	}
}

//保存留言
function saveMessage(planWork2YearId){
	var isAttentioned = $("#scheForm_"+planWork2YearId+" input[name='isAttentioned']").val();
	var content =$("#"+planWork2YearId+"_message").val();
	if($.trim(content).length>0){
		$.post(_ctx+"/plan/plan-work2-year!saveMessage.action",
				{
			     id:planWork2YearId,
			     content:content,
			     isAttentioned:isAttentioned
				},
				 function(result) {
					 if (result == "ok") {
						 //refreshContent(planWork2YearId);
						 refreshMain(planWork2YearId);
						 refreshDetail(planWork2YearId);
						 //$("#"+planWork2YearId+"_message").val("");
					 }else if(result =="no"){
						 alert("留言失败");
					 }
				 });
	}
}
//刷新留言,不用
function refreshContent(planWork2YearId){
	var target=$("#"+planWork2YearId+"_messageDiv");
	$.get(_ctx+"/plan/plan-work2-year!fetchContent.action?id=" + planWork2YearId+"&isEditOrg="+isEditOrg, function(result) {
		if (result) {
			target.html(result);
			$("#pop_bg").hide();
		}
	}); 
}
//显示/隐藏细项
function toggleDetail(detail_obj,planWork2YearId){
	if(isEditOrg){
		if(CAN_toggleDetail){
			$(detail_obj).find('.span_show').toggle();
			$(detail_obj).find('.span_hide').toggle();
		}else{
			CAN_toggleDetail = true;
		}
		if(null!=planWork2YearId){
			closePrevDetail(planWork2YearId);
		}
	}
}
function toggleDetail2(toggleName,planWork2YearId){
	if(isEditOrg){
		if(CAN_toggleDetail){
			$("#"+toggleName).find('.span_show').toggle();
			$("#"+toggleName).find('.span_hide').toggle();
		}else{
			CAN_toggleDetail = true;
		}
		if(null!=planWork2YearId){
			closePrevDetail(planWork2YearId);
		}
	}
}
function hideDetail(toggleName){
	if(true){
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
			setAttentionRead("planWork2Year",scheId);
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
			case "serialOrder" :
				$("#order_serialOrder").html("");
				break;
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
	$.post(_ctx+"/plan/plan-work2-year!doUpdateStatusAll.action", param, function(result) {
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
		var msg = "";
		var ifDelete = "";
		var ifBack = "";
		if((0==sourceStatusCd || 1==sourceStatusCd) && 5==targetStatusCd){
			ifDelete = "true";
			msg = "删除成功";
		}
		if((0==targetStatusCd || 1==targetStatusCd) && 5==sourceStatusCd){
			ifBack = "true";
			msg = "还原成功";
		}
		var param = {id:itemId, statusCd:targetStatusCd, ifDelete:ifDelete, ifBack:ifBack};
		$.post(_ctx+"/plan/plan-work2-year!save.action?isEditOrg="+isEditOrg, param, function(result) {
			$("#succInfoMsg").html(msg).show().fadeOut(2000);
			refreshMain(itemId);
		});
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
	$.post(_ctx+"/plan/plan-work2-year!doExchangeOrder.action", param+"&"+param2, function(result) {
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
		message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=planWork2Year&filterType=image|office&bizEntityName=PlanWork",
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
	$.post(_ctx+"/plan/plan-work2-year!confirm.action",data, function(result) {
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
	$.post(_ctx+"/plan/plan-work2-year!unConfirm.action",data, function(result) {
		if (result=="success"){
			ymPrompt.succeedInfo({message:"解锁成功",title:"提示",width:220,height:180,handler:function(){window.location.reload();}});
		}else{
			ymPrompt.alert({message:result,slideShowHide:false,title:"提示",width:220,height:180});
		}
	});
}
// 提醒功能, 给负责人发信
function remind(itemId) {
	$.post(_ctx + "/plan/plan-work2-year!remind.action?id=" + itemId, function(result) {
		if (result == "done") {
			$("#succInfoMsg").html("提醒邮件已发送").show().fadeOut(2000);
		}
	});
}


var selectMultiDeptCds = "";
var selectMultiDeptNames = "";
//取部门多选框
function selectDeptCds(deptCds,deptNames) {
	selectMultiDeptCds = deptCds;
	selectMultiDeptNames = deptNames;
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"选择发布范围",
		message:"<div id='selectMultiDeptDiv'><img align='absMiddle' src='"+_ctx+"/images/loading.gif'></div>",
		useSlide:true,
		winPos:"t",
		width:350,
		height:400,
		maxBtn:true,
		allowRightMenu:true,
		handler: getSelectedDepts,
		afterShow:function(){
			$.post(_ctx+"/oa/oa-notify!toDepts.action",function(result){
				$("#selectMultiDeptDiv").html(result);
			});
		}
	});
}
function getSelectedDepts(tp) {
	if (tp == "ok") {
		var value = "" + tree.getChecked("id");
		if (value.trim().length > 0) {
			// 如果选中了全部机构，则发布范围为all，不需要把所有的机构都列出来
			if (value.substring(0, 2) == '0,') {
				$("#"+selectMultiDeptCds).val("all");
				$("#"+selectMultiDeptNames).val("全部");
			} else {
				$("#"+selectMultiDeptCds).val("," + value + ",");
				$("#"+selectMultiDeptNames).val(tree.getChecked("text"));
			}
		} else {
			$("#"+selectMultiDeptCds).val("");
			$("#"+selectMultiDeptNames).val("");
		}
	}
}
function clearToDeptCds(deptCds,deptNames) {
	$("#"+deptCds).val("");
	$("#"+deptNames).val("");
}
var tree;
function buildDepts(deptCds){
	$("#notifyToDeptsDiv").empty();
	var selectedCds = $("#"+selectMultiDeptCds).val();
	alert(1);
	$.post(_ctx+"/oa/oa-notify!buildDepts.action?selectedDeptCds=" + selectedCds, function(result) {
		if (result) {
			tree = new TreePanel({
				renderTo:'notifyToDeptsDiv',
				'root' : eval('('+result+')'),
				'ctx':_ctx
			});
			tree.render();
		}
	});
}


//重新生成月计划
function createAll(){
	$.post(_ctx+"/plan/plan-work2-year!createAll.action", function(result) {
		if (result) {
			var succMsg = "保存成功";
			$("#succInfoMsg").html(succMsg).show().fadeOut(2000);
		}
	});
}
