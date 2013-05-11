/**
 * 成本投标台账模块-明细页面

 */
///****************投标台账明细页面************/
//切换咨询公司
function loadsupType(dom){
	var jdom=$(dom);
	var url = _ctx + '/bid/bid-sup!loadSupType.action';
	var bidLedgerId=jdom.attr('bidLedgerId');
	var cmd=jdom.attr('checked');
	var data={bidLedgerId:bidLedgerId,cmd:cmd};
	$.post(url, data, function(result){
		TB_removeMaskLayer();
		alert('切换成功！');
		loadBidLedgerDetail(bidLedgerId);
	});
}
//加载工程列表
function loadProjectList(bidLedgerId){
	TB_showMaskLayer("正在载入...");
	var url=_ctx+"/bid/bid-project!list.action";
	$.post(url, {bidLedgerId : bidLedgerId}, function(result) {
		TB_removeMaskLayer();
		$('#projectListPanel').html(result);
	});	
}
//加载投标单位信息
function loadBidSup(bidLedgerId){
	TB_showMaskLayer("正在载入...");
	var url=_ctx+"/bid/bid-sup!list.action";
	$.post(url, {bidLedgerId : bidLedgerId}, function(result) {
		TB_removeMaskLayer();
		$('#supListPanel').html(result);		
	});	
}
//加载状态
function loadPrevNextStatus(bidLedgerId) {
	TB_showMaskLayer("正在操作...");
	var url = _ctx + "/bid/bid-ledger!loadPrevNextStatus.action";
	$.post(url, {id : bidLedgerId}, function(result) {
		TB_removeMaskLayer();
		$('#prevNextPanel').html(result);
	});
}
// 进入下一个环节
function gotoNextStatus(jdom, bidLedgerId,allSupHasReceived,hasUploadBidFile) {
	var message;
	//获取供应商数量
	var supCnts=$("#hidden_supCnts").val();
	//获取工程数量
	var projectCnts=$("#hidden_projectCnts").val();
	//获取标段状态
	var bidStatusCd = jdom.attr("bidStatusCd");	
	//获取投标文件上传标记
	var hasUploadBidFile=jdom.attr("bidContAttachId");	
	//导入状态,进入邀标阶段
	if('0' == bidStatusCd){
			//如果未上传招标文件，则不能进入要标阶段
			//if(hasUploadBidFile!="1"){
			//alert("未上传招标文件，不能进入邀标!");
			//return ;
		/* contentd by huangbijin 
		//判断是否还存在附件
		var divContent = $("div.attaContainer").length;
		if(divContent<=0){
				alert("未上传招标文件，不能进入邀标!");
				return ;				
		}	*/
		//判断工程数和供应商数
		if(projectCnts<1){
			ymAlert('标段工程数为空,不能继续！');
			return ;
		}
		if(supCnts<2){
			ymAlert('投标供应商为空,不能继续！');
			return ;
		}
	}
	
	
	
	if ('0' == bidStatusCd) {
		//需判断标段总面积是否已填写
		
		/*
		var sectionTotalArea=$("#hidden_sectionTotalArea").val();
		if(typeof(sectionTotalArea) == "undefined"||""==sectionTotalArea||sectionTotalArea==null){
			alert("标段总面积为空,必须填写标段总面积方可继续！");
			return ;
		}else{
			if(sectionTotalArea<=0){
				alert("标段总面积必须大于0！");
				return ;
			}
		}			
		*/
		myGotoNext(jdom, bidLedgerId);
		/*
		var url = _ctx + '/bid/bid-sup!validateHasNoAcctSup.action';
		var data = {bidLedgerId : bidLedgerId};
		$.post(url, data, function(result){
			//若无未开通账号,直接进入下一个环节
			var rs=result.split(";");
			if('none' == rs[1]){
				myGotoNext(jdom, bidLedgerId);
			}else{
				var tip =  rs[2];
				if (window.confirm(tip)) {
					myGotoNext(jdom, bidLedgerId);
				}
			}
		});
		*/
	}
	//如果是"邀标"进入"投标"状态，需校验是否有标底数据
	else if('1'==bidStatusCd){
		
		if(allSupHasReceived=='1'){
			message = '有供应商未应标（如果进入投标，则未应标的供应商将不能看到应标操作！）,是否继续进入投标？';
		
			ymConfirm(message,function(){
				var url1 = _ctx + '/bid/bid-sup!validateHasNotCommitMargin.action';
				var data1 = {bidLedgerId : bidLedgerId};		
				$.post(url1, data1, function(result){
					var rs =result.split(',');
					if(rs[0]=='success'){
						//因为所有数据都是在投标阶段导入，故注销
						//hasSubmmitedMarginNextStep(jdom, bidLedgerId);
						myGotoNext(jdom, bidLedgerId);
					}else{
						if (window.confirm('有'+rs[1]+'家供应商未提交保证金,是否进入投标?')){
							myGotoNext(jdom, bidLedgerId,'1');
						//hasSubmmitedMarginNextStep(jdom, bidLedgerId);
						//if(rs[1]>0){
						//	alert('有'+rs[1]+'家供应商未提交保证金,不能进入投标!');
						//	return;
						//}else{
						//	return;
						}
					}
				});
			});
		}else{
			var url1 = _ctx + '/bid/bid-sup!validateHasNotCommitMargin.action';
			var data1 = {bidLedgerId : bidLedgerId};		
			$.post(url1, data1, function(result){
				var rs =result.split(',');
				if(rs[0]=='success'){
					//因为所有数据都是在投标阶段导入，故注销
					//hasSubmmitedMarginNextStep(jdom, bidLedgerId);
					myGotoNext(jdom, bidLedgerId);
				}else{
					if (window.confirm('有'+rs[1]+'家供应商未提交保证金,是否进入投标?')){
						myGotoNext(jdom, bidLedgerId,'1');
					}
				}
			});
		
		}
		
		
	}
	else{
		myGotoNext(jdom, bidLedgerId);
	}
}
//校验是否提交保证金之后的步骤
function hasSubmmitedMarginNextStep(jdom,bidLedgerId){
	var url = _ctx + '/bid/bid-ledger!validateHasImportBiaodiData.action';
	var data = {bidLedgerId : bidLedgerId};			

	TB_showMaskLayer("校验是否提交保证金...");
	$.post(url, data, function(result){
		TB_removeMaskLayer();
		//已经有标底数据
		if('yes'==result){
			myGotoNext(jdom, bidLedgerId);
		}
		//没有标底数据
		else{
			ymAlert('没有标底数据 ,暂不能进入投标!');
		}
	});
	
}
//进入下一个环节
function myGotoNext(jdom, bidLedgerId,flagConfirm){
	
	 if('1'==flagConfirm){
		 TB_showMaskLayer("进入下一个环节...");
			var url = _ctx + "/bid/bid-ledger!gotoNext.action";
			$.post(url, {bidLedgerId : bidLedgerId}, function(result) {
				TB_removeMaskLayer();
				var rs=result.split(",");
				if ('success' == rs[0]) {
					//如果是明标,且邀标动作完成
					//不需要弹出选择联系人的弹出框
					//if('inviteBid'==rs[1]&&'1'==rs[2]&&'1'==rs[3]){
					//	openConfirmMessage(rs[4]);
					//}else{
					//	loadBidLedgerDetail(bidLedgerId);
					//}
					loadBidLedgerDetail(bidLedgerId);				
				} else {
					ymalert('操作不成功!'+rs[1]);
				}
			});
	 }else{
		 ymConfirm('确认' + jdom.val() + '?',function(){
				TB_showMaskLayer("进入下一个环节...");
				var url = _ctx + "/bid/bid-ledger!gotoNext.action";
				$.post(url, {bidLedgerId : bidLedgerId}, function(result) {
					TB_removeMaskLayer();
					var rs=result.split(",");
					if ('success' == rs[0]) {
						//如果是明标,且邀标动作完成
						//不需要弹出选择联系人的弹出框
						//if('inviteBid'==rs[1]&&'1'==rs[2]&&'1'==rs[3]){
						//	openConfirmMessage(rs[4]);
						//}else{
						//	loadBidLedgerDetail(bidLedgerId);
						//}
						loadBidLedgerDetail(bidLedgerId);				
					} else {
						ymalert('操作不成功!'+rs[1]);
					}
				});
			});
	 }
	
	
}
//如果明标有多个联系人
function openConfirmMessage(bidLedgerId){
	var url=_ctx + "/bid/bid-sup!supHasMultiContactors.action";
	var data={bidLedgerId:bidLedgerId};
	$.post(url, data, function(result) {
		$("#delayWindowDiv").html(result);
		$("#delayWindowDiv").show();
	});
}
// 回退下一个环节
function gotoPrevStatus(jdom,bidLedgerId) {
	if (!window.confirm('确认'+jdom.html()+'?')) {//回退下一个环节
		return;
	}
	var url = _ctx + "/bid/bid-ledger!gotoPrev.action";
	$.post(url, {bidLedgerId : bidLedgerId}, function(result) {
		var rs=result.split(',');
		if ('success' == rs[0]) {
			loadPrevNextStatus(bidLedgerId);
		} else {
			alert('操作不成功!');
		}
	});
}
//保存投标信息
function doSaveLedger(jDom){
	//判断实际定标日期必须大于等于实际开标日期 add by dido start
	//var bidConfirmRealDate = $("#bidConfirmRealDate").val();//实际定标日期
	//var bidOpenRealDate = $("#bidOpenRealDate").val();//实际开标日期
	//var startDate = $("#startDate").val();//施工工期开始日期
	//var endDate = $("#endDate").val();//施工工期结束日期
	/*
	if(StringToDate(bidOpenRealDate)>StringToDate(bidConfirmRealDate)){
		alert("实际定标日期必须大于等于实际开标日期");
		return;
	}
	if(StringToDate(startDate)>StringToDate(endDate)){
		alert("施工工期结束日期必须大于施工工期开始日期");
		return;
	}
	*/
	var bizArea = $("#bizArea").val();//商业面积
	var houseArea = $("#houseArea").val();//住宅面积
	var totalAreaValue =  $("#input_sectionTotalArea").val();//标段总面积判断大于0

	var iBizArea = 0;
	var iHouseArea = 0;
	var iTotalArea = 0;
	
	//转成数值
	if($.trim(houseArea) == ''){
		iBizArea = 0;
	}else{
		iBizArea = parseFloat(bizArea);
	}
	
	if($.trim(houseArea) == ''){
		iHouseArea = 0;
	}else{
		iHouseArea = parseFloat(houseArea);
	}
	
	if($.trim(totalAreaValue) == ''){
		iTotalArea = 0;
	}else{
		iTotalArea = parseFloat(totalAreaValue);
	}
	
	if(iTotalArea <= 0){
		ymAlert("标段总面积必须大于0!");
		//$("#input_sectionTotalArea").attr("value",'');//清空内容 
		return;
	}
	
	if(iBizArea > iTotalArea){
		ymAlert(" 商业面积 不能大于 标段总面积,请核对!");
		//$("#bizArea").attr("value",'');//清空内容 
		return;
	}
	
	if(iHouseArea > iTotalArea){
		ymAlert(" 住宅面积 不能大于 标段总面积 ,请核对!");
		//$("#houseArea").attr("value",'');//清空内容 
		return;
	}
	if((iBizArea + iHouseArea) > iTotalArea){
		ymAlert(" 住宅面积 与 商业面积 总和不能大于 标段总面积,请核对!");
		//$("#houseArea").attr("value",'');//清空内容 
		return;
	}
	//add by dido end
	TB_showMaskLayer("正在保存...");
	
	$('#submitBidLedgerForm').ajaxSubmit(function(result){
		TB_removeMaskLayer();
		if(result.indexOf('success') > -1){
			//面积赋值		
			$("#hidden_sectionTotalArea").val($("#input_sectionTotalArea").val());
			$("#saveok").html("<font color='red'>保存成功</font>").show().fadeOut(3000);
		}else{
			ymAlert('保存不成功！');
			return false;
			//alert(result);
		}
	});
}
//bid-project-list.jsp
//保存新建工程信息
function saveNewBidProject(bidLedgerId){	
	TB_showMaskLayer("正在保存...");
	$('#newProjectForm').ajaxSubmit(function(result){				 
		TB_removeMaskLayer();
		var rs=result.split(';');
		if("success" == rs[0]){
			showSuccessInfoCheck('succInfoId','保存成功！');
			loadProjectList(bidLedgerId);
		}else{
			alert(rs[1]);
		}
	});
}
//提示完成
function showSuccessInfoCheck(id,text){
	$('#'+id).text(text).show().fadeOut(1000);
}
//删除工程
function doDeleteProject(bidProjectId,projectName,dom,bidLedgerId){
	//验证工程是否有有效数据
	var validateUrl=_ctx+"/bid/bid-project!projectHasValidateData.action";	
	$.post(validateUrl,{projectId: bidProjectId},function(result){
		var rs=result.split(',');
		if('success'==rs[0]){
			if(rs[1]==1){
				ymAlert("此工程已经有有效数据,不能删除.");
			}else{
				//没有有效数据,执行删除操作
				deleteProject(bidProjectId,projectName,dom,bidLedgerId);
			}
		}else{
			alert(rs[1]);
			return;
		}
	});
	
}

