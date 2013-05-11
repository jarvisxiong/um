<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/global.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/style_workplan.css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/workplan/workplan.js"></script>
<title>计划管理时间设置</title>
</head>

<body>
<div class="plan_title_bar" style="overflow:hidden;">
	<div style="float:left; height:29px; padding-left:8px; padding-top:3px;"><img src="${ctx}/images/plan/pic_plan.gif" style="margin-top:1px;"></img></div>
	<div style="float:left; height:29px;">
		&nbsp;计划管理时间周期设置
		&nbsp;&nbsp;&nbsp;&nbsp;<a href="plan-period!input.action">新增</a>
	</div>
</div>

<div>
	<div class="pagebody_corner_tl"></div>
	<div class="pagebody_corner_tr"></div>
	<div class="pagebody_corner_tc"></div>
</div>
<div id="content" class="pagebody_c_border">
<div id="message"><s:actionmessage theme="mytheme" /></div>
<s:form id="mainForm" action="plan-period" method="get" theme="simple">
	<s:hidden name="page.pageNo" id="pageNo" />
	<s:hidden name="page.orderBy" id="orderBy"/>
	<s:hidden name="page.order" id="order" />
	<div>
	<table class="content_table" style="width:98%" align="center">
		<tr>
			<th>序号</th>
			<th>年份</th>
			<th>双周数</th>
			<th>起始日期</th>
			<th>结束日期</th>
			<th>操作</th>
		</tr>
		<s:iterator value="page.result">
			<tr class="mainTr">
				<td>${periodSerialNumber}&nbsp;</td>
				<td>${planYear}&nbsp;</td>
				<td>${yearNumber}&nbsp;</td>
				<td><s:date name="startDate" format="MM-dd"></s:date>&nbsp;</td>
				<td><s:date name="endDate" format="MM-dd"></s:date>&nbsp;</td>
				<td>&nbsp;
					<a href="plan-period!input.action?id=${planPeriodId}">修改</a>&nbsp;
					<a href="plan-period!delete.action?id=${planPeriodId}">删除</a>
				</td>
			</tr>
		</s:iterator>
	</table>
	</div>

	<div align="center">第${page.pageNo}页, 共${page.totalPages}页 <a
		href="javascript:jumpPage(1)">首页</a> <s:if test="page.hasPre">
		<a href="javascript:jumpPage(${page.prePage})">上一页</a>
	</s:if> <s:if test="page.hasNext">
		<a href="javascript:jumpPage(${page.nextPage})">下一页</a>
	</s:if> <a href="javascript:jumpPage(${page.totalPages})">末页</a> <security:authorize
		ifAnyGranted="A_ADMIN">
		<a href="plan-period!input.action">增加新用户</a>
	</security:authorize></div>
</s:form></div>
<div>
	<div class="pagebody_corner_bl"></div>
	<div class="pagebody_corner_br"></div>
	<div class="pagebody_corner_bc"></div>
</div>
</body>
</html>
