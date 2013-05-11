<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<!--特殊费用申请表-->
		<div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" >
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable" >
					<col width="10%"/>
					<col width="80px"/>
					<col width="30%"/>
					<col width="20%"/>
					<col/>
					<tr>	
						<td colspan="2"  class="td_title">申请公司/总部中心</td>
						<td colspan="3">
						<input validate="required" type="text" name="templateBean.applyOrgName" value="${templateBean.applyOrgName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
						<input type="hidden" id="centerCd"  name="templateBean.applyOrgCd" value="${templateBean.applyOrgCd}"  />
						</td>
					</tr>	
					<tr>			
						<td colspan="2"  class="td_title">资金用途</td>
						<td><input class="inputBorder" validate="required" type="text" name="templateBean.fundsUseDesc" value="${templateBean.fundsUseDesc}"  /></td>
						<td  class="td_title">列支科目</td>
						<td><input class="inputBorder" validate="required" type="text" name="templateBean.subjectDesc" value="${templateBean.subjectDesc}"  /></td>
					</tr>	
					<tr>		
						<td colspan="2"  class="td_title">资金数额 (元)</td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.fundsAmt" value="${templateBean.fundsAmt}"  /></td>
						<td  class="td_title">用款时间</td>
						<td><input class="inputBorder Wdate" validate="required" type="text" name="templateBean.paymentDate" value="${templateBean.paymentDate}"    onfocus="WdatePicker()"/></td>
					</tr>	 
					<%-- hidden by huangbj 2011-04-11 与word不一致
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
					 --%>
					<tr>	
						<td colspan="2"  class="td_title">需说明事项</td>
						<td colspan="3"><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentDesc">${templateBean.contentDesc}</textarea></td>
					</tr>	
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		
		<%@ include file="template-footer.jsp"%>
