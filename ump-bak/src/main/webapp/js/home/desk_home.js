var _PATH = _ctx;
var oriFuncMenuPlanContentHeight = null;
var oriFuncMenuCommonContentHeight = null;
var oriFuncMenuContactContentHeight = null;
//屏幕高度
var PAGE_SIZE;

//如果字符串超过指定长度，自动截断并用...代替后面的文本
function cutoffStr(str, num) {
	str = jQuery.trim(str);
	if(str.length <= num) {
		return str;
	} else {
		return str.substring(0, num) + "...";
	}
}
//打开一条公告
function openNotify(url, notifyId, isReaded) {
//	window.open(url);
	TabUtils.newTab("pdf-notify-read","查看公告",url,true);
	// 如果当前公告是未读，则往后台发一条消息设置为已读
	if (isReaded == "-1") {
		$.post(_PATH + "/oa/oa-notify!readNotify.action", {id : notifyId});
	}
}
//打开一条新的新闻或者公告时把new图标删掉
function removeNewImg(srcEle) {
	$(srcEle).siblings(".new_img").hide();
}
// 更新内部邮件记录
function refreshMails() {
	$.post(_PATH + "/desk/desk-email!email.action",{"deskHomePager.pageSize":PAGE_SIZE}, function(result) {
		$("#desk_front_email_div").html(result);
	});
} 
// 更新审批记录
function refreshJbpm() {
	$.post(_PATH + "/desk/jbpm-task.action", function(result) {
		$("#desk_front_jbpm_div").html(result);
	});
}
// 更新首页的公告列表
function refreshHomeNotifyList() {
	$.post(_PATH + "/desk/desk-notify!frontPageNotify.action",{"deskHomePager.pageSize":(PAGE_SIZE-3)>4?(PAGE_SIZE-3):4}, function(result) {
		$("#desk_front_notify_div").html(result);
	});
}
// 更新首页的新闻列表
function refreshHomeNewsList() {
	$.post(_PATH + "/desk/desk-news!frontPageNews.action",{"deskHomePager.pageSize":(PAGE_SIZE-3)>4?(PAGE_SIZE-3):4}, function(result) {
		$("#desk_front_news_div").html(result);
	});
}
// 从别的标签也切换到首页时更新整个首页的内容
function refreshWholeHome() {
	if (ACTIVE_TAB_ID=="homepage") {
		return;
	}
	try{
		document.getElementById("mid_caidan_jiao_hui").style.display = "none";
		document.getElementById("mid_caidan_jiao_bai").style.display = "block";
	}catch(e){}
	refreshHome();
}
function refreshHome(){
	refreshJbpm();
	refreshMails();
	refreshHomeNewsList();
	refreshHomeNotifyList();
}
var refreshNews = refreshHomeNewsList;
var jbpmMoudel={"travel":"出差审批","reimburse":"报销审批","ceomeeting":"总裁例会","specialtask":"专项任务","projectAcc":"项目"};
// 全屏填充整个中间区域
function showAll(url, type) {
	TabUtils.newTab(type,jbpmMoudel[type],url,true);
	return false;
}

function jumpPageTo(url, pageNo, targDivId) {
	var tempPageSize = PAGE_SIZE;
	if(targDivId == "desk_front_notify_div"||targDivId == "desk_front_news_div"){
		tempPageSize = (PAGE_SIZE-3)>4?(PAGE_SIZE-3):4;
	}
	if (parseInt(pageNo) > 0) {
		url = url + "?deskHomePager.pageNo=" + pageNo+"&deskHomePager.pageSize=" + tempPageSize;
	}
	$.post(url, function(result) {
		$("#" + targDivId).html(result);
	});
}
function showEmail(dom,id,emailBodyId){
	$(dom).find('.new_img').remove();
	var url = _ctx+"/oa/oa-email!main.action?oaEmailId="+id+"&id="+emailBodyId+"&boxId=5";
	TabUtils.newTab("email","邮件",url,true);
	//refreshMails();
}

