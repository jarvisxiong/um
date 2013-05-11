<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<%--开业前商业四级计划--%>

<div class="billContent" align="center">
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
		<col width="120px">
		<col/>
		<col width="100px">
		<col/>
		<tr>
			<td class="td_title">项目名称</td>
			<td>
			 <input validate="required" class="inputBorder" type="text" name="templateBean.projectName" value="${templateBean.projectName}"/>
			</td>
			<td class="td_title">版本</td>
			<td>
			 <input validate="required" class="inputBorder" type="text" name="templateBean.versionNo" value="${templateBean.versionNo}"/>
			</td>
		</tr>
		<tr>
		 <td colspan="4">
			<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentDesc" rows="6" cols="30">${templateBean.contentDesc}</textarea>
		 </td>
		</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>