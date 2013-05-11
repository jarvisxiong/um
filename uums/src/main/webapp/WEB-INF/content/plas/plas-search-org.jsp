<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>搜索机构</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"  ></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js" ></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js" ></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
</head>
<body class="easyui-layout">
<div region="north" title="搜索" icon="icon-search" style="height:100px;padding-top:5px;overflow:hidden;" border="false">
	<form id="searchForm" method="post" action="/plas-search-org!list.action">
		<table cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td>
					机构编号:
					<input style="width:80px;" name="filter_EQS_orgCd" type="text" value="${filter_EQS_orgCd}"></input>
					机构业务编号:
					<input style="width:80px;" name="filter_EQS_orgBizCd" type="text" value="${filter_EQS_orgBizCd}"></input>
	      			机构名称:
					<input style="width:80px;" name="filter_LIKES_orgName" type="text" value="${filter_LIKES_orgName}"></input>
					机构简称:
					<input style="width:80px;" name="filter_LIKES_shortOrgName" value="${filter_LIKES_shortOrgName}"/>
					负责人:
					<input style="width:80px;" name="filter_EQS_orgMgrId" value="${filter_EQS_creator}"/>
					是否有效:
					<s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapEnableFlg()" name="filter_EQB_activeBl" listKey="key" listValue="value"/>
					
					创建人:
					<input style="width:80px;" name="filter_EQS_creator" value="${filter_EQS_creator}"/>
					更新人:
					<input style="width:80px;" name="filter_EQS_updator" value="${filter_EQS_updator}"/>
					
					创建日期:
					<input class="easyui-datebox" style="width:80px;" name="filter_LTD_createdDate" value="${filter_LTD_createdDate}"/>
					至
					<input class="easyui-datebox" style="width:80px;" name="filter_GTD_updateDate" value="${filter_GTD_createdDate}"/>
					更新日期:
					<input class="easyui-datebox" style="width:80px;" name="filter_GTD_updatedDate" value="${filter_GTD_updatedDate}"/>
					至
					<input class="easyui-datebox" style="width:80px;" name="filter_LTD_updatedDate" value="${filter_LTD_updatedDate}"/>
					<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="customSearch()">搜索</a>
				</td>
			</tr>
		</table>
	</form> 
</div>

<div region="center" border="false">
	<table id="tt" fit="true"
			title="机构列表" singleSelect="true" rownumbers="true"
			idField="plasOrgId" url="${ctx}/plas/plas-search-org!list.action">
	</table>
</div>
<div id="w" class="easyui-window" style="left:40px;top:20px;">
</div>

<script type="text/javascript">
	$(function(){
		init();
	});
	function init(){
		$('#tt').datagrid({
			pagination:true,
			pageSize:30,
			pageList:[50,40,30,20,10],
			columns:[[
	          	{field:'orgBizCd',title:'机构业务编号', sortable:true, width:80},
				{field:'orgName',title:'机构名称', sortable:true, width:140,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'shortOrgName',title:'机构简称', sortable:true, width:60,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'orgMgrId',title:'负责人',editor:{type:'text'}, sortable:true, align: 'right', width:60},
				{field:'sequenceNo',title:'显示序号',editor:{type:'text'}, sortable:true, align: 'right', width:60},
				{field:'activeBl',title:'是否可用',editor:{type:'text'}, sortable:true, align: 'center;', width:100,
					formatter:function(value,row,index){
						if(value){
							return '可用'
						}else{
							return '<div id="operateEnableOrg_'+row.plasOrgId+'">不可用<input type="button" onclick="enableOrg(\''+row.plasOrgId+'\')" value="启用"/></div>'
						}
					}
				},
				{field:'creator',title:'创建人', sortable:true, width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'createdDate',title:'创建时间', sortable:true, width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'updator',title:'更新人', sortable:true, width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'updatedDate',title:'更新时间', sortable:true, width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'opt',title:'操作',width:80,align:'center', rowspan:2,
					formatter:function(value,row,index){
						var s = '<a href="#" class="easyui-linkbutton" onclick=searchrow("'+row.plasOrgId+'") >查看</a> ';
						return s;															
					}
				}			
			]],		
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			},
			onDblClickRow:function(rowIndex,row){
				searchrow(row.plasOrgId);
			}
		});
		$('#w').window({
			title: '机构明细',
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
		var tit = "机构明细";
		var url="${ctx}/plas/plas-search-org!detail.action?id="+id;
		parent.addTab(tit,url);
		//$('#w').window("open");
	}
 	function enableOrg(plasOrgId){
 		if(!window.confirm('确认启用?')){
 			return;
 		}
 		$.post('${ctx}/plas/plas-org!enableOrg.action',{plasOrgId: plasOrgId},function(result){
 			if('success' == result){
 				alert('启用成功!');
 				$('#operateEnableOrg_'+plasOrgId).html('可用');
 			}else{
 				alert(result);
 			}
 		});
 	}
</script>
</body>
</html>