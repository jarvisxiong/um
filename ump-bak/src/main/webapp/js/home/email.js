var UPLOAD_FILE_NAMES = new Array();
var SELECTED_ALL_USER_PER = false;
var MAX_SELECT_USER_NUM = 50;
var mailContentEditor;//缓存xheditor编辑器，用于ajax提交时获取内容
var mailSignEditor;//缓存个人签名档xheditor
var userMap = {};//收件人数据缓存
var copyUserMap = {};//抄送人数据缓存
var bccUserMap = {};//密送人数据缓存
var currentType = "to";//默认当前打开给收件人
var searchTime;//搜索 --人员搜索定时器
var PAGE_SIZE = 13; //分页大小，随列表区高度改变而改变
var PAGE_NO = 1; //当前第几页
var BOX_ID = "1";	//现在在那种类别里,默认是收件箱2
var now_sending = false;	//是否现在正在发送
/**
 * 判断对象是否为空 
 */
function isEmptyObj(obj){
	return ((function(){for(var k in obj)return k})()!=null?false:true);
}
/**
 * 检查附件格式以及附件是否已经存在于上传列表
 * @param fileName  附件名称
 */
function checkUploadFile(fileName){
	//var uploadFileSuffix = ["gif", "png","jpg", "bmp","jpg","wma","avi","xlsx", "docx", "doc", "xls", "ppt","pptx","pdf", "txt", "tif","zip","rar"];
	var uploadFileSuffix = ["bat","exe","dll"];
	if(UPLOAD_FILE_NAMES.contains(fileName)){
		alert("该文件已经存在！");
		return false;
	}
	var suffix = fileName.substring(fileName.lastIndexOf('.')+1,fileName.length).toLowerCase();
	if(uploadFileSuffix.contains(suffix)){
		//alert("对不起,系统暂不支持该格式的文件上传!目前允许的格式有\n"+uploadFileSuffix.join(",")+"\n\n建议压缩成rar或zip格式 ，谢谢!");
		alert("对不起,系统暂不支持"+uploadFileSuffix.join(",")+"格式的文件上传!");
		return false;
	};
	return true;
}
function getCurrMap(tag){
	var map;
	switch(tag){
	case "to":{
		return userMap;
	}
	case "copy":{
		return copyUserMap;
	}
	case "bcc":{
		return bccUserMap;
	}
	default:{
		return {};
	}
}
}
/**
 * 根据boxId高亮左侧导航栏
 * @param boxId
 */
function hilightLeft(boxId){
	var box;
	//if(boxId == 0)box="newEmailId";
	if(boxId == 0){
		$('#mailBottom .menuClick').removeClass('menuClick');
	}else{
		if(boxId == 1)box="inBoxId";
		if(boxId == 2)box="draftBoxId";
		if(boxId == 3)box="outBoxId";
		if(boxId == 4)box="delBoxId";
		if(boxId == 5)box="unReadBoxId";
		if(boxId == 6)box="searchBoxId";
		if(box == $('#mailBottom .menuClick').attr('id')){
			return;
		}
		$('#quickSearchBoxId').val(boxId).change();
		$("#"+box).addClass("menuClick").siblings().removeClass("menuClick");
	}
	
}
/**
 * 用ajax刷新区域(主要由于右侧列表刷新)
 */
function ajaxDiv(url,data,divId,callback){
	try{
		if("string"==typeof(data)){
			data += "&pageEmail.pageSize="+PAGE_SIZE;
			data += "&pageEmail.pageNo="+PAGE_NO;
		}else{
			data["pageEmail.pageSize"]=PAGE_SIZE;
			data["pageEmail.pageNo"]=PAGE_NO;
		}
	}catch(e){}
	TB_showMaskLayer("正在加载...");
	$.ajax({
		   type: "POST",
		   url: url,
		   data: data,
		   timeout:10000,
		   success: function(result){
			   if("doSearch"==result){
				   TB_removeMaskLayer();
				   $('#quickSearchPageSize').val(PAGE_SIZE);
				   $('#quickSearchOrderBy,#quickSearchOrder,#quickSearchSort').val('');
				   quickSearch(PAGE_NO);
				   if(callback)callback();
				   return;
			   }else if(result == "error_deleted"){
				   alert("该已经被删除！");
				   $("#inBoxId").trigger("click");
				   TB_removeMaskLayer();
				   return;
			   }else if(result == "error_locked"){
				   alert("服务器繁忙,是否重试？");
				   TB_removeMaskLayer();
				   return;
			   }
			   $("#"+divId).html(result);
			   TB_removeMaskLayer();
			   bindTdEvents();
			   if(callback)callback();
		   },
		   error:function(e){
			   if(window.confirm("系统繁忙(故障),请重试！")){
				   ajaxDiv(url,data,divId,callback);
			   }else{
				   TB_removeMaskLayer();
			   }
		   }
	});
	
//	$.post(url,data, function(result) {
//		if(result == "error_deleted"){
//			alert("该已经被删除！");
//			$("#inBoxId").trigger("click");
//			//$("#"+divId).removeClass("waiting");
//			TB_removeMaskLayer();
//			return;
//		}
//		if(result == "error_locked"){
//			alert("系统繁忙(故障),请重试！");
//			//$("#"+divId).removeClass("waiting");
//			TB_removeMaskLayer();
//			return;
//		}
//		$("#"+divId).html(result);
//		TB_removeMaskLayer();
//		bindTdEvents();
//		if(callback)callback();
//	});
}

