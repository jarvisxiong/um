var _PATH = _ctx;
var openItem = null;
var isRefreshing = false;

// 显示新增的输入区
function triggerNewTaskInput() {
	var h = $("#addNewFileDiv").height();
	var oh = $("#filelistDiv").height();
	if ($("#addNewFileDiv").css("display") == "none") {
		$("#addNewFileDiv").show();
		$("#filelistDiv").height(oh - h);
	} else {
		$("#addNewFileDiv").hide();
		$("#filelistDiv").height(oh + h);
	}
}
// 显示搜索区
function showSearch() {
	var s = $("#searchPanel");
	var searchHeight = s.height();
	var oriHeight = $("#filelistDiv").height();
	if (s.css("display") == "none") {
		s.show();
		$("#filelistDiv").height(oriHeight - searchHeight);
	} else {
		s.hide();
		$("#filelistDiv").height(oriHeight + searchHeight);
	}
}
// 搜索文件列表
function searchFileList() {
	$("#pagePageNo").val("1");
	if (isRefreshing) {
		return;
	}
	refreshFileList();
}
// 刷新文件列表区域
function refreshFileList() {
	isRefreshing = true;
	
	$("#filelistDiv").addClass("waiting").html("");
	$("#searchForm").ajaxSubmit(function(result) {
		if (result) {
			$("#filelistDiv").removeClass("waiting").html(result);
			$("#mainPager").html($("#hiddenPager").html());
			isRefreshing = false;
			resetWidth();
			$(".receiver").ouSelect({
				muti:false
			});
			$('.sendOrg').ouSelect({type:'org'});
		}
	});
}
// 根据浏览器重新设置高度
function resetHeight() {
	var totalHeight = parent.$("#fileTrack_frame").height();
	if (totalHeight) {
		$("div.wrapper").height(totalHeight - 90);
		$("#filelistDiv").height(totalHeight - 140);
	} else {
		totalHeight = $(document).height();
		$("div.wrapper").height(totalHeight - 70);
		$("#filelistDiv").height(totalHeight - 130);
	}
}
// 根据浏览器重新设置宽度
function resetWidth() {
	var width = $("#filelistHeadDiv").width();
	$("#filelistDiv table.file_list").width(width - 18);
}
//根据浏览器重新设置布局
function resetLayout() {
	resetHeight();
	resetWidth();
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
			$.post(_PATH + "/oa/oa-file-followed!attachMents.action?bizEntityId=" + bizEntityId,
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
			refreshAttachment(bizEntityId);
		}
	});
}
//删除附件，删除完毕后更新附件管理页面
function deleteAttachment(fileId, bizEntityId) {
	var url = _PATH + "/app/app-attachment!delete.action";
	$.post(url,
		{
			id:				fileId,
			bizModuleCd: 	"fileTracking",
			bizEntityId:	bizEntityId
		},
		function(result) {
			if (result) {
				refreshAttachment(bizEntityId);
			}
		}
	);
	return false;
}
// 更新附件管理页面信息
function refreshAttachment(bizEntityId) {
	var url = _PATH + "/oa/oa-file-followed!attachMents.action";
	$.post(url,
			{
				bizEntityId : bizEntityId
			},
			function(result) {
				$("#attachDiv").html(result);
		}
	);
}
// 创建新的文件跟踪记录
function createNewFile() {
	var c = $.trim($("#contentInputArea").val());
	if (c.length == 0) {
		alert("请输入文件名");
		return;
	}
	var proj = $("#projectSnHidden").val();
	if (!proj || $.trim(proj) == "") {
		alert("请选择文件来源");
		return;
	}
	$("#inputForm").ajaxSubmit(function(result) {
		if (result == "done") {
			refreshPage();
		}
	});
}
function refreshPage() {
	this.location.href = _PATH + "/oa/oa-file-followed!list.action";
}
//选择发送人
var curId = null;
function selectPerson(fileId) {
	if (!fileId || $.trim(fileId).length == 0) {
		return;
	}
	curId = fileId;
	var url = _PATH + "/oa/oa-user-selection!selPerson.action";
	temPersons = [];
	var uiid = $("#" + fileId + "receiver").val();
	if (uiid && $.trim(uiid).length > 0) {
		var uname = $("#" + fileId + "receiverName").val();
		var j = eval("({\"uiid\" : \"" + uiid + "\", \"userName\" : \""+ uname + "\"})");
		temPersons.push(j);
	}
	ymPrompt.confirmInfo({
		icoCls:"",
		title: "请选择发送人",
		message:"<div id='personDiv'><img align='absMiddle' src='" + _PATH + "/images/loading.gif'></div>",
		useSlide:true,
		winPos:"t",
		width:400,
		height:400,
		maxBtn: false,
		allowRightMenu:true,
		handler:savePerson,
		afterShow:function(){
			$.post(url, function(result){
				$("#personDiv").html(result);
				if (temPersons.length > 0) {
					$("#deptDisplay").html("已选择人员");
					$("#userDisplay").empty();
					for (var i = 0; i < temPersons.length; i ++) {
						bindPersonEvents(temPersons[i], "userDisplay", true);
					}
				} else {
					$("#userDisplay li").each(function(){
						var obj = {"uiid" : $(this).attr("id"), "userName" : $(this).text()};
						bindPersonEvents(obj, "userDisplay", false);
					});
				}
			});
		}
	});
}
function savePerson(tp) {
	if (tp == "ok") {
		if (temPersons.length == 1) {
			$("#" + curId + "receiver").val(temPersons[0].uiid);
			$("#" + curId + "receiverName").html(temPersons[0].userName);
		} else {
			$("#" + curId + "receiver").val("");
			$("#" + curId + "receiverName").html("");
		}
	}
	temPersons = [];
	curId = null;
}
//绑定事件
function bindPersonEvents(obj, targetId, addFlag) {
	if (addFlag) {
		$("#" + targetId).append("<li class='userUnSelected' id="+obj.uiid+">"+obj.userName+"</li>");
	}
	$("#"+obj.uiid).toggle(function() {
		$("#userDisplay li.userSelected").attr("class", "userUnSelected");
		$(this).addClass("userSelected");
		temPersons = [];
		addUser(obj.uiid, obj.userName, temPersons);
	}, function() {
		$(this).attr("class","userUnSelected");
		removeUser(obj.uiid, temPersons);
	});
	
	if (containsUser(obj.uiid, temPersons)) {
		$("#" + obj.uiid).trigger("click");
	}
}
// 保存留言
function saveComment(id){
	var comment = $.trim($("#" + id + "commentArea").val());
	if (comment.length == 0) {
		alert("请输入留言信息");
		return;
	}
	var param ={id:id, message:comment};
	var url =_PATH + "/oa/oa-file-followed!saveComment.action";
	$.post(url, param, function(result) {
		$("#" + id + "commentDiv").html(result);
		$("#" + id + "commentArea").val("");
	});
}
//发送
function sendPerson(fileId) {
	var receiver = $("#" + fileId + "receiver").val();
	if ($.trim(receiver).length == 0) {
		alert("请选择发送人");
		return;
	}
	var url = _PATH + "/oa/oa-file-followed!sendToNext.action";
	var param = { id : fileId, receiverId : receiver };
	sendReqToServer(url, param);
}
//确认接收
function receive(fileId) {
	var url = _PATH + "/oa/oa-file-followed!receiveFile.action";
	var param = {id : fileId};
	sendReqToServer(url, param);
}
//退回文件
function sendBack(fileId) {
	var url = _PATH + "/oa/oa-file-followed!sendBackFile.action";
	var param = {id : fileId};
	sendReqToServer(url, param);
}
//完成文件
function complete(fileId) {
	var url = _PATH + "/oa/oa-file-followed!completeFile.action";
	var param = {id : fileId};
	sendReqToServer(url, param);
}
//继续流转文件
function contin(fileId) {
	var url = _PATH + "/oa/oa-file-followed!continueFile.action";
	var param = {id : fileId};
	sendReqToServer(url, param);
}
//删除文件
function deleteFile(fileId){
	if(confirm("确定要删除这条记录吗？")){
		var url =_PATH + "/oa/oa-file-followed!delete.action";
		var param = {id : fileId};
		sendReqToServer(url, param);
	}
}
function sendReqToServer(url, param) {
	$.post(url, param, function(result) {
		if (result == "done") {
			refreshFileList();
		}
	});
}
//页面跳转
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
		refreshFileList();
	}	
}
function exportResult(url) {
	var oriAction = $("#searchForm").attr("action");
	$("#searchForm").attr("action", url);
	$("#searchForm").submit();
	$("#searchForm").attr("action", oriAction);
}

