﻿<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
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
				<%--
				<security:authorize ifAnyGranted="A_ADMIN">
				<a target="_blank" style="color:red;font-weight: bold;" href="${ctx}/html/index.html">DEMO</a> 
				</security:authorize>
				 --%>
            </span>
            <span style="font-size: 16px; ">
            	<div style="float:left;margin: 5px 0 0 10px;"><img src="${ctx}/images/pd_logo.png" width="76" height="25" align="absmiddle" />&nbsp;</div>
            	<div style="float:left;margin: 5px 0 0 10px;font-size:20px;color:white;font-weight: bold;vertical-align: middle;">欢迎使用宝龙信息系统认证授权平台</div>
            </span>
        </div>
	</div>
	<div region="south" style="height:30px;padding:5px;background:#efefef;">
		<div align="center" style="font-weight: bold;color:#1B5978">诚信  恭谦 创新 敬业 - 宝龙集团</div>
	</div>
	<div region="west" split="true" title="导航菜单" style="width:180px;">
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
			<div title="首页" style="min-width:860px;width:100%;overflow: hidden;"> 
				<table style="width: 100%;">
					<tr>
						<td>
							<div id="idx_left" collapsible="true" style="max-width:370px;padding:0;min-height:420px; float:left;">
								<table  id="tbl_publist" fit="true"
										title="<a href='javascript:morePub()'>最新公告 </a>   <a style='float:right;' href='javascript:morePub()'>更多</a>" singleSelect="true" rownumbers="true"
										idField="plasPubId" url="${ctx}/plas/plas-pub!list.action">
								</table>
							</div>
						</td> 
						<td>
							<div id="idx_right" collapsible="true" style="min-width:520px；padding:0;min-height:420px; border-right:1px solid #99BBE8;">
								<table id="tbl_operatelist" fit="true"
										title="<a href='javascript:moreOperateLog()'>我的操作日志</a>     <a style='float:right;margin-right:50px;' href='javascript:moreOperateLog()'>更多</a>" singleSelect="true" rownumbers="true"
										idField="plasOperateLogId" url="${ctx}/plas/plas-operate-log!listMy.action">
								</table>
							</div>
						</td>
					</tr>
				</table>
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
	
	<script language="javascript">
	
		$(function(){

			var vHeight = $('#tabs').height();
				vHeight = vHeight-100;
			
			$('#idx_left').height(vHeight);
			$('#idx_right').height(vHeight);
		})
		
		$('#tbl_publist').datagrid({
			onDblClickRow: function(rowIndex, row){
				var id = row.plasPubId;
				var url = '${ctx}/plas/plas-pub!detail.action?id=' + id;
				parent.addTab('查看公告明细',url);
			},
			pageSize: 15,
			pagination:true,
			columns:[[
				{field:'pubDate',title:'公告日期', sortable:true, width:100,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
		        {field:'briefTitle',title:'标题',sortable:true,width:210}
			]],		
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
		
		$('#tbl_operatelist').datagrid({
			onDblClickRow: function(rowIndex, row){
				var id = row.plasOperateLogId;
				var url = '${ctx}/plas/plas-operate-log!detail.action?id=' + id;
				parent.addTab('查看操作日志明细',url);
			},
			pageSize: 15,
			pagination:true,
			columns:[[
			    /*
				{field:'moduleCd',title:'模块名称',sortable:true,width:50},
				{field:'sumarry',title:'操作',align:'center',width:30},		
				*/
				{field:'createdDate',title:'操作日期', sortable:true, width:100,
					formatter:function(value,row,index){
						var t = value.substr(2);
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+t+'</div>';
					}
				},				   
				{field:'operateDetailDesc',title:'操作明细',align:'left',width:300,
					formatter:function(value,row,index){		
						//设置单元格可换行
						var s = '<span style="white-space:pre-line">'+value+'</span>';
						return s;															
					}		
				}
			]],		
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			}
		});
		 
		function moreOperateLog(){
			parent.addTab('查询操作日志','/plas/plas/plas-operate-log.action')
		}
		function morePub(){
			parent.addTab('查询公告','/plas/plas/plas-pub.action')
		}
	</script>
</body>
</html>