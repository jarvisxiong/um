<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 设计费用变更审批表 --%>
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
		<div><s:checkbox name="templateBean.importDesignVisa" cssClass="group"></s:checkbox><span>重大设计变更</span></div>
		<div><s:checkbox name="templateBean.guraDesignVisa" cssClass="group"></s:checkbox><span>其他设计变更</span></div>
		<div><s:checkbox name="templateBean.otherVisa" cssClass="group"></s:checkbox><span>其他</span></div>
	</div>
	<div class="div_row">
 			<span class="wap_title">合同编号:</span>
 			<span class="wap_value">${templateBean.contNo}</span>
 	</div>
 	<div class="div_row">
 			<span class="wap_title">合同名称:</span>
 			<span class="wap_value">${templateBean.contName}</span>
 	</div>
 	<div class="div_row">
 			<span class="wap_title">乙方:</span>
 			<span class="wap_value">${templateBean.partB}</span>
 	</div>
 	<div class="div_row">
 			<span class="wap_title">项目名称:</span>
 			<span class="wap_value">${templateBean.projectName}(${templateBean.period})期</span>
 	</div>
 	<div class="div_row">
 			<span class="wap_title">变更内容:</span>
 			<span class="wap_value">${templateBean.visaContent}</span>
 	</div>
 	<div class="div_row">
 			<span class="wap_title">拟签发变更单编号:</span>
 			<span class="wap_value">${templateBean.issueVisaNo}</span>
 	</div>
 	<div class="div_row">
 		<span class="wap_title">变更原因:</span>
		<div><s:checkbox name="templateBean.visaReason1" cssClass="group"></s:checkbox><span>招商/销售原因业态改变</span></div>
		<div><s:checkbox name="templateBean.visaReason2" cssClass="group"></s:checkbox><span>市场定位改变</span></div>
		<div><s:checkbox name="templateBean.visaReason3" cssClass="group"></s:checkbox><span>设计范围调整</span></div>
		<div><s:checkbox name="templateBean.visaReason4" cssClass="group"></s:checkbox><span>设计条件改变</span></div>
		<div><s:checkbox name="templateBean.visaReason5" cssClass="group"></s:checkbox><span>产品设计风格调整</span></div>
		<div><s:checkbox name="templateBean.visaReason6" cssClass="group"></s:checkbox><span>法律法规调整</span></div>
		<div><s:checkbox name="templateBean.visaReason7" cssClass="group"></s:checkbox><span>其它</span></div>
	</div>
 	<div class="div_row">
 			<span class="wap_title">合同总价:</span>
 			<span class="wap_value">${templateBean.totalPrice}</span>
 	</div>
 	<div class="div_row">
 			<span class="wap_title">已确认合同总价:</span>
 			<span class="wap_value">${templateBean.updateTotal}</span>
 	</div>
 	<div class="div_row">
 			<span class="wap_title">已累计变更预估费用(元):</span>
 			<span class="wap_value">${templateBean.prepareFeeTotal}</span>
 	</div>
 	<div class="div_row">
 			<span class="wap_title">已预估变更比例%:</span>
 			<span class="wap_value">${templateBean.preFeeTotRate}</span>
 	</div>
 	<div class="div_row">
 			<span class="wap_title">本次预估费用(元):</span>
 			<span class="wap_value">${templateBean.prepareFee}</span>
 	</div>
 	<div class="div_row">
 			<span class="wap_title">本次预估变更比例%:</span>
 			<span class="wap_value">${templateBean.preFeeRate}</span>
 	</div>
 	<div class="div_row">
 			<span class="wap_title">本次设计费用增加后预估合同总价(元):</span>
 			<span class="wap_value">${templateBean.curVisaFeeAdd}</span>
 	</div>
 	<div class="div_row">
 			<span class="wap_title">本次设计费用增加后费用变更比例%:</span>
 			<span class="wap_value">${templateBean.curVisaFeeAddRate}</span>
 	</div>
 	<div class="div_row">
 			<span class="wap_title">备注说明:</span>
 			<span class="wap_value">${templateBean.remark}</span>
 	</div>
 	<div class="div_label">
 		<span class="wap_label">【附件】</span>
 		<div class="div_row">
 			<span class="wap_title">设计调整决策决议纪要:</span>
 			<div id="show_issueVisaDrawId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.issueVisaDrawId}','issueVisaDrawId','${canEdit}');
			</script>
 		</div>
 		<div class="div_row">
 			<span class="wap_title">设计费用调整预算:</span>
 			<div id="show_updateBudgetId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.updateBudgetId}','updateBudgetId','${canEdit}');
			</script>
 		</div>
 		<div class="div_row">
 			<span class="wap_title">提出部门的文件(如果有):</span>
 			<div id="show_adjureFileId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.adjureFileId}','adjureFileId','${canEdit}');
			</script>
 		</div>
 	</div>
 	<div class="div_row">
 		<span class="wap_title">审批权限:</span>
		<div><s:checkbox name="templateBean.land" cssClass="group"></s:checkbox><span>仅与地产有关</span></div>
		<div><s:checkbox name="templateBean.hotel" cssClass="group"></s:checkbox><span>与酒店有关</span></div>
		<div><s:checkbox name="templateBean.estate" cssClass="group"></s:checkbox><span>与商业有关</span></div>
	</div>
</div>
