var _PAGEWIDTH = 0;
var _PAGEHEIGHT = 0;
var _RIGHT = 0;
var _MIDDLEWIDTH = 0;
var now_status_middle_content_top = "";	//现在中间的顶部div处于哪个状态
var now_status_middle_content_center = "";	//现在中间的主div处于哪个状态
function doOnload(){
	_PAGEWIDTH = Number(document.documentElement.clientWidth);
	_PAGEHEIGHT = Number(document.documentElement.clientHeight);
	_MIDDLEWIDTH = _PAGEWIDTH-10-220-10-70-10;
	
	//document.getElementById("middle_content_top").style.width = _MIDDLEWIDTH+"px";
	//document.getElementById("middle_content_top").style.visibility = "visible";
	//document.getElementById("middle_top_container").style.width = Number(_MIDDLEWIDTH)+"px";
	
	document.getElementById("middle_content_center").style.width = _MIDDLEWIDTH+"px";
	document.getElementById("middle_content_center").style.visibility = "visible";
	document.getElementById("div_contents").style.width = Number(_MIDDLEWIDTH-20)+"px";

	//setMoveDiv('middle_content_top','default',240,5,_MIDDLEWIDTH,370);
	//setMoveDiv('middle_content_top','max',240,5,_MIDDLEWIDTH,700);
	
	setMoveDiv('middle_content_center','default',240,390,_MIDDLEWIDTH,428);
	setMoveDiv('middle_content_center','max',240,5,_MIDDLEWIDTH,800);
	now_status_middle_content_top = "default";
	now_status_middle_content_center = "default";
	parent.setIframeMouse(0,0);
}
function div_move_middle_content_top(url){
	if("default"==now_status_middle_content_top){
		divMove_y_h('middle_content_top','max','after_move_middle_content_top("max")','middle_top_container',48);
	}else if("max"==now_status_middle_content_top){
		//document.getElementById('middle_content_center').style.visibility="visible";
		divMove_y_h('middle_content_top','default','after_move_middle_content_top("default")','middle_top_container',48);
	}
}
function after_move_middle_content_top(status){
	if("max"==status){
		//document.getElementById('middle_top_container').style.visibility="visible";
		//document.getElementById('middle_content_center').style.visibility="hidden";
	}
	now_status_middle_content_top = status;
}
function div_move_middle_content_center(url){
	$("#div_contents").hide();
	if("default"==now_status_middle_content_center){
		//if(null!=url){
			document.getElementById('div_contents').src=curMidBottomTabUrl;
		//}
			
		divMove_y_h('middle_content_center','max','after_move_middle_content_center("max")','div_JbpmTravel_contents,div_contents',48);
		//$("#qp_bottom").attr("src",_ctx+"/images/tab/close.gif");
		$("#qp_bottom").attr("className","closeBtn");
	}else if("max"==now_status_middle_content_center){
		$("#middle_content_top").show();
		
		//if(null!=url){
			document.getElementById('div_contents').src=curMidBottomTabUrl+"&pageSize=3";
		//}
		divMove_y_h('middle_content_center','default','after_move_middle_content_center("default")','div_JbpmTravel_contents,div_contents',48);
		//$("#qp_bottom").attr("src","/PowerDesk/images/desk/anniu_quanping.png");
		$("#qp_bottom").attr("className","maxBtn");
	}
}
function after_move_middle_content_center(status){
	if("max"==status){
		$("#middle_content_top").hide();
	}
	$("#div_contents").show();
	//document.getElementById('div_contents').style.visibility="visible";
	now_status_middle_content_center = status;
}
