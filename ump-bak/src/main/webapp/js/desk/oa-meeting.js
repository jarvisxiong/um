﻿﻿var _PATH = _ctx;
var openItem = null;
var attendee = [];
var attendeeAll = [];
var person = [];
var resPersonOn= [];
var corPersonOn = [];
var resPerson = [];
var corPerson = [];
var viewPerson = [];
var existResPerson = [];
var existCorPerson = [];
var _ATT_TYPE_RES = "RES";
var _ATT_TYPE_COR = "COR";
var _ATT_TYPE_VIEW = "VIEW";
var _ATT_TYPE_EXIST_RES = "EXIST_RES";
var _ATT_TYPE_EXIST_COR = "EXIST_COR";
var _ATT_TYPE_EXIST_VIEW = "EXIST_VIEW";
var isUpDateRefreshed = false;
var isRefreshing = false;
var PersonalAll = "";
var resNameAll="";
var outTime;
var changeFlag = false;
var _strModule="";	//是哪个模块（地产或者商业）
// 增加新的会议记录
function addNewSummary() {
	$(".add_new").each(function() {
		var disp = $(this).css("display");
		if (disp == "none") {
			$.post(_PATH + "/oa/oa-meeting!computeWaterNum.action?strModule="+_strModule, function(result) {
				$("#inputWaterNum").text(result);
				$("#waterNumberInput").val(result);
				$("#addNew_Business").focus();
			});
			$(this).show();
		} else {
			$(this).hide();
		}
	});
}

// 切换页面表格的上下箭头
function switchArrow(ele, dir) {
	if (dir == "down") {
		ele.find(".up_arrow").hide();
		ele.find(".down_arrow").show();
	} else {
		ele.find(".up_arrow").show();
		ele.find(".down_arrow").hide();
	}
}

// 选择与会人员
function selectAttendee(personal) {
    PersonalAll = personal;
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"管理用户池",
		message:"<div id='memberDiv'><img align='absMiddle' src='" + _PATH + "/images/loading.gif'></div>",
		useSlide:true,
		winPos:"t",
		width:400,
		height:400,
		maxBtn: false,
		allowRightMenu:true,
		handler:getAttendee,
		afterShow:function(){
			$.post(_PATH + "/oa/oa-meeting!member.action",{"personal":PersonalAll},function(result){
				$("#memberDiv").html(result);
				if(PersonalAll=="manager"){
				  if(attendee.length > 0) {
					showSelectedUser(PersonalAll);
				}else{
					$("#userDisplay li").each(function(){
						var obj = {"uiid" : $(this).attr("id"), "userName" : $(this).text()};
						bindEvents(obj, false,PersonalAll);
					});
				 }
				}else{
				if(person.length > 0) {
					showSelectedUser(PersonalAll);
				}else{
					$("#userDisplay li").each(function(){
						var obj = {"uiid" : $(this).attr("id"), "userName" : $(this).text()};
						bindEvents(obj, false,PersonalAll);
					});
				}
				}
				
			});
		}
	});
}

// 显示已经选择的人员
function showSelectedUser(PersonalAll){
	$("#userDisplay").empty();
	$("#deptDisplay").text("已选人员");
	if(PersonalAll=="manager"){
		$.each(attendee, function(i,n) {
			bindEvents(n, true,PersonalAll);
		});
	}else if(PersonalAll=="personal"){
	 $.each(person, function(i,n) {
		bindEvents(n, true,PersonalAll);
	});
	}else if(PersonalAll=="res"){
	$.each(resPersonOn, function(i,n) {
		bindEvents(n, true,PersonalAll);
	});
	}else{
	$.each(corPersonOn, function(i,n) {
		bindEvents(n, true,PersonalAll);
	});
	
	}
	
}

function getAttendee(tp){
	if(tp=="ok"){
		saveUserPool(PersonalAll);
		
		for (var i = 0; i < resPerson.length; i ++) {
			if (resPerson[i].uiid == uiid) {
				resPerson.splice(i, 1);
				break;
			}
		}
		var result = getSelectedResult(resPerson);
		if (result.length > 0) {
			$("#resPersonNames").html(result[0].userNames).attr("title", result.userNames);
			$("#resPersonIds").val(result[0].uiids + ";");
		} else {
			$("#resPersonNames").html("").attr("title", "");
			$("#resPersonIds").val("");
		}
		
		
		for (var i = 0; i < corPerson.length; i ++) {
			if (corPerson[i].uiid == uiid) {
				corPerson.splice(i, 1);
				break;
			}
		}
		result = getSelectedResult(corPerson);
		if (result.length > 0) {
			$("#corPersonNames").html(result[0].userNames).attr("title", result[0].userNames);
			$("#corPersonIds").val(result[0].uiids);
		} else {
			$("#corPersonNames").html("").attr("title", "");
			$("#corPersonIds").val("");
		}
	} else {
		initUserManagerPool();
		initUserPersonPool();
		initUserAllPool();
	}
}
// 把用户池保存到数据库
function saveUserPool(PersonalAll) {
    if(PersonalAll=="manager"){
       var result = getSelectedResult(attendee);
       result[0]["userIds"]=$("#select_manager_cd").val();
       result[0]["userNames"]=$("#select_manager").val();
     }else{
       var result = getSelectedResult(person);
       result[0]["userIds"]=$("#select_personal_cd").val();
       result[0]["userNames"]=$("#select_personal").val();
     }
	if (result.length > 0) {
		$.post(_PATH + "/oa/oa-meeting!saveUserPool.action",{"PersonalAll":PersonalAll,attendeeIds:result[0].userIds});
	} else {
		$.post(_PATH + "/oa/oa-meeting!saveUserPool.action",{"PersonalAll":PersonalAll,attendeeIds:result[0].userIds});
	}
	alert("保存成功");
}
// 搜索用户

