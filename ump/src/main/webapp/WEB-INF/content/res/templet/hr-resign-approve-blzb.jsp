<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<div align="center">
<!-- 总部辞职审批表 -->
<form action="res-approve-info!save.action" method="post" id="templetForm">
	<table class="mainTable" style="margin-bottom: 5px;">
		<col width="120px"/>
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
						<col width="150">
						<tr>
							<td><s:checkbox name="templateBean.positionGrade1" cssClass="group"></s:checkbox>副总裁级</td>
							<td><s:checkbox name="templateBean.positionGrade2" cssClass="group"></s:checkbox>总经理级</td>
							<td><s:checkbox name="templateBean.positionGrade3" cssClass="group"></s:checkbox>副总经理级</td>
							<td><s:checkbox name="templateBean.positionGrade4" cssClass="group"></s:checkbox>高级经理级</td>
						</tr>
						<tr>
							<td><s:checkbox name="templateBean.positionGrade5" cssClass="group"></s:checkbox>经理级</td>
							<td><s:checkbox name="templateBean.positionGrade6" cssClass="group"></s:checkbox>主管级</td>
							<td><s:checkbox name="templateBean.positionGrade7" cssClass="group"></s:checkbox>专员级</td>
						</tr>
					</table>
				</td>
			</tr>
	</table>
	
	<%@ include file="hr-resign-approve-base.jsp"%>
	
</form>
</div>
<%@ include file="template-footer.jsp"%>