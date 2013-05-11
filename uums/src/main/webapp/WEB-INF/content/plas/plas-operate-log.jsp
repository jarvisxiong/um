<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>搜索操作日志</title>
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
		<table>
			<tr>
				<td>
					<%--
					<span>模块名称:</span>
				  	<s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapModuleCd()" name="moduleCd" listKey="key" listValue="value"/>
				  	<span>操作类型:</span>
				  	<s:select name="sumarry" id="sumarry" list="@com.powerlong.plas.utils.DictMapUtil@getMapOperCd()" listKey="key" listValue="value"/>
				  	<br/>
					 --%>
				  	<span>关键字:</span>
				  	<s:textfield name="operateDetailDesc" id="operateDetailDesc" cssStyle="width:120px;" value="%{operateDetailDesc}"/>
				  	
					<span>操作人:</span>
					<input type="text"   id="userName" name="userName" title="（用户名或PD账号）" style="width:80px" />
					<input type="hidden" id="uiid" name="uiid"/>
					
					<span>操作日期:</span>
					<input class="easyui-datebox"  name="filter_GED_createdDate" id="filter_GED_createdDate" style="width:90px;" value='<s:date name="filter_GED_createdDate" format="yyyy-MM-dd"/>'/> 
					至
					<input class="easyui-datebox"  name="filter_LED_createdDate" id="filter_LED_createdDate" style="width:90px;" value='<s:date name="filter_LED_createdDate" format="yyyy-MM-dd"/>'/>
					<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="customSearch()">搜索</a>
					
					<%--
			  		<security:authorize ifAnyGranted="A_ADMIN">
					<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="searchRelEmailAdmin()">搜索与邮件管理员相关的授权记录</a>
					<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="exportExcel()">导出</a>
					</security:authorize>
					 --%>
					
				</td>
			</tr>
		</table>
	</form> 
</div>

<div region="center" border="false">
	<table id="tt" fit="true" style="width:100%"
			title="日志列表" singleSelect="true" rownumbers="true"
			idField="plasOperateLogId" url="${ctx}/plas/plas-operate-log!list.action">
	</table>
</div>
<div id="w" class="easyui-window" style="left:40px;top:20px;">
</div>

<script type="text/javascript">
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
			onDblClickRow: function(rowIndex, row){
				var id = row.plasOperateLogId;
				var url = '${ctx}/plas/plas-operate-log!detail.action?id=' + id;
				parent.addTab('查看操作日志明细',url);
			},
			pagination:true,
			columns:[[
		        {field:'uiid',title:'操作人',sortable:true,width:100},
				{field:'userName',title:'操作人姓名',sortable:true,width:80},
				/*
				{field:'moduleCd',title:'模块名称',sortable:true,width:100},
				{field:'sumarry',title:'操作',width:80},		
				*/
				{field:'createdDate',title:'操作日期', sortable:true, width:120,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},				   
				{field:'operateDetailDesc',title:'操作明细',align:'left',
					formatter:function(value,row,index){		
						//设置单元格可换行
						var s = '<span style="white-space:pre-line">'+value+'</span>';
						return s;															
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
		//过滤日期
		$('#filter_LED_createdDate').datebox({
			formatter: function(date){
				return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
			},   
			parser: function(date){
				return new Date(Date.parse(date.replace(/-/g,"/"))); 
			}
		});  
		$('#filter_GED_createdDate').datebox({
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
	function searchRelEmailAdmin(){
		$('#moduleCd').val('授权管理');
		$('#operateDetailDesc').val('邮件管理员-查看');
		customSearch();
	}
	//导出
	function exportExcel(){
		$('#searchForm').attr('action','plas-operate-log!exportExcel.action');
		$('#searchForm').submit();
		$('#searchForm').attr('action','plas-operate-log');
	}
</script>
</body>
</html>