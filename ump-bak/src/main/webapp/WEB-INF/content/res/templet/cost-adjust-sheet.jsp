<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 开发项目目标成本调整审批表 --%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="80px"/>
			<col/>
			<col width="100px"/>
			<col/>
			<tr>
				<td class="td_title">工程名称</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.engineeringName" value="${templateBean.engineeringName}" />
				</td>
				<td class="td_title">原目标成本(元)</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.origTargetCost" onblur="formatVal($(this));" value="${templateBean.origTargetCost}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">调整子项</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.adjustSubKey" value="${templateBean.adjustSubKey}" />
				</td>
				<td class="td_title">调整后(元)</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.afterAdjust" onblur="formatVal($(this));" value="${templateBean.afterAdjust}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">调整原因</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.adjustCause">${templateBean.adjustCause}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
