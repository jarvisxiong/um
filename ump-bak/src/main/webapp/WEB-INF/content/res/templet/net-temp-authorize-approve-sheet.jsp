<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 网批临时授权审批表 --%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="70"/>
			<col/>
			<col width="70"/>
			<col width="100"/>
			<col width="70"/>
			<col width="100"/>
			<tr>
				<td class="td_title">授权人</td>
				<td>
					<input id="authorizeUserName" validate="required" type="text" name="templateBean.authorizeUserName" value="${templateBean.authorizeUserName}" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip singleSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else>/>
					<input id="authorizeUserCd" type="hidden" name="templateBean.authorizeUserCd" value="${templateBean.authorizeUserCd}"  />
				</td>
				<td class="td_title">职位</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.position" value="${templateBean.position}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">被授权人</td>
				<td>
					<input id="authorizedUserName" validate="required" type="text" name="templateBean.authorizedUserName" value="${templateBean.authorizedUserName}" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip singleSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else>/>
					<input id="authorizedUserCd" type="hidden" name="templateBean.authorizedUserCd" value="${templateBean.authorizedUserCd}"  />
				</td>
				<td class="td_title">开始日期</td>
				<td>
				<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.beginDate" value="${templateBean.beginDate}"/>
				</td>
				<td class="td_title">结束日期</td>
				<td>
				<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.endDate" value="${templateBean.endDate}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">授权原因</td>
				<td colspan="5">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.authorizeReason">${templateBean.authorizeReason}</textarea>
				</td>
			</tr>
			
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
