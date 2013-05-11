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
	<link href="${ctx}/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
	<script src="${ctx}/js/jquery/jquery-lasted.min.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/common/ConvertUtil.js"  type="text/javascript" ></script>
	<script>
		var lastIndex;
		$(function(){
			$('#tt').datagrid({
				pagination:true,
				width:600px,
				toolbar:[{
					text:'新增',
					iconCls:'icon-add',
					handler:function(){
						$('#tt').datagrid('endEdit', lastIndex);
						var index = $('#tt').datagrid('getData').total;
						$('#tt').datagrid('appendRow',{
							dictTypeCd:'',
							dictTypeName:'',
							remark:'',
							defaultFlg:'false',
							dispOrderNo:index
						});
						lastIndex = $('#tt').datagrid('getRows').length-1;
						$('#tt').datagrid('selectRow', lastIndex);
						$('#tt').datagrid('beginEdit', lastIndex);
						$('#tt').datagrid('clearSelections');
					}
				},'-',{
					text:'保存',
					iconCls:'icon-save',
					handler:function(){
						//saveDelete();
						saveEdit();
					}
				},'-',{
					text:'删除',
					iconCls:'icon-remove',
					handler:function(){
						var row = $('#tt').datagrid('getSelected');
						if (row){
							var index = $('#tt').datagrid('getRowIndex', row);
							$('#tt').datagrid('deleteRow', index);
							var nextSelect=index>0?index-1:0;
							$('#tt').datagrid('clearSelections');
							$('#tt').datagrid('selectRow', nextSelect);
							var appDictTypeId=row.appDictTypeId;
							$.post("${ctx}/app/app-dict-type!delete.action",{id:appDictTypeId} , function(result) {
								$('#tt').datagrid('acceptChanges');
							});
						}
					}
				},'-',{
					text:'getChanges',
					iconCls:'icon-search',
					handler:function(){
						var rows = $('#tt').datagrid('getChanges');
						alert('changed rows: ' + rows.length + ' lines');
					}
				},'-',{
					text:'rejectChanges',
					iconCls:'icon-search',
					handler:function(){
						var rows = $('#tt').datagrid('rejectChanges');
					}
				}],
				onBeforeLoad:function(){
					$(this).datagrid('rejectChanges');
				},
				onAfterEdit:function(rowIndex, rowData, changes){
					changes['id']=rowData['appDictTypeId'];
					//$.post("${ctx}/app/app-dict-type!save.action",changes , function(result) {
					//});
				},
				onDblClickRow:function(rowIndex){
					if (lastIndex != rowIndex){
						$('#tt').datagrid('endEdit', lastIndex);
						$('#tt').datagrid('beginEdit', rowIndex);
					}
					lastIndex = rowIndex;
				}
			});
		});
		
		function saveDelete(){
			$('#tt').datagrid('endEdit', lastIndex);
			var rows = $('#tt').datagrid('getChanges','deleted');
			var ids = new Array();
			for(var i=0 ;i<rows.length;i++){
				var appDictTypeId=rows[i].appDictTypeId;
				if (isNotEmpty(appDictTypeId)){
					ids.push("ids=" + appDictTypeId);
				}
			}
			if (ids.length>0){
				var param = ids.join("&");
				$.post("${ctx}/app/app-dict-type!deleteBatch.action",param , function(result) {
					$('#tt').datagrid('acceptChanges');
				});
			}
		}
		function saveEdit(){
			var valResult=$('#tt').datagrid('validateRow',lastIndex);
			$('#tt').datagrid('endEdit', lastIndex);
			var rowsInserted = $('#tt').datagrid('getChanges','inserted');
			var rowsUpdated = $('#tt').datagrid('getChanges','updated');
			var rows=rowsInserted.concat(rowsUpdated);
			var index=0;
			var rowsChanged={};
			for(var i=0 ;i<rows.length;i++){
				var row=rows[i];
				var rowIndex=$('#tt').datagrid('getRowIndex',row);
				var valResult=$('#tt').datagrid('validateRow',rowIndex);
				if (valResult){
					for(var j in row){
						var strKey='entities'+'['+index+'].'+j;
						rowsChanged[strKey]=row[j];
					}
					index++;
				}
			}
			$.post("${ctx}/app/app-dict-type!save.action",rowsChanged , function(result) {
				$('#tt').datagrid('acceptChanges');
				$('#tt').datagrid('reload');
			});
		}
	</script>
</head>
<body class="easyui-layout">
<div region="center" style="padding:5px;" border="false">
	<table id="tt" fit="true"
			title="Editable DataGrid" iconCls="icon-edit" singleSelect="true" rownumbers="true"
			idField="dictTypeCd" url="${ctx}/app/app-dict-type!list.action">
		<thead>
			<tr>
				<th field="dictTypeCd" width="80" sortable="true" editor="{type:'validatebox',options:{required:'true',validType:'length[1,20]'}}">dictTypeCd</th>
				<th field="dictTypeName" width="100" editor="{type:'validatebox',options:{required:'true',validType:'length[1,50]'}}">dictTypeName</th>
				<th field="remark" width="150" editor="{type:'validatebox',options:{validType:'length[0,200]'}}">remark</th>
				<th field="defaultFlg" width="80" align="right" editor="{type:'checkbox',options:{on:'true',off:'false'}}">defaultFlg</th>
				<th field="dispOrderNo" width="80" align="right" editor="{type:'numberbox',options:{max:'10'}}">dispOrderNo</th>
				<th field="updatedDate" width="80" align="right">updatedDate</th>
			</tr>
		</thead>
	</table>
</div>
</body>
</html>