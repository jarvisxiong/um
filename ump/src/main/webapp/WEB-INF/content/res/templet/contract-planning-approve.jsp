<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 合约规划审批表  -->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<s:set var="conItemCount"><s:property value="templateBean.otherProperties.size()"/></s:set>
		<table  class="mainTable">
			<col width="100"/>
			<col width="50"/>
			<col width="180"/>
			<col width="120"/>
			<col/>
			<tr>
			<td class="td_title">审批权限</td>
			<td align="left" colspan="4">
				<table class="tb_checkbox" style="width:100%;">
				    <col width="100">
				    <col width="100">
					<col width="100">
					<col width="100">
					<tr>
					<td><s:checkbox name="templateBean.hotel"  cssClass="group"></s:checkbox>与酒店有关</td>
					</tr>
				</table>
			</td>
			</tr>
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="2">
				<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
				<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}" />
				</td>
				<td class="td_title">工程名称</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.engineeringName" value="${templateBean.engineeringName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">开发计划</td>
				<td colspan="4">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="20"/>
						<col width="200"/>
						<col/>
						<tr>
						<td >分</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.totalPeriodTimes" value="${templateBean.totalPeriodTimes}" />
						</td>
						<td style="padding-left: 10px;">期开发</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">
				开发周期
				<s:if test="statusCd==0 || statusCd==3">
				<input type="button"  class="btn_blue_55_20" style="border:none;"  value="增加周期" onclick="addItem();" />
				</s:if>
				</td>
				<td colspan="4">
					<table id="tbConItem" border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<col width="30"/>
						<col/>
						<col width="30"/>
						<col/>
						<col width="30"/>
						<col/>
						<col width="30"/>
						<col width="30"/>
						<s:if test="statusCd==0 || statusCd==3">
						<tr id="trConItem" style="display: none;margin-bottom:5px;">
							<td><input class="inputBorder" type="text" name="templateBean.otherProperties[0].periodNo"/></td>
							<td style="text-align: center;">期</td>
							<td><input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" name="templateBean.otherProperties[0].startDate"/></td>
							<td style="text-align: center;">至</td>
							<td><input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" name="templateBean.otherProperties[0].endDate"/></td>
							<td style="text-align: center;">共</td>
							<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[0].totalDay"  /></td>
							<td style="text-align: center;">天</td>
							<td width="15px" align="center"><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a></td>
						</tr>
						</s:if>
						<s:iterator value="templateBean.otherProperties" var="item" status="s">
						<tr style="margin-bottom:5px;">
							<td><input class="inputBorder" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].periodNo" value="${item.periodNo}" style="text-align: right;"/></td>
							<td style="text-align: center;">期</td>
							<td><input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].startDate" value="${item.startDate}" /></td>
							<td style="text-align: center;">至</td>
							<td><input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].endDate" value="${item.endDate}" /></td>
							<td style="text-align: center;">共</td>
							<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].totalDay" value="${item.totalDay}" /></td>
							<td style="text-align: center;">天</td>
							<td width="15px" align="center">
							<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
							<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
							</s:if>
							</td>
						</tr>
						</s:iterator>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="5" style="font-weight: bold; font-size: medium;">项目经济技术指标</td>
			</tr>
			<tr>
				<td style="border-top: none;text-align: center;font-weight: bold;">指标</td>
				<td style="border-top: none;text-align: center;font-weight: bold;">单位</td>
				<td colspan="2" style="border-top: none;text-align: center;font-weight: bold;">指标</td>
				<td style="border-top: none;text-align: center;font-weight: bold;">备注</td>
			</tr>
			<tr>
				<td class="td_title">总用地面积</td>
				<td style="text-align: center;">平方米</td>
				<td colspan="2"><input class="inputBorder" validate="required" type="text" name="templateBean.totalAreaIndex" value="${templateBean.totalAreaIndex}" onblur="formatVal($(this));"/></td>
				<td ><input class="inputBorder" validate="required" type="text" name="templateBean.totalAreaDesc" value="${templateBean.totalAreaDesc}" /></td>
			</tr>
			<tr>
				<td class="td_title">可开发用地面积</td>
				<td style="text-align: center;">平方米</td>
				<td colspan="2"><input class="inputBorder" validate="required" type="text" name="templateBean.developAreaIndex" value="${templateBean.developAreaIndex}" onblur="formatVal($(this));"/></td>
				<td ><input class="inputBorder" validate="required" type="text" name="templateBean.developAreaDesc" value="${templateBean.developAreaDesc}" /></td>
			</tr>
			<tr>
				<td class="td_title">规划容积率</td>
				<td style="text-align: center;">——</td>
				<td colspan="2"><input class="inputBorder" validate="required" type="text" name="templateBean.pcRateIndex" value="${templateBean.pcRateIndex}" onblur="formatVal($(this));"/></td>
				<td ><input class="inputBorder" validate="required" type="text" name="templateBean.pcRateDesc" value="${templateBean.pcRateDesc}" /></td>
			</tr>
			<tr>
				<td class="td_title">地上总建筑面积(计容积率)</td>
				<td style="text-align: center;">平方米</td>
				<td colspan="2"><input class="inputBorder" validate="required" type="text" name="templateBean.totalBuildAreaIndex" value="${templateBean.totalBuildAreaIndex}" onblur="formatVal($(this));"/></td>
				<td ><input class="inputBorder" validate="required" type="text" name="templateBean.totalBuildAreaDesc" value="${templateBean.totalBuildAreaDesc}" /></td>
			</tr>
			<tr>
				<td class="td_title">建筑密度</td>
				<td style="text-align: center;">%</td>
				<td colspan="2"><input class="inputBorder" validate="required" type="text" name="templateBean.buildDensityIndex" value="${templateBean.buildDensityIndex}" onblur="formatVal($(this));"/></td>
				<td ><input class="inputBorder" validate="required" type="text" name="templateBean.buildDensityDesc" value="${templateBean.buildDensityDesc}" /></td>
			</tr>
			<tr>
				<td class="td_title">绿化率</td>
				<td style="text-align: center;">%</td>
				<td colspan="2"><input class="inputBorder" validate="required" type="text" name="templateBean.greenRateIndex" value="${templateBean.greenRateIndex}" onblur="formatVal($(this));"/></td>
				<td ><input class="inputBorder" validate="required" type="text" name="templateBean.greenRateDesc" value="${templateBean.greenRateDesc}" /></td>
			</tr>
			<tr>
				<td colspan="5" style="font-weight: bold; font-size: medium;">全案产品配比</td>
			</tr>
			<tr>
				<td style="border-top: none;text-align: center;font-weight: bold;">产品类型</td>
				<td colspan="2" style="border-top: none;text-align: center;font-weight: bold;">预计建筑面积(㎡)</td>
				<td style="border-top: none;text-align: center;font-weight: bold;">面积占比(%)</td>
				<td style="border-top: none;text-align: center;font-weight: bold;">备注</td>
			</tr>
			<tr>
				<td class="td_title">住宅</td>
				<td colspan="2" ><input class="inputBorder" validate="required" type="text" name="templateBean.houseArea" value="${templateBean.houseArea}" onblur="formatVal($(this));" id="houseArea" onchange="onchange_totalArea();"/></td>
				<td ><input class="inputBorder" validate="required" type="text" name="templateBean.houseRate" value="${templateBean.houseRate}" onblur="formatVal($(this));" id="houseRate" onchange="onchange_totalRate();"/></td>
				<td ><input class="inputBorder" validate="required" type="text" name="templateBean.houseDesc" value="${templateBean.houseDesc}" /></td>
			</tr>
			<tr>
				<td class="td_title">酒店(星级)</td>
				<td colspan="2" ><input class="inputBorder" validate="required" type="text" name="templateBean.starHotelArea" value="${templateBean.starHotelArea}" onblur="formatVal($(this));" id="starHotelArea" onchange="onchange_totalArea();"/></td>
				<td ><input class="inputBorder" validate="required" type="text" name="templateBean.starHotelRate" value="${templateBean.starHotelRate}" onblur="formatVal($(this));" id="starHotelRate" onchange="onchange_totalRate();"/></td>
				<td ><input class="inputBorder" validate="required" type="text" name="templateBean.starHotelDesc" value="${templateBean.starHotelDesc}" /></td>
			</tr>
			<tr>
				<td class="td_title">度假公寓</td>
				<td colspan="2" ><input class="inputBorder" validate="required" type="text" name="templateBean.holidayFlatArea" value="${templateBean.holidayFlatArea}" onblur="formatVal($(this));" id="holidayFlatArea" onchange="onchange_totalArea();"/></td>
				<td ><input class="inputBorder" validate="required" type="text" name="templateBean.holidayFlatRate" value="${templateBean.holidayFlatRate}" onblur="formatVal($(this));" id="holidayFlatRate" onchange="onchange_totalRate();"/></td>
				<td ><input class="inputBorder" validate="required" type="text" name="templateBean.holidayFlatDesc" value="${templateBean.holidayFlatDesc}" /></td>
			</tr>
			<tr>
				<td class="td_title">企业公馆</td>
				<td colspan="2" ><input class="inputBorder" validate="required" type="text" name="templateBean.enteMansionArea" value="${templateBean.enteMansionArea}" onblur="formatVal($(this));" id="enteMansionArea" onchange="onchange_totalArea();"/></td>
				<td ><input class="inputBorder" validate="required" type="text" name="templateBean.enteMansionRate" value="${templateBean.enteMansionRate}" onblur="formatVal($(this));"  id="enteMansionRate" onchange="onchange_totalRate();"/></td>
				<td ><input class="inputBorder" validate="required" type="text" name="templateBean.enteMansionDesc" value="${templateBean.enteMansionDesc}" /></td>
			</tr>
			<tr>
				<td class="td_title">餐饮休闲街</td>
				<td colspan="2" ><input class="inputBorder" validate="required" type="text" name="templateBean.foodStreetArea" value="${templateBean.foodStreetArea}" onblur="formatVal($(this));" id="foodStreetArea" onchange="onchange_totalArea();"/></td>
				<td ><input class="inputBorder" validate="required" type="text" name="templateBean.foodStreetRate" value="${templateBean.foodStreetRate}" onblur="formatVal($(this));" id="foodStreetRate" onchange="onchange_totalRate();"/></td>
				<td ><input class="inputBorder" validate="required" type="text" name="templateBean.foodStreetDesc" value="${templateBean.foodStreetDesc}" /></td>
			</tr>
			<tr>
				<td class="td_title">商业mall</td>
				<td colspan="2" ><input class="inputBorder" validate="required" type="text" name="templateBean.mallArea" value="${templateBean.mallArea}" onblur="formatVal($(this));" id="mallArea" onchange="onchange_totalArea();"/></td>
				<td ><input class="inputBorder" validate="required" type="text" name="templateBean.mallRate" value="${templateBean.mallRate}" onblur="formatVal($(this));" id="mallRate" onchange="onchange_totalRate();"/></td>
				<td ><input class="inputBorder" validate="required" type="text" name="templateBean.mallDesc" value="${templateBean.mallDesc}" /></td>
			</tr>
			<tr>
				<td style="border-top: none;text-align: right;font-weight: bold;">合计</td>
				<td colspan="2" style="font-weight: bold;"><input class="inputBorder" validate="required" type="text" name="templateBean.totalArea" value="${templateBean.totalArea}" onblur="formatVal($(this));" id="totalArea" /></td>
				<td style="font-weight: bold;"><input class="inputBorder" validate="required" type="text" name="templateBean.totalRate" value="${templateBean.totalRate}" onblur="formatVal($(this));" id="totalRate" /></td>
				<td style="font-weight: bold;"><input class="inputBorder" validate="required" type="text" name="templateBean.totalDesc" value="${templateBean.totalDesc}" /></td>
			</tr>
			<tr>
				<td class="td_title" style="font-weight: bold;">附件</td>
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
		
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
	var trApprover=$("#trConItem").clone();
	$("#trConItem").remove();
	var cloneCount = 0;
	var index=${conItemCount};
	
	function addItem(){
		var trApprover_new=trApprover.clone();
		cloneCount++;
		$(trApprover_new).show();
		$(trApprover_new).find(".inputBorder").each(function(i){
			this.name=this.name.replace('0',index);
		});
		//$(trApprover_new).find("#cloneUserCds").attr("id",cloneCount+"cloneUserCds");
		$("#tbConItem").append(trApprover_new);
		index++;
	}
	function delItem(dom){
		$(dom).parent().parent().remove();
	}
	
	function onchange_totalArea(){
		var totalArea = getVal("houseArea")+
						getVal("starHotelArea")+
						getVal("holidayFlatArea")+
						getVal("enteMansionArea")+
						getVal("foodStreetArea")+
						getVal("mallArea");
		$("#totalArea").val(formatMoney(totalArea,2));
	}
	function onchange_totalRate(){
		var totalRate = getVal("houseRate")+
						 getVal("starHotelRate")+
						 getVal("holidayFlatRate")+
						 getVal("enteMansionRate")+
						 getVal("foodStreetRate")+
						 getVal("mallRate");
		$("#totalRate").val(formatMoney(totalRate,2));
	}
</script>
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
</script>
</s:if>