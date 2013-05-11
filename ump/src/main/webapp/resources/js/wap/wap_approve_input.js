var SEARCH_VALUE = '搜索...';
var ID_CONTENT_DIV = 'divContent';// 
var authTypeCd,id;
var selectOpinion,pageNo;
var shareUserNames,shareUserCds;
var delayApproveUserCd='',delayApproveUserName='';
var agreeUrl = _ctx+'/wap/wap-approve-info!agree.action';
var backUrl=_ctx+'/wap/wap-approve-info!back.action';
//重写打开详细页面方法
function init(resApproveInfoId,selectOpinion,pageNo) {
	window.id=resApproveInfoId;
	window.selectOpinion=selectOpinion;
	window.pageNo=pageNo;
}
//打开明细
function openDetail(){
	var data={
			id : window.id,
			'page.pageNo' : window.pageNo 
		};
		
		$.post(_ctx + "/wap/wap-approve-info!input.action",data , function(result) {
			$("#"+ID_CONTENT_DIV).html(result);
	});
	mapTree = {};
}

//重写同意按钮方法
function doAgree(nodeCd,curUserCd,isJcw){
	var wapAgreeUrl=_ctx+'/wap/wap-approve-info!agree.action';
	if('21' == nodeCd || curUserCd=='xujk'){
		//21-总裁
		var signFile = $("#signFile").val();
		if($.trim(signFile) == ''){
			alert('请上传总裁签字');
			return;
		}
		doProcess(wapAgreeUrl,"同意");
	}else if(curUserCd=='xiaoqp'){
		var signFileX = $("#signFileX").val();
		if($.trim(signFileX) == ''){
			alert('请上传肖总签字');
			return;
		}
		doProcess(wapAgreeUrl,"同意");
	}else if (isJcw=='true'){
		//企管部，决策委
		var uploadFlg=$("#uploadFlg").val();
		if (uploadFlg!='true'){
			alert('请上传附件');
			return;
		}
		doProcess(wapAgreeUrl,"同意");
	}else{
		doProcess(wapAgreeUrl,"同意");
	}
}

// 驳回
function doBack(){
	var approveRemark=$("#approveRemark").val();
	if(isEmpty(approveRemark)){
		alert('请填写驳回原因!');
		return;
	}
	var r =confirm('直接驳回给发起人？');
	if(r == true){
		doProcess(backUrl,"驳回");
	}
}

function initSrhData(){
	var data={
			'page.pageNo':pageNo,
			selectOpinion:selectOpinion
			};
	return data;
}
//重写处理方法，以便于返回手机版页面
function process(url,fn,remark){
	var rejectTo=$("#idRejectTo").val();
	var addNode=$("#idAddNode").val();
	var data=initSrhData();
	var flag = false;
	data.id=window.id;
	data.approveRemark=$("#approveRemark").val();
	if(isNotEmpty(addNode)){
		data["addNode"] = addNode;
	}
	if(isNotEmpty(rejectTo)){
		data["rejectTo"] = rejectTo;
	}
	if(isEmpty($("#approveRemark").val())){
		data["approveRemark"] = remark;
	}
	var isEdit=$("#isEdit").val();
	if (isEdit=='true'&& url!=backUrl){
		data["isEdit"] = isEdit;
		//合同跟踪人确认
		$(".param").each(function(i,dom){
			data[$(dom).attr("name")]=$(dom).val();
		});
		$("#billContent [edit='true']").each(
				function() {
					if ( $(this).attr("validate") == "required" && $(this).val()=="") {
						$(this).focus();
						$(this).css({"color":"red","border":"2px solid red"});
						flag = true;
					}else{
						data[$(this).attr("name")]=$(this).val();
					}
				}
		);
		if(flag)
		{	alert("该表单需要填写数据!!");
			return null;
		}
		$.post(url,data,
				function(result) {
					if(result=='fail'){
						alert('操作失败,您无权处理该记录!');
					}else{
						window.location.href=_ctx+'/wap/wap-approve-info.action';
						$("#content").html(result);
					}
					if (fn) fn();
			});
	}else{
		//合同跟踪人确认
		$(".param").each(function(i,dom){
			//alert($(dom).attr("name")+":"+$(dom).val());
			data[$(dom).attr("name")]=$(dom).val();
		});
		//return false;
		$.post(url,data,
			function(result) {
				if(result=='fail'){
					alert('操作失败,您无权处理该记录!');
				}else{
					window.location.href=_ctx+'/wap/wap-approve-info.action';
					$("#content").html(result);
//					resetLeftPanel();
				}
				if (fn) fn();
		});
	}
}

