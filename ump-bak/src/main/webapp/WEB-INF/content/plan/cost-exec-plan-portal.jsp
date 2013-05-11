<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.hhz.core.utils.DateOperator;"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/global.jsp"%>
		<title>成本计划</title>
		
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/plan.css"/>
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/cost-exec-plan.css"/>
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/userSelection.css" />
		
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js" ></script>
		<script type="text/javascript" src="${ctx}/js/oa/userSelection.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/plan/cost-exec-plan.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
		
	</head>
	
	<%--
		说明: 本页面作为计划的入口,链接地址分别是:
		 成本计划 /plan/cost-execution-plan!portal.action?planTypeCd=2
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
		
	     <div class="title_bar_plan" id="portalTopDiv">
			<div class="title_bar_h">
				<div style="float:left;;padding:3px 5px 0 2px;" >
					<div id="curPlanTypeDescCost" style="float:left;cursor:pointer;color: #0167A2;" onclick="switchProject(null,getCurProjectCd());">成本计划</div>
				</div>
				<s:select id="typecd_select" list="mapProjectsType"  listKey="key" listValue="value"  name="typeCd" cssStyle="float:left; width:100px;" onchange="projectChange();"/>
				
				<div style="float:left;padding-left:5px;">
					主责方
					<s:select cssStyle="width:100px;display:none;" label="选择成本计划主责方"  list="resOrgListCost" id="resOrgListCd_select_cost" name="resOrgListCd_select_cost" listKey="entityCd" listValue="entityName" emptyOption="true" onchange="resOrgChange();" />
				</div>
				
			</div>
	
			
			<div class="title_bar_ops">
			    <div class="btn_cutline_3_26"></div>
				<div class="project_process" style="float:left" onclick="projectExecStat();" title="查看项目业态进度">
					业态进度
				</div>
				<s:if test="superAdmin">
					
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
					<div class="config_opertype" style="float: left;" onclick="configPlanSearch();" title="查看业态">
						查看业态
					</div>
				</s:if>
				<div class="btn_cutline_3_26"></div>
				<div class="manager_plan" style="float: left;" onclick="viewCostControlPlans();" title="查看成本控制计划">
					控制计划
				</div>
				<div class="btn_cutline_3_26"></div>
				<div class="full_screen" style="float: left;" onclick="switchFullScreen()">
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
	</body>
</html>


