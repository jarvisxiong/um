<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/global.jsp" %>
<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
<title>新增计划管理时间设置</title>
</head>

<body>
<div class="plan_title_bar" style="overflow:hidden;">
	<div style="float:left; height:29px; padding-left:8px; padding-top:3px;"><img src="${ctx}/images/plan/pic_plan.gif" style="margin-top:1px;"></img></div>
	<div style="float:left; height:29px;">
		&nbsp;新增计划管理时间设置
	</div>
</div>
<div id="content" align="center">
	<form id="inputForm" action="plan-period!save.action" method="post">
	<input type="hidden" name="periodTypeCd" value="0"/>
	<table style="line-height:40px;">
		<tr align="left">
			<td>
				序号<input type="text" name="periodSerialNumber" value="${periodSerialNumber}"/>
			</td>
			<td>
				年份<input type="text" name="planYear" value="${planYear}"/>
			</td>
			<td>
				双周数<input type="text" name="yearNumber" value="${yearNumber}"/>
			</td>
		</tr>
		<tr align="left">
			<td>
				起始日期<input type="text" name="startDate" value="${startDate}" onfocus="WdatePicker()" class="Wdate"/>
			</td>
			<td>
				结束日期<input type="text" name="endDate" value="${endDate}" onfocus="WdatePicker()" class="Wdate"/>
			</td>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="提交"/>
				&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="self.location='plan-period!list.action';" value="返回"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</body>
</html>