<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<%--项目开业/开街/交房时间审批表	--%>
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="100px"/>
			<col/>
			<col width="90px"/>
			<col/>
			<tr>
				<td class="td_title">标题</td>
				<td colspan="3"><r:resInput name="titleName" value="${templateBean.titleName}"/></td>
			</tr>
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="3"><r:resInput name="projectName" value="${templateBean.projectName}"/></td>
			</tr>
			<tr>
				<td class="td_title">开业/开街区域</td>
				<td colspan="3"><r:resInput name="openArea" value="${templateBean.openArea}"/></td>
			</tr>
			<tr>
				<td class="td_title">交房区域</td>
				<td colspan="3"><r:resInput name="roomArea" value="${templateBean.roomArea}"/></td>
			</tr>
			<tr>
				<td class="td_title">原定时间</td>
				<td><r:resDate name="oriDate" value="${templateBean.oriDate}"/></td>
				<td class="td_title">调整时间</td>
				<td><r:resDate name="tarDate" value="${templateBean.tarDate}"/></td>
			</tr>
			<tr>
				<td  class="td_title">相关说明</td>
       			<td colspan="3"><r:resTextArea name="remark" value="${templateBean.remark}"/></td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>