function deleteProject(bidProjectId,projectName,dom,bidLedgerId){
	if(!window.confirm('确认删除工程['+ projectName +']?')){
		return;
	}
	var url = _ctx+"/bid/bid-project!delete.action";	
	TB_removeMaskLayer('正在删除...');
	$.post(url,{id: bidProjectId},function(result){
		TB_removeMaskLayer();
		if('success' == result){
			var numProjectCount = new Number($("span[numProjectCount]").html());
			$("span[numProjectCount]").html(numProjectCount -1 );
			showSuccessInfoCheck('succInfoId','删除成功！');
			
			//$(dom).parent().parent().remove();
			loadProjectList(bidLedgerId);
		}else{
			showSuccessInfoCheck('succInfoId','删除失败！');		 
			alert(result);
		}
	});
}
//提示完成
function showSuccessInfo(id,text){
	$('#'+id).text(text).show().fadeOut(1000);
}
//更新工程信息
function updateBidProject(bidProjectId,fieldName,dom){		
	var newVal = $.trim($(dom).val());
	var oldVal = $.trim($(dom).attr('oldVal'));
	//if( newVal == oldVal){
	//	$(dom).hide();
	//	$(dom).prev().show();
	//	return;
	//}
	var url = _ctx + "/bid/bid-project!saveProperty.action";	
	var data={id: bidProjectId, fieldName: fieldName, newVal: newVal, oldVal: oldVal};
	TB_showMaskLayer("正在保存...");
	$.post(url, data, function(result){
		TB_removeMaskLayer();
		var rs=result.split(";");
		if('success' == rs[0]){
			showSuccessInfoCheck('succInfoId','更新成功！');
			if( newVal == ''){
				$(dom).prev().html('&nbsp;').show();
				$(dom).attr('oldVal','').hide();
			}else{
				$(dom).prev().html(newVal).show();
				$(dom).attr('oldVal',newVal).hide();
			}
		}else{
			showSuccessInfoCheck('succInfoId','更新失败！'+rs[1]);
			loadProjectList(rs[2]);
		}
	});
}


