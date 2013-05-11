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
<script type="text/javascript" src="${ctx}/resources/js/oa/allAttention.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plan/planWork.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/userChoose.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
<title>中心月计划</title>
<script type="text/javascript">
var opened_entityid = "${opened_entityid}";	//要打开的记录的id
var if_in_attention = "${if_in_attention}";	//是否由关注的记录打开
var nowDate = "<%=DateOperator.formatDate(DateOperator.truncDate(new Date()),"yyyy-MM-dd") %>";	//更新记录后显示更新时间用
var page_size = 15; //分页大小，随列表区高度改变而改变
var IF_DEPT_IN_SELECT = false;	//是否在部门列表内，如果不在，就不能新增
$(function(){
	myTask = "${myTask}";
	now_year = "${now_year}";
	now_month_of_year = "${now_month_of_year}";
	centerCd = "${centerCd}";
	var bodyHeight = Number($(document).height()-140);
	$("#scheduleDiv").css("height",bodyHeight+"px");
	page_size = Math.ceil((bodyHeight-100)/30);
	centerClick();
	return;
});
</script>
</head>

<body>
<div class="title_bar" style="height:27px;">
	<div style="float:left; padding-left:8px;">
		&nbsp;中心月计划
		<span class="color_blue" style="font-size:12px;">
			正在查看:<span id="deptInfoMsg" class="color_red"></span>
			<select id="search_centerCd" onchange="myTask='';if_in_attention=false;IF_DEPT_IN_SELECT=true;doChangeCenter(this);" <security:authorize ifAnyGranted="A_PLAN_WORK_DEPT">disabled="disabled"</security:authorize>>
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
		<span class="color_red" style="font-size:12px;" id="succInfoMsg"></span>
		<c:if test="${if_dept_in_select=='true'}"><script language="javascript">IF_DEPT_IN_SELECT = true;</script></c:if>
	</div>
	<div style="float:right; height:26px; margin-right:0px;">
		<s:url id="down1" action="exportXls" namespace="/plan"  includeParams="none"  >
		</s:url>
		<div class="btn_cutline_3_26"></div>
		<div id="NewBtn" style="display:none; float:left;" class="btn_add_blue_bar" onclick="newSchedule();"><div style="padding-left:19px;">新建计划</div></div>
		<div id="NewBtn_cutline" style="display:none;" class="btn_cutline_3_26"></div>
		<div class="btn_title_bar" onclick="myTask='';if_in_attention=false;IF_DEPT_IN_SELECT=true;doChangeCenter($('#search_centerCd'));"><div style="float:left; margin-top:4px; margin-left:8px;"><img src="${ctx}/resources/images/common/title_bar_refresh.gif"/></div><div style="float:left;">刷新</div></div>
		<div class="btn_cutline_3_26"></div>
		<div class="btn_title_bar" onclick="$('#if_search_all').val('1');centerClick();"><div style="float:left; margin-top:4px; margin-left:8px;"><img src="${ctx}/resources/images/common/title_bar_all.gif"/></div><div style="float:left;">全部</div></div>
		<div class="btn_cutline_3_26"></div>
		<security:authorize ifAnyGranted="A_PLAN_WORK_ADMIN,A_PLAN_WORK_MANAGER,A_PLAN_WORK_USER">
		<div id="ConfirmBtn" style="display:none;" class="btn_title_bar" onclick="doConfirm();"><div style="float:left; margin-top:4px; margin-left:8px;"><img src="${ctx}/resources/images/plan/plan_lock.gif"/></div><div style="float:left;">归档</div></div>
		<div id="ConfirmBtn_cutline" style="display:none;" class="btn_cutline_3_26"></div>
		<div id="UnConfirmBtn" style="display:none;" class="btn_title_bar" onclick="doUnConfirm();"><div style="float:left; margin-top:4px; margin-left:8px;"><img src="${ctx}/resources/images/plan/plan_unlock.gif"/></div><div style="float:left;">解锁</div></div>
		<div id="UnConfirmBtn_cutline" style="display:none;" class="btn_cutline_3_26"></div>
		</security:authorize>
		<div class="btn_full_white" onclick="window.open('${ctx}/plan/plan-work!getAllPlan.action?centerCd=${centerCd}')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;全屏</div>
	</div>
</div>

