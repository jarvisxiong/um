﻿//成本工作管理模块脚本
//@author shixy 2011-03-22

function initProjectSelect(dom){
	if( $(dom).attr('initSelectFlg') != '1'){
		$(dom).attr('initSelectFlg','1'); 
		$(dom).orgSelect({
			showProjectOrg:true
		});
		$(dom).trigger('click');
	}
}
function updateByDate(){
	$('#main_search_form').attr("action",_ctx+"/plan/cost-ctrl-bid-purc!listByUpdate.action");
	$("#updateFlg").val("1");
	jumpPage(1);
}

/**
 * 控制搜索区域和新增按钮是否显示
 * @param id
 */
function showOrHideSearch(id,targetPageType){
	if(id=='cost_task'||id=='cost_plan'||id=='cost_stat'){
		$('#main_search_div').hide();
	}else{
		if(""==targetPageType){
			$('#main_search_div').show();
		}
		if(id=='cost_sup_li'){
			$('#bid_pur_search').hide();
			$('#sup_search').show();
		}else{
			$('#bid_pur_search').show();
			$('#sup_search').hide();
		}
	}
	if(id!='n_li' && id!='s_li' && id!='sh_li'){
		$('#btn_add_cost').hide();
	}
}

function costTaskClick(d){
	loadPage("","");
	
//	$.get(_ctx+"/plan/plan-work2!costCtrl.action?if_in_weight=0&centerCd=17",function(r){
//		$('#cost-container').html(r);
//		autoHeight();
//		$.get( _ctx+"/plan/exec-plan!getCostCtrl.action",function(result) {
//				$("#cost-plan-exec").html(result);
//				autoHeight();
//				TB_removeMaskLayer();
//			}
//		);
//	});
}
// 排序
function clickSortCost(attr) {
//	var orderBy = "";
//	var order = "asc";
//	var tds = $(".mainTable >thead >tr >td");
//	for(var i=0; i<tds.size(); i++) {
//		if(tds.eq(i).attr("sortCol") != "" && tds.eq(i).attr("sortCol") == attr) { // 排序的列
//			orderBy = ORDER_BY[attr];
//			if(tds.eq(i).children().attr("class") == "asc") {
//				tds.eq(i).children().attr("class", "desc");
//				order = "desc";
//			} else {
//				tds.eq(i).children().attr("class", "asc");
//				order = "asc";
//			}
//			$("#orderColMark").val(attr);
//			$("#orderMark").val(order);
//		}
//	}
//	$("#orderBy").val(orderBy);
//	$("#order").val(order);
//
//	loadPage();
}
function loadPage(month,dom) {
    if(""!=month){
    	var planSelect=$("#planSelect").val();
    	var param={currentCenterCd:"17",currentPlanMonth:month,currentPlanYear:planSelect};
    		$(".month_big").attr("class","month_small");
    		$(dom).attr("class","month_big color_red");
    }else{
    	var param={currentCenterCd:"17"};
    }
	$.post(_ctx+"/plan/plan-target!costYearPlan.action",param,function(r){
		$('#cost-container').html(r);

		initMonthList();
		
		var subPlanNumber = $(".mainTable >tbody >tr td span[class=subPlanNumber]");
		for(var i=0; i<subPlanNumber.size(); i++) {
			var sub = subPlanNumber.eq(i).html();
			subPlanNumber.eq(i).html(sub.split("-")[sub.split("-").length - 1]);
		}
		autoHeight();
		$("#planMonth").show();
		TB_removeMaskLayer();
	});

}

