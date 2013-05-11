/**
 * 合同模板公共方法
 */
var editor;
// 编辑器
var _editor;

// display:-moz-inline-box;display: inline-block; overflow: visible;
// white-space:nowrap; vertical-align:bottom;cursor:pointer;
// width:80px;border-bottom:1px solid #000;background:#DEE1DD;
var _Inputcss = "display:-moz-inline-box;display: inline-block; overflow: visible; white-space:nowrap; vertical-align:bottom;cursor:pointer; width:";
var _urltoolParam = 'toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no';
String.prototype.trim = function() {
	// 用正则表达式将前后空格
	// 用空字符串替代。
	var t = this.replace(/(^\s*)|(\s*$)/g, "");
	return t.replace(/(^　*)|(　*$)/g, "");
}

function StringBuffer() {
	this._strs = new Array;
}
StringBuffer.prototype.append = function(str) {
	this._strs.push(str);
};
StringBuffer.prototype.toString = function() {
	return this._strs.join("");
};

// 转化为JSON
function toJson(str) {
	// 如果是对象则直接返回
	if(typeof(str)=="object"){
		return str;
	}
	if ("" == $.trim(str)) {
		return "";
	}
	str=str.replace("PRE","pre");
	str=str.replace("<pre>","").replace("</pre>","").replace("<PRE>","").replace("</PRE>","");
	return eval("(" + str + ")");
}
function isEmpty(str) {
	return (typeof(str) === "undefined" || str === null || (str.trim().length === 0));
}

// 返回val的字节长度
function getByteLen(val) {

	var len = 0;
	for (var i = 0; i < val.length; i++) {

		var intCode = val.charCodeAt(i);
		if (intCode >= 0 && intCode <= 128) {
			len = len + 1; // 非中文单个字符长度加 1
		} else {
			len = len + 2; // 中文字符长度则加 2
		}
	}
	return len;

}
/**
 * 判断是否超过最大的字符长度
 * 
 * @param {}
 *            val 字符串
 * @param {}
 *            maxLen 最大长度
 */
function isPassMaxLen(val, maxLen) {

	if (getByteLen(val) > maxLen) {
		return true;
	} else {
		return false;
	}
}
/**
 * 合并函数
 * 
 * @return
 */
function mergeCont() {	

	var _edit = $("#keEditIframe");
	// 清掉所有标记样式，如果存在的话
	clearMark();
	$(_edit).contents().find("ins").attr("style", "text-decoration: none;")
			.unbind('mouseenter').unbind('mouseleave');
	$(_edit).contents().find("[tableid]").css("background", "");
	$(_edit).contents().find("span[otype=min]").css("background-color", "");
	$("#markDiv").hide();
	$("#markTop").hide();
	$("#markFill").hide();
	$("#contDivLeft").attr("style", "width:100%; float: left;");

	$("#keEditIframe").contents().find("span[otype=min]").css("cursor",
			"default").unbind('mouseenter').unbind('mouseleave');
	// 清除删除内容
	$("#keEditIframe").contents().find("ins[isdel=true]")
			.css("display", "none").next("br").css("display","none");
}

/**
 * 审核通过
 */
function doScApprovePass() {

	ymPrompt.confirmInfo({message:'你确定要审核通过当前合同吗？',title:"合同审核",handler:function (tp){
		if (tp=='ok'){
             
	
			$(_editor).contents().find("ins").attr("style",
					"text-decoration: none;").unbind('mouseenter').removeAttr("isNew")
					.unbind('mouseleave');
			$(_editor).contents().find("[tableid]").css("background", "");		
			$("#markDiv").hide();
			$("#markTop").hide();
			$("#contDivLeft").attr("style", "width:100%; float: left;");

			$(_editor).contents().find("span[otype=min]").css("cursor",
					"default").unbind('mouseenter').unbind('mouseleave').css("background-color",
					"");
			// 清除删除内容
			$(_editor).contents().find("ins[isdel=true]")
			.css("display", "none").next("br").css("display","none");

			TB_showMaskLayer("请稍等,正在合并中...");
			var _content = "" + editor.html();

			// 合并历史版本
			$.post(_ctx + "/sc/sc-contract-templet-info!mergeCon.action", {
						scContractId : $("#scContractId").val(),
						hisConId : $("#hisContId").val(),
						actType : "merge",
						statusCD : "40",
						content : _content
					}, function(result) {
						var _rJson = toJson(result);

						if (_rJson != undefined && _rJson.status != undefined) {

							if (_rJson.status != true) {
								alert(_rJson.errorMsg);

							} else {
								TB_removeMaskLayer();

								
								ymPrompt.alert({message:"已合并成功！",title:"提示",handler:function (tp){
								
									window.location.href = _ctx
									+ "/sc/sc-contract-templet-info!mergeContract.action?scContId="
									+ _rJson.scConId;
								}});
							

								return;
							}
						} else {
							
							ymPrompt.alert({message:"无法解析返回的信息！",title:"提示",handler:function (tp){
							
							}});
							
						}
						TB_removeMaskLayer();
					});

		
			
		}}});
	
	
}

