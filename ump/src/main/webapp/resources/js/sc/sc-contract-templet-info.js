//加载页面功能树123

var treePanel;
// 搜索定位表单
var curVal = null;
var curNode = null;
var searchTreeManager;

String.prototype.trim = function() {
	// 用正则表达式将前后空格
	// 用空字符串替代。
	var t = this.replace(/(^\s*)|(\s*$)/g, "");
	return t.replace(/(^　*)|(　*$)/g, "");
}


// 转化为JSON
function toJson(str) {
	if ("" == $.trim(str)) {
		return "";
	}
	return eval("(" + str + ")");
}
$(document).ready(function(){
	/**
	 * 创建标准合同
	 */
	$("#btnNewContract").click(function(){
		doCreateContract("1");
		
	});
	/**
	 * 创建非标
	 */
	$("#btnNewNonContract").click(function(){
		doCreateContract("0");
		
	});
	// 项目
	$('#projectName').orgSelect({
		muti:true,
		showProjectOrg : true,
		orgTreeType : '2'
			});
	$("#curUserName").userSelect({muti:false,nameField:'curUserName',cdField:'curUserCd'});
	
});

function doCreateContract(isStandard){
	
	var _templetId="";
	if("0" == isStandard ){
		_templetId=$("#scNonConTempletId").val();
	}else{
		_templetId=$("#scTempletId").val();
	}
	if(	$("#idCurTempletName").attr("isVaild") =="0"){
		ymPrompt.alert({
			message :"当前合同模板未启用，无法创建新合同！",
			title : "提示",
			handler : function(tp) {				
			}
		});
		return;
	}
	if(!isEmpty(_templetId)){
		var otherPara="";
		if($("#resNo").val().trim() != ""){
			otherPara="&resFields="+$("#resFileds").val()+"&resRela="+$("#resRela").val()+"&resNo="+$("#resNo").val()+"&ifNoContLeger="+$("#ifNoContLeger").val();			
		}
		var otherParam="";
		if("0" == isStandard){
			openWindow('scnonstandardTemplet', '非标合同',_ctx +"/sc/sc-contract-templet-info!createNonStandard.action?templetId="+_templetId+"&frameId="+$("#frameId").val()+otherPara);
		}else{
			openWindow('scTemplet', '标准合同',_ctx +"/sc/sc-contract-templet-info!toNewCreateContract.action?templetId="+_templetId+"&frameId="+$("#frameId").val()+otherPara);

		}
	}else if($("#idCurTempletName").text().trim()!=""){
		ymPrompt.alert({
			message :"无法创建合同,当前模板未上传模板文件或者模板文件未被启用！",
			title : "提示",
			handler : function(tp) {
				
			}
		});
		return;
	}else{
		ymPrompt.alert({
			message :"请先选择合同模板！",
			title : "提示",
			handler : function(tp) {
				
			}
		});
		return;
	}

	
}
// 快速搜索事件
function doQuickSeniorSearch() {

	if ($("#rightHeadTool").css("display") == "none") {
		$("#bt_search").addClass("quickSeniorHover");
		$("#rightHeadTool").show();
	} else {

		$("#bt_search").removeClass("quickSeniorHover");
		$("#rightHeadTool").hide();
	}
}

function searchMyReco(){
	$("#btnMyReco").css("color","red");
	$("#btnMyConTemlet").css("color","");
	$("#mainFormSearch").attr("action","sc-contract-templet-info!list.action");
	$("#pageNo").val("1");
	$("#sctempletTypeId").val("");
	$("#divStatusCd").val("");
	$("#idCurTempletName").html("");
	if(treePanel.focusNode!=undefined){
	// 取消左侧选中的模板类别
	treePanel.focusNode.unselect();
	}
	doGetContStatusList();
}

// 通过合同编号查询合同
function searchByContractNo() {
	if($("#contractNo").val()) {
		$("#conNo").val($("#contractNo").val());
		$("#mainFormSearch").attr("action","sc-contract-templet-info!list.action");
		$("#pageNo").val("1");
		$("#sctempletTypeId").val("");
		$("#divStatusCd").val("");
		$("#idCurTempletName").html("");
		if(treePanel.focusNode!=undefined){
		// 取消左侧选中的模板类别
		treePanel.focusNode.unselect();
		}
		doGetContStatusList();
	}
}

