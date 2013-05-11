
///////////////////////

//是否电缆
function isDianLanFlg(){
	var name = getMateModuleName();
	if('电缆' == $.trim(name)){
		return true;
	}else{
		return false;
	}
}
//模块是否存在
function isMateExists(mateId){
	var len = $('table[costmateid='+mateId+']').length;
	if( len == 0){
		return false;
	}else{
		return true;
	}
}
//行是否存在
function isPriceExists(mateId, priceId){
	var len =  $('table[costmateid='+ mateId +'] tr[costmatepriceid='+ priceId +']').length;
	if( len == 0){
		return false;
	}else{
		return true;
	}
}

//选中战略内
function insertRow(mateId, priceId){
	if(isMateExists(mateId)){
		if(isPriceExists(mateId, priceId)){
			setTipHtml('quickSearchPriceTip','已选择!');
		}else{
			appendPriceRowIn(mateId,priceId);
		}
	}else{
		createPriceRowIn(mateId,priceId);
	} 
}

//设置抬头行属性
function setPriceHeadRow(divId,mateId){
	var arr = $('#'+divId+' table tr');
	if( arr.length > 0){
		$('#'+divId+' table tr').eq(0).attr('costmatepriceid', (mateId+'_head'));
		$('#'+divId+' table tr').eq(0).attr('costmateid', mateId);
		$('#'+divId+' table tr').eq(0).find('input[fname=costMatePriceId]').val(mateId+'_head');
		$('#'+divId+' table tr').eq(0).find('input[fname=costMateId]').val(mateId);
	}
}

//设置数据行属性
function setPriceDataRow(divId,mateId,priceId){
	var size = $('#'+divId+' table tr').length;
	var t = 0;
	$('#'+divId+' table tr').each(function(i,n){
		++t;
		if(t == size){
			$(this).attr('costmateid', mateId);
			$(this).attr('costmatepriceid', priceId);
			$(this).find('input[fname=costMateId]').val(mateId);
			$(this).find('input[fname=costMatePriceId]').val(priceId);
		}
	});
}
//(战略内)创建模块
function createPriceRowIn(mateId, priceId){

	//$('#strategyInDiv').mask('正在添加...');
	var url =_ctx + '/cost/cost-mate-price!resInCreate.action';
	$.post(url, {resCostMatePriceId: priceId}, function(result){
		$('#strategyInDiv').append(result);
		//设置head
		setPriceHeadRow('strategyInDiv',mateId);
		setPriceDataRow('strategyInDiv',mateId,priceId);
		//绑定事件
		bindRowInputAutoCalc(mateId+'_head', true);
		bindRowInputAutoCalc(priceId, true);

		refreshDecoration();

//		$('#strategyInDiv').unmask();
	});
}
//(战略内)追加行
function appendPriceRowIn(mateId,priceId){
//	$('#strategyInDiv').mask('正在添加...');
	var url =_ctx + '/cost/cost-mate-price!resInAppend.action';
	$.post(url, {resCostMatePriceId: priceId}, function(result){
		$('table[costmateid='+mateId+']').append(result);
		setPriceDataRow('strategyInDiv',mateId,priceId);
		//绑定事件
		bindRowInputAutoCalc(priceId, true);
		
		refreshDecoration();
//		$('#strategyInDiv').unmask();
	});
}

var STRAGE_OUT = 'strageout';
//(战略外)
function insertRowOut(){
	if(isMateExists(STRAGE_OUT)){
		priceRowAppendOut(STRAGE_OUT+(++itemRowCount));
	}else{
		priceRowCreateOut(STRAGE_OUT+(++itemRowCount));
	}
}


//(战略外)创建
function priceRowCreateOut(priceId){
//	$('#strategyOutDiv').mask('正在添加...');
	var url =_ctx + '/cost/cost-mate-price!resOutCreate.action';
	$.post(url, {moduleId: getMateModuleId()}, function(result){
		$('#strategyOutDiv').append(result);
		//设置head
		setPriceHeadRow('strategyOutDiv',STRAGE_OUT);
		setPriceDataRow('strategyOutDiv',STRAGE_OUT,priceId);
		//绑定事件
		bindRowInputAutoCalc(STRAGE_OUT+'_head', false);
		bindRowInputAutoCalc(priceId, false);

		refreshDecoration();
//		$('#strategyOutDiv').unmask();
	});
}

