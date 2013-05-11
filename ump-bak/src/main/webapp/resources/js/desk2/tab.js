/**
 *	Tab相关变量与方法 
 */
var OPENED_TABS_ID = [];
var ORIGINAL_TAB_ID = null;
var ACTIVE_TAB_ID = null; //现在所处的div_tab的序号
var IS_DIRTY_FUNC = {};
var div_tab_now=0;	
var delScroll;
var nheight =  $(window).height();	//iframe打开的默认高度
var mailheight = "1636px";	//mail的高度
var oheight ;
var isFirst = true;
var oTime = null;
var nowMenuId = "homepage";			//首页的id

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
		if(isFirst){
			oheight = $("#bodyLoad").height();
			isFirst = false;
		}
		// already opend
		if(OPENED_TABS_ID.contains(menuId)){
			var event = {data:{tabId:menuId,src:menuURl}};
			TabUtils.showTab(event);
			return;
		}
	
		// open too many tabs		
		if(OPENED_TABS_ID.length >= 10){
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
		$(divNewTab).html("<div type='pageDiv' class='div_right_tab'><div class='tab_title' title='"+menuName+"'>" + menuName + 
				"</div><span style='display:none' class='close_hover'><img src='"+_ctx+"/resources/images/desk2/close.png' /></span></div>");
		
		oDivTab.appendChild(divNewTab);
		$("#"+menuId+"_tab div[type='pageDiv']").bind('click', {tabId:menuId}, TabUtils.showTab);
		$("#"+menuId+"_tab div[type='pageDiv']").bind('dblclick', {tabId:menuId}, TabUtils.closeTab);
		$("#"+menuId+"_tab span[class='close_hover']").bind('click', {tabId:menuId}, TabUtils.closeTab);
		// new frame
		var oDivFrame = $('#divFrame')[0];
		if($('#'+menuId + '_frame').length>0){
			oDivFrame.appendChild($('#'+menuId + '_frame')[0]);
		}else{
			var oNewFrame = document.createElement('iframe');
			var jNewFrame = $(oNewFrame);
			jNewFrame.attr('id', menuId + '_frame');
			jNewFrame.css('display','block');
			jNewFrame.css('width',$(".div_right_title").width()-9);
			//jNewFrame.css('width','100%');
			//jNewFrame.css('marginLeft','100px');
			//jNewFrame.css('marginRight','8px');
			//为取消内页滚动条而设置当前body高度
			var h = window.screen.availHeight-80;
			if(menuId == "206"){
				jNewFrame.css('height', mailheight);
			}else{
				jNewFrame.css('height', nheight);
			}
			
			jNewFrame.css('border', '0');
			jNewFrame.css('overflow', 'hidden');
			jNewFrame.attr('scroll', 'no');
			oNewFrame.setAttribute('frameborder', '0', 0);
			oDivFrame.appendChild(oNewFrame);
		}
		
		var event = {data:{tabId:menuId, src:menuURl}};
		nowMenuId = menuId;
		TabUtils.showTab(event);
		OPENED_TABS_ID.push(menuId);
	},
	showTab : function (event){
		var context = event.data;
		var _tabId = context.tabId;
		var _frameId = _tabId + '_frame';
		var _src = context.src;
		
		if(_tabId == "homepage"){
			if(!IF_RIGHT_SHOW){
				rightControlBtnClick();
			}
		}else{
			if(IF_RIGHT_SHOW){
				rightControlBtnClick();
			}
		}
		
		$("#div_right_fixed").hide();
		$('#' + ACTIVE_TAB_ID+"_tab div[type='pageDiv']").removeClass("div_right_tab_selected").addClass("div_right_tab").find(".close_hover").hide();
		$('#' + ACTIVE_TAB_ID + '_frame').hide();
		$('#' + _tabId+"_tab div[type='pageDiv']").removeClass("div_right_tab").addClass("div_right_tab_selected").find(".close_hover").show();
		$('#' + _tabId+'_frame').show();

		ORIGINAL_TAB_ID = ACTIVE_TAB_ID;
		ACTIVE_TAB_ID = _tabId;
		if(_src){
			$('#' + _frameId).attr('src', _src);
		}
		if("homepage"==_tabId){
			//如果是首页，刷新首页信息
			$("#bodyLoad").css("overflow-y","auto");
			$("#bodyLoad").height(oheight);
			refreshHome();
			$("#div_right_fixed").show();
		}else if("206" == _tabId){
			//邮件，固定高度
			$('#' + _tabId+'_frame').height(mailheight);
			$("#bodyLoad").height(mailheight);
			$("#div_left_b").height(mailheight);
		}else{
			//首页滚动条的高度随着iframe的高度切换而改变
			var toHeight = 0;
			try{
				toHeight = $('#' + _tabId+'_frame').height();
				if(toHeight<nheight){
					toHeight = nheight;
				}
			}catch(e){}
			$('#' + _tabId+'_frame').height(toHeight);
			$("#bodyLoad").height(toHeight+50);
			$("#div_left_b").height(toHeight+50);
		}
		$('#' + _tabId+'_frame').load(function(){
			$(this).contents().find("body").each(function(){
					$(this).click(function(){
						$(window.parent.document).find("#addressBookList").hide();
						$(window.parent.document).find("#divXialaMenu").find("[isCheck='true']").css("background-color","").attr("isCheck","false");
					})
					;
			});
		});
		nowMenuId = _tabId;
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
	},getTabFrame:function(event){
		var context = event.data;
		var _tabId = context.tabId;
		var _frameId = _tabId + '_frame';
		var currentTab = $('#' + _tabId);
		var currentFrame = $('#' + _frameId);
		if(context.typeCd){
			return currentFrame;
		}else{
			return currentTab;
		}
	} 
};