var _PATH = _ctx;

function displayCtrlPanel(){
	var myMod = $("#myMod").val();
	var curUser  = $("#curUser").val(); 
	var lockUser = $("#lockUser").val(); 
	
	//会签
	if( myMod == 'HQ'){
		
		//未锁定
		if(lockUser == ''){
			$("#btnDownFile").show();
			$("#btnLockDownFile").show();
			$("#btnLockFile").show();
		}
		//当前用户锁定
		else if(lockUser == curUser){
			$("#btnUnlockFile").show();
			$("#btnUploadFile").show();
		}else{
			$("#btnUploadFile").hide();
		}
	}
}
function loadLockTip(id,myMod,lockUser) {
	//alert("loadLockTip:"+id);
	$.post(_PATH + "/res/res-approve-info!loadLockTip.action", {id : id}, function(result) {
		$("#lock_user_tip").html(result);
	});
}
function isImage(fileName) {
	$.post("${ctx}/app/app-attachment!isImage.action", {
		fileName : fileName
	}, function(result) {
		if (result) {
			$("#main_" + dataId).remove();
			$("#detail_" + dataId).remove();
		}
	});
}
function download(dom) {
	$(dom).parents('form').submit();
	// $('#downloadForm').submit();
}
function openImage(url) {
	window.open(url);
}

// 锁定并下载
function lockDownFile(bizEntityId, url, fileName, myMod,lockUser ) {
	$.post(_PATH + "/res/res-approve-info!lock.action", {id : bizEntityId}, function(result) {
		if('success' == result){
			//alert('锁定成功!');
			$("#btnLockDownFile").hide();
			$("#btnLockFile").hide();
			$("#btnUploadFile").show();
			$("#btnUnlockFile").show();
			openAtta(url, fileName);
			loadLockTip(bizEntityId);
		}else{
			alert(result);
		}
	});
	//loadAttachment(bizEntityId,myMod,lockUser);
} 
// 锁定
function lockFile(bizEntityId) {
	$.post(_PATH + "/res/res-approve-info!lock.action", {id : bizEntityId}, function(result) {
		if('success' == result){
			//alert('锁定成功!');
			$("#btnLockDownFile").hide();
			$("#btnLockFile").hide();
			$("#btnUploadFile").show();
			$("#btnUnlockFile").show();
			loadLockTip(bizEntityId);
		}else{
			alert(result);
			loadLockTip(bizEntityId);
		}
	});
}
// 解锁
function unlockFile(bizEntityId,myMod,lockUser) {
	//alert(id);
	$.post(_PATH + "/res/res-approve-info!unlock.action", {id : bizEntityId}, function(result) {
		if('success' == result){
			//alert('解锁成功!');
			$("#btnLockDownFile").show();
			$("#btnLockFile").show();
			$("#btnUploadFile").hide();
			$("#btnUnlockFile").hide();
			$("#lock_user_tip").html('');
		}else{
			alert(result);
		}
	});

	loadAttachment(bizEntityId,myMod,lockUser);
}

// 上传
function uploadFileForm(bizEntityId,myMod,lockUser) {
	$("#attachForm").ajaxSubmit(function(result) {
		if('success' == result){
			unlockFile(bizEntityId,myMod,lockUser);
		}else{
			alert(result);
		}
	});
}
// 下载
function downFile(url,fileName) {
	openAtta(url, fileName);
} 