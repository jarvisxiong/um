<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisStoreSelect.css" />

<input type="hidden" id="bisProjectHideId" value="${bisProjectId}"/>
<input type="hidden" id="floorType" value="${floorType}" />
<input type="hidden" id="bisFloorId" value="${bisFloorId}" />
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
	

<script type="text/javascript">
$(function(){
	
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

</script>