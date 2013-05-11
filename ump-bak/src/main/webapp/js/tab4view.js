/**
 *	Tab相关变量与方法 
 */
var OPENED_TABS_ID = [];
var ORIGINAL_TAB_ID = null;
var ACTIVE_TAB_ID = null;
var IS_DIRTY_FUNC = {};
Array.prototype.indexOf = function(o) {
    for (var i=0; i<this.length; i++) if (this[i]==o) return i;
    return -1;
};
	
Array.prototype.contains = function(o) {
    for (var i=0; i<this.length; i++) if (this[i]==o) return true;
    return false;
};

Array.prototype.del = function(n)  {
    if (n<0) return this;
    return this.slice(0,n).concat(this.slice(n+1,this.length));
};
var TabUtils = {
	newTab : function (menuId, menuName, menuURl, closeAble){
		// already opend
		if(OPENED_TABS_ID.contains(menuId)){
			var event = {data:{tabId:menuId}};
			TabUtils.showTab(event);
			return;
		}
	
		// open too many tabs		
		//if(OPENED_TABS_ID.length >= 7){
		//	return;
		//}
		
		// new tab
		var oDivTab = document.getElementById('divTab');
		var divNewTab = document.createElement('div');
		divNewTab.id = menuId;
		$(divNewTab).attr('class','tab-title-up');
		$(divNewTab).attr('style','display:block;');
		
		var divLeft = document.createElement('div');
		divLeft.innerHTML = '&nbsp;';
		$(divLeft).attr('class', 'tab-title-left');
		
		var divCenter = document.createElement('div');
		divCenter.innerHTML = menuName;
		$(divCenter).attr('class','tab-title-center');
		$(divCenter).bind('click', {tabId:menuId}, TabUtils.showTab);
		
		var divRight = document.createElement('div');
		divRight.innerHTML = '&nbsp;';
		$(divRight).attr('class', 'tab-title-right');
		
		divNewTab.appendChild(divLeft);
		divNewTab.appendChild(divCenter);
		divNewTab.appendChild(divRight);
		
		if(!(closeAble==false)){
			var divClose = document.createElement('div');
			$(divClose).attr('class', 'tab-close');
			$(divClose).bind('click', {tabId:menuId}, TabUtils.closeTab);
			divNewTab.appendChild(divClose);
		}
		
		oDivTab.appendChild(divNewTab);		
	
		// new frame
		var oDivFrame = $('#divFrame')[0];
		
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
		
		var event = {data:{tabId:menuId,src:menuURl}};
		
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
		$('#' + ACTIVE_TAB_ID).attr('class','tab-title');
		$('#' + ACTIVE_TAB_ID + '_frame').css('display','none');
		
		// show tab
		$('#' + _tabId).attr('class','tab-title-up');
		$('#' + _frameId).css('display', 'block');
		
		// update global variable
		ORIGINAL_TAB_ID = ACTIVE_TAB_ID;
		ACTIVE_TAB_ID = _tabId;
		if(_src){
			$('#' + _frameId).attr('src', _src);
		}
		//TabUtils.setCwinHeight(_frameId);
	},
	closeTab : function(event){	
		var context = event.data;
		var _tabId = context.tabId;
		var _frameId = _tabId + '_frame';
		
		var currentTab = $('#' + _tabId);
		var currentFrame = $('#' + _frameId);
		
		// 移除tab
		currentTab[0].parentNode.removeChild(currentTab[0]);
		// 移除frame
		currentFrame[0].parentNode.removeChild(currentFrame[0]);
		
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
	},
	setCwinHeight : function(id) {
		var cwin = $('#' + id)[0];
		if (document.all){//IE
			alert(cwin.document.body.scrollHeight);
			$("#divFrame").height(cwin.document.body.scrollHeight);
		}else{
			cwin.height = cwin.contentDocument.body.offsetHeight;
		}
	}
};