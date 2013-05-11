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
	<script src="${ctx}/js/jquery-lasted.min.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/common.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/ConvertUtil.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script>
		$(function(){
			$('#tt').tree({
				url: '${ctx}/admin/app/app-role-module!list.action',
				dnd:true,
				animate:true,
				onClick:function(node){
					var nodeType=node.attributes.nodeType;
					$(this).tree('toggle', node.target);
		        	if (nodeType=='module'){
		    			//$('#ffRole').hide();
		    			//$('#ffModule').show();
		    			var parentId=$(this).tree('getParent',node.target).id;
		        		var url="${ctx}/admin/app/app-role-module!input.action?id="+node.id+"&parentId="+parentId;
		    			$.post(url,function(result) {
							$('#centerDiv').html(result);
						});
					}else if (nodeType=='role'){
						var parentId=$(this).tree('getParent',node.target).id;
						var url="${ctx}/admin/app/app-role-module!inputRole.action?appRoleId="+node.id+"&id="+parentId;
		    			$.post(url,function(result) {
							$('#centerDiv').html(result);
						});
						/*
		    			$('#appRoleModuleId').val(node.id);
						var url="${ctx}/admin/app/app-role-module!loadRole.action?id="+node.id;
			        	$('#ffRole').form('load',url);
			        	*/
					}
				},
				onContextMenu: function(e, node){
					e.preventDefault();
					$('#tt').tree('select', node.target);
					$('#mm').menu('show', {
						left: e.pageX,
						top: e.pageY
					});
					var nodeType=node.attributes.nodeType;
					if (nodeType=='root'){
						$('#btnAddModule').show();
						$('#btnAddRole').hide();
						$('#btnDelete').hide();
					}else if (nodeType=='module'){
						$('#btnAddModule').show();
						$('#btnAddRole').show();
						$('#btnDelete').show();
					}else{
						$('#btnAddModule').hide();
						$('#btnAddRole').hide();
						$('#btnDelete').show();
					}
					var isLeaf=$('#tt').tree('isLeaf', node.target);
					if (!isLeaf){
						$('#btnDelete').hide();
					}
				},
				onDrop:function(target, source, point){
						var parentNode=null;
						if(point=='append'){
							parentNode=$('#tt').tree('getNode',target);
						}else{
							parentNode=$('#tt').tree('getParent',target);
						}
						var parentId=parentNode.id;
						if (parentNode.attributes){
							var parentNodeType=parentNode.attributes.nodeType;
							var nodeType=source.attributes.nodeType;
							if (parentNodeType=='module'||nodeType=='module'&&parentNodeType=='root'){
								if (nodeType=='module'){
					        		var url="${ctx}/admin/app/app-role-module!save.action?id="+source.id+"&parentId="+parentId;
					    			$.post(url,function(result) {
										//$('#centerDiv').html(result);
									});
								}else if (nodeType=='role'){
									var url="${ctx}/admin/app/app-role-module!saveRole.action?appRoleId="+source.id+"&id="+parentId;
					    			$.post(url,function(result) {
										//$('#centerDiv').html(result);
									});
								}
							}else{
								$.messager.alert('警告','不能拖入此处！','warning');
								$('#tt').tree('reload');
							}
						}else{
							$.messager.alert('警告','不能拖入此处！','warning');
							$('#tt').tree('reload');
						}
					
				}
			});
		});
		function appendModule(){
			var node = $('#tt').tree('getSelected');
			var url="${ctx}/admin/app/app-role-module!input.action?parentId="+node.id;
			$.post(url,function(result) {
				$('#centerDiv').html(result);
			});
			/*
			$('#tt').tree('append',{
				parent: (node?node.target:null),
				data:[{
					text:'new Module',
					attributes:{nodeType:"module"}
				}]
			});
			*/
		}
		function appendRole(){
			var node = $('#tt').tree('getSelected');
			var url="${ctx}/admin/app/app-role-module!inputRole.action?id="+node.id;
			$.post(url,function(result) {
				$('#centerDiv').html(result);
			});
			/*
			var newNode={
					text:'new Role',
					attributes:{nodeType:"role"}
					};
			$('#tt').tree('append',{
				parent: (node?node.target:null),
				data:[newNode]
			});
			$('#tt').tree('select', newNode);
			*/
		}
		function remove(){
			$.messager.confirm('Confirm','Are you sure?',function(r){
				if (r){
					var node = $('#tt').tree('getSelected');
					var nodeType=node.attributes.nodeType;
					if (nodeType=='module'){
		        		var url="${ctx}/admin/app/app-role-module!delete.action?id="+node.id;
		    			$.post(url,function(result) {
							if (result=='true'){
		    					$('#tt').tree('remove', node.target);
								$('#centerDiv').html('');
							}else{
								alert(result);
							}
						});
					}else if (nodeType=='role'){
						var url="${ctx}/admin/app/app-role-module!deleteRole.action?appRoleId="+node.id;
		    			$.post(url,function(result) {
							if (result=='true'){
		    					$('#tt').tree('remove', node.target);
								$('#centerDiv').html('');
							}else{
								alert(result);
							}
						});
						/*
		    			$('#appRoleModuleId').val(node.id);
						var url="${ctx}/admin/app/app-role-module!loadRole.action?id="+node.id;
			        	$('#ffRole').form('load',url);
			        	*/
					}
					
				}
			});
		}
		function collapse(){
			var node = $('#tt').tree('getSelected');
			$('#tt').tree('collapse',node.target);
		}
		function expand(){
			var node = $('#tt').tree('getSelected');
			$('#tt').tree('expand',node.target);
		}
		
	</script>
