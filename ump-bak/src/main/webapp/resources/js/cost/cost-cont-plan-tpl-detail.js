String.prototype.trim = function() {
	// 用正则表达式将前后空格
	// 用空字符串替代。
	var t = this.replace(/(^\s*)|(\s*$)/g, "");
	return t.replace(/(^　*)|(　*$)/g, "");
}

function StringBuffer() {
	this._strs = new Array;
}
StringBuffer.prototype.append = function(str) {
	this._strs.push(str);
};
StringBuffer.prototype.toString = function() {
	return this._strs.join("");
};

// 转化为JSON
function toJson(str) {
	// 如果是对象则直接返回
	if(typeof(str)=="object"){
		return str;
	}
	if ("" == $.trim(str)) {
		return "";
	}
	str=str.replace("PRE","pre");
	str=str.replace("<pre>","").replace("</pre>","");
	return eval("(" + str + ")");
}
function isEmpty(str) {
	return (typeof(str) === "undefined" || str === null || (str.trim().length === 0));
}

// 返回val的字节长度
function getByteLen(val) {

	var len = 0;
	for (var i = 0; i < val.length; i++) {

		var intCode = val.charCodeAt(i);
		if (intCode >= 0 && intCode <= 128) {
			len = len + 1; // 非中文单个字符长度加 1
		} else {
			len = len + 2; // 中文字符长度则加 2
		}
	}
	return len;

}
/**
 * 判断是否超过最大的字符长度
 * 
 * @param {}
 *            val 字符串
 * @param {}
 *            maxLen 最大长度
 */
function isPassMaxLen(val, maxLen) {

	if (getByteLen(val) > maxLen) {
		return true;
	} else {
		return false;
	}
}


function splitDate(){}

/**
 * 增加一行合约规划
 */
