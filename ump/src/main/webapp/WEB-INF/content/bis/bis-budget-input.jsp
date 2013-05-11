<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.DictMapUtil"%>

<%@page import="com.hhz.ump.util.JspUtil"%>
<style>
	.payTable input {
		width: 70px ;	
		margin-right:5px;
		
	}
</style>

<div style="margin-left: 5px">
<form action="${ctx}/bis/bis-budget!save.action" id="bisBudgetInputForm" method="post"><input type="hidden" name="id" id="id"
	value="${entity.bisBudgetId}" /> <input type="hidden" name="entity.monthStr" id="monthStr" value="${entity.monthStr}" /> <input type="hidden"
	name="entity.yearStatus" id="yearStatus" value="1" /> <input type="hidden" name="entity.bisBudgetId" id="bisBudgetId" value="${entity.bisBudgetId}" /> <input
	type="hidden" id="bisProjectIdInput" name="entity.bisProjectId" value="${entity.bisProjectId}" />

<table border="0" cellpadding="0" cellspacing="0" class="budgetTable">
	<tr>
		<td colspan="2">
		<div class="b-title1" id="tip">经营预算明细（单位：万元）</div>
		</td>
	</tr>
	<tr>

		<td style="text-align: left"><span class="budget-title3">收入合计:</span> <input class="autoinput" type="text" mm="tt" name="entity.incomeTotal"
			countField="entity.profit" value="${entity.incomeTotal}" readonly=""></input></td>
	</tr>
	<tr>
		<td colspan="2">
		<table class="main_content1" id="budgetTable" width="100%">
			<tr>
				<td style="border: 0px !important">
				<div style="height: 0.1px;">&nbsp;</div>
				</td>
				<td style="border: 0px !important">
				<div style="height: 1px;">&nbsp;</div>
				</td>
				<td style="border: 0px !important">
				<div style="height: 1px;">&nbsp;</div>
				</td>
			</tr>

			<tr>
				<td colspan="3" style="text-align: left;"><span class="budget-title">物业管理费收入:</span> 
				<input class="input1" type="text" maxlength="12" name="entity.propManage" countField="entity.incomeTotal" value="${entity.propManage}" /></td>

			</tr>
			<tr></tr>
			<tr>
				<td colspan="2" class="multiRowTitle2"> &nbsp;&nbsp;&nbsp;&nbsp;停车场收入<input type="hidden" mm="tt" name="entity.carParking" countField="entity.incomeTotal"
					value="${entity.carParking}"></input></td>
				<td align="right">
				<div class="div50">车位管理费收入:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.carManage" countField="entity.carParking"
					value="${entity.carManage}" /></div>
				<div class="div50">临时停车收入:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.carTemporary" countField="entity.carParking"
					value="${entity.carTemporary}" /></div>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="multiRowTitle2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;多种经营收入<input type="hidden" mm="tt" name="entity.multiTotal" countField="entity.incomeTotal"
					value="${entity.multiTotal}"></input>&nbsp;&nbsp;&nbsp;&nbsp;</td>


				<td align="right">
				<div style="width: 100%; float: left;">
				<div class="div50">广告场地使用收入:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.multiAdvert" countField="entity.multiTotal"
					value="${entity.multiAdvert}" /></div>
				<div class="div50">服务费收入:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.multiService" countField="entity.multiTotal"
					value="${entity.multiService}" /></div>
				</div>
				<div style="width: 100%; float: left;">
				<div class="div50">维修服务收入:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.multiRepair" countField="entity.multiTotal"
					value="${entity.multiRepair}" /></div>
				<div class="div50">清洁服务收入:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.multiClean" countField="entity.multiTotal"
					value="${entity.multiClean}" /></div>
				</div>

				<div class="div50">其他收入:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.multiOther" countField="entity.multiTotal"
					value="${entity.multiOther}" /></div>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="multiRowTitle2 ">&nbsp;&nbsp;&nbsp;&nbsp;招商佣金收入<input type="hidden" mm="tt" name="entity.inviteMerchant" countField="entity.incomeTotal"
					value="${entity.inviteMerchant}"></input></td>
				<td align="right">
				<div class="div50">招商代理费:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.inviteAgent" countField="entity.inviteMerchant"
					value="${entity.inviteAgent}" /></div>
				<div class="div50">租金管理费:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.inviteRent" countField="entity.inviteMerchant"
					value="${entity.inviteRent}" /></div>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="text-align: left"><span class=" budget-title">委托租赁收入(返租收入):</span> <input class="input1" type="text" maxlength="12"
					name="entity.agencyRent" countField="entity.incomeTotal" value="${entity.agencyRent}" /></td>
			</tr>
			<tr>

				<td colspan="3" style="text-align: left"><span class=" budget-title">代收款项资金流入:</span> <input class="input1" type="text" maxlength="12"
					name="entity.agencyFund" countField="entity.incomeTotal" value="${entity.agencyFund}" /></td>
			</tr>
		</table>

		</td>
	</tr>
