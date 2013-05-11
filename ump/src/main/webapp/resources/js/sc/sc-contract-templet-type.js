//加载页面功能树

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
	$("#rightHeadTool").hide();
	$("#bt_addTempletType").bind("click",function(){
		if($("#parentId").val().trim()==""){
			alert("请先选择左侧合同模板类别！");
			return;
		}
		_isClose=false;// 当前已开打
		ymPrompt.win({
			title:'增加合同模板类别',
			fixPosition:true,
			width:350,
			height:280,
			allowRightMenu:true,
			showMask : false,
			message:"<div id='popEditDiv'><img align='absMiddle' src='"+_ctx+"/images/loading.gif'></div>",
			afterShow:function(){
				$.post(_ctx+"/sc/sc-contract-templet-type!edit.action",{id:id},function(result){
					$("#popEditDiv").html(result);
					$("#btTypeDel").val("关闭");			
					$("#btTypeDel").prev().hide();
					$("#eParentId").val($("#parentId").val());			
					
					$("#isstandard").attr("checked",$("#typeStandard").val()=="1"?true:false);
				});
			},
			handler : function(btn) {
				_isClose=true;
				ymPrompt.close();
			}
		});
		return;
	});
});
function searchMyReco(){
	$(".btn_green").hide();
	$("#rightHeadAdd").hide();
	$("#btnMyReco").css("color","red");
	$("#btnMyConTemlet").css("color","");
	$("#mainFormSearch").attr("action","sc-contract-templet-info!list.action");
	$("#pageNo").val("1");
	
	// TB_showMaskLayer("正在搜索...",5000);
	jumpPage();
}

function searchMyCon(){
	$(".btn_green").show();
	$("#rightHeadAdd").show();
	$("#btnMyReco").css("color","");
	$("#btnMyConTemlet").css("color","red");
	
	$("#mainFormSearch").attr("action","sc-contract-templet!loadList.action");
	$("#pageNo").val("1");
	
	jumpPage();
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
	
	// alert("rigHeight: " + rigHeight + ",baseMin:" + baseMin
	// +",docHeight:"+docHeight);
	
	if( rigHeight < baseMin){
		$("#leftPanel").height(baseMin-$("#searchApproveFix").height()-8);
		$("#divTreeP").height(baseMin-$("#searchApproveFix").height()-8);
		$("#tableDiv").height(baseMin-$("#rightHeadTool").height()-$("#rightHeadQuick").height()-$("#rightHeadAdd").height()+93);  
	}else{
		$("#tableDiv").height(baseMin-$("#rightHeadTool").height()-$("#rightHeadQuick").height()-$("#rightHeadAdd").height()+93);  
		$("#leftPanel").height(baseMin-$("#searchApproveFix").height()-8);
		$("#divTreeP").height(baseMin-$("#searchApproveFix").height()-8);
	}
	// important
	$("#flg_firstFlg").val("1");
	
	// 查看明细
	if( $("#detailPanelDiv").length == 1){
		$("#divAll").height(baseMin-5);
		$("#detailPanelDiv").height(baseMin- $("#funcPanelDiv").height() -30 );  
	} 
}
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
				
				if(	$("#divisstandard").val()!="1"){
					
					$("#scloadTempletName").html("合同名称");
					$("#scloadTempletName").attr("title","合同名称");
				}
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



/**
 * 获取合同分类
 * 
 * @return
 */
