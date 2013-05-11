<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>月度资金预算</title>	
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/superTables.css"  />	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cost/cost.css"  />
	
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>	
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js" ></script>	
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>		
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/superTables.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/cost/cost-budget.js"></script>	
		
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	

</head>
<style>
.fakeContainer { /* The parent container */
    padding: 0px;
    border: none;
    width: 770px; /* Required to set */
    height: 383px; /* Required to set */
    overflow: hidden; /* Required to set */
}
</style>
<body style="padding:10px">
	<div id="firstPanel" style="margin:10 auto;text-align: center;">
	<h3>
		<%-- 项目 --%>
		<s:select 	list="dropProjectList" 
					listValue="sectionName" 
					listKey="costProjectSectionId" 
					id="planSectionId" 
					name="planSectionId"
					value="planSectionId"					
					title="请选择项目" 
					cssStyle="border-width: 0 0 1px 0; border-bottom:1px solid #aaabb0;min-width:100px;"
					onchange="loadMonthDetailList('show');"	
					>
		</s:select>
		
		<%--年月 --%>
		月度资金预算(<input  type="text" 
					id="planYearMonth" 				
					name="planYearMonth" 
					value="${planYearMonth}"
					class="Wdate"
					onfocus="WdatePicker({dateFmt:'yyyy-MM'});"
					style="width:90px;border-width: 0 0 1px 0;text-align: center;padding-right:10px;" 
					title="请选择年月" 
					onchange="loadMonthDetailList('show');"
					></input>)			
		
		<span style="margin-left:10px">单位（元）</span>
		<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M,A_COST_BUD_EDIT_M">
			<input type="button" class="btn_new btn_query_new" onclick="loadMonthDetailList('show');"  value="搜索"/>
			<input type="button" class="btn_new btn_export_new" onclick="exportBudgerMonth();"  value="导出"/>
		</security:authorize>
	</h3>	
	</div>

	<%--表格搜索结果 --%>
	<div id="monthDetailList">
		<%--
		<%@include file="cost-budget-month-loadMonthDetailList.jsp" %>
		 --%>
	</div>
	<input type="hidden" name="latestDetailId" id="hide_latestDetailId"/>
	
	<%--提交按钮
	<div id="buttons" style="clear: left;display: none;">
		<div onclick="commitCost(this);" class="plblue"  projectCd="" ym="">提交</div>
		<div id="monthCostStatus" style="float:left;"></div>
	</div>
	 --%>
	 
	<script type="text/javascript">
	$(function(){
		var url = location.href;
		var paraString = url.substring(url.indexOf("?")+1,url.length);		
		if(paraString!=null&&paraString!=''){
			var st=paraString.split("=");		
			if(st[0]=='costBudgetMonthId'){
					$("#hide_costBudgetMonthId").val(st[1]);
				}	
			}
		loadMonthDetailList('show');
	});
	</script>	
</body>
</html>