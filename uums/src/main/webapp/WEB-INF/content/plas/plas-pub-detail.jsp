<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>查看申请明细</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	
</head>
<body style="background-color: white;font-size: 12px;" class="easyui-layout">
	<div region="center" border="false" style="padding:5px 20px 5px 5px;+position: relative;overflow-x:hidden;">
		<form id="saveForm" method="post">
			<table class="edit_table">
				<tr>
					<td style="width:60px;text-align: right;">发布日期:</td>
					<td><s:date name="pubDate" format="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td style="width:60px;text-align: right;">标题:</td>
					<td>${briefTitle}</td>
				</tr>
				<tr>
					<td style="text-align: right;" valign="top">发布内容:</td>
					<td><s:property value='content' escape="false"/></td><%-- 转换特殊字符false --%>
				</tr> 
			</table>
		</form>
	</div>
</body>
</html>