//开通账号
function openPlacct(bidSupId,bidLedgerId){
	if(!window.confirm('确认开通供应商的官网账号?')){
		return;
	}
	var url = _ctx + "/bid/bid-sup!openPlacct.action";
	$.post(url, {bidSupId: bidSupId}, function(result) {
		var rs=result.split(",");
		if ('success' == rs[0]) {
			loadBidSup(bidLedgerId);
		} else {
			alert(result);
		}
	});	
}

//提交保证金(不使用)
function editMarginCommit(supId, bidLedgerId) {
	var url = _ctx + "/bid/bid-sup!save.action";
	$.post(url, {id : supId, attachFlg : 1}, function(result) {
		if ('success' == result) {
			loadBidSup(bidLedgerId);
		} else {
			alert(result);
		}
	});	
}
//中标修改
function editBidFlag(supId,bidLedgerId){
	var url=_ctx+"/bid/bid-sup!save.action";
	var data={id:supId,isBidFlg:1};
	$.post(url,data,function(result){		
		//加载投标单位
		loadBidSup(bidLedgerId);
	});	
}

//查看商务标
function viewBizItem(bidLedgerId){
	var url = _ctx + '/bid/bid-ledger-excel!bisexcel.action?bidLedgerId='+bidLedgerId;
	openWindow('bidLedgerExcel','商务标',url);
}
//查看技术标
function viewTechItemList(bidLedgerId){
	var url = _ctx+"/bid/bid-tech-item!list.action?bidLedgerId="+bidLedgerId;
	openWindow('bidLedgerTech','技术标',url);
}

