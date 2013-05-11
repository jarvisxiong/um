//加载页面功能树
var curAuthTypeCd='';
//liwei3 add
var curModuleCd='';
var curModuleId='';
var treePanel;
//搜索定位表单
var curVal = null;
var curNode = null;
var searchTreeManager;
var userSearchMgr;
var g_quicksearch='快速搜索...';
function openAtta(url,fileName){
	var suffix = fileName.substring(fileName.lastIndexOf('.')+1,fileName.length).toLowerCase();
	if(suffix == 'pdf'){
		try{
			window.parent.TabUtils.newTab("pdf-cont-atta","查看附件",url,true);
		}catch(e){
			window.open(url);
			setInterval(arg1, arg2);
		}
	}else{
		window.open(url);
	}
}
var now = new Date();
function GetServerTime()
{
	var urodz = new Date(reduceTime);
	now.setTime(now.getTime()+250);
	days = (urodz - now) / 1000 / 60 / 60 / 24;
	daysRound = Math.floor(days);
	hours = (urodz - now) / 1000 / 60 / 60 - (24 * daysRound);
	hoursRound = Math.floor(hours);
	minutes = (urodz - now) / 1000 /60 - (24 * 60 * daysRound) - (60 * hoursRound);
	minutesRound = Math.floor(minutes);
	seconds = (urodz - now) / 1000 - (24 * 60 * 60 * daysRound) - (60 * 60 * hoursRound) - (60 * minutesRound);
	secondsRound = Math.round(seconds);
	document.getElementById("date").innerHTML = daysRound;
	document.getElementById("time").innerHTML = hoursRound + ":" + minutesRound + ":" + secondsRound;
}
var content_editor;
//liwei3 add
function openSelectDetail(){
	var data={};
	if(curModuleCd){
		data.moduleCd=curModuleCd;
		data.moduleId=curModuleId;
	}
	
	loadJs(_ctx+'/js/res/resApproveInput_0220.js');
	loadJs(_ctx+'/resources/js/res/resShow.js');
	loadJs(_ctx+'/resources/js/res/resSingleUpload.js');
	loadJs(_ctx+'/js/validate/PdValidate.js');
	loadJs(_ctx+'/js/validate/formatUtil.js');
	loadJs(_ctx+'/resources/js/jquery/jQuery.artTxtCount.js');
	
	$.post(_ctx + "/res/res-rights!addSelect.action", data , function(result) {
		$("#content").html(result);
		resetLeftPanel();
	});
}

