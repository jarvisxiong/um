<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
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
	
	
	 <%--<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script> --%>
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
	<script type="text/javascript" src="${ctx}/resources/js/cost/cost-budget-year.js"></script>	
	

</head>
<style>
.fakeContainer { /* The parent container */
    padding: 0px;
    border: none;
    width: 770px; /* Required to set */
    height: 400px; /* Required to set */
    overflow: hidden; /* Required to set */
}
</style>
<body style="padding:10px 10px 0 10px;">
	<div id="bannerPanel" style="margin: 10 auto;">
		<div style="text-align: center;">
			<input  name="budgetYear"  
				    id="budgetYear"
					value="${budgetYear}" 
					class="Wdate"
					onfocus="WdatePicker({dateFmt:'yyyy'});"
					onchange="loadPlanList();"
					style="border:none;border-bottom:1px solid #555555;width:80px;text-align: center;"
					/>
					
			<span>年资金计划汇总表</span>
			<security:authorize ifAnyGranted="A_COST_BUD_VIEW_M,A_COST_BUD_EDIT_M">		
				<span>
					<input type="button" value="搜索" class="btn_new btn_query_new"  id="btnLoadPlanList" onclick="loadPlanList();" />
					<input type="button" value="导出" class="btn_new btn_export_new" id="btnExportPlanYear" onclick="exportBudgerYear();"/>
				</span>
			</security:authorize>
		</div>
	</div>
	
	<%--搜索结果区域 --%>
	<div id="planYearList" style="margin: 20px 0 0 0;">		
		
	</div>
		
	<script type="text/javascript">
	$(function(){
		//为了使用fixtable控件,需要载入表格后,调整界面
		loadPlanList();
	});
	</script>	
</body>
</html>