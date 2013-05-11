var editor;
// 编辑器
var _editor;
// 是否自动设置合同总价
var isSetAllprice = false;
KindEditor.ready(function(K) {
	var _adjustHeight = 144;
	// 如是合同未提交，则调整的高度要包含最近留言信息区域搞
	if (isAdjust()) {
		_adjustHeight -= 40;
	} else {
		// _adjustHeight+=36;

	}
	// 判断总价是否为空
	if ($("#contractPrice").val() == "") {

		isSetAllprice = true;
	}
	_adjustHeight = ($(document).height() - _adjustHeight);
	editor = K.create('textarea[name="content"]', {
				width : "100%",
				height : _adjustHeight + "px",
				allowFileManager : true,
				items : []
			});
	// 全部设成只读状态
	editor.readonly();
	// 标记列表高度
	$("#markList").css("height", _adjustHeight + "px");
	$("#markFillList").css("height", _adjustHeight + "px");
	// 转换填充的数据
	var _fillJson = toJson($("#fillJson").val());
	// 编辑器控件
	_editor = $("#keEditIframe");
	$(_editor).contents().find("span[otype=min]").unbind("click").css(
			"background", "#DEE1DD");
	// 如果合同总价在合同文本中存在，则其值不允许输入直接从文本中带出
	if ($(_editor).contents().find("span[allPrice=1]").length > 0) {
		$("#contractPrice").attr("readonly", "true");
	}
	// 初始化输入控件  otype=min 为合同文本填空输入框
	$(_editor).contents().find("span[otype=min]").each(function(idx, input) {
		//len:输入框宽度属性 若无此属性则默认宽度为10
		var _len = $(input).attr("len");
		var _index = $(input).attr("index");	
		if (_len == undefined) {
			
			_len = 10;
		}
		if (_index == undefined || _index == "") {
			$(input).attr("index", (idx + 1));
		}
		var _style = _Inputcss + _len+ "px;border-bottom:1px solid #000;background:#DEE1DD;";

		$(input).attr("style", _style);
		if ($(input).text().trim() == "") {
			$(input).html("&nbsp;&nbsp;");

		} else {
			$(input).val($(input).text().trim());
			// 如果需要自动设置则是创建状态
			if (isSetAllprice && isCanEditor()) {
				// allprice 为合同总价，非必需项最多只有一个，它的值用于自动回填主表合同总价
				if ($(input).attr("allPrice")) {
					$("#contractPrice").val($(input).val());
				}

			}
		}
		//数据页面后 ,自动更新合同文本中的填空输入框内容
		if ($("#isCanInput").val() != "" && _fillJson.length > 0) {
			try{
				$(input).attr("id", _fillJson[idx].contractTempletFillId);
			}catch(e){}
			try{
				if (_fillJson[idx].contractFillContent.trim() != ""
						&& $(input).val().trim() != "") {
					$(input).text(_fillJson[idx].contractFillContent);
					$(input).val(_fillJson[idx].contractFillContent);
					$("#fillRecordVersion").val(_fillJson[idx].recordVersion);
				}
				$("#markTop").hide();
			}catch(e){}
		}

		if (isCanEditor()) {// 如果未提交则点击编辑填写数据
			$(input).click(function() {
				//只有用相关合同负责人可以编辑填空信息   且从定标审批表带出的填空信息也不可编辑只能通过批注修改
                 //isOnlySee：若是合同负责人则isonlySee值为0; 默认其值为1
				//isCanEdit：0-不可编辑,1-可编辑;如果是定标审批表中带入的字段值其值为0则不允许编辑,只可通过批注修改
				if($("#isOnlySee").val() == '1' || $(this).attr("isCanEdit") == "0"){
					  return;		
					  }
				//isClick：false-说明输入文本框未被选中,true-说明输入文本框被选中
				$(_editor).contents().find("span[otype=min]").attr("isClick","false");			
				$(this).attr("isClick", "true");
				 editor.clickToolbar('datainput');

			});
		}

	});

	// 如果是编辑状态则不显示右侧标注
	if (isCanEditor()) {
			// 项目
	$('#projectName').orgSelect({
				muti : true,
				showProjectOrg : true,
				orgTreeType : '2',
				callfun : "doChangeProject()"// 选中项目点击确定回调函数
			});
	$("#curUserName").userSelect({
				muti : true,
				nameField : 'curUserName',
				cdField : 'curUserCd'
			});

		$("#markList").remove();
		$("#markTop").remove();
		$("#fillList").parent().hide();
		$("#contDivLeft").attr("style", "width:100%; float: left;");

	} else {// 提交后只能用来显示填充的数据
		// 合同名称、编号不可编辑
		$(".scName").attr("readonly", true);
		// 确定为批注状态
		$("#isMark").val("true");
		$("#btDel").show();
		if ($("#isCanInput").val() == "20") {
			$("#markList").show();
			$("#markTop").show();
		}
		// 20已提交 30已标注完毕
		if ($("#isCanInput").val() == "20" || $("#isCanInput").val() == "30") {
			loader();
			fillList();
		}

	}
	// 清除编辑器上方工具栏
	$(".ke-toolbar").remove();
	/*recurentlyConInfo(); // 更新最近操作信息*/
	reloadAttachs();
	/*reloadMsg(""); // 更新留言
	reloadHisRecordVersion(); // 更新历史记录*/


	$(".downAttach").click(function() {
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
						var url = _ctx
								+ "/sc/sc-contract-templet!attachLoadList.action";
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
				    	$(_editor).contents().find("span[resname='"+field+"']").html(fieldVal).val(fieldVal).attr("isHasInput","1").attr("isCanEdit","0");
					});
			//設置合同總價	
			var allPrice=$(_editor).contents().find("span[allPrice=1]").val();
			if (allPrice) {
				$("#contractPrice").val(allPrice);
			}
			doChangeProject();
		}
	}

});
function closeDialog() {
	ymPrompt.close();

}
/**
 * 选中项目执行回调函数生成合同编号
 */