function getUser(dom,PersonalAll) {
	var domValue = $.trim(dom.value);
	var PersonalAll = PersonalAll;
	if(outTime) clearTimeout(outTime);
	outTime= setTimeout(function(){
		if (domValue=="") return;
		$("#userDisplay").html("<img align='absMiddle' src='" + _PATH + "/images/loading.gif'>");
		$.post(_PATH + "/oa/oa-meeting!getUsersByFilter.action", {value:domValue}, function(result){
			$("#userDisplay").empty();
			$("#deptDisplay").text("搜索结果");
			result = eval(result);
			$.each(result, function(i, n){
				if(n) {
					bindEvents(n, true,PersonalAll);
				}
			});
		});
	},300);
}

function bindEvents(obj, addFlag,PersonalAll){
	if(addFlag) {
		$("#userDisplay").append("<li class='userUnSelected' id="+obj.uiid+">"+obj.userName+"</li>");
	}
	if(PersonalAll=="manager"){
			$("#"+obj.uiid).toggle(function(){
				$(this).attr("className","userSelected");
				addUser(obj.uiid, obj.userName, attendee);
			},function(){
				$(this).attr("className","userUnSelected");
				removeUser(obj.uiid, attendee);
			});
			
			if(containsUser(obj.uiid, attendee)){
				$("#"+obj.uiid).trigger("click");
	}
	}else if(PersonalAll=="personal"){
			  $("#"+obj.uiid).toggle(function(){
				$(this).attr("className","userSelected");
				addUser(obj.uiid, obj.userName, person);
			},function(){
				$(this).attr("className","userUnSelected");
				removeUser(obj.uiid, person);
			});
			
			if(containsUser(obj.uiid, person)){
				$("#"+obj.uiid).trigger("click");
			}
	}else if(PersonalAll=="res"){
	     $("#"+obj.uiid).toggle(function(){
				$(this).attr("className","userSelected");
				addUser(obj.uiid, obj.userName, resPersonOn);
			},function(){
				$(this).attr("className","userUnSelected");
				removeUser(obj.uiid, resPersonOn);
			});
			
			if(containsUser(obj.uiid, resPersonOn)){
				$("#"+obj.uiid).trigger("click");
			}
	}else{
	  $("#"+obj.uiid).toggle(function(){
				$(this).attr("className","userSelected");
				addUser(obj.uiid, obj.userName, corPersonOn);
			},function(){
				$(this).attr("className","userUnSelected");
				removeUser(obj.uiid, corPersonOn);
			});
			
			if(containsUser(obj.uiid, corPersonOn)){
				$("#"+obj.uiid).trigger("click");
			}
	
	}
	
}



function bindAttendeeEvents(obj, targetId, type) {
	$("#" + targetId).append("<li class='userUnSelected' id="+obj.uiid+"><div class='userStatus_online'>"+obj.userName+"</div></li>");
	$("#"+obj.uiid).toggle(function(){
		$(this).attr("className","userSelected");
		switch (type) {
		case _ATT_TYPE_RES :
			addUser(obj.uiid, obj.userName, resPerson);
			break;
		case _ATT_TYPE_COR :
			addUser(obj.uiid, obj.userName, corPerson);
			break;
		case _ATT_TYPE_VIEW :
			addUser(obj.uiid, obj.userName, viewPerson);
			break;
		case _ATT_TYPE_EXIST_RES :
			addUser(obj.uiid, obj.userName, existResPerson);
			break;
		case _ATT_TYPE_EXIST_COR :
			addUser(obj.uiid, obj.userName, existCorPerson);
			break;
		}
	},function(){
		$(this).attr("className","userUnSelected");
		switch (type) {
		case _ATT_TYPE_RES :
			removeUser(obj.uiid, resPerson);
			break;
		case _ATT_TYPE_COR :
			removeUser(obj.uiid, corPerson);
			break;
		case _ATT_TYPE_VIEW :
			removeUser(obj.uiid, viewPerson);
			break;
		case _ATT_TYPE_EXIST_RES :
			removeUser(obj.uiid, existResPerson);
			break;
		case _ATT_TYPE_EXIST_COR :
			removeUser(obj.uiid, existCorPerson);
			break;
		}
	});
	
	switch (type) {
	case _ATT_TYPE_RES :
		if (containsUser(obj.uiid, resPerson)) {
			$("#" + obj.uiid).trigger("click");
		}
		break;
	case _ATT_TYPE_COR :
		if (containsUser(obj.uiid, corPerson)) {
			$("#" + obj.uiid).trigger("click");
		}
		break;
	case _ATT_TYPE_VIEW :
		if (containsUser(obj.uiid, viewPerson)) {
			$("#" + obj.uiid).trigger("click");
		}
		break;
	case _ATT_TYPE_EXIST_RES :
		if (containsUser(obj.uiid, existResPerson)) {
			$("#" + obj.uiid).trigger("click");
		}
		break;
	case _ATT_TYPE_EXIST_COR :
		if (containsUser(obj.uiid, existCorPerson)) {
			$("#" + obj.uiid).trigger("click");
		}
		break;
	}
}

