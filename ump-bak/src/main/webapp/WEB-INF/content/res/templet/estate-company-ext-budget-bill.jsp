<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 地产公司月预算外资金申请表-->
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" >
		<%@ include file="template-var.jsp"%>
		
		<table  class="mainTable">
			<col width="120"/>
			<col/>
			<col width="100"/>
			<col />
			<tr>
				<td class="td_title">收款单位</td>
				<td colspan="3"><input class="inputBorder" type="text" validate="required" name="templateBean.payee" value="${templateBean.payee}"  /></td>
			</tr>
			<tr>
				<td class="td_title">公司名称</td>
				<td>
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
				<td class="td_title">预计支付日期</td>
				<td><input onfocus="WdatePicker()" class="Wdate" type="text" validate="required" name="templateBean.planPayDate" value="${templateBean.planPayDate}"  /></td>
			</tr>
			<tr>
				<td class="td_title">合同总金额(元)</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.contactTotalAmt" value="${templateBean.contactTotalAmt}"/></td>
			</tr>	
			<tr>
				<td class="td_title">合同已付金额(元)</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.contactPayedAmd" value="${templateBean.contactPayedAmd}"/></td>
			</tr>	
			<tr>
				<td class="td_title">申请支付金额(元)</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.applyPayAmt" value="${templateBean.applyPayAmt}"/></td>
			</tr>	
			<tr>
				<td class="td_title">批准支付金额(元)</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.approvePayAmt" value="${templateBean.approvePayAmt}"/></td>
			</tr>	
			<tr>
				<td class="td_title"> 资金用途及支付理由<br/>(地产公司填写)</td>
				<td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.useDesc">${templateBean.useDesc}</textarea></td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>

<%@ include file="template-footer.jsp"%>
