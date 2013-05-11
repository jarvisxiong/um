<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 目标成本调整审批表 --%>

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
		<span class="wap_title">原目标成本总额(元):</span>
		<span class="wap_value">${templateBean.oriTotalTargetCost}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">原容积率面积单方造价(元/㎡):</span>
		<span class="wap_value">${templateBean.oriCapacityRatioUnitCost}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">调整后目标成本总额(元):</span>
		<span class="wap_value">${templateBean.totalTargetCost}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">调整后容积率面积单方造价(元/㎡):</span>
		<span class="wap_value">${templateBean.capacityRatioUnitCost}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">调整子项成本明细表:</span>
		<div id="show_subItemTargetCostDetailId"></div>
		<script type="text/javascript">
		showUploadedFile('${templateBean.subItemTargetCostDetailId}','subItemTargetCostDetailId','${canEdit}');
		</script>
		</div>
		
		<div class=div_table_row>
		<span class="wap_title">序号/调整子项内容/调整原因/原子项目标成本/调整后子项目标成本</span>	
		<s:iterator value="templateBean.otherProperties" var="item" status="s">
		<div class="orgDiv">
			<s:property value="#s.index+1"/>.
			/${item.subItemContent}/${item.adjustReason}/${item.oriSubItemTargetCost}/${item.nowSubItemTargetCost}
		</div>
		</s:iterator>
		</div>
</div>