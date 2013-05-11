/**
 * 目标成本台账模块脚本
 */

$("document").ready(function() {
	resetLeftPanel();
	loadAreaProjectTree();
	$("#billContentDiv").hide();

	$("#newBnt").bind("click", function() {

				if ($("#billContentDiv").css("display") == "none") {
					$("#billContentDiv").show();
				} else {
					$("#billContentDiv").hide();
				}

				$("#quickBidSenior").removeClass("quickSeniorHover");
				$("#seniorPanel").hide();
			});
	// 项目
	$('#projectName').orgSelect({
				showProjectOrg : true
			});
	$("#btSave").bind("click", function() {
		if (isEmpty($("#projectName").val())) {

			alert("项目名称不能为空！");
			$("#projectName").focus();
			return;
		}
		var _costTargetTotalAmt = $("input[index=3]");
		if (isEmpty($(_costTargetTotalAmt).val())) {

			alert("目标成本总额不能为空！");
			$(_costTargetTotalAmt).focus();
			return;
		}

		$.each($("input[ctrType=num]"), function(i, ctr) {
					var _val = $(ctr).val();
					$("input[ctrNum=num" + $(ctr).attr("index") + "]").val(_val
							.replace(/,/g, ""));

				});

		TB_showMaskLayer("请稍等，正在添加台账...");
		$("#templetForm").ajaxSubmit(function(result) {
					TB_removeMaskLayer();
					$("#billContentDiv").hide();
					jumpPage(1);
				});
	});
	$("#btCollapse").bind("click", function() {

				$("#billContentDiv").hide();
			});
});

// 快速搜索事件
function searchSenior() {
	$("#billContentDiv").hide();
	if ($("#seniorPanel").css("display") == "none") {
		$("#quickBidSenior").addClass("quickSeniorHover");
		$("#seniorPanel").show();
		// $("#tableDiv").height($("#tableDiv").height()-$("#rightHeadTool").height()-15);
	} else {

		$("#quickBidSenior").removeClass("quickSeniorHover");
		$("#seniorPanel").hide();
		resetSeniorPanel();
		// $("#tableDiv").height($("#tableDiv").height()+$("#rightHeadTool").height()+15);
	}
}
// 主页面
// 加载区域项目树
var gTreePanel;
function loadAreaProjectTree() {
	var url = _ctx + '/ct/ct-ledger!getAreaProjectTree.action';
	$.post(url, {}, function(result) {

				gTreePanel = new TreePanel({
							renderTo : 'leftTreePanel',
							'root' : eval('(' + result + ')'),
							'ctx' : _ctx
						});
				gTreePanel.render();
				gTreePanel.isExpendSelect = true;
				gTreePanel.on(function(node) {
							var nodeType = node.attributes.nodeType;
							// 若非根节点
							if ("0" != nodeType) {
								if (node.isExpand) {
									node.collapse();
								} else {
									node.expand();
								}
							}
						});

				// 搜索第一页
				jumpPage(1);
			});
}

