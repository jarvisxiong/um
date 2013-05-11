<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<div class="easyui-layout" fit="true">	
	<div region="center" style="padding:5px;" border="false">
		<form id="ff" method="post">
			<input type="hidden" name="id" value="${appUserId}" /> 
			<table>
				<tr>
					<td>帐号:</td>
					<td>
						<input name="userCode" id="userCode" class="easyui-validatebox" required="true" validType="length[0,50]" type="text" value="${userCode}"></input>
						<span id="userCodeTip" style="display:none;background: none repeat scroll 0 0 #FFFFCC;border: 1px solid #CC9933;padding:3px;"></span>
					</td>
				</tr>
				<tr>
					<td>上级机构名称:</td>
					<td>
						<input name="appOrgId" id="appOrgId" required="true" validType="length[1,50]" type="text" value="${appOrgId}"></input>
					</td>
				</tr>
				<tr>
					<td>姓名:</td>
					<td><input name="userName" id="userName" class="easyui-validatebox" required="true" validType="length[0,50]" type="text" value="${userName}"></input></td>
				</tr>
				<%--
				<tr>
					<td>密码:</td>
					<td><input id="pwd" name="pwd" type="password" class="easyui-validatebox"  required="true" validType="length[3,50]" ></input></td>
				</tr>
				<tr>
					<td>确认密码:</td>
					<td><input id="pwdConfirm" type="password"  class="easyui-validatebox" required="true" validType="length[3,50]"></input></td>
				</tr>
				--%>
				<tr>
					<td>显示序号:</td>
					<td><input name="dispOrderNo" id="dispOrderNo" class="easyui-numberbox" required="true" validType="length[1,50]" type="text" value="${dispOrderNo}"></input></td>
				</tr>
				<%--
				<tr>
					<td>是否启用:</td>
					<td><s:checkbox name="enableFlg"></s:checkbox> </td>
				</tr>
				<tr>
					<td>是否删除:</td>
					<td><s:checkbox name="deleteFlg"></s:checkbox> </td>
				</tr>
				 --%>
				<tr>
					<td>备注:</td>
					<td><textarea name="remark" style="width: 100%;height: 100px;" class="easyui-validatebox" validType="length[0,200]">${remark}</textarea> </td>
				</tr>
			</table>
			
			<div style="width:350px;text-align: center;">
				<a id="btnSave" href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveUserDetail('${userCode}');">保存</a>
				<s:if test="appUserId != null && appUserId != ''">
				<a id="btnRemove" href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteUserDetail('${appUserId}');">删除</a>
				</s:if>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		//上级机构名称
		$('#appOrgId').combotree({
			url:'${ctx}/admin/app/app-org!loadOrgTreeRootJson.action', 
			editable: false
		}); 
	});
</script>