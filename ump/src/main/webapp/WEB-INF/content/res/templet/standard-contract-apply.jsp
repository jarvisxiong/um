<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<%--合同标准版本审批表(新)--%>

<div class="billContent" align="center">
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
		<col width="120px">
		<col/>
		<tr>
			<td class="td_title">文件名称</td>
			<td>
			 <input validate="required" class="inputBorder" type="text" name="templateBean.contractFileName" value="${templateBean.contractFileName}"/>
			</td>
		</tr>
		<tr>
		 <td class="td_title">类型</td>
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
		<td class="td_title">适用范围</td>
		<td>
			<input validate="required" class="inputBorder" type="text" name="templateBean.suitArea" value="${templateBean.suitArea}"/>
		</td>
		</tr>
		<tr>
		<td class="td_title">内容简述(详细内容附后)</td>
		<td>
		  <textarea class="inputBorder contentTextArea" validate="required" name="templateBean.remark" rows="6" cols="30">${templateBean.remark}</textarea>
		</td>
		</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>