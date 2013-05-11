<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>临时表</title>
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
	<div region="north" title="查询" icon="icon-search" style="padding:5px;" border="false">
		<form id="searchForm" method="post" onsubmit="return false;"/>
			账号:<input type="text" style="width:90px" class="wdate" id="filter_EQS_uiid" name="filter_EQS_uiid" />
			姓名:<input type="text" style="width:100px" class="wdate" id="filter_EQS_userName" name="filter_EQS_userName" />
			类型:
			<select name="filter_EQS_typeCd" id="filter_EQS_typeCd">
				<option value=""></option>
				<option value="1">发送密码</option>
			</select>
			执行结果:
			<select name="filter_EQS_statusCd" id="filter_EQS_statusCd">
				<option value=""></option>
				<option value="1">执行成功</option>
				<option value="2">执行失败</option>
				<option value="0">待执行</option>
			</select>
			产生日期:<input type="text" title="yyyy-MM-dd" style="width:90px" class="wdate" id="filter_EQS_createdDate" name="filter_EQS_createdDate" />
			<a href="#" id="btnSearch" iconCls="icon-search" class="easyui-linkbutton" onclick="customSearch()">查询</a>
			<a href="#" id="btnClean"  iconCls="icon-search" class="easyui-linkbutton" onclick="cleanSearchField()">清空</a>
		</form> 
	</div>
	
	<div region="center" border="false">
		<table id="tt" fit="true"
				title="日常申请列表" singleSelect="true" rownumbers="true"
				idField="plasOperBatchId" url="${ctx}/plas/bis-sms-tmp!list.action">
		</table>
	</div>
<script type="text/javascript">
	$(function(){
		init();
	});
	function viewDetail(id){
		var url = '${ctx}/plas/bis-sms-tmp!input.action?id=' + id;
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
				{field:'uiid',title:'账号',sortable:true,width:80},
				{field:'userName',title:'姓名',sortable:true,width:80},
				{field:'typeCd',title:'类型',sortable:true,width:80,
					formatter:function(value,row,index){
						var t = '';
						switch(value){
							case '1': t = '待审批'; break; 
							case '0': t = value; break; 
							default : ;
						}
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;width:100%;text-align:center;" title="'+t+'">'+t+'</div>';
					}
				},
		        {field:'statusCd',title:'执行情况',sortable:true,width:120,
					formatter:function(value,row,index){
						var t = '';
						switch(value){
							case '1': t = '已执行'; break; 
							case '2': t = '未执行'; break; 
							case '0': t = '待执行'; break; 
							default : ;
						}
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;width:100%;text-align:center;" title="'+t+'">'+t+'</div>';
					}
				},
				{field:'updatedDate',title:'更新时间',sortable:true,width:120},	   
				{field:'remark',title:'操作', sortable:true, width:150,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;cursor:pointer;" title="'+value+'" onclick=viewDetail("'+ row.plasOperBatchId +'">查看明细</div>';
					}
				}
			]],			 
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		}); 
		
		/*
		//提交日期\答复日期(bug)
		$('#filter_EQS_replyDate,#filter_EQS_applyDate').datebox({
			formatter: function(date){
				return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
			},   
			parser: function(date){
				return new Date(Date.parse(date.replace(/-/g,"/"))); 
			}
		});  	
		*/c
	}
	function customSearch(){
		var param = Convert.getJson4Form('searchForm');
		$('#tt').datagrid('options').queryParams = param;
		$('#tt').datagrid('reload');
	}  
	//导出
	function exportExcel(){
		$('#searchForm').attr('action','bis-sms-tmp!exportExcel.action');
		search();
		$('#searchForm').attr('action','bis-sms-tmp');
	}
</script>
</body>
</html>