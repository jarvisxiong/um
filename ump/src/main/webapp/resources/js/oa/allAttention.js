//增加关注(模块cd,模块记录的id)
function doAddAttention(moduleCd,entityId,moduleRecordVersion){
	var data={moduleCd:moduleCd,entityId:entityId,moduleRecordVersion:moduleRecordVersion};
	$.post(_ctx+"/oa/oa-all-attention!save.action",data, function(result) {
		if (result=="success"){
			$("#attention_"+entityId).show();
			$("#attention_cancel_"+entityId).hide();
			try{
				$("#scheForm_"+entityId+" input[name='isAttentioned']").val("attention");
			}catch(e){}
		}else{
			ymPrompt.alert({message:result,slideShowHide:false,title:"提示",width:220,height:180});
		}
		try{
			$("#pop_bg").hide();
		}catch(e){}
	});
	try{
		$("#pop_bg").show();
	}catch(e){}
}
//删除关注(模块cd,模块记录的id)
function doDeleteAttention(moduleCd,entityId){
	var data={moduleCd:moduleCd,entityId:entityId};
	$.post(_ctx+"/oa/oa-all-attention!delete.action",data, function(result) {
		if (result=="success"){
			$("#attention_"+entityId).hide();
			$("#attention_cancel_"+entityId).show();
			try{
				$("#scheForm_"+entityId+" input[name='isAttentioned']").val("");
			}catch(e){}
		}else{
			ymPrompt.alert({message:result,slideShowHide:false,title:"提示",width:220,height:180});
		}
		try{
			$("#pop_bg").hide();
		}catch(e){}
	});
	try{
		$("#pop_bg").show();
	}catch(e){}
}

//单点设置为已读，取id方式
function setAttentionRead(moduleCd,entityId){
	var ifDelete = "false";
	var attention_status = 0;
	var recordVersion = "";
	recordVersion = $("#attention_recordVersion_"+entityId).html();
	attention_status = $("#attention_status_"+entityId).html();
	if("oaFileFollowed"==moduleCd){
		if(6==attention_status){
			ifDelete = "true";
		}
	}else if("planWork"==moduleCd || "planWorkCenter"==moduleCd || "oaMeeting"==moduleCd){
		if(3==attention_status){
			ifDelete = "true";
		}
	}
	if(null!=recordVersion && ""!=recordVersion){
		var param = "&moduleCd="+moduleCd+"&entityIds="+entityId+"&recordVersions="+recordVersion+"&ifDeletes="+ifDelete;
		$.post(_ctx+"/oa/oa-all-attention!setAttentionRead.action", param, function(result) {
			if(result == "error"){
				alert("操作失败！");
				return;
			}
			if (result == "done") {
				$("#attention_unread_"+entityId).html("");
				$("#attention_unread_img_"+entityId).css("display","none");
			}
		});
	}
}

//批量设置为已读，取id方式
function setAttentionReadAll(moduleCd,chk_name,byType){
	if(null==byType){
		byType = "id";
	}
	var entityIds = new Array();
	var recordVersions = new Array();
	$("input[type=checkbox]["+byType+"='"+chk_name+"']:checked").each(function(i, dom) {
		entityIds.push("entityIds=" + $(dom).val());
		recordVersions.push("recordVersions=" + $(dom).attr("recordVersion"));
	});
	var param = "&moduleCd="+moduleCd+"&"+entityIds.join("&")+"&"+recordVersions.join("&");
	$.post(_ctx+"/oa/oa-all-attention!setAttentionRead.action", param, function(result) {
		if(result == "error"){
			alert("操作失败！");
			return;
		}
		if (result == "done") {
			self.location = self.location;
		}
	});
}
