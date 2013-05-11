<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<!--请假单(宝龙商业)-->
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm">
		<table  class="mainTable" style="margin-bottom: 5px;">
			<col width="120px"/>
			<col />
			<tr>
			<td rowspan="2"  class="td_title">
			选择
			</td>
			<td class="chkGroup" align="left" validate="required">
			<table class="tb_checkbox">
				<col width="150">
				<col width="150">
				<tr>
				<td><s:checkbox name="templateBean.dayType1" cssClass="group"></s:checkbox>短期</td>
				<td><s:checkbox name="templateBean.dayType2" cssClass="group"></s:checkbox>长期</td>
				</tr>
			</table>
			</td>
			</tr>
			<tr>
			<td class="chkGroup" align="left" validate="required">
			<table class="tb_checkbox">
				<col width="150">
				<col width="150">
				<tr>
				<td><s:checkbox name="templateBean.positionLevel1" cssClass="group"></s:checkbox>总经理</td>
				<td><s:checkbox name="templateBean.positionLevel2" cssClass="group"></s:checkbox>副总经理</td>
				</tr>
				<tr>
				<td><s:checkbox name="templateBean.positionLevel3" cssClass="group"></s:checkbox>中心第一负责人</td>
				<td><s:checkbox name="templateBean.positionLevel4" cssClass="group"></s:checkbox>其他人员</td>
				</tr>
			</table>
			</td>
			</tr>
		</table>
		<%@ include file="hr-holiday-apply-base.jsp"%>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