<div class="search_bar" style="height:80px;">
	<table style="height:80px;" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td style="font-size:12px;" align="left">
				当前总任务数:<span id="countAll"></span>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				已完成任务数:<span id="countComplete"></span>
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;" align="left">
				来自上个月的任务数:<span id="countFromPrev"></span>
				&nbsp;&nbsp;&nbsp;&nbsp;
				来自上个月的任务已完成数:<span id="countFromPrevCompleted"></span>
				&nbsp;&nbsp;&nbsp;&nbsp;
				上个月任务完成率:<span id="completedRate"></span>%
			</td>
		</tr>
		<tr>
			<td align="left">
				内容:<input type="text" onclick="select();" id="filter_LIKES_content" style="width:90px;" />
				状态:<select id="search_statusCd">
					<option value=""></option>
					<option value="1">进行中</option>
					<option value="2">申请删除</option>
					<option value="3">完成</option>
					<option value="4">已删除</option>
					<option value="5">已隐藏</option>
					<option value="6">预完成</option>
				</select>
				<input id="search_year" type="text" style="width:40px;" onclick="select();" value="${now_year}"/>年
				<select id="search_planMonth" style="width:40px;">
<%
	int now_month_of_year = 0;
	now_month_of_year = (Integer)request.getAttribute("now_month_of_year");
	for(int i=1;i<13;i++){
%>
						<option value="<%=i%>"<%if(i==now_month_of_year){%> selected<%}%>><%=i%></option>
<%} %>
					</select>
					月
				<input type="hidden" id="orderStr1"/>
				<input type="hidden" id="orderStr2"/>
				<input type="hidden" id="orderDir1"/>
				<input type="hidden" id="orderDir2"/>
				<input type="hidden" id="if_search_all" value="0"/>
				&nbsp;
				<button type="button" class="btn_green_55_20" onclick="$('#pageNo').val(1);centerClick();">当前</button>&nbsp;
				<button type="button" class="btn_green_55_20" onclick="$('#pageNo').val(1);searchHistory();">历史</button>&nbsp;
				<button type="button" class="btn_green_55_20" onclick="clearAllSearch();">清空</button>
			</td>
		</tr>
	</table>
</div>
<div id="scheduleDiv" style="height:391px; overflow:auto; overflow-x:hidden;">
	<div style='height:100px'></div>
	<table width='100%'>
		<tr>
			<td align='center'>
				<img src='${ctx}/images/loading.gif'/>
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
			<security:authorize ifAnyGranted="A_PLAN_WORK_ADMIN,A_PLAN_WORK_MANAGER,A_PLAN_WORK_USER">
				<div class="btn_bottom_bar" onclick="doExchangeOrder();">交换顺序</div>
				<div class="btn_cutline_3_26"></div>
				<security:authorize ifAnyGranted="A_PLAN_WORK_USER">
					<div class="btn_bottom_bar" onclick="doUpdateStatusAll(2);">申请删除</div>
					<div class="btn_cutline_3_26"></div>
				</security:authorize> 
				<security:authorize ifAnyGranted="A_PLAN_WORK_ADMIN,A_PLAN_WORK_MANAGER">
					<div class="btn_bottom_bar" onclick="doUpdateStatusAll(4);">批量删除</div>
					<div class="btn_cutline_3_26"></div>
				</security:authorize>
				<div class="btn_bottom_bar" onclick="doUpdateStatusAll(1);">批量退回</div>
				<div class="btn_cutline_3_26"></div>
				<security:authorize ifAnyGranted="A_PLAN_WORK_ADMIN,A_PLAN_WORK_MANAGER,A_PLAN_WORK_USER">
					<div class="btn_bottom_bar" onclick="doUpdateStatusAll(3);">批量完成</div>
					<div class="btn_cutline_3_26"></div>
				</security:authorize>
				<security:authorize ifAnyGranted="A_PLAN_WORK_ADMIN">
					<div class="btn_bottom_bar" onclick="doUpdateStatusAll(5);">批量隐藏</div>
					<div class="btn_cutline_3_26"></div>
				</security:authorize>
			</security:authorize>
		</div>
		<div id="td_page" style="float:right; text-align: center; height:26px; margin-right:10px;"></div>
	</div>
	<div style="width:100%;height:1px;background-color:#dcdcde;"></div>
	<div style="width:100%;height:1px;background-color:#8c8f94;"></div>
</div>
</body>
</html>
