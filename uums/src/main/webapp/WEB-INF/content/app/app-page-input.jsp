<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="easyui-layout" fit="true">	
	<div region="center" border="false" style="padding:5px 20px 5px 5px;+position: relative;overflow-x:hidden;">
		<form id="saveForm" method="post">
			<input type="hidden" name="id" value="${appPageId}" /> 
			<table class="edit_table">
				<tr>
					<td style="width:60px;text-align: right;">页面代码:</td>
					<td>
						<s:if test="pageCd == null || pageCd == ''">
							系统生成
						</s:if>
						<s:else>
							${pageCd}
						</s:else>
						<%--
						<input style="width:100%;" readonly="readonly" name="pageCd" id="dictTypeCd" validType="length[0,100]" type="text" value="${pageCd}"/>
						 --%>
					</td>
					<td style="width:60px;text-align: right;">页面名称:</td>
					<td><input style="width:100%;" name="pageName" class="easyui-validatebox" required="true" validType="length[0,100]" type="text" value="${pageName}"/></td>
				</tr>
				<tr>
					<td style="text-align: right;">页面路径:</td>
					<td colspan="3"><input style="width:100%;" name="pagePath" type="text"  class="easyui-validatebox" required="true" value="${pagePath}"/></td>
				</tr>
				<tr>
					<td style="text-align: right;">备注:</td>
					<td colspan="3"><textarea style="width:100%;" name="remark" class="easyui-validatebox" validType="length[0,400]">${remark}</textarea> </td>
				</tr>
				<tr>
					<td>页面状态</td>
					<td><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapPageStatus()" name="pageStatusCd" listKey="key" listValue="value"/></td>
				</tr>
			</table>
		</form>
		<div>
			<table id="funcTable" title="页面功能配置" singleSelect="true"
				idField="appPageId" url="${ctx}/app/app-page!listFunc.action?id=${appPageId}">
			</table>
		</div>
	</div>
	<div region="south" border="true" style="height:35px;padding-left:10px;padding-top:3px;">
		<div class="toolbar">
			<s:if test="appPageId != null">
				<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="editrow('${appPageId}');">刷新</a>
			</s:if>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="savePage();">保存</a>
			<s:if test="appPageId != null">
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="deletePage('${appPageId}');">删除</a>
			</s:if>
			<a id="add" href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="editrow('');">新增</a>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$('#funcTable').datagrid({
			columns:[[
				{field:'functionCd',title:'功能CD',sortable:true,width:80,editor:{type:'validatebox',options:{required:'true',validType:'length[1,50]'}}},
				{field:'functionBizCd',title:'功能业务编号',width:100,editor:{type:'validatebox',options:{required:'true',validType:'length[1,50]'}}},
				{field:'functionName',title:'功能名称',width:120,editor:{type:'validatebox',options:{required:'true',validType:'length[0,200]'}}},
				{field:'functionTip',title:'提示',width:100,editor:{type:'validatebox',options:{validType:'length[0,200]'}}},
				{field:'sequenceNo',title:'序号',width:40,editor:{type:'numberbox',options:{required:'true',max:'100'}}},
				//TODO:“功能业务编号”，应不重复/“功能类型”列,应可以选择.
				{field:'functionTypeCd',title:'功能类型',width:80,editor:{type:'validatebox',options:{validType:'length[1,50]'}}},
				{field:'action',title:'操作',width:60,align:'center',
					formatter:function(value,row,index){
						var id =row.appFunctionId;
						var d = '<a href="#" onclick=deleteFunc('+index+',"'+id+'") >删除</a>';
						return d;
					}
				}
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					var rowSel=$('#funcTable').datagrid('getSelected');
					$('#funcTable').datagrid('endEdit', rowSel);
					var index = $('#funcTable').datagrid('getRows').length;
					$('#funcTable').datagrid('appendRow',{
						appFunctionId : '',
						functionCd:'',
						functionBizCd:'',
						functionName:'',
						functionTip:'',
						functionTypeCd:'',
						sequenceNo:index
					});
					$('#funcTable').datagrid('selectRow', index);
					$('#funcTable').datagrid('beginEdit', index);
				}
			}]
		});
	});
	
	function deleteFunc(index,id){
		$.messager.confirm('Confirm','Are you sure?',function(r){
			if (r){
				$('#funcTable').datagrid('deleteRow', index);
				var nextSelect=index>0?index-1:0;
				$('#funcTable').datagrid('selectRow', nextSelect);
				if(id){
					$.post("${ctx}/app/app-page!deleteFunc.action",{appFunctionId:id} , function(result) {
						$('#funcTable').datagrid('acceptChanges');
					});
				}else{
					$('#funcTable').datagrid('acceptChanges');
				}
			}
		});
	}
	
	function savePage(){
		$('#saveForm').form('submit',{
			url:"${ctx}/app/app-page!save.action",
			onSubmit:function(){
				var rows = $('#funcTable').datagrid('getRows');
				for(var i =0;i<rows.length;i++){
					var index = $('#funcTable').datagrid('getRowIndex',rows[i]);
					$('#funcTable').datagrid('endEdit',index);
				};
				Convert.setChildren2Form("saveForm","funcTable");
				return $(this).form('validate');
			},
			success:function(data){
				if(data == 'success'){
				//	$.messager.alert('Info','保存成功');
					$('#w').window('close');
					$('#tt').datagrid('reload');
				}else{
					$.messager.alert('Info','保存失败,请重试！');
				}
			}
		});
	}
	function deletePage(id){
		$.messager.confirm('Confirm','Are you sure?',function(r){
			if(r){
				$.post('${ctx}/app/app-page!delete.action',{id:id},function(r){
					if(r.success){
						$.messager.alert('Info','删除成功');
						$('#w').window('close');
						$('#tt').datagrid('reload');
					}else{
						$.messager.alert('Info','删除失败');
					}
				});
			}
		});
	}
</script>