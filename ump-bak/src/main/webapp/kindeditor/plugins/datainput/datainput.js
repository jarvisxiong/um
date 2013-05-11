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
KindEditor.plugin('datainput', function(K) {
	var self = this, name = 'datainput', css = 'background-color: yellow;text-decoration: none;cursor: default;';
	self.plugin.datainput = {
		edit : function() {// ' + lang.descript + '

			var lang = self.lang(name + '.'), html = '<div style="padding:10px 20px;">' + '<div class="ke-dialog-row">'
					+ '<span for="markDesc" id="descript" name="descript" style="font-weight:bold;"></span><span id="defaultVal"></span><br/>'
					+ '<input type="text" name="datainput" id="datainput" style="width:300px !important;" maxlength="100" /></div>' + '</div>', dialog = self
					.createDialog( {
						name : name,
						width : 400,
						title : self.lang(name),
						height : 150,
						body : html,
						yesBtn : {
							name : self.lang('yes'),
							click : function(e) {
								doSureInput();
							}
						}
					}), div = dialog.div, innputBox = K('input[name="datainput"]', div), descriptSpan = K('span[name="descript"]', div), defaultVal = K(
					'span[id="defaultVal"]', div);

			var _edit = $("#keEditIframe");
			var _inputCtr = $(_edit).contents().find("span[isClick=true]");
			var _val = $(_inputCtr).val().trim();
			var _len = $(_inputCtr).attr("len");
			if (_len == undefined) {
				_len = 20;
			}
			_len += "px";
			$("#datainput").css("width", _len);
			if (_val) {

				// 初始化默认值
				defaultVal.html(_val);
				if (_inputCtr.attr("isHasInput") == "1") {
					innputBox.val(_val);
				}

			}
			if ($(_inputCtr).attr("title") != "") {
				$(descriptSpan).html($(_inputCtr).attr("title") + ":");
			}
			$(innputBox).focus();

			var _skipBtn = '<span class="ke-button-common ke-button-outer skipbtn" title="不填此项" >';
			    _skipBtn += '<input class="ke-button-common ke-button " type="button" id="skipbtn" value="不填此项" onclick="skipDataInput(\'' + $(_inputCtr)
					.attr("id") + '\');	mySelfDialog.hideDialog();"></span>&nbsp;';

			if ($("#skipbtn").length == 0) {
				// 增加跳过按钮
				$(".ke-dialog-footer").append(_skipBtn);
			}

			mySelfDialog = self;

			// 输入框回车事件
			$("#datainput").keypress(function(e) {
				if (e.which == 13)
					doSureInput();
			});

			function doSureInput() {
				_val = innputBox.val();
				//是否修改合同总价
				var _isAllPrice=false;
				if($(_inputCtr).attr("allPrice")){
					_val=_val.toFloat();
                 //自动关联合同总价
                  $("#contractPrice").val(_val);
                  _isAllPrice = true;
				}
				$(_inputCtr).val(_val);
				$(_inputCtr).html(_val);
				// 如果是批注状态则用post更新数据
				if ($("#isMark").val() == "true") {
					
					$.post(_ctx + "/sc/sc-contract-templet-info!updateTempletFill.action", {
						id : $(_inputCtr).attr("id"),
						isAllPrice:_isAllPrice,
						value : _val
					}, function(result) {
						var rVal = "";
						rVal += _val;
						$("#markFillList").find("div[forid=" + $(_inputCtr).attr("id") + "]").attr("value", rVal).html(rVal);
					});
					// 重新加载右侧填空列表数据
					fillList();
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
				// 是否已经编辑值
				$(_inputCtr).attr("isHasInput", "1");
				$("#contractStatus").html("<span style='color:red;'>填空成功</span>");
				self.hideDialog().focus();
				// 点确定后自动跳到下个填空
				doReNext();
				return;

			}

		}

	}
	self.clickToolbar(name, self.plugin.datainput.edit);
});

/**
 * 跳过当前输入框
 * 
 * @param inputId
 *            输入框ID
 * @return
 */
function skipDataInput(inputId) {
	var _edit = $("#keEditIframe");
	var _inputCtr = $(_edit).contents().find("span[isClick=true]");
	var _val = _inputCtr.val();
	var _len = $(_inputCtr).attr("len");
	if (_len == undefined) {
		_len = 10;
	}
	_len += "px";
	if (_val.length > 9) {
		_len = "auto";
	}
	var _style = _Inputcss + _len + ";border-bottom:1px solid #000;background:#DEE1DD;";

	// 增加跳过属性
	$(_inputCtr).attr("style", _style).attr("isSkip", "1").attr("isReSkip", "1");
	if (isSumitDoNext) {
		doNext();//如果是提交时调用的下一个，则不需要从头开始搜索
	} else {
		doReNext();

	}

}
String.prototype.toFloat = function() {
	// 用正则表达式将前后空格

	 var val=this.replace(/[^\d\.-]/g, "");
	val = val.replace(/^\./g, "");
	val = val.replace(/\.{2,}/g, ".");
	val = val.replace(".", "$#$").replace(/\./g, "").replace("$#$",
			".");
	return val;
	
}
