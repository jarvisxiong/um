<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" import="com.hhz.uums.utils.DictContants;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>机构审核(HR)</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common/treePanel.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js" ></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.quickSearch.js"></script>
	
	<script type="text/javascript" src="${ctx}/js/common/treePanel.js"></script>
	<style>
	.scronll{displasy:block;}
	</style>
</head>
<body class="easyui-layout">
	<div region="west" title='组织视图' split="true"  style="width:200px;">
		<div id="searchApproveFix" style="border-bottom:1px solid #BFBFBF;">
			<table border="0" cellpadding="0" cellspacing="0" style="width:100%;">
				<tr>
					<td>
						<input  value="搜索机构..." 
								type="text" 
								style="padding:2px;border:0;font-size: 12px;color: #CCCCCC;width:100%;"
								onkeyup="searchTreeNode(this)"
								onblur="resetSearchApproveInput(this);"
								onclick="clearSearchApproveInput(this);"
								id="inputSearchVal"
						/>
					</td>
					<td style="width:56px;">
						<div id="inputSearchOperate" class="searchNextNoActive" onclick="searchTreeNode(document.getElementById('inputSearchVal'))">&nbsp;</div>
					</td>
				</tr>
			</table>
		</div>
		<div id="orgTreePanel" style="min-height:100px;padding-top:10px;">
		
		</div>
	</div>
	
	<input type="hidden" id="orgId" name="orgId"/>
	<input type="hidden" id="currentType" name="currentType" value="sysPosList"/>
	<div class="easyui-panel" id="centerRegion" region="center"   split="true" style="padding:5px;+position:relative;overflow-x:hidden">
		<!-- <div style="padding:5px 10px;">
			快捷搜索机构: 
			<input style="width:150px;" type="text" id="quickSearchField" name="quickSearchField" value="${quickSearchField}" title="模糊检索机构业务编号或机构名称(所有机构)"/>
			<input type="hidden" id="quickSearchFieldId" name="quickSearchFieldId" value="${quickSearchFieldId}"/>
			<input type="button" onclick="cleanSearchField()" value="清空"></input>
		</div> -->
		<div class="panel-header" ><div id="currentOrg" ></div></div>
		<div id="tabs" class="tabs-container" style="width:auto;height:auto;">
			<div class="tabs-header">
				<div class="tabs-wrap">
					<ul class="tabs">
						<li id="sysPosList">
							<a  class="tabs-inner tabs-title" href="#" onclick="switchView('','','sysPosList')" style="">机构审核员列表</a>
						</li>
						<li id="physicalTree">
							<!-- 物理系统职位树 -->
							<a  class="tabs-inner tabs-title" href="#" onclick="switchView('','','physicalTree')" style="">物理系统职位视图</a>
						</li>
						<li id="logicalTree">
							<a  class="tabs-inner tabs-title" href="#" onclick="switchView('','','logicalTree')" style="">逻辑系统职位视图</a>
						</li>
						<li style="border:0px;">
							<a id="btnSaveBatchSysPos"  href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="showPopConfirm()">保存</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="tabs-panels">
				<div class="panel">
					<div id="rightContainer" style="border:1px solid #99BBE8;width:100%;min-height:80%" >
						<!-- 系统职位列表 -->
						<div id ="rightDataGrid" >
							<div style="line-height: 30px;">
								<span style="margin-left:5px">添加机构审核职位:</span> 
								<input style="width:150px;" type="text" id="quickSearchSysPos" name="quickSearchSysPos" onchange="copyuiid(this.value);" value="${quickSearchSysPos}" title="模糊检索系统职位业务编号或名称或相应uiid(所有)"/>
								<input type="hidden" id="quickSearchSysPosId" name="quickSearchSysPosId" value="${quickSearchSysPosId}" />
								<input type="hidden" id="copyuiid" name="copyuiid" />
								<span style="color:red;">(请选择职位添加)</span>
								
								<!-- 操作提示 -->
								<span id="span_savePosTip" style="color:red;"></span>
							</div>
							<div style="min-height:430px;"><table id="tt" fit="true"	title="系统职位列表" singleSelect="true" rownumbers="true"  ></table>
							</div>
						</div>
						<div id="rightTreePanel" style="">
							
							<div id="rightTree" ></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	 
 	<div id="wSaveConfirm" title="职位变化如下:" class="easyui-window" closed="true" style="top:20px;width:350px;height:500px;padding:5px 0;background: #fafafa;">
		<div style="width: 100%;text-align: left;">
			<input type="button" style="cursor:pointer" class="buttom" onclick="saveBatchSysPosRel()" value="确定" />
			<input type="button" style="cursor:pointer" class="buttom" onclick="closePopConfirm()" value="取消" />
		</div>
		<div id="confirmDetailDiv">
			
		</div>
	</div>
