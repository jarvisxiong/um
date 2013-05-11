<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!--限额设计指标审批表-->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="100px">
			<col>
			<col>
			<col width="80px">
			<col>
			<col>
		  <tr>
				<td class="td_title">工程名称</td>
				<td colspan="2">
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
				<td class="td_title">专项名称</td>
				<td colspan="2">
					<input validate="required" class="inputBorder" type="text" name="templateBean.specItemName" value="${templateBean.specItemName}"/>
				</td>
			</tr> 
			<tr>
				<td class="td_title">概算指标</td>
				<td colspan="2">
					<input validate="required" class="inputBorder" type="text" name="templateBean.budgetIndexName" value="${templateBean.budgetIndexName}"/>
				</td>
				<td class="td_title">设计单位</td>
				<td colspan="2">
					<input validate="required" class="inputBorder" type="text" name="templateBean.designOrgName" value="${templateBean.designOrgName}"/>
					<input type="hidden" id="designOrgCd"  name="templateBean.designOrgCd" value="${templateBean.designOrgCd}"  />
				</td>
			</tr> 
			<tr>
				<td class="td_title">限额设计指标</td>
				<td colspan="5">
					<textarea class="inputBorder contentTextArea" name="templateBean.designLimitedIndexDesc" style="width:100%;height:180px;">${templateBean.designLimitedIndexDesc}</textarea>
				</td>
			</tr> 
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
