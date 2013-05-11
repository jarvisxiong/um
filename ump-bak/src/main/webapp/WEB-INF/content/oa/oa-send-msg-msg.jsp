<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/global.jsp" %>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>短信发送</title>
		<link href="${ctx}/css/desk/desk-oa.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/userChoose.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
		<link href="${ctx}/css/desk/thickbox.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>	
		<script type=text/javascript src="${ctx}/js/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/js/userChoose.js"></script>
		<script src="${ctx}/resources/js/common/TreePanel.js" type="text/javascript"></script>
		<script type=text/javascript src="${ctx}/js/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	</head>
	
	<body style="line-height: 25px;">
		<div class="moduleTtile">
			<div style="float:left; height:29px; padding-left:8px; padding-top:3px;"><img src="${ctx}/images/meetingRoom/pic_chairman_res.gif" style="margin-top:1px;"></img></div>
			<div style="float:left; height:29px;color:#5a5a5a;font-weight: bold;">&nbsp;短信发送</div>
		</div>
		<div style="height:2px;background-color: #5a5a5a;height:1px;margin-bottom:2px;margin-bottom: 8px;"></div>
		<div style="margin:2px">
		
		<div id="sendMsgContainer">
		<fieldset>
			<div>
				<div style="height: 35px;line-height:35px;background-color: #eeeeee">
					<div style="float:left;font-weight: bold;margin-right: 10px;font-size: 14px;color:#316685;padding-left:10px;">人工短信提醒</div>
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
								<button onclick="sendMsg();" style="width:60px;height:25px;line-height: 20px;">发送</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</fieldset>
		<div style="height:5px;"></div>
		</div>
			
			<div style="height:15px;"></div>
		</div>
		
		<script type="text/javascript">
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
				var url = '${ctx}/oa/oa-send-msg!send.action';
				$.post(url,param,function(){
					TB_removeMaskLayer();
					alert('发送成功！');
					$('#innerUserCds').val("");
					$('#innerUserNames').val("");
					$('#telNoId').val("");
					$('#msgContent').val("");
				});
			}
			
		</script>
	</body>
</html>


