<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<s:if test="appModuleId == null || appModuleId == '' ">
	<div id="rightModulePanel" class="easyui-panel" title="新增模块" style="overflow-x:hidden;">
</s:if>
<s:if test="appModuleId != null && appModuleId != '' ">
	<div id="rightModulePanel" class="easyui-panel" title="编辑模块" style="overflow-x:hidden;">
</s:if>

<form id="ffModule" method="post">
	<input type="hidden" name="id" value="${appModuleId}" /> 
	<input type="hidden" name="parentId" value="${parentId}" /> 
	<table class="edit_table">
		<tr>
			<td style="width:80px;">模块名称<span style="color:red">*</span></td>
			<td>
				<input style="width:100%;" type="text" name="moduleName"  class="easyui-validatebox" required="true" validType="length[0,50]" value="${moduleName}" /> 
				<input type="hidden" name="moduleCd" value="${moduleCd}" /> 
			</td>
		</tr>
		<tr>
			<td>显示序号<span style="color:red">*</span></td>
			<td><input style="width:100%;" type="text" name="sequenceNo" value="${sequenceNo}"  class="easyui-numberbox" required="true" validType="length[1,5]"/></td>
		</tr>
		<tr>
			<td>访问URL</td>
			<td><input style="width:100%;" type="text" name="linkUrl" value="${linkUrl}"  class="easyui-validatebox" validType="length[0,100]"/> </td>
		</tr>
		<%-- 不适用
		<tr>
			<td>所在层级</td>
			<td><input type="text" value="${treeLevel}" readonly="readonly"/> </td>
		</tr>
		 --%>
		<tr>
			<td valign="top">备注</td>
			<td><textarea type="text" name="remark" style="width:100%;height:50px;" class="easyui-validatebox" validType="length[0,200]">${remark}</textarea> </td>
		</tr>
		<s:if test="appModuleId != null && appModuleId != '' ">
		<tr>
			<td>创建人</td>
			<td>${creator}</td>
		</tr>
		<tr>
			<td>创建日期</td>
			<td><s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss"/> </td>
		</tr>
		<tr>
			<td>更新人</td>
			<td>${updator}</td>
		</tr>
		<tr>
			<td>更新日期</td>
			<td><s:date name="updatedDate" format="yyyy-MM-dd HH:mm:ss"/> </td>
		</tr>
		<tr>
			<td>更新版本</td>
			<td>${recordVersion} </td>
		</tr>
		</s:if>
		<tr>
			<td colspan="2" >
				<div class="toolbar" style="text-align: center;">
					<a href="#" class="easyui-linkbutton" plain="false"  iconCls="icon-save" onclick="saveModule();">保存</a>
					<a href="#" class="easyui-linkbutton" plain="false"  iconCls="icon-undo" onclick="resetModule()">重置</a>
					<s:if test="appModuleId != null && appModuleId != ''">
					<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-undo" onclick="removeModule('${appModuleId}')">删除</a>
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
</div>

<script type="text/javascript">
	function saveModule(){
		$('#ffModule').form('submit', {
			url:'${ctx}/app/app-module!save.action',
			onSubmit: function(){
				var flag = $('#ffModule').form('validate');
				return flag;
			},
			success:function(appModuleId){
				if(appModuleId != 'failure'){

					$.messager.alert('提示','保存成功!');
					$.ajaxSettings.async = false;
					$('#leftTree').tree('reload');
					var node = $('#leftTree').tree('find',appModuleId);
					if(node){
						$('#leftTree').tree('expandTo',node.target);
						$('#leftTree').tree('select',node.target);
					}
					$.ajaxSettings.async = true;

					//TODO:刷新模块 app-module-menu.jsp
					showModule(appModuleId);
					//$("#moduleDetailPanel").html('');
					
				}else{
					$.messager.alert('提示','保存失败!');
				}
			}
		});
	}
	function resetModule(){
		$('#ffModule')[0].reset();
	}
	function cancelModule(){
		$("#moduleDetailPanel").hide();
	}
</script>