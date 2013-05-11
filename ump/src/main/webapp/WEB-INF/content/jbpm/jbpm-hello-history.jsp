<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>jBPM4视频教程第3课</title>
</head>
<body>

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
		</tr>
	</c:forEach>
</table>
<br/>
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
<br/>
<!-- 
<table width=680 border=1>
	<tr>
		<td colspan="5">历史动作</td>
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
		</tr>
	</c:forEach>
</table>
 -->
</body>
</html>