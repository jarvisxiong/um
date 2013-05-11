<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>jBPM4流程后台管理</title>
</head>
<body>
<form action="jbpm-hello!deployZip.action" method="post" enctype="multipart/form-data" >
<s:file name="upload.upload" /><input type="submit" value="发布" /> <input
	type="reset" value="取消" />
</form>
<!--<form action="jbpm-hello!deploy.action" method="post">-->
<!--<textarea-->
<!--	name="xml" cols=50 rows=15></textarea> <input type="submit" value="发布" />-->
<!--<input type="reset" value="取消" />-->
<!--</form>-->

<table width=680 border=1>
	<tr>
		<td colspan="5">流程定义</td>
	</tr>
	<tr>
		<td>ID</td>
		<td>Key</td>
		<td>名称</td>
		<td>版本</td>
		<td>操作</td>
	</tr>
	<c:forEach var="process" items="${process}">
		<tr>
			<td>${process.id}</td>
			<td>${process.key}</td>
			<td>${process.name}</td>
			<td>${process.version}</td>
			<td><a
				href="jbpm-hello!list.action?id=${process.id}">查看</a> 
				<a href="jbpm-hello!history.action?id=${process.id}">历史</a>
				<a href="jbpm-hello!delete.action?id=${process.id}">删除</a></td>
		</tr>
	</c:forEach>
</table>
<br> <br> <br>
<table width=680 border=1>
	<tr>
		<td colspan="5">流程实例</td>
	</tr>
	<tr>
		<td>ID</td>
		<td>Name</td>
		<td>状态</td>
		<td>操作</td>
	</tr>
	<c:forEach var="pi" items="${executions}">
		<tr>
			<td>${pi.id}</td>
			<td>${pi.name}</td>
			<td>${pi.state}</td>
			<td><a href="jbpm-hello!query.action?executionId=${pi.id}">搜索</a>
			<a href="jbpm-hello!history.action?executionId=${pi.id}&id=${pi.processDefinitionId}">历史</a>
			<a href="jbpm-hello!deleteExecution.action?executionId=${pi.id}">删除</a>
			</td>
		</tr>
	</c:forEach>
</table>
<br/>
<table width=680 border=1>
	<tr>
		<td colspan="5">任务实例</td>
	</tr>
	<tr>
		<td>Id</td>
		<td>Name</td>
		<td>activityName</td>
		<td>executionId</td>
		<td>操作</td>
	</tr>
	<c:forEach var="task" items="${tasks}">
		<tr>
			<td>${task.id}</td>
			<td>${task.name}</td>
			<td>${task.activityName}</td>
			<td>${task.executionId}</td>
			<td><a href="jbpm-hello!deleteTask.action?taskId=${task.id}">删除</a>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>