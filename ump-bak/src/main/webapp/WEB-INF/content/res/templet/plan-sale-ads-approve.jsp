<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 营销活动方案审批表 --%>
<%-- 月度营销活动方案审批表 --%>
<%-- 媒体广告审批表 --%>
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
			<s:if test="authTypeCd=='HY_QHGL_10'">
			<tr>
				<td class="td_title">方案类别</td>
				<td class="chkGroup" align="left"  validate="required">
					<table class="tb_noborder">
					<col width="120">
					<tr>
					<td><s:checkbox name="templateBean.yearSalePlan" cssClass="group"></s:checkbox>年度营销活动方案</td>
					<td><s:checkbox name="templateBean.yearSalePlanOther" cssClass="group"></s:checkbox>年度营销活动方案外营销活动方案</td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.openShopPlan" cssClass="group"></s:checkbox>开店活动方案</td>
					<td><s:checkbox name="templateBean.majorEventPlan" cssClass="group"></s:checkbox>重大活动方案</td>
					</tr>
					</table>
				</td>
			</tr>
			</s:if>
			<s:if test="authTypeCd=='HY_QHGL_30'">
			<tr>
				<td class="td_title">广告类别</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.type" value="${templateBean.type}" />
				</td>
			</tr>
			</s:if>
			<tr>
				<td class="td_title">方案简要说明<br/>(具体内容附后)</td>
				<td>
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.desc">${templateBean.desc}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
