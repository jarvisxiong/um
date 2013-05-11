<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<table id="position_list_old"> 
	<col width="50px"/>
	<col style="min-width: 150px;"/>
	<col width="120px"/>
	<thead class="panel-header">
	<tr>
		<td style="text-align: center;line-height: 23px;padding-left:5px;">序号</td>
		<td style="text-align: left;padding-left:5px;">机构</td>
		<td style="text-align: left;padding-left:5px;">职位名称</td>
	</tr>
	</thead>
	<tbody>
	<s:iterator value="acctRelPosListOld" var="vo"  status="stat">
	<tr style="text-align: left;">
		<td style="text-align: center;padding-left:5px;">${stat.index+1}</td>
		<td style="text-align: left;padding:0 5px;">${vo.orgName}</td>
		<td>${vo.sysPosName}</td><%--(${vo.sysPosCd}) --%>
	</tr>
	</s:iterator>
	</tbody>
</table>
