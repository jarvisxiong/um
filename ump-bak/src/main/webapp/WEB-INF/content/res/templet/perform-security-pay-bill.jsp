<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--履约保证金支付审批表-->
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
		<table  class="mainTable" >
			<col width="30px"/>
			<col width="100px"/>
			<col />
			<col width="120px"/>
			<col />
			<tr>
			<td class="td_title">审批权限</td>
			<td align="left" colspan="4">
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
				<td colspan="2" class="td_title">公司名称</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.companyName" value="${templateBean.companyName}"  /></td>
				<td class="td_title">项目名称</td>
				<td>
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
			</tr>	
			<tr>
				<td colspan="2" class="td_title">合同编号</td>
				<td align="left">
				
				
				<input type="hidden" id="contLedgerId"  />
					<s:if test="!templateBean.contractNo.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getContByno('${templateBean.contractNo}');">${templateBean.contractNo}</span>
						<input id="contactNoId" type="hidden" name="templateBean.contractNo" value="${templateBean.contractNo}" />
					</s:if>
					<s:else>
					<input class="inputBorder" validate="required" type="text" id="contactNoId"  name="templateBean.contractNo" value="${templateBean.contractNo}"/>
					</s:else>
				</td>
				<td class="td_title">合同名称:</td>
				<td><input class="inputBorder" validate="required" type="text" readonly="readonly" id="contactNameId" name="templateBean.contractName" value="${templateBean.contractName}" /></td>
			</tr>
			<tr>
				<td colspan="2" class="td_title">履约保证金起始时间</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.guaranteeMoneyStartDate" value="${templateBean.guaranteeMoneyStartDate}"  /></td>
			</tr>
			<tr>
				<td colspan="2" class="td_title">保证金总额 (元)</td>
				<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.totalGuaranteeAmt" value="${templateBean.totalGuaranteeAmt}"  /></td>
				<td class="td_title">已返还保证金 (元)</td>
				<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.returnedGuranteeAmt" value="${templateBean.returnedGuranteeAmt}"  /></td>
			</tr>
			<tr>
				<td colspan="2" class="td_title">本次支付金额(元)</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.curPaymentAmt" value="${templateBean.curPaymentAmt}"/></td>
			</tr>	
			<tr>
				<td rowspan="3" class="td_title" valign="middle">收款人信息</td>
				<td class="td_title">收款人名</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.payer" value="${templateBean.payer}"/></td>
			</tr>
			<tr>
				<td class="td_title">收款人开户银行</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.payerAccount" value="${templateBean.payerAccount}"/></td>
			</tr>
			<tr>
				<td class="td_title">收款人账户号</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.payerBank" value="${templateBean.payerBank}"/></td>
			</tr>
			<tr>
				<td colspan="2" class="td_title">需说明事项</td><td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentDesc">${templateBean.contentDesc}</textarea></td>
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
</script>