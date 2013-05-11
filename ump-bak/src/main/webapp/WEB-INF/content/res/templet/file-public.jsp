<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<!--发文签批单-->
<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="80">
			<col>
			<tr>
				<td class="td_title">发文标题</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.title" value="${templateBean.title}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">发文编号</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.serialNumber" value="${templateBean.serialNumber}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">报送单位</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.submissionDept" value="${templateBean.submissionDept}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">主送单位</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.mainDept" value="${templateBean.mainDept}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">抄送单位</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.ccDept" value="${templateBean.ccDept}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">发文单位</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.publicDept" value="${templateBean.publicDept}"/>
				</td>
			</tr>
			<tr>
				<td></td>
				<td align="left" class="chkGroup" validate="required">
					<s:checkbox name="templateBean.publicWay1" cssClass="group"></s:checkbox>PD系统公告
					<s:checkbox name="templateBean.publicWay2" cssClass="group"></s:checkbox>电子邮件
					<s:checkbox name="templateBean.publicWay3" cssClass="group"></s:checkbox>其他方式：
					<input validate="required" class="inputBorder" type="text" style="width:150px;" name="templateBean.otherWay" value="${templateBean.otherWay}"/>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>