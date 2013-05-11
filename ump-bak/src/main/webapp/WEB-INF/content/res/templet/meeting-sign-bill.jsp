<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--会议签到情况审批表-->
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="80">
			<col>
			<tr>
				<td class="td_title">会议主题:</td>
				<td><input validate="required" class="inputBorder" type="text" name="templateBean.topic" value="${templateBean.topic}"/></td>
			</tr>	
			<tr>
				<td class="td_title">会议时间:</td>
				<td>  
				<input type="text" class="inputBorder Wdate" style="width: 140px;" validate="required" id="noteRemindDate" name="templateBean.meetingDate" value="${templateBean.meetingDate}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
				</td>
			</tr>	
			<tr>
				<td class="td_title">类型:</td>
				<td class="chkGroup" align="left" validate="required" >
				<s:checkbox name="templateBean.isBefore" cssClass="group" ></s:checkbox>会前请假
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<s:checkbox name="templateBean.isAfter" cssClass="group"></s:checkbox>会后说明
				</td>
			</tr>
			<tr>
				<td class="td_title">姓名:</td>
				<td><input class="inputBorder" type="text" name="templateBean.userName" value="${templateBean.userName}"  /></td>
			</tr>
			<tr>
				<td class="td_title" rowspan="2">无法签到原因说明:</td>
				<td><textarea class="inputBorder contentTextArea"  name="templateBean.reason">${templateBean.reason}</textarea></td>
			</tr>
			<tr>
				<td>说明：未签到原因必须为公务原因。</td>
			</tr>
		</table>
		
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
