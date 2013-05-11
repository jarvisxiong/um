<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 履约保证金付款审批表  --%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
	</s:set>
	
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="145"/>
			<col/>
			<col width="125"/>
			<col/>
			<tr>
			<td class="td_title">审批权限</td>
			<td align="left" colspan="3">
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
				<td></td>
				<td colspan="3" class="chkGroup" align="left"  validate="required">
					<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<tr>
					<td><s:checkbox name="templateBean.inFlag"  cssClass="group"></s:checkbox>预算内</td>
					<td><s:checkbox name="templateBean.outFlag"  cssClass="group"></s:checkbox>预算外</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">合同编号</td>
				<td align="left">
				
				
				<input type="hidden" id="contLedgerId"  />
					<s:if test="!templateBean.contNo.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getContByno('${templateBean.contNo}');">${templateBean.contNo}</span>
						<input id="contNo" type="hidden" name="templateBean.contNo" value="${templateBean.contNo}" />
					</s:if>
					<s:else>
					<input id="contNo" class="inputBorder" validate="required" type="text" name="templateBean.contNo" value="${templateBean.contNo}" />
					</s:else>
				</td>
				<td class="td_title">合同名称</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="contName" name="templateBean.contName" value="${templateBean.contName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="3">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col/>
						<col width="20"/>
						<col width="40"/>
						<col width="20"/>
						<tr>
						<td>
						<input class="inputBorder" readonly="readonly" type="text" id="projectName" name="templateBean.projectName" value="${templateBean.projectName}" />
						<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
						</td>
						<td style="text-align: right;">(</td>
						<td>
						<input class="inputBorder" type="text" name="templateBean.projectPeriod" value="${templateBean.projectPeriod}" />
						</td>
						<td>)期</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">付款单位</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.paymentUnit" value="${templateBean.paymentUnit}" />
				</td>
			</tr>
			<tr>
				<td rowspan="3" class="td_title">收款人(乙方)信息</td>
				<td class="td_title">收款人名称</td>
				<td colspan="2">
				<input class="inputBorder" readonly="readonly" type="text" id="partB" name="templateBean.partB" value="${templateBean.partB}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">收款人开户行</td>
				<td colspan="2">
				<input class="inputBorder" validate="required" type="text" name="templateBean.payeeOpenBankNo" value="${templateBean.payeeOpenBankNo}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">收款人账号</td>
				<td colspan="2">
				<input class="inputBorder" validate="required" type="text" name="templateBean.payeeAccountNo" value="${templateBean.payeeAccountNo}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">实际开工日期</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="realBeginDate" name="templateBean.realBeginDate" value="${templateBean.realBeginDate}" />
				</td>
				<td class="td_title">实际竣工日期</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="realEndDate" name="templateBean.realEndDate" value="${templateBean.realEndDate}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">合同工期(天)</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="period" name="templateBean.period" value="${templateBean.period}" />
				</td>
				<td class="td_title">实际工期(天)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.realPeriod" value="${templateBean.realPeriod}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">保修期起止时间</td>
				<td colspan="3">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col/>
						<col width="20"/>
						<col/>
						<tr>
						<td>
						<input class="inputBorder" readonly="readonly" type="text" id="guarBeginDate" name="templateBean.guarBeginDate" value="${templateBean.guarBeginDate}" />
						</td>
						<td style="text-align: center;">至</td>
						<td>
						<input class="inputBorder" readonly="readonly" type="text" id="guarEndDate" name="templateBean.guarEndDate" value="${templateBean.guarEndDate}" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">工期延误说明</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.durationDelaysDesc" value="${templateBean.durationDelaysDesc}" />
				</td>
				<td class="td_title">应扣工期违约金</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.durationPenalty" value="${templateBean.durationPenalty}" id="durationPenalty" onblur="formatVal($(this));" onchange="onchange_countPerformBond();"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">质量验收说明</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.qualityAcceptDesc" value="${templateBean.qualityAcceptDesc}" />
				</td>
				<td class="td_title">应扣质量违约金</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.qualityPenalty" value="${templateBean.qualityPenalty}" id="qualityPenalty" onblur="formatVal($(this));" onchange="onchange_countPerformBond();"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">其他违约说明</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.otherRenegeDesc" value="${templateBean.otherRenegeDesc}" />
				</td>
				<td class="td_title">应扣其他违约金</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.otherRenegePenalty" value="${templateBean.otherRenegePenalty}" id="otherRenegePenalty" onblur="formatVal($(this));" onchange="onchange_countPerformBond();"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">实际缴纳履约保证金(元)</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.realPerformBond" value="${templateBean.realPerformBond}" id="realPerformBond" onblur="formatVal($(this));" onchange="onchange_countPerformBond();"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">应付履约保证金金额</td>
				<td colspan="3">
				<input class="inputBorder" readonly="readonly" type="text" name="templateBean.payPerformBond" value="${templateBean.payPerformBond}" id="payPerformBond" />
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="3">附件上传</td>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.leaveProblemsConformId}">工程竣工遗留问题确认单</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('工程竣工遗留问题确认单','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','leaveProblemsConformId',event);"/>
				<input type="hidden" name="templateBean.leaveProblemsConformId" id="leaveProblemsConformId" value="${templateBean.leaveProblemsConformId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_leaveProblemsConformId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.leaveProblemsConformId}','leaveProblemsConformId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.receiptVerifyId}">收据凭证</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('收据凭证','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','receiptVerifyId',event);"/>
				<input type="hidden" name="templateBean.receiptVerifyId" id="receiptVerifyId" value="${templateBean.receiptVerifyId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_receiptVerifyId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.receiptVerifyId}','receiptVerifyId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.warrantyAgreementId}">保修协议书</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('保修协议书','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','warrantyAgreementId',event);"/>
				<input type="hidden" name="templateBean.warrantyAgreementId" id="warrantyAgreementId" value="${templateBean.warrantyAgreementId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_warrantyAgreementId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.warrantyAgreementId}','warrantyAgreementId','${canEdit}');
				</script>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
function getContByno(contNo){
	var data = {contNo:contNo};
    var getContUrl="${ctx}/cont/cont-ledger!detail.action";
	var url;
	$.post(getContUrl,data, function(result){
		$("#contLedgerId").val(result);
		url="${ctx}/cont/cont-ledger!input.action?id="+$("#contLedgerId").val();
		if(parent.TabUtils==null)
			window.open(url);
		else
		  parent.TabUtils.newTab("cont-ledger-input","合同台账查看",url,true);
	});
	
}
	function onchange_countPerformBond() {
		var durationPenalty = getVal("durationPenalty");
		var qualityPenalty = getVal("qualityPenalty");
		var otherRenegePenalty = getVal("otherRenegePenalty");
		var realPerformBond = getVal("realPerformBond");
		
		if(realPerformBond != 0) {
			var payPerformBond = realPerformBond-durationPenalty-qualityPenalty-otherRenegePenalty;
			$("#payPerformBond").val(formatMoney(payPerformBond,2));
		}
	}
</script>

<s:if test="#canEdit=='true'">
<script type="text/javascript">

	var mapPrama={
		contNo:'contNo',
		contName:'contName',
		projectCd:'projectCd',
		projectName:'projectName',
		partB:'partB',
		realBeginDate:'realBeginDate',
		realEndDate:'realEndDate',
		period:'period',
		guarBeginDate:'guarBeginDate',
		guarEndDate:'guarEndDate'
	};
	$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama);
</script>
</s:if>
