/*
 * @requires VisitingCardUtil.js - 名片
 */

﻿﻿var _MID_TOP_TABS_MAIL = "mail";
var _MID_TOP_TABS_NOTIFY = "notify";
var _MID_TOP_TABS_NEWS = "news";

var curMidTopTab = _MID_TOP_TABS_MAIL;
var _MID_BOTTOM_TABS_TRAVEL="travel";
var _MID_BOTTOM_TABS_REIMBURSE="reimburse";
var curMidBottomTab = _MID_BOTTOM_TABS_TRAVEL;
var curMidBottomTabUrl = _ctx+'/jbpm/jbpm-travel-flow!desk.action?isDesk=1';
/**
 * 当前编辑便签的编辑状态
 * false 无变动;
 * true 有变动 
 */
var IS_NOTE_DIRTY = false;
/**
 * 当前鼠标是否已经离开便签区域
 * true 已经离开
 * false 未离开
 */
var IS_OUT_NOTE_PANEL = false;

/**
 * 获取邮件列表刷新中间区域
 * @param boxId
 * @param dom
 * @param param
 * @return
 */
function getMailList(boxId,dom,param){
	$("#mailBox").addClass("waiting");
	if(dom)$(dom).addClass("liClick").siblings().removeClass("liClick");
	var params;
	if(param){
		params = param;
	}else{
		params = {boxId:boxId};
	}
	$.post(_ctx+"/email/email!list.action",params, function(result) {
		$("#mailBox").html(result);
		$("#mailBox").removeClass("waiting");
		if(boxId==1)getUnReadNum();
	});
}
function setNum2LeftBar(){
	$.post(_ctx+"/oa/oa-email!getMailNum.action",function(result){
		var num = eval(result);
		$("#inBoxNum").text(num[0]);
		$("#draftBoxNum").text(num[1]);
		$("#outBoxNum").text(num[2]);
		$("#delBoxNum").text(num[3]);
		$("#unReadBoxNum").text(num[4]);
	});
}
/**
 * 首页邮件分页
 * @param pageNo
 * @param boxId
 * @param dom
 * @return
 */
function jumpPage(pageNo,boxId,dom){
	var param = {"boxId":boxId,"pageEmail.pageNo":pageNo};
	getMailList(null,null,param);
}

function getNotePage(pageNo){
	$("#note_container").addClass("waiting");
	$.post(_ctx+"/dly/dly-note!desk.action",{"pageNote.pageNo":pageNo}, function(result) {
		$("#note_container").html(result).removeClass("waiting");
		bindNoteEvents();
		hideNote();
	});
}
/**
 * 获取未读邮件数量
 * @return
 */
function getUnReadNum(){
	if($("#unReadMailNum").length>0){
		$.post(_ctx+"/email/email!getUnReadNum.action",function(result){
			$("#unReadMailNum").html(result);
		});
	}
}

var isStillSliding = false;
function slideContent(direction, target, targetScrollHeight, targetOffsetHeight, slideHeight, speed) {
	if (isStillSliding) {
		return;
	}
	var old = parseInt(target.css("margin-top"));
	if (direction == "up" && old < 0) {
		var newp =  old + slideHeight;
		isStillSliding = true;
		target.animate({
			marginTop: newp
		}, 
		speed,
		function() {
			isStillSliding = false;
		});
	} else if (direction == "down" && (targetScrollHeight + old) > targetOffsetHeight) {
		var newp =  old - slideHeight;
		isStillSliding = true;
		target.animate({
			marginTop: newp
		}, 
		speed,
		function() {
			isStillSliding = false;
		});
	}
}

