<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>角色组管理</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common/treePanel.css"/>

	<%--注意：jquery1.4以上，不适用eval构造json,直接(result)即可 --%>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/validate/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/treePanel.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.quickSearch.js"></script>
</head>
<body class="easyui-layout">
	<div region="west" title='角色结构
		<span><input type=checkbox id="load_auto_flag" checked="checked" onchange="reloadGroupTree()"/>刷新</span>
	' split="true" style="width:250px;">
		<div id="leftPanel">
		</div>
		<div>
			<ul id="leftTree"></ul>
		</div>
	</div>
	<div region="center" split="true" style="padding:5px;+position: relative;overflow-x:hidden; ">
		<div class="toolbar" style="height:30px;padding:5px;">
		<input id="gbCurType" type="hidden" value="1"/>
		快捷搜索角色: 
			<input style="width:150px;" type="text" id="quickSearchField" name="quickSearchField" value="${quickSearchField}" title="支持角色名称和角色编号搜索角色"/>
			<input type="hidden" id="quickSearchFieldId" name="quickSearchFieldId" value="${quickSearchFieldId}"/>
			<input type="button" onclick="cleanQuickSearchRole()" value="清空"></input>
			
			<a href="#" disabled="true" id="addGroup" class="easyui-linkbutton" iconCls="icon-add" onclick="appendRoleGroup();">新增角色组</a>
			<a href="#" disabled="true" id='addRolebtn' class="easyui-linkbutton" iconCls="icon-add" onclick="appendRole();">新增角色</a>
			
			<security:authorize ifAnyGranted="A_ADMIN">
			<a href="#" id='btnShowExport' class="easyui-linkbutton" iconCls="icon-add" onclick="showExport();">导出角色</a>
			</security:authorize>
		</div>
		<div id="rightContainer">
			<div id="groupDetailPanel"></div>
			<div id="roleDetailPanel"></div>
			<div id="exportPanel" style="display: none;">
				<security:authorize ifAnyGranted="A_ADMIN">
					<%--导出form --%>
					<form action="plas-role-group!exportRoleRelList.action" method="post" id="exportForm">
						<input type="hidden" id="roleids" name="roleids" value=""/>
					</form>
					
					<div id="btnExecExportDiv"><a href="#" id='btnExecExport' class="easyui-linkbutton" iconCls="icon-save" onclick="execExport();">执行导出</a></div>
					<div id="exportTree"></div>
				</security:authorize>
			</div>
		</div>
	</div>
	<div id="treeMenu" class="easyui-menu" style="width:120px;">
		<div onclick="appendRoleGroup()" iconCls="icon-add" id="btnAddModule">新增角色组</div>
		<div onclick="appendRole()" iconCls="icon-add" id="btnAddMenu">新增角色</div>
		<div onclick="copyRole()" iconCls="icon-copy" id="btnCopyMenu">复制角色</div>
		<div onclick="remove()" iconCls="icon-remove" id="btnDeleteRole">删除</div>
	</div>
	
