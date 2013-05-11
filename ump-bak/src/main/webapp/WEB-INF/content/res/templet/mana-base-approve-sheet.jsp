<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 宝龙行业经营管理基本审批表 --%>

<%-- 百货招商政策审批表 HY_JYGL_30 --%>
<%-- 百货商家租金、扣点审批表 HY_JYGL_40 --%>
<%-- 百货商家补贴、借款审批表 HY_JYGL_50 --%>
<%-- KTV门店一般争端处理方案审批表 --%>
<%-- KTV门店诉讼处理方案审批表 --%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">

	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="100"/>
			<col/>
			<tr>
				<td class="td_title"><s:if test="authTypeCd=='HY_JYGL_40' || authTypeCd=='HY_JYGL_50'">商家名称</s:if><s:else>名称</s:else></td>
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
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
