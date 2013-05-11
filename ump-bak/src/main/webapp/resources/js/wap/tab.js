/**
 *	Tab相关变量与方法 
 */
var OPENED_TABS_ID = [];
var ORIGINAL_TAB_ID = null;
var ACTIVE_TAB_ID = null; //现在所处的div_tab的序号
var IS_DIRTY_FUNC = {};
var div_tab_now=0;	
var arr_div_tab_id = new Array();	//由div_tab的id组成的数组，用来判断现在处于哪个div_tab上
arr_div_tab_id[0] = '"homepage_tab"';
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
		$("#"+menuId).children("div[type='pageDiv']").bind('click', {tabId:menuId}, TabUtils.showTab);
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
			if(confirm("过多的标签将影响性能，需要关闭最新的标签才能打开新标签。")){
				TabUtils.closeTab({data:{tabId:OPENED_TABS_ID[1]}});
			}else{
				return;
			}
		}
		
		// new tab
		var oDivTab = document.getElementById('divTab');
		var divNewTab = document.createElement('div');
		divNewTab.id = menuId+"_tab";
		$(divNewTab).html("<div type='pageDiv' class='tab_inactive'><div class='tab_title' title='"+menuName+"'>" + menuName + 
				"</div><div class='close_tab'></div></div><div style='float:left; width:5px; height:23px;'></div>");
		oDivTab.appendChild(divNewTab);
		$("#"+menuId+"_tab div[type='pageDiv']").bind('click', {tabId:menuId}, TabUtils.showTab);
		$("#"+menuId+"_tab div[type='pageDiv']").bind('dblclick', {tabId:menuId}, TabUtils.closeTab);
		$("#"+menuId+"_tab div[class='close_tab']").bind('click', {tabId:menuId}, TabUtils.closeTab);
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
			jNewFrame.css('height', '100%');
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
		// original actived tab
		$('#' + ACTIVE_TAB_ID+"_tab div[type='pageDiv']").removeClass("tab_active").addClass("tab_inactive");
		$('#' + ACTIVE_TAB_ID + '_frame').hide();
		// show tab
		$('#' + _tabId+"_tab div[type='pageDiv']").removeClass("tab_inactive").addClass("tab_active");
		$('#' + _tabId+'_frame').show();

		// update global variable
		ORIGINAL_TAB_ID = ACTIVE_TAB_ID;
		ACTIVE_TAB_ID = _tabId;
		if(_src){
			$('#' + _frameId).attr('src', _src);
		}
		if("homepage"==_tabId){
			//如果是首页，刷新首页信息
			refreshHome();
		}
		//TabUtils.setCwinHeight(_frameId);
	},
	closeTab : function(event){
		var context = event.data;
		var _tabId = context.tabId;
		var _frameId = _tabId + '_frame';
		
		var currentTab = $('#' + _tabId+"_tab");
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