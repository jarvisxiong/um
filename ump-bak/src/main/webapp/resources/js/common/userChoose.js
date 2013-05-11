function isEmpty(str) {
		return (typeof (str) === "undefined" || str === null || (str.length === 0));
	};
function isNotEmpty(str) {
	return (!String.isEmpty(str));
};
function getOrgTree() {
	var data = {
		"isByUser" : isByUser,
		orgType:orgType
	};
	if (isNotEmpty(rootOrgCd)){
		data.parentOrgCd=rootOrgCd;
	}
	$.post(_ctx + "/com/user-choose!getOrgTree.action",data, function(result) {
		var tree = new TreePanel( {
			renderTo : "deptDiv",
			'root' : eval('(' + result + ')'),
			'ctx' : _ctx
		});
		tree.on(function(node) {
			if ($("#deptDisplay").text() == node.attributes.text)
				return;
			getUsers(node, "click");
		});
		tree.addListener("check", function(node) {
			getUsers(node);
		});
		tree.render();
	});
}

function getSelectedUser(tp) {
	if (tp == 'ok') {
		var map = userMap;
		var names = [];
		var codes = [];
		var i = 0;
		for ( var k in map) {
			var obj = map[k];
			
			if (isNotEmpty(mapData)){
				for ( var j in mapData) {
					$("#" +j).val(map[0]['+eval(attrName)+']);
				}
			}
			names.push(obj.userName);
			codes.push(obj.uiid);
			i++;
		}
		if(names.length>0){
			var nameStr = names.join(";");
			var codeStr = codes.join(";");
			if(isOnlyOne == '1'){
				$("#" +nameId).val(nameStr);
				$("#" + cdId).val(codeStr);
			}else{
				$("#" +nameId).val(nameStr+';');
				$("#" + cdId).val(codeStr+';');
			}
		}else{
			$("#" +nameId).val('');
			$("#" + cdId).val('');
		}
		if (callBack){
			callBack();
		}
		
		if(i>0){
			//TODO 暂时没用
			//$("#"+ currentType + "Num").val(i);
		}
	}
	$("#memberDiv").remove();
	
}

function bindEvents(obj, addFlag) {
	var map = userMap;
	if (addFlag) {
		var strList = new Array();
		strList.push("<li class='userUnSelected' id=");
		strList.push(obj.uiid);
		strList.push("><div class='userStatus_online'>");
		strList.push(obj.userName);
		if(obj.orgName){
			strList.push("(");
			strList.push(obj.orgName);
			strList.push(")");
		}
		strList.push("</div></li>");
		$("#userDisplay").append(strList.join(""));
	}
	$("#" + obj.uiid).toggle(function() {
		if(isOnlyOne == '1'){
			$('li.userSelected').trigger('click');
			$.each(map,function(i,n){
				delete map[i];
			});
		}
		$(this).attr("className", "userSelected");
		map[obj.uiid] = obj;
	}, function() {
		$(this).attr("className", "userUnSelected");
		delete map[obj.uiid];
	});
	if (map[obj.uiid]) {
		$("#" + obj.uiid).trigger("click");
	}
}

function showSelectedUser() {
	var map = userMap;
	$("#userDisplay").empty();
	$("#deptDisplay").text("已选人员");
	for ( var k in map) {
		var obj = map[k];
		if(k == 0){
			continue;
		}
		bindEvents(obj, true);
	}

}

function getUsers(node, tag) {
	//var orgCds = node.getAllChildren(node);
	var orgCds = node.getAllChildren(node,'entityCd');
	var title = node.attributes.text;
	var orgBizCd = node.attributes.extParam;
	var orgCd = node.attributes.entityCd;
	var checked = node.checked;
	if (tag)
		checked = tag;
	if (title == $("#deptDisplay").text()) {
		$("#memberDiv").css("cursor", "wait");
		if (checked == '0') {
			delAll();
		} else if (checked == '1') {
			addAll();
		}
		;
		$("#memberDiv").css("cursor", "");
		return;
	}

	$("#memberDiv").css("cursor", "wait");
	$("#mailUserRight").addClass("waiting");
	$.post(_ctx + "/com/user-choose!getUsersbyOrg.action", {
		orgCds : orgCds.join(",")
	}, function(result) {
		$("#userDisplay").empty();
		$("#deptDisplay").text(title);
		result = eval(result);
		$.each(result, function(i, n) {
			if (n)
				bindEvents(n, true);
		});
		if (checked == '0') {
			delAll();
		} else if (checked == '1') {
			addAll();
		}
		;
		$("#memberDiv").css("cursor", "");
		$("#mailUserRight").removeClass("waiting");
	});
}

