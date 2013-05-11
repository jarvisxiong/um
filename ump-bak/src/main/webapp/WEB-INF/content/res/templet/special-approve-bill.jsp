<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<!--特别申请单-->
<%@ include file="template-header.jsp"%>
			
<div align="center" class="billContent">
	<%--274-执行总裁秘书,1-我审批 --%>
	<s:if test="nodeCd==274 && isMyApprove==1">
		<s:hidden id="isEdit" value="true"/>
		<s:set var="isEdit" value="true" />
	</s:if> 
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
			<%@ include file="template-var.jsp"%>
				<s:if test="resApproveInfoId==null">
					<s:set var="sendCenterName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %> </s:set>
					<s:set var="sendCenterCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></s:set>
					<s:set var="defaultUserName"><%=SpringSecurityUtils.getCurrentUserName() %></s:set>
					<s:set var="defaultUserCd"><%=SpringSecurityUtils.getCurrentUiid() %></s:set>
				</s:if>
				<s:else>
					<s:set var="sendCenterName">${templateBean.centerOrgName}</s:set>
					<s:set var="sendCenterCd">${templateBean.centerOrgCd}</s:set>
					<s:set var="defaultUserName">${templateBean.userName}</s:set>
					<s:set var="defaultUserCd">${templateBean.userCd}</s:set>
				</s:else>
				<table  class="mainTable">
					<col width="100" />
					<col/>
					<col width="100" />
					<col/>
					<tr>
						<td class="td_title">申请中心/公司</td>
						<td>
						<input validate="required" type="text" name="templateBean.centerOrgName" value="${sendCenterName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
						<input type="hidden" id="centerCd" name="templateBean.centerOrgCd" value="${sendCenterCd}"  />
						</td>
						<td class="td_title">申请人</td>
						<td>
							<input id="slotUserName" validate="required" type="text" name="templateBean.userName" value="${defaultUserName}" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip singleSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else>/>
							<input id="slotUserCd" type="hidden" name="templateBean.userCd" value="${defaultUserCd}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">申请金额(元)</td>
						<td>
						<input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.applyMoney" value="${templateBean.applyMoney}"  />
						</td>
						<td class="td_title">*批准金额(元)</td>
						<td>
						<input class="inputBorder"<s:if test="#isEdit==true"> edit="true" validate="required"</s:if> type="text" onblur="formatVal($(this));" name="templateBean.approveMoney" value="${templateBean.approveMoney}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">*考核</td>
						<s:if test="#isEdit==true">
						<td colspan="3" class="chkGroup" edit="true" validate="required">
							<table class="tb_checkbox">
							<col width="150">
							<col width="150">
							<tr>
								<td><s:checkbox name="templateBean.checkFlag1" edit="true" cssClass="group"></s:checkbox>列入考核</td>
								<td><s:checkbox name="templateBean.checkFlag2" edit="true" cssClass="group"></s:checkbox>不列入考核</td>
							</tr>
							</table>
						</td>
						</s:if>
						<s:else>
						<td colspan="3" class="chkGroup">
							<table class="tb_checkbox">
							<col width="150">
							<col width="150">
							<tr>
								<td><s:checkbox name="templateBean.checkFlag1" cssClass="group"></s:checkbox>列入考核</td>
								<td><s:checkbox name="templateBean.checkFlag2" cssClass="group"></s:checkbox>不列入考核</td>
							</tr>
							</table>
						</td>
						</s:else>
					</tr>
					<tr>
						<td class="td_title">备注</td>
						<td colspan="3">
						<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.remark">${templateBean.remark}</textarea>
						</td>
					</tr>
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div> 
		<%@ include file="template-footer.jsp"%>
