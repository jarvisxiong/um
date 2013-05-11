KindEditor.ready(function(K) {

	editor = K.create('textarea[name="content"]', {
				width : "100%",
				height : ($(document).height() - 180) + "px",
				allowFileManager : true,
				items : []
			});
	// 全部设成只读状态
	editor.readonly();
	$("#markList").css("height", ($(document).height() - 180) + "px");
	$("#markFillList").css("height", ($(document).height() - 180) + "px");
	// 转换填充的数据
	var _fillJson = toJson($("#fillJson").val());
	// 编辑器控件
	_editor = $("#keEditIframe");
	$(_editor).contents().find("span[otype=min]").unbind("click");
	$(_editor).contents().find("span[otype=min]").each(function(idx, input) {
		var _len = $(input).attr("len");
		if (_len == undefined) {
			_len = 10;
		}
		$(input)
				.attr(
						"style",
						_Inputcss
								+ _len
								+ "px;border-left:0px;border-bottom:1px solid #000;background:#DEE1DD;");
		$(this).html("&nbsp;&nbsp;");

		if ($("#isCanInput").val() != "" && _fillJson.length > 0) {
			$(input).attr("id", _fillJson[idx].contractTempletFillId);
			if (_fillJson[idx].contractFillContent.trim() != "") {
				$(input).text(_fillJson[idx].contractFillContent);
				$(input).val(_fillJson[idx].contractFillContent);
				$("#fillRecordVersion").val(_fillJson[idx].recordVersion);
			}
			$("#markTop").hide();
		}

	});

	$("#contractNo").attr("disabled", true);
	$("#curUserName").attr("disabled", true);
	$("#isMark").val("true");
	$("#btDel").show();
	$("#contractName").attr("disabled", true);
	$("#projectName").attr("disabled", true);
	if ($("#isCanInput").val() == "20") {
		$("#markList").show();
		$("#markTop").show();
		fillList();
	}

	if ($("#isCanInput").val() == "40") {// 已合并
		$("#contDivLeft").attr("style", "width:100%; float: left;");
		$("#markDiv").hide();
		$("#markTop").hide();
		$(_editor).contents().find("span[otype=min]").css("background-color",
				"");
		// 清除删除内容
		$("#keEditIframe").contents().find("ins[isdel=true]").css("display",
				"none");

	}

	recurentlyConInfo();
	$(".ke-toolbar").remove();
	reloadAttachs();
	reloadContractMsg("");
	reloadHisRecordVersion();
	if($("#resApproveInfoId").val() == ""){
		//mergeCont();
		showBookMark();
	}else{
		showBookMark();
	}
	
	
});



// 返回
function doReturn() {
	window.location.href = _ctx + "/sc/sc-contract-templet-info.action";
}




// 是否可以可编辑输入框数据
function isCanEditor() {
	if ($("#isCanInput").val() == "10" || $("#isCanInput").val() == "") {
		return true;
	}
	return false;
}
// 是否许用户删除当前合同
function isDel() {
	if ($("#isAdd").val() == "1") {
		return false;;
	}
	return true;
}
/**
 * 删除合同
 */
function doDelete() {
	
		ymPrompt.confirmInfo({message:'删除不可恢复,你确认要删除当前合同吗？',title:"合同删除",handler:function (tp){
		if (tp=='ok'){
		
				TB_showMaskLayer("请稍等,正在删除合同...");
	$.post(_ctx + "/sc/sc-contract-templet-info!delete.action", {
				id : $("#scContractId").val()
			}, function(result) {
				var _rJson = toJson(result);
				if (_rJson != undefined && _rJson.status != undefined) {
					if (_rJson.status != true) {
						if (_rJson.errorMsg.indexOf("could not execute") > -1) {
							
							ymPrompt.alert({
										message : "当前合同不能被刪除！",
										title : "提示",
										handler : function(tp) {
											
										}
									});
						
						} else {
							ymPrompt.alert({
										message :_rJson.errorMsg,
										title : "提示",
										handler : function(tp) {
											
										}
									});
						
						}

					} else {
						TB_removeMaskLayer();
						
						ymPrompt.alert({
										message : "当前合同删除成功！",
										title : "提示",
										handler : function(tp) {
											window.location.href = _ctx
													+ "/sc/sc-contract-templet-info.action";
										}
									});
						
						

						return;
					}
				} else {
					
					ymPrompt.alert({
											message : "无法解析返回的信息！",
											title : "提示",
											handler : function(tp) {

											}
										});
		
				}
				TB_removeMaskLayer();
			});			
		}}});	
	
}