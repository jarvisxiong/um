function showUploadedFile(id,fieldId,canEdit) {
	if (isEmpty(id)){
		return;
	}
	var url = _ctx+ "/app/app-attachment!load.action";
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

/**
 * 上传附件的onClick事件
 * 
 * @param titileName 弹出下载框的标题
 * @param bizEntityId 
 * @param bizModuleCd
 * @param id
 * @param domId
 */
function showUploadSingleAttachDialog(titileName,bizEntityId, bizModuleCd,domId) {
	ymPrompt
			.confirmInfo( {
				icoCls : "",
				title : titileName,
				message : "<div id='singleAttachDiv'><img align='absMiddle' src='"
						+ _ctx + "/images/loading.gif'></div>",
				useSlide : true,
				winPos : "c",
				width : 400,
				height : 130,
				maxBtn : false,
				allowRightMenu : true,
				afterShow : function() {
					var url = _ctx+ "/app/app-attachment!singleUpload.action";
					var data = {
						bizEntityId : bizEntityId,
						bizModuleCd : bizModuleCd,
						id:$("#"+domId).val(),
						domId:domId,
						bizEntityName:'ResApproveInfo'
					};
					$.post(url, data, function(result) {
						$("#singleAttachDiv").html(result);
					});
				},
				handler : doSingleUpload,
				autoClose : false
			});
}
function doSingleUpload(btn) {
	//确定
	if (btn == 'ok') {
		var fileName = $("#attachSingleForm_uploadInput").val();
		if (!fileName) {
			alert("请选择待上传的文件!");
			return;
		}
		var fieldId=$("#domId").val();
		
		$("#attachSingleForm").ajaxSubmit(function(result) {
			if (result.indexOf("success") != -1) {
				var rtn = result.split(",");
				if ('success' == rtn[0]) {
				 	$("#"+fieldId).val(rtn[2]);
					$("#"+fieldId).parent().siblings("[validate]").attr("value",rtn[2]);
					showUploadedFile(rtn[2],fieldId,true);
					$("#isModified").val(true);
				} else {
					alert(result);
				}
			} else {
				alert(result);
			}
		});
	}
	//取消
	else if (btn == 'cancel') {

	} else {

	}
	ymPrompt.close();
}
function deleteFile(fileId,fieldId){
	var url = _ctx+ "/app/app-attachment!delete.action";
	var data = {
		id : fileId,
		bizEntityName:'ResApproveInfo',
		bizEntityId:'${resApproveInfoId==null?entityTmpId:resApproveInfoId}'
	};
	$.post(url, data, function(result) {
			$("#show_"+fieldId).html('');
			$("#"+fieldId).parent().siblings("[validate]").attr("value",'');
			$("#"+fieldId).val('');
			$("#isModified").val(true);
		});
}