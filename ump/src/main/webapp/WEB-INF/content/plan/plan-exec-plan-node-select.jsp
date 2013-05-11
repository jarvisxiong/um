<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<div style="width:100%;overflow-x:hidden;">
<table style="width: expression(this.parentNode.offsetHeight >this.parentNode.scrollHeight ? '100%' :parseInt(this.parentNode.offsetWidth - 20) + 'px');">
	<thead>
		<tr>
			<th style="text-align: center;width:100px;">序号</th>
			<th style="text-align: left;">名称</th>
			<th style="text-align: left;width:100px"">主责方</th>
			<th style="text-align: left;width:100px">关键输出成果</th>
			<th style="text-align: left;width:40px">级别</th>
			<%--
			<th style="text-align: left;width:80px">上级节点</th>
			 --%>
		</tr>
	</thead>
	
	<tbody>	
		<s:iterator value="projNodeList" status="node">
			<tr class="levelTreeRow${treeType}">
				<td align="left">
					<input type="hidden" id="select_nodeName_${planProjectNodeRelId}" value="${nodeName}"/>
					<input onClick="selected_nodeId=$(this).val();" type="radio" name="name" value="${planProjectNodeRelId}"/>
					${order1}
					<s:if test="order2>0">${order2}</s:if>
					<s:if test="order3>0">${order3}</s:if>
				</td>
				<td align="left" style="overflow:hidden;white-space: nowrap;padding-right:3px;" title="${nodeName}" class="head">
				<%-- class="levelTree${treeType}" --%>
					${nodeName}
				</td>
				<td align="left" style="overflow:hidden;white-space: nowrap;padding-right:3px;" title="${resOrgName}">
					${resOrgName}
				</td>
				<td align="left" style="overflow:hidden;white-space: nowrap;padding-right:3px;" title="${outputFiles}">
					${outputFiles}
				</td>
				<td align="center">
					${treeType}级
				</td>
				<%--
				<td align="left">
					${remark}
				</td>
				 --%>
			</tr>
		</s:iterator>
	</tbody>
</table>
</div>