function updateBidLedger(jDom){		
	
	//获取节点值
	$('#changeFieldName').val(jDom.attr("name"));		
	$('#changeFieldValue').val(jDom.val());	
	if('sectionTotalArea'==jDom.attr("name")&&jDom.val()==jDom.attr('title')){
			return ;
	}
	if('bizArea'==jDom.attr("name")&&jDom.val()==jDom.attr('title')){
			return ;
	}
	if('houseArea'==jDom.attr("name")&&jDom.val()==jDom.attr('title')){
			return ;
	}
	
	$("#submitBidLedgerForm").ajaxSubmit(function(result) {
		if('success' == result){
			$('#changeFieldName').val('');
			$('#changeFieldValue').val('');
			showSuccessInfoCheck('bidLedgerSuccInfoId','['+jDom.parent().prev().html()+']已更新!');
		}else{
			showSuccessInfoCheck('bidLedgerSuccInfoId','更新失败!');
		}
	});
	
	/*
	var url =_ctx + "/bid/bid-ledger!save.action";	
	var data = {id: bidLedgerId, operDesc: fieldValue};
	editPropertyValue(url,data,'bidLedgerSuccInfoId','');
	*/
}

//更新ledger部分属性值
function editPropertyValue(url, data, resultTipId, successId){
	$.post(url, data, function(result){
		if("success" == result)
			showSuccessInfoCheck(resultTipId,'更新成功!');
		else
			showSuccessInfoCheck(resultTipId,'更新失败!');
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
function openAttachmentRefresh(jdom,entityId,bizModuleCd,enableRefreshFlg){
	
	var validateUrl  = '';//校验是否存在附件的url,若返回1,存在,否则不存在;
	//弹出窗口的URL
	var popWinUrl=_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd="+bizModuleCd+"&filterType=image|office";
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
		validateUrl = _ctx+"/bid/bid-sup!validateAttach.action";
		popWinUrl=popWinUrl+'&onlyShow=true';
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
		}
	});
}
//打开网批
function openResTask(id, resAuthTypeCd){
	var url = _ctx + '/res/res-approve-info.action?id=' + id + '&resAuthTypeCd=' + resAuthTypeCd;
	openWindow('resApprove', '网上审批', url);
}
//设置中标
function setBidSucc(bidSupId,bidLedgerId){

	ymConfirm("确认推荐中标?",function(){
		var url = _ctx + '/bid/bid-sup!setBidSucc.action';
		$.post(url, {bidSupId: bidSupId}, function(result){
			if('success' == result){
				loadBidLedgerDetail(bidLedgerId);
			}else{
				alert(result);
			}
		});
	});
	
}
//取消中标
function cancelBidSucc(bidSupId,bidLedgerId){
	ymConfirm("确认取消推荐中标??",function(){
		var url = _ctx + '/bid/bid-sup!cancelBidSucc.action';
		$.post(url, {bidSupId: bidSupId}, function(result){
			if('success' == result){
				loadBidLedgerDetail(bidLedgerId);
			}else{
				alert(result);
			}
		});
	});
}

