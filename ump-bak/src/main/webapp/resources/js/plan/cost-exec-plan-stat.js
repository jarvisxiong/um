//全局变量
var isLoading = false;
var scrollWidth = 100;
var isScrolling = false;

function getCenterCd(){
	return $("#curCenterDiv").val();
}
function setCenterCd(centerCd){
	$("#curCenterDiv").val(centerCd);
}
function getProjectCd(){
	return $("#curProjDiv").val();
}
function setProjecCd(projectCd){
	$("#curProjDiv").val(projectCd);
}
function getOperationId(){
	return $("#curOperationId").val();
}
function setOperationId(operationId){
	$("#curOperationId").val(operationId);
}
function getPlanTypeCd(){
	return $("#curPlanTypeCd").val();
}
function setPlanTypeCd(planTypeCd){
	$("#curPlanTypeCd").val(operationId);
}


//切换项目
function switchProject(srcElem, projectCd){
	if (isLoading) {
		return;
	}

	isLoading = true;
	$("#planContainerDiv").html('<div class="waiting" style="width:200px;height:100px;"></div>');
	$("#nav_btns_projects li div.active").removeClass("active");
	if( srcElem){
		srcElem.addClass("active");
	}
	
	
	//为全屏直接定位到正在操作的当前业态,特此判断
	var tProjectCd  = getProjectCd();
	var operationId = '';
	if( tProjectCd == projectCd){
		operationId = getOperationId();
	}
	
	var planTypeCd = getPlanTypeCd();
	var data = {projectCd: projectCd,operationId: operationId,planTypeCd: planTypeCd};
	$.post(_ctx+'/plan/cost-exec-plan-stat!switchProject.action',data, function(result) {
		$("#nav_btns_container_operations").html(result).hide().fadeIn();
		setProjecCd(projectCd);
		resetMatrixLayout();
		isLoading = false;
	});
}

//切换业态
function switchOperation(srcElem, projectCd, operationId){
	if (isLoading) {
		return;
	}
	
	isLoading = true;
	$("#planContainerDiv").html('<div class="waiting" style="width:200px;height:100px;"></div>');
	$("#nav_btns_operations li div.active").removeClass("active");
	if(srcElem){
		srcElem.addClass("active");
	}
	
	var planTypeCd = getPlanTypeCd();
	var data = {projectCd: projectCd, operationId : operationId, planTypeCd: planTypeCd};
	$.post(_ctx + "/plan/cost-exec-plan-stat!switchOperation.action", data, function(result) {
		$("#planContainerDiv").html(result).hide().fadeIn();
		setOperationId(operationId);
		resetMatrixLayout();
		isLoading = false;
	});
}
//全屏显示
function switchFullScreen(){
	var url = new Array();
	url.push(_ctx+'/plan/cost-exec-plan-stat!portal.action?');
	url.push("centerCd="+getCenterCd());
	url.push("&projectCd=" + getProjectCd());
	url.push("&operationId=" + getOperationId());
	url.push("&planTypeCd=" + getPlanTypeCd());
	window.open(url.join(""));
}

// 滚动项目菜单
function scrollNavBtn(direction) {
	if (isScrolling) {
		return;
	}
	var mgLeft = parseInt($("#nav_btns_projects").css("margin-left"));
	if (direction == "left") {
		mgLeft = mgLeft - scrollWidth;
	} else if (direction == "right") {
		if (mgLeft < 0) {
			mgLeft = mgLeft + scrollWidth;
		}
	}
	isScrolling = true;
	$("#nav_btns_projects").animate({marginLeft: mgLeft}, 300, function() {
		isScrolling = false;
	});
}
// 滚动业态菜单
function scrollNavBtnOperation(direction) {
	if (isScrolling) {
		return;
	}
	var mgLeft = parseInt($("#nav_btns_operations").css("margin-left"));
	if (direction == "left") {
		mgLeft = mgLeft - scrollWidth;
	} else if (direction == "right") {
		if (mgLeft < 0) {
			mgLeft = mgLeft + scrollWidth;
		}
	}
	isScrolling = true;
	$("#nav_btns_operations").animate({marginLeft: mgLeft}, 300, function() {
		isScrolling = false;
	});
}

//滚动自调整
function resetLayout(srcElem){
	var t = parseInt(srcElem.scrollTop());
	var l = parseInt(srcElem.scrollLeft());
	
	$("#bottomLeftContent").css("margin-top", -t + "px");
	$("#topRightTitle").css("margin-left", -l + "px");
}

//自适应大小
function resetMatrixLayout() {
	resetMatrixHeight();
	resetMatrixWidth();
}
function resetMatrixHeight() {
	var totalHeight = parent.$("#workplanexecstat_frame").height(); 
	var operationsHeight = $("#nav_btns_container_operations").height();
	if (totalHeight) {
		$("#planContainerDiv").height(totalHeight - operationsHeight- 65);
		$("#bottomLeftDiv").height(totalHeight - operationsHeight- 130);
		$("#bottomRightDiv").height(totalHeight - operationsHeight- 130);
	} else {
		totalHeight = $(document).height();
		$("#planContainerDiv").height(totalHeight - operationsHeight- 30);
		$("#bottomLeftDiv").height(totalHeight - operationsHeight- 95);
		$("#bottomRightDiv").height(totalHeight - operationsHeight- 95);
	}
}
function resetMatrixWidth() {
	var trtw = $("#topRightTitle").width();
	if (trtw == 0) {
		return;
	}
	var totalWidth = parent.$("#workplanexecstat_frame").width();
	if (!totalWidth) {
		totalWidth = $(document).width();
	}
	var h1 = $("#bottomRightDiv").height();
	var h2 = $("#bottomRightDiv table").height();
	var tltw = $("#topLeftTd").width();
	var rw = totalWidth - tltw - 17;
	if (h2 > h1 - 17) {
		if ($("#topRightTitle").width() >= rw - 17) {
			$("#topRightTd").width(rw+10);
			$("#topRightDiv").width(rw - 17+10);
			$("#bottomRightDiv").width(rw+10);
			$("#mainMatrix").width(totalWidth - 17);
		}
	} else {
		if ($("#topRightTitle").width() >= rw) {
			$("#topRightTd").width(rw+10);
			$("#topRightDiv").width(rw+10);
			$("#bottomRightDiv").width(rw+10);
			$("#mainMatrix").width(totalWidth - 17);
			$("#bottomRightDiv").height(h2 + 17);
		}
	}
}