function feedback(){
	var url = _ctx+"/oa/oa-email!main.action?sendEmailFlag=true&feedbackFlg=true";
	TabUtils.newTab("email","邮件",url,true);
}

// 如果菜单高度没超过外部容器高度，则回复布局
function revertFunc() {
	var oriMarTop = parseInt($(".func_container").css("margin-top"));
	if (oriMarTop != 0) {
		var innerHeight = parseInt($(".func_container").height());
		var containerHeight = parseInt($(".center_right_funcs").height());
		if (innerHeight <= containerHeight - 50) {
			isScrolling = true;
			$(".func_container").animate({marginTop : 0}, 200, function() {
				isScrolling = false;
			});
		}
	}
}
// 点击功能按钮顶部时隐藏或显示该模块的功能按钮
function bindFuncMenuHeaderEvents() {
	$("#func_menus_common_header").click(function() {
		var funcs = $("#func_menus_common_content");
		var head = $(this);
		if (funcs.css("display") == "none") {
			//head.html("").height(8).css("padding", "0");
			//head.height(8).css("padding", "0");
			$("#center_right_header_img").show();
			$("#center_right_header_text").hide();
			funcs.animate({height: oriFuncMenuCommonContentHeight}, 200, function() { 
				funcs.show();
			});
		} else {
			funcs.animate({height: 0}, 200, function() { 
				funcs.hide();
				//head.height(20).css("padding-top", "7px").css("padding-left", "10px");
				$("#center_right_header_img").hide();
				$("#center_right_header_text").show();
				//head.height(20).html("公共菜单").css("padding-top", "7px").css("padding-left", "10px");
				
				revertFunc();
			});
		}
	});
	$("#func_menus_contact_header").click(function() {
		var funcs = $("#func_menus_contact_content");
		var head = $(this);
		if (funcs.css("display") == "none") {
			$("#center_right_contact_img").show();
			$("#center_right_contact_text").hide();
			funcs.animate({height: oriFuncMenuContactContentHeight}, 200, function() { 
				funcs.show();
			});
		} else {
			funcs.animate({height: 0}, 200, function() { 
				funcs.hide();
				$("#center_right_contact_img").hide();
				$("#center_right_contact_text").show();
				revertFunc();
			});
		}
	});
	$("#func_menus_exam_header").click(function() {
		var funcs = $("#func_menus_exam_content");
		var head = $(this);
		if (funcs.css("display") == "none") {
			$("#center_right_exam_img").show();
			$("#center_right_exam_text").hide();
			funcs.animate({height: 88}, 200, function() { 
				funcs.show();
			});
		} else {
			funcs.animate({height: 0}, 200, function() { 
				funcs.hide();
				$("#center_right_exam_img").hide();
				$("#center_right_exam_text").show();
				
				revertFunc();
			});
		}
	});
	$("#func_menus_system_header").click(function() {
		var funcs = $("#func_menus_system_content");
		var head = $(this);
		if (funcs.css("display") == "none") {
			$("#center_right_system_img").show();
			$("#center_right_system_text").hide();
			funcs.animate({height: 88}, 200, function() { 
				funcs.show();
			});
		} else {
			funcs.animate({height: 0}, 200, function() { 
				funcs.hide();
				$("#center_right_system_img").hide();
				$("#center_right_system_text").show();
				
				revertFunc();
			});
		}
	});
	$("#func_menus_plan_header").click(function() {
		var funcs = $("#func_menus_plan_content");
		var head = $(this);
		if (funcs.css("display") == "none") {
			$("#center_right_plan_img").show();
			$("#center_right_plan_text").hide();
			funcs.animate({height: oriFuncMenuPlanContentHeight}, 200, function() { 
				funcs.show();
			});
		} else {
			funcs.animate({height: 0}, 200, function() { 
				funcs.hide();
				$("#center_right_plan_img").hide();
				$("#center_right_plan_text").show();
				
				revertFunc();
			});
		}
	});
	$("#func_menus_old_header").click(function() {
		var funcs = $("#func_menus_old_content");
		var head = $(this);
		if (funcs.css("display") == "none") {
			$("#center_right_old_img").show();
			$("#center_right_old_text").hide();
			funcs.animate({height: 86}, 200, function() { 
				funcs.show();
			});
		} else {
			funcs.animate({height: 0}, 200, function() { 
				funcs.hide();
				$("#center_right_old_img").hide();
				$("#center_right_old_text").show();
				revertFunc();
			});
		}
	});
	$("#func_menus_fins_header").click(function() {
		var funcs = $("#func_menus_fins_content");
		var head = $(this);
		if (funcs.css("display") == "none") {
			$("#center_right_fins_img").show();
			$("#center_right_fins_text").hide();
			funcs.animate({height: 86}, 200, function() { 
				funcs.show();
			});
		} else {
			funcs.animate({height: 0}, 200, function() { 
				funcs.hide();
				$("#center_right_fins_img").hide();
				$("#center_right_fins_text").show();
				revertFunc();
			});
		}
	});
	$("#func_menus_sup_header").click(function() {
		var funcs = $("#func_menus_sup_content");
		var head = $(this);
		if (funcs.css("display") == "none") {
			$("#center_right_sup_img").show();
			$("#center_right_sup_text").hide();
			funcs.animate({height: 86}, 200, function() { 
				funcs.show();
			});
		} else {
			funcs.animate({height: 0}, 200, function() { 
				funcs.hide();
				$("#center_right_sup_img").hide();
				$("#center_right_sup_text").show();
				revertFunc();
			});
		}
	});
	/*
	$(".func_menus_exam .header").click(function() {
		var funcs = $(this).next();
		var head = $(this);
		if (funcs.css("display") == "none") {
			head.html("").height(8).css("padding", "0");
			funcs.animate({height: 69}, 200, function() { 
				funcs.show();
			});
		} else {
			funcs.animate({height: 0}, 200, function() { 
				funcs.hide();
				head.height(19).html("审批菜单").css("padding-top", "7px").css("padding-left", "10px");
			});
		}
	});
	*/
}

