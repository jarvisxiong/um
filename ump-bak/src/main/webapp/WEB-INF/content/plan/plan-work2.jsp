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
<script type="text/javascript" src="${ctx}/resources/js/plan/planWork2.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/userChoose.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
<title>中心月计划<s:if test="1==if_in_weight">考评</s:if></title>
<script type="text/javascript">
var opened_entityid = "${opened_entityid}";	//要打开的记录的id
var if_in_attention = "${if_in_attention}";	//是否由关注的记录打开
var nowDate = "<%=DateOperator.formatDate(DateOperator.truncDate(new Date()),"yyyy-MM-dd") %>";	//更新记录后显示更新时间用
var page_size = 15; //分页大小，随列表区高度改变而改变
var IF_DEPT_IN_SELECT = false;	//是否在部门列表内，如果不在，就不能新增
$(function(){
	myTask = "${myTask}";	//邮件进的ID值(不用)
	now_year = "${now_year}";
	now_month = "${now_month}";
	now_quarter = "${now_quarter}";
	if_in_weight = "${if_in_weight}";
	if_goto_cost = "${if_goto_cost}";	//是否是成本计划
	$("#select_now_year").val(now_year);
	$("#select_now_month").val(now_month);
	$("#select_now_quarter").val(now_quarter);
	centerCd = $("#search_centerCd").val();
	var bodyHeight = Number($(document).height()-118);
	$("#scheduleDiv").css("height",bodyHeight+"px");
	//page_size = Math.ceil((bodyHeight-60)/30);
	centerClick();
	return;
});
function openPlanWork2Year(){
	try{
		parent.TabUtils.newTab("154","年度工作计划",_ctx+"/plan/plan-work2-year!getAllPlan.action?centerCd="+centerCd,true);
	}catch(e){
		self.location = _ctx+"/plan/plan-work2-year!getAllPlan.action?centerCd="+centerCd;
	}
}
</script>
</head>

<body>
<div id="title_bar" class="title_bar_plan" style="height:40px;<s:if test="1==if_goto_cost"> display:none;</s:if>">
	<div style="float:left; padding-left:8px;margin-top:8px;">
		<span style="font-weight:bolder;">中心月计划<s:if test="1==if_in_weight">考评</s:if></span>：
				内容:<input type="text" onclick="select();" id="filter_LIKES_content" style="width:90px;" />
				状态:<select id="search_statusCd" class="select_box">
					<option value=""></option>
					<option value="1">进行中</option>
					<option value="2">预完成</option>
					<option value="3">申请删除</option>
					<option value="4">完成</option>
					<option value="10">完成但曾过期</option>
					<option value="5">已删除</option>
					<option value="6">已隐藏</option>
					<option value="7">非本月</option>
					<option value="8">已过期</option>
					<option value="9">非考核性过期</option>
				</select>
				<input type="hidden" id="orderStr1"/>
				<input type="hidden" id="orderStr2"/>
				<input type="hidden" id="orderDir1"/>
				<input type="hidden" id="orderDir2"/>
				<input type="hidden" id="if_search_all" value="0"/>
				&nbsp;
				
	</div>
	<div style="float: left;">
	<input type="button" class="button_blue" onclick="$('#pageNo').val(1);centerClick();" value="搜索"/>
				<s:if test="1==if_in_weight"><input type="button" class="button_green" onclick="javascript:self.location=_ctx+'/plan/plan-work2!getAllPlan.action?if_in_weight=0&centerCd='+centerCd;" value="返回列表"/></s:if>
				<s:else><input type="button" class="button_green" style="display:none;" onclick="javascript:self.location=_ctx+'/plan/plan-work2!getAllPlan.action?if_in_weight=1&centerCd='+centerCd;" value="评分"/></s:else>
				<input type="button" class="button_green" onclick="openPlanWork2Year()" value="年计划"/>
	</div>
	<div style="float:right; height:26px; margin-right:0px;">
		<s:url id="down1" action="exportXls" namespace="/plan"  includeParams="none"  >
		</s:url>
		<input type="button" class="button_green" onclick="myTask='';if_in_attention=false;IF_DEPT_IN_SELECT=true;doChangeCenter($('#search_centerCd'));" value="刷新"/>
        <input type="button" class="button_green" onclick="window.open('${ctx}/plan/plan-work2!getAllPlan.action?centerCd=${centerCd}')" value="全屏"/>
	</div>
