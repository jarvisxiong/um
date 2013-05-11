<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@ include file="template-header.jsp"%>
<!--工程进度款支付审批表（按比例扣除）-->
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
				<col width="11%"/>
				<col width="3%"/>
				<col width="12%"/>
				<col width="21%"/>
				<col width="21%"/>
				<col width="21%"/>
					<tr>	 	 
						<td class="td_title">公司名称</td>
						<td colspan="3"><input class="inputBorder" validate="required"  type="text" id="templateBean.companyName" name="templateBean.companyName" value="${templateBean.companyName}"  /></td>
						<td colspan="2" class="chkGroup" validate="required" >
							<s:checkbox name="templateBean.processTypeEarlyPeriod" cssClass="group"></s:checkbox>前期&nbsp;
							<s:checkbox name="templateBean.processTypeBuilder" cssClass="group"></s:checkbox>建筑&nbsp;
							<s:checkbox name="templateBean.processTypeInstall" cssClass="group"></s:checkbox>安装
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
						<td colspan="2"><input class="inputBorder" validate="required" id="contactNameId" type="text" readonly="readonly" id="templateBean.contractName" name="templateBean.contractName" value="${templateBean.contractName}"  /></td>
					</tr>		
					<tr>		 
						<td class="td_title">合同金额(元)</td>
						<td colspan="2"><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="contractAmt" name="templateBean.contractAmt" value="${templateBean.contractAmt}"  /></td>
						<td class="td_title">预借款余款(元)</td>
						<td colspan="2"><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="preLoanBalanceAmt" name="templateBean.preLoanBalanceAmt" value="${templateBean.preLoanBalanceAmt}"  /></td>
					</tr>	
					<tr>		
						<td class="td_title">签约单位</td>
						<td colspan="3"><input class="inputBorder" validate="required" type="text" id="templateBean.contractSignedOrgName" name="templateBean.contractSignedOrgName" value="${templateBean.contractSignedOrgName}"  /></td>
						<td colspan="2" class="chkGroup" validate="required" >
							<s:checkbox name="templateBean.inFlag" cssClass="group" id="inFlag"></s:checkbox>预算内&nbsp;
							<s:checkbox name="templateBean.outFlag" cssClass="group" id="outFlag"></s:checkbox>预算外&nbsp;
						</td>
					</tr>
					<tr>
						<td rowspan="3" align="center" valign="middle">收款人信息</td>
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
						<td colspan="3"><input class="inputBorder" validate="required" type="text" id="convProcPaymentRate" name="templateBean.convProcPaymentRate" value="${templateBean.convProcPaymentRate}"/></td>
						<td class="td_title">结算金额(元)</td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="footingsAmt" name="templateBean.footingsAmt" value="${templateBean.footingsAmt}"  /></td>
					</tr>		
					<tr>
						<td class="td_title">产值付款比例</td>
						<td colspan="2" class="td_title">(累计其他扣除+累计付款)/(累计产值-累计甲供材扣除)</td>
						<td><input class="inputBorder" type="text" id="outputValueRate" readonly="readonly" name="templateBean.outputValueRate" value="${templateBean.outputValueRate}"  /></td>
						<td class="td_title">本次支付金额(元)</td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="curPaymentAmt" name="templateBean.curPaymentAmt" value="${templateBean.curPaymentAmt}"  /></td>
					</tr>					
					<tr>
						<td>内容</td>
						<td colspan="2">项目说明</td>
						<td>上期累计(元)</td>
						<td>本期发生(元)</td>
						<td>本期累计(元)</td>
					</tr>	
					<tr>
						<td rowspan="4" class="td_title">完成产值(包含甲供材):</td>
						<td class="td_title">A1</td>
						<td class="td_title">完成合同内的工作产值</td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="prodPriorIncludeAmt" name="templateBean.prodPriorIncludeAmt" value="${templateBean.prodPriorIncludeAmt}"  /></td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="prodCurIncludeAmt" name="templateBean.prodCurIncludeAmt" value="${templateBean.prodCurIncludeAmt}" /></td>
						<td><input class="inputBorder" type="text" readonly="readonly" id="prodCurIncludeAccuAmt" name="templateBean.prodCurIncludeAccuAmt" value="${templateBean.prodCurIncludeAccuAmt}"   class="autoCalculate"/></td>
					</tr>	
					<tr>
						<td class="td_title">A2</td>
						<td class="td_title">完成的变更及签证产值</td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="prodPriorChangeAmt" name="templateBean.prodPriorChangeAmt" value="${templateBean.prodPriorChangeAmt}" /></td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="prodCurChangeAmt" name="templateBean.prodCurChangeAmt" value="${templateBean.prodCurChangeAmt}" /></td>
						<td><input class="inputBorder" type="text" readonly="readonly" id="prodCurChangeAccuAmt" name="templateBean.prodCurChangeAccuAmt" value="${templateBean.prodCurChangeAccuAmt}"   class="autoCalculate"/></td>
					</tr>	
					<tr>
						<td class="td_title">A3</td>
						<td class="td_title">完成的合同外委托产值</td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="prodPriorExtAmt" name="templateBean.prodPriorExtAmt" value="${templateBean.prodPriorExtAmt}" /></td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="prodCurExtAmt" name="templateBean.prodCurExtAmt" value="${templateBean.prodCurExtAmt}" /></td>
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
						<td rowspan="3" class="td_title">甲供材扣除(按暂定价)</td>
						<td class="td_title">B1</td>
						<td class="td_title">钢筋款</td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="jgSteelPriorAmt" name="templateBean.jgSteelPriorAmt" value="${templateBean.jgSteelPriorAmt}" /></td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="jgSteelCurAmt" name="templateBean.jgSteelCurAmt" value="${templateBean.jgSteelCurAmt}" /></td>
						<td><input class="inputBorder" type="text" readonly="readonly" id="jgSteelCurAccuAmt" name="templateBean.jgSteelCurAccuAmt" value="${templateBean.jgSteelCurAccuAmt}" class="autoCalculate"/></td>
					</tr>	
					<tr>
						<td class="td_title">B2</td>
						<td class="td_title">其它</td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="jgOtherPriorAmt" name="templateBean.jgOtherPriorAmt" value="${templateBean.jgOtherPriorAmt}" /></td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="jgOtherCurAmt" name="templateBean.jgOtherCurAmt" value="${templateBean.jgOtherCurAmt}" /></td>
						<td><input class="inputBorder" type="text" readonly="readonly" id="jgOtherCurAccuAmt" name="templateBean.jgOtherCurAccuAmt" value="${templateBean.jgOtherCurAccuAmt}"  class="autoCalculate"/></td>
					</tr>
					<tr>
						<td class="td_title">B</td>
						<td class="td_title">小计=B1+B2</td>
						<td><input class="inputBorder" type="text" readonly="readonly" id="jgDeduPriorAmt" name="templateBean.jgDeduPriorAmt" value="${templateBean.jgDeduPriorAmt}"   class="autoCalculate"/></td>
						<td><input class="inputBorder" type="text" readonly="readonly" id="jgDeduCurAmt" name="templateBean.jgDeduCurAmt" value="${templateBean.jgDeduCurAmt}"  class="autoCalculate" /></td>
						<td><input class="inputBorder" type="text" readonly="readonly" id="jgDeduCurAccuAmt" name="templateBean.jgDeduCurAccuAmt" value="${templateBean.jgDeduCurAccuAmt}"   class="autoCalculate"/></td>
					</tr>
					<tr>
						<td class="td_title">预留款项</td>
						<td class="td_title">C</td>
						<td class="td_title">预留进度款(A-B)*(1-拨付率)</td>
						<td><input class="inputBorder" validate="required" type="text" id="obliPriorProcAmt" name="templateBean.obliPriorProcAmt" value="${templateBean.obliPriorProcAmt}" class="autoCalculate"/></td>
						<td><input class="inputBorder" validate="required" type="text" id="obliCurProcAmt" name="templateBean.obliCurProcAmt" value="${templateBean.obliCurProcAmt}"  class="autoCalculate" /></td>
						<td><input class="inputBorder" validate="required" type="text" id="obliCurProcAccuAmt" name="templateBean.obliCurProcAccuAmt" value="${templateBean.obliCurProcAccuAmt}"   class="autoCalculate"/></td>
					</tr> 
					<tr>
						<td rowspan="6" class="td_title">其它扣除</td>
						<td class="td_title">D1</td>
						<td class="td_title">扣除借款</td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="deduLoanPriorAmt" name="templateBean.deduLoanPriorAmt" value="${templateBean.deduLoanPriorAmt}"/></td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="deduLoanCurAmt" name="templateBean.deduLoanCurAmt" value="${templateBean.deduLoanCurAmt}" /></td>
						<td><input class="inputBorder" type="text" readonly="readonly" id="deduLoanCurAccuAmt" name="templateBean.deduLoanCurAccuAmt" value="${templateBean.deduLoanCurAccuAmt}"   class="autoCalculate"/></td>
					</tr>	
					<tr>
						<td class="td_title">D2</td>
						<td class="td_title">扣代付的水电费</td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="deduPowerPriorAmt" name="templateBean.deduPowerPriorAmt" value="${templateBean.deduPowerPriorAmt}"/></td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="deduPowerCurAmt" name="templateBean.deduPowerCurAmt" value="${templateBean.deduPowerCurAmt}" /></td>
						<td><input class="inputBorder" type="text" readonly="readonly" id="deduPowerCurAccuAmt" name="templateBean.deduPowerCurAccuAmt" value="${templateBean.deduPowerCurAccuAmt}" class="autoCalculate"/></td>
					</tr>	
					<tr>
						<td class="td_title">D3</td>
						<td class="td_title">扣代付的材料款、人工费</td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="deduMatePPriorAmt" name="templateBean.deduMatePPriorAmt" value="${templateBean.deduMatePPriorAmt}" /></td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="deduMatePCurAmt" name="templateBean.deduMatePCurAmt" value="${templateBean.deduMatePCurAmt}" /></td>
						<td><input class="inputBorder" type="text" readonly="readonly" id="deduMatePCurAccuAmt" name="templateBean.deduMatePCurAccuAmt" value="${templateBean.deduMatePCurAccuAmt}"  class="autoCalculate"/></td>
					</tr>	
					<tr>
						<td class="td_title">D4</td>
						<td class="td_title">工期、质量、安全违约金</td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="deduDeditPriorAmt" name="templateBean.deduDeditPriorAmt" value="${templateBean.deduDeditPriorAmt}" /></td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="deduDeditCurAmt" name="templateBean.deduDeditCurAmt" value="${templateBean.deduDeditCurAmt}"/></td>
						<td><input class="inputBorder" type="text" readonly="readonly" id="deduDeditCurAccuAmt" name="templateBean.deduDeditCurAccuAmt" value="${templateBean.deduDeditCurAccuAmt}"   class="autoCalculate"/></td>
					</tr>
					<tr>
						<td class="td_title">D5</td>
						<td class="td_title">其它</td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="deduOtherPriorAmt" name="templateBean.deduOtherPriorAmt" value="${templateBean.deduOtherPriorAmt}" /></td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="deduOtherCurAmt" name="templateBean.deduOtherCurAmt" value="${templateBean.deduOtherCurAmt}"/></td>
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
						<td colspan="3"  class="td_title">实际累计已付金额(元)</td>
						<td colspan="3"><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="totalAlreadyPayment" name="templateBean.totalAlreadyPayment" value="${templateBean.totalAlreadyPayment}" class="autoCalculate" style="text-align: center;"/></td>
					</tr>
					<tr>
						<td colspan="3"  class="td_title">发票金额(元):</td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="receiptAmoutLastPeriod" name="templateBean.receiptAmoutLastPeriod" value="${templateBean.receiptAmoutLastPeriod}" class="autoCalculate" /></td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="receiptAmoutCurPeriod" name="templateBean.receiptAmoutCurPeriod" value="${templateBean.receiptAmoutCurPeriod}" class="autoCalculate"/></td>
						<td><input class="inputBorder" type="text" readonly="readonly" id="receiptAmoutCurPeriodTotal" name="templateBean.receiptAmoutCurPeriodTotal" value="${templateBean.receiptAmoutCurPeriodTotal}" class="autoCalculate"/></td>
					</tr>	
					<tr>
						<td colspan="3" class="td_title">需说明事项:</td>
						<td colspan="3"><textarea  class="inputBorder contentTextArea" id="contentDesc" validate="required" name="templateBean.contentDesc">${templateBean.contentDesc}</textarea></td>
					</tr>
					<tr>
				<td class="td_title" rowspan="2">应提供细项<br/>(请作为附件上传)</td>
				<td style="height:40px;" colspan="2" validate="required" value="${templateBean.outputTotalFileId}">当月工程造价产值签证汇总表</td>
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
				<td style="height:40px;" colspan="2" validate="required" value="${templateBean.outputDetailFileId}">当月工程造价产值签证明细表</td>
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
			function sumEleFloat(){
				var i = 0, sum = 0 ;
				for(;i<arguments.length;i++ ){
					sum += getVal(arguments[i]);
				}
				return sum ;
			}

			function substractEleFloat(minuend,subtrahend){
				var i = 1 , result ;
				var minuend ; 
				var length = arguments.length;
				if(length==1){
					result = getVal(arguments[0]) ; 
				}else if(length==2){
					result = getVal(arguments[0]) - getVal(arguments[1]) ; 
				}else{
					result = getVal(arguments[0]);
					for(;i<arguments.length;i++ ){
						result = result - getVal(arguments[i]);
					}
				}
				return result ;
			}

			function computeTotalChanZhi() {// 产值
				$("#prodPriorSubtAmt").val(formatMoney(sumEleFloat("prodPriorIncludeAmt","prodPriorChangeAmt","prodPriorExtAmt"), 2)); //上期累计(总)
				$("#prodCurSubtAmt").val(formatMoney(sumEleFloat("prodCurIncludeAmt","prodCurChangeAmt","prodCurExtAmt"), 2)); // 本期发生(总)
				$("#prodCurIncludeAccuAmt").val(formatMoney(sumEleFloat("prodPriorIncludeAmt","prodCurIncludeAmt"), 2));  // A1 本期累计
				$("#prodCurChangeAccuAmt").val(formatMoney(sumEleFloat("prodPriorChangeAmt","prodCurChangeAmt"), 2));  // A2 本期累计
				$("#prodCurExtAccuAmt").val(formatMoney(sumEleFloat("prodPriorExtAmt","prodCurExtAmt"), 2));  // A3 本期累计
				$("#prodCurAccuAmt").val(formatMoney(sumEleFloat("prodPriorSubtAmt","prodCurSubtAmt"), 2));  //  本期累计(总)
			}

			function computeTotalJiaGong() {//甲供扣除
				$("#jgDeduPriorAmt").val(formatMoney(sumEleFloat("jgSteelPriorAmt","jgOtherPriorAmt"), 2)); //上期累计(总)
				$("#jgDeduCurAmt").val(formatMoney(sumEleFloat("jgSteelCurAmt","jgOtherCurAmt"), 2)); // 本期发生(总)
				$("#jgSteelCurAccuAmt").val(formatMoney(sumEleFloat("jgSteelPriorAmt","jgSteelCurAmt"), 2));  // B1 本期累计
				$("#jgOtherCurAccuAmt").val(formatMoney(sumEleFloat("jgOtherPriorAmt","jgOtherCurAmt"), 2));  // B2 本期累计
				$("#jgDeduCurAccuAmt").val(formatMoney(sumEleFloat("jgDeduPriorAmt","jgDeduCurAmt"), 2));  //  本期累计(总)
			}

			function computeTotalYuLiuKuanXiang() {  // 预留款项				
				var rate = getVal("convProcPaymentRate");
				$("#obliPriorProcAmt").val(formatMoney(substractEleFloat("prodPriorSubtAmt","jgDeduPriorAmt") * (100-rate) / 100 + "", 2)); //上期累计(总)
				$("#obliCurProcAmt").val(formatMoney(substractEleFloat("prodCurSubtAmt","jgDeduCurAmt") * (100-rate) / 100 + "", 2)); // 本期发生(总)
				$("#obliCurProcAccuAmt").val(formatMoney(substractEleFloat("prodCurAccuAmt","jgDeduCurAccuAmt") * (100-rate) / 100 + "", 2)); // 本期累计(总)
			}

			function computeTotalOther() { // 其他
				$("#jgDeduPriorAmt").val(formatMoney(sumEleFloat("jgSteelPriorAmt","jgOtherPriorAmt"), 2)); //上期累计(总)
				$("#jgDeduCurAmt").val(formatMoney(sumEleFloat("jgSteelCurAmt","jgOtherCurAmt"), 2)); // 本期发生(总)
				$("#deduLoanCurAccuAmt").val(formatMoney(sumEleFloat("deduLoanPriorAmt","deduLoanCurAmt"), 2));  // D1 本期累计
				$("#deduPowerCurAccuAmt").val(formatMoney(sumEleFloat("deduPowerPriorAmt","deduPowerCurAmt"), 2));  // D2 本期累计
				$("#deduMatePCurAccuAmt").val(formatMoney(sumEleFloat("deduMatePPriorAmt","deduMatePCurAmt"), 2));  // D3 本期累计
				$("#deduDeditCurAccuAmt").val(formatMoney(sumEleFloat("deduDeditPriorAmt","deduDeditCurAmt"), 2));  // D4 本期累计
				$("#deduOtherCurAccuAmt").val(formatMoney(sumEleFloat("deduOtherPriorAmt","deduOtherCurAmt"), 2));  // D5 本期累计
				$("#deduPriorSubtAmt").val(formatMoney(sumEleFloat("deduLoanPriorAmt","deduPowerPriorAmt","deduMatePPriorAmt","deduDeditPriorAmt","deduOtherPriorAmt"), 2));  // D 上期累计
				$("#deduCurSubtAmt").val(formatMoney(sumEleFloat("deduLoanCurAmt","deduPowerCurAmt","deduMatePCurAmt","deduDeditCurAmt","deduOtherCurAmt"), 2));  // D 本期发生
				$("#deduCurSubtAccuAmt").val(formatMoney(sumEleFloat("deduPriorSubtAmt","deduCurSubtAmt"), 2));  //  本期累计(总)
			}

			function computeTotalShiJiYingFu() { // 实际应付
				$("#realDealPriorAmt").val(formatMoney(substractEleFloat("prodPriorSubtAmt","jgDeduPriorAmt","obliPriorProcAmt","deduPriorSubtAmt"), 2)); // 上期累计(总)
				$("#realDealCurAmt").val(formatMoney(substractEleFloat("prodCurSubtAmt","jgDeduCurAmt","obliCurProcAmt","deduCurSubtAmt"), 2)); // 本期发生(总)
				$("#realDealCurAccuAmt").val(formatMoney(substractEleFloat("prodCurAccuAmt","jgDeduCurAccuAmt","obliCurProcAccuAmt","deduCurSubtAccuAmt"), 2)); //  本期累计(总)
			}

			function computePaymentPercent() { // 产值付款比例
				//(累计其他扣除+累计付款)/(累计产值-累计甲供材扣除)
				//(deduCurSubtAccuAmt+totalAlreadyPayment)/(prodCurAccuAmt-jgDeduCurAccuAmt)
				var dividend = substractEleFloat("prodCurAccuAmt","jgDeduCurAccuAmt");
				if (dividend === 0 ) {
					$("#outputValueRate").val("");
					return;
				}else{
					$("#outputValueRate").val(formatMoney(((sumEleFloat("totalAlreadyPayment","deduCurSubtAccuAmt"))/dividend * 100), 2) + "%");
				}
			}

			function computeInvoice() { // invoice count
				$("#receiptAmoutCurPeriodTotal").val(formatMoney(sumEleFloat("receiptAmoutLastPeriod","receiptAmoutCurPeriod"), 2));  // 本期累计
			}

			function computeAll(){
				computeTotalChanZhi();	
				computeTotalJiaGong();
				computeTotalYuLiuKuanXiang();
				computeTotalOther();
				computeTotalShiJiYingFu();
				computePaymentPercent();
				computeInvoice();
			}
			(function(){
				// bind calculate function
				var inputEleList=[
					'footingsAmt','curPaymentAmt','prodPriorIncludeAmt','prodCurIncludeAmt',
					'prodPriorChangeAmt','prodCurChangeAmt','prodPriorExtAmt','prodCurExtAmt','jgSteelPriorAmt',
					'jgSteelCurAmt','jgOtherPriorAmt','jgOtherCurAmt','deduLoanPriorAmt','deduLoanCurAmt',
					'deduPowerPriorAmt','deduPowerCurAmt','deduMatePPriorAmt','deduMatePCurAmt','deduDeditPriorAmt',
					'deduDeditCurAmt','deduOtherPriorAmt','deduOtherCurAmt','receiptAmoutLastPeriod','receiptAmoutCurPeriod'
				  ];
				
				 $.each(inputEleList,function(index,value){
				 		$("#"+value).bind ("blur",computeAll);
				 		//$("#"+value).bind ("blur",formatValById(value));
						});
				 
				 // bind calculate function
				 $("#convProcPaymentRate").bind ("blur",computeTotalYuLiuKuanXiang);	
				 $("#convProcPaymentRate").bind ("blur",computeTotalShiJiYingFu);				
				// bind format rate function
				 $("#convProcPaymentRate").bind ("blur",function(){formatRate("convProcPaymentRate");});

				// bind format value function
				var valueEleList = ['contractAmt','preLoanBalanceAmt','footingsAmt','curPaymentAmt','totalAlreadyPayment'];
				$.each(valueEleList,function(index,value){
					//$("#"+value).bind ("blur",formatValById(value));
				});

			})();
			
		</script>	