/**
 * 标记
 */
function doBookMark() {
	var _bookmarkId = "bkmk" + (new Date().getTime());
	editor.cmd.markcolor("yellow", _bookmarkId);
	var _content = "" + editor.html();
	editor.focus();

	// 合并历史版本
	$.post(_ctx + "/sc/sc-contract-templet-info!doBookmark.action", {
				scContractId : $("#scContractId").val(),
				hisConId : $("#hisContId").val(),
				actType : "bookMark",
				content : _content
			}, function(result) {
				var _rJson = toJson(result);

				if (_rJson != undefined && _rJson.status != undefined) {
					if (_rJson.status != true) {
						alert(_rJson.errorMsg);

					}
				} else {
					
					ymPrompt.alert({message:"无法解析返回的信息！",title:"提示",handler:function (tp){
						
					}});
					
				
				}

			});

}
/**
 * 清除标记信息
 */
function clearBookMark() {

	$(_editor).contents().find("span[optype=bookmark]").css("background-color",
			"").removeAttr("optype");

	var _content = "" + editor.html();
	editor.focus();

	// 合并历史版本
	$.post(_ctx + "/sc/sc-contract-templet-info!doBookmark.action", {
				scContractId : $("#scContractId").val(),
				hisConId : $("#hisContId").val(),
				actType : "bookMark",
				content : _content
			}, function(result) {
				var _rJson = toJson(result);

				if (_rJson != undefined && _rJson.status != undefined) {
					if (_rJson.status != true) {
						alert(_rJson.errorMsg);

					}
					$("#contractStatus")
							.html("<span style='color:red;'>标注信息已清除成功</span>");
				} else {
					
					ymPrompt.alert({message:"无法解析返回的信息！",title:"提示",handler:function (tp){
						
					}});
					
				
				}

			});

}
/**
 * 清除标记样式
 */
function clearMark() {
	$("#markIdent").hide();
	$(_editor).contents().find("span[optype=bookmark]").css("background-color","");
	var _content = "" + editor.html();
	editor.focus();
}
/**
 * 清除文本编辑器,填空列表,批注列表的样式
 * liwei3
 */
function clearEditorCss(){
	//清除填空样式
	var _spanInput=$("#keEditIframe").contents().find("span[id]");
	_spanInput.each(function(a,b) {
		$(b).removeClass().addClass("postil");
		if($(b).attr("allprice")=='1'){
			$(b).css("background", "yellow");
		}else{
			$(b).css("background", "#cbcbcb");
		}
		$("div[forid='" + $(b).get(0).id+ "']").parent().css("border-color","#ddd");
	});
	//清除批注样式
	var _insInput=$("#keEditIframe").contents().find("ins[id]");
	_insInput.each(function(a,b) {
		$(b).css("background", "yellow");
		$("div[forid='" + $(b).get(0).id + "']").parent().css("border-color","#ddd");
	});
}

/**
 * 填空列表js start
 * 
 * @return
 */
