<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" import="com.hhz.uums.utils.DictContants;" %>
<table id="tt" fit="true"
		title="维度管理" iconCls="icon-edit" singleSelect="true" width="100%"
		idField="dimeCd" url="${ctx}/plas/plas-org-dime!list.action">
	<thead>
		<tr>
			<th field="dimeCd" width="80" editor="numberbox" sortable="true">维度代码</th>
			<th field="dimeName" width="150" editor="text">维度名称</th>
			<th field="sequenceNo" width="80"  editor="numberbox">显示序号</th>
			<th field="remark" width="120" editor="text" >备注</th>
			<th field="creator" width="80" >创建人</th>
			<th field="createdDate" width="120" >创建时间</th>
			<th field="updator" width="80" >更新人</th>
			<th field="updatedDate" width="120" >更新时间</th>
		</tr>
	</thead>
</table>

<script type="text/javascript">
	var lastIndexx;
	$(function(){
		$('#tt').datagrid({
			pagination:true,
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					$('#tt').datagrid('endEdit', lastIndexx);
					$('#tt').datagrid('appendRow',{
						dimeCd:'',
						dimeName:'',
						remark:'',
						sequenceNo:'0'
					});
					 lastIndexx = $('#tt').datagrid('getRows').length-1;
					$('#tt').datagrid('selectRow', lastIndexx);
					$('#tt').datagrid('beginEdit', lastIndexx);
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
					var row = $('#tt').datagrid('getSelected');
					if(row){
						$.messager.confirm('提示','确认要删除该记录吗?',function(t){
							if(t){
								$.post("${ctx}/plas/plas-org-dime!delete.action",{id:row.plasOrgDimeId} , function(result) {

									if(result=='success'){
										$('#tt').datagrid('reload');
										$.messager.alert('提示','请刷新页面');
									}else{
										alert(result);
									}
								});
							}
						});
					}
				}
			},'-'],
			onClickRow:function(rowIndex,rowData){
				var dimeCd = rowData.dimeCd;
				if(dimeCd == <%=DictContants.TREE_DIME_PHYSICAL%>||dimeCd == <%=DictContants.TREE_DIME_LOGICAL%>){
					return ;
				}
				if (lastIndexx != rowIndex){
					$('#tt').datagrid('endEdit', lastIndexx);
					$('#tt').datagrid('beginEdit', rowIndex);
				}
				lastIndexx = rowIndex;
			}
		});
	});
	function saveEdit(){
		//校验
		
		var row = $('#tt').datagrid('getSelected');
		if (row){
			var index = $('#tt').datagrid('getRowIndex', row);
			$('#tt').datagrid('endEdit', index);
		}
		$.post("${ctx}/plas/plas-org-dime!saveBatch.action",Convert.ToSaveParam("tt") , function(result) {
			if(result=='success'){
				$('#tt').datagrid('reload');
				$.messager.alert('提示','请刷新页面');
			}else{
				alert(result);
			}
		});
	}
</script>
