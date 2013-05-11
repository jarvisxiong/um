<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<!--文件提取申请单-->

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%><div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="100px">
			<col>
			<col width="80px">
			<col>
			<tr>
				<td class="td_title">申请中心</td>
				<td colspan="3" >
					<s:if test="resApproveInfoId==null">
					<s:set var="sendCenterName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %> </s:set>
					<s:set var="sendCenterCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></s:set>
					</s:if>
					<s:else>
					<s:set var="sendCenterName">${templateBean.applyCenterName}</s:set>
					<s:set var="sendCenterCd">${templateBean.applyCenterCd}</s:set>
					</s:else>
					<input validate="required" type="text" name="templateBean.applyCenterName" value="${sendCenterName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="centerCd" name="templateBean.applyCenterCd" value="${sendCenterCd}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">申请日期</td>
				<td align="left">
					<input validate="required" class="inputBorder Wdate" type="text" name="templateBean.applyDate" value="${templateBean.applyDate}" onfocus="WdatePicker()" style="width:120px;"/>
				</td>
				<td class="td_title">归还日期</td>
				<td align="left">
					<input validate="required" class="inputBorder Wdate" type="text" name="templateBean.returnDate" value="${templateBean.returnDate}" onfocus="WdatePicker()" style="width:120px;"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">提取文档明细</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.fileDetail" rows="6" cols="30">${templateBean.fileDetail}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">事由说明</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.reason" rows="6" cols="30">${templateBean.reason}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>