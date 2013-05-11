<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>搜索实际职位</title>
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
<div region="north" title="搜索" icon="icon-search" style="height:70px;padding-top:5px;overflow:hidden;" border="false">
	<form id="searchForm" method="post">
		<table style="width:100%;">
			<tr>
				<td>
					职位代码:
					<input name="filter_EQS_realPosCd" type="text" value="${filter_EQS_realPosCd}"></input>
					职位名称:
					<input name="filter_LIKES_realPosName" type="text" value="${filter_LIKES_realPosName}"></input>
					显示职位名称:
					<input name="filter_LIKES_realPosNameShow" type="text" value="${filter_LIKES_realPosNameShow}"></input>
					<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="Convert.search('searchForm','tt');">搜索</a> 
			 	</td>
			</tr>
		</table>
	</form> 
</div>
<div region="center" border="false">
	<table id="tt" fit="true"
			title="实际职位管理" iconCls="icon-edit" singleSelect="true" rownumbers="ture"
			idField="plasRealPositionId" url="${ctx}/plas/plas-real-position!list.action">
	</table>
</div>
</div>
<div id="w" class="easyui-window" closed="true" style="width:650px;height:400px;padding:5px;background: #fafafa;">
</div>

<script type="text/javascript">
		//增加新开窗口的题目名称
		var windowOption={
			title: '实际职位管理',
			modal: true,
			closed: true,
			collapsible : false,
			minimizable : false,
			cache: false,
			iconCls:"icon-save",
			onClose:function(){
				//刷新数据
				$('#tt').datagrid('reload');
			}
		};
		var lastIndex;
		$(function(){
			$('#tt').datagrid({
				//modify by chen-yk 2011-4-13 增加编辑按钮固修改表单列表模式由原来的HTML表单变更为datagrid表单
				pagination:true,
				pageSize:30,
				pageList:[50,40,30,20,10],
				columns:[[
					{field:'realPosCd',title:'职位代码',sortable:true,width:70},
					{field:'realPosName',title:'职位名称',sortable:true,width:100,
						formatter:function(value,row,index){
							return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
						}
					},
					{field:'realPosNameShow',title:'显示名称',sortable:true,width:100,
						formatter:function(value,row,index){
							return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
						}
					},
					{field:'sequenceNo',title:'显示序号',sortable:true,width:60},
					{field:'remark',title:'备注',width:80,
						formatter:function(value,row,index){
							return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
						}
					},
					{field:'creator',title:'创建人',sortable:true,width:80,
						formatter:function(value,row,index){
							return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
						}
					},
					{field:'createdDate',title:'创建时间',sortable:true,width:80,
						formatter:function(value,row,index){
							return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
						}
					},
					{field:'updator',title:'更新人',sortable:true,width:80,
						formatter:function(value,row,index){
							return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
						}
					},
					{field:'updatedDate',title:'更新时间',sortable:true,width:80,
						formatter:function(value,row,index){
							return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
						}
					},
					{field:'action',title:'操作',width:80,align:'center',
						formatter:function(value,row,index){
							return '<a href="#" class="easyui-linkbutton" onclick=editrow("'+row.plasRealPositionId+'") >编辑</a> ';
						}
					}
				]],			
				toolbar:[{
					text:'新增',
					iconCls:'icon-add',
					handler:function(){
						editrow('');//编辑列表单行传入值必须为该表主键
					}
				},'-',{
					text:'保存',
					iconCls:'icon-save',
					handler:function(){
						saveEdit();
					}
				},'-',{
					text:'删除',
					iconCls:'icon-remove',
					handler:function(){
						dele();
					}
				},'-'],
				onLoadSuccess:function(){
					$('a.easyui-linkbutton').linkbutton();//新增按钮的3D模式化
				}
			});
			$('#w').window(windowOption);
		});	
		//增加实际职位管理的录入窗口
		function editrow(id){
			var url="${ctx}/plas/plas-real-position!input.action?id="+id;
			$('#w').window({href:url});
			$('#w').window("open");
		}
		function saveEdit(){
			var row = $('#tt').datagrid('getSelected');
			if (row){
				var index = $('#tt').datagrid('getRowIndex', row);
				var flag = $('#tt').datagrid('validateRow',row.index);
				if(flag){
					$('#tt').datagrid('endEdit', index);
					$.post("${ctx}/plas/plas-real-position!batchSave.action",Convert.ToSaveParam("tt") , function(result) {
						var rObj = eval(result);
						if(rObj.success){
							$.messager.alert('Info',rObj.success);
							$('#tt').datagrid('reload');
						}else{
							$.messager.alert('Info',rObj.failure);
						}
					});
				}else{
					return flag;
				}
			}
			
		}
		$.extend($.fn.validatebox.defaults.editors, {
		    isExists: {
		        validator: function(value, param){
					var flg = true;
					$.ajaxSettings.async = false;
					$.post("${ctx}/app/plas-real-position!isTypeExists.action?dictTypeCd=" + encodeURIComponent(value)+'&oldDictTypeCd='+encodeURIComponent(param[0]) , function(result) {
						var obj = eval(result);
						if(obj.success){

							flg = true;
		        		}else {

		        			flg = false;
		        		}
					});
					$.ajaxSettings.async = true;
					
				return flg;
		        },
		        message: '已经存在.'
		    }
		});
		function dele(){
			var row = $('#tt').datagrid('getSelected');
			var index = $('#tt').datagrid('getRowIndex',row);
			if(row){
				$.messager.confirm('提示','确认要删除该记录吗?',function(t){
					if(t){
						$('#tt').datagrid('deleteRow', index);
						var nextSelect=index>0?index-1:0;
						$('#tt').datagrid('selectRow', nextSelect);
						deleteOneRow(row.plasRealPositionId);
					}
				});
			}
			
		}
		function deleteOneRow(id){
			if(window.confirm('删除?')){
				$.post("${ctx}/plas/plas-real-position!delete.action",{id:id} , function(result) {
					var rObj = eval(result);
					if(rObj.success){
						$('#tt').datagrid('acceptChanges');
					}else{
						$('#tt').datagrid('rejectChanges');
						$.messager.alert('Info',rObj.failure);
					}
				});
			}
		}
	</script>
</body>
</html>