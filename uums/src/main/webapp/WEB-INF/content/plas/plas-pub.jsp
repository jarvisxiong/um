<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>管理员公告</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.quickSearch.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/xheditor/xheditor-src-zh-cn.js"></script>
	
</head>
<body class="easyui-layout">
<div region="north" title="搜索" icon="icon-search" style="height:80px;padding-top:5px;overflow:hidden;" border="false">
	<form id="searchForm" method="post" onsubmit="return false;">
		<table>
			<tr>
				<td>
					标题:
					<input type="text"   id="filter_EQS_briefTitle" name="filter_EQS_briefTitle" title="模糊搜索标题" style="width:120px" />
					关键字:
					<input type="text"   id="filter_LIKES_content" name="filter_LIKES_content" title="模糊搜索标题" style="width:120px" />
					
					公告日期:  
					<input class="easyui-datebox"  name="filter_GED_pubDate" id="filter_GED_pubDate" style="width:90px;" value='<s:date name="filter_GED_pubDate" format="yyyy-MM-dd"/>'/> 
					至
					<input class="easyui-datebox"  name="filter_LED_pubDate" id="filter_LED_pubDate" style="width:90px;" value='<s:date name="filter_LED_pubDate" format="yyyy-MM-dd"/>'/>
					<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="customSearch()">搜索</a>
					
					<security:authorize ifNotGranted="A_ADMIN">
					<a href="#" id="btn" iconCls="icon-add" class="easyui-linkbutton" onclick="editrow('')">发布新闻</a>
					</security:authorize>
				</td>
			</tr>
		</table>
	</form> 
</div>

<div region="center" border="false">
	<table id="tt" fit="true" style="width:100%"
			title="公告列表" singleSelect="true" rownumbers="true"
			idField="plasPubId" url="${ctx}/plas/plas-pub!list.action">
	</table>
</div>
<div id="w" class="easyui-window" style="width:650px;height:450px;">
</div>

<script type="text/javascript">
	$(function(){
		init();
	});

	function init(){
		$('#tt').datagrid({
			onDblClickRow: function(rowIndex, row){
				var id = row.plasPubId;
				var url = '${ctx}/plas/plas-pub!detail.action?id=' + id;
				parent.addTab('查看公告明细',url);
			},
			pagination:true,
			columns:[[
				{field:'pubDate',title:'发布日期', sortable:true, width:100,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
		        {field:'briefTitle',title:'标题',sortable:true,width:400}
			]],		
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
		$('#w').window({
			title: '发布公告',
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
		//过滤日期
		$('#filter_LED_pubDate').datebox({
			formatter: function(date){
				return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
			},   
			parser: function(date){
				return new Date(Date.parse(date.replace(/-/g,"/"))); 
			}
		});  
		$('#filter_GED_pubDate').datebox({
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
	function editrow(id){
		var url="${ctx}/plas/plas-pub!input.action?id="+id;
		//$('#w').window({href:url});
		//$('#w').window("open");

		parent.addTab('公告明细',url);
	}
	

</script>
</body>
</html>