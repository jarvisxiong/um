function saveProdWeight(){	
	TB_showMaskLayer("正在保存...");
	$('#newProdWeightForm').ajaxSubmit(function(result){
		if('success'==result){
			TB_removeMaskLayer();
			$("div.weightForm").fadeOut("slow");
			loadProdWeight();
		}
	});
}

function search(){
	
}

//加载权重列表
function loadProdWeight(){
	var url=_ctx+"/prod/prod-bussiness-weight!wieghtContent.action";
	var data={};
	$.post(url,data,function(result){		
			$('#rsTable').html(result);		
		});
}
 
function newProdWeight(){
	$("div.weightForm").show();
}

//跳转至给定的页面,配合前台的分页搜索
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}

function jumpPage(){
	
}
function jumpPage(pageNo,first) {	
	$("#pageNo").val(pageNo);
	TB_showMaskLayer("正在搜索...");
	$("#pageForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		$(".rsTable").html(result);
		
	});
}