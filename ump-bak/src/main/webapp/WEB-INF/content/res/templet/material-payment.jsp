<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 材料设备款付款审批表 --%>
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
		<s:set var="conItemCount"><s:property value="templateBean.otherProperties.size()"/></s:set>
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
					&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="templateBean.inFlag"  cssClass="group"></s:checkbox>预算内
					&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="templateBean.outFlag"  cssClass="group"></s:checkbox>预算外
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
				<input class="inputBorder" type="text" id="partB" name="templateBean.partB" value="${templateBean.partB}" />
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
				<td class="td_title">本次付款申请金额(元)</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.applyAmount" value="${templateBean.applyAmount}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: center;font-weight: bold;">
				付款计算
				<s:if test="statusCd==0 || statusCd==3">
				<input type="button"  class="btn_green_65_20" style="border:none;"  value="增加单位" onclick="addItem();" />
				</s:if>
				</td>
			</tr>
			<tr>
				<td colspan="4" style="padding-right: 0px; padding-left: 0px; ">
				<table id="tbConItem" border="1px solid #8c8f94;" cellpadding="0" cellspacing="0" frame="void" style="width: 100%; border-collapse:collapse;" >
					<col width="300"/>
					<col/>
					<col/>
					<col/>
					<col width="40"/>
					<tr>
						<td ></td>
						<td class="td_title">已累计</td>
						<td class="td_title">本次</td>
						<td class="td_title">小计</td>
						<td class="td_title">操作</td>
					</tr>
					<tr>
						<td class="td_title">已供货合价</td>
						<td>
						<input class="inputBorder" readonly="readonly" type="text" id="completeNumBefore" name="templateBean.completeNumBefore" value="${templateBean.completeNumBefore}" />
						</td>
						<td>
						<input class="inputBorder" validate="required" type="text" id="completeNumThis" name="templateBean.completeNumThis" value="${templateBean.completeNumThis}" onblur="formatVal($(this));" onchange="onchange_countRatioAndTotal();" />
						</td>
						<td >
						<input class="inputBorder" readonly="readonly" type="text" id="completeNumTotal" name="templateBean.completeNumTotal" value="${templateBean.completeNumTotal}" />
						</td>
						<td ></td>
					</tr>
					<tr>
						<td class="td_title">付款</td>
						<td>
						<input class="inputBorder" readonly="readonly" type="text" id="currentPayBefore" name="templateBean.currentPayBefore" value="${templateBean.currentPayBefore}" />
						</td>
						<td>
						<input class="inputBorder" validate="required" type="text" id="currentPayThis" name="templateBean.currentPayThis" value="${templateBean.currentPayThis}" onblur="formatVal($(this));" onchange="onchange_countRatioAndTotal();"/>
						</td>
						<td >
						<input class="inputBorder" readonly="readonly" type="text" id="currentPayTotal" name="templateBean.currentPayTotal" value="${templateBean.currentPayTotal}" />
						</td>
						<td ></td>
					</tr>
					<tr>
						<td class="td_title">付款比例%</td>
						<td>
						<input class="inputBorder" readonly="readonly" type="text" id="payRateBefore" name="templateBean.payRateBefore" value="${templateBean.payRateBefore}" />
						</td>
						<td>
						<input class="inputBorder" readonly="readonly" type="text" id="payRateThis" name="templateBean.payRateThis" value="${templateBean.payRateThis}" />
						</td>
						<td >
						<input class="inputBorder" readonly="readonly" type="text" id="payRateTotal" name="templateBean.payRateTotal" value="${templateBean.payRateTotal}" />
						</td>
						<td ></td>
					</tr>
					
					<s:if test="statusCd==0 || statusCd==3">
					<tr id="trConItem" style="display: none;margin-bottom:5px;">
						<td>
						<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
							<col width="10"/>
							<col/>
							<col width="102"/>
							<tr>
							<td >(</td>
							<td>
							<input class="inputBorder"  type="text" name="templateBean.otherProperties[0].constructionUnit" />
							</td>
							<td >)施工单位领货合价</td>
							</tr>
						</table>
						</td>
						<td><input class="inputBorder"  type="text" id="receiveAmountBefore_0" name="templateBean.otherProperties[0].receiveAmountBefore" onblur="formatVal($(this));" onchange="onchange_countTotal(this);"/></td>
						<td><input class="inputBorder"  type="text" id="receiveAmountThis_0" name="templateBean.otherProperties[0].receiveAmountThis" onblur="formatVal($(this));" onchange="onchange_countTotal(this);"/></td>
						<td><input class="inputBorder" readonly="readonly" type="text" id="receiveAmountTotal_0" name="templateBean.otherProperties[0].receiveAmountTotal"/></td>
						<td width="15px" align="center"><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a></td>
					</tr>
					</s:if>
					<s:iterator value="templateBean.otherProperties" var="item" status="s">
					<tr style="margin-bottom:5px;">
						<td>
						<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
							<col width="10"/>
							<col/>
							<col width="102"/>
							<tr>
							<td >(</td>
							<td>
							<input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].constructionUnit" value="${item.constructionUnit}" />
							</td>
							<td >)施工单位领货合价</td>
							</tr>
						</table>
						</td>
						<td><input class="inputBorder"  type="text" id="receiveAmountBefore_<s:property value="#s.index" />" name="templateBean.otherProperties[<s:property value="#s.index" />].receiveAmountBefore" value="${item.receiveAmountBefore}" onblur="formatVal($(this));" onchange="onchange_countTotal(this);"/></td>
						<td><input class="inputBorder"  type="text" id="receiveAmountThis_<s:property value="#s.index" />" name="templateBean.otherProperties[<s:property value="#s.index" />].receiveAmountThis" value="${item.receiveAmountThis}" onblur="formatVal($(this));" onchange="onchange_countTotal(this);"/></td>
						<td><input class="inputBorder" readonly="readonly" type="text" id="receiveAmountTotal_<s:property value="#s.index" />" name="templateBean.otherProperties[<s:property value="#s.index" />].receiveAmountTotal" value="${item.receiveAmountTotal}" /></td>
						<td width="15px" align="center">
						<s:if test="#canEdit=='true'">
						<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
						</s:if>
						</td>
					</tr>
					</s:iterator>
				</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">需说明事项</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.description">${templateBean.description}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="2">附件上传</td>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.provideDetailId}">供货、领货明细表</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('供货、领货明细表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','provideDetailId',event);"/>
				<input type="hidden" name="templateBean.provideDetailId" id="provideDetailId" value="${templateBean.provideDetailId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_provideDetailId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.provideDetailId}','provideDetailId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.threeSideAcceptCertificateId}">三方验收凭证</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('三方验收凭证','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','threeSideAcceptCertificateId',event);"/>
				<input type="hidden" name="templateBean.threeSideAcceptCertificateId" id="threeSideAcceptCertificateId" value="${templateBean.threeSideAcceptCertificateId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_threeSideAcceptCertificateId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.threeSideAcceptCertificateId}','threeSideAcceptCertificateId','${canEdit}');
				</script>
				</td>
			</tr> 
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
	var trApprover=$("#trConItem").clone();
	$("#trConItem").remove();
	var cloneCount = 0;
	var index=${conItemCount};
	
	function addItem(){
		var trApprover_new=trApprover.clone();
		cloneCount++;
		$(trApprover_new).show();
		$(trApprover_new).find(".inputBorder").each(function(i){
			this.name=this.name.replace('0',index);
			this.id=this.id.replace('0',index);
		});
		//$(trApprover_new).find("#cloneUserCds").attr("id",cloneCount+"cloneUserCds");
		$("#tbConItem").append(trApprover_new);
		index++;
	}
	function delItem(dom){
		$(dom).parent().parent().remove();
	}
