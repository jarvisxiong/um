<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--工作联系单-->
<%@ include file="template-header.jsp"%>


<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%><div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="100px;">
			<col>
			<col width="80px">
			<col>
			<tr>
				<td class="td_title">发单中心</td>
				<td colspan="3">
					<s:if test="resApproveInfoId==null">
					<s:set var="sendCenterName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %> </s:set>
					<s:set var="sendCenterCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></s:set>
					</s:if>
					<s:else>
					<s:set var="sendCenterName">${templateBean.signCenterOrgName}</s:set>
					<s:set var="sendCenterCd">${templateBean.signCenterOrgCd}</s:set>
					</s:else>
					<input validate="required" type="text" name="templateBean.signCenterOrgName" value="${sendCenterName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="centerCd" name="templateBean.signCenterOrgCd" value="${sendCenterCd}"  />
						
				</td>
			</tr>
			<tr>
				<td class="td_title">接单中心</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" id="receCenterOrgName" name="templateBean.receCenterOrgName" value="${templateBean.receCenterOrgName}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">签发日期</td>
				<td><input class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.signDate" value="${templateBean.signDate}"/></td>
				<td class="td_title">接单日期</td>
				<td><input class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.receDate" value="${templateBean.receDate}"/></td>
			
			</tr>
			<tr>
				</tr>	
			<tr>
				<td class="td_title">工作联系内容</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" name="templateBean.workContactDesc" style="width:100%;height:100px;">${templateBean.workContactDesc}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">完成情况</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" name="templateBean.completeDesc" style="width:100%;height:100px;">${templateBean.completeDesc}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