function apppendRow(addRow){
	var selItemId=$(".trClicked").attr("id");
	var contPlanTplId=$(".trClicked").attr("contPlanTplId");
	if(selItemId){
		selItemId=selItemId.replace("td","");
		$.post(_ctx+"/cost/cost-cont-plan-tpl-detail!addCostContPlanRow.action",{id:selItemId,contPlanTplId:contPlanTplId,addRowFlag:addRow}, function(result) {
			var tempContainer = $("<div>"+result+"</div>");	
			var gridView1Html = $(tempContainer).find("#addGridView").val();
			$.each($("tr[id="+selItemId+"]"),function(){
				$(this).after(gridView1Html);
			});	
		});
	}else{
		ymPrompt.alert({
			message : "请先选择合约规划项！",
			title : "提示",
			handler : function(tp) {
				
			}
		});
	}
	
}
/*
 * 
 */
	function delRow(){
		var selItemId=$(".trClicked").attr("id");
		if(selItemId){
			ymPrompt.params={};
			ymPrompt.params.selItemId=selItemId.replace("td","");
		ymPrompt.confirmInfo({
			message : '删除后对应合约规划子项也一并删除，你确认要删除当前合同规划项吗？',
			title : "删除合约规划项",
			handler : function(tp) {
				if (tp == 'ok') {
					ymPrompt.close();
					$('body').mask('请稍等，正在删除合约规划项...');
					$.post(_ctx+"/cost/cost-cont-plan-tpl-detail!delete.action", {
								id : selItemId.replace("td","")
							}, function(result) {
								$('body').unmask();
								var myDate = new Date();
								var mytime=myDate.toLocaleTimeString();     // 获取当前时间
					
								if(result.indexOf("true")> -1){
									$("tr[id="+ymPrompt.params.selItemId+"]").remove();
											displayOperInfo(mytime+" 删除成功！");
								}else{
									displayOperInfo(mytime+" 删除失败！");
								}
							
							})
							
				}
			}
	   });
		}else{
			ymPrompt.alert({
				message : "请先选择合约规划项！",
				title : "提示",
				handler : function(tp) {
				
				}
			});
		}
	}
	/**
	 * 鼠标移上时更改背景颜色
	 */
	function changeBg(dom) {
		var selid=$(".trSelectd").attr("cid");
		var itemId=$(dom).attr("id");	
		if(selid && selid == itemId){
              return;
			}
		$("td[cid="+itemId+"]").css("background", "#EDEFF3");
		$("td[cid="+itemId+"]").find("input").css("background", "#EDEFF3");
	}
	/**
	 * 鼠标移除时更改去除背景颜色
	 */
	function changeBgOut(dom) {	
		var selid = $(".trSelectd").attr("cid");
		var itemId = $(dom).attr("id");	
		if(selid && selid == itemId){
              return;
			}	
		$("td[cid="+itemId+"]").css("background", "");
		$("td[cid="+itemId+"]").find("input").css("background", "");
		
	}
	
	/**
	 * 点击选择合约规划(指定1-10类中的第一个子类)
	 * 
	 * @param dom
	 * tr行
	 * @return
	 */
	function rowsItem(dom){
		//定义【1-10】大类中的第一个子类
		var subjectCd_1='1',subjectCd_2='2',subjectCd_3='3',subjectCd_4='4',subjectCd_5='5',subjectCd_6='6',subjectCd_7='7',subjectCd_8='8',subjectCd_9='9',subjectCd_10='10';
		var rowTypeCd_1='1';
		var subjectCd=$(dom).attr("subcd");
		var rowTypeCd=$(dom).attr("rowcd");
		//判断如果是所有大类中的第一个子类，则不做任何操作
		if((subjectCd==subjectCd_1&&rowTypeCd==rowTypeCd_1)||(subjectCd==subjectCd_2&&rowTypeCd==rowTypeCd_1)||(subjectCd==subjectCd_3&&rowTypeCd==rowTypeCd_1)||(subjectCd==subjectCd_4&&rowTypeCd==rowTypeCd_1)||(subjectCd==subjectCd_5&&rowTypeCd==rowTypeCd_1)||(subjectCd==subjectCd_6&&rowTypeCd==rowTypeCd_1)||(subjectCd==subjectCd_7&&rowTypeCd==rowTypeCd_1)||(subjectCd==subjectCd_8&&rowTypeCd==rowTypeCd_1)||(subjectCd==subjectCd_9&&rowTypeCd==rowTypeCd_1)||(subjectCd==subjectCd_10&&rowTypeCd==rowTypeCd_1)){
			$(dom).find("input").attr("disabled","disabled");
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 点击选择合约规划
	 * 
	 * @param dom
	 *            tr行
	 * @return
	 */
	function selItem(dom){
		if(rowsItem(dom)==false){
			var selid = $(".trSelectd").attr("cid");
			var itemId = $(dom).attr("id");
			if(selid && selid == itemId){
				return;
			}else if(selid){
				$("div.cls"+selid).show();
				$(".icls"+selid).hide();	    
	            $("#td"+selid).removeClass("trClicked");
	            $("td[cid="+selid+"]").removeClass("trSelectd");
	            changeBgOut($("#"+selid));
			}

			$("div.cls"+itemId).hide();
			$(".icls"+itemId).show();
	        $("td[field=sort]").removeClass();
	        $("#td"+itemId).addClass("trClicked");
	        $("td[cid="+itemId+"]").find("input").css("background", "#FFFFFF");
	        $("#"+itemId).find(".input1").css("background", "#FFFFFF");
	        $("td[cid="+itemId+"]").addClass("trSelectd");
		}
	}

function saveEdit() {
	var row = $('#cpPlanTplDetailTreeGridDiv').datagrid('getSelected');
	if (row) {
		var index = $('#cpPlanTplDetailTreeGridDiv').datagrid('getRowIndex', row);
		$('#cpPlanTplDetailTreeGridDiv').datagrid('endEdit', index);
	}
	$.post(_ctx+"/cost/cost-cont-plan-tpl-detail!saveBatch.action", Convert.ToSaveItemParam("cpPlanTplDetailTreeGridDiv"), function(result) {
		var rObj = eval("("+result+")");
		if (rObj.success) {
			$('#cpPlanTplDetailTreeGridDiv').datagrid('reload');
		} else {
			alert(rObj.failure);
		}
	});
}


/**
 * 选择甲供料
 * 
 * @param dom
 *            当前触发事件对象
 * @return
 */

function save(dom){
	ymPrompt.params.activeDom=dom;
	var oldVal=$(dom).parent().attr("title");
	var val=$(dom).val();
	if(oldVal.trim() == val.trim()){
		var myDate = new Date();
		var mytime=myDate.toLocaleTimeString();     // 获取当前时间
		displayOperInfo(mytime+" 自动保存成功！");
		return;	
	}
	var id=$(dom).attr("id");
	var oid=$(dom).attr("oid");
	var field=$(dom).attr("field");
	var nameObj=$(dom).find("option:selected").text();
	var data={};
	data.field=field;
	data.id=oid;
	//特殊处理多选的甲供材料、设备（避免入库前存入的并不是甲供Cd）
	if(field=='partMateTypeCds'){
		data.val=id;
	}else{
		data.val=val;
	}
	// alert("field:"+field+";id:"+oid+";val:"+val);
	$.ajax({
		url : _ctx+"/cost/cost-cont-plan-tpl-detail!save.action",
		type : 'POST',
		data : data,
		async : false, // ajax同步
		timeout : 1000,
		error : function() {
			alert('Error loading  document');
		},

		success : function(responseText) {
			var resultJson=toJson(responseText);
			if(resultJson.status){
				displayOperInfo(resultJson.updateDate+" 自动保存成功！");
				
			}else{
				
				displayOperInfo("<span style='color:red'>"+resultJson.updateDate+" 自动保存失败！</span>");
				
			}
			
		}

	});
	if(nameObj.length>0){
		$(dom).prev().html(nameObj);
	}else{
		$(dom).prev().html(val);
	}
}

function displayOperInfo(msgText){
	
	$(".operInfo").html(msgText);
	window.setTimeout("	$('.operInfo').html('')", 4000);
}
/**
 * 选择甲供料
 * 
 * @param dom
 *            当前触发事件对象
 * @return
 */
function selectPartMateType(dom,addMultiRow){
	//获取当前行的Id
	var costContPlanTplDetailId=$(dom).parent().parent().parent().attr("id");
	ymPrompt.params={};
	ymPrompt.params.activeDom=dom;
	ymPrompt.params.activeMultiRowDom=addMultiRow;
	ymPrompt.win({
		title:"甲供料选择(按住[Ctrl键]可多选)",
		fixPosition:true,
		width:280,
		height:350,
		allowRightMenu:true,
		showMask :true,
		useSlide : true,
		message:"<div id='popEditDiv' style='margin:10px;height:270px;'><img align='absMiddle' src='"+_ctx+"/images/loading.gif'>数据正在加载中...</div>" +
				"&nbsp;<input type='radio' id='sameLevel'  checked='checked' name='selectNode' value='sameLevelNode'/>同级&nbsp;&nbsp;<input type='radio' id='nextLevel' name='selectNode' value='nextLevelNode'/>下级<br/>" +
				"&nbsp;<input type='button' value='确认' class='btnStyle handler' id='"+costContPlanTplDetailId+"' onclick='comfirmOk();'/>",
		afterShow:function(){
			$.post(_ctx+"/cost/cost-cont-plan-tpl-detail!selectPartMateType.action",{costContPlanTplDetailId:costContPlanTplDetailId},function(result){
				$("#popEditDiv").html(result);
			});
		},
		handler : function(btn) {
			ymPrompt.close();
		}
	});
}

function comfirmOk(element){
	//选择多个甲供材料、设备Cds
	var selectCds = "";
	//选择多个甲供材料、设备名
	var selectNames = "";
	//选择多个甲供材料、设备cds和names拼接成json串,格式为：{'CostContPlanTplDetail':[{'cd0':'10','name0':'空调'},{'cd1'：'20','name1':'空调'}]}
	var selectPartMateTypeJsonStr="{'CostContPlanTplDetail':[";
	var partMateTypelen=$("#selectPartMateType").find("option:selected").length;
	$("#selectPartMateType").find("option:selected").each(function(i){
		if(i==partMateTypelen-1){
			selectCds+=$(this).val();
			selectNames+=$(this).text();
			selectPartMateTypeJsonStr+="{'contPartMateTypeCds':'"+$(this).val()+"','contPartMateTypeNames':'"+$(this).text()+"'}]}";
		}else{
			selectCds+=$(this).val()+",";
			selectNames+=$(this).text()+",";
			selectPartMateTypeJsonStr+="{'contPartMateTypeCds':'"+$(this).val()+"','contPartMateTypeNames':'"+$(this).text()+"'},";
		}
	});
	//将获取到的值赋值给当前指定的文本框内
	$(ymPrompt.params.activeDom).attr("id",selectCds);
	$(ymPrompt.params.activeDom).attr("value",selectNames);
	$(ymPrompt.params.activeDom).attr("title",selectNames);
	$("#partMateTypeNames").val(selectNames);
	if(parseInt(partMateTypelen)>0){
		//判断单选按钮是否被选中
		var selectNode = $(':radio[name=selectNode][checked]').val();
		save($(ymPrompt.params.activeDom));
		//确认操作后增加节点（1,同级（默认）2，下级） 状态标示: 'sameLevelNode'--同级，'nextLevelNode'--下级
		addRowPartMateType(selectPartMateTypeJsonStr,selectNode);
		ymPrompt.close();
	}else{
		alert("请先选择要添加的甲供料项！");
	}
}


/**
 * 添加甲供材料、设备的行记录值（可多选）
 * @return
 */
function addRowPartMateType(selectPartMateTypeJsonStr,element){
	var selItemId=$(".trClicked").attr("id");
	var contPlanTplId=$(".trClicked").attr("contPlanTplId");
	//alert("addMultiRow:"+ymPrompt.params.activeMultiRowDom);
	//alert("selItemId: "+selItemId+" selectPartMateTypeJsonStr: "+selectPartMateTypeJsonStr);
	if(selItemId){
		selItemId=selItemId.replace("td","");
		$.post(_ctx+"/cost/cost-cont-plan-tpl-detail!addCostContPlanRow.action",{id:selItemId,contPlanTplId:contPlanTplId,addRowFlag:ymPrompt.params.activeMultiRowDom,selectPartMateTypeJsonStr:selectPartMateTypeJsonStr,node:element}, function(result) {
			var tempContainer = $("<div>"+result+"</div>");	
			var gridView1Html = $(tempContainer).find("#addGridView").val();
			$.each($("tr[id="+selItemId+"]"),function(){
				$(this).after(gridView1Html);
			});	
		});
	}
}


/**
 * 自动计算合约子成本额
 * @return
 */
function autoCalcContSubTargAmt(element){
	//定义大类【1-10】的所有变量SUBJECT_CD
	var subjectV_1='1',subjectV_2='2',subjectV_3='3',subjectV_4='4',subjectV_5='5',subjectV_6='6',subjectV_7='7',subjectV_8='8',subjectV_9='9',subjectV_10='10';;
	//定义大类【1-9】的所有变量ROWTYPE_CD
	var sub_rowType_11='1_1',sub_rowType_21='2_1',sub_rowType_31='3_1',sub_rowType_41='4_1',sub_rowType_51='5_1',sub_rowType_61='6_1',sub_rowType_71='7_1',sub_rowType_81='8_1',sub_rowType_91='9_1',sub_rowType_101='10_1';
	var oldVal=$(element).parent().attr("title");
	var newVal=$(element).val();
	var id=$(element).attr("id");
	var oid=$(element).attr("oid");
	var field=$(element).attr("field");
	var val=$(element).val();
	var rowNo=$(element).parent().parent().attr("subcd");
	var sDataTable=$(".sData");
	var numValue;
	var sub_rowType;
	var calcAmtSum=0.0;
	var totalSum=0.0;
	if(oldVal.trim() != newVal.trim()){
		//统一处理合约计划从大类1-9
		if(rowNo == subjectV_1||rowNo == subjectV_2||rowNo == subjectV_3||rowNo == subjectV_4||rowNo == subjectV_5||rowNo == subjectV_6||rowNo == subjectV_7||rowNo == subjectV_8||rowNo == subjectV_9){
			numValue = sDataTable.find("#MyTable input[pid=contSubTargetAmt_"+rowNo+"]");
			$.each($(numValue),function(i,item){
				var subRowCd = $($(item).get(0)).attr("identity");
				if(subRowCd == sub_rowType_11 || subRowCd == sub_rowType_21 || subRowCd == sub_rowType_31 || subRowCd == sub_rowType_41 || subRowCd == sub_rowType_51 || subRowCd == sub_rowType_61 || subRowCd == sub_rowType_71 || subRowCd == sub_rowType_81 || subRowCd == sub_rowType_91){
					sub_rowType = subRowCd;
				}else{
					calcAmtSum += isNull($(item).val());
				}
			});
			
			if(sub_rowType == sub_rowType_11 || sub_rowType == sub_rowType_21 || sub_rowType == sub_rowType_31 || sub_rowType == sub_rowType_41 || sub_rowType == sub_rowType_51 || sub_rowType == sub_rowType_61 || sub_rowType == sub_rowType_71 || sub_rowType == sub_rowType_81 || sub_rowType == sub_rowType_91){
				$("input[identity="+sub_rowType+"]").val(calcAmtSum);   //更改文本框值
				$("input[identity="+sub_rowType+"]").text(calcAmtSum);  //更改标签span的文本值
			}
		}
		
		//获取土地成本（合约）中的合约子目标成本
		var sub_rowType_11_Val = getRowAttr(sub_rowType_11).val();
		//获取开发前期准备费中的合约子目标成本
		var sub_rowType_21_Val = getRowAttr(sub_rowType_21).val();
		//获取主体建筑工程费中的合约子目标成本
		var sub_rowType_31_Val = getRowAttr(sub_rowType_31).val();
		//获取主体安装工程费中的合约子目标成本
		var sub_rowType_41_Val = getRowAttr(sub_rowType_41).val();
		//获取社区管网工程费中的合约子目标成本
		var sub_rowType_51_Val = getRowAttr(sub_rowType_51).val();
		//获取园林环境费中的合约子目标成本
		var sub_rowType_61_Val = getRowAttr(sub_rowType_61).val();
		//获取配套设施费中的合约子目标成本
		var sub_rowType_71_Val = getRowAttr(sub_rowType_71).val();
		//获取开发间接费中的合约子目标成本
		var sub_rowType_81_Val = getRowAttr(sub_rowType_81).val();
		//获取期间费用中的合约子目标成本
		var sub_rowType_91_Val = getRowAttr(sub_rowType_91).val();
		
		//统计合约目标成本合计
		totalSum = isNull(sub_rowType_11_Val) + isNull(sub_rowType_21_Val) + isNull(sub_rowType_31_Val) + isNull(sub_rowType_41_Val) + isNull(sub_rowType_51_Val) + isNull(sub_rowType_61_Val) + isNull(sub_rowType_71_Val) + isNull(sub_rowType_81_Val) + isNull(sub_rowType_91_Val);
		$("input[identity="+sub_rowType_101+"]").val(totalSum);   //更改文本框值
		$("input[identity="+sub_rowType_101+"]").text(totalSum);  //更改标签span的文本值
		var data={};
		data.field=field;
		data.id=oid;
		data.val=val;
		TB_showMaskLayer("正在保存...");
		var url = _ctx + '/cost/cost-cont-plan-tpl-detail!save.action';
		$.post(url,data,function(responseText){
			var resultJson=toJson(responseText);
			if(resultJson.status){
				displayOperInfo(resultJson.updateDate+" 自动保存成功！");
			}else{
				displayOperInfo("<span style='color:red'>"+resultJson.updateDate+" 自动保存失败！</span>");
			}
		});
		TB_removeMaskLayer();
	}
}

/**
 * 判断值是否为空，如果为空返回0
 * @param val
 * @return
 */
function isNull(val){
	return val==''?0:parseFloat(val);
}

/**
 * 找到公用对象（指定右侧的supertable）
 * @param val
 * @return
 */
function getRowAttr(val){
	return $(".sData").find("#MyTable input[identity="+val+"]");
}
