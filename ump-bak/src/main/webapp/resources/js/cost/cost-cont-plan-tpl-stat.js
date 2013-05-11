$(function(){
	
	//$('#addContentformDiv').jqm().jqDrag(".jqmHeader");
	//调整框高
	var width = document.body.clientWidth -20;	
	//var height = document.body.clientHeight;
	$('.fakeContainer').css({'width': width});		
	var myST = new superTable("MyTable",{fixedCols : 2,cssSkin : "sOrange",onFinish : function () {}});

	//添加行操作
	addRowOperator();
	
	//删除行操作
	delRowOperator();
}); 

//由于添加了iframe，页面第一次不会处理
function iframeInit(){
	window.location.reload();
}

//判断最长，包括中文
function validateLenth(orData,max,alertName){
	//判断最长
	var length = orData.replace(/[^\x00-\xff]/g,"**").length; 	
	if(length>max){
		alert(alertName+' 不能超出'+max+'个字符！');
		return false;
		}
	return true;
} 


/**
 * 显示提示成功
 * @param result
 */
function openSuccess(result){
	var myDate = new Date();
	var hour = myDate.getHours();      //获取当前小时数(0-23)
	var minu = myDate.getMinutes();    //获取当前分钟数(0-59)
	var sec  = myDate.getSeconds();    //获取当前秒数(0-59)
	$('#operateResultTip').html(hour +':' + minu + ':' + sec +' ' + result).css({'color':'green'}).show().fadeOut(5000);
}
/**
 * 显示提示不成功
 * @param result
 */
function openFailure(result){
	$('#operateResultTip').css({'color':'red'}).html(result).show().fadeOut(5000);
}

/**
 * 关闭提示
 */
function closeOpeateResultTip(){
	$('#operateResultTip').hide();
}
/**格式化金额
 */
function toDecimal(x) {
	var f = parseFloat(x);
	if (isNaN(f)) {
		return false;
	}
	var f = Math.round(x * 100) / 100;
	var s = f.toString();
	var rs = s.indexOf('.');
	if (rs < 0) {
		rs = s.length;
		s += '.';
	}
	while (s.length <= rs + 2) {
		s += '0';
	}
	return s;
}

//判断是否为数字
function isNum(txt) {
	var _val = $.trim(txt);
	if ("" == _val || _val.length < 1) {

		return true;
	}

	var reg = /^(([0-9]+.?[0-9]*)|(\d*[0-9]+))$/; // /^((\d*[1-9]*)|([0]))(\.\d+)?$/;
	var _isN = reg.test(_val);
	// alert('isNum' + _val + " " + txt+" _isN:"+_isN);
	return _isN;
}

//添加行操作
function addRowOperator(){
	$("#addRowOne").bind("click",function(){ 
		var parentTr=$(this).parent().parent();
		//合约规划ID
		var costContPlanTplId=$(parentTr).attr("rowsub");
        //科目CD
		var subcd=$(parentTr).attr("subcd");
        //行类别CD
		var rowcd=$(parentTr).attr("rowcd");
		//alert("合约规划ID: "+costContPlanTplId+" 科目CD: "+subcd+" 行类别CD: "+rowcd);
		var data = {costContPlanTplId: costContPlanTplId, subjectCd: subcd, rowTypeCd: rowcd};
		//外键ID
		//data['costContPlanTplId'] = costContPlanTplId;
		//data['subjectCd'] = subcd;
		//data['rowTypeCd'] = rowcd;
	    var url = $("#planForm").attr("action");
	    TB_showMaskLayer("正在保存...");
		$.post(url,data,function(data){
			if(data=="success"){
				openSuccess('添加行成功!');
				window.location.reload();
				//$('#operateResultTip').html('更新成功!').show().fadeOut(5000);
			}else{
				 openFailure('添加行失败!');
				//$('#operateResultTip').html('更新失败!').show().fadeOut(5000);
			}
		});
		TB_removeMaskLayer();
	});
}

