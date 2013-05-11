<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<script>
try{
    (1).toFixed(1);   //   ie5不支持此方法
}
catch(e)   {
    Number.prototype.toFixed   =   function(dot)   {   //   所以要在这里定义
        with(Math){
            var   m=pow(10,Number(dot));
            var   s   =   (round(this*m)/m).toString();
        }
        if(s.indexOf( '. ')   <   0)
              s   +=   ". ";
        s   +=   "000000000000 ";
        return   s.substr(0,s.indexOf( '. ')+dot+1)+ "a ";
    }
}



function computePercent(budgetMoney,factIncomeMoney,arrearIncomeMoney,domId){
	var percent=0;
	if(factIncomeMoney==""){
		factIncomeMoney=0;	
	}
	if(arrearIncomeMoney==""){
		arrearIncomeMoney = 0;	
	}
	var factIncomeMoney2 = parseFloat(factIncomeMoney)+parseFloat(arrearIncomeMoney);
	
	if(budgetMoney !="" && budgetMoney!="0"){
		percent=(parseFloat(factIncomeMoney2)/parseFloat(budgetMoney)) *100;
	}

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

function mergData(factIncome,arrearIncome,domId){
	var mergData = 0;
	
	if(factIncome == ""){
		factIncome = 0;	
	}
	//alert(arrearIncome);
	if(arrearIncome == ""){
		arrearIncome = 0 ;
	}
	
	mergData = parseFloat(factIncome)+parseFloat(arrearIncome);

	var divDom=document.getElementById(domId);
	if(divDom){
			var decimalStr=""+mergData;		
			if(decimalStr.indexOf(".")>-1 && decimalStr.split(".")[1].length>2){		
				divDom.innerHTML=(mergData).toFixed(2);
				}else{
					divDom.innerHTML=mergData;
				}
			
			}	
	}

</script>
<!-- ${resultMap['1']} -->
<table style="width: 100%" align="center">
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
				<col width="80px">
				<tr  class="headTr"> 
				<td colspan="7" align="center" nowrap="nowrap"style="border-right: 0px;">预算执行情况表</td>
				<td width="33" colspan="1" align="right" style="border-left: 0px;">单位:万元</td>
				</tr>
				
				<tr>
					<th nowrap="nowrap" colspan=2 style="text-align: center;padding-left:5px;">项目</th>
					<th nowrap="nowrap" style="text-align: center;">本年累计实际数合计</th>
					<th nowrap="nowrap" style="text-align: center;">本年累计预算合计</th>
					<th nowrap="nowrap" style="text-align: center;">本年累计预算达成率</th>
					<th nowrap="nowrap" style="text-align: center;">本月实际数合计</th>
					<th nowrap="nowrap" style="text-align: center;">本月预算合计</th>
					<th nowrap="nowrap" style="text-align: center;">本月预算达成率</th>
				</tr>
				<tr><td rowspan="15" id= "incomeId" class="indent_5" style=" font-size:20px;padding-left:5px;text-align: center;" >收入</td></tr>
				<tr myid="1"  ifHasSub="0" rowid="0">
				
					<td class="indent_5" style="text-align:left;">收入合计<span class="triangle"/></td>
					<td>
					<div id="B_1-c"></div>
					<script>
					mergData("${resultMap['B_1-2'].moneyFact+curYearBisBudgetVo.propManage}","${resultMap['B_1-2'].moneyArrear}",'B_1-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudgetVo.incomeTotal==null">0</s:if><s:else>${curYearBisBudgetVo.incomeTotal}</s:else></td>
					
					<td>					
					<div id="B_1-d"></div>
				   	<script>
				    computePercent('${curYearBisBudgetVo.incomeTotal}',"${resultMap['B_1-2'].moneyFact+curYearBisBudgetVo.propManage}","${resultMap['B_1-2'].moneyArrear}",'B_1-d');
					</script>
					</td>
					<td>
					<div id="B_1-a"></div>
					<script>
					mergData("${resultMap['B_1-1'].moneyFact+curMonthBisBudgetVo.propManage}","${resultMap['B_1-1'].moneyArrear}",'B_1-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudgetVo.incomeTotal==null">0</s:if><s:else>${curMonthBisBudgetVo.incomeTotal}</s:else></td>
					<td>
					<div id="B_1-b"></div>
				    <script>
				    computePercent('${curMonthBisBudgetVo.incomeTotal}',"${resultMap['B_1-1'].moneyFact+curMonthBisBudgetVo.propManage}","${resultMap['B_1-1'].moneyArrear}",'B_1-b');
					</script>
					</td>
					
				</tr>
				<tr myid="1.1"  ifHasSub="0" rowid="0">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;物业管理费收入</td>
					<td>
					<div id="B_1_1-c"></div>
					<script>
					mergData("${resultMap['B_1_1-2'].moneyFact+curYearBisBudgetVo.propManage}","${resultMap['B_1_1-2'].moneyArrear}",'B_1_1-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.propManage==null">0</s:if><s:else>${curYearBisBudget.propManage}</s:else></td>
					
					<td>					
					<div id="B_1_1-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.propManage}',"${resultMap['B_1_1-2'].moneyFact+curYearBisBudgetVo.propManage}","${resultMap['B_1_1-2'].moneyArrear}",'B_1_1-d');
					</script>
					</td>
					
					<td>
					<div id="B_1_1-a"></div>
					<script>
					mergData("${resultMap['B_1_1-1'].moneyFact+curMonthBisBudgetVo.propManage}","${resultMap['B_1_1-1'].moneyArrear}",'B_1_1-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.propManage==null">0</s:if><s:else>${curMonthBisBudget.propManage}</s:else></td>
					<td>
					<div id="B_1_1-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.propManage}',"${resultMap['B_1_1-1'].moneyFact+curMonthBisBudgetVo.propManage}","${resultMap['B_1_1-1'].moneyArrear}",'B_1_1-b');
					</script>
					</td>
				</tr>
				<tr myid="1.2" ifHasSub="0" rowid="0">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;停车场收入<span class="triangle"/></td>
					
					<td>
					<div id="B_1_2-c"></div>
					<script>
					mergData("${resultMap['B_1_2-2'].moneyFact}","${resultMap['B_1_2-2'].moneyArrear}",'B_1_2-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.carParking==null">0</s:if><s:else>${curYearBisBudget.carParking}</s:else></td>
					
					<td>					
					<div id="B_1_2-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.carParking}',"${resultMap['B_1_2-2'].moneyFact}","${resultMap['B_1_2-2'].moneyArrear}",'B_1_2-d');
					</script>
					</td>
					<td>
					<div id="B_1_2-a"></div>
					<script>
					mergData("${resultMap['B_1_2-1'].moneyFact}","${resultMap['B_1_2-1'].moneyArrear}",'B_1_2-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.carParking==null">0</s:if><s:else>${curMonthBisBudget.carParking}</s:else></td>
						<td>
					<div id="B_1_2-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.carParking}',"${resultMap['B_1_2-1'].moneyFact}","${resultMap['B_1_2-1'].moneyArrear}",'B_1_2-b');
					</script>
					</td>
				</tr>
				<tr myid="1.2.1" ifHasSub="0" rowid="0">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;车位管理费收入</td>
					
					<td>
					<div id="19-c"></div>
					<script>
					mergData("${resultMap['19-2'].moneyFact}","${resultMap['19-2'].moneyArrear}",'19-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.carManage==null">0</s:if><s:else>${curYearBisBudget.carManage}</s:else></td>
					
					<td>					
					<div id="19-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.carManage}',"${resultMap['19-2'].moneyFact}","${resultMap['19-2'].moneyArrear}",'19-d');
					</script>
					</td>
					
					<td>
					<div id="19-a"></div>
					<script>
					mergData("${resultMap['19-1'].moneyFact}","${resultMap['19-1'].moneyArrear}",'19-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.carManage==null">0</s:if><s:else>${curMonthBisBudget.carManage}</s:else></td>
						<td>
					<div id="19-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.carManage}',"${resultMap['19-1'].moneyFact}","${resultMap['19-1'].moneyArrear}",'19-b');
					</script>
					</td>
					
				</tr>
				<tr  myid="1.2.2" ifHasSub="0" rowid="0">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;临时停车收入</td>
					<td>
					<div id="17-c"></div>
					<script>
					mergData("${resultMap['17-2'].moneyFact}","${resultMap['17-2'].moneyArrear}",'17-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.carTemporary==null">0</s:if><s:else>${curYearBisBudget.carTemporary}</s:else></td>
					
					<td>					
					<div id="17-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.carTemporary}',"${resultMap['17-2'].moneyFact}","${resultMap['17-2'].moneyArrear}",'17-d');
					</script>
					</td>
					<td>
					<div id="17-a"></div>
					<script>
					mergData("${resultMap['17-1'].moneyFact}","${resultMap['17-1'].moneyArrear}",'17-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.carTemporary==null">0</s:if><s:else>${curMonthBisBudget.carTemporary}</s:else></td>
					
					<td>
					<div id="17-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.carTemporary}',"${resultMap['17-1'].moneyFact}","${resultMap['17-1'].moneyArrear}",'17-b');
					</script>
					</td>
				</tr>
				<tr  myid="1.3" ifHasSub="0" rowid="0"><!-- <span class="triangledown"/> -->
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;多种经营收入<span class="triangle"/></td>
					<td>
					<div id="B_1_3-c"></div>
					<script>
					mergData("${resultMap['B_1_3-2'].moneyFact}","${resultMap['B_1_3-2'].moneyArrear}",'B_1_3-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudgetVo.multiTotal==null">0</s:if><s:else>${curYearBisBudgetVo.multiTotal}</s:else></td>
					
					<td>					
					<div id="B_1_3-d"></div>
				   	<script>
				    computePercent('${curYearBisBudgetVo.multiTotal}',"${resultMap['B_1_3-2'].moneyFact}","${resultMap['B_1_3-2'].moneyArrear}",'B_1_3-d');
					</script>
					</td>
					
					<td>
					<div id="B_1_3-a"></div>
					<script>
					mergData("${resultMap['B_1_3-1'].moneyFact}","${resultMap['B_1_3-1'].moneyArrear}",'B_1_3-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudgetVo.multiTotal==null">0</s:if><s:else>${curMonthBisBudgetVo.multiTotal}</s:else></td>
					
					<td>
					<div id="B_1_3-b"></div>
				    <script>
				    computePercent('${curMonthBisBudgetVo.multiTotal}',"${resultMap['B_1_3-1'].moneyFact}","${resultMap['B_1_3-1'].moneyArrear}",'B_1_3-b');
					</script>
					</td>
					
				</tr>
				<tr myid="1.3.1" ifHasSub="0" rowid="0">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;广告场地使用收入</td>
					<td>
					<div id="16-c"></div>
					<script>
					mergData("${resultMap['16-2'].moneyFact}","${resultMap['16-2'].moneyArrear}",'16-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.multiAdvert==null">0</s:if><s:else>${curYearBisBudget.multiAdvert}</s:else></td>
					
					<td>					
					<div id="16-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.multiAdvert}',"${resultMap['16-2'].moneyFact}","${resultMap['16-2'].moneyArrear}",'16-d');
					</script>
					</td>
					<td>
					<div id="16-a"></div>
					<script>
					mergData("${resultMap['16-1'].moneyFact}","${resultMap['16-1'].moneyArrear}",'16-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.multiAdvert==null">0</s:if><s:else>${curMonthBisBudget.multiAdvert}</s:else></td>
					
					<td>
					<div id="16-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.multiAdvert}',"${resultMap['16-1'].moneyFact}","${resultMap['16-1'].moneyArrear}",'16-b');
					</script>
					</td>
					
				</tr>
				<tr myid="1.3.2" ifHasSub="0" rowid="0">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服务费收入</td>
					<td>
					<div id="20-c"></div>
					<script>
					mergData("${resultMap['20-2'].moneyFact}","${resultMap['20-2'].moneyArrear}",'20-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.multiService==null">0</s:if><s:else>${curYearBisBudget.multiService}</s:else></td>
					
					<td>					
					<div id="20-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.multiService}',"${resultMap['20-2'].moneyFact}","${resultMap['20-2'].moneyArrear}",'20-d');
					</script>
					</td>
					<td>
					<div id="20-a"></div>
					<script>
					mergData("${resultMap['20-1'].moneyFact}","${resultMap['20-1'].moneyArrear}",'20-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.multiService==null">0</s:if><s:else>${curMonthBisBudget.multiService}</s:else></td>
					
					<td>
					<div id="20-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.multiService}',"${resultMap['20-1'].moneyFact}","${resultMap['20-1'].moneyArrear}",'20-b');
					</script>
					</td>
				</tr>
				<tr myid="1.3.3" ifHasSub="0" rowid="0">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;维修服务收入</td>
					<td>
					<div id="21-c"></div>
					<script>
					mergData("${resultMap['21-2'].moneyFact}","${resultMap['21-2'].moneyArrear}",'21-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.multiRepair==null">0</s:if><s:else>${curYearBisBudget.multiRepair}</s:else></td>
					
					<td>					
					<div id="21-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.multiRepair}',"${resultMap['21-2'].moneyFact}","${resultMap['21-2'].moneyArrear}",'21-d');
					</script>
					</td>
					
					<td>
					<div id="21-a"></div>
					<script>
					mergData("${resultMap['21-1'].moneyFact}","${resultMap['21-1'].moneyArrear}",'21-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.multiRepair==null">0</s:if><s:else>${curMonthBisBudget.multiRepair}</s:else></td>
					
					<td>
					<div id="21-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.multiRepair}',"${resultMap['21-1'].moneyFact}","${resultMap['21-1'].moneyArrear}",'21-b');
					</script>
					</td>
				</tr>
				<tr myid="1.3.4" ifHasSub="0" rowid="0">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;清洁服务收入</td>
					<td>
					<div id="22-c"></div>
					<script>
					mergData("${resultMap['22-2'].moneyFact}","${resultMap['22-2'].moneyArrear}",'22-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.multiClean==null">0</s:if><s:else>${curYearBisBudget.multiClean}</s:else></td>
					
					<td>					
					<div id="22-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.multiClean}',"${resultMap['22-2'].moneyFact}","${resultMap['22-2'].moneyArrear}",'22-d');
					</script>
					</td>
					
					<td>
					<div id="22-a"></div>
					<script>
					mergData("${resultMap['22-1'].moneyFact}","${resultMap['22-1'].moneyArrear}",'22-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.multiClean==null">0</s:if><s:else>${curMonthBisBudget.multiClean}</s:else></td>
					
					<td>
					<div id="22-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.multiClean}',"${resultMap['22-1'].moneyFact}","${resultMap['22-1'].moneyArrear}",'22-b');
					</script>
					</td>
				</tr>
				<tr myid="1.3.5" ifHasSub="0" rowid="0">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其他收入</td>
					
					<td>
					<div id="B_1_3_5-c"></div>
					<script>
					mergData("${resultMap['B_1_3_5-2'].moneyFact}","${resultMap['B_1_3_5-2'].moneyArrear}",'B_1_3_5-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.multiOther==null">0</s:if><s:else>${curYearBisBudget.multiOther}</s:else></td>
					
					<td>					
					<div id="B_1_3_5-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.multiOther}',"${resultMap['B_1_3_5-2'].moneyFact}","${resultMap['B_1_3_5-2'].moneyArrear}",'B_1_3_5-d');
					</script>
					</td>
					<td>
					<div id="B_1_3_5-a"></div>
					<script>
					mergData("${resultMap['B_1_3_5-1'].moneyFact}","${resultMap['B_1_3_5-1'].moneyArrear}",'B_1_3_5-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.multiOther==null">0</s:if><s:else>${curMonthBisBudget.multiOther}</s:else></td>
					
					<td>
					<div id="B_1_3_5-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.multiOther}',"${resultMap['B_1_3_5-1'].moneyFact}","${resultMap['B_1_3_5-1'].moneyArrear}",'B_1_3_5-b');
					</script>
					</td>
				</tr>
				<tr myid="1.4" ifHasSub="0" rowid="0">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;招商代理费收入<span class="triangle"/></td>
					<td>
					<div id="B_1_4-c"></div>
					<script>
					mergData("${resultMap['B_1_4-2'].moneyFact}","${resultMap['B_1_4-2'].moneyArrear}",'B_1_4-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudgetVo.inviteMerchant==null">0</s:if><s:else>${curYearBisBudgetVo.inviteMerchant}</s:else></td>
					
					<td>					
					<div id="B_1_4-d"></div>
				   	<script>
				    computePercent('${curYearBisBudgetVo.inviteMerchant}',"${resultMap['B_1_4-2'].moneyFact}","${resultMap['B_1_4-2'].moneyArrear}",'B_1_4-d');
					</script>
					</td>
					<td>
					<div id="B_1_4-a"></div>
					<script>
					mergData("${resultMap['B_1_4-1'].moneyFact}","${resultMap['B_1_4-1'].moneyArrear}",'B_1_4-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudgetVo.inviteMerchant==null">0</s:if><s:else>${curMonthBisBudgetVo.inviteMerchant}</s:else></td>
					
					<td>
					<div id="B_1_4-b"></div>
				    <script>
				    computePercent('${curMonthBisBudgetVo.inviteMerchant}',"${resultMap['B_1_4-1'].moneyFact}","${resultMap['B_1_4-1'].moneyArrear}",'B_1_4-b');
					</script>
					</td>
				</tr>
				<tr myid="1.4.1" ifHasSub="0" rowid="0">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;招商佣金</td>
					<td>
					<div id="2-c"></div>
					<script>
					mergData("${resultMap['2-2'].moneyFact}","${resultMap['2-2'].moneyArrear}",'2-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.inviteAgent==null">0</s:if><s:else>${curYearBisBudget.inviteAgent}</s:else></td>
					
					<td>					
					<div id="2-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.inviteAgent}',"${resultMap['2-2'].moneyFact}","${resultMap['2-2'].moneyArrear}",'2-d');
					</script>
					</td>
					
					<td>
					<div id="2-a"></div>
					<script>
					mergData("${resultMap['2-1'].moneyFact}","${resultMap['2-1'].moneyArrear}",'2-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.inviteAgent==null">0</s:if><s:else>${curMonthBisBudget.inviteAgent}</s:else></td>
					
					<td>
					<div id="2-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.inviteAgent}',"${resultMap['2-1'].moneyFact}","${resultMap['2-1'].moneyArrear}",'2-b');
					</script>
					</td>
					
				</tr>
				<tr myid="1.4.2" ifHasSub="0" rowid="0">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;租金管理费</td>
					<td>
					<div id="18-c"></div>
					<script>
					mergData("${resultMap['18-2'].moneyFact}","${resultMap['18-2'].moneyArrear}",'18-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.inviteRent==null">0</s:if><s:else>${curYearBisBudget.inviteRent}</s:else></td>
					
					<td>					
					<div id="18-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.inviteRent}',"${resultMap['18-2'].moneyFact}","${resultMap['18-2'].moneyArrear}",'18-d');
					</script>
					</td>
					
					<td>
					<div id="18-a"></div>
					<script>
					mergData("${resultMap['18-1'].moneyFact}","${resultMap['18-1'].moneyArrear}",'18-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.inviteRent==null">0</s:if><s:else>${curMonthBisBudget.inviteRent}</s:else></td>
					
					<td>
					<div id="18-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.inviteRent}',"${resultMap['18-1'].moneyFact}","${resultMap['18-1'].moneyArrear}",'18-b');
					</script>
					</td>
				</tr>
				<tr><td id="costId" style="font-size:20px; padding-left:5px;text-align: center;" rowspan="16" class="indent_5" >成本费用</td></tr>
				<tr  myid="2" ifHasSub="0" rowid="1">
					<td class="indent_5" style="text-align:left;">成本费用合计<span class="triangle"/></td>
					<td>
					<div id="B_2-c"></div>
					<script>
					mergData("${resultMap['B_2-2'].moneyFact}","${resultMap['B_2-2'].moneyArrear}",'B_2-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudgetVo.payTotal==null">0</s:if><s:else>${curYearBisBudgetVo.payTotal}</s:else></td>
					
					<td>					
					<div id="B_2-d"></div>
				   	<script>
				    computePercent('${curYearBisBudgetVo.payTotal}',"${resultMap['B_2-2'].moneyFact}","${resultMap['B_2-2'].moneyArrear}",'B_2-d');
					</script>
					</td>
					<td>
					<div id="B_2-a"></div>
					<script>
					mergData("${resultMap['B_2-1'].moneyFact}","${resultMap['B_2-1'].moneyArrear}",'B_2-a');
					</script>
					</td>
					<td><s:if test="curMonthBisBudgetVo.payTotal==null">0</s:if><s:else>${curMonthBisBudgetVo.payTotal}</s:else></td>
					
					<td>
					<div id="B_2-b"></div>
				    <script>
				    computePercent('${curMonthBisBudgetVo.payTotal}',"${resultMap['B_2-1'].moneyFact}","${resultMap['B_2-1'].moneyArrear}",'B_2-b');
					</script>
					</td>
				</tr>
				<tr myid="2.1" ifHasSub="0" rowid="1">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;人工福利费</td>
					<td>
					<div id="B_2_1-c"></div>
					<script>
					mergData("${resultMap['B_2_1-2'].moneyFact}","${resultMap['B_2_1-2'].moneyArrear}",'B_2_1-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudgetVo.welfare==null">0</s:if><s:else>${curYearBisBudgetVo.welfare}</s:else></td>
					
					<td>					
					<div id="B_2_1-d"></div>
				   	<script>
				    computePercent('${curYearBisBudgetVo.welfare}',"${resultMap['B_2_1-2'].moneyFact}","${resultMap['B_2_1-2'].moneyArrear}",'B_2_1-d');
					</script>
					</td>
					
					<td>
					<div id="B_2_1-a"></div>
					<script>
					mergData("${resultMap['B_2_1-1'].moneyFact}","${resultMap['B_2_1-1'].moneyArrear}",'B_2_1-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudgetVo.welfare==null">0</s:if><s:else>${curMonthBisBudgetVo.welfare}</s:else></td>
					
					<td>
					<div id="B_2_1-b"></div>
				    <script>
				    computePercent('${curMonthBisBudgetVo.welfare}',"${resultMap['B_2_1-1'].moneyFact}","${resultMap['B_2_1-1'].moneyArrear}",'B_2_1-b');
					</script>
					</td>
				</tr>
				<tr myid="2.2" ifHasSub="0" rowid="1">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;其他人事费用</td>
					<td>
					<div id="B42-c"></div>
					<script>
					mergData("${resultMap['B42-2'].moneyFact}","${resultMap['B42-2'].moneyArrear}",'B42-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.otherPersonnel==null">0</s:if><s:else>${curYearBisBudget.otherPersonnel}</s:else></td>
					
					<td>					
					<div id="B42-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.otherPersonnel}',"${resultMap['B42-2'].moneyFact}","${resultMap['B42-2'].moneyArrear}",'B42-d');
					</script>
					</td>
					
					<td>
					<div id="B42-a"></div>
					<script>
					mergData("${resultMap['B42-1'].moneyFact}","${resultMap['B42-1'].moneyArrear}",'B42-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.otherPersonnel==null">0</s:if><s:else>${curMonthBisBudget.otherPersonnel}</s:else></td>
					
					<td>
					<div id="B42-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.otherPersonnel}',"${resultMap['B42-1'].moneyFact}","${resultMap['B42-1'].moneyArrear}",'B42-b');
					</script>
					</td>
					
				</tr>
				<tr myid="2.3" ifHasSub="0" rowid="1">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;行政类费用</td>
					
					<td>
					<div id="B36-c"></div>
					<script>
					mergData("${resultMap['B36-2'].moneyFact}","${resultMap['B36-2'].moneyArrear}",'B36-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.administraFee==null">0</s:if><s:else>${curYearBisBudget.administraFee}</s:else></td>
					
					<td>					
					<div id="B36-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.administraFee}',"${resultMap['B36-2'].moneyFact}","${resultMap['B36-2'].moneyArrear}",'B36-d');
					</script>
					</td>
					<td>
					<div id="B36-a"></div>
					<script>
					mergData("${resultMap['B36-1'].moneyFact}","${resultMap['B36-1'].moneyArrear}",'B36-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.administraFee==null">0</s:if><s:else>${curMonthBisBudget.administraFee}</s:else></td>
					
					<td>
					<div id="B36-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.administraFee}',"${resultMap['B36-1'].moneyFact}","${resultMap['B36-1'].moneyArrear}",'B36-b');
					</script>
					</td>
				</tr>
				<tr myid="2.3" ifHasSub="0" rowid="1">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;差旅费</td>
					<td>
					<div id="B35-c"></div>
					<script>
					mergData("${resultMap['B35-2'].moneyFact}","${resultMap['B35-2'].moneyArrear}",'B35-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.travelFee==null">0</s:if><s:else>${curYearBisBudget.travelFee}</s:else></td>
					
					<td>					
					<div id="B35-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.travelFee}',"${resultMap['B35-2'].moneyFact}","${resultMap['B35-2'].moneyArrear}",'B35-d');
					</script>
					</td>
					<td>
					<div id="B35-a"></div>
					<script>
					mergData("${resultMap['B35-1'].moneyFact}","${resultMap['B35-1'].moneyArrear}",'B35-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.travelFee==null">0</s:if><s:else>${curMonthBisBudget.travelFee}</s:else></td>
					
					<td>
					<div id="B35-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.travelFee}',"${resultMap['B35-1'].moneyFact}","${resultMap['B35-1'].moneyArrear}",'B35-b');
					</script>
					</td>
					
				</tr>
				<tr myid="2.4" ifHasSub="0" rowid="1">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;业务招待费</td>
					<td>
					<div id="B37-c"></div>
					<script>
					mergData("${resultMap['B37-2'].moneyFact}","${resultMap['B37-2'].moneyArrear}",'B37-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.businessServe==null">0</s:if><s:else>${curYearBisBudget.businessServe}</s:else></td>
					
					<td>					
					<div id="B37-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.businessServe}',"${resultMap['B37-2'].moneyFact}","${resultMap['B37-2'].moneyArrear}",'B37-d');
					</script>
					</td>
					<td>
					<div id="B37-a"></div>
					<script>
					mergData("${resultMap['B37-1'].moneyFact}","${resultMap['B37-1'].moneyArrear}",'B37-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.businessServe==null">0</s:if><s:else>${curMonthBisBudget.businessServe}</s:else></td>
					
					<td>
					<div id="B37-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.businessServe}',"${resultMap['B37-1'].moneyFact}","${resultMap['B37-1'].moneyArrear}",'B37-b');
					</script>
					</td>
				</tr>
				<tr  myid="2.5" ifHasSub="0" rowid="1">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;广告宣传费</td>
					<td>
					<div id="B21-c"></div>
					<script>
					mergData("${resultMap['B21-2'].moneyFact}","${resultMap['B21-2'].moneyArrear}",'B21-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.adivertise==null">0</s:if><s:else>${curYearBisBudget.adivertise}</s:else></td>
					
					<td>					
					<div id="B21-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.adivertise}',"${resultMap['B21-2'].moneyFact}","${resultMap['B21-2'].moneyArrear}",'B21-d');
					</script>
					</td>
					<td>
					<div id="B21-a"></div>
					<script>
					mergData("${resultMap['B21-1'].moneyFact}","${resultMap['B21-1'].moneyArrear}",'B21-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.adivertise==null">0</s:if><s:else>${curMonthBisBudget.adivertise}</s:else></td>
					
					<td>
					<div id="B21-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.adivertise}',"${resultMap['B21-1'].moneyFact}","${resultMap['B21-1'].moneyArrear}",'B21-b');
					</script>
					</td>
				</tr>
				<tr  myid="2.6" ifHasSub="0" rowid="1">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;招商奖金</td>
					
					<td>
					<div id="B22-c"></div>
					<script>
					mergData("${resultMap['B22-2'].moneyFact}","${resultMap['B22-2'].moneyArrear}",'B22-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.inviteReward==null">0</s:if><s:else>${curYearBisBudget.inviteReward}</s:else></td>
					
					<td>					
					<div id="B22-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.inviteReward}',"${resultMap['B22-2'].moneyFact}","${resultMap['B22-2'].moneyArrear}",'B22-d');
					</script>
					</td>
					<td>
					<div id="B22-a"></div>
					<script>
					mergData("${resultMap['B22-1'].moneyFact}","${resultMap['B22-1'].moneyArrear}",'B22-a');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.inviteReward==null">0</s:if><s:else>${curMonthBisBudget.inviteReward}</s:else></td>
					
					<td>
					<div id="B22-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.inviteReward}',"${resultMap['B22-1'].moneyFact}","${resultMap['B22-1'].moneyArrear}",'B22-b');
					</script>
					</td>
					
				</tr>
				<tr  myid="2.7" ifHasSub="0" rowid="1">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;清洁卫生费</td>
					
					<td>
					<div id="B1-c"></div>
					<script>
					mergData("${resultMap['B1-2'].moneyFact}","${resultMap['B1-2'].moneyArrear}",'B1-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.cleanHealthCost==null">0</s:if><s:else>${curYearBisBudget.cleanHealthCost}</s:else></td>
					
					<td>					
					<div id="B1-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.cleanHealthCost}',"${resultMap['B1-2'].moneyFact}","${resultMap['B1-2'].moneyArrear}",'B1-d');
					</script>
					</td>
					<td>
					<div id="B1-a"></div>
					<script>
					mergData("${resultMap['B1-1'].moneyFact}","${resultMap['B1-1'].moneyArrear}",'B1-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.cleanHealthCost==null">0</s:if><s:else>${curMonthBisBudget.cleanHealthCost}</s:else></td>
					
					<td>
					<div id="B1-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.cleanHealthCost}',"${resultMap['B1-1'].moneyFact}","${resultMap['B1-1'].moneyArrear}",'B1-b');
					</script>
					</td>
					
				</tr>
				<tr myid="2.8" ifHasSub="0" rowid="1">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;安全保卫费</td>
					
					<td>
					<div id="B2-c"></div>
					<script>
					mergData("${resultMap['B2-2'].moneyFact}","${resultMap['B2-2'].moneyArrear}",'B2-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.safeGuard==null">0</s:if><s:else>${curYearBisBudget.safeGuard}</s:else></td>
					
					<td>					
					<div id="B2-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.safeGuard}',"${resultMap['B2-2'].moneyFact}","${resultMap['B2-2'].moneyArrear}",'B2-d');
					</script>
					</td>
					<td>
					<div id="B2-a"></div>
					<script>
					mergData("${resultMap['B2-1'].moneyFact}","${resultMap['B2-1'].moneyArrear}",'B2-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.safeGuard==null">0</s:if><s:else>${curMonthBisBudget.safeGuard}</s:else></td>
					
					<td>
					<div id="B2-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.safeGuard}',"${resultMap['B2-1'].moneyFact}","${resultMap['B2-1'].moneyArrear}",'B2-b');
					</script>
					</td>
					
				</tr>
				<tr  myid="2.9" ifHasSub="0" rowid="1">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;绿化养护费</td>
					
					<td>
					<div id="B3-c"></div>
					<script>
					mergData("${resultMap['B3-2'].moneyFact}","${resultMap['B3-2'].moneyArrear}",'B3-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.afforeMainten==null">0</s:if><s:else>${curYearBisBudget.afforeMainten}</s:else></td>
					
					<td>					
					<div id="B3-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.afforeMainten}',"${resultMap['B3-2'].moneyFact}","${resultMap['B3-2'].moneyArrear}",'B3-d');
					</script>
					</td>
					<td>
					<div id="B3-a"></div>
					<script>
					mergData("${resultMap['B3-1'].moneyFact}","${resultMap['B3-1'].moneyArrear}",'B3-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.afforeMainten==null">0</s:if><s:else>${curMonthBisBudget.afforeMainten}</s:else></td>
					
					<td>
					<div id="B3-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.afforeMainten}',"${resultMap['B3-1'].moneyFact}","${resultMap['B3-1'].moneyArrear}",'B3-b');
					</script>
					</td>
					
				</tr>
				<tr myid="2.10" ifHasSub="0" rowid="1">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;工程维保费</td>
					
					<td>
					<div id="B4-c"></div>
					<script>
					mergData("${resultMap['B4-2'].moneyFact}","${resultMap['B4-2'].moneyArrear}",'B4-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.engineerMainten==null">0</s:if><s:else>${curYearBisBudget.engineerMainten}</s:else></td>
					
					<td>					
					<div id="B4-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.engineerMainten}',"${resultMap['B4-2'].moneyFact}","${resultMap['B4-2'].moneyArrear}",'B4-d');
					</script>
					</td>
					<td>
					<div id="B4-a"></div>
					<script>
					mergData("${resultMap['B4-1'].moneyFact}","${resultMap['B4-1'].moneyArrear}",'B4-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.engineerMainten==null">0</s:if><s:else>${curMonthBisBudget.engineerMainten}</s:else></td>
					
					<td>
					<div id="B4-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.engineerMainten}',"${resultMap['B4-1'].moneyFact}","${resultMap['B4-1'].moneyArrear}",'B4-b');
					</script>
					</td>
					
				</tr>
				<tr myid="2.11" ifHasSub="0" rowid="1">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;能源费</td>
					
					<td>
					<div id="B25-c"></div>
					<script>
					mergData("${resultMap['B25-2'].moneyFact}","${resultMap['B25-2'].moneyArrear}",'B25-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.energyCost==null">0</s:if><s:else>${curYearBisBudget.energyCost}</s:else></td>
					
					<td>					
					<div id="B25-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.energyCost}',"${resultMap['B25-2'].moneyFact}","${resultMap['B25-2'].moneyArrear}",'B25-d');
					</script>
					</td>
					<td>
					<div id="B25-a"></div>
					<script>
					mergData("${resultMap['B25-1'].moneyFact}","${resultMap['B25-1'].moneyArrear}",'B25-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.energyCost==null">0</s:if><s:else>${curMonthBisBudget.energyCost}</s:else></td>
					
					<td>
					<div id="B25-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.energyCost}',"${resultMap['B25-1'].moneyFact}","${resultMap['B25-1'].moneyArrear}",'B25-b');
					</script>
					</td>
					
				</tr>
				<tr myid="2.12" ifHasSub="0" rowid="1">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;其他费用</td>
					
					<td>
					<div id="B_2_13-c"></div>
					<script>
					mergData("${resultMap['B_2_13-2'].moneyFact}","${resultMap['B_2_13-2'].moneyArrear}",'B_2_13-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.otherPay==null">0</s:if><s:else>${curYearBisBudget.otherPay}</s:else></td>
					
					<td>					
					<div id="B_2_13-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.otherPay}',"${resultMap['B_2_13-2'].moneyFact}","${resultMap['B_2_13-2'].moneyArrear}",'B_2_13-d');
					</script>
					</td>
					<td>
					<div id="B_2_13-a"></div>
					<script>
					mergData("${resultMap['B_2_13-1'].moneyFact}","${resultMap['B_2_13-1'].moneyArrear}",'B_2_13-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.otherPay==null">0</s:if><s:else>${curMonthBisBudget.otherPay}</s:else></td>
					
					<td>
					<div id="B_2_13-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.otherPay}',"${resultMap['B_2_13-1'].moneyFact}","${resultMap['B_2_13-1'].moneyArrear}",'B_2_13-b');
					</script>
					</td>
					
				</tr>
				<tr myid="2.13" ifHasSub="0" rowid="1">
					<td class="indent_5" style="text-align:left;">&nbsp;&nbsp;&nbsp;立项费用（大营运专用）</td>
					
					<td>
					<div id="B38-c"></div>
					<script>
					mergData("${resultMap['B38-2'].moneyFact}","${resultMap['B38-2'].moneyArrear}",'B38-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.setupProject==null">0</s:if><s:else>${curYearBisBudget.setupProject}</s:else></td>
					
					<td>					
					<div id="B38-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.setupProject}',"${resultMap['B38-2'].moneyFact}","${resultMap['B38-2'].moneyArrear}",'B38-d');
					</script>
					</td>
					<td>
					<div id="B38-a"></div>
					<script>
					mergData("${resultMap['B38-1'].moneyFact}","${resultMap['B38-1'].moneyArrear}",'B38-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.setupProject==null">0</s:if><s:else>${curMonthBisBudget.setupProject}</s:else></td>
					
					<td>
					<div id="B38-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.setupProject}',"${resultMap['B38-1'].moneyFact}","${resultMap['B38-1'].moneyArrear}",'B38-b');
					</script>
					</td>
					
				</tr>
				<tr  ifHasSub="0" >
					<td colspan=2 class="indent_5" style="text-align:left;">营业税金及附加</td>
					
					<td>
					<div id="B19-c"></div>
					<script>
					mergData("${resultMap['B19-2'].moneyFact}","${resultMap['B19-2'].moneyArrear}",'B19-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.salesExtra==null">0</s:if><s:else>${curYearBisBudget.salesExtra}</s:else></td>
					
					<td>					
					<div id="B19-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.salesExtra}',"${resultMap['B19-2'].moneyFact}","${resultMap['B19-2'].moneyArrear}",'B19-d');
					</script>
					</td>
					<td>
					<div id="B19-a"></div>
					<script>
					mergData("${resultMap['B19-1'].moneyFact}","${resultMap['B19-1'].moneyArrear}",'B19-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.salesExtra==null">0</s:if><s:else>${curMonthBisBudget.salesExtra}</s:else></td>
					
					<td>
					<div id="B19-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.salesExtra}',"${resultMap['B19-1'].moneyFact}","${resultMap['B19-1'].moneyArrear}",'B19-b');
					</script>
					</td>
				</tr>
				
				<!-- 营业利润特殊处理 -->
				<!-- 本月实收+公寓物业管理费 -->
				<s:if test="resultMap['B_4-1'].moneyFact==null">
				<c:set value="${curMonthBisBudgetVo.propManage}" var="bisprofit_month_moneyFact"/>
				</s:if>
				<s:else>
				<c:set value="${resultMap['B_4-1'].moneyFact+curMonthBisBudgetVo.propManage}" var="bisprofit_month_moneyFact"/>
				</s:else>
				<!-- 本月回收欠费 -->
				<s:if test="resultMap['B_4-1'].moneyArrear==null">
				<c:set value="0" var="bisprofit_month_moneyArrear"/>
				</s:if>
				<s:else>
				<c:set value="${resultMap['B_4-1'].moneyArrear}" var="bisprofit_month_moneyArrear"/>
				</s:else>
				
				<!-- 本年累计实收+本年累计公寓物业管理费 -->
				<s:if test="resultMap['B_4-2'].moneyFact==null">
				<c:set value="${curYearBisBudgetVo.propManage}" var="bisprofit_year_moneyFact"/>
				</s:if> 
				<s:else>
				<c:set value="${resultMap['B_4-2'].moneyFact+curYearBisBudgetVo.propManage}" var="bisprofit_year_moneyFact"/>
				</s:else>
				<!--本年回收费用  -->
				<s:if test="resultMap['B_4-2'].moneyArrear==null">
				<c:set value="0" var="bisprofit_year_moneyArrear"/>
				</s:if>
				<s:else>
				<c:set value="${resultMap['B_4-2'].moneyArrear}" var="bisprofit_year_moneyArrear"/>
				</s:else>
			
				<tr  ifHasSub="0">
					<td colspan=2 class="indent_5" style="text-align:left;">营业利润</td>
					
					<td>
					<div id="B_4-c"></div>
					<script>
					mergData("${bisprofit_year_moneyFact}","${bisprofit_year_moneyArrear}",'B_4-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.bisProfit==null">0</s:if><s:else>${curYearBisBudget.bisProfit}</s:else></td>
					
					<td>					
					<div id="B_4-d"></div>
				   	<script>
				    computePercent('${curYearBisBudgetVo.bisProfit}',"${bisprofit_year_moneyFact}","${bisprofit_year_moneyArrear}",'B_4-d');
					</script>
					</td>
					<td>
					<div id="B_4-a"></div>
					<script>
					mergData("${bisprofit_month_moneyFact}","${bisprofit_month_moneyArrear}",'B_4-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.bisProfit==null">0</s:if><s:else>${curMonthBisBudget.bisProfit}</s:else></td>
					
					<td>
					<div id="B_4-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.bisProfit}',"${bisprofit_month_moneyFact}","${bisprofit_month_moneyArrear}",'B_4-b');
					</script>
					</td>
				</tr>
				<tr  ifHasSub="0">
					<td colspan=2 class="indent_5" style="text-align:left;">&nbsp;&nbsp;加：营业外收入</td>
					
					<td>
					<div id="B29-c"></div>
					<script>
					mergData("${resultMap['B29-2'].moneyFact}","${resultMap['B29-2'].moneyArrear}",'B29-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.bisIncome==null">0</s:if><s:else>${curYearBisBudget.bisIncome}</s:else></td>
					
					<td>					
					<div id="B29-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.bisIncome}',"${resultMap['B29-2'].moneyFact}","${resultMap['B29-2'].moneyArrear}",'B29-d');
					</script>
					</td>
					<td>
					<div id="B29-a"></div>
					<script>
					mergData("${resultMap['B29-1'].moneyFact}","${resultMap['B29-1'].moneyArrear}",'B29-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.bisIncome==null">0</s:if><s:else>${curMonthBisBudget.bisIncome}</s:else></td>
					
					<td>
					<div id="B29-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.bisIncome}',"${resultMap['B29-1'].moneyFact}","${resultMap['B29-1'].moneyArrear}",'B29-b');
					</script>
					</td>
				</tr>
				<tr  ifHasSub="0">
					<td colspan=2 class="indent_5" style="text-align:left;">&nbsp;&nbsp;减：营业外支出</td>
					
					<td>
					<div id="B34-c"></div>
					<script>
					mergData("${resultMap['B34-2'].moneyFact}","${resultMap['B34-2'].moneyArrear}",'B34-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.bisExpense==null">0</s:if><s:else>${curYearBisBudget.bisExpense}</s:else></td>
					
					<td>					
					<div id="B34-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.bisExpense}',"${resultMap['B34-2'].moneyFact}","${resultMap['B34-2'].moneyArrear}",'B34-d');
					</script>
					</td>
					<td>
					<div id="B34-a"></div>
					<script>
					mergData("${resultMap['B34-1'].moneyFact}","${resultMap['B34-1'].moneyArrear}",'B34-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.bisExpense==null">0</s:if><s:else>${curMonthBisBudget.bisExpense}</s:else></td>
					
					<td>
					<div id="B34-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.bisExpense}',"${resultMap['B34-1'].moneyFact}","${resultMap['B34-1'].moneyArrear}",'B34-b');
					</script>
					</td>
				</tr>
				
				<!-- 利润总和 -->
				<s:if test="resultMap['B34-1'].moneyFact==null&&resultMap['B29-1'].moneyFact==null">
				<c:set value="${bisprofit_month_moneyFact}" var="profitTotal_month_moneyFact"/>
				</s:if>
				<s:elseif test="resultMap['B34-1'].moneyFact==null">
				<c:set value="${bisprofit_month_moneyFact+resultMap['B29-1'].moneyFact}" var="profitTotal_month_moneyFact"/>
				</s:elseif> 
				<s:elseif test="resultMap['B29-1'].moneyFact==null">
				<c:set value="${bisprofit_month_moneyFact-resultMap['B34-1'].moneyFact}" var="profitTotal_month_moneyFact"/>
				</s:elseif>
				<s:else>
				<c:set value="${bisprofit_month_moneyFact-resultMap['B34-1'].moneyFact+resultMap['B29-1'].moneyFact}" var="profitTotal_month_moneyFact"/>
				</s:else>
				
				<s:if test="resultMap['B34-1'].moneyArrear==null&&resultMap['B29-1'].moneyArrear==null">
				<c:set value="${bisprofit_month_moneyArrear}" var="profitTotal_month_moneyArrear"/>
				</s:if>
				<s:elseif test="resultMap['B34-1'].moneyArrear==null">
				<c:set value="${bisprofit_month_moneyArrear+resultMap['B29-1'].moneyArrear}" var="profitTotal_month_moneyArrear"/>
				</s:elseif> 
				<s:elseif test="resultMap['B29-1'].moneyArrear==null">
				<c:set value="${bisprofit_month_moneyArrear-resultMap['B34-1'].moneyArrear}" var="profitTotal_month_moneyArrear"/>
				</s:elseif>
				<s:else>
				<c:set value="${bisprofit_month_moneyArrear-resultMap['B34-1'].moneyArrear+resultMap['B29-1'].moneyArrear}" var="profitTotal_month_moneyArrear"/>
				</s:else>
				
				<s:if test="resultMap['B34-2'].moneyFact==null&&resultMap['B29-2'].moneyFact==null">
				<c:set value="${bisprofit_year_moneyFact}" var="profitTotal_year_moneyFact"/>
				</s:if>
				<s:elseif test="resultMap['B34-2'].moneyFact==null">
				<c:set value="${bisprofit_year_moneyFact+resultMap['B29-2'].moneyFact}" var="profitTotal_year_moneyFact"/>
				</s:elseif> 
				<s:elseif test="resultMap['B29-2'].moneyFact==null">
				<c:set value="${bisprofit_year_moneyFact-resultMap['B34-2'].moneyFact}" var="profitTotal_year_moneyFact"/>
				</s:elseif>
				<s:else>
				<c:set value="${bisprofit_year_moneyFact-resultMap['B34-2'].moneyFact+resultMap['B29-2'].moneyFact}" var="profitTotal_year_moneyFact"/>
				</s:else>
				
				<s:if test="resultMap['B34-2'].moneyArrear==null&&resultMap['B29-1'].moneyArrear==null">
				<c:set value="${bisprofit_year_moneyArrear}" var="profitTotal_year_moneyArrear"/>
				</s:if>
				<s:elseif test="resultMap['B34-2'].moneyArrear==null">
				<c:set value="${bisprofit_year_moneyArrear+resultMap['B29-2'].moneyArrear}" var="profitTotal_year_moneyArrear"/>
				</s:elseif> 
				<s:elseif test="resultMap['B29-2'].moneyArrear==null">
				<c:set value="${bisprofit_year_moneyArrear-resultMap['B34-2'].moneyArrear}" var="profitTotal_year_moneyArrear"/>
				</s:elseif>
				<s:else>
				<c:set value="${bisprofit_year_moneyArrear-resultMap['B34-2'].moneyArrear+resultMap['B29-2'].moneyArrear}" var="profitTotal_year_moneyArrear"/>
				</s:else>
				
				<tr  ifHasSub="0">
					<td colspan=2 class="indent_5" style="text-align:left;">利润总额</td>
					
					<td>
					<div id="B_7-c"></div>
					<script>
					mergData("${profitTotal_year_moneyFact}","${profitTotal_year_moneyArrear}",'B_7-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.profitTotal==null">0</s:if><s:else>${curYearBisBudget.profitTotal}</s:else></td>
					
					<td>					
					<div id="B_7-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.profitTotal}',"${profitTotal_year_moneyFact}","${profitTotal_year_moneyArrear}",'B_7-d');
					</script>
					</td>
					<td>
					<div id="B_7-a"></div>
					<script>
					mergData("${profitTotal_month_moneyFact}","${profitTotal_month_moneyArrear}",'B_7-a');
					</script>
					</td>
					<td><s:if test="curMonthBisBudget.profitTotal==null">0</s:if><s:else>${curMonthBisBudget.profitTotal}</s:else></td>
					
					<td>
					<div id="B_7-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.profitTotal}',"${profitTotal_month_moneyFact}","${profitTotal_month_moneyArrear}",'B_7-b');
					</script>
					</td>
					
				</tr>
				<tr  ifHasSub="0">
					<td colspan=2 class="indent_5" style="text-align:left;">所得税</td>
					
					<td>
					<div id="B31-c"></div>
					<script>
					mergData("${resultMap['B31-2'].moneyFact}","${resultMap['B31-2'].moneyArrear}",'B31-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.incomeTax==null">0</s:if><s:else>${curYearBisBudget.incomeTax}</s:else></td>
					
					<td>					
					<div id="B31-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.incomeTax}',"${resultMap['B31-2'].moneyFact}","${resultMap['B31-2'].moneyArrear}",'B31-d');
					</script>
					</td>
					<td>
					<div id="B31-a"></div>
					<script>
					mergData("${resultMap['B31-1'].moneyFact}","${resultMap['B31-1'].moneyArrear}",'B31-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.incomeTax==null">0</s:if><s:else>${curMonthBisBudget.incomeTax}</s:else></td>
					
					<td>
					<div id="B31-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.incomeTax}',"${resultMap['B31-1'].moneyFact}","${resultMap['B31-1'].moneyArrear}",'B31-b');
					</script>
					</td>
					
				</tr>
				
				<tr  ifHasSub="0">
					<td colspan=2 class="indent_5" style="text-align:left;">净利润</td>
					
					<td>
					<div id="B_9-c"></div>
					<script>
					mergData("${profitTotal_year_moneyFact-resultMap['B31-2'].moneyFact}","${profitTotal_year_moneyArrear-resultMap['B31-2'].moneyArrear}",'B_9-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.netProfit==null">0</s:if><s:else>${curYearBisBudget.netProfit}</s:else></td>
					
					<td>					
					<div id="B_9-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.netProfit}',"${profitTotal_year_moneyFact-resultMap['B31-2'].moneyFact}","${profitTotal_year_moneyArrear-resultMap['B31-2'].moneyArrear}",'B_9-d');
					</script>
					</td>
					<td>
					<div id="B_9-a"></div>
					<script>
					mergData("${profitTotal_month_moneyFact-resultMap['B31-1'].moneyFact}","${profitTotal_month_moneyArrear-resultMap['B31-1'].moneyArrear}",'B_9-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.netProfit==null">0</s:if><s:else>${curMonthBisBudget.netProfit}</s:else></td>
					
					<td>
					<div id="B_9-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.netProfit}',"${profitTotal_month_moneyFact-resultMap['B31-1'].moneyFact}","${profitTotal_month_moneyArrear-resultMap['B31-1'].moneyArrear}",'B_9-b');
					</script>
					</td>
					
				</tr>
				<tr  ifHasSub="0">
					<td colspan=2 class="indent_5" style="text-align:left;">租金</td>
					
					<td>
					<div id="1-c"></div>
					<script>
					mergData("${resultMap['1-2'].moneyFact}","${resultMap['1-2'].moneyArrear}",'1-c');
					</script>
					</td>
					
					<td><s:if test="curYearBisBudget.rentTotal==null">0</s:if><s:else>${curYearBisBudget.rentTotal}</s:else></td>
					
					<td>					
					<div id="1-d"></div>
				   	<script>
				    computePercent('${curYearBisBudget.rentTotal}',"${resultMap['1-2'].moneyFact}","${resultMap['1-2'].moneyArrear}",'1-d');
					</script>
					</td>
					<td>
					<div id="1-a"></div>
					<script>
					mergData("${resultMap['1-1'].moneyFact}","${resultMap['1-1'].moneyArrear}",'1-a');
					</script>
					</td>
					
					<td><s:if test="curMonthBisBudget.rentTotal==null">0</s:if><s:else>${curMonthBisBudget.rentTotal}</s:else></td>
					
					<td>
					<div id="1-b"></div>
				    <script>
				    computePercent('${curMonthBisBudget.rentTotal}',"${resultMap['1-1'].moneyFact}","${resultMap['1-1'].moneyArrear}",'1-b');
					</script>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<div style="height:5px;"></div>
<script language="javascript">
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
			tempifHasSub = $(this).attr("ifHasSub");
		}
	});
	var costrow = 1;
	var incomeRow = 1;
	incomeRow = document.getElementById("incomeId").rowSpan;
	costrow = document.getElementById("costId").rowSpan;
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
					//alert(tempMyId+";"+otid);
					if(otid.substring(0,tempMyId.length)==tempMyId){
						if(1==tempifHasSub){
							$(this).show();
							$(this).attr("ifHasSub",0);

							var rowid = $(this).attr("rowid");
							if(rowid==0){
								incomeRow = incomeRow+1;
							}else{
								costrow = costrow+1;
							}
						}else{
							$(this).hide();
							$(this).attr("ifHasSub",1);

							var rowid = $(this).attr("rowid");
							if(rowid==0){
								incomeRow = incomeRow-1;
							}else{
								costrow = costrow -1;
							}
						}
					}
				}
			}
		});
		document.getElementById("incomeId").rowSpan = incomeRow;
		document.getElementById("costId").rowSpan = costrow;
	}
}

</script>