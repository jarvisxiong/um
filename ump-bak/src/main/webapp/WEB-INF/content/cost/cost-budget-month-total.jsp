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
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cost/cost.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>	
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>		
	<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	
	<%--
	<link type="text/css" rel="stylesheet" href="${ctx}/js/loadMask/jquery.loadmask.css"  />
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	 --%>

</head>
<body >
	
<div style="margin:10px auto;text-align: center;margin-left: 15px;">
	<table style="width:400px;ma">
		<tr class="line">
			<td>请选择汇总年月：</td>
			<td>
				<form id="monthTotalForm" action="cost-budget-month!monthStat.action" method="post" accept-charset="utf-8">
				<input  type="text" 
						readonly="readonly"
						id="planYearMonth" 				
						name="planYearMonth" 
						value="${planYearMonth}"
						class="Wdate"
						onfocus="WdatePicker({dateFmt:'yyyy-MM'});"
						style="width:90px;border-width: 0 0 1px 0;text-align: center;padding-right:10px;" 
						onchange="searchMonthStat();"
						title="请选择年月"/>
				</form>
			</td>
			<td style="padding-bottom: 5px;width:200px;" valign="middle">
				<input type="button" onclick="searchMonthStat();"  class="btn_new btn_query_new" style="margin-left: 20px;cursor: pointer;" value="搜索" />
				<input type="button" onclick="exportMonthStat();"  class="btn_new btn_export_new" style="margin-left: 20px;cursor: pointer;" value="导出" />
			</td>
		</tr>
	</table>
</div>

<div style="margin-top: 10px;">
	<input type="hidden" id="count" value='<s:property value="monthStatVoList.size()"/>'/>
	<span style="font-weight: bolder;margin: 10px;margin-left: 15px;">单位：万元</span>
	
	
	<table class="costTable" cellpadding="0" cellspacing="0" style="margin-top: 5px;width: 100%;overflow-x:auto;">
		<colgroup>
			<col width="50px;"></col>
			<col style="min-width: 120px;"></col>
			<col width="90px;"></col>				
			<col width="120px;"></col>
			<col width="150px;"></col>
			<col width="150px;"></col>		
			<col width="150px;"></col>		
			<col width="120px;"></col>		
			<col width="60px"/>
		</colgroup>
		<thead>
			<tr style="border-bottom: 1px solid #aaabb0;">
				<th nowrap="nowrap"  style="background-image: none;width: 30px;">序号</th>
				<th title="项目名称" nowrap="nowrap">项目名称</th>	
				<th title="年度预算合计(元)" nowrap="nowrap">年度预算合计</th>					
				<th title="至本期的年度预算合计(元)" nowrap="nowrap">至本期年度预算合计</th>
				<th title="本年度至本期累计实付(元)" nowrap="nowrap">本年度至本期累计实付</th>
				<th title="本期资金预算(元)" nowrap="nowrap">本期资金预算</th>				
				<th title="预算偏差" nowrap="nowrap">预算偏差</th>				
				<th title="偏差说明" nowrap="nowrap" colspan="2">偏差说明</th>				
			</tr>
		</thead>
		<tbody>
		
		<s:if test="monthStatVoList.size()>0">
			<s:iterator value="monthStatVoList" var="ye" status="st">
				<tr onclick="processColor(this)">
					<td align="center" class="firstCol"><s:property value="#st.index+1"></s:property></td>
					<td style="padding-left:5px;" title="${projectName}" ><div class="partHide" style="width: 99%;">${projectName}</div></td>
					<td style="padding-left:5px;text-align: right;" title="${groupTotalAmt}"><div class="partHide" style="width: 99%;">${groupTotalAmt}</div></td>				
					<td style="padding-left:5px;text-align: right;" title="${planAmtMCount}"><div class="partHide" style="width: 99%;">${planAmtMCount}</div></td>
					<td style="padding-left:5px;text-align: right;" title="${cumuRealPayTotalAmt}"><div class="partHide" style="width: 99%;">${cumuRealPayTotalAmt}</div></td>
					<td style="padding-left:5px;text-align: right;" title="${curPeriodFundBudgetAmt3}"><div class="partHide" style="width: 99%;">${curPeriodFundBudgetAmt3}</div></td>
					<td style="padding-left:5px;text-align: right;" title="${deviationAmt}" style="width: 99%;">
					
					<%--预算偏差： 若为负值，则红色显示 --%>					
						<c:set value="${deviationAmt}" var="piancha" ></c:set>	
						<c:choose>
							<c:when test="${piancha<0}">
								<div class="ellipsisDiv" style="color: red;">${piancha}</div>
							</c:when>
							<c:otherwise>
								<div class="ellipsisDiv" >${piancha}</div>
							</c:otherwise>
						</c:choose>				
					</td>
										
					<%-- 偏差说明 --%>
					<td style="padding-left:5px;text-align:  left;" title="${desc}">
						<s:if test="costBudgetYearId == 'none'">
							<font color="red">无年度计划</font>
						</s:if>
						<s:elseif test="statusCd==-1">
							<font color="red">无月度计划</font>
						</s:elseif>							
						<s:else>
							<input style="width: 100px;" type="text" value="${desc}" id="desc${st.index}" name="desc${st.index}" 
								onkeyup="$('#tips${st.index}').html('')"
								<security:authorize ifAnyGranted="A_COST_BUD_STAT_EDIT">								
								<s:if test="statusCd==2">
									<security:authorize ifNotGranted="A_COST_BUD_QY_CHK,A_COST_BUD_QY_CFM">
								 		readonly="readonly"
									 	disabled="disabled"	
									</security:authorize>	
								</s:if>
								<s:elseif test="statusCd==3 ">
									<security:authorize ifNotGranted="A_COST_BUD_FIN_CHK">
								 		readonly="readonly"
									 	disabled="disabled"	
									</security:authorize>
								</s:elseif> 	
								<s:elseif test="statusCd == 1 || statusCd == 4 "> 
							 		readonly="readonly"
								 	disabled="disabled"	 
								</s:elseif> 	
								</security:authorize> 
								<security:authorize ifNotGranted="A_COST_BUD_STAT_EDIT">
							 		readonly="readonly"
								 	disabled="disabled"	
								</security:authorize>		
							/>
						</s:else>
					</td>
					<td style="text-align: center;" align="center">
						<%-- 若无年度计划(只有财务有权限) --%>
						<s:if test="costBudgetYearId =='none'">
							<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N">
							<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">
								<input style="width: 40px;" class="btn_green" onclick="createCostBudgetYear();" type="button" value ="新建" title="新建年度计划"/>
							</security:authorize>
							</security:authorize>
						</s:if>
						<%-- 若无月度计划(只有地产有权限) --%>
						<s:elseif test="statusCd==-1">
							<security:authorize ifAnyGranted="A_COST_BUD_EDIT_N">
							<security:authorize ifAnyGranted="A_COST_BUD_DC_CHK">
								<input style="width: 40px;" class="btn_green" onclick="viewYearMonthBudget('${projectId}');" type="button" value ="新建" title="新建月度计划"/>
							</security:authorize>
							</security:authorize>
						</s:elseif>
						<s:else>
							<security:authorize ifAnyGranted="A_COST_BUD_STAT_EDIT">
								<s:if test="statusCd==2">
									<security:authorize ifAnyGranted="A_COST_BUD_QY_CHK,A_COST_BUD_QY_CFM">
										<input id="saveBtu" name="saveBtu" style="width: 40px;" class="btn_green" onclick="doSave('${costBudgetYearId}','${st.index}');" type="button" value ="保存"/>
									</security:authorize>	
								</s:if>
								<s:elseif test="statusCd==3 ">
									<security:authorize ifAnyGranted="A_COST_BUD_FIN_CHK">
										<input id="saveBtu" name="saveBtu" style="width: 40px;" class="btn_green" onclick="doSave('${costBudgetYearId}','${st.index}');" type="button" value ="保存"/>
									</security:authorize>
								</s:elseif> 			
								<s:elseif test="statusCd==4 ">
									归档
								</s:elseif>		
								<br/>
								<span id="tips${st.index}" style="color: red;"></span>
							</security:authorize>
							<security:authorize ifNotGranted="A_COST_BUD_STAT_EDIT">
								<s:elseif test="statusCd==4 ">
									归档
								</s:elseif>	
							</security:authorize>	
						</s:else>
					</td>
				</tr>
			</s:iterator>
		</s:if>
		<s:else>
			<tr style="height: 50px;">
				<td colspan="9" align="center"><span style="padding-left: 20px; ">搜索无记录!</span></td>
			</tr>
		</s:else>	
		</tbody>
	</table>
