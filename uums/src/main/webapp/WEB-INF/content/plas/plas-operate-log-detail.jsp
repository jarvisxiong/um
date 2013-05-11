<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>查看操作日志明细</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>

</head>
<body>
<div style="overflow:auto;background-color: white;line-height: 23px;" fit="true" style="+position: relative">
	<table class="mainTable" style="height:100%;width:100%;" cellpadding="0" cellspacing="0">
		<tr class="panel-header" style="line-height:30px">
			<td style="padding-left:5px;width:80px;"><div style="font-weight:bolder;cursor: default;" href="#" onclick="return false;" onfocus="this.blur();"><font color="#15428B">操作日志浏览</font></div></td>
			<td></td>
			<td style="width:80px"></td>
			<td></td>
		</tr>
		<tr>
			<td style="padding-left:5px;">操作人账号:</td>
			<td style="text-align:left;">${uiid}</td> 
			<td style="text-align:left;">操作人姓名:</td>
			<td style="text-align:left;">${userName}</td> 
		</tr> 
		<tr>
			<td style="padding-left:5px;">模块名称:</td>
			<td style="text-align:left;"><b style="color: red;">${moduleCd}</b></td> 
			<td style="text-align:left;">概要:</td>
			<td style="text-align:left;">${sumarry}</td> 
		</tr> 
		<tr>
			<td style="padding-left:5px;" valign="top">操作明细:</td>
			<td colspan="3"><div style="text-align:left;padding: 0 5px;min-height: 350px;">${operateDetailDesc}</div></td> 
		</tr> 
	</table>
		
	<table style="width:100%;">	
		<tr class="panel-header" style="line-height:30px">
			<td style="padding-left:5px;" colspan="4"><font color="#15428b">审计信息</font></td></tr>
	</table>
		
	<table style="width:100%">
		<tr>
			<td style="padding-left:5px;width:120px">创建时间:</td>
			<td style="width:20%;">${createdDate}</td>
			<td style="width:120px;">创建人员:</td>
			<td style="width:20%;">${creator}</td>
			<td style="width:120px;">创建部门:</td>
			<td>${createdDeptCd}</td>
		</tr>
		<tr>
			<td style="padding-left:5px;">最近更新时间:</td>
			<td>${updatedDate}</td>
			<td>最近更新人员:</td>
			<td>${updator}</td>
			<td>最近更新部门:</td>
			<td>${updatedDeptCd}</td>
		</tr>
	</table> 
</div>
 
</body>
</html>