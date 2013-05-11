<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/global.jsp" %>
		<title>项目开发计划</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
		<link href="${ctx}/resources/css/common/TreePanel.css" rel="stylesheet" type="text/css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
		<link rel="stylesheet" href="${ctx}/css/desk/oa-meeting.css" type="text/css" />

<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/plan.css"/>

	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
		<script language="javascript" src="${ctx}/js/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
		<script type=text/javascript src="${ctx}/js/common.js"></script>
		<script type=text/javascript src="${ctx}/resources/js/common/TreePanel.js"></script>
		<script src="${ctx}/js/datePicker/WdatePicker.js" type="text/javascript"></script>
		<script type=text/javascript src="${ctx}/resources/js/plan/exec-plan.js"></script>
		<script type=text/javascript src="${ctx}/js/desk/oa-meeting.js"></script>
		<script language="javascript" src="${ctx}/js/table.js"></script>
		<script language="javascript" src="${ctx}/js/jqueryplugin/chilltip.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/oa/allAttention.js"></script>
	<script type="text/javascript">
		var _ctx = '${ctx}'; 
		$(function(){
			$('#select_manager').ouSelect();
			$('#select_personal').ouSelect();
		});
	</script>
	</head>
	<body style="padding:0;margin:0;">
		<div id="oaMeetingContainer" style="display: block;">
			<div class="title_bar" style="padding-top: 0px; font-size: 12px;">
				<div id="goingInfo" style="float:left;font-size: 14px; height:29px;color: #5A5A5A; font-weight: bold;">&nbsp;
				<% 
						if(request.getAttribute("flag")!=null && request.getAttribute("flag").equals("1")){%>
							留言列表
						<%}else{%>
							待回复进度节点信息
						<%}
					%>
				</div>
				<div style="float:left; height:29px; line-height: 29px; text-align: center; margin-left: 20px; font-size: 12px;">
					<span style="color: red;" id="succInfoMsg"></span>
				</div>
				<div id="jindu" style="float:right; height:26px; display: block;">
					<div class="split_vertial" style="float: left;">&nbsp;</div>
					<% 
						if(request.getAttribute("flag")!=null && request.getAttribute("flag").equals("1")){%>
							<div id="messageView" class="project_process" style="float:left" onclick="doJinduViews()">
								进度查看
							</div>
						<%}else{%>
							<div id="messageView" class="project_process" style="float:left" onclick="doMessageView()">
								留言查看
							</div>
						<%}
					%>
					
					<div class="split_vertial" style="float: left;">&nbsp;</div>
					<div style="float: left;" class="btn_refreshMeeting" onclick="">
						刷新
					</div>
					<div class="split_vertial" style="float: left;">&nbsp;</div>
					<div style="color: white;" class="btn_fullScreen" onclick="window.open('${ctx}/plan/exec-plan!getAllSuspendExecPlan.action')">
						全屏
					</div>
				</div>
			</div>
			
			<div class="wrapper">
			 	<div id="meetingListDiv" style="display: block;">
					<%@ include file="exec-plan-listExecPlan.jsp"%>
				</div>
			</div>
		</div>

	</body>
</html>