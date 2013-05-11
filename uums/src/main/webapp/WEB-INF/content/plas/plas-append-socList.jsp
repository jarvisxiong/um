<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>教育情况</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js" ></script>
	
</head>
<body class="easyui-layout">
<div region="north" title="搜索" icon="icon-search" fit="true" style="height:35px;padding-top:5px;overflow:hidden;" border="false">
	<form id="searchForm" method="post" onsubmit="return false;">
		<table>
			<tr>
				<td>接入应用业务编号4:</td>
				<td><input name="number" type="text"></input></td>
				<td>接入应用中文名称4:</td>
				<td><input name="name" type="text" ></input></td>
				<td><a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="">搜索</a></td> 
			</tr>
		</table>
	</form> 
</div>
	<table title="社会关系" id="soc_info" style="overflow:auto;padding:5px;">
	</table>
	<br/>
	<table title="紧急联系人" id="linkman_info" style="overflow:auto;padding:5px;">
	</table>
	<br/>
	<table title="亲属回避关系" id="relatives_info" style="overflow:auto;padding:5px;">
	</table>
<script type="text/javascript">
$(function(){
	init();
});
function init(){
	$('#soc_info').datagrid({
		pagination:true,
		collapsible:true,
		rownumbers:true,
		width:"auto",
		height:"auto",
		nowrap: false,
		collapsible:true,
		pageSize:10,
		pageList:[50,40,30,20,10],
		columns:[[
			{field:'uiid',title:'姓名',sortable:true, align: 'center', width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'userBizCd',title:'与本人关系',sortable:true,align: 'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'userName',title:'工作单位',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'email',title:'单位联系电话',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'sexCd',title:'学历',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}  
			},{field:'custLoginName',title:'专业技术职务',sortable:true,align: 'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'statusCd',title:'人员现状',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'opt',title:'操作',width:60,align:'center', rowspan:2,
				formatter:function(value,row,index){
					var s = '<a href="#" class="easyui-linkbutton" onclick=searchrow("'+row.plasAcctId+'") >查看</a> ';
					return s;															
				}
			}				
		]],
		toolbar:[{
			id:'btnadd',
			text:'Add',
			iconCls:'icon-add',
			handler:function(){
				//$('#btnsave').linkbutton('enable');
				alert('add')
			}
		}],
		onLoadSuccess:function(){
			$('a.easyui-linkbutton').linkbutton();
		},
		onDblClickRow:function(rowIndex,row){
			searchrow(row.plasAcctId);
		}
	});
	/*****************************************************/
	$('#linkman_info').datagrid({
		pagination:true,
		collapsible:true,
		rownumbers:true,
		width:"auto",
		height:"auto",
		nowrap: false,
		pageSize:10,
		pageList:[50,40,30,20,10],
		columns:[[
			{field:'uiid',title:'姓名',sortable:true, align: 'center', width:120,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'userBizCd',title:'电话号码',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'userName',title:'单位电话',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'email',title:'单位名称',sortable:true,align: 'center',width:120,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'sexCd',title:'备注',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}  
			},{field:'opt',title:'操作',width:80,align:'center',
				formatter:function(value,row,index){
					var s = '<a href="#" class="easyui-linkbutton" onclick=searchrow("'+row.plasAcctId+'") >查看</a> ';
					return s;															
				}
			}				
		]],	
		toolbar:[{
			id:'btnadd',
			text:'Add',
			iconCls:'icon-add',
			handler:function(){
				//$('#btnsave').linkbutton('enable');
				alert('add')
			}
		}],	
		onLoadSuccess:function(){
			$('a.easyui-linkbutton').linkbutton();
		},
		onDblClickRow:function(rowIndex,row){
			searchrow(row.plasAcctId);
		}
	});
	/*****************************************************/
	$('#relatives_info').datagrid({
		pagination:true,
		collapsible:true,
		rownumbers:true,
		width:"auto",
		height:"auto",
		nowrap: false,
		pageSize:10,
		pageList:[50,40,30,20,10],
		columns:[[
			{field:'uiid',title:'对方姓名',sortable:true, align: 'center', width:120,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'userBizCd',title:'对方编码',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'userName',title:'所在部门',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'email',title:'职位',sortable:true,align: 'center',width:120,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'sexCd',title:'本人与对方关系',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}  
			},{field:'sexCd',title:'亲属关系类型',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}  
			},{field:'sexCd',title:'入职日期',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}  
			},{field:'sexCd',title:'记录日期',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}  
			},{field:'sexCd',title:'员工状态',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}  
			},{field:'opt',title:'操作',width:80,align:'center',
				formatter:function(value,row,index){
					var s = '<a href="#" class="easyui-linkbutton" onclick=searchrow("'+row.plasAcctId+'") >查看</a> ';
					return s;															
				}
			}				
		]],	
		toolbar:[{
			id:'btnadd',
			text:'Add',
			iconCls:'icon-add',
			handler:function(){
				//$('#btnsave').linkbutton('enable');
				alert('add')
			}
		}],	
		onLoadSuccess:function(){
			$('a.easyui-linkbutton').linkbutton();
		},
		onDblClickRow:function(rowIndex,row){
			searchrow(row.plasAcctId);
		}
	});
}
</script>
</body>
</html>