// 切换通讯录/聊天
function showTab(activeElemId){
	//alert(activeElemId);
	switch(activeElemId){
		case "address_nav": 
			$("#left_address_panel_id").show();
			$("#left_talk_options_id").hide();
			$("#left_user_result").hide();
			if(document.getElementById("imTabSub").style.left != "0px"){
				$("#imTabSub").animate({"left":"0px"},200);
				//document.getElementById("talk_nav").style.borderRightWidth="0px";
				//$("#impageSub").width(98);
			}
			break;
		case "talk_nav":
			$("#left_address_panel_id").hide();
			$("#left_talk_options_id").show();
			$("#left_user_result").hide();
			if(document.getElementById("imTabSub").style.left != "101px"){
				$("#imTabSub").animate({"left":"101px"},200);
				//document.getElementById("talk_nav").style.borderRightWidth="0px";
				//$("#impageSub").width(100);
			}
			break;
		case "search_nav":
			$("#left_address_panel_id").hide();
			$("#left_talk_options_id").hide();
			$("#left_user_result").show();
			break;
		default:break;
	}
}

// 切换正在联系/最近联系
function showTalkTab(navId){
	switch(navId){
		case 'talk_talking_nav':
			 $("#talk_talking_div").show();
			 $("#talk_latest_div").hide();
		   	  break;
		case 'talk_group_nav':
			  break;
		case 'talk_latest_nav':
			 $("#talk_talking_div").hide();
			 $("#talk_latest_div").show();
			  break;
		default: ;
	}
}
/**
* 功能:OA搜索,搜索用户列表
* @param val: 模糊匹配 登录名,姓名,公司电话和手机号码
* @return 更新搜索结果
*/
var DEFAULT_SEARCH_TIP = '搜索用户...';
//按键时若存在默认字符,清空
function clearSearchInput(){
	var val = $.trim($("#searched_user").val());
	if( val == DEFAULT_SEARCH_TIP){
		$("#searched_user").val("");
		$("#searched_user").css({color:"#5A5A5A"});
	}
};
function resetSearchInput(){
	var val = $.trim($("#searched_user").val());
	if( val == ''){
		$("#searched_user").val(DEFAULT_SEARCH_TIP);
		$("#searched_user").css({color:"#E6E6E6"});
		var curTabId = $(".left_top_tabs div[class='active']").attr("id");
		showTab(curTabId);
	}
}

