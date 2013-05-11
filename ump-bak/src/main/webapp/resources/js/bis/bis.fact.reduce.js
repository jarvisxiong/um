function newReduce(){
	var projectId = $('#bisProjectIdFact').val();
	var data={bisProjectId:projectId};
	if($("#addContent").css("display")=="none"){
		$("#welcom").hide();
		
		var url = _ctx+'/bis/bis-fact-reduce!input.action';
		$.post(url,data,function(result){
			$('#addContent').html(result).show();
			$('#list_div').hide();
			init4Input();
		});
		//$('#detailPanel').hide();
	}else{
		$("#addContent").html('').hide();
	}
}
function cance(){
	newReduce();
}
function searchReduce(){
	$("#addContent").html('').hide();
	$("#welcom").hide();
	$('#list_div').show();
}
function selectConts(){
		bisTenantId = '';
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='selectTypeDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 820,
		height : 417,
		title : "请选择合同",
		afterShow : function() {
			var url = _ctx+"/bis/bis-cont!contTypeSelect.action";
			$.post(url, {bisTenantId:bisTenantId,bisProjectId:$("#bisProjectIdFact").val()}, function(result) {
				$("#selectTypeDiv").html(result);
				$("#search_contNo").keydown(function(event){
					if(event.keyCode==13){
						var dom =$("#ulSmallType li.ctslt-nav-click");
						loadContList(dom[0].value);
					}
				});
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				var bisContIdsTemp = $('#bisContIdsTemp').val();
				if(bisContIdsTemp==''){
					alert('请选择合同!');
					return false;
				}
				$('#selectConts').val($('#bisContNosTemp').val());
				$('#selectContIds').val(bisContIdsTemp);
			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["退出",'cancel']]
	});
}
function clkBigType(dom) {
	$("#ulBigType li").removeClass("ctslt-nav-click");
	$(dom).addClass("ctslt-nav-click");
	$("#bigTypeId").val(dom.value);
	$("#smallTypeId").val("");
	loadSmallType(dom.value);
	if(dom.value=='3'){
		$('#divFloor').hide();
	}else{
		$('#divFloor').show();
		loadFloor(dom.value);
	}
}

function loadSmallType(bigtype) {
	$.post(_ctx+"/bis/bis-cont!loadSmallType.action", {contBigTypeCd:bigtype,bisProjectId:$("#bisProjectIdFact").val()}, function(result) {
		$("#ulSmallType").html(result);
	});
}

