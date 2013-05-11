 
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
//------------------------

/**
 * 获取选中年份
 * @returns 
 */
function getSelctedYear(){
	return $('#budgetYear').val();
}

/**
 * 导出年度资金预算计划
 */
function exportBudgerYear(){
	var budgetYear = getSelctedYear();
	if($.trim(budgetYear) == ''){
		alert('请选择年份!');
		return;
	}
	var url = _ctx + '/cost/cost-budget-year!exportExcel.action?exportFlg=1&budgetYear=' + budgetYear;
	openWindow('exportYear', '导出年度资金预算', url);
}

/**打开窗口
 * @param id  窗口ID
 * @param desc 标题描述
 * @param url 打开链接地址
 */
function openWindow(id, desc, url) {
	if (window.parent && window.parent.parent.TabUtils) {
		window.parent.parent.TabUtils.newTab(id, desc, url);
	} else {
		window.open(url);
	}
}

//formatFloat(this,'2','10000000000');modifyBudgetYear(this)
/**
 * 保存dom输入框的值
 * @param dom
 */
function saveYearPropValue(dom){
 	formatInputFloat(dom);
 	saveYearProp(dom);
}

/**
 * 保存dom输入框的值
 * @param dom
 */
function saveYearProp(dom){
	
	$(dom).css({'color': 'black'});

	closeOpeateResultTip();
	
	//年计划ID
	var costBudgetYearId = $(dom).attr('costBudgetYearId');
	//属性名称
	var fieldName = $(dom).attr('name');
	 
	//旧值 
	var fieldOldValue  = $(dom).parent().attr('title');
	if($.trim(fieldOldValue) == ''){
		fieldOldValue  = '0';
	}
	//新值
	var fieldNewValue  = $(dom).val();
	if($.trim(fieldNewValue) == ''){
		fieldNewValue  = '0';
	}

	var chnField = $(dom).attr('al');
	var sectionName = $(dom).attr('sn');
	
	//若新旧值不等,则更新;成功后，修改汇总值
	if(fieldOldValue != fieldNewValue){
		var url = _ctx + '/cost/cost-budget-year!saveBudgetYearPropValue.action';
		var data = {id: costBudgetYearId, fieldName: fieldName, fieldOldValue: fieldOldValue, fieldNewValue: fieldNewValue};
		    data[fieldName] = fieldNewValue;//很重要
		    
		TB_showMaskLayer("正在保存...");
		$.post(url, data, function(result) {
			TB_removeMaskLayer();
			var rs=result.split(",");
			if('success' == rs[0]){
				//更新汇总值
				var selector = 't_'+ fieldName;
				//1.取汇总值
				var totalDiv = $('div[nc='+selector+']').eq(0).html();
				var total = parseFloat(($.trim(totalDiv) == '')? 0 : totalDiv);
				//2.新值
				total = total - parseFloat(fieldOldValue) + parseFloat(fieldNewValue);
				$('div[nc='+selector+']').each(function(){
					$(this).html(total);
				});

				//更新字段值
				$(dom).attr('title',fieldNewValue);
				$(dom).parent().attr('title',fieldNewValue);
				
				//更新本年度预算批准额
				$("input[line='"+$(dom).attr('line')+"'][name='groupTotalAmt']").each(function(){ $(this).val(rs[1]);});
				//提示保存成功
				openSuccess('更新成功! (' + sectionName + '-'+ chnField +')');
				
				//更新"本年度预算批准额",这里判断，避免出现死循环!
				if(fieldName != 'groupTotalAmt' && fieldName != 'memoDesc'){
					var groupJDom = $(dom).parent().parent().find('td input[name=groupTotalAmt]');
					saveYearProp(groupJDom);
				}
			}
			else{
				//提示保存不成功
				$(dom).css({'color': 'red'});
				$(dom).val(fieldOldValue);
				openFailure('更新不成功! (' + rs[1] + ')');
			}
		});
	}
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
	$('#operateResultTip').html(hour +':' + minu + ':' + sec +' ' + result).css({'color':'green'}).show();
}
/**
 * 显示提示不成功
 * @param result
 */
function openFailure(result){
	$('#operateResultTip').css({'color':'red'}).html(result).show();
}
/**
 * 关闭提示
 */
function closeOpeateResultTip(){
	$('#operateResultTip').hide();
}
/**格式化金额
 * @param dom  输入框dom对象
 * @param tailNum 小数点位数
 * @param max  最大值
 */
function formatInputFloat(dom,tailNum,max){
	if(!tailNum || typeof tailNum == undefined){
		tailNum = 2;
	}
	if(!tailNum || typeof(max) == undefined){
		max = 10000000000;
	}
	
	var value = $(dom).val();
	var formated = parseFloat(value);
	
	if(isNaN(formated)){		
		$(dom).val('');
	}
	else if(formated > max){
		$(dom).val('');
	}
	else{
		if(tailNum){
			$(dom).val(formated.toFixed(tailNum));
		}
		else{
			$(dom).val(formated.toFixed(6));
		}
	}
}

/**
 * 加载数据-资金计划汇总表 
 */
function loadPlanList(){
	var budgetYear = getSelctedYear();
	if($.trim(budgetYear) == ''){
		//alert('请选择年份！');
		return;
	}
	
	
	var url= _ctx + '/cost/cost-budget-year!planList.action';
	TB_showMaskLayer("正在搜索...");
	$.post(url, {budgetYear: budgetYear}, function(result){
		TB_removeMaskLayer();
		
		$("#planYearList").html(result);
		
		//调整框高
		var width = document.body.clientWidth -20;	
		var height = document.body.clientHeight - $('#bannerPanel').height() - $('#cellUnitPanel').height() - 200;
		
		// 冻结效果
		// 2: 行
		// width: 300
		// height: 200
		$('.fakeContainer').css({'width': width});
		var myST = new superTable("MyTable",{fixedCols : 3,cssSkin : "sOrange",onFinish : function () {}});
		//FixTable("MyTable", 2, width, 400);
	});
}


/**新建年度计划
 * @param planYear 年份
 * @param sectionId 月份
 */
function createYearBudget(planYear, sectionId){
	var url = _ctx + "/cost/cost-budget-year!createYearBudget.action";
	TB_showMaskLayer("正在创建...");
	$.post(url, {planYear: planYear, sectionId: sectionId}, function(result) {
		TB_removeMaskLayer();
		//若创建成功，刷新列表
		if('success' == result){
			loadPlanList();
		}
	});
}

/**
 * 保存年度预算的"本年度之前已付额"
 * @return
 */
function savePreYearPaiedAmt(dom){
	var url = _ctx + '/cost/cost-budget-year!saveBudgetYearPropValue.action';
	var jdom=$(dom);
	if(jdom.next().val()){
		if(window.confirm("本年度之前已付额以后将不可修改，确认吗?")){
			var costBudgetYearId=jdom.attr('costBudgetYearId');
			var data={id:costBudgetYearId};
			data['entity.cfmPreYearAmtFlg']=1;
			$.post(url, data, function(result) {
				jdom.attr('onclick','');
				jdom.removeClass('btn_orange').addClass('btn_gray');
				jdom.next().attr('onclick','');
				jdom.next().attr("readonly","readonly");
				jdom.next().attr("disabled","disabled");			
			});
		}
	}else{
		alert('请先填写本年度之前已付额，再确认');
	}
	
	
}