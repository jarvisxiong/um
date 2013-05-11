﻿﻿<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<title>宝龙信息系统认证授权平台</title>
	<%@ include file="/common/global.jsp" %>
	<link href="${ctx}/css/default.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/js/jquery-easyui/themes/icon.css" rel="stylesheet" type="text/css" />
	<script src="${ctx}/js/jquery/jquery.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/biz/index.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/common/ConvertUtil.js"  type="text/javascript" ></script>
	<script src="${ctx}/js/common/loader.js"  type="text/javascript" ></script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no" >
	<noscript>
        <div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
            <img src="${ctx}/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
        </div>
    </noscript>
	
	<div region="north" split="false" border="false">
        <div class="top">
            <span style="float:right; padding-right:20px;" class="head">
            	当前用户：
				<b><%=SpringSecurityUtils.getCurUserName()%></b>
				<b><%=SpringSecurityUtils.getCurDeptName()%></b>
				&nbsp;
				[ <a href="javascript: changePwd();">修改密码</a> 
				| <a href="javascript: editMyInfo();">我的资料</a> 
				| <a  href="javascript:logout();">退出登录</a> ]
				<security:authorize ifAnyGranted="A_ADMIN">
				<a target="_blank" style="color:red;font-weight: bold;" href="${ctx}/html/index.html">DEMO</a> 
				</security:authorize>
            </span>
            <span style="padding-left:10px; font-size: 16px; ">
            	<img src="${ctx}/images/pd_logo.png" width="76" height="25" align="absmiddle" />&nbsp;宝龙信息系统认证授权平台
            </span>
        </div>
	</div>
	<div region="south" style="height:30px;padding:5px;background:#efefef;">
		<div align="center" style="font-weight: bold;color:#1B5978">诚信  恭谦 创新 敬业 - 宝龙集团</div>
	</div>
	<div region="west" split="true" title="导航菜单" style="width:250px;">
		<div class="easyui-accordion" fit="true"  border="false">
			
		
			<c:forEach items="${mapModule}" var="id">
				<c:if test="${fn:length(id.value) > 0}">
				<div title="${id.key}" iconCls="icon-dict" style="overflow:auto;">
					<ul>
						<c:forEach items="${id.value}" var="menu">
							<li>
								<div>
								<a href="javascript:addTab('${menu.menuName}','${ctx}${menu.appPage.pagePath}')">
									<span class="icon icon-dict">&nbsp;</span>
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
					<div style="margin:0 5px 10px 5px;font-size:20px;color:#15428B;font-weight: bold;">欢迎使用宝龙信息系统认证授权平台</div>
					<div style="width:48%;float:left;height:200px;">
						<div title="快捷操作"  class="easyui-panel" collapsible="true" style="padding:20px 10px 10px 10px;height:200px;overflow:hidden; ">
							<security:authorize ifAnyGranted="A_ADMIN">
								<a class="easyui-linkbutton" href="javascript:addTab('全局参数','${ctx}/app/app-param.action','icon-edit')">全局参数</a>
								<a class="easyui-linkbutton" href="javascript:addTab('数据字典','${ctx}/app/app-dict-type.action','icon-edit')">数据字典</a>
								<a class="easyui-linkbutton"  href="#" onclick="refreshCache();">清除缓存</a>
								<a class="easyui-linkbutton" href="javascript:addTab('职员管理','${ctx}/plas/plas-user.action','icon-edit')">人员管理</a>
							</security:authorize>
						</div>
						<div style="height:20px;"></div>
						<div title="新增职员、开通账号 操作步骤如下"  class="easyui-panel" collapsible="true" style="padding:20px 10px 10px 10px;height:200px;overflow:hidden; ">
							<span style="font-weight: bold;color:#416AA3;">
								1、在“职员管理”中选择中心/部门，点击快速查询旁的“新增”按钮，输入员工信息后保存；<br/>
								2、在“职员管理”的快速查询框中，输入已保存的员工姓名，例：张三<br/>
								3、选择后点击“开通账号”，输入账号，例：zhangsan<br/>
								4、填写生效日期，例：2011-06-01<br/>
							</span>
						</div>
					</div>
					<div style="width:48%;float:right;height:200px;">
						<div title="天气预报"  class="easyui-panel" collapsible="true" style="padding:20px 10px 10px 10px;height:200px;overflow:hidden; ">
						<%--
							<iframe src="http://www.thinkpage.cn/weather/weather.aspx?uid=&c=CHXX0116&l=zh-CHS&p=CMA&a=1&u=C&s=3&m=1&x=1&d=3&fc=&bgc=&bc=&ti=1&in=1&li=2&ct=iframe" frameborder="0" scrolling="no" width="400" height="150" allowTransparency="true"></iframe>
						 --%>
						</div>
						
						<div style="height:20px;"></div>
						<div title="公告"  class="easyui-panel" collapsible="true" style="padding:20px 10px 10px 10px;height:200px;overflow:hidden; ">
							<span style="font-weight: bold;color:#416AA3;">
								《宝龙信息系统认证授权平台》入口地址：<br/><br/>
								&nbsp;&nbsp;<a style="color:#416AA3;" href="javascript:window.open('http://plas.powerlong.com/login.action')">http://plas.powerlong.com/login.action</a>
							</span>
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

	<div id="changePwdDiv" title="密码修改" style="width:300px;">
		<form id="changePwdForm" method="post">
			<input type="hidden" name="acctId"  value="<%=SpringSecurityUtils.getCurPlasAcctId()%>"/>
			<table style="width:100%;">
				<tr>
					<td width="60">原密码</td>
					<td><input type="password" id="oldPwd" class="easyui-validatebox"  required="true" name="oldPwd" onchange="$('#oldPwdWrong').hide();"/>
						<span style="color:red;display: none;" id="oldPwdWrong">原密码错误</span>
					</td>
				</tr>
				<tr>
					<td>新密码</td>
					<td><input type="password" id="newPwd" class="easyui-validatebox"  required="true" name="newPwd"/></td>
				</tr>
				<tr>
					<td>再输一次</td>
					<td><input type="password" class="easyui-validatebox" validType="equalTo['newPwd']"  required="true" id="newPwdRepeat"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
