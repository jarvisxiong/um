
//第一次进入时默认展开合同留方
var isFirst= true;
var isHide_bt_attachUpload = false;	//是否要隐藏上传附件按钮（定表单走完后，新增文本库时，关闭上传附件按钮）
$(document).ready(function() {
			getContButton();
			// 如果是编辑状态则不显示右侧标注
			if (!isCanEditor()) {
				// 提交后合同名称、编号不可编辑
				$(".scName").attr("readonly",true);
				$("#approveNo").attr("readonly",false);
				$("#isMark").val("true");
				$("#btDel").show();
				
			}else{
				
				// 项目
				$('#projectName').orgSelect({
							muti : true,
							showProjectOrg : true,
							orgTreeType : '2',
							callfun:"doChangeProject()"// 选中项目点击确定回调函数
						});
			}
			$("#curUserName").userSelect({
				muti : true,
				nameField : 'curUserName',
				cdField : 'curUserCd'
			});
			/*$("#msgInfo").css("height", ($(document).height() - 80) + "px");
			recurentlyConInfo(); // 更新合同最近操作信息*/
			// 同时会更新最近合同的信息包含附件
			reloadAttachs();
			/*reloadMsg(""); // 更新留言*/
			/*reloadHisRecordVersion(); // 更新历史记录*/

			$("#contAttachDiv").show();

			$(".downAttach").click(function(){			
				ymPrompt.confirmInfo({
						icoCls : "",
						title : "模板附件下载",
						message : "<div id='AttachDiv'><img align='absMiddle' src='"
								+ _ctx + "/images/loading.gif'></div>",
						useSlide : true,
						winPos : "c",
						width : 400,
						height : 300,
						maxBtn : false,
						allowRightMenu : true,
						afterShow : function() {
							var url = _ctx + "/sc/sc-contract-templet!attachLoadList.action";
							var data = {
								id : $("#conTemletId").val()
							};
					$.post(url, data, function(result) {
						$("#AttachDiv").html(result);
						$(".ym-btn .btnStyle").val("关闭");
						  
						$($(".ym-btn .btnStyle")[1]).remove();
							});
				},
				handler : closeDialog,
				autoClose : false
				});
			
			});
			//初始化从定标审批表中带出的数据
			if ($("#resNo").val().trim() != "") {

				$("#approveNo").attr("readonly", "true");
				if ($("#resFileds").val().trim() != "") {
					var resFields = toJson($("#resFileds").val());
					//字段，值
					var field,fieldVal;
					$.each(resFields, function() {
						   field=this.fieldName;
						   fieldVal=this.fieldVal;
						    $("#"+field).val(fieldVal);				    
							});
					doChangeProject();
				}
			}
			try{
				/*showMsgDetail();*/
			}catch(e){}
});
function closeDialog(){
ymPrompt.close();

}

function isEmpty(str) {
	return (typeof(str) === "undefined" || str === null || (str.trim().length === 0));
}
/**
 * 选中项目执行回调函数生成合同编号
 */
function doChangeProject(){

	// 合同编号是否已存在
	$.post(_ctx + "/sc/sc-contract-templet-info!getProjectShortName.action", {
				projectCd : $("#projectCd").val().trim()
			}, function(result) {
				var _rJson = toJson(""+result);
				if (_rJson != undefined && _rJson.status != undefined) {
					if (_rJson.status != true) {
					
							ymPrompt.alert({
										message : _rJson.errormsg,
										title : "提示",
										handler : function(tp) {
										}
									});
					} else {
					var _newContNo=_rJson.shortName;
					// 二级编号规则 yx,bg,zl编号规则取年份号（2位）如：2012 -->12
					var _roleDate=",yx,bg,zl,"
					var _year= ""+new Date().getFullYear();
					    _newContNo+=$("#thirdSn").val();// +_year.substring(2,4);
						isExsitConNoAndBuildNewNo(_newContNo,1);
					}
				} else {
					alert("无法解析返回的信息！");
				}
			});
	
}
/**
 * 项目业态关闭、确定未选中任何业态时自动生成合同编号
 */
function doExecPlanLayOutColse(){
var _newContNo=ymPrompt.newContNo+$("#thirdSn").val();
   isExsitConNoAndBuildNewNo(_newContNo);
   ymPrompt.close();
}
function selectedPlanLayOut(dom,secondSn){
	var _newContNo=ymPrompt.newContNo+secondSn+$("#thirdSn").val();

   isExsitConNoAndBuildNewNo(_newContNo);
   ymPrompt.close();
}