var batchData = "{'data':[";
function doTransmitCostCtrl(id,type,content,costName,projectCd){
	if(id!=null){
		if(confirm("确定要推送该条记录吗？")){
			var url =_ctx+"/plan/cost-ctrl-bid-purc!yearPlanSave.action";
			if(costName=="招标部"){
				pageType="bid";
			}else{
				pageType="pur";
			}
			batchData = "{'data':[";
			batchData += "{'detailId':'" + id + "','planType':'" + type + "','content':'" + content+"','projectCd':'" + projectCd+"','pageType':'"+pageType+"'}]}";
			var param = {transmitJson : batchData};
			$.post(url,param,
					function(result) {
						if (result == "ok") {
							alert("已送出推送的文件");
							var href=_ctx+"/plan/cost-exec-plan!getCostCtrlPlanRel.action";
							$("#cost-plan-exec").html(result);
							batchData = "{'data':[";
						}
					});
		}
	}
};
function doTranByCostCtrl(execDetailId){
	if(null!=execDetailId){
		var url =_ctx+"/plan/cost-ctrl-bid-purc!yearPlanSave.action?execDetailId="+execDetailId;
		$.post(url,null,
				function(result) {
					if (result == "ok") {
						alert("已送出推送的文件");
						//var href=_ctx+"/plan/exec-plan!getCostCtrl.action";
						//costPlanClick(href);
						$("#tuisong_"+execDetailId).hide();
					}
				});
		/*
		ymPrompt.win({
			icoCls:"",
			title:"请选择推送部门",
			message:"<div id='itemDiv'><input type='radio' name='selectType' value='bid' />招标<input type='radio' name='selectType' value='pur' />采购</div>",
			useSlide:true,
			winPos:"c",
			width:400,
			height:130,
			maxBtn: false,
			allowRightMenu:true,
			afterShow:function(){},
			handler:function(e){
				if("ok"==e){
					var selectType =$("input[name='selectType']:checked").val();
					if(typeof selectType=="undefined"){
						alert("请选择部门类型");
					}else{
						var content ="【"+projectName+"】"+executionPlanName+nodeName;
						batchData = "{'data':[";//3为半年计划
						batchData += "{'detailId':'" + id + "','planType':'3','content':'" + content+"','projectCd':'" + projectCd+"','pageType':'"+selectType+"'}]}";
						var param = {transmitJson : batchData};
						var url =_ctx+"/plan/cost-ctrl-bid-purc!yearPlanSave.action";
						$.post(url,param,
								function(result) {
									if (result == "ok") {
										alert("已送出推送的文件");
										var href=_ctx+"/plan/exec-plan!getCostCtrl.action";
										costPlanClick(href);
										batchData = "{'data':[";
									}
								});
					}
				}
			}
		});*/
	}
}
function transmitJsonData(id,type,content,costName,projectCd){
	if(costName=="招标部"){
		pageType="bid";
	}else{
		pageType="pur";
	}
	var replaceStr = "{'detailId':'" + id + "','planType':'" + type + "','content':'" + content+"','projectCd':'" + projectCd+"','pageType':'"+pageType+"'},";
	//如果包含该ID了，则表示要去掉勾选
	if(batchData.indexOf("'detailId':'" + id + "',")>0){
		batchData =batchData.replace(replaceStr, "");
	}else{
		batchData+=replaceStr;
	}
}
function costPlanClick(href){
	$.get(href,function(r){
		$("#cost-container").html(r);
	});
}
function batchTransmit(){
	if(batchData.length>9){
		if(confirm("确定要批量推送吗？")){
			batchData =batchData.substring(0, batchData.length-1);
			batchData+="]}";
			var param = {transmitJson : batchData};
			var url =_ctx+"/plan/cost-ctrl-bid-purc!yearPlanSave.action";
			$.post(url,param,
					function(result) {
						if (result == "ok") {
							alert("已送出推送的文件");
							var href=_ctx+"/plan/cost-exec-plan!getCostCtrlPlanRel.action";
							costPlanClick(href);
							batchData = "{'data':[";
						}
			});
		}
	}
}
/**
 * 保存新增的招标、采购
 */
function saveNewCost(){
	if($.trim($('#projectCdId').val())==''){
		alert('请选择项目！');
		return;
	}
	if($.trim($('#new_planContentDesc').val())==''){
		alert('请填写事项！');
		return;
	}
	
	TB_showMaskLayer("正在保存...");
	$('#new_templet_form').ajaxSubmit(function(r){
		$('#cost-container').html(r);
		bindTblEv();
		$('#bid_pur_search a,#sup_search a').css('color','#0167a2');
		resetSearch();
		TB_removeMaskLayer();
	});
}

/**
 *  显示新增招标、采购输入框
 */
function addCost(){
	$('#new_templet').show();
}
function cancelAdd(){
	$('#new_templet').hide();
}
/**
 * 绑定表格事件
 */