function bindTdEvents(){
	$('#mailBoxTable tbody tr td[click2expand=true]').click(
		function(){
			var $parent = $(this).parent();
			if($parent.next().css('display')!='none'){
				$parent.removeClass('read').next().hide();
				return;
			}
			var oaEmailId = $parent.attr('oaEmailId');
			var oaEmailBodyId = $parent.attr('oaEmailBodyId');
			var boxId = $parent.attr('boxId');
			var isLoaded = eval($parent.attr('isLoaded'));
			$('#mailBoxTable tbody tr.read').removeClass('read').next().hide();
			$parent.addClass('read');
			if(isLoaded){
				$parent.next().show();
			}else{
				showEmail(oaEmailId, oaEmailBodyId, boxId);
			}
	});
	$('#mailBoxTable tbody tr td[click2open=true]').click(function(){
		var $parent = $(this).parent();
		var oaEmailId = $parent.attr('oaEmailId');
		var oaEmailBodyId = $parent.attr('oaEmailBodyId');
		var boxId = $parent.attr('boxId');
		readMail(oaEmailId, oaEmailBodyId, boxId);
	});
}


/**
 * 显示搜索界面，默认显示收件箱
 */
function searchEmail(){
	ajaxDiv(_ctx+"/oa/oa-email!search.action","","mailRight",function(){
		$("#emailSearchBtn").trigger("click");
		var h = $(document).height();
		$("#searchResult").height(h-108+'px');
		bindTdEvents();
		hilightLeft(6);
	});
}
/**
 * 写新
 */
function newMail(id,args,reply_id){
	var param = null;
	if(id){
		param = {dataId:id,args:args,reply_id:reply_id};
	}
	ajaxDiv(_ctx+"/oa/oa-email!input.action",param,"mailRight",function(){
		limit();
		 if(!isPhone()){
			//初始化个人签名档
			mailContentEditor = $('#mailContent').xheditor({
				tools:'full',forcePtag:false,showBlocktag:false,html5Upload:false,upMultiple:'1',
				upLinkUrl:_ctx+"/oa/oa-email!upload.action",upLinkExt:"xlsx,docx,doc,xls,pdf,zip,rar,txt"
				/*
				,
				upImgUrl:_ctx+"/oa/oa-email!upload.action",upImgExt:"jpg,jpeg,gif,png",
				upFlashUrl:_ctx+"/oa/oa-email!upload.action",upFlashExt:"swf",
				upMediaUrl:_ctx+"/oa/oa-email!upload.action",upMediaExt:"avi"
				*/
			}
			);
			$.get(_ctx+"/oa/oa-email!getEmailSign.action",function(result){
				if($.trim(result) != ""){
					mailContentEditor.appendHTML("<br/><br/><hr size='1' style='border:1px dashed #cccccc'/>"+result);
					mailContentEditor.focus();
				}
			});
		 }
		hilightLeft(0);
		userMap={};
		copyUserMap={};
		bccUserMap = {};
	});
}
/**
 * 获取列表
 */
