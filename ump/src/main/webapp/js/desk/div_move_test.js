var _MOVEFREQUENCY = 10;	//网页刷新频率，不建议值太小
var _MOVESPEED = 10;	//运动速度，值越大越慢
var arr_movedivobj = new Array();	//div的运动对象组成的数组

//注册一个新的moveDivObj,设置当前状态为指定的状态对象
//type含义:start：开始状态；default：默认展开状态；min：最小化的状态；max：最大化的状态
function setMoveDiv(div_id,type_name,left,top,width,height){
	var moveDivObj = findMoveDivObj(div_id);
	var div_property = getMoveDivProperty(div_id,left,top,width,height);
	for(var i=0;i<moveDivObj.arr_divProperty.length;i++){
		if(moveDivObj.arr_divProperty[i].typeName == type_name){
			moveDivObj.arr_divProperty[i].divProperty = div_property;
			return true;
		}
	}
	moveDivObj.arr_divProperty.push({typeName:type_name,divProperty:div_property});
	return true;
}
//获取一个DivProperty对象
function getMoveDivProperty(div_id,left,top,width,height){
	if(null==left){
		left = getObjLeft(div_id);
	}
	if(null==top){
		top = getObjTop(div_id);
	}
	if(null==width){
		width = getObjWidth(div_id);
	}
	if(null==height){
		height = getObjHeight(div_id);
	}
	return new DivProperty(left,top,width,height);
}
//根据id获取movedivobj对象
function findMoveDivObj(div_id){
	for(var i=0;i<arr_movedivobj.length;i++){
		if(arr_movedivobj[i].id == div_id){
			return arr_movedivobj[i];
		}
	}
	var newMovedivobj = new MoveDivObj(div_id);
	arr_movedivobj.push(newMovedivobj);
	return newMovedivobj;
}
//根据movedivobj的id和typeName获取moveDivProperty对象
function findMoveDivProperty(moveDivObj,type_name){
	for(var i=0;i<moveDivObj.arr_divProperty.length;i++){
		if(moveDivObj.arr_divProperty[i].typeName == type_name){
			return moveDivObj.arr_divProperty[i].divProperty;
		}
	}
}
//div的运动对象
function MoveDivObj(id){
	this.id = id;
	this.arr_divProperty = new Array();
	var intervalobjpos = null;
	var intervalobjpossub = null;
	var intervalobjsize = null;
	var intervalobjsizesub = null;
}
//div的位置对象
function DivProperty(left,top,width,height){
	this.left = left;
	this.top = top;
	this.width = width;
	this.height = height;
}
//movediv移动到指定模式，包含移动和缩放
//func_after 移动结束后运行的函数名
//sub_div_id 跟随内容的div 名，只控制height值
//sub_dis 跟随内容的height的差值
function divMove(div_id,type_name,func_after,sub_div_id,sub_dis){
	var movedivobj = findMoveDivObj(div_id);
	var divProperty = findMoveDivProperty(movedivobj,type_name);
	if(null!=divProperty){
		divGotoPos(div_id,divProperty.left,divProperty.top,func_after,sub_div_id,sub_dis);
		divGotoSize(div_id,divProperty.width,divProperty.height,func_after,sub_div_id,sub_dis);
	}
}
//移动div的方法1
function divGotoPos(div_id,targetX,targetY,func2){
	var movedivobj = findMoveDivObj(div_id);
	movedivobj.intervalobjpos = window.setInterval(function(){gotoPos(movedivobj,targetX,targetY,func2)},_MOVEFREQUENCY);
}
//移动div的方法2
function gotoPos(movedivobj,targetX,targetY,func_after){
	var div_id = movedivobj.id;
	var disx = targetX-getObjLeft(div_id);
	var disy = targetY-getObjTop(div_id);
	setObjLeft(div_id,getObjLeft(div_id)+parseInt(disx/_MOVESPEED));
	setObjTop(div_id,getObjTop(div_id)+parseInt(disy/_MOVESPEED));
	if(Math.abs(disx)>Math.abs(disy)){
		if(Math.abs(disy)<_MOVESPEED){
			if(getObjTop(div_id)<targetY){
				setObjTop(div_id,getObjTop(div_id)+1);
			}else if(getObjTop(div_id)>targetY){
				setObjTop(div_id,getObjTop(div_id)-1);
			}
		}
		if(Math.abs(disx)<_MOVESPEED){
			movedivobj.intervalobjpossub = setInterval(function(){gotoPosSub(movedivobj,targetX,targetY,func_after)},_MOVEFREQUENCY);
			window.clearInterval(movedivobj.intervalobjpos);
			movedivobj.intervalobjpos = null;
		}
	}else{
		if(Math.abs(disx)<_MOVESPEED){
			if(getObjLeft(div_id)<targetX){
				setObjLeft(div_id,getObjLeft(div_id)+1);
			}else if(getObjLeft(div_id)>targetX){
				setObjLeft(div_id,getObjLeft(div_id)-1);
			}
		}
		if(Math.abs(disy)<_MOVESPEED){
			movedivobj.intervalobjpossub = setInterval(function(){gotoPosSub(movedivobj,targetX,targetY,func_after)},_MOVEFREQUENCY);
			window.clearInterval(movedivobj.intervalobjpos);
			movedivobj.intervalobjpos = null;
		}
	}
}
//移动div的方法3
function gotoPosSub(movedivobj,targetX,targetY,func_after){
	var div_id = movedivobj.id;
	if(getObjLeft(div_id)<targetX){
		setObjLeft(div_id,getObjLeft(div_id)+1);
		if(getObjTop(div_id)<targetY){
			setObjTop(div_id,getObjTop(div_id)+1);
		}else if(getObjTop(div_id)>targetY){
			setObjTop(div_id,getObjTop(div_id)-1);
		}
	}else if(getObjLeft(div_id)>targetX){
		setObjLeft(div_id,getObjLeft(div_id)-1);
		if(getObjTop(div_id)<targetY){
			setObjTop(div_id,getObjTop(div_id)+1);
		}else if(getObjTop(div_id)>targetY){
			setObjTop(div_id,getObjTop(div_id)-1);
		}
	}else{
		if(getObjTop(div_id)<targetY){
			setObjTop(div_id,getObjTop(div_id)+1);
		}else if(getObjTop(div_id)>targetY){
			setObjTop(div_id,getObjTop(div_id)-1);
		}else{
			//alert(0+"="+movedivobj.intervalobjsizesub);
			try{
				if(null==movedivobj.intervalobjsize && null==movedivobj.intervalobjsizesub){
					eval(func_after);
				}
			}catch(e){}
			window.clearInterval(movedivobj.intervalobjpossub);
			movedivobj.intervalobjpossub = null;
		}
	}
}

