<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<title>管理区域</title>
</head>

<body>
<div >
<div>
	<div class="mail_corner_tl"></div>
	<div class="mail_corner_tr"></div>
	<div class="mail_corner_tc"></div>
</div>
<div class="mainCenter">
<div class="tableDiv">
<!--<div class="titleBar"><span class="Eng">Reimbursement Application</span><span class="Chi">报销申请</span></div>-->
<s:form cssClass="searchForm" id="searchApply" action="jbpm-reimburse-flow!listApply" method="post" onsubmit="return false;">
<s:hidden name="isDesk"/>
<s:hidden name="page.pageSize"/>
<input type="hidden" id="pageNoApplyHidden"  name="page.pageNo" value="0"/>
<div class="search">
    <div>
    <table>
	    <tr>
	    <td>申请人CD:</td>
	    <td><s:textfield cssClass="input" name="filter_LIKES_userCd" id="filter_LIKES_userCd" /></td>
	    <td><s:text name="jbpmJbpmReimburse.statusCd"/>:</td>
	    <td><s:select list="mapApproveStatus" listKey="key" listValue="value" name="filter_EQS_statusCd" id="filter_EQS_statusCd"/></td>
	    <td><s:text name="jbpmJbpmReimburse.reimburseCd"/>:</td>
	    <td><s:textfield cssClass="input" name="filter_LIKES_reimburseCd" id="filter_LIKES_reimburseCd" /></td>
	    <td>
	    <div class="toolBar" >
	    <div class="button" onclick="listApply();"><div class="btn_search"></div><span class="text"><s:text name="common.search" /></span></div>
	  	<security:authorize ifAnyGranted="A_ROLE_CN">
		<div class="button" onclick="sendManage();"><div class="btn_sendM"></div><span class="text">发放管理</span></div>
		</security:authorize>
		</div>
	   </td>
	    </tr>
	</table>
    </div>
</div>
</s:form>
<div class="tableContain" >
<table class="showTable"  cellpadding="0" cellspacing="0" >
	<thead>
		<tr>
<!--		<th width="26">选择</th>-->
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
	<s:iterator value="page.result">
		<tr id="0" onclick="openTask('${statusCd}','${jbpmReimburseId}','${approver}'=='<%=SpringSecurityUtils.getCurrentUiid()%>','${pageApprove.pageNo}','${searchScop}');"  class="editTr">
<!--			<td><input type="checkbox" /> </td>-->
			<td>${reimburseCd}</td>
			<td width="1">&nbsp;</td>
			<td>${userName}</td>
			<td width="1">&nbsp;</td>
			<td class="split" title="${travelPlace}">${travelPlace}</td>
			<td width="1">&nbsp;</td>
			<td><s:property value="reiDate" /></td>
			<td width="1">&nbsp;</td>
			<td><s:property value="amountFee" /></td>
			<td width="1">&nbsp;</td>
			<td class="split"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("approver"))%> </td>
			<td width="1">&nbsp;</td>
			<td><p:code2name mapCodeName="mapApproveStatus" value="statusCd"/></td>
			<td width="1">&nbsp;</td>
			<td>
			<input class="taskId" type="hidden" id="taskId_${jbpmReimburseId}" value="<p:code2name mapCodeName="mapId2TaskId" value="jbpmReimburseId"/>" />
			<s:if test="statusCd==2 || statusCd==-1 ">
			<div class="button"><div class="btn_edit"></div><span class="text"><s:text name="common.edit" /></span></div>
			</s:if>
			<s:else>
			<div class="button"><div class="btn_view"></div><span class="text"><s:text name="common.view" /></span></div>
			</s:else>
			</td>
		</tr>
	</s:iterator>
	</tbody>
</table>
<div class="pageBar"><p:page key="Apply" /></div>
</div>
</div>
</div>
</div>
<div>
	<div class="mail_corner_bl"></div>
	<div class="mail_corner_br"></div>
	<div class="mail_corner_bc"></div>
</div>
<div style="height: 20px;"></div>
</body>
</html>