//删除行操作
function delRowOperator(){
	$(".delRowOne").bind("click",function(){ 
		if(window.confirm('确定删除行吗？')){
			var parentTr=$(this).parent().parent();
			var costContPlanTplStatId=$(parentTr).attr("rownum");
			//合约计划主键ID
			var data = {costContPlanTplStatId: costContPlanTplStatId};
			
			//alert("合约计划主键："+costContPlanTplStatId);
		    var url = _ctx + '/cost/cost-cont-plan-tpl-stat!delRow.action';
		    TB_showMaskLayer("正在删除...");
			$.post(url,data,function(data){
				if(data=="success"){
					openSuccess('删除行成功!');
					window.location.href=_ctx + '/cost/cost-cont-plan-tpl-stat!list.action';
				}else{
					 openFailure('删除行失败!');
				}
			});
			TB_removeMaskLayer();
		}
	});
}

/**
鼠标移上时更改背景颜色
**/
function changeBg(dom) {

	var itemId=$(dom).attr("id");	
	$(".icls"+itemId).show();
	$(dom).find("td").css("background", "#EDEFF3");
	$("#"+itemId).find(".input1").css("background", "#FFFFFF");
}
/**
鼠标移除时更改去除背景颜色
**/
function changeBgOut(dom) {	
	var itemId=$(dom).attr("id");	
	$("div.cls"+itemId).show();
	$(".icls"+itemId).hide();
	$(dom).find("td").css("background", "");
	$("#"+itemId).find(".input1").css("background", "#F7EDE4");
}

//合约计划保存(鼠标移出的时保存操作)
function contractSave(dom){
		//项目成本汇总表ID （当前表格行）
		var thisObj=$(dom).parent().parent();
		var contPlanTplStatId;
		var subject_rowtypeCd;
		if(thisObj[0].tagName=='TD'){
			contPlanTplStatId=thisObj.parent().attr("rownum");
			subject_rowtypeCd=thisObj.parent().attr("id");
		}else if(thisObj[0].tagName=='TR'){
			contPlanTplStatId=thisObj.attr("rownum");
			subject_rowtypeCd=thisObj.attr("id");
		}
		//获取字段名（必须与实体属性匹配）
		var fieldName=$(dom).attr("name");
		//旧值 
		var fieldOldValue=$(dom).attr("title");

		if($.trim(fieldOldValue) == ''){
			fieldOldValue  = '0';
		}
		//新值
		var fieldNewValue  = $(dom).val();
		if($.trim(fieldNewValue) == ''){
			fieldNewValue  = '0';
		}

		//alert("fieldNewValue: "+fieldNewValue+"fieldOldValue: "+fieldOldValue);
		if(fieldOldValue != fieldNewValue){
			var data = {contPlanTplStatId: contPlanTplStatId, fieldName: fieldName, fieldOldValue: fieldOldValue, fieldNewValue: fieldNewValue};
			//主键ID
			data['contPlanTplStatId'] = contPlanTplStatId;//很重要
			data['fieldName'] = fieldName;//很重要,识别更新字段
			//更新字段的值
		    data['costContPlanTplStat.'+fieldName] = fieldNewValue;//很重要
		    var url = $("#planForm").attr("action");
		    TB_showMaskLayer("正在保存...");
			$.post(url,data,function(data){
				if(data=="success"){
					autoCalculate(dom);
					//openSuccess('更新成功!');
					//window.location.reload();
					//$('#operateResultTip').html('更新成功!').show().fadeOut(5000);
				}else{
					autoCalculate(dom);
					//openFailure('更新失败!');
					//$('#operateResultTip').html('更新失败!').show().fadeOut(5000);
				}
			});
			TB_removeMaskLayer();
			//关闭提示
			closeOpeateResultTip();
		}
}


