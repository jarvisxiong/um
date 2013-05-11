﻿<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/email/email.css"  />

<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type=text/javascript src="${ctx}/resources/js/common/common.js"></script>
<script src="${ctx}/resources/js/common/TreePanel.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/js/xheditor/xheditor-zh-cn.min.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script src="${ctx}/js/datePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/home/email.js"></script>
<title></title>
<script type="text/javascript">
$(function(){
	var _bodyHeight = Number($(document).height()-130);
	PAGE_SIZE = Math.ceil((_bodyHeight)/24);
	return;
});
</script>
</head>

<body>
<div class="mailTop">
	<div style="float:left;">
		内部
	</div>
	<div style=" margin-left: 123px;color:#000;font-weight: normal;font-size: 12px;">
		<form action="${ctx}/oa/oa-email!list.action" method="post" id="quickSearchForm" onkeydown="formEnter(event);">
		<table><tr>
			<td width="80px">
				<select name="boxId" id="quickSearchBoxId" style="width:100%" onchange="changeUserFilter(this)">
					<option value="1">收件箱</option>
					<option value="2">草稿箱</option>
					<option value="3">发件箱</option>
					<option value="4">垃圾箱</option>
				</select>
			</td>
			<td userType="send" align="center" width="50px">发件人</td>
			<td userType="send" >
				<s:textfield cssStyle="width:80px" name="filter_LIKES_sender" onkeyup="showSearchUser(this)" ></s:textfield>
			</td>
			<td userType="receive" style="display:none" align="center" width="50px">收件人</td>
			<td userType="receive" style="display:none">
				<s:textfield cssStyle="width:80px" name="filter_LIKEC_toUserNames" onkeyup="showSearchUser(this)"></s:textfield>
			</td>
			<td width="50px" align="center">主题</td>
			<td><s:textfield cssStyle="width:100px" name="filter_LIKES_subject"></s:textfield></td>
			<td align="center" id="qucikSearchBtn" onclick="$('#quickSearchPageSize').val(PAGE_SIZE);$('#quickSearchOrderBy,#quickSearchOrder,#quickSearchSort').val('');quickSearch();" style="cursor: pointer;">
				<input type="hidden" name="pageEmail.pageNo" id="quickSearchPageNo" value="1"/>
				<input type="hidden" name="pageEmail.orderBy" id="quickSearchOrderBy" />
				<input type="hidden" name="pageEmail.order" id="quickSearchOrder" />
				<input type="hidden" name="pageEmail.pageSize" id="quickSearchPageSize" />
				<input type="hidden" name="sort" id="quickSearchSort" />
				<div style="float:left"><img class="imgNormal" src="${ctx}/resources/images/email/search_email.gif" /></div>
				<div style="float:left">快速搜索</div>
			</td>
		</tr></table>
		</form>
	</div>
