var gTreePanel_one;
var gTreePanel_two;
var gTreePanel_three;


//获取选中的tab
function getSelectedTab(){
	return $("#lefTNav ul.tab li.li-click").attr('rel');
}
//获取搜索表单(选中标签)
function getSearchForm(){
	var tab = getSelectedTab();
	if( tab == ''){
		return;
	}
	$('#searchPanel div[rel='+tab+']').show();
	$('#searchPanel div[rel='+tab+']').siblings().hide();
	
	hideListPanel();
}

//关闭搜索
function closeTabSearch(){
	clearTabSearch();
	hideTabSearch();
}
function hideTabSearch(){
	$('#searchPanel > div').hide();
}
function hideResultList(){
	$('#rsTable').hide();
}
function showResultList(){
	$('#rsTable').show();
}

//清空
function clearTabSearch(){
	$('#searchPanel').find('input[type=text]').val('');
}

//设置选中项
function setSelectedItem(tree){
	
	var tab = getSelectedTab();
	var cds = getSelectedItem(tree);
	$('form[rel='+tab+'] input[name=selectedItems]').val(cds);
	
}
function getSelectedItem(tree){
	if(tree == null || typeof(tree) == undefined){
		return '';
	}
	var arr = tree.getModifiedLeafNodes('project')[0];
	var pushList = new Array();
	for(var i=0; i< arr.length; i++){
		pushList.push(arr[i]);
	}
	return pushList.join(',');
}
//定标数据库
function loadTree(divId, tab){ 
	
	var action = '';
	if( 'bidded' == tab){
		action = 'firstTree';
	}
	if( 'billing' == tab){
		action = 'secondTree';
	}
	if( 'list' == tab){
		action = 'thirdTree';
	}
	
	var url = _ctx + '/ctdb/ctdb-index!'+ action +'.action';
	$.post(url, {}, function(result){
		var tree = new TreePanel({
			renderTo: (divId),
			'root' : eval('('+result+')'),
			'ctx':_ctx
		});
		tree.render();
		tree.isExpendSelect = true;
		//监听事件
		tree.addListener("check",function(node){
			searchByModuleId(tab);
		});
		tree.on(function(node){
			var nodeType = node.attributes.nodeType;	
			//1-根节点
			if("1" == nodeType){
				return;
			}
			searchByModuleId(tab);
		}); 
		
		

		if( 'bidded' == tab){
			gTreePanel_one = tree;
		}
		if( 'billing' == tab){
			gTreePanel_two = tree;
		}
		if( 'list' == tab){
			gTreePanel_three = tree;
		}
	});
} 

//根据模块ID执行搜索或其他动作
function searchByModuleId(tab){
	
	//设置分页
	jumpPageTab(tab);
} 
function showListBtn(){
	$('#listBnt').show();
}
function hideListBtn(){
	$('#listBnt').hide();
}

function jumpPageTab(tab,pageNo){

	
	//不显示查询条件
	hideTabSearch();
	
	
	//不显示清单面板
	hideListPanel();
	
	
	if (tab == null || typeof(tab) == undefined){
		tab = getSelectedTab();
	}
	switch(tab){
		case 'bidded':
			setSelectedItem(gTreePanel_one);
			hideListBtn();
			break;
		case 'billing':
			setSelectedItem(gTreePanel_two);
			hideListBtn();
			break;
		case 'list':
			setSelectedItem(gTreePanel_three);
			showListBtn();
			break;
		default:
			return;
	} 
	
	$('#leftTreePanel div[rel='+tab+']').show();
	$('#leftTreePanel div[rel='+tab+']').siblings().hide();	
	
	if( pageNo == null || typeof(pageNo) == undefined){
		pageNo = 1;
	}
	$('form[rel='+tab+'] input[name="pageNo"]').val(pageNo);
	TB_showMaskLayer("正在搜索...");
	$('form[rel='+tab+']').ajaxSubmit(function(result){
		TB_removeMaskLayer();
		$("#rsTable").html(result).show();
	});
}

//跳转至给定的页面,配合前台的分页搜索
function jumpPageTo() {
	var pageNo = $("#pageTo").val();
	if(pageNo == null || typeof(pageNo) == undefined){
		pageNo = 1;
	}
	var num = parseInt(pageNo);
	jumpPage(num);
}
//跳转到相应的页
function jumpPage(pageNo) {	
	jumpPageTab(getSelectedTab(), pageNo);
}