function bindTblEv(){
	$('#result_table tbody tr td[click2expand=true]').click(
		function(){
			var $parent = $(this).parent();
			var $p_next = $parent.next();
			var $read = $($parent).find('.cost-read');
			var $edit = $($parent).find('.cost-edit');
			var $dataEdit = $parent.attr('dataEdit');
			var $permEdit = $parent.attr('permEdit');
			var $scheCd = $parent.attr('scheCd');
			var $permConfirm = $parent.attr('permConfirm');
			if($p_next.css('display') == 'none'){
				$p_next.show();
				$parent.addClass('click');
				if($permEdit == 'true'){
					$read.hide();
					$edit.show();
					if($dataEdit == 'false'){
						$read.filter('[dataEdit=false]').show();
						$edit.filter('[dataEdit=false]').hide();
					}
				}else if($permConfirm == 'true'){
					$read.filter('[confirmEdit=true]').hide();
					$edit.filter('[confirmEdit=true]').show();
				}
				$parent.find('img:eq(0)').attr('src',_ctx+'/resources/images/common/down_grey.gif');
				//如果数据状态为已完成，则所有控件不能进行编辑
				if($scheCd == 2){
					$(':text,:checkbox',$p_next).attr('disabled',true);
				}
				
				//将其他显示的detail tr隐藏
				$parent.siblings('.click').find('[click2expand=true]:eq(1)').trigger('click');
			}else{
				$p_next.hide();
				$parent.removeClass('click');
				$read.show();
				$edit.hide();
				$parent.find('img:eq(0)').attr('src',_ctx+'/resources/images/common/right_grey.gif');
			}
	});
	
	$('#result_table tbody tr.group').toggle(function(){
		//先将已经展开的详细信息收起来
		$('#result_table .click').find('[click2expand=true]:eq(1)').trigger('click');
		
		var id = $(this).attr('id');
		$('.mainTr[group="'+id+'"]').hide();
		$('.detailTr[group="'+id+'"]').hide();
	},function(){
		var id = $(this).attr('id');
		$('.mainTr[group="'+id+'"]').show();
		$('.detailTr[group="'+id+'"]').hide();
	});
	
	$('#result_table tbody tr td div.cost-edit :input').click(function(e){
		//阻止事件向上冒泡
		e.stopPropagation(); 
	});
	$('.shortcutsArea').dblclick(function(){
		$(this).closest('tr.detailTr').prev().find('[click2expand=true]:eq(1)').trigger('click');
	});
}

/**
 * 保存留言
 * @param id 数据主键id
 */
function saveMsg(id){
	var msg = $('#message_'+id).val();
	if($.trim(msg) == ''){
		alert('留言不能为空!');
		$('#message_'+id).focus();
		return;
	}
	$.post(_ctx+'/plan/cost-ctrl-bid-purc!saveMsg.action',{id:id,msg:msg},function(r){
		$('#msg_container_'+id).prepend('<div class="detail_message_div">'+r+'</div>');
		$('#message_'+id).val('');
	});
}

/**
 * 保存招标、采购 的等待、进度信息
 * @param id 数据主键id
 */
function saveCost(id){
	var isReturn=false;
	$("#sche_"+id+" tr").each(function(){
		var _this =$(this);
		if(_this.hasClass("mustTrColor")){
			var obj =_this.children("td:eq(2)");
			if($(obj).find(":text").val()==""){
				alert("请确认黄色区域目标时间是否填写");
				isReturn=true;
				return false;
			}
		}
	});
	if(isReturn){
		return false;
	}
	TB_showMaskLayer("正在保存...");
	setCurSche(id);
	$('#form_'+id).ajaxSubmit(function(r){
		jumpPage($('#pageNo').val());
	});
}

function confirmCost(id){
	if($('#'+id+'UserCds').val()==''){
		alert('负责人不能为空,请选择负责人！');
		return;
	}
	if($('#bid_date_'+id).val()==''){
	alert('定标目标时间不能为空！');
		return;
	}
	/*var isReturn=false;
	$("#sche_"+id+" tr").each(function(){
		var _this =$(this);
		if(_this.hasClass("mustTrColor")){
			var obj =_this.children("td:eq(2)");
			if($(obj).find(":text").val()==""){
				alert("请确认黄色区域目标时间是否填写");
				isReturn=true;
				return false;
			}
		}
	});
	if(isReturn){
		return false;
	}*/
	TB_showMaskLayer("正在保存...");
	setCurSche(id);
	$('#confirmFlg_'+id).val('1');
	$('#form_'+id).ajaxSubmit(function(r){
		resetSearch();
		$('#cost-container').html(r);
		bindTblEv();
		$('#bid_pur_search a,#sup_search a').css('color','#0167a2');
		TB_removeMaskLayer();
	});
}
/**
 * 设置数据当前的最新完成进度、以及进度完成事件
 * @param id
 */
function setCurSche(id){
	var $curSche = $('#bidStatusCd_'+id);
	var dataTypeCd=$('#dataTypeCd_'+id).val();
	$('#bidDate_'+id).val($('#bid_date_'+id).val());
	var d = $('input:checked:last','#sche_'+id);
	if(d.length>0){
		if("1"==dataTypeCd&&"7"==d.val()){
			$curSche.val("5");
		}else{
			$curSche.val(d.val());
		}
		var id = $(d).attr('id').split('_')[0];
	}else{
		$curSche.val('');
	}
}
/**
 * 等待
 * 复选框点击后改变隐藏域的值，方便后台取值
 * @param d checkbox 的 dom
 * @param clearEId 取消选择时需要清除值的dom id
 */
