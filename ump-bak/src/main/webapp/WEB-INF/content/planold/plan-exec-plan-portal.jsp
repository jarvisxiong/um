<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.hhz.core.utils.DateOperator;"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/global.jsp"%>
		<title>执行计划</title>
		
		
	</head>
	
	<%--
		说明: 本页面作为计划的入口,链接地址分别是:
		 前期计划 /planold/plan-exec-plan!portal.action?planTypeCd=0 前期计划
		 执行计划 /planold/plan-exec-plan!portal.action?planTypeCd=1 执行计划(不带参数默认)
	 --%>

	<body>
  		<span style="position:absolute; left:100px; top:100px; font-size:24px; font-weight:bolder;" class="color_red">
  			项目设计计划正在开发中。。。。。。
  		</span>
  		<%--
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/planold/plan.css"/>
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/planold/plan-exec-plan.css"/>
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/userSelection.css" />
		
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js" ></script>
		<script type="text/javascript" src="${ctx}/js/oa/userSelection.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/planold/plan-exec-plan.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
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
		
	     <div class="title_bar_plan" id="portalTopDiv">
			<div class="title_bar_h">
				<div style="float:left;color:#0167A2;;padding:3px 5px 0 2px;" >
					<div id="curPlanTypeDescPre"  style='float:left; display: <s:if test="planTypeCd == 0">block;</s:if><s:else>none</s:else>;'><a style="color: #0167A2;" href="javascript: switchProject(null,getCurProjectCd());">前期计划</a></div>
					<div id="curPlanTypeDescExec" style='float:left; display: <s:if test="planTypeCd == 1">block;</s:if><s:else>none</s:else>;'><a style="color: #0167A2;" href="javascript: switchProject(null,getCurProjectCd());">执行计划</a></div>
					<!--
					<div id="curPlanTypeDescCost" style='float:left; display: <s:if test="planTypeCd == 2">block;</s:if><s:else>none</s:else>;'>成本计划</div>
					 -->
				</div>
				<s:select id="typecd_select" list="mapProjectsType"  listKey="key" listValue="value"  name="typeCd" cssStyle="float:left; width:100px;" onchange="projectChange();"/>
				
				<div style="float:left;padding-left:5px;">
					主责方
					<s:select cssStyle="width:100px;display:none;" title="选择 前期计划 主责方" list="resOrgListPre" id="resOrgListCd_select_pre" name="resOrgListCd_select_pre" listKey="entityCd" listValue="entityName" emptyOption="true" onchange="resOrgChange();" />
					<s:select cssStyle="width:100px;display:none;" title="选择执行计划主责方"  list="resOrgListExec" id="resOrgListCd_select_exec" name="resOrgListCd_select_exec" listKey="entityCd" listValue="entityName" emptyOption="true" onchange="resOrgChange();" />
					
					<s:select cssStyle="width:100px;display:none;" label="选择成本计划主责方"  list="resOrgListCost" id="resOrgListCd_select_cost" name="resOrgListCd_select_cost" listKey="entityCd" listValue="entityName" emptyOption="true" onchange="resOrgChange();" />
					 
				</div>
				
				
				<div style="float:left;padding-top:3px;font-size: 12px;padding-left:5px;">
					<s:if test="planTypeCd == 1 || planTypeCd == 0 ">看
						<span onclick="javascript:changePlanTypeCd();" id="planTypeDescChg" style="cursor: pointer;color:#0167A2;">
							<s:if test="planTypeCd == 1">前期计划</s:if>
							<s:if test="planTypeCd == 0">执行计划</s:if>
						</span>
					</s:if>
				</div>
			</div>
			
			<div class="title_bar_ops">
			
				<div class="btn_cutline_3_26"></div>
				<div class="project_process" style="float:left" onclick="projectWorkPlan();" title="查看项目业态">
					项目进度
				</div>
				<div class="btn_cutline_3_26"></div>
				<div class="project_process" style="float:left" onclick="projectExecStat();" title="查看项目业态进度">
					业态进度
				</div>
					
				<s:if test="superAdmin">
				
					<div class="btn_cutline_3_26"></div>
					<div class="project_process" style="float:left" onclick="exportExecPlanStat();" title="导出项目执行统计">
						导出
					</div>
					
					<div class="btn_cutline_3_26"></div>
					<div class="btn_over_white" style="float:left" onclick="exportExecPlanDetail();" title="导出项目执行明细">
						导出明细
					</div>
					
				    <div class="btn_cutline_3_26"></div>
					<div class="manager_node" style="float: left;" onclick="configNodes();" title="管理项目节点">
						管理节点
					</div>
					<div class="btn_cutline_3_26"></div>
				
					<div class="batch_confirm" style="float: left;" onclick="batchConfirm();" title="批量确认计划日期">
						批量确认
					</div>
					<div class="btn_cutline_3_26"></div>
					<div class="link_responser" style="float: left;" onclick="mngProjReminder();" title="查看项目联系人">
						项目联系人
					</div>
				    <div class="btn_cutline_3_26"></div>
					<div class="config_opertype" style="float: left;" onclick="configPlan();" title="查看业态">
						配置业态
					</div>
				</s:if>
				<div class="btn_cutline_3_26"></div>
				<div class="manager_plan" style="float: left;" onclick="viewControlPlans();" title="查看控制计划">
					控制计划
				</div>
				<div class="btn_cutline_3_26"></div>
				<div class="full_screen" style="float: left;" onclick="window.open('${ctx}/planold/plan-exec-plan!portal.action?projectCd=' + getCurProjectCd() +'&planTypeCd=' + getPlanTypeCd())">
				 	全屏
				</div>
			</div>
			
		</div>
	     <div id="planContainerDiv" class="plan_container" style="overflow: auto;">
			
		</div>
		
	 	<script type="text/javascript">
 			initPlanContainerHeight();
			var projectCd ="${projectCd}";
			if(projectCd!=""){
				$("#typecd_select").val(projectCd);
			}
			projectChange();
			setPlanTypeCd("${planTypeCd}");
		</script>
		 --%>
	</body>
</html> 