/* 显示URL所指页面填充整个中间区域  */
function showAll(url, tab, type) {
	$("#middle_content_center").hide();
	$("#fullscreen_container .top_tabs ul li[class='active']").removeClass("active");
	// 根据不同的tab类型来决定全屏时哪个tab处于激活状态
	switch (tab) {
		case "mail":
			$("#fullscreen_mails_tab").addClass("active");
			break;
		case "notify":
			$("#fullscreen_notify_tab").addClass("active");
			break;
		case "news":
			$("#fullscreen_news_tab").addClass("active");
			break;
		default: break;
	}
	if (type == "iframe") {
		$("#middle_content").hide();
		$("remoteContent").html("").hide();
		$("#middle_showAll").show();
		$("#btns_panel_all_top").show();
		$("#btns_only_close").hide();
		$("#remotePage").attr("src", url).fadeIn();
	} else {
		$.get(
			url,
			function(data) {
				$("#middle_content").hide();
				$("#remotePage").attr("src", "").hide();
				$("#middle_showAll").show();
				$("#btns_panel_all_top").show();
				$("#btns_only_close").hide();
				$("#remoteContent").html(data).fadeIn();
		});
	}
	return false;
}
function showAllBottom(url,  tab,	type) {
	//alert('ss');
	if(null!=url){
		document.getElementById('div_contents').src=url;
	}
	if("max"!=now_status_middle_content_center){
		//document.getElementById('div_contents').style.visibility="hidden";
		divMove_y_h('middle_content_center','max','after_move_middle_content_center("max")','div_JbpmTravel_contents,div_contents',48);
		//$("#qp_bottom").attr("src",_ctx+"/images/tab/close.gif");
		$("#qp_bottom").attr("className","closeBtn");
	}
	return false;
}

/**
 * 功能:搜索子孙机构和员工
 * @param val: 
 * @return 
 */
function onClick_searchDescendantOrgUsers(orgBizCd){

	if("" == val)return;

	//加载中...
	$("#userDisplay").html("<img align='middle' src='${ctx}/images/loading.gif'>");
	
	$.post("${ctx}/desk/desk!getUsersByFilter.action",{value:domValue},function(result){
		$("#userDisplay").empty();
		$("#deptDisplay").text("搜索结果");
		result = eval(result);
		$.each(result,function(i,n){
			if(n)bindEvents(n,true);
		});
	});
}

/**
 *便签点击事件 
 */
function showNote(dom){
	$("#right_panel").width("227px");
	$(dom).addClass("note_click").siblings().removeClass("note_click");
	if(dom.id == "new_note_li"){
		//$("#new_note_li").addClass("new_note_click");
		setNoteInfo2Div(null);
	}else{
		//$("#new_note_li").attr("className","");
		$.post(_ctx+"/dly/dly-note!load.action",{id:dom.id},function(result){
			if(result == "error"){
				alert("该便签已经被删除！");
				getNotePage(1);
				return;
			}
			setNoteInfo2Div(eval(result));
		});
	}
	
}
function setNoteInfo2Div(obj){
	if(obj){
		$("#dlyNoteId").val(obj[0]);
		$("#noteTitle").val(obj[1]||"");
		$("#noteContent").val(obj[2]||"");
		$("#noteContent").focus();
		$("#updateDate").text(obj[3]);
	}else{
		$("#dlyNoteId").val("");
		$("#noteTitle").val("");
		$("#noteTitle").focus();
		$("#noteContent").val("");
		$("#updateDate").text("");
		
	}
	$("#notes_panel").css("marginLeft","2px");
	$("#notes_content_container").show();
	IS_NOTE_DIRTY = false;
}

function saveNote(){
	if(!IS_NOTE_DIRTY)return;
	var id = $("#dlyNoteId").val();
	var title = $("#noteTitle").val();
	var content = $("#noteContent").val();
	if($("#dlyNoteId").val()==""){
		$("#noteTitle").val("");
		$("#noteContent").val("");
	}
	if($.trim(title) == ""&&$.trim(content) == "")return;
	$.post(_ctx+"/dly/dly-note!save.action",{noteTitle:title,noteContent:content,id:id},function(result){
		IS_NOTE_DIRTY = false;
		var obj = eval(result);
		if(id !=""){
			$("#"+id).text(title).attr("title",obj[1]);
		}else{
			if($("#notes_title_container ul li").length == 11){
				$("#notes_title_container ul li:last").remove();
			}
			$("#new_note_li").after("<li id='"+obj[0]+"' title='"+obj[1]+"&#13;"+obj[2]+"'>"+obj[1]+"</li>");
			showNote($("#"+obj[0])[0]);
		}
	});
	
}
function hideNote(){
	$("#notes_content_container").hide();
	$("#notes_panel").css("marginLeft","4px");
	$("#right_panel").animate({width:"70px"},300);
	$("#notes_title_container ul li.note_click").removeClass("note_click");
}
/**
 * 删除便签
 */