// 选择负责人员
function selectResPerson() {
    
	if (attendee.length == 0) {
		alert("用户池为空！");
		return;
	}

		ymPrompt.confirmInfo({
			icoCls:"",
			title:"选择负责人",
			message:"<div id='attendeeDiv'><img align='absMiddle' src='" + _PATH + "/images/loading.gif'></div>",
			useSlide:true,
			winPos:"t",
			width:200,
			height:400,
			maxBtn: false,
			allowRightMenu:true,
			handler:getResPerson,
			afterShow:function(){
				$.post(_PATH + "/oa/oa-meeting!attendee.action?attendeeType=" + _ATT_TYPE_RES,
						function(result){
							$("#attendeeDiv").html(result);
				});
			}
		});	
}
function selectResPersonOn(res){
  resNameAll = res;
  ymPrompt.confirmInfo({
		icoCls:"",
		title:"用户池",
		message:"<div id='memberDiv'><img align='absMiddle' src='" + _PATH + "/images/loading.gif'></div>",
		useSlide:true,
		winPos:"t",
		width:400,
		height:400,
		maxBtn: false,
		allowRightMenu:true,
		handler:insertResPerson,
		afterShow:function(){
			$.post(_PATH + "/oa/oa-meeting!member.action",{"personal":resNameAll},function(result){
				$("#memberDiv").html(result);
				if(resNameAll=="res"){
				  if(resPersonOn.length > 0) {
					showSelectedUser(resNameAll);
				 }else{
				    $("#userDisplay li").each(function(){
						var obj = {"uiid": $(this).attr("id"), "userName" : $(this).text()};
						bindEvents(obj, false,resNameAll);
					});
				 }
				}else{
				  if(corPersonOn.length > 0) {
					showSelectedUser(resNameAll);
				 }else{
				    $("#userDisplay li").each(function(){
						var obj = {"uiid": $(this).attr("id"), "userName" : $(this).text()};
						bindEvents(obj, false,resNameAll);
					});
				 }
				
				}
				  
				
			});
		}
	});
}


function insertResPerson(tp) {
	if (tp == "ok") {
		
		if(resNameAll=="res"){
		 var result = getSelectedResult(resPersonOn);
			if (result.length > 0) {
			    var userNames = $.trim(result[0].userNames);
				$("#resPersonNames").val(userNames).attr("title",userNames);
				$("#resPersonIds").val(result[0].userIds);
			} else {
				$("#resPersonNames").val("").attr("title", "");
				$("#resPersonIds").val("");
			}
		}else{
		       var result = getSelectedResult(corPersonOn);
			   if (result.length > 0) {
			    var userNames = $.trim(result[0].userNames);
				$("#corPersonNames").val(userNames).attr("title",userNames);
				$("#corPersonIds").val(result[0].userIds);
			} else {
				$("#corPersonNames").val("").attr("title", "");
				$("#corPersonIds").val("");
			}
		}
		
	}
}



function getResPerson(tp) {
	if (tp == "ok") {
		var result = getSelectedResult(resPerson);
		if (result.length > 0) {
			$("#resPersonNames").html(result[0].userNames).attr("title", result[0].userNames);
			$("#resPersonIds").val(result[0].userIds);
		} else {
			$("#resPersonNames").html("").attr("title", "");
			$("#resPersonIds").val("");
		}
	}
}

// 个人管理员管理负责人员
function geManageResPerson(itemId, event,res) {
	jQuery.Event(event).stopPropagation();
	resNameAll = res;
	if(resNameAll=="res"){
	   resPersonOn = [];
	}else{
	   corPersonOn = [];
	}
	$.post(_PATH + "/oa/oa-meeting!fetchPersonOn.action",
			{id : itemId, "PerSon" : resNameAll},
			function(result) {
				if ($.trim(result).length > 0) {
					var strAr = result.split("|");
					for (var i = 0; i < strAr.length; i ++) {
						var j = eval("(" + strAr[i] + ")");
						if(resNameAll=="res"){
						   resPersonOn.push(j);
						}else{
						   corPersonOn.push(j);
						}
					}
				}
				ymPrompt.confirmInfo({
					icoCls:"",
					title:"选择负责人",
					message:"<div id='memberDiv'><img align='absMiddle' src='" + _PATH + "/images/loading.gif'></div>",
					useSlide:true,
					winPos:"t",
					width:400,
					height:400,
					maxBtn: false,
					allowRightMenu:true,
					handler:updateResPersonOn,
					afterShow:function(){
					$.post(_PATH + "/oa/oa-meeting!member.action",{bizEntityId : itemId,"personal":resNameAll},function(result){
						$("#memberDiv").html(result);
						if(resNameAll=="res"){
						  if(resPersonOn.length > 0) {
							showSelectedUser(resNameAll);
						 }else{
						    $("#userDisplay li").each(function(){
								var obj = {"uiid": $(this).attr("id"), "userName" : $(this).text()};
								bindEvents(obj, false,resNameAll);
							});
						 }
						}else{
						  if(corPersonOn.length > 0) {
							showSelectedUser(resNameAll);
						 }else{
						    $("#userDisplay li").each(function(){
								var obj = {"uiid": $(this).attr("id"), "userName" : $(this).text()};
								bindEvents(obj, false,resNameAll);
							});
						 }
						
						}
						  
						
					});
					}
				});
	});
}

