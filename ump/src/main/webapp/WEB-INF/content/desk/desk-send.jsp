<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/global.jsp" %>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>消息推送</title>
		<link href="${ctx}/css/desk/desk-oa.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/userChoose.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
		<link href="${ctx}/css/desk/thickbox.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" type="text/css" />
		<script type='text/javascript' src='${ctx}/dwr/interface/MsgTipService.js'></script>
		<script type='text/javascript'src='${ctx}/dwr/engine.js'></script>
		<script type='text/javascript' src='${ctx}/dwr/util.js'></script>
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>	
		<script type=text/javascript src="${ctx}/js/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/js/userChoose.js"></script>
		<script src="${ctx}/resources/js/common/TreePanel.js" type="text/javascript"></script>
		<script type=text/javascript src="${ctx}/js/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/xheditor/xheditor-zh-cn.min.js"></script>
	</head>
	
	<body style="line-height: 25px;">
		<div class="moduleTtile">
			<div style="float:left; height:29px; padding-left:8px; padding-top:3px;"><img src="${ctx}/images/meetingRoom/msg.png" style="margin-top:1px;"></img></div>
			<div style="float:left; height:29px;color:#5a5a5a;font-weight: bold;">&nbsp;消息推送</div>
		</div>
		<div style="height:2px;background-color: #5a5a5a;height:1px;margin-bottom:2px;margin-bottom: 8px;"></div>
		<div style="margin:2px">
			<fieldset>
				<div>
					<div style="height: 35px;line-height:35px;background-color: #eeeeee">
						<div style="float:left;font-weight: bold;margin-right: 10px;font-size: 14px;color:#316685;padding-left:10px;">消息推送</div>
					</div>
					<div style="padding:5px;">
						<table width="100%" cellpadding="0" cellspacing="0" class="editTable">
							<tr>
								<td width="80px">选择接收人:</td>
								<td>
									<textarea id="receUserNames" style="width:450px;height:40px;" onclick="getMember('rece','','0');"  readonly="readonly"></textarea>
									<span onclick="getMember('rece','','0');" style="font-weight: bolder;cursor: pointer;text-decoration: underline">选择</span>
									<s:hidden id="receUserCds"></s:hidden>
								</td>
								<td>当前PD不在线人员,将无法收到推送的消息。</td>
							</tr>
							<tr>
								<td width="80px">快捷选择</td>
								<td>
									<input type="checkbox" name="quickSelect" value="all"></input>
									<span>全部在线人员</span>
									<input type="checkbox" name="quickSelect" value="allCenter"></input>
									<span>中心在线人员</span>
									<input type="checkbox" name="quickSelect" value="allDept"></input>
									<span>部门在线人员</span>
									
								</td>
								<td>选择后会<span style="color:red">自动清空</span>【选择接收人】一栏的内容,发送消息以快捷选择为主。</td>
							</tr>
							<tr>
								<td width="80px">推送内容：</td>
								<td>
									<textarea id="msgContent"  style="width:450px;height:150px;"></textarea>
								</td>
								<td valign="bottom">
									<button onclick="sendMsg();" style="width:60px;height:25px;line-height: 20px;">推送</button>
								</td>
							</tr>
							<tr>
								<td colspan="3" align="center">
									<div id="sucessInfoId" style="display:none; color:red;font-weight: bold;font-size: 14px;">消息推送成功</div>
									<div id="errorUserDivId" style="display: none;padding-top:10px;" align="left">
									<p style="font-weight: bold;">以下用户因不在线,无法推送(在线人员已经成功推送)</p>
									<span></span>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
		</fieldset>
	</div>
	<script type="text/javascript">
	var msgContentEditor;
	$(function(){
		$(':checkbox[name="quickSelect"]').click(function(){
			if($(this).attr('checked')==true){
				$(this).siblings().attr('checked',false);
				$('#receUserNames').val('');
				$('#receUserCds').val('');
			}
		});
		
		msgContentEditor = $('#msgContent').xheditor({
			tools:'full',forcePtag:false,showBlocktag:false,internalStyle:false,
			html5Upload:false,upMultiple:'1',
			upLinkUrl:_ctx+"/oa/oa-email!upload.action",upLinkExt:"xlsx,docx,doc,xls,pdf,zip,rar,txt",
			upImgUrl:_ctx+"/oa/oa-email!upload.action",upImgExt:"jpg,jpeg,gif,png",
			upFlashUrl:_ctx+"/oa/oa-email!upload.action",upFlashExt:"swf",
			upMediaUrl:_ctx+"/oa/oa-email!upload.action",upMediaExt:"avi"
		}
		);
	});
	
	function sendMsg(){
		
		$('#errorUserDivId').hide();
		
		var userCds = $.trim($('#receUserCds').val());
		var quickType = $.trim($('input:checked[name="quickSelect"]').val());
		
		var content = msgContentEditor.getSource();
		
		if(userCds == "" && quickType == ""){
			alert("请选择接收人或者选择快捷操作");
			return;
		}
		if(content == ""){
			alert("请填写短信内容");
			msgContentEditor.focus();
			return;
		}
		if(quickType != ''){
			userCds = quickType;
		}
		content = '<div style="line-height:20px;padding:10px;">'+content+'</div>';
		var param = {
			userCds : userCds,
			content : content 
		};
		TB_showMaskLayer("正在推送消息...");
		MsgTipService.send(param,{
			callback:function(r){
				TB_removeMaskLayer();
				$('#sucessInfoId').slideDown();
				setTimeout(function(){$('#sucessInfoId').slideUp();}, 5000);
				$('#errorUserDivId').show();
				$('#errorUserDivId span').text(r.toString());
			},
			errorHandler:function(errorString, exception) { 
				TB_removeMaskLayer();
				alert(errorString);
			}
		});
	}
	</script>
</body>
</html>


