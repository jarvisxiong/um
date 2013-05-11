<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Mini-Web 帐号管理</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/table.js" type="text/javascript"></script>
</head>

<body>
<%@ include file="/common/header.jsp" %>
<div id="content">
	<div id="message"><s:actionmessage theme="mytheme"/></div>
	<s:form id="mainForm" action="user" method="get"  theme="simple">
<!--		<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>-->
<!--		<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>-->
<!--		<input type="hidden" name="page.order" id="order" value="${page.order}"/>-->
		<s:hidden name="page.pageNo" id="pageNo" />
		<s:hidden name="page.orderBy" id="orderBy"/>
		<s:hidden name="page.order" id="order" />
		<div id="filter">
		<%--
			你好, <%=SpringSecurityUtils.getCurrentUiid()%>.&nbsp;&nbsp;
		 --%>
			登录名: <s:textfield id="filter_EQS_userCode" name="filter_EQS_userCode" size="9" />
			姓名或Email: <s:textfield key="userName" name="#parameters.filter_LIKES_userName" id="filter_LIKES_userName" size="9" /> 
			<input class="button" type="button" value="搜索" onclick="search();"/>
		</div>
		<div>
			<table id="contentTable">
				<tr>
					<th><a href="javascript:sort('loginName','asc')">登录名</a></th>
					<th><a href="javascript:sort('name','asc')">姓名</a></th>
					<th><a href="javascript:sort('email','asc')">电邮</a></th>
					<th>角色</th>
					<th>操作</th>
				</tr>

				<s:iterator value="page.result">
					<tr>
						<td>${loginName}&nbsp;</td>
						<td>${name}&nbsp;</td>
						<td>${email}&nbsp;</td>
						<td>${roleNames}&nbsp;</td>
						<td>&nbsp;
							<security:authorize ifAnyGranted="A_VIEW_USER">
								<security:authorize ifNotGranted="A_MODIFY_USER">
									<a href="user!input.action?id=${id}">查看</a>&nbsp;
								</security:authorize>
							</security:authorize>

							<security:authorize ifAnyGranted="A_MODIFY_USER">
								<a href="user!input.action?id=${id}">修改</a>&nbsp;
								<a href="user!delete.action?id=${id}">删除</a>
							</security:authorize>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>

		<div>
			第${page.pageNo}页, 共${page.totalPages}页
			<a href="javascript:jumpPage(1)">首页</a>
			<s:if test="page.hasPre"><a href="javascript:jumpPage(${page.prePage})">上一页</a></s:if>
			<s:if test="page.hasNext"><a href="javascript:jumpPage(${page.nextPage})">下一页</a></s:if>
			<a href="javascript:jumpPage(${page.totalPages})">末页</a>

			<security:authorize ifAnyGranted="A_MODIFY_USER">
				<a href="user!input.action">增加新用户</a>
			</security:authorize>
		</div>
	</s:form>
</div>
<%@ include file="/common/footer.jsp" %>
</body>
</html>