var searchTime;
function showSearchUser(dom){
	var $currentDom = $(dom);
	var $next = $(dom).next();
	
	if(searchTime)clearTimeout(searchTime);
	searchTime = setTimeout(function(){
		var val = $.trim($currentDom.val());
		$next.val("");
		if(val == ""){
			$("#searchUserDiv").slideUp(200);
			return;
		}		
		$.post(_PATH + "/oa/oa-email!getUsersByFilter.action",{value:val,maxNum:10}, function(result){
			result = eval(result);
			var $offset = $currentDom.offset();
			$("#searchUserDiv").css({left:$offset.left, top:$offset.top+$currentDom.height()+5}).empty().show();
			$.each(result,function(i,n){
				var html = "<div uiid='"+n.uiid+"'><span>"+n.userName +"</span>|<span>"+ n.orgName+"</span></div>";
				$("#searchUserDiv").append(html);
			});
		
			$("#searchUserDiv div").click(function(){
				var userName = $(this).children("span:first").text();
				var uiid = $(this).attr("uiid");
				$currentDom.val(userName);
				$next.val(uiid);
				$("#searchUserDiv").slideUp(200);
			});
		});
	}, 300);
	
	$(document).bind('click',function(event){
		var event  = window.event || event;
	    var obj = event.srcElement ? event.srcElement : event.target;
	    if(obj != dom){
	    	$("#searchUserDiv").slideUp(200);
	    	if($next.val() == ""){
	    		$currentDom.val("");
	    	}
	    }
	    $(document).unbind('click');
	});
	return false;
}
// 激活文件名编辑框
function enableFileNameEdit(fileId, event) {
	jQuery.Event(event).stopPropagation();
	var id = $.trim(fileId);
	$("#" + id + "FileNameInput").show().focus();
	$("#" + id + "FileNameDiv").hide();
}
// 保存文件名
function saveFileName(fileId) {
	var fileNameInput = $("#" + fileId + "FileNameInput");
	var fileNameDiv = $("#" + fileId + "FileNameDiv");
	var oldFileName = $.trim(fileNameDiv.html());
	var newFileName = $.trim(fileNameInput.val());
	if ($.trim(newFileName) == ""){
		alert("文件名不能为空");
		fileNameInput.val(oldFileName).focus();
		return;
	}
	if (oldFileName == newFileName) {
		fileNameInput.hide();
		fileNameDiv.show();
		return;
	}
	$.post(_PATH + "/oa/oa-file-followed!saveFileName.action",
			{ id:fileId, fileTrackingName:newFileName },
			function(result) {
				if (result == "done") {
					fileNameInput.hide();
					fileNameDiv.attr("title", newFileName).html(newFileName).fadeIn();
					displaySuccInfo("文件"+$("#" + fileId + "serialNumSpan").html()+"文件名修改成功");
				} else {
					fileNameInput.hide();
					fileNameDiv.fadeIn();
					displaySuccInfo("文件"+$("#" + fileId + "serialNumSpan").html()+"文件名修改失败");
				}
	});
}
//展示操作成功信息
function displaySuccInfo(msg) {
	$("#succInfoMsg").html(msg).show().fadeOut(2000);
}
var tempOrgCd = null;
var tempOrgName = null;
var selType = null;
//打开机构选择框
function enableOrgSel(type) {
	tempOrgCd = null;
	tempOrgName = null;
	selType = type;
	
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"选择机构",
		message:"<div id='deptDiv' style='padding-top: 10px;'></div>",
		useSlide:true,
		winPos:"t",
		width:350,
		height:400,
		maxBtn:false,
		allowRightMenu:true,
		handler: selOrgInfo,
		afterShow:function(){
			$.post(_PATH + "/oa/oa-file-followed!buildOrgs.action",
					function(result) {
						if (result) {
							tree = new TreePanel({
								renderTo:'deptDiv',
								'root' : eval('('+result+')'),
								'ctx': _PATH
							});
							tree.render();
							
							tree.on(function(node){
								var id = node.attributes.id;
								if( $.trim(id) == '' || $.trim(id)=='0'){
									tempOrgCd = "";
									tempOrgName = "";
								} else{
									tempOrgCd = node.attributes.id;
									tempOrgName = node.attributes.text;
									
									if(node.isExpand){
										node.collapse();
									}else{
										node.expand();
									}
								}
							});
						}
			});
		}
	});
}
// 保存机构信息
function selOrgInfo(tp) {
	if (tp == "ok") {
		if (tempOrgName != null && tempOrgCd != null) {
			if (selType == "src") {
				$("#searchByProjName").val(tempOrgName);
				$("#searchByProjCd").val(tempOrgCd);
			} else if (selType == "dept") {
				$("#searchByDeptName").val(tempOrgName);
				$("#searchByDeptCd").val(tempOrgCd);
			}
		} else {
			if (selType == "src") {
				$("#searchByProjName").val("");
				$("#searchByProjCd").val("");
			} else if (selType == "dept") {
				$("#searchByDeptName").val("");
				$("#searchByDeptCd").val("");
			}
		}
	}
}

