<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<div align="center">
<!-- 商业集团辞职审批表 -->
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
						<col width="200">
						<col width="200">
						<tr>
							<td><s:checkbox name="templateBean.positionGrade1" cssClass="group"></s:checkbox>中心总经理级以上人员</td>
							<td><s:checkbox name="templateBean.positionGrade2" cssClass="group"></s:checkbox>中心副总经理</td>
						</tr>
						<tr>
							<td><s:checkbox name="templateBean.positionGrade4" cssClass="group"></s:checkbox>双线管理人员(财务)</td>
							<td><s:checkbox name="templateBean.positionGrade3" cssClass="group"></s:checkbox>其它人员</td>
						</tr>
					</table>
				</td>
			</tr>
	</table>
	
	<%@ include file="hr-resign-approve-base.jsp"%>
	
</form>
</div>
<%@ include file="template-footer.jsp"%>