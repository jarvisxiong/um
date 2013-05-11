<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>页面管理</title>
	<%@ include file="/common/global.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script>
	
</head>
<body class="easyui-layout">
<div region="north" title="查询"  style="height:70px;padding-top:5px;overflow:hidden;" border="false">
	<form id="searchForm"  method="post" onsubmit="return false;">
		<table>
			<tr>
				<td>页面内码:</td>
				<td><input name="filter_EQS_pageCd" type="text" value="${filter_EQS_pageCd}"/></td>
				<td>页面名称:</td>
				<td><input name="filter_LIKES_pageName" type="text" value="${filter_LIKES_pageName}"/></td>
				<td>页面路径:</td>
				<td><input name="filter_LIKES_pagePath" type="text" value="${filter_LIKES_pagePath}"/></td>
				<td>页面状态:</td>
				<td><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapPageStatus()" name="filter_EQS_pageStatusCd" listKey="key" listValue="value"/></td>
				<td><a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="Convert.search('searchForm','tt');">查询</a></td> 
			</tr>
		</table>
	</form>
</div>
<div region="center" border="false">
	<table id="tt" fit="true"
			title="页面列表" singleSelect="true" rownumbers="true"
			idField="appPageId" url="${ctx}/app/app-page!list.action">
	</table>
</div>
<div id="w" class="easyui-window" style="width:650px;height:500px;">
</div>
<script type="text/javascript">
	$(function(){
		init();
	});
	
	function init(){
		$('#tt').datagrid({
			pagination:true,
			pageSize:50,
			pageList:[50,40,30,20,10],
			columns:[[
				{field:'pageCd',title:'内码',align:'right',width:40},
				{field:'pageName',title:'页面名称',sortable:true,width:150,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'pagePath',title:'页面路径',sortable:true,width:240,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'pageStatusCd',title:'是否可用',sortable:true,align:'center',width:70,
					formatter:function(value,row,index){
						//TODO:翻译成中文
						if(value){
							return '是';
						}else{
							return '否';
						}
					}
				},
				{field:'createdDate',title:'创建时间', sortable:true, width:70,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'updatedDate',title:'更新时间', sortable:true, width:70,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'action',title:'操作',width:150,align:'center',
					formatter:function(value,row,index){
						var id =row.appPageId;
						var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+id+'") >编辑</a> ';
						var d = '<a href="#" class="easyui-linkbutton" onclick=deleterow('+index+',"'+id+'") >删除</a>';
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
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
		$('#w').window({
			title: '页面管理',
			modal:true,
			closed: true,
			collapsible : false,
			minimizable : false,
			cache:false,
			iconCls:"icon-save",
			onClose:function(){
				$('#tt').datagrid('reload');
			}
		});
	}

	function deleterow(index,id){
		$.messager.confirm('确认','你确认要删除该记录吗?',function(r){
			if (r){
				$('#tt').datagrid('deleteRow', index);
				var nextSelect=index>0?index-1:0;
				$('#tt').datagrid('selectRow', nextSelect);
				if(id){
					$.post("${ctx}/app/app-page!delete.action",{id:id} , function(result) {
						$('#tt').datagrid('acceptChanges');
					});
				}else{
					$('#tt').datagrid('acceptChanges');
				}
			}
		});
	}

	function editrow(id){
		var url="${ctx}/app/app-page!input.action?id="+id;
		$('#w').window({href:url});
		$('#w').window("open");
	}
</script>
</body>
</html>