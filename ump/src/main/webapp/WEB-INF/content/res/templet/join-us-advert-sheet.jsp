<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!--招聘广告审批表(总部/项目公司)-->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="120px">
			<col>
			<tr>
				<td class="td_title">名称</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.adverName" value="${templateBean.adverName}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">发布方式及范围</td>
				<td>
					<textarea class="inputBorder contentTextArea" name="templateBean.postScopDesc" style="width:100%;height:180px;">${templateBean.postScopDesc}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">内容简述<br/>(详细内容附后)</td>
				<td>
					<textarea class="inputBorder contentTextArea" name="templateBean.adverDesc" style="width:100%;height:180px;">${templateBean.adverDesc}</textarea>
				</td>
			</tr> 
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