<script type="text/javascript">
$(function(){
	$('#leftPanel').html('<div style="height:60px;width:100%;"></div>').mask('正在载入应用与角色树,请稍候...');
	$('#leftTree').tree({
		url: '${ctx}/plas/plas-role-group!loadRolesTree.action',
		dnd: false,
		onClick:function(node){
			$(this).tree('toggle', node.target);
			var nodeType = node.attributes.nodeType;
			if(nodeType == 'module'){
				showRoleGroup(node.id);
				$('#addGroup').linkbutton('disable');
				$('#addRolebtn').linkbutton('enable');
				$('#copyRole').linkbutton('disable');
			}else if(nodeType == 'role'){
				
				$('#addGroup').linkbutton('disable');
				$('#addRolebtn').linkbutton('disable');
				$('#copyRole').linkbutton('enable');
				
				if(node.id =='0'){
					return;
				}
				showRole(node.id);
			}else if(nodeType == 'app'){
				$('#addGroup').linkbutton('enable');
				$('#addRolebtn').linkbutton('disable');
				$('#copyRole').linkbutton('disable');
   				$("#groupDetailPanel").hide();
   				$("#roleDetailPanel").hide();
			}else{
				$('#addGroup').linkbutton('disable');
				$('#addRolebtn').linkbutton('disable');
				$('#copyRole').linkbutton('disable');
   				$("#groupDetailPanel").hide();
   				$("#roleDetailPanel").hide();
			}
		},
		onExpand: function(node){
			var nodeType=node.attributes.nodeType;
			if(nodeType == 'app'){
				node.iconCls = 'icon-folderOpen';
				$('#leftTree').tree('update',node);
			}

		},
		onCollapse: function(node){
			var nodeType=node.attributes.nodeType;
			if(nodeType == 'app'){
				node.iconCls = 'icon-folder';
				$('#leftTree').tree('update',node);
			}
		},
		onContextMenu: function(e, node){
			e.preventDefault();
			$('#leftTree').tree('select', node.target);
			$('#treeMenu').menu('show', {
				left: e.pageX,
				top: e.pageY
			});
			//nodeType:'root','module','menu', 'role',??
			var nodeType=node.attributes.nodeType;
			switch(nodeType){
				case 'root':
					$('#btnAddModule').show();
					$('#btnAddMenu').hide();
					$('#btnDeleteRole').hide();
					$('#btnCopyMenu').hide();
					break;
				case 'app':
					$('#btnAddModule').show();
					$('#btnAddMenu').hide();
					$('#btnDeleteRole').hide();
					$('#btnCopyMenu').hide();
					break;
				case 'module':
					$('#btnAddModule').hide();
					$('#btnAddMenu').show();
					$('#btnDeleteRole').show();
					$('#btnCopyMenu').hide();
					break;
				case 'role':
					$('#btnAddModule').hide();
					$('#btnAddMenu').hide();
					$('#btnDeleteRole').show();
					$('#btnCopyMenu').show();
					break;
				default:
					$('#btnAddModule').hide();
					$('#btnAddMenu').hide();
					$('#btnDeleteRole').hide();
					$('#btnCopyMenu').hide();
					break;
			} 
		}, 
		onLoadSuccess:function(){
			$('#leftPanel').html('').unmask();
			$('#leftTree').tree("collapseAll");
			var root = $('#leftTree').tree("getRoot");
			if(root){
				$('#leftTree').tree('expand',root.target);
			}
		}
	});

	//快速搜索角色(模糊匹配:roleName,roleCd)  @author liuzh
	$('#quickSearchField').quickSearch(
		'${ctx}/plas/plas-role!quickSearchRoleList.action',
		['appChnName','roleGroupName','roleName','roleCd'],
		{plasRoleId:'quickSearchFieldId',roleName:'quickSearchField'},
		'',
		function(result){
			showRole(result.attr('plasRoleId'),null,null)
		}
	);
});

//新增角色组
function appendRoleGroup(){

	var node = $('#leftTree').tree('getSelected');
	if( node == null){
		$.messager.alert('提示',"请选择所在根或上级模块!");
		return;}
	
	var nodeType = node.attributes.nodeType;

	if( nodeType == 'role' || nodeType == 'module'){
		$.messager.alert('提示',"请选择应用模块!");
		return ;
	}
	/*
	$('#leftTree').tree('append',{
		parent: (node?node.target:null),
		data:[{
			text:'新增模块',
			attributes:{nodeType:"module"}
		}]
	});
	*/
	showRoleGroup("",node.id);
}

