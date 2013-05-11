
function getContById(){
	var url= _ctx + "/cont/cont-ledger!input.action?id="+$("#contLedgerId").val();
	$.post(url,{}, function(result){
		if(parent.TabUtils==null)
			window.open(url);
		else
		  parent.TabUtils.newTab("cont-ledger-input","合同台账查看",url,true);
	});
	
}

//判断是否为空
function validatePm(){
	//企划案例编号
	var pmMateEntryNo = $("#pmMateEntryNo");
	if(""==pmMateEntryNo.val()){
		alert("企划案例编号为必填");
		pmMateEntryNo.focus();
		return false;
	}
	//活动主题
	var activeTitle = $("#activeTitle");
	if(""==activeTitle.val()){
		alert("活动主题为必填");
		activeTitle.focus();
		return false;
	}
	//活动内容
	var activeContent = $("#activeContent");
	if(""==activeContent.val()){
		alert("活动内容为必填");
		activeContent.focus();
		return false;
	}
	//时间周期
	var activePeriod = $("#activePeriod");
	if(""==activePeriod.val()){
		alert("时间周期为必填");
		activePeriod.focus();
		return false;
	}
	//费用预期
	var expensesBudget = $("#expensesBudget");
	if(""==expensesBudget.val()){
		alert("费用预期为必填");
		expensesBudget.focus();
		return false;
	}
	//道具
	var properties = $("#properties");
	if(""==properties.val()){
		alert("道具为必填");
		properties.focus();
		return false;
	}
	//美陈
	var meiChen = $("#meiChen");
	if(""==meiChen.val()){
		alert("美陈为必填");
		meiChen.focus();
		return false;
	}
	//赠品
	var present = $("#present");
	if(""==present.val()){
		alert("赠品为必填");
		present.focus();
		return false;
	}
	//媒体
	var medium = $("#medium");
	if(""==medium.val()){
		alert("媒体为必填");
		medium.focus();
		return false;
	}
	//操作指引
	var operatorGuide = $("#operatorGuide");
	if(""==operatorGuide.val()){
		alert("操作指导为必填");
		operatorGuide.focus();
		return false;
	}
	//预期效果
	var expectedResults = $("#expectedResults");
	if(""==expectedResults.val()){
		alert("预期效果为必填");
		expectedResults.focus();
		return false;
	}
	//参考图片
	var attaFilesContainer = $("#attaFilesContainer").find(".mainTr");
	if(""==attaFilesContainer.text()){
		alert("请先上传参考图片及方案");
		return false;
	}
	return true;
}
//保存企划案例库
function savePmMateEntry(){
	//企划案例编号
	var pmMateEntryNo = $("#pmMateEntryNo");
	var pmMateEntryId = $("#pmMateEntryId");
	var flag = $("#flag").val();
	//统计所有附件中的文件数
	var objLen = $("#attaFilesContainer").find(".mainTr").length;
	//统计附件预览中的图片数
	var objImgLen = $("#attaFilesContainer").find("a img").length;
	//统计除图片外其他格式文件数
	var objFilesLen = parseInt(objLen)-parseInt(objImgLen);
	//alert("objLen: "+objLen+" ,objImgLen: "+objImgLen+" ,objFilesLen: "+objFilesLen);
	//if(objImgLen>0){
	//	$("#fileSn_pic").val("1");
	//}else{
	//	$("#fileSn_pic").val("0");
	//}
	//if(objFilesLen>0){
	//	$("#fileSn_prj").val("1");
	//}else{
	//	$("#fileSn_prj").val("0");
	//}
	if(objLen>0){
		$("#fileSn_pic").val("1");
	}else{
		$("#fileSn_pic").val("0");
	}
	if(validatePm()==true){
		var url= _ctx + "/pm/pm-mate-entry!noRepeat.action";
		var param={pmMateEntryNo:$.trim(pmMateEntryNo.val())};
		if(flag=="0"){
			var pmEntryNo_U = $.trim($("#pmMateEntryNo").attr("pmEntryNo_U"));
			//alert(pmEntryNo_U+" || "+pmMateEntryNo.val());
			$.post(url,param,function(result){
				//alert((pmEntryNo_U==$.trim(pmMateEntryNo.val()))+" pmEntryId : "+$("#pmEntryId").val()+" pmMateEntryId: "+$("#pmMateEntryId").val()+" pmMateModuId: "+$("#pmMateModuId").val());
				if(pmEntryNo_U==$.trim(pmMateEntryNo.val())){
					TB_showMaskLayer("正在保存...");
					$("#pmMateEntryFrom").ajaxSubmit(function(result){
						var toJsonResult = eval("("+result+")");
						if("success"==toJsonResult.status){
							openTipSuccess("保存成功");
							doQueryMateById($("#pmMateModuId").val());
						}else{
							openTipFailure("保存失败");
							return false;
						}	
					});
					TB_removeMaskLayer();
				}else{
					if($.trim(result)!=''){
						alert("企划案例编号已经存在");
						pmMateEntryNo.focus();
						return false;
					}else{
						TB_showMaskLayer("正在保存...");
						//alert("save ... pmMateEntryId:"+$("#pmMateEntryId").val());
						$("#pmMateEntryFrom").ajaxSubmit(function(result){
							var toJsonResult = eval("("+result+")");
							//alert(toJsonResult.status+"  "+toJsonResult.pmMateEntryId);
							if("success"==toJsonResult.status){
								openTipSuccess("保存成功");
								//alert(toJsonResult.pmMateEntryId);
								$("#pmEntryId").val(toJsonResult.pmMateEntryId);
								$("#pmMateModuId").val(toJsonResult.pmMateModuId);
								$("#pmMateEntryId").val(toJsonResult.pmMateEntryId);
								$("#pmMateEntryNo").attr("pmEntryNo_U",toJsonResult.pmMateEntryNo);
								
								var id = toJsonResult.pmMateEntryId;
								//updateMateEntry(id);
								doQueryMateById($("#pmMateModuId").val());
							}else{
								openTipFailure("保存失败");
								return false;
							}	
						});
						TB_removeMaskLayer();
					}
				}
			});
		}else{
			var pmEntryNo_U = $.trim($("#pmMateEntryNo").attr("pmEntryNo_U"));
			$.post(url,param,function(result){
				//alert(pmEntryNo_U==$.trim(pmMateEntryNo.val()));
				if(pmEntryNo_U==$.trim(pmMateEntryNo.val())){
					TB_showMaskLayer("正在保存...");
					$("#pmMateEntryFrom").ajaxSubmit(function(result){
						var toJsonResult = eval("("+result+")");
						if("success"==toJsonResult.status){
							openTipSuccess("修改成功");
							$("#flag").val("1");
							doQueryMateById($("#pmMateModuId").val());
						}else{
							openTipFailure("修改失败");
							return false;
						}	
					});
					TB_removeMaskLayer();
				}else{
					if($.trim(result)!=''){
						alert("企划案例编号已经存在");
						pmMateEntryNo.focus();
						return false;
					}else{
						TB_showMaskLayer("正在保存...");
						$("#pmMateEntryFrom").ajaxSubmit(function(result){
							var toJsonResult = eval("("+result+")");
							if("success"==toJsonResult.status){
								openTipSuccess("修改成功");
								$("#flag").val("1");
								doQueryMateById($("#pmMateModuId").val());
							}else{
								openTipFailure("修改失败");
								return false;
							}	
						});
						TB_removeMaskLayer();
					}
				}
			});
		}
	}
}
/**
 * 显示提示成功
 * @param result
 */
function openTipSuccess(result){
	var myDate = new Date();
	var hour = myDate.getHours();      //获取当前小时数(0-23)
	var minu = myDate.getMinutes();    //获取当前分钟数(0-59)
	var sec  = myDate.getSeconds();    //获取当前秒数(0-59)
	$('#operateTip').html(hour +':' + minu + ':' + sec +' ' + result).css({'color':'green'}).show().fadeOut(5000);
}
/**
 * 显示提示不成功
 * @param result
 */
function openTipFailure(result){
	$('#operateTip').css({'color':'red'}).html(result).show().fadeOut(5000);
}