function getMailList(id,sort,usePage){
	if(BOX_ID!=id){
		//如果是不同类别，自动翻回第一页
		PAGE_NO = 1;
	}
	if(usePage){
		pageNo = $("#currentPageNo").val();
		if($("#quickSearchFlag").val() == 'true'){
			quickSearch(pageNo);
			return;
		}
	}else{
		pageNo = 1;
		$("#currentPageNo").val(1);
	}
	$("#quickSearchFlag").val(false);
	var param = {boxId:id,'pageEmail.pageNo':pageNo,'pageEmail.pageSize':PAGE_SIZE};
	if(sort){
		param = {boxId:id,sort:sort,'pageEmail.pageNo':pageNo,'pageEmail.pageSize':PAGE_SIZE};
	};
	ajaxDiv(_ctx+"/oa/oa-email!list.action",param,"mailRight");
	hilightLeft(id);
	BOX_ID=id;
}

function quickSearch(pageNo){
	pageNo = pageNo||1;
	$("#quickSearchFlag").val(true);
	$("#quickSearchPageNo").val(pageNo);
	TB_showMaskLayer("正在加载...");
	$("#quickSearchForm").ajaxSubmit(function(result){
		$("#mailRight").html(result);
		TB_removeMaskLayer();
		$("#currentPageNo").val(pageNo);
		hilightLeft($('#quickSearchBoxId').val());
		bindTdEvents();
	});
}

function return2SearchDiv(){
	$("#searchResult").show();
	$("#emailSearchDiv").show();
	$("#searchReadMailDiv").hide();
}

function setSortInfo(orderName,sort){
	$("#orderBy").val(orderName);
	$("#order,#sort").val(sort);
	if($("#quickSearchFlag").val() == 'true'){
		$("#quickSearchOrderBy").val(orderName);
		$("#quickSearchOrder,#quickSearchSort").val(sort);
	}
	jumpPage($("#pageNo").val());
}

/**
 * ajax分页
 */
function jumpPage(pageNo) {
	var divId = "mailRight";
	var formId = "mailPageForm";
	
	if($("#quickSearchFlag").val() == 'true'){
		formId = "quickSearchForm";
		$("#quickSearchPageNo").val(pageNo);
	}else{
		if($("#mailSearchForm").length>0){
			divId = "searchResult";
			formId = "mailSearchForm";
			$("#searchPageNo").val(pageNo);
		}
	}
	$("#pageNo").val(pageNo);
	PAGE_NO = pageNo;
	$("#"+formId+" input[name='pageEmail.pageSize']").val(PAGE_SIZE);
	TB_showMaskLayer("正在加载...");
	$("#"+formId).ajaxSubmit(function(result){
		$("#"+divId).html(result);
		TB_removeMaskLayer();
		$("#currentPageNo").val(pageNo);
		bindTdEvents();
	});
}
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
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
	ajaxDiv(_ctx+"/oa/oa-email!mark.action",param,"mailRight",setNum2LeftBar);
}
/**
 * 删除多封
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
	if(status == "delete"&&boxId == 3){
		if(!window.confirm("你确定要删除选择的吗?\n(注：如果删除对方未读，对方将无法收到该！)"))return;
	}
	checkbox_ids.push("boxId="+boxId);
	var param = checkbox_ids.join("&");
	param = param +"&status="+status+getQuickSearchParamStr();
	ajaxDiv(_ctx+"/oa/oa-email!deleteBatch.action",param,"mailRight",setNum2LeftBar);
	hilightLeft(boxId);
}
//组合出快速搜索的搜索条件的paramStr
function getQuickSearchParamStr(){
	var returnStr = "";
//	try{
//		if(""!=$("#quickSearchForm input[name='filter_LIKES_oaEmailBody_sender']").val())
//		returnStr += "&filter_LIKES_oaEmailBody_sender="+$("#quickSearchForm input[name='filter_LIKES_oaEmailBody_sender']").val();
//	}catch(e){}
//	try{
//		if(""!=$("#quickSearchForm input[name='filter_LIKEC_oaEmailBody_toUserNames']").val())
//		returnStr += "&filter_LIKEC_oaEmailBody_toUserNames="+$("#quickSearchForm input[name='filter_LIKEC_oaEmailBody_toUserNames']").val();
//	}catch(e){}
//	try{
//		if(""!=$("#quickSearchForm input[name='filter_LIKES_oaEmailBody_subject']").val())
//		returnStr += "&filter_LIKES_oaEmailBody_subject="+$("#quickSearchForm input[name='filter_LIKES_oaEmailBody_subject']").val();
//	}catch(e){}
	return returnStr;
}
//组合出快速搜索的搜索条件的param[]
function setQuickSearchParamArray(param){
//	try{
//		if(""!=$("#quickSearchForm input[name='filter_LIKES_oaEmailBody_sender']").val())
//		param["filter_LIKES_oaEmailBody_sender"]=$("#quickSearchForm input[name='filter_LIKES_oaEmailBody_sender']").val();
//	}catch(e){}
//	try{
//		if(""!=$("#quickSearchForm input[name='filter_LIKEC_oaEmailBody_toUserNames']").val())
//			param["filter_LIKEC_oaEmailBody_toUserNames"]=$("#quickSearchForm input[name='filter_LIKEC_oaEmailBody_toUserNames']").val();
//	}catch(e){}
//	try{
//		if(""!=$("#quickSearchForm input[name='filter_LIKES_oaEmailBody_subject']").val())
//			param["filter_LIKES_oaEmailBody_subject"]=$("#quickSearchForm input[name='filter_LIKES_oaEmailBody_subject']").val();
//	}catch(e){}
}
/**
 * 删除单封
 */
