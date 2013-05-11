<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
		parent.myFrame.cols="0,7,*";
	},10000);
	
	$(document).hover(function(){
		if(t)clearTimeout(t);
	},function(){
		t = setTimeout(function(){
			parent.myFrame.cols="0,7,*";
		},5000);
	});
})

</script>
</head>
<body >
<div class="left_content">
     <div class="user_info">欢迎您，<strong>pobear</strong><br />[<a href="#">系统管理员</a>，<a href="${ctx}/j_spring_security_logout">退出</a>]</div>

	<div class="main_nav">
		<div class="left_main_nav" id="left_main_nav">
			<ul>
				<li class="left_back" id="nav_0">系统设置</li>
				<li class="left_back" id="nav_1">管理层</li>
				<li class="left_back" id="nav_2">人力资源</li>
				<li class="left_back" id="nav_3">OA</li>
					
				<%--
				<s:iterator value="appModuleList" status="status">
				<li class="left_back" id="nav_${appModuleId}">${moduleName}</li>
				</s:iterator>
			  	--%>	
			</ul>
		</div>
	<div class="right_main_nav" id="right_main_nav">
		<div id="sub_0" style="display: none;">
			<div onclick="" id="sub_sort_0" class="list_tilte"><span>系统设置</span></div>
			<div style="display: block;" id="sub_detail_2" class="list_detail">
				<ul>
					<li onclick="" id="item_0_0" linkSrc="list_demo.html"><a href="#">权限设置</a></li>
					<li onclick="" id="item_0_1" linkSrc="${ctx}/app/app-dict-type.action"><a href="#">数据字典</a></li>
					<li onclick="" id="item_0_2" linkSrc="${ctx}/app/app-module.action"><a href="#">模块管理</a></li>
					<li onclick="" id="item_0_3" linkSrc="${ctx}/app/app-module-menu-rel.action"><a href="#">模块菜单管理</a></li>
					<li onclick="" id="item_0_4" linkSrc="${ctx}/app/app-page.action"><a href="#">页面管理</a></li>
					<li onclick="" id="item_0_5" linkSrc="${ctx}/app/app-page-function.action"><a href="#">页面功能管理</a></li>
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
			<div onclick="" id="sub_sort_2" class="list_tilte"><span>OA</span></div>
			<div style="display: block;" id="sub_detail_2" class="list_detail">
				<ul>
					<li id="item_3_1" linkSrc="${ctx}/oa/oa-email!unRead.action"><a href="#">内部邮件</a></li>
					<li id="item_3_2" linkSrc="${ctx}/oa/oa-news.action"><a href="#">新闻中心</a></li>
				</ul>
			</div> 
		</div>
		
		<%-- 遍历模块 ->遍历菜单

		<s:iterator value="appModuleList">
			<div onclick="" id="sub_sort_${appModuleId}" class="list_tilte"><span>${moduleName}</span></div>
			<div style="display: block;" id="sub_detail_2" class="list_detail">
				<ul>
					<s:iterator value="appModuleMenuRelList">
					<s:if test="appModuleId == voAppModuleId ">
					<li id="item_${voAppModuleId}_${voAppMenuId}" linkSrc="${ctx}${voPagePath}" title=""><a href="#">${voAppMenuName}</a></li>
					</s:if>
					</s:iterator>
				</ul>
			</div> 
		</s:iterator>
		 --%> 
		
</div>
</div>
</div>
</body>
</html>