/**
 * 判断当前合同编号是否已存在并构建新的合同编号
 */
function isExsitConNoAndBuildNewNo(newConNo,isYear) 
{
	// 合同编号是否已存在
	$.post(_ctx + "/sc/sc-contract-templet-info!isExsitConNo.action", {
				contractNo :newConNo,
				actType:"buildNewConNo",// 生成新的合同编号,
				isYear:isYear
			}, function(result) {
				var _rJson = toJson(result);
				if (_rJson != undefined && _rJson.status != undefined) {
					if (_rJson.status != true) {
						alert(_rJson.errorMsg);
					} else {
						$("#contractNo").val(_rJson.newContNo);	
						$("#inviteNo").val("ZB"+_rJson.newContNo);	
						return;
					}
				} else {
					alert("无法解析返回的信息！");
				}
			});
}
/**
 * 获取按钮
 * 
 */
function getContButton() {
	$.post(_ctx + "/sc/sc-contract-templet-info!loadNonStandardButton.action",
			{
				scContractId : $("#scContractId").val(),
				isOnlySee:$("#isOnlySee").val()
				
			}, function(result) {
				$("#showButton").html(result).find("[type='button']").css(
						"height", "26px").css("line-height", "22px");
				if(isHide_bt_attachUpload){
					$("#bt_attachUpload").hide();
				}
			});
}
/**
 * 判断当前合同编号是否已存在
 */
function isExsitConNo() {
	// 合同编号是否已存在
	$.post(_ctx + "/sc/sc-contract-templet-info!isExsitConNo.action", {
				contractNo : $("#contractNo").val().trim()
			}, function(result) {
				var _rJson = toJson(result);
				if (_rJson != undefined && _rJson.status != undefined) {
					if (_rJson.status != true) {
						alert(_rJson.errorMsg);
					} else {
						if (_rJson.count > 0) {
							ymPrompt.alert({
										message : "当前合同编号已经存在！",
										title : "提示",
										handler : function(tp) {
								         $("#contractNo").val("");
										}
									});

							$("#contractNo").val("");
						}

						return;
					}
				} else {

					ymPrompt.alert({
								message : "无法解析返回的信息！",
								title : "提示",
								handler : function(tp) {
								}
							});

					// alert("无法解析返回的信息！");
				}

			});
}
// 返回
function doReturn() {
	window.location.href = _ctx + "/sc/sc-contract-templet-info.action";
}
/**
 * 更新合同状态
 * 
 * @param statusName
 *            状态名称:如 提交，审核通过等
 * @param statusCd
 *            状态CD
 * @param isAlert
 *            是否提示
 * @return
 */
function updateScStatus(statusName, statusCd, isAlert) {

	if ($("#projectName").val().trim() == "") {
		ymPrompt.alert({
					message : "项目名称不能为空!",
					title : "提示",
					handler : function(tp) {

						$("#projectName").focus();

					}
				});
		return;

	}
	if ($("#contractNo").val().trim() == "") {

		ymPrompt.alert({
					message : "合同编号不能为空！",
					title : "提示",
					handler : function(tp) {

						$("#contractNo").focus();

					}
				});
		return;
	}
	if ($("#contractName").val().trim() == "") {

		ymPrompt.alert({
					message : "合同名称不能为空！",
					title : "提示",
					handler : function(tp) {
						$("#contractName").focus();
					}
				});

		return;

	}
	if ($("#curUserName").val().trim() == "") {

		ymPrompt.alert({
					message : "合同责任人不能为空！",
					title : "提示",
					handler : function(tp) {

						$("#curUserName").focus();
					}
				});

		return;
	}
	if (typeof(isAlert) == "undefined") {
		ymPrompt.params = {};
		ymPrompt.params.statusCd = statusCd;
		ymPrompt.confirmInfo({
					message : "你确认要" + statusName + "吗？",
					title : statusName,
					handler : function(tp) {
						if (tp == 'ok') {
							doRunUpdate(ymPrompt.params.statusCd);
						}
					}
				});

	} else {
		
		doRunUpdate(ymPrompt.params.statusCd);
		
	}
}

/**
 * 执行列新
 * 
 * @param statusCd
 *            合同状态
 * @return
 */
