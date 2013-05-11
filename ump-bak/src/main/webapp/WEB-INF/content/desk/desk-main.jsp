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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>宝龙管理平台 </title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/global.jsp" %>
<meta http-equiv="imagetoolbar" content="no" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/desk/main.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/desk/header.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/desk/left.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/desk/tab.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/desk/right.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/dtree/dtree.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" id='skin' />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/desk/wab/visitingCard.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/desk/wab/wab.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/desk/wab/wabTreePanel.css" />
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/cookie.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.messager.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/desk/main.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/desk/left.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/desk/tab.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/desk/right.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt_source.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/desk/wab/visitingCard.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/desk/wab/wabTreePanel.js"></script>

<script language="javascript">
//密码过期
<s:if test="isModLastDateFlag == 1">
showModPasswordDlg();
</s:if>
//注册登录
<%PlasCache.addOnlineCount();%>

var _bodyHeight = 0;
var ggxw_interval;
var daiban_attention_height;	//待办和关注的剩余空间,首页载入后计算,关注事项forHome的页面里用到
$(function(){
	//自动调整首页高度
	_bodyHeight = $(document).height();
	$(".body_center").css("height",_bodyHeight-628+538+"px");
	$(".main_left").css("height",_bodyHeight-628+538+"px");
	$(".main_right").css("height",_bodyHeight-628+515+"px");
	$(".center_daiban").css("height",_bodyHeight-628+280+"px");
	$("#left_address_div").css("height",_bodyHeight-628+416+"px");
	daiban_attention_height = _bodyHeight-628+515-255-5;
	
	var height_center_all = _bodyHeight-628+508;
	$(".main_center").css("height",height_center_all+"px");
	$("#mailBodyPanel").css("height",Number(height_center_all*5/11)+"px");
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
		refreshHomeNotifyList();
	}catch(e){}
	try{
		refreshHomeNewsList();
	}catch(e){}
	try{
		setInterval(refreshHome, 5*60*1000);
	}catch(e){}
	try{
		//refreshUpdateAlert();
		//setInterval(refreshUpdateAlert, 5*60*1000);
	}catch(e){}
	try{
		ggxw_interval = setInterval(autoChangeGgxw, 10*1000);
		$("#pn_content").bind("mouseenter",function(){
			CAN_GGXW_CHANGE = false;
		});
		$("#pn_content").bind("mouseleave",function(){
			CAN_GGXW_CHANGE = true;
		});
	}catch(e){}
	
	//问卷调查
	//ask();
	
	//定位通知栏
	//initBroad('broad_content');
	//注册方向标事件
	initDirectWab();
	//加载机构树
	loadWabTree();
	showLoginMsg('${userName}','${lastLoginIp}','${lastLoginTime}');
	
});

//调查
function ask(){
	var isAsked=getCookieValue("isAsked_YGSC");
	if (isAsked!='1'){
	ymPrompt.confirmInfo({message:'《员工手册》意见调查表',title:'问卷调查',okTxt:"参与调查",cancelTxt:"以后再说",handler:function (tp){
		if (tp=='ok'){
			//注销webim
			window.open('http://www.askform.cn/77233-160967.aspx');
			setCookie("isAsked_YGSC","1",24,"/");
			//window.location.href='${ctx}/ask.html';
		}else{
			//doj.src='${ctx}/images/desk2/top_quit_normal.jpg';
		}
	}});
	}
}

//检查过期密码
function showModPasswordDlg(){
	ymPrompt.win( {
		message : "${ctx}/plaspd/plas-user-change!password.action?pwdExpiredFlag=1",
		width : 450,
		height : 240,
		dragOut: false,
		title : '密码修改提示',
		iframe : true,
		afterShow : function(){},
		handler : handlerCall
	});
} 
function handlerCall(){

	var url = '${ctx}/desk/desk!isPwdExpired.action';
	$.post(url,{},function(result){
		if('1' == result){
			showModPasswordDlg();
		}
	});
}

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
			<button class="btn_help_home" onclick="openHelp();" style="display:none;">帮助中心</button><button class="btn_exit_home" style="margin-left:3px;" onclick="logout(this);">退出</button>
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
	<!-- 左边 -->
	<div class="main_left"><%@ include file="desk-main-left.jsp"%></div>
	
	<div style="margin-left:212px;">
		<!-- Tab -->
		<div class="main_tab"><%@ include file="desk-main-tab.jsp"%></div>
		<div>
			<!-- 右边 -->
			<div class="main_right" id="main_right"><%@ include file="desk-main-right.jsp"%></div>
			<!-- 中间 -->
			<div class="main_center" id="divFrame">
				<div id="homepage_frame">
					<!-- 邮件列表/提醒列表 -->
					<div id="mailBodyPanel" style="background-color: #FFFFFF;width:100%;overflow-y: hidden;">
						<div id="desk_front_emailOut_div" style="width:100%;"></div>
						<div id="desk_front_email_div" style="width:100%;display:none;"></div>
					</div>
					<!-- 分割线 -->
					<div style="height:1px; width:100%; background-color:#8c8f94;"></div>
					<!-- 待办事宜 -->
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