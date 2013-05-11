//跳转至给定的页面,配合前台的分页搜索
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}

//跳转页面
function jumpPage(pageNo) {		
	if(typeof(pageNo) == 'undefined' || (pageNo == '')){
		$("#pageNo").val(1);
	}else{
		$("#pageNo").val(pageNo);
	}
	//状态
	var bidStatusCd = '';
	$('#quickClickPanel span[class*=quickBidItem-click]').each(function(i){
		bidStatusCd += $(this).attr("typeCd") + ",";
	});
	$("input[name=bidStatusCd]").val(bidStatusCd);
	TB_showMaskLayer("正在搜索...");
	$("#mainFormSearch").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		$(".searchResultPanel").html(result);
	});
}

//通过表单搜索条件搜索列表
function searchBidLedger(){
	jumpPage(1);
}

//显示标段明细
function showLedgerDetail(bidLedgerId){
	openWindow('strategyDetail','战略投标明细',_ctx + '/bid/strategy-bid-ledger!detail.action?id=' + bidLedgerId);
}

//打开窗口
function openWindow(id,desc,url){
	if(window.parent && window.parent.parent.TabUtils){
		window.parent.parent.TabUtils.newTab(id,desc,url);
	}else{
		window.open(url);
	}
}

//全屏
function fullScreen(url){
	window.open(url);
}

//高级搜索表单显示
function seniorQuery(dom){
	$("#newBidLedger").hide();			
	//$(dom).addClass('quickSeniorExt').removeClass('quickSenior');
	$('#seniorPanel').show();
}

//高级搜索表单隐藏
function hideSeniorQuery(){
	$('#seniorPanel').hide();
	$("#quickBidSenior").addClass('quickSenior').removeClass('quickSeniorExt');
}



//新增战略投标表单显示
function addNewStrategy(){
	$('#seniorPanel').hide();
	$("#newBidLedger").show();
}

//新增战略投标表单隐藏
function resetTempForm(){
	$("#projectName").attr("value",'');//清空内容 
	$("#biaoShuNo").attr("value",'');
	$("#biaoDuan").attr("value",''); 
	$(":checkbox").attr("checked","");
	$("#planMoney").attr("value",'');
	$("#newBidLedger").hide();
}

//重置表单
function resetSeniorPanel(){
	$("#bidSectionName").val('');
	$("#resCd").val('');
	$("#lastGuarDate1").val('');
	$("#lastGuarDate2").val('');
	
	$("#lastReceDate1").val('');
	$("#lastReceDate2").val('');
	
	$("#createdDate1").val('');
	$("#createdDate2").val('');
	
}

//保存战略投标
function saveBidLedger(){
	
//	if($("#biaoShuNo").val()=="" || $("#biaoShuNo").val()==0 ){ 
//        alert("标书编号不能为空");
//        $("#biaoShuNo").focus();
//        return;
//     } 
    var pdv = new Validate("templetForm");
	if(pdv.validate()){
		var data={};
		data['entity.bidSectionName']=$("#inp_bidSectionName").val();
		data['entity.remark']=$("#inp_remark").val();
		data['showFlag']=$("#showFlag").attr('checked');
		data['hideFlag']=$("#hideFlag").attr('checked');
		if($("#hideFlag").attr('checked')==false && $("#showFlag").attr('checked')==false){
			alert("请选择邀标类型！");
			return;
		}
		var errorNum = 0;
		$("table#addNewbidLedger input[type='text']").each(function(){
			if($.trim($(this).val())=='' && $(this).attr("class")=='required'){
				errorNum++;
			}
		});
		if(errorNum>0){
			alert("请完整填写信息！");
			return;
		}
		data['projectName']=$("#projectName").val();
		data['projectCd']=$("#projectCd").val();
		data['entity.periodNum']=$("#inp_periodNum").val();
		data['entity.construction']=$("#inp_construction").val();
		var url=_ctx+"/bid/strategy-bid-ledger!save.action";
		$.post(url,data,function(result){
		//$('#templetForm').ajaxSubmit(function(result){
		
			var rs=result.split(",");			
			if(rs[0]=='success'){
				//清空表单
				clearBidLedgerForm();
				$("#newBnt").trigger('click');
				jumpPage();
			}else{
				alert(rs[1]);
			}
		});	
		}
}