function updateResPersonOn(tp) {
	var result = ""; 
	if (tp == "ok") {
	    if(resNameAll=="res"){
	    result = getSelectedResult(resPersonOn);
	    }else{
	    result = getSelectedResult(corPersonOn);
	    }
		var itemId = $("#memberDiv .bizEntityId").html();
		if (result.length > 0) {
		    if(resNameAll=="res"){
			    $("#" + itemId + "resPerson").html(result[0].userNames).attr("title", result[0].userNames);
				(itemId, "resPerson", result[0].userIds);
		    }else{
			    $("#" + itemId + "corPerson").html(result[0].userNames).attr("title", result[0].userNames);
				(itemId, "corPerson", result[0].userIds);
		    }
		} else {
		    if(resNameAll=="res"){
			    $("#" + itemId + "resPerson").html("").attr("title", "");
				(itemId, "resPerson", "");
		    }else{
			    $("#" + itemId + "corPerson").html("").attr("title", "");
				(itemId, "corPerson", "");
		    }
			
		}
		
	}
	    resPersonOn=[];
	    corPersonOn=[];
}



// 管理负责人员
function manageResPerson(itemId, event) {
	jQuery.Event(event).stopPropagation();
	if (attendeeAll.length == 0) {
		alert("用户池为空！");
		return;
	}
	
	existResPerson = [];
	$.post(_PATH + "/oa/oa-meeting!fetchPerson.action",
			{id : itemId, attendeeType : _ATT_TYPE_EXIST_RES},
			function(result) {
				if ($.trim(result).length > 0) {
					var strAr = result.split("|");
					for (var i = 0; i < strAr.length; i ++) {
						var j = eval("(" + strAr[i] + ")");
						existResPerson.push(j);
					}
				}
				
				ymPrompt.confirmInfo({
					icoCls:"",
					title:"选择负责人",
					message:"<div id='attendeeDiv'><img align='absMiddle' src='" + _PATH + "/images/loading.gif'></div>",
					useSlide:true,
					winPos:"t",
					width:200,
					height:400,
					maxBtn: false,
					allowRightMenu:true,
					handler:updateResPerson,
					afterShow:function(){
						$.post(_PATH + "/oa/oa-meeting!attendee.action",
								{bizEntityId : itemId, attendeeType : _ATT_TYPE_EXIST_RES},
								function(result) {
									$("#attendeeDiv").html(result);
						});
					}
				});
	});
}

function openUserChooseEditMeeting(nameId,cdId,numId,type){
	var config={'nameId':nameId,'cdId':cdId,callBack:function(){
		var cdValue = $("#"+cdId).val();
		var nameValue = $("#"+nameId).val();
	    (numId,type,cdValue);
	    $("#"+numId+type).html(nameValue);
	    $("#"+numId+type).attr("title",nameValue);
	}};
	getMemberJson(config);
}

function updateResPerson(tp) {
	if (tp == "ok") {
		var result = getSelectedResult(existResPerson);
		var itemId = $("#attendeeDiv .bizEntityId").html();
		
		if (result.length > 0) {
			$("#" + itemId + "resPerson").html(result[0].userNames).attr("title", result[0].userNames);
			(itemId, "resPerson", result[0].userIds);
		} else {
			$("#" + itemId + "resPerson").html("").attr("title", "");
			(itemId, "resPerson", "");
		}
	}
}
//管理配合人员
function manageCorPerson(itemId, event) {
	jQuery.Event(event).stopPropagation();
	if (attendee.length == 0) {
		alert("用户池为空！");
		return;
	}
	
	existCorPerson = [];
	$.post(_PATH + "/oa/oa-meeting!fetchPerson.action",
			{id : itemId, attendeeType : _ATT_TYPE_EXIST_COR},
			function(result) {
				if ($.trim(result).length > 0) {
					var strAr = result.split("|");
					for (var i = 0; i < strAr.length; i ++) {
						var j = eval("(" + strAr[i] + ")");
						existCorPerson.push(j);
					}
				}
				
				ymPrompt.confirmInfo({
					icoCls:"",
					title:"选择配合人",
					message:"<div id='attendeeDiv'><img align='absMiddle' src='" + _PATH + "/images/loading.gif'></div>",
					useSlide:true,
					winPos:"t",
					width:200,
					height:400,
					maxBtn: false,
					allowRightMenu:true,
					handler:updateCorPerson,
					afterShow:function(){
						$.post(_PATH + "/oa/oa-meeting!attendee.action",
								{bizEntityId : itemId, attendeeType : _ATT_TYPE_EXIST_COR},
								function(result){
									$("#attendeeDiv").html(result);
						});
					}
				});
	});
}
function updateCorPerson(tp) {
	if (tp == "ok") {
		var result = getSelectedResult(existCorPerson);
		var itemId = $("#attendeeDiv .bizEntityId").html();
		
		if (result.length > 0) {
			$("#" + itemId + "corPerson").html(result[0].userNames).attr("title", result[0].userNames);
			(itemId, "corPerson", result[0].userIds);
		} else {
			$("#" + itemId + "corPerson").html("").attr("title", "");
			(itemId, "corPerson", "");
		}
	}
}
// 选择配合人员
function selectCorPerson() {
	if (attendee.length == 0) {
		alert("用户池为空！");
		return;
	}
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"选择配合人",
		message:"<div id='attendeeDiv'><img align='absMiddle' src='" + _PATH + "/images/loading.gif'></div>",
		useSlide:true,
		winPos:"t",
		width:200,
		height:400,
		maxBtn: false,
		allowRightMenu:true,
		handler:getCorUser,
		afterShow:function(){
			$.post(_PATH + "/oa/oa-meeting!attendee.action?attendeeType=COR",
					function(result){
						$("#attendeeDiv").html(result);
			});
		}
	});
}

