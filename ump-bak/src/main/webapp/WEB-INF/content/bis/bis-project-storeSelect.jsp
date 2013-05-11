<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisStoreSelect.css" />

<input type="hidden" id="bisProjectHideId" value="${bisProjectId}"/>
<input type="hidden" id="floorType" value="${floorType}" />
<input type="hidden" id="bisFloorId" value="${bisFloorId}" />
<input type="hidden" id="bisStoreIdsTemp" value="${bisStoreIdsTemp}">
<input type="hidden" id="bisStoreNosTemp" value="${bisStoreNosTemp}">

<div style="padding: 15px 20px 0 20px; height: 400px;">
	<s:set var="title">
		<s:if test="floorType == 1">商铺</s:if>
		<s:elseif test="floorType == 2">公寓</s:elseif>
		<s:else>多经</s:else>
	</s:set>
	<s:if test="floorType != 3">
	<div id="divFloor" style="float: left; margin-right: 20px; width: 150px; height: 390px; border: 1px solid #ccc;">
		<div class="ctsltTitle" >楼(层)号</div>
		<div id="divFloorData" style="height:355px; border-top:1px solid #ccc;">
			<ul id="ulFloor" class="ctslt-nav" >
				<s:if test="floorType == 1">
					<s:iterator value="mapFloorSel">
					<li id="btli_${key}" value="${key}" onclick="selectFloor(this,'${key}');" title="${value}">
						<div class="ellipsisDiv_full" >
						${value}
						</div>
					</li>
					</s:iterator>
				</s:if>
				<s:else>
					<s:iterator value="mapBuildSel">
					<li id="btli_${key}" value="${key}" onclick="selectFloor(this,'${key}');" title="${value}">
						<div class="ellipsisDiv_full" >
						${value}
						</div>
					</li>
					</s:iterator>
				</s:else>
			</ul>
		</div>
	</div>
	</s:if>
	
	<div id="divStore" style="float: left; margin-right: 20px; width: 150px; height: 390px; border: 1px solid #ccc;">
		<div class="ctsltTitle" >${title}&nbsp;<input type="text" id="search_storeNo" style="width:90px;" class="search" searchtext="搜索..."/></div>
		<div id="divStoreData" style="height:355px; border-top:1px solid #ccc;">
			<ul id="ulStore" class="ctslt-nav" >
			
			</ul>
		</div>
	</div>
	
	<div id="divStoreSelected" style="float: left; width: 150px; height: 390px; border: 1px solid #ccc;">
		<div class="ctsltTitle" >已选${title}</div>
		<div id="divStoreSelectedData" style="height:355px; border-top:1px solid #ccc;">
			<ul id="ulStoreSelected" class="ctslt-nav" >
			
			</ul>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	initStoreSelected();
	
	if($("#floorType").val() == 3) {
		loadStore();
	} else {
		
		if($("#ulFloor").height() > 355) {
			$("#divFloorData").addClass("div_scroll_y");
		} else {
			$("#divFloorData").removeClass("div_scroll_y");
		}
	}
	
	bindSearchEv();
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

