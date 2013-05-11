// 如果字符串超过指定长度，自动截断并用...代替后面的文本
function cutoffStr(str, num) {
	str = jQuery.trim(str);
	if(str.length <= num) {
		return str;
	} else {
		return str.substring(0, num) + "...";
	}
}
//当点击标题时如果后面有new的图标，则把该图标移除
function removeNewImg(subj) {
	//$(subj).siblings(".new_img").hide();
	var newImg = $.find('.new_img',subj);
	if(newImg!==undefined){ $(newImg).hide(); }
}

//打开公告信息
function openNotify(url, notifyId, isReaded) {
	window.parent.TabUtils.newTab('notifydetail','查看公告',url);
	// 如果当前公告是未读，则往后台发一条消息设置为已读
	if (isReaded == "-1") {
		$.get("${ctx}/oa/oa-notify!detail.action?id=" + notifyId);
	}
}
// 设置全部已读
function readAll(url, formId) {
	$.post(url, function(result) {
		if (result == "succ") {
			$("#" + formId).submit();
		}
	});
	return false;
}

$(function() {
	$("tr.data").mouseover(function() {
		$(this).find("*").css("color", "#FFF").css("background-color", "#9a9fa6");
	});
	
	$("tr.data").mouseout(function() {
		$(this).find("*").css("color", "#000").css("background-color", "#FFF");
	});
	
	$("#mainForm").submit(function() {
		var totalPageNo = parseInt($("#totalPageNo").val());
		var pageTo = parseInt($("#pageTo").val());
		if (pageTo > totalPageNo) {
			return false;
		}
	});
	
	// 对发布范围限制最多显示10个字
	$(".to_depts").each(function() {
		var oldText = $(this).html();
		$(this).html(cutoffStr(oldText, 12));
	});
});