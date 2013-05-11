<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisStoreSelect.css" />

<input type="hidden" id="provinceName" value="${cooperateArea}"/>
<input type="hidden" id="provinceNameCd" value="${cooperateAreaCd}"/>

<div style="padding: 15px;height: 400px;" align="center">
	<div id="divFloor" style="float: left;margin-right:10px;margin-left:10px; width: 170px; height: 390px; border: 1px solid #ccc;">
		<div class="ctsltTitle">省份</div>
		<div id="divFloorData" style="height:355px; border-top:1px solid #ccc;">
			<ul id="ulFloor" class="ctslt-nav" >
				<s:iterator value="mapProvince">
					<li id="pro_${key}" value="${key}" onclick="selectNextPro(this,'${key}');" title="${key}">
						<div class="ellipsisDiv_full" style="text-align: left;">
							${value}
						</div>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
	
	<div id="divStore" style="float: right;margin-right:10px; width: 170px; height: 390px; border: 1px solid #ccc;">
		<div class="ctsltTitle">已选省份</div>
		<div id="divProData" style="height:355px; border-top:1px solid #ccc;">
			<ul id="ulPro" class="ctslt-nav">
				
			</ul>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	initSelected();
	if($("#ulFloor").height() > 355) {
		$("#divFloorData").addClass("div_scroll_y");
	} else {
		$("#divFloorData").removeClass("div_scroll_y");
	}
});
//初始化选中的值
function initSelected(){
	var provinceNameCd = $("#provinceNameCd").val();
	var provinceName = $("#provinceName").val();
	if(provinceNameCd != '' && provinceName !=''){
		var	provinceNameCds = provinceNameCd.split(",");
		var	provinceNames = provinceName.split(",");
		for(var i=0; i < provinceNameCds.length-1;i++){
			var pCd = provinceNameCds[i];
			var pName = provinceNames[i];
			var li = '<li id=pro_s_'+pCd+' class="ctslt-nav-click" onclick="removeSelected(\''+pCd+'\')" title="'+pName+'" >'+pName+'</li>';
			$("#ulPro").append(li);
		}
		if($("#ulPro").height() > 355) {
			$("#divProData").addClass("div_scroll_y");
		}
	}
}

//选择省
function selectNextPro(dom,id) {
	var provinceName = $("#provinceName").val();
	var provinceNameCd = $("#provinceNameCd").val();
	var pName = $("#pro_"+id).text().trim();
	if($("#pro_"+id).hasClass("ctslt-nav-click")) {
		$("#pro_"+id).removeClass("ctslt-nav-click");
		provinceName = provinceName.replace(pName+",","");
		provinceNameCd = provinceNameCd.replace(id+",","");
		$("#pro_s_"+id).remove();
		if($("#ulPro").height() <= 355) {
			$("#divProData").removeClass("div_scroll_y");
		}
	} else {
		var flag = false;
		$("#ulPro").find('li').each(function(){
			var liId = $(this).attr("id");
			var tmpId = "pro_s_"+id;
			if(liId == tmpId){
				flag = true;
			}
		});
		if(flag){
			$("#pro_"+id).removeClass("ctslt-nav-click");
			provinceName = provinceName.replace(pName+",","");
			provinceNameCd = provinceNameCd.replace(id+",","");
			$("#pro_s_"+id).remove();
			if($("#ulPro").height() <= 355) {
				$("#divProData").removeClass("div_scroll_y");
			}
		}else{
			//$("#pro_"+id).addClass("ctslt-nav-click");
			if(provinceName == ''){
				provinceName = pName+",";
			}else{
				provinceName = provinceName+pName+",";
			}
			if(provinceNameCd == ''){
				provinceNameCd = id+",";
			}else{
				provinceNameCd = provinceNameCd+id+",";
			}
			var li = '<li id=pro_s_'+id+' class="ctslt-nav-click" onclick="removeSelected(\''+id+'\')" title="'+pName+'" >'+pName+'</li>';
			$("#ulPro").append(li);
			if($("#ulPro").height() > 355) {
				$("#divProData").addClass("div_scroll_y");
			}
		}
	}
	$("#provinceName").val(provinceName);
	$("#provinceNameCd").val(provinceNameCd);
}
//移除选中的数据
function removeSelected(id){
	var provinceName = $("#provinceName").val();
	var provinceNameCd = $("#provinceNameCd").val();
	var pName = $("#pro_"+id).text().trim();
	provinceName = provinceName.replace(pName+",","");
	provinceNameCd = provinceNameCd.replace(id+",","");
	$("#pro_s_"+id).remove();
	if($("#ulPro").height() <= 355) {
		$("#divProData").removeClass("div_scroll_y");
	}
	if($("#pro_"+id)) {
		$("#pro_"+id).removeClass("ctslt-nav-click");
	}
	$("#provinceName").val(provinceName);
	$("#provinceNameCd").val(provinceNameCd);
}
</script>