//(战略外)追加数据
function priceRowAppendOut(priceId){
//	$('#strategyOutDiv').mask('正在添加...');
	$('#btnExtAppend').hide();
	var url =_ctx + '/cost/cost-mate-price!resOutAppend.action';
	$.post(url, {resCostMatePriceId: priceId,moduleId: getMateModuleId()}, function(result){

		var mateId = STRAGE_OUT;
		if( $('table[costmateid='+mateId+'] tbody').length == 1){
			$('table[costmateid='+mateId+'] tbody').append(result);
		}else{
			$('table[costmateid='+mateId+']').append(result);
		}
		setPriceDataRow('strategyOutDiv',mateId,priceId);
		//绑定事件
		bindRowInputAutoCalc(priceId, false);

		refreshDecoration();
//		$('#strategyOutDiv').unmask();
		$('#btnExtAppend').show();
	});
} 

function initBindAll(){
	$('table[costmateid] tr').each(function(){
		var priceId = $(this).attr('costMatePriceId');
		var mateId = $(this).attr('costmateid');
		if( mateId.indexOf('strageout') == -1){
			bindRowInputAutoCalc(priceId, true);
		}else{
			bindRowInputAutoCalc(priceId, false);
		}
	});
	
	refreshDecoration();
}

function refreshTargetPrice(jdom,inFlg){
/* 
	//若是电缆,计算“最终单价"和"总价"
	alert(isDianLanFlg());
	if(isDianLanFlg()){
		var price = jdom.parent().parent().find('input[fname=price]');
		alert(price.html());
		alert(price.val());
		var priceTarget = jdom.parent().parent().find('input[fname=f02]');
		priceTarget.val(price.val());
	} */
	
	//请求后台 ,获取当前记录的总价 
	//input {span}> td > tr
	var domTr = null;
	if( jdom.parent().hasClass('mainTr')){
		domTr = jdom.parent();
	}
	else if( jdom.parent().parent().hasClass('mainTr')){
		domTr = jdom.parent().parent();
	}
	else if( jdom.parent().parent().parent().hasClass('mainTr')){
		domTr = jdom.parent().parent().parent();
	}
	else{
		return;
	}
	
	var priceId = domTr.attr('costmatepriceid');
	var buyNum = getColumnVal(domTr, 'buyNum');
	var currentPrices = getColumnVal(domTr, 'currentPrices');
	
	//若战略内，查询后台，获得最终计算结果(实际价格，总价）。
	if(inFlg){
		var data = {buyNum: buyNum, costMatePriceId: priceId, currentPrice: currentPrices};
		if(buyNum == ''||typeof(buyNum) == undefined){
			setColumnVal(domTr, 'cgPrice', '0');
			setColumnVal(domTr, 'totalPrice', '0');
			return;
		}
		
		var url  = _ctx + '/cost/cost-mate-price!calcRowData.action';
		$('#failureTip').html('');
		$.post(url, data, function(result){
			
			if('price_range' == result){
				$('#failureTip').html('铜价 不在区间内，请确认!').show();
				setColumnVal(domTr, 'cgPrice','');
				setColumnVal(domTr, 'totalPrice', '');
			}
			else if('failure' == result){
				alert('未找到基础数据，请联系管理员，谢谢!');
			}else{
				var arr = result.split(',');
				var ttprice = arr[1];
				setColumnVal(domTr, 'cgPrice', ttprice);
				setColumnVal(domTr, 'totalPrice', arr[2]);
				
				//currentPrices
				if(isDianLanFlg()){
					var domInput = domTr.find('input[fname=currentPrices]');
					var domTd = domInput.parent().prev();
					domTd.find('div').html(ttprice);
					domTd.find('div').next().val(ttprice);
				}
			}
			
			strateInOutAmt();
		});
	}
	//战略外
	else{
		
		//若是电缆，将单价自己填
		/*
		if(isDianLanFlg()){
			 domTr.find('input[fname=currentPrices]').each(function(){
				 $(this).val(domTr.find('input[fname=currentPrices]').val());
			 });
		}
		*/
		
		var cgprice = getFieldVal(priceId, 'cgPrice');
		var buyNum = getFieldVal(priceId, 'buyNum');
		var normalAmt = formatNumberic(cgprice) * formatNumberic(buyNum);
		setColumnVal(domTr, 'totalPrice', normalAmt);
	
		strateInOutAmt();
	}
	
}
/**
* 绑定输入框事件
* @param costMatPriceId
* @param inFlg 是否战略内 true-是 false-否
*/
function bindRowInputAutoCalc(costMatPriceId, inFlg){
	

	//是否可编辑: true-是 false-否
	var canEditFlg= $('#canEditFlg').val();
	$(".strageTables tr[costmatepriceid=" + costMatPriceId +"]").each(function(){

		//重新计算
		++itemRowCount;
		
		var tmpFName = 'default';
		
		//为了有序保存,设置属性name,不分战略内外
		$(this).find('input').each(function(){
			tmpFName = $(this).attr('fname');
			$(this).attr('name',('templateBean.strategyMatePrices['+itemRowCount+'].'+tmpFName));

			if(!$(this).hasClass('inputBorder')){
				$(this).addClass('inputBorder');
			}
		});
		

		//电缆：铜价
		$(this).find('input[fname=currentPrices]').each(function(){
			$(this).addClass('required');
			$(this).attr("validate",'required');
			$(this).css({'width':'100%'});
			$(this).bind('blur',function(){
				clearNoNum_1(this);
				refreshTargetPrice($(this),inFlg);
			});
		});
		
		
		//(采购的数量)触发自动计算
		$(this).find('input[fname=buyNum]').each(function(){
			$(this).addClass('required');
			$(this).attr("validate",'required');
			$(this).css({'width':'100%'});
			$(this).bind('keyup',function(){
				clearNoNum_1(this);
				refreshTargetPrice($(this),inFlg);
			});
		});
		//(扩展单价列的数量)触发自动计算
		$(this).find('input[extprice=1]').each(function(){
			$(this).css({'width':'100%'});
			$(this).bind('keyup',function(){
				clearNoNum_1(this);
				refreshTargetPrice($(this),inFlg);
			});
		});

		//基础单价
		$(this).find('input[fname=price]').each(function(){
			$(this).attr("validate",'required');
			$(this).css({'width':'100%'});
			$(this).bind('keyup',function(){
				clearNoNum_1(this);
				refreshTargetPrice($(this),inFlg);
			});
		});
		
		//采购单价
		$(this).find('input[fname=cgPrice]').each(function(){
			$(this).attr("validate",'required');
			$(this).css({'width':'100%'});
			$(this).bind('keyup',function(){
				clearNoNum_1(this);
				refreshTargetPrice($(this),inFlg);
			});
		});
		
		if(!inFlg){
			//若是头，跳过;否则加样式;
			if(costMatPriceId.indexOf('_head') == -1){
				$(this).find('input[type=text]').each(function(){

					//$(this).css({'height':'99%'});
					
					if(!$(this).hasClass('inputBorder')){
						//$(this).addClass('inputBorder');
						if(canEditFlg == 'false'){
							$(this).addClass('inputBorder_readOnly');
						}
					};
				});
			}
		}
	});
}
/**
* 计算行金额
* @param priceId 型号ID
* @param inFlg 是否战略内 true-是 false-否
*/
function calculateRowAmt(priceId, inFlg){ 
	
	var normalAmt = getFieldVal(priceId, 'totalPrice');
	
	//查找扩展单价1 * 数量1 =扩额1
	//查找扩展单价2 * 数量2 =扩额2
	//var extAmts = getExtAmts(priceId);
	
	//行累加值
	var totalRowAmt = formatNumberic(normalAmt);
	return totalRowAmt;
}
//数字型
function formatNumberic(num){
	if(num == ''|| typeof(num) ==undefined){
		return 0;
	}else{
		return Number($.trim(num));
	}
}

