function loadProdBasicVersion(){
	jumpPage(1,'first');
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