//(下一轮)轮次
function addBatchNo(batchNo,bidLedgerId){
	if( batchNo == ''|| batchNo == '0'){
		batchNo = 1;
	}else{
		batchNo += 1;//累加
	}
	if(!window.confirm('进入第 ' + batchNo + ' 轮投标?')){
		return;
	}
	var url = _ctx + '/bid/bid-ledger!addBatchNo.action';
	$.post(url, {bidLedgerId: bidLedgerId}, function(result){
		if('success' == result){
			loadPrevNextStatus(bidLedgerId);
		}else{
			alert(result);
		}
	});
}
//(上一轮)回退轮次
function backBatchNo(batchNo,bidLedgerId){
	if( batchNo == ''|| batchNo == 1){
		batchNo = 0;
	}else{
		batchNo -= 1;//累减
	}
	if(!window.confirm('退回到第 ' + batchNo + ' 轮投标?')){
		return;
	}
	var url = _ctx + '/bid/bid-ledger!backBatchNo.action';
	$.post(url, {bidLedgerId: bidLedgerId}, function(result){
		if('success' == result){
			loadPrevNextStatus(bidLedgerId);
		}else{
			alert(result);
		}
	});
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
		message = '评标文件未上传,确定进入第 ' + batchNo + ' 轮投标?';
	}else{
		message = '进入第 ' + batchNo + ' 轮投标?';
	}
	ymConfirm(message,function(){
		var url = _ctx + '/bid/bid-ledger!gotoNextBatchNo.action';
		$.post(url, {bidLedgerId: bidLedgerId}, function(result){
			if('success' == result){
				//如果为下一轮投标,隐藏回标分析
				$("#btnShowPop").hide();	
				loadPrevNextStatus(bidLedgerId);
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
			//如果为下一轮投标,隐藏回标分析
			$("#btnShowPop").hide();	
			loadPrevNextStatus(bidLedgerId);
			loadBidSup(bidLedgerId);
		}else{
			alert(result);
		}
	});
	}
}