function loadApprove(){
	var resApproveInfoId = window.id;  
	if (resApproveInfoId!=''){
		var data={id:resApproveInfoId};
		$.post(_ctx+"/wap/wap-approve-info!loadApprove.action",data,
				 function(result) {
			 $("#divApprove").html(result);
			});
	}
}
//重载
function reloadMsg(content){
		var resApproveInfoId = window.id;
		if (resApproveInfoId!=''){
			var replyMsgId = $('#idReplyMsg').val();
			var isShare = $('#isShare').val();  
			var data={id:resApproveInfoId,'referMsgId':replyMsgId};
			
			if(isNotEmpty(content)){
				data.content=content;
			}
			if(isNotEmpty(isShare)){
				data.isShare=isShare;
			}
			$.post(_ctx+"/wap/wap-approve-info!say.action",data,
					 function(result) {
					 $("#msgContent").html(result);
				});
			$("#comment").css("border","0px solid red");
		}
	}
//重写
function doRollback(){
		var r =confirm('确认追回记录？');
		if (r == true) {
			var data=initSrhData();
			data.id=window.id;
			data.recordVersion=$("#recordVersion").val();
			$.post(_ctx+"/res/res-approve-info!rollback.action",data,
				function(result) {
					if(result=='fail'){
						alert('撤回失败，对方已经签批!');
					}else{
						 //$("#content").html(result);
						 window.location.href=_ctx+'/wap/wap-approve-info.action';
//						 resetLeftPanel();
					}
			});
		}
	}
function back2Last(){
	var r =confirm('确认追回记录？');
	if (r == true) {
		if (tp=='ok'){
		var data=initSrhData();
		data.id=window.id;
		data.recordVersion=$("#recordVersion").val();
		$.post(_ctx+"/res/res-approve-info!back2Last.action",data,
			function(result) {
				if(result=='fail'){
					alert('撤回失败');
				}else{
					 $("#content").html(result);
					 resetLeftPanel();
				}
		});
		}
	}
}
function doMeeting(){
	doProcess(agreeUrl,"完成",openMeeting);
	
}
function doComplete(){
	doProcess(agreeUrl,"同意");
}
function doProcess(url,title,fn){
	var approveRemark=$("#approveRemark").val();
	if(approveRemark==''){
		var r = confirm('您的意见为空，将默认填写同意，请问是否继续？');
		 if (r == true) {
			 if(title=="驳回"){
					$("#isEdit").val('false');
					$("#approveRemark").val('驳回');
				}else if(title=="同意"){
					$("#approveRemark").val('同意');
				}
				process(url,fn);
		}
	}else{
		process(url);
	}
}
//资料不全/驳回方法
function doProcessMust(url,title,fn){
	var approveRemark=$("#approveRemark").val();
	if(approveRemark==''){
		alert('请输入你的意见！');
	}else{
		process(url);
	}
}

// 查看审批历史
function viewApproveHis() {
	var id =window.id;
	$.post(_ctx + "/wap/wap-approve-info!showApproveHis.action",{id:id} , function(result) {
		$("#"+ID_CONTENT_DIV).html(result);
	});
}
function viewSteps(authTypeCd) {
	$.post(_ctx + "/wap/wap-approve-info!viewSteps.action",{resAuthTypeCd:authTypeCd} , function(result) {
		$("#"+ID_CONTENT_DIV).html(result);
	});
}
//加载共享人
function loadShare(){
	var id =window.id;
	if(isNotEmpty(id)){
		var url = _ctx + '/res/res-approve-info!loadShare.action';
		$.post(url,  
				{id:id},
				function(result) {
					if(result!='fail'){
						$('#resSharedList').html(result);
					}
				}
			);
	}
}
//打开共享页面
function doShare(){
	var url = _ctx + '/wap/wap-approve-info!doShare.action';
	$.post(url,  
			{id:window.id},
			function(result) {
				if(result!='fail'){
					$('#'+ID_CONTENT_DIV).html(result);
					setSelectedUser();
				}
			}
		);
}

isEmpty = function (str) {
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
};
isNotEmpty = function (str) {
	return (!isEmpty(str));
};
/****************延期相关-start*********************/
//查看延期申请历史记录
function viewDelayApproveHistory(){
	var url = _ctx + '/wap/wap-approve-delay!history.action';
	$.post(url,  
			{resApproveId:window.id},
			function(result) {
				$('#'+ID_CONTENT_DIV).html(result);
			}
		);
}

