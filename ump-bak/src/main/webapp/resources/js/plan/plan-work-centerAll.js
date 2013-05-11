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
			page_size = 20;
		}
		var data = {
				myTask : myTask,
				opened_entityid : opened_entityid,
				if_in_attention : if_in_attention,
				centerCd : centerCd,
				search_orgCd : search_orgCd,
				now_year : now_year,
				now_month : now_month,
				now_month_of_year : now_month_of_year,
				"page.pageNo" : page_no,
				"page.pageSize" : page_size,
				filter_LIKES_principal : $("#search_principal").val(),
				filter_LIKES_content : $("#filter_LIKES_content").val(),
				filter_LIKES_targetPointCd : $("#search_targetPointCd").val(),
				search_statusCd : $("#search_statusCd").val(),
				if_search_all : $("#if_search_all").val(),
				orderStr1 : $("#orderStr1").val(),
				orderStr2 : $("#orderStr2").val(),
				orderDir1 : $("#orderDir1").val(),
				orderDir2 : $("#orderDir2").val()
		};
		$("#scheduleDiv").html("<div style='height:100px'></div><table width='100%'><tr><td align='center'><img src='"+_ctx+"/images/loading.gif'/></td></tr></table>");
		$.post(_ctx+"/plan/plan-work-center!list.action", data, function(result) {
			$("#scheduleDiv").html(result);
			IF_IN_LOADING = false;
			autoHeight();
		});
	}
}
//页面跳转函数的覆盖

//此方法会与cost-ctrl.js中的jumpPage()方法冲突，会引起当点击’中心内部任务‘后‘北方区招采’、‘南方区招采’等后面5个按钮无法点击搜索的问题，特此注释。
// 注释  by liuzhihui 2012-04-19
/*function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	centerClick();
}*/
