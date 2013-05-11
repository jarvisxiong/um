 
/**
 * 提交月度资金预算
 * @param dom
 * @return
 */
function commitBudget(dom,nowStatus) {
	//提交前校验有合同情况的战略是否已选择
	var flag=false;
	$("select#strageFlg").each(function(){
		var o=$(this);
		var flg=false;
		$("select#strageFlg[budgetid='"+o.attr('budgetid')+"']").each(function(){
			var selectedVal=$(this).find('option:selected').val();
			if(selectedVal==='undefined'||selectedVal==''){				
			}else{
				if(!flg)
				flg=true;
			}
		});
		if(!flg){
			if(!flag){
				flag=true;
			}			
			$("select#strageFlg[budgetid='"+o.attr('budgetid')+"']").each(function(){
				$(this).addClass('bigger');
			});
		}		
	});
	if(flag){
		alert("存在合同未选择是否战略！");
		return;
	}
	//执行提交
	var data = {};
	data['planSectionId'] = $("#planSectionId").find('option:selected').val();
	data['planYearMonth'] = $("#planYearMonth").val();
	data['nowStatus'] = nowStatus;
	if(nowStatus=='2'||nowStatus=='3'){
		if(window.confirm('确定提交吗？')){
			if(window.confirm('是否填写汇总意见？')){
				var url=_ctx+'/cost/cost-budget-month!monthStat.action?planYearMonth='+$("#planYearMonth").val();
				openWindow("viewMonthStat","查看月度资金汇总",url);
			}else{
				var url = _ctx + "/cost/cost-budget-month!commitCost.action";
				TB_showMaskLayer("正在提交...");
				$.post(url, data, function(result) {
					loadMonthDetailList();		
				});
			}
		}
	}else{
		if(nowStatus=='1'){
			if(window.confirm('确定提交吗？')){
				var url = _ctx + "/cost/cost-budget-month!commitCost.action";
				TB_showMaskLayer("正在提交...");
				$.post(url, data, function(result) {
					loadMonthDetailList();		
				});
			}
		}else{
			var url = _ctx + "/cost/cost-budget-month!commitCost.action";
			TB_showMaskLayer("正在提交...");
			$.post(url, data, function(result) {
				loadMonthDetailList();		
			});
		}
		
	}	
}


/**
 * 驳回月度资金
 * @param dom
 * @param nowStatus
 * @return
 */
function rejectBudget(dom,nowStatus){
	if(window.confirm("确认驳回？")){
		var data={};
		data['planSectionId'] = $("#planSectionId").find('option:selected').val();
		data['planYearMonth'] = $("#planYearMonth").val();
		data['nowStatus'] = nowStatus;
		var url = _ctx + "/cost/cost-budget-month!rejectBudget.action";
		TB_showMaskLayer("正在提交...");
		$.post(url, data, function(result) {
			TB_removeMaskLayer();
			if('success'==result){
				loadMonthDetailList();	
			}
				
		});
	}
}
function showBtnDelete(){
	$('#btnDeleteRow').show();
}
function hideBtnDelete(){
	$('#btnDeleteRow').hide();
}

function getPlanYearMonth(){
	return $('#planYearMonth').val();
}
function getPlanSectionId(){
	return $('#planSectionId').val();
}
function getCostBudgetMonthId(){
	return $('#costBudgetMonthId').val();
}

/**
 * 不显示提示面板
 */
function hideTipPanel(){
	$('#createdTipPanel').hide();
}

/**
 * 创建月度资金计划
 */
function createMonthBudget(){
	var planSectionId = getPlanSectionId();
	var planYearMonth = getPlanYearMonth();

	if($.trim(planSectionId) == ''){
		alert('请选择项目!');
		return;
	}
	if($.trim(planYearMonth) == ''){
		alert('请选择年月!');
		return;
	}
	
	var url = _ctx + "/cost/cost-budget-month!createMonthBudget.action";
	TB_showMaskLayer("正在搜索...");
	$.post(url, {planSectionId: planSectionId, planYearMonth: planYearMonth}, function(result) {
		TB_removeMaskLayer();
		if('success' == result){
			alert('创建成功!');
		}else{
			alert(result);
		}
		loadMonthDetailList('show');
	});
}


/**
 * 导出年度资金预算计划
 * @return
 */
