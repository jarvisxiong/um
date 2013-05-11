<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<style>
.ctslt-nav{
	list-style-type:none;
	font-size:12px;
	/*border-top: 1px solid #CCCCCC;*/
	margin: 0 5px;
}
.ctslt-nav li{
	height:20px;
	line-height:20px;
	padding:0px 5px;
	margin-top:2px;
	cursor:pointer;
	word-break:break-all;
}
.ctslt-nav li:hover{
	background-color:#6eb1cf;
	color:#fff;
}
.ctslt-nav-click{
	background-color:#6eb1cf;
	color:#fff;
}
.ctsltTitle{
	border-top: 4px solid #9CB6C6;
	line-height:30px;
	height:30px;
	padding:0 5px;
	font-weight:bolder;
	font-size:14px;
}

.div_scroll_y {
	overflow-y:scroll;
}
#delete_all,#add_all{height:25px;display:none}
#divContSelected,#divFloor,#divContList,#divSmallType,#divBigType{float: left; margin-right: 20px; width: 130px; height: 100%; border: 1px solid #ccc;"}
</style>
<input type="hidden" id="bisContIdsTemp" value="${bisContIdsTemp}">
<input type="hidden" id="bisContNosTemp" value="${bisContNosTemp}">
<input type="hidden" id="bisProjectIdTemp" value="${bisProjectId}">
<div style="padding: 20px 20px 0 20px; height: 27px;">
	<input type="text" id="search_contNo" style="width:130px;" class="search" searchtext="搜索..."/>
	<img style="vertical-align: middle; margin-top: -5px;" src="${ctx }/images/webim/search_zoom.jpg">
</div>
<div style="padding: 0px 20px 0 20px; height: 270px;">
	<div id="divBigType" style="">
		<div class="ctsltTitle" ><font color="#ff0000">*</font>&nbsp;合同大类</div>
		<ul id="ulBigType" class="ctslt-nav" style="border-top: 1px solid #ccc;">
			<s:iterator value="mapContBigType">
			<li id="btli_${key}" value="${key}" onclick="clkBigType(this);" >${value}</li>
			</s:iterator>
		</ul>
	</div>
	
	<div id="divSmallType" style="">
		<div class="ctsltTitle" ><font color="#ff0000">*</font>&nbsp;合同小类</div>
		<ul id="ulSmallType" class="ctslt-nav" style="border-top: 1px solid #ccc;">
			<!--<s:iterator value="mapContSmallType">
			<li id="stli_${key}" value="${key}" onclick="clkSmallType(this);">${value}</li>
			</s:iterator>
			-->
		</ul>
	</div>
	
	<div id="divFloor" style="">
		<div class="ctsltTitle" ><font color="#ff0000">*</font>&nbsp;楼层</div>
		<ul id="ulFloor" class="ctslt-nav" style="border-top: 1px solid #ccc;">
		</ul>
	</div>
	
	<div id="divContList" style="">
		<div class="ctsltTitle" ><font color="#ff0000">*</font>合同&nbsp;<input type="button" id="add_all" style="width:40px;"  value="全选"/></div>
		<ul id="ulContList" class="ctslt-nav" style="border-top: 1px solid #ccc;">
			<!--<s:iterator value="mapContSmallType">
			<li id="stli_${key}" value="${key}" onclick="clkSmallType(this);">${value}</li>
			</s:iterator>
			-->
		</ul>
	</div>
	
	<div id="divContSelected" style="">
		<div class="ctsltTitle" >已选${title}&nbsp;<input type="button" id="delete_all" value="全部删除"/></div>
		<div id="divContSelectedData" style="height:355px; border-top:1px solid #ccc;">
			<ul id="ulContSelected" class="ctslt-nav" >
			
			</ul>
		</div>
	</div>
	
</div>
<script type="text/javascript">


</script>