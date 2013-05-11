<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<%--费用报销审批表(宝龙商业)--%>
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable" style="margin-bottom: 5px;">
			<col width="90"/>
			<col />
			<tr>
			<td class="td_title">
			</td>
			<td class="chkGroup" align="left" validate="required">
			<table class="tb_checkbox">
				<col width="150">
				<col width="150">
				<tr>
				<td><s:checkbox name="templateBean.positionLevel3"  cssClass="group"></s:checkbox>中心总经理及以下</td>
				<td><s:checkbox name="templateBean.positionLevel4"  cssClass="group"></s:checkbox>集团副总经理</td>
				</tr>
			</table>
			</td>
			</tr>
		</table>
		<%@ include file="fee-reim-bill.jsp"%>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
