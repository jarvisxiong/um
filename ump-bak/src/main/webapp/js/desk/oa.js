// 如果字符串超过指定长度，自动截断并用...代替后面的文本
function cutoffStr(str, num) {
	str = jQuery.trim(str);
	if(str.length <= num) {
		return str;
	} else {
		return str.substring(0, num) + "...";
	}
}

$(function() {
	$("#selectAll").click(function() {
		if ($(this).attr("checked")) {
			$("#content input:checkbox").attr("checked", true);
		} else {
			$("#content input:checkbox").attr("checked", false);
		}
	});
	
	$("#delAll").click(function(event) {
		if ($("#content input:checkbox[checked=true]").length == 0) {
			alert("请选择一条记录");
		} else {
			var actionUrl = "";
			var moduleName = $("input:hidden[name='biz_module']").val();
			if (moduleName == "notify") {
				actionUrl = _ctx+"/oa/oa-notify!deleteBatch.action";
			} else if (moduleName == "news") {
				actionUrl = _ctx+"/oa/oa-news!deleteBatch.action";
			}
			$("#mainForm").attr("action", actionUrl).submit();
		}
		return false;
	});
	
	$(".subject").each(function() {
			var oldText = $(this).html();
			$(this).html(cutoffStr(oldText, 20));
		}
	);
	
	$(".to_depts").each(function() {
			var oldText = $(this).html();
			$(this).html(cutoffStr(oldText, 12));
		}
	);
	
	$(".anniu_lan_2").each(function() {
		$(this).mouseover(function() {
			$(this).removeClass("anniu_lan_2").addClass("anniu_lan_2_an");
		});
		$(this).mouseout(function() {
			$(this).removeClass("anniu_lan_2_an").addClass("anniu_lan_2");
		});
	});
	$(".anniu_lan_4").each(function() {
		$(this).mouseover(function() {
			$(this).removeClass("anniu_lan_4").addClass("anniu_lan_4_an");
		});
		$(this).mouseout(function() {
			$(this).removeClass("anniu_lan_4_an").addClass("anniu_lan_4");
		});
	});
	$(".anniu_lan_6").each(function() {
		$(this).mouseover(function() {
			$(this).removeClass("anniu_lan_6").addClass("anniu_lan_6_an");
		});
		$(this).mouseout(function() {
			$(this).removeClass("anniu_lan_6_an").addClass("anniu_lan_6");
		});
	});
});