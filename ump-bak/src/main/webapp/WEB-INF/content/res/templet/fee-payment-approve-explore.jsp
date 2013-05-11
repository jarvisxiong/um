<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 勘察、测绘费、监理费付款审批表  --%>
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
		<input type="hidden" name="templateBean.resApproveCd" value="${approveCd}${serialNo}" >
	  	<input type="hidden" name="templateBean.resApproveId" value="${resApproveInfoId}" >
	  	<input type="hidden" id="contLedgerId"  name="templateBean.contLedgerId" value="${templateBean.contLedgerId}" />
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
					&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="templateBean.inFlag"  cssClass="group"></s:checkbox>预算内
					&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="templateBean.outFlag"  cssClass="group"></s:checkbox>预算外
				</td>
			</tr>
			<tr>
				<td class="td_title">合同编号</td>
				<td align="left">
				
				
				<s:if test="!templateBean.contLedgerId.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getCont('${templateBean.contLedgerId}');">${templateBean.contNo}</span>
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
				<td class="td_title">付款方式</td>
				<td colspan="3">
				<textarea id="payWay" class="inputBorder contentTextArea" readonly="readonly" name="templateBean.payWay">${templateBean.payWay}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">合同总价</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="totalPrice" name="templateBean.totalPrice" value="${templateBean.totalPrice}" />
				</td>
				<td class="td_title">已确认合同总价</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="updateTotal" name="templateBean.updateTotal" value="${templateBean.updateTotal}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">本次付款前已经支付(元)</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="currentPayBefore" name="templateBean.currentPayBefore" value="${templateBean.currentPayBefore}" />
				</td>
				<td class="td_title">本次付款前支付比例%</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="payRateBefore" name="templateBean.payRateBefore" value="${templateBean.payRateBefore}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">本次付款理由</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.currentPayReason" value="${templateBean.currentPayReason}" />
				</td>
				<td class="td_title">本次付款期数</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.currentPayPeriod" value="${templateBean.currentPayPeriod}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">付款依据</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.payDependence" value="${templateBean.payDependence}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">本次付款金额(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" id="currentPayThis" name="templateBean.currentPayThis" value="${templateBean.currentPayThis}" onblur="formatVal($(this));" onchange="onchange_countRatioAndTotal();"/>
				</td>
				<td class="td_title">本次付款比例%</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="payRateThis" name="templateBean.payRateThis" value="${templateBean.payRateThis}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">本次付款后付款总额(元)</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="currentPayTotal" name="templateBean.currentPayTotal" value="${templateBean.currentPayTotal}" />
				</td>
				<td class="td_title">本次付款后支付比例%</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="payRateTotal" name="templateBean.payRateTotal" value="${templateBean.payRateTotal}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">备注</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.remark">${templateBean.remark}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="1">附件上传</td>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.payDependenceId}">付款依据附件</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('付款依据附件','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','payDependenceId',event);"/>
				<input type="hidden" name="templateBean.payDependenceId" id="payDependenceId" value="${templateBean.payDependenceId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_payDependenceId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.payDependenceId}','payDependenceId','${canEdit}');
				</script>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
function getCont(id){
	var url="${ctx}/cont/cont-ledger!input.action?id="+id;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cont-ledger-input","合同台账查看",url,true);
}
	function onchange_countRatioAndTotal() {
		
		var totalPrice = getVal("totalPrice");
		var updateTotal = getVal("updateTotal");
		var currentPayBefore = getVal("currentPayBefore");
		var currentPayThis = getVal("currentPayThis");
		var currentPayTotal = currentPayBefore+currentPayThis;
		$("#currentPayTotal").val(formatMoney(currentPayTotal,2));
		
		if(updateTotal != 0) {
			var payRateThis = currentPayThis/updateTotal*100;
			$("#payRateThis").val(formatMoney(payRateThis,2));
		}
		if(updateTotal != 0) {
			var payRateTotal = currentPayTotal/updateTotal*100;
			$("#payRateTotal").val(formatMoney(payRateTotal,2));
		}
	}
	
	function countRateBefore() {
		var totalPrice = getVal("totalPrice");
		var updateTotal = getVal("updateTotal");
		var currentPayBefore = getVal("currentPayBefore");
		if(updateTotal != 0) {
			var payRateBefore = currentPayBefore/updateTotal*100;
			$("#payRateBefore").val(formatMoney(payRateBefore,2));
		}
		
		onchange_countRatioAndTotal();
	}
</script>

<s:if test="#canEdit=='true'">
<script type="text/javascript">

	var mapPrama={
		contLedgerId:'contLedgerId',
		contNo:'contNo',
		contName:'contName',
		projectCd:'projectCd',
		projectName:'projectName',
		partB:'partB',
		payWay:'payWay',
		totalPrice:'totalPrice',
		updateTotal:'updateTotal',
		currentPay:'currentPayBefore'
	};
	$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama,{},countRateBefore);
</script>
</s:if>
