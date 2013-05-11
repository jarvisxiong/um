<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.apache.commons.lang.StringUtils"%>

<div class="monthSeach">
	<form id="searchBudgetMonthForm" action="cost-budget-month!loadMonthList.action" method="post" accept-charset="utf-8" class="searchForm" >
		<input type="hidden" name="pageNo" id="pageNo" value="${pageNo }"/>
		<input type="hidden" name="sectionIds" id="sectionIds" value="${sectionIds}"/>
		<table>
				<tr>
					<td style="padding-left: 10px;text-align: right;">年月：</td>
					<td>
						<input name="yearMonth" 
							   value="${yearMonth}"  
							   type="text" 
							   class="Wdate text" 
							   onfocus="WdatePicker({dateFmt:'yyyy-MM'})" 
							   title="请输入月度计划年月"/>
					</td>
					<td style="padding-left: 10px;text-align: right;">施工单位：</td>
					<td><input name="partb"  type="text" class="text" value="${partb }" title="请输入月度计划中施工单位"/></td>
					<td style="padding-left: 10px;text-align: right;">科目：</td>
					<td>
						<s:select   list="mapTypeByEstate" 
									listKey="key" 
									listValue="value" 
									name="subjectCd"  			
									cssClass="text"
									cssStyle="height:22px;line-height:22px;width:94%"/>
					</td>
					
					
				</tr>
				<tr style="line-height: 30px;">
					<td style="padding-left: 10px;text-align: right;">合同编号：</td>
					<td><input name="contactNo" type="text"  class="text" value="${contactNo }" title="请输入月度计划中合同编号"/></td>
					<td style="padding-left: 10px;text-align: right;">合同名称：</td>
					<td><input name="contactName"  type="text" class="text" value="${contactName }" title="请输入月度计划中合同名称"/></td>
					<td style="padding-left: 10px;text-align: right;">是否战略 ：</td>
					<td>
						<s:select   list="mapStrageFlg" 
									listKey="key" 
									listValue="value" 
									name="strageFlg"  			
									cssClass="text"
									cssStyle="height:22px;line-height:22px;width:94%"/>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="5">
						<input onclick="submitSearchMonth();" class="fLeft btn_new btn_query_new"  value="搜索"/>
						<input onclick="exportMonthDetail();" class="fLeft btn_new btn_export_new" value="导出"/>
					</td>
				</tr>
		</table>
	</form>
</div>


<table class="costTable" cellpadding="0" cellspacing="0" style="margin-top: 15px;width: 100%;" id="MyTable">
	<colgroup>
		<col width="50px;"></col>
		<col width="80px;"></col>
		<col width="140px;"></col>				
		<col width="100px;"></col>
		<col></col>
		<col width="160px;"></col>			
		<col width="120px;"></col>
		<col width="60px;"></col>		
	</colgroup>
	<thead>
		<tr>
			<th nowrap="nowrap"  style="background-image: url('');">序号</th>
			<th title="年月" nowrap="nowrap">年月</th>
			<th title="项目名称" nowrap="nowrap">项目名称</th>					
			<th title="科目" nowrap="nowrap">科目</th>
			<th title="合同编号" nowrap="nowrap">合同编号</th>
			<th title="施工单位" nowrap="nowrap">施工单位</th>					
			<th title="本期资金预算(元)" nowrap="nowrap">本期资金预算(元)</th>	
			<th title="明细" nowrap="nowrap">明细</th>		
		</tr>
	</thead>
	<tbody>
	<s:if test="monthDetailPage.result.size()>0">
		<s:iterator value="monthDetailPage.result" var="cbcp" status="st">
			<tr 
			onclick="viewYearMonthBudget('${costBudgetMonth.costBudgetMonthId}');">
				<td> <s:property value="#st.index+1"></s:property></td>
				<td style="overflow: hidden;" 
					title="${costBudgetMonth.budegetYear}-<%=StringUtils.leftPad(JspUtil.findString("costBudgetMonth.budegetMonth"),2, "0") %>" 
					align="center">
					${costBudgetMonth.budegetYear}-<%=StringUtils.leftPad(JspUtil.findString("costBudgetMonth.budegetMonth"),2, "0") %>
				</td>
				<td style="overflow: hidden;padding-left: 5px;" class="pd-chill-tip" title="${costBudgetMonth.costProjectSection.sectionName}"  align="left"><div class="partHide" style="width: 99%;">${costBudgetMonth.costProjectSection.sectionName}</div></td>					
				<td style="overflow: hidden;padding-left: 5px;" class="pd-chill-tip" title="${subjectName}" align="left"><div class="partHide" style="width: 99%;">${subjectName}</div></td>
				<td style="overflow: hidden;padding-left: 5px;" class="pd-chill-tip" title="${contactNo}" align="left"><div class="partHide" style="width: 99%;">${contactNo}</div></td>
				<td style="overflow: hidden;padding-left: 5px;" class="pd-chill-tip" title="${partb}" align="left"><div class="partHide" style="width: 99%;">${partb}</div></td>
				<td style="overflow: hidden;padding-right: 5px;" class="pd-chill-tip" title="${curPeriodFundBudgetAmt}" align="right"><div class="partHide" style="width: 99%;text-align:right;">${curPeriodFundBudgetAmt}</div></td>
				<td style="overflow: hidden;padding-right: 5px;" title="查看明细" align="center">
					<input type="button" 
						onclick="viewYearMonthBudget('${costBudgetMonth.costBudgetMonthId}');"
						class="btn_new btn_blue_new" 
						value="查看" 
						style="width: 40px;"
						title="${costBudgetMonth.budegetYear}-${costBudgetMonth.budegetMonth}年${costBudgetMonth.costProjectSection.sectionName}的年度计划"
					/>					
			</tr>
		</s:iterator>
		
		<%-- 分页信息 --%>
		<tr>
			<td colspan="8" align="center" >
				<p:page pageInfo="monthDetailPage"/>
			</td>
		</tr>
	</s:if>
	<s:else>
		<tr style="height: 50px;">
			<td colspan="8" align="center"><span style="padding-left: 20px; ">搜索无记录!</span></td>
		</tr>
	</s:else>
	</tbody>
</table> 
	
<script type="text/javascript">
//搜索月度计划
function submitSearchMonth(){
	jumpPage(1);
}
//跳转到相应的页
function jumpPage(pageNo) {	
	$('#sectionIds').val(getSectionIds());
	$("#pageNo").val(pageNo);
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
//搜索后导出
function exportMonthDetail(){
	var data=$("#searchBudgetMonthForm").serialize();
	var url=_ctx+"/cost/cost-budget-month!exportSectionMonthList.action?searchExport=1&"+data;
	openWindow('exportMonthDetail', '导出月度资金预算', url);
}

//查看月度资金预算明细
function viewYearMonthBudget(costBudgetMonthId){	
	var url = _ctx + '/cost/cost-budget-month!input.action?id=' + costBudgetMonthId;
	openWindow('viewMonthBudget','查看月度资金预算',url);
}
</script>

