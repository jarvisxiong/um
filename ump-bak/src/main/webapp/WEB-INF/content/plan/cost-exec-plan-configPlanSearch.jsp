<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>


<div id="configPlanContentDiv" style="padding-left:5px;">
	<table class="table_list" cellpadding="0" cellspacing="0" style="table-layout: fixed; width: 100%;">
		<thead>
			<tr class="header">
				<th>业态名称</th>
				<th width="100">创建时间</th>
				<th width="100" style="background: none;">当前状态</th>
			</tr>
		</thead>
		
		<tbody>
			<s:iterator value="plans">
			<tr class="data">
				<td style="cursor: pointer;">
					<div id="${costExecutionPlanId}PlanNameDiv">${executionPlanName}</div>
				</td>
				<td align="left">
					<s:date name="createdDate" format="yyyy-MM-dd"/>
				</td>
				<td align="left">
					<c:if test="${activeFlg == '1'}">
						<span style="color: green;">已激活</span>
					</c:if>
					<c:if test="${activeFlg == '0'}">
						<span style="color: red;">已禁用</span>
					</c:if>
				</td>
			</tr>
			</s:iterator>
		</tbody>
	</table>
</div>