function deleteNote(){
	var dom = $(".note_click");
	var id = dom.attr("id");
	if(id=="new_note_li")return;
	$.post(_ctx+"/dly/dly-note!delete.action",{id:id},function(result){
		$("#note_container").html(result).removeClass("waiting");
		bindNoteEvents();
		hideNote();
	});
}
/**
 * 将便签内容通过邮件发送
 */
function sendNote2Email(){
	var noteTitle = $("#noteTitle").val();
	var noteContent = $("#noteContent").val();
	var param="sendEmailFlag=true&subject="+noteTitle+"&content="+noteContent;
	showAll(_ctx+"/oa/oa-email!main.action?"+param, "mail", "iframe");
}
function showAllNote(){
	showAll(_ctx+"/dly/dly-note!list.action", "", "iframe");
	$("#fullscreen_container .top_tabs").hide();
	$("#btns_only_close").show();
	
}
/**
 * 复制便签
 */
function copyNote(){
	IndexUtils.copyToClipboard($("#noteContent").text());
}
function bindNoteEvents(){
	//$("#notes_panel").mouseover(function(){IS_OUT_NOTE_PANEL = false;}).mouseout(function(){IS_OUT_NOTE_PANEL = true;})
	$("#notes_content_container").bind("mouseleave",function(){
		saveNote();
	});
	$("#notes_panel").bind("mouseleave",function(){
		//if(IS_OUT_NOTE_PANEL){
			saveNote();
			setTimeout(hideNote, 300);
		//}
	});
	$("#notes_title_container ul li").live("click",function(){
		showNote(this);
	})
	$("#noteTitle,#noteContent").keyup(function(){IS_NOTE_DIRTY = true});
	
	/*$(document).click(function(){
		if(IS_OUT_NOTE_PANEL){
			saveNote();
			hideNote();
		}
	})*/
}

// 首页中间顶部的公告/新闻分页
function jumpPageTo(type, pageNo) {
	var url = "";
	var contentDiv = null;
	switch (type) {
		case "notify":
			url = _ctx+"/desk/desk-notify!frontPageNotify.action?frontNotifyPageNo=" + pageNo;
			contentDiv = $("#middle_top_content_container_notify");
			break;
		case "news":
			url = _ctx+"/desk/desk-news!frontPageNews.action?frontNewsPageNo=" + pageNo;
			contentDiv = $("#middle_top_content_container_news");
			break;
		default: return;
	}
	contentDiv.addClass("waiting");
	$.get(
		url,
		function(data) {
			contentDiv.removeClass("waiting").html(data);
		}
	);
}

