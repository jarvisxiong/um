<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>未读邮件统计</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script>
	
</head>
<body class="easyui-layout">
	<div region="north" title="查询" icon="icon-search" style="padding:5px;height:83px;" border="false">
		<form id="searchForm" method="post" onsubmit="return false;">
		  <input type="hidden" name="id" value="${bisEmailStatId}"/>
			<table cellpadding="0" cellspacing="3" border="0">
				<tr>
					<td>
						账号:
						<input style="width:114px;" id="filter_EQS_uiid" name="filter_EQS_uiid" type="text" ></input>
						&nbsp;&nbsp;姓名:
						<input style="width:114px;" id="filter_LIKES_userName"  name="filter_LIKES_userName" type="text" ></input>
						&nbsp;&nbsp;时间段:
						
						<input style="width:114px" class="easyui-datebox" id="filter_GED_createdDate" name="filter_GED_createdDate" type="text" ></input>
						至
						<input style="width:114px" class="easyui-datebox" id="filter_LED_createdDate" name="filter_LED_createdDate" type="text" ></input>
						
						&nbsp;&nbsp;部门:
						<input style="width:114px;" id="filter_LIKES_relOrgName" name="filter_LIKES_relOrgName" class="easyui-email" />
						&nbsp;&nbsp;<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="customSearch()">查询</a>
						<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="customSearchTopList()">排行榜</a>
						<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="exportExcel();">导出Excel</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div region="center" border="false">
		<table id="tt" fit="true"
				title="未读邮件统计列表" singleSelect="true" rownumbers="true"
				idField="bisEmailStatId" url="${ctx}/bis/bis-email-stat!list.action">
		</table>
	</div>
	
<script type="text/javascript">
	$(function(){
		init();
		$('#filter_GED_createdDate').datebox({
			formatter: function(date){
				return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
			},
			parser: function(date){
				return new Date(Date.parse(date.replace(/-/g,"/")));
			}
		});
		$('#filter_LED_createdDate').datebox({
			formatter: function(date){
				return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
			},
			parser: function(date){
				return new Date(Date.parse(date.replace(/-/g,"/")));
			}
		});
	});
	
	function init(){
		$('#tt').datagrid({
			pagination:true,
			pageSize:20,
			pageList:[50,40,30,20,10],
			columns:[[
				{field:'uiid',title:'登录账号',sortable:true, align: 'left', width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'userName',title:'用户姓名',sortable:true,width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'relOrgName',title:'所在部门',sortable:true,width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'noReadCount',title:'未读邮件数',sortable:true,align: 'right',width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'totalCount',title:'总邮件数',sortable:true,align: 'right',width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'countDivi',title:'占比(%)',sortable:true,align: 'right',width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'noReadCountSizeDivi',title:'未读邮件(M)',sortable:true,align: 'right', width:100,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'totalCountSizeDivi',title:'总邮件(M)',sortable:true,align: 'right',width:100,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'sizeDivi',title:'占比(%)',sortable:true,align: 'right',width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'createdDate',title:'记录时间', sortable:true, width:140,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'opt',title:'操作',width:80,align:'center', rowspan:2,
					formatter:function(value,row,index){
						var s = '<a href="#" class="easyui-linkbutton" onclick=searchrow("'+row.bisEmailStatId+'") >查看</a> ';
						return s;															
					}
				}
			]],	
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			},
			onDblClickRow:function(rowIndex,row){
				searchrow(row.bisEmailStatId);
			}
		});
	}
	
	function exportExcel(){
		var url = "${ctx}/bis/bis-email-stat!exportToExcel.action";
		location.href = url;
	}
	function customSearchTopList(){
		var tit = "排行榜";
		var url = "${ctx}/bis/bis-email-stat!toplist.action";
		
		if(parent.$('#tabs').tabs('exists',tit)){
			parent.$('#tabs').tabs('update',{
				tab:parent.$('#tabs').tabs('getTab',tit),
				options:{
					content:parent.createFrame(url)
				}
			});
			parent.addTab(tit,url);	
		}else{
			parent.addTab(tit,url);
		}
	}
	function customSearch(){
		var param = Convert.getJson4Form('searchForm');
		$('#tt').datagrid('options').queryParams = param;
		$('#tt').datagrid('reload');
	}
	function searchrow(id){
		var tit = "查看未读邮件统计图";
		var url = "${ctx}/bis/bis-email-stat!draw.action?id="+id;
		if(parent.$('#tabs').tabs('exists',tit)){
			parent.$('#tabs').tabs('update',{
				tab:parent.$('#tabs').tabs('getTab',tit),
				options:{
					content:parent.createFrame(url)
				}
			});
			parent.addTab(tit,url);	
		}else{
			parent.addTab(tit,url);
		}
		
	}
</script>



<body>
</html>