//选择楼(层)号
function selectFloor(dom,value) {
	$("#ulFloor li").removeClass("ctslt-nav-click");
	$(dom).addClass("ctslt-nav-click");
	$("#bisFloorId").val(value);
	if($("#search_storeNo").val() != $("#search_storeNo").attr("searchtext")) {
		$("#search_storeNo").val('');
	}
	loadStore(value);
}
//加载商铺
function loadStore(bisFloorId) {
	var filter_LIKES_storeNo = $("#search_storeNo").val();
	if($("#search_storeNo").val() == $("#search_storeNo").attr("searchtext")) {
		filter_LIKES_storeNo = "";
	}
	var data = {
		bisProjectId:$("#bisProjectHideId").val(),
		floorType:$("#floorType").val(),
		bisFloorId:bisFloorId,
		filter_LIKES_storeNo:filter_LIKES_storeNo
	};
	//TB_showMaskLayer("正在搜索...");
	$.post("${ctx}/bis/bis-project!loadStoreList.action", data, function(result) {
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
//选择商铺
function selectStore(storeId) {
	var bisStoreIdsTemp = $("#bisStoreIdsTemp").val();
	var bisStoreNosTemp = $("#bisStoreNosTemp").val();
	var storeNo = $("#stli_"+storeId).text().trim();
	if($("#stli_"+storeId).hasClass("ctslt-nav-click")) {
		$("#stli_"+storeId).removeClass("ctslt-nav-click");
		bisStoreIdsTemp=bisStoreIdsTemp.replace(storeId+",","");
		bisStoreNosTemp=bisStoreNosTemp.replace(storeNo+",","");
		
		$("#stsli_"+storeId).remove();
		if($("#ulStoreSelected").height() <= 355) {
			$("#divStoreSelectedData").removeClass("div_scroll_y");
		}
	} else {
		// 公寓/多经 单选
		//if($("#floorType").val() != 1) {
		//	$("#ulStore li").removeClass("ctslt-nav-click");
		//	$("#ulStoreSelected li").remove();
		//	bisStoreIdsTemp='';
		//	bisStoreNosTemp='';
		//}
		
		$("#stli_"+storeId).addClass("ctslt-nav-click");
		bisStoreIdsTemp=bisStoreIdsTemp+storeId+",";
		bisStoreNosTemp=bisStoreNosTemp+storeNo+",";
		var li = '<li id=stsli_'+storeId+' class="ctslt-nav-click" onclick="removeStoreSelected(\''+storeId+'\')" title="'+storeNo+'" >'+storeNo+'</li>';
		$("#ulStoreSelected").append(li);
		if($("#ulStoreSelected").height() > 355) {
			$("#divStoreSelectedData").addClass("div_scroll_y");
		}
	}
	$("#bisStoreIdsTemp").val(bisStoreIdsTemp);
	$("#bisStoreNosTemp").val(bisStoreNosTemp);
}
//移除已选商铺
function removeStoreSelected(storeId) {
	var bisStoreIdsTemp = $("#bisStoreIdsTemp").val();
	var bisStoreNosTemp = $("#bisStoreNosTemp").val();
	var storeNo = $("#stsli_"+storeId).text().trim();
	bisStoreIdsTemp=bisStoreIdsTemp.replace(storeId+",","");
	bisStoreNosTemp=bisStoreNosTemp.replace(storeNo+",","");
	$("#stsli_"+storeId).remove();
	if($("#ulStoreSelected").height() <= 355) {
		$("#divStoreSelectedData").removeClass("div_scroll_y");
	}
	if($("#stli_"+storeId)) {
		$("#stli_"+storeId).removeClass("ctslt-nav-click");
	}
	$("#bisStoreIdsTemp").val(bisStoreIdsTemp);
	$("#bisStoreNosTemp").val(bisStoreNosTemp);
}
//初始化商铺选择
function initCheckBox() {
	var bisStoreIdsTemp = $("#bisStoreIdsTemp").val();
	var	bisStoreIds = bisStoreIdsTemp.split(",");
	for(i=0;i<bisStoreIds.length-1;i++){
		if($("#stli_"+bisStoreIds[i])) {
			$("#stli_"+bisStoreIds[i]).addClass("ctslt-nav-click");
		}
	}
}
//初始化已选商铺
function initStoreSelected() {
	var bisStoreIdsTemp = $("#bisStoreIdsTemp").val();
	var bisStoreNosTemp = $("#bisStoreNosTemp").val();
	var	bisStoreIds = bisStoreIdsTemp.split(",");
	var	bisStoreNos = bisStoreNosTemp.split(",");
	for(i=0;i<bisStoreIds.length-1;i++){
		var storeId = bisStoreIds[i];
		var storeNo = bisStoreNos[i];
		var li = '<li id=stsli_'+storeId+' class="ctslt-nav-click" onclick="removeStoreSelected(\''+storeId+'\')" title="'+storeNo+'" >'+storeNo+'</li>';
		$("#ulStoreSelected").append(li);
	}
	if($("#ulStoreSelected").height() > 355) {
		$("#divStoreSelectedData").addClass("div_scroll_y");
	}
}
</script>