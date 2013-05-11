<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<s:if test="plasRoleGroupId == null || plasRoleGroupId == '' ">
	<div class="easyui-panel" title="新增角色组" style="overflow-x:hidden;">
</s:if>
<s:if test="plasRoleGroupId != null && plasRoleGroupId != '' ">
	<div class="easyui-panel" title="编辑角色组" style="overflow-x:hidden;">
</s:if>

<form id="ffRoleGroup" method="post">
	<input type="hidden" name="id" value="${plasRoleGroupId}" /> 
	<input type="hidden" name="parentId" value="${parentId}" /> 
	<table style="width:100%;">
		<col style="width:100px;"/>
		<col/>
		<col style="width:100px;"/>
		<col/>
		<tr>
			<td>角色组编号:</td>
			<td>
				<s:if test="plasRoleGroupId != null">
					${roleGroupCd}
				</s:if>
				<s:else>
					系统生成
				</s:else>
			</td>
		</tr>
		<tr>
			<td>角色组业务编号:</td>
			<td>
				<input type="text" name="roleGroupBizCd" id="roleGroupBizCd"  value="${roleGroupBizCd}"  class="easyui-validatebox" required="true" validType="lengtg[1,20]"/>
				<span style="color:red">*</span>
				<span id="roleGroupBizCdTip" style="color:red;font-weight: bold;display: none;">角色组业务编号已经存在！</span>
				
				<input name="oldRoleGroupBizCd" id="oldRoleGroupBizCd" value="${roleGroupBizCd}" type="hidden"/>
			</td>
			<td>角色组名称:</td>
			<td>				
				<input type="text" name="roleGroupName"  id="roleGroupName" class="easyui-validatebox" required="true" validType="length[0,50]" value="${roleGroupName}"/> 
				<span style="color:red">*</span>
				<span id="roleGroupNameTip" style="color:red;font-weight: bold;display: none;">角色组名称已经存在！</span>
				
				<input name="oldRoleGroupName" id="oldRoleGroupName" value="${roleGroupName}" type="hidden"/>
				<input type="hidden" name="moduleCd" value="${roleGroupName}" /> 
			</td>
		</tr>
		<%-- 
		<tr>
			<td>父模块名称:</td>
			<td>
				<input style="width:100%;" type="text" name="parentName" value="${parentName}" /> 
				
			</td>
		</tr>
		--%>
		<tr>
			<td>显示序号:</td>
			<td><input type="text" name="sequenceNo" value="${sequenceNo}"  class="easyui-numberbox" required="true" validType="length[1,5]"/><span style="color:red">*</span></td>
		</tr>
		<tr>
			<td valign="top">备注:</td>
			<td colspan="3"><textarea name="remark" style="width:80%;height:50px;" class="easyui-validatebox" validType="length[0,200]">${remark}</textarea> </td>
		</tr>
		<s:if test="plasRoleGroupId != null && plasRoleGroupId != '' ">
		<tr>
			<td>创建人</td>
			<td>${creator}</td>
			<td>创建日期</td>
			<td><s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss"/> </td>
		</tr>
		<tr>
			<td>更新人</td>
			<td>${updator}</td>
			<td>更新日期</td>
			<td><s:date name="updatedDate" format="yyyy-MM-dd HH:mm:ss"/> </td>
		</tr>
		<tr>
			<td>更新版本</td>
			<td colspan="3">${recordVersion} </td>
		</tr>
		</s:if>
		<tr>
			<td colspan="4" >
				<div class="toolbar" style="line-height: 60px;">
					<a href="#" class="easyui-linkbutton" plain="false"  iconCls="icon-save" onclick="saveRoleGroup('${parentId}')">保存</a>
					<a href="#" class="easyui-linkbutton" plain="false"  iconCls="icon-undo" onclick="resetRoleGroup()">重置</a>
					<s:if test="plasRoleGroupId != null">
						<a href="#" class="easyui-linkbutton" plain="false"  iconCls="icon-remove" onclick="remove()">删除角色组</a>
					</s:if>
				</div>
			</td>
		</tr>
	</table>
</form>

<%-- 子菜单 --%>
<div>
	<s:iterator value="appMenus">
	
	
	</s:iterator>
</div>

<script type="text/javascript">

function saveRoleGroup(){
	var url1 = '${ctx}/plas/plas-role-group!isRoleGroupBizCdExist.action';
	$.post(url1, {roleGroupBizCd: $('#roleGroupBizCd').val(),oldRoleGroupBizCd: $('#oldRoleGroupBizCd').val()} ,function(result){
		if('success' == result){
			var url = '${ctx}/plas/plas-role-group!isRoleGroupNameExist.action';
			$.post(url,{roleGroupName: $('#roleGroupName').val(),oldRoleGroupName: $('#oldRoleGroupName').val()},function(result){
				if('success' == result){
					$('#ffRoleGroup').form('submit', {
						url:'${ctx}/plas/plas-role-group!save.action',
						onSubmit: function(){
							var flag = $('#ffRoleGroup').form('validate');
							return flag;
						},
						success:function(result){
							if( 'success' == result){
								$("#moduleDetailPanel").hide();
								$("#menuDetailPanel").hide();
								$.messager.alert('提示','保存成功!');
								$('#leftTree').tree('reload');
							}else{
								$.messager.alert('提示',result);
							}
						}
					});
					$('#roleGroupNameTip').hide();
				}else{ 
					$('#roleGroupNameTip').show();
				}
			});
			$('#roleGroupBizCdTip').hide();
		}else{ 
			$('#roleGroupBizCdTip').show();
		}
	});
} 
function resetRoleGroup(){
	$('#ffRoleGroup')[0].reset();
}
</script>