function doTempletManage(){
	openWindow('scTemplet', '合同模板',_ctx +"/sc/sc-contract-templet-type.action");
}



// 我的记录
function seleRecoOp(recoOpinion,index) {
	$("#divStatusCd").val(recoOpinion);
	$("#cost-nav input[type='button']").removeClass("btn_nav1_click");
	$("#btn_nav1_"+index).addClass("btn_nav1_click");
	jumpPage();
}
/**
 * 标准合同模板选中
 * 
 * @param scTempletId
 *            模板编号
 * @param scTempletName
 *            模板名称
 * @return
 */

function btnTempletClick(scTempletId,scTempletName,isVaild){
	
	$("#idCurTempletName").attr("scTempletId",scTempletId);
	$("#idCurTempletName").attr("isVaild",isVaild);
	$("#idCurTempletName").html(scTempletName);
}

function resetLeftPanel(){
	
	// 初始化左边高度
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
	
	if( rigHeight < baseMin){
		try{$("#leftPanel").height(baseMin-$("#searchApproveFix").height()-8);}catch(e){}
		try{$("#divTreeP").height(baseMin-$("#searchApproveFix").height()-8);}catch(e){}
	}else{
		try{$("#tableDiv").height(baseMin-$("#rightHeadTool").height()-$("#rightHeadQuick").height()-$("#rightHeadAdd").height()+93);}catch(e){}
		try{$("#leftPanel").height(baseMin-$("#searchApproveFix").height()-8);}catch(e){}
	}
	// important
	$("#flg_firstFlg").val("1");
	
	// 查看明细
	if( $("#detailPanelDiv").length == 1){
		$("#divAll").height(baseMin-5);
		$("#detailPanelDiv").height(baseMin- $("#funcPanelDiv").height() -30 );  
	} 
}
/**
 * 获限合同状态列表
 * 
 * @return
 */
function doGetContStatusList(){
	$.post(_ctx + "/sc/sc-contract-templet-info!constacdlist.action", {
		sctempletTypeId : $("#sctempletTypeId").val(),
		projectCd:$("#projectCd").val(),
		conName:$("#conName").val(),
		conNo:$("#conNo").val(),
		curUserCd:$("#curUserCd").val(),
		startDate:$("#startDate").val(),
		endDate:$("#endDate").val(),
		approveNo:$("#approveNo").val(),
		conStatusCd:$("#conStatusCd").val(),
		isSearchByStanCon:$("#isSearchByStanCon").val()
	}, function(result) {
		$("#cost-nav" ).html(result);
		var statusCdLi=$("li[isFirst=1]");
		var statusCd="";
		if(statusCdLi!=undefined && statusCdLi!=null && statusCdLi.length>0){			
			statusCd=statusCdLi.attr("id").replace("btn_nav1_","");
		}
		$("#divStatusCd").val(statusCd);
		jumpPage();
	});
	
	
}
//是否第一次搜索
var isFirstQuery=true;
// 通过表单搜索条件搜索列表
function jumpPage(pageNo) {
	if (typeof(pageNo) == 'undefined' || (pageNo == '')) {
		$("#pageNo").val(1);
	} else {
		$("#pageNo").val(pageNo);
	}
	TB_showMaskLayer("正在搜索...");
		$("#searchConInfoResultPanel").hide();
		$("#mainFormSearch").ajaxSubmit(function(result) {
					TB_removeMaskLayer();
					$("#searchResultPanel").html(result);
					
				var _nontempletId=$("#scNonConTempletId").val();
				
				//第一次搜索不允许创建合同，需选择中左侧树
				if(isFirstQuery){
					 $("#btnNewContract").hide();
					 $("#btnNewNonContract").hide();
					isFirstQuery=false;
					return;
				}
			    if(_nontempletId.trim()!=""){
			    	$("#btnNewNonContract").show();
				 }else{
					 
					 $("#btnNewNonContract").hide(); 
				 }
				var _templetId=$("#scTempletId").val();
				 $("#btnNewContract").attr("disabled",false);
				 if(_templetId.trim()!=""){
					 $("#btnNewContract").val("  创建标准合同"); 
					 $("#btnNewContract").show(); 
				 }else{
					 // 如果当前类别下没有既没标准又没非标合同模板则只显示创建合同按钮
					 if(_nontempletId.trim()==""){	
						 // 否则如果有非标合同，没有标准合同按钮则藏
						 $("#btnNewContract").show();
						 $("#btnNewContract").attr("disabled",true); 	
						 $("#btnNewContract").val("暂无合同模板"); 						 
					 }else{
						 // 否则如果有非标合同，没有标准合同按钮则藏
						 $("#btnNewContract").hide();
						 
					 }
				
				 }
					
					
					var _isStandard=$("#searchResultPanel").find("#scIsStandard").val();
					 $("#idCurTempletName").attr("scTempletId",$("#searchResultPanel").find("[id=scTempletId]").val());
			    	 $("#idCurTempletName").attr("isStandard",_isStandard);
			    	 
			    	 
			    	 $("#idCurTempletName").attr("isVaild",$("#searchResultPanel").find("[id=isVaild]").val());
					$("#operation").hide();
					$("#btnQuickSenior").removeClass("quickSeniorHover");
					$("#rightHeadTool").hide();
				});

	
	
	
}
// 跳转至给定的页面,配合前台的分页搜索
function jumpPageTo() {
	var index = $("#pageTo").val();
	var t = parseInt(index);
	if (t > 0) {
		jumpPage(t);
	}
}

