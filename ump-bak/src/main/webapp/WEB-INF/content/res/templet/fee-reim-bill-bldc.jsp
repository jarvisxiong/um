<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<%--费用报销审批表(地产)--%>
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
				<col width="150">
				<tr>
				<td><s:checkbox name="templateBean.positionLevel1"  cssClass="group"></s:checkbox>部门负责人以下</td>
				<td><s:checkbox name="templateBean.positionLevel2"  cssClass="group"></s:checkbox>部门负责人</td>
				<td><s:checkbox name="templateBean.positionLevel3"  cssClass="group"></s:checkbox>中心总经理</td>
				</tr>
				<tr>
				<td><s:checkbox name="templateBean.positionLevel4"  cssClass="group"></s:checkbox>副总裁</td>
				<td><s:checkbox name="templateBean.positionLevel5"  cssClass="group"></s:checkbox>行政副总裁</td>
				<td></td>
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
<script>
	var mapFeeAccount={
				'0':['zhangsj','张圣佳'],
				'1':['baisha','白莎'],
				'2':['luyan','卢燕'],
				'3':['zhangsj','张圣佳'],
				'4':['luyan','卢燕'],
				'5':['luyan','卢燕'],
				'6':['chenfang1','陈芳'],
				'7':['luyan','卢燕'],
				'8':['luyan','卢燕']};
	function autoSelectAccount(){
		var recordedCompany = $("#js_recordedCompany").val();
		var accountor=mapFeeAccount[recordedCompany];
		$("#235").val(accountor[0]);
		$("#235Name").val(accountor[1]);
	}
</script>