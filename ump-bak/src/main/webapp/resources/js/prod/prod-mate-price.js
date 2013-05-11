function loadMatePrice(){
	var url=_ctx+"/prod/prod-material-price!loadList.action";
	var data={};
	$.post(url,data,function(result){		
			$('#rsTable').html(result);		
		});
}


function saveMatePrice(){
	TB_showMaskLayer("正在保存...");
	$('#newPriceForm').ajaxSubmit(function(result){
		var rs=result.split(',');
		if('success'==rs[0]){
			TB_removeMaskLayer();
			$("div.priceForm").fadeOut("slow");
			loadMatePrice();
		}
		else{
			TB_removeMaskLayer();
			alert(rs[1]);
		}
	});
	
}

//跳转至给定的页面,配合前台的分页搜索
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}

function jumpPage(pageNo,first) {		
	$("#pageNo").val(pageNo);
	TB_showMaskLayer("正在搜索...");
	$("#pageForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		$(".rsTable").html(result);
		
	});
}


