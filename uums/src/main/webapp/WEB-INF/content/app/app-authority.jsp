<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html" />
	<title>角色菜单权限管理</title>
	<%@ include file="/common/global.jsp" %>
	
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common/treePanel.css" />
	
	<script type="text/javascript"  src="${ctx}/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/treePanel.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
</head>

<body class="easyui-layout">
	<!-- 左边 -->
	<div class="easyui-panel" region="west" title="角色菜单 " split="true" style="width:250px">
		<div id="roleTreePanel" style="min-height:400px"/>
			
		</div>
	</div>
	
	<!-- 中间 -->
	<div class="easyui-panel" id="centerRegion" region="center" split="true" style="width:300px;padding:5px;+position:relative;overflow-x:hidden">
		<div style="width:100%;line-height: 30px;height: 30px;">
			<div style="float:left;margin-left:5px;">当前角色:</div>
			<div id="sel_role_name" style="float:left;background-color: orange;"></div>
			<a id="btnSaveCenterTree" style="float:right;" href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveRoleMenuRel()">保存菜单授权</a>
		</div>
		<div id="successTip" style="margin-left:5px;color:red;"></div>
		<div id="menuTreePanel" style="min-height:400px"/>
			
		</div>
	</div>
	
	<!-- 右边 -->
	<div class="easyui-panel" region="east" split="true" style="width:300px;">
		<div style="width:100%;line-height: 30px;height: 30px;">
			<div style="float:left;margin-left:5px;">当前菜单:</div>
			<div id="sel_menu_name" style="float:left;background-color: orange;"></div>
			<a id="btnSaveRightTree" style="float:right;margin-top:3px" href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveMenuRoleRel()">保存角色授权</a>
		</div>
		<div id="successTipRight" style="margin-left:5px;color:red;"></div>
		<div id="rightRolePanel" style="min-height:400px"/>
			
		</div>
	</div>
	
	
	
	<div id="wSaveConfirm" title="角色授权菜单变化如下:" class="easyui-window" closed="true" style="width:350px;height:500px;padding:5px;background: #fafafa;">
		<div style="width: 100%;text-align: left;">
			<input type="button" style="cursor:pointer" class="buttom" onclick="savePopConfirm()" value="确定" />
			<input type="button" style="cursor:pointer" class="buttom" onclick="closePopConfirm()" value="取消" />
		</div>
		<div id="confirmDetailDiv">
			
		</div>
	</div>
 
	
	<div id="wSaveConfirmRight" title="菜单授权角色变化如下:" class="easyui-window" closed="true" style="width:350px;height:500px;padding:5px;background: #fafafa;">
		<div style="width: 100%;text-align: left;">
			<input type="button" style="cursor:pointer" class="buttom" onclick="savePopConfirmRight()" value="确定" />
			<input type="button" style="cursor:pointer" class="buttom" onclick="closePopConfirmRight()" value="取消" />
		</div>
		<div id="confirmDetailDivRight">
			
		</div>
	</div>
<script type="text/javascript">

//左边角色树
var gRoleTree;
var gSelRoleNode;

//中间菜单树
var gMenuTree;
var gSelMenuNode;
var gRoleCd;

//右边角色树
var gRightRoleTree;

$(function(){ 
	disableSaveBtn();
	disableRightSaveBtn();
	
	loadRoleTree();
});