function deleteMail(status,id,boxId,oaEmailId){
	if(boxId == '3'&&status!="clear2")status = 'clear';
	if(status == "delete"){
		if(!window.confirm("你确定要删除当前吗?"))return;
	}
	if(status == "clear"){
		if(!window.confirm("你确定要删除当前吗(如果收件人尚未阅读，收件人将无法看到该)?"))return;
	}
	if(!oaEmailId)oaEmailId = "";
	var param = {id:id,status:status,boxId:boxId,oaEmailId:oaEmailId};
	setQuickSearchParamArray(param);
	//var param = "&id="+id+"&status="+status+"&boxId="+boxId+"&oaEmailId="+oaEmailId+getQuickSearchParamStr();
	ajaxDiv(_ctx+"/oa/oa-email!delete.action",param,"mailRight",setNum2LeftBar);
	hilightLeft(boxId);
}
/**
 * 发送
 */
function sendMail(){
	if(now_sending){
		return;
	}
	if(!SELECTED_ALL_USER_PER){
		var i = $("#toUserNames").val().split(";").length;
		var j = $("#copyUserNames").val().split(";").length;
		if(i+j>MAX_SELECT_USER_NUM){
			alert("对不起，您权限不足，您最多同时发送给"+MAX_SELECT_USER_NUM+"个人(含收件人、抄送人)");
			return;
		}
	}
	if($("#toUserNames").val() == ""){
		alert("请选择收件人");
		$("#toUserChoose").trigger("click");
		return;
	}
	try{
		TB_showMaskLayer(".");
	}catch(e){}
	$("#TB_overlay").remove();
	$("#TB_HideSelect").remove();
	if (mailContentEditor){
	var content = mailContentEditor.getSource();
	try{
		copyToClipboard(content);
	}catch(e){}
	}
	if($("#attaContainer input").length>0){
		saveAtta();
	}else{
		saveEmail();
	}
}
function saveAtta(){
	$("#TB_tip").text("正在上传附件..");
	$("#mailForm").attr("action",_ctx+"/app/app-attachment!upload.action");
	$("#uploadFlg").val("1");
	$("#entityId").val("");
	$("#mailForm").ajaxSubmit(function(result){
		//返回内容:Struts2Utils.renderHtml("maxSizeError-" + Constants.MAX_FILE_SIZE / (1024 * 1000));
		if(result.indexOf("maxSizeError")== 0){
			var max = result.split("-")[1];
			alert("上传文件最大不能超过"+max+"M");
			TB_removeMaskLayer();
			now_sending = false;
			return;
		}
		saveEmail();
	});
	now_sending = true;
}
function saveEmail(){
	$("#attaContainer").empty();
	$("#mailForm").attr("action",_ctx+"/oa/oa-email!save.action");
	if (mailContentEditor){
		var content = mailContentEditor.getSource();
		$("#mailContent").val(content);
	}else{
		content=$("#mailContent").val()+$('#divOriContent').html();
		$("#mailContent").val(content);
	}
	$("#entityId").val($("#entityIdAlias").val());
	try{
		$("#mailFormPageSize").val(PAGE_SIZE);
	}catch(e){}
	if($("#uploadAttaContainer").children().length>0){$("#uploadFlg").val("1");};
	$("#TB_tip").text("内容已复制到剪贴板,正在发送..");
	$("#mailForm").ajaxSubmit(function(result) {
		$("#mailRight").empty().html(result);
		bindTdEvents();
		TB_removeMaskLayer();
		hilightLeft(3);
		setNum2LeftBar();
		userMap={};
		copyUserMap={};
		bccUserMap = {};
		now_sending = false;
	});
	now_sending = true;
}
/**
 * 存入草稿箱
 */
