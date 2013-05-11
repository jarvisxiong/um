<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>系统职位管理</title>
	<%@ include file="/common/global.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css" >
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common/treePanel.css"/>

	<%--注意：jquery1.4以上，不适用eval构造json,直接(result)即可 --%>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>

	<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/treePanel.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.quickSearch.js"></script>
</head>
<body class="easyui-layout">
	<div region="west" title='
	机构职位树
	<span><input type=checkbox id="pos_all_flag" onchange="loadOrgSysPosTree()"/>全部</span>
	<span><input type=checkbox id="load_auto_flag" checked="checked" onchange="loadOrgSysPosTree()"/>刷新</span>
	<span><input type=checkbox id="load_view_flag" checked="checked" onchange="loadOrgSysPosTree()"/>人力</span>
	' split="true"  style="width:250px;+position: relative;">
		<div id="positionTree"></div>
	</div>
	<div region="center" split="true" style="padding:5px;+position: relative;overflow-x:hidden; ">
		<div style="padding:5px 0;">
			快捷搜索职位: 
			<input style="width:150px;" type="text" id="quickSearchPos" name="quickSearchPos" value="${quickSearchPos}" title="支持系统职位编号或名称或关联账号uiid搜索"/>
			<input type="hidden" id="quickSearchPosId" name="quickSearchPosId" value="${quickSearchPosId}"/>
			<input type="button" onclick="cleanQuickSearchPos()" value="清空"></input>
			&nbsp;&nbsp;&nbsp;
			
			<security:authorize ifAnyGranted="A_ADMIN">
			<a href="#" id="addBtn"  iconCls="icon-add"  class="easyui-linkbutton" onclick="addSysPos();">新增</a>
			</security:authorize>
		</div>

		<div id="rightContainer">
				 
		</div>
	</div>

	<div id="wSaveConfirm" title="角色变化如下:" class="easyui-window" closed="true" style="top:20px;width:350px;height:500px;padding:5px;background: #fafafa;">
		<div style="width: 100%;text-align: left;">
			<input type="button" style="cursor:pointer" class="buttom" onclick="savePopConfirm()" value="确定" />
			<input type="button" style="cursor:pointer" class="buttom" onclick="closePopConfirm()" value="取消" />
		</div>
		<div id="confirmDetailDiv" style="height:450px;overflow-y:auto;+position: relative;">
			
		</div>
	</div>
	
	<div id="wParentOrg" class="easyui-window" title="人力机构树" closed="true" style="width:300px;max-height:300px;padding:5px;background: #fafafa;">
		<div style="width: 100%;text-align: left;">
			<input type="button" style="cursor:pointer" class="buttom" onclick="savePopOrg()" value="保存" />
			<input type="button" style="cursor:pointer" class="buttom" onclick="closePopOrg()" value="取消" />
			<input type="hidden" id="wParentOrg_orgId"/>
			<input type="hidden" id="wParentOrg_orgCd"/>
			<input type="hidden" id="wParentOrg_orgBizCd"/>
			<input type="hidden" id="wParentOrg_orgName"/>
		</div>
		<div id="orgTreePanel" style="margin-top:10px;">
			<!-- 这里加载人力机构视图 -->
		</div>
	</div>
<script type="text/javascript" >

$(function(){
	//注册快速搜索(模糊匹配:uiid,sysPosName,sysPosCd)
	$('#quickSearchPos').quickSearch(
		'${ctx}/plas/plas-sys-position!quickSearchSysPosList.action',
		['sysPosCd','sysPosName','orgName','activeFlgName','userName'],//'centerOrgName' 速度慢，不显示
		{sysPosCd:'quickSearchPosId',sysPosName:'quickSearchPos'},
		'',
		function(result){
			gPosId = result.attr('plasSysPostionId');
			showPositionDetail(gPosId);
		},
		{'sysPosCd':'quickSearchPos','sysPosName':'quickSearchPos','uiid':'quickSearchPos'}
	);
	loadOrgSysPosTree();
	$('#addBtn').linkbutton('disable');
});

