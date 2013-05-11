<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.hhz.core.utils.PowerUtils"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.cache.PlasCache"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>宝龙管理平台</title>  
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/global.jsp" %>
<meta http-equiv="imagetoolbar" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link href="${ctx}/resources/css/desk2/pdhome.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/css/desk2/pdarea.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/desk2/tab.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/desk2/right.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/desk/left.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/dtree/dtree.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" id='skin' />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/desk/wab/visitingCard.css" />

<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/cookie.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.messager.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/desk2/main.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/desk2/tab.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt_source.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/desk2/left.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/desk2/right.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/desk2/wabTreePanel.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/desk2/visitingCard.js"></script>
<script type="text/javascript">
//密码过期
<s:if test="isModLastDateFlag == 1">
showModPasswordDlg();
</s:if>
//注册登录
<%PlasCache.addOnlineCount();%>

$(function(){
	var ggxw_interval;
	try{
		//默认初始化桌面标签
		TabUtils.initTab("homepage");
	}catch(e){}
	//初始化外网邮箱  main.js
	try{
		refreshMails('1');
	}catch(e){}
	
	//初始化待办事项  main.js
	try{
		refreshJbpm("","1");
	}catch(e){}

	
	//更新会议提醒
    try{
		refreshHomeAttention();
	}catch(e){}
	
	//公告  main.js
	try{
		refreshHomeNotifyList();
	}catch(e){}

	try{
		//设置首页每5分钟自动刷新一次
		setInterval(refreshHome, 10*60*1000);
	}catch(e){}

	try{
		//自动切换公告和新闻
		//ggxw_interval = setInterval(autoChangeGgxw, 10*1000);
		$("#pn_content").bind("mouseenter",function(){
			CAN_GGXW_CHANGE = false;
		});
		$("#pn_content").bind("mouseleave",function(){
			CAN_GGXW_CHANGE = true;
		});
	}catch(e){}
});
</script>
</head>
<body id="bodyLoad" onbeforeunload="runOnBeforeUnload();" onblur="closeBook();"  onfucosout="closeBook();"    >

