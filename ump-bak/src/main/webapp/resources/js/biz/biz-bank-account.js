$(function() {

	// 回车事件绑定
	enterEvent("companyName_search");
	enterEvent("bankName_search");
	enterEvent("bankNo_search");
	
	jumpPage("1");
});

function showNewPanel() {
	if($("#new_account_panel").css("display") == "none") {
		$("#new_account_panel").show();
		$("#content_panel").hide();
	} else {
		$("#new_account_panel").hide();
		$("#content_panel").show();
	}
}

var lastShowPanelId = ""; // 记录上一次打开的编辑页面ID
function showEditPanel(id) {
	if($("#edit_account_panel_" + id).css("display") == "none") {
		if(lastShowPanelId) {
			$(lastShowPanelId).hide();
		}
		$("#edit_account_panel_" + id).show();
		lastShowPanelId = "#edit_account_panel_" + id;
	} else {
		$("#edit_account_panel_" + id).hide();
	}
}

function validateNewInput() {
	var valdt = true; // 是否通过校验
	$.each($("input[required='required']"), function(k, v) {
		if($.trim($(v).val()) == "") { // 有空值，则不通过
			$(v).css("border", "1px solid red");
			valdt = false;
		} else {
			$(v).css("border", "1px solid #7F9DB9");
		}
	});
	return valdt;
}

function validateEditInput(id) {
	var valdt = true; // 是否通过校验
	$.each($("#edit_account_panel_" + id).find("input[type=text]"), function(k, v) {
		if($(v).val() == "") { // 有空值，则不通过
			$(v).css("border", "1px solid red");
			valdt = false;
		} else {
			$(v).css("border", "1px solid #7F9DB9");
		}
	});
	return valdt;
}

/***
 * 回车事件绑定
 * @param tagId 元素ID
 */
function enterEvent(tagId) {
	$("#" + tagId).keydown(function(e){
		if(e.keyCode == 13) {
			jumpPage("1");
		}
	});
}

function jumpPageTo() {
	var pageNo = $("#pageTo").val();
	jumpPage(pageNo);
}

function jumpPage(pageNo) {
	$("#new_account_panel").hide();
	$("#content_panel").show();
	if(pageNo) {
		$("#pageNo").val(pageNo);
	} else {
		$("#pageNo").val("1");
	}
	TB_showMaskLayer("正在搜索...");
	var url = _ctx + "/biz/biz-bank-account!list.action";
	$("#search_account_form").attr("action", url).ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		$("#content_panel").html(result);
	});
}

function exportAccount() {
	var url = _ctx + "/biz/biz-bank-account!exportTemplate.action";
	TB_showMaskLayer("正在导出...",2000);
	location.href = url;
}

function importAccount() {
	if(!$("#importFile").val()) {
		alert("请选择导入数据Excel模板！");
		$("#importFile").focus();
		return;
	}
	var result = confirm("导入数据将会清除已有的全部数据！\n确定要导入数据吗？");
	if(result) {
		var url = _ctx + "/biz/biz-bank-account!importData.action";
		$("#search_account_form").attr("action", url).ajaxSubmit(function(result) {
			var index = result.toLowerCase().indexOf("<pre>");
			if(index != -1) {
				result = result.substring(index + 5, result.toLowerCase().indexOf("</pre>"));			
			}
			result = eval("(" + result + ")");
			//alert("success : " + result["success"] + "\ncount : " + result["count"] + "\nsecond : " + result["second"]);
			if(result["success"] == "success") {
				alert("成功导入" + result['count'] + "条数据！\n共耗时" + result['second'] + "秒钟");
				url = _ctx + "/biz/biz-bank-account!bankEnter.action";
				$("#search_account_form").attr("action", url).submit();
			} else if(result["format_error"] == "format_error") {
				alert("请确认标题列的顺序依次是：\n1：\"开户公司\"  2：\"银行名称\"  3：\"银行账号\"！");
			} else if(result["error"]) {
				alert("数据导入异常！");
			}
			//jumpPage("1");
		});
	}
}

function saveAccount() {
	if(validateNewInput()) {
		$("#new_account_form").submit();
	}
}

function editAccount(id) {
	if(validateEditInput(id)) {
		$("#edit_account_form_" + id).ajaxSubmit(function(result) {
			result = eval("("+result+")");
			if(result) {
				$("#td_bankNo_" + id).html(result["bankNo"]);
				$("#td_bankName_" + id).html(result["bankName"]);
				$("#td_companyName_" + id).html(result["companyName"]);
				
				var rv = $("#recordVersion").val();
				$("#recordVersion").val(parseInt(rv) + 1);
			}
		});
	}
}

function deleteAccount(id) {
	var result = confirm("删除后不可恢复\n确定要删除此数据吗？");
	if(result) {
		$("#bizBankAccountId").val(id);
		$("#del_account_form").submit();
	}
}