//清空新增表单
function clearBidLedgerForm(){
	$("#inp_bidSectionName").val('');
	$("#showFlag").val('');
	$("#hideFlag").val('');
	$("#inp_remark").val('');
	$("#inp_bidSectionName").css('border','1px solid gray');
}

//更新战略投标
function uopdateBidLedger(dom){
	
		var data={};
		var url=_ctx+"/bid/strategy-bid-ledger!save.action"
		//保证金截止日期
		var lastGuarDate=$("#lastGuarDate").val();
		//应标截止日期
		var lastReceDate=$("#lastReceDate").val();
		if($.trim(lastGuarDate)==""){
			ymAlert("保证金截止日期为必填");
			return;
		}
		if($.trim(lastReceDate)==""){
			ymAlert("应标截止日期为必填");
			return;
		}
		
		//有保证金
		var needGuarFlg=$("input[id='needGuarFlg']").attr('checked');
		//项目介绍
		var projIntrDesc=$("textarea[id='projIntrDesc']").val();
		//备注
		//var remark=$("textarea[id='remark']").val();
		
		data['id']=$("#bidLedgerId").val();
		data['entity.lastGuarDate']=lastGuarDate;
		data['entity.lastReceDate']=lastReceDate;
		data['needGuarFlg']=needGuarFlg;
		data['entity.projIntrDesc']=projIntrDesc;
		//data['entity.remark']=remark;
		
		//提交数据
		TB_showMaskLayer("正在保存...");
		$.post(url,data,function(result){
			TB_removeMaskLayer();
			$('#bid_detail_tip').html('<font color="red">保存成功!</font>').show().fadeOut(3000);
		});
	
}

//废弃战略投标
function deleteBidLedger(dom){
	ymConfirm("确认关闭投标，将终止投标，继续？",function(){

		var data={};
		var url=_ctx+"/bid/strategy-bid-ledger!save.action";
		data['id']=$("#bidLedgerId").val();
		data['entity.bidStatusCd']='9';
		
		//提交数据
		TB_showMaskLayer("正在关闭投标...");
		$.post(url,data,function(result){
			TB_removeMaskLayer();
			//隐藏进入下一环节按钮
			//$("#nextStatusBtn").hide();
			//隐藏关闭按钮
			//$("#closeBidBtn").hide();
			//隐藏保存按钮
			//$("#saveBidBtn").hide();
			//状态改为关闭
			//$("#bidStatusDesc").html('关闭');
			showLedgerDetail($("#bidLedgerId").val());
			
		});
	
	});

}

//加载战略投标供应商
function loadStrategyBidSup(bidLedgerId){
	TB_showMaskLayer("正在载入...");
	var url=_ctx+"/bid/bid-sup!strategyList.action";
	$.post(url, {bidLedgerId : bidLedgerId}, function(result) {
		TB_removeMaskLayer();
		$('#supListPanel').html(result);		
	});	
}

//加载轮次明细列表
function loadStrategyAttachList(bidLedgerId){
	TB_showMaskLayer("正在载入...");
	var url=_ctx+"/bid/bid-sup!supAttachList.action";
	$.post(url, {bidLedgerId : bidLedgerId}, function(result) {
		TB_removeMaskLayer();
		$('#supListPanel').html(result);
		/*
		var width= document.body.clientWidth;
		
		//$('.fakeContainer').css({'width': width-9});
		//更换插件：jquery.fixedheadertable插件，author:wangming  modifyDate:2012.5.21 
		$('#MyTable1').fixedHeaderTable({ footer: true, altClass: 'odd',fixedColumns: 2 });
		//var myST = new superTable("MyTable",{fixedCols : 1,headerRows:2, sParentHeight:100,cssSkin : "sOrange",onFinish : function () {}});
		*/
	});	
}

//打开投标列表新页面
function supStrategyAttach(bidLedgerId){
	openWindow('strategyDetailAttach','轮次明细',_ctx + '/bid/bid-sup!attachList.action?bidLedgerId=' + bidLedgerId);
}

