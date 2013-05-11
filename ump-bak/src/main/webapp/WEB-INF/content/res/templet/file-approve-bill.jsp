<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<!--文件审批单-->
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
			<%@ include file="template-var.jsp"%>
				
				<table  class="mainTable">
				<col width="100" />
				<col/>
				<col width="50" />
					<tr>
						<td class="td_title">文件标题</td>
						<td colspan="2"><input class="inputBorder" validate="required" type="text" name="templateBean.fileTitle" value="${templateBean.fileTitle}"/></td>
					</tr>
					<tr>
					<td></td>
					<td align="left" class="chkGroup" colspan="2">
						<s:checkbox name="templateBean.secretLevel1" cssClass="group"></s:checkbox>绝密
						<s:checkbox name="templateBean.secretLevel2" cssClass="group"></s:checkbox>机密
						<s:checkbox name="templateBean.secretLevel3" cssClass="group"></s:checkbox>保密
						<s:checkbox name="templateBean.secretLevel4" cssClass="group"></s:checkbox>内部公开
						</td>
					</tr>
					<tr>
						<td class="td_title">发起中心/公司</td>
						<td>
						<s:if test="resApproveInfoId==null">
						<c:set var="sendCenterName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %></c:set>
						<c:set var="sendCenterCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></c:set>
						</s:if>
						<s:else>
						<c:set var="sendCenterName">${templateBean.sendOrgName}</c:set>
						<c:set var="sendCenterCd">${templateBean.sendOrgCd}</c:set>
						</s:else>
						<input validate="required" type="text" name="templateBean.sendOrgName" value="${sendCenterName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
						<input type="hidden" id="centerCd" name="templateBean.sendOrgCd" value="${sendCenterCd}"  />
						</td>
						<td align="left"><s:checkbox name="templateBean.urgencyLevel" cssClass="group"></s:checkbox>急</td>
					</tr>
					<tr>
						<td class="td_title">文件内容简述(详细内容附后)</td>
						<td colspan="2">
						<textarea id="idDesc" class="inputBorder xheditor-simple" validate="required" name="templateBean.fileDesc">${templateBean.fileDesc}</textarea></td>
					</tr>
				</table>
				<%@ include file="template-steps.jsp"%>
			</form>
		</div> 
		<%@ include file="template-footer.jsp"%>
				