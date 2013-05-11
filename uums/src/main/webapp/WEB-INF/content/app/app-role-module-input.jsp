<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script>
	var optionsModule={
	        url:'${ctx}/admin/app/app-role-module!save.action',
	        onSubmit: function(){
	        	var flag=$('#ffModule').form('validate');
	        	return flag;
	        },
	        success:function(data){
	        	var id=data;
	        	var url="${ctx}/admin/app/app-role-module!input.action?id="+id;
	        	$.post(url,function(result) {
					$('#centerDiv').html(result);
				});
	        	$('#tt').tree("reload");
	        }
		};
	function save(){
		$('#ffModule').form('submit', optionsModule);
	}
</script>
<form id="ffModule" method="post">
		<input type="hidden" name="id" value="${appRoleModuleId}" /> 
		<input type="hidden" name="parentId" value="${parentId}" /> 
		<table>
			<tr>
				<td>模块代码:</td>
				<td><input name="roleModuleCd" id="roleModuleCd" class="easyui-validatebox" required="true" validType="length[0,50]" type="text" value="${roleModuleCd}"></input></td>
			</tr>
			<tr>
				<td>模块名称:</td>
				<td><input name="roleModuleName" id="roleModuleName"  class="easyui-validatebox" required="true" validType="length[0,50]" type="text" value="${roleModuleName}"></input></td>
			</tr>
			<tr>
				<td>备注:</td>
				<td><textarea name="remark" class="easyui-validatebox" validType="length[0,200]">${remark}</textarea> </td>
			</tr>
		</table>
</form>