//加载系统职位树
var gPosId;
var gOrgId;
var gOrgSysPosTree;
function loadOrgSysPosTree(focusNodeId){
	

	//若不勾选,不刷新
	if(!$('#load_auto_flag').attr('checked')){
		return;
	}

	$("#positionTree").html('<div style="height:60px;width:100%;">&nbsp;</div>').mask('正在加载系统职位树,请稍候...');
	var url = '${ctx}/plas/plas-sys-position!loadOrgSysPosTree.action';
	var sAllFlg = $('#pos_all_flag').attr('checked');
	var sViewFlg = $('#load_view_flag').attr('checked');
	var data = {sAllFlag: sAllFlg, sViewFlg: sViewFlg};
	$.post(url, data, function(result) {
		$("#positionTree").html('');
		if (result) {
			gOrgSysPosTree = new TreePanel({
				renderTo: "positionTree",
				'root'  : (result),
				'ctx'	:'${ctx}'
			}); 
			//自定义
			gOrgSysPosTree.isDelegateIcon = true;
			gOrgSysPosTree.delegateGetDelegateIcon = function(node){
				return node.iconPath;
			};
			gOrgSysPosTree.delegateOnMouseOverNode = function(node){
				return '';
			};
			
			//修饰所有节点
			for(var k in gOrgSysPosTree.nodeHash){
				var node = gOrgSysPosTree.nodeHash[k];
				var nodeType = node.attributes.nodeType;
				var entityStatusCd = node.attributes.entityStatusCd;
				//职位
				if( nodeType== "3"){
					if(entityStatusCd == '1'){
						 node.iconPath = _ctx + "/images/webim/male_online.jpg"; 
					}else{
						 node.iconPath = _ctx + "/images/webim/male_offline.jpg"; 
					}
				}
				//机构
				else if( nodeType== "1" && node.isLeaf()){
					node.iconPath = _ctx + "/images/imgTree/page.gif";
				}
			}
			gOrgSysPosTree.render(); 
			//单击事件
			gOrgSysPosTree.on(function(node){
				var id  = node.id;
				var parentId = node.parentNode.id;
				if( $.trim(id) == '' || $.trim(id)=='0'){
					return;
				}
				
				var nodeType = node.attributes.nodeType;
				//机构
				if( nodeType == "1"){
					if(node.isExpand){
						node.collapse();
					}else{
						node.expand();
					}
					gOrgId = id;
					$('#addBtn').linkbutton('enable');
				}
				//系统职位
				else if( nodeType == "3"){
					cleanQuickSearchPos();
					showPositionDetail(id);
					$('#addBtn').linkbutton('disable');
				}
			});
			
			/*
			定位到特定节点,有bug
			if(typeof(focusNodeId) != 'undefined'){
				gOrgSysPosTree.focusNodeById(focusNodeId,'positionTree');
			}
			*/
		}
	});
}  
function addSysPos(){
	showPositionDetail('');
}
//查看职位明细
function showPositionDetail(positionId){
	if(positionId)
		gPosId = positionId;
	$('#rightContainer').html('<div style="height:50px;">&nbsp;&nbsp;</div>').mask('正在载入职位明细,请稍候...');
	
	<security:authorize ifAnyGranted="A_ADMIN">
	var url='${ctx}/plas/plas-sys-position!input.action';
	</security:authorize>
	<security:authorize ifNotGranted="A_ADMIN">
	var url='${ctx}/plas/plas-sys-position!view.action';
	</security:authorize>
	 
	$.post(url, {id: positionId,plasOrgId:gOrgId}, function(result) {
		$("#rightContainer").html(result);
		$.parser.parse('#rightContainer');
		
		//到期日期
		$('#endDate').datebox({
			formatter: function(date){
				return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
			},   
			parser: function(date){
				return new Date(Date.parse(date.replace(/-/g,"/"))); 
			}
		});  
		//$('#endDate').next().find('input:first-child').attr('readonly','readonly');
	});
}