//统计战略内外价格
function strateInOutAmt(){
	
	//战略内
	var inAmt = 0;
	//战略外
	var outAmt = 0;
	
	$('table[stragetable=cost_mate_table] tr').each(function(){
		var strageTypeCd = $(this).find('input[fname=strageTypeCd]').val();
		var headFlg = $(this).find('input[fname=headFlg]').val();
		var priceId = $(this).find('input[fname=costMatePriceId]').val();
		
		//0-数据行
		if( '1' != headFlg){
			//战略内
			if('1' == strageTypeCd){
				inAmt  = inAmt  + calculateRowAmt(priceId, true);
			}
			//战略外
			else{
				outAmt = outAmt + calculateRowAmt(priceId, false);
			}
		}
	});
	
	//若存在，则自动计算.
	var hasMateFlg = $('#hasMateFlg').val();
	
	$('#strageInAmt').html(formatMoney(inAmt));
	$('#strageOutAmt').html(formatMoney(outAmt));
	
	if( hasMateFlg == '1'){
		//采购总价
		$('#purchaseTotalAmt').val(formatMoney(inAmt + outAmt));
		//含战略内总价(元)
		$('#purchaseStrategyTotalAmt').val(formatMoney(inAmt));
		//含战略外总价(元)
		$('#strategyOutTotalAmt').val(formatMoney(outAmt));
	}
}