//进入下一个环节
function gotoNextStatus(jdom, bidLedgerId,allSupHasReceived,hasUploadBidFile) {
	//获取供应商数量
	var supCnts=$("#hidden_supCnts").val();
	//获取标段状态
	var bidStatusCd = jdom.attr("bidStatusCd");	
	//保证金截止日期
	var lastGuarDate=$("#lastGuarDate").val();
	//应标截止日期
	var lastReceDate=$("#lastReceDate").val();
	//alert("导入状态: "+bidStatusCd);
	//导入状态,进入邀标阶段
	if('0' == bidStatusCd){
		if($.trim(lastGuarDate)==""){
			ymAlert("保证金截止日期为必填");
			return;
		}
		if($.trim(lastReceDate)==""){
			ymAlert("应标截止日期为必填");
			return;
		}
		if(supCnts<1){
			ymAlert('投标供应商为空,不能继续！');
			return ;
		}		
	}
	//“导入”状态到“邀标”状态
	if ('0' == bidStatusCd) {
		//验证是否已经上传必传附件
		var attUrl=_ctx+"/bid/strategy-bid-ledger!hasUploadAllAttach.action";
		var attData={bidLedgerId:bidLedgerId};		
		$.post(attUrl, attData, function(result){			
			if('success'==result){
				myGotoNext(jdom, bidLedgerId);
			}else{
				ymAlert(result);
				return;
			}
		});
	}
	//如果是"邀标"进入"投标"状态，需校验是否有标底数据
	else if('1'==bidStatusCd){		
		if(allSupHasReceived=='1'){
			ymConfirm("有供应商未应标,是否继续进入投标？（如果进入投标，则未应标的供应商将不能应标，也无法投标！）",function(){
				var url1 = _ctx + '/bid/bid-sup!strategyHasNotCommitMargin.action';
				var data1 = {bidLedgerId : bidLedgerId};		
				$.post(url1, data1, function(result){
					var rs =result.split(',');
					if(rs[0]=='success'){
						myGotoNext(jdom, bidLedgerId);
					}else{
						ymConfirm('有'+rs[1]+'家供应商未提交保证金,是否进入投标?',function(){
							myGotoNext(jdom, bidLedgerId,'1');
						});
						
					}
				});
			});
		}else{
			var url1 = _ctx + '/bid/bid-sup!strategyHasNotCommitMargin.action';
			var data1 = {bidLedgerId : bidLedgerId};		
			$.post(url1, data1, function(result){
				var rs =result.split(',');
				if(rs[0]=='success'){
					myGotoNext(jdom, bidLedgerId);
				}else{
					ymConfirm('有'+rs[1]+'家供应商未提交保证金,是否进入投标?',function(){
						myGotoNext(jdom, bidLedgerId,'1');
					});
					
				}
			});
		}

		
	}
	//如果是"投标"进入"评标"状态
	else if('2'==bidStatusCd){
		var valAttacheurl=_ctx+"/bid/bid-sup!unUploadAttach.action";
		$.post(valAttacheurl, {bidLedgerId:bidLedgerId}, function(result){
			var rs=result.split(",");
			if("has"==rs[0]){
				ymConfirm("存在未提交投标文件的投标单位，是否继续？",function(){
					myGotoNext(jdom, bidLedgerId,'1');
				});
				
			}else{
				myGotoNext(jdom, bidLedgerId);
			}
		});
	}
	else{
		myGotoNext(jdom, bidLedgerId);
	}
}


//进入下一个环节
function myGotoNext(jdom, bidLedgerId,flagConfirm){
	
    if('1'==flagConfirm){
		var url = _ctx + "/bid/strategy-bid-ledger!gotoNext.action";
		TB_showMaskLayer("正在进入下一环节...");
		$.post(url, {bidLedgerId : bidLedgerId}, function(result) {
			TB_removeMaskLayer();
			var rs=result.split(",");
			if ('success' == rs[0]) {
				showLedgerDetail(bidLedgerId);				
			} else {
				ymAlert('操作不成功!'+rs[1]);
			}
		});
    }else{
		ymConfirm('确认' + jdom.val() + '?',function(){
			var url = _ctx + "/bid/strategy-bid-ledger!gotoNext.action";
			TB_showMaskLayer("正在进入下一环节...");
			$.post(url, {bidLedgerId : bidLedgerId}, function(result) {
				TB_removeMaskLayer();
				var rs=result.split(",");
				if ('success' == rs[0]) {
					showLedgerDetail(bidLedgerId);				
				} else {
					ymAlert('操作不成功!'+rs[1]);
				}
			});
		});
    }
	
}


