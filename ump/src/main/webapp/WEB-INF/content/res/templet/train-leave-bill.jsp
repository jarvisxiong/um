<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--培训请假单-->
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="60px"/>
			<col/>
			<col width="40px"/>
			<col/>
			<col width="60px"/>
			<col/>
			<tr>
				<td  class="td_title">姓名</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.userName" value="${templateBean.userName}"  />
				</td>
				<td  class="td_title">部门</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.deptName" value="${templateBean.deptName}"  />
				</td>
				<td  class="td_title">职务</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.positionName" value="${templateBean.positionName}"  />
				</td>
			</tr>
			<tr>
				<td  class="td_title">培训项目</td>
				<td colspan="3">
				<input class="inputBorder" validate="required" type="text" name="templateBean.trainName" value="${templateBean.trainName}"  />
				</td>
				<td  class="td_title">培训日期</td>
				<td>
				<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" name="templateBean.trainDate" value="${templateBean.trainDate}"/>
				</td>
			</tr>
			<tr>
				<td  class="td_title">请假事由</td>
				<td colspan="5">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.reasonDesc">${templateBean.reasonDesc}</textarea>
				</td>
			</tr> 
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
