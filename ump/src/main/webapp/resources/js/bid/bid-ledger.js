/**
 * 成本投标台账模块脚本
 */

//主页面
//加载区域项目树
var gTreePanel;
function loadAreaProjectTree(){
	var url = _ctx + '/bid/bid-ledger!getAreaProjectTree.action';
	$.post(url, {}, function(result){
		gTreePanel = new TreePanel({
			renderTo:'leftTreePanel',
			'root' : eval('('+result+')'),
			'ctx':_ctx
		});
		gTreePanel.render();
		gTreePanel.isExpendSelect = true;
		//监听事件
		gTreePanel.addListener("check",function(node){
			searchBidLedger();
		});
		gTreePanel.on(function(node){
			var nodeType = node.attributes.nodeType;		
			//1-非根节点 
			if("1" != nodeType){
				if(node.isExpand){
					node.collapse();
				}else{
					node.expand();
				}
				searchBidLedger();
			}
		}); 

		searchBidLedger();
	});
} 

//执行快速搜索
function execQuickItem(jDom){
	//$(dom).css('color','red').siblings('a').css('color','#0167A2');
	
	var val = jDom.attr('typeCd');
	$("#selectBidCd").val(val);
	searchBidLedger();
}
//清空搜索条件
function resetSeniorPanel(){
	$(':input','#seniorPanel').each(function(){
		var t = $(this).hasClass('btn_new');
		if( !t){
			$(this).val('');
		}
	});
	//$('.quickBidItem').css('color','#0167A2');
	
	$('#sort').val('');
	$('#order').val('');
	$('.sortField').removeClass('sortFieldDesc').removeClass('sortFieldAsc');
}

//重新计算高度
function resetLeftPanel(){
//	
	var docHeight = $(document).height();
//	var headHeight= $('#bodyHead').height();

//	var firstFlg = $('#leftTreePanel').attr('firstFlg');
//	$('#leftTreePanel').attr('hh',headHeight);
		
	$('#leftTreePanel').height(docHeight);
}

//通过表单搜索条件搜索列表
function searchBidLedger(){
	jumpPage(1);
}
function jumpPage(pageNo) {
	
	if(typeof(pageNo) == 'undefined' || (pageNo == '')){
		$("#pageNo").val(1);
	}else{
		$("#pageNo").val(pageNo);
	}
	
	//选中项目
	//var arrProjects = (gTreePanel.getModifiedLeafNodes('project'))[0];
//	var arr = new Array();
//	for(var i=0; i < arrProjects.length; i++){
//		arr.push(arrProjects[i]);
//		arr.push(',');
//	}
//	var strProjects = arr.join('');
	$('#selectItemCds').val('');
	
	//状态
	var bidStatusCd = '';
	$('#quickClickPanel span[class*=quickBidItem-click]').each(function(i){
		bidStatusCd += $(this).attr("typeCd") + ",";
	});
	$("input[name=bidStatusCd]").val(bidStatusCd);


	TB_showMaskLayer("正在搜索...");
	$("#mainFormSearch").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		$(".searchResultPanel").html(result);
		
	});
}
//跳转至给定的页面,配合前台的分页搜索
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}

//******以下是搜索列页面使用******/

//保存新增标后核对
function updateBidLedger(id){		

	TB_showMaskLayer("正在保存...");
	$('#form_'+id).ajaxSubmit(function(result){	
		if('success' == result){
			TB_removeMaskLayer();
			showSuccessWithId(id,'保存成功！');
			
			// 若计划开始日期  < 实际开始日期,红色
			// 若计划定标日期 < 实际定标日期,红色
			
			var bidOpenPlanDate    = $('#edit_'+ id+' input[name=bidOpenPlanDate]');
			var bidOpenRealDate    = $('#edit_'+ id+' input[name=bidOpenRealDate]');
			var bidConfirmPlanDate = $('#edit_'+ id+' input[name=bidConfirmPlanDate]');
			var bidConfirmRealDate = $('#edit_'+ id+' input[name=bidConfirmRealDate]');
			
			//行
			var targetOpenRealDate = $('#brief_'+ id+' td[fileldName=bidOpenRealDate]');
			var targetConfirmRealDate = $('#brief_'+ id+' td[fileldName=bidConfirmRealDate]');
			
			targetOpenRealDate.html(bidOpenRealDate.val());
			targetOpenRealDate.attr('title',bidOpenRealDate.val());
			
			targetConfirmRealDate.html(bidConfirmRealDate.val());
			targetConfirmRealDate.attr('title',bidConfirmRealDate.val());
			
			if(!validateDateNormal(bidOpenPlanDate.val(), bidOpenRealDate.val())){
				targetOpenRealDate.css({color:'red'});
			}
			if(!validateDateNormal(bidConfirmPlanDate.val(), bidConfirmRealDate.val())){
				targetConfirmRealDate.css({color:'red'});
			}
		}else{
			alert(result);
		}
	});
}

