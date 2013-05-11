//[成本控制计划]标后核对模块

function bindTblEvCheck(){
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
function showSuccessInfoCheck(text){
	$('#succInfoId').text(text).show();
	$('#succInfoId').fadeOut(5000);
} 

//显示 标后核对标签页 
function showCostCheckList(){
	$('#btn_add_cost').hide();
	$('#main_search_div').hide();
	$('#btn_add_check').show();
	$('#check_search_div').show();
	searchCostCheckPage();
}

//显示新增明细
function showCostCheckPanel(){
	$('#new_templet_check').show();
}

function initProjectSelectCheck(dom){
	if( $(dom).attr('initSelectFlg') != '1'){
		$(dom).attr('initSelectFlg','1'); 
		$(dom).orgSelect({
			showProjectOrg:true
		});
		$(dom).trigger('click');
	}
}
//保存新增标后核对
function saveNewCostCheck(){
	if($.trim($('#projectCdId').val())==''){
		alert('请选择项目！');
		return;
	}
	if($.trim($('#new_planContentDesc').val())==''){
		alert('请填写事项！');
		return;
	}
	
	TB_showMaskLayer("正在保存...");
	$('#new_templet_form_check').ajaxSubmit(function(r){
		$('#cost-container').html(r);
		bindTblEvCheck();
		TB_removeMaskLayer();
	});
}

//取消新增标后核对
function cancelNewcostCheck(){
	$('#new_templet_check').hide();
}

//更新标后核对
function saveCostCheck(id){
	TB_showMaskLayer("正在保存...");
	updateMainSche(id);
	$('#form_'+id).ajaxSubmit(function(r){
		TB_removeMaskLayer();
		showSuccessInfoCheck('保存成功！');
		searchCostCheck();
	});
}
function updateMainSche(id){
	var tmp = $('#bidStatusCd_'+id);
	$('#bidDate_'+id).val($('#bid_date_'+id).val());
	var d = $('input:checked:last','#sche_'+id);
	if(d.length>0){
		tmp.val(d.val());
	}else{
		tmp.val('');
	}
}
//确认标后核对
function confirmCostCheck(id){

	if($('#'+id+'UserCds').val()==''){
		alert('负责人不能为空,请选择负责人！');
		return;
	}
	if($('#bid_date_'+id).val()==''){
	alert('审批目标时间不能为空！');
		return;
	}
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
	updateMainSche(id);
	$('#confirmFlg_'+id).val('1');
	$('#form_'+id).ajaxSubmit(function(r){
		resetSearchCheck();
		$('#cost-container').html(r);
		bindTblEvCheck();
		$('#bid_pur_search a,#sup_search a').css('color','#0167a2');
		TB_removeMaskLayer();
	});
}

//日期降序
function quickSearchByUpdate(){
	$('#search_isUpdateFlg').val('1');
	searchCostCheckPage(1);
}
//快速搜索
function quickSearchCheck(dom, id, value){
	if(value){
		$('#'+id).val(value);
	}else{
		$('#'+id).val('1');
	}
	
	$(dom).siblings().css('color','#0167a2');
	$(dom).css('color','red');
	
	searchCostCheckPage(1);
}
//搜索列表
function searchCostCheck(){
	searchCostCheckPage(1);
}
//分页搜索标后核对列表
function searchCostCheckPage(pageNo){
	
	if(pageNo){
		$('#search_pageNo_check').val(pageNo);
	}
	
	TB_showMaskLayer("正在搜索...");
	$('#main_search_form_check').ajaxSubmit(function(r){
		TB_removeMaskLayer();
		$('#cost-container').html(r);
		bindTblEvCheck();
		
		//$('#result_table .mainTr[target="true"]').find('[click2expand=true]:eq(1)').trigger('click');
	});
}
//删除记录
function deleteDataCheck(id){
	if(!confirm("确定要删除该条记录吗？"))
		return;
	
	TB_showMaskLayer("正在删除...");
	$.post(_ctx+'/plan/cost-check!delete.action',{id:id},function(result){
		TB_removeMaskLayer();
		if('success' == result){
			$('#main_'+id+',#detail_'+id).remove();
		}else{
			alert("删除失败!");
		}
	});
}

//保存留言
function saveMsgCheck(id){
	var msg = $('#message_'+id).val();
	if($.trim(msg) == ''){
		alert('留言不能为空!');
		$('#message_'+id).focus();
		return;
	}
	$.post(_ctx+'/plan/cost-check!saveMsg.action',{id:id,msg:msg},function(result){
		$('#msg_container_'+id).prepend('<div class="detail_message_div">'+result+'</div>');
		$('#message_'+id).val('');
	});
}
//更新属性字段值
//事项
function changeDescCheck(dom, id){
	var v = $(dom).val();
	$.post(_ctx+'/plan/cost-check!save.action',{id:id,planContentDesc:v},function(){
		showSuccessInfoCheck('事项保存成功！');
		$(dom).parent().prev().text(v);
	});
}
function changeOwnerCheck(dom, id){
	if( $('#'+id+"UserNames").attr('initSelectFlg') != '1'){
		$('#'+id+"UserNames").attr('initSelectFlg','1');
		$('#'+id+"UserNames").userSelect({
			type: 'user',
			callback: function(map){
				var userNames = $('#'+id+'UserNames').val();
				var userCds = $('#'+id+'UserCds').val();
				$.post(_ctx+'/plan/cost-check!save.action',{id:id,ownerCds:userCds,ownerNames:userNames},function(){
					showSuccessInfoCheck('负责人保存成功！');
					$(dom).parent().prev().text($('#'+id+'UserNames').val());
				});
			}
		});
		$('#'+id+"UserNames").trigger('click');
	}
	/*
	getMember(id,'','0','','','','',function(){
		var userNames = $('#'+id+'UserNames').val();
		var userCds = $('#'+id+'UserCds').val();
		$.post(_ctx+'/plan/cost-check!save.action',{id:id,ownerCds:userCds,ownerNames:userNames},function(){
			showSuccessInfoCheck('负责人保存成功！');
			$(dom).parent().prev().text($('#'+id+'UserNames').val());
		});
	});
	*/
}
function showWaitDeptCheck(dom, id){
	if( $('#waitDeptName_'+id).attr('initSelectFlg') != '1'){
		$('#waitDeptName_'+id).attr('initSelectFlg','1');
		$('#waitDeptName_'+id).userSelect();
		$('#waitDeptName_'+id).trigger('click');
	}
}
//复选框,若不选,则只读
function changeWaitValueCheck(dom,clearEId){
	var clearEIdArr = clearEId.split('&');
	if($(dom).attr('checked')){
		$(dom).next().val('1');
		$.each(clearEIdArr,function(i,n){
			$('#'+n).attr('readOnly',false);
		});
	}else{
		$(dom).next().val('0');
		$.each(clearEIdArr,function(i,n){
			$('#'+n).val('');
			$('#'+n).attr('readOnly',true);
		});
	}
}
//节点完成情况
function changeScheValueCheck(dom,clearEId,idxScheType,dataType,costCtrlId){
	if($(dom).attr('checked')){
		$(dom).next().val('1');
		var now = new Date();
		var date = now.getFullYear()+'-'+(now.getMonth()+1)+'-'+now.getDate();
		$('#'+clearEId).val(date);
		$('#'+clearEId).prev().text((now.getMonth()+1)+'-'+now.getDate());
		if(idxScheType==4){
			$('#conStatusChangeFlg_'+costCtrlId).val('1');
			$('#bidDate_'+costCtrlId).val(date);
		}
		
	}else{
		$(dom).next().val('0');
		$('#'+clearEId).val('');
		$('#'+clearEId).prev().text('');
		if(idxScheType==4){
			$('#conStatusChangeFlg_'+costCtrlId).val('');
			$('#bidDate_'+costCtrlId).val('');
		}
	}
}
//清空输入框
function resetSearchCheck(){
	$(':input[name!="pageType"]','#main_search_form_check').val('');
	$('#bid_pur_search_check').find('a').css('color','#0167A2');
}

//完成
function costCheckComplete(id){
	if(!window.confirm('确认标记为完成吗？'))
		return;
	TB_showMaskLayer("正在标记完成...");
	$.post(_ctx+'/plan/cost-check!complete.action',{id:id},function(result){
		TB_removeMaskLayer();
		if('success' == result){
			showSuccessInfoCheck('已标记完成!');
			$('#main_'+id+',#detail_'+id).remove();
		}else{
			alert(result);
		}
	});
}
//退回
function costCheckReturn(id){
	if(!window.confirm('确认退回吗？'))
		return;
	TB_showMaskLayer("正在退回...");
	$.post(_ctx+'/plan/cost-check!back.action',{id:id},function(result){
		TB_removeMaskLayer();
		if('success' == result){
			showSuccessInfoCheck('已退回!');
			$('#main_'+id+',#detail_'+id).remove();
		}else{
			alert(result);
		}
	});
}