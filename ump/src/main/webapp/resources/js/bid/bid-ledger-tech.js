
//onchange
function saveJudgeForm(){
	$("#submitJudgeForm").ajaxSubmit(function(result){
		if("success"==result){
			$('#succInfoId').show().fadeOut(3000);
		}else{
			alert('数据过长出错！');
			$("#judgeName").val('');
			$("#judgeName").next().val('');
		}
	});
}



//计算拖拉的高度和宽度
function calculateLength(){
	//$("#rightDownDiv").css("width",(Number($(document).width())-250)+"px"); 
}
function bidLedgerSave(dom,ledgerId){
	var value=$(dom).val().trim();
	var url=_ctx+"/bid/bid-tech-item!saveLedger.action";
	if(""!=value){
	    param={bidLedgerId:ledgerId,totalRankDesc:value};
		$.post(url,param,function(result){	
			if("success"==result){
				$('#succInfoId').show().fadeOut(3000);
			}else{
				alert('数据过长出错！');
			}
		});
	}
} 


/**弹出附件窗口，并更新更新附件状态
 * @param jdom 附件图标jquery对象
 * @param entityId 供应商ID
 * @param bizModuleCd 模块类型 
 */ 
function openAttachmentRefresh(jdom,entityId,bizModuleCd){
	
	var validateUrl  = '';//校验是否存在附件的url,若返回1,存在,否则不存在;
	var title = '上传附件';
	switch(bizModuleCd){
	case 'bidProject':
		title = '上传工程资料(附件)';
		validateUrl = _ctx+"/bid/bid-project!validateAttach.action";
		break;
	case 'bidSup':
		title = '上传投标单位资料(附件)';
		validateUrl = _ctx+"/bid/bid-sup!validateAttach.action";
		break;
	case 'bidSupGurantee':
		title = '上传保证金(附件)';
		validateUrl = _ctx+"/bid/bid-sup!validateAttach.action";
		break;
	case 'bidSupTech':
		title = '技术标(附件)';
		validateUrl = _ctx+"/bid/bid-sup!validateAttach.action";
		break;
	default:
		alert('类型不对!');
		return;
	}
	//附件请求地址
	var winUrl="/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd="+bizModuleCd+"&filterType=image|office"
	if('bidSupTech'==bizModuleCd){
		winUrl=winUrl+'&onlyShow=true';
	}
	ymPrompt.win({
		message: _ctx+winUrl,
		width:500,
		height:300,
		title: title,
		afterShow:function(){},
		iframe:true,
		handler:function(e){
			if(validateUrl){
				//eg:判断供应商是否有上传附件，若有则将附件按钮值为1
				//var url = _ctx+"/bid/bid-sup!attachValidate.action";
				$.post(validateUrl, {id: entityId, bizModuleCd: bizModuleCd}, function(result) {
					if("1" == result){
						jdom.removeClass('noAttachFile').addClass('hasAttachFile');
						jdom.attr('title','已上传附件');
					}else if("0" == result){
						jdom.removeClass('hasAttachFile').addClass('noAttachFile');
						jdom.attr('title','请上传附件');
					}
				});
			}
		}
	});
}