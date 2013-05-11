<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
	<script language="javascript" src="${ctx}/js/jquery.js"></script>
	<title>角色显示</title>
</head>

<body>
<div>
	<fieldset>
	    <legend>角色</legend>
	<div>
		<s:select cssClass="role" id ="roleCd" list="uaapRoles" listKey="roleCd" listValue="roleName" size="25" name="roleCd" onchange="doChangeRole();" ></s:select>
	</div>
	</fieldset>
</div>
</body>
</html>