//比较日期,若strDate1>strDate2,则正常;否则,不正常;
function validateDateNormal(strDate1, strDate2){
	if($.trim(strDate1) == '' || $.trim(strDate2) == ''){
		return true;
	}
	// 用 - 分隔符将日期分开
	var maxDateSplit = strDate1.split("-");
	var minDateSplit = strDate2.split("-");
	// 创建 Date 对象
	var date1 = new Date(maxDateSplit[0], maxDateSplit[1], maxDateSplit[2]);
	var date2 = new Date(minDateSplit[0], minDateSplit[1], minDateSplit[2]);
	return (date1 >= date2);
}

//显示提示,渐变效果
function showSuccessWithId(id,text){
	$('#succInfoId_'+id).text(text).show().fadeOut(2000);
}

//显示标段明细
function showLedgerDetail(bidLedgerId){
	openWindow('bidLedgerDetail','投标明细',_ctx + '/bid/bid-ledger!detail.action?id=' + bidLedgerId);
}

///****************投标台账明细页面************/

//加载工程列表
function loadProjectList(bidLedgerId){
	var url=_ctx+"/bid/bid-project!list.action";
	var data={bidLedgerId: bidLedgerId};
	$.post(url,data,function(result){		
		$('#projectListPanel').html(result);
		autoHeight();
	});	
}
//加载投标单位信息
function loadBidSup(bidLedgerId){
	var url=_ctx+"/bid/bid-sup!list.action";
	var data={bidLedgerId: bidLedgerId};
	$.post(url,data,function(result){		
		$('#supListPanel').html(result);
		autoHeight();
	});	
}

//bid-project-list.jsp
//保存新建工程信息
function saveNewBidProject(bidLedgerId){	
	TB_showMaskLayer("正在保存...");
	$('#newProjectForm').ajaxSubmit(function(result){				 
		TB_removeMaskLayer();
		if("success" == result){
			showSuccessInfoCheck('保存成功！');
			loadProjectList(bidLedgerId);
		}else{
			alert(result);
		}
	});
}
//提示完成
function showSuccessInfoCheck(text){
	$('#succInfoId').text(text).show().fadeOut(5000);
}
//删除工程
function doDeleteProject(bidProjectId,projectName,dom){
	if(!window.confirm('确认删除工程['+ projectName +']?')){
		return;
	}
	///TODO 校验是否引用(导入数据)
	var url = _ctx+"/bid/bid-project!delete.action";	
	TB_removeMaskLayer('正在删除...');
	$.post(url,{id: bidProjectId},function(result){
		TB_removeMaskLayer();
		if('success' == result){
			var numProjectCount = new Number($("span[numProjectCount]").html());
			$("span[numProjectCount]").html(numProjectCount -1 );
			showSuccessInfoCheck('删除成功！');
			
			$(dom).parent().parent().remove();
		}else{
			showSuccessInfoCheck('删除失败！');		 
			alert(result);
		}
	});
}
//提示完成
function showSuccessInfo(id,text){
	$('#'+id).text(text).show();
	$('#'+id).fadeOut(5000);
}
//更新工程信息
function updateBidProject(bidProjectId,fieldName,dom){		
	var newVal = $.trim($(dom).val());
	var oldVal = $.trim($(dom).prev().html());

	$(dom).hide();
	
	if( oldVal == '&nbsp;'){
		oldVal = '';
	}
	
	if( newVal == oldVal){
		$(dom).prev().show();
		return;
	}
	var url = _ctx + "/bid/bid-project!saveProperty.action";	
	var data={id: bidProjectId, fieldName: fieldName, newVal: newVal, oldVal: oldVal};
	TB_showMaskLayer("正在保存...");
	$.post(url, data, function(result){
		TB_removeMaskLayer();
		if('success' == result){
			showSuccessInfoCheck('更新成功！');
			if( newVal == ''){
				newVal = '&nbsp;';
			}
			$(dom).prev().html(newVal).show();
		}else{
			showSuccessInfoCheck('更新失败！');//+result
		}
	});
}



//提交保证金
function editMarginCommit(bidProjectId,bidLedgerId){
	var url=_ctx+"/bid/bid-sup!save.action";
	var data={id:id};
	$.post(url,data,function(result){	
		if('success' == result){
			loadBidSup(bidLedgerId);
		}else{
			alert(result);
		}
	});	
}
//中标修改
function editBidFlag(id){
	var url=_ctx+"/bid/bid-sup!save.action";
	var data={id:id,isBidFlg:1};
	$.post(url,data,function(result){		
		//加载投标供应商
		loadBidSup('${bidLedgerId}');
		init();
	});	
}

