<%@ page contentType="text/html;charset=UTF-8" %>
<script language="javascript">
var endHeight = 0;
var windowWidth = document.documentElement.clientWidth;
var windowHeight = document.documentElement.clientHeight;
/*
	$(function(){
		var menuId = top.nowMenuId;
		if(null!=menuId && undefined !=menuId){
			var toHeight = $(window).height();
			var iHeight = $("#"+menuId+"_frame",top.document).height();
			if(null!=toHeight && null!= iHeight && toHeight>iHeight){
				$("#"+menuId+"_frame",top.document).height(toHeight);
				$("#div_home_b",top.document).height(toHeight+440);
				$("#div_left_b",top.document).height(toHeight+440);
			}
		}
	});
	$(window).resize(function(){
		var menuId = top.nowMenuId;
		if(null!=menuId && undefined !=menuId){
			var toHeight = $(window).height();
			var iHeight = $("#"+menuId+"_frame",top.document).height();
			if(null!=toHeight && null!= iHeight && toHeight>iHeight){
				$("#"+menuId+"_frame",top.document).height(toHeight);
				$("#div_home_b",top.document).height(toHeight+440);
				$("#div_left_b",top.document).height(toHeight+440);
			}
		}
	});
	var ifScrolled = false;
	var ifScroll2 = false;
	$(window).scroll(function(){
		var menuId = top.nowMenuId;
		if(null!=menuId && undefined !=menuId){
			var toHeight = $(document).height();
			var iHeight = $("#"+menuId+"_frame",top.document).height();
			if(null!=toHeight && null!= iHeight && toHeight>iHeight){
				$("#"+menuId+"_frame",top.document).height(toHeight);
				$("#div_home_b",top.document).height(toHeight+440);
				$("#div_left_b",top.document).height(toHeight+440);
				if(!ifScroll2){
					try{cancelSchedule();}catch(e){}
					ifScroll2 == true;
				}
			}
			if(!ifScrolled){
				try{newSchedule();}catch(e){}
				$(window).scrollTop(0);
				ifScrolled = true;
			}
		}
	});
	*/

	function autoHeight(specialName){
		var oheight = $(window.parent.document).find("#bodyLoad").height();
		var menuId = top.nowMenuId;
		if(null!=menuId && undefined !=menuId){
			//获取iframe中包含滚动条整体body高度
			var o = $(window.parent.document).find('#' + menuId+'_frame').get(0);
			//alert($(window.parent.document).find('#' + menuId+'_frame').contents().find("body").scrollHeight);
			var ch = $(window.parent.document).find('#' + menuId+'_frame').get(0).contentDocument.body.scrollHeight;
			if(endHeight == 0){
				endHeight = ch;
			}
			
			else if(ch > endHeight){
				endHeight = ch;
			}
			$(window.parent.document).find("#bodyLoad").height(endHeight+50);
			$(window.parent.document).find("#div_left_b").height(endHeight+50);
			
			$(window.parent.document).find('#' + menuId+'_frame').contents().find("body").height(endHeight);
			$(window.parent.document).find('#' + menuId+'_frame').height(endHeight);
			if("res"==specialName){
				$("#divTreeP").height(endHeight-50);
				$("#leftPanel").height(endHeight-50);
			}
		}

	

	}

	function rePosition(obj){
		//取ymPrompt弹出窗口，全定位于屏幕中间
		var popupHeight = obj.height();
		var popupWidth = obj.width();
		//$(window.parent.document).find("#bodyLoad").scrollTop(0);
		//重新给弹出窗口定位到屏幕中间
		//windowHeight = $(window.parent.document).find("#bodyLoad").scrollTop(0);
		/*
		obj.css({
		    "position": "absolute",
		    "top": windowHeight/2-popupHeight/2,
		    "left": windowWidth/2-popupWidth/2
		});
		*/
		//$(window.parent.document).find("#bodyLoad").scrollTop(0);
		//alert($(window.parent.document).find("#bodyLoad").scrollTop());
		obj.focus();
	}

	
	
</script>