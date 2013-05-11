<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" href="${ctx}/css/content.css" type="text/css" />
<script language="javascript" src="${ctx}/js/jquery.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery.form.pack.js" type="text/javascript"></script>
<script src="${ctx}/js/jbpm/jbpm.js" type="text/javascript"></script>
<script src="${ctx}/js/table.js" type="text/javascript"></script>
<script src="${ctx}/js/jbpm/style.js" type="text/javascript"></script>
<title>发放管理</title>
<script language="javascript"><!--
$(function() {
	config = {
		ctx : '${ctx}',
		isDesk : '${isDesk}',
		taskId : '${taskId}',
		module : 'reimburse',
		pageSize : '${pageSize}',
		styleCall : Style.replace
	};
	if(config['styleCall'])config['styleCall']();
	$("#reimburse_frame",window.parent.document).css("height","90%");
});
function query(){
	$("#searchApply").submit();
}
function makeAllsend(){
	var param=sortIds();
	if (param){
		window.location.href = config['ctx']+'/jbpm/jbpm-'+config['module']+'-flow!send.action?'+param;
	}
}
function sendNotice(){
	var param=sortIds();
	if (param){
		window.location.href = config['ctx']+'/jbpm/jbpm-'+config['module']+'-flow!sendNotice.action?'+param;
	}
}
function sortIds(){
	 var checkbox_ids = new Array();
		$("input[name='jbpmReimburseIds']:checked").each(function(i, dom) {
			checkbox_ids.push("jbpmReimburseIds=" + $(dom).val());
		});
		if(checkbox_ids.length == 0){
			alert("请勾选记录");
			return false;
		}
		var param = checkbox_ids.join("&");
		return param;
}
function checkAll(){
	var flag=$("#checkAll").attr("checked");
	$("input[name='jbpmReimburseIds']").each(function(i, dom) {
		$(dom).attr("checked", flag);
	});
}
</script>
</head>

<body>
<div >

<div class="tableDiv">
<s:form cssClass="searchForm" id="mainForm" action="jbpm-reimburse-flow!listAll" method="post" onsubmit="return false;">
<s:hidden name="isDesk"/>
<s:hidden name="pageSize"/>
<input type="hidden" id="pageNo"  name="page.pageNo" value="0"/>
<div class="search">
    <div>
    <table>
	    <tr>
	    <td>中心:</td>
	    <td><s:select list="mapCenters" listKey="key" listValue="value" name="filter_EQS_centerCd" id="filter_EQS_centerCd"/></td>
	    <td><s:text name="jbpmJbpmReimburse.reimburseCd"/>:</td>
	    <td><s:textfield cssClass="input" name="filter_LIKES_reimburseCd" id="filter_LIKES_reimburseCd" /></td>
	    <td>
	    <div class="toolBar" >
	    <div class="button" onclick="query();"><div class="btn_search"></div><span class="text"><s:text name="common.search" /></span></div>
		</div>
	   </td>
	    </tr>
	</table>
    </div>
</div>
</s:form>
<div class="tableContain">
<table class="showTable">
	<thead>
		<tr>
		<th width="10%"><input id="checkAll" type="checkbox" onclick="checkAll();"/>全选</th>
		<th width="15%"><s:text name="jbpmJbpmReimburse.reimburseCd"/></th>
		<th width="10%"><s:text name="jbpmJbpmReimburse.userName"/></th>
		<th width="35%"><s:text name="jbpmJbpmReimburse.travelPlace"/></th>
		<th width="15%"><s:text name="jbpmJbpmReimburse.reiDate"/></th>
		<th width="15%"><s:text name="jbpmJbpmReimburse.amountFee"/></th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="page.result">
		<tr id="0" class="editTr">
			<td><input type="checkbox" name="jbpmReimburseIds" value="${jbpmReimburseId}" /></td>
			<td onclick="openTask('${statusCd}','${jbpmReimburseId}','${approver}'=='<%=SpringSecurityUtils.getCurrentUiid()%>','${pageApprove.pageNo}','${searchScop}');">${reimburseCd}</td>
			<td onclick="openTask('${statusCd}','${jbpmReimburseId}','${approver}'=='<%=SpringSecurityUtils.getCurrentUiid()%>','${pageApprove.pageNo}','${searchScop}');">${userName}</td>
			<td onclick="openTask('${statusCd}','${jbpmReimburseId}','${approver}'=='<%=SpringSecurityUtils.getCurrentUiid()%>','${pageApprove.pageNo}','${searchScop}');">${travelPlace}</td>
			<td onclick="openTask('${statusCd}','${jbpmReimburseId}','${approver}'=='<%=SpringSecurityUtils.getCurrentUiid()%>','${pageApprove.pageNo}','${searchScop}');"><s:property value="reiDate" /></td>
			<td onclick="openTask('${statusCd}','${jbpmReimburseId}','${approver}'=='<%=SpringSecurityUtils.getCurrentUiid()%>','${pageApprove.pageNo}','${searchScop}');"><s:property value="amountFee" /></td>
		</tr>
	</s:iterator>
	</tbody>
</table>
<div class="button" onclick="sendNotice();"><div class="btn_edit"></div><span class="text">发送通知</span></div>
<div class="button" onclick="makeAllsend();"><div class="btn_edit"></div><span class="text">标记为发放</span></div>
<div class="pageBar"><p:page /></div>
</div>
</div>
</div>
</body>
</html>
