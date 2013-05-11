<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 变更费用核定单 --%>
<div class="billContent">
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
		<span class="wap_title">设计变更审批表:</span>
		<span class="wap_value">${templateBean.resApproveCd}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">设计变更审批表:</span>
		<span class="wap_value">${templateBean.resApproveTitleName}</span>
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
		<span class="wap_title">设计变更/现场变更审批表编号:</span>
		<span class="wap_value">${templateBean.visaNo}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">变更内容:</span>
		<span class="wap_value">${templateBean.visaContent}</span>
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
		<span class="wap_title">已累计变更额(元):</span>
		<span class="wap_value">${templateBean.amountUpdateTotal}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">已变更比例%:</span>
		<span class="wap_value">${templateBean.amountUpdateTotal}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">已审批预算(元):</span>
		<span class="wap_value">${templateBean.prepareFee}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">施工单位上报费用(元):</span>
		<span class="wap_value">${templateBean.workFee}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">地产公司审核费用(元):</span>
		<span class="wap_value">${templateBean.projectAuditFee}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">本次变更比例%:</span>
		<span class="wap_value">${templateBean.updateRate}</span>
	</div>
	<div class="div_label">
		<span class="wap_label">【核价编制说明】</span>
		<div class="div_row">
		1、变更费用按合同${templateBean.contItem}条款 &nbsp;第${templateBean.contWay} 种方法约定，其中工程量按计量，单价按
		<s:checkbox name="templateBean.contPrice"  cssClass="group"></s:checkbox><span>合同综合单价</span>
		<s:checkbox name="templateBean.refeContPrice"  cssClass="group"></s:checkbox><span>参考合同综合单价</span>
		<s:checkbox name="templateBean.fixedPrice"  cssClass="group"></s:checkbox><span>定额计价</span>
		确定，合同约定下浮率${templateBean.lowerRate}%
		</div>
		<div class="div_row">
		<span class="wap_title">2、扣减其他单位费用:</span>
		${templateBean.contItem}条款 &nbsp;第${templateBean.contWay} 种方法约定，其中工程量按计量，单价按
		<s:checkbox name="templateBean.haveOtherFee"  cssClass="group"></s:checkbox><span>无</span>
		<s:checkbox name="templateBean.noOtherFee"  cssClass="group"></s:checkbox><span>有</span>
		 ,已同时上报《扣减费用核定单》
		<s:checkbox name="templateBean.preHaveOtherFee"  cssClass="group"></s:checkbox><span>无</span>
		<s:checkbox name="templateBean.preNoOtherFee"  cssClass="group"></s:checkbox><span>有</span>
		。
		</div>
		<div class="div_row">
		<span class="wap_title">3、其他说明:</span>
		<span class="wap_value">${templateBean.otherDeclare}</span>
		</div>
	</div>
	<div class="div_row">
		<span class="wap_title">审批权限:</span>
		<div><s:checkbox name="templateBean.abovePercent3" cssClass="group"></s:checkbox><span>累计签证超过目标成本的3%</span></div>
		<div><s:checkbox name="templateBean.abovePercent5" cssClass="group"></s:checkbox><span>累计签证超过单项合同额5%且累计签证金额超过100万元</span></div>
		<div><s:checkbox name="templateBean.other" cssClass="group"></s:checkbox><span>其他</span></div>
	</div>
	<div class="div_label">
		<span class="wap_label">【附件】</span>
		<div class="div_row">
			<span class="wap_title">地产公司成本部审核预算:</span>
			<div id="show_projectAuditId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.projectAuditId}','projectAuditId','${canEdit}');
			</script>
		</div>
		<div class="div_row">
			<span class="wap_title">施工单位预算:</span>
			<div id="show_constructFeeId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.constructFeeId}','constructFeeId','${canEdit}');
			</script>
		</div>
		<div class="div_row">
			<span class="wap_title">设计变更审批表:</span>
			<div id="show_designVisaAppId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.designVisaAppId}','designVisaAppId','${canEdit}');
			</script>
		</div>
		<div class="div_row">
			<span class="wap_title">设计变更图纸或现场签证工程量确认单:</span>
			<div id="show_visaCheckId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.visaCheckId}','visaCheckId','${canEdit}');
			</script>
		</div>
	</div>
</div>