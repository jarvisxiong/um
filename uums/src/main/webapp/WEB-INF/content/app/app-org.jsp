<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>机构查询</title>
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
<div region="north" title="查询条件"  style="height:85px;padding:5px;" border="false">
	<form id="searchForm"  method="post">
		<table>
			<tr>
				<td>机构编号:</td>  
				<td><input name="filter_LIKES_orgBizCd" type="text" value="${filter_LIKES_orgBizCd}"></input></td>
				<td>机构名称:</td>
				<td><input name="filter_LIKES_orgName" type="text" value="${filter_LIKES_orgName}"></input></td><td> 是否启用 :</td>
				<td><s:select list="mapEnableFlg" listKey="key" listValue="value" id="filter_EQB_enableFlg" name="filter_EQB_enableFlg"/></td> 
				<td><a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="customSearch()">查询</a></td> 
			</tr>
		</table>
	</form>
</div>
<div id="t2" region="center" style="padding:5px;" border="false">
	<table id="tt" fit="true"
			title="机构列表(显示'已启用'和'禁用'的机构,不包含'已删除'的机构)" singleSelect="true" rownumbers="true"
			idField="appUserId" url="${ctx}/admin/app/app-org!list.action">
	</table>
</div>
<div id="w" class="easyui-window" closed="true" style="width:400px;height:400px;padding:5px;background: #fafafa;">
</div>

<script type="text/javascript">
	var lastIndex;
	var inputUrl;
	$(function(){
		$('#tt').datagrid({
			pagination:true,
			columns:[[
				{field:'orgBizCd',title:'机构编号',sortable:true,width:80},
				{field:'orgName',title:'机构名称',width:200},
				{field:'remark',title:'备注',width:180},
				{field:'updatedDate',title:'更新时间',width:80},
				{field:'action',title:'操作',width:100,align:'center',
					formatter:function(value,row,index){
						var id =row.appOrgId;
						var e = '<a href="#" onclick=editrow("'+id+'") >修改</a> ';
						var d = '<a href="#" onclick=deleterow('+index+',"'+id+'") >删除</a>';
						return e+d;
					}
				},
				{field:'enableFlg',title:'是否启用',width:140,align:'center',
					formatter:function(value,row,index){
						var id =row.appOrgId;
						var enableFlg =row.enableFlg;
						var e = (enableFlg=='false'||enableFlg=='')?'已禁用, 您可以&nbsp;<a href="#" onclick=enableOrgRow("'+id+'") >启用</a> ':'';
						var d = (enableFlg=='true')?'已启用, 您可以&nbsp;<a href="#" onclick=disableOrgRow("'+id+'") >禁用</a>':'';
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
			title: '机构编辑',
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
		$.post("${ctx}/admin/app/app-org!validateEnableDelete.action",{id:id} , function(result) {
			if('true' == result){
				$.messager.confirm('Confirm','Are you sure?',function(r){
					if (r){
						$('#tt').datagrid('deleteRow', index);
						var nextSelect=index>0?index-1:0;
						$('#tt').datagrid('selectRow', nextSelect);
						if(id){
							$.post("${ctx}/admin/app/app-org!delete.action",{id:id} , function(result) {
								if('true' == result){
									$('#tt').datagrid('acceptChanges');
								}else{
									//alert(result);
									alert('机构下存在用户,不能删除机构!');
								}
							});
						}else{
							$('#tt').datagrid('acceptChanges');
						}
					}else{
						//alert('删除失败');
					}
				});
			}else{
				alert(result);
			}
		});
	}
	
	function editrow(id){
		var url="${ctx}/admin/app/app-org!input.action?id="+id;
		$('#w').window({href:url});
		$('#w').window("open");
		//$.parser.parse();
	}
	function clearSearch(){
		$('#searchForm').form('clear');
	}

	function enableOrgRow(id){
		$("body").mask();
		$.post("${ctx}/admin/app/app-org!enableOrg.action",{id:id} , function(result) {
			if('true' == result){
				$('#tt').datagrid('reload');
			}else{
				alert(result);
			}
			$("body").unmask();
		});
	}
	function disableOrgRow(id){
		$("body").mask();
		$.post("${ctx}/admin/app/app-org!disableOrg.action",{id:id} , function(result) {
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
	function saveOrgDetail(){
		$('#ff').form('submit', {
			url:'${ctx}/admin/app/app-org!save.action',
			onSubmit: function(){
				var flag=$('#ff').form('validate');
				return flag;
			},
			success:function(data){
				$('#w').window("close");
				$('#tt').datagrid('reload');
				$('#parentId').combotree('reload');
			}
		});
	} 
</script>
</body>
</html>