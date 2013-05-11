<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--工作指令单-->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="100px">
			<col>
			<tr>
				<td class="td_title">接单责任人</td>
				<td>
					<input id="receiveUserNames" type="text" name="templateBean.receiveUserNames" value="${templateBean.receiveUserNames}"  readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip mutiSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="receiveUserCds" name="templateBean.receiveUserCds"  value="${templateBean.receiveUserCds}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">要求完成时间</td>
				<td align="left">
					<input validate="required" class="inputBorder Wdate" style="width:100px;" type="text" name="templateBean.orderEndDate" value="${templateBean.orderEndDate}"onfocus="WdatePicker()"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">工作指令</td>
				<td>
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.orderContent" rows="6" cols="30">${templateBean.orderContent}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">指令完成情况</td>
				<td>
					<textarea class="inputBorder contentTextArea" name="templateBean.orderCondition" rows="6" cols="30">${templateBean.orderCondition}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>