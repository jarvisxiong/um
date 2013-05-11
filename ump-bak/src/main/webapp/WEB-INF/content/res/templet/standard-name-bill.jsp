<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<!--标准审批表-名称	-->
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="100px"/>
			<col/>
			<tr>
				<td class="td_title">名称</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.billName" value="${templateBean.billName}"  /></td>
			</tr>
			<tr>
				<td  class="td_title">内容简述<br/>(详细内容附后)</td>
       			<td ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.remark" style="height:200px;">${templateBean.remark}</textarea></td>
			</tr>
			
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