//新增角色
function appendRole(){
	var node = $('#leftTree').tree('getSelected');
	if( node == null){
		$.messager.alert('提示',"请选择根或者角色组!");
		return;}
	var nodeType = node.attributes.nodeType;
	if( nodeType != 'module'){
		$.messager.alert('提示',"请选择角色组!");
		return ;
	}
	var parentNode = $('#leftTree').tree('getParent',node.target);


	showRole("",parentNode.id,node.id);
}
//复制角色
function copyRole(){
	var node = $('#leftTree').tree('getSelected');
	if( node == null){
		$.messager.alert('提示',"请选择角色!");
		return;
	}
	var nodeType = node.attributes.nodeType;
	if( nodeType != 'role'){
		$.messager.alert('提示',"请选择角色!");
		return ;
	}
	
	if(!window.confirm('复制角色,同时自动复制角色与职位关系,确认继续?')){
		return;
	}
	
	var url= '${ctx}/plas/plas-role!copyRole.action';
	var data = {id: node.id};
	$.post(url, data, function(result){
		if(result.indexOf('success') == 0 && result.split(',').length == 2 ){
			$.messager.alert('提示','复制成功!');
			$('#leftTree').tree('reload');
			showRole(result.split(',')[1], '', '')
			/*
			var node = $('#leftTree').tree('find',result.split(',')[1]);
			if(node){
				$('#leftTree').tree('expandTo',node.target);
				$('#leftTree').tree('select',node.target);
			}
			*/
		}else{
			$.messager.alert('提示','保存失败!');
		}	
	});
}
//删除角色组或角色
function remove(){
	var node = $('#leftTree').tree('getSelected');
	var nodeId = node.id;
	var nodeName = node.text;
	var nodeType = node.attributes.nodeType;
	var nodeType = node.attributes.nodeType;
	var url = '';
	var data = {id: nodeId};
	var message = '';
	if(nodeType == 'module'){
		var child = $('#leftTree').tree('isLeaf',node.target);
		if(!child){
			$.messager.alert("提示",'角色组下存在角色,不允许删除');
			return ;
		}
		url = '${ctx}/plas/plas-role-group!delete.action';
		message = '您确认删除角色组[' + nodeName + ']吗?';
	}else if(nodeType == 'role'){
		url = '${ctx}/plas/plas-role!delete.action';
		message = '删除角色，同时将解除与系统职位的关联，您确认删除角色[' + nodeName + ']吗?';
	}
	
	$.messager.confirm('确认操作',message,function(r){
		if (r){
			$.post(url, data, function(result) {
				if('success' == result){
					$('#leftTree').tree('remove', node.target);
					$("#groupDetailPanel").html('');
					$("#roleDetailPanel").html('');
					$.messager.alert('提示','删除成功!');
				}else{
					$.messager.alert('提示','删除失败!' + result);
				}
			});
		}
	});
}
function showRoleGroup(id, parentId){
	
	$('#rightContainer').mask('正在加载角色组明细,请稍候...');
	var url = "${ctx}/plas/plas-role-group!input.action?id=" + id;
	if(parentId){
		url = url + "&parentId=" + parentId;
	}
	$.post(url,function(result) {
		$("#groupDetailPanel").siblings().hide();
		$("#groupDetailPanel").show().html(result);
		$.parser.parse('#groupDetailPanel');
		$('#rightContainer').unmask();
	});
	
}
function showRole(id,plasAppId, plasRoleGroupId){
	 
	$('#rightContainer').mask('正在加载角色明细,请稍候...');
	var url = "${ctx}/plas/plas-role!input.action?id="+id+"&plasAppId=" + plasAppId;
	if(plasRoleGroupId){
		url = url + "&plasRoleGroupId=" + plasRoleGroupId;
	}
	$.post(url,function(result) {
		$("#roleDetailPanel").siblings().hide();
		$("#roleDetailPanel").show().html(result);
		$.parser.parse('#roleDetailPanel');
		$('#rightContainer').unmask();
	});
}


function reloadGroupTree(type) {
	//若不勾选,不刷新
	if(!$('#load_auto_flag').attr('checked')){
		return;
	}
	$('#leftTree').tree('reload');
}

//清空角色输入框
function cleanQuickSearchRole(){
	$('#quickSearchField').val('');
	$('#quickSearchFieldId').val('');
}

var exportTree;

//导出角色与人员关系
function showExport(){
	
	$('#exportPanel').show();
	$('#exportTree').siblings().hide();
	$('#exportTree').show().html('<div style="height:60px;">&nbsp;</div>').mask('正在载入角色树,请稍候...');
	var url = '${ctx}/plas/plas-role-group!loadRolesTreeWithCheck.action';
	$('#btnExecExportDiv').hide();
	$.post(url, {}, function(result) {
		$('#exportTree').empty().unmask();
		if (result) {
			exportTree = new TreePanel({
				renderTo : "exportTree",
				'root' : (result),
				'ctx' : '${ctx}'
			});
			//自定义
			exportTree.isDelegateIcon = true;
			exportTree.delegateGetDelegateIcon = function(node) {
				return node.iconPath;
			};
			exportTree.delegateOnMouseOverNode = function(node) {
				return '';
			};
			//修饰所有节点
			for ( var k in exportTree.nodeHash) {
				var node = exportTree.nodeHash[k];
				var nodeType = node.attributes.nodeType;
				if( nodeType == "1" && node.isLeaf()){
					node.iconPath = _ctx + "/images/imgTree/page.gif";
				}
			}
			exportTree.render();
			//单击事件
			exportTree.on(function(node){
				//1-模块 0-角色
				if('1' == node.attributes.nodeType){
					if (node.isExpand) {
						node.collapse();
					} else {
						node.expand();
					}
				}
			});
			$('#btnExecExportDiv').show();
		}
	}); 
}
function execExport(){
	if(!exportTree){return;}
	var arrNodes = exportTree.getModifiedLeafNodes('0');//0-角色
	var selNodes = arrNodes[0];
	if( selNodes == null || selNodes == ''|| selNodes.length == 0){
		alert('请选择！');
		return;
	}
	//先赋值
	$('#roleids').val(selNodes);
	$('#rightPanel').mask('正在执行...');
	
	setTimeout(removeMaskLayer,5000);
	
	$('#exportForm').submit();
}

function removeMaskLayer() {	
	$('#rightPanel').unmask();
	return false;
}
</script>
</body>
</html>
