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
				<td>接入应用业务编号3:</td>
				<td><input name="number" type="text"></input></td>
				<td>接入应用中文名称3:</td>
				<td><input name="name" type="text" ></input></td>
				<td><a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="">搜索</a></td> 
			</tr>
		</table>
	</form> 
</div>
	<table title="合同信息" id="contact_info" style="overflow:auto;padding:5px;">
	</table>
	<br/>
	<table title="工作经历" id="workExperience_info" style="overflow:auto;padding:5px;">
	</table>
	<br/>
	<table title="职称信息(人事局)" id="title_info" style="overflow:auto;padding:5px;">
	</table>
	
<script type="text/javascript">
$(function(){
	init();
});
function editrow(id){
	/*$('#w').window({
		title: '页面管理',
		modal:true,
		closed: true,
		collapsible : false,
		minimizable : false,
		cache:false,
		iconCls:"icon-save",
		onClose:function(){
			$('#baseInfo_2').datagrid('reload');
		}
	});*/
	var url="${ctx}/plas/plas-append!input.action";
	$('#w').window({href:url});
	$('#w').window("open");
}
function init(){
	$('#contact_info').datagrid({
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
			{field:'uiid',title:'甲方单位',sortable:true, align: 'center', width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'userBizCd',title:'合同编号',sortable:true,align: 'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'userName',title:'合同类别',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'email',title:'合同模板',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'sexCd',title:'合同期限类型',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}  
			},{field:'custLoginName',title:'签订日期',sortable:true,align: 'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'statusCd',title:'生效日期',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'serviceStatusCd',title:'解除日期',sortable:true,align:'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'updateDate',title:'终止日期', sortable:true, align: 'center',width:80,
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
				editrow();
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
	$('#workExperience_info').datagrid({
		pagination:true,
		collapsible:true,
		rownumbers:true,
		width:"auto",
		height:"auto",
		nowrap: false,
		pageSize:10,
		pageList:[50,40,30,20,10],
		columns:[[
			{field:'uiid',title:'开始日期',sortable:true, align: 'center', width:120,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'userBizCd',title:'结束日期',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'userName',title:'单位名称',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'email',title:'任职部门',sortable:true,align: 'center',width:120,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'sexCd',title:'担任职务',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}  
			},{field:'custLoginName',title:'任职职位',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'custLoginName',title:'月基本工资',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'custLoginName',title:'离职原因',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'custLoginName',title:'证明人',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'custLoginName',title:'证明人电话',sortable:true,align: 'center',width:100,
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
	/**********************************************/
	$('#title_info').datagrid({
		pagination:true,
		collapsible:true,
		rownumbers:true,
		width:"auto",
		height:"auto",
		nowrap: false,
		pageSize:10,
		pageList:[50,40,30,20,10],
		columns:[[
			{field:'uiid',title:'职称',sortable:true, align: 'center', width:120,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'userBizCd',title:'职称类别',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'userName',title:'职称级别',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'email',title:'最高职称',sortable:true,align: 'center',width:120,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'sexCd',title:'评定单位',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}  
			},{field:'custLoginName',title:'职称授予时间',sortable:true,align: 'center',width:100,
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