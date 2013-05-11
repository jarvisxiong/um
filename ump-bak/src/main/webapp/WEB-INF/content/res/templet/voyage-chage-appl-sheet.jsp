<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
	<%-- 航程变更申请单 --%>
		<div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="90"/>
					<col/>
					<col width="80"/>
					<col/>
					<tr>
						<td  class="td_title">姓名</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.userName" value="${templateBean.userName}" />
							<input type="hidden" name="templateBean.userCd" value="${templateBean.userCd}"  />
						</td>
						<td  class="td_title">原订航程</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.originalVoya" value="${templateBean.originalVoya}"  />
						</td>
					</tr>
					<tr>
						<td  class="td_title">需要更改和<br/>预订的航程</td>
						<td colspan="3">
							<input class="inputBorder" validate="required" type="text" name="templateBean.changeVoya" value="${templateBean.changeVoya}"  />
						</td>
					</tr>
					<tr>
						<td  class="td_title">机票航程更改原因</td>
						<td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.cause">${templateBean.cause}</textarea></td>
					</tr>
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