var searchFilterUserTime;
function searchUser(){
	if(searchFilterUserTime)clearTimeout(searchFilterUserTime);
	searchFilterUserTime = setTimeout(function(){
		var tabId = 'search_nav';
		var val = $.trim($("#searched_user").val());

		if( val == ""){
			var curTabId = $(".left_top_tabs div[class='active']").attr("id");
			//alert(curTabId);
			showTab(curTabId);
			return;
		}else{
			$("#searched_user").css({color:"#5A5A5A"});
			$("#left_user_result").addClass("waiting");
			$.post(_ctx+"/desk/desk!getUsersByFilter.action",{value:val},function(result){
				//alert(result);
				$("#left_user_result").html(result).removeClass("waiting").show();
				showTab(tabId);
			});
		}
	}, 300);
}

//清空输入框
function cleanTextArea(){
	$(".chatting_textarea").val("");
}
// 给滑动栏注册事件，点击时把功能按钮模块隐藏或者显示
function bindSlideEvents() {
	$("#slidebar").click(function() {
		var isHiden = $(".center_right_funcs").css("display") == "none" ? true : false;
		if (isHiden == false) {
			$(".center_right_funcs").hide();
			$(".center_right_btns").hide();
			$(".center_right").animate({width : 11}, 100, function() {
				$("#slidebar").addClass("slide_bar_left");
			});
			$(".center_left").animate({marginRight: 11}, 100);
		} else {
			$(this).addClass("slide_bar_right");
			$(".center_left").animate({marginRight: 160}, 100);
			$(".center_right").animate({width : 160}, 100, function() {
				$("#slidebar").removeClass("slide_bar_left");
				$("#slidebar").add("slide_bar_right");
				$(".center_right_funcs").fadeIn();
				$(".center_right_btns").fadeIn();
			});
		}
	});
	
	$("#slidebar").mouseover(function() {
		if ($(this).hasClass("slide_bar_left")) {
			return;
		}
		
		if (!$(this).hasClass("slide_bar_right")) {
			$(this).addClass("slide_bar_right");
		}
	});
	
	$("#slidebar").mouseout(function() {
		$(this).removeClass("slide_bar_right");
	});
}
// 给功能按钮模块里面的每个按钮注册时间，当点击时全屏显示
function bindFuncEvents() {
	$(".func_menu_btn").click(function(event) {
		event.preventDefault();
		if ($(this).attr("type") != "none") {
			TabUtils.newTab($(this).attr("type"), $(this).attr("menuname"), $(this).attr("href"), true);
		}
	});
}
// 给左下角聊天窗口里面的三角附加时间，点击隐藏/显示聊天记录
function bindChatSlideEvents() {
	$(".chatting_slider_hidecontent").click(function() {
		var isHidden = $(".notify_content").css("display");
		if (isHidden == "none") {
			$(".notify_content").show();
			$("#chatting_textarea").css("height", "32px");
			$(this).removeClass("chatting_slider_showcontent").addClass("chatting_slider_hidecontent");
		} else {
			$(".notify_content").hide();
			$("#chatting_textarea").css("height", "157px");
			$(this).removeClass("chatting_slider_hidecontent").addClass("chatting_slider_showcontent");
		}
	});
}

