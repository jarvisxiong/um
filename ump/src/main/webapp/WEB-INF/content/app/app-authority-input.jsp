<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
<script language="javascript" src="${ctx}/js/jquery.js"></script>
<title>角色编辑</title>
<script language="javascript">
</script>
</head>

<body>
<s:if test="appMenu!=null">
<div class="fieldsetdiv">
	<fieldset>
	    <legend><s:text name="appAppMenu"/></legend>
	<div>
	<table class="commonTable" id="editTable" align="left" width="99%">
	<tbody>
		<tr>
			<td width="100" ><s:text name="appAppMenu.menuCd"/>:</td><td>${appMenu.menuCd}</td>
		</tr>
		<tr>	<td width="100"><s:text name="appAppMenu.menuName"/>:</td><td>${appMenu.menuName}</td>
		</tr>
		<tr>
			<td><s:text name="appAppMenu.parentMenuCd"/>:</td><td>${appMenu.parentMenuCd}</td>
		</tr>
		<tr>	<td><s:text name="appAppMenu.menuTip"/>:</td><td>${appMenu.menuTip}</td>
		</tr>
		<tr>
			<td><s:text name="appAppPage.pageCd"/>:</td><td>${appMenu.appPage.pageCd}</td>
		</tr>
		<tr>	<td><s:text name="appAppPage.pageName"/>:</td><td>${appMenu.appPage.pageName}</td>
		</tr>
		<tr>
			<td><s:text name="appAppPage.pagePath"/>:</td><td>${appMenu.appPage.pagePath}</td>
		</tr>
		<tr>	<td><s:text name="appAppPage.pageStatusCd"/>:</td><td><p:code2name mapCodeName="mapEnabledFlg" value="appMenu.appPage.pageStatusCd"/></td>
		</tr>
		<tr>
		<td><s:text name="appAppMenu.updator"/>:</td><td>${appMenu.updator}</td>
		</tr>
		<tr>	<td><s:text name="appAppMenu.updatedDate"/>:</td><td><s:property value="appMenu.updatedDate" /></td>
		</tr>
		<tr>
			<td><s:text name="appAppMenu.remark"/>:</td><td>${appMenu.remark}</td>
		</tr>
	</tbody>
	</table>	
	</div>
	</fieldset>
</div>
</s:if>
<s:if test="appModule!=null">
<div class="fieldsetdiv">
	<fieldset>
	    <legend><s:text name="appAppModule"/></legend>
	<div>
	<table class="commonTable" id="editTable" align="left" width="99%">
	<tbody>
		<tr>
			<td width="100"><s:text name="appAppModule.moduleCd"/>:</td><td>${appModule.moduleCd}</td>
		</tr>
		<tr>	<td width="100"><s:text name="appAppModule.moduleName"/>:</td><td>${appModule.moduleName}</td>
		</tr>
		<tr>
			<td><s:text name="appAppModule.dispOrderNo"/>:</td><td>${appModule.dispOrderNo}</td>
		</tr>
		<tr>	<td><s:text name="appAppModule.moduleTip"/>:</td><td>${appModule.moduleTip}</td>
		</tr>
		<tr>
			<td><s:text name="appAppModule.updator"/>:</td><td>${appModule.updator}</td>
		</tr>
		<tr>	<td><s:text name="appAppModule.updatedDate"/>:</td><td><s:property value="appModule.updatedDate" /></td>
		</tr>
		<tr>
			<td><s:text name="appAppModule.remark"/>:</td><td>${appModule.remark}</td>
		</tr>
	</tbody>
	</table>	
	</div>
	</fieldset>
</div>
</s:if>
</body>
</html>