function draftMail(){
	if(now_sending){
		return;
	}
	TB_showMaskLayer(".");
	$("#TB_overlay").remove();
	$("#TB_HideSelect").remove();
    if($("#attaContainer input").length>0){
		$("#TB_tip").text("正在保存附件..");
		$("#mailForm").attr("action",_ctx+"/app/app-attachment!upload.action");
		$("#uploadFlg").val("1");
		$("#entityId").val("");
		$("#mailForm").ajaxSubmit(function(){
			draftMailContent();
		});
	}else{
		draftMailContent();
	}
}
function draftMailContent(){
	$("#TB_tip").text("正在保存..");
	$("#attaContainer").empty();
	if (mailContentEditor){
   	var content = mailContentEditor.getSource();
	$("#mailContent").val(content);
	}
	if($("#uploadAttaContainer").children().length>0){$("#uploadFlg").val("1");};
	$("#entityId").val($("#entityIdAlias").val());
	$("#mailForm").attr("action",_ctx+"/oa/oa-email!draft.action");
	$("#mailForm").ajaxSubmit(function(result) {
		$("#mailRight").empty().html(result);
		bindTdEvents();
		TB_removeMaskLayer();
		hilightLeft(2);
		setNum2LeftBar();
		userMap={};
		copyUserMap={};
		bccUserMap={};
		now_sending = false;
	});
	now_sending = true;
}
/**
 * 查看
 */
function readMail(oaEmailId,oaEmailBodyId,boxId,oper){
	var param;
	if(oper){
		param  = {oaEmailId:oaEmailId,id:oaEmailBodyId,boxId:boxId,operate:oper};
	}else{
		param  = {oaEmailId:oaEmailId,id:oaEmailBodyId,boxId:boxId};
	}
	var divId = "mailRight";
	if($("#mailSearchForm").length>0){
		divId = "searchReadMailDiv";
		param.searchFlg = "true";
	}
	ajaxDiv(_ctx+"/oa/oa-email!read.action",param,divId,function(){
		if(divId == "searchReadMailDiv"){
			$("#emailSearchDiv").hide();
			$("#searchResult").hide();
			$("#"+divId).show();
		}
		 if(!isPhone()){
			mailContentEditor = $('#mailContent').xheditor({
				tools:'full',forcePtag:false,showBlocktag:false,html5Upload:false,upMultiple:'1',
				emots:{'qq':{'name':'QQ','count':55,'width':25,'height':25,'line':11}},
				upLinkUrl:_ctx+"/oa/oa-email!upload.action",upLinkExt:"zip,rar,txt",
				upImgUrl:_ctx+"/oa/oa-email!upload.action",upImgExt:"jpg,jpeg,gif,png",
				upFlashUrl:_ctx+"/oa/oa-email!upload.action",upFlashExt:"swf",
				upMediaUrl:_ctx+"/oa/oa-email!upload.action",upMediaExt:"avi"
				}
			);
		 }
		limit();
		setNum2LeftBar();
	});
}
/**
 * 预览
 */
function showEmail(oaEmailId,oaEmailBodyId,boxId){
	var $detail = $('#detail_'+oaEmailBodyId);
	var $info = $('#info_'+oaEmailBodyId);
	$detail.find('td').html("<img src='"+_ctx+"/images/loading.gif'/>");
	$detail.show();
	$info.removeClass('unReadEmail');
	$info.find('.newEmail').remove();
	param  = {oaEmailId:oaEmailId,id:oaEmailBodyId,boxId:boxId,expand:true};
	$.post(_ctx+'/oa/oa-email!read.action',param,function(result){
		if(result == 'error_deleted')result = "该已经被删除！";
		$info.attr('isLoaded',true);
		$('#detail_'+oaEmailBodyId +' td').html(result);
		$('#detail_'+oaEmailBodyId).dblclick(function(){
			$detail.hide();
			$info.removeClass('read');
		});
		limit();
		setNum2LeftBar();
	});
}
/**
 * 清空垃圾箱
 */
function clearDelBox(){
	ajaxDiv(_ctx+"/oa/oa-email!clearDelBox.action",{},"mailRight",setNum2LeftBar);
}
function checkedAll(flag){
	$("input[name='oaEmailIds']").attr("checked",flag);
}

/**
 * 管理(用于管理员来删除或者编辑)
 */