function openDetail(resApproveInfoId,pageNo,listMode) {
	//alert("anson mark");
	var data={
		id : resApproveInfoId
	};
	if(curAuthTypeCd){
		data.resAuthTypeCd=curAuthTypeCd;
	}
	if(resApproveInfoId == '' || resApproveInfoId == null){
		TB_showMaskLayer("正在创建申请单...",5000);
	}else{
		TB_showMaskLayer("正在查看明细...",5000);
	}
	
	//搜索供应商
	loadJs(_ctx+'/js/res/resApproveInput_0220.js');
	loadJs(_ctx+'/resources/js/res/resShow.js');
	loadJs(_ctx+'/resources/js/res/resSingleUpload.js');
	loadJs(_ctx+'/js/validate/PdValidate.js');
	loadJs(_ctx+'/js/validate/formatUtil.js');
	loadJs(_ctx+'/resources/js/jquery/jQuery.artTxtCount.js');
	
	$.post(_ctx + "/res/res-approve-info!input.action",data , function(result) {
		TB_removeMaskLayer();
		
		$("#content").html(result);
		
		// 修复bug
		$('#pdChilltip').remove();
		//编辑状态
		if($("#nodeCd").val()==""){
			loadJs(_ctx+'/js/datePicker/WdatePicker.js');
		}
		//是否有富文本框xheditor-simple样式
		if($('textarea.xheditor-simple').length > 0){
			var rtn=loadJs(_ctx+'/resources/js/xheditor/xheditor-zh-cn.min.js',initXhedit);
			if (!rtn){
				initXhedit();
			}
		};
		
		
		resetLeftPanel();
		
	});
}
function initXhedit(){
	content_editor = $('textarea.xheditor-simple').xheditor({tools:'Blocktag,Fontface,FontSize,Bold,Italic,Underline,FontColor,BackColor,|,Align,List,Outdent,Indent,Table,|,Removeformat,Preview',height:'250'});
}
function getValidValue(value){
	if(typeof(value)=='undefined'){
		value='';
	}
	return value;
}
function initQuickSearch(){
	setQuickSearchColor();
	
	$('#quicksearch').bind('focus',function(){
		$(this).css({color:"#5A5A5A"});
	});
}
function setQuickSearchColor(){
	if ($('#quicksearch').val()==''){
		$('#quicksearch').val(g_quicksearch);
		$('#quicksearch').css({color:"#cccccc"});
	}
}
function initSearchCondition(){
	$('#srh_listMode').val(getValidValue($("#listMode").val()));
	$('#srh_qsCondition').val(getValidValue($("#qsCondition").val()));
	$('#srh_selectNodeCd').val(getValidValue($("#selectNodeCd").val()));
	$('#srh_selectOpinion').val(getValidValue($("#selectOpinion").val()));
	$('#srh_approveCd').val(getValidValue($("#approveCd").val()));
	$('#srh_filter_LIKES_statusCd').val(getValidValue($("#filter_LIKES_statusCd").val()));
	$('#srh_filter_GED_startDate').val(getValidValue($("#filter_GED_startDate").val()));
	$('#srh_filter_LTD_startDate').val(getValidValue($("#filter_LTD_startDate").val()));
	$('#srh_filter_LIKES_landproject').val(getValidValue($("#filter_LIKES_landproject").val()));
	$('#srh_filter_LIKES_titlename').val(getValidValue($("#filter_LIKES_titlename").val()));
	$('#srh_auditorUserNames').val(getValidValue($("#auditorUserNames").val()));
	$('#srh_auditorUserCds').val(getValidValue($("#auditorUserCds").val()));
	$('#srh_creatorUserNames').val(getValidValue($("#creatorUserNames").val()));
	$('#srh_creatorUserCds').val(getValidValue($("#creatorUserCds").val()));
	$('#srh_filter_LIKES_authTypeName').val(getValidValue($("#filter_LIKES_authTypeName").val()));
	$('#srh_filter_LIKES_authTypeCd').val(getValidValue($("#filter_LIKES_authTypeCd").val()));
	$('#srh_filter_LIKES_randomNo').val(getValidValue($("#filter_LIKES_randomNo").val()));
	$('#srh_filter_LIKES_displayNo').val(getValidValue($("#filter_LIKES_displayNo").val()));
	$('#srh_quicksearchValue').val(getValidValue($("#quicksearch").val()));
	if($('#srh_quicksearchValue').val() == g_quicksearch){
		$('#srh_quicksearchValue').val('');
	}
	
}
function searchRes() {
	if($('#quicksearch').val() == g_quicksearch){
		$('#quicksearch').val('');
	}
	$("#pageNo").val("1");
	initSearchCondition();
	TB_showMaskLayer("正在搜索...",5000);
	$("#mainFormSearch").ajaxSubmit(function(result) {
		$("#content").html(result); 
		resetLeftPanel();
		TB_removeMaskLayer();
		$('#srh_listMode').val(getValidValue($("#listMode").val()));
	});
}
function exportExcel() {
	$("#mainFormSearch").attr("action","res-approve-info!exportExcel.action");
	$("#mainFormSearch").submit();
	$("#mainFormSearch").attr("action","res-approve-info!load.action");
}