//保存职位与账号关联
function savePositionRelAcct(positionId){
	var url = '${ctx}/plas/plas-sys-position!savePositionRelAcct.action';
	var acctId = $('#quickSearchFieldId').val();
	
	if('' == $.trim(acctId)){
		alert('请输入关联账号!');
		$('#quickSearchField').focus();
		return;
	}

	$('#quickSearchFieldIdTip').hide();
	$.post(url, {positionId: positionId, acctId: acctId}, function(result){
		if('success' == result){
			$('#quickSearchFieldIdTip').html('保存关联账号成功!').show();
		}else{
			$.messager.alert('提示','保存关联账号失败! \n' + result);
		}
	});
}
//清空关系账号
function cleanPositionRelAcct(positionId){
	var url = '${ctx}/plas/plas-sys-position!cleanPositionRelAcct.action';
 
	$('#quickSearchFieldIdTip').hide();
	$.post(url, {positionId: positionId}, function(result){
		if('success' == result){
			$('#quickSearchField').val('');
			$('#quickSearchFieldId').val('');
			$('#quickSearchFieldIdTip').html('清空关系账号成功!').show();
		}else{
			$.messager.alert('提示','清空关联账号失败! \n' + result);
		}
	});
}
//设置状态
function savePosEnable(){

	var positionId = $('#plasSysPositionId').val();
	var activeBl = $('#quickActiveBl').attr("checked");
	if(positionId == ''){
		return;
	} 
	$('#tip_checkOperate').hide();
	var tip = '';
	if(activeBl){
		tip = '设置';
	}else{
		tip = '解除';
	}
	var url = '${ctx}/plas/plas-sys-position!savePositionFlg.action';
	$.post(url, {positionId: positionId,activeBl: activeBl}, function(result){
		if('success' == result){
			$('#tip_checkOperate').html(tip + '成功!').show();
		}else{
			$.messager.alert('提示',tip + '失败! \n' + result);
		}
	});
}
//临时职位
function savePosTmp(){

	var positionId = $('#plasSysPositionId').val();
	var tmpPosFlg = $('#tmpPosFlg').attr("checked");
	if(positionId == ''){
		return;
	}
	$('#tip_tmpOperate').hide();
	var tip = '';
	if(tmpPosFlg){
		tip = '设置';
	}else{
		tip = '解除';
	}
	var url = '${ctx}/plas/plas-sys-position!savePosTmp.action';
	$.post(url, {positionId: positionId, tmpPosFlg: tmpPosFlg}, function(result){
		if('success' == result){
			$('#tip_tmpOperate').html(tip + '成功!').show();
		}else{
			$.messager.alert('提示',tip + '失败! \n' + result);
		}
	});
}
//超编
function savePosBeyond(){

	var positionId = $('#plasSysPositionId').val();
	var beyondFlg = $('#beyondFlg').attr("checked");
	if(positionId == ''){
		return;
	}
	$('#tip_beyondOperate').hide();
	var tip = '';
	if(beyondFlg){
		tip = '设置';
	}else{
		tip = '解除';
	}
	var url = '${ctx}/plas/plas-sys-position!savePosBeyond.action';
	$.post(url, {positionId: positionId, beyondFlg: beyondFlg}, function(result){
		if('success' == result){
			$('#tip_beyondOperate').html(tip + '成功!').show();
		}else{
			$.messager.alert('提示',tip + '失败! \n' + result);
		}
	});
}
//不在统计内
function saveOutStat(){

	var positionId = $('#plasSysPositionId').val();
	var outStatFlg = $('#outStatFlg').attr("checked");
	if(positionId == ''){
		return;
	}
	$('#tip_outStatOperate').hide();
	var tip = '';
	if(outStatFlg){
		tip = '设置';
	}else{
		tip = '解除';
	}
	var url = '${ctx}/plas/plas-sys-position!saveOutStat.action';
	$.post(url, {positionId: positionId, outStatFlg: outStatFlg}, function(result){
		if('success' == result){
			$('#tip_outStatOperate').html(tip + '成功!').show();
		}else{
			$.messager.alert('提示',tip + '失败! \n' + result);
		}
	});
}

