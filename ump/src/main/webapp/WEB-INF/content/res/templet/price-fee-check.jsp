<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 工程造价咨询单项委托单审批表 --%>
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
		<table  class="mainTable">
			<col width="110"/>
			<col/>
			<col width="125"/>
			<col/>
			<tr>
				<td class="td_title">合同编号</td>
				<td align="left">
				
				<input type="hidden" id="contLedgerId" name="templateBean.contLedgerId" value="${templateBean.contLedgerId}"/>
				
				<s:if test="!templateBean.contLedgerId.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getCont('${templateBean.contLedgerId}');">${templateBean.contNo}</span>
						<input id="contNo" type="hidden" name="templateBean.contNo" value="${templateBean.contNo}" />
					</s:if>
					<s:else>
					<input class="inputBorder" validate="required" validate="required" type="text" id="contNo" name="templateBean.contNo" value="${templateBean.contNo}"/>
					</s:else>
				</td>
				<td>委托单编号</td>
				<td align="left">
				
				<input type="hidden" id="contVisaUpdateId" name="templateBean.contVisaUpdateId" value="${templateBean.contVisaUpdateId}"/>
				
				<s:if test="!templateBean.contVisaUpdateId.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getCont('${templateBean.contVisaUpdateId}');">${templateBean.visaNo}</span>
						<input id="visaNo" type="hidden" name="templateBean.visaNo" value="${templateBean.visaNo}" />
					</s:if>
					<s:else>
					<input class="inputBorder" type="text" id="visaNo" validate="required" name="templateBean.visaNo" value="${templateBean.visaNo}"/>
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="td_title">合同名称</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" validate="required" id="contName" name="templateBean.contName" value="${templateBean.contName}"/>
				</td>
				<td class="td_title">乙方</td>
				<td>
				<input class="inputBorder" id="partB" readonly="readonly" validate="required" type="text" name="templateBean.partB" value="${templateBean.partB}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">项目名称</td>
				<td>
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col/>
						<col width="20"/>
						<col width="40"/>
						<col width="20"/>
						<tr>
						<td>
						<input class="inputBorder" id="projectName" readonly="readonly" type="text" name="templateBean.projectName" value="${templateBean.projectName}"/>
						<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
						</td>
						<td style="text-align: right;">(</td>
						<td>
						<input class="inputBorder" id="projectPeriod" type="text" name="templateBean.projectPeriod" value="${templateBean.projectPeriod}" />
						</td>
						<td>)期</td>
						</tr>
					</table>
				</td>
				<td class="td_title">面积(m2)</td>
				<td>
					<input class="inputBorder" validate="required" validate="required" type="text" id="area" readonly="readonly" name="templateBean.area" value="${templateBean.area}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">甲方委托部门</td>
				<td>
					<input class="inputBorder" id="partATrustPart" validate="required" readonly="readonly" type="text" name="templateBean.partATrustPart" value="${templateBean.partATrustPart}"/>
				</td>
				<td class="td_title">甲方经办人</td>
				<td>
					<input class="inputBorder" id="partAOperator" validate="required" readonly="readonly" type="text" name="templateBean.partAOperator" value="${templateBean.partAOperator}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">咨询费用计算规则</td>
				<td>
				<input class="inputBorder" type="text" name="templateBean.consultFeeRule" value="${templateBean.consultFeeRule}"/>
				</td>
				<td class="td_title">咨询费比例</td>
				<td>
				<input class="inputBorder" readonly="readonly" validate="required" type="text" id="consultRate" name="templateBean.consultRate" value="${templateBean.consultRate}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">委托事项</td>
				<td>
				 <table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<tr>
						<td>
						<input class="inputBorder" type="text" name="templateBean.trustBidItem" value="${templateBean.trustBidItem}" style="width:50%;"/>
						工程招标造价咨询
						</td>
						</tr>
						<tr>
						<td>
						<input class="inputBorder" type="text" name="templateBean.settleItem" value="${templateBean.settleItem}" style="width:50%;"/>
						工程结算造价咨询
						</td>
						</tr>
					</table>
				</td>
				<td class="td_title">核定咨询费(元)</td>
				<td>
				<input class="inputBorder" type="text" name="templateBean.consultFee" validate="required" value="${templateBean.consultFee}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
			  <td>咨询内容</td>
			  <td colspan="3" class="chkGroup">
			   <table class="tb_checkbox">
			        <col width="100"/>
				    <col width="100"/>
				    <col width="120"/>
				    <col width="100"/>
				    <col/>
					<tr>
					<td><s:checkbox name="templateBean.braidList" cssClass="group"></s:checkbox>编制订单</td>
					<td><s:checkbox name="templateBean.braidBaseBidPrice" cssClass="group"></s:checkbox>编制标底</td>
					<td><s:checkbox name="templateBean.rePriceCheck" cssClass="group"></s:checkbox>重新度量标后核对</td>
					<td><s:checkbox name="templateBean.projectSettle" cssClass="group"></s:checkbox>工程结算</td>
					<td><s:checkbox name="templateBean.braidOther" cssClass="group"></s:checkbox>其他</td>
					</tr>
				</table>
			  </td>
			</tr>
			<tr>
			 <td>标底预算价(元)</td>
			 <td><input class="inputBorder" type="text" name="templateBean.baseBidPriceBudget" value="${templateBean.baseBidPriceBudget}" onblur="formatVal($(this));"/>
			 </td>
			 <td>重新度量施工图预算价(元)</td>
			 <td>
			 <input class="inputBorder" type="text" name="templateBean.reDrawingBudget" value="${templateBean.reDrawingBudget}" onblur="formatVal($(this));"/>
			 </td>
			</tr>
			<tr>
			 <td>一</td>
			 <td>工程造价基数(=1或2+3-4+5)</td>
			 <td colspan="2"></td>
			</tr>
			<tr>
			 <td>1</td>
			 <td>定标价(适用总价包干定标)</td>
			 <td colspan="2">
			 <input class="inputBorder" type="text" name="templateBean.bidApprovalPrice" value="${templateBean.bidApprovalPrice}" onblur="formatVal($(this));"/>
			 </td>
			</tr>
			<tr>
			 <td>2</td>
			 <td>标后核对批准价(适用模拟清单、费率定标)</td>
			  <td colspan="2">
			  <input class="inputBorder" type="text" name="templateBean.afterCheckApprove" value="${templateBean.afterCheckApprove}" onblur="formatVal($(this));"/>
			  </td>
			</tr>
			<tr>
			 <td>3</td>
			 <td>应计入工程造价的电缆、面砖、石材的价格</td>
			  <td colspan="2">
			  <input class="inputBorder" type="text" name="templateBean.projectPrice" value="${templateBean.projectPrice}" onblur="formatVal($(this));"/>
			  </td>
			</tr>
			<tr>
			 <td>4</td>
			 <td>应扣除套图部分造价</td>
			  <td colspan="2">
			  <input class="inputBorder" type="text" name="templateBean.cutDrawingPrice" value="${templateBean.cutDrawingPrice}" onblur="formatVal($(this));"/>
			  </td>
			</tr>
			<tr>
			 <td>5</td>
			 <td>其他工程造价加减额</td>
			  <td colspan="2">
			  <input class="inputBorder" type="text" name="templateBean.otherProjectPrice" value="${templateBean.otherProjectPrice}" onblur="formatVal($(this));"/>
			  </td>
			</tr>
			<tr>
			 <td>二</td>
			 <td>咨询费(=6+7)</td>
			 <td colspan="2"></td>
			</tr>
			<tr>
			 <td>6</td>
			 <td>标准咨询费(=工程造价基数*咨询费比例)</td>
			 <td colspan="2">
			 <input class="inputBorder" type="text" name="templateBean.standardConsultFee" value="${templateBean.standardConsultFee}" onblur="formatVal($(this));"/>
			 </td>
			</tr>
			<tr>
			 <td>7</td>
			 <td>加减咨询费</td>
			 <td colspan="2">
			 <input class="inputBorder" type="text" name="templateBean.addSubFee" value="${templateBean.addSubFee}" onblur="formatVal($(this));"/>
			 </td>
			</tr>
			<tr>
			 <td>备注</td>
			 <td colspan="3"></td>
			</tr>
			<tr>
			 <td rowspan="3">上传附件</td>
			 <td>单项委托单</td>
			 <td>
			   <input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('核对的图纸清单目录','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','singleTrustId',event);"/>
				<input id="singleTrustId" type="hidden" name="templateBean.singleTrustId" value="${templateBean.singleTrustId}"/>
             </td>
             <td>
              <div id="show_singleTrustId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.singleTrustId}','singleTrustId','${canEdit}');
				</script>
             </td>
			</tr>
			<tr>
			 <td>定标单或标后核对核定单</td>
			 <td>
			   <input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('核对的图纸清单目录','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','bidApprovalId',event);"/>
				<input id="bidApprovalId" type="hidden" name="templateBean.bidApprovalId" value="${templateBean.bidApprovalId}"/>
             </td>
             <td>
              <div id="show_bidApprovalId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.bidApprovalId}','bidApprovalId','${canEdit}');
				</script>
             </td>
			</tr>
			<tr>
			 <td>与咨询机构初审意见(内容同上)</td>
			 <td>
			   <input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('核对的图纸清单目录','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','firstTrialId',event);"/>
				<input id="firstTrialId" type="hidden" name="templateBean.firstTrialId" value="${templateBean.firstTrialId}"/>
             </td>
             <td>
              <div id="show_firstTrialId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.firstTrialId}','firstTrialId','${canEdit}');
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
var mapPrama={
		contLedgerId:'contLedgerId',
		contNo:'contNo',
		contName:'contName',
		projectName:'projectName',
		partB:'partB'
	};
	$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama);
var visaParma={
		visaNo:'visaNo',
		area:'area',
		partATrustPart:'partATrustPart',
		partAOperator:'partAOperator',
		consultRate:'consultRate',
		visaNoId:'contVisaUpdateId'
};
$("#visaNo").quickSearch("${ctx}/cont/cont-ledger!searchVisaById.action",['visaContent','partATrustPart'],visaParma,{},false,{ledgerId:'contLedgerId'});
</script>