function fillList() {
	var $MARK_LIST = $("#markList");
	var $MARK_FILL_LIST = $("#markFillList");
	$MARK_FILL_LIST.html("");
	// 重新获取填空数据
	$.post(_ctx + "/sc/sc-contract-templet-info!getFill.action", {
		scContId : $("#scContractId").val()
	}, function(result) {
		var _rJson = toJson(result);
		if (_rJson != undefined) {
			resultCollection="";
			resultCollection = _rJson;
			// 重设填空样式和事件
			$.each(resultCollection, function(index, item) {
						var fillId = item.contractTempletFillId;
						var content = item.contractFillContent;
						
						if($("#keEditIframe").contents().find("span[id=" + fillId + "]").length>0){
						var _spanInput=$("#keEditIframe").contents().find("span[id=" + fillId + "]");
						$MARK_FILL_LIST.append(getFill(fillId, content, item, $(_spanInput).attr("index")));					
						_spanInput.each(function() {
									//碰到合同总价格的填空,背景设置为黄色,其余填空的背景设置为灰色
									if($(this).attr("allprice")=='1'){
										$(this).css("background", "yellow");
									}else{
										$(this).css("background", "#cbcbcb");
									}
									$(this).unbind('click');
									$(this).click(function() {
												clearEditorCss();
												$("#markList").hide();
												$("#markFillList").show();
												//$("#fillList").removeClass("changeList").addClass("select_changeList");
												//$("#fillList").siblings().removeClass("select_changeList").addClass("changeList");
												fillOldChange("fillList");
												
												// 批注鼠标移入
												var _divH = 0;
												var _insId = $(this).attr("id");
												$($("div[ctrType=scfill]")).each(
														function(a, b) {
															if ($(b).attr("forid") == _insId) {
																return false;
															}
															_divH += $(b).get(0).offsetHeight;
														});

												$(this).css("background", "Tomato");
												$("div[forid='" + $(this).get(0).id+ "']").parent().css("border-color","red");
												$(".filllist").get(0).scrollTop = _divH;
											}, function() {// 批注鼠标移出
												$(this).removeClass().addClass("postil");
												$(this).css("background", "yellow");
												$("div[forid='" + $(this).get(0).id+ "']").css("border-color","#ddd");
											});
									$(this).unbind('dblclick');
									$(this).dblclick(function() {
										$div = $("div[ctrtype='scfill'][forid='" + $(this).attr("id")+ "']").parent();
										$div.dblclick();
									});
								});
						}
					});
		} else {
			// alert("无法解析返回的信息！");
		}

	});
	
	
	
	$MARK_LIST.hide();
	$MARK_FILL_LIST.show();
	fillOldChange("fillList");
}
//填空列表和批注原内容的切换动作
function fillOldChange(changeId){
	$("#"+changeId).addClass("btn_nav1_click");
	$("#"+changeId).siblings().removeClass("btn_nav1_click");
}
// 绑定事件
function getFill(fillId, content, item, index) {
	var _descript= $("#keEditIframe").contents().find("span[id=" + fillId + "]").attr("title");
	if(_descript==undefined || _descript=="undefined" || _descript==null || _descript==""){
		//_descript="填空说明";
	}
	_descript+=":";
	//修改在合同内容编辑区域鼠标移动到填空区右侧无法定位对应的填空区域内容
	var _htmlBuf = new StringBuffer();
	_htmlBuf.append("<div style='width:100%;vertical-align:top;'>");
	_htmlBuf.append("<span style='color:#0693E3;'>【"+(index)+"】"+_descript+ "</span>" + content);
	
	//if($("#isCanInput").val() == '30' || $("#displayMarkBtn").val()!='false'){
	if($("#displayMarkBtn").val()=='true'){
	_htmlBuf.append("<span class='editDiv' style='float:right;position:relative;' onclick='editFill(this)'>&nbsp;</span>");
	}
	
	_htmlBuf.append("<div forid='" + fillId
			+ "' ctrType='scfill' style='width:95%; folat:left; overflow: visible; white-space:wrap;border:0px;'"
			+ " value='"+content+"'>");
	_htmlBuf.append("</div>");
	_htmlBuf.append("</div>");
	
	var $postil = $(_htmlBuf.toString());
	// 右侧原始批注数据鼠标移动事件
	$postil.click(function() {
		clearEditorCss();
		$(this).css("border-color", "red");

		$(_editor).contents().find("#" + $(this).find("div").attr("forid") + "").css("background", "Tomato");
		// 获取标注偏移量Y
		var y = GetObjPos($(_editor).contents().find("#"
				+ $(this).find("div").attr("forid") + "").get(0))["y"];
		if (y > 100) {
			y = y - 30;
		}
		$(_editor).contents().find("body").get(0).scrollTop = y;
	}, function() {
		$(this).css("border-color", "#ddd");

		$(_editor).contents().find("#" + $(this).attr("forid") + "").css(
				"background", "yellow");
	}).dblclick(function() {
		//alert($("#displayMarkBtn").val());
		//alert($("#isCanInput").val());
		//if($("#isCanInput").val() == '30' || $("#displayMarkBtn").val()!='false'){
		if($("#displayMarkBtn").val()=='true'){
			// 双击右侧填空列表信息可进行编辑
			$("div[ctrType=scfill]").attr("isDblclick", "false");
			$(this).find("div").attr("isDblclick", "true");
			//liwei3 del 和现有老卢提供的流程不符合,删除
			//是否为定标审批表中的数据，如果是则不可以直接双击编辑，需通过批注修改
		    //var isCanEdit=$(_editor).contents().find("#" + $(this).attr("forid") + "").attr("isCanEdit");
			//if($("#isOnlySee").val()!="1" && isCanEdit!="0"){//只有用相关负责人可以编辑填空信息
				editor.clickToolbar('datamarkinput');
			//}
		}
	});
	return $postil;
}

