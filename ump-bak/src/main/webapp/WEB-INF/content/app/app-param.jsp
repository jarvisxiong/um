<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>全局参数</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/resources/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/resources/js/jquery-easyui/themes/icon.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery-1.4.4.min.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui/jquery.easyui.min.js"  ></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/ConvertUtil.js" ></script>
</head>
<body class="easyui-layout">
<div region="north" title="搜索" icon="icon-search" style="height:70px;padding-top:5px;overflow:hidden;" border="false">
	<form id="searchForm" method="post">
		<table>
			<tr>
				<td style="width:60px;">参数代码:</td>
				<td><input name="filter_EQS_paramCd" type="text" value="${filter_EQS_paramCd}"></input></td>
				<td style="width:60px;">参数名称:</td>
				<td><input name="filter_LIKES_paramName" type="text" value="${filter_LIKES_paramName}" ></input></td>
				<td style="width:50px;">参数值:</td>
				<td><input name="filter_LIKES_paramValue" type="text" value="${filter_LIKES_paramValue}" ></input></td>
				<td style="width:60px;">是否默认:</td>
				
				<td><a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="Convert.search('searchForm','tt');">搜索</a></td>
					<%--
					<a href="#" id="btn" iconCls="icon-cancel" class="easyui-linkbutton" onclick="cleanSearchCon()">清空</a>
					 --%> 
				</td> 
			</tr>
		</table>
	</form> 
</div>
<div region="center" border="false">
	<table id="tt" fit="true"
			title="全局参数管理" iconCls="icon-edit" singleSelect="true"
			idField="appParamId" url="${ctx}/app/app-param!list.action">
	</table>
</div>
<script type="text/javascript">
	$(function(){
		var lastIndex;
		$('#tt').datagrid({
			pagination:true,
			pageSize:50,
			pageList:[50,40,30,20,10],
			columns:[[
				{field:'paramCd',title:'参数代码',editor:{type:'text'}, sortable:true, width:280,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'paramName',title:'参数名称',editor:{type:'text'}, sortable:true, width:230,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'paramValue',title:'参数值',editor:{type:'text'}, sortable:true, width:200,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'defaultFlg',title:'是否默认',editor:{type:'checkbox',options:{on:'1',off:'0'}}, sortable:true, align: 'center',width:60,
					formatter:function(value,row,index){
						if(value=='1')
							return '是';
						else 
							return '否';
					}
				},
				{field:'remark',title:'备注',editor:{type:'text'},width:130,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'sequenceNo',title:'显示序号',editor:{type:'text'}, sortable:true, align: 'right', width:60},
				{field:'creator',title:'创建人', sortable:true, width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'createdDate',title:'创建时间', sortable:true, width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'updator',title:'更新人', sortable:true, width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'updatedDate',title:'更新时间', sortable:true, width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				}
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					$('#tt').datagrid('endEdit', lastIndex);
					$('#tt').datagrid('appendRow',{
						paramCd:'',
						paramName:'',
						remark:'',
						defaultFlg:'1',//默认值
						sequenceNo:'0'//默认值
					});
					var lastIndex = $('#tt').datagrid('getRows').length-1;
					$('#tt').datagrid('beginEdit', lastIndex);
				}
			},'-',{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					saveEdit();
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					var row = $('#tt').datagrid('getSelected');
					if(row){
						$.messager.confirm('提示','确认要删除该记录吗?',function(t){
							if(t){
								//TODO:如果该记录被引用,是否强制不允许删除?
								$.post("${ctx}/app/app-param!delete.action",{id:row.appParamId} , function(result) {
									var rObj = eval(result);
									if(rObj.success){
										$('#tt').datagrid('reload');
									}else{
										alert(rObj.failure);
									}
								});
							}
						});
					}
				}
			},'-'],
			onClickRow:function(rowIndex){
				if (lastIndex != rowIndex){
					$('#tt').datagrid('endEdit', lastIndex);
					$('#tt').datagrid('beginEdit', rowIndex);
				}
				lastIndex = rowIndex;
			}
		});
	});
	function saveEdit(){
		var row = $('#tt').datagrid('getSelected');
		if (row){
			var index = $('#tt').datagrid('getRowIndex', row);
			$('#tt').datagrid('endEdit', index);
		}
		$.post("${ctx}/app/app-param!saveBatch.action",Convert.ToSaveParam("tt") , function(result) {
			var rObj = eval(result);
			if(rObj.success){
				$('#tt').datagrid('reload');
			}else{
				alert(rObj.failure);
			}
		});
	}
	//清空搜索条件
	function cleanSearchCon(){
		$('#filter_EQS_paramCd').val('');
		$('#filter_EQS_paramName').val('');
		$('#filter_EQS_paramValue').val('');
		$('#filter_EQS_defaultFlg').val('');
	}
</script>
</body>
</html>