function changeWaitValue(d,clearEId){
	var clearEIdArr = clearEId.split('&');
	if($(d).attr('checked')){
		$(d).next().val('1');
		$.each(clearEIdArr,function(i,n){
			$('#'+n).attr('readOnly',false);
		});
	}else{
		$(d).next().val('0');
		$.each(clearEIdArr,function(i,n){
			$('#'+n).val('');
			$('#'+n).attr('readOnly',true);
		});
	}
}

/**
 * 进度复选框勾选
 * @param d
 * @param clearEId
 * @param scheType 进度cd  
 * @param dataType 0代表招标 1代表采购
 * @param costCtrlId 主数据Id
 */
function changeScheValue(d,clearEId,scheType,dataType,costCtrlId){
	if($(d).attr('checked')){
		$(d).next().val('1');
		var now = new Date();
		var date = now.getFullYear()+'-'+(now.getMonth()+1)+'-'+now.getDate();
		$('#'+clearEId).val(date);
		$('#'+clearEId).prev().text((now.getMonth()+1)+'-'+now.getDate());
		if((dataType==0&& scheType==7)||(dataType==1&& (scheType==5||scheType==7))||(dataType==2 && scheType==2)){
			//保存后进入合同管理和招采计划
			//$('#conStatusChangeFlg_'+costCtrlId).val('1');
			//定标日期
			$('#bidDate_'+costCtrlId).val(date);
			var srcDomId = $(d).attr('id');// onchange="showDivHtml(\''+srcDomId+'\',\''+costCtrlId+'\');"
			var tmpDivHtml='<div id="temSearDiv" style="padding-left:50px;">定标审批查询号：<input type="text" id="tmpSearch" onkeyup="queryAppAuth(this);"/>'
				+'<input type="hidden" id="tmpSearchId"/></div>'+
				'<div style="width:70%;padding-left:145px;"><div id="searchDiv" style="display:none;padding:3px 5px;position: absolute;z-index:9999999;border: 1px solid gray;background-color: #e8e8e8"></div></div>'+
				'<div id="zbSearDiv" style="display:none;padding-top:20px;padding-left:100px;">'+
				'<input id="importSrcDomId"  type="hidden" value="'+ srcDomId +'"/>'+
				 '<input id="importCtrlId"    type="hidden" value="'+ costCtrlId +'"/>'+
				 '是否进入标后核对:<br><input style="margin:3px 5px 0 5px;" id="importChiefChk"  type="checkbox" checked="checked" onclick="changeChiefChk(this)"/>是' +
				 '<span  style="margin:3px 5px 0 5px;" id="importChiefDateSpan">审核时间:<input style="text-align:left;width:100px;" id="importChiefDate" type="text" onfocus=WdatePicker({dateFmt:"yyyy-MM-dd"}) class="Wdate"/></span><br/>'+
				 '<input style="margin:0 5px 0 5px;" id="importChiefChk2" type="checkbox" onclick="changeChiefChk(this)" style="margin:0 5px;"/>否' +
				 '</div>';
			/*var tmpDivHtml = '<div id="importChiefDateDiv" style="padding:10px;">'+
							 '<input id="importSrcDomId"  type="hidden" value="'+ srcDomId +'"/>'+
							 '<input id="importCtrlId"    type="hidden" value="'+ costCtrlId +'"/>'+
			 				 '<input style="margin:3px 5px 0 5px;" id="importChiefChk"  type="checkbox" checked="checked" onclick="changeChiefChk(this)"/>是' +
							 '<span  style="margin:3px 5px 0 5px;" id="importChiefDateSpan">审核时间:<input style="text-align:left;width:100px;" id="importChiefDate" type="text" onfocus=WdatePicker({dateFmt:"yyyy-MM-dd"}) class="Wdate"/></span><br/>'+
							 '<input style="margin:0 5px 0 5px;" id="importChiefChk2" type="checkbox" onclick="changeChiefChk(this)" style="margin:0 5px;"/>否' +
							 '</div>';*/
			ymPrompt.confirmInfo({
				icoCls:"",
				title: '<span style="color:red;">保存后会进入合同管理！请选择定标审批编号！</span>',
				message:tmpDivHtml,
				winPos:"c",
				useSlide:true,
				width:400,
				height:250,
				//maxBtn: false,
				allowRightMenu:true,
				showMask:false,
				handler:function(tp){//confirmSchePop,
					//标后核对的审核时间
					var tDate = $('#importChiefDate').val();
					var tSpan = '';
					//定标审批表编号
					var tmpSearch =$("#tmpSearch").val();
					if (tp == "ok") {
						if(""==tmpSearch){
							alert("请输入网批编号");
							cancelBidChecked(d,clearEId,dataType,scheType);
							return false;
						}else{
							var tmpId=$("#tmpSearchId").val();
							$('#resNo_' + $('#importCtrlId').val()).val(tmpSearch);
							$('#resId_' + $('#importCtrlId').val()).val(tmpId);
							$("#spanShowRes_"+ $('#importCtrlId').val()).text("选中网批查询号："+tmpSearch);
							//有网批进入合同管理
							$("#conStatusChangeFlg_"+ $('#importCtrlId').val()).val("true");
						}
						tDate = $('#importChiefDate').val();
						if('' == tDate){
							if(true == $('#importChiefChk').attr("checked")){
								alert('请填写审核日期!');
								$('#'+$('#importSrcDomId').val()).attr('checked','');
								$('#'+$('#importSrcDomId').val()).trigger('click');
							}
							return;
						}else{
							tSpan = '(标核)';
						}
					}else{
						$('#importChiefDate').val('');
						//作取消的动作
						/*$(d).attr("checked",false);
						$(d).next().val('0');
						$('#'+clearEId).val('');
						$('#'+clearEId).prev().text('');*/
						cancelBidChecked(d,clearEId,dataType,scheType,costCtrlId);
					}
					$('#chiefDate_' + $('#importCtrlId').val()).val(tDate);
					$('#span_chiefDate_' + $('#importCtrlId').val()).html(tSpan);
				},
				afterShow:function(){
					//$("#tmpSearch").quickSearch( _ctx+"/res/res-approve-info!queryAppAuth.action",['resNo'],{resNo:'tmpSearch',resId:'tmpSearchId'},{},showTemSearch);
                     
					/*$.post("${ctx}/cost/cost-ctrl-bid-purc!openAppAuthority.action", function(result){
						
					});*/
					/*
					 * 不默认日期
					var myDate = new Date();
					var year = myDate.getFullYear();   //获取完整的年份(4位,1970-????)
					var month = ((myDate.getMonth() < 10) ? "0" : "") + myDate.getMonth()+"";
					var day = ((myDate.getDay() < 10) ? "0" : "") + myDate.getDay()+"";
					$('#importChiefDate').val(year + '-' + month + '-' + day);
					*/
				}
			});
		}
	}else{
		cancelBidChecked(d,clearEId,dataType,scheType,costCtrlId);
	}
}
function cancelBidChecked(d,clearEId,dataType,scheType,costCtrlId){
	$(d).attr("checked",false);
	$(d).next().val('0');
	$('#'+clearEId).val('');
	$('#'+clearEId).prev().text('');
	if((dataType==0&& scheType==7)||(dataType==1 && scheType==5)||(dataType==2 && scheType==2)){
		$('#conStatusChangeFlg_'+costCtrlId).val('');
		$('#bidDate_'+costCtrlId).val('');
		
		$('#importChiefDate').val('');
		$('#chiefDate_' + $('#importCtrlId').val()).val('');
		$('#span_chiefDate_' + $('#importCtrlId').val()).html('');
	}
}
function changeChiefChk(dom){
	if(true == $(dom).attr("checked")){
		$(dom).siblings().attr("checked","");
	}else{
		$(dom).siblings().attr("checked","checked");
	}
	
	//是否显示日期
	if(true == $('#importChiefChk').attr("checked")){
		$('#importChiefDateSpan').show();
	}else{
		$('#importChiefDate').val('');
		$('#importChiefDateSpan').hide();
	}
}

