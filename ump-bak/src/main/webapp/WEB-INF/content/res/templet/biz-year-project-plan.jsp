<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<%--宝龙商业年度工作计划审批表 --%>

<div class="billContent" align="center">
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
		<col width="120px">
		<col>
		<tr>
			<td class="td_title">文件名称</td>
			<td>
				<input validate="required" class="inputBorder" type="text" name="templateBean.fileName" value="${templateBean.fileName}"/>
			</td>
		</tr>
		<tr>
		 <td class="td_title">内容简述<br></br>(详细内容附后)</td>
		<td>
		  <textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentName" rows="6" cols="30">${templateBean.contentName}</textarea>
		</td>
		</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>