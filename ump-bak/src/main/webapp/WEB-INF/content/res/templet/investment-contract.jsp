<%@ page contentType="text/html;charset=UTF-8"%>
<!--投资类合同审批表-->
<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="template-header.jsp"%>
		<div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="10%"/>
					<col width="10%"/>
					<col width="30%"/>
					<col width="20%"/>
					<col width="30%"/>
					<tr>
						<td class="td_title" colspan="2">标题</td>
						<td colspan="3">
							<input validate="required" class="inputBorder" type="text" name="templateBean.titleName" value="${templateBean.titleName}" />
						</td>
					</tr>
					<tr>
						<td rowspan="3" class="td_title">合同双方</td>
						<td class="td_title">甲方</td>
						<td><input class="inputBorder" validate="required" type="text" name="templateBean.partyA" value="${templateBean.partyA}"  /></td>
					    <td class="td_title">签约人</td>
						<td><input class="inputBorder" validate="required" type="text" name="templateBean.partyASigning" value="${templateBean.partyASigning}"  /></td>
					</tr>
					<tr>
					    <td class="td_title">乙方</td>
						<td><input class="inputBorder" validate="required" type="text" name="templateBean.partyB" value="${templateBean.partyB}"  /></td>
					    <td class="td_title">签约人</td>
						<td><input class="inputBorder" validate="required" type="text" name="templateBean.partyBSigning" value="${templateBean.partyBSigning}"  /></td>
					</tr>
					<!-- Start Add for part C by zhuxj on 2012.3.31 -->
					<tr>
					    <td class="td_title">丙方</td>
						<td><input class="inputBorder" type="text" name="templateBean.partyC" value="${templateBean.partyC}"  /></td>
					    <td class="td_title">签约人</td>
						<td><input class="inputBorder" type="text" name="templateBean.partyCSigning" value="${templateBean.partyCSigning}"  /></td>
					</tr>
					<!-- End  Add for part C by zhuxj on 2012.3.31 -->
					<tr>
					    <td rowspan="3" align="center">合同主要内容</td>
					    <td class="td_title">主要内容</td>
					    <td colspan="3"><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.mainContents">${templateBean.mainContents}</textarea></td>
					</tr>
					<tr>
					    <td class="td_title">合同价款(元)</td>
					    <td colspan="3"><textarea class="inputBorder contentTextArea" validate="required"  name="templateBean.contractPrice">${templateBean.contractPrice}</textarea></td>
					</tr>
					<tr>
					    <td class="td_title">付款方式</td>
					    <td colspan="3"><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.priceWay">${templateBean.priceWay}</textarea></td>
					</tr>
					
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
