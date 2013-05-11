<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>角色用户配置</title>
	<%@ include file="/common/global.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/loadMask/jquery.loadmask.css">
	<script src="${ctx}/js/jquery-lasted.min.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/loadMask/jquery.loadmask.min.js"  type="text/javascript" ></script>
	<script>
		$(function(){
			$('#roleTree').tree({
				url: '${ctx}/admin/app/app-role-user-rel!list.action?type=role',
				animate:true,
				onClick:function(node){
					var roleId = node.id;
					$('#btn_save').linkbutton('disable');
					if(node.attributes.nodeType != 'role')return;
					$('#userTreeContainer').mask('正在加载...');
					$.post('${ctx}/admin/app/app-role-user-rel!user.action',{roleId:roleId},function(data){
						$('#userTree').tree('loadData',eval(data));
						$('#userTreeContainer').unmask();
						$('#btn_save').linkbutton('enable');
					});
				}
			});
			$('#userTree').tree({
				url:'${ctx}/admin/app/app-role-user-rel!list.action?type=user',
				checkbox:true
			});
			$('#btn_save').linkbutton('disable');
		});
		function save_users(){
			$('body').mask('正在保存...');
			var roleNode = $('#roleTree').tree('getSelected');
			var userNodes = $('#userTree').tree('getChecked');
			var userIds = [];
			for(var i=0;i<userNodes.length;i++){
				if(userNodes[i].id != '0'){
					var userId = userNodes[i].id;
					userIds.push(userId);
				}
			}
			var userIds_str = userIds.join(',');
			var roleId_str = roleNode.id;
			$.post('${ctx}/admin/app/app-role-user-rel!saveUsers.action',{roleId:roleId_str,userIds:userIds_str},function(){
				$('body').unmask();
			});
		}
	</script>
</head>
<body class="easyui-layout" >
<div region="north" style="height:35px;" border="false">
	<div class="toolbar" align="left" style="padding:5px;">
		<a href="#" class="easyui-linkbutton" id="btn_save" plain="false"  iconCls="icon-save" onclick="save_users();">保存</a>
		<span style="margin-left:220px;">提示:用户树显示所有'已启用'用户</span>
	</div>
</div>
<div region="west"  style="padding:5px;margin:0 5px;width:300px;">
	<div>
		<ul id="roleTree"></ul>
	</div>
</div>
<div region="center"  style="padding:5px;">
	<div id="userTreeContainer">
		<ul id="userTree"></ul>
	</div>
</div>
</body>
</html>