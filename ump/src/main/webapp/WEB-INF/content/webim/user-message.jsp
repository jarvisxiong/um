<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.hhz.core.utils.DateOperator"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>管理区域</title>
	<meta http-equiv="Content-Type" content="text/html" />
	<link type="text/css" rel="stylesheet" href="${ctx}/css/common.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/webim.css" />
	<script language="javascript" src="${ctx}/js/jquery.js"></script>
	<script language="javascript" src="${ctx}/js/table.js"></script>
</head>
<body>
<s:form id="mainForm" action="user-message" method="post">
	<%--
	 <div class="search">
	 	<table style="width:100%;">
	 		<tr>
	 		<td align="left">
	 			<s:text name="webim.userMessageManager"></s:text>
	 		</td>
	 		<td align="right">
	 			<s:select list="mapTalkMember" listKey="key" listValue="value" name="talkMemberCd" id="talkMemberCd" value="talkMemberCd"/>
	 		</td>
	 		</tr>
	 		</table>
	 </div>
	<div id="tableDiv" style="border: 1px red solid;">
		<table class="commonTable" id="editTable" align="left" width="99%">
			<tr>
				<th style="display:none;"><s:text name="webimUserMessage.msgId" /></th>
				<th style="display:none;"><s:text name="webimUserMessage.fromUserCd" /></th>
				<th style="width:80px;"><s:text name="webimUserMessage.fromUserName" /></th>
				<th style="display:none;"><s:text name="webimUserMessage.toUserCd" /></th> 
				<th style="width:80px;"><s:text name="webimUserMessage.toUserName" /></th>
				<th style="display:none;"><s:text name="webimUserMessage.isViewed" /></th>
				<th ><s:text name="webimUserMessage.msgText" /></th>
				<th style="width:100px;"><s:text name="webimUserMessage.sendDate" /></th>
			</tr>
			<s:iterator value="page.result">
			<tr>
				<td style="display:none;">${msgId}</td>
				<td style="display:none;">${fromUserCd}</td>
				<td style="width:80px;">${fromUserName}</td>
				<td style="display:none;">${toUserCd}</td> 
				<td style="width:80px;">${toUserName}</td>
				<td style="display:none;">${isViewed}</td>
				<td style="width:300px;word-wrap: break-word;">${msgText}</td>
				<td style="width:100px;"><%=DateOperator.formatDate(new Date((Long)JspUtil.findValue("sendDate")),"yyyy-MM-dd HH:mm:ss") %>  <s:date name="sendDate" format="yyyymmdd"/></td>
			</tr>
			</s:iterator>
			<tr> 
				<td colspan="4" align="center">
					<p:page/>  
				</td>
			</tr>
		</table>
	</div>
	--%>
		
	 	<table style="width:99%;padding-left: 10px;">
	 	<tr>
		 	<td align="left">
	 			<s:text name="webim.userMessageManager"></s:text>
			</td> 
	 		<td align="right">
	 			<span>联系人:</span>
	 			<s:select list="mapAllChattors" listKey="key" listValue="value"  name="chattorCd" id="chattorCd" value="chattorCd"/>
	 			<a class="buttom" href="javascript:searchChatHistory()">搜索</a>
	 		</td>
		</tr>
		</table>
		<table class="commonTable" id="editTable" align="left" style="width:99%;">
		<tr>
		<td>
			<div id="chatting_content" class="chatting_content" style="width:100%;height:450px;">
				<s:iterator value="page.result">
					<%-- class: me/buddy --%>
					<div style="padding:5px;">
						<input type="hidden" id="msgId" name="msgId" value="${msgId}"/>
						<input type="hidden" id="fromUserCd" name="fromUserCd"  value="${fromUserCd}"/>
						<input type="hidden" id="toUserCd" name="toUserCd" value="${toUserCd}"/>
						<span class="dlg_msg_title">${fromUserName}
							<font class="dlg_msg_time">
							<%--
								<%=DateOperator.formatDate(new Date((Long)JspUtil.findValue("sendDate")),"yyyy-MM-dd HH:mm:ss") %>  
							 --%>
								<s:date name="sendDate" format="yyyyMMdd"/>
							</font>
						</span>	
						<p class="dlg_msg_content">${msgText}</p>
					</div> 
				</s:iterator>
			</div>
		</td>
		</tr>
		<tr> 
			<td align="center">
				<p:page/>  
			</td>
		</tr>
		</table>
		
</s:form>

<script language="javascript">

	//渲染
	var currentUserCd = '${currentUserCd}';
	$("#chatting_content > div").each(function(){
		var fromUserCd = $(this).find("#fromUserCd").val();
		if(currentUserCd == fromUserCd){
			$(this).addClass("dlg_from_self");
		}else{
			$(this).addClass("dlg_from_other");
		}
	});


	//搜索历史聊天记录
	function searchChatHistory(){
		document.location = "${ctx}/webim/user-message!searchChatHistory.action?chattorCd=" + $("#chattorCd").val();
	}
 
</script>

</body>
</html>
