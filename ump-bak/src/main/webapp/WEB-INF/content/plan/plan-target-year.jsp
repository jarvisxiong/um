<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.core.utils.RandomUtils"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>宝龙管理平台</title>
<%@ include file="/common/global.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/plan-target.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />
	
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plan/plan-target.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/oa/allAttention.js"></script>
</head>
<body style="overflow:hidden;">
<div id="main">
	<div id="top">
		<div style="float:left;">
		<label style="font-weight: bold; font-size: 14px;float:left;margin-top:3px;">正在查看：</label><input id="centerName" class="inputBtn"
		<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_VICE,A_PLAN_WORK2_CEO,A_PLAN_WORK2_VIEW">
		 onmouseover="centerShow();"
		 </security:authorize>
		  readonly="readonly" style="color:#F46614;font-weight: bold;float:left;"/>
		<div id="selectCenter" style="display: none;">
			<div class="centerTitle">地产</div>
			<s:iterator value="MapEstateCenterName" id="" status="st">
				<s:if test="key!=''">
					<input type="button" value="<s:property value="value"/>" key="<s:property value="key"/>" class="btnCancel" onclick="centerYearSelect('<s:property value="key"/>');"/>
				</s:if>
				<s:if test="#st.index%5==0">
					<div style="clear:both;"></div>
				</s:if>
			</s:iterator>
			<div class="centerTitle">商业</div>
			<s:iterator value="MapBusinessCenterName" id="" status="st">
				<s:if test="key!=''">
					<input type="button" value="<s:property value="value"/>" key="<s:property value="key"/>" class="btnCancel" onclick="centerYearSelect('<s:property value="key"/>');"/>
				</s:if>
				<s:if test="#st.index==5">
					<div style="clear:both;"></div>
				</s:if>
			</s:iterator>
			<div class="centerTitle">其它</div>
			<s:iterator value="MapOtherCenterName" id="" status="st">
				<s:if test="key!=''">
					<input type="button" value="<s:property value="value"/>" key="<s:property value="key"/>" class="btnCancel" onclick="centerYearSelect('<s:property value="key"/>');"/>
				</s:if>
				<s:if test="#st.index==5">
					<div style="clear:both;"></div>
				</s:if>
			</s:iterator>
		</div>
		<span style="float:left;margin-left: 10px;"><input id="year" class="year"/>年
			<div id="selectYear" style="display: none;">
				<div class="yearDiv">请选择年份</div>
				<input type="button" class="btnCancel" value="2010" key="2010" onclick="selectYearByYear(2010);"/>
				<input type="button" class="btnCancel" value="2011" key="2011" onclick="selectYearByYear(2011);"/>
				<input type="button" class="btnCancel" value="2012" key="2012" onclick="selectYearByYear(2012);"/>
				<div style="clear: both;"></div>
				<input type="button" class="btnCancel" value="2013" key="2013" onclick="selectYearByYear(2013);"/>
				<input type="button" class="btnCancel" value="2014" key="2014" onclick="selectYearByYear(2014);"/>
				<input type="button" class="btnCancel" value="2015" key="2015" onclick="selectYearByYear(2015);"/>
				<div style="clear: both;"></div>
				<input type="button" class="btnCancel" value="2016" key="2016" onclick="selectYearByYear(2016);"/>
				<input type="button" class="btnCancel" value="2017" key="2017" onclick="selectYearByYear(2017);"/>
				<input type="button" class="btnCancel" value="2018" key="2018" onclick="selectYearByYear(2018);"/>
			</div>
		</span>
		<span style="margin-left:10px;">
		<input class="btn_blue" type="button" onclick="openMonth();" value="月计划"/>
		</span>
	</div>
	<div class="titleBarRight" style="float:right;">
	<%-- 
	    <input class="btnBlue" type="button" onclick="updateYearForMonth();" value="更新年计划数据"/>
	    --%>
	    <security:authorize ifAnyGranted="A_PLAN_WORK2_YEAR">
	    <input type="button" value="高级搜索" onclick="hideOrShow_Search();" style="margin-left: 5px;" class="btn_Gray">
	    <input class="btn_Blue" type="button" onclick="newYearPlan();" value="新建计划"/>
	    </security:authorize>
		<input class="btn_Blue" type="button" onclick="jumpPageYear('1');" value="刷新"/>
		<input class="btn_Green" type="button" onclick="fullScreenYear();" value="全屏"/>
	</div>
	</div>
	<div id="searchPanel" style="display: none;height:35px;">
	 <table style="width: 100%;">
		<tr>
			<td width="25%">工作目标：<input type="text" id="workTarget"> </td>
			<td width="25%">措施：<input type="text" id="workContent"> </td>
			<td width="25%">状态：<select id="search_statusCd" >
					<option value=""></option>
					<option value="10">已删除</option>
				</select>
				</td>
			 <td width="25%">
			 <input type="button" class="btnSearch btn_Green" onclick="seniorSearch()" value="搜索" />
			 <input type="button" class="btnCancel btn_Green" onclick="hideOrShow_Search();" value="返回"/>
			 </td>
		  </tr>
	  </table>
	</div>
	<div id="mainContent">
		<form action="${ctx}/plan/plan-target!year.action" method="post" id="advanceSearch">
			<input type="hidden" id="queryPageNo" name="page.pageNo"/>
			<input type="hidden" id="centerCd" name="center" value="${currentCenterCd}"/>
			<input type="hidden" name="lockFlg" id="lockFlg" />
			<input type="hidden" id="currentCenterCd" name="currentCenterCd" value="${currentCenterCd}"/>
			<input type="hidden" id="currentPlanYear" name="currentPlanYear" value="${currentPlanYear}"/>
			<input type="hidden" id="targetYear" name="targetYear" value=""/>
			<input type="hidden" id="targetMonth" name="targetMonth" value=""/>
			<input type="hidden" id="yearTarget_qua" name="entity.yearTarget"/>
			<input type="hidden" id="yearContent" name="entity.content"/>
			<input type="hidden" id="status" name="entity.status"/>
		</form>
	<div id="contentPanel">
	</div>
	</div>
</div>
</body>
</html>

<script language="javascript">
	var currentPlanYear = "${currentPlanYear}";
	$(function() {
		$("#year").val(currentPlanYear);
		$("#centerName").val(CENTER_NAMES[$("#currentCenterCd").val()]);
		var centers = $("#selectCenter >:input");
		initSelect("selectCenter", "centerName", centers, $("#currentCenterCd").val());
		
		// 选中当前用户所在中心
		for(var i=0; i<centers.size(); i++) {
			if((i+1) % 5 == 0) {
				centers.eq(i).css("border-right", "none");
			}
		}

		var date = new Date();
		var year = 0;
		try{
			year = currentPlanYear;
		}catch(e){year = date.getFullYear();}
		if($("#currentPlanYear").val()) {
			year = $("#currentPlanYear").val();
		}
		// 初始化年份选择div
		var years = $("#selectYear >:input");
		initSelect("selectYear", "year", years, year);
		
		// 初始化年份选择div
		var years = $("#selectYear >:input");
		initSelect("selectYear", "year", years, year);
		jumpPageYear("1");
	});
	
</script>