//加载角色树
function loadRoleTree(){
	disableSaveBtn();
	$('#roleTreePanel').html('<div style="height:60px;">&nbsp;</div>').mask('加载角色中,请稍候..');
	$.post("${ctx}/app/app-authority!loadRoleTree.action", function(result) {
		$("#roleTreePanel").html('').unmask();
		if (result) {
			gRoleTree = new TreePanel({
				renderTo: "roleTreePanel",
				'root'  : eval('('+result+')'),
				'ctx'	:'${ctx}'
			}); 
			//自定义
			gRoleTree.isDelegateIcon = true;
			gRoleTree.delegateGetDelegateIcon = function(node){
				return node.iconPath;
			};
			gRoleTree.delegateOnMouseOverNode = function(node){
				return '';
			};
			//修饰所有节点
 			for(var k in gRoleTree.nodeHash){
 				var node = gRoleTree.nodeHash[k];
 				var nodeType = node.attributes.nodeType;
 				if( nodeType== "0"){
 					node.iconPath = _ctx + "/images/webim/male_online.jpg"; 
 					
 				}
 				if( nodeType== "1" && node.isLeaf()){
 					node.iconPath = _ctx + "/images/imgTree/page.gif";
 				}
 			}
			gRoleTree.render();
			gRoleTree.on(function(node){
				var id = node.attributes.entityId;
				var nodeCd = node.attributes.entityCd;
				var nodeName =  node.attributes.entityName;
				var nodeType = node.attributes.nodeType;
				//根节点
				if(nodeType == '9'){
					return;
				}
				//角色
				else if( nodeType == "0"){
					gSelRoleNode = node;
					gRoleCd = nodeCd;
					
					$('#sel_role_name').html(nodeName);
					$('#sel_role_name').attr('roleCd',nodeCd);
					
					loadMenuTree(nodeCd);
				}else{
					disableSaveBtn();
					if(node.isExpand){
						node.collapse();
					}else{
						node.expand();
					}
				}
			});
		}
	});
}

//加载角色对应的菜单树
function loadMenuTree(roleCd){
	disableSaveBtn();
	var url = "${ctx}/app/app-authority!loadModuleMenuTree.action";
	$('#menuTreePanel').html('<div style="height:60px;">&nbsp;</div>').mask('加载模块/菜单中,请稍候..');
	$.post(url,{roleCd: roleCd}, function(result) {
		$("#menuTreePanel").html('').unmask();
		if (result) {
			gMenuTree = new TreePanel({
				renderTo: "menuTreePanel",
				'root'  : eval('('+result+')'),
				'ctx'	:'${ctx}'
			}); 
			//自定义
			gMenuTree.isDelegateIcon = true;
			gMenuTree.delegateGetDelegateIcon = function(node){
				return node.iconPath;
			};
			gMenuTree.delegateOnMouseOverNode = function(node){
				return '';
			};
			//修饰所有节点
 			for(var k in gMenuTree.nodeHash){
 				var node = gMenuTree.nodeHash[k];
 				var nodeType = node.attributes.nodeType;
 				if( nodeType== "module"){
 					node.iconPath = _ctx + "/images/imgTree/folder.jpg"; 
 					
 				}
 				if( nodeType== "menu" && node.isLeaf()){
 					node.iconPath = _ctx + "/images/imgTree/page.gif";
 				}
 			}
			gMenuTree.render();
			gMenuTree.on(function(node){
				var menuId   = node.attributes.entityId;
				var menuCd   = node.attributes.entityCd;
				var menuName = node.attributes.entityName;
				var nodeType = node.attributes.nodeType;
				//菜单
				if( nodeType == "menu"){
					$('#sel_menu_name').attr('menuId',menuId);
					$('#sel_menu_name').html(menuName);
					gSelMenuNode = node;
					//加载右边角色树
					loadRightRoleTree(menuId)
				}else{
					if( $.trim(menuId) == '' || $.trim(menuId)=='0'){
						return;
					}
					if(node.isExpand){
						node.collapse();
					}else{
						node.expand();
					}
				}
			});
			enableSaveBtn();
		}
	});
	
	$('#centerTreePanel').unmask();
}
//设置生效保存按钮
function enableSaveBtn(){
	$('#btnSaveCenterTree').linkbutton('enable');
}
//设置失效保存按钮
function disableSaveBtn(){
	$('#btnSaveCenterTree').linkbutton('disable');
}
//保存角色->菜单配置
function saveRoleMenuRel(){
	var addDels    = gMenuTree.getAddDeleteIds('menu');//menu-菜单
	var addMenuIds = addDels[0];
	var delMenuIds = addDels[1];
	if((addMenuIds == '') && (delMenuIds == '') ){
		alert("菜单无变化,请确认!");
		return;
	}
	$("#confirmDetailDiv").html(gMenuTree.getPopDiv('menu','授权菜单','收回菜单'));
	$("#wSaveConfirm").window('open');
}
//确认保存
function savePopConfirm(){
	var addDels    = gMenuTree.getAddDeleteIds('menu');//menu-菜单
	var addMenuIds = addDels[0];
	var delMenuIds = addDels[1];
	
	var url = '${ctx}/app/app-authority!saveRoleMenuBatch.action';
	$.post(url,{roleCd: gRoleCd,addMenuIds: addMenuIds,delMenuIds: delMenuIds},function(result){
		if(result=='success'){
			//注意:必须重新加载菜单树,否则点击保存方法,仍可以保存.
			loadMenuTree(gRoleCd);
			closePopConfirm();
			$('#successTip').text('设置成功!').show().fadeOut(2000);
		}else{
			$.messager.alert('提示','设置失败!<br/>'+result);
		}
	});
}
//关闭
function closePopConfirm(){
	$('#wSaveConfirm').window('close');
}

