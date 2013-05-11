<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<!--	标准审批表-项目名称-自由步骤	-->
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="100px"/>
			<col/>
			<tr>
				<td class="td_title">项目名称</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" />
				</td>
				<td class="td_title">设计单位</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.designUnit" value="${templateBean.designUnit}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">设计任务提要（设计任务书附后）</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.remark">${templateBean.remark}</textarea>
				</td>
			</tr>
		</table>
		<br/>
		<%@ include file="template-steps.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
