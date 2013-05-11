<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisStoreSelect.css" />

<input type="hidden" id="selectThridAssmId"/>
<input type="hidden" id="selectThridAssmName"/>
<input type="hidden" id="selectAssmModelId"/>
<input type="hidden" id="selectAssmModelName"/>

<div style="padding: 15px 20px 0 20px; height: 400px;">
	<div id="divFloor" style="float: left; margin-right: 20px; width: 150px; height: 390px; border: 1px solid #ccc;">
		<div class="ctsltTitle">二级设备</div>
		<div id="divFloorData" style="height:355px; border-top:1px solid #ccc;">
			<ul id="ulFloor" class="ctslt-nav" >
				<s:iterator value="mapAssmModel">
				<li id="_${key}" value="${key}" onclick="selectNextModel(this,'${key}','2');" title="${value}">
					<div class="ellipsisDiv_full" >
						${value}
					</div>
				</li>
				</s:iterator>
			</ul>
		</div>
	</div>
	
	<div id="divStore" style="float: left; margin-right: 20px; width: 150px; height: 390px; border: 1px solid #ccc;">
		<div class="ctsltTitle">三级设备</div>
		<div id="divStoreData" style="height:355px; border-top:1px solid #ccc;">
			<ul id="ulStore" class="ctslt-nav" >
			
			</ul>
		</div>
	</div>
	
	<div id="divStoreSelected" style="float: left; width: 150px; height: 390px; border: 1px solid #ccc;">
		<div class="ctsltTitle" >四级设备</div>
		<div id="divStoreSelectedData" style="height:355px; border-top:1px solid #ccc;">
			<ul id="ulStoreSelected" class="ctslt-nav" >
			
			</ul>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	if($("#ulFloor").height() > 355) {
		$("#divFloorData").addClass("div_scroll_y");
	} else {
		$("#divFloorData").removeClass("div_scroll_y");
	}
});

//选择二级设备
function selectNextModel(dom,key,level,value) {
	if('2'==level){
		$("#ulFloor li").removeClass("ctslt-nav-click");
	}else if('3'==level){
		
		$("#ulStore li").removeClass("ctslt-nav-click");
		$("#selectAssmModelId").val('');
		$("#selectAssmModelName").val('');
		$("#selectThridAssmName").val(value); //选中的第三级设备名称(用于判断‘电脑’类的设备保管人只能有一个)
		$("#selectThridAssmId").val(key); //选中的第三级设备ID
	}else{
		$("#ulStoreSelected li").removeClass("ctslt-nav-click");
		$("#selectAssmModelId").val(key);
		$("#selectAssmModelName").val(value);
	}
	$(dom).addClass("ctslt-nav-click");
	loadStore(key,level);
}

//加载下一级设备
function loadStore(assmModelId,level) {
	var data = {
		assmModelId:assmModelId,
		level:level
	};
	$.post("${ctx}/assm/assm-account!loadNextModel.action", data, function(result) {
		if('2'==level){
			$("#ulStore").html(result);
		}else if('3'==level){
			$("#ulStoreSelected").html(result);
		}
		//initCheckBox();
		if($("#ulStore").height() > 355) {
			$("#divStoreData").addClass("div_scroll_y");
		} else {
			$("#divStoreData").removeClass("div_scroll_y");
		}
		if($("#ulStoreSelected").height() > 355) {
			$("#divStoreSelectedData").addClass("div_scroll_y");
		} else {
			$("#divStoreSelectedData").removeClass("div_scroll_y");
		}
	});
}
/*
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
*/
</script>