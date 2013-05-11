<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<table>
<tr>
	<td width="250px;" valign="top">
		<div id="leftTopDiv" style="width:250px;">
		<table style="width:100%;" class="report_table">
			<col width="90" />
			<col />
			<col width="70" />
			<tr class="top_thead" >
				<th colspan="2">租户</th>
				<th rowspan="2">产权面积<br>(建筑)</th>
			</tr>
			<tr>
				<th>商家</th>
				<th>商铺</th>
			</tr>
		</table>
		</div>
		
		<div id="leftDiv" style="width:250px;overflow:hidden;height:300px;"> 
		<table id="leftTable" class="report_table">
			<col width="90" />
			<col />
			<col width="70" />
			<s:iterator value="bisStoreReportVos">
			<tr <security:authorize ifAnyGranted="A_PLAN_QUERY">onclick="newTenant('${tentantId}');" style="cursor: pointer;"</security:authorize> >
				<td class="pd-chill-tip" title="${tentantName}" style="border-top: 0;">
				<div class="ellipsisDiv_full" >
				${tentantName}
				</div>
				</td>
				<td class="pd-chill-tip" title="${storeName}" style="border-top: 0;">
				<div class="ellipsisDiv_full" >
				${storeName}
				</div>
				</td>
				<td style="border-top: 0;">${shopArea}</td>
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
				<th colspan="5" style="border-left: 0;"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapChargeTypeSrpt()" value="chargeTypeArr[#s.index]"/>(元)</th>
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
			<s:iterator value="bisStoreReportVos">
			<tr <security:authorize ifAnyGranted="A_PLAN_QUERY">onclick="newTenant('${tentantId}');" style="cursor: pointer;"</security:authorize> >
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
function newTenant(tenantId){
	if(tenantId=='') {
		return;
	}
	var url="${ctx}/bis/bis-tenant!main.action?bisTenantId="+tenantId+"&bisProjectId="+$("#bisProjectId").val();
	if(parent.TabUtils==null)
		window.open(url);
	else
		parent.TabUtils.newTab("bisTenant","商业租户",url,true);
}
//滚动时,自动保持左下/右上/右下布局对齐.
function resetLayout(obj) {
	var t = parseInt(obj.scrollTop());
	var l = parseInt(obj.scrollLeft());
	$("#leftTable").css("margin-top", -t + "px");
	$("#rightTopTable").css("margin-left", -l + "px");
} 
</script>