//是否可见
function saveVisableFlg(){

	var positionId = $('#plasSysPositionId').val();
	var visableFlg = $('#visableFlg').attr("checked");
	if(positionId == ''){
		return;
	}
	$('#tip_visableFlgOperate').hide();
	var tip = '';
	if(visableFlg){
		tip = '设置';
	}else{
		tip = '解除';
	}
	var url = '${ctx}/plas/plas-sys-position!saveVisableFlg.action';
	$.post(url, {positionId: positionId, visableFlg: visableFlg}, function(result){
		if('success' == result){
			$('#tip_visableFlgOperate').html(tip + '成功!').show();
		}else{
			$.messager.alert('提示',tip + '失败! \n' + result);
		}
	});
}


/* ******************************************************
 * 明细页面功能
 */

 //保存系统职位明细
 function saveSysPos(plasPositionId){		
 	$('#sysPosForm').form('submit',{
 		url:'${ctx}/plas/plas-sys-position!save.action',
 		onSubmit: function(){
 			var flag = $('#sysPosForm').form('validate');
 			return flag;
 		},
 		success:function(result){
 			if(result && result.split(',').length == 2 && 'success' == result.split(',')[0]){
 				var tmpPosId = result.split(',')[1];
 				loadOrgSysPosTree(tmpPosId);
 				$('#tip_operate').html('保存职位信息成功!').show();
 				showPositionDetail(tmpPosId);
 			}else{
 				$.messager.alert('提示','保存职位信息失败!'+result);
 			}
 		}
 	});
 }
 //重置
 function resetSysPos(){
 	$('#sysPosForm')[0].reset();
 }
 
 //停用职位
 function disablePos(positionId){
 	$.messager.confirm('确认','停用系统职位,将删除与人员的关系，您确定继续吗?',function(r){
 		if (r){
			var url="${ctx}/plas/plas-sys-position!disablePos.action";
 			$.post(url, {positionId: positionId}, function(result) {
 				if('success' == result){
 					showPositionDetail(positionId);
 					$.messager.alert('提示','停用职位成功!');
 				}else{
 					$.messager.alert('提示','停用职位失败!');
 				}
 			});
 		}
 	});
 }  
 //启用职位
 function enablePos(positionId){
	var url="${ctx}/plas/plas-sys-position!enablePos.action";
	$.post(url, {positionId: positionId}, function(result) {
		if('success' == result){
			showPositionDetail(positionId);
			$.messager.alert('提示','启用职位成功!');
		}else{
			$.messager.alert('提示','启用职位失败!');
		}
	});
 }

 //复制系统职位
 function copySysPos(positionId){
	if(!window.confirm('系统提示:复制职位同时将复制职位与角色、机构的关系!是否继续?')){
		return;
	}
 	var url="${ctx}/plas/plas-sys-position!copySysPosition.action";
 	$.post(url, {id: positionId}, function(result) {
 		if('success' == result){
 			loadOrgSysPosTree();
 			$.messager.alert('提示','复制职位成功!');
 		}else{
 			$.messager.alert('提示','复制职位失败!' + result);
 		}
 	});
 }

 //查看角色
 function viewRelRoleList(positionId){
	$('#roleTreePanel').hide();
	$('#rolePackPanel').hide();
	$("#relRoleListPanel").html('<div style="height:60px;width:100%;">&nbsp;</div>').mask('正在搜索角色,请稍候...');
	var url = '${ctx}/plas/plas-sys-position!viewRelRoleList.action';
 	$.post(url, {plasSysPositionId: positionId,isAllRoleFlg:"true"}, function(result) {
 		$("#relRoleListPanel").html('');
 		$('#relRoleListPanel').html(result).show();
		$.parser.parse('#relRoleListPanel');
 	}); 
 }
 
 //配置角色树
 var roleTree;
 function loadRoleTree(positionId){
	 $('#relRoleListPanel').hide();
	 $('#rolePackPanel').hide();
	if(!positionId){
		return;
	}
	
	$('#btnSaveBatchRole').linkbutton('disable');
 	$("#roleTree").html('<div style="height:60px;width:100%;">&nbsp;</div>').mask('正在载入角色树,请稍候...');
 	var url = '${ctx}/plas/plas-sys-pos-role-rel!loadSysPosRoleRelTree.action';
 	$.post(url, {plasSysPositionId: positionId}, function(result) {
 		$("#roleTree").html('');
 		if (result) {
 			roleTree = new TreePanel({
 				renderTo: "roleTree",
 				'root'  : (result),
 				'ctx'	:'${ctx}'
 			}); 
 			//自定义
 			roleTree.isDelegateIcon = true;
 			roleTree.delegateGetDelegateIcon = function(node){
 				return node.iconPath;
 			};
 			roleTree.delegateOnMouseOverNode = function(node){
 				return '';
 			};
 			

 			//修饰所有节点
 			for(var k in roleTree.nodeHash){
 				var node = roleTree.nodeHash[k];
 				var nodeType = node.attributes.nodeType;
 				if( nodeType== "0"){
 					node.iconPath = _ctx + "/images/webim/male_online.jpg"; 
 					
 				}
 				else if( nodeType== "1" && node.isLeaf()){
 					node.iconPath = _ctx + "/images/imgTree/page.gif";
 				}
 			}
 			roleTree.render(); 
 			//单击事件
 			roleTree.on(function(node){
 				var id  = node.id;
 				var parentId = node.parentNode.id;
 				if( $.trim(id) == '' || $.trim(id)=='0'){
 					return;
 				}
 				
 				var nodeType = node.attributes.nodeType;
 				if( nodeType == "1"){//应用或角色组
 					if(node.isExpand){
 						node.collapse();
 					}else{
 						node.expand();
 					}
 				}
 			});
 		}
 		//treePanel动态生成,只能在这里赋值height:400px;overflow-y:auto;+position: relative;
 		$('#roleTree').find('.TreePanel').css({"height":"400px", "overflow-y": "auto", "+position":"relative"});
 		
 		
	 	$('#btnSaveBatchRole').linkbutton('enable');
	 	$('#roleTreePanel').show();
 	});
 }

 //配置打包角色
 function loadRolePack(positionId){
	 $('#roleTreePanel').hide();
	 $('#relRoleListPanel').hide();
	 $("#rolePackPanel").html('<div style="height:60px;width:100%;">&nbsp;</div>').mask('正在载入角色包,请稍候...');
	 var url = '${ctx}/plas/plas-sys-position!viewRolePackList.action';
	 $.post(url, {plasSysPositionId: positionId}, function(result) {
		$("#rolePackPanel").html('');
	 	$('#rolePackPanel').html(result).show();
		$.parser.parse('#rolePackPanel');
	 }); 
 }
 
 //保存配置
 function showPopConfirm(){
 	var addDels = getAddDeleteIds();
 	var addRoleIds = addDels[0];
 	var delRoleIds = addDels[1];
 	if((addRoleIds == '') && (delRoleIds == '') ){
 		alert("未发生变化!");
 		return;
 	}
 	$("#confirmDetailDiv").html(getPopDiv());
 	$("#wSaveConfirm").window('open');
 }
 //关闭
 function closePopConfirm(){
 	$('#wSaveConfirm').window('close');
 }

 function savePopConfirm(){
	var addDels = getAddDeleteIds();
	var addRoleIds = addDels[0];
	var delRoleIds = addDels[1];
	
	if((addRoleIds == '') && (delRoleIds == '') ){
		$.messager.alert('Info',"请选择授权或收回人员");
		return;
	}
	$("#roleTree").html('<span><image src="${ctx}/images/loading.gif"/>保存设置中...</span>');
	var url='${ctx}/plas/plas-sys-pos-role-rel!saveBatch.action';
	var data = {plasSysPositionId : gPosId, addRoleIds: addRoleIds,delRoleIds:delRoleIds}
	$.post(url, data, function(result) {
		if(result == 'success'){
			closePopConfirm();
			loadRoleTree(gPosId);
			$('#successTip').html('授权成功!').show().fadeOut(10000);
		}else{
			$.messager.alert('提示','授权角色失败!');
		}
	});
 }

 //返回html片段
 function getPopDiv(){
 	var modifyNodes = roleTree.getModifiedLeafNodes('0');//0-角色//roleTree.getModifiedLeafNodes('0');//0-用户
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
	popDiv.push("<table style='width:98%;border:1px solid #D2E0F2;' class='panel-header'>");
	popDiv.push("	<tr class='panel-header'><th style='text-align:center;'>新增清单</th><th style='text-align:center;'>收回清单</th></tr>" );
 	popDiv.push("	<tr><td valign='top'><div>"+ addDiv.join("")+"</div></td><td><div>"+ delDiv.join("")+"</div></td></tr>");
 	popDiv.push("</table>");

 	return popDiv.join("");
 }

 //获取授权或收回的用户IDs
 function getAddDeleteIds(){
 	var modifyNodes = roleTree.getModifiedLeafNodes('0');//0-角色
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
//选择上级机构
function showPopOrg(){
	$('#orgTreePanel').html('<div style="height:60px;">&nbsp;</div>').mask('正在载入机构关系树,请稍候...');
	var url = '${ctx}/plas/plas-org!loadOrgTreePhysical.action';
	$.post(url, function(result) {
		$('#orgTreePanel').empty().unmask();
		if (result) {
			var mytree = new TreePanel({
				renderTo : "orgTreePanel",
				'root' : (result),
				'ctx' : '${ctx}'
			});
			//自定义
			mytree.isDelegateIcon = true;
			mytree.delegateGetDelegateIcon = function(node) {
				return node.iconPath;
			};
			mytree.delegateOnMouseOverNode = function(node) {
				return '';
			};
			//修饰所有节点
			for ( var k in mytree.nodeHash) {
				var node = mytree.nodeHash[k];
				var nodeType = node.attributes.nodeType;
				if( nodeType == "1" && node.isLeaf()){
					node.iconPath = _ctx + "/images/imgTree/page.gif";
				}
			}
			mytree.render();
			//单击事件
			mytree.on(function(node) {
				var nodeId = node.id;
				var nodeType = node.attributes.nodeType;
				if(nodeType == '1') {//机构
					$('#wParentOrg_orgId').val(nodeId);
					$('#wParentOrg_orgCd').val(node.attributes.entityCd);
					$('#wParentOrg_orgBizCd').val(node.attributes.entityBizCd);
					$('#wParentOrg_orgName').val(node.attributes.entityName);
					
					if (node.isExpand) {
						node.collapse();
					} else {
						node.expand();
					}
				} 
			});
		}
	});
	$('#wParentOrg').window('open');
}
//保存调动机构
function savePopOrg(){
	var orgId 	= $('#wParentOrg_orgId').val();
	var orgCd 	= $('#wParentOrg_orgCd').val();
	var orgBizCd= $('#wParentOrg_orgBizCd').val();
	var orgName = $('#wParentOrg_orgName').val();
	
	if(orgId == '' || orgId == '0'){
		alert('请选择机构!');
		return;
	}

	var url = '${ctx}/plas/plas-sys-position!saveSysPosRedeploy.action';
	$('#saveUserParentOrgTip').hide();
	$.post(url, {id: gPosId, plasOrgId: orgId}, function(result) {
		if('success' == result){
			var title = '机构业务编号:['+orgBizCd+'],机构内码:['+ orgCd+']';
			$('#span_parentOrg').attr("title",title);
			$('#span_parentOrg').html(orgName);
			$('#saveUserParentOrgTip').html('保存上级成功!').show();
			//重新载入
			loadOrgSysPosTree();
			showPositionDetail(gPosId);
			//关闭窗口
			closePopOrg();
		}else{
			$.messager.alert('异常',result);
		}
	});
}

//关闭机构
function closePopOrg(){
	$('#wParentOrg').window('close');
}
//清空系统职位输入框
function cleanQuickSearchPos(){
	$('#quickSearchPos').val('');
	$('#quickSearchPosId').val('');
}
//清空账号输入框
function cleanSearchField(){
	$('#quickSearchField').val('');
	$('#quickSearchFieldId').val('');
}
</script>
</body>
</html>