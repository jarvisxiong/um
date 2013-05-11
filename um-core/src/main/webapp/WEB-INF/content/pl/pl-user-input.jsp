<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:text name="user.manage"/></title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/pl/user-input.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script src="${ctx}/js/pl/user-input.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#userCode").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					userCode: {
						required: true,
						remote: "pl-user!checkUserCode.action?oldUserCode=" + encodeURIComponent('${userCode}')
					},
					userName: "required",
					pwd: {
						required: true,
						minlength:3
					},
					pwdConfirm: {
						equalTo:"#pwd"
					},
					checkedRoleIds:"required",
					plAuthorityId:"required"
				},
				messages: {
					userCode: {
						remote: $("#js_i18n_userName").val()
					},
					pwdConfirm: {
						equalTo: document.getElementById("js_i18n_pwdMatch").value
					}
				}
			});
		});
	</script>
</head>

<body>
<%@ include file="/common/header.jsp" %>
<div id="content" align="center">
	<h1><s:if test="id == null"><s:text name="common.create"/></s:if><s:else><s:text name="common.modify"/></s:else><s:text name="user.userCode"/></h1>
	
	<s:form id="inputForm" action="pl-user!save.action" method="post" theme="simple">
	<table>
	<tr  align="left"><td>
				<s:hidden id="js_i18n_userName" name="mapJsI18n.user_exists" />
				<s:hidden id="js_i18n_pwdMatch" name="mapJsI18n.pwd_match" />
				<s:hidden name="plUserId"/>
				<s:hidden name="userLevel"/>
		<s:text name="user.userCode"/>:</td>
		<td><s:textfield name="userCode" size="40" id="userCode" />
		</td></tr>
		<tr  align="left"><td>
				<s:text name="user.userName"/>:</td>
				<td><s:textfield key="user.userName" id="userName" name="userName" size="40"/>
		</td></tr>
		<tr  align="left"><td >
				<s:text name="user.password"/>:</td>
				<td><s:password key="user.password" id="pwd" name="pwd" size="40"/>
		</td></tr>
		<tr  align="left"><td >
				<s:text name="user.pwdConfirm"/>:</td>
				<td><s:password key="user.pwdConfirm" id="pwdConfirm" name="pwdConfirm" size="40"/>
		</td></tr>	
		<tr  align="right"><td  colspan="2">
				<s:radio name="plAuthorityId" id="plAuthorityId" list="allPlAuthority" listKey="plAuthorityId" listValue="authorityName"/>
		</td></tr>	
		<tr  align="right"><td colspan="2">
				<input class="button" type="button"  onclick="history.back()" value="<s:text name="common.return"/>" />
				<security:authorize ifAnyGranted="A_ADMIN">
				<s:submit cssClass="button" name="" key="common.submit"/>
				</security:authorize>
		</td></tr>	
	</table>
	</s:form>
</div>
<%@ include file="/common/footer.jsp" %>
</body>
</html>