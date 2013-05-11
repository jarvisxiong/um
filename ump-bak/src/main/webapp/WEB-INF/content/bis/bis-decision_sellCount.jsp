<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<%--合计行--%>
<tr style="height: 45px;border-width: 2px;border-top-style: solid;" class="edit_tr" id="list_tr_count" onmouseover="mouseOver('_count');" onmouseout="mouseOut('_count');">
	<td style="background-color: #D9D9D9;text-align: right;font-weight:bolder; padding-right: 5px;border-width: 2px;border-style:solid;" colspan="2">
		以上合计
	</td>
	<td class="pd-chill-tip back_gray2 click_td" 
		title="合计_本月_签约<br/>${voSellCount.signMoneySjMonthCount }万/${voSellCount.signMoneyJhMonthCount }万(${voSellCount.monthQyPercentCount})">
		${voSellCount.signMoneySjMonthCount }
		<input type="hidden" id="month_qy" value="${voSellCount.signMoneySjMonthCount }"/>
	</td>
	<td class="pd-chill-tip click_td"
		title="合计_本月_回款<br/>${voSellCount.returnMoneySjMonthCount }万/${voSellCount.returnMoneyJhMonthCount }万(${voSellCount.monthHkPercentCount})">
		${voSellCount.returnMoneySjMonthCount }
	</td>
	<td class="pd-chill-tip back_gray2 click_td" 
		title="合计_年度_签约<br/>${voSellCount.signMoneySjYearCount }万/计划${voSellCount.signMoneyJhYearCount }万(${voSellCount.yearQyPercentCount})">
		${voSellCount.signMoneySjYearCount }
		<input type="hidden" id="year_qy" value="${voSellCount.signMoneySjYearCount }"/>
	</td>
	<td class="pd-chill-tip click_td"
		title="合计_年度_回款<br/>${voSellCount.returnMoneySjYearCount }万/计划${voSellCount.returnMoneyJhYearCount }万(${voSellCount.yearHkPercentCount})">
		${voSellCount.returnMoneySjYearCount }
	</td>
	<td class="pd-chill-tip click_td"
		title="合计_存货_可售<br/>${voSellCount.dsMoneyCount }万/${voSellCount.dsSuiteNumCount }套/${voSellCount.dsAreaCount }平米">
		${voSellCount.dsMoneyCount }
	</td>
	<td class="pd-chill-tip click_td"
		title="合计_存货_销控<br/>${voSellCount.xkMoneyCount }万/${voSellCount.xkSuiteNumCount }套/${voSellCount.xkAreaCount }平米">
		${voSellCount.xkMoneyCount }
	</td>
	<td class="pd-chill-tip click_td"
		title="合计_认购未签约<br/>${voSellCount.notSignMoneyCount }万/${voSellCount.notSignSuiteNumCount }套">
		${voSellCount.notSignMoneyCount }
	</td>
	<td class="pd-chill-tip click_td"
		title="合计_欠款_按揭<br/>${voSellCount.mortgageArrearsCount }万">
		${voSellCount.mortgageArrearsCount }
	</td>
	<td class="pd-chill-tip click_td" title="合计_欠款_其他<br/>${voSellCount.otherArrearsCount }万">
		${voSellCount.otherArrearsCount }
	</td>
</tr>
