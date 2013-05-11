<%@page import="java.util.Map"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
	<table style="width:100%;">
		<thead>
			<tr class="header">
				<th>序号</th>
				<th>名称</th>
				<th>主责方</th>
				<th>关键输出成果</th>
				<th>节点级别</th>
				<th>上级节点</th>
			</tr>
		</thead>
		
		<tbody>	
			<s:iterator value="projNodeList" status="node">
				<tr>
					<td align="left">
						<input type="hidden" id="select_nodeName_${planProjectNodeRelId}" value="${nodeName}"/>
						<input onClick="selected_nodeId=$(this).val();" type="radio" name="name" value="${planProjectNodeRelId}"/>
						${nodeCd}
					</td>
					<td align="left" nowrap="nowrap" class="ellipsisDiv_full">
						<s:if test="treeType==2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</s:if><s:if test="treeType==3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</s:if>${nodeName}
					</td>
					<td align="left">
						${resOrgName}
					</td>
					<td align="left">
						${outputFiles}
					</td>
					<td align="left">
						${treeType}级
					</td>
					<td align="left">
						${remark}
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
