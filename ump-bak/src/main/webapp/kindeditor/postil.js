// 解析批注
var _htmlBuf = new StringBuffer();
var _spanInputCss="background-color: yellow;text-decoration: none;cursor: pointer;";
var _markCss="folat:left; overflow: visible; white-space:wrap;border:0px;width:95%";
function loader() {
	var isHasMark = false;
	var $list = $(".list");
	$list.children().remove();

	var _edit = $("#keEditIframe");
	var _statusCd = $("#isCanInput").val();
	$(_edit).contents().find("ins").find("table").css("background", "yellow");
	$(_edit).contents().find("ins").attr("style",_spanInputCss)
			.each(function(a, b) {
				if (null != $(b).attr("comment") && undefined != $(b).attr("comment")) {
					if ($(b).attr("isdel") == 'true') {
						$(b).css("text-decoration", "line-through").next("br").css("display", "");
					}
					var content = $(b).attr("comment").replace(/‘/ig, "'").replace(/“/ig, "\"");
					if (content != undefined) {
						$(_edit).contents().find("table[tableid="+ $(b).get(0).id + "]").css("background","yellow");
						_htmlBuf = new StringBuffer();
						_htmlBuf.append("<div style='width:100%;vertical-align:top;'>");
						if ($("#isDel").val() != 1){ // 如果当前记录为删除状态(1: 删除)，不可编辑
						//		&& (_statusCd == "30" || _statusCd == "45"  || _statusCd == "40")
						//		&& $(b).attr("isNew") == "1"
						//		&& $("#isCanClose").val() != "0") 
						//		|| $("#displayMarkBtn").val()!='false' ) {
						if($("#displayMarkBtn").val()=='true') {
							_htmlBuf.append("<span class='closeDiv' style='float:right;position:relative;' onclick='removePostil(this)'>&nbsp;</span>");
							_htmlBuf.append("<span class='editDiv' style='float:right;position:relative;' onclick='editPostil(this)'>&nbsp;</span>");
						}
						}
						_htmlBuf.append("<div forid='" + $(b).get(0).id	+ "'  ctrType='mark'");
						_htmlBuf.append("style='"+_markCss+"'>");
						_htmlBuf.append(content + "</div>");
						_htmlBuf.append("</div>");
						var $postil = $(_htmlBuf.toString());
						// 有标注
						isHasMark = true;
						// 右侧原始批注数据鼠标移动事件
						$postil.click(function() {
							clearEditorCss();
							$(this).css("border-color", "red");
							var _edit = $("#keEditIframe");
							var _forid = $(this).find("div").attr("forid");
							$(_edit).contents().find("#" + _forid + "").css("background", "Tomato");
							$(_edit).contents().find("table[tableid=" + _forid+ "]").css("background", "Tomato");
							// 获取标注偏移量Y
							var y = GetObjPos($(_edit).contents().find("#"
									+ _forid + "").get(0))["y"];
							if (y > 100) {
								y = y - 30;
							}

							var contaner = $(_edit).contents()
									.find("html,body");
							contaner.each(function() {
										$(this).animate({
													scrollTop : y
												}, 0);
									});

						}, function() {
							$(this).css("border-color", "#ddd");
							var _edit = $("#keEditIframe");
							var _forid = $(this).find("div").attr("forid");
							$(_edit).contents().find("#" + _forid + "").css("background", "yellow");
							$(_edit).contents().find("table[tableid=" + _forid+ "]").css("background", "yellow");
						});
						$postil.dblclick(function() {// 双击右侧批注列表批注项进行编辑
							//alert($("#displayMarkBtn").val());
							//alert($("#isCanInput").val());
							// 如果当前记录为删除状态(1: 删除)，不可编辑
							if($("#isDel").val() != 1) {
								//var _statusCd = $("#isCanInput").val();
								//if ((_statusCd == "30" || _statusCd == "45" || _statusCd == "40") || _displayMarkBtn!="false") {
								if($("#displayMarkBtn").val()=='true') {
									$("div[ctrType=mark]").attr("isDblclick", "false");
									$(this).find("div").attr("isDblclick","true");
									// 双击修改标注信息
									editor.clickToolbar('modmark');
								}
							}
						});
						$(b).click(function() {
							clearEditorCss();
							$("#markList").show();
							$("#markFillList").hide();
							//$("#oldContent").removeClass("changeList").addClass("select_changeList");
							//$("#oldContent").siblings().removeClass("select_changeList").addClass("changeList");
							fillOldChange("oldContent");
							// 批注鼠标移入
							var _divH = 0;
							var _insId = $(this).attr("id");
							$($("div[ctrType=mark]")).each(function(a, b) {
								if ($(b).attr("forid") == _insId) {
									return false;
								}
								_divH += $(b).get(0).offsetHeight;
							});
							$(this).css("background", "Tomato");
							$(_edit).contents().find("table[tableid="+ $(this).attr("id") + "]").css("background", "Tomato");
							$("div[forid='" + $(this).get(0).id + "']").parent().css("border-color", "red");
							$(".list").get(0).scrollTop = _divH;
						}, function() {// 批注鼠标移出
							$(this).removeClass().addClass("postil");
							$(this).css("background", "yellow");
							$(_edit).contents().find("table[tableid="+ $(this).attr("id") + "]").css("background", "yellow");
							$("div[forid='" + $(this).get(0).id + "']").parent().css("border-color","#ddd");
						});
						$(b).dblclick(function() {
							$div = $("div[forid='" + $(this).attr("id")+ "']").parent();
							$div.dblclick();
						});
						$list.append($postil);
					}
				}
			});
	//如果有标注则默认查看标注
	if (isHasMark) {
        $("#markList").show();
		$("#markFillList").hide();
		//$("#oldContent").removeClass("changeList").addClass("select_changeList");
		//$("#oldContent").siblings().removeClass("select_changeList").addClass("changeList");
		fillOldChange("oldContent");
									
	}
	$(".sc-content-right").show();
}