function exportBudgerMonth() {
	var sectionId = getPlanSectionId();
	if ($.trim(sectionId) == '') {
		alert('请选择项目!');
		return;
	}
	var yearMonth = getPlanYearMonth();
	if ($.trim(yearMonth) == '') {
		alert('请选择年月!');
		return;
	}
	var nocontactVisiable=getNocontacteCheck();
	var strageFlg=$("#strageFlgSelect").find('option:selected').val();	
	var url = _ctx + '/cost/cost-budget-month!exportSectionMonthList.action?exportFlg=1&planYearMonth=' + yearMonth + '&planSectionId=' + sectionId+"&strageFlg="+strageFlg;
	//如果值大于0，则表示显示无预算的记录,传阐述为show
	if(nocontactVisiable>0){
		url=url+"&nocontactVisiable=show";
	}
	
	openWindow('exportMonth', '导出月度资金预算', url);
}

/**
 * 打开窗口
 * @param id
 * @param desc
 * @param url
 * @return
 */
function openWindow(id, desc, url) {
	if (window.parent && window.parent.parent.TabUtils) {
		window.parent.parent.TabUtils.newTab(id, desc, url);
	} else {
		window.open(url);
	}
}

/**
 * 搜索月度资金计划(主表，字表列表)
 */
function loadMonthDetailList(defaultVal){
	
	hideTipPanel();
	
	var planSectionId = getPlanSectionId();
	var planYearMonth = getPlanYearMonth();
	var nocontactVisiable=getNocontacteCheck();
	var strageFlgSelect=$("#strageFlgSelect").find("option:selected").val();
	if($.trim(planSectionId) == ''){
		//alert('请选择项目!');
		return;
	}
	if($.trim(planYearMonth) == ''){
		//alert('请选择年月!');
		return;
	}
	
	var clientWidth = document.body.clientWidth;
	var clientHeight= document.body.clientHeight;
	var data = {planSectionId: planSectionId, planYearMonth: planYearMonth};
	var latestDetailId= $("#hide_latestDetailId").val();
	if(latestDetailId!=''&&'undefined'!=latestDetailId){
		data['latestDetailId']=latestDetailId;
	}
	//如果值大于0，则表示显示无预算的记录,传阐述为show
	if(nocontactVisiable>0){
		data['nocontactVisiable']='show';
	}else{
		if(defaultVal){
			data['nocontactVisiable']='show';
		}
	}
	//战略显示问题参数
	if(strageFlgSelect==='undefined'){
	}else{
		data['strageFlg']=strageFlgSelect;
	}
	var url = _ctx + "/cost/cost-budget-month!loadMonthDetailList.action";
	TB_showMaskLayer("正在搜索...");
	$.post(url, data, function(result) {
		TB_removeMaskLayer();
		$('#monthDetailList').html(result);
		
		
		//调整框高
		var width =  clientWidth -20;	
		var height = clientHeight - $('#firstPanel').height() - $('#secondPanel').height();
		
		var adjHeight = 350;//height>350?350:height;
		// 冻结效果
		// 2: 列
		$('.fakeContainer').css({'width': width});
		var myST = new superTable("MyTable",{fixedCols : 3,cssSkin : "sOrange",onFinish : function () {}});
	});
}


/**
 * 保存月度预算明细(合同)
 * @param contNo
 * @return
 */