function getCorUser(tp) {
	if (tp == "ok") {
		var result = getSelectedResult(corPerson);
		if (result.length > 0) {
			$("#corPersonNames").html(result[0].userNames).attr("title", result[0].userNames);
			$("#corPersonIds").val(result[0].userIds);
		} else {
			$("#corPersonNames").html("").attr("title", "");
			$("#corPersonIds").val("");
		}
	}
}

// 往与会人列表里面加入一个新的用户信息
function addUser(uiid, userName, userList) {
	if (!containsUser(uiid, userList)) {
		userList.push({"uiid" : $.trim(uiid), "userName" : $.trim(userName)});
	}
}
// 从指定列表中删除一个用户
function removeUser(uiid, userList) {
	for (var i = 0; i < userList.length; i ++) {
		if (userList[i].uiid == uiid) {
			userList.splice(i, 1);
			break;
		}
	}
}
// 右侧用户列表里面的用户全部加入与会人列表
function addAll(){
	$("#userDisplay li.userUnSelected").trigger("click");
} 
// 右侧用户列表里面的用户全部移出与会人列表
function delAll(){
	$("#userDisplay li.userSelected").trigger("click");
}
//判断某个用户是否已经在指定的选择列表中
function containsUser(uiid, userList) {
	for (var i = 0; i < userList.length; i ++) {
		if (userList[i].uiid == uiid) {
			return true;
		}
	}
	return false;
}
// 获取被选中人员的信息 id和名字
function getSelectedResult(userList) {
	var result = [];
	if (userList.length > 0) {
		var ids = ";";
		var names = "";
		for (var i = 0; i < userList.length; i ++) {
			ids = ids + userList[i].uiid + ";";
			names = names + userList[i].userName + ";";
		}
		result.push({"userIds" : ids, "userNames" : names});
	}
	return result;
	
}
// 更新目标时间
function enableTargDateInput(itemId, event) {
	jQuery.Event(event).stopPropagation();
	
	var targSpan = $("#" + itemId + "targDate");
	var targInpt = $("#" + itemId + "targetDateInput");
	targSpan.hide();
	targInpt.show();
}
function saveTargDate(itemId) {
	var targSpan = $("#" + itemId + "targDate");
	var targInpt = $("#" + itemId + "targetDateInput");
	
	var newVal = $.trim(targInpt.val());
	if (newVal != "") {
		targInpt.val("").hide();
		targSpan.html(newVal.substr(newVal.indexOf("-") + 1)).show();
		(itemId, "targetDate", newVal);
	}
}
function submitMeeting() {
	$("#inputForm").ajaxSubmit(function() {
		refreshMeetingList();
		resPerson = [];
		corPerson = [];
		viewPerson = [];
	});
}
function cancelInput() {
	$(".add_new").hide();
}
// 保存留言
function saveComment(itemId) {
	var comment = $("#" + itemId + "commentArea").val();
	if ($.trim(comment).length > 0) {
		$.post(_PATH + "/oa/oa-meeting!saveComment.action",
				{
				comment	:	comment,
				id		:	itemId
				},
				function(result) {
					if (result == "done") {
						refreshComment(itemId);
						$("#" + itemId + "commentArea").val("");
					}
		});
	} else {
		alert("请输入评论后再提交");
	}
}
//删除留言
function deleteComment(itemId,itemIds){

	$.post(_PATH+"/oa/oa-meeting!deleteComment.action",
	{
		id      : itemIds,
		comId   : itemId
	},function (result){
		if(result == "done"){
			refreshComment(itemIds);
			$("#" + itemIds + "commentArea").val("");
		}
	});
	
}





// 更新留言记录
function refreshComment(itemId) {
	var target = $("#" + itemId + "commentDiv");
	$.post(_PATH + "/oa/oa-meeting!fetchComments.action?id=" + itemId, function(result) {
		if (result) {
			target.html(result);
			refreshUpdatedDate(itemId);
		}	
	});
}
// 附件管理
function attachManage(bizEntityId, event) {
	jQuery.Event(event).stopPropagation();
	
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"附件管理",
		message:"<div id='attachDiv'><img align='absMiddle' src='" + _PATH + "/images/loading.gif'></div>",
		useSlide:true,
		winPos:"t",
		width:400,
		height:400,
		maxBtn: false,
		allowRightMenu:true,
		afterShow:function(){
			$.post(_PATH + "/oa/oa-meeting!attachMents.action?bizEntityId=" + bizEntityId,
					function(result){
						$("#attachDiv").html(result);
			});
		}
	});
}



