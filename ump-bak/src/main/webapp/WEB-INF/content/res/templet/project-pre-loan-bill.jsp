<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--工程预借款-->
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
	<form action="res-approve-info!save.action" method="post" id="templetForm" >
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="8%"/>
			<col width="8%"/>
			<col width="15%"/>
			<col width="19%"/>
			<col width="15%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="15%"/>
			<tr>
				<td colspan="2" class="td_title">公司名称</td>
				<td colspan="2"><input class="inputBorder"  validate="required" type="text" name="templateBean.companyName" value="${templateBean.companyName}"  /></td>
				<td colspan="1" class="td_title">项目名称</td>
				<td colspan="3">
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
			</tr>
			<tr>
				<td colspan="2" class="td_title">合同编号</td>
				<td colspan="2" align="left">
					<input type="hidden" id="contLedgerId"  />
					<s:if test="!templateBean.contractNo.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getContByno('${templateBean.contractNo}');">${templateBean.contractNo}</span>
						<input id="contractNoId" type="hidden" name="templateBean.contractNo" value="${templateBean.contractNo}" />
					</s:if>
					<s:else>
					<input class="inputBorder" validate="required" type="text" id="contractNoId" name="templateBean.contractNo" value="${templateBean.contractNo}" />
					</s:else>
				</td>
				<td colspan="1" class="td_title">合同总价(元)</td>
				<td colspan="3"><input class="inputBorder"  validate="required" type="text" onblur="formatVal($(this));" id="totoalContractPrices" name="templateBean.totoalContractPrices" value="${templateBean.totoalContractPrices}"  /></td>
			</tr>
			<tr>
				<td colspan="2" class="td_title">合同名称</td>
				<td colspan="6">
					<input class="inputBorder" validate="required" type="text" readonly="readonly" id="contractNameId" name="templateBean.contractName" value="${templateBean.contractName}"  />
				</td>
			</tr>
			<tr>
				<td colspan="2" class="td_title">借款单位</td>
				<td colspan="1"><input class="inputBorder"  validate="required" type="text" name="templateBean.loanOrgName" value="${templateBean.loanOrgName}"  /></td>
				<td colspan="1" class="td_title">预借款余额(元)</td>
				<td colspan="1"><input class="inputBorder"  validate="required" type="text" onblur="formatVal($(this));" name="templateBean.preLoanBalanceAmt" value="${templateBean.preLoanBalanceAmt}"   class="autoCalculate"/></td>
				<td colspan="2" class="td_title">A.本次拟借款(元)</td>
				<td colspan="1"><input class="inputBorder"  validate="required" type="text" onblur="formatVal($(this));" id="draftLoanAmt" name="templateBean.draftLoanAmt" value="${templateBean.draftLoanAmt}"  /></td>
			</tr>
			<tr>
				<td rowspan="3" class="td_title" valign="middle">收款人信息</td>
				<td class="td_title">收款人名称</td>
				<td colspan="7"><input class="inputBorder"  validate="required" type="text" name="templateBean.payer" value="${templateBean.payer}"/></td>
			</tr>
			<tr>
				<td class="td_title">收款人开户银行</td>
				<td colspan="7"><input class="inputBorder"  validate="required" type="text" name="templateBean.payerAccount" value="${templateBean.payerAccount}"/></td>
			</tr>
			<tr>
				<td class="td_title">收款人账户号</td>
				<td colspan="7"><input class="inputBorder"  validate="required" type="text" name="templateBean.payerBank" value="${templateBean.payerBank}"/></td>
			</tr>
			
			<tr>
				<td colspan="2" class="td_title">B.已付款总额(B=D+F)</td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="paidTotalAmt" name="templateBean.paidTotalAmt" value="${templateBean.paidTotalAmt}"  class="autoCalculate"/></td>
				<td class="td_title">D.实际领用甲供价格(暂定价)</td>
				<td><input class="inputBorder"  validate="required" type="text" onblur="compute(this.id);" id="realPickJgInterimPrice" name="templateBean.realPickJgInterimPrice" value="${templateBean.realPickJgInterimPrice}"  /></td>
				<td colspan="2" class="td_title">F.不含甲供已付款</td>
				<td><input class="inputBorder"  validate="required" type="text" onblur="compute(this.id);" id="noContainJgPaidAmt" name="templateBean.noContainJgPaidAmt" value="${templateBean.noContainJgPaidAmt}"  /></td>
			</tr>
			<tr>
				<td colspan="2" class="td_title">C.已完产值总额(C=E+G)</td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="finishProductAmt" name="templateBean.finishProductAmt" value="${templateBean.finishProductAmt}"  class="autoCalculate"/></td>
				<td class="td_title">E.按进度核定的甲供材料(暂定价)</td>
				<td><input class="inputBorder"  validate="required" type="text" onblur="compute(this.id);" id="procChkJgInterimPrice" name="templateBean.procChkJgInterimPrice" value="${templateBean.procChkJgInterimPrice}"  /></td>
				<td colspan="2" class="td_title">G.不含甲供产值(含甲供材料已开票税额)</td>
				<td><input class="inputBorder"  validate="required" type="text" onblur="compute(this.id);" id="noContainJgAmt" name="templateBean.noContainJgAmt" value="${templateBean.noContainJgAmt}"  /></td>
			</tr>	
			<tr>
				<td rowspan="2" class="td_title">付款比例(含甲供)</td>
				<td class="td_title">借款前B/C</td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="pcjgLoanBeforeRate" name="templateBean.pcjgLoanBeforeRate" value="${templateBean.pcjgLoanBeforeRate}"  /></td>
				<td class="td_title">甲供材料总限额(元)</td>
				<td><input class="inputBorder"  validate="required" type="text" onblur="formatVal($(this));" id="jgMaterialTotalQuotaAmt" name="templateBean.jgMaterialTotalQuotaAmt" value="${templateBean.jgMaterialTotalQuotaAmt}"  /></td>
				<td rowspan="2" align="right">付款比例(不含甲供)</td>
				<td class="td_title">借款前F/G</td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="pncjgLoanBeforeRate" name="templateBean.pncjgLoanBeforeRate" value="${templateBean.pncjgLoanBeforeRate}" /></td>
			</tr>	
			<tr>
				<td class="td_title">借款后(A+B)/C</td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="pcjgLoanAfterRate" name="templateBean.pcjgLoanAfterRate" value="${templateBean.pcjgLoanAfterRate}"  /></td>
				<td class="td_title">约定的进度款付款比例</td>
				<td><input class="inputBorder"  validate="required" type="text" name="templateBean.convProcPaymentRate" value="${templateBean.convProcPaymentRate}"  /></td>
				<td class="td_title">借款后(A+F)/G</td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="pncjgLoanAfterRate" name="templateBean.pncjgLoanAfterRate" value="${templateBean.pncjgLoanAfterRate}" /></td>
			</tr>	 
			<tr>	
				<td colspan="2" class="td_title">需说明事项</td>
				<td colspan="6"><textarea class="inputBorder contentTextArea"  validate="required" name="templateBean.contentDesc">${templateBean.contentDesc}</textarea></td>
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
	$("#contractNoId").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",["contNo","contName"],{contNo:"contractNoId",contName:"contractNameId"});
	
	function compute(srcEleId) {
		formatVal($("#" + srcEleId));
		computePaidTotalAmt();
		computeFinishProductAmt();
		computePcjgLoanBeforeRate();
		computePncjgLoanBeforeRate();
		computePcjgLoanAfterRate();
		computePncjgLoanAfterRate();
	}

	function computePaidTotalAmt() {
		sum("realPickJgInterimPrice", "noContainJgPaidAmt", "paidTotalAmt");
	}
	function computeFinishProductAmt() {
		sum("procChkJgInterimPrice", "noContainJgAmt", "finishProductAmt");
	}
	function computePcjgLoanBeforeRate() {
		var v2 = getVal("finishProductAmt");
		if (v2 == 0.00) {
			$("#pcjgLoanBeforeRate").val("");
			return;
		}
		var v1 = getVal("paidTotalAmt");
		$("#pcjgLoanBeforeRate").val(formatMoney(v1/v2 * 100, 2) + "%");
		
	}
	function computePncjgLoanBeforeRate() {
		var v2 = getVal("noContainJgAmt");
		if (v2 == 0.00) {
			$("#pncjgLoanBeforeRate").val("");
			return;
		}
		var v1 = getVal("noContainJgPaidAmt");
		$("#pncjgLoanBeforeRate").val(formatMoney(v1/v2 * 100, 2) + "%");
	}
	function computePcjgLoanAfterRate() {
		var v3 = getVal("finishProductAmt");
		if (v3 == 0.00) {
			$("#pcjgLoanAfterRate").val("");
			return;
		}
		var v1 = getVal("paidTotalAmt");
		var v2 = getVal("draftLoanAmt");
		$("#pcjgLoanAfterRate").val(formatMoney((v1 + v2)/v3 * 100, 2) + "%");
	}
	function computePncjgLoanAfterRate() {
		var v3 = getVal("noContainJgAmt");
		if (v3 == 0.00) {
			$("#pncjgLoanAfterRate").val("");
			return;
		}
		var v1 = getVal("draftLoanAmt");
		var v2 = getVal("noContainJgPaidAmt");
		$("#pncjgLoanAfterRate").val(formatMoney((v1 + v2)/v3 * 100, 2) + "%");
	}
	

	function sum(summandEleId, addendEleId, sumEleId) {
		var summand = getVal(summandEleId);
		var addend = getVal(addendEleId);
		$("#" + sumEleId).val(formatMoney(summand + addend, 2));
	}
</script>