function confirmSchePop(tp){
	var tDate = $('#importChiefDate').val();
	var tSpan = '';
	var tmpSearch =$("#tmpSearch").val();
	if (tp == "ok") {
		if(""==tmpSearch){
			alert("请输入网批编号");
			return false;
		}else{
			var tmpId=$("#tmpSearchId").val();
			$('#resNo_' + $('#importCtrlId').val()).val(tmpSearch);
			$('#resId_' + $('#importCtrlId').val()).val(tmpId);
			$("#spanShowRes_"+ $('#importCtrlId').val()).text("选中网批编号："+tmpSearch);
			//有网批进入合同管理
			$("#conStatusChangeFlg_"+ $('#importCtrlId').val()).val("true");
		}
		tDate = $('#importChiefDate').val();
		if('' == tDate){
			if(true == $('#importChiefChk').attr("checked")){
				alert('请填写审核日期!');
				$('#'+$('#importSrcDomId').val()).attr('checked','');
				$('#'+$('#importSrcDomId').val()).trigger('click');
			}
			return;
		}else{
			tSpan = '(标核)';
		}
	}else{
		$('#importChiefDate').val('');
	}
	$('#chiefDate_' + $('#importCtrlId').val()).val(tDate);
	$('#span_chiefDate_' + $('#importCtrlId').val()).html(tSpan);
}