//在评标状态,允许进入下一轮投标
function gotoNextBatchNo(batchNo,bidLedgerId){
	
		
	if( batchNo == ''|| batchNo == '0'){
		batchNo = 1;
	}else{
		batchNo += 1;//累加
	}
	var message;
	if($("input[name='hasEvalFiles']").val()!='1')
	{
		message = "评标文件未上传,确定进入第 " + batchNo + "轮投标?";
	}else{
		message = '进入第 ' + batchNo + ' 轮投标?';
	}
	ymConfirm(message,function(){
		var url = _ctx + '/bid/strategy-bid-ledger!gotoNextBatchNo.action';
		$.post(url, {bidLedgerId: bidLedgerId}, function(result){
			if('success' == result){
				showLedgerDetail(bidLedgerId);
			}else{
				ymAlert(result);
			}
		});
	});
	
}

//进入中标阶段
function gotoWin(bidLedgerId){
	if(window.confirm('进入中标?')){
	var url = _ctx + '/bid/bid-ledger!gotoWin.action';
	$.post(url, {bidLedgerId: bidLedgerId}, function(result){
		if('success' == result){	
			showLedgerDetail(bidLedgerId);
		}else{
			alert(result);
		}
	});
	}
}

//进入推荐中标阶段
function gotoPop(bidLedgerId){
	if(window.confirm('进入推荐中标?')){
	var url = _ctx + '/bid/bid-ledger!gotoPop.action';
	$.post(url, {bidLedgerId: bidLedgerId}, function(result){
		if('success' == result){	
			showLedgerDetail(bidLedgerId);
		}else{
			alert(result);
		}
	});
	}
}

/**
 * 弹出附件窗口，并关闭窗口时更新附件状态
 * 
 * @param jdom 附件图标jquery对象
 * @param entityId 供应商ID
 * @param bizModuleCd 模块类型 
 * @param enableRefreshFlg 是否允许刷新标志,若不传，不刷新.
 */ 
function openAttachmentRefresh(jdom,entityId,bizModuleCd,enableRefreshFlg,bizFieldName){
	
	var validateUrl  = '';//校验是否存在附件的url,若返回1,存在,否则不存在;
	//弹出窗口的URL
	var popWinUrl=_ctx+"/bid/bid-app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd="+bizModuleCd+"&filterType=image|office";
	popWinUrl=popWinUrl+"&bizFieldName="+bizFieldName
	if('disable'==enableRefreshFlg){
		popWinUrl=popWinUrl+'&onlyShow=true';
	}
	var title = '上传附件';		
	ymPrompt.win({
		message: popWinUrl,
		width:500,
		height:300,
		title: title,
		afterShow:function(){},
		iframe:true,
		handler:function(e){
			supStrategyAttach($("#atta_bidLegerId").val());
		}
	});
}

/**
 * 弹出附件窗口，并关闭窗口时更新附件状态
 * 
 * @param jdom 附件图标jquery对象
 * @param entityId 供应商ID
 * @param bizModuleCd 模块类型 
 * @param enableRefreshFlg 是否允许刷新标志,若不传，不刷新.
 */ 
function openAttachmentGurantee(jdom,entityId,bizModuleCd,enableRefreshFlg){
	
	var validateUrl  = '';//校验是否存在附件的url,若返回1,存在,否则不存在;
	//弹出窗口的URL
	var popWinUrl=_ctx+"/bid/bid-app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd="+bizModuleCd+"&filterType=image|office";
	if('disable'==enableRefreshFlg){
		popWinUrl=popWinUrl+'&onlyShow=true';
	}
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
		validateUrl = _ctx+"/bid/bid-sup!validateAttach.action?updateFlg=true";
		//popWinUrl=popWinUrl+'&onlyShow=true';
		break;
	case 'bidSupTech':
		title = '上传技术标(附件)';
		validateUrl = _ctx+"/bid/bid-sup!validateAttach.action";
		break;
	case 'bidLedger':
		title = '上传标段(附件)';
		validateUrl = _ctx+"/bid/bid-sup!validateAttach.action";
		break;
	default:
		alert('类型不对!');
		return;
	}			
	ymPrompt.win({
		message: popWinUrl,
		width:500,
		height:300,
		title: title,
		afterShow:function(){},
		iframe:true,
		handler:function(e){
			if(enableRefreshFlg){
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
			//重新刷新模块
			loadStrategyBidSup($("#bidLedgerId").val());
		}
	});
}

