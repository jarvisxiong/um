<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<%--标准合同版本审批表(商业)--%>

<div class="billContent" align="center">
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
		<col width="120px">
		<col/>
		<tr>
			<td class="td_title">合同版本名称</td>
			<td>
			 <input validate="required" class="inputBorder" type="text" name="templateBean.contractFileName" value="${templateBean.contractFileName}"/>
			</td>
		</tr>
		<tr>
		 <td class="td_title">文本类型</td>
		 <td class="chkGroup" align="left" validate="required">
				<table class="tb_checkbox">
					<col>
					<tr>
					<td><s:checkbox name="templateBean.fileType1"  cssClass="group"></s:checkbox>租赁意向书、租赁合同</td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.fileType2"  cssClass="group"></s:checkbox>其他各类协议</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
		 <td class="td_title">审批类型</td>
		 <td class="chkGroup" align="left" validate="required">
				<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<tr>
					<td><s:checkbox name="templateBean.approveType1"  cssClass="group"></s:checkbox>新增</td>
					<td><s:checkbox name="templateBean.approveType2"  cssClass="group"></s:checkbox>修订</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
		 <td class="td_title">合同适用范围</td>
		<td>
		  <textarea class="inputBorder contentTextArea" validate="required" name="templateBean.remark" rows="6" cols="30">${templateBean.remark}</textarea>
		</td>
		</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>