/**
 * 更改计划类型直接异步保存
 * @param d
 * @param id
 */
function changePlanType(d,id){
	var v = $(d).val();
	var t = $(d).find('option:selected').text();
	$.post(_ctx+'/plan/cost-ctrl-bid-purc!save.action',{id:id,planTypeCd:v},function(){
		$(d).parent().prev().text(t);
		showSuccessInfo('计划类型保存成功！');
	});
}
/**
 * 更改重要性直接异步保存
 * @param d
 * @param id
 */
function changeImporType(d,id){
	var v = $(d).val();
	$.post(_ctx+'/plan/cost-ctrl-bid-purc!save.action',{id:id,importantTypeCd:v},function(){
		if(v == '2'){
			$(d).parent().prev().find('div').show();
		}else{
			$(d).parent().prev().find('div').hide();
		}
		showSuccessInfo('重要性保存成功！');
	});
}
/**
 * 更改事项直接异步保存
 * @param d
 * @param id
 */
function changeDesc(d,id){
	var v = $(d).val();
	$.post(_ctx+'/plan/cost-ctrl-bid-purc!save.action',{id:id,planContentDesc:v},function(){
		$(d).parent().prev().text(v);
		showSuccessInfo('事项保存成功！');
	});
}

/**
 * 合同完成
 * @param id
 */
function conComplete(id){
	if(!window.confirm('确认将该数据标记为已完成吗？'))return;
	TB_showMaskLayer("正在保存...");
	$.post(_ctx+'/plan/cost-ctrl-bid-purc!save.action',{id:id,conCompleteFlg:'1'},function(){
		$('#main_'+id+',#detail_'+id).remove();
		TB_removeMaskLayer();
	});
}
function conReturn(id){
	TB_showMaskLayer("正在退回...");
	$.post(_ctx+'/plan/cost-ctrl-bid-purc!save.action',{id:id,conReturnFlg:'1'},function(){
		$('#main_'+id+',#detail_'+id).remove();
		TB_removeMaskLayer();
	});
}

