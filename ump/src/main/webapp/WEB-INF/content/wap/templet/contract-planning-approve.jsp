<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 合约规划审批表  -->

<div id="billContent">
	
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">审批权限:</span>
			<div><s:checkbox name="templateBean.hotel" cssClass="group"></s:checkbox><span>与酒店有关</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">工程名称:</span>
			<span class="wap_value">${templateBean.engineeringName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">开发计划:</span>
			<span class="wap_value">分${templateBean.totalPeriodTimes}期开发</span>
		</div>
		<div class="div_row">
			<span class="wap_title">开发周期:</span>
			<s:iterator value="templateBean.otherProperties" var="item" status="s">
			<div class="orgDiv">
				<s:property value="#s.index+1"/>./ ${item.periodNo} 期 ${item.startDate} 至 ${item.endDate} 共 ${item.totalDay} 天
			</div>
			</s:iterator>
		</div>
		
		<div class="div_label">
			<span class="wap_label">【项目经济技术指标】</span>
			<div class="div_scroll">
				<table>
					<tr>
						<td><span class="wap_title">指标</span></td>
						<td><span class="wap_title">单位</span></td>
						<td colspan="2"><span class="wap_title">指标</span></td>
						<td><span class="wap_title">备注</span></td>
					</tr>
					<tr>
						<td><span class="wap_title">总用地面积</span></td>
						<td><span class="wap_title">平方米</span></td>
						<td colspan="2"><span class="wap_value">${templateBean.totalAreaIndex}</span></td>
						<td><span class="wap_value">${templateBean.totalAreaDesc}</span></td>
					</tr>
					<tr>
						<td><span class="wap_title">可开发用地面积</span></td>
						<td><span class="wap_title">平方米</span></td>
						<td colspan="2"><span class="wap_value">${templateBean.developAreaIndex}</span></td>
						<td ><span class="wap_value">${templateBean.developAreaDesc}</span></td>
					</tr>
					<tr>
						<td><span class="wap_title">规划容积率</span></td>
						<td>——</td>
						<td colspan="2"><span class="wap_value">${templateBean.pcRateIndex}</span></td>
						<td ><span class="wap_value">${templateBean.pcRateDesc}</span></td>
					</tr>
					<tr>
						<td><span class="wap_title">地上总建筑面积(计容积率)</span></td>
						<td><span class="wap_title">平方米</span></td>
						<td colspan="2"><span class="wap_value">${templateBean.totalBuildAreaIndex}</span></td>
						<td ><span class="wap_value">${templateBean.totalBuildAreaDesc}</span></td>
					</tr>
					<tr>
						<td><span class="wap_title">建筑密度</span></td>
						<td>%</td>
						<td colspan="2"><span class="wap_value">${templateBean.buildDensityIndex}</span></td>
						<td ><span class="wap_value">${templateBean.buildDensityDesc}</span></td>
					</tr>
					<tr>
						<td><span class="wap_title">绿化率</span></td>
						<td>%</td>
						<td colspan="2"><span class="wap_value">${templateBean.greenRateIndex}</span></td>
						<td ><span class="wap_value">${templateBean.greenRateDesc}</span></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="div_label">
			<span class="wap_label">【全案产品配比】</span>
			<div class="div_scroll">
				<table>
					<tr>
						<td><span class="wap_title">产品类型</span></td>
						<td colspan="2"><span class="wap_title">预计建筑面积(㎡)</span></td>
						<td><span class="wap_title">面积占比(%)</span></td>
						<td><span class="wap_title">备注</span></td>
					</tr>
					<tr>
						<td><span class="wap_title">住宅</span></td>
						<td colspan="2" ><span class="wap_value">${templateBean.houseArea}</span></td>
						<td ><span class="wap_value">${templateBean.houseRate}</span></td>
						<td ><span class="wap_value">${templateBean.houseDesc}</span></td>
					</tr>
					<tr>
						<td><span class="wap_title">酒店(星级)</span></td>
						<td colspan="2" ><span class="wap_value">${templateBean.starHotelArea}</span></td>
						<td ><span class="wap_value">${templateBean.starHotelRate}</span></td>
						<td ><span class="wap_value">${templateBean.starHotelDesc}</span></td>
					</tr>
					<tr>
						<td><span class="wap_title">度假公寓</span></td>
						<td colspan="2" ><span class="wap_value">${templateBean.holidayFlatArea}</span></td>
						<td ><span class="wap_value">${templateBean.holidayFlatRate}</span></td>
						<td ><span class="wap_value">${templateBean.holidayFlatDesc}</span></td>
					</tr>
					<tr>
						<td><span class="wap_title">企业公馆</span></td>
						<td colspan="2" ><span class="wap_value">${templateBean.enteMansionArea}</span></td>
						<td ><span class="wap_value">${templateBean.enteMansionRate}</span></td>
						<td ><span class="wap_value">${templateBean.enteMansionDesc}</span></td>
					</tr>
					<tr>
						<td><span class="wap_title">餐饮休闲街</span></td>
						<td colspan="2" ><span class="wap_value">${templateBean.foodStreetArea}</span></td>
						<td ><span class="wap_value">${templateBean.foodStreetRate}</span></td>
						<td ><span class="wap_value">${templateBean.foodStreetDesc}</span></td>
					</tr>
					<tr>
						<td><span class="wap_title">商业mall</span></td>
						<td colspan="2" ><span class="wap_value">${templateBean.mallArea}</span></td>
						<td ><span class="wap_value">${templateBean.mallRate}</span></td>
						<td ><span class="wap_value">${templateBean.mallDesc}</span></td>
					</tr>
					<tr>
						<td><span class="wap_title">合计</span></td>
						<td colspan="2"><span class="wap_value">${templateBean.totalArea}</span></td>
						<td><span class="wap_value">${templateBean.totalRate}</span></td>
						<td><span class="wap_value">${templateBean.totalDesc}</span></td>
					</tr>
					<tr>
						<td><span class="wap_title">附件</span></td>
						<td colspan="4">
							<s:url id="downContractPlanning" action="download" namespace="/app" includeParams="none" >
						  	  <s:param name="fileName">ContractPlanning(Approve).xls</s:param>
						  	  <s:param name="realFileName">合约规划(审批版).xls</s:param>
						  	  <s:param name="bizModuleCd">resDownload</s:param>
							</s:url>
							<a href="${downContractPlanning}">合约规划(审批版)</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		
		
		
</div>
