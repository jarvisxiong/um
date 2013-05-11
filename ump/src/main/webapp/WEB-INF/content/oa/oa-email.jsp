<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/global.jsp" %>
<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/email.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/dmm-green/ymPrompt.css" /> 
<script language="javascript" src="${ctx}/js/jquery.js"></script>
<script language="javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script src="${ctx}/js/FCKEditor/fckeditor.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<link href="${ctx}/resources/css/common/TreePanel.css" rel="stylesheet" type="text/css"/>
<script type=text/javascript src="${ctx}/js/common.js"></script>
<script type=text/javascript src="${ctx}/resources/js/common/TreePanel.js"></script>
<script type="text/javascript" src="${ctx}/js/dtree.js"></script>
<link href="${ctx}/css/dtree.css" rel="stylesheet" type="text/css"></link>
<title> </title>
</head>

<body>
<div class="mailContainer">
	<div class="mailLeft">
		<div class="leftTop">
			 <a href="#" onclick="getMailList(5);"><img align="absMiddle" src="${ctx}/images/email/nm.png"/><span>收 信</span></a>
			 <a href="#" onclick="newMail();"><img align="absMiddle" src="${ctx}/images/email/wm.png"/><span>写信</span></a>
		</div>
		<div class="leftMainMenu"><img align="absMiddle" src="${ctx}/images/email/ar.png"/> 邮箱管理</div>
		<div class="leftMenu">
			<ul>
			    <li id="inBoxId" onclick="getMailList(1);">&nbsp;&nbsp;<img align="absMiddle" src="${ctx}/images/email/inbox.gif"/> 收件箱<span id="inBoxNum"></span></li>
			    <li id="draftBoxId" onclick="getMailList(2);">&nbsp;&nbsp;<img align="absMiddle" src="${ctx}/images/email/outbox.gif"/> 草稿箱<span id="draftBoxNum"></span>
			    	<img src="${ctx}/images/email/bin_empty.png" class="recyleMail"/>
			    </li>
			    <li id="outBoxId" onclick="getMailList(3);">&nbsp;&nbsp;<img align="absMiddle" src="${ctx}/images/email/sendbox.gif"/> 已发送<span id="outBoxNum"></span><img src="${ctx}/images/email/bin_empty.png" class="recyleMail"/></li>
			    <li id="delBoxId" onclick="getMailList(4);">&nbsp;&nbsp;<img align="absMiddle" src="${ctx}/images/email/trash.gif"/> 已删除<span id="delBoxNum"></span></li>
			 </ul>
		</div>
	</div>
	<div class="mailRight" id="mailRight">
		<%@include file="oa-email-list.jsp" %>
	</div>
</div>
<script language="javascript">
function ajaxDiv(url,data,divId,msgInfo,callback){
	$("#"+divId).html("<img align='absMiddle' src='${ctx}/images/loading.gif'>");
	$.post(url,data, function(result) {
		$("#"+divId).html(result);
		showSuccesTip(msgInfo);
		if(callback)callback();
	});
}
function showSuccesTip(msgInfo){
	var msg = msgInfo||'操作成功';
	ymPrompt.win({
		message:"<div style='text-align:center'>"+msg+"</div>",
		winPos:"t",
		width:80,
		height:26,
		showMask:false,
		titleBar:false,
		useSlide:true,
		afterShow:function(){},
		handler:setTimeout(function(){ymPrompt.close()},1000)
	});
}

/**
 * 写
 */
function newMail(id,args){
	var param = null;
	if(id){
		param = {dataId:id,args:args};
	}
	ajaxDiv("${ctx}/oa/oa-email!input.action",param,"mailRight");
}
/**
 * 获取列表
 */
function getMailList(id){
	ajaxDiv("${ctx}/oa/oa-email!list.action",{boxId:id},"mailRight","搜索成功");
}
/**
 * 标记为已读
 */
function markMails(){
	var checkbox_ids = new Array();
	$("input[name='oaEmailIds']:checked").each(function(i, dom) {
		checkbox_ids.push("oaEmailIds=" + $(dom).val());
	});
	if(checkbox_ids.length == 0){
		alert("请勾选需要标记的记录");
		return false;
	}
	var param = checkbox_ids.join("&");
	ajaxDiv("${ctx}/oa/oa-email!mark.action",param,"mailRight");
}
/**
 * 删除
 *@param 'delete'彻底删除
 *@param 'remove'移入废件箱
 */
function deleteMails(status,boxId){
	var checkbox_ids = new Array();
	$("input[name='oaEmailIds']:checked").each(function(i, dom) {
		checkbox_ids.push("oaEmailIds=" + $(dom).val());
	});
	if(checkbox_ids.length == 0){
		alert("请勾选需要删除的记录");
		return false;
	}
	checkbox_ids.push("boxId="+boxId);
	var param = checkbox_ids.join("&");
	ajaxDiv("${ctx}/oa/oa-email!"+status+"Batch.action",param,"mailRight","删除成功",setNum2LeftBar);
}
/**
 * 发送
 */
function sendMail(){
	var content = FCKeditorAPI.GetInstance('mailContent').GetXHTML();
	$("#mailContent").val(content);
	$("#mailForm").ajaxSubmit(function(result) {
		$("#mailRight").html(result);
		showSuccesTip("发送成功");
		$("#outBoxId").addClass("liHilight");
		setNum2LeftBar();
		userMap.clear();
	});
}
/**
 * 存入草稿箱
 */
