<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 工程预付(借)款审批表 --%>
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
		<table class="mainTable">
			<col width="135"/>
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
					<td><s:checkbox name="templateBean.checkType1"  cssClass="group"></s:checkbox>预付款</td>
					<td><s:checkbox name="templateBean.checkType2"  cssClass="group"></s:checkbox>借款</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">合同编号</td>
				<td align="left">
				<input type="hidden" id="contLedgerId"  name="templateBean.contLedgerId" value="${templateBean.contLedgerId}" />
				
				
				
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
				<input class="inputBorder" readonly="readonly" type="text" id="payWay" name="templateBean.payWay" value="${templateBean.payWay}" />
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
				<td class="td_title">借款原因</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.loanReason">${templateBean.loanReason}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">本次付款申请金额(元)</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.applyAmount" value="${templateBean.applyAmount}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: center;font-weight: bold;">
				付款计算
				</td>
			</tr>
			<tr>
				<td colspan="4" style="padding-right: 0px; padding-left: 0px; ">
				<table id="tbConItem" style="width: 100%; border: 1px; solid #8c8f94;"  >
					<col width="200"/>
					<col/>
					<col/>
					<col/>
					<tr>
						<td ></td>
						<td class="td_title">已累计</td>
						<td class="td_title">本次</td>
						<td class="td_title">小计</td>
					</tr>
					<tr>
						<td class="td_title">已确认产值(含甲供料)</td>
						<td>
						<input class="inputBorder" readonly="readonly" type="text" id="completeNumBefore" name="templateBean.completeNumBefore" value="${templateBean.completeNumBefore}" />
						</td>
						<td>
						<input class="inputBorder" validate="required" type="text" id="completeNumThis" name="templateBean.completeNumThis" value="${templateBean.completeNumThis}" onblur="formatVal($(this));" onchange="onchange_countRatioAndTotal();"/>
						</td>
						<td >
						<input class="inputBorder" readonly="readonly" type="text" id="completeNumTotal" name="templateBean.completeNumTotal" value="${templateBean.completeNumTotal}" />
						</td>
					</tr>
					<tr>
						<td class="td_title">扣：甲供料款(按暂定价)</td>
						<td>
						<input class="inputBorder" type="text" readonly="readonly" id="matieralNumBefore" name="templateBean.matieralNumBefore" value="${templateBean.matieralNumBefore}" />
						</td>
						<td>
						<input class="inputBorder" validate="required" type="text" id="matieralNumThis" name="templateBean.matieralNumThis" value="${templateBean.matieralNumThis}" onblur="formatVal($(this));" onchange="onchange_countRatioAndTotal();"/>
						</td>
						<td >
						<input class="inputBorder" readonly="readonly" type="text" id="matieralNumTotal" name="templateBean.matieralNumTotal" value="${templateBean.matieralNumTotal}" />
						</td>
					</tr>
					<tr>
						<td class="td_title">扣：其他扣款或代扣款</td>
						<td>
						<input class="inputBorder" readonly="readonly" type="text" id="currentAddBefore" name="templateBean.currentAddBefore" value="${templateBean.currentAddBefore}" />
						</td>
						<td>
						<input class="inputBorder" validate="required" type="text" id="currentAddThis" name="templateBean.currentAddThis" value="${templateBean.currentAddThis}" onblur="formatVal($(this));" onchange="onchange_countRatioAndTotal();"/>
						</td>
						<td >
						<input class="inputBorder" readonly="readonly" type="text" id="currentAddTotal" name="templateBean.currentAddTotal" value="${templateBean.currentAddTotal}" />
						</td>
					</tr>
					<tr>
						<td class="td_title">直接支付</td>
						<td>
						<input class="inputBorder" readonly="readonly" type="text" id="currentPayBefore" name="templateBean.currentPayBefore" value="${templateBean.currentPayBefore}" />
						</td>
						<td>
						<input class="inputBorder" validate="required" type="text" id="currentPayThis" name="templateBean.currentPayThis" value="${templateBean.currentPayThis}" onblur="formatVal($(this));" onchange="onchange_countRatioAndTotal();"/>
						</td>
						<td >
						<input class="inputBorder" readonly="readonly" type="text" id="currentPayTotal" name="templateBean.currentPayTotal" value="${templateBean.currentPayTotal}" />
						</td>
					</tr>
					<tr>
						<td class="td_title">产值付款比例</td>
						<td>
						<input class="inputBorder" readonly="readonly" type="text" id="payRateBefore" name="templateBean.payRateBefore" value="${templateBean.payRateBefore}" />
						</td>
						<td>
						<input class="inputBorder" readonly="readonly" type="text" id="payRateThis" name="templateBean.payRateThis" value="${templateBean.payRateThis}" />
						</td>
						<td >
						<input class="inputBorder" readonly="readonly" type="text" id="payRateTotal" name="templateBean.payRateTotal" value="${templateBean.payRateTotal}" />
						</td>
					</tr>
					
				</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">备注</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" name="templateBean.description">${templateBean.description}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="1">附件上传</td>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.loanReasonId}">借款理由的相应附件</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('借款理由的相应附件','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','loanReasonId',event);"/>
				<input type="hidden" name="templateBean.loanReasonId" id="loanReasonId" value="${templateBean.loanReasonId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_loanReasonId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.loanReasonId}','loanReasonId','${canEdit}');
				</script>
				</td>
			</tr>
			<s:if test="statusCd==2">
			<tr>
				<td class="td_title">集团核定直接支付费用(元)</td>
				<td colspan="3">
				<input class="inputBorder" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true' validate="required"</s:if> type="text" id="groupMoney" name="templateBean.groupMoney" value="${templateBean.groupMoney}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			</s:if>
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
		var completeNumBefore = getVal("completeNumBefore");
		var matieralNumBefore = getVal("matieralNumBefore");
		var currentAddBefore = getVal("currentAddBefore");
		var currentPayBefore = getVal("currentPayBefore");
		
		var completeNumThis = getVal("completeNumThis");
		var matieralNumThis = getVal("matieralNumThis");
		var currentAddThis = getVal("currentAddThis");
		var currentPayThis = getVal("currentPayThis");
		
		var completeNumTotal = completeNumBefore+completeNumThis;
		var matieralNumTotal = matieralNumBefore+matieralNumThis;
		var currentAddTotal = currentAddBefore+currentAddThis;
		var currentPayTotal = currentPayBefore+currentPayThis;
		
		$("#completeNumTotal").val(formatMoney(completeNumTotal,2));
		$("#matieralNumTotal").val(formatMoney(matieralNumTotal,2));
		$("#currentAddTotal").val(formatMoney(currentAddTotal,2));
		$("#currentPayTotal").val(formatMoney(currentPayTotal,2));
		
		if(completeNumThis != 0) {
			var payRateThis = (matieralNumThis+currentAddThis+currentPayThis)/completeNumThis*100;
			$("#payRateThis").val(formatMoney(payRateThis,2));
		}
		if(completeNumTotal != 0) {
			var payRateTotal = (matieralNumTotal+currentAddTotal+currentPayTotal)/completeNumTotal*100;
			$("#payRateTotal").val(formatMoney(payRateTotal,2));
		}
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
		completeNum:'completeNumBefore',
		matieralNum:'matieralNumBefore',
		currentAdd:'currentAddBefore',
		currentPay:'currentPayBefore',
		payRate:'payRateBefore'
	};
	$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama,{},onchange_countRatioAndTotal);
</script>
</s:if>