<script type="text/javascript">

$(function(){
	reloadOrgTree();
	//快速搜索(模糊匹配:orgBizCd,orgName,orgMgrId)
	$('#quickSearchField').quickSearch(
		'${ctx}/plas/plas-org-check-rel!quickSearchOrgList.action',
		['orgName','orgMgrId','activeFlgName','centerOrgName','orgBizCd'],
		{plasOrgId:'quickSearchFieldId',orgName:'quickSearchField'},
		'',
		function(result){
			var orgId = result.attr('plasOrgId');
			var orgName = result.attr('orgName');
			searchTreeNodeById(orgId);
			switchView(orgId,orgName,'');
		}
	);
	//快速授予系统职位管理权限(模糊匹配:sysPosName,sysPosCd,uiid)
	$('#quickSearchSysPos').quickSearch(
		'${ctx}/plas/plas-sys-position!quickSearchSysPosList.action',
		['userName','sysPosName','orgName','orgCd','activeFlgName','centerOrgName','sysPosCd'],
		{sysPosCd:'quickSearchSysPosId',sysPosName:'quickSearchSysPos'},
		'',
		function(result){ 
			appendSysPos(result.attr('sysPosCd'),result.attr('sysPosName'),result.attr('orgName'),result.attr('orgCd'));
		},
		{'sysPosCd':'quickSearchSysPos','uiid':'copyuiid'}
	);
	$("#wSaveConfirm").window({
		onOpen:function(){
			$('body').mask();
		},
		onClose:function(){
			$('body').unmask();
		}
	});
	//初始化管理员列表
	switchView();
	$('#tabs').hide();
	
});
function copyuiid(uiid){
	$('#copyuiid').val(uiid);
}
//清空输入框
function cleanSearchField(){
	$('#quickSearchField').val('');
	$('#quickSearchFieldId').val('');
}
function setCurrentOrg(orgId,orgName){
	if(orgId){gOrgId = orgId;	}
	if(gOrgId){
		if(orgName){
			$('#currentOrg').empty();
			$('#currentOrg').append('当前机构:'+orgName);
		}
	}else{
		$('#currentOrg').empty();
		$('#currentOrg').append('当前机构:');
	}

}
function getCurrentType(){
	return $('#currentType').val();
}
function setCurrentType(type){
	var otype;
	if(type){
		 $('#currentType').val(type);
	}
	 otype=getCurrentType();
	//渲染
		
		
	$('.tabs-selected').removeClass();
	switch(otype){
	 case 'sysPosList':$("#btnSaveBatchSysPos").hide();$('#sysPosList').addClass('tabs-selected');$('#rightDataGrid').show();$('#rightTreePanel').hide();break;
	 case 'physicalTree':$("#btnSaveBatchSysPos").show();$('#physicalTree').addClass('tabs-selected');$('#rightTreePanel').show();$('#rightDataGrid').hide();break;
	 case 'logicalTree':$("#btnSaveBatchSysPos").show();$('#logicalTree').addClass('tabs-selected');$('#rightTreePanel').show();$('#rightDataGrid').hide();break;
		default:{$("#btnSaveBatchSysPos").show();$('#sysPosList').addClass('tabs-selected');$('#rightDataGrid').show();$('#rightTreePanel').hide();}
	}
}
function switchView(orgId,orgName,type){
	$('#tabs').show();
	setCurrentType(type);
	setCurrentOrg(orgId,orgName);
	var otype = getCurrentType();
	switch(otype){
		case	'sysPosList':loadSysPosList(gOrgId);break;
		case	'physicalTree':loadOrgPosTree(gOrgId,'physicalTree');break;
		case	'logicalTree':loadOrgPosTree(gOrgId,'logicalTree');break;
		default:{loadSysPosList(gOrgId);}
	}
	
}
//选中的树节点
var gOrgId;
//机构树
var gOrgTree;
function reloadOrgTree(){
	$('#orgTreePanel').html('<div style="height:60px;">&nbsp;</div>').mask('正在载入机构树,请稍候...');
	var url = '${ctx}/plas/plas-org!loadOrgTreePhysical.action';
	$.post(url, function(result) {
		$("#orgTreePanel").html('').unmask();
		if (result) {
			gOrgTree = new TreePanel({
				renderTo: "orgTreePanel",
				'root'  : eval('('+result+')'),
				'ctx'	:'${ctx}'
			}); 
			//自定义
			gOrgTree.isDelegateIcon = true;
			gOrgTree.delegateGetDelegateIcon = function(node){
				return node.iconPath;
			};
			gOrgTree.delegateOnMouseOverNode = function(node){
				return '';
			};
			//修饰所有节点
			for(var k in gOrgTree.nodeHash){
				var node = gOrgTree.nodeHash[k];
				var nodeType = node.attributes.orgOrUser;
				if( nodeType == "1"){
					if(node.isLeaf()){
						node.iconPath = _ctx + "/images/imgTree/page.gif";
					}else{
						node.iconPath = _ctx + "/images/imgTree/folder.gif"; 
					}
				}
			}
			gOrgTree.render(); 
			//单击事件
			gOrgTree.on(function(node){
				
				var orgId = node.attributes.entityId;
				var orgName = node.attributes.entityName;
				if('0' == gOrgId){
					$('#rightContainer').empty();
					return;
				}
				var nodeType = node.attributes.nodeType;
				//机构
				if( nodeType == '1'){
					if(node.isExpand){
						node.collapse();
					}else{
						node.expand();
					}
					switchView(orgId,orgName,'');
				}
			});
		}
	});
}
	
