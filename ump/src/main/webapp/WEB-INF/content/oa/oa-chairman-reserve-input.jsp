<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%><html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/global.jsp" %>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>预约总裁</title>
		<link href="${ctx}/css/desk/desk-oa.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/resources/css/common/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	</head>
	
	<body>
		<div class="title_bar">
			<div style="float:left; margin-left:8px; font-size:18px; font-weight:bolder;">预约总裁</div>
		</div>
		<div style="height:25px;line-height: 25px;padding-left:5px;">
			<span style="color:red;">提示：本模块预约会议默认是在总裁办公室进行,若要使用其他会议室请使用会议室预定模块。</span>
			<a style="color:red;font-weight: bold;text-decoration: underline;" href="javascript:resMeetingRoom();">预定会议室</a>	
		</div>
		<div>
			<fieldset style="margin:0 5px;padding: 5px;">
			<div style="float:left;padding-left: 10px;width:490px;border-right: 1px solid #BFC4C8;">
				<form action="${ctx}/oa/oa-chairman-reserve!save.action" method="post" id="roomForm">
				<s:hidden name="status" value="0"/>
				<div style="font-weight:bold;margin-bottom: 10px;"><span style="margin-right:5px;">预约总裁</span><img src="${ctx}/images/meetingRoom/pic_down_blue.gif"/></div>
				<table width="100%" cellpadding="0" cellspacing="0" class="editTable">
					<tr>
						<td width="80px">预约<font color="red">*</font></td>
						<td>
							<div>
								<input type="checkbox" name="chairmanType" id="roomIds-0" value="0"/>
								<label class="checkboxLabel" for="roomIds-0">总裁</label>
								<input type="checkbox" name="chairmanType" id="roomIds-1" value="1"/>
								<label class="checkboxLabel" for="roomIds-1">执行总裁</label>
							</div>
						</td>
					</tr>
					<tr>
						<td width="80px">会议主题<font color="red">*</font></td>
						<td><s:textfield name="subject" id="subject" cssStyle="width:350px"/></td>
					</tr>
					<tr>
						<td>会议日期 <font color="red">*</font></td>
						<td >
							<s:textfield cssStyle="color:red" cssClass="date" id="meetingDateId" name="currDay" onfocus="WdatePicker({minDate:'%y-%M-%d'})"/>
							<input type="text"  value="<s:date name="beginTime" format='HH:mm' />" name="meetingBegin" id="meetingDateBeginId" class="time" style="width:80px;color:red;" onfocus="WdatePicker({dateFmt:'HH:mm'})"/>
							到
							<input type="text" value="<s:date name="endTime" format='HH:mm' />" name="meetingEnd" id="meetingDateEndId" class="time" style="width:80px;color:red;" onfocus="WdatePicker({dateFmt:'HH:mm'})"/></td>
					</tr>
					<tr>
						<td>主持人 <font color="red">*</font></td>
						<td>
							<input type="text" name="compereName" value="<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("compere")) %>" onkeyup="showSearchUser(this)"/>
							<s:hidden name="compere" id="compere" />
						</td>
					</tr>
					<tr>
						<td>召集人 <font color="red">*</font></td>
						<td>
							<input type="text" name="applicantName" value="<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("applicant")) %>" onkeyup="showSearchUser(this)"/>
							<s:hidden name="applicant" id="applicant" />
						</td>
					</tr>
					<tr>
						<td>记录人<font color="red">*</font></td>
						<td>
							<input type="text" name="recorderName" value="<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("recorder")) %>" onkeyup="showSearchUser(this)"/>
							<s:hidden name="recorder" id="recorder"/>
						</td>
					</tr>
					<tr>
						<td>参会人员<font color="red">*</font></td>
						<td>
							<textarea id="otherUserNames"  name="participatorNames" style="width:350px;height:50px;"  readonly="readonly"><%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("participators"),";") %></textarea>
							<s:hidden id="otherUserCds" name="participators"></s:hidden>
							<span onclick="$('#otherUserNames').trigger('click')" style="font-weight: bolder;cursor: pointer;text-decoration: underline">选择</span>
						</td>
					</tr>
					<tr>
						<td>参会人数<font color="red">*</font></td>
						<td>约&nbsp;&nbsp;<s:textfield name="partNum"  cssStyle="width:30px" id="otherNum" />&nbsp;&nbsp;人</td>
					</tr>
					<tr >
						<td>注意事项</td>
						<td><s:textarea name="attention" cssStyle="width:350px;height:60px;"/></td>
					</tr>
					<tr>
						<td>备注</td>
						<td><s:textarea name="remark" cssStyle="width:350px;height:60px;"/></td>
					</tr>
					<tr style="height:10px;">
					<td colspan="2">&nbsp;</td>
					</tr>
					<tr style="height:50px;">
						<td colspan="2" align="center">
							<input type="button" class="btn_blue" onclick="saveRes();" value="预约" />
							<input type="button" class="btn_red" onclick="cancel();" value="取消" />
						</td>
					</tr>
				</table>	
				</form>
			</div>
			<div style="margin-left:510px;max-width: 300px;">
					<div style="font-weight:bold;">
					<span style="margin-right:5px;">已预约情况</span><img src="${ctx}/images/meetingRoom/pic_down_blue.gif"/>
					</div>
					<div style="margin-top: 10px;margin-bottom: 10px;"><span style="margin-right:5px;">选择日期</span>
					<input type="text" value="${currDay}" style="width:70px;" onfocus="WdatePicker({minDate:'%y-%M-%d',onpicked:function(dp){changeMeetingDate(dp);}})"/>
					</div>
					<div id="meetingRoomResCon"></div>
			</div>
			</fieldset>
		</div>
		<div id="searchUserDiv" class="searchUserDiv"></div>
		<script type="text/javascript">
			var cfg = {};
			cfg.data = {tabId:'chairmanRes'};
			function saveRes(){
				if(!checkForm())return;
				var confStr = "请确认预约信息：\r\r";
				var roomName= [];
				$("#roomForm div input[name='chairmanType']:checked").each(function(){
					roomName.push($(this).next().text());
				});
				confStr += roomName.join(",")+"\r";
				var dateStr = $("#meetingDateId").val()+"   "+$("#meetingDateBeginId").val()+" ~ "+$("#meetingDateEndId").val();
				
				if(!confirm(confStr+dateStr))return;
				
				$("#roomForm").ajaxSubmit(function(result){
					if(result == "success"){
						alert("申请已提交成功,等待总裁秘书/助理审批！");
						window.parent.TabUtils.closeTab(cfg);
					}else{
						window.parent.TabUtils.closeTab(cfg);
					}
				});
			}
			function checkForm(){
				var str = [];
				if($("#roomForm div input[name='chairmanType']:checked").length == 0){
					str.push("请选择预约对象");
				}
				if($.trim($("#subject").val()) == ""){
					str.push("主题不能为空");
				}
				if($("#meetingDateId").val() == ""||$("#meetingDateBeginId").val() == ""||$("#meetingDateEndId").val() == ""){
					str.push("会议日期不能为空");
				}
				if($.trim($("#compere").val()) == ""){
					str.push("请选择主持人");
				}
				if($.trim($("#applicant").val()) == ""){
					str.push("请选择召集人");
				}
				if($.trim($("#recorder").val()) == ""){
					str.push("请选择记录人");
				}
				if($.trim($("#otherUserCds").val()) == ""){
					str.push("请选择参会人员");
				}
				if($.trim($("#otherNum").val()) == ""){
					str.push("请填写参会人数");
				}
				if(str.length>0){
					alert(str.join("\r"));
					return false
				}
				return true;
			}
			function cancel(){
				window.parent.TabUtils.closeTab(cfg);
			}

			var searchTime;
			function showSearchUser(dom){
				var $currentDom = $(dom);
				var $next = $(dom).next();
				
				if(searchTime)clearTimeout(searchTime);
				searchTime = setTimeout(function(){
					var val = $.trim($currentDom.val());
					$next.val("");
					if(val == ""){
						$("#searchUserDiv").slideUp(200);
						return;
					}
					$.post("${ctx}/oa/oa-email!getUsersByFilter.action",{value:val,maxNum:10},function(result){
						result = eval(result);
						var $offset = $currentDom.offset();
						$("#searchUserDiv").css({left:$offset.left,top:$offset.top+$currentDom.height()}).empty().show();
						$.each(result,function(i,n){
							var html = "<div uiid='"+n.uiid+"'><span>"+n.userName +"</span>|<span>"+ n.orgName+"</span></div>";
							$("#searchUserDiv").append(html);
						});
					
						$("#searchUserDiv div").click(function(){
							var userName = $(this).children("span:first").text();
							var uiid = $(this).attr("uiid");
							$currentDom.val(userName);
							$next.val(uiid);
							$("#searchUserDiv").slideUp(200);
						});
						//$("#searchUserDiv").focus();
						//$(dom).focus();
					});
				}, 300);
				
				$(document).bind('click',function(event){
					var event  = window.event || event;
				    var obj = event.srcElement ? event.srcElement : event.target;
				    if(obj != dom){
				    	$("#searchUserDiv").slideUp(200);
				    	if($next.val() == ''){
				    		$currentDom.val('');
				    	}
				    }
				    $(document).unbind('click');
				});
				return false;
			}

			
			function changeMeetingDate(dp){
				refreshResInfo(dp.cal.getDateStr());
			}
			
			function refreshResInfo(meetingDate){
				$("#meetingRoomResCon").addClass("waiting");
				var param = {"simple":"1"};
				if(meetingDate){
					param.currDay = meetingDate;
				}
				$.post("${ctx}/oa/oa-chairman-reserve!res.action",param,function(result){
					$("#meetingRoomResCon").html(result).removeClass("waiting");
				});
			}

			function resMeetingRoom(){
				var url = '${ctx}/oa/oa-meeting-room-res!input.action';
				window.parent.TabUtils.newTab("resMeeting","预定会议室",url,true);
				
			}
			
			/*
			*旧的人员选择框
			function chooseUser(){
				getMember('other','','0');
			}*/
			
			$(function(){
				//人员选择框初始化
				$('#otherUserNames').ouSelect({showGroupFlg : true});
				
				$("#roomIds-0").click(function(){
					if($(this).attr("checked")==true){
						$("#roomIds-1").attr("checked",false).next().removeClass("red");
						$(this).next().addClass("red");
					}else{
						$(this).next().removeClass("red");
					}
				});
				$("#roomIds-1").click(function(){
					if($(this).attr("checked")==true){
						$("#roomIds-0").attr("checked",false).next().removeClass("red");
						$(this).next().addClass("red");
					}else{
						$(this).next().removeClass("red");
					}
				});
				var chairmanType = "<s:property value='chairmanType'/>";
				$("#roomIds-"+chairmanType).attr("checked",true).next().addClass("red");
				refreshResInfo();
			});
		</script>
	</body>
</html>


