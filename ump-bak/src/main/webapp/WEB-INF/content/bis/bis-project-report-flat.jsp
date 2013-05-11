<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<table>
<tr>
	<td width="250px;" valign="top">
		<div id="leftTopDiv" style="width:250px;">
		<table style="width:100%;" class="report_table">
			<col width="80" />
			<col />
			<col />
			<col />
			<tr class="top_thead" >
				<th class="report_table_dbRow" rowspan="2">公寓编号</th>
				<th class="report_table_dbRow" rowspan="2">面积</th>
				<th class="report_table_dbRow" rowspan="2">套内面积</th>
				<th class="report_table_dbRow" rowspan="2">公摊面积</th>
			</tr>
			<tr></tr>
		</table>
		</div>

		<div id="leftDiv" style="width:250px;overflow:hidden;height:300px;">
		<table id="leftTable" style="width:100%;" class="report_table">
			<col width="80" />
			<col />
			<col />
			<col />
			<s:iterator value="bisFlatReportVos">
			<tr>
				<td class="pd-chill-tip" title="${flatNo}" style="border-top: 0;">
				<div class="ellipsisDiv_full" >
				${flatNo}
				</div>
				</td>
				<td style="border-top: 0;">${square}</td>
				<td style="border-top: 0;">${innerSquare}</td>
				<td style="border-top: 0;">${PublicSquare}</td>
			</tr>
			</s:iterator>
		</table>
		</div>
	</td>

	<td valign="top">
		<div id="rightTopDiv" style="overflow: hidden;width:920px;">
		<table id="rightTopTable" class="report_table">
			<s:iterator value="chargeTypeArr" status="s">
			<col width="110" />
			<col width="110" />
			<col width="80" />
			<col width="80" />
			<col width="80" />
			</s:iterator>
			<tr class="top_thead">
				<s:iterator value="chargeTypeArr" status="s">
				<th colspan="5" style="border-left: 0;">
                    <p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapChargeTypeFrpt()" value="chargeTypeArr[#s.index]"/>(元)
                </th>
				</s:iterator>
			</tr>
			<tr class="top_thead">
				<s:iterator value="chargeTypeArr" status="s">
				<th style="border-left: 0;">本年累计比较数<br>(实收/应收)</th>
				<th>本月比较数<br>(实收/应收)</th>
				<th>本年累计欠费</th>
				<th>本月欠费</th>
				<th>以前年度欠费</th>
				</s:iterator>
			</tr>
		</table>
		</div>

		<div id="rightDiv" style="overflow: auto;width:920px;height:300px;" onscroll="resetLayout($(this));">
		<table id="rightTable" class="report_table">
			<s:iterator value="chargeTypeArr" status="s">
			<col width="110" />
			<col width="110" />
			<col width="80" />
			<col width="80" />
			<col width="80" />
			</s:iterator>
			<s:iterator value="bisFlatReportVos">
			<tr>
				<s:iterator value="chargeTypeArr" status="s">
					<s:set var="hasVal">false</s:set>
					<s:iterator value="detailVoList">
						<s:if test="chargeTypeCdRel == chargeTypeArr[#s.index]">
						<s:set var="hasVal">true</s:set>
						<td class="pd-chill-tip" title="${yearMoneyRel}" style="border-left: 0; border-top: 0;">
						<div class="ellipsisDiv_full" >
						${yearMoneyRel}
						</div>
						</td>
						<td class="pd-chill-tip" title="${monthMoneyRel}" style="border-top: 0;">
						<div class="ellipsisDiv_full" >
						${monthMoneyRel}
						</div>
						</td>
						<td style="border-top: 0;" align="right">${yearMoneySub}</td>
						<td style="border-top: 0;" align="right">${monthMoneySub}</td>
						<td style="border-top: 0;" align="right">${befYearMoney}</td>
						</s:if>
					</s:iterator>
					<s:if test="#hasVal == 'false'">
						<td style="border-left: 0; border-top: 0;"></td>
						<td style="border-top: 0;"></td>
						<td style="border-top: 0;"></td>
						<td style="border-top: 0;"></td>
						<td style="border-top: 0;"></td>
					</s:if>
				</s:iterator>
			</tr>
			</s:iterator>
		</table>
		</div>
	</td>
</tr>
</table>

<script type="text/javascript">
//滚动时,自动保持左下/右上/右下布局对齐.
function resetLayout(obj) {
	var t = parseInt(obj.scrollTop());
	var l = parseInt(obj.scrollLeft());
	$("#leftTable").css("margin-top", -t + "px");
	$("#rightTopTable").css("margin-left", -l + "px");
}
</script>