//刷新页面上附件信息
function refreshUpdatedDate(itemId) {
	var targ = $("#" + itemId + "updatedDateSpan");
	var targNext = $("#" + itemId + "fileSpan");
	if (targ) {
		$.post(_PATH + "/oa/oa-meeting!fetchUpdatedDate.action?id=" + itemId, function(result) {
			if (result) {
//			    var resultLast = result.charAt(result.length -1);
//			    if(resultLast=="1"){
//			    	targNext.html(_PATH+"/resources/images/common/atta_y.gif");
//			    }else{
//			    	targNext.html(_PATH+"/resources/images/common/atta.gif");
//			    }
				targ.html(result);
			}
		});
	}
}

//上传附件，上传完毕后更新附件区域信息
function uploadAttachment() {
	var uploadedAttach = $("#uploadInput").val();
	if (jQuery.trim(uploadedAttach).length == 0) {
		return false;
	}
	var bizEntityId = $("#attachForm input:hidden[name='bizEntityId']").val();
	$("#attachForm").ajaxSubmit(function(result) {
		if (result) {
			refreshAttachment(bizEntityId, "1");
			var fileName = uploadedAttach.replace(/\\/g,"/").replace(/(.*\/)(.*)/,"$2");
			addAttaLog(bizEntityId,"",fileName,"add");
		}
	});
}
//删除附件，删除完毕后更新附件管理页面
function deleteAttachment(fileId, bizModuleCd, bizEntityId) {
	
	var url = _PATH + "/app/app-attachment!delete.action";
	$.post(url,
		{
			id:				fileId,
			bizModuleCd: 	"oaMeeting",
			bizEntityId:	bizEntityId
		},
		function(result) {
			if (result) {
				refreshAttachment(bizEntityId, "1");
				addAttaLog(bizEntityId,fileId,"","del");
			}
		}
	);
	return false;
}
function addAttaLog(bizEntityId,fileId,fileName,flag){
	var url = _PATH + "/oa/oa-meeting!attachLog.action";
	$.post(url,{
		id:bizEntityId,
		fileId:fileId,
		fileName:fileName,
		flag:flag
	});
}
// 更新附件管理页面信息
function refreshAttachment(bizEntityId, attUpdateFlg) {
	var url = _PATH + "/oa/oa-meeting!attachMents.action";
	$.post(url,
			{
				bizEntityId : bizEntityId,
				isAttachUpdated : attUpdateFlg
			},
			function(result) {
				$("#attachDiv").html(result);
				refreshUpdatedDate(bizEntityId);
			}
	);
}

function enableBusinessInput(itemId, event) {
	jQuery.Event(event).stopPropagation();
	$("#" + itemId + "business").hide();
	$("#" + itemId + "businessInput").show().focus();
	$("#" + itemId + "_detalTr").show();
	return false;
}
function enableWaterNumInput(itemId, event) {
	jQuery.Event(event).stopPropagation();
	$("#" + itemId + "waterNum").hide();
	$("#" + itemId + "waterNumInput").show().focus();
	$("#" + itemId + "_detalTr").show();
	return false;
}
function enableResponsiblePersonInput(itemId, event) {
	jQuery.Event(event).stopPropagation();
	$("#" + itemId + "responsiblePerson").hide();
	$("#" + itemId + "responsiblePersonInput").show().focus();
	$("#" + itemId + "_detalTr").show();
	return false;
}
function enableCoordinatePersonInput(itemId, event) {
	jQuery.Event(event).stopPropagation();
	$("#" + itemId + "coordinatePerson").hide();
	$("#" + itemId + "coordinatePersonInput").show().focus();
	$("#" + itemId + "_detalTr").show();
	return false;
}

function updateBusiness(itemId) {
	var busEle = $("#" + itemId + "business");
	var busInp = $("#" + itemId + "businessInput");
	
	var oldVal = $.trim(busEle.text());
	var newVal = $.trim(busInp.val());
	
	$("#" + itemId + "businessInput").hide();
	if (oldVal != newVal) {
		(itemId, "business", newVal);
		busEle.html(newVal).show();
		$("#" + itemId + "doneInfo").show().fadeOut(1000);
	} else {
		busEle.show();
	}
}
function updateWaterNumber(itemId) {
	var busEle = $("#" + itemId + "waterNum");
	var busInp = $("#" + itemId + "waterNumInput");
	
	var oldVal = $.trim(busEle.text());
	var newVal = $.trim(busInp.val());
	
	$("#" + itemId + "waterNumInput").hide();
	if (oldVal != newVal) {
		(itemId, "waterNumber", newVal);
		busEle.html(newVal).show();
		$("#" + itemId + "doneInfo").show().fadeOut(1000);
	} else {
		busEle.show();
	}
}