/**
 * liwei3
 * 点击填空列表里的编辑图标进入填空编辑栏 
 */
function editFill(arg){
	var $div = $(arg).parent();
	$div.dblclick();
}

/**
 * 填空列表 end
 */
function showBookMark() {
	mergeCont();
	$(_editor).contents().find("span[optype=bookmark]").css("background-color", "yellow");
	$("#markIdent").show();
}

/**
 * 打印
 */
function doScPrint() {
	var _url = _ctx + "/sc/sc-contract-templet-info!print.action?scContId="
			+ $("#scContractId").val() + "" + "&contractTempletHisId="
			+ $("#hisContId").val() + "";
	window.open(_url, 'print', _urltoolParam);
}
/**
 * 导出word
 */
function doExportDoc() {
	var _url = _ctx + "/sc/sc-contract-templet-info!exportDoc.action?scContId="
			+ $("#scContractId").val() + "" + "&contractTempletHisId="
			+ $("#hisContId").val() + "";
	window.open(_url, 'print', _urltoolParam);
}

/**
 * 获取文件扩展名
 * @param {}
 *            path 文件路径
 * @return {}
 */
function getFileExt(path){
    return path.substring(path.lastIndexOf(".")+1);
}

/**
 * 获取按钮
 */
var isHide_bt_attachUpload = false;	//是否要隐藏上传附件按钮（定表单走完后，新增文本库时，关闭上传附件按钮）
function getContButton(){
	//alert("001");
	$.post(_ctx + "/sc/sc-contract-templet-info!loadButton.action",
		{scContractId : $("#scContractId").val(),
		isOnlySee:$("#isOnlySee").val()
		}, function(result) {
			$("#showButton").html(result).find("[type='button']").css("height","26px").css("line-height","22px");
			if(isHide_bt_attachUpload){
				$("#bt_attachUpload").hide();
			}
			var statusCd = $("#statusCd").val();
			if(statusCd == undefined){
				statusCd=$("#conStatusCd").val();
			}
			//alert(statusCd);
			switch(statusCd){
				// 新增中
				case "10":
				// mergeCont();
					break;
				// 审核中
				case "20":
					reviseCont();
					break;
				// 修改中
				case "30":
					reviseCont();
					break;
				// 审批通过
				case "40":
					reviseCont();
					break;
					// 驳回
				case "45":
					//mergeCont();
					reviseCont();
					break;
				// 网批中
				case "50":
					//mergeCont();
					//showBookMark();
					reviseCont();
					break;
				// 网批结束
				case "60":
					//mergeCont();
					reviseCont();
					break;
				// 合同完成
				case "70":
					//mergeCont();
					reviseCont();
					break;
				// 合同签署
				case "80":
					//mergeCont();
					reviseCont();
					break;
				default:
				// / mergeCont();
					break;
			}
		}
	);
}
/**
 * 合同确定(合并)
 */
function sureMergeCont(){
	mergeCont();
	if ($("#isCanInput").val() == "40") {
		return;
	}
	TB_showMaskLayer("请稍等,正在合并合同中...");
	var _content = "" + editor.html();
	// 合并历史版本
	$.post(_ctx + "/sc/sc-contract-templet-info!mergeCon.action", {
				scContractId : $("#scContractId").val(),
				hisConId : $("#hisContId").val(),
				actType : "merge",
				statusCD : "40",
				content : _content
			}, function(result) {
				var _rJson = toJson(result);
				if (_rJson != undefined
						&& _rJson.status != undefined) {
					if (_rJson.status != true) {
						alert(_rJson.errorMsg);
					} else {
						TB_removeMaskLayer();
						ymPrompt.alert({message:"合并成功！",title:"提示",handler:function (tp){
							
						}});
						
						$("#isCanInput").val("40");
						return;
					}
				} else {
					ymPrompt.alert({message:"无法解析返回的信息！",title:"提示",handler:function (tp){
						
					}});
					
				}
				TB_removeMaskLayer();
			});
}

/**
 * 修订版
 */
