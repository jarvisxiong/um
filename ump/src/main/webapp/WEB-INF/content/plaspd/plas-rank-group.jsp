<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<s:if test="page.result.size() > 0">
	<div class="panel-header">
		职级组信息 &nbsp;&nbsp;当前标签 &nbsp;&nbsp;职级
		<%--
		<s:select list="mapUaapTagType" listKey="key" listValue="value" name="tagTypeCd" id="tagTypeCd" onchange="changeTagType(this.value)" value="tmpTagTypeCd"/>
		 --%>
	</div>
	<div  class="datagrid-body">
	<table style="width:100%;border: 0" cellpadding="0" cellspacing="0">
		<tr style="height:30px">
			<td>职级名称</td>
			<td style="text-align: left">操作</td>
		</tr>
		<s:iterator value="page.result" >
		<tr onmouseover="$(this).css({'background-color':'#FBEC88'});" onmouseout="$(this).css({'background-color':''});">
			<td title="点击分配组员">
				<div class="datagrid-cell" style="cursor:pointer;" id="group_${dictCd}" onclick="viewGroup('${dictCd}','${dictName}','${remark }','${updatedDeptCd }','${updatedDate }');">${dictName}</div>
			</td>
			<td style="text-align: left">
				<div class="datagrid-cell">
					<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="viewGroup('${dictCd}','${dictName}','${remark }','${updatedDeptCd }','${updatedDate }')">分配</a>(固化)
				</div>
			</td>
		</tr>
		</s:iterator>
	</table>
	</div>
</s:if>
<s:else>
 	暂未配置标签
</s:else>
