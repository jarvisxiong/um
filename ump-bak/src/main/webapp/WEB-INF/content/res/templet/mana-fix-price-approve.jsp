<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 宝龙行业经营定价审批表 --%>

<%-- KTV经营定价审批表 --%>
<%-- 电玩经营定价审批表 --%>
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
				<td class="td_title">类别</td>
				<td class="chkGroup" align="left"  validate="required">
					<s:checkbox name="templateBean.priceStrategy" cssClass="group"></s:checkbox>价格策略
					<s:checkbox name="templateBean.priceList" cssClass="group"></s:checkbox>价目表<s:if test="authTypeCd=='HY_JYGL_60'">(超市和包房)</s:if><s:else>(电玩项目)</s:else>
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
