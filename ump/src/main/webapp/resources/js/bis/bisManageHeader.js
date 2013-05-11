
/**
 * 选择项目业态图，跳转mall楼层展示页面
 */
function goFloor(bisProjectId){
	var url = _ctx+"/bis/bis-project!projectOperate.action?bisProjectId="+bisProjectId;
	$('#layoutPanel').hide();
	if(parent.TabUtils==null)
		window.open(url);
	else
		parent.TabUtils.newTab("bisProjectMenu","项目业态图",url,true);
}

function showSeniorSearch() {
	if($("#seniorSearchDiv").css("display")=="none"){
		$("#btnSeniorSearch").addClass("quickSeniorHover");
		if(_MODULE==3||_MODULE==4) {
			$("#seniorSearchDiv").height(160);
		} 
		$("#seniorSearchDiv").show();
	}else{
		$("#btnSeniorSearch").removeClass("quickSeniorHover");
		$("#seniorSearchDiv").hide();
	}
}

function clkFullScreen() {
	var bisProjectId = $("#bisProjectId").val();
	var url;
	if(isEmpty(bisProjectId)) {
		url = _ctx+"/bis/bis-manage!layout.action?module=";
	} else {
		url = _ctx+"/bis/bis-manage!layout.action?bisProjectId="+$("#bisProjectId").val()+"&module=";
	}
	
	if(_MODULE==1) {
		window.open(url+"1");
	} else if(_MODULE==2) {
		window.open(url+"2");
	} else if(_MODULE==3) {
		window.open(url+"3");
	} else if(_MODULE==4) {
		window.open(url+"4");
	} else if(_MODULE==5) {
		window.open(url+"5");
	} else if(_MODULE==6) {
		window.open(_ctx+"/bis/bis-shop!main.action");
	} else if(_MODULE==7) {
		window.open(url+"7");
	} else if(_MODULE==7) {
		window.open(url+"8");
	}
}

/**
 * 切换业态
 */
function changeLayout(dom) {
	var bisProjectId = $("#bisProjectId").val();
	var url;
	if(isEmpty(bisProjectId)) {
		url = _ctx+"/bis/bis-manage!layout.action?module=";
	} else {
		url = _ctx+"/bis/bis-manage!layout.action?bisProjectId="+$("#bisProjectId").val()+"&module=";
	}
	
	if(dom.id=='li_1') {
		//window.location = _ctx+"/bis/bis-manage-report.action";
		window.location = url+"1";
	} else if(dom.id=='li_2') {
		//window.location = _ctx+"/bis/bis-project-report.action";
		window.location = url+"2";
	} else if(dom.id=='li_3') {
		//window.location = _ctx+"/bis/bis-fact!list.action";
		window.location = url+"3";
	} else if(dom.id=='li_4') {
		//window.location = _ctx+"/bis/bis-cont.action";
		window.location = url+"4";
	} else if(dom.id=='li_5') {
		//window.location = _ctx+"/bis/bis-tenant!main.action";
		window.location = url+"5";
	} else if(dom.id=='li_6') {
		//window.location = _ctx+"/bis/bis-shop!main.action";
		var url = _ctx+"/bis/bis-shop!main.action";
		if(parent.TabUtils==null)
			window.open(url);
		else
			parent.TabUtils.newTab("bisShop","商家库",url,true);
	} else if(dom.id=='li_7') {
		//window.location = _ctx+"/bis/bis-project!main.action";
		window.location = url+"7";
	}
}
//导入商铺报表
function doImport(){
	var fileName ="D://storeImport.xls";
	var url =_ctx+"/bis/bis-store-coords!importExcelByStroe.action?fileName="+fileName;
	$.post(url,
			function(result) {
				
			});
}
function doExport(){
	var bisProjectId = $('#bisProjectId').val();
	var floorType =$('#floorType').val();
	var bisFloorId=$('#bisFloorId').val();
	if(bisProjectId==''){
		alert('请先选择项目');
		return;
	}
	if(floorType==''){
		alert('请先选择楼宇类型');
		return;
	}
	if(bisFloorId==="--选择--"){
		bisFloorId='';
	}
	if (confirm("导出仅用于核对，确定要导出么？")){
	
		var url =_ctx+"/bis/bis-store-coords!exportTemplate.action?bisProjectId="+bisProjectId+"&bisFloorId="+bisFloorId+"&floorType="+floorType;
		location.href = url;
		
	}
}
//导出空坐标商铺
function exportAlertInfo(){
	var bisProjectId = $('#bisProjectId').val();
	var url =_ctx+"/bis/bis-store-coords!exportAlertInfo.action?bisProjectId="+bisProjectId;
	location.href = url;
}
function updateStoreInfo(bisStoreId){
	var url =_ctx+"/bis/bis-store-coords!updateStoreInfo.action";
	var virtualArea = $('#virtualArea').val();
	var coords = $('#coords').val();
	$.post(url,{bisStoreId:bisStoreId,coords:coords,virtualArea:virtualArea},function(result){
		$('#virtualArea').addClass('inputread');
		$('#coords').addClass('inputread');
	});
}
function closeSeniorSearch() {
	$("#btnSeniorSearch").removeClass("quickSeniorHover");
	$("#seniorSearchDiv").hide();
}
function factReduce(){
	var bisProjectId = $("#bisProjectId").val();
	var url;
	if(isEmpty(bisProjectId)) {
		url = _ctx+"/bis/bis-fact-reduce!list.action";
	} else {
		url = _ctx+"/bis/bis-fact-reduce!list.action?bisProjectId="+$("#bisProjectId").val();
	}
	parent.TabUtils.newTab("bisFactReduce","批量减免",url,true);
}