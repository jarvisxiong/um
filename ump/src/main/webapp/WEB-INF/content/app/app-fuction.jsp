<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>管理区域</title>
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
	<script language="javascript" src="${ctx}/js/jquery.js"></script>
	<script language="javascript" src="${ctx}/js/table.js"></script>
</head>

<body>
	    
	    
<s:form id="mainForm" action="app-function" method="post">
	<div class="search">
		<fieldset>
		    <legend><s:text name="common.search" /></legend>
		    <div>
			      <s:text name="appAppFunction.functionCd"/>:<s:textfield name="filter_EQS_functionCd" id="filter_EQS_functionCd" size="18" maxlength="30" />
			      <s:text name="appAppFunction.functionName"/>:<s:textfield name="filter_LIKES_functionName" id="filter_LIKES_functionName" size="18" maxlength="30" />
			  	  <input type="submit" class="buttom" value="<s:text name="common.search" />" />
			      <input type="button" class="buttom" value="<s:text name="common.reset" /> " onclick="resetToEmpty();"/>
			      <input type="button" class="buttom" value="<s:text name="common.exportExcel" />" />
	 	 	</div>
		</fieldset> 
	</div>
 
	<div id="tableDiv">
	<table class="commonTable" id="editTable" align="left" width="99%">
		<tr>
			<th><a href="javascript:sort('functionCd','functionCd')"><s:text name="appAppFunction.functionCd"></s:text></a></th>
			<th><s:text name="appAppFunction.functionName" /></th>
			<th><a href="javascript:sort('pagePath','pagePath')"><s:text name="appAppFunction.pagePath" /></a></th>
			<th><s:text name="appAppFunction.functionTip" /></th>
			<th><s:text name="appAppFunction.functionTypeCd" /></th>
			<th><s:text name="appAppFunction.dispOrderNo" /></th>
			<th><s:text name="appAppFunction.remark" /></th>
			<th><s:text name="common.operate" /></th>
		</tr>
		<s:iterator value="page.result">
		<tr>
			<td>${functionCd}</td>
			<td>${functionName}</td>
			<td>${functionTip}</td>
			<td><p:code2name mapCodeName="mapEnabledFlg" value="functionTypeCd"/></td>
			<td>${dispOrderNo}</td>
			<td>${remark}</td>
			<td><security:authorize ifAnyGranted="A_ADMIN">
					<p:operate idName="appPageId" action="app-function" enableName="" />
				</security:authorize>
			</td>
		</tr>
		</s:iterator>
		<tr>
			<td  align="center" colspan="5">
				<a href="app-function!input.action"> 
					<font color="red">
						<s:text name="common.create" /> 
						<s:text name="appAppFunction.pageTitle" /> 
					</font> 
				</a>&nbsp; 
				<!-- 分页信息 -->
				<p:page />
			</td>
		</tr>
	</table>
	</div>
</s:form>

</body>
</html>
