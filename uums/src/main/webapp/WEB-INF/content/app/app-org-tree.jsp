<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>机构管理</title>
	<%@ include file="/common/global.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<script src="${ctx}/js/jquery-lasted.min.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/common.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/ConvertUtil.js"  type="text/javascript" ></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/loadMask/jquery.loadmask.css">
	<script src="${ctx}/js/loadMask/jquery.loadmask.min.js"  type="text/javascript" ></script>
</head>
<body class="easyui-layout">
<div region="north" style="height:35px;padding-top:5px;padding-left:10px;" border="false">
	<div class="toolbar">
		<a id="btnAddOrg" href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="addOrgNode();">新增机构</a>
		<a id="btnDeleteOrg" href="#" class="easyui-linkbutton" disabled="true" iconCls="icon-remove" onclick="deleteOrgNode();">删除机构</a>
		<span>提示:左边树只显示所有'已启用'的机构</span>
	</div>
</div>
<div region="west" title="机构管理" split="true"  style="width:250px;">
	<ul id="orgTree"></ul>
</div>
<div  region="center" title="机构明细" split="true"  style="width:250px;" id="detailDiv">

</div>
<script type="text/javascript">
	$(function(){
		$('#orgTree').tree({
			url: '${ctx}/admin/app/app-org!loadOrgTreeRootJson.action',
			animate:true,
			onClick:function(node){
				if(node == null || node.id == '0'){
					$("#btnDeleteOrg").linkbutton('disable');
					return;
				}
				$("#btnDeleteOrg").linkbutton('enable');
				editOrgDetail(node.id,'');
			}
		}); 
	}); 
	
	
	//新增机构
	function addOrgNode(){
		var node = $('#orgTree').tree('getSelected');
		if( node == null || node.id == '' || node.id == '0'){
			editOrgDetail('','');
		}else{
			editOrgDetail('',node.id);
		}
	}
	//删除机构
	function deleteOrgNode(){
		var node = $('#orgTree').tree('getSelected');
		if( node == null || node.id == '' || node.id == '0'){
			return;
		}
		deleteOrgDetail(node.id);
	}
	
	//删除机构明细
	function deleteOrgDetail(id){
		
		var node = $('#orgTree').tree('getSelected');
		if( node == null || node.id == '' || node.id == '0'){
			return null;
		}
		
		$('body').mask('校验是否有下属员工...');
		var url='${ctx}/admin/app/app-org!validateEnableDelete.action';
		var data = {id: node.id};
		$.post(url, data, function(result) {
			$('body').unmask();
			if('true' == result){
				$.messager.confirm('Confirm','Are you sure?',function(r){
					if(r){
						$('body').mask('删除机构...');
						var url='${ctx}/admin/app/app-org!delete.action';
						var data = {id: id};
						$.post(url, data, function(result) {
							$('body').unmask();
							if('true' == result){
								$('#orgTree').tree('remove', node.target);
								$("#detailDiv").html('');
							}else{
								alert('delete : ' + result);
							}
						});
					}
				});
			}else{
				alert('不能删除,存在有效下属员工!');
				$("#btnDeleteOrg").linkbutton('disable');
				$("#btnRemoveOrgDetail").linkbutton('disable');
			}
		}); 
	}
	
	//private 
	function editOrgDetail(id, parentId){
		$('body').mask('正在加载...');
		if(!id){
			id = '';
		}
		if(!parentId){
			parentId = '';
		}
		var url='${ctx}/admin/app/app-org!input.action';
		var data = {id: id, parentId: parentId};
		$.post(url, data, function(result) { 
			$("#detailDiv").html(result);
			$.parser.parse();
			$('body').unmask();
		});
	}

	function saveOrgDetail(){
		$('#ff').form('submit', {
			url:'${ctx}/admin/app/app-org!save.action',
			onSubmit: function(){
				var flag=$('#ff').form('validate');
				return flag;
			},
			success:function(data){
				$('#orgTree').tree('reload');
				$('#parentId').combotree('reload');
			}
		});
	} 
</script>
</body>
</html>