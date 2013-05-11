<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户角色配置</title>
	<%@ include file="/common/global.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/loadMask/jquery.loadmask.css">
	<script src="${ctx}/js/jquery-lasted.min.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/loadMask/jquery.loadmask.min.js"  type="text/javascript" ></script>
	<script>
		$(function(){
			$('#userTree').tree({
				url: '${ctx}/admin/app/app-role-user-rel!list.action?type=user',
				animate:true,
				onClick:function(node){
					var userId = node.id;
					$('#btn_save').linkbutton('disable');
					if(userId == '0')return;
					$('#roleTreeContainer').mask('正在加载...');
					$.post('${ctx}/admin/app/app-role-user-rel!role.action',{userId:userId},function(data){
						$('#roleTree').tree('loadData',eval(data));
						$('#roleTreeContainer').unmask();
						$('#btn_save').linkbutton('enable');
					});
				}
			});
			$('#roleTree').tree({
				url:'${ctx}/admin/app/app-role-user-rel!list.action?type=role',
				checkbox:true
			});
			$('#btn_save').linkbutton('disable');
		});
		function save_roles(){
			$('body').mask('正在保存...');
			var userNode = $('#userTree').tree('getSelected');
			var roleNodes = $('#roleTree').tree('getChecked');
			var roleIds = [];
			for(var i=0;i<roleNodes.length;i++){
				if(roleNodes[i].attributes.nodeType == 'role'){
					var roleId = roleNodes[i].id;
					roleIds.push(roleId);
				}
			}
			var roleIds_str = roleIds.join(',');
			var userId_str = userNode.id;
			$.post('${ctx}/admin/app/app-role-user-rel!saveRoles.action',{userId:userId_str,roleIds:roleIds_str},function(){
				$('body').unmask();
			});
		}
	</script>
</head>
<body class="easyui-layout" >
<div region="north" style="height:35px;" border="false">
	<div class="toolbar" align="left" style="padding:5px;">
		<a href="#" class="easyui-linkbutton" id="btn_save" plain="false"  iconCls="icon-save" onclick="save_roles();">保存</a>
		<span>提示:用户树显示所有'已启用'用户</span>
	</div>
</div>
<div region="west"  style="padding:5px;margin:0 5px;width:300px;">
	<div>
		<ul id="userTree"></ul>
	</div>
</div>
<div region="center"  style="padding:5px;">
	<div id="roleTreeContainer">
		<ul id="roleTree"></ul>
	</div>
</div>
</body>
</html>