<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="easyui-layout" fit="true">	
	<div region="center" style="padding:5px 20px 5px 5px;+position: relative;overflow-x:hidden;" border="false">
		<form id="ff" method="post" validate="true">
			<input type="hidden" name="id" id="plasRolePackId" value="${plasRolePackId}" /> 
			<table class="edit_table">
				<tr>
					<td style="text-align: right;width: 10%;">包名:</td>
					<td style="width:40%;"><input style="width:95%;" name="packName" id="packName" class="easyui-validatebox" required="true"  type="text" value="${packName}" validType="length[0,50]"></input></td>
					<td style="text-align: right;width: 10%;">是否可用:</td>
					<td style="width: 40%;">
					<s:checkbox name="enableFlg" id="enableFlg"></s:checkbox></td>
				</tr>
				<tr>
					<td style="text-align: right;width: 10%;" valign="top">备注:</td>
					<td colspan="3" style="width: 90%;"><textarea style="width:99%;" name="remark" id="remark" class="easyui-validatebox" validType="length[0,200]">${remark}</textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div region="south" border="false" style="height:30px;line-height:30px;">
		<div class="toolbar">
			<%--
			<s:if test="plasRolePackId != null">
				<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="editrow('${plasRolePackId}');">刷新</a>
			</s:if>
			--%>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="savePack();">保存</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closePop();">关闭</a>
		</div>
	</div>
</div>

<script type="text/javascript">
function savePack(){
	$('#ff').form('submit',{
		url:"${ctx}/plas/plas-role-pack!save.action",
		onSubmit:function(){
			return $(this).form('validate');
		},
		success:function(data){
			if(data == 'success'){
				$('#w').window('close');
				$('#tt').datagrid('reload');
			}else{
				$.messager.alert('Info','保存失败,请重试！');
			}
		}
	});
}
function closePop(){
	$('#w').window('close');
}
</script>