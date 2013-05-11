<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>字典管理</title>
	<%@ include file="/common/global.jsp" %>
	<link href="${ctx}/theme/default/css/admin/style.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/js/jquery-easyui/themes/icon.css"  rel="stylesheet" type="text/css" >
	<link href="${ctx}/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
	<script src="${ctx}/js/jquery-lasted.min.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/common.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/ConvertUtil.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	
	<link href="${ctx}/js/loadMask/jquery.loadmask.css" rel="stylesheet" type="text/css" />
	<script src="${ctx}/js/loadMask/jquery.loadmask.min.js" type="text/javascript"></script>
</head>
<body class="easyui-layout">
<div region="north" title="查询"  style="height:85px;padding:5px;" border="false">
	<form id="searchForm"  method="post">
		<table>
			<tr>
				<td>帐号:</td>
				<td><input name="filter_EQS_userCode" type="text" value="${filter_EQS_userCode}"></input></td>
				<td>姓名:</td>
				<td><input name="filter_LIKES_userName" type="text" value="${filter_LIKES_userName}" ></input></td>
				<%-- demo 
				<td><select id="userSelect" name="userCd" style="width:250px;"></select></td>
				 --%>
				<td><a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="customSearch()">查询</a></td> 
			</tr>
		</table>
	</form>
</div>
<div region="center" style="padding:5px;" border="false">
	<table id="tt" fit="true"
			title="用户列表(显示'已启用'和'禁用'的用户,不包含'已删除'的用户)" singleSelect="true" rownumbers="true"
			idField="appUserId" url="${ctx}/admin/app/app-user!list.action">
	</table>
</div>
<div id="w" class="easyui-window" closed="true" style="width:400px;height:400px;padding:5px;background: #fafafa;">
</div>
<script>
	var lastIndex;
	var inputUrl;
	$(function(){
		//Convert.initUserSelect('userSelect');
		$('#tt').datagrid({
			pagination:true,
			columns:[[
				{field:'userCode',title:'帐号',sortable:true,width:80},
				{field:'userName',title:'姓名',width:100},
				{field:'remark',title:'备注',width:180},
				{field:'updatedDate',title:'更新时间',width:80},
				{field:'action',title:'操作',width:150,align:'center',
					formatter:function(value,row,index){
						var id =row.appUserId;
						var e = '<a href="#" onclick=editrow("'+id+'") >修改</a> ';
						var d = '<a href="#" onclick=deleterow('+index+',"'+id+'") >删除</a>';
						var r = '&nbsp;<a href="#" onclick=resetUserPwd("'+id+'") >重置密码</a>';
						return e+d+r;
					}
				},
				{field:'enableFlg',title:'是否启用',width:140,align:'center',
					formatter:function(value,row,index){
						var id =row.appUserId;
						var enableFlg =row.enableFlg;
						var e = (enableFlg=='false'||enableFlg=='')?'已禁用, 您可以&nbsp;<a href="#" onclick=enableUserRow("'+id+'") >启用</a> ':'';
						var d = (enableFlg=='true')?'已启用, 您可以&nbsp;<a href="#" onclick=disableUserRow("'+id+'") >禁用</a>':'';
						return e+d;
					}
				}
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					editrow('');
				}
			}]
		});
		$('#w').window({
			title: '用户管理',
			modal: true,
			closed: true,
			cache: false,
			collapsible : false,
			minimizable : false,
			iconCls:"icon-save",
			onClose:function(){
				//刷新数据
				//$('#w').window("destroy");
				$('#tt').datagrid('reload');
			}
		});
	});
	function deleterow(index,id){
		 
		$.messager.confirm('Confirm','Are you sure?',function(r){
			if (r){
				$('#tt').datagrid('deleteRow', index);
				var nextSelect=index>0?index-1:0;
				$('#tt').datagrid('selectRow', nextSelect);
				if(id){
					$.post("${ctx}/admin/app/app-user!delete.action",{id:id} , function(result) {
						if('true' == result){
							$('#tt').datagrid('acceptChanges');
						}else{
							alert(result);
						}
					});
				}else{
					$('#tt').datagrid('acceptChanges');
				}
			}
		});
	}
	
	function editrow(id){
		var url="${ctx}/admin/app/app-user!input.action?id="+id;
		$('#w').window({href:url});
		$('#w').window("open");
		//$.parser.parse();
	}
	function clearSearch(){
		$('#searchForm').form('clear');
	}
	function enableUserRow(id){
		$("body").mask('process..');
		$.post("${ctx}/admin/app/app-user!enableUser.action",{id:id} , function(result) {
			if('true' == result){
				$('#tt').datagrid('reload');
			}else{
				alert(result);
			}
			$("body").unmask();
		});
	}
	function disableUserRow(id){
		$("body").mask('process..');
		$.post("${ctx}/admin/app/app-user!disableUser.action",{id:id} , function(result) {
			$("body").unmask();
			if('true' == result){
				$('#tt').datagrid('reload');
			}else{
				alert(result);
			}
			$("body").unmask();
		});
	}
	function customSearch(){
		var param = Convert.getJson4Form('searchForm');
		$('#tt').datagrid('options').queryParams = param;
		$('#tt').datagrid('reload');
	}

	function saveUserDetail(oldValue){
		var newValue = $("#userCode").val();
		if('' == $.trim(newValue)){
			$("#userCodeTip").hide();
			return;
		}
		//校验唯一性
		var flag = Convert.codeValidate('${ctx}/admin/app/app-user!isUserCdExists.action',{oldValue: oldValue, newValue: newValue});
		if(flag){
			$("#userCodeTip").hide();
		}else{
			$("#userCodeTip").html('帐号重复').show();
			return;
		}
		
		$('#ff').form('submit', {
			url:'${ctx}/admin/app/app-user!save.action',
			onSubmit: function(){
				var flag=$('#ff').form('validate');
				return flag;
			},
			success:function(data){
				$('#w').window("close");
				$('#appOrgId').combotree('reload');
			}
		});
	}
	function deleteUserDetail(id){
		$.messager.confirm('Confirm','Are you sure?',function(r){
			if (r){
				$.post("${ctx}/admin/app/app-user!delete.action",{id:id} , function(result) {
					if('true' == result){
						$('#tt').datagrid('reload');
						$('#w').window("close");
					}else{
						alert(result);
					}
				});
			}
		});
	}
	
	function resetUserPwd(id){
		$.messager.confirm('Confirm','Are you sure reset password?',function(r){
			if (r){
				$.post("${ctx}/admin/app/app-user!resetPassword.action",{id:id} , function(result) {
					if('true' == result){
						alert('Congratulation! Password has been reseted!');
					}else{
						alert(result);
					}
				});
			}
		});
	}
</script>
</body>
</html>