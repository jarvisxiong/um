<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<table class="mainTable" id="report_table" style="width: 98%; border: 1px;">
	<col width="180"/>
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
	<thead>
		<tr>
			<th style="height: 50px;">项目经营情况汇总表</th>
			<th colspan="3">本年度</th>
			<th colspan="3">本季度</th>
			<th colspan="3">季度第一月</th>
			<th colspan="3">季度第二月</th>
			<th colspan="3">季度第三月</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>收入</td>
			<td>实收</td>
			<td>应收</td>
			<td>%</td>
			<td>实收</td>
			<td>应收</td>
			<td>%</td>
			<td>实收</td>
			<td>应收</td>
			<td>%</td>
			<td>实收</td>
			<td>应收</td>
			<td>%</td>
			<td>实收</td>
			<td>应收</td>
			<td>%</td>
		</tr>
		<tr>
			<td>
				<table class="tb_input">
					<tr>
						<td rowspan="3">日常收入</td>
						<td>租金</td>
					</tr>
					<tr>
						<td>物业管理费</td>
					</tr>
					<tr>
						<td>能源费</td>
					</tr>
					<tr>
						<td rowspan="6">经营</td>
						<td>佣金</td>
					</tr>
					<tr>
						<td>停车费</td>
					</tr>
					<tr>
						<td>广告费</td>
					</tr>
					<tr>
						<td>招商代理费</td>
					</tr>
					<tr>
						<td>多经收入及其他</td>
					</tr>
					<tr>
						<td>保证金</td>
					</tr>
					<tr>
						<td colspan="2">合计</td>
					</tr>
				</table>
			</td>
			<td colspan="15">
				<table style="width:100%; border: 1px solid #8c8f94;">
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
					<s:iterator value="inlist">
					<tr class="mainTr" >	
						<td>${mustYear}</td>
						<td>${factYear}</td>
						<td>${rateYear}</td>
						<td>${mustQuarter}</td>
						<td>${factQuarter}</td>
						<td>${rateQuarter}</td>
						<td>${mustMonthOne}</td>
						<td>${factMonthOne}</td>
						<td>${rateMonthOne}</td>
						<td>${mustMonthTwo}</td>
						<td>${factMonthTwo}</td>
						<td>${rateMonthTwo}</td>
						<td>${mustMonthThree}</td>
						<td>${factMonthThree}</td>
						<td>${rateMonthThree}</td>
					</tr>
					</s:iterator>
				</table>
			</td>
		</tr>
		<tr>
			<td>支出</td>
			<td>预算</td>
			<td>实际</td>
			<td>%</td>
			<td>预算</td>
			<td>实际</td>
			<td>%</td>
			<td>预算</td>
			<td>实际</td>
			<td>%</td>
			<td>预算</td>
			<td>实际</td>
			<td>%</td>
			<td>预算</td>
			<td>实际</td>
			<td>%</td>
		</tr>
		<tr>
			<td>
				<table class="mainTable">
					<tr>
						<td>违约金</td>
					</tr>
					<tr>
						<td>成本费用支出</td>
					</tr>
					<tr>
						<td>广告宣传费</td>
					</tr>
					<tr>
						<td>人工福利费</td>
					</tr>
					<tr>
						<td>项目能源费</td>
					</tr>
					<tr>
						<td>日常办公支出</td>
					</tr>
					<tr>
						<td>合计</td>
					</tr>
				</table>
			</td>
			<td colspan="15">
				<table class="mainTable">
					<col width="40"/>
					<col width="40"/>
					<col width="40"/>
					<col width="40"/>
					<col width="40"/>
					<col width="40"/>
					<col width="40"/>
					<col width="40"/>
					<col width="40"/>
					<col width="40"/>
					<col width="40"/>
					<col width="40"/>
					<col width="40"/>
					<col width="40"/>
					<col width="40"/>
					<s:iterator value="outlist">
					<tr class="mainTr" >	
						<td>${mustYear}</td>
						<td>${factYear}</td>
						<td>${rateYear}</td>
						<td>${mustQuarter}</td>
						<td>${factQuarter}</td>
						<td>${rateQuarter}</td>
						<td>${mustMonthOne}</td>
						<td>${factMonthOne}</td>
						<td>${rateMonthOne}</td>
						<td>${mustMonthTwo}</td>
						<td>${factMonthTwo}</td>
						<td>${rateMonthTwo}</td>
						<td>${mustMonthThree}</td>
						<td>${factMonthThree}</td>
						<td>${rateMonthThree}</td>
					</tr>
					</s:iterator>
				</table>
			</td>
		</tr>
	</tbody>
</table>

<table class="content_table" id="arrears_table" style="width: 98%">
	<col />
	<col width="60"/>
	<col width="80"/>
	<col width="80"/>
	<col width="100"/>
	<col width="80"/>
	<col width="80"/>
	<col width="100"/>
	<col width="80"/>
	<thead>
		<tr class="header">
			<th style="background: none;">商家名称</th>
			<th >面积</th>
			<th >累计应收</th>
			<th >累计实收</th>
			<th >累计实收百分比</th>
			<th >累计欠费</th>
			<th >欠费提醒</th>
			<th >欠费账龄分析</th>
			<th >保证金额度</th>
		</tr>
	</thead>
	<tbody>
		<!--<s:iterator value="page.result">
			<tr class="mainTr" id="main_${bisContId}" onclick="goDetail('${bisContId}');" >	
				<td class="pd-chill-tip" title="${contNo}">${contNo}</td>
				<td class="pd-chill-tip" title="${contName}">${contName}</td>
				<td><p:code2name mapCodeName="mapContBigType" value="contBigTypeCd"/>-<p:code2name mapCodeName="mapContSmallType" value="contSmallTypeCd"/></td>
				<td><s:date name="contStartDate" format="yyyy-MM-dd"/></td>
				<td><s:date name="contEndDate" format="yyyy-MM-dd"/></td>
				<td><s:date name="signDate" format="yyyy-MM-dd"/></td>
				<td class="pd-chill-tip" title='<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("checkUserCd")) %>(<s:date name="checkDate" format="yyyy-MM-dd"/>)'>
					<s:date name="checkDate" format="yyyy-MM-dd"/>
				</td>
				<td style="color: red" >
					<s:if test="activeBl==true">
					<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapBisContStatus()" value="statusCd"/>
					</s:if>
					<s:else>已失效</s:else>
				</td>
			</tr>
		</s:iterator>
	--></tbody>
</table>