//更改左边高度
function resetLeftPanel(){
	autoHeight("res");
	return;
	if( $("#flg_firstFlg").val() == "0" ){
		$("#flg_baseDocHeight").val($(document).height());
	}
	
	var docHeight = parseFloat($("#flg_baseDocHeight").val());
	
	var titHeight = $("#idDivTitle").height();
	var rigHeight = $("#content").height();
	var minHeight = (docHeight - titHeight);   
  
	if( $("#flg_firstFlg").val() == "0" ){
		$("#flg_baseMinHeight").val(minHeight);
	}
	var baseMin = parseFloat($("#flg_baseMinHeight").val());
	
	//alert("rigHeight: " + rigHeight + ",baseMin:" + baseMin +",docHeight:"+docHeight);
	
	if( rigHeight < baseMin){
		$("#leftPanel").height(baseMin-$("#searchApproveFix").height()-8);
		$("#divTreeP").height(baseMin-$("#searchApproveFix").height()-8);
		$("#tableDiv").height(baseMin-$("#rightHeadTool").height()-$("#rightHeadQuick").height()-$("#rightHeadAdd").height()+93);  
	}else{
		//$("#tableDiv").height(rigHeight+50);
		$("#leftPanel").height(rigHeight);
		$("#divTreeP").height(rigHeight);
		//$("#tableDiv").height(baseMin-$("#rightHeadTool").height()-$("#rightHeadQuick").height()-$("#rightHeadAdd").height()+93);  
		//$("#leftPanel").height(baseMin-$("#searchApproveFix").height()-8);
		//$("#divTreeP").height(baseMin-$("#searchApproveFix").height()-8);
		//alert(baseMin-$("#rightHeadTool").height()-$("#rightHeadQuick").height()-$("#rightHeadAdd").height()+93);
		//alert(baseMin-$("#searchApproveFix").height()-8);
	}
	//important
	$("#flg_firstFlg").val("1");
	
	// 查看明细
	if( $("#detailPanelDiv").length == 1){
		$("#divAll").height(baseMin-5);
		$("#detailPanelDiv").height(baseMin- $("#funcPanelDiv").height() -30 );  
	} 
}

function jumpPage(pageNo) {
	if($('#quicksearch').val() == g_quicksearch){
		$('#quicksearch').val('');
	}
	$("#pageNo").val(pageNo);
	$('#srh_pageNo').val(getValidValue($("#pageNo").val()));
	$("#mainFormSearch").ajaxSubmit(function(result) {
		$("#content").html(result);
		autoHeight("res");
	});
}
function jumpPageTo() {
		var index = $("#pageTo").val();
		index = parseInt(index);
		if (index > 0) {
			jumpPage(index);
		}
}
function isEmpty(str){
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
}

function autoTexteare(selector,maxRow){
	maxRow=maxRow||10000;
//	alert(maxRow);
	jQuery(selector).each(
		function(){
			while(this.scrollHeight>this.clientHeight && this.rows<maxRow){
				 this.rows +=1;
			}
		});
 }

function bindAutoTexteare(pEvents,selector){
	jQuery(selector).each(
		function(intIndex){
			$(this).bind(pEvents,autoTexteare);
		});
}

//快速搜索事件
function searchSenior(){
	if($("#rightHeadTool").css("display")=="none"){
		$("#btnQuickSenior").addClass("quickSeniorHover");
		$("#rightHeadTool").show();
		$("#tableDiv").height($("#tableDiv").height()-$("#rightHeadTool").height()-15);
	}else{
		$("#btnQuickSenior").removeClass("quickSeniorHover");
		$("#rightHeadTool").hide();
		$("#tableDiv").height($("#tableDiv").height()+$("#rightHeadTool").height()+15);
	}
}

//高级搜索
function commonSearch() {
	$("#listMode").val("0");
	$("#qsCondition").val("");
	searchRes();
}

//选择,新增,审批中,完成,驳回,已经手,即将审批,我留言过,共享给我

