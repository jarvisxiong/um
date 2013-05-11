<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<table style="width: 100%" >
	<col width="160"/>
	<col />
	<tr>
		<td>
			<table class="innerTable" cellpadding="0" cellspacing="0">
				<col width="66"/>
				<col width="100"/>
				<tr class="headTr">
					<td colspan="2" >项目经营情况汇总表</td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: left;">收入(万元)</th>
				</tr>
				<tr>
					<td rowspan="3" style="text-align: left;">日常收入</td>
					<td style="text-align: left;">租金</td>
				</tr>
				<tr>
					<td style="text-align: left;">物业管理费</td>
				</tr>
				<tr>
					<td style="text-align: left;">能源费</td>
				</tr>
				<tr>
					<td rowspan="6" style="text-align: left;">经营</td>
					<td style="text-align: left;">佣金</td>
				</tr>
				<tr>
					<td style="text-align: left;">停车费</td>
				</tr>
				<tr>
					<td style="text-align: left;">广告费</td>
				</tr>
				<tr>
					<td style="text-align: left;">招商代理费</td>
				</tr>
				<tr>
					<td style="text-align: left;">多经收入及其他</td>
				</tr>
				<tr>
					<td style="text-align: left;">保证金</td>
				</tr>
				<tr>
					<td colspan="2" style="font-weight: bold; text-align: right; padding-right: 10px; color: #0167a2;">收入合计(万元)</td>
				</tr>
				<tr>
				<tr>
					<th colspan="2" style="text-align: left;">支出(万元)</th>
				</tr>
				<tr>
					<td colspan="2" style="text-align: left;">违约金</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: left;">成本费用支出</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: left;">广告宣传费</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: left;">人工福利费</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: left;">项目能源费</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: left;">日常办公支出</td>
				</tr>
				<tr>
					<td colspan="2" style="font-weight: bold; text-align: right; padding-right: 10px; color: #0167a2;">支出合计(万元)</td>
				</tr>
				<tr >
					<td colspan="2" class="bottomTitle" >总计(万元)</td>
				</tr>
			</table>
		</td>
		<td>
			<table class="innerTable" cellpadding="0" cellspacing="0" >
				<col width="50"/>
				<col width="50"/>
				<col width="50"/>
				<col width="50"/>
				<col width="50"/>
				<col width="50"/>
				<col width="50"/>
				<col width="50"/>
				<col width="50"/>
				<col width="50"/>
				<col width="50"/>
				<col width="50"/>
				<col width="50"/>
				<col width="50"/>
				<col width="50"/>
				<tr class="headTr">
					<td colspan="3" >本年度</td>
					<td colspan="3" >第${quarter}季度</td>
					<td colspan="3" id="monthOne">${monthOne}月</td>
					<td colspan="3" id="monthTwo">${monthTwo}月</td>
					<td colspan="3" id="monthThree">${monthThree}月</td>
				</tr>
				<tr>
					<th style="border-left:0px;">应收</th>
					<th>实收</th>
					<th title="收缴率">%</th>
					<th>应收</th>
					<th>实收</th>
					<th title="收缴率">%</th>
					<th>应收</th>
					<th>实收</th>
					<th title="收缴率">%</th>
					<th>应收</th>
					<th>实收</th>
					<th title="收缴率">%</th>
					<th>应收</th>
					<th>实收</th>
					<th title="收缴率">%</th>
				</tr>	
				<s:iterator value="inlist">
				<tr>
					<td style="border-left:0px;">
						<div class="ellipsisDiv_full" >
						${mustYear}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${factYear}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${rateYear}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${mustQuarter}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${factQuarter}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${rateQuarter}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${mustMonthOne}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${factMonthOne}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${rateMonthOne}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${mustMonthTwo}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${factMonthTwo}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${rateMonthTwo}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${mustMonthThree}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${factMonthThree}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${rateMonthThree}&nbsp;
						</div>
					</td>
				</tr>
				</s:iterator>
				
				<tr >
					<th style="border-left:0px;">预算</th>
					<th>实际</th>
					<th title="实际/预算">%</th>
					<th>预算</th>
					<th>实际</th>
					<th title="实际/预算">%</th>
					<th>预算</th>
					<th>实际</th>
					<th title="实际/预算">%</th>
					<th>预算</th>
					<th>实际</th>
					<th title="实际/预算">%</th>
					<th>预算</th>
					<th>实际</th>
					<th title="实际/预算">%</th>
				</tr>
				<s:iterator value="outlist">
				<tr >
					<td style="border-left:0px;">
						<div class="ellipsisDiv_full" >
						${mustYear}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${factYear}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${rateYear}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${mustQuarter}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${factQuarter}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${rateQuarter}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${mustMonthOne}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${factMonthOne}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${rateMonthOne}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${mustMonthTwo}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${factMonthTwo}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${rateMonthTwo}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${mustMonthThree}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${factMonthThree}&nbsp;
						</div>
					</td>
					<td>
						<div class="ellipsisDiv_full" >
						${rateMonthThree}&nbsp;
						</div>
					</td>
				</tr>
				</s:iterator>
				<tr>
					<td style="border-left:0px;" class="bottomTd">
						<div class="ellipsisDiv_full" >
						${totalReportVo.mustYear}&nbsp;
						</div>
					</td>
					<td class="bottomTd">
						<div class="ellipsisDiv_full" >
						${totalReportVo.factYear}&nbsp;
						</div>
					</td>
					<td class="bottomTd">
						<div class="ellipsisDiv_full" >
						${totalReportVo.rateYear}&nbsp;
						</div>
					</td>
					<td class="bottomTd">
						<div class="ellipsisDiv_full" >
						${totalReportVo.mustQuarter}&nbsp;
						</div>
					</td>
					<td class="bottomTd">
						<div class="ellipsisDiv_full" >
						${totalReportVo.factQuarter}&nbsp;
						</div>
					</td>
					<td class="bottomTd">
						<div class="ellipsisDiv_full" >
						${totalReportVo.rateQuarter}&nbsp;
						</div>
					</td>
					<td class="bottomTd">
						<div class="ellipsisDiv_full" >
						${totalReportVo.mustMonthOne}&nbsp;
						</div>
					</td>
					<td class="bottomTd">
						<div class="ellipsisDiv_full" >
						${totalReportVo.factMonthOne}&nbsp;
						</div>
					</td>
					<td class="bottomTd">
						<div class="ellipsisDiv_full" >
						${totalReportVo.rateMonthOne}&nbsp;
						</div>
					</td>
					<td class="bottomTd">
						<div class="ellipsisDiv_full" >
						${totalReportVo.mustMonthTwo}&nbsp;
						</div>
					</td>
					<td class="bottomTd">
						<div class="ellipsisDiv_full" >
						${totalReportVo.factMonthTwo}&nbsp;
						</div>
					</td>
					<td class="bottomTd">
						<div class="ellipsisDiv_full" >
						${totalReportVo.rateMonthTwo}&nbsp;
						</div>
					</td>
					<td class="bottomTd">
						<div class="ellipsisDiv_full" >
						${totalReportVo.mustMonthThree}&nbsp;
						</div>
					</td>
					<td class="bottomTd">
						<div class="ellipsisDiv_full" >
						${totalReportVo.factMonthThree}&nbsp;
						</div>
					</td>
					<td class="bottomTd">
						<div class="ellipsisDiv_full" >
						${totalReportVo.rateMonthThree}&nbsp;
						</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<div style="height: 50px; line-height: 50px;">

