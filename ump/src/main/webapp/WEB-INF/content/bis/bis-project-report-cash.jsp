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



function computePercent(budgetMoney,factIncomeMoney,domId){
var percent=0;
if(factIncomeMoney==""){
	factIncomeMoney=0;	
}
if(budgetMoney !="" && budgetMoney!="0"){
	percent=(parseFloat(factIncomeMoney)/parseFloat(budgetMoney)) *100;
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
}
</script>
<!-- ${resultMap['1']} -->
<table style="width: 100%" align="center">
	<tr>
		<td valign="top">
			<table id="tableLeft" class="mTable" cellpadding="0" cellspacing="0">
				<col width="180px">
				<col width="80px"/>
				<col width="80px"/>
				<col width="80px"/>
				<col width="80px"/>
				<col width="80px"/>
				<col width="80px"/>
				<tr class="headTr">
					<td>项目资金流量表</td>
					<td nowrap="nowrap" colspan="3" >${month}月</td>
					<td nowrap="nowrap" colspan="3" style="border-left:0px;">本年度</td>
				</tr>
				<tr>
					<th nowrap="nowrap" style="text-align: left;padding-left:5px;">(单位：万元)</th>
					<th nowrap="nowrap" style="text-align: center;">预算</th>
					<th nowrap="nowrap" style="text-align: center;">实收</th>
					<th nowrap="nowrap" style="text-align: center;">完成率</th>
					<th nowrap="nowrap" style="text-align: center;">预算</th>
					<th nowrap="nowrap" style="text-align: center;">实收</th>
					<th nowrap="nowrap" style="text-align: center;">完成率</th>
				</tr>
				<tr myid="1" ifHasSub="1">
					<td class="indent_1" style="text-align:left;">1&nbsp;资金流入合计<span class="triangle"/></td>
					<td>${curMonthBisBudget.incomeTotal}</td>
					<td><s:if test="resultMap['A_1-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_1-1'].moneyFact}</s:else></td>
					<td>
					<div id="A_1-1"></div>
				   <script>
				    computePercent('${curMonthBisBudget.payTotal}',"${resultMap['A_1-1'].moneyFact}",'A_1-1');
					</script>
					</td>
					<td>${curYearBisBudget.incomeTotal}</td>
					<td><s:if test="resultMap['A_1-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_1-2'].moneyFact}</s:else></td>
					<td>					
					<div id="A_1-2"></div>
				   <script>
				    computePercent('${curYearBisBudget.payTotal}',"${resultMap['A_1-2'].moneyFact}",'A_1-2');
					</script>
					</td>
				</tr>
				<tr myid="1.1" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;1.1&nbsp;物业管理费收入</td>
					<td>${curMonthBisBudget.propManage}</td>
					<td><s:if test="resultMap['A_1_1-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_1-1'].moneyFact}</s:else></td>
					<td>
					<div id="A_1_1-1"></div>
				    <script>
				     computePercent('${curMonthBisBudget.propManage}',"${resultMap['A_1_1-1'].moneyFact}",'A_1_1-1');
					</script>
					</td>
					<td>${curYearBisBudget.propManage}</td>
					<td><s:if test="resultMap['A_1_1-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_1-2'].moneyFact}</s:else></td>
					<td>
					<div id="A_1_1-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.propManage}',"${resultMap['A_1_1-2'].moneyFact}",'A_1_1-2');
					</script>
					
					</td>
				</tr>
				<tr myid="1.2" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;1.2&nbsp;停车场收入<span class="triangle"/></td>
					<td>${curMonthBisBudget.carParking}</td>
					<td><s:if test="resultMap['A_1_2-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_2-1'].moneyFact}</s:else></td>
					<td>
					<div id="A_1_2-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.carParking}',"${resultMap['A_1_2-1'].moneyFact}",'A_1_2-1');
					</script>
					
					</td>
					<td>${curYearBisBudget.carParking}</td>
					<td><s:if test="resultMap['A_1_2-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_2-2'].moneyFact}</s:else></td>
					<td>
					
					<div id="A_1_2-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.carParking}',"${resultMap['A_1_2-2'].moneyFact}",'A_1_2-2');
					</script>
					
					</td>
				</tr>
				<tr myid="1.2.1" ifHasSub="0" style="display:none;">
					<td class="indent_3" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;1.2.1&nbsp;车位管理费收入</td>
					<td>${curMonthBisBudget.carManage}</td>
					<td><s:if test="resultMap['19-1'].moneyFact==null">0</s:if><s:else>${resultMap['19-1'].moneyFact}</s:else></td>
					<td>
					<div id="19-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.carManage}',"${resultMap['19-1'].moneyFact}",'19-1');
					</script>
					</td>
					<td>${curYearBisBudget.carManage}</td>
					<td><s:if test="resultMap['19-2'].moneyFact==null">0</s:if><s:else>${resultMap['19-2'].moneyFact}</s:else></td>
					<td>
					<div id="19-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.carManage}',"${resultMap['19-2'].moneyFact}",'19-2');
					</script>
					</td>
				</tr>
				<tr myid="1.2.2" ifHasSub="0" style="display:none;">
					<td class="indent_3" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;1.2.2&nbsp;临时停车收入</td>
					<td>${curMonthBisBudget.carTemporary}</td>
					<td><s:if test="resultMap['17-1'].moneyFact==null">0</s:if><s:else>${resultMap['17-1'].moneyFact}</s:else></td>
					<td>
					<div id="17-1" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.carTemporary}',"${resultMap['17-1'].moneyFact}",'17-1');
					</script>
					</td>
					<td>${curYearBisBudget.carTemporary}</td>
					<td><s:if test="resultMap['17-2'].moneyFact==null">0</s:if><s:else>${resultMap['17-2'].moneyFact}</s:else></td>
					<td>
					<div id="17-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.carTemporary}',"${resultMap['17-2'].moneyFact}",'17-2');
					</script>
					</td>
				</tr>
				<tr myid="1.3" ifHasSub="0" style="display:none;"><!-- <span class="triangledown"/> -->
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;1.3&nbsp;多种经营收入<span class="triangle"/></td>
					<td>${curMonthBisBudget.multiTotal}</td>
					<td><s:if test="resultMap['A_1_3-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_3-1'].moneyFact}</s:else></td>
					<td>
					<div id="A_1_3-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.multiTotal}',"${resultMap['A_1_3-1'].moneyFact}",'A_1_3-1');
					</script>
					</td>
					<td>${curYearBisBudget.multiTotal}</td>
					<td><s:if test="resultMap['A_1_3-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_3-2'].moneyFact}</s:else></td>
					<td>
					<div id="A_1_3-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.multiTotal}',"${resultMap['A_1_3-2'].moneyFact}",'A_1_3-2');
					</script>
					</td>
				</tr>
				<tr myid="1.3.1" ifHasSub="0" style="display:none;">
					<td class="indent_3" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;1.3.1&nbsp;广告场地使用收入</td>
					<td>${curMonthBisBudget.multiAdvert}</td>
					<td><s:if test="resultMap['16-1'].moneyFact==null">0</s:if><s:else>${resultMap['16-1'].moneyFact}</s:else></td>
					<td>
					<div id="16-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.multiAdvert}',"${resultMap['16-1'].moneyFact}",'16-1');
					</script>
					</td>
					<td>${curYearBisBudget.multiAdvert}</td>
					<td><s:if test="resultMap['16-2'].moneyFact==null">0</s:if><s:else>${resultMap['16-2'].moneyFact}</s:else></td>
					<td>
					<div id="16-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.multiAdvert}',"${resultMap['16-2'].moneyFact}",'16-2');
					</script>
					</td>
				</tr>
				<tr myid="1.3.2" ifHasSub="0" style="display:none;">
					<td class="indent_3" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;1.3.2&nbsp;服务费收入</td>
					<td>${curMonthBisBudget.multiService}</td>
					<td><s:if test="resultMap['20-1'].moneyFact==null">0</s:if><s:else>${resultMap['20-1'].moneyFact}</s:else></td>
					<td>
					<div id="20-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.multiService}',"${resultMap['20-1'].moneyFact}",'20-1');
					</script>
					</td>
					<td>${curYearBisBudget.multiService}</td>
					<td><s:if test="resultMap['20-2'].moneyFact==null">0</s:if><s:else>${resultMap['20-2'].moneyFact}</s:else></td>
					<td>
					<div id="20-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.multiService}',"${resultMap['20-2'].moneyFact}",'20-2');
					</script>
					</td>
				</tr>
				<tr myid="1.3.3" ifHasSub="0" style="display:none;">
					<td class="indent_3" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;1.3.3&nbsp;维修服务收入</td>
					<td>${curMonthBisBudget.multiRepair}</td>
					<td><s:if test="resultMap['21-1'].moneyFact==null">0</s:if><s:else>${resultMap['21-1'].moneyFact}</s:else></td>
					<td>
					<div id="21-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.multiRepair}',"${resultMap['21-1'].moneyFact}",'21-1');
					</script>
					</td>
					<td>${curYearBisBudget.multiRepair}</td>
					<td><s:if test="resultMap['21-2'].moneyFact==null">0</s:if><s:else>${resultMap['21-2'].moneyFact}</s:else></td>
					<td>
						<div id="21-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.multiRepair}',"${resultMap['21-2'].moneyFact}",'21-2');
					</script>
					</td>
				</tr>
				<tr myid="1.3.4" ifHasSub="0" style="display:none;">
					<td class="indent_3" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;1.3.4&nbsp;清洁服务收入</td>
					<td>${curMonthBisBudget.multiClean}</td>
					<td><s:if test="resultMap['22-1'].moneyFact==null">0</s:if><s:else>${resultMap['22-1'].moneyFact}</s:else></td>
					<td>
					<div id="22-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.multiClean}',"${resultMap['22-1'].moneyFact}",'22-1');
					</script>
					</td>
					<td>${curYearBisBudget.multiClean}</td>
					<td><s:if test="resultMap['22-2'].moneyFact==null">0</s:if><s:else>${resultMap['22-2'].moneyFact}</s:else></td>
					<td>
					<div id="22-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.multiClean}',"${resultMap['22-2'].moneyFact}",'22-2');
					</script>
					</td>
				</tr>
				<tr myid="1.3.5" ifHasSub="0" style="display:none;">
					<td class="indent_3" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;1.3.5&nbsp;其他收入</td>
					<td>${curMonthBisBudget.multiOther}</td>
					<td><s:if test="resultMap['A_1_3_5-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_3_5-1'].moneyFact}</s:else></td>
					<td>
					<div id="A_1_3_5-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.multiOther}',"${resultMap['A_1_3_5-1'].moneyFact}",'A_1_3_5-1');
					</script>
					</td>
					<td>${curYearBisBudget.multiOther}</td>
					<td><s:if test="resultMap['A_1_3_5-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_3_5-2'].moneyFact}</s:else></td>
					<td>
					<div id="A_1_3_5-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.multiOther}',"${resultMap['A_1_3_5-2'].moneyFact}",'A_1_3_5-2');
					</script>
					</td>
				</tr>
				<tr myid="1.4" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;1.4&nbsp;招商佣金收入<span class="triangle"/></td>
					<td>${curMonthBisBudget.inviteMerchant}</td>
					<td><s:if test="resultMap['A_1_4-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_4-1'].moneyFact}</s:else></td>
					<td>
					<div id="A_1_4-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.inviteMerchant}',"${resultMap['A_1_4-1'].moneyFact}",'A_1_4-1');
					</script>
					</td>
					<td>${curYearBisBudget.inviteMerchant}</td>
					<td><s:if test="resultMap['A_1_4-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_4-2'].moneyFact}</s:else></td>
					<td>
					<div id="A_1_4-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.inviteMerchant}',"${resultMap['A_1_4-2'].moneyFact}",'A_1_4-2');
					</script>
					</td>
				</tr>
				<tr myid="1.4.1" ifHasSub="0" style="display:none;">
					<td class="indent_3" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;1.4.1&nbsp;招商代理费</td>
					<td>${curMonthBisBudget.inviteAgent}</td>
					<td><s:if test="resultMap['2-1'].moneyFact==null">0</s:if><s:else>${resultMap['2-1'].moneyFact}</s:else></td>
					<td>
					<div id="2-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.inviteAgent}',"${resultMap['2-1'].moneyFact}",'2-1');
					</script>
					</td>
					<td>${curYearBisBudget.inviteAgent}</td>
					<td><s:if test="resultMap['2-2'].moneyFact==null">0</s:if><s:else>${resultMap['2-2'].moneyFact}</s:else></td>
					<td>
					<div id="2-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.inviteAgent}',"${resultMap['2-2'].moneyFact}",'2-2');
					</script>
					</td>
				</tr>
				<tr myid="1.4.2" ifHasSub="0" style="display:none;">
					<td class="indent_3" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;1.4.2&nbsp;租金管理费</td>
					<td>${curMonthBisBudget.inviteRent}</td>
					<td><s:if test="resultMap['18-1'].moneyFact==null">0</s:if><s:else>${resultMap['18-1'].moneyFact}</s:else></td>
					<td>
					<div id="18-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.inviteRent}',"${resultMap['18-1'].moneyFact}",'18-1');
					</script>
					</td>
					<td>${curYearBisBudget.inviteRent}</td>
					<td><s:if test="resultMap['18-2'].moneyFact==null">0</s:if><s:else>${resultMap['18-2'].moneyFact}</s:else></td>
					<td>
					
					<div id="18-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.inviteRent}',"${resultMap['18-2'].moneyFact}",'18-2');
					</script>
					</td>
				</tr>
				<tr myid="1.5" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;1.5&nbsp;委托租赁收入（返租收入）</td>
					<td>${curMonthBisBudget.agencyRent}</td>
					<td><s:if test="resultMap['B29-1'].moneyFact==null">0</s:if><s:else>${resultMap['B29-1'].moneyFact}</s:else></td>
					<td>
						<div id="B29-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.agencyRent}',"${resultMap['B29-1'].moneyFact}",'B29-1');
					</script>
					</td>
					<td>${curYearBisBudget.agencyRent}</td>
					<td><s:if test="resultMap['B29-2'].moneyFact==null">0</s:if><s:else>${resultMap['B29-2'].moneyFact}</s:else></td>
					<td>
					<div id="B29-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.agencyRent}',"${resultMap['B29-2'].moneyFact}",'B29-2');
					</script>
					</td>
				</tr>
				<tr myid="1.6" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;1.6&nbsp;代收款项资金流入</td>
					<td>${curMonthBisBudget.agencyFund}</td>
					<td><s:if test="resultMap['A_1_6-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_6-1'].moneyFact}</s:else></td>
					<td>
					<div id="A_1_6-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.agencyFund}',"${resultMap['A_1_6-1'].moneyFact}","A_1_6-1");
					</script>
					</td>
					<td>${curYearBisBudget.agencyFund}</td>
					<td><s:if test="resultMap['A_1_6-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_1_6-2'].moneyFact}</s:else></td>
					<td>
					<div id="A_1_6-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.agencyFund}',"${resultMap['A_1_6-2'].moneyFact}",'A_1_6-2');
					</script>
					</td>
				</tr>
				<tr myid="2" ifHasSub="1">
					<td class="indent_1" style="text-align:left;">2&nbsp;成本支出合计<span class="triangle"/></td>
					<td>${curMonthBisBudget.payTotal}</td>
					<td><s:if test="resultMap['A_2-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_2-1'].moneyFact}</s:else></td>
					<td>
					<div id="A_2-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.payTotal}',"${resultMap['A_2-1'].moneyFact}",'A_2-1');
					</script>
					</td>
					<td>${curYearBisBudget.payTotal}</td>
					<td><s:if test="resultMap['A_2-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_2-2'].moneyFact}</s:else></td>
					<td>
					<div id="A_2-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.payTotal}',"${resultMap['A_2-2'].moneyFact}",'A_2-2');
					</script>
					</td>
				</tr>
				<tr myid="2.1" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.1&nbsp;人工福利费</td>
					<td>${curMonthBisBudget.welfare}</td>
					<td><s:if test="resultMap['A_2_1-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_1-1'].moneyFact}</s:else></td>
					<td>
					<div id="A_2_1-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.welfare}',"${resultMap['A_2_1-1'].moneyFact}",'A_2_1-1');
					</script>
					</td>
					<td>${curYearBisBudget.welfare}</td>
					<td><s:if test="resultMap['A_2_1-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_1-2'].moneyFact}</s:else></td>
					<td>
					<div id="A_2_1-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.welfare}',"${resultMap['A_2_1-2'].moneyFact}",'A_2_1-2');
					</script>
					</td>
				</tr>
				<tr myid="2.2" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.2&nbsp;其他人事费用</td>
					<td>${curMonthBisBudget.otherPersonnel}</td>
					<td><s:if test="resultMap['B42-1'].moneyFact==null">0</s:if><s:else>${resultMap['B42-1'].moneyFact}</s:else></td>
					<td>
					<div id="B42-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.otherPersonnel}',"${resultMap['B42-1'].moneyFact}",'B42-1');
					</script>
					</td>
					<td>${curYearBisBudget.otherPersonnel}</td>
					<td><s:if test="resultMap['B42-2'].moneyFact==null">0</s:if><s:else>${resultMap['B42-2'].moneyFact}</s:else></td>
					<td>
					<div id="B42-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.otherPersonnel}',"${resultMap['B42-2'].moneyFact}",'B42-2');
					</script>
					</td>
				</tr>
				<tr myid="2.3" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.3&nbsp;行政类费用</td>
					<td>${curMonthBisBudget.administraFee}</td>
					<td><s:if test="resultMap['B36-1'].moneyFact==null">0</s:if><s:else>${resultMap['B36-1'].moneyFact}</s:else></td>
					<td>
				    <div id="B36-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.administraFee}',"${resultMap['B36-1'].moneyFact}",'B36-1');
					</script>
					</td>
					<td>${curYearBisBudget.administraFee}</td>
					<td><s:if test="resultMap['B36-2'].moneyFact==null">0</s:if><s:else>${resultMap['B36-2'].moneyFact}</s:else></td>
					<td>
					<div id="B36-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.administraFee}',"${resultMap['B36-2'].moneyFact}",'B36-2');
					</script>
					</td>
				</tr>
				<tr myid="2.4" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.4&nbsp;差旅费</td>
					<td>${curMonthBisBudget.travelFee}</td>
					<td><s:if test="resultMap['B35-1'].moneyFact==null">0</s:if><s:else>${resultMap['B35-1'].moneyFact}</s:else></td>
					<td>
					 <div id="B35-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.travelFee}',"${resultMap['B35-1'].moneyFact}",'B35-1');
					</script>
					</td>
					<td>${curYearBisBudget.travelFee}</td>
					<td><s:if test="resultMap['B35-2'].moneyFact==null">0</s:if><s:else>${resultMap['B35-2'].moneyFact}</s:else></td>
					<td>
					 <div id="B35-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.travelFee}',"${resultMap['B35-2'].moneyFact}",'B35-2');
					</script>
					</td>
				</tr>
				<tr myid="2.5" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.5&nbsp;业务招待费</td>
					<td>${curMonthBisBudget.businessServe}</td>
					<td><s:if test="resultMap['B37-1'].moneyFact==null">0</s:if><s:else>${resultMap['B37-1'].moneyFact}</s:else></td>
					<td>
					 <div id="B37-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.businessServe}',"${resultMap['B37-1'].moneyFact}",'B37-1');
					</script>
					</td>
					<td>${curYearBisBudget.businessServe}</td>
					<td><s:if test="resultMap['B37-2'].moneyFact==null">0</s:if><s:else>${resultMap['B37-2'].moneyFact}</s:else></td>
					<td>
					 <div id="B37-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.businessServe}',"${resultMap['B37-2'].moneyFact}",'B37-2');
					</script>
					</td>
				</tr>
				<tr myid="2.6" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.6&nbsp;广告宣传费</td>
					<td>${curMonthBisBudget.adivertise}</td>
					<td><s:if test="resultMap['B21-1'].moneyFact==null">0</s:if><s:else>${resultMap['B21-1'].moneyFact}</s:else></td>
					<td>
					 <div id="B21-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.adivertise}',"${resultMap['B21-12'].moneyFact}",'B21-1');
					</script>
					</td>
					<td>${curYearBisBudget.adivertise}</td>
					<td><s:if test="resultMap['B21-2'].moneyFact==null">0</s:if><s:else>${resultMap['B21-2'].moneyFact}</s:else></td>
					<td>
					 <div id="B21-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.adivertise}',"${resultMap['B21-2'].moneyFact}",'B21-2');
					</script>
					</td>
				</tr>
				<tr myid="2.7" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.7&nbsp;招商奖金</td>
					<td>${curMonthBisBudget.inviteReward}</td>
					<td><s:if test="resultMap['B22-1'].moneyFact==null">0</s:if><s:else>${resultMap['B22-1'].moneyFact}</s:else></td>
					<td>
					 <div id="B22-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.inviteReward}',"${resultMap['B22-1'].moneyFact}",'B22-1');
					</script>
					</td>
					<td>${curYearBisBudget.inviteReward}</td>
					<td><s:if test="resultMap['B22-2'].moneyFact==null">0</s:if><s:else>${resultMap['B22-2'].moneyFact}</s:else></td>
					<td>
					 <div id="B22-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.inviteReward}',"${resultMap['B22-2'].moneyFact}",'B22-2');
					</script>
					</td>
				</tr>
				<tr myid="2.8" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.8&nbsp;清洁卫生费</td>
					<td>${curMonthBisBudget.cleanHealthCost}</td>
					<td><s:if test="resultMap['B1-1'].moneyFact==null">0</s:if><s:else>${resultMap['B1-1'].moneyFact}</s:else></td>
					<td>
					 <div id="B1-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.cleanHealthCost}',"${resultMap['B1-1'].moneyFact}",'B1-1');
					</script>
					</td>
					<td>${curYearBisBudget.cleanHealthCost}</td>
					<td><s:if test="resultMap['B1-2'].moneyFact==null">0</s:if><s:else>${resultMap['B1-2'].moneyFact}</s:else></td>
					<td>
					 <div id="B1-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.cleanHealthCost}',"${resultMap['B1-2'].moneyFact}",'B1-2');
					</script>
					</td>
				</tr>
				<tr myid="2.9" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.9&nbsp;安全保卫费</td>
					<td>${curMonthBisBudget.safeGuard}</td>
					<td><s:if test="resultMap['B2-1'].moneyFact==null">0</s:if><s:else>${resultMap['B2-1'].moneyFact}</s:else></td>
					<td>
					 <div id="B2-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.safeGuard}',"${resultMap['B2-1'].moneyFact}",'B2-1');
					</script>
					</td>
					<td>${curYearBisBudget.safeGuard}</td>
					<td><s:if test="resultMap['B2-2'].moneyFact==null">0</s:if><s:else>${resultMap['B2-2'].moneyFact}</s:else></td>
					<td>
					
					 <div id="B2-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.safeGuard}',"${resultMap['B2-2'].moneyFact}",'B2-2');
					</script>
					</td>
				</tr>
				<tr myid="2.10" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.10&nbsp;绿化养护费</td>
					<td>${curMonthBisBudget.afforeMainten}</td>
					<td><s:if test="resultMap['B3-1'].moneyFact==null">0</s:if><s:else>${resultMap['B3-1'].moneyFact}</s:else></td>
					<td>
					 <div id="B3-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.afforeMainten}',"${resultMap['B3-1'].moneyFact}",'B3-1');
					</script>
					</td>
					<td>${curYearBisBudget.afforeMainten}</td>
					<td><s:if test="resultMap['B3-2'].moneyFact==null">0</s:if><s:else>${resultMap['B3-2'].moneyFact}</s:else></td>
					<td>
					 <div id="B3-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.afforeMainten}',"${resultMap['B3-2'].moneyFact}",'B3-2');
					</script>
					</td>
				</tr>
				<tr myid="2.10" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.11&nbsp;工程维保费</td>
					<td>${curMonthBisBudget.engineerMainten}</td>
					<td><s:if test="resultMap['B4-1'].moneyFact==null">0</s:if><s:else>${resultMap['B4-1'].moneyFact}</s:else></td>
					<td>
					 <div id="B4-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.engineerMainten}',"${resultMap['B4-1'].moneyFact}",'B4-1');
					</script>
					</td>
					<td>${curYearBisBudget.engineerMainten}</td>
					<td><s:if test="resultMap['B4-2'].moneyFact==null">0</s:if><s:else>${resultMap['B4-2'].moneyFact}</s:else></td>
					<td>
					 <div id="B4-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.engineerMainten}',"${resultMap['B4-2'].moneyFact}",'B4-2');
					</script>
					</td>
				</tr>
				<tr myid="2.11" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.12&nbsp;能源费(无法收回或摊销)</td>
					<td>${curMonthBisBudget.energyCost}</td>
					<td><s:if test="resultMap['B32-1'].moneyFact==null">0</s:if><s:else>${resultMap['B32-1'].moneyFact}</s:else></td>
					<td>					
					<div id="B32-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.energyCost}',"${resultMap['B32-1'].moneyFact}",'B32-1');
					</script>
					</td>
					<td>${curYearBisBudget.energyCost}</td>
					<td><s:if test="resultMap['B32-2'].moneyFact==null">0</s:if><s:else>${resultMap['B32-2'].moneyFact}</s:else></td>
					<td>
					<div id="B32-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.energyCost}',"${resultMap['B32-2'].moneyFact}",'B32-2');
					</script>
					
					</td>
				</tr>
				<tr myid="2.12" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.13&nbsp;其他费用</td>
					<td>${curMonthBisBudget.otherPay}</td>
					<td><s:if test="resultMap['A_2_13-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_13-1'].moneyFact}</s:else></td>
					<td>
						<div id="A_2_13-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.otherPay}',"${resultMap['A_2_13-1'].moneyFact}",'A_2_13-1');
					</script>
					</td>
					<td>${curYearBisBudget.otherPay}</td>
					<td><s:if test="resultMap['A_2_13-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_13-2'].moneyFact}</s:else></td>
					<td>
						<div id="A_2_13-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.otherPay}',"${resultMap['A_2_13-2'].moneyFact}",'A_2_13-2');
					</script>
					</td>
				</tr>
				<tr myid="2.14" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.14&nbsp;资本性支出</td>
					<td>${curMonthBisBudget.capitalExpend}</td>
					<td><s:if test="resultMap['B40-1'].moneyFact==null">0</s:if><s:else>${resultMap['B40-1'].moneyFact}</s:else></td>
					<td>
						<div id="B40-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.capitalExpend}',"${resultMap['B40-1'].moneyFact}",'B40-1');
					</script>
					</td>
					<td>${curYearBisBudget.capitalExpend}</td>
					<td><s:if test="resultMap['B40-2'].moneyFact==null">0</s:if><s:else>${resultMap['B40-2'].moneyFact}</s:else></td>
					<td>
					<div id="B40-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.capitalExpend}',"${resultMap['B40-2'].moneyFact}",'B40-2');
					</script>
					</td>
				</tr>
				<tr myid="2.15" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.15&nbsp;营业税金及附加</td>
					<td>${curMonthBisBudget.salesExtra}</td>
					<td><s:if test="resultMap['A_2_18-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_18-1'].moneyFact}</s:else></td>
					<td>
					<div id="A_2_18-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.salesExtra}',"${resultMap['A_2_18-1'].moneyFact}",'A_2_18-1');
					</script>
					</td>
					<td>${curYearBisBudget.salesExtra}</td>
					<td><s:if test="resultMap['A_2_18-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_18-2'].moneyFact}</s:else></td>
					<td>
					<div id="A_2_18-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.salesExtra}',"${resultMap['A_2_18-2'].moneyFact}",'A_2_18-2');
					</script>
					</td>
				</tr>
				<tr myid="2.16" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.16&nbsp;委托租赁支出（返租支出）</td>
					<td>${curMonthBisBudget.proxyRent}</td>
					<td><s:if test="resultMap['B34-1'].moneyFact==null">0</s:if><s:else>${resultMap['B34-1'].moneyFact}</s:else></td>
					<td>
					<div id="B34-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.proxyRent}',"${resultMap['B34-1'].moneyFact}",'B34-1');
					</script>
					</td>
					<td>${curYearBisBudget.proxyRent}</td>
					<td><s:if test="resultMap['B34-2'].moneyFact==null">0</s:if><s:else>${resultMap['B34-2'].moneyFact}</s:else></td>
					<td>
					<div id="B34-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.proxyRent}',"${resultMap['B34-2'].moneyFact}",'B34-2');
					</script>
					</td>
				</tr>
				<tr myid="2.17" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.17&nbsp;立项费用（大营运专用）</td>
					<td>${curMonthBisBudget.setupProject}</td>
					<td><s:if test="resultMap['A_2_17-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_17-1'].moneyFact}</s:else></td>
					<td>
					<div id="A_2_17-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.setupProject}',"${resultMap['A_2_17-1'].moneyFact}",'A_2_17-1');
					</script>
					</td>
					<td>${curYearBisBudget.setupProject}</td>
					<td><s:if test="resultMap['A_2_17-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_2_17-2'].moneyFact}</s:else></td>
					<td>
					<div id="A_2_17-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.setupProject}',"${resultMap['A_2_17-2'].moneyFact}",'A_2_17-2');
					</script>
					</td>
				</tr>
				<tr myid="2.18" ifHasSub="0" style="display:none;">
					<td class="indent_2" style="text-align:left;">&nbsp;&nbsp;2.18&nbsp;代付款项资金支出</td>
					<td>${curMonthBisBudget.prePayFee}</td>
					<td><s:if test="resultMap['B38-1'].moneyFact==null">0</s:if><s:else>${resultMap['B38-1'].moneyFact}</s:else></td>
					<td>
					<div id="B38-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.prePayFee}',"${resultMap['B38-1'].moneyFact}",'B38-1');
					</script>
					</td>
					<td>${curYearBisBudget.prePayFee}</td>
					<td><s:if test="resultMap['B38-2'].moneyFact==null">0</s:if><s:else>${resultMap['B38-2'].moneyFact}</s:else></td>
					<td>
					<div id="B38-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.prePayFee}',"${resultMap['B38-2'].moneyFact}",'B38-2');
					</script>
					</td>
				</tr>
				<tr myid="3" ifHasSub="0">
					<td class="indent_1" style="text-align:left;">3&nbsp;净利润</td>
					<td>${curMonthBisBudget.netProfit}</td>
					<td><s:if test="resultMap['A_3-1'].moneyFact==null">0</s:if><s:else>${resultMap['A_3-1'].moneyFact}</s:else></td>
					<td>
					<%-- 
					<div id="A_3-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.netProfit}',"${resultMap['A_3-1'].moneyFact}",'A_3-1');
					</script>
					 --%>
					</td>
					<td>${curYearBisBudget.netProfit}</td>
					<td><s:if test="resultMap['A_3-2'].moneyFact==null">0</s:if><s:else>${resultMap['A_3-2'].moneyFact}</s:else></td>
					<td>
					<%--
					<div id="A_3-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.netProfit}',"${resultMap['A_3-2'].moneyFact}",'A_3-2');
					</script>--%>
					</td>
				</tr>
				<tr myid="4" ifHasSub="0">
					<td class="indent_1" style="text-align:left;">4&nbsp;租金</td>
					<td>
				   ${curMonthBisBudget.rentTotal}
					<!--<s:if test="resultMap['1-1'].moneyMust==null">0</s:if><s:else>${resultMap['1-1'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['1-1'].moneyFact==null">0</s:if><s:else>${resultMap['1-1'].moneyFact}</s:else></td>
					<td>						
					<div id="1-1" optype="auto"></div>
				    <script>
				     computePercent('${curMonthBisBudget.rentTotal}',"${resultMap['1-1'].moneyFact}",'1-1');
					</script>					
					<!--<s:if test="resultMap['1-1'].moneyRate==null">0</s:if><s:else>${resultMap['1-1'].moneyRate}</s:else>%-->
									
					</td>
					<td>
					
					${curYearBisBudget.rentTotal}
					<!--<s:if test="resultMap['1-2'].moneyMust==null">0</s:if><s:else>${resultMap['1-2'].moneyMust}</s:else>--></td>
					<td><s:if test="resultMap['1-2'].moneyFact==null">0</s:if><s:else>${resultMap['1-2'].moneyFact}</s:else></td>
					<td>											
					<div id="1-2" optype="auto"></div>
				    <script>
				     computePercent('${curYearBisBudget.rentTotal}',"${resultMap['1-2'].moneyFact}",'1-2');
					</script>
					
					<!--<s:if test="resultMap['1-2'].moneyRate==null">0</s:if><s:else>${resultMap['1-2'].moneyRate}</s:else>%--></td>
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
					if(otid.substring(0,tempMyId.length)==tempMyId){
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

</script>