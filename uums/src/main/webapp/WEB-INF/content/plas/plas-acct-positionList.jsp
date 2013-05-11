<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="datagrid-body">
<div style="color:red;" id="tip_operatePos"></div>
<table style="width:100%;">
	<col width="50px"/>
	<col width="100px"/>
	<col width="100px"/>
	<col width="200px;"/>
	<col width="120px;"/>
	<security:authorize ifAnyGranted="A_ADMIN">
	<col width="100px;"/>
	</security:authorize>
	<tr class="panel-header">
		<td style="line-height: 23px;">序号</td>
		<td>机构</td>
		<td>职位简称</td>
		<td>职位全称</td>
		<%--
		<td>持有人</td>
		 --%>
		<td>职位有效状态</td>
		<security:authorize ifAnyGranted="A_ADMIN">
		<td>操作</td>
		</security:authorize>
	</tr>
	<s:if test="acctRelPosList != null">
	<s:iterator value="acctRelPosList" var="vo"  status="stat">
	<tr>
		<td>&nbsp;${stat.index+1}</td>
		<td>&nbsp;${vo.orgName}</td>
		<td>&nbsp;${vo.shortName}</td>
		<td>&nbsp;${vo.sysPosName}(${vo.sysPosCd})</td>
		<%--
		<td>&nbsp;[${vo.uiid},${vo.userName}]</td>
		 --%>
		<td>&nbsp;${vo.activeBl}
			<s:if test="#vo.activeBl == true">
				有效
			</s:if>
			<s:else>
				失效
			</s:else>
		</td>
		<security:authorize ifAnyGranted="A_ADMIN">
		<td>
			<a href="#" class="easyui-linkbutton" plain="false" iconCls="icon-remove" onclick="cleanPositionRelAcct($(this),'${plasSysPositionId}')">收回</a>
		</td>
		</security:authorize>
	</tr>
	</s:iterator>
	</s:if>
</table>
</div>

<security:authorize ifAnyGranted="A_ADMIN">
<script type="text/javascript">
//清空关系账号
function cleanPositionRelAcct(jDom,positionId){
	var url = '${ctx}/plas/plas-sys-position!cleanPositionRelAcct.action';
	$('#tip_operatePos').hide();
	$.post(url, {positionId: positionId}, function(result){
		if('success' == result){
			$('#tip_operatePos').html('收回成功!').show().fadeOut(2000);
			jDom.parent().parent().remove();
		}else{
			$.messager.alert('提示','清空关联账号失败! \n' + result);
		}
	});
}
</script>
</security:authorize>