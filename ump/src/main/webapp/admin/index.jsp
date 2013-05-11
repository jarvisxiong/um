<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<title>Power Desk</title>
	<%@ include file="/common/global.jsp" %>
	<link href="${ctx}/css/admin.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/resources/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/resources/js/jquery-easyui/themes/icon.css" rel="stylesheet" type="text/css" />
	<script src="${ctx}/resources/js/jquery/jquery-1.4.4.min.js" type="text/javascript" language="javascript" ></script>
	<script src="${ctx}/resources/js/jquery-easyui/jquery.easyui.min.js"  type="text/javascript" ></script>
	<script src="${ctx}/resources/js/common/index.js"  type="text/javascript" ></script>
	<script src="${ctx}/resources/js/common/ConvertUtil.js"  type="text/javascript" ></script>
	<script src="${ctx}/resources/js/common/loader.js"  type="text/javascript" ></script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no" >
	<noscript>
        <div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
            <img src="${ctx}/resources/images/admin/noscript.gif" alt='抱歉，请开启脚本支持！' />
        </div>
    </noscript>
	
	<div region="north" split="false" border="false">
        <div class="top">
            <span style="float:right; padding-right:20px;" class="head">
            	当前用户：
				<b><%=SpringSecurityUtils.getCurrentUserName()%></b>
				<b><%=SpringSecurityUtils.getCurrentDeptName()%></b>
				| <a  href="javascript:logout();">退出登录</a> ]
            </span>
            <span style="padding-left:10px; font-size: 16px; ">
            	<img src="${ctx}/resources/images/admin/pd_logo.png" width="25" height="25" align="absmiddle" />&nbsp;工作计划管理系统平台
            </span>
        </div>
	</div>
	<div region="south" style="height:30px;padding:5px;background:#efefef;">
		<div align="center" style="font-weight: bold;color:#1B5978">诚信  恭谦 创新 敬业 - 宝龙集团</div>
	</div>
	<div region="west" split="true" title="导航菜单" style="width:180px;">
		<div class="easyui-accordion" fit="true"  border="false">
			<div title="系统设置 " iconCls="icon-dict" >
				<ul> 
					<li>
						<div>
							<a href="javascript:addTab('角色权限管理','${ctx}/app/app-authority.action')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav"><font>角色权限管理</font></span>
							</a>
						</div>
					</li>
					<li>
						<div>
							<a href="javascript:addTab('模块管理','${ctx}/app/app-module.action')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav"><font>模块管理</font></span>
							</a>
						</div>
					</li>
					<li>
						<div>
							<a href="javascript:addTab('菜单管理','${ctx}/app/app-module-menu-rel.action')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">菜单管理</span>
							</a>
						</div>
					</li>
					<li>
						<div>
							<a href="javascript:addTab('页面管理','${ctx}/app/app-page.action')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">页面管理</span>
							</a>
						</div>
					</li>
					<li>
						<div>
							<a href="javascript:addTab('功能管理','${ctx}/app/app-page-function.action')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">功能管理</span>
							</a>
						</div>
					</li>
					<li>
						<div>
							<a href="javascript:addTab('字典管理','${ctx}/app/app-dict-type.action')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">字典管理</span>
							</a>
						</div>
					</li>
					<li>
						<div>
							<a href="javascript:addTab('参数管理','${ctx}/app/app-param.action')"> <span class="icon icon-dict">&nbsp;</span> <span class="nav">参数管理</span> </a>
						</div>
					</li>
					<li>
						<div>
							<a href="javascript:addTab('修改密码','${ctx}/plaspd/plas-user-change!password.action')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">修改密码</span>
							</a>
						</div>
					</li>
					<li>
						<div>
							<a href="javascript:addTab('修改资料','${ctx}/plaspd/plas-user-change!info.action')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">修改资料</span>
							</a>
						</div>
					</li>
					<li>
						<div>
							<a href="javascript:addTab('定时器/缓存管理','${ctx}/plaspd/plas-quartz!main.action')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">定时器/缓存管理</span>
							</a>
						</div>
					</li>
					<li>
						<div>
							<a href="javascript:addTab('邮箱系统','mail.powerlong.com')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">邮箱系统</span>
							</a>
						</div>
					</li>
					
					<li>
						<div>
							<a href="javascript:addTab('假期管理','${ctx}/app/app-vocation.action')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">假期管理</span>
							</a>
						</div>
					</li>
				</ul>
			</div>
			
			<div title="商业系统 " iconCls="icon-dict" selected="true">
				<ul>
					
					<li>
						<div>
							<a href="javascript:addTab('授权管理','${ctx}/res/res-accredit.action')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">授权管理</span>
							</a>
						</div>
					</li>
					<li>
						<div>
							<a href="javascript:addTab('商铺坐标','${ctx}/bis/bis-store-coords.action')">
								<span class="icon icon-dict">&nbsp;</span>
								<span class="nav">商铺坐标</span>
							</a>
						</div>
					</li>
				</ul>
			</div>
			
			<c:forEach items="${mapModule}" var="id">
				<c:if test="${fn:length(id.value) > 0}">
				<div title="${id.key}" iconCls="icon-dict" style="overflow:auto;">
					<ul>
						<c:forEach items="${id.value}" var="menu">
							<li>
								<div>
								<a href="javascript:addTab('${menu.menuName}','${ctx}${menu.linkUrl }','${menu.iconName }')">
									<span class="icon-save">&nbsp;</span>
									<span class="nav">${menu.menuName}</span>
								</a>
								</div>
							</li>
						</c:forEach>
						
					</ul>
				</div>
				
				</c:if>
			</c:forEach>
		</div>
	</div>
	<div region="center" style="overflow:hidden;">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页" style="padding:10px 20px;overflow: auto;+position: relative;"> 
					<div style="margin:0 5px 10px 5px;font-size:20px;color:#15428B;font-weight: bold;">欢迎使用工作计划管理系统平台</div>
					<div style="width:48%;float:left;height:200px;">
						<div title="快捷操作"  class="easyui-panel" collapsible="true" style="padding:20px 10px 10px 10px;height:200px;overflow:hidden; ">

						</div>
						<div style="height:20px;"></div>
						<div title="OOOO"  class="easyui-panel" collapsible="true" style="padding:20px 10px 10px 10px;height:200px;overflow:hidden; ">
							<span style="font-weight: bold;color:#416AA3;">待添加</span>
						</div>
					</div>
					<div style="width:48%;float:right;height:200px;">
						
						<div title="天气预报"  class="easyui-panel" collapsible="true" style="padding:20px 10px 10px 10px;height:200px;overflow:hidden; ">
							<!--<iframe src="http://www.thinkpage.cn/weather/weather.aspx?uid=&c=CHXX0116&l=zh-CHS&p=CMA&a=1&u=C&s=3&m=1&x=1&d=3&fc=&bgc=&bc=&ti=1&in=1&li=2&ct=iframe" frameborder="0" scrolling="no" width="400" height="150" allowTransparency="true"></iframe>
						--></div>
						<div style="height:20px;"></div>
						<div title="OOOO"  class="easyui-panel" collapsible="true" style="padding:20px 10px 10px 10px;height:200px;overflow:hidden; ">
							<span style="font-weight: bold;color:#416AA3;">待添加</span>
						</div>
					</div>
			</div>
		</div>
	</div>
	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">关闭全部</div>
		<div id="mm-tabcloseother">关闭其他</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">关闭后面</div>
		<div id="mm-tabcloseleft">关闭前面</div>
	</div>

	
</body>
</html>
