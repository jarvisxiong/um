<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户管理</title>
	<%@ include file="/common/global.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/loadMask/jquery.loadmask.css">
	<script src="${ctx}/js/jquery-lasted.min.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/common.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/ConvertUtil.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/loadMask/jquery.loadmask.min.js"  type="text/javascript" ></script>
</head>
<body class="easyui-layout">
<div region="north" style="height:30px;line-height:30px;" border="false">
	<div class="toolbar">
		<a id="btnAddUser" href="#" class="easyui-linkbutton" disabled="true" iconCls="icon-save" onclick="addUser();">新增用户</a>
		<span>提示:左边树只显示所有'已启用'的用户</span>
	</div>
</div>
<div region="west" title="用户管理" split="true"  style="width:250px;">
	<ul id="userTree"></ul>
</div>
<div  region="center" title="查看明细" split="true"  style="width:250px;" id="detailDiv">

</div>
<div region="south" border="false" style="height:30px;line-height:30px;">
</div>
<script>
	$(function(){
		$('#userTree').tree({
			url: '${ctx}/admin/app/app-user!loadUserTreeRootJson.action',
			animate:true,
			onClick:function(node){
				if(node == null||'0' == node.id){
					$("#btnAddUser").linkbutton('disable');
					return;
				}
				
				if('org' == node.attributes.nodeType){
					$('#btnAddUser').linkbutton('enable'); 
				}
				if('user' == node.attributes.nodeType){
					$('#btnAddUser').linkbutton('disable'); 
				 	editrow(node.id);
				}
			}
		}); 
	});
	function editrow(id){
		$('body').mask();
		var url="${ctx}/admin/app/app-user!input.action?id="+id;
		$.post(url,function(result) {
			$("#detailDiv").html(result);
			$.parser.parse();
			$('body').unmask();
		});
	} 
	
	function addUser(){
		var node = $('#userTree').tree('getSelected');
		if( node==null || node.id == '' || node.id == '0'){
			return;
		}
		var url='${ctx}/admin/app/app-user!input.action';
		var data = {appOrgId: node.id};
		$.post(url,data,function(result) {
			$("#detailDiv").html(result);
			$.parser.parse();
		});
	}
	
	function saveUserDetail(){
		$('#ff').form('submit', {
			url:'${ctx}/admin/app/app-user!save.action',
			onSubmit: function(){
				var flag=$('#ff').form('validate');
				return flag;
			},
			success:function(data){
				$('#userTree').tree('reload');
				$('#appOrgId').combotree('reload');
			}
		});
	} 
	function deleteUserDetail(id){

		$.messager.confirm('Confirm','Are you sure?',function(r){
			if (r){
				$('body').mask('删除用户...');
 				$.post("${ctx}/admin/app/app-user!delete.action",{id:id} , function(result) {
					$('body').unmask();
					if('true' == result){
						$("#detailDiv").html('');
						var node = $('#userTree').tree('getSelected');
						if( node == null || node.id == '' || node.id == '0'){
							return null;
						}else{
							$('#userTree').tree('remove', node.target);
						}
					}else{
						alert('delete : ' + result);
					}
				});
				
			}
		});
	}
</script>
</body>
</html>