function emailManage(role){
	TB_showMaskLayer("正在加载...");
	$.post(_ctx+"/oa/oa-email-body!list.action",function(r){
		$('#mailRight').html(r);
		TB_removeMaskLayer();
		var h = $(document).height();
		$("#searchResult").height(h-81-55+'px');
		if(role == 'mail_admin'){
			bindTdEvents();
		}
	});
}
/**
 * 用于管理员来强制删除
 * @return
 */
function deleteEmailAdmin(oaEmailBodyId){
	if(window.confirm('确定要删除该吗？(删除后所有用户将无法看到该)')){
		TB_showMaskLayer("正在删除...");
		$.post(_ctx+"/oa/oa-email-body!delete.action",{id:oaEmailBodyId},function(){
			$('#info_'+oaEmailBodyId+',#detail_'+oaEmailBodyId).remove();
			TB_removeMaskLayer();
		});
		
	}
}
function searchEmailAdmin(role){
	TB_showMaskLayer("正在加载...");
	$("#mailAdminSearchForm").ajaxSubmit(function(result){
		$("#mailRight").html(result);
		TB_removeMaskLayer();
		var h = $(document).height();
		$("#searchResult").height(h-81-55+'px');
		if(role == 'A_EMAIL_AMDIN_READ'){
			bindTdEvents();
		}
	});
}

/**
 * 将选中的人显示在收件人或者抄送人文本框内
 * @param tp "to"=收件人
 */
function getSelectedUser(tp){
	//var map =(currentType=="to")?userMap:copyUserMap; 
	var map = getCurrMap(currentType);
	var names = "";
	var codes = "";
	for(var k in map){
		var obj = map[k];
		names = names+ obj.userName+";"; 
		codes = codes+ obj.uiid+";";
	}
//	if(currentType=="to"){
//		$("#toUserNames").val(names);
//		$("#toUserCds").val(codes);
//	}else{
//		$("#copyUserNames").val(names);
//		$("#copyUserCds").val(codes);
//	}
	$("#"+currentType+"UserNames").val(names);
	$("#"+currentType+"UserCds").val(codes);
}

/**
 * 弹出框右侧人员显示事件绑定
 * @param obj 人员信息
 * @param addFlag 是否需要在右侧添加该人
 * @return
 */
