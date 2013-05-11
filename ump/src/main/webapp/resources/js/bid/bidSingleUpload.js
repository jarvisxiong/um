function showUploadedFile(id,fieldId,canEdit) {
	if (id){
	}else{
		return;
	}
	var url = _ctx+ "/bid/bid-app-attachment!load.action";
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

function showMultiUploadedFile(id,fieldId,canEdit) {
	if (id){
	}else{
		return;
	}
	var url = _ctx+ "/bid/bid-app-attachment!loadMulti.action";
	var data = {
		id : id,
		domId : fieldId,
		canEdit : canEdit,
		single:'1'
	};
	$.post(url, data, function(result) {
		$("#show_"+fieldId).append(result);
	});
}


/**
 * 上传附件的onClick事件
 * 
 * @param titileName   弹出下载框的标题
 * @param bizEntityId  关联表的id
 * @param bizModuleCd  上传路径
 * @param domId        附件表的BIZ_FIELD_NAME字段的值
 * @param bizEntityName 关联的实体的类名
 * @param fieldName    关联的实体的上传状态的属性名
 */
function showUploadSingleAttachDialog(titileName,bizEntityId, bizModuleCd,domId,bizEntityName,fieldName) {
	ymPrompt
			.confirmInfo({
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
					var url = _ctx+ "/bid/bid-app-attachment!singleUpload.action";
					var data = {
						bizEntityId : bizEntityId,
						bizModuleCd : bizModuleCd,
						id:$("#"+domId).val(),
						domId:domId,
						bizEntityName:bizEntityName,
						fieldName:fieldName
					};
					$.post(url, data, function(result) {
						$("#singleAttachDiv").html(result);
					});
				},
				handler : doSingleUpload,
				autoClose : false
			});
}

/**
 * 上传附件的onClick事件---(上传多个文件)
 * 
 * @param titileName   弹出下载框的标题
 * @param bizEntityId  关联表的id
 * @param bizModuleCd  上传路径
 * @param domId        附件表的BIZ_FIELD_NAME字段的值
 * @param bizEntityName 关联的实体的类名
 * @param fieldName    关联的实体的上传状态的属性名
 */
function showUploadMultiAttachDialog(titileName,bizEntityId, bizModuleCd,domId,bizEntityName,fieldName) {
	ymPrompt
			.confirmInfo({
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
					var url = _ctx+ "/bid/bid-app-attachment!multiUpload.action";
					var data = {
						bizEntityId : bizEntityId,
						bizModuleCd : bizModuleCd,
						id:$("#"+domId).val(),
						domId:domId,
						bizEntityName:bizEntityName,
						fieldName:fieldName
					};
					$.post(url, data, function(result) {
						$("#singleAttachDiv").html(result);
					});
				},
				handler : doMultiUpload,
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
		TB_showMaskLayer("正在上传...");	
		$("#attachSingleForm").ajaxSubmit(function(result) {
			TB_removeMaskLayer();
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

function doMultiUpload(btn) {
	//确定
	if (btn == 'ok') {
		var fileName = $("#attachSingleForm_uploadInput").val();
		if (!fileName) {
			alert("请选择待上传的文件!");
			return;
		}
		var fieldId=$("#domId").val();
		TB_showMaskLayer("正在上传...");	
		$("#attachSingleForm").ajaxSubmit(function(result) {
			TB_removeMaskLayer();
			if (result.indexOf("success") != -1) {
				var rtn = result.split(",");
				if ('success' == rtn[0]) {
					//$("#projIntrDesc").val(fieldId.substring(11,12));
					$("input[name='hasEvalFiles']").val('1');
				 	$("#"+fieldId).val(rtn[2]);
					$("#"+fieldId).parent().siblings("[validate]").attr("value",rtn[2]);
					showMultiUploadedFile(rtn[2],fieldId,true);
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


function deleteFile(fileId,fieldId,bizEntityId,bizEntityName){
	var url = _ctx+ "/bid/bid-app-attachment!delete.action";
	var data = {
		id : fileId,
		bizEntityName:bizEntityName,
		bizEntityId:bizEntityId
	};
	$.post(url, data, function(result) {
		if($("#show_"+fieldId+" div[attaId='"+fileId+"']")[0]){
			$("#show_"+fieldId+" div[attaId='"+fileId+"']").html('&nbsp;');
		}else{
			$("#show_"+fieldId).html('&nbsp;');
		}
			$("#"+fieldId).parent().siblings("[validate]").attr("value",'');
			$("#"+fieldId).val('');
			$("#isModified").val(true);
		});
}