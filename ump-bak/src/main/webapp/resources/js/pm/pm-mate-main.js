//高级搜索是否显示
function showEntrySearchDiv(){
	$('#searchModelDiv').hide();
	if($("#searchEntryDiv").is(":hidden")){
		$("#searchDiv").show();
		$("#searchEntryDiv").show();
	}else{
		$("#searchDiv").hide();
		$('#searchEntryDiv').hide();
	}
}

//清空高级搜索的值
function doClear(){
	//活动主题
	$("#pm_activeTitle").val("");
	//时间周期
	$("#pm_activePeriod").val("");
	//预期效果
	$("#pm_expectedResults").val("");
	//费用预算(min)
	$("#pm_expensesBudget_min").val("");
	//费用预算(max)
	$("#pm_expensesBudget_max").val("");
}
//高级搜索(按条件搜索)
function doQuery(){
	//活动主题
	var activeTitle = $("#pm_activeTitle").val();
	//时间周期
	var activePeriod = $("#pm_activePeriod").val();
	//预期效果
	var expectedResults = $("#pm_expectedResults").val();
	//费用预算(min)
	var expensesBudget_min = $("#pm_expensesBudget_min").val();
	//费用预算(max)
	var expensesBudget_max = $("#pm_expensesBudget_max").val();
	TB_showMaskLayer("正在搜索...");
	$.post(_ctx+"/pm/pm-mate-entry!doQuery.action",{activeTitle:activeTitle,activePeriod:activePeriod,expectedResults:expectedResults,expensesBudget_min:expensesBudget_min,expensesBudget_max:expensesBudget_max},function(result) {
		TB_removeMaskLayer();
		if (result) {
			$("#pmMateEntryList").html(result);
		}
	});
	$("#searchDiv").hide();
	$('#searchEntryDiv').hide();
	
	$("#pmMateEntryDiv").hide();
	$("#pmMateEntryDiv").html('');
	$("#mainDiv").show();
}

var g_quicksearch='快速搜索...';
function resetQuickSearch(dom){
	if($(dom).val().trim() == ''){
		$(dom).val(g_quicksearch);
		$(dom).css({color:"#cccccc"});
	}else{
		$(dom).css({color:"#5A5A5A"});
	}
}

function clearQuickSearch(dom){
	if($(dom).val() == g_quicksearch){
		$(dom).val('');
		$(dom).css({color:"#5A5A5A"});
	}
} 

//快速搜索活动主题
function quickSearchModel(){
	$("#mateEntry_quick").quickSearch(_ctx+"/pm/pm-mate-entry!quickSearch.action",
   		['activeTitle'],
   		{mateEntryId:'entry_quick_id',activeTitle:'mateEntry_quick'},'',
   		function(result){
   			var id = $("#entry_quick_id").val();
   			var url=_ctx+"/pm/pm-mate-entry!getMateEntryByEntryId.action";
   			TB_showMaskLayer("正在搜索...");
   			$.post(url,{mateEntryId:id},function(result){
   				TB_removeMaskLayer();
   	   			$("#pmMateEntryDiv").hide();
	   	   		$("#pmMateEntryDiv").html('');
	   	   		$("#pmMateEntryList").html(result);
	   	   		$("#mainDiv").show();
   	   		});
       	}
   	);
}