<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="java.util.Date"%>
<%@page import="com.hhz.core.utils.DateOperator"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/global.jsp" %>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/planWork.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/userChoose.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/plan.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/plan/execution-plan.css" />

	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/plan/centerQueryIndex.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/plan/planWork2.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/plan/centerQueryIndex.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/userChoose.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script language="javascript">
		var opened_entityid = "${opened_entityid}";	//要打开的记录的id
		var if_in_attention = "${if_in_attention}";	//是否由关注的记录打开
		var nowDate = "<%=DateOperator.formatDate(DateOperator.truncDate(new Date()),"yyyy-MM-dd") %>";
		var page_size = 15; //分页大小，随列表区高度改变而改变
		$(function(){
			now_year = "${now_year}";
			now_week_of_year = "${now_week_of_year}";
			now_month = "<%=DateOperator.getMonth12(new Date())%>";
			centerCd = "${centerCd}";
			var bodyHeight = Number($(document).height()-86);
			$("#scheduleDiv").css("height",bodyHeight+"px");
			page_size = Math.ceil((bodyHeight-100)/24);
			centerClick();
			return;
		});
		function returnMain(){
			var url ="${ctx}/plan/plan-exec-plan!portal.action";
			this.location.href=url;
		}
		function gotoExecStat(centerCd,projectCd){
			var url ="${ctx}/plan/plan-exec-plan-stat!portal.action?centerCd="+centerCd+"&projectCd="+projectCd;
			this.location.href=url;
		}
	</script>
	<title>中心计划索引</title>
</head>
<body>
<input type="hidden" id="filter_LIKES_serialNumber"/>
<input type="hidden" id="filter_LIKES_content"/>
<input type="hidden" id="search_statusCd"/>
<input type="hidden" id="filter_GED_createdDate"/>
<input type="hidden" id="filter_LTD_createdDate"/>
<input type="hidden" id="filter_GED_targetDate"/>
<input type="hidden" id="filter_LTD_targetDate"/>
<input type="hidden" id="filter_GED_endDate"/>
<input type="hidden" id="filter_LTD_endDate"/>
<input type="hidden" id="if_search_all"/>
<input type="hidden" id="orderStr1"/>
<input type="hidden" id="orderStr2"/>
<input type="hidden" id="orderDir1"/>
<input type="hidden" id="orderDir2"/>
<div class="title_bar">
	<div style="float:left; font-size:12px; padding-left:5px;" >中心计划：${centerName}&nbsp;<span class="color_red" id="succInfoMsg"></span></div>
	<div style="float:right; height:26px; margin-right:0px;">
		<div class="btn_title_bar_4" style="width:80px;" onclick="gotoExecStat('${centerCd}','${projectCd}');"><div style="float:left; margin-top:4px;"><img src="${ctx}/resources/images/common/title_bar_all_blue.gif"/></div><div style="float:left;">业态进度</div></div>
		<div class="btn_cutline_3_26"></div>
		<div class="btn_title_bar_4" style="width:100px;" onclick="returnMain();"><div style="float:left; margin-top:4px;"><img src="${ctx}/resources/images/common/title_bar_all_green.gif"/></div><div style="float:left;">执行计划总表</div></div>
		<div class="btn_cutline_3_26"></div>
		<div class="btn_title_bar_4" style="width:90px;" onclick="parent.TabUtils.newTab('SZJH', '月计划', '${ctx}/plan/plan-work!getAllPlan.action?centerCd=${centerCd}', true);"><div style="float:left; margin-top:4px;"><img src="${ctx}/resources/images/common/title_bar_all_grey.gif"/></div><div style="float:left;">月计划总表</div></div>
	</div>
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td style="height: 26px; line-height:26px; padding-left:4px; background-color:#e4e7ec; border-right:1px solid #8c8f94; border-bottom:1px solid #8c8f94;"><img src="${ctx}/resources/images/common/down_grey.gif"/>相关执行计划</td>
		<td style="height: 26px; line-height:26px; padding-left:4px; background-color:#e4e7ec; border-bottom:1px solid #8c8f94;">
			<div style="float:left;"><img src="${ctx}/resources/images/common/down_grey.gif"/>月计划</div>
			<div style="float:right; margin-right:0px;">
				<div id="NewBtn" class="btn_title_bar_4" style="cursor:pointer;" onclick="newSchedule();"><div style="float:left; margin-top:4px;"><img src="${ctx}/resources/images/common/title_bar_add.gif"/></div><div style="float:left;">新建计划</div></div>
			</div>
		</td>
	</tr>
	<tr>
		<td valign="top" style="width:130px; border-right:1px solid #8c8f94; border-bottom:1px solid #dddbdc;">
			<div style="text-align:center; cursor:pointer; height: 24px; line-height:24px; padding-left:4px; border-bottom:1px solid #8c8f94;" onclick="getCenterRel('${centerCd}');" title="点击进入相关执行计划总表">
				<div style="width:130px;" onclick="gotoExecStat('${centerCd}','${projectCd}');"><div style="float:left; margin-top:4px;"><img src="${ctx}/resources/images/common/title_bar_all_blue.gif"/></div><div style="float:left;">相关执行计划总表</div></div>
			</div>
			<div>
				<table class="content_table" style="100%">
					<tbody>
						<s:iterator value="centers">
						<tr class="mainTr">
							<td align="center" nowrap="nowrap" onclick="getProjectRel('${projectCd}','${centerCd}');">
								${projectName}
							</td>
						</tr>
					</s:iterator>
					</tbody>
				</table>
			</div>
		</td>
		<td valign="top" rowspan="2" style="border-bottom:1px solid #dddbdc;">
			<div id="scheduleDiv" style="height:400px; overflow:auto; overflow-x:hidden;">
				<div style='height:100px'></div>
				<table width='100%'>
					<tr>
						<td align='center'>
							<img src='${ctx}/images/loading.gif'/>
						</td>
					</tr>
				</table>
			</div>
			<div id="td_page" style="float:right; text-align: center; height:26px; margin-right:10px;"></div>
		</td>
	</tr>
	<tr>
	</tr>
</table>
</body>
</html>
