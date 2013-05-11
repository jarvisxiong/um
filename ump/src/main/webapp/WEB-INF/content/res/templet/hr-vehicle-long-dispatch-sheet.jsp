<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<%@ include file="template-header.jsp"%>
<!--长途派车单-->

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%><div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="80">
			<col>
			<col width="80">
			<col>  
			<tr>
				<td class="td_title">中 心</td>
				<td>
					<s:if test="resApproveInfoId==null">
					<s:set var="sendCenterName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %> </s:set>
					<s:set var="sendCenterCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></s:set>
					</s:if>
					<s:else>
					<s:set var="sendCenterName">${templateBean.centerOrgName}</s:set>
					<s:set var="sendCenterCd">${templateBean.centerOrgCd}</s:set>
					</s:else>
					<input validate="required" type="text" name="templateBean.centerOrgName" value="${sendCenterName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="centerCd" name="templateBean.centerOrgCd" value="${sendCenterCd}"  />
				</td>
				<td class="td_title">部 门</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.deptOrgName" value="${templateBean.deptOrgName}"/>
					<input type="hidden" id="centerCd"  name="templateBean.deptOrgCd" value="${templateBean.deptOrgCd}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">用车人</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.userName" value="${templateBean.userName}"/>
					<input type="hidden" id="centerCd"  name="templateBean.userCd" value="${templateBean.userCd}"  />
				</td>
				<td class="td_title">职 位</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.positionName" value="${templateBean.positionName}"/>
					<input type="hidden" id="centerCd"  name="templateBean.positionCd" value="${templateBean.positionCd}"  />
				</td>
			</tr>  
			<tr>
				<td class="td_title">行程安排</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" name="templateBean.schedulingDesc" style="width:100%;height:150px;">${templateBean.schedulingDesc}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">发车时间</td>
				<td><input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" validate="required" class="inputBorder Wdate" type="text" name="templateBean.startDate" value="${templateBean.startDate}"/></td>
				<td class="td_title">返回时间</td>
				<td><input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" validate="required" class="inputBorder Wdate" type="text" name="templateBean.endDate" value="${templateBean.endDate}"/></td>
			</tr>
			<tr>
				<td class="td_title">随行人员</td>
				<td colspan="3"><input validate="required" class="inputBorder" type="text" name="templateBean.memberDesc" value="${templateBean.memberDesc}"/></td>
			</tr>
			<tr>
				<td class="td_title">事 由</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" name="templateBean.subjectDesc" style="width:100%;heigth:150px;">${templateBean.subjectDesc}</textarea>
				</td>
			</tr> 
			<tr>
				<td class="td_title">驾驶员</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.driverName" value="${templateBean.driverName}"/>
				</td>
			</tr> 
			<tr>
				<td class="td_title">车号</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.wagonNumber" value="${templateBean.wagonNumber}"/>
				</td>
				<td class="td_title">公里数</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.miles" value="${templateBean.miles}"/>
				</td>
			</tr> 
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