function searchMyApprove(){
	$("select[id='filter_LIKES_statusCd']")[0].selectedIndex = 1;
	$("#listMode").val("0");
	searchRes();
}
function searchProcessing(){
	$("select[id='filter_LIKES_statusCd']")[0].selectedIndex = 2;
	$("#listMode").val("0");
	searchRes();
}
function searchCompleted(){
	$("select[id='filter_LIKES_statusCd']")[0].selectedIndex = 3;
	$("#listMode").val("0");
	searchRes();
}
function searchRefacted(){
	$("select[id='filter_LIKES_statusCd']")[0].selectedIndex = 4;
	$("#listMode").val("0");
	searchRes();
}
function searchMyHanded(){
	$("select[id='filter_LIKES_statusCd']")[0].selectedIndex = 5;
	$("#listMode").val("0");
	searchRes();
}
function searchWillMyChief(){
	$("select[id='filter_LIKES_statusCd']")[0].selectedIndex = 6;
	$("#listMode").val("0");
	searchRes();
}
function searchMyMessaged(){
	$("select[id='filter_LIKES_statusCd']")[0].selectedIndex = 7;
	$("#listMode").val("0");
	searchRes();
}
function searchSharedToMe(){
	$("select[id='filter_LIKES_statusCd']")[0].selectedIndex = 8;
	$("#listMode").val("0");
	searchRes();
}
function searchPushToMe(){
	$("select[id='filter_LIKES_statusCd']")[0].selectedIndex = 9;
	$("#listMode").val("0");
	searchRes();
}
function searchDelay(){
	$("select[id='filter_LIKES_statusCd']")[0].selectedIndex = 10;
	$("#listMode").val("0");
	searchRes();
}
function sortBy(element,property,oldOrder){
	var sortStr ;
	var old = oldOrder;
	if(typeof(oldOrder)==='undefined'){
		old = ''; 
	}
	if(old=='asc'){
		sortStr = 'desc'; 
	}else if (old=='desc'){
		sortStr = 'asc';
	}else{
		sortStr = 'asc';
	}
	$("#selectedOrderBy").val(property);
	$("#selectedOrder").val(sortStr);
	$('#srh_selectedOrderBy').val(getValidValue($("#selectedOrderBy").val()));
	$('#srh_selectedOrder').val(getValidValue($("#selectedOrder").val()));
	
	if($('#quicksearch').val() == g_quicksearch){
		$('#quicksearch').val('');
	}
	$("#mainFormSearch").ajaxSubmit(
		function(result){
			$("#content").html(result);
			resetLeftPanel();
		});
}
// 选择审批节点
function getMyNodeApprove(nodeCd) {
	$("#selectNodeCd").val(nodeCd);
	searchRes();
}
// 审批模式
function goApprovePattern() {
	$("#qsCondition").val("");
	$("#listMode").val("1");
	searchRes();
}
// 常规模式
function goRegularPattern() {
	$("#qsCondition").val("");
	$("#listMode").val("0");
	searchRes();
}
// 我的记录
function seleOption(opinion) {
	$("#selectOpinion").val(opinion);
	searchRes();
}
function searchMyReco(){
	$("#qsCondition").val("1");
	$("#listMode").val("0");
	$("#selectOpinion").val("");
	searchRes();
}
function searchMyDuty(){
	$("#qsCondition").val("2");
	$("#listMode").val("0");
	$("#selectOpinion").val("");
	searchRes();
}
function searchMyDeal(){
	$("#qsCondition").val("3");
	$("#listMode").val("0");
	$("#selectOpinion").val("");
	searchRes();
}
function ajustHeight(node, rows){
	  var flag= hideValue(node,rows);
	  if (flag){
		  node.style.cursor="pointer";
		  $(node).toggle(function(){
			  showOriValue(node, rows);
		  },function(){
			  hideValue(node, rows);
		  });
	  }
}
function hideValue(node,rows){
	  var v = node.innerHTML;
	  node.innerHTML = "";
	  var h1 = node.offsetHeight;
	  node.innerHTML = "&nbsp;";
	  var h2 = node.offsetHeight;
	  var rowHeight = h2 - h1;
	  node.innerHTML=v;
	  $(node).attr('oriValue',v);
	  var len = v.length, i = 4;
	  var flag=false;
	  while(node.offsetHeight > rowHeight * rows + h1){
	  node.innerHTML = v.substring(0, len - i) + '...'+'<span style="color:#909090">↓</span>';
	  i++;
	  flag=true;
	  }
	  if(flag){
		$(node).attr('title','点击展开');  
	  }
	  return flag;
}
function showOriValue(node,rows){
	node.innerHTML =  $(node).attr('oriValue');
	$(node).attr('title','点击收起');
}

function parseTextArea(){
    if(isPhone()){
    	$('textarea.xheditor-simple').removeClass(xheditor-simple);
    }
}
function resetSearchApproveInput(dom){
	if($(dom).val().trim() == ''){
		$(dom).val('搜索表单...');
		$(dom).css({color:"#cccccc"});
	}else{
		$(dom).css({color:"#5A5A5A"});
	}
}
function clearSearchApproveInput(dom){
	if( $(dom).val() == '搜索表单...'){
		$(dom).val('');
		$(dom).css({color:"#5A5A5A"});
	}
}
function resetQuickSearch(dom){
	if($(dom).val().trim() == ''){
		$(dom).val(g_quicksearch);
		$(dom).css({color:"#cccccc"});
	}else{
		$(dom).css({color:"#5A5A5A"});
	}
}
function clearQuickSearch(dom){
	if( $(dom).val() == g_quicksearch){
		$(dom).val('');
		$(dom).css({color:"#5A5A5A"});
	}
}