function doRunUpdate(statusCd) {
	TB_showMaskLayer("请稍等,正在保存数据中...");
	$("#scnonstatusCd").val(statusCd);
	
	$("#contForm").ajaxSubmit(function(result) {
				result=""+result;
				var _rJson = toJson(result.toLowerCase());
			

				if (_rJson != undefined && _rJson.status != undefined) {

					if (_rJson.status != true) {
						alert(_rJson.errorMsg);

					} else {
						  
						   if(_rJson.ctledgermsg){
							   var _resultJson=toJson(result);		
							   var _ctLedgerJson=toJson("{"+_resultJson.ctLedgerMsg.replace(/\'/ig,"\"")+"}");
							   /**
							    * 合同网批中数据，设置合同台账编号、定标总价值
							    */
								$.ajax({
									url :_ctx + "/res/res-approve-info!saveContent.action"+"?ifNoContLeger="+$("#ifNoContLeger").val(),
									type : 'POST',
									data :_ctLedgerJson,
									async : false, // ajax同步
									timeout : 1000,
									error : function(responseText) {	
										//设置网批内容成功
										if(responseText == "true"){
											
											doExecResTabImportedEvent(_ctLedgerJson["templateBean.contractNo"],_ctLedgerJson["templateBean.contractPrice"],true);
										}else{
											
											doExecResTabImportedEvent(_ctLedgerJson["templateBean.contractNo"],_ctLedgerJson["templateBean.contractPrice"],false);
											
										}
									},
									success : function(responseText) {	
										//设置网批内容成功
										if(responseText == "true"){
											
											doExecResTabImportedEvent(_ctLedgerJson["templateBean.contractNo"],_ctLedgerJson["templateBean.contractPrice"],true);
										}else{
											
											doExecResTabImportedEvent(_ctLedgerJson["templateBean.contractNo"],_ctLedgerJson["templateBean.contractPrice"],false);
											
										}
										}
									});
							   
							  
						   }
						
						
						TB_removeMaskLayer();
						ymPrompt.alert({
							message : "已操作成功！",
							title : "提示",
							handler : function(tp) {
							
							var sconId=_rJson.scconid;						
							if(sconId== undefined){
								sconId=_rJson.scContId;
								
							}
							   doClearParentTabResParamVal();
								var framId= $.trim($("#frameId").val());
								if(framId != ""){ 
									// 如果是由其他TAB页打开的，则需要关闭当前TAB
						        doCloseOtherTab();
								}else{
									var resNo = "";
									if(_rJson.resNo) {
										resNo = _rJson.resNo;
									}
								// 否否刷新当前页面
									window.location.href = _ctx
											+ "/sc/sc-contract-templet-info!readNonStandardCon.action?scContId="
											+ sconId + "&resNo=" + resNo;
								}
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

	return;

}
// 保存
function doSave() {

	if ($("#projectName").val().trim() == "") {
		ymPrompt.alert({
					message : "项目名称不能为空!",
					title : "提示",
					handler : function(tp) {

						$("#projectName").focus();
					}
				});
		return;

	}
	if ($("#contractNo").val().trim() == "") {

		ymPrompt.alert({
					message : "合同编号不能为空！",
					title : "提示",
					handler : function(tp) {

						$("#contractNo").focus();

					}
				});
		return;
	}
	if ($("#contractName").val().trim() == "") {

		ymPrompt.alert({
					message : "合同名称不能为空！",
					title : "提示",
					handler : function(tp) {
						$("#contractName").focus();
					}
				});

		return;

	}
	if ($("#curUserName").val().trim() == "") {

		ymPrompt.alert({
					message : "合同责任人不能为空！",
					title : "提示",
					handler : function(tp) {
						$("#curUserName").focus();
					}
				});

		return;
	}
	TB_showMaskLayer("请稍等,正在保存数据中...");
	$("#contForm").ajaxSubmit(function(result) {
				TB_removeMaskLayer();
				result=""+result;
				var _rJson = toJson(result.toLowerCase());
				if (_rJson != undefined && _rJson.status != undefined) {

					if (_rJson.status != true) {
						alert(_rJson.errorMsg);

					} else {
						TB_removeMaskLayer();
						if ($("#scContractId").val().trim() == "") {

							ymPrompt.alert({
								message : "保存成功！",
								title : "提示",
								handler : function(tp) {
									var sconId=_rJson.scconid;
									if(sconId== undefined){
										sconId=_rJson.scContId;
										
									}
									ymPrompt.close();
									doClearParentTabResParamVal();
									var framId= $.trim($("#frameId").val());
									if(framId != ""){ 
										// 如果是由其他TAB页打开的，则需要关闭当前TAB
							        doCloseOtherTab();
									}else{
									// 否否刷新当前页面
										window.location.href = _ctx
												+ "/sc/sc-contract-templet-info!readNonStandardCon.action?scContId="
												+ sconId;
									}
								}
							});

						} else {

							ymPrompt.alert({
										message : "保存成功！",
										title : "提示",
										handler : function(tp) {

										}
									});
							$("#btDel").show();
							var sconId=_rJson.scconid;
						
							if(sconId== undefined){
								sconId=_rJson.scContId;
								
							}
							$("#scContractId").val(sconId);
							$("#recordVersion").val(_rJson.reversion);
							$("#fillRecordVersion").val(_rJson.fillreversion);
							doClearParentTabResParamVal();
							var framId= $.trim($("#frameId").val());
							if(framId != ""){ 
								// 如果是由其他TAB页打开的，则需要关闭当前TAB
					        doCloseOtherTab();
							}
						}

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
				
			});

}
/**
 * 清除父窗体从网批中带的参数值
 * 
 * @return
 */
function doClearParentTabResParamVal(){
	var event = {data:{tabId:"scconInfo_Select",src:"",typeCd:1}};
	var frame;
	var parentFram;
	var _bodyContent;
	var father=null;
	// 如果打开方式为tab
	if(parent.TabUtils!=undefined && typeof(parent.TabUtils.getTabFrame) == "function"){
		frame=parent.TabUtils.getTabFrame(event);
		parentFram=frame;
	  _bodyContent=$(frame).contents();
	}else{
		// 打开方式为window.open
		 father = window.opener;
		 parentFram=father;
			if (father != null) {
				_bodyContent=$(father.document);
		   }
			frame=father;
	}
	/**
	 * 说明是用窗窗体打开的
	 */
	if(frame!=undefined  && frame!=null && $(frame).contents()!=undefined){	
		$(_bodyContent).find(".res").val("");
	}
}
/**
 * 当合同保存或提交后则关当前Tab页和合同文本库Tab
 * 
 * @return
 */

function doCloseOtherTab() {
	var conNo = $("#contractNo").val();
	var framId = $.trim($("#frameId").val());
	if (framId != "") {
		var event = {
			data : {
				tabId : framId,
				src : "",
				typeCd : 1
			}
		};

		var frame;
		var parentFram;
		var father = null;
		// 如果打开方式为tab
		if (parent.TabUtils != undefined) {
			frame = parent.TabUtils.getTabFrame(event);
			parentFram = frame;

		} else {
			// 打开方式为window.open
			father = window.opener;
			parentFram = father;
			if (father != null) {
				_bodyContent = $(father.document);
			}
			frame = father;
		}
		//判断网批Tab是否已经关闭
		if (parent.TabUtils != undefined && parent.TabUtils.getTabFrame(event)[0] != undefined) {
			// 设置活动Tab
			parent.TabUtils.showTab(event);
		}
		// 关联合同台账
		if (parent.TabUtils != undefined
				&& typeof(parent.TabUtils.getTabFrame(event)[0].contentWindow.autoQuickSearchCtledger) == "function") {
			// 使用tab打开
			parent.TabUtils.getTabFrame(event)[0].contentWindow
					.autoQuickSearchCtledger(conNo);

		} else if (window.opener != undefined
				&& typeof(window.opener.autoQuickSearchCtledger) == "function") { // 使用open打开
			window.opener.autoQuickSearchCtledger(conNo);
		}

		// 查询合同
		if (parent.TabUtils != undefined
				&& typeof(parent.TabUtils.getTabFrame(event)[0].contentWindow.autoQuickSearch) == "function") {
			// 使用tab打开
			parent.TabUtils.getTabFrame(event)[0].contentWindow
					.autoQuickSearch(conNo);

		} else if (window.opener != undefined
				&& typeof(window.opener.autoQuickSearch) == "function") { // 使用open打开
			window.opener.autoQuickSearch(conNo);
		}
	}
	//
	// 先闭父亲Tab
	closeTab("scconInfo_Select");
	// 闭闭当前
	closeTab("scnonstandardTemplet");

}
/**
 * 关闭tab
 * 
 * @param tabId
 *            tabID
 * @return
 */
function closeTab(tabId) {
	if (tabId != "") {
		var event = {
			data : {
				tabId : tabId,
				src : "",
				typeCd : 1
			}
		};
		var father = null;
		// 如果打开方式为tab
		if (parent.TabUtils != undefined && parent.TabUtils.getTabFrame(event)[0]!=undefined) {
			parent.TabUtils.closeTab(event);

		} else {
			// 打开方式为window.open
			father = window.opener;
			if (father != null || father != undefined) {
				// 先关闭父亲窗体
				father.close();
			}
			window.close();

		}
	}
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
	ymPrompt.confirmInfo({
		message : "删除不可恢复,你确认要删除当前合同吗？",
		title : "删除合同",
		handler : function(tp) {
			if (tp == 'ok') {

				TB_showMaskLayer("请稍等,正在删除合同...");
				$.post(_ctx + "/sc/sc-contract-templet-info!delete.action", {
							id : $("#scContractId").val()
						}, function(result) {
							var _rJson = toJson(result);
							if (_rJson != undefined
									&& _rJson.status != undefined) {
								if (_rJson.status != true) {
									if (_rJson.errorMsg
											.indexOf("could not execute") > -1) {

										ymPrompt.alert({
													message : "当前合同不能被刪除！！",
													title : "提示",
													handler : function(tp) {
														if (tp == 'ok') {

														}
													}
												});
									} else {
										alert(_rJson.errorMsg);
									}

								} else {
									TB_removeMaskLayer();

									ymPrompt.alert({
										message : "当前合同删除成功",
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

			}
		}
	});

}

/**
 * 上传附件的onClick事件
 * 
 * @param titileName
 *            弹出下载框的标题
 * @param bizEntityId
 * @param bizModuleCd
 *            sctemplet
 * @param id
 * @param domId
 */
function showUploadConAttachDialog(titileName, actType, para) {

	ymPrompt.confirmInfo({
		icoCls : "",
		title : titileName,
		message : "<div id='singleAttachDiv'><img align='absMiddle' src='"
				+ _ctx + "/images/loading.gif'></div>",
		useSlide : true,
		winPos : "c",
		width : 400,
		height : 200,
		maxBtn : false,
		allowRightMenu : true,
		afterShow : function() {
			var url = _ctx + "/sc/sc-contract-templet-info!attachUpload.action";
			var data = {
				bizEntityName : 'scConAttachUpload',
				ifNoContLeger : $("#ifNoContLeger").val()
			};
			$.post(url, data, function(result) {
						$("#singleAttachDiv").html(result);
						$("#scAttachContId").val($("#scContractId").val());
						$("#scAttachHistContId").val($("#hisContId").val());
						$("#sysTempletId").val($("#conTemletId").val());
						$("#isscstandard").val("0");

					});
		},
		handler : doConAttachUpload,
		autoClose : false
	});
}
var canClick = true; // 可以点击确定按钮，true-可以点击; false-已经点击过确定，等待处理完成才可以再次点击
function doConAttachUpload(btn) {
	// 确定
	if (btn == 'ok') {
		if(canClick == false) { // 不可重复确定
			return;
		}
		var fileName = $("#attachSingleForm_uploadInput").val();
		// 上传文件和合同模板ID必须同时存在
		if (!fileName && isEmpty($("#conTemletId").val())) {
			alert("请选择待上传的文件!");
			return;
		} else {

			if (isEmpty($("#attachTypeCd").val().trim())) {
				alert("请选择附件类型！");
				return;
			}
			 if(isPassMaxLen($("#remark").val(),200)){			    	
			    	alert("附件描述信息不能超过200个字符！");
			    	return;
			    }
			canClick = false;
			TB_showMaskLayer("正在上传附件...");
			$("#conAttachForm").ajaxSubmit(function(result) {
				TB_removeMaskLayer();
				canClick = true;
				if (result.indexOf("true") != -1) {
					if(typeof(result)!="object"){
						result=result.toLowerCase();
					}
					var _rJson = toJson(result);
					if (_rJson != undefined && _rJson.status != undefined) {
						if($("#scContractId").val().trim()==""){
						$("#scContractId").val(_rJson.scconid);	
						$("#hisContId").val(_rJson.schiscontid);
						}
					}
					reloadAttachs();
					// 同时会更新最近合同的信息包含留方、附件、历史记录
					/*reloadMsg(""); // 更新留言记录
					reloadHisRecordVersion(); // 更新历史记录*/

					ymPrompt.close();
					alert("文件上传成功！");

				} else {
					alert(result);
				}
			});
		}
	} 
	 else {
		 ymPrompt.close();
	}
	
}