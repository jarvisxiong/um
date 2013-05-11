<%@page import="com.hhz.ump.util.JspUtil"%>
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
<script type="text/javascript" src="${ctx}/resources/js/plan/planWorkCenter.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/plan/plan-work-centerAll.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/userChoose.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
<title>中心内部任务</title>
<script type="text/javascript">
uiid="<%=SpringSecurityUtils.getCurrentUiid()%>";
var opened_entityid = "${opened_entityid}";	//要打开的记录的id
var if_in_attention = "${if_in_attention}";	//是否由关注的记录打开
var nowDate = "<%=DateOperator.formatDate(DateOperator.truncDate(new Date()),"yyyy-MM-dd") %>";	//更新记录后显示更新时间用
var page_size = 15; //分页大小，随列表区高度改变而改变
var IF_DEPT_IN_SELECT = false;	//是否在部门列表内，如果不在，就不能新增
var LIST_CENTER = new Array();	//中心的数组，给部门选择框用
var if_in_center = false;	//是否在中心搜索内，如果在中心内则内容前面显示部门。refreshMain方法用
var if_chengben = false;	//是否成本，如果是要显示项目的选择框

var if_add_from_planExec = false;	//是否有新增来自执行计划的情况，如是则自动打开新增，填入相应信息
if("1"=="${new_addFromType}" || "2"=="${new_addFromType}"){
	if_add_from_planExec = true;
	new_content = "${new_content}";
	new_targetDate = "${new_targetDate}";
	new_targetPointCd = "${new_projectCd}";
	new_addFromType = "${new_addFromType}";
	new_planExecId = "${new_planExecId}";
}
<c:forEach items="${mapCenterSelect}" var="mapCenterSelect" >
LIST_CENTER.add({"key":"${mapCenterSelect.key}","value":"${mapCenterSelect.value}"});
</c:forEach>
$(function(){
	myTask = "${myTask}";
	now_year = "${now_year}";
	now_month = "${now_month}";
	now_month_of_year = "${now_month_of_year}";
	centerCd = "${centerCd}";
	var bodyHeight = Number($(document).height()-90);
	$("#scheduleDiv").css("height",bodyHeight+"px");
	page_size = Math.ceil((bodyHeight-100)/30);
	$("select[@id=search_centerCd] option").each(function(){
		//把部门下拉选择框中的cd替换为name
		if("center"==$(this).attr("mytype")){
			//只替换中心
			for(var i=0;null!=LIST_CENTER&&i<LIST_CENTER.length;i++){
					if($(this).val() == LIST_CENTER[i].key){
						$(this).html(LIST_CENTER[i].value);
						break;
					}
			}
		}
	});
	<s:if test="ifFromDaiban">
		doChangeCenter("ifFromDaiban");
	</s:if>
	<s:else>doChangeCenter(centerCd);</s:else>
	return;
});
function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	centerClick();
}
</script>
<script type="text/javascript">
		$(function(){
			$('#select_manager').ouSelect();
			$('#select_personal').ouSelect();
			$('.singleSelect').userSelect({
				muti:true
			});
		});
	</script>
</head>

<body>
<div class="title_bar" style="height:40px;">
	<div style="float:left; padding-left:8px;">
		&nbsp;中心内部任务
		<span class="color_blue" style="font-size:12px;">
			正在查看:<span id="deptInfoMsg" class="color_red"></span>
			<select id="search_centerCd" class="select_box" onchange="myTask='';if_in_attention=false;IF_DEPT_IN_SELECT=true;doChangeCenter($(this).val());">
				<option value="">--请选择--</option>
				<c:set var="if_dept_in_select" value="false"></c:set>
				<c:forEach items="${orgMap}" var="map" >
					<optgroup label="${map.key}">
						<c:forEach items="${map.value}" var="map2" >
							<option value="${map2.key}" mytype="center" <c:if test="${map2.key==centerCd}">selected="selected"<c:set var="if_dept_in_select" value="true"></c:set></c:if>>${map2.key}</option>
								<c:forEach var="org" items="${map2.value}">
									<option value="${org.orgCd}" mytype="${map2.key}_dept" <c:if test="${org.orgCd==centerCd}">selected="selected"<c:set var="if_dept_in_select" value="true"></c:set></c:if>>&nbsp;&nbsp;${org.orgName}</option>
								</c:forEach>
						</c:forEach>
					</optgroup>
				</c:forEach>
				<option value="ifFromDaiban"<s:if test="ifFromDaiban"> selected="selected"</s:if>>我的待办事项</option>
			</select>
		</span>
		<span class="color_red" style="font-size:12px;" id="succInfoMsg"></span>
		<c:if test="${if_dept_in_select=='true'}"><script language="javascript">IF_DEPT_IN_SELECT = true;</script></c:if>
	</div>
	<div style="float:right; height:30px; margin-right:0px;">
		<input id="NewBtn_cutline" style="display:none;" type="button" class="button_new" onclick="newSchedule();" value="新建计划" />
		<input type="button" onclick="myTask='';if_in_attention=false;IF_DEPT_IN_SELECT=true;doChangeCenter($('#search_centerCd').val());" class="btn_green" value="刷新" />
		<input type="button" onclick="$('#if_search_all').val('1');centerClick();" class="btn_green" value="全部"/>
		<input type="button" onclick="window.open('${ctx}/plan/plan-work-center!getAllPlan.action?centerCd=${centerCd}');" class="btn_green" value="全屏" />
	</div>