//弹出附件窗口
/**
 * @param title: 标题
 * @param entityId: id
 * @param bizModuleCd: 模块CD(eg:bidProject,bidSupTech)
 */
function openAttachment(title,entityId,bizModuleCd){
	var url = _ctx + "/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd="+bizModuleCd+"&bizEntityName=BidProject";
	ymPrompt.win({
		message: url,
			width:500,
			height:300,
			title:'查看附件',
			iframe:true,
			handler : function(){}
	 });
}

function viewBizItem(bidLedgerId){
	var url = _ctx + '/bid/bid-ledger-excel!bisexcel.action?bidLedgerId=${bidLedgerId}';
	openWindow('bidLedgerExcel','商务标',url);
}
function viewTechItemList(bidLedgerId){
	var url = _ctx+"/bid/bid-tech-item!list.action?bidLedgerId="+bidLedgerId;
	openWindow('bidLedgerDetail','技术标',url);
}

function resetTempForm(){
	$("#newBnt").trigger('click');
}
function lenStr(s) {
	var l = 0;
	var a = s.split("");
	for (var i=0;i<a.length;i++) {
	  if (a[i].charCodeAt(0)<299) {
		  l++;
	  } else {
		  l+=2;
	  }
	 }
	return l;
}
function saveBidLedger(){
	if($("#biaoShuNo").val()=="" || $("#biaoShuNo").val()==0 ){ 
         alert("标书编号不能为空");
         $("#biaoShuNo").focus();
         return;
      }
	if(lenStr($("#biaoShuNo").val())>20){ 
         alert("标书编号不能大于20个字符");
         $("#biaoShuNo").focus();
         return;
      }
      if($("#biaoDuan").val()=="" || $("#biaoDuan").val().length==0 ){ 
	         alert("标段内容不能为空");
	         $("#biaoDuan").focus();
	         return;
	      }
      if($("#biaoDuan").val().trim().length==0){ 
	         alert("标段内容不能为空");
	         $("#biaoDuan").focus();
	         return;
	      }
      var zbFlag="false";//判断'总包'复选框是否选择
      var yqFlag="false";//判断'邀请类别'复选框是否选择
      var ysFlag="false";//判断'预算'复选框是否选择
      $("[name=overAllYes]:checkbox:checked").each(function(){
    	  zbFlag=$(this).val();
      });
      $("[name=overAllNo]:checkbox:checked").each(function(){
    	  zbFlag=$(this).val();
      });
      $("[name=showFlag]:checkbox:checked").each(function(){
    	  yqFlag=$(this).val();
      });
      $("[name=hideFlag]:checkbox:checked").each(function(){
    	  yqFlag=$(this).val();
      });
      $("[name=outFlag]:checkbox:checked").each(function(){
    	  ysFlag=$(this).val();
      });
      $("[name=inFlag]:checkbox:checked").each(function(){
    	  ysFlag=$(this).val();
      });
      if(zbFlag == 'false'){
    	  alert("请选择'总包'类型!");
    	  return;
      }
      if(yqFlag == 'false'){
    	  alert("请选择'邀请类别'类型!");
    	  return;
      }
      //验证'预计金额'
      var planMoney = $("#planMoney").val();   
      var len = planMoney.length;   
      if(len == 0){  
          alert("'预计金额'不能为空！");
          $("#planMoney").focus();
          return;
      }
      planMoney=planMoney.replace(/,/g,"");
      if(Number(planMoney)<=0 || isNaN(Number(planMoney))){
    	  alert("'预计金额'必须大于0！");
    	  $("#planMoney").attr("value",'');
    	  $("#planMoney").focus();
          return;
      }
      if(parseFloat(planMoney)>100000000000){
    	  alert("'预计金额'过大！");
    	  $("#planMoney").attr("value",'');
    	  $("#planMoney").focus();
          return;
      }
      if(ysFlag == 'false'){
    	  alert("请选择'预算'类型!");
    	  return;
      }
      
	var pdv = new Validate("templetForm");
	if(pdv.validate()){
		$('#templetForm').ajaxSubmit(function(result){
			var rs=result.split(",");			
			if(rs[0]=='success'){
				$("#newBnt").trigger('click');
				jumpPage();
			}else{
				alert(rs[1]);
			}
			
		});	
		}		
	}

 

//项目权限管理
function manageRole(){
	openWindow('bidProjectRole','项目权限管理',_ctx + '/bid/bid-project-role!main.action');
}