// 通过表单搜索条件搜索列表
function searchBidLedger() {
	jumpPage(1);
}
function isEmpty(str){
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
}


// 判断是否为数字
function isNum(txt) {
	var _val = $.trim(txt);
	if ("" == _val || _val.length < 1) {

		return true;
	}

	var reg = /^(\d*[0-9]+)$/; // /^((\d*[1-9]*)|([0]))(\.\d+)?$/;
	var _isN = reg.test(_val);
	// alert('isNum' + _val + " " + txt+" _isN:"+_isN);
	return _isN;
}

// 返回val的字节长度
function getByteLen(val) {

	var len = 0;
	for (var i = 0; i < val.length; i++) {

		var intCode = val.charCodeAt(i);
		if (intCode >= 0 && intCode <= 128) {
			len = len + 1; // 非中文单个字符长度加 1
		} else {
			len = len + 2; // 中文字符长度则加 2
		}
	}
	return len;

}
/**
 * 判断是否超过最大的字符长度
 * 
 * @param {}
 *            val 字符串
 * @param {}
 *            maxLen 最大长度
 */
function isPassMaxLen(val, maxLen) {

	if (getByteLen(val) > maxLen) {
		return true;
	} else {
		return false;
	}
}



/**
 * 清空数据
 * 
 * @return
 */
function doClear(){
	// $("#bt_search").removeClass("quickSeniorHover");
	$("#projectCd").val("");
	$("#curUserCd").val("");
	// $("#rightHeadTool").hide();
	$("#mainFormSearch").get(0).reset();
}

// 快速搜索事件
function searchSenior(){
	if($("#rightHeadTool").css("display")=="none"){
		$("#btnQuickSenior").addClass("quickSeniorHover");
		$("#rightHeadTool").show();
	}else{
		$("#btnQuickSenior").removeClass("quickSeniorHover");
		$("#rightHeadTool").hide();
	}
}



function doSearch(){
	$("#contractNo").val($("#conNo").val());
	doGetContStatusList();
}

