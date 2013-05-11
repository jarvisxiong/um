<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="easyui-layout" fit="true" style="padding:5px;overflow:hidden;height:570px;">	
<div region="north" title="搜索" icon="icon-search" style="height:70px;padding-top:5px;overflow:hidden;" border="false">
	<form id="searchForm" method="post" onsubmit="return false;">
		<table>
			<tr>
				<td>接入应用业务编号:</td>
				<td><input name="filter_LIKES_appBizCd" type="text"></input></td>
				<td>接入应用中文名称:</td>
				<td><input name="filter_LIKES_appChnName" type="text" ></input></td>
				<td><a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="customSearch()">搜索</a></td> 
			</tr>
		</table>
	</form> 
</div>

<div region="center" border="false" style="overflow: auto;overflow-x:auto;">
	<table id="tt" fit="true" singleSelect="true" rownumbers="true" idField="plasAppId">
	</table>
</div>
<div id="w" class="easyui-window" closed="true" style="width:650px;height:500px;">
</div>
</div>
<script type="text/javascript">
$(function(){
	var indexs = ${pages};
	init(indexs);
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
	var url="${ctx}/plas/plas-append!input.action?status=1&id="+id;
	$('#w').window({href:url});
	$('#w').window("open");
}
function init(indexs){
	if(indexs==1){
		$('#tt').datagrid({
			title:"新员工入职列表",
			pagination:true,
			url:"${ctx}/plas/plas-app!list.action",
			//pageSize:10,
			//pageList:[50,40,30,20,10],
			columns:[[{field:'appChnName',title:'员工编码',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'姓名',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appBizCd',title:'身份证号码',sortable:true,width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appTypeCd',title:'护照号码',sortable:true,width:80,formatter:function(value,row,index){
				var r = Convert.code2Name(row.appTypeCd,'appType');			
				return r;
			}},	{field:'creator',title:'性别', width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'createdDate',title:'出生日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updator',title:'职位', width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'行政组织', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'职务', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'职级', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'职等', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'入职来源', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'入职日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'生效日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'员工状态', sortable:true, width:80,ormatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'试用期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'opt',title:'操作',width:80,align:'center', rowspan:2,formatter:function(value,row,index){
				var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+row.plasAppId+'") >编辑</a> ';
				return e;											
			}}]],		
			toolbar:[{				
				id:'btnadd',
				text:'新增',
				iconCls:'icon-add',
				handler:function(){				
					editrow('1');
				}
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
	}else if(indexs==2){
		$('#tt').datagrid({
			title:"离职回岗列表",
			pagination:true,
			url:"${ctx}/plas/plas-app!list.action",
			columns:[[{field:'appChnName',title:'员工编码',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'姓名',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appBizCd',title:'身份证号码',sortable:true,width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appTypeCd',title:'原职位',sortable:true,width:80,formatter:function(value,row,index){
				var r = Convert.code2Name(row.appTypeCd,'appType');			
				return r;
			}},{field:'creator',title:'原所属行政组织', width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'createdDate',title:'原任职类型', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updator',title:'入职日期', width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'离职日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'离职原因类型', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'员工状态', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'任职类型', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'回岗原因', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'opt',title:'操作',width:80,align:'center', rowspan:2,formatter:function(value,row,index){
				var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+row.plasAppId+'") >编辑</a> ';
				return e;											
			}}]],		
			toolbar:[{				
				id:'btnadd',
				text:'新增',
				iconCls:'icon-add',
				handler:function(){				
					editrow('2');
				}
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
	}else if(indexs==3){
		$('#tt').datagrid({
			title:"新员工转正列表",
			pagination:true,
			url:"${ctx}/plas/plas-app!list.action",
			columns:[[{field:'appChnName',title:'员工编码',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'姓名',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appBizCd',title:'职位',sortable:true,width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appTypeCd',title:'所属行政组织',sortable:true,width:80,formatter:function(value,row,index){
				var r = Convert.code2Name(row.appTypeCd,'appType');			
				return r;
			}},{field:'updatedDate',title:'职级', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'职等', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'入职日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'试用期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'转正日期', sortable:true, width:80,ormatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'opt',title:'操作',width:80,align:'center', rowspan:2,formatter:function(value,row,index){
				var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+row.plasAppId+'") >编辑</a> ';
				return e;											
			}}]],		
			toolbar:[{				
				id:'btnadd',
				text:'新增',
				iconCls:'icon-add',
				handler:function(){				
					editrow('3');
				}
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
	}else if(indexs==4){
		$('#tt').datagrid({
			title:"内部异动列表",
			pagination:true,
			url:"${ctx}/plas/plas-app!list.action",
			columns:[[{field:'appChnName',title:'员工编码',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'姓名',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appBizCd',title:'职位',sortable:true,width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appTypeCd',title:'所属行政组织',sortable:true,width:80,formatter:function(value,row,index){
				var r = Convert.code2Name(row.appTypeCd,'appType');			
				return r;
			}},	{field:'creator',title:'当前职务', width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'createdDate',title:'当前职级', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updator',title:'当前职等', width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'目标职位', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'目标行政组织', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'目标职务', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'目标职级', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'目标职等', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'变更原因', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'生效日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'原职位作为兼职', sortable:true, width:80,ormatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'opt',title:'操作',width:80,align:'center', rowspan:2,formatter:function(value,row,index){
				var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+row.plasAppId+'") >编辑</a> ';
				return e;											
			}}]],		
			toolbar:[{				
				id:'btnadd',
				text:'新增',
				iconCls:'icon-add',
				handler:function(){				
					editrow('4');
				}
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
	}else if(indexs==5){
		$('#tt').datagrid({
			title:"兼职入职列表",
			pagination:true,
			url:"${ctx}/plas/plas-app!list.action",
			columns:[[{field:'appChnName',title:'员工编码',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'姓名',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appBizCd',title:'员工状态',sortable:true,width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appTypeCd',title:'职位',sortable:true,width:80,formatter:function(value,row,index){
				var r = Convert.code2Name(row.appTypeCd,'appType');			
				return r;
			}},	{field:'creator',title:'所属行政组织', width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'createdDate',title:'兼职职位', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updator',title:'兼职所属行政组织', width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'兼职开始日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'兼职结束日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'兼职任职类型', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'opt',title:'操作',width:80,align:'center', rowspan:2,formatter:function(value,row,index){
				var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+row.plasAppId+'") >编辑</a> ';
				return e;											
			}}]],		
			toolbar:[{				
				id:'btnadd',
				text:'新增',
				iconCls:'icon-add',
				handler:function(){				
					editrow('5');
				}
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
	}else if(indexs==6){
		$('#tt').datagrid({
			title:"兼职免职列表",
			pagination:true,
			url:"${ctx}/plas/plas-app!list.action",
			columns:[[{field:'appChnName',title:'员工编码',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'姓名',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appBizCd',title:'员工状态',sortable:true,width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appTypeCd',title:'职位',sortable:true,width:80,formatter:function(value,row,index){
				var r = Convert.code2Name(row.appTypeCd,'appType');			
				return r;
			}},	{field:'creator',title:'所属行政组织', width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'createdDate',title:'兼职职位', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updator',title:'兼职所属行政组织', width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'兼职开始日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'兼职结束日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'兼职任职类型', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'opt',title:'操作',width:80,align:'center', rowspan:2,formatter:function(value,row,index){
				var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+row.plasAppId+'") >编辑</a> ';
				return e;											
			}}]],		
			toolbar:[{				
				id:'btnadd',
				text:'新增',
				iconCls:'icon-add',
				handler:function(){				
					editrow('6');
				}
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
	}else if(indexs==7){
		$('#tt').datagrid({
			title:"兼职变动列表",
			pagination:true,
			url:"${ctx}/plas/plas-app!list.action",
			columns:[[{field:'appChnName',title:'员工编码',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'姓名',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appBizCd',title:'员工状态',sortable:true,width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appTypeCd',title:'职位',sortable:true,width:80,formatter:function(value,row,index){
				var r = Convert.code2Name(row.appTypeCd,'appType');			
				return r;
			}},	{field:'creator',title:'所属行政组织', width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'createdDate',title:'目标职位', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updator',title:'目标行政组织', width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'变更原因', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'生效日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'opt',title:'操作',width:80,align:'center', rowspan:2,formatter:function(value,row,index){
				var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+row.plasAppId+'") >编辑</a> ';
				return e;											
			}}]],		
			toolbar:[{				
				id:'btnadd',
				text:'新增',
				iconCls:'icon-add',
				handler:function(){				
					editrow('7');
				}
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
	}else if(indexs==8){
		$('#tt').datagrid({
			title:"借调调出列表",
			pagination:true,
			url:"${ctx}/plas/plas-app!list.action",
			columns:[[{field:'appChnName',title:'员工编码',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'姓名',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appBizCd',title:'借调调出前职位',sortable:true,width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appTypeCd',title:'借调调出前所属行政组织',sortable:true,width:100,formatter:function(value,row,index){
				var r = Convert.code2Name(row.appTypeCd,'appType');			
				return r; 	
			}},	{field:'creator',title:'借调调出前职务', width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'createdDate',title:'借调调出前职级', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updator',title:'借调调出前职等', width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'目标员工池', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'变更原因', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'借调后归属', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'拟借调结束日期', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'占调入方编制', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'占调入方组织单元人数', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'占调出方编制', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'占调出方组织单元人数', sortable:true, width:100,ormatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'opt',title:'操作',width:80,align:'center', rowspan:2,formatter:function(value,row,index){
				var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+row.plasAppId+'") >编辑</a> ';
				return e;											
			}}]],		
			toolbar:[{				
				id:'btnadd',
				text:'新增',
				iconCls:'icon-add',
				handler:function(){				
					editrow('8');
				}
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
	}else if(indexs==9){
		$('#tt').datagrid({
			title:"借调调入列表",
			pagination:true,
			url:"${ctx}/plas/plas-app!list.action",
			columns:[[{field:'appChnName',title:'员工编码',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'姓名',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appBizCd',title:'借调调入前职位',sortable:true,width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appTypeCd',title:'借调调入前所属行政组织',sortable:true,width:100,formatter:function(value,row,index){
				var r = Convert.code2Name(row.appTypeCd,'appType');			
				return r;
			}},	{field:'creator',title:'借调调入前职务', width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'createdDate',title:'借调调入前职级', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updator',title:'借调调入前职等', width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'目标员工池', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'变更原因', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'借调后归属', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'拟借调结束日期', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'占调出方编制', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'占调出方组织单元人数', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'占调入方编制', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'占调入方组织单元人数', sortable:true, width:100,ormatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'opt',title:'操作',width:80,align:'center', rowspan:2,formatter:function(value,row,index){
				var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+row.plasAppId+'") >编辑</a> ';
				return e;											
			}}]],		
			toolbar:[{				
				id:'btnadd',
				text:'新增',
				iconCls:'icon-add',
				handler:function(){				
					editrow('9');
				}
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
	}else if(indexs==10){
		$('#tt').datagrid({
			title:"借调调回列表",
			pagination:true,
			url:"${ctx}/plas/plas-app!list.action",
			columns:[[{field:'appChnName',title:'员工编码',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'姓名',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appBizCd',title:'借调调出后职位',sortable:true,width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appTypeCd',title:'借调调出后所属行政组织',sortable:true,width:100,formatter:function(value,row,index){
				var r = Convert.code2Name(row.appTypeCd,'appType');			
				return r;
			}},	{field:'creator',title:'借调调出后职务', width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'createdDate',title:'借调调出后职级', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updator',title:'借调调出后职等', width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'借调调回后职位', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'借调调回后所属行政组织', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'借调调回后职务', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'借调调回后职级', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'借调调回后职等', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'变更原因', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'生效日期', sortable:true, width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'opt',title:'操作',width:80,align:'center', rowspan:2,formatter:function(value,row,index){
				var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+row.plasAppId+'") >编辑</a> ';
				return e;											
			}}]],		
			toolbar:[{				
				id:'btnadd',
				text:'新增',
				iconCls:'icon-add',
				handler:function(){				
					editrow('10');
				}
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
	}else if(indexs==11){
		$('#tt').datagrid({
			title:"借调处理列表",
			pagination:true,
			url:"${ctx}/plas/plas-app!list.action",
			columns:[[{field:'appChnName',title:'员工编码',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'姓名',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appBizCd',title:'借调调出前职位',sortable:true,width:120,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appTypeCd',title:'借调调出前所属行政组织',sortable:true,width:150,formatter:function(value,row,index){
				var r = Convert.code2Name(row.appTypeCd,'appType');			
				return r;
			}},	{field:'creator',title:'借调调出前职务', width:120,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'createdDate',title:'借调调出前职级', sortable:true, width:120,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updator',title:'借调调出前职等', width:120,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'借调调出后职位', sortable:true, width:120,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'借调调出后所属行政组织', sortable:true, width:120,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'借调调出后职务', sortable:true, width:120,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'借调调出后职级', sortable:true, width:120,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'借调调出后职等', sortable:true, width:120,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'变更原因', sortable:true, width:120,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'借调后归属', sortable:true, width:120,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'拟借调结束日期', sortable:true, width:120,ormatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'占调出方编制', sortable:true, width:120,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'占调出方组织单元人数', sortable:true, width:120,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'占调入方编制', sortable:true, width:120,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'占调入方组织单元人数', sortable:true, width:120,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'生效日期', sortable:true, width:120,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'opt',title:'操作',width:80,align:'center', rowspan:2,formatter:function(value,row,index){
				var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+row.plasAppId+'") >编辑</a> ';
				return e;											
			}}]],		
			toolbar:[{				
				id:'btnadd',
				text:'新增',
				iconCls:'icon-add',
				handler:function(){				
					editrow('11');
				}
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
	}else if(indexs==12){
		$('#tt').datagrid({
			title:"借调调回延时处理列表",
			pagination:true,
			url:"${ctx}/plas/plas-app!list.action",
			columns:[[{field:'appChnName',title:'员工编码',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'姓名',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appBizCd',title:'借调调出前职位',sortable:true,width:100,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'借调调出前所属行政组织', sortable:true, width:150,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'createdDate',title:'借调调出后职位', sortable:true, width:120,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'借调调出后所属行政组织', sortable:true, width:150,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'到位日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'原拟调回日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'拟调回日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'生效日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'opt',title:'操作',width:80,align:'center', rowspan:2,formatter:function(value,row,index){
				var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+row.plasAppId+'") >编辑</a> ';
				return e;											
			}}]],
			toolbar:[{				
				id:'btnadd',
				text:'新增',
				iconCls:'icon-add',
				handler:function(){				
					editrow('12');
				}
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
	}else if(indexs==13){
		$('#tt').datagrid({
			title:"离职处理列表",
			pagination:true,
			url:"${ctx}/plas/plas-app!list.action",
			columns:[[{field:'appChnName',title:'姓名',width:120,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'离职原因类型',width:120,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appBizCd',title:'离职原因',sortable:true,width:200,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appTypeCd',title:'生效日期',sortable:true,width:120,formatter:function(value,row,index){
				var r = Convert.code2Name(row.appTypeCd,'appType');			
				return r;
			}},{field:'opt',title:'操作',width:120,align:'center', rowspan:2,formatter:function(value,row,index){
				var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+row.plasAppId+'") >编辑</a> ';
				return e;											
			}}]],		
			toolbar:[{				
				id:'btnadd',
				text:'新增',
				iconCls:'icon-add',
				handler:function(){				
					editrow('13');
				}
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
	}else if(indexs==14){
		$('#tt').datagrid({
			title:"不在职处理列表",
			pagination:true,
			url:"${ctx}/plas/plas-app!list.action",
			columns:[[{field:'appChnName',title:'员工编码',width:60,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'姓名',width:60,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appBizCd',title:'员工状态',sortable:true,width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'职位', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'createdDate',title:'所属行政组织', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'目标员工状态', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'目标行政组织', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'变更原因', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'生效日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'opt',title:'操作',width:80,align:'center', rowspan:2,formatter:function(value,row,index){
				var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+row.plasAppId+'") >编辑</a> ';
				return e;											
			}}]],		
			toolbar:[{				
				id:'btnadd',
				text:'新增',
				iconCls:'icon-add',
				handler:function(){				
					editrow('14');
				}
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
	}else if(indexs==15){
		$('#tt').datagrid({
			title:"离退休处理列表",
			pagination:true,
			url:"${ctx}/plas/plas-app!list.action",
			columns:[[{field:'appChnName',title:'员工编码',width:60,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'姓名',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appBizCd',title:'出生日期',sortable:true,width:60,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'creator',title:'性别', width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updator',title:'员工状态', width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'离退休日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'职位', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'createdDate',title:'所属行政组织', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'目标员工状态', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'目标行政组织', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'变更原因', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'生效日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'opt',title:'操作',width:60,align:'center', rowspan:2,formatter:function(value,row,index){
				var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+row.plasAppId+'") >编辑</a> ';
				return e;											
			}}]],		
			toolbar:[{				
				id:'btnadd',
				text:'新增',
				iconCls:'icon-add',
				handler:function(){				
					editrow('15');
				}
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
	}else if(indexs==16){
		$('#tt').datagrid({
			title:"返聘处理列表",
			pagination:true,
			url:"${ctx}/plas/plas-app!list.action",
			columns:[[{field:'appChnName',title:'员工编码',width:60,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'姓名',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appBizCd',title:'员工状态',sortable:true,width:60,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'creator',title:'职位', width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'createdDate',title:'所属行政组织', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updator',title:'目标员工状态', width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'目标职位', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'目标行政组织', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'变更原因', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'入职日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'生效日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'opt',title:'操作',width:60,align:'center', rowspan:2,formatter:function(value,row,index){
				var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+row.plasAppId+'") >编辑</a> ';
				return e;											
			}}]],
			toolbar:[{				
				id:'btnadd',
				text:'新增',
				iconCls:'icon-add',
				handler:function(){				
					editrow('16');
				}
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
	}else if(indexs==17){
		$('#tt').datagrid({
			title:"返聘解聘处理列表",
			pagination:true,
			url:"${ctx}/plas/plas-app!list.action",
			columns:[[{field:'appChnName',title:'员工编码',width:60,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appEngName',title:'姓名',width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appBizCd',title:'员工状态',sortable:true,width:60,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'appTypeCd',title:'离退休时间',sortable:true,width:80,formatter:function(value,row,index){
				var r = Convert.code2Name(row.appTypeCd,'appType');			
				return r;
			}},	{field:'creator',title:'职位', width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'createdDate',title:'所属行政组织', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updator',title:'目标员工状态', width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'目标行政组织', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},{field:'updatedDate',title:'解聘日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'变更原因', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'updatedDate',title:'生效日期', sortable:true, width:80,formatter:function(value,row,index){
				return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
			}},	{field:'opt',title:'操作',width:60,align:'center', rowspan:2,formatter:function(value,row,index){
				var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+row.plasAppId+'") >编辑</a> ';
				return e;											
			}}]],		
			toolbar:[{				
				id:'btnadd',
				text:'新增',
				iconCls:'icon-add',
				handler:function(){				
					editrow('17');
				}
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
	}
	$('#w').window({
		title: '应用管理',
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
</script>