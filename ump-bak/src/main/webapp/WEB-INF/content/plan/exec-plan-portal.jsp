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
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/exec-plan-stat.css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/exec-plan.css"/>
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/userSelection.css" />
		
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js" ></script>
		<script type="text/javascript" src="${ctx}/js/oa/userSelection.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/plan/exec-plan.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	</head>
	<body style="overflow:visible;" id="planBody">
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
				<div style="float:left; font-weight:bolder; font-size:14px; color:#333;">
				<s:if test="if_bis">
					商业执行计划&nbsp;
				</s:if>
				<s:elseif test="planTypeCd!=5">
					项目开发计划&nbsp;
				</s:elseif>
				<s:else>
					项目设计计划&nbsp;
				</s:else>
				</div>
				<div style="float:left;" 
					<s:if test="planTypeCd==24 && myUiid!='wanghui6'">
					<security:authorize ifAnyGranted="A_EXEC_BIS_PROJECT">disabled=""</security:authorize>
					</s:if> >
					<s:select id="typecd_select" cssClass="select_box" list="mapProjectsType"  listKey="key" listValue="value"  name="typeCd"  onchange="projectChange();"/>
				</div>
			</div>
			<div class="title_bar_ops">
				<s:if test="aExecAdmin && !(planTypeCd==10 || planTypeCd==11)">
					<!--<div class="project_process" style="float:left" onclick="exportExecPlanStat();" title="导出项目执行统计">
						导出统计
					</div> -->
					<s:if test="aExecAdmin && !(planTypeCd==10 || planTypeCd==11 || planTypeCd==14 || planTypeCd==15 || planTypeCd==16)">
					  <input type="button" class="button_blue" onclick="configPlan();" title="配置业态" value="配置业态"/>
						
					</s:if>
					<input type="button" class="button_green" onclick="exportExecPlanDetail();" title="导出项目执行明细" value="导出"/>
				
				</s:if>
				<input type="button" class="button_green" onclick="window.open('${ctx}/plan/exec-plan!portal.action?projectCd=' + getCurProjectCd() +'&planTypeCd=' + getPlanTypeCd()+'&if_bis=${if_bis}');" value="全屏"/>
				
			</div>
		</div>
		<s:if test="planTypeCd==1">
		<div style="position: fixed; right: 235px; top: 8px;" onclick="moveleft();" title="选择公司点击向左移"><img src="${ctx}/resources/images/desk/note/left_blueback.gif" /></div>
		<div style="position: fixed; right: 205px; top: 8px;" onclick="moveRight();" title="选择公司点击向右移"><img src="${ctx}/resources/images/desk/note/right_blueback.gif" /></div>
		</s:if>
		<div id="portalTopBtnDiv" style="width:100%; height:32px; padding-top:5px;">
			<s:if test="planTypeCd==1">
				<input type="button" id="btnPointLevel1" <s:if test="planTypeCd==5">class="btn_white"</s:if><s:else>class="button_blue"</s:else> onclick="onClickCheckTreeType(1)" value="一级" />
				<input type="button" id="btnPointLevel2" class="btn_white" onclick="onClickCheckTreeType(2)" value="二级"/>
				<input type="button" id="btnPointLevel3" class="btn_white" onclick="onClickCheckTreeType(3)" value="三级"/>
			</s:if><s:elseif test="planTypeCd==5">
				<div id="btnPointLevel5" <s:if test="planTypeCd==5">class="button_blue"</s:if><s:else>class="btn_white"</s:else> style="width:100px;">设计计划</div>
			</s:elseif><s:elseif test="planTypeCd==10 || planTypeCd==11 || planTypeCd==14 || planTypeCd==15 || planTypeCd==16">
				<div id="btnPointLevel14" <s:if test="planTypeCd==14">class="button_blue"</s:if><s:else>class="btn_white"</s:else> style="width:100px;" onclick="onClickCheckTreeType(14)">四级计划</div>
				<div id="btnPointLevel15" <s:if test="planTypeCd==15">class="button_blue"</s:if><s:else>class="btn_white"</s:else> style="width:100px;" onclick="onClickCheckTreeType(15)">开业筹备五级</div>
				<div id="btnPointLevel16" <s:if test="planTypeCd==16">class="button_blue"</s:if><s:else>class="btn_white"</s:else> style="width:100px;" onclick="onClickCheckTreeType(16)">招商五级</div>
				<div id="btnPointLevel10" <s:if test="planTypeCd==10">class="button_blue"</s:if><s:else>class="btn_white"</s:else> style="width:100px;" onclick="onClickCheckTreeType(10)">汇总</div>
			</s:elseif><s:elseif test="planTypeCd==24" >
				<input type="button" id="btnPointLevel14" <s:if test="planTypeCd==24">class="button_blue"</s:if><s:else>class="btn_white"</s:else> style="width:100px;" onclick="onClickCheckTreeType(24)" value="四级计划"/>
			</s:elseif>
			<s:if test="planTypeCd!=5">
			<input type="button" id="btnViewStyle1" <s:if test="now_view_style==1">class="button_blue"</s:if><s:else>class="btn_white"</s:else> onclick="onClickViewStyle(1)" title="只显示过期、计划完成时间在一个月内、计划开始时间在两个月内的节点" value="精简"/>
			<input type="button" id="btnViewStyle2" <s:if test="now_view_style==2">class="button_blue"</s:if><s:else>class="btn_white"</s:else> onclick="onClickViewStyle(2)" title="显示所有节点" value="完整"/>
			</s:if>
			<s:if test="planTypeCd==1 || planTypeCd==2 || planTypeCd==3 || planTypeCd==5">
				<input type="button" class="button_green" onclick="$('#plansWindowDiv').toggle();" value="选择业态"/>
			</s:if>
			 <input type="button" class="button_grey" onclick="$('#searchWindowDiv').toggle();"value="高级搜索"/>
		</div>
	    <div id="planContainerDiv" <s:if test="planTypeCd==1 || planTypeCd==2 || planTypeCd==3 || planTypeCd==5">class="plan_container"</s:if><s:elseif test="planTypeCd==10 || planTypeCd==11 || planTypeCd==14 || planTypeCd==15 || planTypeCd==16">class="plan_container2"</s:elseif> style="width:100%;padding-top:6px; overflow-x: auto;"></div>
		<div id="searchWindowDiv" style="position:absolute; width:350px; height:150px; top:90px; left:0px; display:none; background-color:#fff; border:1px solid #000; padding:5px;">
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
						<select id="search_status" class="select_box">
							<option value="999">--</option>
							<option value="100">未确认</option>
							<option value="0">确认</option>
							<option value="2">完成</option>
							<option value="101">过期</option>
							<option value="102">完成曾过期</option>
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				<td></td>
				 <td>
				  <input type="button" class="button_blue" onClick="ifProjectChange=false;switchProject(null,getCurProjectCd());$('#searchWindowDiv').hide();" value="搜索"/>
						&nbsp;&nbsp;
						<input type="button" class="button_red" onClick="$('#searchWindowDiv').hide();" value="取消"/>
				 </td>
				</tr>
			</table>
		</div>
		<div id="resOrgNameWindowDiv" style="position:absolute; width:200px; height:320px; top:90px; left:200px; display:none; background-color:#fff; border:1px solid #000;">
			<div style="margin:8px;">
				<div style="height:25px; width:100%; padding-left:5px;">
					<button class="btn_green" onClick="ifProjectChange=false;switchProject(null,getCurProjectCd());$('#resOrgNameWindowDiv').hide();">搜索</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn_red" onClick="$('#resOrgNameWindowDiv').hide();">取消</button>
				</div>
				<s:if test="planTypeCd==1 || planTypeCd==2 || planTypeCd==3 || planTypeCd==5">
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_5" class="js_resOrgName" myid="人力资源管理中心" if_active="0">&nbsp;&nbsp;人力资源管理中心</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:14px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_10" class="js_resOrgName" myid="投资发展中心" if_active="0">&nbsp;&nbsp;投资发展中心</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_9" class="js_resOrgName" myid="设计管理中心" if_active="0">&nbsp;&nbsp;设计管理中心</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_9" class="js_resOrgName" myid="营销管理中心" if_active="0">&nbsp;&nbsp;营销管理中心</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_9" class="js_resOrgName" myid="项目管理中心" if_active="0">&nbsp;&nbsp;项目管理中心</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_6" class="js_resOrgName" myid="成本控制中心" if_active="0">&nbsp;&nbsp;成本控制中心</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_11" class="js_resOrgName" myid="营运管理中心" if_active="0">&nbsp;&nbsp;营运管理中心</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_4" class="js_resOrgName" myid="酒店开发中心" if_active="0">&nbsp;&nbsp;酒店开发中心</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_1" class="js_resOrgName" myid="地产公司" if_active="0">&nbsp;&nbsp;地产公司</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_2" class="js_resOrgName" myid="商业集团" if_active="0">&nbsp;&nbsp;商业集团</div>
				
				</s:if><s:elseif test="planTypeCd==24">
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_11" class="js_resOrgName" myid="行政人事部" if_active="0">&nbsp;&nbsp;行政人事部</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_12" class="js_resOrgName" myid="财务部" if_active="0">&nbsp;&nbsp;财务部</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_1" class="js_resOrgName" myid="招商中心" if_active="0">&nbsp;&nbsp;招商中心</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_8" class="js_resOrgName" myid="营运中心" if_active="0">&nbsp;&nbsp;营运中心</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_7" class="js_resOrgName" myid="总工办" if_active="0">&nbsp;&nbsp;总工办</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_5" class="js_resOrgName" myid="企划部" if_active="0">&nbsp;&nbsp;企划部</div>
				<div style="cursor:pointer;	height:16px; line-height:16px; width:100%; font-size:12px; margin-bottom:2px;" onclick="clickPlanBtn($(this))" id="searchResOrgName_10" class="js_resOrgName" myid="商业公司" if_active="0">&nbsp;&nbsp;商业公司</div>
				</s:elseif>
			</div>
		</div>
		<div id="plansWindowDiv" style="position:absolute; width:300px; height:300px; top:90px; left:320px; display:none; background-color:#fff; border:1px solid #000;"></div>
<script type="text/javascript">
	$(function(){
		if_bis = "${if_bis}";
		now_view_style = "${now_view_style}";
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
		//autoHeight();	
	});
</script>
		<form id="downloadForm" name="downloadForm" target="_blank">
			<input type="hidden" name="bizModuleCd"></input>
			<input type="hidden" name="fileName"></input>
			<input type="hidden" name="realFileName"></input>
		</form>
	</body>
</html>


