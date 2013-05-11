<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<div class="monthSeach">
	<form id="searchBudgetMonthForm" action="cost-budget-year!loadYearList.action" method="post" accept-charset="utf-8" >
		<input type="hidden" name="pageNo" id="pageNo" value="${pageNo }"/>
		<input type="hidden" name="sectionIds" id="sectionIds" value="${sectionIds}"/>
		<table style="margin-left: 15px;">
			<tr class="line">
				<td style="padding-right: 5px;">年份：</td>
				<td><input name="budgetYear" value="${budgetYear}" type="text" onfocus="WdatePicker({dateFmt:'yyyy',onpicked:function(dp){}})" class="Wdate text"></input></td>
			
				<td style="padding-right: 10px;padding-left: 10px;">项目名称：</td>
				<td><input name="sectionName" value="${sectionName}" type="text" title="模糊匹配" class="text"></input></td>
				<td colspan="2" style="padding-bottom: 5px;width: 200px;">
					<input type="button" onclick="submitSearchYear();" class="btn_new btn_query_new" value="搜索"/>
				</td>
			</tr>
		</table>
	</form>
</div>


<table class="costTable" cellpadding="0" cellspacing="0" style="margin-top: 15px;width: 100%;">
	<colgroup>
		<col width="50px;"></col>
		<col width="50px;"></col>
		<col width="100px;"></col>				
		<col width="100px;"></col>
		<col width="100px;"></col>
		<col width="100px;"></col>		
		<col width="50px;"></col>
	</colgroup>
	<thead>
		<tr style="border-bottom: 1px solid #aaabb0;">
			<th title="" nowrap="nowrap"  style="background-image: url('');">序号</th>
			<th style="line-height: 16px;padding:3px 2;" title="年份" nowrap="nowrap">年份</th>
			<th style="line-height: 16px;padding:3px 2;" title="项目名称" nowrap="nowrap">项目名称</th>					
			<th style="line-height: 16px;padding:3px 2;"  title="目标成本(万元)" nowrap="nowrap">目标成本<br/><span style="font-weight: 12px;">(万元)</span></th>
			<th style="line-height: 16px;padding:3px 2;" title="本年度之前已付额(万元)" nowrap="nowrap">本年度之前已付额<br/><span style="font-weight: 12px;">(万元)</span></th>
			<th style="line-height: 16px;padding:3px 2;" title="本年度预算批准额(万元)" nowrap="nowrap">本年度预算批准额<br/><span style="font-weight: 12px;">(万元)</span></th>			
			<th style="line-height: 16px;padding:3px 2;"  title="明细" nowrap="nowrap">明细</th>	
		</tr>
	</thead>
	<tbody>
	<s:if test="page.result.size()>0">
		<s:iterator value="page.result" var="ye" status="st">
		<tr title="查看${budgetYear}年度资金计划" line="${costBudgetYearId}"  onclick="viewYearBudget('${budgetYear}')" >
			<TD> <s:property value="#st.index+1"></s:property></TD>
			<td style="overflow: hidden;" title="查看${budgetYear}年度资金计划" align="center">${budgetYear}</td>
			<td style="overflow: hidden;padding-left: 5px;" title="${costProjectSection.sectionName}" class="pd-chill-tip" align="left" nowrap="nowrap"><div style="width:100px;overflow: hidden;">${costProjectSection.sectionName}</div></td>				
			<td style="overflow: hidden;padding-right: 10px;" title="${targetCostAmt}" class="pd-chill-tip" align="right"><div class="ellipsisDiv">${targetCostAmt}</div></td>
			<td style="overflow: hidden;padding-right: 10px;" title="${preYearPaiedAmt}" class="pd-chill-tip" align="right"><div class="ellipsisDiv">${preYearPaiedAmt}</div></td>
			<td style="overflow: hidden;padding-right: 10px;" title="${groupTotalAmt}" class="pd-chill-tip" align="right"><div class="ellipsisDiv">${groupTotalAmt}</div></td>
			
			<td style="overflow: hidden;padding-right: 5px;" title="查看明细" align="center">
				<input 
				type="button" 
				onclick="viewYearBudget('${budgetYear}')"
				class="btn_new btn_blue_new" 
				style="width: 40px;"
				value="查看"/>
			</td>
			 
		</tr>
		</s:iterator>
		
		<%-- 分页信息 --%>
		<tr>
			<td colspan="7" align="center" >
				<p:page pageInfo="page"/>
			</td>
		</tr>
	</s:if>
	<s:else>
		<tr style="height: 50px;">
			<td colspan="7" align="center"><span style="padding-left: 20px; ">搜索无记录！</span></td>
		</tr>
	</s:else>	
	</tbody>
</table>


<script typ="text/javascript">

//搜索年度计划
function submitSearchYear(){
	jumpPage(1);
}
//跳转到相应的页
function jumpPage(pageNo) {	
	$("#pageNo").val(pageNo);
	$('#sectionIds').val(getSectionIds());
	TB_showMaskLayer("正在搜索...");
	$("#searchBudgetMonthForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();		
		$("#resultPanel").html(result);
	});
}

//跳转至给定的页面,配合前台的分页搜索
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
//打开新增年度计划资金窗口
function viewYearBudget(budgetYear){
	var url = _ctx + '/cost/cost-budget-year!input.action?budgetYear='+budgetYear;
	openWindow('viewYearBudget','查看年度资金预算',url);
} 
</script>
