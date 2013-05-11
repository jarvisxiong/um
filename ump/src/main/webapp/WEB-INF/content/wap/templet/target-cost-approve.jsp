<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 目标成本审批表 --%>

<div class="billContent">
	<s:set var="canEdit">false</s:set>
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
		<span class="wap_title">审批权限:</span>
		<div><s:checkbox name="templateBean.hotel" cssClass="group"></s:checkbox><span>与酒店有关</span></div>
		</div>
		<div class="div_row">
		<div><s:checkbox name="templateBean.checkType1" cssClass="group"></s:checkbox><span>目标成本(可研版)</span></div>
		<div><s:checkbox name="templateBean.checkType2" cssClass="group"></s:checkbox><span>目标成本</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
			(<span class="wap_value">${templateBean.projectPeriod}</span>)
			<span class="wap_title">期</span>
		</div>
		<div class="div_row">
		<span class="wap_title">开工时间:</span>
		<span class="wap_value">${templateBean.startDate}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">交房时间:</span>
		<span class="wap_value">${templateBean.submitDate}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">总建筑面积(㎡):</span>
		<span class="wap_value">${templateBean.totalBuildingArea}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">计入容积率建筑面积(㎡):</span>
		<span class="wap_value">${templateBean.capacityRatioBuildingArea}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">目标成本总额(元):</span>
		<span class="wap_value">${templateBean.totalTargetCost}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">容积率面积单方造价(元/㎡):</span>
		<span class="wap_value">${templateBean.capacityRatioUnitCost}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">主要内容及说明:</span>
		<span class="wap_value">${templateBean.remark}</span>
		</div>
		<div class="div_label">
			<span class="wap_label">【附件】</span>
			<div class="div_row">
				<span class="wap_title">规划指标或产品标准:</span>
				<div id="show_indicateOrProductStandardId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.indicateOrProductStandardId}','indicateOrProductStandardId','${canEdit}');
				</script>
			</div>
			<div class="div_row">
				<span class="wap_title">目标成本编制说明:</span>
				<div id="show_targetCostDrawingDescId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.targetCostDrawingDescId}','targetCostDrawingDescId','${canEdit}');
				</script>
			</div>
			<div class="div_row">
				<span class="wap_title">目标成本组成表:</span>
				<div id="show_targetCostSheetId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.targetCostSheetId}','targetCostSheetId','${canEdit}');
				</script>
			</div>
		</div>		
</div>
