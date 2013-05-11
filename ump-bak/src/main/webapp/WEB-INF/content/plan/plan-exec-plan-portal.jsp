<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.hhz.core.utils.DateOperator;"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/global.jsp"%>
		<title>项目开发计划</title>
		
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/plan.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/plan-exec-plan-stat.css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/plan-exec-plan.css"/>
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/userSelection.css" />
		
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js" ></script>
		<script type="text/javascript" src="${ctx}/js/oa/userSelection.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/plan/plan-exec-plan.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
		
	</head>
	
	<%--
		说明: 本页面作为计划的入口,链接地址分别是:
		 前期计划 /plan/plan-exec-plan!portal.action?planTypeCd=0 前期计划
		 执行计划 /plan/plan-exec-plan!portal.action?planTypeCd=1 执行计划(不带参数默认)
	 --%>

	<body>
		<!-- 隐藏域,很重要 -->
		<input type="hidden" id="curProjectCd"  value="${projectCd}"/>
		<input type="hidden" id="curPlanTypeCd" value="${planTypeCd}"/>
		<input type="hidden" id="curExecPlanMainId"/>
		
		<!-- 宽度 -->
		<input type="hidden" id="curDocWidth"/>
		<input type="hidden" id="curDocHeight"/>
	 	<script type="text/javascript">
	 		setCurDocWidth($(document).width());
	 		setCurDocHeight($(document).height());
		</script>
		
	     <div id="portalTopDiv" class="title_bar_plan">
			<div class="title_bar_h">
				<%--
				--%>
				<div style="float:left;color:#0167A2;;padding:3px 5px 0 2px;" >
					<div id="curPlanTypeDescPre"  style='float:left; display: <s:if test="planTypeCd == 0">block;</s:if><s:else>none</s:else>;'><a style="color: #0167A2;" href="javascript: switchProject(null,getCurProjectCd());">前期计划</a></div>
					<div id="curPlanTypeDescExec" style='float:left; display: <s:if test="planTypeCd == 1">block;</s:if><s:else>none</s:else>;'><a style="color: #0167A2;" href="javascript: switchProject(null,getCurProjectCd());">执行计划</a></div>
					<div id="curPlanTypeDescCost" style='float:left; display: <s:if test="planTypeCd == 2">block;</s:if><s:else>none</s:else>;'>成本计划</div>
				</div>
				<div style="float:left; padding-left:15px;">
					<button class="btn_green" onClick="switchProject(null,getCurProjectCd());">搜索</button>
				</div>
				<div style="float:left;padding-left:5px;">
					主责方
					<s:select cssStyle="width:100px;display:none;" title="选择 前期计划 主责方" list="resOrgListPre" id="resOrgListCd_select_pre" name="resOrgListCd_select_pre" listKey="entityCd" listValue="entityName" emptyOption="true" onchange="resOrgChange();" />
					<s:select cssStyle="width:100px;display:none;" title="选择执行计划主责方"  list="resOrgListExec" id="resOrgListCd_select_exec" name="resOrgListCd_select_exec" listKey="entityCd" listValue="entityName" emptyOption="true" onchange="resOrgChange();" />
					<s:select cssStyle="width:100px;display:none;" label="选择成本计划主责方"  list="resOrgListCost" id="resOrgListCd_select_cost" name="resOrgListCd_select_cost" listKey="entityCd" listValue="entityName" emptyOption="true" onchange="resOrgChange();" />
				</div>
				<div style="float:left; padding-left:15px; cursor:pointer;" class="color_blue" onClick="$('#searchWindowDiv').show();">高级搜索</div>前期计划/执行计划
				<div style="float:left;padding-top:3px;font-size: 12px;padding-left:5px;display:none;">
					<s:if test="planTypeCd == 1 || planTypeCd == 0 ">看
						<span onclick="javascript:changePlanTypeCd();" id="planTypeDescChg" style="cursor: pointer;color:#0167A2;">
							<s:if test="planTypeCd == 1">前期计划</s:if>
							<s:if test="planTypeCd == 0">执行计划</s:if>
						</span>
					</s:if>
				</div>
				<div style="float:left; font-weight:bolder; font-size:14px; color:#333;">
				<s:if test="planTypeCd!=5">
					项目开发计划&nbsp;
				</s:if><s:else>
					项目设计计划&nbsp;
				</s:else>
				</div>
				<div style="float:left;">
				<s:select id="typecd_select" list="mapProjectsType"  listKey="key" listValue="value"  name="typeCd" cssStyle="float:left; width:100px;" onchange="projectChange();"/>
				</div>
			</div>
			<div class="title_bar_ops">
				<%--
				<div class="btn_cutline_3_26"></div>
				<div class="btn_over_white" style="float:left" onclick="projectWorkPlan();" title="查看项目业态">
					项目进度
				</div>
				<div class="btn_cutline_3_26"></div>
				<div class="btn_over_white" style="float:left" onclick="projectExecStat();" title="查看项目业态进度">
					业态进度
				</div>
				<div class="btn_cutline_3_26"></div>
				<div class="btn_over_white" style="float: left;" onclick="viewControlPlans();" title="查看控制计划">
					控制计划
				</div>
					<div class="btn_cutline_3_26"></div>
					<div class="btn_over_white" style="float: left;" onclick="batchConfirm();" title="批量确认计划日期">
						批量确认
					</div>
				    <div class="btn_cutline_3_26"></div>
					<div class="btn_over_white" style="float: left;" onclick="configNodes();" title="管理项目节点">
						管理节点
					</div>
					<div class="btn_cutline_3_26"></div>
					<div class="btn_over_white" style="float: left;" onclick="mngProjReminder();" title="查看项目联系人">
						项目联系人
					</div>
				 --%>
				<s:if test="aExecAdmin">
				</s:if>
					<div class="btn_cutline_3_26"></div>
					<div class="project_process" style="float:left" onclick="exportExecPlanStat();" title="导出项目执行统计">
						导出统计
					</div>
					<div class="btn_cutline_3_26"></div>
					<div class="btn_over_white" style="float:left" onclick="exportExecPlanDetail();" title="导出项目执行明细">
						导出明细
					</div>
				    <div class="btn_cutline_3_26"></div>
					<div class="btn_over_white" style="float: left;" onclick="configPlan();" title="查看业态">
						配置业态
					</div>
				<div class="btn_cutline_3_26"></div>
				<div class="btn_full_white" style="float: left;" onclick="window.open('${ctx}/plan/plan-exec-plan!portal.action?projectCd=' + getCurProjectCd() +'&planTypeCd=' + getPlanTypeCd())">
				 	&nbsp;&nbsp;&nbsp;&nbsp;全屏
				</div>
			</div>
		</div>
		<div id="portalTopBtnDiv" style="width:100%; height:32px; padding-top:5px;">
			<s:if test="planTypeCd!=5">
				<div id="btnPointLevel1" <s:if test="planTypeCd==5">class="btn_white"</s:if><s:else>class="btn_blue"</s:else> onclick="onClickCheckTreeType(1)">一级</div>
				<div id="btnPointLevel2" class="btn_white" onclick="onClickCheckTreeType(2)">二级</div>
				<div id="btnPointLevel3" class="btn_white" onclick="onClickCheckTreeType(3)">三级</div>
			</s:if><s:else>
				<div id="btnPointLevel5" <s:if test="planTypeCd==5">class="btn_blue"</s:if><s:else>class="btn_white"</s:else> style="width:100px;">设计计划</div>
			</s:else>
			<div style="float:left; width:15px; height:36px;"></div>
			<s:if test="planTypeCd!=5">
			<div id="btnViewStyle1" class="btn_blue" onclick="onClickViewStyle(1)" title="只显示过期、计划完成时间在一个月内、计划开始时间在两个月内的节点">精简</div>
			<div id="btnViewStyle2" class="btn_white" onclick="onClickViewStyle(2)" title="显示所有节点">完整</div>
			</s:if>
			<div style="float:left; width:15px; height:36px;"></div>
			<div style="float:left; margin-left:5px; margin-top:5px;"><button type="button" class="btn_blue_65_20" onclick="$('#plansWindowDiv').toggle();">选择业态</button></div>
			<div style="float:left; margin-left:5px; margin-top:5px;"><button type="button" class="btn_green_65_20" onclick="$('#searchWindowDiv').toggle();">高级搜索</button></div>
			<div style="float:left; width:15px; height:36px;"></div>
			<%--
			<div style="float:left; margin-left:5px; margin-top:5px;"><button type="button" class="btn_blue_65_20" onclick="">查看网批</button></div>
			--%>
		</div>
	    <div id="planContainerDiv" class="plan_container" style="width:100%;"></div>
		<div id="searchWindowDiv" style="position:absolute; width:300px; height:110px; top:90px; left:0px; display:none; background-color:#fff; border:1px solid #000; padding:5px;">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" style="line-height:26px;">
				<tr>
					<td align="right">计划开始时间：</td>
					<td align="left"><input type="text" id="filter_GED_scheduleStartDate" onfocus="WdatePicker()" style="width:85px;"/> 至 <input type="text" id="filter_LED_scheduleStartDate" onfocus="WdatePicker()" style="width:85px;"/></td>
				</tr>
				<tr>
					<td align="right">计划完成时间：</td>
					<td align="left"><input type="text" id="filter_GED_scheduleEndDate" onfocus="WdatePicker()" style="width:85px;"/> 至 <input type="text" id="filter_LED_scheduleEndDate" onfocus="WdatePicker()" style="width:85px;"/></td>
				</tr>
				<tr>
					<td align="right">实际完成时间：</td>
					<td align="left"><input type="text" id="filter_GED_realEndDate" onfocus="WdatePicker()" style="width:85px;"/> 至 <input type="text" id="filter_LED_realEndDate" onfocus="WdatePicker()" style="width:85px;"/></td>
				</tr>
				<tr>
					<td align="right">状态：</td>
					<td align="left">
						<select id="search_status">
							<option value="999">--</option>
							<option value="100">未确认</option>
							<option value="0">确认</option>
							<option value="2">完成</option>
							<option value="101">过期</option>
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="btn_green" onClick="ifProjectChange=false;switchProject(null,getCurProjectCd());$('#searchWindowDiv').hide();">搜索</button>
						&nbsp;&nbsp;
						<button class="btn_green" onClick="$('#searchWindowDiv').hide();">关闭</button>
					</td>
				</tr>
			</table>
		</div>
		<div id="resOrgNameWindowDiv" style="position:absolute; width:200px; height:280px; top:90px; left:200px; display:none; background-color:#fff; border:1px solid #000;">
			<div style="margin:5px;">
				<div style="height:22px; width:100%; padding-left:30px;">
					<button class="btn_green" onClick="ifProjectChange=false;switchProject(null,getCurProjectCd());$('#resOrgNameWindowDiv').hide();">搜索</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn_green" onClick="$('#resOrgNameWindowDiv').hide();">关闭</button>
				</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_1" class="js_resOrgName" myid="地产公司" if_active="0">&nbsp;&nbsp;地产公司</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_2" class="js_resOrgName" myid="宝龙商业" if_active="0">&nbsp;&nbsp;宝龙商业</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_3" class="js_resOrgName" myid="宝龙行业" if_active="0">&nbsp;&nbsp;宝龙行业</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_4" class="js_resOrgName" myid="酒店开发中心" if_active="0">&nbsp;&nbsp;酒店开发中心</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_5" class="js_resOrgName" myid="人力资源管理中心" if_active="0">&nbsp;&nbsp;人力资源管理中心</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_6" class="js_resOrgName" myid="成本控制中心" if_active="0">&nbsp;&nbsp;成本控制中心</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_7" class="js_resOrgName" myid="规划设计中心" if_active="0">&nbsp;&nbsp;规划设计中心</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_8" class="js_resOrgName" myid="投资发展中心" if_active="0">&nbsp;&nbsp;投资发展中心</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_9" class="js_resOrgName" myid="区域项目管理部" if_active="0">&nbsp;&nbsp;区域项目管理部</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:14px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_10" class="js_resOrgName" myid="区域技术管理部" if_active="0">&nbsp;&nbsp;区域技术管理部</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_11" class="js_resOrgName" myid="区域成本管理部" if_active="0">&nbsp;&nbsp;区域成本管理部</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_12" class="js_resOrgName" myid="区域行政管理部" if_active="0">&nbsp;&nbsp;区域行政管理部</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_13" class="js_resOrgName" myid="区域营销管理部" if_active="0">&nbsp;&nbsp;区域营销管理部</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_14" class="js_resOrgName" myid="区域投资管理部" if_active="0">&nbsp;&nbsp;区域投资管理部</div>
			</div>
		</div>
		<div id="plansWindowDiv" style="position:absolute; width:200px; height:300px; top:90px; left:420px; display:none; background-color:#fff; border:1px solid #000;"></div>
<script type="text/javascript">
	$(function(){
		initPlanContainerHeight();
		var projectCd ="${projectCd}";
		if(projectCd!=""){
			$("#typecd_select").val(projectCd);
		}
		//projectChange();
		setPlanTypeCd("${planTypeCd}");
		$("#searchWindowDiv").css("left",($(document).width()-500)/2);
		ifProjectChange = true;
		switchProject(null, getCurProjectCd());
	});
</script>
		<form id="downloadForm" name="downloadForm" target="_blank">
			<input type="hidden" name="bizModuleCd"></input>
			<input type="hidden" name="fileName"></input>
			<input type="hidden" name="realFileName"></input>
		</form>
	</body>
</html>


