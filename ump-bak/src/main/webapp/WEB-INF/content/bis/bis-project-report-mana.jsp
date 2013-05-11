<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<script type="text/javascript">
function computePercent(budgetMoney,factIncomeMoney,arrearIncomeMoney,domId){
	var percent=0;
	if(factIncomeMoney==""){
		factIncomeMoney=0;	
	}
	if(arrearIncomeMoney==""){
		arrearIncomeMoney = 0;	
	}
	var factIncomeMoney2 = (parseFloat(factIncomeMoney)+parseFloat(arrearIncomeMoney));
	
	if(budgetMoney !="" && budgetMoney!="0"){
		percent=(parseFloat(factIncomeMoney2)/parseFloat(budgetMoney)) *100;
	}

	//alert(percent);
	var divDom=document.getElementById(domId);
	if(divDom){
			var decimalStr=""+percent;		
			if(decimalStr.indexOf(".")>-1 && decimalStr.split(".")[1].length>2){		
				divDom.innerHTML=(percent).toFixed(2)+"%";
			}else{
					divDom.innerHTML=percent+"%";
			}
	}	
}
function computePercent2(budgetMoney,factIncomeMoney,arrearIncomeMoney,domId){
	var percent=0;
	if(factIncomeMoney==""){
		factIncomeMoney=0;	
	}
	if(arrearIncomeMoney==""){
		arrearIncomeMoney = 0;	
	}
	var factIncomeMoney2 = (parseFloat(factIncomeMoney));
	
	if(budgetMoney !="" && budgetMoney!="0"){
		percent=(parseFloat(factIncomeMoney2)/parseFloat(budgetMoney)) *100;
	}

	//alert(percent);
	var divDom=document.getElementById(domId);
	if(divDom){
			var decimalStr=""+percent;		
			if(decimalStr.indexOf(".")>-1 && decimalStr.split(".")[1].length>2){		
				divDom.innerHTML=(percent).toFixed(2)+"%";
			}else{
					divDom.innerHTML=percent+"%";
			}
	}	
}
function computePercentForRate(budgetProfitTotal,budgetNetProfit,factProfitTotal,arrearProfitTotal,factNetProfit,arrearNetProfit,domId){
	var percent=0;
	if(budgetProfitTotal==""){
		budgetProfitTotal=0;	
	}
	if(budgetNetProfit==""){
		budgetNetProfit = 0;	
	}
	if(factNetProfit==""){
		factNetProfit = 0;	
	}
	if(arrearNetProfit==""){
		arrearNetProfit = 0;	
	}
	if(factProfitTotal==""){
		factProfitTotal = 0;	
	}
	if(arrearProfitTotal==""){
		arrearProfitTotal = 0;	
	}
	//alert(budgetProfitTotal+";"+budgetNetProfit+";"+factNetProfit+";"+arrearNetProfit+";"+factProfitTotal+";"+arrearProfitTotal);
	
	
	//alert(percent);
	
	var factNetProfitTotal = (parseFloat(factNetProfit)+parseFloat(arrearNetProfit));
	var factProfitTotalTotal = (parseFloat(factProfitTotal)+parseFloat(arrearProfitTotal));
	
	var resultPercent = 0;
	if(budgetProfitTotal !="" && budgetProfitTotal!="0"&&factNetProfitTotal !="" && factNetProfitTotal!="0"){
		var a = (parseFloat(budgetNetProfit)*parseFloat(factProfitTotalTotal));
		//alert(budgetNetProfit+";"+factProfitTotalTotal);
		var b = (parseFloat(factNetProfitTotal)*parseFloat(budgetProfitTotal));
		//alert(factNetProfitTotal+";"+budgetProfitTotal);
		resultPercent=(b/a)*100;
		//alert(resultPercent);
	}



	var divDom=document.getElementById(domId);
	if(divDom){
			var decimalStr=""+resultPercent;		
			if(decimalStr.indexOf(".")>-1 && decimalStr.split(".")[1].length>2){		
				divDom.innerHTML=(resultPercent).toFixed(2)+"%";
			}else{
				divDom.innerHTML=resultPercent+"%";
			}
	}	
}
</script>
<table style="width: 100%;" align="center">
	<tr>
		<td valign="top">
			<table id="tableLeft" class="mTable" cellpadding="0" cellspacing="0">
				<col width="80px">
				<col width="80px"/>
				<col width="80px"/>
				<col width="80px"/>
				<col width="80px"/>
				<col width="80px"/>
				<col width="80px"/>
				<col width="80px"/>
				<tr  class="headTr"> 
				<td colspan="7" align="center" nowrap="nowrap" style="border-right:0px;">项目经营情况表</td>
				<td colspan="1" align="right" style="border-left:0px;">单位:万元</td>
				</tr>
				<tr class="headTr">
					<td rowspan="2">序号</td>
					<td rowspan="2">项目</td>
					<td nowrap="nowrap" colspan="4"  align="center" style="border-left:0px;">年度分析</td>
					<td nowrap="nowrap" colspan="2" align="center" >月度分析</td>
				</tr>
				<tr>
				<td nowrap="nowrap" >本年累计预算数</td>
				<td nowrap="nowrap" >本年累计预算达成率</td>
				<td nowrap="nowrap" >年度预算</td>
				<td nowrap="nowrap" >年度预算达成率</td>
				<td nowrap="nowrap" >本月预算数</td>
				<td nowrap="nowrap" >本月预算达成率</td>
				</tr>
				
				<tr myid="1" ifHasSub="1">
					<td style="background-color:#E4E7EC;">1&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;营业收入<span class="triangle"/></td>
					
					<td><s:if test="curYearBisBudgetVo.incomeTotal==null">0</s:if><s:else>${curYearBisBudgetVo.incomeTotal}</s:else></td>

					<td>
					<div id="B_1-b"></div>
				   	<script>
				    computePercent2('${curYearBisBudgetVo.incomeTotal}',"${resultMap['B_1-2'].moneyFact}","${resultMap['B_1-2'].moneyArrear}",'B_1-b');
					</script>
					</td>

					<td><s:if test="curAllYearBisBudgetVo.incomeTotal==null">0</s:if><s:else>${curAllYearBisBudgetVo.incomeTotal}</s:else></td>
					
					<td>
					<div id="B_1-c"></div>
				   	<script>
				    computePercent2('${curAllYearBisBudgetVo.incomeTotal}',"${resultMap['B_1-2'].moneyFact}","${resultMap['B_1-2'].moneyArrear}",'B_1-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudgetVo.incomeTotal==null">0</s:if><s:else>${curMonthBisBudgetVo.incomeTotal}</s:else></td>
					<td>
					<div id="B_1-a"></div>
				   	<script>
				    computePercent('${curMonthBisBudgetVo.incomeTotal}',"${resultMap['B_1-1'].moneyFact}","${resultMap['B_1-1'].moneyArrear}",'B_1-a');
					</script>
					</td>
				</tr>
				<tr myid="1.1" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">1.1&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;物业管理费收入</td>
					<td><s:if test="curYearBisBudget.propManage==null">0</s:if><s:else>${curYearBisBudget.propManage}</s:else></td>
					
					<td>
					<div id="B_1_1-b"></div>
				   	<script>
				    computePercent2('${curYearBisBudget.propManage}',"${resultMap['B_1_1-2'].moneyFact+curYearBisBudgetVo.propManage}","${resultMap['B_1_1-2'].moneyArrear}",'B_1_1-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.propManage==null">0</s:if><s:else>${curAllYearBisBudget.propManage}</s:else></td>
					
					<td>
					<div id="B_1_1-c"></div>
				   	<script>
				    computePercent2('${curAllYearBisBudget.propManage}',"${resultMap['B_1_1-2'].moneyFact+curAllYearBisBudgetVo.propManage}","${resultMap['B_1_1-2'].moneyArrear}",'B_1_1-c');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.propManage==null">0</s:if><s:else>${curMonthBisBudget.propManage}</s:else></td>
					
					<td>
					<div id="B_1_1-a"></div>
				   	<script>
				    computePercent('${curMonthBisBudget.propManage}',"${resultMap['B_1_1-1'].moneyFact+curMonthBisBudgetVo.propManage}","${resultMap['B_1_1-1'].moneyArrear}",'B_1_1-a');
					</script>
					</td>
				</tr>
				
				<tr myid="1.2" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">1.2&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;车位管理费收入</td>
					
					<td><s:if test="curYearBisBudget.carManage==null">0</s:if><s:else>${curYearBisBudget.carManage}</s:else></td>
					
					<td>
					<div id="19-b"></div>
				   	<script>
				    computePercent2('${curYearBisBudget.carManage}',"${resultMap['19-2'].moneyFact}","${resultMap['19-2'].moneyArrear}",'19-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.carManage==null">0</s:if><s:else>${curAllYearBisBudget.carManage}</s:else></td>
					
					<td>
					<div id="19-c"></div>
				   	<script>
				    computePercent2('${curAllYearBisBudget.carManage}',"${resultMap['19-2'].moneyFact}","${resultMap['19-2'].moneyArrear}",'19-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.carManage==null">0</s:if><s:else>${curMonthBisBudget.carManage}</s:else></td>
					
					<td>
					<div id="19-a"></div>
				   	<script>
				    computePercent('${curMonthBisBudget.carManage}',"${resultMap['19-1'].moneyFact}","${resultMap['19-1'].moneyArrear}",'19-a');
					</script>
					</td>
				</tr>
				
				<!-- 本月停车费实收 -->
				<s:if test="resultMap['17-1'].moneyFact==null">
				<c:set value="0" var="carParking_month_moneyFact"/>
				</s:if>
				<s:else>
				<c:set value="${resultMap['17-1'].moneyFact}" var="carParking_month_moneyFact"/>
				</s:else>
				
				<!-- 本月停车费欠费 -->
				<s:if test="resultMap['17-1'].moneyArrear==null">
				<c:set value="0" var="carParking_month_moneyArrear"/>
				</s:if>
				<s:else>
				<c:set value="${resultMap['17-1'].moneyArrear}" var="carParking_month_moneyArrear"/>
				</s:else>
				
				<!-- 本年停车费实收 -->
				<s:if test="resultMap['17-2'].moneyFact==null">
				<c:set value="0" var="carParking_year_moneyFact"/>
				</s:if>
				<s:else>
				<c:set value="${resultMap['17-2'].moneyFact}" var="carParking_year_moneyFact"/>
				</s:else>
				
				<!-- 本年停车费欠费 -->
				<s:if test="resultMap['17-2'].moneyArrear==null">
				<c:set value="0" var="carParking_year_moneyArrear"/>
				</s:if>
				<s:else>
				<c:set value="${resultMap['17-2'].moneyArrear}" var="carParking_year_moneyArrear"/>
				</s:else>
				
				<!-- 本月招商佣金收入  -->
				<s:if test="resultMap['B_1_4-1'].moneyFact==null">
				<c:set value="0" var="inviteMerchant_month_moneyFact"/>
				</s:if>
				<s:else>
				<c:set value="${resultMap['B_1_4-1'].moneyFact}" var="inviteMerchant_month_moneyFact"/>
				</s:else>
				
				<!-- 本月招商佣金收入  -->
				<s:if test="resultMap['B_1_4-1'].moneyArrear==null">
				<c:set value="0" var="inviteMerchant_month_moneyArrear"/>
				</s:if>
				<s:else>
				<c:set value="${resultMap['B_1_4-1'].moneyArrear}" var="inviteMerchant_month_moneyArrear"/>
				</s:else>
				
				<!-- 本月招商佣金收入  -->
				<s:if test="resultMap['B_1_4-2'].moneyFact==null">
				<c:set value="0" var="inviteMerchant_year_moneyFact"/>
				</s:if>
				<s:else>
				<c:set value="${resultMap['B_1_4-2'].moneyFact}" var="inviteMerchant_year_moneyFact"/>
				</s:else>
				
				<!-- 本月招商佣金收入  -->
				<s:if test="resultMap['B_1_4-2'].moneyArrear==null">
				<c:set value="0" var="inviteMerchant_year_moneyArrear"/>
				</s:if>
				<s:else>
				<c:set value="${resultMap['B_1_4-2'].moneyArrear}" var="inviteMerchant_year_moneyArrear"/>
				</s:else>
				
				<!-- 多种经营收入 -->
				
				<!-- 本月多种经营收入  -->
				<s:if test="resultMap['B_1_3-1'].moneyFact==null">
				<c:set value="0" var="inviteMerchant_month_moneyFact"/>
				</s:if>
				<s:else>
				<c:set value="${resultMap['B_1_3-1'].moneyFact}" var="multiTotal_month_moneyFact"/>
				</s:else>
				
				<!-- 本月多种经营收入  -->
				<s:if test="resultMap['B_1_3-1'].moneyArrear==null">
				<c:set value="0" var="multiTotal_month_moneyArrear"/>
				</s:if>
				<s:else>
				<c:set value="${resultMap['B_1_3-1'].moneyArrear}" var="multiTotal_month_moneyArrear"/>
				</s:else>
				
				<!-- 本月多种经营收入  -->
				<s:if test="resultMap['B_1_3-2'].moneyFact==null">
				<c:set value="0" var="multiTotal_year_moneyFact"/>
				</s:if>
				<s:else>
				<c:set value="${resultMap['B_1_3-2'].moneyFact}" var="multiTotal_year_moneyFact"/>
				</s:else>
				
				<!-- 本月多种经营收入  -->
				<s:if test="resultMap['B_1_3-2'].moneyArrear==null">
				<c:set value="0" var="multiTotal_year_moneyArrear"/>
				</s:if>
				<s:else>
				<c:set value="${resultMap['B_1_3-2'].moneyArrear}" var="multiTotal_year_moneyArrear"/>
				</s:else>
				<!-- 多经营的和算 -->
				<c:set value="${multiTotal_month_moneyFact+carParking_month_moneyFact+inviteMerchant_month_moneyFact}" var ="real_multiTotal_month_moneyFact"></c:set>
				<c:set value="${multiTotal_month_moneyArrear+carParking_month_moneyArrear+inviteMerchant_month_moneyArrear}" var ="real_multiTotal_month_moneyArrear"></c:set>
				<c:set value="${multiTotal_year_moneyFact+carParking_year_moneyFact+inviteMerchant_year_moneyFact}" var ="real_multiTotal_year_moneyFact"></c:set>
				<c:set value="${multiTotal_year_moneyArrear+carParking_year_moneyArrear+inviteMerchant_year_moneyArrear}" var ="real_multiTotal_year_moneyArrear"></c:set>
					
					<tr myid="1.3" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">1.3&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;多种经营收入<span class="triangle"/></td>
					
					<td><s:if test="curYearBisBudgetVo.multiTotal==null">0</s:if><s:else>${curYearBisBudgetVo.multiTotal}</s:else></td>
					
					<td>
					<div id="B_1_3-b"></div>
				   	<script>
				    computePercent2('${curYearBisBudgetVo.multiTotal}',"${real_multiTotal_year_moneyFact}","${real_multiTotal_year_moneyArrear}",'B_1_3-b');
					</script>
					</td>
					<td><s:if test="curAllYearBisBudgetVo.multiTotal==null">0</s:if><s:else>${curAllYearBisBudgetVo.multiTotal}</s:else></td>
					
				    <td>
					<div id="B_1_3-c"></div>
				   	<script>
				    computePercent2('${curAllYearBisBudgetVo.multiTotal}',"${real_multiTotal_year_moneyFact}","${real_multiTotal_year_moneyArrear}",'B_1_3-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudgetVo.multiTotal==null">0</s:if><s:else>${curMonthBisBudgetVo.multiTotal}</s:else></td>
					
					<td>
					<div id="B_1_3-a"></div>
				   	<script>
				    computePercent('${curMonthBisBudgetVo.multiTotal}',"${real_multiTotal_month_moneyFact}","${real_multiTotal_month_moneyArrear}",'B_1_3-a');
					</script>
					</td>
				</tr>
				
				<tr myid="1.3.1" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">1.3.1&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;广告场地使用收入</td>
					
					<td><s:if test="curYearBisBudget.multiAdvert==null">0</s:if><s:else>${curYearBisBudget.multiAdvert}</s:else></td>
					
					<td>
					<div id="16-b"></div>
				   	<script>
				    computePercent2('${curYearBisBudget.multiAdvert}',"${resultMap['16-2'].moneyFact}","${resultMap['16-2'].moneyArrear}",'16-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.multiAdvert==null">0</s:if><s:else>${curAllYearBisBudget.multiAdvert}</s:else></td>
					
					<td>
					<div id="16-c"></div>
				   	<script>
				    computePercent2('${curAllYearBisBudget.multiAdvert}',"${resultMap['16-2'].moneyFact}","${resultMap['16-2'].moneyArrear}",'16-c');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.multiAdvert==null">0</s:if><s:else>${curMonthBisBudget.multiAdvert}</s:else></td>
					
					<td>
					<div id="16-a"></div>
				   	<script>
				    computePercent('${curMonthBisBudget.multiAdvert}',"${resultMap['16-1'].moneyFact}","${resultMap['16-1'].moneyArrear}",'16-a');
					</script>
					</td>
				</tr>
				
				<tr myid="1.3.2" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">1.3.2&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;招商佣金收入<span class="triangle"/></td>
					
					<td><s:if test="curYearBisBudgetVo.inviteMerchant==null">0</s:if><s:else>${curYearBisBudgetVo.inviteMerchant}</s:else></td>
					
					<td>
					<div id="B_1_4-b"></div>
				   	<script>
				    computePercent2('${curYearBisBudgetVo.inviteMerchant}',"${resultMap['B_1_4-2'].moneyFact}","${resultMap['B_1_4-2'].moneyArrear}",'B_1_4-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudgetVo.inviteMerchant==null">0</s:if><s:else>${curAllYearBisBudgetVo.inviteMerchant}</s:else></td>
					
					<td>
					<div id="B_1_4-c"></div>
				   	<script>
				    computePercent2('${curAllYearBisBudgetVo.inviteMerchant}',"${resultMap['B_1_4-2'].moneyFact}","${resultMap['B_1_4-2'].moneyArrear}",'B_1_4-c');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudgetVo.inviteMerchant==null">0</s:if><s:else>${curMonthBisBudgetVo.inviteMerchant}</s:else></td>
					<td>
					<div id="B_1_4-a"></div>
				   	<script>
				    computePercent('${curMonthBisBudgetVo.inviteMerchant}',"${resultMap['B_1_4-1'].moneyFact}","${resultMap['B_1_4-1'].moneyArrear}",'B_1_4-a');
					</script>
					</td>
					
				</tr>
				
			    <tr myid="1.3.2.1" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">1.3.2.1&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;招商代理费</td>
					
					<td><s:if test="curYearBisBudget.inviteAgent==null">0</s:if><s:else>${curYearBisBudget.inviteAgent}</s:else></td>
					
					<td>
					<div id="2-b"></div>
				   	<script>
				    computePercent2('${curYearBisBudget.inviteAgent}',"${resultMap['2-2'].moneyFact}","${resultMap['2-2'].moneyArrear}",'2-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.inviteAgent==null">0</s:if><s:else>${curAllYearBisBudget.inviteAgent}</s:else></td>
					
					<td>
					<div id="2-c"></div>
				   	<script>
				    computePercent2('${curAllYearBisBudget.inviteAgent}',"${resultMap['2-2'].moneyFact}","${resultMap['2-2'].moneyArrear}",'2-c');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.inviteAgent==null">0</s:if><s:else>${curMonthBisBudget.inviteAgent}</s:else></td>
					
					<td>
					<div id="2-a"></div>
				   	<script>
				    computePercent('${curMonthBisBudget.inviteAgent}',"${resultMap['2-1'].moneyFact}","${resultMap['2-1'].moneyArrear}",'2-a');
					</script>
					</td>
					
				</tr>
				
				
				<tr myid="1.3.2.2" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">1.3.2.2&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;租金管理费</td>
					<td><s:if test="curYearBisBudget.inviteRent==null">0</s:if><s:else>${curYearBisBudget.inviteRent}</s:else></td>
					
					<td>
					<div id="18-b"></div>
				   	<script>
				    computePercent2('${curYearBisBudget.inviteRent}',"${resultMap['18-2'].moneyFact}","${resultMap['18-2'].moneyArrear}",'18-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.inviteRent==null">0</s:if><s:else>${curAllYearBisBudget.inviteRent}</s:else></td>
					
					<td>
					<div id="18-c"></div>
				   	<script>
				    computePercent2('${curAllYearBisBudget.inviteRent}',"${resultMap['18-2'].moneyFact}","${resultMap['18-2'].moneyArrear}",'18-c');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.inviteRent==null">0</s:if><s:else>${curMonthBisBudget.inviteRent}</s:else></td>
					
					<td>
					<div id="18-a"></div>
				   	<script>
				    computePercent('${curMonthBisBudget.inviteRent}',"${resultMap['18-1'].moneyFact}","${resultMap['18-1'].moneyArrear}",'18-a');
					</script>
					</td>
					
				</tr>
				
				<tr myid="1.3.3" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">1.3.3&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;服务费收入</td>
					
					<td><s:if test="curYearBisBudget.multiService==null">0</s:if><s:else>${curYearBisBudget.multiService}</s:else></td>
					
					<td>
					<div id="20-b"></div>
				   	<script>
				    computePercent2('${curYearBisBudget.multiService}',"${resultMap['20-2'].moneyFact}","${resultMap['20-2'].moneyArrear}",'20-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.multiService==null">0</s:if><s:else>${curAllYearBisBudget.multiService}</s:else></td>
					
					<td>
					<div id="20-c"></div>
				   	<script>
				    computePercent2('${curAllYearBisBudget.multiService}',"${resultMap['20-2'].moneyFact}","${resultMap['20-2'].moneyArrear}",'20-c');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.multiService==null">0</s:if><s:else>${curMonthBisBudget.multiService}</s:else></td>
					
					<td>
					<div id="20-a"></div>
				   	<script>
				    computePercent('${curMonthBisBudget.multiService}',"${resultMap['20-1'].moneyFact}","${resultMap['20-1'].moneyArrear}",'20-a');
					</script>
					</td>
					
				</tr>
				
				<tr myid="1.3.4" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">1.3.4&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;停车场收入</td>
					
					<td><s:if test="curYearBisBudget.carTemporary==null">0</s:if><s:else>${curYearBisBudget.carTemporary}</s:else></td>
					<td>
					<div id="17-b"></div>
				   	<script>
				    computePercent2('${curYearBisBudget.carTemporary}',"${resultMap['17-2'].moneyFact}","${resultMap['17-2'].moneyArrear}",'17-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.carTemporary==null">0</s:if><s:else>${curAllYearBisBudget.carTemporary}</s:else></td>
					
					<td>
					<div id="17-c"></div>
				   	<script>
				    computePercent2('${curAllYearBisBudget.carTemporary}',"${resultMap['17-2'].moneyFact}","${resultMap['17-2'].moneyArrear}",'17-c');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.carTemporary==null">0</s:if><s:else>${curMonthBisBudget.carTemporary}</s:else></td>
					
					<td>
					<div id="17-a"></div>
				   	<script>
				    computePercent('${curMonthBisBudget.carTemporary}',"${resultMap['17-1'].moneyFact}","${resultMap['17-1'].moneyArrear}",'17-a');
					</script>
					</td>
					
				</tr>
				<tr myid="1.3.5" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">1.3.5&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;维修服务收入</td>
					
					<td><s:if test="curYearBisBudget.multiRepair==null">0</s:if><s:else>${curYearBisBudget.multiRepair}</s:else></td>
					
					<td>
					<div id="21-b"></div>
				   	<script>
				    computePercent2('${curYearBisBudget.multiRepair}',"${resultMap['21-2'].moneyFact}","${resultMap['21-2'].moneyArrear}",'21-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.multiRepair==null">0</s:if><s:else>${curAllYearBisBudget.multiRepair}</s:else></td>
					
					<td>
					<div id="21-c"></div>
				   	<script>
				    computePercent2('${curAllYearBisBudget.multiRepair}',"${resultMap['21-2'].moneyFact}","${resultMap['21-2'].moneyArrear}",'21-c');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.multiRepair==null">0</s:if><s:else>${curMonthBisBudget.multiRepair}</s:else></td>
					
					<td>
					<div id="21-a"></div>
				   	<script>
				    computePercent('${curMonthBisBudget.multiRepair}',"${resultMap['21-1'].moneyFact}","${resultMap['21-1'].moneyArrear}",'21-a');
					</script>
					</td>
					
				</tr>
				
				<tr myid="1.3.6" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">1.3.6&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;清洁服务收入</td>
					
					<td><s:if test="curYearBisBudget.multiClean==null">0</s:if><s:else>${curYearBisBudget.multiClean}</s:else></td>
					
					<td>
					<div id="22-b"></div>
				   	<script>
				    computePercent2('${curYearBisBudget.multiClean}',"${resultMap['22-2'].moneyFact}","${resultMap['22-2'].moneyArrear}",'22-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.multiClean==null">0</s:if><s:else>${curAllYearBisBudget.multiClean}</s:else></td>
					
					<td>
					<div id="22-c"></div>
				   	<script>
				    computePercent2('${curAllYearBisBudget.multiClean}',"${resultMap['22-2'].moneyFact}","${resultMap['22-2'].moneyArrear}",'22-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.multiClean==null">0</s:if><s:else>${curMonthBisBudget.multiClean}</s:else></td>
					
					<td>
					<div id="22-a"></div>
				   	<script>
				    computePercent('${curMonthBisBudget.multiClean}',"${resultMap['22-1'].moneyFact}","${resultMap['22-1'].moneyArrear}",'22-a');
					</script>
					</td>
					
				</tr>
			 <tr myid="1.3.7" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">1.3.7&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;其他收入</td>
					
					<td><s:if test="curYearBisBudget.multiOther==null">0</s:if><s:else>${curYearBisBudget.multiOther}</s:else></td>
					
					<td>
					<div id="B_1_3_5-b"></div>
				   	<script>
				    computePercent2('${curYearBisBudget.multiOther}',"${resultMap['B_1_3_5-2'].moneyFact}","${resultMap['B_1_3_5-2'].moneyArrear}",'B_1_3_5-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.multiOther==null">0</s:if><s:else>${curAllYearBisBudget.multiOther}</s:else></td>
					
					<td>
					<div id="B_1_3_5-c"></div>
				   	<script>
				    computePercent2('${curAllYearBisBudget.multiOther}',"${resultMap['B_1_3_5-2'].moneyFact}","${resultMap['B_1_3_5-2'].moneyArrear}",'B_1_3_5-c');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.multiOther==null">0</s:if><s:else>${curMonthBisBudget.multiOther}</s:else></td>
					
					<td>
					<div id="B_1_3_5-a"></div>
				   	<script>
				    computePercent('${curMonthBisBudget.multiOther}',"${resultMap['B_1_3_5-1'].moneyFact}","${resultMap['B_1_3_5-1'].moneyArrear}",'B_1_3_5-a');
					</script>
					</td>
				</tr>
				<tr myid="2" ifHasSub="1">
					<td style="background-color:#E4E7EC;">2&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;营业成本<span class="triangle"/></td>
					
					<td><s:if test="curYearBisBudgetVo.payTotal==null">0</s:if><s:else>${curYearBisBudgetVo.payTotal}</s:else></td>
					
					<td>
					<div id="B_2_14-b"></div>
				   	<script>
				    computePercent2('${curYearBisBudgetVo.payTotal}',"${resultMap['B_2_14-2'].moneyFact}","${resultMap['B_2_14-2'].moneyArrear}",'B_2_14-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudgetVo.payTotal==null">0</s:if><s:else>${curAllYearBisBudgetVo.payTotal}</s:else></td>
					
					<td>
					<div id="B_2_14-c"></div>
				   	<script>
				    computePercent2('${curAllYearBisBudgetVo.payTotal}',"${resultMap['B_2_14-2'].moneyFact}","${resultMap['B_2_14-2'].moneyArrear}",'B_2_14-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudgetVo.payTotal==null">0</s:if><s:else>${curMonthBisBudgetVo.payTotal}</s:else></td>
					
					<td>
					<div id="B_2_14-a"></div>
				   	<script>
				    computePercent('${curMonthBisBudgetVo.payTotal}',"${resultMap['B_2_14-1'].moneyFact}","${resultMap['B_2_14-1'].moneyArrear}",'B_2_14-a');
					</script>
					</td>
					
				</tr>
				
				<tr myid="2.1" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">2.1&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;清洁卫生费</td>
					
					<td><s:if test="curYearBisBudget.cleanHealthCost==null">0</s:if><s:else>${curYearBisBudget.cleanHealthCost}</s:else></td>
					
					<td>
					<div id="B1-b"></div>
				   	<script>
				    computePercent2('${curYearBisBudget.cleanHealthCost}',"${resultMap['B1-2'].moneyFact}","${resultMap['B1-2'].moneyArrear}",'B1-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.cleanHealthCost==null">0</s:if><s:else>${curAllYearBisBudget.cleanHealthCost}</s:else></td>
					
					<td>
					<div id="B1-c"></div>
				   	<script>
				    computePercent2('${curAllYearBisBudget.cleanHealthCost}',"${resultMap['B1-2'].moneyFact}","${resultMap['B1-2'].moneyArrear}",'B1-c');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.cleanHealthCost==null">0</s:if><s:else>${curMonthBisBudget.cleanHealthCost}</s:else></td>
					
					<td>
					<div id="B1-a"></div>
				   	<script>
				    computePercent('${curMonthBisBudget.cleanHealthCost}',"${resultMap['B1-1'].moneyFact}","${resultMap['B1-1'].moneyArrear}",'B1-a');
					</script>
					</td>
					
				</tr>
				<tr myid="2.2" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">2.2&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;安全保卫费</td>
					
					<td><s:if test="curYearBisBudget.safeGuard==null">0</s:if><s:else>${curYearBisBudget.safeGuard}</s:else></td>
					
					<td>
					<div id="B2-b"></div>
				   	<script>
				    computePercent2('${curYearBisBudget.safeGuard}',"${resultMap['B2-2'].moneyFact}","${resultMap['B2-2'].moneyArrear}",'B2-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.safeGuard==null">0</s:if><s:else>${curAllYearBisBudget.safeGuard}</s:else></td>
					
					<td>
					<div id="B2-c"></div>
				   	<script>
				    computePercent2('${curAllYearBisBudget.safeGuard}',"${resultMap['B2-2'].moneyFact}","${resultMap['B2-2'].moneyArrear}",'B2-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.safeGuard==null">0</s:if><s:else>${curMonthBisBudget.safeGuard}</s:else></td>
					
					<td>
					<div id="B2-a"></div>
				   	<script>
				    computePercent('${curMonthBisBudget.safeGuard}',"${resultMap['B2-1'].moneyFact}","${resultMap['B2-1'].moneyArrear}",'B2-a');
					</script>
					</td>
					
				</tr>
				<tr myid="2.3" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">2.3&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;绿化养护费</td>
					
					<td><s:if test="curYearBisBudget.afforeMainten==null">0</s:if><s:else>${curYearBisBudget.afforeMainten}</s:else></td>
					
					<td>
					<div id="B3-b"></div>
				   	<script>
				    computePercent2('${curYearBisBudget.afforeMainten}',"${resultMap['B3-2'].moneyFact}","${resultMap['B3-2'].moneyArrear}",'B3-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.afforeMainten==null">0</s:if><s:else>${curAllYearBisBudget.afforeMainten}</s:else></td>
					
					<td>
					<div id="B3-c"></div>
				   	<script>
				    computePercent2('${curAllYearBisBudget.afforeMainten}',"${resultMap['B3-2'].moneyFact}","${resultMap['B3-2'].moneyArrear}",'B3-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.afforeMainten==null">0</s:if><s:else>${curMonthBisBudget.afforeMainten}</s:else></td>
					
					<td>
					<div id="B3-a"></div>
				   	<script>
				    computePercent('${curMonthBisBudget.afforeMainten}',"${resultMap['B3-1'].moneyFact}","${resultMap['B3-1'].moneyArrear}",'B3-a');
					</script>
					</td>
					
				</tr>
				
				<tr myid="2.4" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">2.4&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;工程维保费</td>
					
					<td><s:if test="curYearBisBudget.engineerMainten==null">0</s:if><s:else>${curYearBisBudget.engineerMainten}</s:else></td>
					
					<td>
					<div id="B4-b"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curYearBisBudget.engineerMainten}',"${resultMap['B4-2'].moneyFact}","${resultMap['B4-2'].moneyArrear}",'B4-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.engineerMainten==null">0</s:if><s:else>${curAllYearBisBudget.engineerMainten}</s:else></td>
					
					<td>
					<div id="B4-c"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.engineerMainten}',"${resultMap['B4-2'].moneyFact}","${resultMap['B4-2'].moneyArrear}",'B4-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.engineerMainten==null">0</s:if><s:else>${curMonthBisBudget.engineerMainten}</s:else></td>
					
					<td>
					<div id="B4-a"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.engineerMainten}',"${resultMap['B4-1'].moneyFact}","${resultMap['B4-1'].moneyArrear}",'B4-a');
					</script>
					</td>
					
				</tr>
				<tr myid="2.5" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">2.5&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;能源费</td>
					
					<td><s:if test="curYearBisBudget.energyCost==null">0</s:if><s:else>${curYearBisBudget.energyCost}</s:else></td>
					
					<td>
					<div id="B25-b"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curYearBisBudget.energyCost}',"${resultMap['B25-2'].moneyFact}","${resultMap['B25-2'].moneyArrear}",'B25-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.energyCost==null">0</s:if><s:else>${curAllYearBisBudget.energyCost}</s:else></td>
					
					<td>
					<div id="B25-c"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.energyCost}',"${resultMap['B25-2'].moneyFact}","${resultMap['B25-2'].moneyArrear}",'B25-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.energyCost==null">0</s:if><s:else>${curMonthBisBudget.energyCost}</s:else></td>
					
					<td>
					<div id="B25-a"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.energyCost}',"${resultMap['B25-1'].moneyFact}","${resultMap['B25-1'].moneyArrear}",'B25-a');
					</script>
					</td>
					
				</tr>
				
				<tr myid="2.6" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">2.6&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;停车场成本</td>
					
					<td><s:if test="curYearBisBudget.parkCharge==null">0</s:if><s:else>${curYearBisBudget.parkCharge}</s:else></td>
					
					<td>
					<div id="B16-b"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curYearBisBudget.parkCharge}',"${resultMap['B16-2'].moneyFact}","${resultMap['B16-2'].moneyArrear}",'B16-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.parkCharge==null">0</s:if><s:else>${curAllYearBisBudget.parkCharge}</s:else></td>
					
					<td>
					<div id="B16-c"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.parkCharge}',"${resultMap['B16-2'].moneyFact}","${resultMap['B16-2'].moneyArrear}",'B16-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.energyCost==null">0</s:if><s:else>${curMonthBisBudget.parkCharge}</s:else></td>
					
					<td>
					<div id="B16-a"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.parkCharge}',"${resultMap['B16-1'].moneyFact}","${resultMap['B16-1'].moneyArrear}",'B16-a');
					</script>
					</td>
					
				</tr>
				
				<tr myid="2.7" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">2.7&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;营业成本-工资福利费</td>
					
					<td><s:if test="curYearBisBudget.salaryFareBiscost==null">0</s:if><s:else>${curYearBisBudget.salaryFareBiscost}</s:else></td>
					
					<td>
					<div id="B17-b"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curYearBisBudget.salaryFareBiscost}',"${resultMap['B17-2'].moneyFact}","${resultMap['B17-2'].moneyArrear}",'B17-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.salaryFareBiscost==null">0</s:if><s:else>${curAllYearBisBudget.salaryFareBiscost}</s:else></td>
					
					<td>
					<div id="B17-c"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.salaryFareBiscost}',"${resultMap['B17-2'].moneyFact}","${resultMap['B17-2'].moneyArrear}",'B17-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.salaryFareBiscost==null">0</s:if><s:else>${curMonthBisBudget.salaryFareBiscost}</s:else></td>
					
					<td>
					<div id="B17-a"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.salaryFareBiscost}',"${resultMap['B17-1'].moneyFact}","${resultMap['B17-1'].moneyArrear}",'B17-a');
					</script>
					</td>
					
				</tr>
				<tr myid="2.8" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">2.8&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;其他成本</td>
				
					<td><s:if test="curYearBisBudget.otherBisCost==null">0</s:if><s:else>${curYearBisBudget.otherBisCost}</s:else></td>
					
					<td>
					<div id="B18-b"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curYearBisBudget.otherBisCost}',"${resultMap['B18-2'].moneyFact}","${resultMap['B18-2'].moneyArrear}",'B18-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.otherBisCost==null">0</s:if><s:else>${curAllYearBisBudget.otherBisCost}</s:else></td>
					
					<td>
					<div id="B18-c"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.otherBisCost}',"${resultMap['B18-2'].moneyFact}","${resultMap['B18-2'].moneyArrear}",'B18-c');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.otherBisCost==null">0</s:if><s:else>${curMonthBisBudget.otherBisCost}</s:else></td>
					
					<td>
					<div id="B18-a"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.otherBisCost}',"${resultMap['B18-1'].moneyFact}","${resultMap['B18-1'].moneyArrear}",'B18-a');
					</script>
					</td>
					
				</tr>
				
				<tr myid="3" ifHasSub="0">
					<td style="background-color:#E4E7EC;">3&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;营业税金及附加</td>
					<td><s:if test="curYearBisBudget.salesExtra==null">0</s:if><s:else>${curYearBisBudget.salesExtra}</s:else></td>
					
					<td>
					<div id="B19-b"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curYearBisBudget.salesExtra}',"${resultMap['B19-2'].moneyFact}","${resultMap['B19-2'].moneyArrear}",'B19-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.salesExtra==null">0</s:if><s:else>${curAllYearBisBudget.salesExtra}</s:else></td>
					
					<td>
					<div id="B19-c"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.salesExtra}',"${resultMap['B19-2'].moneyFact}","${resultMap['B19-2'].moneyArrear}",'B19-c');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.salesExtra==null">0</s:if><s:else>${curMonthBisBudget.salesExtra}</s:else></td>
					
					<td>
					<div id="B19-a"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.salesExtra}',"${resultMap['B19-1'].moneyFact}","${resultMap['B19-1'].moneyArrear}",'B19-a');
					</script>
					</td>
				</tr>
				
				<tr myid="4" ifHasSub="0">
					<td style="background-color:#E4E7EC;">4&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;销售费用<span class="triangle"/></td>
					
					<td><s:if test="curYearBisBudget.saleChargeTotal==null">0</s:if><s:else>${curYearBisBudget.saleChargeTotal}</s:else></td>
					
					<td>
					<div id="XB6-b"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curYearBisBudget.saleChargeTotal}',"${resultMap['XB6-2'].moneyFact}","${resultMap['XB6-2'].moneyArrear}",'XB6-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.saleChargeTotal==null">0</s:if><s:else>${curAllYearBisBudget.saleChargeTotal}</s:else></td>
					
					<td>
					<div id="XB6-c"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.saleChargeTotal}',"${resultMap['XB6-2'].moneyFact}","${resultMap['XB6-2'].moneyArrear}",'XB6-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.saleChargeTotal==null">0</s:if><s:else>${curMonthBisBudget.saleChargeTotal}</s:else></td>
					<td>
					<div id="XB6-a"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.saleChargeTotal}',"${resultMap['XB6-1'].moneyFact}","${resultMap['XB6-1'].moneyArrear}",'XB6-a');
					</script>
					</td>
					
				</tr>
				
				
				<tr myid="4.1" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">4.1&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;销售费用-工资福利费</td>
					
					<td><s:if test="curYearBisBudget.salaryFareSales==null">0</s:if><s:else>${curYearBisBudget.salaryFareSales}</s:else></td>
					
					<td>
					<div id="B20-b"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curYearBisBudget.salaryFareSales}',"${resultMap['B20-2'].moneyFact}","${resultMap['B20-2'].moneyArrear}",'B20-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.salaryFareSales==null">0</s:if><s:else>${curAllYearBisBudget.salaryFareSales}</s:else></td>
					
					<td>
					<div id="B20-c"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.salaryFareSales}',"${resultMap['B20-2'].moneyFact}","${resultMap['B20-2'].moneyArrear}",'B20-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.salaryFareSales==null">0</s:if><s:else>${curMonthBisBudget.salaryFareSales}</s:else></td>
					
					<td>
					<div id="B20-a"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.salaryFareSales}',"${resultMap['B20-1'].moneyFact}","${resultMap['B20-1'].moneyArrear}",'B20-a');
					</script>
					</td>
					
				</tr>
				<tr myid="4.2" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">4.2&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;广告宣传费</td>
					
					<td><s:if test="curYearBisBudget.adivertise==null">0</s:if><s:else>${curYearBisBudget.adivertise}</s:else></td>
					
					<td>
					<div id="B21-b"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curYearBisBudget.adivertise}',"${resultMap['B21-2'].moneyFact}","${resultMap['B21-2'].moneyArrear}",'B21-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.adivertise==null">0</s:if><s:else>${curAllYearBisBudget.adivertise}</s:else></td>
					
					<td>
					<div id="B21-c"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.adivertise}',"${resultMap['B21-2'].moneyFact}","${resultMap['B21-2'].moneyArrear}",'B21-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.adivertise==null">0</s:if><s:else>${curMonthBisBudget.adivertise}</s:else></td>
					
					<td>
					<div id="B21-a"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.adivertise}',"${resultMap['B21-1'].moneyFact}","${resultMap['B21-1'].moneyArrear}",'B21-a');
					</script>
					</td>
					
				</tr>
				<tr myid="4.3" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">4.3&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;招商奖金</td>
					
					<td><s:if test="curYearBisBudget.inviteReward==null">0</s:if><s:else>${curYearBisBudget.inviteReward}</s:else></td>
					
					<td>
					<div id="B22-b"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curYearBisBudget.inviteReward}',"${resultMap['B22-2'].moneyFact}","${resultMap['B22-2'].moneyArrear}",'B22-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.inviteReward==null">0</s:if><s:else>${curAllYearBisBudget.inviteReward}</s:else></td>
					
					<td>
					<div id="B22-c"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.inviteReward}',"${resultMap['B22-2'].moneyFact}","${resultMap['B22-2'].moneyArrear}",'B22-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.inviteReward==null">0</s:if><s:else>${curMonthBisBudget.inviteReward}</s:else></td>
					
					<td>
					<div id="B22-a"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.inviteReward}',"${resultMap['B22-1'].moneyFact}","${resultMap['B22-1'].moneyArrear}",'B22-a');
					</script>
					</td>
					
				</tr>
				<!--新增数据字段-->
				<tr myid="4.4" ifHasSub="0" style="display:none;">
					<td style="background-color:#E4E7EC;">4.4&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;其他</td>
					
					<td><s:if test="curYearBisBudget.otherSaleCharge==null">0</s:if><s:else>${curYearBisBudget.otherSaleCharge}</s:else></td>
					
					<td>
					<div id="B23-b"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curYearBisBudget.otherSaleCharge}',"${resultMap['B23-2'].moneyFact}","${resultMap['B23-2'].moneyArrear}",'B23-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.otherSaleCharge==null">0</s:if><s:else>${curAllYearBisBudget.otherSaleCharge}</s:else></td>
					
					<td>
					<div id="B23-c"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.otherSaleCharge}',"${resultMap['B23-2'].moneyFact}","${resultMap['B23-2'].moneyArrear}",'B23-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.otherSaleCharge==null">0</s:if><s:else>${curMonthBisBudget.otherSaleCharge}</s:else></td>
					
					<td>
					<div id="B23-a"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.otherSaleCharge}',"${resultMap['B23-1'].moneyFact}","${resultMap['B23-1'].moneyArrear}",'B23-a');
					</script>
					</td>
					
				</tr>
				
				<tr myid="5" ifHasSub="0">
					<td style="background-color:#E4E7EC;">5&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;管理费用</td>

					<td><s:if test="curYearBisBudget.otherMgCharge==null">0</s:if><s:else>${curYearBisBudget.otherMgCharge}</s:else></td>
					
					<td>
					<div id="B26-b"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curYearBisBudget.otherMgCharge}',"${resultMap['B26-2'].moneyFact}","${resultMap['B26-2'].moneyArrear}",'B26-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.otherMgCharge==null">0</s:if><s:else>${curAllYearBisBudget.otherMgCharge}</s:else></td>
					
					<td>
					<div id="B26-c"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.otherMgCharge}',"${resultMap['B26-2'].moneyFact}","${resultMap['B26-2'].moneyArrear}",'B26-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.otherMgCharge==null">0</s:if><s:else>${curMonthBisBudget.otherMgCharge}</s:else></td>
					
					<td>
					<div id="B26-a"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.otherMgCharge}',"${resultMap['B26-1'].moneyFact}","${resultMap['B26-1'].moneyArrear}",'B26-a');
					</script>
					</td>
					
				</tr>
					<tr myid="6" ifHasSub="0">
					<td style="background-color:#E4E7EC;">6&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;财务费用</td>
					
					<td><s:if test="curYearBisBudget.finacialCharge==null">0</s:if><s:else>${curYearBisBudget.finacialCharge}</s:else></td>
					
					<td>
					<div id="B27-b"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curYearBisBudget.finacialCharge}',"${resultMap['B27-2'].moneyFact}","${resultMap['B27-2'].moneyArrear}",'B27-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.finacialCharge==null">0</s:if><s:else>${curAllYearBisBudget.finacialCharge}</s:else></td>
					
					<td>
					<div id="B27-c"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.finacialCharge}',"${resultMap['B27-2'].moneyFact}","${resultMap['B27-2'].moneyArrear}",'B27-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.finacialCharge==null">0</s:if><s:else>${curMonthBisBudget.finacialCharge}</s:else></td>
					
					<td>
					<div id="B27-a"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.finacialCharge}',"${resultMap['B27-1'].moneyFact}","${resultMap['B27-1'].moneyArrear}",'B27-a');
					</script>
					</td>
					
				</tr>
				<tr myid="7" ifHasSub="0">
					<td style="background-color:#E4E7EC;">7&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;投资收益</td>
					<td>0</td>
					<td>0%</td>
					<td>0</td>
					<td>0%</td>
					<td>0</td>
					<td>0%</td>
				</tr>
				<tr myid="8" ifHasSub="0">
					<td style="background-color:#E4E7EC;">8&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;营业利润<span class="triangle"/></td>
					
					<td><s:if test="curYearBisBudget.bisProfit==null">0</s:if><s:else>${curYearBisBudget.bisProfit}</s:else></td>
					<td>
					<div id="B_4_1-b"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curYearBisBudget.bisProfit}',"${resultMap['B_4_1-2'].moneyFact}","${resultMap['B_4_1-2'].moneyArrear}",'B_4_1-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.bisProfit==null">0</s:if><s:else>${curAllYearBisBudget.bisProfit}</s:else></td>
					
					<td>
					<div id="B_4_1-c"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.bisProfit}',"${resultMap['B_4_1-2'].moneyFact}","${resultMap['B_4_1-2'].moneyArrear}",'B_4_1-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.bisProfit==null">0</s:if><s:else>${curMonthBisBudget.bisProfit}</s:else></td>
					<td>
					<div id="B_4_1-a"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.bisProfit}',"${resultMap['B_4_1-1'].moneyFact}","${resultMap['B_4_1-1'].moneyArrear}",'B_4_1-a');
					</script>
					</td>
					
				</tr>
				<tr myid="8.1" ifHasSub="0">
					<td style="background-color:#E4E7EC;">&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;加：营业外收入</td>
					
					<td><s:if test="curYearBisBudget.bisIncome==null">0</s:if><s:else>${curYearBisBudget.bisIncome}</s:else></td>
					
					<td>
					<div id="B29-b"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curYearBisBudget.bisIncome}',"${resultMap['B29-2'].moneyFact}","${resultMap['B29-2'].moneyArrear}",'B29-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.bisIncome==null">0</s:if><s:else>${curAllYearBisBudget.bisIncome}</s:else></td>
					
					<td>
					<div id="B29-c"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.bisIncome}',"${resultMap['B29-2'].moneyFact}","${resultMap['B29-2'].moneyArrear}",'B29-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.bisIncome==null">0</s:if><s:else>${curMonthBisBudget.bisIncome}</s:else></td>
					<td>
					<div id="B29-a"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.bisIncome}',"${resultMap['B29-1'].moneyFact}","${resultMap['B29-1'].moneyArrear}",'B29-a');
					</script>
					</td>
					
				</tr>
				<tr myid="8.2" ifHasSub="0">
					<td style="background-color:#E4E7EC;">&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;减：营业外支出</td>
					
					<td><s:if test="curYearBisBudget.bisExpense==null">0</s:if><s:else>${curYearBisBudget.bisExpense}</s:else></td>
					<td>
					<div id="B34-b"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curYearBisBudget.bisExpense}',"${resultMap['B34-2'].moneyFact}","${resultMap['B34-2'].moneyArrear}",'B34-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.bisExpense==null">0</s:if><s:else>${curAllYearBisBudget.bisExpense}</s:else></td>
					
					<td>
					<div id="B34-c"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.bisExpense}',"${resultMap['B34-2'].moneyFact}","${resultMap['B34-2'].moneyArrear}",'B34-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.bisExpense==null">0</s:if><s:else>${curMonthBisBudget.bisExpense}</s:else></td>
					
					<td>
					<div id="B34-a"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.bisExpense}',"${resultMap['B34-1'].moneyFact}","${resultMap['B34-1'].moneyArrear}",'B34-a');
					</script>
					</td>
					
				</tr>
				<tr myid="9" ifHasSub="0">
					<td style="background-color:#E4E7EC;">9&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;利润总额<span class="triangle"/></td>
					
					<td>
					
					<s:if test="curYearBisBudget.profitTotal==null">0
					</s:if><s:else>${curYearBisBudget.profitTotal}</s:else>
					</td>
					
					<td>
					<div id="B_7_1-b"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.profitTotal}',"${resultMap['B_7_1-2'].moneyFact}","${resultMap['B_7_1-2'].moneyArrear}",'B_7_1-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.profitTotal==null">0</s:if><s:else>${curAllYearBisBudget.profitTotal}</s:else></td>
					
					<td>
					<div id="B_7_1-c"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.profitTotal}',"${resultMap['B_7_1-2'].moneyFact}","${resultMap['B_7_1-2'].moneyArrear}",'B_7_1-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.profitTotal==null">0</s:if><s:else>${curMonthBisBudget.profitTotal}</s:else></td>
					
					<td>
					<div id="B_7_1-a"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.profitTotal}',"${resultMap['B_7_1-1'].moneyFact}","${resultMap['B_7_1-1'].moneyArrear}",'B_7_1-a');
					</script>
					</td>
				</tr>
				<tr myid="9.1" ifHasSub="0">
					<td style="background-color:#E4E7EC;">&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;减：所得税费用</td>
					
					<td><s:if test="curYearBisBudget.incomeTax==null">0</s:if><s:else>${curYearBisBudget.incomeTax}</s:else></td>
					
					<td>
					<div id="B31-b"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curYearBisBudget.incomeTax}',"${resultMap['B31-2'].moneyFact}","${resultMap['B31-2'].moneyArrear}",'B31-b');
					</script>
					</td>
					
					<td><s:if test="curAllYearBisBudget.incomeTax==null">0</s:if><s:else>${curAllYearBisBudget.incomeTax}</s:else></td>
					
					<td>
					<div id="B31-c"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.incomeTax}',"${resultMap['B31-2'].moneyFact}","${resultMap['B31-2'].moneyArrear}",'B31-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.incomeTax==null">0</s:if><s:else>${curMonthBisBudget.incomeTax}</s:else></td>
					
					<td>
					<div id="B31-a"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.incomeTax}',"${resultMap['B31-1'].moneyFact}","${resultMap['B31-1'].moneyArrear}",'B31-a');
					</script>
					</td>
					
				</tr>
				<tr myid="10" ifHasSub="0">
					<td style="background-color:#E4E7EC;">10&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;净利润<span class="triangle"/></td>
					<!--  
					<td><s:if test="curMonthBisBudgetVo.profitTotal==null&&curMonthBisBudget.incomeTax==null">0</s:if>
					<s:elseif test="curMonthBisBudgetVo.profitTotal==null">-${curMonthBisBudget.incomeTax}</s:elseif>
					<s:elseif test="curMonthBisBudget.incomeTax==null">${curMonthBisBudgetVo.profitTotal}</s:elseif>
					<s:else>${curMonthBisBudgetVo.profitTotal-curMonthBisBudget.incomeTax}</s:else></td>
					-->
					
					<!--  
					<td><s:if test="curYearBisBudget.profitTotal==null&&curYearBisBudget.incomeTax==null">0</s:if>
					<s:elseif test="curYearBisBudget.profitTotal==null">-${curYearBisBudget.incomeTax}</s:elseif>
					<s:elseif test="curYearBisBudget.incomeTax==null">${curYearBisBudget.profitTotal}</s:elseif>
					<s:else>${curYearBisBudget.profitTotal-curYearBisBudget.incomeTax}</s:else></td>
					-->
					<td><s:if test="curYearBisBudget.netProfit==null||curYearBisBudget.netProfit==null">0</s:if><s:else>${curYearBisBudget.netProfit}</s:else></td>
					
					<td>
					<div id="B_9_1-b"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curYearBisBudget.netProfit}',"${resultMap['B_9_1-2'].moneyFact}","${resultMap['B_9_1-2'].moneyArrear}",'B_9_1-b');
					</script>
					</td>
					<!--  
					<td><s:if test="curAllYearBisBudgetVo.profitTotal==null&&urAllYearBisBudget.incomeTax==null">0</s:if>
					<s:elseif test="curAllYearBisBudgetVo.profitTotal==null">-${curAllYearBisBudget.incomeTax}</s:elseif>
					<s:elseif test="curAllYearBisBudget.incomeTax==null">${curAllYearBisBudgetVo.profitTotal}</s:elseif>
					<s:else>${curAllYearBisBudgetVo.profitTotal-curAllYearBisBudget.incomeTax}</s:else></td>
					-->
					<td><s:if test="curAllYearBisBudget.netProfit==null||curAllYearBisBudget.netProfit==null">0</s:if><s:else>${curAllYearBisBudget.netProfit}</s:else></td>
					<td>
					<div id="B_9_1-c"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent2('${curAllYearBisBudget.netProfit}',"${resultMap['B_9_1-2'].moneyFact}","${resultMap['B_9_1-2'].moneyArrear}",'B_9_1-c');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.netProfit==null||curMonthBisBudget.netProfit==0">0</s:if><s:else>${curMonthBisBudget.netProfit}</s:else></td>
					<td>
					<div id="B_9_1-a"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.netProfit}',"${resultMap['B_9_1-1'].moneyFact}","${resultMap['B_9_1-1'].moneyArrear}",'B_9_1-a');
					</script>
					</td>
				</tr>
				<tr myid="10.1" ifHasSub="0">
					<td style="background-color:#E4E7EC;">&nbsp;</td>
					<td class="indent_1" style="text-align:left;">&nbsp;&nbsp;净利率</td>
					<!-- 
					<td><s:if test="curMonthBisBudgetVo.profitTotal==null||curMonthBisBudgetVo.profitTotal<=0">0</s:if>
					<s:elseif test="curMonthBisBudget.incomeTax==null">100%</s:elseif>
					<s:else>${(curMonthBisBudgetVo.netProfit)/curMonthBisBudgetVo.profitTotal}</s:else>%</td>
					 -->
					<!--
					<td><s:if test="curYearBisBudgetVo.profitTotal==null||curYearBisBudgetVo.profitTotal<=0">0</s:if>
					<s:elseif test="curYearBisBudget.incomeTax==null">100%</s:elseif>
					<s:else>${(curYearBisBudgetVo.netProfit)/curYearBisBudgetVo.profitTotal}</s:else>%</td>
					
					-->
					<td>
					<div id="10-e"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curYearBisBudget.profitTotal}',"${curYearBisBudget.netProfit}","",'10-e');
					</script>
					</td>
					
					<td>
					<div id="10-b"></div>
				   	<script language="javascript" type="text/javascript">
				   	computePercentForRate('${curYearBisBudget.profitTotal}',"${curYearBisBudget.netProfit}","${resultMap['B_7_1-2'].moneyFact}","${resultMap['B_7_1-2'].moneyArrear}","${resultMap['B_9_1-2'].moneyFact}","${resultMap['B_9_1-2'].moneyArrear}",'10-b');
					</script>
					</td>
					<!-- 
					<td><s:if test="curAllYearBisBudgetVo.profitTotal==null||curAllYearBisBudgetVo.profitTotal<=0">0</s:if>
					<s:elseif test="curAllYearBisBudget.incomeTax==null">100%</s:elseif>
					<s:else>${(curAllYearBisBudgetVo.netProfit)/curAllYearBisBudgetVo.profitTotal}</s:else>%</td>
					 -->
					 <td>
					 <div id="10-f"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curAllYearBisBudget.profitTotal}',"${curAllYearBisBudget.netProfit}","",'10-f');
					</script>
					</td>
					
					<td>
					<div id="10-c"></div>
				   	<script language="javascript" type="text/javascript">
				   	computePercentForRate('${curAllYearBisBudget.profitTotal}',"${curAllYearBisBudget.netProfit}","${resultMap['B_7_1-2'].moneyFact}","${resultMap['B_7_1-2'].moneyArrear}","${resultMap['B_9_1-2'].moneyFact}","${resultMap['B_9_1-2'].moneyArrear}",'10-c');
					</script>
					</td>
					<td>
					<div id="10-d"></div>
				   	<script language="javascript" type="text/javascript">
				    computePercent('${curMonthBisBudget.profitTotal}',"${curMonthBisBudget.netProfit}","",'10-d');
					</script>
					</td>
					
					<td>
					<div id="10-a"></div>
				   	<script language="javascript" type="text/javascript">
				   	computePercentForRate('${curMonthBisBudget.profitTotal}',"${curMonthBisBudget.netProfit}","${resultMap['B_7_1-1'].moneyFact}","${resultMap['B_7_1-1'].moneyArrear}","${resultMap['B_9_1-1'].moneyFact}","${resultMap['B_9_1-1'].moneyArrear}",'10-a');
					</script>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<script type="text/javascript" language="javascript">
