/**
 * 初始化页面状态
 */
function initCompent() {
	if ($('#contLedgerId').val() != '') {
		$('#tipStatus').text('查看/不可编辑');
		var auditStat = $('#contAudit_status').val();
		if (auditStat == 0) {
			$('#auditStat').text('未提交');
		} else if (auditStat == 1) {
			$('#auditStat').text('待审核');
		} else if (auditStat == 2) {
			$('#auditStat').text('已审核');
		}

	} else {
		editCont();
		$('#tipStatus').text('新增');
	}
	$(':text,textarea').attr('readOnly', 'true');
	$(':radio').attr('disabled', true).customInput();
	$('input[alt="amount"]').live('keyup', function() {
		clearNoNum_1(this);
		if ($('.percent').val() > 100) {
			this.value = 0;
		}
	});
	$('input[alt="amount_2"]').live('keyup', function() {
		clearNoNum(this);
		if ($('.percent').val() > 100) {
			this.value = 0;
		}
	});
	// 付款合计
	sumPay(1);
	sumPay(2);
	sumPay(3);
	sumPay(4);
	sumPay(6);
	// 付款比率
	sumTotalRate();
	autoHeightCont("rightDiv");

}
//设置合同台账高度
function autoHeightCont(contentTable){
	//alert($("#"+contentTable).height());
	var curHeight=$("#"+contentTable).height();//多出来的高度
    var endHeight = 0;
	try{
		var menuId = top.nowMenuId;
		if(null!=menuId && undefined !=menuId){
			var	ch =0;
			if(curHeight-1500>0){
				ch=curHeight-1500;
			}else if(curHeight>0){
				ch=curHeight;
			}
			if(endHeight == 0){
				endHeight = ch;
			}else if(ch > endHeight){
				endHeight = ch;
			}
			if(endHeight>604){
				$(window.top.document).find("#bodyLoad").height(endHeight+51);
				$(window.top.document).find("#div_left_b").height(endHeight+51);
				$(window.top.document).find('#' + menuId+'_frame').contents().find("body").height(endHeight);
				$(window.top.document).find('#' + menuId+'_frame').height(endHeight);
			}
		}
	}catch(e){}
} 

/**
 * 取消编辑或新增，还原页面到初始化状态
 */
function cancelCont() {
	window.location.href = window.location.href;
}
/**
 * 保存合同台账
 * 
 * @param audit
 */
function saveCont(audit) {
	$("#contractNo").val($("#cont-no").val());
	// 合同类型
	if ("" == $("#cont_type_cd").val() && "" == $("#projectCd").val()) {
		alert("请输入合同类型");
		return false;
	}

	var contNo = $("input[name='contNo']").val();
	if (contNo == "") {
		alert("合同编号不能为空！");
		$("#cont-no").focus();
		return false;
	}
	if (audit != "0") {
		//如果处于查看状态，则不需要校验
		if (!$('#tipStatus').text() == "查看/不可编辑"){
			// 如果校验不成功，return false;
			if (!contNoValidate()) {
				return false;
			}
		}
		if ("" == $("#total_price").val()) {
			alert("请输入合同总价");
			return false;
		}

		// 范围/数量rangeNum
		if ($("input[name='rangeNum']").val() == "") {
			alert("请输入范围/数量");
			return false;
		}
		// 计划开工日期planBeginDate
		if ($("input[name='planBeginDate']").val() == "") {
			alert("请输入计划开工日期");
			return false;
		}
		// 工期period
		if ($("input[name='period']").val() == "") {
			alert("请输入工期");
			return false;
		}
		// 如果实际竣工日期填写了，必填保修期
		if ($("input[name='realEndDate']").val() != "") {
			if ($("input[name='guarBeginDate']").val() == "" || $("input[name='guarEndDate']").val() == "") {
				alert("请输入保修期起止日期");
				return false;
			}
		} else {
			// 如果未填实际竣工日期，默认进度状态为 未完未结
			$("#contStatus_1").attr("checked", 'checked');
			$("#contStatus_1").trigger('click');
		}
	}
	$(':radio').removeAttr('disabled');
	/*
	 * if($("input[name='updateRate']").val()!=""){ var rate
	 * =eval($("input[name='updateRate']").val()|0);
	 * $("input[name='updateRate']").val(rate.toFixed(2)); }
	 */
	if (audit) {
		if (audit == "0") {
			var status = $("#contAudit_status").val();
			if (status == "") {
				$("#contAudit_status").val("0");
			} else {
				$("#contAudit_status").val(status);
			}
		} else
			$("#contAudit_status").val(audit);
	}

	$('#cont-ledger-form').submit();
}
/**
 * 合同编号唯一性校验
 */