function autoCalculate(dom){
	//定义大类【1-9】的所有变量SUBJECT_CD
	var subjectV_1='1',subjectV_2='2',subjectV_3='3',subjectV_4='4',subjectV_5='5',subjectV_6='6',subjectV_7='7',subjectV_8='8',subjectV_9='9';
	//定义大类【1-9】的所有变量ROWTYPE_CD
	var sub_rowType_11='1_1',sub_rowType_21='2_1',sub_rowType_31='3_1',sub_rowType_41='4_1',sub_rowType_51='5_1',sub_rowType_61='6_1',sub_rowType_71='7_1',sub_rowType_81='8_1',sub_rowType_91='9_1';
	var sub_rowType_12='1_2',sub_rowType_22='2_2',sub_rowType_32='3_2',sub_rowType_42='4_2',sub_rowType_52='5_2',sub_rowType_62='6_2',sub_rowType_72='7_2',sub_rowType_82='8_2',sub_rowType_92='9_2';
	var sub_rowType_13='1_3',sub_rowType_23='2_3',sub_rowType_33='3_3',sub_rowType_43='4_3',sub_rowType_53='5_3',sub_rowType_63='6_3',sub_rowType_73='7_3',sub_rowType_83='8_3',sub_rowType_93='9_3';
	var sub_rowType_14='1_4',sub_rowType_24='2_4',sub_rowType_34='3_4',sub_rowType_44='4_4',sub_rowType_54='5_4',sub_rowType_64='6_4',sub_rowType_74='7_4',sub_rowType_84='8_4',sub_rowType_94='9_4';
	var sub_rowType_15='1_5',sub_rowType_25='2_5',sub_rowType_35='3_5',sub_rowType_45='4_5',sub_rowType_55='5_5',sub_rowType_65='6_5',sub_rowType_75='7_5',sub_rowType_85='8_5',sub_rowType_95='9_5';
	//定义大类SUBJECT_CD为【10】且子类ROWTYPE_CD为【1040、1050、1060】
	var subjectV_10='10';
	var sub_rowType_1010='10_1010',//
		sub_rowType_1020='10_1020',
		sub_rowType_1030='10_1030',
		sub_rowType_1031='10_1031',
		sub_rowType_1040='10_1040',
		sub_rowType_1050='10_1050',
		sub_rowType_1060='10_1060',
		sub_rowType_1090='10_1090';
	var sDataTable=$(".sData");
	var subjectRowTypeCd;
	var calcAmtSum=0.0;
	var totalVal=0.0;
	var subjectCd,rowTypeCd;
	var numValue;
	var contPlanTplStatId;
	var costContPlanTplId;
	var rowNo;
	var diff;
	var calcDiff;
	var thisObj=$(dom).parent().parent();
	if(thisObj[0].tagName=='TD'){
		rowNo=thisObj.parent().attr("rowNo");
		//主键
		contPlanTplStatId=thisObj.parent().attr("rownum");
		//外键
		costContPlanTplId=thisObj.parent().attr("num");
	}else if(thisObj[0].tagName=='TR'){
		rowNo=thisObj.attr("rowNo");
		contPlanTplStatId=thisObj.attr("rownum");
		costContPlanTplId=thisObj.attr("num");
	}
	//获取字段名（必须与实体属性匹配）
	var fieldName=$(dom).attr("name");
	//旧值 
	var fieldOldValue=$(dom).attr("title");

	if($.trim(fieldOldValue) == ''){
		fieldOldValue  = '0';
	}
	//新值
	var fieldNewValue  = $(dom).val();
	if($.trim(fieldNewValue) == ''){
		fieldNewValue  = '0';
	}
	
	if(fieldOldValue!=fieldNewValue){
		//统一处理合约计划从大类1-9
		if(rowNo==subjectV_1||rowNo==subjectV_2||rowNo==subjectV_3||rowNo==subjectV_4||rowNo==subjectV_5||rowNo==subjectV_6||rowNo==subjectV_7||rowNo==subjectV_8||rowNo==subjectV_9){
			numValue = sDataTable.find("#MyTable input[pid=contSubTargAmt_"+rowNo+"]");
			$.each(numValue,function(i,item){
				var subRowCd=$(item).attr("identity");
				//土地成本（合约） 1 
				if(subRowCd==sub_rowType_11||subRowCd==sub_rowType_21||subRowCd==sub_rowType_31||subRowCd==sub_rowType_41||subRowCd==sub_rowType_51||subRowCd==sub_rowType_61||subRowCd==sub_rowType_71||subRowCd==sub_rowType_81||subRowCd==sub_rowType_91){
					if($.trim($(item).val())!=""){
						subjectRowTypeCd=parseFloat($(item).val());
					}else{
						subjectRowTypeCd=parseFloat("0.0");
					}
				}
				//土地成本（原目标） 2
				if(subRowCd==sub_rowType_12||subRowCd==sub_rowType_22||subRowCd==sub_rowType_32||subRowCd==sub_rowType_42||subRowCd==sub_rowType_52||subRowCd==sub_rowType_62||subRowCd==sub_rowType_72||subRowCd==sub_rowType_82||subRowCd==sub_rowType_92){
					var rowTypeCdOld=subRowCd.split("_");
					if($.trim($(item).val())!=""){
						calcAmtSum+=parseFloat($(item).val());
						//统计所有【1-9】土地成本（原目标）
						$('.sData #MyTable input[rowTypeCd=contSubTargAmt_'+rowTypeCdOld[1]+']').each(function(){
							if($.trim($(this).val())!=""){
								totalVal+=parseFloat($(this).val());
							}else{
								totalVal+=parseFloat("0.0");
							}
						});
					}else{
						calcAmtSum+=parseFloat("0.0");
					}
				}
				//调减至其他二级科目成本 3
				if(subRowCd==sub_rowType_13||subRowCd==sub_rowType_23||subRowCd==sub_rowType_33||subRowCd==sub_rowType_43||subRowCd==sub_rowType_53||subRowCd==sub_rowType_63||subRowCd==sub_rowType_73||subRowCd==sub_rowType_83||subRowCd==sub_rowType_93){
					if($.trim($(item).val())!=""){
						calcAmtSum+=parseFloat($(item).val());
					}else{
						calcAmtSum+=parseFloat("0.0");
					}
				}
				//从其他科目调增成本 4 
				if(subRowCd==sub_rowType_14||subRowCd==sub_rowType_24||subRowCd==sub_rowType_34||subRowCd==sub_rowType_44||subRowCd==sub_rowType_54||subRowCd==sub_rowType_64||subRowCd==sub_rowType_74||subRowCd==sub_rowType_84||subRowCd==sub_rowType_94){
					if($.trim($(item).val())!=""){
						calcAmtSum+=parseFloat($(item).val());
					}else{
						calcAmtSum+=parseFloat("0.0");
					}
				}
				//合约目标与原目标差额 5
				if(subRowCd==sub_rowType_15||subRowCd==sub_rowType_25||subRowCd==sub_rowType_35||subRowCd==sub_rowType_45||subRowCd==sub_rowType_55||subRowCd==sub_rowType_65||subRowCd==sub_rowType_75||subRowCd==sub_rowType_85||subRowCd==sub_rowType_95){
					diff=subRowCd;
					var rowNumValue=subRowCd.split("_");
					//科目类型CD
					subjectCd=rowNumValue[0];
					//汇总科目CD
					rowTypeCd=rowNumValue[1];
				}
			});
			//合约目标与原目标差额5 
			calcDiff=parseFloat(calcAmtSum)-parseFloat(subjectRowTypeCd);
			
			//alert("calcAmtSum: "+calcAmtSum+" ,subjectRowTypeCd: "+subjectRowTypeCd+" ,calcDiff: "+calcDiff);
			var data = {contPlanTplStatId:contPlanTplStatId,costContPlanTplId:costContPlanTplId,subjectCd: subjectCd,rowTypeCd:rowTypeCd,calcDiff:calcDiff};
			//data['contPlanTplStatId']=contPlanTplStatId;
			//data['subjectCd']=subjectCd;
			//data['rowTypeCd']=rowTypeCd;
			//data['calcDiff']=calcDiff;
			//alert("subjectCd: "+subjectCd+" ,rowTypeCd: "+rowTypeCd);
			TB_showMaskLayer("正在保存...");
			var url = _ctx + '/cost/cost-cont-plan-tpl-stat!save.action';
			$.post(url,data,function(data){
				if(data=="success"){
					//合约目标与目标差额【1-9】
					$("input[identity="+diff+"]").val(calcDiff);
					$("input[identity="+diff+"]").next().text(calcDiff);
					//土地成本（原目标）总计 --- 【1020】
					$("input[identity="+sub_rowType_1020+"]").val(totalVal);  //更改文本框值
					$("input[identity="+sub_rowType_1020+"]").next().text(totalVal);  //更改标签span的文本值
					//获取合约目标成本合计【10】【1010】
					var obj1010=$('.sData #MyTable input[identity='+sub_rowType_1010+']');
					//获取目标成本调整合计【10】【1030】
					var obj1030=$('.sData #MyTable input[identity='+sub_rowType_1030+']');
					var contract1010Val=0,contract1030Val=0,contract1050Val=0,sumContract1040=0,sumContract1060=0;
					if($.trim(obj1010.next().text())!=""){
						contract1010Val+=parseFloat(obj1010.next().text());
					}else{
						contract1010Val+=parseFloat("0.0");
					}
					if($.trim(obj1030.next().text())!=""){
						contract1030Val+=parseFloat(obj1030.next().text());
					}else{
						contract1030Val+=parseFloat("0.0");
					}
					sumContract1040=parseFloat(totalVal)+parseFloat(contract1030Val)-parseFloat(contract1010Val);
					//计算合约目标与目标差额【10】【1040】
					$("input[identity="+sub_rowType_1040+"]").val(sumContract1040);
					$("input[identity="+sub_rowType_1040+"]").next().text(sumContract1040);
					//获取目标成本调整合计【10】【1050】
					var obj1050=$('.sData #MyTable input[identity='+sub_rowType_1050+']');
					if($.trim(obj1050.val())!=""){
						contract1050Val+=parseFloat(obj1050.val());
					}else{
						contract1050Val+=parseFloat("0.0");
					}
					//计算不可预见费余额【10】【1060】
					sumContract1060=parseFloat(sumContract1040)+parseFloat(contract1050Val);
					$("input[identity="+sub_rowType_1060+"]").val(sumContract1060);
					$("input[identity="+sub_rowType_1060+"]").next().text(sumContract1060);
					openSuccess('更新成功!');
				}else{
					openFailure('更新失败!');
				}
			});
			TB_removeMaskLayer();
			//关闭提示
			closeOpeateResultTip();
		}else{
			//合约目标与目标差额:目标成本调整合计 + 原目标成本合计 - 合约目标成本合计
			var contractObjDiff=0.0,sumContractAmt=0.0,sumOldContractObj=0.0,sumContractObj=0.0;
			//处理新增目标成本调整合计总额
			var sumNewAddContractObj=0.0;
			//不可预见费余额:不可预见费 + 合约目标和目标差额(处理合约计划SUBJECT_CD=10且ROWTYPE_CD=1060)
			var contractObjAmtDiff=0.0,unKnowObjAmt=0.0;
			if(rowNo==subjectV_10){
				
				//合约目标成本合计
				numValue = sDataTable.find("#MyTable input[pid=contSubTargAmt_"+rowNo+"]");
				$.each(numValue,function(i,item){
					var subRowCd=$(item).attr("identity");
					if(subRowCd==sub_rowType_1010){
						//合约目标成本合计
						if($.trim($(item).val())!=""){
							sumContractObj=parseFloat($(item).val());
						}else{
							sumContractObj=parseFloat("0.0");
						}
					}
					if(subRowCd==sub_rowType_1020){
						//原目标成本合计
						if($.trim($(item).val())!=""){
							sumOldContractObj=parseFloat($(item).val());
						}else{
							sumOldContractObj=parseFloat("0.0");
						}
					}
					if(subRowCd==sub_rowType_1030){
						//目标成本调整合计
						if($.trim($(item).val())!=""){
							sumContractAmt=parseFloat($(item).val());
						}else{
							sumContractAmt=parseFloat("0.0");
						}
					}
					if(subRowCd==sub_rowType_1031){
						if($.trim($(item).val())!=""){
							sumNewAddContractObj+=parseFloat($(item).val());
						}else{
							sumNewAddContractObj+=parseFloat("0.0");
						}
						
					}
					if(subRowCd==sub_rowType_1040){
						//合约目标与目标差额
						if($.trim($(item).val())!=""){
							contractObjAmtDiff=parseFloat($(item).val());
						}else{
							contractObjAmtDiff=parseFloat("0.0");
						}
					}
					if(subRowCd==sub_rowType_1050){
						//不可预见费
						if($.trim($(item).val())!=""){
							unKnowObjAmt=parseFloat($(item).val());
						}else{
							unKnowObjAmt=parseFloat("0.0");
						}
					}
					if(subRowCd==sub_rowType_1060){
						diff=subRowCd;
						var rowNumValue=subRowCd.split("_");
						//科目类型CD
						subjectCd=rowNumValue[0];
						//汇总科目CD
						rowTypeCd=rowNumValue[1];
					}
				});
				
				//不可预见费余额:不可预见费 + 合约目标和目标差额
				calcDiff=parseFloat(unKnowObjAmt)+parseFloat(contractObjAmtDiff);
				var data = {contPlanTplStatId:contPlanTplStatId,costContPlanTplId:costContPlanTplId,subjectCd:subjectCd,rowTypeCd:rowTypeCd,calcDiff:calcDiff};
				//data['contPlanTplStatId']=contPlanTplStatId;
				//data['subjectCd']=subjectCd;
				//data['rowTypeCd']=rowTypeCd;
				//data['calcDiff']=calcDiff;
				TB_showMaskLayer("正在保存...");
				var url = _ctx + '/cost/cost-cont-plan-tpl-stat!save.action';
				$.post(url,data,function(data){
					if(data=="success"){
						//处理目标成本调整合计（新增行添加的数据）
						$("input[identity="+sub_rowType_1030+"]").val(sumNewAddContractObj); //更改文本值
						$("input[identity="+sub_rowType_1030+"]").next().text(sumNewAddContractObj); //更改文本值
						//处理合约目标与目标差额
						//alert(sumNewAddContractObj+" ,"+sumOldContractObj+" ,"+sumContractObj);
						contractObjDiff=parseFloat(sumNewAddContractObj) + parseFloat(sumOldContractObj) - parseFloat(sumContractObj);
						$("input[identity="+sub_rowType_1040+"]").val(contractObjDiff); //更改文本值
						$("input[identity="+sub_rowType_1040+"]").next().text(contractObjDiff); //更改文本值
						//处理不可预见费余额
						$("input[identity="+diff+"]").val(calcDiff);  //更改文本值
						$("input[identity="+diff+"]").next().text(calcDiff);  //更改文本值
						if(calcDiff>=0){
							$("input[identity="+sub_rowType_1090+"]").next().text("未超过目标成本");
							$("input[identity="+sub_rowType_1090+"]").next().css({'color':''}); 
						}else{
							$("input[identity="+sub_rowType_1090+"]").next().text("超过目标成本");
							$("input[identity="+sub_rowType_1090+"]").next().css({'color':'red'});
						}
						openSuccess('更新成功!');
					}else{
						openFailure('更新失败!');
					}
				});
				TB_removeMaskLayer();
				//关闭提示
				closeOpeateResultTip();
			}
		}
	}
}


