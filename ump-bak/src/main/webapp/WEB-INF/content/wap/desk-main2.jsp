<%@page import="com.hhz.ump.cache.PlasCache"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.core.utils.PowerUtils"%>
<%@page import="com.hhz.ump.util.Util"%>
<%@page import="com.hhz.ump.cache.PlasCache"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<title>宝龙管理平台</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/global.jsp" %>
<meta http-equiv="imagetoolbar" content="no" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" id='skin' />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/wap/main.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/wap/header.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/wap/tab.css" />
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/wap/main.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/wap/tab.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js" ></script>

<script language="javascript">
//注册登录
<%PlasCache.addOnlineCount();%>

var _bodyHeight = 0;
var daiban_attention_height;	//待办和关注的剩余空间,首页载入后计算,关注事项forHome的页面里用到
$(function(){
	//自动调整首页高度
	_bodyHeight = $(document).height();
	$(".body_center").css("height",_bodyHeight-628+538+"px");
	$(".main_left").css("height",_bodyHeight-628+538+"px");
	$(".main_right").css("height",_bodyHeight-628+515+"px");
	$(".center_daiban").css("height",_bodyHeight-628+280+"px");
	$("#left_address_div").css("height",_bodyHeight-628+436+"px");
	daiban_attention_height = _bodyHeight-628+515-255-5;
	
	var height_center_all = _bodyHeight-628+508;
	$(".main_center").css("height",height_center_all+"px");
	$("#desk_front_email_div").css("height",Number(height_center_all*5/11)+"px");
	$("#desk_front_jbpm_div").css("height",Number(height_center_all*6/11-1)+"px");
	HOME_EMAIL_PAGESIZE = parseInt((height_center_all*5/11-54)/25);
	HOME_JBPM_PAGESIZE = parseInt((height_center_all*6/11-28)/25);
	
	try{
		TabUtils.initTab("homepage");
	}catch(e){}
	try{
		var xiala_width=Number(Number(document.body.clientWidth)-211-113-4);
		$("#divXialaMenu").css("width",xiala_width+"px");
		var data={roleCd:'<%=PowerUtils.array2String(SpringSecurityUtils.getCurrentRoleCds())%>' };
		$.post("${ctx}/app/app-authority!loadMenuByRoleCd.action",data, function(result) {
			$("#divXialaMenu").html(result);
		});
	}catch(e){}
	try{
		refreshMails();
	}catch(e){}
	try{
		refreshJbpm();
	}catch(e){}
	try{
		setInterval(refreshHome, 5*60*1000);
	}catch(e){}
	//加载机构树
	//loadWabTree();
});

</script>
</head>
<body onbeforeunload="runOnBeforeUnload()">
<div style="position:absolute; width:100%; top:1px; left:211px; overflow:hidden;" id="divXialaMenu"></div>
<div class="body_header">
	<div class="logo">
		<span style="color:#00437c;">宝龙管理平台&nbsp;</span>
	</div>
	<div class="user_right_title">
		<div style="line-height:16px;">
			<button class="btn_exit_home" style="margin-left:3px;" onclick="logout(this);">退出</button>
		</div>
		<div>
			<span id="title_user_dept" title="${positionName}">${userName} &nbsp; ${deptName}</span>
		</div>
		<div>
			<span id="title_positionName" title="${positionName}">${positionName}</span>
		</div>
	</div>
</div>
<div class="body_center">
	<div style="margin-left:5px;">
		<div class="main_tab"><%@ include file="desk-main-tab.jsp"%></div>
		<div>
			<div class="main_center" id="divFrame">
				<div id="homepage_frame">
					<div class="center_mail" id="desk_front_email_div"></div>
					<div style="height:1px; width:100%; background-color:#8c8f94;"></div>
					<div class="center_daiban" id="desk_front_jbpm_div"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="body_footer">
	<div style="float:left; margin-left:10px;">
		<img src="${ctx}/resources/images/desk/bottom_logo_PD.jpg"/>
	</div>
	<div style="float:left; margin-left:10px;">
		<img src="${ctx}/resources/images/desk/bottom_cutline.jpg"/>
	</div>
	<div style="float:left; line-height:22px; margin-left:10px;">
		在线人数/总人数：
		<span style="line-height: 14px;height: 14px;" id="count_online">${onlineCount}</span>
		/<span style="line-height: 14px;height: 14px;" id="count_all">${totalCount}</span>
	</div>
	<div style="float:right; line-height:22px;padding-right:5px;" id="lastLoginInfoId">
	</div>
	<div style="text-align: center;">
		<img src="${ctx}/resources/images/desk/bottom_honest.jpg"/>
	</div>
</div>
<div id="noteClickMask" class="full_screen_mask"></div>

<script language="javascript">
	//main.js
	resizeRightTitle('title_user_dept','title_positionName');
</script>
</body>
</html>