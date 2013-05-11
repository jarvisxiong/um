<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- KTV门店一般争端处理方案审批表 --%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">

	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="100"/>
			<col/>
			<tr>
				<td class="td_title">名称</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.name" value="${templateBean.name}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">内容简述<br/>(详细内容附后)</td>
				<td>
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.desc">${templateBean.desc}</textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td class="chkGroup" align="left"  validate="required">
					<table class="tb_noborder">
					<tr><td><s:checkbox name="templateBean.less"  cssClass="group"></s:checkbox>单次金额≤2000元，且月度累计金额≤5000元</td></tr>
					<tr><td><s:checkbox name="templateBean.more"  cssClass="group"></s:checkbox>单次2000元＜金额≤1万元，且月度累计5000元＜金额≤2万元</td></tr>
					<tr><td><s:checkbox name="templateBean.other"  cssClass="group"></s:checkbox>其他</td></tr>
					</table>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
