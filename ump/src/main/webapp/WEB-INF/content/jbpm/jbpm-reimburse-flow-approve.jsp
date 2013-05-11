<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<%@page import="java.util.Map"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<title>管理区域</title>
</head>

<body>
<div>
	<div class="mail_corner_tl"></div>
	<div class="mail_corner_tr"></div>
	<div class="mail_corner_tc"></div>
</div>
<div class="mainCenter">
<div class="tableDiv">
<!--<div class="titleBar"><span class="Eng">Reimbursement Approval</span><span class="Chi">报销审批</span></div>-->
<div class="greenline fontTitle clear"></div>
<s:form cssClass="searchForm" id="searchApprove" action="jbpm-reimburse-flow!listApprove" method="post">
<input type="hidden" name="isDesk" value="${isDesk}" />
<input type="hidden" id="pageNoApproveHidden" name="pageApprove.pageNo" value="0"/>
<div class="search">
	    <div>
	    <table>
	    <tr>
	    <td><s:text name="jbpmJbpmReimburse.statusCd"/>:</td>
	    <td><s:select list="mapApproveStatus" listKey="key" listValue="value" name="statusCd" id="statusCd"/></td>
	    <td><s:text name="jbpm.searchScop"/>:</td>
	    <td><div class="radioDiv"><s:radio list="mapSearchScop" listKey="key" listValue="value" name="searchScop" id="searchScop"/></div></td>
	    <td>
	    <div class="button" onclick="listApprove();"><div class="btn_search"></div><span class="text"><s:text name="common.search" /></span></div>
	    </td>
	    </tr>
	    </table>
	    </div>
</div>
</s:form>
<div class="tableContain" >
<table class="showTable" cellpadding="0" cellspacing="0" >
	<thead>
		<tr>
		<th width="15%"><s:text name="jbpmJbpmReimburse.reimburseCd"/></th>
		<td width="1"><div class="border_split"></div></td>
		<th width="10%"><s:text name="jbpmJbpmReimburse.userName"/></th>
		<td width="1"><div class="border_split"></div></td>
		<th width="12.5%"><s:text name="jbpmJbpmReimburse.travelPlace"/></th>
		<td width="1"><div class="border_split"></div></td>
		<th width="15%"><s:text name="jbpmJbpmReimburse.reiDate"/></th>
		<td width="1"><div class="border_split"></div></td>
		<th width="10%"><s:text name="jbpmJbpmReimburse.amountFee"/></th>
		<td width="1"><div class="border_split"></div></td>
		<th width="12.5%"><s:text name="jbpmJbpmReimburse.approver"/></th>
		<td width="1"><div class="border_split"></div></td>
		<th width="10%"><s:text name="jbpmJbpmReimburse.statusCd"/></th>
		<td width="1"><div class="border_split"></div></td>
		<th width="15%"><s:text name="common.operate"/></th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="pageApprove.result">
		<tr id="0" class="editTr" onclick="openTask('${statusCd}','${jbpmReimburseId}','${userCd}'=='<%=SpringSecurityUtils.getCurrentUiid()%>','${pageApprove.pageNo}','${searchScop}');">
			<td>${reimburseCd}</td>
			<td width="1"></td>
			<td>${userName}</td>
			<td width="1"></td>
			<td class="split" title="${travelPlace}">${travelPlace}</td>
			<td width="1"></td>
			<td><s:property value="reiDate" /></td>
			<td width="1"></td>
			<td><s:property value="amountFee" /></td>
			<td width="1"></td>
			<td class="split"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("approver"))%> </td>
			<td width="1"></td>
			<td><p:code2name mapCodeName="mapApproveStatus" value="statusCd"/></td>
			<td width="1"></td>
			<td>
			<input type="hidden" id="taskId_${jbpmReimburseId}" value="${taskId}" />
			<div class="button"><div class="btn_approve"></div><span class="text">
			<s:if test="searchScop==0">
			<s:text name="common.auditing"/>
			</s:if><s:else>
			<s:text name="common.view"/>
			</s:else></span></div>
			</td>
		</tr>
	</s:iterator>
	</tbody>
</table>
<div class="pageBar"><div><p:page pageInfo="pageApprove" key="Approve"/></div>
</div>
</div>
</div>
</div>
<div>
	<div class="mail_corner_bl"></div>
	<div class="mail_corner_br"></div>
	<div class="mail_corner_bc"></div>
</div>
</body>
</html>
