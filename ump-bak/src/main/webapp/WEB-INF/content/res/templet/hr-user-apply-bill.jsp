<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--人员申请表-->
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="100px"/>
			<col/>
			<col width="60px"/>
			<col width="60px"/>
			<col width="60px"/>
			<col width="50px"/>
			<col width="60px"/>
			<col/>
			<tr>
				<td class="td_title">中心</td>
				<td colspan="7">
					<input class="inputBorder" validate="required" type="text" name="templateBean.centerName" value="${templateBean.centerName}" id="centerName"  />
					<input type="hidden" id="centerCd"  name="templateBean.centerCd" value="${templateBean.centerCd}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">职位</td>
				<td colspan="3">
					<input class="inputBorder" validate="required" type="text" name="templateBean.positionName" value="${templateBean.positionName}"  />
				</td>
				<td class="td_title">级别</td>
				<td colspan="3">
					<input class="inputBorder" validate="required" type="text" name="templateBean.positionLevel" value="${templateBean.positionLevel}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">薪金</td>
				<td >
					<input class="inputBorder" validate="required" type="text" name="templateBean.salary" value="${templateBean.salary}"  />
				</td>
				<td class="td_title">性别</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.sex" value="${templateBean.sex}"  />
				</td>
				<td class="td_title">年龄</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.age" value="${templateBean.age}"  />
				</td>
				<td class="td_title">婚否</td>
				<td class="chkGroup" align="left" validate="required">
					<s:checkbox name="templateBean.selectMarriageYes" id="selectMarriageYes" cssClass="group"></s:checkbox>是
					<s:checkbox name="templateBean.selectMarriageNo" id="selectMarriageNo" cssClass="group"></s:checkbox>否
				</td>
			</tr>
			<tr>
				<td class="td_title">到岗日期</td>
				<td >
					<input onfocus="WdatePicker()" class="Wdate" type="text" validate="required" name="templateBean.checkDate" value="${templateBean.checkDate}"/>
				</td>
				<td class="td_title">申请人数</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.applyNum" value="${templateBean.applyNum}" />
				</td>
				<td colspan="4" class="chkGroup" style="text-align:left;" validate="required">
					<s:checkbox name="templateBean.isAdd" id="isAdd" cssClass="group"></s:checkbox>增加
					<s:checkbox name="templateBean.isRepair" id="isRepair" cssClass="group"></s:checkbox>补缺
				</td>
			</tr>
			<tr>
				<td class="td_title">员工类别</td>
				<td colspan="7" class="chkGroup" style="text-align:left;" validate="required">
					<s:checkbox name="templateBean.userKind1" cssClass="group"></s:checkbox>正式工
					<s:checkbox name="templateBean.userKind2" cssClass="group"></s:checkbox>临时工
				</td>
			</tr>
			<tr>
				<td class="td_title">工作职责简述</td>
				<td colspan="7">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.workDuty">${templateBean.workDuty}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">任职要求</td>
				<td colspan="7">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.jobRequire">${templateBean.jobRequire}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">人员申请理由</td>
				<td colspan="7">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.applyReason">${templateBean.applyReason}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>

<!-- 若为空,初始化当期中心 -->
<s:if test="resApproveInfoId==null">
	<script type="text/javascript">
		$("#centerName").val('<%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %>');
		$("#centerCd").val('<%=SpringSecurityUtils.getCurrentCenterCd() %>');
	</script>
</s:if>