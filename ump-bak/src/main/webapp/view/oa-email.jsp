<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/email.css" type="text/css" />
<script language="javascript" src="${ctx}/js/jquery.js"></script>
<script language="javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<title>邮件</title>
<script language="javascript">
$(function(){
	
});
</script>
</head>

<body>
<div class="mailContainer">
	<div class="mailLeft">
		<div class="leftTop">
			 <a href="#"><img align="absMiddle" src="${ctx}/images/email/nm.png"/><span>收 信</span></a>
			 <a href="#"><img align="absMiddle" src="${ctx}/images/email/wm.png"/><span>写信</span></a>
		</div>
		<div class="leftMainMenu"><img align="absMiddle" src="${ctx}/images/email/ar.png"/> 邮箱管理</div>
		<div class="leftMenu">
			<ul>
			    <li><img align="absMiddle" src="${ctx}/images/email/inbox.gif"/> 收件箱</li>
			    <li><img align="absMiddle" src="${ctx}/images/email/inbox.gif"/> 草稿箱</li>
			    <li><img align="absMiddle" src="${ctx}/images/email/inbox.gif"/> 已发送</li>
			    <li><img align="absMiddle" src="${ctx}/images/email/inbox.gif"/> 已删除</li>
			 </ul>
		</div>
	</div>
	<div class="mailRight">
		<%@include file="oa-email-new.jsp" %>
	</div>
</div>
</body>

</html>