//如果字符串超过指定长度，自动截断并用...代替后面的文本
function cutoffStr(str, num) {
	str = jQuery.trim(str);
	if(str.length <= num) {
		return str;
	} else {
		return str.substring(0, num) + "...";
	}
}
// 隐藏首页顶部邮件内容
function hideMailContent() {
	$("#middle_top_content_container_mail").hide();
}
// 隐藏首页顶部公告内容
function hideNotifyContent() {
	$("#middle_top_content_container_notify").hide();
}
// 隐藏首页顶部新闻内容
function hideNewsContent() {
	$("#middle_top_content_container_news").hide();
}
// 打开一条新的新闻或者公告时把new图标删掉
function removeNewImg(srcEle, type) {
	$(srcEle).siblings(".new_img").hide();
	if (type == "news") {
		checkUnreadedNews();
	} else if (type == "notify") {
		checkUnreadedNotify();
	}
}
//查看是否有未读公告
function checkUnreadedNotify() {
	$.get(_ctx+"/desk/desk-notify!getUnreadedNotifyNum.action",
		function(result) {
			if (parseInt(result) > 0) {
				$("#new_notify_flag").fadeIn();
			} else {
				$("#new_notify_flag").fadeOut();
			}
		}
	);
}
// 打开一条公告
function openNotify(url, notifyId, isReaded) {
	window.open(url);
	// 如果当前公告是未读，则往后台发一条消息设置为已读
	if (isReaded == "-1") {
		$.get("${ctx}/oa/oa-notify!detail.action?id=" + notifyId);
	}
}
$(function(){
	$("#right_panel").width(70);
	$("#btns_panel").children("[class*='btn']").each(function(i){
		$(this).click(function(){
			$(this).removeClass("white").addClass("green").siblings("[class*='btn']").removeClass("green").addClass("white");
			var src=$(this).attr("src");
			if("max"!=now_status_middle_content_center){
			src=src+"&pageSize=3";
			}
			$("#div_contents").attr("src",src);
			curMidBottomTabUrl=$(this).attr("src");
			curMidBottomTab=$(this).attr("tabType");
		});
	 });
	
	var slideSpeed = 300;

	
	var viewNotify = true;
	$("#notify_nav").click(function(event) {
		if($(this).hasClass("bg_bai")) {
			$(this).removeClass("bg_bai");
			$("#news_nav").removeClass("bg_bai");
			$(this).addClass("bg_lv");
			$("#news_nav").addClass("bg_bai");
			viewNotify = true;
		}
		
		if ($("#notify_list").css("display") == "none") {
			$("#notify_list").show();
			$("#news_list").hide();
			$("#gotomanage").attr("href", _ctx+"/oa/oa-notify.action");
		}
	});
	$("#news_nav").click(function(event) {
		if($(this).hasClass("bg_bai")) {
			$(this).removeClass("bg_bai");
			$("#notify_nav").removeClass("bg_lv");
			$(this).addClass("bg_lv");
			$("#notify_nav").addClass("bg_bai");
			viewNotify = false;
		}
		
		if ($("#news_list").css("display") == "none") {
			$("#news_list").show();
			$("#notify_list").hide();
			$("#gotomanage").attr("href", _ctx+"/oa/oa-news.action");
		}
	});
	
	$("#news_slide_up_btn").click(function(event) {
		slideNewsOrNotify("up");
	});
	$("#news_slide_down_btn").click(function(event) {
		slideNewsOrNotify("down");
	}); 
	function slideNewsOrNotify(direction) {
		if (viewNotify) {
			var notifyOffsetHeight = $("#notify_list").get(0).offsetHeight;
			var notifyItemHeight = parseInt($("#notify_list li:first").height());
			var notifyScrollHeight = $("#notify_list ul li").length * notifyItemHeight;
			
			slideContent(direction, $("#notify_list li:first"), notifyScrollHeight, notifyOffsetHeight, notifyItemHeight*3, 300);
		} else {
			var newsOffsetHeight = $("#news_list").get(0).offsetHeight;
			var newsItemHeight = parseInt($("#news_list li:first").height());
			var newsScrollHeight = $("#news_list ul li").length * newsItemHeight;
			
			slideContent(direction, $("#news_list li:first"), newsScrollHeight, newsOffsetHeight, newsItemHeight*3, 300);
		}
	}
	
	// 当点击邮件tab时载入邮件内容
	$("#mails_nav_btn").click(function() {
		if (!$(this).hasClass("active")) {
			$("#middle_top_content_container_mail").html("").addClass("waiting").show();
			$("#middle_top_tabs li[class='active']").removeClass("active");
			$(this).addClass("active");
			
			hideNotifyContent();
			hideNewsContent();
			curMidTopTab = _MID_TOP_TABS_MAIL;
			setNum2LeftBar();
			var url = $(this).children("div").children("a").attr("href");
			$.get(
				url,
				function(data) {
					$("#middle_top_content_container_mail").removeClass("waiting").html(data);
					$("#inBoxId").trigger("click");
				}
			);
		}
	});
	
	// 当点击公告tab时载入公告内容
	$("#notify_nav_btn").click(function() {
		if (!$(this).hasClass("active")) {
			$("#middle_top_content_container_notify").html("").addClass("waiting").show();
			$("#middle_top_tabs li[class='active']").removeClass("active");
			$(this).addClass("active");
			
			hideMailContent();
			hideNewsContent();
			curMidTopTab = _MID_TOP_TABS_NOTIFY;
			
			var url = $(this).children("div").children("a").attr("href");
			$.get(
				url,
				function(data) {
					$("#middle_top_content_container_notify").removeClass("waiting").html(data);
				}
			);
		}
	});
	
	// 当点击新闻tab时载入新闻内容
	$("#news_nav_btn").click(function() {
		if (!$(this).hasClass("active")) {
			$("#middle_top_tabs li[class='active']").removeClass("active");
			$(this).addClass("active");
			curMidTopTab = _MID_TOP_TABS_NEWS;
			
			hideMailContent();
			hideNotifyContent();
			$("#middle_top_content_container_news").html("").addClass("waiting").show();
			
			var url = $(this).children("div").children("a").attr("href");
			$.get(
				url,
				function(data) {
					$("#middle_top_content_container_news").removeClass("waiting").html(data);
				}
			);
		}
	});	
	
	//页面进入时默认点击邮件按钮
	$("#mails_nav_btn").trigger("click");
	//默认显示收件箱
	//getUnReadNum();
	//每五分钟读取一次未读邮件数量
	setInterval(getUnReadNum, 5*60*1000);
	
	// 定时查看是否有未读新闻
	checkUnreadedNews();
	setInterval(checkUnreadedNews, 10 * 1000);
	
	// 定时查看是否有未读公告
	checkUnreadedNotify();
	setInterval(checkUnreadedNotify, 10 * 1000);
	
	$("#full_screen_btn").click(function() {
		switch(curMidTopTab) {
			case _MID_TOP_TABS_MAIL:
				return showAll(_ctx+"/oa/oa-email!main.action", "mail", "iframe");
			case _MID_TOP_TABS_NOTIFY:
				return showAll(_ctx+"/desk/desk-notify.action", "notify", "iframe");
			case _MID_TOP_TABS_NEWS:
				return showAll(_ctx+"/desk/desk-news.action", "news", "iframe");
			default: 
				return false;
		}
	});
	
	$("#closeMiddleShowAll,#closeMiddleShowAll_bottom,#closeOnly").click(function() {
		$("#middle_content_center").show();
		$("#remoteContent").html("");
		$("#remotePage").attr("src", "");
		$("#middle_showAll").hide();
		$("#middle_content").show();
	});
	
	/*********************************************************/
	/*            HUANGBIJIN   begin                        **/
	/*********************************************************/
	
	//切换"通讯录/对话"
	//<li id="talk_talking_nav"><div><a>正在对话</a></div></li>
	//<li id="talk_latest_nav"><div><a>最近联系人</a></div></li>
	
	$(".left_top_tabs li").click(function() {
		if (!$(this).hasClass("active")) {
			$(".left_top_tabs li[class='active']").removeClass("active");

			var activeNavId = $(this).attr("id");
			$(this).addClass("active");
			
			showTab(activeNavId);
			
		}
	});
	

	//切换"正在对话/群组/最近联系人"
	$(".left_talk_options li").click(function() {

		if (!$(this).hasClass("active")) {
			$(".left_talk_options li[class='active']").removeClass("active");
			$(this).addClass("active");
			showTalkTab($(this).attr("id"));
		}
	});
	
	//页面进入时默认点击"通讯录" 
	$("#address_nav").trigger("click");
	//页面进入时默认点击"正在对话"
	$("#talk_talking_nav").trigger("click");
	
	// 页面进入是默认隐藏新闻和公告内容
	hideNotifyContent();
	hideNewsContent();
	
	//鼠标离开左边栏,名片自动关闭
	$("#left_panel").bind("mouseleave",function(){
		VisitingCardUtil.hide();
	});
	
	bindNoteEvents();
	
});
$("#mail_box").ready(function(){
	setNum2LeftBar();
	});
