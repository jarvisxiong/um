<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--账套建立申请表 --%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="110"/>
			<col/>
			<col width="140"/>
			<col/>
			<tr>
				<td class="td_title">申请人</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.applicant" value="${templateBean.applicant}" />
				</td>
				<td class="td_title">联系电话</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.tel" value="${templateBean.tel}"  /></td>
			</tr>
			<tr>
				<td class="td_title">帐套单位名称</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.accountName" value="${templateBean.accountName}"/>
				</td>
				<td class="td_title">是否上市帐套</td>
				<td class="chkGroup" align="left" validate="required">
					<s:checkbox  name="templateBean.listedAccountFlg1" cssClass="group"></s:checkbox>是
					<s:checkbox  name="templateBean.listedAccountFlg2" cssClass="group"></s:checkbox>否
				</td>
			</tr>
			<tr>
				<td class="td_title">初始建账日期</td>
				<td>
					<input class="inputBorder Wdate" onfocus="WdatePicker()" validate="required" type="text" name="templateBean.initCreatedDate" value="${templateBean.initCreatedDate}"/>
				</td>
				<td class="td_title">是否有科目初始余额</td>
				<td class="chkGroup" align="left" validate="required">
					<s:checkbox  name="templateBean.initBalanceFlag1" cssClass="group"></s:checkbox>是
					<s:checkbox  name="templateBean.initBalanceFlag2" cssClass="group"></s:checkbox>否
				</td>
			</tr>
			<tr>
				<td class="td_title">帐套操作用户姓名\职位\权限说明</td>
				<td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.accountDesc">${templateBean.accountDesc}</textarea></td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
