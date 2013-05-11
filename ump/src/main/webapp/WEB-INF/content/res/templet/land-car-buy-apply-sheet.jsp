<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--地产、行业、商业车辆购置审批表--%>
<%@ include file="template-header.jsp"%>


<%@page import="com.hhz.ump.util.DictContants"%><div align="center" class="billContent">
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="110"/>
			<col/>
			<col width="110"/>
			<col/>
			<tr>
				<td class="td_title"></td>
				<td colspan="3" class="chkGroup">
				<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<tr>
					<td><s:checkbox name="templateBean.carStandard1" cssClass="group"></s:checkbox>标准车型</td>
					<td><s:checkbox name="templateBean.carStandard2" cssClass="group"></s:checkbox>非标准车型</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">选购车型</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.selectCarType" value="${templateBean.selectCarType}" />
				</td>
				<td class="td_title">申购日期</td>
				<td>
				<input class="inputBorder" validate="required" type="text" onfocus="WdatePicker()" name="templateBean.applyDateTime" value="${templateBean.applyDateTime}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">当地报价</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.localPrice" value="${templateBean.localPrice}" />
				</td>
				<td class="td_title">己购车辆</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.yetBuyCars" value="${templateBean.yetBuyCars}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">购车理由</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.buyCarReason">${templateBean.buyCarReason}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
