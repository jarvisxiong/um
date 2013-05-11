<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<table id="position_list_ul"> 
	<col width="50px"/>
	<col style="min-width: 150px;"/>
	<col width="120px"/>
	<thead class="panel-header">
	<tr>
		<td style="line-height: 23px;padding-left:5px;">序号</td>
		<td style="text-align: left;padding-left:5px;">机构</td>
		<td style="text-align: left;padding-left:5px;">职位名称</td>
		<td style="text-align: center;">操作</td>
	</tr>
	</thead>
	<tbody>
	<s:iterator value="acctRelPosList" var="vo"  status="stat">
	<tr style="text-align: left;">
		<td style="text-align: center;padding-left:5px;" class="posId" posId="${vo.plasSysPositionId}">${stat.index+1}</td>
		<td style="text-align: left;padding:0 5px;">${vo.orgName}</td>
		<td class="posName">${vo.sysPosName}</td><%--(${vo.sysPosCd}) --%>
		<td>
			<input type="button" keybutton="true" class="plbtn plred" value="删除" onclick="$(this).parent().parent().remove();refreshIndex();"/>
		</td>
	</tr>
	</s:iterator>
	</tbody>
</table>