<a name="top" id="top"></a>
<div id="div_home_b" class="div_home" style="min-width: 1008px;background-color: #e4e7ec">
	<c:set var="num" value="1"/>
    <!--左边框架-->
	<div class="div_left" id="div_left_b" style="width:88px;" >
		<div class="div_left_top">
			&nbsp;
		</div>
		
	<%--左边菜单 --%>
	<div id="divXialaMenu" >
	<%@include file="desk-new-left.jsp" %>	
	</div>	
	<div class="div_left_bottom" style="margin-top:1px;">&nbsp;</div>
		<div align="center" class="person_online" id="login_user_pic">
			&nbsp;
		</div>
		<div align="center" style="margin-top: 2px;" class="font_left_user">${userName}</div>
	<div class="font_left_config red_hover" onclick="logout(this);" style="margin-top:8px;">退出</div>
	</div>
	<%--通讯录左边显示框 start --%>
	<div id="addressBookList" oi="nohide" addressShow="false" style="z-index: 100;height:590px;" ><%-- 注意：这里的宽度通讯录与菜单不一致.--%>
		<%--显示 --%>
		<div id="child_book" class="div_address">
		<div style="margin-top:1px;margin-left:12px;">
			<div style="height:28px; line-height:24px;font-size:18px;margin-top: 8px;width:225px;">
				<div id="wab" onclick="resetWabTreeHeight();" title="查看通讯录">
					通讯录 
					<a  href="javascript:showAddressTree()" >回到架构表</a>
				</div>
			</div> 
			
			<!-- 搜索条件 -->
			<input  type="text" 
					id="searched_user" 
					value="" 
					class="search_input" 
					onclick="clearSearchInput(this);searchUserResult(this);"
					onkeyup ="searchUserResult(this);" 
					onblur="this.focus();"/>
					<!-- onblur="resetSearchInput(this,'wabTree','searchResult');" style="width:225px;"/> -->
			<div style="height:8px;">&nbsp;</div>
			
			<!-- 搜索列表 -->
			<div id="searchResult" class="search_user_result" ></div>
			
			<!-- 通讯录树-->
			<div id="wabTree" style="float:left;width:230px; height:501px; overflow-x:hidden;overflow-y:auto;font-size:12px;line-height:26px;" onclick="resetWabTreeHeight();">
			</div>
			
			<!-- 名片区域-->
			<div id="showContent" style="float:left;max-width:200px;padding-left:5px;">
			</div>
		</div>
		</div>
	</div>
	<div id="div_show_address" style="background-color:#fff;margin-left:249px;display:none;width:100px;z-index: 100;position:absolute;height:501px;">
		&nbsp;	
	</div>
	<%--通讯录左边显示框 end --%>
	<!--中间框架-->
	<div class="div_center" id="div_center_b">&nbsp;</div>
	<!--右边框架-->
	<div class="div_right" style="padding-top:8px;" id="divFrame" onclick="closeBook();">
  		<div id="divTab" class="div_right_title" style="margin-right:8px;">
			<div id="homepage_tab">
				<div type="pageDiv" class="div_right_tab_selected" onclick="TabUtils.showTab({data:{tabId:'homepage',src:''}});">桌面</div>
			</div>
		</div>	
		<div id="div_right_fixed" class="div_right_content" style="margin-top:10px;min-width: 1024px;">
			<!-- 固定区域 -->
			<div style="float:left;width:863px;height:522px;margin-right: 9px;margin-bottom:4px;">
				<div style="width:281px;float:left;margin-right: 10px;">
					<div style="border-bottom-width:1px;
								border-bottom-color:#CCCCCC;border-bottom-style:solid;">
						<div class="div_index" >
							${num }
							<c:set var="num" value="${num+1 }"/>
						</div>
						<div style="width:100%;margin-left:0px;padding-bottom: 0px;padding-top:0px;height:30px;" class="font_title_16">
						<div style="float:left;margin-right:8px; cursor:pointer;font-weight: bold;line-height:30px;height:30px;text-align:center;" id="email"  onclick="refreshMails('1');">邮件</div>
						<div id="attention">
						<div style="float:left;margin-right:5px;font-size:14px;margin-top:6px;" isline="true">|</div>
						<div id="attenDiv" style="float:left;margin-right:8px; cursor:pointer;font-weight: bold;line-height:30px;height:30px;text-align:center;">关注
						<span id="attentionCount"></span>
						</div>
						</div>
						<div id="emailPageTest" style="float:right;margin-right: 0px;">
						</div>
						</div>
					 </div>
					<div class="div_clear"></div>
					<div id="desk_new_email" style="width:100%;height:422px;margin-left:0px;">
					</div>
					<div class="div_clear"></div>
					<div id="desk_new_meeting" style="width:100%;margin-left:0px;background-color:#54b147;height:40px;border-bottom:0px;margin-top:4px;"></div>
				</div>
				<%--待办区域 --%>
				<div style="float:left;width:399px;margin-right:10px;" id="waitArea">
					 <div class="div_index">
						${num }
						<c:set var="num" value="${num+1 }"/>
					</div>
				 	<div id="desk-new-schedule" style="height:370px;width:100%;margin-left:0px;"></div>
				</div>
				<div style="float:left;width:163px;" id="main_right">
						<div>
							<div class="div_index" >
							${num }
							<c:set var="num" value="${num+1 }"/>
							</div>
							<div style="width:100%;padding-bottom: 0px;height:30px;border-bottom-width:1px;
							border-bottom-color:#CCCCCC;border-bottom-style:solid;" class="font_title_16">
			 					<div style="float:left;font-weight: bold;line-height:30px;height:30px;text-align:center;" >便签</div>
			 					<div class="div_clear"></div>
		 					</div>
	 					</div>
						<%@include file="desk-new-notepad.jsp" %>
				</div>
			</div>
			
			<%-- 浮动区域 --%>
			<div style="float:left;width:281px;height:261px;margin-bottom: 2px;margin-right: 10px;max-height: 262px;min-height: 224px;" id="pn_content">
				<div>
					<div class="div_index" >
						${num }
						<c:set var="num" value="${num+1 }"/>
					</div>
					<div style="width:100%;margin-left:0px;padding-bottom: 0px;border-bottom-width:1px;
						border-bottom-color:#CCCCCC;border-bottom-style:solid;height:30px;" class="font_title_16">
						<div style="float:left;margin-right:8px;font-weight: bold;line-height:30px;height:30px;text-align:center;" id="div_notice" class="notice_link_selected" onclick="refreshHomeNotifyList();" >公告</div>
						<div style="float:left;margin-right:8px;font-size:14px;margin-top:6px;" isline="true">|</div>
						<div style="float:left;font-weight: bold;line-height:30px;height:30px;text-align:center;" class="notice_link" id="div_news" onclick="refreshHomeNewsList();">新闻</div>
						<div class="div_clear"></div>
					</div>
				</div>
				<div class="div_clear"></div>
		        <div id="desk-new-news" style="max-height: 261px;min-height: 224px;"></div>
			</div>
			<%--常用区域 --%>
			<div style="float:left;width:281px;height:261px;margin-right: 10px;margin-bottom: 2px;" >
				<%@include file="desk-new-common.jsp" %>
			</div>
			<%--计划管理 --%>
			<div style="float:left;width:281px;height:261px;margin-right: 10px;margin-bottom: 2px;display:none;" isShow="107||" isParent="true">
				<%@include file="desk-new-plan.jsp" %>
			</div>
			<%--成本管理 --%>
			<div style="float:left;width:281px;height:261px;margin-right: 10px;margin-bottom: 2px;display:none;" isShow="115||" isParent="true">
				<%@include file="desk-new-cost.jsp" %>
			</div>
			<%--商业系统 --%>
			<div style="float:left;width:281px;height:261px;margin-right: 10px;margin-bottom: 2px;display:none;" isShow="102||" isParent="true">
				<%@include file="desk-new-business.jsp" %>
			</div>
			<%--其它业务 因为在x201分辨率问题边界正好多了一像素 --%>
			<div style="float:left;width:281px;height:261px;margin-right: 9px;margin-bottom: 2px;display:none;" isShow="112||" isParent="true">
				<%@include file="desk-new-other.jsp" %>
			</div>
		</div>
		
	  	
	</div>
