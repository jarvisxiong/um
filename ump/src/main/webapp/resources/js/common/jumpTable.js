function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	$("#mainForm").submit();
}
function jumpPageTo() {
		var index = $("#pageTo").val();
		index = parseInt(index);
		if (index > 0) {
			jumpPage(index);
		}
}

function sort(orderBy, defaultOrder) {
	if ($("#orderBy").val() == orderBy) {
		if ($("#order").val() == "") {
			$("#order").val(defaultOrder);
		} else if ($("#order").val() == "desc") {
			$("#order").val("asc");
		} else if ($("#order").val() == "asc") {
			$("#order").val("desc");
		}
	} else {
		$("#orderBy").val(orderBy);
		$("#order").val(defaultOrder);
	}

	$("#mainForm").submit();
}
function resetToEmpty(){
	var re = /^filter/;
	$("#mainForm :input").each(function (i,dom){
		if(re.test(dom.name)){
			$(dom).val("");
		}
	});
}
function search() {
	$("#order").val("");
	$("#orderBy").val("");
	$("#pageNo").val("1");

	$("#mainForm").submit();
}

function isNum(value) {
	return /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(value);
}
function toggleBox(domObj) {
	var flag = $(domObj).attr("checked");
	$("input[name='ids']").each(function(i, dom) {
		$(dom).attr("checked", flag);
	});
}
function deleteChecked(url) {
	var checkbox_ids = new Array();
	$("input[name='ids']:checked").each(function(i, dom) {
		checkbox_ids.push("ids=" + $(dom).val());
	});
	if(checkbox_ids.length == 0){
		alert("请勾选需要删除的数据");
		return false;
	}
	if (confirm("确定删除所选？")) {
		var param = checkbox_ids.join("&");
		$.post(url, param, function() {
			window.location.href = window.location.href;
		});
	}
}