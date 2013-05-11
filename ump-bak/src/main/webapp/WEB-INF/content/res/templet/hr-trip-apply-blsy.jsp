<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<!--出差申请单(宝龙商业)-->
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm">
		<table  class="mainTable" style="margin-bottom: 5px;">
			<col width="100px"/>
			<col />
			<tr>
			<td class="td_title">
			选择
			</td>
			<td class="chkGroup" align="left" validate="required">
			<table class="tb_checkbox">
				<col width="150">
				<col >
				<tr>
				<td><s:checkbox name="templateBean.positionLevel1" cssClass="group"></s:checkbox>总经理</td>
				<td><s:checkbox name="templateBean.positionLevel2" cssClass="group"></s:checkbox>副总经理</td>
				</tr>
				<tr>
				<td colspan="2"><s:checkbox name="templateBean.positionLevel3" cssClass="group"></s:checkbox>适用于各中心主持工作副总经理级以上人员</td>
				</tr>
				<tr>
				<td colspan="2"><s:checkbox name="templateBean.positionLevel4" cssClass="group"></s:checkbox>其他人员</td>
				</tr>
			</table>
			</td>
			</tr>
		</table>
		<%@ include file="hr-trip-apply-base.jsp"%>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
