<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<!--印签会签单-->
		<div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="100"/>
					<col width="250"/>
					<col width="80"/>
					<col />
					<tr>
						<td class="td_title">申请日期</td>
						<td><input class="inputBorder Wdate" validate="required" type="text" onfocus="WdatePicker()" class="Wdate" name="templateBean.appDate" value="${templateBean.appDate}"/></td>
						<td class="td_title">申请人</td>
						<td><input class="inputBorder" validate="required" type="text" name="templateBean.applicant" value="${templateBean.applicant}"  /></td>
					</tr>
					<tr>
					   <td class="td_title">直属部门/公司</td>
					   <td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.appDept" value="${templateBean.appDept}"/></td>
					</tr>
					<tr>
						<td class="td_title">事由</td>
						<td colspan="3"><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.origin">${templateBean.origin}</textarea></td>
					</tr>
					<tr>
					  <td class="td_title">文件名称</td>
					  <td><input type="text" class="inputBorder contentTextArea" validate="required" name="templateBean.fileName" value="${templateBean.fileName}"/></td>
					  <td class="td_title">份数</td>
					  <td><input type="text" class="inputBorder contentTextArea" validate="required" name="templateBean.quantity" value="${templateBean.quantity}"/></td>
					</tr>
					<tr>
					  <td class="td_title">用章种类</td>
					  <td colspan="3" class="chkGroup" validate="required"  align="left">
					    <table class="tb_checkbox">
					    <col width="60"/>
					    <col width="100"/>
					    <col width="100"/>
					    <col width="100"/>
					    <col/>
					    <tr>
					     <td><s:checkbox name="templateBean.typeGroup" cssClass="group"/>公章</td>
					     <td><s:checkbox name="templateBean.typeSpecial" cssClass="group"/>合同专用章 </td>
					     <td><s:checkbox name="templateBean.typePersonnel" cssClass="group"/>人事专用章</td>
					     <td><s:checkbox name="templateBean.typeFinance" cssClass="group"/>财务专用章 </td>
					     <td><s:checkbox name="templateBean.typeLegalPerson" cssClass="group"/>法人章</td>
					    </tr>
					   </table>
					  </td>
					</tr>
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
