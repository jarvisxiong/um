<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!--未刷卡原因确认单(新)-->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<s:set var="conItemCount"><s:property value="templateBean.slotCardProperties.size()"/></s:set>
		<table id="tbConItem" class="mainTable" style="margin-top: 5px;">
			<col width="80">
			<col>
			<col width="70">
			<col>
			<col width="70">
			<col>
			<tr>
				<td class="td_title">中心/区域</td>
				<td colspan="3">
					<s:if test="resApproveInfoId==null">
					<c:set var="sendCenterName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %> </c:set>
					<c:set var="sendCenterCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></c:set>
					<c:set var="defaultOrgName"><%=SpringSecurityUtils.getCurrentDeptName() %></c:set>
					<c:set var="defaultPositionName"><%=SpringSecurityUtils.getRealPositonName() %></c:set>
					<c:set var="defaultUserName"><%=SpringSecurityUtils.getCurrentUserName() %></c:set>
					<c:set var="defaultUserCd"><%=SpringSecurityUtils.getCurrentUiid() %></c:set>
					</s:if>
					<s:else>
					<c:set var="sendCenterName">${templateBean.centerOrgName}</c:set>
					<c:set var="sendCenterCd">${templateBean.centerOrgCd}</c:set>
					<c:set var="defaultOrgName">${templateBean.deptOrgName}</c:set>
					<c:set var="defaultPositionName">${templateBean.positionName}</c:set>
					<c:set var="defaultUserName">${templateBean.userName}</c:set>
					<c:set var="defaultUserCd">${templateBean.userCd}</c:set>
					</s:else>
					<input validate="required" type="text" name="templateBean.centerOrgName" value="${sendCenterName}" id="templateBean.centerOrgName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="centerOrgCd"  name="templateBean.centerOrgCd" value="${sendCenterCd}"  />
				</td>
				<td class="td_title">部门</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.deptOrgName" value="${defaultOrgName}"/>
					<input type="hidden" id="deptOrgCd"  name="templateBean.deptOrgCd" value="${templateBean.deptOrgCd}"  />
				</td>
			</tr> 
			<tr>
				<td class="td_title">姓名</td>
				<td>
					<input id="slotUserName" validate="required" type="text" name="templateBean.userName" value="${defaultUserName}" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip singleSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else>/>
					<input id="slotUserCd" type="hidden" name="templateBean.userCd" value="${defaultUserCd}"  />
				</td>
				<td class="td_title">职位</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.positionName" value="${defaultPositionName}"/>
					<input type="hidden" id="positionCd"  name="templateBean.positionCd" value="${templateBean.positionCd}"  />
				</td> 
				<td class="td_title">
					刷卡日期
				</td>
				<td>
					<input id="slotUserName" validate="required" class="inputBorder Wdate" onfocus="WdatePicker({maxDate:'%y-%M-{%d-1}'})" type="text" name="templateBean.slotDate" value="${templateBean.slotDate}"/>
				</td>
			</tr>  
			<tr>
				<td class="td_title">班次</td>
				<td colspan="5" validate="required" >
					<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<tr>
						<td><s:checkbox name="templateBean.slotTime1" cssClass="group"></s:checkbox>上午上班</td>
						<td><s:checkbox name="templateBean.slotTime2" cssClass="group"></s:checkbox>中午</td>
						<td><s:checkbox name="templateBean.slotTime4" cssClass="group"></s:checkbox>下午下班</td>
					</tr>
					</table>
				</td>
				</tr>
				<tr>
				<td class="td_title">原因</td>
				<td colspan="5" class="chkGroup" validate="required" >
					<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<col width="100">
					<tr>
						<td><s:checkbox name="templateBean.rsGoOut" cssClass="group"></s:checkbox>公事外出</td>
						<td><s:checkbox name="templateBean.rsForget" cssClass="group"></s:checkbox>忘记刷卡</td>
						<td><s:checkbox name="templateBean.rsBug" cssClass="group"></s:checkbox>机器故障</td>
						<td><s:checkbox name="templateBean.selfOut" cssClass="group"></s:checkbox>私事外出</td>
					</tr>
					</table>
				</td>
				</tr>  
				<tr>
				<td class="td_title">备注</td>
					<td colspan="5" class="chkGroup">
						<textarea class="inputBorder contentTextArea" name="templateBean.remark">${templateBean.remark}</textarea>
					</td>
				</tr>
		</table>
		
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>