/**
 * liwei3
 * 点击批注列表里的编辑图标进入批注编辑栏
 */
function editPostil(arg) {
	var $div = $(arg).parent();
	$div.dblclick();
}

/**
 * 删除批注
 */
function removePostil(arg) {
	// 定义参数集
	ymPrompt.parames = {};
	// 保存参数
	ymPrompt.parames.arg = arg;
	ymPrompt.confirmInfo({
		message : '你确定要删除当前批注信息吗？',
		title : "删除批注",
		handler : function(tp) {
			if (tp == 'ok') {
				var $div = $(ymPrompt.parames.arg).next().next("div");
				var _markId = $div.attr("forid");
				var _edit = $("#keEditIframe");
				var $source = $(_edit).contents().find("#" + _markId + "");
				var sourceHtml = $source.attr("comment").replace(/‘/ig, "'")
						.replace(/“/ig, "\"");
				$($div).parent().remove();
				$($source).after(sourceHtml).remove();
				var _content = "";
				var statusContent = "<span style='color:red;'>批注删除成功</span>";
				if (editor != null && editor != undefined) {
					_content = "" + editor.html();
				}
				$.post(_ctx	+ "/sc/sc-contract-templet-info!delHistoryMark.action",
					{
						scContractId : $("#scContractId").val(),
						hisConId : $("#hisContId").val(),
						markId : _markId,
						actType : "del",
						content : _content

					}, function(result) {
									var _resultJson = toJson(result);
									if (_resultJson != undefined
											&& _resultJson.status != undefined) {
										if (_resultJson.status != true) {
											alert(_resultJson.errorMsg);
										}
									} else {
										alert("无法解析返回的信息！");
									}

								});
				$("#contractStatus").html(statusContent);
			}
		}
	});

}

/**
 * 坐标
 * 
 * @param x
 * @param y
 * @return
 */
function CPos(x, y) {
	this.x = x;
	this.y = y;
}
/**
 * 得到对象的相对浏览器的坐标
 * 
 * @param ATarget
 * @return
 */
function GetObjPos(ATarget) {
	var target = ATarget;
	if (target == undefined || target == null) {
		return new CPos(0, 0);

	}
	var pos = new CPos(target.offsetLeft, target.offsetTop);

	var target = target.offsetParent;
	while (target) {
		pos.x += target.offsetLeft;
		pos.y += target.offsetTop;

		target = target.offsetParent
	}
	return pos;
}
