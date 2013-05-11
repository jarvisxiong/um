<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisStoreSelect.css" />
<input type="hidden" id="itemId"  />
<input type="hidden" id="itemIdsTemp" value="${itemIdsTemp}">
<input type="hidden" id="itemNamesTemp" value="${itemNamesTemp}">
<div style="padding: 15px 20px 0 20px; height: 400px;">
	<div id="divFloor" style="float: left; margin-right: 20px; width: 150px; height: 390px; border: 1px solid #ccc;">
		<div class="ctsltTitle" >国标大类</div>
		<div id="divFloorData" style="height:355px; border-top:1px solid #ccc;">
			<ul id="ulFloor" class="ctslt-nav" >
					<s:iterator value="bidCountryDivisitList">
					<li id="btli_${itemId}" value="${itemId}" onclick="findCountryDetail(this,'${itemId}');" title="${itemName}">
						<div class="ellipsisDiv_full" >
						${itemName}
						</div>
					</li>
					</s:iterator>
			</ul>
		</div>
	</div>
	
	<div id="divStore" style="float: left; margin-right: 20px; width: 150px; height: 390px; border: 1px solid #ccc;">
		<div class="ctsltTitle" >国标小类&nbsp;</div>
		<div style="padding-bottom: 5px;">
		<input type="button" style="width: 60px;margin-left: 10px;" onclick="selectAllBid();" class="btn_green" value="全选">
		<input style="width: 60px;margin-left: 5px;" type="button" onclick="rebackAllBid();" class="btn_green" value="反选">
		</div>
		<div id="divStoreData" style="height:330px; border-top:1px solid #ccc;">
			<ul id="ulStore" class="ctslt-nav" >
			
			</ul>
		</div>
	</div>
	
	<div id="divStoreSelected" style="float: left; width: 150px; height: 390px; border: 1px solid #ccc;">
		<div class="ctsltTitle" >已选国标</div>
		<div id="divStoreSelectedData" style="height:355px; border-top:1px solid #ccc;">
			<ul id="ulStoreSelected" class="ctslt-nav" >
			
			</ul>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	initStoreSelected();
	loadSmallItem();
	
});

$("#search_storeNo").keydown(function(event){
	if(event.keyCode==13){
		loadStore($("#bisFloorId").val());
	}
});

/**
 * 绑定搜索框事件
 */
function bindSearchEv() {
	$("input").filter(".search").each(function(i,dom){
		if($(dom).val().trim() == '') {
			$(dom).val($(dom).attr("searchtext"));
			$(dom).css({color: "#909090"});
		}
	});
	
	$("input").filter(".search").click(function(){
		if($(this).val() == $(this).attr("searchtext")) {
			$(this).val('');
			$(this).css({color:"#000000"});
		}
	});
	
	$("input").filter(".search").blur(function(){
		if($(this).val().trim() == '') {
			$(this).val($(this).attr("searchtext"));
			$(this).css({color: "#909090"});
		} else {
			$(this).css({color:"#000000"});
		}
	});
}

//选择国标大类
function findCountryDetail(dom,value) {
	$("#ulFloor li").removeClass("ctslt-nav-click");
	$(dom).addClass("ctslt-nav-click");
	$("#itemId").val(value);
	loadSmallItem(value);
}