//加载机构职位树
var gOrgPosTree;
function loadOrgPosTree(orgId,type){
	$('#btnSaveBatchSysPos').linkbutton('disable');
	if(orgId){
		gOrgId = orgId;
	}else{
		orgId = gOrgId;
	}
	var url = '';
	switch(type){
		case 'physicalTree': url = '${ctx}/plas/plas-org-check-rel!loadOrgCheckPhysicalTree.action';break;
		case 'logicalTree': url = '${ctx}/plas/plas-org-check-rel!loadOrgCheckLogicalTree.action';break;
		default: {
			alert('类型不对!');
			return;
		}
	}
	$("#rightTree").html('<div style="height:60px;width:100%;">&nbsp;</div>').mask('正在加载系统职位树,请稍候...');
	$.post(url,{orgId:orgId}, function(result) {
		$("#rightTree").html('');
		if (result) {
			gOrgPosTree = new TreePanel({
				renderTo: "rightTree",
				'root'  : eval('('+result+')'),
				'ctx'	:'${ctx}'
			}); 
			//自定义
			gOrgPosTree.isDelegateIcon = true;
			gOrgPosTree.delegateGetDelegateIcon = function(node){
				return node.iconPath;
			};

			//修饰所有节点
			for(var k in gOrgPosTree.nodeHash){
				var node = gOrgPosTree.nodeHash[k];
				var nodeType = node.attributes.nodeType;
				if( nodeType== "3"){
					node.iconPath = _ctx + "/images/webim/male_online.jpg"; 
					
				}
				if( nodeType== "1" && node.isLeaf()){
					node.iconPath = _ctx + "/images/imgTree/page.gif";
				}
			}
			gOrgPosTree.render(); 
			//单击事件
			gOrgPosTree.on(function(node){
				var id  = node.id;
				var parentId = node.parentNode.id;
				if( $.trim(id) == '' || $.trim(id)=='0'){
					return;
				}
				
				var nodeType = node.attributes.nodeType;
				if( nodeType == "1"){//系统职位
					if(node.isExpand){
						node.collapse();
					}else{
						node.expand();
					}
				}
			});
			//treePanel动态生成,只能在这里赋值height:400px;overflow-y:auto;+position: relative;
	 		$('#rightTree').find('.TreePanel').css({"height":"400px", "overflow-y": "auto", "+position":"relative"});
			$("#btnSaveBatchSysPos").linkbutton('enable');
			$('#rigthTree').addClass('scronll');
		}
	});
}