function setColumnVal(jdom,fname,val){
	jdom.find('input[fname='+fname+']').each(function(){
		$(this).val(val);
		
		//若是电缆,价格写到"订购单价"列，用于显示
		
		
		if(fname == 'totalPrice'){
			if($(this).next() == null){
				$(this).parent().next().html(formatMoney(val));//div 财务格式 :###,###,###.00
			}else{
				$(this).next().html(formatMoney(val));//div 财务格式 :###,###,###.00
			}
		}
	});
}
function getColumnVal(jdom,fname){
	return jdom.find('input[fname='+fname+']').val();
}
//获取属性字段的值
function getFieldVal(priceId, fname){
	var jDomTr = $('tr[costmatepriceid='+priceId+']');
	var val = 0;
	jDomTr.find('input[fname='+ fname +']').each(function(){
		val = $(this).val();
		if(val == '' || typeof(val) == undefined){
			val = 0;
		}
		if(fname == 'totalPrice'){
			if($(this).next() == null){
				$(this).parent().next().html(formatMoney(val));//div 财务格式 :###,###,###.00
			}else{
				$(this).next().html(formatMoney(val));//div 财务格式 :###,###,###.00
			}
		}
	});
	return val;
}
//指行的扩展累加值
function getExtAmts(priceId){
	var extAmts = 0;
	var jDomTr = $('tr[costmatepriceid='+priceId+']');
	jDomTr.find('input[extprice=1]').each(function(){
		//input -> td -> preTd -> input(first)
		var extNum = $(this).val();
		var extVal = $(this).parent().prev().find('input').eq(0).val();//第一个
		extAmts = extAmts + formatNumberic(extVal) * formatNumberic(extNum);
	});
	return extAmts;
}


//刷新序号,删除表格,电缆显示,自动计算
function refreshDecoration(){

	$('#strategy_in_span').hide();
	$('#strategy_out_span').hide();

	$('table[stragetable=cost_mate_table]').each(function(){
		//当前table
		var vFlg = false;
		var len = $(this).find('tr').length;
		//若无数据，则删除抬头
		if( len == 1){
			//删除表格
			$(this).remove();
			vFlg = false;
		}else{
			vFlg = true;
			//设置序号
			$(this).find('tr').each(function(i,n){
				if(i == 0){
					$(this).find('td').eq(0).html('序号');
				}else{
					$(this).find('td').eq(0).html(i);
				}
			});
		}
		
		if(vFlg){
			$(this).parent().prev().show();
		}else{
			$(this).parent().prev().hide();
		}
	});

	//电缆显示
	var dianlan = isDianLanFlg();
	$('table[stragetable=cost_mate_table] td[dianlan=1]').each(function(){
		if(dianlan){
			$(this).show();
			$(this).find('input').attr("validate","required");
			
			//若是战略外
			if('2' == $(this).parent().attr('inoutflg')){
				//$(this).next().hide();
			}
		}else{
			$(this).hide();
			$(this).find('input').removeAttr("validate");

			//若是战略外
			if('2' == $(this).parent().attr('inoutflg')){
				$(this).next().show();
			}
		}
	});

	//若战略内无，不显示;
	//若战略外无，不显示;
	
	
	//重新计算价格
	strateInOutAmt();
	
	//重新设置排除ID
	initExcludeIds();
	
	var moduleId = getMateModuleId();
	if( moduleId == '' || typeof(moduleId) == undefined){
		//$('#appendFiles').show();
		$('#btnExtAppend').hide();
	}else{
		//$('#appendFiles').hide();
		$('#btnExtAppend').show();
	}
	
	//自适应宽度
	$('.cost_mate_table').css({'table-layout':''});
}