<div style="padding:10px 0px; line-height:30px; float:left;">
<input type="button" value="查看本项目费用" class="btn_blue" style="width:105px; font-size:12px;" onclick="viewFeeManage();"/>
<input type="button" value="查看本项目合同" class="btn_green" style="width:105px; font-size:12px;" onclick="viewContManage();"/>
</div>

</div>

<div style="border: 1px solid #cccccc;">

<div class="res_tip">
	<div style="padding-left:60px; line-height:30px; font-weight:bold; font-size:16px; width:40%; float:left;">
	欠费提醒&nbsp;&nbsp;&nbsp;&nbsp;总计：欠费${totalMoney}万元
	</div>
</div>
	
<table class="content_table" id="result_table" style="width: 100%">
	<col />
	<col />
	<col />
	<col />
	<col width="80"/>
	<col width="100"/>
	<col width="80"/>
	<col width="100"/>
	<col width="100"/>
	<thead>
		<tr class="header">
			<th style="background: none; padding-left: 20px;">商家</th>
			<th >面积</th>
			<th >累计应收</th>
			<th >累计实收</th>
			<th title="累计实收/累计应收">累计比例</th>
			<th >累计欠费</th>
			<th >欠费提醒</th>
			<th >欠费账龄分析</th>
			<th >保证金额度</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td colspan="9" style="font-size: 14px; cursor: default; font-weight: bold; background-color: #E4E7EC;height: 24px; line-height:24px;">
			<div style="float:left;padding:7px 5px 0 5px ;"><img src="${ctx}/resources/images/common/down_black.gif"></img></div>
			<div style="float:left;">商家</div>
			</td>
		</tr>
		<s:iterator value="storeMap" id="column" status="status">
			<s:if test="#column.value.size > 0">
			<tr id="group_<s:property value='#column.key'/>" class="group"> 
				<td colspan="9" class="group-title" style="padding-left: 20px;">
					<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisShopType()" value="key"/>(<s:property value='#column.value.size' />)
				</td>
			</tr>
			<s:iterator value="#column.value" status="s">
				<tr class="mainTr" group="group_<s:property value='#column.key'/>" >
					<td style="padding-left: 20px;">
						<div class="ellipsisDiv_full" >
						${name}
						</div>
					</td>
					<td>&nbsp;${area}</td>
					<td>&nbsp;${accumulateMust}</td>
					<td>&nbsp;${accumulateFact}</td>
					<td>&nbsp;${accumulateRate}%</td>
					<td>&nbsp;${accumulateArrears}</td>
					<td>
						<s:if test="#remindLevel == one">
						<span style="color: red;">一级提醒</span>
						</s:if>
						<s:elseif test="#remindLevel == two">
						<span style="color: yellow;">二级提醒</span>
						</s:elseif>
						<s:else>
						<span>三级提醒</span>
						</s:else>
					</td>
					<td>&nbsp;${arrearsAge}</td>
					<td>&nbsp;${earnestMoney}</td>
				</tr>
			</s:iterator>
			</s:if>
		</s:iterator>
		
		<tr>
			<td colspan="9" style="font-size: 14px; cursor: default; font-weight: bold; background-color: #E4E7EC;height: 24px; line-height:24px;">
			<div style="float:left;padding:7px 5px 0 5px ;"><img src="${ctx}/resources/images/common/down_black.gif"></img></div>
			<div style="float:left;">公寓</div>
			</td>
		</tr>
		<s:iterator value="flatList" status="s">
		<tr class="mainTr" >
			<td style="padding-left: 20px;">&nbsp;${name}</td>
			<td>&nbsp;${area}</td>
			<td>&nbsp;${accumulateMust}</td>
			<td>&nbsp;${accumulateFact}</td>
			<td>&nbsp;${accumulateRate}%</td>
			<td>&nbsp;${accumulateArrears}</td>
			<td>
				<s:if test="#remindLevel == one">
				<span style="color: red;">一级提醒</span>
				</s:if>
				<s:elseif test="#remindLevel == two">
				<span style="color: yellow;">二级提醒</span>
				</s:elseif>
				<s:else>
				<span>三级提醒</span>
				</s:else>
			</td>
			<td>&nbsp;${arrearsAge}</td>
			<td>&nbsp;${earnestMoney}</td>
		</tr>
		</s:iterator>
		<tr>
			<td colspan="9" style="font-size: 14px; cursor: default; font-weight: bold; background-color: #E4E7EC;height: 24px; line-height:24px;">
			<div style="float:left;padding:7px 5px 0 5px ;"><img src="${ctx}/resources/images/common/down_black.gif"></img></div>
			<div style="float:left;">多经</div>
			</td>
		</tr>
	</tbody>
</table>

</div>
<div style="height: 10px;"></div>

<script type="text/javascript">
$(function(){
	
	setMonthBg();
	
	bindTblEv();
});

function setMonthBg() {
	var monthIndex = '${monthIndex}';
	if(monthIndex == 1) {
		$("#monthOne").css({"background-color": "#6eb1ce", color: "#ffffff"});
	} else if(monthIndex == 2) {
		$("#monthTwo").css({"background-color": "#6eb1ce", color: "#ffffff"});
	} else {
		$("#monthThree").css({"background-color": "#6eb1ce", color: "#ffffff"});
	}
}

function bindTblEv() {
	$('#result_table tbody tr.group').toggle(function(){
		var id = $(this).attr('id');
		$('.mainTr[group="'+id+'"]').hide();
	},function(){
		var id = $(this).attr('id');
		$('.mainTr[group="'+id+'"]').show();
	});
}

</script>
