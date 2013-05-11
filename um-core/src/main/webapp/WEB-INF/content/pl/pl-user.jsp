<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><s:text name="user.manage"></s:text></title>
<%@ include file="/common/meta.jsp"%>
<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
<script src="${ctx}/js/table.js" type="text/javascript"></script>
</head>

<body>
<%@ include file="/common/header.jsp"%>
<div id="content">
<div id="message"><s:actionmessage theme="mytheme" /></div>
<s:form id="mainForm" action="pl-user" method="get" theme="simple">
	<s:hidden name="page.pageNo" id="pageNo" />
	<s:hidden name="page.orderBy" id="orderBy"/>
	<s:hidden name="page.order" id="order" />
	<%--
	 --%>
	<s:text name="user.userCode"/>:<s:textfield id="filter_EQS_userCode" name="filter_EQS_userCode"
		size="9" />
	<s:text name ="user.userName"/>:<s:textfield name="filter_LIKES_userName" id="filter_LIKES_userName" size="9" /> 
<!--	<s:submit type="button" key="common.search" onclick="doSearch();" cssClass="button"></s:submit>-->
	<input class="button" type="submit" value="搜索" onclick="search();"/>
	</div>
	<div>
	<table id="contentTable">
		<tr>
			<th><a href="javascript:sort('userCode','asc')">帐号</a></th>
			<th><a href="javascript:sort('name','asc')">姓名</a></th>
			<th>角色</th>
			<th>操作</th>
		</tr>

		<s:iterator value="page.result">
			<tr>
				<td>${userCode}&nbsp;</td>
				<td>${userName}&nbsp;</td>
				<td>${plAuthority.authorityName}&nbsp;</td>
				<td>&nbsp; <security:authorize ifAnyGranted="A_VIEW,A_VIP">
						<a href="pl-user!input.action?id=${plUserId}">查看</a>&nbsp;
				</security:authorize> <security:authorize ifAnyGranted="A_ADMIN">
					<a href="pl-user!input.action?id=${plUserId}">修改</a>&nbsp;
								<a href="pl-user!delete.action?id=${plUserId}">删除</a>
				</security:authorize></td>
			</tr>
		</s:iterator>
	</table>
	</div>

	<div>第${page.pageNo}页, 共${page.totalPages}页 <a
		href="javascript:jumpPage(1)">首页</a> <s:if test="page.hasPre">
		<a href="javascript:jumpPage(${page.prePage})">上一页</a>
	</s:if> <s:if test="page.hasNext">
		<a href="javascript:jumpPage(${page.nextPage})">下一页</a>
	</s:if> <a href="javascript:jumpPage(${page.totalPages})">末页</a> <security:authorize
		ifAnyGranted="A_ADMIN">
		<a href="pl-user!input.action">增加新用户</a>
	</security:authorize></div>
</s:form></div>
<%@ include file="/common/footer.jsp"%>
</body>
</html>
