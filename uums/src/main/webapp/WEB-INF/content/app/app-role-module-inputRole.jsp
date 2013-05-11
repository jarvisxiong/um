<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script>
	var optionsModule={
	        url:'${ctx}/admin/app/app-role-module!saveRole.action',
	        onSubmit: function(){
	        	var flag=$('#ffRole').form('validate');
	        	return flag;
	        },
	        success:function(data){
	        	var id=data;
	        	var url="${ctx}/admin/app/app-role-module!inputRole.action?appRoleId="+id;
	        	$.post(url,function(result) {
					$('#centerDiv').html(result);
				});
	        	$('#tt').tree("reload");
	        }
		};
	function save(){
		$('#ffRole').form('submit', optionsModule);
	}
</script>
<form id="ffRole" method="post" >
		<input type="hidden" name="appRoleId" value="${appRole.appRoleId}" /> 
		<input type="hidden" name="id" value="${id}" /> 
		<table>
			<tr>
				<td>角色代码:</td>
				<td><input name="appRole.roleCd" id="roleCd" class="easyui-validatebox" required="true" validType="length[0,50]" type="text" value="${appRole.roleCd}"></input></td>
			</tr>
			<tr>
				<td>角色名称:</td>
				<td><input name="appRole.roleName" id="roleName"  class="easyui-validatebox" required="true" validType="length[0,50]" type="text" value="${appRole.roleName}"></input></td>
			</tr>
			<tr>
				<td>备注:</td>
				<td><textarea name="appRole.remark" class="easyui-validatebox" validType="length[0,200]">${appRole.remark}</textarea> </td>
			</tr>
		</table>
</form>