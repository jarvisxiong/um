<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 工程、材料设备质保金付款审批表 --%>
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
			<td align="left">
				<s:checkbox name="templateBean.hotel"  cssClass="group"></s:checkbox>与酒店有关
			</td>
			<td align="left" colspan="2">
			<s:checkbox name="templateBean.hasEstate" value="true" cssClass="group"></s:checkbox>有商业公司
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
				<td class="td_title">合同总价</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="totalPrice" name="templateBean.totalPrice" value="${templateBean.totalPrice}" />
				</td>
				<td class="td_title">结算总价</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="clearPrice" name="templateBean.clearPrice" value="${templateBean.clearPrice}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">本次应付质保金(元)</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.marginPay" value="${templateBean.marginPay}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: center;font-weight: bold;">计算公式</td>
			</tr>
			<tr>
				<td class="td_title">实际预留质保金</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.fuRealGuarantee" value="${templateBean.fuRealGuarantee}"  onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">扣：保修应扣款</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.fuCutPayment" value="${templateBean.fuCutPayment}"  onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">扣：预留保修金</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.fuLeaveGuarantee" value="${templateBean.fuLeaveGuarantee}"  onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">本次应付质保金</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.fuPayMoeny" value="${templateBean.fuPayMoeny}"  onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">需说明事项</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" name="templateBean.description">${templateBean.description}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="2">附件上传</td>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.mateSettleId}">工程或材料设备结算款付款审批表</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('工程或材料设备结算款付款审批表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','mateSettleId',event);"/>
				<input type="hidden" name="templateBean.mateSettleId" id="mateSettleId" value="${templateBean.mateSettleId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_mateSettleId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.mateSettleId}','mateSettleId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.settleApproveId}">结算审批表</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('结算审批表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','settleApproveId',event);"/>
				<input type="hidden" name="templateBean.settleApproveId" id="settleApproveId" value="${templateBean.settleApproveId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_settleApproveId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.settleApproveId}','settleApproveId','${canEdit}');
				</script>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script>
function getCont(id){
	var url="${ctx}/cont/cont-ledger!input.action?id="+id;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cont-ledger-input","合同台账查看",url,true);
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
		guarBeginDate:'guarBeginDate',
		guarEndDate:'guarEndDate',
		totalPrice:'totalPrice',
		clearPrice:'clearPrice'
	};
	$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama);
</script>
</s:if>