function contNoValidate() {

	if ($('#tipStatus').text() == "可编辑" || $('#tipStatus').text() == "新增") {
		var contNo = $.trim($("input[name='contNo']").val());//trim前后去空格
		if (contNo == '') {
			return false;
		} else {
			/* 这段代码是异步的,不能起到重复提示作用*/
			var contLedgerId = $('#contLedgerId').val();
			var data = {contNo: contNo, contLedgerId: contLedgerId};
			$.post(_ctx + '/cont/cont-ledger!contNoValidate.action', data, function(result) {
				//0-不重复
				if (result != '0') {
					alert("该编号已存在，请重新输入");
					$("input[name='contNo']").val("");
					return false;
				}
			});
		}

		return true;
	}
}
/**
 * 添加签证变更动态
 */
function addVisa() {
	var tmpAttaId = 'visa_' + curr_user_cont + '_' + String((new Date().getTime()) ^ Math.random());
	var newTr = '<tr><td></td><td><input type="text" name="contVisaUpdates[' + visaIndex + '].visaUpdateNo" /></td>' 
	        + '<td><input type="text" name="contVisaUpdates['+ visaIndex + '].content"  /></td>' 
	        + '<td><select name="contVisaUpdates[' + visaIndex + '].cause" style="width:80px">'
			  + '<option value=""></option><option value="1">设计错漏</option><option value="2">招商原因</option>'
			  + '<option value="3">施工原因</option><option value="4">甲方原因</option><option value="5">其它</option></select></td>'
			+ '<td><input type="text" name="contVisaUpdates[' + visaIndex + '].prepareFee"  alt="amount"/></td>'
			+ '<td><input type="text" name="contVisaUpdates[' + visaIndex + '].amountUpdate" alt="amount" onkeyup="sumVisaAmout()" /></td>' + '<td><img src="'
			+ _ctx + '/resources/images/common/atta.gif" title="上传附件" onclick=openAttachment(this,"' + tmpAttaId + '") />'
			+ '<input type="hidden" name="contVisaUpdates[' + visaIndex + '].attaBizId" value="' + tmpAttaId + '" /></td>'
			+ '<td onclick="delVisa(this)">删除</td></tr>';
	$("#visaTable").append(newTr);
	visaIndex++;
}
/**
 * 删除签证变更动态
 * 
 * @param dom
 * @param id
 *            如果id不存在则直接删除，否则询问一下再删除
 */
function delVisa(dom, delId, id) {
	if (delId) {
		if (window.confirm("确定要删除该记录?")) {
			$("#visaTable").parent().mask("Waiting...");
			var url = _ctx + "/cont/cont-ledger!deleteVisa.action";
			$.post(url, {
				contVisaUpdateId : delId,
				id : id
			}, function() {
				$(dom).parents("tr").remove();
				$("#visaTable").parent().unmask();
				// 重新求签证的总和
					sumVisaAmout();
				});
		}
	} else {
		$(dom).parents("tr").remove();
		// 重新求签证的总和
		sumVisaAmout();
	}
}
/**
 * 添加付款动态
 */
