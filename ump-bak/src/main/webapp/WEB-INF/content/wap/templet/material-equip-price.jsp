<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--材料设备价格批定审批表-->
<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
		<div><s:checkbox name="templateBean.in5percent" cssClass="group"></s:checkbox><span>与约定价格差额5%以内</span></div>
		<div><s:checkbox name="templateBean.others" cssClass="group"></s:checkbox><span>其他情况</span></div>
	</div>	
	<div class="div_row">
		<span class="wap_title">项目名称:</span>
		<span class="wap_value">${templateBean.projectName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">工程名称:</span>
		<span class="wap_value">${templateBean.engineeringName}</span>
	</div>
	<div class="div_label">
	    <span class="wap_label">合同双方</span>
	    <div class="div_row">
	      <span class="wap_title">甲方:</span>
	      <span class="wap_value">${templateBean.sideA}</span>
	    </div>
	    <div class="div_row">
	      <span class="wap_title">乙方:</span>
	      <span class="wap_value">${templateBean.sideB}</span>
	    </div>
	    <!-- Start Add for part C by zhuxj on 2012.3.31 -->
	    <div class="div_row">
	      <span class="wap_title">丙方:</span>
	      <span class="wap_value">${templateBean.sideC}</span>
	    </div>
	    <!-- End   Add for part C by zhuxj on 2012.3.31 -->
	</div>
	<div class="div_row">
		<span class="wap_title">批价类型:</span>
		<div><s:checkbox name="templateBean.provideB" cssClass="group"></s:checkbox><span>甲定乙供</span></div>
		<div><s:checkbox name="templateBean.provideA" cssClass="group"></s:checkbox><span>甲供合同增补</span></div>
	</div>
	<div class="div_row">
		<span class="wap_title">材料名称:</span>
		<span class="wap_value">${templateBean.equipMaterialName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">进场时间:</span>
		<span class="wap_value">${templateBean.approachDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">进场时间:</span>
		<span class="wap_value">${templateBean.approachDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">原合同价(元):</span>
		<span class="wap_value">${templateBean.oriContractPrice}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">上报总价(元):</span>
		<span class="wap_value">${templateBean.reportTotalPrice}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">增补原因说明:</span>
		<span class="wap_value">${templateBean.addReason}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">原合同约定的品牌:</span>
		<span class="wap_value">${templateBean.oriContractBrand}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">原合同单价(元):</span>
		<span class="wap_value">${templateBean.oriContractUnitPrice}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">原合同总价(元):</span>
		<span class="wap_value">${templateBean.oriContractTotalPrice}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">推荐品牌:</span>
		<span class="wap_value">${templateBean.commendBrand}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">推荐品牌单价(元):</span>
		<span class="wap_value">${templateBean.commendBrandUnitPrice}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">推荐品牌总价(元):</span>
		<span class="wap_value">${templateBean.commendBrandTotalPrice}</span>
	</div>
	<div class="div_label">
	 <span class="wap_label">备注</span>
	 <div class="div_row">
	   <span class="wap_title">需批定价格原因说明:</span>
	   <span class="wap_value">${templateBean.approvePriceReason}</span>
	 </div>
	 <div class="div_row">
	   <span class="wap_title">推荐改品牌的理由:</span>
	   <span class="wap_value">${templateBean.commendBrandReason}</span>
	 </div>
	</div>
	<div class="div_label">
	  <span class="wap_title">应提供细项:</span>
	  <div class="div_row">
	     <span class="wap_title">材料数量清单(工程副总签字确认):</span>
	     <div id="show_meterialQuantityListId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.meterialQuantityListId}','meterialQuantityListId','${canEdit}');
			</script>
	  </div>
	  <div class="div_row">
	     <span class="wap_title">乙方报价清单:</span>
	     <div id="show_sideBPriceListId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.sideBPriceListId}','sideBPriceListId','${canEdit}');
			</script>
	  </div>
	  <div class="div_row">
	     <span class="wap_title">技术参数要求:</span>
	     <div id="show_teckParamRequireId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.teckParamRequireId}','teckParamRequireId','${canEdit}');
			</script>
	  </div>
	  <div class="div_row">
	     <span class="wap_title">相关图纸:</span>
	     <div id="show_relatedDrawingId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.relatedDrawingId}','relatedDrawingId','${canEdit}');
				</script>
	  </div>
	</div>
</div>