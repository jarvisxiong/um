var temPersons = [];

//获取机构树
function getOrgTree() {
	$.post(_PATH + "/oa/oa-user-selection!getOrgTree.action",
			function(result) {
				var tree = new TreePanel({
					renderTo:"deptDiv",
					'root' : eval('('+result+')'),
					'ctx': _PATH
				});
				tree.on(function(node){
					if($("#deptDisplay").text() == node.attributes.text) {
						return;
					}
					getUsers(node, temPersons);
				});
				tree.addListener("check",function(node){
					getUsers(node, temPersons);
				});
				tree.render();
	});
}

// 获取指定机构下的所有用户
function getUsers(node){
	var orgCds = node.getAllChildren(node);
	var title = node.attributes.text;
	var orgBizCd = node.attributes.extParam;
	var orgCd = node.attributes.id; 
	var checked = node.checked;
	
	if(title == $("#deptDisplay").text()) {
		$("#personDiv").css("cursor","wait");
		if(checked == '0') {
			delAll();
		} else if(checked == '1') {
			addAll();
		};
		$("#personDiv").css("cursor","");
		return;
	}
	
	$("#personDiv").css("cursor","wait");
	$("#userDisplay").empty().addClass("person_waiting");
	$.post(_PATH + "/oa/oa-user-selection!getUsersbyOrg.action", 
			{orgCds : orgCds.join(",")}, 
			function(result){
				$("#deptDisplay").text(title);
				$("#userDisplay").removeClass("person_waiting");
				
				var obj = eval(result);
				$.each(obj, function(i, n){
					if(n) {
						bindPersonEvents(n, "userDisplay", temPersons, true);
					}
				});
		
				if(checked == '1') {
					addAll();
				}
				
				$("#personDiv").css("cursor","");
	});
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
	$.post(_PATH + "/oa/oa-user-selection!getUsersByFilter.action", {value:domValue}, function(result){
		if (result) {
			$("#userDisplay").removeClass("person_waiting");
			$("#deptDisplay").text("搜索结果");
			var obj = eval(result);
			$.each(obj, function(i, n){
				bindPersonEvents(n, "userDisplay", temPersons, true);
			});
			isSearching = false;
		}
	});
}
//显示已经选择的人员
function showSelectedUser(){
	$("#userDisplay").empty();
	$("#deptDisplay").text("已选择人员");
	
	$.each(temPersons, function(i,n) {
		bindPersonEvents(n, "userDisplay", temPersons, true);
	});
}
// 绑定事件
function bindPersonEvents(obj, targetId, userList, addFlag) {
	if (addFlag) {
		$("#" + targetId).append("<li class='userUnSelected' id="+obj.uiid+">"+obj.userName+"</li>");
	}
	
	$("#"+obj.uiid).toggle(function() {
		$(this).attr("class","userSelected");
		addUser(obj.uiid, obj.userName, userList);
	}, function() {
		$(this).attr("class","userUnSelected");
		removeUser(obj.uiid, userList);
	});
	
	if (containsUser(obj.uiid, userList)) {
		$("#" + obj.uiid).trigger("click");
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
//向指定的人员列表中加入一条新的人员
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