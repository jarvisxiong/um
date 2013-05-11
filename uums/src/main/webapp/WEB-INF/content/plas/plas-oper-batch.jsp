<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>日常申请审批[集团总部/商业公司(除各地产公司和各项目公司)]</title>
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
	<div region="north" title="搜索" icon="icon-search" style="padding:5px;" border="false">
		<form id="searchForm" method="post" onsubmit="return false;">
			申请状态:<select id="applyStatusCd" name="applyStatusCd">
					<option value=""></option>
					<%--
					<option value="0">暂存</option>
					 --%>
					<option value="1">待审批</option>
					<option value="2">已处理</option>
					<option value="3">不予处理</option>
				</select>
			申请日期:<input type="text" title="日期格式: yyyy-MM-dd" style="width:90px" class="wdate" id="applyDate" name="applyDate" value="${todayDate}"/>
			
			<security:authorize ifAnyGranted="A_ADMIN">
					<input type="checkbox" name="isExactFlag" id="isExactFlag" />精确		
			申请人:	<input type="text" style="width:100px" class="wdate" id="applyUserName" name="applyUserName" />
			</security:authorize>
			
			问题描述:<input type="text" style="width:100px" id="filter_LIKES_briefTitle" name="filter_LIKES_briefTitle"/>
			<%-- 
			回复日期:<input type="text" title="日期格式: yyyy-MM-dd" style="width:90px" class="wdate" id="filter_EQS_replyDate" name="filter_EQS_replyDate" />
			--%>
			<a href="#" id="btnSearch" iconCls="icon-search" class="easyui-linkbutton" onclick="customSearch()">搜索</a>
			<a href="#" id="btnClean"  iconCls="icon-search" class="easyui-linkbutton" onclick="cleanSearchField()">清空</a>
		</form> 
	</div>
	
	<div region="center" border="false">
		<table id="tt" fit="true"
				title="日常申请列表" singleSelect="true" rownumbers="true"
				idField="plasOperBatchId" url="${ctx}/plas/plas-oper-batch!list.action">
		</table>
	</div>
<script type="text/javascript">
	$(function(){
		init();
	});
	function viewDetail(id){
		var url = '${ctx}/plas/plas-oper-batch!input.action?id=' + id;
		parent.addTab('查看申请',url);
	}
	function init(){
		$('#tt').datagrid({
			onDblClickRow: function(rowIndex, row){
				viewDetail(row.plasOperBatchId);
			},
			pagination:true,
			pageSize:30,
			pageList:[50,40,30,20,10],
			columns:[[
				{field:'briefTitle',title:'问题描述',sortable:true,width:200},
				{field:'applyStatusCd',title:'申请状态',sortable:true,width:80,
					formatter:function(value,row,index){
						var t = '';
						switch(value){
							case '9': t = '暂存'; break; 
							case '1': t = '待审批'; break; 
							case '2': t = '已处理'; break; 
							case '3': t = '驳回'; break; 
							case '0': t = '删除'; break; 
							default : ;
						}
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;width:100%;text-align:center;" title="'+t+'">'+t+'</div>';
					}
		        },
				{field:'applyUserName',title:'申请人',sortable:true,width:80},
		        {field:'applyDate',title:'申请时间',sortable:true,width:120},
				{field:'replyUserName',title:'回复人',sortable:true,width:80},		   
				{field:'replyDate',title:'回复时间',sortable:true,width:120},	   
				{field:'remark',title:'操作', sortable:true, width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;cursor:pointer;" title="'+value+'" onclick=viewDetail("'+ row.plasOperBatchId +'")>查看明细</div>';
					}
				}
			]],				
			toolbar:[{				
				id:'btnadd',
				text:'发起申请',
				iconCls:'icon-add',
				handler:function(){				
					addBatch();
				}
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		}); 

		//提交日期\答复日期(bug)
		$('#replyDate,#applyDate').datebox({
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
		param.firstFlg = '0';
		$('#tt').datagrid('options').queryParams = param;
		$('#tt').datagrid('reload');
	}  
	//导出
	function exportExcel(){
		$('#searchForm').attr('action','plas-oper-batch!exportExcel.action');
		search();
		$('#searchForm').attr('action','plas-oper-batch');
	}
	function addBatch(){
		var url = '${ctx}/plas/plas-oper-batch!input.action';
		//parent.addTab('发起申请',url);
		window.location.href = url;
	}
</script>
</body>
</html>