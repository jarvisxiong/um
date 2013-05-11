<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 现场变更审批表 --%>
<div class="billContent">
	  <s:set var="canEdit">false</s:set>
	  <%@ include file="template-var.jsp"%>
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
			<span class="wap_value">${templateBean.projectName}</span>
			(<span class="wap_value">${templateBean.period}</span>)
			<span class="wap_title">期</span>
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
			<div><s:checkbox name="templateBean.designWrong" cssClass="group"></s:checkbox><span>变更错漏</span></div>
			<div><s:checkbox name="templateBean.saleCause" cssClass="group"></s:checkbox><span>招商/销售原因</span></div>
			<div><s:checkbox name="templateBean.constructCause" cssClass="group"></s:checkbox><span>施工原因</span></div>
			<div><s:checkbox name="templateBean.partACause" cssClass="group"></s:checkbox><span>甲方原因</span></div>
			<div><s:checkbox name="templateBean.otherCause" cssClass="group"></s:checkbox><span>其它</span></div>
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
			<span class="wap_title">已预估变更比例%:</span>
			<span class="wap_value">${templateBean.preFeeRate}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">备注说明:</span>
			<span class="wap_value">${templateBean.remark}</span>
		</div>
		<div class="div_label">
			<span class="wap_label">【附件】</span>
			<div class="div_row">
			<span class="wap_title">拟签发指令单:</span>
			<div id="show_issueVisaInstrId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.issueVisaInstrId}','issueVisaInstrId','${canEdit}');
			</script>
			</div>
			<div class="div_row">
			<span class="wap_title">变更预算:</span>
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
			<div class="div_row">
			<span class="wap_title">拟签发指令单:</span>
			<div id="show_issueVisaInstrId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.issueVisaInstrId}','issueVisaInstrId','${canEdit}');
			</script>
			</div>
		</div>
</div>