// 重新计算高度
function resetLeftPanel() {
	var docHeight = $(document).height();
	var headHeight = $('#bodyHead').height();
	var firstFlg = $('#leftTreePanel').attr('firstFlg');
	if (firstFlg != 'yes') {
		$('#leftTreePanel').attr('docHeight', docHeight);
		$('#leftTreePanel').attr('firstFlg', 'yes');
	} else {
		headHeight = $('#leftTreePanel').attr('docHeight');
	}
	$('#leftTreePanel').height(docHeight - headHeight);
}
// 判断是否为数字
function isNum(txt) {

	if ("" == $.trim(txt)) {

		return true;
	}
	var reg = /^((\d*[1-9]*)|([0]))(\.\d+)?$/;
	return reg.test($.trim(txt));
}
function isEmpty(txt) {
	if ("" == $.trim(txt)) {

		return true;
	}
	return false;

}
// 通过表单搜索条件搜索列表
function jumpPage(pageNo) {
	if (typeof(pageNo) == 'undefined' || (pageNo == '')) {
		$("#pageNo").val(1);
	} else {
		$("#pageNo").val(pageNo);
	}

	// 选中项目
	var arrProjects = (gTreePanel.getModifiedLeafNodes('project'))[1];// 后台直接是项目的名称故获取[1]
	var arr = new Array();
	for (var i = 0; i < arrProjects.length; i++) {
		arr.push(arrProjects[i]);
		arr.push(',');
	}
	var strProjects = arr.join('');
	$('#selectItemCds').val(strProjects);

	// 状态
	var bidStatusCd = '';
	$('#quickClickPanel span[class*=quickBidItem-click]').each(function(i) {
				bidStatusCd += $(this).attr("typeCd") + ",";
			});
	$("input[name=bidStatusCd]").val(bidStatusCd);

	if (!isNum($("#ctTotalArea1").val())) {
		alert('总面积起始值数据输入不合法！');
		$("#ctTotalArea1").val("");
		$("#ctTotalArea1").focus();
		return;

	}
	if (!isNum($("#ctTotalArea2").val())) {
		alert('总面积结束值数据输入不合法！');
		$("#ctTotalArea2").val("");
		$("#ctTotalArea2").focus();
		return;

	}
	/*
	 * if(!isEmpty($("#ctTotalArea1").val()) &&
	 * !isEmpty($("#ctTotalArea2").val())) { if(eval($("#ctTotalArea1").val())>
	 * eval($("#ctTotalArea2").val())) {
	 * 
	 * 
	 * alert('总面积结束值范围不能小于总面积起始值！'); $("#ctTotalArea2").val("");
	 * $("#ctTotalArea2").focus(); return; } }
	 */
	if (!isNum($("#ctTargetTotalAmt").val())) {

		alert('目标总额起始数据输入不合法！');
		$("#ctTargetTotalAmt").val("");
		$("#ctTargetTotalAmt").focus();
		return;
	}
	if (!isNum($("#ctTargetTotalAmt1").val())) {

		alert('目标总额结束数据输入不合法！');
		$("#ctTargetTotalAmt1").val("");
		$("#ctTargetTotalAmt1").focus();
		return;
	}
	if (!isNum($("#ctRateUnitAmt").val())) {
		alert('分解总额起始数据输入不合法！');
		$("#ctRateUnitAmt").val("");
		$("#ctRateUnitAmt").focus();

		return;
	}
	if (!isNum($("#ctRateUnitAmt1").val())) {
		alert('分解总额结束数据输入不合法！');
		$("#ctRateUnitAmt1").val("");
		$("#ctRateUnitAmt1").focus();

		return;
	}

	TB_showMaskLayer("正在搜索...");

	$.each($("input"), function(i, input) {

				$(input).val($.trim($(input).val()));
			});
	$("#mainFormSearch").ajaxSubmit(function(result) {
				TB_removeMaskLayer();
				$("#searchResultPanel").html(result);
			});
}
// 跳转至给定的页面,配合前台的分页搜索
function jumpPageTo() {
	var index = $("#pageTo").val();
	var t = parseInt(index);
	if (t > 0) {
		jumpPage(t);
	}
}

// 通过表单搜索条件搜索列表
function searchBidLedger() {
	jumpPage(1);
}
// 清空搜索条件
function resetSeniorPanel() {
	$(':input', '#seniorPanel').val('');
	$('.quickBidItem').css('color', '#0167A2');
	$('#sort').val('');
	$('#order').val('');
	$('.sortField').removeClass('sortFieldDesc').removeClass('sortFieldAsc');
}
// 目标成本台账明细
function loadCtLedgerDetail(ctLedgerId) {
	openWindow('ctLedgerDetail', '台账详情', _ctx
					+ '/ct/ct-ledger!detail.action?id=' + ctLedgerId);
}
// 业态编辑页面
function loadCtOperation() {
	openWindow('ctBaseOperationList', '业态详情', _ctx
					+ '/ct/ct-base-operation!list.action');
}
// 打开窗口
function openWindow(id, desc, url) {
	if (window.parent && window.parent.parent.TabUtils) {
		window.parent.parent.TabUtils.newTab(id, desc, url);
	} else {
		window.open(url);
	}
}
// 下载附件(项目成本分类作业指引，合约规划创作指引)
function downloadAttach(attachId) {

}