var curFileId = null;
var oriProjectSn ="";
//打开文件来源机构选择框
function enableSrcOrgSel(fileId, e) {
	jQuery.Event(e).stopPropagation();
	
	tempOrgCd = null;
	tempOrgName = null;
	curFileId = fileId;
	$("#"+fileId+"ProjDiv").hide().next().show();
	oriProjectSn =$("#"+fileId+"ProjectSn").val();
	$("#"+fileId+"Proj").orgSelect({
		callback:saveProjectSn(fileId)
	});
	
	/**
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"选择机构",
		message:"<div id='deptDiv' style='padding-top: 10px;'></div>",
		useSlide:true,
		winPos:"t",
		width:350,
		height:400,
		maxBtn:false,
		allowRightMenu:true,
		handler: selSrcOrgInfo,
		afterShow:function(){
			$.post(_PATH + "/oa/oa-file-followed!buildOrgs.action",
					function(result) {
						if (result) {
							tree = new TreePanel({
								renderTo:'deptDiv',
								'root' : eval('('+result+')'),
								'ctx': _PATH
							});
							tree.render();
							
							tree.on(function(node){
								var id = node.attributes.id;
								if( $.trim(id) == '' || $.trim(id)=='0'){
									tempOrgCd = "";
									tempOrgName = "";
								} else{
									tempOrgCd = node.attributes.id;
									tempOrgName = node.attributes.text;
									
									if(node.isExpand){
										node.collapse();
									}else{
										node.expand();
									}
								}
							});
						}
			});
		}
	});*/
}
//保存机构信息
function selSrcOrgInfo(tp) {
	if (tp == "ok") {
		if (curFileId != null) {
			if (confirm("确认修改文件来源吗？")) {
				if (tempOrgCd && $.trim(tempOrgCd).length > 0) {
					$.post(_PATH + "/oa/oa-file-followed!saveProjSn.action",
							{ id:curFileId, fileProjSn:tempOrgCd },
							function(result) {
								if (result == "done") {
									$("#" + curFileId + "ProjDiv").text(tempOrgName);
									displaySuccInfo("文件"+$("#" + curFileId + "serialNumSpan").html()+"文件来源修改成功");
								} else {
									displaySuccInfo("文件"+$("#" + curFileId + "serialNumSpan").html()+"文件来源修改失败");
								}
					});
				}
			}
		} else {
			$("#projNameInput").html(tempOrgName);
			$("#projectSnHidden").val(tempOrgCd);
		}
	}
}
function saveProjectSn(fileId){
	var fileProjSn =$("#"+fileId+"ProjectSn").val();
	if(fileId&&oriProjectSn!=fileProjSn){
		$.post(_PATH + "/oa/oa-file-followed!saveProjSn.action",
				{ id:fileId, fileProjSn:fileProjSn},
				function(result) {
					if (result == "done") {
						//$("#" + fileId + "ProjDiv").text(tempOrgName);
						displaySuccInfo("文件"+$("#" + fileId + "serialNumSpan").html()+"文件来源修改成功");
					} else {
						displaySuccInfo("文件"+$("#" + fileId + "serialNumSpan").html()+"文件来源修改失败");
					}
		});
	}
}