//保存配置
function showPopConfirm(){
	var orgId 	   = gOrgId;
	var addDels    = getAddDeleteIds();
	var addMenuIds = addDels[0];
	var delMenuIds = addDels[1];
	if((addMenuIds == '') && (delMenuIds == '') ){
		alert("未发生变化!");
		return;
	}
	$("#confirmDetailDiv").html(getPopDiv());
	$("#wSaveConfirm").window('open');
}

//批量保存授权系统职位信息
function saveBatchSysPosRel(){
	$('#btnSaveBatchSysPos').linkbutton('disable');
	var orgId = gOrgId;
	var addDels = getAddDeleteIds();
	var addPosAOrgIds = addDels[0];
	var delPosAOrgIds = addDels[1];
	
	if((addPosAOrgIds == '') && (delPosAOrgIds == '') ){
		$.messager.alert('提示',"未发生变化!");
		return;
	}

	$("#rightTree").html('<span><image src="${ctx}/images/loading.gif"/>保存设置中...</span>');
	var url='${ctx}/plas/plas-org-check-rel!saveBatch.action';
	var data = {orgId: orgId, addPosAOrgIds: addPosAOrgIds,delPosAOrgIds: delPosAOrgIds}
	$.post(url, data, function(result) {
		if(result == 'success'){
			closePopConfirm();
			loadOrgPosTree(orgId,getCurrentType());
		}else{
			$.messager.alert('提示','授权失败!');
		}
	});
}

//获取授权或收回的用户IDs
function getAddDeleteIds(){
	var modifyNodes = gOrgPosTree.getModifiedLeafNodes('3');//0-系统职位
	var addIds   = modifyNodes[0];
	var delIds 	 = modifyNodes[2];

	var strAddIds = "";
	var strDelIds = "";
	
	for(var i=0; i<addIds.length; i++){
		if(i>0){
			strAddIds +=',';
		}
		strAddIds += addIds[i];
	}

	for(var i=0; i<delIds.length; i++){
		if(i>0){
			strDelIds +=',';
		}
		strDelIds += delIds[i];
	}
	return [strAddIds,strDelIds];
}	
//关闭
function closePopConfirm(){
	$('#wSaveConfirm').window('close');
}

//返回html片段
function getPopDiv(){
	var modifyNodes= gOrgPosTree.getModifiedLeafNodes('3');
	var addIds   = modifyNodes[0];
	var addTexts = modifyNodes[1];
	var delIds 	 = modifyNodes[2];
	var delTexts = modifyNodes[3];

	//新增部分
	var addDiv   = new Array();
		addDiv.push("<table style='width:150px;'>");
	for(var i=0; i<addIds.length; i++){
		addDiv.push("<tr><td valign='top' id='"+addIds[i]+"'>"+addTexts[i]+"<span class='x_close' style='float:right;'></span></td></tr>");
	}
		addDiv.push("</table>");

	//删除部分
	var delDiv  = new Array();
		delDiv.push("<table style='width:150px;'>");
	for(var i=0; i<delIds.length; i++){
		delDiv.push("<tr><td valign='top' id='"+delIds[i]+"'>"+delTexts[i]+"<span class='x_close' style='float:right;'></span></td></tr>");
	}
		delDiv.push("</table>");

	//弹出框
	var popDiv = new Array();
	popDiv.push("<table style='width:300px;border:1px solid #D2E0F2;' >");
	popDiv.push("	<tr class='panel-header'><th style='text-align:center;'>新增清单</th><th style='text-align:center;'>收回清单</th></tr>" );
	popDiv.push("	<tr><td valign='top'><div>"+ addDiv.join("")+"</div></td><td valign='top'><div>"+ delDiv.join("")+"</div></td></tr>");
	popDiv.push("</table>");

	return popDiv.join("");
}
function loadSysPosList(orgId){
	var url;
	if(orgId){
		url = '${ctx}/plas/plas-org-check-rel!loadOrgCheckList.action?orgId='+orgId;
	}else {
		url = '${ctx}/plas/plas-org-check-rel!loadOrgCheckList.action';
	}
	$('#rightTree').html('');
	$('#tt').datagrid({
		url:url,
		columns:[[
			{field:'sysPosName',title:'系统职位',sortable:true, width:100 },
			{field:'orgName',title:'上级机构名称',sortable: true, width:100 },
			{field:'sysPosCd',title:'系统职位编号',sortable: true, align: 'center', width:200 },
			{field:'action',title:'操作',width:50,sortable: true, align:'center',
				formatter:function(value,row,index){
					var orgId = row.orgId;
					var posId = row.plasSysPositionId;
					var d = '<a href="#" class="easyui-linkbutton" onclick=deleterow('+index+',"'+orgId+'","'+posId+'") >收回</a>';
					return d;
				}
			}
		]]
	});
}
function deleterow(index,orgId,posId){
	$('#span_savePosTip').hide();
	$.messager.confirm('确认','收回管理员吗?',function(r){
		if (r){
			$('#tt').datagrid('deleteRow', index);
			var nextSelect=index>0?index-1:0;
			$('#tt').datagrid('selectRow', nextSelect);
			if(orgId){
				$.post("${ctx}/plas/plas-org-check-rel!delete.action",{orgId:orgId,posId:posId} , function(result) {
					if('success' == result){
						$('#tt').datagrid('acceptChanges');
						$('#span_savePosTip').html('收回成功!').show().fadeOut(2000);
					}else{
						$.messager.alert('提示','收回失败!'+result);
						$('#tt').datagrid('rejectChanges');
					}
				});
			}else{
				$('#tt').datagrid('rejectChanges');
			}
		}
	});
}
function appendSysPos(sysPosCd,sysoPosName,orgName,orgCd){
	$('#span_savePosTip').hide();
	if(gOrgId){
		$('#tt').datagrid('appendRow',{
			sysPosName:sysoPosName,
			orgName:orgName,
			orgCd:orgCd,
			sysPosCd:sysPosCd
		});
		
		var url='${ctx}/plas/plas-org-check-rel!save.action';
		$.post(url,{orgId:gOrgId,sysPosCd:sysPosCd},function(result){
			if('success' == result){
				$('#span_savePosTip').html('授权成功!').show().fadeOut(2000);
			}else{
				$.messager.alert('提示','授权机构管理员失败:' + result);
			}
		});
	}else{
		
	}
}