function doGetContTypeList(contType){
	
	$.post(_ctx + "/sc/sc-contract-templet!constacdlist.action", {
		sctempletTypeId : $("#sctempletTypeId").val()		
	}, function(result) {
		if(!isEmpty(contType) && contType == "select"){
			
			$("#cost-nav" ).html(result);
			// $("#cost-nav" ).hide();
		}else{
			$("#cost-nav" ).html(result);
		}
		var conType=$("li[isFirst=1]");
		var typeCd="";
		if(conType!=undefined && conType!=null && conType.length>0){
			
			typeCd=conType.attr("id").replace("btn_nav1_","");
		}
		
		$("#divisstandard").val(typeCd);
		$("#pStandard").val(typeCd);
		jumpPage();
	});
	
	
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

// 快速搜索事件
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


/**
 * 点击合同分类
 * 
 * @param coOpinion
 * @return
 */
function seleConTypeOp(coOpinion){
	$("#divisstandard").val(coOpinion);
	$("#pStandard").val(coOpinion);
	$("#cost-nav li").removeClass("cost-nav-click");
	$("#btn_nav1_"+coOpinion).addClass("cost-nav-click");
	jumpPage();
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
			$("#rightHeadQuick").hide();
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
	$("#tree-div").html('<div><image src="'+_ctx+'/resources/images/common/loading.gif"/>加载数据...</div>');
	$.post(_ctx+"/sc/sc-contract-templet-type!buildTree.action",{active:'1',moduleName:'合同模板库'}, function(result) {
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
				// 合同类型（标准｜非标)
				$("#pStandard").val(node.attributes.sexCd);
				$("#typeStandard").val(node.attributes.sexCd);
		    	$("#parentId").val(tmp);
				if("0" == tmp){					
					$("#idCurAuthType").html('');
					$("#sctempletTypeId").val("");
					var standardCd=node.attributes.sexCd;
					$("#isstandard").val("");
					$("#pageNo").val("1");
					doGetContTypeList();
					return;
				}
				if("0" == node.attributes.entityId){
					$("#typeId").val(tmp);
				}
				$("#sctempletTypeId").val(tmp);
		     if(node.attributes.nodeType != "root" ){
		    	 $("#idCurTempletName").attr("scTempletId","");
		    	 $("#idCurTempletName").html("");
		    	 $("#idCurTempletName").attr("isVaild","");
		    	 doGetContTypeList("select");
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
			treePanel.delegateOnDblClick = function(node){
				if(node.attributes.nodeType != "root" ){
					// curNode = node;
					var id = node.attributes.id;
					//_isClose=false;// 当前已开打
					openModuleType(id,node);
				}
			};
			function getValidValue(value){
				if(typeof(value)=='undefined'){
					value='';
				}
				return value;
			} 
		}
		doGetContTypeList();
	});
}

/**
 * 异步加载节点
 * @param orgId
 * @param node
 * @param moduleCd
 * @return
 */
function loadNodeTree(node){

	var tNode ;
	$("#"+node.id).html('<div><image src="'+_ctx+'/resources/images/common/loading.gif"/>加载数据...</div>');
	$.post(	_ctx+"/sc/sc-contract-templet-type!buildTree.action",{conTypePid:node.id,moduleName:node.attributes.text}, function(result) {
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
/**
 * 打开模板类型编辑页面
 * @return
 */
function modTempletType(){
	
	if(_selNode==null || _selNode==undefined){
		
		alert("请先选择需要编辑的模板类别！");
		return;
	}else{
		
		// curNode = node;
		var id = _selNode.attributes.id;
		//_isClose=false;// 当前已开打
		openModuleType(id,_selNode);
	}
	
}
/**
 * 修改合同模板
 * 
 * @return
 */
function openModuleType(tmpTypeId,node){
	var id = $("#typeId").val();
	if(typeof(tmpTypeId)!="undefined"){
		id=tmpTypeId;
	}
	if(id == "0"){
		
		alert("合同文本模板库根节点不允许编辑！");
		return;
	}
	ymPrompt.win({
		title:'编辑模板类型',
		fixPosition:true,
		width:350,
		height:280,
		allowRightMenu:true,
		showMask :true,
		useSlide : true,
		message:"<div id='popEditDiv'><img align='absMiddle' src='"+_ctx+"/images/loading.gif'></div>",
		afterShow:function(){
			$.post(_ctx+"/sc/sc-contract-templet-type!edit.action",{id:id},function(result){
				$("#popEditDiv").html(result);							
				//$("#eModuleTypeCd").val(node.attributes.extParam);		
				$("#btTypeDel").css("background","#5B0C0C");
				});
		},
		handler : function(btn) {
			//_isClose=true;
			ymPrompt.close();
		}
	});
}

/**
 * 保存|修改的合同模板类别
 * 
 * @return
 */

function saveModulePop(){
	
	$("#popModuleForm input[type=text]").each(function(i,sinput){
		$(sinput).val($(sinput).val().trim());
	});

	if ($("#eTypeName").val().trim()=="") {

		alert("类别名称不能为空！");
		$("#eTypeName").focus();
		return;
	}	
	if($("#eModuleTypeCd").val().trim() ==""){
		
		//alert("请选择模块类别！");
		//return;
	}
	TB_showMaskLayer("请稍等，正在更新模板类别...");
	$("#popModuleForm").ajaxSubmit(function(result) {
		var _rJson = toJson(result);
				if (_rJson != undefined && _rJson.status != undefined) {
					if (_rJson.status != true) {					
							alert(_rJson.errorMsg);
					} else {
		                	//添加刷新当前节点
		                	loadNodeTree(treePanel.focusNode);
					    	ymPrompt.close();
							_isClose=true;
					}
				} else {
					alert("无法解析返回的信息！");
				}
				
				TB_removeMaskLayer();
			});
}
/**
 * 删除合同模板类别
 * 
 * @param scid
 *            id号
 * @return
 */
function delModule(scid){
	if($("#btTypeDel").val()=="关闭"){
		
		_isClose=true;
		ymPrompt.close();
		return;
	}
	if(window.confirm("删除后对应的子合同模板将一并删除\n你确认要删除当前合同模板类别吗？")){
		var _url = _ctx + "/sc/sc-contract-templet-type!delete.action";
	    var _scid=$("#id").val();
		$.post(_url, {
					id : _scid
				}, function(result) {
					
					var _resultJson = toJson(result);

					if (_resultJson != undefined && _resultJson.status != undefined) {

						if (_resultJson.status != true) {
							alert("当前类别已被使用，无法删除！");
						
						}else{
							TB_removeMaskLayer();			
							ymPrompt.close();
							window.location.reload();parentNode
							//修改刷新父节点
		                	//loadNodeTree(treePanel.focusNode.parentNode);
							_isClose=true;
						  return;
						}
					} else {
						alert("无法解析返回的信息！");
					}
					TB_removeMaskLayer();
					
					
				});
	}
	

}


/**
 * 上传附件的onClick事件
 * 
 * @param titileName
 *            弹出下载框的标题
 * @param bizEntityId
 * @param bizModuleCd
 *            sctemplet
 * @param id
 * @param domId
 */
function showUploadSingleAttachDialog(titileName,actType, bizModuleCd) {
	if(actType!="mod"){	
			if(_selNode==null || _selNode==undefined){
			alert("请先选择左侧标准合同模板分类节点！");
		   return;	
		}
		if(_selNode.attributes.id=="0"){
			
			alert("根目录下不允许增加标准合同模板！");
			   return;	
		}
	}
	
	 var _templetTr=$("tr[isSel=true]");
	 if(_templetTr!=null && _templetTr!=undefined && _templetTr.length>0){		 
		 $("#pStandard").val( $(_templetTr).attr("isstandard"));
	 }
	 $("#temSequenceNo").val($(_templetTr).attr("seq"));
	 titileName="增加合同模板";
	 if(actType=="mod"){
		 titileName="编辑合同模板";
		 
	 }
	ymPrompt
			.confirmInfo( {
				icoCls : "",
				title : titileName,
				message : "<div id='singleAttachDiv'><img align='absMiddle' src='"
						+ _ctx + "/images/loading.gif'></div>",
				useSlide : true,
				winPos : "c",
				width : 450,
				height : 430,
				maxBtn : false,
				allowRightMenu : true,
				afterShow : function() {
					var url = _ctx+ "/sc/sc-contract-templet!input.action";
					var data = {					
						id : bizModuleCd,					
						bizEntityName:'scContractTemplet'
					};
					$.post(url, data, function(result) {
						$("#singleAttachDiv").html(result);	
						$($(".ym-btn .btnStyle")[0]).before("<span id='resultMessage' style='color:red'></span>");
						$($(".ym-btn .btnStyle")[1]).before("<input type='button' value='添加附件' class='btnStyle handler' style='cursor:pointer' onclick='addNewFile()'>&nbsp;&nbsp;");
						 if(actType!="mod"){	
						 $("#isvalid").attr("checked",true);
						 $("#iscurversion").attr("checked",true);													 
						 $("#isstandard").attr("checked",true);
						 $("#templetTypeId").val($("#sctempletTypeId").val());
							
						 }else{
							 $("#isstandard").parent().parent().hide();
						 }
						  
					});
				},
				handler : doSingleUpload,
				autoClose : false
			});
}
function doSingleUpload(btn) {
$("#attachSingleForm input[type=text]").each(function(i,sinput){
	$(sinput).val($(sinput).val().trim());
});
	// 确定
	if (btn == 'ok') {
		var fileName = $("#attachSingleForm_uploadInput").val();
		
		 if(!isNum($("#temSequenceNo").val().trim()) || isEmpty($("#temSequenceNo").val().trim())){
				
				alert("请确序号[由数字组成])是否合法且不能为空！");
				
				$("#temSequenceNo").focus();
				return;
			}

			if ($("#templetName").val().trim()=="") {

				alert("模板名称不能为空！");
				$("#templetName").focus();
				return;
			}	
			if (!fileName && isEmpty($("#templetId").val().trim())) {
				alert("请选择待上传的文件!");
				return;
			}else{
				   if(!isEmpty(fileName)){					   
					   var ext=getFileExt(fileName).toLowerCase();
					if(ext!="txt" && ext!="text" && ext!="htm" && ext!="html" &&  $("#isstandard").attr("checked")==true){
						alert("选择的模板格式不正确，请重新选择！\n允许的文件格式[.txt|.text|.htm|.html] ");
					   return;
				    }
				  }
				
			}
			if($("#iscurversion").attr("checked")==true && $("#isvalid").attr("checked")==false){
				
				if(!confirm("只有设置启用后才能设置成当前版本!\n是否要启用?")){					
					return;
				}
				$("#isvalid").attr("checked",true);
			}
			if($("#iscurversion").attr("checked")==false && $("#isvalid").attr("checked")==true){
				if(!confirm("只有设置成当前版本才能启用！\n是设置成当前版本?")){
				
					return;
				}
				$("#iscurversion").attr("checked",true);
			}
		
		$("#attachSingleForm").ajaxSubmit(function(result) {
			if (result.indexOf("true") != -1) {
				//jumpPage($("#pageTo").val());
				doGetContTypeList();
			
			} else {
				alert(result);
			}
		});
	}
	// 取消
	else if (btn == 'cancel') {

	} else {

	}
	ymPrompt.close();
}


function getFileExt(path){
    return path.substring(path.lastIndexOf(".")+1);
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

function deleteTemplet(scTempletId){
	if(!confirm("你确认要删除吗？")){
		return;
	}
	var _url = _ctx + "/sc/sc-contract-templet!delete.action";
	var _scid=$("#id").val();
		$.post(_url, {
					id : scTempletId
				}, function(result) {
					if (result.indexOf("true") != -1) {
						$("#idCurTempletName").html("");
						$("#idCurTempletName").attr("sctempletid","");
						doGetContTypeList("select");
					} else {
						alert("当前合同模板已被使用，无法删除！");
					}
				});
	
}

/**
 * 搜索合同时点击合同模板类别搜索对应模板
 * 
 * @param templetTypeId
 * @param templetTypeName
 * @return
 */
function searchScTemplet(templetTypeId,templetTypeName){
	$("#idCurTempletName").attr("scTempletId","");
	$("#idCurTempletName").html("");
	$("#idCurTempletName").attr("isVaild","");
	$("#sctempletTypeId").val(templetTypeId);
	doGetContTypeList();
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
 * 删除自己创建的合同
 * 
 * @param contId
 * @return
 */
function btnLoadConInfo(contId){
	if(contId.trim()!="" && !_isCurDel){
		openWindow('scViewContract', '编辑合同',_ctx +"/sc/sc-contract-templet-info!readContract.action?scContId="+contId);
	}
}

var _isCurDel=false;

function deleteTempletInfo(contId){
	_isCurDel=true;
if(!confirm("你确认要删除当前合同吗？")){
	_isCurDel=false;
		return;
	}
TB_showMaskLayer("请稍等，正在删除合同...");

	var _url = _ctx + "/sc/sc-contract-templet-info!delete.action";
	var _scid=$("#id").val();
		$.post(_url, {
					id : contId
				}, function(result) {
					if (result.indexOf("true") != -1) {
						doGetContTypeList();
					
					} else {
						alert(result);
					}
					_isCurDel=false;
					TB_removeMaskLayer();	
				});
	
}