//弹出搜索和新增
function showListDown(dom){	
	var _jDom = $(dom);
	var $offset = _jDom.offset();
	$('#listMenu').hide();
	$('#listMenu').css({left:$offset.left,top:$offset.top + _jDom.height()+3}).show();
}

function hideListPanel(){
	$('#newListForm').hide();
}
function showListPanel(){
	$('#newListForm').show();
}

//新增清单
function newListForm(){
	
	hideTabSearch();
	
	hideResultList();
	
	var url = _ctx+'/ctdb/ctdb-list-content!getForm.action';
	$.post(url, {newListForm:'form'}, function(result){
		$("#newListForm").html(result).show();
	});
}

//编辑清单
function editListForm(listId,pageNo){	
	
	hideTabSearch();
	
	hideResultList();
	
	var url=_ctx+'/ctdb/ctdb-list-content!getForm.action?listId='+listId;
	$.post(url, {newListForm:'form','pageNo':pageNo}, function(result){
		$("#newListForm").html(result).show(); 	
	});
}
//一二级联动搜索
function firstCatChange(dom){
	var jdom=$(dom);
	var data={};
	data={firstCat:jdom.find("option:selected").val()};
	//alert(jdom.find("option:selected").val());
	var url=_ctx+'/ctdb/ctdb-list-content!getSecondCat.action';
	$.post(url, data, function(result){
		$("#secCat").html(result);
	});
}
//弹出的表单
function firstCatChange2(dom){
	var jdom=$(dom);
	var data={};
	data={firstCat:jdom.find("option:selected").val()};
	//alert(jdom.find("option:selected").val());
	var url=_ctx+'/ctdb/ctdb-list-content!getSecondCat.action';
	$.post(url, data, function(result){
		$("#secCat2").html(result);
	});
}

//保存清单数据库表单数据
function saveCtdbListContent(dom){
	if(validateForm()){
		var jdom=$(dom);
		$('#listForm').ajaxSubmit(function(result){			
			//重置表单
			resetForm();
			var rs=result.split(",");
			if('success'==rs[0]){
				editListForm(rs[1],1);
			}
		});
	}	
}
//保存清单数据库表单数据
function editCtdbListContent(dom){
	if(validateForm()){
		var jdom=$(dom);
		$('#listForm').ajaxSubmit(function(result){
			alert('修改成功!');
			//重置表单
			//resetForm();
		});
	}	
}


