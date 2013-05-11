function centerClickMonthSelect(orgCd,parentOrgCd,shortOrgName,parentShortOrgName){
	if(!IF_IN_LOADING){
		IF_IN_LOADING = true;
		if(null!=orgCd && ""!=orgCd){
			NOW_ORGCD = orgCd;
		}
		if(null!=parentOrgCd && ""!=parentOrgCd){
			NOW_PARENT_ORGCD = parentOrgCd;
		}
		var page_no = 1;
		var data = {
				now_orgCd : NOW_ORGCD,
				"page.pageNo" : page_no,
				workplanId : from_workplanId,
				filter_EQs_planYear : $("#filter_EQs_planYear").val(),
				filter_EQs_planMonth : $("#filter_EQs_planMonth").val(),
				filter_LIKES_content : $("#select_filter_LIKES_content").val(),
				search_statusCd : $("#select_search_statusCd").val(),
				orderStr1 : $("#select_orderStr1").val(),
				orderStr2 : $("#select_orderStr2").val(),
				orderDir1 : $("#select_orderDir1").val(),
				orderDir2 : $("#select_orderDir2").val()
		};
		$("#select_scheduleTaskDiv").html("<div style='height:100px'></div><table width='100%'><tr><td align='center'><img src='"+_ctx+"/images/loading.gif'/></td></tr></table>");
		$.post(_ctx+"/plan/workplan-month!listSelect.action", data, function(result) {
			$("#select_scheduleTaskDiv").html(result);
			IF_IN_LOADING = false;
		});
	}
}
//清空所有搜索选项
function clearAllSearchMonthSelect(){
	$("#select_filter_LIKES_content").val("");
	$("#select_search_statusCd").val("");
	$("#select_filter_GED_endDate").val("");
	$("#select_filter_LTD_endDate").val("");
}
//点击某条展开内容
var select_oldMonthId = "";
function select_scheClickMonth(scheId){
	if(CAN_scheClick){
		var detailObj = document.getElementById("select_detailMonth_"+scheId);
		if(detailObj.style.display == "none"){
			if(select_oldMonthId!="" && select_oldMonthId!=scheId){
				try{
					$("#select_detailMonth_"+select_oldMonthId).hide();
					$("#select_mainMonth_"+select_oldMonthId).css("backgroundColor","#fff");
					$("#select_mainMonth_"+select_oldMonthId+" td").css("color","#161616");
				}catch(e){}
			}
			$(detailObj).show();
			$("#select_down_arrow_"+scheId).hide();
			$("#select_up_arrow_"+scheId).show();
			$("#select_mainMonth_"+scheId).css("backgroundColor","#eee");
			$("#select_mainMonth_"+scheId+" td").css("color","#40a3de");
		}else{
			$(detailObj).hide();
			$("#select_down_arrow_"+scheId).show();
			$("#select_up_arrow_"+scheId).hide();
			$("#select_mainMonth_"+scheId).css("backgroundColor","#fff");
			$("#select_mainMonth_"+scheId+" td").css("color","#161616");
		}
		select_oldMonthId =scheId;
	}else{
		CAN_scheClick = true;
	}
}
var select_oldId = "";
function select_scheClick(scheId){
	if(CAN_scheClick){
		var detailObj = document.getElementById("select_detail_"+scheId);
		if(detailObj.style.display == "none"){
			if(select_oldId!="" && select_oldId!=scheId){
				try{
					$("#select_detail_"+select_oldId).hide();
					$("#select_main_"+select_oldId).css("backgroundColor","#fff");
					$("#select_main_"+select_oldId+" td").css("color","#161616");
				}catch(e){}
			}
			$(detailObj).show();
			$("#select_down_arrow_"+scheId).hide();
			$("#select_up_arrow_"+scheId).show();
			$("#select_main_"+scheId).css("backgroundColor","#eee");
			$("#select_main_"+scheId+" td").css("color","#40a3de");
		}else{
			$(detailObj).hide();
			$("#select_down_arrow_"+scheId).show();
			$("#select_up_arrow_"+scheId).hide();
			$("#select_main_"+scheId).css("backgroundColor","#fff");
			$("#select_main_"+scheId+" td").css("color","#161616");
		}
		select_oldId =scheId;
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
