
function searchPanel(dom) {
	var relType = $(dom).attr('reltype');
	switch(relType){
		case '1':
			disableApplyRow();
			disableChiefRow();
			disableChiefButtons();
//			quickSearchClose();
//			$('#btnMoreCond').hide();
			$('#search_panel_sub').show();
			$('#search_panel_sub li[reltype=1]').addClass('search_panel_sub_click').show();
			$('#search_panel_sub li[reltype=2]').addClass('search_panel_sub_click').show();
			$('#search_panel_sub li[reltype=3]').removeClass('search_panel_sub_click').show();
			$('#search_panel_sub li[reltype=4]').addClass('search_panel_sub_click').show();
			$('#search_panel_sub li[reltype=9]').removeClass('search_panel_sub_click').show();
			$('#condApproveStatusCd').val('1,2,4');//默认:未提交、审批中、驳回
			break;
		case '2':
			enableApplyRow();
			disableChiefRow();
			enableChiefButtons();
//			quickSearchClose();
//			$('#btnMoreCond').hide();
			$('#search_panel_sub').hide();
			$('#search_panel_sub li[reltype=1]').removeClass('search_panel_sub_click').show();
			$('#search_panel_sub li[reltype=2]').addClass('search_panel_sub_click').show();
			$('#search_panel_sub li[reltype=3]').removeClass('search_panel_sub_click').show();
			$('#search_panel_sub li[reltype=4]').removeClass('search_panel_sub_click').show();
			$('#search_panel_sub li[reltype=9]').addClass('search_panel_sub_click').show();
			$('#condApproveStatusCd').val('2');//默认:审批中
			break;
		case '3':
			enableApplyRow();
			enableChiefRow();
			disableChiefButtons();
			$('#btnMoreCond').show();
			$('#search_panel_sub').show();
			$('#search_panel_sub li[reltype=1]').removeClass('search_panel_sub_click').show();
			$('#search_panel_sub li[reltype=2]').removeClass('search_panel_sub_click').show();
			$('#search_panel_sub li[reltype=3]').addClass('search_panel_sub_click').show();
			$('#search_panel_sub li[reltype=4]').removeClass('search_panel_sub_click').show();
			$('#search_panel_sub li[reltype=9]').removeClass('search_panel_sub_click').show();
			$('#condApproveStatusCd').val('3');//默认:完成
			break;
		default:
			return;
	}
	
	$(dom).addClass('search_panel_click').siblings().removeClass('search_panel_click');
	$('#searchTypeCd').val($(dom).attr('reltype'));
	submitSearchForm();
}

function enableApplyRow(){
	$('#searchSeniorPanel .applyRow').show();
}
function disableApplyRow(){
	$('#searchSeniorPanel .applyRow').hide();
	$('#applyName').val('');
	$('#applyUiid').val('');
}
function enableChiefRow(){
	$('#searchSeniorPanel .chiefRow').show();
}
function disableChiefRow(){
	$('#searchSeniorPanel .chiefRow').hide();
	$('#chiefName').val('');
	$('#chiefUiid').val('');
}
function disableChiefButtons(){
	$('#btnBatchOperate').hide();
	$('#batchOperatePanel').hide();
}
function enableChiefButtons(){
	$('#btnBatchOperate').show();
}