function reviseCont(){
	$(".sc-content-right").show();
	$("#markTop").show();
	$("#markFill").show();
	// 清掉所有标记样式，如果存在的话
	clearMark();
	fillList();
	loader();
	$("#contDivLeft").attr("style",
			"width:60%; float: left;");
	$("#markDiv").show();
	$("#markIdent").hide();
	
	//alert("003");
	var highMarkId = $("#highMarkId").val();
	if(highMarkId!=null && highMarkId.length>0){
		highComment(highMarkId);
	}
}

/*
 * liwei3
 * 从合同审核表跳转过来时，进行批注定位
 */
function highComment(forid){
	
	//根据markid查找右侧的批注div
	var commentDiv = $("div[ctrtype='mark'][forid='"+forid+"']").parent("div");;
	//alert(forid);
	
	//把批注div的边框变红
	commentDiv.css("border-color", "red");
	
	//把左侧合同文本的批注内容变红
	var _edit = $("#keEditIframe");
	var _forid = commentDiv.find("div").attr("forid");
	$(_edit).contents().find("#" + _forid + "").css("background", "Tomato");
	$(_edit).contents().find("table[tableid=" + _forid+ "]").css("background", "Tomato");
	//获取批注在合同文本里向下的偏移量Y
	var y = GetObjPos($(_edit).contents().find("#"+ _forid + "").get(0))["y"];
	if (y > 100) {
		y = y - 30;
	}
	var contaner = $(_edit).contents().find("html,body");
	contaner.each(function() {
		$(this).animate({scrollTop : y}, 0);
	});

}


/**
 * 提交
 */
