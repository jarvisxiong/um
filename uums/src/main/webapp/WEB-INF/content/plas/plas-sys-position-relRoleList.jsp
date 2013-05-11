<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<s:if test="isAllRoleFlg=='true'">
	<span style="font-weight:bold;color: red;">包含角色包角色!</span>
</s:if>
<s:else>
	<span style="font-weight:bold;color: red;">不包含角色包角色!</span>
</s:else>
<div id="relRoleListPanel" class="datagrid-body" style="width:100%;margin-top: 10px;">
<input type="hidden" id="sysPositionId" value="${plasSysPositionId}"/>
<div style="width:100%;height:350px;overflow-y: auto;">
	<table style="width:100%;" id="maskDiv"> 
		<tr class="panel-header">
				<td style="padding-left:5px;width: 10%;" align="center">
					<s:checkbox name="isAllRoleFlg" id="isAllRoleFlg" title="勾选搜索所有角色" onchange="loadRolesList();"></s:checkbox>
				</td>
				<td style="padding-left:5px;width: 30%;">应用系统</td>
				<td style="padding-left:5px;width: 25%;">模块</td>
				<td style="padding-left:5px;width: 35%;">角色</td>
		</tr>
		
		<s:iterator value="relRoleList" var="vo" status="stat">
		<s:set name="curSysName">${appName}</s:set>
		<s:set name="curGroupName">${groupRoleName}</s:set>
		
		<tr style="cursor: pointer;">
			<td style="padding-right:5px;text-align: right;">${stat.index+1}</td>
			<td style="padding-left:5px;">
				<div style="margin-right:5px;overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" 
					 title="${appName}/${groupRoleName}/${roleName}">
					 <%-- 太神奇了，要加#号 --%>
					 <s:if test="#curSysName == #preSysName">
					 	&nbsp;
					 </s:if>
					 <s:else>
						 ${appName}
					 </s:else>
				</div>
			</td>
			<td style="padding-left:5px;">
				<div style="margin-right:5px;overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" 
					 title="${appName}/${groupRoleName}/${roleName}">
					 <%-- 太神奇了，要加#号 --%>
					 <s:if test="#curGroupName == #preGroupName">
					 	&nbsp;
					 </s:if>
					 <s:else>
						${groupRoleName}
					 </s:else>
				</div>
			</td>
			<td style="padding-left:5px;">
				<div style="margin-right:5px;overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" 
					 title="${appName}/${groupRoleName}/${roleName}">${roleName}
				</div>
			</td>
		</tr>
		<s:set name="preSysName">${appName}</s:set>
		<s:set name="preGroupName">${groupRoleName}</s:set>
		
		</s:iterator>
</table>
</div>
</div>

<script type="text/javascript">
$(function(){
	$('#maskDiv tr').hover(
	 	function () {
	  		$(this).addClass("datagrid-row-over");
		},
		function () {
		  	$(this).removeClass("datagrid-row-over");
		}
	);
});
function loadRolesList(){
	var isAllFlg = $("#isAllRoleFlg").attr("checked");
	var sysPosId = $("#sysPositionId").val();
	$("#maskDiv").html('<div style="height:60px;width:100%;">&nbsp;</div>').mask('正在搜索角色,请稍候...');
	var url = '${ctx}/plas/plas-sys-position!viewRelRoleList.action';
	$.post(url, {plasSysPositionId: sysPosId,isAllRoleFlg: isAllFlg}, function(result) {
	 	$("#maskDiv").html('');
	 	$('#relRoleListPanel').html(result).show();
		$.parser.parse('#relRoleListPanel');
	}); 
}
</script>