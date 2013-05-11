<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 地产公司办公室租凭审批表 --%>
<%@ include file="template-header.jsp"%>
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="110"/>
			<col/>
			<col width="125"/>
			<col/>
			<tr>
				<td class="td_title">申请公司</td>
				<td colspan="3"> 
				<input class="inputBorder" validate="required" type="text" name="templateBean.approveCompany" id="approveCompany" value="${templateBean.approveCompany}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">租凭地点</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.rentAddr" value="${templateBean.rentAddr}" />	
				</td>
				<td class="td_title">面积大小</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.rentArea" value="${templateBean.rentArea}"  onblur="formatVal($(this));"/>	
				</td>
			</tr>
			<tr>
				<td class="td_title">租凭期</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.rentDate" value="${templateBean.rentDate}" />	
				</td>
				<td class="td_title">资金数额(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.fundNo" value="${templateBean.fundNo}"  onblur="formatVal($(this));"/>	
				</td>
			</tr>
			<tr>
				<td class="td_title">费用说明(租凭合同附后)</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.costDeclare">${templateBean.costDeclare}</textarea>
				</td>
				
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
