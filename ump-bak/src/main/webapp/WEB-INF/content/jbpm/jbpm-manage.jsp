<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>流程管理</title>
</head>
<body>
<form action="jbpm-manage!deploy.action" method="post" enctype="multipart/form-data" >
<s:file name="upload.upload" /><input type="submit" value="发布" /> <input
	type="reset" value="取消" />
</form>

<table width=480 border=1>
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
			<td><a href="jbpm-hello!start.action?key=${process.key}">启动</a>
			<a href="jbpm-hello!list.action?id=${process.id}">查看</a> <a
				href="jbpm-hello!delete.action?id=${process.id}">删除</a></td>
		</tr>
	</c:forEach>
</table>
<br>
<br>
<br>
<table width=480 border=1>
	<tr>
		<td colspan="5">流程实例</td>
	</tr>
	<tr>
		<td>ID</td>
		<td>Key</td>
		<td>状态</td>
		<td>操作</td>
	</tr>
	<c:forEach var="pi" items="${executions}">
		<tr>
			<td>${pi.id}</td>
			<td>${pi.key}</td>
			<td>${pi.state}</td>
			<td><a href="jbpm-hello!signal.action?executionId=${pi.id}">执行</a>
			<a href="jbpm-hello!query.action?executionId=${pi.id}">搜索</a></td>
		</tr>
	</c:forEach>
</table>
<br />
<table width=480 border=1>
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
			<td><a href="jbpm-hello!task.action?taskId=${task.id}">执行</a></td>
		</tr>
	</c:forEach>
</table>
<br />
<table width=880 border=1>
	<tr>
		<td colspan="5">历史实例</td>
	</tr>
	<tr>
		<td>ProcessInstanceId</td>
		<td>ProcessDefinitionId</td>
		<td>State</td>
		<td>StartTime</td>
		<td>EndTime</td>
		<td>Duration</td>
		<td>EndActivityName</td>
	</tr>
	<c:forEach var="historyP" items="${historyProcessInstances}">
		<tr>
			<td>${historyP.processInstanceId}</td>
			<td>${historyP.processDefinitionId}</td>
			<td>${historyP.state}</td>
			<td>${historyP.startTime}</td>
			<td>${historyP.endTime}</td>
			<td>${historyP.duration}</td>
			<td>${historyP.endActivityName}</td>
			</td>
		</tr>
	</c:forEach>
</table>
<br />
<table width=880 border=1>
	<tr>
		<td colspan="5">历史任务</td>
	</tr>
	<tr>
		<td>Id</td>
		<td>ExecutionId</td>
		<td>CreateTime</td>
		<td>EndTime</td>
		<td>Duration</td>
		<td>State</td>
		<td>Assignee</td>
		<td>Outcome</td>
	</tr>
	<c:forEach var="historyT" items="${historyTasks}">
		<tr>
			<td>${historyT.id}</td>
			<td>${historyT.executionId}</td>
			<td>${historyT.createTime}</td>
			<td>${historyT.endTime}</td>
			<td>${historyT.duration}</td>
			<td>${historyT.state}</td>
			<td>${historyT.assignee}</td>
			<td>${historyT.outcome}</td>
		</tr>
	</c:forEach>
</table>
<br />
<table width=680 border=1>
	<tr>
		<td colspan="5">动作历史</td>
	</tr>
	<tr>
		<td>ActivityName</td>
		<td>StartTime</td>
		<td>EndTime</td>
		<td>Duration</td>
		<td>ExecutionId</td>
	</tr>
	<c:forEach var="history" items="${historyActivityInstances}">
		<tr>
			<td>${history.activityName}</td>
			<td>${history.startTime}</td>
			<td>${history.endTime}</td>
			<td>${history.duration}</td>
			<td>${history.executionId}</td>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>