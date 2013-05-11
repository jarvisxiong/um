<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<style>
.projecttitle{background:#0167A2;color:#ffffff;line-height:30px;height:30px;padding-left:0px;}
.projects{float:left;margin-left:2px;}
.projectDisplay{padding-left:1px;width:120px;}
.projectDisplay li{list-style:none;padding-left:5px}
.projectHeader{background:#eeeeee;color:#000000;font-size:14px;height:25px;line-height:25px;}
.projectSelected{background-color:#6eb1cf;color:#ffffff;cursor:pointer;padding-left:15px;height:20px;line-height:20px;margin-bottom:2px!important;margin-bottom:0px;}
.projectUnSelected{cursor:pointer;padding-left:15px;height:20px;line-height:20px;margin-bottom:2px!important;margin-bottom:0px}

.jqmBottom{}
</style>
<div style="clear:both;overflow:auto;display:inline"  >
<div class="projects" id="projectNorth">
	<div>
		<ul class="projectDisplay">
			<li class="projecttitle">北方区域公司</li>
			<c:forEach items="${projectNorth}" var="northInfo">
				<li class="projectUnSelected"
					id="${northInfo.bisProjectId}" 
					projectName="${northInfo.projectName}" 
					orgCd="${northInfo.orgCd}" 
				>
				<div class=''>
					${northInfo.projectName}
				</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<div class="projects" id="projectSouth">
	<div>
		<ul class="projectDisplay">
			<li class="projecttitle">南方区域公司</li>
			<c:forEach items="${projectSouth}" var="southInfo">
				<li class="projectUnSelected"
					id="${southInfo.bisProjectId}" 
					projectName="${southInfo.projectName}" 
					orgCd="${southInfo.orgCd}" 
				>
				<div class=''>
					${southInfo.projectName}
				</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<div  id="projectGroup" style="float:left;padding-left:1px;width:60px;">
	<div>
		<ul class="projectDisplay">
				<li class="projecttitle">上海总部</li>
				<li class="projectUnSelected"
					id="1" 
					projectName="宝龙商业" 
					orgCd="153" 
				>
				<div class=''>
					宝龙商业
				</div>
				</li>
		</ul>
	</div>
</div>
</div>
