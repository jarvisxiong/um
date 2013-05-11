<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
 

<div class="datagrid-body" id="disableOrgItems">
	<table cellpadding="0" cellspace="0" style="width:90%;">
	<col width="50px"/>
	<col width="80px"/>
	<col/>
	<thead class="panel-header" style="line-height: 30px;">
		<th>编号</th>
		<th>业务编号</th>
		<th>名称</th>
	</thead>
	<tbody>
	<s:iterator value="disableOrgList">
	<tr title="${orgCd}/${orgBizCd}/${orgName},请点击查看明细" style="cursor:pointer" onclick="refreshOrgArea('${plasOrgId}')">
		<td class="datagrid-cell">${orgCd}</td>
		<td class="datagrid-cell">${orgBizCd}</td>
		<td class="datagrid-cell">${orgName}</td>
	</tr>
	</s:iterator>
	</tbody>
	</table>
</div>

<script language="javascript">
	$(function(){
		$('#disableOrgItems tbody tr').hover(
			function(){
				$(this).addClass('datagrid-row-over');
			},
			function(){
				$(this).removeClass('datagrid-row-over');
			}
		);
	});
</script>