function changeOwner(d,id){
	if( $('#'+id+"UserNames").attr('initSelectFlg') != '1'){
		$('#'+id+"UserNames").attr('initSelectFlg','1');
		$('#'+id+"UserNames").userSelect({
			type: 'user',
			callback: function(map){
				var userNames = $('#'+id+'UserNames').val();
				var userCds = $('#'+id+'UserCds').val();
				$.post(_ctx+'/plan/cost-ctrl-bid-purc!save.action',{id:id,ownerCds:userCds,ownerNames:userNames},function(){
					$(d).parent().prev().text($('#'+id+'UserNames').val());
					showSuccessInfo('负责人保存成功！');
				});
			}
		});
		$('#'+id+"UserNames").trigger('click');
	}
	/*
	getMember(id,'','0','','','','',function(){
		var userNames = $('#'+id+'UserNames').val();
		var userCds = $('#'+id+'UserCds').val();
		$.post(_ctx+'/plan/cost-ctrl-bid-purc!save.action',{id:id,ownerCds:userCds,ownerNames:userNames},function(){
			$(d).parent().prev().text($('#'+id+'UserNames').val());
			showSuccessInfo('负责人保存成功！');
		});
		
	});
	*/
}
function showWaitDept(dom, id){
	if( $('#waitDeptName_'+id).attr('initSelectFlg') != '1'){
		$('#waitDeptName_'+id).attr('initSelectFlg','1');
		$('#waitDeptName_'+id).userSelect();
		$('#waitDeptName_'+id).trigger('click');
	}
}
function showSuccessInfo(text){
	$('#succInfoId').text(text).show();
	setTimeout(function(){
		$('#succInfoId').text('').hide();
	}, 5000);
} 
function jumpPage(pageNo) {
	 
	//add by huangbijin 2011-08-08
	if('block' == $('#btn_add_check').css("display")){
		searchCostCheckPage(pageNo);//cost-check.js
		return;
	}
	
	submitSearch(pageNo);
}
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
function trOnclickByProj(projectCd){
	if(projectCd){
		if($(".tr_"+projectCd).css("display") == "none"){
		    $(".tr_"+projectCd).show();
		    $("#detail_tr_"+projectCd).removeClass("proj_drop_up").addClass("proj_drop_down");
		    $("#detail_tr_"+projectCd).removeClass("proj_up_item").addClass("proj_down_item");
		}else{
			$(".tr_"+projectCd).hide();
			$("#detail_tr_"+projectCd).removeClass("proj_drop_down").addClass("proj_drop_up");
			 $("#detail_tr_"+projectCd).removeClass("proj_down_item").addClass("proj_up_item");
		}	
	}
}
function trOnclick(projectCd){
	if(projectCd){
		if($(".tr_"+projectCd).css("display") == "none"){
		    $(".tr_"+projectCd).show();
		    $("#detail_tr_"+projectCd).removeClass("big_drop_up").addClass("big_drop_down");
		}else{
			$(".tr_"+projectCd).hide();
			$("#detail_tr_"+projectCd).removeClass("big_drop_down").addClass("big_drop_up");
		}	
	}
}
function selectSuppliers(id,costType){
	if(id){
		var selectId ="";
		var selectName ="";
		var finType ="";
		ymPrompt.confirmInfo({
			icoCls:"",
			title:"供应商需求标准",
			message:"<div id='suppliersDiv'></div>",
			useSlide:true,
			winPos:"c",
			width:450,
			height:400,
			showMask : false,
			allowRightMenu:true,
			afterShow:function(){
				$.post(_ctx + "/plan/cost-ctrl-bid-purc!supply.action?id="+id, function(result){
					$("#suppliersDiv").html(result);
					if(costType=='con'){
						$('input').attr("readonly","readonly");
					}
				});
		},
		handler:function(e){
			if("ok"==e){
				if(costType!='con'){
					if($("#suppliersType").val()!=""){
						if($("#suppliersId").val()==""){
							//新增
							$("#suppliers_comFlg").val("1");
						}
						$("#suppliersForm").ajaxSubmit();
					}
					$("#sup_sel_"+id).hide();
					$("#sup_have_"+id).show();
				}
			}
		}
		});
	}
}
function supplyDetail(id){
	var _this = $('#main_'+id);
	_this.siblings('.click').trigger('click');
	if(_this.next().css('display') == 'none'){
		_this.find('img:eq(0)').attr('src',_ctx+'/resources/images/common/down_grey.gif');
		_this.addClass('click').next().show();
	}else{
		_this.find('img:eq(0)').attr('src',_ctx+'/resources/images/common/right_grey.gif');
		_this.removeClass('click').next().hide();
	}
	var type=$('#supType_'+id).val();
	if(type==1){
		$('.sup_material').hide();
		$('.sup_project').show();
	}else if(type==2){
		$('.sup_project').hide();
		$('.sup_material').show();
	}
	$('input').attr("readonly","readonly");
}
/**
 * 打开成本工作月计划的附件
 * @param title
 * @param entityId
 */
function openAttachmentPlanWork2(title,entityId){
	 ymPrompt.win({
		message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=planWork2&filterType=image|office&bizEntityName=PlanWork2&onlyShow=true",
			width:500,
			height:300,
			title:'附件查看',
			iframe:true,
			handler : function(){}
	 });
} 
function attachRefresh(){
	if(null!=cur_entityId){
		refreshMain(cur_entityId);
	}else{
		$("#new_img_atta").attr("src",_ctx+"/resources/images/common/atta_y.gif");
	}
}
//点击全选按钮
function checkedAll(flag){
  $("input[name='allFilesCheckBox']").attr("checked",flag);
  var checked = $(':input[name="allFilesCheckBox"]:checked');
  if(checked.length==0){
	  batchData = "{'data':[";
  }else{
		checked.each(function(){
			var obj = {};
			//obj.detailId = this.attr('detailId'); 
			var id =$(this).attr('detailId');
			var type=$(this).attr('planType');
			var content =$(this).attr('content');
			var costName=$(this).attr('costName');
			var projectCd =$(this).attr('projectCd');
			transmitJsonData(id,type,content,costName,projectCd);
		});
  }
} 

