<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 百货商家租金、扣点审批表 HY_JYGL_40 --%>
<%-- 百货商家补贴、借款审批表 HY_JYGL_50 --%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">

	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="100"/>
			<col/>
			<tr>
				<td class="td_title">商家名称</td>
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
					<s:if test="authTypeCd=='HY_JYGL_40'||authTypeCd=='JD_YYGL_50'">
					<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<col width="100">
					<tr>
					<td><s:checkbox name="templateBean.inPolicy"  cssClass="group"></s:checkbox>政策内</td>
					<td><s:checkbox name="templateBean.outBelow10"  cssClass="group"></s:checkbox>超政策≤10%</td>
					<td><s:checkbox name="templateBean.outAbove10"  cssClass="group"></s:checkbox>超政策＞10%</td>
					</tr>
					</table>
					</s:if>
					<s:elseif test="authTypeCd=='HY_JYGL_50'">
					<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<tr>
					<td><s:checkbox name="templateBean.inPlan"  cssClass="group"></s:checkbox>计划总额内</td>
					<td><s:checkbox name="templateBean.outPlan"  cssClass="group"></s:checkbox>超计划总额</td>
					</tr>
					</table>
					</s:elseif>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