function addAll() {
	$("#userDisplay li.userUnSelected").trigger("click");
}
function delAll() {
	$("#userDisplay li.userSelected").trigger("click");
}
function clearMember(tag) {
	$("#" + tag + "UserNames").val("");
	$("#" + tag + "UserCds").val("");
	cleartUserMap();
}
function cleartUserMap(){
	userMap = {};
}
var userMap = {};
var rootOrgCd;
var isByUser='1';
var nameId,cdId;
var isOnlyOne;
var mapData;
var callBack;
//isOnlyUser 只列出人员名单
//isOnlyOne 只能选择一个人
var orgType='Physical';//Logical
function getMemberJson(config) {
	rootOrgCd = isEmpty(config['root'])?'':config['root'];
	window.isByUser =isEmpty(config['isByUser'])?'0': config['isByUser'];
	window.isOnlyOne = String(config['isOnlyOne']);
	window.isOnlyUser = String(config['isOnlyUser']);
	window.callBack = config['callBack'];
	window.orgType =isEmpty(config['orgType'])?'Physical': config['orgType'];
	if (isNotEmpty(config['nameId'])){
		window.nameId=String(config['nameId']);
		window.cdId=String(config['cdId']);
	}else{
		window.nameId=config['tag'] + "UserNames";
		window.cdId=config['tag'] + "UserCds";
	}
	return initUserChoose();
}
function getMember(tag, root,isByUser,nameId,cdId,isOnlyUser,isOnlyOne,callBack) {
	rootOrgCd = root;
	window.isByUser = isByUser;
	window.isOnlyOne = isOnlyOne;
	window.isOnlyUser = isOnlyUser;
	window.callBack = callBack;
	if (isNotEmpty(nameId)){
		window.nameId=nameId;
		window.cdId=cdId;
	}else{
		window.nameId=tag + "UserNames";
		window.cdId=tag + "UserCds";
	}
	return initUserChoose();
};
 function initUserChoose(){
	var map = {};
	var names,cds;
	names = $("#" + window.nameId).val().split(";");
	cds = $("#" +window.cdId).val().split(";");
	if (isEmptyObj(map)) {
		$.each(names, function(i, n) {
			if ($.trim(n) == "")
				return;
			var obj = {};
			obj.uiid = cds[i];
			obj.userName = names[i];
			map[obj.uiid] = obj;
		});
	}
	window.userMap=map;
	var data={};
	if (window.isOnlyUser=='1'){
		data.isOnlyUser=isOnlyUser;
	}else{
		data.isOnlyUser='0';
	}
	if (isNotEmpty(rootOrgCd)){
		data.parentOrgCd=rootOrgCd;
	}

	ymPrompt.alert( {
		icoCls : "",
		title : "选择人员",
		message : "<div id='memberDiv'><img align='absMiddle' src='" + _ctx
				+ "/images/loading.gif'></div>",
		useSlide : true,
		winPos : "t",
		width : 390,
		height : 400,
		showMask:true,
		allowRightMenu : true,
		handler : getSelectedUser,
		btn:[["确定",'ok'],["取消",'cancel']],
		afterShow : function() {
			$.post(_ctx + "/com/user-choose!member.action", data,function(result) {
				$("#memberDiv").empty().html(result);
				if (window.isOnlyUser=='1'){
					cleartUserMap();
				}
				if (!isEmptyObj(map)&&window.isOnlyUser!='1') {
					showSelectedUser();
				} else {
					$("#userDisplay li").each(function() {
						var obj = {};
						obj.uiid = $(this).attr("id");
						obj.userName = $(this).text();
						bindEvents(obj, false);
					});
				}
			});
		}
	});
	return userMap[0];
}
var outTime;
function getUser(dom){
	if(outTime) clearTimeout(outTime);
	outTime= setTimeout(function(){
		var domValue=$.trim(dom.value);
		if(domValue=="")return;
		var data={value:domValue,"parentOrgCd" : rootOrgCd};
		$("#mailUserRight").addClass("waiting");
		$("#userDisplay").html("<img align='absMiddle' src='"+_ctx+"/images/loading.gif'>");
		$.post(_ctx+"/com/user-choose!getUsersByFilter.action",data,function(result){
			$("#userDisplay").empty();
			$("#deptDisplay").text("搜索结果");
			result = eval(result);
			$.each(result,function(i,n){
				if(n)bindEvents(n,true);
			});
			$("#mailUserRight").removeClass("waiting");
			delete result;
		});
	},300);
}
function showGroupMember(dom,checkFlg){
	var $parent = $(dom).parent();
	var dataId = $parent.attr("dataId");
	var title = $parent.text();
	$("#userDisplay").empty();
	$("#deptDisplay").text(title);
	$.post(_ctx+"/oa/oa-email-group!load.action",{id:dataId},function(result){
		result = eval(result);
		$.each(result,function(i,n){
			if(n)bindEvents(n,true);
		});
		if(checkFlg){
			var checked = $(dom).attr("checked");
			if(checked){
				addAll();
			}else{
				delAll();
			} 
		}
	});
}
function isEmptyObj(obj) {
	return ((function() {
		for ( var k in obj)
			return k
	})() != null ? false : true);
}