function submitSearch(pageNo){
	if("1"==$("#updateFlg").val()){
		$('#main_search_form').attr("action",_ctx+"/plan/cost-ctrl-bid-purc!listByUpdate.action");
	}
	if(pageNo == 0){
		$('#filter_scheduleStatusCd,#filter_delayFlg,#filter_waitFlg').val('');
		$('#bid_pur_search a,#sup_search a').css('color','#0167a2');
	}
	TB_showMaskLayer("正在搜索...");
	$('#search_pageNo').val(pageNo);
	if(""!=$("#search_pageType").val()){
		$('#main_search_form').ajaxSubmit(function(r){
			$('#cost-container').html(r);
			autoHeight();
			bindTblEv();
			TB_removeMaskLayer();
			
			$('#result_table .mainTr[target="true"]').find('[click2expand=true]:eq(1)').trigger('click');
		});
	}
	$('#main_search_form').attr("action",_ctx+"/plan/cost-ctrl-bid-purc!list.action");
}
function resetSearch(){
	$(':input[name!="pageType"]','#main_search_form').val('');
}
function quickSearch(d,id,value){
	$(d).css('color','red').siblings('a').css('color','#0167A2');
	//为什么要重置? contentd by huangbijin 2011/07/29
	//resetSearch();
	if(value){
		$('#'+id).val(value);
	}else{
		$('#'+id).val('1');
	}
	submitSearch(1);
}
function quickSearchSup(d,value){
	$(d).css('color','red').siblings('a').css('color','#0167A2');
	//为什么要重置? contentd by huangbijin 2011/07/29
	//resetSearch();
	$('#filter_isSuppCompleteFlag').val(value);
	submitSearch(1);
}
function deleteData(id){
	if(!confirm("确定要删除该条记录吗？"))return;
	TB_showMaskLayer("正在删除...");
	$.post(_ctx+'/plan/cost-ctrl-bid-purc!delete.action',{id:id},function(){
		$('#main_'+id+',#detail_'+id).remove();
		TB_removeMaskLayer();
	});
}
function updateSupStatus(id){
	if(id){
		//完成
		if(confirm("确定要完成吗？")){
			$.post(_ctx+'/plan/cost-ctrl-bid-purc!saveFlg.action',{id:id,supComFlag:'2'},function(r){
				$("#supSpan_"+id).text("完成");
			});
		}
	}
}
function changeStatDate(dp){
	changeMonth(dp.cal.getDateStr()+'-01');
}
function changeMonth(month){
	TB_showMaskLayer("正在加载...");
	var url = _ctx+'/plan/cost-ctrl-bid-purc!statZc.action';
	$.post(url,{currMonth:month},function(r){
		$('#cost-container').html(r);
		TB_removeMaskLayer();
	});
}
function showTemSearch(){
	if(""!=$("#tmpSearch").val())
	   $("#zbSearDiv").show();
	else
		 $("#zbSearDiv").hide();
}
/**
 * 快速搜索网批标号
 */
function queryAppAuth(dom){
	if(""!=$(dom).val()){
		$.post( _ctx+"/res/res-approve-info!queryAppAuth.action",{value:$(dom).val()},function(result){
			result = eval(result);
			var htmlL = '';
			$.each(result,function(i,n){
				var html = "<div id='"+n['resId']+"' style='padding-left:5px;font-size:12px;border: 1px solid #fff;cursor: pointer;' onclick='selectedResNo(this);'>";
				htmlL +=html+n['resNo']+"|"+n['titleName']+"</div>";
			});
			//$('#tmpSearch').append('');
			$("#searchDiv").show().html(htmlL);
		});
	}
	//$(dom).quickSearch( _ctx+"/res/res-approve-info!queryAppAuth.action",['resNo'],{resNo:'tmpSearch',resId:'tmpSearchId'},{},showTemSearch);
}
function selectedResNo(dom){
	var value=$(dom).text();
	if(""!=value){
		$("#tmpSearch").val(value);
		$("#tmpSearchId").val($(dom).attr("id"));
		$("#searchDiv").hide();
		$("#zbSearDiv").show();
	}
}
//除‘定标’几点外其他节点的目标时间只有‘负责人’才能进行编辑修改
function processFocusBidDate(dom, curUserUiid, costId, dataTypeCd, scheduleTypeCd, scheduleTypeName){
	var jRr = $(dom).parent().parent();
	//if((dataTypeCd == '0' && scheduleTypeCd=='7')||(dataTypeCd == '1' && scheduleTypeCd=='7')||(dataTypeCd == '2' && scheduleTypeCd=='7')){
	if(jRr.hasClass('mustTrBid')){
		if(scheduleTypeName == '定标'){
			WdatePicker();
		}
		else{
			var userCds = $('#'+ costId + 'UserCds').val();
			if((userCds.indexOf(curUserUiid+';') == 0) || (userCds.indexOf(';'+curUserUiid+';') > 0)){
				WdatePicker();
			}else{
				//$(this).attr('readonly','readonly');
			}
		}
	}
	else if(jRr.hasClass('mustTrColor')){
		WdatePicker();
	}
	else{
		WdatePicker();
	}
}
function planMonthClick(month){
	loadPage(month);
}