isEmpty = function (str) {
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
};
isNotEmpty = function (str) {
	return !isEmpty(str);
};

$(function(){
	$("#tableLeft tr").each(function(){
		if(isNotEmpty($(this).attr("myid"))){
			$(this).bind("click",{myid:$(this).attr("myid")},trClick);
			if(1==$(this).attr("ifHasSub")){
				$(this).css("cursor","pointer");
			}
		}
	});
});
var tempMyId;
var tempifHasSub;
function trClick(event){
	tempMyId = event.data.myid;
	$("#tableLeft tr").each(function(){	
		var otid = $(this).attr("myid");
		if(otid==tempMyId){
			tempifHasSub = $(this).attr("ifHasSub")
		}
	});
	if(isNotEmpty(tempMyId)){
		$("#tableLeft tr").each(function(){
			
			var otid = $(this).attr("myid");
			if(isNotEmpty(otid)){
				if(otid==tempMyId){
					if(1==tempifHasSub){
						$(this).attr("ifHasSub",0);
						$(this).find('span').removeClass('triangle').addClass('triangledown');
					}else{
						$(this).attr("ifHasSub",1);
						$(this).find('span').removeClass('triangledown').addClass('triangle');
					}
				}
				if(tempMyId.length<otid.length){
					if(otid.substring(0,tempMyId.length)==tempMyId && otid.substring(tempMyId.length,tempMyId.length+1)=="."){
						if(1==tempifHasSub){
							$(this).show();
							$(this).attr("ifHasSub",0);
						}else{
							$(this).hide();
							$(this).attr("ifHasSub",1);
						}
					}
				}
			}
		});
	}
}
/*
function computePercent(budgetMoney,factIncomeMoney,arrearIncomeMoney,domId){
	var percent=0;
	if(factIncomeMoney==""){
		factIncomeMoney=0;	
	}
	if(arrearIncomeMoney==""){
		arrearIncomeMoney = 0;	
	}
	var factIncomeMoney2 = parseInt(factIncomeMoney)+parseInt(arrearIncomeMoney);
	
	if(budgetMoney !="" && budgetMoney!="0"){
		percent=(parseInt(factIncomeMoney2)/parseInt(budgetMoney)) *100;
	}

	var divDom=document.getElementById(domId);
	if(divDom){
			var decimalStr=""+percent;		
			if(decimalStr.indexOf(".")>-1 && decimalStr.split(".")[1].length>2){		
				divDom.innerHTML=(percent).toFixed(4)+"%";
			}else{
					divDom.innerHTML=percent+"%";
			}
	}	
}*/
</script>