</table>

<div style="divH">&nbsp;</div>

<table class="payTable" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="6" style="text-align: left"><span class="budget-title3">支出合计:</span> <input class="autoinput" type="text" readonly="" mm="tt"
			name="entity.payTotal" countField="entity.profit" value="${entity.payTotal}"></td>

	</tr>

	<tr>
		<td>
			<div style="float:left;margin:auto;width:32%;">工资福利费(营业成本):&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.salaryFareBiscost" countField="entity.payTotal" value="${entity.salaryFareBiscost }" /></div>
			<div style="float:left;margin:auto;width:34%;">人工福利费(管理费用):&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.welfare" countField="entity.payTotal" value="${entity.welfare }" /></div>
			<div style="float:left;margin:auto;width:34%;">工资福利费(销售费用):&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.salaryFareSales" countField="entity.payTotal" value="${entity.salaryFareSales }" /></div>
		</td>
	</tr>
	<tr>
		<td>
			<div style="float:left;margin:auto;width:32%;">行政类费用:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.administraFee" countField="entity.payTotal" value="${entity.administraFee}" /></div>
			<div style="float:left;margin:auto;width:34%;">业务招待费:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.businessServe" countField="entity.payTotal" value="${entity.businessServe}" /></div>
			<div style="float:left;margin:auto;width:34%;">广告宣传费:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.adivertise" countField="entity.payTotal" value="${entity.adivertise}" /></div>
		</td>
	</tr>
	<tr>
		<td>
			<div style="float:left;margin:auto;width:32%;">差旅费:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.travelFee" countField="entity.payTotal" value="${entity.travelFee}" /></div>
			<div style="float:left;margin:auto;width:34%;">清洁卫生费:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.cleanHealthCost" countField="entity.payTotal" value="${entity.cleanHealthCost}" /></div>
			<div style="float:left;margin:auto;width:34%;">安全保卫费:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.safeGuard" countField="entity.payTotal" value="${entity.safeGuard}" /></div>
		</td>
	</tr>
	<tr>
		<td>
			<div style="float:left;margin:auto;width:32%;">招商奖金:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.inviteReward" countField="entity.payTotal" value="${entity.inviteReward}" /></div>
			<div style="float:left;margin:auto;width:34%;">工程维保费:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.engineerMainten" countField="entity.payTotal" value="${entity.engineerMainten}" /></div>
			<div style="float:left;margin:auto;width:34%;">能源费(办公能源费):&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.energyCost" countField="entity.payTotal" value="${entity.energyCost}" /></div>
		</td>
	</tr>
	<tr>
		<td>
			<div style="float:left;margin:auto;width:32%;">绿化养护费:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.afforeMainten" countField="entity.payTotal" value="${entity.afforeMainten}" /></div>
			<div style="float:left;margin:auto;width:34%;">委托租赁支出(返租支出):&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.proxyRent" countField="entity.payTotal" value="${entity.proxyRent}" /></div>
			<div style="float:left;margin:auto;width:34%;">资本性支出:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.capitalExpend" countField="entity.payTotal" value="${entity.capitalExpend}" /></div>
		</td>
	</tr>
	<tr>
		<td>
			<div style="float:left;margin:auto;width:32%;">立项费用(大营运专用):&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.setupProject" countField="entity.payTotal" value="${entity.setupProject}" /></div>
			<div style="float:left;margin:auto;width:34%;">代付款项资金支出:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.prePayFee" countField="entity.payTotal" value="${entity.prePayFee}" /></div>
			<div style="float:left;margin:auto;width:34%;">停车场成本:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.parkCharge" countField="entity.payTotal" value="${entity.parkCharge }" /></div>
		</td>
	</tr>
	<tr>
		<td>
			<div style="float:left;margin:auto;width:32%;">其他营业成本:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.otherBisCost" countField="entity.payTotal" value="${entity.otherBisCost }" /></div>
			<div style="float:left;margin:auto;width:34%;">其他人事费用:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.otherPersonnel" countField="entity.payTotal" value="${entity.otherPersonnel}" /></div>
			<div style="float:left;margin:auto;width:34%;">其他管理费用:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.otherMgCharge" countField="entity.payTotal" value="${entity.otherMgCharge }" /></div>
		</td>
	</tr>
	<tr>
		<td>
			<div style="float:left;margin:auto;width:32%;">财务费用:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.finacialCharge" countField="entity.payTotal" value="${entity.finacialCharge }" /></div>
			<div style="float:left;margin:auto;width:34%;">&nbsp;&nbsp;其他费用:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.otherPay" countField="entity.payTotal" value="${entity.otherPay}" /></div>
			<div style="float:left;margin:auto;width:34%;">&nbsp;&nbsp;其他销售费用:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.otherSaleCharge" countField="entity.payTotal" value="${entity.otherSaleCharge}" /></div>
		</td>
	</tr>
	<tr>
		<td>
			<div style="float:left;margin:auto;width:32%;">销售费用:&nbsp;&nbsp;<input type="text" maxlength="12" name="entity.saleChargeTotal" countField="entity.payTotal" value="${entity.saleChargeTotal}" /></div>
			<div style="float:left;margin:auto;width:34%;">&nbsp;&nbsp;</div>
			<div style="float:left;margin:auto;width:34%;">&nbsp;&nbsp;</div>
		</td>
	</tr>
	
	<tr>
		<td style="text-align: right;">营业税金及附加: <input class="autoinput" type="text" maxlength="12" name="entity.salesExtra"
			style="background: #ffffff !important;" value="${entity.salesExtra}" /></td>
	</tr>
	<tr>
		<td style="text-align: right;">营业利润: <input class="autoinput" type="text"  maxlength="12" name="entity.bisProfit"
			style="background: #ffffff !important;" value="${entity.bisProfit }" /></td>
	</tr>
	<tr>
		<td style="text-align: right;">加：营业外收入: <input class="autoinput" type="text" maxlength="12" name="entity.bisIncome"
			style="background: #ffffff !important;" value="${entity.bisIncome }" /></td>
	</tr>
	<tr>
		<td style="text-align: right;">减：营业外支出: <input class="autoinput" type="text" maxlength="12" name="entity.bisExpense"
			style="background: #ffffff !important;" value="${entity.bisExpense }" /></td>
	</tr>
	<tr>
		<td style="text-align: right;">利润总额: <input class="autoinput" type="text" maxlength="12" name="entity.profitTotal"
			style="background: #ffffff !important;" value="${entity.profitTotal }" /></td>
	</tr>
	<tr>
		<td style="text-align: right;">所得税: <input class="autoinput" type="text" maxlength="12" name="entity.incomeTax"
			style="background: #ffffff !important;" value="${entity.incomeTax }" /></td>
	</tr>
	
	<tr>
		<td style="text-align: right;">净利润: <input class="autoinput" type="text" maxlength="12" name="entity.netProfit"
			style="background: #ffffff !important;" value="${entity.netProfit}" /></td>
	</tr>
	<tr>
		<td style="text-align: right;">租金: <input class="autoinput" type="text" maxlength="12" name="entity.rentTotal"
			style="background: #ffffff !important;" value="${entity.rentTotal}" /></td>
	</tr>
	
	<tr>
		<td style="text-align: right;" colspan="3">
		<div style="margin-left: 24px;"><input type="button" class="btn_green" style="with:650px !important" value="保存" onclick="isExistBisBudget();" />
		&nbsp;&nbsp;<input type="button" class="btn_red" style="with: 65px !important" value="关闭" onclick='resetAndHide()' /></div>
		</td>
		<td colspan="4"></td>
	</tr>
</table>
</form>
</div>