function clkSmallType(dom) {
	$("#ulSmallType li").removeClass("ctslt-nav-click");
	$(dom).addClass("ctslt-nav-click");
	$("#smallTypeId").val(dom.value);
	var bigTypeId = $('#bigTypeId').val();
	loadContList(dom.value);
}
function loadFloor(floorType){
	$.post(_ctx+"/bis/bis-project!doFloorSelect.action", {floorType:floorType,bisProjectId:$("#bisProjectIdFact").val()}, function(result) {
		$("#ulFloor").html(result);
		$("#ulFloor").height($("#ulFloor").parent().height()-32);
		$("#ulFloor").css('overflow','auto');
	});
}
//选择楼(层)号
function selectFloor(dom,value) {
	$("#ulFloor li").removeClass("ctslt-nav-click");
	$(dom).addClass("ctslt-nav-click");
	if($("#search_storeNo").val() != $("#search_storeNo").attr("searchtext")) {
		$("#search_storeNo").val('');
	}
	$("#bisFloorId").val(value);
	loadContList($("#smallTypeId").val());
}
function loadContList(contSmallType){
	var floorId = $("#bisFloorId").val();
	var filter_LIKES_conteNo = $("#search_contNo").val();
	if($("#search_contNo").val() == $("#search_contNo").attr("searchtext")) {
		filter_LIKES_conteNo = "";
	}
	var bigTypeId = $('#bigTypeId').val();
	
	$.post(_ctx+"/bis/bis-cont!loadContList.action", {bisFloorId:floorId,contBigTypeCd:bigTypeId,contSmallTypeCd:contSmallType,bisProjectId:$("#bisProjectIdFact").val(),filter_LIKES_contNo:filter_LIKES_conteNo}, function(result) {
		$("#ulContList").html(result);
		$("#ulContList").height($("#ulContList").parent().height()-32);
		$("#ulContList").css('overflow-y','auto');
		$("#ulContList").css('overflow-x','hidden');
		$("#ulContList").css('white-space','nowrap');
	});
}
function clkCont(dom){
	selectCont($(dom).attr('val'));
}
function selectCont(contId){
	var bisContIdsTemp = $("#bisContIdsTemp").val();
	var bisContNosTemp = $("#bisContNosTemp").val();
	var contNo = $("#conli_"+contId).text().trim();
	if($("#conli_"+contId).hasClass("ctslt-nav-click")) {
		$("#conli_"+contId).removeClass("ctslt-nav-click");
		bisContIdsTemp=bisContIdsTemp.replace(contId+",","");
		bisContNosTemp=bisContNosTemp.replace(contNo+",","");
		
		$("#conli_"+contId).remove();
		if($("#ulContSelected").height() <= 355) {
			$("#divContSelectedData").removeClass("div_scroll_y");
		}
	} else {
		$("#conli_"+contId).addClass("ctslt-nav-click");
		bisContIdsTemp=bisContIdsTemp+contId+",";
		bisContNosTemp=bisContNosTemp+contNo+",";
		var li = '<li id=contsli_'+contId+' class="ctslt-nav-click" onclick="removeContSelected(\''+contId+'\')" title="'+contNo+'" >'+contNo+'</li>';
		$("#ulContSelected").append(li);
		if($("#ulContSelected").height() > 355) {
			$("#divContSelectedData").addClass("div_scroll_y");
		}
	}
	$("#bisContIdsTemp").val(bisContIdsTemp);
	$("#bisContNosTemp").val(bisContNosTemp);
}
function removeContSelect(contId){
	var bisContIdsTemp = $("#bisContIdsTemp").val();
	var bisContNosTemp = $("#bisContNosTemp").val();
	var contNo = $("#conli_"+contId).text().trim();
	bisContIdsTemp=bisContIdsTemp.replace(contId+",","");
	bisContNosTemp=bisContNosTemp.replace(contNo+",","");
	$("#contsli_"+contId).remove();
	if($("#ulContSelected").height() <= 355) {
		$("#divContSelectedData").removeClass("div_scroll_y");
	}
	if($("#conli_"+contId)) {
		$("#conli_"+contId).removeClass("ctslt-nav-click");
	}
	$("#bisContIdsTemp").val(bisContIdsTemp);
	$("#bisContNosTemp").val(bisContNosTemp);
}
function isEmpty(dom){
	if($(dom).val()==''){
		return true;
	}return false;
}
function save(){
	if(isEmpty('#selectContIds')){
		alert('请选择合同');
		return;
	}
	if(isEmpty('#resApproveInfoId')){
		alert('请选择网批');
		return;
	}
	if(isEmpty('#remark')){
		alert('请填写说明');
		return;
	}
	$("#inputForm").ajaxSubmit(function(result) {
		if(result=='success'){
			$('#tip').html('保存成功').show().fadeOut(2000);
			searchReduce();
		}else{
			$('#tip').html('保存失败').show().fadeOut(2000);
			
		}
	});
}
function processSearchText(dom){
	if($('#'+dom).attr('searchText')==$('#'+dom).val()){
		$('#'+dom).val('');
	}
}
function searchReduce(){
	processSearchText('filter_LIKES_shopName');
	processSearchText('filter_LIKES_contNo');
	processSearchText('resApproveInfoCdNo');
	$("#searchForm").submit();
	//$('#filter_LIKES_contNo').val(filter_LIKES_contNo);
	//$('#filter_LIKES_shopName').val(filter_LIKES_shopName);
	
}
function jumpPageTo() {
	 var index = $("#pageTo").val();
	 index = parseInt(index);
	 if (index > 0) {
	 jumpPage(index);
	 }
} 
function jumpPage(pageNo){
	//$("#searchForm").attr("action",_ctx+"/bis/bis-fact-reduce!list.action");
	searchReduce();
}
function clearInput(dom,defaultValue){
	if($(dom).val()==defaultValue){
		$(dom).val('');
	}
	$(dom).css("color","#000");
	$(dom).select();
}
function init4Input(){
	$('#selectConts').bind('click',function(){selectConts();});
	var mapPrama={
			titleName:'selectResApprove',
			id:'resApproveInfoId'
		};
	$("#selectResApprove").quickSearch(_ctx+"/res/res-approve-info!quickSearch.action",["approveCd","titleName"],mapPrama,{statusCd:'2'});
}