</script>
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
</script>
</s:if>
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
		var completeNumThis = getVal("completeNumThis");
		var currentPayBefore = getVal("currentPayBefore");
		var currentPayThis = getVal("currentPayThis");
		var completeNumTotal = completeNumBefore+completeNumThis;
		var currentPayTotal = currentPayBefore+currentPayThis;
		$("#completeNumTotal").val(formatMoney(completeNumTotal,2));
		$("#currentPayTotal").val(formatMoney(currentPayTotal,2));
		if(completeNumThis != 0) {
			var payRateThis = currentPayThis/completeNumThis*100;
			$("#payRateThis").val(formatMoney(payRateThis,2));
		}
		if(completeNumTotal != 0) {
			var payRateTotal = currentPayTotal/completeNumTotal*100;
			$("#payRateTotal").val(formatMoney(payRateTotal,2));
		}
	}
	
	function countRateBefore() {
		var completeNumBefore = getVal("completeNumBefore");
		var currentPayBefore = getVal("currentPayBefore");
		if(completeNumBefore != 0) {
			var payRateBefore = currentPayBefore/completeNumBefore*100;
			$("#payRateBefore").val(formatMoney(payRateBefore,2));
		}
		
		onchange_countRatioAndTotal();
	}
	
	function onchange_countTotal(obj) {
		
		var index = obj.id.charAt(obj.id.length-1);
		var beforeId = "receiveAmountBefore_"+index;
		var thisId = "receiveAmountThis_"+index;
		var totalId = "receiveAmountTotal_"+index;
		var receiveAmountTotal = getVal(beforeId) + getVal(thisId);
		$("#"+totalId).val(formatMoney(receiveAmountTotal,2));
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
		currentPay:'currentPayBefore'
	};
	$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama,{},countRateBefore);
	
</script>
</s:if>
