<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<%--行业人事审批表(宝龙行业)--%>
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm">
		<table  class="mainTable" style="margin-bottom: 5px;">
			<col width="80"/>
			<col />
			<tr>
			<td class="td_title">
			选择
			</td>
			<td class="chkGroup" align="left" validate="required">
			<table class="tb_checkbox">
				<col width="150">
				<col width="150">
				<col width="150">
				<tr>
				<td><s:checkbox name="templateBean.positionLevel1" cssClass="group"></s:checkbox>总经理级及以上</td>
				<td><s:checkbox name="templateBean.positionLevel2" cssClass="group"></s:checkbox>副总经理级及以下</td>
				</tr>
			</table>
			</td>
			</tr>
		</table>
		<%@ include file="hy-hr-approve-base.jsp"%>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
