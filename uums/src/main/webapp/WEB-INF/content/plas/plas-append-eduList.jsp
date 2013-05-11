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
				<td>接入应用业务编号2:</td>
				<td><input name="number" type="text"></input></td>
				<td>接入应用中文名称2:</td>
				<td><input name="name" type="text" ></input></td>
				<td><a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="">搜索</a></td> 
			</tr>
		</table>
	</form> 
</div>
	<!--<table id="educational_info" fit="true"
			title="学历及学位" singleSelect="true" rownumbers="true"
			idField="appPageId"></table>
	-->
	<table id="educational_info" fit="true" title="学历及学位" style="overflow-x:auto;" singleSelect="true" rownumbers="true" idField="plasAppId">
	</table>
	<br/>
	<table id="profession_info" title="职业资格信息(专业证书)" style="overflow-x:auto;" fit="true" singleSelect="true" rownumbers="true" idField="plasAppId">
	</table>
	
<div id="w" class="easyui-window" closed="true" style="width:650px;height:500px;">
</div>

<script type="text/javascript">
$(function(){
	init();
});
function editrow(id){
	$('#w').window({
		title: '页面管理',
		modal:true,
		closed: true,
		width:"auto",
		height:400,
		collapsible : false,
		minimizable : false,
		cache:false,
		iconCls:"icon-save",
		onClose:function(){
			$('#baseInfo_2').datagrid('reload');
		}
	});
	var url="${ctx}/plas/plas-append!eduInput.action?id="+id;
	$('#w').window({href:url});
	$('#w').window("open");
}
function init(){
	$('#educational_info').datagrid({
		pagination:true,
		collapsible:true,
		rownumbers:true,
		height:300,
		nowrap: false,
		collapsible:true,
		pageSize:10,
		pageList:[50,40,30,20,10],
		columns:[[
			{field:'uiid',title:'学历',sortable:true, align: 'center', width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'userBizCd',title:'学位',sortable:true,align: 'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'userName',title:'专业',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'email',title:'入学时间',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'sexCd',title:'毕业学校',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}  
			},{field:'custLoginName',title:'学习形式',sortable:true,align: 'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'statusCd',title:'教育类型',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'serviceStatusCd',title:'学制（年）',sortable:true,align:'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'updateDate',title:'学位授予日期', sortable:true, align: 'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'updateDate',title:'毕业日期', sortable:true, align: 'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'updateDate',title:'最高学位', sortable:true, align: 'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'updateDate',title:'最高学历', sortable:true, align: 'center',width:80,
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
			text:'新增',
			iconCls:'icon-add',
			handler:function(){
				editrow('1');
			}
		},'-'],
		onLoadSuccess:function(){
			$('a.easyui-linkbutton').linkbutton();
		},
		onDblClickRow:function(rowIndex,row){
			searchrow(row.plasAcctId);
		}
	});
	/*****************************************************/
	$('#profession_info').datagrid({
		pagination:true,
		collapsible:true,
		rownumbers:true,
		width:"auto",
		height:300,
		nowrap: false,
		pageSize:10,
		pageList:[50,40,30,20,10],
		columns:[[
			{field:'uiid',title:'职业资格名称',sortable:true, align: 'center', width:120,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'userBizCd',title:'职业资格级别',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'userName',title:'获取时间',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'email',title:'授予单位',sortable:true,align: 'center',width:120,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},{field:'sexCd',title:'聘任或注册单位',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}  
			},{field:'custLoginName',title:'聘任或注册年月',sortable:true,align: 'center',width:100,
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
			text:'新增',
			iconCls:'icon-add',
			handler:function(){
				editrow('2');
			}
		},'-'],
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