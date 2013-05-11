<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>未读邮件统计</title>
	<%@ include file="/common/global.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery.jqChart.css" />
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/excanvas.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.jqChart.min.js"></script>
</head>

<body class="easyui-layout">
	<div region="north" title="未读邮件明细" icon="icon-search" style="padding: 5px; height: 68px;" border="false">
		<form id="searchForm" method="post" onsubmit="return false;">
			<table cellpadding="0" cellspacing="3" border="0">
				<tr>
					<input type="hidden" id="filter_EQS_uiid" name="filter_EQS_uiid" value="${uiid }" />
					<input type="hidden" id="id" value="${bisEmailStatId }" />
					<input type="hidden" id="usedSpace" value="${usedSpace }" />
					<input type="hidden" id="noReadNums" value="${noReadNums }" />
					<input type="hidden" id="noReadNames" value="${noReadNames }" />
					
					<td>登录账号:&nbsp;${uiid }</td>
					<td>用户姓名:&nbsp;${userName }</td>
					<td>按时间段:</td>
					<td style="width: 265px;">
						<input style="width: 120px" class="easyui-datebox" id="filter_GED_createdDate" name="filter_GED_createdDate" type="text" value="${filter_GED_createdDate }"/> 
						至
						<input style="width: 120px"	class="easyui-datebox" id="filter_LED_createdDate" name="filter_LED_createdDate" type="text" value="${filter_LED_createdDate }"/>
					</td>
					<td align="left">
						<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="customSearch()">查询</a>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div region="center" border="false"> 
		<div id="jqChart" style="margin: 5px 10px;width: 90%; height: 300px;" />
	</div>
	
	<div region="south" border="false">
		<div id="usedSpaceChart" style="margin: 30px 10px;width: 90%; height: 300px;" />
	</div>
	
	<script type="text/javascript">
		$(function() {
			//未读邮件绘图
			$('#jqChart').jqChart({
				title : {
					text : '未读邮件统计图'
				},
				axes : [ {
					show: true,
					name : 'y2',
					location : 'left',
					strokeStyle : '#FCB441',
					majorGridLines : {
						strokeStyle : '#FCB441'
					},
					majorTickMarks : {
						strokeStyle : '#FCB441'
					}
				} ],
				series : [ {
					title: '邮件单位(封)',
					type : 'line',
					data : (function() {
						var result = "";
						var noReadNums = $('#noReadNums').val();
						var noReadNames = $('#noReadNames').val();
						var array = new Array();
						var names = new Array();
						array = noReadNums.split(",");
						names = noReadNames.split(",");
						result = "[";
						for ( var i = 0; i < names.length; i++) {
							if (i == eval(names.length - 1)) {
								result += "['" + names[i] + "',"+ array[i] + "]";
							} else {
								result += "['" + names[i] + "',"+ array[i] + "],";
							}
						}
						eval("jsonResult="+(result += "]"));
						return jsonResult;
					})(),
					tickOptions: {
						angle: -30,
						fontSize: '10pt'
					}
				} ]
			});
			//占用空间绘图
			$('#usedSpaceChart').jqChart({
				title : {
					text : '占用空间统计',
					fontSize:'2px'
				},
				axes : [ {
					show: true,
					name : 'y2',
					location : 'left',
					strokeStyle : '#FCB441',
					majorGridLines : {
						strokeStyle : '#FCB441'
					},
					majorTickMarks : {
						strokeStyle : '#FCB441'
					}
				} ],
				legend :[{
					show: true,
					placement: 'inside',
					location: 'top'
				}],
				series : [ {
					title: '空间单位(M)',
					style: 'circle',
					type : 'line',
					data : (function() {
						var result = "";
						var noReadNums = $('#usedSpace').val();
						var noReadNames = $('#noReadNames').val();
						var array = new Array();
						var names = new Array();
						array = noReadNums.split(",");
						names = noReadNames.split(",");
						result = "[";
						for ( var i = 0; i < names.length; i++) {
							if (i == eval(names.length - 1)) {
								result += "['" + names[i] + "',"+ array[i] + "]";
							} else {
								result += "['" + names[i] + "',"+ array[i] + "],";
							}
						}
						eval("jsonResult="+(result += "]"));
						return jsonResult;
					})(),
					tickOptions: {
						angle: -30,
						fontSize: '10pt'
					}
				} ]
			});
			
			//日期格式重写
			$('#filter_GED_createdDate').datebox({
				formatter : function(date) {
					return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
				},
				parser : function(date) {
					return new Date(Date.parse(date.replace(/-/g, "/")));
				}
			});
			$('#filter_LED_createdDate').datebox({
				formatter : function(date) {
					return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
				},
				parser : function(date) {
					return new Date(Date.parse(date.replace(/-/g, "/")));
				}
			}); 

			//提交事件
			window.customSearch = function() {
				var id = $('#id').val();
				var uiid = $('input[name=filter_EQS_uiid]').val();
				var bgnDate = $('input[name=filter_GED_createdDate]').val();
				var endDate = $('input[name=filter_LED_createdDate]').val();
				var url = "${ctx}/bis/bis-email-stat!draw.action?id=" + id
						+ "&filter_EQS_uiid=" + uiid
						+ "&filter_GED_createdDate=" + bgnDate
						+ "&filter_LED_createdDate=" + endDate;
				location.href = url;
			}
		});
	</script>
<body>
</html>