<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<!--行政事务申请表-->
		<div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="25%" align="right"/>
					<col width="25%"/>
					<col width="25%"/>
					<col width="25%"/>
					<tr>
						<td class="td_title">申请中心</td>
						<td colspan="3">
						<input validate="required" type="text" name="templateBean.appCenterName" value="${templateBean.appCenterName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
						<input type="hidden" id="res_appCenterCd" name="templateBean.appCenterCd" valeu="${templateBean.appCenterCd}"/>
						</td>
					</tr>
					<tr>
						<td class="td_title">资金用途</td>
						<td><input class="inputBorder" validate="required" type="text" name="templateBean.finUseWay" value="${templateBean.finUseWay}"/></td>
						<td  class="td_title">列支科目</td>
						<td><input class="inputBorder" validate="required" type="text" name="templateBean.subjects" value="${templateBean.subjects}" /></td>
					</tr>
					<tr>
						<td class="td_title">资金数额(元)</td>
						<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.finAmount" value="${templateBean.finAmount}"/></td>
						<td  class="td_title">用款时间</td>
						<td><input onfocus="WdatePicker()" class="Wdate"  validate="required" type="text"  name="templateBean.useTime" value="${templateBean.useTime}"/></td>
					</tr>
					<tr>
						<td class="td_title">申请事由</td>
						<td colspan="3"><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.appReason">${templateBean.appReason}</textarea></td>
						
					</tr>	
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
		