function searchPanelSub(dom) {
	$(dom).addClass('search_panel_sub_click').siblings().removeClass('search_panel_sub_click');
	$('#condApproveStatusCd').val($(dom).attr('reltype'));
	submitSearchForm();
}
function quickSearchFormClean(){
	$('#searchSeniorPanel select').each(function(){
		$(this).val('');
	});
	$('#searchSeniorPanel input').each(function(){
		var type = $(this).attr('type');
		switch(type){
			case 'checkbox':
				$(this).attr('checked','');
				break;
			case 'text':
			case 'hidden'://同上
				$(this).val('');
				break;
			default:;
		}
	});
}
function quickSearchClose(){
	if($('#btnMoreCond').attr('open') == '1'){
		$('#btnMoreCond').click();
	}
}
function submitSearchForm() {
	$('#tableDiv').show().mask('查找中...');
	$('#mainSearchForm').ajaxSubmit(function(result) {
		$('#tableDiv').unmask();
		//quickSearchClose();
		$('#tableDiv').html(result).show();
	});
}
function openDetail(id, applyTypeCd, approveStatusCd, creator, currentUiid) {
	
	if ((approveStatusCd == '1' || approveStatusCd == '4') && (creator == currentUiid)) {// 未提交，跳转至新增修改页面 plas-approve.js
		refreshApproveArea(id);
		return;
	}
	var url = _ctx + '/plas/plas-approve!view.action';
	var param ={id:id};
	var title = '';
	//1-新增 2-调动  3-冻结  4-解冻 5-注销 6-启用
	switch(applyTypeCd){
		case '1': 	title = '人员新增'; break;
		case '2': 	title = '人员调动'; url = _ctx + '/plas/approve!loadApproveDetail.action';break;
		case '3': 	title = '人员冻结'; break;
		case '4': 	title = '人员解冻'; break;
		case '5': 	title = '人员离职'; break;
		case '6': 	title = '人员启用'; break;
		default:
			return;
	}
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='selectTypeDiv' ><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
		width : 520,
		height : 450,
		title : "详细信息-" + title,
		closeBtn:true,
		allowSelect: true, //是否允许选择消息框内容
		allowRightMenu: true,//允许在消息框使用右键
		afterShow : function() {
			$.post(url, param, function(result) {
				$("#selectTypeDiv").html(result);
				
				//点击遮罩,自动关闭弹出框窗口
				$('#maskLevel').bind('click',function(){
					$('#ym-window .ym-btn input').each(function(){
						$(this).trigger('click');
					});
				});
				
				$.post(_ctx+"/plas/approve!loadApproves.action",param,function(data) {
					$('#approve_his').html(data);
				});
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				setFlatNo(getFlatId(),getFlatNo());
			}else{
				ymPrompt.close();
			}
		},
		btn:[["退出",'cancel']]
	});
}
//重新提交：驳回之后
function reApprove(id, dom) {
	var val = prompt("请填写发起事由", "");
	if (val) { // 如果返回的有内容
		doApprove(id, val);
		$(dom).css('background-color', '#ccc');
	} else {
		return;
	}
}
function doApprove(id, optionDesc){
	
	$('#operaTip').hide();
	var url = _ctx + '/plas/approve!approve.action';
	$.post(url, {id : id, optionDesc: optionDesc}, function(result) {
		if (result == 'success') {
			$('#operaTip').html('操作成功!').show().fadeOut(3000,function(){	submitSearchForm();	});
		}else {
			$.messager.alert('提示', '操作不成功!' + result);
			//$('#operaTip').html('操作不成功!'+result).show();//.fadeOut(2000,function(){	submitSearchForm();	});
		}
	});
}
//审批通过
function pass(approveId,dom) {

	
	$('#operaTip').hide();
	
	
	if(!window.confirm('确认操作?')){
		return;
	}
	
	var jDomTr = $(dom).parent().parent().parent();
	var jChk = jDomTr.find('input:first');
	
	var marked = jChk.attr('marked');
	if(marked == 'true'){
		jDomTr.remove();
		return ;
	}
	
	$('#rightPanel').mask('正在处理...');
	var url = _ctx + '/plas/approve!pass.action';
	$.post(url, {id : approveId}, function(result) {
		$('#rightPanel').unmask();
		if (result == 'success') {
			jChk.attr('marked','true');
			$('#operaTip').html('操作成功!').show().fadeOut(2000,function(){	submitSearchForm();	});
		}else {
			if(result.indexOf('已有人处理!') > -1){
				$('#operaTip').html(result).show().fadeOut(3000,function(){	submitSearchForm();	});
			}else{
				$.messager.alert('提示', '操作不成功!' + result);
				//$('#operaTip').html('操作不成功!' + result).show();
			}
		}
	});
}
//驳回
function reject(plasApproveInfoId,dom) {

	if(!window.confirm('确认操作?')){
		return;
	}
	
	$('#operaTip').hide();
	var jDomTr = $(dom).parent().parent().parent();
	var jChk = jDomTr.find('input:first');
	
	var marked = jChk.attr('marked');
	if(marked == 'true'){
		jDomTr.remove();
		return ;
	}
	
	var optionDesc = prompt("请输入驳回原因","");
	if( optionDesc){//如果返回的有内容 
		$('#rightPanel').mask('正在处理...');
		var url = _ctx + '/plas/approve!reject.action';
		var data = {id : plasApproveInfoId,optionDesc: optionDesc};
		$.post(url, data, function(result) {
			$('#rightPanel').unmask();
			if (result == 'success') {
				jChk.attr('marked','true');
				$('#operaTip').html('操作成功!').show().fadeOut(2000,function(){	submitSearchForm();	});
			}else {
				if(result.indexOf('已有人处理!') > -1){
					$('#operaTip').html(result).show().fadeOut(3000,function(){	submitSearchForm();	});
				}else{
					$('#operaTip').html('操作不成功!' + result).show();
				}
			}
		});
	} 
}
function loadApprove(id){
	if (plasApproveInfoId!=''){
		var url =_ctx+"/plas/approve!loadApproves.action";
		$.post(url,{id: id}, function(result) {
			return result;
		});
	}
}
function goRefreshApproveArea(id){
	ymPrompt.close();
	refreshApproveArea(id);
}