function bindEvents(obj,addFlag){
//	var map =(currentType=="to")?userMap:copyUserMap; 
	var map = getCurrMap(currentType);
	if(addFlag){
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
	$("#"+obj.uiid).click(function(){
		var className = $(this).attr("className");
		if(className == "userSelected"){
			$(this).attr("className","userUnSelected");
			delete map[obj.uiid];
		}else if(className == "userUnSelected"){
			if(!SELECTED_ALL_USER_PER){
				var i=0,j=0;
				for(var k in userMap){i++;};
				for(var k in copyUserMap){j++;};
				if(i+j>=MAX_SELECT_USER_NUM){
					alert("对不起，您权限不足，您最多同时发送给"+MAX_SELECT_USER_NUM+"个人(含收件人、抄送人)");
					return;
				}
			}
			$(this).attr("className","userSelected");
			map[obj.uiid] = obj;
		}
	});
	if(map[obj.uiid]){
		$("#"+obj.uiid).attr("className","userSelected");
	}
}

/**
 * 显示已经选中的人
 * @return
 */
function showSelectedUser(){
//	var map =(currentType=="to")?userMap:copyUserMap; 
	var map = getCurrMap(currentType);
	$("#userDisplay").empty();
	$("#deptDisplay").text("已选人员");
	for(var k in map){
		var obj = map[k];
		bindEvents(obj,true);
	}
	
}
/**
 * 根据部门代码加载该部门所有人员
 */
function getUsersByOrg(node,tag){
	var orgCds = node.getAllChildren(node,'extParam');
	var title = node.attributes.text;
	var orgCd = node.attributes.extParam; 
	var checked = node.checked;
	if(tag)checked = tag;
	$("#memberDiv").css("cursor","wait");
	$("#userDisplay").empty().addClass("waiting");
	$.post(_ctx+"/oa/oa-email!getUsersbyOrg.action",{orgCds:orgCds.join(",")},function(result){
		$("#deptDisplay").text(title);
		result = eval(result);
		$.each(result,function(i,n){
			if(n)bindEvents(n,true);
		});
		if(SELECTED_ALL_USER_PER){
			if(checked == '0'){delAll();}
			else if(checked == '1'){addAll();};
		}
		$("#memberDiv").css("cursor","");
		$("#userDisplay").removeClass("waiting");
	});
}

function addAll(){
	$("#userDisplay li.userUnSelected").trigger("click");
} 
function delAll(){
	$("#userDisplay li.userSelected").trigger("click");
}
function clearMember(tag){
	$("#"+tag+"UserNames").val("");
	$("#"+tag+"UserCds").val("");
	var map = getCurrMap(tag);
	for(var k in map){
		delete map[k];
	};
}

/**
 * 显示选择收件人/抄送人弹出框
 */
function getMember(tag){
	currentType=tag;
//	var map =(currentType=="to")?userMap:copyUserMap;
	var map = getCurrMap(currentType);
	var names = $("#"+tag+"UserNames").val().split(";");
	var cds = $("#"+tag+"UserCds").val().split(";");
	if(isEmptyObj(map)){
		$.each(names,function(i,n){
			if(n=="")return;
			var obj = {};
			obj.uiid = cds[i];
			obj.userName = names[i];
			map[obj.uiid] = obj;
		});
	};
	ymPrompt.alert({
		icoCls:"",
		title:"选择人员",
		message:"<div id='memberDiv'><img align='absMiddle' src='"+_ctx+"/images/loading.gif'></div>",
		showMask:false,
		winPos:"t",
		width:390,
		height:400,
		allowRightMenu:true,
		handler:getSelectedUser,
		afterShow:function(){
			$.post(_ctx+"/oa/oa-email!member.action",function(result){
				$("#memberDiv").empty().html(result);
				$("#searchDiv input").focus();
				if(!isEmptyObj(map)){
					showSelectedUser();
				}else{
					$("#userDisplay li").each(function(){
						var obj = {};
						obj.uiid = $(this).attr("id");
						obj.userName = $(this).text();
						bindEvents(obj,false);
					});
				}
			});
		}
	});
};
/**
 * 显示个人签名档
 */
function showEmailSign(){
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"签名档设置",
		message:"<textarea id='emailSignDiv' style='height:175px;width:500px;'></textarea>",
		winPos:"c",
		width:500,
		height:250,
		showMask:false,
		handler:saveEmailSign,
		afterShow:function(){
			$.get(_ctx+"/oa/oa-email!getEmailSign.action",function(result){
				mailSignEditor = $('#emailSignDiv').xheditor({
					tools:'simple',forcePtag:false,showBlocktag:false,
					upImgUrl:_ctx+"/oa/oa-email!upload.action",upImgExt:"jpg,jpeg,gif,png"
				});
				$('#emailSignDiv').val(result);
			});
		}
	});
}
/**
 * 保存个人签名档
 */
function saveEmailSign(tp){
	var content = mailSignEditor.getSource();
	if(tp=='ok'){
		$.post(_ctx+"/oa/oa-email!saveEmailSign.action",{signContent:content});
	}
}
/**
 * 刷新各个箱的数量
 */
function setNum2LeftBar(){
	$.post(_ctx+"/oa/oa-email!getMailNum.action",function(result){
		var num = eval(result);
		$("#inBoxNum").text(num[0]);
		$("#draftBoxNum").text(num[1]);
		$("#outBoxNum").text(num[2]);
		$("#delBoxNum").text(num[3]);
		$("#unReadBoxNum").text(num[4]);
		$("#unReadMailNum").text(num[4]);
	});
}
/**
 * 限制显示的字符个数，当超过后自动显示“查看全部”按钮
 */
function limit(){ 
    var self = $("table tr td[limit]"); 
    self.each(function(){ 
        var objString = $(this).text(); 
        var objLength = $(this).text().length; 
        var num = $(this).attr("limit"); 
        if(objLength > num){ 
			$(this).attr("title",objString); 
	        $(this).text(objString.substring(0,num) + "...");
	        var htmlStr = "<span class='lookAll'>查看全部</span>";
	        $(this).append(htmlStr);
	        $(this).find("span.lookAll").click(function(){alert(objString);});
        } 
    }); 
} 
function formEnter(event){
	var event = window.event?window.event:event;
	if(event.keyCode==13){
		$('#qucikSearchBtn').trigger('click');
	}
}