// 异步保存会议记录信息
function updateMeeting(itemId, type, newVal,dom) {
	var url = _PATH + "/oa/oa-meeting!save.action";
	var param = null;
	var msg = "";
	var wn = $("#" + itemId + "waterNum").html();
	switch (type) {
		case "waterNumber" :
			param = {"id" : itemId, "waterNumber" : newVal,type:"waterNumber"};
			msg = "编号修改成功";
			break;
		case "business" :
			param = {"id" : itemId, "business" : newVal,type:"business"};
			msg = "事项信息修改成功";
			break;
		case "resPerson" :
			param = {"id" : itemId, "responsiblePerson" : newVal,type:"resPerson"};
			msg = "责任人信息修改成功";
			break;
		case "corPerson" :
			param = {"id" : itemId, "coordinatePerson" : newVal,type:"corPerson"};
			msg = "配合人信息修改成功";
			break;
		case "targetDate" :
			param = {"id" : itemId, "targetDate" : newVal,type:"targetDate"};
			msg = "目标日期修改成功";
			break;
		case "preComplete" :
			param = {"id" : itemId, "status" : newVal,type:"preComplete"};
			msg = "已设置为预完成状态";
			break;
		case "complete" :
			param = {"id" : itemId, "status" : newVal,type:"complete"};
			msg = "已设置为完成状态";
			break;
		case "revert" :
			param = {"id" : itemId, "status" : newVal,type:"revert"};
			msg = "已恢复为进行中状态";
			break; 
		case "delete" :
			if (confirm("确认删除？")) {
				param = {"id" : itemId, "isDeleted" : newVal,type:"delete"};
				msg = "已删除";
			}
			break;
		case "hide" :
			param = {"id" : itemId, "hiddenFlg" : newVal,type:"hide"};
			msg = "已设置为隐藏";
			break;
		case "show" :
			param = {"id" : itemId, "hiddenFlg" : newVal,type:"show"};
			msg = "已设置为被显示";
			break;
	}
	
	if (param) {
		$.post(url, param, function(result) {
			if (result == "done") {
				$("#succInfoMsg").html(msg).show().fadeOut(2000);
				switch (type) {
					case "delete" :
					case "hide" :
					case "show" :
						refreshMeetingList();
						break;
					case "revert" :
						$("#" + itemId + "StatusTd").attr("title", "进行中");
						$("#" + itemId + "StatusFlg").attr("src", _PATH + "/images/plan/pic_confirm.gif");
						break;
					case "preComplete" :
						$("#" + itemId + "StatusTd").attr("title", "预完成");
						$("#" + itemId + "StatusFlg").attr("src", _PATH + "/images/plan/pic_preFinish.gif");
						break;
					case "complete" :
						$("#" + itemId + "StatusTd").attr("title", "已完成");
						$("#" + itemId + "StatusFlg").attr("src", _PATH + "/images/plan/pic_finish.gif");
						break;
					default:
						refreshUpdatedDate(itemId);
						break;
				}
			}
		});
	}
}

//保存记录
function saveMeeting(itemId) {
	var url = _PATH + "/oa/oa-meeting!save.action";
	var v2 = $("#" + itemId + "businessInput").val();
	var v6 = $("#" + itemId + "targetDateInput").val();
	var param = {
			"id" : itemId,
			"business" : v2,
			"targetDate" : v6
	};
	$.post(url, param, function(result) {
		refreshUpdatedDate(itemId);
		$("#succInfoMsg").html("保存成功").show().fadeOut(2000);
	});
}
//保存记录(管理员)
function saveMeetingV5(itemId) {
	var url = _PATH + "/oa/oa-meeting!save.action";
	var v1 = $("#" + itemId + "waterNumInput").val();
	var v2 = $("#" + itemId + "businessInput").val();
	var v3 = $("#" + itemId + "responsiblePersonCds").val();
	var v4 = $("#" + itemId + "coordinatePersonCds").val();
	var v5 = $("#" + itemId + "visibleToUsersCds").val();
	var v6 = $("#" + itemId + "targetDateInput").val();
	var param = {
			"id" : itemId,
			"waterNumber" : v1,
			"business" : v2,
			"responsiblePerson" : v3,
			"coordinatePerson" : v4,
			"visibleToUsers" : v5,
			"targetDate" : v6
	};
	$.post(url, param, function(result) {
		refreshUpdatedDate(itemId);
		$("#succInfoMsg").html("保存成功").show().fadeOut(2000);
	});
}

// 选择可以查看会议记录的用户
function usersCanView() {
	if (attendee.length == 0) {
		alert("用户池为空！");
		return;
	}
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"选择可以查看的人员",
		message:"<div id='viewPersonDiv'><img align='absMiddle' src='" + _PATH + "/images/loading.gif'></div>",
		useSlide:true,
		winPos:"t",
		width:200,
		height:400,
		maxBtn: false,
		allowRightMenu:true,
		handler:getViewUser,
		afterShow:function(){
			$.post(_PATH + "/oa/oa-meeting!attendee.action?attendeeType=VIEW",
					function(result){
						$("#viewPersonDiv").html(result);
			});
		}
	});
}
function getViewUser(tp) {
	if (tp == "ok") {
		var result = getSelectedResult(viewPerson);
		if (result.length > 0) {
			$("#visibleToUsers").val(result[0].userIds);
		} else {
			result = getSelectedResult(attendee);
			$("#visibleToUsers").val(result[0].userIds);
		}
	}
}
// 初始化管理用户池对象
function initUserManagerPool() {
	attendee = [];
	$.post(_PATH + "/oa/oa-meeting!fetchUserManagerPool.action", function(result) {
		var userStr1="";
		var userStr2="";
		var strAr = result.split("|");
		for (var i = 0; i < strAr.length; i ++) {
			if (jQuery.trim(strAr[i]).length > 0) {
				var j = eval("(" + strAr[i] + ")");
				userStr1 += j["uiid"]+";";
				userStr2 += j["userName"]+";";
				attendee.push(j);
			}
		}
		$('#select_manager_cd').val(userStr1);
		$('#select_manager').val(userStr2);
	});
}