//确认保证金
function checkGurantee(bidSupId,guarAttaConfFlg){
	var url=_ctx+"/bid/bid-sup!checkGurantee.action";
	var data={};
	data['id']=bidSupId;
	data['guarAttaConfFlg']=guarAttaConfFlg;
	TB_showMaskLayer("正在确认保证金...");		
	$.post(url, data, function(result){
		TB_removeMaskLayer();
		var rs=result.split(',');
		if('success' == rs[0]){	
			loadStrategyBidSup(rs[1]);
			//查看是否存在已经提交保证金，但未确认保证金的情况
			unCheckedGurantee(rs[1]);
		}else{
			alert(result[1]);
		}
	});
	
}


//是否存在已经提交保证金，但还未确认保证金的情况
function unCheckedGurantee(bidLedgerId){
	var url=_ctx+"/bid/bid-sup!unCheckedGuar.action";
	var data={bidLedgerId:bidLedgerId};
	$.post(url, data, function(result){
		var rs=result.split(",");
		if("has"==rs[0]){
			$("#hasGuarNotConfirmedTip").html('存在投标单位的保证金未确认');
		}else{
			$("#hasGuarNotConfirmedTip").html('');
		}
	});
}



//设置中标
function setBidSucc(bidSupId,bidLedgerId){
	ymConfirm("确认推荐中标?",function(){
		var url = _ctx + '/bid/bid-sup!setBidSucc.action';
		$.post(url, {bidSupId: bidSupId}, function(result){
			if('success' == result){
				loadStrategyBidSup(bidLedgerId);
			}else{
				ymAlert(result);
			}
		});
	});
}

$(function(){
	var flag=$("#needGuarFlg").attr("checked");
	if(flag==true){
		$("#needGuarFlgTip").html('需要');
	}
});
//切换有无保证金
function changeNeedGuarFlg(dom){
	var jdom=$(dom);
	if(jdom.attr('checked')){
		$("#needGuarFlgTip").html('需要');
	}else{
		$("#needGuarFlgTip").html('不需要');
	}
}

//防止冒泡
function preventClick(e){
	 if (e && e.stopPropagation) {//非IE  
         e.stopPropagation();  
     }  
     else {//IE  
         window.event.cancelBubble = true;  
     } 
}

//退款
function supRefund(jdom, bidSupId,bidLedgerId){
	ymConfirm("确认退款?",function(){
		var url = _ctx + '/bid/bid-sup!supRefund.action';
		$.post(url, {bidSupId: bidSupId}, function(result){
			if('success' == result){
				loadStrategyBidSup(bidLedgerId);
			}else{
				ymAlert(result);
			}
		});
	});
	
}

//取消中标
function cancelBidSucc(bidSupId,bidLedgerId){
	ymConfirm("确认取消推荐中标?",function(){
		var url = _ctx + '/bid/bid-sup!cancelBidSucc.action';
		$.post(url, {bidSupId: bidSupId}, function(result){
			if('success' == result){
				showLedgerDetail(bidLedgerId);	
			}else{
				ymAlert(result);
			}
		});
	});	
	
}

//打开网批
function openResTask(id, resAuthTypeCd){
	var url = _ctx + '/res/res-approve-info.action?id=' + id + '&resAuthTypeCd=' + resAuthTypeCd;
	openWindow('resApprove', '网上审批', url);
}

//ymPrompt alert
function ymAlert(text){
	ymPrompt.alert({message:"<p style='margin-top:-25px;display:block;line-height: 20px;'>"+text+"</p>",title:"提示",width:350,height:160});
}
//ymPrompt confirm
function ymConfirm(message,func){
	ymPrompt.confirmInfo({
		message:"<p style='margin-top:-25px;display:block;line-height: 20px;'>"+message+"</p>",
		width:350,
		height:160,
		//winPos:[($("body").width()-350)/2,50],
		title:"消息提示！",
		closeBtn:true,
		handler:function (btn) {
		if (btn == 'ok') {
			func();
		}
		ymPrompt.close();
		}
	}); 
}


function showOpenRecord(bidLedgerId){ 
	ymPrompt.win({message: _ctx+ '/bid/bid-ledger!openRecord.action?id='+bidLedgerId,width:880,height:650,title:'开标信息',handler:openCallBack,iframe:true});
	}

//子窗口关闭后执行的函数
//subPageInfo为子窗口返回的信息
function openCallBack(subPageInfo) {
    if (subPageInfo == 'close') return;
    if (subPageInfo == '') return;

    //业务逻辑....
    
    gotoNextStatus($("#btnOpen"),$("#hdden_bidLedgerId").val(),$("#hdden_hasSupNotReceive").val());
}