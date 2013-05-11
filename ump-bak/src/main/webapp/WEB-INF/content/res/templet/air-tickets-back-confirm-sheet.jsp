<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

		<%@ include file="template-header.jsp"%>
	<%-- 机票退票确认单 --%>
		<br />
		<div align="center" class="billContent">
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="80"/>
					<col/>
					<col width="80"/>
					<col/>
					<tr>
						<td class="td_title">出行人员</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.userName" value="${templateBean.userName}" />
							<input type="hidden" name="templateBean.userCd" value="${templateBean.userCd}"  />
						</td>
						<td class="td_title">出行时间</td>
						<td>
							<input class="Wdate inputBorder" validate="required" type="text" name="templateBean.tripDate" value="${templateBean.tripDate}"  onfocus="WdatePicker()"/>
						</td>
					</tr>
					<tr>
						<td class="td_title">电子客票号</td>
						<td colspan="3">
							<input class="inputBorder" validate="required" type="text" name="templateBean.eticketNo" value="${templateBean.eticketNo}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">应退航程</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.backVovage" value="${templateBean.backVovage}"  />
						</td>
						<td class="td_title">退票费用</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.backAmount" value="${templateBean.backAmount}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">退票原因</td>
						<td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.backCause">${templateBean.backCause}</textarea></td>
					</tr>
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
