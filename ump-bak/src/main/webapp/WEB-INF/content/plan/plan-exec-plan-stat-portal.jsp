<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/global.jsp"%>
		<title>执行计划-项目业态节点完成情况</title>
		
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/plan.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/plan-exec-plan-stat.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/userSelection.css" />
		
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js" ></script>
		<script type="text/javascript" src="${ctx}/js/oa/userSelection.js" ></script>
		<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/plan/plan-exec-plan-stat.js"></script>
		
	</head>

	<body>
		<!-- 全局变量 -->
		<input type="hidden" id="curCenterDiv" value="${centerCd}"/>
		<input type="hidden" id="curProjDiv" value="${projectCd}"/>
		<input type="hidden" id="curOperationId" value="${operationId}"/>
		<input type="hidden" id="curPlanTypeCd" value="${planTypeCd}"/>
		
		 
		<!-- 标题 -->
		<div class="title_bar">
			<%--
			<div class="title_bar_icon"><img src="${ctx}/pics/exec_plan_logo.gif" /></div>
			 --%>
			 
			<div style="float: left;padding-left:5px; font-size: 14px;">
				 [<s:if test="planTypeCd == 1">执行计划</s:if><s:else>前期计划</s:else>]
			</div>
			<div class="title_bar_h" style="float: left;">
				各业态节点完成情况:
			</div>
			<div style="float: left;font-size: 12px;color: #0167A2;">
				<p:code2name mapCodeName="mapProjectCdName" value="projectCd"/>
			</div>
			<div class="full_screen" style="float:right;"  onclick="switchFullScreen();">
				全屏
			</div>
		</div>
		 
		<!-- 项目列表:导航、项目 -->
		<div class="nav_bar" style="display: none;">
			<div id="nav_scroll_btns_projects" class="nav_scroll_btns" style="float: right;">
				<div class="nav_left"  style="float: left;" onclick="scrollNavBtn('left');" title="向左">
				 	&nbsp;
				</div>
				<div class="nav_right" style="float: left;" onclick="scrollNavBtn('right');" title="向右">
				 	&nbsp;
				</div>
			</div>
			<div id="nav_btns_container_projects" class="nav_btns_container">
				<ul id="nav_btns_projects" class="nav_btns">
					<s:iterator value="projects">
						<li>
							<div onclick="switchProject($(this), '${orgCd}')" id="projectCd_${orgCd}" <c:if test="${orgCd == projectCd}">class="active"</c:if>>${orgName}</div>
						</li>
						<li>
							<div class="spliter"></div>
						</li>
					</s:iterator>
				</ul>
			</div>
			
		 	<script language="javascript">
				//默认当前项目
				var projectCd = "${projectCd}";
				//alert("projectCd="+projectCd);
				if(projectCd != ''){
					isLoading = false;
					$("#projectCd_" + projectCd).trigger("click");
				}
				
		 	</script>
		</div>
		
		<!-- 业态列表 -->
		<div class="nav_bar">
			<div id="nav_scroll_btns_operation" class="nav_scroll_btns" style="float: right;">
				<div class="nav_left"  style="float: left;" onclick="scrollNavBtnOperation('left');" title="向左">
				 	&nbsp;
				</div>
				<div class="nav_right" style="float: left;" onclick="scrollNavBtnOperation('right');" title="向右">
				 	&nbsp;
				</div>
			</div>
			
			<div id="nav_btns_container_operations" class="nav_btns_container">
				 
			</div>
		</div>
			
		<!-- 某项目某业态统计明细 -->
		<div id="planContainerDiv" class="plan_container" style="float:left;overflow: auto;">
			
		</div>
		
		<%--
		<div style="clear: both;">
	 		<div class="corner_bottom_left"></div>
	 		<div class="corner_bottom_right"></div>
	 		<div class="corner_bottom_center"></div>
	 	</div>
		 --%>
	</body>
</html>