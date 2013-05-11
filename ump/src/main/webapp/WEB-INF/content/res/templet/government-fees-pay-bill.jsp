<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--政府规费付款审批表-->
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" >
		<%@ include file="template-var.jsp"%>
		
		<table  class="mainTable">
			<col width="30px"/>
			<col width="100px"/>
			<col />
			<col width="120px"/>
			<col />
			<tr>
				<td colspan="2" class="td_title">公司名称</td>
				<td><input class="inputBorder" type="text" validate="required" name="templateBean.companyName" value="${templateBean.companyName}"  /></td>
				<td  class="td_title">项目名称</td>
				<td>
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
			</tr>
			<tr>
				<td colspan="2"  class="td_title">规费内容</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.feeContent" value="${templateBean.feeContent}" /></td>
				<td  class="td_title">办理何种工程手续</td>
				<td><input  class="inputBorder" validate="required" type="text" name="templateBean.projectProcedure" value="${templateBean.projectProcedure}" /></td>
			</tr>
			<tr>
				<td colspan="2"  class="td_title">本次支付金额(元)</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.curPaymentAmt" value="${templateBean.curPaymentAmt}"/></td>
			</tr>	
			<tr>
				<td colspan="2" class="td_title">收费部门/收款单位</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.acceptOrgName" value="${templateBean.acceptOrgName}"/></td>
			</tr>
			<tr>
				<td colspan="2" class="td_title">收费文件号</td>
				<td colspan="3"><input  class="inputBorder" validate="required" type="text" name="templateBean.chargeDocNo" value="${templateBean.chargeDocNo}"/></td>
			</tr>
			<tr>
				<td colspan="2"  class="td_title">收费期限</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.chargeDeadlineDesc" value="${templateBean.chargeDeadlineDesc}"/></td>
				<td  class="td_title">是否有政策性减免</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.hasPolicyDerate" value="${templateBean.hasPolicyDerate}"/></td>
			</tr>
			<tr>
				<td rowspan="3" align="center" valign="middle">收款人信息</td>
				<td  class="td_title">收款人名称</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.payer" value="${templateBean.payer}"/></td>
			</tr>
			<tr>
				<td  class="td_title">收款人开户银行</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.payerAccount" value="${templateBean.payerAccount}"/></td>
			</tr>
			<tr>
				<td  class="td_title">收款人账户号</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.payerBank" value="${templateBean.payerBank}"/></td>
			</tr>
			<tr>
				<td colspan="2"  class="td_title">需说明事项</td><td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentDesc">${templateBean.contentDesc}</textarea></td>
			</tr>
		
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>

<%@ include file="template-footer.jsp"%>