function saveContBudget(contNo) {
	
	var url = _ctx + '/cost/cost-budget-month!saveContBudget.action';
	var costBudgetMonthId = getCostBudgetMonthId();  
	var data = {contNo: contNo, costBudgetMonthId: costBudgetMonthId};
	$.post(url, data, function(result) {
		var rs=result.split(",");
		if ('success' == rs[0]) {
			//提示保存成功
			openSuccess('添加成功! ');
		} else{
			openFailure('添加不成功!');
		}		
		//重新加载
		loadMonthDetailList();
	});
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

/**
 * 新增无合同
 * @param dom
 * @return
 */
function appendNewRow(dom) {
	
	//关闭提示
	closeOpeateResultTip();
	
	var jdom = $(dom);
	var sequenceNo = jdom.attr('sequenceNo');
	
	var costMonthId = getCostBudgetMonthId();
	var data = {costMonthId: costMonthId, sequenceNo: sequenceNo};
	
	var url = _ctx + "/cost/cost-budget-month!saveNoContact.action";
	TB_showMaskLayer("正在创建...");
	$.post(url, data, function(result) {
		var rs=result.split(",");
		TB_removeMaskLayer();
		if('success' == rs[0]){
			openSuccess('新建成功!');
			$("#hide_latestDetailId").val(rs[1]);
			//刷新列表
			loadMonthDetailList();
		}else{
			openFailure('新建成功!');
		}
	});
}


/**
 * 删除一行
 * @param dom
 * @return
 */
function deleteRow(dom) {

	//关闭提示
	closeOpeateResultTip();	
	var jdom = $(dom);	
	var costMonthDetailId = jdom.attr('detailid');
	if (costMonthDetailId == null || costMonthDetailId == '' || typeof costMonthDetailId == undefined) {
		alert('请先选中删除行!');
		return;
	}

	if (window.confirm('确实删除？')) {
		var data = {costMonthDetailId: costMonthDetailId};
		var url = _ctx + "/cost/cost-budget-month!deleteMonthDetail.action";
		TB_showMaskLayer("提交删除...");
		$.post(url, data, function(result) {
			TB_removeMaskLayer();
			if('success' == result){
				openSuccess('删除成功!');
			}else{
				openFailure('删除不成功!');
			}
			//刷新列表
			loadMonthDetailList();
		});
	}
}


//formatFloat(this,'2','10000000000');modifyBudget(this);
/**
 * 保存dom输入框的值
 * @param dom
 */
function saveMonthDetailPropValue(dom){	
 	formatInputFloat(dom);
 	saveMonthDetailProp(dom);
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
 * 自动更新相应字段
 * @param dom
 * @return
 */
function saveMonthDetailProp(dom) {
	
	$(dom).css({'color': 'black'});
	closeOpeateResultTip();	
	var jdom = $(dom);	
	//明细ID
	var costMonthId = jdom.attr('budgetid');	
	//属性名称
	var fieldName = jdom.attr('name');		
	//旧值
	var fieldOldValue = jdom.attr('title')==''?'0':jdom.attr('title');	
	//新值
	var fieldNewValue;
	if (fieldName != 'subjectName' 
			&& fieldName != 'contactNo' 
				&& fieldName != 'contactName'
					&& fieldName != 'partb' 
						&& fieldName != 'memoDesc'
							&&fieldName!='strageFlg') {		
		//取值
		fieldNewValue = (jdom.val()=='')?'0':jdom.val();
	}	
	else{
		//如果是战略的更改,相应的样式标红
		if(fieldName=='strageFlg'){
			if($(dom).find('option:selected').val()!=''){
				$(dom).removeClass('bigger');
			}else{
				$(dom).addClass('bigger');
			}
		}
		fieldNewValue = jdom.val();
	}
	 
	if(fieldOldValue != fieldNewValue){
		var url = _ctx + '/cost/cost-budget-month!saveMonthDetailPropValue.action';
		var data = {costMonthId: costMonthId, fieldName: fieldName, fieldOldValue: fieldOldValue, fieldNewValue: fieldNewValue};
	    data['monthDetail.'+fieldName] = fieldNewValue;//很重要

		var chnField = $(dom).attr('al');
		
		TB_showMaskLayer("正在保存...");
		$.post(url, data, function(result) {
			TB_removeMaskLayer();
			var arr = result.split(',');
			//因为控件拷贝原因，修改是必须循环把所有同样的值都更新
			if('success' == arr[0]){
				//将所有本单元格更新
				$("input[name="+jdom.attr('name')+"][budgetid='"+jdom.attr('budgetid')+"']").each(function(){ $(this).val(fieldNewValue);});
				if(fieldName=='strageFlg'||fieldName=='subjectCd')
				$("select[name="+jdom.attr('name')+"][budgetid='"+jdom.attr('budgetid')+"']").each(function(){ $(this).val(fieldNewValue);});
				//更新统计
				var total = 0;
				var selector = "t_" + fieldName;	
				total = parseFloat($('div[nc='+selector+"]").eq(0).html());
				//统计值=修改前统计值-修改前值+修改后的当前值
				total = total - parseFloat(fieldOldValue) + parseFloat(fieldNewValue);
				$('div[nc='+selector+"]").each(function(){
					$(this).html(total);
				});
				//如果更新的是“累计应付款合计(元)cumuMustPayTotalAmt”,则需要修改“累计：应付未付”和“本期资金预算（地产上报）”
				//如果更新的是“累计实际支付cumuRealPayTotalAmt”,则需要修改“累计：应付未付”和“本期资金预算（地产上报）”
				if(fieldName == 'cumuMustPayTotalAmt'){
					//修改“累计：应付未付”
					$("input[name='cumuMustNoPayAmt'][line='"+jdom.attr('line')+"']").each(function(){
						$(this).val(arr[1]);
						});
					//修改“累计：应付未付”统计
					var toNoPayAmt = $("div[nc='t_cumuMustNoPayAmt']").html();
					var t1 = parseFloat(toNoPayAmt) - parseFloat(fieldOldValue) + parseFloat(fieldNewValue);
					$("div[nc='t_cumuMustNoPayAmt']").each(function(){$(this).html(t1);});
					//修改“本期资金预算（地产上报）”统计
					toNoPayAmt = $("div[nc='t_curPeriodFundBudgetAmt']").html();
					t1= parseFloat(toNoPayAmt) - parseFloat(fieldOldValue) + parseFloat(fieldNewValue);
					$("div[nc='t_curPeriodFundBudgetAmt']").each(function(){$(this).html(t1);});
				}
				//如果是修改“累计实际支付”
				if(fieldName == 'cumuRealPayTotalAmt'){
					//修改“累计：应付未付”
					$("input[name='cumuMustNoPayAmt'][line='"+jdom.attr('line')+"']").each(function(){
						$(this).val(arr[1]);
						});
					//修改“累计：应付未付”统计
					var toNoPayAmt = $("div[nc='t_cumuMustNoPayAmt']").html();
					var t1=parseFloat(toNoPayAmt) + parseFloat(fieldOldValue)- parseFloat(fieldNewValue);
					$("div[nc='t_cumuMustNoPayAmt']").each(function(){$(this).html(t1);});
					//修改“本期资金预算（地产上报）”统计
					 toNoPayAmt = $("div[nc='t_curPeriodFundBudgetAmt']").html();
					 t1=parseFloat(toNoPayAmt) + parseFloat(fieldOldValue)-parseFloat(fieldNewValue);
					$("div[nc='t_curPeriodFundBudgetAmt']").each(function(){$(this).html(t1);});
				}
				//如果修改“本期：资金应付预算curPeriodFundMpayAmt”
				if(fieldName == 'curPeriodFundMpayAmt'){
					//修改“本期资金预算（地产上报）”
					var toNoPayAmt = $("div[nc='t_curPeriodFundBudgetAmt']").html();
					var t1=parseFloat(toNoPayAmt)- parseFloat(fieldOldValue) + parseFloat(fieldNewValue);
					$("div[nc='t_curPeriodFundBudgetAmt']").each(function(){$(this).html(t1);});
				}
				//如果修改“累计：应付未付（元）cumuMustNoPayAmt”
				if(fieldName == 'cumuMustNoPayAmt'){
					//修改“本期资金预算（地产上报）”
					var toNoPayAmt = $("div[nc='t_curPeriodFundBudgetAmt']").html();
					var t1=parseFloat(toNoPayAmt)- parseFloat(fieldOldValue) + parseFloat(fieldNewValue);
					$("div[nc='t_curPeriodFundBudgetAmt']").each(function(){$(this).html(t1);});
				}				
				
				//jdom.attr('title',fieldNewValue);
				
				//提示保存成功
				openSuccess('自动保存成功! (' + chnField +')');
				
				var line = jdom.attr('line');
				// 累计：应付未付计算
				$("[nopay=" + line + "]").each(function(){$(this).html(arr[1]);});
				// 本期资金预算（地产上报）
				$("[name=curPeriodFundBudgetAmt][line=" + line + "]").each(function(){$(this).val(arr[2]);});
				//更新title
				$("input[name="+jdom.attr('name')+"][line="+jdom.attr('line')+"]").each(function(){ 
					$(this).attr('title',fieldNewValue);
					$(this).parent().attr('title',fieldNewValue);
				});
				//若" 调整本期资金预算（地产上报）” >　"本期：资金应付预算", 前者红色底显示。
				//"本期：资金应付预算"
				var curPeriodFundBudgetAmt=$("input[name='curPeriodFundBudgetAmt'][budgetid='"+jdom.attr('budgetid')+"']").eq(0).val();
				//调整本期资金预算（地产上报）
				var curPeriodFundBudgetAmt1=$("input[name='curPeriodFundBudgetAmt1'][budgetid='"+jdom.attr('budgetid')+"']").eq(0).val();
				try {
					if(Number(curPeriodFundBudgetAmt)<Number(curPeriodFundBudgetAmt1)){
						//标红色
						$("input[name='curPeriodFundBudgetAmt1'][budgetid='"+jdom.attr('budgetid')+"']").each(function(){
							$(this).addClass('bigger');
						});
					}else{
						$("input[name='curPeriodFundBudgetAmt1'][budgetid='"+jdom.attr('budgetid')+"']").each(function(){
							$(this).removeClass('bigger');
						});
					}
				} catch (e) {
				}
			}else{

				//提示保存不成功
				$(dom).css({'color': 'red'});
				$(dom).val(fieldOldValue);
				//提示保存不成功
				openSuccess('保存不成功! (' + chnField +')');
			}
		});
	}
}

/**
 * 显示无合同
 * @param dom
 * @return
 */
function showNocontact(dom){
	loadMonthDetailList();
}

/**
 * 获取现象值
 * @return
 */
function getNocontacteCheck(){
	var jdom=$("#cheContactVisiable");
	var ch=jdom.attr('checked');
	if('undefined'===ch){
		return -1;
	}else if ('checked'==ch){
		return 1;
	}else{
		return -1;
	}
}


