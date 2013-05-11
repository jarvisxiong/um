
//Dependencies: _ctx,app_attachment.jsp

function attach_AddNewFile() {
	var trUpload = $("#trUploadTemplate").clone();
	trUpload.appendTo("#downTable");
	trUpload.append("<td onclick='attach_remove(this);' style='cursor: pointer;text-align:left;padding-left:5px;'><font style='color:red;font-weight:bold;cursor:pointer;' title='可以点击移除'>×</font>删除</td>");
}
function attach_remove(dom){
	$(dom).parent().remove();
}
function attach_changeMainFlg(dom,appAttachFileId,bizEntityId,bizEntityName,mainFlg,bizMainEnableFlg){
	$("#loading").html("设置中，请稍候...");
	var mainFlg=$(dom).attr("checked");
	var url= _ctx + "/app/app-attachment!changeMainFlg.action";
	var data = {
		id : appAttachFileId,
		bizEntityId: bizEntityId,
		bizEntityName: bizEntityName,
		mainFlg: mainFlg,
		bizMainEnableFlg:bizMainEnableFlg
	};
	$.post(url, data, function(result){
		$("#attachFileMainDiv").parent().html(result);
	});
}
function attach_doUpload(){
	$('#mainForm').form('submit', {
		url: _ctx + "/app/app-attachment!upload.action",
		onSubmit: function(){
			var flag=$('#mainForm').form('validate');
			if(flag){
				$('#attachFileMainDiv').mask('上传附件，请稍候...');
			}
			return flag;
		},
		success:function(result){
			$('#attachFileMainDiv').unmask();
			$("#attachFileMainDiv").parent().html(result);
		}
	});
}

function attach_deleteAttach(appAttachFileId,bizModuleCd,bizEntityId,bizEntityName,filterType,attachModelType){
	var url = _ctx+"/app/app-attachment!delete.action";
	var data = {
		id: appAttachFileId,
		bizModuleCd: bizModuleCd,
		bizEntityId: bizEntityId,
		bizEntityName: bizEntityName,
		filterType: filterType,
		attachModelType: attachModelType
	};
	
	$("#attachFileMainDiv").mask('操作中,请稍后...');
	$.post(url, data, function(result){
		$("#attachFileMainDiv").parent().html(result);
	});
}

function showUploadFile(conf,entityTmpId,windowId,bMultiFileFlg){
	//是否允许操作主图:1-是 0-否
	if($.trim(conf['bizEntityId']) == ''){
		conf['bizEntityId'] = entityTmpId;
		conf['bizMainEnableFlg'] = '0';
	}
	var url =  _ctx+'/app/app-attachment!list.action';
	$.post(url,conf,function(result){
		$('#'+windowId).html(result);
		$('#'+windowId).window('open');
	
		$.parser.parse('#attachFileMainDiv');

		//多个附件
		if(bMultiFileFlg){
			attach_AddNewFile();
			attach_AddNewFile();
			attach_AddNewFile();
		}
	});
}