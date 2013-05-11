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

function toDecimal(x) {
	var f = parseFloat(x);
	if (isNaN(f)) {
		return false;
	}
	var f = Math.round(x * 100) / 100;
	var s = f.toString();
	var rs = s.indexOf('.');
	if (rs < 0) {
		rs = s.length;
		s += '.';
	}
	while (s.length <= rs + 2) {
		s += '0';
	}
	return s;
}
// 转化为JSON
function toJson(str) {
	if ("" == $.trim(str)) {
		return "";
	}
	return eval("(" + str + ")");
}

var _isShow = true;
// 隐藏错误信息
function hideErrorMsg() {
	window.setTimeout(function() {
				if (_isShow) {
					$("#errMsg").toggle(1000);
					_isShow = false;
				} else {
					$("#errMsg").hide();
				}

			}, 3000);

}
function showMsgInfo(msg) {
	$("#errMsg").html(msg);
	$("#errMsg").show();
	_isShow = true;
	hideErrorMsg();

}

// 判断是否为数字
function isNum(txt) {
	var _val = $.trim(txt);
	if ("" == _val || _val.length < 1) {

		return true;
	}

	var reg = /^(([0-9]+.?[0-9]*)|(\d*[0-9]+))$/; // /^((\d*[1-9]*)|([0]))(\.\d+)?$/;
	var _isN = reg.test(_val);
	// alert('isNum' + _val + " " + txt+" _isN:"+_isN);
	return _isN;
}
// 判断是否为空
function isEmptyOrNull(va) {

	var _val = $.trim(va);

	if ("" == _val || _val.length < 1 || _val == null) {

		return true;
	}

	return false;
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
 * 是否绑定事件
 */
function isBindEvent() {
	var isBind = false;
	var _status = $("#ctLedgerStatus").val();
	// 如果台账信息为空、0、3、4则说明允用户再次进次修改台账信息
	if ((isEmptyOrNull(_status) || _status == "0" || _status == "3"
			|| _status == "7" || _status == "4" ) 
			&& $("#divButton").html()!=null) {

		isBind = true;
	}else{
		
		//$("#divButton").html() 为null   则说明当前用户没有目标成本分解权限
	}
	return isBind;
}
$("document").ready(function() {
	$("#operationLoading").hide();
	$("#landLoading").hide();
	//
	$("#divButton").hide();
	// 隐藏取消按钮
	$("#divButtonCenter").hide();
	if (isBindEvent()) {
		loadLands();
		// 绑定事件
		bindEvent();
		$("#reviewBtnDiv").hide();
		$("#modificationBtnDiv").hide();
	}
	var _status = $("#ctLedgerStatus").val();
	// 如果台账信息为空、0、3、4则说明允用户再次进次修改台账信息
	if (isEmptyOrNull(_status) || _status == "0" || _status == "3"
			|| _status == "4" || _status == "7"  ) {

		$("#reviewBtnDiv").hide();
		$("#modificationBtnDiv").hide();
	}

	isHasBuildCostItem();
	bindReviewEvent();
	if ($("tr[ctr=remark]").length < 1) {
		// 如果没有历史调整记录则隐藏按钮
		$("#btn_seeHisAdjustRecord").hide();
	} else {
		$("#btn_seeHisAdjustRecord").bind("click", function() {
					doSeeHisAdjustRecord();
				});

	}

});
/**
 * 载入已生成的成本科目
 * 
 * @return""
 */
function loadCostItem() {
	TB_showMaskLayer("请稍等,正在初始化目标成本...");

	var _ctLederid = $("#ctLdId").val();
	var _url = _ctx + "/ct/ct-land!getLandAndOperationInfo.action";
	$.ajax({
				url : _url,
				type : "POST",
				async : true,
				data : "ctLederid=" + _ctLederid,
				success : function(responseText) {
					TB_removeMaskLayer();
					if (typeof(responseText) == "object") {// 返回的为JSON数据
						buildCostItemDetails(responseText);// 构建目标成本

					} else {
						var _landJson = toJson(responseText);

						if (_landJson != undefined
								&& _landJson.status != undefined) {

							if (_landJson.status != true) {
								showMsgInfo(_landJson.errorMsg);
								return;
							}
						} else {
							buildCostItemDetails(_landJson);// 构建目标成本
						}

					}

					/*
					 * if (isBindEvent()) { $("#divButton").show(); }
					 */
				},
				error : function(responseText) {
					TB_removeMaskLayer();
					if (isBindEvent()) {
						alert("error" + responseText);
						$("#divButton").show();
					}
				}
			});

}

/**
 * 是否已生成成本科目
 * 
 * @return
 */
function isHasBuildCostItem() {
	var _ctLederid = $("#ctLdId").val();
	var _url = _ctx + "/ct/ct-land!getCostItemSatus.action";

	$.post(_url, {
				ctLederid : _ctLederid
			}, function(result) {

				var _landJson = toJson(result);

				if (_landJson != undefined && _landJson.status != undefined) {

					if (_landJson.status != true) {
						showMsgInfo(_landJson.errorMsg);
						return;
					} else {
						if (_landJson.isHas == 1) {
							$("#isHasBuild").val("true");
							loadCostItem();

						}

					}
				} else {
					alert(responseText);
				}

			});

}
/**
 * 载入地块信息
 * 
 * @return
 */

function loadLands() {
	var _ctLederid = $("#ctLdId").val();
	var _url = _ctx + "/ct/ct-land!list.action";

	$.post(_url, {
				ctLederId : _ctLederid
			}, function(result) {

				var _jsonData = toJson(result);
				if (_jsonData != undefined && _jsonData.status != undefined) {

					if (_jsonData.status != true) {
						showMsgInfo(_jsonData.errorMsg);
						return;
					}
				} else {
					buildLands(_jsonData);// 加载地块
				}
			});

}
/**
 * 绑定审核功能DIV按钮中的事件
 */

function bindReviewEvent() {
	// 审核
	$("#btn_approve").bind("click", function() {

				$.messager.confirm('提示', "你确认要审核通过当前台账信息吗？", function(t) {

							if (t) {
								executeReview(2, "")
							}

						});

			});
	// 驳回
	$("#btn_overrule").bind("click", function() {

				$.messager.confirm('提示', "你确认要驳回当前台账信息吗？", function(t) {

							if (t) {

								var _status = $("#ctLedgerStatus").val();
								if (_status == "9") {
									executeReview(7, "")
								} else {
									executeReview(3, "")
								}

							}

						});

			});

	// 调整
	$("#btn_modification").bind("click", function() {
				$.messager.confirm('提示', "你确认要调整当前台账信息吗？", function(t) {

							if (t) {
								executeReview(4, "")
							}

						});
			});
}
/**
 * 执行审核操作
 * 
 * @param {}
 *            status 状态：2通过，3驳回，4变更
 * @param {}
 *            remark 描述
 */
function executeReview(status, remark) {

	var _ctLederid = $("#ctLdId").val();
	var _url = _ctx + "/ct/ct-ledger!approveLedger.action";

	switch (status) {
		case 1 :// 提交
		case 9 :// 变更提交
			_url = _ctx + "/ct/ct-ledger!modificationLegder.action";

			break;
		case 2 :
			_url = _ctx + "/ct/ct-ledger!approveLedger.action";
			break;
		case 3 :
		case 7 :// 变更驳回
			_url = _ctx + "/ct/ct-ledger!overruleLedger.action";
			break;
		case 4 :
			_url = _ctx + "/ct/ct-ledger!modificationLegder.action";

			break;
		default :

	}
	$.post(_url, {
				ctLedgerId : _ctLederid,
				status : status,
				modRemark : remark
			}, function(result) {

				var _jsonData = toJson(result);
				if (_jsonData != undefined && _jsonData.status != undefined) {
					if (_jsonData.status == true) {
						$.messager.alert('提示信息', '已操作成功', 'info');
						window.location.reload();
					} else {
						$.messager.alert('提示信息', _jsonData.errorMsg, 'info');
					}

				}
			});

}
// 绑定事件
function bindEvent() {
	unbindEvent();
	// bindItemTreeGridEvent();
	// 地块新增按钮事件
	$("#btAddLand").bind("click", function() {
				doAddLand();
			});
	// 业态新增按键事件
	$("#btAddOper").bind("click", function() {
				doAddOperation();

			});
	// 增加科目
	$("#btn_addItem").bind("click", function() {

				ctAddPlanItem();
			});
	// 删除科目
	$("#btn_delItem").bind("click", function() {
				ctDelCostItem();
			});
	// 修改科目
	$("#btn_modItem").bind("click", function() {
				ctModCostItem();
			});
	// 科目升序
	$("#btn_AscItem").bind("click", function() {
				ctDoSortItem(true);
			});
	// 科目降序
	$("#btn_DescItem").bind("click", function() {
				ctDoSortItem(false);
			});
	// 提交科目并生成新版号
	$("#btn_publishNewVerison").bind("click", function() {
				ctPublishNewVersion();
			});
	// 取消分解
	$("#btn_cancelItem").unbind("click");
	// 取消分解
	$("#btn_cancelItem").bind("click", function() {

				// 分解目标成本
				$("#btn_dismantleCost").unbind("click");
				unbindEvent();
				bindEvent();// 绑定相关事件
				$("#divButtonLeft").show();
				$("#divButtonCenter").hide();
				$("#btn_dismantleCost").val("分解目标成本");

				$("#btn_dismantleCost").attr("isShowCheck", "false");
				$("label[ctr=lb_treeCk]").hide();

				$("label[ctr=lb_treeCk]").removeClass();
				$("label[ctr=lb_treeCk]").attr("checked", "false");
				$("label[ctr=lb_treeCk]")
						.addClass("tree-checkbox tree-checkbox0");

			});
	// 分解目标成本
	$("#btn_dismantleCost").bind("click", function() {
		// 分解目成本時解除相应控件事件
		unbindEvent();
		$("#divButtonCenter").show();
		$("#divButtonLeft").hide();
		if ($(this).val() == "分解目标成本") {
			$(this).attr("isShowCheck", "true");
			$(this).val("确定");

			$("label[conPlanId=]").show();

		} else {
			var _json = doDismantleCost();// 返回String类型选中科目与地块信息
			$(this).attr("isShowCheck", "false");
			if (!isEmptyOrNull(_json)) {

				$("#btn_dismantleCost").attr("isShowCheck", "false");
				$("label[ctr=lb_treeCk]").hide();

				$("label[ctr=lb_treeCk]").removeClass();
				$("label[ctr=lb_treeCk]")
						.addClass("tree-checkbox tree-checkbox0");
				$(this).val("分解目标成本");
				$("label[ctr=lb_treeCk]").attr("checked", "false");
				doShowDismantleDetails(_json);
			}

		}

	});
	// 地块回车事件
	$("#landName").keypress(function(e) { // 键盘事件
	
				if (e.which == 13)
					doAddLand();
			});
	// 业态回车事件
	$("#operTypeName").keypress(function(e) {
				if (e.which == 13)
					doAddOperation();
			});

}

// 解除绑定事件
function unbindEvent() {
	// 地块新增按钮事件
	$("#btAddLand").unbind("click");
	// 业态新增按键事件
	$("#btAddOper").unbind("click");
	// 增加科目
	$("#btn_addItem").unbind("click");
	// 删除科目
	$("#btn_delItem").unbind("click");
	// 修改科目
	$("#btn_modItem").unbind("click");
	// 科目升序
	$("#btn_AscItem").unbind("click");
	// 科目降序
	$("#btn_DescItem").unbind("click");
	// 提交科目并生成新版号
	$("#btn_publishNewVerison").unbind("click");
	// 取消分解
	// $("#btn_cancelItem").unbind("click");
	// 分解目标成本
	// $("#btn_dismantleCost").unbind("click");
	// 地块回车事件
	$("#landName").unbind("keypress");
	// 业态回车事件
	$("#operTypeName").unbind("keypress");
	// 目标成本点击事件
	$("td[field=targetAmt]").unbind("click");
	// 目标成本编辑
	$("input[ctr=editor]").unbind("blur");
}
/**
 * 移动科目时设置排序按钮状态
 * 
 * @param {}
 *            isEnabled(true or false)
 */
function setBtSortStatus(isEnabled) {
	// 科目升序
	$("#btn_AscItem").attr("disabeld", isEnabled);
	// 科目降序
	$("#btn_DescItem").attr("disabeld", isEnabled);
}
/**
 * 提交科目并生成新的版本号
 */
function ctPublishNewVersion() {
	var _url = _ctx + '/ct/ct-item!getItemAndConNoRelationInfo.action';
	var _isContinue = true;
	$.ajax({
				url : _url,
				type : 'POST',
				data : {
					ctLedgerId : $("#ctLdId").val(),
					recordVerison : $("#ctVersion").val()
				},
				async : false, // ajax同步
				timeout : 1000,

				error : function() {
					alert('Error loading PHP document');
				},

				success : function(responseText) {
					var _results = toJson(responseText);
					if (typeof(_results) == "object") {

						if (typeof(_results[0].items) != null
								&& typeof(_results[0].items) != "undefined") {
							var _msg = new StringBuffer();
							var _isMoreMany = false;
							_msg.append("有科目");
							if (_results[0].contPlans[0].length > 0) {
								if (_results[0].items[0].length > 0) {
									_msg.append("、");
								}
								_msg.append("或合约规划");

							}
							_msg.append(" 未进行关联\n提交后将不能更改，你确定要提交吗？");
							/*
							 * if (_results[0].items[0].length > 15) {
							 * 
							 * _isMoreMany = true;
							 * _msg.append("\n由于科目比较多在此只显示一部份:"); }
							 * _msg.append("\n"); $.each(_results[0].items[0],
							 * function(i, item) { if (_isMoreMany && i > 15) {
							 * return true; } _msg.append("地块：");
							 * _msg.append(item[0]); _msg.append("|");
							 * _msg.append("业态："); _msg.append(item[1]);
							 * _msg.append("|"); _msg.append("科目名称：");
							 * _msg.append(item[3]); _msg.append("\n");
							 * 
							 * });
							 * 
							 * if (_results[0].contPlans[0].length > 0) {
							 * 
							 * _msg.append("\n---------未被关联的合约规则---------\n");
							 * $.each(_results[0].contPlans[0], function(i, con) {
							 * if (i > 10) {
							 * 
							 * _msg.append(".............\n"); return true; }
							 * _msg.append("合约规则名称："); _msg.append(con[1]);
							 * 
							 * _msg.append("\n\t"); }); }
							 */
							if (!confirm(_msg.toString())) {
								_isContinue = false;
								return;
							}

						}
					}

				}

			});
	if (!_isContinue) {// 点击取消
		return;
	}

	var _status = $("#ctLedgerStatus").val();
	// 如果是变更
	if (!isEmptyOrNull(_status) && (_status == "4" || _status == "7")) {
		// 当前是变更提交
		ymPrompt.confirmInfo({
			icoCls : "",
			autoClose : false,
			message : "<div id='modRemarkDiv'><img align='absMiddle' src='"
					+ _ctx + "/images/loading.gif'></div>",
			width : 350,
			height : 250,
			title : "变更说明",
			closeBtn : true,
			afterShow : function() {
				var _url = _ctx + "/ct/ct-item!modificationInput.action";

				$.post(_url, {}, function(result) {
							$("#modRemarkDiv").html(result);

						});
			},
			handler : function(btn) {

				if (btn == 'ok') {
					var _modRemark = $("#modRemark").val().trim();

					if (isEmptyOrNull(_modRemark)) {
						alert("变更描述不能为空！");
						$("#modRemark").val("");
						return;
					}
					if (isPassMaxLen(_modRemark, 500)) {
						alert("变更描述不能超过500个字符\n\t已超出了["
								+ eval(getByteLen(_modRemark) - 500) + "]个字符！");
						return;
					}
					$.messager.confirm('提示', "当前台账信息提交后将不能被修改<br/>你确认要这样做吗？",
							function(t) {
								if (t) {
									var _modRemark = $("#modRemark").val()
											.trim();
									// 9，变更后提交
									executeReview(9, _modRemark);
								}
							});

				}
				ymPrompt.close();
			},
			btn : [["确定", 'ok'], ["取消", 'cancel']]
		});

	} else {// 提交
		$.messager.confirm('提示', "当前台账信息提交后将不能被修改<br/>你确认要这样做吗？", function(t) {

					if (t) {
						executePublishNewVersion();
					}

				});

	}
}

function executePublishNewVersion() {
	var _url = _ctx + '/ct/ct-item!doPublishNewVersion.action';
	$.post(_url, {
				ctLedgerId : $("#ctLdId").val(),
				isPubNewVersion : "false",
				recordVerison : $("#ctVersion").val()
			}, function(result) {

				var _jsonData = toJson(result);

				if (_jsonData != undefined && _jsonData.status != undefined) {

					if (_jsonData.status != true) {
						$.messager.alert('提示信息', _jsonData.errorMsg, 'info');
						return;
					} else {
						$.messager.alert('提示信息', '当前台账信息已提交！', 'info');
						window.location.reload();
					}

				}

			});

}
/**
 * 分解目标成本
 * 
 * @return { 所有选中待分解的科目}
 */
function doDismantleCost() {
	var _jsonData = "";
	var _jsonCount = 0;
	var _jsonNoCount = 0;
	var _jsonBuffer = new StringBuffer();
	_jsonBuffer.append("[");

	var _landId = "";
	var _operaitonId = "";
	var _noTargetAmtBuffer = new StringBuffer();
	_noTargetAmtBuffer.append("[");
	var _isParentSelected = false;// 是否父亲节点已选择
	var _amtsum = 0;
	$.each($("label[ctr=lb_treeCk]"), function(i, item) {
				_isParentSelected = false;
				_pid = $(item).attr("pid");
				if (!isEmptyOrNull(_pid)) {
					if ($("#" + _pid).attr("checked") == "true") {
						_isParentSelected = true
					}
				}
				if ($(item).attr("checked") == "true") {

					if (_jsonCount > 0) {

						_jsonBuffer.append(",");
					}
					_landId = $(item).attr("landid");
					_operaitonId = $(item).attr("operationid");

					var _jKey = new Array();
					var _jVal = new Array();

					_jKey.push("landName");
					_jVal.push($("th[landid=" + _landId + "]").text());
					_jKey.push("operName");
					_jVal.push($("label[operid=" + _operaitonId + "]").text());
					_jKey.push("itemName");
					_jVal.push($(item).attr("title"));

					var _amt = 0;
					if (!isEmptyOrNull($(item).attr("targetAmt"))
							&& parseFloat($(item).attr("targetAmt")) > 0) {
						if (!_isParentSelected) {// 如果上级科目已被选中，则不记录目标节点值
							_amt = $(item).attr("targetAmt");
						}

					} else {
						if (_jsonNoCount > 0) {
							_noTargetAmtBuffer.append(",");
						}
						// 记录值为空或为0的科目
						_noTargetAmtBuffer.append(jsonBuilder(_jKey, _jVal));
						_jsonNoCount++;
					}
					_jKey.push("targetAmt");
					_jVal.push(_amt);
					_amtsum += parseFloat("" + _amt);
					_jKey.push("ctLedgerId");
					_jVal.push($("#ctLdId").val());

					_jKey.push("ctLandId");
					_jVal.push($("th[landid=" + _landId + "]").text());

					_jKey.push("ctOperationId");
					_jVal.push(_operaitonId);

					_jKey.push("ctItemId");
					_jVal.push($(item).attr("id"));
					_jKey.push("parentId");
					_jVal.push($(item).attr("pid"));

					_jsonBuffer.append(jsonBuilder(_jKey, _jVal));
					_jsonCount++;

				}

			});
	_jsonBuffer.append("]");
	_noTargetAmtBuffer.append("]");

	if (_amtsum == 0) {
		$.messager.alert('提示信息', '你所分解的科目目标成本金额总和不能为空！', 'info');

		return;
	}
	if (_jsonCount < 1) {
		$.messager.alert('提示信息', '无法分解，请确认你当前是否选择了科目<br/>或者所有科目已被分解！', 'info');

		return;
	}
	var _noValJson = toJson(_noTargetAmtBuffer.toString());

	var _isAlert = false;
	var _msg = new StringBuffer();
	_msg.append("选择的科目成本金额包含了空值或值为0,是否继续分解？\n");
	var _isContinue = true;

	if (_noValJson.length > 0) {
		_isAlert = true;
	}

	/*
	 * if (_noValJson.length > 20) { _isContinue = false;
	 * _msg.append("由于科目过多在此将只显示一部份：\n"); } $.each(_noValJson, function(i, json) {
	 * _isAlert = true; if (!_isContinue && i > 20) {
	 * 
	 * return; } _msg.append("地块："); _msg.append(json.landName);
	 * _msg.append("|"); _msg.append("业态："); _msg.append(json.operName);
	 * _msg.append("|"); _msg.append("科目名称："); _msg.append(json.itemName);
	 * _msg.append("\n");
	 * 
	 * }); if (!_isContinue) { _msg.append("............................\n"); }
	 */
	if (_isAlert) {

		if (!confirm(_msg)) {
			return "";
		}

	}
	return _jsonBuffer.toString();;

}

/**
 * auth: qlb 拼接JSON数据 params: arrKey jsonKey数组, arrVal json 数据数组
 */
function jsonBuilder(arrKey, arrVal) {
	var _returnString = "";
	var _j = arrKey.length;
	var _jsonBuff = new StringBuffer();
	_jsonBuff.append("{");
	for (i = 0; i < _j; i++) {
		if (i > 0) {
			_jsonBuff.append(",");
		}
		_jsonBuff.append(arrKey[i]);
		_jsonBuff.append(":\"");
		_jsonBuff.append(arrVal[i]);
		_jsonBuff.append("\"");

	}
	_jsonBuff.append("}");
	return _jsonBuff.toString();
}
/**
 * 显示科目分解明细
 * 
 * @param {}
 *            jsonDatas 科目JSon
 */
function doShowDismantleDetails(jsonData) {

	var _allFirstLevelItems = $("label[pid='']");
	var _allFirstTotalAmt = 0.0;
	$.each(_allFirstLevelItems, function(i, item) {

				var _subTargetAmt = $.trim($("input[hideId="
						+ $(item).attr("id") + "]").val());

				if (!isEmptyOrNull(_subTargetAmt) && "0" != _subTargetAmt
						&& isNum($.trim(_subTargetAmt))) {

					_allFirstTotalAmt += parseFloat($.trim(_subTargetAmt));;
				}
			});

	var _totalAmt = $.trim("" + $("#ctCostTargetTotalAmt").text()); // 总的目标金额

	if (isEmptyOrNull(_totalAmt)) {
		_totalAmt = "0";

	}

	ymPrompt.confirmInfo({
				icoCls : "",
				autoClose : false,
				message : "<div id='itemPlanConn'><img align='absMiddle' src='"
						+ _ctx + "/images/loading.gif'></div>",
				width : 510,
				height : 460,
				title : "分解目标成本",
				closeBtn : true,
				afterShow : function() {

					var url = _ctx + "/ct/ct-cont-plan!itemPlanConn.action";
					$.post(url, {
								jsonData : jsonData,
								ctLedgerId : $("#ctLdId").val()
							}, function(result) {
								$("#itemPlanConn").html(result);
								// $("#canDismantleAmt").html((parseFloat(_totalAmt)
							// - _allFirstTotalAmt));
						});
				},
				handler : function(btn) {
					if (btn == 'ok') {
						var _changeAmt = $('#changeAmt').val().trim();

						var _totalAmt = $('#totalAmt').val().trim();
						var _contPlan = $("#ctContPlanSelect").val().trim()
						if (isEmptyOrNull(_contPlan)) {
							alert('未选择关联的合约规划！');
							return;
						}

						if (_changeAmt != "" && _changeAmt != null
								&& Number(_totalAmt) >= Number(_changeAmt)
								&& Number(_changeAmt) != 0) {
							$('#saveItemPlanConn').submit();
						} else {
							alert('调整金额必须大于0且小于等于最高金额！');
							return false;
						}
					}
					ymPrompt.close();
				},
				btn : [["确定", 'ok'], ["取消", 'cancel']]
			});
}

/**
 * 增加地块
 * 
 * @return
 */
function doAddLand() {
	var _actType = "add";
	$("#landName").val($.trim($("#landName").val().trim()));
	var _landName = $("#landName").val();

	if (isEmptyOrNull(_landName)) {
		$("#landName").focus();
		showMsgInfo("地块名称不能为空！");
		return;
	} else {
		if (isPassMaxLen(_landName, 100)) {
			showMsgInfo("地块名称不能超过100个字符<br/>超出了["
					+ eval(getByteLen(_landName) - 100) + "]个字符！");
			return;
		}

		if ($("#btAddLand").val() == "修改") {
			_actType = "mod";
		}

		var _isExsit = false;
		$.each($("li[ctr=lands]"), function(i, singleLand) {
					if ($(singleLand).attr("title").toLowerCase() == $
							.trim(_landName).toLowerCase()) {

						_isExsit = true;
					}

				});
		if (_isExsit) {
			showMsgInfo("此地块名称已存在，请换一个！");
			return;
		}
		$("#landLoading").show();
		$("#landDiv").hide();
		var _ctLederid = $("#ctLdId").val();
		var _landId = $("li[isLandSelected=1]").attr("id");
		var _url = _ctx + "/ct/ct-land!save.action";

		var _dataText = new StringBuffer();
		_dataText.append("landName=" + _landName);;
		_dataText.append("&ctLederId=" + _ctLederid);
		_dataText.append("&actType=" + _actType);
		_dataText.append("&landId=" + _landId);

		$.post(_url, {
					landName : _landName,
					ctLederId : _ctLederid,
					actType : _actType,
					landId : _landId
				}, function(result) {

					var _jsonData = toJson(result);

					if (_jsonData != undefined && _jsonData.status != undefined) {

						if (_jsonData.status != true) {
							showMsgInfo(_jsonData.errorMsg);
							return;
						}
						showMsgInfo("操作成功");
					} else {
						$("#landName").val("");
						$("#btAddLand").val("新增");
						if (_actType == "mod") {
							$("#" + _landId).attr("title", _landName);
							$("div[pLiId=" + _landId + "]").html(_landName);
						} else {
							var _curSelectedLandId = $("li[isLandSelected=1]")
									.attr("id");
							buildLands(_jsonData);// 加载地块
							if (!isEmptyOrNull(_curSelectedLandId)) {
								$("#" + _curSelectedLandId)
										.addClass("ctslt-nav-click");
								$("#" + _curSelectedLandId).attr(
										"isLandSelected", "1");
							}
						}

					}
					$("#landLoading").hide();
					$("#landDiv").show();

				});

	}
}

/**
 * 构建地块数据
 * 
 * @param jsonData
 *            地块JSON数据
 * @return “”
 */
function buildLands(jsonData) {
	var lands = $("#landDiv");

	var _htmlText = new StringBuffer();
	var _landId = "";// 地块编号
	var _landName = "";// 地块名称
	$.each(jsonData, function(i, land) {
				_landId = land.ctLandId;
				_landName = land.landName;
				_htmlText.append("<li id='" + _landId + "'");
				_htmlText.append("title='" + _landName);
				_htmlText.append("' ctr='lands' isLandSelected='0' >");
				_htmlText.append("<div  style='text-align:left;' >");
				_htmlText.append("<div class='Div_full'");
				_htmlText.append("pLiId='" + _landId + "'");
				_htmlText.append("ctr='divLands'>");
				_htmlText.append(_landName);
				_htmlText.append("</div>");
				_htmlText.append("<div style='float:left;display:none;'");
				_htmlText.append("ctr='delLand' lid='" + _landId + "'");
				_htmlText.append("class='btDel'>");
				_htmlText.append("&nbsp;&nbsp;&nbsp;");
				_htmlText.append("</div>");
				_htmlText.append("</div></li>");

			});
	lands.html(_htmlText.toString());
	bindLandsEvent();// 绑定地块信息事件

}
/**
 * * 绑定地块信息事件
 * 
 * @return ""
 */
function bindLandsEvent() {

	$("div[ctr=divLands]").bind("click", function() {
				$("#txtlandId").val("");
				$("#landName").val("");
				$("#btAddLand").val("新增");
				$("#txtlandId").val($(this).attr("pLiId"));
				$("li[ctr=lands]").removeClass();
				$("li[ctr=lands]").attr("isLandSelected", "0");
				var pLI = $("#" + $(this).attr("pLiId"));
				$(pLI).addClass("ctslt-nav-click");
				$(pLI).attr("isLandSelected", "1");

				var ctLandid = $("li[isLandSelected=1]").attr("id");
				$("div[ctr=operation]").show();
				var ulOperId = "ul" + ctLandid;
				if ($("#" + ulOperId).length > 0) {
					$("ul[ctr=ul]").hide();
					$("#" + ulOperId).show();
					return;
				}
				loadOperations(ctLandid);

			}).bind("dblclick", function() {

				$("#landName").val($(this).html());
				$("#btAddLand").val("修改");
				$("#operationId").val($(this).attr(""));
			});

	$("div[ctr=delLand]").click(function() {
		var _landName = $("#" + $(this).attr("lid")).attr("title");
		var _landId = $(this).attr("lid");
		$.messager.confirm('地块删除提示', '你确认要删除【' + _landName + '】吗？',
				function(t) {
					if (t) {
						// 执行删除
						delLand(_landId);
					}
				});

	});

	$('li[ctr=lands]').hover(function() {

				$("div[lid=" + $(this).attr("id") + "]").show();
			}, function() {
				$("div[lid=" + $(this).attr("id") + "]").hide();
			});

}

/**
 * 删除地块
 * 
 * @param landId
 *            地块编号
 * @return
 */
function delLand(landId) {

	var _ctLederid = $("#ctLdId").val();
	var _url = _ctx + "/ct/ct-land!delete.action";
	$.ajax({
				url : _url,
				type : "POST",
				data : "ctLandid=" + landId + "&ctLederid=" + _ctLederid,
				success : function(responseText) {
					var _jsonData = toJson(responseText);

					if (_jsonData != undefined && _jsonData.status != undefined) {

						if (_jsonData.status != true) {
							showMsgInfo(_jsonData.errorMsg);
							return;
						}
						if ($("#txtlandId").val() == landId) {// 如果当前编辑的地块不存在则更改更新按钮
							$("#landName").val("");
							$("#btAddLand").val("新增");
							$("div[ctr=operation]").hide();
						}

						var ulOperId = "ul" + landId;
						$("#" + ulOperId).remove();
						$("#" + landId).remove();

						showMsgInfo("地块删除成功！");
					}

				},
				error : function(responseText) {
					showMsgInfo(responseText);
				}
			});
}

/**
 * 点击增加地块所对应的业态
 * 
 * @return""
 */
function doAddOperation() {

	if ($("li[isLandSelected=1]").length < 1) {
		return;
	}
	var _actType = "add";
	$("#operTypeName").val($.trim($("#operTypeName").val().trim()));
	var _operTypeName = $("#operTypeName").val();
	if (isEmptyOrNull($("#operTypeName").val())) {
		$("#operTypeName").focus();
		showMsgInfo("业态名称不能为空！");
		return;
	} else {

		if (isPassMaxLen(_operTypeName, 50)) {
			showMsgInfo("地块名称不能超过50个字符<br/>超出了["
					+ eval(getByteLen(_operTypeName) - 50) + "]个字符！");
			return;
		}

		if ($("#btAddOper").val() == "修改") {
			_actType = "mod";
		}

		var _isExsit = false;
		var _ctLandid = $("li[isLandSelected=1]").attr("id");
		$.each($("li[ctr=opers]"), function(i, _singleOper) {

					if ($(_singleOper).attr("title").toLowerCase() == $
							.trim(_operTypeName).toLowerCase()
							&& $(_singleOper).attr("lid") == _ctLandid) {
						_isExsit = true;
					}

				});
		if (_isExsit) {
			showMsgInfo("此业态名称已存在，请换一个！");
			return;
		}

		var _operationId = $("#operationId").val();
		$("#operDiv").hide();
		$("#operationLoading").show();

		var _url = _ctx + "/ct/ct-operation!addOper.action";
		var _dataText = new StringBuffer();
		_dataText.append("operTypeName=" + $.trim(_operTypeName));
		_dataText.append("&ctLandid=" + _ctLandid);
		_dataText.append("&actType=" + _actType);
		_dataText.append("&operationId=" + _operationId);

		$.ajax({
					url : _url,
					type : "POST",
					data : _dataText.toString(),
					success : function(responseText) {

						var _jsonData = toJson(responseText);

						if (_jsonData != undefined
								&& _jsonData.status != undefined) {

							if (_jsonData.status != true) {
								showMsgInfo(_jsonData.errorMsg);
								return;
							}
							if (_actType == "mod") {
								$("#operTypeName").val("");
								$("#btAddOper").val("新增");
								$("#operationId").val("");
								$("#" + operationId)
										.attr("title", operTypeName);
								$("div[pLiId=" + operationId + "]")
										.html(operTypeName);

							}
							$("#operTypeName").val("")
							showMsgInfo("操作成功");
						} else {
							$("#operTypeName").val("");
							var ctLandid = $("li[isLandSelected=1]").attr("id");
							var ulOperId = "ul" + ctLandid;
							$("#" + ulOperId).remove();

							buildOpers(_jsonData);// 加载业态

						}

						$("#operationLoading").hide();
						$("#operDiv").show();
					},
					error : function(responseText) {
						$("#operationLoading").hide();
						$("#operDiv").show();
					}
				});
	}

}

/**
 * 构建地块所对应的业态
 * 
 * @param jsonData
 * @return
 */
function buildOpers(jsonData) {
	var _operDiv = $("#operDiv");
	var ctLandid = $("li[isLandSelected=1]").attr("id");
	var ulOperId = "ul" + ctLandid;
	$("ul[ctr=ul]").hide();
	var _htmlText = new StringBuffer();
	_htmlText.append("<ul class='ctslt-nav' ");
	_htmlText.append("id='" + ulOperId + "'");
	_htmlText.append("ctr='ul'>");
	var _liId = "";// 业态编号
	var _text = "";// 业态名称

	$.each(jsonData, function(i, oper) {
		_text = oper.operTypeName;
		_liId = oper.ctOperationId;
		if (oper.baseOperation) {// 如果是科目模板数据
			_liId += ctLandid;
			_text = "<label class='baseTxt' pid='" + _liId + "'>"
					+ oper.operTypeName + "</label>";
		}
		_htmlText.append("<li lid='" + ctLandid + "'  id='" + _liId + "'");
		_htmlText.append("isBase='" + oper.baseOperation + "' title='"
				+ oper.operTypeName + "' ctr='opers'>");
		_htmlText.append("<div  style='text-align:left;' >");
		_htmlText.append("<div class='opDiv_full' pLiId='" + _liId + "'");
		_htmlText.append(" ctr='divOperations' isBase='" + oper.baseOperation
				+ "'>" + _text + "</div>");
		_htmlText
				.append("<div style='float:left;display:none;' ctr='delOperation' lid='"
						+ _liId + "' class='btDel'>");
		_htmlText.append("&nbsp;&nbsp;&nbsp;");
		_htmlText.append("</div>");
		_htmlText.append("</div></li>");

	});
	_htmlText.append("</ul> ");

	$(_operDiv).append(_htmlText.toString());
	bindOperationEvent();
}
/**
 * 绑定业态事件
 * 
 * @return
 */
function bindOperationEvent() {

	$("li[ctr=opers]").unbind("click");
	$("li[ctr=opers]").bind("click", function() {
				$("#btAddOper").val("新增");
				$("#operTypeName").val("");
				if ($(this).attr("class") == "ctslt-nav-click") {
					$(this).removeClass();
				} else {
					$(this).addClass("ctslt-nav-click");

				}

			}).hover(function() {
				if ($(this).attr("isBase") == "true") {
					return;
				}
				$("div[lid=" + $(this).attr("id") + "]").show();
			}, function() {
				$("div[lid=" + $(this).attr("id") + "]").hide();
			});

	$("div[ctr=divOperations]").unbind("dblclick");
	$("div[ctr=divOperations]").bind("dblclick", function() {
				if ($(this).attr("isBase") == "true") {
					return;
				}
				$("#btAddOper").val("修改");
				$("#operTypeName").val($(this).html());
				$("#operationId").val($(this).attr("pLiId"));

			});

	$("div[ctr=delOperation]").unbind("click");
	$("div[ctr=delOperation]").click(function() {
		var operationName = $("#" + $(this).attr("lid")).attr("title");
		var operationId = $(this).attr("lid");
		$.messager.confirm('业态删除提示', '你确认要删除【' + operationName + '】吗？',
				function(t) {
					if (t) {
						// 执行删除
						delOperation(operationId);
					}
				});

	});

}
/**
 * 删除指定的业态(只删除地块所对应的业态，在此不能删除基础业态信息
 * 
 * @param operationId
 *            业态CD
 * @return “”
 */
function delOperation(operationId) {

	var _ctLandid = $("li[isLandSelected=1]").attr("id");

	var _ctLederid = $("#ctLdId").val();
	var _url = _ctx + "/ct/ct-operation!doDelete.action";
	$.ajax({
				url : _url,
				type : "POST",
				data : "ctOperationId=" + operationId + "&ctLederid="
						+ _ctLederid + "&ctLandid=" + _ctLandid,
				success : function(responseText) {
					var _jsonData = toJson(responseText);

					if (_jsonData != undefined && _jsonData.status != undefined) {

						if (_jsonData.status != true) {
							showMsgInfo(_jsonData.errorMsg);
							return;
						}

						$("#" + operationId).remove();
						showMsgInfo("业态删除成功！");
					}

				},
				error : function(responseText) {
					showMsgInfo(responseText);
				}
			});

}
/**
 * 根据地块号加载相应的业态信息
 * 
 * @param ctLandid
 *            地块CD
 * @return ""
 */
function loadOperations(ctLandid) {
	$("#operDiv").hide();
	$("#addOperDiv").show();
	$("#operationLoading").show();
	var _url = _ctx + "/ct/ct-operation!getLandToOpers.action";
	$.ajax({
				url : _url,
				type : "POST",
				data : "ctLandid=" + ctLandid,
				success : function(responseText) {
					var _jsonData = toJson(responseText);

					if (_jsonData != undefined && _jsonData.status != undefined) {

						if (_jsonData.status != true) {
							showMsgInfo(_jsonData.errorMsg);
							return;
						}
						showMsgInfo("操作成功");
					} else {
						buildOpers(_jsonData);// 加载地块所对应的业态

					}
					$("#operationLoading").hide();
					$("#operDiv").show();
				},
				error : function(responseText) {
					$("#operationLoading").hide();
					$("#operDiv").show();
				}
			});

}

// 拷贝基础业态数据
function copyBaseOperation(ctLandid, operTypeName, curOperId) {
	var _url = _ctx + "/ct/ct-operation!addOper.action";
	$.ajax({
		url : _url,
		type : "POST",
		async : false, // ajax同步
		data : "operTypeName=" + operTypeName + "&ctLandid=" + ctLandid
				+ "&isCallOperId=true",
		success : function(responseText) {
			var _jsonData = toJson(responseText);
			if (_jsonData != undefined && _jsonData.status != undefined) {
				if (_jsonData.status != true) {
					showMsgInfo(_jsonData.errorMsg);
					return;
				} else {
					var newOperationId = _jsonData.operId;
					$("div[pliid=" + curOperId + "]").attr("isBase", false);
					$("#" + curOperId).attr("isBase", false);
					$("#" + curOperId).attr("id", newOperationId);
					$("div[lid=" + curOperId + "]").attr("lid", newOperationId);
					$("div[pliid=" + curOperId + "]").attr("pliid",
							newOperationId);
					$("label[pid=" + curOperId + "]").removeClass();

				}

			}

		},
		error : function(responseText) {
			showMsgInfo(responseText);
		}
	});
}
/**
 * 生成目标成本
 * 
 * @return
 */

function buildCostItem() {

	$("#showOperation").show();
	$('#showOperation').dialog({
		title : "生成目标成本科目",
		modal : true,
		width : 450,
		buttons : [{
					id : 'errMsg',
					text : '<div id="error"></div>',
					handler : function() {
					}
				}, {
					text : '确定',
					iconCls : 'icon-ok',
					handler : function() {

						if ($("#isHasBuild").val() == "true") {
							var _msg = new StringBuffer();
							_msg.append("当前目标成本科目已经生成是否覆盖<br/>");

							_msg
									.append("<label style='color:red;font-weight:bold'>");
							_msg.append("覆盖后已生成的成本科目将无法恢复，</label>是否继续？")
							_msg.append(" ")
							$.messager.confirm('提示', _msg.toString(), function(
											t) {
										if (t) {
											prePareBuildCostItem();
										}
									});
						} else {
							prePareBuildCostItem();
						}

					}
				}, {
					text : '退出',
					handler : function() {
						$('#showOperation').dialog('close');
					}
				}, {
					id : 'WarmTips',
					text : '<div>友情提示：新增业态为黑色，基础业态为绿色</div>',
					handler : function() {
					}
				}]
	});

	$("#errMsg").removeClass();
	$("#errMsg").addClass("aError");

	$("#WarmTips").removeClass();
	$("#WarmTips").addClass("warmTips");

	$("#landName").focus();// 设置地块文本框焦点

}
function prePareBuildCostItem() {

	var _isAlert = false;
	var _noOperationSelectedItem = "";
	var _isSelectedAnyOperation = false;
	var _isSelected = false;
	// 没有任何地块则提示
	if ($("li[ctr=lands]").length < 1) {

		showMsgInfo("请先添加地块！");
		return;
	}

	$.each($("li[ctr=lands]"), function(i, land) {
				_isSelected = true;// 当前地块有业态被选中
				if ($("li[lid=" + $(land).attr("id") + "]").length < 1) {
					_isSelected = false;
				} else {
					_isSelected = false;
					$.each($("li[lid=" + $(land).attr("id") + "]"), function(j,
									opItem) {
								if ($(opItem).attr("class") == "ctslt-nav-click") {
									_isSelectedAnyOperation = true;
									_isSelected = true;
									return true;
								}
							});
				}
				if (!_isSelected) {
					_isAlert = true;
					if (isEmptyOrNull(_noOperationSelectedItem)) {
						_noOperationSelectedItem = $(land).attr("title");
					} else {
						_noOperationSelectedItem += "," + $(land).attr("title");
					}
				}

			});
	// 未选中任何业态则提示
	if (!_isSelectedAnyOperation) {
		showMsgInfo("你未选择任何业态，无法生成！");
		return;
	}
	if (_isAlert) {
		$.messager.confirm('提示', '地块【' + _noOperationSelectedItem
						+ '】未选择任何业态信息\n这些地块将不会显示你确定要继续吗？', function(t) {
					if (t) {
						autoStartBuildCostItem(_noOperationSelectedItem);
					}
				});
	} else {
		autoStartBuildCostItem();
	}

}

/**
 * 开始生成目标成本
 * 
 * @return
 */
function autoStartBuildCostItem(noOperationSelectedItem) {
	noOperationSelectedItem = "," + noOperationSelectedItem + ",";
	TB_showMaskLayer("请稍等,正在生成目标成本...");
	$('#showOperation').dialog('close');
	$.each($("li[isBase=true]"), function(j, oper) {

				if ($(oper).attr("class") == "ctslt-nav-click") {
					var _ctLandid = $(oper).attr("lid");
					var _operTypeName = $(oper).attr("title");
					var _curOperId = $(oper).attr("id");
					copyBaseOperation(_ctLandid, _operTypeName, _curOperId);
				}

			});
	var _buldJsonBuffer = new StringBuffer();

	var _ctLedgerId = $("#ctLdId").val();
	var _count = 0;
	_buldJsonBuffer.append("isCovert=");
	_buldJsonBuffer.append($("#isHasBuild").val())
	_buldJsonBuffer.append("&jsonData=[");
	$.each($("li[ctr=lands]"), function(i, land) {
				if ($("li[lid=" + $(land).attr("id") + "]").length < 1
						|| noOperationSelectedItem.indexOf(","
								+ $(land).attr("title") + ",") > -1) {
					if (_count > 0) {

						_buldJsonBuffer.append(",");
					}

					_buldJsonBuffer.append("{ctLandId:\"" + $(land).attr("id")
							+ "\"");
					_buldJsonBuffer.append(",landName:\""
							+ $(land).attr("title") + "\"");
					_buldJsonBuffer.append(",ctLedgerId:\"" + _ctLedgerId
							+ "\"");
					_buldJsonBuffer.append(",operName:\"\"");
					_buldJsonBuffer.append(",ctOperationId:\"\"}");
					_count++;
				} else {
					$.each($("li[lid=" + $(land).attr("id") + "]"), function(j,
									opItem) {

								if ($(opItem).attr("class") == "ctslt-nav-click") {
									if (_count > 0) {

										_buldJsonBuffer.append(",");
									}
									_buldJsonBuffer.append("{ctLandId:\""
											+ $(land).attr("id") + "\"");
									_buldJsonBuffer.append(",landName:\""
											+ $(land).attr("title") + "\"");
									_buldJsonBuffer.append(",ctLedgerId:\""
											+ _ctLedgerId + "\"");
									_buldJsonBuffer.append(",operName:\""
											+ $(opItem).attr("title") + "\"");
									_buldJsonBuffer.append(",ctOperationId:\""
											+ $(opItem).attr("id") + "\"}");
									_count++;

								}
							});
				}
			});

	_buldJsonBuffer.append("]");
	var _url = _ctx + "/ct/ct-item!buildNewCostItem.action";
	$.ajax({
				url : _url,
				type : "POST",
				dataType : "json",
				data : _buldJsonBuffer.toString(),
				cache : false,
				success : function(responseText) {
					if (typeof(responseText) == "object") {// 返回的为JSON数据

						ctCostItemShow();
						buildCostItemDetails(responseText);// 构建目标成本
						$("#isHasBuild").val("true");
						$('#showOperation').dialog('close');

					} else {
						$('#showOperation').dialog('open');
						alert(responseText);
					}
					TB_removeMaskLayer();
				},
				error : function(responseText) {
					TB_removeMaskLayer();
					$('#showOperation').dialog('open');
					alert(responseText);

				}
			});

}
var firstTabDiv = "";
/**
 * 生成目标成本详细信息
 * 
 * @param costItemJson
 * @return “”
 */
function buildCostItemDetails(costItemJson) {
	var _hasExsitLands = "";
	var _landHtml = new StringBuffer();

	var _landTabHtml = new StringBuffer();
	var _landNameDivCtrl = $("#ctLandNameDiv");
	var _landToOperationDivsCtrl = $("#ctLandToOperationDivs");

	var _landName = "";
	var _landId = "";
	var operName = "";
	var ctOperationId = "";
	var tabId = "";
	var pareTabId = "";
	_landHtml.append("<table><tr><th width='20'></th><th>地块：</th>");
	$(_landToOperationDivsCtrl).html("");
	var landCount = 0;
	var subTabCount = 0;
	$.each(costItemJson, function(i, item) {
		_landId = item.ctLandId;
		_landName = item.landName;
		ctOperationId = item.ctOperationId;
		operName = item.operName;
		tabId = ctOperationId;
		pareTabId = "tabDiv" + _landId;
		if (!isEmptyOrNull(ctOperationId)) {
			if (_hasExsitLands.indexOf("," + item.landName + ",") < 0) {
				subTabCount = 0;
				if (landCount > 0) {
					_landHtml.append("<th>|</th>");

				}
				_landHtml.append("<th class='landText'");
				_landHtml.append("ctr='landArea'");
				_landHtml.append("landId='" + _landId + "'");
				_landHtml.append("isFirst='" + landCount + "'>");
				_landHtml.append(_landName);
				_landHtml.append("</th>");

				landCount++;

				_landTabHtml.append("<div id='" + pareTabId + "'");
				_landTabHtml.append("isFirstTabSelected='0'");
				_landTabHtml.append("curOperationId='" + tabId + "'");
				_landTabHtml.append("class='easyui-tabs tabs-container'");
				_landTabHtml.append("tools='#tab-tools'");
				_landTabHtml
						.append("style='width:auto;height:450px;right:10px' ctr='landTabDiv'>");
				_landTabHtml.append("</div>");
				$(_landToOperationDivsCtrl).append(_landTabHtml.toString());
				$("#" + pareTabId).tabs();
				$("#" + pareTabId)
						.append("<div class='tabs-panels' style='height: 423px; width:auto;'> </div>");

			}

			_hasExsitLands += "," + item.landName + ",";
		}

		if (!isEmptyOrNull(ctOperationId)) {
			addTab(operName, tabId, pareTabId, _landId, ctOperationId);
			if (subTabCount == 0) {
				$("#" + pareTabId).tabs();
			}
		}
		subTabCount++;
	});
	_landHtml.append("</tr></table>");

	$(_landNameDivCtrl).html(_landHtml.toString());

	bindNewBuildLandDivEvent();

}

/**
 * 绑定成本科目生成生的地块事件
 * 
 * @return""
 */
function bindNewBuildLandDivEvent() {

	$("th[ctr=landArea]").unbind("click");
	$("th[ctr=landArea]").bind("click", function() {

				$("th[ctr=landArea]").removeClass();
				$("th[ctr=landArea]").addClass("landText");
				$(this).removeClass();
				// 设置当前地块样式为选中
				$(this).addClass("landSelectedText");

				var _landId = $(this).attr("landId");
				$("div[ctr=landTabDiv]").hide();

				var pareTabId = "#tabDiv" + _landId;
				$(pareTabId).show();
				// 地块中第一个业态tab是否选中过
				var isFirstTabSelected = $(pareTabId)
						.attr('isFirstTabSelected');

				if (isFirstTabSelected == "0") {
					// 第一个业态未选中过，则点击地块时先选中第一个业态（当前地块所对应的),此做为解决第一个TAB无法显示数据问题
					$(pareTabId).tabs();
					$(pareTabId).attr('isFirstTabSelected', "1");
				}
				// 设置当前地块号
				$("#ctLandIdHide").val($(this).attr("landId"));
				// 设置当前选中的业态ID
				$("#ctOperationIdHide")
						.val($(pareTabId).attr('curOperationId'));

			});
	$("ul.tabs li").unbind("click");
	$("ul.tabs li").bind("click", function() {
				var lb = $(this).find("a span label");// 查找LI子孙节点
				var curOperationId = $(lb).attr("operId");// 获取选中的业态ID
				// 设置当前选中的业态ID号
				$("#ctOperationIdHide").val(curOperationId);
				var _landId = $("#ctLandIdHide").val();
				$("#tabDiv" + _landId).attr('curOperationId', curOperationId);

			});

	$("th[isFirst=0]").removeClass();
	$("th[isFirst=0]").addClass("landSelectedText");// 设置第一个地块样式为选中
	var defaultLandId = $("th[isFirst=0]").attr("landId");
	var defaultTabId = "#tabDiv" + defaultLandId;
	var defaultOperationId = $(defaultTabId).attr("curOperationId");
	$("div[ctr=landTabDiv]").hide();
	$(defaultTabId).show();
	$(defaultTabId).attr('isFirstTabSelected', "1");

	// 设置默认地块
	$("#ctLandIdHide").val(defaultLandId);
	// 设置默认业态
	$("#ctOperationIdHide").val(defaultOperationId);
	if (isBindEvent()) {
		// 显示按钮组
		$("#divButton").show();
	} else {
		// 禁止生成成本科目按钮
		$("#btn_buildCostItem").attr("disabled", true);
		$("#divButton").hide();
	}

}
// 增加TABS

function addTab(tabTitle, tabId, pareTabId, _landId, operationId) {

	$("#ctLandIdHide").val(_landId);
	$("#ctOperationIdHide").val(operationId);
	var _treeGridDiv = "div" + tabId;

	if ($("#" + _treeGridDiv).length > 0) {
		return;
	}
	$("#" + pareTabId).tabs('add', {
		title : "<label ctr='tabTitle'  operId='" + operationId + "'>"
				+ tabTitle + "</label>",
		content : "<div id='" + _treeGridDiv + "' ctr='landOperDiv'></div>",
		id : tabTitle + 1,
		closable : false
	});

	// 创建成本科目TreeGrid
	createTreeGridDetails(_treeGridDiv);
}

// 构建序号
function buildSque(squ, level, pid) {
	var squTextBuffer = new StringBuffer();

	switch (level) {
		case 1 :// 一级

			switch (squ) {
				case 1 :
					squTextBuffer.append("一");
					break;
				case 2 :
					squTextBuffer.append("二");
					break;
				case 3 :
					squTextBuffer.append("三");
					break;
				case 4 :
					squTextBuffer.append("四");
					break;
				case 5 :
					squTextBuffer.append("五");
					break;
				case 6 :
					squTextBuffer.append("六");
					break;
				case 7 :
					squTextBuffer.append("七");
					break;
				case 8 :
					squTextBuffer.append("八");
					break;
				case 9 :
					squTextBuffer.append("九");
					break;
				case 10 :
					squTextBuffer.append("十");
					break;
				default :
					break;

			}

			break;
		case 2 :
			squTextBuffer.append(squ);
			break;

		default :
			squTextBuffer.append(getNewSequ(pid));
			squTextBuffer.append("." + squ);
			break;

	}
	return squTextBuffer.toString();
}
function getNewSequ(pid) {
	var _parentLabel = $("#" + pid);
	if (typeof(_parentLabel) == "undefined") {

		return "";
	}
	var _pSequeNo = $("#" + pid).attr("sequenceNo");
	var _pItemId = $("#" + pid).attr("pid");
	var _level = $("#" + pid).attr("itemLevel");
	if (_level == 1) {
		return "";
	}
	var _newSqu = getParentSequ(_pItemId);

	return _newSqu == "" ? _pSequeNo : (_newSqu + "." + _pSequeNo);
}

function getParentSequ(pid) {
	var _parentLabel = $("#" + pid);
	if (typeof(_parentLabel) == "undefined") {
		return "";
	}

	var _pSequeNo = $("#" + pid).attr("sequenceNo");
	if (typeof(_pSequeNo) == "undefined" || _pSequeNo == null
			|| _pSequeNo == undefined) {
		return "";
	}
	var _pItemId = $("#" + pid).attr("pid");
	var _level = $("#" + pid).attr("itemLevel");
	if (_level == 1) {
		return "";
	}
	var _newSqu = "";
	_newSqu += getParentSequ(_pItemId);
	return _newSqu == "" ? _pSequeNo : (_newSqu + "." + _pSequeNo);
}

// 目标成本明细
function createTreeGridDetails(treegridDiv) {

	var lastIndex;
	$('#' + treegridDiv).treegrid({

		autoWidth : true,
		height : 400,
		nowrap : false,
		rownumbers : false,
		animate : true,
		// showTreeIcon : false,
		collapsible : true,

		url : _ctx + '/ct/ct-item!getCostItem.action',
		idField : 'ctItemId',
		treeField : 'itemName',
		columns : [[{
					field : 'sequenceNo',
					title : '序号',
					width : 40,
					align : 'center',
					formatter : function(value, row) {
						if (row.itemLevel > 2)// 3级后的标识符不在此处显示
						{
							return "";
						}

						return buildSque(value, row.itemLevel, row.parentId);
					}
				}, {
					field : 'itemName',
					title : '成本科目(金额单位：RMB)',
					width : 230,
					cssStyle : "width:222px;text-align:left;overflow:hidden;white-space:nowrap;height:24px;line-height: 24px;",
					formatter : function(value, row) {
						$("#ctVersion").val(row.recordVersion);// 记录当前版本号
						var _text = new StringBuffer();
						var _buildStr = value;
						var _itemId = row.ctItemId;
						_text.append("<label");

						_text.append(" itemId='" + _itemId + "'");
						_text.append(" title ='" + value + "'");

						_text.append("ctr='itemTitle'");
						// 科目名称单击事件
						_text.append(" onclick='btn_itemNameClickEvent(this)'");
						_text.append(">");

						if (row.itemLevel > 2) {

							var _treeGridId = "#div"
									+ $("#ctOperationIdHide").val();

							if ($(_treeGridId).length < 1
									|| $(_treeGridId) == null) {

								return;
							}
							var _node = $(_treeGridId).treegrid('getSelected');
							if (typeof(_node) == "undefined" || _node == null) {
								_text.append(row.sequenceText);
							} else {
								_text.append(buildSque(row.sequenceNo,
										row.itemLevel, row.parentId));
							}

						}

						_text.append("&nbsp;" + value);
						_text.append("</label>");

						var _targetAmt = row.targetAmt;
						if (_targetAmt == "-1") {

							_targetAmt = "";
						} else {
							_targetAmt = "" + toDecimal(_targetAmt);
						}

						var _buildBuf = new StringBuffer();
						_buildBuf.append("<label ");
						_buildBuf
								.append(" class='tree-checkbox tree-checkbox0 chkbxHide'");
						_buildBuf.append("id='" + _itemId + "'");
						if (isEmptyOrNull(row.parentId)
								&& !isEmptyOrNull(_targetAmt)) {

							_buildBuf.append(" isFirstVal='true' ");
						}
						_buildBuf.append(" pid='" + row.parentId + "'");
						_buildBuf.append(" title='" + value + "' landid=''");
						_buildBuf.append(" checked='false' operationid='"
								+ row.ctOperationId + "'");
						_buildBuf.append(" state='" + row.state + "'");
						_buildBuf.append(" sequenceNo=");
						_buildBuf.append("'" + row.sequenceNo + "'");
						_buildBuf.append(" itemLevel=");
						_buildBuf.append("'" + row.itemLevel + "'");
						_buildBuf.append(" targetAmt=");
						_buildBuf.append("'" + _targetAmt + "'");
						_buildBuf.append(" conPlanId=");
						_buildBuf.append("'" + row.ctContPlanId + "'");
						_buildBuf.append(" ctr='lb_treeCk' ");
						_buildBuf
								.append(" onclick='btn_itemCkbSelEvent(this)'")
						_buildBuf
								.append("style='cursor:pointer;veritca-align:middle;");
						_buildBuf
								.append("padding-left:2px;' >&nbsp;&nbsp;&nbsp;");

						_buildBuf.append("</label>");
						_buildBuf.append(_text.toString());
						return _buildBuf.toString();

					}
				}, {
					field : 'targetAmt',
					title : '目标成本',
					width : 100,

					formatter : function(value, row) {
						var _amtVal = value;
						if (value == "-1") {

							value = "&nbsp;&nbsp;";
							_amtVal = "";
						} else {
							value = "" + toDecimal(_amtVal)
							_amtVal = value;
						}
						var _itemId = row.ctItemId;
						var _buildBuf = new StringBuffer();
						_buildBuf.append("<input type='text'");
						_buildBuf.append("hideId='" + _itemId + "' ");
						_buildBuf.append("maxlength='15'");
						_buildBuf.append("pid='" + row.parentId + "'");
						_buildBuf.append("value='" + _amtVal + "'");
						_buildBuf.append(" class='selectInput chkbxHide' ");
						// 目标成本失去焦点事件
						_buildBuf.append(" onblur='doModTargetAmt(this)'");
						// 判断是否已经关联合约规划
						if (isEmptyOrNull("" + row.ctContPlanId)) {
							_buildBuf.append("ctr='editor'");
						}
						_buildBuf.append(" />");
						_buildBuf.append("<div spId='" + _itemId + "'");
						_buildBuf.append(" onclick='btn_AmtEditorEvent(this)'");
						if (isEmptyOrNull("" + row.ctContPlanId)) {
							_buildBuf.append("ctr='editor'");
						}
						_buildBuf.append(" style='width:120px;height:20px'>");
						_buildBuf.append(value);
						_buildBuf.append("</div>");
						return _buildBuf.toString();

					}
				}, {
					field : 'contPlanName',
					title : '合约规划',
					width : 150,
					cssStyle : "width:142px;text-align:left;overflow:hidden;white-space:nowrap;",
					formatter : function(value, row) {
						var _itemId = row.ctItemId;
						var _buildBuf = new StringBuffer();
						_buildBuf.append("<span cpItemId='" + _itemId + "'");
						_buildBuf.append(" ctCPId='" + row.ctContPlanId + "'");
						_buildBuf.append(" title='" + value + "'");
						_buildBuf.append(" class='clsConPlan'");
						// 增加合约规划点击事件
						_buildBuf
								.append(" onclick='btn_dispalyContDetail(this)'");
						_buildBuf.append(">");
						_buildBuf.append(value);
						_buildBuf.append("</span>");
						return _buildBuf.toString();

					}
				}, {
					field : 'contractNo',
					title : '合同编号',
					width : 150,
					formatter : function(value, row) {
						return "&nbsp;&nbsp;/";
					}
				}, {
					field : 'contractName',
					title : '合同名称',
					width : 150,
					formatter : function(value, row) {
						return "&nbsp;&nbsp;/";
					}
				}, {
					field : 'signCorp',
					title : '对方签约单位',
					width : 100,
					formatter : function(value, row) {
						return "&nbsp;&nbsp;/";
					}
				}, {
					field : 'remark',
					title : '备注',
					width : 100
				}]],
		onBeforeLoad : function(row, param) {

			var _url = new StringBuffer();
			_url.append(_ctx + '/ct/ct-item!getCostItem.action?');

			_url.append("ctLedgerId=" + $("#ctLdId").val());
			_url.append("&ctOperationId=" + $("#ctOperationIdHide").val());
			_url.append("&ctItemId=");
			if (row) {
				_url.append(row.ctItemId);
			}
			$(this).treegrid('options').url = _url.toString();
		},
		onCollapse : function(row) {
			if (row) {
				var _ctItmeId = row.ctItemId;
				$("label[itemId=" + _ctItmeId + "]").attr("isCollapse", "true");
			}

		},
		onBeforeExpand : function(row) {
			if (row) {
				var _ctItmeId = row.ctItemId;
				$("label[itemId=" + row.ctItemId + "]").attr("isCollapse",
						"false");
			}
		},
		onLoadSuccess : function(row, data) {

			if (typeof(data) == "object") {
				if (data.length > 0 && typeof(data[0].parentId) != "undefined") {
					var _pareId = data[0].parentId;
					if (!isEmptyOrNull(_pareId)) {
						$("tr[node-id=" + _pareId + "]")
								.attr("hasLoad", "true");
					}

				}
			}
			if ($("#btn_dismantleCost").attr("isShowCheck") == "true") {
				$("label[ctr=lb_treeCk]").hide();
				$("label[conPlanId=]").show();
			} else {
				$("label[conPlanId=]").hide();
			}

		}

	});

}
/**
 * 目标成本单击事件 点击显示编辑框
 * 
 * @param {}ele 事件源
 */
function btn_AmtEditorEvent(ele) {

	// 如果正在分解科目、已关联合约规划或者台账信息已提交则不允许编辑
	if ($("#btn_dismantleCost").attr("isShowCheck") == "true" || !isBindEvent()
			|| $(ele).attr("ctr") != "editor") {

		return;
	}
	var _itemId = $(ele).attr("spId");
	var _targetAmt = $("input[hideId=" + _itemId + "]");

	var _conplanid = $("#" + _itemId).attr("conplanid");
	var _pItemId = $("#" + _itemId).attr("pid");
	var _totalAmt = $("#ctCostTargetTotalAmt").text(); // 总的目标金额
	if (isEmptyOrNull(_totalAmt) || 0 == parseFloat(_totalAmt)) {
		$.messager.alert('提示信息', "不允许更改目标成本，请确认总的目标成本总额是否存在且不能为0！", 'info');
		return;
	}
	if (!isEmptyOrNull(_pItemId)) {
		var _pTargetAmt = $("input[hideId=" + _pItemId + "]").val();
		if (isEmptyOrNull(_pTargetAmt) || 0 == parseFloat(_pTargetAmt)) {
			$.messager.alert('提示信息', "不能设置当前节点目标成本\n请先设置父级节点目标成本！", 'info');
			return;
		}
	}

	$(ele).hide();
	$(_targetAmt).show();
	$(_targetAmt).focus();

}

/**
 * 更新目标成本
 * 
 * @param {}
 *            eidtor
 */
function doModTargetAmt(eidtor) {

	var _targetAmt = $(eidtor).val().trim();
	var _ctItemId = $(eidtor).attr("hideId");
	var _postParams = new StringBuffer();
	var _valid = true;
	var _msg = new StringBuffer();
	// 目标成本显示span控件
	var _originTargetAmtCtr = $("div[spId=" + _ctItemId + "]");
	// label （科目前面出现的checkbox）控件，这个控件中包含了科目信息都以属性方式存在
	var _labCkBoxCtr = $("#" + _ctItemId);
	// 先记录原始金额
	var _orginTargetAmt = $(_labCkBoxCtr).attr("targetAmt").trim();
	if (isEmptyOrNull(_targetAmt)) {
		_valid = false;
	} else if (_valid && !isNum(_targetAmt)) {
		_msg.append("抱歉，无法更新数据，输入的数据不合法！");
		_valid = false;
	} else if (_valid && _orginTargetAmt == _targetAmt) {// 如果修改的目标成sg
		_valid = false;
	}
	_postParams.append("ctItemId=");
	_postParams.append(_ctItemId);
	_postParams.append("&");
	_postParams.append("targetAmt=");
	_postParams.append(_targetAmt);
	var _pItemId = $("#" + _ctItemId).attr("pid");

	if (_valid && !isEmptyOrNull(_pItemId)) {
		var _pTargetAmt = $("input[hideId=" + _pItemId + "]").val();
		if (isEmptyOrNull(_pTargetAmt) || 0 == parseFloat(_pTargetAmt)) {

			_msg.append('提示信息', "不能设置当前目标成本\n请先设置父级科目目标成本！", 'info');
			_valid = false;
		} else if (_valid) {
			var _slibTargetAmts = 0.0;
			$.each($("label[pid=" + _pItemId + "]"), function(i, item) {

						var _slibTargetAmt = $("input[hideId="
								+ $(item).attr("id") + "]").val();
						if (isEmptyOrNull(_slibTargetAmt)
								|| "0" == _slibTargetAmt) {

						} else {

							_slibTargetAmts += parseFloat(_slibTargetAmt);

						}

					});
			// 看看是兄弟节点之和是否大于父母节点
			if (parseFloat(_pTargetAmt) < _slibTargetAmts) {

				// 剩余可分配金额
				var _canSetAmt = parseFloat(_pTargetAmt
						- (_slibTargetAmts - parseFloat(_targetAmt)));
				_msg.append("当前科目目标成本之和");
				_msg.append("<br/>[" + _slibTargetAmts + "]");
				_msg.append("不能大于父级科目目标成本[" + toDecimal("" + _pTargetAmt)
						+ "]！");
				if (_canSetAmt < 0) {
					_canSetAmt = 0
				};

				_msg.append("<br/>父科目剩余可分配金额为：[" + toDecimal("" + _canSetAmt)
						+ "]");
				_valid = false;
			}

		}
	} else if (_valid) {
		var _allFirstLevelItems = $("label[isfirstval=true]");
		var _allFirstTotalAmt = 0.0;
		// 统计所有一级科目金额
		$.each(_allFirstLevelItems, function(i, item) {
			var _subItemId = $(item).attr("id");
			// 排除当前修改的科目金额
			if (_subItemId != _ctItemId) {

				var _subTargetAmt = $("input[hideId=" + _subItemId + "]").val();

				if (!isEmptyOrNull(_subTargetAmt) && "0" != _subTargetAmt
						&& isNum($.trim(_subTargetAmt))) {

					_allFirstTotalAmt += parseFloat($.trim(_subTargetAmt));;
				}
			}

		});
		_allFirstTotalAmt += parseFloat(_targetAmt);

		var _totalAmt = $("#ctCostTargetTotalAmt").text().trim(); // 总的目标金额

		// 查看所有已设置的一级科目成本金额是否大于总的成本金额
		if (parseFloat(_totalAmt) < _allFirstTotalAmt) {
			// 剩余可分配金额
			var _canSetAmt = parseFloat(_totalAmt
					- (_allFirstTotalAmt - parseFloat(_targetAmt)));
			_msg.append("当前所有已设置的科目成本金额[");
			_msg.append(_allFirstTotalAmt);
			_msg.append("]\n");
			_msg.append("不能大于总的目标成本金额[" + toDecimal("" + _totalAmt) + "]");
			if (_canSetAmt < 0) {
				_canSetAmt = 0;
			};

			_msg.append("\n<br/>剩余可分配金额为：[" + toDecimal("" + _canSetAmt) + "]");
			_valid = false;

		}

	}

	if (_valid) {

		var _isParentNode = $("#" + _ctItemId).attr("state");
		// 如果本身原始金额为空或为0，则就没必要查看子科目的值是否大于刚修改的金额
		if (_valid && _isParentNode == "closed"
				&& !isEmptyOrNull(_orginTargetAmt) && "0" != _orginTargetAmt
				&& parseFloat(_orginTargetAmt) > 0) {
			var _url = _ctx + '/ct/ct-item!getCostItem.action?';
			_url += "lederId=" + $("#ctLdId").val();
			_url += "&ctOperationId=" + $("#ctOperationIdHide").val();
			_url += "&itemid=" + _ctItemId;
			var _subTargetAmts = 0.0;
			var _subItems = $("label[pid=" + _ctItemId + "]");
			if (_subItems.length > 0) {
				$.each(_subItems, function(i, item) {

							var _subTargetAmt = $.trim($("input[hideId="
									+ $(item).attr("id") + "]").val());

							if (!isEmptyOrNull(_subTargetAmt)
									&& isNum(_subTargetAmt)) {

								_subTargetAmts += parseFloat(_subTargetAmt);

							}

						});
			} else {
				// 搜索子节点
				$.ajax({
							url : _url,
							async : false, // ajax同步
							success : function(text) {
								var _jsonData = null;
								if (typeof(text) != "object") {
									_jsonData = toJson(text);

								} else {
									_jsonData = text;
								}

								if (typeof(_jsonData) == "object"
										&& _jsonData.length > 0) {

									for (var i = 0; i < _jsonData.length; i++) {
										var _item = _jsonData[i];

										var _tamt = "" + _item.targetAmt;

										if (!isEmptyOrNull(_tamt)
												&& _tamt != "-1"
												&& isNum(_tamt)) {
											_subTargetAmts += parseFloat(_tamt);
										}

									}

								}
							}
						});

			}

			// 看看是兄弟节点之和是否大于父母节点
			if (parseFloat(_targetAmt) < _subTargetAmts) {

				var _msg = new StringBuffer();
				_msg.append("当前科目成本金额[");
				_msg.append(_targetAmt);
				_msg.append("]\n");
				_msg.append("不能小于其子节点目标成本[" + toDecimal("" + _subTargetAmts)
						+ "]之和");
				_msg.append("\n你可以先修改其子科目中的成本科目值");
				_msg.append("\n然后再修改其值");
				_valid = false;
			}

		}
	}

	if (!_valid) {
		if (!isEmptyOrNull(_msg.toString())) {
			$.messager.alert('提示信息', _msg.toString(), 'info');
		}
		$(eidtor).hide();
		$(eidtor).val(_orginTargetAmt);
		$(_originTargetAmtCtr).show();
		return;

	}
	// 更新成本科目
	$.ajax({
				url : _ctx + '/ct/ct-item!updateCostItem.action',
				data : _postParams.toString(),
				async : false, // ajax同步
				success : function(text) {

					var _jsonData = toJson(text);
					$(eidtor).hide();
					var _originTargetLabel = null;
					if (_originTargetAmtCtr.length > 0) {
						_originTargetLabel = _originTargetAmtCtr[0];
					}
					if (_jsonData != undefined && _jsonData.status != undefined) {
						if (_jsonData.status) {
							$(_labCkBoxCtr).attr("targetAmt",
									toDecimal(_targetAmt));
							if (isEmptyOrNull($(_labCkBoxCtr).attr("pid"))) {
								$(_labCkBoxCtr).attr("isFirstVal", "true");
							}
							// 把字符串转化为浮点型数据
							$(_originTargetAmtCtr).html(toDecimal(_targetAmt));
							$(eidtor).val(toDecimal(_targetAmt));
							$(_originTargetLabel).show();

							var _isShowCheck = $("#btn_dismantleCost")
									.attr("isShowCheck");
							if (_isShowCheck == "true") {
								$("label[ctr=lb_treeCk]").show();

							}
							return;

						}

					}
					$(eidtor).val(_orginTargetAmt);
					$(_originTargetAmtCtr).show();
					$.messager.alert('提示信息', _jsonData.errorMsg, 'info');
				}
			});

}

/**
 * 科目名称点击事件 点击展开或折叠节点
 * 
 * @param {}
 *            ele 事件源
 */
function btn_itemNameClickEvent(ele) {

	var _lab = $(ele);
	var _isCollapse = $(_lab).attr("isCollapse");
	var _itemId = $(_lab).attr("itemId");
	var _treeGridId = "#div" + $("#ctOperationIdHide").val();
	if (_isCollapse == "false") {
		$(_treeGridId).treegrid('collapse', _itemId);
		$(_lab).attr("_isCollapse", "true");
	} else {
		$(_treeGridId).treegrid('expand', _itemId);
		$(_lab).attr("isCollapse", "false");
	}

}
/**
 * 科目checkbox勾选
 * 
 * @param {}
 *            ele
 */
function btn_itemCkbSelEvent(ele) {

	$(ele).removeClass();
	$(ele).attr("landid", $("#ctLandIdHide").val());
	$(ele).attr("operationid", $("#ctOperationIdHide").val());
	if ($(ele).attr("checked") == "true") {
		$(ele).addClass("tree-checkbox tree-checkbox0");
		$(ele).attr("checked", 'false');
		recursionChildNode($(ele).attr("id"), false);
	} else {
		$(ele).addClass("tree-checkbox tree-checkbox1");
		$(ele).attr("checked", 'true');
		recursionChildNode($(ele).attr("id"), true);
	}

}
/**
 * 单击科目合约规则事件
 * 
 * @param {}
 *            ele 事件源
 */
function btn_dispalyContDetail(ele) {

	var _ctCpId = $(ele).attr("ctCPId");
	var _cpItemId = $(ele).attr("cpItemId");

	ymPrompt.confirmInfo({
		icoCls : "",
		autoClose : false,
		message : "<div id='ctItemConPlanRelation'><img align='absMiddle' src='"
				+ _ctx + "/images/loading.gif'></div>",
		width : 510,
		height : 460,
		title : "查看【目标成本科目】详情",
		closeBtn : true,
		afterShow : function() {
			var _url = _ctx + "/ct/ct-cont-plan!relationDetail.action";
			$.post(_url, {
						ctContPlanId : _ctCpId
					}, function(result) {
						$("#ctItemConPlanRelation").html(result);

					});
		},
		handler : function(btn) {
			if (btn == 'ok') {

			} else {
				ymPrompt.close();

			}

		},
		btn : [["关闭", 'cancel']]
	});

}

/**
 * 递归TreeGrid所有父节点
 * 
 * @param {}
 *            nodeId
 */
function recursionParentNode(nodeId, isChecked) {

	if (isEmptyOrNull(nodeId)) {
		return;
	}
	var _parentTreeGridNode = $("#" + nodeId);
	if (typeof(_parentTreeGridNode) == "undefined") {
		return;
	}

	if (isChecked) {
		$(_parentTreeGridNode).removeClass("tree-checkbox0");
		$(_parentTreeGridNode).addClass("tree-checkbox1");

		$(_parentTreeGridNode).attr("checked", "" + isChecked);
		recursionParentNode(_parentTreeGridNode.attr("pid"), isChecked);
	} else {

	}

}

/**
 * 递归TreeGrid所有子节点 展开子节点并加载子节点
 * 
 * @param {}
 *            nodeId
 */
function recursionChildNode(nodeId, isChecked) {

	if (isEmptyOrNull(nodeId) || $("#" + nodeId).attr("state") != "closed") {
		return;
	}
	var _childTreeGridNodes = $("label[pid=" + nodeId + "]");

	if (isChecked) {
		$("#div" + $("#ctOperationIdHide").val()).treegrid('expand', nodeId);
	}

	if (typeof(_childTreeGridNodes) == "undefined") {
		window.setTimeout("recursionChildNode('" + nodeId + "'," + isChecked
						+ ")", 500);
		return;
	}
	$.each(_childTreeGridNodes, function(i, node) {
				var _targetAmt = $(node).attr("targetAmt");
				$(node).removeClass();
				if (isChecked) {
					$(node).addClass("tree-checkbox tree-checkbox1");

				} else {
					$(node).addClass("tree-checkbox tree-checkbox0");

				}

				$(node).attr("checked", "" + isChecked);
				var _parentNodeId = "#" + nodeId;
				$(node).attr("landid", $(_parentNodeId).attr("landid"));
				$(node).attr("operationid",
						$(_parentNodeId).attr("operationid"));
				recursionChildNode($(node).attr("id"), isChecked)

			});
}

/**
 * 更新目标成本
 * 
 * @param {}
 *            eidtor
 */

// 增加科目
function ctAddPlanItem() {
	var _treeGridId = "#div" + $("#ctOperationIdHide").val();
	if ($(_treeGridId).length < 1 || $(_treeGridId) == null) {

		return;
	}
	var _node = $(_treeGridId).treegrid('getSelected');
	if (typeof(_node) == "undefined" || _node == null) {

		return;
	}
	var _level = _node.itemLevel + 1;
	if (_level < 3) {
		$.messager.alert('提示信息', "当前级别下无法添加科目！", 'info');
		return;
	}

	ymPrompt.confirmInfo({
		icoCls : "",
		autoClose : false,
		message : "<div id='ctCostItemNew'><img align='absMiddle' src='" + _ctx
				+ "/images/loading.gif'></div>",
		width : 350,
		height : 250,
		title : "新增加" + _level + "级科目",
		closeBtn : true,
		afterShow : function() {
			var _url = _ctx + "/ct/ct-item!input.action";

			$.post(_url, {}, function(result) {
				$("#ctCostItemNew").html(result);

				$("#itemLevel").val(_level);
				var _childCount = $("label[pid=" + _node.ctItemId + "]").length;
				$("#sequenceNo").val(++_childCount);
				var _sque = buildSque(_childCount, _level, _node.ctItemId);

				$("#sequenceNoText").val(_sque);

				$("#parentId").val(_node.ctItemId);
				$("#ctDimeCd").val(_node.ctDimeCd);
				$("#ctLedgerId").val($("#ctLdId").val());
				$("#ctLandId").val($("#ctLandIdHide").val());
				$("#ctOperationId").val($("#ctOperationIdHide").val());
				$("#ctRecordVersion").val(_node.recordVersion);
				$("#itemName").focus();

			});
		},
		handler : function(btn) {
			$("#itemName").val($.trim($("#itemName").val()));
			if (btn == 'ok') {
				var _itName = $("#itemName").val().trim();

				if ($("#itemName").attr("isAdd") == "true") {

					return;
				}
				if (isEmptyOrNull(_itName)) {

					alert("科目名称不能为空！");

					return;
				}

				if (isPassMaxLen(_itName, 200)) {
					alert("科目名称不能超过200个字符\n\t已超出了["
							+ eval(getByteLen(_itName) - 200) + "]个字符！");
					return;
				}
				$("#itemName").attr("isAdd", "true");
				$('#ctItemForm').ajaxSubmit(function(result) {
					var _jsonData = toJson(result);
					if (_jsonData != undefined && _jsonData.status != undefined) {
						if (_jsonData.status) {
							ymPrompt.close();
							reload();
							$("#itemName").val("");
						} else {

							$.messager
									.alert('提示信息', _jsonData.errorMsg, 'info');

						}
					}
					$("#itemName").attr("isAdd", "false");
				});
			} else {
				ymPrompt.close();
			}

		},
		btn : [["确定", 'ok'], ["取消", 'cancel']]
	});

}
/**
 * 刷新当前节点数据
 */
function reload() {
	var _treeGridId = "#div" + $("#ctOperationIdHide").val();
	var _node = $(_treeGridId).treegrid('getSelected');
	if (null == _node || typeof(_node) == "undefined") {
		return;
	}
	var _itemId = _node.ctItemId;
	var _childCount = $("label[pid=" + _itemId + "]").length;
	if (_childCount < 1) {// 如果当前选中节点之前为叶子节点
		$("#" + _itemId).attr("state", "closed");
		_node.state = "closed";

		var _p = $("#" + _itemId).parent();

		var _div = $(_p).parent();
		var _spanTreeIndents = $(_div).find("span.tree-indent");

		if (_spanTreeIndents.length > 0) {
			var _spanTreeIndent = _spanTreeIndents[0];
			$(_spanTreeIndent).remove();
		}

		// 则需要先在先在其父母节之前增加折叠图标，才能动态刷新叶子节点，使期可以正常显示子节点
		$(_p)
				.before("<span class='tree-hit tree-collapsed'></span><span class='tree-folder-open''></span>");
	}
	if (_node) {
		$(_treeGridId).treegrid('reload', _itemId);
	} else {
		$(_treeGridId).treegrid('reload');
	}
}

/**
 * 刷新父母节点
 */
function reloadParentNode() {
	var _treeGridId = "#div" + $("#ctOperationIdHide").val();
	var _node = $(_treeGridId).treegrid('getSelected');
	if (null == _node || typeof(_node) == "undefined") {
		return;
	}
	var _itemId = _node.ctItemId;
	var _parentItemId = $("#" + _itemId).attr("pid");
	if (!isEmptyOrNull(_parentItemId)) {
		$("#" + _itemId).remove();
		var _childCount = $("label[pid=" + _parentItemId + "]").length;
		if (_childCount < 1) {// 如果当前选中节点之前为叶子节点
			var _span = $("#" + _parentItemId).parent();
			$.each($(_span).prevAll(), function(i, ele) {
						if ($(ele).attr("class").indexOf("tree-hit") > -1) {
							$(ele).remove();
						}

					});

			$(_span).before("<span class='tree-indent'></span>");

		} // 删除成本后刷新父母节点
		$(_treeGridId).treegrid('reload', _parentItemId);
	}

}
/**
 * 删除科目
 */

function ctDelCostItem() {

	var _treeGridId = "#div" + $("#ctOperationIdHide").val();
	if ($(_treeGridId).length < 1 || $(_treeGridId) == null) {

		return;
	}
	var _node = $(_treeGridId).treegrid('getSelected');
	if (null == _node || typeof(_node) == "undefined") {
		return;
	}

	var _itemId = _node.ctItemId;
	var _itemName = $("#" + _itemId).attr("title");
	var _ctContPlanId = _node.ctContPlanId;
	var _level = _node.itemLevel + 1;
	if (_level < 4) {
		$.messager.alert('提示信息', "当前科目不允许删除！", 'info');
		return;
	}
	var _dataText = new StringBuffer();
	var _url = _ctx + "/ct/ct-item!delete.action";
	_dataText.append("ctItemId=");
	_dataText.append(_itemId);
	_dataText.append("&parentId=");
	_dataText.append(_node.parentId);
	_dataText.append("&sequenceNo=");
	_dataText.append(_node.sequenceNo);
	if (!isEmptyOrNull(_ctContPlanId)) {
		$.messager.alert('科目删除提示', "不能执行删除，当前科目已关联到合约规则，请先取消关联！", 'info');
		return;
	}
	if (!confirm("执行此操作将连同子科目一并删除且不可恢复请慎重\n你确认要删除[" + _itemName + "]科目吗？")) {
		return;
	}
	$.ajax({
				url : _url,
				type : "POST",
				data : _dataText.toString(),
				success : function(responseText) {

					var _jsonData = toJson(responseText);

					if (_jsonData != undefined && _jsonData.status != undefined) {

						if (_jsonData.status != true) {
							$.messager
									.alert('提示信息', _jsonData.errorMsg, 'info');
							return;
						} else {
							reloadParentNode();

							$.messager.alert('提示信息', '当前科目已删除成功！', 'info');
							selectItem(_node.parentId);// 选择中父级科目
						}

					}
				},
				error : function(responseText) {
					$.messager.alert('提示信息', _jsonData.errorMsg, 'info');
				}
			});
}

/**
 * 修改科目
 */
function ctModCostItem() {

	var _treeGridId = "#div" + $("#ctOperationIdHide").val();
	if ($(_treeGridId).length < 1 || $(_treeGridId) == null) {

		return;
	}
	var _node = $(_treeGridId).treegrid('getSelected');
	if (typeof(_node) == "undefined" || _node == null) {

		return;
	}
	var _itemId = _node.ctItemId;
	var _level = _node.itemLevel + 1;
	if (_level < 4) {
		$.messager.alert('提示信息', "当前科目不允许修改！", 'info');
		return;
	}

	ymPrompt.confirmInfo({
		icoCls : "",
		autoClose : false,
		message : "<div id='ctCostItemNew'><img align='absMiddle' src='" + _ctx
				+ "/images/loading.gif'></div>",
		width : 350,
		height : 250,
		title : "修改科目",
		closeBtn : true,
		afterShow : function() {
			var _url = _ctx + "/ct/ct-item!input.action";
			$.post(_url, {}, function(result) {
				$("#ctCostItemNew").html(result);

				$("#itemLevel").val(_level);
				var _childCount = $("label[pid=" + _node.ctItemId + "]").length;

				var _sque = buildSque(_node.sequenceNo, _node.itemLevel,
						_node.parentId);
				$("#sequenceNo").val(_node.sequenceNo);
				$("#sequenceNoText").val(_sque);
				$("#parentId").val(_node.ctItemId);
				$("#ctDimeCd").val(_node.ctDimeCd);
				$("#ctLedgerId").val(_node.ctLedgerId);
				$("#ctLandId").val($("#ctLandIdHide").val());
				$("#ctOperationId").val($("#ctOperationIdHide").val());
				$("#itemName").val($("label[itemId=" + _itemId + "]")
						.attr("title"));

				$("#itemName").focus();
			});
		},
		handler : function(btn) {
			if (btn == 'ok') {
				$("#itemName").val($.trim($("#itemName").val().trim()));
				var _itName = $("#itemName").val();
				if ($("#itemName").attr("isAdd") == "true") {

					return;
				}
				if (isEmptyOrNull(_itName)) {

					alert("科目名称不能为空！");

					return;
				}

				if (isPassMaxLen(_itName, 200)) {
					alert("科目名称不能超过200个字符\n\t已超出了["
							+ eval(getByteLen(_itName) - 200) + "]个字符！");
					return;
				}

				var _dataText = new StringBuffer();
				var _url = _ctx + "/ct/ct-item!save.action";
				_dataText.append("ctItemId=");
				_dataText.append($("#parentId").val());
				_dataText.append("&itemName=");
				_dataText.append($("#itemName").val());
				_dataText.append("&ctLedgerId=");
				_dataText.append($("#ctLedgerId").val());
				// 设置状态防止用户重复添加科目
				$("#itemName").attr("isAdd", "true");
				$.ajax({
							url : _url,
							type : "POST",
							data : _dataText.toString(),
							success : function(responseText) {

								var _jsonData = toJson(responseText);

								if (_jsonData != undefined
										&& _jsonData.status != undefined) {

									if (_jsonData.status != true) {

										$.messager.alert('提示信息',
												_jsonData.errorMsg, 'info');
										return;
									} else {
										var _itemName = $("#itemName").val();
										$("#" + _itemId).attr("title",
												_itemName);
										// treeGrid中科目名称控件
										var _itemTitleCtr = $("label[itemId="
												+ _itemId + "]");

										var _sque = buildSque(_node.sequenceNo,
												_node.itemLevel, _node.parentId);
										// 更新科目名称
										$(_itemTitleCtr).html(_sque
												+ "&nbsp;&nbsp;" + _itemName);
										// 更新科目标题
										$(_itemTitleCtr).attr("title",
												_itemName);
										ymPrompt.close();
										$("#itemName").val("");

									}

								}

								$("#itemName").attr("isAdd", "false");
							},
							error : function(responseText) {
								$.messager.alert('提示信息', _jsonData.errorMsg,
										'info');
								$("#itemName").attr("isAdd", "false");
							}
						});
			} else {
				ymPrompt.close();

			}

		},
		btn : [["确定", 'ok'], ["取消", 'cancel']]
	});

}
/**
 * 科目排序
 * 
 * @param {}
 *            isAsc :true,升序;false,降序
 */
function ctDoSortItem(isAsc) {

	var _treeGridId = "#div" + $("#ctOperationIdHide").val();
	if ($(_treeGridId).length < 1 || $(_treeGridId) == null) {

		return;
	}
	var _node = $(_treeGridId).treegrid('getSelected');
	if (typeof(_node) == "undefined" || _node == null) {

		return;
	}
	var _level = _node.itemLevel + 1;
	if (_level < 4) {
		$.messager.alert('提示信息', "当前科目不允许排序！", 'info');
		return;
	}
	var _itemId = _node.ctItemId;
	var _sequenceNo = parseInt(_node.sequenceNo);
	var _newSequenceNo = _sequenceNo;
	switch (isAsc) {
		case true :
			if (isAsc && _sequenceNo == 1) {
				$.messager.alert('提示信息', "当前科目已经到了顶部无法升序！", 'info');
				return;
			}
			_newSequenceNo--;
			break;
		case false :
			var isExsitSque = false;
			_newSequenceNo++;
			// 当前移动科目总的兄弟科目个数
			var _heightSqeuenceNo = $("label[pid=" + _node.parentId + "]").length;
			if (_heightSqeuenceNo >= _newSequenceNo) {
				isExsitSque = true;
			}
			if (!isAsc && !isExsitSque) {
				$.messager.alert('提示信息', "当前科目无法降序，请确认是否有兄弟科目或者是否已到达底部！",
						'info');
				return;
			}
			break;
		default :
			break;
	}

	var _dataText = new StringBuffer();
	var _url = _ctx + "/ct/ct-item!move.action";
	_dataText.append("ctItemId=");
	_dataText.append(_itemId);
	_dataText.append("&parentId=");
	_dataText.append(_node.parentId);
	_dataText.append("&isAsc=");
	_dataText.append(isAsc);
	_dataText.append("&sequenceNo=");
	_dataText.append(_sequenceNo);
	_dataText.append("&newSequenceNo=");
	_dataText.append(_newSequenceNo);
	_dataText.append("&ctLedgerId=");
	_dataText.append($("#ctLdId").val());
	setBtSortStatus(true);// 禁止用按钮
	$.ajax({
				url : _url,
				type : "POST",
				data : _dataText.toString(),
				success : function(responseText) {

					var _jsonData = toJson(responseText);

					if (_jsonData != undefined && _jsonData.status != undefined) {

						if (_jsonData.status != true) {
							$.messager
									.alert('提示信息', _jsonData.errorMsg, 'info');
							return;
						} else {
							reloadParentNode();

							// $.messager.alert('提示信息', '当前科目已移动成功！', 'info');
							selectItem(_itemId);

						}

					}
					setBtSortStatus(false);// 启用按钮
				},
				error : function(responseText) {
					$.messager.alert('提示信息', _jsonData.errorMsg, 'info');
					setBtSortStatus(false);// 启用按钮
				}
			});

}
/**
 * 重新载入节点并选中指定的节点
 * 
 * @param {}
 *            _itemId 节点号
 */
function selectItem(_itemId) {

	if ($("#" + _itemId).length < 1) {

		window.setTimeout("selectItem('" + _itemId + "')", 500);
	}

	var _treeGridId = "#div" + $("#ctOperationIdHide").val();

	var _node = $(_treeGridId).treegrid('select', _itemId);

}

/**
 * 查看历史调整记录
 */
function doSeeHisAdjustRecord() {

	// 当前是变更提交
	ymPrompt.confirmInfo({
		icoCls : "",
		autoClose : false,
		message : "<div id='hisAdjustRecord' style=''><img align='absMiddle' src='"
				+ _ctx + "/images/loading.gif'></div>",
		width : 450,
		height : 350,
		title : "历史调整记录",
		closeBtn : true,
		afterShow : function() {
			var _url = _ctx + "/ct/ct-item!modificationInput.action";

			$.post(_url, {}, function(result) {
						$("#hisAdjustRecord").html($("#hisRemarkDiv").html());

					});
		},
		handler : function(btn) {

			ymPrompt.close();
		},
		btn : [["关闭", 'ok']]
	});

}
function ctContPlanShow(ctLedgerId, detailId) {
	var data = {
		ctLedgerId : ctLedgerId
	};
	$("#ctLandToOperationDivs").hide();
	$("#ctLandNameDiv").hide();
	$("#divButton").hide();
	$("#ctPlanShow").show();
	TB_showMaskLayer("正在搜索请稍后...");
	$.post(_ctx + "/ct/ct-cont-plan!list.action", data, function(result) {
				if (result) {					
					$("#ctPlanShow").html(result);					
					$.each($("div.clsPlan"),function(i,ctplan){
						detailId=$(ctplan).attr("id");					
						return false;						
					});					
					var dom = $('#btn_' + detailId);			
					queryCtContPlan(detailId, dom);					
					if (!isBindEvent()) {
						$("#btn_addContPlan").hide();
					}
					TB_removeMaskLayer();
				}

			});
}
function ctCostItemShow() {

	// 分解目标成本
	$("#btn_dismantleCost").unbind("click");
	unbindEvent();
	bindEvent();// 绑定相关事件
	$("#divButtonLeft").show();
	$("#divButtonCenter").hide();
	$("#btn_dismantleCost").val("分解目标成本");

	$("#btn_dismantleCost").attr("isShowCheck", "false");
	$("#ctPlanShow").hide();
	$("#ctLandToOperationDivs").show();
	$("#ctLandToOperationDivs").show();
	$("#ctLandNameDiv").show();

	if ($("div[ctr=divLands]").length > 0 && isBindEvent()) {
		$("#divButton").show();
	} else {
		$("#divButton").hide();
	}

}