function showSearchUser(dom){
	var $currentDom = $(dom);
	var $next = $(dom).next();
	
	if(searchTime)clearTimeout(searchTime);
	searchTime = setTimeout(function(){
		$("#creatorCdId,#toUserCdId").val("");
		var val = $.trim($currentDom.val());
		//$next.val("");
		if(val == ""){
			$("#searchUserDiv").slideUp(200);
			return;
		}
		$.post(_ctx+"/oa/oa-email!getUsersByFilter.action",{value:val,maxNum:10},function(result){
			var $offset = $currentDom.offset();
			$("#searchUserDiv").css({left:$offset.left,top:$offset.top+$currentDom.height()}).empty().show();
			result = eval(result);
			$.each(result,function(i,n){
				var html = "<div uiid='"+n.uiid+"'><span>"+n.userName +"</span>|<span>"+ n.orgName+"</span></div>";
				$("#searchUserDiv").append(html);
			});
			$("#searchUserDiv div").click(function(){
				var userName = $(this).children("span:first").text();
				var uiid = $(this).attr("uiid");
				$currentDom.val(userName);
				//$next.val(uiid);
				$("#searchUserDiv").slideUp(200);
			});
		});
	}, 300);
	$(document).bind('click',function(event){
		var event  = window.event || event;
	    var obj = event.srcElement ? event.srcElement : event.target;
	    if(obj != dom && obj != $("#searchResult")[0]){
	    	$("#searchUserDiv").slideUp(200);
	    	//if($next.val() == ''){
	    	//	$currentDom.val('');
	    	//}
	    }
	    $(document).unbind('click');
	});
}
function changeUserFilter(dom){
	var v = $(dom).val();
	var $nextAll = $(dom).parent().nextAll();
	if(v == '3'){
		$nextAll.filter('[userType=send]').hide().find('input').val('');
		$nextAll.filter('[userType=receive]').show().find('input').val('');
	}else{
		$nextAll.filter('[userType=send]').show().find('input').val('');
		$nextAll.filter('[userType=receive]').hide().find('input').val('');
	}
}
function showEmailGroup(){
	window.parent.TabUtils.newTab("emailGroup","组",_ctx+"/oa/oa-email-group.action",true);
}
//从剪贴板黏贴上次自动保存的内容
function pasteFromCopy(){
	var paste_str = getClipboard();
	if(null!=paste_str){
		mailContentEditor.focus();
		mailContentEditor.toggleSource();
		mailContentEditor.setSource(paste_str+mailContentEditor.getSource());
		mailContentEditor.toggleSource();
	}
}
//保存到剪贴板的公用方法
function copyToClipboard(txt){
	if(window.clipboardData) {
		window.clipboardData.clearData();
		window.clipboardData.setData("Text", txt);
	} else if(navigator.userAgent.indexOf("Opera") != -1) {
		window.location = txt;
	} else if (window.netscape) {
		try {
			netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
		}catch(e){}
		var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
		if (!clip)return;
		var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
		if (!trans) return;
		trans.addDataFlavor('text/unicode');
		var str = new Object();
		var len = new Object();
		var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
		var copytext = txt;
		str.data = copytext;
		trans.setTransferData("text/unicode",str,copytext.length*2);
		var clipid = Components.interfaces.nsIClipboard;
		if (!clip)return false;
		clip.setData(trans,null,clipid.kGlobalClipboard);
	}
}
//返回剪贴板的内容
function getClipboard(){
   if (window.clipboardData) {
      return(window.clipboardData.getData('Text'));
   }
   else if (window.netscape) {
      netscape.security.PrivilegeManager.enablePrivilege('UniversalXPConnect');
      var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
      if (!clip) return;
      var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
      if (!trans) return;
      trans.addDataFlavor('text/unicode');
      clip.getData(trans,clip.kGlobalClipboard);
      var str = new Object();
      var len = new Object();
      try {
         trans.getTransferData('text/unicode',str,len);
      }
      catch(error) {
         return null;
      }
      if (str) {
         if (Components.interfaces.nsISupportsWString) str=str.value.QueryInterface(Components.interfaces.nsISupportsWString);
         else if (Components.interfaces.nsISupportsString) str=str.value.QueryInterface(Components.interfaces.nsISupportsString);
         else str = null;
      }
      if (str) {
         return(str.data.substring(0,len.value / 2));
      }
   }
   return null;
}

//在发件箱的查看收件状况时，删除未读的收件人
function deleteUserBySingle(deleteBodyId,deleteUserCd){
	$.post(_ctx+"/oa/oa-email!deleteUserBySingle.action",{id:deleteBodyId,deleteUserCd:deleteUserCd},function(result){
		$("#userMailStatusDiv").html(result);
	});
}