function addPay() {
	var newTr = '<tr><td><input type="text" name="contPaies[' + payIndex + '].payTime" onfocus="WdatePicker()"/></td>'
			+ '<td><input type="text" id="completeNum_' + payIndex + '" name="contPaies[' + payIndex
			+ '].completeNum" alt="amount"  onkeyup="sumPay(1);sumAmount(' + payIndex + ');"/></td>' + '<td><input type="text" id="matieralNum_' + payIndex
			+ '" name="contPaies[' + payIndex + '].matieralNum" alt="amount"  onkeyup="sumPay(2);sumAmount(' + payIndex + ');"/></td>'
			+ '<td><input type="text" id="currentPay_' + payIndex + '" name="contPaies[' + payIndex
			+ '].currentPay" alt="amount" onkeyup="sumPay(3);sumAmount(' + payIndex + ');"/></td>' + '<td><input type="text" id="currentAdd_' + payIndex
			+ '" name="contPaies[' + payIndex + '].currentAdd" alt="amount" onkeyup="sumPay(4);sumAmount(' + payIndex + ');"/></td>'
			+ '<td><select id="processStatus_${index}" name="contPaies[' + payIndex + '].payProperty" style="width:80px">'
			+ '<option value=""></option><option value="1">预付款</option>'
			+ '<option value="2">进度款</option><option value="3">结算款</option><option value="4">质保金</option><option value="5">其它尾款</option>' + '</select></td>'
			+ '<td><input type="text" id="payMoney_' + payIndex + '" name="contPaies[' + payIndex
			+ '].payMoney" readonly="readonly" alt="amount" onkeyup="sumPay(5)"/></td>' + '<td><input type="text" id="payRate_' + payIndex
			+ '" name="contPaies[' + payIndex + '].payRate" readonly="readonly" alt="amount" onkeyup="sumPay(6)"/></td>'
			+ '<td onclick="delPay(this)">删除</td></tr>';
	$("#addPayTable").append(newTr);
	payIndex++;
}
/**
 * 删除付款动态
 * 
 * @param dom
 * @param id
 */
function delPay(dom, id) {
	if (id) {
		if (window.confirm("确定要删除该记录?")) {
			$("#addPayTable").parent().mask("Waiting...");
			var url = _ctx + "/cont/cont-ledger!deletePay.action";
			$.post(url, {
				contPayId : id
			}, function() {
				$(dom).parents("tr").remove();
				$("#addPayTable").parent().unmask();
			});
		}
	} else {
		$(dom).parents("tr").remove();
	}
}
/**
 * 添加补充协议动态
 */
function addAgree() {
	var tmpAttaId = 'agree_' + curr_user_cont + '_' + String((new Date().getTime()) ^ Math.random());
	var newTr = '<tr><td></td><td><input type="text" name="contAddAgreements[' + addIndex + '].addAgreementNo" /></td>'
			+ '<td><input type="text" name="contAddAgreements[' + addIndex + '].content"  /></td>' + '<td><input type="text" name="contAddAgreements['
			+ addIndex + '].groupCheckFee" alt="amount"/></td>' + '<td><input type="text" name="contAddAgreements[' + addIndex
			+ '].contTempMoney" alt="amount"/></td>' + '<td><input type="text" name="contAddAgreements[' + addIndex
			+ '].amountUpdate" alt="amount" onkeyup="sumAgreeAmout()"/></td>' + '<td><img src="' + _ctx
			+ '/resources/images/common/atta.gif" title="上传附件" onclick=openAttachment(this,"' + tmpAttaId + '") />'
			+ '<input type="hidden" name="contAddAgreements[' + addIndex + '].attaBizId" value="' + tmpAttaId + '"/></td>'
			+ '<td onclick="delAgree(this)">删除</td></tr>';
	$("#addAgreementTable").append(newTr);
	addIndex++;
}
/**
 * 删除补充协议动态
 */