//---------右边--------

function enableRightSaveBtn(){
	$('#btnSaveRightTree').linkbutton('enable');
}
function disableRightSaveBtn(){
	$('#btnSaveRightTree').linkbutton('disable');
}
function loadRightRoleTree(menuId){
	disableRightSaveBtn();
	$('#rightRolePanel').html('<div style="height:60px;">&nbsp;</div>').mask('加载已授权的角色中,请稍候..');
	$.post("${ctx}/app/app-authority!loadRightRoleTree.action", {menuId: menuId}, function(result) {
		$("#rightRolePanel").html('').unmask();
		if (result) {
			gRightRoleTree = new TreePanel({
				renderTo: "rightRolePanel",
				'root'  : eval('('+result+')'),
				'ctx'	:'${ctx}'
			}); 
			//自定义
			gRightRoleTree.isDelegateIcon = true;
			gRightRoleTree.delegateGetDelegateIcon = function(node){
				return node.iconPath;
			};
			gRightRoleTree.delegateOnMouseOverNode = function(node){
				return '';
			};
			//修饰所有节点
 			for(var k in gRightRoleTree.nodeHash){
 				var node = gRightRoleTree.nodeHash[k];
 				var nodeType = node.attributes.nodeType;
 				if( nodeType== "0"){
 					node.iconPath = _ctx + "/images/webim/male_online.jpg"; 
 					
 				}
 				if( nodeType== "1" && node.isLeaf()){
 					node.iconPath = _ctx + "/images/imgTree/page.gif";
 				}
 			}
 			gRightRoleTree.render();
			gRightRoleTree.on(function(node){
				var id = node.attributes.entityId;
				var nodeCd = node.attributes.entityCd;
				var nodeType = node.attributes.nodeType;
				//根节点
				if(nodeType == '9'){
					return;
				} 
				//角色
				else if( nodeType != "0"){
					if(node.isExpand){
						node.collapse();
					}else{
						node.expand();
					}
				}
			});
		}
		enableRightSaveBtn();
	});
}
//保存菜单->角色配置
function saveMenuRoleRel(){
	var addDels    = gRightRoleTree.getAddDeleteIds('0');//0-角色
	var addMenuIds = addDels[0];
	var delMenuIds = addDels[1];
	if((addMenuIds == '') && (delMenuIds == '') ){
		alert("角色无变化,请确认!");
		return;
	}
	$("#confirmDetailDivRight").html(gRightRoleTree.getPopDiv('0','授权角色','收回角色'));
	$("#wSaveConfirmRight").window('open');
}
//确认保存
function savePopConfirmRight(){
	var addDels    = gRightRoleTree.getAddDeleteIds('0');//0-角色
	var addRoleIds = addDels[0];
	var delRoleIds = addDels[1];
	
	var menuId = $('#sel_menu_name').attr('menuId');
	
	if( menuId && menuId != ''){
		var url = '${ctx}/app/app-authority!saveMenuRoleBatch.action';
		$.post(url,{menuId: menuId,addRoleIds: addRoleIds, delRoleIds: delRoleIds},function(result){
			if(result=='success'){
				//注意:必须重新加载右边角色菜单树,否则点击保存方法,仍可以保存.
				loadRightRoleTree(menuId);
				closePopConfirmRight();
				$('#successTipRight').html('设置成功!').show().fadeOut(3000);
			}else{
				$.messager.alert('提示','设置失败!<br/>'+result);
			}
		});
	}else{
		alert('请选择菜单!');
	}
}
//关闭
function closePopConfirmRight(){
	$('#wSaveConfirmRight').window('close');
}

</script>

</body>
</html>
