<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!--专项设计成果审批表-->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="100px">
			<col>
			<col width="70px">
			<col>
			<tr>
				<td class="td_title">成果名称</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.resultName" value="${templateBean.resultName}"/>
				</td>
			</tr> 
			<tr>
				<td class="td_title">项目名称</td>
				<td>
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
				<td class="td_title">审批次数</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.examNum" value="${templateBean.examNum}"/>
				</td>
			</tr> 
			<tr>
				<td class="td_title">方案编号</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.schemeSerialNo" value="${templateBean.schemeSerialNo}"/>
				</td>
				<td class="td_title">设计单位</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.designOrgName" value="${templateBean.designOrgName}"/>
					<input type="hidden" id="designOrgCd"  name="templateBean.designOrgCd" value="${templateBean.designOrgCd}"  />
				</td>
			</tr> 
			<tr>
				<td class="td_title">内容简述<br/>(详细内容附后)</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" name="templateBean.contentDesc" style="height:200px;">${templateBean.contentDesc}</textarea>
				</td>
			</tr> 
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