function initExcludeIds(){
	var arr = new Array();

	$('#strategyInDiv  table[stragetable=cost_mate_table] tr[headFlg=0]').each(function(){
		arr.push($(this).attr('costmatepriceid'));
		arr.push(',');
	});
	
	$('#excludePriceIds').val(arr.join(''));
}
//设置弹出,若已弹出过，则不需要加载；否则，加载一次。
function setCostMatePopFlgOpen(){
	$('#moduleType_Div').attr('openFlg','1');
}
//是否曾经弹出
function isCostMatePoped(){
	var flg = $('#moduleType_Div').attr('openFlg');
	return ('1' == flg);
}

//get/set方法
function getMateModuleId(){
	return $('#mateModuleId').val();
}
function setMateModuleId(id){
	$('#mateModuleId').attr('newval',id);
}
function getMateModuleName(){
	return $('#mateModuleName').val();
}
function setMateModuleName(name){
	$('#mateModuleName').attr('newval',name);
}

function setMateModule(id,name){
	setMateModuleId(id);
	setMateModuleName(name);
}
//确认替换
function confirmMateModule(){
	var newidval = $('#mateModuleId').attr('newval');
	var newnameval = $('#mateModuleName').attr('newval');
	var oldidval = $('#mateModuleId').val();
	
	if(oldidval != newidval){
		var tableNum = $('#strategyInDiv table tr[headFlg=0]').length;
		if(tableNum > 0){
			if(window.confirm('若重选，将清除已选材料设备,确认操作?')){
				$('#mateModuleId').val(newidval);
				$('#mateModuleName').val(newnameval);
				
				$('#strategyInDiv table').each(function(){
					$(this).remove();
				});
				$('#strategyOutDiv table').each(function(){
					$(this).remove();
				});
				
				$('#purchaseTotalAmt').val('');
				$('#purchaseStrategyTotalAmt').val('');
				$('#strageInAmt').html('0');
				$('#strageOutAmt').html('0');
			}
		}else{
			$('#mateModuleId').val(newidval);
			$('#mateModuleName').val(newnameval);
		}
		
		if(newidval && newidval != ''){
			var url = _ctx + '/cost/cost-mate!validateHasMate.action';
			var data = {moduleId: newidval};
			$.post(url, data, function(result){
				//若无，则不显示搜索栏
				if('0' == result){
					//$('#appendFiles').show();
					$('#btnExtAppend').hide();
					$('#hasMateFlg').val("0");
					$('#tr_purc').attr('style','display:none;');
					$('#inputTip').html('');
					
					//可编辑
					$("#purchaseTotalAmt").removeAttr('readonly');
					$("#strategyOutTotalAmt").removeAttr('readonly');
				}
				//若有数据,则显示
				else{
					//$('#appendFiles').hide();
					$('#btnExtAppend').show();
					$('#hasMateFlg').val("1");
					$('#tr_purc').removeAttr('style');
					$('#inputTip').html(result.split(',')[1]);
					
					//不可编辑
					$("#purchaseTotalAmt").attr('readonly','readonly');
					$("#strategyOutTotalAmt").attr('readonly','readonly');
				}
			});
		}else{
			//$('#appendFiles').show();
		}
	}
	
	if(getMateModuleName() == ''){
		$('#btnExtAppend').hide();
	}else{
		$('#btnExtAppend').show();
	}
}
//选择材料备类型
var _modulePopTree; 
function selectCostMateModule(){
	if(isCostMatePoped()){
		$('#moduleType_Div').show();
	}else{
		ymPrompt.confirmInfo({
			icoCls:"",
			title:"请选择类型",
			message:"<div id='moduleType_Div'></div>",
			useSlide:true,
			winPos:"c",
			width:300,
			height:400,
			allowRightMenu:true,
			afterShow:function(){
				$.post(_ctx+"/cost/cost-mate-module!getCostMateModuleTreeNoCheck.action", function(result){
					_modulePopTree = new TreePanel({
						renderTo:"moduleType_Div",
						'root' : eval('('+result+')'),
						'ctx':_ctx
					});
					_modulePopTree.on(function(node){
						var nodeType = node.attributes.nodeType;
						//非根节点
						if(nodeType != '0'){
							if(node.isExpand){
								node.collapse();
							}else{
								node.expand();
							}
						} 
						if(node.isLeaf()){
						    setMateModule(node.attributes.id, node.attributes.text);
						}
					});
					_modulePopTree.render();
					//默认展开一级
					//_modulePopTree.getRootNode().expandDeep(0);
				});
			},
			handler:function(e){
				if("ok" == e){
				 	confirmMateModule();
				}
			}
		});
	}
}
//是否为空
function isBlank(val){
	return ($.trim(val) == '') || ($.trim(val) == null);
}
//设置提示
function setTipHtml(id, content){
	$('#'+id).html(content).show().fadeOut(3000);
}
//删除行 a->td ->tr
function removeCurrentRow(dom){ 
	$(dom).parent().parent().remove(); 
	
	refreshDecoration();
}

