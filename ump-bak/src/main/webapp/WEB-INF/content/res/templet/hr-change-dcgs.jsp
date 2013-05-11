<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<%--人事变动表(地产公司)--%>
<div align="center" class="billContent">
	<form action="res-approve-info!save.action" method="post" id="templetForm">
		<table  class="mainTable" style="margin-bottom: 5px;">
			<col width="80"/>
			<col />
			<tr>
			<td></td>
			<td class="chkGroup" align="left" validate="required">
			<table class="tb_checkbox">
				<col width="250">
				<col width="250">
				<tr>
				<td><s:checkbox name="templateBean.positionLevel1" cssClass="group"></s:checkbox>总经理级及以上</td>
				<td><s:checkbox name="templateBean.positionLevel2" cssClass="group"></s:checkbox>副总经理级</td>
				</tr>
				<tr>
				<td><s:checkbox name="templateBean.positionLevel3" cssClass="group"></s:checkbox>经理级(双线管理人员部门第一负责人除外)</td>
				<td><s:checkbox name="templateBean.positionLevel4" cssClass="group"></s:checkbox>双线管理人员部门第一负责人(财务、成本)</td>
				</tr>
				<tr>
				<td><s:checkbox name="templateBean.positionLevel6" cssClass="group"></s:checkbox>双线管理人员部门第一负责人(人资)</td>
				<td><s:checkbox name="templateBean.positionLevel5" cssClass="group"></s:checkbox>其他人员</td>
				</tr>
			</table>
			</td>
			</tr>
			<tr>
			<td></td>
			<td class="chkGroup" align="left">
			<table class="tb_checkbox">
				<col width="250">
				<tr>
				<td><s:checkbox name="templateBean.positionType1" cssClass="group"></s:checkbox>是否为双线管理部门</td>
				</tr>
			</table>
			</td>
			</tr>
		</table>
		<%@ include file="hr-change-base.jsp"%>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