</head>
<body class="easyui-layout">
<div region="north" style="height:35px;" border="false">
	<div class="toolbar" style="padding:5px;">
		<a href="#" class="easyui-linkbutton"  iconCls="icon-save" onclick="save();">保存</a>
	</div>
</div>
<div region="west" title="角色结构" split="true"  style="width:150px;">
	<ul id="tt"></ul>
</div>
<div region="center" split="true" style="padding:5px;" id="centerDiv">
	<form id="ffModule" method="post" style="display:none;">
		<input type="hidden" name="appRoleModuleId" value="${appRoleModuleId}" /> 
		<table>
			<tr>
				<td>模块代码:</td>
				<td><input name="roleModuleCd" id="roleModuleCd" class="easyui-validatebox" required="true" validType="length[0,50]" type="text" value="${roleModuleCd}"></input></td>
			</tr>
			<tr>
				<td>模块名称:</td>
				<td><input name="roleModuleName" id="roleModuleName"  class="easyui-validatebox" required="true" validType="length[0,50]" type="text" value="${roleModuleName}"></input></td>
			</tr>
			<tr>
				<td>备注:</td>
				<td><textarea name="remark" class="easyui-validatebox" validType="length[0,200]">${remark}</textarea> </td>
			</tr>
		</table>
	</form>
	<form id="ffRole" method="post" style="display:none;">
		<input type="hidden" name="appRoleId" value="${appRoleId}" /> 
		<input type="hidden" name="appRoleModuleId" id="appRoleModuleId" value="${appRoleModuleId}" /> 
		<table>
			<tr>
				<td>角色代码:</td>
				<td><input name="roleCd" id="roleCd" class="easyui-validatebox" required="true" validType="length[0,50]" type="text" value="${roleCd}"></input></td>
			</tr>
			<tr>
				<td>角色名称:</td>
				<td><input name="roleName" id="roleName"  class="easyui-validatebox" required="true" validType="length[0,50]" type="text" value="${roleName}"></input></td>
			</tr>
			<tr>
				<td>备注:</td>
				<td><textarea name="remark" class="easyui-validatebox" validType="length[0,200]">${remark}</textarea> </td>
			</tr>
		</table>
	</form>
</div>
<div id="mm" class="easyui-menu" style="width:120px;">
		<div onclick="appendModule()" iconCls="icon-add" id="btnAddModule">新增模块</div>
		<div onclick="appendRole()" iconCls="icon-add" id="btnAddRole">新增角色</div>
		<div onclick="remove()" iconCls="icon-remove" id="btnDelete" >删除</div>
		<div class="menu-sep"></div>
		<div onclick="expand()">Expand</div>
		<div onclick="collapse()">Collapse</div>
</div>

</body>
</html>