function doScSubmit(){
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
	ymPrompt.confirmInfo({message:'你确认要保存吗？',title:"合同保存",handler:function (tp){
		if (tp=='ok'){

			// 是否可以提交
			var _isCanSubmit = true;
			var _strBuf = new StringBuffer();
			_strBuf.append("[");
			$(_editor).contents().find("span[otype=min]").each(
					function(idx, input) {
						var _InputId = (new Date().getTime()) + "" + idx;
						if (idx > 0) {
							_strBuf.append(",");

						}
						var _inputText = "" + $(input).val();
						// 填空区域有未填写的，则不允许提交
						if (_inputText.trim() == "" && $(input).attr("isSkip") !="1") {
							_isCanSubmit = false;
							//return false;
						}
						_strBuf.append("{conFillContent:");
						_strBuf.append("\"" + $(input).val().trim() + "\"");
						_strBuf.append(",");
						_strBuf.append("conFillId:");
						_strBuf.append("\""
								+ ($(input).attr("id") != undefined
										? $(input).attr("id") + "\""
										: "\"\""));

						_strBuf.append("}");

					});
			_strBuf.append("]");
			if (!_isCanSubmit) {
				ymPrompt.confirmInfo({message:'数据未填写完毕，你确定要保存吗？',title:"保存确认",handler:function (tp){
					if (tp=='ok'){
						// 合并清楚格式
						mergeCont();
						var _content = "" + editor.html();
						$(".content").val(_content);
						$("#fillJson").val(_strBuf.toString());
						$("#conStatusCd").val("30");
						TB_showMaskLayer("请稍等,正在保存数据中...");
						$("#contForm").ajaxSubmit(function(result) {
							       result=""+result;
									var _rJson = toJson(result);
									if (_rJson != undefined
											&& _rJson.status != undefined) {
										if (_rJson.status != true) {
											alert(_rJson.errorMsg);
										} else {
											if(_rJson.ctLedgerMsg) {
											   var _ctLedgerJson=toJson("{"+_rJson.ctLedgerMsg.replace(/\'/ig,"\"")+"}");
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
											doClearParentTabResParamVal();
											ymPrompt.alert({
												message : "合同已保存！",
												title : "提示",
												handler : function(tp) {
												
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
													+ "/sc/sc-contract-templet-info!readContract.action?scContId="
													+ _rJson.scConId + "&resNo=" + resNo;
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
					}else{
						isSumitDoNext = true;
						doNext();
						return;
					}
				}});
				/*
				ymPrompt.alert({
					message : "数据未填写完毕，无法保存!",
					title : "提示",
					handler : function(tp) {
					isSumitDoNext = true;
					doNext();
				}});
				return;*/
			}else{
				// 合并清楚格式
				mergeCont();
				var _content = "" + editor.html();
				$(".content").val(_content);
				$("#fillJson").val(_strBuf.toString());
				$("#conStatusCd").val("30");
				TB_showMaskLayer("请稍等,正在保存数据中...");
				$("#contForm").ajaxSubmit(function(result) {
					       result=""+result;
							var _rJson = toJson(result);
							if (_rJson != undefined
									&& _rJson.status != undefined) {
								if (_rJson.status != true) {
									alert(_rJson.errorMsg);
								} else {
									if(_rJson.ctLedgerMsg) {
									   var _ctLedgerJson=toJson("{"+_rJson.ctLedgerMsg.replace(/\'/ig,"\"")+"}");
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
									doClearParentTabResParamVal();
									ymPrompt.alert({
										message : "合同已保存！",
										title : "提示",
										handler : function(tp) {
										
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
											+ "/sc/sc-contract-templet-info!readContract.action?scContId="
											+ _rJson.scConId + "&resNo=" + resNo;
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
			}
		
		}
	}});

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
	if(parent.TabUtils!=undefined){
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
 * 标注
 * 
 * @return
 */
function doMark(){
	editor.clickToolbar('mark');
}

/**
 * 显示历史流程
 * 
 * @return
 */
function showFlow(){
	var id =$('#scContractId').val();
	ymPrompt.win({
		message :_ctx+ "/sc/sc-contract-templet-info!getFlow.action?scContId="+id,
		titleBar:false,
		btn:[['关闭','close']],
		fixPosition: true,
		winPos: 'c',
		width : 600,
		height: 400,
		iframe: true
	});
}


/**
 * 更新合同状态
 */ 
function updateScStatus(scContractId,statusCd){
	ymPrompt.confirmInfo({message:'确定该操作？',title:"提示",handler:function (tp){
		if (tp=='ok'){
			TB_showMaskLayer("请稍等,正在提交数据中...");
			$.post(_ctx + "/sc/sc-contract-templet-info!updateStatus.action", {
						scContractId : scContractId,
						statusCd : statusCd
					}, function(result) {
						window.location.href = _ctx
						+ "/sc/sc-contract-templet-info!readContract.action?scContId="
						+ scContractId;
						TB_removeMaskLayer();
					});
		}
		ymPrompt.close();	
	}
	});
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
		//网批Tab是否打开
		var isOpen=(parent.TabUtils != undefined && parent.TabUtils.getTabFrame(event)[0] != undefined);
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
		if (isOpen) {
			// 设置活动Tab
			parent.TabUtils.showTab(event);
		}
		// 关联合同台账
		if (isOpen
				&& typeof(parent.TabUtils.getTabFrame(event)[0].contentWindow.autoQuickSearchCtledger) == "function") {
			// 使用tab打开
			parent.TabUtils.getTabFrame(event)[0].contentWindow
					.autoQuickSearchCtledger(conNo);

		} else if (window.opener != undefined
				&& typeof(window.opener.autoQuickSearchCtledger) == "function") { // 使用open打开
			window.opener.autoQuickSearchCtledger(conNo);
		}

		// 查询合同
		if (isOpen
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
	closeTab("scTemplet");

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
/**
 * 查看网批
 * @param resId 网批ID
 * @return
 */
function loadFlow(resId){
	var url = _ctx+"/res/res-approve-info.action?id="+resId;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("res-approve-info","网批明细",url,true);
}
/**
 *在合同文本库创建界面点击“更多。。。”或“收起”时执行方法
 * @return
 */
function showMsg(){
	$("#recuentInfo").hide();
	$("#msgInfo").animate({height: 'toggle', opacity: 'toggle'}, "slow");	
}

/**
 * 加载合同文本最近操作信息
 * @return
 */
function recurentlyConInfo(){
	var scContractId = $("#scContractId").val();
	if (scContractId != '') {
		var replyMsgId = $('#idReplyMsg').val();
		var data = {
			id : scContractId
		};
		data.scContractId = $("#scContractId").val();
		data.hisContId = $("#hisContId").val();
		$.post(_ctx + "/sc/sc-contract-templet-info!recurentlyConInfo.action",
				data, function(result) {
					$("#recuentInfo").html(result);
					if($("#msgInfo").css("display") == "none" && $(".sc-recuentInfo-body").css("display") == "none"){
						
						$(".sc-recuentInfo-body").show();
					}
				});
	}

}

/**
 * 加载用户留信息
 * 
 * @param {}
 *            content 信息内容
 */
function reloadContractMsg(content) {
	var scContractId = $("#scContractId").val();

	if (scContractId != '') {
		var replyMsgId = $('#idReplyMsg').val();
		var data = {
			id : scContractId,
			'referMsgId' : replyMsgId
		};
		data.scContractId = $("#scContractId").val();
		data.hisContId = $("#hisContId").val();
		if (!isEmpty(content)) {
			data.content = content;
		}

		$.post(_ctx + "/sc/sc-contract-templet-info!say.action", data,
				function(result) {
					$("#msgContent").html(result);
			          // 是否调整留言的区域名的高度
					if($("#isSetMsgHeight").val()=="1"){
						$("#conMsgDiv").css("height",($(document).height() - 360) + "px");					
					}

					var y = GetObjPos($("#conMsgDiv").get(0))["y"];
					if (y > 100) {
						y = y - 30;
					}
					var contaner =$("#conMsgDiv");
					contaner.each(function() {
								$(this).animate({
											scrollTop :$("#conMsgDiv").get(0).scrollHeight
										}, 0);
							});
					
			var _width=$("#conMsgDiv").width();
			
					/*recurentlyConInfo(); // 最近合同操作信息*/
					if(_width>40){
					// $("#pre").css("width",_width+"px");
					}
				});
		$("#comment").css("border", "0px solid red");
	}
}

/**
 * 加载历史记录
 * 
 * @param {}
 */
function reloadHisRecordVersion() {
	var scContractId = $("#scContractId").val();

	if (scContractId != '') {
		var data = {
			id : scContractId
		};
		data.scContId = $("#scContractId").val();

		$.post(_ctx + "/sc/sc-contract-templet-info!getFlow.action", data,
				function(result) {
					$("#hisRecordVersion").html(result);
				});

	}

}
/**
 * 加载附件
 * 
 * @param {}
 */
function reloadAttachs() {

	var scContractId = $("#scContractId").val();

	if (scContractId != '') {
		var replyMsgId = $('#idReplyMsg').val();
		var data = {
			id : scContractId
		};
		data.scContractId = $("#scContractId").val();
		data.hisContId = $("#hisContId").val();

		$.post(_ctx + "/sc/sc-contract-templet-info!loadAttachList.action",
				data, function(result) {
					$("#attachList").html(result);
				});

	}

}
/**
 * 删除附件 author:QLB
 * 
 * @param {}
 *            attachId
 */
function deleteAttachment(attachId) {
	// 定义参数集
	ymPrompt.parames={};
	// 保存参数
	ymPrompt.parames.attachId=attachId;
	ymPrompt.confirmInfo({message:'你确定要删除合同附件吗？',title:"删除附件",handler:function (tp){
		if (tp=='ok'){		
			var data={};
			data.attachmentId = ymPrompt.parames.attachId;

			$.post(_ctx + "/sc/sc-contract-templet-info!delAttachment.action", data,
					function(result) {
						
						var _rJson = toJson(result);
						if (_rJson != undefined && _rJson.status != undefined) {
							if (_rJson.status != true) {						
									alert(_rJson.errorMsg);				

							} else {
								reloadAttachs();
								/*recurentlyConInfo(); // 更新最近合同操作信息*/
								ymPrompt.alert({
									message : "附件已删除成功！",
									title : "提示",
									handler : function(tp) {
							
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
						
					
					});
		}}});
	
	

}
function showMsgDetail(){
	$("#recuentInfo").hide();
	showMsg();


}

function doCollapse(){

	showMsg();
	$("#recuentInfo").show();
	
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
function showUploadSingleAttachDialog(titileName, actType, para) {

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
				bizEntityName : 'scConAttachUpload' ,
				ifNoContLeger : $("#ifNoContLeger").val()
			};
			$.post(url, data, function(result) {
						$("#singleAttachDiv").html(result);
						$("#scAttachContId").val($("#scContractId").val());
						$("#scAttachHistContId").val($("#hisContId").val());						
						$("#sysTempletId").val($("#conTemletId").val());
						$("#isscstandard").val("1");

					});
		},
		handler : doSingleUpload,
		autoClose : false
	});
}
var canClick = true; // 可以点击确定按钮，true-可以点击; false-已经点击过确定，等待处理完成才可以再次点击
function doSingleUpload(btn) {

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
			
			if(isEmpty($("#attachTypeCd").val().trim())){
				alert("请选择附件类型！");
				return;
			}
			/*
			 * if (!isEmpty(fileName)) {
			 * 
			 * var ext = getFileExt(fileName).toLowerCase(); if (ext != "txt" &&
			 * ext != "text" && ext != "htm" && ext != "html") {
			 * alert("选择的模板格式不正确，请重新选择！\n允许的文件格式[.txt|.text|.htm|.html] ");
			 * return; } }
			 */
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
			result=""+result;
					if (result.indexOf("true") > -1) {
						
						var _rJson = toJson(result);
						if (_rJson != undefined && _rJson.status != undefined) {
							if($("#scContractId").val().trim()==""){
							$("#scContractId").val(_rJson.scConId);	
							$("#hisContId").val(_rJson.scHisContId);
							}
							/*reloadMsg(); // 更新留言记录*/
						}	
						/*recurentlyConInfo(); // 最近合同操作信息*/
						reloadAttachs();
						if($("#isAccomplishNeedUpload").val()=="1" && $(".ym-header-text").text().indexOf("扫描")>-1){
							
							alert("合同扫描件已上传成功！");
							$("#isSignatureUpload").val("1");
						}else{
							alert("文件上传成功！");
						}
						

					} else {
						alert(result);
					}
				});
	}
	// 取消
	else if (btn == 'cancel') {

	} else {

	}
	ymPrompt.close();
}



/**
 * 完成編輯
 */
function doAccomplish(){

	ymPrompt.confirmInfo({message:'你确定要编辑完成当前合同吗？',title:"完成合同",handler:function (tp){
		if (tp=='ok'){	
			mergeCont();
			
			TB_showMaskLayer("请稍等，正在提交数据....");
			var _content = "" + editor.html();
			// 合并历史版本
			$.post(_ctx + "/sc/sc-contract-templet-info!mergeCon.action", {
						scContractId : $("#scContractId").val(),
						hisConId : $("#hisContId").val(),
						actType : "merge",
						statusCD : "70",
						content : _content
					}, function(result) {
						var _rJson = toJson(result);
						if (_rJson != undefined
								&& _rJson.status != undefined) {
							if (_rJson.status != true) {
								alert(_rJson.errorMsg);
							} else {
								TB_removeMaskLayer();
								ymPrompt.alert({
									message : "操作成功！",
									title : "提示",
									handler : function(tp) {
									window.location.href = _ctx
									+ "/sc/sc-contract-templet-info!readContract.action?scContId="
									+ _rJson.scConId;
									}
								});
								return;
							}
						} else {
							
							ymPrompt.alert({
								message : "操作成功！",
								title : "提示",
								handler : function(tp) {

								}
							});
							
						}
						TB_removeMaskLayer();
					});		
			
		}}});	
}
/**
 * 合同签署之前上传附件
 * @param scContractId 合同ID
 * @param statusCd 合同状态
 * @return
 */
function doSignatureCon(scContractId,statusCd){
	
	if($("#isSignatureUpload").val()!="1"){
		showUploadSingleAttachDialog("请先上传合同扫描件！","upload", "") ;
		return;
	}
	
	updateScStatus(scContractId,statusCd);
}
/**
 * 查看关联的合同台账
 * @param id 合同台账ID
 * @return
 */
function getContLedger(id){
	if(null!=id && ""!=id && undefined!=id && "undefined"!=id){
		var url=_ctx+"/cont/cont-ledger!input.action?id="+id;
		if(parent.TabUtils==null)
			window.open(url);
		else
		  parent.TabUtils.newTab("cont-ledger-input","合同台账查看",url,true);
	}
}

/**
 * 查看网上审批
 * @param id 网批ID
 * @return
 */
function openSheet(id){
	//var url="${ctx}/res/res-approve-info!detail.action?id="+id+"&statusCd=2";	
	var url=_ctx+"/res/res-approve-info.action?id="+id+"&statusCd=2";	
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("156","网上审批",url,true);
	
}


/**
 * 去执定标网批中创建合同后回调方法
   *@param contLedgerNo 合同台账编号
   *@param contractPrice 合同总价
   *@param isSuccess true--合同台账导入成功  false--合同台账导入失败
 * @return
 */

function doExecResTabImportedEvent(ctLedgerNo,contractPrice,isSuccess) {
	//网批ID
	var framId ="156";
	var frame;
	var parentFram;
	var father = null;
	var event = {
			data : {
				tabId : framId,
				src : "",
				typeCd : 1
			}
		};
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
	// 执行网批中
	if (parent.TabUtils != undefined
			&& typeof(parent.TabUtils.getTabFrame(event)[0].contentWindow.doContImported) == "function") {
		// 使用tab打开
		parent.TabUtils.getTabFrame(event)[0].contentWindow
				.doContImported(ctLedgerNo,contractPrice,isSuccess);

	} else if (window.opener != undefined
			&& typeof(window.opener.doContImported) == "function") { // 使用open打开
		window.opener.doContImported(ctLedgerNo,contractPrice,isSuccess);
	}
}

//显示批注的提示
function doShowTip(){
	ymPrompt.win({message:'sc-contract-templet-info!showTip.action',width:600,height:500,title:'批注修改的操作提示',maxBtn:true,minBtn:true,iframe:true})
}