</div>
 
 

<script type="text/javascript">

/**
 * 更新备注
 * @param costBudgetYearId 年份ID
 * @param index 序号
 */
function doSave(costBudgetYearId,index){
	
	clearTips();
	
	var desc = $("#desc"+index).val();
	var yearMonth = getPlanYearMonth();
	var url = _ctx +'/cost/cost-budget-year!updateMonthDesc.action';
	var data = {costBudgetYearId: costBudgetYearId, desc:desc, planYearMonth: yearMonth};
	$.post(url, data, function(result) {
		if('success' == result){
			$('#tips'+index).html('保存成功!').show();
		}else{
			$('#tips'+index).html('保存不成功!').show();
			return false;
		}
	});
}
/**
 * 清除提示
 */
function clearTips(){
	var count = $("#count").val();
	for(var i = 0;i<count;i++){
		$('#tips'+i).html('');
	}
}
/**
 * 创建年度计划
 */
function createCostBudgetYear(){
	var url = _ctx + '/cost/cost-budget-year!input.action';
	openWindow('newBudgetYear', '新增年度资金预算', url);
}

/**
 * 创建月度资金预算明细
 */
function viewYearMonthBudget(planSectionId){	
	var planYearMonth=$("#planYearMonth").val();
	if(planYearMonth==''){
			alert("请选择年月");
			return ;
		}
	var url = _ctx + '/cost/cost-budget-month!input.action?planYearMonth='+planYearMonth+"&planSectionId="+planSectionId;	
	openWindow('newBudgetMonth', '新增月度资金预算', url);
}
/**
 * 打开窗口
 * @param id 窗口ID
 * @param desc 描述
 * @param url 链接地址
 */
function openWindow(id, desc, url) {
	if (window.parent && window.parent.parent.TabUtils) {
		window.parent.parent.TabUtils.newTab(id, desc, url);
	} else {
		window.open(url);
	}
}

/**
 * 获取年月
 */
function getPlanYearMonth(){
	return $('#planYearMonth').val();
}
//搜索月度资金汇总
function searchMonthStat() {
	var yearMonth = getPlanYearMonth();
	if($.trim(yearMonth) == ''){
		//alert('请选择年月!');
		return;
	} 
	window.location.href = _ctx + '/cost/cost-budget-month!monthStat.action?planYearMonth=' + yearMonth;
}
//导出月度资金汇总表
function exportMonthStat(){
	var yearMonth = getPlanYearMonth();
	if('' == yearMonth){
		alert('请选择年月!');
		return;
	}
	window.open(_ctx + "/cost/cost-budget-month!exportMonthStat.action?planYearMonth=" + yearMonth);
} 
 
function processColor(dom){
	$('.firstCol').css({'background-color': ''});
	$(dom).find('td:first').css({'background-color': 'orange'});
}
</script>

</body>
</html>