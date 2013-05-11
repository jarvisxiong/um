<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--非常规软件使用清单-->
<%@ include file="template-header.jsp"%>
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="80"/>
			<col width="100"/>
			<col width="80"/>
			<col/>
			<tr>
				<td class="td_title">申请人</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.applicant" value="${templateBean.applicant}"  /></td>
				<td class="td_title">所属部门</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.appDept" value="${templateBean.appDept}"  /></td>
			</tr>
			<tr>
				<td rowspan="2" class="td_title">申请事宜</td>
				<td colspan="3" align="left">
				<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder" >
				<tr>
				<td width="40">
				开通
				</td>
				<td>
				<input class="inputBorder"  validate="required" type="text" name="templateBean.appSoft" value="${templateBean.appSoft}"/>
				</td>
				<td width="60">
				软件/端口
				</td>
				</tr>
				</table>
				</td>
				
			</tr>
			<tr>
			 <td colspan="3" align="left">
			 <table border="0" cellpadding="0" cellspacing="0" class="tb_noborder" >
				<tr>
				<td width="40">
				说明
				</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.appDescribe" value="${templateBean.appDescribe}"/>
				</td>
				</tr>
			</table>
			 </td>
			</tr>
			<tr>
				<td class="td_title">申请理由</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.appReason">${templateBean.appReason}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>