function doChangeProject() {
if($("#projectCd").val() == undefined || $("#projectCd").val() == ""){
	return;
}
	// 合同编号是否已存在
	$.post(_ctx + "/sc/sc-contract-templet-info!getProjectShortName.action", {
				projectCd : $("#projectCd").val().trim()
			}, function(result) {
				var _rJson = toJson("" + result);
				if (_rJson != undefined && _rJson.status != undefined) {
					if (_rJson.status != true) {

						ymPrompt.alert({
									message : _rJson.errormsg,
									title : "提示",
									handler : function(tp) {
									}
								});
					} else {
						var _newContNo = _rJson.shortName;
						// 二级编号规则 yx,bg,zl编号规则取年份号（2位）如：2012 -->12
						var _roleDate = ",yx,bg,zl,"
						var _year = "" + new Date().getFullYear();
						_newContNo += $("#thirdSn").val();// +_year.substring(2,4);
						isExsitConNoAndBuildNewNo(_newContNo, 1);
					}
				} else {
					alert("无法解析返回的信息！");
				}
			});

}
/**
 * 项目业态关闭、确定未选中任何业态时自动生成合同编号
 */
function doExecPlanLayOutColse() {
	var _newContNo = ymPrompt.newContNo + $("#thirdSn").val();
	isExsitConNoAndBuildNewNo(_newContNo);
	ymPrompt.close();

}
function selectedPlanLayOut(dom, secondSn) {
	var _newContNo = ymPrompt.newContNo + secondSn + $("#thirdSn").val();

	isExsitConNoAndBuildNewNo(_newContNo);
	ymPrompt.close();
}

/**
 * 判断当前合同编号是否已存在并构建新的合同编号
 */
function isExsitConNoAndBuildNewNo(newConNo, isYear) {

	// 合同编号是否已存在
	$.post(_ctx + "/sc/sc-contract-templet-info!isExsitConNo.action", {
				contractNo : newConNo,
				actType : "buildNewConNo",// 生成新的合同编号
				isYear : isYear
			}, function(result) {
				var _rJson = toJson(result);
				if (_rJson != undefined && _rJson.status != undefined) {
					if (_rJson.status != true) {
						alert(_rJson.errorMsg);
					} else {
						$("#contractNo").val(_rJson.newContNo);
						$("#inviteNo").val("ZB" + _rJson.newContNo);
						return;
					}
				} else {
					alert("无法解析返回的信息！");
				}
			});
}
/**
 * 判断当前合同编号是否已存在
 */
function isExsitConNo() {
	// 如果为空则不需判断是否存在
	if ($("#contractNo").val().trim() == "") {
		return;
	}
	// 合同编号是否已存在
	$.post(_ctx + "/sc/sc-contract-templet-info!isExsitConNo.action", {
				contractNo : $("#contractNo").val().trim(),
				isReBuild : 0
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

						}
						return;
					}
				} else {
					alert("无法解析返回的信息！");
				}
			});
}
// 返回
function doReturn() {
	window.location.href = _ctx + "/sc/sc-contract-templet-info.action";
}