// 打开审批任务模块的条目
function openTask(statusCd,id,taskId,type){
	var url;
	
	switch (type) {
	case "ceomeeting" :
		url = _PATH + "/oa/oa-meeting.action?moduleCd=zc&id=" + id;
		break;
	case "specialtask" :
		url = _PATH + "/oa/special-task.action?id=" + id;
		break;
	case "reimburse":
	case "travel":
		if (statusCd==2 || statusCd==-1){
			url=_PATH+'/jbpm/jbpm-'+type+'-flow!input.action?id='+id+'&taskId='+taskId+"&isDesk=1";
		}else{
			url=_PATH+'/jbpm/jbpm-'+type+'-flow!view.action?id='+id+'&taskId='+taskId+"&isDesk=1";
		}
		break;
	}
	
	showAll(url,type);
}
function diplayAll(statusCd, type) {
	var url = _PATH+'/jbpm/jbpm-' + type + '-flow!desk.action?isDesk=1';
	showAll(url,type);
}

/**
 * 显示/隐藏聊天区域(实际是调整机构树的高度)
 * @param tag 'show'/'hide'
 * @return
 */
function initChatHeight(tag){
	var docH = $(document).height();
	var h ;
	if(tag == "show"){
		h = docH-48-26-4-24-20-35-16;
	}else{
		h = docH-48-26-4-24-20-30-16;
	}
	$("#left_address_panel_id").height(h+'px');
	$(".talk_member_ul_cls").height(h-4+'px');
	$("#left_user_result").height(h-6+'px');
}
function initHeight(){
	var docH = $(document).height();
	h = docH-48-20; 
	$("#center_content_container").height(h+'px');
	$("#noteContent").height(h-100+'px');
	$("#note_data_container").height(h-33+'px');
}
function openShare(){
	$("#OpenShareApplet").remove();
	$("#divJre").append("<applet width='0' id='OpenShareApplet' height='0' code='OpenShareApplet.class' codebase='.' archive='../winApplet.jar'><param name='url' value='192.168.180.252'/></applet>");
}
//关闭浏览器时注销
function runOnBeforeUnload() { 
	//desk-org-user.jsp
	//$("#btn_logout").click();
	logoff();
} 
function initChatEditor(){
	var iWidth = $(document).width(); 
	var iHeight = $(document).height();
	var h = 350;
	var w = 500;
	//初始化富文本编辑器
	this.chatRichEditor = $('#chatRichEditor').xheditor({
		tools:'GStart,Fontface,FontSize,Bold,Italic,Underline,Strikethrough,FontColor,BackColor,Removeformat,Align,Link,Img,Emot,GEnd',
		forcePtag:false,
		html5Upload:false,upMultiple:'1',
		upImgUrl:_ctx+"/webim/user-message!upload.action",upImgExt:"jpg,bmp,gif,png",
		upLinkUrl:_ctx+"/webim/user-message!upload.action",upLinkExt:"zip,rar,txt,xlsx,docx,doc,xls,pdf",
		shortcuts:{'enter':function(){
				$("#chatting_panel .chatting_send").trigger('click');
			},'ALT+S':function(){
				$("#chatting_panel .chatting_send").trigger('click');
			},'ALT+C':function(){
				$("#chatting_panel .chatting_close").trigger('click');
			},'ALT+M':function(){
				$("#chatting_panel .chatting_window_min").trigger('click');
			},'ALT+T':function(){
				$("#chatting_panel .chatting_clean").trigger('click');
			}
		}
	});
	var $chattingPanel = $("#chatting_panel");
	//注册关闭动画事件
	$("#chatting_panel .chatting_window_close").click(function(){
		$("#chatting_min_container").hide();
		$chattingPanel.animate({
			width:0,
			height:0,
			opacity:"hide"
		},400);
		closeChattingWindow();
	});
	$("#chatting_panel .chatting_window_close,#chatting_panel .chatting_close").click(function(){
		$("#chatting_min_container").hide();
		$chattingPanel.animate({
			width:0,
			height:0,
			opacity:"hide"
		},400);
		closeChattingWindow();
	});
	
	//注册最小化动画事件
	$("#chatting_panel .chatting_window_min").click(function(){
		$("#chatting_min_container").show();
		var $minOff = $("#chatting_min_container").offset();
		$chattingPanel.animate({
			top:$minOff.top-20+"px",
			left:$minOff.left-20+"px",
			width:"20px",
			height:"20px",
			opacity:"hide"
		},400);
	});
	//注册还原动画事件
	$("#chatting_min_container").click(function(){
		var $minOff = $(this).offset();
		$(this).hide();
		$chattingPanel.css({'left':$minOff.left,'top':$minOff.top});
		$chattingPanel.animate({
			top:(iHeight-h)/2+'px',
			left:(iWidth-w)/2+'px',
			width:w+'px',
			height:h+'px',
			opacity:"show"
		},400);
	});
	
	$(document).keydown(function(event){
		var event = window.event?window.event:event;
		var key = event.which || event.keyCode;
		if (key == 27){
			if(!$("#chatting_panel").is(":hidden")){
				$("#chatting_panel .chatting_close").trigger('click');
			}
		}
	});
	
	$chattingPanel.hide().css({'zIndex':'99','width':w+'px','height':h+'px','top':(iHeight-h)/2+'px','left':(iWidth-w)/2+'px'});
	var od = $("#chatting_panel div.titlebar")[0];
	var dx,dy,mx,my,mouseD; 
	var odrag; 
	var isIE = document.all ? true : false; 
	document.onmousedown = function(e){ 
		var e = e ? e : event; 
		if(e.button == (document.all ? 1 : 0)){ 
			mouseD = true; 
		} 
	};
	document.onmouseup = function(){
		od.style.cursor = '';
		mouseD = false; 
		odrag = ""; 
		if(isIE){ 
			od.releaseCapture(); 
		}else{ 
			window.releaseEvents(od.MOUSEMOVE); 
		} 
	};
	od.onmousedown = function(e){
		od.style.cursor = 'move';
		odrag = this; 
		var e = e ? e : event; 
		if(e.button == (document.all ? 1 : 0)){ 
			mx = e.clientX; 
			my = e.clientY; 
			$chattingPanel[0].style.left = $chattingPanel[0].offsetLeft + "px"; 
			$chattingPanel[0].style.top = $chattingPanel[0].offsetTop + "px"; 
			if(isIE){ 
				od.setCapture(); 
			} else { 
				window.captureEvents(Event.MOUSEMOVE); 
			} 
		} 
	};
	document.onmousemove = function(e){ 
		var e = e ? e : event; 
	
		if(mouseD==true && odrag) { 
			var mrx = e.clientX - mx + parseInt($chattingPanel[0].style.left); 
			var mry = e.clientY - my + parseInt($chattingPanel[0].style.top); 
			mrx = mrx > 0 ? mrx : 0; 
			mry = mry > 0 ? mry : 0; 
			$chattingPanel[0].style.left = mrx + "px"; 
			$chattingPanel[0].style.top = mry + "px"; 
			mx = e.clientX; 
			my = e.clientY; 
		} 
	};
}
/****************************************************************************************/
/****************************************************************************************/

