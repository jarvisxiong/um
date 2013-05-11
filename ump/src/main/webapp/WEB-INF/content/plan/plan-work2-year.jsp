<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="java.util.Date"%>
<%@page import="com.hhz.core.utils.DateOperator"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp" %>
<%@ include file="/common/global.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/planWork.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/userChoose.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css"/>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/userChoose.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plan/planWork2Year.js"></script>
<title>公司年度工作计划</title>
<s:set var="curUserOrgCd" ><%=SpringSecurityUtils.getCurrentDeptCd()%></s:set>
<s:set var="curUserCenterOrgCd" ><%=SpringSecurityUtils.getCurrentCenterCd()%></s:set>
<s:set var="curUserCenterOrgName" ><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %></s:set>
<script type="text/javascript">
<%
	Date nowDate = DateOperator.truncDate(new Date());
%>
var DERAULT_YEAR = "<%=DateOperator.formatDate(nowDate,"yyyy") %>";	//默认的当前年份
var NOW_YEAR = DERAULT_YEAR;	//当前年份

$(function(){
	//初始化年份下拉框
	now_year = NOW_YEAR;
	$("#filter_EQs_planYear").val(NOW_YEAR);
	centerCd = "${centerCd}";
	var bodyHeight = Number($(document).height()-88);
	//$("#scheduleDiv").css("height",bodyHeight+"px");
	centerClick();
	$("#pop_bg").css("height",Number($(document).height()-20)+"px");
});
function openPlanWork2(){
	try{
		parent.TabUtils.newTab("153","总部工作计划",_ctx+"/plan/plan-target!monEnter.action?currentCenterCd="+centerCd,true);
	     }catch(e){
		self.location = _ctx+"/plan/plan-target!monEnter.action?currentCenterCd="+centerCd;
    } 
	/* try{
	parent.TabUtils.newTab("153","中心月计划",_ctx+"/plan/plan-work2!getAllPlan.action?centerCd="+centerCd,true);
	}catch(e){
		self.location = _ctx+"/plan/plan-work2!getAllPlan.action?centerCd="+centerCd;
	} */
}
</script>
</head>

<body>
<div class="title_bar" style="height: 40px;">
	<div style="float:left; padding-left:8px;">
	  <div  style="float: left;">
		<span class="color_blue" style="font-size:12px;">
			正在查看:<span id="deptInfoMsg" class="color_red"></span>
			<select id="search_centerCd" onchange="IF_DEPT_IN_SELECT=true;doChangeCenter(this);" <s:if test='cannotChangeDept'> disabled="disabled"</s:if>>
				<option value="">--请选择--</option>
				<c:set var="if_dept_in_select" value="false"></c:set>
				<c:forEach items="${orgMap}" var="map" >
					<optgroup label="${map.key}">
						<c:forEach var="org" items="${map.value}">
							<option value="${org.orgCd}" <c:if test="${org.orgCd==centerCd}">selected="selected"<c:set var="if_dept_in_select" value="true"></c:set></c:if> >${org.orgName}</option>
						</c:forEach>
					</optgroup>
				</c:forEach>
			</select>
		</span>
		<select id="filter_EQs_planYear" onchange="NOW_YEAR=$(this).val();centerClick(NOW_YEAR,$('#search_centerCd').val());">
			<option value="2010">2010</option>
			<option value="2011">2011</option>
			<option value="2012">2012</option>
			<option value="2013">2013</option>
			<option value="2014">2014</option>
			<option value="2015">2015</option>
			<option value="2016">2016</option>
			<option value="2017">2017</option>
			<option value="2018">2018</option>
			<option value="2019">2019</option>
		</select>
		年度工作计划
		</div>
		<div  style="float: left;">
		<input type="button" class="button_blue" onclick="openPlanWork2()" value="看月计划"/>
		</div>
		<span class="color_red" style="font-size:12px;" id="succInfoMsg"></span>
		<c:if test="${if_dept_in_select=='true'}"><script language="javascript">IF_DEPT_IN_SELECT = true;</script></c:if>
	</div>
	<div style="float:right; height:26px; margin-right:0px;">
		<s:url id="down1" action="exportXls" namespace="/plan"  includeParams="none"  >
		</s:url>
		<div class="btn_cutline_3_26"></div>
		<security:authorize ifAnyGranted="A_PLAN_WORK2_YEAR">
		<input type="button" id="NewBtn" class="button_new" onclick="newSchedule();" value="新建计划"/>
		<div id="NewBtn_cutline" style="display:none;" class="btn_cutline_3_26"></div>
		</security:authorize>
		<!-- 
		 -->
		<div id="operAll" style="float:left; display:none;" class="btn_title_bar" onclick="createAll();">重新生成月计划</div>
		<div id="operAll_cutline" class="btn_cutline_3_26" style=" display:none;"></div>
		<input type="button" onclick="$('#if_search_all').val('1');centerClick();" class="button_blue" value="全部"/>
		
		<input type="button" class="button_green" onclick="myTask='';if_in_attention=false;IF_DEPT_IN_SELECT=true;doChangeCenter($('#search_centerCd'));" value="刷新" />
		
		
		<input type="button" onclick="window.open('${ctx}/plan/plan-work2-year!getAllPlan.action?centerCd=${centerCd}')" style="padding-left:5px;" class="button_green" value="全屏"/>
	</div>
</div>

<div class="search_bar" style="height:40px; width:100%;">
 <div style="float:right">
	<table style="height:26px;" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="left" style="padding-top:8px;">
				工作目标:<input type="text" onclick="select();" id="filter_LIKES_workTarget" style="width:80px;" />
			
				措施:<input type="text" onclick="select();" id="filter_LIKES_detailStep" style="width:80px;" />
				
				&nbsp;
				状态:<select id="search_statusCd">
					<option value=""></option>
					<option value="5">已删除</option>
				</select>
				
			</td>
			<td><input type="button" class="button_blue" onclick="$('#pageNo').val(1);centerClick();" value="搜索"/></td>
		</tr>
	</table>
	</div>
</div>
<div id="scheduleDiv" style="overflow-x:hidden;">
	<div style='height:100px'></div>
	<table width='100%'>
		<tr>
			<td align='center'>
				<img src='${ctx}/resources/images/common/loading.gif'/>
			</td>
		</tr>
	</table>
</div>
<div class="bottom_bar">
	<div style="width:100%;height:1px;background-color:#dcdcde;"></div>
	<div style="width:100%;height:1px;background-color:#8c8f94;"></div>
	<div style="width:100%;height:26px;">
		<div id="operate_all_div">
			<div class="btn_bottom_chk_all"><div style="padding-top:5px;"><input type="checkbox" onclick="checkedAll($(this).attr('checked'));" style="cursor:pointer;" title="全选/不选"/></div></div>
			<div class="btn_cutline_3_26"></div>
				<div class="btn_bottom_bar" onclick="doExchangeOrder();">交换顺序</div>
		</div>
		<div id="td_page" style="float:right; text-align: center; height:26px; margin-right:10px;"></div>
	</div>
	<div style="width:100%;height:1px;background-color:#dcdcde;"></div>
	<div style="width:100%;height:1px;background-color:#8c8f94;"></div>
</div>
<div id="pop_bg" class="pop_bg" style="display:none;">
	<div style='height:200px'></div>
	<table width='100%'>
		<tr>
			<td align='center'>
				<img src='${ctx}/resources/images/common/loading.gif'/>
			</td>
		</tr>
	</table>
</div>
</body>
</html>