function draftMail(){
	alert(draftMail);
    if($("#attaContainer input").length>1){
	alert(attach);
	$("#TB_tip").text("正在保存附件..");
	$("#mailForm").attr("action","${ctx}/app/app-attachment!upload.action");
	$("#uploadFlg").val("1");
	$("#mailForm").ajaxSubmit(function(){
		draftMail2();
	})
	}
}
function draftMail2(){
   var content = mailContentEditor.getSource();
	$("#mailContent").val(content);
	$("#mailForm").attr("action","${ctx}/oa/oa-email!draft.action");
	$("#mailForm").ajaxSubmit(function(result) {
		$("#mailRight").html(result);
		hilightLeft(2);
		setNum2LeftBar();
		userMap.clear();
		copyUserMap.clear();
	});
}
/**
 * 查看
 */
function readMail(oaEmailId,oaEmailBodyId,boxId,npFlag){
	var param;
	if(npFlag){
		param = {oaEmailId:oaEmailId,id:oaEmailBodyId,boxId:boxId,npFlag:npFlag}
	}else{
		param = {oaEmailId:oaEmailId,id:oaEmailBodyId,boxId:boxId}
	};
	ajaxDiv("${ctx}/oa/oa-email!read.action",param,"mailRight");
}
function modContent(my_id){
    var oFCKeditor = new FCKeditor(my_id) ;
    oFCKeditor.BasePath = "${ctx}/js/FCKEditor/";
	oFCKeditor.Height = 300;
	oFCKeditor.ToolbarSet = "Basic";
    oFCKeditor.ReplaceTextarea();
}
function checkedAll(flag){
	$("input[name='oaEmailIds']").attr("checked",flag);
}
function checkedRead(flag){
	$("input[readFlag='0']").attr("checked",flag);
	$("input[readFlag='1']").attr("checked",!flag);
}
function reveChecked(){
	$("input[name='oaEmailIds']").each(function(){
		var flag = $(this).attr("checked");
		$(this).attr("checked",!flag);
	});
}
function getSelectedUser(tp){
	if(tp=="ok"){
		var names = "";
		var codes = "";
		$.each(userMap.keySet(),function(i,n){
			var obj = userMap.get(n);
			names = names+ obj.userName+";"; 
			codes = codes+ obj.uiid+";"; 
		});
		$("#toUserNames").val(names);
		$("#toUserCds").val(codes);
	}
}

function bindEvents(obj,addFlag){
	if(addFlag){
		$("#userDisplay").append("<li class='userUnSelected' id="+obj.uiid+">"+obj.userName+"</li>");
	}
	$("#"+obj.uiid).toggle(function(){
		$(this).attr("className","userSelected");
		userMap.put(obj.uiid,obj);
	},function(){
		$(this).attr("className","userUnSelected");
		userMap.remove(obj.uiid);
	});
	if(userMap.containsKey(obj.uiid)){
		$("#"+obj.uiid).trigger("click");
	}
}

function showSelectedUser(){
	$("#userDisplay").empty();
	$("#deptDisplay").text("已选人员");
	$.each(userMap.keySet(),function(i,n){
		var obj = userMap.get(n);
		bindEvents(obj,true);
	});
}

function getUsers(orgCd,title){
	$.post("${ctx}/oa/oa-email!getUsersbyOrg.action",{orgCd:orgCd},function(result){
		alert(result);
		$("#userDisplay").empty();
		$("#deptDisplay").text(title);
		result = eval(result);
		$.each(result,function(i,n){
			if(n)bindEvents(n,true);
		});
	})
}

function addAll(){
	$("#userDisplay li.userUnSelected").trigger("click");
} 
function delAll(){
	$("#userDisplay li.userSelected").trigger("click");
}

var userMap = new Map();
function getMember(){
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"选择人员",
		message:"<div id='memberDiv'><img align='absMiddle' src='${ctx}/images/loading.gif'></div>",
		useSlide:true,
		winPos:"t",
		width:350,
		height:400,
		maxBtn:true,
		allowRightMenu:true,
		handler:getSelectedUser,
		afterShow:function(){
			$.post("${ctx}/oa/oa-email!member.action",function(result){
				$("#memberDiv").html(result);
				if(userMap.isNotEmpty()){
					showSelectedUser();
				}else{
					$("#userDisplay li").each(function(){
						var obj = {};
						obj.uiid = $(this).attr("id");
						obj.userName = $(this).text();
						bindEvents(obj,false);
					});
				}
				showTree();
				$("#deptLi").toggle(function(){$("#deptDiv").show()},function(){$("#deptDiv").hide()});
				$("#roleLi").toggle(function(){$("#roleDiv").show()},function(){$("#roleDiv").hide()});
			});
		}
	});
}
function setNum2LeftBar(){
	$.post("${ctx}/oa/oa-email!getMailNum.action",function(result){
		var num = eval(result);
		$("#inBoxNum").text("("+num[0]+")");
		$("#draftBoxNum").text("("+num[1]+")");
		$("#outBoxNum").text("("+num[2]+")");
		$("#delBoxNum").text("("+num[3]+")");
	});
}
$(function(){
	setNum2LeftBar();
	$(".leftMenu li").click(function(){
		$(this).siblings().removeClass("liHilight");
		$(this).addClass("liHilight");
	});
});
</script>
</body>

</html>