//表单验证
function validateForm(){
	
	//数据库大类
	var firstCat=$("select[name='firstCat']").find("option:selected").val();
	//小类
	var secCat=$("select[name='secCat']").find("option:selected").val();
	//大小类验证
	if(firstCat==null||firstCat==''){
		alert('数据库大类不能为空');
		return false;
	}
	if(secCat==null||secCat==''){
		alert('小类不能为空');
		return false;
	}
	//清单编号
	var listCd=$("#listCd").val();
	if(listCd==null||listCd==''){
		alert('清单编号为空');
		return false;
	}else if(!validateLenth(listCd,100,'清单编号')){
		return false;
	}
	//清单名称
	var listName=$("#listName").val();
	if(listName==null||listName==''){
		alert('清单名称为空');
		return false;
	}else if(!validateLenth(listName,100,'清单名称')){
		return false;
	}
	//说明
	var listDesc=$("#listDesc").val();
	if(listDesc==null||listDesc==''){
		alert('说明为空');
		return false;
	}else if(!validateLenth(listDesc,500,'说明')){
		return false;
	}
	//数量
	var qty=$("#qty").val();
	qty=qty.replace(/,/g,"");
	if(qty==null||qty==''){
		alert('数量为空');
		return false;
	}else if(parseFloat(qty)<0.0){
		alert('数量必须大于0');
		return false;
	}else if(parseFloat(qty)>100000000){
		alert('数量过大');
		return false;
	}
	//单位
	var measurement=$("#measurement").val();
	if(measurement==null||measurement==''){
		alert('单位为空');
		return false;
	}else if(!validateLenth(measurement,10,'单位')){
		return false;
	}
	//单价
	var unitPrice=$("#unitPrice").val();
	unitPrice=unitPrice.replace(/,/g,"");
	if(unitPrice==null||unitPrice==''){
		alert('单价为空');
		return false;
	}else if(parseFloat(unitPrice)<0.0){
		alert('单价必须大于0');
		return false;
	}else if(parseFloat(unitPrice)>100000000){
		alert('单价过大');
		return false;
	}
	//主材耗损率
	var mainMateRate=$("#mainMateRate").val();
	mainMateRate=mainMateRate.replace(/,/g,"");
	if(mainMateRate==null||mainMateRate==''){
		alert('主材耗损率为空');
		return false;
	}else if(parseFloat(mainMateRate)<0.0){
		alert('主材耗损率必须大于0');
		return false;
	}else if(parseFloat(mainMateRate)>100000000){
		alert('主材耗损率过大');
		return false;
	}
	//人  工  费
	var personalPrice=$("#personalPrice").val();	
	if(!isNaN(Number(personalPrice))&&personalPrice!=null&&personalPrice!=''){
		personalPrice=personalPrice.replace(/,/g,"");
		if(parseFloat(personalPrice)>100000000){
			alert('人工费过大');
			return false;
		}if(parseFloat(personalPrice)<0){
			alert('人工费不能小于0');
			return false;
		}
	}
	//主  材  费
	var mainMatePrice=$("#mainMatePrice").val();
	mainMatePrice=mainMatePrice.replace(/,/g,"");
	if(!isNaN(Number(mainMatePrice))&&mainMatePrice!=null&&mainMatePrice!=''){
		if(parseFloat(mainMatePrice)>100000000){
			alert('主材费过大');
			return false;
		}
		if(parseFloat(mainMatePrice)<0){
			alert('主材费不能小于0');
			return false;
		}
	}
	//辅  材  费
	var aidMatePrice=$("#aidMatePrice").val();
	aidMatePrice=aidMatePrice.replace(/,/g,"");
	if(!isNaN(Number(aidMatePrice))&&aidMatePrice!=null&&aidMatePrice!=''){
		if(parseFloat(aidMatePrice)>100000000){
			alert('辅材费过大');
			return false;
		}if(parseFloat(aidMatePrice)<0){
			alert('辅材费不能小于0');
			return false;
		}
		
	}
	//机  械  费
	var machinePrice=$("#machinePrice").val();
	machinePrice=machinePrice.replace(/,/g,"");
	if(!isNaN(Number(machinePrice))&&machinePrice!=null&&machinePrice!=''){
		if(parseFloat(machinePrice)>100000000){
			alert('机械费过大');
			return false;
		}
		if(parseFloat(machinePrice)<0){
			alert('机械费不能小于0');
			return false;
		}
		
	}
	//管理费及利润
	var manageFees=$("#manageFees").val();
	manageFees=manageFees.replace(/,/g,"");
	if(!isNaN(Number(manageFees))&&manageFees!=null&&manageFees!=''){
		if(parseFloat(manageFees)>100000000){
			alert('管理费及利润过大');
			return false;
		}
		if(parseFloat(manageFees)<0){
			alert('管理费及利润不能小于0');
			return false;
		}
		
	}
	//税       金
	var tax=$("#tax").val();
	tax=tax.replace(/,/g,"");
	if(!isNaN(Number(tax))&&tax!=null&&tax!=''){
		if(parseFloat(tax)>100000000){
			alert('税 金过大');
			return false;
		}
		if(parseFloat(tax)<0){
			alert('税 金不能小于0');
			return false;
		}
	}
	//价格来源
	var priceFrom=$("#priceFrom").val();
	if(priceFrom!=null&&priceFrom!=''&&priceFrom.length>0)
	if(!validateLenth(priceFrom,200,'价格来源')){
		return false;
	}
	return true;
	
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
//重置表单
function resetForm(){
	//清单编号
	$("#listCd").val('');
	//清单名称
	$("#listName").val('');
	//说明
	$("#listDesc").val('');
	//数量
	$("#qty").val('');
	//单位
	$("#measurement").val('');
	//单价
	$("#unitPrice").val('');
	//人工费
	$("#personalPrice").val('');
	//主材费
	$("#mainMatePrice").val('');
	//辅材费
	$("#aidMatePrice").val('');
	//机械费
	$("#machinePrice").val('');
	//管理费及利润
	$("#manageFees").val('');
	//税金
	$("#tax").val('');
	//主材耗损率
	$("#mainMateRate").val('');
	//价格来源
	$("#priceFrom").val('');
}
//导入/导出窗口
function impWin(flg){
	//按钮
	var btn;
	//窗口标题
	var title;
	//1表示导入
	if(flg==1){
		 btn=[["导入",'imp'],["取消",'cancel']];
		 title="导入清单数据";
	}
	//2表示导出
	else{
		 btn=[["导出","exp"],["取消",'cancel']];
		 title="导出清单数据";
	}
	//构建弹出窗口
	ymPrompt.confirmInfo({
		icoCls : "",
		autoClose:false,
		message : "<div id='windetail'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 380,
		height : 180,
		title : title,
		closeBtn:true,
		afterShow : function() {
			//导入窗口
			var url=_ctx+"/ctdb/ctdb-list-content!getForm.action";
			var data={formTypeCat:'impform',formType:'1'};
			$.post(url,data,function(result){
				$("#windetail").html(result);
				if(flg==2){
					$("#impTable tbody tr").eq(2).remove();
				}
			});
		},
		handler : function(btn){
			//叉叉的关闭事件
			$("div.ymPrompt_close").click(function(){					
				ymPrompt.close();
				});
			//取消
			if(btn=='cancel'){
				ymPrompt.close();
			}
			//执行导入
			if(btn=='imp'){				
				//验证表单字段
				var valRes=validateClform();
				if(valRes){
					alert(valRes);
				}else{
					TB_showMaskLayer("正在上传并导入中...");
					$("#impForm").ajaxSubmit(function(result) {
						TB_removeMaskLayer();
						var rs=result.split(',');
						if(rs[0]=='success')
						alert('导入成功');
						else{
							alert(rs[1]);
						}
						jumpPage(1);
					});
					ymPrompt.close();
				}
			}
			//执行导出
			if(btn=='exp'){
				var valRes=validateExpClform();
				if(valRes){
					alert(valRes);
				}else{
					var firstCat=$("#firstCat").find("option:selected").val();
					var secCat=$("#secCat2").find("option:selected").val();
					var url=_ctx+'/ctdb/ctdb-index!exportExcel.action';
					window.location.href=url+"?firstCat="+firstCat+"&secCat="+secCat;
				}
				
			}
		},
		btn:btn
	});
}

//验证导入数据清单表单字段
function validateClform(){
	var firstCat=$("#firstCat").find("option:selected").val();
	var secCat=$("#secCat2").find("option:selected").val();
	var importFile=$("#importFile").val();
	if(firstCat){}
	else{
		return "数据库大类必选!";
	}
	if(secCat){}
	else{
		return "小类必选!";
	}
	if(importFile.length<1){
		return "请选择导入文件!";
	}
	return "";
	
}
//验证导出数据清单表单字段
function validateExpClform(){
	var firstCat=$("#firstCat").find("option:selected").val();
	var secCat=$("#secCat2").find("option:selected").val();
	//var importFile=$("#importFile").val();
	if(firstCat){}
	else{
		return "数据库大类必选!";
	}
	if(secCat){}
	else{
		return "小类必选!";
	}
	return "";
	
}
//下载excel清单数据库模板
function downExcelTemp(url){
	window.location.href=url;
}

function getFormParam(formId, name){
	var val = $('#' + formId + ' input[name='+name+']').val();
	return (val == null || typeof(val) == undefined || val == '')?'':val;
}

//导出为excel
function exportToExcel(){
	
	//获取激活的标签ID
	var tab = getSelectedTab();

	var tree= null;
	if( 'bidded' == tab){
		tree = gTreePanel_one;
	}
	else if( 'billing' == tab){
		tree = gTreePanel_two;
	}
	else if( 'list' == tab){
		tree = gTreePanel_three;
	}
	else{
		return;
	}
	
	setSelectedItem(tree);
	
	//定标数据库
	if('bidded'==tab){ 

		var arr = new Array();
		arr.push(_ctx+"/ctdb/ctdb-fixed-bid!exportToExcel.action?");
		arr.push("ecode=1");
		arr.push("&selectedItems=" + getFormParam('biddedForm','selectedItems'));
		arr.push("&orgName=" 	   + getFormParam('biddedForm','orgName'));
		arr.push("&projectName="   + getFormParam('biddedForm','projectName'));
		arr.push("&bidSupName="    + getFormParam('biddedForm','bidSupName'));
		arr.push("&bidDate="       + getFormParam('biddedForm','bidDate'));
		arr.push("&bidDateTo="     + getFormParam('biddedForm','bidDateTo'));
		
		window.location.href = encodeURI(arr.join(''));
	}//结算数据库
	else if('billing' == tab){ 
		
		var arr = new Array();
		arr.push(_ctx+"/ctdb/ctdb-billing-app!exportToExcel.action?");
		arr.push("ecode=1");
		arr.push("&selectedItems=" 	+ getFormParam('biddedForm','selectedItems'));
		arr.push("&orgName=" 		+ getFormParam('biddedForm','orgName'));
		arr.push("&projectName="	+ getFormParam('biddedForm','projectName'));
		arr.push("&impSupName="		+ getFormParam('biddedForm','impSupName'));
		arr.push("&billingDate="	+ getFormParam('biddedForm','billingDate'));
		arr.push("&billingDateTo="	+ getFormParam('biddedForm','billingDateTo'));

		window.location.href = encodeURI(arr.join(''));
	}
	//清单数据库
	else if('list'==tab){ 
		var arr = new Array();
		arr.push(_ctx+"/ctdb/ctdb-list-content!exportToExcel.action?");
		arr.push("ecode=1");
		arr.push("&selectedItems=" 	+ getFormParam('listForm','selectedItems'));
		arr.push("&listCd="			+ getFormParam('listForm','listCd'));
		arr.push("&listName="		+ getFormParam('listForm','listName'));
		arr.push("&createdDate="	+ getFormParam('listForm','createdDate'));
		arr.push("&createdDateTo="	+ getFormParam('listForm','createdDateTo'));
		arr.push("&statusCd="		+ getFormParam('listForm','statusCd'));

		window.location.href = encodeURI(arr.join(''));
	}
	
}

//打开网批
function openResTask(id, resAuthTypeCd,unkn){
	var url = _ctx + '/res/res-approve-info.action?id=' + id + '&resAuthTypeCd=' + resAuthTypeCd;
	openWindow('resApprove', '网上审批', url);
}

//打开窗口
function openWindow(id,desc,url){
	if(window.parent && window.parent.parent.TabUtils){
		window.parent.parent.TabUtils.newTab(id,desc,url);
	}else{
		window.open(url);
	}
}
//左边下角搜索，针对树菜单的搜索
function leftsearch(){
	//先判断是那个 被选中的
	//获取激活的标签ID
	searchByModuleId(getSelectedTab());
}

//提交清单状态
function commitList(statusCd,listId,pageNo){
	if(window.confirm('确认继续吗？')){
		var data={};
		data['entity.statusCd']=statusCd;
		data['listId']=listId;
		var url=_ctx+"/ctdb/ctdb-list-content!commitList.action";
		$.post(url,data,function(result){
			if('success'==result){
				editListForm(listId,pageNo);
			}
		});
	}	
}

//返回列表
function backListContent(pageNo){
	jumpPage(pageNo);
}
//驳回清单状态
function reject(statusCd,listId,pageNo){
	if(window.confirm('确认驳回？')){
		var data={};
		data['entity.statusCd']=statusCd;
		data['listId']=listId;
		var url=_ctx+"/ctdb/ctdb-list-content!commitList.action";
		$.post(url,data,function(result){
			if('success'==result){
				alert('驳回成功');
				editListForm(listId,pageNo);
			}
		});
	}	
}
//删除清单
function delteList(listId,pageNo){
	if(window.confirm('确认删除？')){
		var data={};
		data['listId']=listId;
		var url=_ctx+"/ctdb/ctdb-list-content!delete.action";
		$.post(url,data,function(result){
			if('success'==result){
				jumpPage(pageNo);
			}
		});
	}
}
//上传附件弹出窗
function openAttachmentRefresh(jdom,entityId,bizModuleCd,enableRefreshFlg){
	var validateUrl  = _ctx+'/ctdb/ctdb-list-content!validateAttach.action';//校验是否存在附件的url,若返回1,存在,否则不存在;
	//弹出窗口的URL
	var popWinUrl=_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd="+bizModuleCd+"&filterType=image|office";
	if(enableRefreshFlg){
		popWinUrl=popWinUrl+'&onlyShow=true';
	}
	var title = '上传附件';
	ymPrompt.win({
		message: popWinUrl,
		width:550,
		height:300,
		title: title,
		afterShow:function(){},
		iframe:true,
		handler:function(e){
			if(validateUrl){
				//eg:判断供应商是否有上传附件，若有则将附件按钮值为1
				$.post(validateUrl, {id: entityId, bizModuleCd: bizModuleCd}, function(result) {
					if("1" == result){
						jdom.removeClass('noAttachFile').addClass('hasAttachFile');
						jdom.attr('title','已上传附件');
					}else if("0" == result){
						jdom.removeClass('hasAttachFile').addClass('noAttachFile');
						jdom.attr('title','请上传附件');
					}
				});
			}
		}
	});
	
}