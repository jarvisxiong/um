$(function() {

	// 回车事件绑定
	enterEvent("srh_applyOrgName");
	enterEvent("srh_userName");
	enterEvent("srh_statusCd");
	
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
	$("#search_account_form").submit();
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

function deleteRecord(id) {
	var result = confirm("删除后不可恢复\n确定要删除此数据吗？");
	if(result) {
		$("#resBeanSpecialRecordId").val(id);
		$("#del_account_form").submit();
	}
}
function confirmRecord(id) {
	var result = confirm("确认付款？");
	if (result) {
		$.post(_ctx + '/res/res-bean-special-record!confirm.action', {
			id : id
		}, function(result) {
			if (result == 'success') {
				jumpPage();
			} else {
				alert("操作失败！");
			}
		});
	}
}