</div>
<div id="mailBottom">
	<div class="mailLeft">
		<input type="hidden" id="currentPageNo" value="1" />
		<input type="hidden" id="quickSearchFlag" value="false" />
		
		<%-- 2011-10-10 禁用按钮
		<div class="left_btn_container" style="display: none;">
			<div class="collectEmailBtn" onclick="getMailList(1);">收</div>
			<security:authorize ifNotGranted="A_EMAIL_READ_ONLY">
				<div class="writeEmailBtn" onclick="newMail();">写</div>
			</security:authorize>
			<security:authorize ifAnyGranted="A_EMAIL_READ_ONLY">
				<div class="writeEmailBtn">写</div>
			</security:authorize>
		</div>
		  --%>
		
		<div class="leftMenu">
			<div id="unReadBoxId" class="box" onclick="getMailList(5)">
				<div class="boxImg">
				<img class="imgNormal" style="margin-top:1px" src="${ctx}/resources/images/email/unRead_box.gif"/>
				</div>
				<div class="boxText">&nbsp;未读(<span id="unReadBoxNum">0</span>)</div>
			</div>
			<div id="inBoxId" class="box" onclick="getMailList(1);">
				<div class="boxImg">
				<img class="imgNormal" src="${ctx}/resources/images/email/in_box.gif"/>
				</div>
				<div class="boxText">
				&nbsp;收件箱(<span id="inBoxNum">0</span>)</div>
			</div>
			<div id="outBoxId" class="box" onclick="getMailList(3);">
				<div class="boxImg">
				<img class="imgNormal" src="${ctx}/resources/images/email/out_box.gif"/>
				</div>
				<div class="boxText">&nbsp;发件箱(<span id="outBoxNum">0</span>)</div>
			</div>
			<div id="draftBoxId" class="box" onclick="getMailList(2);">
				<div class="boxImg">
				<img class="imgNormal" style="margin:0" src="${ctx}/resources/images/email/draft_box.gif"/>
				</div>
				<div class="boxText">
				&nbsp;草稿箱(<span id="draftBoxNum">0</span>)
				</div>
			</div>
			<div id="delBoxId" class="box" onclick="getMailList(4)">
				<div class="boxImg">
				<img class="imgNormal" src="${ctx}/resources/images/email/del_box.gif" />
				</div>
				<div class="boxText">
				&nbsp;垃圾箱(<span id="delBoxNum">0</span>)
				</div>
			</div>
			<div id="searchBoxId" class="box" onclick="searchEmail();">
				<div class="boxImg">
					<img class="imgNormal" src="${ctx}/resources/images/email/search_email.gif" />
				</div>
				<div class="boxText">
				&nbsp;高级搜索
				</div>
			</div>
			<div id="emailSignId" class="box" onclick="showEmailSign();">
				<div class="boxImg">
					<img class="imgNormal" src="${ctx}/resources/images/email/sign.gif" />
				</div>
				<div class="boxText">
				&nbsp;签名档设置
				</div>
			</div>
			<div class="box" onclick="showEmailGroup();">
				<div class="boxImg">
					<img class="imgNormal" src="${ctx}/resources/images/email/pic_email_custom.gif" />
				</div>
				<div class="boxText">
				&nbsp;自定义收件组
				</div>
			</div>
			<%--
			<div class="box" onclick="window.parent.openExchange();">
				<div class="boxImg">
					<img class="imgNormal" src="${ctx}/resources/images/email/pic_email_outside.gif" />
				</div>
				<div class="boxText" style="color:#0167a2">
				&nbsp;外部
				</div>
			</div>
			 --%>
			<security:authorize ifAnyGranted="A_EMAIL_AMDIN_DEL">
			<div id="email_admin_id" class="box" onclick="emailManage('A_EMAIL_AMDIN_DEL');">
				<div class="boxImg">
					<img class="imgNormal" src="${ctx}/resources/images/email/sign.gif" />
				</div>
				<div class="boxText">
				&nbsp;管理
				</div>
			</div>
			</security:authorize>
			<security:authorize ifAnyGranted="A_EMAIL_AMDIN_READ">
			<div id="email_admin_id" class="box" onclick="emailManage('A_EMAIL_AMDIN_READ');">
				<div class="boxImg">
					<img class="imgNormal" src="${ctx}/resources/images/email/sign.gif" />
				</div>
				<div class="boxText">
				&nbsp;管理
				</div>
			</div>
			</security:authorize>
		</div>
	</div>
	<div class="mailRight">
		<div id="mailRight" style="overflow: auto;height:100%;">
			<s:if test="sendEmailFlag == true">
				<%@include file="oa-email-input.jsp" %>
			</s:if>
		</div>
	</div>
</div>
<script language="javascript">
$(function(){
	var h = $(document).height();
	$("#mailBottom").height(h-28+"px");
	setNum2LeftBar();
	if("${oaEmailId}" != ""&&"${id}"!=""){
		hilightLeft(${boxId});
		readMail("${oaEmailId}","${id}","${boxId}",false);
	}else if("${sendEmailFlag}" =="true"){
		mailContentEditor = $('#mailContent').xheditor({
			tools:'full',forcePtag:false,showBlocktag:false,html5Upload:false,upMultiple:'1',
			upLinkUrl:"${ctx}/oa/oa-email!upload.action",upLinkExt:"zip,rar,txt",
			upImgUrl:"${ctx}/oa/oa-email!upload.action",upImgExt:"jpg,jpeg,gif,png",
			upFlashUrl:"${ctx}/oa/oa-email!upload.action",upFlashExt:"swf",
			upMediaUrl:"${ctx}/oa/oa-email!upload.action",upMediaExt:"avi"
			}
		);
		mailContentEditor.focus();
	}else{
		if("${newEmailFlag}" =="true"){
			newMail();
		}else{
			getMailList(1);
		}
	};
});
</script>
<security:authorize ifAnyGranted="A_EMAIL_USER_ADMIN">
	<script language="javascript">
		SELECTED_ALL_USER_PER = true;
	</script>
</security:authorize>
<div id="searchUserDiv" class="searchUserDiv"></div>
</body>

</html>