</div>

<div class="search_bar" style="height:40px;">
	<table style="height:40px;width:100%;" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
			  <div>
				内容:<input type="text" onclick="select();" id="filter_LIKES_content" style="width:80px;" />
				状态:<select id="search_statusCd" class="select_box">
					<option value=""></option>
					<option value="1">进行中</option>
					<option value="2">预完成</option>
					<option value="3">完成</option>
					<option value="4">已删除</option>
					<option value="5">已隐藏</option>
					<option value="10">完成但曾过期</option>
					<option value="7">非本月</option>
					<option value="8">已过期</option>
					<option value="9">非考核性过期</option>
				</select>
				负责人:
				<input id="search_principalName" type="text" class="singleSelect" style="width:40px;"/>
				<input id="search_principal" type="hidden"  value="${principal}" />
				<span id="search_span_targetPointCd" style="display:none;">项目:<s:select list="mapXM" cssClass="select_box" listKey="key" listValue="value" id="search_targetPointCd"/></span>
				<input type="hidden" id="orderStr1"/>
				<input type="hidden" id="orderStr2"/>
				<input type="hidden" id="orderDir1"/>
				<input type="hidden" id="orderDir2"/>
				<input type="hidden" id="if_search_all" value="0"/>
				<span class="color_black">
					<select id="select_now_year" onchange="now_year=$(this).val();centerClick();" class="select_box">
						<option value="2011" <s:if test="now_year==2011">selected</s:if>>2011</option>
						<option value="2012" <s:if test="now_year==2012">selected</s:if>>2012</option>
						<option value="2013" <s:if test="now_year==2013">selected</s:if>>2013</option>
						<option value="2014" <s:if test="now_year==2014">selected</s:if>>2014</option>
						<option value="2015" <s:if test="now_year==2015">selected</s:if>>2015</option>
						<option value="2016" <s:if test="now_year==2016">selected</s:if>>2016</option>
						<option value="2017" <s:if test="now_year==2017">selected</s:if>>2017</option>
						<option value="2018" <s:if test="now_year==2018">selected</s:if>>2018</option>
						<option value="2019" <s:if test="now_year==2019">selected</s:if>>2019</option>
						<option value="2020" <s:if test="now_year==2020">selected</s:if>>2020</option>
					</select>
					年
					<button id="month1"  onClick="monthClick(1)"  <s:if test="now_month==1">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>1</button><button id="month2"  onClick="monthClick(2)"  <s:if test="now_month==2">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>2</button><button id="month3"  onClick="monthClick(3)"  <s:if test="now_month==3">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>3</button><button id="month4"  onClick="monthClick(4)"  <s:if test="now_month==4">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>4</button><button id="month5"  onClick="monthClick(5)"  <s:if test="now_month==5">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>5</button><button id="month6"  onClick="monthClick(6)"  <s:if test="now_month==6">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>6</button><button id="month7"  onClick="monthClick(7)"  <s:if test="now_month==7">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>7</button><button id="month8"  onClick="monthClick(8)"  <s:if test="now_month==8">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>8</button><button id="month9"  onClick="monthClick(9)"  <s:if test="now_month==9">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>9</button><button id="month10" onClick="monthClick(10)" <s:if test="now_month==10">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>10</button><button id="month11" onClick="monthClick(11)" <s:if test="now_month==11">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>11</button><button id="month12" onClick="monthClick(12)" <s:if test="now_month==12">class="month_big color_red"</s:if><s:else>class="month_small"</s:else>>12</button>
					月
				</span>
				&nbsp;
	 			<input type="button" class="btn_green" onclick="$('#pageNo').val(1);centerClick();" value="搜索" />
				</div>
			</td>
		</tr>
	</table>
</div>
<div id="scheduleDiv" style="overflow-x:hidden;">
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
			<security:authorize ifAnyGranted="A_PLAN_CENTER_ADMIN,A_PLAN_CENTER_USER">
				<div class="btn_cutline_3_26"></div>
				<div class="btn_bottom_bar" onclick="doExchangeOrder();">交换顺序</div>
				<div class="btn_cutline_3_26"></div>
				<security:authorize ifAnyGranted="A_PLAN_CENTER_ADMIN,A_PLAN_CENTER_USER">
					<div class="btn_bottom_bar" onclick="doUpdateStatusAll(4);">批量删除</div>
					<div class="btn_cutline_3_26"></div>
				</security:authorize>
				<div class="btn_bottom_bar" onclick="doUpdateStatusAll(1);">批量退回</div>
				<div class="btn_cutline_3_26"></div>
				<security:authorize ifAnyGranted="A_PLAN_CENTER_ADMIN,A_PLAN_CENTER_USER">
					<div class="btn_bottom_bar" onclick="doUpdateStatusAll(3);">批量完成</div>
					<div class="btn_cutline_3_26"></div>
				</security:authorize>
				<security:authorize ifAnyGranted="A_PLAN_CENTER_ADMIN,A_PLAN_CENTER_USER">
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