//初始化所有用户池对象
function initUserAllPool() {
	attendeeAll = [];
	$.post(_PATH + "/oa/oa-meeting!fetchUserAllPool.action", function(result) {
		var strAr = result.split("|");
		for (var i = 0; i < strAr.length; i ++) {
			if (jQuery.trim(strAr[i]).length > 0) {
				var j = eval("(" + strAr[i] + ")");
				attendeeAll.push(j);
			}
		}
	});
}



// 初始化个人用户池对象
function initUserPersonPool() {
	person = [];
	$.post(_PATH + "/oa/oa-meeting!fetchUserPersonPool.action", function(result) {
		var userStr1="";
		var userStr2="";
		var strAr = result.split("|");
		for (var i = 0; i < strAr.length; i ++) {
			if (jQuery.trim(strAr[i]).length > 0) {
				var j = eval("(" + strAr[i] + ")");
				userStr1 += j["uiid"]+";";
				userStr2 += j["userName"]+";";
				person.push(j);
			}
		}
		$('#select_personal_cd').val(userStr1);
		$('#select_personal').val(userStr2);
	});
}



// 翻页函数
function jumpTo(pageNo) {
	if ($.trim(pageNo) == "") {
		return;
	}
	var p = parseInt(pageNo);
	var t = 99999;
	
	if (p && t) {
		if (p <= 0 || p > t) {
			alert("请输入1至" + t + "的数字");
			return;
		}
		
		$("#pagePageNo").val(pageNo);
		$("#searchMode").val("");
		refreshMeetingList();
	}
	
	
}
// 提醒功能, 给负责人和协助人发信
function remind(itemId) {
	$.post(_PATH + "/oa/oa-meeting!remind.action?id=" + itemId, function(result) {
		if (result == "done") {
			$("#succInfoMsg").html("提醒邮件已发送").show().fadeOut(2000);
		}
	});
}
// 点击搜索按钮显示或隐藏搜索域
function showSearch() {
	var s = $("#searchPanel");
	var ex1 = $("#btnExportExec1");
	ex1.css("display");
	var ex = $("#btnExportExec");
	ex.css("display");
	if (s.css("display") == "none") {
		s.show();
		ex.hide();
		ex1.show();
	} else {
		s.hide();
		ex.show();
		ex1.hide();
	}
}
//导出指令明细
function exportExecOMeeting(changeStats){
	window.open(_ctx + "/oa/oa-meeting!exportExecOMeeting.action?changeStats="+changeStats+"&changeFlag="+changeFlag);
}
// 点击搜索进行搜索操作
function searchMeetingList() {
	//window.location.href=window.location.href;
	//$('#searchForm').submit();
	isRefreshing = true;
	$("#meetingListDiv").html("").addClass("waiting");
	$('#searchForm').ajaxSubmit(function(result) {
		if (result) {
			$("#meetingListDiv").removeClass("waiting").html(result);
			$("#mainPager").html($("#hiddenPager").html());
			$("#hiddenPager").html("");
			isRefreshing = false;
		}
	});
	changeFlag = true;
//	if (isRefreshing) {
//		return;
//	}
//	$("#pagePageNo").val("1");
//	refreshMeetingList();
}
// 刷新会议记录列表
function refreshMeetingList() {
	isRefreshing = true;
	$("#meetingListDiv").html("").addClass("waiting");
	//window.location.href=window.location.href;
	$('#searchForm').ajaxSubmit(function(result) {
		if (result) {
			$("#meetingListDiv").removeClass("waiting").html(result);
			$("#mainPager").html($("#hiddenPager").html());
			$("#hiddenPager").html("");
			isRefreshing = false;
		}
	});
}
//排序
function order(type) {
	var oldOrderBy = $("#orderByParam").val();
	var oldOrder = $("#orderParam").val();
	
	if (type == oldOrderBy) {
		if (oldOrder == "asc") {
			$("#orderParam").val("desc");
		} else {
			$("#orderParam").val("asc");
		}
	} else {
		$("#orderByParam").val(type);
		$("#orderParam").val("asc");
	}
	
	refreshMeetingList();
}
function resetLayout() {
	var totalHeight = parent.$("#ceomeeting_frame").height();
	if (totalHeight) {
		$("div.wrapper").height(totalHeight - 60);
		$("#specialTaskContainer").height(totalHeight - 70);
	} else {
		totalHeight = $(document).height();
		$("div.wrapper").height(totalHeight - 60);
		$("#specialTaskContainer").height(totalHeight - 40);
	}
}
$(function() {
	initUserManagerPool();
	initUserPersonPool();
	initUserAllPool();
	$("a").each(function() {
		$(this).focus(function() {			
			$(this).blur();
		});
	});
	
	resetLayout();
});