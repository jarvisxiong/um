<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--人事审批表 --%>

<div align="center" class="billContent">
	
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="100"/>
			<col/>
			<col width="100"/>
			<col/>
			<tr>
				<td class="td_title">姓    名</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.userName" value="${templateBean.userName}" />
				<input type="hidden"  name="templateBean.userCd" value="${templateBean.userCd}"  />
				</td>
				<td class="td_title">岗位名称</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.position" value="${templateBean.position}"  /></td>
			</tr>
			<tr>
				<td class="td_title">所在部门</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.dept" value="${templateBean.dept}"/>
				</td>
				<td class="td_title">职    级</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.positionLevel" value="${templateBean.positionLevel}" /></td>
			</tr>
			<tr>
				<td class="td_title">入职日期</td>
				<td>
				<input onfocus="WdatePicker()" class="Wdate inputBorder" validate="required" type="text" name="templateBean.entryDate" value="${templateBean.entryDate}"/></td>
				<td class="td_title">员工编号</td>
				<td><input class="inputBorder" type="text" name="templateBean.jobNumber" value="${templateBean.jobNumber}"/></td>
			</tr>
			<tr>
				<td class="td_title">审核种类</td>
				<td colspan="3" class="chkGroup" align="left" validate="required" >
					<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<col width="100">
					<col width="100">
					<tr>
					<td><s:checkbox name="templateBean.auditType1"  cssClass="group"></s:checkbox>试用 </td>
					<td><s:checkbox name="templateBean.auditType2"  cssClass="group"></s:checkbox>转正</td>
					<td><s:checkbox name="templateBean.auditType3"  cssClass="group"></s:checkbox>任免</td>
					<td><s:checkbox name="templateBean.auditType4"  cssClass="group"></s:checkbox>调薪</td>
					</tr><tr>
					<td><s:checkbox name="templateBean.auditType5"  cssClass="group"></s:checkbox>奖励</td>
					<td><s:checkbox name="templateBean.auditType6"  cssClass="group"></s:checkbox>处罚</td>
					<td><s:checkbox name="templateBean.auditType7"  cssClass="group"></s:checkbox>辞退</td>
					<td><s:checkbox name="templateBean.auditType8"  cssClass="group"></s:checkbox>离职</td>
					<td></td>
					</tr>
					</table>
				</td>
			</tr>					
			<tr>
				<td class="td_title">简要说明</td>
				<td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.simpleDesc">${templateBean.simpleDesc}</textarea></td>
			</tr>
		</table>
</div>
