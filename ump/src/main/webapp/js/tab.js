/**
 *	Tab相关变量与方法 
 */
var OPENED_TABS_ID = [];
var ORIGINAL_TAB_ID = null;
var ACTIVE_TAB_ID = null;
var IS_DIRTY_FUNC = {};
var div_tab_now=0;	//现在所处的div_tab的序号
var arr_div_tab_id = new Array();	//由div_tab的id组成的数组，用来判断现在处于哪个div_tab上
arr_div_tab_id[0] = 'homepage';
Array.prototype.indexOf = function(o) {
    for (var i=0; i<this.length; i++) if (this[i]==o) return i;
    return -1;
}
	
Array.prototype.contains = function(o) {
    for (var i=0; i<this.length; i++) if (this[i]==o) return true;
    return false;
} 

Array.prototype.del = function(n)  {
    if (n<0) return this;
    return this.slice(0,n).concat(this.slice(n+1,this.length));
}
var TabUtils = {
	/**
	 * 需要自己创建好tab的Div和iframe的Div
	 */
	initTab : function(menuId){
		ACTIVE_TAB_ID = menuId;
		OPENED_TABS_ID.push(menuId);
		$("#"+menuId).children("a").bind('click', {tabId:menuId}, TabUtils.showTab);
	},
	newTab : function (menuId, menuName, menuURl, closeAble){
		// already opend
		if(OPENED_TABS_ID.contains(menuId)){
			var event = {data:{tabId:menuId,src:menuURl}};
			TabUtils.showTab(event);
			return;
		}
	
		// open too many tabs		
		if(OPENED_TABS_ID.length >= 9){
			if (!confirm("打开太多标签也会影响性能，确认继续吗？")) {
				return;
			}
		}
		
		// new tab
		var oDivTab = document.getElementById('divTab');
		var divNewTab = document.createElement('div');
		divNewTab.id = menuId;
		$(divNewTab).addClass("tab").addClass("tab_active").html("<a href='#' onfocus='this.blur();'>" + menuName + "</a>" +
				"<div id='div_tab_close_"+menuId+"' class='close_tab' href='#'></div>" +
						"<div style='float:left; width:1px;'><img src='../images/desk2/menu_top_line.gif'/></div>");
		$(divNewTab).children("a").bind('click', {tabId:menuId}, TabUtils.showTab);
		$(divNewTab).children("a").bind('dblclick', {tabId:menuId}, TabUtils.closeTab);
		$(divNewTab).bind('click', {tabId:menuId}, function(){
			document.getElementById("div_tab_close_"+menuId).style.visibility = "visible";
		});
		$(divNewTab).bind('mouseleave', {tabId:menuId}, function(){
			if(arr_div_tab_id[div_tab_now]!=menuId){
				document.getElementById("div_tab_close_"+menuId).style.visibility = "hidden";
			}
		});
		$(divNewTab).children("div.close_tab").bind('click', {tabId:menuId}, TabUtils.closeTab);
		oDivTab.appendChild(divNewTab);
		
		var divNewTab2 = document.createElement('div');

		try{
			//底下的绿色条也随之移动
			arr_div_tab_id[arr_div_tab_id.length] = menuId;
			div_tab_now = arr_div_tab_id.length-1;
			var marginLeft_number = div_tab_now*81;
			if(document.getElementById("homepageSub").style.marginLeft != marginLeft_number+"px"){
				$("#homepageSub").animate({"marginLeft":marginLeft_number+"px"},200);
			}
		}catch(e){}
		
		// new frame
		var oDivFrame = $('#divFrame')[0];
		if($('#'+menuId + '_frame').length>0){
			oDivFrame.appendChild($('#'+menuId + '_frame')[0]);
		}else{
			var oNewFrame = document.createElement('iframe');
			var jNewFrame = $(oNewFrame);
			jNewFrame.attr('id', menuId + '_frame');
			jNewFrame.css('display','block');
			jNewFrame.css('width', '100%');
			if(new RegExp("pdf-.*").test(menuId)){
				jNewFrame.css('height', '90%');
			}else{
				jNewFrame.css('height', '100%');
			}
			jNewFrame.css('border', '0');
			jNewFrame.attr('scroll', 'no');
			oNewFrame.setAttribute('frameborder', '0', 0);
			oDivFrame.appendChild(oNewFrame);
		}
		
		var event = {data:{tabId:menuId, src:menuURl}};
		TabUtils.showTab(event);

		// update global variable
		OPENED_TABS_ID.push(menuId);
	},
	showTab : function (event){
		var context = event.data;
		var _tabId = context.tabId;
		var _frameId = _tabId + '_frame';
		var _src = context.src;
		
		try{
			//一旦标签页也有变化，改变底下一条的颜色
			if("#e6e6e6"==document.getElementById("homepageSub_bai").style.backgroundColor){
				document.getElementById("homepageSub_bai").style.backgroundColor = "#FFF";
			}
		}catch(e){}
		
		// original actived tab
		$('#' + ACTIVE_TAB_ID).removeClass("tab_active").addClass("tab_inactive");
		$('#' + ACTIVE_TAB_ID + '_frame').hide();
		// show tab
		$('#' + _tabId).removeClass("tab_inactive").addClass("tab_active");
		$('#' + _tabId+'_frame').show();
		
		// update global variable
		ORIGINAL_TAB_ID = ACTIVE_TAB_ID;
		ACTIVE_TAB_ID = _tabId;
		if(_src){
			$('#' + _frameId).attr('src', _src);
		}
		//TabUtils.setCwinHeight(_frameId);

		try{
			//桌面左上角的角变色
			if("homepage"!=_tabId){
				document.getElementById("mid_caidan_jiao_hui").style.display = "block";
				document.getElementById("mid_caidan_jiao_bai").style.display = "none";
			}else{
				document.getElementById("mid_caidan_jiao_hui").style.display = "none";
				document.getElementById("mid_caidan_jiao_bai").style.display = "block";
			}
		}catch(e){}
		
		try{
			//显示关闭按钮
			document.getElementById("div_tab_close_"+_tabId).style.visibility = "visible";
		}catch(e){}
		
		try{
			//底下的绿色条也随之移动
			for(var i=0;i<arr_div_tab_id.length;i++){
				if(arr_div_tab_id[i] == _tabId){
					div_tab_now = i;
				}else{
					try{
						//不显示关闭按钮
						if(document.getElementById("div_tab_close_"+arr_div_tab_id[i]).style.visibility == "visible"){
							document.getElementById("div_tab_close_"+arr_div_tab_id[i]).style.visibility = "hidden";
						}
					}catch(e){}
				}
			}
			var marginLeft_number = div_tab_now*81;
			if(document.getElementById("homepageSub").style.marginLeft != marginLeft_number+"px"){
				$("#homepageSub").animate({"marginLeft":marginLeft_number+"px"},200);
			}
		}catch(e){}
		try{
			if(arr_div_tab_id.length>1){
				$("#homepage_shu").show();
			}else{
				//如果只剩下一个桌面在显示，则右边的竖线不显示，并且底色是透明的
				$("#homepage_shu").hide();
				document.getElementById("mid_caidan_jiao_hui").style.display = "block";
				document.getElementById("mid_caidan_jiao_bai").style.display = "none";
				$('#homepage').removeClass("tab_active").addClass("tab_inactive");
			}
		}catch(e){}
	},
	closeTab : function(event){
		var context = event.data;
		var _tabId = context.tabId;
		var _frameId = _tabId + '_frame';
		
		var currentTab = $('#' + _tabId);
		var currentFrame = $('#' + _frameId);
		if(new RegExp("pdf-.*").test(_tabId)){
			currentTab.remove();
			currentFrame.hide();
		}else{
			// 移除tab
			currentTab[0].parentNode.removeChild(currentTab[0]);
			// 移除frame
			currentFrame[0].parentNode.removeChild(currentFrame[0]);
			
			//added by shixy 20100330 释放iframe占用的内存
			currentTab.remove();
			currentFrame.remove();
		}
		OPENED_TABS_ID = OPENED_TABS_ID.del(OPENED_TABS_ID.indexOf(_tabId));
		

		try{
			//底下的绿色条也随之移动
			for(var i=0;i<arr_div_tab_id.length;i++){
				if(arr_div_tab_id[i] == _tabId){
					arr_div_tab_id = arr_div_tab_id.slice(0,i).concat(arr_div_tab_id.slice(i+1,arr_div_tab_id.length));
					break;
				}
			}
			if(div_tab_now > arr_div_tab_id.length-1){
				div_tab_now = arr_div_tab_id.length-1;
			}
		}catch(e){}
		
		// 如果删除的是激活的tab，则需要激活前一个tab
		if(_tabId == ACTIVE_TAB_ID){
			var originalTabId = null;
			if(ORIGINAL_TAB_ID && OPENED_TABS_ID.contains(ORIGINAL_TAB_ID)){
				originalTabId = ORIGINAL_TAB_ID;
			} else if(OPENED_TABS_ID.length > 0){
				originalTabId = OPENED_TABS_ID[OPENED_TABS_ID.length-1];
			}
			if(originalTabId){
				var event = {data:{tabId:originalTabId}};
				TabUtils.showTab(event);
			}
		}
		
	}
};