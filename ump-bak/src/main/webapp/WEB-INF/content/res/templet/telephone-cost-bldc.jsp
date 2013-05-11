<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<%--电话费用报销单(宝龙地产)--%>
<div align="center" class="billContent">
	<form action="res-approve-info!save.action" method="post" id="templetForm">
		<table  class="mainTable" style="margin-bottom: 5px;">
			<col width="100"/>
			<col />
			<tr>
			<td class="td_title">
			</td>
			<td class="chkGroup" align="left" validate="required">
			<table class="tb_checkbox">
				<col width="150">
				<col width="150">
				<tr>
				<td><s:checkbox name="templateBean.positionLevel1" cssClass="group"></s:checkbox>副总级(含)以上</td>
				<td><s:checkbox name="templateBean.positionLevel2" cssClass="group"></s:checkbox>副总级以下</td>
				</tr>
			</table>
			</td>
			</tr>
		</table>
		<%@ include file="telephone-cost-base.jsp"%>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>