function underline(dom){
	$(dom).css('text-decoration','underline');
}
function removeUnderline(dom){
	$(dom).css('text-decoration','none');
}
//------------------------网上审批优化异步加载树结构新加方法 start -----------
function openAuthType(authTypeCd,authTypeName){
	var itemCd=authTypeCd;
	var resAuthName=authTypeName;
	$('#'+itemCd).css("background","#C5CDD8");
	$('#'+itemCd).siblings().css("background","#E4E7EC");
	$('#idCurAuthType').html(resAuthName);
	var isSearch=true;
	var isCanSend=true;
	curAuthTypeCd=itemCd;
	if (curAuthTypeCd=='QTSW_WJSP'){
		//文件审批发起权限
		var hasPermission=$('#flg_lwsx_p').val();
		hasPermission=eval(hasPermission);
		if (!hasPermission){
			$("#btnNewApply").attr("disabled","disabled");
			isCanSend=false;
			ymPrompt.alert({message:'没有发起权限！',title:"提示",width:220,height:180});
		}
	}else if (curAuthTypeCd.indexOf('JSGL_SYGCGZ')!=-1){
		//商品工程改造
		//$("#btnNewApply").attr("disabled","disabled");
		//isCanSend=false;
		//ymPrompt.alert({message:'很抱歉，此项表单已暂停使用，请使用其他流程表或纸面文件，如有不明请咨询总裁办企管部',title:"提示",width:400,height:180});
	}
	$('#resAuthName').val(resAuthName);
	if (isSearch){
		$.post(_ctx+"/res/res-approve-info!load.action",
		{
			resAuthTypeCd:curAuthTypeCd,  //
			listMode:"0",  //
			qsCondition:getValidValue($("#qsCondition").val()),
			approveCdSrh:getValidValue($("#approveCd").val()),
			selectOpinion:getValidValue($("#selectOpinion").val()),
			filter_LIKES_userName:getValidValue($("#filter_LIKES_userName").val()),
			filter_LIKES_approveUserCd:getValidValue($("#filter_LIKES_approveUserCd").val()),
			filter_GED_startDate:getValidValue($("#mainFormSearch_filter_GED_startDate").val()),
			filter_LTD_startDate:getValidValue($("#mainFormSearch_filter_LTD_startDate").val()),
			filter_LIKES_statusCd:getValidValue($("#filter_LIKES_statusCd").val()),
			filter_LIKES_approveUserName:getValidValue($("#filter_LIKES_approveUserName").val()),
			filter_LIKES_landproject:getValidValue($("#filter_LIKES_landproject").val()),
			filter_LIKES_titlename:getValidValue($("#filter_LIKES_titlename").val()),
			filter_LIKES_randomNo:getValidValue($("#filter_LIKES_randomNo").val())
		},
		function(result) {
			$("#content").html(result);
			if (!isCanSend){
				$("#btnNewApply").attr("disabled","disabled");
			}
			$('#srh_listMode').val(getValidValue($("#listMode").val()));
			resetLeftPanel();
		});
	}
}
function searchTreeNode(srcElem) {
	var wabTreeElemId = "tree-div";
	var resultElemId = "search-div";
	if (userSearchMgr)
		clearTimeout(userSearchMgr);
	userSearchMgr = setTimeout(function() {
		var val = $.trim($(srcElem).val());
		if (val == "") {
			$("#" + wabTreeElemId).show();
			$("#" + resultElemId).hide();
			return;
		} else {
			$("#" + wabTreeElemId).hide();
			$("#" + resultElemId).show();
			$(srcElem).css( {
				color : "#5A5A5A"
			});
			$("#" + resultElemId).addClass("waiting");
			$.post(_ctx + "/res/res-approve-info-tree!searchTreeList.action", {
				value : val
			}, function(result) {
				$("#" + resultElemId).html(result).removeClass("waiting").height('100%').show();// 300:计算排除的高度
			});
		}
	}, 300);
}
//网批快速搜索
function resQuickSearch(event){
	if (event.keyCode == 13) {
	commonSearch();
	}
}
function initTree(){
	$("#tree-div").html('<div><image src="'+_ctx+'/resources/images/common/loading.gif"/>加载数据...</div>');
	$.post(_ctx+"/res/res-approve-info-tree!buildTree.action",{active:'1',moduleTypeCdSrh:$('#srh_moduleTypeCd').val()}, function(result) {
		$("#tree-div").empty();
		if (result) {
			var arr=eval('('+result+')');
			root=arr;
			treePanel = new TreePanel({
				renderTo:'tree-div',
				'root' : root,
				'ctx':_ctx
			});
			treePanel.render();
			treePanel.isExpendSelect = true;
			treePanel.on(function(node){
				var tmp = node.attributes.entityCd;
				if("0" == tmp){
					$("#btnNewApply").attr("disabled","disabled");
					curAuthTypeCd='';
					$("#idCurAuthType").html('');
					return;
				}

					//模块
				if(node.attributes.nodeType == "module"){
					//liwei3 add
					//alert(node.attributes.text);
					if(node.attributes.text.indexOf("招标")>=0 || node.attributes.text.indexOf("定标")>=0){
						//alert(node.attributes.text);
						curModuleCd=node.attributes.entityCd;
						curModuleId=node.id;
						//alert(curModuleCd);
						//alert(curModuleId);
						var curModuleName=node.attributes.entityName;
						$('#idCurAuthType').html(curModuleName);
						$.post(_ctx+"/res/res-approve-info-tree!getResAuthTypeListByResModule.action",{orgId:node.id,moduleCd:curModuleCd},
							function(result) {
								if (result) {
									curAuthTypeCd = result;
									$.post(_ctx+"/res/res-approve-info!load.action",{
										resAuthTypeCd:curAuthTypeCd,  //
										resModuleCd:curModuleCd,
										listMode:"0",  //
										qsCondition:getValidValue($("#qsCondition").val()),
										approveCdSrh:getValidValue($("#approveCd").val()),
										selectRecoOpinion:getValidValue($("#selectRecoOpinion").val()),
										selectDutyOpinion:getValidValue($("#selectDutyOpinion").val()),
										selectDealOpinion:getValidValue($("#selectDealOpinion").val()),
										filter_LIKES_userName:getValidValue($("#filter_LIKES_userName").val()),
										filter_LIKES_approveUserCd:getValidValue($("#filter_LIKES_approveUserCd").val()),
										filter_GED_startDate:getValidValue($("#mainFormSearch_filter_GED_startDate").val()),
										filter_LTD_startDate:getValidValue($("#mainFormSearch_filter_LTD_startDate").val()),
										filter_LIKES_statusCd:getValidValue($("#filter_LIKES_statusCd").val()),
										filter_LIKES_approveUserName:getValidValue($("#filter_LIKES_approveUserName").val()),
										filter_LIKES_landproject:getValidValue($("#filter_LIKES_landproject").val()),
										filter_LIKES_titlename:getValidValue($("#filter_LIKES_titlename").val()),
										filter_LIKES_randomNo:getValidValue($("#filter_LIKES_randomNo").val()),
										moduleTypeCdSrh:getValidValue($('#srh_moduleTypeCd').val())
									},function(result1) {
										$("#content").html(result1);
										if (!isCanSend){
											//$("#btnNewApply").attr("disabled","disabled");
										}
										$('#srh_listMode').val(getValidValue($("#listMode").val()));
										resetLeftPanel();
									});
								}
						});
						
					}
					//else{
						if(node['html-element']['child'].innerHTML==''){
							node['html-element']['child'].id=node.id;
							loadNodeTree(node.id,node,tmp);
						}
						if(node.isExpand){
							node.collapse();
						}else{
							if(node['html-element']['child'].style.display=='block'){
								node.collapse();
							}else{
								node['html-element']['child'].style.display='block';
								node.isExpand = true;
								node.expand();
							}
						}
					//}
				}

					//分类
					if(node.attributes.nodeType == "authType"){
						var itemCd=node.attributes.entityCd;
						var resAuthName=node.attributes.entityName;
						$('#idCurAuthType').html(resAuthName);
						var isSearch=true;
						var isCanSend=true;
						curAuthTypeCd=itemCd;
						if (curAuthTypeCd=='QTSW_WJSP'){
							//文件审批发起权限
							var hasPermission=$('#flg_lwsx_p').val();
							hasPermission=eval(hasPermission);
							if (!hasPermission){
								$("#btnNewApply").attr("disabled","disabled");
								isCanSend=false;
								ymPrompt.alert({message:'没有发起权限！',title:"提示",width:220,height:180});
							}
						}else if (curAuthTypeCd.indexOf('JSGL_SYGCGZ')!=-1){
							//商品工程改造
							//$("#btnNewApply").attr("disabled","disabled");
							//isCanSend=false;
							//ymPrompt.alert({message:'很抱歉，此项表单已暂停使用，请使用其他流程表或纸面文件，如有不明请咨询总裁办企管部',title:"提示",width:400,height:180});
						}
						$('#resAuthName').val(resAuthName);
						if (isSearch){
							$.post(_ctx+"/res/res-approve-info!load.action",
							{
								resAuthTypeCd:curAuthTypeCd,  //
								listMode:"0",  //
								qsCondition:getValidValue($("#qsCondition").val()),
								approveCdSrh:getValidValue($("#approveCd").val()),
								selectRecoOpinion:getValidValue($("#selectRecoOpinion").val()),
								selectDutyOpinion:getValidValue($("#selectDutyOpinion").val()),
								selectDealOpinion:getValidValue($("#selectDealOpinion").val()),
								filter_LIKES_userName:getValidValue($("#filter_LIKES_userName").val()),
								filter_LIKES_approveUserCd:getValidValue($("#filter_LIKES_approveUserCd").val()),
								filter_GED_startDate:getValidValue($("#mainFormSearch_filter_GED_startDate").val()),
								filter_LTD_startDate:getValidValue($("#mainFormSearch_filter_LTD_startDate").val()),
								filter_LIKES_statusCd:getValidValue($("#filter_LIKES_statusCd").val()),
								filter_LIKES_approveUserName:getValidValue($("#filter_LIKES_approveUserName").val()),
								filter_LIKES_landproject:getValidValue($("#filter_LIKES_landproject").val()),
								filter_LIKES_titlename:getValidValue($("#filter_LIKES_titlename").val()),
								filter_LIKES_randomNo:getValidValue($("#filter_LIKES_randomNo").val()),
								moduleTypeCdSrh:getValidValue($('#srh_moduleTypeCd').val())
							},
							function(result) {
								$("#content").html(result);
								if (!isCanSend){
									$("#btnNewApply").attr("disabled","disabled");
								}
								$('#srh_listMode').val(getValidValue($("#listMode").val()));
								resetLeftPanel();
							});
						}
					}
					//liwei3 delete
					//else{
					//	$("#btnNewApply").attr("disabled","disabled");
					//	curAuthTypeCd='';
					//	$("#idCurAuthType").html('');
					//}
				
			});
			function getValidValue(value){
				if(typeof(value)=='undefined'){
					value='';
				}
				return value;
			}
			for(var k in treePanel.nodeHash){
				if (k !='0'){
					var node = treePanel.nodeHash[k];
					var tmp = node.attributes.entityCd;
					if(node['html-element']['child'].innerHTML==''){
						node['html-element']['child'].id=node.id;
						loadNodeTree(node.id,node,tmp);
					}
					node['html-element']['child'].style.display='block';
					node.isExpand = true;
					node.expand();
				}
			}
		}
	});
}
function loadNodeTree(orgId,node,moduleCd){
	var tNode ;
	$("#"+node.id).html('<div><image src="'+_ctx+'/resources/images/common/loading.gif"/>加载数据...</div>');
	$.post(_ctx+"/res/res-approve-info-tree!buildTree.action",{active:'1',orgId:orgId,moduleCd:moduleCd,moduleTypeCdSrh:$('#srh_moduleTypeCd').val()}, function(result) {
		if (result) {
			$("#"+orgId).empty();
			var arr=eval('('+result+')');
			tNode = arr.children;
			for(var i=0,j=tNode.length;i<j;i++){
				var p = new TreeNode(tNode[i]);
				node.appendChild(p);
			}
			node.paintChildren();
		}
	});
}
//------------------------网上审批优化异步加载树结构新加方法 end -----------