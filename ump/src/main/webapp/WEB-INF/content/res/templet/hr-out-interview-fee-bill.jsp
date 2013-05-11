<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--外地人员面试费用确认单-->
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="15%"/>
			<col width="35%"/>
			<col width="15%"/>
			<col width="35%"/>
			<tr>
				<td class="td_title">应聘人员</td>
				<td >
					<input class="inputBorder" validate="required" type="text" name="templateBean.userName" value="${templateBean.userName}"  />
				</td>
				<td class="td_title">起止地</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.starStopPlace" value="${templateBean.starStopPlace}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">应聘岗位</td>
				<td colspan="3">
					<input class="inputBorder" validate="required" type="text" name="templateBean.positionName" value="${templateBean.positionName}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">来沪日期</td>
				<td>
					<input onfocus="WdatePicker()" class="Wdate" type="text" validate="required" name="templateBean.shangHaiDate" value="${templateBean.shangHaiDate}"/>
				</td>
				<td class="td_title">抵达时间</td>
				<td>
				<input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="Wdate" type="text" validate="required" name="templateBean.arriveDate" value="${templateBean.arriveDate}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">备注</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.remark">${templateBean.remark}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
