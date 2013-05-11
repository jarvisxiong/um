<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@ include file="template-header.jsp"%>
<!--融资类合同审批表-->
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="100"/>
			<col width="60"/>
			<col width="30%"/>
			<col width="20%"/>
			<col/>
			<tr>
				<td rowspan="3" class="td_title">合作双方</td>
				<td  class="td_title">甲方</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.partA" value="${templateBean.partA}"  /></td>
				<td class="td_title">签约人</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.partASign" value="${templateBean.partASign}"  />
				</td>
			</tr>
			<tr>
			    <td class="td_title">乙方</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.partB" value="${templateBean.partB}"  /></td>
				<td class="td_title">签约人</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.partBSign" value="${templateBean.partBSign}"  />
				</td>
			</tr>
			<!-- Start Add for part C by zhuxj on 2012.3.31 -->
			<tr>
			    <td class="td_title">丙方</td>
				<td><input class="inputBorder" type="text" name="templateBean.partC" value="${templateBean.partC}"  /></td>
				<td class="td_title">签约人</td>
				<td>
				<input class="inputBorder" type="text" name="templateBean.partCSign" value="${templateBean.partCSign}"  />
				</td>
			</tr>
			<!-- End  Add for part C by zhuxj on 2012.3.31 -->
			<tr>
				<td rowspan="3" class="td_title">合同主要内容</td>
				<td class="td_title">主要内容</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.mainContent">${templateBean.mainContent}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">合同款价(元)</td>
				<td colspan="3">
					<input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.contractPrice" value="${templateBean.contractPrice}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">付款方式</td>
				<td colspan="3">
					<input class="inputBorder" validate="required" type="text" name="templateBean.payWay" value="${templateBean.payWay}"/>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
