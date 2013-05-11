<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>字典管理</title>
	<%@ include file="/common/global.jsp" %>
	
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js" ></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js" ></script>
	<script type="text/javascript" src="${ctx}/js/plugins/validate/jquery.validate.js"></script>
	
</head>
<body class="easyui-layout">
<div region="north" title="查询" icon="icon-search" style="height:70px;padding-top:5px;overflow:hidden;" border="false">
	<form id="searchForm"  method="post">
		<table>
			<tr>
				<td>字典代码:</td>
				<td><input name="filter_LIKES_dictTypeCd" type="text"></input></td>
				<td>字典名称:</td>
				<td><input name="filter_LIKES_dictTypeName" type="text" ></input></td>
				<td>是否默认:</td>
				<td><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapEnableFlg()" name="filter_EQB_defaultFlg" listKey="key" listValue="value"/></td>
				<td><a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="Convert.search('searchForm','tt');">查询</a></td> 
			</tr>
		</table>
	</form>
</div>
<div region="center" border="false">
	<table id="tt" fit="true"
			title="数据列表" singleSelect="true" rownumbers="true"
			idField="appDictTypeId" url="${ctx}/app/app-dict-type!list.action">
	</table>
</div>
<div id="w" class="easyui-window" closed="true" style="width:650px;height:500px;padding:5px;background: #fafafa;">
</div>

<script type="text/javascript">
	var windowOption={
		title: '数据字典明细',
		modal: true,
		closed: true,
		collapsible : false,
		minimizable : false,
		cache: false,
		iconCls:"icon-save",
		onClose:function(){
			//刷新数据
			//$('#w').window("destroy");
			$('#tt').datagrid('reload');
		}
	};
	var lastIndex;
	var inputUrl;
	$(function(){
		$('#tt').datagrid({
			pagination:true,
			pageSize:50,
			pageList:[50,40,30,20,10],
			columns:[[
				{field:'dictTypeCd',title:'字典代码',sortable:true, width:180,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'dictTypeName',title:'字典名称',sortable: true, width:130,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'defaultFlg',title:'是否默认',sortable: true, align: 'center', width:60,
					formatter:function(value,row,index){
						var defaultFlg = value;
						if(defaultFlg){
							return '是';
						}else{
							return '否';
						}
					}
				},
				{field:'remark',title:'备注',width:130,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">' + value +'</div>';
					}
				},
				{field:'sequenceNo',title:'显示序号',sortable: true, align: 'right',width:60},
				{field:'updatedDate',title:'更新时间',sortable: true, width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'action',title:'操作',width:150,sortable: true, align:'center',
					formatter:function(value,row,index){
						var id =row.appDictTypeId;
						var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+id+'") >编辑</a> ';
						var d = '<a href="#" class="easyui-linkbutton" onclick=deleterow('+index+',"'+id+'") >删除</a>';
						return e+d;
					}
				}
			]],
			toolbar:[{
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
		$('#w').window(windowOption);
	});
	function deleterow(index,id){
		$.messager.confirm('Confirm','确定要删除么?',function(r){
			if (r){
				$('#tt').datagrid('deleteRow', index);
				var nextSelect=index>0?index-1:0;
				$('#tt').datagrid('selectRow', nextSelect);
				if(id){
					$.post("${ctx}/app/app-dict-type!delete.action",{id:id} , function(result) {
						$('#tt').datagrid('acceptChanges');
					});
				}else{
					$('#tt').datagrid('acceptChanges');
				}
			}
		});
	}
	
	function editrow(id){
		var url="${ctx}/app/app-dict-type!input.action?id="+id;
		$('#w').window({href:url});
		$('#w').window("open");
	}
	function clearForm(){
		$('#searchForm').form('clear');
	}
	$.extend($.fn.validatebox.defaults.rules, {
	    isExists: {
	        validator: function(value, param){
				var flg = true;
				$.ajaxSettings.async = false;
				$.post("${ctx}/app/app-dict-type!isTypeExists.action?dictTypeCd=" + encodeURIComponent(value)+'&oldDictTypeCd='+encodeURIComponent(param[0]) , function(result) {
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
	
	function saveDict(){
		$('#ff').form('submit', options);
		//$('#ff').submit();
	}
	
	var options={
	        url:'${ctx}/app/app-dict-type!save.action',
	        onSubmit: function(){
	        	var flag=$('#ff').form('validate');
	        	if (flag){
		        	var changeRows=$('#tt2').datagrid('getChanges','inserted','updated');
		        	for(var i=0;i<changeRows.length;i++){
		        		var rowIndex=$('#tt2').datagrid('getRowIndex',changeRows[i]);
		        		flag=$('#tt2').datagrid('validateRow',rowIndex);
		        		if (!flag){
		        			$('#tt2').datagrid('selectRow',rowIndex);
		        			$('#tt2').datagrid('beginEdit', rowIndex);
							break;
		        		}else{
		        			$('#tt2').datagrid('endEdit', rowIndex);
		        		}
		        	}
	        	}
	        	if(flag){
	        		$('#tt2').datagrid('endEdit', lastIndex2);
			        Convert.setChildren2Form("ff","tt2");
	        	}
	        	return flag;
	        },
	        success:function(data){
	        	if(data!='failure'){
		        	$.messager.alert('Info','保存成功');
		        	var id=data;
		        	var url="${ctx}/app/app-dict-type!input.action?id="+id;
		        	$('#w').window({href:url});
		        	$('#w').window("close");// modify by chenyk 保存成功后需要应该关闭窗口而不是打开
	        	}else{
	        		$.messager.alert('Info','保存失败');
	        	}
	        }
		};
	

	function deleterow2(index,id){
		$.messager.confirm('Confirm','Are you sure?',function(r){
			if (r){
				$('#tt2').datagrid('deleteRow', index);
				var nextSelect=index>0?index-1:0;
				$('#tt2').datagrid('selectRow', nextSelect);
				if(id){
					$.post("${ctx}/app/app-dict-type!deleteSub.action",{subId:id} , function(result) {
						$('#tt2').datagrid('acceptChanges');
					});
				}else{
					//$('#tt2').datagrid('acceptChanges');
				}
			}
		});
	}
</script>
</body>
</html>