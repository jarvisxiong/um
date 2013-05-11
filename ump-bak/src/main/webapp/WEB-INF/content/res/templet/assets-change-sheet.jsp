<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 办公资产维修/更换申请单 --%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="20%"/>
			<col width="30%"/>
			<col width="20%"/>
			<col width="30%"/>
			<tr>
				<td class="td_title">申请日期</td>
				<td>
					<input class="Wdate inputBorder" validate="required" type="text" name="templateBean.applDate" value="${templateBean.applDate}" onfocus="WdatePicker()" />
				</td>
				<td class="td_title">单号</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.applNo" value="${templateBean.applNo}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">申请人姓名</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.userName" value="${templateBean.userName}"  />
				</td>
				<td class="td_title">所在部门</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.dept" value="${templateBean.dept}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">金额(元)</td>
				<td colspan="3">
					<input class="inputBorder" validate="required"  onblur="formatVal($(this));" type="text" name="templateBean.totalMoney" value="${templateBean.totalMoney}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">资产编号</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.assetNo" value="${templateBean.assetNo}"  />
				</td>
				<td class="td_title">购置时间</td>
				<td>
					<input class="Wdate inputBorder" validate="required" type="text" name="templateBean.buyDate" value="${templateBean.buyDate}" onfocus="WdatePicker()" />
				</td>
			</tr>
			<tr>
				<td class="td_title">是否在预算内</td>
				<td colspan="3" style="text-align: left;" class="chkGroup" validate="required">
					<s:checkbox id="inFlag" name="templateBean.inFlag" cssClass="group"></s:checkbox>预算内
					<s:checkbox id="outFlag" name="templateBean.outFlag"  cssClass="group"></s:checkbox>预算外
				</td>
			</tr>
			<tr>
				<td class="td_title">申请原因</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.applCause">${templateBean.applCause}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
