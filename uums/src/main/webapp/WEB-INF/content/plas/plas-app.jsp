<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>应用管理</title>
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

<div region="center" border="false">
	<table id="tt" fit="true"
			title="应用列表" singleSelect="true" rownumbers="true"
			idField="plasAppId" url="${ctx}/plas/plas-app!list.action">
	</table>
</div>
<div id="w" class="easyui-window" style="left:40px;top:10px;">
</div>

<script type="text/javascript">
	$(function(){
		init();
	});
	
	function init(){
	$('#tt').datagrid({
		pagination:true,
		columns:[[
			{field:'appChnName',title:'应用中文名称',width:140,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'appEngName',title:'英文缩写',width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'appBizCd',title:'业务编号',sortable:true,width:60,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'appTypeCd',title:'应用类型',sortable:true,width:60,
				formatter:function(value,row,index){
					var r = Convert.code2Name(row.appTypeCd,'appType');			
					return r;
				}
			},		
			{field:'creator',title:'创建人', width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'createdDate',title:'创建时间', sortable:true, width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},	
			{field:'updator',title:'更新人', width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'updatedDate',title:'更新时间', sortable:true, width:80,
				formatter:function(value,row,index){
					return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
				}
			},		
			{field:'opt',title:'操作',width:140,align:'center', rowspan:2,
			formatter:function(value,row,index){
				var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+row.plasAppId+'") >编辑</a> ';
				var d = '<a href="#" class="easyui-linkbutton" onclick=deleterow('+index+',"'+row.plasAppId+'") >删除</a>';
				return e+d;															}
			}						
		]],		
		toolbar:[{				
			id:'btnadd',
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
	function customSearch(){
		var param = Convert.getJson4Form('searchForm');
		$('#tt').datagrid('options').queryParams = param;
		$('#tt').datagrid('reload');
	} 
	function deleterow(index,id){
		var row = $('#tt').datagrid('getSelected');
		var index = $('#tt').datagrid('getRowIndex',row);
		$.messager.confirm('确认','你确认要删除该记录吗?',function(r){
				
			if (r){			
				if(id){
					$('#tt').datagrid('deleteRow', index);
					var nextSelect=index>0?index-1:0;
					$('#tt').datagrid('selectRow', nextSelect);
					deleteOneRow(id);
				}
			}
		});
	}
	function deleteOneRow(id){
		$.post("${ctx}/plas/plas-app!delete.action",{id:id} , function(result) {
			var rObj = eval(result);
			if(rObj.success){
				$('#tt').datagrid('reload');
			}else{
				$('#tt').datagrid('rejectChanges');
				$.messager.alert('Info',rObj.failure);
			}
		});
	}
	function editrow(id){
		var tit = "接入应用信息";
		if(id)
			tit = "编辑"+tit;
		else
			tit = "新增"+tit;
		var url="${ctx}/plas/plas-app!input.action?id="+id;
		$('#w').window({
			title:tit,
			href:url,
			modal:true,
			shadow:false,
			closed:false,
			height:500,
			width:760
		});
		$('#w').window("open");
	}
	var flag1;var flag2;var flag3;
	function validateAppBizCd(){
		var appBizCd = $('#appBizCd').val();
		var oldAppBizCd = $('#oldAppBizCd').val();
		var url = '${ctx }/plas/plas-app!isAppBizCdExists.action';
		$.post(url,{appBizCd:appBizCd,oldAppBizCd:oldAppBizCd},function(result){
			if(result.success){
				$('#appBizCdTip').hide();
				flag1=true;
			}else{
				$('#appBizCdTip').show();
				flag1=false;
			}
		});
		
	}
	function validateAppEngName(){
		var appEngName = $('#appEngName').val();
		var oldAppEngName = $('#oldAppEngName').val();
		var url = '${ctx }/plas/plas-app!isAppEngNameExists.action';
		$.post(url,{appEngName:appEngName,oldAppEngName:oldAppEngName},function(result){
			if(result.success){
				$('#appEngNameTip').hide();
				flag2=true;
			}else{
				$('#appEngNameTip').show();
				flag2=false;
			}
		});
		
	}
	function validateAppChnName(){
		var appChnName = $('#appChnName').val();
		var oldAppChnName = $('#oldAppChnName').val();
		var url = '${ctx }/plas/plas-app!isAppChnNameExists.action';
		$.post(url,{appChnName:appChnName,oldAppChnName:oldAppChnName},function(result){
			if(result.success){
				$('#appChnNameTip').hide();
				flag3=true;
			}else{
				$('#appChnNameTip').show();
				flag3=false;
			}
		});
		
	}
	function saveApp(){		
		$.ajaxSettings.async = false;
		var flag = $('#ffApp').form('validate');
		if(flag){
			validateAppBizCd();
			validateAppEngName();
			validateAppChnName();
			if(flag1||flag2||flag3){
				
				$('#ffApp').form('submit',{
					url:'${ctx}/plas/plas-app!save.action',
					onSubmit:function(){
						
					},
					success:function(data){
						if(data == 'success'){
							$.messager.alert('Info','保存成功');
							$('#w').window('close');
							$('#tt').datagrid('reload');
						}else{
							$.messager.alert('Info','保存失败,请重试！');
						}
					}
				});
			}
		}
		$.ajaxSettings.async = true;
	}
</script>
</body>
</html>