</div>
<div id="noteClickMask" onclick="saveNote();" class="full_screen_mask"></div>
<div id="bookClickMask" class="full_screen_mask_book"></div>

<script type="text/javascript">
var speed = 500;   
var bookShowMgr;
var bookHideMgr;
var addressShowMgr;
var addressHideMrg;
var selectedObj;
var allMenu;
var n = 4;


$("#div_home_b").blur(function(){closeBook();});

$("#bookClickMask").click(function(){
	closeBook();
	$('#bookClickMask').hide();
});

//初始化
$(function(){
	if(navigator.userAgent.indexOf('iPad') > -1){
	}else if(navigator.userAgent.indexOf('8') > -1 && navigator.userAgent.indexOf('Trident/4.0') > -1){
		$("#div_home_b").find(".div_index").removeClass().addClass("div_index_ie8");
		$("#pn_content").find("div[isline]").css("margin-top","4");
	}
	try{
		//加载有权限使用菜单
		var data={roleCd:'<%=PowerUtils.array2String(SpringSecurityUtils.getCurrentRoleCds())%>' };
		$("#divXialaMenu").append('<div id="loading" style="padding-left:8px;"><image src="'+_ctx+'/images/loading.gif"></div>');
		$.post("${ctx}/app/app-authority!loadNewMenuByRoleCd.action",data, function(result) {
			$("#divXialaMenu").find("#loading").remove();
			$("#divXialaMenu").append(result);
			$("#divXialaMenu").find("div[type='moduleChild']").each(function(){
				if($(this).attr("id") != "child_book"){
					$("#addressBookList").append($(this));
				}
			});
		});
		//加载有权限所有menu
		$.post("${ctx}/app/app-authority!loadAllRoleModuleMenu.action",data, function(result) {
			allMenu = eval('('+ result +')');
			n++;
			$("#div_right_fixed").find("[isShow]").each(function(){
				var v = allMenu[$(this).attr("isShow")];
				$(this).find("[type='num']").each(function(){
					n++;
					$(this).html(n);
				});
				if(typeof v != 'undefined'){
					$(this).show();
					if($(this).parent().attr("isControl") == 'true'){
						v = v.split("||");
						$(this).parent().attr("title",v[2]);
						$(this).parent().toggleClass($(this).parent().attr("class").replace("_disabled",""));
						//加入点击事件
						$(this).parent().click(function(){
							window.scroll(0,0);
							if("1"!=$(this).attr("sameWinFlg")){
								TabUtils.newTab(v[0],v[2],"${ctx}"+v[1]);
							}else{
								window.open("${ctx}"+v[1]);
							}
						});
					}
				}else{
					//将没权限菜单名字灰掉
					if($(this).parent().width() == "91"){
						$(this).parent().removeClass("area_deep_blue_disabled").removeClass("area_light_blue_disabled").addClass("area_light_blue_other_noright");
					}else{
						$(this).parent().removeClass("area_deep_blue_disabled").removeClass("area_light_blue_disabled").addClass("area_light_blue_noright");
					}
					$(this).css("color","#666666");
					$(this).attr("title","["+$(this).text().trim()+"]您没有权限");
					//$(this).html($(this).text().trim());
					$(this).click(function(){
					});
					//如果一级菜单没权限整个隐藏
					if($(this).attr("isParent") == "true"){
						$(this).show();
					}else{
						$(this).show();
					}
				}
			});
			//加载常用模块
			$("#div_right_fixed").find("[isStatic]").each(function(){
				$(this).click(function(){
					window.scroll(0,0);
					if(typeof allMenu[$(this).attr("isStatic")] != 'undefined'){
						var tmp = allMenu[$(this).attr("isStatic")]
						tmp = tmp.split("||");
						TabUtils.newTab(tmp[0],tmp[2],"${ctx}"+tmp[1]);
					}
					
				});
			});
			//遍历判断各区块的子区块，将区块下没有权限的父区块隐藏。（5月13日draco要求取消）
			/*
			$("#div_right_fixed").find("[isParent='true'][display!='none']").each(function(){
				var success = true;
				$(this).find("[isShow]").each(function(){
					var v = allMenu[$(this).attr("isShow")]
					if(typeof v != 'undefined'){
						success = false;
						return true;
					}				
					});
				if(success){
					$(this).hide();
				}
			});
			*/
			refreshWin();

			//加载个人照片
			loadUserPic();
		});
	}catch(e){}
	
	refreshWin();
});