function resetSearchApproveInput(dom){
	if($(dom).val().trim() == ''){
		$(dom).val('搜索模板...');
		$(dom).css({color:"#cccccc"});
	}else{
		$(dom).css({color:"#5A5A5A"});
	}
}
function clearSearchApproveInput(dom){
	if( $(dom).val() == '搜索模板...'){
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
var userSearchMgr;
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
			$("#rightHeadQuick").show();
			return;
		} else {
			$("#" + wabTreeElemId).hide();
			$("#" + resultElemId).show();
			// $("#rightHeadQuick").hide();
			$(srcElem).css( {
				color : "#5A5A5A"
			});
			$("#" + resultElemId).addClass("waiting");
			$.post(_ctx + "/sc/sc-contract-templet-type!searchTreeList.action", {
				value : val
			}, function(result) {
				$("#" + resultElemId).html(result).removeClass("waiting").height(320).show();// 300:计算排除的高度
			});
		}
	}, 300);
}
var _isClose=true;// 合同类别编辑窗体是否已关闭
var _selNode=null;
function initTree(){
	$("#isSearchByStanCon").val("");
	$("#tree-div").html('<div><image src="'+_ctx+'/resources/images/common/loading.gif"/>加载数据...</div>');
	$.post(_ctx+"/sc/sc-contract-templet-type!buildTree.action",{active:'1',moduleName:'合同文本库',isStandard:""}, function(result) {
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
				var tmp = node.attributes.id;
				_selNode=node;
			$("#parentId").val(tmp);
				if("0" == tmp){					
					$("#idCurAuthType").html('');	
				
					return;
				}
				
				$("#sctempletTypeId").val(tmp);
				var standardCd=node.attributes.sexCd;
				$("#isstandard").val(standardCd=="1"?"1":"0");
		     if(node.attributes.nodeType != "root" ){		
		    	 	//获取包含当前节点下面的所有子节点ID
			        var allNodes=node.getAllChildren (node);
			        //把所有子点节点联接成402834e935f265680135f26c21440003,402834e935f265680135f2760d2f0004
			    	 var ids=allNodes.join(",");
		    	   $("#sctempletTypeId").val(ids);
		    	 	$("#idCurTempletName").html(node.attributes.text);
		    	 	$("#btnMyReco").css("color","");
		    		$("#btnMyConTemlet").css("color","");
		    		$("#mainFormSearch").attr("action","sc-contract-templet-info!list.action");
		    		$("#pageNo").val("1");
		    		doGetContStatusList();
		    	 
		     }
				
				
				// 模块
				if(node.attributes.children.length>0){
					if(node['html-element']['child'].innerHTML==''){
						node['html-element']['child'].id=node.id;
					
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
				}

				
			});		
		
			function getValidValue(value){
				if(typeof(value)=='undefined'){
					value='';
				}
				return value;
			} 
		}
		
		
		$(".TreePanel div span[title=合同文本库]").after("&nbsp;&nbsp;&nbsp;<span><input type='checkbox' onclick='doSearchByConType(this)'  id='standardCkb'/>标准合同</span>");
		// doGetContStatusList();
		//默认搜索所有合同
		$("#isSearchByStanCon").val("false");
		
	});

}
/**
 * 搜索合同模板
 * 
 * @param dom
 *            标准合同模板复选框
 * @return
 */
function doSearchByConType(dom){	
	var rootNode=treePanel.getNodeById("0");
	$("#isSearchByStanCon").val($(dom).attr("checked"));
	doGetContStatusList();
	loadNodeTree(rootNode,$(dom).attr("checked"));
	
}


/**
 * 搜索合同时点击合同模板类别搜索对应模板
 * 
 * @param templetTypeId
 * @param templetTypeName
 * @return
 */
function searchScTemplet(templetTypeId,templetTypeName){
	var _templetTypeName=templetTypeName.substring(templetTypeName.lastIndexOf('/')+1,templetTypeName.length);
	$("#idCurTempletName").attr("scTempletId","");
	$("#idCurTempletName").html(_templetTypeName);
	$("#idCurTempletName").attr("isVaild","");
	$("#sctempletTypeId").val(templetTypeId);
	$("#mainFormSearch").attr("action","sc-contract-templet-info!list.action");
	$("#pageNo").val("1");
	doGetContStatusList();
	
	
}

