<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="template-header.jsp"%>
		<br />
		<div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="80"/>
					<col/>
					<col width="60"/>
					<col width="100"/>
					<tr>
						<td class="td_title">工程名称</td>
						<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}"  /></td>
					</tr>
					<tr>
						<td class="td_title">设计单位</td>
						<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.designUnit" value="${templateBean.designUnit}"  /></td>
					</tr>
					<tr>
						<td class="td_title">施工单位</td>
						<td><input class="inputBorder" validate="required" type="text" name="templateBean.builder" value="${templateBean.builder}"  /></td>
						<td class="td_title">检查次数</td>
						<td><input class="inputBorder" validate="required" type="text" name="templateBean.checkNum" value="${templateBean.checkNum}"  /></td>
					</tr>
					<tr>
						<td class="td_title">施工质量检查督办意见</td>
						<td colspan="3"><textarea class="inputBorder contentTextArea" validate="required" type="text" name="templateBean.checkOpinion">${templateBean.checkOpinion}</textarea></td>
					</tr>
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