/*
function addRow(){
	var rowNum=0;
	$("#addRowOne,#addRowTwo").bind("click",function(){ 
		rowNum +=1; 
		var parentTr=$(this).parent().parent();
        //科目CD
		var subcd=$(parentTr).attr("subcd");
        //行类别CD
		var rowcd=$(parentTr).attr("rowcd");

        var subRowCd=parseInt(rowcd)+1;
		var rowHtml=""; 
		rowHtml +="<tr id='row_"+rowNum+"' subcd='"+subcd+"' rowcd='"+subRowCd+"' markId='"+subcd+"_"+subRowCd+"' onmouseover='changeBg(this);' onmouseout='changeBgOut(this);' >"; 
		rowHtml +="<td height='25' align='center'></td>"; 
		rowHtml +="<td height='25' align='left'><div style=\"float:right;width:10%;height:25px;\" class=\"btn_refresh jqModal\" onclick='deleteRow("+rowNum+",this)'>删除行</div><div style=\"float:left;width:80%\"><input name='statName' class='input1' type='text' id='statName'  value='成本调整额（第"+rowNum+"次）' maxlength='100'/></div></td>";
		rowHtml +="<td><input name='contSubTargAmt' type='text' id='contSubTargAmt'  value='' maxlength='18'/></td>"; 
		rowHtml +="<td><input name='diff_"+rowNum+"' type='text' id='diff_"+rowNum+"'  value='' maxlength=''/></td>"; 
		rowHtml +="<td><input name='remark' type='text' id='remark'  value='' maxlength='200'/>&nbsp;&nbsp;"; 
		rowHtml +="";
		rowHtml +="</td>"; 
		rowHtml +="</tr>"; 
		
		

		var markId=subcd+"_"+subRowCd;
		//是否有子节点
		var isHasChild=false;
		var half=$("tr[markId="+markId+"]").length;
		//新增行
        $.each($("tr[markId="+markId+"]"),function(i,item){
            if(i==(half-1) || i==(half/2-1)){             
                	$(item).after(rowHtml); 
                	isHasChild=true;
                }
           	});
        //如果没有子节点则直接在当前节点后插入
        if(!isHasChild){
        	var rowNumId=$(parentTr).attr("id");
        	$.each($("tr[id="+rowNumId+"]"),function(i,item){
        		$(item).after(rowHtml); 
        	});
        }

        //插入行后绑定事件处理
        contractSave();
	})
}

//删除行
function deleteRow(rowNum,dom){
	var rowNumId=$(dom).parent().parent().attr("id");
	alert(rowNumId);
	$("tr[id="+rowNumId+"]").remove();
}
*/
