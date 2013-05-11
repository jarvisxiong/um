<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>角色管理</title>
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
				checkbox:true,
				animate:true
			});
			$('#ttMenu').tree({
				url: '${ctx}/admin/app/app-role-menu-rel!loadMenu.action',
				animate:true,
				onClick:function(node){
					$('#btn_save').linkbutton('disable');
					var nodeType=node.attributes.nodeType;
					$(this).tree('toggle', node.target);
		        	if (nodeType=='menu'){
						$('#roleDiv').mask('正在加载...');
						var parentId=$(this).tree('getParent',node.target).id;
						var url="${ctx}/admin/app/app-role-menu-rel!loadRole.action?appMenuId="+node.id;
		    			$.post(url,function(result) {
		    				$('#ttRole').tree('loadData',eval(result));
							$('#roleDiv').unmask();
							$('#btn_save').linkbutton('enable');
						});
					}
				},
				onCheck: function(e, node){
					
				}
			});
			$('#btn_save').linkbutton('disable');
		});
		function save(){
			$('body').mask('正在保存...');
			var menuNode = $('#ttMenu').tree('getSelected');
			var roleNodes = $('#ttRole').tree('getChecked');
			var roleIds = [];
			for(var i=0;i<roleNodes.length;i++){
				if(roleNodes[i].attributes.nodeType == 'role'){
					var roleId = roleNodes[i].id;
					roleIds.push(roleId);
				}
			}
			var roleIds_str = roleIds.join(',');
			var menuId_str = menuNode.id;
			$.post('${ctx}/admin/app/app-role-menu-rel!saveMenuRel.action',{appMenuId:menuId_str,roleIds:roleIds_str},function(){
				$('body').unmask();
			});
		}
	</script>
</head>
<body class="easyui-layout">
<div region="north" style="height:35px;" border="false">
	<div class="toolbar" align="left" style="padding:5px;">
		<a href="#" id="btn_save" class="easyui-linkbutton"  iconCls="icon-save" onclick="save();">保存</a>
	</div>
</div>
<div  region="west" title="菜单结构" split="true"  style="width:200px;">
	<div id="menuDiv">
	<ul id="ttMenu"></ul>
	</div>
</div>
<div region="center" title="角色结构" split="true">
	<div id="roleDiv">
	<ul id="ttRole"></ul>
	</div>
</div>
</body>
</html>