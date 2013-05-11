<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 合同付款审批表 -->
<div id="billContent">
	
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">公司名称:</span>
			<span class="wap_value">${templateBean.companyName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
		</div>
		<s:if test="authTypeCd=='JD_CWGL_100'||authTypeCd=='FSS_ZYJD_CWGL_110'">
		<div class="div_row">
			<div><s:checkbox name="templateBean.contractArea1" cssClass="group"></s:checkbox><span>合同规定范围内</span></div>
			<div><s:checkbox name="templateBean.contractArea2" cssClass="group"></s:checkbox><span>超出合同规定</span></div>
		</div>
		</s:if>
		<s:if test="authTypeCd=='FKGL_YXFY'||authTypeCd=='FKGL_XZFY'||authTypeCd=='FKGL_GDZC'||authTypeCd=='FKGL_ZXFY'||authTypeCd=='JD_CWGL_100'">
		<s:hidden id="isEdit" value="true"/>
		<div class="div_row">
			<span class="wap_title">合同编号:</span>
			<div class="div_input">
			 <input edit='true' validate="required" type="text" name="templateBean.contractNo" value="${templateBean.contractNo}"/>
			</div>
		</div>
		<div class="div_row">
			<span class="wap_title">合同名称:</span>
			<div class="div_input">
			 <input edit='true' validate="required" type="text" name="templateBean.contractName" value="${templateBean.contractName}"/>
			</div>
		</div>
		</s:if>
		<s:else>
		<div class="div_row">
			<span class="wap_title">合同编号:</span>
			<span class="wap_value">${templateBean.contractNo}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">合同名称:</span>
			<span class="wap_value">${templateBean.contractName}</span>
		</div>
		</s:else>
		<div class="div_row">
			<span class="wap_title">合同总金额(元):</span>
			<span class="wap_value">${templateBean.contractTotalAmt}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">已付合同款(元):</span>
			<span class="wap_value">${templateBean.contractPaidAmt}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">本次支付金额(元):</span>
			<span class="wap_value">${templateBean.curPaymentAmt}</span>
		</div>
		<s:if test="authTypeCd!='FKGL_GCCL' && authTypeCd!='FKGL_SJF' && authTypeCd!='CWGL_JDBD_10' && authTypeCd!='CWGL_JDBD_20'">
		<div class="div_row">
			<div><s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox><span>预算外</span></div>
			<div><s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox><span>预算内</span></div>
		</div>
		</s:if>
		<s:if test="authTypeCd=='FKGL_XZFY'">
		<div class="div_row">
			<div><s:checkbox name="templateBean.salaryFlag" cssClass="group"></s:checkbox><span>员工工资</span></div>
		</div>
		</s:if>
		<div class="div_label">
			<span class="wap_label">【收款人信息】</span>
			<div class="div_row">
				<span class="wap_title">收款人名称:</span>
				<span class="wap_value">${templateBean.payer}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">收款人开户银行:</span>
				<span class="wap_value">${templateBean.payerAccount}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">收款人账户号:</span>
				<span class="wap_value">${templateBean.payerBank}</span>
			</div>
		</div>
		<div class="div_row">
				<span class="wap_title">需说明事项:</span>
				<span class="wap_value">${templateBean.contentDesc}</span>
		</div>
		<s:if test="authTypeCd=='FKGL_GCCL'">
		<div class="div_row">
				<span class="wap_title">结算审批表(附件):</span>
				<div id="show_settAppFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.settAppFileId}','settAppFileId','${canEdit}');
				</script>
		</div>
		</s:if>
</div>
