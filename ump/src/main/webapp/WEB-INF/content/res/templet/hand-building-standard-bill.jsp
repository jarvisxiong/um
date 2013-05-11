<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--交楼标准审批表-->

<%@ include file="template-header.jsp"%>
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="20%"/>
			<col width="30%"/>
			<col width="20%"/>
			<col width="30%"/>
			<tr>
				<td class="td_title">项目名称</td>
				<td>
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
				<td class="td_title">物业范围</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.propertyScope" value="${templateBean.propertyScope}"  />
				</td>
			</tr>
			<tr>
				<td  class="td_title">
					<div>内容简述</div>
					<div>（详细内容附后）</div>
				</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentDesc">${templateBean.contentDesc}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
