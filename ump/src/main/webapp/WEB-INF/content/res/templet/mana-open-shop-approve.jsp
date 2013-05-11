<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 开店方案、定位、撤场审批表 --%>

<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">

	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="100"/>
			<col/>
			<tr>
				<td class="td_title">项目名称</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.name" value="${templateBean.name}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">类别</td>
				<td class="chkGroup" align="left"  validate="required">
					<s:checkbox name="templateBean.shop" cssClass="group"></s:checkbox>百货
					<s:checkbox name="templateBean.ktv" cssClass="group"></s:checkbox>KTV
					<s:checkbox name="templateBean.game" cssClass="group"></s:checkbox>电玩
				</td>
			</tr>
			<tr>
				<td class="td_title">内容简述<br/>(详细内容附后)</td>
				<td>
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.desc">${templateBean.desc}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
