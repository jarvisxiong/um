<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<%-- 外派人员机票订购申请单 --%>
		<div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="80"/>
					<col />
					<col width="90"/>
					<col/>
					<tr>
						<td  class="td_title">出行人员</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.userName" value="${templateBean.userName}" />
							<input type="hidden" name="templateBean.userCd" value="${templateBean.userCd}"  />
						</td>
						<td  class="td_title">起迄地</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.beginEndPlace" value="${templateBean.beginEndPlace}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">身份证号</td>
						<td colspan="3">
							<input class="inputBorder" validate="required" type="text" name="templateBean.idCardNo" value="${templateBean.idCardNo}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">出行事由</td>
						<td colspan="3">
							<input class="inputBorder" validate="required" type="text" name="templateBean.tripCause" value="${templateBean.tripCause}"  />
						</td>
					</tr>
					<tr>
						<td  class="td_title">出行日期</td>
						<td>
							<input class="Wdate inputBorder" validate="required" type="text" name="templateBean.tripDate" value="${templateBean.tripDate}"  onfocus="WdatePicker()"/>
						</td>
						<td  class="td_title">费用承担部门</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.costDept" value="${templateBean.costDept}"  />
						</td>
					</tr>
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
