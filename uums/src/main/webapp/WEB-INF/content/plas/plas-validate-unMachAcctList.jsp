<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="datagrid-body">
<table style="width:100%;" class="panel-header">
	<tr style="line-height: 25px;">
		<td>序号</td>
	
		<td>职员机构名称</td>
		<td>账号</td>
		<td>姓名</td>
		<td>性别</td>
		<td>职员状态</td>
		
		<td>联系电话</td>
		<td>职位机构名称</td>
		<td>账号状态</td>
		<td>是否审核</td>
		<td>职员调整</td>
	</tr>
	<s:if test="acctList != null">
	<s:iterator value="acctList" var="vo"  status="stat">
	<tr>
		<td>${#stat.index }</td>
		<td>${vo.orgName}</td>
		<td>${vo.uiid }</td>
		<td>${vo.userName }</td>
		<td>${vo.sexCd }</td>
		<td><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapServiceStatus()" value="serviceStatus"/></td>
		
		<td>${vo.mobilePhone}/${vo.fixedPhone }</td>
		<td>${vo.posOrgName }</td>
		<td><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapAcctStatus()" value="statusCd"/></td>
		<td></td>
		<td></td>
	</tr>
	</s:iterator>
	</s:if>
</table>
</div>