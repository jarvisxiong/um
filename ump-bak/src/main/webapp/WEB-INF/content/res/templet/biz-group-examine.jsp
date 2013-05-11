<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<%--宝龙商业总部各中心考核审批表--%>

<div class="billContent" align="center">
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
		<col width="120px">
		<col/>
		<tr>
			<td class="td_title">文件名称</td>
			<td>
			 <input validate="required" class="inputBorder" type="text" name="templateBean.fileName" value="${templateBean.fileName}"/>
			</td>
		</tr>
		<tr>
		 <td>文件类型</td>
		 <td class="chkGroup" align="left"  validate="required">
				<table class="tb_checkbox">
					<col width="150">
					<col width="150">
					<tr>
					<td><s:checkbox name="templateBean.quarterExam"  cssClass="group"></s:checkbox>季度工作计划</td>
					<td><s:checkbox name="templateBean.yearExam"  cssClass="group"></s:checkbox>年度工作计划</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
		 <td class="td_title">内容简述<br></br>(详细内容附后)</td>
		<td>
		  <textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentDesc" rows="6" cols="30">${templateBean.contentDesc}</textarea>
		</td>
		</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>