function checkAll(){
	$('#tableDiv input[type=checkbox]').attr('checked','true');
}
function unCheckAll(){
	$('#tableDiv input[type=checkbox]').attr('checked','');
}
function passAll(){

	var arr = new Array();
	var count = 0;
	$('#tableDiv :input[type=checkbox]').each(function(){
		var check = $(this).attr("checked");
		var id = $(this).attr('relId');
		var statusCd = $(this).attr('relStatusCd');
		var marked = $(this).attr('marked');
		if(check && statusCd == '2' && (marked !='true')){
			arr.push(id);			
			arr.push(',');
			count ++;
		}
	});

	if(count == 0){
		$('#operaTip').html('您未选择申请记录!').show().fadeOut(3000);
		return;
	}
	
	
	var ids = arr.join('');
	if (confirm("您要驳回" + count  + "条申请, 确认操作吗?")){
		var url = _ctx+"/plas/approve!checkAll.action";
		$('#approvePanel').mask('正在执行批量驳回,请稍后...');
		$.post(url,{ids: ids},function(result){
			markCheckBox();
			$('#approvePanel').unmask();
			$('#approvePanel').html(result).show();
			$('#operaTip').html('操作成功.').show().fadeOut(2000,function(){	
				submitSearchForm();	
			});
		});
	}
}
function markCheckBox(){
	$('#tableDiv :input[type=checkbox]').each(function(){
		var check = $(this).attr("checked");
		var statusCd = $(this).attr('relStatusCd');
		//追加记号
		var marked = $(this).attr('marked');
		if(check && statusCd == '2' && (marked !='true')){
			$(this).attr('marked','true');
		}
	});
}
function rejectAll(){

	var arr = new Array();
	var count = 0;
	$('#tableDiv :input[type=checkbox]').each(function(){
		var check = $(this).attr("checked");
		var id = $(this).attr('relId');
		var statusCd = $(this).attr('relStatusCd');
		
		if(check && statusCd == '2'){
			arr.push(id);			
			arr.push(',');
			count ++;
		}
	});

	if(count == 0){
		$('#operaTip').html('您未选择申请记录!').show().fadeOut(3000);
		return;
	}
	
	
	var ids = arr.join('');
	if (confirm("您要驳回" + count  + "条申请, 确认操作吗?")){
		var url = _ctx+"/plas/approve!rejectAll.action";
		$('#approvePanel').mask('正在执行批量驳回,请稍后...');
		$.post(url,{ids: ids},function(result){
			markCheckBox();
			$('#approvePanel').unmask();
			$('#approvePanel').html(result).show();
			$('#operaTip').html('操作成功.').show().fadeOut(2000,function(){	
				submitSearchForm();	
			});
		});
	}
}
function turn2MyReco(){
	var url = _ctx+'/plas/approve!panel.action';
	$.post(url,{searchTypeCd:'1'},function(result){
		$('#approvePanel').html(result).show();
	});
}

//翻页查询
function jumpPage(pageNo) {
	
	if(typeof(pageNo) == 'undefined' || (pageNo == '')){
		$("#pageNo").val(1);
	}else{
		$("#pageNo").val(pageNo);
	}
	submitSearchForm();
}
//跳转至给定的页面,配合前台的分页查询
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}

//300毫秒触发一次查询
var quickSearchMgr;
function changeQuickSearch(){
	
	/*
	var t = $('#searchTypeCd').val();
	if($.trim(t) == '2'){
		return;
	}
	*/
	
	if(quickSearchMgr){
		clearTimeout(quickSearchMgr);
	}
		
	quickSearchMgr = setTimeout(
	function() {
		$('#pageNo').val('1');
		submitSearchForm();
	}, 300);
}


function operateChiefTr(dom){
	var val = $(dom).attr("checked");
	if(val){
		$(dom).parent().siblings().find('input[type=checkbox]').attr('checked','');
		$(dom).parent().parent().parent().parent().next().show();
	}else{
		$(dom).parent().parent().parent().parent().next().hide();
		$('#chiefName').val('');
		$('#chiefName').next().val('');
	}
}