/**
* 电缆，获取时价
* @param currentPrice 时价
* @param price 单价
*/

function getCurrentPrice(currentPrice, price, costMateId){
	var costMateId = $("#materialId").val();
	var data = {currentPrice:currentPrice, price: priceValue, costMateId: costMateId};
	var url= _ctx + "/cost/cost-mate!getCurrPrice.action";
	$.post(url,data,function(result) {
		if(result=="error"){
			//alert("您输入的铜价不在最低价与最高价之间!");
			return false;
		}
		else if(result.split(',').length == 2 && result.split(',')[0] == 'error'){

			return false;
		}
		else{
			$(trApprover_new).find("#price_in").each(function(i){
				$(this).val(result);
				//采购单价
				$(trApprover_new).find("#cgSinglePriceSpan").each(function(i){
					$(this).html(result);
				});
				$(trApprover_new).find("#cgPrice").each(function(i){
					$(this).val(result);
				});
				
				$(trApprover_new).find("#numbers_in").each(function(i){
					getTotalAmtSingle(this,trApprover_new,'in');//计算单个型号的总价
				});
				$(trApprover_new).find("#numbers_in").each(function(i){
					getTotalAmtSingle(this,trApprover_new,'in');//计算单个型号的总价
				});
			});
		}
	});
}

function getDealTotalAmt(){
	var url= _ctx + "/cost/cost-mate-price!totalAmt.action";
	var data={prices:prices_in,numbers:numbers_in,totalFloatRate:totalFloatRate};
	$.post(url,data,function(result) {
		if(result){
			$("#purchaseStrategyTotalAmt").val(result);
			$("#inCount").html(result);
		}else{
			$("#purchaseStrategyTotalAmt").val(0);
			$("#inCount").html('0');
		}
	}); 
}  
//得到材料设备对应的一些属性信息
function getMateCol(){
	if($("#lowPrice").val()!='' && $("#highPrice").val() !=''){
		$("#tipsPrice").html("(最低价："+$("#lowPrice").val()+",最高价："+$("#highPrice").val()+")");
	}else{
		$("#tipsPrice").html("");
	}  
}
//合同编号唯一性校验
function contNoValidate(dom){
	if($(dom).val()){
		$.post( _ctx + "/cont/cont-ledger!contNoValidate.action?contNo=" + $(dom).val(),
			function(result){
				//合同新增，不是0条记录
				if(result!="0"){
					if (!$("#contractNo").attr("readonly")) {
						alert("该编号已存在，请重新输入");
						$(dom).val("");
					}
				}
	     });
	}
} 

function getCont(id){
	var url= _ctx + "/cont/cont-ledger!input.action?id="+id;
	showPageLink(url,"cont-ledger-input","合同台账查看");
}

//解决弹出窗口的链接问题,改造 parent.showAll()
function showPageLink(url, type, name){
	if(parent && parent.TabUtils && parent.TabUtils.newTab){
	  parent.TabUtils.newTab(type, name,url,true);
	}else{
		window.open(url);
	}
}