function delAgree(dom, agreeId, id) {
	if (id) {
		if (window.confirm("确定要删除该记录?")) {
			$("#addAgreementTable").parent().mask("Waiting...");
			var url = _ctx + "/cont/cont-ledger!deleteAgree.action";
			$.post(url, {
				contAddAgreementId : agreeId,
				id : id
			}, function() {
				$(dom).parents("tr").remove();
				$("#addAgreementTable").parent().unmask();
				// 更新补充协议数据;
					sumAgreeAmout();
				});
		}
	} else {
		$(dom).parents("tr").remove();
		// 更新补充协议数据;
		sumAgreeAmout();
	}
}
/**
 * 计算签证变更总和
 */
function sumVisaAmout() {
	var sum = sumColumn('visaTable', 4);
	$('#visa_total').val(sum);
	calcUpdateTotal();
}
/**
 * 计算补充协议总和
 */
function sumAgreeAmout() {
	var sum = sumColumn('addAgreementTable', 5);
	$('#agree_total').val(sum);
	calcUpdateTotal();
}
function calcUpdateTotal() {
	try {
		var priceType = 0;
		if ($("input[type=radio][name='contProperty']:checked").val() == null) {
			alert("请输入合同性质");
			return false;
		}
		/*
		 * priceType =$("input[type=radio][name='contProperty']:checked").val();
		 * if(priceType==0||priceType==1){//固定总价合同 var totalPrice =
		 * eval($('#total_price').val()||0); var visaTotal =
		 * eval($('#visa_total').val()||0); var agreeTotal =
		 * eval($('#agree_total').val()||0);
		 * $('#update_total').val(totalPrice+visaTotal+agreeTotal);
		 * $('#update_rate').val(visaTotal/(totalPrice+agreeTotal)*100);
		 * 
		 * }else{//单价费率合同 var totalPrice = eval($('#total_price').val()||0); var
		 * visaTotal = eval($('#visa_total').val()||0); var agreeTotal =
		 * eval($('#agree_total').val()||0);
		 * $('#update_total').val(visaTotal+agreeTotal); if(agreeTotal!=0)
		 * $('#update_rate').val(visaTotal/(agreeTotal)*100); else
		 * $('#update_rate').val(0); }
		 */
		// 已确认合同总价改为：合同总价+累计变更总价+补充协议总价；变更比率：变更累计/已确认合同总价
		var totalPrice = eval($('#total_price').val() || 0);
		var visaTotal = eval($('#visa_total').val() || 0);
		var agreeTotal = eval($('#agree_total').val() || 0);
		$('#update_total').val(totalPrice + visaTotal + agreeTotal);
		$('#update_rate').val((visaTotal / ($('#update_total').val()) * 100).toFixed(2));
	} catch (e) {

	}
}
/**
 * 计算付款动态各列的和
 * 
 * @param colIndex
 */
function sumPay(colIndex) {
	var sum = sumColumn('addPayTable', colIndex);
	var _footCol = $('#addPayTable tfoot tr:not(.fin) td:eq(' + colIndex + ')');
	_footCol.text(sum).css('fontSize', '15px');
	setTimeout(function() {
		_footCol.css('fontSize', '12px');
	}, 200);
}
function sumTotalRate() {
	var _footCol1 = $('#addPayTable tfoot tr:not(.fin) td:eq(1)').text();
	var _footCol6 = $('#addPayTable tfoot tr:not(.fin) td:eq(6)').text();
	$('#addPayTable tfoot tr td:eq(7)').text(((_footCol6 / _footCol1) * 100).toFixed(2));
}
/**
 * 对表格列进行求和
 * 
 * @param tableId
 *            表格id
 * @param colIndex
 *            表格索引 从0开始
 * @returns {Number} 和
 */