//缩放div的方法1
function divGotoSize(div_id,targetW,targetH,func_after,sub_div_id,sub_dis){
	var movedivobj = findMoveDivObj(div_id);
	movedivobj.intervalobjsize = setInterval(function(){gotoSize(movedivobj,targetW,targetH,func_after,sub_div_id,sub_dis)},_MOVEFREQUENCY);
}
//缩放div的方法2
function gotoSize(movedivobj,targetW,targetH,func_after,sub_div_id,sub_dis){
	var div_id = movedivobj.id;
	var disw = targetW-getObjWidth(div_id);
	var dish = targetH-getObjHeight(div_id);
	setObjWidth(div_id,getObjWidth(div_id)+parseInt(disw/_MOVESPEED));
	setObjHeight(div_id,getObjHeight(div_id)+parseInt(dish/_MOVESPEED),sub_div_id,sub_dis);
	if(Math.abs(disw)>Math.abs(dish)){
		if(Math.abs(dish)<_MOVESPEED){
			if(getObjHeight(div_id)<targetH){
				setObjHeight(div_id,getObjHeight(div_id)+1,sub_div_id,sub_dis);
			}else if(getObjHeight(div_id)>targetH){
				setObjHeight(div_id,getObjHeight(div_id)-1,sub_div_id,sub_dis);
			}
		}
		if(Math.abs(disw)<_MOVESPEED){
			movedivobj.intervalobjsizesub = setInterval(function(){gotoSizeSub(movedivobj,targetW,targetH,func_after,sub_div_id,sub_dis)},_MOVEFREQUENCY);
			window.clearInterval(movedivobj.intervalobjsize);
			movedivobj.intervalobjsize = null;
		}
	}else{
		if(Math.abs(disw)<_MOVESPEED){
			if(getObjWidth(div_id)<targetW){
				setObjWidth(div_id,getObjWidth(div_id)+1);
			}else if(getObjWidth(div_id)>targetW){
				setObjWidth(div_id,getObjWidth(div_id)-1);
			}
		}
		if(Math.abs(dish)<_MOVESPEED){
			movedivobj.intervalobjsizesub = setInterval(function(){gotoSizeSub(movedivobj,targetW,targetH,func_after,sub_div_id,sub_dis)},_MOVEFREQUENCY);
			window.clearInterval(movedivobj.intervalobjsize);
			movedivobj.intervalobjsize = null;
		}
	}
}
//缩放div的方法3
function gotoSizeSub(movedivobj,targetW,targetH,func_after,sub_div_id,sub_dis){
	var div_id = movedivobj.id;
	if(getObjWidth(div_id)<targetW){
		setObjWidth(div_id,getObjWidth(div_id)+1);
		if(getObjHeight(div_id)<targetH){
			setObjHeight(div_id,getObjHeight(div_id)+1,sub_div_id,sub_dis);
		}else if(getObjHeight(div_id)>targetH){
			setObjHeight(div_id,getObjHeight(div_id)-1,sub_div_id,sub_dis);
		}
	}else if(getObjWidth(div_id)>targetW){
		setObjWidth(div_id,getObjWidth(div_id)-1);
		if(getObjHeight(div_id)<targetH){
			setObjHeight(div_id,getObjHeight(div_id)+1,sub_div_id,sub_dis);
		}else if(getObjHeight(div_id)>targetH){
			setObjHeight(div_id,getObjHeight(div_id)-1,sub_div_id,sub_dis);
		}
	}else{
		if(getObjHeight(div_id)<targetH){
			setObjHeight(div_id,getObjHeight(div_id)+1,sub_div_id,sub_dis);
		}else if(getObjHeight(div_id)>targetH){
			setObjHeight(div_id,getObjHeight(div_id)-1,sub_div_id,sub_dis);
		}else{
			//alert(1+"="+movedivobj.intervalobjpossub);
			try{
				if(null==movedivobj.intervalobjpos && null==movedivobj.intervalobjpossub){
					eval(func_after);
				}
			}catch(e){}
			window.clearInterval(movedivobj.intervalobjsizesub);
			movedivobj.intervalobjsizesub = null;
		}
	}
}

function getObjLeft(obj_id){
	var x_str = document.getElementById(obj_id).style.left;
	return Number(x_str.substring(0,x_str.length-2));
}
function setObjLeft(obj_id,value){
	document.getElementById(obj_id).style.left = value+"px";
}
function getObjTop(obj_id){
	var x_str = document.getElementById(obj_id).style.top;
	return Number(x_str.substring(0,x_str.length-2));
}
function setObjTop(obj_id,value){
	document.getElementById(obj_id).style.top = value+"px";
}
function getObjWidth(obj_id){
	var x_str = document.getElementById(obj_id).style.width;
	return Number(x_str.substring(0,x_str.length-2));
}
function setObjWidth(obj_id,value){
	document.getElementById(obj_id).style.width = value+"px";
}
function getObjHeight(obj_id){
	var x_str = document.getElementById(obj_id).style.height;
	return Number(x_str.substring(0,x_str.length-2));
}
function setObjHeight(obj_id,value,sub_div_id,sub_dis){
	document.getElementById(obj_id).style.height = value+"px";
	try{
		document.getElementById(sub_div_id).style.height = parseInt(value-sub_dis)+"px";
	}catch(e){}
}