/**
 * 下一个未填控件
 */
function doNext() {
	var _isAlert = true;
	$(_editor).contents().find("span[otype=min]").each(function(idx, input) {
		var _len = $(input).attr("len");
		if (_len == undefined) {
			_len = 10;
		}
		_len += "px";
		var _inputText = "" + $(input).val();
		var _isSkip = $(input).attr("isSkip");
		if (_inputText.trim().length > 9) {

			_len = "auto";
		}
		// 填空区域有未填写的，则不允许提交
		if ((_inputText.trim() == "" || $(input).attr("isHasInput") != "1")
				&& _isSkip != "1") {
			// 获取标注偏移量Y
			var y = GetObjPos($(_editor).contents().find("span[otype=min]")
					.get(idx))["y"];
			if (y > 100) {
				y = y - 30;
			}
			var contaner = $(_editor).contents().find("html,body");
			contaner.each(function() {
						$(this).animate({
									scrollTop : y
								}, 0);
					});

			var _style = _Inputcss + _len
					+ ";border-bottom:1px solid #000;background-color:red;";

			$(input).attr("style", _style);
			$(_editor).contents().find("span[otype=min]").attr("isClick",
					"false");
			$(input).attr("isClick", "true");
			_isAlert = false;
			// 自动弹出输入框
			editor.clickToolbar('datainput');
			return false;
		}
	});
	if (_isAlert) {
		$("#curUserName").focus();

	}
	isSumitDoNext = true;
}
// 是否为提交时自动下一个
var isSumitDoNext = false;
/**
 * 自动跳到下一个
 */
function doAutoSkipNext() {
	isSumitDoNext = false;
	$(_editor).contents().find("span[otype=min]").attr("isReSkip", "0");
	doReNext();
}

/**
 * 下一个未填控件 如果设置了调过，仍然可以重新扫描到
 */
function doReNext() {
	var _isAlert = true;
	$(_editor).contents().find("span[otype=min]").each(function(idx, input) {
		var _len = $(input).attr("len");
		if (_len == undefined) {
			_len = 10;
		}
		_len += "px";
		var _inputText = "" + $(input).val();
		var _isSkip = $(input).attr("isReSkip");
		if (_inputText.trim().length > 9) {
			_len = "auto";
		}
		// 填空区域有未填写的，则不允许提交
		if ((_inputText.trim() == "" || $(input).attr("isHasInput") != "1")
				&& _isSkip != "1") {
			// 获取标注偏移量Y
			var y = GetObjPos($(_editor).contents().find("span[otype=min]")
					.get(idx))["y"];
			if (y > 100) {
				y = y - 30;
			}
			var contaner = $(_editor).contents().find("html,body");
			contaner.each(function() {
						$(this).animate({
									scrollTop : y
								}, 0);
					});

			var _style = _Inputcss + _len
					+ ";border-bottom:1px solid #000;background-color:red;";

			$(input).attr("style", _style);
			$(_editor).contents().find("span[otype=min]").attr("isClick",
					"false");
			$(input).attr("isClick", "true");
			_isAlert = false;
			// 自动弹出输入框
			editor.clickToolbar('datainput');
			return false;
		}
	});
	if (_isAlert) {
		$("#curUserName").focus();

	}
}

// 是否可以可编辑输入框数据
function isCanEditor() {
	if ($("#isCanInput").val() == "10" || $("#isCanInput").val() == "") {
		return true;
	}
	return false;
}
// 是否可以可编辑输入框数据
function isAdjust() {
	if ($("#isCanInput").val() == "" || eval($("#isCanInput").val()) < 50) {
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
		message : '删除不可恢复,你确认要删除当前合同吗？',
		title : "合同删除",
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
										// alert("当前合同不能被刪除！");

										ymPrompt.alert({
													message : "当前合同不能被刪除！",
													title : "提示",
													handler : function(tp) {

													}
												});
									} else {

										ymPrompt.alert({
													message : _rJson.errorMsg,
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
										window.location.href = _ctx
												+ "/sc/sc-contract-templet-info.action";
									}
								});

							}
							TB_removeMaskLayer();
						});
			}
		}
	});

}