function sumColumn(tableId, colIndex) {
	var _all_tr = $('#' + tableId + ' tbody tr');
	var sum = 0;
	_all_tr.each(function() {
		var value = $.trim($(this).children().eq(colIndex).children().val());
		if (value == '' || value == '-')
			value = 0;
		sum += eval(value);
	});
	return sum.toFixed(2);
}
/**
 * 付款小计 总和=当期甲供料+当期直接支付+当期增加款或扣款
 * 
 * @param colIndex
 *            行
 */
function sumAmount(colIndex) {
	// 小计eval($('#total_price').val()||0);
	var matieralNum = eval($("#matieralNum_" + colIndex).val() || 0);
	var currentPay = eval($("#currentPay_" + colIndex).val() || 0);
	var currentAdd = eval($("#currentAdd_" + colIndex).val() || 0);
	var amount = matieralNum + currentPay + currentAdd;
	var completeNum = eval($("#completeNum_" + colIndex).val() || 0);
	$("#payMoney_" + colIndex).val(amount);
	$("#payRate_" + colIndex).val((amount / completeNum) * 100);
}
/**
 * 设置合同约定下浮率的值和可编辑状态
 * 
 * @param id
 */
function changeRate(id) {
	if (id == 'downRate-1') {
		$('#down_rate').attr('readOnly', true).val('');
	} else {
		$('#down_rate').removeAttr('readOnly').removeAttr('disabled');
	}
}
/**
 * 选择合同类型
 */
function selectContType() {
	var selectId = "";
	var selectName = "";
	var ledgerType = $("#ledgerType").val();
	ymPrompt.confirmInfo( {
		icoCls : "",
		title : "合同类型",
		message : "<div id='contractType1_Div'></div>",
		useSlide : true,
		winPos : "c",
		width : 300,
		height : 400,
		allowRightMenu : true,
		afterShow : function() {
			$.post(_ctx + "/cont/cont-contract-type!getItemTree.action", {
				ledgerType : ledgerType
			}, function(result) {
				var tree = new TreePanel( {
					renderTo : "contractType1_Div",
					'root' : eval('(' + result + ')'),
					'ctx' : _ctx
				});
				tree.on(function(node) {
					if (node.attributes.children == null || node.attributes.children == "") {
						selectId = node.attributes.id;
						selectName = node.attributes.text;
					}
				});
				tree.render();
			});
		},
		handler : function(e) {
			if ("ok" == e) {
				if ("0" != selectId) {
					$("#cont_type_cd").val(selectId);
					$("#cont_type_name").val(selectName);
				}

			}
		}
	});
}
//打开附件方法（合同台账统一使用）
function openAttachment(dom, entityId, hiddenId, title) {
	var _title = title || '上传附件';
	if (!entityId || entityId == '') {
		//若entityId为空，则取他下面的ID
		 var realId=$(dom).next();
		if ($.trim($(realId).val()) == '') {
			entityId = 'agree_' + curr_user_cont + '_' + String((new Date().getTime()) ^ Math.random());
			//$('#' + hiddenId).val(entityId);
			$(dom).next().val(entityId);
		} else {
			entityId = $(realId).val();
		}
	}
	ymPrompt.win( {
		message : _ctx + "/app/app-attachment!list.action?bizEntityId=" + entityId + "&bizModuleCd=contLedger&filterType=image|office",
		width : 500,
		height : 300,
		title : _title,
		afterShow : function() {
		},
		iframe : true
	});
}
/**
 * 查看附件
 * 
 * @param attachTypeCd
 * @return
 */
/*
function showAttachment(attachTypeCd) {
	ymPrompt.win( {
		message : _ctx + "/sc/sc-contract-templet-info!seeAttachList.action?attachTypeCd=" + attachTypeCd + "&scContractId="
				+ $("#contractTempletInfoId").val() + "&hisContId=" + $("#contractTempletHisId").val(),
		width : 500,
		height : 300,
		title : '附件查看',
		afterShow : function() {
		},
		iframe : true
	});
}
*/

  function showAttachment(entityId){ ymPrompt.win({
  message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=contLedger&filterType=image|office&onlyShow=true",
  width:500, height:300, title: '附件查看', afterShow:function(){}, iframe:true }); }