// 打开窗口
function openWindow(id, desc, url) {
	if (window.parent && window.parent.parent.TabUtils) {
		window.parent.parent.TabUtils.newTab(id, desc, url);
	} else {
		window.open(url);
	}
}

/**
 * 编辑自己创建的合同
 * 
 * @param contId
 * @return
 */
function btnLoadConInfo(contId,statusCd,isstandard){
   var _url=_ctx +"/sc/sc-contract-templet-info!readContract.action?scContId="+contId;
   var _title="标准合同";
   if(_isCurDel){// 正在删除时
	   
	   return;
   }
	switch(isstandard){
	case "0":
		// 非标准合同
		_title="非标合同";
		if(contId.trim()!="" && !_isCurDel){
				_url=_ctx +"/sc/sc-contract-templet-info!readNonStandardCon.action?scContId="+contId;
			
		}
		break;
	case "1":
		// 标准合同
		_title="标准合同";
		if(contId.trim()!="" && !_isCurDel){		
			if(statusCd=="30" || statusCd=="40"){
				_url=_ctx +"/sc/sc-contract-templet-info!mergeContract.action?scContId="+contId;
			}
		}
		break;
	 default:
			break;				
	}

	openWindow('scViewContract', _title,_url);

}

var _isCurDel=false;

function deleteTempletInfo(contId,creator){	
	_isCurDel=true;
	
	// 合同创建人可以删除合同文本信息
	// 有删除权限的人可以删除(0: 无删除权限，1: 有删除权限);
	if(creator!=$("#curUserId").val() || $("#isCanDel").val()=="0"){	
		
		ymPrompt.alert({
			message :"不允许删除当前合同！",
			title : "提示",
			handler : function(tp) {
			window.setTimeout("	_isCurDel=false;", 1000);
			}
		});
	
		return;
		
	}
	
	
	ymPrompt.params={};
	ymPrompt.params.contId=contId;

	ymPrompt.confirmInfo({message:'删除不可恢复,你确认要删除当前合同吗？',title:"合同删除",handler:function (tp){
	if (tp=='ok'){
		
		 TB_showMaskLayer("请稍等，正在删除合同...");

			var _url = _ctx + "/sc/sc-contract-templet-info!delete.action";
			var _scid=$("#id").val();
				$.post(_url, {
							id : ymPrompt.params.contId
						}, function(result) {
							if (result.indexOf("true") != -1) {
								doGetContStatusList();
							
							} else {
								TB_removeMaskLayer();
								if (result.indexOf("could not execute") > -1) {
									alert("当前合同不能被刪除！");
								} else {
									alert(result);
								}
							}
							_isCurDel=false;
								
						});
			
		
	}
	window.setTimeout("	_isCurDel=false;",500);
	return;
	
	}});
	
	/*
	 * if(!confirm("你确认要删除当前合同吗？")){ // 两秒钟后才允许用户删除合同，防止用户点击删除直接相看合同信息
	 * window.setTimeout(" _isCurDel=false;",500); return; }
	 */
}




/**
 * 异步加载节点
 * 
 * @param orgId
 * @param node
 * @param moduleCd
 * @return
 */
function loadNodeTree(node,isstandard){
	isFirstQuery=true;
	var tNode ;
	$("#"+node.id).html('<div><image src="'+_ctx+'/resources/images/common/loading.gif"/>加载数据...</div>');
	$.post(	_ctx+"/sc/sc-contract-templet-type!buildTree.action",{conTypePid:node.id,moduleName:node.attributes.text,isStandard:isstandard}, function(result) {
		if (result) {
			node.childNodes=[];

		
			$("#"+node.id).empty();
			var arr=eval('('+result+')');
			tNode = arr.children;
			node['html-element'].innerHTML=arr.text;
			node['html-element'].title=arr.text;
			
			node['html-element']['child'].innerHTML = '';
			for(var i=0,j=tNode.length;i<j;i++){
				var p = new TreeNode(tNode[i]);
				var index = node.childNodes.indexOf(p);
				if(index >-1){
					continue;
				}
				node.appendChild(p);
			}
			node.paintChildren();
			$("#"+node.id).prev().html(arr.text);
	
		}
	});
}
