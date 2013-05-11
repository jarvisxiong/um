<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<%@page import="java.util.Map"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%><html xmlns="http://www.w3.org/1999/xhtml">
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
<s:form cssClass="searchForm" id="searchApprove" action="jbpm-travel-flow!listApprove" method="post">
<input type="hidden" name="isDesk" value="${isDesk}" />
<input type="hidden" id="pageNoApproveHidden" name="pageApprove.pageNo" value="0"/>
<div class="search">
	    <table>
	    <tr>
	    <td><s:text name="jbpmJbpmTravelApply.statusCd"/>:</td>
	    <td><s:select list="mapApproveStatus" listKey="key" listValue="value" name="statusCd" id="statusCd"/></td>
	    <td><s:text name="jbpm.searchScop"/>:</td>
	    <td><div class="radioDiv"><s:radio list="mapSearchScop" listKey="key" listValue="value" name="searchScop" id="searchScop"/></div></td>
	    <td>
	    <div class="button" onclick="listApprove();"><div class="btn_search"></div><span class="text"><s:text name="common.search" /></span></div>
	    </td>
	    </tr>
	    </table>
</div>
</s:form>
<div class="tableContain" >
<table class="showTable" align="left"  cellpadding="0" cellspacing="0">
	<thead>
		<tr>
		<th width="15%"><s:text name="jbpmJbpmTravelApply.travelCd"/></th>
		<td width="1"><div class="border_split">&nbsp;</div></td>
		<th width="13%"><s:text name="jbpmJbpmTravelApply.userName"></s:text></th>
		<td width="1"><div class="border_split">&nbsp;</div></td>
		<th width="12%"><s:text name="jbpmJbpmTravelApply.deptCd"/></th>
		<td width="1"><div class="border_split">&nbsp;</div></td>
		<th width="13%"><s:text name="jbpmJbpmTravelApply.positionCd"/></th>
		<td width="1"><div class="border_split">&nbsp;</div></td>
		<th width="13%"><s:text name="jbpmJbpmTravelApply.travelPlace"/></th>
		<td width="1"><div class="border_split">&nbsp;</div></td>
		<th width="10%"><s:text name="jbpmJbpmTravelApply.statusCd"/></th>
		<td width="1"><div class="border_split">&nbsp;</div></td>
		<th width="12%"><s:text name="jbpmJbpmTravelApply.approver"/></th>
		<td width="1"><div class="border_split">&nbsp;</div></td>
		<th width="12%"><s:text name="common.operate"/></th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="pageApprove.result">
		<tr id="0" onclick="openTask('${statusCd}','${jbpmTravelApplyId}','${userCd}'=='<%=SpringSecurityUtils.getCurrentUiid()%>','${pageApprove.pageNo}','${searchScop}');"  class="editTr" >
			<td>${travelCd}</td>
			<td width="1">&nbsp;</td>
			<td>${userName}</td>
			<td width="1">&nbsp;</td>
			<td><%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("deptCd"))%></td>
			<td width="1">&nbsp;</td>
			<td><%=CodeNameUtil.getPositionNameByCd(JspUtil.findString("positionCd"))%></td>
			<td width="1">&nbsp;</td>
			<td class="split" title="${travelPlace}">${travelPlace}</td>
			<td width="1">&nbsp;</td>
			<td><p:code2name mapCodeName="mapApproveStatus" value="statusCd"/></td>
			<td width="1">&nbsp;</td>
			<td><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("approver"))%></td>
			<td width="1">&nbsp;</td>
			<td>
			<input type="hidden" id="taskId_${jbpmTravelApplyId}" value="${taskId}" />
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
<script language="javascript">

</script>
</body>
</html>