$(function() {
	//屏幕高度
	var docH = $(document).height();
	PAGE_SIZE = parseInt(((docH-38)/3-33)/23);
	initChatHeight('show');
	initHeight();
	TabUtils.initTab("homepage");
	$("#homepage2").click(function(event) {
		event.preventDefault();
		if ($(this).attr("type") != "none") {
			TabUtils.newTab($(this).attr("type"), $(this).attr("menuname"), $(this).attr("href"), true);
		}
	});
	bindNoteEvents();
	
	bindSlideEvents();
	bindFuncEvents();
	bindFuncMenuHeaderEvents();
	bindChatSlideEvents();
	
	$(".func_menu_btn").each(function() {
		$(this).bind("mouseover", function() {
			$(this).find(".menu_icon").hide();
			$(this).find(".menu_icon_hover").show();
			$(this).find(".button_menu_content_text").css("color","#FFF");
			
		});
		$(this).bind("mouseout", function() {
			$(this).find(".menu_icon_hover").hide();
			$(this).find(".menu_icon").show();
			$(this).find(".button_menu_content_text").css("color","#999");
		});
	});
	
	$(".page_icon").each(function() {
		$(this).bind("mouseover", function() {
			$(this).find(".page_icon").hide();
			$(this).find(".page_icon_hover").show();
		});
		$(this).bind("mouseout", function() {
			$(this).find(".page_icon_hover").hide();
			$(this).find(".page_icon").show();
		});
	});
	
	// 切换通讯录/聊天
	$(".left_top_tabs div").click(function() {
		$(".left_top_tabs div[class='active']").removeClass("active").addClass("inactive");
		$(this).removeClass("inactive").addClass("active");
		showTab($(this).attr("id"));
	});

	// 切换正在联系/最近联系
	$(".left_talk_options li").click(function() {
		if (!$(this).hasClass("active")) {
			$(".left_talk_options li[class='active']").removeClass("active");
			$(this).addClass("active");
			showTalkTab($(this).attr("id"));
		}
	});
	setInterval(refreshHome, 5*60*1000);
	//检测用户是否已经绑定mac地址
	//showMessage();
	//showHelpMessage();
	//initChatEditor();
	refreshHome();
	
	oriFuncMenuPlanContentHeight = $("#func_menus_plan_content").height();
	oriFuncMenuCommonContentHeight = $("#func_menus_common_content").height();
	oriFuncMenuContactContentHeight = $("#func_menus_contact_content").height();
});
// 点击向上或向下按钮时滚动菜单区域
var direction = null;
function startScrolling(dir) {
	direction = dir;
	scrollMenu();
}
function stopScrolling() {
	direction = null;
}
function scrollMenu() {
	if (direction == null) {
		return;
	}
	
	var innerHeight = parseInt($(".func_container").height());
	var containerHeight = parseInt($(".center_right_funcs").height());
	var oriMarTop = parseInt($(".func_container").css("margin-top"));
	var scHeight = 5;
	
	if (innerHeight > containerHeight - 60) {
		if (direction == "down") {
			if (oriMarTop >= -(innerHeight - containerHeight + 60)) {
				isScrolling = true;
				$(".func_container").css("margin-top", (oriMarTop - scHeight) + "px");
			}
		} else {
			if (oriMarTop < 0) {
				$(".func_container").css("margin-top", (oriMarTop + scHeight) + "px");
			}
		}
	}
	
	setTimeout("scrollMenu()",  30);
}
function openHelp(){
	window.open(_ctx+"/sso/help/login.jsp");
}
function openEas(){
	window.open(_ctx+"/sso/eas/login.jsp");
}
function openExchange(){
	//window.open(_ctx+"/sso/exchange/login.jsp");
	var url = _ctx+"/sso/exchange/login.jsp";
	TabUtils.newTab("out_email","外部邮件",url,true);
}
function openCtrip(){
	window.open("http://corporatetravel.ctrip.com/crpTravel/loginIndex.asp");
}
function isMacOs(){
	var os = window.navigator.userAgent; 
	var osVersion = os.split(";")[2]; 
	if(osVersion.indexOf("Mac")>-1){
		return true;
	}else{
		return false;
	}

}
