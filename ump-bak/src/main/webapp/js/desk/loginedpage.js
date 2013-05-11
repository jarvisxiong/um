var isIE = /*@cc_on!@*/!1;	//是否是IE，如果是IE用滤镜，如果不是IE用CANVAS
var _PAGEWIDTH = 0;	//屏幕宽
var _PAGEHEIGHT = 0;	//屏幕高
var menu_timout1;	//中控台菜单的seTimeout对象1
var menu_timout2;	//中控台菜单的seTimeout对象2
var menu_timout3;	//中控台菜单的seTimeout对象3
var if_show_menu1 = false;	//是否正在显示中控台菜单1
var if_show_menu2 = false;	//是否正在显示中控台菜单2
var if_show_menu3 = false;	//是否正在显示中控台菜单3-搜索
var if_show_menu1_btn = false;	//是否正在显示中控台菜单1的按钮
var if_show_menu2_btn = false;	//是否正在显示中控台菜单1的按钮
var if_in_left_btn_move = false;	//是否在左边按钮的移动动画中
var if_in_right_btn_move = false;	//是否在右边按钮的移动动画中
var left_menu_btn_count = 0;	//中控台左边按钮的计数器
var right_menu_btn_count = 0;	//中控台右边按钮的计数器
var if_can_open_center_menu = true;	//是否能激活中控台按钮
var mousePos;	//mousePos对象，用于中控台的setTimout机制，如果鼠标仅仅是短时间离开中控台，中控台然后要显示
var ua = navigator.userAgent.toLowerCase();  
var is360se = ua.indexOf("360se") > -1 ;	//判断是否是360浏览器
function doOnload(){
	_PAGEWIDTH = document.documentElement.clientWidth;
	_PAGEHEIGHT = document.documentElement.clientHeight;
	if(is360se){
		document.getElementById("iframe_frontpage").style.height = parseInt(_PAGEHEIGHT-40)+"px";
	}else{
		document.getElementById("iframe_frontpage").style.height = parseInt(_PAGEHEIGHT-24)+"px";
	}
	document.getElementById("iframe_frontpage").style.visibility = "visible";
	document.getElementById("iframe_frontpage").src = iframe_src;
	document.getElementById("div_logo_pd").style.left = parseInt(_PAGEWIDTH/2-44)+"px";
	document.getElementById("div_logo_pd").style.visibility = "visible";
	document.getElementById("div_logo_mouse_hide").style.left = parseInt(_PAGEWIDTH/2-25)+"px";
	document.getElementById("div_logo_pd1").style.left = parseInt(_PAGEWIDTH/2-21)+"px";
	document.getElementById("div_logo_pd2").style.left = parseInt(_PAGEWIDTH/2-21)+"px";
	document.getElementById("div_logo_pd3").style.left = Number(_PAGEWIDTH/2-45)+"px";
	setMoveDiv('div_logo_pd1','hidden',parseInt(_PAGEWIDTH/2-21),0);
	setMoveDiv('div_logo_pd1','show',parseInt(_PAGEWIDTH/2-55),21);
	setMoveDiv('div_logo_pd2','hidden',parseInt(_PAGEWIDTH/2-21),0);
	setMoveDiv('div_logo_pd2','show',parseInt(_PAGEWIDTH/2+13),21);
	setMoveDiv('div_logo_pd3','hidden',parseInt(_PAGEWIDTH/2-45),5);
	setMoveDiv('div_logo_pd3','show',parseInt(_PAGEWIDTH/2-45),70);
	document.getElementById("div_logo_light").style.left = parseInt(_PAGEWIDTH/2-29)+"px";
	document.getElementById("div_logo_light").style.visibility = "visible";
	
	document.getElementById("div_logo_zuo_0").style.left = parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_zuo_0")/2)+"px";
	setMoveDiv('div_logo_zuo_0','hidden',parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_zuo_0")/2),4);
	setMoveDiv('div_logo_zuo_0','show',parseInt(_PAGEWIDTH/2-126),8);
	document.getElementById("div_logo_zuo_1").style.left = parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_zuo_1")/2)+"px";
	setMoveDiv('div_logo_zuo_1','hidden',parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_zuo_1")/2),4);
	setMoveDiv('div_logo_zuo_1','show',parseInt(_PAGEWIDTH/2-136),32);
	document.getElementById("div_logo_zuo_2").style.left = parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_zuo_2")/2)+"px";
	setMoveDiv('div_logo_zuo_2','hidden',parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_zuo_2")/2),4);
	setMoveDiv('div_logo_zuo_2','show',parseInt(_PAGEWIDTH/2-146),56);
	document.getElementById("div_logo_zuo_3").style.left = parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_zuo_3")/2)+"px";
	setMoveDiv('div_logo_zuo_3','hidden',parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_zuo_3")/2),4);
	setMoveDiv('div_logo_zuo_3','show',parseInt(_PAGEWIDTH/2-146),80);
	document.getElementById("div_logo_zuo_4").style.left = parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_zuo_4")/2)+"px";
	setMoveDiv('div_logo_zuo_4','hidden',parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_zuo_4")/2),4);
	setMoveDiv('div_logo_zuo_4','show',parseInt(_PAGEWIDTH/2-156),104);
	document.getElementById("div_logo_zuo_5").style.left = parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_zuo_5")/2)+"px";
	setMoveDiv('div_logo_zuo_5','hidden',parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_zuo_5")/2),4);
	setMoveDiv('div_logo_zuo_5','show',parseInt(_PAGEWIDTH/2-126),128);
	
	document.getElementById("div_logo_you_0").style.left = parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_you_0")/2)+"px";
	setMoveDiv('div_logo_you_0','hidden',parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_you_0")/2),4);
	setMoveDiv('div_logo_you_0','show',parseInt(_PAGEWIDTH/2+50),8);
	document.getElementById("div_logo_you_1").style.left = parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_you_1")/2)+"px";
	setMoveDiv('div_logo_you_1','hidden',parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_you_1")/2),4);
	setMoveDiv('div_logo_you_1','show',parseInt(_PAGEWIDTH/2+60),32);
	document.getElementById("div_logo_you_2").style.left = parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_you_2")/2)+"px";
	setMoveDiv('div_logo_you_2','hidden',parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_you_2")/2),4);
	setMoveDiv('div_logo_you_2','show',parseInt(_PAGEWIDTH/2+70),56);
	document.getElementById("div_logo_you_3").style.left = parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_you_3")/2)+"px";
	setMoveDiv('div_logo_you_3','hidden',parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_you_3")/2),4);
	setMoveDiv('div_logo_you_3','show',parseInt(_PAGEWIDTH/2+70),80);
	document.getElementById("div_logo_you_4").style.left = parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_you_4")/2)+"px";
	setMoveDiv('div_logo_you_4','hidden',parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_you_4")/2),4);
	setMoveDiv('div_logo_you_4','show',parseInt(_PAGEWIDTH/2+60),104);
	document.getElementById("div_logo_you_5").style.left = parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_you_5")/2)+"px";
	setMoveDiv('div_logo_you_5','hidden',parseInt(_PAGEWIDTH/2-getObjWidth("div_logo_you_5")/2),4);
	setMoveDiv('div_logo_you_5','show',parseInt(_PAGEWIDTH/2+50),128);
	
	/*
	if(isIE){
		var el = document.getElementById("div_logo_light"); 
		el.style.filter = "progid:DXImageTransform.Microsoft.Matrix()"; 
		el.filters.item("DXImageTransform.Microsoft.Matrix").SizingMethod = "auto expand"; 
		el.filters.item("DXImageTransform.Microsoft.Matrix").FilterType = "bilinear"; 
	}
	*/
	doMouse();
	/*
	$('#xiala_main_menu_out').mouseleave(function(){
		document.getElementById('xiala_main_menu').style.display='none';
		document.getElementById('xiala_mouse_out').style.display='none';
		for(var i=0;i<6;i++){
			document.getElementById("xiala_sub_menu_"+i).style.display = "none";
		}
	});
	*/
}
//显示中控台菜单
function doShowMenu(menu_id){
	document.getElementById("div_logo_pd"+menu_id).style.visibility = "visible";
	divGoto("div_logo_pd"+menu_id,"show","setMenu123Show('"+menu_id+"')");
	if(1==menu_id){
		if_show_menu1 = true;
	}else if(2==menu_id){
		if_show_menu2 = true;
	}else if(3==menu_id){
		if_show_menu3 = true;
		document.getElementById("search_input").focus();
	}
}
//点击隐藏中控台菜单
function doHideCenterMenu(){
	document.getElementById("div_logo_pd1").style.zIndex = 10;
	document.getElementById("div_logo_pd2").style.zIndex = 10;
	document.getElementById("div_logo_pd3").style.zIndex = 10;
	divGoto("div_logo_pd1","hidden","setMenu123Hidden(1)");
	divGoto("div_logo_pd2","hidden","setMenu123Hidden(2)");
	divGoto("div_logo_pd3","hidden","setMenu123Hidden(3)");
	if_can_open_center_menu = false;
	window.setTimeout("if_can_open_center_menu=true;",1000);
}
//中控台主按钮出现后动作
function setMenu123Show(menu_id){
	document.getElementById("div_logo_pd"+menu_id).style.zIndex = 16;
}
//中控台主按钮隐藏后动作
function setMenu123Hidden(menu_id){
	document.getElementById("div_logo_pd"+menu_id).style.visibility = "hidden";
	if(1==menu_id){
		if_show_menu1 = false;
		window.clearTimeout(menu_timout1);
		menu_timout1 = null;
		if(if_show_menu1_btn){
			hideLeftMenu();
		}
	}else if(2==menu_id){
		if_show_menu2 = false;
		window.clearTimeout(menu_timout2);
		menu_timout2 = null;
		if(if_show_menu2_btn){
			hideRightMenu();
		}
	}else if(3==menu_id){
		if_show_menu3 = false;
		window.clearTimeout(menu_timout3);
		menu_timout3 = null;
	}
}
//隐藏中控台菜单1
function doHideMenu(menu_id){
	//document.getElementById("printspan1").innerHTML = Number(document.getElementById("printspan1").innerHTML)+1;
	if(1==menu_id){
		if(null==menu_timout1){
			menu_timout1 = window.setTimeout("menu_timeout("+menu_id+")",1000);
		}
	}else if(2==menu_id){
		if(null==menu_timout2){
			menu_timout2 = window.setTimeout("menu_timeout("+menu_id+")",1000);
		}
	}else if(3==menu_id){
		if(null==menu_timout3){
			menu_timout3 = window.setTimeout("menu_timeout("+menu_id+")",1000);
		}
	}
}
//隐藏中控台菜单2
function menu_timeout(menu_id){
	if(!judgeInMenuOut(mousePos.x,mousePos.y)){
		document.getElementById("div_logo_pd"+menu_id).style.zIndex = 10;
		divGoto("div_logo_pd"+menu_id,"hidden","setMenu123Hidden('"+menu_id+"')");
	}else{
		if(1==menu_id){
			window.clearTimeout(menu_timout1);
			menu_timout1 = null;
		}else if(2==menu_id){
			window.clearTimeout(menu_timout2);
			menu_timout2 = null;
		}else if(3==menu_id){
			window.clearTimeout(menu_timout3);
			menu_timout3 = null;
		}
	}
}
//判断是否在中控台的响应区域内
function judgeInMenuIn(mousex,mousey){
	if(mousey<60){
		if(40<mousey && mousey<60 && _PAGEWIDTH/2-20<mousex && mousex<_PAGEWIDTH/2+20){
			return 3;
		}else if(_PAGEWIDTH/2-50<mousex && mousex<_PAGEWIDTH/2){
			return 1;
		}else if(_PAGEWIDTH/2<mousex && mousex<_PAGEWIDTH/2+50){
			return 2;
		}
	}
	return false;
}
//判断是否在中控台的响应区域外
function judgeInMenuOut(mousex,mousey){
	if(mousey<180){
		if(_PAGEWIDTH/2-220<mousex && mousex<_PAGEWIDTH/2+220){
			return 2;
		}
	}
	return false;
}
//中控台响应区域动作
function doMouseMenu(mousex,mousey){
	var if_in_menu_in = judgeInMenuIn(mousex,mousey);
	var if_in_menu_out = judgeInMenuOut(mousex,mousey);
	if(false != if_in_menu_in){
		var menu_id = if_in_menu_in;
		if(if_can_open_center_menu){
			if(!eval("if_show_menu"+menu_id)){
				doShowMenu(menu_id);
			}
		}
	}else if(!if_in_menu_out){
		if(if_show_menu1 || if_show_menu1_btn){
			doHideMenu(1);
		}
		if(if_show_menu2 || if_show_menu2_btn){
			doHideMenu(2);
		}
		if(if_show_menu3){
			doHideMenu(3);
		}
		return false;
	}
}
//显示中控台左边按钮
function showLeftMenu(){
	if(!if_show_menu1_btn && !if_in_left_btn_move){
		if(!if_in_left_btn_move){
			left_menu_btn_count = 0;
		}
		if_in_left_btn_move = true;
		document.getElementById("div_logo_zuo_0").style.visibility = "visible";
		document.getElementById("div_logo_zuo_1").style.visibility = "visible";
		document.getElementById("div_logo_zuo_2").style.visibility = "visible";
		document.getElementById("div_logo_zuo_3").style.visibility = "visible";
		document.getElementById("div_logo_zuo_4").style.visibility = "visible";
		document.getElementById("div_logo_zuo_5").style.visibility = "visible";
		divGoto('div_logo_zuo_0','show','setObjVisible("div_logo_zuo_0")');
		divGoto('div_logo_zuo_1','show','setObjVisible("div_logo_zuo_1")');
		divGoto('div_logo_zuo_2','show','setObjVisible("div_logo_zuo_2")');
		divGoto('div_logo_zuo_3','show','setObjVisible("div_logo_zuo_3")');
		divGoto('div_logo_zuo_4','show','setObjVisible("div_logo_zuo_4")');
		divGoto('div_logo_zuo_5','show','setObjVisible("div_logo_zuo_5")');
	}
}
//显示中控台右边按钮
function showRightMenu(){
	if(!if_show_menu2_btn && !if_in_right_btn_move){
		if(!if_in_right_btn_move){
			right_menu_btn_count = 0;
		}
		if_in_right_btn_move = true;
		document.getElementById("div_logo_you_0").style.visibility = "visible";
		document.getElementById("div_logo_you_1").style.visibility = "visible";
		document.getElementById("div_logo_you_2").style.visibility = "visible";
		document.getElementById("div_logo_you_3").style.visibility = "visible";
		document.getElementById("div_logo_you_4").style.visibility = "visible";
		document.getElementById("div_logo_you_5").style.visibility = "visible";
		divGoto('div_logo_you_0','show','setObjVisible("div_logo_you_0")');
		divGoto('div_logo_you_1','show','setObjVisible("div_logo_you_1")');
		divGoto('div_logo_you_2','show','setObjVisible("div_logo_you_2")');
		divGoto('div_logo_you_3','show','setObjVisible("div_logo_you_3")');
		divGoto('div_logo_you_4','show','setObjVisible("div_logo_you_4")');
		divGoto('div_logo_you_5','show','setObjVisible("div_logo_you_5")');
	}
}
//隐藏中控台左边按钮
function hideLeftMenu(){
	if(if_show_menu1_btn && !if_in_left_btn_move){
		if(!if_in_left_btn_move){
			left_menu_btn_count = 0;
		}
		if_in_left_btn_move = true;
		divGoto('div_logo_zuo_0','hidden','setObjHidden("div_logo_zuo_0")');
		divGoto('div_logo_zuo_1','hidden','setObjHidden("div_logo_zuo_1")');
		divGoto('div_logo_zuo_2','hidden','setObjHidden("div_logo_zuo_2")');
		divGoto('div_logo_zuo_3','hidden','setObjHidden("div_logo_zuo_3")');
		divGoto('div_logo_zuo_4','hidden','setObjHidden("div_logo_zuo_4")');
		divGoto('div_logo_zuo_5','hidden','setObjHidden("div_logo_zuo_5")');
	}
}
//隐藏中控台右边按钮
function hideRightMenu(){
	if(if_show_menu2_btn && !if_in_right_btn_move){
		if(!if_in_right_btn_move){
			right_menu_btn_count = 0;
		}
		if_in_right_btn_move = true;
		divGoto('div_logo_you_0','hidden','setObjHidden("div_logo_you_0")');
		divGoto('div_logo_you_1','hidden','setObjHidden("div_logo_you_1")');
		divGoto('div_logo_you_2','hidden','setObjHidden("div_logo_you_2")');
		divGoto('div_logo_you_3','hidden','setObjHidden("div_logo_you_3")');
		divGoto('div_logo_you_4','hidden','setObjHidden("div_logo_you_4")');
		divGoto('div_logo_you_5','hidden','setObjHidden("div_logo_you_5")');
		if_show_menu2_btn = false;
	}
}
//运动完后的动作，设置状态位
function setObjVisible(obj_id){
	if("div_logo_zuo_0"==obj_id || "div_logo_zuo_1"==obj_id || "div_logo_zuo_2"==obj_id || "div_logo_zuo_3"==obj_id || "div_logo_zuo_4"==obj_id || "div_logo_zuo_5"==obj_id){
		left_menu_btn_count++;
		if(6==left_menu_btn_count){
			//如果是左边6个按钮全部做完动作，设置状态位
			if_show_menu1_btn = true;
			if_in_left_btn_move = false;
		}
	}else if("div_logo_you_0"==obj_id || "div_logo_you_1"==obj_id || "div_logo_you_2"==obj_id || "div_logo_you_3"==obj_id || "div_logo_you_4"==obj_id || "div_logo_you_5"==obj_id){
		right_menu_btn_count++;
		if(6==right_menu_btn_count){
			//如果是左边最后一个，设置状态位
			if_show_menu2_btn = true;
			if_in_right_btn_move = false;
		}
	}
}
//运动完后的动作，设置某id的style.visibile属性为hidden
function setObjHidden(obj_id){
	document.getElementById(obj_id).style.visibility = "hidden";
	if("div_logo_zuo_0"==obj_id || "div_logo_zuo_1"==obj_id || "div_logo_zuo_2"==obj_id || "div_logo_zuo_3"==obj_id || "div_logo_zuo_4"==obj_id || "div_logo_zuo_5"==obj_id){
		left_menu_btn_count++;
		if(6==left_menu_btn_count){
			//如果是左边6个按钮全部做完动作，设置状态位
			if_show_menu1_btn = false;
			if_in_left_btn_move = false;
		}
	}else if("div_logo_you_0"==obj_id || "div_logo_you_1"==obj_id || "div_logo_you_2"==obj_id || "div_logo_you_3"==obj_id || "div_logo_you_4"==obj_id || "div_logo_you_5"==obj_id){
		right_menu_btn_count++;
		if(6==right_menu_btn_count){
			//如果是左边最后一个，设置状态位
			if_show_menu2_btn = false;
			if_in_right_btn_move = false;
		}
	}
}

//iframe子页面捕捉鼠标,支撑3级的iframe嵌套
function setIframeMouse(iframe_id,sub_iframe_id,third_iframe_id){
	if(null==third_iframe_id){
		if(null==sub_iframe_id){
			if(null==iframe_id){
				iframe_id = 0;
			}
			if ( navigator.userAgent.search( 'MSIE' ) == -1 ){
				frames[iframe_id].document.addEventListener( 'mousemove',  mouseFunc1, false );
			}else{
				frames[iframe_id].document.attachEvent( 'onmousemove', mouseFunc1 );
			}
		}else{
			if ( navigator.userAgent.search( 'MSIE' ) == -1 ){
				frames[iframe_id].frames[sub_iframe_id].document.addEventListener( 'mousemove',  mouseFunc2, false );
			}else{
				frames[iframe_id].frames[sub_iframe_id].document.attachEvent( 'onmousemove', mouseFunc2 );
			}
		}
	}else{
		if ( navigator.userAgent.search( 'MSIE' ) == -1 ){
			frames[iframe_id].frames[sub_iframe_id].frames[third_iframe_id].document.addEventListener( 'mousemove',  mouseFunc3, false );
		}else{
			frames[iframe_id].frames[sub_iframe_id].frames[third_iframe_id].document.attachEvent( 'onmousemove', mouseFunc3 );
		}
	}
}
//本页面和子页面的鼠标坐标捕捉事件
function doMouse(){
	document.onmousemove = mouseFunc;	//本页面捕捉鼠标
	/*
	//iframe子页面捕捉鼠标
	if ( navigator.userAgent.search( 'MSIE' ) == -1 ){
	  frames[0].document.addEventListener( 'mousemove',  mouseFunc1, false );
	}else{
	  frames[0].document.attachEvent( 'onmousemove', mouseFunc1 );
	}
	*/
}
//捕捉鼠标的window.event方法
function mouseFunc(ev){
    ev = ev || window.event;
    mousePos = mousePosition(ev);
	mouseAngle(mousePos);
	doMouseMenu(mousePos.x,mousePos.y);
}
//子页面的鼠标捕捉
var mouseFunc1 = function( evt ){
    var mousePosTemp = mousePosition1(evt);
	parent.mousePos = mousePosTemp;
	mouseAngle(mousePosTemp);
	doMouseMenu(mousePosTemp.x,mousePosTemp.y);
};
//二级子页面的鼠标捕捉
var mouseFunc2 = function( evt ){
    var mousePosTemp = mousePosition2(evt);
    parent.parent.mousePos = mousePosTemp;
	mouseAngle(mousePosTemp);
	doMouseMenu(mousePosTemp.x,mousePosTemp.y);
};
//三级子页面的鼠标捕捉
var mouseFunc3 = function( evt ){
    var mousePosTemp = mousePosition3(evt);
    parent.parent.parent.mousePos = mousePosTemp;
	mouseAngle(mousePosTemp);
	doMouseMenu(mousePosTemp.x,mousePosTemp.y);
};
//获取本页面的鼠标坐标，返回对象
function mousePosition(ev){
	if(ev.pageX || ev.pageY){
		return {
			x:ev.pageX,
			y:ev.pageY
		};
	}else{
		return {
			x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,
			y:ev.clientY + document.body.scrollTop  - document.body.clientTop
		}
	}
}
//获取子页面的鼠标坐标，返回对象;
function mousePosition1(ev){
	if(ev.pageX || ev.pageY){
		return {
			x:ev.pageX + window.frames[0].mozInnerScreenX-window.mozInnerScreenX,
			y:ev.pageY + window.frames[0].mozInnerScreenY-window.mozInnerScreenY
		};
	}else{
		return {
			x:ev.clientX + document.body.scrollLeft - document.body.clientLeft + window.frames[0].mozInnerScreenX-window.mozInnerScreenX,
			y:ev.clientY + document.body.scrollTop  - document.body.clientTop + window.frames[0].mozInnerScreenY-window.mozInnerScreenY
		}
	}
}
//获取二级子页面的鼠标坐标，返回对象;
function mousePosition2(ev){
	if(ev.pageX || ev.pageY){
		return {
			x:ev.pageX + window.frames[0].frames[0].mozInnerScreenX-window.mozInnerScreenX,
			y:ev.pageY + window.frames[0].frames[0].mozInnerScreenY-window.mozInnerScreenY
		};
	}
	return {
		x:ev.clientX + document.body.scrollLeft - document.body.clientLeft + window.frames[0].frames[0].mozInnerScreenX-window.mozInnerScreenX,
		y:ev.clientY + document.body.scrollTop  - document.body.clientTop + window.frames[0].frames[0].mozInnerScreenY-window.mozInnerScreenY
	}; 
}
//获取三级子页面的鼠标坐标，返回对象;
function mousePosition3(ev){
	if(ev.pageX || ev.pageY){
		return {
			x:ev.pageX + window.frames[0].frames[0].frames[0].mozInnerScreenX-window.mozInnerScreenX,
			y:ev.pageY + window.frames[0].frames[0].frames[0].mozInnerScreenY-window.mozInnerScreenY
		};
	}
	return {
		x:ev.clientX + document.body.scrollLeft - document.body.clientLeft + window.frames[0].frames[0].frames[0].mozInnerScreenX-window.mozInnerScreenX,
		y:ev.clientY + document.body.scrollTop  - document.body.clientTop + window.frames[0].frames[0].frames[0].mozInnerScreenY-window.mozInnerScreenY
	}; 
}
//根据鼠标坐标旋转
function mouseAngle(mousePos){
	//document.getElementById("x_p").innerHTML = angle;
	if(isIE){
		//如果是IE，使用滤镜来旋转
		//rotate("div_logo_light",angle-53);
	}else{
		//如果是FF,使用jquery_rotate来旋转
		var angle = Math.atan2(mousePos.y-15, mousePos.x-_PAGEWIDTH/2)*180/Math.PI;
		document.getElementById("div_logo_light").style.left = parseInt(_PAGEWIDTH/2-40)+"px";
		document.getElementById("div_logo_light").style.top = "-25px";
		$('#image_logo_light').rotate(angle-53);
	}
}
function degToRad(x) { return ( x/(360/(2*Math.PI)) ); } 
function radToDeg(x) { return ( x*(360/(2*Math.PI)) ); } 
//光点跟随鼠标旋转
function rotate(name, angle){
	var rad = degToRad(angle); 
	costheta = Math.cos(rad); 
	sintheta = Math.sin(rad); 
	var el = document.getElementById(name); 
	if(el) { 
		el.filters.item("DXImageTransform.Microsoft.Matrix").M11 = costheta; 
		el.filters.item("DXImageTransform.Microsoft.Matrix").M12 = -sintheta; 
		el.filters.item("DXImageTransform.Microsoft.Matrix").M21 = sintheta; 
		el.filters.item("DXImageTransform.Microsoft.Matrix").M22 = costheta; 
		el.style.left = _PAGEWIDTH/2 - (el.offsetWidth/2); 
		el.style.top = 15 - (el.offsetHeight/2); 
	} 
} 

