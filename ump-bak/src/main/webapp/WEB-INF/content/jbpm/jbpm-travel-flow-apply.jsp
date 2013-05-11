<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.Constants"%>
<%@page import="org.apache.commons.lang.StringUtils"%><html xmlns="http://www.w3.org/1999/xhtml">
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
<!--<div class="titleBar"><span class="Eng">Business Trip Application</span><span class="Chi">出差申请</span></div>-->

<s:form cssClass="searchForm" id="searchApply" action="jbpm-travel-flow!listApply" method="post" onsubmit="return false;" >

<s:hidden name="isDesk"/>
<s:hidden name="page.pageSize"/>
<input type="hidden" id="pageNoApplyHidden"  name="page.pageNo" value="0"/>
<div class="search">
	    <div>
	    <table>
	    <tr>
	    <td>申请人CD:</td>
	    <td><s:textfield cssClass="input" name="filter_LIKES_userCd" id="filter_LIKES_userCd" /></td>
	    <td><s:text name="jbpmJbpmTravelApply.statusCd"/>:</td>
	    <td><s:select list="mapApproveStatus" listKey="key" listValue="value" name="filter_EQS_statusCd" id="filter_EQS_statusCd"/></td>
	    <td><s:text name="jbpmJbpmTravelApply.travelCd"/>:</td>
	    <td><s:textfield  cssClass="input" name="filter_LIKES_travelCd" id="filter_LIKES_travelCd" /></td>
	    <td>
	    <div class="toolBar" >
	  	<div class="button" onclick="listApply();"><div class="btn_search"></div><span class="text"><s:text name="common.search" /></span></div>
		</div>
		</td>
	    </tr>
	    </table>
	    </div>
</div>
</s:form>
<div class="tableContain" >
<table class="showTable" cellpadding="0" cellspacing="0">
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
	<s:iterator value="page.result">
		<tr id="0" class="editTr" >
			<s:set var="curUiid"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
			<td onclick="openTask('${statusCd}','${jbpmTravelApplyId}','${userCd==curUiid}');" >${travelCd}</td>
			<td width="1">&nbsp;</td>
			<td onclick="openTask('${statusCd}','${jbpmTravelApplyId}','${userCd==curUiid}');" >${userName}</td>
			<td width="1">&nbsp;</td>
			<td onclick="openTask('${statusCd}','${jbpmTravelApplyId}','${userCd==curUiid}');" ><%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("deptCd"))%></td>
			<td width="1">&nbsp;</td>
			<td onclick="openTask('${statusCd}','${jbpmTravelApplyId}','${userCd==curUiid}');" ><%=CodeNameUtil.getPositionNameByCd(JspUtil.findString("positionCd"))%></td>
			<td width="1">&nbsp;</td>
			<td class="split" title="${travelPlace}" onclick="openTask('${statusCd}','${jbpmTravelApplyId}','${userCd==curUiid}');" >${travelPlace}</td>
			<td width="1">&nbsp;</td>
			<td onclick="openTask('${statusCd}','${jbpmTravelApplyId}','${userCd==curUiid}');" ><p:code2name mapCodeName="mapApproveStatus" value="statusCd"/></td>
			<td width="1">&nbsp;</td>
			<td onclick="openTask('${statusCd}','${jbpmTravelApplyId}','${userCd==curUiid}');" ><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("approver"))%></td>
			<td width="1">&nbsp;</td>
			<td>
			<input type="hidden" id="taskId_${jbpmTravelApplyId}" value="<p:code2name mapCodeName="mapId2TaskId" value="jbpmTravelApplyId"/>" />
			<s:if test="(statusCd==2 || statusCd==-1 ||userCd==#curUiid)&&statusCd!=4&&statusCd!=1">
			<div class="button"  onclick="openTask('${statusCd}','${jbpmTravelApplyId}','${userCd==curUiid}');" ><div class="btn_edit"></div><span class="text"><s:text name="common.edit" /></span></div>
			</s:if>
			<s:elseif test="statusCd==1">
			<div class="button" onclick="reimburse('${jbpmTravelApplyId}');"><div class="btn_Ent"></div><span class="text">转入报销</span></div>
			</s:elseif>
			<s:else>
			<div class="button"  onclick="openTask('${statusCd}','${jbpmTravelApplyId}','${userCd==curUiid}');" ><div class="btn_view"></div><span class="text"><s:text name="common.view" /></span></div>
			</s:else>
			</td>
		</tr>
	</s:iterator>
	</tbody>
</table>
<div class="pageBar"><div><p:page key="Apply"/></div>
</div>
<div class="clear" align="center" >

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
