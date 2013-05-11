<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<%@ include file="template-header.jsp"%>
<!--预借款申请单（个人）-->
<div class="billContent" align="center">
	
	
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="60px">
			<col>
			<col width="40px">
			<col>
			<col width="40px">
			<col>
			<col width="90px">
			<col width="100px"> 
			<tr>
				<td class="td_title">借款人</td>
				<td colspan="7">
					<input validate="required" class="inputBorder" type="text" id="lendUserName" name="templateBean.lendUserName" value="${templateBean.lendUserName}"  />
					<input type="hidden" id="lendUserCd"  name="templateBean.lendUserCd" value="${templateBean.lendUserCd}"  />
				</td> 
			</tr>
			<tr>
				<td class="td_title">部门</td>
				<td colspan="2">
					<input validate="required" class="inputBorder" type="text" id="lendDeptOrgName" name="templateBean.lendDeptOrgName" value="${templateBean.lendDeptOrgName}"  />
				</td> 
				<td class="td_title">职务</td>
				<td colspan="2">
					<input validate="required" class="inputBorder" type="text" id="positionName" name="templateBean.positionName" value="${templateBean.positionName}"  />
					<input type="hidden" id="positionCd"  name="templateBean.positionCd" value="${templateBean.positionCd}"  />
				</td> 
				<td class="td_title">预计归还期限</td>
				<td><input class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.planRepaymentDate" value="${templateBean.planRepaymentDate}"/></td>
			</tr>
			<tr>
				<td class="td_title">借款事由<br/>(用途)</td>
				<td colspan="5">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.lendReasonDesc">${templateBean.lendReasonDesc}</textarea>
				</td>
				<td style="text-align:center;">借款类别<br/>(请打勾)</td>
				<td style="text-align: left;" class="chkGroup" validate="required">
					<s:checkbox name="templateBean.selectTempLend" cssClass="group"></s:checkbox>临时借款
					<br/>
					<s:checkbox name="templateBean.selectBackUp" cssClass="group"></s:checkbox>备用金
				</td>
			</tr>
			<tr>
				<td class="td_title">现金借款(元)</td>
				<td colspan="7" style="padding-top:3px;vertical-align: middle;">
					<div style="float:left;">(大写)</div>
					<div style="float:left;">
						<input style="width:220px;" class="inputBorder" type="text" id="cashLendCapitalAmt"  name="templateBean.cashLendCapitalAmt" value="${templateBean.cashLendCapitalAmt}"  readonly="readonly" onfocus="$(this).blur();"/>
					</div>
					<div style="float:left;margin-left:25px;">(小写)</div>
					<div style="float:left;">
						<input class="inputBorder" type="text" id="cashLendSmallAmt"  name="templateBean.cashLendSmallAmt" value="${templateBean.cashLendSmallAmt}" onblur="formatVal($(this));" onchange="formatVal($(this));$('#cashLendCapitalAmt').val(i2c(formatFloat($(this).val())))"/>
					</div>
				</td> 
			</tr>
			<tr>
				<td rowspan="3" class="td_title">转账支票(元)</td>
				<td colspan="7">
					<div style="float:left;">(大写)</div>
					<div style="float:left;">
						<input style="width:220px;" class="inputBorder" type="text" id="tranCheckCapitalAmt"  name="templateBean.tranCheckCapitalAmt" value="${templateBean.tranCheckCapitalAmt}"  readonly="readonly"  onfocus="$(this).blur();"/>
					</div>
					<div style="float:left;margin-left:25px;">(小写)</div>
					<div style="float:left;">
						<input class="inputBorder" type="text" id="tranCheckSmallAmt"  name="templateBean.tranCheckSmallAmt" value="${templateBean.tranCheckSmallAmt}"  onblur="formatVal($(this));" onchange="formatVal($(this));$('#tranCheckCapitalAmt').val(i2c(formatFloat($(this).val())))"/>
					</div>
				</td> 
			</tr>
			<tr>
				<td colspan="7">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder" >
						<tr>
							<td style="width:100px;">收款单位名称</td>
							<td>
							<input validate="required" class="inputBorder" type="text" id="receiveUnitName" name="templateBean.receiveUnitName" value="${templateBean.receiveUnitName}"/>
							</td>
						</tr>
					</table>
				</td> 
			</tr>
			<tr>
				<td colspan="4">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder" >
						<tr>
							<td style="width:100px;">账号</td>
							<td>
							<input validate="required" class="inputBorder" type="text" id="accountNo" name="templateBean.accountNo" value="${templateBean.accountNo}"/>
							</td>
						</tr>
					</table>
				</td> 
				<td colspan="3">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder" >
						<tr>
							<td style="width:80px;">开户银行</td>
							<td>
							<input validate="required" class="inputBorder" type="text" id="bankName" name="templateBean.bankName" value="${templateBean.bankName}"/>
							</td>
						</tr>
					</table>
				</td> 
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>