//加载个人照片
function loadUserPic(){
	var url = _ctx + '/desk/desk-wab!loadUserPic.action';
	$.post(url, {}, function(path){
		if('' != path){
			$('#login_user_pic').html('<img style="width:60px; padding-top:5px; padding-bottom:5px;" src="'+path+'"/>');
		}else{
			$('#login_user_pic').html('<img style="width:60px; padding-top:5px; padding-bottom:5px;" src="'+_ctx+'/resources/images/desk2/div_left_03.gif"  />' )
		}
	});
}

//
function openMenu(){
	var url = '${ctx}/app/app-authority!loadMenuByNewModule.action?moduleCd=105';
	$.post(url,{},function(){
		doClickBook(this,url);
		
	});
}
function refreshWin(){
	//自适应高度
	$("#div_left_b,#div_center_b").css("height",$(document).height());
	//$("#addressBookList").css("height",$(document).height());
	//$("#div_show_address").css("height",$(document).height());
	$("#divFrame").css("height",$(document).height()-51);
	
	/*
	$(window).resize(function() {
		$(".div_left,div_center").css("height",$(document).height());
		//$("#addressBookList").css("height",$(document).height());
		$("#divFrame").css("height",$(document).height()-51);
		$("iframe").each(function(){
			$(this).css("width",$(".div_right_title").width());
		});
	});
	*/
	$("#float_news").css("width","88px");
	$("#newsMarquee").css("width","88px");
}
//显示通讯录
function showAddressTree(){
	$('#wabTree').show();
	$('#searchResult').hide();
}
//绑定
/*
$("#addressBookList").bind("mouseover",function(){
	//清掉隐藏事件
	if(addressHideMrg){
		clearTimeout(addressHideMrg);
	}
	if(bookHideMgr){
		clearTimeout(bookHideMgr);
	}
	selectedObj.css("background-color","#1BA0E1");
	selectedObj.css("color","#FFFFFF");
	$(this).mouseleave(function(){
		if(addressHideMrg){
			clearTimeout(addressHideMrg);
		}
		addressHideMrg = setTimeout(function(){
			$("#addressBookList").hide();
			},300);
		selectedObj.css("background-color","");
		});
	$(this).mouseout(function(){
	});
});*/
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
</body>
</html>