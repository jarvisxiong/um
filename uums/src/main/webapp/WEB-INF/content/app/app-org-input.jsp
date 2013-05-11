<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<div class="easyui-layout" fit="true">	
	<div region="center" style="padding:5px;" border="false">
		<form id="ff" method="post">
			<input type="hidden" name="id" value="${appOrgId}" /> 
			<table>
				<tr>
					<td>机构业务编号<span style="color:red">&#42;</span>:</td>
					<td><input name="orgBizCd" id="orgBizCd" class="easyui-validatebox" required="false" validType="length[0,50]" type="text" value="${orgBizCd}"></input></td>
				</tr>
				<tr>
					<td>机构名称:</td>
					<td><input name="orgName" id="orgName" class="easyui-validatebox" required="true" validType="length[1,50]" type="text" value="${orgName}"></input></td>
				</tr>
				<tr>
					<td>上级机构名称:</td>
					<td>
						<input name="parentId" id="parentId" required="false" validType="length[1,50]" type="text" value="${parentId}"></input>
					</td>
				</tr>
				<tr>
					<td>机构负责人:</td>
					<td>
						<input name="resUserId" id="resUserId" required="false" validType="length[0,50]" type="text" value="${resUserId}"></input>
					</td>
				</tr>
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
				<a id="btnSaveOrgDetail" href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveOrgDetail();">保存</a>
				<s:if test="appOrgId != null && appOrgId != ''">
				<a id="btnRemoveOrgDetail" href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteOrgDetail('${appOrgId}');">删除</a>
				</s:if>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		//上级机构名称
		$('#parentId').combotree({
			url:'${ctx}/admin/app/app-org!loadOrgTreeRootJson.action', 
			editable: false
		});
		//机构责任人
		$('#resUserId').combotree({
			url:'${ctx}/admin/app/app-user!loadUserTreeNoRootJson.action', 
			editable: false,
			onBeforeSelect: function(node){
				if("user" != node.attributes.nodeType){
					return false;
				}else{
					return true;
				}
			}
		});
	});
</script>