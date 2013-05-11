var config;
function saveComment() {
	var comment = $("#comment").val();
	if (comment != '') {
		$("#inputFromComment").ajaxSubmit(function(result) {
			$("#divComment").html(result);
			if (config['styleCall'])
				config['styleCall']();

		});
	}
}

function doBack(taskId) {
	var comment = $("#comment").val();
	window.location.href = config['ctx']
			+ '/jbpm/jbpm-reimburse-flow!back.action?taskId=' + taskId
			+ '&comment=' + comment + "&isDesk=" + config['isDesk'];
}
function createDetl(id) {
	var options = {
		beforeSubmit : function(formArray, jqForm) {
			var pdv = new Validate(jqForm.attr("id"));
			if (!pdv.validate()) {
				return false;
			}
		},
		success : function(result) {
			$("#divDetail").html(result);
			if (config['styleCall'])
				config['styleCall']();

		}
	};
	$("#createFormDetl").ajaxSubmit(options);
	if (config['styleCall'])
		config['styleCall']();
	saveSum(id);
}
function editDetl(appRuleId, id) {
	var options = {
		beforeSubmit : function(formArray, jqForm) {
			var pdv = new Validate(jqForm.attr("id"));
			if (!pdv.validate()) {
				return false;
			}
		},
		success : function(result) {
			$("#divDetail").html(result);
			if (config['styleCall'])
				config['styleCall']();

		}
	};
	$("#editFormDetl_" + appRuleId).ajaxSubmit(options);

	saveSum(id);
}

