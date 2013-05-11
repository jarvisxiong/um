var _PATH = _ctx;
var editorEle = null;
var openItem = null;
var isUpDateRefreshed = false;
var tempPerson = [];
var sendPerson = [];
var resPerson = [];
var corPerson = [];
var existSendPerson = [];
var existResPerson = [];
var existCorPerson = [];
var querySendPerson = [];
var queryResPerson = [];
var _PERSON_TYPE_SEND = "SEND";
var _PERSON_TYPE_RES = "RES";
var _PERSON_TYPE_COR = "COR";
var _PERSON_TYPE_EXIST_SEND = "EXIST_SEND";
var _PERSON_TYPE_EXIST_RES = "EXIST_RES";
var _PERSON_TYPE_EXIST_COR = "EXIST_COR";
var _PERSON_TYPE_QUERY_SEND = "QUERY_SEND";
var _PERSON_TYPE_QUERY_RES = "QUERY_RES";
var oriSearchAction = null;

//切换页面表格的上下箭头
function switchArrow(ele, dir) {
	if (dir == "down") {
		ele.find(".up_arrow").hide();
		ele.find(".down_arrow").show();
	} else {
		ele.find(".up_arrow").show();
		ele.find(".down_arrow").hide();
	}
}

// 点击展开新建块
function triggerNewTaskInput() {
	$(".add_new").each(function() {
		var disp = $(this).css("display");
		if (disp == "none") {
			$(this).show();
		} else {
			$(this).hide();
		}
	});
}
// 创建新任务
function createNewTask() {
	$("#inputForm").ajaxSubmit(function() {
		refreshTaskList();
	});
}
// 显示搜索区
function triggerSearchPanel() {
	var s = $("#searchPanel");
	if (s.css("display") == "none") {
		s.show();
	} else {
		s.hide();
	}
}
//点击搜索进行搜索操作
function searchTaskList() {
	$("#pagePageNo").val("1");
	refreshTaskList();
}
// 新建任务时选择人员(发出人，负责人，配合人)
function selectPerson(pType) {
	
	var title = "";
	switch (pType) {
	case _PERSON_TYPE_QUERY_SEND :
		title = "选择发送人";
		tempPerson = querySendPerson.slice(0);
		break;
	case _PERSON_TYPE_SEND :
		title = "选择发送人";
		tempPerson = sendPerson.slice(0);
		break;
	case _PERSON_TYPE_QUERY_RES :
		title = "选择负责人";
		tempPerson = queryResPerson.slice(0);
		break;
	case _PERSON_TYPE_RES :
		title = "选择负责人";
		tempPerson = resPerson.slice(0);
		break;
	case _PERSON_TYPE_COR :
		title = "选择配合人";
		tempPerson = corPerson.slice(0);
		break;
	}
	
	ymPrompt.confirmInfo({
		icoCls:"",
		title: title,
		message:"<div id='personDiv'><img align='absMiddle' src='" + _PATH + "/images/loading.gif'></div>",
		useSlide:true,
		winPos:"t",
		width:400,
		height:400,
		maxBtn: false,
		allowRightMenu:true,
		handler:savePerson,
		afterShow:function(){
			$.post(_PATH + "/oa/special-task!selPerson.action?personType=" + pType, function(result){
				$("#personDiv").html(result);
				if (tempPerson.length > 0) {
					$("#deptDisplay").html("已选择人员");
					$("#userDisplay").empty();
					for (var i = 0; i < tempPerson.length; i ++) {
						bindPersonEvents(tempPerson[i], "userDisplay", pType, true);
					}
				} else {
					$("#userDisplay li").each(function(){
						var obj = {"uiid" : $(this).attr("id"), "userName" : $(this).text()};
						bindPersonEvents(obj, "userDisplay", pType, false);
					});
				}
			});
		}
	});
}
function savePerson(tp){
	if (tp=="ok") {
		var personType = $("#personType").val();
		var result = [];
		var targNames = null;
		var targIds = null;
		
		switch (personType) {
			case _PERSON_TYPE_SEND:
				sendPerson = tempPerson.slice(0);
				result = getSelectedResult(sendPerson);
				targNames = $("#sendPersonNames");
				targIds = $("#sendPersonIds");
				break;
			case _PERSON_TYPE_RES:
				resPerson = tempPerson.slice(0);
				result = getSelectedResult(resPerson);
				targNames = $("#resPersonNames");
				targIds = $("#resPersonIds");
				break;
			case _PERSON_TYPE_COR:
				corPerson = tempPerson.slice(0);
				result = getSelectedResult(corPerson);
				targNames = $("#corPersonNames");
				targIds = $("#corPersonIds");
				break;
			case _PERSON_TYPE_QUERY_SEND :
				querySendPerson = tempPerson.slice(0);
				result = getSelectedResult(querySendPerson);
				targNames = $("#searchBySendPersonName");
				targIds = $("#searchBySendPersonParam");
				break;
			case _PERSON_TYPE_QUERY_RES :
				queryResPerson = tempPerson.slice(0);
				result = getSelectedResult(queryResPerson);
				targNames = $("#searchByResPersonName");
				targIds = $("#searchByResPersonParam");
				break;
		}
		
		if (result.length > 0) {
			if (personType == _PERSON_TYPE_QUERY_SEND || personType == _PERSON_TYPE_QUERY_RES) {
				targNames.val(result[0].userNames).attr("title", result[0].userNames);
			} else {
				targNames.html(result[0].userNames).attr("title", result[0].userNames);
			}
			targIds.val(result[0].userIds);
		} else {
			if (targNames && targIds) {
				if (personType == _PERSON_TYPE_QUERY_SEND || personType == _PERSON_TYPE_QUERY_RES) {
					targNames.attr("title", "").val("");
				} else {
					targNames.attr("title", "").html("");
				}
				targIds.val("");
			}
		}
	}
}
// 管理已有的人员：发出人，负责人，配合人
function managePerson(taskId, pType, event) {
	jQuery.Event(event).stopPropagation();
	var exPerson = null;
	switch (pType) {
		case _PERSON_TYPE_EXIST_SEND :
			existSendPerson = [];
			exPerson = existSendPerson;
			title = "管理发送人";
			break;
		case _PERSON_TYPE_EXIST_RES :
			existResPerson = [];
			exPerson = existResPerson;
			title = "管理负责人";
			break;
		case _PERSON_TYPE_EXIST_COR :
			existCorPerson = [];
			exPerson = existCorPerson;
			title = "管理配合人";
			break;
	}
	
	$.post(_PATH + "/oa/special-task!fetchPerson.action",
			{id : taskId, personType : pType},
			function(result) {
				if ($.trim(result).length > 0) {
					var strAr = result.split("|");
					for (var i = 0; i < strAr.length; i ++) {
						var j = eval("(" + strAr[i] + ")");
						exPerson.push(j);
					}
				}
				
				ymPrompt.confirmInfo({
					icoCls:"",
					title: title,
					message:"<div id='personDiv'><img align='absMiddle' src='" + _PATH + "/images/loading.gif'></div>",
					useSlide:true,
					winPos:"t",
					width:400,
					height:400,
					maxBtn: false,
					allowRightMenu:true,
					handler:updatePerson,
					afterShow:function(){
						$.post(_PATH + "/oa/special-task!selPerson.action?bizEntityId=" + taskId + "&personType=" + pType, function(result){
							$("#personDiv").html(result);
							if (exPerson.length > 0) {
								$("#deptDisplay").html("已选择人员");
								$("#userDisplay").empty();
								for (var i = 0; i < exPerson.length; i ++) {
									bindPersonEvents(exPerson[i], "userDisplay", pType, true);
								}
							} else {
								$("#userDisplay li").each(function(){
									var obj = {"uiid" : $(this).attr("id"), "userName" : $(this).text()};
									bindPersonEvents(obj, "userDisplay", pType, false);
								});
							}
						});
					}
				});
	});
}
function updatePerson(tp) {
	if (tp == "ok") {
		var pType = $("#personType").val();
		var taskId = $("#personTaskId").val();
		var result = null;
		
		switch (pType) {
			case _PERSON_TYPE_EXIST_SEND :
				result = getSelectedResult(existSendPerson);
				if (result && result.length > 0) {
					$("#" + taskId + "sendPerson").html(result[0].userNames).attr("title", result[0].userNames);
					updateTask(taskId, "sendPerson", result[0].userIds);
				} else {
					$("#" + taskId + "sendPerson").html("").attr("title", "");
					updateTask(taskId, "sendPerson", "");
				}
				
				break;
			case _PERSON_TYPE_EXIST_RES :
				result = getSelectedResult(existResPerson);
				if (result && result.length > 0) {
					$("#" + taskId + "resPerson").html(result[0].userNames).attr("title", result[0].userNames);
					updateTask(taskId, "resPerson", result[0].userIds);
				} else {
					$("#" + taskId + "resPerson").html("").attr("title", "");
					updateTask(taskId, "resPerson", "");
				}
				
				break;
			case _PERSON_TYPE_EXIST_COR :
				result = getSelectedResult(existCorPerson);
				if (result && result.length > 0) {
					$("#" + taskId + "corPerson").html(result[0].userNames).attr("title", result[0].userNames);
					updateTask(taskId, "corPerson", result[0].userIds);
				} else {
					$("#" + taskId + "corPerson").html("").attr("title", "");
					updateTask(taskId, "corPerson", "");
				}
				
				break;
		}
	}
}
//显示已经选择的人员
function showSelectedUser(){
	$("#userDisplay").empty();
	$("#deptDisplay").text("已选择人员");
	var personType = $("#personType").val();
	var personList = [];
	
	switch (personType) {
		case _PERSON_TYPE_SEND :
		case _PERSON_TYPE_RES :
		case _PERSON_TYPE_COR :
		case _PERSON_TYPE_QUERY_SEND :
		case _PERSON_TYPE_QUERY_RES :
			personList = tempPerson;
			break;
		case _PERSON_TYPE_EXIST_SEND :
			personList = existSendPerson;
			break;
		case _PERSON_TYPE_EXIST_RES :
			personList = existResPerson;
			break;
		case _PERSON_TYPE_EXIST_COR :
			personList = existCorPerson;
			break;
	}
	
	$.each(personList, function(i,n) {
		bindPersonEvents(n, "userDisplay", personType, true);
	});
}
function bindPersonEvents(obj, targetId, type, addFlag) {
	if (addFlag) {
		$("#" + targetId).append("<li class='userUnSelected' id="+obj.uiid+">"+obj.userName+"</li>");
	}
	
	$("#"+obj.uiid).toggle(function(){
		$(this).attr("class","userSelected");
		
		switch (type) {
			case _PERSON_TYPE_SEND :
			case _PERSON_TYPE_RES :
			case _PERSON_TYPE_COR :
			case _PERSON_TYPE_QUERY_SEND :
			case _PERSON_TYPE_QUERY_RES :
				addUser(obj.uiid, obj.userName, tempPerson);
				break;
			case _PERSON_TYPE_EXIST_SEND :
				addUser(obj.uiid, obj.userName, existSendPerson);
				break;
			case _PERSON_TYPE_EXIST_RES :
				addUser(obj.uiid, obj.userName, existResPerson);
				break;
			case _PERSON_TYPE_EXIST_COR :
				addUser(obj.uiid, obj.userName, existCorPerson);
				break;
		}
	},function(){
		$(this).attr("class","userUnSelected");
		
		switch (type) {
			case _PERSON_TYPE_SEND :
			case _PERSON_TYPE_RES :
			case _PERSON_TYPE_COR :
			case _PERSON_TYPE_QUERY_SEND :
			case _PERSON_TYPE_QUERY_RES :
				removeUser(obj.uiid, tempPerson);
				break;
			case _PERSON_TYPE_EXIST_SEND :
				removeUser(obj.uiid, existSendPerson);
				break;
			case _PERSON_TYPE_EXIST_RES :
				removeUser(obj.uiid, existResPerson);
				break;
			case _PERSON_TYPE_EXIST_COR :
				removeUser(obj.uiid, existCorPerson);
				break;
		}
	});
	switch (type) {
		case _PERSON_TYPE_SEND :
		case _PERSON_TYPE_RES :
		case _PERSON_TYPE_COR :
		case _PERSON_TYPE_QUERY_SEND :
		case _PERSON_TYPE_QUERY_RES :
			if (containsUser(obj.uiid, tempPerson)) {
				$("#" + obj.uiid).trigger("click");
			}
			break;
		case _PERSON_TYPE_EXIST_SEND :
			if (containsUser(obj.uiid, existSendPerson)) {
				$("#" + obj.uiid).trigger("click");
			}
			break;
		case _PERSON_TYPE_EXIST_RES :
			if (containsUser(obj.uiid, existResPerson)) {
				$("#" + obj.uiid).trigger("click");
			}
			break;
		case _PERSON_TYPE_EXIST_COR :
			if (containsUser(obj.uiid, existCorPerson)) {
				$("#" + obj.uiid).trigger("click");
			}
			break;
	}
}
//右侧用户列表里面的用户全部加入与会人列表
function addAll() {
	$("#userDisplay li.userUnSelected").trigger("click");
}
// 右侧用户列表里面的用户全部移出与会人列表
function delAll() {
	$("#userDisplay li.userSelected").trigger("click");
}
// 向指定的人员列表中加入一条新的人员
function addUser(uiid, userName, personList) {
	if (!containsUser(uiid, personList)) {
		personList.push({"uiid" : uiid, "userName" : jQuery.trim(userName)});
	}
}
// 从指定的人员列表中删除一个人员
function removeUser(uiid, userList) {
	for (var i = 0; i < userList.length; i ++) {
		if (userList[i].uiid == uiid) {
			userList.splice(i, 1);
			break;
		}
	}
}
//判断某个用户是否已经在指定的人员列表中
function containsUser(uiid, userList) {
	for (var i = 0; i < userList.length; i ++) {
		if (userList[i].uiid == uiid) {
			return true;
		}
	}
	return false;
}
//获取被选中人员的信息 id和名字
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
//搜索用户
var isSearching = false;
function getUser(dom) {
	var domValue = $.trim(dom.value);
	if (domValue == "" || isSearching) {
		return;
	}
	$("#userDisplay").empty().addClass("person_waiting");
	isSearching = true;
	$.post(_PATH + "/oa/special-task!getUsersByFilter.action", {value:domValue}, function(result){
		if (result) {
			$("#userDisplay").removeClass("person_waiting");
			$("#deptDisplay").text("搜索结果");
			result = eval(result);
			$.each(result, function(i, n){
				bindPersonEvents(n, "userDisplay", $("#personType").val(), true);
			});
			isSearching = false;
		}
	});
}
//附件管理
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
			$.post(_PATH + "/oa/special-task!attachMents.action?bizEntityId=" + bizEntityId,
					function(result){
						$("#attachDiv").html(result);
			});
		}
	});
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
function deleteAttachment(fileId, bizEntityId) {
	var url = _PATH + "/app/app-attachment!delete.action";
	$.post(url,
		{
			id:				fileId,
			bizModuleCd: 	"specialTask",
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
	var url = _PATH + "/oa/special-task!attachLog.action";
	$.post(url,{
		id:bizEntityId,
		fileId:fileId,
		fileName:fileName,
		flag:flag
	});
}
// 更新附件管理页面信息
function refreshAttachment(bizEntityId, attUpdateFlg) {
	var url = _PATH + "/oa/special-task!attachMents.action";
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
//保存留言
function saveComment(taskId) {
	var comment = $("#" + taskId + "commentArea").val();
	if ($.trim(comment).length > 0) {
		$.post(_PATH + "/oa/special-task!saveComment.action",
				{
					comment	:	comment,
					id		:	taskId
				},
				function(result) {
					if (result == "done") {
						$("#" + taskId + "commentArea").val("");
						refreshComment(taskId);
					}
		});
	} else {
		alert("请输入评论后再提交");
	}
}
// 更新留言记录
function refreshComment(taskId) {
	var target = $("#" + taskId + "commentDiv");
	$.post(_PATH + "/oa/special-task!fetchComments.action?id=" + taskId, function(result) {
		if (result) {
			target.html(result);
			refreshUpdatedDate(taskId);
		}	
	});
}
// 激活业务编号编辑框
function enableWatNumEdit(taskId, event) {
	jQuery.Event(event).stopPropagation();
	$("#" + taskId + "waterNum").hide();
	$("#" + taskId + "waterNumInput").show().focus();
	return false;
}
// 失去焦点时保存业务编号
function updateWaterNum(taskId) {
	var ele = $("#" + taskId + "waterNum");
	var inp = $("#" + taskId + "waterNumInput");
	
	var oldVal = $.trim(ele.text());
	var newVal = $.trim(inp.val());
	
	$("#" + taskId + "waterNumInput").hide();
	if (oldVal != newVal) {
		updateTask(taskId, "waterNum", newVal);
		ele.html(newVal).show();
	} else {
		ele.show();
	}
}
// 激活标题编辑框
function enableSubEdit(taskId, event) {
	jQuery.Event(event).stopPropagation();
	$("#" + taskId + "subject").hide();
	$("#" + taskId + "subjectInput").show().focus();
	return false;
}
// 保存标题
function updateSubject(taskId) {
	var ele = $("#" + taskId + "subject");
	var inp = $("#" + taskId + "subjectInput");
	
	var oldVal = $.trim(ele.text());
	var newVal = $.trim(inp.val());
	
	inp.hide();
	if (oldVal != newVal) {
		updateTask(taskId, "subject", newVal);
		ele.html(newVal).show();
	} else {
		ele.show();
	}
}
//激活详细信息编辑域
function enableDetailEdit(taskId, event) {
	jQuery.Event(event).stopPropagation();
	$("#" + taskId + "detail").hide();
	$("#" + taskId + "detailInput").show().focus();
	return false;
}
// 保存标题
function updateDetail(taskId) {
	var ele = $("#" + taskId + "detail");
	var inp = $("#" + taskId + "detailInput");
	
	var oldVal = $.trim(ele.text());
	var newVal = $.trim(inp.val());
	
	inp.hide();
	if (oldVal != newVal) {
		updateTask(taskId, "detail", newVal);
		ele.html("<pre>" + newVal + "</pre>").show();
	} else {
		ele.show();
	}
}
// 激活目标时间输入控件
function enableTargDateInput(taskId, event) {
	jQuery.Event(event).stopPropagation();
	
	var targSpan = $("#" + taskId + "targDate");
	var targInpt = $("#" + taskId + "targetDateInput");
	targSpan.hide();
	targInpt.show();
}
// 保存新的目标时间
function saveTargDate(taskId) {
	var targSpan = $("#" + taskId + "targDate");
	var targInpt = $("#" + taskId + "targetDateInput");
	
	var newVal = $.trim(targInpt.val());
	if (newVal != "") {
		targInpt.val("").hide();
		targSpan.html(newVal.substr(newVal.indexOf("-") + 1)).show();
		updateTask(taskId, "targetDate", newVal);
	}
}
//异步保存专项任务信息
function updateTask(taskId, type, newVal) {
	var url = _PATH + "/oa/special-task!save.action";
	var param = null;
	var msg = "";
	switch (type) {
		case "waterNum" :
			param = {"id" : taskId, "waterNum" : newVal,type:"waterNum"};
			msg = "标题修改成功";
			break;
		case "subject" :
			param = {"id" : taskId, "subject" : newVal,type:"subject"};
			msg = "标题修改成功";
			break;
		case "detail" :
			param = {"id" : taskId, "detail" : newVal,type:"detail"};
			msg = "详细内容修改成功";
			break;
		case "sendPerson" :
			param = {"id" : taskId, "sendPerson" : newVal,type:"sendPerson"};
			msg = "责任人修改成功";
			break;
		case "resPerson" :
			param = {"id" : taskId, "responsiblePerson" : newVal,type:"resPerson"};
			msg = "责任人修改成功";
			break;
		case "corPerson" :
			param = {"id" : taskId, "coordinatePerson" : newVal,type:"corPerson"};
			msg = "配合人修改成功";
			break;
		case "targetDate" :
			param = {"id" : taskId, "targetDate" : newVal,type:"targetDate"};
			msg = "目标日期修改成功";
			break;
		case "preComplete" :
			param = {"id" : taskId, "status" : newVal,type:"preComplete"};
			msg = "已设置为预完成状态";
			break;
		case "complete" :
			param = {"id" : taskId, "status" : newVal,type:"complete"};
			msg = "已设置为完成状态";
			break;
		case "revert" :
			param = {"id" : taskId, "status" : newVal,type:"revert"};
			msg = "已恢复为进行中状态";
			break; 
		case "delete" :
			if (confirm("确认删除？")) {
				param = {"id" : taskId, "deletedFlg" : newVal,type:"delete"};
				msg = "删除成功";
			}
			break;
		case "hide" :
			param = {"id" : taskId, "hiddenFlg" : newVal,type:"hide"};
			msg = "已设置为隐藏";
			break;
		case "show" :
			param = {"id" : taskId, "hiddenFlg" : newVal,type:"show"};
			msg = "已设置为显示";
			break;
	}
	
	if (param) {
		$.post(url, param, function(result) {
			if (result == "done") {
				displaySuccInfo(msg);
				switch (type) {
					case "delete" :
					case "hide" :
					case "show" :
						refreshTaskList();
						break;
					case "revert" :
						$("#" + taskId + "StatusTd").attr("title", "进行中");
						$("#" + taskId + "StatusFlg").attr("src", _PATH + "/images/plan/pic_confirm.gif");
						break;
					case "preComplete" :
						$("#" + taskId + "StatusTd").attr("title", "预完成");
						$("#" + taskId + "StatusFlg").attr("src", _PATH + "/images/plan/pic_preFinish.gif");
						break;
					case "complete" :
						$("#" + taskId + "StatusTd").attr("title", "已完成");
						$("#" + taskId + "StatusFlg").attr("src", _PATH + "/images/plan/pic_finish.gif");
						break;
					default:
						refreshUpdatedDate(taskId);
						break;
				}
			}
		});
	}
}
// 刷新任务列表
function refreshTaskList() {
	$("#taskListDiv").html("").addClass("waiting");
	$('#searchForm').attr("action", oriSearchAction).ajaxSubmit(function(result) {
		if (result) {
			$("#taskListDiv").removeClass("waiting").html(result);
			$("#mainPager").html($("#hiddenPager").html());
			$("#hiddenPager").html("");
		}
	});
}
// 发送提醒邮件
function remind(taskId) {
	
	$.get(_PATH + "/oa/special-task!remind.action?id=" + taskId, function(result) {
		if (result == "done") {
			displaySuccInfo("提醒邮件已发送");
		}
	});
}
// 展示操作成功信息
function displaySuccInfo(msg) {
	$("#succInfoMsg").html(msg).show().fadeOut(2000);
}
// 排序
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
	
	refreshTaskList();
}
//翻页函数
function jumpTo(pageNo) {
	if ($.trim(pageNo) == "") {
		return;
	}

	var p = parseInt(pageNo);
	var t = parseInt($("#totalPageSize").val());
	
	
	if (p && t) {
		if (p <= 0 || p > t) {
			alert("请输入1至" + t + "的数字");
			return;
		}
		
		$("#pagePageNo").val(pageNo);
		refreshTaskList();
	}	
}
// 刷新页面上指定记录的更新时间
function refreshUpdatedDate(itemId) {
	var targ = $("#" + itemId + "updatedDateSpan");
	if (targ) {
		$.post(_PATH + "/oa/special-task!fetchUpdatedDate.action?id=" + itemId, function(result) {
			if (result) {
				targ.html(result);
			}
		});
	}
}
function exportResult(srcEle) {
	var oriAction = $("#searchForm").attr("action");
	$("#searchForm").attr("action", srcEle.attr("href"));
	$("#searchForm").submit();
	$("#searchForm").attr("action", oriAction);
}
function resetLayout() {
	var totalHeight = parent.parent.$("#ceomeeting_frame").height();
	if (totalHeight) {
		$("div.wrapper").height(totalHeight - 140);
	} else {
		totalHeight = parent.$("html").height();
		$("div.wrapper").height(totalHeight - 100);
	}
}
$(function() {
	oriSearchAction = $("#searchForm").attr("action");
	resetLayout();
});