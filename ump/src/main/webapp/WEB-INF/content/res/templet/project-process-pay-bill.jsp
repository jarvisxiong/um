<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--工程进度款支付审批表（按100%）-->
<%@ include file="template-header.jsp"%>

<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable" >
		<col width="100px"/>
		<col width="30px"/>
		<col/>
		<col width="90px"/>
		<col width="100px"/>
		<col/>
			<tr>	 	 
				<td class="td_title">公司名称</td>
				<td colspan="3"><input class="inputBorder" validate="required"  type="text" id="templateBean.companyName" name="templateBean.companyName" value="${templateBean.companyName}"  /></td>
				<td colspan="2" style="text-align:left;" class="chkGroup" validate="required" >
					<s:checkbox name="templateBean.processTypeEarlyPeriod" cssClass="group"></s:checkbox>前期&nbsp;
					<s:checkbox name="templateBean.processTypeBuilder" cssClass="group"></s:checkbox>建筑&nbsp;
					<s:checkbox name="templateBean.processTypeInstall" cssClass="group"></s:checkbox>安装&nbsp;
					<br/>
					<s:checkbox name="templateBean.processTypeNetwork" cssClass="group"></s:checkbox>管网&nbsp;
					<s:checkbox name="templateBean.processTypeGardens" cssClass="group"></s:checkbox>园林&nbsp;
					<s:checkbox name="templateBean.processTypeMating" cssClass="group"></s:checkbox>配套
				</td>
			</tr>	
			<tr>	 	 
				<td class="td_title">项目名称</td>
				<td colspan="5">
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
			</tr>
			<tr>		 
				<td class="td_title">合同编号</td>
				<td colspan="2" align="left">
				
				
				<input type="hidden" id="contLedgerId"  />
					<s:if test="!templateBean.contractNo.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getContByno('${templateBean.contractNo}');">${templateBean.contractNo}</span>
						<input id="contactNoId" type="hidden" name="templateBean.contractNo" value="${templateBean.contractNo}" />
					</s:if>
					<s:else>
					<input class="inputBorder" validate="required" id="contactNoId" type="text" id="templateBean.contractNo" name="templateBean.contractNo" value="${templateBean.contractNo}"  />
					</s:else>
				</td>
				<td class="td_title">合同名称</td>
				<td colspan="2"><input class="inputBorder" validate="required" readonly="readonly" id="contactNameId" type="text" id="templateBean.contractName" name="templateBean.contractName" value="${templateBean.contractName}"  /></td>
			</tr>		
			<tr>		 
				<td class="td_title">合同金额(元)</td>
				<td colspan="2"><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="templateBean.contractAmt" name="templateBean.contractAmt" value="${templateBean.contractAmt}"  /></td>
				<td class="td_title">预借款余款(元)</td>
				<td colspan="2"><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="preLoanBalanceAmt" name="templateBean.preLoanBalanceAmt" value="${templateBean.preLoanBalanceAmt}"  /></td>
			</tr>	
			<tr>		
				<td class="td_title">签约单位</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" id="templateBean.contractSignedOrgName" name="templateBean.contractSignedOrgName" value="${templateBean.contractSignedOrgName}"  /></td>
				<td colspan="2" style="text-align:left;" class="chkGroup" validate="required">
					<s:checkbox name="templateBean.inFlag" cssClass="group" id="inFlag"></s:checkbox>预算内&nbsp;
					<s:checkbox name="templateBean.outFlag" cssClass="group" id="outFlag"></s:checkbox>预算外&nbsp;
				</td>
			</tr>
			<tr>
				<td rowspan="3" class="td_title" valign="middle">收款人信息</td>
				<td colspan="2" class="td_title">收款人名称</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.payer" value="${templateBean.payer}"/></td>
			</tr>
			<tr>
				<td colspan="2" class="td_title">收款人开户银行</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.payerAccount" value="${templateBean.payerAccount}"/></td>
			</tr>
			<tr>
				<td colspan="2" class="td_title">收款人账户号</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.payerBank" value="${templateBean.payerBank}"/></td>
			</tr>						
			<tr>
				<td class="td_title">约定进度付款比例</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" id="convProcPaymentRate" name="templateBean.convProcPaymentRate" value="${templateBean.convProcPaymentRate}" /></td>
				<td class="td_title">结算金额(元)</td>
				<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="templateBean.footingsAmt" name="templateBean.footingsAmt" value="${templateBean.footingsAmt}"  /></td>
			</tr>		
			<tr>
				<td class="td_title">产值付款比例</td>
				<td colspan="2" class="td_title">(累计甲供材扣除+累计付款)/累计产值</td>
				<td><input class="inputBorder" type="text" id="outputValueRate" readonly="readonly" name="templateBean.outputValueRate" value="${templateBean.outputValueRate}"  /></td>
				<td class="td_title">本次支付金额(元)</td>
				<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="curPaymentAmt" name="templateBean.curPaymentAmt" value="${templateBean.curPaymentAmt}"  /></td>
			</tr>					
			<tr>
				<td class="td_title">内容</td>
				<td colspan="2">项目说明</td>
				<td style="text-align: center;">上期累计(元)</td>
				<td style="text-align: center;">本期发生(元)</td>
				<td style="text-align: center;">本期累计(元)</td>
			</tr>	
			<tr>
				<td rowspan="4" class="td_title">完成产值(包含甲供材)</td>
				<td class="td_title">A1</td>
				<td class="td_title">完成合同内的工作产值</td>
				<td><input class="inputBorder" validate="required" type="text" id="prodPriorIncludeAmt" name="templateBean.prodPriorIncludeAmt" onblur="compute(this.id, 'prodCurIncludeAmt', 'prodCurIncludeAccuAmt');" value="${templateBean.prodPriorIncludeAmt}"  /></td>
				<td><input class="inputBorder" validate="required" type="text" id="prodCurIncludeAmt" name="templateBean.prodCurIncludeAmt" value="${templateBean.prodCurIncludeAmt}" onblur="compute(this.id, 'prodPriorIncludeAmt', 'prodCurIncludeAccuAmt');" /></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="prodCurIncludeAccuAmt" name="templateBean.prodCurIncludeAccuAmt" value="${templateBean.prodCurIncludeAccuAmt}"   class="autoCalculate"/></td>
			</tr>	
			<tr>
				<td class="td_title">A2</td>
				<td class="td_title">完成的变更及签证产值</td>
				<td><input class="inputBorder" validate="required" type="text" id="prodPriorChangeAmt" name="templateBean.prodPriorChangeAmt" value="${templateBean.prodPriorChangeAmt}" onblur="compute(this.id, 'prodCurChangeAmt', 'prodCurChangeAccuAmt');" /></td>
				<td><input class="inputBorder" validate="required" type="text" id="prodCurChangeAmt" name="templateBean.prodCurChangeAmt" value="${templateBean.prodCurChangeAmt}" onblur="compute(this.id, 'prodPriorChangeAmt', 'prodCurChangeAccuAmt');" /></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="prodCurChangeAccuAmt" name="templateBean.prodCurChangeAccuAmt" value="${templateBean.prodCurChangeAccuAmt}"   class="autoCalculate"/></td>
			</tr>	
			<tr>
				<td class="td_title">A3</td>
				<td class="td_title">完成的合同外委托产值</td>
				<td><input class="inputBorder" validate="required" type="text" id="prodPriorExtAmt" name="templateBean.prodPriorExtAmt" value="${templateBean.prodPriorExtAmt}" onblur="compute(this.id, 'prodCurExtAmt', 'prodCurExtAccuAmt');" /></td>
				<td><input class="inputBorder" validate="required" type="text" id="prodCurExtAmt" name="templateBean.prodCurExtAmt" value="${templateBean.prodCurExtAmt}" onblur="compute(this.id, 'prodPriorExtAmt', 'prodCurExtAccuAmt');" /></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="prodCurExtAccuAmt" name="templateBean.prodCurExtAccuAmt" value="${templateBean.prodCurExtAccuAmt}"   class="autoCalculate"/></td>
			</tr>
			<tr>
				<td class="td_title">A</td>
				<td class="td_title">小计=A1+A2+A3</td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="prodPriorSubtAmt" name="templateBean.prodPriorSubtAmt" value="${templateBean.prodPriorSubtAmt}" class="autoCalculate"/></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="prodCurSubtAmt" name="templateBean.prodCurSubtAmt" value="${templateBean.prodCurSubtAmt}" class="autoCalculate"/></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="prodCurAccuAmt" name="templateBean.prodCurAccuAmt" value="${templateBean.prodCurAccuAmt}" class="autoCalculate"/></td>
			</tr>
			<tr>
				<td class="td_title">预留款项</td>
				<td class="td_title">B</td>
				<td class="td_title">预留进度款</td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="obliPriorProcAmt" name="templateBean.obliPriorProcAmt" value="${templateBean.obliPriorProcAmt}" class="autoCalculate"/></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="obliCurProcAmt" name="templateBean.obliCurProcAmt" value="${templateBean.obliCurProcAmt}"  class="autoCalculate" /></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="obliCurProcAccuAmt" name="templateBean.obliCurProcAccuAmt" value="${templateBean.obliCurProcAccuAmt}"   class="autoCalculate"/></td>
			</tr> 
			<tr>
				<td rowspan="3" class="td_title">甲供材扣除(按暂定价)</td>
				<td class="td_title">C1</td>
				<td class="td_title">钢筋款</td>
				<td><input class="inputBorder" validate="required" type="text" id="jgSteelPriorAmt" name="templateBean.jgSteelPriorAmt" value="${templateBean.jgSteelPriorAmt}" onblur="compute(this.id, 'jgSteelCurAmt', 'jgSteelCurAccuAmt');" /></td>
				<td><input class="inputBorder" validate="required" type="text" id="jgSteelCurAmt" name="templateBean.jgSteelCurAmt" value="${templateBean.jgSteelCurAmt}" onblur="compute(this.id, 'jgSteelPriorAmt', 'jgSteelCurAccuAmt');" /></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="jgSteelCurAccuAmt" name="templateBean.jgSteelCurAccuAmt" value="${templateBean.jgSteelCurAccuAmt}" class="autoCalculate"/></td>
			</tr>	
			<tr>
				<td class="td_title">C2</td>
				<td class="td_title">其它</td>
				<td><input class="inputBorder" validate="required" type="text" id="jgOtherPriorAmt" name="templateBean.jgOtherPriorAmt" value="${templateBean.jgOtherPriorAmt}" onblur="compute(this.id, 'jgOtherCurAmt', 'jgOtherCurAccuAmt');" /></td>
				<td><input class="inputBorder" validate="required" type="text" id="jgOtherCurAmt" name="templateBean.jgOtherCurAmt" value="${templateBean.jgOtherCurAmt}" onblur="compute(this.id, 'jgOtherPriorAmt', 'jgOtherCurAccuAmt');" /></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="jgOtherCurAccuAmt" name="templateBean.jgOtherCurAccuAmt" value="${templateBean.jgOtherCurAccuAmt}"  class="autoCalculate"/></td>
			</tr>
			<tr>
				<td class="td_title">C</td>
				<td class="td_title">小计=C1+C2</td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="jgDeduPriorAmt" name="templateBean.jgDeduPriorAmt" value="${templateBean.jgDeduPriorAmt}"   class="autoCalculate"/></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="jgDeduCurAmt" name="templateBean.jgDeduCurAmt" value="${templateBean.jgDeduCurAmt}"  class="autoCalculate" /></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="jgDeduCurAccuAmt" name="templateBean.jgDeduCurAccuAmt" value="${templateBean.jgDeduCurAccuAmt}"   class="autoCalculate"/></td>
			</tr>
			<tr>
				<td rowspan="6" class="td_title">其它扣除</td>
				<td class="td_title">D1</td>
				<td class="td_title">扣除借款</td>
				<td><input class="inputBorder" validate="required" type="text" id="deduLoanPriorAmt" name="templateBean.deduLoanPriorAmt" value="${templateBean.deduLoanPriorAmt}" onblur="compute(this.id, 'deduLoanCurAmt', 'deduLoanCurAccuAmt');" /></td>
				<td><input class="inputBorder" validate="required" type="text" id="deduLoanCurAmt" name="templateBean.deduLoanCurAmt" value="${templateBean.deduLoanCurAmt}" onblur="compute(this.id, 'deduLoanPriorAmt', 'deduLoanCurAccuAmt');" /></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="deduLoanCurAccuAmt" name="templateBean.deduLoanCurAccuAmt" value="${templateBean.deduLoanCurAccuAmt}"   class="autoCalculate"/></td>
			</tr>	
			<tr>
				<td class="td_title">D2</td>
				<td class="td_title">扣代付的水电费</td>
				<td><input class="inputBorder" validate="required" type="text" id="deduPowerPriorAmt" name="templateBean.deduPowerPriorAmt" value="${templateBean.deduPowerPriorAmt}" onblur="compute(this.id, 'deduPowerCurAmt', 'deduPowerCurAccuAmt');" /></td>
				<td><input class="inputBorder" validate="required" type="text" id="deduPowerCurAmt" name="templateBean.deduPowerCurAmt" value="${templateBean.deduPowerCurAmt}" onblur="compute(this.id, 'deduPowerPriorAmt', 'deduPowerCurAccuAmt');" /></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="deduPowerCurAccuAmt" name="templateBean.deduPowerCurAccuAmt" value="${templateBean.deduPowerCurAccuAmt}" class="autoCalculate"/></td>
			</tr>	
			<tr>
				<td class="td_title">D3</td>
				<td class="td_title">扣代付的材料款、人工费</td>
				<td><input class="inputBorder" validate="required" type="text" id="deduMatePPriorAmt" name="templateBean.deduMatePPriorAmt" value="${templateBean.deduMatePPriorAmt}" onblur="compute(this.id, 'deduMatePCurAmt', 'deduMatePCurAccuAmt');" /></td>
				<td><input class="inputBorder" validate="required" type="text" id="deduMatePCurAmt" name="templateBean.deduMatePCurAmt" value="${templateBean.deduMatePCurAmt}" onblur="compute(this.id, 'deduMatePPriorAmt', 'deduMatePCurAccuAmt');" /></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="deduMatePCurAccuAmt" name="templateBean.deduMatePCurAccuAmt" value="${templateBean.deduMatePCurAccuAmt}"  class="autoCalculate"/></td>
			</tr>	
			<tr>
				<td class="td_title">D4</td>
				<td class="td_title">工期、质量、安全违约金</td>
				<td><input class="inputBorder" validate="required" type="text" id="deduDeditPriorAmt" name="templateBean.deduDeditPriorAmt" value="${templateBean.deduDeditPriorAmt}" onblur="compute(this.id, 'deduDeditCurAmt', 'deduDeditCurAccuAmt');" /></td>
				<td><input class="inputBorder" validate="required" type="text" id="deduDeditCurAmt" name="templateBean.deduDeditCurAmt" value="${templateBean.deduDeditCurAmt}" onblur="compute(this.id, 'deduDeditPriorAmt', 'deduDeditCurAccuAmt');" /></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="deduDeditCurAccuAmt" name="templateBean.deduDeditCurAccuAmt" value="${templateBean.deduDeditCurAccuAmt}"   class="autoCalculate"/></td>
			</tr>
			<tr>
				<td class="td_title">D5</td>
				<td class="td_title">其它</td>
				<td><input class="inputBorder" validate="required" type="text" id="deduOtherPriorAmt" name="templateBean.deduOtherPriorAmt" value="${templateBean.deduOtherPriorAmt}" onblur="compute(this.id, 'deduOtherCurAmt', 'deduOtherCurAccuAmt');" /></td>
				<td><input class="inputBorder" validate="required" type="text" id="deduOtherCurAmt" name="templateBean.deduOtherCurAmt" value="${templateBean.deduOtherCurAmt}" onblur="compute(this.id, 'deduOtherPriorAmt', 'deduOtherCurAccuAmt');" /></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="deduOtherCurAccuAmt" name="templateBean.deduOtherCurAccuAmt" value="${templateBean.deduOtherCurAccuAmt}" class="autoCalculate"/></td>
			</tr>
			<tr>
				<td class="td_title">D</td>
				<td class="td_title">小计=D1+D2+D3+D4+D5</td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="deduPriorSubtAmt" name="templateBean.deduPriorSubtAmt" value="${templateBean.deduPriorSubtAmt}"   class="autoCalculate"/></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="deduCurSubtAmt" name="templateBean.deduCurSubtAmt" value="${templateBean.deduCurSubtAmt}"   class="autoCalculate"/></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="deduCurSubtAccuAmt" name="templateBean.deduCurSubtAccuAmt" value="${templateBean.deduCurSubtAccuAmt}"   class="autoCalculate"/></td>
			</tr>	
			<tr>
				<td class="td_title"></td>
				<td class="td_title">E</td>
				<td class="td_title">本次实际应付=A-B-C-D</td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="realDealPriorAmt" name="templateBean.realDealPriorAmt" value="${templateBean.realDealPriorAmt}"   class="autoCalculate"/></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="realDealCurAmt" name="templateBean.realDealCurAmt" value="${templateBean.realDealCurAmt}"   class="autoCalculate"/></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="realDealCurAccuAmt" name="templateBean.realDealCurAccuAmt" value="${templateBean.realDealCurAccuAmt}"   class="autoCalculate"/></td>
			</tr>
			<tr>
				<td colspan="3"  class="td_title">实际累计已付金额(元)<br/>(不含C、D，对应E)</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="totalAlreadyPayment" name="templateBean.totalAlreadyPayment" value="${templateBean.totalAlreadyPayment}" class="autoCalculate" style="text-align: center;"/></td>
			</tr>
			<tr>
				<td colspan="3"  class="td_title">发票金额(元)</td>
				<td><input class="inputBorder" validate="required" type="text" id="receiptAmoutLastPeriod" name="templateBean.receiptAmoutLastPeriod" value="${templateBean.receiptAmoutLastPeriod}" class="autoCalculate" onblur="compute(this.id, 'receiptAmoutCurPeriod', 'receiptAmoutCurPeriodTotal');" /></td>
				<td><input class="inputBorder" validate="required" type="text" id="receiptAmoutCurPeriod" name="templateBean.receiptAmoutCurPeriod" value="${templateBean.receiptAmoutCurPeriod}" class="autoCalculate" onblur="compute(this.id, 'receiptAmoutLastPeriod', 'receiptAmoutCurPeriodTotal');" /></td>
				<td><input class="inputBorder" type="text" readonly="readonly" id="receiptAmoutCurPeriodTotal" name="templateBean.receiptAmoutCurPeriodTotal" value="${templateBean.receiptAmoutCurPeriodTotal}" class="autoCalculate"/></td>
			</tr>	
			<tr>
				<td colspan="3" class="td_title">需说明事项</td>
				<td colspan="3"><textarea  class="inputBorder contentTextArea" id="contentDesc" validate="required"  name="templateBean.contentDesc">${templateBean.contentDesc}</textarea></td>
			</tr>
			
			<%-- add condition by huangbijin --%>
			<s:if test="authTypeCd == 'FKGL_GCJD1'"><%--//工程进度款（甲供按100%扣除） --%>
			<tr>
				<td class="td_title" rowspan="2">应提供细项<br/>(请作为附件上传)</td>
				<td colspan="2" style="height:40px;"  validate="required" value="${templateBean.outputTotalFileId}">当月工程造价产值签证汇总表</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('当月工程造价产值签证汇总表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','outputTotalFileId',event);"/>
				<input  validate="required" type="hidden" name="templateBean.outputTotalFileId" id="outputTotalFileId" value="${templateBean.outputTotalFileId}"/>
				</s:if>
				</td>
				<td colspan="2">
				<div id="show_outputTotalFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.outputTotalFileId}','outputTotalFileId','${canEdit}');
				</script>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" style="height:40px;" validate="required" value="${templateBean.outputDetailFileId}">当月工程造价产值签证明细表</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('当月工程造价产值签证明细表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','outputDetailFileId',event);"/>
				<input  validate="required" type="hidden" name="templateBean.outputDetailFileId" id="outputDetailFileId" value="${templateBean.outputDetailFileId}"/>
				</s:if>
				</td>
				<td colspan="2">
				<div id="show_outputDetailFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.outputDetailFileId}','outputDetailFileId','${canEdit}');
				</script>
				</td>
			</tr> 
			</s:if>
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
	$("#contactNoId").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",["contNo","contName"],{contNo:"contactNoId",contName:"contactNameId"});
	function compute(summandEleId, addendEleId, sumEleId){
		var summandEle = $("#" + summandEleId);
		summandEle.val($.trim(summandEle.val()));
		if (!parseFloat(summandEle.val())) {
			summandEle.val("0.00");
		} else {
			summandEle.val(formatMoney(summandEle.val(), 2));
		}
		sum(summandEleId, addendEleId, sumEleId);

		computeTotalA();
		computeTotalB();
		computeTotalC();
		computeTotalD();
		computeTotalE();
		computePaymentPercent();
	}

	function computeTotalA() {
		var v1 = getVal("prodPriorIncludeAmt");
		var v2 = getVal("prodPriorChangeAmt");
		var v3 = getVal("prodPriorExtAmt");
		$("#prodPriorSubtAmt").val(formatMoney(v1 + v2 + v3, 2));

		v1 = getVal("prodCurIncludeAmt");
		v2 = getVal("prodCurChangeAmt");
		v3 = getVal("prodCurExtAmt");
		$("#prodCurSubtAmt").val(formatMoney(v1 + v2 + v3, 2));

		sum("prodPriorSubtAmt", "prodCurSubtAmt", "prodCurAccuAmt");
	}

	function computeTotalB() {
		var v = getVal("prodPriorSubtAmt");
		var r = getVal("convProcPaymentRate");
		$("#obliPriorProcAmt").val(formatMoney(v * (100-r) / 100 + "", 2));

		v = getVal("prodCurSubtAmt");
		$("#obliCurProcAmt").val(formatMoney(v * (100-r) / 100 + "", 2));

		sum("obliPriorProcAmt", "obliCurProcAmt", "obliCurProcAccuAmt");
	}

	function computeTotalC() {
		sum("jgSteelPriorAmt", "jgOtherPriorAmt", "jgDeduPriorAmt");
		sum("jgSteelCurAmt", "jgOtherCurAmt", "jgDeduCurAmt");
		sum("jgDeduPriorAmt", "jgDeduCurAmt", "jgDeduCurAccuAmt");
	}

	function computeTotalD() {
		var v1 = getVal("deduLoanPriorAmt");
		var v2 = getVal("deduPowerPriorAmt");
		var v3 = getVal("deduMatePPriorAmt");
		var v4 = getVal("deduDeditPriorAmt");
		var v5 = getVal("deduOtherPriorAmt");
		$("#deduPriorSubtAmt").val(formatMoney(v1 + v2 + v3 + v4 + v5, 2));

		v1 = getVal("deduLoanCurAmt");
		v2 = getVal("deduPowerCurAmt");
		v3 = getVal("deduMatePCurAmt");
		v4 = getVal("deduDeditCurAmt");
		v5 = getVal("deduOtherCurAmt");
		$("#deduCurSubtAmt").val(formatMoney(v1 + v2 + v3 + v4 + v5, 2));

		sum("deduPriorSubtAmt", "deduCurSubtAmt", "deduCurSubtAccuAmt");
	}

	function computeTotalE() {
		var v1 = getVal("prodPriorSubtAmt");
		var v2 = getVal("obliPriorProcAmt");
		var v3 = getVal("jgDeduPriorAmt");
		var v4 = getVal("deduPriorSubtAmt");
		$("#realDealPriorAmt").val(formatMoney(v1 - v2 - v3 - v4, 2));

		v1 = getVal("prodCurSubtAmt");
		v2 = getVal("obliCurProcAmt");
		v3 = getVal("jgDeduCurAmt");
		v4 = getVal("deduCurSubtAmt");
		$("#realDealCurAmt").val(formatMoney(v1 - v2 - v3 - v4, 2));

		sum("realDealPriorAmt", "realDealCurAmt", "realDealCurAccuAmt");
	}
	//计算产值付款比例
	function computePaymentPercent() {
		var v5 = getVal("prodCurAccuAmt");
		if (v5 == 0.00) {
			$("#outputValueRate").val("");
			return;
		}
		var v1 = getVal("jgDeduCurAccuAmt");
		var v2 = getVal("totalAlreadyPayment");
		var v3 = getVal("deduCurSubtAccuAmt");
		var v4 = getVal("curPaymentAmt");
		$("#outputValueRate").val(formatMoney(((v1 + v2 + v3 + v4)/v5 * 100), 2) + "%");
	}

	function sum(summandEleId, addendEleId, sumEleId) {
		var summand = getVal(summandEleId);
		var addend = getVal(addendEleId);
		$("#" + sumEleId).val(formatMoney(summand + addend, 2));
	}
</script>
