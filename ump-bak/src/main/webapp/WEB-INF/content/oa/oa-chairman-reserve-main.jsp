<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/global.jsp" %>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>预约总裁</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/desk-oa.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/userChoose.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
		<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css" />
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>	
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/js/userChoose.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
		<script type="text/javascript" src="${ctx}/js/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	</head>
	
	<body style="line-height: 25px;" <s:if test="flag == 'true'">onLoad="showMeetingInfo('${objId}')"</s:if>>
		<div class="title_bar">
			<div style="float:left; margin-left:8px; font-size:18px; font-weight:bolder;">预约总裁</div>
			<div style="float:right; margin-right: 5px; margin-top:6px;">
				<div class="btn_blue" style="float:left; margin-right:5px;" onclick="resMeetingRoom();" class="title_bar_chairman_res">预约总裁</div>
				<div class="btn_green" style="float:left; margin-right:5px;" onclick="showMyApp();">我的申请</div>
				<div class="btn_green" style="float:left; margin-right:5px;" onclick="window.location.href='${ctx}/oa/oa-chairman-reserve!main.action'">刷新</div>
				<div class="btn_green" style="float:left; margin-right:5px;" onclick="window.open('${ctx}/oa/oa-chairman-reserve!main.action')">全屏</div>
			</div>
		</div>
		<div style="height:2px;background-color: #5a5a5a;height:1px;margin-bottom:2px;margin-bottom: 8px;"></div>
		<div style="margin:2px">
		
		<div id="myMeetingCon" style="display:none">
			<fieldset>
				<form action="${ctx}/oa/oa-chairman-reserve!myres.action" method="post" id="myMeetingForm">
				<div>
					<div style="height: 35px;line-height:35px;background-color: #eeeeee">
						<div style="float:left;font-weight: bold;margin-right: 10px;font-size: 14px;color:#316685;padding-left:10px;">我的申请</div>
						<div style="float: left; display:inline;">
							<span style="margin-right: 5px;">申请日期</span>
							从<input type="text" name="filter_GED_createdDate" class="date" style="width:100px;" onfocus="WdatePicker()"/>
							到<input type="text" name="filter_LTD_createdDate" class="date" style="width:100px;" onfocus="WdatePicker()"/>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" style="width:60px;height:24px;line-height: 20px;" onclick="showMyApp();" value="搜索" />
						</div>
						<div class="meetingClose" onclick="closeMyMeetingCon();"></div>
					</div>
					<div id="myMeetingRoomRes"></div>
				</div>
				</form>
			</fieldset>
			<div style="height:5px;"></div>
		</div>
		
		<security:authorize ifAnyGranted="A_CHAIRMAN_RES_ADMIN,A_CEO_RES_ADMIN">	
			<div id="sendMsgContainer" style="display: none;">
			<fieldset>
				<div>
					<div style="height: 35px;line-height:35px;background-color: #eeeeee">
						<div style="float:left;font-weight: bold;margin-right: 10px;font-size: 14px;color:#316685;padding-left:10px;">人工短信提醒</div>
						<div class="meetingClose" onclick="$('#sendMsgContainer').slideUp();"></div>
					</div>
					<div>
						<table width="100%" cellpadding="0" cellspacing="0" class="editTable">
							<tr>
								<td width="80px">内部接收人:</td>
								<td>
									<textarea id="innerUserNames" style="width:450px;height:40px;" onclick="getMember('inner','','0');"  readonly="readonly"></textarea>
									<span onclick="getMember('inner','','0');" style="font-weight: bolder;cursor: pointer;text-decoration: underline">选择</span>
									<s:hidden id="innerUserCds"></s:hidden>
								</td>
								<td>无需填写手机号,直接选择人员</td>
							</tr>
							<tr>
								<td width="80px">手机号：</td>
								<td>
									<textarea id="telNoId"  style="width:450px;height:40px;"></textarea>
								</td>
								<td>填写11位手机号，多个手机号请以,(英文逗号)分隔</td>
							</tr>
							<tr>
								<td width="80px">短信内容：</td>
								<td>
									<textarea id="msgContent"  style="width:450px;height:60px;"></textarea>
								</td>
								<td valign="bottom">
									<input onclick="sendMsg();" style="width:60px;height:25px;line-height: 20px;" value="发送" />
								</td>
							</tr>
						</table>
					</div>
				</div>
			</fieldset>
			<div style="height:5px;"></div>
			</div>
			
			<fieldset>
			<div>
				<div style="height: 35px;line-height:35px;background-color: #eeeeee">
					<div style="float:left;font-weight: bold;margin-right: 10px;font-size: 14px;color:#316685;padding-left:10px;">预约申请列表</div>
					<div style="float: left; display:inline;">
						<span style="margin-right: 5px;">申请日期</span><input type="text" class="date" style="width:120px;" onfocus="WdatePicker({onpicked:function(dp){changeApplyDate(dp);}})"/>
					</div>
					<div onclick="$('#sendMsgContainer').slideDown();" style="float:right;font-weight: bold;color:#316685;padding-right: 10px;text-decoration: underline;cursor: pointer;">手动短信提醒</div>
				</div>
				<div id="meetingRoomResCon"></div>
			</div>
			<div style="height:15px;"></div>
			</fieldset>
			<div style="height:15px;"></div>
		</security:authorize>	
		<div id="meetingRoomResInfoCon">
		</div>
		</div>
		
		<script type="text/javascript">
			function changeApplyDate(dp){
				refreshRes(dp.cal.getDateStr());
			}
			function changeMeetingDate(dp){
				refreshResInfo(dp.cal.getDateStr());
			}
			
			function refreshRes(applyDate){
				$("#meetingRoomResCon").addClass("waiting");
				var param = {};
				if(applyDate){
					param = {applyDate:applyDate};
				}
				$.post("${ctx}/oa/oa-chairman-reserve!list.action",param,function(result){
					$("#meetingRoomResCon").html(result).removeClass("waiting");
				});
			}
			function refreshResInfo(meetingDate){
				$("#meetingRoomResInfoCon").addClass("waiting");
				var param = {};
				if(meetingDate){
					param = {currDay:meetingDate};
				}
				$.post("${ctx}/oa/oa-chairman-reserve!res.action",param,function(result){
					$("#meetingRoomResInfoCon").html(result).removeClass("waiting");
				});
			}
			function resMeetingRoom(){
				var url = '${ctx}/oa/oa-chairman-reserve!input.action';
				window.parent.TabUtils.newTab("chairmanRes","预约总裁",url,true);
				
			}

			function reResMeetingRoom(id){
				var url = '${ctx}/oa/oa-chairman-reserve!input.action?id='+id;
				window.parent.TabUtils.newTab("chairmanRes","预约总裁",url,true);
			}
			
			function assign(id){
				$.post('${ctx}/oa/oa-chairman-reserve!status.action?id='+id,function(result){
					if(result == '0'){
						ymPrompt.win({
							title:'审核预约信息',
							fixPosition:true,
							width:550,
							height:370,
							closeBtn:false,
							iframe:{id:'assignChairmanResIframe',name:'assignChairmanResIframe',src:'${ctx}/oa/oa-chairman-reserve!assign.action?id='+id}
						});
					}else{
						alert('该预约已经审核通过！');
						window.location.href='${ctx}/oa/oa-chairman-reserve!main.action';
					}
				});
			}
			function delMeeting(id){
				if(confirm("确定要删除该条预约申请吗？")){
					$.post('${ctx}/oa/oa-chairman-reserve!delete.action?id='+id,function(){
						refreshRes();
					});
				}
			}
			function assignMeetingRoom(id){
				$.post('${ctx}/oa/oa-meeting-room-res!status.action?id='+id,function(result){
					if(result != "1"){
						ymPrompt.win({
							title:'会议预约审核',
							fixPosition:true,
							width:550,
							height:450,
							closeBtn:false,
							iframe:{id:'assignMeetingRoomIframe',name:'assignMeetingRoomIframe',src:'${ctx}/oa/oa-meeting-room-res!assign.action?id='+id}
						});
					}
				});
			}
			function delMeetingRoomRes(id){
				if(confirm("确定要删除该会议室申请记录吗？")){
					$.post('${ctx}/oa/oa-meeting-room-res!delete.action?id='+id,function(){
						refreshRes();
					});
				}
			}
			function showMeetingInfo(id){
				ymPrompt.win({
					title:'预约信息',
					fixPosition:true,
					width:550,
					height:385,
					showMask:false,
					iframe:{id:'showMeetingRoomIframe',name:'showMeetingRoomIframe',src:'${ctx}/oa/oa-chairman-reserve!look.action?id='+id}
				});
			}

			function showMeetingRoomInfo(id){
				ymPrompt.win({
					title:'会议室预定信息',
					fixPosition:true,
					width:550,
					height:450,
					showMask:false,
					iframe:{id:'showMeetingRoomIframe',name:'showMeetingRoomIframe',src:'${ctx}/oa/oa-meeting-room-res!look.action?id='+id}
				});
			}

			function showMyApp(){
				$("#pageNo").val(1);
				$("#myMeetingCon").slideDown();
				$("#myMeetingForm").ajaxSubmit(function(result){
					$("#myMeetingRoomRes").html(result);
				});
			}
			function closeMyMeetingCon(){
				$('#myMeetingCon').slideUp();
				$("#myMeetingForm input").val("");
			}

			function jumpPage(pageNo) {
				$("#myMeetingRoomRes").addClass("waiting");
				$("#pageNo").val(pageNo);
				$("#myMeetingForm").ajaxSubmit(function(result){
					$("#myMeetingRoomRes").html(result).removeClass("waiting");
				});
			}
			function jumpPageTo() {
				var index = $("#pageTo").val();
				index = parseInt(index);
				if (index > 0) {
					jumpPage(index);
				}
			}
			function sendMsg(){
				var userCds = $.trim($('#innerUserCds').val());
				var telNos = $.trim($('#telNoId').val());
				var content = $.trim($('#msgContent').val());
				if(userCds == "" && telNos == ""){
					alert("请选择接收人或者填写手机号");
					return;
				}
				if(content == ""){
					alert("请填写短信内容");
					$('#msgContent').focus();
					return;
				}
				var param = {
					userCds : userCds,
					telNos : telNos,
					msgContent : content 
				};
				TB_showMaskLayer("正在发送短信...");
				var url = '${ctx}/oa/oa-chairman-reserve!send.action';
				$.post(url,param,function(){
					TB_removeMaskLayer();
					alert('发送成功！');
					$('#innerUserCds').val("");
					$('#innerUserNames').val("");
					$('#telNoId').val("");
					$('#msgContent').val("");
				});
			}
			
			$(function(){
				refreshRes();
				refreshResInfo();
			});

		</script>
	</body>
</html>