// 清空搜索条件
function clearSearch() {
	$("#searchForm input").val("");
}
function checkAll(src) {
	$("#filelistDiv input[type=checkbox]").attr("checked", $(src).attr("checked"));
}
function batchSend() {
	if ($("#filelistDiv input:checkbox[checked=true]").length == 0) {
		alert("请选择待送出的文件记录");
		return;
	}
	
	var sent = [];
	for (var i = 0; i < $("#filelistDiv input:checkbox[checked=true]").length; i ++) {
		var id = $("#filelistDiv input:checkbox[checked=true]").eq(i).val();
		var rec = $("#" + id + "receiver").val();
		var fileNum = $("#" + id + "serialNumSpan").html();
		if (rec == undefined) {
			alert("文件记录" + fileNum + "不符合发送条件");
			return;
		} else {
			if ($.trim(rec).length == 0) {
				alert("请为文件记录" + fileNum + "选择发送人");
				return;
			} else {
				var param = { id : id, receiverId : rec };
				sent.push(param);
			}
		}
	}
	
	var url = _PATH + "/oa/oa-file-followed!batchSend.action";
	var batchData = "{'data':[";
	for (var i = 0; i < sent.length; i ++){
		batchData += "{'id':'" + sent[i].id + "','receiverId':'" + sent[i].receiverId + "'}";
		if (i < sent.length - 1) {
			batchData += ",";
		}
	}
	batchData += "]}";
	var param = {batchData : batchData};
	$.post(url, param, function(result) {
		if (result == "done") {
			alert("已送出选中的文件");
			refreshFileList();
		}
	});
}
// 批量确认
function batchConfirm() {
	if ($("#filelistDiv input:checkbox[checked=true]").length == 0) {
		alert("请选择待确认的文件记录");
		return;
	}
	var recs = [];
	for (var i = 0; i < $("#filelistDiv input:checkbox[checked=true]").length; i ++) {
		var id = $("#filelistDiv input:checkbox[checked=true]").eq(i).val();
		if ($("#" + id + "receiveBtn").length == 1) {
			var param = {id : id};
			recs.push(param);
		}
	}
	if (recs.length == 0) {
		alert("没有待确认的文件");
		return;
	}
	
	var url = _PATH + "/oa/oa-file-followed!batchConfirm.action";
	var batchData = "{'data':[";
	for (var i = 0; i < recs.length; i ++){
		batchData += "{'id':'" + recs[i].id + "'}";
		if (i < recs.length - 1) {
			batchData += ",";
		}
	}
	batchData += "]}";
	var param = {batchData : batchData};
	$.post(url, param, function(result) {
		if (result == "done") {
			alert("已确认收到选中的文件");
			refreshFileList();
		}
	});
}
// 批量删除
function batchDel() {
	if ($("#filelistDiv input:checkbox[checked=true]").length == 0) {
		alert("请选择待删除的文件记录");
		return;
	}
	var delFiles = [];
	for (var i = 0; i < $("#filelistDiv input:checkbox[checked=true]").length; i ++) {
		var id = $("#filelistDiv input:checkbox[checked=true]").eq(i).val();
		if ($("#" + id + "delBtn").length == 1) {
			var param = {id : id};
			delFiles.push(param);
		} else {
			alert("文件" + $("#" + id + "serialNumSpan").html() + "不能被删除");
			return;
		}
	}
	if (delFiles.length == 0) {
		alert("没有可删除的文件");
		return;
	}
	
	if (confirm("确认删除选中的文件吗？")) {
		var url = _PATH + "/oa/oa-file-followed!batchDelete.action";
		var batchData = "{'data':[";
		for (var i = 0; i < delFiles.length; i ++){
			batchData += "{'id':'" + delFiles[i].id + "'}";
			if (i < delFiles.length - 1) {
				batchData += ",";
			}
		}
		batchData += "]}";
		var param = {batchData : batchData};
		$.post(url, param, function(result) {
			if (result == "done") {
				alert("已删除选中的文件");
				refreshFileList();
			}
		});
	}
}
function checkFile(event) {
	jQuery.Event(event).stopPropagation();
	if ($("#filelistDiv input:checkbox[checked=true]").length == 0) {
		$("#allFilesCheckBox").attr("checked", false);
	}
	if ($("#filelistDiv input:checkbox[checked=true]").length == $("#filelistDiv input:checkbox").length) {
		$("#allFilesCheckBox").attr("checked", true);
	}
}
$(function() {
	resetLayout();
});
function updateRemark(){
	$.post(_PATH + "/oa/oa-file-followed!updateRemark.action",function(result){
		
	});
}
function updatePersonRemark(){
	var project=$("#project_text").val();
	var person =$("#person_sn").val();
	if(""==project||""==person){
		alert("请选择项目或人员");
		return;
	}
	var param ={curUser:person, fileProjSn:project};
	var url =_PATH + "/oa/oa-file-followed!updatePersonRemark.action";
	$.post(url, param, function(result) {
		
	});
}
function person(){
	var url = _PATH + "/oa/oa-user-selection!selPerson.action";
	temPersons = [];
	var uiid = $("#person_sn").val();
	if (uiid && $.trim(uiid).length > 0) {
		var uname = $("#person_name").val();
		var j = eval("({\"uiid\" : \"" + uiid + "\", \"userName\" : \""+ uname + "\"})");
		temPersons.push(j);
	}
	ymPrompt.confirmInfo({
		icoCls:"",
		title: "请选择发送人",
		message:"<div id='personDiv'><img align='absMiddle' src='" + _PATH + "/images/loading.gif'></div>",
		useSlide:true,
		winPos:"t",
		width:400,
		height:400,
		maxBtn: false,
		allowRightMenu:true,
		handler:saveUser,
		afterShow:function(){
			$.post(url, function(result){
				$("#personDiv").html(result);
				if (temPersons.length > 0) {
					$("#deptDisplay").html("已选择人员");
					$("#userDisplay").empty();
					for (var i = 0; i < temPersons.length; i ++) {
						bindPersonEvents(temPersons[i], "userDisplay", true);
					}
				} else {
					$("#userDisplay li").each(function(){
						var obj = {"uiid" : $(this).attr("id"), "userName" : $(this).text()};
						bindPersonEvents(obj, "userDisplay", false);
					});
				}
			});
		}
	});
}
function saveUser(tp){
	if (tp == "ok") {
		if (temPersons.length == 1) {
			$("#person_sn").val(temPersons[0].uiid);
			$("#person_name").val(temPersons[0].userName);
		} else {
			$("#person_sn").val("");
			$("#person_name").val("");
		}
	}
	temPersons = [];
}
//若搜索条件为全部文件，则默认填充中心值
function searchStatus(){
	
}