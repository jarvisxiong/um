<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/nocache.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/planold/plan-exec-plan.css" />
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/table.js"></script>
	<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
	<title>计划管理操作日志查看</title>
</head>
<body>
<div class="title_info">
	<div style="float:left;margin-right: 5px;">
		<img src="${ctx}/images/desk2/mail_new.jpg" style="margin-top:3px;"/>
	</div>
	<div style="float:left;">
		计划管理操作日志
	</div>
</div>

<div>
	<div class="left_log">
		<div><img src="${ctx}/pics/email/mail_left_top.jpg"/></div>
		<div class="left_menu">
			<div onclick="changeLogPage('ceoTask')" >副总裁任务</div>
			<div onclick="changeLogPage('workPlan')" >中心月计划</div>
			<div onclick="changeLogPage('prePlan')" >项目前期计划</div>
			<div onclick="changeLogPage('execPlan')" >项目执行计划</div>
			<div  class="menu_click">项目成本计划</div>
			<div onclick="changeLogPage('planWorkCenter')" >中心内部计划</div>
		</div>
		<div><img src="${ctx}/pics/email/mail_left_b.gif"/></div>
	</div>
	<div class="right_log">
		<form action="${ctx}/planold/plan-operation-log!log.action?moduleCd=costPlan" id="mainForm" method="post">
		<div style="margin-bottom: 10px;">
			<fieldset style="padding: 5px;">
			<table class="searchTable">
				<tr>
					<td width="60">项目:</td>
					<td width="120">
						<s:select list="projects" listKey="orgCd" emptyOption="true" listValue="orgName" name="filter_EQS_projectCd"></s:select>
					</td>
					<td width="60">操作对象:</td>
					<td width="80"><s:textfield cssStyle="width:80px" name="filter_LIKES_modifiedObject"/></td>
					<td width="70" align="right">操作时间从</td>
					<td width="80"><s:textfield cssStyle="width:80px" name="filter_GED_createdDate" onfocus="WdatePicker()"/></td>
					<td width="20" align="center">到</td>
					<td><s:textfield cssStyle="width:80px" name="filter_LTD_createdDate" onfocus="WdatePicker()"/></td>
				</tr>
				<tr>
					<td>操作记录:</td>
					<td colspan="4"><s:textfield cssStyle="width:300px" name="filter_LIKES_operationLog"/></td>
					<td colspan="3">
						<button class="" onclick="$('#pageNo').val(1);$('#mainForm').submit();" style="margin-right: 5px;">搜索</button>
						<button class="" type="button" onclick="resetForm();">重置</button>
					</td>
				</tr>
			</table>
			</fieldset>
		</div>
		<div>
		<fieldset>
			<table class="showTable" >
				<tr class="header">
					<th width="80" >项目</th>
					<th width="80" >操作类型</th>
					<th width="80" >操作人</th>
					<!-- <th width="200px" >内部编号</th>-->
					<th width="150" >操作对象</th>
					<th width="120" >操作时间</th>
					<th align="left" style="padding:0 4px;">操作记录</th>
				</tr>
				<s:iterator value="page.result" status="status">
					<s:if test="#status.odd == true">
					<tr class="trOdd">
					</s:if>
					<s:else>
					<tr>
					</s:else>
						<td><%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("projectCd")) %></td>
						<td><p:code2name mapCodeName="operationTypeMap" value="operationType"/> </td>
						<td><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %></td>
						<!-- <td>${bizEntityId}</td>-->
						<td  title='${modifiedObject}' style="overflow: hidden"><div class="ellipsisDiv_full" style="white-space:pre;">${modifiedObject}</div></td>
						<td><s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td class="ellipsisDiv" title='${operationLog}'><nobr>${operationLog}<nobr></td>
					</tr>
				</s:iterator>
			</table>
			<p:page/>
		</fieldset>
		</div>
		</form>
	</div>
</div>
<script type="text/javascript">
function changeLogPage(moduleCd){
	window.location.href="${ctx}/planold/plan-operation-log!log.action?moduleCd="+moduleCd;
}
function resetForm(){
	$("table.searchTable :text").val("");
	$("table.searchTable select").val("");
}
</script>

</body>

</html>