function loadBidLedgerDetail(bidLedgerId){

	TB_showMaskLayer("刷新页面...");
	window.location.href = _ctx + '/bid/bid-ledger!detail.action?id=' + bidLedgerId;
}
//退款
function supRefund(jdom, bidSupId,bidLedgerId){
	if(!window.confirm('确认退款?')){
		return;
	}
	var url = _ctx + '/bid/bid-sup!supRefund.action';
	$.post(url, {bidSupId: bidSupId}, function(result){
		if('success' == result){
			//jdom.hide();
			//jdome.next().show();
			loadBidSup(bidLedgerId);
		}else{
			alert(result);
		}
	});
}

function  isEmpty(str) {
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
}
//计算时间
function onchange_DesignTotalDay(id) {
	verificationTime(id);//add by dido
	var fromDate = $("#startDate").val();
	var toDate = $("#endDate").val();
	if(isEmpty(fromDate) || isEmpty(toDate)) {
		$("#rankDateNum").attr("value",'');//清空内容 
		return;
	}
	var fArray = fromDate.split("-");
	var tArray = toDate.split("-");
	var fDate = new Date(fArray[0],fArray[1]-1,fArray[2]);
	var tDate = new Date(tArray[0],tArray[1]-1,tArray[2]);
	if(tDate.getTime()<fDate.getTime()) {
		//alert("结束时间不能小于开始时间");
		$("#rankDateNum").val("");
		return;
	}
	var day = Math.abs(tDate.getTime()-fDate.getTime())/1000/60/60/24+1;
	$("#rankDateNum").val(day);
}

function cancelConfirm(bidLedgerId){
	$("#delayWindowDiv").html('');
	$("#delayWindowDiv").hide();
	loadBidLedgerDetail(bidLedgerId);
}

function sendMessage(bidLedgerId){
	var relSupContactIds="";
	$("div.supList").children().filter(':checked').each(
			function(){	
				relSupContactIds=relSupContactIds+","+$(this).attr('contactId');
			}
	);	
	if(""!=relSupContactIds&&relSupContactIds.length>1){
		relSupContactIds=relSupContactIds.substring(1,relSupContactIds.length);
		//执行ajax发送信息
		sendMailAndMessage(bidLedgerId,relSupContactIds);
	}else{
		loadBidLedgerDetail(bidLedgerId);
	}	
	
}

function sendMailAndMessage(bidLedgerId,relSupContactIds){
	var url = _ctx + '/bid/bid-ledger!sendMailAndMessage.action';
	var data={bidLedgerId:bidLedgerId,relSupContactIds:relSupContactIds};
	TB_showMaskLayer("正在发送...");
	$.post(url, data, function(result){
		TB_removeMaskLayer();
		alert('帐号密码发送成功！');
		loadBidLedgerDetail(bidLedgerId);
	});
}


//关闭项目(即 废弃战略投标)
function closeBid(bidLedgerId){
	if(window.confirm('确认关闭投标，将终止投标，继续？')){
		var url = _ctx + "/bid/bid-ledger!closeBid.action";
		var data = {id: bidLedgerId};
		//提交数据
		TB_showMaskLayer("正在关闭投标...");
		$.post(url,data,function(result){
			TB_removeMaskLayer();
			if('success' == result){
				window.location.href = _ctx + '/bid/strategy-bid-ledger!detail.action?id=' + bidLedgerId;
				alert('项目已关闭');
			}else{
				alert(result);
			}
		});
	}
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
			loadBidSup(rs[1]);
			//查看是否存在已经提交保证金，但未确认保证金的情况
			unCheckedGurantee(rs[1]);
		}else{
			ymAlert(result[1]);
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
//打开投标文件列表新页面
function supStrategyAttach(bidLedgerId){
	openWindow('strategyDetailAttach','轮次明细',_ctx + '/bid/bid-sup!attachList.action?bidLedgerId=' + bidLedgerId);
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