//取消延期
function cancelApproveDelay(){
	var id =window.id;
	if(!window.confirm('确认取消?')){
		return;
	}
	$.post(_ctx + "/res/res-approve-delay!cancelApproveDelay.action",{resApproveId: id}, function(result) {
		if ('success' == result) {
			$('#resDelayProcessTip').text('已撤销延期申请!').show().fadeIn(2000).fadeOut(5000);
			$('#resDelayProcess').html('');
			$('#btnDoApproveDelay').show();
			$('#btnCancelApproveDelay').hide();
		}else if('process' == result){
			$('#resDelayProcessTip').text('');
			$('#resDelayProcess').html('');
			$('#btnDoApproveDelay').show();
			$('#btnCancelApproveDelay').hide();
			
			alert('您的延期申请已处理,载入中...');
			loadDelay();
		}else{
			alert(result);
		}
	});
}
//发起延期
function doApproveDelay(){
	var url = _ctx + '/wap/wap-approve-delay!apply.action';
	$.post(url,  
			{resApproveId:id},
			function(result) {
				if(result!='fail'){
					$('#'+ID_CONTENT_DIV).html(result);
					$('#delayApproveUserName').val(delayApproveUserName);
					$('#delayApproveUserCd').val(delayApproveUserCd);
				}
			}
		);
}


//加载延期信息
function loadDelay(){
	var id =window.id;
	$.post(_ctx + "/wap/wap-approve-delay!loadChief.action",{resApproveId: id}, function(result) {
		$('#resDelayProcess').html(result);
	});
}


//审核同意
function onChiefAgree(resApproveDelayId){
	comChiefDelay(resApproveDelayId, '1');
}

//审核拒绝
function onChiefDegree(resApproveDelayId){
	comChiefDelay(resApproveDelayId, '2');
}
function comChiefDelay(resApproveDelayId, type){
	var id =window.id;
	$.post(_ctx + "/wap/wap-approve-delay!reason.action",{id: resApproveDelayId,delayChiefTypeCd: type}, function(result) {
		$('#'+ID_CONTENT_DIV).html(result);
	});
	
}
function submitDelayChiefReason(resApproveDelayId,type){
	var delayReason = $.trim($('#delayReason').val());
	if( delayReason == ''){
		alert('请输入意见!');
		$('#delayReason').focus();
		return;
	}
	if('1' == type){
		if(!window.confirm('确认同意?')){
			return;
		}
		$.post(_ctx + "/res/res-approve-delay!chiefAgree.action",{resApproveDelayId: resApproveDelayId, reason: delayReason}, function(result) {
			if('success' == result){
				$('#resDelayProcessTip').text('您已同意申请延期!').show().fadeIn(2000);
				$('#resDelayProcess').html('');
				openDetail();
			}else{
				alert(result);
			}
		});
	}
	else if('2' == type){
		if(!window.confirm('确认拒绝?')){
			return;
		}
		$.post(_ctx + "/res/res-approve-delay!chiefDegree.action",{resApproveDelayId: resApproveDelayId, reason: delayReason}, function(result) {
			if('success' == result){
				$('#resDelayProcessTip').text('您已拒绝申请延期!').show().fadeIn(2000);
				$('#resDelayProcess').html('');
				openDetail();
			}else{
				alert(result);
			}
		});
	}
}
//延期人员选择
function openChooseUser(){
	var url = _ctx + '/wap/wap-approve-info!userChoose.action';
	$.post(url,  
			{id:window.id},
			function(result) {
				if(result!='fail'){
					$('#'+ID_CONTENT_DIV).html(result);
					setSelectedUser();
				}
			}
		);
}
function doProcessError(){
	process(backUrl,"流程错误，请查看权责手册，并请选择正确的表单发起审批！");
}

function doProcessError2() {
	var approveRemark=$("#approveRemark").val();
	if(isEmpty(approveRemark)){
		alert("请填写详细原因！");
	}else{
		$("#isEdit").val('false');
		process(backUrl);
	}
}
//给执行总裁
function toCeo(){
	if(window.confirm("确认直接给执行总裁？")){
		$("#idAddNode").val('toCeo');
		doProcessMust(agreeUrl,"给执行总裁",null);
	}
	
}
//给执行总裁
function toPresident(){
	if(window.confirm("确认直接给总裁？")){
		$("#idAddNode").val('toPresident');
		doProcessMust(agreeUrl,"给总裁",null);
	}
	
}
/****************延期相关-end*********************/
/****************附件-start********************/
function showUploadedFile(id,fieldId,canEdit) {
	if (isEmpty(id)){
		return;
	}
	var url = _ctx+ "/app/app-attachment!list.action";
	var data = {
		id : id,
		domId : fieldId,
		canEdit : canEdit,
		single:'1'
	};
	$.post(url, data, function(result) {
		$("#show_"+fieldId).html(result);
	});
}
/****************附件-end********************/
//驳回
function showReject() {
	var id =window.id;
	var appRemark = $("#approveRemark").val();
	$.post(_ctx + "/wap/wap-approve-info!showRejectTo.action",{id:id} , function(result) {
		$("#"+ID_CONTENT_DIV).html(result);
		$("#approveRemark").val(appRemark);
	});
}