//搜索定位
var curVal = null;
var curNode = null;

var searchTreeManager;
function searchTreeNode(dom){
	if(searchTreeManager)clearTimeout(searchTreeManager);
	searchTreeManager = setTimeout(function(){
		processSearch(dom);
	}, 300);
}
function searchTreeNodeById(value){
	curNode = gOrgTree.searchNodeById(value, curNode);
	if(curNode){
		var nodes = curNode.getPathNodes();
		for(var i= 0; i < nodes.length; i++){
			nodes[i].expand();
		}
		gOrgTree.setFocusNode(curNode);
		var nodeDom = curNode['html-element']['text'];
		var noh = $(nodeDom).offset().top;
	}
}
function processSearch(dom){
	processSearch(dom,gOrgTree);
}
function processSearch(dom,gOrgTree1){
	
	if($(dom).val().trim() == ''){
		$('#inputSearchOperate').removeClass('searchNextActive').addClass('searchNextNoActive');
	}else{
		$('#inputSearchOperate').removeClass('searchNextNoActive').addClass('searchNextActive');
	}
	
	$(dom).css({color:"red"});
		if($(dom).val().trim() == curVal){
		//NONE
	}else{
		curVal = $(dom).val().trim();
		curNode = null;
	}
		curNode = gOrgTree.searchNode(curVal, curNode);
	if(curNode){
		var nodes = curNode.getPathNodes();
		for(var i= 0; i < nodes.length; i++){
			nodes[i].expand();
		}
		gOrgTree.setFocusNode(curNode);
		var nodeDom = curNode['html-element']['text'];
		var toh = $('#divTreeP').height();
		var top = $('#divTreeP')[0].scrollTop;
		var noh = $(nodeDom).offset().top;
		$('#divTreeP').animate({ scrollTop : top+noh-toh }, "normal");
	}else{
		//$('#inputSearchOperate').removeClass('searchNextActive').addClass('searchNextNoActive');
	}
}
function resetSearchApproveInput(dom){
	if($(dom).val().trim() == ''){
		$(dom).val('搜索机构...');
		$(dom).css({color:"#cccccc"});
	}else{
		$(dom).css({color:"#5A5A5A"});
	}
}
function clearSearchApproveInput(dom){
	if( $(dom).val() == '搜索机构...'){
		$(dom).val('');
		$(dom).css({color:"#5A5A5A"});
	}
}
</script>
</body>
</html>