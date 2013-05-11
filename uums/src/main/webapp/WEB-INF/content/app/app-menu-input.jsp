<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<s:if test="appMenuId == null || appMenuId == '' ">
	<div id="rightMenuPanel" class="easyui-panel" title="新增菜单" style="overflow-x:hidden;">
</s:if>
<s:if test="appMenuId != null && appMenuId != '' ">
	<div id="rightMenuPanel" class="easyui-panel" title="编辑菜单" style="overflow-x:hidden;">
</s:if>

<form id="ffMenu" method="post">
	<input type="hidden" name="id" value="${appMenuId}" /> 
	<input type="hidden" name="appMenuId" value="${appMenuId}" /> 
	<input type="hidden" name="appModuleId" value="${appModule.appModuleId}" /> 
	<table class="edit_table">
		<col style="width:80px;"/>
		<col />
		<col style="width:80px;"/>
		<col />
		<%--
		<tr>
			<td>上级模块名称</td>
			<td>
				
			</td>
		</tr>
		 --%>
		<tr>
			<td style="padding-left:5px;">菜单名称<span style="color:red">*</span></td>
			<td style="padding-left:5px;">
				<input style="width:90%;" type="text"  name="menuName" value="${menuName}" class="easyui-validatebox" required="true" validType="length[1,50]"/> 
				<input type="hidden" name="menuCd" value="${menuCd}" /> 
			</td>
			<td style="padding-left:5px;">菜单类型<span style="color:red">*</span></td>
			<td style="padding-left:5px;"><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapAppMenuType()" name="menuTypeCd" listKey="key" listValue="value"/></td>
		</tr>
		<tr>
			<td style="padding-left:5px;">菜单提示</td>
			<td><input style="width:90%;" type="text"  name="menuTip" value="${menuTip}" class="easyui-validatebox" validType="length[0,50]"/> </td>
			<td style="padding-left:5px;">显示序号<span style="color:red">*</span></td>
			<td><input style="width:90%;" type="text"  name="sequenceNo" value="${sequenceNo}"  class="easyui-numberbox" required="true" validType="length[1,5]"/> </td>
		</tr>
		</tr>
		<tr>
			<td style="padding-left:5px;">是否叶子节点</td>
			<td style="padding-left:5px;"><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapEnableFlgNum()" name="leafFlg" listKey="key" listValue="value"/></td>
			<td style="padding-left:5px;">是否允许修改</td>
			<td style="padding-left:5px;"><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapEnableFlgNum()" name="fixFlg" listKey="key" listValue="value"/></td>
		</tr>
		<tr>
			<td style="padding-left:5px;">是否窗口弹出</td>
			<td style="padding-left:5px;" colspan="3"><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapEnableFlgNum()" name="sameWinFlg" listKey="key" listValue="value"/></td>
		</tr>
		<tr>
			<td style="padding-left:5px;">对应的页面<span style="color:red">*</span></td>
			<%-- TODO:下拉模糊检索 --%>
			<td><s:select style="width:100%;" name="appPageId" id="appPageId" value="appPage.appPageId" list="#request.appPageList" listKey="appPageId" listValue="pageName" cssClass="easyui-combobox" ></s:select></td>
		</tr>
		<tr>
			<td style="padding-left:5px;">路径</td>
			<td style="padding-left:5px;"><input style="width:90%;" type="text"  name="linkUrl" value="${linkUrl}" class="easyui-validatebox" validType="length[0,50]"/> </td>
			<td style="padding-left:5px;">图标</td>
			<td style="padding-left:5px;"><input style="width:90%;" type="text"  name="iconName" value="${iconName}" class="easyui-validatebox" validType="length[0,50]"/> </td>
		</tr>
		<tr>
			<td style="padding-left:5px;" valign="top">备注</td>
			<td style="padding-left:5px;" colspan="3"><textarea type="text" name="remark" style="width:100%;height:50px;" class="easyui-validatebox" validType="length[0,200]">${remark}</textarea> </td>
		</tr>
		
		<s:if test="appMenuId != null && appMenuId != '' ">
		<tr>
			<td style="padding-left:5px;">创建人</td>
			<td style="padding-left:5px;">${creator}</td>
			<td style="padding-left:5px;">创建日期</td>
			<td style="padding-left:5px;"><s:date id="createdDate" name="createdDate" format="yyyy-MM-dd hh:mm:ss"/> </td>
		</tr>
		<tr>
			<td style="padding-left:5px;">更新人</td>
			<td style="padding-left:5px;">${updator}</td>
			<td style="padding-left:5px;">更新日期</td>
			<td style="padding-left:5px;"><s:date id="updatedDate" name="updatedDate" format="yyyy-MM-dd hh:mm:ss"/> </td>
		</tr>
		<tr>
			<td style="padding-left:5px;">更新版本</td>
			<td style="padding-left:5px;" colspan="3">${recordVersion} </td>
		</tr>
		</s:if>
		<tr>
			<td colspan="4" >
				<div class="toolbar" style="text-align:center;">
					<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-save" onclick="saveMenu();">保存</a>
					<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-undo" onclick="resetMenu()">重置</a>
					<s:if test="appMenuId != null && appMenuId != ''">
					<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-undo" onclick="removeMenuWithTip('${appMenuId}')">删除</a>
					</s:if>
				</div>
			</td>
		</tr>
	</table>
</form>

<%-- 授权角色 --%>
<div>
	<s:iterator value="appRoleMenuRels">
	
	
	</s:iterator>
	
</div>
</div>

<script type="text/javascript">
$('#appPageId').combobox({
	//模糊匹配本地查询
	filter : function(q,row){
		var text = row.text;
		if(text.indexOf(q)>-1){
			return true;
		}else{
			return false;
		}
	}
});
//菜单表单
var menuOptions={
	url:'${ctx}/app/app-menu!save.action',
	onSubmit: function(){
		var flag = $('#ffMenu').form('validate');
		return flag;
	},
	success:function(appMenuId){
		if(appMenuId != 'failure'){
			//$.messager.alert('提示','保存成功!');

			$.ajaxSettings.async = false;
			$('#leftTree').tree('reload');
			var node = $('#leftTree').tree('find',appMenuId);
			if(node){
				$('#leftTree').tree('expandTo',node.target);
				$('#leftTree').tree('select',node.target);
			}
			$.ajaxSettings.async = true;

			//刷新模块app-module-menu.jsp
			showMenu(appMenuId);
			//$("#menuDetailPanel").html('');
			
		}else{
			$.messager.alert('提示','保存失败!');
		}
	}
};
function saveMenu(){
	$('#ffMenu').form('submit', menuOptions);
}
function resetMenu(){
	$('#ffMenu')[0].reset();
}
function cancelMenu(){
	$("#menuDetailPanel").hide();
} 
</script>