/*切换正在对话/最近联系人*/
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



/*切换面板:通讯录/对话/搜索 */
function showTab(activeElemId){
	switch(activeElemId){
		case "address_nav": 
			$("#left_address_panel_id").show();
			$("#left_talk_options_id").hide();
			$("#left_user_result").hide();
			break;
		case "talk_nav":
			$("#left_address_panel_id").hide();
			$("#left_talk_options_id").show();
			$("#left_user_result").hide();
			break;
		case "search_nav":
			$("#left_address_panel_id").hide();
			$("#left_talk_options_id").hide();
			$("#left_user_result").show();
			break;
		default:break;
	}
}

/**
 * 功能:OA搜索,搜索用户列表
 * @param val: 模糊匹配 登录名,姓名 ,公司电话和手机号码
 * @return 更新搜索结果
 */
function searchUser(val){
	var tabId = 'search_nav';
	if($.trim(val) == ""){
		var curTabId = $(".left_top_tabs li[class='active']").attr("id");
		showTab(curTabId);
		return;
	}else{
		$("#left_user_result").addClass("waiting");
		$.post(_ctx+"/desk/desk!getUsersByFilter.action",{value:val},function(result){
			//alert(result);
			$("#left_user_result").html(result).removeClass("waiting").show();
			showTab(tabId);
		});

		$("#left_user_result").removeClass("waiting");
		showTab(tabId);
	}
}


