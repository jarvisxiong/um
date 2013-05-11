<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 商业付款审批表(eg: 商业公司工程改造付款审批表) --%>
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
			<col width="150px"/>
			<col width="90px"/>
			<col width="60px"/>
			<col width="140px"/>
			<col/>
			<tr>
				<td class="td_title"></td>
				<td colspan="4" validate="required"  class="chkGroup">
					<table class="tb_checkbox">
					<col width="150">
					<col width="150">
					<tr>
						<td><s:checkbox name="templateBean.inFlag" id="inFlag" cssClass="group"></s:checkbox>月度预算内</td>
						<td><s:checkbox name="templateBean.outFlag" id="outFlag" cssClass="group"></s:checkbox>月度预算外</td>
					</tr>
					</table>
				</td>
			</tr>
			<s:if test="authTypeCd=='BLSY_CWGL_FKGL_60'">
			<tr>
				<td class="td_title"></td>
				<td colspan="4" class="chkGroup">
					<table class="tb_checkbox">
					<col width="150">
					<tr>
						<td><s:checkbox name="templateBean.hasLandCompany" id="inFlag" cssClass="group"></s:checkbox>有地产公司</td>
					</tr>
					</table>
				</td>
			</tr>
			</s:if>
			<tr>
				<input type="hidden" id="resApproveInfoId" name="templateBean.resApproveInfo.resApproveInfoId" value="${templateBean.resApproveInfo.resApproveInfoId }"/>
				<input type="hidden" id="resAuthTypeId" name="templateBean.resAuthTypeId" value="${resAuthType.resAuthTypeId}"/> 
				<input type="hidden" id="displayName" name="templateBean.displayName" value="${resAuthType.displayName}"/>
				<input type="hidden" id="contLedgerId" name="templateBean.contLedgerId" value="${templateBean.contLedgerId}"/>
				
				<td style="border-top:0 none;" class="td_title">合同编号</td>
				<td style="border-top: none;" colspan="2" align="left">
					<input type="hidden" id="contLedgerId"  />
					<s:if test="!templateBean.contactNo.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getContByno('${templateBean.contactNo}');">${templateBean.contactNo}</span>
						<input id="contactNoId" type="hidden" name="templateBean.contactNo" value="${templateBean.contactNo}" />
					</s:if>
					<s:else>
					<input class="inputBorder" validate="required" id="contactNoId" type="text" name="templateBean.contactNo" value="${templateBean.contactNo}" />
					</s:else>
				</td>
				<td style="border-top:0 none;" class="td_title">合同名称	</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" readonly="readonly" id="contactNameId" type="text" name="templateBean.contactName" value="${templateBean.contactName}" />
				</td> 
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">企划案例编号</td>
				<td style="border-top: none;" colspan="2" align="left">
					<input type="hidden" id="pmMateEntryId" name="templateBean.pmMateEntryId" value="${templateBean.pmMateEntryId }"/>
					<input class="inputBorder" validate="required" id="pmMateEntryNo" type="text" name="templateBean.pmMateEntryNo" value="${templateBean.pmMateEntryNo}" />
				</td>
				<td style="border-top:0 none;" class="td_title">活动主题</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" readonly="readonly" id="activeTitle" type="text" name="templateBean.activeTitle" value="${templateBean.activeTitle}" />
				</td> 
			</tr>
			<tr>
				<td style="border-top:0 none;" class="td_title">时间周期</td>
				<td style="border-top: none;" colspan="2" align="left">
					<input class="inputBorder" validate="required" readonly="readonly" id="activePeriod" type="text" name="templateBean.activePeriod" value="${templateBean.activePeriod}" />
				</td>
				<td style="border-top:0 none;" class="td_title">预算费用</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" readonly="readonly" id="expensesBudget" type="text" name="templateBean.expensesBudget" value="${templateBean.expensesBudget}" />
				</td> 
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">项目名称</td>
				<td style="border-top: none;" colspan="4">
					<input class="inputBorder" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" />
					<input type="hidden" name="templateBean.projectCd" value="${templateBean.projectCd}" />
				</td> 
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">付款单位</td>
				<td style="border-top: none;" colspan="4">
					<input class="inputBorder" validate="required" type="text" name="templateBean.payUnitName" value="${templateBean.payUnitName}" />
					<input type="hidden" name="templateBean.payUnitCd" value="${templateBean.payUnitCd}" />
				</td> 
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title" rowspan="4">收款人（乙方）信息</td>
				<td style="border-top:0 none;" class="td_title">收款人名称</td>
				<td style="border-top: none;" colspan="3">
					<input class="inputBorder" readonly="readonly" type="text" id="receName" name="templateBean.receName" value="${templateBean.receName}" />
				</td> 
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">收款人开户行</td>
				<td style="border-top: none;" colspan="3">
					<input class="inputBorder" validate="required" type="text" name="templateBean.receOpenBankName" value="${templateBean.receOpenBankName}" />
				</td> 
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">收款人账号</td>
				<td style="border-top: none;" colspan="3">
					<input class="inputBorder" validate="required" type="text" name="templateBean.receAcctNo" value="${templateBean.receAcctNo}" />
				</td> 
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">付款方式</td>
				<td style="border-top: none;" colspan="3">
					<input class="inputBorder" readonly="readonly" type="text" id="payTypeDesc" name="templateBean.payTypeDesc" value="${templateBean.payTypeDesc}" />
				</td> 
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">合同总价（元）</td>
				<td style="border-top:0 none;" colspan="2">
					<input class="inputBorder" readonly="readonly" type="text" id="contractTotalAmt" name="templateBean.contractTotalAmt" value="${templateBean.contractTotalAmt}"  onblur="formatVal($(this));" />
				</td>
				<td style="border-top:0 none;" class="td_title">已确认合同总价（元）</td>
				<td style="border-top:0 none;">
					<input class="inputBorder" readonly="readonly" type="text" id="confirmTotalAmt" name="templateBean.confirmTotalAmt" value="${templateBean.confirmTotalAmt}"  onblur="formatVal($(this));" />
				</td> 
			</tr>  
			<tr>
				<td style="border-top:0 none;" class="td_title">已付合同款（元）</td>
				<td style="border-top:0 none;" colspan="4">
					<input class="inputBorder" validate="required" type="text" name="templateBean.paidTotalAmt" value="${templateBean.paidTotalAmt}"  onblur="formatVal($(this));" />
				</td>
			</tr>  
			<tr>
				<td style="border-top:0 none;" class="td_title">本次付款申请金额（元）</td>
				<td style="border-top:0 none;" colspan="4">
					<input class="inputBorder" validate="required" type="text" name="templateBean.applyAmt" value="${templateBean.applyAmt}"  onblur="formatVal($(this));" />
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
var mapPrama={
		contLedgerId:'contLedgerId',
		contNo:'contactNoId',
		contName:'contactNameId',
		partB:'receName',
		payWay:'payTypeDesc',
		totalPrice:'contractTotalAmt',
		updateTotal:'confirmTotalAmt'
	};
	$("#contactNoId").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama,{codeType:'2'});

var mapPramaNo={
		pmMateEntryId:'pmMateEntryId',
		pmMateEntryNo:'pmMateEntryNo',
		activeTitle:'activeTitle',
		activePeriod:'activePeriod',
		expensesBudget:'expensesBudget'
	};
	$("#pmMateEntryNo").quickSearch("${ctx}/pm/pm-mate-entry!quickSearchMateEntry.action",['pmMateEntryNo','activeTitle','activePeriod','expensesBudget'],mapPramaNo);
</script>
