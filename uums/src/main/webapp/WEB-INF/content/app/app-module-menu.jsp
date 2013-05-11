<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>模块菜单管理</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css">

	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
</head>
<body class="easyui-layout">
	<div region="west" title="菜单结构" split="true"  style="width:250px;">
		<ul id="leftTree"></ul>
	</div>
	<div region="center" split="true" style="padding:5px;+position: relative;overflow-x:hidden; ">
		<div class="toolbar" style="height:30px;padding:5px;">
			<a href="#" id="aAddModule" class="easyui-linkbutton" iconCls="icon-add" onclick="appendModule();">新增模块</a>
			<a href="#" id="aAddMenu" class="easyui-linkbutton" iconCls="icon-add" onclick="appendMenu();">新增菜单</a>
		</div>
		<div id="rightContainer">
			<div id="moduleDetailPanel">
			</div>
			
			<div id="menuDetailPanel"> 
			</div>
			
			<div id="tipPanel" class="tip">
				<ol>
					<li>在左侧菜单树上选择模块后才能新增菜单</li>
					<li>在左侧菜单树上右键可以进行增加删除等操作</li>
					<li>左侧菜单树上支持拖曳保存操作</li>
				</ol>
			</div>
		</div>
		
	</div>
	<div id="treeMenu" class="easyui-menu" style="width:120px;">
		<div onclick="appendModule()" iconCls="icon-add" id="btnAddModule">新增模块</div>
		<div onclick="appendMenu()" iconCls="icon-add" id="btnAddMenu">新增菜单</div>
		<div onclick="remove()" iconCls="icon-remove" id="btnDelete" >删除</div>
	</div>

	<script type="text/javascript">
		$(function(){
			$('#aAddMenu').linkbutton('disable');
			$('#aAddModule').linkbutton('disable');
			reloadModuleMenuTree();
		});
		
		function reloadModuleMenuTree(){
			$('#leftTree').tree({
				url: '${ctx}/app/app-module-menu!loadModuleMenuTree.action',
				dnd:true,
				//animate:true,
				onClick:function(node){
					$(this).tree('toggle', node.target);
					var nodeType=node.attributes.nodeType;
					if(nodeType == 'module'){
						showModule(node.id);
						$('#aAddModule').linkbutton('disable');
						$('#aAddMenu').linkbutton('enable');
					}else if(nodeType == 'menu'){
						showMenu(node.id);
						$('#aAddModule').linkbutton('disable');
						$('#aAddMenu').linkbutton('disable');
					}else{
	    				$("#moduleDetailPanel").hide();
	    				$("#menuDetailPanel").hide();
	    				$('#aAddModule').linkbutton('enable');
						$('#aAddMenu').linkbutton('disable');
					}
				},
				onSelect:function(node){
				},
				onContextMenu: function(e, node){

					e.preventDefault();
					$('#leftTree').tree('select', node.target);
					$('#treeMenu').menu('show', {
						left: e.pageX,
						top: e.pageY
					});
					//nodeType:'root','module','menu'
					var nodeType=node.attributes.nodeType;
					if (nodeType=='root'){
						$('#btnAddModule').show();
						$('#btnAddMenu').hide();
						$('#btnDelete').hide();
					}else if (nodeType=='module'){
						$('#btnAddModule').show();
						$('#btnAddMenu').show();
						$('#btnDelete').show();
					}else{
						$('#btnAddModule').hide();
						$('#btnAddMenu').hide();
						$('#btnDelete').show();
					}
				},
				onDrop:function(target, source, point){
					var parentNode=null;
					if( point=='append' ){
						parentNode = $('#leftTree').tree('getNode',target);
					}else{
						parentNode = $('#leftTree').tree('getParent',target);
					}
					if (parentNode.attributes){
						var nodeType = source.attributes.nodeType;
						var parentId = parentNode.id;
						var parentNodeType = parentNode.attributes.nodeType;

						if( parentNodeType == 'module' || (nodeType == 'module' && parentNodeType == 'root')){
							if(nodeType == 'module'){
				        		var url = "${ctx}/app/app-module!save.action?id=" + source.id + "&parentId=" + parentId;
				    			$.post(url,function(result) {
									$('#leftTree').tree('reload');
									$("#moduleDetailPanel").html('');
								});
							}else if( nodeType == 'menu'){
								var url = "${ctx}/app/app-menu!save.action?id=" + source.id + "&appModuleId=" + parentId;
				    			$.post(url,function(result) {
									$('#leftTree').tree('reload');
									$("#menuDetailPanel").html('');
								});
							}
						}else{
							//$.messager.alert('警告','不能拖入此处！','warning');
							$('#leftTree').tree('reload');
						}
					}else{
						$.messager.alert('警告','不能拖入此处！','warning');
						$('#leftTree').tree('reload');
					}
					
				},
				onLoadSuccess:function(){
					$('#leftTree').tree("collapseAll");
					var root = $('#leftTree').tree("getRoot");
					if(root){
						$('#leftTree').tree('expand',root.target);
					}
					
				}
			});
		}
		//新增模块
		function appendModule(){
			var node = $('#leftTree').tree('getSelected');
			if( node == null)
				return;
			var nodeType = node.attributes.nodeType;
			if( nodeType == 'menu'){
				alert("请选择所在根或上级模块!");
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
			showModule("",node.id);
		}
		//新增菜单
		function appendMenu(){
			var node = $('#leftTree').tree('getSelected');
			if( node == null)
				return;
			var nodeType = node.attributes.nodeType;
			if( nodeType != 'module'){
				alert("请选择模块!");
				return ;
			}
			/*
			$('#leftTree').tree('append',{
				parent: (node?node.target:null),
				data:[{
					text:'新增菜单',
					attributes:{nodeType:"menu"}
				}]
			});
			*/
			showMenu("",node.id);
		}
		//删除
		function remove(){
			
			var node = $('#leftTree').tree('getSelected');
			var nodeType=node.attributes.nodeType;
			if (nodeType == 'module'){
        		removeModule(id);
			}else if (nodeType=='menu'){
				removeMenuWithTip(node.id);
			}
				
		}
		function removeMenuWithTip(id){
			var url="${ctx}/app/app-menu!validateEnableDelete.action";
			$.post(url,{id: id},function(result) {
				if("success" == result){
					removeMenu(id);
				}else{
					$.messager.confirm('提示',result,function(r){
						if (r){
							removeMenu(id);
						}else{
							return ;
						}
					});
				}
			});
		}
		//删除模块
		function removeModlue(id){
			var url="${ctx}/app/app-module!delete.action?id="+id;
			$.post(url,function(result) {
				if("true" == result){
					$('#leftTree').tree('remove', node.target);
					$("#moduleDetailPanel").html('');
					$("#menuDetailPanel").html('');
				}else{
					alert(result);
				}
			});
		}
		//删除菜单
		function removeMenu(id){
			var url="${ctx}/app/app-menu!delete.action";
			$.post(url,{id: id},function(result) {
				if("success" == result){
					$('#leftTree').tree('reload');
					$("#moduleDetailPanel").html('');
					$("#menuDetailPanel").html('');
				}else{
					alert(result);
				}
			});
		}
		//展开
		function expand(){
			var node = $('#leftTree').tree('getSelected');
			$('#leftTree').tree('expand',node.target);
		}
		//收起
		function collapse(){
			var node = $('#leftTree').tree('getSelected');
			$('#leftTree').tree('collapse',node.target);
		}
		
		function showModule(id, parentId){
			$('#rightContainer').mask('加载中...');
    		var url = "${ctx}/app/app-module!input.action?id=" + id;
			if(parentId){
    			url = url + "&parentId=" + parentId;
			}
			$.post(url,function(result) {
				$("#moduleDetailPanel").show().html(result);
				$("#menuDetailPanel").hide();
				//$('a.easyui-linkbutton').linkbutton();
				$.parser.parse('#moduleDetailPanel');
				$('#rightContainer').unmask();
			});
		}
		function showMenu(id, appModleId){
			$('#rightContainer').mask('加载中...');
			var url = "${ctx}/app/app-menu!input.action?id=" + id;
			if(appModleId){
    			url = url + "&appModuleId=" + appModleId;
			}
			$.post(url,function(result) {
				$("#moduleDetailPanel").hide();
				$("#menuDetailPanel").show().html(result);
				//$('a.easyui-linkbutton').linkbutton();
				$.parser.parse('#menuDetailPanel');
				$('#rightContainer').unmask();
			});
		}
		function removeModule(moduleId){
			if(window.confirm('确认删除么?')){
				var url = '${ctx}/app/app-module!removeModule.action';
				$.post(url, {moduleId: moduleId},function(result){
					if(result == 'success'){
						$('#rightModulePanel').empty();
						//TODO:刷新模块菜单树
						reloadModuleMenuTree();
					}
				});
			}
		}
	</script>
</body>
</html>