</div>
<div id="search_bar" style="padding:5px;<s:if test="1==if_goto_cost"> display:none;</s:if>">
	<table style="width:100%;border:1px solid #E4E7EC;font-size:14px;color:red;height: 30px;line-height: 30px;" cellpadding="0" cellspacing="0">
		<tr>
			<td style="padding:8px 5px; font-size:14px;">
				<span class="color_red">
					正在查看：
				</span>
				<select id="search_centerCd" class="select_box" onchange="myTask='';if_in_attention=false;IF_DEPT_IN_SELECT=true;doChangeCenter(this);" <s:if test='cannotChangeDept'> disabled="disabled"</s:if>>
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
				<span class="color_black">
					<select id="select_now_year"  class="select_box" onchange="now_year=$(this).val();centerClick();">
						<option value="2011">2011</option>
						<option value="2012">2012</option>
						<option value="2013">2013</option>
						<option value="2014">2014</option>
						<option value="2015">2015</option>
						<option value="2016">2016</option>
						<option value="2017">2017</option>
						<option value="2018">2018</option>
						<option value="2019">2019</option>
						<option value="2019">2020</option>
					</select>
					年
					<s:if test="!(1==if_in_weight)">
						<button id="month1"  onClick="monthClick(1)"  <s:if test="now_month==1">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>1</button><button id="month2"  onClick="monthClick(2)"  <s:if test="now_month==2">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>2</button><button id="month3"  onClick="monthClick(3)"  <s:if test="now_month==3">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>3</button><button id="month4"  onClick="monthClick(4)"  <s:if test="now_month==4">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>4</button><button id="month5"  onClick="monthClick(5)"  <s:if test="now_month==5">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>5</button><button id="month6"  onClick="monthClick(6)"  <s:if test="now_month==6">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>6</button><button id="month7"  onClick="monthClick(7)"  <s:if test="now_month==7">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>7</button><button id="month8"  onClick="monthClick(8)"  <s:if test="now_month==8">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>8</button><button id="month9"  onClick="monthClick(9)"  <s:if test="now_month==9">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>9</button><button id="month10" onClick="monthClick(10)" <s:if test="now_month==10">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>10</button><button id="month11" onClick="monthClick(11)" <s:if test="now_month==11">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>11</button><button id="month12" onClick="monthClick(12)" <s:if test="now_month==12">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>12</button>
						月
					</s:if>
					<s:if test="1==if_in_weight">
						<select id="select_now_quarter"  class="select_box" onchange="now_quarter=$(this).val();centerClick();">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
						季度
					</s:if>
				 </span>
				<span class="color_black">(状态：<span id="centerStatusMsg" class="color_red"></span><span id="finalPointSumMsg" class="color_red"></span>)</span>
				<span class="color_red" style="font-size:12px;" id="succInfoMsg"></span>
				<span id="span_select_center_status" style="display:none">
					<select id="select_center_status" class="select_box">
						<option value="">-请选择-</option>
						<option value="0">各中心新增中</option>
						<option value="1">各中心新增完毕</option>
						<option value="2">副总裁确认新增完毕</option>
						<option value="3">计划已确认，开始执行</option>
						<option value="4">开始给权重分</option>
						<option value="5">总裁给权重分完毕</option>
						<option value="6">各中心自评完毕</option>
						<option value="7">副总裁评分完毕</option>
						<option value="8">营运管理中心考评完毕</option>
						<option value="9">总裁给最终分数完毕</option>
					</select>
				</span>
				<c:if test="${if_dept_in_select=='true'}"><script language="javascript">IF_DEPT_IN_SELECT = true;</script></c:if>
			</td>
			<td align="right" style="white-space: nowrap;">
				<input type="button" id="NewBtn" class="button_new" style="float:right; display:none;" onclick="newSchedule();" value="新建计划"/>
				<input type="button" id="centerStatusBtn" class="button_blue" style="float:right; display:none;" onclick="changeCenterStatus();" value="确认完成"/>
			</td>
		</tr>
	</table>
</div>
<div id="scheduleDiv" style="width:100%;">
	<div style='height:100px'></div>
	<table width='100%'>
		<tr>
			<td align='center'>
				<img src='${ctx}/images/loading.gif'/>
			</td>
		</tr>
	</table>
</div>

<div style="display:none;">
<div id="bottom_bar" class="bottom_bar" style="display:none;">
	<div style="width:100%;height:1px;background-color:#dcdcde;"></div>
	<div style="width:100%;height:1px;background-color:#8c8f94;"></div>
	<div style="width:100%;height:26px;">
		<div id="operate_all_div">
			<div class="btn_bottom_chk_all"><div style="padding-top:5px;"><input type="checkbox" onclick="checkedAll($(this).attr('checked'));" style="cursor:pointer;" title="全选/不选"/></div></div>
			<div class="btn_cutline_3_26"></div>
			<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_VICE,A_PLAN_WORK2_OFFICE">
			    <input type="button" class="button_green" onclick="doExchangeOrder();" value="交换顺序"/>
				
				<security:authorize ifAnyGranted="A_PLAN_WORK2_CENTER">
					<div class="btn_bottom_bar" onclick="doUpdateStatusAll(3);">申请删除</div>
					<div class="btn_cutline_3_26"></div>
				</security:authorize> 
				<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_MANAGER">
					<div class="btn_bottom_bar" onclick="doUpdateStatusAll(5);">批量删除</div>
					<div class="btn_cutline_3_26"></div>
				</security:authorize>
				<div class="btn_bottom_bar" onclick="doUpdateStatusAll(1);">批量退回</div>
				<div class="btn_cutline_3_26"></div>
				<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN,A_PLAN_WORK2_VICE,A_PLAN_WORK2_OFFICE">
					<div class="btn_bottom_bar" onclick="doUpdateStatusAll(4);">批量完成</div>
					<div class="btn_cutline_3_26"></div>
				</security:authorize>
				<security:authorize ifAnyGranted="A_PLAN_WORK2_ADMIN">
					<div class="btn_bottom_bar" onclick="doUpdateStatusAll(6);">批量隐藏</div>
					<div class="btn_cutline_3_26"></div>
				</security:authorize>
			</security:authorize>
		</div>
		<div id="td_point_count" style="float:right; text-align: center; height:26px; margin-right:10px; font-weight:bolder;"></div>
		<div id="td_page" style="float:right; text-align: center; height:26px; margin-right:10px; display:none;"></div>
	</div>
	<div style="width:100%;height:1px;background-color:#dcdcde;"></div>
	<div style="width:100%;height:1px;background-color:#8c8f94;"></div>
</div>
</div>
</body>
</html>