function saveSum(id) {
	var amountFee = $("#amountFee").val();
	$.post(config['ctx'] + "/jbpm/jbpm-reimburse-flow!save.action", {
		id : id,
		amountFee : amountFee,
		isSum : '1'
	}, function(result) {
		if (result) {
			$("#recordVersionReimburse").val(result);
		}
	});
}
function deleteDetl(detailId) {
	$.post(config['ctx'] + "/jbpm/jbpm-reimburse-flow!deleteDetail.action", {
		subId : detailId,
		id :  config['jbpmReimburseId'],
		isEditDetl : 1
	}, function(result) {
		if (result) {
			$("#detail_" + detailId).remove();
			$("#main_" + detailId).remove();
			var size = $("#detailSize").val();
			$("#detailSize").val(size - 1);
			sum();
		}
	});
	isEditing=false;
}
function cancelNew() {
	$("#divParent").empty();
	$("#divToolBar").show();
	sum();
	isEditing=false;
}
function cancelEdit() {
	$(".foldTr").trigger("click");
	$(this).addClass("foldTr");
	$(this).children().eq(0).children().attr("src",
			config['ctx'] + "/images/up.gif");
	$(this).next().show();
	sum();
	//isEditing=false;
}
function sum() {
	var sum = 0;

	$(".feeAmount").each(function(i) {
		var amount = this.value;
		amount = amount == '' ? 0 : amount;
		sum = sum + parseFloat(amount);
	});
	sum=sum.toFixed(2);
	$("#amountFee").val(sum);
	calcOfficePay();
}
function countAmount(jbpmReimburseDetlId) {
	var ticketFee = $("#ticketFee_" + jbpmReimburseDetlId).val();
	ticketFee = ticketFee == '' ? 0 : ticketFee;
	var fareFee = $("#fareFee_" + jbpmReimburseDetlId).val();
	fareFee = fareFee == '' ? 0 : fareFee;
	var lodgingFee = $("#lodgingFee_" + jbpmReimburseDetlId).val();
	lodgingFee = lodgingFee == '' ? 0 : lodgingFee;
	var travelFee = $("#travelFee_" + jbpmReimburseDetlId).val();
	travelFee = travelFee == '' ? 0 : travelFee;
	var otherFee = $("#otherFee_" + jbpmReimburseDetlId).val();
	otherFee = otherFee == '' ? 0 : otherFee;
	var amount = parseFloat(ticketFee) + parseFloat(fareFee)
			+ parseFloat(lodgingFee) + parseFloat(travelFee)
			+ parseFloat(otherFee);
	amount=amount.toFixed(2);
	$("#feeAmount_" + jbpmReimburseDetlId).val(amount);
	//设置校验条件
	if (lodgingFee>0){
		$("#lodgingStan_" + jbpmReimburseDetlId).attr("validate","num[1,4,0,0]required");
		$("#lodgingDays_" + jbpmReimburseDetlId).attr("validate","num[1,4,1,0]required");
	}else{
		$("#lodgingStan_" + jbpmReimburseDetlId).attr("validate","num[0,4]");
		$("#lodgingDays_" + jbpmReimburseDetlId).attr("validate","num[0,4,1]");
	}
	if (travelFee>0){
		$("#travelDays_" + jbpmReimburseDetlId).attr("validate","num[1,3,1,0]required");
	}else{
		$("#travelDays_" + jbpmReimburseDetlId).attr("validate","num[0,3,1]");
	}
	isEditing=true;
	sum();
}
function setValidation(jbpmReimburseDetlId){
	
}
function calcOfficePay(flag){
	if (flag=='task'){
		var selfPay=$("#selfPayAmount").html();
		var amountFee=$("#amountFee").html();
		var total=amountFee-selfPay;
		total=total.toFixed(2);
		$("#officePayAmount").html();
	}else{
		var selfPay=$("#selfPayAmount").val();
		var amountFee=$("#amountFee").val();
		if(Number(selfPay)<=Number(amountFee)){
			var total=amountFee-selfPay;
			total=total.toFixed(2);
			$("#officePayAmount").val(total);
		}else{
			alert("个人支付金额不能大于总金额");
			$("#selfPayAmount").val("");
			return false;
		}
	}
}
function save() {
	if (isEditing){
		alert("请先保存报销明细");
		return ;
	}
	var pdv = new Validate("inputFromReimburse");
	if (pdv.validate()) {
		$("#inputFromReimburse").submit();
	}
}
function apply() {
	if (isEditing){
		alert("请先保存报销明细");
		return ;
	}
	var pdv = new Validate("inputFromReimburse");
	if (pdv.validate()) {
		if ($("#detailSize").val() > 0) {
			saveComment();
			$("#inputFromReimburse").attr("action",
					"jbpm-reimburse-flow!apply.action");
			$("#inputFromReimburse").submit();
		} else {
			ymPrompt.alert( {
				message : '报销明细不能为空',
				title : '提示'
			});
		}
	}
}
function approve(taskId) {
	var comment = $("#comment").val();
	window.location.href = config['ctx']
			+ '/jbpm/jbpm-reimburse-flow!process.action?taskId=' + taskId
			+ '&comment=' + comment + "&isDesk=" + config['isDesk']+"&id="+config['jbpmReimburseId'];
}
function doExport() {
	window.open('jbpm-reimburse-flow!print.action?id='
			+ config['jbpmReimburseId'] + '&executionId='
			+ config['executionId'] + '&format=PDF', '_blank');
}
function doReturn(pageNo,searchScop) {
	var isDesk = config['isDesk'];
	if (isDesk == '1') {
		window.location.href = 'jbpm-reimburse-flow!desk.action?isDesk=' + config['isDesk']+"&pageApprove.pageNo="+pageNo+"&searchScop="+searchScop;
	} else {
		window.location.href = 'jbpm-reimburse-flow.action?isDesk=' + config['isDesk']+"&pageApprove.pageNo="+pageNo+"&searchScop="+searchScop;
	}
}
function cancel(taskId,id) {
	saveComment();
	window.location.href = config['ctx']
			+ '/jbpm/jbpm-reimburse-flow!cancel.action?taskId=' + taskId
			+ '&isDesk=' + config['isDesk']+"&id="+id;
}
function openAttachment(title, entityId) {
	ymPrompt.win( {
		message : config['ctx']
				+ "/app/app-attachment!list.action?bizEntityId=" + entityId
				+ "&bizModuleCd=jbpmReimburse&filterType=image|office",
		width : 500,
		height : 300,
		title : '附件管理',
		iframe : true
	});
}
function loadAttachment() {
	$.get($("#attach_files_div").attr("href"), function(data) {
		$("#attach_files_div").html(data);
	});
}
function loadComment() {
	var data = {
		executionId : config['executionId'],
		taskId : config['taskId'],
		statusCd : config['statusCd'],
		isMyTask : config['isMyTask'],
		isMyApprove : config['isMyApprove']
	};
	$.post(config['ctx'] + "/jbpm/jbpm-reimburse-flow!listComments.action",
			data, function(result) {
				$("#divComment").html(result);
				if (config['styleCall'])
					config['styleCall']();
			});
}
function loadDetail(isEditDetl) {
	var data = {
		id : config['jbpmReimburseId'],
		isEditDetl : isEditDetl
	};
	$.post(config['ctx'] + "/jbpm/jbpm-reimburse-flow!listDetail.action", data,
			function(result) {
				$("#divDetail").html(result);
				if (config['styleCall'])
					config['styleCall']();
			});
}