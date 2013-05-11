/*******************************************************************************
 * KindEditor - WYSIWYG HTML Editor for Internet Copyright (C) 2006-2011
 * kindsoft.net
 * 
 * @author Roddy <luolonghao@gmail.com>
 * @site http://www.kindsoft.net/
 * @licence http://www.kindsoft.net/license.php
 ******************************************************************************/
var _Inputcss = "display:-moz-inline-box;display: inline-block; overflow: visible; white-space:nowrap; vertical-align:bottom;cursor:pointer; width:";
var mySelfDialog;

KindEditor.plugin('datamarkinput', function(K) {
	var self = this, name = 'datamarkinput', css = 'background-color: yellow;text-decoration: none;cursor: default;';
	self.plugin.datamarkinput = {
		edit : function() {
			var lang = self.lang(name + '.'), html = '<div style="padding:10px 20px;">' + '<div class="ke-dialog-row">'
					+ '<label for="markDesc" id="descript" name="descript">' + lang.descript + '</label>'
					+ '<input type="text" name="datamarkinput" id="datamarkinput" width="200px" maxlength="100"/></div></div>',
			dialog = self.createDialog( {
				name : name,
				width : 400,
				title : self.lang(name),
				body : html,
				noBtn: {
					name : self.lang('no'),
					click : function(e) {
						self.hideDialog();
					}
				},
				yesBtn : {
					name : self.lang('yes'),
					click : function(e) {
						//是否修改合同总价
						var _isAllPrice = false;
						_val = innputBox.val();
						if ($(_inputCtr).attr("allPrice")) {
							_val = _val.toFloat();
								
							// 自动关联合同总价
							$("#contractPrice").val(_val);
							_isAllPrice = true;
						}
						$(_inputCtr).val(_val);
						$(_inputCtr).html(_val);
									
						// 如果是批注状态则用post更新数据
						if ($("#isMark").val() == "true") {
							$.post(_ctx + "/sc/sc-contract-templet-info!updateTempletFill.action", {
								id : $(_inputCtr).attr("id"),
									isAllPrice : _isAllPrice,
									value : _val
								}, function(result) {
									//重新加载右侧填空列表数据
									fillList();
							});
						}
						if (_val.trim() != "") {
							var _len = $(_inputCtr).attr("len");
							if (_len == undefined) {
								_len = 10;
							}
							_len += "px";
							if (_val.length > 9) {
								_len = "auto";
							}
							var _style = _Inputcss + _len + ";border-bottom:1px solid #000;background:#DEE1DD;";
							$(_inputCtr).attr("style", _style);
						} else {
							$(_inputCtr).html("&nbsp;&nbsp;");
						}
						if (_val.length > 9) {// 如果输入的值超过9个字符则根据内容让其自动调整宽度
							$(_inputCtr).css("width", "auto");
						} else {
							// 否则使用用户定义的长度
							$(_inputCtr).css("width", $(_inputCtr).attr("len") + "px");
						}
						
						//liwei3 add 保存修改填空后合同文本到html里
						var _content = "";
						if (editor != null && editor != undefined) {
							_content = "" + editor.html();
						}
						
						$.post(_ctx + "/sc/sc-contract-templet-info!delHistoryMark.action", 
							{scContractId : $("#scContractId").val(),
							 hisConId : $("#hisContId").val(),
							 content : _content
							}, function(result) {
								var _resultJson = toJson(result);
								if (_resultJson != undefined && _resultJson.status != undefined) {
									if (_resultJson.status != true) {
										alert(_resultJson.errorMsg);
									}
								} else {
									alert("无法解析返回的信息！");
								}
							}
						);
						
						
						$("#contractStatus").html("<span style='color:red;'>填空成功</span>");
						self.hideDialog();
						return;
					}
				}
			}), 
			div = dialog.div, 
			innputBox = K('input[name="datamarkinput"]', div), 
			descriptSpan = K('label[name="descript"]', div);

			var _edit = $("#markFillList");
			// 右侧显示区
			var _inputCtrRightDiv = $("#markFillList").find("div[isDblclick=true]");
				
			// 输入控件
			var _inputCtr = $("#keEditIframe").contents().find("span[id=" + $(_inputCtrRightDiv).attr("forid") + "]");

			var _val = $(_inputCtrRightDiv).attr("value").trim();
			if (_val) {
				innputBox.val(_val);
			}
			if ($(_inputCtr).attr("title") != "") {
				$(descriptSpan).html($(_inputCtr).attr("title"));
			}
			$(innputBox).focus();
			if ($(_inputCtr).attr("allPrice") != "1") {
				var _delBut = '<span class="ke-button-common ke-button-outer " title="删除">' 
						+'<input class="ke-button-common ke-button" type="button" value="删除" onclick="delDataInput(\'' 
						+ $(_inputCtr).attr("id") + '\');	mySelfDialog.hideDialog();"></span>&nbsp;';

				$(".ke-dialog-footer").append(_delBut);
			}
			mySelfDialog = self;
		}
	};

	// 如果当前记录为删除状态(1: 删除)，不可编辑
	if($("#isDel").val() != 1) {
		self.clickToolbar(name, self.plugin.datamarkinput.edit);
	}
});

function delDataInput(inputId) {
	ymPrompt.params = {};
	ymPrompt.params.inputId = inputId;

	ymPrompt.confirmInfo({
		message : '删除不可恢复,你确认要删除当前添空信息吗？',
		title : "填空删除",
		handler : function(tp) {
			if (tp == 'ok') {
				// alert(ymPrompt.params.inputId);
				$(_editor).contents().find("#" + ymPrompt.params.inputId).remove();
				var _content = "";
				if (editor != null && editor != undefined) {
					_content = "" + editor.html();
				}
				$.post(_ctx + "/sc/sc-contract-templet-info!delHistoryMark.action", 
					{scContractId : $("#scContractId").val(),
					 hisConId : $("#hisContId").val(),
					 content : _content
					}, function(result) {
						var _resultJson = toJson(result);
						if (_resultJson != undefined && _resultJson.status != undefined) {
							if (_resultJson.status != true) {
								alert(_resultJson.errorMsg);
							} else {
								var statusContent = "<span style='color:red;'>填空区域删除成功！</span>";
								$("#contractStatus").html(statusContent);
								fillList();
							}
						} else {
							alert("无法解析返回的信息！");
						}
					}
				);
			} else {
				// 重新加载右侧填空列表数据
			}
		}
	});
}

String.prototype.toFloat = function() {
	// 用正则表达式将前后空格
	var val = this.replace(/[^\d\.-]/g, "");
	val = val.replace(/^\./g, "");
	val = val.replace(/\.{2,}/g, ".");
	val = val.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
	return val;
}