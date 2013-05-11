function saveVersionNo(){
	TB_showMaskLayer("正在保存...");
	$('#newBasicVersionForm').ajaxSubmit(function(result){
		var rs=result.split(",");
		if('success'==rs[0]){
			TB_removeMaskLayer();			
			$("div.versionForm").fadeOut("slow");
			$("#input_versionNo").val(rs[2]);
			$("#hidden_versionNo").val(rs[1]);
		}
	});
}

function saveVersionDetail(){
	TB_showMaskLayer("正在保存...");
	$('#newVersionDetailForm').ajaxSubmit(function(result){
		var rs=result.split(",");
		if('success'==rs[0]){
			TB_removeMaskLayer();			
			$("div.versionDetailForm").fadeOut("slow");
			loadVersionDetail('');
		}else{
			TB_removeMaskLayer();
			alert(rs[1]);
		}
	});
}

function loadVersionDetail(sval){	
	var data={};
	if(sval){
		data={versionNo:sval};
	}else{
		//alert($("#curtVersionNo").val());
		//如果不为空
		if($("#curtVersionNo").val()){
			data={versionNo:$("#curtVersionNo").val()};			
		}
		else{			
			data={};
		}
		if($("#curtAreaCd").val()){
			data['areaCd']=$("#curtAreaCd").val();
		}
		
	}
	var url=_ctx+"/prod/prod-version-detail!loadList.action";	
	$.post(url,data,function(result){		
			$('#rsTable').html(result);		
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
	//var svalue=$("#curtVersionNo").find("option:selected").val();
	//alert(svalue);
	
	$("#pageNo").val(pageNo);
	TB_showMaskLayer("正在搜索...");
	$("#pageForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		$(".rsTable").html(result);
		
	});
}