<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<title>左侧导航栏</title>
<script  type="text/javascript">
$(function(){
	$("#left_main_nav li").mouseover(function(){
		$(this).siblings().removeClass("left_back_onclick");
		$(this).addClass("left_back_onclick");
		var sub_id = "sub_"+this.id.substring(4);
		var subDom = $("#"+sub_id);
		subDom.show().siblings().hide();
	});
	$("#right_main_nav li").click(function(){
		window.top.frames['mainFrame'].createOrShowTab(this.id,$(this).text(),$(this).attr("linkSrc"),true);
	});
	$("#left_main_nav li:first").trigger("mouseover");
	
	var t = setTimeout(function(){
		parent.myFrame.colsRut="0,7,*";
	},10000);
	
	$(document).hover(function(){
		if(t)clearTimeout(t);
	},function(){
		t = setTimeout(function(){
			parent.myFrame.cols="0,7,*";
		},50000);
	});
})

</script>
</head>
<body >
<div class="left_content">
     <div class="user_info">欢迎您，<strong><%=SpringSecurityUtils.getCurrentUserName()%></strong><br />[<a href="#">系统管理员</a>，<a href="${ctx}/j_spring_security_logout" target="_parent" >退出</a>]</div>

	<div class="main_nav">
		<div class="left_main_nav" id="left_main_nav">
			<ul>
				<li class="left_back" id="nav_0">系统设置</li>
				<li class="left_back" id="nav_1">管理层</li>
				<li class="left_back" id="nav_2">人力资源</li>
				<li class="left_back" id="nav_3">办公系统</li>
				<li class="left_back" id="nav_10">组织机构</li>
			</ul>
		</div>
	<div class="right_main_nav" id="right_main_nav">
		<div id="sub_0" style="display: none;">
			<div onclick="" id="sub_sort_0" class="list_tilte"><span>系统设置</span></div>
			<div style="display: block;" id="sub_detail_2" class="list_detail">
				<ul>
					<li onclick="" id="item_1_5" linkSrc="${ctx}/app/app-authority.action"><a href="#">角色权限设置</a></li>
					<li onclick="" id="item_1_7" linkSrc="${ctx}/app/app-module.action"><a href="#">模块设置</a></li>
					<li onclick="" id="item_1_8" linkSrc="${ctx}/app/app-module-menu-rel.action"><a href="#">菜单设置</a></li>
					<li onclick="" id="item_1_9" linkSrc="${ctx}/app/app-page.action"><a href="#">页面设置</a></li>
					<li onclick="" id="item_1_10" linkSrc="${ctx}/app/app-page-function.action"><a href="#">功能设置</a></li>
					<li onclick="" id="item_1_12" linkSrc="${ctx}/app/app-dict-type.action"><a href="#">字典设置</a></li>
					<li onclick="" id="item_1_11" linkSrc="${ctx}/app/app-param.action"><a href="#">参数设置</a></li>
					<li onclick="" id="item_1_13" linkSrc="${ctx}/plaspd/plas-user-change!password.action"><a href="#">修改密码</a></li>
					<li onclick="" id="item_1_13" linkSrc="${ctx}/plaspd/plas-user-change!info.action"><a href="#">修改资料</a></li>
					<li onclick="" id="item_1_14" linkSrc="${ctx}/plaspd/plas-quartz!main.action"><a href="#">定时器/缓存管理</a></li>
					<li onclick="" id="item_1_15" linkSrc="${ctx}/sso/exchange/login.jsp"><a href="#" >邮件服务器(内)</a></li>
					<li onclick="" id="item_1_16"><a href="${ctx}/sso/exchange/login.jsp" target="_blank" >邮件服务器(外)</a></li>
				</ul>
			</div>
		</div>
		<div id="sub_1" style="display: none;">
			<div onclick="" id="sub_sort_1" class="list_tilte"><span>管理层</span></div>
			<div style="display: block;" id="sub_detail_1" class="list_detail">
				<ul>
					<li onclick="" id="item_1_0" linkSrc="${ctx}/dly/dly-schedule-task.action"><a href="#">各中心任务</a></li>
					<li onclick="" id="item_1_1" linkSrc="a.html"><a href="#">**任务</a></li>
				</ul>
			</div>
		</div>
		<div id="sub_2" style="display: none;">
			<div onclick="" id="sub_sort_2" class="list_tilte"><span>人力资源</span></div>
			<div style="display: block;" id="sub_detail_2" class="list_detail">
				<ul>
					<li onclick="" id="item_2_0" linkSrc="${ctx}/hr/hr-interview.action"><a href="#">招聘管理</a></li>
					<li onclick="" id="item_2_1" linkSrc="a.html"><a href="#">人才管理</a></li>
				</ul>
			</div> 
		</div>
		<div id="sub_3" style="display: none;">
			<div onclick="" id="sub_sort_2" class="list_tilte"><span>办公系统</span></div>
			<div style="display: block;" id="sub_detail_2" class="list_detail">
				<ul>
					<li id="item_3_1" linkSrc="${ctx}/oa/oa-email!unRead.action"><a href="#">内部邮件</a></li>
					<li id="item_3_2" linkSrc="${ctx}/oa/oa-news.action"><a href="#">新闻管理</a></li>
					<li id="item_3_3" linkSrc="${ctx}/oa/oa-notify.action"><a href="#">公告管理</a></li>
					<li id="item_3_4" linkSrc="${ctx}/jbpm/jbpm-travel-flow.action"><a href="#">出差审批</a></li>
					<li id="item_3_5" linkSrc="${ctx}/jbpm/jbpm-reimburse-flow.action"><a href="#">报销审批</a></li>
					<li id="item_3_6" linkSrc="${ctx}/res/res-node-deploy.action"><a href="#">审批节点配置</a></li>
					<li id="item_3_7" linkSrc="${ctx}/res/res-bill-templet.action"><a href="#">审批模板配置</a></li>
					<li id="item_3_8" linkSrc="${ctx}/res/res-module.action"><a href="#">权责信息配置</a></li>
					<li id="item_3_9" linkSrc="${ctx}/res/res-accredit.action"><a href="#">授权管理</a></li>
					<li id="item_3_10" linkSrc="${ctx}/res/res-approve-info.action"><a href="#">权责审批</a></li>
				</ul>
			</div> 
		</div>
		<div id="sub_10">
		<div onclick="" id="sub_sort_10" class="list_tilte"><span>组织机构</span></div>
			<div style="display: block;" id="sub_detail_10" class="list_detail">
				<ul> 
					<li onclick="" id="item_10_11" linkSrc="${ctx}/plaspd/plas-org.action"><a href="#">机构管理</a></li>
					<li onclick="" id="item_10_12" linkSrc="${ctx}/plaspd/plas-user.action"><a href="#">用户管理</a></li> 
				</ul>
			</div>
		</div>
</div>
</div>
</div>
</body>
</html>
