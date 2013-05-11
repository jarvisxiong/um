<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/global.jsp" %>
		<title>项目计划自动统计</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
		<link href="${ctx}/resources/css/common/TreePanel.css" rel="stylesheet" type="text/css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
		<link rel="stylesheet" href="${ctx}/css/desk/oa-meeting.css" type="text/css" />

<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/exec-plan.css"/>
	
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
		<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
		<style>
		 .btn2 {padding: 2 4 0 4;font-size:12px;height:18;background-color:#ece9d8;border-width:1px;}
		</style>
		
	<script type="text/javascript">
		var _ctx = '${ctx}'; 
		$(function(){
			$('#select_manager').ouSelect();
			$('#select_personal').ouSelect();
			now_year = "${now_year}";
			now_month = "${now_month}";
			$("#select_now_year").val(now_year);
			$("#select_now_month").val(now_month);
			centerCd = $("#typecd_select").val();
		});
		//切换
		function projectChange(){
			var curProjCd=$("#typecd_select").val();
			curOpenedNodeCompDateId = null;
			$("#planContainerDiv").html('<div class="waiting" style="width:200px;height:100px;"></div>');
			var data = {
					projectCd : curProjCd,
					ifFirst : "1"
				};
			$.post("${ctx}/plan/exec-plan-extend!dataMissingAnalysis.action", data, function(result) {
				$("#planContainerDiv").html(result).hide().fadeIn();
			});
		}
		function doDataMissingExcute(){
			$("#myDiv").addClass("waiting");
			var createDate=$("#filter_ST_createdDate").val();
			var typecd_select=$("#typecd_select").val();
			var data = {
					createDate : createDate,
					typecd_select : typecd_select,
					ifFirst : "1"
				};
			$.post(_ctx+"/plan/exec-plan-extend!test.action",data, function(result) {
				$("#planContainerDiv").html(result).hide().fadeIn();
				$("#myDiv").html(result).removeClass("waiting");
			});
		}
	</script>
	</head>
	<body style="padding:0;margin:0;">
	<input type="hidden" id="curProjectCd"  value="${projectCd}"/>
	<input type="hidden" id="ifFirst" name="ifFirst" value="${ifFirst}" />
		<div id="oaMeetingContainer" style="width:100%;display: block;">
		<%
			String ifFirst = (String)request.getAttribute("ifFirst");
			if(ifFirst.equals("0")){%>
				<div id="" class="title_bar" style="padding-top: 0px; font-size: 12px;">
				<div class="title_bar_h">
					<div style="float:left; font-weight:bolder; font-size:14px; color:#333;">
					项目开发计划&nbsp;
					</div>
					<div style="float:left;" <s:if test="!aViewFlag">disabled="disabled"</s:if>>
					<s:select id="typecd_select" list="mapProjectsType" listKey="key" listValue="value"  name="projectCd" cssStyle="float:left; width:100px;" onchange="projectChange()"/>
					</div>
					<div style="float:left; height:29px; line-height: 29px; text-align: center; margin-left: 20px; font-size: 12px;">
						<span style="color: red;" id="succInfoMsg"></span>
					</div>
				</div>
				<s:if test="currentUiid == 'lujy' || currentUiid == 'dengdd'">
				<div class="title_bar_h">
					<div style="float:left; font-weight:bolder; font-size:14px; color:#333;">
						<span style="margin-right: 5px;">采集时间</span>
							<input type="text" id="filter_ST_createdDate" name="filter_ST_createdDate"  style="width:100px;" onfocus="WdatePicker()"/>
					</div>
					&nbsp;<button type="button"  class=btn2 style="width:60px;height:20px;" onclick="doDataMissingExcute();">执行</button>
				</div>
				</s:if>
				<div id="jindu" style="float:right; height:26px; display: block;">
					<div class="split_vertial" style="float: left;">&nbsp;</div>
					<div class="split_vertial" style="float: left;">&nbsp;</div>
					<div style="float: left;" class="btn_refreshMeeting" onclick="projectChange()">
						刷新
					</div>
					<div class="split_vertial" style="float: left;">&nbsp;</div>
					<div style="color: white;" class="btn_fullScreen" onclick="window.open('${ctx}/plan/exec-plan-extend!dataMissingAnalysis.action')">
						全屏
					</div>
				</div>
			</div>
			<div>
				<table style="width:100%;border:1px solid #E4E7EC;font-size:14px;color:red;height: 30px;line-height: 30px;" cellpadding="0" cellspacing="0">
						<tr>
							<td style="padding:8px 5px; font-size:14px;">
								<span class="color_red">
									正在查看：
								</span>
								<span class="color_black">
									<select id="select_now_year" onchange="now_year=$(this).val();centerClick();">
										<option value="2011">2011</option>
										<option value="2012">2012</option>
										<option value="2013">2013</option>
										<option value="2014">2014</option>
										<option value="2015">2015</option>
										<option value="2016">2016</option>
										<option value="2017">2017</option>
										<option value="2018">2018</option>
										<option value="2019">2019</option>
										<option value="2020">2020</option>
									</select>
									年
									<button id="month1"  onClick="monthClick(1)"  
										<s:if test="now_month==1">class="month_big color_red"</s:if>
										<s:else>class="month_small"</s:else>>1</button>
									<button id="month2"  onClick="monthClick(2)"  
										<s:if test="now_month==2">class="month_big color_red"</s:if>
										<s:else>class="month_small"</s:else>>2</button>
									<button id="month3"  onClick="monthClick(3)"  
										<s:if test="now_month==3">class="month_big color_red"</s:if>
										<s:else>class="month_small"</s:else>>3</button>
									<button id="month4"  onClick="monthClick(4)"  
										<s:if test="now_month==4">class="month_big color_red"</s:if>
										<s:else>class="month_small"</s:else>>4</button>
									<button id="month5"  onClick="monthClick(5)"  
										<s:if test="now_month==5">class="month_big color_red"</s:if>
										<s:else>class="month_small"</s:else>>5</button>
									<button id="month6"  onClick="monthClick(6)"  
										<s:if test="now_month==6">class="month_big color_red"</s:if>
										<s:else>class="month_small"</s:else>>6</button>
									<button id="month7"  onClick="monthClick(7)"  
										<s:if test="now_month==7">class="month_big color_red"</s:if>
										<s:else>class="month_small"</s:else>>7</button>
									<button id="month8"  onClick="monthClick(8)"  
										<s:if test="now_month==8">class="month_big color_red"</s:if>
										<s:else>class="month_small"</s:else>>8</button>
									<button id="month9"  onClick="monthClick(9)"  
										<s:if test="now_month==9">class="month_big color_red"</s:if>
										<s:else>class="month_small"</s:else>>9</button>
									<button id="month10" onClick="monthClick(10)" 
										<s:if test="now_month==10">class="month_big color_red"</s:if>
										<s:else>class="month_small"</s:else>>10</button>
									<button id="month11" onClick="monthClick(11)" 
										<s:if test="now_month==11">class="month_big color_red"</s:if>
										<s:else>class="month_small"</s:else>>11</button>
									<button id="month12" onClick="monthClick(12)" 
										<s:if test="now_month==12">class="month_big color_red"</s:if>
										<s:else>class="month_small"</s:else>>12
									</button>
										月
								 </span>
							</td>
						</tr>
					</table>	
			</div>
			<%}
		%>
			<div class="wrapper" id="myDiv">
				<div id="planContainerDiv" class="plan_container" style="width:100%;display: block;">
					<%@ include file="exec-plan-extend-dataMissing.jsp"%>
				</div>
			</div>
		</div>

	</body>
</html>