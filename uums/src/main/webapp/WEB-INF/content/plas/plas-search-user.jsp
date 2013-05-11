<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>搜索用户</title>
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
<div region="north" title="搜索" icon="icon-search" style="height:140px;padding-top:5px;overflow:hidden;" border="false">
	<form id="searchForm" method="post" onsubmit="return false;">
		<table cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td style="width:60px;">账号:</td>
				<td><input style="width:80px;" name="filter_LIKES_uiid" type="text" ></input></td>
				<td style="width:60px;">用户姓名:</td>
				<td><input style="width:80px;" name="filter_LIKES_plasUser_userName" type="text" ></input></td>
				<td style="width:60px;">邮箱:</td>
				<td><input style="width:120px;" name="filter_LIKES_email" class="easyui-email" /></td>
				<td style="width:60px;">职务：</td>
				<td><input style="width:80px;" name="filter_LIKES_workDutyDesc"  class="easyui-validatbox"   /></td>
			</tr>
			<tr>
				<td>生效日期:</td>
				<td colspan="3">
					<input style="width:90px" class="easyui-datebox" id="filter_GED_effectDate" name="filter_GED_effectDate" type="text" ></input>
					至
					<input style="width:90px" class="easyui-datebox" id="filter_LTD_effectDate" name="filter_LTD_effectDate" type="text"  ></input>
				</td>
				<td>失效日期:</td>
				<td colspan="3">
					<input style="width:90px" class="easyui-datebox" id="filter_GED_invalidDate" name="filter_GED_invalidDate" type="text"  ></input>
					至
					<input style="width:90px" class="easyui-datebox" id="filter_LTD_invalidDate" name="filter_LTD_invalidDate" type="text"  ></input>
				</td>
			</tr>
			<tr>
				<td>email开通:</td>
				<td><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapEmailFlg()" name="filter_EQS_emailFlg" listKey="key" listValue="value"/></td>
				<td>email重置:</td>
				<td><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapEnableFlgNum()" name="filter_EQS_emailPasswordSetFlg" listKey="key" listValue="value"/></td>
				
				<td>EAS开通:</td>
				<td><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapEasFlg()" name="filter_EQS_easFlg" listKey="key" listValue="value"/></td>
				<td>EAS重置:</td>
				<td><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapEnableFlgNum()" name="filter_EQS_easPasswordSetFlg" listKey="key" listValue="value"/></td>
			</tr>
			<tr>
				<td>账号状态:</td>
				<td><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapUserStatus()" name="filter_EQS_statusCd" listKey="key" listValue="value"/></td>
				<td>员工状态:</td>
				<td><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapServiceStatus()" name="filter_EQS_plasUser_serviceStatusCd" listKey="key" listValue="value"/></td>
				<td colspan="2">	<td style="width:80px;" rowspan="2"><a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="customSearch()">搜索</a></td>
			</tr>
			<%--
			<tr>
				<td>mysoft开通状态:</td>
				<td><input name="filter_EQS_mysoftFlg" class="easyui-email" value="${filter_EQS_mysoftFlg}"/></td>
				<td>mysoft密码重置状态</td>
				<td><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapEnableFlgNum()" name="filter_EQS_mysoftPasswordSetFlg" listKey="key" listValue="value"/></td>
			</tr>
			 --%>
		</table>
	</form> 
</div>

<div region="center" border="false">
	<table id="tt" fit="true"
			title="用户列表" singleSelect="true" rownumbers="true"
			idField="plasAcctId" url="${ctx}/plas/plas-search-user!list.action">
	</table>
</div>
<div id="w" class="easyui-window" style="left:40px;top:20px;">
</div>

<script type="text/javascript">
	$(function(){
		init();
	
		$('#filter_GED_effectDate').datebox({
			formatter: function(date){
				return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
			},   
			parser: function(date){
				return new Date(Date.parse(date.replace(/-/g,"/"))); 
			}
		});  
		$('#filter_LTD_effectDate').datebox({
			formatter: function(date){
				return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
			},   
			parser: function(date){
				return new Date(Date.parse(date.replace(/-/g,"/"))); 
			}
		});  
		$('#filter_GED_invalidDate').datebox({
			formatter: function(date){
				return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
			},   
			parser: function(date){
				return new Date(Date.parse(date.replace(/-/g,"/"))); 
			}
		});  
		$('#filter_LTD_invalidDate').datebox({
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
				{field:'uiid',title:'统一登录账号',sortable:true, align: 'center', width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},						   
				{field:'userBizCd',title:'工号',sortable:true,width:50,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},						   
				{field:'userName',title:'用户姓名',sortable:true,width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},						   
				{field:'email',title:'邮箱地址',sortable:true,width:150,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},						   
				//{field:'',title:'上级机构名称(逻辑)',width:120},
				{field:'sexCd',title:'性别',sortable:true,align:'center',width:40,
					formatter:function(value,row,index){
						switch(value){
							case '0': return '-';
							case '1': return '男';
							case '2': return '女';
							default:'';
						}
						return '';
					}  
				},
				{field:'custLoginName',title:'自定义登录名',sortable:true,width:150,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},						   
				{field:'statusCd',title:'账户状态',sortable:true,align:'center',width:60,
					formatter:function(value,row,index){
						switch(value){
							case '0': return '未启用';
							case '1': return '正常';
							case '2': return '冻结';
							case '3': return '解冻';
							case '4': return '注销';
							default:'';
						} 
						return '';
					}
				},
				{field:'serviceStatusCd',title:'员工状态',sortable:true,align:'center',width:60,
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
				{field:'updateDate',title:'更新时间', sortable:true, width:100,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'opt',title:'操作',width:80,align:'center', rowspan:2,
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
		$('#w').window({
			title: '查看用户',
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
	
	function customSearch(){
		var param = Convert.getJson4Form('searchForm');
		$('#tt').datagrid('options').queryParams = param;
		$('#tt').datagrid('reload');
	} 
	function searchrow(id){
		var tit = "查看用户";
		var url = "${ctx}/plas/plas-search-user!detail.action?id="+id;
		parent.addTab(tit,url);
		//$('#w').window("open");
	}
</script>
</body>
</html>