//加载国标小类
function loadSmallItem(itemId) {
	var data = {
		itemId:itemId
	};
	//TB_showMaskLayer("正在搜索...");
	$.post("${ctx}/bid/bid-country-divisit!detail.action", data, function(result) {
		//TB_removeMaskLayer();
		$("#ulStore").html(result);

		initCheckBox();
		if($("#ulStore").height() > 355) {
			$("#divStoreData").addClass("div_scroll_y");
		} else {
			$("#divStoreData").removeClass("div_scroll_y");
		}
	});
}
//var itemNamesTemp ="";
//var itemNamesTemp="";
//选择国标小类
function selectSmallItem(itemId) {
	var itemIdsTemp = $("#itemIdsTemp").val();
	var itemNamesTemp = $("#itemNamesTemp").val();
	var itemName = $("#stli_"+itemId).text().trim();
	if($("#stli_"+itemId).hasClass("ctslt-nav-click")) {
		$("#stli_"+itemId).removeClass("ctslt-nav-click");
		itemIdsTemp=itemIdsTemp.replace(itemId+",","");
		itemNamesTemp=itemNamesTemp.replace(itemName+",","");
		
		$("#stsli_"+itemId).remove();
		if($("#ulStoreSelected").height() <= 355) {
			$("#divStoreSelectedData").removeClass("div_scroll_y");
		}
	} else {
		$("#stli_"+itemId).addClass("ctslt-nav-click");
		itemIdsTemp=itemIdsTemp+itemId+",";
		itemNamesTemp=itemNamesTemp+itemName+",";
		var li = '<li id=stsli_'+itemId+' class="ctslt-nav-click" onclick="removeItemSelected(\''+itemId+'\')" title="'+itemName+'" >'+'<div class="ellipsisDiv_full" >'+itemName+'</div>'+'</li>';
		$("#ulStoreSelected").append(li);
		if($("#ulStoreSelected").height() > 355) {
			$("#divStoreSelectedData").addClass("div_scroll_y");
		}
	}
	$("#itemIdsTemp").val(itemIdsTemp);
	$("#itemNamesTemp").val(itemNamesTemp);
}
//移除已选国标小类
function removeItemSelected(itemId) {
	var itemIdsTemp = $("#itemIdsTemp").val();
	var itemNamesTemp = $("#itemNamesTemp").val();
	var itemName = $("#stsli_"+itemId).text().trim();
	itemIdsTemp=itemIdsTemp.replace(itemId+",","");
	itemNamesTemp=itemNamesTemp.replace(itemName+",","");
	$("#stsli_"+itemId).remove();
	if($("#ulStoreSelected").height() <= 355) {
		$("#divStoreSelectedData").removeClass("div_scroll_y");
	}
	if($("#stli_"+itemId)) {
		$("#stli_"+itemId).removeClass("ctslt-nav-click");
	}
	$("#itemIdsTemp").val(itemIdsTemp);
	$("#itemNamesTemp").val(itemNamesTemp);
}
//初始化国标小类选择
function initCheckBox() {
	var itemIdsTemp = $("#itemIdsTemp").val();
	var	itemIds = itemIdsTemp.split(",");
	for(i=0;i<itemIds.length-1;i++){
		if($("#stli_"+itemIds[i])) {
			$("#stli_"+itemIds[i]).addClass("ctslt-nav-click");
		}
	}
}
//初始化已选商铺
function initStoreSelected() {
	var itemIdsTemp = $("#itemIdsTemp").val();
	var itemNamesTemp = $("#itemNamesTemp").val();
	var	itemIds = itemIdsTemp.split(",");
	var	itemNames = itemNamesTemp.split(",");
	for(i=0;i<itemIds.length-1;i++){
		var itemId = itemIds[i];
		var itemName = itemNames[i];
		var li = '<li id=stsli_'+itemId+' class="ctslt-nav-click" onclick="removeItemSelected(\''+itemId+'\')" title="'+itemName+'" >'+'<div class="ellipsisDiv_full" >'+itemName+'</div>'+'</li>';
		$("#ulStoreSelected").append(li);
	}
	if($("#ulStoreSelected").height() > 355) {
		$("#divStoreSelectedData").addClass("div_scroll_y");
	}
}
function selectAllBid(){
	$('#ulStore li').each(function(index) {
	    if(!($("#stli_"+$(this).attr("itemId")).hasClass("ctslt-nav-click"))){
	    	selectSmallItem($(this).attr("itemId"));
	    }
	  });
}
function rebackAllBid(){
	$('#ulStore li').each(function(index) {
	    if($("#stli_"+$(this).attr("itemId")).hasClass("ctslt-nav-click")){
	    	removeItemSelected($(this).attr("itemId"));
	    }else{
	    	selectSmallItem($(this).attr("itemId"));
	    }
	  });
}
</script>