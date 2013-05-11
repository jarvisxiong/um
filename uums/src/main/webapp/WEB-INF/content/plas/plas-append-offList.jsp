<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>应用管理</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js" ></script>
	
</head>
<body class="easyui-layout">
<div region="center" class="easyui-panel"  title="任职信息" style="width:auto;padding-top:5px;" 
	 border="true"><!-- closable="true" collapsible="true" minimizable="true" maximizable="true" -->
	<form id="searchForm" method="post" onsubmit="return false;">
		<table>
			<tr>
				<td>接入应用业务编号:</td>
				<td><input name="number" type="text"></input></td>
				<td>接入应用中文名称:</td>
				<td><input name="name" type="text" ></input></td>
				<td><a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="">搜索</a></td> 
			</tr>
		</table>
	</form>
	
	<table title="任职历史" id="baseInfo_2" style="overflow:auto;padding:5px;">
	</table>
	<br/>
	<table title="借调信息" id="baseInfo_3" style="overflow:auto;padding:5px;">
	</table>
	<br/>
	<table title="职等历史" id="baseInfo_4" style="overflow:auto;padding:5px;">
	</table>
	<br/>
	<table title="员工状态变动历史" id="baseInfo_5" style="overflow:auto;padding:5px;">
	</table>
<div id="w" class="easyui-window" closed="true" style="width:650px;height:500px;">
</div>
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
		collapsible : false,
		minimizable : false,
		cache:false,
		iconCls:"icon-save",
		onClose:function(){
			$('#baseInfo_2').datagrid('reload');
		}
	});
	var url="${ctx}/plas/plas-append!input.action?id="+id;
	$('#w').window({href:url});
	$('#w').window("open");
}
function init(){
	//$('#eec').html('<div style="height:100px;width:100%;">&nbsp;</div>');
	//$("#eec").mask('正在载入任职信息,请稍候...');
	$('#baseInfo_2').datagrid({
		pagination:true,
		collapsible:true,
		rownumbers:true,
		width:"auto",
		height:300,
		nowrap: false,
		collapsible:true,
		pageSize:10,
		pageList:[50,40,30,20,10],
		columns:[[
			{field:'uiid',title:'职位',sortable:true, align: 'center', width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},						   
			{field:'userBizCd',title:'职务',sortable:true,align: 'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},						   
			{field:'userName',title:'行政组织',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},						   
			{field:'email',title:'开始日期',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},						   
			{field:'sexCd',title:'结束日期',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}  
			},
			{field:'custLoginName',title:'历史职位',sortable:true,align: 'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},						   
			{field:'statusCd',title:'历史部门',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'serviceStatusCd',title:'历史职务',sortable:true,align:'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'updateDate',title:'主要职务', sortable:true, align: 'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'opt',title:'操作',width:60,align:'center', rowspan:2,
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
				editrow('');
			}
		},'-'],
		onLoadSuccess:function(){
			$('a.easyui-linkbutton').linkbutton();
		}
	});
	/*****************************************************/
	$('#baseInfo_3').datagrid({
		pagination:true,
		collapsible:true,
		rownumbers:true,
		width:"auto",
		height:300,
		nowrap: false,
		pageSize:10,
		pageList:[50,40,30,20,10],
		columns:[[
			{field:'uiid',title:'借调调出职位',sortable:true, align: 'center', width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},						   
			{field:'userBizCd',title:'借调调出所属行政组织',sortable:true,align: 'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},						   
			{field:'userName',title:'借调调出职务',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},						   
			{field:'email',title:'借调调出职级',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},						   
			{field:'sexCd',title:'借调调出职等',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}  
			},
			{field:'custLoginName',title:'借调跳入职位',sortable:true,align: 'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},						   
			{field:'statusCd',title:'借调调入所属行政组织',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'serviceStatusCd',title:'借调调入职务',sortable:true,align:'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'updateDate',title:'借调调入职级', sortable:true,align: 'center', width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'opt',title:'借调调入职等',width:60,align:'center',
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'opt',title:'拟借调结束日期',width:60,align:'center',
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'opt',title:'借调调入日期',width:60,align:'center',
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'opt',title:'借调调回日期',width:60,align:'center',
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'opt',title:'借调状态',width:60,align:'center',
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'opt',title:'操作',width:60,align:'center',
				formatter:function(value,row,index){
					var s = '<a href="#" class="easyui-linkbutton" onclick=searchrow("'+row.plasAcctId+'") >查看</a> ';
					return s;															
				}
			}				
		]],		
		onLoadSuccess:function(){
			$('a.easyui-linkbutton').linkbutton();
		},
		onDblClickRow:function(rowIndex,row){
			searchrow(row.plasAcctId);
		}
	});
	/*****************************************************/
	$('#baseInfo_4').datagrid({
		pagination:true,
		collapsible:true,
		rownumbers:true,
		width:"auto",
		height:300,
		nowrap: false,
		pageSize:10,
		pageList:[50,40,30,20,10],
		columns:[[
			{field:'uiid',title:'职等',sortable:true, align: 'center', width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},						   
			{field:'userBizCd',title:'职等开始日期',sortable:true,align: 'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},						   
			{field:'userName',title:'职等结束日期',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},						   
			{field:'email',title:'职级',sortable:true,align: 'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},						   
			{field:'sexCd',title:'职位',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'custLoginName',title:'职务',sortable:true,align: 'center',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'opt',title:'操作',width:60,align:'center',
				formatter:function(value,row,index){
					var s = '<a href="#" class="easyui-linkbutton" onclick=searchrow("'+row.plasAcctId+'") >查看</a> ';
					return s;															
				}
			}				
		]],		
		onLoadSuccess:function(){
			$('a.easyui-linkbutton').linkbutton();
		},
		onDblClickRow:function(rowIndex,row){
			searchrow(row.plasAcctId);
		}
	});
	/*****************************************************/
	$('#baseInfo_5').datagrid({
		pagination:true,
		collapsible:true,
		rownumbers:true,
		width:"auto",
		height:300,
		nowrap: false,
		pageSize:10,
		pageList:[50,40,30,20,10],
		columns:[[
			{field:'uiid',title:'员工编码',sortable:true, align: 'center', width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},						   
			{field:'userBizCd',title:'职位',sortable:true,width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},						   
			{field:'userName',title:'行政组织',sortable:true,width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},						   
			{field:'email',title:'原员工状态',sortable:true,width:100,
				formatter:function(value,row,index){
					switch(value){
						case '0': return '未入职';
						case '1': return '在职';
						case '2': return '离职';
						default:'';
					} 
					return '';
				}
			},						   
			{field:'sexCd',title:'状态转换日期',sortable:true,align:'center',width:100,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}  
			},
			{field:'opt',title:'操作',width:100,align:'center',
				formatter:function(value,row,index){
					var s = '<a href="#" class="easyui-linkbutton" onclick=searchrow("'+row.plasAcctId+'") >查看</a> ';
					return s;									
				}
			}				
		]],		
		onLoadSuccess:function(){
			$('a.easyui-linkbutton').linkbutton();
		},
		onDblClickRow:function(rowIndex,row){
			searchrow(row.plasAcctId);
		}
	});
	//$("#baseInfo_1").hide();$("#baseInfo_2").show();
}
</script>
</body>
</html>