/**
 * 勾选合同状态
 * 
 * @param status
 */
function updateContStatus(status) {
	var realBeginDate = $('input[name="realBeginDate"]').val();
	// 如果填写计划开工日期，且未填写实际开工日期，则为未开工状态,并填写未完未结
	if (status == "plan" && realBeginDate == "") {
		$('input[name="procStatus"]:checked').attr('checked', false);
		$("#procStatus_1").attr("checked", true);
		$("#procStatus_1").trigger('updateState');
		$('input[name="contStatus"]:checked').attr('checked', false);
		$("#contStatus_1").attr("checked", true);
		$("#contStatus_1").trigger('updateState');
	} else if (status == "real" && $(realBeginDate != "" && $('input[name="clearPrice"]').val() == "")) {
		// 实际开工日期
		var proc = $('input[name="procStatus"]:checked').val();
		if (!("1" == proc || "2" == proc || $('input[name="realEndDate"]').val() != "")) {
			alert("请选择按期或滞后");
			$('input[name="procStatus"]:checked').removeAttr('checked');
			$('#procStatus_2').removeAttr('disabled');
			$('#procStatus_3').removeAttr('disabled');
			$('#delay_day').removeAttr('readonly');
		}
	} else if (status == "realEnd") {
		if (realBeginDate == "") {
			alert("请输入实际开工日期");
			$('input[name="realEndDate"]').val("");
		}
		if ($('input[name="realEndDate"]').val() != "") {
			// 实际竣工日期、未填结算价时，合同进度：已竣工；合同状态：已完未结
			$('input[name="procStatus"]:checked').removeAttr('checked');
			$("#procStatus_5").attr("checked", true);
			$("#procStatus_5").trigger('updateState');
			if ($('input[name="clearPrice"]').val() == "") {
				$('input[name="contStatus"]:checked').removeAttr('checked');
				$("#contStatus_2").attr("checked", true);
				$("#contStatus_2").trigger('updateState');
			}else{
				//已完未结
				$("#contStatus_3").attr("checked",true);
				$("#contStatus_3").trigger('updateState');
			}
		} else {
			if (realBeginDate == "") {
				updateContStatus("plan");
			} else if ($('input[name="realBeginDate"]').val() != "" && $('input[name="clearPrice"]').val() == "") {
				updateContStatus("real");
			}
		}
	} else if (status == "clear") {
		var realEndDate = $('input[name="realEndDate"]').val();
		if (realEndDate == "") {
			alert("请填入实际竣工日期");
			$('input[name="clearPrice"]').val("");
			return false;
		}
		// 如果结算价填入，则已完已结
		var clearPrice = $('input[name="clearPrice"]').val();
		if (clearPrice != "") {
			$('input[name="contStatus"]:checked').removeAttr('checked');
			$("#contStatus_3").attr("checked", true);
			$("#contStatus_3").trigger('updateState');
		} else {
			$('input[name="contStatus"]:checked').removeAttr('checked');
			if (realEndDate != "") {
				// 已完未结
				$("#contStatus_2").attr("checked", true);
				$("#contStatus_2").trigger('updateState');
			} else {
				// 未完未结
				$("#contStatus_1").attr("checked", true);
				$("#contStatus_1").trigger('updateState');
			}
			$('input[name="clearPrice"]').val("");
		}
	}
}
function doDeleteRemark(dom, delId) {
	if (delId) {
		if (window.confirm("确定要删除该记录?")) {
			var param = {
				id : delId
			};
			var url = _ctx + "/cont/cont-ledger!deleteRemark.action";
			$.post(url, param, function(result) {
				$(dom).parents("tr").remove();
				$("#visaTable").parent().unmask();
			});
		}
	}
}
