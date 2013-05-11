<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>搜索登录日志</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.quickSearch.js"></script>
	
	
</head>
<body class="easyui-layout">
<div region="north" title="搜索" icon="icon-search" style="height:80px;padding-top:5px;overflow:hidden;" border="false">
	<form id="searchForm" method="post" onsubmit="return false;">
		<table style="width:100%;"> 
			<tr>
				<td>
					操作人:
					<input type="text"   id="userName" name="userName" title="（用户名或PD账号）" style="width:80px" />
					<input type="hidden" id="uiid" name="uiid"/>
					(登录/登出)日期: 
					<input style="width:120px;" class="easyui-datebox"  name="gtDate" id="gtDate"/> 
					至 
					<input style="width:120px;" class="easyui-datebox" name="ltDate" id="ltDate"/>
					<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="customSearch()">搜索</a> 
				</td>
			</tr>
		</table>
	</form> 
</div>

<div region="center" border="false">
	<table id="tt" fit="true" style="width:100%;"
			title="日志列表" singleSelect="true" rownumbers="true"
			idField="plasLoginLogId" url="${ctx}/plas/plas-login-log!list.action">
	</table>
</div>
<div id="w" class="easyui-window" style="left:40px;top:20px;">
</div>
<script>
	$(function(){
		init();

		//注册快速搜索(模糊匹配:uiid,userName)
		$('#userName').quickSearch(
			'${ctx}/plas/plas-acct!quickSearchAcctList.action',
			['uiid','userName','orgName','centerOrgName','statusCd'],
			{uiid:'uiid',userName:'userName'},
			'',
			function(result){}
		);
	});
	
	function init(){
		$('#tt').datagrid({
			pagination:true,
			columns:[[
	          	{field:'uiid',title:'账号',sortable:true,width:80},
				{field:'userName',title:'操作人姓名',sortable:true,width:100},
				//{field:'ip',title:'IP',sortable:true,width:150},
				//{field:'macAddress',title:'MAC地址', width:150},						   
				{field:'loginDate',title:'登录时间', sortable:true, width:130,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'logoutDate',title:'退出时间', sortable:true, width:130,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'remark',title:'备注',width:250,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				}
			]],		
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
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


		$('#gtDate').datebox({
			formatter: function(date){
				return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
			},   
			parser: function(date){
				return new Date(Date.parse(date.replace(/-/g,"/"))); 
			}
		});  
		$('#ltDate').datebox({
			formatter: function(date){
				return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
			},   
			parser: function(date){
				return new Date(Date.parse(date.replace(/-/g,"/"))); 
			}
		});   
	}
	function customSearch(){
		var param = Convert.getJson4Form('searchForm');
		$('#tt').datagrid('options').queryParams = param;
		$('#tt').datagrid('reload');
	} 
</script>
</body>
</html>