<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>会议室预定</title>
		<%@ include file="/common/global.jsp"%>
		<link href="${ctx}/css/desk/desk-oa.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css" />
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>	
		<script type=text/javascript src="${ctx}/js/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	</head>
	
	<body <s:if test="flag == 'true'">onLoad="showMeetingInfo('${objId}')"</s:if>>
		<div class="title_bar">
			<div style="float:left; font-size:18px; font-weight:bolder;">会议室预定
			<s:if test="addrType == 'GB'">
			
			</s:if>
			<s:else>
			
			</s:else>
			</div>
			<div style="float:right; margin-top:6px;">
				<div class="btn_blue" style="float:left; margin-right:5px;" onclick="resMeetingRoom();">预定会议室</div>
				<div class="btn_green" style="float:left; margin-right:5px;" onclick="showMyApp();">我的申请</div>
				<div class="btn_green" style="float:left; margin-right:5px;" onclick="window.location.href='${ctx}/oa/oa-meeting-room-res!main.action?addrType=${addrType}'">刷新</div>
				<div class="btn_green" style="float:left; margin-right:5px; width:100px;" onclick="showRoomMap();">会议室分布图</div>
				<div class="btn_green" style="float:left; margin-right:5px;" onclick="window.open('${ctx}/oa/oa-meeting-room-res!main.action?addrType=${addrType}')">全屏</div>
			</div>
		</div>
		<div style="height:2px;background-color: #5a5a5a;height:1px;margin-bottom:2px;margin-bottom: 8px;"></div>
		<div style="margin:2px">
		
		<div id="myMeetingCon" style="display:none">
			<fieldset>
				<form action="${ctx}/oa/oa-meeting-room-res!myres.action?addrType=${addrType}" method="post" id="myMeetingForm">
				<div>
					<div style="height: 35px;line-height:35px;background-color: #eeeeee">
						<div style="float:left;font-weight: bold;margin-right: 10px;font-size: 14px;color:#316685;padding-left:10px;">我的申请</div>
						<div style="float: left; display:inline;">
							<span style="margin-right: 5px;">申请日期</span>
							从<input type="text" name="filter_GED_createdDate" class="date" style="width:100px;" onfocus="WdatePicker()"/>
							到<input type="text" name="filter_LTD_createdDate" class="date" style="width:100px;" onfocus="WdatePicker()"/>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" class="btn_blue" onclick="showMyApp();" value="搜索" />
						</div>
						<div class="meetingClose" onclick="closeMyMeetingCon();"></div>
					</div>
					<div id="myMeetingRoomRes"></div>
				</div>
				</form>
			</fieldset>
			<div style="height:5px;"></div>
		</div>
		
		<security:authorize ifAnyGranted="A_MEETING_ROOM_ADMIN">	
			<s:if test="addrType == 'GB'">
			<fieldset>
			<div>
				<div style="height: 35px;line-height:35px;background-color: #eeeeee">
					<div style="float:left;font-weight: bold;margin-right: 10px;font-size: 14px;color:#316685;padding-left:10px;">会议室预定申请列表</div>
					<div style="float: left; display:inline;">
						<span style="margin-right: 5px;">申请日期</span><input type="text" class="date" style="width:120px;" onfocus="WdatePicker({onpicked:function(dp){changeApplyDate(dp);}})"/>
					</div>
				</div>
				<div id="meetingRoomResCon"></div>
			</div>
			<div style="height:15px;"></div>
			</fieldset>
			<div style="height:15px;"></div>
			</s:if>
		</security:authorize>	
		<security:authorize ifAnyGranted="A_MEETING_ROOM_SHC">	
			<s:if test="addrType == 'SHC'">
			<fieldset>
			<div>
				<div style="height: 35px;line-height:35px;background-color: #eeeeee">
					<div style="float:left;font-weight: bold;margin-right: 10px;font-size: 14px;color:#316685;padding-left:10px;">会议室预定申请列表</div>
					<div style="float: left; display:inline;">
						<span style="margin-right: 5px;">申请日期</span><input type="text" class="date" style="width:120px;" onfocus="WdatePicker({onpicked:function(dp){changeApplyDate(dp);}})"/>
					</div>
				</div>
				<div id="meetingRoomResCon"></div>
			</div>
			<div style="height:15px;"></div>
			</fieldset>
			<div style="height:15px;"></div>
			</s:if>
		</security:authorize>	
			
			<div id="meetingRoomResInfoCon">
				
			</div>
		</div>
		
		<script type="text/javascript">
			var addrType = '${addrType}';
			function changeApplyDate(dp){
				refreshRes(dp.cal.getDateStr());
			}
			function changeMeetingDate(dp){
				refreshResInfo(dp.cal.getDateStr());
			}
			
			function refreshRes(applyDate){
				$("#meetingRoomResCon").addClass("waiting");
				var param = {
					'addrType' : addrType
				};
				if(applyDate){
					param.applyDate = applyDate;
				}
				$.post("${ctx}/oa/oa-meeting-room-res!list.action",param,function(result){
					$("#meetingRoomResCon").html(result).removeClass("waiting");
					autoHeight();
				});
			}
			function refreshResInfo(meetingDate){
				$("#meetingRoomResInfoCon").addClass("waiting");
				var param = {
						'addrType' : addrType
					};
				if(meetingDate){
					param.currDay = meetingDate;
				}
				$.post("${ctx}/oa/oa-meeting-room-res!res.action",param,function(result){
					$("#meetingRoomResInfoCon").html(result).removeClass("waiting");
					autoHeight();
				});
			}
			function resMeetingRoom(){
				var checkedRooms = new Array();
				$("#resRoomInfoTable tr td input[name=selectedRoom]:checked").each(function(i, dom) {
					checkedRooms.push("roomIds=" + $(dom).val());
				});
				//if(checkedRooms.length == 0){
				//	alert("请勾选需要预定的会议室");
				//	return false;
				//}
				var param = checkedRooms.join("&");
				var currDay = $("#currMeetingDay").val();
				param += "&currDay="+ currDay;
				param += "&addrType=${addrType}";
				var url = '${ctx}/oa/oa-meeting-room-res!input.action?'+param;

				window.parent.TabUtils.newTab("resMeeting","预定",url,true);
				
			}

			function reResMeetingRoom(id){
				var url = '${ctx}/oa/oa-meeting-room-res!input.action?id='+id+'&addrType='+addrType;
				window.parent.TabUtils.newTab("resMeeting","预定",url,true);
			}
			
			function assign(id){
				$.post('${ctx}/oa/oa-meeting-room-res!status.action?id='+id+'&addrType='+addrType,function(result){
					if(result == "0"){
						ymPrompt.win({
							title:'会议室分配',
							fixPosition:true,
							width:550,
							height:510,
							closeBtn:false,
							iframe:{id:'assignMeetingRoomIframe',name:'assignMeetingRoomIframe',src:'${ctx}/oa/oa-meeting-room-res!assign.action?id='+id+'&addrType='+addrType}
						});
					}else{
						alert("该会议已经确认过！");
						window.location.href='${ctx}/oa/oa-meeting-room-res!main.action?addrType='+addrType;
					}
				});
			}
			function delMeeting(id){
				if(confirm("确定要删除该会议室申请记录吗？")){
					$.post('${ctx}/oa/oa-meeting-room-res!delete.action?id='+id,function(){
						refreshRes();
					});
				}
			}
			function showMeetingInfo(id){
				ymPrompt.win({
					title:'会议室预定信息',
					fixPosition:true,
					width:550,
					height:480,
					showMask:false,
					iframe:{id:'showMeetingRoomIframe',name:'showMeetingRoomIframe',src:'${ctx}/oa/oa-meeting-room-res!look.action?id='+id+'&addrType='+addrType}
				});
			}
			function showRoomMap(){
				var msg = "";
				if(addrType == 'GB'){
					msg = "<div align='center' style='margin-top:10px;'><img src='${ctx}/images/meetingRoom/pic_Meetingroom_Map.jpg'/></div>";
				}else if(addrType == 'SHC'){
					msg = "<div align='center' style='margin-top:30px;font-size:18px;font-weight:bold;color:#5B6B83'>待添加</div>";
				}
				ymPrompt.win({
					title:'会议室分布图',
					fixPosition:true,
					width:550,
					height:400,
					message:msg
				});
			}

			function showMyApp(){
				$("#pageNo").val(1);
				$("#myMeetingForm").ajaxSubmit(function(result){
					$("#myMeetingRoomRes").html(result);
					$("#myMeetingCon").fadeIn();
				});
			}
			function closeMyMeetingCon(){
				$('#myMeetingCon').fadeOut();
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
			
			$(function(){
				refreshRes();
				refreshResInfo();
			});

		</script>
	</body>
</html>


