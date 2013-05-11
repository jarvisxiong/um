<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>角色菜单管理</title>
	<%@ include file="/common/global.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/loadMask/jquery.loadmask.css">
	<script src="${ctx}/js/jquery-lasted.min.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/common.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/ConvertUtil.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/loadMask/jquery.loadmask.min.js"  type="text/javascript" ></script>
	<script>
		$(function(){
			$('#ttRole').tree({
				url: '${ctx}/admin/app/app-role-menu-rel!loadRole.action',
				animate:true,
				dnd: false,
				onClick:function(node){
		        	$('#btn_save').linkbutton('disable');
					var nodeType=node.attributes.nodeType;
					$(this).tree('toggle', node.target);
		        	if (nodeType=='role'){
						$('#menuDiv').mask('正在加载...');
						var parentId=$(this).tree('getParent',node.target).id;
						var url="${ctx}/admin/app/app-role-menu-rel!loadMenu.action?appRoleId="+node.id + "&parentId=" + parentId;
		    			$.post(url,function(result) {
		    				$('#ttMenu').tree('loadData',eval(result));
							$('#menuDiv').unmask();
							$('#btn_save').linkbutton('enable');
						});
					}
				},
				onCheck: function(e, node){
					
				}
			});
			$('#ttMenu').tree({
				url: '${ctx}/admin/app/app-role-menu-rel!loadMenu.action',
				checkbox:true,
				animate:true,
				onClick:function(node){
					var nodeType=node.attributes.nodeType;
					$(this).tree('toggle', node.target);
		        	if (nodeType=='role'){
						var parentId=$(this).tree('getParent',node.target).id;
					}
				},
				onCheck: function(e, node){
					
				}
			});
			$('#btn_save').linkbutton('disable');
		});
		function save(){
			$('body').mask('正在保存...');
			var roleNode = $('#ttRole').tree('getSelected');
			var menuNodes = $('#ttMenu').tree('getChecked');
			var menuIds = [];
			for(var i=0;i<menuNodes.length;i++){
				if(menuNodes[i].attributes.nodeType == 'menu'){
					var menuId = menuNodes[i].id;
					menuIds.push(menuId);
				}
			}
			var menuIds_str = menuIds.join(',');
			var roleId_str = roleNode.id;
			$.post('${ctx}/admin/app/app-role-menu-rel!save.action',{appRoleId:roleId_str,menuIds:menuIds_str},function(){
				$('body').unmask();
			});
		}
	</script>
</head>
<body class="easyui-layout">
<div region="north" style="height:35px;" border="false">
	<div class="toolbar" style="padding:5px;">
		<a href="#" id="btn_save" class="easyui-linkbutton"  iconCls="icon-save" onclick="save();">保存</a>
	</div>
</div>
<div region="west" title="角色结构" split="true"  style="width:200px;">
	<ul id="ttRole"></ul>
</div>
<div  region="center" title="菜单结构" split="true"  style="width:200px;">
	<div id="menuDiv">
	<ul id="ttMenu"></ul>
	</div>
</div>

</body>
</html>