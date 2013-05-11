<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--A类笔记本补贴申请审批表-->
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="100"/>
			<col />
			<col width="100"/>
			<col />
			<tr>
				<td class="td_title">所属中心/公司</td>
				<td colspan="3">
					<input validate="required" type="text" name="templateBean.centerOrCompanyName" value="${templateBean.centerOrCompanyName}" id="centerOrCompanyName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="centerOrCompanyCd"  name="templateBean.centerOrCompanyCd" value="${templateBean.centerOrCompanyCd}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">申请人</td>
				<td>
					<input id="applyUserName" validate="required" type="text" name="templateBean.userName" value="${templateBean.userName}" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip singleSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else>/>
					<input id="applyUserCd" type="hidden" name="templateBean.userCd" value="${templateBean.userCd}"  />
				</td>
				<td class="td_title">职位</td>
				<td><input class="inputBorder" type="text" id="position"  name="templateBean.position" value="${templateBean.position}"  /></td>
			</tr>
			<tr>
			  <td class="td_title">文件内容简述</td>
			  <td colspan="3">
			    <textarea class="inputBorder contentTextArea" validate="required"  name="templateBean.documentContent">${templateBean.documentContent}</textarea>
			  </td>
			</tr>
			<s:if test="nodeCd==115&&isMyApprove==1">
			<s:hidden id="isEdit" value="true"/>
			<tr>
			   <td class="td_title">使用人</td>
			   <td><input class="inputBorder" type="text" id="applyUser" edit='true' validate="required"   name="templateBean.applyUser" value="${templateBean.applyUser}"  /></td>
			   <td class="td_title">A类笔记本型号</td>
			   <td><input class="inputBorder" type="text" id="notebookType" edit='true' validate="required"   name="templateBean.notebookType" value="${templateBean.notebookType}"  /></td>
			</tr>
			<tr>
			   <td class="td_title">每月补贴金额(元)</td>
			   <td colspan="3"><input class="inputBorder" type="text" id="subsidyMoney" edit='true' validate="required"   name="templateBean.subsidyMoney" value="${templateBean.subsidyMoney}" onblur="formatVal($(this));" /></td>
			</tr>
			<tr>
			   <td class="td_title">补贴生效日期</td>
			   <td><input  class="inputBorder Wdate" onfocus="WdatePicker()" edit='true'  type="text" id="subsidyStartDate"  edit='true' validate="required" name="templateBean.subsidyStartDate" value="${templateBean.subsidyStartDate}"  /></td>
			   <td class="td_title">补贴终止日期</td>
			   <td><input  class="inputBorder Wdate" onfocus="WdatePicker()" edit='true'  type="text" id="subsidyEndDate"  name="templateBean.subsidyEndDate" value="${templateBean.subsidyEndDate}"  /></td>
			</tr>
			<tr>
				<td class="td_title">注</td>
				<td colspan="3"><textarea class="inputBorder contentTextArea" edit='true'  name="templateBean.remark">${templateBean.remark}</textarea></td>
			</tr>
			</s:if>
			<s:else>
			<tr>
			   <td class="td_title">使用人</td>
			   <td><input class="inputBorder" type="text" id="applyUser"  name="templateBean.applyUser" value="${templateBean.applyUser}"  /></td>
			   <td class="td_title">A类笔记本型号</td>
			   <td><input class="inputBorder" type="text" id="notebookType"  name="templateBean.notebookType" value="${templateBean.notebookType}"  /></td>
			</tr>
			<tr>
			   <td class="td_title">每月补贴金额(元)</td>
			   <td colspan="3"><input class="inputBorder" type="text" id="subsidyMoney"  name="templateBean.subsidyMoney" value="${templateBean.subsidyMoney}" onblur="formatVal($(this));" /></td>
			</tr>
			<tr>
			   <td class="td_title">补贴生效日期</td>
			   <td><input  class="inputBorder Wdate" onfocus="WdatePicker()" type="text" id="subsidyStartDate"  name="templateBean.subsidyStartDate" value="${templateBean.subsidyStartDate}"  /></td>
			   <td class="td_title">补贴终止日期</td>
			   <td><input  class="inputBorder Wdate" onfocus="WdatePicker()" type="text" id="subsidyEndDate"  name="templateBean.subsidyEndDate" value="${templateBean.subsidyEndDate}"  /></td>
			</tr>
			<tr>
				<td class="td_title">注</td>
				<td colspan="3"><textarea class="inputBorder contentTextArea" name="templateBean.remark">${templateBean.remark}</textarea></td>
			</tr>
			</s:else>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
