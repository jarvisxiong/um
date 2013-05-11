<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--EAS用户变更申请表 --%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="120"/>
			<col/>
			<col width="80"/>
			<col/>
			<tr>
				<td class="td_title">申请人</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.applicant" value="${templateBean.applicant}" />
				</td>
				<td class="td_title">申请人帐号</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.applAccount" value="${templateBean.applAccount}"  /></td>
			</tr>
			<tr>
				<td class="td_title">申请日期</td>
				<td colspan="3">
					<input class="Wdate inputBorder" validate="required" type="text" name="templateBean.applDate" value="${templateBean.applDate}" onfocus="WdatePicker()"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">所在单位/部门/职位</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.dept" value="${templateBean.dept}" /></td>
			</tr>
			<tr>
				<td class="td_title">帐套范围</td>
				<td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.accountScope">${templateBean.accountScope}</textarea></td>
			</tr>
			<tr>
				<td class="td_title">职位级别</td>
				<td colspan="3" class="chkGroup" validate="required" align="left">
					是否为公司财务负责人、中心经理级以上人员
					<s:checkbox  name="templateBean.postionLevel1" cssClass="group"></s:checkbox>是
					<s:checkbox  name="templateBean.postionLevel2" cssClass="group"></s:checkbox>否
				</td>
			</tr>
			<tr>
				<td class="td_title">变更原因</td>
				<td colspan="3" align="left" class="chkGroup" validate="required">
					<div><s:checkbox name="templateBean.reason1" cssClass="group"></s:checkbox>岗位变动 </div>
					<div><s:checkbox name="templateBean.reason2" cssClass="group"></s:checkbox>离职 </div>
					<div><s:checkbox name="templateBean.reason3" cssClass="group"></s:checkbox>密码遗失 </div>
					<div><s:checkbox name="templateBean.reason4" cssClass="group"></s:checkbox>其他（说明）
						<input class="inputBorder" type="text" style="width:200px;" name="templateBean.reasonOther" value="${templateBean.reasonOther}"  />
					</div>
				</td>
			</tr>					
			<tr>
				<td class="td_title">变更说明</td>
				<td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.changeDesc">${templateBean.changeDesc}</textarea></td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
