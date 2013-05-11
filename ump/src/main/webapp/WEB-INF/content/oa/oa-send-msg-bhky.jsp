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
		<style type="text/css">
			.content{
				border:1px solid #316685;
				padding:5px;
				line-height:30px;
			}
			.short{
				text-align:center;
				width:25px;
				border:none;
				border-bottom: 1px solid #000;
			}
			.long{
				text-align:center;
				width:120px;
				border:none;
				border-bottom: 1px solid #000;
			}
		
		</style>
	</head>
	
	<body style="line-height: 25px;">
		<div class="moduleTtile">
			<div style="float:left; height:29px; padding-left:8px; padding-top:3px;"><img src="${ctx}/images/meetingRoom/pic_chairman_res.gif" style="margin-top:1px;"></img></div>
			<div style="float:left; height:29px;color:#5a5a5a;font-weight: bold;">&nbsp;百货开业汇报</div>
		</div>
		<div style="height:2px;background-color: #5a5a5a;height:1px;margin-bottom:2px;margin-bottom: 8px;"></div>
		<div style="margin:2px">
		
		<div id="sendMsgContainer">
		<fieldset>
			<div>
				<div style="height: 35px;line-height:35px;background-color: #eeeeee">
					<div style="float:left;font-weight: bold;margin-right: 10px;font-size: 14px;color:#316685;padding-left:10px;">百货开业汇报</div>
				</div>
				<div>
					<table width="100%" cellpadding="0" cellspacing="0" class="editTable" style="margin:15px 0;">
						<security:authorize ifAnyGranted="A_SMS_BHKY_ADMIN">
						<tr>
							<td width="80px">接收人:</td>
							<td width="500px">
								<textarea id="innerUserNames" style="width:450px;height:40px;" onclick="getMember('inner','','0');"  readonly="readonly">${userNames}</textarea>
								<span onclick="getMember('inner','','0');" style="font-weight: bolder;cursor: pointer;text-decoration: underline">选择</span>
								<s:hidden id="innerUserCds" name="userCds"></s:hidden>
							</td>
							<td align="left">
								<button onclick="saveProvider();" style="width:100px;height:25px;line-height: 20px;">保存接收人</button>
							</td>
						</tr>
						</security:authorize>
						<security:authorize ifAnyGranted="A_SMS_BHKY_USER">
						<tr>
							<td width="80px">短信内容：</td>
							<td width="600px" >
								<div class="content" id="msgContent">
									<% int i=1; %>
									<form action="${ctx}/oa/oa-send-msg!sendBhky.action" method="post" id="msgForm">
									<input type="hidden" name="smsModule" value="bhky"/>
									百货
									<input type="text" name="content<%=i++ %>" class="short"/>月
									<input type="text" name="content<%=i++ %>" class="short"/>日
									<br/>
									即墨:共
									<input type="text" name="content<%=i++ %>" class="short" value="142"/>
									柜位,送签
									<input type="text" name="content<%=i++ %>" class="short"/>(
									<input type="text" name="content<%=i++ %>" class="short"/>%),新增
									<input type="text" name="content<%=i++ %>" class="short"/>,双签
									<input type="text" name="content<%=i++ %>" class="short"/>(
									<input type="text" name="content<%=i++ %>" class="short"/>%),新增
									<input type="text" name="content<%=i++ %>" class="short"/>;
									</form>
								</div>
								
								
							</td>
							<td valign="top">
								<div style="padding: 10px;margin-bottom:20px;">
									注：<br/>
									1.所有空格都需填写<br/>
								</div>
								<button onclick="sendMsg();" style="width:60px;height:25px;line-height: 20px;margin-left:20px;">发送</button>
							</td>
						</tr>
						</security:authorize>
					</table>
				</div>
			</div>
		</fieldset>
		<div style="height:5px;"></div>
		</div>
			
			<div style="height:15px;"></div>
		</div>
		
		<script type="text/javascript">
		
			function saveProvider(){
				var param = {
						'smsModule':'bhky',
						'userCds':$('#innerUserCds').val(),
						'userNames':$('#innerUserNames').val()
				};
				$.post('${ctx}/oa/oa-send-msg!saveRecv.action',param,function(){
					alert('保存成功');
				});
			}
			
			function sendMsg(){
				if(!confirm('确认信息无误并发送吗?'))return;
				TB_showMaskLayer("正在发送短信...");
				$('#msgForm').ajaxSubmit(function(){
					TB_removeMaskLayer();
					alert('发送成功！');
				});
			}
			
		</script>
	</body>
</html>


